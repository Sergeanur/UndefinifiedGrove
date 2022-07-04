MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************    STRIP WATCHER BRAIN 	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
STRIPM_Brain:			 

    SCRIPT_NAME STRIPM

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering 			// State Machine variables
	LVAR_FLOAT fAnim_Time fX fY fZ
	LVAR_INT iStripper iTempMoney

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION STRIP	
	WHILE NOT HAS_ANIMATION_LOADED STRIP
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	//---MAIN LOOP---
STRIPM_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB STRIPM_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped			
			IF iSetSTRIPPanic = 0	//--- If there are global flags to check add them here
				GET_CHAR_COORDINATES this_ped fX fY fZ
				GET_USER_OF_CLOSEST_MAP_ATTRACTOR fX fY fZ 15.0 0 STRIPW iStripper 		
				IF NOT IS_CHAR_DEAD iStripper
					GOSUB STRIPM_State_Machine					
				ELSE
					GOSUB STRIPM_CleanUpBrain
				ENDIF
			ELSE
				//---Somebody has set a global flag to true
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO STRIPM_GlobalFlagIntercept 
			ENDIF		
		ELSE
			GOSUB STRIPM_CleanUpBrain
		ENDIF
	ELSE
		GOSUB STRIPM_CleanUpBrain
	ENDIF

GOTO STRIPM_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
STRIPM_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB STRIPM_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
STRIPM_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state			 
			iTemp = this_ped
			IF iTemp > 10000
				iTemp /= 10000
			ENDIF
			IF iTemp < 1000
				iTemp += 2000
			ENDIF
			TASK_PLAY_ANIM this_ped PUN_LOOP STRIP 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 1		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK			
				TASK_PLAY_ANIM this_ped PUN_HOLLER STRIP 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 2			
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine after how many seconds he will move to the next state
			GENERATE_RANDOM_INT_IN_RANGE 2000 5000 iTemp
			TASK_PLAY_ANIM this_ped PUN_LOOP STRIP 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 3
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he throws money or not
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp >= 50
				TASK_PLAY_ANIM this_ped PUN_CASH STRIP 4.0 FALSE FALSE FALSE FALSE -1
				GET_CHAR_COORDINATES this_ped fX fY fZ
				GET_USER_OF_CLOSEST_MAP_ATTRACTOR fX fY fZ 15.0 0 STRIPW iStripper 		
				IF NOT IS_CHAR_DEAD iStripper
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS iStripper 0.3 0.5 -0.5 fX fY fZ 
					CREATE_MONEY_PICKUP fX fY fZ 100 FALSE iTempMoney
				ENDIF
			ELSE
				TASK_PLAY_ANIM this_ped PUN_HOLLER STRIP 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF		
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			iSubStateStatus = 0
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
STRIPM_CleanUpBrain:
	REMOVE_ANIMATION STRIP
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
STRIPM_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB STRIPM_CleanUpBrain
	ENDIF	

GOTO  STRIPM_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
STRIPM_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END