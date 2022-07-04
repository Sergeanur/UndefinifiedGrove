MISSION_START

CONST_INT DEALER_HATES_PLAYER		1
CONST_INT DEALER_DISLIKES_PLAYER	2
// Global dealer zone 
VAR_TEXT_LABEL txtGlobalDealerZoneName

/*****************************************************************************************************************************************
************************************************	DEALER BRAIN		******************************************************************
*****************************************************************************************************************************************/
{

Dealer_Brain:

    SCRIPT_NAME DEALER

    LVAR_INT this_ped    											// Parameters arriving from caller
    LVAR_INT iPedState iSubStateStatus 			 					// State Machine variables
	LVAR_FLOAT fDealerX fDealerY fDealerZ fPedX fPedY fPedZ ftemp ftemp2 
	LVAR_INT iOther_Ped iOther_Ped_Car iTemp iIdleTime iHatesAndDislikes iDealerStrength 
	LVAR_TEXT_LABEL txtThisDealerZoneName
	VAR_INT iMyDM	

    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_DEALER 0 0.0 0.0 0.0 this_ped
    ENDIF

	//--- Init the vars			
	iPedState = 0 
	iSubStateStatus = 0
	
	iOther_Ped = -1	
	iIdleTime = 0	// Timer for idle tasks set in the idle state 0
	
	//--- Misc Setups that require an 'alive' check
	IF NOT IS_CHAR_DEAD this_ped
	AND NOT IS_CHAR_DEAD scplayer  
		//--- Grab the zone for this ped
		GET_CHAR_COORDINATES this_ped fDealerX fDealerY fDealerZ 
		GET_NAME_OF_INFO_ZONE fDealerX fDealerY fDealerZ txtGlobalDealerZoneName
		$txtThisDealerZoneName = $txtGlobalDealerZoneName		 
		//--- Set up the Decision Maker
		COPY_SHARED_CHAR_DECISION_MAKER  DM_PED_RANDOM_TOUGH iMyDM
		SET_CHAR_DECISION_MAKER this_ped iMyDM
		SET_CHAR_MONEY this_ped 2000
		//--- Init the PLAYER CONVERSATION
		START_SETTING_UP_CONVERSATION this_ped
		SET_UP_CONVERSATION_NODE_WITH_SPEECH DEAL1 DEAL2 DEAL3 CONTEXT_GLOBAL_SOLICIT CONTEXT_GLOBAL_DRUG_DEALER_DISLIKE CONTEXT_GLOBAL_DRUG_DEALER_HATE
		GET_INT_STAT RESPECT_TOTAL iTemp   
		IF iTemp >= 500 //player has a high respect
			SET_UP_CONVERSATION_END_NODE_WITH_SPEECH DEAL2 CONTEXT_GLOBAL_DRUG_REASONABLE_HIGH 
			SET_UP_CONVERSATION_END_NODE_WITH_SPEECH DEAL3 CONTEXT_GLOBAL_DRUG_AGGRESSIVE_HIGH 
		ELSE //player has a low respect
			SET_UP_CONVERSATION_END_NODE_WITH_SPEECH DEAL2 CONTEXT_GLOBAL_DRUG_REASONABLE_LOW
			SET_UP_CONVERSATION_END_NODE_WITH_SPEECH DEAL3 CONTEXT_GLOBAL_DRUG_AGGRESSIVE_LOW 
		ENDIF
		GET_CURRENT_LANGUAGE iTemp
		IF iTemp = LANGUAGE_ENGLISH 
			FINISH_SETTING_UP_CONVERSATION_NO_SUBTITLES
		ELSE
			FINISH_SETTING_UP_CONVERSATION
		ENDIF
	ELSE
		GOSUB Dealer_CleanUpBrain
	ENDIF 

	//---MAIN LOOP---
Dealer_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
// 	GOSUB Dealer_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped // Always check if he is dead or alive				
		IF NOT IS_CHAR_DEAD scplayer
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
			OR IS_PLAYER_TARGETTING_CHAR player1 this_ped				
				GOSUB Dealer_ReleaseCustomer
				GOSUB Dealer_HatePlayer				
			ELSE  
				GOSUB Dealer_InventoryCheck // Tries to maintain an inventory for this ped without requesting models
				GOSUB Dealer_CheckCustomer //Returns iOther_Ped > -1 if it is a valid living ped, stores his car as well 		
				GOSUB Dealer_State_Machine //Handles all the states and transitions
			ENDIF
		ELSE
			GOSUB Dealer_CleanUpBrain
		ENDIF
	ELSE
		GOSUB Dealer_CleanUpBrain					
	ENDIF

GOTO Dealer_Main_Loop 
	//---END OF MAIN LOOP---

/*******************************************
			CHECK CUSTOMER
********************************************/
Dealer_CheckCustomer:

	IF iOther_Ped = -1
		IF TIMERA > 15000 //Time to check for a ped to interact with 
			GET_CHAR_COORDINATES this_ped fDealerX fDealerY fDealerZ
 			//---Look for a junkie
  			GET_RANDOM_CHAR_IN_SPHERE_ONLY_DRUGS_BUYERS fDealerX fDealerY fDealerZ 5.0 iOther_Ped
  			IF NOT iOther_Ped = -1 	
				IF IS_CHAR_IN_ANY_CAR iOther_Ped
					STORE_CAR_CHAR_IS_IN_NO_SAVE iOther_Ped iOther_Ped_Car
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF IS_CHAR_DEAD iOther_Ped
		OR NOT DOES_CHAR_EXIST iOther_Ped
		OR IS_CHAR_RESPONDING_TO_EVENT this_ped EVENT_ACQUAINTANCE_PED_HATE
		  iOther_Ped = -1 //Mark this ped as invalid		  	
		ENDIF
	ENDIF

RETURN
/*******************************************
			RELEASE CUSTOMER
********************************************/
Dealer_ReleaseCustomer:

	 	MARK_CHAR_AS_NO_LONGER_NEEDED iOther_Ped

		IF NOT IS_CHAR_DEAD iOther_Ped
		 	IF NOT IS_CAR_DEAD iOther_Ped_Car  // Check if it exists first!
				IF NOT IS_CAR_ON_FIRE iOther_Ped_Car
					TASK_CAR_DRIVE_WANDER iOther_Ped iOther_Ped_Car 20.0 DRIVINGMODE_AVOIDCARS
				ENDIF
			ENDIF 
		ENDIF
	 	
		TIMERA = 0 //Restart the counter to look for customers
		iOther_Ped = -1

RETURN
/*******************************************
		GENERATE SALE OUTCOME
********************************************/
Dealer_GenerateSaleOutcome:

	IF NOT IS_CHAR_DEAD this_ped
		 IF NOT IS_CHAR_DEAD iOther_Ped		
		 	GET_CHAR_COORDINATES this_ped fDealerX fDealerY fDealerZ
		 	GET_CHAR_COORDINATES iOther_Ped fPedX fPedY fPedZ
		 	GET_DISTANCE_BETWEEN_COORDS_2D fDealerX fDealerY fPedX fPedY ftemp
		 	IF ftemp > 2.0
		 		//RETURN TRUE 
		 		iTemp = 1
		 	ELSE
		 		//RETURN FALSE
		 		iTemp=0			   			   	 			
		 	ENDIF			
		 ENDIF		
	ENDIF 
RETURN
/*******************************************
		INVENTORY MEMORY CHECK 
********************************************/
Dealer_InventoryCheck:

	//--- Give the guy a shooter
	IF HAS_MODEL_LOADED COLT45 
		IF NOT HAS_CHAR_GOT_WEAPON this_ped WEAPONTYPE_PISTOL
			GIVE_WEAPON_TO_CHAR this_ped WEAPONTYPE_PISTOL 1000
			IF NOT iHatesAndDislikes = DEALER_HATES_PLAYER 
				SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_UNARMED
			ELSE
				SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_PISTOL
			ENDIF
		ENDIF
	ENDIF

RETURN
/********************************************
			STATE MACHINE
********************************************/
Dealer_State_Machine:
	
	SWITCH iPedState	   	

		CASE 0 //---STATE 0: The default IDLE state.  
			IF IS_PLAYER_IN_POSITION_FOR_CONVERSATION this_ped
				iSubStateStatus = 0
				iPedState = 3
				GOSUB Dealer_PedState3
			ELSE
				IF iOther_Ped > -1 //we are dealing with a valid ped
					//---Decide if this sale goes well or not
					GOSUB Dealer_GenerateSaleOutcome //Returns iTemp. Can be: 0 botched sale, 1 good sale
					IF iTemp = 0
						//---Botched sale
						iSubStateStatus = 0
						iPedState = 2
						GOSUB Dealer_PedState2 
					ELSE
						//---Successful sale
						iSubStateStatus = 0
						iPedState = 1
						GOSUB Dealer_PedState1 
					ENDIF		 
				ELSE
					//--- No customer yet - stay Idle
					GOSUB Dealer_PedState0
				ENDIF
			ENDIF
		BREAK

   		CASE 1 //---STATE 1: Good sale to an approaching customer 
			IF iOther_Ped > -1 //Valid ped 
				GOSUB Dealer_PedState1
			ELSE //Invalid ped - go back to idle
				iPedState=0
				iSubStateStatus=0
				GOSUB Dealer_PedState0
			ENDIF 
		BREAK

		CASE 2	//---STATE 2: Botched sale, follow a ped and peddle drugs
			IF iOther_Ped > -1 //Valid ped 
				GOSUB Dealer_PedState2
			ELSE //Invalid ped - go back to idle
				iPedState=0
				iSubStateStatus=0
				GOSUB Dealer_PedState0
			ENDIF 
		BREAK

		CASE 3	//---STATE 3: Player interaction
			GOSUB Dealer_PedState3			
		BREAK

		DEFAULT //---CLEAN UP 
			GOSUB Dealer_CleanUpBrain
		BREAK		
		
	ENDSWITCH

RETURN
/********************************************
				IDLE STATE
********************************************/
Dealer_PedState0:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Entry of IDLE
		//--- Chack if player is not in sight and hated
		IF NOT IS_CHAR_RESPONDING_TO_EVENT this_ped EVENT_ACQUAINTANCE_PED_HATE 
			ENABLE_CONVERSATION this_ped TRUE
			//--- Play intro default idle anim
			GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
			IF iTemp = FINISHED_TASK
				TASK_PLAY_ANIM this_ped DEALER_IDLE DEALER 4.0 TRUE FALSE FALSE FALSE -1
				//--- Reset the internal variables used to leave this state
				TIMERB = 0
				iIdleTime = 3000
				++iSubStateStatus		
			ENDIF		
		ENDIF	 	
	BREAK

	CASE 1		
		//--- Choose the next IDLE action
		IF TIMERB >= iIdleTime
			SET_CHAR_WANTED_BY_POLICE this_ped FALSE // He can be arrested only if found dealing!
			GENERATE_RANDOM_INT_IN_RANGE 1 15 iTemp
			IF iTemp > 5 
		   		IF iTemp > 10
		   			GET_SCRIPT_TASK_STATUS this_ped TASK_LOOK_ABOUT iTemp			
					IF iTemp = FINISHED_TASK
						TASK_LOOK_ABOUT this_ped -1
						iIdleTime = 2000
					ENDIF
				ELSE
		   			GET_SCRIPT_TASK_STATUS this_ped TASK_WANDER_STANDARD iTemp			
					IF iTemp = FINISHED_TASK
						TASK_WANDER_STANDARD this_ped 
						iIdleTime = 1500
					ENDIF
				ENDIF
			ELSE
				GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp			
				IF iTemp = FINISHED_TASK			   	
				   	TASK_PLAY_ANIM this_ped DEALER_IDLE DEALER 4.0 TRUE FALSE FALSE FALSE -1
					iIdleTime = 3000
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
/********************************************
		SELL TO APPROACHING CUSTOMER
********************************************/
Dealer_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Disable player conversation at this stage
		ENABLE_CONVERSATION this_ped FALSE
		//--- The customer approaces the dealer		
		TASK_LOOK_ABOUT this_ped -1
		TASK_GOTO_CHAR iOther_Ped this_ped 20000 1.0
		TIMERB = 0 //Keep track of time to abort if it takes too long
		++iSubStateStatus	
	BREAK

	CASE 1			
		GET_CHAR_COORDINATES this_ped fDealerX fDealerY fDealerZ
		GET_CHAR_COORDINATES iOther_Ped fPedX fPedY fPedZ
		GET_DISTANCE_BETWEEN_COORDS_2D fDealerX fDealerY fPedX fPedY ftemp
		TASK_LOOK_AT_CHAR this_ped iOther_Ped -1

		IF ftemp <= 1.8
			//--- Customer has reached dealer
			++iSubStateStatus
		ELSE	
			IF TIMERB > 20000 
			//---ABORTED - OUT OF TIME - BACK TO IDLE
			GOSUB Dealer_ReleaseCustomer
			iSubStateStatus=0
			iPedState=0
			ENDIF
		ENDIF
				
	BREAK

   	CASE 2
  		TASK_TURN_CHAR_TO_FACE_CHAR iOther_Ped this_ped
		TASK_TURN_CHAR_TO_FACE_CHAR this_ped iOther_Ped 
		++iSubStateStatus		
	BREAK
	
	CASE 3		  
		GET_SCRIPT_TASK_STATUS iOther_Ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp
		IF iTemp = FINISHED_TASK
			
			GET_SCRIPT_TASK_STATUS iOther_Ped TASK_STAND_STILL iTemp			
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL iOther_Ped -1
			ENDIF
		ENDIF
		  
		GET_SCRIPT_TASK_STATUS this_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp			
		IF iTemp = FINISHED_TASK

			GET_SCRIPT_TASK_STATUS this_ped TASK_STAND_STILL iTemp 		
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL this_ped -1
			ENDIF

			GET_SCRIPT_TASK_STATUS iOther_Ped TASK_PLAY_ANIM iTemp			
			IF iTemp = FINISHED_TASK
				SET_CHAR_SAY_CONTEXT iOther_Ped	CONTEXT_GLOBAL_DRUGS_BUY iTemp
				TASK_PLAY_ANIM iOther_Ped shop_pay DEALER 4.0 FALSE FALSE FALSE FALSE -1
				++iSubStateStatus
			ENDIF				 			
		ENDIF 						
	BREAK													  

	CASE 4
  		GET_SCRIPT_TASK_STATUS iOther_Ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			
			CLEAR_LOOK_AT this_ped
	
	  		GET_SCRIPT_TASK_STATUS iOther_Ped TASK_PLAY_ANIM iTemp
			IF iTemp = FINISHED_TASK		 			
				TASK_PLAY_ANIM iOther_Ped DRUGS_BUY DEALER 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_WANTED_BY_POLICE iOther_Ped TRUE
			ENDIF
	
	  		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
			IF iTemp = FINISHED_TASK		 				
				SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_DRUGS_SELL iTemp
				TASK_PLAY_ANIM this_ped DEALER_DEAL DEALER 4.0 FALSE FALSE FALSE FALSE -1
				SET_CHAR_WANTED_BY_POLICE this_ped TRUE
				++iSubStateStatus						
			ENDIF										   
		ENDIF
	BREAK

	CASE 5
  		GET_SCRIPT_TASK_STATUS iOther_Ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK

			GET_SCRIPT_TASK_STATUS iOther_Ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL iOther_Ped -1
			ENDIF
		ENDIF

		GET_SCRIPT_TASK_STATUS this_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK 				
			SET_CHAR_DRUGGED_UP iOther_Ped TRUE
			//---State Completed - back to idle
			GOSUB Dealer_ReleaseCustomer								 			
			iSubStateStatus = 0
			iPedState = 0
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
	FOLLOW CUSTOMER TRYING TO SELL
********************************************/
Dealer_PedState2:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Disable player conversation at this stage
		ENABLE_CONVERSATION this_ped FALSE

		//--- Dealer starts solliciting for drugs
		GET_CHAR_COORDINATES this_ped fDealerX fDealerY fDealerZ //Store coords to return here later
		TASK_LOOK_AT_CHAR this_ped iOther_Ped -1
		TASK_FOLLOW_FOOTSTEPS this_ped iOther_Ped
		SET_CHAR_SAY_CONTEXT this_ped CONTEXT_GLOBAL_DRUGS_SELL iTemp
		TIMERB = 0
		++iSubStateStatus
	BREAK

	CASE 1			
		IF TIMERB > 6000
			//---No deal amigo, time to go back 
			CLEAR_LOOK_AT this_ped
			TASK_FOLLOW_PATH_NODES_TO_COORD this_ped fDealerX fDealerY fDealerZ PEDMOVE_WALK -2
			++iSubStateStatus
		ENDIF
	
	BREAK

   	CASE 2
		GET_SCRIPT_TASK_STATUS this_ped TASK_FOLLOW_PATH_NODES_TO_COORD iTemp
		IF iTemp = FINISHED_TASK			
			//---State Completed - back to idle
			GOSUB Dealer_ReleaseCustomer
			iSubStateStatus = 0
			iPedState = 0
		ENDIF
	BREAK	
	ENDSWITCH

RETURN
/********************************************
			PLAYER CONVERSATION
********************************************/
Dealer_PedState3:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Dealer orients to face player
		TIMERB = 0 
		TASK_TURN_CHAR_TO_FACE_CHAR this_ped scplayer
		++iSubStateStatus
	BREAK

	CASE 1
		//--- Dealer starts looking at player 
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
			IF NOT IS_CONVERSATION_AT_NODE this_ped  DEAL2
			AND NOT IS_CONVERSATION_AT_NODE this_ped DEAL3 
				PRINT_HELP TALK_1
			ENDIF
		ENDIF
		GET_SCRIPT_TASK_STATUS this_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp		
		IF iTemp = FINISHED_TASK
			TASK_LOOK_AT_CHAR this_ped scplayer	-1
			GET_SCRIPT_TASK_STATUS this_ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL this_ped -2		  				
			ENDIF
			++iSubStateStatus
		ELSE
			IF NOT IS_PLAYER_IN_POSITION_FOR_CONVERSATION this_ped
			OR TIMERB > 15000
				//--- ABORT CONVERSATION - Back to Idle				
				CLEAR_LOOK_AT this_ped
				iSubStateStatus = 0
				iPedState = 0
			ENDIF
		ENDIF
	BREAK

	CASE 2
				
		IF IS_CONVERSATION_AT_NODE this_ped  DEAL3 //Negative response: 'Fuck off man'
		AND TIMERB > 9000
			CLEAR_HELP
			GOSUB Dealer_HatePlayer
		ENDIF
		
		IF IS_CONVERSATION_AT_NODE this_ped  DEAL2 //Positive response: 'Take it easy man'
		AND TIMERB > 9000
			CLEAR_HELP
			GOSUB Dealer_DislikePlayer
		ENDIF

		IF NOT IS_PLAYER_IN_POSITION_FOR_CONVERSATION this_ped
		OR TIMERB > 15000
			//--- ABORT CONVERSATION - Back to Idle			
			CLEAR_LOOK_AT this_ped
			iSubStateStatus = 0
			iPedState = 0
		ENDIF		 
	BREAK 

	CASE 3
		 IF TIMERB > 2000
		 	//---  State Complete - Back to Idle
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE
			SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_UNARMED
			iSubStateStatus = 0
			iPedState = 0
		 ENDIF
	BREAK
	
	ENDSWITCH
RETURN
/*******************************************
		SET DEALER TO HATE PLAYER
********************************************/
Dealer_HatePlayer:
   
	CLEAR_HELP
	CLEAR_LOOK_AT this_ped
	ENABLE_CONVERSATION this_ped FALSE

	//--- Switch to guns if possible
	IF HAS_CHAR_GOT_WEAPON this_ped WEAPONTYPE_PISTOL  
		SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_PISTOL
	ENDIF

	IF NOT iHatesAndDislikes = DEALER_HATES_PLAYER 
		iHatesAndDislikes = DEALER_HATES_PLAYER 
		//--- Check player's RESPECT
		GET_INT_STAT RESPECT_TOTAL iTemp   

		IF iTemp > 500 //player has a high respect
			
			//--- This guy now hates the player
			SET_CHAR_RELATIONSHIP this_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		   	//--- Modify the Decision Maker - Dealer RUNS away while shooting if he can
		   	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_HATE					
		   	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_FLEE_ANY_MEANS 0.0 100.0 0.0 0.0 TRUE TRUE										

			//---State Completed - back to idle
			iSubStateStatus = 0
			iPedState = 0

		ELSE // Player has not enough respect

			//--- This guy now hates the player
			SET_CHAR_RELATIONSHIP this_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		   	
		   	//--- Modify the Decision Maker - Dealer KILLS player
		   	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_HATE
		   	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 TRUE TRUE										

			//---State Completed - back to idle
			iSubStateStatus = 0
			iPedState = 0
		ENDIF
	ENDIF
RETURN
/*******************************************
		SET DEALER TO DISLIKE PLAYER
********************************************/
Dealer_DislikePlayer:

	//--- Check player's RESPECT
	CLEAR_HELP
	CLEAR_LOOK_AT this_ped
	ENABLE_CONVERSATION this_ped FALSE

	GET_INT_STAT RESPECT iTemp
	IF NOT iHatesAndDislikes = DEALER_DISLIKES_PLAYER 
		iHatesAndDislikes = DEALER_DISLIKES_PLAYER

		IF iTemp > 500 //player has a high respect

			//--- This guy now hates the player
			SET_CHAR_RELATIONSHIP this_ped ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
		   	
		   	//--- Modify the Decision Maker - Dealer RUNS away (without shooting)
			SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_UNARMED
		   	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE
		   	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_FLEE_ANY_MEANS 0.0 100.0 0.0 0.0 TRUE TRUE										

			//---State Completed - back to idle
			iSubStateStatus = 0
			iPedState = 0
				   
		ELSE //Player has not enough respect

			//--- This guy now dislikes the player
			SET_CHAR_RELATIONSHIP this_ped ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1

			//--- Dealer stays here but does not fight
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE
			
			IF HAS_CHAR_GOT_WEAPON this_ped WEAPONTYPE_PISTOL			   
				SET_CURRENT_CHAR_WEAPON this_ped WEAPONTYPE_PISTOL			   
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_GUN_CTRL 0.0 100.0 0.0 0.0 FALSE TRUE										
			ELSE
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iMyDM EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_HANDS_UP 0.0 100.0 0.0 0.0 FALSE TRUE
			ENDIF

			//---Move to a short wait state before resetting all
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	ENDIF
RETURN
/*******************************************
		REDUCE DEALER STRENGTH
********************************************/
Dealer_ReduceStrength:
		GET_ZONE_DEALER_STRENGTH $txtThisDealerZoneName iDealerStrength
		IF iDealerStrength > 0
			--iDealerStrength 
		ENDIF
		SET_ZONE_DEALER_STRENGTH $txtThisDealerZoneName iDealerStrength
RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
Dealer_CleanUpBrain:

	IF iHatesAndDislikes = DEALER_HATES_PLAYER
		//--- This guy is gone, reduce the dealer strength
		GOSUB Dealer_ReduceStrength
	ENDIF

	CLEAR_CONVERSATION_FOR_CHAR this_ped

	IF iOther_Ped > -1	
		MARK_CHAR_AS_NO_LONGER_NEEDED iOther_Ped
	ENDIF

	REMOVE_DECISION_MAKER iMyDM

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
Dealer_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT TIMER_B		TIMERB
	WRITE_DEBUG_WITH_INT TIMER_A		TIMERA

RETURN

}

MISSION_END
