MISSION_START

CONST_INT DANCE_TIME_TO_TRIGGER_SPEECH	10000

//--- PC VERSION: STICK POSITIONS
CONST_INT DANCE_STKLEFT					9
CONST_INT DANCE_STKRGHT					10
CONST_INT DANCE_STKUR					11 
CONST_INT DANCE_STKDL					12 
CONST_INT DANCE_STKUP					13
CONST_INT DANCE_STKDW					14
CONST_INT DANCE_STKUL					15 
CONST_INT DANCE_STKDR					16

CONST_INT SPRITE_DANCE_PERFECT			57
CONST_INT SPRITE_DANCE_GOOD				58
CONST_INT SPRITE_DANCE_BAD				59

//--- PC VERSION: SPECIAL QUIT BUTTON (still mapped on triangle)
CONST_INT DANCE_BUTTON_PC_QUIT 			99
CONST_INT DANCE_BUTTON_PC_CONFIRM		100

{
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************     DANCE MINIGAME	 	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/

    SCRIPT_NAME DANCE
	LVAR_FLOAT fParX fParY fParZ fParH
	LVAR_INT iSoundTrack iPartner 

	//--- INTERNALS
	LVAR_INT iState iSubState
	LVAR_FLOAT fX fY fZ fTemp fAnimTotalTime
	LVAR_INT iTemp iTimeToClosestBeat iClosestBeatNumber iLastScoredBeatNumber iAnimCount iCurrentButton 
	LVAR_INT iCurrentTime iStoredTime iTimeStep iStoredBeat iButtonDown	iLastPrint iAnimCount_P 
	LVAR_INT iGoodBeatsCounter iBadBeatsCounter iPerfectBeatsCounter iCurrentOverallState iTempScore
	LVAR_INT iAnimStatus 
	VAR_INT  iDance_SpeechRequest iDance_SpeechState iDance_SpeechCounter	 // no more space for locals I'm affraid


	//--- PC ONLY VARS
	VAR_INT d_lstickx d_lsticky d_rstickx d_rsticky d_player_stick_position
	VAR_FLOAT d_temp_float d_vec_x d_vec_y

	SET_BIT iDanceReport DANCE_MINIGAME_RUNNING
	SET_MINIGAME_IN_PROGRESS TRUE 

	//--- Set up the text display stuff
	USE_TEXT_COMMANDS TRUE
	FORCE_BIG_MESSAGE_AND_COUNTER TRUE

	//--- Parameter passing Fudge
	IF iTemp > 0
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iPartner
	ENDIF

	//--- Init the vars
	iCurrentButton = DANCE_BUTTON_NONE
	iButtonDown = DANCE_BUTTON_NONE 
	iLastScoredBeatNumber = -1
	iLastPrint = -1
	iAnimCount = -1
	iTemp = 0
	iDanceScore = 0
	iDance_SpeechState = DANCE_SPEECH_IDLE
	iDance_SpeechRequest = -1
	iDance_SpeechCounter = 1
	iStoredBeat = DANCE_BEAT_NONE

	TIMERA = DANCE_TIME_TO_TRIGGER_SPEECH

	//---MAIN LOOP---

Dance_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
// 	GOSUB Dance_Debug

	IF IS_PLAYER_PLAYING PLAYER1
		IF iState > 0 // We are past the INIT stage of the mini-game			
			IF iTerminateDanceMiniGame = 1
				IF NOT iState = 2
					//--- Force a change of state to the final wrap up
					iState = 2
					iSubState = 0 
				ENDIF				
			ENDIF
			//--- Check if we are in a mission or not
			IF iPartner = DANCE_MISSION_NO_PARTNER	 // this is a MISSION
				//--- In a mission, before printing the QUIT box, check if the beats have started
				IF NOT iStoredBeat = DANCE_BEAT_NONE 
					GOSUB Dance_PrintInfoText
				ENDIF
				//--- Now call the dance state machine
				GOSUB Dance_State_Machine	
			ELSE // This is NOT a mission						
				IF NOT IS_CHAR_DEAD iPartner
					//--- Outside missions, player can abort as soon as he likes
					GOSUB Dance_PrintInfoText
					GOSUB Dance_State_Machine
				ELSE
					GOSUB Dance_CleanUp
				ENDIF
			ENDIF
		ELSE
			//--- We are not yet past the init stage so all we can do is carry on 
			GOSUB Dance_State_Machine	
		ENDIF
	ELSE
		GOSUB Dance_CleanUp
	ENDIF

GOTO Dance_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
Dance_State_Machine:
	SWITCH iState	   	
   		CASE 0 //---INIT 
			GOSUB Dance_State0
		BREAK
   		CASE 1 //---DANCING
			GOSUB Dance_State1
		BREAK
   		CASE 2 //---WRAP UP
			GOSUB Dance_State2
		BREAK
   		CASE 3 //---PAUSE
			GOSUB Dance_State3
		BREAK
	ENDSWITCH

RETURN
/********************************************
			STATE 0: INIT
********************************************/
Dance_State0:  
	SWITCH iSubState

	CASE 0
		//--- Init the whole hog
		SET_PLAYER_CONTROL player1 OFF
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE 
		DISPLAY_HUD FALSE
		DISPLAY_RADAR FALSE
		IF NOT iPartner = DANCE_MISSION_NO_PARTNER 
			STORE_CLOCK
		ENDIF
		DO_FADE 500 FADE_OUT
		++iSubState
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS 			
			GOSUB Dance_GetAnimsToStream
			REQUEST_ANIMATION $txtCURR_DANCE_BLOCK
			IF NOT HAS_ANIMATION_LOADED $txtCURR_DANCE_BLOCK
				BREAK
			ENDIF

			IF NOT iPartner = DANCE_MISSION_NO_PARTNER
				SET_TIME_OF_DAY 00 00
			ENDIF
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_AREA fParX fParY fParZ 1.5 TRUE 
			SET_CHAR_COORDINATES scplayer fParX fParY fParZ 
			SET_CHAR_HEADING scplayer fParH
			GOSUB Dance_PartnerCreate			 
			GOSUB Dance_CameraViewChange
			iAnimCount = 0 // reset this here (has been used in Dance_CameraViewChange to initialise it)

			PRELOAD_BEAT_TRACK iSoundTrack 			
			++iSubState
		ENDIF
	BREAK

	CASE 2
		GET_BEAT_TRACK_STATUS iTemp 
		IF iTemp = CUTSCENE_TRACK_LOADED
			PLAY_BEAT_TRACK			
			
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer DANCE_LOOP $txtCURR_DANCE_BLOCK 99.0 TRUE FALSE FALSE TRUE 99999999999

			IF NOT IS_CHAR_DEAD iPartner
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iPartner DANCE_LOOP $txtCURR_DANCE_BLOCK 99.0 TRUE FALSE FALSE TRUE 99999999999
			ENDIF
			
			DO_FADE 500 FADE_IN
			TIMERB = 0 // reset the timer and start counting 
			++iSubState
		ENDIF
	BREAK

	CASE 3
		IF NOT GET_FADING_STATUS
			IF iPartner = DANCE_MISSION_NO_PARTNER
				IF TIMERB >= DANCE_WAIT_TO_START_BEATDISPLAY
					START_NEW_SCRIPT beat_display					
					DISPLAY_ONSCREEN_COUNTER_WITH_STRING iDanceScore COUNTER_DISPLAY_NUMBER DNC_001
					iState = 1
					iSubState =	STATE_DANCE_TRACK_AND_GET_BEATS
				ENDIF
			ELSE
				START_NEW_SCRIPT beat_display					
				DISPLAY_ONSCREEN_COUNTER_WITH_STRING iDanceScore COUNTER_DISPLAY_NUMBER DNC_001
				iState = 1
				iSubState =	STATE_DANCE_TRACK_AND_GET_BEATS
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
			STATE 1: DANCING
********************************************/
Dance_State1:  

	SWITCH iSubState
	
	CASE STATE_DANCE_TRACK_AND_GET_BEATS

		//--- Manage the dancing for the current frame
		GOSUB Dance_Get_TimeStep
		GOSUB Dance_CheckTrackStatus		// Break out and end if the track has finished
		GOSUB Dance_CheckAnimLinks
		GOSUB Dance_PartnerLinkAndPlayAnim	// If using a partner, updates its animations
		GOSUB Dance_GetPadStatus			// Reads the pad and fills the buttons variables
	
		// Must PAUSE
		IF iState = 3
			BREAK
		ENDIF
	
		GOSUB Dance_GetTimingOfCurrentBeat	// Returns iStoredBeat: the timing zones 
		GOSUB Dance_GetAnimLinkStatus		// Returns iAnimStatus: the animation status
		GOSUB Dance_CameraViewChange		// Changes to the appropriate camera view for the current animation
		GOSUB Dance_Update_Overall_Report	// Updates the big prints at the top of the screen
		GOSUB Dance_PlayStateFeedbackSpeech // Handles the spoken feedback for the current overall status

		IF iStoredBeat = DANCE_BEAT_ALRIGHT

			
			// --- Score it			
			IF iDanceScore < DANCE_SCORE_TOP_LIMIT				 
				IF iCurrentOverallState = DANCE_OVERALL_PERFECT					
					iTempScore = DANCE_SCORE_ALRIGHT
					iTempScore *= DANCE_SCORE_PERFECT_MULTIPLYER
					iDanceScore += iTempScore 
				ELSE
					iDanceScore += DANCE_SCORE_ALRIGHT 
				ENDIF
			ENDIF

			//--- Count good beat for report
			++iGoodBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback
			 
			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayGoodAnim
			ENDIF
			BREAK

		ENDIF


		IF iStoredBeat = DANCE_BEAT_PERFECT

			// --- Score it			
			IF iDanceScore < DANCE_SCORE_TOP_LIMIT
				IF iCurrentOverallState = DANCE_OVERALL_PERFECT					
					iTempScore = DANCE_SCORE_PERFECT
					iTempScore *= DANCE_SCORE_PERFECT_MULTIPLYER
					iDanceScore += iTempScore 
				ELSE
					iDanceScore += DANCE_SCORE_PERFECT 
				ENDIF
			ENDIF

			//--- Count good beat for report
			++iPerfectBeatsCounter

			//--- Print on screen 
			GOSUB Dance_Print_Input_Feedback
						
			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayGoodAnim
			ENDIF
			BREAK

		ENDIF

		IF iStoredBeat = DANCE_BEAT_GOOD
			
			// --- Score it
			IF iDanceScore < DANCE_SCORE_TOP_LIMIT
				IF iCurrentOverallState = DANCE_OVERALL_PERFECT					
					iTempScore = DANCE_SCORE_GOOD
					iTempScore *= DANCE_SCORE_PERFECT_MULTIPLYER
					iDanceScore += iTempScore 
				ELSE
					iDanceScore += DANCE_SCORE_GOOD 
				ENDIF
			ENDIF

			//--- Count good beat for report
			++iGoodBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback

			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayGoodAnim
			ENDIF
			BREAK

		ENDIF
			
		IF iStoredBeat = DANCE_BEAT_PAST

			// --- Score shit here
			IF iDanceScore > DANCE_SCORE_BAD
				iDanceScore -= DANCE_SCORE_BAD 
			ELSE
				iDanceScore = 0
			ENDIF
			//--- Count bad beat for report
			++iBadBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback

			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayMissAnim
			ENDIF
			BREAK

		ENDIF 

		IF iStoredBeat = DANCE_BEAT_FUTURE

			// --- Score shit here				
			IF iDanceScore > DANCE_SCORE_BAD
				iDanceScore -= DANCE_SCORE_BAD 
			ELSE
				iDanceScore = 0
			ENDIF
			//--- Count bad beat for report
			++iBadBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback

			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayMissAnim
			ENDIF
			BREAK

		ENDIF 				  

		IF iStoredBeat = DANCE_BEAT_WRONG_BUTTON

			// --- Score shit here				
			IF iDanceScore > DANCE_SCORE_VERY_BAD
				iDanceScore -= DANCE_SCORE_VERY_BAD 
			ELSE
				iDanceScore = 0
			ENDIF

			//--- Count bad beat for report
			++iBadBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback

			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayMissAnim
			ENDIF
			BREAK

		ENDIF 		
		
		IF iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
			
			// --- Score shit here				
			IF iDanceScore > DANCE_SCORE_VERY_BAD
				iDanceScore -= DANCE_SCORE_VERY_BAD 
			ELSE
				iDanceScore = 0
			ENDIF

			//--- Count bad beat for report
			++iBadBeatsCounter

			//--- Print on screen
			GOSUB Dance_Print_Input_Feedback

			IF NOT iAnimStatus = ANIM_IS_PLAYING
				GOSUB Dance_PlayMissAnim
			ENDIF
			BREAK

		ENDIF

	BREAK

	ENDSWITCH

RETURN
/********************************************
			STATE 2: WRAP UP
********************************************/
Dance_State2:  

	SWITCH iSubState

	CASE 0
		//--- Quit
		DO_FADE 500 FADE_OUT
		++iSubState
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS 			
			GET_BEAT_TRACK_STATUS iTemp 
			IF iTemp = CUTSCENE_TRACK_PLAYING
				STOP_BEAT_TRACK
			ENDIF		
			++iSubState
		ENDIF
	BREAK

	CASE 2		
		CLEAR_ONSCREEN_COUNTER iDanceScore
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE 
		DISPLAY_HUD TRUE
		DISPLAY_RADAR TRUE
		CLEAR_HELP			
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT			
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE			 								
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer DANCE_LOOP $txtCURR_DANCE_BLOCK 16.0 FALSE FALSE FALSE FALSE 0 
		IF NOT IS_CHAR_DEAD iPartner
			CLEAR_CHAR_TASKS_IMMEDIATELY iPartner
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iPartner DANCE_LOOP $txtCURR_DANCE_BLOCK 16.0 FALSE FALSE FALSE FALSE 0 
		ENDIF		
		IF NOT iPartner = DANCE_MISSION_NO_PARTNER 
			RESTORE_CLOCK
		ENDIF
		DO_FADE 1500 FADE_IN
		++iSubState
	BREAK

	CASE 3		
		GOSUB Dance_CleanUp		
	BREAK

	ENDSWITCH

RETURN
/********************************************
			STATE 3: PAUSE
********************************************/
Dance_State3: 
	SWITCH iSubState

		CASE 0
			PAUSE_CURRENT_BEAT_TRACK TRUE						
			//--- Pause the player mid-anim
			IF IS_CHAR_PLAYING_ANIM scplayer $txtCURR_DANCE_MOVE
				SET_CHAR_ANIM_PLAYING_FLAG scplayer $txtCURR_DANCE_MOVE FALSE
			ENDIF
			//--- Pause the partner mid-anim
			IF NOT IS_CHAR_DEAD iPartner
				IF IS_CHAR_PLAYING_ANIM iPartner $txtCURR_DANCE_MOVE_P
					SET_CHAR_ANIM_PLAYING_FLAG iPartner $txtCURR_DANCE_MOVE_P FALSE
				ENDIF
			ENDIF
			++iSubState			
		BREAK

		CASE 1
			GOSUB Dance_GetPadStatus
			IF iCurrentButton = DANCE_BUTTON_PC_CONFIRM
				//--- Quit out
				iDanceScore = 0
				iState = 2
				iSubState = 0				
				BREAK
			ENDIF	  	
			IF iCurrentButton = DANCE_BUTTON_PC_QUIT
				IF iState > 0 // We are past the INIT stage of the mini-game
					//--- Restart dance
					PAUSE_CURRENT_BEAT_TRACK FALSE
					IF IS_CHAR_PLAYING_ANIM scplayer $txtCURR_DANCE_MOVE
						SET_CHAR_ANIM_PLAYING_FLAG scplayer $txtCURR_DANCE_MOVE TRUE
					ENDIF		  
					IF NOT IS_CHAR_DEAD iPartner
						IF IS_CHAR_PLAYING_ANIM iPartner $txtCURR_DANCE_MOVE_P
							SET_CHAR_ANIM_PLAYING_FLAG iPartner $txtCURR_DANCE_MOVE_P TRUE
						ENDIF
					ENDIF
					iState = 1
					iSubState = STATE_DANCE_TRACK_AND_GET_BEATS	
					BREAK
				ENDIF
			ENDIF	  	
		BREAK

	ENDSWITCH
RETURN
/*******************************************
		LOAD ANIMATIONS FROM STREAM
********************************************/
Dance_GetAnimsToStream:

SWITCH iSoundTrack 

	CASE DANCE_TRACK_GFUNK 
		$txtCURR_DANCE_BLOCK = &GFUNK
	BREAK

	CASE DANCE_TRACK_GFUNK_ALT
		$txtCURR_DANCE_BLOCK = &GFUNK
	BREAK

	CASE DANCE_TRACK_RUNNINGMAN 
		$txtCURR_DANCE_BLOCK = &RUNNINGMAN
	BREAK

	CASE DANCE_TRACK_WOP 
		$txtCURR_DANCE_BLOCK = &WOP
	BREAK

	DEFAULT
		$txtCURR_DANCE_BLOCK = &WOP
		iSoundTrack = 4
	BREAK

ENDSWITCH

RETURN
/********************************************
			GET PAD STATUS
********************************************/
Dance_GetPadStatus:

//--- PC ONLY
GOSUB Dance_get_stick_position
	
SWITCH iButtonDown
	//WRITE_DEBUG_WITH_INT iButtonDown iButtonDown

	CASE DANCE_BUTTON_NONE 
		
		IF d_player_stick_position = DANCE_STKRGHT
			iCurrentButton = DANCE_BUTTON_CIRCLE
			iButtonDown = DANCE_BUTTON_CIRCLE			
			BREAK
		ENDIF

		IF d_player_stick_position = DANCE_STKUP
			iCurrentButton = DANCE_BUTTON_TRIANGLE
			iButtonDown = DANCE_BUTTON_TRIANGLE
			BREAK
		ENDIF

		IF d_player_stick_position = DANCE_STKLEFT
			iCurrentButton = DANCE_BUTTON_SQUARE
			iButtonDown = DANCE_BUTTON_SQUARE
			BREAK
		ENDIF

		IF d_player_stick_position = DANCE_STKDW
			iCurrentButton = DANCE_BUTTON_CROSS
			iButtonDown = DANCE_BUTTON_CROSS
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			iCurrentButton = DANCE_BUTTON_PC_QUIT
			iButtonDown = DANCE_BUTTON_PC_QUIT
			TIMERB = 0
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS
			iCurrentButton = DANCE_BUTTON_PC_CONFIRM
			iButtonDown = DANCE_BUTTON_PC_CONFIRM
			BREAK
		ENDIF

	BREAK
	
	CASE DANCE_BUTTON_CIRCLE
		iCurrentButton = DANCE_BUTTON_NONE
		IF d_player_stick_position = 0 
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE DANCE_BUTTON_TRIANGLE
		iCurrentButton = DANCE_BUTTON_NONE
		IF d_player_stick_position = 0
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE DANCE_BUTTON_SQUARE
		iCurrentButton = DANCE_BUTTON_NONE
		IF d_player_stick_position = 0
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE DANCE_BUTTON_CROSS
		iCurrentButton = DANCE_BUTTON_NONE
		IF d_player_stick_position = 0
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ENDIF
	BREAK
	
	CASE DANCE_BUTTON_PC_QUIT
		iCurrentButton = DANCE_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ELSE
			//--- Check if player is holding down TRIANGLE to QUIT
			IF iPartner = DANCE_MISSION_NO_PARTNER
				//--- Do all these fiddly 'on a mission' checks
				IF NOT iStoredBeat = DANCE_BEAT_NONE
					IF NOT iState = 3 // Not paused yet
					AND TIMERB >= DANCE_TIME_TO_HOLD_QUIT
						//--- Button has been held down				
						CLEAR_HELP
						iState = 3
						iSubState = 0				
						RETURN  
					ENDIF
				ENDIF  
			ELSE
				//--- Not on a mission, just the simple state checks 
				IF NOT iState = 3 // Not paused yet
				AND TIMERB >= DANCE_TIME_TO_HOLD_QUIT
					//--- Button has been held down				
					CLEAR_HELP
					iState = 3
					iSubState = 0				
					RETURN  
				ENDIF
			ENDIF
		ENDIF
	BREAK

	CASE DANCE_BUTTON_PC_CONFIRM
		iCurrentButton = DANCE_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			iButtonDown = DANCE_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

ENDSWITCH

RETURN
/********************************************
		GET TIMING OF CURRENT BEAT
********************************************/
Dance_GetTimingOfCurrentBeat:
	
	GET_BEAT_PROXIMITY 0 iTimeToClosestBeat iTemp iClosestBeatNumber
	
	IF iTimeToClosestBeat > DANCE_BOUNDS_NO_BEAT 
		iStoredBeat = DANCE_BEAT_NONE
	ELSE
	 	IF iClosestBeatNumber > iLastScoredBeatNumber 

			//--- Unverified Beat
			IF iCurrentButton = DANCE_BUTTON_NONE

				//WRITE_DEBUG_WITH_INT d_player_stick_position d_player_stick_position

				//--- No button press - check if the beat is not past
				IF iTimeToClosestBeat < DANCE_BOUNDS_ALRIGHT_MIN
					iStoredBeat = DANCE_BEAT_PAST
					iLastScoredBeatNumber = iClosestBeatNumber
					RETURN
				ENDIF

			ELSE

				//--- Button press
				BD_RenderHit = 1 // request for beatdisplay to render the hit fx
				
			//	WRITE_DEBUG_WITH_INT iCurrentButton iCurrentButton
			//	WRITE_DEBUG_WITH_INT iTemp iTemp

				// The right button has been pressed
				IF iCurrentButton = iTemp 
					//--- Check the scoring boundaries		
					IF iTimeToClosestBeat <= DANCE_BOUNDS_PERFECT_MAX  
					AND iTimeToClosestBeat >= DANCE_BOUNDS_PERFECT_MIN
						iStoredBeat = DANCE_BEAT_PERFECT
						BD_RenderHit = BD_HIT_PERFECT // override the default hit with this one
						iLastScoredBeatNumber = iClosestBeatNumber
						RETURN
					ELSE
						IF iTimeToClosestBeat <= DANCE_BOUNDS_GOOD_MAX  
						AND iTimeToClosestBeat >= DANCE_BOUNDS_GOOD_MIN
							iStoredBeat = DANCE_BEAT_GOOD
							BD_RenderHit = BD_HIT_GOOD // override the default hit with this one
							iLastScoredBeatNumber = iClosestBeatNumber
							RETURN
						ELSE
							IF iTimeToClosestBeat <= DANCE_BOUNDS_ALRIGHT_MAX
							AND iTimeToClosestBeat >= DANCE_BOUNDS_ALRIGHT_MIN
								iStoredBeat = DANCE_BEAT_ALRIGHT
								iLastScoredBeatNumber = iClosestBeatNumber
								RETURN
							ELSE
								iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
								iLastScoredBeatNumber = iClosestBeatNumber
								RETURN
							ENDIF
						ENDIF
					ENDIF

				ELSE

					iStoredBeat = DANCE_BEAT_WRONG_BUTTON
					iLastScoredBeatNumber = iClosestBeatNumber
					RETURN	
									
				ENDIF
			ENDIF
		ELSE

			//--- Verified beat - mark it as already scored
			IF iStoredBeat = DANCE_BEAT_PERFECT
			OR iStoredBeat = DANCE_BEAT_GOOD
			OR iStoredBeat = DANCE_BEAT_ALRIGHT
				iStoredBeat = DANCE_BEAT_GOOD_SCORED // For animation chaining purposes only
				RETURN
			ENDIF

			IF iStoredBeat = DANCE_BEAT_PAST
			OR iStoredBeat = DANCE_BEAT_FUTURE
			OR iStoredBeat = DANCE_BEAT_WRONG_BUTTON
			OR iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
				iStoredBeat = DANCE_BEAT_BAD_SCORED // For animation chaining purposes only
				RETURN
			ENDIF

		ENDIF
	ENDIF




RETURN
/********************************************
			PLAY GOOD ANIMATION
********************************************/
Dance_PlayGoodAnim:

 SWITCH iAnimCount
	DEFAULT
		$txtCURR_DANCE_MOVE = DANCE_G1 
		iAnimCount = 2
	BREAK
	CASE 2
		$txtCURR_DANCE_MOVE = DANCE_G2 
		++iAnimCount
	BREAK
	CASE 3
		$txtCURR_DANCE_MOVE = DANCE_G3 
		++iAnimCount
	BREAK
	CASE 4
		$txtCURR_DANCE_MOVE = DANCE_G4 
		++iAnimCount
	BREAK
	CASE 5
		$txtCURR_DANCE_MOVE = DANCE_G5 
		++iAnimCount
	BREAK
	CASE 6
		$txtCURR_DANCE_MOVE = DANCE_G6 
		++iAnimCount
	BREAK
	CASE 7
		$txtCURR_DANCE_MOVE = DANCE_G7 
		++iAnimCount
	BREAK
	CASE 8
		$txtCURR_DANCE_MOVE = DANCE_G8 
		++iAnimCount
	BREAK
	CASE 9
		$txtCURR_DANCE_MOVE = DANCE_G9 
		++iAnimCount
	BREAK
	CASE 10
		$txtCURR_DANCE_MOVE = DANCE_G10 
		++iAnimCount
	BREAK
	CASE 11
		$txtCURR_DANCE_MOVE = DANCE_G11 
		++iAnimCount
	BREAK
	CASE 12
		$txtCURR_DANCE_MOVE = DANCE_G12 
		++iAnimCount
	BREAK
	CASE 13
		$txtCURR_DANCE_MOVE = DANCE_G13 
		++iAnimCount
	BREAK
	CASE 14
		$txtCURR_DANCE_MOVE = DANCE_G14 
		++iAnimCount
	BREAK
	CASE 15
		$txtCURR_DANCE_MOVE = DANCE_G15 
		++iAnimCount
	BREAK
	CASE 16
		$txtCURR_DANCE_MOVE = DANCE_G16 
		++iAnimCount
	BREAK
ENDSWITCH

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $txtCURR_DANCE_MOVE $txtCURR_DANCE_BLOCK 4.0 FALSE FALSE FALSE FALSE 0 
 	
RETURN
/********************************************
			PLAY MISS ANIMATION
********************************************/
Dance_PlayMissAnim:

 SWITCH iAnimCount
	DEFAULT
		$txtCURR_DANCE_MOVE = DANCE_B1 
		iAnimCount = 2
	BREAK
	CASE 2
		$txtCURR_DANCE_MOVE = DANCE_B2 
		++iAnimCount
	BREAK
	CASE 3
		$txtCURR_DANCE_MOVE = DANCE_B3 
		++iAnimCount
	BREAK
	CASE 4
		$txtCURR_DANCE_MOVE = DANCE_B4 
		++iAnimCount
	BREAK
	CASE 5
		$txtCURR_DANCE_MOVE = DANCE_B5 
		++iAnimCount
	BREAK
	CASE 6
		$txtCURR_DANCE_MOVE = DANCE_B6 
		++iAnimCount
	BREAK
	CASE 7
		$txtCURR_DANCE_MOVE = DANCE_B7 
		++iAnimCount
	BREAK
	CASE 8
		$txtCURR_DANCE_MOVE = DANCE_B8 
		++iAnimCount
	BREAK
	CASE 9
		$txtCURR_DANCE_MOVE = DANCE_B9 
		++iAnimCount
	BREAK
	CASE 10
		$txtCURR_DANCE_MOVE = DANCE_B10 
		++iAnimCount
	BREAK
	CASE 11
		$txtCURR_DANCE_MOVE = DANCE_B11 
		++iAnimCount
	BREAK
	CASE 12
		$txtCURR_DANCE_MOVE = DANCE_B12 
		++iAnimCount
	BREAK
	CASE 13
		$txtCURR_DANCE_MOVE = DANCE_B13 
		++iAnimCount
	BREAK
	CASE 14
		$txtCURR_DANCE_MOVE = DANCE_B14 
		++iAnimCount
	BREAK
	CASE 15
		$txtCURR_DANCE_MOVE = DANCE_B15 
		++iAnimCount
	BREAK
	CASE 16
		$txtCURR_DANCE_MOVE = DANCE_B16 
		++iAnimCount
	BREAK
ENDSWITCH

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $txtCURR_DANCE_MOVE $txtCURR_DANCE_BLOCK 4.0 FALSE FALSE FALSE FALSE 0
 	
RETURN
/********************************************
		GET ANIM LINK STATUS
********************************************/
Dance_GetAnimLinkStatus:
	IF IS_CHAR_PLAYING_ANIM scplayer $txtCURR_DANCE_MOVE 		
		GET_CHAR_ANIM_TOTAL_TIME scplayer $txtCURR_DANCE_MOVE fAnimTotalTime 		
		GET_CHAR_ANIM_CURRENT_TIME scplayer $txtCURR_DANCE_MOVE fTemp 
		fX =# iTimeStep
		fparX = fX / fAnimTotalTime
		fTemp += fparX 
		IF fTemp >= 1.0		
			//--- Anim finished
			iAnimStatus = ANIM_CONCANTENATE_NOW						
			RETURN
		ELSE
			iAnimStatus = ANIM_IS_PLAYING
			RETURN
		ENDIF
	ELSE
		//--- All good
		iAnimStatus = ANIM_NOT_PLAYING		
		RETURN
	ENDIF
RETURN
/********************************************
		PARTNER LINK AND PLAY ANIM
********************************************/
Dance_PartnerLinkAndPlayAnim:
	IF iPartner > -1
		IF IS_CHAR_PLAYING_ANIM iPartner $txtCURR_DANCE_MOVE_P 		
			GET_CHAR_ANIM_TOTAL_TIME iPartner $txtCURR_DANCE_MOVE_P fAnimTotalTime 		
			GET_CHAR_ANIM_CURRENT_TIME iPartner $txtCURR_DANCE_MOVE_P fTemp 
			fX =# iTimeStep 
			fparX = fX / fAnimTotalTime
			fTemp += fparX 
			IF fTemp >= 1.0		
				//--- Anim finished
				GOSUB Dance_PlayPartnerAnim
				RETURN
			ENDIF
		ELSE
			//--- Intercept FIRST SYNCH beat
			GET_BEAT_PROXIMITY 0 iTimeToClosestBeat iTemp iClosestBeatNumber	    
			IF iClosestBeatNumber = 0
				IF iTimeToClosestBeat <= 25
					//--- Anim must start now
					GOSUB Dance_PlayPartnerAnim			
					RETURN
				ENDIF
			ENDIF		
		ENDIF
	ENDIF
RETURN
/********************************************
			GET TIME STEP
********************************************/
Dance_Get_TimeStep:
	GET_GAME_TIMER iCurrentTime
	iTimeStep = iCurrentTime - iStoredTime
	iStoredTime = iCurrentTime
RETURN
/*******************************************
		CHECK ANIMATION LINKS
********************************************/
Dance_CheckAnimLinks:

	GOSUB Dance_GetAnimLinkStatus // Returns in iAnimStatus
	 
	//--- Check if the animation requires to chain a beat 
	IF iAnimStatus = ANIM_CONCANTENATE_NOW
	   	//--- Check the previous stored beat and follow up in a chain
		IF iStoredBeat = DANCE_BEAT_PERFECT
		OR iStoredBeat = DANCE_BEAT_GOOD
		OR iStoredBeat = DANCE_BEAT_ALRIGHT
		OR iStoredBeat = DANCE_BEAT_GOOD_SCORED
			GOSUB Dance_PlayGoodAnim
			iAnimStatus = ANIM_IS_PLAYING //???
			RETURN
		ELSE			 			
			GOSUB Dance_PlayMissAnim
			iAnimStatus = ANIM_IS_PLAYING //???
			RETURN
		ENDIF

	ENDIF

RETURN
/*******************************************
			CAMERA VIEW CHANGE
********************************************/
Dance_CameraViewChange:

	SWITCH iAnimCount

		CASE -1
			IF iPartner = DANCE_MISSION_NO_PARTNER
			AND fDanceCameraX[0] > 0.0
				SET_FIXED_CAMERA_POSITION fDanceCameraX[0] fDanceCameraY[0] fDanceCameraZ[0] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fDanceTargetX[0] fDanceTargetY[0] fDanceTargetZ[0] JUMP_CUT
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -2.3176 0.9635 0.5141 fX fY fZ
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.3381 0.8235 0.6587 fparX fparY fparZ			
				fZ -= 1.0
				fparZ -= 1.0				
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fparX fparY fparZ JUMP_CUT
			ENDIF
		BREAK

		CASE 4
			IF iPartner = DANCE_MISSION_NO_PARTNER
			AND fDanceCameraX[1] > 0.0
				SET_FIXED_CAMERA_POSITION fDanceCameraX[1] fDanceCameraY[1] fDanceCameraZ[1] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fDanceTargetX[1] fDanceTargetY[1] fDanceTargetZ[1] JUMP_CUT
			ELSE
				//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 2.5211 0.6375 0.4297 fX fY fZ
				//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.5521 0.7218 0.6618 fparX fparY fparZ			
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 3.5814 0.8987 0.6651 fX fY fZ
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 2.6071 0.853 0.8857 fparX fparY fparZ
				fZ -= 1.0
				fparZ -= 1.0
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fparX fparY fparZ JUMP_CUT
			ENDIF
		BREAK


		CASE 8
			IF iPartner = DANCE_MISSION_NO_PARTNER
			AND fDanceCameraX[2] > 0.0
				SET_FIXED_CAMERA_POSITION fDanceCameraX[2] fDanceCameraY[2] fDanceCameraZ[2] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fDanceTargetX[2] fDanceTargetY[2] fDanceTargetZ[2] JUMP_CUT
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.3843 2.9502 1.8476 fX fY fZ
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.461 2.0089 1.5188 fparX fparY fparZ			
				fZ -= 1.0
				fparZ -= 1.0
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fparX fparY fparZ JUMP_CUT
			ENDIF			
		BREAK

		CASE 12
			IF iPartner = DANCE_MISSION_NO_PARTNER
			AND fDanceCameraX[3] > 0.0
				SET_FIXED_CAMERA_POSITION fDanceCameraX[3] fDanceCameraY[3] fDanceCameraZ[3] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fDanceTargetX[3] fDanceTargetY[3] fDanceTargetZ[3] JUMP_CUT
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -2.2621 0.1226 0.6863 fX fY fZ
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.2706 0.1649 0.8096 fparX fparY fparZ			
				fZ -= 1.0
				fparZ -= 1.0
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fparX fparY fparZ JUMP_CUT
			ENDIF
		BREAK

		CASE 16
			IF iPartner = DANCE_MISSION_NO_PARTNER
			AND fDanceCameraX[4] > 0.0
				SET_FIXED_CAMERA_POSITION fDanceCameraX[4] fDanceCameraY[4] fDanceCameraZ[4] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fDanceTargetX[4] fDanceTargetY[4] fDanceTargetZ[4] JUMP_CUT
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.6647 1.7322 2.3217 fX fY fZ
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.8677 1.3108 1.889 fparX fparY fparZ			
				fZ -= 1.0
				fparZ -= 1.0
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fparX fparY fparZ JUMP_CUT
			ENDIF
		BREAK
	ENDSWITCH	
RETURN
/*******************************************
		PARTNER CREATE
********************************************/
Dance_PartnerCreate:
 
	IF NOT iPartner = DANCE_MISSION_NO_PARTNER 
		
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.0 1.3 0.0 fX fY fZ
		GET_GROUND_Z_FOR_3D_COORD fX fY fZ fZ

		//--- Check if the passed ped exists
		IF IS_CHAR_DEAD iPartner 
			//--- Create the random person she is talking to at the moment
			GOSUB Dance_InstanceRandomPartner 			
		ELSE
			IF IS_GROUP_MEMBER iPartner players_group
				REMOVE_CHAR_FROM_GROUP iPartner  
			ENDIF  
	 		CLEAR_CHAR_TASKS_IMMEDIATELY iPartner
	 		SET_CHAR_COORDINATES iPartner fX fY fZ 
		ENDIF

		IF NOT IS_CHAR_DEAD iPartner
		AND NOT IS_CHAR_DEAD scplayer
			GET_CHAR_HEADING scplayer fTemp[0]
			fTemp[0] += 180.0
			SET_CHAR_HEADING iPartner fTemp[0]		
		ENDIF
	ENDIF

RETURN
/********************************************
			PLAY PARTNER ANIMATION
********************************************/
Dance_PlayPartnerAnim:

 SWITCH iAnimCount_P
	DEFAULT
		$txtCURR_DANCE_MOVE_P = DANCE_G1 
		iAnimCount_P = 2
	BREAK
	CASE 2
		$txtCURR_DANCE_MOVE_P = DANCE_G2 
		++iAnimCount_P
	BREAK
	CASE 3
		$txtCURR_DANCE_MOVE_P = DANCE_G3 
		++iAnimCount_P
	BREAK
	CASE 4
		$txtCURR_DANCE_MOVE_P = DANCE_G4 
		++iAnimCount_P
	BREAK
	CASE 5
		$txtCURR_DANCE_MOVE_P = DANCE_G5 
		++iAnimCount_P
	BREAK
	CASE 6
		$txtCURR_DANCE_MOVE_P = DANCE_G6 
		++iAnimCount_P
	BREAK
	CASE 7
		$txtCURR_DANCE_MOVE_P = DANCE_G7 
		++iAnimCount_P
	BREAK
	CASE 8
		$txtCURR_DANCE_MOVE_P = DANCE_G8 
		++iAnimCount_P
	BREAK
	CASE 9
		$txtCURR_DANCE_MOVE_P = DANCE_G9 
		++iAnimCount_P
	BREAK
	CASE 10
		$txtCURR_DANCE_MOVE_P = DANCE_G10 
		++iAnimCount_P
	BREAK
	CASE 11
		$txtCURR_DANCE_MOVE_P = DANCE_G11 
		++iAnimCount_P
	BREAK
	CASE 12
		$txtCURR_DANCE_MOVE_P = DANCE_G12 
		++iAnimCount_P
	BREAK
	CASE 13
		$txtCURR_DANCE_MOVE_P = DANCE_G13
		++iAnimCount_P
	BREAK
	CASE 14
		$txtCURR_DANCE_MOVE_P = DANCE_G14
		++iAnimCount_P
	BREAK
	CASE 15
		$txtCURR_DANCE_MOVE_P = DANCE_G15
		++iAnimCount_P
	BREAK
	CASE 16
		$txtCURR_DANCE_MOVE_P = DANCE_G16 
		++iAnimCount_P
	BREAK
ENDSWITCH

	 IF NOT IS_CHAR_DEAD iPartner
		TASK_PLAY_ANIM_NON_INTERRUPTABLE iPartner $txtCURR_DANCE_MOVE_P $txtCURR_DANCE_BLOCK 4.0 FALSE FALSE FALSE FALSE 0 
	ENDIF  

RETURN
/*******************************************
		INSTANCE RANDOM PARTNER 
********************************************/
Dance_InstanceRandomPartner:

	GENERATE_RANDOM_INT_IN_RANGE 1 5 iTemp

	SWITCH iTemp
		CASE 1
			WHILE NOT HAS_MODEL_LOADED BFYRI
				REQUEST_MODEL BFYRI						  
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI fX fY fZ iPartner
			RETURN
		BREAK

		CASE 2
			WHILE NOT HAS_MODEL_LOADED OFYST
				REQUEST_MODEL OFYST						  
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVFEMALE OFYST fX fY fZ iPartner
			RETURN
		BREAK

		CASE 3
			WHILE NOT HAS_MODEL_LOADED WFYST
				REQUEST_MODEL WFYST						  
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVFEMALE WFYST fX fY fZ iPartner
			RETURN
		BREAK

		CASE 4
			WHILE NOT HAS_MODEL_LOADED HFYRI
				REQUEST_MODEL HFYRI						  
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVFEMALE HFYRI fX fY fZ iPartner
			RETURN
		BREAK

		CASE 5
			WHILE NOT HAS_MODEL_LOADED WFYRI
				REQUEST_MODEL WFYRI						  
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVFEMALE WFYRI fX fY fZ iPartner
			RETURN
		BREAK

	ENDSWITCH
RETURN
/*******************************************
		CHECK TRACK STATUS
********************************************/
Dance_CheckTrackStatus:

	IF NOT IS_STRING_EMPTY $txtCURR_DANCE_MOVE		
		GET_BEAT_PROXIMITY 0 iTimeToClosestBeat iTemp iClosestBeatNumber
		IF iTemp = DANCE_END_BEAT
			iState = 2
			iSubState =	0
		ENDIF
	ENDIF

RETURN
/********************************************
			PRINT INPUT FEEDBACK
********************************************/
Dance_Print_Input_Feedback:
  
 SWITCH iLastPrint
		DEFAULT
			IF iStoredBeat = DANCE_BEAT_PERFECT				
				PRINT_BIG DNC_005 1000 5 // Great timing!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_GOOD
				PRINT_BIG DNC_006 1000 5 // Well done!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_ALRIGHT
				PRINT_BIG DNC_007 1000 5  // Alright
			ENDIF
			IF iStoredBeat = DANCE_BEAT_PAST
				PRINT_BIG DNC_008 1000 5 // Too late!	 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_FUTURE
				PRINT_BIG DNC_009 1000 5 // Too early!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_010 1000 5 // Wrong!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_011 1000 5 // Poor timing!
			ENDIF
			iLastPrint = 2
		BREAK

		CASE 2
			IF iStoredBeat = DANCE_BEAT_PERFECT
				PRINT_BIG DNC_012 1000 5 //Synchronized! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_GOOD
				PRINT_BIG DNC_013 1000 5 //That's it! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_ALRIGHT
				PRINT_BIG DNC_014 1000 5  //Not bad! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_PAST
				PRINT_BIG DNC_015 1000 5 //You missed it! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_FUTURE
				PRINT_BIG DNC_016 1000 5 //You're fast! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_017 1000 5 //Not that one! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_018 1000 5 //Try again! 
			ENDIF
			++iLastPrint
		BREAK

		CASE 3
			IF iStoredBeat = DANCE_BEAT_PERFECT
				PRINT_BIG DNC_019 1000 5 //The master! 
			ENDIF
			IF iStoredBeat = DANCE_BEAT_GOOD
				PRINT_BIG DNC_020 1000 5 //You got it!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_ALRIGHT
				PRINT_BIG DNC_021 1000 5  //Can do better!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_PAST
				PRINT_BIG DNC_022 1000 5 //You're late!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_FUTURE
				PRINT_BIG DNC_023 1000 5 //Slow down!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_024 1000 5 //Random!
			ENDIF
			IF iStoredBeat = DANCE_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_025 1000 5 //Not on time!
			ENDIF
			++iLastPrint
		BREAK

	ENDSWITCH
 
RETURN
/********************************************
			UPDATE OVERALL REPORT
********************************************/
Dance_Update_Overall_Report:

	iTemp = iGoodBeatsCounter + iBadBeatsCounter 
	iTemp += iPerfectBeatsCounter

	IF iTemp > 0
		IF iTemp >= DANCE_SCORE_TOTAL_BEATS_TO_REPORT
			//--- Time to update the overall status report
			IF iPerfectBeatsCounter = DANCE_SCORE_TOTAL_BEATS_TO_REPORT 
				//--- Player has scored perfect on all beats
			 	IF iCurrentOverallState = DANCE_OVERALL_GOOD
				OR iCurrentOverallState = DANCE_OVERALL_PERFECT
					//--- Can enter or remain perfect
					iDance_SpeechRequest = DANCE_SPEECH_HIGH
					TIMERA = 0 // Reset the timer used to count the time limit to trigger same context
					PRINT_BIG DNC_002 50000 7 // PERFECT! //use a big number, the next state will brak it
					iCurrentOverallState = DANCE_OVERALL_PERFECT					
				ELSE
					//--- Last state was bad, so we can only move up to good
					iDance_SpeechRequest = DANCE_SPEECH_HIGH
					TIMERA = 0 // Reset the timer used to count the time limit to trigger same context
					PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
					iCurrentOverallState = DANCE_OVERALL_GOOD
				ENDIF
			ELSE
				IF iGoodBeatsCounter > iBadBeatsCounter
					//--- Player has scored more good beats than bad beats
					IF NOT iCurrentOverallState = DANCE_OVERALL_GOOD
						//--- If the current state was not GOOD it means we have just entered it, trigger speech
						iDance_SpeechRequest = DANCE_SPEECH_MED
						TIMERA = 0
					ELSE
						//-- The current state was good already, check timer to see if we can trigger speech
						IF TIMERA >= DANCE_TIME_TO_TRIGGER_SPEECH
							iDance_SpeechRequest = DANCE_SPEECH_MED
							TIMERA = 0
						ENDIF
					ENDIF
					PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
					iCurrentOverallState = DANCE_OVERALL_GOOD
				ELSE
					//--- Player has scored more bad beats than good beats
					IF iCurrentOverallState = DANCE_OVERALL_PERFECT
						iDance_SpeechRequest = DANCE_SPEECH_LOW
						TIMERA = 0 // Reset the timer used to count the time limit to trigger same context
						PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
						iCurrentOverallState = DANCE_OVERALL_GOOD						
					ELSE
						IF iCurrentOverallState = DANCE_OVERALL_GOOD 
							iDance_SpeechRequest = DANCE_SPEECH_NOT
							TIMERA = 0 // Reset the timer used to count the time limit to trigger same context
							PRINT_BIG DNC_003 50000 7 // BAD! //use a big number, the next state will brak it
							iCurrentOverallState = DANCE_OVERALL_BAD							
						ELSE
							//--- Player has never moved away from his BAD state
							IF TIMERA >= DANCE_TIME_TO_TRIGGER_SPEECH
								iDance_SpeechRequest = DANCE_SPEECH_NOT
								TIMERA = 0 // Reset the timer used to count the time limit to trigger same context
							ENDIF
							PRINT_BIG DNC_003 50000 7 // BAD! //use a big number, the next state will brak it
							iCurrentOverallState = DANCE_OVERALL_BAD
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			//--- Clear the counters 
			iPerfectBeatsCounter = 0
			iGoodBeatsCounter = 0
			iBadBeatsCounter = 0
		ENDIF
	ENDIF

RETURN
/*******************************************
			PRINT INFO TEXT
********************************************/
Dance_PrintInfoText:	
	SET_TEXT_RIGHT_JUSTIFY OFF	
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND ON
	SET_TEXT_SCALE 0.5014 1.8889
	SET_TEXT_EDGE 2 0 0 0 255
	IF iState = 3
		SET_TEXT_WRAPX 240.0
		DISPLAY_TEXT 32.0 32.0 DNC_H2
	ELSE
		
		IF NOT iState = 2
			SET_TEXT_WRAPX 220.0
			DISPLAY_TEXT 32.0 32.0 DNC_H1
		ENDIF
	ENDIF
RETURN
/*******************************************
		PLAY STATE FEEDBACK SPEECH
********************************************/
Dance_PlayStateFeedbackSpeech:

SWITCH iDance_SpeechState

	CASE DANCE_SPEECH_IDLE
		IF iDance_SpeechRequest = DANCE_SPEECH_HIGH
			iDance_SpeechState = DANCE_SPEECH_REQUEST_HIGH 
			iDance_SpeechRequest = -1
			BREAK
		ENDIF  
		IF iDance_SpeechRequest = DANCE_SPEECH_LOW
			iDance_SpeechState = DANCE_SPEECH_REQUEST_LOW 
			iDance_SpeechRequest = -1
			BREAK
		ENDIF  
		IF iDance_SpeechRequest = DANCE_SPEECH_MED
			iDance_SpeechState = DANCE_SPEECH_REQUEST_MED
			iDance_SpeechRequest = -1
			BREAK
		ENDIF  
		IF iDance_SpeechRequest = DANCE_SPEECH_NOT
			iDance_SpeechState = DANCE_SPEECH_REQUEST_NOT
			iDance_SpeechRequest = -1
			BREAK
		ENDIF  
	BREAK 

	CASE DANCE_SPEECH_REQUEST_HIGH
		IF HAS_MISSION_AUDIO_FINISHED 4
			CLEAR_MISSION_AUDIO 4
			GOSUB Dance_RetrieveSpeechHigh
			iDance_SpeechState = DANCE_SPEECH_PLAYBACK		 
		ENDIF
	BREAK

	CASE DANCE_SPEECH_REQUEST_LOW
		IF HAS_MISSION_AUDIO_FINISHED 4
			CLEAR_MISSION_AUDIO 4
			GOSUB Dance_RetrieveSpeechLow
			iDance_SpeechState = DANCE_SPEECH_PLAYBACK		 
		ENDIF
	BREAK

	CASE DANCE_SPEECH_REQUEST_MED
		IF HAS_MISSION_AUDIO_FINISHED 4
			CLEAR_MISSION_AUDIO 4
			GOSUB Dance_RetrieveSpeechMed
			iDance_SpeechState = DANCE_SPEECH_PLAYBACK		 
		ENDIF
	BREAK

	CASE DANCE_SPEECH_REQUEST_NOT
		IF HAS_MISSION_AUDIO_FINISHED 4
			CLEAR_MISSION_AUDIO 4
			GOSUB Dance_RetrieveSpeechNot
			iDance_SpeechState = DANCE_SPEECH_PLAYBACK		 
		ENDIF
	BREAK
	 
	CASE DANCE_SPEECH_PLAYBACK 
		IF HAS_MISSION_AUDIO_LOADED 4
			ATTACH_MISSION_AUDIO_TO_CHAR 4 scplayer
			PLAY_MISSION_AUDIO 4
			iDance_SpeechState = DANCE_SPEECH_IDLE			
		ENDIF
	BREAK

ENDSWITCH
RETURN
/*******************************************
			RETRIEVE SPEECH HIGH
********************************************/
Dance_RetrieveSpeechHigh:

SWITCH iDance_SpeechCounter

	DEFAULT
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_56
		iDance_SpeechCounter = 1
	BREAK
	CASE 1
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_01
		++iDance_SpeechCounter 
	BREAK 
	CASE 2
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_02
		++iDance_SpeechCounter 
	BREAK 
	CASE 3
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_03 
		++iDance_SpeechCounter
	BREAK
	CASE 4
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_04 
		++iDance_SpeechCounter
	BREAK 
	CASE 5
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_05
		++iDance_SpeechCounter
	BREAK 
	CASE 6
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_06
		++iDance_SpeechCounter
	BREAK 
	CASE 7
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_07
		++iDance_SpeechCounter
	BREAK 
	CASE 8
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_08
		++iDance_SpeechCounter
	BREAK 
	CASE 9
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_09
		++iDance_SpeechCounter
	BREAK 
	CASE 10
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_10
		++iDance_SpeechCounter
	BREAK 
	CASE 11 
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_11
		++iDance_SpeechCounter
	BREAK 
	CASE 12
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_12
		++iDance_SpeechCounter 
	BREAK 
	CASE 13
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_13 
		++iDance_SpeechCounter
	BREAK
	CASE 14
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_14 
		++iDance_SpeechCounter
	BREAK 
	CASE 15
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_15
		++iDance_SpeechCounter
	BREAK 
	CASE 16
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_16
		++iDance_SpeechCounter
	BREAK 
	CASE 17
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_17
		++iDance_SpeechCounter
	BREAK 
	CASE 18
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_18
		++iDance_SpeechCounter
	BREAK 
	CASE 19
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_19
		++iDance_SpeechCounter
	BREAK 
	CASE 20
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_20
		++iDance_SpeechCounter 
	BREAK 
	CASE 21
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_21
		++iDance_SpeechCounter 
	BREAK 
	CASE 22
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_22
		++iDance_SpeechCounter 
	BREAK 
	CASE 23
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_23 
		++iDance_SpeechCounter
	BREAK
	CASE 24
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_24 
		++iDance_SpeechCounter
	BREAK 
	CASE 25
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_25
		++iDance_SpeechCounter
	BREAK 
	CASE 26
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_26
		++iDance_SpeechCounter
	BREAK 
	CASE 27
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_27
		++iDance_SpeechCounter
	BREAK 
	CASE 28
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_28
		++iDance_SpeechCounter
	BREAK 
	CASE 29
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_29
		++iDance_SpeechCounter
	BREAK 
	CASE 30
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_30
		++iDance_SpeechCounter 
	BREAK 
	CASE 31
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_31
		++iDance_SpeechCounter 
	BREAK 
	CASE 32
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_32
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 33
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_33 
		++iDance_SpeechCounter
	BREAK
	CASE 34
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_34 
		++iDance_SpeechCounter
	BREAK 
	CASE 35
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_35
		++iDance_SpeechCounter
	BREAK 
	CASE 36
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_36
		++iDance_SpeechCounter
	BREAK 
	CASE 37
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_37
		++iDance_SpeechCounter
	BREAK 
	CASE 38
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_38
		++iDance_SpeechCounter
	BREAK 
	CASE 39
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_39
		++iDance_SpeechCounter
	BREAK 
	CASE 40
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_40
		++iDance_SpeechCounter 
	BREAK 
	CASE 41
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_41
		++iDance_SpeechCounter 
	BREAK 
	CASE 42
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_42
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 43
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_43 
		++iDance_SpeechCounter
	BREAK
	CASE 44
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_44 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 45
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_45
		++iDance_SpeechCounter
	BREAK 
	CASE 46
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_46
		++iDance_SpeechCounter
	BREAK 
	CASE 47
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_47
		++iDance_SpeechCounter
	BREAK 
	CASE 48
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_48
		++iDance_SpeechCounter
	BREAK 
	CASE 49
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_49
		++iDance_SpeechCounter
	BREAK 
	CASE 50
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_50
		++iDance_SpeechCounter 
	BREAK 
	CASE 51
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_51
		++iDance_SpeechCounter 
	BREAK 
	CASE 52
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_52
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 53
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_53 
		++iDance_SpeechCounter
	BREAK
	CASE 54
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_54 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 55
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_55
		++iDance_SpeechCounter
	BREAK 
	CASE 56
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_HIGH_56
		iDance_SpeechCounter = 1
	BREAK
ENDSWITCH
RETURN
/*******************************************
			RETRIEVE SPEECH LOW
********************************************/
Dance_RetrieveSpeechLow:

SWITCH iDance_SpeechCounter

	DEFAULT
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_56
		iDance_SpeechCounter = 1
	BREAK
	CASE 1
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_01
		++iDance_SpeechCounter 
	BREAK 
	CASE 2
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_02
		++iDance_SpeechCounter 
	BREAK 
	CASE 3
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_03 
		++iDance_SpeechCounter
	BREAK
	CASE 4
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_04 
		++iDance_SpeechCounter
	BREAK 
	CASE 5
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_05
		++iDance_SpeechCounter
	BREAK 
	CASE 6
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_06
		++iDance_SpeechCounter
	BREAK 
	CASE 7
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_07
		++iDance_SpeechCounter
	BREAK 
	CASE 8
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_08
		++iDance_SpeechCounter
	BREAK 
	CASE 9
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_09
		++iDance_SpeechCounter
	BREAK 
	CASE 10
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_10
		++iDance_SpeechCounter
	BREAK 
	CASE 11 
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_11
		++iDance_SpeechCounter
	BREAK 
	CASE 12
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_12
		++iDance_SpeechCounter 
	BREAK 
	CASE 13
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_13 
		++iDance_SpeechCounter
	BREAK
	CASE 14
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_14 
		++iDance_SpeechCounter
	BREAK 
	CASE 15
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_15
		++iDance_SpeechCounter
	BREAK 
	CASE 16
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_16
		++iDance_SpeechCounter
	BREAK 
	CASE 17
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_17
		++iDance_SpeechCounter
	BREAK 
	CASE 18
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_18
		++iDance_SpeechCounter
	BREAK 
	CASE 19
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_19
		++iDance_SpeechCounter
	BREAK 
	CASE 20
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_20
		++iDance_SpeechCounter 
	BREAK 
	CASE 21
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_21
		++iDance_SpeechCounter 
	BREAK 
	CASE 22
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_22
		++iDance_SpeechCounter 
	BREAK 
	CASE 23
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_23 
		++iDance_SpeechCounter
	BREAK
	CASE 24
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_24 
		++iDance_SpeechCounter
	BREAK 
	CASE 25
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_25
		++iDance_SpeechCounter
	BREAK 
	CASE 26
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_26
		++iDance_SpeechCounter
	BREAK 
	CASE 27
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_27
		++iDance_SpeechCounter
	BREAK 
	CASE 28
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_28
		++iDance_SpeechCounter
	BREAK 
	CASE 29
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_29
		++iDance_SpeechCounter
	BREAK 
	CASE 30
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_30
		++iDance_SpeechCounter 
	BREAK 
	CASE 31
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_31
		++iDance_SpeechCounter 
	BREAK 
	CASE 32
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_32
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 33
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_33 
		++iDance_SpeechCounter
	BREAK
	CASE 34
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_34 
		++iDance_SpeechCounter
	BREAK 
	CASE 35
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_35
		++iDance_SpeechCounter
	BREAK 
	CASE 36
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_36
		++iDance_SpeechCounter
	BREAK 
	CASE 37
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_37
		++iDance_SpeechCounter
	BREAK 
	CASE 38
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_38
		++iDance_SpeechCounter
	BREAK 
	CASE 39
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_39
		++iDance_SpeechCounter
	BREAK 
	CASE 40
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_40
		++iDance_SpeechCounter 
	BREAK 
	CASE 41
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_41
		++iDance_SpeechCounter 
	BREAK 
	CASE 42
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_42
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 43
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_43 
		++iDance_SpeechCounter
	BREAK
	CASE 44
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_44 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 45
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_45
		++iDance_SpeechCounter
	BREAK 
	CASE 46
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_46
		++iDance_SpeechCounter
	BREAK 
	CASE 47
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_47
		++iDance_SpeechCounter
	BREAK 
	CASE 48
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_48
		++iDance_SpeechCounter
	BREAK 
	CASE 49
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_49
		++iDance_SpeechCounter
	BREAK 
	CASE 50
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_50
		++iDance_SpeechCounter 
	BREAK 
	CASE 51
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_51
		++iDance_SpeechCounter 
	BREAK 
	CASE 52
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_52
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 53
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_53 
		++iDance_SpeechCounter
	BREAK
	CASE 54
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_54 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 55
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_55
		++iDance_SpeechCounter
	BREAK 
	CASE 56
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_LOW_56
		iDance_SpeechCounter = 1
	BREAK
ENDSWITCH
RETURN
/*******************************************
			RETRIEVE SPEECH MED
********************************************/
Dance_RetrieveSpeechMed:

SWITCH iDance_SpeechCounter

	DEFAULT
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_56
		iDance_SpeechCounter = 1
	BREAK
	CASE 1
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_01
		++iDance_SpeechCounter 
	BREAK 
	CASE 2
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_02
		++iDance_SpeechCounter 
	BREAK 
	CASE 3
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_03 
		++iDance_SpeechCounter
	BREAK
	CASE 4
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_04 
		++iDance_SpeechCounter
	BREAK 
	CASE 5
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_05
		++iDance_SpeechCounter
	BREAK 
	CASE 6
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_06
		++iDance_SpeechCounter
	BREAK 
	CASE 7
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_07
		++iDance_SpeechCounter
	BREAK 
	CASE 8
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_08
		++iDance_SpeechCounter
	BREAK 
	CASE 9
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_09
		++iDance_SpeechCounter
	BREAK 
	CASE 10
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_10
		++iDance_SpeechCounter
	BREAK 
	CASE 11 
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_11
		++iDance_SpeechCounter
	BREAK 
	CASE 12
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_12
		++iDance_SpeechCounter 
	BREAK 
	CASE 13
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_13 
		++iDance_SpeechCounter
	BREAK
	CASE 14
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_14 
		++iDance_SpeechCounter
	BREAK 
	CASE 15
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_15
		++iDance_SpeechCounter
	BREAK 
	CASE 16
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_16
		++iDance_SpeechCounter
	BREAK 
	CASE 17
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_17
		++iDance_SpeechCounter
	BREAK 
	CASE 18
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_18
		++iDance_SpeechCounter
	BREAK 
	CASE 19
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_19
		++iDance_SpeechCounter
	BREAK 
	CASE 20
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_20
		++iDance_SpeechCounter 
	BREAK 
	CASE 21
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_21
		++iDance_SpeechCounter 
	BREAK 
	CASE 22
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_22
		++iDance_SpeechCounter 
	BREAK 
	CASE 23
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_23 
		++iDance_SpeechCounter
	BREAK
	CASE 24
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_24 
		++iDance_SpeechCounter
	BREAK 
	CASE 25
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_25
		++iDance_SpeechCounter
	BREAK 
	CASE 26
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_26
		++iDance_SpeechCounter
	BREAK 
	CASE 27
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_27
		++iDance_SpeechCounter
	BREAK 
	CASE 28
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_28
		++iDance_SpeechCounter
	BREAK 
	CASE 29
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_29
		++iDance_SpeechCounter
	BREAK 
	CASE 30
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_30
		++iDance_SpeechCounter 
	BREAK 
	CASE 31
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_31
		++iDance_SpeechCounter 
	BREAK 
	CASE 32
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_32
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 33
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_33 
		++iDance_SpeechCounter
	BREAK
	CASE 34
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_34 
		++iDance_SpeechCounter
	BREAK 
	CASE 35
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_35
		++iDance_SpeechCounter
	BREAK 
	CASE 36
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_36
		++iDance_SpeechCounter
	BREAK 
	CASE 37
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_37
		++iDance_SpeechCounter
	BREAK 
	CASE 38
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_38
		++iDance_SpeechCounter
	BREAK 
	CASE 39
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_39
		++iDance_SpeechCounter
	BREAK 
	CASE 40
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_40
		++iDance_SpeechCounter 
	BREAK 
	CASE 41
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_41
		++iDance_SpeechCounter 
	BREAK 
	CASE 42
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_42
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 43
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_43 
		++iDance_SpeechCounter
	BREAK
	CASE 44
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_44 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 45
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_45
		++iDance_SpeechCounter
	BREAK 
	CASE 46
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_46
		++iDance_SpeechCounter
	BREAK 
	CASE 47
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_47
		++iDance_SpeechCounter
	BREAK 
	CASE 48
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_48
		++iDance_SpeechCounter
	BREAK 
	CASE 49
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_49
		++iDance_SpeechCounter
	BREAK 
	CASE 50
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_50
		++iDance_SpeechCounter 
	BREAK 
	CASE 51
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_51
		++iDance_SpeechCounter 
	BREAK 
	CASE 52
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_52
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 53
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_53 
		++iDance_SpeechCounter
	BREAK
	CASE 54
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_54 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 55
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_55
		++iDance_SpeechCounter
	BREAK 
	CASE 56
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_MED_56
		iDance_SpeechCounter = 1
	BREAK
ENDSWITCH
RETURN
/*******************************************
			RETRIEVE SPEECH NOT
********************************************/
Dance_RetrieveSpeechNot:

SWITCH iDance_SpeechCounter

	DEFAULT
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_56
		iDance_SpeechCounter = 1
	BREAK
	CASE 1
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_01
		++iDance_SpeechCounter 
	BREAK 
	CASE 2
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_02
		++iDance_SpeechCounter 
	BREAK 
	CASE 3
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_03 
		++iDance_SpeechCounter
	BREAK
	CASE 4
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_04 
		++iDance_SpeechCounter
	BREAK 
	CASE 5
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_05
		++iDance_SpeechCounter
	BREAK 
	CASE 6
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_06
		++iDance_SpeechCounter
	BREAK 
	CASE 7
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_07
		++iDance_SpeechCounter
	BREAK 
	CASE 8
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_08
		++iDance_SpeechCounter
	BREAK 
	CASE 9
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_09
		++iDance_SpeechCounter
	BREAK 
	CASE 10
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_10
		++iDance_SpeechCounter
	BREAK 
	CASE 11 
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_11
		++iDance_SpeechCounter
	BREAK 
	CASE 12
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_12
		++iDance_SpeechCounter 
	BREAK 
	CASE 13
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_13 
		++iDance_SpeechCounter
	BREAK
	CASE 14
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_14 
		++iDance_SpeechCounter
	BREAK 
	CASE 15
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_15
		++iDance_SpeechCounter
	BREAK 
	CASE 16
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_16
		++iDance_SpeechCounter
	BREAK 
	CASE 17
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_17
		++iDance_SpeechCounter
	BREAK 
	CASE 18
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_18
		++iDance_SpeechCounter
	BREAK 
	CASE 19
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_19
		++iDance_SpeechCounter
	BREAK 
	CASE 20
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_20
		++iDance_SpeechCounter 
	BREAK 
	CASE 21
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_21
		++iDance_SpeechCounter 
	BREAK 
	CASE 22
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_22
		++iDance_SpeechCounter 
	BREAK 
	CASE 23
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_23 
		++iDance_SpeechCounter
	BREAK
	CASE 24
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_24 
		++iDance_SpeechCounter
	BREAK 
	CASE 25
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_25
		++iDance_SpeechCounter
	BREAK 
	CASE 26
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_26
		++iDance_SpeechCounter
	BREAK 
	CASE 27
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_27
		++iDance_SpeechCounter
	BREAK 
	CASE 28
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_28
		++iDance_SpeechCounter
	BREAK 
	CASE 29
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_29
		++iDance_SpeechCounter
	BREAK 
	CASE 30
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_30
		++iDance_SpeechCounter 
	BREAK 
	CASE 31
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_31
		++iDance_SpeechCounter 
	BREAK 
	CASE 32
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_32
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 33
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_33 
		++iDance_SpeechCounter
	BREAK
	CASE 34
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_34 
		++iDance_SpeechCounter
	BREAK 
	CASE 35
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_35
		++iDance_SpeechCounter
	BREAK 
	CASE 36
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_36
		++iDance_SpeechCounter
	BREAK 
	CASE 37
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_37
		++iDance_SpeechCounter
	BREAK 
	CASE 38
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_38
		++iDance_SpeechCounter
	BREAK 
	CASE 39
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_39
		++iDance_SpeechCounter
	BREAK 
	CASE 40
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_40
		++iDance_SpeechCounter 
	BREAK 
	CASE 41
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_41
		++iDance_SpeechCounter 
	BREAK 
	CASE 42
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_42
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 43
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_43 
		++iDance_SpeechCounter
	BREAK
	CASE 44
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_44 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 45
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_45
		++iDance_SpeechCounter
	BREAK 
	CASE 46
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_46
		++iDance_SpeechCounter
	BREAK 
	CASE 47
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_47
		++iDance_SpeechCounter
	BREAK 
	CASE 48
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_48
		++iDance_SpeechCounter
	BREAK 
	CASE 49
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_49
		++iDance_SpeechCounter
	BREAK 
	CASE 50
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_50
		++iDance_SpeechCounter 
	BREAK 
	CASE 51
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_51
		++iDance_SpeechCounter 
	BREAK 
	CASE 52
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_52
		++iDance_SpeechCounter 				   
	BREAK 
	CASE 53
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_53 
		++iDance_SpeechCounter
	BREAK
	CASE 54
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_54 
		++iDance_SpeechCounter				   
	BREAK 
	CASE 55
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_55
		++iDance_SpeechCounter
	BREAK 
	CASE 56
		LOAD_MISSION_AUDIO 4 SOUND_DANCE_NOT_56
		iDance_SpeechCounter = 1
	BREAK
ENDSWITCH
RETURN
/*******************************************
				CLEAN UP 
********************************************/
Dance_CleanUp:

	//--- Request  the Beat Display to terminate
	bd_terminate_script = 1
	//--- Housekeeping

	iTerminateDanceMiniGame = 0 	
	REMOVE_ANIMATION $txtCURR_DANCE_BLOCK
	CLEAR_PRINTS 	
	IF NOT IS_CHAR_DEAD iPartner
		IF NOT IS_CHAR_MODEL iPartner GANGRL3
		AND NOT IS_CHAR_MODEL iPartner MECGRL3
		AND NOT IS_CHAR_MODEL iPartner GUNGRL3
		AND NOT IS_CHAR_MODEL iPartner COPGRL3
		AND NOT IS_CHAR_MODEL iPartner NURGRL3
		AND NOT IS_CHAR_MODEL iPartner CROGRL3
			GET_CHAR_MODEL iPartner iTemp			
			MARK_MODEL_AS_NO_LONGER_NEEDED iTemp 
			DELETE_CHAR iPartner
		ENDIF
	ENDIF

	//--- JUST TO MAKE SURE...
	GET_BEAT_TRACK_STATUS iTemp 
	IF iTemp = CUTSCENE_TRACK_PLAYING
		STOP_BEAT_TRACK
	ENDIF

	//--- reset all the camera vars
	REPEAT 5 iTemp 
		fDanceCameraX[iTemp] = 0.0
		fDanceCameraY[iTemp] = 0.0
		fDanceCameraZ[iTemp] = 0.0
		fDanceTargetX[iTemp] = 0.0
		fDanceTargetY[iTemp] = 0.0
		fDanceTargetZ[iTemp] = 0.0
	ENDREPEAT

	//--- Reset the text commands
	FORCE_BIG_MESSAGE_AND_COUNTER FALSE
	USE_TEXT_COMMANDS FALSE

	//--- Update the dance score stat
	IF iDanceScore >= 0
		SET_INT_STAT LATEST_DANCE_SCORE iDanceScore
	ENDIF

	CLEAR_MISSION_AUDIO 4

	//--- Give control back to the player
	SET_PLAYER_CONTROL player1 ON

	CLEAR_BIT iDanceReport DANCE_MINIGAME_RUNNING
	SET_MINIGAME_IN_PROGRESS FALSE
	
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
			GET STICK POSITION
********************************************/
Dance_get_stick_position:

	GET_POSITION_OF_ANALOGUE_STICKS PAD1 d_lstickx d_lsticky d_rstickx d_rsticky

	d_temp_float =# d_lstickx
	d_vec_x = d_temp_float

	d_temp_float =# d_lsticky
	d_vec_y = d_temp_float

	GET_DISTANCE_BETWEEN_COORDS_2D 0.0 0.0 d_vec_x d_vec_y d_temp_float
	
	d_player_stick_position = 0

	IF d_temp_float > 64.0

		GET_ANGLE_BETWEEN_2D_VECTORS d_vec_x d_vec_y 0.0 -1.0 d_temp_float
		
		// must be up
		IF d_temp_float < 15.0 
			d_player_stick_position = DANCE_STKUP // up
		ELSE
			IF d_temp_float < 75.0 
				IF d_lstickx > 0
					d_player_stick_position = DANCE_STKUR // right and up
				ELSE
					d_player_stick_position = DANCE_STKUL // left and up
				ENDIF 
			ELSE
				IF d_temp_float < 105.0 
					IF d_lstickx > 0
						d_player_stick_position = DANCE_STKRGHT // right 
					ELSE
						d_player_stick_position = DANCE_STKLEFT // left 
					ENDIF
				ELSE
					IF d_temp_float < 165.0 
						IF d_lstickx > 0
							d_player_stick_position = DANCE_STKDR // right down 
						ELSE
							d_player_stick_position = DANCE_STKDL // left down
						ENDIF 	
					ELSE
						// must be down	
						d_player_stick_position = DANCE_STKDW
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN
/*******************************************
				RUN DEBUG
********************************************/
Dance_Debug:
	WRITE_DEBUG $txtCURR_DANCE_MOVE
	GET_BEAT_PROXIMITY 1 iTemp iTemp iTemp // The NEXT beat			
	WRITE_DEBUG_WITH_INT NEXT_BEAT_NUMBER iTemp 
RETURN
}
MISSION_END
