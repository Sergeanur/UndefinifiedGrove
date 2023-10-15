MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   PIZZA CUSTOMER BRAIN  **********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
Pcustom_Brain:

    SCRIPT_NAME PCUSTOM

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
Pcustom_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB Pcustom_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetPizzaPanic = 0	//--- If there are global flags to check add them here
				GOSUB Pcustom_State_Machine					
			ELSE
				//---Somebody has set a global flag to true
				GOTO Pcustom_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB Pcustom_CleanUpBrain	
		ENDIF
	ELSE
		GOSUB Pcustom_CleanUpBrain	
	ENDIF

GOTO Pcustom_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
Pcustom_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB Pcustom_PedState1
		BREAK
	ENDSWITCH
RETURN
/********************************************
				ORDER FOOD
********************************************/
Pcustom_PedState1:  
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
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.1 1.0 fX fY fZ
			IF $shop_name = &FDPIZA
				CREATE_OBJECT CJ_PIZZA_2 fX fY fZ iFoodModel
			ELSE
				CREATE_OBJECT CJ_BURG_2 fX fY fZ iFoodModel
			ENDIF
			TASK_PICK_UP_OBJECT this_ped ifoodModel 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE					   					
			++iSubStateStatus
		ENDIF									
	BREAK

	CASE 3		
		GOSUB Pcustom_CleanUpBrain
	BREAK
	ENDSWITCH
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
Pcustom_CleanUpBrain:
	REMOVE_ANIMATION INT_SHOP		
	MARK_OBJECT_AS_NO_LONGER_NEEDED ifoodModel
	IF NOT IS_CHAR_DEAD this_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY this_ped
		//--- This command will also terminate this brain script
		TASK_USE_CLOSEST_MAP_ATTRACTOR this_ped 20.0 0 0.0 0.0 0.0 PCHAIR
	ENDIF
	TERMINATE_THIS_SCRIPT

RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
Pcustom_GlobalFlagIntercept:

	WAIT 0

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_STRING_EMPTY $shop_name
	AND iTerminateAllAmbience = 0
		IF NOT IS_CHAR_DEAD this_ped
			GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
			IF iIsCowering = FINISHED_TASK
				TASK_DUCK  this_ped -2
			ENDIF			 				
		ENDIF		
	ELSE 
		GOSUB Pcustom_CleanUpBrain
	ENDIF		

GOTO  Pcustom_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
Pcustom_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END