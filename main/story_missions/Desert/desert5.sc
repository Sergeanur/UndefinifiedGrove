MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Desert 5 ******************************************
// *********************************** Pilot School ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME desert5

// Mission start stuff

GOSUB mission_start_desert5

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_desert5_failed
ENDIF

GOSUB mission_cleanup_desert5

MISSION_END													   

{

// Variables for mission

LVAR_INT d5_mission_selection

LVAR_INT d5_mission_plane d5_mission_plane_health
LVAR_FLOAT d5_plane_x d5_plane_y d5_plane_z d5_plane_heading
LVAR_FLOAT d5_player_x d5_player_y d5_player_z
LVAR_FLOAT d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2
LVAR_FLOAT d5_runway_centre_x d5_runway_centre_y
LVAR_FLOAT d5_m1_plane_start_x d5_m1_plane_start_y d5_m1_plane_start_z
//LVAR_FLOAT d5_m2_plane_start_x d5_m2_plane_start_y d5_m2_plane_start_z
LVAR_FLOAT d5_m8_plane_start_x d5_m8_plane_start_y d5_m8_plane_start_z
LVAR_FLOAT d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z
LVAR_INT d5_m9_current_wave
LVAR_INT d5_m9_first_wave_cars[3] d5_m9_num_of_first_wave_cars
LVAR_INT d5_m9_second_wave_cars[2] d5_m9_num_of_second_wave_cars d5_m9_second_wave_car_drivers[2]
LVAR_FLOAT d5_m9_first_wave_car_x
LVAR_INT d5_m12_stunt_double
LVAR_INT d5_second_wave_seq0 d5_second_wave_seq1

LVAR_FLOAT d5_m1_corona_x[3] d5_m1_corona_y[3] d5_m1_corona_z[3]
LVAR_FLOAT d5_m2_corona_x d5_m2_corona_y d5_m2_corona_z
LVAR_FLOAT d5_m3_corona_x[8] d5_m3_corona_y[8] d5_m3_corona_z[8]
LVAR_FLOAT d5_m7_corona_x[1] d5_m7_corona_y[1] d5_m7_corona_z[1]
//LVAR_FLOAT d5_m10_corona_x[12] d5_m10_corona_y[12] d5_m10_corona_z[12]
LVAR_FLOAT d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z
LVAR_FLOAT d5_m10_end_corona_x
LVAR_INT d5_next_corona d5_next_corona_plus_one
LVAR_INT d5_cur_checkpoint d5_m3_checkpoints[8] d5_m10_start_checkpoint d5_m10_end_checkpoint
LVAR_FLOAT d5_checkpoint_radius
LVAR_FLOAT d5_radius d5_angle
LVAR_FLOAT d5_required_altitude
LVAR_FLOAT d5_circle_centre_x d5_circle_centre_y //d5_circle_centre_z

// global (onscreen counter)
//VAR_INT d5_m9_targets_remaining

// temp globals for view_float_variable (usually locals)
VAR_FLOAT d5_cur_pitch d5_last_pitch d5_pitch_diff d5_pitch_progress
VAR_FLOAT d5_cur_roll d5_last_roll d5_roll_diff d5_roll_progress
VAR_FLOAT d5_negative_progress
VAR_FLOAT d5_plane_up_component

LVAR_INT d5_m12_parachute_target
LVAR_FLOAT d5_m12_parachute_target_x d5_m12_parachute_target_y d5_m12_parachute_target_z
LVAR_FLOAT d5_m12_parachute_start_x d5_m12_parachute_start_y d5_m12_parachute_start_z
LVAR_FLOAT d5_m12_distance_from_player_to_target //d5_dist_from_target_to_edge_of_runway

LVAR_INT d5_m12_checkpoint

LVAR_INT d5_menu

LVAR_INT d5_damage_penalty d5_time_score d5_perfect_time d5_pass_time d5_pass_minus_perfect
LVAR_INT d5_car_recording

LVAR_FLOAT d5_landing_gear_position

LVAR_INT d5_text_rval d5_text_gval d5_text_bval d5_text_alpha

LVAR_INT d5_index //d5_index_plus_one

// flags

LVAR_INT d5_landing_gear_retracted d5_landing_gear_extended
LVAR_INT d5_m2_reached_runway_corona d5_m2_started_taxiing
LVAR_INT d5_m5_finished_following_coronas
LVAR_INT d5_m8_player_reached_hovering_area
LVAR_INT d5_m9_first_wave_blip_flags[3] d5_m9_second_wave_blip_flags[2]
LVAR_INT d5_m9_all_cars_in_current_wave_destroyed d5_m9_heli_landing
LVAR_INT d5_m9_car_exploded[3]
LVAR_INT d5_m10_player_passed_starting_corona d5_m10_completed_stunt
LVAR_INT d5_m12_player_reached_opening_altitude d5_m12_player_opened_parachute

//LVAR_INT d5_player_finished_any_tests_yet

LVAR_INT d5_just_passed_pilot_school
LVAR_INT d5_just_watched_first_cutscene

// mission audio
LVAR_TEXT_LABEL d5_audio_text[12]
LVAR_INT d5_audio_sound[12]
LVAR_INT d5_audio_is_playing d5_audio_index d5_total_audio_to_play d5_started_talking


LVAR_INT d5_fake_creates

// blips

LVAR_INT d5_corona_blip
LVAR_INT d5_m9_first_wave_car_blips[3] d5_m9_second_wave_car_blips[2]

// Front end stuff
LVAR_INT d5_fade_flag d5_alpha d5_which_score_displayed d5_which_medal_displayed
LVAR_INT d5_control_flag d5_playback_flag

LVAR_INT d5_mission_plane_dead_flag
LVAR_INT d5_plane_started
// temp globals for view_float_variable (usually locals)
VAR_INT d5_plane_reached_takeoff_point d5_plane_taken_off d5_plane_reached_required_altitude
VAR_FLOAT d5_mission_plane_speed
LVAR_INT d5_m7_rotated_180
LVAR_INT d5_finished_mission d5_failed_challenge
LVAR_INT d5_old_score d5_print_top_scores_flag
//LVAR_FLOAT d5_overall_score_float

// **************************************** Mission Start **********************************

mission_start_desert5:

CLEAR_THIS_PRINT M_FAIL
flag_player_on_mission = 1
IF NOT pilot_test_passed = 1
	REGISTER_MISSION_GIVEN
ENDIF
LOAD_MISSION_TEXT DSERT5
SET_FADING_COLOUR 0 0 0
WAIT 0

DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE

// ************************************ Initialise variables  *****************************

// front end stuff
noticeboard_x = 415.55
noticeboard_y = 2533.57
noticeboard_z = 19.18
d5_control_flag = 0
d5_fade_flag = 0
d5_alpha = 0
d5_which_medal_displayed = 0
d5_which_score_displayed = 0
d5_playback_flag = 0

d5_mission_plane_dead_flag = 0
d5_plane_started = 0
d5_finished_mission = 0
d5_failed_challenge = 0
d5_old_score = 0
d5_print_top_scores_flag = 0

// specific to mini-missions
d5_runway_x1 = -81.36
d5_runway_y1 = 2476.0
d5_runway_x2 = 435.92
d5_runway_y2 = 2528.62

d5_runway_centre_x = d5_runway_x1 + d5_runway_x2
d5_runway_centre_x /= 2.0
d5_runway_centre_y = d5_runway_y1 + d5_runway_y2
d5_runway_centre_y /= 2.0

d5_m1_plane_start_x = 321.52
d5_m1_plane_start_y = d5_runway_centre_y
d5_m1_plane_start_z = 17.16

d5_m1_corona_x[0] = -383.79
d5_m1_corona_y[0] = d5_runway_centre_y
d5_m1_corona_z[0] = 147.09

d5_m1_corona_x[1] = d5_m1_corona_x[0] - 300.0
d5_m1_corona_y[1] = d5_m1_corona_y[0]
d5_m1_corona_z[1] = d5_m1_corona_z[0] - 20.0

d5_m1_corona_x[2] = d5_m1_corona_x[0] - 650.0
d5_m1_corona_y[2] = d5_m1_corona_y[0]
d5_m1_corona_z[2] = d5_m1_corona_z[0] + 10.0

//d5_m2_plane_start_x = -423.79
//d5_m2_plane_start_y = d5_runway_centre_y
//d5_m2_plane_start_z = 94.09

d5_m8_plane_start_x = -383.79
d5_m8_plane_start_y = d5_runway_centre_y
d5_m8_plane_start_z = 85.09

d5_m2_corona_x = d5_runway_x1
d5_m2_corona_y = d5_runway_centre_y
d5_m2_corona_z = 33.30

d5_m7_corona_x[0] = d5_m1_plane_start_x - 500.0
d5_m7_corona_y[0] = d5_m1_plane_start_y
d5_m7_corona_z[0] = d5_m1_plane_start_z + 120.0

d5_m8_landing_target_x = 308.0
d5_m8_landing_target_y = d5_runway_centre_y
d5_m8_landing_target_z = 17.07

d5_m9_num_of_first_wave_cars = 3
d5_m9_num_of_second_wave_cars = 2

d5_m10_start_corona_x = d5_runway_centre_x
d5_m10_start_corona_y = d5_runway_centre_y
d5_m10_start_corona_z = 80.0
d5_m10_end_corona_x = d5_m10_start_corona_x + 200.0

d5_checkpoint_radius = 15.0

d5_m12_parachute_target_x = d5_runway_centre_x
d5_m12_parachute_target_y = d5_runway_centre_y
d5_m12_parachute_target_z = 15.53
d5_m12_parachute_start_x = d5_m12_parachute_target_x + 350.0
d5_m12_parachute_start_y = d5_m12_parachute_target_y
d5_m12_parachute_start_z = 900.0
//d5_dist_from_target_to_edge_of_runway = d5_runway_centre_y - d5_runway_y1

d5_plane_reached_takeoff_point = 0
d5_plane_taken_off = 0
d5_plane_reached_required_altitude = 0
d5_m2_reached_runway_corona = 0
d5_m2_started_taxiing = 0

d5_just_passed_pilot_school = 0

d5_just_watched_first_cutscene = 0

//d5_player_finished_any_tests_yet = 0

d5_audio_is_playing = 0
d5_audio_index = 0
d5_started_talking = 0

d5_fake_creates = 0


// CREATE statements to keep the compiler happy

IF d5_fake_creates = 1
	CREATE_CAR RUSTLER 0.0 0.0 -100.0 d5_mission_plane
	CREATE_CHAR PEDTYPE_MISSION1 0 0.0 0.0 -100.0 d5_m12_stunt_double
	d5_index = 0
	WHILE d5_index < d5_m9_num_of_first_wave_cars
		CREATE_CAR PETRO 0.0 0.0 -100.0 d5_m9_first_wave_cars[d5_index]
		d5_index++
	ENDWHILE
ENDIF

CLEAR_THIS_PRINT_BIG_NOW 1

CLEAR_AREA_OF_CARS d5_runway_x1 d5_runway_y1 0.0 d5_runway_x2 d5_runway_y2 50.0
SHOW_UPDATE_STATS FALSE
SET_AREA51_SAM_SITE OFF
SWITCH_POLICE_HELIS FALSE
LVAR_INT d5_wanted_level_at_start
GET_MAX_WANTED_LEVEL d5_wanted_level_at_start
SET_MAX_WANTED_LEVEL 0

//LVAR_INT pause_flag
//LVAR_FLOAT instructor_car_roll 
//LVAR_INT instructor_car_roll_score instructor_car_roll_plus_minus 
//LVAR_INT f1_direction_flag
//LVAR_INT f1_checkpoint
// 
//LVAR_INT test_timer pausing_flag 


//test_timer = 0
//pausing_flag = 0

//start_coordsx = -2050.6 //these define the starting position of each track.
//start_coordsy = -130.0

REQUEST_MODEL RUSTLER
REQUEST_MODEL HUNTER
REQUEST_MODEL PETRO
REQUEST_MODEL LANDSTAL
REQUEST_MODEL STUNT
STREAM_SCRIPT parachute.sc

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED RUSTLER
   OR NOT HAS_MODEL_LOADED HUNTER
   OR NOT HAS_MODEL_LOADED PETRO
   OR NOT HAS_MODEL_LOADED LANDSTAL
   OR NOT HAS_MODEL_LOADED STUNT
	WAIT 0
ENDWHILE

WHILE NOT HAS_STREAMED_SCRIPT_LOADED parachute.sc
	WAIT 0
ENDWHILE

LOAD_TEXTURE_DICTIONARY LD_drv

LOAD_SPRITE 1 brfly
LOAD_SPRITE 2 silfly
LOAD_SPRITE 3 golfly
LOAD_SPRITE 4 ribb
LOAD_SPRITE 5 tvcorn
LOAD_SPRITE 6 naward
LOAD_SPRITE 7 ribbw
LOAD_SPRITE 8 blkdot
LOAD_SPRITE 9 tvbase
LOAD_SPRITE 10 nawtxt

SET_ALWAYS_DRAW_3D_MARKERS TRUE

IF d5_watched_first_cutscene = 0

	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 50.0 FALSE
	//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
	// set player in front of tv
	SET_CHAR_COORDINATES scplayer 414.8 2534.2 18.18
	SET_CHAR_HEADING scplayer 200.0

	$d5_audio_text[0] = &MTOR04A
	$d5_audio_text[1] = &MTOR04B
	$d5_audio_text[2] = &MTOR04C
	$d5_audio_text[3] = &MTOR04D
	$d5_audio_text[4] = &MTOR04E
	$d5_audio_text[5] = &MTOR04F
	$d5_audio_text[6] = &MTOR04G
	$d5_audio_text[7] = &MTOR04H
	$d5_audio_text[8] = &MTOR04J
	$d5_audio_text[9] = &MTOR04K
	$d5_audio_text[10] = &MTOR04L
	$d5_audio_text[11] = &MTOR04M
	d5_audio_sound[0] = SOUND_MTOR04A
	d5_audio_sound[1] = SOUND_MTOR04B
	d5_audio_sound[2] = SOUND_MTOR04C
	d5_audio_sound[3] = SOUND_MTOR04D
	d5_audio_sound[4] = SOUND_MTOR04E
	d5_audio_sound[5] = SOUND_MTOR04F
	d5_audio_sound[6] = SOUND_MTOR04G
	d5_audio_sound[7] = SOUND_MTOR04H
	d5_audio_sound[8] = SOUND_MTOR04J
	d5_audio_sound[9] = SOUND_MTOR04K
	d5_audio_sound[10] = SOUND_MTOR04L
	d5_audio_sound[11] = SOUND_MTOR04M
	d5_total_audio_to_play = 12
	d5_audio_is_playing = 0
	d5_started_talking = 0

	SET_PLAYER_CONTROL player1 OFF
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN ON

	REQUEST_MODEL cellphone
	LOAD_MISSION_AUDIO 3 SOUND_MOBRING
	WHILE NOT HAS_MODEL_LOADED cellphone
	   OR NOT HAS_MISSION_AUDIO_LOADED 3
		WAIT 0
	ENDWHILE

	TASK_USE_MOBILE_PHONE scplayer TRUE
	PLAY_MISSION_AUDIO 3

	SET_FIXED_CAMERA_POSITION 415.83 2532.94 19.5 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 415.28 2533.65 19.5 JUMP_CUT

	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE

	LVAR_INT d5_cutscene_skipped
	d5_cutscene_skipped = 1

	SKIP_CUTSCENE_START

	WAIT 600

	CLEAR_MISSION_AUDIO 3

	WAIT 1000

	d5_audio_index = 0
	WHILE d5_audio_index < 1
		WAIT 0
		GOSUB d5_load_and_play_audio
	ENDWHILE

	WHILE d5_audio_index < 2
		WAIT 0
		GOSUB d5_load_and_play_audio
		IF d5_audio_is_playing = 2
		AND NOT d5_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			d5_started_talking = 1
		ENDIF
	ENDWHILE
	STOP_CHAR_FACIAL_TALK scplayer
	WHILE d5_audio_index < 3
		WAIT 0
		GOSUB d5_load_and_play_audio
		IF d5_audio_is_playing = 2
		AND NOT d5_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			d5_started_talking = 1
		ENDIF
	ENDWHILE
	STOP_CHAR_FACIAL_TALK scplayer

	WHILE d5_audio_index < 5
		WAIT 0
		GOSUB d5_load_and_play_audio
	ENDWHILE
	
	WHILE d5_audio_index < 6
		WAIT 0
		GOSUB d5_load_and_play_audio
		IF d5_audio_is_playing = 2
		AND NOT d5_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			d5_started_talking = 1
		ENDIF
	ENDWHILE
	STOP_CHAR_FACIAL_TALK scplayer

	WHILE d5_audio_index < 8
		WAIT 0
		GOSUB d5_load_and_play_audio
		IF d5_audio_is_playing = 2
		AND d5_audio_index = 7
		AND NOT d5_started_talking = 1
			SET_FIXED_CAMERA_POSITION 414.83 2532.13 19.43 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 415.01 2533.11 19.42 JUMP_CUT
			d5_started_talking = 1
		ENDIF
	ENDWHILE
	//TASK_LOOK_AT_COORD scplayer 416.05 2533.81 19.91 2000
	WHILE d5_audio_index < 10
		WAIT 0
		GOSUB d5_load_and_play_audio
	ENDWHILE
	
	WHILE d5_audio_index < 11
		WAIT 0
		GOSUB d5_load_and_play_audio
		IF d5_audio_is_playing = 2
		AND NOT d5_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			d5_started_talking = 1
		ENDIF
	ENDWHILE
	STOP_CHAR_FACIAL_TALK scplayer

	WHILE d5_audio_index < d5_total_audio_to_play
		WAIT 0
		GOSUB d5_load_and_play_audio
	ENDWHILE

	d5_cutscene_skipped = 0

	SKIP_CUTSCENE_END

	IF NOT d5_cutscene_skipped = 1
		TASK_USE_MOBILE_PHONE scplayer FALSE
	ENDIF

	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE

	IF d5_cutscene_skipped = 1
		TASK_USE_MOBILE_PHONE scplayer FALSE
	ENDIF

	//CLEAR_CHAR_TASKS scplayer
	//CLEAR_LOOK_AT scplayer
	STOP_CHAR_FACIAL_TALK scplayer
	CLEAR_MISSION_AUDIO 3
	CLEAR_MISSION_AUDIO 1
	CLEAR_PRINTS
	//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

	// set player in front of tv
	SET_CHAR_COORDINATES scplayer 413.94 noticeboard_y 18.18
	SET_CHAR_HEADING scplayer 270.0
	d5_just_watched_first_cutscene = 1
	d5_watched_first_cutscene = 1

	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	SET_PLAYER_CONTROL player1 ON

	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE

	GOTO mission_desert5_failed

ENDIF

SET_CHAR_COORDINATES scplayer 412.38 2533.65 18.18
SET_CHAR_HEADING scplayer 90.0

d5_noticeboard_setup:
// cleanup
SET_PLAYER_CONTROL player1 OFF
CLEAR_HELP
CLEAR_PRINTS
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0
// preparing for noticeboard
//GOSUB setting_up_variables
d5_mission_plane_dead_flag = 0
d5_plane_started = 0
d5_finished_mission = 0
d5_failed_challenge = 0
d5_print_top_scores_flag = 0
d5_playback_flag = 0
d5_control_flag = 0
USE_TEXT_COMMANDS TRUE
d5_alpha = 255
d5_fade_flag = 2

d5_mission_selection = d5_last_played

// circle airstrip both ways
IF d5_mission_selection = 3
	d5_mission_selection = 4
ENDIF
IF d5_mission_selection = 5
	d5_mission_selection = 6
ENDIF

DISPLAY_HUD FALSE
DISPLAY_RADAR FALSE

LOAD_MISSION_AUDIO 3 SOUND_VIDEOTAPE_NOISE
WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
ENDWHILE

DO_FADE 1000 FADE_IN
GOSUB d5_drawing_tv_screen

WHILE GET_FADING_STATUS
	WAIT 0
	GOSUB d5_drawing_tv_screen
ENDWHILE

//SET_AREA_VISIBLE 0

// mission selection
d5_mission_selection_loop:
WAIT 0

		// debug
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
			GOTO mission_desert5_passed  
		ENDIF

		GOSUB d5_drawing_tv_screen

		// CHOOSING WHICH MISSION
		
		IF d5_control_flag = 0

			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			
			// Look for input to switch missions if more than one mission is available.

		//	IF d5_total_missions_open > 1
						    
			    IF LStickX < -100
				OR IS_BUTTON_PRESSED PAD1 DPADLEFT
					d5_mission_selection--
					d5_last_played--
					IF d5_mission_selection = 3
						d5_mission_selection = 2
					ENDIF
					IF d5_mission_selection = 5
						d5_mission_selection = 4
					ENDIF
					IF d5_total_missions_open > 1 
						IF d5_playback_flag < 4 
							d5_playback_flag = 3 //overriding playback
						ENDIF
					ENDIF	
				ENDIF
                
				IF LStickX > 100
				OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
					d5_mission_selection++
					d5_last_played++
					IF d5_mission_selection = 3
						d5_mission_selection = 4
					ENDIF	
					IF d5_mission_selection = 5
						d5_mission_selection = 6
					ENDIF
					IF d5_total_missions_open > 1 
						IF d5_playback_flag < 4
							d5_playback_flag = 3 //overriding playback	
						ENDIF
					ENDIF
				ENDIF
		
		   // ENDIF

			IF d5_mission_selection < 1
				d5_mission_selection = d5_total_missions_open
				d5_last_played = d5_total_missions_open
			ENDIF

			IF d5_mission_selection > d5_total_missions_open
				d5_mission_selection = 1
				d5_last_played = 1
			ENDIF

			WHILE LStickX < -100
			OR LStickX > 100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
				WAIT 0
				GOSUB d5_drawing_tv_screen
				GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			ENDWHILE
			
			//triggering mission
			IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
				IF d5_mission_selection = 4 
				OR d5_mission_selection = 6 
					d5_control_flag = 1	
				ELSE
					IF d5_playback_flag < 4
						d5_playback_flag = 3
					ENDIF
					GOTO d5_start_mission
				ENDIF
			ENDIF

		ENDIF

		////SELECTING WHETHER TO GO RIGHT OR LEFT ON SOME MISSIONS
		IF d5_control_flag = 1
			IF d5_playback_flag < 4
				d5_playback_flag = 3
			ENDIF
			IF d5_playback_flag = 0 
				d5_playback_flag = 5
			ENDIF

			GOSUB d5_which_course_text 
			SET_TEXT_COLOUR 255 255 255 255
		   	DISPLAY_TEXT 320.0 140.0 DES5_FH  // Which course?

			GOSUB d5_which_course_text 
			SET_TEXT_CENTRE OFF
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 182.0 185.0 DES5_FI

			GOSUB d5_which_course_text 
			SET_TEXT_CENTRE OFF
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 182.0 211.0 DES5_FJ

			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			
			IF LStickX < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				GOTO d5_start_mission
			ENDIF

			IF LStickX > 100
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
				IF d5_mission_selection = 4 
					d5_mission_selection = 3
				ENDIF
				
				IF d5_mission_selection = 6 
					d5_mission_selection = 5
				ENDIF

				GOTO d5_start_mission
			ENDIF
		ENDIF

		//opening all the missions
		
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			d5_total_missions_open = 12
			pilot_test_passed = 1
		ENDIF


		//quitting the driving school
		IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
			GOTO mission_desert5_failed
		ENDIF

	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
	GOSUB d5_watching_demo

GOTO d5_mission_selection_loop
d5_start_mission:
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
	GOSUB d5_drawing_tv_screen
ENDWHILE
IF NOT IS_CAR_DEAD d5_mission_plane 
	STOP_PLAYBACK_RECORDED_CAR d5_mission_plane
ENDIF
DELETE_CAR d5_mission_plane
RESTORE_CAMERA_JUMPCUT
DELETE_CHAR d5_m12_stunt_double
d5_index = 0
WHILE d5_index < d5_m9_num_of_first_wave_cars
	DELETE_CAR d5_m9_first_wave_cars[d5_index]
	d5_index++
ENDWHILE
CLEAR_MISSION_AUDIO 3
CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 500.0 TRUE
CLEAR_ONSCREEN_TIMER car_timer
FREEZE_ONSCREEN_TIMER FALSE

d5_playback_flag = 5 
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE


IF d5_mission_selection = 1 

	d5_refresh_takeoff:

	GOSUB d5_start_initialise_stuff

	d5_setup_takeoff:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_plane_reached_takeoff_point = 0
	d5_plane_taken_off = 0
	d5_landing_gear_retracted = 0

	// create mission plane
	CREATE_CAR RUSTLER d5_m1_plane_start_x d5_m1_plane_start_y d5_m1_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 90.0
	//SET_CAR_PITCH d5_mission_plane 14.24

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 172.6 2466.14 17.05 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	WAIT 500

	CLEAR_PRINTS
	
    PRINT_NOW DES5_1A 5000 4 

    GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_takeoff_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_takeoff																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_takeoff
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			
			PRINT_NOW DES5_1A 5000 4  

			GOSUB d5_has_plane_started
			IF d5_plane_started = 1
				PRINT_NOW DES5_1B 10000 4  
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF NOT d5_plane_taken_off = 1
					IF NOT d5_plane_reached_takeoff_point = 1
						GET_CAR_PITCH d5_mission_plane d5_cur_pitch
						IF d5_cur_pitch <= 5.0
							d5_plane_reached_takeoff_point = 1
							
							PRINT_NOW DES5_1C 10000 4   

						ENDIF
					ENDIF
					GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
					IF d5_plane_z >= 25.0
						d5_plane_taken_off = 1
						PRINT_NOW ( DES5_1I ) 10000 4  
					ELSE						
						// check that player hasn't left the runway before taking off
						IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
							d5_failed_challenge = 1
						ENDIF
					ENDIF
				ENDIF
				IF d5_plane_taken_off = 1
					IF NOT d5_landing_gear_retracted = 1
						GET_PLANE_UNDERCARRIAGE_POSITION d5_mission_plane d5_landing_gear_position
						IF d5_landing_gear_position > 0.0
							d5_landing_gear_retracted = 1
							ADD_BLIP_FOR_COORD d5_m1_corona_x[0] d5_m1_corona_y[0] d5_m1_corona_z[0] d5_corona_blip
							// first checkpoint normal parallel to x-axis
							CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m1_corona_x[0] d5_m1_corona_y[0] d5_m1_corona_z[0] 0.0 d5_m1_corona_y[0] d5_m1_corona_z[0] d5_checkpoint_radius d5_cur_checkpoint
							d5_next_corona = 0
							CLEAR_PRINTS
							PRINT_NOW ( DES5_1F ) 7000 4 // Head for the first corona.
							PRINT ( DES5_1J ) 10000 4 // As the plane climbs and falls, the altitude meter to the left of the radar will rise and fall respectively.
						ENDIF
					ELSE
						IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m1_corona_x[d5_next_corona] d5_m1_corona_y[d5_next_corona] d5_m1_corona_z[d5_next_corona] d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
							REMOVE_BLIP d5_corona_blip
							DELETE_CHECKPOINT d5_cur_checkpoint
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							d5_next_corona++
							IF d5_next_corona < 3
								ADD_BLIP_FOR_COORD d5_m1_corona_x[d5_next_corona] d5_m1_corona_y[d5_next_corona] d5_m1_corona_z[d5_next_corona] d5_corona_blip
								// checkpoint normals parallel to x-axis
								CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m1_corona_x[d5_next_corona] d5_m1_corona_y[d5_next_corona] d5_m1_corona_z[d5_next_corona] 0.0 d5_m1_corona_y[d5_next_corona] d5_m1_corona_z[d5_next_corona] d5_checkpoint_radius d5_cur_checkpoint
								
								IF d5_next_corona = 1
									PRINT_NOW DES5_1G 10000 4  // ~s~Push up on the movement controls to descend to the next ~y~corona.
								ENDIF
								
								IF d5_next_corona = 2
									PRINT_NOW DES5_1H 10000 4  
								ENDIF
							ELSE
								d5_finished_mission = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF d5_plane_taken_off = 1
					//IF IS_CAR_STOPPED d5_mission_plane
					IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
						d5_failed_challenge = 1
					ENDIF
				ENDIF
				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1

					GOSUB d5_freeze_plane_pos

					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 30
						d5_pass_time = 45
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[0]  	
						d5_old_score = d5_best_scores[0] 
						d5_best_scores[0] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 1
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 2
							d5_last_played = 2
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_takeoff																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_takeoff																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_takeoff:
					GOSUB d5_mini_cleanup

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_takeoff
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_takeoff_loop
ENDIF

IF d5_mission_selection = 2

	d5_refresh_landplane:

	GOSUB d5_start_initialise_stuff

	d5_setup_landplane:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_m2_reached_runway_corona = 0
	d5_m2_started_taxiing = 0
	d5_landing_gear_extended = 0

	// create mission plane
	CREATE_CAR RUSTLER d5_m8_plane_start_x d5_m8_plane_start_y d5_m8_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	//CHANGE_CAR_COLOUR d5_mission_plane 1 1

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 27.29 2466.31 16.76 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	SET_PLANE_UNDERCARRIAGE_UP d5_mission_plane TRUE
	FREEZE_CAR_POSITION d5_mission_plane TRUE

	WAIT 500

	CLEAR_PRINTS
	
    PRINT_NOW ( DES5_2J ) 5000 4 // Press R3 to extend the landing gear.

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_landplane_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_landplane																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_landplane
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			IF NOT IS_CAR_DEAD d5_mission_plane
				FREEZE_CAR_POSITION d5_mission_plane FALSE
				SET_CAR_FORWARD_SPEED d5_mission_plane 30.0
				ADD_BLIP_FOR_COORD d5_m2_corona_x d5_m2_corona_y d5_m2_corona_z d5_corona_blip
				// first checkpoint normal parallel to x-axis
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m2_corona_x d5_m2_corona_y d5_m2_corona_z -200.0 d5_m2_corona_y d5_m2_corona_z d5_checkpoint_radius d5_cur_checkpoint
				DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				
                PRINT_NOW ( DES5_2J ) 5000 4 // Press R3 to extend the landing gear.
				d5_plane_started = 1
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF d5_m2_reached_runway_corona = 0
					IF NOT d5_landing_gear_extended = 1
						GET_PLANE_UNDERCARRIAGE_POSITION d5_mission_plane d5_landing_gear_position
						IF d5_landing_gear_position < 1.0
							d5_landing_gear_extended = 1
							PRINT_NOW ( DES5_2C ) 5000 4 // Head for the first corona.
						ENDIF
					ENDIF							
					IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m2_corona_x d5_m2_corona_y d5_m2_corona_z d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
						REMOVE_BLIP d5_corona_blip
						DELETE_CHECKPOINT d5_cur_checkpoint
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
						d5_m2_reached_runway_corona = 1
						// temp vars
						ADD_BLIP_FOR_COORD 308.0 d5_runway_centre_y d5_m1_plane_start_z d5_corona_blip
						CLEAR_PRINTS
						PRINT_NOW DES5_2I 5000 4  
						PRINT ( DES5_2D ) 7000 4  // Point the nose of the plane upward to reduce the rate of descent.
					ENDIF
				ELSE
					IS_CAR_STOPPED_IN_AREA_3D d5_mission_plane 288.0 d5_runway_y1 16.0 328.0 d5_runway_y2 18.0 TRUE
					IF NOT d5_m2_started_taxiing = 1
						GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
						IF d5_plane_z <= 17.66
							d5_m2_started_taxiing = 1
							CLEAR_PRINTS
							
							PRINT_NOW ( DES5_2E ) 7000 4
							PRINT ( DES5_2F ) 7000 4 // Taxi along the runway and stop in the target zone for extra points.
							PRINT ( DES5_2G ) 7000 4
						ENDIF
					ELSE
						IF IS_CAR_STOPPED_IN_AREA_2D d5_mission_plane d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
						AND NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
						AND NOT IS_CAR_UPSIDEDOWN d5_mission_plane
							d5_finished_mission = 1
						ENDIF
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					IF NOT d5_m2_reached_runway_corona = 1
						d5_failed_challenge = 1
					ELSE
						IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
							d5_failed_challenge = 1
						ELSE
							IF IS_CAR_UPSIDEDOWN d5_mission_plane
								d5_failed_challenge = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 25
						d5_pass_time = 40
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[1]  	
						d5_old_score = d5_best_scores[1] 
						d5_best_scores[1] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 2
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 4
							d5_last_played = 4
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0
						
						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_landplane																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_landplane																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_landplane:
					GOSUB d5_mini_cleanup

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_landplane
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_landplane_loop
ENDIF

IF d5_mission_selection = 3
OR d5_mission_selection = 4

	d5_refresh_circleairstrip:

	GOSUB d5_start_initialise_stuff

	d5_setup_circleairstrip:

//	GOSUB setting_up_variables
	GOSUB d5_m3_calculate_corona_circle
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_plane_taken_off = 0
	d5_next_corona = 0

	// create mission plane
	CREATE_CAR RUSTLER d5_m1_plane_start_x d5_m1_plane_start_y d5_m1_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 90.0

	IF d5_playback_flag = 0
		SET_PLANE_UNDERCARRIAGE_UP d5_mission_plane TRUE
		SET_FIXED_CAMERA_POSITION -1251.02 2301.32 140.44 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	WAIT 500

	CLEAR_PRINTS

	PRINT_NOW DES5_1A 5000 4

	GOSUB d5_stop_initialise_stuff

	// starting challenge
	d5_circleairstrip_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_circleairstrip																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_circleairstrip
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			
			PRINT_NOW DES5_1A 5000 4

			GOSUB d5_has_plane_started
			IF d5_plane_started = 1
				d5_next_corona = 0
				ADD_BLIP_FOR_COORD d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] d5_corona_blip
				// first checkpoint normal parallel to x-axis
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] 0.0 d5_m3_corona_y[0] d5_m3_corona_z[0] d5_checkpoint_radius d5_m3_checkpoints[0]
				// each new checkpoint's normal points towards the last checkpoint
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[1] d5_m3_corona_y[1] d5_m3_corona_z[1] d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] d5_checkpoint_radius d5_m3_checkpoints[1]
				PRINT_NOW ( DES5_3B ) 10000 4 // Take off and climb steadily until you reach the first corona.
				PRINT ( DES5_3C ) 7000 4 // Press the L2 button to turn left and the R2 button to turn right using the rudder.
				PRINT ( DES5_3D ) 10000 4 // You can use the rudder to turn more quickly while banking.
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF NOT d5_plane_taken_off = 1
					GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
					IF d5_plane_z >= 25.0
						d5_plane_taken_off = 1
					ENDIF
				ENDIF

				IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
					REMOVE_BLIP d5_corona_blip
					DELETE_CHECKPOINT d5_m3_checkpoints[d5_next_corona]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
					d5_next_corona++
					IF d5_next_corona < 8
						ADD_BLIP_FOR_COORD d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_corona_blip
						d5_next_corona_plus_one = d5_next_corona + 1
						IF d5_next_corona_plus_one < 8
							// each new checkpoint's normal points towards the last checkpoint
							CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[d5_next_corona_plus_one] d5_m3_corona_y[d5_next_corona_plus_one] d5_m3_corona_z[d5_next_corona_plus_one] d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_checkpoint_radius d5_m3_checkpoints[d5_next_corona_plus_one]
						ENDIF
						IF d5_next_corona = 1
							IF d5_mission_selection = 4
								
								PRINT_NOW DES5_3F 10000 4 // ~s~Hold the movement controls down and to the right to bank round and head for the next ~y~corona.
																					   
							ELSE													   
								PRINT_NOW DES5_3E 10000 4 // ~s~Hold the movement controls down and to the left to bank round and head for the next ~y~corona.

							ENDIF
						ENDIF
						IF d5_next_corona = 2
							IF d5_mission_selection = 4
								PRINT_NOW ( DES5_3G ) 10000 4 // Bank left and fly through the next corona.
							ELSE
								PRINT_NOW ( DES5_3H ) 10000 4 // Bank right and fly through the next corona.
							ENDIF
						ENDIF
						IF d5_next_corona = 3
							PRINT_NOW ( DES5_3K ) 10000 4 // Keep banking round and follow the coronas.
							PRINT ( DES5_3L ) 7000 4 // The artificial horizon on the radar displays the plane's attitude.
							PRINT ( DES5_3M ) 10000 4 // As the plane banks and pitches, the artificial horizon changes appropriately.
						ENDIF
					ELSE
						d5_finished_mission = 1
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF d5_plane_taken_off = 1
					//IF IS_CAR_STOPPED d5_mission_plane
					IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
						d5_failed_challenge = 1
					ENDIF
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

//				//checking timer hasn't run to 0
//				GOSUB car_timer_0

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 60
						d5_pass_time = 75
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[2]  	
						d5_old_score = d5_best_scores[2] 
						d5_best_scores[2] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 4
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 6
							d5_last_played = 6
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_circleairstrip																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_circleairstrip																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_circleairstrip:
					GOSUB d5_mini_cleanup
					// delete all checkpoints
					d5_index = 0
					WHILE d5_index < 8
						DELETE_CHECKPOINT d5_m3_checkpoints[d5_index]
						d5_index++
					ENDWHILE

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_circleairstrip
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_circleairstrip_loop
ENDIF

IF d5_mission_selection = 5
OR d5_mission_selection = 6

	d5_refresh_circleairstripland:

	GOSUB d5_start_initialise_stuff

	d5_setup_circleairstripland:

//	GOSUB setting_up_variables
	GOSUB d5_m3_calculate_corona_circle
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_plane_taken_off = 0
	d5_next_corona = 0
	d5_m5_finished_following_coronas = 0

	// create mission plane
	CREATE_CAR RUSTLER d5_m1_plane_start_x d5_m1_plane_start_y d5_m1_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 90.0

	IF d5_playback_flag = 0
		SET_PLANE_UNDERCARRIAGE_UP d5_mission_plane TRUE
		SET_FIXED_CAMERA_POSITION -394.09 2601.47 134.89 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	WAIT 500

	CLEAR_PRINTS
	
	PRINT_NOW DES5_1A 5000 4

	GOSUB d5_stop_initialise_stuff

	// starting challenge
	d5_circleairstripland_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_circleairstripland																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_circleairstripland
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			
			PRINT_NOW DES5_1A 5000 4

			GOSUB d5_has_plane_started
			IF d5_plane_started = 1
				d5_next_corona = 0
				ADD_BLIP_FOR_COORD d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] d5_corona_blip
				// first checkpoint normal parallel to x-axis
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] 0.0 d5_m3_corona_y[0] d5_m3_corona_z[0] d5_checkpoint_radius d5_m3_checkpoints[0]
				// each new checkpoint's normal points towards the last checkpoint
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[1] d5_m3_corona_y[1] d5_m3_corona_z[1] d5_m3_corona_x[0] d5_m3_corona_y[0] d5_m3_corona_z[0] d5_checkpoint_radius d5_m3_checkpoints[1]
				PRINT_NOW ( DES5_3B ) 10000 4 // Take off and climb steadily until you reach the first corona.
//				PRINT ( DES5_3C ) 10000 4 // Press the L2 button to turn left and the R2 button to turn right using the rudder.
//				PRINT ( DES5_3D ) 10000 4 // You can use the rudder to turn more quickly while banking.
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF NOT d5_plane_taken_off = 1
					GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
					IF d5_plane_z >= 25.0
						d5_plane_taken_off = 1
					ENDIF
				ENDIF

				IF NOT d5_m5_finished_following_coronas = 1

					IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
						REMOVE_BLIP d5_corona_blip
						DELETE_CHECKPOINT d5_m3_checkpoints[d5_next_corona]
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
						d5_next_corona++
						IF d5_next_corona < 8
							ADD_BLIP_FOR_COORD d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_corona_blip
							d5_next_corona_plus_one = d5_next_corona + 1
							IF d5_next_corona_plus_one < 8
								// each new checkpoint's normal points towards the last checkpoint
								CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m3_corona_x[d5_next_corona_plus_one] d5_m3_corona_y[d5_next_corona_plus_one] d5_m3_corona_z[d5_next_corona_plus_one] d5_m3_corona_x[d5_next_corona] d5_m3_corona_y[d5_next_corona] d5_m3_corona_z[d5_next_corona] d5_checkpoint_radius d5_m3_checkpoints[d5_next_corona_plus_one]
							ENDIF
							IF d5_next_corona = 1
								IF d5_mission_selection = 6
									PRINT_NOW ( DES5_3H ) 10000 4 // Bank right and fly through the next corona.
								ELSE
									PRINT_NOW ( DES5_3G ) 10000 4 // Bank left and fly through the next corona.
								ENDIF
							ENDIF
							IF d5_next_corona = 2
								IF d5_mission_selection = 6
									PRINT_NOW ( DES5_3G ) 10000 4 // Bank left and fly through the next corona.
								ELSE
									PRINT_NOW ( DES5_3H ) 10000 4 // Bank right and fly through the next corona.
								ENDIF
							ENDIF
							IF d5_next_corona = 3
								PRINT_NOW ( DES5_3K ) 10000 4 // Keep banking round and follow the coronas.
							ENDIF
						ELSE
							d5_m5_finished_following_coronas = 1
							ADD_BLIP_FOR_COORD 308.0 d5_runway_y1 d5_m1_plane_start_z d5_corona_blip
							IF d5_mission_selection = 6
								PRINT_NOW ( DES5_5I ) 10000 4 // Bank right and land on the runway.
							ELSE
								PRINT_NOW ( DES5_5H ) 10000 4 // Bank left and land on the runway.
							ENDIF
							PRINT ( DES5_5J ) 10000 4 // Stop in the target zone for extra points.
						ENDIF
					ENDIF

				ELSE
					IS_CAR_STOPPED_IN_AREA_3D d5_mission_plane 288.0 d5_runway_y1 16.0 328.0 d5_runway_y2 18.0 TRUE
					IF IS_CAR_STOPPED_IN_AREA_2D d5_mission_plane d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
					AND NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					AND NOT IS_CAR_UPSIDEDOWN d5_mission_plane
						d5_finished_mission = 1
//					ELSE
//						IF IS_CAR_STOPPED d5_mission_plane
//							// check that player hasn't stopped outside the runway area
//							IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
//								d5_failed_challenge = 1
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
//				IF d5_plane_taken_off = 1
//				AND NOT d5_m5_finished_following_coronas = 1
//					IF IS_CAR_STOPPED d5_mission_plane
//						d5_failed_challenge = 1
//					ENDIF
//				ENDIF
				IF d5_plane_taken_off = 1
				AND NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					IF NOT d5_m5_finished_following_coronas = 1
						d5_failed_challenge = 1
					ELSE
						IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
							d5_failed_challenge = 1
						ELSE
							IF IS_CAR_UPSIDEDOWN d5_mission_plane
								d5_failed_challenge = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

//				//checking timer hasn't run to 0
//				GOSUB car_timer_0

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 80
						d5_pass_time = 95
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[3]  	
						d5_old_score = d5_best_scores[3] 
						d5_best_scores[3] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 6
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 7
							d5_last_played = 7
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_circleairstripland																		  
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_circleairstripland																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_circleairstripland:
					GOSUB d5_mini_cleanup
					// delete all checkpoints
					d5_index = 0
					WHILE d5_index < 8
						DELETE_CHECKPOINT d5_m3_checkpoints[d5_index]
						d5_index++
					ENDWHILE

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_circleairstripland
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_circleairstripland_loop
ENDIF

IF d5_mission_selection = 7

	d5_refresh_helitakeoff:

	GOSUB d5_start_initialise_stuff

	d5_setup_helitakeoff:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_plane_taken_off = 0
	d5_plane_reached_required_altitude = 0
	d5_required_altitude = d5_m7_corona_z[0]
	d5_m7_rotated_180 = 0

	// create mission plane
	CREATE_CAR HUNTER d5_m1_plane_start_x d5_m1_plane_start_y 15.07 d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	CAR_SET_IDLE d5_mission_plane

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 323.02 2446.81 117.65 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	LVAR_INT d5_mission_plane_moving
	d5_mission_plane_moving = 1
	WHILE d5_mission_plane_moving = 1
		WAIT 0
		IF NOT IS_CAR_DEAD d5_mission_plane
			IF IS_CAR_STOPPED d5_mission_plane
				d5_mission_plane_moving = 0
			ENDIF
		ENDIF
	ENDWHILE

	WAIT 500

	CLEAR_PRINTS
	
    PRINT_NOW DES5_7A 5000 4  

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_helitakeoff_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_helitakeoff																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_helitakeoff
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			
			PRINT_NOW DES5_7A 10000 4  

			IF NOT IS_CAR_DEAD d5_mission_plane
				IF IS_CAR_IN_AIR_PROPER d5_mission_plane
					DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
					PRINT_NOW ( DES5_7B ) 10000 4 // Keep gaining height.
					d5_plane_started = 1
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane
				// temp
				//SET_CAMERA_BEHIND_PLAYER
				IF NOT d5_plane_reached_required_altitude = 1
					GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
					IF d5_plane_z >= d5_required_altitude
						d5_plane_reached_required_altitude = 1
						
                        PRINT_NOW DES5_7C 5000 4 
						
                        PRINT DES5_7D 10000 4 
						
						//PRINT_HELP DES5_7E // Press the L2 button to turn left and the R2 button to turn right.
					ENDIF
				ENDIF
				IF d5_plane_reached_required_altitude = 1
					IF NOT d5_m7_rotated_180 = 1
						GET_CAR_HEADING d5_mission_plane d5_plane_heading
						IF d5_plane_heading >= 80.0
						AND d5_plane_heading <= 100.0
							d5_m7_rotated_180 = 1
							ADD_BLIP_FOR_COORD d5_m7_corona_x[0] d5_m7_corona_y[0] d5_m7_corona_z[0] d5_corona_blip
							CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m7_corona_x[0] d5_m7_corona_y[0] d5_m7_corona_z[0] 0.0 d5_m7_corona_y[0] d5_m7_corona_z[0] d5_checkpoint_radius d5_cur_checkpoint
							CLEAR_PRINTS
														
							PRINT_NOW DES5_7F 10000 4 
                            
                            PRINT DES5_7G 10000 4 
							
                            PRINT DES5_7H 10000 4 
                            PRINT DES5_7I 10000 4 
							
						ENDIF
					ELSE
						IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m7_corona_x[0] d5_m7_corona_y[0] d5_m7_corona_z[0] d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
							REMOVE_BLIP d5_corona_blip
							DELETE_CHECKPOINT d5_cur_checkpoint
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							d5_finished_mission = 1
						ENDIF
					ENDIF
				ENDIF

				IF NOT d5_plane_taken_off = 1
					GET_CAR_COORDINATES d5_mission_plane d5_plane_x d5_plane_y d5_plane_z
					IF d5_plane_z >= 25.0
						d5_plane_taken_off = 1
					ENDIF
				ENDIF
				// check for player stopped (prevents player from getting stuck in ground)
				IF d5_plane_taken_off = 1
					IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
						d5_failed_challenge = 1
					ENDIF
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 45
						d5_pass_time = 60
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[4]  	
						d5_old_score = d5_best_scores[4] 
						d5_best_scores[4] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 7
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 8
							d5_last_played = 8
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						If IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_helitakeoff																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_helitakeoff																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_helitakeoff:
					GOSUB d5_mini_cleanup

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_helitakeoff
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_helitakeoff_loop
ENDIF

IF d5_mission_selection = 8 

	d5_refresh_landheli:

	GOSUB d5_start_initialise_stuff

	d5_setup_landheli:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_m8_player_reached_hovering_area = 0

	// create mission plane
	CREATE_CAR HUNTER d5_m8_plane_start_x d5_m8_plane_start_y d5_m8_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	SET_HELI_BLADES_FULL_SPEED d5_mission_plane

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 301.56 2455.21 55.82 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	FREEZE_CAR_POSITION d5_mission_plane TRUE

	WAIT 500

	CLEAR_PRINTS
	
	PRINT_NOW DES5_8A 5000 4  

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_landheli_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_landheli																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_landheli
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			IF NOT IS_CAR_DEAD d5_mission_plane
				FREEZE_CAR_POSITION d5_mission_plane FALSE
				SET_CAR_FORWARD_SPEED d5_mission_plane 15.0
				DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				
				PRINT_NOW DES5_8A 10000 4  

				PRINT ( DES5_8B ) 10000 4 // Head for the far end of the runway and maintain your height.
				ADD_BLIP_FOR_COORD d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z d5_corona_blip
				d5_plane_started = 1
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane
				
				IF NOT d5_m8_player_reached_hovering_area = 1
					IF LOCATE_CHAR_IN_CAR_2D scplayer d5_m8_landing_target_x d5_m8_landing_target_y 300.0 300.0 FALSE
						d5_m8_player_reached_hovering_area = 1
						
						PRINT_NOW DES5_8G 4000 4

						PRINT ( DES5_8C ) 10000 4 // Slow down and hover over the target zone.
						
						PRINT DES5_8D 10000 4 // Hold the ~Q~ button to descend slowly and land.~N~Land in the target area for extra points.
                        PRINT DES5_8E 10000 4 
                        
					ENDIF
				ELSE
					LOCATE_STOPPED_CAR_3D d5_mission_plane d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z 26.31 26.31 26.31 TRUE
					IF IS_CAR_STOPPED_IN_AREA_2D d5_mission_plane d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
					AND NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					AND NOT IS_CAR_UPSIDEDOWN d5_mission_plane
						d5_finished_mission = 1
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					IF NOT d5_m8_player_reached_hovering_area = 1
						d5_failed_challenge = 1
					ELSE
						IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
							d5_failed_challenge = 1
						ELSE
							IF IS_CAR_UPSIDEDOWN d5_mission_plane
								d5_failed_challenge = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 30
						d5_pass_time = 45
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[5]  	
						d5_old_score = d5_best_scores[5] 
						d5_best_scores[5] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 8
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 9
							d5_last_played = 9
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_landheli																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_landheli																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_landheli:
					GOSUB d5_mini_cleanup

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_landheli
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_landheli_loop
ENDIF

IF d5_mission_selection = 9

	d5_refresh_destroytargets:

	GOSUB d5_start_initialise_stuff

	d5_setup_destroytargets:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_m9_heli_landing = 0
	//d5_m9_targets_remaining = 0

	// create mission plane
	CREATE_CAR HUNTER d5_m8_plane_start_x d5_m8_plane_start_y d5_m8_plane_start_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	SET_HELI_BLADES_FULL_SPEED d5_mission_plane

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 365.5 2339.25 63.23 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 363.09 2473.76 33.46 JUMP_CUT
		d5_m9_first_wave_car_x = 418.5
		d5_index = 0
		WHILE d5_index < d5_m9_num_of_first_wave_cars
			CREATE_CAR PETRO d5_m9_first_wave_car_x d5_runway_centre_y 17.1 d5_m9_first_wave_cars[d5_index]
			d5_m9_first_wave_car_x -= 40.0
			d5_m9_car_exploded[d5_index] = 0
			d5_index++
		ENDWHILE
		TIMERB = 0
		RETURN
	ENDIF

	FREEZE_CAR_POSITION d5_mission_plane TRUE

	WAIT 500

	CLEAR_PRINTS
	PRINT_NOW ( DES5_9A ) 5000 4  // ~s~Destroy the three ~r~trucks ~s~at the end of the runway.

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_destroytargets_loop:
	WAIT 0

		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_destroytargets																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_destroytargets
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			IF NOT IS_CAR_DEAD d5_mission_plane
				FREEZE_CAR_POSITION d5_mission_plane FALSE
				SET_CAR_FORWARD_SPEED d5_mission_plane 15.0
				//d5_m9_targets_remaining = 3
				//DISPLAY_ONSCREEN_COUNTER_WITH_STRING d5_m9_targets_remaining COUNTER_DISPLAY_NUMBER ( DES5_9F )
				DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				
				PRINT_NOW ( DES5_9A ) 10000 4 // ~s~Destroy the three ~r~trucks ~s~at the end of the runway.
				
				PRINT DES5_9B 10000 4

				// create first wave of cars
				d5_m9_first_wave_car_x = 418.5
				d5_index = 0
				WHILE d5_index < d5_m9_num_of_first_wave_cars
					CREATE_CAR PETRO d5_m9_first_wave_car_x d5_runway_centre_y 17.1 d5_m9_first_wave_cars[d5_index]
					ADD_BLIP_FOR_CAR d5_m9_first_wave_cars[d5_index] d5_m9_first_wave_car_blips[d5_index]
					d5_m9_first_wave_blip_flags[d5_index] = 1
					d5_m9_first_wave_car_x -= 40.0
					d5_index++
				ENDWHILE
				d5_m9_current_wave = 0
				d5_plane_started = 1
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane
				// temp
				//SET_CAMERA_BEHIND_PLAYER

				IF NOT d5_m9_heli_landing = 1
					d5_m9_all_cars_in_current_wave_destroyed = 1
					SWITCH d5_m9_current_wave

						CASE 0
							d5_index = 0
							WHILE d5_index < d5_m9_num_of_first_wave_cars
								IF IS_CAR_DEAD d5_m9_first_wave_cars[d5_index]
									// remove blips if cars destroyed
									IF d5_m9_first_wave_blip_flags[d5_index] = 1
										REMOVE_BLIP d5_m9_first_wave_car_blips[d5_index]
										d5_m9_first_wave_blip_flags[d5_index] = 0
										//d5_m9_targets_remaining--
									ENDIF
								ELSE
									d5_m9_all_cars_in_current_wave_destroyed = 0
								ENDIF
								d5_index++
							ENDWHILE
							IF d5_m9_all_cars_in_current_wave_destroyed = 1
								d5_m9_current_wave = 1
								CREATE_CAR LANDSTAL 22.61 2322.06 22.35 d5_m9_second_wave_cars[0]
								CREATE_CAR LANDSTAL 74.58 2375.99 16.52 d5_m9_second_wave_cars[1]
								CREATE_CHAR_INSIDE_CAR d5_m9_second_wave_cars[0] PEDTYPE_MISSION1 MALE01 d5_m9_second_wave_car_drivers[0]
								CREATE_CHAR_INSIDE_CAR d5_m9_second_wave_cars[1] PEDTYPE_MISSION1 MALE01 d5_m9_second_wave_car_drivers[1]
								FLUSH_ROUTE
								EXTEND_ROUTE 76.44 2327.46 20.63
								EXTEND_ROUTE 74.58 2375.99 16.52
								EXTEND_ROUTE 19.12 2370.77 18.97
								EXTEND_ROUTE 22.61 2322.06 22.35
								OPEN_SEQUENCE_TASK d5_second_wave_seq0
									TASK_DRIVE_POINT_ROUTE -1 d5_m9_second_wave_cars[0] 10.0
									SET_SEQUENCE_TO_REPEAT d5_second_wave_seq0 1
								CLOSE_SEQUENCE_TASK d5_second_wave_seq0
								PERFORM_SEQUENCE_TASK d5_m9_second_wave_car_drivers[0] d5_second_wave_seq0
								CLEAR_SEQUENCE_TASK d5_second_wave_seq0
								FLUSH_ROUTE
								EXTEND_ROUTE 19.12 2370.77 18.97
								EXTEND_ROUTE 22.61 2322.06 22.35
								EXTEND_ROUTE 76.44 2327.46 20.63
								EXTEND_ROUTE 74.58 2375.99 16.52
								OPEN_SEQUENCE_TASK d5_second_wave_seq1
									TASK_DRIVE_POINT_ROUTE -1 d5_m9_second_wave_cars[1] 10.0
									SET_SEQUENCE_TO_REPEAT d5_second_wave_seq1 1
								CLOSE_SEQUENCE_TASK d5_second_wave_seq1
								PERFORM_SEQUENCE_TASK d5_m9_second_wave_car_drivers[1] d5_second_wave_seq1
								CLEAR_SEQUENCE_TASK d5_second_wave_seq1
								d5_index = 0
								WHILE d5_index < d5_m9_num_of_second_wave_cars
									LOCK_CAR_DOORS d5_m9_second_wave_cars[d5_index] CARLOCK_LOCKED
									ADD_BLIP_FOR_CAR d5_m9_second_wave_cars[d5_index] d5_m9_second_wave_car_blips[d5_index]
									d5_m9_second_wave_blip_flags[d5_index] = 1
									d5_index++
								ENDWHILE
								//d5_m9_targets_remaining = 2
								//CLEAR_ONSCREEN_COUNTER d5_m9_targets_remaining
								//DISPLAY_ONSCREEN_COUNTER_WITH_STRING d5_m9_targets_remaining COUNTER_DISPLAY_NUMBER ( DES5_9F )
								PRINT_NOW ( DES5_9D ) 10000 4 // Now destroy the two moving cars to the south-west of the runway.
								d5_index = 0
								WHILE d5_index < d5_m9_num_of_first_wave_cars
									MARK_CAR_AS_NO_LONGER_NEEDED d5_m9_first_wave_cars[d5_index]
									d5_index++
								ENDWHILE
							ENDIF
							BREAK

						CASE 1
							d5_index = 0
							WHILE d5_index < d5_m9_num_of_second_wave_cars
								IF IS_CAR_DEAD d5_m9_second_wave_cars[d5_index]
									// remove blips if cars destroyed
									IF d5_m9_second_wave_blip_flags[d5_index] = 1
										REMOVE_BLIP d5_m9_second_wave_car_blips[d5_index]
										d5_m9_second_wave_blip_flags[d5_index] = 0
										//d5_m9_targets_remaining--
									ENDIF
								ELSE
									d5_m9_all_cars_in_current_wave_destroyed = 0
								ENDIF
								d5_index++
							ENDWHILE
							IF d5_m9_all_cars_in_current_wave_destroyed = 1
								d5_m9_current_wave = -1
								//CLEAR_ONSCREEN_COUNTER d5_m9_targets_remaining
								ADD_BLIP_FOR_COORD d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z d5_corona_blip
								PRINT_NOW ( DES5_9E ) 10000 4 // Land on the runway.
								PRINT ( DES5_5J ) 10000 4 // Stop in the target zone for extra points.
								d5_m9_heli_landing = 1
							ENDIF
							BREAK

						DEFAULT
							d5_m9_heli_landing = 1
							BREAK

					ENDSWITCH
				ELSE
					LOCATE_STOPPED_CAR_3D d5_mission_plane d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z 26.31 26.31 26.31 TRUE
					IF IS_CAR_STOPPED_IN_AREA_2D d5_mission_plane d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
					AND NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					AND NOT IS_CAR_UPSIDEDOWN d5_mission_plane
						d5_finished_mission = 1
//					ELSE
//						IF IS_CAR_STOPPED d5_mission_plane
//							// check that player hasn't stopped outside the runway area
//							IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
//								d5_failed_challenge = 1
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
//				IF NOT d5_m9_heli_landing = 1
//					IF IS_CAR_STOPPED d5_mission_plane
//						d5_failed_challenge = 1
//					ENDIF
//				ENDIF
				IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					IF NOT d5_m9_heli_landing = 1
						d5_failed_challenge = 1
					ELSE
						IF NOT IS_CHAR_IN_AREA_2D scplayer d5_runway_x1 d5_runway_y1 d5_runway_x2 d5_runway_y2 FALSE
							d5_failed_challenge = 1
						ELSE
							IF IS_CAR_UPSIDEDOWN d5_mission_plane
								d5_failed_challenge = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1																		  
				ENDIF

//				//checking timer hasn't run to 0
//				GOSUB car_timer_0

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 80
						d5_pass_time = 105
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[6]  	
						d5_old_score = d5_best_scores[6] 
						d5_best_scores[6] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 9
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 10
							d5_last_played = 10
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						If IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_destroytargets																		  
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_destroytargets																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_destroytargets:
					GOSUB d5_mini_cleanup
					// clean up any cars/blips that might be left
					CLEAR_SEQUENCE_TASK d5_second_wave_seq0
					CLEAR_SEQUENCE_TASK d5_second_wave_seq1
					d5_index = 0
					WHILE d5_index < d5_m9_num_of_first_wave_cars
						DELETE_CAR d5_m9_first_wave_cars[d5_index]
						REMOVE_BLIP d5_m9_first_wave_car_blips[d5_index]
						d5_index++
					ENDWHILE
					d5_index = 0
					WHILE d5_index < d5_m9_num_of_second_wave_cars
						DELETE_CHAR d5_m9_second_wave_car_drivers[d5_index]
						DELETE_CAR d5_m9_second_wave_cars[d5_index]
						REMOVE_BLIP d5_m9_second_wave_car_blips[d5_index]
						d5_index++
					ENDWHILE
					//CLEAR_ONSCREEN_COUNTER d5_m9_targets_remaining

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_destroytargets
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_destroytargets_loop
ENDIF

IF d5_mission_selection = 10
				 
	d5_refresh_looptheloop:

	GOSUB d5_start_initialise_stuff

	d5_setup_looptheloop:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	//GOSUB d5_m10_calculate_looptheloop_coronas
	d5_m10_player_passed_starting_corona = 0
	d5_m10_completed_stunt = 0

	// create mission plane
	CREATE_CAR STUNT d5_m8_plane_start_x d5_m10_start_corona_y d5_m10_start_corona_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	//CHANGE_CAR_COLOUR d5_mission_plane 7 53
	
	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 269.53 2445.3 88.46 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	FREEZE_CAR_POSITION d5_mission_plane TRUE

	WAIT 500

	CLEAR_PRINTS
	
    PRINT_NOW DES5_AA 5000 4
	
	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_looptheloop_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_looptheloop																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_looptheloop
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			IF NOT IS_CAR_DEAD d5_mission_plane
				FREEZE_CAR_POSITION d5_mission_plane FALSE
				SET_CAR_FORWARD_SPEED d5_mission_plane 20.0
				d5_plane_started = 1
				ADD_BLIP_FOR_COORD d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_corona_blip
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z 0.0 d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_m10_start_checkpoint
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z 0.0 d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_m10_end_checkpoint
				DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				
				PRINT_NOW DES5_AA 7000 4
				PRINT DES5_AB 10000 4
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF NOT d5_m10_player_passed_starting_corona = 1
					IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
						REMOVE_BLIP d5_corona_blip
 						DELETE_CHECKPOINT d5_m10_start_checkpoint
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
 						d5_m10_player_passed_starting_corona = 1
						
						PRINT_NOW DES5_AC 5000 4
						
 						GET_CAR_PITCH d5_mission_plane d5_last_pitch
 						d5_pitch_progress = 0.0
 						d5_negative_progress = 0.0
					ENDIF
				ELSE

					IF NOT d5_m10_completed_stunt = 1

						GET_CAR_ROLL d5_mission_plane d5_cur_roll
						IF d5_cur_roll < 0.0
							d5_cur_roll += 360.0
						ENDIF
						GET_CAR_UPRIGHT_VALUE d5_mission_plane d5_plane_up_component
						// different limits for roll depending on whether plane's upside down or not
						IF d5_plane_up_component >= 0.0 // right way up
							IF d5_cur_roll >= 20.0
							AND d5_cur_roll <= 340.0
								d5_failed_challenge = 1
							ENDIF
						ELSE // upside down
							IF d5_cur_roll <= 160.0
							OR d5_cur_roll >= 200.0
								d5_failed_challenge = 1
							ENDIF
						ENDIF

						GET_CAR_PITCH d5_mission_plane d5_cur_pitch
						d5_pitch_diff = d5_cur_pitch - d5_last_pitch
						d5_last_pitch = d5_cur_pitch

						// compensate for cur_pitch and last_pitch being on opposite sides of zero degrees
						// (eg. if cur_pitch is	359 and last_pitch is 1, real distance moved is 2, not 358.)
						IF d5_pitch_diff > 180.0
							d5_pitch_diff -= 360.0
						ENDIF
						IF d5_pitch_diff < -180.0
							d5_pitch_diff += 360.0
						ENDIF

						IF d5_pitch_diff < 0.0
							d5_negative_progress += d5_pitch_diff
						ELSE
							IF d5_negative_progress < 0.0
								d5_negative_progress += d5_pitch_diff
							ELSE
								d5_pitch_progress += d5_negative_progress
								d5_negative_progress = 0.0
								d5_pitch_progress += d5_pitch_diff
							ENDIF
						ENDIF

						IF d5_negative_progress < -20.0
							d5_failed_challenge = 1
						ENDIF
						IF NOT d5_failed_challenge = 1
							// done full loop-the-loop
							IF d5_pitch_progress >= 360.0
								d5_m10_completed_stunt = 1
								ADD_BLIP_FOR_COORD d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_corona_blip
								PRINT_NOW ( DES5_AD ) 10000 4 // Fly through the final corona.
							ENDIF
						ENDIF

//						// temp
//						VIEW_FLOAT_VARIABLE d5_pitch_diff d5_pitch_diff
//						VIEW_FLOAT_VARIABLE d5_negative_progress d5_negative_progress
//						VIEW_FLOAT_VARIABLE d5_pitch_progress d5_pitch_progress

					ELSE

						IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
							REMOVE_BLIP d5_corona_blip
	 						DELETE_CHECKPOINT d5_m10_end_checkpoint
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							d5_finished_mission = 1
						ENDIF

					ENDIF

				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					d5_failed_challenge = 1
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 21
						d5_pass_time = 27
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[7]  	
						d5_old_score = d5_best_scores[7] 
						d5_best_scores[7] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 10
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 11
							d5_last_played = 11
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_looptheloop																		  
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_looptheloop																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_looptheloop:
					GOSUB d5_mini_cleanup
					DELETE_CHECKPOINT d5_m10_start_checkpoint
					DELETE_CHECKPOINT d5_m10_end_checkpoint

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_looptheloop
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_looptheloop_loop
ENDIF

IF d5_mission_selection = 11
				 
	d5_refresh_barrelroll:

	GOSUB d5_start_initialise_stuff

	d5_setup_barrelroll:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_m10_player_passed_starting_corona = 0
	d5_m10_completed_stunt = 0

	// create mission plane
	CREATE_CAR STUNT d5_m8_plane_start_x d5_m10_start_corona_y d5_m10_start_corona_z d5_mission_plane
	SET_CAR_HEADING d5_mission_plane 270.0
	//CHANGE_CAR_COLOUR d5_mission_plane 7 53

	IF d5_playback_flag = 0
		SET_FIXED_CAMERA_POSITION 292.95 2445.3 93.46 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR d5_mission_plane FIXED JUMP_CUT
		RETURN
	ENDIF

	FREEZE_CAR_POSITION d5_mission_plane TRUE

	WAIT 500

	CLEAR_PRINTS

	PRINT_NOW DES5_AA 5000 4

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_barrelroll_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking player hasn't left plane
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_barrelroll																		  
		ENDIF 

		// checking plane isn't dead
		GOSUB d5_mission_plane_dead_check
		IF d5_mission_plane_dead_flag = 1
			IF IS_CAR_IN_WATER d5_mission_plane
				d5_mission_plane_dead_flag = 2
				GOTO d5_after_scores_barrelroll
			ELSE
				GOTO mission_desert5_failed
			ENDIF
		ENDIF

		// checking if plane has moved
		IF d5_plane_started = 0
			IF NOT IS_CAR_DEAD d5_mission_plane
				FREEZE_CAR_POSITION d5_mission_plane FALSE
				SET_CAR_FORWARD_SPEED d5_mission_plane 20.0
				d5_plane_started = 1
				ADD_BLIP_FOR_COORD d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_corona_blip
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z 0.0 d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_m10_start_checkpoint
				CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z 0.0 d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_m10_end_checkpoint
				DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )

				PRINT_NOW DES5_AA 7000 4
				
                PRINT DES5_BB 10000 4
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d5_mission_plane

				IF NOT d5_m10_player_passed_starting_corona = 1
					IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m10_start_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
						REMOVE_BLIP d5_corona_blip
 						DELETE_CHECKPOINT d5_m10_start_checkpoint
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
 						d5_m10_player_passed_starting_corona = 1
 						CLEAR_PRINTS
						
						PRINT_NOW DES5_BC 5000 4
						
						GET_CAR_ROLL d5_mission_plane d5_last_roll
						IF d5_last_roll < 0.0
							d5_last_roll += 360.0
						ENDIF
 						d5_roll_progress = 0.0
 						d5_negative_progress = 0.0
					ENDIF
				ELSE

					IF NOT d5_m10_completed_stunt = 1

						GET_CAR_ROLL d5_mission_plane d5_cur_roll
						IF d5_cur_roll < 0.0
							d5_cur_roll += 360.0
						ENDIF
						d5_roll_diff = d5_cur_roll - d5_last_roll
						d5_last_roll = d5_cur_roll

						// compensate for cur_roll and last_roll being on opposite sides of zero degrees
						// (eg. if cur_roll is 359 and last_roll is 1, real distance moved is 2, not 358.)
						IF d5_roll_diff > 180.0
							d5_roll_diff -= 360.0
						ENDIF
						IF d5_roll_diff < -180.0
							d5_roll_diff += 360.0
						ENDIF

						IF d5_roll_diff < 0.0
							d5_negative_progress += d5_roll_diff
						ELSE
							IF d5_negative_progress < 0.0
								d5_negative_progress += d5_roll_diff
							ELSE
								d5_roll_progress += d5_negative_progress
								d5_negative_progress = 0.0
								d5_roll_progress += d5_roll_diff
							ENDIF
						ENDIF

						IF d5_negative_progress < -20.0
							d5_failed_challenge = 1
						ELSE
							// done full barrel roll
							IF d5_roll_progress >= 360.0
								d5_m10_completed_stunt = 1
								ADD_BLIP_FOR_COORD d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_corona_blip
								PRINT_NOW ( DES5_AD ) 10000 4 // Fly through the final corona.
							ENDIF
						ENDIF

//						// temp
//						VIEW_FLOAT_VARIABLE d5_roll_diff d5_roll_diff
//						VIEW_FLOAT_VARIABLE d5_negative_progress d5_negative_progress
//						VIEW_FLOAT_VARIABLE d5_roll_progress d5_roll_progress

					ELSE

						IF LOCATE_CHAR_IN_CAR_3D scplayer d5_m10_end_corona_x d5_m10_start_corona_y d5_m10_start_corona_z d5_checkpoint_radius d5_checkpoint_radius d5_checkpoint_radius FALSE
							REMOVE_BLIP d5_corona_blip
	 						DELETE_CHECKPOINT d5_m10_end_checkpoint
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							d5_finished_mission = 1
						ENDIF

					ENDIF

				ENDIF

				// check for player stopped (prevents player from getting stuck in ground)
				IF NOT IS_CAR_IN_AIR_PROPER d5_mission_plane
					d5_failed_challenge = 1
				ENDIF

				// checking player hasn't flown too far away from the airstrip
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
					d5_failed_challenge = 1
				ENDIF

				// checking if finished challenge
				IF d5_finished_mission = 1
				OR d5_failed_challenge = 1
//				OR car_timer = 0

					GOSUB d5_freeze_plane_pos
				
					// calculate player score
					IF d5_finished_mission = 1
						GOSUB d5_calculate_position_score
						GOSUB d5_calculate_damage_penalty
						d5_perfect_time = 18
						d5_pass_time = 23
						GOSUB d5_calculate_time_score
						GOSUB d5_calculate_overall_score
					ELSE
						overall_secs = 0
						position_score = 0
						d5_damage_penalty = 0
						overall_score = 0
					ENDIF
					// checking overall score against the best score at present
					IF overall_score > d5_best_scores[8]  	
						d5_old_score = d5_best_scores[8] 
						d5_best_scores[8] = overall_score
						d5_print_top_scores_flag = 1
						GOSUB d5_medal_check
					ENDIF 	
					//opening next level
					IF d5_total_missions_open = 11
						IF overall_score > 69
							INCREMENT_INT_STAT FLYING_SKILL 60
							d5_print_top_scores_flag = 2
							d5_total_missions_open = 12
							d5_last_played = 12
							d5_mission_plane_dead_flag = 2
						ENDIF
					ENDIF

					GOSUB d5_display_scores

					// infinite loop
					WHILE d5_fake_creates = 0

						WAIT 0

						GOSUB d5_draw_record_text

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							d5_mission_plane_dead_flag = 2
							GOTO d5_after_scores_barrelroll																		  
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							GOTO d5_after_scores_barrelroll																		  
						ENDIF

					ENDWHILE

					// resetting for another try
					d5_after_scores_barrelroll:
					GOSUB d5_mini_cleanup
					DELETE_CHECKPOINT d5_m10_start_checkpoint
					DELETE_CHECKPOINT d5_m10_end_checkpoint

					IF d5_mission_plane_dead_flag = 2 
						GOTO d5_noticeboard_setup
					ELSE
						GOTO d5_refresh_barrelroll
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO d5_barrelroll_loop
ENDIF

IF d5_mission_selection = 12 

	d5_refresh_parachute:

	GOSUB d5_start_initialise_stuff

	d5_setup_parachute:

//	GOSUB setting_up_variables
	car_timer = 0
	d5_print_top_scores_flag = 0
	d5_mission_plane_dead_flag = 0
	d5_plane_started = 0
	d5_finished_mission = 0
	d5_failed_challenge = 0
	d5_m12_player_reached_opening_altitude = 0
	d5_m12_player_opened_parachute = 0

	IF d5_playback_flag = 0
		// creating parachuting guy and starting parachute script
		CREATE_CHAR PEDTYPE_MISSION1 0 d5_runway_x2 d5_runway_centre_y 250.0 d5_m12_stunt_double
		SET_CHAR_HEADING d5_m12_stunt_double 90.0
		FREEZE_CHAR_POSITION d5_m12_stunt_double TRUE
		ai_target_x = d5_m12_parachute_target_x
		ai_target_y = d5_m12_parachute_target_y
		ai_target_z = d5_m12_parachute_target_z
		START_NEW_STREAMED_SCRIPT parachute.sc -1 -10.0 10.0 -5.0 -2.0 10.0 12.0 TRUE d5_m12_stunt_double
		STREAM_SCRIPT parachute.sc

		ATTACH_CAMERA_TO_CHAR_LOOK_AT_CHAR d5_m12_stunt_double 0.0 -5.0 4.0 d5_m12_stunt_double 0.0 JUMP_CUT
		RETURN
	ENDIF

	SET_CHAR_COORDINATES scplayer d5_m12_parachute_start_x d5_m12_parachute_start_y d5_m12_parachute_start_z
	SET_CHAR_HEADING scplayer 90.0
	// give the player a parachute
	REQUEST_MODEL gun_para
	WHILE NOT HAS_MODEL_LOADED gun_para
		WAIT 0
	ENDWHILE
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
	para_float_Vy = 5.0
	para_float_Vz = -5.0
	para_flare_Vy = 8.5
	para_flare_Vz = -1.5
	para_freefall_Vz = -30.0
	para_freefall_Vy = 15.0
	Para_pickup_flag = 0
	WHILE player_has_parachute < 2
		WAIT 0
	ENDWHILE
	//POINT_CAMERA_AT_CHAR scplayer TOP_DOWN_PED JUMP_CUT
	//ATTACH_CAMERA_TO_CHAR_LOOK_AT_CHAR scplayer 0.0 0.0 50.0 scplayer 0.0 JUMP_CUT
	CREATE_OBJECT basejump_target d5_m12_parachute_target_x d5_m12_parachute_target_y d5_m12_parachute_target_z d5_m12_parachute_target
	SET_OBJECT_HEADING d5_m12_parachute_target 90.0
	CREATE_CHECKPOINT CHECKPOINT_TORUS d5_m12_parachute_target_x d5_m12_parachute_target_y d5_m12_parachute_target_z d5_m12_parachute_target_x d5_m12_parachute_target_y 900.0 6.5 d5_m12_checkpoint

	WAIT 500

	CLEAR_PRINTS
	
	PRINT_NOW DES5_CA 7000 4

	PRINT ( DES5_CI ) 10000 4  // Use the movement controls to slow your descent and to rotate.

	GOSUB d5_stop_initialise_stuff

	//starting challenge
	d5_parachute_loop:
	WAIT 0

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
		// checking if player's pressed triangle to quit out
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			d5_mission_plane_dead_flag = 2
			GOTO d5_after_scores_parachute																		  
		ENDIF

		// checking if player has started the jump
		IF d5_plane_started = 0
			DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
			ADD_BLIP_FOR_COORD d5_m12_parachute_target_x d5_m12_parachute_target_y d5_m12_parachute_target_z d5_corona_blip
			d5_plane_started = 1
		ELSE

			IF NOT d5_m12_player_opened_parachute = 1
				IF NOT d5_m12_player_reached_opening_altitude = 1
					GET_CHAR_COORDINATES scplayer d5_player_x d5_player_y d5_player_z
					IF d5_player_z <= 300.0
						d5_m12_player_reached_opening_altitude = 1
						
						PRINT_NOW DES5_CC 10000 4
						
					ENDIF
				ENDIF
				
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					d5_m12_player_opened_parachute = 1
					CLEAR_PRINTS
					PRINT_NOW ( DES5_CD ) 7000 4 // Land on the target.~N~Land closer to the centre for extra points.
					PRINT ( DES5_CF ) 7000 4
					PRINT ( DES5_CH ) 7000 4
					PRINT ( DES5_CE ) 7000 4
				ENDIF
			ENDIF
			IF d5_m12_player_opened_parachute = 1
				IF player_landed = 1
					GET_CHAR_COORDINATES scplayer d5_player_x d5_player_y d5_player_z
					GET_DISTANCE_BETWEEN_COORDS_2D d5_player_x d5_player_y d5_m12_parachute_target_x d5_m12_parachute_target_y d5_m12_distance_from_player_to_target
					IF d5_m12_distance_from_player_to_target <= 10.0
						d5_finished_mission = 1
					ELSE
						d5_failed_challenge = 1
					ENDIF
				ENDIF
			ENDIF

			// checking player hasn't flown too far away from the airstrip
			IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer -450.0 d5_runway_centre_y 1250.0 1250.0 FALSE
				d5_failed_challenge = 1
			ENDIF
			// checking if finished challenge
			IF d5_finished_mission = 1
			OR d5_failed_challenge = 1

				GOSUB d5_freeze_plane_pos // freezes player pos (special case for parachuting)
				// calculate player score
				IF d5_finished_mission = 1
					GOSUB d5_calculate_position_score
					GOSUB d5_calculate_damage_penalty
					d5_perfect_time = 55
					d5_pass_time = 70
					GOSUB d5_calculate_time_score
					GOSUB d5_calculate_overall_score
				ELSE
					overall_secs = 0
					position_score = 0
					d5_damage_penalty = 0
					overall_score = 0
				ENDIF
				// checking overall score against the best score at present
				IF overall_score > d5_best_scores[9]  	
					d5_old_score = d5_best_scores[9] 
					d5_best_scores[9] = overall_score
					d5_print_top_scores_flag = 1
					GOSUB d5_medal_check
				ENDIF 	
				//opening next level
				IF NOT pilot_test_passed = 1
					IF overall_score > 69
						d5_print_top_scores_flag = 2
						d5_total_missions_open = 12
						d5_last_played = 12
						d5_mission_plane_dead_flag = 2
						INCREMENT_INT_STAT FLYING_SKILL 60
						pilot_test_passed = 1
						d5_just_passed_pilot_school = 1
					ENDIF
				ENDIF

				GOSUB d5_display_scores

				// infinite loop
				WHILE d5_fake_creates = 0

					WAIT 0

					GOSUB d5_draw_record_text

					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						d5_mission_plane_dead_flag = 2
						GOTO d5_after_scores_parachute																		  
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						GOTO d5_after_scores_parachute																		  
					ENDIF
					
				ENDWHILE

				// resetting for another try
				d5_after_scores_parachute:
				GOSUB d5_mini_cleanup
				FREEZE_CHAR_POSITION scplayer FALSE

				IF d5_just_passed_pilot_school = 1
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					GOTO mission_desert5_passed
				ENDIF

				IF d5_mission_plane_dead_flag = 2 
					GOTO d5_noticeboard_setup
				ELSE
					GOTO d5_refresh_parachute
				ENDIF
			ENDIF

		ENDIF  
	GOTO d5_parachute_loop
ENDIF

// ********************************** Mission GOSUBS ************************************

// ************************************************************
//	    Find positions of coronas for circling left/right
// ************************************************************

	d5_m3_calculate_corona_circle:

		d5_m3_corona_x[0] = -383.79
		d5_m3_corona_y[0] = d5_runway_centre_y
		d5_m3_corona_z[0] = 147.09

		// lower half of balloon (straight sides)
		d5_m3_corona_x[1] = d5_m3_corona_x[0] - 300.0
		d5_m3_corona_x[6] = d5_m3_corona_x[0] - 300.0
		IF d5_mission_selection = 3
		OR d5_mission_selection = 5
			// circling right
			d5_m3_corona_y[1] = d5_m3_corona_y[0] - 300.0
			d5_m3_corona_y[6] = d5_m3_corona_y[0] + 300.0
		ELSE
			// circling left
			d5_m3_corona_y[1] = d5_m3_corona_y[0] + 300.0
			d5_m3_corona_y[6] = d5_m3_corona_y[0] - 300.0
		ENDIF
		d5_m3_corona_z[1] = d5_m3_corona_z[0]
		d5_m3_corona_z[6] = d5_m3_corona_z[0]

		// find semicircle at top of balloon
		d5_radius = 350.0
		// 3 & 5 right, 4 & 6 left
		IF d5_mission_selection = 3
		OR d5_mission_selection = 5
			d5_angle = 270.0
		ELSE
			d5_angle = 90.0
		ENDIF
		d5_circle_centre_x = d5_m3_corona_x[0] - 600.0
		d5_circle_centre_y = d5_m3_corona_y[0]
		d5_index = 2
		WHILE d5_index <= 5
			COS d5_angle d5_m3_corona_x[d5_index]
			SIN d5_angle d5_m3_corona_y[d5_index]
			d5_m3_corona_x[d5_index] *= d5_radius
			d5_m3_corona_y[d5_index] *= d5_radius
			d5_m3_corona_x[d5_index] += d5_circle_centre_x
			d5_m3_corona_y[d5_index] += d5_circle_centre_y
			d5_m3_corona_z[d5_index] = d5_m3_corona_z[0]
			IF d5_mission_selection = 3
			OR d5_mission_selection = 5
//				IF d5_index = 2
//					d5_m3_corona_y[2] += 100.0
//				ENDIF
				d5_angle -= 60.0
			ELSE
//				IF d5_index = 2
//					d5_m3_corona_y[2] -= 100.0
//				ENDIF
				d5_angle += 60.0
			ENDIF
			d5_index++
		ENDWHILE

		// final corona
		d5_m3_corona_x[7] = d5_m3_corona_x[0]
		d5_m3_corona_y[7] = d5_m3_corona_y[0]
		d5_m3_corona_z[7] = d5_m3_corona_z[0]
		IF d5_mission_selection = 5
		OR d5_mission_selection = 6
			d5_m3_corona_x[7] += 20.0
			d5_m3_corona_z[7] -= 30.0
		ENDIF

	RETURN

// ************************************************************
//			Find positions of coronas for loop-the-loop
// ************************************************************

//	d5_m10_calculate_looptheloop_coronas:
//
//		d5_circle_centre_x = d5_runway_centre_x
//		d5_circle_centre_z = 80.0
//		// find positions of loop coronas
//		d5_radius = 30.0
//		d5_angle = -90.0
//		d5_index = 3
//		WHILE d5_index <= 11
//			COS d5_angle d5_m10_corona_x[d5_index]
//			SIN d5_angle d5_m10_corona_z[d5_index]
//			d5_m10_corona_x[d5_index] *= d5_radius
//			d5_m10_corona_z[d5_index] *= d5_radius
//			d5_m10_corona_x[d5_index] += d5_circle_centre_x
//			d5_m10_corona_z[d5_index] += d5_circle_centre_z
//			d5_m10_corona_y[d5_index] = d5_runway_centre_y
//			d5_angle += 45.0
//			d5_index++
//		ENDWHILE
//		// find positions of coronas leading into loop
//		d5_index = 2
//		WHILE d5_index >= 0
//			d5_index_plus_one = d5_index + 1
//			d5_m10_corona_x[d5_index] = d5_radius * 2.0
//			d5_m10_corona_x[d5_index] *= -1.0
//			d5_m10_corona_x[d5_index] += d5_m10_corona_x[d5_index_plus_one]
//			d5_m10_corona_y[d5_index] = d5_m10_corona_y[3]
//			d5_m10_corona_z[d5_index] = d5_m10_corona_z[3]
//			d5_index--
//		ENDWHILE
//
//	RETURN

// ************************************************************
//						Front end GOSUBS
// ************************************************************

d5_drawing_tv_screen:
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 225.0 612.0 438.0 0 0 0 d5_alpha

	//FADE_OUT
	IF d5_fade_flag = 1 
		IF d5_alpha < 255
			d5_alpha = d5_alpha + 5 
			IF d5_alpha > 255
				d5_alpha = 255
			ENDIF
		ELSE
			d5_fade_flag = 2
		ENDIF
	ENDIF

	//FADE_IN
	IF d5_fade_flag = 3
		IF d5_alpha > 0
			d5_alpha = d5_alpha - 5 
			IF d5_alpha < 0
				d5_alpha = 0
			ENDIF
		ELSE
			d5_fade_flag = 0
		ENDIF
	ENDIF

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 0.0 640.0 197.0 0 0 0 255 

	// which test
	GOSUB d5_test_name_text 
	SET_TEXT_COLOUR 255 255 255 255

	IF d5_mission_selection = 1 
		DISPLAY_TEXT 322.0 31.0 DES5_MA // Takeoff
		d5_which_score_displayed = d5_best_scores[0]
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 2 
		DISPLAY_TEXT 322.0 31.0 DES5_MB // Land plane 
		d5_which_score_displayed = d5_best_scores[1]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 3 
		DISPLAY_TEXT 322.0 31.0 DES5_MC // Circle airstrip
		d5_which_score_displayed = d5_best_scores[2]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 4 
		DISPLAY_TEXT 322.0 31.0 DES5_MC // Circle airstrip
		d5_which_score_displayed = d5_best_scores[2]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 5
		DISPLAY_TEXT 322.0 31.0 DES5_MD // Circle airstrip and land
		d5_which_score_displayed = d5_best_scores[3]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 6 
		DISPLAY_TEXT 322.0 31.0 DES5_MD // Circle airstrip and land 
		d5_which_score_displayed = d5_best_scores[3]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 7 
		DISPLAY_TEXT 322.0 31.0 DES5_ME // Helicopter takeoff 
		d5_which_score_displayed = d5_best_scores[4]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 8 
		DISPLAY_TEXT 322.0 31.0 DES5_MF // Land helicopter 
		d5_which_score_displayed = d5_best_scores[5]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 9 
		DISPLAY_TEXT 322.0 31.0 DES5_MG // Destroy targets 
		d5_which_score_displayed = d5_best_scores[6]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 10 
		DISPLAY_TEXT 322.0 31.0 DES5_MH // Loop-the-loop
		d5_which_score_displayed = d5_best_scores[7]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 11 
		DISPLAY_TEXT 322.0 31.0 DES5_MI // Barrel roll
		d5_which_score_displayed = d5_best_scores[8]  
		GOSUB d5_drawing_medal 
	ENDIF
	IF d5_mission_selection = 12 
		DISPLAY_TEXT 322.0 31.0 DES5_MJ // Parachute onto target
		d5_which_score_displayed = d5_best_scores[9]
		GOSUB d5_drawing_medal 
	ENDIF

	//TV SCREEN HUD
	GOSUB d5_small_onscreen_text
	GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
	SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
	DISPLAY_TEXT 105.0 375.0 DES5_FO

	IF NOT d5_control_flag = 1
		GOSUB d5_small_onscreen_text
		GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 105.0 325.0 DES5_FP
		GOSUB d5_small_onscreen_text
		IF NOT d5_total_missions_open = 1
			GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		ELSE
			GET_HUD_COLOUR HUD_COLOUR_GREY d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		ENDIF
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 105.0 350.0 DES5_FN
	ENDIF

	GOSUB d5_small_onscreen_text
	SET_TEXT_SCALE 1.45 2.0
	GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
	SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
	DISPLAY_TEXT 70.0 372.0 SCHX

	IF NOT d5_control_flag = 1
		GOSUB d5_small_onscreen_text
		SET_TEXT_SCALE 1.45 2.0
		GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 70.0 322.0 SCHO

		GOSUB d5_small_onscreen_text
		SET_TEXT_SCALE 1.45 2.0
		GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 61.4 347.0 DES5_FK
	ENDIF

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 160.0 112.0 320.0 224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 160.0 317.0 320.0 -224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 480.0 112.0 -320.0 224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 480.0 317.0 -320.0 -224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 9 160.0 435.0 320.0 17.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 9 480.0 435.0 -320.0 17.0 150 150 150 255

RETURN

d5_drawing_medal:
    //background for medal, ribbons and score 
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 430.0 640.0 250.0 0 0 0 255

	//no medal
	IF d5_which_score_displayed < 70
		d5_which_medal_displayed = 1
	ENDIF
	
	//bronze
	IF d5_which_score_displayed > 69
		IF d5_which_score_displayed < 85
			d5_which_medal_displayed = 2
		ENDIF
	ENDIF

	//silver
	IF d5_which_score_displayed > 84
		IF d5_which_score_displayed < 100
			d5_which_medal_displayed = 3
		ENDIF
	ENDIF

	//gold
	IF d5_which_score_displayed = 100
		d5_which_medal_displayed = 4
	ENDIF

	//best score
	GOSUB d5_small_onscreen_text 
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_CENTRE ON
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
	SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha

	IF d5_which_medal_displayed > 1
		DISPLAY_TEXT_WITH_NUMBER 320.0 75.0 DES5_FQ d5_which_score_displayed
	ELSE
		DISPLAY_TEXT 320.0 75.0 DES5_FR  // Get 70% or above to pass
	ENDIF
					   
	IF d5_which_medal_displayed > 1
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 4 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 4 395.0 306.0 60.0 60.0 180 180 180 255
	ELSE
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 7 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 7 395.0 306.0 60.0 60.0 180 180 180 255
	ENDIF
	
	//which medal awarded
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	IF d5_which_medal_displayed = 1 //no medal
		LVAR_INT d5_language
		GET_CURRENT_LANGUAGE d5_language
		IF d5_language = LANGUAGE_ENGLISH
			DRAW_SPRITE	6 320.0 307.0 110.0 95.0 160 160 160 255
		ELSE
			DRAW_SPRITE	10 320.0 307.0 110.0 95.0 160 160 160 255
		ENDIF

		GOSUB d5_small_onscreen_text 
		SET_TEXT_CENTRE ON
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 320.0 375.0 DES5_FS
	ENDIF

	IF d5_which_medal_displayed = 2 //bronze
		DRAW_SPRITE	1 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB d5_small_onscreen_text 
		SET_TEXT_CENTRE ON
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 320.0 375.0 DES5_FF
	ENDIF
	IF d5_which_medal_displayed = 3 //silver
		DRAW_SPRITE	2 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB d5_small_onscreen_text 
		SET_TEXT_CENTRE ON
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 320.0 375.0 DES5_FE
	ENDIF
	IF d5_which_medal_displayed = 4 //gold
		DRAW_SPRITE	3 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB d5_small_onscreen_text 
		SET_TEXT_CENTRE ON
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
		DISPLAY_TEXT 320.0 375.0 DES5_FD
	ENDIF

RETURN

d5_watching_demo:
// setting up each individual mission for the playback and starting playback
	IF d5_playback_flag = 0 
		IF d5_mission_selection = 1
			GOSUB d5_setup_takeoff
		ENDIF
		IF d5_mission_selection = 2
			GOSUB d5_setup_landplane
		ENDIF
		IF d5_mission_selection = 4
			GOSUB d5_setup_circleairstrip
		ENDIF
		IF d5_mission_selection = 6
			GOSUB d5_setup_circleairstripland
		ENDIF
		IF d5_mission_selection = 7
			GOSUB d5_setup_helitakeoff
		ENDIF
		IF d5_mission_selection = 8
			GOSUB d5_setup_landheli
		ENDIF
		IF d5_mission_selection = 9
			GOSUB d5_setup_destroytargets
		ENDIF
		IF d5_mission_selection = 10
			GOSUB d5_setup_looptheloop
		ENDIF
		IF d5_mission_selection = 11
			GOSUB d5_setup_barrelroll
		ENDIF
		IF d5_mission_selection = 12
			GOSUB d5_setup_parachute
		ENDIF
		PLAY_MISSION_AUDIO 3
		d5_playback_flag = 1
	ENDIF

	IF d5_playback_flag = 1
		IF NOT d5_mission_selection = 12
			// loading in car recordings
			IF NOT IS_CAR_DEAD d5_mission_plane
				d5_car_recording = 589 + d5_mission_selection
				IF d5_mission_selection = 4
					d5_car_recording--
				ENDIF
				IF d5_mission_selection >= 6
					d5_car_recording -= 2
				ENDIF
				IF NOT HAS_CAR_RECORDING_BEEN_LOADED d5_car_recording
					REQUEST_CAR_RECORDING d5_car_recording 
				ELSE
					// playing back car recordings	
					START_PLAYBACK_RECORDED_CAR d5_mission_plane d5_car_recording
					GOSUB d5_load_scenes_around_video_and_player
					d5_fade_flag = 3 //FADE_IN
					d5_playback_flag = 2
				ENDIF
			ELSE
				d5_fade_flag = 3 //FADE_IN
				d5_playback_flag = 2
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD d5_m12_stunt_double
				// wait for parachute script to load
				IF IS_CHAR_PLAYING_ANIM d5_m12_stunt_double FALL_skydive
					FREEZE_CHAR_POSITION d5_m12_stunt_double FALSE
					TIMERB = 0
					d5_fade_flag = 3 //FADE_IN
					d5_playback_flag = 2
				ENDIF
			ELSE
				d5_fade_flag = 3 //FADE_IN
				d5_playback_flag = 2
			ENDIF
		ENDIF
	ENDIF

	// waiting for playback to finish
	IF d5_playback_flag = 2 
		IF NOT d5_mission_selection = 12
			IF NOT IS_CAR_DEAD d5_mission_plane
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR d5_mission_plane
					FREEZE_CAR_POSITION d5_mission_plane TRUE
					d5_fade_flag = 1 //FADE_OUT
					d5_playback_flag = 4
				ELSE
					SET_HELI_BLADES_FULL_SPEED d5_mission_plane
					IF d5_mission_selection = 9
						FIRE_HUNTER_GUN d5_mission_plane
						IF NOT d5_m9_car_exploded[2] = 1
						AND NOT IS_CAR_DEAD d5_m9_first_wave_cars[2]
						AND TIMERB > 3000
							EXPLODE_CAR_IN_CUTSCENE d5_m9_first_wave_cars[2]
							d5_m9_car_exploded[2] = 1
						ENDIF
						IF NOT d5_m9_car_exploded[1] = 1
						AND NOT IS_CAR_DEAD d5_m9_first_wave_cars[1]
						AND TIMERB > 4500
							EXPLODE_CAR_IN_CUTSCENE d5_m9_first_wave_cars[1]
							d5_m9_car_exploded[1] = 1
						ENDIF
						IF NOT d5_m9_car_exploded[0] = 1
						AND NOT IS_CAR_DEAD d5_m9_first_wave_cars[0]
						AND TIMERB > 6000
							EXPLODE_CAR_IN_CUTSCENE d5_m9_first_wave_cars[0]
							d5_m9_car_exploded[0] = 1
						ENDIF
					ENDIF
				ENDIF
			ELSE
				d5_fade_flag = 1 //FADE_OUT
				d5_playback_flag = 4
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD d5_m12_stunt_double
				IF TIMERB > 15000
					FREEZE_CHAR_POSITION d5_m12_stunt_double TRUE
					d5_fade_flag = 1 //FADE_OUT
					d5_playback_flag = 4
				ENDIF
			ELSE
				d5_fade_flag = 1 //FADE_OUT
				d5_playback_flag = 4
			ENDIF				
		ENDIF
	ENDIF

	// overriding playback
	IF d5_playback_flag = 3
		IF NOT IS_CAR_DEAD d5_mission_plane
			FREEZE_CAR_POSITION d5_mission_plane TRUE
		ENDIF
		d5_fade_flag = 1 //FADE_OUT
		d5_playback_flag = 4
	ENDIF

	// waiting for screen to FADE_OUT and cleaning everything up
	IF d5_playback_flag = 4
		IF d5_fade_flag = 2
			STOP_PLAYBACK_RECORDED_CAR d5_mission_plane
			RESTORE_CAMERA_JUMPCUT

			DELETE_CHAR d5_m12_stunt_double
			DELETE_CAR d5_mission_plane
			d5_index = 0
			WHILE d5_index < d5_m9_num_of_first_wave_cars
				DELETE_CAR d5_m9_first_wave_cars[d5_index]
				d5_index++
			ENDWHILE

			REMOVE_CAR_RECORDING 590
			REMOVE_CAR_RECORDING 591
			REMOVE_CAR_RECORDING 592
			REMOVE_CAR_RECORDING 593
			REMOVE_CAR_RECORDING 594
			REMOVE_CAR_RECORDING 595
			REMOVE_CAR_RECORDING 596
			REMOVE_CAR_RECORDING 597
			REMOVE_CAR_RECORDING 598
			CLEAR_MISSION_AUDIO 3
			CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
			CLEAR_ONSCREEN_TIMER car_timer
			FREEZE_ONSCREEN_TIMER FALSE
			d5_playback_flag = 0
		ENDIF
	ENDIF
RETURN

// stop scene from loading in while watching demo
d5_load_scenes_around_video_and_player:

	SWITCH d5_mission_selection
		CASE 1
			LOAD_SCENE 172.6 2466.14 17.05
			BREAK
		CASE 2
			LOAD_SCENE 27.29 2466.31 16.76
			BREAK
		CASE 3
		CASE 4
			LOAD_SCENE_IN_DIRECTION -1251.02 2301.32 120.44 60.0
			BREAK
		CASE 5
		CASE 6
			LOAD_SCENE -394.09 2601.47 134.89
			BREAK
		CASE 7
			LOAD_SCENE 323.02 2446.81 117.65
			BREAK
		CASE 9
			LOAD_SCENE 365.5 2339.25 63.23
			BREAK
		CASE 10
			LOAD_SCENE 269.53 2445.3 88.46
			BREAK
		CASE 11
			LOAD_SCENE 292.95 2445.3 93.46
			BREAK
		DEFAULT
			BREAK
	ENDSWITCH

	LOAD_SCENE_IN_DIRECTION 412.38 2533.65 18.18 90.0

RETURN

d5_small_onscreen_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE OFF
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.52 1.45
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

d5_which_course_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.8 2.6
	SET_TEXT_DROPSHADOW 2 0 0 0 255
RETURN/////////////////////////////////////////////////////////////////////////////////////

d5_test_name_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.2 4.0 
	SET_TEXT_DROPSHADOW 3 0 0 0 255
	SET_TEXT_FONT FONT_HEADING
	//SET_TEXT_FONT FONT_STANDARD
RETURN/////////////////////////////////////////////////////////////////////////////////////

d5_new_scores_text:
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.0 3.4
	SET_TEXT_DROPSHADOW 3 0 0 0 255
	SET_TEXT_FONT FONT_HEADING	
RETURN

d5_start_initialise_stuff:
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
RETURN

d5_stop_initialise_stuff:
	IF NOT d5_mission_selection = 12 // parachuting
		IF NOT IS_CAR_DEAD d5_mission_plane
			WARP_CHAR_INTO_CAR scplayer d5_mission_plane
			//LOCK_CAR_DOORS d5_mission_plane CARLOCK_LOCKED_PLAYER_INSIDE
		ENDIF
	ENDIF
	GET_CHAR_COORDINATES scplayer player_x player_y player_z
	REQUEST_COLLISION player_x player_y
	LOAD_SCENE player_x player_y player_z
	WAIT 0
	IF NOT d5_mission_selection = 9
		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
	ENDIF
	DISPLAY_HUD TRUE
	DISPLAY_RADAR TRUE
	SET_CAMERA_BEHIND_PLAYER 
	RESTORE_CAMERA_JUMPCUT
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	SET_PLAYER_CONTROL player1 ON
RETURN

d5_mission_plane_dead_check:///////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD d5_mission_plane
		IF d5_plane_started = 1 
			IF NOT IS_CHAR_IN_CAR scplayer d5_mission_plane
				d5_mission_plane_dead_flag = 1
			ENDIF
		ENDIF
	ELSE  
		d5_mission_plane_dead_flag = 1
	ENDIF
RETURN

d5_has_plane_started:///////////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD d5_mission_plane
		IF IS_CHAR_IN_CAR scplayer d5_mission_plane
			GET_CAR_SPEED d5_mission_plane d5_mission_plane_speed 
		ENDIF
	ENDIF
	IF IS_XBOX_VERSION
		IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
		OR IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
			IF d5_mission_plane_speed > 0.1
				CLEAR_PRINTS

				IF d5_mission_selection = 1
				OR d5_mission_selection = 3
				OR d5_mission_selection = 4
				OR d5_mission_selection = 5
				OR d5_mission_selection = 6
					DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				ENDIF

				d5_plane_started = 1
			ENDIF
		ENDIF
	ELSE
		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE
			IF d5_mission_plane_speed > 0.1
				CLEAR_PRINTS

				IF d5_mission_selection = 1
				OR d5_mission_selection = 3
				OR d5_mission_selection = 4
				OR d5_mission_selection = 5
				OR d5_mission_selection = 6
					DISPLAY_ONSCREEN_TIMER_WITH_STRING car_timer TIMER_UP ( DES5_T4 )
				ENDIF

				d5_plane_started = 1
			ENDIF
		ENDIF
	ENDIF
RETURN

d5_car_timer_0:///////////////////////////////////////////////////////////////////////////////
	IF car_timer = 0
		SET_PLAYER_CONTROL player1 OFF
		APPLY_BRAKES_TO_PLAYERS_CAR player1 TRUE
	ENDIF	 
RETURN/////////////////////////////////////////////////////////////////////////////////////

d5_freeze_plane_pos:////////////////////////////////////////////////////////////////////////////
	SET_PLAYER_CONTROL player1 OFF
	IF NOT d5_mission_selection = 12
		//FREEZE_CAR_POSITION d5_mission_plane TRUE
	ELSE
		FREEZE_CHAR_POSITION scplayer TRUE
	ENDIF
	FREEZE_ONSCREEN_TIMER TRUE
RETURN

d5_medal_check:////////////////////////////////////////////////////////////////////////////
	d5_which_medal_displayed = 0
	//bronze
	IF overall_score > 69
		IF overall_score < 85
			IF d5_old_score > 69 
			AND d5_old_score < 85 
				d5_which_medal_displayed = 0
			ELSE	
				d5_which_medal_displayed = 2
			ENDIF
		ENDIF
	ENDIF
	//silver
	IF overall_score > 84
		IF overall_score < 100
			IF d5_old_score > 84 
			AND d5_old_score < 100 
				d5_which_medal_displayed = 0
			ELSE	
				d5_which_medal_displayed = 3
			ENDIF
		ENDIF
	ENDIF
	//gold
	IF overall_score = 100
		IF d5_old_score = 100
			d5_which_medal_displayed = 0
		ELSE
			d5_which_medal_displayed = 4
		ENDIF
	ENDIF

	// check to see if player's unlocked any of the plane generators
	// (rustler/stunt/hunter unlocked when all missions passed with bronze/silver/gold)
	LVAR_INT d5_bronze_attained_in_all_tests d5_silver_attained_in_all_tests d5_gold_attained_in_all_tests
	d5_bronze_attained_in_all_tests = 1
	d5_silver_attained_in_all_tests = 1
	d5_gold_attained_in_all_tests = 1
	d5_index = 0
	WHILE d5_index < 10
		IF NOT d5_bronze_generator_unlocked = 1
			IF d5_best_scores[d5_index] < 70
			AND d5_bronze_attained_in_all_tests = 1
				d5_bronze_attained_in_all_tests = 0
			ENDIF
		ENDIF
		IF NOT d5_silver_generator_unlocked = 1
			IF d5_best_scores[d5_index] < 85
			AND d5_silver_attained_in_all_tests = 1
				d5_silver_attained_in_all_tests = 0
			ENDIF
		ENDIF
		IF NOT d5_gold_generator_unlocked = 1
			IF d5_best_scores[d5_index] < 100
			AND d5_gold_attained_in_all_tests = 1
				d5_gold_attained_in_all_tests = 0
			ENDIF
		ENDIF
		d5_index++
	ENDWHILE

	IF NOT d5_bronze_generator_unlocked = 1
	AND d5_bronze_attained_in_all_tests = 1
		SWITCH_CAR_GENERATOR d5_bronze_generator 101
		d5_bronze_generator_unlocked = 1
	ENDIF
	IF NOT d5_silver_generator_unlocked = 1
	AND d5_silver_attained_in_all_tests = 1
		SWITCH_CAR_GENERATOR d5_silver_generator 101
		d5_silver_generator_unlocked = 1
	ENDIF
	IF NOT d5_gold_generator_unlocked = 1
	AND d5_gold_attained_in_all_tests = 1
		SWITCH_CAR_GENERATOR d5_gold_generator 101
		SWITCH_CAR_GENERATOR gen_car_flying[25] 101
		d5_gold_generator_unlocked = 1
	ENDIF

RETURN

d5_calculate_position_score:
	IF d5_mission_selection = 1
	OR d5_mission_selection = 3
	OR d5_mission_selection = 4
	OR d5_mission_selection = 7
	OR d5_mission_selection = 10
	OR d5_mission_selection = 11
		// position not used in calculation of overall score
		position_score = 100
	ELSE
		IF NOT d5_mission_selection = 12
			IF NOT IS_CAR_DEAD d5_mission_plane
				position_score = 100
				// if not landed in indicated area, deduct 50 from position_score
				IF d5_mission_selection = 2
					IF NOT IS_CAR_STOPPED_IN_AREA_3D d5_mission_plane 288.0 d5_runway_y1 16.0 328.0 d5_runway_y2 18.0 FALSE
						position_score -= 50
					ENDIF
				ENDIF
				IF d5_mission_selection = 5
				OR d5_mission_selection = 6
					IF NOT IS_CAR_STOPPED_IN_AREA_3D d5_mission_plane 288.0 d5_runway_y1 16.0 328.0 d5_runway_y2 18.0 TRUE
						position_score -= 50
					ENDIF
				ENDIF
				IF d5_mission_selection = 8
					IF NOT LOCATE_STOPPED_CAR_3D d5_mission_plane d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z 26.31 26.31 26.31 TRUE
						position_score -= 50
					ENDIF
				ENDIF
				IF d5_mission_selection = 9
					IF NOT LOCATE_STOPPED_CAR_3D d5_mission_plane d5_m8_landing_target_x d5_m8_landing_target_y d5_m8_landing_target_z 26.31 26.31 26.31 TRUE
						position_score -= 50
					ENDIF
				ENDIF
				// if 260 <= heading <= 280, perfect (don't deduct anything).
				// else if 181 <= heading <= 359, decent (deduct 10)
				// else, facing wrong direction (deduct 25).
				GET_CAR_HEADING d5_mission_plane d5_plane_heading
				IF NOT d5_plane_heading >= 260.0
				OR NOT d5_plane_heading <= 280.0
					IF d5_plane_heading >= 181.0
					AND d5_plane_heading <= 359.0
						position_score -= 10
					ELSE
						position_score -= 25
					ENDIF
				ENDIF
			ELSE
				position_score = 0
			ENDIF
		ELSE
            IF d5_m12_distance_from_player_to_target <= 0.8
				position_score = 100
            ELSE
				IF d5_m12_distance_from_player_to_target <= 2.1
					position_score = 85
				ELSE
					IF d5_m12_distance_from_player_to_target <= 4.0
						position_score = 75
					ELSE
						IF d5_m12_distance_from_player_to_target <= 6.0
							position_score = 70
						ELSE
							IF d5_m12_distance_from_player_to_target <= 10.0
								position_score = 35
							ELSE
								position_score = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
            ENDIF
		ENDIF
	ENDIF
	IF position_score < 0
		position_score = 0
	ENDIF
	IF position_score > 100
		position_score = 100
	ENDIF
RETURN

d5_calculate_damage_penalty:
	IF NOT d5_mission_selection = 12
		IF NOT IS_CAR_DEAD d5_mission_plane
			GET_CAR_HEALTH d5_mission_plane d5_mission_plane_health
			player_car_damage = 1000 - instructor_car_health
			d5_damage_penalty = 1000 - d5_mission_plane_health
			d5_damage_penalty /= 10
			IF d5_damage_penalty < 0
				d5_damage_penalty = 0
			ENDIF
			IF d5_damage_penalty > 100
				d5_damage_penalty = 100
			ENDIF
		ELSE
			d5_damage_penalty = 100
		ENDIF
	ELSE
		d5_damage_penalty = 0
	ENDIF
RETURN

d5_calculate_time_score:
	overall_secs = car_timer / 1000
	IF overall_secs <= d5_perfect_time
		d5_time_score = 100
	ELSE
		// time_score = 100 - ((overall_secs-perfect_time)/(pass_time-perfect_time))*30
		d5_time_score = overall_secs - d5_perfect_time
		d5_time_score *= 30
		d5_pass_minus_perfect = d5_pass_time - d5_perfect_time
		d5_time_score /= d5_pass_minus_perfect
		d5_time_score *= -1
		d5_time_score += 100
		IF d5_time_score < 0
			d5_time_score = 0
		ENDIF
		IF d5_time_score > 100
			d5_time_score = 100
		ENDIF
	ENDIF
RETURN

d5_calculate_overall_score:
	overall_score = d5_time_score
	IF d5_mission_selection = 2
	OR d5_mission_selection = 5
	OR d5_mission_selection = 6
	OR d5_mission_selection = 8
	OR d5_mission_selection = 9
	OR d5_mission_selection = 12
		overall_score += position_score
		overall_score /= 2
	ENDIF
	IF NOT d5_mission_selection = 12
		overall_score -= d5_damage_penalty
	ENDIF
	IF overall_score < 0
		overall_score = 0
	ENDIF
	IF overall_score > 100
		overall_score = 100
	ENDIF
	CLEAR_PRINTS
RETURN

d5_mini_cleanup://////////////////////////////////////////////////////////////////////////////
	SET_PLAYER_CONTROL player1 OFF
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PILOT_AWARD_TRACK_STOP
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
		GOSUB d5_draw_record_text
	ENDWHILE
	DELETE_MENU d5_menu
	CLEAR_HELP
	CLEAR_PRINTS
	CLEAR_THIS_BIG_PRINT DES5_SA 
	CLEAR_THIS_BIG_PRINT DES5_SB
	SET_TEXT_SCALE 1.0 1.0
	APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE
	IF d5_mission_selection = 12
		DELETE_OBJECT d5_m12_parachute_target
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PARACHUTE
		MARK_MODEL_AS_NO_LONGER_NEEDED gun_para	
	ENDIF
	RESTORE_CAMERA_JUMPCUT
	DELETE_CHAR d5_m12_stunt_double
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
	REMOVE_BLIP d5_corona_blip
	DELETE_CHECKPOINT d5_cur_checkpoint
	DELETE_CHECKPOINT d5_m12_checkpoint
	IF d5_mission_plane_dead_flag = 2
		CLEAR_PRINTS
	ENDIF
	IF NOT IS_CAR_DEAD d5_mission_plane
		IF IS_CHAR_IN_CAR scplayer d5_mission_plane
			FREEZE_CAR_POSITION d5_mission_plane TRUE
		ENDIF
	ENDIF
	FREEZE_CHAR_POSITION scplayer TRUE
	WAIT 0
	REQUEST_COLLISION 412.38 2533.65
	LOAD_SCENE 412.38 2533.65 18.18
	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
	IF NOT IS_CAR_DEAD d5_mission_plane
		FREEZE_CAR_POSITION d5_mission_plane FALSE
	ENDIF
	FREEZE_CHAR_POSITION scplayer FALSE
	IF IS_CHAR_IN_ANY_CAR scplayer 
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 412.38 2533.65 18.18
	ELSE
		SET_CHAR_COORDINATES scplayer 412.38 2533.65 18.18
	ENDIF
	SET_CHAR_HEADING scplayer 90.0

	DELETE_CAR d5_mission_plane
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	CLEAR_ONSCREEN_TIMER car_timer
	FREEZE_ONSCREEN_TIMER FALSE
RETURN

// ************************************************************
//				   Show scores after each test
// ************************************************************

d5_display_scores:

	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 200 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	// cleanup
	CLEAR_HELP
	CLEAR_PRINTS
	CLEAR_ONSCREEN_TIMER car_timer
	REMOVE_BLIP d5_corona_blip
	DELETE_CHECKPOINT d5_cur_checkpoint

	// clean up visible stuff on the score screen specific to individual tests
	IF d5_mission_selection = 3
	OR d5_mission_selection = 4
	OR d5_mission_selection = 5
	OR d5_mission_selection = 6
		// delete all checkpoints
		d5_index = 0
		WHILE d5_index < 8
			DELETE_CHECKPOINT d5_m3_checkpoints[d5_index]
			d5_index++
		ENDWHILE
	ENDIF
	IF d5_mission_selection = 9
		d5_index = 0
		WHILE d5_index < d5_m9_num_of_first_wave_cars
			REMOVE_BLIP d5_m9_first_wave_car_blips[d5_index]
			d5_index++
		ENDWHILE
		d5_index = 0
		WHILE d5_index < d5_m9_num_of_second_wave_cars
			REMOVE_BLIP d5_m9_second_wave_car_blips[d5_index]
			d5_index++
		ENDWHILE
	ENDIF
	IF d5_mission_selection = 10
	OR d5_mission_selection = 11
		DELETE_CHECKPOINT d5_m10_start_checkpoint
		DELETE_CHECKPOINT d5_m10_end_checkpoint
	ENDIF
	IF d5_mission_selection = 12
		DELETE_OBJECT d5_m12_parachute_target
		DELETE_CHECKPOINT d5_m12_checkpoint
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PARACHUTE
		MARK_MODEL_AS_NO_LONGER_NEEDED gun_para
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	ENDIF

	RESTORE_CAMERA_JUMPCUT

	DELETE_CHAR d5_m12_stunt_double
	IF d5_mission_plane_dead_flag = 2
		CLEAR_PRINTS
	ENDIF
	IF NOT IS_CAR_DEAD d5_mission_plane
		IF IS_CHAR_IN_CAR scplayer d5_mission_plane
			FREEZE_CAR_POSITION d5_mission_plane TRUE
		ENDIF
	ENDIF
	FREEZE_CHAR_POSITION scplayer TRUE
	WAIT 0
	REQUEST_COLLISION 412.38 2533.65
	//LOAD_SCENE 412.38 2533.65 18.18
	LOAD_SCENE 300.0 2501.94 19.65
	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
	IF NOT IS_CAR_DEAD d5_mission_plane
		FREEZE_CAR_POSITION d5_mission_plane FALSE
	ENDIF
	FREEZE_CHAR_POSITION scplayer FALSE
	IF IS_CHAR_IN_ANY_CAR scplayer 
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 412.38 2533.65 18.18
	ELSE
		SET_CHAR_COORDINATES scplayer 412.38 2533.65 18.18
	ENDIF
	SET_CHAR_HEADING scplayer 90.0
	
	DELETE_CAR d5_mission_plane

	DISPLAY_HUD FALSE
	DISPLAY_RADAR FALSE
	SET_FIXED_CAMERA_POSITION 370.0 2501.94 19.65 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 320.0 2501.94 19.65 JUMP_CUT

	CREATE_MENU DUMMY 184.0 200.0 128.0 2 FALSE TRUE FO_CENTRE d5_menu
	SET_MENU_COLUMN d5_menu 1 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
	SET_MENU_COLUMN_ORIENTATION d5_menu 0 FO_LEFT
	SET_MENU_COLUMN_ORIENTATION d5_menu 1 FO_CENTRE
	SET_MENU_COLUMN_WIDTH d5_menu 0 192
	SET_MENU_COLUMN_WIDTH d5_menu 1 64

	LVAR_TEXT_LABEL d5_row_labels[12]
	LVAR_INT d5_temp_row

	d5_temp_row = 0
	
	IF d5_print_top_scores_flag > 0
	AND d5_which_medal_displayed > 0
		$d5_row_labels[d5_temp_row] = DUMMY
		d5_temp_row++
	ENDIF

	$d5_row_labels[d5_temp_row] = DES5_S3
	SET_MENU_ITEM_WITH_NUMBER d5_menu 1 d5_temp_row DES5_SC overall_secs
	d5_temp_row++

	IF d5_mission_selection = 2
	OR d5_mission_selection = 5
	OR d5_mission_selection = 6
	OR d5_mission_selection = 8
	OR d5_mission_selection = 9
	OR d5_mission_selection = 12
		$d5_row_labels[d5_temp_row] = DES5_S5
		SET_MENU_ITEM_WITH_NUMBER d5_menu 1 d5_temp_row DES5_SD position_score
		d5_temp_row++
	ENDIF

	IF NOT d5_mission_selection = 12
		$d5_row_labels[d5_temp_row] = DES5_S6
		SET_MENU_ITEM_WITH_NUMBER d5_menu 1 d5_temp_row DES5_SD d5_damage_penalty
		d5_temp_row++
	ENDIF

	$d5_row_labels[d5_temp_row] = DES5_S7
	SET_MENU_ITEM_WITH_NUMBER d5_menu 1 d5_temp_row DES5_SD overall_score
	d5_temp_row++
	
	// if player's passed the test for the first time, their flying skill is upgraded
	IF d5_print_top_scores_flag = 2
		$d5_row_labels[d5_temp_row] = DUMMY
		d5_temp_row++
		$d5_row_labels[d5_temp_row] = DES5_SF
		d5_temp_row++
		IF NOT d5_pilot_licence_obtained = 1
			LVAR_INT d5_cur_flying_skill
			GET_INT_STAT FLYING_SKILL d5_cur_flying_skill
			IF d5_cur_flying_skill >= FLYING_SKILL_REQUIRED_FOR_PILOT_LICENCE
				d5_pilot_licence_obtained = 1
				d5_index = 0
				WHILE d5_index <= 24
					IF NOT d5_index = 8
					AND NOT d5_index = 9
						SWITCH_CAR_GENERATOR gen_car_flying[d5_index] 101
					ENDIF
					d5_index++
				ENDWHILE
				SWITCH_CAR_GENERATOR gen_car_flying[27] 101
				d5_index = 0
				WHILE d5_index <= 11
					SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[d5_index] 0
					d5_index++
				ENDWHILE
				$d5_row_labels[d5_temp_row] = DES5_SG
				d5_temp_row++
			ENDIF
		ENDIF
	ENDIF

	$d5_row_labels[d5_temp_row] = DUMMY
	d5_temp_row++

	IF d5_print_top_scores_flag = 2
		$d5_row_labels[d5_temp_row] = DES5_S8
	ELSE
		$d5_row_labels[d5_temp_row] = DES5_SE
	ENDIF
	d5_temp_row++
	$d5_row_labels[d5_temp_row] = DES5_S9
	d5_temp_row++

	WHILE d5_temp_row < 12
		$d5_row_labels[d5_temp_row] = DUMMY
		d5_temp_row++
	ENDWHILE

	SET_MENU_COLUMN d5_menu 0 DUMMY $d5_row_labels[0] $d5_row_labels[1] $d5_row_labels[2] $d5_row_labels[3] $d5_row_labels[4] $d5_row_labels[5] $d5_row_labels[6] $d5_row_labels[7] $d5_row_labels[8] $d5_row_labels[9] $d5_row_labels[10] $d5_row_labels[11]
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PILOT_AWARD_TRACK_START

	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
		GOSUB d5_draw_record_text
	ENDWHILE

	RETURN

d5_draw_record_text:

	IF d5_print_top_scores_flag = 1
	OR d5_print_top_scores_flag = 2
		GOSUB d5_new_scores_text
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 320.0 120.0 DES5_SB // new record
		IF d5_which_medal_displayed > 0
			GOSUB d5_new_scores_text

			GET_HUD_COLOUR HUD_COLOUR_YELLOW d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
			SET_TEXT_COLOUR d5_text_rval d5_text_gval d5_text_bval d5_text_alpha
			DISPLAY_TEXT 320.0 85.0 DES5_SA // new certificate awarded
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	4 261.7 200.0 -50.0 50.0 180 180 180 255
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	4 378.3 200.0 50.0 50.0 180 180 180 255
			SET_SPRITES_DRAW_BEFORE_FADE TRUE

			IF d5_which_medal_displayed = 2 // bronze
				DRAW_SPRITE	1 320.0 200.0 91.7 79.2 160 160 160 255
			ENDIF
			IF d5_which_medal_displayed = 3 // silver
				DRAW_SPRITE	2 320.0 200.0 91.7 79.2 160 160 160 255
			ENDIF
			IF d5_which_medal_displayed = 4 // gold
				DRAW_SPRITE	3 320.0 200.0 91.7 79.2 160 160 160 255
			ENDIF

		ENDIF
	ENDIF

RETURN


// ****************************************************************
//  Mission audio (phone call in scripted cut at start of mission)
// ****************************************************************

	d5_load_and_play_audio:

		IF d5_audio_is_playing = 0
		OR d5_audio_is_playing = 1
			IF d5_audio_index < d5_total_audio_to_play
				IF TIMERB > 200
					GOSUB d5_play_audio
				ENDIF
			ENDIF
		ENDIF

		IF d5_audio_is_playing = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				d5_audio_is_playing = 0
				d5_audio_index++
				d5_started_talking = 0
				CLEAR_PRINTS
				TIMERB = 0
			ENDIF
		ENDIF

	RETURN

	d5_play_audio:

		IF d5_audio_is_playing = 0
			LOAD_MISSION_AUDIO 1 d5_audio_sound[d5_audio_index]
			d5_audio_is_playing = 1
		ENDIF
		IF d5_audio_is_playing = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW $d5_audio_text[d5_audio_index] 10000 1
				PLAY_MISSION_AUDIO 1
				d5_audio_is_playing = 2
			ENDIF
		ENDIF	
		
	RETURN

// ******************************* Mission desert5 failed *******************************

mission_desert5_failed:
CLEAR_PRINTS
IF d5_mission_plane_dead_flag = 1
OR NOT IS_PLAYER_PLAYING player1
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
ENDIF

RETURN


// ******************************* Mission desert5 passed *******************************

mission_desert5_passed:

IF NOT pilot_test_passed = 1
	pilot_test_passed = 1
ENDIF
IF pilot_test_passed = 1
	REGISTER_MISSION_PASSED ( DESERT5 )
	PLAYER_MADE_PROGRESS 1
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1  // MISSION PASSED!~n~~w~FLYING SKILL +
	SET_INT_STAT PASSED_DESERT5 1
	AWARD_PLAYER_MISSION_RESPECT 5
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REMOVE_BLIP pilot_contact_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT pilotx piloty pilotz pilot_blip_icon pilot_contact_blip
	REMOVE_BLIP desert2_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert2_blip_icon desert2_contact_blip
	flag_desert_mission_counter++
ENDIF

RETURN
		

// ********************************** Mission cleanup ***********************************

mission_cleanup_desert5:

MARK_MODEL_AS_NO_LONGER_NEEDED RUSTLER
MARK_MODEL_AS_NO_LONGER_NEEDED HUNTER
MARK_MODEL_AS_NO_LONGER_NEEDED PETRO
MARK_MODEL_AS_NO_LONGER_NEEDED LANDSTAL
MARK_MODEL_AS_NO_LONGER_NEEDED STUNT
MARK_MODEL_AS_NO_LONGER_NEEDED gun_para
MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED parachute.sc
REMOVE_TEXTURE_DICTIONARY
REMOVE_BLIP d5_corona_blip
DELETE_CHECKPOINT d5_cur_checkpoint
d5_index = 0
WHILE d5_index < 8
	DELETE_CHECKPOINT d5_m3_checkpoints[d5_index]
	d5_index++
ENDWHILE
DELETE_CHECKPOINT d5_m10_start_checkpoint
DELETE_CHECKPOINT d5_m10_end_checkpoint
DELETE_CHECKPOINT d5_m12_checkpoint
d5_index = 0
WHILE d5_index < d5_m9_num_of_first_wave_cars
	REMOVE_BLIP d5_m9_first_wave_car_blips[d5_index]
	d5_index++
ENDWHILE
d5_index = 0
WHILE d5_index < d5_m9_num_of_second_wave_cars
	REMOVE_BLIP d5_m9_second_wave_car_blips[d5_index]
	d5_index++
ENDWHILE
CLEAR_HELP
CLEAR_THIS_BIG_PRINT DES5_SA 
CLEAR_THIS_BIG_PRINT DES5_SB
CLEAR_SEQUENCE_TASK d5_second_wave_seq0
CLEAR_SEQUENCE_TASK d5_second_wave_seq1
SET_ALWAYS_DRAW_3D_MARKERS FALSE
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
USE_TEXT_COMMANDS FALSE
DELETE_MENU d5_menu
DELETE_OBJECT d5_m12_parachute_target
Para_pickup_flag = 1
IF NOT IS_CAR_DEAD d5_mission_plane 
	STOP_PLAYBACK_RECORDED_CAR d5_mission_plane
ENDIF
DELETE_CAR d5_mission_plane
d5_index = 0
WHILE d5_index < d5_m9_num_of_first_wave_cars
	DELETE_CAR d5_m9_first_wave_cars[d5_index]
	d5_index++
ENDWHILE
REMOVE_CAR_RECORDING 590
REMOVE_CAR_RECORDING 591
REMOVE_CAR_RECORDING 592
REMOVE_CAR_RECORDING 593
REMOVE_CAR_RECORDING 594
REMOVE_CAR_RECORDING 595
REMOVE_CAR_RECORDING 596
REMOVE_CAR_RECORDING 597
REMOVE_CAR_RECORDING 598
CLEAR_MISSION_AUDIO 3
REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PILOT_AWARD_TRACK_STOP
CLEAR_ONSCREEN_TIMER car_timer
FREEZE_ONSCREEN_TIMER FALSE
//CLEAR_ONSCREEN_COUNTER d5_m9_targets_remaining
RELEASE_WEATHER
DISPLAY_RADAR TRUE
SHOW_UPDATE_STATS TRUE
SET_AREA51_SAM_SITE ON
SWITCH_POLICE_HELIS TRUE
SET_MAX_WANTED_LEVEL d5_wanted_level_at_start

IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
DELETE_CHAR d5_m12_stunt_double
SET_CAMERA_BEHIND_PLAYER
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN
}
