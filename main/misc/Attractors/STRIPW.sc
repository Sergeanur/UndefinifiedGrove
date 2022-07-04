MISSION_START

/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************   	   STRIPW BRAIN  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
STRIPW_Brain:			 

    SCRIPT_NAME STRIPW

    LVAR_INT this_ped iSpawnedAtAttractor		 						// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsCowering  		 	// State Machine variables
	LVAR_FLOAT fX fY fZ fH fSizeOfLocate  
	LVAR_INT iAskedToPrintHelp    

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION STRIP	
	WHILE NOT HAS_ANIMATION_LOADED STRIP
		WAIT 0 
		REQUEST_ANIMATION STRIP
	ENDWHILE
	
	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0
	//--- Determine the size of the locate according to the strip club
	IF IS_PLAYER_PLAYING PLAYER1
		GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtString
		IF $txtString = &STRIP2
			fSizeOfLocate = 3.0
		ELSE
			fSizeOfLocate = 4.0
		ENDIF
	ENDIF
	//---MAIN LOOP---
STRIPW_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB STRIPW_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped			
			IF iSetSTRIPPanic = 0	//--- If there are global flags to check add them here
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
					iSetSTRIPPanic = 1
					GOTO STRIPW_GlobalFlagIntercept
				ELSE 
					GOSUB STRIPW_State_Machine					
				ENDIF
			ELSE
				//---Somebody has set a global flag to true				
				GOTO STRIPW_GlobalFlagIntercept 
			ENDIF		
		ELSE			
			iBouncerAction = 1 // call in the bonucers if she is pushed away
			GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
			IF iIsCowering = FINISHED_TASK
				TASK_DUCK this_ped -1
			ENDIF			 
		ENDIF
	ELSE
		GOSUB STRIPW_CleanUpBrain
	ENDIF

GOTO STRIPW_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
STRIPW_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: 			
			GOSUB STRIPW_PedState1
		BREAK
	ENDSWITCH
RETURN
/********************************************
				STATE 1
********************************************/
STRIPW_PedState1:  
	SWITCH iSubStateStatus	 				
	 
	CASE 0
		//--- Entry of LOOP A 		
		IF IS_CHAR_PLAYING_ANIM this_ped STR_B2A
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_B2A fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_A STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ELSE  			
			//--- Not playing anim
			TASK_PLAY_ANIM this_ped STR_LOOP_A STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 1
		//--- Keep Looping A or trigger transition A2B if there is money
		GOSUB STRIPW_PlayerThrowMoney		
		IF IS_CHAR_PLAYING_ANIM this_ped STR_LOOP_A 
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_LOOP_A fH
			IF fH >= 1.0
				//--- Anim finished
				//--- Check for PLAYER money 
				IF DOES_OBJECT_EXIST iPlayerMoney
				AND iStripperPed = this_ped									
					TASK_PLAY_ANIM this_ped STR_A2B STRIP 1000.0 FALSE FALSE FALSE TRUE -1
					++iSubStateStatus // Transition to B
				ELSE
					//--- Check for CUSTOMER money
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ	
					IF IS_MONEY_PICKUP_AT_COORDS fX fY fZ
						//--- Pick up the money if there is any				
						CLEAR_AREA fX fY fZ 0.3 FALSE 				
						TASK_PLAY_ANIM this_ped STR_A2B STRIP 1000.0 FALSE FALSE FALSE TRUE -1
						++iSubStateStatus // Transition to B
					ELSE
						//--- Keep Loop A
						TASK_PLAY_ANIM this_ped STR_LOOP_A STRIP 1000.0 FALSE FALSE FALSE TRUE -1					
				   ENDIF	
				ENDIF
			ENDIF
		ELSE  			
			//--- Not playing any anim
			TASK_PLAY_ANIM this_ped STR_LOOP_A STRIP 1000.0 FALSE FALSE FALSE TRUE -1					   
		ENDIF
	BREAK

	CASE 2
		//--- Entry of Loop B		
		IF IS_CHAR_PLAYING_ANIM this_ped STR_A2B
			DELETE_OBJECT iPlayerMoney
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ
			CLEAR_AREA fX fY fZ 0.3 FALSE

			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_A2B fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_B STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ELSE  			
			IF IS_CHAR_PLAYING_ANIM this_ped STR_C2B
				GET_CHAR_ANIM_CURRENT_TIME this_ped STR_C2B fH
				IF fH >= 1.0
					//--- Anim finished
					TASK_PLAY_ANIM this_ped STR_LOOP_B STRIP 1000.0 FALSE FALSE FALSE TRUE -1
					++iSubStateStatus
				ENDIF
			ELSE
				//--- Not playing anim
				TASK_PLAY_ANIM this_ped STR_LOOP_B STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 3
		//--- Reach the end of Loop B & go back to Loop A, or trigger transition B2C if there is money
		GOSUB STRIPW_PlayerThrowMoney
		IF IS_CHAR_PLAYING_ANIM this_ped STR_LOOP_B 
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_LOOP_B fH
			IF fH >= 1.0
				//--- Anim finished
				//--- Check for PLAYER money 
				IF DOES_OBJECT_EXIST iPlayerMoney									
				AND iStripperPed = this_ped
					TASK_PLAY_ANIM this_ped STR_B2C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
					++iSubStateStatus // Transition to C
				ELSE
					//--- Check for CUSTOMER money
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ	
					IF IS_MONEY_PICKUP_AT_COORDS fX fY fZ
						//--- Pick up the money if there is any				
						CLEAR_AREA fX fY fZ 0.3 FALSE 				
						TASK_PLAY_ANIM this_ped STR_B2C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
						++iSubStateStatus // Transition to B
					ELSE
						//--- Transition back to Loop A
						TASK_PLAY_ANIM this_ped STR_B2A STRIP 1000.0 FALSE FALSE FALSE TRUE -1
						iSubStateStatus = 0					
				   ENDIF	
				ENDIF
			ENDIF
		ELSE  			
			//--- Not playing any anim
			TASK_PLAY_ANIM this_ped STR_LOOP_B STRIP 1000.0 FALSE FALSE FALSE TRUE -1					   
		ENDIF
	BREAK

	CASE 4
		//--- Entry of Loop C 		
		IF IS_CHAR_PLAYING_ANIM this_ped STR_B2C
			DELETE_OBJECT iPlayerMoney
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ
			CLEAR_AREA fX fY fZ 0.3 FALSE

			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_B2C fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ELSE  			
			//--- Not playing anim
			TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		//--- Reach the end of Loop C and go back to Loop B, or trigger unique anims if there is PLAYER money
		GOSUB STRIPW_PlayerThrowMoney
		IF IS_CHAR_PLAYING_ANIM this_ped STR_LOOP_C 
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_LOOP_C fH
			IF fH >= 1.0
				//--- Anim finished
				//--- Check for PLAYER money 
				IF DOES_OBJECT_EXIST iPlayerMoney					
				AND iStripperPed = this_ped
					GET_CHAR_COORDINATES this_ped fX fY fZ 
					GET_CHAR_HEADING scplayer fH
					fH += 180.0
					GENERATE_RANDOM_INT_IN_RANGE 35 100 iTemp
					IF iTemp < 33
						TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM this_ped fX fY fZ fH -1.0 STR_C3 STRIP 1000.0 FALSE FALSE FALSE TRUE -1
						iSubStateStatus = 6 // Unique move for player
					ELSE
						IF iTemp > 66
							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM this_ped fX fY fZ fH -1.0 STR_C2 STRIP 1000.0 FALSE FALSE FALSE TRUE -1
							iSubStateStatus = 7 // Unique move for player
						ELSE
							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM this_ped fX fY fZ fH -1.0 STR_C1 STRIP 1000.0 FALSE FALSE FALSE TRUE -1
							iSubStateStatus = 8 // Unique move for player
						ENDIF
					ENDIF					
				ELSE
					//--- Check for CUSTOMER money
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ	
					IF IS_MONEY_PICKUP_AT_COORDS fX fY fZ
						//--- Pick up the money if there is any				
						CLEAR_AREA fX fY fZ 0.3 FALSE 				
						//--- Keep Loop C
						TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
					ELSE
						//--- Transition back to Loop B
						TASK_PLAY_ANIM this_ped STR_C2B STRIP 1000.0 FALSE FALSE FALSE TRUE -1
						iSubStateStatus = 2					
				   ENDIF	
				ENDIF
			ENDIF
		ELSE  			
			//--- Not playing any anim
			TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1					   
		ENDIF
	BREAK															

	CASE 6
		//--- Complete unique move NUMBER 1 for player, then Loop C
		IF IS_CHAR_PLAYING_ANIM this_ped STR_C3
			DELETE_OBJECT iPlayerMoney
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ
			CLEAR_AREA fX fY fZ 0.3 FALSE
			
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_C3 fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				iSubStateStatus = 5
			ENDIF
		ELSE  			
			//--- Not playing anim
			TASK_PLAY_ANIM this_ped STR_C3 STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
		ENDIF
	BREAK

	CASE 7
		//--- Complete unique move NYMBER 2 for player, then Loop C
		IF IS_CHAR_PLAYING_ANIM this_ped STR_C2
			DELETE_OBJECT iPlayerMoney
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ
			CLEAR_AREA fX fY fZ 0.3 FALSE
			
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_C2 fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				iSubStateStatus = 5
			ENDIF
		ELSE  			
			//--- Not playing anim
			TASK_PLAY_ANIM this_ped STR_C2 STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
		ENDIF
	BREAK

	CASE 8
		//--- Complete unique move NYMBER 3 for player, then Loop C
		IF IS_CHAR_PLAYING_ANIM this_ped STR_C1
			DELETE_OBJECT iPlayerMoney
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.3 0.5 -0.5 fX fY fZ
			
			CLEAR_AREA fX fY fZ 0.3 FALSE
			GET_CHAR_ANIM_CURRENT_TIME this_ped STR_C1 fH
			IF fH >= 1.0
				//--- Anim finished
				TASK_PLAY_ANIM this_ped STR_LOOP_C STRIP 1000.0 FALSE FALSE FALSE TRUE -1
				iSubStateStatus = 5
			ENDIF
		ELSE  			
			//--- Not playing anim
			TASK_PLAY_ANIM this_ped STR_C1 STRIP 1000.0 FALSE FALSE FALSE TRUE -1			
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
			PLAYER THROW MONEY
********************************************/
STRIPW_PlayerThrowMoney:

 	IF IS_CHAR_STOPPED scplayer
	AND IS_SCORE_GREATER player1 19
		IF NOT DOES_OBJECT_EXIST iPlayerMoney
			IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer this_ped fSizeOfLocate fSizeOfLocate 1.0 FALSE									
			AND iThrowMoneyState = STRIP_NO_PLAYER_MONEY
				IF iAskedToPrintHelp = 0
					++iPrintStripperHelp
					iAskedToPrintHelp = 1
				ENDIF				
				 			
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				AND iThrowMoneyState = STRIP_NO_PLAYER_MONEY 
					iStripperPed = this_ped
					iThrowMoneyState = STRIP_INIT_MONEY_THROW 
				ENDIF			
			ELSE
				IF iAskedToPrintHelp = 1 
					--iPrintStripperHelp
					iAskedToPrintHelp = 0
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF iAskedToPrintHelp = 1 
			--iPrintStripperHelp
			iAskedToPrintHelp = 0
		ENDIF
	ENDIF
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
STRIPW_CleanUpBrain:
	REMOVE_ANIMATION STRIP
	
	DELETE_OBJECT iPlayerMoney   	

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
STRIPW_GlobalFlagIntercept:

	WAIT 0 
	
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
	AND iTerminateAllAmbience = 0
		GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
		IF iIsCowering = FINISHED_TASK
			TASK_DUCK this_ped -2
		ENDIF			 
	ELSE 
		GOSUB STRIPW_CleanUpBrain
	ENDIF	

GOTO  STRIPW_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
STRIPW_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END



