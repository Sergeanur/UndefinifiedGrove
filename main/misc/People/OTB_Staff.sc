MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************		OTB STAFF 		**********************************************************************
************************************************						**********************************************************************
*********************************************************************************************************************************************/
{
//OTB_Staff:

    SCRIPT_NAME OTBSTAF

    LVAR_INT this_ped iMy_Attractor // parameters arriving from caller
    LVAR_INT other_ped iPedState iSubStateStatus // State Machine variables
	//--- Internals
    LVAR_INT iFoodModel iIsCowering iTemp    
	LVAR_FLOAT fAnim_Time fTill_offX fTill_offY fTill_offZ

    other_ped = 0
	
    IF other_ped = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
        //---Other input entities here
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMy_Attractor
    ENDIF

	//--- Request animations
	REQUEST_ANIMATION INT_SHOP
	WHILE NOT HAS_ANIMATION_LOADED INT_SHOP
		WAIT 0 
	ENDWHILE
	
	//--- Misc Setups that require an 'alive' check
	IF NOT  IS_CHAR_DEAD this_ped 
		//--- Set up the Decision Maker		
		SET_CHAR_DECISION_MAKER this_ped iGlobalShopkeeperDM
	ENDIF

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsCowering = 0

	//---MAIN LOOP---
OTB_Staff_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB OTB_Staff_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND iTerminateAllAmbience = 0
		IF iSetOTBPanic = 0	//--- If there are global flags to check add them here
			GOSUB OTB_Staff_State_Machine					
		ELSE
			//---Somebody has set a global flag to true
			GOTO OTB_Staff_GlobalFlagIntercept 
		ENDIF		
	ELSE
		GOSUB OTB_Staff_CleanUpBrain
	ENDIF

GOTO OTB_Staff_Main_Loop 
	//---END OF MAIN LOOP---
 
 
/********************************************
			STATE MACHINE
********************************************/
OTB_Staff_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 		
			GOSUB OTB_Staff_PedState1
		BREAK
		CASE 2	//---STATE 2: 
			GOSUB OTB_Staff_PedState2
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
OTB_Staff_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Check if there is a customer at the till
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 3.0 3.0 fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ 3.0 CJ_FF_TILL_QUE PCUSTOM other_ped 
		IF other_ped > -1
			TASK_PLAY_ANIM  this_ped shop_cashier INT_SHOP 4.0 FALSE FALSE FALSE FALSE -1				
			++iSubStateStatus			
		ENDIF

	BREAK

	CASE 1			
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped shop_cashier
			GET_CHAR_ANIM_CURRENT_TIME this_ped	shop_cashier fAnim_Time
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
				STATE 2
********************************************/
OTB_Staff_PedState2:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Check if there is still the old customer at the till

		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 3.0 3.0 fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ 3.0 CJ_FF_TILL_QUE PCUSTOM other_ped 
		IF other_ped = -1
		   	//---State completed - Step Back to State 1
		   	iSubStateStatus = 0
		   	iPedState = 1
		ENDIF		
	BREAK

	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
OTB_Staff_CleanUpBrain:
	REMOVE_ANIMATION INT_SHOP
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
OTB_Staff_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_HANDS_UP iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_HANDS_UP this_ped -2
		ENDIF			 
	ELSE
		GOSUB OTB_Staff_CleanUpBrain
	ENDIF


GOTO  OTB_Staff_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
OTB_Staff_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END