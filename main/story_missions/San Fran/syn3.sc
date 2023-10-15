MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** SYNDICATE 3 *************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME syn3

// Mission start stuff

GOSUB mission_start_syn3

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_syn3_failed
ENDIF

GOSUB mission_cleanup_syn3

MISSION_END

{
 
// Variables for mission

LVAR_INT s3_meet_car

LVAR_INT s3_player_bike s3_paste_van s3_paste_van_driver //s3_escorting_bike s3_escorting_bike_driver
LVAR_FLOAT s3_paste_van_x s3_paste_van_y s3_paste_van_z s3_paste_van_heading
//LVAR_FLOAT s3_escorting_bike_x s3_escorting_bike_y s3_escorting_bike_z

LVAR_FLOAT s3_van_location_x s3_van_location_y s3_van_location_z

LVAR_FLOAT s3_destination_x s3_destination_y s3_destination_z

LVAR_FLOAT s3_player_x s3_player_y s3_player_z
LVAR_FLOAT s3_roadblock_ped_x s3_roadblock_ped_y s3_roadblock_ped_z
LVAR_FLOAT s3_dist_from_van_to_ped s3_dist_from_player_to_ped

LVAR_FLOAT s3_van_speed

LVAR_INT s3_current_roadblock

LVAR_INT s3_first_roadblock_cars[3] s3_first_roadblock_peds[3]
LVAR_INT s3_second_roadblock_cars[3] s3_second_roadblock_peds[6] //s3_bulldozer
LVAR_INT s3_third_roadblock_cars[3] s3_third_roadblock_peds[6]
LVAR_INT s3_fourth_roadblock_cars[3] s3_fourth_roadblock_peds[3]

LVAR_INT s3_roadblock_ped_decisions

LVAR_INT s3_rocket s3_sniper
LVAR_INT s3_player_rocket_ammo s3_pickup_rocket_ammo
LVAR_INT s3_player_sniper_ammo s3_pickup_sniper_ammo

LVAR_INT s3_task_status s3_event_type

LVAR_INT s3_index

LVAR_INT s3_paste_van_route_seq

//LVAR_INT s3_help_text_stage

LVAR_INT s3_last_player_car_before_van_cutscene

LVAR_INT s3_toreno s3_tbone
LVAR_INT s3_in_car_decisions

LVAR_INT s3_get_in_audio_stage

// mission audio
LVAR_TEXT_LABEL s3_audio_text[8]
LVAR_INT s3_audio_sound[8]
LVAR_INT s3_audio_is_playing s3_audio_index s3_total_audio_to_play s3_started_talking
LVAR_INT s3_current_audio_needed
CONST_INT S3_MOBILE_AUDIO 0
CONST_INT S3_SECOND_CUTSCENE_AUDIO 1
CONST_INT S3_MISSION_PASSED_AUDIO 2

//LVAR_INT s3_escort_decisions

// flags

LVAR_INT s3_cutscene_skipped

LVAR_INT s3_player_reached_meet s3_player_picked_up_rocketlauncher
LVAR_INT s3_player_reached_van_location s3_player_in_meet_car
LVAR_INT s3_van_route_started s3_van_route_finished
LVAR_INT s3_player_bike_has_blip

LVAR_INT s3_use_rocket_brief_text_printed

LVAR_INT s3_first_roadblock_frozen s3_second_roadblock_frozen
LVAR_INT s3_third_roadblock_frozen s3_fourth_roadblock_frozen

LVAR_INT s3_first_roadblock_car_blip_flag[3] s3_first_roadblock_ped_blip_flag[3]
LVAR_INT s3_second_roadblock_car_blip_flag[3] s3_second_roadblock_ped_blip_flag[6]
LVAR_INT s3_third_roadblock_car_blip_flag[3] s3_third_roadblock_ped_blip_flag[6]
LVAR_INT s3_fourth_roadblock_car_blip_flag[3] s3_fourth_roadblock_ped_blip_flag[3]

LVAR_INT s3_first_roadblock_destroyed s3_second_roadblock_destroyed
LVAR_INT s3_third_roadblock_destroyed s3_fourth_roadblock_destroyed

// temp for view_integer_variable
LVAR_INT s3_first_roadblock_ped_attack_flags[3] s3_second_roadblock_ped_attack_flags[6]
LVAR_INT s3_third_roadblock_ped_attack_flags[6] s3_fourth_roadblock_ped_attack_flags[3]

// temp for view_integer_variable
LVAR_INT s3_roadblock_to_update s3_player_attacked_roadblock[4]

LVAR_INT s3_paste_van_heavy s3_paste_van_collisionproof

//LVAR_INT s3_escort_getting_back_on_bike

LVAR_INT s3_van_stopped_at_current_roadblock s3_van_almost_at_roadblock[4]

LVAR_INT s3_player_entered_any_car

LVAR_INT s3_fake_creates

// blips

LVAR_INT s3_meet_car_blip s3_van_location_blip s3_player_bike_blip
LVAR_INT s3_paste_van_blip /*s3_destination_blip*/ s3_temp_first_roadblock_blip
LVAR_INT s3_first_roadblock_car_blips[3] s3_first_roadblock_ped_blips[3]
LVAR_INT s3_second_roadblock_car_blips[3] s3_second_roadblock_ped_blips[6]
LVAR_INT s3_third_roadblock_car_blips[3] s3_third_roadblock_ped_blips[6]
LVAR_INT s3_fourth_roadblock_car_blips[3] s3_fourth_roadblock_ped_blips[3]
LVAR_INT s3_rocket_blip

// **************************************** Mission Start **********************************

mission_start_syn3:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT SYN3

flag_player_on_mission = 1

WAIT 0

// ************************************ Initialise variables  *****************************

s3_paste_van_x = -1832.33
s3_paste_van_y = -14.59
s3_paste_van_z =  15.39
s3_paste_van_heading = 270.0

s3_van_location_x = -1815.04
s3_van_location_y = 2.59
s3_van_location_z = 14.99

s3_destination_x = -2127.42
s3_destination_y = -100.65
s3_destination_z = 34.37

s3_van_speed = 9.5

s3_get_in_audio_stage = 0

s3_player_reached_meet = 0
s3_player_picked_up_rocketlauncher = 0
s3_player_reached_van_location = 0
s3_player_in_meet_car = 0
s3_van_route_started = 0
s3_van_route_finished = 0

s3_current_roadblock = 1

s3_first_roadblock_frozen = 0
s3_second_roadblock_frozen = 0
s3_third_roadblock_frozen = 0
s3_fourth_roadblock_frozen = 0

s3_index = 0
WHILE s3_index < 3
	s3_first_roadblock_car_blip_flag[s3_index] = 0
	s3_first_roadblock_ped_blip_flag[s3_index] = 0
	s3_first_roadblock_ped_attack_flags[s3_index] = 0
	s3_index++
ENDWHILE
s3_index = 0
WHILE s3_index < 6
	s3_second_roadblock_ped_blip_flag[s3_index] = 0
	s3_second_roadblock_ped_attack_flags[s3_index] = 0
	s3_index++
ENDWHILE
s3_index = 0
WHILE s3_index < 3
	s3_second_roadblock_car_blip_flag[s3_index] = 0
	s3_index++
ENDWHILE
s3_index = 0
WHILE s3_index < 6
	s3_third_roadblock_ped_blip_flag[s3_index] = 0
	s3_third_roadblock_ped_attack_flags[s3_index] = 0
	s3_index++
ENDWHILE
s3_index = 0
WHILE s3_index < 3
	s3_third_roadblock_car_blip_flag[s3_index] = 0
	s3_index++
ENDWHILE
s3_index = 0
WHILE s3_index < 3
	s3_fourth_roadblock_car_blip_flag[s3_index] = 0
	s3_fourth_roadblock_ped_blip_flag[s3_index] = 0
	s3_fourth_roadblock_ped_attack_flags[s3_index] = 0
	s3_index++
ENDWHILE

s3_first_roadblock_destroyed = 0
s3_second_roadblock_destroyed = 0
s3_third_roadblock_destroyed = 0
s3_fourth_roadblock_destroyed = 0

s3_roadblock_to_update = -1
s3_index = 0
WHILE s3_index < 4
	s3_player_attacked_roadblock[s3_index] = 0
	s3_index++
ENDWHILE

//s3_escort_getting_back_on_bike = 0

s3_van_stopped_at_current_roadblock = 0

s3_index = 0
WHILE s3_index < 4
	s3_van_almost_at_roadblock[s3_index] = 0
	s3_index++
ENDWHILE

s3_player_entered_any_car = 0

s3_audio_is_playing = 0
s3_audio_index = 0
s3_started_talking = 0

s3_fake_creates = 0

// ****************************************START OF CUTSCENE*******************************



// ****************************************END OF CUTSCENE*********************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_THIS_PRINT_BIG_NOW 1

// CREATE statements to keep the compiler happy

IF s3_fake_creates = 1
	CREATE_CAR SANCHEZ 0.0 0.0 -100.0 s3_player_bike
	ADD_BLIP_FOR_CAR s3_player_bike s3_player_bike_blip
	CREATE_CAR RUMPO 0.0 0.0 -100.0 s3_paste_van
	CREATE_CHAR_INSIDE_CAR s3_paste_van PEDTYPE_MISSION1 SFR1 s3_paste_van_driver
	ADD_BLIP_FOR_CAR s3_paste_van s3_paste_van_blip
//	CREATE_CAR SANCHEZ 0.0 0.0 -100.0 s3_escorting_bike
//	CREATE_CHAR_INSIDE_CAR s3_escorting_bike PEDTYPE_MISSION1 WBDYG2 s3_escorting_bike_driver
ENDIF

REQUEST_MODEL WASHING
REQUEST_MODEL ROCKETLA
REQUEST_MODEL SNIPER
REQUEST_MODEL SANCHEZ
REQUEST_MODEL cellphone
REQUEST_MODEL bodyarmour
WHILE NOT HAS_MODEL_LOADED WASHING
   OR NOT HAS_MODEL_LOADED ROCKETLA
   OR NOT HAS_MODEL_LOADED SNIPER
   OR NOT HAS_MODEL_LOADED SANCHEZ
   OR NOT HAS_MODEL_LOADED cellphone
   OR NOT HAS_MODEL_LOADED bodyarmour
	WAIT 0
ENDWHILE

LVAR_INT s3_wanted_level_at_start
GET_MAX_WANTED_LEVEL s3_wanted_level_at_start

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

CLEAR_AREA -2030.4 148.83 27.84 100.0 FALSE
LOAD_SCENE -2030.4 148.83 27.84

SET_CHAR_COORDINATES scplayer -2030.4 148.83 27.84
SET_CHAR_HEADING scplayer 270.0

SET_FIXED_CAMERA_POSITION -2027.45 150.01 29.01 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -2028.92 149.37 29.03 JUMP_CUT

s3_current_audio_needed = S3_MOBILE_AUDIO
GOSUB s3_setup_audio_data

TASK_USE_MOBILE_PHONE scplayer TRUE

DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

WAIT 1500

WHILE s3_audio_index < 2
	WAIT 0
	IF IS_PLAYER_PLAYING player1
		GOSUB s3_load_and_play_audio
		IF s3_audio_is_playing = 2
		AND s3_audio_index = 1
		AND NOT s3_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			s3_started_talking = 1
		ENDIF
	ELSE
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		s3_started_talking = 0
		s3_audio_index = s3_total_audio_to_play
	ENDIF
ENDWHILE
STOP_CHAR_FACIAL_TALK scplayer

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

WHILE s3_audio_index < s3_total_audio_to_play
	WAIT 0
	IF IS_PLAYER_PLAYING player1
	AND NOT IS_CHAR_IN_WATER scplayer
	AND NOT IS_CHAR_IN_AIR scplayer
	AND NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
		GOSUB s3_load_and_play_audio
		IF s3_audio_is_playing = 2
		AND s3_audio_index = 7
		AND NOT s3_started_talking = 1
			START_CHAR_FACIAL_TALK scplayer 10000
			s3_started_talking = 1
		ENDIF
	ELSE
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		s3_started_talking = 0
		IF IS_PLAYER_PLAYING player1
			STOP_CHAR_FACIAL_TALK scplayer
		ENDIF
		s3_audio_index = s3_total_audio_to_play
	ENDIF
ENDWHILE
STOP_CHAR_FACIAL_TALK scplayer

GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE s3_task_status
IF NOT s3_task_status = FINISHED_TASK
	TASK_USE_MOBILE_PHONE scplayer FALSE
ENDIF
MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
SET_EVERYONE_IGNORE_PLAYER player1 OFF

WAIT 2500

CUSTOM_PLATE_FOR_NEXT_CAR WASHING &_OMEGA__
CREATE_CAR WASHING -1665.27 412.923 6.192 s3_meet_car
SET_CAR_HEADING s3_meet_car 134.44
CHANGE_CAR_COLOUR s3_meet_car 84 84
ADD_BLIP_FOR_CAR s3_meet_car s3_meet_car_blip
SET_BLIP_AS_FRIENDLY s3_meet_car_blip TRUE
FREEZE_CAR_POSITION s3_meet_car TRUE

PRINT_NOW ( SYN3_18 ) 10000 0 // Go to the car.

//VIEW_INTEGER_VARIABLE s3_player_attacked_roadblock[3] attacked_roadblock
//VIEW_INTEGER_VARIABLE s3_fourth_roadblock_ped_attack_flags[0] s3_ped0_state
//VIEW_INTEGER_VARIABLE s3_fourth_roadblock_ped_attack_flags[1] s3_ped1_state
//VIEW_INTEGER_VARIABLE s3_fourth_roadblock_ped_attack_flags[2] s3_ped2_state

//VIEW_INTEGER_VARIABLE s3_paste_van_heavy s3_paste_van_heavy
//VIEW_INTEGER_VARIABLE s3_paste_van_collisionproof s3_paste_van_collisionproof

//VIEW_INTEGER_VARIABLE s3_van_stopped_at_current_roadblock s3_van_stopped_at_current_roadblock

// Mission loop

syn3_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_syn3_passed  
	ENDIF

//	// temp
//	IF IS_BUTTON_PRESSED PAD1 LEFTSHOCK
//		SET_CHAR_COORDINATES scplayer -1824.69 -17.67 14.80
//		SET_CHAR_HEADING scplayer 120.0
//	ENDIF
//	// temp
//	IF IS_BUTTON_PRESSED PAD1 LEFTSHOCK
//		GOSUB s3_play_van_cutscene
//	ENDIF

	IF NOT s3_player_reached_meet = 1
	AND NOT IS_CAR_DEAD s3_meet_car
		IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer s3_meet_car 10.0 10.0 10.0 FALSE

			REMOVE_BLIP s3_meet_car_blip
			s3_player_reached_meet = 1

			// play animated cutscene
			SET_PLAYER_CONTROL player1 OFF
			
			SET_FADING_COLOUR 0 0 0

			DO_FADE 2000 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer -1672.64 404.17 6.18
			ENDIF

			CLEAR_AREA -1665.27 412.923 6.192 50.0 FALSE

			IF NOT IS_CAR_DEAD s3_meet_car
				SET_CAR_VISIBLE s3_meet_car FALSE
			ENDIF

			MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1

			LOAD_CUTSCENE SYND_3A
			 
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

			// create Toreno and T-Bone in player's car
			LOAD_SPECIAL_CHARACTER 1 torino
			LOAD_SPECIAL_CHARACTER 2 tbone
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
			   OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
				WAIT 0
			ENDWHILE

			IF NOT IS_CAR_DEAD s3_meet_car

				SET_CAR_VISIBLE s3_meet_car TRUE
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -1665.27 412.923 -4.192
				ENDIF
				WARP_CHAR_INTO_CAR scplayer s3_meet_car
				s3_player_in_meet_car = 1
				FREEZE_CAR_POSITION s3_meet_car FALSE

				CREATE_CHAR_AS_PASSENGER s3_meet_car PEDTYPE_MISSION1 SPECIAL01 0 s3_toreno
				SET_CHAR_HEALTH s3_toreno 500
				SET_CHAR_MAX_HEALTH s3_toreno 500
				SET_CHAR_NEVER_TARGETTED s3_toreno TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS s3_toreno FALSE
				SET_CHAR_CANT_BE_DRAGGED_OUT s3_toreno TRUE
				SET_CHAR_STAY_IN_CAR_WHEN_JACKED s3_toreno TRUE
				SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR s3_toreno FALSE

				CREATE_CHAR_AS_PASSENGER s3_meet_car PEDTYPE_MISSION1 SPECIAL02 1 s3_tbone
				SET_CHAR_HEALTH s3_tbone 500
				SET_CHAR_MAX_HEALTH s3_tbone 500
				SET_CHAR_NEVER_TARGETTED s3_tbone TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS s3_tbone FALSE
				SET_CHAR_CANT_BE_DRAGGED_OUT s3_tbone TRUE
				SET_CHAR_STAY_IN_CAR_WHEN_JACKED s3_tbone TRUE
				SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR s3_tbone FALSE

				COPY_CHAR_DECISION_MAKER -1 s3_in_car_decisions
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s3_in_car_decisions EVENT_VEHICLE_ON_FIRE
				SET_CHAR_DECISION_MAKER s3_toreno s3_in_car_decisions
				SET_CHAR_DECISION_MAKER s3_tbone s3_in_car_decisions

				CLEAR_ALL_CHAR_RELATIONSHIPS s3_toreno ACQUAINTANCE_TYPE_PED_DISLIKE
				CLEAR_ALL_CHAR_RELATIONSHIPS s3_toreno ACQUAINTANCE_TYPE_PED_HATE
				CLEAR_ALL_CHAR_RELATIONSHIPS s3_tbone ACQUAINTANCE_TYPE_PED_DISLIKE
				CLEAR_ALL_CHAR_RELATIONSHIPS s3_tbone ACQUAINTANCE_TYPE_PED_HATE

			ENDIF

			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_ROCKETLAUNCHER s3_player_rocket_ammo
			IF s3_player_rocket_ammo = 0
				GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_ROCKETLAUNCHER_HS s3_player_rocket_ammo
			ENDIF
			s3_pickup_rocket_ammo = 20 - s3_player_rocket_ammo
			IF s3_pickup_rocket_ammo > 0
				CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE s3_pickup_rocket_ammo -1831.0 -21.43 15.16 s3_rocket
				ADD_BLIP_FOR_PICKUP s3_rocket s3_rocket_blip
			ELSE
				s3_player_picked_up_rocketlauncher = 1 // set so don't check if rocket's been picked up
				//s3_help_text_stage = 3 // set rocket help text to never come up since not needed
			ENDIF
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE s3_player_sniper_ammo
			s3_pickup_sniper_ammo = 40 - s3_player_sniper_ammo
			IF s3_pickup_sniper_ammo > 0
				CREATE_PICKUP_WITH_AMMO SNIPER PICKUP_ONCE s3_pickup_sniper_ammo -1837.0 -21.43 15.16 s3_sniper
			ENDIF
			MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
			MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER

			GOSUB s3_create_paste_van

			CREATE_CAR SANCHEZ -1830.16 -17.64 14.79 s3_player_bike
			SET_CAR_HEADING s3_player_bike 268.78

			WAIT 500

			DO_FADE 1500 FADE_IN

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON

			ADD_BLIP_FOR_COORD s3_van_location_x s3_van_location_y s3_van_location_z s3_van_location_blip
			
			PRINT_NOW ( SYN3_1 ) 10000 0 // Drive to the van's location.
			IF NOT s3_player_picked_up_rocketlauncher = 1
				//PRINT ( SYN3_21 ) 10000 0 // Pick up the rocket launcher when you get there.
			ENDIF

		ENDIF
	ENDIF

	IF s3_player_reached_meet = 1
	AND NOT s3_player_reached_van_location = 1
	AND NOT IS_CAR_DEAD s3_meet_car

		IF s3_player_in_meet_car = 1
		AND NOT IS_CHAR_IN_CAR scplayer s3_meet_car
			REMOVE_BLIP s3_van_location_blip
			ADD_BLIP_FOR_CAR s3_meet_car s3_meet_car_blip
			SET_BLIP_AS_FRIENDLY s3_meet_car_blip TRUE
			GOSUB s3_play_toreno_get_in_audio
			// check mission failed conditions
			IF IS_CHAR_DEAD s3_toreno
				CLEAR_MISSION_AUDIO 2
				PRINT_NOW ( SYN3_27 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CHAR_DEAD s3_tbone
				PRINT_NOW ( SYN3_28 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CAR_DEAD s3_meet_car
				PRINT_NOW ( SYN3_19 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CAR_DEAD s3_paste_van
				PRINT_NOW ( SYN3_15 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CHAR_DEAD s3_paste_van_driver
				PRINT_NOW ( SYN3_16 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CAR_DEAD s3_player_bike
				PRINT_NOW ( SYN3_14 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			PRINT_NOW ( SYN3_26 ) 7000 0
			s3_player_in_meet_car = 0
		ENDIF
		IF s3_player_in_meet_car = 0
		AND IS_CHAR_IN_CAR scplayer s3_meet_car
			REMOVE_BLIP s3_meet_car_blip
			ADD_BLIP_FOR_COORD s3_van_location_x s3_van_location_y s3_van_location_z s3_van_location_blip
			PRINT_NOW ( SYN3_1 ) 7000 0
			s3_player_in_meet_car = 1
		ENDIF

		IF s3_player_in_meet_car = 1
			IF LOCATE_CHAR_IN_CAR_3D scplayer s3_van_location_x s3_van_location_y s3_van_location_z 4.0 4.0 2.0 TRUE

				REMOVE_BLIP s3_van_location_blip
				s3_player_reached_van_location = 1
				
				SET_PLAYER_CONTROL player1 OFF

				GOSUB s3_play_van_location_cutscene

//				// if player was watching the help text when they triggered the cutscene,
//				// set the help text to finished so it doesn't appear afterwards
//				IF s3_player_picked_up_rocketlauncher = 1
//					s3_help_text_stage = 3
//				ENDIF

				IF NOT IS_CAR_DEAD s3_player_bike
					ADD_BLIP_FOR_CAR s3_player_bike s3_player_bike_blip
					SET_BLIP_AS_FRIENDLY s3_player_bike_blip TRUE
				ENDIF
				PRINT_NOW ( SYN3_25 ) 10000 0 // Pick up the escort bike.
			ENDIF
		ENDIF
	ENDIF

	// start van on its route when player picks up escort bike
	IF s3_player_reached_van_location = 1
	AND NOT s3_van_route_started = 1
 		IF NOT IS_CAR_DEAD s3_player_bike
			IF IS_CHAR_IN_CAR scplayer s3_player_bike

				REMOVE_BLIP s3_player_bike_blip
				
				GOSUB s3_start_van_route
				s3_van_route_started = 1

				//ADD_BLIP_FOR_COORD s3_destination_x s3_destination_y s3_destination_z s3_destination_blip
				//CHANGE_BLIP_DISPLAY s3_destination_blip BLIP_ONLY
				//DIM_BLIP s3_destination_blip ON
				//CHANGE_BLIP_SCALE s3_destination_blip 2

//				IF NOT IS_CHAR_DEAD s3_escorting_bike_driver
//				AND NOT IS_CAR_DEAD s3_escorting_bike
//				AND NOT IS_CAR_DEAD s3_paste_van
//					TASK_CAR_MISSION s3_escorting_bike_driver s3_escorting_bike s3_paste_van MISSION_ESCORT_REAR 0.0 DRIVINGMODE_AVOIDCARS
//				ENDIF

				IF NOT IS_CAR_DEAD s3_paste_van
					ADD_BLIP_FOR_CAR s3_paste_van s3_paste_van_blip
					SET_BLIP_AS_FRIENDLY s3_paste_van_blip TRUE
					CHANGE_BLIP_DISPLAY s3_paste_van_blip BLIP_ONLY
					//DIM_BLIP s3_paste_van_blip ON
					//CHANGE_BLIP_SCALE s3_paste_van_blip 2
				ENDIF

				ADD_BLIP_FOR_COORD -2007.75 533.24 33.91 s3_temp_first_roadblock_blip
				SET_COORD_BLIP_APPEARANCE s3_temp_first_roadblock_blip COORD_BLIP_APPEARANCE_ENEMY
				PRINT_NOW ( SYN3_2 ) 10000 0
				s3_use_rocket_brief_text_printed = 0
				TIMERB = 0

				GOSUB s3_create_first_roadblock
				GOSUB s3_freeze_first_roadblock
				s3_first_roadblock_frozen = 1

				// stop traffic jams at roadblocks
				SWITCH_ROADS_OFF -2005.23 517.16 33.0 -2002.69 554.12 36.0
				SWITCH_ROADS_OFF -2097.80 319.42 33.0 -2035.57 321.05 36.0
				SWITCH_ROADS_OFF -2150.18 138.75 33.0 -2145.71 160.84 36.0
				SWITCH_ROADS_OFF -2168.24 42.13  33.0 -2165.99 61.70 36.0
				SWITCH_RANDOM_TRAINS OFF

				// create armour pickup
				LVAR_INT s3_armour_pickup
				CREATE_PICKUP bodyarmour PICKUP_ONCE -2060.03 304.18 35.81 s3_armour_pickup

				s3_player_bike_has_blip = 0
				GOSUB s3_add_blip_for_player_bike

			ENDIF
		ENDIF
	ENDIF

	IF NOT s3_van_route_finished = 1

		IF s3_player_reached_meet = 1
			IF NOT s3_player_picked_up_rocketlauncher = 1
				IF HAS_PICKUP_BEEN_COLLECTED s3_rocket
					REMOVE_BLIP s3_rocket_blip
					s3_player_picked_up_rocketlauncher = 1
				ENDIF
			ENDIF
		ENDIF

		IF s3_van_route_started = 1
		AND NOT IS_CAR_DEAD s3_paste_van

			IF NOT s3_use_rocket_brief_text_printed = 1
			AND TIMERB >= 10000
				IF DOES_BLIP_EXIST s3_rocket_blip
					PRINT_NOW ( SYN3_3 ) 7000 0
				ELSE
					PRINT_NOW ( SYN3_4 ) 7000 0
				ENDIF
				s3_use_rocket_brief_text_printed = 1
			ENDIF				

			IF s3_current_roadblock >= 1
			OR s3_current_roadblock = -1
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1999.7 527.27 150.0 150.0 FALSE
				OR LOCATE_CAR_2D s3_paste_van -1999.7 527.27 100.0 100.0 FALSE
					IF s3_first_roadblock_frozen = 1
						GOSUB s3_unfreeze_first_roadblock
						s3_first_roadblock_frozen = 0
					ENDIF
				ELSE
					IF s3_first_roadblock_frozen = 0
						GOSUB s3_freeze_first_roadblock
						s3_first_roadblock_frozen = 1
					ENDIF
				ENDIF
				// if van within 35m (weapon range), peds attack van
				// if player within 35m but van isn't within 35m, peds attack player
				// if neither are within 35m, peds stop attacking
				s3_roadblock_to_update = 0
				GOSUB s3_update_roadblock_ped_attacks
			ENDIF
			IF s3_current_roadblock >= 2
			OR s3_current_roadblock = -1
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2085.74 320.74 150.0 150.0 FALSE
				OR LOCATE_CAR_2D s3_paste_van -2085.74 320.74 100.0 100.0 FALSE
					IF s3_second_roadblock_frozen = 1
						GOSUB s3_unfreeze_second_roadblock
						s3_second_roadblock_frozen = 0
					ENDIF
				ELSE
					IF s3_second_roadblock_frozen = 0
						GOSUB s3_freeze_second_roadblock
						s3_second_roadblock_frozen = 1
					ENDIF
				ENDIF
				s3_roadblock_to_update = 1
				GOSUB s3_update_roadblock_ped_attacks
			ENDIF
			IF s3_current_roadblock >= 3
			OR s3_current_roadblock = -1
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2146.62 146.06 150.0 150.0 FALSE
				OR LOCATE_CAR_2D s3_paste_van -2146.62 146.06 100.0 100.0 FALSE
					IF s3_third_roadblock_frozen = 1
						GOSUB s3_unfreeze_third_roadblock
						s3_third_roadblock_frozen = 0
					ENDIF
				ELSE
					IF s3_third_roadblock_frozen = 0
						GOSUB s3_freeze_third_roadblock
						s3_third_roadblock_frozen = 1
					ENDIF
				ENDIF
				s3_roadblock_to_update = 2
				GOSUB s3_update_roadblock_ped_attacks
			ENDIF
			IF s3_current_roadblock >= 4
			OR s3_current_roadblock = -1
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2166.92 48.75 150.0 150.0 FALSE
				OR LOCATE_CAR_2D s3_paste_van -2166.92 48.75 100.0 100.0 FALSE
					IF s3_fourth_roadblock_frozen = 1
						GOSUB s3_unfreeze_fourth_roadblock
						s3_fourth_roadblock_frozen = 0
					ENDIF
				ELSE
					IF s3_fourth_roadblock_frozen = 0
						GOSUB s3_freeze_fourth_roadblock
						s3_fourth_roadblock_frozen = 1
					ENDIF
				ENDIF
				s3_roadblock_to_update = 3
				GOSUB s3_update_roadblock_ped_attacks
			ENDIF

			// if roadblock char/car dead, remove blip
			GOSUB s3_remove_blip_check

			// needs to be before current roadblock update in loop;
			// check for current roadblock destroyed in update_vehicle_weights doesn't work otherwise
			// (as soon as the current roadblock is destroyed, it's not the current roadblock any more.)
			GOSUB s3_update_vehicle_weights

			// van stops at each roadblock if the roadblock hasn't been destroyed
			// and only continues its route when the player's destroyed the roadblock
			IF NOT s3_van_stopped_at_current_roadblock = 1
				SWITCH s3_current_roadblock
					CASE 1
						IF LOCATE_CAR_2D s3_paste_van -2007.75 533.24 10.0 10.0 FALSE
							s3_van_stopped_at_current_roadblock = 1
						ENDIF
						BREAK
					CASE 2
						IF LOCATE_CAR_2D s3_paste_van -2079.24 323.74 15.0 15.0 FALSE
							s3_van_stopped_at_current_roadblock = 1
						ENDIF
						BREAK
					CASE 3
						IF LOCATE_CAR_2D s3_paste_van -2149.62 152.06 10.0 10.0 FALSE
							s3_van_stopped_at_current_roadblock = 1
						ENDIF
						BREAK
					CASE 4
						IF LOCATE_CAR_2D s3_paste_van -2169.42 54.75 10.0 10.0 FALSE
							s3_van_stopped_at_current_roadblock = 1
						ENDIF
						BREAK
					DEFAULT
						BREAK
				ENDSWITCH
			ENDIF
			IF s3_van_stopped_at_current_roadblock = 1
				SET_CAR_CRUISE_SPEED s3_paste_van 0.0
			ENDIF

			// warn player when van's getting too close to the current roadblock
			SWITCH s3_current_roadblock
				CASE 1
					IF NOT s3_van_almost_at_roadblock[0] = 1
					AND LOCATE_CAR_2D s3_paste_van -2007.75 533.24 60.0 60.0 FALSE
						PRINT_NOW ( SYN3_9 ) 10000 0
						s3_van_almost_at_roadblock[0] = 1
					ENDIF
					BREAK
				CASE 2
					IF NOT s3_van_almost_at_roadblock[1] = 1
					AND LOCATE_CAR_2D s3_paste_van -2079.24 323.74 60.0 60.0 FALSE
						PRINT_NOW ( SYN3_9 ) 10000 0
						s3_van_almost_at_roadblock[1] = 1
					ENDIF
					BREAK
				CASE 3
					IF NOT s3_van_almost_at_roadblock[2] = 1
					AND LOCATE_CAR_2D s3_paste_van -2149.62 152.06 60.0 60.0 FALSE
						PRINT_NOW ( SYN3_9 ) 10000 0
						s3_van_almost_at_roadblock[2] = 1
					ENDIF
					BREAK
				CASE 4
					IF NOT s3_van_almost_at_roadblock[3] = 1
					AND LOCATE_CAR_2D s3_paste_van -2169.42 54.75 60.0 60.0 FALSE
						PRINT_NOW ( SYN3_9 ) 10000 0
						s3_van_almost_at_roadblock[3] = 1
					ENDIF
					BREAK
				DEFAULT
					BREAK
			ENDSWITCH

			IF s3_current_roadblock = 1

				// if player's destroyed the roadblock or van's managed to get past it by a safe distance,
				// remove blips for old roadblock and set up next roadblock
				GOSUB s3_first_roadblock_destroyed_check
				IF s3_first_roadblock_destroyed = 1
				OR IS_CAR_IN_AREA_2D s3_paste_van -2016.30 434.74 -1960.98 454.11 FALSE
					GOSUB s3_remove_first_roadblock_blips
					GOSUB s3_create_second_roadblock
					GOSUB s3_freeze_second_roadblock
					s3_second_roadblock_frozen = 1
					GOSUB s3_add_second_roadblock_blips
					s3_current_roadblock = 2
					IF s3_van_stopped_at_current_roadblock = 1
						SET_CAR_CRUISE_SPEED s3_paste_van s3_van_speed
						s3_van_stopped_at_current_roadblock = 0
					ENDIF
					PRINT_NOW ( SYN3_5 ) 10000 0
					GOSUB s3_add_blip_for_player_bike
				ENDIF
			
			ENDIF

			IF s3_current_roadblock = 2
			AND NOT IS_CAR_DEAD s3_paste_van

				GOSUB s3_second_roadblock_destroyed_check
				IF s3_second_roadblock_destroyed = 1
				OR IS_CAR_IN_AREA_2D s3_paste_van -2133.87 308.44 -2124.45 330.0 FALSE
					GOSUB s3_remove_second_roadblock_blips
					GOSUB s3_create_third_roadblock
					GOSUB s3_freeze_third_roadblock
					s3_third_roadblock_frozen = 1
					GOSUB s3_add_third_roadblock_blips
					s3_current_roadblock = 3
					IF s3_van_stopped_at_current_roadblock = 1
						SET_CAR_CRUISE_SPEED s3_paste_van s3_van_speed
						s3_van_stopped_at_current_roadblock = 0
					ENDIF
					PRINT_NOW ( SYN3_5 ) 10000 0
					GOSUB s3_add_blip_for_player_bike
				ENDIF
			
			ENDIF

			IF s3_current_roadblock = 3
			AND NOT IS_CAR_DEAD s3_paste_van

				GOSUB s3_third_roadblock_destroyed_check
				IF s3_third_roadblock_destroyed = 1
				OR IS_CAR_IN_AREA_2D s3_paste_van -2171.21 90.96 -2151.63 92.36 FALSE
					GOSUB s3_remove_third_roadblock_blips
					GOSUB s3_create_fourth_roadblock
					GOSUB s3_freeze_fourth_roadblock
					s3_fourth_roadblock_frozen = 1
					GOSUB s3_add_fourth_roadblock_blips
					s3_current_roadblock = 4
					IF s3_van_stopped_at_current_roadblock = 1
						SET_CAR_CRUISE_SPEED s3_paste_van s3_van_speed
						s3_van_stopped_at_current_roadblock = 0
					ENDIF
					PRINT_NOW ( SYN3_7 ) 10000 0
					GOSUB s3_add_blip_for_player_bike
				ENDIF
			
			ENDIF

			IF s3_current_roadblock = 4
			AND NOT IS_CAR_DEAD s3_paste_van

				GOSUB s3_fourth_roadblock_destroyed_check
				IF s3_fourth_roadblock_destroyed = 1
				OR IS_CAR_IN_AREA_2D s3_paste_van -2177.54 7.10 -2157.29 19.76 FALSE
					GOSUB s3_remove_fourth_roadblock_blips
					s3_current_roadblock = -1
					IF s3_van_stopped_at_current_roadblock = 1
						SET_CAR_CRUISE_SPEED s3_paste_van s3_van_speed
						s3_van_stopped_at_current_roadblock = 0
					ENDIF
					PRINT_NOW ( SYN3_8 ) 10000 0
					GOSUB s3_add_blip_for_player_bike
				ENDIF
			
			ENDIF

			IF s3_player_bike_has_blip = 1
				IF NOT IS_CAR_DEAD s3_player_bike
					IF IS_CHAR_IN_CAR scplayer s3_player_bike
						REMOVE_BLIP s3_player_bike_blip
						s3_player_bike_has_blip = 0
					ENDIF
				ELSE
					REMOVE_BLIP s3_player_bike_blip
					s3_player_bike_has_blip = 0
				ENDIF
			ENDIF

			// get handle for last car player is in before cutscene at factory
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN scplayer s3_last_player_car_before_van_cutscene
				IF NOT s3_player_entered_any_car = 1
					s3_player_entered_any_car = 1
				ENDIF
			ENDIF

		ENDIF

//		// keep escorting bike driver on bike
//		IF NOT IS_CHAR_DEAD s3_escorting_bike_driver
//		AND NOT IS_CAR_DEAD s3_escorting_bike
//		AND NOT IS_CAR_DEAD s3_paste_van
//			IF NOT IS_CHAR_IN_CAR s3_escorting_bike_driver s3_escorting_bike
//
//				IF NOT s3_escort_getting_back_on_bike = 1
//
//					IF NOT s3_van_route_started = 1
//						TASK_ENTER_CAR_AS_DRIVER s3_escorting_bike_driver s3_escorting_bike -1
//					ELSE
//						TASK_CAR_MISSION s3_escorting_bike_driver s3_escorting_bike s3_paste_van MISSION_ESCORT_REAR 0.0 DRIVINGMODE_AVOIDCARS
//					ENDIF
//					s3_escort_getting_back_on_bike = 1
//
//				ENDIF
//
//			ELSE
//
//				IF s3_escort_getting_back_on_bike = 1
//					s3_escort_getting_back_on_bike = 0
//				ENDIF
//
//			ENDIF
//		ENDIF

		IF NOT s3_player_reached_van_location = 1
			IF s3_player_reached_meet = 1
				IF IS_CHAR_DEAD s3_toreno
					PRINT_NOW ( SYN3_27 ) 4000 0
					GOTO mission_syn3_failed
				ENDIF
				IF IS_CHAR_DEAD s3_tbone
					PRINT_NOW ( SYN3_28 ) 4000 0
					GOTO mission_syn3_failed
				ENDIF
			ENDIF
			IF IS_CAR_DEAD s3_meet_car
				PRINT_NOW ( SYN3_19 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
		ENDIF
		IF s3_player_reached_meet = 1
			IF IS_CAR_DEAD s3_paste_van
				PRINT_NOW ( SYN3_15 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF IS_CHAR_DEAD s3_paste_van_driver
				PRINT_NOW ( SYN3_16 ) 4000 0
				GOTO mission_syn3_failed
			ENDIF
			IF NOT s3_van_route_started = 1
				IF IS_CAR_DEAD s3_player_bike
					PRINT_NOW ( SYN3_14 ) 4000 0
					GOTO mission_syn3_failed
				ENDIF
			ENDIF
		ENDIF

		// van reached destination successfully
		IF s3_van_route_started = 1
		AND NOT IS_CAR_DEAD s3_paste_van
		AND NOT IS_CHAR_DEAD s3_paste_van_driver

			GET_SCRIPT_TASK_STATUS s3_paste_van_driver PERFORM_SEQUENCE_TASK s3_task_status
			IF s3_task_status = FINISHED_TASK
				IF LOCATE_CAR_3D s3_paste_van s3_destination_x s3_destination_y s3_destination_z 35.0 35.0 5.0 FALSE
				AND LOCATE_CHAR_ANY_MEANS_2D scplayer s3_destination_x s3_destination_y 20.0 20.0 FALSE
					s3_van_route_finished = 1
					GOSUB s3_play_van_cutscene
					GOTO mission_syn3_passed
				ENDIF
			ELSE
				IF LOCATE_CAR_3D s3_paste_van s3_destination_x s3_destination_y s3_destination_z 20.0 20.0 5.0 FALSE
				AND LOCATE_CHAR_ANY_MEANS_2D scplayer s3_destination_x s3_destination_y 20.0 20.0 FALSE
					TASK_CAR_DRIVE_TO_COORD s3_paste_van_driver s3_paste_van s3_destination_x s3_destination_y s3_destination_z s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					s3_van_route_finished = 1
					GOSUB s3_play_van_cutscene
					GOTO mission_syn3_passed
				ENDIF
			ENDIF
		ENDIF

	ENDIF

//	// temp
//	IF IS_BUTTON_PRESSED PAD1 LEFTSHOCK
//		s3_van_route_finished = 1
//		GOSUB s3_play_van_cutscene
//	ENDIF

GOTO syn3_loop


// ********************************** Mission GOSUBS ************************************

// ************************************************************
// 				  Create van and escorting bike
// ************************************************************

	s3_create_paste_van:

		REQUEST_MODEL RUMPO
		REQUEST_MODEL SFR1
		WHILE NOT HAS_MODEL_LOADED RUMPO
		   OR NOT HAS_MODEL_LOADED SFR1
			WAIT 0
		ENDWHILE
	
		CREATE_CAR RUMPO s3_paste_van_x s3_paste_van_y s3_paste_van_z s3_paste_van
		CREATE_CHAR_INSIDE_CAR s3_paste_van PEDTYPE_MISSION1 SFR1 s3_paste_van_driver
		SET_CAR_HEADING s3_paste_van s3_paste_van_heading
		SET_CAR_HEAVY s3_paste_van TRUE
		s3_paste_van_heavy = 1
		LOCK_CAR_DOORS s3_paste_van CARLOCK_LOCKED
		SET_CAR_PROOFS s3_paste_van FALSE TRUE FALSE TRUE FALSE
		s3_paste_van_collisionproof = 1
		SET_CAN_BURST_CAR_TYRES s3_paste_van FALSE
		VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE s3_paste_van FALSE

		MARK_MODEL_AS_NO_LONGER_NEEDED RUMPO
		MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
		MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

	RETURN

// ************************************************************
// 			   Start van on route through roadblocks
// ************************************************************

	s3_start_van_route:

		IF NOT IS_CAR_DEAD s3_paste_van
		AND NOT IS_CHAR_DEAD s3_paste_van_driver

			OPEN_SEQUENCE_TASK s3_paste_van_route_seq

				FLUSH_ROUTE
//				// on to first road
//				EXTEND_ROUTE -1800.68 7.40 14.65
//				// under flyover
//				EXTEND_ROUTE -1778.46 341.29 6.59
//				// on to first road
//				EXTEND_ROUTE -1800.37 5.96 14.63
				// on to first road
				EXTEND_ROUTE -1814.77 -0.45 15.38

				TASK_DRIVE_POINT_ROUTE_ADVANCED -1 s3_paste_van s3_van_speed MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

				FLUSH_ROUTE
				// before flyover
				EXTEND_ROUTE -1746.57 306.18 6.08
				// after flyover
				EXTEND_ROUTE -1858.69 418.9 16.06

				// top of sloping road
				EXTEND_ROUTE -1899.05 605.47 34.06
				// before first roadblock
				EXTEND_ROUTE -2004.31 605.61 34.06
//				// corner of building site
//				EXTEND_ROUTE -2007.09 108.48 27.57
//				// far corner of building site
//				EXTEND_ROUTE -2156.05 110.54 34.19
				// corner of building site
				EXTEND_ROUTE -2004.29 320.22 34.04
				// far corner of building site
				EXTEND_ROUTE -2146.68 320.06 34.20
				// crossroads
				EXTEND_ROUTE -2167.13 -70.20 34.21

				TASK_DRIVE_POINT_ROUTE_ADVANCED -1 s3_paste_van s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

				FLUSH_ROUTE

//				EXTEND_ROUTE -2167.10 30.61 35.44
//				EXTEND_ROUTE -2085.81 28.04 35.41
//				EXTEND_ROUTE -2085.81 68.96 35.41

//				TASK_CAR_DRIVE_TO_COORD -1 s3_paste_van -2167.10 30.61 35.44 s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//				TASK_CAR_DRIVE_TO_COORD -1 s3_paste_van -2085.81 28.04 35.41 s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//				TASK_CAR_DRIVE_TO_COORD -1 s3_paste_van -2085.81 68.96 35.41 s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

				// road nearest to destination
				EXTEND_ROUTE s3_destination_x -70.33 34.22
				// destination
				EXTEND_ROUTE s3_destination_x s3_destination_y s3_destination_z

				TASK_DRIVE_POINT_ROUTE_ADVANCED -1 s3_paste_van s3_van_speed MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

				FLUSH_ROUTE
			
			CLOSE_SEQUENCE_TASK s3_paste_van_route_seq
			PERFORM_SEQUENCE_TASK s3_paste_van_driver s3_paste_van_route_seq
			CLEAR_SEQUENCE_TASK s3_paste_van_route_seq

		ENDIF

	RETURN

// ************************************************************
// 				  		Create roadblocks
// ************************************************************

	s3_create_first_roadblock:

		REQUEST_MODEL GREENWOO
		REQUEST_MODEL DNB3
		REQUEST_MODEL DNB2
		REQUEST_MODEL MP5LNG
		WHILE NOT HAS_MODEL_LOADED GREENWOO
		   OR NOT HAS_MODEL_LOADED DNB3
		   OR NOT HAS_MODEL_LOADED DNB2
		   OR NOT HAS_MODEL_LOADED MP5LNG
			WAIT 0
		ENDWHILE

		REMOVE_BLIP s3_temp_first_roadblock_blip
		// first roadblock
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2000.75 537.24 33.91 s3_first_roadblock_peds[0]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2007.75 543.24 33.91 s3_first_roadblock_peds[1]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2012.75 537.24 33.91 s3_first_roadblock_peds[2]
		// set up decision maker for all roadblock peds
		COPY_CHAR_DECISION_MAKER -1 s3_roadblock_ped_decisions
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE s3_roadblock_ped_decisions EVENT_GUN_AIMED_AT
		s3_index = 0
		WHILE s3_index < 3
			SET_CHAR_DECISION_MAKER s3_first_roadblock_peds[s3_index] s3_roadblock_ped_decisions
			SET_CHAR_STAY_IN_SAME_PLACE s3_first_roadblock_peds[s3_index] TRUE
			GIVE_WEAPON_TO_CHAR s3_first_roadblock_peds[s3_index] WEAPONTYPE_MP5 99999
			ADD_BLIP_FOR_CHAR s3_first_roadblock_peds[s3_index] s3_first_roadblock_ped_blips[s3_index]
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER s3_first_roadblock_peds[s3_index] TRUE
			s3_first_roadblock_ped_blip_flag[s3_index] = 1
			s3_index++
		ENDWHILE
		CREATE_CAR GREENWOO -2000.75 527.24 33.91 s3_first_roadblock_cars[0]
		CREATE_CAR GREENWOO -2007.75 533.24 33.91 s3_first_roadblock_cars[1]
		CREATE_CAR GREENWOO -2012.75 527.24 33.91 s3_first_roadblock_cars[2]
		SET_CAR_HEADING s3_first_roadblock_cars[0] 70.0
		SET_CAR_HEADING s3_first_roadblock_cars[1] 110.0
		SET_CAR_HEADING s3_first_roadblock_cars[2] 250.0
		s3_index = 0
		WHILE s3_index < 3
			CAR_SET_IDLE s3_first_roadblock_cars[s3_index]
			SET_CAR_HEAVY s3_first_roadblock_cars[s3_index] TRUE
			LOCK_CAR_DOORS s3_first_roadblock_cars[s3_index] CARLOCK_LOCKED
			ADD_BLIP_FOR_CAR s3_first_roadblock_cars[s3_index] s3_first_roadblock_car_blips[s3_index]
			SET_CAR_ONLY_DAMAGED_BY_PLAYER s3_first_roadblock_cars[s3_index] TRUE
			s3_first_roadblock_car_blip_flag[s3_index] = 1
			s3_index++
		ENDWHILE

//		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
//		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG

	RETURN

	s3_create_second_roadblock:

//		REQUEST_MODEL GREENWOO
//		REQUEST_MODEL DNB3
//		REQUEST_MODEL DNB2
//		REQUEST_MODEL MP5LNG
		REQUEST_MODEL AK47
		//REQUEST_MODEL DOZER
//		WHILE NOT HAS_MODEL_LOADED GREENWOO
//		   OR NOT HAS_MODEL_LOADED DNB3
//		   OR NOT HAS_MODEL_LOADED DNB2
//		   OR NOT HAS_MODEL_LOADED MP5LNG
//		   OR NOT HAS_MODEL_LOADED AK47
//		   //OR NOT HAS_MODEL_LOADED DOZER
//			WAIT 0
//		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED AK47
		   //OR NOT HAS_MODEL_LOADED DOZER
			WAIT 0
		ENDWHILE

		// second roadblock
		CREATE_CAR GREENWOO -2085.74 314.74 33.97 s3_second_roadblock_cars[0]
		CREATE_CAR GREENWOO -2079.24 323.74 33.97 s3_second_roadblock_cars[1]
		CREATE_CAR GREENWOO -2085.74 326.74 33.97 s3_second_roadblock_cars[2]
		SET_CAR_HEADING s3_second_roadblock_cars[0] 340.0
		SET_CAR_HEADING s3_second_roadblock_cars[1] 20.0
		SET_CAR_HEADING s3_second_roadblock_cars[2] 160.0
		s3_index = 0
		WHILE s3_index < 3
			CAR_SET_IDLE s3_second_roadblock_cars[s3_index]
			SET_CAR_HEAVY s3_second_roadblock_cars[s3_index] TRUE
			LOCK_CAR_DOORS s3_second_roadblock_cars[s3_index] CARLOCK_LOCKED
			SET_CAR_ONLY_DAMAGED_BY_PLAYER s3_second_roadblock_cars[s3_index] TRUE
			s3_index++
		ENDWHILE

		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2075.74 314.74 33.97 s3_second_roadblock_peds[0]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2069.24 323.74 33.97 s3_second_roadblock_peds[1]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2075.74 326.74 33.97 s3_second_roadblock_peds[2]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2076.97 309.56 40.99 s3_second_roadblock_peds[3]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2073.97 309.56 40.99 s3_second_roadblock_peds[4]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2063.46 309.56 40.99 s3_second_roadblock_peds[5]
		s3_index = 0
		WHILE s3_index < 6
			SET_CHAR_DECISION_MAKER s3_second_roadblock_peds[s3_index] s3_roadblock_ped_decisions
			SET_CHAR_STAY_IN_SAME_PLACE s3_second_roadblock_peds[s3_index] TRUE
			IF s3_index >= 3
				GIVE_WEAPON_TO_CHAR s3_second_roadblock_peds[s3_index] WEAPONTYPE_AK47 99999
			ELSE
				GIVE_WEAPON_TO_CHAR s3_second_roadblock_peds[s3_index] WEAPONTYPE_MP5 99999
				SET_CHAR_HEADING s3_second_roadblock_peds[s3_index] 270.0
			ENDIF
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER s3_second_roadblock_peds[s3_index] TRUE
			s3_index++
		ENDWHILE

//		// bulldozer at building site
//		CREATE_CAR DOZER -2046.30 297.84 42.40 s3_bulldozer
//		SET_CAR_HEADING s3_bulldozer 270.0

//		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
//		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
//		MARK_MODEL_AS_NO_LONGER_NEEDED AK47
//		//MARK_MODEL_AS_NO_LONGER_NEEDED DOZER

	RETURN

	s3_create_third_roadblock:

//		REQUEST_MODEL GREENWOO
//		REQUEST_MODEL DNB3
//		REQUEST_MODEL DNB2
//		REQUEST_MODEL MP5LNG
//		REQUEST_MODEL AK47
//		WHILE NOT HAS_MODEL_LOADED GREENWOO
//		   OR NOT HAS_MODEL_LOADED DNB3
//		   OR NOT HAS_MODEL_LOADED DNB2
//		   OR NOT HAS_MODEL_LOADED MP5LNG
//		   OR NOT HAS_MODEL_LOADED AK47
//			WAIT 0
//		ENDWHILE

		// third roadblock
		CREATE_CAR GREENWOO -2141.12 146.06 34.07 s3_third_roadblock_cars[0]
		CREATE_CAR GREENWOO -2149.62 152.06 34.07 s3_third_roadblock_cars[1]
		CREATE_CAR GREENWOO -2153.12 146.06 34.07 s3_third_roadblock_cars[2]
		SET_CAR_HEADING s3_third_roadblock_cars[0] 290.0
		SET_CAR_HEADING s3_third_roadblock_cars[1] 70.0
		SET_CAR_HEADING s3_third_roadblock_cars[2] 110.0
		s3_index = 0
		WHILE s3_index < 3
			CAR_SET_IDLE s3_third_roadblock_cars[s3_index]
			SET_CAR_HEAVY s3_third_roadblock_cars[s3_index] TRUE
			LOCK_CAR_DOORS s3_third_roadblock_cars[s3_index] CARLOCK_LOCKED
			SET_CAR_ONLY_DAMAGED_BY_PLAYER s3_third_roadblock_cars[s3_index] TRUE
			s3_index++
		ENDWHILE

		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2141.12 156.06 34.07 s3_third_roadblock_peds[0]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2149.62 162.06 34.07 s3_third_roadblock_peds[1]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2153.12 156.06 34.07 s3_third_roadblock_peds[2]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2135.68 164.20 41.29 s3_third_roadblock_peds[3]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2135.69 160.93 41.29 s3_third_roadblock_peds[4]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2135.24 153.77 40.72 s3_third_roadblock_peds[5]
		s3_index = 0
		WHILE s3_index < 6
			SET_CHAR_DECISION_MAKER s3_third_roadblock_peds[s3_index] s3_roadblock_ped_decisions
			SET_CHAR_STAY_IN_SAME_PLACE s3_third_roadblock_peds[s3_index] TRUE
			IF s3_index >= 3
				GIVE_WEAPON_TO_CHAR s3_third_roadblock_peds[s3_index] WEAPONTYPE_AK47 99999
				SET_CHAR_HEADING s3_third_roadblock_peds[s3_index] 90.0
			ELSE
				GIVE_WEAPON_TO_CHAR s3_third_roadblock_peds[s3_index] WEAPONTYPE_MP5 99999
			ENDIF
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER s3_third_roadblock_peds[s3_index] TRUE
			s3_index++
		ENDWHILE

//		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
//		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
//		MARK_MODEL_AS_NO_LONGER_NEEDED AK47

	RETURN

	s3_create_fourth_roadblock:

//		REQUEST_MODEL GREENWOO
//		REQUEST_MODEL DNB3
//		REQUEST_MODEL DNB2
//		REQUEST_MODEL MP5LNG
//		WHILE NOT HAS_MODEL_LOADED GREENWOO
//		   OR NOT HAS_MODEL_LOADED DNB3
//		   OR NOT HAS_MODEL_LOADED DNB2
//		   OR NOT HAS_MODEL_LOADED MP5LNG
//			WAIT 0
//		ENDWHILE

		// fourth roadblock
		CREATE_CAR GREENWOO -2161.42 48.75 35.04 s3_fourth_roadblock_cars[0]
		CREATE_CAR GREENWOO -2169.42 54.75 35.04 s3_fourth_roadblock_cars[1]
		CREATE_CAR GREENWOO -2173.42 48.75 35.04 s3_fourth_roadblock_cars[2]
		SET_CAR_HEADING s3_fourth_roadblock_cars[0] 70.0
		SET_CAR_HEADING s3_fourth_roadblock_cars[1] 110.0
		SET_CAR_HEADING s3_fourth_roadblock_cars[2] 250.0
		s3_index = 0
		WHILE s3_index < 3
			CAR_SET_IDLE s3_fourth_roadblock_cars[s3_index]
			SET_CAR_HEAVY s3_fourth_roadblock_cars[s3_index] TRUE
			LOCK_CAR_DOORS s3_fourth_roadblock_cars[s3_index] CARLOCK_LOCKED
			SET_CAR_ONLY_DAMAGED_BY_PLAYER s3_fourth_roadblock_cars[s3_index] TRUE
			s3_index++
		ENDWHILE

		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2161.42 58.75 35.04 s3_fourth_roadblock_peds[0]
		CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2169.42 64.75 35.04 s3_fourth_roadblock_peds[1]
		CREATE_CHAR PEDTYPE_MISSION2 DNB3 -2173.42 58.75 35.04 s3_fourth_roadblock_peds[2]
		s3_index = 0
		WHILE s3_index < 3
			SET_CHAR_DECISION_MAKER s3_fourth_roadblock_peds[s3_index] s3_roadblock_ped_decisions
			SET_CHAR_STAY_IN_SAME_PLACE s3_fourth_roadblock_peds[s3_index] TRUE
			GIVE_WEAPON_TO_CHAR s3_fourth_roadblock_peds[s3_index] WEAPONTYPE_MP5 99999
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER s3_fourth_roadblock_peds[s3_index] TRUE
			s3_index++
		ENDWHILE

//		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
//		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
//		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG

	RETURN

// ********************************************************************************
// Stop collision from loading around roadblocks if neither player nor van are near
// ********************************************************************************

	s3_freeze_first_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_first_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_first_roadblock_cars[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CHAR_DEAD s3_first_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_first_roadblock_peds[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_freeze_second_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_second_roadblock_cars[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_second_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_second_roadblock_peds[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_freeze_third_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_third_roadblock_cars[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_third_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_third_roadblock_peds[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_freeze_fourth_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_fourth_roadblock_cars[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CHAR_DEAD s3_fourth_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_fourth_roadblock_peds[s3_index] TRUE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_unfreeze_first_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_first_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_first_roadblock_cars[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CHAR_DEAD s3_first_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_first_roadblock_peds[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_unfreeze_second_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_second_roadblock_cars[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_second_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_second_roadblock_peds[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_unfreeze_third_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_third_roadblock_cars[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_third_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_third_roadblock_peds[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_unfreeze_fourth_roadblock:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION s3_fourth_roadblock_cars[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CHAR_DEAD s3_fourth_roadblock_peds[s3_index]
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION s3_fourth_roadblock_peds[s3_index] FALSE
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
// 			 Add blips for chars/cars at roadblocks
// ************************************************************

	s3_add_second_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
				ADD_BLIP_FOR_CAR s3_second_roadblock_cars[s3_index] s3_second_roadblock_car_blips[s3_index]
				s3_second_roadblock_car_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_second_roadblock_peds[s3_index]
				ADD_BLIP_FOR_CHAR s3_second_roadblock_peds[s3_index] s3_second_roadblock_ped_blips[s3_index]
				s3_second_roadblock_ped_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_add_third_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
				ADD_BLIP_FOR_CAR s3_third_roadblock_cars[s3_index] s3_third_roadblock_car_blips[s3_index]
				s3_third_roadblock_car_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

		s3_index = 0
		WHILE s3_index < 6
			IF NOT IS_CHAR_DEAD s3_third_roadblock_peds[s3_index]
				ADD_BLIP_FOR_CHAR s3_third_roadblock_peds[s3_index] s3_third_roadblock_ped_blips[s3_index]
				s3_third_roadblock_ped_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_add_fourth_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
				ADD_BLIP_FOR_CAR s3_fourth_roadblock_cars[s3_index] s3_fourth_roadblock_car_blips[s3_index]
				s3_fourth_roadblock_car_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

		s3_index = 0
		WHILE s3_index < 3
			IF NOT IS_CHAR_DEAD s3_fourth_roadblock_peds[s3_index]
				ADD_BLIP_FOR_CHAR s3_fourth_roadblock_peds[s3_index] s3_fourth_roadblock_ped_blips[s3_index]
				s3_fourth_roadblock_ped_blip_flag[s3_index] = 1
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
// 				Remove roadblock car and ped blips
// ************************************************************

	s3_remove_blip_check:

		// first roadblock
		s3_index = 0
		WHILE s3_index < 3
			IF s3_first_roadblock_ped_blip_flag[s3_index] = 1
			AND IS_CHAR_DEAD s3_first_roadblock_peds[s3_index]
				REMOVE_BLIP s3_first_roadblock_ped_blips[s3_index]
				s3_first_roadblock_ped_blip_flag[s3_index] = 0
			ENDIF
			IF s3_first_roadblock_car_blip_flag[s3_index] = 1
			AND IS_CAR_DEAD s3_first_roadblock_cars[s3_index]
				REMOVE_BLIP s3_first_roadblock_car_blips[s3_index]
				s3_first_roadblock_car_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE
		// second roadblock
		s3_index = 0
		WHILE s3_index < 6
			IF s3_second_roadblock_ped_blip_flag[s3_index] = 1
			AND IS_CHAR_DEAD s3_second_roadblock_peds[s3_index]
				REMOVE_BLIP s3_second_roadblock_ped_blips[s3_index]
				s3_second_roadblock_ped_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF s3_second_roadblock_car_blip_flag[s3_index] = 1
			AND IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
				REMOVE_BLIP s3_second_roadblock_car_blips[s3_index]
				s3_second_roadblock_car_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE
		// third roadblock
		s3_index = 0
		WHILE s3_index < 6
			IF s3_third_roadblock_ped_blip_flag[s3_index] = 1
			AND IS_CHAR_DEAD s3_third_roadblock_peds[s3_index]
				REMOVE_BLIP s3_third_roadblock_ped_blips[s3_index]
				s3_third_roadblock_ped_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			IF s3_third_roadblock_car_blip_flag[s3_index] = 1
			AND IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
				REMOVE_BLIP s3_third_roadblock_car_blips[s3_index]
				s3_third_roadblock_car_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE
		// fourth roadblock
		s3_index = 0
		WHILE s3_index < 3
			IF s3_fourth_roadblock_ped_blip_flag[s3_index] = 1
			AND IS_CHAR_DEAD s3_fourth_roadblock_peds[s3_index]
				REMOVE_BLIP s3_fourth_roadblock_ped_blips[s3_index]
				s3_fourth_roadblock_ped_blip_flag[s3_index] = 0
			ENDIF
			IF s3_fourth_roadblock_car_blip_flag[s3_index] = 1
			AND IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
				REMOVE_BLIP s3_fourth_roadblock_car_blips[s3_index]
				s3_fourth_roadblock_car_blip_flag[s3_index] = 0
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
// 				 Check for destroyed roadblocks
// ************************************************************

	s3_first_roadblock_destroyed_check:

		s3_first_roadblock_destroyed = 1
		s3_index = 0
		WHILE s3_index < 3
		AND s3_first_roadblock_destroyed = 1
			IF NOT IS_CHAR_DEAD s3_first_roadblock_peds[s3_index]
				s3_first_roadblock_destroyed = 0
			ENDIF
			IF NOT IS_CAR_DEAD s3_first_roadblock_cars[s3_index]
				s3_first_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_second_roadblock_destroyed_check:

		s3_second_roadblock_destroyed = 1
		s3_index = 0
		WHILE s3_index < 6
		AND s3_second_roadblock_destroyed = 1
			IF NOT IS_CHAR_DEAD s3_second_roadblock_peds[s3_index]
				s3_second_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
		AND s3_second_roadblock_destroyed = 1
			IF NOT IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
				s3_second_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_third_roadblock_destroyed_check:

		s3_third_roadblock_destroyed = 1
		s3_index = 0
		WHILE s3_index < 6
		AND s3_third_roadblock_destroyed = 1
			IF NOT IS_CHAR_DEAD s3_third_roadblock_peds[s3_index]
				s3_third_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
		AND s3_third_roadblock_destroyed = 1
			IF NOT IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
				s3_third_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

	s3_fourth_roadblock_destroyed_check:

		s3_fourth_roadblock_destroyed = 1
		s3_index = 0
		WHILE s3_index < 3
		AND s3_fourth_roadblock_destroyed = 1
			IF NOT IS_CHAR_DEAD s3_fourth_roadblock_peds[s3_index]
				s3_fourth_roadblock_destroyed = 0
			ENDIF
			IF NOT IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
				s3_fourth_roadblock_destroyed = 0
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
// 				 Remove all blips at roadblock
// ************************************************************

	s3_remove_first_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 3
			REMOVE_BLIP s3_first_roadblock_ped_blips[s3_index]
			REMOVE_BLIP s3_first_roadblock_car_blips[s3_index]
			s3_first_roadblock_ped_blip_flag[s3_index] = 0
			s3_first_roadblock_car_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE

	RETURN

	s3_remove_second_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 6
			REMOVE_BLIP s3_second_roadblock_ped_blips[s3_index]
			s3_second_roadblock_ped_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			REMOVE_BLIP s3_second_roadblock_car_blips[s3_index]
			s3_second_roadblock_car_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE

	RETURN

	s3_remove_third_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 6
			REMOVE_BLIP s3_third_roadblock_ped_blips[s3_index]
			s3_third_roadblock_ped_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE
		s3_index = 0
		WHILE s3_index < 3
			REMOVE_BLIP s3_third_roadblock_car_blips[s3_index]
			s3_third_roadblock_car_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE

	RETURN

	s3_remove_fourth_roadblock_blips:

		s3_index = 0
		WHILE s3_index < 3
			REMOVE_BLIP s3_fourth_roadblock_ped_blips[s3_index]
			REMOVE_BLIP s3_fourth_roadblock_car_blips[s3_index]
			s3_fourth_roadblock_ped_blip_flag[s3_index] = 0
			s3_fourth_roadblock_car_blip_flag[s3_index] = 0
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
// 				 Update ped attacking states
// ************************************************************

	s3_update_roadblock_ped_attacks:

		IF NOT IS_CAR_DEAD s3_paste_van

			LVAR_INT s3_num_of_roadblock_peds
			LVAR_INT s3_roadblock_peds[6] s3_roadblock_cars[3] s3_attack_flags[6]

			SWITCH s3_roadblock_to_update
				CASE 0
				CASE 3
					s3_num_of_roadblock_peds = 3
					BREAK
				CASE 1
				CASE 2
					s3_num_of_roadblock_peds = 6
					BREAK
				DEFAULT
					s3_roadblock_to_update = -1
					BREAK
			ENDSWITCH
			IF s3_roadblock_to_update = -1
				RETURN
			ENDIF
			s3_index = 0
			WHILE s3_index < s3_num_of_roadblock_peds
				SWITCH s3_roadblock_to_update
					CASE 0
						s3_roadblock_peds[s3_index] = s3_first_roadblock_peds[s3_index]
						s3_attack_flags[s3_index] = s3_first_roadblock_ped_attack_flags[s3_index]
						IF s3_index < 3
							s3_roadblock_cars[s3_index] = s3_first_roadblock_cars[s3_index]
						ENDIF							
						BREAK
					CASE 1
						s3_roadblock_peds[s3_index] = s3_second_roadblock_peds[s3_index]
						s3_attack_flags[s3_index] = s3_second_roadblock_ped_attack_flags[s3_index]
						IF s3_index < 3
							s3_roadblock_cars[s3_index] = s3_second_roadblock_cars[s3_index]
						ENDIF							
						BREAK
					CASE 2
						s3_roadblock_peds[s3_index] = s3_third_roadblock_peds[s3_index]
						s3_attack_flags[s3_index] = s3_third_roadblock_ped_attack_flags[s3_index]
						IF s3_index < 3
							s3_roadblock_cars[s3_index] = s3_third_roadblock_cars[s3_index]
						ENDIF							
						BREAK
					CASE 3
						s3_roadblock_peds[s3_index] = s3_fourth_roadblock_peds[s3_index]
						s3_attack_flags[s3_index] = s3_fourth_roadblock_ped_attack_flags[s3_index]
						IF s3_index < 3
							s3_roadblock_cars[s3_index] = s3_fourth_roadblock_cars[s3_index]
						ENDIF							
						BREAK
					DEFAULT
						BREAK
				ENDSWITCH
				s3_index++
			ENDWHILE

			IF NOT s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
				GOSUB s3_has_player_attacked_roadblock
			ENDIF

			GET_CAR_COORDINATES s3_paste_van s3_paste_van_x s3_paste_van_y s3_paste_van_z
			GET_CHAR_COORDINATES scplayer s3_player_x s3_player_y s3_player_z

			s3_index = 0
			WHILE s3_index < s3_num_of_roadblock_peds
				IF NOT IS_CHAR_DEAD s3_roadblock_peds[s3_index]

					GET_CHAR_COORDINATES s3_roadblock_peds[s3_index] s3_roadblock_ped_x s3_roadblock_ped_y s3_roadblock_ped_z
					GET_DISTANCE_BETWEEN_COORDS_2D s3_paste_van_x s3_paste_van_y s3_roadblock_ped_x s3_roadblock_ped_y s3_dist_from_van_to_ped
					IF s3_dist_from_van_to_ped < 35.0
						IF NOT s3_attack_flags[s3_index] = 3
							SET_CHAR_ACCURACY s3_roadblock_peds[s3_index] 85
							GET_SCRIPT_TASK_STATUS s3_roadblock_peds[s3_index] TASK_DESTROY_CAR s3_task_status
							IF s3_task_status = FINISHED_TASK
								TASK_DESTROY_CAR s3_roadblock_peds[s3_index] s3_paste_van
							ENDIF
							s3_attack_flags[s3_index] = 3
						ENDIF
					ELSE
						GET_DISTANCE_BETWEEN_COORDS_2D s3_player_x s3_player_y s3_roadblock_ped_x s3_roadblock_ped_y s3_dist_from_player_to_ped
						IF s3_dist_from_player_to_ped < 35.0
						AND s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
							IF NOT s3_attack_flags[s3_index] = 2
								SET_CHAR_ACCURACY s3_roadblock_peds[s3_index] 60
								GET_SCRIPT_TASK_STATUS s3_roadblock_peds[s3_index] TASK_KILL_CHAR_ON_FOOT s3_task_status
								IF s3_task_status = FINISHED_TASK
									TASK_KILL_CHAR_ON_FOOT s3_roadblock_peds[s3_index] scplayer
								ENDIF
								s3_attack_flags[s3_index] = 2
							ENDIF
						ELSE
							IF s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
								IF NOT s3_attack_flags[s3_index] = 1
									SET_CHAR_ACCURACY s3_roadblock_peds[s3_index] 60
									s3_attack_flags[s3_index] = 1
								ENDIF
								IF s3_attack_flags[s3_index] = 1
									GET_SCRIPT_TASK_STATUS s3_roadblock_peds[s3_index] TASK_SHOOT_AT_COORD s3_task_status
									IF s3_task_status = FINISHED_TASK
										TASK_SHOOT_AT_COORD s3_roadblock_peds[s3_index] s3_player_x s3_player_y s3_player_z 10000
									ENDIF
								ENDIF
							ELSE
								IF NOT s3_attack_flags[s3_index] = 0
									SET_CHAR_ACCURACY s3_roadblock_peds[s3_index] 60
									s3_attack_flags[s3_index] = 0
								ENDIF
								IF s3_attack_flags[s3_index] = 0
									IF s3_dist_from_player_to_ped < 35.0
										GET_SCRIPT_TASK_STATUS s3_roadblock_peds[s3_index] TASK_AIM_GUN_AT_CHAR s3_task_status
										IF s3_task_status = FINISHED_TASK
											TASK_AIM_GUN_AT_CHAR s3_roadblock_peds[s3_index] scplayer 9999999
										ENDIF
									ELSE
										GET_SCRIPT_TASK_STATUS s3_roadblock_peds[s3_index] TASK_AIM_GUN_AT_COORD s3_task_status
										IF s3_task_status = FINISHED_TASK
											TASK_AIM_GUN_AT_COORD s3_roadblock_peds[s3_index] s3_player_x s3_player_y s3_player_z 9999999
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				ENDIF
				s3_index++
			ENDWHILE

			s3_index = 0
			WHILE s3_index < s3_num_of_roadblock_peds
				SWITCH s3_roadblock_to_update
					CASE 0
						s3_first_roadblock_ped_attack_flags[s3_index] = s3_attack_flags[s3_index]
						BREAK
					CASE 1
						s3_second_roadblock_ped_attack_flags[s3_index] = s3_attack_flags[s3_index]
						BREAK
					CASE 2
						s3_third_roadblock_ped_attack_flags[s3_index] = s3_attack_flags[s3_index]
						BREAK
					CASE 3
						s3_fourth_roadblock_ped_attack_flags[s3_index] = s3_attack_flags[s3_index]
						BREAK
					DEFAULT
						BREAK
				ENDSWITCH
				s3_index++
			ENDWHILE

		ENDIF

	RETURN

	s3_has_player_attacked_roadblock:

		LVAR_FLOAT s3_xmin s3_ymin s3_zmin s3_xmax s3_ymax s3_zmax
		s3_index = 0
		WHILE s3_index < s3_num_of_roadblock_peds
		AND NOT s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
			IF NOT IS_CHAR_DEAD s3_roadblock_peds[s3_index]
				GET_CHAR_HIGHEST_PRIORITY_EVENT s3_roadblock_peds[s3_index] s3_event_type
				GET_CHAR_COORDINATES s3_roadblock_peds[s3_index] player_x player_y player_z
				s3_xmin = player_x - 10.0
				s3_ymin = player_y - 10.0
				s3_zmin = player_z - 10.0
				s3_xmax = player_x + 10.0
				s3_ymax = player_y + 10.0
				s3_zmax = player_z + 10.0
				IF s3_event_type = EVENT_SHOT_FIRED_WHIZZED_BY
				OR IS_PROJECTILE_IN_AREA s3_xmin s3_ymin s3_zmin s3_xmax s3_ymax s3_zmax
					s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
				ENDIF
			ELSE
				s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
			ENDIF
			IF s3_index < 3
				IF IS_CAR_DEAD s3_roadblock_cars[s3_index]
				AND NOT s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
					s3_player_attacked_roadblock[s3_roadblock_to_update] = 1
				ENDIF
			ENDIF
			s3_index++
		ENDWHILE

	RETURN

// ************************************************************
//    Update weights of paste van and cars at each roadblock
// ************************************************************

	s3_update_vehicle_weights:

		IF NOT IS_CAR_DEAD s3_paste_van
		AND NOT IS_CHAR_DEAD s3_paste_van_driver
		
			// first roadblock
			IF s3_current_roadblock = 1

				GOSUB s3_first_roadblock_destroyed_check

				IF s3_first_roadblock_destroyed = 1
					// van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
				ELSE
					// if van near any car at current roadblock, van light; else, van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
					s3_index = 0
					WHILE s3_index < 3
					AND NOT s3_paste_van_heavy = 0
						IF NOT IS_CAR_DEAD s3_first_roadblock_cars[s3_index]
							IF IS_CHAR_IN_CAR s3_paste_van_driver s3_paste_van
							AND LOCATE_CHAR_IN_CAR_CAR_2D s3_paste_van_driver s3_first_roadblock_cars[s3_index] 50.0 50.0 FALSE
								SET_CAR_HEAVY s3_paste_van FALSE
								s3_paste_van_heavy = 0
							ENDIF
						ENDIF
						s3_index++
					ENDWHILE
				ENDIF

			ENDIF

			// second roadblock
			IF s3_current_roadblock = 2

				GOSUB s3_second_roadblock_destroyed_check

				IF s3_second_roadblock_destroyed = 1
					// van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
				ELSE
					// if van near any car at current roadblock, van light; else, van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
					s3_index = 0
					WHILE s3_index < 3
					AND NOT s3_paste_van_heavy = 0
						IF NOT IS_CAR_DEAD s3_second_roadblock_cars[s3_index]
							IF IS_CHAR_IN_CAR s3_paste_van_driver s3_paste_van
							AND LOCATE_CHAR_IN_CAR_CAR_2D s3_paste_van_driver s3_second_roadblock_cars[s3_index] 50.0 50.0 FALSE
								SET_CAR_HEAVY s3_paste_van FALSE
								s3_paste_van_heavy = 0
							ENDIF
						ENDIF
						s3_index++
					ENDWHILE
				ENDIF

			ENDIF

			// third roadblock
			IF s3_current_roadblock = 3

				GOSUB s3_third_roadblock_destroyed_check

				IF s3_third_roadblock_destroyed = 1
					// van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
				ELSE
					// if van near any car at current roadblock, van light; else, van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
					s3_index = 0
					WHILE s3_index < 3
					AND NOT s3_paste_van_heavy = 0
						IF NOT IS_CAR_DEAD s3_third_roadblock_cars[s3_index]
							IF IS_CHAR_IN_CAR s3_paste_van_driver s3_paste_van
							AND LOCATE_CHAR_IN_CAR_CAR_2D s3_paste_van_driver s3_third_roadblock_cars[s3_index] 50.0 50.0 FALSE
								SET_CAR_HEAVY s3_paste_van FALSE
								s3_paste_van_heavy = 0
							ENDIF
						ENDIF
						s3_index++
					ENDWHILE
				ENDIF

			ENDIF

			// fourth roadblock
			IF s3_current_roadblock = 4

				GOSUB s3_fourth_roadblock_destroyed_check

				IF s3_fourth_roadblock_destroyed = 1
					// van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
				ELSE
					// if van near any car at current roadblock, van light; else, van heavy
					IF NOT s3_paste_van_heavy = 1
						SET_CAR_HEAVY s3_paste_van TRUE
						s3_paste_van_heavy = 1
					ENDIF
					s3_index = 0
					WHILE s3_index < 3
					AND NOT s3_paste_van_heavy = 0
						IF NOT IS_CAR_DEAD s3_fourth_roadblock_cars[s3_index]
							IF IS_CHAR_IN_CAR s3_paste_van_driver s3_paste_van
							AND LOCATE_CHAR_IN_CAR_CAR_2D s3_paste_van_driver s3_fourth_roadblock_cars[s3_index] 50.0 50.0 FALSE
								SET_CAR_HEAVY s3_paste_van FALSE
								s3_paste_van_heavy = 0
							ENDIF
						ENDIF
						s3_index++
					ENDWHILE
				ENDIF

			ENDIF

			IF s3_current_roadblock = -1
			AND NOT s3_paste_van_heavy = 1
				SET_CAR_HEAVY s3_paste_van TRUE
				s3_paste_van_heavy = 1
			ENDIF

			// if van's not near the current roadblock to be destroyed (ie. heavy),
			// set driving mode to avoid cars; if near (ie. not heavy) set to drive into roadblock.
			// NB. needs to be set each frame to make sure it's not overwritten by the sequence task.
			IF s3_paste_van_heavy = 1
				SET_CAR_DRIVING_STYLE s3_paste_van DRIVINGMODE_AVOIDCARS
				IF NOT s3_paste_van_collisionproof = 1
					SET_CAR_PROOFS s3_paste_van FALSE TRUE FALSE TRUE FALSE
					s3_paste_van_collisionproof = 1
				ENDIF
			ELSE
				SET_CAR_DRIVING_STYLE s3_paste_van DRIVINGMODE_SLOWDOWNFORCARS
				IF s3_paste_van_collisionproof = 1
					SET_CAR_PROOFS s3_paste_van FALSE TRUE FALSE FALSE FALSE
					s3_paste_van_collisionproof = 0
				ENDIF
			ENDIF

		ENDIF

	RETURN

// ****************************************************************************************
// When heading to next roadblock, add blip for player's bike if player's not already on it
// ****************************************************************************************

	s3_add_blip_for_player_bike:

		IF NOT IS_CAR_DEAD s3_player_bike
			IF NOT IS_CHAR_IN_CAR scplayer s3_player_bike
			AND NOT s3_player_bike_has_blip = 1
				ADD_BLIP_FOR_CAR s3_player_bike s3_player_bike_blip
				SET_BLIP_AS_FRIENDLY s3_player_bike_blip TRUE
				CHANGE_BLIP_DISPLAY s3_player_bike_blip MARKER_ONLY 
				s3_player_bike_has_blip = 1
			ENDIF
		ENDIF

	RETURN

// **************************************************************
// 			Show player talking to van driver at factory
// **************************************************************

	s3_play_van_cutscene:

		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		
		SET_PLAYER_CONTROL player1 OFF

		CLEAR_AREA -2170.58 -206.92 34.57 100.0 FALSE
		REQUEST_COLLISION -2170.58 -206.92
		LOAD_SCENE_IN_DIRECTION	-2170.58 -206.92 34.57 270.0
		CLEAR_PRINTS

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		IF NOT IS_CAR_DEAD s3_paste_van
			SET_CAR_COORDINATES s3_paste_van -2170.58 -206.92 34.57
			SET_CAR_HEADING s3_paste_van 91.13
		ENDIF
		IF NOT IS_CHAR_DEAD s3_paste_van_driver
			CLEAR_CHAR_TASKS_IMMEDIATELY s3_paste_van_driver
			IF IS_CHAR_IN_ANY_CAR s3_paste_van_driver
				WARP_CHAR_FROM_CAR_TO_COORD s3_paste_van_driver -2170.45 -209.19 34.37
			ELSE
				SET_CHAR_COORDINATES s3_paste_van_driver -2170.45 -209.19 34.37
			ENDIF
			SET_CHAR_HEADING s3_paste_van_driver 207.63
		ENDIF
		LVAR_INT s3_player_currently_has_car
		s3_player_currently_has_car = 0
		IF s3_player_entered_any_car = 1
			IF NOT IS_CAR_DEAD s3_last_player_car_before_van_cutscene
				IF IS_CHAR_IN_CAR scplayer s3_last_player_car_before_van_cutscene
				OR LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer s3_last_player_car_before_van_cutscene 20.0 20.0 FALSE
					s3_player_currently_has_car = 1
				ENDIF
			ENDIF
			IF s3_player_currently_has_car = 0
				DELETE_CAR s3_last_player_car_before_van_cutscene
			ENDIF
		ENDIF
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer -2169.44 -210.34 34.37
		ELSE
			SET_CHAR_COORDINATES scplayer -2169.44 -210.34 34.37
		ENDIF
		SET_CHAR_HEADING scplayer 48.2
		// move player's car out of the way of the cutscene temporarily
		IF s3_player_currently_has_car = 1
		AND NOT IS_CAR_DEAD s3_last_player_car_before_van_cutscene
			IF LOCATE_CAR_2D s3_last_player_car_before_van_cutscene -2170.45 -209.19 20.0 20.0 FALSE
				SET_CAR_COORDINATES s3_last_player_car_before_van_cutscene -2154.28 -275.18 -100.0
				SET_CAR_HEADING s3_last_player_car_before_van_cutscene 270.0
			ENDIF
		ENDIF

		WAIT 1000

		SWITCH_WIDESCREEN ON

		IF NOT IS_CHAR_DEAD s3_paste_van_driver
			TASK_LOOK_AT_CHAR scplayer s3_paste_van_driver -2
			TASK_LOOK_AT_CHAR s3_paste_van_driver scplayer -2
		ENDIF

		SET_FIXED_CAMERA_POSITION -2172.35 -210.03 35.67 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2170.27 -210.10 35.4 JUMP_CUT

		DO_FADE 1000 FADE_IN

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		s3_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		s3_current_audio_needed = S3_MISSION_PASSED_AUDIO
		GOSUB s3_setup_audio_data

		TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1

		WHILE s3_audio_index < 1
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
				START_CHAR_FACIAL_TALK scplayer 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		STOP_CHAR_FACIAL_TALK scplayer
		WHILE s3_audio_index < s3_total_audio_to_play
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
				START_CHAR_FACIAL_TALK scplayer 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		STOP_CHAR_FACIAL_TALK scplayer

		s3_cutscene_skipped = 0
		
		SKIP_CUTSCENE_END

		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		IF s3_cutscene_skipped = 1
		ENDIF

		STOP_CHAR_FACIAL_TALK scplayer
		CLEAR_LOOK_AT scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_PRINTS

		DELETE_CAR s3_paste_van
		DELETE_CHAR s3_paste_van_driver

		CLEAR_AREA -2127.08 -87.75 35.32 20.0 FALSE
		REQUEST_COLLISION -2127.08 -87.75
		LOAD_SCENE -2127.08 -87.75 35.32
		IF s3_player_currently_has_car = 1
		AND NOT IS_CAR_DEAD s3_last_player_car_before_van_cutscene
			SET_CAR_COORDINATES	s3_last_player_car_before_van_cutscene -2127.08 -87.75 -100.0
			SET_CAR_HEADING s3_last_player_car_before_van_cutscene 0.0
			WARP_CHAR_INTO_CAR scplayer s3_last_player_car_before_van_cutscene
		ELSE
			SET_CHAR_COORDINATES scplayer -2127.08 -87.75 -100.0
			SET_CHAR_HEADING scplayer 0.0
		ENDIF

		REMOVE_BLIP s3_player_bike_blip
		REMOVE_BLIP s3_rocket_blip
		REMOVE_BLIP s3_paste_van_blip
		//REMOVE_BLIP s3_destination_blip
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
		MARK_MODEL_AS_NO_LONGER_NEEDED RUMPO
		MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
		MARK_MODEL_AS_NO_LONGER_NEEDED AK47
		SWITCH_ROADS_BACK_TO_ORIGINAL -2005.23 517.16 33.0 -2002.69 554.12 36.0
		SWITCH_ROADS_BACK_TO_ORIGINAL -2097.80 319.42 33.0 -2035.57 321.05 36.0
		SWITCH_ROADS_BACK_TO_ORIGINAL -2150.18 138.75 33.0 -2145.71 160.84 36.0
		SWITCH_ROADS_BACK_TO_ORIGINAL -2168.24 42.13  33.0 -2165.99 61.70 36.0
		SWITCH_RANDOM_TRAINS ON

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

		WAIT 1000

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
// 	  Cutscene showing player leaving car to pick up escort bike
// ***************************************************************

	s3_play_van_location_cutscene:

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0
		CLEAR_AREA s3_van_location_x s3_van_location_y s3_van_location_z 100.0 FALSE
		CLEAR_HELP
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_PRINTS

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		IF NOT IS_CAR_DEAD s3_paste_van
			SET_CAR_COORDINATES s3_paste_van s3_paste_van_x s3_paste_van_y s3_paste_van_z
			SET_CAR_HEADING s3_paste_van s3_paste_van_heading
		ENDIF
		IF NOT IS_CAR_DEAD s3_player_bike
			SET_CAR_COORDINATES s3_player_bike -1830.16 -17.64 14.79
			SET_CAR_HEADING s3_player_bike 268.78
		ENDIF
		IF NOT IS_CAR_DEAD s3_meet_car
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_INTO_CAR scplayer s3_meet_car
			ENDIF
			SET_CAR_COORDINATES s3_meet_car s3_van_location_x s3_van_location_y s3_van_location_z
			SET_CAR_HEADING s3_meet_car 90.0
			CAR_SET_IDLE s3_meet_car
		ENDIF

		REQUEST_ANIMATION CAR_CHAT
		WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0
		ENDWHILE

		s3_current_audio_needed = S3_SECOND_CUTSCENE_AUDIO
		GOSUB s3_setup_audio_data

		IF NOT IS_CHAR_DEAD s3_toreno
			TASK_PLAY_ANIM s3_toreno CAR_Sc3_FR CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
			IF NOT IS_CHAR_DEAD s3_tbone
				TASK_LOOK_AT_CHAR s3_tbone s3_toreno -2
			ENDIF
		ENDIF

		SET_FIXED_CAMERA_POSITION -1815.98 3.07 15.31 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1815.5 2.94 15.25 JUMP_CUT

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		s3_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		WHILE s3_audio_index < 1
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
			AND NOT IS_CHAR_DEAD s3_toreno
				START_CHAR_FACIAL_TALK s3_toreno 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		IF NOT IS_CHAR_DEAD s3_toreno
			STOP_CHAR_FACIAL_TALK s3_toreno
			TASK_LOOK_AT_CHAR scplayer s3_toreno -1
		ENDIF
		WHILE s3_audio_index < 2
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
			AND NOT IS_CHAR_DEAD s3_toreno
				START_CHAR_FACIAL_TALK s3_toreno 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		IF NOT IS_CHAR_DEAD s3_toreno
			STOP_CHAR_FACIAL_TALK s3_toreno
		ENDIF
		WHILE s3_audio_index < 3
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
			AND NOT IS_CHAR_DEAD s3_toreno
				START_CHAR_FACIAL_TALK s3_toreno 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		IF NOT IS_CHAR_DEAD s3_toreno
			STOP_CHAR_FACIAL_TALK s3_toreno
		ENDIF

		CLEAR_LOOK_AT scplayer

		WAIT 200

		//TASK_PLAY_ANIM scplayer CAR_getoutL_LHS PED 4.0 FALSE FALSE FALSE FALSE -1
		TASK_LEAVE_ANY_CAR scplayer

		WAIT 500

		IF NOT IS_CHAR_DEAD s3_toreno
			TASK_LOOK_AT_CHAR s3_toreno scplayer -1
		ENDIF

		WAIT 2000

		IF NOT IS_CHAR_DEAD s3_tbone
			CLEAR_LOOK_AT s3_tbone
		ENDIF

		s3_audio_index = 5
		WHILE s3_audio_index < 6
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
			AND NOT IS_CHAR_DEAD s3_toreno
				START_CHAR_FACIAL_TALK s3_toreno 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		IF NOT IS_CHAR_DEAD s3_toreno
			STOP_CHAR_FACIAL_TALK s3_toreno
		ENDIF

		IF NOT IS_CHAR_DEAD s3_tbone
			TASK_LOOK_AT_CHAR s3_tbone scplayer -1
		ENDIF

		WAIT 1000

		WHILE s3_audio_index < s3_total_audio_to_play
			WAIT 0
			GOSUB s3_load_and_play_audio
			IF s3_audio_is_playing = 2
			AND NOT s3_started_talking = 1
			AND NOT IS_CHAR_DEAD s3_tbone
				START_CHAR_FACIAL_TALK s3_tbone 10000
				s3_started_talking = 1
			ENDIF
		ENDWHILE
		IF NOT IS_CHAR_DEAD s3_tbone
			STOP_CHAR_FACIAL_TALK s3_tbone
		ENDIF

		WAIT 1000

		IF NOT IS_CHAR_DEAD s3_toreno
			CLEAR_LOOK_AT s3_toreno
		ENDIF

		s3_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		DO_FADE 700 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		IF s3_cutscene_skipped = 1
		ENDIF

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		CLEAR_LOOK_AT scplayer
		CLEAR_CHAR_TASKS scplayer

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer -1816.19 -1.19 14.15
		ELSE
			SET_CHAR_COORDINATES scplayer -1816.19 -1.19 14.15
		ENDIF
		SET_CHAR_HEADING scplayer 132.83

		DELETE_CAR s3_meet_car
		DELETE_CHAR s3_toreno
		DELETE_CHAR s3_tbone
		UNLOAD_SPECIAL_CHARACTER 1
		UNLOAD_SPECIAL_CHARACTER 2

		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS

		REMOVE_ANIMATION CAR_CHAT

		SET_CAR_DENSITY_MULTIPLIER 1.0
		SET_PED_DENSITY_MULTIPLIER 1.0

		SET_MAX_WANTED_LEVEL 0

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
// 						  Mission audio
// ***************************************************************

	s3_setup_audio_data:

		SWITCH s3_current_audio_needed

			CASE S3_MOBILE_AUDIO
				$s3_audio_text[0] = &MJIZ01A
				$s3_audio_text[1] = &MJIZ01B
				$s3_audio_text[2] = &MJIZ01C
				$s3_audio_text[3] = &MJIZ01D
				$s3_audio_text[4] = &MJIZ01E
				$s3_audio_text[5] = &MJIZ01F
				$s3_audio_text[6] = &MJIZ01G
				$s3_audio_text[7] = &MJIZ01H
				s3_audio_sound[0] = SOUND_MJIZ01A
				s3_audio_sound[1] = SOUND_MJIZ01B
				s3_audio_sound[2] = SOUND_MJIZ01C
				s3_audio_sound[3] = SOUND_MJIZ01D
				s3_audio_sound[4] = SOUND_MJIZ01E
				s3_audio_sound[5] = SOUND_MJIZ01F
				s3_audio_sound[6] = SOUND_MJIZ01G
				s3_audio_sound[7] = SOUND_MJIZ01H
				s3_total_audio_to_play = 8
				BREAK

			CASE S3_SECOND_CUTSCENE_AUDIO
				$s3_audio_text[0] = &SYN3_CA
				$s3_audio_text[1] = &SYN3_CB
				$s3_audio_text[2] = &SYN3_CC
				$s3_audio_text[3] = &SYN3_CD
				$s3_audio_text[4] = &SYN3_CE
				$s3_audio_text[5] = &SYN3_CF
				$s3_audio_text[6] = &SYN3_CG
				s3_audio_sound[0] = SOUND_SYN3_CA
				s3_audio_sound[1] = SOUND_SYN3_CB
				s3_audio_sound[2] = SOUND_SYN3_CC
				s3_audio_sound[3] = SOUND_SYN3_CD
				s3_audio_sound[4] = SOUND_SYN3_CE
				s3_audio_sound[5] = SOUND_SYN3_CF
				s3_audio_sound[6] = SOUND_SYN3_CG
				s3_total_audio_to_play = 7
				BREAK

			CASE S3_MISSION_PASSED_AUDIO
				$s3_audio_text[0] = &SYN3_EA
				$s3_audio_text[1] = &SYN3_EB
				s3_audio_sound[0] = SOUND_SYN3_EA
				s3_audio_sound[1] = SOUND_SYN3_EB
				s3_total_audio_to_play = 2
				BREAK

			DEFAULT
				BREAK

		ENDSWITCH

		s3_audio_is_playing = 0
		s3_audio_index = 0
		s3_started_talking = 0

	RETURN

	s3_load_and_play_audio:

		IF s3_audio_is_playing = 0
		OR s3_audio_is_playing = 1
			IF s3_audio_index < s3_total_audio_to_play
				IF TIMERB > 1000
					GOSUB s3_play_audio
				ENDIF
			ENDIF
		ENDIF

		IF s3_audio_is_playing = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				s3_audio_is_playing = 0
				s3_audio_index++
				s3_started_talking = 0
				CLEAR_PRINTS
				TIMERB = 0	

				

			ENDIF
		ENDIF

	RETURN

	s3_play_audio:

		IF s3_audio_is_playing = 0
			LOAD_MISSION_AUDIO 1 s3_audio_sound[s3_audio_index]
			s3_audio_is_playing = 1
		ENDIF
		IF s3_audio_is_playing = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW $s3_audio_text[s3_audio_index] 10000 1
				PLAY_MISSION_AUDIO 1
				s3_audio_is_playing = 2
			ENDIF
		ENDIF	
		
	RETURN

// ***************************************************************
// 	 Play audio for Toreno telling player to get back in the car
// ***************************************************************

	s3_play_toreno_get_in_audio:

		CLEAR_MISSION_AUDIO 2

		SWITCH s3_get_in_audio_stage
			CASE 0
				LOAD_MISSION_AUDIO 2 SOUND_TORX_AC
				BREAK
			CASE 1
				LOAD_MISSION_AUDIO 2 SOUND_TORX_AB
				BREAK
			CASE 2
				LOAD_MISSION_AUDIO 2 SOUND_TORX_AD
				BREAK
			CASE 3
				LOAD_MISSION_AUDIO 2 SOUND_TORX_AA
				BREAK
			CASE 4
				LOAD_MISSION_AUDIO 2 SOUND_TORX_AE
				BREAK
			DEFAULT
				BREAK
		ENDSWITCH

		STOP_CHAR_FACIAL_TALK scplayer
		CLEAR_MISSION_AUDIO 1

		WHILE NOT HAS_MISSION_AUDIO_LOADED 2
			WAIT 0
			IF IS_CHAR_DEAD s3_toreno
			OR IS_CHAR_DEAD s3_tbone
			OR IS_CAR_DEAD s3_meet_car
			OR IS_CAR_DEAD s3_paste_van
			OR IS_CHAR_DEAD s3_paste_van_driver
			OR IS_CAR_DEAD s3_player_bike
				RETURN
			ENDIF
			IF NOT s3_player_picked_up_rocketlauncher = 1
			AND HAS_PICKUP_BEEN_COLLECTED s3_rocket
				REMOVE_BLIP s3_rocket_blip
				s3_player_picked_up_rocketlauncher = 1
			ENDIF
		ENDWHILE

		PLAY_MISSION_AUDIO 2

		SWITCH s3_get_in_audio_stage
			CASE 0
				PRINT_NOW ( TORX_AC ) 3000 1
				BREAK
			CASE 1
				PRINT_NOW ( TORX_AB ) 3000 1
				BREAK
			CASE 2
				PRINT_NOW ( TORX_AD ) 3000 1
				BREAK
			CASE 3
				PRINT_NOW ( TORX_AA ) 3000 1
				BREAK
			CASE 4
				PRINT_NOW ( TORX_AE ) 3000 1
				BREAK
			DEFAULT
				BREAK
		ENDSWITCH

		WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
			WAIT 0
			IF IS_CHAR_DEAD s3_toreno
			OR IS_CHAR_DEAD s3_tbone
			OR IS_CAR_DEAD s3_meet_car
			OR IS_CAR_DEAD s3_paste_van
			OR IS_CHAR_DEAD s3_paste_van_driver
			OR IS_CAR_DEAD s3_player_bike
				RETURN
			ENDIF
			IF NOT s3_player_picked_up_rocketlauncher = 1
			AND HAS_PICKUP_BEEN_COLLECTED s3_rocket
				REMOVE_BLIP s3_rocket_blip
				s3_player_picked_up_rocketlauncher = 1
			ENDIF
		ENDWHILE

		s3_get_in_audio_stage++
		IF s3_get_in_audio_stage > 4
			s3_get_in_audio_stage = 0
		ENDIF

	RETURN

// ******************************** Mission syn3 failed **********************************

mission_syn3_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN


// ******************************** Mission syn3 passed **********************************

mission_syn3_passed:

flag_synd_mission_counter++
REGISTER_MISSION_PASSED ( SYND_3 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 9000 5000 1 //"Mission Passed!"
ADD_SCORE player1 9000
AWARD_PLAYER_MISSION_RESPECT 25
SET_MAX_WANTED_LEVEL s3_wanted_level_at_start
ALTER_WANTED_LEVEL_NO_DROP player1 3
PLAY_MISSION_PASSED_TUNE 1
REMOVE_BLIP synd_contact_blip
RETURN
		

// *********************************** Mission cleanup *************************************

mission_cleanup_syn3:

MARK_MODEL_AS_NO_LONGER_NEEDED WASHING
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED RUMPO
MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
//MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG2
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
MARK_MODEL_AS_NO_LONGER_NEEDED bodyarmour
//MARK_MODEL_AS_NO_LONGER_NEEDED DOZER
REMOVE_PICKUP s3_rocket
REMOVE_PICKUP s3_sniper
REMOVE_PICKUP s3_armour_pickup
REMOVE_BLIP s3_meet_car_blip
REMOVE_BLIP s3_van_location_blip
REMOVE_BLIP s3_player_bike_blip
REMOVE_BLIP s3_rocket_blip
REMOVE_BLIP s3_paste_van_blip
//REMOVE_BLIP s3_destination_blip
REMOVE_BLIP s3_temp_first_roadblock_blip
GOSUB s3_remove_first_roadblock_blips
GOSUB s3_remove_second_roadblock_blips
GOSUB s3_remove_third_roadblock_blips
GOSUB s3_remove_fourth_roadblock_blips
CLEAR_SEQUENCE_TASK s3_paste_van_route_seq
IF IS_PLAYER_PLAYING player1
	SET_EVERYONE_IGNORE_PLAYER player1 OFF
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
ENDIF
UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2
REMOVE_CHAR_ELEGANTLY s3_toreno
REMOVE_CHAR_ELEGANTLY s3_tbone
REMOVE_ANIMATION CAR_CHAT
SWITCH_ROADS_BACK_TO_ORIGINAL -2005.23 517.16 33.0 -2002.69 554.12 36.0
SWITCH_ROADS_BACK_TO_ORIGINAL -2097.80 319.42 33.0 -2035.57 321.05 36.0
SWITCH_ROADS_BACK_TO_ORIGINAL -2150.18 138.75 33.0 -2145.71 160.84 36.0
SWITCH_ROADS_BACK_TO_ORIGINAL -2168.24 42.13  33.0 -2165.99 61.70 36.0
SWITCH_RANDOM_TRAINS ON
//CLEAR_HELP
REMOVE_DECISION_MAKER s3_roadblock_ped_decisions
REMOVE_DECISION_MAKER s3_in_car_decisions
IF NOT IS_CAR_DEAD s3_meet_car
	FREEZE_CAR_POSITION s3_meet_car FALSE
ENDIF
//REMOVE_DECISION_MAKER s3_escort_decisions
SET_MAX_WANTED_LEVEL s3_wanted_level_at_start
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


}