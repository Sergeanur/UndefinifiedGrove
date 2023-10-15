MISSION_START
// *******************************************************************************************
// ********************************** Race Tournament ****************************************
// *******************************************************************************************

// Mission start stuff
{
GOSUB mission_start_cprace

IF HAS_DEATHARREST_BEEN_EXECUTED
	PRINT_NOW RACES25 5000 1//~r~You have been disqualified from the race
ELSE
	
	IF exit_mode = 0
	or exit_mode = 3
		DO_FADE 2000 FADE_OUT   
		SET_DEATHARREST_STATE OFF
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
	ENDIF
	if show_race_selection = FALSE
		if race_selection = BADLAND_RACE1
		or race_selection = BADLAND_RACE2
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
		endif
	endif
ENDIF

GOSUB mission_cleanup_cprace

MISSION_END

// *************************************** Mission Start *************************************

mission_start_cprace:

flag_player_on_mission = 1
disable_mod_garage = 1

SCRIPT_NAME cprace

WAIT 0

// fixes bug where game would crash if player fails race/mission while in an interior.
DISABLE_ALL_ENTRY_EXITS TRUE
load_mission_text racetor

LVAR_INT mission_blip mission_timer temp_int sequence_task sequence_taskB players_car
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 temp_float speed

lVAR_INT race_flag

IF show_race_selection = TRUE

	race_flag = MENU_SCREEN
	exit_mode = 0
	if menu_mode = LA_RACES
		lvar_int first_race	last_race
		first_race = 0
		last_race = 8
	endif
	if menu_mode = SF_RACES
		first_race = 9
		last_race = 14
	endif
	if menu_mode = LV_RACES
		first_race = 15
		last_race =  18
	endif
	if menu_mode = AIR_RACES
		first_race = 19
		last_race = 24
	endif
	if race_selection <	first_race
	or race_selection >	last_race
		race_selection = first_race
	endif
	LVAR_INT txd_in_memory
	txd_in_memory = 0
	GOSUB load_race_txd
ELSE
	race_flag = GET_COORDS_FOR_RACE
	lVAR_INT exit_mode
	if race_selection = NASCAR_RACE
	or race_selection = DIRTBIKE_STADIUM
		exit_mode = 3
	else
		exit_mode = 2
	endif
ENDIF

IF race_flag = RACE_START_GO

	lvar_int first_blip
	ADD_BLIP_FOR_COORD x y z first_blip
	CREATE_CHECKPOINT checkpoint_type x y z x y z 6.0 cp_marker
	
	create_racers_car:///////////////////////////////////////////////
		racer_cp_z[a] += 3.0
		GET_GROUND_Z_FOR_3D_COORD racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] racer_cp_z[a]
		if racer_cp_z[a] < 0.0
			if IS_THIS_MODEL_A_BOAT	racers_car_model[a]
				racer_cp_z[a] = 0.0
			endif
		endif
		CREATE_CAR racers_car_model[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] racers_car[a]
		SET_CAR_HEADING	racers_car[a] heading
		SET_VEHICLE_AREA_VISIBLE racers_car[a] interior_race
		LOCK_CAR_DOORS racers_car[a] CARLOCK_LOCKOUT_PLAYER_ONLY
		if race_selection = NASCAR_RACE
			set_car_health racers_car[a] 2200
		endif
		if race_selection = DIRTBIKE_STADIUM
			set_car_health racers_car[a] 10000
			SET_CAR_PROOFS racers_car[a] false false false true false
		endif
		if interior_race = 0
			SET_CAR_PROOFS racers_car[a] FALSE TRUE FALSE FALSE FALSE
			SET_CAR_STRONG racers_car[a] TRUE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER racers_car[a] TRUE
			if race_flag = CREATE_START_LINE
				while IS_CAR_WAITING_FOR_WORLD_COLLISION racers_car[a]
					wait 0
					if is_car_dead racers_car[a]
						if show_race_selection = false
							if race_selection = CESAR_RACE
								failed_cesar_race = 1
							endif
						endif
						return
					endif
				endwhile
			endif
			SET_LOAD_COLLISION_FOR_CAR_FLAG racers_car[a] FALSE
		else
			SET_LOAD_COLLISION_FOR_CAR_FLAG racers_car[a] TRUE
		endif
		if not racers_car_colour1[0] = -1
			CHANGE_CAR_COLOUR racers_car[a] racers_car_colour1[a] racers_car_colour2[a]
		endif
		IF IS_THIS_MODEL_A_BOAT racers_car_model[a]
			SET_CAR_STRAIGHT_LINE_DISTANCE racers_car[a] 40
			SET_BOAT_CRUISE_SPEED racers_car[a] 50.0
		ELSE
			IF IS_THIS_MODEL_A_HELI racers_car_model[a]
				FREEZE_CAR_POSITION	racers_car[a] TRUE
				SET_CAR_FORWARD_SPEED racers_car[a] 10.0
			ELSE
				IF IS_THIS_MODEL_A_PLANE racers_car_model[a]
					FREEZE_CAR_POSITION	racers_car[a] TRUE
					SET_CAR_FORWARD_SPEED racers_car[a] 10.0
				ELSE
					SET_CAR_WATERTIGHT racers_car[a] TRUE
					SET_CAN_BURST_CAR_TYRES racers_car[a] FALSE
					if interior_race = 0
						SET_UPSIDEDOWN_CAR_NOT_DAMAGED racers_car[a] TRUE
					endif
					SET_CAR_STRAIGHT_LINE_DISTANCE racers_car[a] 5
					SET_CAR_DRIVING_STYLE racers_car[a] 2
					SET_CAR_CRUISE_SPEED racers_car[a] 50.0
					CAR_SET_IDLE racers_car[a]
				ENDIF
			ENDIF
		ENDIF
	RETURN

ENDIF

VAR_FLOAT x_hud_pos[14] y_hud_pos[14] x_hud_scale[14] y_hud_scale[14]

x_hud_pos[0] = 70.3919 
y_hud_pos[0] = 154.6278 
x_hud_scale[0] = 0.6617 
y_hud_scale[0] = 2.5845 

y_hud_pos[1] = 203.8119 
x_hud_scale[1] = 0.7208 
y_hud_scale[1] = 2.5114 

y_hud_pos[2] = 228.9919 
x_hud_scale[2] = 0.7377 
y_hud_scale[2] = 2.2364 

y_hud_pos[3] = 279.6367 
y_hud_pos[5] = 333.4643 
y_hud_pos[6] = 358.6443 

y_hud_pos[7] = 25.1800 

LVAR_FLOAT race_distance[30]
race_distance[0] = 2357.2393//LA lowrider race 
race_distance[1] = 1321.1934//LA race 2
race_distance[2] = 3300.9675//LA race 3
race_distance[3] = 3646.7991//LA race 4
race_distance[4] = 3880.6770//LA race 5
race_distance[5] = 4216.2734//LA race 6
race_distance[6] = 8410.4580//LA race 7
race_distance[7] = 4817.6362//badlands race 1 
race_distance[8] = 4817.6362//badlands race 2
race_distance[9] = 3203.2295//badlands race 3 (san fran) sanchez
race_distance[10] = 3265.0281//badlands race 4 (san fran) buggy
race_distance[11] = 1901.2850//gokart
race_distance[12] = 2644.2966//sanfran race 1
race_distance[13] = 8372.6787//sanfran race	2
race_distance[14] = 10760.4961//sanfran race 3
race_distance[15] = 7806.1025//Sanfran to vegas
race_distance[16] = 4307.6499//desert race 1 
race_distance[17] = 4696.6606//desert race 2
race_distance[18] = 5960.2803//vegas ring road
race_distance[19] = 6826.6475 //plane race 1
race_distance[20] = 12299.1367//plane race 2
race_distance[21] = 17384.6172//plane race 3
race_distance[22] = 4922.4722//heli race 1
race_distance[23] = 4398.4995//heli race 2
race_distance[24] = 4970.7422//heli race 3
race_distance[25] = 0.0//nascar 8track 
race_distance[26] = 0.0//dirtbike stadium track 
race_distance[27] = 0.0 
race_distance[28] = 0.0 
race_distance[29] = 0.0

LVAR_TEXT_LABEL $race_name[30]
$race_name[0] = &RACE00//Lowrider Race
$race_name[1] = &RACE01//Little Loop
$race_name[2] = &RACE02//Backroad Wanderer
$race_name[3] = &RACE03//City Circuit
$race_name[4] = &RACE04//Vine Wood
$race_name[5] = &RACE05//Freeway
$race_name[6] = &RACE06//Into the Country
$race_name[7] = &RACE07//Badlands A
$race_name[8] = &RACE08//Badlands B
$race_name[9] = &RACE09//Dirtbike Danger
$race_name[10] = &RACE10//Bandito County
$race_name[11] = &RACE11//gokart
$race_name[12] = &RACE12//San Fierro Fastlane
$race_name[13] = &RACE13//San Fierro Hills
$race_name[14] = &RACE14//Country Endurance
$race_name[15] = &RACE15//San Fierro to Las Venturas
$race_name[16] = &RACE16//Dam Rider
$race_name[17] = &RACE17//Desert Tricks
$race_name[18] = &RACE18//Las Venturas Ringroad
$race_name[19] = &RACE19//Plane Race 1 - Rustler
$race_name[20] = &RACE20//Plane Race 2 - Stunt
$race_name[21] = &RACE21//Plane Race 3 - Hydra
$race_name[22] = &RACE22//Heli Race 1
$race_name[23] = &RACE23//Heli Race 2
$race_name[24] = &RACE24//Heli Race 3
//$race_name[25] = &RACE25//RACE 25
//$race_name[26] = &RACE26//RACE 26
//$race_name[27] = &RACE27//RACE 27
//$race_name[28] = &RACE28//RACE 28
//$race_name[29] = &RACE29//RACE 29

lvar_int race_stats_time[30]
race_stats_time[0] = RACETOUR_0_BEST_TIME
race_stats_time[1] = RACETOUR_1_BEST_TIME
race_stats_time[2] = RACETOUR_2_BEST_TIME
race_stats_time[3] = RACETOUR_3_BEST_TIME
race_stats_time[4] = RACETOUR_4_BEST_TIME
race_stats_time[5] = RACETOUR_5_BEST_TIME
race_stats_time[6] = RACETOUR_6_BEST_TIME
race_stats_time[7] = RACETOUR_7_BEST_TIME
race_stats_time[8] = RACETOUR_8_BEST_TIME
race_stats_time[9] = RACETOUR_9_BEST_TIME
race_stats_time[10] = RACETOUR_10_BEST_TIME
race_stats_time[11] = RACETOUR_11_BEST_TIME
race_stats_time[12] = RACETOUR_12_BEST_TIME
race_stats_time[13] = RACETOUR_13_BEST_TIME
race_stats_time[14] = RACETOUR_14_BEST_TIME
race_stats_time[15] = RACETOUR_15_BEST_TIME
race_stats_time[16] = RACETOUR_16_BEST_TIME
race_stats_time[17] = RACETOUR_17_BEST_TIME
race_stats_time[18] = RACETOUR_18_BEST_TIME
race_stats_time[19] = RACETOUR_19_BEST_TIME
race_stats_time[20] = RACETOUR_20_BEST_TIME
race_stats_time[21] = RACETOUR_21_BEST_TIME
race_stats_time[22] = RACETOUR_22_BEST_TIME
race_stats_time[23] = RACETOUR_23_BEST_TIME
race_stats_time[24] = RACETOUR_24_BEST_TIME
race_stats_time[NASCAR_RACE] = BEST_TIME_HOTRING
race_stats_time[DIRTBIKE_STADIUM] = BEST_TIME_DIRTTRACK
//race_stats_time[25] = BEST_TIME_HOTRING
//race_stats_time[26] = BEST_TIME_HOTRING
//race_stats_time[27] = BEST_TIME_HOTRING
//race_stats_time[28] = BEST_TIME_HOTRING
//race_stats_time[29] = BEST_TIME_HOTRING

lvar_int race_stats_position[30]
race_stats_position[0] = RACETOUR_0_BEST_POSITION
race_stats_position[1] = RACETOUR_1_BEST_POSITION
race_stats_position[2] = RACETOUR_2_BEST_POSITION
race_stats_position[3] = RACETOUR_3_BEST_POSITION
race_stats_position[4] = RACETOUR_4_BEST_POSITION
race_stats_position[5] = RACETOUR_5_BEST_POSITION
race_stats_position[6] = RACETOUR_6_BEST_POSITION
race_stats_position[7] = RACETOUR_7_BEST_POSITION
race_stats_position[8] = RACETOUR_8_BEST_POSITION
race_stats_position[9] = RACETOUR_9_BEST_POSITION
race_stats_position[10] = RACETOUR_10_BEST_POSITION
race_stats_position[11] = RACETOUR_11_BEST_POSITION
race_stats_position[12] = RACETOUR_12_BEST_POSITION
race_stats_position[13] = RACETOUR_13_BEST_POSITION
race_stats_position[14] = RACETOUR_14_BEST_POSITION
race_stats_position[15] = RACETOUR_15_BEST_POSITION
race_stats_position[16] = RACETOUR_16_BEST_POSITION
race_stats_position[17] = RACETOUR_17_BEST_POSITION
race_stats_position[18] = RACETOUR_18_BEST_POSITION
//race_stats_position[19] = RACETOUR_19_BEST_POSITION
//race_stats_position[20] = RACETOUR_20_BEST_POSITION
//race_stats_position[21] = RACETOUR_21_BEST_POSITION
//race_stats_position[22] = RACETOUR_22_BEST_POSITION
race_stats_position[NASCAR_RACE] = BEST_POSITION_HOTRING
race_stats_position[DIRTBIKE_STADIUM] = BEST_POSITION_DIRTTRACK
//race_stats_position[25] = RACETOUR_25_BEST_POSITION
//race_stats_position[26] = RACETOUR_26_BEST_POSITION
//race_stats_position[27] = RACETOUR_27_BEST_POSITION
//race_stats_position[28] = RACETOUR_28_BEST_POSITION
//race_stats_position[29] = RACETOUR_29_BEST_POSITION

get_char_coordinates scplayer x y z
SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION x y z

lvar_int checkpoint_type
checkpoint_type = checkpoint_tube 

USE_TEXT_COMMANDS TRUE
IF IS_JAPANESE_VERSION
	SET_MESSAGE_FORMATTING TRUE 345 370
ELSE
	SET_MESSAGE_FORMATTING TRUE 355 370
ENDIF
SET_PLAYER_CONTROL player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 TRUE
SET_ALL_CARS_CAN_BE_DAMAGED FALSE

DISPLAY_RADAR FALSE

LVAR_INT last_lap_time
last_lap_time = 0

LVAR_INT out_of_car_flag
out_of_car_flag = 0

lvar_int total_races
total_races = 25

lvar_int racers_car_colour1[6] racers_car_colour2[6]
racers_car_colour1[0] = -1 

LVAR_INT interior_race
interior_race = 0

LVAR_INT got_a_best_time got_a_best_position
got_a_best_time	= 0
got_a_best_position	= 0

lvar_int total_laps
total_laps = 1

lvar_int race_audio_flag race_audio_timer current_sound
race_audio_flag = 0
race_audio_timer = 0
current_sound =	0

///////////////////////////////////////////////////////////////////////
//GET RACE COORDS part1 !!!!! /////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_r
	race_debug = 0
	var_int coords
	coords = 0
	VIEW_INTEGER_VARIABLE coords coords
	SET_PLAYER_CONTROL player1 ON
	race_flag = CREATE_A_RACE
	LVAR_INT ps2_key_r_pressed
	ps2_key_r_pressed = 1
ELSE
	race_debug = -1
ENDIF
///////////////////////////////////////////////////////////////////////



// *************************************** MISSION CHUNKS **************************************
CONST_INT CREATE_A_RACE 0
CONST_INT MENU_SCREEN 1
CONST_INT GET_COORDS_FOR_RACE 2
CONST_INT INITIALISE_VARIABLES 3
CONST_INT LOAD_MODELS 4
CONST_INT LOAD_AREA 5
CONST_INT CREATE_START_LINE 6
CONST_INT RACE_START_3 7
CONST_INT RACE_START_2 8
CONST_INT RACE_START_1 9
CONST_INT RACE_START_GO 10
CONST_INT MAIN_RACE_LOOP 11
CONST_INT MAIN_RACE_LOOP_B 12
CONST_INT FINISH_RACE 13
CONST_INT RACE_ENDED 14

// *************************************** MISSION LOOP **************************************
mission_cprace_loop:  //////////////////// //////////// //////////////////////////////////////
// *************************************** MISSION LOOP **************************************

LVAR_INT position

WAIT 0
GET_GAME_TIMER game_timer

IF race_flag < FINISH_RACE
	position = 0
endif
a = 0

switch race_flag

///////////////////////////////////////////////////////////////////////
// GET RACE COORDS part2 !!!!! ////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//ENABLE RACE DEBUG = HOLD R AS WALKING INTO MISSION MARKER
//TURN ON RACE DEBUG = R
//TURN OFF RACE DEBUG = R
//DROP CHECKPOINT = CIRCLE
//TEST & OUTPUT RACE = SHIFT + R
case CREATE_A_RACE
	IF race_debug > -1
		IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_r
			IF ps2_key_r_pressed = 0
				IF race_debug = 0
					++ coords
					PRINT_NOW CHEATON 800 1
					race_debug = 1
				ELSE
					IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_shift
						//TEST RACE
						total_laps = 1
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "total_laps = 1"
						total_racers = 6
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "total_racers = 6"

						racer_model[0] = MALE01
						racer_model[1] = MALE01
						racer_model[2] = MALE01
						racer_model[3] = MALE01
						racer_model[4] = MALE01
						racer_model[5] = MALE01
						SAVE_NEWLINE_TO_DEBUG_FILE
						r = 0
						WHILE r < total_racers
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "racer_model["
							SAVE_INT_TO_DEBUG_FILE r
							SAVE_STRING_TO_DEBUG_FILE "] = MALE01"
							++ r
						ENDWHILE
						racers_car_model[0] = stored_car_model
						racers_car_model[1] = stored_car_model
						racers_car_model[2] = stored_car_model
						racers_car_model[3] = stored_car_model
						racers_car_model[4] = stored_car_model
						racers_car_model[5] = stored_car_model
						SAVE_NEWLINE_TO_DEBUG_FILE
						r = 0
						WHILE r < total_racers
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "racers_car_model["
							SAVE_INT_TO_DEBUG_FILE r
							SAVE_STRING_TO_DEBUG_FILE "] = "
							SAVE_INT_TO_DEBUG_FILE stored_car_model
							++ r
						ENDWHILE
						
						x = checkpoints_x[0] 
						y = checkpoints_y[0]
						z = checkpoints_z[0]
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_x[0] = "
						SAVE_FLOAT_TO_DEBUG_FILE x
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_y[0] = "
						SAVE_FLOAT_TO_DEBUG_FILE y
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_z[0] = "
						SAVE_FLOAT_TO_DEBUG_FILE z
											
						PRINT_NOW LABEL 1000 1 //TESTING NEW RACE!
						race_flag = INITIALISE_VARIABLES
						coords = 0
						race_debug = 0
					ELSE
						coords = 0
						PRINT_NOW CHEATOF 800 1
						race_debug = 0
					ENDIF
				ENDIF
				ps2_key_r_pressed = 1
			ENDIF
		ELSE
			ps2_key_r_pressed = 0
		ENDIF
	ENDIF

	IF race_debug = 1
		IF IS_PLAYER_PLAYING player1
			IF coords = 0
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					IF pad1_circle_pressed = 0
						++coords
						pad1_circle_pressed = 1
					ENDIF
				ELSE
					pad1_circle_pressed = 0
				ENDIF
			ENDIF
			IF coords > 0
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_car
					LVAR_INT stored_car_model
					GET_CAR_MODEL stored_car stored_car_model
					IF IS_THIS_MODEL_A_PLANE stored_car_model
					or IS_THIS_MODEL_A_HELI stored_car_model
						GET_DEBUG_CAMERA_COORDINATES x y z
					else
						GET_CHAR_COORDINATES scplayer x y z
					endif
					LVAR_FLOAT last_x last_y last_z
					GET_DISTANCE_BETWEEN_COORDS_3D x y z last_x last_y last_z distance
					LVAR_INT distance_int
					distance_int =# distance
					GOSUB setup_text_cprace
					SET_TEXT_DROPSHADOW 2 0 0 0 255
					SET_TEXT_SCALE 0.8 2.0
					DISPLAY_TEXT_WITH_NUMBER 30.0 145.0 RACES00 distance_int//Race ~1~:
					
					IF distance > 200.0
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						LVAR_INT pad1_circle_pressed
						IF pad1_circle_pressed = 0
							PRINT_NOW TEXXYZ4 800 1 // Writing coordinates to file...
							-- coords
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "checkpoints_x["
							SAVE_INT_TO_DEBUG_FILE coords
							SAVE_STRING_TO_DEBUG_FILE "] = "
							SAVE_FLOAT_TO_DEBUG_FILE x
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "checkpoints_y["
							SAVE_INT_TO_DEBUG_FILE coords
							SAVE_STRING_TO_DEBUG_FILE "] = "
							SAVE_FLOAT_TO_DEBUG_FILE y
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "checkpoints_z["
							SAVE_INT_TO_DEBUG_FILE coords
							SAVE_STRING_TO_DEBUG_FILE "] = "
							IF IS_THIS_MODEL_A_BOAT stored_car_model
							ELSE
								IF IS_THIS_MODEL_A_HELI stored_car_model
								ELSE
									IF IS_THIS_MODEL_A_PLANE stored_car_model
									ELSE
										GET_GROUND_Z_FOR_3D_COORD x y z z
									ENDIF
								ENDIF
							ENDIF
							SAVE_FLOAT_TO_DEBUG_FILE z
							SAVE_NEWLINE_TO_DEBUG_FILE
							checkpoints_x[coords] = x
							checkpoints_y[coords] = y
							checkpoints_z[coords] = z
							last_x = x
							last_y = y
							last_z = z
							++ coords
							total_checkpoints = coords
							++ coords
							pad1_circle_pressed = 1
						ENDIF
					ELSE
						pad1_circle_pressed = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
break
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////


case MENU_SCREEN
	DISPLAY_HUD FALSE
	DISPLAY_RADAR FALSE
	GET_CURRENT_LANGUAGE current_Language
	LVAR_INT message_box_size
	message_box_size = 200
	IF NOT current_Language = LANGUAGE_ENGLISH
		message_box_size += 50
	ENDIF

	IF IS_XBOX_VERSION
		message_box_size += 50
	ENDIF

	IF IS_JAPANESE_VERSION
		message_box_size += 25
	ENDIF

	SET_HELP_MESSAGE_BOX_SIZE message_box_size
	PRINT_HELP_FOREVER RACES33

	LVAR_INT pad1_leftstick_x pad1_leftstick_y pad1_rightstick_x pad1_rightstick_y
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 pad1_leftstick_x pad1_leftstick_y pad1_rightstick_x pad1_rightstick_y
	LVAR_INT pad1_up_pressed
	
	IF pad1_leftstick_y < -100
	OR pad1_leftstick_x > 100
	OR IS_BUTTON_PRESSED PAD1 DPADUP
	OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
		IF pad1_up_pressed = 0
			IF race_selection < last_race
				++ race_selection
			else
				race_selection = first_race
			ENDIF
			GOSUB load_race_txd
			pad1_up_pressed = 1
		ENDIF
	ELSE
		pad1_up_pressed = 0
	ENDIF
	LVAR_INT pad1_down_pressed
	
	IF pad1_leftstick_y > 100
	OR pad1_leftstick_x < -100
	OR IS_BUTTON_PRESSED PAD1 DPADDOWN
	OR IS_BUTTON_PRESSED PAD1 DPADLEFT
		IF pad1_down_pressed = 0
			IF race_selection > first_race
				-- race_selection
			else
				race_selection = last_race
			ENDIF
			GOSUB load_race_txd
			pad1_down_pressed = 1
		ENDIF
	ELSE
		pad1_down_pressed = 0
	ENDIF
	
	LVAR_INT pad1_triangle_pressed
	IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
		IF pad1_triangle_pressed = 0
			REMOVE_TEXTURE_DICTIONARY
			DISPLAY_HUD true
			DISPLAY_RADAR true
			CLEAR_HELP
			exit_mode = 1
			RETURN
			pad1_triangle_pressed = 1
		ENDIF
	ELSE
		pad1_triangle_pressed = 0
	ENDIF

	LVAR_INT pad1_cross_pressed
	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
		IF pad1_cross_pressed = 0
			IF race_selection > -1
				DO_FADE 500 FADE_OUT
				race_flag = GET_COORDS_FOR_RACE
			ENDIF
			pad1_cross_pressed = 1
		ENDIF
	ELSE
		pad1_cross_pressed = 0
	ENDIF
	
	DRAW_WINDOW 53.0 130.0 597.0 410.0 RACES_0 SWIRLS_BOTH

	GOSUB setup_text_cprace
	SET_TEXT_WRAPX 320.0
	SET_TEXT_SCALE x_hud_scale[0] y_hud_scale[0]
	DISPLAY_TEXT x_hud_pos[0] y_hud_pos[0] $race_name[race_selection] //Race ~1~:

	GOSUB setup_text_cprace
	SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
	DISPLAY_TEXT x_hud_pos[0] y_hud_pos[1] RACES36 //Track Length

	GOSUB setup_text_cprace
	set_text_colour 180 180 180 255
	SET_TEXT_SCALE x_hud_scale[2] y_hud_scale[2]
	y_hud_pos[2] = y_hud_pos[1]	+ y_hud_pos[7]
	//5,280 = feet in a mile
	//1609 = meters in a mile
	IF ARE_MEASUREMENTS_IN_METRES
		LVAR_FLOAT km temp_float2
		km = race_distance[race_selection] / 1000.0
		LVAR_INT left_dec_pl_digits	right_dec_pl_digits
		left_dec_pl_digits =# km
		temp_float =# left_dec_pl_digits
		temp_float2 = km - temp_float
		right_dec_pl_digits =# temp_float2
		IF right_dec_pl_digits < 10
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[2] RACES35 left_dec_pl_digits right_dec_pl_digits//~1~.0~1~ km
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[2] RACES34 left_dec_pl_digits right_dec_pl_digits//~1~.~1~ km
		ENDIF
	ELSE
		LVAR_FLOAT miles
		miles = race_distance[race_selection] /	1609.0
		left_dec_pl_digits =# miles
		temp_float =# left_dec_pl_digits
		temp_float2 = miles - temp_float
		temp_float2 *= 100.0
		right_dec_pl_digits =# temp_float2
		IF right_dec_pl_digits < 10
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[2] RACES47 left_dec_pl_digits right_dec_pl_digits//~1~.0~1~ miles
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[2] RACES46 left_dec_pl_digits right_dec_pl_digits//~1~.~1~ miles
		ENDIF
	ENDIF

	GOSUB setup_text_cprace
	SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
	DISPLAY_TEXT x_hud_pos[0] y_hud_pos[3] RACES38 //Best Time
	
	GOSUB setup_text_cprace
	set_text_colour 180 180 180 255
	SET_TEXT_SCALE x_hud_scale[2] y_hud_scale[2]
	y_hud_pos[4] = y_hud_pos[3]	+ y_hud_pos[7]
	
	IF cprace_best_times[race_selection] = 999999999
		DISPLAY_TEXT x_hud_pos[0] y_hud_pos[4] RACES45 //NA
	ELSE
		mins = cprace_best_times[race_selection] / 60
		temp_int = mins * 60
		seconds = cprace_best_times[race_selection] - temp_int
		
		IF seconds < 10
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[4] RACES40 mins seconds //~1~:0~1~
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[0] y_hud_pos[4] RACES39 mins seconds //~1~:~1~
		ENDIF
	ENDIF
	
	if not menu_mode = AIR_RACES
		GOSUB setup_text_cprace
		SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]

		DISPLAY_TEXT x_hud_pos[0] y_hud_pos[5] RACES37 //Best Result
		
		GOSUB setup_text_cprace
		set_text_colour 180 180 180 255
		SET_TEXT_SCALE x_hud_scale[2] y_hud_scale[2]
		y_hud_pos[6] = y_hud_pos[5]	+ y_hud_pos[7]
		IF cprace_best_position[race_selection] = 1
			DISPLAY_TEXT x_hud_pos[0] y_hud_pos[6] RACES41//1ST
		ELSE
			IF cprace_best_position[race_selection] = 2
				DISPLAY_TEXT x_hud_pos[0] y_hud_pos[6] RACES42//2ND
			ELSE
				IF cprace_best_position[race_selection] = 3
					DISPLAY_TEXT x_hud_pos[0] y_hud_pos[6] RACES43//3RD
				ELSE
					IF cprace_best_position[race_selection] > 3
					AND	cprace_best_position[race_selection] < 100
						DISPLAY_TEXT_WITH_NUMBER x_hud_pos[0] y_hud_pos[6] RACES44 cprace_best_position[race_selection]//~1~TH
					ELSE
						DISPLAY_TEXT x_hud_pos[0] y_hud_pos[6] RACES45//NA
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	temp_int = race_selection + 1
	DRAW_SPRITE	temp_int 454.98 268.18 256.0 256.0 180 180 180 255  // Race preview thumbnail.
break

case GET_COORDS_FOR_RACE
	IF NOT GET_FADING_STATUS
		SET_HELP_MESSAGE_BOX_SIZE 200
		REMOVE_TEXTURE_DICTIONARY
		CLEAR_HELP
		
		gosub race_coords
		
		DISPLAY_CAR_NAMES FALSE
		DISPLAY_HUD TRUE

		if interior_race = 0
			DISPLAY_RADAR TRUE
		else
			SET_PLAYER_IS_IN_STADIUM TRUE
			DISPLAY_ZONE_NAMES false
			HIDE_ALL_FRONTEND_BLIPS true
			SET_EXTRA_COLOURS 2 FALSE
		endif
		disable_all_cranes = 1

		IF IS_XBOX_VERSION
			DISPLAY_ZONE_NAMES false
		ENDIF

		if got_race_made_progress[race_selection] = 0
	        if race_selection = CESAR_RACE
	        and show_race_selection = FALSE
			else
				register_mission_given
			endif
		endif

		if race_selection = 23
			switch_car_generator gen_car_flying[1] 0
		endif

		race_flag = INITIALISE_VARIABLES
	ENDIF
break

case INITIALISE_VARIABLES
	LVAR_INT total_checkpoints_minus_1 total_checkpoints
	total_checkpoints_minus_1 = total_checkpoints - 1
	LVAR_INT total_laps_minus_1
	total_laps_minus_1 = total_laps - 1
	LVAR_INT r a c e
	r = 0
	a = 0
	c = 0
	e = 0
	LVAR_INT total_racers
	WHILE a < total_racers
		LVAR_INT cpcounter[16]
		cpcounter[a] = 0
		LVAR_INT lap_cpcounter[16]
		lap_cpcounter[a] = 0
		lvar_int racer_finished_race[16]
		racer_finished_race[a] = 0
		lvar_int racer_out_car_timer[16]
		racer_out_car_timer[a] = 0
		++ a
	ENDWHILE
	a = 0
	LVAR_INT racer_decisions
	racer_decisions = -1
	race_flag = LOAD_MODELS
	if checkpoint_type = CHECKPOINT_TORUS
		if race_selection = 22
		or race_selection = 23
		or race_selection = 24
			lvar_float loc_size_x loc_size_y loc_size_z
			loc_size_x = 12.0
			loc_size_y = 12.0
			loc_size_z = 12.0
			lvar_float checkpoint_size
			checkpoint_size = 6.0
		else
			loc_size_x = 25.0
			loc_size_y = 25.0
			loc_size_z = 25.0
			checkpoint_size = 12.0
		endif
	else
		loc_size_x = 12.0
		loc_size_y = 12.0
		loc_size_z = 7.0
		checkpoint_size = 6.0
	endif
break

case LOAD_MODELS

	r = 0
	while r < total_racers
		if racer_model[r] = SPECIAL01
			if race_selection = CESAR_RACE
				LOAD_SPECIAL_CHARACTER 1 cesar
				LOAD_SPECIAL_CHARACTER 2 kendl
			endif
			if race_selection = BADLAND_RACE1
				LOAD_SPECIAL_CHARACTER 1 wuzimu
			endif
			if race_selection = BADLAND_RACE2
				LOAD_SPECIAL_CHARACTER 1 wuzimu//claude
				LOAD_SPECIAL_CHARACTER 2 cat
			endif
		else
			REQUEST_MODEL racer_model[r]
		endif
		//if race_selection = BADLAND_RACE1
		if race_selection = BADLAND_RACE2
			REQUEST_VEHICLE_MOD wheel_or1
		endif
		REQUEST_MODEL racers_car_model[r]
		++ r
	endwhile
	REQUEST_ANIMATION CAR
	REQUEST_ANIMATION RIOT

	load_all_models_now
	
	lvar_int all_models_loaded
	all_models_loaded = false
	while all_models_loaded = false
		wait 0

		all_models_loaded = true

		r = 0
		while r < total_racers
			if racer_model[r] = SPECIAL01
				if not has_special_character_loaded 1
					if race_selection = CESAR_RACE
						load_special_character 1 cesar
						all_models_loaded = false
					endif
					if race_selection = BADLAND_RACE1
						load_special_character 1 wuzimu
						all_models_loaded = false
					endif
					if race_selection = BADLAND_RACE2
						load_special_character 1 wuzimu//claude
						all_models_loaded = false
					endif
				endif
				if race_selection = CESAR_RACE
					if not has_special_character_loaded 2
						load_special_character 2 kendl
						all_models_loaded = false
					endif
				endif
				if race_selection = BADLAND_RACE2
					if not has_special_character_loaded 2
						load_special_character 2 cat
						all_models_loaded = false
					endif
				endif
			else
				if not has_model_loaded racer_model[r]
					request_model racer_model[r]
					all_models_loaded = false
				endif
			endif
			//if race_selection = BADLAND_RACE1
			if race_selection = BADLAND_RACE2
				if not HAS_VEHICLE_MOD_LOADED wheel_or1
					REQUEST_VEHICLE_MOD wheel_or1
					all_models_loaded = false
				endif
			endif
			if not has_model_loaded racers_car_model[r]
				request_model racer_model[r]
				request_model racers_car_model[r]
				all_models_loaded = false
			endif
			r++
		endwhile
	endwhile
	race_flag = LOAD_AREA
break

case LOAD_AREA
	set_car_density_multiplier 0.0
	CLEAR_AREA checkpoints_x[0] checkpoints_y[0] checkpoints_z[0] 30.0 0
	if show_race_selection = true
		clear_char_tasks_immediately scplayer
	endif
	SET_CHAR_COORDINATES scplayer checkpoints_x[0] checkpoints_y[0] checkpoints_z[0] 
	SET_CHAR_AREA_VISIBLE scplayer interior_race
	SET_AREA_VISIBLE interior_race
	REQUEST_COLLISION checkpoints_x[0] checkpoints_y[0]
	RESTORE_CAMERA_JUMPCUT
	LOAD_SCENE checkpoints_x[0] checkpoints_y[0] checkpoints_z[0]
	SET_WEATHER_TO_APPROPRIATE_TYPE_NOW
	race_flag = CREATE_START_LINE
break

case CREATE_START_LINE
	IF HAS_ANIMATION_LOADED CAR
	AND HAS_ANIMATION_LOADED RIOT
		
		CREATE_CAR racers_car_model[0] checkpoints_x[0] checkpoints_y[0] checkpoints_z[0] stored_car
		TURN_CAR_TO_FACE_COORD stored_car checkpoints_x[1] checkpoints_y[1]// MUST PLACE checkpoint[1] STRAIGHT AHEAD FROM START checkpoint[0]
		temp_int = total_racers - 1
		a = 0
		WHILE a < total_racers
			if total_racers > 1
				LVAR_FLOAT car_size_x car_size_y half_size_x half_size_y
				GET_MODEL_DIMENSIONS racers_car_model[0] half_size_x half_size_y z car_size_x car_size_y z
				half_size_x *= -1.0
				car_size_x += half_size_x
				IF car_size_x < 2.0
					car_size_x = 2.0
				ENDIF
				half_size_x = car_size_x * -1.0
				
				half_size_y *= -1.0
				car_size_y += half_size_y
				
				c = a / 2
				c *= 2
				IF c = a//IS a AN EVEN NUMBER?
					x = car_size_x
					
					temp_float =# a
					temp_float *= car_size_y
					temp_float *= -1.0
					y = temp_float

				ELSE
					x = half_size_x
					
					e = a - 1
					temp_float =# e
					temp_float *= car_size_y
					temp_float *= -1.0
					y = temp_float

				ENDIF
				if is_car_dead stored_car
					if show_race_selection = false
						if race_selection = CESAR_RACE
							failed_cesar_race = 1
						endif
					endif
					return
				endif
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car x y 0.0 x y z
				GET_CAR_HEADING	stored_car heading
				LVAR_FLOAT racer_cp_x[16] racer_cp_y[16] racer_cp_z[16]
				racer_cp_x[a] = x
				racer_cp_y[a] = y
			else
				GET_CAR_HEADING	stored_car heading
				x = racer_cp_x[a]
				y = racer_cp_y[a]
				racer_cp_x[a] = checkpoints_x[0]
				racer_cp_y[a] = checkpoints_y[0]
				z = checkpoints_z[0]
			endif
			IF NOT IS_THIS_MODEL_A_BOAT racers_car_model[a]
				IF NOT IS_THIS_MODEL_A_PLANE racers_car_model[a]
					IF NOT IS_THIS_MODEL_A_HELI racers_car_model[a]
						GET_GROUND_Z_FOR_3D_COORD x y z z
					ENDIF
				ENDIF
			ENDIF
			racer_cp_z[a] = z
			
			IF a < temp_int
				LVAR_INT racers_car[16]
				GOSUB create_racers_car
				LVAR_INT racer[16]
				CREATE_CHAR_INSIDE_CAR racers_car[a] PEDTYPE_CIVMALE racer_model[a] racer[a]	
				if racer_model[a] = SPECIAL01
					if race_selection = CESAR_RACE
					or race_selection = BADLAND_RACE2
						lvar_int race_passenger
						CREATE_CHAR_AS_PASSENGER racers_car[a] PEDTYPE_CIVFEMALE SPECIAL02 0 race_passenger
					endif
					set_car_proofs racers_car[a] true true true false true
				endif
				if interior_race > 0
					if racer_decisions = -1
						COPY_CHAR_DECISION_MAKER -1 racer_decisions
						CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE racer_decisions EVENT_VEHICLE_ON_FIRE
					endif
					SET_CHAR_DECISION_MAKER racer[a] racer_decisions
				endif
				SET_CHAR_AREA_VISIBLE racer[a] interior_race
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE racer[a] FALSE
				SET_CHAR_SUFFERS_CRITICAL_HITS racer[a] FALSE
				SET_CHAR_NEVER_TARGETTED racer[a] TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER	racer[a] TRUE
				SET_CHAR_CANT_BE_DRAGGED_OUT racer[a] FALSE
				SET_CHAR_DROWNS_IN_WATER racer[a] FALSE
				SET_CHAR_HEALTH	racer[a] 500
				LVAR_INT racer_blip[16]
				if interior_race = 0
					ADD_BLIP_FOR_CHAR racer[a] racer_blip[a]
				endif
				CHANGE_BLIP_DISPLAY	racer_blip[a] MARKER_ONLY
			ELSE
				IF NOT IS_THIS_MODEL_A_BOAT racers_car_model[a]
					IF NOT IS_THIS_MODEL_A_PLANE racers_car_model[a]
						IF NOT IS_THIS_MODEL_A_HELI racers_car_model[a]
							IF interior_race = 0
								LVAR_INT rt_empty_dec
								LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY rt_empty_dec 
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -4.0435 6.5755 -0.8 x y z
								get_ground_z_for_3d_coord x y z z
								LVAR_INT flag_girl
								CREATE_RANDOM_CHAR x y z flag_girl
								SET_CHAR_KEEP_TASK flag_girl TRUE
								SET_CHAR_DECISION_MAKER flag_girl rt_empty_dec
								TASK_TURN_CHAR_TO_FACE_COORD flag_girl checkpoints_x[0] checkpoints_y[0] checkpoints_z[0]
								LVAR_INT flag_object
								CREATE_OBJECT_NO_OFFSET	kmb_goflag x y z flag_object
								TASK_PICK_UP_OBJECT flag_girl flag_object 0.062 0.027 -0.15 PED_HANDL HOLD_ORIENTATE_BONE_FULL FLAG_DROP CAR 0
								
								LVAR_INT spectator[11]
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -5.6392 2.9435 -0.8 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[0]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -6.0071 -2.0778 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[1]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -7.6039 -5.0807 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[2]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -7.0724 -6.4057 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[3]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car -5.9988 -13.553 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[4]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 6.0730 -15.2059 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[5]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 7.2639 -12.9933 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[6]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 5.8429 -7.7069 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[7]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 8.4135 -3.7971 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[8]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 6.2507 0.3634 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[9]

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_car 8.6397 2.4190 2.5 x y z
								get_ground_z_for_3d_coord x y z z
								CREATE_RANDOM_CHAR x y z spectator[10]

								r = 0
								WHILE r < 11
									SET_CHAR_DECISION_MAKER spectator[r] rt_empty_dec
									SET_CHAR_KEEP_TASK spectator[r] TRUE
									
									OPEN_SEQUENCE_TASK sequence_taskB
										gosub generate_random_anims
										SET_SEQUENCE_TO_REPEAT sequence_taskB true
									CLOSE_SEQUENCE_TASK sequence_taskB
									
									OPEN_SEQUENCE_TASK sequence_task
										TASK_TURN_CHAR_TO_FACE_COORD -1 checkpoints_x[0] checkpoints_y[0] checkpoints_z[0]
										PERFORM_SEQUENCE_TASK -1 sequence_taskB
									CLOSE_SEQUENCE_TASK sequence_task
									PERFORM_SEQUENCE_TASK spectator[r] sequence_task
									CLEAR_SEQUENCE_TASK	sequence_taskB
									CLEAR_SEQUENCE_TASK sequence_task
									++ r
								ENDWHILE
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				DELETE_CAR stored_car
				racer[a] = scplayer
				LVAR_INT playaz
				playaz = a
				LVAR_INT stored_car
				racer_cp_z[playaz] += 3.0
				GET_GROUND_Z_FOR_3D_COORD racer_cp_x[playaz] racer_cp_y[playaz] racer_cp_z[playaz] racer_cp_z[playaz]
				if racer_cp_z[playaz] < 0.0
					if IS_THIS_MODEL_A_BOAT	racers_car_model[playaz]
						racer_cp_z[playaz] = 0.0
					endif
				endif
				IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CAR_CHAR_IS_USING scplayer racers_car[playaz]
					set_car_coordinates racers_car[playaz] racer_cp_x[playaz] racer_cp_y[playaz] racer_cp_z[playaz]
				else
					CREATE_CAR racers_car_model[playaz] racer_cp_x[playaz] racer_cp_y[playaz] racer_cp_z[playaz] racers_car[playaz]
					//if race_selection = BADLAND_RACE1
					if race_selection = BADLAND_RACE2
						ADD_VEHICLE_MOD racers_car[playaz] wheel_or1 temp_int
					endif
					if not racers_car_colour1[0] = -1
						CHANGE_CAR_COLOUR racers_car[playaz] racers_car_colour1[playaz] racers_car_colour2[playaz]
					endif
					WARP_CHAR_INTO_CAR scplayer	racers_car[playaz]
				ENDIf
				SET_VEHICLE_AREA_VISIBLE racers_car[playaz] interior_race
				SET_CAR_HEADING	racers_car[playaz] heading//DEBUG!!!

				IF IS_THIS_MODEL_A_PLANE racers_car_model[playaz]
				OR IS_THIS_MODEL_A_HELI racers_car_model[playaz]
					FREEZE_CAR_POSITION	racers_car[playaz] TRUE
					SET_CAR_FORWARD_SPEED racers_car[playaz] 10.0
				ENDIF

				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				DO_FADE 500 FADE_IN
			ENDIF
			++ a
		ENDWHILE
		SET_PLAYER_CONTROL player1 OFF
		SET_CAMERA_BEHIND_PLAYER
		restore_camera_jumpcut
		race_flag = RACE_START_3
	ELSE
		REQUEST_ANIMATION CAR
		REQUEST_ANIMATION RIOT
	ENDIF
break

case RACE_START_3
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE
	SET_ALL_CARS_CAN_BE_DAMAGED FALSE
	PRINT_BIG RACES_4 1100 4
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
	VAR_INT races_timer
	races_timer = game_timer + 999
	race_flag = RACE_START_2
break

case RACE_START_2
	IF races_timer < game_timer
		PRINT_BIG RACES_5 1100 4
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
		races_timer = game_timer + 999
		race_flag = RACE_START_1
	ENDIF
break

case RACE_START_1
	IF races_timer < game_timer
		PRINT_BIG RACES_6 1100 4
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
		races_timer = game_timer + 999
		race_flag = RACE_START_GO
	ENDIF
break

case RACE_START_GO
	IF races_timer < game_timer

		PRINT_BIG RACES_7 800 4
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_GO
		SET_PLAYER_CONTROL player1 ON
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE
		
		a = 0
		WHILE a < 11
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator[a]
			a ++
		ENDWHILE

		MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl
		remove_animation car
		
		a = 0
		WHILE a < total_racers
			IF NOT IS_CAR_DEAD racers_car[a]
				SET_CAR_ONLY_DAMAGED_BY_PLAYER racers_car[a] FALSE
				IF NOT IS_THIS_MODEL_A_BOAT racers_car_model[a]
					IF NOT IS_THIS_MODEL_A_PLANE racers_car_model[a]
						IF NOT IS_THIS_MODEL_A_HELI racers_car_model[a]
							IF NOT a = playaz
								if interior_race = 0
									ADD_STUCK_CAR_CHECK_WITH_WARP racers_car[a] 3.0 4000 TRUE TRUE TRUE -1
								endif
							ENDIF
						ELSE
							FREEZE_CAR_POSITION	racers_car[a] FALSE
						ENDIF
					ELSE
						FREEZE_CAR_POSITION	racers_car[a] FALSE
					ENDIF
				ENDIF
			ENDIF
			++ a
		ENDWHILE

		SET_CAR_DENSITY_MULTIPLIER 1.0

		x_hud_pos[0] = 582.7242 
		y_hud_pos[0] = 356.2812 
		x_hud_scale[0] = 52.2072 
		y_hud_scale[0] = 52.2072 

		x_hud_pos[1] = 584.0833 
		y_hud_pos[1] = 329.3647 
		x_hud_scale[1] = 0.4943 
		y_hud_scale[1] = 2.0970 

		x_hud_pos[2] = 570.6411 
		y_hud_pos[2] = 323.5937 
		IF IS_JAPANESE_VERSION
			x_hud_scale[2] = 0.8 
		ELSE
			x_hud_scale[2] = 0.9127 
		ENDIF
		y_hud_scale[2] = 4.8106 

		x_hud_pos[3] = 585.9410 
		y_hud_pos[3] = 345.6341 
		IF IS_JAPANESE_VERSION
			x_hud_scale[3] = 0.37 
		ELSE
			x_hud_scale[3] = 0.4197 
		ENDIF
		y_hud_scale[3] = 1.8511 

		x_hud_pos[4] = 582.7242 
		y_hud_pos[4] = 359.0323 
		x_hud_scale[4] = 0.4993 
		y_hud_scale[4] = 2.6370 

		x_hud_pos[5] = 582.7242 
		y_hud_pos[5] = 356.2812 
		x_hud_scale[5] = 56.2072 
		y_hud_scale[5] = 56.2072 

		x_hud_pos[6] = 582.7242 
		y_hud_pos[6] = 356.2812 
		x_hud_scale[6] = 58.2072 
		y_hud_scale[6] = 58.2072 

		x_hud_pos[7] = 582.7242 
		y_hud_pos[7] = 400.7518 
		x_hud_scale[7] = 58.2072 
		y_hud_scale[7] = 32.7789 

		x_hud_pos[8] = 582.7242 
		y_hud_pos[8] = 400.2518 
		x_hud_scale[8] = 56.2072 
		y_hud_scale[8] = 31.7789 

		x_hud_pos[9] = 582.7242 
		y_hud_pos[9] = 399.2518 
		x_hud_scale[9] = 52.2072 
		y_hud_scale[9] = 29.7789 

		x_hud_pos[10] = 560.4862 
		y_hud_pos[10] = 384.5027 
		x_hud_scale[10] = 0.5 
		y_hud_scale[10] = 1.5393 

		races_timer = game_timer    // Really confusing but when we are using an onscreen timer, races_timer automatically counts up, and is stored as the current time. 
		last_lap_time = game_timer  // Else, the current time is the current time - the start time :(
		if total_racers = 1
			races_timer = 0
			DISPLAY_ONSCREEN_TIMER_WITH_STRING races_timer timer_up TIMER
		endif
		
		CLEAR_THIS_PRINT_BIG_NOW 2
		
		race_flag = MAIN_RACE_LOOP

	ENDIF
break

case MAIN_RACE_LOOP
case MAIN_RACE_LOOP_B

	WHILE a < total_racers
	
		IF NOT IS_CHAR_DEAD	racer[a]
			//WORK OUT RACERS POSITIONS
			IF race_flag < FINISH_RACE
				IF NOT scplayer = racer[a]
					IF lap_cpcounter[playaz] < lap_cpcounter[a]
						++ position
					ELSE
						IF lap_cpcounter[playaz] = lap_cpcounter[a]
							IF cpcounter[playaz] < cpcounter[a]
								++ position
							ELSE
								IF cpcounter[a] > total_checkpoints_minus_1
									++ position
								ELSE
									IF cpcounter[playaz] = cpcounter[a]
										GET_CHAR_COORDINATES racer[a] x y z
										LVAR_FLOAT racer_distance_from_cp
										GET_DISTANCE_BETWEEN_COORDS_3D x y z racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] racer_distance_from_cp
										GET_CHAR_COORDINATES scplayer x y z
										LVAR_FLOAT player_distance_from_cp
										GET_DISTANCE_BETWEEN_COORDS_3D x y z racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] player_distance_from_cp
										IF player_distance_from_cp > racer_distance_from_cp
											++ position
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF race_flag = MAIN_RACE_LOOP_B
				IF scplayer = racer[a]
					IF NOT IS_CAR_DEAD racers_car[playaz]
						//OUT OF CAR TIMER FOR PLAYER
						IF IS_CHAR_IN_CAR scplayer racers_car[playaz]
							IF out_of_car_flag = 1
								CLEAR_THIS_PRINT RACES21
								REMOVE_BLIP	first_blip
								racer_cp_z[a] -= 1000.0
								if interior_race = 0
									c = 0
									temp_int = total_racers - 1
									WHILE c < temp_int
										IF NOT IS_CHAR_DEAD	racer[c]
											ADD_BLIP_FOR_CHAR racer[c] racer_blip[c]
											CHANGE_BLIP_DISPLAY	racer_blip[c] MARKER_ONLY
										ENDIF
										++ c
									ENDWHILE
									ADD_BLIP_FOR_COORD racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] first_blip
									CHANGE_BLIP_COLOUR first_blip RED
									CHANGE_BLIP_DISPLAY first_blip BLIP_ONLY
									CHANGE_BLIP_SCALE first_blip 3
									IF cpcounter[a] = total_checkpoints_minus_1
									AND lap_cpcounter[a] = total_laps_minus_1
										if checkpoint_type = checkpoint_torus
											temp_int = cpcounter[a]
											-- temp_int
											IF temp_int < 0
												temp_int = 0
											ENDIF
											CREATE_CHECKPOINT checkpoint_type racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] checkpoints_x[temp_int]	checkpoints_y[temp_int]	checkpoints_z[temp_int] checkpoint_size cp_marker
										else
											CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 0.0 0.0 6.0 cp_marker
										endif
									ELSE
										temp_int = cpcounter[a]
										++ temp_int
										IF cpcounter[a] = total_checkpoints_minus_1
											temp_int = 1
										ENDIF
										CREATE_CHECKPOINT checkpoint_type racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] checkpoints_x[temp_int]	checkpoints_y[temp_int]	checkpoints_z[temp_int] checkpoint_size cp_marker
									ENDIF
								endif
								out_of_car_flag = 0
							ENDIF
						ELSE
							IF out_of_car_flag = 0
								DELETE_CHECKPOINT cp_marker
								REMOVE_BLIP	first_blip
								c = 0
								temp_int = total_racers - 1
								WHILE c < temp_int
									REMOVE_BLIP	racer_blip[c]
									++ c
								ENDWHILE
								ADD_BLIP_FOR_CAR racers_car[playaz] first_blip
								SET_BLIP_AS_FRIENDLY first_blip TRUE
								racer_cp_z[a] += 1000.0
								LVAR_INT out_of_car_timer
								out_of_car_timer = game_timer + 25400
								out_of_car_flag = 1
							ENDIF
							seconds = out_of_car_timer - game_timer
							seconds /= 1000
							IF seconds < 1
								seconds = 0
							ENDIF
							IF out_of_car_timer < game_timer
								PRINT_NOW RACES20 5000 1//~r~You have been disqualified for leaving your car.
								if show_race_selection = false
									PRINT_BIG M_FAIL 3000 1
								endif
								if show_race_selection = false
									if race_selection = CESAR_RACE
										failed_cesar_race = 1
									endif
								endif
								RETURN
							ENDIF
							if seconds = 1
								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
								PRINT_WITH_NUMBER_NOW RACE_21 seconds 200 1	//You have ~1~ second to return to your car.
							else
								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
								PRINT_WITH_NUMBER_NOW RACES21 seconds 200 1	//You have ~1~ seconds to return to your car.
							endif
						ENDIF
					ELSE
						IF IS_CAR_IN_WATER racers_car[playaz]
							IF IS_CHAR_IN_ANY_CAR scplayer
								STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_car
								IF NOT stored_car =	racers_car[playaz]
									PRINT_NOW RACES24 5000 1//~r~You have been disqualified for destroying your car.
									if show_race_selection = false
										PRINT_BIG M_FAIL 3000 1
									endif
									if show_race_selection = false
										if race_selection = CESAR_RACE
											failed_cesar_race = 1
										endif
									endif
									RETURN
								ENDIF
							ELSE
								PRINT_NOW RACES24 5000 1//~r~You have been disqualified for destroying your car.
								if show_race_selection = false
									PRINT_BIG M_FAIL 3000 1
								endif
								if show_race_selection = false
									if race_selection = CESAR_RACE
										failed_cesar_race = 1
									endif
								endif
								RETURN
							ENDIF
						ELSE
							PRINT_NOW RACES24 5000 1//~r~You have been disqualified for destroying your car.
							if show_race_selection = false
								PRINT_BIG M_FAIL 3000 1
							endif
							if show_race_selection = false
								if race_selection = CESAR_RACE
									failed_cesar_race = 1
								endif
							endif
							RETURN
						ENDIF
					ENDIF
				ELSE
					IF cpcounter[a] < total_checkpoints
						//SPEED UP OR SLOW DOWN RACERS IF THEY ARE 4 CHECKPOINTS AHEAD OF PLAYER
						IF IS_CHAR_IN_ANY_CAR racer[a]
							if interior_race = 0
								temp_int = cpcounter[playaz] + 4
								STORE_CAR_CHAR_IS_IN_NO_SAVE racer[a] stored_car

								//if this car is ahead of the player by 3 checkpoints, reduce speed
								IF cpcounter[a] > temp_int
								//or locate_char_in_car_2d racer[a] slowdown_x slow_down_y 15.0 15.0 0
									SET_CAR_CRUISE_SPEED stored_car	25.0
								ELSE
									IF race_selection = BADLAND_RACE1
									OR race_selection = BADLAND_RACE2
									OR race_selection = CESAR_RACE
										SET_CAR_CRUISE_SPEED stored_car	35.0
									ELSE
										SET_CAR_CRUISE_SPEED stored_car	50.0
									ENDIF
								ENDIF

								//some special conditional speed for this race
								if race_selection = BADLAND_RACE2
									if locate_car_2d stored_car -882.9833 -47.9468 24.6100 25.6400 0
									or locate_car_2d stored_car -843.0954 -141.9169 24.5900 -29.2400 0
										set_car_cruise_speed stored_car 30.0
									endif
								endif
							
							ENDIF
						else
							if race_selection = NASCAR_RACE
								//respawn
								goto respawn_racer
							else
								if racer_out_car_timer[a] = 0
									racer_out_car_timer[a] = game_timer + 8000
								endif
								if racer_out_car_timer[a] < game_timer
									if not cpcounter[a] = cpcounter[playaz]
										IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer racer[a] 30.0 30.0 0
											IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer racer_cp_x[a] racer_cp_y[a] 30.0 30.0 0
												IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 4.0 4.0 3.0
													IF NOT IS_CHAR_ON_SCREEN racer[a]
														IF NOT IS_POINT_ON_SCREEN racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 4.0
															MARK_CAR_AS_NO_LONGER_NEEDED racers_car[a]
															GOSUB create_racers_car
															WARP_CHAR_INTO_CAR racer[a]	racers_car[a]
															temp_int = cpcounter[a] + 1
															IF temp_int < total_checkpoints
																TURN_CAR_TO_FACE_COORD racers_car[a] checkpoints_x[temp_int] checkpoints_y[temp_int]
																set_car_forward_speed racers_car[a] 20.0
															ENDIF
															racer_out_car_timer[a] = 0
														else
															racer_out_car_timer[a] = 0
														ENDIF
													else
														racer_out_car_timer[a] = 0
													ENDIF
												else
													racer_out_car_timer[a] = 0
												ENDIF
											else
												racer_out_car_timer[a] = 0
											ENDIF
										else
											racer_out_car_timer[a] = 0
										ENDIF
									ENDIF
								ENDIF
							endif
						ENDIF
						
						//IS THIS AI RACER MORE THAN 4 CHECKPOINTS BEHIND?
						if interior_race = 0
							r = 0
							e = 0
							WHILE r < total_racers
								LVAR_INT opponent_cpcounter_minus4
								opponent_cpcounter_minus4 = cpcounter[r] - 4
								IF cpcounter[a] < opponent_cpcounter_minus4
									++ e
								ENDIF
								++ r
							ENDWHILE
							//IF AI RACER IS MORE THAN 4 CHECKPOINTS BEHIND ANYONE WARP HIM TO THE NEXT CHECKPOINT
							IF e > 0
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer racer[a] 150.0 150.0 0
									IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer racer_cp_x[a] racer_cp_y[a] 150.0 150.0 0
										IF NOT LOCATE_CHAR_ANY_MEANS_2D racer[a] racer_cp_x[a] racer_cp_y[a] 40.0 40.0 0
											IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 4.0 4.0 3.0
												IF NOT IS_CHAR_ON_SCREEN racer[a]
													IF NOT IS_POINT_ON_SCREEN racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 4.0
														IF IS_CHAR_SITTING_IN_ANY_CAR racer[a]
															SET_CHAR_COORDINATES racer[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a]
															temp_int = cpcounter[a] + 1
															TURN_CAR_TO_FACE_COORD stored_car checkpoints_x[temp_int] checkpoints_y[temp_int]
														ELSE
															MARK_CAR_AS_NO_LONGER_NEEDED racers_car[a]
															GOSUB create_racers_car
															WARP_CHAR_INTO_CAR racer[a]	racers_car[a]
															temp_int = cpcounter[a] + 1
															IF temp_int < total_checkpoints
																TURN_CAR_TO_FACE_COORD racers_car[a] checkpoints_x[temp_int] checkpoints_y[temp_int]
																set_car_forward_speed racers_car[a] 20.0
															ENDIF
														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			
			temp_int = 0
			//HAS RACER HIT NEXT CHECKPOINT?
			IF LOCATE_CHAR_IN_CAR_3D racer[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] loc_size_x loc_size_y loc_size_z 0
				temp_int = 1
			else
				IF racer[a] = scplayer
					IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
						request_collision racer_cp_x[a] racer_cp_y[a]
						load_scene racer_cp_x[a] racer_cp_y[a] racer_cp_z[a]
						x = racer_cp_z[a] + 1.0
			            set_char_coordinates racer[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a]
					ENDIF
				ENDIF
			ENDIF
			IF temp_int = 1	
				IF race_flag = MAIN_RACE_LOOP
					race_flag = MAIN_RACE_LOOP_B //INCREMENT FLAG SO I KNOW THE RACE HAS STARTED AND I CAN WARP OPPONENTS ETC
				ENDIF
				
				IF scplayer = racer[a]
					if interior_race = 0
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
						REMOVE_BLIP first_blip
					endif
				ENDIF
					
				++ cpcounter[a]
				
				//GET NEXT CHECKPOINT FOR RACER
				IF cpcounter[a] < total_checkpoints
					temp_int = cpcounter[a]
					racer_cp_x[a] = checkpoints_x[temp_int]
					racer_cp_y[a] = checkpoints_y[temp_int]
					racer_cp_z[a] = checkpoints_z[temp_int]
				ELSE
					if lap_cpcounter[a] < total_laps
						++ lap_cpcounter[a]
					endif
					IF lap_cpcounter[a] = total_laps
						IF racer[a] = scplayer
							if total_laps > 1
								lap_timer = game_timer - last_lap_time //STORE A NEW LAP TIME FOR THE PLAYER
								lap_timer /= 1000
								IF cprace_best_lap_times[race_selection] > lap_timer
									cprace_best_lap_times[race_selection] = lap_timer
									if race_selection = NASCAR_RACE
										REGISTER_FASTEST_TIME BEST_LAP_TIME_HOTRING lap_timer
									endif
									if race_selection = DIRTBIKE_STADIUM
										REGISTER_FASTEST_TIME BEST_LAP_TIME_DIRTTRACK lap_timer
									endif
								ENDIF
							endif
							DELETE_CHECKPOINT cp_marker
							REMOVE_BLIP	first_blip
							race_flag = FINISH_RACE
							if show_race_selection = false
								if position > 0
									PRINT_BIG M_FAIL 3000 1
								endif
							endif
							racer_cp_z[a] = 99999.0	//STOP WINNING RACERS FROM GOING ANYWHERE
						ELSE
							//MAKE RACERS AS THEY FINISH THE RACE STOP ON FINISH LINE
							if not is_car_dead racers_car[a]
								REMOVE_STUCK_CAR_CHECK racers_car[a]
							endif
							if interior_race = 0
								get_script_task_status racer[a]	PERFORM_SEQUENCE_TASK task_status
								if task_status = finished_task
									OPEN_SEQUENCE_TASK sequence_task
										GET_CHAR_SPEED racer[a] temp_float
										temp_int =# temp_float
										temp_int *= -100
										temp_int += 4000
										if temp_int < 10
											temp_int = 10
										endif
										TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_GOFORWARD temp_int
										generate_random_int_in_range 0 5 temp_int
										if temp_int = 0
										or temp_int = 1
											TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_TURNLEFT 200
											TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKETURNLEFT 250000
										else
											TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_TURNRIGHT 200
											TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKETURNRIGHT 250000
										endif
										TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKESTRAIGHT 250000
										TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKESTRAIGHT 250000
										TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKESTRAIGHT 250000
										TASK_CAR_TEMP_ACTION -1 -1 TEMPACT_HANDBRAKESTRAIGHT 250000
										TASK_CAR_DRIVE_WANDER -1 -1 100.0 2
									CLOSE_SEQUENCE_TASK sequence_task
									PERFORM_SEQUENCE_TASK racer[a] sequence_task
									CLEAR_SEQUENCE_TASK sequence_task
								endif
							else
								get_script_task_status racer[a]	TASK_CAR_DRIVE_WANDER task_status
								if task_status = finished_task
									TASK_CAR_DRIVE_WANDER racer[a] -1 100.0 2
								endif
							endif
							racer_cp_z[a] = 99999.0	//STOP WINNING RACERS FROM GOING ANYWHERE
						ENDIF
					ELSE
						IF scplayer = racer[a]
							LVAR_INT lap_timer
							lap_timer = game_timer - last_lap_time //STORE A NEW LAP TIME FOR THE PLAYER
							lap_timer /= 1000
							IF cprace_best_lap_times[race_selection] > lap_timer
								cprace_best_lap_times[race_selection] = lap_timer
								if race_selection = NASCAR_RACE
									REGISTER_FASTEST_TIME BEST_LAP_TIME_HOTRING lap_timer
								endif
								if race_selection = DIRTBIKE_STADIUM
									REGISTER_FASTEST_TIME BEST_LAP_TIME_DIRTTRACK lap_timer
								endif
								mins = lap_timer / 60
								temp_int = mins * 60
								seconds = lap_timer - temp_int
								if seconds < 10
									print_with_2_numbers_now LAPTIM0 mins seconds 5000 1//New Best Lap Time ~1~:0~1~
								else
									print_with_2_numbers_now LAPTIME mins seconds 5000 1//New Best Lap Time ~1~:~1~
								endif
							ENDIF
							last_lap_time = game_timer
						ENDIF
						cpcounter[a] = 0
						temp_int = cpcounter[a]
						racer_cp_x[a] = checkpoints_x[temp_int]
						racer_cp_y[a] = checkpoints_y[temp_int]
						racer_cp_z[a] = checkpoints_z[temp_int]
					ENDIF
				ENDIF
				
				IF scplayer = racer[a]
					IF NOT racer_cp_z[a] = 99999.0
						//ADD NEXT BLIP AND CHECKPOINT FOR PLAYER
						if interior_race = 0
							ADD_BLIP_FOR_COORD racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] first_blip
							CHANGE_BLIP_COLOUR first_blip RED
							CHANGE_BLIP_DISPLAY first_blip BLIP_ONLY
							CHANGE_BLIP_SCALE first_blip 3
							DELETE_CHECKPOINT cp_marker
						endif	
						IF cpcounter[a] = total_checkpoints_minus_1
						AND lap_cpcounter[a] = total_laps_minus_1
							if checkpoint_type = checkpoint_torus
								temp_int = cpcounter[a]
								-- temp_int
								IF temp_int < 0
									temp_int = 0
								ENDIF
								CREATE_CHECKPOINT checkpoint_type racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] checkpoints_x[temp_int]	checkpoints_y[temp_int]	checkpoints_z[temp_int] checkpoint_size cp_marker
							else
								CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 0.0 0.0 6.0 cp_marker
							endif
						ELSE
							if interior_race = 0
								temp_int = cpcounter[a]
								++ temp_int
								IF cpcounter[a] = total_checkpoints_minus_1
									temp_int = 1
								ENDIF
								
								LVAR_INT cp_marker
								CREATE_CHECKPOINT checkpoint_type racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] checkpoints_x[temp_int]	checkpoints_y[temp_int]	checkpoints_z[temp_int] checkpoint_size cp_marker
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF NOT racer_cp_z[a] = 99999.0 //STOP WINNING RACERS FROM GOING ANYWHERE
						//TELL THE AI OPPONENT TO DRIVE/SAIL/FLY TO NEXT CHECKPOINT
						IF IS_CHAR_IN_ANY_HELI racer[a]
							IF NOT IS_CAR_DEAD racers_car[a]
								IF IS_CHAR_IN_CAR racer[a] racers_car[a]
									HELI_GOTO_COORDS racers_car[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
								ELSE
									STORE_CAR_CHAR_IS_IN_NO_SAVE racer[a] stored_car
									HELI_GOTO_COORDS stored_car racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
								ENDIF
							ELSE
								STORE_CAR_CHAR_IS_IN_NO_SAVE racer[a] stored_car
								HELI_GOTO_COORDS stored_car racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
							ENDIF
						ELSE
							IF IS_CHAR_IN_ANY_PLANE racer[a]
								IF NOT IS_CAR_DEAD racers_car[a]
									IF IS_CHAR_IN_CAR racer[a] racers_car[a]
										PLANE_GOTO_COORDS racers_car[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
									ELSE
										STORE_CAR_CHAR_IS_IN_NO_SAVE racer[a] stored_car
										PLANE_GOTO_COORDS stored_car racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
									ENDIF
								ELSE
									STORE_CAR_CHAR_IS_IN_NO_SAVE racer[a] stored_car
									PLANE_GOTO_COORDS stored_car racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 0.0 racer_cp_z[a]
								ENDIF
							ELSE
								get_script_task_status racer[a]	PERFORM_SEQUENCE_TASK task_status
								if task_status = finished_task
									get_script_task_status racer[a]	TASK_CAR_drive_wander task_status
									if task_status = finished_task

										//SET INDOOR RACER SPEED//
										IF NOT IS_CAR_DEAD racers_car[a]
											TASK_CAR_DRIVE_TO_COORD racer[a] racers_car[a] racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 50.0 MODE_RACING racers_car_model[a] DRIVINGMODE_AVOIDCARS
											
											IF cpcounter[a] > total_checkpoints
												REMOVE_STUCK_CAR_CHECK racers_car[a]
											ENDIF
										ELSE
											TASK_CAR_DRIVE_TO_COORD racer[a] -1 racer_cp_x[a] racer_cp_y[a] racer_cp_z[a] 50.0 MODE_RACING racers_car_model[a] DRIVINGMODE_AVOIDCARS
										ENDIF

									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			if interior_race > 0
				respawn_racer:
				IF NOT scplayer = racer[a]
					IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY respawn_x respawn_y respawn_z 2.5 2.5 2.5
						cpcounter[a] = 1
						REMOVE_BLIP	racer_blip[a]
						mark_car_as_no_longer_needed racers_car[a]
						mark_char_as_no_longer_needed racer[a]
						racer_cp_x[a] = respawn_x
						racer_cp_y[a] = respawn_y
						racer_cp_z[a] = respawn_z
						heading	= respawn_h
						gosub create_racers_car
						CREATE_CHAR_INSIDE_CAR racers_car[a] PEDTYPE_CIVMALE racer_model[a] racer[a]	
						SET_CHAR_AREA_VISIBLE racer[a] interior_race
						SET_CHAR_CAN_BE_SHOT_IN_VEHICLE racer[a] FALSE
						SET_CHAR_SUFFERS_CRITICAL_HITS racer[a] FALSE
						SET_CHAR_NEVER_TARGETTED racer[a] TRUE
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER	racer[a] TRUE
						SET_CHAR_CANT_BE_DRAGGED_OUT racer[a] FALSE
						SET_CHAR_DROWNS_IN_WATER racer[a] FALSE
						SET_CHAR_HEALTH	racer[a] 500
						set_car_forward_speed racers_car[a] 25.0
					endif
				endif
				//respawn dude + car
			else
				IF NOT scplayer = racer[a]
					IF race_flag < FINISH_RACE
						IF cpcounter[playaz] = cpcounter[a]
						OR cpcounter[playaz] < cpcounter[a]
						OR cpcounter[a] > total_checkpoints_minus_1
							++ position
						ENDIF
					ENDIF
					IF NOT IS_CHAR_IN_WATER racer[a]
						REMOVE_BLIP	racer_blip[a]
						MARK_CHAR_AS_NO_LONGER_NEEDED racer[a]
						MARK_CAR_AS_NO_LONGER_NEEDED racers_car[a]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		++ a
	ENDWHILE

	LVAR_INT display_hud_position race_timer_temp mins seconds
		
	if total_racers > 1

		DRAW_RECT x_hud_pos[6] y_hud_pos[6] x_hud_scale[6] y_hud_scale[6] 0 0 0 255
		DRAW_RECT x_hud_pos[5] y_hud_pos[5] x_hud_scale[5] y_hud_scale[5] 134 155 184 255
		DRAW_RECT x_hud_pos[0] y_hud_pos[0] x_hud_scale[0] y_hud_scale[0] 0 0 0 255

		// If there are multiple laps, render the bottom backdrop and the lap count.

		if total_laps > 1
		
			DRAW_RECT x_hud_pos[7] y_hud_pos[7] x_hud_scale[7] y_hud_scale[7] 0 0 0 255
			DRAW_RECT x_hud_pos[8] y_hud_pos[8] x_hud_scale[8] y_hud_scale[8] 134 155 184 255
			DRAW_RECT x_hud_pos[9] y_hud_pos[9] x_hud_scale[9] y_hud_scale[9] 0 0 0 255

			temp_int = lap_cpcounter[playaz] + 1
			
			if temp_int > total_laps
				temp_int = total_laps
			endif
			
			GOSUB setup_text_cprace 
			
			GET_CURRENT_LANGUAGE current_Language
			IF current_Language = LANGUAGE_GERMAN
				x_hud_scale[10] = 0.4235
			ELSE
				IF current_Language = LANGUAGE_SPANISH
					x_hud_scale[10] = 0.3786
				ELSE
					x_hud_scale[10] = 0.5
				ENDIF
			ENDIF

			SET_TEXT_SCALE x_hud_scale[10] y_hud_scale[10]
			SET_TEXT_JUSTIFY ON
			SET_TEXT_WRAPX 600.0
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[10] y_hud_pos[10] RACES32 temp_int total_laps
		endif

		display_hud_position = position + 1
		
		GOSUB setup_text_cprace 

		IF got_a_best_position = 1
			SET_TEXT_COLOUR 180 25 29 255
		ENDIF

		SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
		IF display_hud_position = 1
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] ST
		ENDIF
		IF display_hud_position = 2
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] ND
		ENDIF
		IF display_hud_position = 3
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] RD
		ENDIF
		IF display_hud_position > 3
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] TH
		ENDIF
		
		GOSUB setup_text_cprace 
		IF got_a_best_position = 1
			SET_TEXT_COLOUR 180 25 29 255
		ENDIF
		SET_TEXT_SCALE x_hud_scale[2] y_hud_scale[2]
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT_WITH_NUMBER x_hud_pos[2] y_hud_pos[2] NUMBER display_hud_position

		GOSUB setup_text_cprace 
		IF got_a_best_position = 1
			SET_TEXT_COLOUR 180 25 29 255
		ENDIF
		SET_TEXT_SCALE x_hud_scale[3] y_hud_scale[3]
		DISPLAY_TEXT_WITH_NUMBER x_hud_pos[3] y_hud_pos[3] OUT_OF total_racers
		
		IF race_flag < FINISH_RACE
			race_timer_temp = game_timer - races_timer
			race_timer_temp /= 1000
		ENDIF

		mins = race_timer_temp / 60
		IF mins > 99
			mins = 99
		ENDIF

		temp_int = mins * 60
		seconds = race_timer_temp - temp_int

		GOSUB setup_text_cprace 
		IF got_a_best_time = 1
			SET_TEXT_COLOUR 180 25 29 255
		ENDIF
		SET_TEXT_CENTRE ON
		SET_TEXT_SCALE x_hud_scale[4] y_hud_scale[4]

		IF seconds > 9
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[4] y_hud_pos[4] TIME mins seconds
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[4] y_hud_pos[4] TIME_0 mins seconds
		ENDIF

	ENDIF
	IF race_selection = BADLAND_RACE1
		GOSUB race13_special
	ENDIF
	IF race_selection = NASCAR_RACE
	or race_selection = DIRTBIKE_STADIUM
		GOSUB do_stadium_sound_effects
	ENDIF
break
	
case FINISH_RACE

	if show_race_selection = false
		IF display_hud_position = 1
			IF race_selection = BADLAND_RACE1
	        OR race_selection = BADLAND_RACE2
				++ flag_bcesar_mission_counter
	        ENDIF
			IF race_selection = CESAR_RACE
				PRINT_WITH_NUMBER_BIG M_PASS 1000 5000 1 //"Mission Passed!"
				ADD_SCORE player1 1000
				REMOVE_BLIP cesar_contact_blip
				++ flag_cesar_mission_counter
			ENDIF
			CLEAR_WANTED_LEVEL player1
		ELSE
			IF race_selection = CESAR_RACE
			OR race_selection = BADLAND_RACE1
			OR race_selection = BADLAND_RACE2
				PRINT_BIG M_FAIL 3000 1 //"Mission Failed"
			ENDIF

		ENDIF
	ENDIF
	
	clear_onscreen_timer races_timer
	
	// Really confusing but when we are using an onscreen timer, races_timer automatically counts up, and is stored as the current time.
	// Else, the current time is the current time - the start time :(

    
	IF cprace_best_times[race_selection] > race_timer_temp
		IF total_racers = 1
			race_timer_temp = races_timer
			race_timer_temp /= 1000
		ENDIF
		
		cprace_best_times[race_selection] = race_timer_temp
		REGISTER_FASTEST_TIME race_stats_time[race_selection] race_timer_temp
		got_a_best_time = 1
	ENDIF

	if total_racers > 1
		IF cprace_best_position[race_selection] > display_hud_position
			cprace_best_position[race_selection] = display_hud_position
			REGISTER_BEST_POSITION race_stats_position[race_selection] display_hud_position
			got_a_best_position = 1
		ENDIF
	endif

	x_hud_pos[0] = 208.0665 
	y_hud_pos[0] = 297.9241 
	x_hud_scale[0] = 474.2788 
	y_hud_scale[0] = 383.4714 

	x_hud_pos[1] = 229.6480 
	y_hud_pos[1] = 313.8785 
	x_hud_scale[1] = 0.5858 
	y_hud_scale[1] = 2.5296 

	x_hud_pos[2] = 455.3051 
	x_hud_scale[2] = 0.4116 

	y_hud_pos[3] = 339.1096
	x_hud_scale[3] = 0.5858 
	
	gosub player_made_progress_cprace 

	races_timer = game_timer + 8000
	race_flag = RACE_ENDED
break

case RACE_ENDED

	GET_CURRENT_LANGUAGE current_Language
	if current_language = LANGUAGE_ENGLISH
		x_hud_scale[1] = x_hud_scale[3] 
	else
		x_hud_scale[1] = x_hud_scale[2]
	endif
	
	if total_racers = 1
		y =	y_hud_pos[0] + 25.0
		DRAW_WINDOW x_hud_pos[0] y x_hud_scale[0] y_hud_scale[0] dummy SWIRLS_BOTH  // You Have Won!
	else
		IF display_hud_position = 1
			DRAW_WINDOW x_hud_pos[0] y_hud_pos[0] x_hud_scale[0] y_hud_scale[0] RACES18 SWIRLS_BOTH  // Winner!
		ELSE
			DRAW_WINDOW x_hud_pos[0] y_hud_pos[0] x_hud_scale[0] y_hud_scale[0] RACES_8 SWIRLS_BOTH  // Loser!
		ENDIF
	ENDIF
	
	if total_racers > 1
		GOSUB setup_text_cprace
		SET_TEXT_JUSTIFY ON
		SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
		
		IF got_a_best_position = 1
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] TOP_POS 	// New Best Position
		else
			DISPLAY_TEXT x_hud_pos[1] y_hud_pos[1] RACES30	// Position
		ENDIF
		
		GOSUB setup_text_cprace
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
		SET_TEXT_COLOUR 180 180 180 255
		
		IF display_hud_position = 1
			DISPLAY_TEXT x_hud_pos[2] y_hud_pos[1] RACES26  // 1ST
		ENDIF
		
		IF display_hud_position = 2
			DISPLAY_TEXT x_hud_pos[2] y_hud_pos[1] RACES27  // 2ND
		ENDIF
		
		IF display_hud_position = 3
			DISPLAY_TEXT x_hud_pos[2] y_hud_pos[1] RACES28  // 3RD
		ENDIF
		
		IF display_hud_position > 3
			DISPLAY_TEXT_WITH_NUMBER x_hud_pos[2] y_hud_pos[1] RACES29 display_hud_position  // ~1~TH
		ENDIF
	ENDIF

	GOSUB setup_text_cprace
	SET_TEXT_JUSTIFY ON
	SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
	
	IF got_a_best_time = 1
		DISPLAY_TEXT x_hud_pos[1] y_hud_pos[3] TOPTIME	// New Best Time
	else
		DISPLAY_TEXT x_hud_pos[1] y_hud_pos[3] RACES31	// Time
	ENDIF
	

	mins = race_timer_temp / 60
	temp_int = mins * 60
	seconds = race_timer_temp - temp_int
	GOSUB setup_text_cprace
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_SCALE x_hud_scale[1] y_hud_scale[1]
	SET_TEXT_COLOUR 180 180 180 255
	
	IF seconds > 9
		DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[2] y_hud_pos[3] TIME mins seconds//  ~1~:~1~
	ELSE
		DISPLAY_TEXT_WITH_2_NUMBERS x_hud_pos[2] y_hud_pos[3] TIME_0 mins seconds//  ~1~:0~1~
	ENDIF

	IF game_timer > races_timer
		RETURN
	ENDIF
break

endswitch

GOTO mission_cprace_loop


race_coords:

SWITCH race_selection
LVAR_FLOAT checkpoints_x[82]
LVAR_FLOAT checkpoints_y[82]
LVAR_FLOAT checkpoints_z[82]

case CESAR_RACE//LA race 1/////////////////////////////////////////////////////
checkpoints_x[0] = 1492.4072 
checkpoints_y[0] = -1872.3652
checkpoints_z[0] = 15.8 

checkpoints_x[1] = 1384.2626 
checkpoints_y[1] = -1872.0153
checkpoints_z[1] = 12.3828 

checkpoints_x[2] = 1075.4420 
checkpoints_y[2] = -1851.9783
checkpoints_z[2] = 12.3910 

checkpoints_x[3] = 647.3663 
checkpoints_y[3] = -1734.1852
checkpoints_z[3] = 12.4844 

checkpoints_x[4] = 627.2474 
checkpoints_y[4] = -1228.5500
checkpoints_z[4] = 16.9893 
				    
checkpoints_x[5] = 497.0043 
checkpoints_y[5] = -1282.8180
checkpoints_z[5] = 14.6513 
				    
checkpoints_x[6] = 500.4120 
checkpoints_y[6] = -1326.6494
checkpoints_z[6] = 14.8178 

checkpoints_x[7] = 401.0033 
checkpoints_y[7] = -1404.4572
checkpoints_z[7] = 32.8973 

checkpoints_x[8] = 420.3067 
checkpoints_y[8] = -1450.7135
checkpoints_z[8] = 29.5842 

checkpoints_x[9] = 329.1244 
checkpoints_y[9] = -1631.1117
checkpoints_z[9] = 32.1247 

checkpoints_x[10] = 366.4376 
checkpoints_y[10] = -1647.3485
checkpoints_z[10] = 31.7176 

checkpoints_x[11] =	369.98 
checkpoints_y[11] =	-2030.34
checkpoints_z[11] = 5.55

total_checkpoints = 12

total_racers = 6
racer_model[0] = DNMOLC2
racer_model[1] = WMYCLOT
racer_model[2] = CWFYHB
racer_model[3] = BIKERB
racer_model[4] = SPECIAL01
racer_model[5] = MALE01

racers_car_model[0] = MAJESTIC
racers_car_model[1] = REMINGTN
racers_car_model[2] = MAJESTIC
racers_car_model[3] = blade 
racers_car_model[4] = SAVANNA  // Cesar
racers_car_model[5] = blade // Player

racers_car_colour1[0] = 6 
racers_car_colour2[0] = 0 
racers_car_colour1[1] = 8 
racers_car_colour2[1] = 0  
racers_car_colour1[2] = 2 
racers_car_colour2[2] = 1 
racers_car_colour1[3] = 0 
racers_car_colour2[3] = 0  
racers_car_colour1[4] = 3 
racers_car_colour2[4] = 3  
break


case 1//3//LA race 2/////////////////////////////////////////////////////////
checkpoints_x[0] = 2875.1919 
checkpoints_y[0] = -1458.8499
checkpoints_z[0] = 10.5892 

checkpoints_x[1] = 2875.3616 
checkpoints_y[1] = -1404.7677 
checkpoints_z[1] = 10.7143 

checkpoints_x[2] = 2797.0366 
checkpoints_y[2] = -1291.1517 
checkpoints_z[2] = 41.3890 

checkpoints_x[3] = 2609.9841 
checkpoints_y[3] = -1256.4584 
checkpoints_z[3] = 47.9322 

checkpoints_x[4] = 2371.2822 
checkpoints_y[4] = -1280.4771 
checkpoints_z[4] = 23.6341 

checkpoints_x[5] = 2450.5571 
checkpoints_y[5] = -1444.2399 
checkpoints_z[5] = 23.6318 

checkpoints_x[6] = 2618.4355 
checkpoints_y[6] = -1444.8256 
checkpoints_z[6] = 31.6234 

checkpoints_x[7] = 2657.3000 
checkpoints_y[7] = -1404.3268 
checkpoints_z[7] = 30.0795 

checkpoints_x[8] = 2680.5063 
checkpoints_y[8] = -1489.5558 
checkpoints_z[8] = 30.2098 

checkpoints_x[9] = 2755.9521 
checkpoints_y[9] = -1488.5153 
checkpoints_z[9] = 29.3812 

checkpoints_x[10] = checkpoints_x[0]
checkpoints_y[10] = checkpoints_y[0] 
checkpoints_z[10] = checkpoints_z[0]

total_checkpoints = 11

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = PCJ600
racers_car_model[1] = FCR900
racers_car_model[2] = NRG500
racers_car_model[3] = PCJ600
racers_car_model[4] = FCR900
racers_car_model[5] = NRG500
gosub get_random_racer_models
break


case 2//7//La race 3/////////////////////////////////////////////////////////
checkpoints_x[0] = 261.7816 
checkpoints_y[0] = -1243.1110 
checkpoints_z[0] = 71.8851 

checkpoints_x[1] = 312.9973 
checkpoints_y[1] = -1206.8527 
checkpoints_z[1] = 74.9057 

checkpoints_x[2] = 440.3485 
checkpoints_y[2] = -1186.6721 
checkpoints_z[2] = 67.2076 

checkpoints_x[3] = 623.8817 
checkpoints_y[3] = -1109.9668 
checkpoints_z[3] = 45.6186 

checkpoints_x[4] = 745.0934 
checkpoints_y[4] = -951.0351 
checkpoints_z[4] = 53.7660 

checkpoints_x[5] = 873.6384 
checkpoints_y[5] = -860.0975 
checkpoints_z[5] = 76.5242 

checkpoints_x[6] = 1053.8181 
checkpoints_y[6] = -777.5837 
checkpoints_z[6] = 104.7803 

checkpoints_x[7] = 1248.3679 
checkpoints_y[7] = -731.3474 
checkpoints_z[7] = 93.7346 

checkpoints_x[8] = 1372.4016 
checkpoints_y[8] = -675.6946 
checkpoints_z[8] = 92.6217 

checkpoints_x[9] = 1328.0111 
checkpoints_y[9] = -583.8703 
checkpoints_z[9] = 93.4113 

checkpoints_x[10] = 1167.3146 
checkpoints_y[10] = -633.0795 
checkpoints_z[10] = 102.7501 

checkpoints_x[11] = 968.4116 
checkpoints_y[11] = -646.2520 
checkpoints_z[11] = 120.7201 

checkpoints_x[12] = 791.0770 
checkpoints_y[12] = -800.4396 
checkpoints_z[12] = 65.0537 

checkpoints_x[13] = 620.9355 
checkpoints_y[13] = -905.8031 
checkpoints_z[13] = 62.1261 

checkpoints_x[14] = 454.9853 
checkpoints_y[14] = -1013.5457
checkpoints_z[14] = 92.0091 

checkpoints_x[15] = 276.8912 
checkpoints_y[15] = -1104.1589 
checkpoints_z[15] = 80.0612 

checkpoints_x[16] = 141.6012 
checkpoints_y[16] = -1247.2673 
checkpoints_z[16] = 43.7920 

checkpoints_x[17] = 192.4688 
checkpoints_y[17] = -1380.5973 
checkpoints_z[17] = 47.6608 

checkpoints_x[18] = checkpoints_x[0]
checkpoints_y[18] = checkpoints_y[0] 
checkpoints_z[18] = checkpoints_z[0]

total_checkpoints = 19

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = PCJ600
racers_car_model[1] = FCR900
racers_car_model[2] = NRG500
racers_car_model[3] = PCJ600
racers_car_model[4] = FCR900
racers_car_model[5] = FCR900
gosub get_random_racer_models
break


case 3//5//La race 4/////////////////////////////////////////////////////////
checkpoints_x[0] = 1927.4796 
checkpoints_y[0] = -1515.5161 
checkpoints_z[0] = 2.2978 

checkpoints_x[1] = 2018.4005 
checkpoints_y[1] = -1515.4050 
checkpoints_z[1] = 2.4054 

checkpoints_x[2] = 2179.0063 
checkpoints_y[2] = -1559.6609 
checkpoints_z[2] = 1.1797 

checkpoints_x[3] = 2370.1377 
checkpoints_y[3] = -1619.5530 
checkpoints_z[3] = 7.3323 

checkpoints_x[4] = 2570.8413 
checkpoints_y[4] = -1619.0562 
checkpoints_z[4] = 17.6841 

checkpoints_x[5] = 2756.8462 
checkpoints_y[5] = -1657.1729 
checkpoints_z[5] = 11.5191 

checkpoints_x[6] = 2915.1785 
checkpoints_y[6] = -1534.3623 
checkpoints_z[6] = 9.8750 

checkpoints_x[7] = 2916.2283 
checkpoints_y[7] = -1333.4915 
checkpoints_z[7] = 9.8750 

checkpoints_x[8] = 2819.3313 
checkpoints_y[8] = -1142.6382
checkpoints_z[8] = 15.7018 

checkpoints_x[9] = 2622.6462 
checkpoints_y[9] = -1152.8718 
checkpoints_z[9] = 50.4181 

checkpoints_x[10] = 2423.1863 
checkpoints_y[10] = -1154.1694 
checkpoints_z[10] = 30.5642 

checkpoints_x[11] = 2167.0461 
checkpoints_y[11] = -1101.9148 
checkpoints_z[11] = 24.3864 

checkpoints_x[12] = 2006.0774 
checkpoints_y[12] = -1027.8392 
checkpoints_z[12] = 34.3152 

checkpoints_x[13] = 1835.8240 
checkpoints_y[13] = -991.5906  
checkpoints_z[13] = 35.8824 

checkpoints_x[14] = 1636.3135 
checkpoints_y[14] = -1005.1503
checkpoints_z[14] = 49.8831 

checkpoints_x[15] = 1617.0243
checkpoints_y[15] = -1215.8334 
checkpoints_z[15] = 51.0566 

checkpoints_x[16] = 1577.9720
checkpoints_y[16] = -1417.8311 
checkpoints_z[16] = 27.6154

checkpoints_x[17] = 1722.8226 
checkpoints_y[17] = -1525.9321 
checkpoints_z[17] = 18.3927 

checkpoints_x[18] = checkpoints_x[0]
checkpoints_y[18] = checkpoints_y[0] 
checkpoints_z[18] = checkpoints_z[0]

total_checkpoints = 19

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = PCJ600
racers_car_model[1] = FCR900
racers_car_model[2] = NRG500
racers_car_model[3] = PCJ600
racers_car_model[4] = FCR900
racers_car_model[5] = FCR900
gosub get_random_racer_models
break

case 4//6//LA race 5/////////////////////////////////////////////////////////
checkpoints_x[0] = 1357.6558 
checkpoints_y[0] = -1361.6705 
checkpoints_z[0] = 12.3828 

checkpoints_x[1] = 1357.6888 
checkpoints_y[1] = -1186.4038 
checkpoints_z[1] = 20.2744 

checkpoints_x[2] = 1369.7407 
checkpoints_y[2] = -1000.5261 
checkpoints_z[2] = 27.0200 

checkpoints_x[3] = 1505.8179 
checkpoints_y[3] = -868.7814 
checkpoints_z[3] = 61.0462 

checkpoints_x[4] = 1452.7943 
checkpoints_y[4] = -711.5325 
checkpoints_z[4] = 89.8766 

checkpoints_x[5] = 1253.5721 
checkpoints_y[5] = -729.2587 
checkpoints_z[5] = 93.4292 

checkpoints_x[6] = 1059.3055 
checkpoints_y[6] = -775.9847 
checkpoints_z[6] = 105.3241 

checkpoints_x[7] = 878.6650 
checkpoints_y[7] = -858.1913 
checkpoints_z[7] = 76.8826 

checkpoints_x[8] = 721.7916 
checkpoints_y[8] = -980.4561 
checkpoints_z[8] = 51.9555 

checkpoints_x[9] = 571.0251 
checkpoints_y[9] = -1045.2030 
checkpoints_z[9] = 72.2876 

checkpoints_x[10] = 373.2952 
checkpoints_y[10] = -1075.6443 
checkpoints_z[10] = 72.8049 

checkpoints_x[11] = 288.4963 
checkpoints_y[11] = -1249.7473 
checkpoints_z[11] = 72.7210 

checkpoints_x[12] = 481.2224 
checkpoints_y[12] = -1240.9764 
checkpoints_z[12] = 19.3512 

checkpoints_x[13] = 518.3340 
checkpoints_y[13] = -1377.6030 
checkpoints_z[13] = 14.9457 

checkpoints_x[14] = 448.0064 
checkpoints_y[14] = -1540.8318 
checkpoints_z[14] = 27.8796 

checkpoints_x[15] = 487.4595 
checkpoints_y[15] = -1661.5750 
checkpoints_z[15] = 20.7400 

checkpoints_x[16] = 687.2253 
checkpoints_y[16] = -1673.4799 
checkpoints_z[16] = 10.7959 

checkpoints_x[17] = 866.4786 
checkpoints_y[17] = -1584.6984 
checkpoints_z[17] = 12.3828 

checkpoints_x[18] = 1066.7291 
checkpoints_y[18] = -1572.3047 
checkpoints_z[18] = 12.3750 

checkpoints_x[19] = 1261.2136 
checkpoints_y[19] = -1572.2433 
checkpoints_z[19] = 12.3906 

checkpoints_x[20] = checkpoints_x[0]
checkpoints_y[20] = checkpoints_y[0]
checkpoints_z[20] = checkpoints_z[0]

total_checkpoints = 21

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = URANUS   ///thiss
racers_car_model[1] = WINDSOR
racers_car_model[2] = BLISTAC
racers_car_model[3] = WINDSOR
racers_car_model[4] = SENTINEL
racers_car_model[5] = SUNRISE
gosub get_random_racer_models
break


case 5//11//LA race 6////////////////////////////////////////////////////////
checkpoints_x[0] = 852.0544 
checkpoints_y[0] = -1405.6216 
checkpoints_z[0] = 12.2170 

checkpoints_x[1] = 1102.7889 
checkpoints_y[1] = -1405.7665 
checkpoints_z[1] = 12.4280 

checkpoints_x[2] = 1353.0377 
checkpoints_y[2] = -1405.5443 
checkpoints_z[2] = 12.3251 

checkpoints_x[3] = 1600.9651 
checkpoints_y[3] = -1440.8522 
checkpoints_z[3] = 12.3828 

checkpoints_x[4] = 1850.6221 
checkpoints_y[4] = -1461.7772 
checkpoints_z[4] = 12.3984 

checkpoints_x[0] = 831.7679 
checkpoints_y[0] = -1405.5880 
checkpoints_z[0] = 12.3849 

checkpoints_x[1] = 1031.8224 
checkpoints_y[1] = -1405.6149 
checkpoints_z[1] = 12.0966 

checkpoints_x[2] = 1232.1444 
checkpoints_y[2] = -1405.7316 
checkpoints_z[2] = 12.0633 

checkpoints_x[3] = 1429.5076 
checkpoints_y[3] = -1439.0908 
checkpoints_z[3] = 12.3828 

checkpoints_x[4] = 1630.1804 
checkpoints_y[4] = -1440.8579 
checkpoints_z[4] = 12.3828 

checkpoints_x[5] = 1829.5267 
checkpoints_y[5] = -1460.8038 
checkpoints_z[5] = 12.3448 

checkpoints_x[6] = 2029.9794 
checkpoints_y[6] = -1463.3813 
checkpoints_z[6] = 14.4553 

checkpoints_x[7] = 2175.8225 
checkpoints_y[7] = -1384.4027 
checkpoints_z[7] = 22.8281 

checkpoints_x[8] = 2342.4265 
checkpoints_y[8] = -1442.3237 
checkpoints_z[8] = 22.8281 

checkpoints_x[9] = 2191.8752 
checkpoints_y[9] = -1545.9338 
checkpoints_z[9] = 1.1724 

checkpoints_x[10] = 1997.0780 
checkpoints_y[10] = -1499.9512 
checkpoints_z[10] = 2.3804 

checkpoints_x[11] = 1797.0646 
checkpoints_y[11] = -1500.8741 
checkpoints_z[11] = 6.8307 

checkpoints_x[12] = 1641.0017 
checkpoints_y[12] = -1439.3447 
checkpoints_z[12] = 27.0747 

checkpoints_x[13] = 1627.5547 
checkpoints_y[13] = -1280.7031 
checkpoints_z[13] = 41.8850 

checkpoints_x[14] = 1683.5068 
checkpoints_y[14] = -1089.0116 
checkpoints_z[14] = 55.7917 

checkpoints_x[15] = 1566.0544 
checkpoints_y[15] = -927.5945 
checkpoints_z[15] = 42.7541 

checkpoints_x[16] = 1366.1051 
checkpoints_y[16] = -933.6759 
checkpoints_z[16] = 33.1953 

checkpoints_x[17] = 1166.0874 
checkpoints_y[17] = -941.9011 
checkpoints_z[17] = 41.8697 

checkpoints_x[18] = 966.7150 
checkpoints_y[18] = -962.2038 
checkpoints_z[18] = 38.1905 

checkpoints_x[19] = 784.2582 
checkpoints_y[19] = -1043.9791 
checkpoints_z[19] = 23.6177 

checkpoints_x[20] = 677.2552 
checkpoints_y[20] = -1164.1283 
checkpoints_z[20] = 14.1640 

checkpoints_x[21] = 627.7037
checkpoints_y[21] = -1337.9041
checkpoints_z[21] = 12.4227

checkpoints_x[22] = checkpoints_x[0]
checkpoints_y[22] = checkpoints_y[0] 
checkpoints_z[22] = checkpoints_z[0]

total_checkpoints = 23


total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = BANSHEE
racers_car_model[1] = BULLET
racers_car_model[2] = BUFFALO
racers_car_model[3] = BANSHEE
racers_car_model[4] = BULLET
racers_car_model[5] = SUPERGT
gosub get_random_racer_models
break



case 6//4//LA & Badlands big loop race/////////////////////////////////////////////////////////
checkpoints_x[0] = 1331.9257 
checkpoints_y[0] = -2280.5098 
checkpoints_z[0] = 13.1732 

checkpoints_x[1] = 1331.7747 
checkpoints_y[1] = -2541.6821 
checkpoints_z[1] = 13.1765 

checkpoints_x[2] = 1544.3086 
checkpoints_y[2] = -2684.9834 
checkpoints_z[2] = 7.5074 

checkpoints_x[3] = 1945.9465 
checkpoints_y[3] = -2685.1306 
checkpoints_z[3] = 6.5549 

checkpoints_x[4] = 2175.1411 
checkpoints_y[4] = -2557.9939 
checkpoints_z[4] = 13.1730 

checkpoints_x[5] = 2352.7188 
checkpoints_y[5] = -2222.2229 
checkpoints_z[5] = 13.1737 

checkpoints_x[6] = 2740.5217 
checkpoints_y[6] = -2168.7822 
checkpoints_z[6] = 10.7349 

checkpoints_x[7] = 2844.8318 
checkpoints_y[7] = -1812.9653 
checkpoints_z[7] = 10.6737 

checkpoints_x[8] = 2926.0945 
checkpoints_y[8] = -1421.2627 
checkpoints_z[8] = 10.6570 

checkpoints_x[9] = 2888.9463 
checkpoints_y[9] = -1022.6848 
checkpoints_z[9] = 10.6607 

checkpoints_x[10] = 2896.4834 
checkpoints_y[10] = -621.0989 
checkpoints_z[10] = 10.6300 

checkpoints_x[11] = 2718.6228 
checkpoints_y[11] = -273.2495 
checkpoints_z[11] = 27.6705 

checkpoints_x[12] = 2774.2993 
checkpoints_y[12] = 119.9424 
checkpoints_z[12] = 22.8712 

checkpoints_x[13] = 2612.8201 
checkpoints_y[13] = 327.3945 
checkpoints_z[13] = 25.6764 

checkpoints_x[14] = 2213.9834 
checkpoints_y[14] = 324.9601 
checkpoints_z[14] = 32.5307 

checkpoints_x[15] = 1817.1840 
checkpoints_y[15] = 278.3323 
checkpoints_z[15] = 21.5314 

checkpoints_x[16] = 1621.1851 
checkpoints_y[16] = 312.8743 
checkpoints_z[16] = 20.8534 

checkpoints_x[17] = 1608.0289 
checkpoints_y[17] = 372.2700 
checkpoints_z[17] = 26.4284 

checkpoints_x[18] = 1658.6716 
checkpoints_y[18] = 317.3336 
checkpoints_z[18] = 30.0474 

checkpoints_x[19] = 1651.5359 
checkpoints_y[19] = -68.8376 
checkpoints_z[19] = 35.9156 

checkpoints_x[20] = 1668.6531 
checkpoints_y[20] = -369.1116 
checkpoints_z[20] = 34.3923 

checkpoints_x[21] = 1699.7850 
checkpoints_y[21] = -665.8258 
checkpoints_z[21] = 43.3311 

checkpoints_x[22] = 1640.0302 
checkpoints_y[22] = -1062.3292 
checkpoints_z[22] = 60.8035 

checkpoints_x[23] = 1594.4076 
checkpoints_y[23] = -1457.8339 
checkpoints_z[23] = 28.3679 

checkpoints_x[24] = 1622.3555 
checkpoints_y[24] = -1856.5045 
checkpoints_z[24] = 25.4707 

checkpoints_x[25] = 1452.1591 
checkpoints_y[25] = -2117.2712 
checkpoints_z[25] = 13.1726 

checkpoints_x[26] = checkpoints_x[0]
checkpoints_y[26] = checkpoints_y[0]
checkpoints_z[26] = checkpoints_z[0]

total_checkpoints = 27

total_racers = 4
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = TURISMO
racers_car_model[1] = BANSHEE
racers_car_model[2] = CHEETAH
racers_car_model[3] = BULLET
racers_car_model[4] = TURISMO
racers_car_model[5] = INFERNUS
gosub get_random_racer_models
break


case BADLAND_RACE1/////////////////////////////BCESAR PART I/////////////////////////////
checkpoints_x[0] = 1559.9829
checkpoints_y[0] = 17.4680 
checkpoints_z[0] = 26.1563 

checkpoints_x[1] = 1556.4261 
checkpoints_y[1] = -94.0345 
checkpoints_z[1] = 19.4957 

checkpoints_x[2] = 1434.7378 
checkpoints_y[2] = -214.9645 
checkpoints_z[2] = 7.3527 

checkpoints_x[3] = 1321.2535 
checkpoints_y[3] = -195.8984 
checkpoints_z[3] = 15.7447

checkpoints_x[4] = 1211.0071 
checkpoints_y[4] = -102.6927 
checkpoints_z[4] = 39.3603 

checkpoints_x[5] = 892.2923 
checkpoints_y[5] = -88.1123 			 
checkpoints_z[5] = 22.4405 
			 
checkpoints_x[6] = 789.9247 
checkpoints_y[6] = -124.3171 
checkpoints_z[6] = 21.3365 
				   
checkpoints_x[7] = 659.6545 
checkpoints_y[7] = -196.7497
checkpoints_z[7] = 11.7663  

checkpoints_x[8] = 419.4744 
checkpoints_y[8] = -302.5507 
checkpoints_z[8] = 6.4460 							   

checkpoints_x[9] = 289.8085 
checkpoints_y[9] = -380.3828 
checkpoints_z[9] = 7.8753

checkpoints_x[10] = 207.4451 
checkpoints_y[10] = -298.9230 
checkpoints_z[10] = 0.4375

checkpoints_x[11] = 182.4970 
checkpoints_y[11] = -217.8004 
checkpoints_z[11] = 0.4216 

checkpoints_x[12] = 22.0727 
checkpoints_y[12] = -207.7681 
checkpoints_z[12] = 0.5827 

checkpoints_x[13] = -114.2310 
checkpoints_y[13] = -132.3417 
checkpoints_z[13] = 2.1236 

checkpoints_x[14] = -38.4818 
checkpoints_y[14] = 140.8613 
checkpoints_z[14] = 2.1234

checkpoints_x[15] = -141.4718 //-149.1394 
checkpoints_y[15] = 187.6611 //192.6522 
checkpoints_z[15] = 6.2406   //2.1250 

checkpoints_x[16] = -329.7878 
checkpoints_y[16] = 173.9210 
checkpoints_z[16] = 5.4030 

checkpoints_x[17] = -507.6872 
checkpoints_y[17] = 221.0444 
checkpoints_z[17] = 8.7633 

checkpoints_x[18] = -661.1096//-658.3503 
checkpoints_y[18] = 226.3879 //146.4855 
checkpoints_z[18] = 16.7064  //28.2693 

checkpoints_x[19] = -618.4406 
checkpoints_y[19] = -67.8995 		 
checkpoints_z[19] = 62.3489

checkpoints_x[20] = -507.8417  
checkpoints_y[20] = -45.9077 
checkpoints_z[20] = 59.2556  

checkpoints_x[21] = -520.6511 
checkpoints_y[21] = 79.6011 
checkpoints_z[21] = 32.2117 

checkpoints_x[22] = -715.4454 
checkpoints_y[22] = 225.1288 
checkpoints_z[22] = 0.2899 

checkpoints_x[23] = -770.0811 
checkpoints_y[23] = 130.1324 
checkpoints_z[23] = 9.4651 

checkpoints_x[24] = -738.1151 
checkpoints_y[24] = 25.5550 
checkpoints_z[24] = 32.2336 

checkpoints_x[25] = -885.7850 
checkpoints_y[25] = -41.3927 
checkpoints_z[25] = 33.2347 

checkpoints_x[26] = -716.4521  
checkpoints_y[26] = 5.1206 
checkpoints_z[26] = 59.1055 

checkpoints_x[27] = -759.2693 
checkpoints_y[27] = -92.0455 
checkpoints_z[27] = 64.8686 

checkpoints_x[28] = -826.8810  
checkpoints_y[28] = -167.7603  
checkpoints_z[28] = 64.9900 

checkpoints_x[29] = -728.9467 
checkpoints_y[29] = -173.5218 
checkpoints_z[29] = 65.1248 

checkpoints_x[30] = -544.8638 
checkpoints_y[30] = -189.4458 
checkpoints_z[30] = 77.8383 
 
total_checkpoints = 31

total_racers = 4
racer_model[0] = WMYST
racer_model[1] = WMYBMX
racer_model[2] = SPECIAL01
racer_model[3] = MALE01
racers_car_model[0] = STALLION
racers_car_model[1] = SABRE
racers_car_model[2] = FORTUNE
racers_car_model[3] = SABRE

racers_car_colour1[0] = 4 
racers_car_colour2[0] = 80 
racers_car_colour1[1] = 3 
racers_car_colour2[1] = 80  
racers_car_colour1[2] = 5 
racers_car_colour2[2] = 80 
break



case BADLAND_RACE2/////////////////////////////BCESAR4 PART II/////////////////////////////
checkpoints_x[0] = -544.8638
checkpoints_y[0] = -189.4458
checkpoints_z[0] = 81.8383

checkpoints_x[1] = -728.9467     
checkpoints_y[1] = -173.5218       
checkpoints_z[1] = 65.1248

checkpoints_x[2] = -826.8810      
checkpoints_y[2] = -167.7603        
checkpoints_z[2] = 64.9900 

checkpoints_x[3] = -759.2693     
checkpoints_y[3] = -92.0455        
checkpoints_z[3] = 64.8686

checkpoints_x[4] = -716.4521     
checkpoints_y[4] = 5.1206        
checkpoints_z[4] = 59.1055       

checkpoints_x[5] = -885.7850     
checkpoints_y[5] = -41.3927       
checkpoints_z[5] = 33.2347        				  

checkpoints_x[6] = -738.1151     
checkpoints_y[6] = 25.5550        
checkpoints_z[6] = 32.2336        

checkpoints_x[7] = -770.0811     
checkpoints_y[7] = 130.1324      
checkpoints_z[7] = 9.4651          

checkpoints_x[8] = -715.4454     
checkpoints_y[8] = 225.1288      
checkpoints_z[8] = 0.2899          

checkpoints_x[9] = -624.6024       
checkpoints_y[9] = 187.2405      
checkpoints_z[9] = 15.4381
										 
checkpoints_x[10] = -520.6511     		 
checkpoints_y[10] = 79.6011        		 
checkpoints_z[10] = 32.2117        

checkpoints_x[11] = -507.8417     
checkpoints_y[11] = -45.9077       
checkpoints_z[11] = 59.2556       

checkpoints_x[12] = -618.4406      
checkpoints_y[12] = -67.8995       
checkpoints_z[12] = 62.3489       

checkpoints_x[13] = -658.3503          
checkpoints_y[13] = 146.4855  
checkpoints_z[13] = 28.2693             

checkpoints_x[14] = -507.6872          
checkpoints_y[14] = 221.0444  
checkpoints_z[14] = 8.7633 									

checkpoints_x[15] = -329.7878          
checkpoints_y[15] = 173.9210  
checkpoints_z[15] = 5.5 

checkpoints_x[16] = -149.4881
checkpoints_y[16] = 189.6471
checkpoints_z[16] = 7.2644

checkpoints_x[17] = -38.4818            			   
checkpoints_y[17] = 140.8613           
checkpoints_z[17] = 2.1234  

checkpoints_x[18] =	-114.2310
checkpoints_y[18] = -132.3417
checkpoints_z[18] =	2.1236		

checkpoints_x[19] = 22.0727 
checkpoints_y[19] = -207.7681
checkpoints_z[19] = 0.5827

checkpoints_x[20] = 182.4970 
checkpoints_y[20] = -217.8004 
checkpoints_z[20] = 0.4216

checkpoints_x[21] = 207.4451 
checkpoints_y[21] = -298.9230 
checkpoints_z[21] = 0.4375					 
											 
checkpoints_x[22] = 279.6441 
checkpoints_y[22] = -378.3416
checkpoints_z[22] = 7.9003

checkpoints_x[23] = 419.4744  
checkpoints_y[23] = -302.5507          
checkpoints_z[23] = 6.4460 

checkpoints_x[24] = 573.5200  
checkpoints_y[24] = -202.8494          
checkpoints_z[24] = 13.7118             

checkpoints_x[25] = 748.4567  
checkpoints_y[25] = -150.8298          
checkpoints_z[25] = 18.3722             

checkpoints_x[26] = 820.4231   
checkpoints_y[26] = -111.5991             
checkpoints_z[26] = 23.2476              

checkpoints_x[27] = 1000.3245 
checkpoints_y[27] = -80.3516            
checkpoints_z[27] = 21.0326            

checkpoints_x[28] = 1187.3932 
checkpoints_y[28] = -83.8790            
checkpoints_z[28] = 35.2576

checkpoints_x[29] = 1265.8466  
checkpoints_y[29] = -153.0857          
checkpoints_z[29] = 36.8097 			   
										   
checkpoints_x[30] = 1434.7378  			   
checkpoints_y[30] = -214.9645          	   
checkpoints_z[30] = 7.3527 				   
										   
checkpoints_x[31] = 1548.9625  
checkpoints_y[31] = -146.2702             
checkpoints_z[31] = 16.4932             

checkpoints_x[32] = 1559.9829  
checkpoints_y[32] = 17.4680            
checkpoints_z[32] = 23.1563 

total_checkpoints = 33

total_racers = 4
racer_model[0] = WMYST
racer_model[1] = WMYBMX
racer_model[2] = special01
racer_model[3] = MALE01
racers_car_model[0] = ZR350
racers_car_model[1] = SULTAN
racers_car_model[2] = ELEGY
racers_car_model[3] = ZR350

racers_car_colour1[0] = 4 
racers_car_colour2[0] = 80 
racers_car_colour1[1] = 3 
racers_car_colour2[1] = 80  
racers_car_colour1[2] = 5 
racers_car_colour2[2] = 80 
break

case 9//19// dirt track 1 - country side ////////////////////////////////////////////////////////
checkpoints_x[0] = -796.8273
checkpoints_y[0] = -2468.5293 
checkpoints_z[0] = 83.6549 

checkpoints_x[1] = -938.5198 
checkpoints_y[1] = -2364.5115
checkpoints_z[1] = 57.0608 

checkpoints_x[2] = -1065.6564
checkpoints_y[2] = -2379.4363
checkpoints_z[2] = 46.5486

checkpoints_x[3] = -1247.8232
checkpoints_y[3] = -2313.5276
checkpoints_z[3] = 19.8007

checkpoints_x[4] = -1356.1370
checkpoints_y[4] = -2180.3655
checkpoints_z[4] = 21.3467

checkpoints_x[5] = -1541.3026
checkpoints_y[5] = -2154.7817
checkpoints_z[5] = 5.8607 

checkpoints_x[6] = -1674.8757
checkpoints_y[6] = -2235.1318
checkpoints_z[6] = 33.8691

checkpoints_x[7] = -1839.9366
checkpoints_y[7] = -2349.3684
checkpoints_z[7] = 31.9587 

checkpoints_x[8] = -1841.0366
checkpoints_y[8] = -2454.4004
checkpoints_z[8] = 27.4044

checkpoints_x[9] = -1702.0441
checkpoints_y[9] = -2587.9758
checkpoints_z[9] = 26.1706 

checkpoints_x[10] = -1508.3315 
checkpoints_y[10] = -2634.7327 
checkpoints_z[10] = 46.8766 

checkpoints_x[11] = -1310.9048 
checkpoints_y[11] = -2637.8320 
checkpoints_z[11] = 12.8080 

checkpoints_x[12] = -1122.6166 
checkpoints_y[12] = -2652.5916 
checkpoints_z[12] = 15.4117 

checkpoints_x[13] = -934.2363 
checkpoints_y[13] = -2668.4731 
checkpoints_z[13] = 80.3479 

checkpoints_x[14] = -753.5882 
checkpoints_y[14] = -2672.7854 
checkpoints_z[14] = 84.0788 

checkpoints_x[15] = -668.0180 
checkpoints_y[15] = -2496.9883 
checkpoints_z[15] = 37.5787 

checkpoints_x[16] = -550.4019 
checkpoints_y[16] = -2334.5386 
checkpoints_z[16] = 26.8502 

checkpoints_x[17] = -366.3848 
checkpoints_y[17] = -2257.2500 
checkpoints_z[17] = 41.7144 

checkpoints_x[18] = -276.1146 
checkpoints_y[18] = -2189.3635 
checkpoints_z[18] = 27.7264 

total_checkpoints = 19


total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = SANCHEZ
racers_car_model[1] = SANCHEZ
racers_car_model[2] = SANCHEZ
racers_car_model[3] = SANCHEZ
racers_car_model[4] = SANCHEZ
racers_car_model[5] = SANCHEZ
gosub get_random_racer_models
break


case 10//22///// cokerun dirtrack - buggy ///////////////////////////////////////////////
checkpoints_x[0] = -1936.7911 
checkpoints_y[0] = -2452.2354 
checkpoints_z[0] = 29.6377 

checkpoints_x[1] = -1862.5398 
checkpoints_y[1] = -2379.4365 
checkpoints_z[1] = 30.1425 

checkpoints_x[2] = -1690.1442//-1689.8959 
checkpoints_y[2] = -2303.8459//-2303.2747 
checkpoints_z[2] = 43.3626 //40.3353 

checkpoints_x[3] = -1676.3170 
checkpoints_y[3] = -2132.6992 
checkpoints_z[3] = 35.2082 

checkpoints_x[4] = -1855.4214//-1855.3040 
checkpoints_y[4] = -2087.1172//-2086.2729 
checkpoints_z[4] = 58.3697   //92.4466 

checkpoints_x[5] = -1919.8019 
checkpoints_y[5] = -1937.4572 
checkpoints_z[5] = 78.5313 

checkpoints_x[6] = -1749.3341 
checkpoints_y[6] = -1899.5192 
checkpoints_z[6] = 95.8199 

checkpoints_x[7] = -1571.2920//-1570.6925 
checkpoints_y[7] = -1904.1659//-1903.4506 
checkpoints_z[7] = 84.3076 //80.4284 

checkpoints_x[8] = -1483.7836 
checkpoints_y[8] = -1783.2861 
checkpoints_z[8] = 49.8768 

checkpoints_x[9] = -1429.8779//-1431.6093 
checkpoints_y[9] = -1915.6406//-1931.1265 
checkpoints_z[9] = 25.1751 //35.0593 

checkpoints_x[10] = -1301.8013//-1303.6959 
checkpoints_y[10] = -2084.0544//-2089.8992 
checkpoints_z[10] = 22.9116 //62.6247 

checkpoints_x[11] = -1269.9126//-1270.3809 
checkpoints_y[11] = -2248.7356//-2249.0642 
checkpoints_z[11] = 21.3152 //44.8726 

checkpoints_x[12] = -1146.5144 
checkpoints_y[12] = -2371.5076 
checkpoints_z[12] = 27.0467 

checkpoints_x[13] = -959.5773 
checkpoints_y[13] = -2306.2700 
checkpoints_z[13] = 56.1859 

checkpoints_x[14] = -867.9595 
checkpoints_y[14] = -2180.8906 
checkpoints_z[14] = 25.6567 

checkpoints_x[15] = -843.4177 
checkpoints_y[15] = -2018.7344 
checkpoints_z[15] = 20.9828 

checkpoints_x[16] = -767.7295 
checkpoints_y[16] = -1865.5333 
checkpoints_z[16] = 11.4149 

checkpoints_x[17] = -666.3682 
checkpoints_y[17] = -1996.2764 
checkpoints_z[17] = 23.9143 

checkpoints_x[18] = -486.6196 
checkpoints_y[18] = -2025.7653 
checkpoints_z[18] = 48.2450 

checkpoints_x[19] = -323.4612 
checkpoints_y[19] = -1914.2063 
checkpoints_z[19] = 11.1951 

total_checkpoints = 20

total_racers = 6
racer_model[0] = male01
racer_model[1] = male01
racer_model[2] = male01
racer_model[3] = male01
racer_model[4] = male01
racer_model[5] = male01
racers_car_model[0] = bandito 
racers_car_model[1] = bandito 
racers_car_model[2] = bandito 
racers_car_model[3] = bandito 
racers_car_model[4] = bandito 
racers_car_model[5] = bandito 
gosub get_random_racer_models
break



case 11//// GO-Kart Race ////////////////////////////////////////////////////////
checkpoints_x[0] = -2728.3093 
checkpoints_y[0] = -310.8731 
checkpoints_z[0] = 6.0391 

checkpoints_x[1] = -2668.6711 
checkpoints_y[1] = -251.6207 
checkpoints_z[1] = 5.4984 

checkpoints_x[2] = -2655.9775 
checkpoints_y[2] = -104.6751 
checkpoints_z[2] = 2.9982 

checkpoints_x[3] = -2460.8352 
checkpoints_y[3] = -69.9766 
checkpoints_z[3] = 31.2000 

checkpoints_x[4] = -2421.2627 
checkpoints_y[4] = 55.6089 
checkpoints_z[4] = 34.0156 

checkpoints_x[5] = -2545.2600 
checkpoints_y[5] = 137.9111 
checkpoints_z[5] = 15.2177 

checkpoints_x[6] = -2604.1365 
checkpoints_y[6] = 136.8579 
checkpoints_z[6] = 3.1797 

checkpoints_x[7] = -2558.6606 
checkpoints_y[7] = 245.6250 
checkpoints_z[7] = 10.7931 

checkpoints_x[8] = -2578.5039 
checkpoints_y[8] = 345.8331 
checkpoints_z[8] = 7.2168 

checkpoints_x[9] = -2706.2817 
checkpoints_y[9] = 317.6024 
checkpoints_z[9] = 3.1797 

checkpoints_x[10] = -2809.2441 
checkpoints_y[10] = 232.3965 
checkpoints_z[10] = 6.0313 

checkpoints_x[11] = -2757.7490 
checkpoints_y[11] = 138.2248 
checkpoints_z[11] = 5.9033 

checkpoints_x[12] = -2758.3901
checkpoints_y[12] = -43.9910
checkpoints_z[12] = 6.1263

checkpoints_x[13] = -2756.8923 
checkpoints_y[13] = -184.0135 
checkpoints_z[13] = 5.8843 

checkpoints_x[14] = -2812.4644 
checkpoints_y[14] = -299.5974 
checkpoints_z[14] = 6.0390 

checkpoints_x[15] = checkpoints_x[0] 
checkpoints_y[15] = checkpoints_y[0]
checkpoints_z[15] = checkpoints_z[0]
total_checkpoints = 16

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = kart
racers_car_model[1] = kart
racers_car_model[2] = kart
racers_car_model[3] = kart
racers_car_model[4] = kart
racers_car_model[5] = kart
gosub get_random_racer_models
break


//case 11//15// boat race 1 dinghy ////////////////////////////////////////////////////////
//checkpoints_x[0] = 2121.7205
//checkpoints_y[0] = -156.0080
//checkpoints_z[0] = 0.0 
//
//checkpoints_x[1] = 1843.6671 
//checkpoints_y[1] = -43.0750 
//checkpoints_z[1] = 0.0 
//
//checkpoints_x[2] = 1571.8040 
//checkpoints_y[2] = -170.2337 
//checkpoints_z[2] = 0.0 
//
//checkpoints_x[3] = 1272.0471 
//checkpoints_y[3] = -187.2620 
//checkpoints_z[3] = 0.0 
//
//checkpoints_x[4] = 980.5278 
//checkpoints_y[4] = -115.9946 
//checkpoints_z[4] = 0.0 
//
//checkpoints_x[5] = 698.4188 
//checkpoints_y[5] = -218.8346 
//checkpoints_z[5] = 0.0 
//
//checkpoints_x[6] = 404.4511 
//checkpoints_y[6] = -279.1968 
//checkpoints_z[6] = 0.0 
//
//checkpoints_x[7] = 142.0295 
//checkpoints_y[7] = -424.9876 
//checkpoints_z[7] = 0.0 
//
//checkpoints_x[8] = -79.2922 
//checkpoints_y[8] = -627.6002 
//checkpoints_z[8] = 0.0 
//
//checkpoints_x[9] = 25.2075 
//checkpoints_y[9] = -908.8446 
//checkpoints_z[9] = 0.0 
//
//checkpoints_x[10] = 73.3687 
//checkpoints_y[10] = -1205.0173 
//checkpoints_z[10] = 0.0 
//
//checkpoints_x[11] = 53.1958 
//checkpoints_y[11] = -1504.3722 
//checkpoints_z[11] = 0.0 
//
//checkpoints_x[12] = 111.5816 
//checkpoints_y[12] = -1798.8573 
//checkpoints_z[12] = 0.0 
//
//checkpoints_x[13] = 249.2093 
//checkpoints_y[13] = -1998.9181 
//checkpoints_z[13] = 0.0 
//
//total_checkpoints = 14
//
//total_racers = 4
//racer_model[0] = MALE01
//racer_model[1] = MALE01
//racer_model[2] = MALE01
//racer_model[3] = MALE01
//racers_car_model[0] = jetmax
//racers_car_model[1] = jetmax
//racers_car_model[2] = jetmax
//racers_car_model[3] = jetmax
//gosub get_random_racer_models
//break

//446, 	squalo
//452, 	speeder
//493, 	jetmax

case 12//0//san fran car race 1/////////////////////////////////////////////////////////
checkpoints_x[0] = -2657.1643 
checkpoints_y[0] = 1153.8717 
checkpoints_z[0] = 34.7396 

checkpoints_x[1] = -2593.2209 
checkpoints_y[1] = 1217.8544 
checkpoints_z[1] = 34.7402 

checkpoints_x[2] = -2312.2881 
checkpoints_y[2] = 1176.3868 
checkpoints_z[2] = 49.6080 

checkpoints_x[3] = -2157.1926 
checkpoints_y[3] = 1268.1965 
checkpoints_z[3] = 26.0127 

checkpoints_x[4] = -1959.5264 
checkpoints_y[4] = 1287.2050 
checkpoints_z[4] = 6.7514 

checkpoints_x[5] = -1741.0756//-1771.2837 
checkpoints_y[5] = 1325.2985//1349.0731 
checkpoints_z[5] = 6.0830//6.7539 

checkpoints_x[6] = -1759.8651 
checkpoints_y[6] = 1269.1467 
checkpoints_z[6] = 8.8598 

checkpoints_x[7] = -1884.2421 
checkpoints_y[7] = 1160.6906 
checkpoints_z[7] = 45.0107 

checkpoints_x[8] = -1897.1008 
checkpoints_y[8] = 960.1686 
checkpoints_z[8] = 34.7366 

checkpoints_x[9] = -1998.1781 
checkpoints_y[9] = 841.1865 
checkpoints_z[9] = 45.0219 

checkpoints_x[10] = -2189.2585 
checkpoints_y[10] = 808.5273 
checkpoints_z[10] = 56.0602 

checkpoints_x[11] = -2389.1855 
checkpoints_y[11] = 808.6409 
checkpoints_z[11] = 34.7495 

checkpoints_x[12] = -2589.6924 
checkpoints_y[12] = 808.5219 
checkpoints_z[12] = 49.5411 

checkpoints_x[13] = -2711.4875//-2690.2190 
checkpoints_y[13] = 812.8809//812.4327 
checkpoints_z[13] = 48.9309//49.5548 

checkpoints_x[14] = -2750.8938 
checkpoints_y[14] = 890.6993 
checkpoints_z[14] = 65.8952 

checkpoints_x[15] = -2653.9116 
checkpoints_y[15] = 1157.3455 
checkpoints_z[15] = 34.7386 

total_checkpoints = 16

total_racers = 6
lvar_int racer_model[16]
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
lvar_int racers_car_model[16]
racers_car_model[0] = JESTER
racers_car_model[1] = CLUB
racers_car_model[2] = JESTER
racers_car_model[3] = FLASH
racers_car_model[4] = URANUS
racers_car_model[5] = ALPHA
gosub get_random_racer_models
break




case 13//1//san fran car race 2/////////////////////////////////////////////////////////
checkpoints_x[0] = -1584.1415 
checkpoints_y[0] = 1053.4387 
checkpoints_z[0] = 6.8392 

checkpoints_x[1] = -1584.1279 
checkpoints_y[1] = 1150.2612 
checkpoints_z[1] = 6.8387 

checkpoints_x[2] = -1699.0767 
checkpoints_y[2] = 1312.0203 
checkpoints_z[2] = 6.8293 

checkpoints_x[3] = -1875.8906 
checkpoints_y[3] = 1352.0593 
checkpoints_z[3] = 6.8322 

checkpoints_x[4] = -2060.7412 
checkpoints_y[4] = 1274.4521 
checkpoints_z[4] = 8.0659 

checkpoints_x[5] = -2258.3064 
checkpoints_y[5] = 1257.6816 
checkpoints_z[5] = 42.6038 

checkpoints_x[6] = -2143.2812
checkpoints_y[6] = 1075.6525
checkpoints_z[6] = 79.1249

checkpoints_x[7] = -2143.1406 
checkpoints_y[7] = 896.6196 
checkpoints_z[7] = 79.6432 

checkpoints_x[8] = -2143.0837 
checkpoints_y[8] = 696.6971 
checkpoints_z[8] = 67.6451 

checkpoints_x[9] = -2146.7373 
checkpoints_y[9] = 532.8465 
checkpoints_z[9] = 34.8181 

checkpoints_x[10] = -2055.5637 
checkpoints_y[10] = 504.0781 
checkpoints_z[10] = 34.8168 

checkpoints_x[11] = -1968.1750 
checkpoints_y[11] = 605.5881 
checkpoints_z[11] = 34.8199 

checkpoints_x[12] = -1820.3933 
checkpoints_y[12] = 475.0548 
checkpoints_z[12] = 23.4295 

checkpoints_x[13] = -1876.9030 	   
checkpoints_y[13] = 288.4981 
checkpoints_z[13] = 32.6699 

checkpoints_x[14] = -1897.5850 
checkpoints_y[14] = 87.7896 
checkpoints_z[14] = 37.9384 

checkpoints_x[15] = -1911.1510 
checkpoints_y[15] = -214.2394 
checkpoints_z[15] = 38.0320 

checkpoints_x[16] = -1910.9742 
checkpoints_y[16] = -515.5727 
checkpoints_z[16] = 38.0323 

checkpoints_x[17] = -1910.6749 
checkpoints_y[17] = -816.3037 
checkpoints_z[17] = 44.7432 

checkpoints_x[18] = -1910.6584 
checkpoints_y[18] = -1117.4086 
checkpoints_z[18] = 38.0214 

checkpoints_x[19] = -1907.2764 
checkpoints_y[19] = -1317.6263 
checkpoints_z[19] = 39.3164 

checkpoints_x[20] = -1996.8965 
checkpoints_y[20] = -1289.2639 
checkpoints_z[20] = 37.3518 

checkpoints_x[21] = -2153.5298 
checkpoints_y[21] = -1031.5967 
checkpoints_z[21] = 32.3638 

checkpoints_x[22] = -2205.5667 
checkpoints_y[22] = -859.1885 
checkpoints_z[22] = 54.6872 

checkpoints_x[23] = -2202.6658 
checkpoints_y[23] = -759.2505 
checkpoints_z[23] = 62.3413 

checkpoints_x[24] = -2352.2554 
checkpoints_y[24] = -775.5399 
checkpoints_z[24] = 95.1597 

checkpoints_x[25] = -2421.5444 
checkpoints_y[25] = -610.6656 
checkpoints_z[25] = 132.3493 

checkpoints_x[26] = -2627.1377 
checkpoints_y[26] = -496.4363 
checkpoints_z[26] = 70.0900 

checkpoints_x[27] = -2351.2471 
checkpoints_y[27] = -460.0875 
checkpoints_z[27] = 79.9485 

checkpoints_x[28] = -2599.9963 
checkpoints_y[28] = -372.7368 
checkpoints_z[28] = 43.7794 

checkpoints_x[29] = -2702.3145 
checkpoints_y[29] = -530.1367 
checkpoints_z[29] = 12.4366 

checkpoints_x[30] = -2699.1968 
checkpoints_y[30] = -401.2050 
checkpoints_z[30] = 7.7008 

checkpoints_x[31] = -2210.2551 
checkpoints_y[31] = -348.0724 
checkpoints_z[31] = 36.8384 

checkpoints_x[32] = -1897.7339 
checkpoints_y[32] = -314.4976 			   
checkpoints_z[32] = 48.9384 

checkpoints_x[33] = -1890.3602 
checkpoints_y[33] = -45.4288 
checkpoints_z[33] = 38.0312 

checkpoints_x[34] = -1886.5565 
checkpoints_y[34] = 55.2930 
checkpoints_z[34] = 38.0326 

checkpoints_x[35] = -1848.0386 
checkpoints_y[35] = 242.5393 
checkpoints_z[35] = 30.8250 							    

checkpoints_x[36] = -1736.6350//-1753.1964   
checkpoints_y[36] = 316.5836//308.0770
checkpoints_z[36] = 6.0390//6.0390 

checkpoints_x[37] = -1682.9807 			  
checkpoints_y[37] = 367.1342 
checkpoints_z[37] = 6.0280 

checkpoints_x[38] = -1559.2928 
checkpoints_y[38] = 528.3320 
checkpoints_z[38] = 6.8409 

checkpoints_x[39] = -1535.5515 
checkpoints_y[39] = 827.4427 
checkpoints_z[39] = 6.8401 

checkpoints_x[40] = -1584.1294 
checkpoints_y[40] = 1115.6006 
checkpoints_z[40] = 6.8376 

total_checkpoints = 41

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = BUFFALO
racers_car_model[1] = SULTAN
racers_car_model[2] = SUPERGT
racers_car_model[3] = SUPERGT
racers_car_model[4] = ZR350
racers_car_model[5] = PHOENIX
gosub get_random_racer_models
break




case 14//2//san fran car race 3/////////////////////////////////////////////////////////
checkpoints_x[0] = -1761.6553 
checkpoints_y[0] = -606.3881 
checkpoints_z[0] = 15.8961 

checkpoints_x[1] = -1760.5399 
checkpoints_y[1] = -686.4133 
checkpoints_z[1] = 24.2389 

checkpoints_x[2] = -1502.8759 
checkpoints_y[2] = -820.2545 
checkpoints_z[2] = 61.3357 

checkpoints_x[3] = -1219.3323 
checkpoints_y[3] = -759.4814 
checkpoints_z[3] = 62.0285 

checkpoints_x[4] = -1096.1517 
checkpoints_y[4] = -489.1358 
checkpoints_z[4] = 32.7156 

checkpoints_x[5] = -911.5416 
checkpoints_y[5] = -455.9558 
checkpoints_z[5] = 26.7166 

checkpoints_x[6] = -621.3364 
checkpoints_y[6] = -393.1580 
checkpoints_z[6] = 22.1878 

checkpoints_x[7] = -395.5605 
checkpoints_y[7] = -533.5220 
checkpoints_z[7] = 17.6561 

checkpoints_x[8] = -372.2669 
checkpoints_y[8] = -819.4839 
checkpoints_z[8] = 28.1926 

checkpoints_x[9] = -584.5377 
checkpoints_y[9] = -1160.5718 
checkpoints_z[9] = 21.8153 

checkpoints_x[10] = -659.3533 
checkpoints_y[10] = -1548.8143 
checkpoints_z[10] = 22.1495 

checkpoints_x[11] = -717.1517 
checkpoints_y[11] = -1697.2047 
checkpoints_z[11] = 48.2586 

checkpoints_x[12] = -714.6181 
checkpoints_y[12] = -1395.0941 
checkpoints_z[12] = 60.5799 

checkpoints_x[13] = -764.0460 
checkpoints_y[13] = -1385.0271 
checkpoints_z[13] = 82.2146 

checkpoints_x[14] = -762.7014 
checkpoints_y[14] = -1685.0406 
checkpoints_z[14] = 96.9103 

checkpoints_x[15] = -945.4141 
checkpoints_y[15] = -1903.2269 
checkpoints_z[15] = 81.3436 

checkpoints_x[16] = -1104.5411 
checkpoints_y[16] = -2155.3254 
checkpoints_z[16] = 34.6761 

checkpoints_x[17] = -1186.2012 
checkpoints_y[17] = -2444.5413 
checkpoints_z[17] = 54.4532 

checkpoints_x[18] = -982.3237 
checkpoints_y[18] = -2611.6235 
checkpoints_z[18] = 86.2530 

checkpoints_x[19] = -713.2529 
checkpoints_y[19] = -2336.6172 
checkpoints_z[19] = 36.4201 

checkpoints_x[20] = -512.3461 
checkpoints_y[20] = -2166.4167 
checkpoints_z[20] = 53.5212 

checkpoints_x[21] = -258.5978 
checkpoints_y[21] = -2074.8718 
checkpoints_z[21] = 36.6480 

checkpoints_x[22] = -260.1367 
checkpoints_y[22] = -1777.1909 
checkpoints_z[22] = 7.5735 

checkpoints_x[23] = -57.3444 
checkpoints_y[23] = -1601.3746 
checkpoints_z[23] = 1.3776 

checkpoints_x[24] = -124.2960 
checkpoints_y[24] = -1466.5753 
checkpoints_z[24] = 2.4045 

checkpoints_x[25] = -143.4815 
checkpoints_y[25] = -1270.3171 
checkpoints_z[25] = 2.4040 

checkpoints_x[26] = -113.7683 //-120.6564 
checkpoints_y[26] = -998.8210 //-991.0425 
checkpoints_z[26] = 23.9178   //33.7625 

checkpoints_x[27] = -377.3843 
checkpoints_y[27] = -838.2166 
checkpoints_z[27] = 47.1444 

checkpoints_x[28] = -626.0056 
checkpoints_y[28] = -989.0120 
checkpoints_z[28] = 66.0268 

checkpoints_x[29] = -877.4561 
checkpoints_y[29] = -1097.4255 
checkpoints_z[29] = 96.1699 

checkpoints_x[30] = -925.9376 
checkpoints_y[30] = -1391.3534 
checkpoints_z[30] = 126.2905 

checkpoints_x[31] = -1219.1407 
checkpoints_y[31] = -1347.2485 
checkpoints_z[31] = 122.5235 

checkpoints_x[32] = -1407.7227 
checkpoints_y[32] = -1414.4497 
checkpoints_z[32] = 104.9232 

checkpoints_x[33] = -1577.0127 
checkpoints_y[33] = -1168.8024 
checkpoints_z[33] = 102.3586 

checkpoints_x[34] = -1626.0673 
checkpoints_y[34] = -882.9627 
checkpoints_z[34] = 97.5243 

checkpoints_x[35] = -1744.3031 
checkpoints_y[35] = -853.2858 
checkpoints_z[35] = 77.1834 

checkpoints_x[36] = -1684.6123 
checkpoints_y[36] = -1143.2905 
checkpoints_z[36] = 72.4364 

checkpoints_x[37] = -1549.5376 
checkpoints_y[37] = -1286.3467 
checkpoints_z[37] = 57.1135 

checkpoints_x[38] = -1525.7571 
checkpoints_y[38] = -1383.5315 
checkpoints_z[38] = 45.1796 

checkpoints_x[39] = -1626.1416 
checkpoints_y[39] = -1375.5872 
checkpoints_z[39] = 45.2062 

checkpoints_x[40] = -1800.1742 
checkpoints_y[40] = -1016.7898 
checkpoints_z[40] = 51.6322 

checkpoints_x[41] = -1817.4778 
checkpoints_y[41] = -617.6195 
checkpoints_z[41] = 15.9507 

total_checkpoints = 42

total_racers = 6

racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = TURISMO
racers_car_model[1] = CHEETAH
racers_car_model[2] = INFERNUS
racers_car_model[3] = TURISMO
racers_car_model[4] = BANSHEE
racers_car_model[5] = BULLET
gosub get_random_racer_models
break



case 15//8//SanFran to Vegas/////////////////////////////////////////////////////////
checkpoints_x[0] = -2001.8562 
checkpoints_y[0] = 1076.3024 
checkpoints_z[0] = 54.5637 

checkpoints_x[1] = -2302.3865 
checkpoints_y[1] = 1076.7543 
checkpoints_z[1] = 54.5859 

checkpoints_x[2] = -2596.7849 
checkpoints_y[2] = 1136.7277 
checkpoints_z[2] = 54.4297 

checkpoints_x[3] = -2673.7673 
checkpoints_y[3] = 1427.2927 
checkpoints_z[3] = 54.4271 

checkpoints_x[4] = -2673.7754 
checkpoints_y[4] = 1728.2385 
checkpoints_z[4] = 66.8937 

checkpoints_x[5] = -2673.8074 
checkpoints_y[5] = 2028.8955 
checkpoints_z[5] = 57.2377 

checkpoints_x[6] = -2731.7419 
checkpoints_y[6] = 2323.1829 
checkpoints_z[6] = 67.5411 

checkpoints_x[7] = -2622.0935 
checkpoints_y[7] = 2602.5972 
checkpoints_z[7] = 68.7000 

checkpoints_x[8] = -2322.7722 
checkpoints_y[8] = 2637.1003 
checkpoints_z[8] = 53.2135 

checkpoints_x[9] = -2027.1217 
checkpoints_y[9] = 2585.8655 
checkpoints_z[9] = 53.9804 

checkpoints_x[10] = -1885.6207 
checkpoints_y[10] = 2321.5217 
checkpoints_z[10] = 38.5395 

checkpoints_x[11] = -1675.2961 
checkpoints_y[11] = 2107.6970 
checkpoints_z[11] = 17.3995 

checkpoints_x[12] = -1629.9763 
checkpoints_y[12] = 1810.8279 
checkpoints_z[12] = 11.0590 

checkpoints_x[13] = -1347.9883 
checkpoints_y[13] = 1705.6704 
checkpoints_z[13] = 4.8179 

checkpoints_x[14] = -1125.8702 
checkpoints_y[14] = 1504.0950 
checkpoints_z[14] = 22.1877 

checkpoints_x[15] = -1010.7657 
checkpoints_y[15] = 1227.0442 
checkpoints_z[15] = 31.1773 

checkpoints_x[16] = -908.7908 
checkpoints_y[16] = 944.9431 
checkpoints_z[16] = 17.3370 

checkpoints_x[17] = -744.0402 
checkpoints_y[17] = 694.0286 
checkpoints_z[17] = 16.9982 

checkpoints_x[18] = -469.1425 
checkpoints_y[18] = 573.0610 
checkpoints_z[18] = 16.0649 

checkpoints_x[19] = -168.3162 
checkpoints_y[19] = 562.0268 
checkpoints_z[19] = 14.9046 

checkpoints_x[20] = 110.1296 
checkpoints_y[20] = 673.4105 
checkpoints_z[20] = 4.6336 

checkpoints_x[21] = 400.6810 
checkpoints_y[21] = 749.8367 
checkpoints_z[21] = 5.0607 

checkpoints_x[22] = 686.3097 
checkpoints_y[22] = 656.6995 
checkpoints_z[22] = 7.8963 

checkpoints_x[23] = 975.3585 
checkpoints_y[23] = 738.1249 
checkpoints_z[23] = 9.6719 

checkpoints_x[24] = 1242.0449 
checkpoints_y[24] = 876.2670 
checkpoints_z[24] = 13.1519 

checkpoints_x[25] = 1226.8104 
checkpoints_y[25] = 1169.8772 
checkpoints_z[25] = 5.8125 

checkpoints_x[26] = 1226.6638 
checkpoints_y[26] = 1469.9360 
checkpoints_z[26] = 5.7420 

total_checkpoints = 27

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = TURISMO
racers_car_model[1] = CHEETAH
racers_car_model[2] = INFERNUS
racers_car_model[3] = TURISMO
racers_car_model[4] = BANSHEE
racers_car_model[5] = BANSHEE
gosub get_random_racer_models
break




case 16//9//Desert race 1/////////////////////////////////////////////////////////
checkpoints_x[0] = -878.0113 
checkpoints_y[0] = 1947.4839 
checkpoints_z[0] = 59.1845 

checkpoints_x[1] = -857.6418 
checkpoints_y[1] = 1873.2499 
checkpoints_z[1] = 59.1830 

checkpoints_x[2] = -1030.6890 
checkpoints_y[2] = 1853.5463 
checkpoints_z[2] = 58.8558 

checkpoints_x[3] = -1093.1816 
checkpoints_y[3] = 1748.0228 
checkpoints_z[3] = 31.6318 

checkpoints_x[4] = -902.4880 
checkpoints_y[4] = 1687.6664 
checkpoints_z[4] = 26.2670 

checkpoints_x[5] = -847.1951 
checkpoints_y[5] = 1495.5806 
checkpoints_z[5] = 17.5443 

checkpoints_x[6] = -782.4052 
checkpoints_y[6] = 1305.8105 
checkpoints_z[6] = 12.6016 

checkpoints_x[7] = -616.3677 
checkpoints_y[7] = 1193.6448 
checkpoints_z[7] = 9.2126 

checkpoints_x[8] = -474.1805 
checkpoints_y[8] = 1052.0857 
checkpoints_z[8] = 10.0313 

checkpoints_x[9] = -319.1918 
checkpoints_y[9] = 925.6066 
checkpoints_z[9] = 10.3099 

checkpoints_x[10] = -267.4492 
checkpoints_y[10] = 825.0159 
checkpoints_z[10] = 13.2739 

checkpoints_x[11] = -188.4539 
checkpoints_y[11] = 1007.1902 
checkpoints_z[11] = 18.5885 

checkpoints_x[12] = -164.8873 
checkpoints_y[12] = 1198.2633 
checkpoints_z[12] = 18.5938 

checkpoints_x[13] = -142.1028 
checkpoints_y[13] = 1250.0220 
checkpoints_z[13] = 18.5869 

checkpoints_x[14] = -341.5205 
checkpoints_y[14] = 1265.6954 
checkpoints_z[14] = 22.1515 

checkpoints_x[15] = -440.0310 
checkpoints_y[15] = 1439.6689 
checkpoints_z[15] = 32.3340 

checkpoints_x[16] = -438.1716 
checkpoints_y[16] = 1639.6729 
checkpoints_z[16] = 34.6319 

checkpoints_x[17] = -381.6811 
checkpoints_y[17] = 1831.0260 
checkpoints_z[17] = 49.8567 

checkpoints_x[18] = -433.4627 
checkpoints_y[18] = 1883.9919 
checkpoints_z[18] = 59.8611 

checkpoints_x[19] = -465.4392 
checkpoints_y[19] = 1768.5317 
checkpoints_z[19] = 71.9576 

checkpoints_x[20] = -464.5399 
checkpoints_y[20] = 1968.4230 
checkpoints_z[20] = 81.2536 

checkpoints_x[21] = -411.0666 
checkpoints_y[21] = 2074.5962 
checkpoints_z[21] = 60.6433 

checkpoints_x[22] = -610.1716 
checkpoints_y[22] = 2050.8872 
checkpoints_z[22] = 59.1798 

checkpoints_x[23] = -790.6900 
checkpoints_y[23] = 2053.9099 
checkpoints_z[23] = 59.1798 

checkpoints_x[24] = checkpoints_x[0] 
checkpoints_y[24] = checkpoints_y[0] 
checkpoints_z[24] = checkpoints_z[0]

total_checkpoints = 25

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = PCJ600
racers_car_model[1] = FCR900
racers_car_model[2] = NRG500
racers_car_model[3] = PCJ600
racers_car_model[4] = FCR900
racers_car_model[5] = NRG500
gosub get_random_racer_models
break




case 17//10//Desert race 2////////////////////////////////////////////////////////
//checkpoints_x[0] = -374.9463 
//checkpoints_y[0] = 2201.4568 
//checkpoints_z[0] = 41.6510 
//
//checkpoints_x[1] = -390.8883 
//checkpoints_y[1] = 2265.4556 
//checkpoints_z[1] = 41.1335 
//
//checkpoints_x[2] = -376.4139 
//checkpoints_y[2] = 2363.9299 
//checkpoints_z[2] = 30.3884 
//
//checkpoints_x[3] = -407.7379 
//checkpoints_y[3] = 2457.2075 
//checkpoints_z[3] = 43.0772 
//
//checkpoints_x[4] = -503.3011 
//checkpoints_y[4] = 2429.9836 
//checkpoints_z[4] = 54.9013 
//
//checkpoints_x[5] = -601.4474 
//checkpoints_y[5] = 2441.7410 
//checkpoints_z[5] = 71.2696 
//
//checkpoints_x[6] = -678.2108 
//checkpoints_y[6] = 2506.3896 
//checkpoints_z[6] = 75.2021 
//
//checkpoints_x[7] = -729.4489 
//checkpoints_y[7] = 2592.5422 
//checkpoints_z[7] = 69.1407 
//
//checkpoints_x[8] = -700.4753 
//checkpoints_y[8] = 2687.3875 
//checkpoints_z[8] = 55.9636 
//
//checkpoints_x[9] = -790.3241 
//checkpoints_y[9] = 2730.0793 
//checkpoints_z[9] = 44.8626 
//
//checkpoints_x[10] = -890.4892 
//checkpoints_y[10] = 2724.2561 
//checkpoints_z[10] = 45.4101 
//
//checkpoints_x[11] = -989.8942 
//checkpoints_y[11] = 2712.7505 
//checkpoints_z[11] = 45.4074 
//
//checkpoints_x[12] = -1090.0802 
//checkpoints_y[12] = 2701.3955 
//checkpoints_z[12] = 45.4332 
//
//checkpoints_x[13] = -1189.7527 
//checkpoints_y[13] = 2689.6638 
//checkpoints_z[13] = 45.4260 
//
//checkpoints_x[14] = -1276.8923 
//checkpoints_y[14] = 2664.9668 
//checkpoints_z[14] = 48.5917 
//
//checkpoints_x[15] = -1351.6736 
//checkpoints_y[15] = 2650.0254 
//checkpoints_z[15] = 50.5879 
//
//checkpoints_x[16] = -1424.2471 
//checkpoints_y[16] = 2718.4275 
//checkpoints_z[16] = 61.7923 
//
//checkpoints_x[17] = -1523.9604 
//checkpoints_y[17] = 2730.3774 
//checkpoints_z[17] = 63.6405 
//
//checkpoints_x[18] = -1623.9374 
//checkpoints_y[18] = 2725.2644 
//checkpoints_z[18] = 57.2280 
//
//checkpoints_x[19] = -1724.0027 
//checkpoints_y[19] = 2731.8008 
//checkpoints_z[19] = 60.4080 
//
//checkpoints_x[20] = -1810.1735 
//checkpoints_y[20] = 2680.3145 
//checkpoints_z[20] = 55.9172 
//
//checkpoints_x[21] = -1852.3207 
//checkpoints_y[21] = 2589.7817 
//checkpoints_z[21] = 49.4334 
//
//checkpoints_x[22] = -1910.9666 
//checkpoints_y[22] = 2508.9009 
//checkpoints_z[22] = 43.9725 
//
//checkpoints_x[23] = -1993.4631 
//checkpoints_y[23] = 2452.0798 
//checkpoints_z[23] = 36.1913 
//
//checkpoints_x[24] = -1983.5367 
//checkpoints_y[24] = 2352.5730 
//checkpoints_z[24] = 28.0793 
//
//checkpoints_x[25] = -1988.1018 
//checkpoints_y[25] = 2253.1653 
//checkpoints_z[25] = 15.6906 
//
//checkpoints_x[26] = -1896.9801 
//checkpoints_y[26] = 2212.8940 
//checkpoints_z[26] = 5.6429 
//
//checkpoints_x[27] = -1826.5327 
//checkpoints_y[27] = 2141.4875 
//checkpoints_z[27] = 7.1591 
//
//checkpoints_x[28] = -1784.7640 
//checkpoints_y[28] = 2050.4395 
//checkpoints_z[28] = 8.3530 
//
//checkpoints_x[29] = -1795.0258 
//checkpoints_y[29] = 1950.9360 
//checkpoints_z[29] = 13.2365 
//
//checkpoints_x[30] = -1763.5988 
//checkpoints_y[30] = 1855.9103 
//checkpoints_z[30] = 19.0940 
//
//checkpoints_x[31] = -1667.6451 
//checkpoints_y[31] = 1827.8444 
//checkpoints_z[31] = 25.4007 
//
//checkpoints_x[32] = -1567.9170 
//checkpoints_y[32] = 1839.8270 
//checkpoints_z[32] = 26.3627 
//
//checkpoints_x[33] = -1467.8148 
//checkpoints_y[33] = 1846.8763 
//checkpoints_z[33] = 32.3573 
//
//checkpoints_x[34] = -1367.7511 
//checkpoints_y[34] = 1854.9581 
//checkpoints_z[34] = 36.8529 
//
//checkpoints_x[35] = -1270.7789 
//checkpoints_y[35] = 1829.6432 
//checkpoints_z[35] = 39.8193 
//
//checkpoints_x[36] = -1175.4015 
//checkpoints_y[36] = 1798.3138 
//checkpoints_z[36] = 39.8602 
//
//checkpoints_x[37] = -1078.8146 
//checkpoints_y[37] = 1824.6576 
//checkpoints_z[37] = 47.0318 
//
//checkpoints_x[38] = -981.6960 
//checkpoints_y[38] = 1843.2656 
//checkpoints_z[38] = 62.2027 
//
//checkpoints_x[39] = -894.8107 
//checkpoints_y[39] = 1793.2656 
//checkpoints_z[39] = 59.7473 
//
//checkpoints_x[40] = -861.4218 
//checkpoints_y[40] = 1887.7130 
//checkpoints_z[40] = 59.7415 
//
//checkpoints_x[41] = -869.7413 
//checkpoints_y[41] = 1987.3971 
//checkpoints_z[41] = 59.7353 
//
//checkpoints_x[42] = -784.1523 
//checkpoints_y[42] = 2040.2885 
//checkpoints_z[42] = 59.7408 
//
//checkpoints_x[43] = -684.8186 
//checkpoints_y[43] = 2054.9055 
//checkpoints_z[43] = 59.7346 
//
//checkpoints_x[44] = -589.2283 
//checkpoints_y[44] = 2023.5383 
//checkpoints_z[44] = 59.7390 
//
//checkpoints_x[45] = -497.0670 
//checkpoints_y[45] = 1984.5245 
//checkpoints_z[45] = 59.7569 
//
//checkpoints_x[46] = -469.7606 
//checkpoints_y[46] = 2060.1919 
//checkpoints_z[46] = 60.4341 
//
//checkpoints_x[47] = -422.4174 
//checkpoints_y[47] = 2143.4629 
//checkpoints_z[47] = 44.4062 
//
//checkpoints_x[48] = checkpoints_x[0] 
//checkpoints_y[48] = checkpoints_y[0] 
//checkpoints_z[48] = checkpoints_z[0]
//
//total_checkpoints = 49

checkpoints_x[0] = -375.6397 
checkpoints_y[0] = 2204.2168 
checkpoints_z[0] = 41.0938 

checkpoints_x[1] = -388.8497 
checkpoints_y[1] = 2258.0559 
checkpoints_z[1] = 40.9952 

checkpoints_x[2] = -410.6823 
checkpoints_y[2] = 2455.7473 
checkpoints_z[2] = 42.9124 

checkpoints_x[3] = -608.4162 
checkpoints_y[3] = 2447.1904 
checkpoints_z[3] = 71.7551 

checkpoints_x[4] = -715.8640 
checkpoints_y[4] = 2536.5945 
checkpoints_z[4] = 72.7592 

checkpoints_x[5] = -713.7043 
checkpoints_y[5] = 2697.4658 
checkpoints_z[5] = 53.2852 

checkpoints_x[6] = -912.5499 
checkpoints_y[6] = 2721.9873 
checkpoints_z[6] = 44.8627 

checkpoints_x[7] = -1112.7545 
checkpoints_y[7] = 2698.6506 
checkpoints_z[7] = 44.8750 

checkpoints_x[8] = -1238.1705 
checkpoints_y[8] = 2679.8606 
checkpoints_z[8] = 45.9321 

checkpoints_x[9] = -1359.5502 
checkpoints_y[9] = 2659.5569 
checkpoints_z[9] = 50.4683 

checkpoints_x[10] = -1545.7728 
checkpoints_y[10] = 2732.3538 
checkpoints_z[10] = 61.3062 

checkpoints_x[11] = -1746.0358 
checkpoints_y[11] = 2727.4485 
checkpoints_z[11] = 59.0648 

checkpoints_x[12] = -1860.2474 
checkpoints_y[12] = 2563.0430 
checkpoints_z[12] = 48.8929 

checkpoints_x[13] = -2002.1327 
checkpoints_y[13] = 2422.6030 
checkpoints_z[13] = 32.9752 

checkpoints_x[14] = -1961.4625 
checkpoints_y[14] = 2227.8911 
checkpoints_z[14] = 10.7077 

checkpoints_x[15] = -1807.1235 
checkpoints_y[15] = 2100.1001 
checkpoints_z[15] = 7.4268 

checkpoints_x[16] = -1782.0896 
checkpoints_y[16] = 1901.7749 
checkpoints_z[16] = 14.7248 

checkpoints_x[17] = -1591.9083 
checkpoints_y[17] = 1837.2899 
checkpoints_z[17] = 24.8515 

checkpoints_x[18] = -1392.0566 
checkpoints_y[18] = 1852.9677 
checkpoints_z[18] = 35.4945 

checkpoints_x[19] = -1199.3655 
checkpoints_y[19] = 1799.6382 
checkpoints_z[19] = 40.4346 

checkpoints_x[20] = -1008.3061 
checkpoints_y[20] = 1855.6041 
checkpoints_z[20] = 61.6784 

checkpoints_x[21] = -857.7028 
checkpoints_y[21] = 1874.0140 
checkpoints_z[21] = 59.1758 

checkpoints_x[22] = -758.5721 
checkpoints_y[22] = 2048.2322 
checkpoints_z[22] = 59.1866 

checkpoints_x[23] = -563.1158 
checkpoints_y[23] = 2005.5745 
checkpoints_z[23] = 59.2474 

checkpoints_x[24] = -468.9791 
checkpoints_y[24] = 2056.8108 
checkpoints_z[24] = 59.8750 

checkpoints_x[25] = -392.3245 
checkpoints_y[25] = 2154.4480 
checkpoints_z[25] = 42.1847 

checkpoints_x[26] = checkpoints_x[0] 
checkpoints_y[26] = checkpoints_y[0] 
checkpoints_z[26] = checkpoints_z[0]

total_checkpoints = 27

total_racers = 6
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racers_car_model[0] = PCJ600
racers_car_model[1] = FCR900
racers_car_model[2] = NRG500
racers_car_model[3] = PCJ600
racers_car_model[4] = FCR900
racers_car_model[5] = FCR900
gosub get_random_racer_models
break


case 18//21// vegas ring road /////////////////////////////////////////////////////////////////
checkpoints_x[0] = 1446.4259 
checkpoints_y[0] = 834.0658 
checkpoints_z[0] = 5.8125 

checkpoints_x[1] = 1747.2593 
checkpoints_y[1] = 834.0250 
checkpoints_z[1] = 8.6815 

checkpoints_x[2] = 2047.3347 
checkpoints_y[2] = 834.1916 
checkpoints_z[2] = 5.7422 

checkpoints_x[3] = 2348.7219 
checkpoints_y[3] = 834.1481 
checkpoints_z[3] = 5.7422 

checkpoints_x[4] = 2637.9202 
checkpoints_y[4] = 915.6277 
checkpoints_z[4] = 5.7498 

checkpoints_x[5] = 2726.8245 
checkpoints_y[5] = 1202.2665 
checkpoints_z[5] = 5.7422 

checkpoints_x[6] = 2726.6975 
checkpoints_y[6] = 1502.9668 
checkpoints_z[6] = 5.7422 

checkpoints_x[7] = 2726.9583 
checkpoints_y[7] = 1804.3431 
checkpoints_z[7] = 5.7393 

checkpoints_x[8] = 2726.5791 
checkpoints_y[8] = 2125.7964 
checkpoints_z[8] = 5.7269 

checkpoints_x[9] = 2705.1716 
checkpoints_y[9] = 2405.5667 
checkpoints_z[9] = 5.7277 

checkpoints_x[10] = 2489.2671 
checkpoints_y[10] = 2614.2732 
checkpoints_z[10] = 4.1679 

checkpoints_x[11] = 2188.4788 
checkpoints_y[11] = 2609.6289 
checkpoints_z[11] = 5.8164 

checkpoints_x[12] = 1898.3093 
checkpoints_y[12] = 2529.1553 
checkpoints_z[12] = 5.8125 

checkpoints_x[13] = 1603.5236 
checkpoints_y[13] = 2473.0833 
checkpoints_z[13] = 5.8516 

checkpoints_x[14] = 1305.1807 
checkpoints_y[14] = 2439.1897 
checkpoints_z[14] = 5.7422 

checkpoints_x[15] = 1208.2319 
checkpoints_y[15] = 2154.5139 
checkpoints_z[15] = 5.7422 

checkpoints_x[16] = 1208.3795 
checkpoints_y[16] = 1853.8372 
checkpoints_z[16] = 5.7422 

checkpoints_x[17] = 1208.3218 
checkpoints_y[17] = 1553.0247 
checkpoints_z[17] = 5.7422 

checkpoints_x[18] = 1208.1263 
checkpoints_y[18] = 1252.5565 
checkpoints_z[18] = 5.7498 

checkpoints_x[19] = 1229.1874 
checkpoints_y[19] = 952.9079 
checkpoints_z[19] = 5.8125 

checkpoints_x[20] = checkpoints_x[0] 
checkpoints_y[20] = checkpoints_y[0]
checkpoints_z[20] = checkpoints_z[0]

total_checkpoints = 21

total_racers = 6

racer_model[0] = male01
racer_model[1] = male01
racer_model[2] = male01
racer_model[3] = male01
racer_model[4] = male01
racer_model[5] = male01

racers_car_model[0] = TURISMO 
racers_car_model[1] = ELEGY 
racers_car_model[2] = CHEETAH 
racers_car_model[3] = INFERNUS 
racers_car_model[4] = ELEGY 
racers_car_model[5] = TURISMO 
gosub get_random_racer_models
break




case 19//23// plane race 1 over LA //////////////////////////////////////////////////////////
checkpoints_x[0] = 1796.9010 
checkpoints_y[0] = -2593.3291 
checkpoints_z[0] = 12.5982 

checkpoints_x[1] = 1596.9150 
checkpoints_y[1] = -2592.1233 
checkpoints_z[1] = 25.9969 

checkpoints_x[2] = 1425.4287 //1397.8796 
checkpoints_y[2] = -2549.1069 //-2573.5667 
checkpoints_z[2] = 37.8234//36.0608 

checkpoints_x[3] = 1280.5555 
checkpoints_y[3] = -2394.5862 
checkpoints_z[3] = 52.2127 

checkpoints_x[4] = 1296.1647 
checkpoints_y[4] = -2194.1313 
checkpoints_z[4] = 72.3519 

checkpoints_x[5] = 1317.0125 
checkpoints_y[5] = -1995.0265 
checkpoints_z[5] = 77.6570 

checkpoints_x[6] = 1385.2157 
checkpoints_y[6] = -1807.2498 
checkpoints_z[6] = 64.6153 

checkpoints_x[7] = 1501.2018 
checkpoints_y[7] = -1642.8923 
checkpoints_z[7] = 78.2774 

checkpoints_x[8] = 1597.7827 
checkpoints_y[8] = -1466.8529 
checkpoints_z[8] = 87.9007 

checkpoints_x[9] = 1621.6930 
checkpoints_y[9] = -1267.5903 
checkpoints_z[9] = 84.5704 

checkpoints_x[10] = 1592.6260 
checkpoints_y[10] = -1069.3135 
checkpoints_z[10] = 87.8825 

checkpoints_x[11] = 1477.0962 
checkpoints_y[11] = -905.3071 
checkpoints_z[11] = 103.4455 

checkpoints_x[12] = 1388.8838 
checkpoints_y[12] = -725.4239 
checkpoints_z[12] = 125.9394 

checkpoints_x[13] = 1320.7153 
checkpoints_y[13] = -536.3066 
checkpoints_z[13] = 127.7300 

checkpoints_x[14] = 1233.9475 
checkpoints_y[14] = -356.5205 
checkpoints_z[14] = 103.7008 

checkpoints_x[15] = 1235.2209 
checkpoints_y[15] = -155.8231 
checkpoints_z[15] = 83.5000 

checkpoints_x[16] = 1362.4542 
checkpoints_y[16] = -2.0532 
checkpoints_z[16] = 70.1118 

checkpoints_x[17] = 1562.0732 
checkpoints_y[17] = -27.0897 
checkpoints_z[17] = 64.6716 

checkpoints_x[18] = 1670.3130 
checkpoints_y[18] = -196.5335 
checkpoints_z[18] = 77.4671 

checkpoints_x[19] = 1698.6969 
checkpoints_y[19] = -394.7666 
checkpoints_z[19] = 84.5130 

checkpoints_x[20] = 1841.2386 
checkpoints_y[20] = -535.4985 
checkpoints_z[20] = 105.1396 

checkpoints_x[21] = 1999.7769 
checkpoints_y[21] = -658.8530 
checkpoints_z[21] = 125.3019 

checkpoints_x[22] = 2117.7710 
checkpoints_y[22] = -819.4944 
checkpoints_z[22] = 144.8639 

checkpoints_x[23] = 2200.1814 
checkpoints_y[23] = -1001.2698 
checkpoints_z[23] = 129.4889 

checkpoints_x[24] = 2265.8049 
checkpoints_y[24] = -1184.5841 
checkpoints_z[24] = 83.0669 

checkpoints_x[25] = 2296.1453 
checkpoints_y[25] = -1383.3942 
checkpoints_z[25] = 62.1655 

checkpoints_x[26] = 2321.6077 
checkpoints_y[26] = -1582.0300 
checkpoints_z[26] = 63.1144 

checkpoints_x[27] = 2400.4419 
checkpoints_y[27] = -1766.2267 
checkpoints_z[27] = 56.7954 

checkpoints_x[28] = 2525.2559 
checkpoints_y[28] = -1922.6801 
checkpoints_z[28] = 52.2529 

checkpoints_x[29] = 2593.7256 
checkpoints_y[29] = -2111.7336 
checkpoints_z[29] = 53.1139 

checkpoints_x[30] = 2575.0493 
checkpoints_y[30] = -2313.6313 
checkpoints_z[30] = 53.9683 

checkpoints_x[31] = 2455.3967 
checkpoints_y[31] = -2476.6331 
checkpoints_z[31] = 54.8922 

checkpoints_x[32] = 2264.0710 
checkpoints_y[32] = -2548.5916 
checkpoints_z[32] = 55.7578 

checkpoints_x[33] = 2065.2854 
checkpoints_y[33] = -2571.6355 
checkpoints_z[33] = 56.5966 

checkpoints_x[34] = 1862.5022 
checkpoints_y[34] = -2582.0991 
checkpoints_z[34] = 54.6357 

total_checkpoints = 35

total_racers = 1
racer_model[0] = male01
racer_model[1] = male01
racer_model[2] = male01
racer_model[3] = male01
racers_car_model[0] = rustler 
racers_car_model[1] = rustler 
racers_car_model[2] = rustler 
racers_car_model[3] = rustler 
checkpoint_type = CHECKPOINT_TORUS 
break




case 20//18///// plane race 2 over san fran and countryside ///////////////////////////////////////////////
checkpoints_x[0] = -1582.6702 
checkpoints_y[0] = -89.9953 
checkpoints_z[0] = 13.1683 

checkpoints_x[1] = -1439.1865 
checkpoints_y[1] = 53.2109 
checkpoints_z[1] = 30.0

checkpoints_x[2] = -1286.6859 
checkpoints_y[2] = 185.0573 
checkpoints_z[2] = 32.9885 

checkpoints_x[3] = -1091.8765 
checkpoints_y[3] = 137.3885 
checkpoints_z[3] = 46.7629 

checkpoints_x[4] = -1042.1692 
checkpoints_y[4] = -59.4087 
checkpoints_z[4] = 43.6030 

checkpoints_x[5] = -1047.5674 
checkpoints_y[5] = -259.0437 
checkpoints_z[5] = 23.8409 

checkpoints_x[6] = -1126.5594 
checkpoints_y[6] = -445.5607 
checkpoints_z[6] = 28.0502 

checkpoints_x[7] = -1261.2401 
checkpoints_y[7] = -598.8228 
checkpoints_z[7] = 37.4576 

checkpoints_x[8] = -1444.8026 
checkpoints_y[8] = -690.5997 
checkpoints_z[8] = 52.7263 

checkpoints_x[9] = -1649.0076 
checkpoints_y[9] = -681.2640 
checkpoints_z[9] = 60.8852 

checkpoints_x[10] = -1845.5848 
checkpoints_y[10] = -636.4395 
checkpoints_z[10] = 61.1217 

checkpoints_x[11] = -2044.8175 
checkpoints_y[11] = -658.4432 
checkpoints_z[11] = 71.2804 

checkpoints_x[12] = -2206.1648 
checkpoints_y[12] = -778.5996 
checkpoints_z[12] = 85.9739 

checkpoints_x[13] = -2240.9282 
checkpoints_y[13] = -976.7443 
checkpoints_z[13] = 75.2422 

checkpoints_x[14] = -2139.4800 
checkpoints_y[14] = -1148.6174 
checkpoints_z[14] = 61.3795 

checkpoints_x[15] = -2028.6338 
checkpoints_y[15] = -1315.7115 
checkpoints_z[15] = 40.6530 

checkpoints_x[16] = -1884.6228 
checkpoints_y[16] = -1459.3368 
checkpoints_z[16] = 37.0927 

checkpoints_x[17] = -1725.2833 
checkpoints_y[17] = -1581.1932 
checkpoints_z[17] = 51.3609 

checkpoints_x[18] = -1548.5010 
checkpoints_y[18] = -1676.2234 
checkpoints_z[18] = 63.2502 

checkpoints_x[19] = -1368.6127 
checkpoints_y[19] = -1769.1973 
checkpoints_z[19] = 52.0990 

checkpoints_x[20] = -1327.0420 
checkpoints_y[20] = -1980.8834 
checkpoints_z[20] = 35.4768 

checkpoints_x[21] = -1462.6591 
checkpoints_y[21] = -2110.3398 
checkpoints_z[21] = 22.6174 

checkpoints_x[22] = -1551.0675 
checkpoints_y[22] = -2287.2764 
checkpoints_z[22] = 56.7071 

checkpoints_x[23] = -1624.6454 
checkpoints_y[23] = -2464.3018 
checkpoints_z[23] = 115.5442 

checkpoints_x[24] = -1735.0121 
checkpoints_y[24] = -2633.0918 
checkpoints_z[24] = 101.7228 

checkpoints_x[25] = -1673.6176 
checkpoints_y[25] = -2817.3203 
checkpoints_z[25] = 50.4920 

checkpoints_x[26] = -1529.3984 
checkpoints_y[26] = -2958.8643 
checkpoints_z[26] = 35.2493 

checkpoints_x[27] = -1331.8695 
checkpoints_y[27] = -3016.8154 
checkpoints_z[27] = 36.5563 

checkpoints_x[28] = -1140.5167 
checkpoints_y[28] = -2957.8384 
checkpoints_z[28] = 35.4633 

checkpoints_x[29] = -1133.4834 
checkpoints_y[29] = -2757.1152 
checkpoints_z[29] = 30.2641 

checkpoints_x[30] = -1219.5931 
checkpoints_y[30] = -2575.6001 
checkpoints_z[30] = 35.8018 

checkpoints_x[31] = -1227.1932 
checkpoints_y[31] = -2375.0991 
checkpoints_z[31] = 31.7209 

checkpoints_x[32] = -1172.7458 
checkpoints_y[32] = -2182.8044 
checkpoints_z[32] = 45.2393 

checkpoints_x[33] = -1144.3121 
checkpoints_y[33] = -1988.0176 
checkpoints_z[33] = 83.1497 

checkpoints_x[34] = -1021.3864 
checkpoints_y[34] = -1832.8214 
checkpoints_z[34] = 115.2484 

checkpoints_x[35] = -943.5054 
checkpoints_y[35] = -1647.9922 
checkpoints_z[35] = 133.3276 

checkpoints_x[36] = -900.7032 
checkpoints_y[36] = -1450.5847 
checkpoints_z[36] = 142.0757 

checkpoints_x[37] = -884.4996 
checkpoints_y[37] = -1249.6418 
checkpoints_z[37] = 148.1781 

checkpoints_x[38] = -880.2889 
checkpoints_y[38] = -1048.9901 
checkpoints_z[38] = 158.8745 

checkpoints_x[39] = -936.6008 
checkpoints_y[39] = -855.3414 
checkpoints_z[39] = 160.1570 

checkpoints_x[40] = -972.9082 
checkpoints_y[40] = -660.9503 
checkpoints_z[40] = 116.3043 

checkpoints_x[41] = -980.3859 
checkpoints_y[41] = -464.2646 
checkpoints_z[41] = 79.1644 

checkpoints_x[42] = -818.3880 
checkpoints_y[42] = -348.3954 
checkpoints_z[42] = 51.4388 

checkpoints_x[43] = -617.8265 
checkpoints_y[43] = -330.2328 
checkpoints_z[43] = 37.1987 

checkpoints_x[44] = -415.4925 
checkpoints_y[44] = -339.8519 
checkpoints_z[44] = 28.2761 

checkpoints_x[45] = -248.3391 
checkpoints_y[45] = -451.8987 
checkpoints_z[45] = 18.5819 

checkpoints_x[46] = -192.9961 
checkpoints_y[46] = -643.9890 
checkpoints_z[46] = 8.8890 

checkpoints_x[47] = -128.7125 
checkpoints_y[47] = -835.6478 
checkpoints_z[47] = 24.9195 

checkpoints_x[48] = 63.3296 
checkpoints_y[48] = -892.0873 
checkpoints_z[48] = 40.4431 

checkpoints_x[49] = 236.1952 
checkpoints_y[49] = -791.0125 
checkpoints_z[49] = 46.7307 

checkpoints_x[50] = 298.5167 
checkpoints_y[50] = -600.2706 
checkpoints_z[50] = 53.5437 

checkpoints_x[51] = 326.4258 
checkpoints_y[51] = -400.9587 
checkpoints_z[51] = 50.4641 

checkpoints_x[52] = 308.5928 
checkpoints_y[52] = -201.9229 
checkpoints_z[52] = 40.3219 

checkpoints_x[53] = 244.4628 
checkpoints_y[53] = -8.2110 
checkpoints_z[53] = 31.0744 

checkpoints_x[54] = 129.2121 
checkpoints_y[54] = 155.3022 
checkpoints_z[54] = 27.6231 

checkpoints_x[55] = -37.7018 
checkpoints_y[55] = 268.3331 
checkpoints_z[55] = 26.3544 

checkpoints_x[56] = -224.3873 
checkpoints_y[56] = 341.0904 
checkpoints_z[56] = 28.1078 

checkpoints_x[57] = -420.1607 
checkpoints_y[57] = 382.1896 
checkpoints_z[57] = 29.8681 

checkpoints_x[58] = -622.4773 
checkpoints_y[58] = 381.9150 
checkpoints_z[58] = 31.6354 

checkpoints_x[59] = -822.8240 
checkpoints_y[59] = 370.0051 
checkpoints_z[59] = 33.4142 

checkpoints_x[60] = -1012.3494 
checkpoints_y[60] = 300.3856 
checkpoints_z[60] = 35.7468 

checkpoints_x[61] = -1193.1001 
checkpoints_y[61] = 214.7394 
checkpoints_z[61] = 38.0629 

total_checkpoints = 62

total_racers = 1
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racers_car_model[0] = STUNT 
racers_car_model[1] = STUNT 
racers_car_model[2] = STUNT 
racers_car_model[3] = STUNT 
checkpoint_type = CHECKPOINT_TORUS 
break




case 21//16// plane race 3 - over desert ////////////////////////////////////////////////////
checkpoints_x[0] = 366.9820 
checkpoints_y[0] = 2502.5947 
checkpoints_z[0] = 15.5571 

checkpoints_x[1] = 116.6520 
checkpoints_y[1] = 2502.9016 
checkpoints_z[1] = 47.5682 

checkpoints_x[2] = -133.6242 
checkpoints_y[2] = 2524.3916 
checkpoints_z[2] = 55.1281 

checkpoints_x[3] = -377.8474 
checkpoints_y[3] = 2588.0684 
checkpoints_z[3] = 67.6866 

checkpoints_x[4] = -604.1302 
checkpoints_y[4] = 2694.6387 
checkpoints_z[4] = 84.7931 

checkpoints_x[5] = -838.6850 
checkpoints_y[5] = 2779.9014 
checkpoints_z[5] = 69.2036 

checkpoints_x[6] = -1045.6289 
checkpoints_y[6] = 2639.2791 
checkpoints_z[6] = 69.0984 

checkpoints_x[7] = -984.3267 
checkpoints_y[7] = 2395.5078 
checkpoints_z[7] = 64.5187 

checkpoints_x[8] = -873.4733 
checkpoints_y[8] = 2171.1863 
checkpoints_z[8] = 64.5549 

checkpoints_x[9] = -770.1340 
checkpoints_y[9] = 1943.2484 
checkpoints_z[9] = 70.9270 

checkpoints_x[10] = -653.4092 
checkpoints_y[10] = 1720.4705 
checkpoints_z[10] = 47.6015 

checkpoints_x[11] = -578.5898 
checkpoints_y[11] = 1481.9722 
checkpoints_z[11] = 21.5084 

checkpoints_x[12] = -521.8337 
checkpoints_y[12] = 1238.4370 
checkpoints_z[12] = 11.6736 

checkpoints_x[13] = -469.9443 
checkpoints_y[13] = 995.7210 
checkpoints_z[13] = 51.4066 

checkpoints_x[14] = -525.4200 
checkpoints_y[14] = 750.7462 
checkpoints_z[14] = 44.2215 

checkpoints_x[15] = -664.0441 
checkpoints_y[15] = 541.0659 
checkpoints_z[15] = 15.9243 

checkpoints_x[16] = -914.7918 
checkpoints_y[16] = 536.2426 
checkpoints_z[16] = 11.8040 

checkpoints_x[17] = -1131.9183 
checkpoints_y[17] = 661.8135 
checkpoints_z[17] = 13.6564 

checkpoints_x[18] = -1309.8328 
checkpoints_y[18] = 839.3160 
checkpoints_z[18] = 10.7039 

checkpoints_x[19] = -1449.3723 
checkpoints_y[19] = 1045.7767 
checkpoints_z[19] = 31.9947 

checkpoints_x[20] = -1603.1224 
checkpoints_y[20] = 1243.2097 
checkpoints_z[20] = 38.6648 

checkpoints_x[21] = -1813.1013 
checkpoints_y[21] = 1379.4404 
checkpoints_z[21] = 28.4044 

checkpoints_x[22] = -2061.4885 
checkpoints_y[22] = 1418.5486 
checkpoints_z[22] = 22.0099 

checkpoints_x[23] = -2312.4692 
checkpoints_y[23] = 1430.6986 
checkpoints_z[23] = 42.3744 

checkpoints_x[24] = -2522.7451 
checkpoints_y[24] = 1564.1936 
checkpoints_z[24] = 75.9034 

checkpoints_x[25] = -2689.2380 
checkpoints_y[25] = 1753.7452 
checkpoints_z[25] = 99.5865 

checkpoints_x[26] = -2759.3706 
checkpoints_y[26] = 1994.2650 
checkpoints_z[26] = 110.8744 

checkpoints_x[27] = -2682.7717 
checkpoints_y[27] = 2233.0974 
checkpoints_z[27] = 113.4608 

checkpoints_x[28] = -2493.9070 
checkpoints_y[28] = 2397.5063 
checkpoints_z[28] = 102.0156 

checkpoints_x[29] = -2245.1086 
checkpoints_y[29] = 2418.7727 
checkpoints_z[29] = 83.5877 

checkpoints_x[30] = -1994.6051 
checkpoints_y[30] = 2407.9421 
checkpoints_z[30] = 87.4376 

checkpoints_x[31] = -1751.4714 
checkpoints_y[31] = 2349.7395 
checkpoints_z[31] = 90.2881 

checkpoints_x[32] = -1535.0994 
checkpoints_y[32] = 2217.6560 
checkpoints_z[32] = 75.7349 

checkpoints_x[33] = -1305.3025 
checkpoints_y[33] = 2118.7158 
checkpoints_z[33] = 74.7752 

checkpoints_x[34] = -1064.8070 
checkpoints_y[34] = 2187.7681 
checkpoints_z[34] = 66.2415 

checkpoints_x[35] = -814.3556 
checkpoints_y[35] = 2195.8723 
checkpoints_z[35] = 56.1521 

checkpoints_x[36] = -563.2742 
checkpoints_y[36] = 2203.3303 
checkpoints_z[36] = 60.9129 

checkpoints_x[37] = -313.3741 
checkpoints_y[37] = 2229.2600 
checkpoints_z[37] = 73.2015 

checkpoints_x[38] = -66.3647 
checkpoints_y[38] = 2178.0820 
checkpoints_z[38] = 71.9294 

checkpoints_x[39] = 186.4198 
checkpoints_y[39] = 2211.2131 
checkpoints_z[39] = 71.6732 

checkpoints_x[40] = 390.8383 
checkpoints_y[40] = 2356.5137 
checkpoints_z[40] = 65.1402 

checkpoints_x[41] = 639.1594 
checkpoints_y[41] = 2385.2598 
checkpoints_z[41] = 47.4816 

checkpoints_x[42] = 857.9940 
checkpoints_y[42] = 2510.1575 
checkpoints_z[42] = 50.2583 

checkpoints_x[43] = 929.9869 
checkpoints_y[43] = 2748.3516 
checkpoints_z[43] = 77.8011 

checkpoints_x[44] = 760.5828 
checkpoints_y[44] = 2935.7300 
checkpoints_z[44] = 91.5673 

checkpoints_x[45] = 512.0109 
checkpoints_y[45] = 2901.7397 
checkpoints_z[45] = 93.8596 

checkpoints_x[46] = 309.8430 
checkpoints_y[46] = 2752.8918 
checkpoints_z[46] = 93.5908 

checkpoints_x[47] = 153.0242 
checkpoints_y[47] = 2560.3210 
checkpoints_z[47] = 64.3547 

checkpoints_x[48] = 18.2657 
checkpoints_y[48] = 2347.9043 
checkpoints_z[48] = 66.4087 

checkpoints_x[49] = -99.4455 
checkpoints_y[49] = 2126.3430 
checkpoints_z[49] = 75.4819 

checkpoints_x[50] = -257.6325 
checkpoints_y[50] = 1931.1837 
checkpoints_z[50] = 75.4922 

checkpoints_x[51] = -392.7389 
checkpoints_y[51] = 1720.5944 
checkpoints_z[51] = 94.1061 

checkpoints_x[52] = -499.7809 
checkpoints_y[52] = 1492.5453 
checkpoints_z[52] = 58.1720 

checkpoints_x[53] = -687.7847 
checkpoints_y[53] = 1327.0256 
checkpoints_z[53] = 45.0028 

checkpoints_x[54] = -881.8704 
checkpoints_y[54] = 1169.7690 
checkpoints_z[54] = 68.6343 

checkpoints_x[55] = -1075.9196 
checkpoints_y[55] = 1008.1553 
checkpoints_z[55] = 61.9286 

checkpoints_x[56] = -1272.9574 
checkpoints_y[56] = 852.6403 
checkpoints_z[56] = 61.1137 

checkpoints_x[57] = -1457.5387 
checkpoints_y[57] = 680.1183 
checkpoints_z[57] = 69.2464 

checkpoints_x[58] = -1634.6477 
checkpoints_y[58] = 496.5454 
checkpoints_z[58] = 79.7593 

checkpoints_x[59] = -1839.8145 
checkpoints_y[59] = 342.0907 
checkpoints_z[59] = 77.0974 

checkpoints_x[60] = -2063.7295 
checkpoints_y[60] = 218.3637 
checkpoints_z[60] = 74.2748 

checkpoints_x[61] = -2305.2566 
checkpoints_y[61] = 145.8570 
checkpoints_z[61] = 72.5908 

checkpoints_x[62] = -2554.3037 
checkpoints_y[62] = 126.6046 
checkpoints_z[62] = 96.9355 

checkpoints_x[63] = -2745.5088 
checkpoints_y[63] = -33.5993 
checkpoints_z[63] = 80.1740 

checkpoints_x[64] = -2863.9419 
checkpoints_y[64] = -255.5460 
checkpoints_z[64] = 62.7643 

checkpoints_x[65] = -2912.2810 
checkpoints_y[65] = -500.7706 
checkpoints_z[65] = 51.8291 

checkpoints_x[66] = -2947.3884 
checkpoints_y[66] = -759.2774 
checkpoints_z[66] = 44.0041 

checkpoints_x[67] = -2962.4233 
checkpoints_y[67] = -1011.3326 
checkpoints_z[67] = 42.2517 

checkpoints_x[68] = -2955.5059 
checkpoints_y[68] = -1276.0228 
checkpoints_z[68] = 45.3020 

checkpoints_x[69] = -3000.9043 
checkpoints_y[69] = -1528.5626 
checkpoints_z[69] = 51.0040 

total_checkpoints = 70

total_racers = 1
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racers_car_model[0] = hydra 
racers_car_model[1] = hydra 
racers_car_model[2] = hydra 
racers_car_model[3] = hydra 
checkpoint_type = CHECKPOINT_TORUS
break


case 22//// heli race 1 ////////////////////////////////////////////////////////
checkpoints_x[0] = 	381.9952 //365.2745
checkpoints_y[0] = 	2542.0879 //2536.9697
checkpoints_z[0] = 	15.6876//15.9518 

checkpoints_x[1] = 	304.6997
checkpoints_y[1] = 	2547.0571
checkpoints_z[1] = 	37.9750 

checkpoints_x[2] = 	-97.4654
checkpoints_y[2] = 	2554.8413
checkpoints_z[2] = 	32.4746 

checkpoints_x[3] = 	-102.6455
checkpoints_y[3] = 	2699.2356
checkpoints_z[3] = 	63.5945

checkpoints_x[4] = 	-221.9480
checkpoints_y[4] = 	2718.0803
checkpoints_z[4] = 	82.3850

checkpoints_x[5] = 	-418.3279 
checkpoints_y[5] = 	2691.0811 
checkpoints_z[5] = 	78.5077 

checkpoints_x[6] = 	-392.2611 
checkpoints_y[6] = 	2492.1931 
checkpoints_z[6] = 	84.2570 

checkpoints_x[7] = 	-360.1944 
checkpoints_y[7] = 	2294.6384 
checkpoints_z[7] = 	79.3440 

checkpoints_x[8] =  -238.1485 
checkpoints_y[8] = 	2230.0068 
checkpoints_z[8] = 	81.9684 

checkpoints_x[9] =  -287.1143 
checkpoints_y[9] = 	2091.0303 
checkpoints_z[9] = 	70.2663 

checkpoints_x[10] = -481.0875 
checkpoints_y[10] = 2051.7400 
checkpoints_z[10] = 76.2466 

checkpoints_x[11] = -665.1192 
checkpoints_y[11] = 2131.2520 
checkpoints_z[11] = 77.2696 

checkpoints_x[12] = -845.4529 
checkpoints_y[12] = 2218.8611 
checkpoints_z[12] = 80.0327 

checkpoints_x[13] = -1044.8663
checkpoints_y[13] = 2207.6155 
checkpoints_z[13] = 67.0078 

checkpoints_x[14] = -1234.2344
checkpoints_y[14] = 2142.3931 
checkpoints_z[14] = 66.1648 

checkpoints_x[15] = -1424.9308 
checkpoints_y[15] = 2205.6995 
checkpoints_z[15] = 70.8862 

checkpoints_x[16] = -1571.2373//-1556.4469 
checkpoints_y[16] = 2247.7661 //2356.8413 
checkpoints_z[16] = 65.1073 //67.7106 

checkpoints_x[17] = -1745.6370 
checkpoints_y[17] = 2292.5112 
checkpoints_z[17] = 49.3695 

checkpoints_x[18] = -1945.6023 
checkpoints_y[18] = 2285.9128 
checkpoints_z[18] = 44.2433 

checkpoints_x[19] = -1953.8809 
checkpoints_y[19] = 2484.8774 
checkpoints_z[19] = 63.2602 

checkpoints_x[20] = -1895.0948 
checkpoints_y[20] = 2633.0344 
checkpoints_z[20] = 74.4549 

checkpoints_x[21] = -1706.8728 
checkpoints_y[21] = 2703.2317 
checkpoints_z[21] = 72.8798 

checkpoints_x[22] = -1505.7236 
checkpoints_y[22] = 2721.0344 
checkpoints_z[22] = 69.9706 

checkpoints_x[23] = -1305.5951  
checkpoints_y[23] = 2708.3213 
checkpoints_z[23] = 66.4619 

checkpoints_x[24] = -1105.8900  
checkpoints_y[24] = 2731.6108 
checkpoints_z[24] = 62.9933 

checkpoints_x[25] = -905.1519  
checkpoints_y[25] = 2728.8303 
checkpoints_z[25] = 64.0124 

checkpoints_x[26] = -704.1036  
checkpoints_y[26] = 2733.8982 
checkpoints_z[26] = 66.2347 

total_checkpoints = 27

total_racers = 1
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racers_car_model[0] = MAVERICK
racers_car_model[1] = MAVERICK
racers_car_model[2] = MAVERICK
racers_car_model[3] = MAVERICK
checkpoint_type = CHECKPOINT_TORUS 
break

case 23//// heli race 2 ////////////////////////////////////////////////////////
checkpoints_x[0] = -1962.8712 
checkpoints_y[0] = 628.0812 
checkpoints_z[0] = 150.2844 

checkpoints_x[1] = -1848.3406 
checkpoints_y[1] = 531.9294 
checkpoints_z[1] = 86.1743 

checkpoints_x[2] = -1827.9133 
checkpoints_y[2] = 399.3668 
checkpoints_z[2] = 29.9100 

checkpoints_x[3] = -1751.3608 
checkpoints_y[3] = 326.9781 
checkpoints_z[3] = 25.4958 

checkpoints_x[4] = -1647.9576 
checkpoints_y[4] = 412.8291 
checkpoints_z[4] = 26.6578 

checkpoints_x[5] = -1560.5126 
checkpoints_y[5] = 532.7433 
checkpoints_z[5] = 18.6913 

checkpoints_x[6] = -1572.1968 
checkpoints_y[6] = 638.3369 
checkpoints_z[6] = 25.3806 

checkpoints_x[7] = -1700.5547 
checkpoints_y[7] = 706.7762 
checkpoints_z[7] = 53.3757 

checkpoints_x[8] = -1715.5071 
checkpoints_y[8] = 777.6501 
checkpoints_z[8] = 45.9312 

checkpoints_x[9] = -1715.9783 
checkpoints_y[9] = 978.3165 
checkpoints_z[9] = 51.9890 

checkpoints_x[10] = -1714.9581 
checkpoints_y[10] = 1178.5619 
checkpoints_z[10] = 54.9734 

checkpoints_x[11] = -1912.9225 
checkpoints_y[11] = 1146.2551 
checkpoints_z[11] = 56.3473 

checkpoints_x[12] = -2003.2480 
checkpoints_y[12] = 982.3228 
checkpoints_z[12] = 58.8190 

checkpoints_x[13] = -2006.1685 
checkpoints_y[13] = 810.2207 
checkpoints_z[13] = 54.1756 

checkpoints_x[14] = -1959.2341 
checkpoints_y[14] = 713.3063 
checkpoints_z[14] = 75.2571 

checkpoints_x[15] = -1793.0067 
checkpoints_y[15] = 730.7606 
checkpoints_z[15] = 44.6388 

checkpoints_x[16] = -1593.7314 
checkpoints_y[16] = 732.4928 
checkpoints_z[16] = 20.5721 

checkpoints_x[17] = -1533.4238 
checkpoints_y[17] = 923.6081 
checkpoints_z[17] = 29.4493 

checkpoints_x[18] = -1489.1721 
checkpoints_y[18] = 1118.7367 
checkpoints_z[18] = 17.2211 

checkpoints_x[19] = -1605.0448 
checkpoints_y[19] = 1284.0444 
checkpoints_z[19] = 27.5543 

checkpoints_x[20] = -1787.9211 
checkpoints_y[20] = 1365.8104 
checkpoints_z[20] = 32.2595 

checkpoints_x[21] = -1986.4290 
checkpoints_y[21] = 1319.3376 
checkpoints_z[21] = 23.0384 

checkpoints_x[22] = -2185.9966 
checkpoints_y[22] = 1345.1117 
checkpoints_z[22] = 19.4206 

checkpoints_x[23] = -2384.5986 
checkpoints_y[23] = 1380.5878 
checkpoints_z[23] = 17.9485 

checkpoints_x[24] = -2537.7593 
checkpoints_y[24] = 1369.6406 
checkpoints_z[24] = 23.8562 

checkpoints_x[25] = -2719.3149 
checkpoints_y[25] = 1345.6749 
checkpoints_z[25] = 32.2039 

checkpoints_x[26] = -2875.1421 
checkpoints_y[26] = 1245.7825 
checkpoints_z[26] = 20.7763 

total_checkpoints = 27

total_racers = 1
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racers_car_model[0] = vcnmav
racers_car_model[1] = vcnmav
racers_car_model[2] = vcnmav
racers_car_model[3] = vcnmav
checkpoint_type = CHECKPOINT_TORUS 
break

case 24//// heli race 3 ////////////////////////////////////////////////////////
checkpoints_x[0] = 1765.6085 
checkpoints_y[0] = -2285.9114 
checkpoints_z[0] = 25.9621 

checkpoints_x[1] = 1764.2942 
checkpoints_y[1] = -2084.3706 
checkpoints_z[1] = 26.0431 

checkpoints_x[2] = 1649.4017 
checkpoints_y[2] = -1920.2314 
checkpoints_z[2] = 40.6173 

checkpoints_x[3] = 1552.0238 
checkpoints_y[3] = -1745.2733 
checkpoints_z[3] = 30.0462 

checkpoints_x[4] = 1419.0309 
checkpoints_y[4] = -1595.6182 
checkpoints_z[4] = 25.4285 

checkpoints_x[5] = 1402.0891 
checkpoints_y[5] = -1441.6224 
checkpoints_z[5] = 18.2271 

checkpoints_x[6] = 1545.3347 
checkpoints_y[6] = -1431.1941 
checkpoints_z[6] = 36.4370 

checkpoints_x[7] = 1653.4458 
checkpoints_y[7] = -1305.8412 
checkpoints_z[7] = 49.6078 

checkpoints_x[8] = 1786.0935 
checkpoints_y[8] = -1276.7664 
checkpoints_z[8] = 22.6321 

checkpoints_x[9] = 1901.0399 
checkpoints_y[9] = -1221.9493 
checkpoints_z[9] = 34.6990 

checkpoints_x[10] = 1848.2241 
checkpoints_y[10] = -1097.6525 
checkpoints_z[10] = 46.0898 

checkpoints_x[11] = 1649.3767 
checkpoints_y[11] = -1085.2963 
checkpoints_z[11] = 36.7768 

checkpoints_x[12] = 1487.2054 
checkpoints_y[12] = -970.0334 
checkpoints_z[12] = 60.1281 

checkpoints_x[13] = 1304.2201 
checkpoints_y[13] = -1038.9294 
checkpoints_z[13] = 39.8547 

checkpoints_x[14] = 1215.7979 
checkpoints_y[14] = -1218.8619 
checkpoints_z[14] = 27.9230 

checkpoints_x[15] = 1302.6127 
checkpoints_y[15] = -1399.1266 
checkpoints_z[15] = 26.3812 

checkpoints_x[16] = 1437.0153 
checkpoints_y[16] = -1321.4310 
checkpoints_z[16] = 45.3960 

checkpoints_x[17] = 1609.0900 
checkpoints_y[17] = -1299.4969 
checkpoints_z[17] = 25.0714 

checkpoints_x[18] = 1791.4205 
checkpoints_y[18] = -1381.3638 
checkpoints_z[18] = 35.1700 

checkpoints_x[19] = 1827.0215 
checkpoints_y[19] = -1567.2794 
checkpoints_z[19] = 22.9243 

checkpoints_x[20] = 1628.1960 
checkpoints_y[20] = -1600.5996 
checkpoints_z[20] = 38.8226 

checkpoints_x[21] = 1450.9086 
checkpoints_y[21] = -1694.8391 
checkpoints_z[21] = 25.2192 

checkpoints_x[22] = 1389.8123 
checkpoints_y[22] = -1852.9839 
checkpoints_z[22] = 24.9970 

checkpoints_x[23] = 1206.6090 
checkpoints_y[23] = -1933.7227 
checkpoints_z[23] = 57.1028 

checkpoints_x[24] = 1053.4609 
checkpoints_y[24] = -2063.9326 
checkpoints_z[24] = 70.7254 

checkpoints_x[25] = 1048.6985 
checkpoints_y[25] = -2265.5034 
checkpoints_z[25] = 47.2394 

checkpoints_x[26] = 1224.4021 
checkpoints_y[26] = -2359.4944 
checkpoints_z[26] = 28.5888 

checkpoints_x[27] = 1425.6099 
checkpoints_y[27] = -2360.0474 
checkpoints_z[27] = 28.3338 

total_checkpoints = 28

total_racers = 1
racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racers_car_model[0] = hunter
racers_car_model[1] = hunter
racers_car_model[2] = hunter
racers_car_model[3] = hunter
checkpoint_type = CHECKPOINT_TORUS 
break


case NASCAR_RACE// NASCAR track - oval ring ////////////////////////////////////////////////////////
//checkpoints_x[0] = -1380.0 
//checkpoints_y[0] = 914.5 
//checkpoints_z[0] = 1040.2 
//
//checkpoints_x[1] = -1351.0 
//checkpoints_y[1] = 914.5 
//checkpoints_z[1] = 1040.2
//
//checkpoints_x[2] = -1257.9135
//checkpoints_y[2] = 1003.5409
//checkpoints_z[2] = 1039.6945
//
//checkpoints_x[3] = -1320.4248
//checkpoints_y[3] = 1067.4575
//checkpoints_z[3] = 1039.4650
//
//checkpoints_x[4] = -1475.5144 
//checkpoints_y[4] = 1067.5107 
//checkpoints_z[4] = 1039.3875 
//
//checkpoints_x[5] = -1520.7008 
//checkpoints_y[5] = 952.5261 
//checkpoints_z[5] = 1039.5588 
//
//checkpoints_x[6] = checkpoints_x[0] 
//checkpoints_y[6] = checkpoints_y[0]
//checkpoints_z[6] = checkpoints_z[0]
//
//lvar_float respawn_x respawn_y respawn_z respawn_h
//respawn_x =	-1410.4200
//respawn_y =	895.6823
//respawn_z =	1034.5179
//respawn_h =	292.4636
//
//total_checkpoints = 7
//
//interior_race = 15



checkpoints_x[0] = -1398.3552 
checkpoints_y[0] = -197.2079 
checkpoints_z[0] = 1043.1188 

checkpoints_x[1] = -1398.9879 
checkpoints_y[1] = -188.7892 
checkpoints_z[1] = 1043.2023 

checkpoints_x[2] = -1465.9392
checkpoints_y[2] = -134.0836
checkpoints_z[2] = 1046.0107

checkpoints_x[3] = -1530.1384 
checkpoints_y[3] = -193.3988 
checkpoints_z[3] = 1050.7562 

checkpoints_x[4] = -1415.6857 
checkpoints_y[4] = -274.1765 
checkpoints_z[4] = 1051.1486 

checkpoints_x[5] = -1305.1780 
checkpoints_y[5] = -143.5283
checkpoints_z[5] = 1050.1250 

checkpoints_x[6] = -1302.7737 
checkpoints_y[6] = -268.6358 
checkpoints_z[6] = 1048.4873 

checkpoints_x[7] = checkpoints_x[0]
checkpoints_y[7] = checkpoints_y[0]
checkpoints_z[7] = checkpoints_z[0]

lvar_float respawn_x respawn_y respawn_z respawn_h
respawn_x =	-1406.3274
respawn_y =	-265.7914
respawn_z =	1042.6562
respawn_h =	346.3297

total_checkpoints = 8

interior_race = 7

total_laps = 12
total_racers = 12

racer_model[0] = MALE01
racer_model[1] = MALE01
racer_model[2] = MALE01
racer_model[3] = MALE01
racer_model[4] = MALE01
racer_model[5] = MALE01
racer_model[6] = MALE01
racer_model[7] = MALE01
racer_model[8] = MALE01
racer_model[9] = MALE01
racer_model[10] = MALE01
racer_model[11] = MALE01
racer_model[12] = MALE01
racer_model[13] = MALE01
racer_model[14] = MALE01
racer_model[15] = MALE01
racers_car_model[0] = HOTRINB
racers_car_model[1] = HOTRINA
racers_car_model[2] = HOTRING
racers_car_model[3] = HOTRINB
racers_car_model[4] = HOTRINA
racers_car_model[5] = HOTRING
racers_car_model[6] = HOTRINA
racers_car_model[7] = HOTRING
racers_car_model[8] = HOTRINB
racers_car_model[9] = HOTRING
racers_car_model[10] = HOTRINB
racers_car_model[11] = HOTRINB
racers_car_model[12] = HOTRING
racers_car_model[13] = HOTRINA
racers_car_model[14] = HOTRING
racers_car_model[15] = HOTRINA
break




case DIRTBIKE_STADIUM// dirtbike stadium race //////////////////////////////////////////////////////////
//checkpoints_x[0] = -1384.8257 
//checkpoints_y[0] = -601.2573 
//checkpoints_z[0] = 1052.9121 
//
//checkpoints_x[1] = -1466.6851 
//checkpoints_y[1] = -601.0947 
//checkpoints_z[1] = 1054.7152 
//
//checkpoints_x[2] = -1523.3116 
//checkpoints_y[2] = -638.0695 
//checkpoints_z[2] = 1050.2198 
//
//checkpoints_x[3] = -1500.4653 
//checkpoints_y[3] = -720.0493 
//checkpoints_z[3] = 1049.7407 
//
//checkpoints_x[4] = -1387.4237 
//checkpoints_y[4] = -740.8824 
//checkpoints_z[4] = 1049.7665 
//
//checkpoints_x[5] = -1309.2252 
//checkpoints_y[5] = -722.5314 
//checkpoints_z[5] = 1049.8013 
//
//checkpoints_x[6] = -1377.7024 
//checkpoints_y[6] = -672.3581 
//checkpoints_z[6] = 1051.7253 
//
//checkpoints_x[7] = -1494.4806 
//checkpoints_y[7] = -640.9672 
//checkpoints_z[7] = 1050.8016 
//
//checkpoints_x[8] = -1447.4924 
//checkpoints_y[8] = -680.9002 
//checkpoints_z[8] = 1048.7092 
//
//checkpoints_x[9] = -1388.9855 
//checkpoints_y[9] = -722.5293 
//checkpoints_z[9] = 1051.4194 
//
//checkpoints_x[10] = -1370.9406 
//checkpoints_y[10] = -690.9001 
//checkpoints_z[10] = 1050.6930 
//
//checkpoints_x[11] = -1401.2961 
//checkpoints_y[11] = -636.0718 
//checkpoints_z[11] = 1046.9365 
//
//checkpoints_x[12] = -1309.1252 
//checkpoints_y[12] = -646.6835 
//checkpoints_z[12] = 1052.2255 
//
//checkpoints_x[13] = checkpoints_x[0]
//checkpoints_y[13] = checkpoints_y[0]
//checkpoints_z[13] = checkpoints_z[0]
//
//respawn_x =	-1344.1630 
//respawn_y =	-598.8836 
//respawn_z =	1051.8291 
//respawn_h =	103.3822
//
//total_checkpoints = 14

checkpoints_x[0] = -1354.3584 
checkpoints_y[0] = -590.5628
checkpoints_z[0] = 1055.4529

checkpoints_x[1] = -1431.1766 
checkpoints_y[1] = -588.3124
checkpoints_z[1] = 1054.5120

checkpoints_x[2] = -1516.8740
checkpoints_y[2] = -635.0942
checkpoints_z[2] = 1050.2751

checkpoints_x[3] = -1500.6779
checkpoints_y[3] = -719.0175 
checkpoints_z[3] = 1051.6433 

checkpoints_x[4] = -1387.5020
checkpoints_y[4] = -743.1501 
checkpoints_z[4] = 1051.0165 

checkpoints_x[5] = -1295.5396
checkpoints_y[5] = -705.3506 
checkpoints_z[5] = 1055.2552 

checkpoints_x[6] = -1365.3123
checkpoints_y[6] = -666.0998 
checkpoints_z[6] = 1055.0581 

checkpoints_x[7] = -1486.5000
checkpoints_y[7] = -636.4586 
checkpoints_z[7] = 1052.2252 

checkpoints_x[8] = -1447.8599
checkpoints_y[8] = -690.3426 
checkpoints_z[8] = 1052.8342 

checkpoints_x[9] = -1389.9077
checkpoints_y[9] = -720.9943 
checkpoints_z[9] = 1055.1191 

checkpoints_x[10] = -1370.6849
checkpoints_y[10] = -687.8977 
checkpoints_z[10] = 1053.7834 

checkpoints_x[11] = -1399.1357
checkpoints_y[11] = -635.8359 
checkpoints_z[11] = 1051.0432 

checkpoints_x[12] = -1308.6388
checkpoints_y[12] = -649.4201 
checkpoints_z[12] = 1054.9724 

checkpoints_x[13] = checkpoints_x[0]
checkpoints_y[13] = checkpoints_y[0]
checkpoints_z[13] = checkpoints_z[0]

respawn_x =	-1344.1630 
respawn_y =	-598.8836 
respawn_z =	1051.8291 
respawn_h =	103.3822

total_checkpoints = 14


interior_race = 4

total_laps = 6
total_racers = 12

racer_model[0] = WMYCR
racer_model[1] = DNMOLC2
racer_model[2] = MALE01
racer_model[3] = VWMYCR
racer_model[4] = OMYST
racer_model[5] = DWMYLC1
racer_model[6] = CWFYFR1
racer_model[7] = SWMYCR
racer_model[8] = MALE01
racer_model[9] = WMYCR
racer_model[10] = DWMYLC1
racer_model[11] = MALE01
racer_model[12] = MALE01
racer_model[13] = MALE01
racer_model[14] = MALE01
racer_model[15] = MALE01

racers_car_model[0] = sanchez
racers_car_model[1] = sanchez
racers_car_model[2] = sanchez
racers_car_model[3] = sanchez
racers_car_model[4] = sanchez
racers_car_model[5] = sanchez
racers_car_model[6] = sanchez
racers_car_model[7] = sanchez
racers_car_model[8] = sanchez
racers_car_model[9] = sanchez
racers_car_model[10] = sanchez
racers_car_model[11] = sanchez
racers_car_model[12] = sanchez
racers_car_model[13] = sanchez
racers_car_model[14] = sanchez
racers_car_model[15] = sanchez
break
endswitch
return



race13_special:///////// Kev's Bolt on ;) ///////////////////////////////
LVAR_INT race_event_flag[2] special_car[2] special_char[2] task_state test_jmp
IF race_event_flag[0] = 0
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -148.2144 87.3396 2.1234 280.0 280.0 30.0 FALSE		
		race_event_flag[0] = 1
	ENDIF
ENDIF
IF race_event_flag[0] = 1
	race_event_flag[0] = 2
	REQUEST_MODEL COMBINE
	REQUEST_MODEL VHMYELV
ENDIF
IF race_event_flag[0] = 2
	IF HAS_MODEL_LOADED COMBINE
	AND HAS_MODEL_LOADED VHMYELV
		race_event_flag[0] = 3
		CREATE_CAR COMBINE -128.2144 80.3396 2.1234 special_car[0] 
		SET_CAR_HEADING special_car[0] 250.5799
		CREATE_CHAR_INSIDE_CAR special_car[0] PEDTYPE_CIVMALE VHMYELV special_char[0]
		MARK_MODEL_AS_NO_LONGER_NEEDED COMBINE
		MARK_MODEL_AS_NO_LONGER_NEEDED VHMYELV
		FLUSH_ROUTE
		EXTEND_ROUTE -95.8304 68.2700 3.4764 
		EXTEND_ROUTE -77.3806 70.3096 3.5478 
		TASK_DRIVE_POINT_ROUTE_ADVANCED special_char[0] special_car[0] 7.5 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
	ENDIF
ENDIF
IF race_event_flag[0] = 3
	IF NOT IS_CHAR_DEAD special_char[0]
		GET_SCRIPT_TASK_STATUS special_char[0] TASK_DRIVE_POINT_ROUTE_ADVANCED task_state
		IF task_state = FINISHED_TASK
			race_event_flag[0] = 4
			IF NOT IS_CAR_DEAD special_car[0]
				MARK_CAR_AS_NO_LONGER_NEEDED special_car[0]
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED special_char[0]
		ENDIF
	ENDIF
ENDIF

IF race_event_flag[1] = 0
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -542.1076 230.7356 11.6475 320.0 320.0 30.0 FALSE		
		race_event_flag[1] = 1
	ENDIF
ENDIF 
IF race_event_flag[1] = 1
	race_event_flag[1] = 2
	REQUEST_MODEL WALTON
	REQUEST_MODEL VHMYELV
ENDIF
IF race_event_flag[1] = 2
	IF HAS_MODEL_LOADED WALTON
	AND HAS_MODEL_LOADED VHMYELV
		race_event_flag[1] = 3
		CREATE_CAR WALTON -542.1076 230.7356 11.6475 special_car[1] 
		SET_CAR_HEADING special_car[1] 262.8685
		CREATE_CHAR_INSIDE_CAR special_car[1] PEDTYPE_CIVMALE VHMYELV special_char[1]
		MARK_MODEL_AS_NO_LONGER_NEEDED WALTON
		MARK_MODEL_AS_NO_LONGER_NEEDED VHMYELV
		TASK_CAR_DRIVE_TO_COORD special_char[1] special_car[1] -270.5629 199.0073 6.9900 11.5 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
	ENDIF
ENDIF
IF race_event_flag[1] = 3
	IF NOT IS_CHAR_DEAD special_char[1]
		GET_SCRIPT_TASK_STATUS special_char[1] TASK_CAR_DRIVE_TO_COORD task_state
		IF task_state = FINISHED_TASK
			race_event_flag[1] = 4
			IF NOT IS_CAR_DEAD special_car[1]
				TASK_CAR_DRIVE_WANDER special_char[1] special_car[1] 11.5 DRIVINGMODE_AVOIDCARS
				MARK_CAR_AS_NO_LONGER_NEEDED special_car[1]
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED special_char[1]
		ENDIF
	ENDIF
ENDIF

RETURN


load_race_txd:///////////////////////////////////////////////////
IF race_selection < 6
	IF NOT txd_in_memory = 1
		REMOVE_TEXTURE_DICTIONARY
		LOAD_TEXTURE_DICTIONARY LD_RCE1
		LOAD_SPRITE 1 race00
		LOAD_SPRITE 2 race01
		LOAD_SPRITE 3 race02
		LOAD_SPRITE 4 race03
		LOAD_SPRITE 5 race04
		LOAD_SPRITE 6 race05
		txd_in_memory = 1
		RETURN
	ENDIF
ENDIF

IF race_selection > 5
AND	race_selection < 12
	IF NOT txd_in_memory = 2
		REMOVE_TEXTURE_DICTIONARY
		LOAD_TEXTURE_DICTIONARY LD_RCE2
		LOAD_SPRITE 7 race06
		LOAD_SPRITE 8 race07
		LOAD_SPRITE 9 race08
		LOAD_SPRITE 10 race09
		LOAD_SPRITE 11 race10
		LOAD_SPRITE 12 race11
		txd_in_memory = 2
		RETURN
	ENDIF
ENDIF

IF race_selection > 11
AND	race_selection < 18
	IF NOT txd_in_memory = 3
		REMOVE_TEXTURE_DICTIONARY
		LOAD_TEXTURE_DICTIONARY LD_RCE3
		LOAD_SPRITE 13 race12
		LOAD_SPRITE 14 race13
		LOAD_SPRITE 15 race14
		LOAD_SPRITE 16 race15
		LOAD_SPRITE 17 race16
		LOAD_SPRITE 18 race17
		txd_in_memory = 3
		RETURN
	ENDIF
ENDIF

IF race_selection > 17
AND	race_selection < 24
	IF NOT txd_in_memory = 4
		REMOVE_TEXTURE_DICTIONARY
		LOAD_TEXTURE_DICTIONARY LD_RCE4
		LOAD_SPRITE 19 race18
		LOAD_SPRITE 20 race19
		LOAD_SPRITE 21 race20
		LOAD_SPRITE 22 race21
		LOAD_SPRITE 23 race22
		LOAD_SPRITE 24 race23
		txd_in_memory = 4
		RETURN
	ENDIF
ENDIF

IF race_selection > 23
AND	race_selection < 30
	IF NOT txd_in_memory = 5
		REMOVE_TEXTURE_DICTIONARY
		LOAD_TEXTURE_DICTIONARY LD_RCE5
		LOAD_SPRITE 25 race24
		txd_in_memory = 5
		RETURN
	ENDIF
ENDIF

RETURN


setup_text_cprace:///////////////////////////////////////////////
	SET_TEXT_COLOUR 134 155 184 255
	SET_TEXT_SCALE 0.6146 2.4961
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_DROPSHADOW 2 0 0 0 255
RETURN


get_random_racer_models:///////////////////////////////////////////////
c = 0
e = total_racers - 1
while c < e
	generate_random_int_in_range 0 32 a
	switch a
	case 0
		racer_model[c] = DWMYLC1
	break
	case 1
		racer_model[c] = OMYST
	break
	case 2
		racer_model[c] = SBFYST
	break
	case 3
		racer_model[c] = WMYJG
	break
	case 4
		racer_model[c] = WMYCR
	break
	case 5
		racer_model[c] = WMYST
	break
	case 6
		racer_model[c] = MAFFB
	break
	case 7
		racer_model[c] = DNB2
	break
	case 8
		racer_model[c] = VMAFF2
	break
	case 9
		racer_model[c] = DNMYLC
	break
	case 10
		racer_model[c] = DNFYLC
	break
	case 11
		racer_model[c] = DNMOLC2
	break
	case 12
		racer_model[c] = HFYBE
	break
	case 13
		racer_model[c] = SBMYST
	break
	case 14
		racer_model[c] = DWFYLC1
	break
	case 15
		racer_model[c] = CWFYHB
	break
	case 16
		racer_model[c] = SOMYST
	break
	case 17
		racer_model[c] = VWMYCR
	break
	case 18
		racer_model[c] = VBMYCR
	break
	case 19
		racer_model[c] = SOMYRI
	break
	case 20
		racer_model[c] = MECGRL3
	break
	case 21
		racer_model[c] = CWFYFR1
	break
	case 22
		racer_model[c] = DWFYLC2
	break
	case 23
		racer_model[c] = VWMYCD
	break
	case 24
		racer_model[c] = WFYCLOT
	break
	case 25
		racer_model[c] = WMYCLOT
	break
	case 26
		racer_model[c] = SBMOCD
	break
	case 27
		racer_model[c] = SHMYCR
	break
	case 28
		racer_model[c] = SWFYST
	break
	case 29
		racer_model[c] = VHFYST3
	break
	case 30
		racer_model[c] = BIKERB
	break
	case 31
		racer_model[c] = SWMYCR
	break
	endswitch
	++ c
endwhile
return



player_made_progress_cprace:///////////////////////////////////////////////
	if display_hud_position = 1
	or total_racers = 1
		PLAY_MISSION_PASSED_TUNE 1
		switch race_selection
		case 0
			if got_race_made_progress[0] = 0 //CESAR_RACE
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				if show_race_selection = false
					REGISTER_MISSION_PASSED CESAR_1
				else
					register_mission_passed	$race_name[race_selection]
				endif
				got_race_made_progress[0] = 1
			endif
		break
		case 1
			if got_race_made_progress[1] = 0	// LITTLE LOOP
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[1] = 1
			endif
		break
		case 2
			if got_race_made_progress[2] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[2] = 1
			endif
		break
		case 3
			if got_race_made_progress[3] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[3] = 1
			endif
		break
		case 4
			if got_race_made_progress[4] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[4] = 1
			endif
		break
		case 5
			if got_race_made_progress[5] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[5] = 1
			endif
		break
		case 6
			if got_race_made_progress[6] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[6] = 1
			endif
		break
		case 7
			if got_race_made_progress[7] = 0//BADLAND_RACE1
				PLAYER_MADE_PROGRESS 1
				start_new_script display_win_text 5000 5000
				add_score player1 5000
				++ total_races_completed
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[7] = 1
			endif
		break
		case 8
			if got_race_made_progress[8] = 0//BADLAND_RACE2
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[8] = 1
			endif
		break
		case 9
			if got_race_made_progress[9] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[9] = 1
			endif
		break
		case 10
			if got_race_made_progress[10] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[10] = 1
			endif
		break
		case 11
			if got_race_made_progress[11] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[11] = 1
			endif
		break
		case 12
			if got_race_made_progress[12] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[12] = 1
			endif
		break
		case 13
			if got_race_made_progress[13] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[13] = 1
			endif
		break
		case 14
			if got_race_made_progress[14] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[14] = 1
			endif
		break
		case 15
			if got_race_made_progress[15] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[15] = 1
			endif
		break
		case 16
			if got_race_made_progress[16] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[16] = 1
			endif
		break
		case 17
			if got_race_made_progress[17] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[17] = 1
			endif
		break
		case 18
			if got_race_made_progress[18] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[18] = 1
			endif
		break
		case 19
			if got_race_made_progress[19] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[19] = 1
			endif
		break
		case 20
			if got_race_made_progress[20] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[20] = 1
			endif
		break
		case 21
			if got_race_made_progress[21] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[21] = 1
			endif
		break
		case 22
			if got_race_made_progress[22] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[22] = 1
			endif
		break
		case 23
			if got_race_made_progress[23] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[23] = 1
			endif
		break
		case 24
			if got_race_made_progress[24] = 0
				PLAYER_MADE_PROGRESS 1
				++ total_races_completed
				gosub win_cash_cprace
				register_mission_passed	$race_name[race_selection]
				got_race_made_progress[24] = 1
			endif
		break
		case NASCAR_RACE   // 8-track race
			if got_race_made_progress[NASCAR_RACE] = 0
				start_new_script display_win_text 10000 5000
				add_score player1 10000
				PLAYER_MADE_PROGRESS 1
				register_mission_passed	STAD_03
				SWITCH_CAR_GENERATOR nascar_reward_cargen1 101
				SWITCH_CAR_GENERATOR nascar_reward_cargen2 101
				got_race_made_progress[NASCAR_RACE] = 1
			endif
//			if got_race_made_progress[NASCAR_RACE] = 1     //this allows for repeat completion (unsure if this is supposed to be repeatable)
//				start_new_script display_win_text 10000 5000
//				add_score player1 10000
//			endif
		break
		case DIRTBIKE_STADIUM
			if got_race_made_progress[DIRTBIKE_STADIUM] = 0
				start_new_script display_win_text 25000 5000
				add_score player1 25000
				PLAYER_MADE_PROGRESS 1
				register_mission_passed	STAD_01
				SWITCH_CAR_GENERATOR dirtrack_reward_cargen 101
				got_race_made_progress[DIRTBIKE_STADIUM] = 1
			endif
		break
		endswitch

		
	endif
return




win_cash_cprace:///////////////////////////////////////////////
	if total_races_completed = total_races
		if got_race_mission_complete = 0
			start_new_script display_win_text 1000000 5000 45
			add_score player1 1000000
			got_race_mission_complete = 1
		endif
	else
		start_new_script display_win_text 10000 5000
		add_score player1 10000
	endif
return




generate_random_anims:///////////////////////////////////////////////
	repeat 7 c
		generate_random_int_in_range 0 5 e
		switch e
			case 0
				TASK_PLAY_ANIM -1 RIOT_CHANT RIOT 4.0 TRUE FALSE FALSE FALSE 2700
			break
			case 1
				TASK_PLAY_ANIM -1 RIOT_CHALLENGE RIOT 4.0 TRUE FALSE FALSE FALSE 2000
			break
			case 2
				TASK_PLAY_ANIM -1 RIOT_SHOUT RIOT 4.0 TRUE FALSE FALSE FALSE 2000
			break
			case 3
				TASK_PLAY_ANIM -1 RIOT_FUKU RIOT 4.0 TRUE FALSE FALSE FALSE 3000
			break
			case 4
				TASK_PLAY_ANIM -1 RIOT_PUNCHES RIOT 4.0 TRUE FALSE FALSE FALSE 2200
			break
		endswitch
	endrepeat
return

do_stadium_sound_effects:///////////////////////////////////////////////

if race_audio_flag = 0
	if has_mission_audio_finished 4	 //Fix PC Bug Nos. 509 & 513
		if race_audio_timer < game_timer
			CLEAR_MISSION_AUDIO 4
			generate_random_int_in_range 0 4 current_sound
			switch current_sound
			case 0
				LOAD_MISSION_AUDIO 4 SOUND_CROWD_AWWS
			break
			case 1
				LOAD_MISSION_AUDIO 4 SOUND_CROWD_CHEERS
			break
			case 2
				LOAD_MISSION_AUDIO 4 SOUND_CROWD_CHEERS_BIG
			break
			case 3
				LOAD_MISSION_AUDIO 4 SOUND_BANK_AIR_HORN
			break
			endswitch
			++ race_audio_flag
		endif
	endif
endif

if race_audio_flag = 1
	if HAS_MISSION_AUDIO_LOADED 4
		if current_sound = 3
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_AIR_HORN
		else
			play_mission_audio 4
		endif
		generate_random_int_in_range 2000 10000 race_audio_timer
		race_audio_timer += game_timer
		race_audio_flag = 0
	endif
endif

return
return
return
temp_float = 0.0
temp_float = 0.0
temp_float = 0.0
return
return
return


mission_cleanup_cprace://////////////////////////////////////////

IF exit_mode = 0
OR exit_mode = 1
OR exit_mode = 3
	IF IS_PLAYER_PLAYING player1
		if exit_mode = 3
			if race_selection = NASCAR_RACE
				x = 2693.1836
				y = -1699.6553
				z = 9.3891
				heading = 34.2987 
			endif
			if race_selection = DIRTBIKE_STADIUM
				x = 1101.7811
				y = 1604.5785
				z = 11.5506
				heading = 353.1771 
			endif
		else
			if menu_mode = LA_RACES
				x = traceX[menu_mode]
				y = traceY[menu_mode] + 4.0
				heading = 180.0
			endif
			if menu_mode = SF_RACES
				x = traceX[menu_mode] + 4.0
				y = traceY[menu_mode]
				heading = 90.0
			endif
			if menu_mode = LV_RACES
			or menu_mode = AIR_RACES
				x = traceX[menu_mode] + 4.0
				y = traceY[menu_mode]
				heading = 90.0
			endif
//			if menu_mode = AIR_RACES
//				x = traceX[menu_mode] + 4.0
//				y = traceY[menu_mode]
//				heading = 90.0
//			endif
			IF not exit_mode = 1
				x = traceX[menu_mode]
				y = traceY[menu_mode]
			ENDIF
			z = traceZ[menu_mode]
		endif
		clear_char_tasks_immediately scplayer
		SET_CHAR_COORDINATES scplayer x y z 
		SET_CHAR_HEADING scplayer heading
		CLEAR_AREA x y z 0.5 0
		REQUEST_COLLISION x y
		LOAD_SCENE x y z
		SET_WEATHER_TO_APPROPRIATE_TYPE_NOW
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		DO_FADE 2000 FADE_IN
		SET_AREA_VISIBLE 0
		SET_CHAR_AREA_VISIBLE scplayer 0
	ENDIF
ENDIF

a = 0
WHILE a < total_racers
	if not a = playaz
		if not racer[a] = scplayer
			MARK_CAR_AS_NO_LONGER_NEEDED racers_car[a]
			MARK_CHAR_AS_NO_LONGER_NEEDED racer[a]
		endif
	endif
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model[a]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model[a]
	REMOVE_BLIP	racer_blip[a]
	++ a
ENDWHILE

remove_char_elegantly race_passenger

UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2

if player_fall_state > 0
	player_fall_state = 6
endif
MARK_CHAR_AS_NO_LONGER_NEEDED race_passenger
MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl
a = 0
WHILE a < 11
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator[10]
	++ a
ENDWHILE

if race_selection = 23
	IF d5_pilot_licence_obtained = 1
		SWITCH_CAR_GENERATOR gen_car_flying[1] 101
	ENDIF
ENDIF


REMOVE_BLIP	first_blip
DELETE_CHECKPOINT cp_marker
DELETE_OBJECT flag_object

clear_onscreen_timer races_timer

remove_animation riot
remove_animation car

USE_TEXT_COMMANDS FALSE
SET_MESSAGE_FORMATTING FALSE 380 464
SET_CAR_DENSITY_MULTIPLIER 1.0
DISABLE_ALL_ENTRY_EXITS FALSE
DISPLAY_RADAR true
DISPLAY_CAR_NAMES true
DISPLAY_ZONE_NAMES true
HIDE_ALL_FRONTEND_BLIPS false
SET_PLAYER_IS_IN_STADIUM false
DISPLAY_HUD TRUE
CLEAR_EXTRA_COLOURS FALSE
SET_HELP_MESSAGE_BOX_SIZE 200
if is_player_playing player1
	SET_CHAR_AREA_VISIBLE scplayer 0
endif
disable_all_cranes = 0

disable_mod_garage = 0
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN
}

