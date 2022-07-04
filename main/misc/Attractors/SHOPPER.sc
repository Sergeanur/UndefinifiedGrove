MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************  	SHOPPER  BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
SHOPPER_Brain:			 

    SCRIPT_NAME SHOPPER

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering iRand 	// State Machine variables
	LVAR_FLOAT fX fY fZ 	 					// Other variables

    iTemp = 0
	
    IF iTemp = 1
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

	//---MAIN LOOP---
SHOPPER_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB SHOPPER_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
			OR IS_CHAR_SHOOTING scplayer  			
				iSetClothesPanic = 1
				SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
				GOTO SHOPPER_GlobalFlagIntercept 
			ELSE
				IF iSetClothesPanic = 1
					SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM 
					GOTO SHOPPER_GlobalFlagIntercept 
				ELSE
					GOSUB SHOPPER_State_Machine					
				ENDIF 
			ENDIF		
		ELSE
			GOSUB SHOPPER_CleanUpBrain
		ENDIF
	ELSE
		GOSUB SHOPPER_CleanUpBrain
	ENDIF

GOTO SHOPPER_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
SHOPPER_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 
			GOSUB SHOPPER_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1
********************************************/
SHOPPER_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			TASK_PLAY_ANIM this_ped shop_in INT_SHOP 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1
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
			//--- Determine after how many seconds he will move to the next state			
			TASK_PLAY_ANIM this_ped shop_loop INT_SHOP 4.0 TRUE FALSE FALSE TRUE iTemp
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 2		
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he picks A or B
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped shop_lookA INT_SHOP 4.0 FALSE FALSE FALSE FALSE -1		
			ELSE
				TASK_PLAY_ANIM this_ped shop_lookB INT_SHOP 4.0 FALSE FALSE FALSE FALSE -1						
			ENDIF
			++iSubStateStatus			
		ENDIF
	BREAK

	CASE 3
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			//--- Determine if he looks again or leaves
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped shop_out INT_SHOP 4.0 FALSE FALSE FALSE FALSE -1
				++iSubStateStatus		
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 3000 6000 iTemp
				TASK_PLAY_ANIM this_ped shop_loop INT_SHOP 4.0 TRUE FALSE FALSE TRUE iTemp						
				iSubStateStatus = 1
			ENDIF
		ENDIF
	BREAK

	CASE 4
		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
		IF iTemp = FINISHED_TASK
			GOSUB SHOPPER_CleanUpBrain	
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
SHOPPER_CleanUpBrain:
	REMOVE_ANIMATION INT_SHOP
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
SHOPPER_GlobalFlagIntercept:

	WAIT 0 

	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0			
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB SHOPPER_CleanUpBrain
	ENDIF	

GOTO  SHOPPER_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
SHOPPER_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END