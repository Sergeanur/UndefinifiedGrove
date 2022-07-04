MISSION_START

CONST_INT DANCER_MINIGAME_NOTHING	0
CONST_INT DANCER_MINIGAME_STARTED	1

/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************   	   DANCER BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
DANCER_Brain:			 

    SCRIPT_NAME DANCER

    LVAR_INT this_ped iSpawnedAtAttractor		 						// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering  		 	// State Machine variables
	LVAR_FLOAT fAnim_Time 						 						// Other variables
	LVAR_INT iDanceTime iDanceMoves iDanceMinigameStatus

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION DANCING	
	WHILE NOT HAS_ANIMATION_LOADED DANCING
		WAIT 0 
	ENDWHILE

	IF NOT IS_CHAR_DEAD this_ped
		GET_CHAR_MODEL this_ped iTemp
		IF iTemp < 10
			iDanceTime = iTemp * 1500
		ELSE
			IF iTemp < 100
				iDanceTime = iTemp * 150
			ELSE
				iDanceTime = iTemp * 15
			ENDIF
		ENDIF		
		iPedState = iTemp / 2			
		iPedState *= 2
		IF iTemp = iPedState // check if number is odd or even
			IF IS_CHAR_MALE this_ped
				iDanceMoves = 1
			ELSE
				iDanceMoves = 2
			ENDIF 
		ELSE
			iDanceMoves = 3
		ENDIF
	ENDIF

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	//---MAIN LOOP---
DANCER_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB DANCER_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetBarPanic = 0	//--- If there are global flags to check add them here
				GOSUB DANCER_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO DANCER_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB DANCER_CleanUpBrain
		ENDIF
	ELSE
		GOSUB DANCER_CleanUpBrain
	ENDIF

GOTO DANCER_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
DANCER_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB DANCER_CheckForComment
			GOSUB DANCER_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
DANCER_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			IF iDanceMoves =1
				TASK_PLAY_ANIM this_ped dnce_M_c DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
			ELSE
				IF iDanceMoves = 2
					TASK_PLAY_ANIM this_ped dnce_M_b DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
				ELSE
					TASK_PLAY_ANIM this_ped dnce_M_a DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 							
				ENDIF
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			IF iDanceMoves =1
				TASK_PLAY_ANIM this_ped dnce_M_d DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
			ELSE
				IF iDanceMoves = 2
					TASK_PLAY_ANIM this_ped dnce_M_c DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
				ELSE
					TASK_PLAY_ANIM this_ped dnce_M_b DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 							
				ENDIF
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			IF iDanceMoves =1
				TASK_PLAY_ANIM this_ped dnce_M_a DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
			ELSE
				IF iDanceMoves = 2
					TASK_PLAY_ANIM this_ped dnce_M_d DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 
				ELSE
					TASK_PLAY_ANIM this_ped dnce_M_c DANCING 4.0 TRUE FALSE FALSE TRUE iDanceTime		 							
				ENDIF
			ENDIF
			iSubStateStatus = 0
		ENDIF
	BREAK
	ENDSWITCH

RETURN
/********************************************
			CHECK FOR COMMENTS
********************************************/
DANCER_CheckForComment:
	IF IS_BIT_SET iDanceReport DANCE_MINIGAME_RUNNING 
		iDanceMinigameStatus = DANCER_MINIGAME_STARTED
	ELSE
		IF iDanceMinigameStatus = DANCER_MINIGAME_STARTED					
			IF iDanceScore > GF_DANCE_SCORE_REQUIRED
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_DANCE_IMPR_HIGH iTemp				
				iDanceMinigameStatus = DANCER_MINIGAME_NOTHING	
			ELSE
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_DANCE_IMPR_NOT iTemp				
				iDanceMinigameStatus = DANCER_MINIGAME_NOTHING	
			ENDIF
		ENDIF
	ENDIF
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
DANCER_CleanUpBrain:
	REMOVE_ANIMATION DANCING
	IF NOT IS_CHAR_DEAD this_ped
		TASK_USE_CLOSEST_MAP_ATTRACTOR this_ped 100.0 0 0.0 0.0 0.0 DANCER
	ENDIF
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
DANCER_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB DANCER_CleanUpBrain
	ENDIF	

GOTO  DANCER_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
DANCER_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END