MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************   FF LEFT BOOT BRAIN	**********************************************************************
************************************************						**********************************************************************
*********************************************************************************************************************************************/
{
FF_Left_Booth_Brain:

    SCRIPT_NAME FBOOTHL

    LVAR_INT this_ped iSpawnedAtAttractor    // parameters arriving from caller
    LVAR_INT iTemp iIsPlayingAnims iPedState iSubStateStatus iEatAndDrinkTime iIsCowering	ifoodModel
	LVAR_FLOAT fAnim_Time foffX foffY foffZ	foriginalX foriginalY foriginalZ fMinX fMinY fMinZ fMaxX fMaxY fMaxZ

    iTemp = 0
	
    IF iTemp = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Requests
	REQUEST_ANIMATION FOOD	
	WHILE NOT HAS_ANIMATION_LOADED FOOD
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
	
	iIsCowering = 0

	IF NOT IS_CHAR_DEAD this_ped 
		SET_CHAR_SUFFERS_CRITICAL_HITS this_ped FALSE
		iEatAndDrinkTime = 1800000
	ELSE
		iEatAndDrinkTime = 0
	ENDIF

	//---MAIN LOOP---
FBOOTHL_Loop:

	WAIT 0
	
//	GOSUB FBOOTHL_Run_Debug

	// Do all the checks and then run the state machine
	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
	AND iTerminateAllAmbience = 0
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF iSetPizzaPanic = 0	
				GOSUB FBOOTHL_State_Machine					
			ELSE
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer 
					iSetPizzaPanic = 1
				ENDIF
				//---Somebody has set the pizza panic flag to true
				GOTO FBOOTHL_PizzaPanic
			ENDIF
		ELSE
			GOSUB FBOOTHL_CleanUpBrain
		ENDIF		
	ELSE
		GOSUB FBOOTHL_CleanUpBrain	
	ENDIF

GOTO FBOOTHL_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
FBOOTHL_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: Sit Down
			GOSUB FBOOTHL_PedState1
		BREAK
		CASE 2	//---STATE 2: Consume food looped
			GOSUB FBOOTHL_PedState2
		BREAK
		CASE 3 //---STATE 3: Stand up 
			GOSUB FBOOTHL_PedState3	
		BREAK
		CASE 4 //---STATE 4: WRAP IT UP
			GOSUB FBOOTHL_CleanUpBrain
		BREAK		
		CASE 5 //---STATE 5: DAMAGE
			GOSUB FBOOTHL_PedState5
		BREAK	
	ENDSWITCH

RETURN
/********************************************
				SIT DOWN
********************************************/
FBOOTHL_PedState1:  
	SWITCH iSubStateStatus	 				

   	CASE 0   	      		
   		TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_In_L FOOD 4.0 FALSE FALSE FALSE TRUE -1
		iIsPlayingAnims = 1 //Flag the ped as an anim player so he wont get aborted
		++iSubStateStatus
	BREAK
	
	CASE 1
		fAnim_Time = 0.0
		IF IS_CHAR_PLAYING_ANIM this_ped FF_Sit_In_L
			GET_CHAR_ANIM_CURRENT_TIME this_ped	FF_Sit_In_L fAnim_Time			
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
				EAT LOOP
*******************************************/
FBOOTHL_PedState2: 
	SWITCH iSubStateStatus				

		CASE -2 // This state is only used when SPAWNED AT ATTRACTOR
			IF ARE_ANY_CHARS_NEAR_CHAR this_ped 2.2
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Look FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.0 0.0 foffX foffY foffZ
				IF $shop_name = &FDPIZA
					CREATE_OBJECT CJ_PIZZA_2 foffX foffY foffZ iFoodModel
				ELSE
					CREATE_OBJECT CJ_BURG_2 foffX foffY foffZ iFoodModel
				ENDIF
				TASK_PICK_UP_OBJECT this_ped ifoodModel 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				IF IS_CHAR_MALE this_ped 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat1 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000			
				ELSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat2 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000
				ENDIF		  	
			ENDIF
			iSubStateStatus = 1
		BREAK

		CASE 0 // STATE ENTRY here
 			IF IS_CHAR_HOLDING_OBJECT this_ped -1
				IF IS_CHAR_MALE this_ped 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat1 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000			
				ELSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat2 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000
				ENDIF
			ELSE
				IF ARE_ANY_CHARS_NEAR_CHAR this_ped 2.2
					TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Look FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000
				ELSE
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.0 0.0 foffX foffY foffZ
					IF $shop_name = &FDPIZA
						CREATE_OBJECT CJ_PIZZA_2 foffX foffY foffZ iFoodModel
					ELSE
						CREATE_OBJECT CJ_BURG_2 foffX foffY foffZ iFoodModel
					ENDIF
					TASK_PICK_UP_OBJECT this_ped ifoodModel 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
					IF IS_CHAR_MALE this_ped 
						TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat1 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000			
					ELSE
						TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_Eat2 FOOD 1000.0 TRUE FALSE FALSE FALSE 50000000
					ENDIF		  	
				ENDIF
			ENDIF
			++iSubStateStatus
		BREAK

		CASE 1	

			GET_CHAR_COORDINATES this_ped foriginalX foriginalY foriginalZ
		    foriginalZ-=1.04 
		    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped -0.1 0.5 0.0 fMinX fMinY fMinZ
		    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped -1.0 -0.5 0.0 fMaxX fMaxY fMaxZ

			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 1.0 0.0 -0.65 foffX foffY foffZ	  		

			IF IS_CHAR_PLAYING_ANIM this_ped FF_Sit_Look
			OR IS_CHAR_PLAYING_ANIM this_ped FF_Sit_Eat1			
			OR IS_CHAR_PLAYING_ANIM this_ped FF_Sit_Eat2								
				SET_CHAR_USES_COLLISION_CLOSEST_OBJECT_OF_TYPE foriginalX foriginalY foriginalZ 1.5 CJ_PIZZA_CHAIR2 FALSE this_ped
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
				//--- No time for eating!
				iEatAndDrinkTime = 0
				//--- Move to the Damage State
				iPedState = 5
				iSubStateStatus = 0	
				BREAK					
			ENDIF
			//--- Check if it is time to stand up
			IF TIMERB > iEatAndDrinkTime				
				//--- Check if ped can leave chair or if there is a ped behind
				IF NOT IS_AREA_OCCUPIED fMinX fMinY fMinZ fMaxX fMaxY fMaxZ FALSE FALSE TRUE FALSE FALSE
					//---State completed
					iSubStateStatus = 0
					++iPedState					
				ELSE											   
					//--- Somebody IS behind. Check if this is an emergency or if the ped can remain seated
					IF iSetPizzaPanic = 1
						//--- Can't get out! Emergency!!!
						TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Die_Fwd FOOD 16.0 FALSE FALSE FALSE TRUE -1
						++iSubStateStatus
					ENDIF
				ENDIF
			ENDIF 		 
		BREAK

		CASE 3
			IF IS_CHAR_HEALTH_GREATER this_ped 0 
				//--- Get out as soon as possible!
				IF NOT IS_AREA_OCCUPIED fMinX fMinY fMinZ fMaxX fMaxY fMaxZ FALSE FALSE TRUE FALSE FALSE
					//---State completed
					iSubStateStatus = 0
					++iPedState
				ENDIF
			ELSE 
				//--- Play dead
				SET_CHAR_NEVER_TARGETTED this_ped TRUE
			ENDIF
		BREAK

	ENDSWITCH
RETURN
/*******************************************
				STAND UP
*******************************************/
FBOOTHL_PedState3: 
	SWITCH iSubStateStatus				

		CASE 0			
			IF IS_CHAR_HOLDING_OBJECT this_ped -1
				DROP_OBJECT this_ped TRUE
			ENDIF
			TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Sit_out_L_180 FOOD 99.0 FALSE FALSE FALSE FALSE -1				
			++iSubStateStatus
		BREAK 

		CASE 1
			SET_CHAR_COORDINATES this_ped foriginalX foriginalY foriginalZ
			FREEZE_CHAR_POSITION this_ped FALSE			
			++iSubStateStatus
		BREAK

		CASE 2
			fAnim_Time = 0.0
			IF IS_CHAR_PLAYING_ANIM this_ped FF_Sit_out_L_180
				GET_CHAR_ANIM_CURRENT_TIME this_ped	FF_Sit_out_L_180 fAnim_Time
			   	IF fAnim_Time = 1.0					
					SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE foriginalX foriginalY foriginalZ 1.5 CJ_PIZZA_CHAIR3 TRUE
					SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE foriginalX foriginalY foriginalZ 1.5 CJ_PIZZA_CHAIR2 FALSE
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
FBOOTHL_PedState5:
	SWITCH iSubStateStatus				

		CASE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Dam_Fwd FOOD 16.0 FALSE FALSE FALSE TRUE 0	
			++iSubStateStatus
		BREAK 

		CASE 1
			fAnim_Time = 0.0
			IF IS_CHAR_PLAYING_ANIM this_ped FF_Dam_Fwd
				GET_CHAR_ANIM_CURRENT_TIME this_ped	FF_Dam_Fwd fAnim_Time
			   	IF fAnim_Time = 1.0	
				   	++iSubStateStatus				
				ENDIF
			ENDIF

			IF NOT IS_CHAR_HEALTH_GREATER this_ped 0								
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Die_Fwd FOOD 16.0 FALSE FALSE FALSE TRUE 0
				GOSUB FBOOTHL_CleanUpBrain
			ENDIF
		BREAK

		CASE 2
			IF NOT IS_CHAR_HEALTH_GREATER this_ped 0								
				TASK_PLAY_ANIM_NON_INTERRUPTABLE this_ped FF_Die_Fwd FOOD 16.0 FALSE FALSE FALSE TRUE 0
				GOSUB FBOOTHL_CleanUpBrain
			ELSE
				IF NOT IS_AREA_OCCUPIED fMinX fMinY fMinZ fMaxX fMaxY fMaxZ FALSE FALSE TRUE FALSE FALSE
					//--- Stand Up				
					iPedState = 3
					iSubStateStatus = 0
					GOSUB FBOOTHL_PizzaPanic
				ELSE
					//--- Stay seated				
					iPedState = 2
					iSubStateStatus = 2
				ENDIF
			ENDIF	
			iSetPizzaPanic = 1
		BREAK
	ENDSWITCH				
RETURN
/*******************************************
			CLEAN UP BRAIN
*******************************************/
FBOOTHL_CleanUpBrain:
	MARK_OBJECT_AS_NO_LONGER_NEEDED ifoodModel
	REMOVE_ANIMATION FOOD
	IF NOT IS_CHAR_DEAD this_ped
		SET_CHAR_SUFFERS_CRITICAL_HITS this_ped TRUE
		TASK_USE_CLOSEST_MAP_ATTRACTOR this_ped 100.0 0 0.0 0.0 0.0 PCUSTOM	
	ENDIF
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
			PIZZA PANIC
*******************************************/ 
FBOOTHL_PizzaPanic:
	WAIT 0
	//--- This replaces the main loop with a cowering behaviour
	IF NOT IS_STRING_EMPTY $shop_name
	AND iTerminateAllAmbience = 0
		IF NOT IS_CHAR_DEAD this_ped
		AND IS_PLAYER_PLAYING PLAYER1
		 
			SWITCH iPedState		  
			
			CASE 1
				IF iSubStateStatus > 1 // anim has not started playing yet			
					GOSUB FBOOTHL_State_Machine				
				ELSE
					GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
					IF iIsCowering = FINISHED_TASK
						TASK_DUCK this_ped -2
						GOSUB FBOOTHL_CleanUpBrain
					ENDIF			 				
				ENDIF
			BREAK
			
			CASE 2
				IF iSubStateStatus =2
					GENERATE_RANDOM_INT_IN_RANGE 0 5 iEatAndDrinkTime
					iEatAndDrinkTime *= 500 
				ENDIF
				GOSUB FBOOTHL_State_Machine
			BREAK

			CASE 3
				GOSUB FBOOTHL_State_Machine
			BREAK

			CASE 4
				GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
				IF iIsCowering = FINISHED_TASK
					TASK_DUCK this_ped -2
					GOSUB FBOOTHL_CleanUpBrain
				ENDIF			 				
			BREAK

			CASE 5
				GOSUB FBOOTHL_State_Machine
			BREAK
		
			DEFAULT
				GOSUB FBOOTHL_CleanUpBrain
			BREAK

	   	  	ENDSWITCH		  
		ENDIF		
	ELSE 
		GOSUB FBOOTHL_CleanUpBrain
	ENDIF		

GOTO  FBOOTHL_PizzaPanic
/*******************************************/

/*******************************************/
FBOOTHL_Run_Debug:
	WRITE_DEBUG I__________________________I
	WRITE_DEBUG H 		   
	WRITE_DEBUG G
	WRITE_DEBUG F
	WRITE_DEBUG E
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT TIMERB	TIMERB
	WRITE_DEBUG_WITH_INT TIMERA	TIMERA
RETURN

}
MISSION_END