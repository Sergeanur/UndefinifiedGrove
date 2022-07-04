MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   	OTB WATCHER BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
OTBWTCH_Brain:			 

    SCRIPT_NAME OTBWTCH

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering 	// State Machine variables
	LVAR_FLOAT fAnim_Time 						 					// Other variables

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION OTB	
	WHILE NOT HAS_ANIMATION_LOADED OTB
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	//---MAIN LOOP---
OTBWTCH_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB OTBWTCH_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetOTBPanic = 0	//--- If there are global flags to check add them here
				GOSUB OTBWTCH_State_Machine					
			ELSE
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO OTBWTCH_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB OTBWTCH_CleanUpBrain
		ENDIF
	ELSE
		GOSUB OTBWTCH_CleanUpBrain
	ENDIF

GOTO OTBWTCH_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
OTBWTCH_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB OTBWTCH_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
OTBWTCH_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start Watching
		TASK_PLAY_ANIM this_ped wtchrace_in OTB 4.0 FALSE FALSE FALSE FALSE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped wtchrace_loop OTB 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he incites or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped wtchrace_cmon OTB 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_PLAY_ANIM this_ped wtchrace_loop OTB 4.0 TRUE FALSE FALSE FALSE 1000
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped wtchrace_loop OTB 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he wins or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped wtchrace_win OTB 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_PLAY_ANIM this_ped wtchrace_lose OTB 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF		
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Quit the loop
			TASK_PLAY_ANIM this_ped wtchrace_out OTB 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			GOSUB OTBWTCH_CleanUpBrain
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
OTBWTCH_CleanUpBrain:
	REMOVE_ANIMATION OTB
	IF NOT IS_CHAR_DEAD this_ped
		TASK_USE_CLOSEST_MAP_ATTRACTOR this_ped 100.0 0 0.0 0.0 0.0 OTBSLP
	ENDIF
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
OTBWTCH_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB OTBWTCH_CleanUpBrain
	ENDIF	

GOTO  OTBWTCH_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
OTBWTCH_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END