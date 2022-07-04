MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************	COP  SIT  BRAIN	 	**********************************************************************
************************************************						**********************************************************************
*********************************************************************************************************************************************/
{
COPSIT_Brain:

    SCRIPT_NAME COPSIT

    LVAR_INT this_ped iSpawnedAtAttractor    // parameters arriving from caller
    LVAR_INT iTemp iIsPlayingAnims iPedState iSubStateStatus iSeatedLoopTime iIsKilling iRand
	LVAR_FLOAT fAnim_Time foffX foffY foffZ	foriginalX foriginalY foriginalZ fMinX fMinY fMinZ fMaxX fMaxY fMaxZ
	VAR_INT iCopDM

    iTemp = 0
	
    IF iTemp = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Requests

	REQUEST_ANIMATION INT_OFFICE	
	WHILE NOT HAS_ANIMATION_LOADED INT_OFFICE 
		WAIT 0 
	ENDWHILE

	//--- Init the vars	
	fAnim_Time = 0.0
	iIsPlayingAnims = 0

	IF iSpawnedAtAttractor = 0
		iSubStateStatus = 0
		iPedState = 1 
	ELSE
		iSubStateStatus  = -2
		iPedState = 2
	ENDIF
			
	iIsKilling = 0

	IF NOT IS_CHAR_DEAD this_ped 
		SET_CHAR_SUFFERS_CRITICAL_HITS this_ped FALSE
		COPY_SHARED_CHAR_DECISION_MAKER  DM_PED_COP iCopDM
		SET_CHAR_DECISION_MAKER this_ped iCopDM
		iSeatedLoopTime = 15000
	ENDIF

	//---MAIN LOOP---
COPSIT_Loop:

	WAIT 0
	
	//GOSUB COPSIT_Run_Debug

	// Do all the checks and then run the state machine
	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
				GOTO COPSIT_Panic
			ELSE
				GOSUB COPSIT_State_Machine
			ENDIF
		ELSE
			GOSUB COPSIT_CleanUpBrain
		ENDIF		
	ELSE
		GOSUB COPSIT_CleanUpBrain	
	ENDIF

GOTO COPSIT_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
COPSIT_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: Sit Down
			GOSUB COPSIT_PedState1
		BREAK
		CASE 2	//---STATE 2: Consume food looped
			GOSUB COPSIT_PedState2
		BREAK
		CASE 3 //---STATE 3: Stand up 
			GOSUB COPSIT_PedState3	
		BREAK
		CASE 4 //---STATE 4: WRAP IT UP
			GOSUB COPSIT_CleanUpBrain
		BREAK		
		CASE 5 //---STATE 5: DEATH SEATED
			GOSUB COPSIT_PedState5
		BREAK	
	ENDSWITCH

RETURN
/********************************************
				SIT DOWN
********************************************/
COPSIT_PedState1:  
	SWITCH iSubStateStatus	 				

   	CASE 0   	      		
   		TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_In INT_OFFICE 4.0 FALSE FALSE FALSE TRUE -1
		iIsPlayingAnims = 1 //Flag the ped as an anim player so he wont get aborted
		++iSubStateStatus
	BREAK
	
	CASE 1
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped OFF_Sit_In
			GET_CHAR_ANIM_CURRENT_TIME this_ped	OFF_Sit_In fAnim_Time			
		ENDIF

		IF fAnim_Time = 1.0 	
			//---State completed
			iSubStateStatus = 0
			++iPedState
		ENDIF 						
	BREAK
	ENDSWITCH

RETURN
/*******************************************
				SIT LOOP
*******************************************/
COPSIT_PedState2: 
	SWITCH iSubStateStatus				

		CASE -2 // This state is only used when SPAWNED AT ATTRACTOR
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iRand 
			IF iRand > 66
				IF iRand > 33 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Type_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000			
				ELSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Bored_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
				ENDIF		  	
			ELSE
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Idle_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
			ENDIF
			iSubStateStatus = 1
		BREAK

		CASE 0 // STATE ENTRY here
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iRand 
			IF iRand > 66
				IF iRand > 33 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Type_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000			
				ELSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Bored_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
				ENDIF		  	
			ELSE
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Idle_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
			ENDIF
			++iSubStateStatus
		BREAK

		CASE 1	

			GET_CHAR_COORDINATES this_ped foriginalX foriginalY foriginalZ
		    foriginalZ-=1.04 
		    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.5 -0.5 0.0 fMinX fMinY fMinZ
		    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped -0.5 -1.0 0.0 fMaxX fMaxY fMaxZ
			//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 1.0 -0.65 foffX foffY foffZ	  		
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 1.0 -1.0 foffX foffY foffZ	  					
			
			SET_CHAR_USES_COLLISION_CLOSEST_OBJECT_OF_TYPE foriginalX foriginalY foriginalZ 1.5 CJ_PIZZA_CHAIR FALSE this_ped

			IF IS_CHAR_PLAYING_ANIM this_ped OFF_Sit_Type_Loop
			OR IS_CHAR_PLAYING_ANIM this_ped OFF_Sit_Bored_Loop			
			OR IS_CHAR_PLAYING_ANIM this_ped OFF_Sit_Idle_Loop
				SET_CHAR_COORDINATES this_ped foffX	foffY foffZ
				FREEZE_CHAR_POSITION this_ped TRUE
				TIMERB = 0
				++iSubStateStatus
			ENDIF

		BREAK

		CASE 2	
			//-- Check if ped is damaged
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
				CLEAR_CHAR_LAST_DAMAGE_ENTITY this_ped				
				//--- Move to the Damage State
				iPedState = 5
				iSubStateStatus = 0	
				BREAK					
			ENDIF
			//--- Check if it is time 
			IF TIMERB > iSeatedLoopTime				
				++iSubStateStatus 
			ENDIF 		 
		BREAK

		CASE 3
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iRand 
			IF iRand > 66
				IF iRand > 33 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Type_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000			
				ELSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Bored_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
				ENDIF		  	
			ELSE
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_Idle_Loop INT_OFFICE 1000.0 TRUE FALSE FALSE FALSE 50000000
			ENDIF
			TIMERB = 0
			--iSubStateStatus
		BREAK

	ENDSWITCH
RETURN
/*******************************************
				STAND UP
*******************************************/
COPSIT_PedState3: 
	SWITCH iSubStateStatus				

		CASE 0			
			TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped OFF_Sit_2Idle_180 INT_OFFICE 99.0 FALSE FALSE FALSE FALSE -1				
			++iSubStateStatus
		BREAK 

		CASE 1
			SET_CHAR_COORDINATES this_ped foriginalX foriginalY foriginalZ
			FREEZE_CHAR_POSITION this_ped FALSE
			++iSubStateStatus
		BREAK

		CASE 2
			fAnim_Time = 0.0
			IF IS_CHAR_PLAYING_ANIM this_ped OFF_Sit_2Idle_180
				GET_CHAR_ANIM_CURRENT_TIME this_ped	OFF_Sit_2Idle_180 fAnim_Time
			   	IF fAnim_Time = 1.0					
					SET_CHAR_USES_COLLISION_CLOSEST_OBJECT_OF_TYPE foriginalX foriginalY foriginalZ 1.5 CJ_PIZZA_CHAIR TRUE this_ped
					//---State completed
					iSubStateStatus = 0
					++iPedState		   					
				ENDIF
			ENDIF					
		BREAK
		
	ENDSWITCH				
RETURN
/*******************************************
		DAMAGE WHILE SEATED
*******************************************/
COPSIT_PedState5: 
	SWITCH iSubStateStatus				

		CASE 0
			iPedState = 3
			iSubStateStatus = 0
			GOSUB COPSIT_Panic		
		BREAK 

	ENDSWITCH				
RETURN
/*******************************************
			CLEAN UP BRAIN
*******************************************/
COPSIT_CleanUpBrain:
	REMOVE_ANIMATION INT_OFFICE	
	REMOVE_DECISION_MAKER iCopDM 
	IF NOT IS_CHAR_DEAD this_ped		
		FREEZE_CHAR_POSITION this_ped FALSE
		SET_CHAR_SUFFERS_CRITICAL_HITS this_ped TRUE
	ENDIF
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
			PIZZA PANIC
*******************************************/ 
COPSIT_Panic:
	WAIT 0
	//--- This replaces the main loop with a cowering behaviour
	IF NOT IS_STRING_EMPTY $shop_name
	AND iTerminateAllAmbience = 0
		IF NOT IS_CHAR_DEAD this_ped
		AND IS_PLAYER_PLAYING PLAYER1
		 
			SWITCH iPedState		  
			
			CASE 1
				IF iSubStateStatus > 1 // anim has not started playing yet			
					GOSUB COPSIT_State_Machine				
				ELSE
					GET_SCRIPT_TASK_STATUS this_ped	TASK_KILL_CHAR_ON_FOOT iIsKilling
					IF iIsKilling = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT this_ped scplayer
						GOSUB COPSIT_CleanUpBrain
					ENDIF			 				
				ENDIF
			BREAK
			
			CASE 2
				IF iSubStateStatus =2
					GENERATE_RANDOM_INT_IN_RANGE 0 5 iSeatedLoopTime
					iSeatedLoopTime *= 500 
				ENDIF
				GOSUB COPSIT_State_Machine
			BREAK

			CASE 3
				GOSUB COPSIT_State_Machine
			BREAK

			CASE 4
				GET_SCRIPT_TASK_STATUS this_ped	TASK_KILL_CHAR_ON_FOOT iIsKilling
				IF iIsKilling = FINISHED_TASK
					TASK_KILL_CHAR_ON_FOOT this_ped scplayer
					GOSUB COPSIT_CleanUpBrain
				ENDIF			 				
			BREAK

			CASE 5
				GOSUB COPSIT_State_Machine
			BREAK
		
			DEFAULT
				GOSUB COPSIT_CleanUpBrain
			BREAK

	   	  	ENDSWITCH		  
		ENDIF		
	ELSE 
		GOSUB COPSIT_CleanUpBrain
	ENDIF		

GOTO  COPSIT_Panic
/*******************************************/

/*******************************************/
COPSIT_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT TIMERB		TIMERB
	WRITE_DEBUG_WITH_INT TIMERA	TIMERA
RETURN
}
MISSION_END