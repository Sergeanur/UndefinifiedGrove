MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* MUSIC 1 *******************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME music1		 

// Mission start stuff

GOSUB mission_start_music1												   

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_music1_failed
ENDIF

GOSUB mission_cleanup_music1

MISSION_END

{
 
// **************************************** Declare variables  *****************************

LVAR_FLOAT m1_beach_party_x m1_beach_party_y m1_beach_party_z

LVAR_INT m1_campfire1 m1_campfire2
LVAR_FLOAT m1_campfire1_x m1_campfire1_y m1_campfire1_z
LVAR_FLOAT m1_campfire2_x m1_campfire2_y m1_campfire2_z
LVAR_INT m1_campfire1wood m1_campfire2wood

LVAR_INT m1_sound_van
LVAR_FLOAT m1_sound_van_x m1_sound_van_y m1_sound_van_z m1_sound_van_heading
LVAR_FLOAT m1_destination_x m1_destination_y m1_destination_z
//LVAR_FLOAT m1_van_speed

LVAR_INT m1_DJ
LVAR_FLOAT m1_DJ_start_x m1_DJ_start_y
LVAR_FLOAT m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z

LVAR_INT m1_num_of_ambient_peds m1_ambient_peds[21]
LVAR_FLOAT m1_ambient_ped_start_x[21] m1_ambient_ped_start_y[21] m1_ambient_ped_start_heading[21]

LVAR_INT m1_num_of_perimeter_cars
LVAR_INT m1_perimeter_cars[7] m1_car_drivers[7] m1_perimeter_car_driver
LVAR_FLOAT m1_party_radius m1_four_times_party_radius m1_perimeter_car_angle
LVAR_FLOAT m1_perimeter_car_x m1_perimeter_car_y m1_perimeter_car_z m1_perimeter_car_heading
LVAR_INT m1_num_of_cars_chasing_player

//LVAR_INT m1_perimeter_quads[3]
//LVAR_FLOAT m1_perimeter_quad_x[3] m1_perimeter_quad_y[3] m1_perimeter_quad_heading[3]

LVAR_INT m1_index m1_ped_index m1_car_index

LVAR_INT m1_first_drink_seq m1_second_drink_seq

LVAR_INT m1_hours m1_minutes

LVAR_INT m1_DJ_leave_car_seq

LVAR_INT m1_task_status
LVAR_INT m1_seq_progress

LVAR_INT m1_party_decisions

LVAR_INT m1_DJ_play_seq m1_DJ_dance_seq m1_ambient_ped_anim_seq

// mission audio
LVAR_TEXT_LABEL m1_audio_text[1]
LVAR_INT m1_audio_sound[1]
LVAR_INT m1_audio_is_playing m1_audio_index m1_total_audio_to_play m1_started_talking
LVAR_INT m1_current_audio_needed
CONST_INT M1_STEAL_AUDIO 0
CONST_INT M1_DANCE_FAIL_AUDIO 1

// flags

LVAR_INT m1_fake_creates
LVAR_INT m1_beach_party_created m1_first_scripted_cutscene_played
LVAR_INT m1_talking_to_DJ m1_DJ_going_back_to_decks
LVAR_INT m1_dancing_for_DJ m1_playing_dancing_failed_audio
LVAR_INT m1_completed_dancing m1_talking_to_DJ_after_dancing m1_DJ_gone_to_van m1_DJ_unlocked_van
LVAR_INT m1_sound_van_captured m1_DJ_left_van m1_DJ_started_shouting m1_DJ_shouted
LVAR_INT m1_player_entered_sound_van m1_player_in_sound_van m1_cars_chasing_player
LVAR_INT m1_ambient_ped_attacking[21] m1_DJ_attacking
LVAR_INT m1_ped_chasing[21] m1_car_chasing[7]
LVAR_INT m1_driver_getting_in_to_car[7]
LVAR_INT m1_all_chars_and_cars_not_chasing_deleted
LVAR_INT m1_party_disrupted
LVAR_INT m1_everyone_stopped_from_chasing_player
LVAR_INT m1_script_fires_on

// blips

LVAR_INT m1_DJ_blip m1_sound_van_blip m1_destination_blip


// **************************************** Mission Start **********************************

mission_start_music1:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT STRAP1

flag_player_on_mission = 1

WAIT 0

// **************************************** Initialise variables  *****************************

//m1_beach_party_x =  2872.26
//m1_beach_party_y = -2157.87
//m1_beach_party_z =  1.76
m1_beach_party_x =  526.84
m1_beach_party_y = -1885.19
m1_beach_party_z =  2.4

m1_campfire1_x = m1_beach_party_x + 2.5
m1_campfire1_y = m1_beach_party_y + 3.5
m1_campfire1_z = 2.79

m1_campfire2_x = m1_beach_party_x - 7.0
m1_campfire2_y = m1_beach_party_y - 9.8
m1_campfire2_z = 2.06 

m1_sound_van_x = m1_campfire1_x + 9.0
m1_sound_van_y = m1_campfire1_y + 4.0
m1_sound_van_z = m1_campfire1_z
m1_sound_van_heading = 340.0

// outside garage door
m1_destination_x = 1643.42
m1_destination_y = -1524.69
m1_destination_z = 12.61

m1_DJ_start_x = m1_sound_van_x - 1.08
m1_DJ_start_y = m1_sound_van_y - 3.05

m1_next_to_DJ_x = m1_DJ_start_x - 0.54
m1_next_to_DJ_y = m1_DJ_start_y - 1.53
m1_next_to_DJ_z = 3.14

m1_num_of_ambient_peds = 21

// dancers
m1_ambient_ped_start_x[0] = m1_campfire1_x + 10.0
m1_ambient_ped_start_y[0] = m1_campfire1_y - 6.0
m1_ambient_ped_start_heading[0] = 300.0
m1_ambient_ped_start_x[1] = m1_ambient_ped_start_x[0] + 3.0
m1_ambient_ped_start_y[1] = m1_ambient_ped_start_y[0]
m1_ambient_ped_start_heading[1] = m1_ambient_ped_start_heading[0] + 120.0
m1_ambient_ped_start_x[2] = m1_ambient_ped_start_x[0] + 3.0
m1_ambient_ped_start_y[2] = m1_ambient_ped_start_y[0] + 3.0
m1_ambient_ped_start_heading[2] = m1_ambient_ped_start_heading[0] + 240.0

// idle chat
m1_ambient_ped_start_x[3] = m1_beach_party_x + 17.5
m1_ambient_ped_start_y[3] = m1_beach_party_y + 3.0
m1_ambient_ped_start_heading[3] = 270.0
m1_ambient_ped_start_x[4] = m1_ambient_ped_start_x[3] + 1.0
m1_ambient_ped_start_y[4] = m1_ambient_ped_start_y[3]
m1_ambient_ped_start_heading[4] = m1_ambient_ped_start_heading[3] - 180.0

// sitting down
m1_ambient_ped_start_x[5] = m1_campfire1_x - 2.0
m1_ambient_ped_start_y[5] = m1_campfire1_y - 8.0
m1_ambient_ped_start_heading[5] = 315.0
m1_ambient_ped_start_x[6] = m1_ambient_ped_start_x[5] + 2.5
m1_ambient_ped_start_y[6] = m1_ambient_ped_start_y[5]
m1_ambient_ped_start_heading[6] = m1_ambient_ped_start_heading[5] + 90.0
m1_ambient_ped_start_x[7] = m1_ambient_ped_start_x[5] + 2.5
m1_ambient_ped_start_y[7] = m1_ambient_ped_start_y[5] + 2.5
m1_ambient_ped_start_heading[7] = m1_ambient_ped_start_heading[5] + 180.0
m1_ambient_ped_start_x[8] = m1_ambient_ped_start_x[5]
m1_ambient_ped_start_y[8] = m1_ambient_ped_start_y[5] + 2.5
m1_ambient_ped_start_heading[8] = m1_ambient_ped_start_heading[5] + 270.0

m1_ambient_ped_start_x[9] = m1_beach_party_x + 12.0
m1_ambient_ped_start_y[9] = m1_beach_party_y - 9.5
m1_ambient_ped_start_heading[9] = 0.0
m1_ambient_ped_start_x[10] = m1_ambient_ped_start_x[9] - 0.5
m1_ambient_ped_start_y[10] = m1_ambient_ped_start_y[9] + 1.5
m1_ambient_ped_start_heading[10] = m1_ambient_ped_start_heading[9] + 180.0

// moonbathers
m1_ambient_ped_start_x[11] = m1_campfire2_x
m1_ambient_ped_start_y[11] = m1_campfire2_y + 5.0
m1_ambient_ped_start_heading[11] = 200.0
m1_ambient_ped_start_x[12] = m1_campfire2_x + 3.0
m1_ambient_ped_start_y[12] = m1_campfire2_y + 3.5
m1_ambient_ped_start_heading[12] = 170.0
m1_ambient_ped_start_x[13] = m1_campfire2_x + 4.0
m1_ambient_ped_start_y[13] = m1_campfire2_y
m1_ambient_ped_start_heading[13] = 0.0
m1_ambient_ped_start_x[14] = m1_campfire2_x + 5.0
m1_ambient_ped_start_y[14] = m1_campfire2_y + 2.0
m1_ambient_ped_start_heading[14] = 270.0

// more dancers
m1_ambient_ped_start_x[15] = m1_campfire1_x + 1.0
m1_ambient_ped_start_y[15] = m1_campfire1_y + 6.0
m1_ambient_ped_start_heading[15] = 270.0
m1_ambient_ped_start_x[16] = m1_ambient_ped_start_x[15] + 1.5
m1_ambient_ped_start_y[16] = m1_ambient_ped_start_y[15] + 1.5
m1_ambient_ped_start_heading[16] = m1_ambient_ped_start_heading[15] - 90.0
m1_ambient_ped_start_x[17] = m1_ambient_ped_start_x[15] + 3.0
m1_ambient_ped_start_y[17] = m1_ambient_ped_start_y[15]
m1_ambient_ped_start_heading[17] = m1_ambient_ped_start_heading[15] - 180.0
m1_ambient_ped_start_x[18] = m1_ambient_ped_start_x[15] + 1.5
m1_ambient_ped_start_y[18] = m1_ambient_ped_start_y[15] - 1.5
m1_ambient_ped_start_heading[18] = m1_ambient_ped_start_heading[15] - 270.0

// guys leaning on van
m1_ambient_ped_start_x[19] = m1_sound_van_x - 3.0
m1_ambient_ped_start_y[19] = m1_sound_van_y - 1.0
m1_ambient_ped_start_heading[19] = 0.0
m1_ambient_ped_start_x[20] = m1_ambient_ped_start_x[19]
m1_ambient_ped_start_y[20] = m1_ambient_ped_start_y[19] + 1.0
m1_ambient_ped_start_heading[20] = m1_ambient_ped_start_heading[19] + 180.0

m1_party_radius = 20.0
m1_four_times_party_radius = m1_party_radius * 4.0
m1_num_of_perimeter_cars = 7

//m1_perimeter_quad_x[0] = m1_beach_party_x + 2.5
//m1_perimeter_quad_y[0] = m1_beach_party_y - 11.0
//m1_perimeter_quad_x[1] = m1_beach_party_x + 5.5
//m1_perimeter_quad_y[1] = m1_beach_party_y - 12.0
//m1_perimeter_quad_x[2] = m1_beach_party_x + 3.0
//m1_perimeter_quad_y[2] = m1_beach_party_y - 15.0

GOSUB m1_calculate_new_positions

m1_audio_is_playing = 0
m1_audio_index = 0
m1_started_talking = 0

m1_fake_creates = 0
m1_beach_party_created = 0
m1_first_scripted_cutscene_played = 0
m1_talking_to_DJ = 0
m1_dancing_for_DJ = 0
m1_playing_dancing_failed_audio = 0
m1_completed_dancing = 0
m1_talking_to_DJ_after_dancing = 0
m1_DJ_gone_to_van = 0
m1_DJ_unlocked_van = 0
m1_sound_van_captured = 0
m1_DJ_left_van = 0
m1_player_entered_sound_van = 0
m1_player_in_sound_van = 0
m1_DJ_started_shouting = 0
m1_DJ_shouted = 0
m1_cars_chasing_player = 0
m1_all_chars_and_cars_not_chasing_deleted = 0

m1_party_disrupted = 0
m1_everyone_stopped_from_chasing_player = 0

m1_DJ_attacking = 0
m1_index = 0
WHILE m1_index < m1_num_of_ambient_peds
	m1_ambient_ped_attacking[m1_index] = 0
	m1_ped_chasing[m1_index] = 0
	IF m1_index < m1_num_of_perimeter_cars
		m1_car_chasing[m1_index] = 0
		m1_driver_getting_in_to_car[m1_index] = 0
	ENDIF
	m1_index++
ENDWHILE

// ****************************************START OF CUTSCENE********************************

SET_FADING_COLOUR 0 0 0

DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_AREA 809.5818 -1630.5734 12.5469 100.0 FALSE
LOAD_SCENE 809.5818 -1630.5734 12.5469

MAKE_PLAYER_GANG_DISAPPEAR

LOAD_CUTSCENE STRAP1A

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

MAKE_PLAYER_GANG_REAPPEAR

// ****************************************END OF CUTSCENE**********************************

REQUEST_MODEL VOODOO
REQUEST_MODEL ESPERANT
REQUEST_MODEL OCEANIC
//REQUEST_MODEL QUAD
REQUEST_MODEL PONY
REQUEST_MODEL MP5LNG
REQUEST_MODEL BFYST
REQUEST_MODEL WFYST
REQUEST_MODEL BMYST
REQUEST_MODEL WMYRO
REQUEST_MODEL WFYCLOT
REQUEST_MODEL WMYST
REQUEST_MODEL WMYCLOT
REQUEST_MODEL BMYDJ
REQUEST_MODEL WFYRI
REQUEST_MODEL OFYRI
REQUEST_VEHICLE_MOD wheel_or1
STREAM_SCRIPT Dance.sc
LOAD_ALL_MODELS_NOW
WHILE NOT HAS_MODEL_LOADED VOODOO
   OR NOT HAS_MODEL_LOADED ESPERANT
   OR NOT HAS_MODEL_LOADED OCEANIC
   OR NOT HAS_MODEL_LOADED PONY
   OR NOT HAS_MODEL_LOADED MP5LNG
   //OR NOT HAS_MODEL_LOADED QUAD
	WAIT 0
ENDWHILE
WHILE NOT HAS_MODEL_LOADED BFYST
	WAIT 0
ENDWHILE
WHILE NOT HAS_MODEL_LOADED WFYST
   OR NOT HAS_MODEL_LOADED BMYST
   OR NOT HAS_MODEL_LOADED WMYRO
   OR NOT HAS_MODEL_LOADED WFYCLOT
   OR NOT HAS_MODEL_LOADED WMYST
   OR NOT HAS_MODEL_LOADED WMYCLOT
	WAIT 0
ENDWHILE
WHILE NOT HAS_MODEL_LOADED BMYDJ 
   OR NOT HAS_MODEL_LOADED WFYRI 
   OR NOT HAS_MODEL_LOADED OFYRI 
	WAIT 0
ENDWHILE
WHILE NOT HAS_VEHICLE_MOD_LOADED wheel_or1
	OR NOT HAS_STREAMED_SCRIPT_LOADED Dance.sc
	WAIT 0
ENDWHILE

CHANGE_GARAGE_TYPE mul_lan GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE
// switch off ped nodes around beach party
SWITCH_PED_ROADS_OFF 502.15 -1913.39 -5.0 558.86 -1838.87 10.0

LOAD_SCENE 809.5818 -1630.5734 12.5469

FORCE_WEATHER_NOW WEATHER_SUNNY_LA

SET_CHAR_COORDINATES scplayer 809.5818 -1630.5734 12.5469
SET_CHAR_HEADING scplayer 176.0

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

// fades the screen in 

SET_FADING_COLOUR 0 0 0

WAIT 500

DO_FADE 1500 FADE_IN

//WAIT 1500

SET_PLAYER_CONTROL player1 ON

ADD_BLIP_FOR_COORD m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z m1_DJ_blip
PRINT_NOW ( MUS1_5 ) 10000 0

// CREATE statements to keep the compiler happy

IF m1_fake_creates = 1
	CREATE_CAR PONY 0.0 0.0 -100.0 m1_sound_van
	ADD_BLIP_FOR_CAR m1_sound_van m1_sound_van_blip
	CREATE_CHAR PEDTYPE_MISSION1 BFYST 0.0 0.0 -100.0 m1_DJ
	OPEN_SEQUENCE_TASK m1_DJ_play_seq
		TASK_STAND_STILL -1 -2
	CLOSE_SEQUENCE_TASK m1_DJ_play_seq
	m1_index = 0
	WHILE m1_index < m1_num_of_ambient_peds
		CREATE_CHAR PEDTYPE_MISSION1 BFYST 0.0 0.0 -100.0 m1_ambient_peds[m1_index]
		m1_index++
	ENDWHILE
	m1_index = 0
	WHILE m1_index < m1_num_of_perimeter_cars
		CREATE_CAR VOODOO 0.0 0.0 -100.0 m1_perimeter_cars[m1_index]
		m1_index++
	ENDWHILE
//	m1_index = 0
//	WHILE m1_index < 3
//		CREATE_CAR QUAD 0.0 0.0 -100.0 m1_perimeter_quads[m1_index]
//		m1_index++
//	ENDWHILE
ENDIF


// Mission loop

music1_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_music1_passed  
	ENDIF

	FORCE_WEATHER_NOW WEATHER_SUNNY_LA

	IF NOT m1_beach_party_created = 1

		IF LOCATE_CHAR_ANY_MEANS_2D scplayer m1_beach_party_x m1_beach_party_y 200.0 200.0 FALSE

			CLEAR_AREA m1_beach_party_x m1_beach_party_y m1_beach_party_z 100.0 FALSE
			GOSUB m1_create_beach_party
			LVAR_FLOAT m1_campfirelog_x m1_campfirelog_y
			m1_campfirelog_x = m1_campfire1_x + 0.15
			m1_campfirelog_y = m1_campfire1_y + 0.15
			CREATE_OBJECT_NO_OFFSET DYN_woodpile2 m1_campfirelog_x m1_campfirelog_y 2.6 m1_campfire1wood
			m1_campfirelog_x = m1_campfire2_x + 0.15
			m1_campfirelog_y = m1_campfire2_y + 0.15
			CREATE_OBJECT_NO_OFFSET DYN_woodpile2 m1_campfirelog_x m1_campfirelog_y 2.31 m1_campfire2wood
			SET_OBJECT_SCALE m1_campfire1wood 0.5
			SET_OBJECT_SCALE m1_campfire2wood 0.5
			SET_OBJECT_COLLISION_DAMAGE_EFFECT m1_campfire1wood FALSE
			SET_OBJECT_COLLISION_DAMAGE_EFFECT m1_campfire2wood FALSE

			SWITCH_AUDIO_ZONE BEACH TRUE

			WAIT 1500

			GOSUB m1_animate_ambient_peds
			IF NOT IS_CHAR_DEAD m1_DJ
				FREEZE_CHAR_POSITION m1_DJ TRUE
			ENDIF
			IF NOT IS_CAR_DEAD m1_sound_van
				FREEZE_CAR_POSITION m1_sound_van TRUE
			ENDIF
			IF DOES_OBJECT_EXIST m1_campfire1wood
				FREEZE_OBJECT_POSITION m1_campfire1wood TRUE
			ENDIF
			IF DOES_OBJECT_EXIST m1_campfire2wood
				FREEZE_OBJECT_POSITION m1_campfire2wood TRUE
			ENDIF

//			START_SCRIPT_FIRE m1_campfire1_x m1_campfire1_y m1_campfire1_z 0 1 m1_campfire1
//			START_SCRIPT_FIRE m1_campfire2_x m1_campfire2_y m1_campfire2_z 0 1 m1_campfire2
			CREATE_FX_SYSTEM fire m1_campfire1_x m1_campfire1_y m1_campfire1_z TRUE m1_campfire1
			CREATE_FX_SYSTEM fire m1_campfire2_x m1_campfire2_y m1_campfire2_z TRUE m1_campfire2
			PLAY_FX_SYSTEM m1_campfire1
			PLAY_FX_SYSTEM m1_campfire2
			m1_script_fires_on = 1

			m1_beach_party_created = 1

		ENDIF

	ENDIF

	IF m1_beach_party_created = 1

		// campfires on at night and put out in the daytime
		GET_TIME_OF_DAY m1_hours m1_minutes
		IF m1_hours >= 6
		AND m1_hours < 22
			IF m1_script_fires_on = 1
//				REMOVE_ALL_SCRIPT_FIRES
				STOP_FX_SYSTEM m1_campfire1
				STOP_FX_SYSTEM m1_campfire2
				m1_script_fires_on = 0
			ENDIF
		ELSE
			IF m1_script_fires_on = 0
//				START_SCRIPT_FIRE m1_campfire1_x m1_campfire1_y m1_campfire1_z 0 1 m1_campfire1
//				START_SCRIPT_FIRE m1_campfire2_x m1_campfire2_y m1_campfire2_z 0 1 m1_campfire2
				PLAY_FX_SYSTEM m1_campfire1
				PLAY_FX_SYSTEM m1_campfire2
				m1_script_fires_on = 1
			ENDIF
		ENDIF

		IF NOT m1_first_scripted_cutscene_played = 1
		AND LOCATE_CHAR_ANY_MEANS_2D scplayer m1_beach_party_x m1_beach_party_y 50.0 50.0 FALSE

			GOSUB m1_play_first_scripted_cutscene
			m1_first_scripted_cutscene_played = 1

		ENDIF

		IF m1_sound_van_captured = 0

			IF NOT m1_talking_to_DJ = 1
			AND NOT m1_dancing_for_DJ = 1
			AND NOT m1_completed_dancing = 1
				IF NOT IS_CHAR_DEAD m1_DJ
				AND LOCATE_CHAR_ON_FOOT_3D scplayer m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z 1.2 1.2 2.0 TRUE

					REMOVE_BLIP m1_DJ_blip
					//TASK_TURN_CHAR_TO_FACE_CHAR m1_DJ scplayer
					CLEAR_CHAR_TASKS m1_DJ
					SET_CHAR_HEADING m1_DJ 110.0

					GOSUB m1_init_first_conversation

					PRINT_HELP ( TALK_1 )

					m1_talking_to_DJ = 1
					m1_DJ_going_back_to_decks = 0
					
					SET_PLAYER_CONTROL player1 OFF
					SET_CHAR_COORDINATES scplayer m1_next_to_DJ_x m1_next_to_DJ_y 2.3
					SET_CHAR_HEADING scplayer 290.0
					SET_CAMERA_POSITION_UNFIXED 0.031 -2.152
					//SET_CAMERA_POSITION_UNFIXED 1.778 -123.36
					WAIT 1000
					SET_PLAYER_CONTROL player1 ON

				ENDIF
			ENDIF

			IF m1_talking_to_DJ = 1
			AND NOT m1_dancing_for_DJ = 1
				IF NOT IS_CHAR_DEAD m1_DJ

					IF IS_CONVERSATION_AT_NODE m1_DJ $m1_dstar_node

						SET_PLAYER_CONTROL player1 OFF
						
						IF NOT HAS_STREAMED_SCRIPT_LOADED Dance.sc
							STREAM_SCRIPT Dance.sc
						ENDIF
						
						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
						OR NOT HAS_STREAMED_SCRIPT_LOADED Dance.sc
							WAIT 0
						ENDWHILE

						CLEAR_HELP

						IF IS_CHAR_IN_ANY_CAR scplayer
							WARP_CHAR_FROM_CAR_TO_COORD scplayer 533.81 -1891.64 -100.0
						ELSE
							SET_CHAR_COORDINATES scplayer 533.81 -1891.64 -100.0
						ENDIF
						//SET_CHAR_HEADING scplayer m1_sound_van_heading
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
						CLEAR_AREA 533.81 -1891.64 2.27 10.0 FALSE
						// clear dancing area of mission quads and cars
//						m1_index = 0
//						WHILE m1_index < 3
//							IF NOT IS_CAR_DEAD m1_perimeter_quads[m1_index]
//								IF LOCATE_CAR_3D m1_perimeter_quads[m1_index] 533.81 -1891.64 2.27 10.0 10.0 10.0 FALSE
//									DELETE_CAR m1_perimeter_quads[m1_index]
//								ENDIF
//							ELSE
//								DELETE_CAR m1_perimeter_quads[m1_index]
//							ENDIF
//							m1_index++
//						ENDWHILE
						m1_index = 0
						WHILE m1_index < m1_num_of_perimeter_cars
							IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_index]
								IF LOCATE_CAR_3D m1_perimeter_cars[m1_index] 533.81 -1891.64 2.27 10.0 10.0 10.0 FALSE
									DELETE_CAR m1_perimeter_cars[m1_index]
								ENDIF
							ELSE
								DELETE_CAR m1_perimeter_cars[m1_index]
							ENDIF
							m1_index++
						ENDWHILE

						IF NOT IS_CHAR_DEAD m1_DJ
							CLEAR_CONVERSATION_FOR_CHAR m1_DJ
							CLEAR_CHAR_TASKS_IMMEDIATELY m1_DJ
							SET_CHAR_HEADING m1_DJ 110.0
							OPEN_SEQUENCE_TASK m1_DJ_dance_seq
								TASK_PLAY_ANIM -1 DAN_Loop_A DANCING 4.0 TRUE FALSE FALSE FALSE -1
								SET_SEQUENCE_TO_REPEAT m1_DJ_dance_seq 1
							CLOSE_SEQUENCE_TASK m1_DJ_dance_seq
							PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_dance_seq
							CLEAR_SEQUENCE_TASK m1_DJ_dance_seq
						ENDIF

						SWITCH_AUDIO_ZONE BEACH FALSE

						// stop peds chatting during dancing
						IF NOT IS_CHAR_DEAD m1_ambient_peds[3]
							CLEAR_CHAR_TASKS_IMMEDIATELY m1_ambient_peds[3]
						ENDIF
						IF NOT IS_CHAR_DEAD m1_ambient_peds[4]
							CLEAR_CHAR_TASKS_IMMEDIATELY m1_ambient_peds[4]
						ENDIF

						CLEAR_PRINTS
						PRINT_NOW ( MUS1_4 ) 10000 0

						m1_index = 0
						WHILE m1_index < 5
//							IF m1_index = 1
//							OR m1_index = 2
								fDanceCameraX[m1_index] = 531.18
								fDanceCameraY[m1_index] = -1893.8
								fDanceCameraZ[m1_index] = 3.45
								fDanceTargetX[m1_index] = 533.94
								fDanceTargetY[m1_index] = -1891.82
								fDanceTargetZ[m1_index] = 3.44
//							ELSE
//								fDanceCameraX[m1_index] = 
//								fDanceCameraY[m1_index] = 
//								fDanceCameraZ[m1_index] = 
//								fDanceTargetX[m1_index] = 
//								fDanceTargetY[m1_index] = 
//								fDanceTargetZ[m1_index] = 
//							ENDIF
							m1_index++
						ENDWHILE

						SET_BIT iDanceReport DANCE_MINIGAME_RUNNING
						START_NEW_STREAMED_SCRIPT Dance.sc 533.81 -1891.64 -100.0 m1_sound_van_heading DANCE_TRACK_GFUNK DANCE_MISSION_NO_PARTNER

//						SET_CAMERA_BEHIND_PLAYER
//						RESTORE_CAMERA_JUMPCUT
//
//						DO_FADE 1000 FADE_IN
//						WHILE GET_FADING_STATUS
//							WAIT 0
//						ENDWHILE

						m1_dancing_for_DJ = 1

					ELSE

						IF NOT m1_DJ_going_back_to_decks = 1
							IF IS_CONVERSATION_AT_NODE m1_DJ $m1_bexit_node
							OR IS_CONVERSATION_AT_NODE m1_DJ $m1_gexit_node
								m1_DJ_going_back_to_decks = 1
							ELSE
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer m1_DJ 5.0 5.0 FALSE
									TIMERB = 0
								ELSE
									IF TIMERB > 3000
										// if player goes 5m away from the DJ for more than 3s,
										// conversation is reset
										CLEAR_CONVERSATION_FOR_CHAR m1_DJ
										CLEAR_HELP
										SET_CHAR_COORDINATES m1_DJ m1_DJ_start_x m1_DJ_start_y -100.0
										SET_CHAR_HEADING m1_DJ m1_sound_van_heading
										PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_play_seq
										m1_talking_to_DJ = 0
										m1_DJ_going_back_to_decks = 0
										ADD_BLIP_FOR_COORD m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z m1_DJ_blip
									ENDIF
								ENDIF
							ENDIF
						ELSE
							// if conversation has finished and player leaves DJ locate,
							// conversation is reset
							IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z 1.2 1.2 2.0 FALSE
								m1_DJ_going_back_to_decks = 0
								CLEAR_CONVERSATION_FOR_CHAR m1_DJ
								CLEAR_HELP
								SET_CHAR_COORDINATES m1_DJ m1_DJ_start_x m1_DJ_start_y -100.0
								SET_CHAR_HEADING m1_DJ m1_sound_van_heading
								PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_play_seq
								m1_talking_to_DJ = 0
								ADD_BLIP_FOR_COORD m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z m1_DJ_blip
							ENDIF
						ENDIF

					ENDIF

				ENDIF
			ENDIF

			IF m1_dancing_for_DJ = 1
			AND NOT m1_completed_dancing = 1

				IF NOT IS_BIT_SET iDanceReport DANCE_MINIGAME_RUNNING
				AND NOT m1_playing_dancing_failed_audio = 1

					SET_PLAYER_CONTROL player1 ON

					IF NOT IS_CHAR_DEAD m1_DJ
						CLEAR_CHAR_TASKS_IMMEDIATELY m1_DJ
					ENDIF

					CLEAR_PRINTS

					IF iDanceScore >= 2500

						SWITCH_AUDIO_ZONE BEACH TRUE

						GOSUB m1_init_second_conversation

						PRINT_HELP ( TALK_1 )

						m1_completed_dancing = 1
						m1_talking_to_DJ_after_dancing = 1
						m1_DJ_going_back_to_decks = 0

					ELSE

						m1_current_audio_needed = M1_DANCE_FAIL_AUDIO
						GOSUB m1_setup_audio_data
						TIMERA = 0
						m1_playing_dancing_failed_audio = 1

					ENDIF

				ENDIF

				IF m1_playing_dancing_failed_audio = 1

					IF m1_audio_index < m1_total_audio_to_play
					AND m1_current_audio_needed = M1_DANCE_FAIL_AUDIO
						IF NOT IS_CHAR_DEAD m1_DJ
							GOSUB m1_load_and_play_audio
						ENDIF
					ELSE
						IF TIMERA > 1000
							PRINT_NOW ( MUS1_8 ) 5000 0
							GOTO mission_music1_failed
						ENDIF
					ENDIF

				ENDIF

			ENDIF

			IF m1_completed_dancing = 1
			AND NOT m1_DJ_gone_to_van = 1
				IF NOT IS_CHAR_DEAD m1_DJ

					IF NOT m1_talking_to_DJ_after_dancing = 1

						IF LOCATE_CHAR_ON_FOOT_3D scplayer m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z 1.2 1.2 2.0 TRUE

							REMOVE_BLIP m1_DJ_blip
							CLEAR_CHAR_TASKS m1_DJ
							SET_CHAR_HEADING m1_DJ 110.0

							GOSUB m1_init_second_conversation

							PRINT_HELP ( TALK_1 )

							m1_talking_to_DJ_after_dancing = 1
							m1_DJ_going_back_to_decks = 0

						ENDIF

					ELSE

						IF IS_CONVERSATION_AT_NODE m1_DJ VYES1
						OR IS_CONVERSATION_AT_NODE m1_DJ VYES2

							IF NOT IS_CAR_DEAD m1_sound_van
								CLEAR_CONVERSATION_FOR_CHAR m1_DJ
								CLEAR_HELP
								SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
								FREEZE_CHAR_POSITION m1_DJ FALSE
								TASK_ENTER_CAR_AS_PASSENGER m1_DJ m1_sound_van -1 0
								ADD_BLIP_FOR_CAR m1_sound_van m1_sound_van_blip
								SET_BLIP_AS_FRIENDLY m1_sound_van_blip TRUE
								m1_talking_to_DJ_after_dancing = 0
								m1_DJ_gone_to_van = 1
								PRINT ( MUS1_7 ) 10000 0
							ENDIF

						ELSE

							IF NOT m1_DJ_going_back_to_decks = 1
 								IF IS_CONVERSATION_AT_NODE m1_DJ VLATE
									m1_DJ_going_back_to_decks = 1
								ELSE
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer m1_DJ 5.0 5.0 FALSE
										TIMERB = 0
									ELSE
										IF TIMERB > 3000
											// if player goes 5m away from the DJ for more than 3s,
											// conversation is reset
											CLEAR_CONVERSATION_FOR_CHAR m1_DJ
											CLEAR_HELP
											SET_CHAR_COORDINATES m1_DJ m1_DJ_start_x m1_DJ_start_y -100.0
											SET_CHAR_HEADING m1_DJ m1_sound_van_heading
											PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_play_seq
											m1_talking_to_DJ_after_dancing = 0
											m1_DJ_going_back_to_decks = 0
											ADD_BLIP_FOR_COORD m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z m1_DJ_blip
										ENDIF
									ENDIF
								ENDIF
							ELSE
								// if conversation has finished and player leaves DJ locate,
								// conversation is reset
								IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z 1.2 1.2 2.0 FALSE
									m1_DJ_going_back_to_decks = 0
									CLEAR_CONVERSATION_FOR_CHAR m1_DJ
									CLEAR_HELP
									SET_CHAR_COORDINATES m1_DJ m1_DJ_start_x m1_DJ_start_y -100.0
									SET_CHAR_HEADING m1_DJ m1_sound_van_heading
									PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_play_seq
									m1_talking_to_DJ_after_dancing = 0
									ADD_BLIP_FOR_COORD m1_next_to_DJ_x m1_next_to_DJ_y m1_next_to_DJ_z m1_DJ_blip
								ENDIF
							ENDIF

						ENDIF

					ENDIF

				ENDIF
			ENDIF

			IF m1_DJ_gone_to_van = 1
			AND NOT m1_DJ_unlocked_van = 1
			AND NOT IS_CHAR_DEAD m1_DJ
			AND NOT IS_CAR_DEAD m1_sound_van

				IF IS_CHAR_IN_CAR m1_DJ m1_sound_van
					LOCK_CAR_DOORS m1_sound_van CARLOCK_UNLOCKED
					CONTROL_CAR_DOOR m1_sound_van REAR_LEFT_DOOR DT_DOOR_SWINGING_FREE -1.0
					CONTROL_CAR_DOOR m1_sound_van REAR_RIGHT_DOOR DT_DOOR_SWINGING_FREE -1.0
					SET_CAR_CAN_BE_VISIBLY_DAMAGED m1_sound_van TRUE
					m1_DJ_unlocked_van = 1
				ENDIF

			ENDIF

			// if player attacks the DJ, fires a shot near the party or any member of the party is killed,
			// guards attack and all other members flee
			IF NOT m1_party_disrupted = 1

				IF LOCATE_CHAR_ANY_MEANS_2D scplayer m1_beach_party_x m1_beach_party_y m1_party_radius m1_party_radius FALSE
				AND IS_CHAR_SHOOTING scplayer
					m1_party_disrupted = 1
				ELSE
					IF IS_CHAR_DEAD m1_DJ
						m1_party_disrupted = 1
					ELSE
						IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR m1_DJ scplayer
						OR IS_CHAR_RESPONDING_TO_EVENT m1_DJ EVENT_GUN_AIMED_AT
							m1_party_disrupted = 1
						ENDIF
					ENDIF
					m1_index = 0
					WHILE m1_index < m1_num_of_ambient_peds
					AND NOT m1_party_disrupted = 1
						IF IS_CHAR_DEAD m1_ambient_peds[m1_index]
							m1_party_disrupted = 1
						ELSE
							IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR m1_ambient_peds[m1_index] scplayer
								m1_party_disrupted = 1
							ENDIF
						ENDIF
						m1_index++
					ENDWHILE
				ENDIF

				IF m1_party_disrupted = 1
					CLEAR_MISSION_AUDIO 1
					CLEAR_PRINTS
					IF NOT m1_DJ_unlocked_van = 1
						GOSUB m1_set_party_guards_attacking
						GOSUB m1_set_ped_reactions_to_fight
					ELSE
						IF NOT m1_cars_chasing_player = 1
			 				GOSUB m1_set_cars_chasing_player
							m1_cars_chasing_player = 1
						ENDIF
						IF NOT IS_CHAR_DEAD m1_DJ
						AND NOT m1_DJ_attacking = 1
							SET_CURRENT_CHAR_WEAPON m1_DJ WEAPONTYPE_MP5
				  			TASK_KILL_CHAR_ON_FOOT m1_DJ scplayer
				   			m1_DJ_attacking = 1
							SET_CHAR_KEEP_TASK m1_DJ TRUE
						ENDIF
					ENDIF
					SWITCH_AUDIO_ZONE BEACH FALSE
				ELSE
					// only keep peds chatting when player isn't dancing
					IF NOT m1_dancing_for_DJ = 1
					OR m1_completed_dancing = 1
						GOSUB m1_keep_chatting_peds_talking
					ENDIF
				ENDIF

			ENDIF

			IF m1_DJ_unlocked_van = 1
			AND m1_party_disrupted = 1
				IF m1_cars_chasing_player = 1
				AND NOT m1_everyone_stopped_from_chasing_player = 1
					GOSUB m1_keep_drivers_in_cars
				ENDIF
			ENDIF

			// player entered sound van
			IF NOT m1_player_entered_sound_van = 1
			AND NOT IS_CAR_DEAD m1_sound_van
				IF IS_CHAR_IN_CAR scplayer m1_sound_van

					REMOVE_BLIP m1_sound_van_blip
					m1_player_entered_sound_van = 1
					m1_player_in_sound_van = 1
					FREEZE_CAR_POSITION m1_sound_van FALSE
					SET_CAMERA_BEHIND_PLAYER
					PRINT_NOW ( MUS1_19 ) 7000 0
					ADD_BLIP_FOR_COORD m1_destination_x m1_destination_y m1_destination_z m1_destination_blip

				ENDIF
			ENDIF

			IF m1_player_entered_sound_van = 1

				GOSUB m1_current_blip_check

				// player captured sound van
				IF NOT IS_CAR_DEAD m1_sound_van
					IF NOT LOCATE_CAR_2D m1_sound_van m1_beach_party_x m1_beach_party_y m1_party_radius m1_party_radius FALSE

						SET_CAR_ONLY_DAMAGED_BY_PLAYER m1_sound_van FALSE
						m1_sound_van_captured = 1

						SWITCH_AUDIO_ZONE BEACH FALSE

					ENDIF
				ENDIF

			ENDIF

		ENDIF

		IF m1_sound_van_captured = 1

			IF NOT m1_DJ_left_van = 1
			AND NOT IS_CHAR_DEAD m1_DJ
			AND NOT IS_CAR_DEAD m1_sound_van
				OPEN_SEQUENCE_TASK m1_DJ_leave_car_seq
					TASK_LEAVE_CAR_IMMEDIATELY -1 m1_sound_van
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m1_DJ_leave_car_seq
				PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_leave_car_seq
				CLEAR_SEQUENCE_TASK m1_DJ_leave_car_seq
				m1_DJ_left_van = 1
			ENDIF

			IF m1_DJ_left_van = 1
			AND NOT IS_CHAR_DEAD m1_DJ
			AND NOT m1_DJ_started_shouting = 1
				GET_SCRIPT_TASK_STATUS m1_DJ PERFORM_SEQUENCE_TASK m1_task_status
				IF NOT m1_task_status = FINISHED_TASK
					GET_SEQUENCE_PROGRESS m1_DJ m1_seq_progress
					IF m1_seq_progress >= 0
						m1_current_audio_needed = M1_STEAL_AUDIO
						GOSUB m1_setup_audio_data
						SET_CURRENT_CHAR_WEAPON m1_DJ WEAPONTYPE_MP5
						m1_DJ_started_shouting = 1
					ENDIF
				ENDIF
			ENDIF

			IF m1_DJ_started_shouting = 1
			AND NOT m1_DJ_shouted = 1

				IF m1_audio_index < m1_total_audio_to_play
				AND m1_current_audio_needed = M1_STEAL_AUDIO
					IF NOT IS_CHAR_DEAD m1_DJ
						GOSUB m1_load_and_play_audio
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_PRINTS
						m1_DJ_shouted = 1
					ENDIF
				ELSE
					m1_DJ_shouted = 1
				ENDIF

			ENDIF

			// send peds to chase player
			IF NOT m1_cars_chasing_player = 1
 				GOSUB m1_set_cars_chasing_player
				m1_cars_chasing_player = 1
			ENDIF

			IF NOT m1_everyone_stopped_from_chasing_player = 1
				GOSUB m1_keep_drivers_in_cars
			ENDIF

			// once cars chasing and player is outside 4*party_radius,
			// remove all unused ambient peds (improved performance)
			IF m1_cars_chasing_player = 1
			AND NOT m1_all_chars_and_cars_not_chasing_deleted = 1
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer m1_beach_party_x m1_beach_party_y m1_four_times_party_radius m1_four_times_party_radius FALSE

					GOSUB m1_delete_all_chars_and_cars_not_chasing
					m1_all_chars_and_cars_not_chasing_deleted = 1

				ENDIF
			ENDIF

			// stop everyone from chasing player if near destination
			IF m1_cars_chasing_player = 1
			AND NOT IS_CAR_DEAD m1_sound_van
			AND NOT m1_everyone_stopped_from_chasing_player = 1
				IF IS_CHAR_IN_CAR scplayer m1_sound_van
				AND LOCATE_CHAR_IN_CAR_2D scplayer m1_destination_x m1_destination_y 50.0 50.0 FALSE

					GOSUB m1_stop_everyone_from_chasing_player
					m1_everyone_stopped_from_chasing_player = 1

				ENDIF
			ENDIF
			
			GOSUB m1_current_blip_check

		ENDIF

		// mission pass/fail conditions
		IF NOT m1_DJ_unlocked_van = 1
			IF IS_CHAR_DEAD m1_DJ
				CLEAR_PRINTS
				PRINT_NOW ( MUS1_18 ) 5000 0
				GOTO mission_music1_failed
			ELSE
				IF m1_party_disrupted = 1
					CLEAR_PRINTS
					PRINT_NOW ( MUS1_21 ) 5000 0
					GOTO mission_music1_failed
				ENDIF
			ENDIF
		ENDIF

		IF IS_CAR_DEAD m1_sound_van
			CLEAR_PRINTS
			PRINT_NOW ( MUS1_20 ) 5000 0
			GOTO mission_music1_failed
		ELSE
			IF m1_player_entered_sound_van = 1
				IF IS_CHAR_IN_CAR scplayer m1_sound_van
					IF LOCATE_CAR_3D m1_sound_van m1_destination_x m1_destination_y m1_destination_z 4.0 4.0 2.0 TRUE
						SET_PLAYER_CONTROL player1 OFF
						GOSUB m1_play_mission_passed_cutscene
						GOTO mission_music1_passed
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

GOTO music1_loop


// ********************************** Mission GOSUBS ************************************

// ************************************************************
// 		Create beach party with circle of parked cars,
//		four quads, sound van, beach party peds and DJ
// ************************************************************

	m1_create_beach_party:

		// create ring of parked cars around the beach party
		m1_index = 0
		m1_perimeter_car_angle = -30.0
		WHILE m1_index < m1_num_of_perimeter_cars

			COS m1_perimeter_car_angle m1_perimeter_car_x
			SIN m1_perimeter_car_angle m1_perimeter_car_y
			m1_perimeter_car_x *= m1_party_radius
			m1_perimeter_car_y *= m1_party_radius
			m1_perimeter_car_x += m1_beach_party_x
			m1_perimeter_car_y += m1_beach_party_y

			IF m1_index = 0
			OR m1_index = 3
			OR m1_index = 6
				CREATE_CAR VOODOO m1_perimeter_car_x m1_perimeter_car_y -100.0 m1_perimeter_cars[m1_index]
			ELSE
				IF m1_index = 1
				OR m1_index = 7
					CREATE_CAR OCEANIC m1_perimeter_car_x m1_perimeter_car_y -100.0 m1_perimeter_cars[m1_index]
				ELSE
					CREATE_CAR ESPERANT m1_perimeter_car_x m1_perimeter_car_y -100.0 m1_perimeter_cars[m1_index]
				ENDIF
			ENDIF
			m1_perimeter_car_heading = 90.0 + m1_perimeter_car_angle
			SET_CAR_HEADING m1_perimeter_cars[m1_index] m1_perimeter_car_heading
			//FORCE_CAR_LIGHTS m1_perimeter_cars[m1_index] FORCE_CAR_LIGHTS_ON
			//SET_CAR_ENGINE_ON m1_perimeter_cars[m1_index] TRUE
			LOCK_CAR_DOORS m1_perimeter_cars[m1_index] CARLOCK_LOCKED

			m1_perimeter_car_angle += 37.0
			m1_index++

		ENDWHILE

//		// create quads
//		m1_index = 0
//		WHILE m1_index < 3
//			CREATE_CAR QUAD m1_perimeter_quad_x[m1_index] m1_perimeter_quad_y[m1_index] -100.0 m1_perimeter_quads[m1_index]
//			SET_CAR_HEADING m1_perimeter_quads[m1_index] m1_perimeter_quad_heading[m1_index]
//			m1_index++
//		ENDWHILE

		// create sound van
		SET_CAR_MODEL_COMPONENTS PONY 0 0
		CREATE_CAR PONY m1_sound_van_x m1_sound_van_y m1_sound_van_z m1_sound_van
		LVAR_INT m1_dummy_wheelmod
		ADD_VEHICLE_MOD m1_sound_van wheel_or1 m1_dummy_wheelmod
		SET_CAR_HEADING m1_sound_van m1_sound_van_heading
	   	CAR_SET_IDLE m1_sound_van
		SET_CAR_ONLY_DAMAGED_BY_PLAYER m1_sound_van TRUE	// don't let enemies damage sound van until player captures it
		SET_CAR_HEALTH m1_sound_van 5000
		CHANGE_CAR_COLOUR m1_sound_van CARCOLOUR_MIDNIGHTBLUE CARCOLOUR_MIDNIGHTBLUE
		SET_CAN_BURST_CAR_TYRES m1_sound_van FALSE
		SET_CAR_TRACTION m1_sound_van 2.0
		OPEN_CAR_DOOR m1_sound_van REAR_LEFT_DOOR
		OPEN_CAR_DOOR m1_sound_van REAR_RIGHT_DOOR
		LOCK_CAR_DOORS m1_sound_van CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_CAN_BE_VISIBLY_DAMAGED m1_sound_van FALSE

		// wait for sound van to land so that DJ can be created near the van
		WAIT 1000

		// create beach party peds and DJ
		CREATE_CHAR PEDTYPE_MISSION1 BFYST m1_DJ_start_x m1_DJ_start_y -100.0 m1_DJ
		SET_CHAR_HEADING m1_DJ m1_sound_van_heading
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER m1_DJ TRUE
		GIVE_WEAPON_TO_CHAR m1_DJ WEAPONTYPE_MP5 99999
		SET_CURRENT_CHAR_WEAPON m1_DJ WEAPONTYPE_UNARMED
		m1_DJ_attacking = 0

		COPY_CHAR_DECISION_MAKER DM_PED_EMPTY m1_party_decisions
		ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE m1_party_decisions EVENT_GUN_AIMED_AT TASK_SIMPLE_HANDS_UP 100.0 100.0 100.0 100.0 FALSE TRUE
		SET_CHAR_DECISION_MAKER m1_DJ m1_party_decisions

		m1_index = 0
		WHILE m1_index < m1_num_of_ambient_peds
			IF m1_index = 0
			OR m1_index = 4
			OR m1_index = 7
			OR m1_index = 11
				CREATE_CHAR PEDTYPE_MISSION1 BMYST m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
			ELSE
				IF m1_index = 6
				OR m1_index = 3
				OR m1_index = 2
					CREATE_CHAR PEDTYPE_MISSION1 WMYST m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
				ELSE
					IF m1_index = 9
					OR m1_index = 14
					OR m1_index = 17
					OR m1_index = 19
						CREATE_CHAR PEDTYPE_MISSION1 WFYCLOT m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
					ELSE
						IF m1_index = 12
							CREATE_CHAR PEDTYPE_MISSION1 WMYRO m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
						ELSE
							IF m1_index = 16
							OR m1_index = 10
								CREATE_CHAR PEDTYPE_MISSION1 WFYST m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
							ELSE
								IF m1_index = 5
								OR m1_index = 15
									CREATE_CHAR PEDTYPE_MISSION1 WMYCLOT m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
								ELSE
									IF m1_index = 8
									OR m1_index = 13
										CREATE_CHAR PEDTYPE_MISSION1 BMYDJ m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
									ELSE
										IF m1_index = 1
										OR m1_index = 20
											CREATE_CHAR PEDTYPE_MISSION1 OFYRI m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
										ELSE
											CREATE_CHAR PEDTYPE_MISSION1 WFYRI m1_ambient_ped_start_x[m1_index] m1_ambient_ped_start_y[m1_index] -100.0 m1_ambient_peds[m1_index]
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			SET_CHAR_HEADING m1_ambient_peds[m1_index] m1_ambient_ped_start_heading[m1_index]
			GIVE_WEAPON_TO_CHAR m1_ambient_peds[m1_index] WEAPONTYPE_MP5 99999
			SET_CURRENT_CHAR_WEAPON m1_ambient_peds[m1_index] WEAPONTYPE_UNARMED
			SET_CHAR_DECISION_MAKER m1_ambient_peds[m1_index] m1_party_decisions
			m1_ambient_ped_attacking[m1_index] = 0
			m1_index++
		ENDWHILE

//		MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
		MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
		MARK_MODEL_AS_NO_LONGER_NEEDED ESPERANT
		MARK_MODEL_AS_NO_LONGER_NEEDED OCEANIC
		MARK_MODEL_AS_NO_LONGER_NEEDED WFYST
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
		MARK_MODEL_AS_NO_LONGER_NEEDED WMYRO
		MARK_MODEL_AS_NO_LONGER_NEEDED WFYCLOT
		MARK_MODEL_AS_NO_LONGER_NEEDED WMYST
		MARK_MODEL_AS_NO_LONGER_NEEDED WMYCLOT
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ
		MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
		MARK_MODEL_AS_NO_LONGER_NEEDED OFYRI
		MARK_VEHICLE_MOD_AS_NO_LONGER_NEEDED wheel_or1

	RETURN

// ************************************************************
// 			     Animate ambient peds
// ************************************************************

	m1_animate_ambient_peds:

		REQUEST_ANIMATION BAR
		REQUEST_ANIMATION DANCING
		REQUEST_ANIMATION BEACH
		REQUEST_ANIMATION SCRATCHING
		WHILE NOT HAS_ANIMATION_LOADED BAR
		   OR NOT HAS_ANIMATION_LOADED DANCING
		   OR NOT HAS_ANIMATION_LOADED BEACH
		   OR NOT HAS_ANIMATION_LOADED SCRATCHING
			WAIT 0
		ENDWHILE

		OPEN_SEQUENCE_TASK m1_DJ_play_seq
			TASK_PLAY_ANIM -1 SCLNG_R SCRATCHING 4.0 TRUE FALSE FALSE FALSE -1
			SET_SEQUENCE_TO_REPEAT m1_DJ_play_seq 1
		CLOSE_SEQUENCE_TASK m1_DJ_play_seq
		IF NOT IS_CHAR_DEAD m1_DJ
			PERFORM_SEQUENCE_TASK m1_DJ m1_DJ_play_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[0]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_A DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[0] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[1]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_B DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[1] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[2]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_D DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[2] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[3]
		AND NOT IS_CHAR_DEAD m1_ambient_peds[4]
			TASK_CHAT_WITH_CHAR m1_ambient_peds[3] m1_ambient_peds[4] TRUE TRUE
			TASK_CHAT_WITH_CHAR m1_ambient_peds[4] m1_ambient_peds[3] FALSE TRUE
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[5]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 Lay_Bac_Loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[5] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[6]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 ParkSit_M_loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[6] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[7]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 Lay_Bac_Loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[7] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[8]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 ParkSit_M_loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[8] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[9]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 ParkSit_W_loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[9] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[10]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 ParkSit_M_loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[10] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[11]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 ParkSit_M_loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[11] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[12]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 Lay_Bac_Loop BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[12] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[13]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 BATHER BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[13] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[14]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 SitnWait_loop_W BEACH 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[14] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[15]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_B DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[15] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[16]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_C DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[16] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[17]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_D DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[17] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF
		IF NOT IS_CHAR_DEAD m1_ambient_peds[18]
			OPEN_SEQUENCE_TASK m1_ambient_ped_anim_seq
				TASK_PLAY_ANIM -1 DNCE_M_E DANCING 4.0 TRUE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT m1_ambient_ped_anim_seq 1
			CLOSE_SEQUENCE_TASK m1_ambient_ped_anim_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[18] m1_ambient_ped_anim_seq
			CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[19]
			OPEN_SEQUENCE_TASK m1_first_drink_seq
				TASK_PLAY_ANIM -1 dnk_stndF_loop BAR 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PAUSE -1 4000
				SET_SEQUENCE_TO_REPEAT m1_first_drink_seq 1
			CLOSE_SEQUENCE_TASK m1_first_drink_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[19] m1_first_drink_seq
			CLEAR_SEQUENCE_TASK m1_first_drink_seq
		ENDIF

		IF NOT IS_CHAR_DEAD m1_ambient_peds[20]
			OPEN_SEQUENCE_TASK m1_second_drink_seq
				TASK_PLAY_ANIM -1 dnk_stndF_loop BAR 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PAUSE -1 2200
				SET_SEQUENCE_TO_REPEAT m1_second_drink_seq 1
			CLOSE_SEQUENCE_TASK m1_second_drink_seq
			PERFORM_SEQUENCE_TASK m1_ambient_peds[20] m1_second_drink_seq
			CLEAR_SEQUENCE_TASK m1_second_drink_seq
		ENDIF

	RETURN

// ************************************************************
// 		Refresh chatting peds if both of them have stopped
// ************************************************************

	m1_keep_chatting_peds_talking:

		IF NOT IS_CHAR_DEAD m1_ambient_peds[3]
		AND NOT IS_CHAR_DEAD m1_ambient_peds[4]
			LVAR_INT m1_chat1_status m1_chat2_status
			GET_SCRIPT_TASK_STATUS m1_ambient_peds[3] TASK_CHAT_WITH_CHAR m1_chat1_status
			GET_SCRIPT_TASK_STATUS m1_ambient_peds[4] TASK_CHAT_WITH_CHAR m1_chat2_status
			IF m1_chat1_status = FINISHED_TASK
			AND m1_chat2_status = FINISHED_TASK
				TASK_CHAT_WITH_CHAR m1_ambient_peds[3] m1_ambient_peds[4] TRUE TRUE
				TASK_CHAT_WITH_CHAR m1_ambient_peds[4] m1_ambient_peds[3] FALSE TRUE
			ENDIF
		ENDIF

	RETURN

// ************************************************************
// 			Send small group of ambient peds to attack
// ************************************************************

	m1_set_party_guards_attacking:
 
		m1_index = 5
		WHILE m1_index <= 8
			IF NOT IS_CHAR_DEAD m1_ambient_peds[m1_index]
	   		AND m1_ambient_ped_attacking[m1_index] = 0
				SET_CURRENT_CHAR_WEAPON m1_ambient_peds[m1_index] WEAPONTYPE_MP5
	  			TASK_KILL_CHAR_ON_FOOT m1_ambient_peds[m1_index] scplayer
	   			m1_ambient_ped_attacking[m1_index] = 1
				SET_CHAR_KEEP_TASK m1_ambient_peds[m1_index] TRUE
			ENDIF
			m1_index++
		ENDWHILE
		IF NOT IS_CHAR_DEAD m1_DJ
   		AND m1_DJ_attacking = 0
			SET_CURRENT_CHAR_WEAPON m1_DJ WEAPONTYPE_MP5
  			FREEZE_CHAR_POSITION m1_DJ FALSE
  			TASK_KILL_CHAR_ON_FOOT m1_DJ scplayer
   			m1_DJ_attacking = 1
			SET_CHAR_KEEP_TASK m1_DJ TRUE
		ENDIF

	RETURN

// ************************************************************
// Stop all ambient peds from attacking when no longer required
// ************************************************************

	m1_delete_all_chars_and_cars_not_chasing:

		m1_index = 0
		WHILE m1_index < m1_num_of_ambient_peds
			IF NOT m1_ped_chasing[m1_index] = 1
				IF NOT IS_CHAR_DEAD m1_ambient_peds[m1_index]
					IF NOT IS_CHAR_ON_SCREEN m1_ambient_peds[m1_index]
						DELETE_CHAR m1_ambient_peds[m1_index]
					ENDIF
				ENDIF
			ENDIF
			m1_index++
		ENDWHILE

		m1_index = 0
		WHILE m1_index < m1_num_of_perimeter_cars
			IF NOT m1_car_chasing[m1_index] = 1
				IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_index]
					IF NOT IS_CAR_ON_SCREEN m1_perimeter_cars[m1_index]
						DELETE_CAR m1_perimeter_cars[m1_index]
					ENDIF
				ENDIF
			ENDIF
			m1_index++
		ENDWHILE

//		m1_index = 0
//		WHILE m1_index < 3
//			IF NOT IS_CAR_DEAD m1_perimeter_quads[m1_index]
//				IF NOT IS_CAR_ON_SCREEN m1_perimeter_quads[m1_index]
//					DELETE_CAR m1_perimeter_quads[m1_index]
//				ENDIF
//			ENDIF
//			m1_index++
//		ENDWHILE

	RETURN

// ************************************************************
// 					Set peds to duck and cover
// ************************************************************

	m1_set_ped_reactions_to_fight:

		// peds that aren't guards flee player
		m1_index = 0
		WHILE m1_index < m1_num_of_ambient_peds
			IF NOT IS_CHAR_DEAD m1_ambient_peds[m1_index]
				IF m1_index < 5
				OR m1_index > 8
					TASK_SMART_FLEE_CHAR m1_ambient_peds[m1_index] scplayer 400.0 9999999
					SET_CHAR_KEEP_TASK m1_ambient_peds[m1_index] TRUE
				ENDIF
			ENDIF
			m1_index++
		ENDWHILE

		// second group of dancers hide in cars
		IF NOT IS_CAR_DEAD m1_perimeter_cars[1]
			LOCK_CAR_DOORS m1_perimeter_cars[1] CARLOCK_LOCKOUT_PLAYER_ONLY
			IF NOT IS_CHAR_DEAD m1_ambient_peds[15]
				TASK_ENTER_CAR_AS_DRIVER m1_ambient_peds[15] m1_perimeter_cars[1] -1
			ENDIF
			IF NOT IS_CHAR_DEAD m1_ambient_peds[16]
				TASK_ENTER_CAR_AS_PASSENGER m1_ambient_peds[16] m1_perimeter_cars[1] -1 0
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD m1_perimeter_cars[2]
			LOCK_CAR_DOORS m1_perimeter_cars[2] CARLOCK_LOCKOUT_PLAYER_ONLY
			IF NOT IS_CHAR_DEAD m1_ambient_peds[17]
				TASK_ENTER_CAR_AS_DRIVER m1_ambient_peds[17] m1_perimeter_cars[2] -1
			ENDIF
			IF NOT IS_CHAR_DEAD m1_ambient_peds[18]
				TASK_ENTER_CAR_AS_PASSENGER m1_ambient_peds[18] m1_perimeter_cars[2] -1 0
			ENDIF
		ENDIF

	RETURN

// ************************************************************
// 				   Send cars to chase player
// ************************************************************

	m1_set_cars_chasing_player:

		IF NOT IS_CAR_DEAD m1_sound_van
		
			m1_num_of_cars_chasing_player = 0
			m1_car_index = 0
			WHILE m1_car_index < m1_num_of_perimeter_cars
				IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_car_index]
					GET_DRIVER_OF_CAR m1_perimeter_cars[m1_car_index] m1_perimeter_car_driver
					IF m1_perimeter_car_driver = -1
					AND NOT m1_car_chasing[m1_car_index] = 1
					AND m1_num_of_cars_chasing_player < 2

		 				m1_ped_index = 0
						WHILE m1_ped_index < m1_num_of_ambient_peds
							IF NOT IS_CHAR_DEAD m1_ambient_peds[m1_ped_index]
						 	AND NOT m1_ped_chasing[m1_ped_index] = 1
				   			//AND NOT m1_ambient_ped_attacking[m1_ped_index] = 1
						
		 						LOCK_CAR_DOORS m1_perimeter_cars[m1_car_index] CARLOCK_LOCKOUT_PLAYER_ONLY
		 						TASK_CAR_MISSION m1_ambient_peds[m1_ped_index] m1_perimeter_cars[m1_car_index] -1 MISSION_RAMPLAYER_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
								m1_car_drivers[m1_car_index] = m1_ambient_peds[m1_ped_index]
								m1_driver_getting_in_to_car[m1_car_index] = 1

								SET_CAR_HEAVY m1_perimeter_cars[m1_car_index] TRUE
								SET_CAR_STRONG m1_perimeter_cars[m1_car_index] TRUE
								SET_CAR_ONLY_DAMAGED_BY_PLAYER m1_perimeter_cars[m1_car_index] TRUE
								
								m1_ped_chasing[m1_ped_index] = 1
								m1_car_chasing[m1_car_index] = 1
								m1_num_of_cars_chasing_player++
								// driver found for this car; break out of ped loop
								m1_ped_index = m1_num_of_ambient_peds

							ENDIF
							m1_ped_index++
						ENDWHILE

					ENDIF
				ENDIF
				m1_car_index++
			ENDWHILE

			REMOVE_DECISION_MAKER m1_party_decisions
			COPY_CHAR_DECISION_MAKER -1 m1_party_decisions
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m1_party_decisions EVENT_DRAGGED_OUT_CAR
			m1_index = 0
			WHILE m1_index < m1_num_of_ambient_peds
				IF NOT IS_CHAR_DEAD m1_ambient_peds[m1_index]
					// set reactions of peds that aren't chasing
					IF NOT m1_ped_chasing[m1_index] = 1
						IF m1_index >= 5
						AND m1_index <= 8
							// guards get stay in same place so that there aren't too many chars chasing player in van
							SET_CHAR_STAY_IN_SAME_PLACE m1_ambient_peds[m1_index] TRUE
							SET_CURRENT_CHAR_WEAPON m1_ambient_peds[m1_index] WEAPONTYPE_MP5
							TASK_KILL_CHAR_ON_FOOT m1_ambient_peds[m1_index] scplayer
							m1_ambient_ped_attacking[m1_index] = 1
							SET_CHAR_KEEP_TASK m1_ambient_peds[m1_index] TRUE
							SET_CHAR_RELATIONSHIP m1_ambient_peds[m1_index] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						ELSE
							// all other peds flee player
							TASK_SMART_FLEE_CHAR m1_ambient_peds[m1_index] scplayer 400.0 9999999
							SET_CHAR_KEEP_TASK m1_ambient_peds[m1_index] TRUE
						ENDIF
					ELSE
						SET_CHAR_KEEP_TASK m1_ambient_peds[m1_index] FALSE
					ENDIF
					// reset decision maker to normal responses now that the party's attacking
					SET_CHAR_DECISION_MAKER m1_ambient_peds[m1_index] m1_party_decisions
				ENDIF
				m1_index++
			ENDWHILE
			IF NOT IS_CHAR_DEAD m1_DJ
				SET_CHAR_DECISION_MAKER m1_DJ m1_party_decisions
			ENDIF

		ENDIF

	RETURN

// ************************************************************
// 			Check if anyone's fallen out of their car
// ************************************************************

	m1_keep_drivers_in_cars:

		IF NOT IS_CAR_DEAD m1_sound_van

			m1_car_index = 0
			WHILE m1_car_index < m1_num_of_perimeter_cars

				IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_car_index]
				AND m1_car_chasing[m1_car_index] = 1

					IF NOT IS_CHAR_DEAD m1_car_drivers[m1_car_index]
					
						IF NOT IS_CHAR_IN_CAR m1_car_drivers[m1_car_index] m1_perimeter_cars[m1_car_index]
						AND m1_driver_getting_in_to_car[m1_car_index] = 0
							TASK_CAR_MISSION m1_car_drivers[m1_car_index] m1_perimeter_cars[m1_car_index] -1 MISSION_RAMPLAYER_FARAWAY 30.0 DRIVINGMODE_AVOIDCARS
							m1_driver_getting_in_to_car[m1_car_index] = 1
						ENDIF

						IF IS_CHAR_IN_CAR m1_car_drivers[m1_car_index] m1_perimeter_cars[m1_car_index]
						AND m1_driver_getting_in_to_car[m1_car_index] = 1
							m1_driver_getting_in_to_car[m1_car_index] = 0
						ENDIF

					ENDIF

				ENDIF
				m1_car_index++
			ENDWHILE

		ENDIF
		
	RETURN

// ************************************************************
// 	 Make sure cars aren't too far behind when chasing player
// ************************************************************

	m1_stop_chasing_cars_from_losing_player:

		m1_index = 0
		WHILE m1_index < m1_num_of_perimeter_cars
			
			IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_index]
			AND m1_car_chasing[m1_index] = 1

				GET_DRIVER_OF_CAR m1_perimeter_cars[m1_index] m1_perimeter_car_driver			
				IF NOT IS_CAR_ON_SCREEN m1_perimeter_cars[m1_index]
				AND NOT m1_perimeter_car_driver = -1
				AND NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m1_perimeter_cars[m1_index] 100.0 100.0 FALSE 		

					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -10.0 -1.0 m1_perimeter_car_x m1_perimeter_car_y m1_perimeter_car_z
					SET_CAR_COORDINATES m1_perimeter_cars[m1_index] m1_perimeter_car_x m1_perimeter_car_y m1_perimeter_car_z

				ENDIF
			
			ENDIF
			m1_index++
		ENDWHILE

	RETURN

// ************************************************************
//     Stop chasing drivers once player is near destination
// ************************************************************

	m1_stop_everyone_from_chasing_player:

		m1_car_index = 0
		WHILE m1_car_index < m1_num_of_perimeter_cars

			IF NOT IS_CAR_DEAD m1_perimeter_cars[m1_car_index]
			AND NOT IS_CHAR_DEAD m1_car_drivers[m1_car_index]
			AND m1_car_chasing[m1_car_index] = 1

				TASK_CAR_DRIVE_TO_COORD m1_car_drivers[m1_car_index] m1_perimeter_cars[m1_car_index] m1_beach_party_x m1_beach_party_y m1_beach_party_z 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
																																																	                           
			ENDIF
			m1_car_index++
		ENDWHILE
		
	RETURN

// *****************************************************************
// 		 Cutscene showing player driving sound van into garage
// *****************************************************************

	m1_play_mission_passed_cutscene:

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE 
		OPEN_GARAGE mul_lan
		WHILE NOT IS_GARAGE_OPEN mul_lan
			WAIT 0 	
		ENDWHILE
		IF IS_CAR_DEAD m1_sound_van
			CLEAR_PRINTS
			PRINT_NOW ( MUS1_20 ) 5000 0
			GOTO mission_music1_failed
		ENDIF

		CLEAR_AREA m1_destination_x m1_destination_y m1_destination_z 50.0 FALSE
		// remove any cars still chasing
		m1_car_index = 0
		WHILE m1_car_index < m1_num_of_perimeter_cars
			IF m1_car_chasing[m1_car_index] = 1
				DELETE_CHAR m1_car_drivers[m1_car_index]
				DELETE_CAR m1_perimeter_cars[m1_car_index]
			ENDIF
			m1_car_index++
		ENDWHILE

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		IF NOT IS_CAR_DEAD m1_sound_van
			SET_CAR_COORDINATES m1_sound_van m1_destination_x m1_destination_y m1_destination_z
			SET_CAR_HEADING m1_sound_van 0.0
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_INTO_CAR scplayer m1_sound_van
			ENDIF
		ENDIF

		WAIT 500

		SET_FIXED_CAMERA_POSITION 1640.75 -1528.1 14.25 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1642.49 -1522.89 14.11 JUMP_CUT

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE
		IF IS_CAR_DEAD m1_sound_van
			CLEAR_PRINTS
			PRINT_NOW ( MUS1_20 ) 5000 0
			GOTO mission_music1_failed
		ENDIF

		LVAR_INT m1_cutscene_skipped
		m1_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		LVAR_INT m1_final_cutscene_seq
		IF NOT IS_CAR_DEAD m1_sound_van
			OPEN_SEQUENCE_TASK m1_final_cutscene_seq
				TASK_CAR_DRIVE_TO_COORD -1 m1_sound_van 1643.48 -1515.73 13.82 5.0 MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS
				TASK_LEAVE_CAR -1 m1_sound_van
				TASK_GO_STRAIGHT_TO_COORD -1 1641.91 -1517.64 12.62 PEDMOVE_WALK -1
				TASK_GO_STRAIGHT_TO_COORD -1 1641.91 -1522.36 12.61 PEDMOVE_WALK -1
			CLOSE_SEQUENCE_TASK m1_final_cutscene_seq
			PERFORM_SEQUENCE_TASK scplayer m1_final_cutscene_seq
			CLEAR_SEQUENCE_TASK m1_final_cutscene_seq
		ENDIF

		WAIT 1000

		// wait until player's left van to fade out
		GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK m1_task_status
		IF NOT m1_task_status = FINISHED_TASK 
			GET_SEQUENCE_PROGRESS scplayer m1_seq_progress
			WHILE m1_seq_progress <= 1
			AND NOT m1_task_status = FINISHED_TASK
				WAIT 0
				GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK m1_task_status
				IF NOT m1_task_status = FINISHED_TASK
					GET_SEQUENCE_PROGRESS scplayer m1_seq_progress
				ENDIF
			ENDWHILE
		ENDIF

		WAIT 2000

		m1_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF m1_cutscene_skipped = 1
		ENDIF

		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE 
		CLOSE_GARAGE mul_lan
		WHILE NOT IS_GARAGE_CLOSED mul_lan
			WAIT 0 	
		ENDWHILE
		IF IS_CAR_DEAD m1_sound_van
			CLEAR_PRINTS
			PRINT_NOW ( MUS1_20 ) 5000 0
			GOTO mission_music1_failed
		ENDIF

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer m1_destination_x m1_destination_y m1_destination_z
		ELSE
			SET_CHAR_COORDINATES scplayer m1_destination_x m1_destination_y m1_destination_z
		ENDIF
		SET_CHAR_HEADING scplayer 180.0

		DELETE_CAR m1_sound_van

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

	RETURN

// ************************************************************
//     		Set up conversation before dance with DJ
// ************************************************************

	m1_init_first_conversation:

		START_SETTING_UP_CONVERSATION m1_DJ
		VAR_INT m1_random_int m1_random_int2
		LVAR_TEXT_LABEL m1_hi_node m1_doffe_node m1_bexit_node m1_dstar_node m1_gexit_node
		LVAR_INT m1_hi_sound m1_hiy_sound m1_hin_sound
		LVAR_INT m1_doffe_sound m1_doffey_sound m1_doffen_sound
		LVAR_INT m1_bexit_sound m1_dstar_sound m1_gexit_sound

		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		GENERATE_RANDOM_INT_IN_RANGE 0 3 m1_random_int2
		IF m1_random_int = 0
			SWITCH m1_random_int2
				CASE 0
					$m1_hi_node = HI1
					m1_hi_sound = SOUND_HI1
					m1_hiy_sound = SOUND_HI1Y
					m1_hin_sound = SOUND_HI1N
					BREAK
				CASE 1
					$m1_hi_node = HI3
					m1_hi_sound = SOUND_HI3
					m1_hiy_sound = SOUND_HI3Y
					m1_hin_sound = SOUND_HI3N
					BREAK
				CASE 2
					$m1_hi_node = HI5
					m1_hi_sound = SOUND_HI5
					m1_hiy_sound = SOUND_HI5Y
					m1_hin_sound = SOUND_HI5N
					BREAK
				DEFAULT
					BREAK
			ENDSWITCH
		ELSE
			SWITCH m1_random_int2
				CASE 0
					$m1_hi_node = HI2
					m1_hi_sound = SOUND_HI2
					m1_hiy_sound = SOUND_HI2Y
					m1_hin_sound = SOUND_HI2N
					BREAK
				CASE 1
					$m1_hi_node = HI4
					m1_hi_sound = SOUND_HI4
					m1_hiy_sound = SOUND_HI4Y
					m1_hin_sound = SOUND_HI4N
					BREAK
				CASE 2
					$m1_hi_node = HI6
					m1_hi_sound = SOUND_HI6
					m1_hiy_sound = SOUND_HI6Y
					m1_hin_sound = SOUND_HI6N
					BREAK
				DEFAULT
					BREAK
			ENDSWITCH
		ENDIF

		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		IF m1_random_int = 0
			$m1_doffe_node = DOFFE1
			m1_doffe_sound = SOUND_DOFFE1
			m1_doffey_sound = SOUND_DOFFE1Y
			m1_doffen_sound = SOUND_DOFFE1N
		ELSE
			$m1_doffe_node = DOFFE3
			m1_doffe_sound = SOUND_DOFFE3
			m1_doffey_sound = SOUND_DOFFE3Y
			m1_doffen_sound = SOUND_DOFFE3N
		ENDIF
		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		IF m1_random_int = 0
			$m1_bexit_node = BEXIT1
			m1_bexit_sound = SOUND_BEXIT1
		ELSE
			$m1_bexit_node = BEXIT2
			m1_bexit_sound = SOUND_BEXIT2
		ENDIF
		$m1_dstar_node = DSTAR2
		m1_dstar_sound = SOUND_DSTAR2
		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		IF m1_random_int = 0
			$m1_gexit_node = GEXIT1
			m1_gexit_sound = SOUND_GEXIT1
		ELSE
			$m1_gexit_node = GEXIT2
			m1_gexit_sound = SOUND_GEXIT2
		ENDIF
		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH $m1_hi_node $m1_doffe_node $m1_bexit_node m1_hi_sound m1_hiy_sound m1_hin_sound
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH $m1_bexit_node m1_bexit_sound
		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH $m1_doffe_node $m1_dstar_node $m1_gexit_node m1_doffe_sound m1_doffey_sound m1_doffen_sound
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH $m1_dstar_node m1_dstar_sound
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH $m1_gexit_node m1_gexit_sound
		FINISH_SETTING_UP_CONVERSATION

	RETURN

// ************************************************************
//     		Set up conversation after dance with DJ
// ************************************************************

	m1_init_second_conversation:

		START_SETTING_UP_CONVERSATION m1_DJ
		LVAR_TEXT_LABEL m1_voffe_node m1_vsure_node
		LVAR_INT m1_voffe_sound m1_voffey_sound m1_voffen_sound
		LVAR_INT m1_vsure_sound m1_vsurey_sound m1_vsuren_sound
		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		IF m1_random_int = 0
			$m1_voffe_node = VOFFE1
			m1_voffe_sound = SOUND_VOFFE1
			m1_voffey_sound = SOUND_VOFFE1Y
			m1_voffen_sound = SOUND_VOFFE1N
		ELSE
			$m1_voffe_node = VOFFE2
			m1_voffe_sound = SOUND_VOFFE2
			m1_voffey_sound = SOUND_VOFFE2Y
			m1_voffen_sound = SOUND_VOFFE2N
		ENDIF
		GENERATE_RANDOM_INT_IN_RANGE 0 2 m1_random_int
		IF m1_random_int = 0
			$m1_vsure_node = VSURE1
			m1_vsure_sound = SOUND_VSURE1
			m1_vsurey_sound = SOUND_VSURE1Y
			m1_vsuren_sound = SOUND_VSURE1N
		ELSE
			$m1_vsure_node = VSURE2
			m1_vsure_sound = SOUND_VSURE2
			m1_vsurey_sound = SOUND_VSURE2Y
			m1_vsuren_sound = SOUND_VSURE2N
		ENDIF
		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH $m1_voffe_node VYES2 $m1_vsure_node m1_voffe_sound m1_voffey_sound m1_voffen_sound
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH VYES2 SOUND_VYES2
		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH $m1_vsure_node VLATE VYES1 m1_vsure_sound m1_vsurey_sound m1_vsuren_sound
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH VLATE SOUND_VLATE
		SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH VYES1 SOUND_VYES1
		FINISH_SETTING_UP_CONVERSATION

	RETURN

// ************************************************************
//     	   Find coordinates for new beach party location
// ************************************************************

	m1_calculate_new_positions:

		LVAR_FLOAT m1_xdiff m1_ydiff
		LVAR_FLOAT m1_xnew m1_ynew

		// first campfire
		m1_xdiff = m1_campfire1_x - m1_beach_party_x
		m1_ydiff = m1_campfire1_y - m1_beach_party_y
		GOSUB m1_calculate_individual_position
		m1_campfire1_x = m1_xnew
		m1_campfire1_y = m1_ynew
		m1_campfire1_z = 2.68
		// second campfire
		m1_xdiff = m1_campfire2_x - m1_beach_party_x
		m1_ydiff = m1_campfire2_y - m1_beach_party_y
		GOSUB m1_calculate_individual_position
		m1_campfire2_x = m1_xnew
		m1_campfire2_y = m1_ynew
		m1_campfire2_z = 2.39

		// sound van
		m1_xdiff = m1_sound_van_x - m1_beach_party_x
		m1_ydiff = m1_sound_van_y - m1_beach_party_y
		GOSUB m1_calculate_individual_position
		m1_sound_van_x = m1_xnew
		m1_sound_van_y = m1_ynew
		m1_sound_van_z = 3.42
		m1_sound_van_heading -= 51.0

		// DJ
		m1_xdiff = m1_DJ_start_x - m1_beach_party_x
		m1_ydiff = m1_DJ_start_y - m1_beach_party_y
		GOSUB m1_calculate_individual_position
		m1_DJ_start_x = m1_xnew
		m1_DJ_start_y = m1_ynew

		m1_xdiff = m1_next_to_DJ_x - m1_beach_party_x
		m1_ydiff = m1_next_to_DJ_y - m1_beach_party_y
		GOSUB m1_calculate_individual_position
		m1_next_to_DJ_x = m1_xnew
		m1_next_to_DJ_y = m1_ynew
		m1_next_to_DJ_z = 2.31

		// ambient ped positions
		m1_index = 0
		WHILE m1_index < m1_num_of_ambient_peds

			m1_xdiff = m1_ambient_ped_start_x[m1_index] - m1_beach_party_x
			m1_ydiff = m1_ambient_ped_start_y[m1_index] - m1_beach_party_y

			GOSUB m1_calculate_individual_position

			m1_ambient_ped_start_x[m1_index] = m1_xnew
			m1_ambient_ped_start_y[m1_index] = m1_ynew
			m1_ambient_ped_start_heading[m1_index] -= 51.0

			m1_index++
		ENDWHILE

//		// quads
//		m1_index = 0
//		WHILE m1_index < 3
//			m1_xdiff = m1_perimeter_quad_x[m1_index] - m1_beach_party_x
//			m1_ydiff = m1_perimeter_quad_y[m1_index] - m1_beach_party_y
//			GOSUB m1_calculate_individual_position
//			m1_perimeter_quad_x[m1_index] = m1_xnew
//			m1_perimeter_quad_y[m1_index] = m1_ynew
//			m1_index++
//		ENDWHILE
//		m1_perimeter_quad_heading[0] = 190.0
//		m1_perimeter_quad_heading[1] = 317.0
//		m1_perimeter_quad_heading[2] = 112.0

	RETURN

	m1_calculate_individual_position:

		LVAR_FLOAT m1_xdiff_squared m1_ydiff_squared
		LVAR_FLOAT m1_dist_from_centre m1_dist_from_centre_squared

		m1_xdiff_squared = m1_xdiff * m1_xdiff
		m1_ydiff_squared = m1_ydiff * m1_ydiff
		m1_dist_from_centre_squared = m1_xdiff_squared + m1_ydiff_squared
		SQRT m1_dist_from_centre_squared m1_dist_from_centre

		// need cos and sin of (theta + 51) to find new coord (where theta is angle from y-axis of old coords)
		// don't have inverse tan to find theta, but only need cos(A+B) and sin(A+B) so use
		// cos(A+B) = cosAcosB - sinAsinB, sin(A+B) = sinAcosB + cosAsinB
		LVAR_FLOAT m1_cos_theta m1_sin_theta m1_cos_rotation m1_sin_rotation
		LVAR_FLOAT m1_cos_theta_cos_rotation m1_sin_theta_sin_rotation
		LVAR_FLOAT m1_sin_theta_cos_rotation m1_cos_theta_sin_rotation
		LVAR_FLOAT m1_cos_theta_plus_rotation m1_sin_theta_plus_rotation

		m1_cos_theta = m1_ydiff / m1_dist_from_centre
		m1_sin_theta = m1_xdiff / m1_dist_from_centre
		COS 51.0 m1_cos_rotation
		SIN 51.0 m1_sin_rotation

 		m1_cos_theta_cos_rotation = m1_cos_theta * m1_cos_rotation
 		m1_sin_theta_sin_rotation = m1_sin_theta * m1_sin_rotation
 		m1_sin_theta_cos_rotation = m1_sin_theta * m1_cos_rotation
 		m1_cos_theta_sin_rotation = m1_cos_theta * m1_sin_rotation

		m1_cos_theta_plus_rotation = m1_cos_theta_cos_rotation - m1_sin_theta_sin_rotation
		m1_sin_theta_plus_rotation = m1_sin_theta_cos_rotation + m1_cos_theta_sin_rotation

		m1_xnew = m1_dist_from_centre * m1_sin_theta_plus_rotation
		m1_ynew = m1_dist_from_centre * m1_cos_theta_plus_rotation
		m1_xnew += m1_beach_party_x
		m1_ynew += m1_beach_party_y

	RETURN

// ************************************************************
//   		  If player gets out, add blip for van
// ************************************************************

	m1_current_blip_check:

		IF NOT IS_CAR_DEAD m1_sound_van
			IF m1_player_in_sound_van = 1
				IF NOT IS_CHAR_IN_CAR scplayer m1_sound_van
					REMOVE_BLIP m1_destination_blip
					ADD_BLIP_FOR_CAR m1_sound_van m1_sound_van_blip
					SET_BLIP_AS_FRIENDLY m1_sound_van_blip TRUE
					PRINT_NOW ( MUS1_6 ) 7000 0
					m1_player_in_sound_van = 0
				ENDIF
			ELSE
				IF IS_CHAR_IN_CAR scplayer m1_sound_van
					REMOVE_BLIP m1_sound_van_blip
					ADD_BLIP_FOR_COORD m1_destination_x m1_destination_y m1_destination_z m1_destination_blip
					PRINT_NOW ( MUS1_19 ) 7000 0
					m1_player_in_sound_van = 1
				ENDIF
			ENDIF
		ENDIF

	RETURN

// ****************************************************************
// Cutscene showing player beach party and giving them instructions
// ****************************************************************

	m1_play_first_scripted_cutscene:

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		CLEAR_PRINTS
		PRINT_NOW ( MUS1_2 ) 10000 0

		SET_FIXED_CAMERA_POSITION 532.87 -1893.69 3.29 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 533.97 -1892.67 3.3 JUMP_CUT

		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		SET_CHAR_COORDINATES scplayer player_x player_y -100.0
		// set player facing the beach party
		LVAR_FLOAT m1_direction_x m1_direction_y
		m1_direction_x = m1_beach_party_x - player_x
		m1_direction_y = m1_beach_party_y - player_y
		GET_HEADING_FROM_VECTOR_2D m1_direction_x m1_direction_y heading
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			SET_CHAR_HEADING scplayer heading
		ELSE
			LVAR_INT m1_player_car
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer m1_player_car
			CAR_SET_IDLE m1_player_car
			SET_CAR_HEADING m1_player_car heading
		ENDIF

		WAIT 2000

		m1_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		WAIT 8000

//		PRINT_NOW ( MUS1_3 ) 10000 0
//
//		SET_FIXED_CAMERA_POSITION 534.88 -1903.09 2.16 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 535.7 -1897.65 2.07 JUMP_CUT
//
//		WAIT 10000
//
//		PRINT_NOW ( MUS1_4 ) 10000 0
//
//		SET_FIXED_CAMERA_POSITION 535.77 -1895.22 2.89 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 536.55 -1893.94 2.92 JUMP_CUT
//
//		WAIT 10000

		m1_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF m1_cutscene_skipped = 1
		ENDIF

		CLEAR_PRINTS

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		SET_PLAYER_CONTROL player1 ON

	RETURN

// ***************************************************************
// 						   Mission audio
// ***************************************************************

	m1_setup_audio_data:

		SWITCH m1_current_audio_needed

			CASE M1_STEAL_AUDIO
				$m1_audio_text[0] = &LOC1_YT
				m1_audio_sound[0] = SOUND_LOC1_YT
				m1_total_audio_to_play = 1
				BREAK

			CASE M1_DANCE_FAIL_AUDIO
				$m1_audio_text[0] = &DNCEF1
				m1_audio_sound[0] = SOUND_DNCEF1
				m1_total_audio_to_play = 1
				BREAK

			DEFAULT
				BREAK

		ENDSWITCH

		m1_audio_is_playing = 0
		m1_audio_index = 0
		m1_started_talking = 0

		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS

	RETURN

	m1_load_and_play_audio:

		IF m1_audio_is_playing = 0
		OR m1_audio_is_playing = 1
			IF m1_audio_index < m1_total_audio_to_play
				IF TIMERA > 1000
					GOSUB m1_play_audio
				ENDIF
			ENDIF
		ENDIF

		IF m1_audio_is_playing = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				m1_audio_is_playing = 0
				m1_audio_index++
				CLEAR_PRINTS
				TIMERA = 0	
			ENDIF
		ENDIF

	RETURN

	m1_play_audio:

		IF m1_audio_is_playing = 0
			LOAD_MISSION_AUDIO 1 m1_audio_sound[m1_audio_index]
			m1_audio_is_playing = 1
		ENDIF
		IF m1_audio_is_playing = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW $m1_audio_text[m1_audio_index] 10000 1
				IF m1_current_audio_needed = M1_DANCE_FAIL_AUDIO
				AND NOT IS_CHAR_DEAD m1_DJ
					ATTACH_MISSION_AUDIO_TO_CHAR 1 m1_DJ
				ENDIF
				PLAY_MISSION_AUDIO 1
				m1_audio_is_playing = 2
			ENDIF
		ENDIF	
		
	RETURN

// ******************************** Mission music1 failed **********************************

mission_music1_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// ******************************** Mission music1 passed **********************************

mission_music1_passed:

flag_strap_mission_counter ++
REGISTER_MISSION_PASSED ( STRAP_1 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 3
//ADD_SCORE player1 100
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
RETURN
		


// *********************************** Mission cleanup *************************************

mission_cleanup_music1:

//MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
MARK_MODEL_AS_NO_LONGER_NEEDED ESPERANT
MARK_MODEL_AS_NO_LONGER_NEEDED OCEANIC
MARK_MODEL_AS_NO_LONGER_NEEDED PONY
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED BFYST
MARK_MODEL_AS_NO_LONGER_NEEDED WFYST
MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
MARK_MODEL_AS_NO_LONGER_NEEDED WMYRO
MARK_MODEL_AS_NO_LONGER_NEEDED WFYCLOT
MARK_MODEL_AS_NO_LONGER_NEEDED WMYST
MARK_MODEL_AS_NO_LONGER_NEEDED WMYCLOT
MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ
MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
MARK_MODEL_AS_NO_LONGER_NEEDED OFYRI
MARK_VEHICLE_MOD_AS_NO_LONGER_NEEDED wheel_or1
MARK_OBJECT_AS_NO_LONGER_NEEDED m1_campfire1wood
MARK_OBJECT_AS_NO_LONGER_NEEDED m1_campfire2wood
MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Dance.sc
REMOVE_ANIMATION BAR
REMOVE_ANIMATION DANCING
REMOVE_ANIMATION BEACH
REMOVE_ANIMATION SCRATCHING
REMOVE_BLIP m1_DJ_blip
REMOVE_BLIP m1_sound_van_blip
REMOVE_BLIP m1_destination_blip
//REMOVE_ALL_SCRIPT_FIRES
KILL_FX_SYSTEM m1_campfire1
KILL_FX_SYSTEM m1_campfire2
CLEAR_SEQUENCE_TASK m1_first_drink_seq
CLEAR_SEQUENCE_TASK m1_second_drink_seq
CLEAR_SEQUENCE_TASK m1_DJ_leave_car_seq
CLEAR_SEQUENCE_TASK m1_final_cutscene_seq
CLEAR_SEQUENCE_TASK m1_DJ_play_seq
CLEAR_SEQUENCE_TASK m1_DJ_dance_seq
CLEAR_SEQUENCE_TASK m1_ambient_ped_anim_seq
IF IS_PLAYER_PLAYING player1
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
ENDIF
IF NOT IS_CHAR_DEAD m1_DJ
	CLEAR_CONVERSATION_FOR_CHAR m1_DJ
	FREEZE_CHAR_POSITION m1_DJ FALSE
ENDIF
IF NOT IS_CAR_DEAD m1_sound_van
	SET_CAR_CAN_BE_VISIBLY_DAMAGED m1_sound_van TRUE
ENDIF
REMOVE_DECISION_MAKER m1_party_decisions
CLEAR_HELP
SWITCH_AUDIO_ZONE BEACH FALSE
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 502.15 -1913.39 -5.0 558.86 -1838.87 10.0
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN

 
}