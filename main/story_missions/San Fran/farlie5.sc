MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* DRIVER 6 ******************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME driv6

// Mission start stuff

GOSUB mission_start_driv6

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_driv6_failed
ENDIF

GOSUB mission_cleanup_driv6

MISSION_END

{
 
// Variables for mission

LVAR_INT d6_player_car
LVAR_FLOAT d6_chase_start_x d6_chase_start_y d6_chase_start_z
LVAR_FLOAT d6_destination_x d6_destination_y d6_destination_z
LVAR_INT d6_next_checkpoint

LVAR_FLOAT d6_left_offset_x d6_left_offset_y d6_left_offset_z
LVAR_FLOAT d6_right_offset_x d6_right_offset_y d6_right_offset_z
LVAR_INT d6_temp_driver

LVAR_INT d6_cur_num_of_chase_bikes_created d6_total_num_of_chase_bikes
LVAR_INT d6_num_of_chase_bikes_close
LVAR_INT d6_chase_bikes[14] d6_chase_bike_drivers[14] d6_chase_bike_passengers[14]
LVAR_FLOAT d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z d6_chase_bike_heading
LVAR_INT d6_num_of_chase_bike_groups_created

LVAR_INT d6_player_car_health d6_door_damage

LVAR_INT d6_car_chat_seq
LVAR_INT d6_task_status d6_seq_progress

LVAR_INT d6_index d6_one_past_end_of_group d6_index_within_group

// for mission passed cutscene
LVAR_FLOAT d6_camera_x d6_camera_y d6_camera_z

// mission audio
LVAR_TEXT_LABEL d6_audio_text[7]
LVAR_INT d6_audio_sound[7]
LVAR_INT d6_audio_is_playing d6_audio_index d6_total_audio_to_play d6_started_talking
LVAR_INT d6_current_audio_needed
CONST_INT D6_MISSION_FAILED_AUDIO 0
CONST_INT D6_MISSION_PASSED_AUDIO 1

// flags

LVAR_INT d6_fake_creates
LVAR_INT d6_player_picked_up_decoy_car d6_countryside_print_cleared d6_bike_chase_started
//LVAR_INT d6_triangle_pressed_last_frame d6_player_already_tried_to_leave_car
LVAR_INT d6_driver_getting_back_on_bike[14] d6_passenger_getting_back_on_bike[14]
LVAR_INT d6_chase_bike_driver_dead_flag[14] d6_chase_bike_passenger_dead_flag[14]
LVAR_INT d6_driver_mission_changed_to_ram[14] d6_passenger_mission_changed_to_ram[14]
LVAR_INT d6_driver_sent_to_bike_to_ram_player[14] d6_passenger_sent_to_bike_to_ram_player[14]
LVAR_INT d6_player_car_armoured //d6_left_door_smashed d6_right_door_smashed d6_boot_smashed
//LVAR_INT d6_player_reached_bridge d6_player_passed_bridge
// temp for view_integer_variable
VAR_INT d6_player_in_ravine d6_player_in_tunnel
LVAR_INT d6_player_reached_zeroth_checkpoint
// temp for view_integer_variable
VAR_INT d6_player_reached_first_creation_pt d6_player_reached_second_creation_pt
LVAR_INT d6_player_reached_third_creation_pt d6_player_reached_fourth_creation_pt
// temp for view_integer_variable
VAR_INT d6_player_reached_fifth_creation_pt d6_player_reached_sixth_creation_pt
LVAR_INT d6_first_warning_given d6_second_warning_given
LVAR_INT d6_reset_timer_flag

LVAR_INT d6_cutscene_skipped d6_first_chase_bike_near_player_car d6_first_bikes_started_driveby

// blips

LVAR_INT d6_player_car_blip d6_countryside_blip d6_next_checkpoint_blip d6_destination_blip

// **************************************** Mission Start **********************************

mission_start_driv6:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT FARLIE5

flag_player_on_mission = 1

WAIT 0

// ************************************ Initialise variables *******************************

d6_chase_start_x = -1957.67
d6_chase_start_y = -2443.7
d6_chase_start_z = 30.83

d6_destination_x = -1571.4
d6_destination_y = -2742.09
d6_destination_z = 47.57

d6_cur_num_of_chase_bikes_created = 0
d6_total_num_of_chase_bikes = 14

d6_index = 0
WHILE d6_index < d6_total_num_of_chase_bikes
	d6_driver_getting_back_on_bike[d6_index] = 0
	d6_passenger_getting_back_on_bike[d6_index] = 0
	d6_chase_bike_driver_dead_flag[d6_index] = 0
	d6_chase_bike_passenger_dead_flag[d6_index] = 0
	d6_driver_mission_changed_to_ram[d6_index] = 0
	d6_passenger_mission_changed_to_ram[d6_index] = 0
	d6_driver_sent_to_bike_to_ram_player[d6_index] = 0
	d6_passenger_sent_to_bike_to_ram_player[d6_index] = 0
	d6_index++
ENDWHILE

//d6_triangle_pressed_last_frame = 0
//d6_player_already_tried_to_leave_car = 0

d6_player_picked_up_decoy_car = 0
d6_countryside_print_cleared = 0
d6_bike_chase_started = 0

//d6_player_reached_bridge = 0
//d6_player_passed_bridge = 0
d6_player_in_ravine = 0
d6_player_in_tunnel = 0

d6_player_reached_zeroth_checkpoint = 0
d6_player_reached_first_creation_pt = 0
d6_player_reached_second_creation_pt = 0
d6_player_reached_third_creation_pt = 0
d6_player_reached_fourth_creation_pt = 0
d6_player_reached_fifth_creation_pt = 0
d6_player_reached_sixth_creation_pt = 0

d6_num_of_chase_bike_groups_created = 0

d6_player_car_armoured = 0
//d6_left_door_smashed = 0
//d6_right_door_smashed = 0
//d6_boot_smashed = 0
d6_first_warning_given = 0
d6_second_warning_given = 0

d6_first_chase_bike_near_player_car = 0
d6_first_bikes_started_driveby = 0

d6_audio_is_playing = 0
d6_audio_index = 0
d6_started_talking = 0

d6_fake_creates = 0

// ****************************************START OF CUTSCENE*******************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 1

LOAD_CUTSCENE FARL_5a
 
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

SET_PLAYER_CONTROL player1 OFF

SET_AREA_VISIBLE 0

// ****************************************END OF CUTSCENE*********************************

// CREATE statements to keep the compiler happy

IF d6_fake_creates = 1
	CREATE_CAR RANCHER 0.0 0.0 -100.0 d6_player_car
	d6_index = 0
	WHILE d6_index < d6_total_num_of_chase_bikes
		CREATE_CAR SANCHEZ d6_chase_bike_x d6_chase_bike_y -100.0 d6_chase_bikes[d6_index]
		CREATE_CHAR_INSIDE_CAR d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB3 d6_chase_bike_drivers[d6_index]
		CREATE_CHAR_AS_PASSENGER d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB2 0 d6_chase_bike_passengers[d6_index]
		d6_index++
	ENDWHILE
ENDIF

//SET_UP_TAXI_SHORTCUT -2149.61 642.9 52.0 180.0 -2023.98 -2512.47 32.94 235.3

REQUEST_MODEL SANCHEZ
REQUEST_MODEL DNB3
REQUEST_MODEL DNB2
REQUEST_MODEL MP5LNG
WHILE NOT HAS_MODEL_LOADED SANCHEZ
   OR NOT HAS_MODEL_LOADED DNB3
   OR NOT HAS_MODEL_LOADED DNB2
   OR NOT HAS_MODEL_LOADED MP5LNG
	WAIT 0
ENDWHILE

SWITCH_CAR_GENERATOR car_gen_pine[3] 0
SWITCH_CAR_GENERATOR car_gen_pine[4] 0
SWITCH_CAR_GENERATOR car_gen_pine[5] 0

SET_CHAR_COORDINATES scplayer -2154.20 645.31 51.39
SET_CHAR_HEADING scplayer 270.0

GOSUB d6_create_player_car
ADD_BLIP_FOR_CAR d6_player_car d6_player_car_blip
SET_BLIP_AS_FRIENDLY d6_player_car_blip TRUE

// fades the screen in 

SET_FADING_COLOUR 0 0 0

WAIT 500

DO_FADE 1500 FADE_IN

// request models

//WAIT 1500

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

PRINT_NOW ( DRV6_8 ) 10000 0

// debug
//SET_CHAR_COORDINATES scplayer -1958.36 -2447.92 30.67
//SET_CHAR_HEADING scplayer 340.51
//SET_CAMERA_BEHIND_PLAYER

// temp
//VAR_INT num_of_chase_bike_groups_created cur_num_of_chase_bikes player_car_health
//VIEW_INTEGER_VARIABLE num_of_chase_bike_groups_created num_of_chase_bike_groups_created
//VIEW_INTEGER_VARIABLE cur_num_of_chase_bikes cur_num_of_chase_bikes
//VIEW_INTEGER_VARIABLE d6_player_car_health player_car_health
//VIEW_INTEGER_VARIABLE d6_player_in_ravine d6_player_in_ravine
//VIEW_INTEGER_VARIABLE d6_player_reached_second_creation_pt d6_player_reached_second_creation_pt
//VIEW_INTEGER_VARIABLE d6_player_in_tunnel d6_player_in_tunnel
//VIEW_INTEGER_VARIABLE d6_player_reached_fifth_creation_pt d6_player_reached_fifth_creation_pt

// Mission loop

driv6_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_driv6_passed  
	ENDIF

	// temp
	//cur_num_of_chase_bikes = d6_cur_num_of_chase_bikes_created
	//num_of_chase_bike_groups_created = d6_num_of_chase_bike_groups_created
	//player_car_health = d6_player_car_health

	IF NOT d6_player_picked_up_decoy_car = 1
		IF NOT IS_CAR_DEAD d6_player_car
			IF IS_CHAR_IN_CAR scplayer d6_player_car
				REMOVE_BLIP d6_player_car_blip
				d6_player_picked_up_decoy_car = 1
				ADD_BLIP_FOR_COORD -1963.84 -2441.49 30.78 d6_countryside_blip
				PRINT_NOW ( DRV6_19 ) 10000 0 // Drive out to the countryside.
				PRINT ( DRV6_17 ) 7000 0 // Do not leave the decoy car.
				IF d6_trip_skips_available = 1
					SET_UP_SKIP -1990.65 -2428.65 30.77 225.0
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF d6_player_picked_up_decoy_car = 1
	AND NOT d6_bike_chase_started = 1
		IF NOT IS_CAR_DEAD d6_player_car
			IF NOT d6_countryside_print_cleared = 1
			AND LOCATE_CHAR_ANY_MEANS_2D scplayer -1990.65 -2428.65 50.0 50.0 FALSE
				CLEAR_THIS_PRINT DRV6_19
				d6_countryside_print_cleared = 1
			ENDIF
			IF NOT d6_trip_skips_available = 1
			AND LOCATE_CHAR_ANY_MEANS_2D scplayer -1963.84 -2441.49 25.0 25.0 FALSE
				d6_trip_skips_available = 1
			ENDIF
			IF LOCATE_CHAR_IN_CAR_3D scplayer -1963.84 -2441.49 30.78 4.0 4.0 2.0 TRUE

//				IF NOT IS_CAR_DEAD d6_player_car
//					LOCK_CAR_DOORS d6_player_car CARLOCK_LOCKED_PLAYER_INSIDE
//				ENDIF
//
//				SET_CAMERA_BEHIND_PLAYER
//				PRINT_NOW ( DRV6_14 ) 10000 0 // Drive to the first checkpoint.

				SET_PLAYER_CONTROL player1 OFF

				CLEAR_SKIP
				REMOVE_BLIP d6_countryside_blip

				GOSUB d6_play_first_cutscene

				ADD_BLIP_FOR_COORD -1867.14 -2442.39 31.24 d6_next_checkpoint_blip
				CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
				CREATE_CHECKPOINT CHECKPOINT_TUBE -1867.14 -2442.39 31.24 -1178.32 -2638.85 10.8 4.0 d6_next_checkpoint
				DISPLAY_ONSCREEN_COUNTER_WITH_STRING d6_door_health COUNTER_DISPLAY_BAR ( DRV6_18 )
				//SET_ONSCREEN_COUNTER_COLOUR d6_door_health HUD_COLOUR_RED
				d6_door_damage = 0
				d6_door_health = 100
				SET_CAR_HEALTH d6_player_car 9000

				PRINT_NOW ( DRV6_2 ) 10000 0
				//PRINT ( DRV6_3 ) 7000 0
				PRINT ( DRV6_17 ) 7000 0
				PRINT ( DRV6_4 ) 10000 0
				PRINT ( DRV6_15 ) 10000 0

				d6_bike_chase_started = 1
				TIMERB = 0


			ENDIF
		ENDIF
	ENDIF


	IF d6_bike_chase_started = 1
		IF IS_WANTED_LEVEL_GREATER player1 0
			CLEAR_WANTED_LEVEL player1
		ENDIF

		IF NOT IS_CAR_DEAD d6_player_car

			IF NOT d6_first_bikes_started_driveby = 1
			AND TIMERB > 2000
				IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[0]
				AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[1]
					TASK_DRIVE_BY d6_chase_bike_passengers[0] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
					TASK_DRIVE_BY d6_chase_bike_passengers[1] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
					d6_first_bikes_started_driveby = 1
				ENDIF
			ENDIF

			// car only damaged when hit by bullets or flipped
			IF IS_CAR_UPSIDEDOWN d6_player_car
				IF d6_player_car_armoured = 1
					SET_CAR_PROOFS d6_player_car FALSE FALSE FALSE FALSE FALSE
					d6_player_car_armoured = 0
				ENDIF
			ELSE
				IF NOT d6_player_car_armoured = 1
					SET_CAR_PROOFS d6_player_car FALSE TRUE TRUE TRUE TRUE
					d6_player_car_armoured = 1
				ENDIF
			ENDIF

			GOSUB d6_keep_bikers_on_bikes

			// bikes escort at rear while in ravine or in tunnel
			// (easy to make them drive into walls otherwise)
			IF NOT d6_player_reached_second_creation_pt = 1 
				IF NOT d6_player_in_ravine = 1
					IF LOCATE_CAR_2D d6_player_car -1181.93 -2642.33 300.0 300.0 FALSE
						// bikes escort at rear while player's in ravine
						GOSUB d6_set_occupied_bikes_escort_rear
						d6_player_in_ravine = 1
					ENDIF
				ELSE
					IF NOT LOCATE_CAR_2D d6_player_car -1181.93 -2642.33 300.0 300.0 FALSE
						// set bikes back to usual type of escort after ravine
						GOSUB d6_set_occupied_bikes_escort_normal
						d6_player_in_ravine = 0
					ENDIF
				ENDIF
			ELSE
				IF d6_player_in_ravine = 1
					d6_player_in_ravine = 0
				ENDIF
			ENDIF
			IF d6_player_reached_fifth_creation_pt = 1 
				IF NOT d6_player_in_tunnel = 1
					IF LOCATE_CAR_2D d6_player_car -519.74 -2782.11 214.0 214.0 FALSE
						// bikes escort at rear while player's in tunnel
						GOSUB d6_set_occupied_bikes_escort_rear
						d6_player_in_tunnel = 1
					ENDIF
				ELSE
					IF NOT LOCATE_CAR_2D d6_player_car -519.74 -2782.11 214.0 214.0 FALSE
						// set bikes back to usual type of escort after tunnel
						GOSUB d6_set_occupied_bikes_escort_normal
						d6_player_in_tunnel = 0
					ENDIF
				ENDIF
			ELSE
				IF d6_player_in_tunnel = 1
					d6_player_in_tunnel = 0
				ENDIF
			ENDIF

			IF NOT d6_player_reached_zeroth_checkpoint = 1
				IF LOCATE_CAR_3D d6_player_car -1867.14 -2442.39 31.24 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					d6_player_reached_zeroth_checkpoint = 1
					ADD_BLIP_FOR_COORD -1178.32 -2638.85 10.8 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -1178.32 -2638.85 10.8 -654.95 -2469.35 33.30 4.0 d6_next_checkpoint
				ENDIF
			ENDIF				

			IF d6_player_reached_zeroth_checkpoint = 1
			AND NOT d6_player_reached_first_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -1178.32 -2638.85 10.8 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_first_creation_pt = 1
					ADD_BLIP_FOR_COORD -654.95 -2469.35 33.30 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -654.95 -2469.35 33.30 -309.54 -2248.81 29.59 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_player_reached_first_creation_pt = 1
			AND NOT d6_player_reached_second_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -654.95 -2469.35 33.30 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_second_creation_pt = 1
					ADD_BLIP_FOR_COORD -309.54 -2248.81 29.59 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -309.54 -2248.81 29.59 -165.70 -2421.71 35.10 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_player_reached_second_creation_pt = 1
			AND NOT d6_player_reached_third_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -309.54 -2248.81 29.59 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_third_creation_pt = 1
					ADD_BLIP_FOR_COORD -165.70 -2421.71 35.10 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -165.70 -2421.71 35.10 -191.26 -2822.52 42.09 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_player_reached_third_creation_pt = 1
			AND NOT d6_player_reached_fourth_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -165.70 -2421.71 35.10 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_fourth_creation_pt = 1
					ADD_BLIP_FOR_COORD -191.26 -2822.52 42.09 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -191.26 -2822.52 42.09 -946.91 -2844.84 67.36 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_player_reached_fourth_creation_pt = 1
			AND NOT d6_player_reached_fifth_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -191.26 -2822.52 42.09 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_fifth_creation_pt = 1
					ADD_BLIP_FOR_COORD -946.91 -2844.84 67.36 d6_next_checkpoint_blip
					CHANGE_BLIP_COLOUR d6_next_checkpoint_blip RED
					CREATE_CHECKPOINT CHECKPOINT_TUBE -946.91 -2844.84 67.36 d6_destination_x d6_destination_y d6_destination_z 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_player_reached_fifth_creation_pt = 1
			AND NOT d6_player_reached_sixth_creation_pt = 1
				IF LOCATE_CAR_3D d6_player_car -946.91 -2844.84 67.36 6.0 6.0 3.0 FALSE
					REMOVE_BLIP d6_next_checkpoint_blip
					DELETE_CHECKPOINT d6_next_checkpoint
					GOSUB d6_create_next_chase_bike_group
					d6_player_reached_sixth_creation_pt = 1
					ADD_BLIP_FOR_COORD d6_destination_x d6_destination_y d6_destination_z d6_destination_blip
					CHANGE_BLIP_COLOUR d6_destination_blip RED
					CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE d6_destination_x d6_destination_y d6_destination_z 0.0 0.0 0.0 4.0 d6_next_checkpoint
				ENDIF
			ENDIF

			IF d6_door_damage < 100
				GET_CAR_HEALTH d6_player_car d6_player_car_health
				// as car health decreases from 9000 to 3000, door damage increases from 0 to 100
				d6_door_damage = d6_player_car_health - 3000
				d6_door_damage /= 60
				d6_door_health = d6_door_damage
				d6_door_damage *= -1
				d6_door_damage += 100
			ENDIF
			// change appearance of doors as the damage meter increases
			IF d6_door_damage <= 45
				IF IS_CHAR_SITTING_IN_CAR scplayer d6_player_car
					OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_LEFT_DOOR 0.0
					OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_RIGHT_DOOR 0.0
				ENDIF
				CONTROL_CAR_DOOR d6_player_car FRONT_LEFT_DOOR DT_DOOR_INTACT 0.0
				CONTROL_CAR_DOOR d6_player_car FRONT_RIGHT_DOOR DT_DOOR_INTACT 0.0
			ENDIF
			IF d6_door_damage > 45
			AND d6_door_damage <= 80
				IF IS_CHAR_SITTING_IN_CAR scplayer d6_player_car
					OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_LEFT_DOOR 0.0
					OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_RIGHT_DOOR 0.0
				ENDIF
				CONTROL_CAR_DOOR d6_player_car FRONT_LEFT_DOOR DT_DOOR_BASHED 0.0
				CONTROL_CAR_DOOR d6_player_car FRONT_RIGHT_DOOR DT_DOOR_BASHED 0.0
			ENDIF
			IF d6_door_damage > 80
			AND d6_door_damage < 100
				CONTROL_CAR_DOOR d6_player_car FRONT_LEFT_DOOR DT_DOOR_BASHED_AND_SWINGING_FREE -1.0
				CONTROL_CAR_DOOR d6_player_car FRONT_RIGHT_DOOR DT_DOOR_BASHED_AND_SWINGING_FREE -1.0
			ENDIF
			IF d6_door_damage >= 100
				CONTROL_CAR_DOOR d6_player_car FRONT_LEFT_DOOR DT_DOOR_MISSING -1.0
				CONTROL_CAR_DOOR d6_player_car FRONT_RIGHT_DOOR DT_DOOR_MISSING -1.0
			ENDIF

			IF d6_door_damage > 80
			AND NOT d6_first_warning_given = 1
				PRINT_NOW ( DRV6_6 ) 7000 0
				d6_first_warning_given = 1
			ENDIF
			IF d6_door_damage >= 100
			AND NOT d6_second_warning_given = 1
				CLEAR_ONSCREEN_COUNTER d6_door_health
				PRINT_NOW ( DRV6_16 ) 5000 0
				TIMERA = 0
				d6_second_warning_given = 1
			ENDIF
			IF d6_second_warning_given = 1
				// if no bikers near either of the doors, reset timer
				GOSUB d6_reset_timer_check
			ENDIF

		ENDIF

	ENDIF

	// mission pass/fail conditions
	IF NOT d6_player_picked_up_decoy_car = 1

		IF IS_CAR_DEAD d6_player_car
			CLEAR_PRINTS
			PRINT_NOW ( DRV6_11 ) 5000 0
			GOTO mission_driv6_failed
		ENDIF

	ENDIF
	IF d6_player_picked_up_decoy_car = 1
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			IF d6_bike_chase_started = 1
				GOSUB d6_bikes_stop_and_look
				// only play audio if anyone's near the player
				LVAR_INT d6_any_chars_near_player
				d6_any_chars_near_player = 0
				d6_index = 0
				WHILE d6_index < d6_cur_num_of_chase_bikes_created
				AND NOT d6_any_chars_near_player = 1
					IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer d6_chase_bike_drivers[d6_index] 20.0 20.0 20.0 FALSE
							d6_any_chars_near_player = 1
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer d6_chase_bike_passengers[d6_index] 20.0 20.0 20.0 FALSE
							d6_any_chars_near_player = 1
						ENDIF
					ENDIF
					d6_index++
				ENDWHILE
				IF d6_any_chars_near_player = 1
					d6_current_audio_needed = D6_MISSION_FAILED_AUDIO
					GOSUB d6_setup_audio_data
					d6_audio_index = 1
					WHILE d6_audio_index < d6_total_audio_to_play
						 WAIT 0
						 GOSUB d6_load_and_play_audio
					ENDWHILE
				ENDIF
				GOSUB d6_disengage_bikes
			ENDIF
			CLEAR_PRINTS
			PRINT_NOW ( DRV6_7 ) 5000 0
			GOTO mission_driv6_failed
		ENDIF
	ENDIF
	IF d6_bike_chase_started = 1

		IF d6_second_warning_given = 1
		AND TIMERA > 10000
			d6_current_audio_needed = D6_MISSION_FAILED_AUDIO
			GOSUB d6_setup_audio_data
			WHILE d6_audio_index < 1
				 WAIT 0
				 GOSUB d6_load_and_play_audio
			ENDWHILE
			WHILE d6_audio_index < d6_total_audio_to_play
				 WAIT 0
				 GOSUB d6_load_and_play_audio
			ENDWHILE
			GOSUB d6_disengage_bikes
			CLEAR_PRINTS
			PRINT_NOW ( DRV6_7 ) 5000 0
			GOTO mission_driv6_failed
		ENDIF

		IF d6_player_reached_sixth_creation_pt = 1
			IF LOCATE_CHAR_IN_CAR_3D scplayer d6_destination_x d6_destination_y d6_destination_z 6.0 6.0 3.0 TRUE
				SET_PLAYER_CONTROL player1 OFF
				REMOVE_BLIP d6_destination_blip
				DELETE_CHECKPOINT d6_next_checkpoint
				GOSUB d6_play_mission_passed_cutscene
				GOTO mission_driv6_passed
			ENDIF
		ENDIF

	ENDIF


GOTO driv6_loop


// ********************************** Mission GOSUBS ************************************

// ************************************************************
// 					  Create player's car
// ************************************************************

	d6_create_player_car:

		REQUEST_MODEL RANCHER

		WHILE NOT HAS_MODEL_LOADED RANCHER
			WAIT 0
		ENDWHILE

		CLEAR_AREA -2149.95 644.31 52.35 100.0 FALSE
		CREATE_CAR RANCHER -2149.95 644.31 52.35 d6_player_car
		SET_CAR_HEADING d6_player_car 180.0
		SET_CAR_STRONG d6_player_car TRUE
		SET_CAN_BURST_CAR_TYRES d6_player_car FALSE
		SET_CAR_HEALTH d6_player_car 9000
		SET_CAR_CAN_BE_VISIBLY_DAMAGED d6_player_car FALSE
		//SET_CAR_PROOFS d6_player_car TRUE TRUE TRUE TRUE TRUE

	RETURN

// ************************************************************
// 			Create first two chase bikes for cutscene
// ************************************************************

	d6_create_first_chase_bikes:

//		CREATE_CAR SANCHEZ -2013.89 -2404.18 30.29 d6_chase_bikes[0]
//		SET_CAR_HEADING d6_chase_bikes[0] 221.95
//		CREATE_CAR SANCHEZ -2016.9 -2404.14 30.3 d6_chase_bikes[1]
//		SET_CAR_HEADING d6_chase_bikes[1] 224.23
//		CREATE_CAR SANCHEZ -2010.39 -2410.84 30.3 d6_chase_bikes[0]
//		SET_CAR_HEADING d6_chase_bikes[0] 230.57
//		CREATE_CAR SANCHEZ -2011.75 -2413.4 30.3 d6_chase_bikes[1]
//		SET_CAR_HEADING d6_chase_bikes[1] 233.07
		CREATE_CAR SANCHEZ -2015.66 -2408.82 30.28 d6_chase_bikes[0]
		SET_CAR_HEADING d6_chase_bikes[0] 229.35
		CREATE_CAR SANCHEZ -2017.07 -2410.37 30.3 d6_chase_bikes[1]
		SET_CAR_HEADING d6_chase_bikes[1] 225.81

		d6_index = 0
		WHILE d6_index < 2
			CREATE_CHAR_INSIDE_CAR d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB3 d6_chase_bike_drivers[d6_index]
			CREATE_CHAR_AS_PASSENGER d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB2 0 d6_chase_bike_passengers[d6_index]
			GIVE_WEAPON_TO_CHAR d6_chase_bike_passengers[d6_index] WEAPONTYPE_MP5 99999
			SET_CAR_ONLY_DAMAGED_BY_PLAYER d6_chase_bikes[d6_index] TRUE
			SET_CAR_TRACTION d6_chase_bikes[d6_index] 2.0
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE d6_chase_bike_drivers[d6_index] KNOCKOFFBIKE_ALWAYSNORMAL
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE d6_chase_bike_passengers[d6_index] KNOCKOFFBIKE_ALWAYSNORMAL
			d6_cur_num_of_chase_bikes_created++
			d6_index++
		ENDWHILE

		d6_num_of_chase_bike_groups_created++

	RETURN

// ************************************************************
// 			   Show bikes first closing in on player
// ************************************************************

	d6_play_first_cutscene:

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		CLEAR_AREA d6_chase_start_x d6_chase_start_y d6_chase_start_z 300.0 FALSE
		CLEAR_PRINTS

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		IF NOT IS_CAR_DEAD d6_player_car
			SET_CAR_COORDINATES d6_player_car d6_chase_start_x d6_chase_start_y d6_chase_start_z
			SET_CAR_HEADING d6_player_car 241.85
		ENDIF

//		// preload audio
//		LOAD_MISSION_AUDIO 1 SOUND_FAR5_BB
//		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
//			WAIT 0
//		ENDWHILE

		GOSUB d6_create_first_chase_bikes

		SET_FIXED_CAMERA_POSITION -1955.92 -2447.69 30.78 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1962.83 -2441.79 30.44 JUMP_CUT

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		d6_cutscene_skipped = 1

		SKIP_CUTSCENE_START

		IF NOT IS_CAR_DEAD d6_player_car
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[0]
			AND NOT IS_CAR_DEAD d6_chase_bikes[0]
//				TASK_CAR_DRIVE_TO_COORD d6_chase_bike_drivers[0] d6_chase_bikes[0] -1989.59 -2428.97 30.29 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD d6_chase_bike_drivers[0] d6_chase_bikes[0] -1977.97 -2435.05 30.3 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			ENDIF
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[1]
			AND NOT IS_CAR_DEAD d6_chase_bikes[1]
//				TASK_CAR_DRIVE_TO_COORD d6_chase_bike_drivers[1] d6_chase_bikes[1] -1990.75 -2430.89 30.29 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
				TASK_CAR_DRIVE_TO_COORD d6_chase_bike_drivers[1] d6_chase_bikes[1] -1979.45 -2437.47 30.3 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			ENDIF
		ENDIF

//		PRINT_NOW FAR5_BB 10000 1
//		PLAY_MISSION_AUDIO 1
//		d6_audio_is_playing = 2
		TIMERB = 0
		WHILE TIMERB < 2500
			WAIT 0
//			IF HAS_MISSION_AUDIO_FINISHED 1
//			AND NOT d6_audio_is_playing = 0
//				CLEAR_PRINTS
//				d6_audio_is_playing = 0
//			ENDIF
		ENDWHILE

		IF NOT IS_CAR_DEAD d6_chase_bikes[0]
//			SET_FIXED_CAMERA_POSITION -1953.61 -2453.81 34.47 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -1961.58 -2442.97 32.5 JUMP_CUT
			SET_FIXED_CAMERA_POSITION -1989.91 -2435.33 30.68 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1987.22 -2436.07 30.82 JUMP_CUT
		ENDIF

		IF NOT IS_CAR_DEAD d6_chase_bikes[0]
//			SET_CAR_COORDINATES d6_chase_bikes[0] -1990.59 -2429.97 30.29
//			SET_CAR_HEADING d6_chase_bikes[0] 228.93
			SET_CAR_COORDINATES d6_chase_bikes[0] -1985.6 -2435.14 30.3
			SET_CAR_HEADING d6_chase_bikes[0] 254.67
		ENDIF
		IF NOT IS_CAR_DEAD d6_chase_bikes[1]
//			SET_CAR_COORDINATES d6_chase_bikes[1] -1991.75 -2431.89 30.29
//			SET_CAR_HEADING d6_chase_bikes[1] 239.99
			SET_CAR_COORDINATES d6_chase_bikes[1] -1986.37 -2437.8 30.3
			SET_CAR_HEADING d6_chase_bikes[1] 250.82
		ENDIF

		IF NOT IS_CAR_DEAD d6_player_car
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[0]
			AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[0]
			AND NOT IS_CAR_DEAD d6_chase_bikes[0]
				SET_CAR_STRAIGHT_LINE_DISTANCE d6_chase_bikes[0] 250
				TASK_CAR_MISSION d6_chase_bike_drivers[0] d6_chase_bikes[0] d6_player_car MISSION_ESCORT_LEFT 10.0 DRIVINGMODE_AVOIDCARS
				//TASK_DRIVE_BY d6_chase_bike_passengers[0] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
			ENDIF
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[1]
			AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[1]
			AND NOT IS_CAR_DEAD d6_chase_bikes[1]
				SET_CAR_STRAIGHT_LINE_DISTANCE d6_chase_bikes[1] 250
				TASK_CAR_MISSION d6_chase_bike_drivers[1] d6_chase_bikes[1] d6_player_car MISSION_ESCORT_RIGHT 10.0 DRIVINGMODE_AVOIDCARS
				//TASK_DRIVE_BY d6_chase_bike_passengers[1] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
			ENDIF
		ENDIF

		WHILE NOT d6_first_chase_bike_near_player_car = 1
		AND NOT IS_CHAR_DEAD d6_chase_bike_drivers[0]
		AND NOT IS_CAR_DEAD d6_chase_bikes[0]
		AND NOT IS_CAR_DEAD d6_player_car
			IF IS_CHAR_IN_CAR d6_chase_bike_drivers[0] d6_chase_bikes[0]
				IF LOCATE_CHAR_IN_CAR_CAR_2D d6_chase_bike_drivers[0] d6_player_car 15.0 15.0 FALSE
					d6_first_chase_bike_near_player_car = 1
				ENDIF
			ELSE
				// break out of loop
				d6_first_chase_bike_near_player_car = 1
			ENDIF
			WAIT 0
//			IF HAS_MISSION_AUDIO_FINISHED 1
//			AND NOT d6_audio_is_playing = 0
//				CLEAR_PRINTS
//				d6_audio_is_playing = 0
//			ENDIF
		ENDWHILE

		d6_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF d6_cutscene_skipped = 1
			IF NOT IS_CAR_DEAD d6_player_car
				IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[0]
				AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[0]
				AND NOT IS_CAR_DEAD d6_chase_bikes[0]
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.0 -14.0 0.0 d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
					d6_chase_bike_z = -100.0
					SET_CAR_COORDINATES d6_chase_bikes[0] d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
//					TASK_CAR_MISSION d6_chase_bike_drivers[0] d6_chase_bikes[0] d6_player_car MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
					//TASK_DRIVE_BY d6_chase_bike_passengers[0] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
				ENDIF
				IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[1]
				AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[1]
				AND NOT IS_CAR_DEAD d6_chase_bikes[1]
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.0 -14.0 0.0 d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
					d6_chase_bike_z = -100.0
					SET_CAR_COORDINATES d6_chase_bikes[1] d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
//					TASK_CAR_MISSION d6_chase_bike_drivers[1] d6_chase_bikes[1] d6_player_car MISSION_ESCORT_RIGHT 30.0 DRIVINGMODE_AVOIDCARS
					//TASK_DRIVE_BY d6_chase_bike_passengers[1] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
				ENDIF
			ENDIF
		ENDIF

//		CLEAR_MISSION_AUDIO 1
//		CLEAR_PRINTS

		// set straight line distance of bikes back to default
		IF NOT IS_CAR_DEAD d6_chase_bikes[0]
			SET_CAR_STRAIGHT_LINE_DISTANCE d6_chase_bikes[0] 20
		ENDIF
		IF NOT IS_CAR_DEAD d6_chase_bikes[1]
			SET_CAR_STRAIGHT_LINE_DISTANCE d6_chase_bikes[1] 20
		ENDIF

		IF NOT IS_CAR_DEAD d6_player_car
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[0]
			AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[0]
			AND NOT IS_CAR_DEAD d6_chase_bikes[0]
				TASK_CAR_MISSION d6_chase_bike_drivers[0] d6_chase_bikes[0] d6_player_car MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
			ENDIF
			IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[1]
			AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[1]
			AND NOT IS_CAR_DEAD d6_chase_bikes[1]
				TASK_CAR_MISSION d6_chase_bike_drivers[1] d6_chase_bikes[1] d6_player_car MISSION_ESCORT_RIGHT 30.0 DRIVINGMODE_AVOIDCARS
			ENDIF
		ENDIF

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

	RETURN

// ************************************************************
// 	   Create groups of chasing bikes throughout the mission
// ************************************************************

	d6_create_next_chase_bike_group:

		IF NOT IS_CAR_DEAD d6_player_car

			// find number of bikes close to player and delete bikes not close enough
			d6_num_of_chase_bikes_close = 0
			d6_index = 0
			WHILE d6_index < d6_cur_num_of_chase_bikes_created
				IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
					IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer d6_chase_bikes[d6_index] 100.0 100.0 FALSE
						d6_num_of_chase_bikes_close++
					ELSE
						DELETE_CAR d6_chase_bikes[d6_index]
						DELETE_CHAR d6_chase_bike_drivers[d6_index]
						DELETE_CHAR d6_chase_bike_passengers[d6_index]
					ENDIF
				ENDIF
				d6_index++
			ENDWHILE

			// don't have more than four bikes chasing the player at once
			IF NOT d6_num_of_chase_bikes_close > 2

				d6_index = d6_num_of_chase_bike_groups_created * 2
				d6_one_past_end_of_group = d6_index + 2
				d6_index_within_group = 0
				WHILE d6_index < d6_one_past_end_of_group

					IF d6_player_reached_zeroth_checkpoint = 1
					AND NOT d6_player_reached_first_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -1342.94
							d6_chase_bike_y = -2624.45
							d6_chase_bike_z = 19.58
							d6_chase_bike_heading = 261.04
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -1344.02
							d6_chase_bike_y = -2628.07
							d6_chase_bike_z = 19.82
							d6_chase_bike_heading = 260.5
						ENDIF
					ENDIF
					IF d6_player_reached_first_creation_pt = 1
					AND NOT d6_player_reached_second_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -708.68
							d6_chase_bike_y = -2601.46
							d6_chase_bike_z = 69.83
							d6_chase_bike_heading = 338.56
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -704.89
							d6_chase_bike_y = -2602.92
							d6_chase_bike_z = 69.9
							d6_chase_bike_heading = 340.64
						ENDIF
					ENDIF
					IF d6_player_reached_second_creation_pt = 1
					AND NOT d6_player_reached_third_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -420.89
							d6_chase_bike_y = -2258.87
							d6_chase_bike_z = 47.37
							d6_chase_bike_heading = 278.1
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -420.64
							d6_chase_bike_y = -2261.39
							d6_chase_bike_z = 47.79
							d6_chase_bike_heading = 279.7
						ENDIF
					ENDIF
					IF d6_player_reached_third_creation_pt = 1
					AND NOT d6_player_reached_fourth_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -260.22
							d6_chase_bike_y = -2323.12
							d6_chase_bike_z = 30.42
							d6_chase_bike_heading = 222.63
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -262.81
							d6_chase_bike_y = -2326.8
							d6_chase_bike_z = 30.43
							d6_chase_bike_heading = 220.93
						ENDIF
					ENDIF
					IF d6_player_reached_fourth_creation_pt = 1
					AND NOT d6_player_reached_fifth_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -179.45
							d6_chase_bike_y = -2708.91
							d6_chase_bike_z = 35.82
							d6_chase_bike_heading = 177.36
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -182.07
							d6_chase_bike_y = -2709.06
							d6_chase_bike_z = 35.77
							d6_chase_bike_heading = 176.56
						ENDIF
					ENDIF
					IF d6_player_reached_fifth_creation_pt = 1
					AND NOT d6_player_reached_sixth_creation_pt = 1
						IF d6_index_within_group = 0
							d6_chase_bike_x = -846.18
							d6_chase_bike_y = -2809.6
							d6_chase_bike_z = 70.32
							d6_chase_bike_heading = 112.41
						ENDIF
						IF d6_index_within_group = 1
							d6_chase_bike_x = -848.6
							d6_chase_bike_y = -2806.3
							d6_chase_bike_z = 70.29
							d6_chase_bike_heading = 111.76
						ENDIF
					ENDIF
					CLEAR_AREA d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z 5.0 TRUE
					CREATE_CAR SANCHEZ d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z d6_chase_bikes[d6_index]
					CREATE_CHAR_INSIDE_CAR d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB3 d6_chase_bike_drivers[d6_index]
					CREATE_CHAR_AS_PASSENGER d6_chase_bikes[d6_index] PEDTYPE_MISSION1 DNB2 0 d6_chase_bike_passengers[d6_index]
					SET_CAR_HEADING d6_chase_bikes[d6_index] d6_chase_bike_heading

					SET_CAR_ONLY_DAMAGED_BY_PLAYER d6_chase_bikes[d6_index] TRUE
					SET_CAR_TRACTION d6_chase_bikes[d6_index] 2.0
					SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE d6_chase_bike_drivers[d6_index] KNOCKOFFBIKE_ALWAYSNORMAL
					SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE d6_chase_bike_passengers[d6_index] KNOCKOFFBIKE_ALWAYSNORMAL

					IF d6_player_in_ravine = 1
					OR d6_player_in_tunnel = 1
						TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS
					ELSE
						IF d6_index_within_group = 0
							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
						ENDIF
						IF d6_index_within_group = 1
							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_RIGHT 30.0 DRIVINGMODE_AVOIDCARS
						ENDIF
//						IF d6_index_within_group = 2
//							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS
//						ENDIF
					ENDIF
					GIVE_WEAPON_TO_CHAR d6_chase_bike_passengers[d6_index] WEAPONTYPE_MP5 99999
					TASK_DRIVE_BY d6_chase_bike_passengers[d6_index] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60

					IF d6_player_reached_second_creation_pt = 1
					AND NOT d6_player_reached_third_creation_pt = 1
						SET_CAR_FORWARD_SPEED d6_chase_bikes[d6_index] 30.0
					ELSE
						SET_CAR_FORWARD_SPEED d6_chase_bikes[d6_index] 70.0
					ENDIF

					d6_cur_num_of_chase_bikes_created++
					d6_index++
					d6_index_within_group++

				ENDWHILE

				d6_num_of_chase_bike_groups_created++

			ENDIF

		ENDIF

	RETURN

// ************************************************************
// 			  Check if anyone's fallen off their bike
// ************************************************************

	d6_keep_bikers_on_bikes:

		IF NOT IS_CAR_DEAD d6_player_car

			d6_index = 0
			WHILE d6_index < d6_cur_num_of_chase_bikes_created
				IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
					IF IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					AND NOT d6_chase_bike_driver_dead_flag[d6_index] = 1
						d6_chase_bike_driver_dead_flag[d6_index] = 1
					ENDIF
					IF IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
					AND NOT d6_chase_bike_passenger_dead_flag[d6_index] = 1
						d6_chase_bike_passenger_dead_flag[d6_index] = 1
					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					AND d6_chase_bike_passenger_dead_flag[d6_index] = 1
					AND NOT d6_driver_mission_changed_to_ram[d6_index] = 1
						TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_BLOCKCAR_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
						d6_driver_sent_to_bike_to_ram_player[d6_index] = 1
						d6_driver_mission_changed_to_ram[d6_index] = 1
					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
					AND d6_chase_bike_driver_dead_flag[d6_index] = 1
					AND NOT d6_passenger_mission_changed_to_ram[d6_index] = 1
						IF IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							// put passenger into driver's seat
							GET_CAR_COORDINATES d6_chase_bikes[d6_index] d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
							WARP_CHAR_FROM_CAR_TO_COORD d6_chase_bike_passengers[d6_index] d6_chase_bike_x d6_chase_bike_y -10.0
							WARP_CHAR_INTO_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							TASK_CAR_MISSION d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_BLOCKCAR_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
						ENDIF
//						TASK_CAR_MISSION d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_BLOCKCAR_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
//						d6_passenger_sent_to_bike_to_ram_player[d6_index] = 1
						d6_passenger_mission_changed_to_ram[d6_index] = 1
					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					AND d6_driver_mission_changed_to_ram[d6_index] = 1

						IF NOT IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
							IF d6_driver_sent_to_bike_to_ram_player[d6_index] = 0
								TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_BLOCKCAR_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
								d6_driver_sent_to_bike_to_ram_player[d6_index] = 1
							ENDIF
						ELSE
							IF d6_driver_sent_to_bike_to_ram_player[d6_index] = 1
								d6_driver_sent_to_bike_to_ram_player[d6_index] = 0
							ENDIF
						ENDIF

					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
					AND d6_passenger_mission_changed_to_ram[d6_index] = 1

						IF NOT IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							IF d6_passenger_sent_to_bike_to_ram_player[d6_index] = 0
								TASK_CAR_MISSION d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_BLOCKCAR_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
								d6_passenger_sent_to_bike_to_ram_player[d6_index] = 1
							ENDIF
						ELSE
							IF d6_passenger_sent_to_bike_to_ram_player[d6_index] = 1
								d6_passenger_sent_to_bike_to_ram_player[d6_index] = 0
							ENDIF
						ENDIF

					ENDIF
					IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]

						IF NOT IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
						AND d6_driver_getting_back_on_bike[d6_index] = 0
							TASK_ENTER_CAR_AS_DRIVER d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] 10000
							d6_driver_getting_back_on_bike[d6_index] = 1
						ENDIF

						IF NOT IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
						AND d6_passenger_getting_back_on_bike[d6_index] = 0
							TASK_ENTER_CAR_AS_PASSENGER d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] 10000 0
							d6_passenger_getting_back_on_bike[d6_index] = 1
						ENDIF

						IF IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
						AND IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
 							IF d6_driver_getting_back_on_bike[d6_index] = 1
							OR d6_passenger_getting_back_on_bike[d6_index] = 1

								IF d6_player_in_ravine = 1
								OR d6_player_in_tunnel = 1
									TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS
								ELSE
									// 0 or 1 within each group of two bikes created
									d6_index_within_group = d6_index
									WHILE d6_index_within_group >= 2
										d6_index_within_group -= 2
									ENDWHILE
									IF d6_index_within_group = 0
										TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
									ENDIF
									IF d6_index_within_group = 1
										TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_RIGHT 30.0 DRIVINGMODE_AVOIDCARS
									ENDIF
//									IF d6_index_within_group = 2
//										TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS
//									ENDIF
								ENDIF
								TASK_DRIVE_BY d6_chase_bike_passengers[d6_index] scplayer -1 0.0 0.0 0.0 999999.0 DRIVEBY_AI_ALL_DIRN FALSE 60
								d6_driver_getting_back_on_bike[d6_index] = 0
								d6_passenger_getting_back_on_bike[d6_index] = 0

							ENDIF
						ENDIF

					ENDIF
					IF IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					AND IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
						SET_CAR_MISSION d6_chase_bikes[d6_index] MISSION_NONE
					ENDIF
				ENDIF
				d6_index++
			ENDWHILE

		ENDIF

	RETURN

// *************************************************************
// 		  All bikes escort at rear (player reached bridge)
// *************************************************************

	d6_set_occupied_bikes_escort_rear:

		IF NOT IS_CAR_DEAD d6_player_car
		
			d6_index = 0
			WHILE d6_index < d6_cur_num_of_chase_bikes_created

				IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
				AND NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
				AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]

					IF IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
					AND IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]

						TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS

					ENDIF

				ENDIF

				d6_index++

			ENDWHILE

		ENDIF

	RETURN

// *************************************************************
//    All bikes return to normal escort (player passed bridge)
// *************************************************************

	d6_set_occupied_bikes_escort_normal:

		IF NOT IS_CAR_DEAD d6_player_car
		
			d6_index = 0
			WHILE d6_index < d6_cur_num_of_chase_bikes_created

				IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
				AND NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
				AND NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]

					IF IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
					AND IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]

						// 0 or 1 within each group of two bikes created
						d6_index_within_group = d6_index
						WHILE d6_index_within_group >= 2
							d6_index_within_group -= 2
						ENDWHILE
						IF d6_index_within_group = 0
							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
						ENDIF
						IF d6_index_within_group = 1
							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_RIGHT 30.0 DRIVINGMODE_AVOIDCARS
						ENDIF
//						IF d6_index_within_group = 2
//							TASK_CAR_MISSION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] d6_player_car MISSION_ESCORT_REAR 30.0 DRIVINGMODE_AVOIDCARS
//						ENDIF

					ENDIF

				ENDIF

				d6_index++

			ENDWHILE

		ENDIF

	RETURN

// *************************************************************
// 				Reset timer if no bikers near doors
// *************************************************************

	d6_reset_timer_check:

		IF NOT IS_CAR_DEAD d6_player_car

			d6_reset_timer_flag = 1
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS d6_player_car -1.0 0.0 0.0 d6_left_offset_x d6_left_offset_y d6_left_offset_z
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS d6_player_car 1.0 0.0 0.0 d6_right_offset_x d6_right_offset_y d6_right_offset_z
			d6_index = 0
			WHILE d6_index < d6_cur_num_of_chase_bikes_created
			AND d6_reset_timer_flag = 1
				IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
//					IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					GET_DRIVER_OF_CAR d6_chase_bikes[d6_index] d6_temp_driver
					IF NOT d6_temp_driver = -1
						IF LOCATE_CAR_3D d6_chase_bikes[d6_index] d6_left_offset_x d6_left_offset_y d6_left_offset_z 5.0 5.0 5.0 FALSE
						OR LOCATE_CAR_3D d6_chase_bikes[d6_index] d6_right_offset_x d6_right_offset_y d6_right_offset_z 5.0 5.0 5.0 FALSE
							// don't reset timer (bike with driver close enough to see into car)
							d6_reset_timer_flag = 0
						ENDIF
					ENDIF
				ENDIF
				d6_index++
			ENDWHILE

			IF d6_reset_timer_flag = 1
				TIMERA = 0
			ENDIF

		ENDIF

	RETURN

// *************************************************************
// 				Bikes disengage at end of mission
// *************************************************************

	d6_disengage_bikes:

		d6_index = 0
		WHILE d6_index < d6_cur_num_of_chase_bikes_created
			IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
				IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					TASK_CAR_DRIVE_TO_COORD d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] -2148.62 1063.98 79.73 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
					SET_CHAR_KEEP_TASK d6_chase_bike_drivers[d6_index] TRUE
				ELSE
					IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
						IF IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							// put passenger into driver's seat
							GET_CAR_COORDINATES d6_chase_bikes[d6_index] d6_chase_bike_x d6_chase_bike_y d6_chase_bike_z
							WARP_CHAR_FROM_CAR_TO_COORD d6_chase_bike_passengers[d6_index] d6_chase_bike_x d6_chase_bike_y -10.0
							WARP_CHAR_INTO_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
						ENDIF
						TASK_CAR_DRIVE_TO_COORD d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] -2148.62 1063.98 79.73 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
					SET_CHAR_KEEP_TASK d6_chase_bike_passengers[d6_index] TRUE
				ENDIF
			ENDIF
			d6_index++
		ENDWHILE
		
	RETURN

// ************************************************************
// 			If player leaves car, bikers stop and look
// ************************************************************

	d6_bikes_stop_and_look:

		d6_index = 0
		WHILE d6_index < d6_cur_num_of_chase_bikes_created
			IF NOT IS_CAR_DEAD d6_chase_bikes[d6_index]
				IF NOT IS_CHAR_DEAD d6_chase_bike_drivers[d6_index]
					TASK_LOOK_AT_CHAR d6_chase_bike_drivers[d6_index] scplayer 5000
					IF IS_CHAR_IN_CAR d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index]
						TASK_CAR_TEMP_ACTION d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] TEMPACT_HANDBRAKESTRAIGHT 2000000
					ELSE
						TASK_ENTER_CAR_AS_DRIVER d6_chase_bike_drivers[d6_index] d6_chase_bikes[d6_index] -1
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD d6_chase_bike_passengers[d6_index]
					TASK_LOOK_AT_CHAR d6_chase_bike_passengers[d6_index] scplayer 5000
					IF d6_passenger_mission_changed_to_ram[d6_index] = 1
						IF IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							TASK_CAR_TEMP_ACTION d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] TEMPACT_HANDBRAKESTRAIGHT 2000000
						ELSE
							TASK_ENTER_CAR_AS_DRIVER d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] -1
						ENDIF
					ELSE
						IF NOT IS_CHAR_IN_CAR d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index]
							TASK_ENTER_CAR_AS_PASSENGER d6_chase_bike_passengers[d6_index] d6_chase_bikes[d6_index] -1 0
						ENDIF
					ENDIF
				ENDIF
 			ENDIF
			d6_index++
		ENDWHILE

	RETURN

// *************************************************************
// 			Bikes disengage so not in final cutscene
// *************************************************************

	d6_disengage_bikes_for_mission_passed_cutscene:

		d6_index = 0
		WHILE d6_index < d6_cur_num_of_chase_bikes_created
			DELETE_CHAR d6_chase_bike_drivers[d6_index]
			DELETE_CHAR d6_chase_bike_passengers[d6_index]
			DELETE_CAR d6_chase_bikes[d6_index]
			d6_index++
		ENDWHILE
		
	RETURN

// ************************************************************
// 			Player gets phone call when near destination
// ************************************************************

	d6_play_mission_passed_cutscene:

		IF NOT IS_CAR_DEAD d6_player_car
			LOCK_CAR_DOORS d6_player_car CARLOCK_LOCKED
		ENDIF
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		
		CLEAR_AREA d6_destination_x d6_destination_y d6_destination_z 100.0 FALSE

		GOSUB d6_disengage_bikes_for_mission_passed_cutscene
		
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		LVAR_INT d6_mobile_phone
		REQUEST_MODEL cellphone
		WHILE NOT HAS_MODEL_LOADED cellphone
			WAIT 0
		ENDWHILE
		CREATE_OBJECT_NO_OFFSET cellphone -1561.4 -2721.1 47.57 d6_mobile_phone
		SET_OBJECT_VISIBLE d6_mobile_phone FALSE
		TASK_PICK_UP_OBJECT scplayer d6_mobile_phone 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE

		IF NOT IS_CAR_DEAD d6_player_car
			CAR_SET_IDLE d6_player_car
			SET_CAR_COORDINATES d6_player_car d6_destination_x d6_destination_y -100.0    
			SET_CAR_HEADING d6_player_car 55.5
			// make sure door's closed so it's not in the way of the camera
			OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_LEFT_DOOR 0.0
			OPEN_CAR_DOOR_A_BIT d6_player_car FRONT_RIGHT_DOOR 0.0
		ENDIF

		REQUEST_ANIMATION CAR_CHAT
		LOAD_MISSION_AUDIO 3 SOUND_MOBRING
		WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
		   OR NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE

		SET_FIXED_CAMERA_POSITION -1573.6 -2743.74 49.12 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1573.0 -2743.39 49.05 JUMP_CUT

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			IF DOES_OBJECT_EXIST d6_mobile_phone
				SET_OBJECT_VISIBLE d6_mobile_phone FALSE
			ENDIF
			WAIT 0
		ENDWHILE

		d6_cutscene_skipped = 1

		SKIP_CUTSCENE_START

		OPEN_SEQUENCE_TASK d6_car_chat_seq
			TASK_PLAY_ANIM -1 carfone_in CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
			TASK_PLAY_ANIM -1 carfone_loopA_to_B CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
			TASK_PLAY_ANIM -1 carfone_loopB CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK d6_car_chat_seq
		PERFORM_SEQUENCE_TASK scplayer d6_car_chat_seq
		CLEAR_SEQUENCE_TASK d6_car_chat_seq

		PLAY_MISSION_AUDIO 3

		TIMERA = 0
		WHILE TIMERA < 3000
			IF DOES_OBJECT_EXIST d6_mobile_phone
				SET_OBJECT_VISIBLE d6_mobile_phone FALSE
			ENDIF
			WAIT 0
		ENDWHILE
		SET_OBJECT_VISIBLE d6_mobile_phone TRUE
		CLEAR_MISSION_AUDIO 3
		
		d6_current_audio_needed = D6_MISSION_PASSED_AUDIO
		GOSUB d6_setup_audio_data

		TIMERA = 0
		GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK d6_task_status
		IF NOT d6_task_status = FINISHED_TASK
			GET_SEQUENCE_PROGRESS scplayer d6_seq_progress
		ELSE
			d6_seq_progress = 2
		ENDIF
		WHILE d6_seq_progress < 2
		OR d6_audio_index = 0
			WAIT 0
			IF TIMERA > 1000
				GOSUB d6_load_and_play_audio
			ENDIF
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK d6_task_status
			IF NOT d6_task_status = FINISHED_TASK
				GET_SEQUENCE_PROGRESS scplayer d6_seq_progress
			ELSE
				d6_seq_progress = 2
			ENDIF
		ENDWHILE

		IF NOT IS_CAR_DEAD d6_player_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS d6_player_car 0.4 0.51 0.65 d6_camera_x d6_camera_y d6_camera_z
			SET_FIXED_CAMERA_POSITION d6_camera_x d6_camera_y d6_camera_z 0.0 0.0 0.0
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS d6_player_car -0.13 0.22 0.54 d6_camera_x d6_camera_y d6_camera_z
			POINT_CAMERA_AT_POINT d6_camera_x d6_camera_y d6_camera_z JUMP_CUT
		ENDIF

		TASK_PLAY_ANIM scplayer carfone_loopB CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
		WHILE d6_audio_index <= 1
			WAIT 0
			GOSUB d6_load_and_play_audio
		ENDWHILE
		OPEN_SEQUENCE_TASK d6_car_chat_seq
			TASK_PLAY_ANIM -1 carfone_loopB_to_A CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK d6_car_chat_seq
		PERFORM_SEQUENCE_TASK scplayer d6_car_chat_seq
		CLEAR_SEQUENCE_TASK d6_car_chat_seq
		WHILE d6_audio_index <= 4
			WAIT 0
			GOSUB d6_load_and_play_audio
		ENDWHILE
		OPEN_SEQUENCE_TASK d6_car_chat_seq
			TASK_PLAY_ANIM -1 carfone_loopA_to_B CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
			TASK_PLAY_ANIM -1 carfone_loopB CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK d6_car_chat_seq
		PERFORM_SEQUENCE_TASK scplayer d6_car_chat_seq
		CLEAR_SEQUENCE_TASK d6_car_chat_seq
		WHILE d6_audio_index <= 5
			WAIT 0
			GOSUB d6_load_and_play_audio
			IF d6_audio_is_playing = 2
			AND NOT d6_started_talking = 1
				START_CHAR_FACIAL_TALK scplayer 4000
				d6_started_talking = 1
			ENDIF
		ENDWHILE

		LVAR_INT d6_cutscene_bike d6_cutscene_biker
		CREATE_CAR SANCHEZ -1565.89 -2748.32 48.21 d6_cutscene_bike
		SET_CAR_HEADING d6_cutscene_bike 58.42
		CREATE_CHAR_INSIDE_CAR d6_cutscene_bike PEDTYPE_MISSION1 DNB3 d6_cutscene_biker

		WAIT 1000

		IF NOT IS_CHAR_DEAD d6_cutscene_biker
		AND NOT IS_CAR_DEAD d6_cutscene_bike
			TASK_CAR_DRIVE_TO_COORD d6_cutscene_biker d6_cutscene_bike -1574.23 -2742.62 48.2 20.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH
		ENDIF

		SET_FIXED_CAMERA_POSITION -1570.65 -2748.39 48.35 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1571.99 -2745.26 48.29 JUMP_CUT

		IF NOT IS_CHAR_DEAD d6_cutscene_biker
			GET_SCRIPT_TASK_STATUS d6_cutscene_biker TASK_CAR_DRIVE_TO_COORD d6_task_status
			WHILE NOT d6_task_status = FINISHED_TASK
				WAIT 0
				IF NOT IS_CHAR_DEAD d6_cutscene_biker
					GET_SCRIPT_TASK_STATUS d6_cutscene_biker TASK_CAR_DRIVE_TO_COORD d6_task_status
				ELSE
					d6_task_status = FINISHED_TASK
				ENDIF
			ENDWHILE
		ENDIF

		IF NOT IS_CHAR_DEAD d6_cutscene_biker
			TASK_LOOK_AT_CHAR d6_cutscene_biker scplayer 3000
			WAIT 3000
		ENDIF

		IF NOT IS_CHAR_DEAD d6_cutscene_biker
			TASK_LOOK_AT_COORD d6_cutscene_biker -1572.73 -2747.12 48.2 -2
		ENDIF
		WHILE d6_audio_index < d6_total_audio_to_play
			WAIT 0
			IF NOT IS_CHAR_DEAD d6_cutscene_biker
				GOSUB d6_load_and_play_audio
				IF d6_audio_is_playing = 2
				AND NOT d6_started_talking = 1
					START_CHAR_FACIAL_TALK d6_cutscene_biker 10000
					d6_started_talking = 1
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_PRINTS
				d6_audio_index = d6_total_audio_to_play
			ENDIF
		ENDWHILE

		IF NOT IS_CHAR_DEAD d6_cutscene_biker
		AND NOT IS_CAR_DEAD d6_cutscene_bike
			STOP_CHAR_FACIAL_TALK d6_cutscene_biker
			CLEAR_LOOK_AT d6_cutscene_biker
			TASK_CAR_DRIVE_TO_COORD d6_cutscene_biker d6_cutscene_bike -1630.35 -2721.17 48.62 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
		ENDIF

		WAIT 3000

		d6_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF d6_cutscene_skipped = 1
		ENDIF

		CLEAR_CHAR_TASKS scplayer
		STOP_CHAR_FACIAL_TALK scplayer
		CLEAR_MISSION_AUDIO 3
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		DELETE_CHAR d6_cutscene_biker
		DELETE_CAR d6_cutscene_bike
		DELETE_OBJECT d6_mobile_phone
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		IF NOT IS_CAR_DEAD d6_player_car
			LOCK_CAR_DOORS d6_player_car CARLOCK_UNLOCKED
		ENDIF

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

	RETURN

// ***************************************************************
// Mission audio (bikers spotting decoy/phone call at destination)
// ***************************************************************

	d6_setup_audio_data:

		SWITCH d6_current_audio_needed

			CASE D6_MISSION_FAILED_AUDIO
				$d6_audio_text[0] = &FAR5_AB
				$d6_audio_text[1] = &FAR5_AA
				d6_audio_sound[0] = SOUND_FAR5_AB
				d6_audio_sound[1] = SOUND_FAR5_AA
				d6_total_audio_to_play = 2
				BREAK

			CASE D6_MISSION_PASSED_AUDIO
				$d6_audio_text[0] = &MTG02A
				$d6_audio_text[1] = &MTG02B
				$d6_audio_text[2] = &MTG02C
				$d6_audio_text[3] = &MTG02D
				$d6_audio_text[4] = &MTG02E
				$d6_audio_text[5] = &MTG02F
				$d6_audio_text[6] = &FAR5_AA
				d6_audio_sound[0] = SOUND_MTG02A
				d6_audio_sound[1] = SOUND_MTG02B
				d6_audio_sound[2] = SOUND_MTG02C
				d6_audio_sound[3] = SOUND_MTG02D
				d6_audio_sound[4] = SOUND_MTG02E
				d6_audio_sound[5] = SOUND_MTG02F
				d6_audio_sound[6] = SOUND_FAR5_AA
				d6_total_audio_to_play = 7
				BREAK

			DEFAULT
				BREAK

		ENDSWITCH

		d6_audio_is_playing = 0
		d6_audio_index = 0
		d6_started_talking = 0

	RETURN

	d6_load_and_play_audio:

		IF d6_audio_is_playing = 0
		OR d6_audio_is_playing = 1
			IF d6_audio_index < d6_total_audio_to_play
				IF TIMERB > 1000
					GOSUB d6_play_audio
				ENDIF
			ENDIF
		ENDIF

		IF d6_audio_is_playing = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				d6_audio_is_playing = 0
				d6_audio_index++
				d6_started_talking = 0
				CLEAR_PRINTS
				TIMERB = 0	
			ENDIF
		ENDIF

	RETURN

	d6_play_audio:

		IF d6_audio_is_playing = 0
			LOAD_MISSION_AUDIO 1 d6_audio_sound[d6_audio_index]
			d6_audio_is_playing = 1
		ENDIF
		IF d6_audio_is_playing = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW $d6_audio_text[d6_audio_index] 10000 1
				PLAY_MISSION_AUDIO 1
				d6_audio_is_playing = 2
			ENDIF
		ENDIF	
		
	RETURN

// ******************************** Mission driv6 failed **********************************

mission_driv6_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN


// ******************************** Mission driv6 passed **********************************

mission_driv6_passed:

flag_wuzi_mission_counter++
REGISTER_MISSION_PASSED ( FAR_5 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 8000 5000 1 //"Mission Passed!"
ADD_SCORE player1 8000
AWARD_PLAYER_MISSION_RESPECT 20
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
RETURN
		

// *********************************** Mission cleanup *************************************

mission_cleanup_driv6:

IF NOT IS_CAR_DEAD d6_player_car
	SET_CAR_PROOFS d6_player_car FALSE FALSE FALSE FALSE FALSE
	SET_CAR_CAN_BE_VISIBLY_DAMAGED d6_player_car TRUE
ENDIF
MARK_MODEL_AS_NO_LONGER_NEEDED RANCHER
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
REMOVE_ANIMATION CAR_CHAT
REMOVE_BLIP d6_player_car_blip
REMOVE_BLIP d6_countryside_blip
REMOVE_BLIP d6_next_checkpoint_blip
REMOVE_BLIP d6_destination_blip
// temp (stops crash when use debug to restart mission after scripted cut's played)
GOSUB d6_disengage_bikes
DELETE_CHECKPOINT d6_next_checkpoint
//IF NOT IS_CAR_DEAD d6_player_car
//	LOCK_CAR_DOORS d6_player_car CARLOCK_UNLOCKED
//ENDIF
CLEAR_ONSCREEN_COUNTER d6_door_health
CLEAR_SEQUENCE_TASK d6_car_chat_seq
SWITCH_CAR_GENERATOR car_gen_pine[3] 101
SWITCH_CAR_GENERATOR car_gen_pine[4] 101
SWITCH_CAR_GENERATOR car_gen_pine[5] 101
GET_GAME_TIMER timer_mobile_start
SET_CAR_DENSITY_MULTIPLIER 1.0
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


}
