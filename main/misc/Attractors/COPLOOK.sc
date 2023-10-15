MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************  	COP LOOKING BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
COPLOOK_Brain:			 

    SCRIPT_NAME COPLOOK

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsKilling 	// State Machine variables
	LVAR_FLOAT fAnim_Time 						 					// Other variables
	
    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION COP_AMBIENT	
	WHILE NOT HAS_ANIMATION_LOADED COP_AMBIENT
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	//---MAIN LOOP---
COPLOOK_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB COPLOOK_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0 
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
				GOTO COPLOOK_GlobalFlagIntercept 
			ELSE
				GOSUB COPLOOK_State_Machine
			ENDIF		
		ELSE
			GOSUB COPLOOK_CleanUpBrain
		ENDIF
	ELSE
		GOSUB COPLOOK_CleanUpBrain
	ENDIF

GOTO COPLOOK_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
COPLOOK_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB COPLOOK_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
COPLOOK_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start Watching
		TASK_PLAY_ANIM this_ped Coplook_in COP_AMBIENT 4.0 FALSE FALSE FALSE FALSE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped Coplook_loop COP_AMBIENT 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if nods or shakes
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped Coplook_nod COP_AMBIENT 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_PLAY_ANIM this_ped Coplook_shake COP_AMBIENT 4.0 TRUE FALSE FALSE FALSE 1000
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped Coplook_loop COP_AMBIENT 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he thinks or watches
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped Coplook_think COP_AMBIENT 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_PLAY_ANIM this_ped Coplook_watch COP_AMBIENT 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF		
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped Coplook_loop COP_AMBIENT 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Quit the loop
			TASK_PLAY_ANIM this_ped Coplook_out COP_AMBIENT 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 7
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			GOSUB COPLOOK_CleanUpBrain
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
COPLOOK_CleanUpBrain:
	REMOVE_ANIMATION COP_AMBIENT
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
COPLOOK_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_KILL_CHAR_ON_FOOT iIsKilling
		IF iIsKilling = FINISHED_TASK
			TASK_KILL_CHAR_ON_FOOT this_ped scplayer
		ENDIF			 
	ELSE 
		GOSUB COPLOOK_CleanUpBrain
	ENDIF	

GOTO  COPLOOK_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
COPLOOK_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END