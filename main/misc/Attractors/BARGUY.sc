MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************    BAR CUSTOMER BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
BARGUY_Brain:

    SCRIPT_NAME BARGUY

    LVAR_INT this_ped iSpawnedAtAttractor											 // parameters arriving from caller
    LVAR_INT test_brain_flag iPedState iSubStateStatus iIsPlayingAnims 	// State Machine variables
	LVAR_FLOAT fTemp fX fY fZ
	LVAR_INT iTemp iIsCowering iDrinkModel 

    test_brain_flag = 0
	
    IF test_brain_flag = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION BAR	
	WHILE NOT HAS_ANIMATION_LOADED BAR
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsPlayingAnims = 0
	iIsCowering = 0

	//---MAIN LOOP---
BARGUY_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB BARGUY_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetCasinoPanic = 0	//--- If there are global flags to check add them here
				IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
					GOSUB BARGUY_State_Machine					
				ELSE
					iSetCasinoPanic = 1
					GOTO BARGUY_GlobalFlagIntercept
				ENDIF
			ELSE
				//---Somebody has set a global flag to true
				GOTO BARGUY_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB BARGUY_CleanUpBrain	
		ENDIF
	ELSE
		GOSUB BARGUY_CleanUpBrain	
	ENDIF


GOTO BARGUY_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
BARGUY_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB BARGUY_PedState1
		BREAK
	ENDSWITCH
RETURN
/********************************************
				ORDER DRINK
********************************************/
BARGUY_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0							
		GET_SCRIPT_TASK_STATUS this_ped TASK_LOOK_ABOUT iTemp
		IF iTemp = FINISHED_TASK
			TASK_LOOK_ABOUT this_ped -2
			SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_BAR_CHAT iTemp 		
			++iSubStateStatus
		ENDIF
	BREAK

   	CASE 1
		//--- Using health as a unique ped flag I can alter remotely
		GET_CHAR_HEALTH this_ped iTemp
		IF iTemp = 99
			SET_CHAR_HEALTH this_ped 100
			TASK_PLAY_ANIM this_ped Barcustom_order BAR 4.0 FALSE FALSE FALSE FALSE 0
			++iSubStateStatus
		ENDIF 
	BREAK

	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			TASK_PLAY_ANIM this_ped Barcustom_loop BAR 4.0 TRUE FALSE FALSE FALSE 600000
			SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_BAR_CHAT iTemp
			++iSubStateStatus
		ENDIF
	BREAK

   	CASE 3
		//--- Using health as a unique ped flag I can alter remotely
		GET_CHAR_HEALTH this_ped iTemp
		IF iTemp = 99			
			TASK_PLAY_ANIM this_ped Barcustom_get BAR 4.0 FALSE FALSE FALSE FALSE 0
			++iSubStateStatus
		ENDIF 
	BREAK
	
	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.1 1.0 fX fY fZ
			//CREATE_OBJECT DYN_GLASS fX fY fZ iDrinkModel
			//TASK_PICK_UP_OBJECT this_ped iDrinkModel 0.1 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE					   					
			++iSubStateStatus
		ENDIF									
	BREAK

	CASE 5
		//--- DRINK		
		IF IS_CHAR_MALE this_ped
			TASK_PLAY_ANIM this_ped dnk_stndM_loop BAR 4.0 TRUE FALSE FALSE FALSE 15000
		ELSE
			TASK_PLAY_ANIM this_ped dnk_stndF_loop BAR 4.0 TRUE FALSE FALSE FALSE 15000
		ENDIF
		++iSubStateStatus
	BREAK
	
	CASE 6
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			SET_CHAR_HEALTH this_ped 100
			IF $txtEntryExit = &BAR1
				iSubStateStatus = 0 // loop back, the guy is never allowed to walk about here	
			ELSE
				GOSUB BARGUY_CleanUpBrain
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
BARGUY_CleanUpBrain:
	REMOVE_ANIMATION BAR		
	//MARK_OBJECT_AS_NO_LONGER_NEEDED iDrinkModel
	IF NOT IS_CHAR_DEAD this_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY this_ped		
	ENDIF
	TERMINATE_THIS_SCRIPT

RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
BARGUY_GlobalFlagIntercept:

	WAIT 0

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK  this_ped -2
		ENDIF			 				
	ELSE
		GOSUB BARGUY_CleanUpBrain
	ENDIF		

GOTO  BARGUY_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
BARGUY_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END