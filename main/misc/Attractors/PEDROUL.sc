MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   	ROULETTE BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
PEDROUL_Brain:			 

    SCRIPT_NAME PEDROUL

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering 	// State Machine variables
	LVAR_FLOAT fAnim_Time 						 					// Other variables

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION CASINO	
	WHILE NOT HAS_ANIMATION_LOADED CASINO
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	//---MAIN LOOP---
PEDROUL_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB PEDROUL_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetCasinoPanic = 0	//--- If there are global flags to check add them here
				GOSUB PEDROUL_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO PEDROUL_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB PEDROUL_CleanUpBrain
		ENDIF
	ELSE
		GOSUB PEDROUL_CleanUpBrain
	ENDIF

GOTO PEDROUL_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
PEDROUL_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB PEDROUL_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
PEDROUL_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		TASK_PLAY_ANIM this_ped roulette_in CASINO 4.0 FALSE FALSE FALSE FALSE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Place bet
			TASK_PLAY_ANIM this_ped roulette_bet CASINO 4.0 FALSE FALSE FALSE FALSE -1
			SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_ROUL_CHAT iTemp		 
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 8000 15000 iTemp
			TASK_PLAY_ANIM this_ped roulette_loop CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he wins or loses 
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped roulette_win CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_CASINO_WIN iTemp
			ELSE
				TASK_PLAY_ANIM this_ped roulette_lose CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_CASINO_LOSE iTemp
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he plays again or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				iSubStateStatus = 1 
			ELSE
				TASK_PLAY_ANIM this_ped roulette_out CASINO 4.0 FALSE FALSE FALSE FALSE -1
				++iSubStateStatus	
			ENDIF		
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			GOSUB PEDROUL_CleanUpBrain
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
PEDROUL_CleanUpBrain:
	REMOVE_ANIMATION CASINO
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
PEDROUL_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB PEDROUL_CleanUpBrain
	ENDIF	

GOTO  PEDROUL_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
PEDROUL_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END