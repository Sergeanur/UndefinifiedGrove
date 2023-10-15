MISSION_START
// ------------------------------------------------------------------------------------------------
// Cesar Mission 1: Race
{

SCRIPT_NAME cesar1

// Begin...
GOSUB mission_start_cesar1
	//WRITE_DEBUG CHECKING_ARREST
	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_cesar1
	ENDIF

	//IF cs1_race_is_go = 1
		//WRITE_DEBUG CLEANUP_
		GOSUB mission_cleanup_cesar1
	//ENDIF

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	LVAR_INT cs1_stage cs1_in_car cs1_cut 
	LVAR_INT cs1_cesar_status
	LVAR_INT cs1_cesar_slow

// ---- Dialogue
	LVAR_INT cs1_dialogue_playing cs1_random_dialogue_last[2]
	VAR_INT cs1_random_dialogue

// ---- Blips
	LVAR_INT cs1_contact_blip

// ---- Timers / Counters
	LVAR_INT cs1_timer_start cs1_timer_end cs1_timer_diff 
	LVAR_INT cs1_text_timer_start cs1_text_timer_end cs1_text_timer_diff
// ---- Sequences
	LVAR_INT cs1_cesar_drive
	 
// ---- Coords
	LVAR_FLOAT cs1_meet_x cs1_meet_y cs1_meet_z
	LVAR_FLOAT cs1_cesar_start_x cs1_cesar_start_y cs1_cesar_start_z cs1_player_start_x cs1_player_start_y cs1_player_start_z

// ---- Dialogue
	LVAR_TEXT_LABEL cs1_text[17]
	LVAR_INT cs1_audio_char
	LVAR_INT cs1_audio[17] cs1_counter cs1_audio_playing cs1_audio_slot	cs1_alt_slot cs1_ahead_counter

// ------------------------------------------------------------------------------------------------
// -------- Vehicles
	cs1_load_all:
		REQUEST_MODEL VLA1 // Drone 1
		REQUEST_MODEL REMINGTN // Drone 2
		REQUEST_MODEL MAJESTIC
		REQUEST_MODEL SAVANNA // Lowrider
		REQUEST_MODEL BLADE
		LOAD_ALL_MODELS_NOW
		
		WHILE NOT HAS_MODEL_LOADED VLA1
		OR NOT HAS_MODEL_LOADED REMINGTN
		OR NOT HAS_MODEL_LOADED MAJESTIC
		OR NOT HAS_MODEL_LOADED SAVANNA
		OR NOT HAS_MODEL_LOADED BLADE
		WAIT 0
		ENDWHILE
		
		LOAD_SPECIAL_CHARACTER 1 cesar
		LOAD_SPECIAL_CHARACTER 2 kendl

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
		WAIT 0
		ENDWHILE
	RETURN

	cs1_player_car_create:
		LVAR_INT  cs1_player_car_blip cs1_player_car_health cs1_player_car_health_end //
		LVAR_FLOAT cs1_player_car_x cs1_player_car_y cs1_player_car_z cs1_player_car_h
		ADD_BLIP_FOR_CAR cs1_player_car cs1_player_car_blip
	RETURN
// -------- Cesar, Kendl, & Cesar's Car
	ADD_BLIP_FOR_COORD cesarX cesarY cesarZ cs1_contact_blip

	cs1_cesar_car_create:
		LVAR_INT cs1_cesar cs1_cesar_blip
		LVAR_INT cs1_cesar_car cs1_cesar_car_blip cs1_kendl 
		LVAR_FLOAT cs1_cesar_car_x cs1_cesar_car_y cs1_cesar_car_z cs1_cesar_car_h cs1_distance cs1_cesar_car_speed
	  
		REQUEST_CAR_RECORDING 172
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 172
			WAIT 0
		ENDWHILE

		cs1_cesar_car_x = 1793.6869 
		cs1_cesar_car_y = -2138.6863  
		cs1_cesar_car_z = 12.5547 
		cs1_cesar_car_h = 0.1899 
		CLEAR_AREA cs1_cesar_car_x cs1_cesar_car_y cs1_cesar_car_z 20.5 TRUE
		CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA &_LVA4L__

		CREATE_CAR SAVANNA cs1_cesar_car_x cs1_cesar_car_y cs1_cesar_car_z cs1_cesar_car
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs1_cesar_car FALSE
		SUPPRESS_CAR_MODEL SAVANNA
		
		CHANGE_CAR_COLOUR cs1_cesar_car 3 3
		CREATE_CHAR_INSIDE_CAR cs1_cesar_car PEDTYPE_CIVMALE SPECIAL01 cs1_cesar
		CREATE_CHAR_AS_PASSENGER cs1_cesar_car PEDTYPE_CIVFEMALE SPECIAL02 0 cs1_kendl 
		SET_CAR_HEADING cs1_cesar_car cs1_cesar_car_h
  		SET_CAR_CRUISE_SPEED cs1_cesar_car 35.0
		SET_CAR_DRIVING_STYLE cs1_cesar_car DRIVINGMODE_PLOUGHTHROUGH
		SET_CAR_HEAVY cs1_cesar_car TRUE
		LOCK_CAR_DOORS cs1_cesar_car CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_PROOFS cs1_cesar_car TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES cs1_cesar_car FALSE
		SET_CHAR_PROOFS cs1_cesar TRUE TRUE TRUE TRUE TRUE
		SET_CHAR_PROOFS cs1_kendl TRUE TRUE TRUE TRUE TRUE
		
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_cesar FALSE
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_kendl FALSE

		
	RETURN
	ADD_BLIP_FOR_CAR cs1_cesar_car cs1_cesar_car_blip 
// --------
// ---- Cesar's Pals
	cs1_drone_car_create:
		LVAR_INT cs1_drone[4] cs1_drone_car[4] 		
		LVAR_FLOAT cs1_drone_car_x[4] cs1_drone_car_y[4] cs1_drone_car_z[4] cs1_drone_car_h[4]

		cs1_drone_car_x[0] = 1492.3043 
		cs1_drone_car_y[0] = -1869.0234 
		cs1_drone_car_z[0] = 12.3828 
		cs1_drone_car_h[0] = 90.0
		CLEAR_AREA cs1_drone_car_x[0] cs1_drone_car_y[0] cs1_drone_car_z[0] 2.5 TRUE
		CREATE_CAR MAJESTIC cs1_drone_car_x[0] cs1_drone_car_y[0] cs1_drone_car_z[0] cs1_drone_car[0]
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs1_drone_car[0] FALSE
		CHANGE_CAR_COLOUR cs1_drone_car[0] 6 0
		SET_CAR_HEADING cs1_drone_car[0] cs1_drone_car_h[0]
		CREATE_CHAR_INSIDE_CAR cs1_drone_car[0] PEDTYPE_GANG_SMEX VLA1 cs1_drone[0]
		SET_CHAR_PROOFS cs1_drone[0] TRUE TRUE TRUE TRUE TRUE
  		SET_CAR_CRUISE_SPEED cs1_drone_car[0] 35.0
		SET_CAR_DRIVING_STYLE cs1_drone_car[0] DRIVINGMODE_PLOUGHTHROUGH
		LOCK_CAR_DOORS cs1_drone_car[0] CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_PROOFS cs1_drone_car[0] TRUE TRUE TRUE FALSE TRUE
		SET_CAN_BURST_CAR_TYRES cs1_drone_car[0] FALSE

		cs1_drone_car_x[1] = 1492.3043 
		cs1_drone_car_y[1] = -1875.0234 
		cs1_drone_car_z[1] = 12.3828 
		cs1_drone_car_h[1] = 90.0
		CLEAR_AREA cs1_drone_car_x[1] cs1_drone_car_y[1] cs1_drone_car_z[1] 2.5 TRUE
		CREATE_CAR REMINGTN cs1_drone_car_x[1] cs1_drone_car_y[1] cs1_drone_car_z[1] cs1_drone_car[1]
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs1_drone_car[1] FALSE
		SUPPRESS_CAR_MODEL REMINGTN 
		CHANGE_CAR_COLOUR cs1_drone_car[1] 8 0 
		SET_CAR_HEADING cs1_drone_car[1] cs1_drone_car_h[1]
		CREATE_CHAR_INSIDE_CAR cs1_drone_car[1] PEDTYPE_GANG_SMEX VLA1 cs1_drone[1]
		SET_CHAR_PROOFS cs1_drone[1] TRUE TRUE TRUE TRUE TRUE
  		SET_CAR_CRUISE_SPEED cs1_drone_car[1] 35.0
		SET_CAR_DRIVING_STYLE cs1_drone_car[1] DRIVINGMODE_PLOUGHTHROUGH
		LOCK_CAR_DOORS cs1_drone_car[1] CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_PROOFS cs1_drone_car[1] TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES cs1_drone_car[1] FALSE

		cs1_drone_car_x[2] = 1504.3043 
		cs1_drone_car_y[2] = -1869.0234 
		cs1_drone_car_z[2] = 12.3828 
		cs1_drone_car_h[2] = 90.0
		CLEAR_AREA cs1_drone_car_x[2] cs1_drone_car_y[2] cs1_drone_car_z[2] 2.5 TRUE
		CREATE_CAR MAJESTIC cs1_drone_car_x[2] cs1_drone_car_y[2] cs1_drone_car_z[2] cs1_drone_car[2] 
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs1_drone_car[2] FALSE
		SUPPRESS_CAR_MODEL MAJESTIC 
		CHANGE_CAR_COLOUR cs1_drone_car[2] 2 1
		SET_CAR_HEADING cs1_drone_car[2] cs1_drone_car_h[2]
		CREATE_CHAR_INSIDE_CAR cs1_drone_car[2] PEDTYPE_GANG_SMEX VLA1 cs1_drone[2]
		SET_CHAR_PROOFS cs1_drone[2] TRUE TRUE TRUE TRUE TRUE
  		SET_CAR_CRUISE_SPEED cs1_drone_car[2] 35.0
		SET_CAR_DRIVING_STYLE cs1_drone_car[2] DRIVINGMODE_PLOUGHTHROUGH
		LOCK_CAR_DOORS cs1_drone_car[2] CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_PROOFS cs1_drone_car[2] TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES cs1_drone_car[2] FALSE

		cs1_drone_car_x[3] = 1504.3043 
		cs1_drone_car_y[3] = -1875.0234 
		cs1_drone_car_z[3] = 12.3828 
		cs1_drone_car_h[3] = 90.0
		CLEAR_AREA cs1_drone_car_x[3] cs1_drone_car_y[3] cs1_drone_car_z[3] 2.5 TRUE
		CREATE_CAR BLADE cs1_drone_car_x[3] cs1_drone_car_y[3] cs1_drone_car_z[3] cs1_drone_car[3]
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs1_drone_car[3] FALSE
		SET_CAR_HEADING cs1_drone_car[3] cs1_drone_car_h[3]
		CREATE_CHAR_INSIDE_CAR cs1_drone_car[3] PEDTYPE_GANG_SMEX VLA1 cs1_drone[3]
		
  		SET_CAR_CRUISE_SPEED cs1_drone_car[3] 35.0
		SET_CAR_DRIVING_STYLE cs1_drone_car[3] DRIVINGMODE_PLOUGHTHROUGH
		LOCK_CAR_DOORS cs1_drone_car[3] CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_PROOFS cs1_drone_car[3] TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES cs1_drone_car[3] FALSE
	RETURN

// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_cesar1:
	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1
	WAIT 0

LOAD_MISSION_TEXT CESAR1
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	cs1_stage = 0
	cs1_in_car = 0
	cs1_cut = -1
	cs1_race_is_go = 0
	cs1_dialogue_playing = 2
	cs1_cesar_slow = 0
	cs1_counter = 0
	cs1_ahead_counter = 1
	cs1_audio_slot = 1
	cs1_alt_slot = 2
	cs1_audio_playing = 0
	cs1_random_dialogue_last[0] = 0
	cs1_random_dialogue_last[1] = 4
 	
// ---- Coords
	cs1_meet_x = 1820.44 
	cs1_meet_y = -2117.32
	cs1_meet_z = 13.09

	cs1_player_start_x = 1518.3043 
	cs1_player_start_y = -1875.0234 
	cs1_player_start_z = 12.3828 

	cs1_cesar_start_x = 1518.3043 
	cs1_cesar_start_y = -1869.0234 
	cs1_cesar_start_z = 12.3828 

// ---- Dialogue Text
	$cs1_text[1] = CES1_AA // Hey, keep up, holmes!
	$cs1_text[2] = CES1_AB // Not far now, eh!
	$cs1_text[3] = CES1_AC // C'mon, Carl, follow us!
	$cs1_text[4] = CES1_AD // Nearly there, Carl!

	$cs1_text[5] = CES1_BA // You gonna have to drive better than that, CJ!
	$cs1_text[6] = CES1_BB // Hey, CJ, you wrecking your car, eh!
	$cs1_text[7] = CES1_BC // Carl, your driving is an embarrassment!
	$cs1_text[8] = CES1_BD // Oh, Carl, I thought you said you could drive!

	$cs1_text[9] = CES1_CA // Carl! What are you doing?
	$cs1_text[10] = CES1_CB // Watch out, Carl!
	$cs1_text[11] = CES1_CC // Carl, you idiot!
	$cs1_text[12] = CES1_CD // Hey, Carl, don't play dirty!
	$cs1_text[13] = CES1_CE // Hey, watch out, Carl!
	$cs1_text[14] = CES1_CF // You trying to kill your sister?
	//$cs1_text[15] = CES1_CG // Here we go, yeeehaaa!
	//$cs1_text[16] = CES1_CH // Goodluck, holmes!

// ---- Dialogue Audio
	cs1_audio[1] = SOUND_CES1_AA // Hey, keep up, holmes!
	cs1_audio[2] = SOUND_CES1_AB // Not far now, eh!
	cs1_audio[3] = SOUND_CES1_AC // C'mon, Carl, follow us!
	cs1_audio[4] = SOUND_CES1_AD // Nearly there, Carl!

	cs1_audio[5] = SOUND_CES1_BA // You gonna have to drive better than that, CJ!
	cs1_audio[6] = SOUND_CES1_BB // Hey, CJ, you wrecking your car, eh!
	cs1_audio[7] = SOUND_CES1_BC // Carl, your driving is an embarrassment!
	cs1_audio[8] = SOUND_CES1_BD // Oh, Carl, I thought you said you could drive!

	cs1_audio[9] = SOUND_CES1_CA // Carl! What are you doing?
	cs1_audio[10] = SOUND_CES1_CB // Watch out, Carl!
	cs1_audio[11] = SOUND_CES1_CC // Carl, you idiot!
	cs1_audio[12] = SOUND_CES1_CD // Hey, Carl, don't play dirty!
	cs1_audio[13] = SOUND_CES1_CE // Hey, watch out, Carl!
	cs1_audio[14] = SOUND_CES1_CF // You trying to kill your sister?
	//cs1_audio[15] = SOUND_CES1_CG // Here we go, yeeehaaa!
	//cs1_audio[16] = SOUND_CES1_CH // Goodluck, holmes!

// ------------------------------------------------------------------------------------------------
// Request Models 

// ------------------------------------------------------------------------------------------------
// Entity GoSubs

SET_CAR_DENSITY_MULTIPLIER 0.0
STORE_CAR_CHAR_IS_IN scplayer cs1_player_car
SET_CAR_COORDINATES cs1_player_car cesarX cesarY cesarZ
SET_CAR_HEADING cs1_player_car 270.0
//SET_FADING_COLOUR 0 0 0
//DO_FADE 2000 FADE_OUT
//
//WHILE GET_FADING_STATUS
//	WAIT 0
//ENDWHILE 
CLEAR_AREA 1796.0 -2118.0 14.0 25.0 TRUE

LOAD_CUTSCENE CESAR1A
 
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
CLEAR_AREA 1796.0 -2118.0 14.0 15.0 TRUE

SET_CAR_DENSITY_MULTIPLIER 0.5


GOSUB cs1_load_all

//DO_FADE 1000 FADE_IN

// ------------------------------------------------------------------------------------------------
// Task Sequences

// ------------------------------------------------------------------------------------------------
// Starting blip...
 
//GOSUB cs1_player_car_create

SET_FADING_COLOUR 0 0 0

GET_GAME_TIMER cs1_text_timer_start

SET_CAMERA_BEHIND_PLAYER

SET_PLAYER_CONTROL player1 OFF
//PRINT_NOW ( CES1_01 ) 10000 1 // Pick up your car

// ------------------------------------------------------------------------------------------------
// Main Loop
cs1_main_loop:

WAIT 0

// ---- Load & Play Dialogue...
IF NOT cs1_counter = 0
	IF cs1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED cs1_alt_slot
			CLEAR_MISSION_AUDIO cs1_alt_slot
		ENDIF
		cs1_audio_playing = 1
	ENDIF

	IF cs1_audio_playing = 1
		LOAD_MISSION_AUDIO cs1_audio_slot cs1_audio[cs1_counter]
		//GOSUB cs1_dialogue_pos
		//ATTACH_MISSION_AUDIO_TO_PED cs1_audio_slot cs1_audio_char
		cs1_audio_playing = 2
	ENDIF

	IF cs1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED cs1_audio_slot
			PLAY_MISSION_AUDIO cs1_audio_slot
			PRINT_NOW $cs1_text[cs1_counter] 10000 1
			cs1_audio_playing = 3
		ENDIF
	ENDIF

	IF cs1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED cs1_audio_slot
			CLEAR_THIS_PRINT $cs1_text[cs1_counter]
			IF cs1_audio_slot = 1
				cs1_audio_slot = 2
				cs1_alt_slot = 1
			ELSE
				cs1_audio_slot = 1
				cs1_alt_slot = 2
			ENDIF
			cs1_counter = 0
			cs1_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED cs1_alt_slot
				IF cs1_counter < 60
					cs1_ahead_counter = cs1_counter + 1
					LOAD_MISSION_AUDIO cs1_alt_slot cs1_audio[cs1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	flag_cesar_mission_counter ++
	GOTO mission_passed_cesar1
ENDIF

IF cs1_stage > 0
	GOSUB cs1_in_car_check
ENDIF

IF cs1_stage > 0
AND cs1_stage < 666
	IF IS_CAR_DEAD cs1_cesar_car
		PRINT_NOW ( CES1_09 ) 7000 1
		GOTO mission_failed_cesar1
	ENDIF
ENDIF	  

// Meet Cesar... ...switch to cut... he gets in his car...
IF cs1_stage = 0
	IF cs1_cut = -1
		IF NOT IS_CAR_DEAD cs1_player_car
		AND NOT IS_CHAR_DEAD scplayer
			WARP_CHAR_INTO_CAR scplayer cs1_player_car
			CLEAR_PRINTS
			GET_GAME_TIMER cs1_timer_start
			GOSUB cs1_cesar_car_create
			SWITCH_WIDESCREEN ON
			DO_FADE 500 FADE_IN
			DO_FADE 500 FADE_IN
			DO_FADE 500 FADE_IN
			cs1_cut = 0
		ENDIF
	ENDIF
	IF cs1_cut = 0
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD cs1_player_car
			IF IS_CHAR_IN_CAR scplayer cs1_player_car 
				IF NOT IS_CHAR_DEAD cs1_cesar
				AND NOT IS_CAR_DEAD cs1_cesar_car
					GET_GAME_TIMER cs1_timer_end
					cs1_timer_diff = cs1_timer_end - cs1_timer_start
					IF NOT IS_CHAR_DEAD cs1_cesar
					AND NOT IS_CHAR_DEAD cs1_kendl
						SET_CHAR_PROOFS cs1_cesar TRUE TRUE TRUE TRUE TRUE
						SET_CHAR_PROOFS cs1_kendl TRUE TRUE TRUE TRUE TRUE
						
						SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_cesar FALSE
						SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_kendl FALSE

						IF NOT IS_CAR_DEAD cs1_cesar_car
							START_PLAYBACK_RECORDED_CAR cs1_cesar_car 172
						ENDIF
						 
					   	SET_FIXED_CAMERA_POSITION 1786.3308 -2120.0657 12.8592 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 1787.3093 -2120.1292 13.0548 JUMP_CUT
						GET_CAR_HEALTH cs1_player_car cs1_player_car_health
						cs1_cut = 2
					ENDIF
			   	ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_DEAD cs1_cesar
	AND NOT IS_CAR_DEAD cs1_cesar_car
		IF cs1_cut = 1
			IF LOCATE_CHAR_IN_CAR_3D cs1_cesar 1802.1261 -2110.1030 12.3902 4.0 4.0 4.0 FALSE
				SET_FIXED_CAMERA_POSITION 1825.2024 -2100.5496 18.3156 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1824.6929 -2101.3733 18.0676 JUMP_CUT
			   	cs1_cut = 2
			ENDIF
		ENDIF
		IF cs1_cut = 2
			IF NOT IS_CAR_DEAD cs1_cesar_car
				IF IS_CHAR_IN_CAR cs1_cesar cs1_cesar_car
					IF LOCATE_CHAR_IN_CAR_3D cs1_cesar cs1_meet_x cs1_meet_y cs1_meet_z 25.0 25.0 25.0 FALSE
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR cs1_cesar_car

							OPEN_SEQUENCE_TASK cs1_cesar_drive
								TASK_CAR_DRIVE_TO_COORD -1 cs1_cesar_car 1533.0076 -1877.2430 12.3828 20.0 MODE_ACCURATE FALSE DRIVINGMODE_AVOIDCARS
								TASK_CAR_DRIVE_TO_COORD -1 cs1_cesar_car cs1_cesar_start_x cs1_cesar_start_y cs1_cesar_start_z 12.0 MODE_ACCURATE FALSE DRIVINGMODE_AVOIDCARS
							CLOSE_SEQUENCE_TASK cs1_cesar_drive
							PERFORM_SEQUENCE_TASK cs1_cesar cs1_cesar_drive
							ADD_STUCK_CAR_CHECK_WITH_WARP cs1_cesar_car 1.0 2000 TRUE TRUE TRUE 2
							REMOVE_BLIP	cs1_cesar_car_blip
							ADD_BLIP_FOR_CAR cs1_cesar_car cs1_cesar_car_blip 
							SET_BLIP_AS_FRIENDLY cs1_cesar_car_blip TRUE
							RESTORE_CAMERA_JUMPCUT
							SET_CAMERA_BEHIND_PLAYER
							SET_PLAYER_CONTROL player1 ON
							SWITCH_WIDESCREEN OFF
							cs1_in_car = 1
							PRINT_NOW ( CES1_04 ) 5000 2 // Follow Cesar to the race start.
							cs1_dialogue_playing = 2
							GET_GAME_TIMER cs1_text_timer_start
							// Add stuck check
							cs1_cut = 3

						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF cs1_cut = 3
			GOSUB cs1_drone_car_create
			cs1_stage = 1
	   	ENDIF
	ENDIF 
ENDIF

// Drive to race start...
IF cs1_stage = 1
	IF cs1_cut = 3
		GET_GAME_TIMER cs1_text_timer_start
		IF NOT IS_CHAR_DEAD cs1_drone[0]
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_drone[0] FALSE
		ENDIF
		IF NOT IS_CHAR_DEAD cs1_drone[1]
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_drone[1] FALSE
		ENDIF
		IF NOT IS_CHAR_DEAD cs1_drone[2]
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_drone[2] FALSE
		ENDIF
		IF NOT IS_CHAR_DEAD cs1_drone[3]
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cs1_drone[3] FALSE
		ENDIF
		cs1_cut = 4
	ENDIF


	IF cs1_cut = 4
		IF NOT IS_CAR_DEAD cs1_player_car
			IF NOT IS_CHAR_DEAD cs1_cesar
			AND NOT IS_CAR_DEAD	cs1_cesar_car
				GET_SCRIPT_TASK_STATUS cs1_cesar TASK_CAR_DRIVE_TO_COORD cs1_cesar_status
				IF cs1_cesar_status = FINISHED_TASK
					IF DOES_CAR_HAVE_STUCK_CAR_CHECK cs1_cesar_car 
						REMOVE_STUCK_CAR_CHECK cs1_cesar_car
					ENDIF
				ENDIF	
				IF NOT LOCATE_CHAR_IN_CAR_3D cs1_cesar cs1_cesar_start_x cs1_cesar_start_y cs1_cesar_start_z 20.0 20.0 20.0 FALSE
					GET_CAR_COORDINATES cs1_player_car cs1_player_car_x cs1_player_car_y cs1_player_car_z 
					GET_CAR_COORDINATES cs1_cesar_car cs1_cesar_car_x cs1_cesar_car_y cs1_cesar_car_z
					GET_DISTANCE_BETWEEN_COORDS_2D cs1_player_car_x cs1_player_car_y cs1_cesar_car_x cs1_cesar_car_y cs1_distance
					cs1_cesar_car_speed = 1000.0 / cs1_distance
					IF cs1_cesar_car_speed > 30.0
						cs1_cesar_car_speed = 30.0
					ENDIF

					IF cs1_cesar_car_speed < 12.0
						cs1_cesar_car_speed = 12.0
					ENDIF

					SET_CAR_CRUISE_SPEED cs1_cesar_car cs1_cesar_car_speed
				ELSE
					SET_CAR_CRUISE_SPEED cs1_cesar_car 12.0
				ENDIF

				IF cs1_cesar_slow = 0
					IF LOCATE_CHAR_IN_CAR_3D cs1_cesar cs1_cesar_start_x cs1_cesar_start_y cs1_cesar_start_z 10.0 10.0 10.0 FALSE
						REMOVE_BLIP cs1_cesar_car_blip
						ADD_BLIP_FOR_COORD cs1_player_start_x cs1_player_start_y cs1_player_start_z cs1_cesar_blip 
						PRINT_NOW ( CES1_05 ) 10000 1 // Drive onto the starting grid.
						cs1_dialogue_playing = 5
						cs1_cesar_slow = 1
					ENDIF
				ENDIF

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cs1_cesar 15.0 15.0 15.0 FALSE
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cs1_cesar 10.0 10.0 10.0 FALSE
						IF cs1_dialogue_playing	= 0
							IF cs1_random_dialogue_last[0] < 4
								GET_GAME_TIMER cs1_text_timer_start
								cs1_dialogue_playing = 1
								CLEAR_PRINTS
								cs1_random_dialogue = cs1_random_dialogue_last[0]
								++cs1_random_dialogue_last[0] 
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			GET_CAR_HEALTH cs1_player_car cs1_player_car_health_end
			IF cs1_player_car_health_end < cs1_player_car_health
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cs1_cesar 15.0 15.0 15.0 FALSE
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cs1_cesar 10.0 10.0 10.0 FALSE
						IF cs1_dialogue_playing	= 0
							IF cs1_random_dialogue_last[1] < 8
								cs1_player_car_health = cs1_player_car_health_end
								GET_GAME_TIMER cs1_text_timer_start
								cs1_dialogue_playing = 1
								cs1_random_dialogue = cs1_random_dialogue_last[1]
								++cs1_random_dialogue_last[1] 
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF cs1_dialogue_playing = 1
				IF cs1_random_dialogue = 0
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 1	// Hey, keep up, holmes!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 1
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 2	// Not far now, eh!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 2
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 3	// C'mon, Carl, follow us!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 3
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 4	// Nearly there, Carl!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF

				IF cs1_random_dialogue = 4
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 5	// You gonna have to drive better than that, CJ!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 5
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 6	// Hey, CJ, you wrecking your car, eh!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 6
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 7	// Carl, your driving is an embarrassment!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 7
					IF cs1_audio_playing = 0
					AND cs1_counter = 0
						cs1_counter = 8	// Oh, Carl, I thought you said you could drive!
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF cs1_random_dialogue = 8
					PRINT_NOW ( CES1_04 ) 100 1 // ~s~Follow ~b~Cesar ~s~to the race start.
					GET_GAME_TIMER cs1_text_timer_end 
					cs1_text_timer_diff = cs1_text_timer_end - cs1_text_timer_start
					IF cs1_text_timer_diff > 5000
						GET_GAME_TIMER cs1_text_timer_start
						cs1_dialogue_playing = 2								
					ENDIF 
				ENDIF
			ENDIF

			IF cs1_dialogue_playing = 2
				GET_GAME_TIMER cs1_text_timer_end 
				cs1_text_timer_diff = cs1_text_timer_end - cs1_text_timer_start
				IF cs1_text_timer_diff > 6000
					IF NOT IS_CAR_DEAD	cs1_drone_car[0]
					AND NOT IS_CAR_DEAD	cs1_drone_car[1]
					AND NOT IS_CAR_DEAD	cs1_drone_car[2]
					AND NOT IS_CAR_DEAD	cs1_drone_car[3]
						FREEZE_CAR_POSITION cs1_drone_car[0] TRUE
						FREEZE_CAR_POSITION cs1_drone_car[1] TRUE
						FREEZE_CAR_POSITION cs1_drone_car[2] TRUE
						FREEZE_CAR_POSITION cs1_drone_car[3] TRUE
					ENDIF

					cs1_dialogue_playing = 0
				ENDIF
			ENDIF
  	   	ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD cs1_cesar_car
	AND NOT IS_CHAR_DEAD cs1_cesar
		IF NOT IS_CAR_DEAD cs1_player_car
		AND NOT IS_CHAR_DEAD scplayer
			IF IS_CHAR_IN_CAR scplayer cs1_player_car
				IF LOCATE_CHAR_IN_CAR_3D scplayer cs1_player_start_x cs1_player_start_y cs1_player_start_z 4.0 4.0 4.0 TRUE
					SET_PLAYER_CONTROL player1 OFF
			   		DO_FADE 1000 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					GOTO mission_passed_cesar1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF cs1_stage = 666
	GOTO mission_failed_cesar1
ENDIF

GOTO cs1_main_loop 
// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_cesar1:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
MARK_MODEL_AS_NO_LONGER_NEEDED VLA1
MARK_MODEL_AS_NO_LONGER_NEEDED MAJESTIC
MARK_MODEL_AS_NO_LONGER_NEEDED REMINGTN
MARK_MODEL_AS_NO_LONGER_NEEDED BLADE
//flag_player_on_mission = 0



RETURN

// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_cesar1:
cs1_race_is_go = 1
//flag_cesar_mission_counter ++
////REGISTER_MISSION_PASSED ( )
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 200 5000 1 //"Mission Passed!"
//ADD_SCORE player1 200
//CLEAR_WANTED_LEVEL player1
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_cesar1:

// ---- Entities
// ---- Blips
	REMOVE_BLIP cs1_player_car_blip
	REMOVE_BLIP cs1_cesar_car_blip
	REMOVE_BLIP cs1_cesar_blip
	REMOVE_BLIP cs1_contact_blip
	MARK_MODEL_AS_NO_LONGER_NEEDED VLA1
//	REMOVE_BLIP	cs1_drone_car_blip[0]
//	REMOVE_BLIP	cs1_drone_car_blip[1]
//	REMOVE_BLIP	cs1_drone_car_blip[2]
//	REMOVE_BLIP	cs1_drone_car_blip[3]
	DELETE_CAR cs1_cesar_car
	DELETE_CAR cs1_drone_car[0]
	DELETE_CAR cs1_drone_car[1]
	DELETE_CAR cs1_drone_car[2]
	DELETE_CAR cs1_drone_car[3]

	UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2

// ---- Models
		//SET_PLAYER_GROUP_RECRUITMENT player1 TRUE

// ----	Clear Script Stuff
		

		//CLEAR_ONSCREEN_TIMER cs1_car_timer
		flag_player_on_mission = 0
		GET_GAME_TIMER timer_mobile_start
		MISSION_HAS_FINISHED
		IF NOT cs1_stage = 666
			DO_FADE 0 FADE_OUT
		ENDIF
		
RETURN
// ------------------------------------------------------------------------------------------------
// ---- Sub-routines

// ------------------------------------------------------------------------------------------------
// Starting Cutscene





cs1_in_car_check:
	//Get Player's car...
	IF IS_CAR_DEAD cs1_player_car
		PRINT_NOW ( CES1_08 ) 5000 1
		cs1_stage = 666
	ENDIF
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD cs1_player_car
		IF cs1_in_car = 1
			IF NOT IS_CHAR_IN_CAR scplayer cs1_player_car
				IF cs1_stage = 1
					IF cs1_cesar_slow = 0
						REMOVE_BLIP cs1_cesar_car_blip
					ENDIF
					IF cs1_cesar_slow = 1
						REMOVE_BLIP cs1_cesar_blip
					ENDIF
					cs1_dialogue_playing = 10 
					REMOVE_BLIP	cs1_player_car_blip
					ADD_BLIP_FOR_CAR cs1_player_car cs1_player_car_blip
					SET_BLIP_AS_FRIENDLY cs1_player_car_blip TRUE
				 	cs1_in_car = 0

				ENDIF
//				IF cs1_stage = 0
//				AND cs1_cut = -2
//					REMOVE_BLIP cs1_contact_blip
//				ENDIF
			ENDIF
		ENDIF
		IF cs1_in_car = 0
			IF cs1_stage = 1
				PRINT_NOW ( CES1_06 ) 100 1 // Get back in the car.
			ENDIF
			IF IS_CHAR_IN_CAR scplayer cs1_player_car
				CLEAR_PRINTS
				IF cs1_stage = 1
					IF cs1_cesar_slow = 0
						IF NOT IS_CAR_DEAD cs1_cesar_car
							REMOVE_BLIP	cs1_cesar_car_blip
							ADD_BLIP_FOR_CAR cs1_cesar_car cs1_cesar_car_blip
							SET_BLIP_AS_FRIENDLY cs1_cesar_car_blip TRUE
						ENDIF
					ENDIF
					IF cs1_cesar_slow = 1
						REMOVE_BLIP cs1_cesar_blip
						ADD_BLIP_FOR_COORD cs1_player_start_x cs1_player_start_y cs1_player_start_z cs1_cesar_blip 
						PRINT_NOW ( CES1_05 ) 10000 1 // Drive onto the starting grid.
					ENDIF
					REMOVE_BLIP cs1_player_car_blip
					cs1_dialogue_playing = 0
					cs1_in_car = 1

				ENDIF
//				IF cs1_stage = 0
//				AND cs1_cut = -2
//					ADD_BLIP_FOR_COORD cesarX cesarY cesarZ cs1_contact_blip
//					PRINT_NOW ( CES1_02 ) 10000 1 // Go meet Cesar
//				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN

}


/*


[C1_A_A]
CESAR: Hey, keep up, holmes!

[C1_A_B]
CESAR: Not far now, eh!

[C1_A_C]
KENDL: C'mon, Carl, follow us!

[C1_A_D]
KENDL: Nearly there, Carl!

[C1_B_A]
CESAR: You gonna have to drive better than that, CJ!

[C1_B_B]
CESAR: Hey, CJ, you wrecking your car, eh!

[C1_B_C]
KENDL: Carl, your driving is an embarrassment!

[C1_B_D]
KENDL: Oh, Carl, I thought you said you could drive!

[C1_C_A]
CESAR: Here we go, yeeehaaa!

[C1_C_B]
CESAR: Goodluck, holmes!


[CES1_01 // 
~s~Go and pick up your ~b~car ~s~for the race.

[CES1_02 // 
~s~Get to ~y~Cesar's place~s~.

[CES1_03 // 
~s~Get back in the ~b~car~s~.

[CES1_04 // 
~s~Follow ~b~Cesar ~s~to the race start.

[CES1_05 // 
~s~Drive onto the ~y~starting grid~s~.

[CES1_06 // 
~s~Get back in ~b~the car~s~.

[CES1_07 // 
~r~Not fast enough, try again.

[CES1_08 // 
~r~You destroyed the car!

[CES1_09 // 
~r~You destroyed Cesar's car!
*/