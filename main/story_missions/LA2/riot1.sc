MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Riot 1 ******************************************
// **************************************** Riot *******************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME riot1
// Mission start stuff
GOSUB mission_start_riot1
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_riot1_failed
ENDIF
GOSUB mission_cleanup_riot1
MISSION_END
{
// Variables for mission

//people
VAR_INT r1_sweets_car r1_cesars_car r1_mothership

//blips
LVAR_INT r1_control_blip

//flags
LVAR_INT r1_goals r1_control_flag r1_skip_cutscene_flag r1_deathcheck_flag
LVAR_INT r1_car_check_flag 

//speech
LVAR_INT r1_speech_goals r1_speech_control_flag r1_speech_flag r1_random_last_label 
LVAR_TEXT_LABEL r1_print_label[15] 
LVAR_INT r1_audio_label[15] 
LVAR_INT r1_last_label 
LVAR_INT r1_slot1 r1_slot2 r1_slot_load r1_play_which_slot
LVAR_INT r1_storing_speech_goals_number r1_storing_speech_control_number


//coords

//sequences/decision makers/threat lists/attractors/groups
VAR_INT r1_empty_decision_maker r1_seq

// **************************************** Mission Start **********************************
mission_start_riot1:
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1
LOAD_MISSION_TEXT RIOT1
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
r1_goals = 0 
r1_control_flag = 0 
r1_skip_cutscene_flag = 0 
r1_deathcheck_flag = 0 

r1_car_check_flag = 0

r1_speech_goals = 0
r1_speech_control_flag = 0
r1_speech_flag = 0
r1_random_last_label = 0
r1_last_label = 0
r1_slot1 = 0
r1_slot2 = 0
r1_slot_load = 0
r1_play_which_slot = 0
r1_storing_speech_goals_number = 0 
r1_storing_speech_control_number = 0

// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 5
LOAD_CUTSCENE RIOT_1A
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
START_CUTSCENE
DO_FADE 1000 FADE_IN
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
SET_PLAYER_CONTROL player1 OFF
DO_FADE 0 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
CLEAR_CUTSCENE
IF NOT WAS_CUTSCENE_SKIPPED
	LOAD_CUTSCENE RIOT_1B
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	START_CUTSCENE
	DO_FADE 1000 FADE_IN
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE
	SET_PLAYER_CONTROL player1 OFF
	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	CLEAR_CUTSCENE
ENDIF
SET_AREA_VISIBLE 0
MAKE_PLAYER_GANG_REAPPEAR
SET_PLAYER_CONTROL player1 OFF
// *****************************************END OF CUTSCENE*******************************
SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------
SET_LA_RIOTS ON
SET_INT_STAT STARTED_RIOT1 1

REQUEST_MODEL GREENWOO 
REQUEST_MODEL SAVANNA 
REQUEST_MODEL CAMPER 
LOAD_SPECIAL_CHARACTER 1 sweet
LOAD_ALL_MODELS_NOW 

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY r1_empty_decision_maker

LOAD_SCENE 1254.68 -789.39 91.03 

//creating truth's car
CLEAR_AREA 1242.9 -805.4 82.9 20.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR CAMPER OUTTHERE
CREATE_CAR CAMPER 1242.9 -805.4 82.9 r1_mothership
SET_CAR_HEADING r1_mothership 0.0 
CHANGE_CAR_COLOUR r1_mothership 1 1 
GIVE_VEHICLE_PAINTJOB r1_mothership 0
SET_CAN_RESPRAY_CAR r1_mothership FALSE

//creating cesar's car
CLEAR_AREA 1249.1 -804.9 82.9 5.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA &_LVA4L__ 
CREATE_CAR SAVANNA 1249.1 -804.9 82.9 r1_cesars_car
SET_CAR_HEADING r1_cesars_car 177.7 
CHANGE_CAR_COLOUR r1_cesars_car 3 3 

//sweets car
CLEAR_AREA 1254.3 -806.6 82.9 5.0 TRUE 
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO GROVE4L_
CREATE_CAR GREENWOO 1254.3 -806.6 82.9 r1_sweets_car
CHANGE_CAR_COLOUR r1_sweets_car 59 34 
SET_CAR_HEADING r1_sweets_car 177.7

//player
CLEAR_AREA 1258.2 -814.4 83.1 60.0 TRUE 
SET_CHAR_COORDINATES scplayer 1258.2 -814.4 83.1 
SET_CHAR_HEADING scplayer 40.0

//sweet
CLEAR_AREA 1257.6 -816.6 83.1 5.0 TRUE 
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 1257.6 -816.6 83.1 sweet
SET_CHAR_HEADING sweet 39.9
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE
SET_CHAR_NEVER_TARGETTED sweet TRUE  
SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
SET_CHAR_DECISION_MAKER sweet r1_empty_decision_maker
SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE 

OPEN_SEQUENCE_TASK r1_seq
	TASK_GO_STRAIGHT_TO_COORD -1 1252.4 -810.3 83.1 PEDMOVE_WALK -1
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER -1 r1_sweets_car -1 0
CLOSE_SEQUENCE_TASK r1_seq
PERFORM_SEQUENCE_TASK sweet r1_seq
CLEAR_SEQUENCE_TASK r1_seq

//starting intro scripted cut of player, smoke and sweet getting into the car
SWITCH_WIDESCREEN ON 
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

SET_FIXED_CAMERA_POSITION 1257.8 -815.0 84.7 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 1246.3 -802.5 82.7 JUMP_CUT
SHUT_ALL_CHARS_UP TRUE

ENABLE_AMBIENT_CRIME FALSE


mission_riot1_loop:
WAIT 0
	 
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_riot1_passed  
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB r1_death_checks
	IF r1_deathcheck_flag = 1
		GOTO mission_riot1_failed
	ENDIF

	IF r1_goals = 0 
		IF r1_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			r1_speech_goals = 0
		
			//player entering car
			SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
			TASK_ENTER_CAR_AS_DRIVER scplayer r1_sweets_car -1 
		
			//sweet entering car
			OPEN_SEQUENCE_TASK r1_seq
				TASK_GO_STRAIGHT_TO_COORD -1 1252.4 -810.3 83.1 PEDMOVE_WALK -1
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 r1_sweets_car -1 0
			CLOSE_SEQUENCE_TASK r1_seq
			PERFORM_SEQUENCE_TASK sweet r1_seq
			CLEAR_SEQUENCE_TASK r1_seq
		   
		    DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS			
			    WAIT 0
			ENDWHILE
			GOSUB r1_death_checks
			IF r1_deathcheck_flag = 1
				GOTO mission_riot1_failed
			ENDIF
		
			//Ok, so what's the plan?
			r1_speech_goals = 1
			r1_speech_control_flag = 0
			GOSUB r1_dialogue_setup 
			r1_control_flag = 1

			r1_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START 
		ENDIF

		IF r1_control_flag = 1
			IF r1_speech_goals = 0 
				IF IS_CHAR_IN_CAR scplayer r1_sweets_car 
					IF IS_CHAR_IN_CAR sweet r1_sweets_car 
						r1_skip_cutscene_flag = 0
						SKIP_CUTSCENE_END
						GOSUB r1_death_checks
						IF r1_deathcheck_flag = 1
							GOTO mission_riot1_failed
						ENDIF
						
						IF r1_skip_cutscene_flag = 1
							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
							r1_speech_goals = 0
							
							DO_FADE 500 FADE_OUT	
							WHILE GET_FADING_STATUS	
							   WAIT 0
							ENDWHILE 
							GOSUB r1_death_checks
							IF r1_deathcheck_flag = 1
								GOTO mission_riot1_failed
							ENDIF
						
							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
							WARP_CHAR_INTO_CAR scplayer r1_sweets_car 

							CLEAR_CHAR_TASKS_IMMEDIATELY sweet 
							WARP_CHAR_INTO_CAR_AS_PASSENGER sweet r1_sweets_car 0 
						ENDIF 

						r1_car_check_flag = 1
						SHUT_ALL_CHARS_UP FALSE
						ADD_BLIP_FOR_COORD 2505.4 -1670.6 12.1 r1_control_blip

						HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
						SWITCH_WIDESCREEN OFF
						SET_CAMERA_BEHIND_PLAYER	
						RESTORE_CAMERA_JUMPCUT
						SET_PLAYER_CONTROL player1 ON
						PRINT_NOW ( R1_01 ) 7000 1 //Drive back to Grove.
						timerb = 0

						//if cutscene has been skipped
						IF r1_skip_cutscene_flag = 1
							DO_FADE 500 FADE_IN	
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 
							GOSUB r1_death_checks
							IF r1_deathcheck_flag = 1
								GOTO mission_riot1_failed
							ENDIF
						ENDIF
						r1_goals = 1

					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//waiting for player to reach sweets house 
	IF r1_goals = 1
		////////////DEBUG//////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
				IF IS_CHAR_IN_CAR scplayer r1_sweets_car 
					SET_CAR_COORDINATES r1_sweets_car 2495.7 -1666.3 12.1
					SET_CAR_HEADING r1_sweets_car 254.2 
				ENDIF
			ENDIF
		////////////DEBUG//////////////////

		
		
		// PLAYING RANDOM SPEECH
		IF r1_speech_flag = 0
			IF r1_speech_goals = 0
				IF timerb > 10000
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
					// There ain't no justice, man.
					r1_speech_goals = 2
					r1_speech_control_flag = 0
					GOSUB r1_dialogue_setup 
					r1_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF r1_speech_flag = 1
			IF r1_speech_goals = 0
				IF timerb > 7000
					// So who's the weird brit?
					r1_speech_goals = 3
					r1_speech_control_flag = 0
					GOSUB r1_dialogue_setup 
					r1_speech_flag = 2
				ENDIF
			ENDIF
		ENDIF
	
		IF r1_speech_flag = 2
			IF IS_CHAR_IN_CAR scplayer r1_sweets_car
				IF IS_CHAR_IN_AREA_2D scplayer 2955.5 -1240.1 1192.2 -1855.3 FALSE 
					IF r1_speech_goals = 0
						IF timerb > 7000
							MARK_CAR_AS_NO_LONGER_NEEDED r1_mothership
							MARK_CAR_AS_NO_LONGER_NEEDED r1_cesars_car
							// Shit, look at this place, even old ladies robbing shit!
							r1_speech_goals = 4
							r1_speech_control_flag = 0
							GOSUB r1_dialogue_setup 
							r1_speech_flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF r1_speech_flag = 3
			IF IS_CHAR_IN_CAR scplayer r1_sweets_car
				IF IS_CHAR_IN_AREA_2D scplayer 2955.5 -1240.1 1192.2 -1855.3 FALSE 
					IF r1_speech_goals = 0
						IF timerb > 7000
							// Shit, man, people real mad!
							r1_speech_goals = 5
							r1_speech_control_flag = 0
							GOSUB r1_dialogue_setup 
							r1_speech_flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF r1_speech_flag = 4
			IF IS_CHAR_IN_CAR scplayer r1_sweets_car
				IF IS_CHAR_IN_AREA_2D scplayer 2955.5 -1240.1 1192.2 -1855.3 FALSE 
					IF r1_speech_goals = 0
						IF timerb > 7000
							// Everything's burning.
							r1_speech_goals = 6
							r1_speech_control_flag = 0
							GOSUB r1_dialogue_setup 
							r1_speech_flag = 5
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF r1_speech_flag = 5 	
			IF r1_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
				r1_speech_flag = 6
			ENDIF
		ENDIF


		// MAIN BIT 
		IF IS_CHAR_IN_CAR scplayer r1_sweets_car
			IF IS_CHAR_IN_CAR sweet r1_sweets_car  
				IF LOCATE_CHAR_IN_CAR_3D scplayer 2505.4 -1670.6 12.1 4.0 4.0 4.0 TRUE
					SET_PLAYER_CONTROL player1 OFF
					r1_control_flag = 0
					r1_goals = 2	
				ENDIF
			ENDIF
		ENDIF   
	ENDIF 

	//end cutscene
	IF r1_goals = 2
		IF r1_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			r1_speech_goals = 0
			
			SWITCH_WIDESCREEN ON
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
			SHUT_ALL_CHARS_UP TRUE

			//Shit don't look too bad...
			r1_speech_goals = 7
			r1_speech_control_flag = 0
			GOSUB r1_dialogue_setup 
			r1_control_flag = 1
			
			r1_control_flag = 1
		ENDIF


		IF r1_control_flag = 1 
			IF r1_speech_goals = 0
				OPEN_SEQUENCE_TASK r1_seq
					TASK_LEAVE_ANY_CAR -1 
					TASK_GO_STRAIGHT_TO_COORD -1 2518.9 -1678.2 13.5 PEDMOVE_WALK -1
				CLOSE_SEQUENCE_TASK r1_seq
				PERFORM_SEQUENCE_TASK sweet r1_seq
				CLEAR_SEQUENCE_TASK r1_seq
				timera = 0 
				r1_control_flag = 2
			ENDIF
		ENDIF
		
		IF r1_control_flag = 2
			IF timera > 4000
				DO_FADE 500 FADE_OUT		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 

				DELETE_CHAR sweet 

				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER	
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON

				SHUT_ALL_CHARS_UP FALSE
				
				DO_FADE 500 FADE_IN		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
				GOTO mission_riot1_passed
			ENDIF
		ENDIF
	ENDIF


	//blippage
	IF r1_goals > 0
		GOSUB r1_blippage
	ENDIF

	//dialogue
	GOSUB r1_overall_dialogue

GOTO mission_riot1_loop 


// **************************************** Mission riot1 failed ************************
mission_riot1_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

// **************************************** mission riot1 passed *************************
mission_riot1_passed:
flag_riot_mission_counter ++
SET_INT_STAT PASSED_RIOT1 1
REGISTER_MISSION_PASSED ( RIOT_1 ) //Used in the stats 
PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //mission passed
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL player1
REMOVE_BLIP mansion_contact_blip
REMOVE_BLIP sweet_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
PLAYER_MADE_PROGRESS 1
RETURN
		

// ********************************** mission cleanup ************************************
mission_cleanup_riot1:
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
SHUT_ALL_CHARS_UP FALSE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER 
REMOVE_CHAR_ELEGANTLY sweet
UNLOAD_SPECIAL_CHARACTER 1 
REMOVE_BLIP r1_control_blip
ENABLE_AMBIENT_CRIME TRUE
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////                      GOSUBS                             ///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD sweet
	CLEAR_PRINTS
	PRINT_NOW ( R1_02 ) 7000 1 //You killed Sweet!	
	r1_deathcheck_flag = 1	
ENDIF

IF IS_CAR_DEAD r1_sweets_car
	CLEAR_PRINTS
	PRINT_NOW ( R1_04 ) 7000 1 //Sweet's car has been destroyed!	
	r1_deathcheck_flag = 1	
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_blippage:///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//sorting the blippage
IF r1_car_check_flag = 0
	IF IS_CHAR_IN_CAR scplayer r1_sweets_car
		CLEAR_THIS_PRINT R1_03 
		REMOVE_BLIP r1_control_blip
		ADD_BLIP_FOR_COORD 2505.4 -1670.6 12.1 r1_control_blip	// sweets house
		r1_car_check_flag = 1
	ENDIF
ENDIF

IF r1_car_check_flag = 1 
	IF NOT IS_CHAR_IN_CAR scplayer r1_sweets_car
		REMOVE_BLIP r1_control_blip
		ADD_BLIP_FOR_CAR r1_sweets_car r1_control_blip
		SET_BLIP_AS_FRIENDLY r1_control_blip TRUE
		r1_car_check_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_overall_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r1_goals = 1
	IF IS_CHAR_SITTING_IN_CAR scplayer r1_sweets_car
		IF r1_speech_goals = 2 //There ain't no justice, man.
		OR r1_speech_goals = 3 //So who's the weird brit?
		OR r1_speech_goals = 4 //Shit, look at this place, even old ladies robbing shit!
		OR r1_speech_goals = 5 //Shit, man, people real mad!
		OR r1_speech_goals = 6 //Put your foot down!
			IF r1_speech_control_flag < r1_last_label
				GOSUB r1_loading_dialogue
				GOSUB r1_playing_dialogue
				IF NOT IS_CHAR_DEAD sweet
					GOSUB r1_finishing_dialogue  
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag] 
					r1_slot1 = 0
					r1_slot2 = 0
				ENDIF
			ELSE
				timerb = 0
				r1_speech_goals = 0
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_IN_CAR scplayer r1_sweets_car 
		IF r1_speech_goals < 11 
			IF r1_speech_control_flag < r1_last_label 
				r1_speech_control_flag ++ 
			ENDIF
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS 
			CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag]
			r1_storing_speech_goals_number = r1_speech_goals
			r1_storing_speech_control_number = r1_speech_control_flag
			r1_speech_goals = 11
			GENERATE_RANDOM_INT_IN_RANGE 0 4 r1_speech_control_flag
			r1_random_last_label = r1_speech_control_flag + 1 
			GOSUB r1_dialogue_setup
		ENDIF
	ENDIF	

	IF r1_speech_goals = 11 //carl is out of car
		IF NOT IS_CHAR_IN_CAR scplayer r1_sweets_car
			IF r1_speech_control_flag < r1_last_label
				GOSUB r1_loading_dialogue
				GOSUB r1_playing_dialogue
				IF r1_control_flag = 0 
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
						GOSUB r1_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag] 
						r1_slot1 = 0
						r1_slot2 = 0
					ENDIF
				ENDIF
				IF r1_control_flag = 1
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
					OR NOT IS_CHAR_DEAD mc_strap
						GOSUB r1_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag] 
						r1_slot1 = 0
						r1_slot2 = 0
					ENDIF
				ENDIF
			ELSE
				PRINT_NOW ( R1_03 ) 11000 1 //Get back in Sweet's car.
				r1_speech_goals = 12
			ENDIF
		ENDIF
		IF IS_CHAR_SITTING_IN_CAR scplayer r1_sweets_car
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag]
			r1_speech_goals = 13
			r1_speech_control_flag = 0
			CLEAR_PRINTS 
		ENDIF
	ENDIF

	IF r1_speech_goals = 12 //carl has been out of car and has returned
		IF IS_CHAR_SITTING_IN_CAR scplayer r1_sweets_car 
			r1_speech_goals = 13
			r1_speech_control_flag = 0
			CLEAR_PRINTS 
		ENDIF
	ENDIF

	IF r1_speech_goals = 13 //where player has returned to the car
		IF IS_CHAR_SITTING_IN_CAR scplayer r1_sweets_car 	
			timerb = 0 
			r1_speech_goals = r1_storing_speech_goals_number
			r1_speech_control_flag = r1_storing_speech_control_number
			GOSUB r1_dialogue_setup
			IF r1_storing_speech_goals_number = 0
				PRINT_NOW ( R1_01 ) 7000 1 //Drive back to Grove.
			ENDIF
		ENDIF
		IF NOT IS_CHAR_IN_CAR scplayer r1_sweets_car
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag]
			r1_speech_goals = 11
			GENERATE_RANDOM_INT_IN_RANGE 0 4 r1_speech_control_flag			
			r1_random_last_label = r1_speech_control_flag + 1 
			GOSUB r1_dialogue_setup
		ENDIF
	ENDIF

ENDIF

IF r1_speech_goals = 1 //cutscene at start 
OR r1_speech_goals = 7 //cutscene at end
	IF r1_speech_control_flag < r1_last_label
		GOSUB r1_loading_dialogue
		GOSUB r1_playing_dialogue
		GOSUB r1_finishing_dialogue  
	ELSE
		r1_speech_goals = 0
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_dialogue_setup://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r1_speech_goals = 1
	$r1_print_label[0] = &ROT1_AA // Shit's real fucked up, man.
	$r1_print_label[1] = &ROT1_AB // Yeah, but we gonna handle this ourselves.
	
	r1_audio_label[0] = SOUND_ROT1_AA 
	r1_audio_label[1] = SOUND_ROT1_AB 
	r1_last_label = 2 
ENDIF
IF r1_speech_goals = 2
	$r1_print_label[0] = &ROT1_BA // There ain't no justice, man.
	$r1_print_label[1] = &ROT1_BB // How does scum like Tenpennny stay on the streets?
	$r1_print_label[2] = &ROT1_BC // Man, I dunno, just the way shit stacked I guess.
	$r1_print_label[3] = &ROT1_BD // Man, this is fucked up. We should take that bastard down ourselves.
	$r1_print_label[4] = &ROT1_BE // And we will Sweet.

	r1_audio_label[0] = SOUND_ROT1_BA 
	r1_audio_label[1] = SOUND_ROT1_BB 
	r1_audio_label[2] = SOUND_ROT1_BC 
	r1_audio_label[3] = SOUND_ROT1_BD 
	r1_audio_label[4] = SOUND_ROT1_BE 
	r1_last_label = 5
ENDIF
IF r1_speech_goals = 3
	$r1_print_label[0] = &ROT1_EA // So who's the weird brit?
	$r1_print_label[1] = &ROT1_EB // What? Oh, Maccer!
	$r1_print_label[2] = &ROT1_EC // He got a little problem he can't control.
	$r1_print_label[3] = &ROT1_ED // What kind of problem?
	$r1_print_label[4] = &ROT1_EE // He can't, you know, giving himself a little bit.
	$r1_print_label[5] = &ROT1_EF // What, you mean he likes to consult Professor Hans Jerkov?
	$r1_print_label[6] = &ROT1_EG // Yeah, regularly.
	$r1_print_label[7] = &ROT1_EH // Spank the monkey?
	$r1_print_label[8] = &ROT1_EJ // Yeah!
	$r1_print_label[9] = &ROT1_EK // Take Palm-ela out?
	$r1_print_label[10] = &ROT1_EL // Quit it!
	$r1_print_label[11] = &ROT1_EM // Burp the worm?
	$r1_print_label[12] = &ROT1_EN // Enough, man!

	r1_audio_label[0] = SOUND_ROT1_EA
	r1_audio_label[1] = SOUND_ROT1_EB
	r1_audio_label[2] = SOUND_ROT1_EC
	r1_audio_label[3] = SOUND_ROT1_ED
	r1_audio_label[4] = SOUND_ROT1_EE
	r1_audio_label[5] = SOUND_ROT1_EF
	r1_audio_label[6] = SOUND_ROT1_EG
	r1_audio_label[7] = SOUND_ROT1_EH
	r1_audio_label[8] = SOUND_ROT1_EJ
	r1_audio_label[9] = SOUND_ROT1_EK
	r1_audio_label[10] = SOUND_ROT1_EL
	r1_audio_label[11] = SOUND_ROT1_EM
	r1_audio_label[12] = SOUND_ROT1_EN
	r1_last_label = 13
ENDIF
IF r1_speech_goals = 4
	$r1_print_label[0] = &ROT1_DA // Shit, look at this place, even old ladies robbing shit!
	$r1_print_label[1] = &ROT1_DB // I guess it's better than staying home and watching the shopping channel.
	$r1_print_label[2] = &ROT1_DC // Yeah, go out there and get yourself a bargain!
						  
	r1_audio_label[0] = SOUND_ROT1_DA
	r1_audio_label[1] = SOUND_ROT1_DB
	r1_audio_label[2] = SOUND_ROT1_DC
	r1_last_label = 3
ENDIF

IF r1_speech_goals = 5
	$r1_print_label[0] = &ROT1_FA // Shit, man, people real mad!
	$r1_print_label[1] = &ROT1_FB // Tenpenny's responsible for ALL of this!
	$r1_print_label[2] = &ROT1_FC // As if the ghetto ain't wrecked enough!
	$r1_print_label[3] = &ROT1_FD // Come tomorrow, most the Centrals gonna have new TVs.
	$r1_print_label[4] = &ROT1_FE // Look at them, like angry kids or some shit!
	$r1_print_label[5] = &ROT1_FF // They just tearing up their own neighbourhoods!
	$r1_print_label[6] = &ROT1_FG // Nothing good is gonna come of this.

	r1_audio_label[0] = SOUND_ROT1_FA
	r1_audio_label[1] = SOUND_ROT1_FB
	r1_audio_label[2] = SOUND_ROT1_FC
	r1_audio_label[3] = SOUND_ROT1_FD
	r1_audio_label[4] = SOUND_ROT1_FE
	r1_audio_label[5] = SOUND_ROT1_FF
	r1_audio_label[6] = SOUND_ROT1_FG
	r1_last_label = 7
ENDIF
IF r1_speech_goals = 6
	$r1_print_label[0] = &ROT1_GL // Everything's burning.					  
	$r1_print_label[1] = &ROT1_GA // Put your foot down!	
	$r1_print_label[2] = &ROT1_FH // Man, the ghetto's tearing itself apart!
	//$r1_print_label[2] = &ROT1_GB // They coming after us!	
	//$r1_print_label[2] = &ROT1_GC // Ain't this Ballas turf?	
	//$r1_print_label[3] = &ROT1_GD // Ain't this Vagos country?	
	//$r1_print_label[4] = &ROT1_GE // Back up, CJ, back up!	
	//$r1_print_label[5] = &ROT1_GF // Man, most these roads are blocked with shit!
	//$r1_print_label[6] = &ROT1_GG // The road's blocked! Back up!	
	//$r1_print_label[7] = &ROT1_GH // They've barricaded the road!	
	//$r1_print_label[8] = &ROT1_GJ // Stay away from the National Guard!
	//$r1_print_label[9] = &ROT1_GK // Stay away from those soldiers, CJ!
	//$r1_print_label[11] = &ROT1_GM // Can't see shit through this smoke!					  

	r1_audio_label[0] = SOUND_ROT1_GL
	r1_audio_label[1] = SOUND_ROT1_GA
	r1_audio_label[2] = SOUND_ROT1_FH
	//r1_audio_label[2] = SOUND_ROT1_GB
	//r1_audio_label[2] = SOUND_ROT1_GC
	//r1_audio_label[3] = SOUND_ROT1_GD
	//r1_audio_label[4] = SOUND_ROT1_GE
	//r1_audio_label[5] = SOUND_ROT1_GF
	//r1_audio_label[6] = SOUND_ROT1_GG
	//r1_audio_label[7] = SOUND_ROT1_GH
	//r1_audio_label[8] = SOUND_ROT1_GJ
	//r1_audio_label[9] = SOUND_ROT1_GK
	//r1_audio_label[11] = SOUND_ROT1_GM
	r1_last_label = 3
ENDIF

IF r1_speech_goals = 7
	//$r1_print_label[0] = &ROT1_HA // Shit don't look too bad...
	//$r1_print_label[1] = &ROT1_HB // It only takes one fool to spread this shit to the Grove.
	$r1_print_label[0] = &ROT1_HC // I'm gonna gather up some homies, and get the hood locked down.
	$r1_print_label[1] = &ROT1_HD // You need anything, give me a call.
	$r1_print_label[2] = &ROT1_HE // I'm gonna check out the city and see what I can see.
	$r1_print_label[3] = &ROT1_HF // You be careful out there, CJ.

	//r1_audio_label[0] = SOUND_ROT1_HA
	//r1_audio_label[1] = SOUND_ROT1_HB
	r1_audio_label[0] = SOUND_ROT1_HC
	r1_audio_label[1] = SOUND_ROT1_HD
	r1_audio_label[2] = SOUND_ROT1_HE
	r1_audio_label[3] = SOUND_ROT1_HF
	r1_last_label = 4
ENDIF

IF r1_speech_goals = 11
	$r1_print_label[0] = &SWE1_BG // CJ, GET IN!
	$r1_print_label[1] = &SWE1_BK // CJ, c'mon man, hop in.
	$r1_print_label[2] = &SWE1_BL // C'mon, let's roll, let's step on it.
	$r1_print_label[3] = &SWE1_BM // Get in, nigga!

	r1_audio_label[0] = SOUND_SWE1_BG
	r1_audio_label[1] = SOUND_SWE1_BK
	r1_audio_label[2] = SOUND_SWE1_BL
	r1_audio_label[3] = SOUND_SWE1_BM
 	r1_last_label = r1_random_last_label 
ENDIF

r1_slot_load = r1_speech_control_flag
r1_slot1 = 0
r1_slot2 = 0
r1_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_loading_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r1_slot_load < r1_last_label 
	//slot 1
	IF r1_slot1 = 0
		LOAD_MISSION_AUDIO 1 r1_audio_label[r1_slot_load]  
		r1_slot_load ++ 
		r1_slot1 = 1
	ENDIF

	//slot 2		    
	IF r1_slot2 = 0
		LOAD_MISSION_AUDIO 2 r1_audio_label[r1_slot_load]  
		r1_slot_load ++ 
		r1_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF r1_play_which_slot = 1 
	IF r1_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $r1_print_label[r1_speech_control_flag] ) 4500 1 
			r1_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF r1_play_which_slot = 2 
	IF r1_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $r1_print_label[r1_speech_control_flag] ) 4500 1 
			r1_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r1_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF r1_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag]
		r1_speech_control_flag ++		
		r1_play_which_slot = 2
		r1_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF r1_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $r1_print_label[r1_speech_control_flag]
		r1_speech_control_flag ++		
		r1_play_which_slot = 1
		r1_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////




}



