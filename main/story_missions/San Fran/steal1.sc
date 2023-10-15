MISSION_START
// ------------------------------------------------------------------------------------------------
// Steal Mission 1: Follow Steal -- Is currently listed as Steal 4
{

SCRIPT_NAME steal1

// Begin...
GOSUB mission_start_steal1

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_steal1
	ENDIF

GOSUB mission_cleanup_steal1

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	LVAR_INT st1_stage st1_in_car st1_cut_h st1_cut st1_girl_idx st1_onscreen
	LVAR_INT st1_blipped st1_blip_idx st1_col[6] st1_girl_route st1_locate
	LVAR_INT st1_take st1_clip_text st1_trail_text 
	VAR_INT st1_route st1_route_nx st1_final_dest

// ---- Blips
	LVAR_INT st1_hub_blip st1_blip[6]
	LVAR_INT st1_blip_colour st1_fail_colour

// ----  Colours
	LVAR_INT st1_r st1_g st1_b st1_a
	LVAR_INT st1_r_end st1_g_end st1_b_end st1_a_end
	LVAR_INT st1_r_mult st1_g_mult st1_b_mult st1_a_mult
	LVAR_INT st1_r_sum st1_g_sum st1_b_sum st1_a_sum
	LVAR_INT st1_temp_sum_a st1_temp_sum_b

// ---- Timers / Counters
	LVAR_INT st1_timer_start st1_timer_end st1_timer_diff
	LVAR_INT st1_textimer_diff st1_textimer_start
	LVAR_INT st1_girl_incar  st1_girl_outcar
	LVAR_FLOAT st1_dist st1_spooked_fl st1_blip_dist 
	VAR_FLOAT st1_cruise_speed
	LVAR_INT st1_score st1_guy_mourn
	LVAR_INT st1_cool_health	
	LVAR_INT st1_loc_diff st1_loc_end st1_loc_start
// ---- Coords
	LVAR_FLOAT st1_vector_x st1_vector_y st1_vector_h st1_heading_left st1_heading_right
	LVAR_FLOAT st1_blip_x[6] st1_blip_y[6] st1_blip_z[6]
	LVAR_FLOAT st1_repo_x st1_repo_y st1_repo_z st1_repo_h1 st1_repo_h2
	LVAR_FLOAT st1_route_x[60] st1_route_y[60] st1_route_z[60]
	LVAR_FLOAT st1_cut_x[6] st1_cut_y[6] st1_cut_z[6]
	
	LVAR_FLOAT st1_door_x[2] st1_door_y[2] st1_door_z[2] 
	LVAR_FLOAT st1_div_x[6] st1_div_y[6] st1_div_z[6]
	LVAR_FLOAT st1_car_loc_x st1_car_loc_y st1_car_loc_z
	LVAR_FLOAT st1_player_x st1_player_y st1_player_z
	LVAR_FLOAT st1_hub_x st1_hub_y st1_hub_z

// ---- PIT Skid
	LVAR_FLOAT st1_heading_x st1_heading_y
	LVAR_FLOAT st1_velocity_x st1_velocity_y st1_velocity_z
	LVAR_FLOAT st1_velocity_mag st1_velocity_mag_sqr st1_velocity_forward st1_velocity_side
	LVAR_FLOAT st1_temp_1 st1_temp_2

// ----  Sequences 
	LVAR_INT st1_zero_chat st1_cj_end st1_player_status	st1_girl_cut_seq

// ---- Dialogue
	LVAR_TEXT_LABEL st1_text[6]
	LVAR_INT st1_audio_char
	LVAR_INT st1_audio[17] st1_counter st1_audio_playing st1_audio_slot	st1_alt_slot st1_ahead_counter



// ---- Cameras
	LVAR_FLOAT st1_cam_x[10] st1_cam_y[10] st1_cam_z[10]

// ---- Objects

// ---- Entities
   
// -------- Characters
// --------

// ------------------------------------------------------------------------------------------------
// -------- Vehicles
	st1_girl_car_create:
	LVAR_INT st1_girl_car st1_girl_car_blip	st1_girl_car_health
	LVAR_FLOAT st1_girl_car_x st1_girl_car_y st1_girl_car_z st1_girl_car_h st1_girl_car_speed
	LVAR_INT st1_girl st1_girl_blip
	LVAR_FLOAT st1_girl_x st1_girl_y st1_girl_z	st1_girl_h

	REQUEST_MODEL URANUS
	REQUEST_MODEL HFYST
	
	LOAD_ALL_MODELS_NOW
	WHILE NOT HAS_MODEL_LOADED URANUS
	OR NOT HAS_MODEL_LOADED HFYST
		WAIT 0
	ENDWHILE
	SUPPRESS_CAR_MODEL URANUS
	
	st1_girl_car_x = -2027.3347 
	st1_girl_car_y = 326.0038 
	st1_girl_car_z = 34.2344 
	st1_girl_car_h = 88.2674 

	st1_girl_x = -2025.4425
	st1_girl_y = 323.5849 
	st1_girl_z = 34.1015 	
	st1_girl_h = 77.8368 

	CLEAR_AREA st1_girl_car_x st1_girl_car_y st1_girl_car_z 5.0 TRUE
	//CUSTOM_PLATE_FOR_NEXT_CAR URANUS MAN_FAT
	CREATE_CAR URANUS st1_girl_car_x st1_girl_car_y st1_girl_car_z st1_girl_car
	SET_CAR_HEADING st1_girl_car st1_girl_car_h
	LOCK_CAR_DOORS st1_girl_car CARLOCK_LOCKOUT_PLAYER_ONLY
	SET_CAR_ONLY_DAMAGED_BY_PLAYER st1_girl_car TRUE
	//CREATE_CHAR_INSIDE_CAR st1_girl_car PEDTYPE_CIVFEMALE HFYST st1_girl
	CREATE_CHAR PEDTYPE_CIVFEMALE HFYST st1_girl_x st1_girl_y st1_girl_z st1_girl
	SET_CHAR_HEADING st1_girl st1_girl_h
	//SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st1_girl FALSE
	SET_LOAD_COLLISION_FOR_CAR_FLAG	st1_girl_car FALSE
	ADD_BLIP_FOR_CAR_OLD st1_girl_car 4 MARKER_ONLY st1_girl_car_blip
	CHANGE_CAR_COLOUR st1_girl_car 5 1
	RETURN
	st1_girl_car_delete:
	MARK_MODEL_AS_NO_LONGER_NEEDED URANUS
	RETURN
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 mod_garage_blips[0]
	LVAR_INT st1_player_car
	LVAR_FLOAT st1_player_car_x st1_player_car_y st1_player_car_z st1_player_car_h
	st1_player_car_create:
		REQUEST_MODEL ALPHA
		WHILE NOT HAS_MODEL_LOADED ALPHA
			WAIT 0
		ENDWHILE
		st1_player_car_x = -2043.9114
		st1_player_car_y = 179.1213 
		st1_player_car_z = 27.8507 
		st1_player_car_h = 270.0
		CLEAR_AREA st1_player_car_x st1_player_car_y st1_player_car_z 5.0 TRUE
		CREATE_CAR ALPHA st1_player_car_x st1_player_car_y st1_player_car_z	st1_player_car
		SET_CAR_HEADING st1_player_car st1_player_car_h
		WARP_CHAR_INTO_CAR scplayer	st1_player_car
	RETURN

	LVAR_INT st1_zero
	LVAR_FLOAT st1_zero_x st1_zero_y st1_zero_z st1_zero_h
	st1_zero_create:
		LOAD_SPECIAL_CHARACTER 1 zero
		REQUEST_ANIMATION CAR_CHAT
		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_ANIMATION_LOADED CAR_CHAT
		WAIT 0
		ENDWHILE 
		st1_zero_x = -2034.5664
		st1_zero_y = 175.2188 
		st1_zero_z = 27.8516 
		st1_zero_h = 13.5160 
		CLEAR_AREA st1_zero_x st1_zero_y st1_zero_z 5.0 TRUE
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 st1_zero_x st1_zero_y st1_zero_z st1_zero
	RETURN


// --------	
// --------	

// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_steal1:
	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1
	WAIT 0
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	st1_stage = 0
	st1_cut_h = 0
	st1_cut = 0
	st1_in_car = 0
	st1_girl_idx = 1
	st1_onscreen = 1
	st1_blipped = 0
	st1_take = 0
	st1_score = 500
	st1_final_dest = 57
	st1_route = 2
	st1_route_nx = 3
	st1_cruise_speed = 35.0
	st1_clip_text = 0
	st1_trail_text = 0

	st1_counter = 0
	st1_ahead_counter = 1

	st1_audio_slot = 1
	st1_alt_slot = 2

	st1_audio_playing = 0

// ----  Colours
	st1_blip_colour = 4278190335
	st1_fail_colour = 4286611800  
	st1_col[1] = 4278190335// Red
	st1_col[2] = 4282400938 // pink w/ alpha // 4278190250 // alpha //4282401023
	st1_col[3] = 4286611800 // pinker w/ alpha // 4278190165 // alpha //4286611966 // Pink
	st1_col[4] = 0//4290822654
	st1_col[5] = 0//4294967295// White	 4278190080 16711680 65280

//	st1_r =	0		  // Green to Red
//	st1_g =	255
//	st1_b =	0
//	st1_a =	255
//
//	st1_r_end =	255
//	st1_g_end =	0
//	st1_b_end =	0
//	st1_a_end =	255

	st1_r =	255
	st1_g =	0
	st1_b =	0
	st1_a =	255

	st1_r_end =	0
	st1_g_end =	0
	st1_b_end =	0
	st1_a_end =	0


	st1_r_mult = 16777216
	st1_g_mult = 65536
	st1_b_mult = 256
	st1_a_mult = 1
	
	//st1_blip_colour = (st1_r * st1_r_mult) + (st1_g * st1_g_mult) + (st1_b * st1_b_mult) + (st1_a * st1_a_mult)
	
// ---- Counter Var
   
// ---- Coords
	st1_repo_x = -2711.30 
	st1_repo_y = 118.81 
	st1_repo_z = 3.99 
	st1_repo_h1 = 2.07 // North 
	st1_repo_h2 = 178.25 // South
				 
				
				
	st1_hub_x = -2033.1749 
	st1_hub_y =	178.6755 
	st1_hub_z =	27.8516 

	st1_blip_x[1] = -2708.79 
	st1_blip_y[1] = -9.59 
	st1_blip_z[1] = 3.98

	st1_cut_x[0] = -2689.74 
	st1_cut_y[0] = 91.48 
	st1_cut_z[0] = 5.04

	st1_cut_x[1] = -2692.04 
	st1_cut_y[1] = 91.71 
	st1_cut_z[1] = 4.39

	st1_cut_x[3] = -2904.51 
	st1_cut_y[3] = 1181.56 
	st1_cut_z[3] = 13.71

	st1_cut_x[4] = -2916.92 
	st1_cut_y[4] = 1182.06 
	st1_cut_z[4] = 13.59

	st1_cut_x[5] = -2917.76 
	st1_cut_y[5] = 1176.93 
	st1_cut_z[5] = 13.67

	st1_door_x[0] = -2899.05 
	st1_door_y[0] = 1170.08
	st1_door_z[0] = 13.03

	st1_door_x[1] = -2898.66
	st1_door_y[1] = 1166.74
	st1_door_z[1] = 13.21

// ---- Diversion Coords
	st1_div_x[0] = 0.0 
	st1_div_y[0] = 0.0 
	st1_div_z[0] = 0.0

	st1_div_x[1] = 0.0 
	st1_div_y[1] = 0.0 
	st1_div_z[1] = 0.0

	st1_div_x[2] = 0.0 
	st1_div_y[2] = 0.0 
	st1_div_z[2] = 0.0

	st1_div_x[3] = 0.0 
	st1_div_y[3] = 0.0 
	st1_div_z[3] = 0.0

	st1_div_x[4] = 0.0 
	st1_div_y[4] = 0.0 
	st1_div_z[4] = 0.0

	st1_div_x[5] = 0.0 
	st1_div_y[5] = 0.0 
	st1_div_z[5] = 0.0

// ---- Camera Coords
	st1_cam_x[0] = -2704.72 
	st1_cam_y[0] = 113.56 
	st1_cam_z[0] = 5.52

	st1_cam_x[1] = -2701.12 
	st1_cam_y[1] = 100.21 
	st1_cam_z[1] = 5.47

	st1_cam_x[2] = -2699.84 
	st1_cam_y[2] = 94.03 
	st1_cam_z[2] = 6.41

	st1_cam_x[3] = -2710.60 
	st1_cam_y[3] = 126.88 
	st1_cam_z[3] = 5.13

	st1_cam_x[4] = 0.0 
	st1_cam_y[4] = 0.0 
	st1_cam_z[4] = 0.0


// ---- Dialogue Text
	$st1_text[1] = STL1_AA // Here, this gadget is set to pick up on cell phone signals.
	$st1_text[2] = STL1_AB // Each time she makes a call it will give you the new location.
	$st1_text[3] = STL1_AC // You have to stay close if you're going get an updated position.
	$st1_text[4] = STL1_AD // Ok?
	$st1_text[5] = STL1_AE // Sure thing. Thanks, Zee.

// ---- Dialogue Audio
	st1_audio[1] = SOUND_STL1_AA // Here, this gadget is set to pick up on cell phone signals.
	st1_audio[2] = SOUND_STL1_AB // Each time she makes a call it will give you the new location.
	st1_audio[3] = SOUND_STL1_AC // You have to stay close if you're going get an updated position.
	st1_audio[4] = SOUND_STL1_AD // Ok?
	st1_audio[5] = SOUND_STL1_AE // Sure thing. Thanks, Zee.

// ------------------------------------------------------------------------------------------------
// Request Models 
LOAD_MISSION_TEXT steal1
// ------------------------------------------------------------------------------------------------
// Entity GoSubs

// ------------------------------------------------------------------------------------------------
// Task Sequences
st1_girl_incar = 0
st1_girl_outcar = 1
st1_girl_route = 2

// ------------------------------------------------------------------------------------------------
// Start Cut

FORCE_WEATHER_NOW  WEATHER_SUNNY_SF
SET_AREA_VISIBLE 1

LOAD_CUTSCENE STEAL_1

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
 
CLEAR_CUTSCENE
SET_AREA_VISIBLE 0

SET_PLAYER_CONTROL player1 OFF

// ------------------------------------------------------------------------------------------------

// Main Loop
st1_main_loop:

WAIT 0

// ---- Load & Play Dialogue...
IF NOT st1_counter = 0
	IF st1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED st1_alt_slot
			CLEAR_MISSION_AUDIO st1_alt_slot
		ENDIF
		st1_audio_playing = 1
	ENDIF

	IF st1_audio_playing = 1
		LOAD_MISSION_AUDIO st1_audio_slot st1_audio[st1_counter]
		//GOSUB st1_dialogue_pos
		//ATTACH_MISSION_AUDIO_TO_PED st1_audio_slot st1_audio_char
		st1_audio_playing = 2
	ENDIF

	IF st1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED st1_audio_slot
			PLAY_MISSION_AUDIO st1_audio_slot
			PRINT_NOW $st1_text[st1_counter] 10000 1
			st1_audio_playing = 3
		ENDIF
	ENDIF

	IF st1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED st1_audio_slot
			CLEAR_THIS_PRINT $st1_text[st1_counter]
			IF st1_audio_slot = 1
				st1_audio_slot = 2
				st1_alt_slot = 1
			ELSE
				st1_audio_slot = 1
				st1_alt_slot = 2
			ENDIF
			st1_counter = 0
			st1_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED st1_alt_slot
				IF st1_counter < 60
					st1_ahead_counter = st1_counter + 1
					LOAD_MISSION_AUDIO st1_alt_slot st1_audio[st1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// ----

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_steal1
ENDIF

// ----

IF NOT IS_CHAR_DEAD scplayer
	IF st1_stage < 3
		IF st1_in_car = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN scplayer st1_player_car
				st1_in_car = 1
			ENDIF
		ENDIF
		IF st1_in_car = 1
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				st1_in_car = 0
			ENDIF
		ENDIF
	ENDIF 
ENDIF

// ----

IF st1_stage = 0
	IF st1_cut = 0
//		DO_FADE 500 FADE_OUT
//		WHILE GET_FADING_STATUS
//		WAIT 0
//		ENDWHILE

		CLEAR_AREA st1_hub_x st1_hub_y st1_hub_z 25.0 TRUE
		OPEN_GARAGE hbgdSFS
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION -2037.6014 183.5734 30.4011 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2037.0173 182.7901 30.1884 JUMP_CUT
		GOSUB st1_player_car_create
		GOSUB st1_zero_create
		GOSUB st1_girl_car_create
		GOSUB st1_escape_route
		LOAD_SCENE_IN_DIRECTION -2029.9041 179.7978 29.0075 130.0
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		IF NOT IS_CAR_DEAD st1_player_car
		AND NOT IS_CHAR_DEAD st1_zero
			IF st1_audio_playing = 0
				st1_counter = 1
				OPEN_SEQUENCE_TASK st1_zero_chat
					TASK_GO_STRAIGHT_TO_COORD -1 -2032.55 176.89 28.95 PEDMOVE_WALK -2
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_PLAY_ANIM -1 car_talkm_in CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0
                    TASK_PLAY_ANIM -1 car_talkm_loop CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0
                    TASK_PLAY_ANIM -1 car_talkm_out CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0
				CLOSE_SEQUENCE_TASK st1_zero_chat
				PERFORM_SEQUENCE_TASK st1_zero st1_zero_chat
				TASK_CAR_DRIVE_TO_COORD scplayer st1_player_car st1_hub_x st1_hub_y st1_hub_z 12.0 MODE_ACCURATE FALSE DRIVINGMODE_AVOIDCARS
				GET_GAME_TIMER st1_timer_start
				st1_cut = 1	
			ENDIF
		ENDIF
	ENDIF
	IF st1_cut > 0
		GET_GAME_TIMER st1_timer_end
		st1_timer_diff = st1_timer_end - st1_timer_start
		IF st1_timer_diff >	1000
			IF IS_BUTTON_PRESSED PAD1 CROSS
				st1_timer_start	-= 10000
				st1_cut = 5
			ENDIF
		ENDIF
	ENDIF
	IF st1_cut = 1
		GET_GAME_TIMER st1_timer_end
		st1_timer_diff = st1_timer_end - st1_timer_start
		IF st1_timer_diff >	1000
			IF st1_audio_playing = 0
				//LOAD_SCENE -2022.5178 322.6858 34.6179
				st1_counter = 2
				GET_GAME_TIMER st1_timer_start
				st1_cut = 2
			ENDIF
		ENDIF
	ENDIF
	IF st1_cut = 2
		GET_GAME_TIMER st1_timer_end
		st1_timer_diff = st1_timer_end - st1_timer_start
		IF st1_timer_diff >	1000
			IF st1_audio_playing = 0
				st1_counter = 3
				GET_GAME_TIMER st1_timer_start
				SET_FIXED_CAMERA_POSITION -2031.2416 175.4691 29.0587  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2031.5013 176.4334 29.0070 JUMP_CUT
				st1_cut = 3
			ENDIF
		ENDIF
	ENDIF

	IF st1_cut = 3
		IF NOT IS_CAR_DEAD st1_girl_car
		AND NOT IS_CHAR_DEAD st1_girl
			GET_GAME_TIMER st1_timer_end
			st1_timer_diff = st1_timer_end - st1_timer_start
			IF st1_timer_diff >	1000
				IF st1_audio_playing = 0
					CLEAR_PRINTS
					DO_FADE 100 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					//LOAD_SCENE -2022.5178 322.6858 34.6179
					SET_FIXED_CAMERA_POSITION -2022.5178 322.6858 34.6179 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2023.3816 323.1579 34.7936 JUMP_CUT
					LOAD_SCENE_IN_DIRECTION -2022.5178 322.6858 34.6179 50.0
					DO_FADE 100 FADE_IN
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					st1_counter = 4
					IF NOT IS_CAR_DEAD st1_girl_car
					AND NOT IS_CHAR_DEAD st1_girl
						TASK_ENTER_CAR_AS_DRIVER st1_girl st1_girl_car -1
						GET_GAME_TIMER st1_timer_start
						st1_cut = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF st1_cut = 4		
		IF NOT IS_CAR_DEAD st1_girl_car
			IF NOT IS_CHAR_DEAD st1_girl
				IF IS_CHAR_SITTING_IN_CAR st1_girl st1_girl_car
					IF st1_audio_playing = 0
						st1_counter = 5
						GET_GAME_TIMER st1_timer_start
						OPEN_SEQUENCE_TASK st1_girl_cut_seq
						TASK_CAR_DRIVE_TO_COORD -1 st1_girl_car st1_route_x[1] st1_route_y[1] st1_route_z[1] 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 st1_girl_car st1_route_x[2] st1_route_y[2] st1_route_z[2] 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						CLOSE_SEQUENCE_TASK	st1_girl_cut_seq
						PERFORM_SEQUENCE_TASK st1_girl st1_girl_cut_seq
						st1_cut = 5
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF st1_cut = 5
		GET_GAME_TIMER st1_timer_end
		st1_timer_diff = st1_timer_end - st1_timer_start
		IF st1_timer_diff >	1000
			IF st1_audio_playing = 0
				CLEAR_PRINTS
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				CLOSE_GARAGE hbgdSFS
				CLEAR_CHAR_TASKS scplayer
				IF NOT IS_CAR_DEAD st1_player_car
					LOAD_SCENE -2028.8267 178.4434 27.8516
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2030.6725 179.1596 28.9611
					SET_CAR_COORDINATES st1_player_car -2028.8267 178.4434 27.8516
					SET_CAR_HEADING	st1_player_car 266.8448
					WARP_CHAR_INTO_CAR scplayer	st1_player_car
				ENDIF
				LOAD_MISSION_AUDIO 3 SOUND_BLIP_DETECTED
				WHILE NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD st1_girl_car
				AND NOT IS_CHAR_DEAD st1_girl
					IF NOT IS_CHAR_IN_CAR st1_girl st1_girl_car
						WARP_CHAR_INTO_CAR st1_girl st1_girl_car
					ENDIF
				ENDIF
				REMOVE_ANIMATION CAR_CHAT
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				DELETE_CHAR st1_zero
				UNLOAD_SPECIAL_CHARACTER 1
				MARK_MODEL_AS_NO_LONGER_NEEDED ALPHA
				SWITCH_WIDESCREEN OFF
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				SET_PLAYER_CONTROL player1 ON
				st1_stage = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF 

// Mini cutscene...
IF st1_stage = 1
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD st1_girl_car
	AND NOT IS_CHAR_DEAD st1_girl
		IF IS_CHAR_IN_CAR st1_girl st1_girl_car 
			SET_PLAYER_CONTROL player1 ON    
			PRINT ( STL1_03 ) 7000 1
			ADD_STUCK_CAR_CHECK_WITH_WARP st1_girl_car 1.0 2000 TRUE TRUE TRUE 2
			TASK_CAR_DRIVE_TO_COORD st1_girl st1_girl_car st1_route_x[2] st1_route_y[2] st1_route_z[2] 35.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
	 		st1_stage = 2
			
		ENDIF
	ENDIF
ENDIF


IF IS_CAR_DEAD st1_girl_car
	CLEAR_PRINTS
	PRINT_NOW ( STL1_07 ) 5000 1 // ~s~You destroyed the car!
	GOTO mission_failed_steal1
ENDIF


IF st1_stage = 2
	IF NOT IS_CAR_DEAD st1_girl_car
	AND NOT IS_CHAR_DEAD st1_girl
	AND NOT	IS_CHAR_DEAD scplayer
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
			st1_blipped = 3
			SET_CAR_TEMP_ACTION st1_girl_car TEMPACT_HANDBRAKESTRAIGHT 2000
		ENDIF
		IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer st1_girl 50.0 50.0 50.0 FALSE
			IF st1_cruise_speed < 45.0
				st1_cruise_speed += 0.1
			ENDIF
		ELSE
			IF st1_cruise_speed > 30.0
				st1_cruise_speed -= 0.1
			ENDIF
		ENDIF	
		SET_CAR_CRUISE_SPEED st1_girl_car st1_cruise_speed
		GET_CAR_SPEED st1_girl_car st1_girl_car_speed
		GET_CAR_COORDINATES st1_girl_car st1_girl_car_x st1_girl_car_y st1_girl_car_z
		IF st1_route < st1_final_dest
			IF LOCATE_CHAR_IN_CAR_3D st1_girl st1_route_x[st1_route] st1_route_y[st1_route] st1_route_z[st1_route] 10.0 10.0 10.0 FALSE
				st1_route++
				st1_route_nx++
				TASK_CAR_DRIVE_TO_COORD st1_girl st1_girl_car st1_route_x[st1_route] st1_route_y[st1_route] st1_route_z[st1_route] st1_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				//CAR_GOTO_COORDINATES_RACING st1_girl_car st1_route_x[st1_route] st1_route_y[st1_route] st1_route_z[st1_route]
			ENDIF
		ELSE
			IF LOCATE_CHAR_IN_CAR_3D st1_girl st1_route_x[st1_route] st1_route_y[st1_route] st1_route_z[st1_route] 8.0 8.0 8.0 FALSE
				TASK_CAR_DRIVE_WANDER st1_girl st1_girl_car 35.0 DRIVINGMODE_AVOIDCARS
//				IF NOT IS_CAR_TYRE_BURST st1_girl_car 3
//					BURST_CAR_TYRE st1_girl_car 3
//				ENDIF
				st1_route++
				st1_route_nx++
			ENDIF
			IF st1_route > st1_final_dest
				IF NOT IS_CAR_ON_SCREEN	st1_girl_car
					GET_CHAR_COORDINATES scplayer st1_player_x st1_player_y st1_player_z
					GET_DISTANCE_BETWEEN_COORDS_2D st1_girl_car_x st1_girl_car_y st1_player_x st1_player_y st1_dist
					IF st1_dist > 200.0
						PRINT_NOW ( STL1_09 ) 5000 1 // lost her trail
						GOTO mission_failed_steal1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF st1_blipped = 2
		AND st1_locate = 0 
//			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS st1_girl_car 0.0 17.0 0.0 st1_car_loc_x st1_car_loc_y st1_car_loc_z
			GET_GAME_TIMER st1_loc_start
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer st1_girl_car_x st1_girl_car_y st1_girl_car_z 7.0 7.0 7.0 FALSE 
			   	st1_locate = 1
			ELSE
				st1_locate = 0
			ENDIF
		ENDIF
		// PIT Technique...
		//IF st1_blipped = 2
		IF st1_locate = 1 
			IF st1_girl_car_speed > 10.0
				GET_CAR_SPEED_VECTOR st1_girl_car st1_velocity_x st1_velocity_y st1_velocity_z

				// Get magnitude of car velocity (2d only)
				st1_temp_1 = st1_velocity_x * st1_velocity_x
				st1_temp_2 = st1_velocity_y * st1_velocity_y
				st1_velocity_mag_sqr = st1_temp_1 + st1_temp_2
				SQRT st1_velocity_mag_sqr st1_velocity_mag

				GET_CAR_FORWARD_X st1_girl_car st1_heading_x
				GET_CAR_FORWARD_Y st1_girl_car st1_heading_y

				// Get car velocity in forward direction
				st1_temp_1 = st1_velocity_x * st1_heading_x
				st1_temp_2 = st1_velocity_y * st1_heading_y
				st1_velocity_forward= st1_temp_1 + st1_temp_2

				// Get car sideways velocity by subtracting fwd vel from total
				st1_temp_1 = st1_velocity_forward * st1_velocity_forward
				st1_temp_2 = st1_velocity_mag_sqr - st1_temp_1 

				SQRT st1_temp_2 st1_velocity_side

				// Get ratio of sideways vel to forward speed to get skid angle
				st1_temp_1 = st1_velocity_side / st1_velocity_forward
				
				//WRITE_DEBUG_WITH_FLOAT st1_temp_1 st1_temp_1
				IF st1_temp_1 > 2.2
					//VIEW_FLOAT_VARIABLE st1_temp_1 st1_temp_1
					SET_CAR_TEMP_ACTION st1_girl_car TEMPACT_HANDBRAKESTRAIGHT 2000
					st1_blipped = 3	 // Should be 3
					st1_locate = 0
				ELSE
					st1_blipped = 2
					st1_locate = 0
				ENDIF			
			ENDIF
			//st1_temp_1 = 999.9
	   	ENDIF
 		IF st1_blipped = 3
		// You win!!!
			IF NOT IS_CAR_DEAD st1_girl_car
				CLEAR_PRINTS
				CLEAR_CHAR_TASKS st1_girl
			    SET_CAR_CRUISE_SPEED st1_girl_car 0.0
			   	PRINT_NOW ( STL1_05 ) 5000 1
				REMOVE_STUCK_CAR_CHECK st1_girl_car
				REMOVE_BLIP	st1_girl_car_blip
				ADD_BLIP_FOR_CAR st1_girl_car st1_girl_car_blip
				SET_BLIP_AS_FRIENDLY st1_girl_car_blip TRUE
				IF IS_CAR_STOPPED st1_girl_car
					TASK_LEAVE_CAR_AND_FLEE st1_girl st1_girl_car st1_girl_car_x st1_girl_car_y st1_girl_car_z
					st1_stage = 3
				ENDIF
			ENDIF
		ENDIF
		IF st1_blipped = 0
			REMOVE_BLIP st1_girl_car_blip
			GET_CAR_COORDINATES st1_girl_car st1_girl_x st1_girl_y st1_girl_z
			ADD_BLIP_FOR_COORD st1_girl_x st1_girl_y st1_girl_z st1_girl_car_blip
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer st1_girl_car_x st1_girl_car_y st1_girl_car_z 25.0 25.0 25.0 FALSE
				PLAY_MISSION_AUDIO 3
			ENDIF
//			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BLIP_DETECTED
//			st1_r =	0
//			st1_g =	255
//			st1_b =	0
//			st1_a =	255
			st1_r =	255
			st1_g =	0
			st1_b =	0
			st1_a =	255

			GOSUB st1_colour_check
			GET_GAME_TIMER st1_timer_start
			GET_GAME_TIMER st1_textimer_start
			st1_blipped = 1
		ENDIF
		IF st1_blipped = 1
			GET_GAME_TIMER st1_timer_end
			st1_timer_diff =  st1_timer_end - st1_timer_start
			st1_textimer_diff =  st1_timer_end - st1_textimer_start
			IF st1_textimer_diff > 1000
				IF st1_trail_text = 0
					PRINT ( STL1_03 ) 8000 1
					st1_trail_text = 1
				ENDIF
			ENDIF
			IF st1_timer_diff > 30
				GOSUB st1_colour_check
				GET_GAME_TIMER st1_timer_start
			ENDIF
			IF NOT IS_CHAR_DEAD st1_girl
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer st1_girl_x st1_girl_y 20.0 20.0 FALSE
				OR LOCATE_CHAR_IN_CAR_CHAR_3D scplayer st1_girl 50.0 50.0 50.0 FALSE
					IF st1_clip_text = 0
						PRINT_HELP ( STL1_04 ) // Get in front // Clip her back end to spin her out of control.
						st1_clip_text = 1
					ENDIF
					REMOVE_BLIP st1_girl_car_blip
					GET_GAME_TIMER st1_timer_start
					ADD_BLIP_FOR_CAR st1_girl_car st1_girl_car_blip
					st1_locate = 0
					st1_blipped = 2
				ENDIF
				IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer st1_girl 20.0 20.0 20.0 FALSE
					IF st1_clip_text = 1
						PRINT_NOW ( STL1_02 ) 12000 1
						st1_clip_text = 2
					ENDIF
				ENDIF
			ENDIF
			IF st1_a < 128
				PRINT_NOW ( STL1_10 ) 100 1
			ENDIF

			//IF st1_r = st1_r_end
			IF st1_a = st1_a_end
				PRINT_NOW ( STL1_09 ) 7000 1 // lost her trail
				GOTO mission_failed_steal1
			ENDIF 
		ENDIF
		IF st1_blipped = 2
			IF NOT IS_CAR_DEAD st1_girl_car
				
				IF st1_dist > 200.0
					st1_blipped = 0
				ENDIF
				GET_GAME_TIMER st1_timer_end
				st1_timer_diff =  st1_timer_end - st1_timer_start
				IF st1_timer_diff > 2000
					IF NOT IS_CAR_ON_SCREEN st1_girl_car
						st1_blipped = 0
					ENDIF
				ENDIF
				IF NOT IS_CAR_ON_SCREEN st1_girl_car
					st1_blipped = 0
				ELSE
					//IF st1_timer_diff > 10000
						
					//ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD st1_girl
		st1_stage = 3
	ENDIF
ENDIF

IF st1_stage = 3
	IF NOT IS_CAR_DEAD st1_girl_car
	AND NOT IS_CHAR_DEAD scplayer
		IF st1_take = 0
			LOCK_CAR_DOORS st1_girl_car CARLOCK_UNLOCKED
			st1_take = 1
		ENDIF
		IF st1_take = 1
			IF IS_CHAR_IN_CAR scplayer st1_girl_car
				REMOVE_BLIP st1_girl_car_blip
				PRINT_NOW ( STL1_06 ) 5000 1 // Head back to the garage.
				ADD_BLIP_FOR_COORD st1_hub_x st1_hub_y st1_hub_z st1_hub_blip
				CHANGE_GARAGE_TYPE hbgdSFS GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE 
				REQUEST_CAR_RECORDING 171
				WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED	171
				WAIT 0
				ENDWHILE
				st1_take = 2
			ENDIF
		ENDIF
		IF st1_take = 2
			IF NOT IS_CAR_DEAD st1_girl_car
				IF IS_CHAR_IN_CAR scplayer st1_girl_car 
					IF LOCATE_CHAR_IN_CAR_3D scplayer st1_hub_x st1_hub_y st1_hub_z 4.0 4.0 4.0 TRUE  
						IF IS_CHAR_SITTING_IN_CAR scplayer st1_girl_car
							CLEAR_AREA st1_hub_x st1_hub_y st1_hub_z 25.0 TRUE
							SET_PLAYER_CONTROL player1 OFF
							SWITCH_WIDESCREEN ON
							SET_FIXED_CAMERA_POSITION -2031.2416 175.4691 29.0587  0.0 0.0 0.0
							POINT_CAMERA_AT_POINT -2031.5013 176.4334 29.0070 JUMP_CUT
							OPEN_GARAGE hbgdSFS
							REMOVE_BLIP st1_hub_blip 
							st1_hub_x = -2043.9114 
							st1_hub_y = 179.1213 
							st1_hub_z = 27.8507 
							//ADD_BLIP_FOR_COORD st1_hub_x st1_hub_y st1_hub_z st1_hub_blip 
							//SET_BLIP_AS_FRIENDLY st1_hub_blip FALSE
							//PRINT_NOW ( STL1_13 ) 7000 1 //Drive the car into the garage.
							GET_CAR_HEALTH st1_girl_car st1_girl_car_health
							st1_girl_car_health /= 10
							st1_girl_car_health *= 100
							st1_score = 2000
							st1_score += st1_girl_car_health
							st1_take = 21
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF st1_take = 21
			IF NOT IS_CAR_DEAD st1_girl_car
				IF IS_GARAGE_OPEN hbgdSFS
					CLEAR_AREA st1_hub_x st1_hub_y st1_hub_z 25.0 TRUE
					START_PLAYBACK_RECORDED_CAR st1_girl_car 171
					SET_FIXED_CAMERA_POSITION -2037.6014 183.5734 30.4011 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2037.0173 182.7901 30.1884 JUMP_CUT
					st1_take = 3
				ENDIF
			ENDIF
		ENDIF
		//telling player to drive into garage
		IF st1_take = 3 
			IF NOT IS_CAR_DEAD st1_girl_car
				IF IS_CHAR_IN_CAR scplayer st1_girl_car
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer st1_hub_x st1_hub_y st1_hub_z 4.0 4.0 4.0 FALSE
						
	//					SET_PLAYER_CONTROL player1 OFF
	//					SWITCH_WIDESCREEN ON 
						CLOSE_GARAGE hbgdSFS
						CLEAR_PRINTS
//						SET_FIXED_CAMERA_POSITION -2051.0129 176.9538 28.9961 0.0 0.0 0.0
//						POINT_CAMERA_AT_POINT -2050.0369 177.1556 29.0763 JUMP_CUT
						st1_take = 32
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF st1_take = 31
			IF NOT IS_CAR_DEAD st1_girl_car
				OPEN_SEQUENCE_TASK st1_cj_end
					TASK_LEAVE_CAR -1 st1_girl_car
					TASK_GO_STRAIGHT_TO_COORD -1 -2035.4708 176.6342 27.8359 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK st1_cj_end
				PERFORM_SEQUENCE_TASK scplayer st1_cj_end
				st1_take = 32
			ENDIF
		ENDIF
		IF st1_take = 32
			IF NOT IS_CAR_DEAD st1_girl_car
				//IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer st1_hub_x st1_hub_y 6.0 6.0 FALSE
					CLOSE_GARAGE hbgdSFS
					DO_FADE 1500 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE 
					st1_take = 41
				//ENDIF
			ENDIF
		ENDIF
		IF st1_take = 41
			LVAR_FLOAT old_X old_Y old_Z old_Heading
            SET_PLAYER_CONTROL player1 OFF
            SWITCH_WIDESCREEN ON
            SET_FADING_COLOUR 0 0 1
            GET_CHAR_COORDINATES scplayer old_X old_Y old_Z
            GET_CHAR_HEADING scplayer old_Heading
            DO_FADE 500 FADE_OUT
            WHILE GET_FADING_STATUS
            	WAIT 0
	            IF NOT IS_PLAYER_PLAYING player1
	           		TERMINATE_THIS_SCRIPT
	            ENDIF
            ENDWHILE
            LOAD_SCENE_IN_DIRECTION   -2690.9082 206.6429 7.2879 88.2793
            SET_CHAR_COORDINATES scplayer -2691.1638 206.6478 3.3359 
            SET_FIXED_CAMERA_POSITION -2689.9163 206.5855 7.3990 0.0 0.0 0.0
            POINT_CAMERA_AT_POINT -2690.9082 206.6429 7.2879 JUMP_CUT
            DO_FADE 500 FADE_IN
            PRINT_NOW ( JAP_MOD ) 6000 1 
            WAIT 6000
			PRINT_NOW ( WANG_C ) 6000 1
			WAIT 6000
            DO_FADE 500 FADE_OUT
            WHILE GET_FADING_STATUS
            WAIT 0
            IF NOT IS_PLAYER_PLAYING player1
            	TERMINATE_THIS_SCRIPT
            ENDIF
            ENDWHILE
            LOAD_SCENE_IN_DIRECTION   old_X old_Y old_Z old_Heading
            old_Z = old_Z - 1.0
            SET_CHAR_COORDINATES scplayer old_X old_Y old_Z
            SET_CHAR_HEADING scplayer old_Heading

            DO_FADE 500 FADE_IN
            SET_CAMERA_BEHIND_PLAYER
            RESTORE_CAMERA_JUMPCUT
            SET_PLAYER_CONTROL player1 ON
            SWITCH_WIDESCREEN OFF
            ACTIVATE_GARAGE mds1SFS //PP CAR GARAGE
            REMOVE_BLIP mod_garage_blips[0]
            ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2728.5, 212.2, 3.4 RADAR_SPRITE_MOD_GARAGE mod_garage_blips[0]
            japcar_mod_garage_open = 1
			st1_take = 4
		ENDIF
		IF st1_take = 4
//			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK st1_player_status
//			IF st1_player_status = FINISHED_TASK
				IF IS_GARAGE_CLOSED hbgdSFS
					SWITCH_WIDESCREEN OFF
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer -2032.4708 176.6342 27.8359
					DELETE_CAR st1_girl_car
					SET_CHAR_HEADING scplayer 267.9
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT  
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE 
					//PRINT_NOW ( STL1_08 ) 5000 1 // Well done!
					GOTO mission_passed_steal1
				ENDIF
			//ENDIF
		ENDIF
	ENDIF
ENDIF

// Get Player's car...
IF NOT IS_CHAR_DEAD scplayer
	IF st1_take > 1
	AND st1_take < 4
		IF NOT IS_CAR_DEAD st1_girl_car
			IF st1_in_car = 0
				PRINT_NOW ( STL1_11 ) 100 1
				IF IS_CHAR_IN_CAR scplayer st1_girl_car
					REMOVE_BLIP st1_girl_car_blip
					REMOVE_BLIP st1_hub_blip
					PRINT_NOW ( STL1_06 ) 5000 1 // Head back to the garage.
					ADD_BLIP_FOR_COORD st1_hub_x st1_hub_y st1_hub_z st1_hub_blip
					st1_in_car = 1
				ENDIF
			ENDIF
			IF st1_in_car = 1
				IF NOT IS_CHAR_IN_CAR scplayer st1_girl_car
					REMOVE_BLIP st1_hub_blip
					REMOVE_BLIP st1_girl_car_blip
					ADD_BLIP_FOR_CAR st1_girl_car st1_girl_car_blip
					SET_BLIP_AS_FRIENDLY st1_girl_car_blip TRUE
					st1_in_car = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF	
ENDIF

GOTO st1_main_loop 
// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_steal1:

PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_steal1:

flag_steal_mission_counter ++
SWITCH_CAR_GENERATOR steal_car_cargen1 101
REGISTER_MISSION_PASSED ( STEAL_1 )
PLAYER_MADE_PROGRESS 1
PLAY_MISSION_PASSED_TUNE 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!"
ADD_SCORE player1 5000 //amount of cash reward
AWARD_PLAYER_MISSION_RESPECT 5 //amount of respect

CLEAR_WANTED_LEVEL player1
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_steal1:

// ---- Entities
	CLEAR_MISSION_AUDIO 3	
// ---- Blips
	REMOVE_BLIP st1_girl_car_blip
	REMOVE_BLIP st1_hub_blip
	REMOVE_BLIP st1_girl_car_blip
	RELEASE_WEATHER

// ---- Models
	DELETE_CHAR	st1_girl
	DELETE_CAR	st1_girl_car

	GOSUB st1_girl_car_delete
	MARK_MODEL_AS_NO_LONGER_NEEDED ALPHA
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYST

// ----	Clear Script Stuff
		
	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED
RETURN
// ------------------------------------------------------------------------------------------------

st1_colour_check:

//IF st1_r < 255
//	st1_r += 1
//ENDIF
//
//IF st1_r =  255
//	st1_g -= 1
//ENDIF

IF st1_g < 255
	//st1_r -= 1
	st1_g += 1
	st1_b += 1
ENDIF

IF st1_g =  255
	st1_a -= 1
ENDIF


st1_r_sum = st1_r * st1_r_mult 
st1_g_sum = st1_g * st1_g_mult
st1_b_sum = st1_b * st1_b_mult
st1_a_sum = st1_a * st1_a_mult

st1_temp_sum_a = st1_r_sum + st1_g_sum
st1_temp_sum_b = st1_b_sum + st1_a_sum
st1_blip_colour = st1_temp_sum_a + st1_temp_sum_b

CHANGE_BLIP_COLOUR st1_girl_car_blip st1_blip_colour

RETURN

st1_escape_route:

st1_route_x[0] = -2017.0007 
st1_route_y[0] = 322.4644 
st1_route_z[0] = 34.7593 

st1_route_x[1] = -2036.4390 
st1_route_y[1] = 322.4642 
st1_route_z[1] = 34.0156

st1_route_x[2] = -2148.5054 
st1_route_y[2] = 227.5995 
st1_route_z[2] = 34.8814 

st1_route_x[3] = -2244.5747 
st1_route_y[3] = 211.4546 
st1_route_z[3] = 34.8840 

st1_route_x[4] = -2252.4258 
st1_route_y[4] = 110.4054 
st1_route_z[4] = 34.8813 

st1_route_x[5] = -2254.3977 
st1_route_y[5] = 64.4779 
st1_route_z[5] = 34.8865 

st1_route_x[6] = -2333.1938 
st1_route_y[6] = 52.1153 
st1_route_z[6] = 34.8895 

st1_route_x[7] = -2373.7935 
st1_route_y[7] = -39.8936 
st1_route_z[7] = 34.8887 

st1_route_x[8] = -2411.4060 
st1_route_y[8] = -71.5329 
st1_route_z[8] = 34.8695 

st1_route_x[9] = -2421.9858 
st1_route_y[9] = -171.5927 
st1_route_z[9] = 34.8702 

st1_route_x[10] = -2514.4871 
st1_route_y[10] = -209.5909 
st1_route_z[10] = 24.5432 

st1_route_x[11] = -2612.3376 
st1_route_y[11] = -208.1451 
st1_route_z[11] = 3.8877 

st1_route_x[12] = -2700.5417 
st1_route_y[12] = -205.7518 
st1_route_z[12] = 3.8928 

st1_route_x[13] = -2703.4553 
st1_route_y[13] = -105.7246 
st1_route_z[13] = 3.8899 

st1_route_x[14] = -2704.9326 
st1_route_y[14] = -88.0339 
st1_route_z[14] = 3.8934 

st1_route_x[15] = -2791.1985 
st1_route_y[15] = -69.7781 
st1_route_z[15] = 6.7409 

st1_route_x[16] = -2807.2424 
st1_route_y[16] = 29.8687 
st1_route_z[16] = 6.7487 

st1_route_x[17] = -2807.9224 
st1_route_y[17] = 131.0431 
st1_route_z[17] = 6.7324 

st1_route_x[18] = -2807.2468 
st1_route_y[18] = 232.0953 
st1_route_z[18] = 6.7535 

st1_route_x[19] = -2831.3765 
st1_route_y[19] = 329.6273 
st1_route_z[19] = 4.0742 

st1_route_x[20] = -2851.8401 
st1_route_y[20] = 427.8425 
st1_route_z[20] = 4.0745 

st1_route_x[21] = -2831.4180 
st1_route_y[21] = 526.6416 
st1_route_z[21] = 4.6276 

st1_route_x[22] = -2821.2705 
st1_route_y[22] = 626.4095 
st1_route_z[22] = 7.8679 

st1_route_x[23] = -2849.5251 
st1_route_y[23] = 720.4027 
st1_route_z[23] = 28.1290 

st1_route_x[24] = -2840.6128 
st1_route_y[24] = 819.4821 
st1_route_z[24] = 40.6696 

st1_route_x[25] = -2816.8728 
st1_route_y[25] = 897.2410 
st1_route_z[25] = 43.6833 

st1_route_x[26] = -2776.3962 
st1_route_y[26] = 809.4246 
st1_route_z[26] = 50.6937 

st1_route_x[27] = -2676.2866 
st1_route_y[27] = 810.1769 
st1_route_z[27] = 49.5432 

st1_route_x[28] = -2575.5583 
st1_route_y[28] = 807.5107 
st1_route_z[28] = 49.5239 

st1_route_x[29] = -2528.4617 
st1_route_y[29] = 809.4405 
st1_route_z[29] = 49.5356 

st1_route_x[30] = -2526.5640 
st1_route_y[30] = 908.4559 
st1_route_z[30] = 64.5370 

st1_route_x[31] = -2525.0891 
st1_route_y[31] = 999.8545 
st1_route_z[31] = 77.8166 

st1_route_x[32] = -2594.6897 
st1_route_y[32] = 1006.4087 
st1_route_z[32] = 77.8242 

st1_route_x[33] = -2587.4739 
st1_route_y[33] = 1092.9789 
st1_route_z[33] = 56.2406 

st1_route_x[34] = -2591.7122 
st1_route_y[34] = 1135.3021 
st1_route_z[34] = 55.1350 

st1_route_x[35] = -2658.5493 
st1_route_y[35] = 1209.7676 
st1_route_z[35] = 55.1386 

st1_route_x[36] = -2669.4744 
st1_route_y[36] = 1309.3092 
st1_route_z[36] = 55.1228 

st1_route_x[37] = -2677.5388 
st1_route_y[37] = 1409.6298 
st1_route_z[37] = 55.1226 

st1_route_x[38] = -2669.2937 
st1_route_y[38] = 1509.8888 
st1_route_z[38] = 58.6901 

st1_route_x[39] = -2674.5425 
st1_route_y[39] = 1610.9475 
st1_route_z[39] = 64.4835 

st1_route_x[40] = -2675.3433 
st1_route_y[40] = 1711.9075 
st1_route_z[40] = 67.4134 

st1_route_x[41] = -2675.3442 
st1_route_y[41] = 1812.6913 
st1_route_z[41] = 67.4802 

st1_route_x[42] = -2675.3330 
st1_route_y[42] = 1913.5526 
st1_route_z[42] = 64.6734 

st1_route_x[43] = -2675.7913 
st1_route_y[43] = 2013.8885 
st1_route_z[43] = 59.0350 

st1_route_x[44] = -2670.8145 
st1_route_y[44] = 2114.1055 
st1_route_z[44] = 55.1467 

st1_route_x[45] = -2679.1062 
st1_route_y[45] = 2214.4375 
st1_route_z[45] = 55.1239 

st1_route_x[46] = -2720.0142 
st1_route_y[46] = 2305.9978 
st1_route_z[46] = 63.1498 

st1_route_x[47] = -2720.7439 
st1_route_y[47] = 2353.8872 
st1_route_z[47] = 71.5011 

st1_route_x[48] = -2677.1504 
st1_route_y[48] = 2440.7620 
st1_route_z[48] = 47.2891 

st1_route_x[49] = -2593.7380 
st1_route_y[49] = 2490.0811 
st1_route_z[49] = 22.1514 

st1_route_x[50] = -2513.9397 
st1_route_y[50] = 2429.6116 
st1_route_z[50] = 16.4114 

st1_route_x[51] = -2413.6272 
st1_route_y[51] = 2430.8770 
st1_route_z[51] = 11.8649 

st1_route_x[52] = -2339.4143 
st1_route_y[52] = 2395.6228 
st1_route_z[52] = 5.5583 

st1_route_x[53] = -2422.8386 
st1_route_y[53] = 2340.1985 
st1_route_z[53] = 4.5444 

st1_route_x[54] = -2462.5752 
st1_route_y[54] = 2334.2646 
st1_route_z[54] = 4.5459 

st1_route_x[55] = -2467.3274 
st1_route_y[55] = 2276.3762 
st1_route_z[55] = 4.5468 

st1_route_x[56] = -2560.3081 
st1_route_y[56] = 2313.5662 
st1_route_z[56] = 4.5508 

st1_route_x[57] = -2560.2864 
st1_route_y[57] = 2316.4421 
st1_route_z[57] = 4.5491 
RETURN

/*

[STL1_01 // 
~s~Go and get the ~r~car~s~.

[STL1_02 // 
~s~Clip her back end to spin her out of control.

[STL1_03 // 
~s~Don't let her ~r~trail ~s~go cold.

[STL1_04 // 
Use the P.I.T. technique learned in the Driving School to spin the target car around with minimal damage.

[STL1_05 // 
~s~Steal the ~b~car~s~.

[STL1_06 // 
~s~Drive back to the ~y~garage ~s~in San Fierro.

[STL1_07 // 
~r~You destroyed the car!

[STL1_08 // 
~s~Well done!

[STL1_09 // 
~r~You lost her trail!

[STL1_10 // 
~s~Keep up! Her ~r~trail~s~ is going cold!

[STL1_11 // 
~s~Get back in the ~b~car~s~!

[STL1_12 // 
~s~The car should be nearby.

[STL1_13 // 
~s~Drive the car into the ~y~garage~s~.

*/
}

