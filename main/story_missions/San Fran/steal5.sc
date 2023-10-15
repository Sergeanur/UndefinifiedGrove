MISSION_START
// *****************************************************************************************
// ***************************** steal5 **************************************************** 
// *****************************************************************************************
// *****************************************************************************************
// *******************************STINGER TRAP**********************************************
// *****************************************************************************************
SCRIPT_NAME steal5
// Mission start stuff
GOSUB mission_start_steal5
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_steal5_failed
ENDIF
GOSUB mission_cleanup_steal5
MISSION_END
{
// Variables for mission

//people
LVAR_INT s5_target_car s5_enemy s5_stinger s5_random_car s5_random_car2 s5_temp_car  

//blips
LVAR_INT s5_blip 

//flags
LVAR_INT s5_goals s5_control_flag s5_skip_cutscene_flag s5_deathcheck_flag  
LVAR_INT s5_is_plyr_in_car 
VAR_INT s5_stingers_left
LVAR_INT s5_controlling_woman  
LVAR_INT s5_trigger_stingers
LVAR_INT s5_no_plates_flag s5_no_plates
LVAR_INT s5_fucking_fudge_flag 
 

//speech
LVAR_INT s5_speech_goals s5_speech_flag s5_speech_control_flag s5_random_last_label 
LVAR_TEXT_LABEL s5_print_label[6] 
LVAR_INT s5_audio_label[6] 
LVAR_INT s5_last_label 
LVAR_INT s5_slot1 s5_slot2 s5_slot_load s5_play_which_slot
LVAR_INT s5_stinger_sounds

//coords
LVAR_FLOAT s5_stinger_x1 s5_stinger_x2 s5_stinger_y1 s5_stinger_y2
LVAR_FLOAT s5_x s5_y s5_z  
LVAR_FLOAT s5_player_x s5_player_y s5_player_z
 

//sequences/decision makers/threat lists/attractors/groups
LVAR_INT s5_seq


// ****************************************Mission Start************************************
mission_start_steal5:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT STEAL5
IF flag_player_on_mission = 0 
	CREATE_OBJECT temp_stinger2 -2025.8 169.2 27.7 s5_stinger
	ADD_BLIP_FOR_COORD -2025.8 169.2 27.7 s5_blip
ENDIF
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
s5_goals = 0
s5_control_flag = 0
s5_skip_cutscene_flag = 0 
s5_deathcheck_flag = 0 
s5_is_plyr_in_car = 0
s5_stingers_left = 3  
s5_controlling_woman = 0 
s5_trigger_stingers = 0
s5_no_plates_flag = 0
s5_no_plates = 0
s5_fucking_fudge_flag = 0

s5_stinger_x1 = 0.0 
s5_stinger_x2 = 0.0
s5_stinger_y1 = 0.0 
s5_stinger_y2 = 0.0
s5_x = 0.0 
s5_y = 0.0 
s5_z = 0.0
s5_player_x = 0.0 
s5_player_y = 0.0 
s5_player_z = 0.0

s5_speech_goals = 0 
s5_speech_flag = 0 
s5_speech_control_flag = 0 
s5_random_last_label = 0 
s5_last_label = 0 
s5_slot1 = 0
s5_slot2 = 0 
s5_slot_load = 0 
s5_play_which_slot = 0	
s5_stinger_sounds = 0


// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 1
SET_CHAR_AREA_VISIBLE scplayer 1
LOAD_SCENE -2031.0 149.0 29.0
LOAD_CUTSCENE STEAL_5
WHILE NOT HAS_CUTSCENE_LOADED
    WAIT 0
ENDWHILE
START_CUTSCENE
DO_FADE 1000 FADE_IN
WHILE NOT HAS_CUTSCENE_FINISHED
    WAIT 0
ENDWHILE
DO_FADE 0 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE
SET_AREA_VISIBLE 0
SET_CHAR_AREA_VISIBLE scplayer 0
CLEAR_CUTSCENE
SET_PLAYER_CONTROL player1 OFF
MAKE_PLAYER_GANG_REAPPEAR
// ****************************************END OF CUTSCENE**********************************
SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------
REQUEST_MODEL STRATUM 
REQUEST_MODEL TAMPA 
REQUEST_MODEL HFYRI 
REQUEST_MODEL temp_stinger2 
REQUEST_MODEL CELLPHONE

LOAD_MISSION_AUDIO 3 SOUND_BANK_STINGER
WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0 
ENDWHILE 

LOAD_ALL_MODELS_NOW

/*
CREATE_CAR TAMPA -2025.8 169.2 27.7 s5_temp_car
WARP_CHAR_INTO_CAR scplayer s5_temp_car

VAR_FLOAT s5_a s5_b s5_c 
VAR_INT s5_d 

s5_a = 0.0
s5_b = 0.0 
s5_c = 0.0
s5_d = 0

VIEW_FLOAT_VARIABLE s5_a s5_a 
VIEW_FLOAT_VARIABLE s5_b s5_b
VIEW_FLOAT_VARIABLE s5_c s5_c
VIEW_INTEGER_VARIABLE s5_d s5_d

SWITCH_WIDESCREEN OFF

SET_VEHICLE_CAMERA_TWEAK s5_temp_car 10.0 10.0 10.0

DO_FADE 0 FADE_IN
testy:
WAIT 0

	WHILE IS_BUTTON_PRESSED PAD1 DPADLEFT
		WAIT 0 
		s5_a = s5_a - 1.0
	ENDWHILE  
	WHILE IS_BUTTON_PRESSED PAD1 DPADRIGHT
		WAIT 0 
		s5_a = s5_a + 1.0
	ENDWHILE  

	WHILE IS_BUTTON_PRESSED PAD1 DPADDOWN
		WAIT 0 
		s5_b = s5_b + 1.0
	ENDWHILE  
	WHILE IS_BUTTON_PRESSED PAD1 DPADUP
		WAIT 0 
		s5_b = s5_b - 1.0
	ENDWHILE  

	WHILE IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1 
		WAIT 0 
		s5_c = s5_c - 1.0
	ENDWHILE  
	WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
		WAIT 0 
		s5_c = s5_c + 1.0
	ENDWHILE  
	
	WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK 
		WAIT 0 
		IF s5_d = 0
			RESET_VEHICLE_CAMERA_TWEAK
			s5_d = 1
		ENDIF 
	ENDWHILE 

	IF NOT IS_CAR_DEAD s5_temp_car
		IF s5_d = 1 
			SET_VEHICLE_CAMERA_TWEAK s5_temp_car s5_a s5_b s5_c
			s5_d = 0
		ENDIF
	ENDIF

GOTO testy
*/

CLEAR_AREA -2025.8 169.2 27.7 5.0 TRUE
s5_no_plates = TAMPA
GOSUB s5_my_number_plates
CREATE_CAR TAMPA -2025.8 169.2 27.7 s5_temp_car

CLEAR_AREA -1913.5 -593.8 36.9 5.0 TRUE
s5_no_plates = STRATUM
GOSUB s5_my_number_plates
CREATE_CAR STRATUM -1913.5 -593.8 36.9 s5_target_car
//LOAD_PATH_NODES_IN_AREA -1286.0 -639.5 -3447.2 1835.8  
SET_CAR_HEADING s5_target_car 180.0
SET_CAN_RESPRAY_CAR s5_target_car FALSE
SET_CAR_CAN_GO_AGAINST_TRAFFIC s5_target_car TRUE
LOCK_CAR_DOORS s5_target_car CARLOCK_LOCKOUT_PLAYER_ONLY
SET_LOAD_COLLISION_FOR_CAR_FLAG s5_target_car FALSE
ADD_BLIP_FOR_CAR s5_target_car s5_blip
SET_BLIP_AS_FRIENDLY s5_blip FALSE
s5_is_plyr_in_car = 1
SET_CAR_DRIVING_STYLE s5_target_car DRIVINGMODE_AVOIDCARS 
SET_CAR_ONLY_DAMAGED_BY_PLAYER s5_target_car TRUE 
ADD_STUCK_CAR_CHECK_WITH_WARP s5_target_car 0.5 4000 TRUE TRUE TRUE 7
CREATE_CHAR_INSIDE_CAR s5_target_car PEDTYPE_CIVFEMALE HFYRI s5_enemy
SET_LOAD_COLLISION_FOR_CHAR_FLAG s5_enemy TRUE
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE s5_enemy FALSE

DISPLAY_ONSCREEN_COUNTER_WITH_STRING s5_stingers_left COUNTER_DISPLAY_NUMBER STE5_04 //Stingers Remaining: 

PRINT_NOW ( STE5_12 ) 7000 1 //Get in the car modded to use stingers.

CLEAR_AREA -2028.6 162.9 27.8 1.0 TRUE
SET_CHAR_COORDINATES scplayer -2028.6 162.9 27.8
SET_CHAR_HEADING scplayer 336.6
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON 

CHANGE_GARAGE_TYPE amumis GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE

DO_FADE 500 FADE_IN


/*
//////////////// DEBUG //////////////////////////
s5_goals = 10
IF NOT IS_CAR_DEAD s5_temp_car
	WARP_CHAR_INTO_CAR scplayer s5_temp_car
ENDIF  
//////////////// DEBUG //////////////////////////
*/
timerb = 0

mission_steal5_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_steal5_passed  
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB s5_death_checks
	IF s5_deathcheck_flag = 1
		GOTO mission_steal5_failed
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////MAKING THE STINGER WORK/////////////////////////////////////////////////////////////////////////
	IF s5_goals = 0
		IF s5_trigger_stingers = 1
			GOSUB s5_burst_tyres
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Waiting for player to burst the tyres of the target car/////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF s5_goals = 0

		////////////////////////// blippage //////////////////////
		IF s5_is_plyr_in_car = 1 
			IF NOT IS_CHAR_IN_CAR scplayer s5_temp_car
				REMOVE_BLIP s5_blip
				ADD_BLIP_FOR_CAR s5_temp_car s5_blip
				SET_BLIP_AS_FRIENDLY s5_blip TRUE
				IF timerb > 14000
					PRINT_NOW ( STE5_06 ) 4000 1 //You need to be in the car modded to use stingers.
				ENDIF
				CLEAR_HELP
				s5_is_plyr_in_car = 0
			ENDIF
		ENDIF	
		IF s5_is_plyr_in_car = 0 
			IF IS_CHAR_IN_CAR scplayer s5_temp_car 		
				REMOVE_BLIP s5_blip
				ADD_BLIP_FOR_CAR s5_target_car s5_blip
				SET_BLIP_AS_FRIENDLY s5_blip FALSE
				IF timerb > 14000
					PRINT_NOW ( STE5_07 ) 4000 1 //Burst the tyres of the target car.
				ENDIF
				s5_is_plyr_in_car = 1
			ENDIF
		ENDIF

		////////////////////////// MISC FAILURE /////////////
		IF timerb > 14000 
			IF s5_stingers_left = 3
				IF IS_CHAR_IN_CAR scplayer s5_temp_car
			 		IF NOT IS_MINIGAME_IN_PROGRESS
			 			PRINT_HELP_FOREVER STE5_03 
					ENDIF
				ELSE
					CLEAR_HELP
				ENDIF	
				s5_trigger_stingers = 1
			ELSE
				CLEAR_HELP
			ENDIF
		ENDIF
		
		IF s5_stingers_left = 0
			IF timera > 5000
				CLEAR_PRINTS
				PRINT_NOW ( STE5_05 ) 4000 1 //You ran out of stingers.
				GOTO mission_steal5_failed	
			ENDIF
		ENDIF

		GOSUB s5_speed_up_slow_down
		GOSUB s5_making_sure_car_doesnt_drown
		GOSUB s5_telling_woman_where_to_go


		//////////////// MAIN PART //////////////////////
		IF s5_control_flag = 0
			IF IS_CHAR_IN_CAR scplayer s5_temp_car 
				PRINT_NOW ( STE5_01 ) 7000 1 //Burst the tyres of the target car using stingers.
				PRINT_WITH_NUMBER ( STE5_02 ) 3 7000 1 //You only have 6 stingers available.
				timerb = 0
				s5_control_flag = 1
			ENDIF
		ENDIF
			
		
		IF s5_control_flag = 1
			//////////////////////////checking to see if tyres are burst or not
			IF IS_CAR_TYRE_BURST s5_target_car FRONT_RIGHT_WHEEL
				LOCK_CAR_DOORS s5_target_car CARLOCK_UNLOCKED
				REMOVE_STUCK_CAR_CHECK s5_target_car
				IF NOT IS_CHAR_DEAD s5_enemy
					OPEN_SEQUENCE_TASK s5_seq
						TASK_LEAVE_ANY_CAR -1
						TASK_SMART_FLEE_CHAR -1 scplayer 100.0 100000
					CLOSE_SEQUENCE_TASK s5_seq
					PERFORM_SEQUENCE_TASK s5_enemy s5_seq
					CLEAR_SEQUENCE_TASK s5_seq
					REMOVE_BLIP s5_blip
					ADD_BLIP_FOR_CAR s5_target_car s5_blip
					SET_BLIP_AS_FRIENDLY s5_blip TRUE   
					SET_CAR_WATERTIGHT s5_target_car FALSE
					PRINT_NOW ( STE5_13 ) 7000 1 //Get in the car.
					timera = 0 
					s5_control_flag = 0
					s5_goals = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////cutscene with player telling cesar he is going to fix the car///////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF s5_goals = 1
		IF s5_control_flag = 0 
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer s5_target_car 5.0 5.0 FALSE 
					s5_control_flag = 1
				ENDIF
			ENDIF
			GOSUB s5_burst_tyres
		ENDIF
	
		IF s5_control_flag = 1
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_MISSION_AUDIO 3
			CLEAR_PRINTS
			s5_speech_goals = 0
		
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON  
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
			SHUT_ALL_CHARS_UP TRUE

			s5_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START	
		
			TASK_USE_MOBILE_PHONE scplayer TRUE

			CLEAR_HELP 
			CLEAR_ONSCREEN_COUNTER s5_stingers_left
		
			//phone ringing
			s5_speech_goals = 3
			s5_speech_control_flag = 0
			GOSUB s5_dialogue_setup 	
			 
			timera = 0
			s5_control_flag = 2
		ENDIF

		IF s5_control_flag = 2
			IF timera > 2000
				IF s5_speech_goals = 0 
					//cutscene dialogue
					s5_speech_goals = 1
					s5_speech_control_flag = 0
					GOSUB s5_dialogue_setup 
					s5_control_flag = 3
				ENDIF
			ENDIF
		ENDIF	

		IF s5_control_flag = 3
			IF s5_speech_goals = 0 
				TASK_USE_MOBILE_PHONE scplayer FALSE
				s5_control_flag = 4
			ENDIF
		ENDIF
				
		IF s5_control_flag = 4
			GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE task_status			
			IF task_status = FINISHED_TASK	
				OPEN_SEQUENCE_TASK s5_seq	
					TASK_GOTO_CAR -1 s5_target_car -2 1.0
				CLOSE_SEQUENCE_TASK s5_seq
				PERFORM_SEQUENCE_TASK scplayer s5_seq
				CLEAR_SEQUENCE_TASK s5_seq
				
				SET_MUSIC_DOES_FADE FALSE
				DRAW_SUBTITLES_BEFORE_FADE FALSE

				DO_FADE 700 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 

				LOAD_MISSION_AUDIO 3 SOUND_FIT_TYRE
				WHILE NOT HAS_MISSION_AUDIO_LOADED 3
					WAIT 0 
				ENDWHILE  
				GOSUB s5_death_checks
				IF s5_deathcheck_flag = 1			
					GOTO mission_steal5_failed
				ENDIF
				timera = 0
				s5_control_flag = 5
			ENDIF
		ENDIF

		IF s5_control_flag = 5
			IF timera > 1000
				PLAY_MISSION_AUDIO 3

				//cutscene dialogue
				s5_speech_goals = 2
				s5_speech_control_flag = 0
				GOSUB s5_dialogue_setup 

				s5_control_flag = 6
			ENDIF
		ENDIF	

		IF s5_control_flag = 6
			IF s5_speech_goals = 0 
				s5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB s5_death_checks
				IF s5_deathcheck_flag = 1
					GOTO mission_steal5_failed
				ENDIF

				SET_MUSIC_DOES_FADE TRUE 
				DRAW_SUBTITLES_BEFORE_FADE TRUE

				IF s5_skip_cutscene_flag = 1 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_PRINTS
					s5_speech_goals = 0

					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB s5_death_checks
					IF s5_deathcheck_flag = 1
						GOTO mission_steal5_failed
					ENDIF
					CLEAR_PRINTS
				ENDIF
				
				CLEAR_MISSION_AUDIO 3
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 

				DELETE_CHAR s5_enemy 
				
				IF NOT IS_CAR_DEAD s5_target_car 
					WARP_CHAR_INTO_CAR scplayer s5_target_car
					SET_CAR_ONLY_DAMAGED_BY_PLAYER s5_target_car FALSE
					FIX_CAR_TYRE s5_target_car FRONT_RIGHT_WHEEL
					FIX_CAR_TYRE s5_target_car FRONT_LEFT_WHEEL
					FIX_CAR_TYRE s5_target_car REAR_LEFT_WHEEL
					FIX_CAR_TYRE s5_target_car REAR_RIGHT_WHEEL
				ENDIF 

				DELETE_OBJECT s5_stinger

				GET_CHAR_COORDINATES scplayer s5_x s5_y s5_z
				LOAD_SCENE s5_x s5_y s5_z 

				MARK_CAR_AS_NO_LONGER_NEEDED s5_temp_car
				MARK_CHAR_AS_NO_LONGER_NEEDED s5_enemy
				MARK_MODEL_AS_NO_LONGER_NEEDED TAMPA  
				MARK_MODEL_AS_NO_LONGER_NEEDED HFYRI  
				MARK_MODEL_AS_NO_LONGER_NEEDED temp_stinger2 
					
				SET_CAR_ONLY_DAMAGED_BY_PLAYER s5_target_car FALSE
			
				SHUT_ALL_CHARS_UP FALSE
				
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
			
				REMOVE_BLIP s5_blip 
				ADD_BLIP_FOR_COORD -2026.9 179.1 27.5 s5_blip
				s5_is_plyr_in_car = 1
				
				CLEAR_PRINTS
				PRINT_NOW ( STE5_11 ) 7000 1 //Take the target car back to the Garage.

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB s5_death_checks
				IF s5_deathcheck_flag = 1			
					GOTO mission_steal5_failed
				ENDIF
				s5_control_flag = 0
				s5_goals = 2
			ENDIF
		ENDIF
	ENDIF
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
//////////////////Waiting for player to take car back to garage///////////////////////////////////////////////////	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF s5_goals = 2

		// debug //
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			IF NOT IS_CAR_DEAD s5_target_car 
				SET_CAR_COORDINATES s5_target_car -2011.8 176.5 26.6
				SET_CAR_HEADING s5_target_car 97.7
			ENDIF
		ENDIF  
					


		//waiting for player to reach garage
		IF s5_control_flag = 0
			IF NOT IS_CAR_DEAD s5_target_car 
				IF IS_CHAR_IN_CAR scplayer s5_target_car 
					IF LOCATE_CHAR_IN_CAR_3D scplayer -2033.1 178.6 27.8 4.0 4.0 4.0 TRUE
						SET_PLAYER_CONTROL player1 OFF
						DO_FADE 500 FADE_OUT
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
					
						SET_AREA_VISIBLE 1

						REMOVE_BLIP s5_blip 																			 
					
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						s5_speech_goals = 0
					
						SET_PLAYER_CONTROL player1 OFF 
						SWITCH_WIDESCREEN ON  
						HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
						
						SET_CHAR_AREA_VISIBLE scplayer 1
						CLEAR_AREA -2033.3 179.1 27.4 25.0 TRUE
						IF NOT IS_CAR_DEAD s5_target_car 
							SET_VEHICLE_AREA_VISIBLE s5_target_car 1
							SET_CAR_COORDINATES s5_target_car -2033.3 179.1 27.4
							SET_CAR_HEADING s5_target_car 90.8 
						ENDIF 
						
						LOAD_SCENE_IN_DIRECTION -2048.1 178.0 28.8 270.0

						SET_FIXED_CAMERA_POSITION -2048.1 178.0 28.8 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -2037.9 178.8 27.4 JUMP_CUT

						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
					
						OPEN_GARAGE hbgdSFS								  
						WHILE NOT IS_GARAGE_OPEN hbgdSFS 
							WAIT 0 
						ENDWHILE

						IF NOT IS_CAR_DEAD s5_target_car 
							TASK_CAR_DRIVE_TO_COORD scplayer s5_target_car -2045.4 178.3 27.6 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
						ENDIF

						s5_control_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF s5_control_flag = 1 			
			IF NOT IS_CAR_DEAD s5_target_car
				GET_SCRIPT_TASK_STATUS scplayer TASK_CAR_DRIVE_TO_COORD task_status					
				IF task_status = FINISHED_TASK
					OPEN_SEQUENCE_TASK s5_seq	
						TASK_LEAVE_ANY_CAR -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -2035.4708 176.6342 27.8359 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK s5_seq
					PERFORM_SEQUENCE_TASK scplayer s5_seq
					CLEAR_SEQUENCE_TASK s5_seq
					timera = 0 
					s5_control_flag = 2
				ENDIF
			ENDIF
		ENDIF	  			
				
		IF s5_control_flag = 2
			IF timera  > 2000 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				
				SET_AREA_VISIBLE 0
				SET_CHAR_AREA_VISIBLE scplayer 0
				LOAD_SCENE -2045.4 173.1 27.8
				LOAD_SCENE_IN_DIRECTION -2045.4 173.1 27.8 309.5 

				CLEAR_PRINTS
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				DELETE_CAR s5_target_car
				CLEAR_AREA -2027.2 179.5 27.8 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2027.2 179.5 27.8
				SET_CHAR_HEADING scplayer 275.9
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
			
				CLOSE_GARAGE hbgdSFS
				WHILE NOT IS_GARAGE_CLOSED hbgdSFS 
					WAIT 0 
				ENDWHILE

				//cutscene for carshowroom
				CREATE_PROTECTION_PICKUP -1969.4630 289.5335 34.6719 showroom_revenue showroom_revenue showroom_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON

				LOAD_SCENE -1980.3014 288.1622 38.2246
				SET_FIXED_CAMERA_POSITION -1980.3014 288.1622 38.2246 0.0 0.0 0.0 //cut scene of building
				POINT_CAMERA_AT_POINT -1979.3135 288.0155 38.1743 JUMP_CUT
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				PLAY_MISSION_PASSED_TUNE 1
				PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

				WAIT 5000
				SET_FIXED_CAMERA_POSITION -1972.1849 291.1454 35.7724 0.0 0.0 0.0 //cut scene showing pickup
				POINT_CAMERA_AT_POINT -1971.3434 290.6249 35.6284 JUMP_CUT
				PRINT_WITH_NUMBER_NOW ASS_LUV showroom_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

				WAIT 6000
				DO_FADE 500 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				DO_FADE 500 FADE_IN

				GOTO mission_steal5_passed
			ENDIF
		ENDIF


		//////////////////////////blippage										   
		IF s5_is_plyr_in_car = 1 												   
			IF NOT IS_CHAR_IN_CAR scplayer s5_target_car
				REMOVE_BLIP s5_blip
				ADD_BLIP_FOR_CAR s5_target_car s5_blip
				SET_BLIP_AS_FRIENDLY s5_blip TRUE
				CLEAR_PRINTS 
				PRINT_NOW ( STE5_08 ) 4000 1 //Get back in the car.
				s5_is_plyr_in_car = 0
			ENDIF
		ENDIF	
		IF s5_is_plyr_in_car = 0 
			IF IS_CHAR_IN_CAR scplayer s5_target_car 		
				REMOVE_BLIP s5_blip
				IF s5_control_flag = 0 					   
					ADD_BLIP_FOR_COORD -2033.1 178.6 27.8 s5_blip
				ENDIF	
				CLEAR_PRINTS 
				PRINT_NOW ( STE5_11 ) 7000 1 //Get the car back to the garage.
				s5_is_plyr_in_car = 1
			ENDIF
		ENDIF
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////// FAILING MISSION CUTSCENE ////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF s5_goals = 10  
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF s5_fucking_fudge_flag = 0 

				CLEAR_PRINTS 
							
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				s5_speech_goals = 0
			
				SET_PLAYER_CONTROL player1 OFF 
				SWITCH_WIDESCREEN ON  
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				CLEAR_AREA -2125.5 -2467.5 29.6 1.0 TRUE
				GET_CHAR_COORDINATES scplayer s5_player_x s5_player_y s5_player_z
				
				REQUEST_COLLISION -2125.5 -2467.5
				LOAD_SCENE -2125.5 -2467.5 29.6
				SET_CHAR_COORDINATES scplayer -2125.5 -2467.5 29.6
			
				IF NOT IS_CHAR_DEAD s5_enemy
					CLEAR_CHAR_TASKS s5_enemy
				ENDIF	 
				
				REQUEST_COLLISION -2117.8 -2456.8 
				LOAD_SCENE -2115.6 -2456.5 30.6
				LOAD_ALL_MODELS_NOW

				IF NOT IS_CAR_DEAD s5_target_car 
					REMOVE_STUCK_CAR_CHECK s5_target_car 
					CLEAR_AREA -2117.8 -2456.8 29.3 30.0 TRUE 
					SET_CAR_COORDINATES s5_target_car -2117.8 -2456.8 29.3
					SET_CAR_HEADING s5_target_car 233.8  
				ENDIF 
			
				SET_FIXED_CAMERA_POSITION -2106.0 -2465.7 30.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2112.2 -2461.1 29.6 JUMP_CUT 	
				
				PRINT_NOW ( STE5_14 ) 5000 1 //You missed the car!
				DO_FADE 500 FADE_IN 
				
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
			
				OPEN_GARAGE amumis

				WHILE NOT IS_GARAGE_OPEN amumis
					WAIT 0
				ENDWHILE
				
				IF NOT IS_CHAR_DEAD s5_enemy 
					IF NOT IS_CAR_DEAD s5_target_car 		
						TASK_CAR_DRIVE_TO_COORD s5_enemy s5_target_car -2108.0 -2464.2 29.6 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
					ENDIF
				ENDIF
				s5_fucking_fudge_flag = 1
			ENDIF 
		ENDIF

		IF s5_fucking_fudge_flag = 1
			IF NOT IS_CHAR_DEAD s5_enemy
				GET_SCRIPT_TASK_STATUS s5_enemy TASK_CAR_DRIVE_TO_COORD task_status	
				IF task_status = FINISHED_TASK	
					CLOSE_GARAGE amumis
					WHILE NOT IS_GARAGE_CLOSED amumis
						WAIT 0
					ENDWHILE
					
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					
					REQUEST_COLLISION s5_player_x s5_player_y 
					s5_player_z -= 1.0
					LOAD_SCENE s5_player_x s5_player_y s5_player_z
					LOAD_ALL_MODELS_NOW
					CLEAR_AREA s5_player_x s5_player_y s5_player_z 1.0 TRUE
					SET_CHAR_COORDINATES scplayer s5_player_x s5_player_y s5_player_z   

					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT 
					SET_PLAYER_CONTROL player1 ON
			
					CLEAR_PRINTS 
					
					PRINT_BIG M_FAIL 5000 1
					PRINT_NOW ( STE5_14 ) 5500 1 //You missed the car!
				
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 

					GOTO mission_steal5_failed
				ENDIF
			ENDIF
		ENDIF 
	ENDIF
		 

	///ingame dialogue///
	GOSUB s5_overall_dialogue


GOTO mission_steal5_loop

	
// Mission steal5 failed
mission_steal5_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission steal5 passed
mission_steal5_passed:
flag_steal_mission_counter ++
/*
IF flag_steal_mission_counter = 2
    REMOVE_BLIP trace_contact_blip
    ADD_SPRITE_BLIP_FOR_CONTACT_POINT traceX traceY traceZ trace_blip_icon trace_contact_blip          
ENDIF
IF flag_steal_mission_counter = 3
    START_NEW_SCRIPT zero_mission_loop
    REMOVE_BLIP zero_contact_blip
    ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip 
ENDIF
*/

REMOVE_BLIP steal_contact_blip
SWITCH_CAR_GENERATOR steal_car_cargen5 101

CLEAR_PRINTS
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!" //100 being the amount of cash
ADD_SCORE player1 5000//amount of cash
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED STEAL_5
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT steal5_mission_loop
RETURN
		

// mission cleanup
mission_cleanup_steal5:
//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
SET_MUSIC_DOES_FADE TRUE
REMOVE_BLIP s5_blip
CLEAR_MISSION_AUDIO 3
CLEAR_ONSCREEN_COUNTER s5_stingers_left 
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
DELETE_OBJECT s5_stinger
MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
MARK_MODEL_AS_NO_LONGER_NEEDED STRATUM 
MARK_MODEL_AS_NO_LONGER_NEEDED TAMPA  
MARK_MODEL_AS_NO_LONGER_NEEDED HFYRI  
MARK_MODEL_AS_NO_LONGER_NEEDED temp_stinger2
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
s5_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CAR_DEAD s5_target_car
	CLEAR_PRINTS
	PRINT_NOW ( STE5_09 ) 4000 1 //You destroyed the target car!	
	s5_deathcheck_flag = 1	
ENDIF

IF s5_goals = 0 
	IF IS_CAR_DEAD s5_temp_car
		CLEAR_PRINTS
		PRINT_NOW ( STE5_10 ) 7000 1 //You destroyed the stinger car!	
		s5_deathcheck_flag = 1	
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_burst_tyres:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD s5_temp_car 
	IF IS_CHAR_IN_CAR scplayer s5_temp_car  
		IF timera > 5000
			IF s5_stingers_left > 0 
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					DELETE_OBJECT s5_stinger 
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS s5_temp_car 0.0 -3.0 -0.5 s5_x s5_y s5_z
					GET_CAR_HEADING s5_temp_car heading 
					heading += 90.0 
					GET_GROUND_Z_FOR_3D_COORD s5_x s5_y s5_z s5_z
					CREATE_OBJECT temp_stinger2 s5_x s5_y s5_z s5_stinger
					SET_OBJECT_HEADING s5_stinger heading

					REPORT_MISSION_AUDIO_EVENT_AT_POSITION s5_x s5_y s5_z SOUND_STINGER_FIRE
					s5_stinger_sounds = 1
					s5_stingers_left --
					timera = 0
				ENDIF
			ELSE
				CLEAR_ONSCREEN_COUNTER s5_stingers_left	
			ENDIF
		ENDIF
		IF NOT s5_stingers_left = 3 			 
			CLEAR_HELP
		ENDIF
	ENDIF
ENDIF

IF s5_stinger_sounds = 1
	IF HAS_MISSION_AUDIO_FINISHED 3
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS s5_temp_car 0.0 -3.0 -0.5 s5_x s5_y s5_z
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION s5_x s5_y s5_z SOUND_STINGER_RELOAD
		s5_stinger_sounds = 2
	ENDIF
ENDIF

IF DOES_OBJECT_EXIST s5_stinger
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS s5_stinger -0.5 2.5 0.0 s5_stinger_x1 s5_stinger_y1 s5_z 
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS s5_stinger -0.5 -2.5 0.0 s5_stinger_x2 s5_stinger_y2 s5_z 
	IF IS_CHAR_IN_ANY_CAR scplayer 
		STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer s5_random_car2
		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer s5_stinger_x1 s5_stinger_y1 s5_stinger_x2 s5_stinger_y2 1.0 FALSE
			IF NOT IS_CAR_TYRE_BURST s5_random_car2 FRONT_RIGHT_WHEEL	
				BURST_CAR_TYRE s5_random_car2 FRONT_RIGHT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_random_car2 FRONT_LEFT_WHEEL 
				BURST_CAR_TYRE s5_random_car2 FRONT_LEFT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_random_car2 REAR_LEFT_WHEEL 
				BURST_CAR_TYRE s5_random_car2 REAR_LEFT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_random_car2 REAR_RIGHT_WHEEL 
				BURST_CAR_TYRE s5_random_car2 REAR_RIGHT_WHEEL 
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_DEAD s5_enemy
		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D s5_enemy s5_stinger_x1 s5_stinger_y1 s5_stinger_x2 s5_stinger_y2 1.0 FALSE
			IF NOT IS_CAR_TYRE_BURST s5_target_car FRONT_RIGHT_WHEEL	
				BURST_CAR_TYRE s5_target_car FRONT_RIGHT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_target_car FRONT_LEFT_WHEEL 
				BURST_CAR_TYRE s5_target_car FRONT_LEFT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_target_car REAR_LEFT_WHEEL 
				BURST_CAR_TYRE s5_target_car REAR_LEFT_WHEEL 
			ENDIF			
			IF NOT IS_CAR_TYRE_BURST s5_target_car REAR_RIGHT_WHEEL 
				BURST_CAR_TYRE s5_target_car REAR_RIGHT_WHEEL 
			ENDIF
		ENDIF
	ENDIF
			 
	GET_RANDOM_CAR_OF_TYPE_IN_ANGLED_AREA_NO_SAVE s5_stinger_x1 s5_stinger_y1 s5_stinger_x2 s5_stinger_y2 1.0 -1 s5_random_car 
	IF NOT IS_CAR_DEAD s5_random_car 
		IF NOT s5_random_car = s5_target_car 
			IF NOT s5_random_car = s5_temp_car
				IF NOT IS_CAR_TYRE_BURST s5_random_car FRONT_RIGHT_WHEEL	
					BURST_CAR_TYRE s5_random_car FRONT_RIGHT_WHEEL 
				ENDIF			
				IF NOT IS_CAR_TYRE_BURST s5_random_car FRONT_LEFT_WHEEL 
					BURST_CAR_TYRE s5_random_car FRONT_LEFT_WHEEL 
				ENDIF			
				IF NOT IS_CAR_TYRE_BURST s5_random_car REAR_LEFT_WHEEL 
					BURST_CAR_TYRE s5_random_car REAR_LEFT_WHEEL 
				ENDIF			
				IF NOT IS_CAR_TYRE_BURST s5_random_car REAR_RIGHT_WHEEL 
					BURST_CAR_TYRE s5_random_car REAR_RIGHT_WHEEL 
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

////DEBUG
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
		BURST_CAR_TYRE s5_target_car FRONT_RIGHT_WHEEL
	ENDIF	
////DEBUG
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_speed_up_slow_down://///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD s5_target_car
	IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer s5_target_car 60.0 60.0 FALSE 
		SET_CAR_CRUISE_SPEED s5_target_car 50.0
	ELSE
		IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer s5_target_car 100.0 100.0 FALSE 
			SET_CAR_CRUISE_SPEED s5_target_car 20.0
		ELSE
			SET_CAR_CRUISE_SPEED s5_target_car 15.0
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_making_sure_car_doesnt_drown:///////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD s5_target_car
	IF IS_CAR_ON_SCREEN s5_target_car
		SET_CAR_WATERTIGHT s5_target_car FALSE
	ELSE
		SET_CAR_WATERTIGHT s5_target_car TRUE
	ENDIF
ENDIF   
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_telling_woman_where_to_go:///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CHAR_DEAD s5_enemy
	IF IS_CHAR_IN_CAR s5_enemy s5_target_car  
		IF s5_controlling_woman = 0
			OPEN_SEQUENCE_TASK s5_seq	
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1913.1 -593.8 38.3 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1912.6 -744.7 44.7 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1914.2 -894.9 45.0 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1914.3 -1045.0 38.3 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1907.9 -1195.2 39.5 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1910.7 -1345.3 40.4 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1795.1 -1442.4 35.5 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1658.5 -1506.1 35.7 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
			CLOSE_SEQUENCE_TASK s5_seq
			PERFORM_SEQUENCE_TASK s5_enemy s5_seq
			CLEAR_SEQUENCE_TASK s5_seq
			s5_controlling_woman = 1
		ENDIF

		IF s5_controlling_woman = 1 
			GET_SCRIPT_TASK_STATUS s5_enemy PERFORM_SEQUENCE_TASK task_status	
			IF task_status = FINISHED_TASK
				OPEN_SEQUENCE_TASK s5_seq	
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1704.1 -1649.1 36.3 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1832.4 -1727.6 29.2 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -1954.5 -1815.3 34.4 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -2046.9 -1932.7 52.4 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -2106.8 -2070.0 63.5 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -2213.2 -2175.1 42.2 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -2160.4 -2315.1 30.5 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 s5_target_car -2120.2 -2456.7 29.6 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					//TASK_CAR_DRIVE_WANDER -1 s5_target_car 35.0 DRIVINGMODE_AVOIDCARS
				CLOSE_SEQUENCE_TASK s5_seq
				PERFORM_SEQUENCE_TASK s5_enemy s5_seq
				CLEAR_SEQUENCE_TASK s5_seq
				s5_controlling_woman = 2
			ENDIF
		ENDIF
		IF s5_controlling_woman = 2
			GET_SCRIPT_TASK_STATUS s5_enemy PERFORM_SEQUENCE_TASK task_status	
			IF task_status = FINISHED_TASK
				s5_fucking_fudge_flag = 0
				s5_goals = 10
				s5_controlling_woman = 3
			ENDIF
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_my_number_plates:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 s5_no_plates_flag
	IF s5_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates got_m00_
	ENDIF 
	IF s5_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates m00tv_4u 
	ENDIF
	IF s5_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates mathew_2 
	ENDIF 
	IF s5_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates d4_dew0r 
	ENDIF 
	IF s5_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates d0de_777 
	ENDIF 
	IF s5_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates dam0_666 
	ENDIF 
	IF s5_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates C0NEY_88 
	ENDIF 
	IF s5_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates pre4cher 
	ENDIF 
	IF s5_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates dbp_4ndy 
	ENDIF 
	IF s5_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates ev1l_sly 
	ENDIF 
	IF s5_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates n1_r4v3n 
	ENDIF 
	IF s5_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates d1vx_z00 
	ENDIF 
	IF s5_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates mr_b3nn 
	ENDIF 
	IF s5_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates r3d_r4sp 
	ENDIF 
	IF s5_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates La_B0mba 
	ENDIF 
	IF s5_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates L3337_0g 
	ENDIF 
	IF s5_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates budd4h_X 
	ENDIF 
	IF s5_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates t3h_buck 
	ENDIF 
	IF s5_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates CHUNKY_1 
	ENDIF 
	IF s5_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates ev1l_bnz 
	ENDIF 
	IF s5_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates S4ND_M4N 
	ENDIF 
	IF s5_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates RKK_DBP1 
	ENDIF 
	IF s5_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates RE1_K0KU 
	ENDIF 
	IF s5_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates s3xy_jud 
	ENDIF 
	IF s5_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates sunra_93 
	ENDIF 
	IF s5_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates UG_FuX69 
	ENDIF 
	IF s5_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates Li0n_Cum 
	ENDIF 
	IF s5_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates rkk_pwnd 
	ENDIF 
	IF s5_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates haze_b0b 
	ENDIF 
	IF s5_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates t3h_fluf 
	ENDIF 
	IF s5_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates BM_4NDY_ 
	ENDIF 
	IF s5_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates BM_D34N_ 
	ENDIF 
	IF s5_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates BM_L4C3Y 
	ENDIF 
	IF s5_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates BM_D3V__ 
	ENDIF 
	IF s5_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates NU_SK00L 
	ENDIF 
	IF s5_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates G4L_AVET 
	ENDIF 
	IF s5_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR s5_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_dialogue_setup://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF s5_speech_goals = 1
	$s5_print_label[0] = &STL5_AA // Hey, CJ, how's it going with that crazy bitch?
	$s5_print_label[1] = &STL5_AB // Popped her!
	$s5_print_label[2] = &STL5_AC // Just about to fix the tyres, should be back any minute.
	$s5_print_label[3] = &STL5_AD // Ok, careful with that puncture repair gloop, CJ, it's nasty crap!
	$s5_print_label[4] = &STL5_AE // Will do. Later, dude.

	s5_audio_label[0] = SOUND_STL5_AA
	s5_audio_label[1] = SOUND_STL5_AB
	s5_audio_label[2] = SOUND_STL5_AC
	s5_audio_label[3] = SOUND_STL5_AD
	s5_audio_label[4] = SOUND_STL5_AE
	s5_last_label = 5
ENDIF

IF s5_speech_goals = 2
	$s5_print_label[0] = &STL5_AF // Man, this stuff is disgusting.
	$s5_print_label[1] = &STL5_AG // Aw shit, all over my jeans!

	s5_audio_label[0] = SOUND_STL5_AF
	s5_audio_label[1] = SOUND_STL5_AG
	s5_last_label = 2
ENDIF

IF s5_speech_goals = 3
	$s5_print_label[0] = &MOBRING // Phone Ringing

	s5_audio_label[0] = SOUND_MOBRING
	s5_last_label = 1
ENDIF


s5_slot_load = s5_speech_control_flag
s5_slot1 = 0
s5_slot2 = 0
s5_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_overall_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF s5_speech_goals = 1 //playing over cutscene where player fixes wheels
OR s5_speech_goals = 2 //playing over cutscene where player fixes wheels
OR s5_speech_goals = 3
	IF s5_speech_control_flag < s5_last_label
		GOSUB s5_loading_dialogue
		GOSUB s5_playing_dialogue
		GOSUB s5_finishing_dialogue  
	ELSE
		s5_speech_goals = 0
	ENDIF
ENDIF	
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_loading_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF s5_slot_load < s5_last_label 
	//slot 1
	IF s5_slot1 = 0
		LOAD_MISSION_AUDIO 1 s5_audio_label[s5_slot_load] 
		s5_slot_load ++ 
		s5_slot1 = 1
	ENDIF

	//slot 2
	IF s5_slot2 = 0
		LOAD_MISSION_AUDIO 2 s5_audio_label[s5_slot_load] 
		s5_slot_load ++ 
		s5_slot2 = 1
	ENDIF  
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1			   
IF s5_play_which_slot = 1 
	IF s5_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $s5_print_label[s5_speech_control_flag] ) 4500 1 //
  			s5_slot1 = 2
		ENDIF
	ENDIF
ENDIF
			   
//slot 2
IF s5_play_which_slot = 2 
	IF s5_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $s5_print_label[s5_speech_control_flag] ) 4500 1 //
			s5_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
s5_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF s5_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $s5_print_label[s5_speech_control_flag]
		s5_speech_control_flag ++		
		s5_play_which_slot = 2
		s5_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF s5_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $s5_print_label[s5_speech_control_flag]
		s5_speech_control_flag ++		
		s5_play_which_slot = 1
		s5_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}
