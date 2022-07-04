MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   TICKET MACHINE BRAIN	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
TICKET_Brain:			 

    SCRIPT_NAME TICKET

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
TICKET_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB TICKET_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			GOSUB TICKET_State_Machine					
		ELSE
			GOSUB TICKET_CleanUpBrain
		ENDIF
	ELSE
		GOSUB TICKET_CleanUpBrain
	ENDIF

GOTO TICKET_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
TICKET_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB TICKET_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
TICKET_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		TASK_PLAY_ANIM this_ped slot_in CASINO 4.0 FALSE FALSE FALSE FALSE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- some button pressing anim
			TASK_PLAY_ANIM this_ped slot_bet_01 CASINO 4.0 FALSE FALSE FALSE FALSE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine for how long he waits
			GENERATE_RANDOM_INT_IN_RANGE 5000 10000 iTemp
			TASK_PLAY_ANIM this_ped slot_wait CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- some more button pressing anim			
			TASK_PLAY_ANIM this_ped slot_bet_02 CASINO 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Leave the machine
			TASK_PLAY_ANIM this_ped slot_lose_out CASINO 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine for how long he waits
			GENERATE_RANDOM_INT_IN_RANGE 4000 8000 iTemp
			TASK_PLAY_ANIM this_ped slot_wait CASINO 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Get on the train if possible			
			GOSUB TICKET_CleanUpBrain
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
TICKET_CleanUpBrain:
	REMOVE_ANIMATION CASINO
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
TICKET_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END