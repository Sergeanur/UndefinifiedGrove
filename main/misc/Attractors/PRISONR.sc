MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   	PRISONER BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
PRISONR_Brain:

    SCRIPT_NAME PRISONR

    LVAR_INT this_ped iSpawnedAtAttractor											 // parameters arriving from caller
    LVAR_INT test_brain_flag iPedState iSubStateStatus 	 	// State Machine variables
	LVAR_FLOAT fAnim_Time fX fY fZ
	LVAR_INT iTemp iIsCowering iOther_Prisoner iCiggy iFXSystemID 

    test_brain_flag = 0
	
    IF test_brain_flag = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF

	IF NOT IS_CHAR_DEAD this_ped
		GET_PED_TYPE this_ped iTemp
		IF iTemp = PEDTYPE_COP 
			DELETE_CHAR this_ped
			TERMINATE_THIS_SCRIPT 
		ENDIF 
	ENDIF

	//--- Request animations & Models
	REQUEST_ANIMATION GANGS
	REQUEST_MODEL CIGAR
	WHILE NOT HAS_ANIMATION_LOADED GANGS
		WAIT 0 
	ENDWHILE	
	WHILE NOT HAS_MODEL_LOADED CIGAR
		WAIT 0 
	ENDWHILE

	IF NOT IS_CHAR_DEAD this_ped
		GET_CHAR_COORDINATES this_ped fX fY fZ 
		CREATE_OBJECT CIGAR fX fY fZ iCiggy 
		TASK_PICK_UP_OBJECT this_ped iCiggy 0.0 0.1 -0.02 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1				
	ENDIF									   
	
	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsCowering = 0

	//---MAIN LOOP---
PRISONR_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB PRISONR_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
			SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM
			GOTO PRISONR_GlobalFlagIntercept 
		ELSE
			GOSUB PRISONR_State_Machine
		ENDIF		
	ELSE
		GOSUB PRISONR_CleanUpBrain	
	ENDIF

GOTO PRISONR_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
PRISONR_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB PRISONR_PedState1
		BREAK
	ENDSWITCH
RETURN					 
/********************************************
				ORDER 
********************************************/
PRISONR_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0			
		GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp 
		IF iTemp >= 80
			GET_CHAR_COORDINATES this_ped fX fY fZ
			GET_RANDOM_CHAR_IN_SPHERE fX fY fZ 2.0 TRUE TRUE TRUE iOther_Prisoner
			IF NOT IS_CHAR_DEAD iOther_Prisoner
			AND NOT iOther_Prisoner = this_ped  
				TASK_KILL_CHAR_ON_FOOT this_ped iOther_Prisoner
				iSubStateStatus = 2
				BREAK
			ELSE
				TASK_PLAY_ANIM this_ped smkcig_prtl GANGS 1.0 FALSE FALSE FALSE FALSE 0
				CREATE_FX_SYSTEM_ON_CHAR EXHALE this_ped 0.0 0.0 0.0 TRUE iFXSystemID
				ATTACH_FX_SYSTEM_TO_CHAR_BONE iFXSystemID this_ped BONE_HEAD				
				iSubStateStatus	= 3
				BREAK
			ENDIF
		ELSE
			IF iTemp >= 30 
				TASK_PLAY_ANIM this_ped IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE 5000
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_CHAT iTemp
				iSubStateStatus = 1
				BREAK
			ELSE
				TASK_PLAY_ANIM this_ped smkcig_prtl GANGS 1.0 FALSE FALSE FALSE FALSE 0
				CREATE_FX_SYSTEM_ON_CHAR EXHALE this_ped 0.0 0.0 0.0 TRUE iFXSystemID
				ATTACH_FX_SYSTEM_TO_CHAR_BONE iFXSystemID this_ped BONE_HEAD				
				iSubStateStatus	= 3
				BREAK
			ENDIF		  	
		ENDIF		
	BREAK

   	CASE 1
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			iSubStateStatus = 0
		ENDIF									
	BREAK
	
	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_KILL_CHAR_ON_FOOT iTemp
		IF iTemp = FINISHED_TASK
			iSubStateStatus = 0
		ENDIF									
	BREAK
	
	CASE 3
		IF IS_CHAR_PLAYING_ANIM this_ped smkcig_prtl
			GET_CHAR_ANIM_CURRENT_TIME this_ped smkcig_prtl fx
			IF fx >= 0.5				
			AND fx < 1.0
				PLAY_FX_SYSTEM iFXSystemID
				++iSubStateStatus
			ENDIF
		ELSE
			iSubStateStatus = 0
			KILL_FX_SYSTEM iFXSystemID
		ENDIF									
	BREAK

	CASE 4
		IF IS_CHAR_PLAYING_ANIM this_ped smkcig_prtl
			GET_CHAR_ANIM_CURRENT_TIME this_ped smkcig_prtl fx
			IF fx >= 1.0				
				KILL_FX_SYSTEM iFXSystemID
				iSubStateStatus = 0
			ENDIF
		ELSE
			iSubStateStatus = 0
			KILL_FX_SYSTEM iFXSystemID
		ENDIF									
	BREAK

	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
PRISONR_CleanUpBrain:
	REMOVE_ANIMATION GANGS
	MARK_MODEL_AS_NO_LONGER_NEEDED CIGAR 	
	DELETE_OBJECT iCiggy	
	KILL_FX_SYSTEM iFXSystemID	
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
PRISONR_GlobalFlagIntercept:

	WAIT 0

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 				
	ELSE 
		GOSUB PRISONR_CleanUpBrain
	ENDIF		

GOTO  PRISONR_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
PRISONR_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END