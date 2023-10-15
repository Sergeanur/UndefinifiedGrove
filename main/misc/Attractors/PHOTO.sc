MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************ TOURIST (PHOTO)  BRAIN	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
PHOTO_Brain:			 

    SCRIPT_NAME PHOTO

    LVAR_INT this_ped iSpawnedAtAttractor		 					// Parameters arriving from caller
    LVAR_INT iTemp iPedState iSubStateStatus iIsKilling iFlashDone 	// State Machine variables
	LVAR_FLOAT fAnim_Time fX fY fZ 						 					// Other variables
	
    iTemp = 0
	
    IF iTemp = 1
		//--- The ped running the sript
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF
	
	//--- Request animations & Models
	REQUEST_ANIMATION CAMERA
	REQUEST_MODEL CAMERA
	WHILE NOT HAS_MODEL_LOADED CAMERA
		REQUEST_ANIMATION CAMERA
		REQUEST_MODEL CAMERA
		WAIT 0 
	ENDWHILE
	WHILE NOT HAS_ANIMATION_LOADED CAMERA
		REQUEST_ANIMATION CAMERA
		REQUEST_MODEL CAMERA
		WAIT 0 
	ENDWHILE

	//--- Init the vars			
	iPedState = 1 // Counting from one makes each state more readable  
	iSubStateStatus = 0

	IF NOT IS_CHAR_DEAD this_ped
		GIVE_WEAPON_TO_CHAR this_ped WEAPONTYPE_CAMERA 1000
	ENDIF

	//---MAIN LOOP---
PHOTO_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB PHOTO_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF NOT IS_CHAR_DEAD this_ped
	AND IS_PLAYER_PLAYING PLAYER1
		IF IS_CHAR_USING_MAP_ATTRACTOR this_ped
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_ped scplayer
				GOTO PHOTO_GlobalFlagIntercept
			ELSE
				GOSUB PHOTO_State_Machine				
			ENDIF		
		ELSE
			GOSUB PHOTO_CleanUpBrain
		ENDIF
	ELSE
		GOSUB PHOTO_CleanUpBrain
	ENDIF

GOTO PHOTO_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
PHOTO_State_Machine:
	SWITCH iPedState	   	
   		CASE 1 //---STATE 1: PHOTO STANDING 
			GOSUB PHOTO_PedState1
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 1: 
********************************************/
PHOTO_PedState1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Start 
		TASK_PLAY_ANIM this_ped camstnd_idleloop CAMERA 4.0 FALSE FALSE FALSE TRUE -1		 
		++iSubStateStatus
	BREAK

	CASE 1			
		IF IS_CHAR_PLAYING_ANIM this_ped camstnd_idleloop 
			GET_CHAR_ANIM_CURRENT_TIME this_ped camstnd_idleloop fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Determine after how many seconds he will move to the next state
				GENERATE_RANDOM_INT_IN_RANGE 5000 8000 iTemp
				TASK_PLAY_ANIM this_ped camstnd_lkabt CAMERA 4.0 TRUE FALSE FALSE TRUE iTemp
				TIMERB = 0
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK
	
	CASE 2		
		IF TIMERB >= iTemp
			//--- Determine if he crouches or stands
			GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
			IF iTemp > 50
				TASK_PLAY_ANIM this_ped camstnd_to_camcrch CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				iSubStateStatus = 7 
			ELSE
				TASK_PLAY_ANIM this_ped camstnd_idleloop CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				iSubStateStatus = 3
			ENDIF			
		ENDIF
	BREAK
	//--- STANDING BRANCH
	CASE 3			
		IF IS_CHAR_PLAYING_ANIM this_ped camstnd_idleloop 
			GET_CHAR_ANIM_CURRENT_TIME this_ped camstnd_idleloop fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Take the pic
				TASK_PLAY_ANIM this_ped picstnd_in CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK
	
	CASE 4
		IF IS_CHAR_PLAYING_ANIM this_ped picstnd_in 
			GET_CHAR_ANIM_CURRENT_TIME this_ped picstnd_in fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Take the pic
				TASK_PLAY_ANIM this_ped picstnd_take CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 5			
		IF IS_CHAR_PLAYING_ANIM this_ped picstnd_take 
			GET_CHAR_ANIM_CURRENT_TIME this_ped picstnd_take fAnim_Time
			IF fAnim_Time > 0.5
			AND fAnim_Time < 1.0
			AND iFlashDone = 0
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.5 0.0 fX fY fZ
				DRAW_LIGHT_WITH_RANGE fX fY fZ 255 255 255 50.0
				REPORT_MISSION_AUDIO_EVENT_AT_CHAR this_ped SOUND_CAMERA_SHOT
				iFlashDone = 1
			ENDIF			
			IF fAnim_Time >= 1.0
				//--- Taken the pic
				TASK_PLAY_ANIM this_ped picstnd_out CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				iFlashDone = 0
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 6
		IF IS_CHAR_PLAYING_ANIM this_ped picstnd_out 
			GET_CHAR_ANIM_CURRENT_TIME this_ped picstnd_out fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Determine if he takes another or leaves
				GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
				IF iTemp > 50
					iSubStateStatus = 0  
				ELSE
					GOSUB PHOTO_CleanUpBrain
				ENDIF			
			ENDIF
		ENDIF
	BREAK
	//--- CROUCHED BRANCH
	CASE 7			
		IF IS_CHAR_PLAYING_ANIM this_ped camstnd_to_camcrch 
			GET_CHAR_ANIM_CURRENT_TIME this_ped camstnd_to_camcrch fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Determine after how many seconds he will move to the next state
				GENERATE_RANDOM_INT_IN_RANGE 5000 8000 iTemp
				TASK_PLAY_ANIM this_ped camcrch_idleloop CAMERA 4.0 TRUE FALSE FALSE TRUE iTemp
				TIMERB = 0
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 8			
		IF TIMERB >= iTemp
			//--- Take the pic
			TASK_PLAY_ANIM this_ped piccrch_in CAMERA 4.0 FALSE FALSE FALSE TRUE -1
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 9			
		IF IS_CHAR_PLAYING_ANIM this_ped piccrch_in 
			GET_CHAR_ANIM_CURRENT_TIME this_ped piccrch_in fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Take the pic
				TASK_PLAY_ANIM this_ped piccrch_take CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 10			
		IF IS_CHAR_PLAYING_ANIM this_ped piccrch_take 
			GET_CHAR_ANIM_CURRENT_TIME this_ped piccrch_take fAnim_Time
			IF fAnim_Time > 0.5
			AND fAnim_Time < 1.0
			AND iFlashDone = 0
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS this_ped 0.0 0.6 0.0 fX fY fZ
				DRAW_LIGHT_WITH_RANGE fX fY fZ 255 255 255 50.0
				REPORT_MISSION_AUDIO_EVENT_AT_CHAR this_ped SOUND_CAMERA_SHOT 
				iFlashDone = 1
			ENDIF
			IF fAnim_Time >= 1.0
				//--- Taken the pic
				TASK_PLAY_ANIM this_ped piccrch_out CAMERA 4.0 FALSE FALSE FALSE TRUE -1
				iFlashDone = 0
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 11
		IF IS_CHAR_PLAYING_ANIM this_ped piccrch_out 
			GET_CHAR_ANIM_CURRENT_TIME this_ped piccrch_out fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Back to stand
				TASK_PLAY_ANIM this_ped camcrch_to_camstnd CAMERA 4.0 FALSE FALSE FALSE FALSE -1
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 12
		IF IS_CHAR_PLAYING_ANIM this_ped piccrch_out 
			GET_CHAR_ANIM_CURRENT_TIME this_ped piccrch_out fAnim_Time
			IF fAnim_Time >= 1.0
				//--- Determine if he takes another or leaves
				GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
				IF iTemp > 50					
					iSubStateStatus = 0  
				ELSE
					GOSUB PHOTO_CleanUpBrain
				ENDIF			
			ENDIF
		ENDIF
	BREAK
	ENDSWITCH

RETURN
/*******************************************
			CLEAN UP BRAIN
********************************************/
PHOTO_CleanUpBrain:
	REMOVE_ANIMATION CAMERA
	IF NOT IS_CHAR_DEAD this_ped
		REMOVE_WEAPON_FROM_CHAR this_ped WEAPONTYPE_CAMERA
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
		GLOBAL FLAG INTERCEPT	
********************************************/ 
PHOTO_GlobalFlagIntercept:

	WAIT 0 
	//--- This case here replaces the main loop with an alternative behaviour
	IF NOT IS_CHAR_DEAD this_ped
		GET_SCRIPT_TASK_STATUS this_ped	TASK_KILL_CHAR_ON_FOOT iIsKilling
		IF iIsKilling = FINISHED_TASK
			TASK_KILL_CHAR_ON_FOOT this_ped scplayer
		ENDIF			 
	ELSE 
		GOSUB PHOTO_CleanUpBrain
	ENDIF	

GOTO  PHOTO_GlobalFlagIntercept
/*******************************************
				RUN DEBUG
********************************************/
PHOTO_Run_Debug:
	WRITE_DEBUG_WITH_INT PEDSTATE 		iPedState
	WRITE_DEBUG_WITH_INT SUBSTATESTATUS iSubStateStatus
	WRITE_DEBUG_WITH_INT LOOP_TIMER		TIMERB
	WRITE_DEBUG_WITH_INT SCRIPT_TIMER	TIMERA
RETURN 
}
MISSION_END






