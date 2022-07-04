MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************   		BOUNCER 	  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
//BOUNCER_Brain:
    SCRIPT_NAME BOUNCER

    LVAR_INT this_ped iSpawnedAtAttractor									// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsFighting iIdleTime 			// State Machine variables
	LVAR_FLOAT fTemp 	
	VAR_INT iBouncerDM															// Other variables

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	WHILE NOT HAS_MODEL_LOADED COLT45
		REQUEST_MODEL COLT45
	ENDWHILE
	 
		//--- Misc Setups that require an 'alive' check
	IF NOT IS_CHAR_DEAD this_ped 
		GIVE_WEAPON_TO_CHAR this_ped WEAPONTYPE_PISTOL 99999999
		SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_UNARMED
		IF HAS_CHAR_GOT_WEAPON this_ped WEAPONTYPE_NIGHTSTICK
			REMOVE_WEAPON_FROM_CHAR this_ped WEAPONTYPE_NIGHTSTICK
		ENDIF  
		SET_CHAR_ACCURACY this_ped 80
		//--- Set up the Decision Maker				
		COPY_SHARED_CHAR_DECISION_MAKER  DM_PED_COP iBouncerDM
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iBouncerDM EVENT_ATTRACTOR					
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iBouncerDM EVENT_SCRIPTED_ATTRACTOR
		SET_CHAR_DECISION_MAKER this_ped iBouncerDM
	ENDIF

	//--- Init the vars			
	iPedState = 0 
	iSubStateStatus = 0

	//---MAIN LOOP---
BOUNCER_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
	//GOSUB BOUNCER_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive 
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF iBouncerAction = 0	//--- If there are global flags to check add them here
			GOSUB BOUNCER_State_Machine					
		ELSE
			//---Somebody has set a global flag to true			
			GOTO BOUNCER_GlobalFlagIntercept 
		ENDIF		
	ELSE
		GOSUB BOUNCER_CleanUpBrain
	ENDIF

GOTO BOUNCER_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
BOUNCER_State_Machine:
	SWITCH iPedState	   	
   		CASE 0 //---STATE 0: IDLE
			GOSUB BOUNCER_PedState0
		BREAK
	ENDSWITCH

RETURN
/********************************************
				IDLE STATE
********************************************/
BOUNCER_PedState0:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Entry of IDLE
		GET_SCRIPT_TASK_STATUS this_ped TASK_WANDER_STANDARD iTemp			
		IF iTemp = FINISHED_TASK
			TASK_WANDER_STANDARD this_ped 
			//--- Reset the internal variables used to leave this state
			TIMERB = 0
			iIdleTime = 5000
			++iSubStateStatus		
		ENDIF		
	BREAK

	CASE 1		
		//--- Choose the next IDLE action
		IF TIMERB >= iIdleTime
			GENERATE_RANDOM_INT_IN_RANGE 1 10 iTemp
			IF iTemp > 5 
	   			GET_SCRIPT_TASK_STATUS this_ped TASK_LOOK_ABOUT iTemp			
				IF iTemp = FINISHED_TASK
					TASK_LOOK_ABOUT this_ped -1
					iIdleTime = 5000
				ENDIF
			ELSE
	   			GET_SCRIPT_TASK_STATUS this_ped TASK_WANDER_STANDARD iTemp			
				IF iTemp = FINISHED_TASK
					TASK_WANDER_STANDARD this_ped 
					iIdleTime = 5000
				ENDIF
			ENDIF
		   	TIMERB = 0			
			++iSubStateStatus 			
		ENDIF
	BREAK

   	CASE 2	 
	 	IF TIMERB >= iIdleTime											
		 	//--- Choose another IDLE action
		 	iSubStateStatus=1		
	 	ENDIF								 
	BREAK
		
	ENDSWITCH

RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
BOUNCER_GlobalFlagIntercept:

	WAIT 0 

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_SHOOTING scplayer
			SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_PISTOL 	
			GET_SCRIPT_TASK_STATUS this_ped TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING iIsFighting			
			IF iIsFighting = FINISHED_TASK				 	
				TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING this_ped scplayer DUCK_WHEN_PLAYER_SHOOTING 4000 60
			ENDIF			 
		ELSE
			SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_PISTOL
			GET_SCRIPT_TASK_STATUS this_ped TASK_KILL_CHAR_ON_FOOT iIsFighting			
			IF iIsFighting = FINISHED_TASK
				TASK_KILL_CHAR_ON_FOOT this_ped scplayer
			ENDIF			 
		ENDIF
	ELSE 
		GOSUB BOUNCER_CleanUpBrain
	ENDIF

GOTO  BOUNCER_GlobalFlagIntercept
/*******************************************
			CLEAN UP BRAIN
********************************************/
BOUNCER_CleanUpBrain:
	REMOVE_DECISION_MAKER iBouncerDM
	MARK_CHAR_AS_NO_LONGER_NEEDED this_ped
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
BOUNCER_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN
}
MISSION_END