MISSION_START
// ------------------------------------------------------------------------------------------------
// Badlands Cesar Mission 2: Big Smoke's Cash

{
cash_courier_loop:
SCRIPT_NAME bcesar2

// Begin...
// if this script has been triggered on the wrong day then quit out.
	IF weekday = 1
	OR weekday = 3
	OR weekday = 5
		GOTO mission_cleanup_bce2
	ENDIF

	// if the crack factory has been destroyed, this mission shouldn't be running
	IF flag_Synd_mission_counter >= 10 
		GOTO mission_cleanup_bce2
	ENDIF

	GOSUB check_player_is_safe_for_mobile
	IF player_is_completely_safe_for_mobile = 0
		GOTO mission_cleanup_bce2
	ENDIF

	SET_DEATHARREST_STATE OFF
	IF flag_bce2_passed_1stime = 0
		REGISTER_MISSION_GIVEN
	ENDIF
	bcesar2_mission_flag = 1
	flag_on_courier_mission = 1

WAIT 0

// ------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------
// Cash Courier
// ------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------
// Cash Courier Variables
// ---- Flags
	LVAR_INT bce2_current bce2_next bce2_blipped 
	LVAR_INT bce2_crate_check bce2_ram_text	bce2_task_status[3]	bce2_drive_status

// ---- Blips
	VAR_INT bce2_crate_blip[6]

// ---- Timers / Counters
	LVAR_INT bce2_final_dest bce2_route bce2_route_nx
	VAR_INT bce2_random	bce2_response
	LVAR_INT bce2_crate_start bce2_crate_end bce2_crate_diff
	LVAR_INT bce2_timer

// ---- Coords
	//LVAR_FLOAT bce2_route_x[117] bce2_route_z[117] bce2_route_y[117]
	VAR_FLOAT bce2_track_x bce2_track_y bce2_track_z
	LVAR_FLOAT bce2_player_x bce2_player_y bce2_player_z
	
// ---- Cameras

// ---- Objects
	VAR_INT bce2_crate[6] bce2_crate_status[6] 
	VAR_FLOAT bce2_random_rot
	VAR_FLOAT bce2_crate_x[6] bce2_crate_y[6] bce2_crate_z[6]

// ---- Groups
	VAR_TEXT_LABEL bce2_text[12]
	VAR_INT bce2_audio[12]
	VAR_INT bce2_text_timer_diff bce2_text_timer_end bce2_text_timer_start
	VAR_INT bce2_audio_playing bce2_counter bce2_mobile	bce2_ahead_counter bce2_audio_slot bce2_alt_slot
	

// ---- Entities
	LVAR_INT bce2_courier_car_health bce2_courier_car_health_hit
	LVAR_INT bce2_earnings bce2_player_car bce2_in_car bce2_player_status


// ------------------------------------------------------------------------------------------------
// -------- Vehicles
// ------------ Courier Car
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	bce2_current = 0
	bce2_next = 5

	bce2_earnings = 0
	bce2_crate_check = 0

	bce2_ram_text = 0

	bce2_in_car = 0

	bce2_audio_playing = 0
	bce2_counter = 0
	bce2_ahead_counter = 1
	bce2_mobile	= 0
	bce2_audio_slot = 1
	bce2_alt_slot = 2
	bce2_response = 0

   //	bce2_random = 2

	//bce2_played = 2

// ---- Sequence Flags

// ---- Objects
	bce2_crate_status[0] = 0
	bce2_crate_status[1] = 0
	bce2_crate_status[2] = 0
	bce2_crate_status[3] = 0
	bce2_crate_status[4] = 0
	bce2_crate_status[5] = 0
	bce2_crate_blip[0] = 0
	bce2_crate_blip[1] = 0
	bce2_crate_blip[2] = 0
	bce2_crate_blip[3] = 0
	bce2_crate_blip[4] = 0
	bce2_crate_blip[5] = 0
	
// ---- Counter Var
	bce2_route = 0
	bce2_route_nx = 1

// ---- Coords

// ---- Camera Coords

// ---- Entity
	bce2_courier_car_health = 1000
	bce2_cruise_speed = 40.0

//	bce2_courier_create:
		GENERATE_RANDOM_INT_IN_RANGE 0 2 bce2_random

		IF bce2_random = 0
			bce2_final_dest = 114
		ENDIF 
		IF bce2_random = 1
			bce2_final_dest = 96

		ENDIF
		IF bce2_random = 2
			bce2_final_dest = 115
		ENDIF

		IF bce2_random = 0 
			bce2_track_x = bce2_route1_x[0]
			bce2_track_y = bce2_route1_y[0]
			bce2_track_z = bce2_route1_z[0]
		ELSE
			IF bce2_random = 1 
				bce2_track_x = bce2_route2_x[0]
				bce2_track_y = bce2_route2_y[0]
				bce2_track_z = bce2_route2_z[0]
			ELSE
				bce2_track_x = bce2_route3_x[0]
				bce2_track_y = bce2_route3_y[0]
				bce2_track_z = bce2_route3_z[0]
			ENDIF
		ENDIF

		LVAR_INT bce2_courier bce2_courier_car bce2_courier_car_blip
		LVAR_INT bce2_goon[3]
		LVAR_FLOAT bce2_cruise_speed
		REQUEST_MODEL fam3
		REQUEST_MODEL PATRIOT
		REQUEST_MODEL micro_uzi
		REQUEST_MODEL CELLPHONE																
		WHILE NOT HAS_MODEL_LOADED fam3
		OR NOT HAS_MODEL_LOADED PATRIOT
		OR NOT HAS_MODEL_LOADED micro_uzi
		OR NOT HAS_MODEL_LOADED CELLPHONE

		WAIT 0
		ENDWHILE
		
		SET_CAR_MODEL_COMPONENTS PATRIOT 2 2
		CUSTOM_PLATE_FOR_NEXT_CAR PATRIOT &_ASSMAN_
		CREATE_CAR PATRIOT bce2_track_x bce2_track_y bce2_track_z bce2_courier_car
		SET_LOAD_COLLISION_FOR_CAR_FLAG bce2_courier_car FALSE
		CREATE_CHAR_INSIDE_CAR bce2_courier_car PEDTYPE_CIVMALE fam3 bce2_courier
		SET_CHAR_SUFFERS_CRITICAL_HITS bce2_courier FALSE
		SET_CHAR_PROOFS bce2_courier TRUE TRUE TRUE FALSE TRUE
		SET_CAR_PROOFS bce2_courier_car TRUE TRUE TRUE FALSE TRUE
		SET_CAR_CRUISE_SPEED bce2_courier_car 40.0
		ADD_STUCK_CAR_CHECK_WITH_WARP bce2_courier_car 2.0 2000 TRUE TRUE TRUE 2
		LOCK_CAR_DOORS bce2_courier_car CARLOCK_LOCKOUT_PLAYER_ONLY
		TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route1_x[bce2_route] bce2_route1_y[bce2_route] bce2_route1_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
		ADD_BLIP_FOR_CAR bce2_courier_car bce2_courier_car_blip
		SET_CAR_ONLY_DAMAGED_BY_PLAYER bce2_courier_car TRUE

		IF bce2_played > 0
			CREATE_CHAR_AS_PASSENGER bce2_courier_car PEDTYPE_CIVMALE fam3 0 bce2_goon[0]
			GIVE_WEAPON_TO_CHAR bce2_goon[0] WEAPONTYPE_MICRO_UZI 16000
			SET_CURRENT_CHAR_WEAPON bce2_goon[0] WEAPONTYPE_MICRO_UZI
			SET_CHAR_ACCURACY bce2_goon[0] 90
			IF bce2_played > 1
				CREATE_CHAR_AS_PASSENGER bce2_courier_car PEDTYPE_CIVMALE fam3 1 bce2_goon[1]
				GIVE_WEAPON_TO_CHAR bce2_goon[1] WEAPONTYPE_MICRO_UZI 16000
				SET_CURRENT_CHAR_WEAPON bce2_goon[1] WEAPONTYPE_MICRO_UZI
				SET_CHAR_ACCURACY bce2_goon[1] 90
				IF bce2_played > 2
					CREATE_CHAR_AS_PASSENGER bce2_courier_car PEDTYPE_CIVMALE fam3 2 bce2_goon[2]
					GIVE_WEAPON_TO_CHAR bce2_goon[2] WEAPONTYPE_MICRO_UZI 16000
					SET_CURRENT_CHAR_WEAPON bce2_goon[2] WEAPONTYPE_MICRO_UZI
					SET_CHAR_ACCURACY bce2_goon[2] 90
				ENDIF
			ENDIF
		ENDIF


		IF bce2_route = 0
			TURN_CAR_TO_FACE_COORD bce2_courier_car bce2_route1_x[bce2_route_nx] bce2_route1_y[bce2_route_nx]
		ELSE
			TURN_CAR_TO_FACE_COORD bce2_courier_car bce2_route1_x[bce2_route] bce2_route1_y[bce2_route]
		ENDIF

		
// ---- Crate offsets...

		bce2_crate_x[0] = -0.65 
		bce2_crate_y[0] = -2.307
		bce2_crate_z[0]	= 0.26

		bce2_crate_x[1] = -0.047
		bce2_crate_y[1] = -2.307
		bce2_crate_z[1]	= 0.26

		bce2_crate_x[2] = 0.554 
		bce2_crate_y[2] = -2.307
		bce2_crate_z[2]	= 0.26 
						   
		bce2_crate_x[3] = 0.554 
		bce2_crate_y[3] = -1.808
		bce2_crate_z[3]	= 0.26 

		bce2_crate_x[4] = -0.65 
		bce2_crate_y[4] = -1.808
		bce2_crate_z[4]	= 0.26

		bce2_crate_x[5] = -0.047
		bce2_crate_y[5] = -1.808
		bce2_crate_z[5]	= 0.26

		WHILE NOT bce2_current = 6
		WAIT 0
			IF NOT IS_CAR_DEAD bce2_courier_car
				IF bce2_crate_status[bce2_current] = 0
					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 19.9 bce2_random_rot
					IF bce2_random_rot < 20.0
					AND bce2_random_rot > 10.0
						bce2_random_rot += 340.0
					ENDIF
					CREATE_OBJECT CM_BOX bce2_crate_x[bce2_current] bce2_crate_y[bce2_current] bce2_crate_z[bce2_current] bce2_crate[bce2_current]
					ATTACH_OBJECT_TO_CAR bce2_crate[bce2_current] bce2_courier_car bce2_crate_x[bce2_current] bce2_crate_y[bce2_current] bce2_crate_z[bce2_current] 0.0 0.0 0.0
					ROTATE_OBJECT bce2_crate[bce2_current] bce2_random_rot bce2_random_rot TRUE
					SET_OBJECT_COLLISION bce2_crate[bce2_current] FALSE
					SET_OBJECT_DYNAMIC bce2_crate[bce2_current] FALSE
				ENDIF
				bce2_current ++
			ENDIF
		ENDWHILE
		GET_GAME_TIMER bce2_crate_start
		
// -------- Characters
// --------
// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_bce2:

	WAIT 0

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	ENDIF

// ------------------------------------------------------------------------------------------------
// Request Models 
// ------------------------------------------------------------------------------------------------
// Entity GoSubs
// ------------------------------------------------------------------------------------------------
// Task Sequences

$bce2_text[1] = MCES07A // Yo.
$bce2_text[2] = MCES07B // The cash is leaving Los Santos again.
$bce2_text[3] = MCES07C // Ok, I'm on it!
$bce2_text[4] = MCES06A // Yo, Cesar, whattup?
$bce2_text[5] = MCES06B // I got the low-down on Smoke's yay!
$bce2_text[6] = MCES06C // Word is, every Monday and Friday the cash leaves Los Santos for San Fierro.
$bce2_text[7] = MCES06D // Then every Wednesday and Saturday  a courier takes the yay back to Big Smoke.
$bce2_text[8] = MCES06E // Ok, I'll keep an eye out for them.
$bce2_text[9] = MCES06F // See if I can't spoil the party.
$bce2_text[10] = MCES06G // Later, dude.
$bce2_text[11] = MCES07D

bce2_audio[1] = SOUND_MCES07A // Yo.
bce2_audio[2] = SOUND_MCES07B // The cash is leaving Los Santos again.
bce2_audio[3] = SOUND_MCES07C // Ok, I'm on it!
bce2_audio[4] = SOUND_MCES06A // Yo, Cesar, whattup?
bce2_audio[5] = SOUND_MCES06B // I got the low-down on Smoke's yay!
bce2_audio[6] = SOUND_MCES06C // Word is, every Monday and Friday the cash leaves Los Santos for San Fierro.
bce2_audio[7] = SOUND_MCES06D // Then every Wednesday and Saturday  a courier takes the yay back to Big Smoke.
bce2_audio[8] = SOUND_MCES06E // Ok, I'll keep an eye out for them.
bce2_audio[9] = SOUND_MCES06F // See if I can't spoil the party.
bce2_audio[10] = SOUND_MCES06G // Later, dude.
bce2_audio[11] = SOUND_MCES07D




// ------------------------------------------------------------------------------------------------
// Initialise Route
 			 
//GOSUB bce2_courier_create
	REQUEST_MODEL CELLPHONE
	LOAD_ALL_MODELS_NOW
	LOAD_MISSION_AUDIO 3 SOUND_PED_MOBRING
	WHILE NOT HAS_MODEL_LOADED CELLPHONE
	OR NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
	ENDWHILE

	GOSUB check_player_is_safe_for_mobile
	IF player_is_completely_safe_for_mobile = 0
		GOTO mission_cleanup_bce2
	ENDIF
	TASK_USE_MOBILE_PHONE scplayer TRUE
	PLAY_MISSION_AUDIO 3
	WAIT 2000
	CLEAR_MISSION_AUDIO 3
bce2_mobile_loop:
WAIT 0

// ---- Load & Play Dialogue...
IF NOT bce2_counter = 0
	IF bce2_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED bce2_alt_slot
			CLEAR_MISSION_AUDIO bce2_alt_slot
		ENDIF
		bce2_audio_playing = 1
	ENDIF

	IF bce2_audio_playing = 1
		LOAD_MISSION_AUDIO bce2_audio_slot bce2_audio[bce2_counter]
		bce2_audio_playing = 2
	ENDIF

	IF bce2_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED bce2_audio_slot
			PLAY_MISSION_AUDIO bce2_audio_slot
			PRINT_NOW $bce2_text[bce2_counter] 10000 1
			bce2_audio_playing = 3
		ENDIF
	ENDIF

	IF bce2_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED bce2_audio_slot
			CLEAR_THIS_PRINT $bce2_text[bce2_counter]
			IF bce2_audio_slot = 1
				bce2_audio_slot = 2
				bce2_alt_slot = 1
			ELSE
				bce2_audio_slot = 1
				bce2_alt_slot = 2
			ENDIF
			bce2_counter = 0
			bce2_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED bce2_alt_slot
				IF bce2_counter < 10
					bce2_ahead_counter = bce2_counter + 1
					LOAD_MISSION_AUDIO bce2_alt_slot bce2_audio[bce2_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bce2_played > 0
	IF bce2_mobile = 0
		IF bce2_audio_playing = 0
			bce2_counter = 1	// Yo.
			bce2_mobile = 1
			GET_GAME_TIMER bce2_text_timer_start
		ENDIF
	ENDIF
	IF bce2_mobile = 1
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 2	// The cash is leaving Los Santos again.
				bce2_mobile = 2
				bce2_response = 0
				GET_GAME_TIMER bce2_text_timer_start
				CLEAR_HELP
				PRINT_HELP_FOREVER TALK_1
			ENDIF
		ENDIF
	ENDIF
	IF bce2_mobile = 2

		IF IS_BUTTON_PRESSED PAD1 DPADLEFT

			CLEAR_HELP
			bce2_response = 2
		ENDIF
		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT

			CLEAR_HELP
			bce2_response = 1
		ENDIF

	ENDIF
	IF bce2_response = 1 
		IF bce2_mobile = 2
			GET_GAME_TIMER bce2_text_timer_end
			bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
			IF bce2_text_timer_diff > 1000
				IF bce2_audio_playing = 0
					bce2_counter = 3 // Ok, I'm on it! 
					bce2_mobile = 7
					GET_GAME_TIMER bce2_text_timer_start
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF bce2_response = 2 
		IF bce2_mobile = 2
			GET_GAME_TIMER bce2_text_timer_end
			bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
			IF bce2_text_timer_diff > 1000
				IF bce2_audio_playing = 0
					bce2_counter = 11 // Ok, I'm on it! 
					bce2_mobile = 7
					GET_GAME_TIMER bce2_text_timer_start
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ELSE
	IF bce2_mobile = 0
		IF bce2_audio_playing = 0
			bce2_counter = 4	// Yo, Cesar, whattup?
			bce2_mobile = 1
			GET_GAME_TIMER bce2_text_timer_start
		ENDIF
	ENDIF

	IF bce2_mobile = 1
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 5	// I got the low-down on Smoke's yay!
				bce2_mobile = 2
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF bce2_mobile = 2
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 6 // Word is, every Monday and Friday the cash leaves Los Santos for San Fierro. 
				bce2_mobile = 3
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF bce2_mobile = 3
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 7	// Then every Wednesday and Saturday  a courier takes the yay back to Big Smoke.
				bce2_mobile = 4
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF bce2_mobile = 4
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 8 // Ok, I'll keep an eye out for them. 
				bce2_mobile = 5
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF bce2_mobile = 5
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 9	// See if I can't spoil the party.
				bce2_mobile = 6
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF bce2_mobile = 6
		GET_GAME_TIMER bce2_text_timer_end
		bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
		IF bce2_text_timer_diff > 1000
			IF bce2_audio_playing = 0
				bce2_counter = 10 // Later, dude. 
				bce2_mobile = 7
				GET_GAME_TIMER bce2_text_timer_start
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF bce2_mobile = 7
OR bce2_mobile = 8
	GET_GAME_TIMER bce2_text_timer_end
	bce2_text_timer_diff = bce2_text_timer_end - bce2_text_timer_start
	IF bce2_text_timer_diff > 1000
		IF bce2_audio_playing = 0
			IF NOT IS_CHAR_DEAD scplayer
				GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE bce2_player_status
				IF NOT bce2_player_status = FINISHED_TASK
					TASK_USE_MOBILE_PHONE scplayer FALSE
				ENDIF
				IF bce2_response < 2
					GOTO bce2_main_loop
				ELSE 
					GOTO mission_cleanup_bce2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	IF bce2_played > 0
		bce2_response = 2
	ENDIF
	bce2_mobile = 8
ENDIF

GOTO bce2_mobile_loop

// ------------------------------------------------------------------------------------------------
// Main Loop
bce2_main_loop:
WAIT 0

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC
	bce2_next = 0
	bce2_crate_check = 6
ENDIF

IF flag_player_on_mission = 1
	bce2_next = 0
	bce2_crate_check = 6
ENDIF

IF IS_PLAYER_PLAYING player1
	IF NOT IS_CHAR_DEAD scplayer
		IF bce2_in_car = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer bce2_player_car
				bce2_in_car = 1
			ENDIF
		ENDIF
		IF bce2_in_car = 1
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				bce2_in_car = 0
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF IS_PLAYER_PLAYING player1
	IF NOT IS_CHAR_DEAD scplayer
		IF bce2_ram_text = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer bce2_track_x bce2_track_y bce2_track_z 100.0 100.0 50.0 FALSE
				PRINT_NOW ( BCE2_02 ) 5000 1
				bce2_ram_text = 1
			ENDIF
		ENDIF

	// -------- Moving Car
		IF IS_CAR_DEAD bce2_courier_car
			GOTO mission_cleanup_bce2
		ENDIF
		IF bce2_played > 0
			IF NOT IS_CHAR_DEAD bce2_goon[0]
				GET_SCRIPT_TASK_STATUS bce2_goon[0] TASK_DRIVE_BY bce2_task_status[0]
				IF bce2_task_status[0] = FINISHED_TASK
					TASK_DRIVE_BY bce2_goon[0] scplayer -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN TRUE 30
					SET_CURRENT_CHAR_WEAPON bce2_goon[0] WEAPONTYPE_MICRO_UZI
				ENDIF
			ENDIF
			IF bce2_played > 1
				IF NOT IS_CHAR_DEAD bce2_goon[1]
					GET_SCRIPT_TASK_STATUS bce2_goon[1] TASK_DRIVE_BY bce2_task_status[1]
					IF bce2_task_status[1] = FINISHED_TASK
						TASK_DRIVE_BY bce2_goon[1] scplayer -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN TRUE 30
						SET_CURRENT_CHAR_WEAPON bce2_goon[1] WEAPONTYPE_MICRO_UZI
					ENDIF
				ENDIF
				IF bce2_played > 2
					IF NOT IS_CHAR_DEAD bce2_goon[2]
						GET_SCRIPT_TASK_STATUS bce2_goon[2] TASK_DRIVE_BY bce2_task_status[2]
						IF bce2_task_status[2] = FINISHED_TASK
							TASK_DRIVE_BY bce2_goon[2] scplayer -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN TRUE 30
							SET_CURRENT_CHAR_WEAPON bce2_goon[2] WEAPONTYPE_MICRO_UZI
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD bce2_courier
		AND NOT IS_CAR_DEAD	bce2_courier_car
			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer bce2_courier_car 15.0 15.0 15.0 FALSE
				IF bce2_cruise_speed > 30.0
					bce2_cruise_speed -= 0.1
				ENDIF
			ELSE
				IF bce2_cruise_speed < 50.0
					bce2_cruise_speed += 0.1
				ENDIF
			ENDIF
			IF bce2_ram_text = 1
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer bce2_courier_car 500.0 500.0 500.0 FALSE
					
					GOTO mission_cleanup_bce2	
				ENDIF	  
			ENDIF 	
			GET_CAR_COORDINATES bce2_courier_car bce2_track_x bce2_track_y bce2_track_z
			IF bce2_route < bce2_final_dest
				IF bce2_random = 0
					IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route1_x[bce2_route] bce2_route1_y[bce2_route] bce2_route1_z[bce2_route] 20.0 20.0 20.0 FALSE
						SET_CAR_CRUISE_SPEED bce2_courier_car bce2_cruise_speed
						bce2_route++
						TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route1_x[bce2_route] bce2_route1_y[bce2_route] bce2_route1_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					ENDIF
				ELSE
					IF bce2_random = 1
						IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route2_x[bce2_route] bce2_route2_y[bce2_route] bce2_route2_z[bce2_route] 20.0 20.0 20.0 FALSE
							SET_CAR_CRUISE_SPEED bce2_courier_car bce2_cruise_speed
							bce2_route++
							TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route2_x[bce2_route] bce2_route2_y[bce2_route] bce2_route2_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
					ELSE
						IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route3_x[bce2_route] bce2_route3_y[bce2_route] bce2_route3_z[bce2_route] 20.0 20.0 20.0 FALSE
							SET_CAR_CRUISE_SPEED bce2_courier_car bce2_cruise_speed
							bce2_route++
							TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route3_x[bce2_route] bce2_route3_y[bce2_route] bce2_route3_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF bce2_random = 0
					IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route1_x[bce2_route] bce2_route1_y[bce2_route] bce2_route1_z[bce2_route] 8.0 8.0 8.0 FALSE
						TASK_CAR_DRIVE_WANDER bce2_courier bce2_courier_car 50.0 DRIVINGMODE_AVOIDCARS
					ENDIF
				ELSE
					IF bce2_random = 1
						IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route2_x[bce2_route] bce2_route2_y[bce2_route] bce2_route2_z[bce2_route] 8.0 8.0 8.0 FALSE
							TASK_CAR_DRIVE_WANDER bce2_courier bce2_courier_car 50.0 DRIVINGMODE_AVOIDCARS
						ENDIF
					ELSE
						IF LOCATE_CHAR_IN_CAR_3D bce2_courier bce2_route3_x[bce2_route] bce2_route3_y[bce2_route] bce2_route3_z[bce2_route] 8.0 8.0 8.0 FALSE
							TASK_CAR_DRIVE_WANDER bce2_courier bce2_courier_car 50.0 DRIVINGMODE_AVOIDCARS
						ENDIF
					ENDIF
				ENDIF
				IF bce2_route = bce2_final_dest
					IF NOT IS_CAR_ON_SCREEN	bce2_courier_car
						
						GOTO mission_cleanup_bce2
					ENDIF
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS bce2_courier TASK_CAR_DRIVE_TO_COORD bce2_drive_status
			IF bce2_drive_status = FINISHED_TASK
				IF bce2_random = 0
					TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route1_x[bce2_route] bce2_route1_y[bce2_route] bce2_route1_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				ELSE
					IF bce2_random = 1
						TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route2_x[bce2_route] bce2_route2_y[bce2_route] bce2_route2_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					ELSE
						TASK_CAR_DRIVE_TO_COORD bce2_courier bce2_courier_car bce2_route3_x[bce2_route] bce2_route3_y[bce2_route] bce2_route3_z[bce2_route] bce2_cruise_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					ENDIF
				ENDIF
			ENDIF		
	   	ENDIF
	ENDIF
ELSE
	
	GOTO mission_cleanup_bce2
ENDIF

// ---- Throw Objects in Player's direction, and blip them...
IF NOT IS_CAR_DEAD bce2_courier_car
	GET_GAME_TIMER bce2_crate_end																   
	bce2_crate_diff = bce2_crate_end - bce2_crate_start
	GET_CAR_HEALTH bce2_courier_car bce2_courier_car_health_hit
	IF bce2_crate_diff > 1000
		IF bce2_courier_car_health_hit < bce2_courier_car_health
			IF bce2_crate_status[bce2_next] = 0
				IF IS_OBJECT_ATTACHED bce2_crate[bce2_next]
					IF NOT IS_CAR_DEAD bce2_courier_car
						IF NOT IS_CAR_UPSIDEDOWN bce2_courier_car
						OR NOT IS_CAR_IN_WATER bce2_courier_car
							GET_OBJECT_COORDINATES bce2_crate[bce2_next] bce2_crate_x[bce2_next] bce2_crate_y[bce2_next] bce2_crate_z[bce2_next]
							bce2_crate_z[bce2_next] += 0.5
							SET_OBJECT_COORDINATES bce2_crate[bce2_next] bce2_crate_x[bce2_next] bce2_crate_y[bce2_next] bce2_crate_z[bce2_next]
							DETACH_OBJECT bce2_crate[bce2_next] 90.0 180.0 1.5 TRUE
							ADD_BLIP_FOR_OBJECT bce2_crate[bce2_next] bce2_crate_blip[bce2_next]
							ADD_TO_OBJECT_VELOCITY bce2_crate[bce2_next] 0.0 -5.0 20.0
							SET_OBJECT_COLLISION bce2_crate[bce2_next] TRUE
							SET_OBJECT_DYNAMIC bce2_crate[bce2_next] TRUE
							IF IS_PLAYER_PLAYING player1
								IF IS_CHAR_IN_ANY_CAR scplayer
									IF NOT IS_CAR_DEAD bce2_player_car
										SORT_OUT_OBJECT_COLLISION_WITH_CAR bce2_crate[bce2_next] bce2_player_car
									ENDIF
								ENDIF
							ENDIF
							GET_GAME_TIMER bce2_crate_start
							PRINT_NOW ( BCE2_03 ) 5000 1
							bce2_crate_status[bce2_next] = 2
							IF bce2_next > 0
								bce2_next--
							ELSE
								IF bce2_crate_check = 6
									GOTO mission_cleanup_bce2 // If all of the Objects are thrown the mission is over...
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				ENDIF
			ENDIF
			bce2_courier_car_health = bce2_courier_car_health_hit
		ENDIF
	ENDIF
ENDIF

IF bce2_next = 0
	IF bce2_crate_check = 6
		GOTO mission_cleanup_bce2 // If all of the Objects are thrown the mission is over...
	ENDIF
ENDIF

// ---- Pick up the Objects, or don't. The choice, is yours...
bce2_blipped = 0
WHILE NOT bce2_blipped = 6
WAIT 0
	IF bce2_crate_status[bce2_blipped] = 2
		IF IS_PLAYER_PLAYING player1
			IF NOT IS_CHAR_DEAD scplayer 
				GET_CHAR_COORDINATES scplayer bce2_player_x bce2_player_y bce2_player_z
				IF DOES_OBJECT_EXIST bce2_crate[bce2_blipped]
					IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer bce2_crate[bce2_blipped] 3.0 3.0 3.0 FALSE
						REMOVE_BLIP bce2_crate_blip[bce2_blipped]
						DELETE_OBJECT bce2_crate[bce2_blipped]
						ADD_ONE_OFF_SOUND bce2_player_x bce2_player_y bce2_player_z SOUND_PART_MISSION_COMPLETE
						ADD_SCORE player1 300
						bce2_earnings += 300
					   	bce2_crate_status[bce2_blipped] = 3
						bce2_crate_check++
					ENDIF
				ENDIF
				IF DOES_OBJECT_EXIST bce2_crate[bce2_blipped]
					IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer bce2_crate[bce2_blipped] 75.0 75.0 75.0 FALSE
						REMOVE_BLIP bce2_crate_blip[bce2_blipped]
						DELETE_OBJECT bce2_crate[bce2_blipped]
						bce2_crate_status[bce2_blipped] = 3
						bce2_crate_check++	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	bce2_blipped++
ENDWHILE

GOTO bce2_main_loop 


// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_bce2:

	IF flag_on_courier_mission = 1
		PRINT_WITH_NUMBER_BIG BCE2_04 bce2_earnings 7500 5 
		PRINT_NOW ( BCE2_05 ) 7500 1
		bce2_played++
	ENDIF


// Cash Courier Cleanup
// ---- Entities
// ---- Blips
	REMOVE_BLIP bce2_courier_car_blip
	REMOVE_BLIP bce2_crate_blip[0]
	REMOVE_BLIP bce2_crate_blip[1]
	REMOVE_BLIP bce2_crate_blip[2]
	REMOVE_BLIP bce2_crate_blip[3]
	REMOVE_BLIP bce2_crate_blip[4]
	REMOVE_BLIP bce2_crate_blip[5]

// ---- Entities
	MARK_CAR_AS_NO_LONGER_NEEDED bce2_courier_car
	MARK_CHAR_AS_NO_LONGER_NEEDED bce2_courier
	MARK_CHAR_AS_NO_LONGER_NEEDED bce2_goon[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED bce2_goon[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED bce2_goon[2]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[0]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[1]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[2]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[3]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[4]
	MARK_OBJECT_AS_NO_LONGER_NEEDED bce2_crate[5]


// ---- Models
	MARK_MODEL_AS_NO_LONGER_NEEDED fam3				
	MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT			
	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	
	GET_GAME_TIMER timer_mobile_start		
	CLEAR_HELP
	CLEAR_MISSION_AUDIO 3

// ----	Clear Script Stuff
	//flag_player_on_mission = 0
  	GET_GAME_TIMER timer_mobile_start
  	//MISSION_HAS_FINISHED
	IF flag_on_courier_mission = 1
		IF flag_bce2_passed_1stime = 0
		    REGISTER_ODDJOB_MISSION_PASSED
		    flag_bce2_passed_1stime = 1
		ENDIF
	ENDIF
	flag_on_courier_mission = 0
TERMINATE_THIS_SCRIPT
}
MISSION_END

// ------------------------------------------------------------------------------------------------

