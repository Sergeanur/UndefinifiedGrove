MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   	CARD TABLE BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
PEDCARD_Brain:			 

    SCRIPT_NAME PEDCARD

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
PEDCARD_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB PEDCARD_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetCasinoPanic = 0	//--- If there are global flags to check add them here
				GOSUB PEDCARD_State_Machine					
			ELSE
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO PEDCARD_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB PEDCARD_CleanUpBrain
		ENDIF
	ELSE
		GOSUB PEDCARD_CleanUpBrain
	ENDIF


GOTO PEDCARD_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
PEDCARD_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB PEDCARD_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
PEDCARD_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		TASK_PLAY_ANIM this_ped cards_in CASINO 4.0 FALSE FALSE FALSE FALSE -1
		SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_HIT iTemp
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Pick cards
			TASK_PLAY_ANIM this_ped cards_pick_01 CASINO 4.0 FALSE FALSE FALSE FALSE -1		 
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 8000 15000 iTemp
			TASK_PLAY_ANIM this_ped cards_loop CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he raises or picks 
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped cards_raise CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_DOUBLE iTemp
			ELSE
				TASK_PLAY_ANIM this_ped cards_pick_02 CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_HIT iTemp
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 1500 5000 iTemp
			TASK_PLAY_ANIM this_ped cards_loop CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_STAY iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he wins or loses 
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped cards_win CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_WIN iTemp
			ELSE
				TASK_PLAY_ANIM this_ped cards_lose CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_BJ_LOSE iTemp
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he plays again or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				iSubStateStatus = 1 
			ELSE
				TASK_PLAY_ANIM this_ped cards_out CASINO 4.0 FALSE FALSE FALSE FALSE -1
				++iSubStateStatus	
			ENDIF		
		ENDIF
	BREAK

	CASE 7
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			GOSUB PEDCARD_CleanUpBrain
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
PEDCARD_CleanUpBrain:
	REMOVE_ANIMATION CASINO
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
PEDCARD_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB PEDCARD_CleanUpBrain
	ENDIF	

GOTO  PEDCARD_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
PEDCARD_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END