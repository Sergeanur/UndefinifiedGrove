MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************	SHOPKEEPER BRAIN	**********************************************************************
************************************************						**********************************************************************
*********************************************************************************************************************************************/
{
//Skeeper_Brain:

    SCRIPT_NAME SKBRAIN

    LVAR_INT this_ped iMy_Attractor // parameters arriving from caller
    LVAR_INT other_ped iPedState iSubStateStatus iIsPlayingAnims // State Machine variables
	//--- Internals
    LVAR_INT iFoodTray iIsCowering iTemp    
	LVAR_FLOAT fAnim_Time fTray_offX fTray_offY fTray_offZ fTill_offX fTill_offY fTill_offZ

    other_ped = 0
	
    IF other_ped = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
        //---Other input entities here
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMy_Attractor
		CREATE_OBJECT 0 0.0 0.0 0.0 iFoodTray
    ENDIF
	
	//--- Misc Setups that require an 'alive' check
	IF NOT IS_CHAR_DEAD this_ped 
		//--- Set up the Decision Maker		
		SET_CHAR_DECISION_MAKER this_ped iGlobalShopkeeperDM
	ENDIF

	//--- Request animations
	REQUEST_ANIMATION FOOD
	WHILE NOT HAS_ANIMATION_LOADED FOOD
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsPlayingAnims = 0 
	iIsCowering = 0

	//---MAIN LOOP---
Skeeper_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB Skeeper_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive
 	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOSUB Skeeper_CleanUpBrain
		ELSE
			IF IS_PLAYER_TARGETTING_CHAR player1 this_ped
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer 
				IF DOES_OBJECT_EXIST iFoodTray
					DELETE_OBJECT iFoodTray
				ENDIF 
			ENDIF
			//--- If there are global flags to check add them here 
			IF iSetPizzaPanic = 0	
				GOSUB Skeeper_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				GOTO Skeeper_GlobalFlagIntercept 
			ENDIF
		ENDIF		
	ELSE
		GOSUB Skeeper_CleanUpBrain
	ENDIF

GOTO Skeeper_Main_Loop 
	//---END OF MAIN LOOP---
 
 
/********************************************
			STATE MACHINE
********************************************/
Skeeper_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 		
			GOSUB Skeeper_PedState1
		BREAK
		CASE 2	//---STATE 2: 
			GOSUB Skeeper_PedState2
		BREAK
		CASE 3 //---STATE 3: 
			GOSUB Skeeper_PedState3	
		BREAK		
	ENDSWITCH

RETURN
/********************************************
				GET TRAY
********************************************/
Skeeper_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Check if there is a customer at the till
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 3.0 3.0 fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ 20.0 CJ_FF_TILL_que PCUSTOM other_ped 
		IF other_ped > -1
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped -5.0 -5.0 -5.0 fTray_offX fTray_offY fTray_offZ
			CREATE_OBJECT pizzahigh fTray_offX fTray_offY fTray_offZ iFoodTray		   
			TASK_PLAY_ANIM  this_ped SHP_Tray_In FOOD 4.0 FALSE FALSE FALSE FALSE -1				
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1			
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped SHP_Tray_In
			GET_CHAR_ANIM_CURRENT_TIME this_ped	SHP_Tray_In fAnim_Time
			IF fAnim_Time = 1.0					
			//---State completed
			iSubStateStatus = 0
			++iPedState		   					
			ENDIF
		ENDIF
	BREAK
 	ENDSWITCH

RETURN
/********************************************
		LIFT TRAY UP AND SHOW IT
********************************************/
Skeeper_PedState2:  
	SWITCH iSubStateStatus	 				

	CASE 0
		IF DOES_OBJECT_EXIST ifoodTray
			TASK_PICK_UP_OBJECT this_ped ifoodTray 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE			   					
			TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped SHP_Tray_Lift FOOD 4.0 FALSE FALSE FALSE TRUE -1
			TIMERB = 0
			++iSubStateStatus
		ELSE
		   //---State completed
			iSubStateStatus = 0
			++iPedState	
		ENDIF
	BREAK

	CASE 1			
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped SHP_Tray_Lift
			GET_CHAR_ANIM_CURRENT_TIME this_ped	SHP_Tray_Lift fAnim_Time
			IF fAnim_Time = 1.0					
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 2			
		IF TIMERB > 2000		  			    
		   //---State completed
			iSubStateStatus = 0
			++iPedState	
		ENDIF
	BREAK
	ENDSWITCH
RETURN
/********************************************
		PUT THE TRAY AWAY
********************************************/
Skeeper_PedState3:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Check if there is still the old customer at the till
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 3.0 3.0 fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ 20.0 CJ_FF_TILL_que PCUSTOM other_ped 
		IF other_ped = -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1
		TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped SHP_Tray_Return FOOD 4.0 FALSE FALSE FALSE FALSE -1		
		++iSubStateStatus		
	BREAK

	CASE 2			
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped SHP_Tray_Return
			GET_CHAR_ANIM_CURRENT_TIME this_ped	SHP_Tray_Return fAnim_Time
			IF fAnim_Time = 1.0					
				IF DOES_OBJECT_EXIST ifoodTray 
					DELETE_OBJECT ifoodTray
				ENDIF
				TASK_USE_ATTRACTOR this_ped iMy_Attractor	
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 3
		//---State completed - Step Back to State 1
		iSubStateStatus = 0
		iPedState = 1		
	BREAK

	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
Skeeper_CleanUpBrain:
	REMOVE_ANIMATION FOOD
	MARK_OBJECT_AS_NO_LONGER_NEEDED ifoodTray 	
	IF NOT IS_CHAR_DEAD this_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY this_ped
	ENDIF
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
Skeeper_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOSUB Skeeper_CleanUpBrain
		ELSE
			IF NOT IS_CHAR_DEAD this_ped
				GET_SCRIPT_TASK_STATUS this_ped	TASK_HANDS_UP iIsCowering 
				IF iIsCowering = FINISHED_TASK
					TASK_HANDS_UP this_ped -2 
					IF IS_CHAR_HOLDING_OBJECT this_ped -1
						IF DOES_OBJECT_EXIST ifoodTray 
							DELETE_OBJECT ifoodTray
						ENDIF
					ENDIF
				ENDIF			 				
			ENDIF		
		ENDIF		
	ELSE
		GOSUB Skeeper_CleanUpBrain
	ENDIF

GOTO  Skeeper_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
Skeeper_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END