MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************** SMOKE 2 ******************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME smoke2				

// Mission start stuff

GOSUB mission_start_smoke2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_smoke2_failed
ENDIF										  

GOSUB mission_cleanup_smoke2

MISSION_END

{
 
// Variables for mission

LVAR_INT s2_player_car s2_smoke
LVAR_FLOAT s2_player_car_x s2_player_car_y s2_player_car_z s2_player_car_heading
VAR_FLOAT s2_meet_x s2_meet_y s2_meet_z

LVAR_INT s2_weapon_pickup

LVAR_INT s2_escaping_guy s2_getaway_car s2_getaway_car_driver
LVAR_INT s2_escape_guards[2]
LVAR_INT s2_num_of_points_on_route
LVAR_FLOAT s2_route_x[22] s2_route_y[22] s2_route_z[22]

LVAR_INT s2_index

LVAR_INT s2_whole_route_seq s2_route_seq1 s2_route_seq2 s2_route_seq3 s2_route_seq4
LVAR_INT s2_route_seq_progress1 s2_route_seq_progress2

//LVAR_INT s2_cur_attack_point_reached

LVAR_INT s2_smoke_tired_seq

LVAR_INT s2_task_status

LVAR_INT s2_escaping_guy_decisions
LVAR_INT s2_smoke_decisions

LVAR_INT s2_current_driver

LVAR_FLOAT s2_getaway_dest_x s2_getaway_dest_y s2_getaway_dest_z

LVAR_INT s2_get_in_audio_stage

// mission audio
LVAR_TEXT_LABEL s2_audio_text[9]
LVAR_INT s2_audio_sound[9]
LVAR_INT s2_audio_is_playing s2_audio_index s2_total_audio_to_play s2_started_talking
LVAR_INT s2_current_audio_needed
CONST_INT S2_TIRED_AUDIO 0
CONST_INT S2_CHASE_AUDIO 1
CONST_INT S2_CAR_AUDIO 2

// flags

LVAR_INT s2_player_entered_first_car s2_player_in_first_car
LVAR_INT s2_car_audio_started s2_car_audio_finished
LVAR_INT s2_player_and_smoke_arrived
LVAR_INT s2_weapon_pickup_collected
LVAR_INT s2_help_text_printed[3] s2_help_text_cleared
LVAR_INT s2_smoke_started_to_play_chase_dialogue s2_smoke_played_chase_dialogue
LVAR_INT s2_player_reached_escape_guards
LVAR_INT s2_escaping_guy_reached_first_driveway
//LVAR_INT s2_escaping_guy_attacking
LVAR_INT s2_escaping_guy_finished_route s2_escaping_guy_fleeing
LVAR_INT s2_escaping_guy_entered_car s2_escaping_guy_going_to_destination
LVAR_INT s2_getaway_driver_attacking_player
LVAR_INT s2_cutscene_skipped
LVAR_INT s2_fake_creates 

// blips

LVAR_INT s2_player_car_blip s2_meet_blip s2_escaping_guy_blip
LVAR_INT s2_weapon_pickup_blip

// **************************************** Mission Start **********************************

mission_start_smoke2:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT SMOKE2

flag_player_on_mission = 1

WAIT 0

// ************************************ Initialise variables  *****************************

s2_player_car_x = 2068.42
s2_player_car_y = -1694.87
s2_player_car_z = 13.3
s2_player_car_heading = 270.0

s2_meet_x = 2514.34
s2_meet_y = -1411.93
s2_meet_z = 27.41

s2_player_entered_first_car = 0
s2_player_in_first_car = 0
s2_car_audio_started = 0
s2_car_audio_finished = 0
s2_player_and_smoke_arrived = 0

s2_weapon_pickup_collected = 0

s2_help_text_printed[0] = 0
s2_help_text_printed[1] = 0
s2_help_text_printed[2] = 0
s2_help_text_cleared = 0

s2_smoke_started_to_play_chase_dialogue = 0
s2_smoke_played_chase_dialogue = 0

s2_player_reached_escape_guards = 0

s2_escaping_guy_reached_first_driveway = 0

//s2_cur_attack_point_reached = 0
//s2_escaping_guy_attacking = 0

s2_num_of_points_on_route = 22

// bottom of stairs
s2_route_x[0] = 2459.80
s2_route_y[0] = -1402.19
s2_route_z[0] = 24.06
// across road at start of alleyway
s2_route_x[1] = 2442.07
s2_route_y[1] = -1370.23
s2_route_z[1] = 24.06
// at end of alleyway
s2_route_x[2] = 2414.49
s2_route_y[2] = -1369.36
s2_route_z[2] = 24.76
// across alleyway at start of fence
s2_route_x[3] = 2410.19
s2_route_y[3] = -1364.03
s2_route_z[3] = 24.75

s2_route_x[4] = 2399.68
s2_route_y[4] = -1358.24
s2_route_z[4] = 24.93

s2_route_x[5] = 2381.88
s2_route_y[5] = -1354.88
s2_route_z[5] = 24.08

// second alleyway
s2_route_x[6] = 2343.29
s2_route_y[6] = -1351.41
s2_route_z[6] = 24.06
// end of second alleyway
s2_route_x[7] = 2336.29
s2_route_y[7] = -1310.99
s2_route_z[7] = 24.29
// first fence at last row of houses
s2_route_x[8] = 2257.07
s2_route_y[8] = -1291.85
s2_route_z[8] = 24.01

s2_route_x[9] = 2241.61
s2_route_y[9] = -1281.61
s2_route_z[9] = 24.42
// first alleyway fence
s2_route_x[10] = 2241.61
s2_route_y[10] = -1264.68
s2_route_z[10] = 24.37

s2_route_x[11] = 2239.48
s2_route_y[11] = -1260.91
s2_route_z[11] = 23.98

s2_route_x[12] = 2241.18
s2_route_y[12] = -1257.88
s2_route_z[12] = 23.99

s2_route_x[13] = 2241.18
s2_route_y[13] = -1254.46
s2_route_z[13] = 24.46

s2_route_x[14] = 2241.18
s2_route_y[14] = -1238.30
s2_route_z[14] = 25.21

s2_route_x[15] = 2221.54
s2_route_y[15] = -1235.75
s2_route_z[15] = 24.62

s2_route_x[16] = 2202.27
s2_route_y[16] = -1236.72
s2_route_z[16] = 24.00

s2_route_x[17] = 2202.21
s2_route_y[17] = -1257.05
s2_route_z[17] = 23.91

//// last fence
//s2_route_x[18] = 2198.75
//s2_route_y[18] = -1262.54
//s2_route_z[18] = 23.92

//s2_route_x[19] = 2197.24
//s2_route_y[19] = -1276.44
//s2_route_z[19] = 24.58
//
//s2_route_x[20] = 2192.78
//s2_route_y[20] = -1282.57
//s2_route_z[20] = 24.57

// last fence
s2_route_x[18] = 2199.57
s2_route_y[18] = -1263.15
s2_route_z[18] = 23.92

s2_route_x[19] = 2199.08
s2_route_y[19] = -1279.44
s2_route_z[19] = 24.57

s2_route_x[20] = 2182.07
s2_route_y[20] = -1280.2
s2_route_z[20] = 24.68

s2_route_x[21] = 2175.18
s2_route_y[21] = -1282.82
s2_route_z[21] = 24.02

s2_getaway_dest_x = 2117.86
s2_getaway_dest_y = -1071.42
s2_getaway_dest_z = 24.64

s2_get_in_audio_stage = 0

s2_escaping_guy_finished_route = 0
s2_escaping_guy_fleeing = 0
s2_escaping_guy_entered_car = 0
s2_escaping_guy_going_to_destination = 0
s2_getaway_driver_attacking_player = 0

s2_audio_is_playing = 0
s2_audio_index = 0
s2_started_talking = 0

s2_fake_creates = 0

// ****************************************START OF CUTSCENE*******************************

SET_FADING_COLOUR 0 0 0

DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_AREA 2069.90 -1702.73 12.59 100.0 FALSE

REQUEST_MODEL Lae_smokecutscene
WHILE NOT HAS_MODEL_LOADED Lae_smokecutscene
	WAIT 0
ENDWHILE
LVAR_INT s2_cutscene_object
CREATE_OBJECT_NO_OFFSET Lae_smokecutscene 2055.0 -1695.0 15.0 s2_cutscene_object

LOAD_CUTSCENE SMOKE2A

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

MARK_MODEL_AS_NO_LONGER_NEEDED Lae_smokecutscene
DELETE_OBJECT s2_cutscene_object

// ****************************************END OF CUTSCENE*********************************

LOAD_SPECIAL_CHARACTER 1 smoke
REQUEST_MODEL GLENDALE
REQUEST_MODEL COLT45
LOAD_ALL_MODELS_NOW
WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
   OR NOT HAS_MODEL_LOADED GLENDALE
   OR NOT HAS_MODEL_LOADED COLT45
	WAIT 0
ENDWHILE

GOSUB s2_create_player_car_and_smoke

LOAD_SCENE 2069.90 -1702.73 12.59
CLEAR_AREA 2069.90 -1702.73 12.59 100.0 FALSE
// clear area around first corona driven to
CLEAR_AREA s2_meet_x s2_meet_y s2_meet_z 100.0 FALSE
// switch roads off around first corona driven to
// so there aren't any cars in the way
SWITCH_ROADS_OFF 2488.0 -1431.93 25.41 2528.0 -1391.93 29.41
// switch node off at driveway near first fence at last row of houses
SWITCH_ROADS_OFF 2252.89 -1286.37 22.25 2258.89 -1280.37 28.25

SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

//SET_CHAR_COORDINATES scplayer 2069.90 -1702.73 12.59
SET_CHAR_COORDINATES scplayer 2070.87 -1703.01 12.55
SET_CHAR_HEADING scplayer 0.0

// fades the screen in 

SET_FADING_COLOUR 0 0 0

WAIT 500

DO_FADE 1500 FADE_IN

//WAIT 1500

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

// CREATE statements to keep the compiler happy

IF s2_fake_creates = 1
	CREATE_CAR GLENDALE 0.0 0.0 -100.0 s2_player_car
	ADD_BLIP_FOR_CAR s2_player_car s2_player_car_blip
	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 0.0 0.0 -100.0 s2_smoke
	CREATE_CHAR PEDTYPE_MISSION2 LSV1 0.0 0.0 -100.0 s2_escaping_guy
	CREATE_CAR SENTINEL 0.0 0.0 -100.0 s2_getaway_car
	CREATE_CHAR_INSIDE_CAR s2_getaway_car PEDTYPE_MISSION2 LSV2 s2_getaway_car_driver
	CREATE_CHAR PEDTYPE_MISSION2 LSV2 0.0 0.0 -100.0 s2_escape_guards[0]
	CREATE_CHAR PEDTYPE_MISSION2 LSV3 0.0 0.0 -100.0 s2_escape_guards[1]
ENDIF

SWITCH_PED_ROADS_OFF 2458.50 -1410.63 24.06 2504.27 -1395.83 28.55

OPEN_SEQUENCE_TASK s2_route_seq1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[0] s2_route_y[0] s2_route_z[0] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[1] s2_route_y[1] s2_route_z[1] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[2] s2_route_y[2] s2_route_z[2] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[3] s2_route_y[3] s2_route_z[3] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[4] s2_route_y[4] s2_route_z[4] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[5] s2_route_y[5] s2_route_z[5] PEDMOVE_RUN -1
CLOSE_SEQUENCE_TASK s2_route_seq1

OPEN_SEQUENCE_TASK s2_route_seq2
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[6] s2_route_y[6] s2_route_z[6] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[7] s2_route_y[7] s2_route_z[7] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[8] s2_route_y[8] s2_route_z[8] PEDMOVE_SPRINT -1
	TASK_GO_STRAIGHT_TO_COORD -1 2254.09 -1283.83 25.18 PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[9] s2_route_y[9] s2_route_z[9] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[10] s2_route_y[10] s2_route_z[10] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
CLOSE_SEQUENCE_TASK s2_route_seq2

OPEN_SEQUENCE_TASK s2_route_seq3
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[11] s2_route_y[11] s2_route_z[11] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[12] s2_route_y[12] s2_route_z[12] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[13] s2_route_y[13] s2_route_z[13] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[14] s2_route_y[14] s2_route_z[14] PEDMOVE_RUN -1
	TASK_ACHIEVE_HEADING -1 90.0
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[15] s2_route_y[15] s2_route_z[15] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
CLOSE_SEQUENCE_TASK s2_route_seq3

OPEN_SEQUENCE_TASK s2_route_seq4
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[16] s2_route_y[16] s2_route_z[16] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[17] s2_route_y[17] s2_route_z[17] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[18] s2_route_y[18] s2_route_z[18] PEDMOVE_RUN -1
	TASK_CLIMB -1 TRUE
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[19] s2_route_y[19] s2_route_z[19] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[20] s2_route_y[20] s2_route_z[20] PEDMOVE_RUN -1
	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[21] s2_route_y[21] s2_route_z[21] PEDMOVE_RUN -1
CLOSE_SEQUENCE_TASK s2_route_seq4

//OPEN_SEQUENCE_TASK s2_route_seq1
//	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[0] s2_route_y[0] s2_route_z[0] PEDMOVE_RUN -1
//	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[1] s2_route_y[1] s2_route_z[1] PEDMOVE_RUN -1
//	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[2] s2_route_y[2] s2_route_z[2] PEDMOVE_RUN -1
//	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[3] s2_route_y[3] s2_route_z[3] PEDMOVE_RUN -1
//	TASK_CLIMB -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[4] s2_route_y[4] s2_route_z[4] PEDMOVE_RUN -1
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[5] s2_route_y[5] s2_route_z[5] PEDMOVE_RUN 5.0 1.0 scplayer
//CLOSE_SEQUENCE_TASK s2_route_seq1
//
//OPEN_SEQUENCE_TASK s2_route_seq2
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[6] s2_route_y[6] s2_route_z[6] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[7] s2_route_y[7] s2_route_z[7] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[8] s2_route_y[8] s2_route_z[8] PEDMOVE_SPRINT 5.0 1.0 scplayer
//	TASK_CLIMB -1 TRUE
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[9] s2_route_y[9] s2_route_z[9] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[10] s2_route_y[10] s2_route_z[10] PEDMOVE_RUN 5.0 1.0 scplayer
//CLOSE_SEQUENCE_TASK s2_route_seq2
//
//OPEN_SEQUENCE_TASK s2_route_seq3
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[11] s2_route_y[11] s2_route_z[11] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[12] s2_route_y[12] s2_route_z[12] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[14] s2_route_y[14] s2_route_z[14] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_ACHIEVE_HEADING -1 90.0
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[15] s2_route_y[15] s2_route_z[15] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_CLIMB -1 TRUE
//CLOSE_SEQUENCE_TASK s2_route_seq3
//
//OPEN_SEQUENCE_TASK s2_route_seq4
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[16] s2_route_y[16] s2_route_z[16] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[17] s2_route_y[17] s2_route_z[17] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[18] s2_route_y[18] s2_route_z[18] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_CLIMB -1 TRUE
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[19] s2_route_y[19] s2_route_z[19] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[20] s2_route_y[20] s2_route_z[20] PEDMOVE_RUN 5.0 1.0 scplayer
//	TASK_GO_TO_COORD_WHILE_SHOOTING -1 s2_route_x[21] s2_route_y[21] s2_route_z[21] PEDMOVE_RUN 5.0 1.0 scplayer
//CLOSE_SEQUENCE_TASK s2_route_seq4

OPEN_SEQUENCE_TASK s2_whole_route_seq
	PERFORM_SEQUENCE_TASK -1 s2_route_seq1
	PERFORM_SEQUENCE_TASK -1 s2_route_seq2
	PERFORM_SEQUENCE_TASK -1 s2_route_seq3
	PERFORM_SEQUENCE_TASK -1 s2_route_seq4
CLOSE_SEQUENCE_TASK s2_whole_route_seq

PRINT_NOW ( SMK2_1 ) 10000 0

// Mission loop

smoke2_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_smoke2_passed  
	ENDIF

//	// temp
//	IF IS_BUTTON_PRESSED PAD1 LEFTSHOCK
//		GOSUB s2_play_mission_failed_cutscene
//	ENDIF

	IF NOT s2_player_entered_first_car = 1
	AND NOT IS_CHAR_DEAD s2_smoke
	AND NOT IS_CAR_DEAD s2_player_car
		IF IS_CHAR_IN_CAR scplayer s2_player_car
	
			REMOVE_BLIP s2_player_car_blip
			ADD_BLIP_FOR_COORD s2_meet_x s2_meet_y s2_meet_z s2_meet_blip
			s2_player_entered_first_car = 1
			s2_player_in_first_car = 1

			PRINT_NOW ( SMK2_2 ) 11000 0

			CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ONCE 100 2444.8950 -1981.5243 13.933 s2_weapon_pickup
			ADD_SPRITE_BLIP_FOR_COORD 2444.8950 -1981.5243 13.933 RADAR_SPRITE_EMMETGUN s2_weapon_pickup_blip

			TIMERA = 0
		
		ENDIF
	ENDIF

	IF s2_player_entered_first_car = 1
	AND NOT s2_player_and_smoke_arrived = 1
	AND NOT IS_CHAR_DEAD s2_smoke
	AND NOT IS_CAR_DEAD s2_player_car

		IF NOT s2_weapon_pickup_collected = 1
			IF HAS_PICKUP_BEEN_COLLECTED s2_weapon_pickup
				REMOVE_BLIP s2_weapon_pickup_blip
				s2_weapon_pickup_collected = 1
			ENDIF
		ENDIF

		IF s2_player_in_first_car = 1
		AND NOT IS_CHAR_IN_CAR scplayer s2_player_car
			REMOVE_BLIP s2_meet_blip
			ADD_BLIP_FOR_CAR s2_player_car s2_player_car_blip
			SET_BLIP_AS_FRIENDLY s2_player_car_blip TRUE
			GOSUB s2_play_smoke_get_in_audio
			// check mission failed conditions
			IF IS_CHAR_DEAD s2_smoke
				CLEAR_MISSION_AUDIO 2
				PRINT_NOW ( SMK2_7 ) 4000 0
				GOTO mission_smoke2_failed
			ENDIF
			IF IS_CAR_DEAD s2_player_car
				PRINT_NOW ( SMK2_12 ) 5000 0
				GOTO mission_smoke2_failed
			ENDIF
			IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2444.8950 -1981.5243 20.0 20.0 FALSE
			OR s2_weapon_pickup_collected = 1
				PRINT_NOW ( SMK2_3 ) 5000 0
			ELSE
				PRINT_NOW ( SMK2_16 ) 5000 0
			ENDIF
			s2_player_in_first_car = 0
		ENDIF
		IF s2_player_in_first_car = 0
		AND IS_CHAR_IN_CAR scplayer s2_player_car
			REMOVE_BLIP s2_player_car_blip
			ADD_BLIP_FOR_COORD s2_meet_x s2_meet_y s2_meet_z s2_meet_blip
			IF s2_car_audio_finished = 1
				PRINT_NOW ( SMK2_15 ) 5000 0
			ENDIF
			s2_player_in_first_car = 1
		ENDIF

		IF s2_player_in_first_car = 1
			IF NOT s2_car_audio_started = 1
			AND TIMERA > 10000
				s2_current_audio_needed = S2_CAR_AUDIO
				GOSUB s2_setup_audio_data
				IF NOT IS_CHAR_DEAD s2_smoke
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH s2_smoke TRUE
				ENDIF
				s2_car_audio_started = 1
			ENDIF
			IF s2_car_audio_started = 1
			AND NOT s2_car_audio_finished = 1
				IF s2_audio_index < s2_total_audio_to_play
					GOSUB s2_load_and_play_audio
				ELSE
					IF NOT IS_CHAR_DEAD s2_smoke
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH s2_smoke FALSE
					ENDIF
					s2_car_audio_finished = 1
				ENDIF
			ENDIF
		ENDIF

		IF s2_player_in_first_car = 1
			IF LOCATE_CHAR_IN_CAR_3D scplayer s2_meet_x s2_meet_y s2_meet_z 4.0 4.0 2.0 TRUE
				
				SET_PLAYER_CONTROL player1 OFF

				s2_player_and_smoke_arrived = 1
				REMOVE_BLIP s2_meet_blip
				REMOVE_BLIP s2_weapon_pickup_blip

				SWITCH_ROADS_BACK_TO_ORIGINAL 2488.0 -1431.93 25.41 2528.0 -1391.93 29.41

				IF NOT IS_CHAR_DEAD s2_smoke
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH s2_smoke FALSE
				ENDIF
				
				GOSUB s2_play_meet_cutscene

				// switch nodes off around getaway car
				SWITCH_ROADS_OFF 2164.51 -1287.88 20.0 2170.88 -1267.88 30.0

				PRINT_NOW ( SMK2_17 ) 10000 0
				TIMERA = 0
			ENDIF
		ENDIF

 	ENDIF

	IF s2_player_and_smoke_arrived = 1

		IF NOT s2_help_text_printed[0] = 1
		AND TIMERA > 10000
			
			PRINT_HELP_FOREVER SMK2_H1  
			
			s2_help_text_printed[0] = 1
			TIMERA = 0
		ENDIF

		IF s2_help_text_printed[0] = 1
		AND NOT s2_help_text_printed[1] = 1
		AND TIMERA > 10000
			PRINT_HELP_FOREVER SMK2_H2  // "You can only use the Super Sprint for a short time before becoming tired."
			s2_help_text_printed[1] = 1
			TIMERA = 0
		ENDIF
		IF s2_help_text_printed[1] = 1
		AND NOT s2_help_text_printed[2] = 1
		AND TIMERA > 10000
			PRINT_HELP_FOREVER SPRSTAM  // "The more you sprint, the higher your ~h~stamina~w~ will become, allowing you to exert yourself for longer." );
			s2_help_text_printed[2] = 1
			TIMERA = 0
		ENDIF
		IF s2_help_text_printed[2] = 1
		AND NOT s2_help_text_cleared = 1
		AND TIMERA > 10000
			CLEAR_HELP
			s2_help_text_cleared = 1
		ENDIF

		IF NOT s2_smoke_played_chase_dialogue = 1
			IF NOT IS_CHAR_DEAD s2_smoke
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_smoke 5.0 5.0 FALSE
				AND NOT s2_smoke_started_to_play_chase_dialogue = 1
					s2_current_audio_needed = S2_CHASE_AUDIO
					GOSUB s2_setup_audio_data
					IF NOT IS_CHAR_DEAD s2_smoke
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH s2_smoke TRUE
					ENDIF
					s2_smoke_started_to_play_chase_dialogue = 1
				ENDIF
			ENDIF
			IF s2_smoke_started_to_play_chase_dialogue = 1
			AND s2_current_audio_needed = S2_CHASE_AUDIO
				IF s2_audio_index < s2_total_audio_to_play
					IF NOT IS_CHAR_DEAD s2_smoke
						GOSUB s2_load_and_play_audio
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_PRINTS
						s2_smoke_played_chase_dialogue = 1
					ENDIF
				ELSE
					IF NOT IS_CHAR_DEAD s2_smoke
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH s2_smoke FALSE
					ENDIF
					s2_smoke_played_chase_dialogue = 1
				ENDIF
			ENDIF
		ENDIF

		// escape guards run after player when he goes past them
		IF NOT s2_player_reached_escape_guards = 1
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2354.11 -1355.54 15.0 15.0 FALSE
				s2_index = 0
				WHILE s2_index < 2
					IF NOT IS_CHAR_DEAD s2_escape_guards[s2_index]
						SET_CHAR_KINDA_STAY_IN_SAME_PLACE s2_escape_guards[s2_index] FALSE
					ENDIF
					s2_index++
				ENDWHILE
				s2_player_reached_escape_guards = 1
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			IF NOT s2_escaping_guy_reached_first_driveway = 1
				GET_SCRIPT_TASK_STATUS s2_escaping_guy PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_task_status
				IF s2_task_status = PERFORMING_TASK
					GET_SEQUENCE_PROGRESS_RECURSIVE s2_escaping_guy s2_route_seq_progress1 s2_route_seq_progress2
					IF s2_route_seq_progress1 = 1
					AND s2_route_seq_progress2 = 4
						s2_escaping_guy_reached_first_driveway = 1
					ENDIF
				ENDIF
			ENDIF
			IF NOT s2_escaping_guy_reached_first_driveway = 1
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy run_gang1
					SET_CHAR_ANIM_SPEED s2_escaping_guy run_gang1 1.5
				ENDIF
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy sprint_civi
					SET_CHAR_ANIM_SPEED s2_escaping_guy sprint_civi 1.5
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy run_gang1
					SET_CHAR_ANIM_SPEED s2_escaping_guy run_gang1 1.2
				ENDIF
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy sprint_civi
					SET_CHAR_ANIM_SPEED s2_escaping_guy sprint_civi 1.2
				ENDIF
			ENDIF
		ENDIF

//		// escaping guy stops and shoots at player if close at certain points on route
//		IF NOT IS_CHAR_DEAD s2_escaping_guy
//			IF s2_cur_attack_point_reached < 3
//			AND NOT s2_escaping_guy_attacking = 1
//				GET_SCRIPT_TASK_STATUS s2_escaping_guy PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_task_status
//				IF s2_task_status = PERFORMING_TASK
//					GET_SEQUENCE_PROGRESS_RECURSIVE s2_escaping_guy s2_route_seq_progress1 s2_route_seq_progress2
//					SWITCH s2_cur_attack_point_reached
//						CASE 0
//							IF s2_route_seq_progress1 = 1
//							AND s2_route_seq_progress2 = 3
//
//								s2_cur_attack_point_reached = 1
//								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_escaping_guy 20.0 20.0 FALSE
//									TASK_KILL_CHAR_ON_FOOT s2_escaping_guy scplayer
//									TIMERB = 0
//									s2_escaping_guy_attacking = 1
//								ENDIF
//
//							ENDIF
//							BREAK
//						CASE 1
//							IF s2_route_seq_progress1 = 1
//							AND s2_route_seq_progress2 = 6
//
//								s2_cur_attack_point_reached = 2
//								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_escaping_guy 20.0 20.0 FALSE
//									TASK_KILL_CHAR_ON_FOOT s2_escaping_guy scplayer
//									TIMERB = 0
//									s2_escaping_guy_attacking = 1
//								ENDIF
//
//							ENDIF
//							BREAK
//						CASE 2
//							IF s2_route_seq_progress1 = 3
//							AND s2_route_seq_progress2 = 1
//
//								s2_cur_attack_point_reached = 3
//								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_escaping_guy 20.0 20.0 FALSE
//									TASK_KILL_CHAR_ON_FOOT s2_escaping_guy scplayer
//									TIMERB = 0
//									s2_escaping_guy_attacking = 1
//								ENDIF
//
//							ENDIF
//							BREAK
//						DEFAULT
//							BREAK
//					ENDSWITCH
//				ENDIF
//			ENDIF
//			IF s2_escaping_guy_attacking = 1
//				IF TIMERB > 5000
//				OR LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_escaping_guy 10.0 10.0 FALSE 
//					SWITCH s2_cur_attack_point_reached
//						CASE 1
//							PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_escaping_guy s2_whole_route_seq 1 3
//							BREAK
//						CASE 2
//							PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_escaping_guy s2_whole_route_seq 1 6
//							BREAK
//						CASE 3
//							PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_escaping_guy s2_whole_route_seq 3 1
//							BREAK
//						DEFAULT
//							BREAK
//					ENDSWITCH
//					s2_escaping_guy_attacking = 0
//				ENDIF
//			ENDIF
//		ENDIF

		// at end of route, escaping guy gets into getaway car with driver.
		// if car or driver have been destroyed by the time the escaping guy gets there,
		// he flees from the player.
		IF NOT IS_CHAR_DEAD s2_escaping_guy
			IF NOT s2_escaping_guy_finished_route = 1
				s2_index = s2_num_of_points_on_route - 1
				GET_SCRIPT_TASK_STATUS s2_escaping_guy PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_task_status
				IF s2_task_status = FINISHED_TASK
				AND LOCATE_CHAR_ANY_MEANS_3D s2_escaping_guy s2_route_x[s2_index] s2_route_y[s2_index] s2_route_z[s2_index] 10.0 10.0 10.0 FALSE
					s2_escaping_guy_finished_route = 1
					IF NOT IS_CAR_DEAD s2_getaway_car
					AND NOT IS_CHAR_DEAD s2_getaway_car_driver
						IF NOT IS_CAR_ON_FIRE s2_getaway_car
							TASK_ENTER_CAR_AS_PASSENGER s2_escaping_guy s2_getaway_car -2 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF NOT s2_getaway_driver_attacking_player = 1
				IF NOT IS_CHAR_DEAD s2_getaway_car_driver
				AND NOT IS_CAR_DEAD s2_getaway_car
					IF IS_CAR_ON_FIRE s2_getaway_car
						TASK_KILL_CHAR_ON_FOOT s2_getaway_car_driver scplayer
						s2_getaway_driver_attacking_player = 1
					ENDIF
				ENDIF
			ENDIF
			IF s2_escaping_guy_finished_route = 1
			AND NOT s2_escaping_guy_fleeing = 1
				IF NOT s2_escaping_guy_entered_car = 1
					IF NOT IS_CAR_DEAD s2_getaway_car
					AND NOT IS_CHAR_DEAD s2_getaway_car_driver
						IF IS_CHAR_IN_CAR s2_escaping_guy s2_getaway_car
							s2_escaping_guy_entered_car = 1
							TASK_CAR_DRIVE_WANDER s2_getaway_car_driver s2_getaway_car 30.0 DRIVINGMODE_AVOIDCARS
							GIVE_WEAPON_TO_CHAR s2_escaping_guy WEAPONTYPE_MICRO_UZI 99999
							TASK_DRIVE_BY s2_escaping_guy scplayer -1 0.0 0.0 0.0 1000.0 DRIVEBY_AI_ALL_DIRN FALSE 60
							SWITCH_ROADS_BACK_TO_ORIGINAL 2164.51 -1287.88 20.0 2170.88 -1267.88 30.0
							IF IS_CHAR_IN_ANY_CAR scplayer
								PRINT_NOW ( SMK2_9 ) 10000 0
							ELSE
								PRINT_NOW ( SMK2_8 ) 10000 0
							ENDIF
							TIMERA = 0
						ENDIF
					ENDIF
					IF NOT s2_escaping_guy_entered_car = 1
						IF IS_CAR_DEAD s2_getaway_car
						OR IS_CHAR_DEAD s2_getaway_car_driver
							TASK_SMART_FLEE_CHAR s2_escaping_guy scplayer 1000.0 9999999
							s2_escaping_guy_fleeing = 1
						ELSE
							IF IS_CAR_ON_FIRE s2_getaway_car
								TASK_SMART_FLEE_CHAR s2_escaping_guy scplayer 1000.0 9999999
								s2_escaping_guy_fleeing = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF s2_escaping_guy_entered_car = 1
					// if car's on fire or there's no driver, escaping guy flees
					// and driver attacks player if he still exists
					IF NOT s2_escaping_guy_fleeing = 1
						IF NOT IS_CAR_DEAD s2_getaway_car
							GET_DRIVER_OF_CAR s2_getaway_car s2_current_driver
							IF IS_CAR_ON_FIRE s2_getaway_car
							OR s2_current_driver = -1
								TASK_SMART_FLEE_CHAR s2_escaping_guy scplayer 1000.0 9999999
								IF NOT IS_CHAR_DEAD s2_getaway_car_driver
								AND NOT s2_getaway_driver_attacking_player = 1
									TASK_KILL_CHAR_ON_FOOT s2_getaway_car_driver scplayer
									s2_getaway_driver_attacking_player = 1
								ENDIF
								s2_escaping_guy_fleeing = 1
							ENDIF
						ENDIF
					ENDIF
					IF NOT s2_escaping_guy_fleeing = 1
						IF NOT s2_escaping_guy_going_to_destination = 1
						AND TIMERA > 180000
							IF NOT IS_CAR_DEAD s2_getaway_car
							AND NOT IS_CHAR_DEAD s2_getaway_car_driver
								IF IS_CHAR_IN_CAR s2_getaway_car_driver s2_getaway_car
								AND IS_CHAR_IN_CAR s2_escaping_guy s2_getaway_car
									TASK_CAR_DRIVE_TO_COORD s2_getaway_car_driver s2_getaway_car s2_getaway_dest_x s2_getaway_dest_y s2_getaway_dest_z 30.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
									s2_escaping_guy_going_to_destination = 1
									PRINT_NOW ( SMK2_10 ) 7000 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			GOTO mission_smoke2_passed
		ENDIF

	ENDIF

	IF IS_CHAR_DEAD s2_smoke
		PRINT_NOW ( SMK2_7 ) 4000 0
		GOTO mission_smoke2_failed
	ENDIF
	IF NOT s2_player_and_smoke_arrived = 1
	AND IS_CAR_DEAD s2_player_car
		PRINT_NOW ( SMK2_12 ) 5000 0
		GOTO mission_smoke2_failed
	ENDIF
	IF s2_escaping_guy_finished_route = 1
	AND NOT IS_CHAR_DEAD s2_escaping_guy
		IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer s2_escaping_guy 300.0 300.0 FALSE
			PRINT_NOW ( SMK2_11 ) 4000 0
			GOTO mission_smoke2_failed
		ENDIF
	ENDIF
	IF s2_escaping_guy_going_to_destination = 1
	AND NOT s2_escaping_guy_fleeing = 1
	AND NOT IS_CHAR_DEAD s2_escaping_guy
	AND NOT IS_CHAR_DEAD s2_getaway_car_driver
	AND NOT IS_CAR_DEAD s2_getaway_car
		IF IS_CHAR_IN_CAR s2_escaping_guy s2_getaway_car
		AND IS_CHAR_IN_CAR s2_getaway_car_driver s2_getaway_car
		AND LOCATE_CAR_3D s2_getaway_car s2_getaway_dest_x s2_getaway_dest_y s2_getaway_dest_z 6.0 6.0 4.0 FALSE
			GOSUB check_player_is_safe
			IF player_is_completely_safe = 1
				GOSUB s2_play_mission_failed_cutscene
			ELSE
				REMOVE_CHAR_ELEGANTLY s2_escaping_guy
			ENDIF
			PRINT_NOW ( SMK2_11 ) 4000 0
			GOTO mission_smoke2_failed
		ENDIF
	ENDIF

GOTO smoke2_loop


// ********************************** Mission GOSUBS ************************************

// ************************************************************
// 	 Create player's car with Smoke already in passenger seat
// ************************************************************

	s2_create_player_car_and_smoke:

		CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
		CREATE_CAR GLENDALE s2_player_car_x s2_player_car_y s2_player_car_z s2_player_car
		SET_CAR_HEADING s2_player_car s2_player_car_heading
		CHANGE_CAR_COLOUR s2_player_car 98 14
		ADD_BLIP_FOR_CAR s2_player_car s2_player_car_blip
		SET_BLIP_AS_FRIENDLY s2_player_car_blip TRUE

//		CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2066.71 -1703.59 13.19 s2_smoke
//		SET_CHAR_HEADING s2_smoke 270.0
		CREATE_CHAR_AS_PASSENGER s2_player_car PEDTYPE_MISSION1 SPECIAL01 0 s2_smoke
		SET_CHAR_HEALTH s2_smoke 500
		SET_CHAR_MAX_HEALTH s2_smoke 500
		GIVE_WEAPON_TO_CHAR s2_smoke WEAPONTYPE_PISTOL 99999
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER s2_smoke TRUE
		SET_CHAR_NEVER_TARGETTED s2_smoke TRUE
		SET_CHAR_SUFFERS_CRITICAL_HITS s2_smoke FALSE
		SET_ANIM_GROUP_FOR_CHAR s2_smoke fatman
		SET_CHAR_CANT_BE_DRAGGED_OUT s2_smoke TRUE
		SET_CHAR_STAY_IN_CAR_WHEN_JACKED s2_smoke TRUE
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR s2_smoke FALSE

		COPY_CHAR_DECISION_MAKER -1 s2_smoke_decisions
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_smoke_decisions EVENT_VEHICLE_ON_FIRE
		SET_CHAR_DECISION_MAKER s2_smoke s2_smoke_decisions

		CLEAR_ALL_CHAR_RELATIONSHIPS s2_smoke ACQUAINTANCE_TYPE_PED_DISLIKE
		CLEAR_ALL_CHAR_RELATIONSHIPS s2_smoke ACQUAINTANCE_TYPE_PED_HATE

	RETURN

// *************************************************************
//  Cutscene showing Smoke going into meet and meet breaking up
// *************************************************************

	s2_play_meet_cutscene:

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_PRINTS

 		CLEAR_AREA s2_meet_x s2_meet_y s2_meet_z 150.0 TRUE
		SWITCH_ROADS_OFF 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0
		SWITCH_PED_ROADS_OFF 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0
		SET_CAR_DENSITY_MULTIPLIER 0.0

		LVAR_INT s2_random_cars[2] s2_random_car_drivers[2]
		LVAR_INT s2_random_car_model s2_random_car_class
		GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE s2_random_car_model s2_random_car_class
		IF NOT s2_random_car_model = -1
			CREATE_CAR s2_random_car_model 2448.31 -1392.34 23.68 s2_random_cars[0]
			CREATE_RANDOM_CHAR_AS_DRIVER s2_random_cars[0] s2_random_car_drivers[0]
			SET_CAR_HEADING s2_random_cars[0] 180.0
			CREATE_CAR s2_random_car_model 2453.7 -1408.14 23.68 s2_random_cars[1]
			CREATE_RANDOM_CHAR_AS_DRIVER s2_random_cars[1] s2_random_car_drivers[1]
			SET_CAR_HEADING s2_random_cars[1] 0.0
		ENDIF

		// remove player and Smoke from car before deleting it
		IF NOT IS_CAR_DEAD s2_player_car
			IF IS_CHAR_IN_CAR scplayer s2_player_car
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 2473.6 -1420.86 27.88
			ENDIF
			IF NOT IS_CHAR_DEAD s2_smoke
				IF IS_CHAR_IN_CAR s2_smoke s2_player_car
					WARP_CHAR_FROM_CAR_TO_COORD s2_smoke 2470.6 -1420.86 27.88
				ENDIF
			ENDIF
		ENDIF
		DELETE_CAR s2_player_car

		SET_PLAYER_CONTROL player1 OFF

		MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1

		LOAD_CUTSCENE SMOKE2B

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
		SWITCH_WIDESCREEN ON

		SET_CAR_DENSITY_MULTIPLIER 1.0

		REQUEST_MODEL LSV1
		REQUEST_MODEL LSV2
		REQUEST_MODEL LSV3
		REQUEST_MODEL SENTINEL
		REQUEST_MODEL MICRO_UZI
		//REQUEST_MODEL NRG500
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED LSV1
		   OR NOT HAS_MODEL_LOADED LSV2
		   OR NOT HAS_MODEL_LOADED LSV3
		   OR NOT HAS_MODEL_LOADED SENTINEL
		   OR NOT HAS_MODEL_LOADED MICRO_UZI
		   //OR NOT HAS_MODEL_LOADED NRG500
			WAIT 0
		ENDWHILE

		// preload Smoke's chase and then tired audio
		$s2_audio_text[0] = &SMO2b11
		s2_audio_sound[0] = SOUND_SMO2b11
		GENERATE_RANDOM_INT_IN_RANGE 0 3 s2_random_int
		IF s2_random_int = 0
			$s2_audio_text[1] = &SMO2_CA
			s2_audio_sound[1] = SOUND_SMO2_CA
		ENDIF
		IF s2_random_int = 1
			$s2_audio_text[1] = &SMO2_CB
			s2_audio_sound[1] = SOUND_SMO2_CB
		ENDIF
		IF s2_random_int = 2
			$s2_audio_text[1] = &SMO2_CC
			s2_audio_sound[1] = SOUND_SMO2_CC
		ENDIF
		LOAD_MISSION_AUDIO 1 s2_audio_sound[0]
		LOAD_MISSION_AUDIO 2 s2_audio_sound[1]
		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   OR NOT HAS_MISSION_AUDIO_LOADED 2
			WAIT 0
		ENDWHILE

		LOAD_SCENE 2471.99 -1402.32 27.82

		CREATE_CHAR PEDTYPE_MISSION2 LSV1 2471.99 -1402.32 27.82 s2_escaping_guy
		SET_CHAR_HEADING s2_escaping_guy 90.0
		SET_CHAR_HEALTH s2_escaping_guy 200
		SET_CHAR_MAX_HEALTH s2_escaping_guy 200
		SET_CHAR_MONEY s2_escaping_guy 500
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER s2_escaping_guy TRUE
		GIVE_WEAPON_TO_CHAR s2_escaping_guy WEAPONTYPE_PISTOL 99999
		SET_CHAR_ACCURACY s2_escaping_guy 5
		SET_CHAR_STAY_IN_SAME_PLACE s2_escaping_guy TRUE

		CLEAR_ALL_CHAR_RELATIONSHIPS s2_escaping_guy ACQUAINTANCE_TYPE_PED_DISLIKE
		CLEAR_ALL_CHAR_RELATIONSHIPS s2_escaping_guy ACQUAINTANCE_TYPE_PED_HATE

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			PERFORM_SEQUENCE_TASK s2_escaping_guy s2_whole_route_seq
		ENDIF

		SET_CHAR_COORDINATES scplayer 2473.99 -1402.32 27.82
		SET_CHAR_HEADING scplayer 90.0
		IF NOT IS_CHAR_DEAD s2_smoke
			SET_CHAR_COORDINATES s2_smoke 2474.99 -1402.32 27.82
			SET_CHAR_HEADING s2_smoke 90.0
		ENDIF

		SET_FIXED_CAMERA_POSITION 2460.9929 -1400.9906 24.5671 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2461.8762 -1401.4060 24.7836 JUMP_CUT

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		s2_cutscene_skipped = 1

		SKIP_CUTSCENE_START

		WAIT 2000

		FLUSH_ROUTE
		EXTEND_ROUTE s2_route_x[0] s2_route_y[0] s2_route_z[0]
		EXTEND_ROUTE s2_route_x[1] s2_route_y[1] s2_route_z[1]
		TASK_FOLLOW_POINT_ROUTE scplayer PEDMOVE_RUN FOLLOW_ROUTE_ONCE
		IF NOT IS_CHAR_DEAD s2_smoke
			OPEN_SEQUENCE_TASK s2_smoke_tired_seq
				TASK_GO_STRAIGHT_TO_COORD -1 s2_route_x[0] s2_route_y[0] s2_route_z[0] PEDMOVE_RUN -1
				TASK_PLAY_ANIM -1 IDLE_tired PED 4.0 TRUE FALSE FALSE FALSE -1
			CLOSE_SEQUENCE_TASK s2_smoke_tired_seq
			PERFORM_SEQUENCE_TASK s2_smoke s2_smoke_tired_seq
			CLEAR_SEQUENCE_TASK s2_smoke_tired_seq
		ENDIF

		PLAY_MISSION_AUDIO 1
		PRINT_NOW $s2_audio_text[0] 10000 1
		WAIT 4000
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS

		IF NOT s2_random_car_model = -1
			IF NOT IS_CHAR_DEAD s2_random_car_drivers[0]
			AND NOT IS_CAR_DEAD s2_random_cars[0]
				TASK_CAR_DRIVE_TO_COORD s2_random_car_drivers[0] s2_random_cars[0] 2448.31 -1429.92 23.68 10.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH
			ENDIF
		ENDIF

		// show player chasing escaping guy across street and Smoke stopping
		SET_FIXED_CAMERA_POSITION 2459.7158 -1406.5293 23.4481 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2459.6582 -1405.5365 23.5530 JUMP_CUT

		// wait for Smoke to play tired animation
		IF NOT IS_CHAR_DEAD s2_smoke
			GET_SCRIPT_TASK_STATUS s2_smoke PERFORM_SEQUENCE_TASK s2_task_status
			IF s2_task_status = PERFORMING_TASK
				GET_SEQUENCE_PROGRESS s2_smoke s2_route_seq_progress1
				WHILE s2_route_seq_progress1 <= 0
					WAIT 0
					IF NOT IS_CHAR_DEAD s2_smoke
						GET_SCRIPT_TASK_STATUS s2_smoke PERFORM_SEQUENCE_TASK s2_task_status
						IF s2_task_status = PERFORMING_TASK
							GET_SEQUENCE_PROGRESS s2_smoke s2_route_seq_progress1
						ENDIF
					ENDIF
				ENDWHILE
			ENDIF
		ENDIF

		PLAY_MISSION_AUDIO 2
		PRINT_NOW $s2_audio_text[1] 10000 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
			WAIT 0
		ENDWHILE
		CLEAR_MISSION_AUDIO 2
		CLEAR_PRINTS

		WAIT 1000

		CLEAR_AREA s2_route_x[2] s2_route_y[2] s2_route_z[2] 35.0 FALSE
		// set escaping guy ready for next shot
		IF NOT IS_CHAR_DEAD s2_escaping_guy
			SET_CHAR_COORDINATES s2_escaping_guy s2_route_x[2] s2_route_y[2] 23.74
			SET_CHAR_HEADING s2_escaping_guy 45.0
			PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_escaping_guy s2_whole_route_seq 0 3
		ENDIF

		// show escaping guy climbing over first fence
		SET_FIXED_CAMERA_POSITION 2406.65 -1358.0 25.33 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT s2_route_x[3] s2_route_y[3] s2_route_z[3] JUMP_CUT

		WAIT 1000

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			GET_SCRIPT_TASK_STATUS s2_escaping_guy PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_task_status
			IF s2_task_status = PERFORMING_TASK
				GET_SEQUENCE_PROGRESS_RECURSIVE s2_escaping_guy s2_route_seq_progress1 s2_route_seq_progress2
				WHILE s2_route_seq_progress1 = 0
				AND s2_route_seq_progress2 <= 3
					WAIT 0
					IF NOT IS_CHAR_DEAD s2_escaping_guy
						GET_SCRIPT_TASK_STATUS s2_escaping_guy PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_task_status
						IF s2_task_status = PERFORMING_TASK
							GET_SEQUENCE_PROGRESS_RECURSIVE s2_escaping_guy s2_route_seq_progress1 s2_route_seq_progress2
						ENDIF
					ENDIF
				ENDWHILE
			ENDIF
		ENDIF

		WAIT 2200

		// show escaping guy climbing over second fence
		SET_FIXED_CAMERA_POSITION 2404.81 -1354.37 25.71 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2401.40 -1356.79 25.31 JUMP_CUT

		WAIT 4500

		s2_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF s2_cutscene_skipped = 1
			
			// if cutscene skipped, set conditions to those at the end of the cutscene
			IF NOT IS_CHAR_DEAD s2_smoke
				IF IS_CHAR_IN_ANY_CAR s2_smoke
					WARP_CHAR_FROM_CAR_TO_COORD s2_smoke s2_route_x[0] s2_route_y[0] 23.06
				ELSE
					SET_CHAR_COORDINATES s2_smoke s2_route_x[0] s2_route_y[0] 23.06
				ENDIF
				SET_CHAR_HEADING s2_smoke 90.0
				// Smoke out of breath so player has to chase down escaping guy
				TASK_PLAY_ANIM s2_smoke IDLE_tired PED 4.0 TRUE FALSE FALSE FALSE -1
			ENDIF

			IF NOT IS_CHAR_DEAD s2_escaping_guy
				SET_CHAR_COORDINATES s2_escaping_guy 2390.73 -1355.69 23.74
				SET_CHAR_HEADING s2_escaping_guy 81.47
				PERFORM_SEQUENCE_TASK_FROM_PROGRESS s2_escaping_guy s2_whole_route_seq 0 7
			ENDIF

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS
		
		ENDIF

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			COPY_CHAR_DECISION_MAKER -1 s2_escaping_guy_decisions
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_VEHICLE_ON_FIRE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_DAMAGE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_SHOT_FIRED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_GUN_AIMED_AT
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_GOT_KNOCKED_OVER_BY_CAR
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_ACQUAINTANCE_PED_HATE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_ACQUAINTANCE_PED_DISLIKE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_DEAD_PED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s2_escaping_guy_decisions EVENT_SEXY_PED
			SET_CHAR_DECISION_MAKER s2_escaping_guy s2_escaping_guy_decisions
			ADD_BLIP_FOR_CHAR s2_escaping_guy s2_escaping_guy_blip
		ENDIF

		// create getaway car and driver for escaping guy at end of route
		CLEAR_AREA 2170.68 -1282.01 23.7 100.0 FALSE
		CREATE_CAR SENTINEL 2170.68 -1282.01 23.7 s2_getaway_car
		CREATE_CHAR_INSIDE_CAR s2_getaway_car PEDTYPE_MISSION2 LSV2 s2_getaway_car_driver
		SET_CAR_HEADING s2_getaway_car 357.03
		SET_CAR_ONLY_DAMAGED_BY_PLAYER s2_getaway_car TRUE
		SET_CAN_BURST_CAR_TYRES s2_getaway_car FALSE
		LOCK_CAR_DOORS s2_getaway_car CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER s2_getaway_car_driver TRUE
		GIVE_WEAPON_TO_CHAR s2_getaway_car_driver WEAPONTYPE_PISTOL 99999

		// create two guards to help escaping guy
		CLEAR_AREA 2353.86 -1356.92 23.41 5.0 FALSE
		CREATE_CHAR PEDTYPE_MISSION2 LSV2 2353.86 -1356.92 23.41 s2_escape_guards[0]
		CREATE_CHAR PEDTYPE_MISSION2 LSV3 2354.11 -1355.54 23.41 s2_escape_guards[1]
		SET_CHAR_HEADING s2_escape_guards[0] 90.0
		SET_CHAR_HEADING s2_escape_guards[1] 270.0
		s2_index = 0
		WHILE s2_index < 2
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER s2_escape_guards[s2_index] TRUE
			GIVE_WEAPON_TO_CHAR s2_escape_guards[s2_index] WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY s2_escape_guards[s2_index] 50
			CLEAR_ALL_CHAR_RELATIONSHIPS s2_escape_guards[s2_index] ACQUAINTANCE_TYPE_PED_DISLIKE
			CLEAR_ALL_CHAR_RELATIONSHIPS s2_escape_guards[s2_index] ACQUAINTANCE_TYPE_PED_HATE
			SET_CHAR_KINDA_STAY_IN_SAME_PLACE s2_escape_guards[s2_index] TRUE
			TASK_KILL_CHAR_ON_FOOT s2_escape_guards[s2_index] scplayer
			s2_index++
		ENDWHILE
		MARK_MODEL_AS_NO_LONGER_NEEDED LSV3

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer s2_route_x[1] s2_route_y[1] 23.05
		ELSE
			SET_CHAR_COORDINATES scplayer s2_route_x[1] s2_route_y[1] 23.05
		ENDIF
		SET_CHAR_HEADING scplayer 90.0

		SWITCH_ROADS_BACK_TO_ORIGINAL 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0
		SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0

		IF NOT s2_random_car_model = -1
			MARK_CAR_AS_NO_LONGER_NEEDED s2_random_cars[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED s2_random_car_drivers[0]
			MARK_CAR_AS_NO_LONGER_NEEDED s2_random_cars[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED s2_random_car_drivers[1]
		ENDIF

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

		CLEAR_CHAR_TASKS scplayer
			
	RETURN

// ****************************************************************
// 	Cutscene showing escaping guy running up stairs at destination
// ****************************************************************

	s2_play_mission_failed_cutscene:

		SET_PLAYER_CONTROL player1 OFF

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			SET_CHAR_PROOFS s2_escaping_guy TRUE TRUE TRUE TRUE TRUE
		ENDIF
		IF NOT IS_CHAR_DEAD s2_getaway_car_driver
			SET_CHAR_PROOFS s2_getaway_car_driver TRUE TRUE TRUE TRUE TRUE
		ENDIF
		IF NOT IS_CAR_DEAD s2_getaway_car
			SET_CAR_PROOFS s2_getaway_car TRUE TRUE TRUE TRUE TRUE
		ENDIF

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

		CLEAR_AREA s2_getaway_dest_x s2_getaway_dest_y s2_getaway_dest_z 100.0 FALSE
		SWITCH_ROADS_OFF 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73
		SWITCH_PED_ROADS_OFF 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73

		LVAR_INT s2_current_player_car
		LVAR_FLOAT s2_player_x s2_player_y s2_player_z s2_player_heading
		LVAR_FLOAT s2_ground_z s2_water_height
		GET_CHAR_COORDINATES scplayer s2_player_x s2_player_y s2_player_z
		GET_CHAR_HEADING scplayer s2_player_heading
		GET_WATER_HEIGHT_AT_COORDS s2_player_x s2_player_y FALSE s2_water_height
		GET_GROUND_Z_FOR_3D_COORD s2_player_x s2_player_y s2_player_z s2_ground_z
		IF s2_water_height > s2_ground_z
			s2_ground_z = s2_water_height
		ENDIF

		REQUEST_COLLISION s2_getaway_dest_x s2_getaway_dest_y
		LOAD_SCENE s2_getaway_dest_x s2_getaway_dest_y s2_getaway_dest_z

		SWITCH_ROADS_OFF 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53
		SWITCH_PED_ROADS_OFF 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53
		CLEAR_AREA 2092.91 -1097.01 24.53 20.0 FALSE
		SET_CHAR_COORDINATES scplayer 2092.91 -1097.01 -100.0
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer s2_current_player_car
			SET_CAR_HEADING s2_current_player_car 242.86
		ENDIF

		IF NOT IS_CAR_DEAD s2_getaway_car

			SET_CAR_COORDINATES s2_getaway_car s2_getaway_dest_x s2_getaway_dest_y -100.0
			SET_CAR_HEADING s2_getaway_car 50.74

			// make sure both peds are sitting in the car
			IF NOT IS_CHAR_DEAD s2_escaping_guy
				CLEAR_CHAR_TASKS_IMMEDIATELY s2_escaping_guy
				IF IS_CHAR_IN_ANY_CAR s2_escaping_guy
					WARP_CHAR_FROM_CAR_TO_COORD s2_escaping_guy 2115.98 -1059.43 24.75
				ENDIF
				WARP_CHAR_INTO_CAR_AS_PASSENGER s2_escaping_guy s2_getaway_car 0
			ENDIF
			IF NOT IS_CHAR_DEAD s2_getaway_car_driver
				CLEAR_CHAR_TASKS_IMMEDIATELY s2_getaway_car_driver
				IF IS_CHAR_IN_ANY_CAR s2_getaway_car_driver
					WARP_CHAR_FROM_CAR_TO_COORD s2_getaway_car_driver 2117.04 -1061.53 24.53
				ENDIF
				WARP_CHAR_INTO_CAR s2_getaway_car_driver s2_getaway_car
			ENDIF

		ENDIF

		WAIT 1000

		SWITCH_WIDESCREEN ON

		SET_FIXED_CAMERA_POSITION 2110.56 -1067.61 25.27 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2114.84 -1067.77 25.63 JUMP_CUT

		IF NOT IS_CHAR_DEAD s2_escaping_guy
			FLUSH_ROUTE
			EXTEND_ROUTE 2121.0 -1068.03 23.82
			EXTEND_ROUTE 2127.23 -1061.17 28.33
			EXTEND_ROUTE 2139.74 -1066.82 34.06
			EXTEND_ROUTE 2143.63 -1066.27 34.06
			EXTEND_ROUTE 2157.15 -1072.39 39.48
			LVAR_INT s2_stairs_seq
			OPEN_SEQUENCE_TASK s2_stairs_seq
				TASK_LEAVE_ANY_CAR -1
				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
			CLOSE_SEQUENCE_TASK s2_stairs_seq
			PERFORM_SEQUENCE_TASK s2_escaping_guy s2_stairs_seq
			CLEAR_SEQUENCE_TASK s2_stairs_seq
		ENDIF

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
			IF NOT IS_CHAR_DEAD s2_escaping_guy
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy run_gang1
					SET_CHAR_ANIM_SPEED s2_escaping_guy run_gang1 1.0
				ENDIF
			ENDIF
		ENDWHILE

		LVAR_INT s2_escaping_guy_halfway_up_stairs s2_escaping_guy_almost_at_top
		s2_escaping_guy_halfway_up_stairs = 0
		s2_escaping_guy_almost_at_top = 0

		s2_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		WHILE NOT s2_escaping_guy_almost_at_top = 1
			WAIT 0
			IF NOT IS_CHAR_DEAD s2_escaping_guy
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy run_gang1
					SET_CHAR_ANIM_SPEED s2_escaping_guy run_gang1 1.0
				ENDIF
				IF NOT s2_escaping_guy_halfway_up_stairs = 1
					IF LOCATE_CHAR_ON_FOOT_3D s2_escaping_guy 2143.63 -1066.27 34.06 4.0 4.0 4.0 FALSE
						SET_FIXED_CAMERA_POSITION 2131.97 -1067.61 33.57 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2137.38 -1068.03 34.43 JUMP_CUT
						s2_escaping_guy_halfway_up_stairs = 1
					ENDIF
				ENDIF
				IF NOT s2_escaping_guy_almost_at_top = 1
					IF LOCATE_CHAR_ON_FOOT_3D s2_escaping_guy 2157.15 -1072.39 39.48 6.0 6.0 4.0 FALSE
						s2_escaping_guy_almost_at_top = 1
					ENDIF
				ENDIF
			ENDIF
		ENDWHILE
		
		s2_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
			IF NOT IS_CHAR_DEAD s2_escaping_guy
				IF IS_CHAR_PLAYING_ANIM s2_escaping_guy run_gang1
					SET_CHAR_ANIM_SPEED s2_escaping_guy run_gang1 1.0
				ENDIF
			ENDIF
		ENDWHILE

		REQUEST_COLLISION s2_player_x s2_player_y
		LOAD_SCENE s2_player_x s2_player_y s2_ground_z

		SET_CHAR_COORDINATES scplayer s2_player_x s2_player_y s2_ground_z
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer s2_current_player_car
			SET_CAR_HEADING s2_current_player_car s2_player_heading
		ELSE
			SET_CHAR_HEADING scplayer s2_player_heading
		ENDIF

		DELETE_CHAR s2_escaping_guy
		DELETE_CHAR s2_getaway_car_driver
		DELETE_CAR s2_getaway_car

		SWITCH_ROADS_BACK_TO_ORIGINAL 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73
		SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73

		SWITCH_ROADS_BACK_TO_ORIGINAL 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53
		SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

	RETURN

// ***************************************************************
// 						Mission audio
// ***************************************************************

	s2_setup_audio_data:

		VAR_INT s2_random_int

		SWITCH s2_current_audio_needed

			CASE S2_TIRED_AUDIO

				GENERATE_RANDOM_INT_IN_RANGE 0 3 s2_random_int
				IF s2_random_int = 0
					$s2_audio_text[0] = &SMO2_CA
					s2_audio_sound[0] = SOUND_SMO2_CA
				ENDIF
				IF s2_random_int = 1
					$s2_audio_text[0] = &SMO2_CB
					s2_audio_sound[0] = SOUND_SMO2_CB
				ENDIF
				IF s2_random_int = 2
					$s2_audio_text[0] = &SMO2_CC
					s2_audio_sound[0] = SOUND_SMO2_CC
				ENDIF
				s2_total_audio_to_play = 1
				BREAK

			CASE S2_CHASE_AUDIO

				GENERATE_RANDOM_INT_IN_RANGE 0 3 s2_random_int
				IF s2_random_int = 0
					$s2_audio_text[0] = &SMO2_DA
					s2_audio_sound[0] = SOUND_SMO2_DA
				ENDIF
				IF s2_random_int = 1
					$s2_audio_text[0] = &SMO2_DB
					s2_audio_sound[0] = SOUND_SMO2_DB
				ENDIF
				IF s2_random_int = 2
					$s2_audio_text[0] = &SMO2_DC
					s2_audio_sound[0] = SOUND_SMO2_DC
				ENDIF
				s2_total_audio_to_play = 1
				BREAK

			CASE S2_CAR_AUDIO

				$s2_audio_text[0] = &SMO2_BA
				s2_audio_sound[0] = SOUND_SMO2_BA
				$s2_audio_text[1] = &SMO2_BB
				s2_audio_sound[1] = SOUND_SMO2_BB
				$s2_audio_text[2] = &SMO2_BC
				s2_audio_sound[2] = SOUND_SMO2_BC
				$s2_audio_text[3] = &SMO2_BD
				s2_audio_sound[3] = SOUND_SMO2_BD
				$s2_audio_text[4] = &SMO2_BE
				s2_audio_sound[4] = SOUND_SMO2_BE
				$s2_audio_text[5] = &SMO2_BF
				s2_audio_sound[5] = SOUND_SMO2_BF
				$s2_audio_text[6] = &SMO2_BG
				s2_audio_sound[6] = SOUND_SMO2_BG
				$s2_audio_text[7] = &SMO2_BH
				s2_audio_sound[7] = SOUND_SMO2_BH
				$s2_audio_text[8] = &SMO2_BJ
				s2_audio_sound[8] = SOUND_SMO2_BJ

				s2_total_audio_to_play = 9

				BREAK

			DEFAULT
				BREAK

		ENDSWITCH

		s2_audio_is_playing = 0
		s2_audio_index = 0
		s2_started_talking = 0

		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS

	RETURN

	s2_load_and_play_audio:

		IF s2_audio_is_playing = 0
		OR s2_audio_is_playing = 1
			IF s2_audio_index < s2_total_audio_to_play
				IF TIMERB > 200
					GOSUB s2_play_audio
				ENDIF
			ENDIF
		ENDIF

		IF s2_audio_is_playing = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				s2_audio_is_playing = 0
				s2_audio_index++
				s2_started_talking = 0
				CLEAR_PRINTS
				TIMERB = 0	
			ENDIF
		ENDIF

	RETURN

	s2_play_audio:

		IF s2_audio_is_playing = 0
			LOAD_MISSION_AUDIO 1 s2_audio_sound[s2_audio_index]
			s2_audio_is_playing = 1
		ENDIF
		IF s2_audio_is_playing = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW $s2_audio_text[s2_audio_index] 10000 1
				PLAY_MISSION_AUDIO 1
				s2_audio_is_playing = 2
			ENDIF
		ENDIF	
		
	RETURN

// ***************************************************************
// 	  Play audio for Smoke telling player to get back in the car
// ***************************************************************

	s2_play_smoke_get_in_audio:

		CLEAR_MISSION_AUDIO 2

		SWITCH s2_get_in_audio_stage
			CASE 0
				LOAD_MISSION_AUDIO 2 SOUND_SMOX_AA
				BREAK
			CASE 1
				LOAD_MISSION_AUDIO 2 SOUND_SMOX_AB
				BREAK
			CASE 2
				LOAD_MISSION_AUDIO 2 SOUND_SMOX_AC
				BREAK
			CASE 3
				LOAD_MISSION_AUDIO 2 SOUND_SMOX_AD
				BREAK
			CASE 4
				LOAD_MISSION_AUDIO 2 SOUND_SMOX_AE
				BREAK
			DEFAULT
				BREAK
		ENDSWITCH

		STOP_CHAR_FACIAL_TALK scplayer
		CLEAR_MISSION_AUDIO 1

		WHILE NOT HAS_MISSION_AUDIO_LOADED 2
			WAIT 0
			IF IS_CHAR_DEAD s2_smoke
			OR IS_CAR_DEAD s2_player_car
				RETURN
			ENDIF
			IF NOT s2_weapon_pickup_collected = 1
				IF HAS_PICKUP_BEEN_COLLECTED s2_weapon_pickup
					REMOVE_BLIP s2_weapon_pickup_blip
					s2_weapon_pickup_collected = 1
				ENDIF
			ENDIF
		ENDWHILE

		PLAY_MISSION_AUDIO 2

		SWITCH s2_get_in_audio_stage
			CASE 0
				PRINT_NOW ( SMOX_AA ) 3000 1
				BREAK
			CASE 1
				PRINT_NOW ( SMOX_AB ) 3000 1
				BREAK
			CASE 2
				PRINT_NOW ( SMOX_AC ) 3000 1
				BREAK
			CASE 3
				PRINT_NOW ( SMOX_AD ) 3000 1
				BREAK
			CASE 4
				PRINT_NOW ( SMOX_AE ) 3000 1
				BREAK
			DEFAULT
				BREAK
		ENDSWITCH

		WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
			WAIT 0
			IF IS_CHAR_DEAD s2_smoke
			OR IS_CAR_DEAD s2_player_car
				RETURN
			ENDIF
			IF NOT s2_weapon_pickup_collected = 1
				IF HAS_PICKUP_BEEN_COLLECTED s2_weapon_pickup
					REMOVE_BLIP s2_weapon_pickup_blip
					s2_weapon_pickup_collected = 1
				ENDIF
			ENDIF
		ENDWHILE

		s2_get_in_audio_stage++
		IF s2_get_in_audio_stage > 4
			s2_get_in_audio_stage = 0
		ENDIF

	RETURN

// ******************************** Mission smoke2 failed **********************************

mission_smoke2_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
trigger_phonecall_failed = 1
RETURN


// ******************************** Mission smoke2 passed **********************************

mission_smoke2_passed:

flag_smoke_mission_counter++
REGISTER_MISSION_PASSED ( SMOKE_2 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 3
//ADD_SCORE player1 100
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
RETURN
		

// *********************************** Mission cleanup *************************************

mission_cleanup_smoke2:

MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
//MARK_MODEL_AS_NO_LONGER_NEEDED NRG500
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
UNLOAD_SPECIAL_CHARACTER 1
REMOVE_CHAR_ELEGANTLY s2_smoke
REMOVE_PICKUP s2_weapon_pickup
REMOVE_BLIP s2_weapon_pickup_blip
REMOVE_BLIP s2_player_car_blip
REMOVE_BLIP s2_meet_blip
REMOVE_BLIP s2_escaping_guy_blip
CLEAR_SEQUENCE_TASK s2_whole_route_seq
CLEAR_SEQUENCE_TASK s2_route_seq1
CLEAR_SEQUENCE_TASK s2_route_seq2
CLEAR_SEQUENCE_TASK s2_route_seq3
CLEAR_SEQUENCE_TASK s2_route_seq4
CLEAR_SEQUENCE_TASK s2_smoke_tired_seq
CLEAR_SEQUENCE_TASK s2_stairs_seq
CLEAR_HELP
REMOVE_DECISION_MAKER s2_escaping_guy_decisions
REMOVE_DECISION_MAKER s2_smoke_decisions
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2458.50 -1410.63 24.06 2504.27 -1395.83 28.55
SWITCH_ROADS_BACK_TO_ORIGINAL 2488.0 -1431.93 25.41 2528.0 -1391.93 29.41
SWITCH_ROADS_BACK_TO_ORIGINAL 2252.89 -1286.37 22.25 2258.89 -1280.37 28.25
SWITCH_ROADS_BACK_TO_ORIGINAL 2164.51 -1287.88 20.0 2170.88 -1267.88 30.0
// roads around mission failed cutscene
SWITCH_ROADS_BACK_TO_ORIGINAL 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2108.5 -1080.59 4.73 2128.5 -1060.59 44.73
// roads around player at mission failed cutscene
SWITCH_ROADS_BACK_TO_ORIGINAL 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2082.91 -1107.01 4.53 2102.91 -1087.01 44.53
// roads at first scripted cutscene
SWITCH_ROADS_BACK_TO_ORIGINAL 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2441.38 -1405.59 15.0 2460.42 -1368.34 40.0
SET_CAR_DENSITY_MULTIPLIER 1.0
FLUSH_ROUTE
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


}