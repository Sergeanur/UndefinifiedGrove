MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   	OTB TILL BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
OTBTILL_Brain:

    SCRIPT_NAME OTBTILL

    LVAR_INT this_ped iSpawnedAtAttractor											 // parameters arriving from caller
    LVAR_INT test_brain_flag iPedState iSubStateStatus iIsPlayingAnims 	// State Machine variables
	LVAR_FLOAT fAnim_Time fX fY fZ
	LVAR_INT iTemp iIsCowering iFoodModel 

    test_brain_flag = 0
	
    IF test_brain_flag = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF

	//--- Request animations & Models
	REQUEST_ANIMATION INT_SHOP	
	WHILE NOT HAS_ANIMATION_LOADED INT_SHOP
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	iIsPlayingAnims = 0
	iIsCowering = 0

	//---MAIN LOOP---
OTBTILL_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB OTBTILL_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetOTBPanic = 0	//--- If there are global flags to check add them here			
				GOSUB OTBTILL_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM
				GOTO OTBTILL_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB OTBTILL_CleanUpBrain	
		ENDIF
	ELSE
		GOSUB OTBTILL_CleanUpBrain	
	ENDIF

GOTO OTBTILL_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
OTBTILL_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB OTBTILL_PedState1
		BREAK
	ENDSWITCH
RETURN
/********************************************
				ORDER 
********************************************/
OTBTILL_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0			
		iIsPlayingAnims = 1		
		TASK_PLAY_ANIM this_ped IDLE_chat PED 4.0 TRUE FALSE FALSE FALSE 5000
		++iSubStateStatus
	BREAK

   	CASE 1
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			TASK_PLAY_ANIM this_ped shop_pay INT_SHOP 1000.0 FALSE FALSE FALSE FALSE 0
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			GOSUB OTBTILL_CleanUpBrain		   					
		ENDIF									
	BREAK
	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
OTBTILL_CleanUpBrain:
	REMOVE_ANIMATION INT_SHOP	
	//--- This command will also terminate this brain script
	IF NOT IS_CHAR_DEAD this_ped
		TASK_USE_CLOSEST_MAP_ATTRACTOR this_ped 50.0 0 0.0 0.0 0.0 OTBWTCH
	ENDIF
	TERMINATE_THIS_SCRIPT

RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
OTBTILL_GlobalFlagIntercept:

	WAIT 0

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 				
	ELSE 
		GOSUB OTBTILL_CleanUpBrain
	ENDIF		

GOTO  OTBTILL_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
OTBTILL_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END