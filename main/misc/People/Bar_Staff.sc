MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************		BAR STAFF 		**********************************************************************
************************************************						**********************************************************************
*********************************************************************************************************************************************/
{
//BAR_Staff:

    SCRIPT_NAME BARSTAF

    LVAR_INT this_ped iMyAttractor[4] // parameters arriving from caller
	LVAR_FLOAT fDepthOfBar fAttractorRadius
	LVAR_INT iSkipBarserve_glass
	//--- INTERNALS
    LVAR_INT other_ped iPedState iSubStateStatus // State Machine variables
	//--- Internals
    LVAR_INT iFoodModel iIsCowering iTemp    
	LVAR_FLOAT fTill_offX fTill_offY fTill_offZ fCustomerX fCustomerY fCustomerZ 

    other_ped = 0
	
    IF other_ped = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
		CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 other_ped
        //---Other input entities here
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMyAttractor[0] // middle of bar
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMyAttractor[1] // end 1 of bar
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMyAttractor[2] // end 2 of bar
		ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 -1 iMyAttractor[3] // where the drinks are
    ENDIF

	//--- Misc Setups that require an 'alive' check
	IF NOT  IS_CHAR_DEAD this_ped 
		//--- Set up the Decision Maker		
		SET_CHAR_DECISION_MAKER this_ped iGlobalShopkeeperDM
	ENDIF

	//--- Request animations
	REQUEST_ANIMATION BAR
	WHILE NOT HAS_ANIMATION_LOADED BAR
		REQUEST_ANIMATION BAR
		WAIT 0 
	ENDWHILE
	
	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsCowering = 0
	IF fAttractorRadius <= 0.0
		fAttractorRadius = 6.0
	ENDIF

	//---MAIN LOOP---
BAR_Staff_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
	//GOSUB BAR_Staff_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive  
	AND iTerminateAllAmbience = 0
		IF iPedState = 2
			IF NOT IS_CHAR_DEAD other_ped 
				IF iSetCasinoPanic = 1	//--- If there are global flags to check add them here
				OR iSetBarPanic = 1
				OR iSetSTRIPPanic = 1
					//---Somebody has set a global flag to true
					GOTO BAR_Staff_GlobalFlagIntercept 
				ELSE
					GOSUB BAR_Staff_State_Machine					
				ENDIF
			ELSE
				iPedState = 1
				iSubStateStatus = 0
				GOSUB BAR_Staff_State_Machine
			ENDIF
		ELSE
			IF iSetCasinoPanic = 1	//--- If there are global flags to check add them here
			OR iSetBarPanic = 1
			OR iSetSTRIPPanic = 1
				//---Somebody has set a global flag to true
				GOTO BAR_Staff_GlobalFlagIntercept 
			ELSE
				GOSUB BAR_Staff_State_Machine					
			ENDIF
		ENDIF		
	ELSE
		GOSUB BAR_Staff_CleanUpBrain
	ENDIF

GOTO BAR_Staff_Main_Loop 
	//---END OF MAIN LOOP---
 
 
/********************************************
			STATE MACHINE
********************************************/
BAR_Staff_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 		
			GOSUB BAR_Staff_PedState1
		BREAK
		CASE 2	//---STATE 2: 
			GOSUB BAR_Staff_PedState2
		BREAK
		CASE 3	//---STATE 3: 
			GOSUB BAR_Staff_PedState3
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
BAR_Staff_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		GET_SCRIPT_TASK_STATUS this_ped TASK_USE_ATTRACTOR iTemp
		IF iTemp = FINISHED_TASK 
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1
		GET_CHAR_COORDINATES this_ped fTill_offX fTill_offY fTill_offZ		
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ fAttractorRadius 0 BARGUY other_ped 		
		IF other_ped > -1								 						
			GET_CHAR_HEALTH other_ped iTemp
			IF iTemp = 99 // signals that has been served
			//--- discard this guy
				other_ped = -1
			ELSE
				GET_SCRIPT_TASK_STATUS other_ped TASK_PLAY_ANIM iTemp
				IF iTemp = FINISHED_TASK
					++iPedState
					iSubStateStatus = 0			
				ELSE
					//--- discard this guy
					other_ped = -1
				ENDIF
			ENDIF
		ENDIF

		GET_SCRIPT_TASK_STATUS this_ped TASK_USE_ATTRACTOR iTemp
		IF iTemp = FINISHED_TASK 
			TASK_USE_ATTRACTOR this_ped iMyAttractor[1]
			++iSubStateStatus
		ENDIF

	BREAK

	CASE 2
		GET_CHAR_COORDINATES this_ped fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ fAttractorRadius 0 BARGUY other_ped 
		IF other_ped > -1								 						
			GET_CHAR_HEALTH other_ped iTemp
			IF iTemp = 99 // signals that has been served
				//--- discard this guy
				other_ped = -1
			ELSE
				GET_SCRIPT_TASK_STATUS other_ped TASK_PLAY_ANIM iTemp
				IF iTemp = FINISHED_TASK
					++iPedState
					iSubStateStatus = 0			
				ELSE
					//--- discard this guy
					other_ped = -1
				ENDIF
			ENDIF
		ENDIF

		GET_SCRIPT_TASK_STATUS this_ped TASK_USE_ATTRACTOR iTemp
		IF iTemp = FINISHED_TASK 
			TASK_USE_ATTRACTOR this_ped iMyAttractor[2]
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
		GET_CHAR_COORDINATES this_ped fTill_offX fTill_offY fTill_offZ
		GET_USER_OF_CLOSEST_MAP_ATTRACTOR fTill_offX fTill_offY fTill_offZ fAttractorRadius 0 BARGUY other_ped 
		IF other_ped > -1								 						
			GET_CHAR_HEALTH other_ped iTemp
			IF iTemp = 99 // signals that has been served
				//--- discard this guy
				other_ped = -1
			ELSE
				GET_SCRIPT_TASK_STATUS other_ped TASK_PLAY_ANIM iTemp
				IF iTemp = FINISHED_TASK
					++iPedState
					iSubStateStatus = 0			
				ELSE
					//--- discard this guy
					other_ped = -1
				ENDIF
			ENDIF
		ENDIF

		GET_SCRIPT_TASK_STATUS this_ped TASK_USE_ATTRACTOR iTemp
		IF iTemp = FINISHED_TASK 
			TASK_USE_ATTRACTOR this_ped iMyAttractor[0]
			iSubStateStatus = 0
		ENDIF
	BREAK

 	ENDSWITCH

RETURN
/********************************************
				STATE 2
********************************************/
BAR_Staff_PedState2:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Walk to reach this ped
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS other_ped 0.0 fDepthOfBar 0.0 fTill_offX fTill_offY fTill_offZ 
		TASK_GO_STRAIGHT_TO_COORD this_ped fTill_offX fTill_offY fTill_offZ PEDMOVE_WALK -2 
		++iSubStateStatus
	BREAK

	CASE 1
		GET_SCRIPT_TASK_STATUS this_ped TASK_GO_STRAIGHT_TO_COORD iTemp
		IF iTemp = FINISHED_TASK 
			TASK_TURN_CHAR_TO_FACE_CHAR this_ped other_ped
			++iSubStateStatus	
		ENDIF
	BREAK

	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK 
			//--- TRIGGER AN EVENT IN CUSTOMER 
			SET_CHAR_HEALTH other_ped 99 
			TASK_PLAY_ANIM this_ped Barserve_order BAR 4.0 TRUE FALSE FALSE FALSE 3000
			++iSubStateStatus	
		ENDIF
	BREAK

	CASE 3
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK 
			//--- Retrieve drink
			TASK_USE_ATTRACTOR this_ped iMyAttractor[3]
			++iSubStateStatus			
		ENDIF		
	BREAK

	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_USE_ATTRACTOR iTemp
		IF iTemp = FINISHED_TASK 
			IF iSkipBarserve_glass = 0
				TASK_PLAY_ANIM  this_ped Barserve_glass BAR 4.0 FALSE FALSE FALSE FALSE 0
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK 
			TASK_GO_STRAIGHT_TO_COORD this_ped fTill_offX fTill_offY fTill_offZ PEDMOVE_WALK -2 
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_GO_STRAIGHT_TO_COORD iTemp
		IF iTemp = FINISHED_TASK 
			TASK_TURN_CHAR_TO_FACE_CHAR this_ped other_ped
			++iSubStateStatus	
		ENDIF
	BREAK

	CASE 7
		GET_SCRIPT_TASK_STATUS this_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp
		IF iTemp = FINISHED_TASK 
			//--- TRIGGER AN EVENT IN CUSTOMER 
			SET_CHAR_HEALTH other_ped 99 
			TASK_PLAY_ANIM this_ped Barserve_give BAR 4.0 TRUE FALSE FALSE FALSE 3000
			++iPedState
			iSubStateStatus	= 0
		ENDIF
	BREAK
	ENDSWITCH
RETURN
/********************************************
				STATE 3
********************************************/
BAR_Staff_PedState3:  
	SWITCH iSubStateStatus	 				

	CASE 0
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			//--- Customer Served
			iPedState = 1
			iSubStateStatus = 0
		ENDIF		
	BREAK

	ENDSWITCH
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
BAR_Staff_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_HANDS_UP iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_HANDS_UP this_ped 60000
		ENDIF			 
	ELSE
		GOSUB BAR_Staff_CleanUpBrain
	ENDIF


GOTO  BAR_Staff_GlobalFlagIntercept
/*******************************************
			CLEAN UP BRAIN
********************************************/
BAR_Staff_CleanUpBrain:
	REMOVE_ANIMATION BAR
	MARK_CHAR_AS_NO_LONGER_NEEDED this_ped
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
BAR_Staff_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END