MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   FRUIT MACHINE BRAIN	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
PEDSLOT_Brain:			 

    SCRIPT_NAME PEDSLOT

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
PEDSLOT_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB PEDSLOT_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetCasinoPanic = 0	//--- If there are global flags to check add them here
				GOSUB PEDSLOT_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO PEDSLOT_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB PEDSLOT_CleanUpBrain
		ENDIF
	ELSE
		GOSUB PEDSLOT_CleanUpBrain
	ENDIF

GOTO PEDSLOT_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
PEDSLOT_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB PEDSLOT_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
PEDSLOT_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		TASK_PLAY_ANIM this_ped slot_in CASINO 4.0 FALSE FALSE FALSE FALSE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- first bet
			TASK_PLAY_ANIM this_ped slot_bet_01 CASINO 4.0 FALSE FALSE FALSE FALSE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine for how long he waits
			GENERATE_RANDOM_INT_IN_RANGE 1500 5000 iTemp
			TASK_PLAY_ANIM this_ped slot_wait CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he places another bet
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped slot_bet_02 CASINO 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_PLAY_ANIM this_ped slot_wait CASINO 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF		
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he wins or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50				
				TASK_PLAY_ANIM this_ped slot_win_out CASINO 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_GAMB_SLOT_WIN iTemp
			ELSE
				TASK_PLAY_ANIM this_ped slot_lose_out CASINO 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF		
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he plays again or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				iSubStateStatus = 0 
			ELSE
				GOSUB PEDSLOT_CleanUpBrain
			ENDIF		
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
PEDSLOT_CleanUpBrain:
	REMOVE_ANIMATION CASINO
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
PEDSLOT_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB PEDSLOT_CleanUpBrain
	ENDIF	

GOTO  PEDSLOT_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
PEDSLOT_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END