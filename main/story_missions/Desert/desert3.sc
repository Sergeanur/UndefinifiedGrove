MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Interdiction
//				     AUTHOR : Neil
//		       DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// globals
VAR_INT desert3_mission_attempts


{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************
SCRIPT_NAME DES3
GOSUB mission_start_DES3
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_DES3
ENDIF
GOSUB mission_cleanup_DES3
MISSION_END

mission_start_DES3:
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed  
LVAR_INT m_quit
LVAR_INT m_frame_num
LVAR_INT m_this_frame_time
LVAR_INT m_last_frame_time
LVAR_INT m_time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT temp_seq
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT vec2_x vec2_y vec2_z
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT x3 y3 z3
// commonly used flags
LVAR_INT dialogue_flag
LVAR_INT help_flag
// commonly used timers
LVAR_INT dialogue_timer
LVAR_INT help_timer
// commonly used decision makers
LVAR_INT empty_dm
LVAR_INT tough_dm

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0	  
m_quit		= 0

dialogue_flag = 0
help_flag	  = 0

dialogue_timer = 0
help_timer	   = 0

TIMERA = 0
TIMERB = 0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm

mission_loop_DES3:
WAIT 0


IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S 
	m_passed = 1
ENDIF


// end of debug tools **************		
									
// Frame counter
m_frame_num++
IF m_frame_num > 10
	m_frame_num = 0
ENDIF

// Additional Timers
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time

dialogue_timer += m_time_diff
help_timer	   += m_time_diff

alpha_heli_timer += m_time_diff
bravo_heli_timer += m_time_diff
fed_timer	+= m_time_diff
fed_timer2  += m_time_diff
swing_timer_until_landed -= m_time_diff
pheli_health_timer += m_time_diff


GOSUB d3_debug_tools

	SWITCH m_stage

		CASE 0
			GOSUB d3_m_stage_0
		BREAK
		CASE 1
			GOSUB d3_m_stage_1
		BREAK
		CASE 2
			GOSUB d3_m_stage_2
		BREAK
		CASE 3
			GOSUB d3_m_stage_3
		BREAK
		CASE 4
			GOSUB d3_m_stage_4
		BREAK
		CASE 5
			GOSUB d3_m_stage_5
		BREAK
		CASE 6
			GOSUB d3_m_stage_6
		BREAK

	ENDSWITCH
	

GOSUB d3_global_functions



//  end of main loop *** don't change ***
end_of_main_loop_DES3:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_DES3 
		ELSE
			GOSUB mission_passed_DES3
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_DES3
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF


// *************************************************************************************************************
// 												DEBUG TOOLS   
// *************************************************************************************************************
d3_debug_tools:
	LVAR_INT debug_on	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_X
		IF debug_on = 0
			debug_on = 1
			WRITE_DEBUG LEVEL_DEBUG_ON
		ELSE
			debug_on = 0
			WRITE_DEBUG LEVEL_DEBUG_OFF
		ENDIF
	ENDIF

	IF debug_on = 1
		// Display mission stage variables for debug
		LVAR_INT display_debug
		VAR_INT DES3_view_debug[8]
		VAR_FLOAT DES3_view_debug_f[8]
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
			display_debug++
			IF display_debug > 6
				display_debug = 0
			ENDIF
			CLEAR_ALL_VIEW_VARIABLES
		ENDIF
		IF display_debug = 1
			// system variables
			DES3_view_debug[0] = m_stage
			DES3_view_debug[1] = m_goals
			DES3_view_debug[2] = dialogue_flag
			DES3_view_debug[3] = dialogue_timer
			DES3_view_debug[4] = help_flag
			DES3_view_debug[5] = help_timer
			DES3_view_debug[6] = TIMERA
			DES3_view_debug[7]	= TIMERB
			VIEW_INTEGER_VARIABLE DES3_view_debug[0] m_stage
			VIEW_INTEGER_VARIABLE DES3_view_debug[1] m_goals
			VIEW_INTEGER_VARIABLE DES3_view_debug[2] dialogue_flag
			VIEW_INTEGER_VARIABLE DES3_view_debug[3] dialogue_timer
			VIEW_INTEGER_VARIABLE DES3_view_debug[4] help_flag
			VIEW_INTEGER_VARIABLE DES3_view_debug[5] help_timer
			VIEW_INTEGER_VARIABLE DES3_view_debug[6] TIMERA
			VIEW_INTEGER_VARIABLE DES3_view_debug[7] TIMERB
		ENDIF
		IF display_debug = 2

		// put mission variable for display in here

			DES3_view_debug[0] = pheli_progress
			DES3_view_debug[1] = bravo_ped_flag[0]
			DES3_view_debug[2] = bravo_ped_flag[1]
			DES3_view_debug[3] = bravo_ped_flag[2]
			DES3_view_debug[4] = bravo_ped_flag[3]
			DES3_view_debug[5] = alpha_helis_fucked
			DES3_view_debug[6] = bravo_helis_fucked
			DES3_view_debug[7] = total_fucked_helis
			VIEW_INTEGER_VARIABLE DES3_view_debug[0] pheli_progress
			VIEW_INTEGER_VARIABLE DES3_view_debug[1] bravo_ped_flag[0]
			VIEW_INTEGER_VARIABLE DES3_view_debug[2] bravo_ped_flag[1]
			VIEW_INTEGER_VARIABLE DES3_view_debug[3] bravo_ped_flag[2]
			VIEW_INTEGER_VARIABLE DES3_view_debug[4] bravo_ped_flag[3]
			VIEW_INTEGER_VARIABLE DES3_view_debug[5] alpha_helis_fucked
			VIEW_INTEGER_VARIABLE DES3_view_debug[6] bravo_helis_fucked
			VIEW_INTEGER_VARIABLE DES3_view_debug[7] total_fucked_helis
		ENDIF
		IF display_debug = 3

		// put mission variable for display in here

			DES3_view_debug[0] = alpha_heli_ai
			DES3_view_debug[1] = alpha_heli_goals
			DES3_view_debug[2] = alpha_ped_flag[0]
			DES3_view_debug[3] = alpha_ped_flag[1]
			DES3_view_debug[4] = alpha_ped_flag[2]
			DES3_view_debug[5] = alpha_ped_flag[3]
			DES3_view_debug[6] = alpha_heli_control
		//	DES3_view_debug[7] = TIMERB
			
			VIEW_INTEGER_VARIABLE DES3_view_debug[0] alpha_heli_ai
			VIEW_INTEGER_VARIABLE DES3_view_debug[1] alpha_heli_goals
			VIEW_INTEGER_VARIABLE DES3_view_debug[2] alpha_ped_flag[0]
			VIEW_INTEGER_VARIABLE DES3_view_debug[3] alpha_ped_flag[1]
			VIEW_INTEGER_VARIABLE DES3_view_debug[4] alpha_ped_flag[2]
			VIEW_INTEGER_VARIABLE DES3_view_debug[5] alpha_ped_flag[3]
			VIEW_INTEGER_VARIABLE DES3_view_debug[6] alpha_heli_control
		ENDIF

		IF display_debug = 4

		// put mission variable for display in here

			DES3_view_debug[0] = alpha_ped_attached[0]
			DES3_view_debug[1] = alpha_ped_attached[1]
			DES3_view_debug[2] = alpha_ped_attached[2]
			DES3_view_debug[3] = alpha_ped_attached[3]
			DES3_view_debug_f[4] = fed_distance
			DES3_view_debug[5] = fed_timer
		//	DES3_view_debug[6] = bravo_helis_fucked
		//	DES3_view_debug[7] = total_fucked_helis
			
			VIEW_INTEGER_VARIABLE DES3_view_debug[0] alpha_ped_attached[0]
			VIEW_INTEGER_VARIABLE DES3_view_debug[1] alpha_ped_attached[1]
			VIEW_INTEGER_VARIABLE DES3_view_debug[2] alpha_ped_attached[2]
			VIEW_INTEGER_VARIABLE DES3_view_debug[3] alpha_ped_attached[3]
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[4] fed_distance
			VIEW_INTEGER_VARIABLE DES3_view_debug[5] fed_timer
		ENDIF

		IF display_debug = 5

		// put mission variable for display in here

			DES3_view_debug_f[0] = swing_speed_x
			DES3_view_debug_f[1] = swing_accel_x
			DES3_view_debug_f[2] = swing_rot_x	
			DES3_view_debug_f[3] = swing_dest_x 
			DES3_view_debug_f[4] = swing_dist_x 
			DES3_view_debug_f[5] = swing_angle_for_xy
			DES3_view_debug_f[6] = swing_direct_dist
			DES3_view_debug_f[7] = swing_2d_dist
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[0] swing_speed_x
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[1] swing_accel_x
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[2] swing_rot_x	
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[3] swing_dest_x 
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[4] swing_dist_x 
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[5] swing_angle_for_xy
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[6] swing_direct_dist
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[7] swing_2d_dist
		ENDIF

		IF display_debug = 6

		// put mission variable for display in here

			DES3_view_debug_f[0] = swing_angle_for_z
			DES3_view_debug_f[1] = swing_dist_z
			DES3_view_debug[2] = bravo_heli_ai
			DES3_view_debug[3] = bravo_heli_goals
			DES3_view_debug[4] = bravo_pilot
			DES3_view_debug[5] = bravo_heli 
			DES3_view_debug[6] = total_created_helis
			DES3_view_debug[7] = bravo_heli_control
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[0] swing_angle_for_z
			VIEW_FLOAT_VARIABLE DES3_view_debug_f[1] swing_dist_z
			VIEW_INTEGER_VARIABLE DES3_view_debug[2] bravo_heli_ai
			VIEW_INTEGER_VARIABLE DES3_view_debug[3] bravo_heli_goals
			VIEW_INTEGER_VARIABLE DES3_view_debug[4] bravo_pilot
			VIEW_INTEGER_VARIABLE DES3_view_debug[5] bravo_heli 
			VIEW_INTEGER_VARIABLE DES3_view_debug[6] total_created_helis
			VIEW_INTEGER_VARIABLE DES3_view_debug[7] bravo_heli_control
		ENDIF


		 
		// Quit level - no mission pass/fail - cleanup only 
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC 
			m_quit = 1
		ENDIF
		// Pause level
		LVAR_INT pause_level
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_P
			IF pause_level = 0
				pause_level = 1
				WRITE_DEBUG LEVEL_PAUSED
			ELSE
				pause_level = 0
				WRITE_DEBUG LEVEL_UNPAUSED
			ENDIF		
		ENDIF
		IF pause_level = 1
			GOTO end_of_main_loop_DES3
		ENDIF

		//// progress m_goals & m_stage
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_PLUS
		//	m_goals++
		//ENDIF
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_MINUS
		//	m_goals--
		//ENDIF
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_ASTERISK 
		//	m_stage++
		//ENDIF
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_SLASH 
		//	m_stage--
		//ENDIF
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_9
		//	m_goals = 99
		//ENDIF
			
	ENDIF
RETURN


// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
d3_m_stage_0:
		
		// fake creates
		IF m_goals = -1
			CREATE_CAR PONY 0.0 0.0 0.0 pheli
			CREATE_CAR PONY 0.0 0.0 0.0 alpha_heli
			CREATE_CAR PONY 0.0 0.0 0.0 bravo_heli
			CREATE_CAR PONY 0.0 0.0 0.0 heli_to_move 
			CREATE_CAR PONY 0.0 0.0 0.0 heli_to_avoid  
			CREATE_CAR PONY 0.0 0.0 0.0 grabbed_car
			
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 pheli_pilot
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 alpha_pilot
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 bravo_pilot
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 alpha_ped[0]
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 bravo_ped[0]

			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 alpha_heli_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 bravo_heli_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 escape_car_blip
		ENDIF

		// cars
		LVAR_INT m_quad
		LVAR_INT m_bike
		LVAR_INT m_buggy
		LVAR_INT pheli
		LVAR_INT alpha_heli
		LVAR_INT bravo_heli
		LVAR_INT cam_heli
		LVAR_INT grabbed_car
		LVAR_INT old_car
		LVAR_INT escape_car

		// peds
		LVAR_INT pheli_pilot
		LVAR_INT alpha_pilot
		LVAR_INT bravo_pilot
		LVAR_INT alpha_ped[4]
		LVAR_INT bravo_ped[4]
		LVAR_INT old_man
		LVAR_INT old_woman


		// objects
		LVAR_INT smoke_flare_object
		LVAR_INT cam_object
		LVAR_INT contraband

		// pickup
		LVAR_INT m_pickup
		LVAR_INT m_pickup_health
		LVAR_INT m_pickup_armour

		// search lights
//		LVAR_INT alpha_search
//		LVAR_INT bravo_search

		// fx
		LVAR_INT m_fx

		// blips
		LVAR_INT location_blip
		LVAR_INT alpha_heli_blip
		LVAR_INT bravo_heli_blip
		LVAR_INT pheli_blip
		LVAR_INT contraband_blip
		LVAR_INT weapon_blip
		LVAR_INT escape_car_blip

		// flags
		LVAR_INT pheli_progress
		LVAR_INT alpha_heli_ai
		LVAR_INT bravo_heli_ai
		LVAR_INT pheli_flag
		LVAR_INT alpha_heli_goals
		LVAR_INT bravo_heli_goals
		LVAR_INT pheli_goals
		LVAR_INT alpha_ped_flag[4]
		LVAR_INT bravo_ped_flag[4]
		LVAR_INT alpha_ped_attached[4]
		LVAR_INT alpha_helis_fucked
		LVAR_INT bravo_helis_fucked
		LVAR_INT alpha_heli_control
		LVAR_INT bravo_heli_control
		VAR_INT pheli_health
		LVAR_INT total_fucked_helis
		LVAR_INT alpha_has_caught_up
		LVAR_INT total_created_helis
		VAR_INT des3_heat
		LVAR_INT cross_pressed
						  
		// timers
		LVAR_INT alpha_heli_timer
		LVAR_INT bravo_heli_timer
		LVAR_INT fed_timer
		LVAR_INT fed_timer2
		LVAR_INT pheli_health_timer

		// set flags
		pheli_progress		= 0
		alpha_heli_ai		= CHASE_PHELI
		bravo_heli_ai		= CHASE_PHELI
		pheli_flag			= 0
		alpha_heli_goals	= 0
		bravo_heli_goals	= 0
		pheli_goals			= 0
		temp_int = 0
		WHILE temp_int < 4
		alpha_ped_flag[temp_int] = 0
		bravo_ped_flag[temp_int] = 0
		alpha_ped_attached[temp_int] = 0
		temp_int++
		ENDWHILE
		alpha_helis_fucked	= 0
		bravo_helis_fucked	= 0
		alpha_heli_control	= 0
		bravo_heli_control	= 0
		pheli_health		= 0
		total_fucked_helis	= 0
		alpha_has_caught_up = 0
		
		// floats
		LVAR_FLOAT fed_distance
		LVAR_FLOAT cam_dist_z
		LVAR_FLOAT cam_off_x cam_off_y cam_off_z
		LVAR_FLOAT contraband_x contraband_y contraband_z
		LVAR_FLOAT alpha_goto_x alpha_goto_y alpha_goto_Z

		// COORDS - floats that need to be initialised each time level is played
		LVAR_FLOAT checkpoints_x[15] checkpoints_y[15] checkpoints_z[15] 

		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		
		LOAD_MISSION_TEXT DSERT3
		
		GOSUB DES3_next_stage
RETURN

// *************************************************************************************************************
//											STAGE 1 - Launch - get to location  
// *************************************************************************************************************
d3_m_stage_1:
		
		// initialisation for stage
		IF m_goals = 0

			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_AREA -691.0 941.0 13.0 10.0 TRUE 

			LOAD_CUTSCENE DESERT3
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			START_CUTSCENE
			DO_FADE 1000 FADE_IN
			WHILE NOT HAS_CUTSCENE_FINISHED
				WAIT 0
			ENDWHILE
			

			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			CLEAR_CUTSCENE
			CLEAR_AREA -701.5853 965.8546 11.3768 5.0 TRUE
			REQUEST_MODEL BFINJECT
			WHILE NOT HAS_MODEL_LOADED BFINJECT
				WAIT 0
			ENDWHILE 
			CREATE_CAR BFINJECT -701.5853 965.8546 11.3768 car
			SET_CAR_HEADING car 90.4785
			MARK_MODEL_AS_NO_LONGER_NEEDED BFINJECT
			SET_RADIO_CHANNEL  RS_TALK

			IF desert3_mission_attempts > 0
				SET_UP_SKIP	-426.8536 2213.7830 41.4297 9.9101
			ENDIF

			SET_CHAR_COORDINATES scplayer -688.7614 946.5271 12.0312 
			SET_CHAR_HEADING scplayer 94.6779
			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT
					
			FORCE_WEATHER WEATHER_EXTRASUNNY_DESERT
			//SET_WANTED_MULTIPLIER 0.5

			ADD_BLIP_FOR_COORD -429.0250 2226.2227 41.4297 location_blip
			PRINT AIR1_13 5000 1 // goto small town in desert
			
			REQUEST_MODEL BANDITO
			REQUEST_MODEL QUAD
			REQUEST_MODEL SANCHEZ
			REQUEST_MODEL HEATSEEK
			LOAD_ALL_MODELS_NOW
			WHILE NOT HAS_MODEL_LOADED BANDITO
			   OR NOT HAS_MODEL_LOADED QUAD
			   OR NOT HAS_MODEL_LOADED SANCHEZ
			   OR NOT HAS_MODEL_LOADED HEATSEEK
			   WAIT 0
			ENDWHILE 

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_PLAYER_CONTROL player1 ON

			// create smugglers cars 
			CREATE_CAR QUAD    -434.5365 2237.5671 41.8690 m_quad
			CREATE_CAR SANCHEZ -426.2288 2240.4099 41.4297 m_bike
			CREATE_CAR BANDITO -428.1476 2235.2070 42.3505 m_buggy
			SET_CAR_HEADING m_quad 291.5598 
			SET_CAR_HEADING m_bike 179.3694  
			SET_CAR_HEADING m_buggy 102.7597 
			MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
			MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
			MARK_MODEL_AS_NO_LONGER_NEEDED BANDITO

			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO  1 SOUND_DES3_AA

			m_goals++

		ENDIF
		
		// wait for player to stop in blip
		IF m_goals = 1
			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					SET_CHAR_COORDINATES scplayer -424.6452 2207.5408 41.4297
				ENDIF
			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -429.0250 2226.2227 41.4297 4.0 4.0 4.0 TRUE
				IF IS_CHAR_IN_ANY_CAR scplayer	 
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CHAR_IN_ANY_HELI scplayer
					AND NOT IS_CHAR_IN_ANY_PLANE scplayer
						IF IS_VEHICLE_ON_ALL_WHEELS car
							SET_PLAYER_CONTROL player1 OFF
							//TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000	
							m_goals++
						ENDIF
					ELSE
						SET_PLAYER_CONTROL player1 OFF
						//TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000	
						m_goals++
					ENDIF
				ELSE
					IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -429.0250 2226.2227 41.4297 4.0 4.0 4.0 FALSE
						SET_PLAYER_CONTROL player1 OFF
						m_goals++
					ENDIF
				ENDIF
			ENDIF

		ENDIF	

		// wait for car to stop
		IF m_goals = 2

			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF NOT IS_CHAR_IN_ANY_HELI scplayer
				AND NOT IS_CHAR_IN_ANY_PLANE scplayer 
					IF IS_CAR_STOPPED car
						m_goals++
					ENDIF
				ELSE
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CAR_DEAD car
						SET_CAR_COORDINATES car -429.0250 2226.2227 41.4297
					ENDIF
					m_goals++
				ENDIF
			ELSE
				m_goals++
			ENDIF

		ENDIF

		// small cut
		IF m_goals = 3
				
			IF IS_BUTTON_PRESSED PAD1 CROSS
				cross_pressed = 1
			ELSE
				cross_pressed = 0
			ENDIF

			REMOVE_BLIP location_blip
						
			CLEAR_SKIP
			CLEAR_AREA -432.3058 2234.3745 41.4697 5.0 TRUE

			SET_FIXED_CAMERA_POSITION -433.3420 2232.1760 43.6323 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -432.7942 2232.9971 43.4724 JUMP_CUT
			SWITCH_WIDESCREEN ON

			SET_PLAYER_CONTROL player1 OFF
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer -433.0047 2230.2461 40.4297 
			SET_CHAR_HEADING scplayer 341.9590 
			TASK_GO_STRAIGHT_TO_COORD scplayer -432.3058 2234.3745 41.4697 PEDMOVE_WALK 5000


			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE


			TIMERA = 0
			m_goals++
		ENDIF

		// skip
		IF m_goals > 3
			IF IS_BUTTON_PRESSED PAD1 CROSS
				IF cross_pressed = 0
					m_goals = 99
					cross_pressed = 1
				ENDIF
			ELSE
				IF cross_pressed = 1
					cross_pressed = 0
				ENDIF
			ENDIF 
		ENDIF


		// play 1st bit of audio
		IF m_goals = 4
			IF TIMERA > 1000
				$audio_string    = &DES3_AA	
				audio_sound_file = SOUND_DES3_AA
				START_NEW_SCRIPT audio_line -1 0 1 1 1
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO  2 SOUND_DES3_AB
				TIMERB = 0
				m_goals++
			ENDIF
		ENDIF

		// play 2nd bit of audio
		IF m_goals = 5
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					SET_FIXED_CAMERA_POSITION -433.6039 2235.2102 43.1100 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT     -432.6751 2234.8508 43.0197 JUMP_CUT
					$audio_string    = &DES3_AB					  
					audio_sound_file = SOUND_DES3_AB
					START_NEW_SCRIPT audio_line scplayer 0 1 2 1
				 	CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO  1 SOUND_DES3_AC
					TASK_LOOK_ABOUT scplayer 2000
					TIMERB = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// play 3rd bit of audio
		IF m_goals = 6
			IF TIMERB > 1000
				IF audio_line_is_active = 0	  
					SET_FIXED_CAMERA_POSITION -430.2061 2236.6951 42.8085  0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT     -429.3790 2237.2336 42.9687 JUMP_CUT					
					$audio_string    = &DES3_AC					  
					audio_sound_file = SOUND_DES3_AC
					START_NEW_SCRIPT audio_line -1 0 1 1 1
				 	CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO  2 SOUND_DES3_AD
					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_FOV	TRUE
					CAMERA_SET_LERP_FOV	70.0 100.0 10000 TRUE
					TIMERB = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF
			
		// play 4th bit of audio
		IF m_goals = 7
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_AD				
					audio_sound_file = SOUND_DES3_AD
					START_NEW_SCRIPT audio_line -1 0 1 2 1
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO  1 SOUND_DES3_AE
					TIMERB = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// play 5th bit of audio
		IF m_goals = 8
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_AE				
					audio_sound_file = SOUND_DES3_AE
					START_NEW_SCRIPT audio_line -1 0 1 1 1
					TIMERB = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// wait for audio to finish
		IF m_goals = 9
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF


		// wait for cutscene to finish
		IF m_goals = 99
			desert3_mission_attempts++
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			SET_CHAR_COORDINATES scplayer -432.3058 2234.3745 41.4697
			SET_CHAR_HEADING scplayer 341.9590
			MARK_CAR_AS_NO_LONGER_NEEDED car
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			GOSUB DES3_next_stage
		ENDIF

RETURN 


// *************************************************************************************************************
//										STAGE 2 - go to top of mountain  
// *************************************************************************************************************
d3_m_stage_2:
		
		// initialisation for stage
		IF m_goals = 0

			//  missles
			CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 40 -431.3719 2240.1321 42.6177 m_pickup
			ADD_BLIP_FOR_PICKUP m_pickup weapon_blip
			
			PRINT_NOW AIR1_15 5000 1 //Pick up ~g~rockets~s~.
            
			TIMERA = 0

			m_goals++
		ENDIF

		// wait for rockets to get picked up
		IF m_goals = 1
			IF HAS_PICKUP_BEEN_COLLECTED m_pickup
			OR TIMERA > 7000
				
				REMOVE_BLIP weapon_blip

				REMOVE_BLIP location_blip
				ADD_BLIP_FOR_COORD -804.5877 2377.8428 151.7558 location_blip

				PRINT_NOW AIR1_16 7000 1 //Take a suitable vehicle and go to the top of the ~y~mountain~s~.

				REQUEST_ANIMATION BOMBER
				WHILE NOT HAS_ANIMATION_LOADED BOMBER
					WAIT 0
				ENDWHILE

				REQUEST_MODEL TEARGAS 
				WHILE NOT HAS_MODEL_LOADED TEARGAS
					WAIT 0
				ENDWHILE

				m_goals++
		    ENDIF
		ENDIF
		
		// wait for player to get close
		IF m_goals = 2
			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					SET_CHAR_COORDINATES scplayer -798.1865 2419.4104 155.9362
				ENDIF
			ENDIF
			
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -804.5877 2377.8428 151.7558 4.0 4.0 4.0 TRUE
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer escape_car
					IF NOT IS_CHAR_IN_ANY_HELI scplayer
					AND NOT IS_CHAR_IN_ANY_PLANE scplayer
						IF IS_VEHICLE_ON_ALL_WHEELS escape_car
							SET_PLAYER_CONTROL player1 OFF
							//TASK_CAR_TEMP_ACTION scplayer escape_car TEMPACT_HANDBRAKESTRAIGHT 2000000	
							m_goals++
						ENDIF
					ELSE
						SET_PLAYER_CONTROL player1 OFF
						//TASK_CAR_TEMP_ACTION scplayer escape_car TEMPACT_HANDBRAKESTRAIGHT 2000000	
						m_goals++
					ENDIF
				ELSE
					IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -804.5877 2377.8428 151.7558 4.0 4.0 4.0 FALSE
						SET_PLAYER_CONTROL player1 OFF
						m_goals++
					ENDIF
				ENDIF
			ENDIF

 		ENDIF	

		// wait for car to stop
		IF m_goals = 3

			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF NOT IS_CHAR_IN_ANY_HELI scplayer
				AND NOT IS_CHAR_IN_ANY_PLANE scplayer 
					IF IS_CAR_STOPPED car
						m_goals++
					ENDIF
				ELSE
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CAR_DEAD car
						SET_CAR_COORDINATES car -804.5877 2377.8428 151.7558
					ENDIF
					m_goals++
				ENDIF
			ELSE
				m_goals++
			ENDIF


		ENDIF

		// start of cutscene - walk away from the vehicle
		IF m_goals = 4

			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			REMOVE_BLIP location_blip

			m_goals = 99

		ENDIF
		
		// exit
		IF m_goals = 99
//			REMOVE_ANIMATION BOMBER
//			MARK_MODEL_AS_NO_LONGER_NEEDED KB_BEER

			GOSUB DES3_next_stage
		ENDIF

RETURN 

// *************************************************************************************************************
//										STAGE 3 - Cutscene of heli approaching  
// *************************************************************************************************************
d3_m_stage_3:

		// load stuff for cutscene
		IF m_goals = 0

			IF IS_BUTTON_PRESSED PAD1 CROSS
				cross_pressed = 1
			ENDIF

			m_fx = 0

			SET_PLAYER_CONTROL player1 OFF

			// request stuff	  
			REQUEST_MODEL MAVERICK
			REQUEST_MODEL WMOMIB
			REQUEST_MODEL BMYMIB
			REQUEST_MODEL WMYPLT
			REQUEST_MODEL AK47
			REQUEST_MODEL LEVIATHN
			REQUEST_ANIMATION BOMBER

			LOAD_ALL_MODELS_NOW 

			WHILE NOT HAS_MODEL_LOADED MAVERICK
			   OR NOT HAS_MODEL_LOADED WMOMIB
			   OR NOT HAS_MODEL_LOADED BMYMIB
			   OR NOT HAS_MODEL_LOADED WMYPLT
			   OR NOT HAS_MODEL_LOADED AK47
			   OR NOT HAS_MODEL_LOADED LEVIATHN
				WAIT 0
			ENDWHILE


			WHILE NOT HAS_ANIMATION_LOADED BOMBER
				WAIT 0
			ENDWHILE

			REQUEST_CAR_RECORDING 530
			REQUEST_CAR_RECORDING 531
			REQUEST_CAR_RECORDING 532
			REQUEST_CAR_RECORDING 533
			REQUEST_CAR_RECORDING 534
			REQUEST_CAR_RECORDING 535
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 530
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 531
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 532
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 533
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 534
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 535
				WAIT 0
			ENDWHILE

			m_goals++
		ENDIF

		// skip
		IF m_goals > 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
				IF cross_pressed = 0
					m_goals = 99
					cross_pressed = 1
				ENDIF
			ELSE
				IF cross_pressed = 1
					cross_pressed = 0
				ENDIF
			ENDIF 
		ENDIF


		// toreno voice cutscene
		IF m_goals = 1
			

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			CLEAR_AREA -800.6110 2404.7878 155.1680 5.0 TRUE
			CLEAR_AREA -800.8441 2408.3540 155.4525 5.0	TRUE

			SET_CHAR_COORDINATES scplayer -800.6110 2404.7878 155.1680
			SET_CHAR_HEADING scplayer 342.2393
			TASK_GO_STRAIGHT_TO_COORD scplayer -800.8441 2408.3540 155.4525 PEDMOVE_WALK 10000
			
			SET_FIXED_CAMERA_POSITION -797.7917 2403.7354 157.1193 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -798.3541 2404.5415 156.9353 JUMP_CUT

//			SET_FIXED_CAMERA_POSITION -804.3873 2419.5188 157.4550 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -805.0419 2420.2747 157.4501 JUMP_CUT
			SWITCH_WIDESCREEN ON
			//TASK_TURN_CHAR_TO_FACE_COORD scplayer -809.4514 2426.5151 158.8622

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS 
				WAIT 0
			ENDWHILE

			m_goals++
			TIMERA =0
		ENDIF

		// plant smoke canister
		IF m_goals = 2
			IF LOCATE_CHAR_ON_FOOT_3D scplayer -800.8441 2408.3540 155.4525 1.0 1.0 2.0 FALSE
			OR TIMERA > 5000
				m_goals++
			ENDIF
		ENDIF

		// make cj set smoke flare
		IF m_goals = 3

			CREATE_OBJECT TEARGAS 0.0 0.0 0.0 smoke_flare_object
			OPEN_SEQUENCE_TASK temp_seq		 
				TASK_PICK_UP_OBJECT -1 smoke_flare_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE 
				TASK_PLAY_ANIM -1 BOM_plant_In BOMBER   4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 BOM_plant_Loop BOMBER 4.0 FALSE FALSE FALSE FALSE -1	 
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK scplayer temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
			
			m_goals++
		ENDIF

		// wait for player to play anim
		IF m_goals = 4
			IF IS_CHAR_PLAYING_ANIM scplayer BOM_plant_Loop
				SET_FIXED_CAMERA_POSITION -789.7653 2416.8945 163.5839 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 	  -790.3218 2416.1240 163.2729 JUMP_CUT	 
				m_goals++
			ENDIF
		ENDIF
		
		

		// wait for player to finish planting smoke flare
		IF m_goals = 5
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
			IF temp_int = FINISHED_TASK
				DROP_OBJECT scplayer FALSE
				FREEZE_OBJECT_POSITION smoke_flare_object TRUE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 0.0 x y z
				GET_GROUND_Z_FOR_3D_COORD x y z z
				z += 0.05
				SET_OBJECT_COORDINATES smoke_flare_object x y z
				CREATE_FX_SYSTEM_ON_OBJECT smoke_flare smoke_flare_object 0.0 0.0 0.1 TRUE m_fx
				PLAY_FX_SYSTEM m_fx
				CLEAR_CHAR_TASKS scplayer
				//SET_PLAYER_CONTROL player1 ON
				TIMERA = 0
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO  1 SOUND_DES3_BA
				m_goals++
			ENDIF
		ENDIF


		// wait 
		IF m_goals = 6
			IF TIMERA > 4000
				m_goals++
			ENDIF
		ENDIF

		// look at hut with speaker
		IF m_goals = 7
			
			SET_FIXED_CAMERA_POSITION -779.9253 2418.3381 157.8122 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 	  -778.9708 2418.6294 157.7476 JUMP_CUT
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer -786.2731 2416.1128 156.0893
			SET_CHAR_HEADING scplayer 284.9980

			TASK_GO_STRAIGHT_TO_COORD scplayer -782.2095 2417.0266 156.1289 PEDMOVE_WALK 5000

			CAMERA_RESET_NEW_SCRIPTABLES
			CAMERA_PERSIST_FOV	TRUE
			CAMERA_SET_LERP_FOV	70.0 100.0 10000 TRUE

			

			TIMERA = 0
			m_goals++

		ENDIF
			
		// play audio
		IF m_goals = 8
			IF TIMERA > 1000
				$audio_string    = &DES3_BA	
				audio_sound_file = SOUND_DES3_BA
				START_NEW_SCRIPT audio_line -1 0 1 1 1
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO  2 SOUND_DES3_BB
				TASK_LOOK_AT_COORD scplayer -809.4514 2426.5151 158.8622 99999
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		// play audio
		IF m_goals = 9
			IF TIMERA > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_BB	
					audio_sound_file = SOUND_DES3_BB
					START_NEW_SCRIPT audio_line scplayer 0 1 2 1
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO  1 SOUND_DES3_BC
					TIMERA = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// wait for audio to finish
		IF m_goals = 10
			IF TIMERA > 1000
				IF audio_line_is_active = 0
					m_goals++
				ENDIF
			ENDIF	
		ENDIF

		// show heli flying in (load up stuff first)
		IF m_goals = 11

			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			// turn radio station off
			LVAR_INT stored_radio_station
			GET_RADIO_CHANNEL stored_radio_station
			SET_RADIO_CHANNEL RS_OFF
	

			IF DOES_VEHICLE_EXIST m_quad
				IF NOT IS_CAR_DEAD m_quad
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m_quad 100.0 100.0 FALSE
						MARK_CAR_AS_NO_LONGER_NEEDED m_quad
					ENDIF
				ENDIF
			ENDIF
			IF DOES_VEHICLE_EXIST m_buggy
				IF NOT IS_CAR_DEAD m_buggy
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m_buggy 100.0 100.0 FALSE
						MARK_CAR_AS_NO_LONGER_NEEDED m_buggy
					ENDIF
				ENDIF
			ENDIF
			IF DOES_VEHICLE_EXIST m_bike
				IF NOT IS_CAR_DEAD m_bike
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m_bike 100.0 100.0 FALSE
						MARK_CAR_AS_NO_LONGER_NEEDED m_bike
					ENDIF
				ENDIF
			ENDIF

//			// request stuff	  
//			REQUEST_MODEL MAVERICK
//			REQUEST_MODEL WMOMIB
//			REQUEST_MODEL BMYMIB
//			REQUEST_MODEL WMYPLT
//			REQUEST_MODEL AK47
//			REQUEST_MODEL LEVIATHN
//			WHILE NOT HAS_MODEL_LOADED MAVERICK
//			   OR NOT HAS_MODEL_LOADED WMOMIB
//			   OR NOT HAS_MODEL_LOADED BMYMIB
//			   OR NOT HAS_MODEL_LOADED WMYPLT
//			   OR NOT HAS_MODEL_LOADED AK47
//			   OR NOT HAS_MODEL_LOADED LEVIATHN
//				WAIT 0
//			ENDWHILE
//
//			REQUEST_CAR_RECORDING 530
//			REQUEST_CAR_RECORDING 531
//			REQUEST_CAR_RECORDING 532
//			REQUEST_CAR_RECORDING 533
//			REQUEST_CAR_RECORDING 534
//			REQUEST_CAR_RECORDING 535
//			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 530
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 531
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 532
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 533
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 534
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 535
//				WAIT 0
//			ENDWHILE

			//LOAD_SCENE -1406.5287 1962.9375 109.0392
			x = -778.9708 + 779.9253 
			y = 2418.6294 - 2418.3381 
			GET_HEADING_FROM_VECTOR_2D x y heading
			LOAD_SCENE_IN_DIRECTION -1373.0 2004.0 108.0 heading
			

			GOSUB create_pheli

			IF NOT IS_CAR_DEAD pheli

//				// camera stuff
//				CREATE_OBJECT CARDBOARDBOX 0.0 0.0 0.0 cam_object
//				ATTACH_OBJECT_TO_CAR cam_object	pheli 5.0 -10.0 0.0 0.0 0.0 0.0
//				SET_OBJECT_COLLISION cam_object FALSE
//				SET_OBJECT_VISIBLE cam_object FALSE

				START_PLAYBACK_RECORDED_CAR pheli 530
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 0.0 -20.0 0.0 x y z
				CREATE_CAR LEVIATHN x y z cam_heli
				SET_HELI_BLADES_FULL_SPEED cam_heli
				START_PLAYBACK_RECORDED_CAR cam_heli 531
				SET_CAR_VISIBLE cam_heli FALSE

				SWITCH_WIDESCREEN ON
//				ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_OBJECT cam_heli 0.0 0.0 0.0 cam_object 0.0 JUMP_CUT
				
				WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer pheli 0
			ENDIF

			pheli_progress = 0
			
			dialogue_flag = 0

			// set camera position
			IF NOT IS_CAR_DEAD cam_heli
				DISABLE_HELI_AUDIO cam_heli TRUE
				GET_CAR_COORDINATES cam_heli x y z
				SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0  
			ENDIF
			// set camera point at
			IF NOT IS_CAR_DEAD pheli
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 5.0 -10.0 0.0 x y z
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
			ENDIF


			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				// set camera position
				IF NOT IS_CAR_DEAD cam_heli
					GET_CAR_COORDINATES cam_heli x y z
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0  
				ENDIF
				// set camera point at
				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 5.0 -10.0 0.0 x y z
					POINT_CAMERA_AT_POINT x y z JUMP_CUT
				ENDIF
				WAIT 0
			ENDWHILE

			TIMERA = 0
			m_goals++
		ENDIF

//		// skip 
//		IF m_goals > 4
//		AND TIMERA > 3000
//			IF IS_BUTTON_PRESSED PAD1 CROSS
//				m_goals = 99
//			ENDIF
//		ENDIF

		// cut 1
		IF m_goals = 12
	

			// set camera position
			IF NOT IS_CAR_DEAD cam_heli
				GET_CAR_COORDINATES cam_heli x y z
				SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0  
			ENDIF
			// set camera point at
			IF NOT IS_CAR_DEAD pheli
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 5.0 -10.0 0.0 x y z
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
			ENDIF

			//IF TIMERA > 9800	
			IF TIMERA > 11700		  				
				DO_FADE 300 FADE_OUT
				TIMERA = 0
				m_goals++
			ENDIF

		ENDIF

		IF m_goals = 13
			IF GET_FADING_STATUS

				// set camera position
				IF NOT IS_CAR_DEAD cam_heli
					GET_CAR_COORDINATES cam_heli x y z
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0  
				ENDIF
				// set camera point at
				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 5.0 -10.0 0.0 x y z
					POINT_CAMERA_AT_POINT x y z JUMP_CUT
				ENDIF

			ELSE
				SET_FIXED_CAMERA_POSITION -1047.6195 2164.9250 88.4215 0.0 0.0 0.0
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF


		// make 2 baddie heli's appear from below shooting.
		IF m_goals = 14				

			// stop playback (if one is playing)
			IF NOT IS_CAR_DEAD pheli
				IF IS_PLAYBACK_GOING_ON_FOR_CAR pheli
					STOP_PLAYBACK_RECORDED_CAR pheli
				ENDIF
			ENDIF
								
			GOSUB create_alpha_heli
			GOSUB create_bravo_heli

			IF NOT IS_CAR_DEAD alpha_heli	  
				START_PLAYBACK_RECORDED_CAR alpha_heli 532
				PAUSE_PLAYBACK_RECORDED_CAR alpha_heli
			ENDIF

			IF NOT IS_CAR_DEAD bravo_heli
				START_PLAYBACK_RECORDED_CAR bravo_heli 533
				PAUSE_PLAYBACK_RECORDED_CAR bravo_heli
			ENDIF
			
			IF NOT IS_CAR_DEAD pheli
				START_PLAYBACK_RECORDED_CAR pheli 535 
				PAUSE_PLAYBACK_RECORDED_CAR pheli
			ENDIF  
			
			SET_FIXED_CAMERA_POSITION -798.5942 2436.1836 172.1772 0.0 0.0 0.0
		   	IF NOT IS_CAR_DEAD pheli
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
			ENDIF
			POINT_CAMERA_AT_POINT x y z JUMP_CUT

			LOAD_SCENE_IN_DIRECTION -778.0 2465.0 168.0 45.0
			
			IF NOT IS_CAR_DEAD alpha_heli
				UNPAUSE_PLAYBACK_RECORDED_CAR alpha_heli
			ENDIF
			IF NOT IS_CAR_DEAD bravo_heli
				UNPAUSE_PLAYBACK_RECORDED_CAR bravo_heli
			ENDIF
			IF NOT IS_CAR_DEAD pheli
				UNPAUSE_PLAYBACK_RECORDED_CAR pheli
			ENDIF


			DO_FADE 300 FADE_IN
			WHILE GET_FADING_STATUS
				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
				ENDIF
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
				WAIT 0
			ENDWHILE


		   	dialogue_flag = 0
			TIMERB = 0
			TIMERA = 0
			m_goals++
		ENDIF


		IF m_goals = 15	
			
			//IF TIMERA < 16000
			IF NOT dialogue_flag = 7 

				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
				ENDIF
				POINT_CAMERA_AT_POINT x y z JUMP_CUT

				IF TIMERB > 1000
					SWITCH dialogue_flag
						CASE 0
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BC	
								audio_sound_file = SOUND_DES3_BC
								START_NEW_SCRIPT audio_line -1 0 1 1 1
								CLEAR_MISSION_AUDIO 2
								LOAD_MISSION_AUDIO  2 SOUND_DES3_BD
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 1
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BD	
								audio_sound_file = SOUND_DES3_BD
								START_NEW_SCRIPT audio_line -1 0 1 2 1
								CLEAR_MISSION_AUDIO 1
								LOAD_MISSION_AUDIO  1 SOUND_DES3_BE
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 2
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BE	
								audio_sound_file = SOUND_DES3_BE
								START_NEW_SCRIPT audio_line -1 0 1 1 1
								CLEAR_MISSION_AUDIO 2
								LOAD_MISSION_AUDIO  2 SOUND_DES3_BF
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 3
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BF	
								audio_sound_file = SOUND_DES3_BF
								START_NEW_SCRIPT audio_line -1 0 1 2 1
								CLEAR_MISSION_AUDIO 1
								LOAD_MISSION_AUDIO  1 SOUND_DES3_BG
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 4
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BG	
								audio_sound_file = SOUND_DES3_BG
								START_NEW_SCRIPT audio_line scplayer 0 1 1 1
								CLEAR_MISSION_AUDIO 2
								LOAD_MISSION_AUDIO  2 SOUND_DES3_BH
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 5
							IF audio_line_is_active = 0
								$audio_string    = &DES3_BH	
								audio_sound_file = SOUND_DES3_BH
								START_NEW_SCRIPT audio_line -1 0 1 2 1
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK
						CASE 6
							IF audio_line_is_active = 0
								TIMERB = 0
								dialogue_flag++
							ENDIF
						BREAK

					ENDSWITCH
				ENDIF

			ELSE
				IF TIMERB > 2000

					IF NOT IS_CAR_DEAD pheli
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
					ENDIF
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					IF NOT IS_CAR_DEAD alpha_heli
						IF IS_PLAYBACK_GOING_ON_FOR_CAR alpha_heli
							STOP_PLAYBACK_RECORDED_CAR alpha_heli
						ENDIF	
					ENDIF
					IF NOT IS_CAR_DEAD bravo_heli
						IF IS_PLAYBACK_GOING_ON_FOR_CAR bravo_heli
							STOP_PLAYBACK_RECORDED_CAR bravo_heli
						ENDIF
					ENDIF
					IF NOT IS_CAR_DEAD pheli
						IF IS_PLAYBACK_GOING_ON_FOR_CAR pheli
							STOP_PLAYBACK_RECORDED_CAR pheli
						ENDIF
					ENDIF
					DO_FADE 150 FADE_OUT
					m_goals++
				ELSE
	
					IF NOT IS_CAR_DEAD pheli
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
					ENDIF
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

				ENDIF
			ENDIF
 
		ENDIF
		
		IF m_goals = 16
			IF GET_FADING_STATUS
				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -15.0 5.0 -3.0 x y z
				ENDIF
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
			ELSE
				m_goals = 99
			ENDIF
		ENDIF

		IF m_goals = 99

			DO_FADE 0 FADE_OUT
	
			IF DOES_OBJECT_EXIST smoke_flare_object
				IF IS_CHAR_HOLDING_OBJECT scplayer smoke_flare_object
					DROP_OBJECT	scplayer TRUE
				ENDIF
				IF m_fx = 0
					CREATE_FX_SYSTEM_ON_OBJECT smoke_flare smoke_flare_object 0.0 0.0 0.1 TRUE m_fx
					PLAY_FX_SYSTEM m_fx
				ENDIF
			ELSE
				x =	-800.8441 
				y =	2408.3540 
				z =	155.4525
				GET_GROUND_Z_FOR_3D_COORD x y z z
				z += 1.0
				CREATE_OBJECT TEARGAS x y z smoke_flare_object
				CREATE_FX_SYSTEM_ON_OBJECT smoke_flare smoke_flare_object 0.0 0.0 0.1 TRUE m_fx
				PLAY_FX_SYSTEM m_fx
			ENDIF

			CAMERA_RESET_NEW_SCRIPTABLES
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			//SET_CHAR_COORDINATES scplayer -789.7678 2416.9163 156.0220 
			LOAD_SCENE -806.4670 2420.7495 155.9102
			SET_CHAR_COORDINATES scplayer -806.4670 2420.7495 155.9102
			SET_CHAR_HEADING scplayer 190.8497

						
			IF DOES_VEHICLE_EXIST cam_heli
				DELETE_CAR cam_heli
			ENDIF
			
			GOSUB delete_pheli
			GOSUB delete_alpha_heli
			GOSUB delete_bravo_heli

		
			GOSUB DES3_next_stage
		ENDIF
	

RETURN

// *************************************************************************************************************
//										STAGE 4 - Game section  
// *************************************************************************************************************
d3_m_stage_4:

		IF m_goals = 0
			
			// request stuff
			REQUEST_MODEL MAVERICK
			REQUEST_MODEL WMOMIB
			REQUEST_MODEL BMYMIB
			REQUEST_MODEL WMYPLT
			REQUEST_MODEL AK47
			REQUEST_MODEL LEVIATHN
			WHILE NOT HAS_MODEL_LOADED MAVERICK
			   OR NOT HAS_MODEL_LOADED WMOMIB
			   OR NOT HAS_MODEL_LOADED BMYMIB
			   OR NOT HAS_MODEL_LOADED WMYPLT
			   OR NOT HAS_MODEL_LOADED AK47
			   OR NOT HAS_MODEL_LOADED LEVIATHN
				WAIT 0
			ENDWHILE

			total_created_helis = 0
			
			alpha_x = -780.6791 
			alpha_y = 2482.3389 
			alpha_z = 170.8723
			alpha_h = 151.9289
			GOSUB create_alpha_heli
	
			bravo_x = -750.9330 
			bravo_y = 2465.2854 
			bravo_z = 180.7247
			bravo_h = 144.1498
			GOSUB create_bravo_heli

			pheli_x = -772.2108
			pheli_y = 2419.8433
			pheli_z = 174.0836
			pheli_h	= 176.9396
			GOSUB create_pheli

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer -789.7678 2416.9163 156.0220 
			SET_CHAR_HEADING scplayer 190.8497

			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			TIMERA = 0

			// initailise heli's flags
			pheli_flag = 0
			pheli_goals = 0
			alpha_heli_ai = DROP_GUYS_NEAR
			alpha_heli_goals = 0
			bravo_heli_ai = CHASE_PHELI
			bravo_heli_goals = 0

			FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
			
			pheli_health = 100

			PRINT_NOW AIR1_06 7000 1 // protect the contraband heli from the feds
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING pheli_health COUNTER_DISPLAY_BAR AIR1_17

			// set cars as non targetable
			IF NOT IS_CAR_DEAD m_quad
				VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE m_quad FALSE
			ENDIF
			IF NOT IS_CAR_DEAD m_bike
				VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE m_bike FALSE
			ENDIF
			IF NOT IS_CAR_DEAD m_buggy
				VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE m_buggy FALSE	
			ENDIF
			IF NOT IS_CAR_DEAD pheli
				VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE pheli FALSE
			ENDIF		

			// pickups
			CREATE_PICKUP HEALTH PICKUP_ONCE -770.7520 2423.6301 157.0753  		m_pickup_health
			//CREATE_PICKUP  PICKUP_ONCE -808.1523 2430.7883 156.9872  	m_pickup_armour
			CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 20 -808.1523 2430.7883 156.9872 m_pickup_armour

			SET_MAX_FIRE_GENERATIONS 3
			SET_WANTED_MULTIPLIER 0.5
			SET_MAX_WANTED_LEVEL 2
			SWITCH_POLICE_HELIS	FALSE

			LVAR_INT dropping_off_heli
			dropping_off_heli = 0

			alpha_heli_control = 1
			bravo_heli_control = 1

			SET_RADIO_CHANNEL stored_radio_station

			DO_FADE 150 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			m_goals++
		ENDIF


		// update health of pheli
		IF NOT IS_CAR_DEAD pheli
			IF pheli_health_timer > 50
			
				GET_CAR_HEALTH pheli temp_int
				
					IF temp_int > 100

						// check if other heli is nearby
						temp_int = 0
						IF NOT IS_CAR_DEAD alpha_heli
							GET_CAR_COORDINATES alpha_heli x y z
							IF LOCATE_CAR_3D pheli x y z 40.0 40.0 40.0 FALSE
								temp_int = 1
							ENDIF
						ENDIF
						IF temp_int = 0
							IF NOT IS_CAR_DEAD bravo_heli
								GET_CAR_COORDINATES bravo_heli x y z
								IF LOCATE_CAR_3D pheli x y z 40.0 40.0 40.0 FALSE
									temp_int = 1
								ENDIF
							ENDIF
						ENDIF

						IF temp_int = 1
							GET_CAR_HEALTH pheli temp_int
							temp_int += -5
							IF temp_int < 0
								temp_int = 1
							ENDIF
							SET_CAR_HEALTH pheli temp_int
						ENDIF

					ENDIF
				pheli_health_timer = 0

			ENDIF
			
			GET_CAR_HEALTH pheli temp_int
			temp_float =# temp_int
			temp_float /= 5000.0
			temp_float *= 100.0
			pheli_health =# temp_float

		ENDIF


		// players heli goes round in a circle
		IF m_goals = 1

			// contol ai - first and second last heli will attack player
			IF total_created_helis = 5
				IF dropping_off_heli = 0
					alpha_heli_ai = DROP_GUYS_NEAR
					dropping_off_heli++
					//WRITE_DEBUG HELI_TOLD_TO_DROP_GUYS
				ENDIF
			ENDIF

			// create alpha helis
			IF total_created_helis < 5
				IF alpha_heli = 0
					IF alpha_heli_control = 0
						alpha_x = -344.9471 
						alpha_y = 2724.4214 
						alpha_z = 186.0
						alpha_h = 110.0
						GOSUB create_alpha_heli
						alpha_heli_ai = CHASE_PHELI
						alpha_heli_goals = 0
						alpha_heli_control++
						TIMERA = 0
					ENDIF
				ENDIF
			ENDIF

			// create bravo helis
			IF total_created_helis < 5
				IF bravo_heli = 0
					IF bravo_heli_control = 0
						bravo_x = -324.9471 // 204.0  	  
						bravo_y = 2704.4214 //2816.0  
						bravo_z = 186.0
						bravo_h = 110.0
						GOSUB create_bravo_heli
						bravo_heli_ai = CHASE_PHELI
						bravo_heli_goals = 0
						bravo_heli_control++
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF





			// count fucked helis ------------------------------------------------------
			IF alpha_heli_control > 0
				IF alpha_heli = 0
					alpha_helis_fucked++ 
					total_fucked_helis++
					alpha_heli_control = 0
					TIMERA =  0			
				ENDIF
			ENDIF
			IF bravo_heli_control > 0
				IF bravo_heli = 0
					bravo_helis_fucked++
					total_fucked_helis++
					bravo_heli_control = 0
					TIMERB = 0
				ENDIF
			ENDIF

			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					total_fucked_helis = 5
				ENDIF
			ENDIF	
			
			// passed this section
			IF total_fucked_helis = 5
				TIMERA = 0
				m_goals++	
			ENDIF	


//			// process heli's ai
			GOSUB process_pheli_ai
			GOSUB process_alpha_heli_ai
	   		GOSUB process_bravo_heli_ai

		ENDIF

		IF m_goals = 2
			IF TIMERA > 2000 
				//SET_MAX_WANTED_LEVEL 6
				m_goals = 99
			ENDIF
		ENDIF

		IF m_goals = 99	

			SET_WANTED_MULTIPLIER 1.0
			SET_MAX_WANTED_LEVEL 6
			CLEAR_ONSCREEN_COUNTER pheli_health
		
			GOSUB delete_pheli
			GOSUB delete_alpha_heli
			GOSUB delete_bravo_heli

			GOSUB DES3_next_stage
		ENDIF
	

RETURN

// *************************************************************************************************************
//								STAGE 5 - Quick cut of pheli flying to drop off point  
// *************************************************************************************************************
d3_m_stage_5:

		IF m_goals = 0

			// request stuff
			REQUEST_MODEL LEVIATHN
			REQUEST_MODEL WMYPLT
			REQUEST_MODEL AK47
			REQUEST_MODEL KMB_HOLDALL
			REQUEST_MODEL KMB_PARACHUTE
			REQUEST_MODEL JOURNEY
			REQUEST_MODEL WMOPREA
			REQUEST_MODEL SWFOPRO
			REQUEST_MODEL BANDITO
			WHILE NOT HAS_MODEL_LOADED LEVIATHN
			   OR NOT HAS_MODEL_LOADED WMYPLT
			   OR NOT HAS_MODEL_LOADED AK47
			   OR NOT HAS_MODEL_LOADED KMB_HOLDALL
			   OR NOT HAS_MODEL_LOADED KMB_PARACHUTE
				WAIT 0
			ENDWHILE
			WHILE NOT HAS_MODEL_LOADED JOURNEY
			   OR NOT HAS_MODEL_LOADED BANDITO
				WAIT 0
			ENDWHILE

			REQUEST_CAR_RECORDING 536
			REQUEST_CAR_RECORDING 537
			REQUEST_CAR_RECORDING 538
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 536
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 537
			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 538
				WAIT 0
			ENDWHILE 

			// create pheli
			GOSUB create_pheli

			IF NOT IS_CAR_DEAD pheli
				START_PLAYBACK_RECORDED_CAR pheli 536
			ENDIF

			// take this out
//			SET_CHAR_COORDINATES scplayer -789.7678 2416.9163 156.0220 
//			SET_CHAR_HEADING scplayer 190.8497

			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
			FREEZE_CHAR_POSITION scplayer TRUE
			//MAKE_PLAYER_SAFE_FOR_CUTSCENE player1

			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -720.8120 2476.1816 171.2749 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -721.6284 2475.6072 171.2184 JUMP_CUT
			TIMERA = 0

			m_goals++
		ENDIF

		// skip 
		IF m_goals > 0
		AND TIMERA > 3000
			IF IS_BUTTON_PRESSED PAD1 CROSS
				m_goals = 99
			ENDIF
		ENDIF

		// wait for 1st shot to finish
		IF m_goals = 1

			IF TIMERA < 5000
				IF NOT IS_CAR_DEAD pheli
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 3.0 -5.0 -2.0 x y z
					POINT_CAMERA_AT_POINT x y z JUMP_CUT
				ENDIF
			ELSE
				DO_FADE 300 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD pheli
					IF IS_PLAYBACK_GOING_ON_FOR_CAR pheli
						STOP_PLAYBACK_RECORDED_CAR pheli
					ENDIF
				ENDIF
				m_goals++
			ENDIF

		ENDIF

		// setup second shot
		IF m_goals = 2
			IF NOT IS_CAR_DEAD pheli

				DO_FADE 300 FADE_IN
				
				//START_PLAYBACK_RECORDED_CAR pheli 538
				START_PLAYBACK_RECORDED_CAR_LOOPED pheli 538
				//DISPLAY_PLAYBACK_RECORDED_CAR pheli PLAYBACK_DISPLAY_MODE_WHOLELINE

				SET_FIXED_CAMERA_POSITION 247.7597 2515.9370 82.7789 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 246.7721 2515.8936 82.6276 JUMP_CUT

				SKIP_IN_PLAYBACK_RECORDED_CAR pheli 100.0

				CREATE_OBJECT KMB_HOLDALL 0.0 0.0 0.0 contraband 
				ATTACH_OBJECT_TO_CAR contraband pheli 0.0 -2.0 -0.5 0.0 0.0 0.0
				SET_OBJECT_VISIBLE contraband FALSE
				//SET_OBJECT_COLLISION contraband FALSE

				CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 cam_object
				ATTACH_OBJECT_TO_CAR cam_object pheli 0.0 0.0 -2.5 0.0 0.0 0.0
				SET_OBJECT_VISIBLE cam_object FALSE
				//SET_OBJECT_COLLISION cam_object FALSE

				TIMERA = 0

				cam_off_x = 2.0
				cam_off_y = -7.0
				cam_off_z = 1.0

				m_goals++
			ENDIF
		ENDIF

		// wait for heli to hit mark
		IF m_goals = 3

			IF DOES_OBJECT_EXIST cam_object
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS cam_object cam_off_x cam_off_y cam_off_z x y z 
			ENDIF
			POINT_CAMERA_AT_POINT x y z JUMP_CUT

			// drop bag
			IF TIMERA > 7000

				SET_FIXED_CAMERA_POSITION 296.2388 2480.5535 97.5696 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 295.5632 2481.1533 97.1410 JUMP_CUT

				IF DOES_OBJECT_EXIST contraband
					DETACH_OBJECT contraband 0.0 0.0 0.0 FALSE 
				ENDIF
				SET_OBJECT_VISIBLE contraband TRUE

				IF DOES_OBJECT_EXIST cam_object
					DETACH_OBJECT cam_object 0.0 0.0 0.0 FALSE 
				ENDIF

				TIMERA = 0
				m_goals++
			ENDIF

		ENDIF

		// drop bag
		IF m_goals = 4
			
			IF TIMERA > 1200

				swing_dest_x =   264.2501 
				swing_dest_y =   2877.0112
				swing_dest_z =   13.7096	
				GOSUB initialise_swinging_bag
				IF DOES_OBJECT_EXIST contraband
					GET_OBJECT_COORDINATES contraband x y z
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION x y z SOUND_PARACHUTE_OPEN
				ENDIF
				TIMERA = 0

				m_goals++
			ENDIF

			IF DOES_OBJECT_EXIST cam_object
				GET_OBJECT_VELOCITY cam_object x y z
				z *= -0.1
				ADD_TO_OBJECT_VELOCITY cam_object 0.0 0.0 z
			ENDIF

		ENDIF

		// make bag swing/fall to destination
		IF m_goals = 5

			GOSUB update_swinging_bag

			IF TIMERA > 5000
				m_goals = 99
			ENDIF

			IF DOES_OBJECT_EXIST cam_object
				GET_OBJECT_VELOCITY cam_object x y z
				z *= -0.5
				ADD_TO_OBJECT_VELOCITY cam_object 0.0 0.0 z
			ENDIF

		ENDIF

		IF m_goals = 99
			
			DO_FADE 0 FADE_IN

			IF NOT IS_CAR_DEAD escape_car
				ADD_BLIP_FOR_CAR escape_car escape_car_blip
			ELSE
				MARK_CAR_AS_NO_LONGER_NEEDED escape_car
				CLEAR_AREA -804.5877 2377.8428 151.7558 5.0	TRUE
				CREATE_CAR BANDITO -804.5877 2377.8428 151.7558 escape_car
				ADD_BLIP_FOR_CAR escape_car escape_car_blip
			ENDIF

			GET_CHAR_COORDINATES scplayer x y z
			CLEAR_AREA x y z 20.0 TRUE
			
			SET_BLIP_AS_FRIENDLY escape_car_blip TRUE
			MARK_MODEL_AS_NO_LONGER_NEEDED BANDITO

			CREATE_CAR JOURNEY -680.7584 2466.0808 114.9057 old_car
			SET_CAR_HEADING old_car 75.9390
			CREATE_CHAR_INSIDE_CAR old_car   PEDTYPE_CIVMALE   WMOPREA old_man
			CREATE_CHAR_AS_PASSENGER old_car PEDTYPE_CIVFEMALE SWFOPRO 0 old_woman
			TASK_CAR_DRIVE_TO_COORD old_man old_car -726.9384 2357.3877 125.5138 10.0 MODE_NORMAL TRUE DRIVINGMODE_AVOIDCARS
			MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA
			MARK_MODEL_AS_NO_LONGER_NEEDED SWFOPRO
			

			SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
			FREEZE_CHAR_POSITION scplayer FALSE
			//CLEAR_CUTSCENE 
			
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			WAIT 0

			DELETE_OBJECT contraband
			DELETE_OBJECT cam_object

			GOSUB delete_pheli
			GOSUB DES3_next_stage
		ENDIF


//		// wait for heli to hit mark
//		IF m_goals = 3
//			IF NOT IS_CAR_DEAD pheli
//				IF LOCATE_CAR_2D pheli 409.8435 2499.3025  5.0 5.0 FALSE 
//					HELI_GOTO_COORDS pheli 409.8435 2499.3025 24.6574 10.0 10.0
//					m_goals++
//				ENDIF
//			ENDIF
//		ENDIF
//
//		IF m_goals = 4
//			IF NOT IS_CAR_DEAD pheli
//				GET_CAR_SPEED pheli temp_float
//				IF temp_float < 0.5
//					GET_CAR_COORDINATES pheli x y z
//					IF z < 27.0
//						TIMERA = 0
//						m_goals++
//					ENDIF
//				ELSE
//					GET_CAR_SPEED_VECTOR pheli vec_x vec_y	vec_z
//					vec_x *= -0.001
//					vec_y *= -0.001
//					vec_z *= -0.001
//					APPLY_FORCE_TO_CAR pheli vec_x vec_y vec_z 0.0 0.0 0.0
//				ENDIF				
//			ENDIF
//		ENDIF
//
//		// drop bag
//		IF m_goals = 5
//			IF NOT IS_CAR_DEAD pheli
//				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 0.0 0.0 -2.0 x y z
//				GET_CAR_HEADING pheli heading
//				CREATE_OBJECT_NO_OFFSET KMB_HOLDALL x y z contraband
//				SET_OBJECT_HEADING contraband heading
//				SET_OBJECT_DYNAMIC contraband TRUE
//				SET_OBJECT_VELOCITY contraband 0.0 0.0 -1.0
//				TIMERA = 0
//				m_goals++ 
//			ENDIF
//		ENDIF
//
//		// fly off
//		IF m_goals = 6
//			IF TIMERA > 1500
//				IF NOT IS_CAR_DEAD pheli
//					HELI_GOTO_COORDS pheli 585.4891 2239.5776 30.0 30.0 30.0
//					ACTIVATE_HELI_SPEED_CHEAT pheli 5
//					m_goals++
//					TIMERA = 0		
//				ENDIF
//			ENDIF
//		ENDIF
//
//		IF m_goals = 7
//			IF TIMERA > 3000
////				IF NOT IS_CAR_DEAD pheli
////					STOP_RECORDING_CAR pheli
////					m_goals++
////				ENDIF
//			ENDIF
//		ENDIF

		
		
RETURN


// *************************************************************************************************************
//							STAGE 6 - Retrieve package and take it home 
// *************************************************************************************************************
d3_m_stage_6:

		IF m_goals = 0
		
			PRINT_NOW AIR1_09 7000 1 // get the contraband
				
			REQUEST_MODEL MAVERICK
			REQUEST_MODEL WMYPLT
			REQUEST_MODEL AK47
			REQUEST_MODEL KMB_HOLDALL
			REQUEST_MODEL KMB_PARACHUTE
			REQUEST_MODEL WMOMIB
			REQUEST_MODEL BMYMIB
			WHILE NOT HAS_MODEL_LOADED MAVERICK
			   OR NOT HAS_MODEL_LOADED WMYPLT
			   OR NOT HAS_MODEL_LOADED AK47
			   OR NOT HAS_MODEL_LOADED KMB_HOLDALL
			   OR NOT HAS_MODEL_LOADED WMOMIB
			   OR NOT HAS_MODEL_LOADED BMYMIB
				WAIT 0
			ENDWHILE
			WHILE NOT HAS_MODEL_LOADED KMB_PARACHUTE
				WAIT 0
			ENDWHILE

			// take this out
//			SET_CHAR_COORDINATES scplayer -789.7678 2416.9163 156.0220 
//			SET_CHAR_HEADING scplayer 190.8497

			SWING_START_X =  216.9669 		//		273.0106 
			SWING_START_Y =  2689.1492		//		2503.3118 
			SWING_START_Z =  90.0			//		71.8471
			swing_dest_x =   264.2501 		//		409.8586  			   264.2501 2877.0112 12.7096
			swing_dest_y =   2877.0112		//		2516.3965 		
			swing_dest_z =   13.7096		//		16.0 // 15.4918
			IF DOES_OBJECT_EXIST contraband
				DELETE_OBJECT contraband
			ENDIF
			GOSUB initialise_swinging_bag
								   

//SWING_START_X = 271.1388 
//SWING_START_Y = 2503.5127 
//SWING_START_Z = 77.9865 
//
//SWING_START_X = 216.9669 
//SWING_START_Y = 2689.1492 
//SWING_START_Z = 199.5032 

			
			ADD_BLIP_FOR_OBJECT contraband contraband_blip
			SET_OBJECT_SCALE contraband 0.3
			SWITCH_POLICE_HELIS	TRUE
			m_goals++
		ENDIF

		// wait for player to pickup contraband
		IF m_goals	= 1

			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					SET_CHAR_COORDINATES scplayer 417.0 2493.0 27.83
				ENDIF
			ENDIF

			IF please_update_swinging_bag = 0
				IF DOES_OBJECT_EXIST contraband
					GET_OBJECT_COORDINATES contraband x y z
					IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 1.5 1.5 1.5 FALSE
						IF DOES_BLIP_EXIST escape_car_blip
							REMOVE_BLIP escape_car_blip
						ENDIF
						DELETE_OBJECT contraband
						REMOVE_BLIP contraband_blip
						TIMERA = 0
						m_goals++
					ENDIF
				ENDIF
			ENDIF

		ENDIF	   

		// show final cutscene bit
		IF m_goals = 2
		
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON

			SET_FIXED_CAMERA_POSITION  269.6016 2868.4641 15.2584 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 269.2238 2869.3726 15.0806 JUMP_CUT
										  
			SET_CHAR_COORDINATES scplayer 265.7708 2873.0781 13.4336
			SET_CHAR_HEADING scplayer 266.1126

			$audio_string    = &DES3_CA	
			audio_sound_file = SOUND_DES3_CA
			START_NEW_SCRIPT audio_line -1 0 1 1 0
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO  2 SOUND_DES3_CB
			TIMERB = 0

			m_goals++		
		ENDIF 

		// play audio
		IF m_goals = 3
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_CB	
					audio_sound_file = SOUND_DES3_CB
					START_NEW_SCRIPT audio_line scplayer 0 1 2 1
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO  1 SOUND_DES3_CC
					m_goals++
					TIMERB = 0
				ENDIF
			ENDIF
		ENDIF

		// play audio
		IF m_goals = 4
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_CC	
					audio_sound_file = SOUND_DES3_CC
					START_NEW_SCRIPT audio_line scplayer 0 1 1 1
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO  2 SOUND_DES3_CD
					m_goals++
					TIMERB = 0
				ENDIF	
			ENDIF
		ENDIF

		// play audio
		IF m_goals = 5
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					$audio_string    = &DES3_CD	
					audio_sound_file = SOUND_DES3_CD
					START_NEW_SCRIPT audio_line -1 0 1 2 1
					m_goals++
					TIMERB = 0
				ENDIF	
			ENDIF
		ENDIF

		// finish cutscene
		IF m_goals = 6
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// wait a while before creating alpha chopper
		IF m_goals = 7
		
			PRINT_NOW AIR1_10 7000 1 // deliver contraband to hideout
			ADD_BLIP_FOR_COORD -394.8462 2226.6221 41.4259  location_blip  //-450.9301 2238.4504 43.4409

			gar_door_flag = 0
			m_goals++
		ENDIF

		// wait for player to arrive at hideout
		IF m_goals = 8

			LVAR_INT gar_door_flag
			// open garage
			IF gar_door_flag = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -394.8462 2226.6221 41.4259 25.0 25.0 20.0 FALSE
					OPEN_GARAGE Ghostdr
					gar_door_flag = 1
				ENDIF
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -394.8462 2226.6221 41.4259 40.0 40.0 20.0 FALSE
					CLOSE_GARAGE Ghostdr
					gar_door_flag = 0
				ENDIF
			ENDIF
	

			IF DOES_BLIP_EXIST location_blip
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF LOCATE_CHAR_IN_CAR_3D scplayer -394.8462 2226.6221 41.4259 4.0 4.0 4.0 TRUE
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF IS_VEHICLE_ON_ALL_WHEELS car
							m_goals++
						ENDIF				 
					ENDIF
				ELSE
					IF LOCATE_CHAR_ON_FOOT_3D scplayer -394.8462 2226.6221 41.4259 1.2 1.2 2.0 TRUE
						m_goals++				 
					ENDIF
				ENDIF
			ELSE
				IF TIMERA > 60000
					PRINT_NOW AIR1_11 7000 1 // lose the feds	
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF

		// fade out
		IF m_goals = 9

			SET_PLAYER_CONTROL player1 OFF
			CLOSE_GARAGE Ghostdr
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer -389.8034 2230.7419 41.4297 
			SET_CHAR_HEADING scplayer 270.0
			SET_CAMERA_IN_FRONT_OF_PLAYER
			RESTORE_CAMERA_JUMPCUT

			WHILE NOT IS_GARAGE_CLOSED Ghostdr
				WAIT 0
			ENDWHILE
			
			m_passed = 1

		ENDIF


		IF alpha_has_caught_up = 0
			IF DOES_VEHICLE_EXIST alpha_heli
				IF NOT IS_CAR_DEAD alpha_heli
					GET_CHAR_COORDINATES scplayer x y z
					IF NOT LOCATE_CAR_3D alpha_heli x y z 100.0 100.0 100.0	FALSE
						ACTIVATE_HELI_SPEED_CHEAT alpha_heli 10	
					ELSE 
						des3_heat = 100
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING des3_heat COUNTER_DISPLAY_BAR AIR1_12 
						alpha_has_caught_up = 1
						PRINT_NOW AIR1_11 7000 1 // lose the feds
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF alpha_has_caught_up = 1
			IF DOES_VEHICLE_EXIST alpha_heli
				IF NOT IS_CAR_DEAD alpha_heli
					GET_CHAR_COORDINATES scplayer x y z
					GET_CAR_COORDINATES alpha_heli x2 y2 z2
					GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 fed_distance

					IF fed_distance < 175.0
						// tick up heat 
						IF des3_heat < 100
							IF fed_timer > 100
								des3_heat += 1
								fed_timer = 0
							ENDIF
						ENDIF
					ELSE
						
						// tick down heat
						IF des3_heat > 0
							IF fed_timer > 100
								des3_heat += -1
								fed_timer = 0
							ENDIF
						ENDIF
					ENDIF

					IF des3_heat = 0
						
						alpha_heli_ai = HELI_FUCKS_OFF
						alpha_heli_goals = 0
	
						PRINT_NOW AIR1_10 7000 1 // deliver contraband to hideout
						alpha_has_caught_up = 2	
						CLEAR_ONSCREEN_COUNTER des3_heat

					ENDIF
				ELSE
					PRINT_NOW AIR1_10 7000 1 // deliver contraband to hideout
					alpha_has_caught_up = 2	
					CLEAR_ONSCREEN_COUNTER des3_heat

				ENDIF
			ENDIF
		ENDIF


		// manage location blip
		IF m_goals > 1

			IF DOES_BLIP_EXIST location_blip
				IF alpha_has_caught_up = 1 
					REMOVE_BLIP location_blip
				ENDIF
			ELSE
				IF alpha_has_caught_up = 2 
					ADD_BLIP_FOR_COORD -450.9301 2238.4504 43.4409 location_blip		
				ENDIF	
			ENDIF

		ENDIF

		IF m_frame_num = 3
			IF DOES_VEHICLE_EXIST alpha_heli
				GOSUB process_alpha_heli_ai
			ENDIF
		ENDIF

		GOSUB update_swinging_bag

RETURN



// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
d3_global_functions:

	IF DOES_BLIP_EXIST escape_car_blip
		IF NOT IS_CAR_DEAD escape_car
			IF IS_CHAR_IN_CAR scplayer escape_car
				REMOVE_BLIP escape_car_blip
			ENDIF
		ELSE
			REMOVE_BLIP escape_car_blip
		ENDIF
	ENDIF


	IF DOES_VEHICLE_EXIST pheli
		IF IS_CAR_DEAD pheli
			m_failed = 1
			PRINT_NOW AIR1_08 5000 1 // the contraband heli is history
		ENDIF
	ENDIF

	IF m_frame_num = 7
		GOSUB d3_PROCESS_CLEANUP_PEDS
	ENDIF

RETURN






// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// ****************************************************************
//							PACKAGE
// ****************************************************************
LVAR_FLOAT swing_speed_x  swing_speed_y	  swing_speed_z
LVAR_FLOAT swing_accel_x  swing_accel_y	  swing_accel_z
LVAR_FLOAT swing_rot_x	  swing_rot_y 	  swing_rot_z
LVAR_FLOAT swing_dest_x   swing_dest_y 	  swing_dest_z
LVAR_FLOAT swing_dist_x   swing_dist_y 	  swing_dist_z
LVAR_FLOAT swing_start_x  swing_start_y   swing_start_z
LVAR_FLOAT swing_vec_x swing_vec_y swing_vec_z
LVAR_FLOAT swing_angle_for_z
LVAR_FLOAT swing_angle_for_xy 
LVAR_FLOAT swing_direct_dist
LVAR_FLOAT swing_2d_dist
LVAR_INT   swing_timer_until_landed
LVAR_FLOAT swing_initial_total_time
LVAR_INT   please_update_swinging_bag

initialise_swinging_bag:

	IF DOES_OBJECT_EXIST contraband
		GET_OBJECT_COORDINATES contraband swing_start_x swing_start_y swing_start_z
		swing_start_z += 6.679 
		DELETE_OBJECT contraband
	ENDIF
	CREATE_OBJECT_NO_OFFSET KMB_PARACHUTE swing_start_x swing_start_y swing_start_z contraband 
	SET_OBJECT_COLLISION contraband FALSE
	SET_OBJECT_DYNAMIC contraband FALSE

	//SET_OBJECT_RECORDS_COLLISIONS contraband FALSE


	swing_speed_x = 1.0
	swing_speed_y = 0.5
	swing_speed_z = 0.0

	swing_rot_x = 0.0	  
	swing_rot_y	= 0.0
	swing_rot_z = 0.0			

	swing_timer_until_landed = 80000
	swing_initial_total_time =# swing_timer_until_landed

	// get initial distances
	GET_DISTANCE_BETWEEN_COORDS_3D swing_start_x swing_start_y swing_start_z  swing_dest_x swing_dest_y swing_dest_z swing_direct_dist
	GET_DISTANCE_BETWEEN_COORDS_2D swing_start_x swing_start_y swing_dest_x swing_dest_y swing_2d_dist
	swing_dist_x = swing_start_x - swing_dest_x
	swing_dist_y = swing_start_y - swing_dest_y
	swing_dist_z = swing_start_z - swing_dest_z 


	swing_vec_x = swing_dest_x - swing_start_x
	swing_vec_y = swing_dest_y - swing_start_y
	swing_vec_z	= swing_dest_z - swing_start_z

	swing_vec_x /= swing_direct_dist
	swing_vec_y /= swing_direct_dist
	swing_vec_z	/= swing_direct_dist

	// work out angles to make this thing land where we want
	
	// work out z angle
	vec_x = swing_2d_dist
	vec_y = 0.0
	vec2_x = swing_2d_dist
	vec2_y = swing_dist_z
	GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y vec2_x vec2_y swing_angle_for_z

	// work out xy angle
	vec_x = swing_dist_x
	vec_y = 0.0
	vec2_x = swing_dist_x
	vec2_y = swing_dist_y
	GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y vec2_x vec2_y swing_angle_for_xy 

//	SAVE_NEWLINE_TO_DEBUG_FILE 
//	SAVE_STRING_TO_DEBUG_FILE "swing_start_x = "
//	SAVE_FLOAT_TO_DEBUG_FILE swing_start_x
//
//	SAVE_NEWLINE_TO_DEBUG_FILE 
//	SAVE_STRING_TO_DEBUG_FILE "swing_start_y = "
//	SAVE_FLOAT_TO_DEBUG_FILE swing_start_y
//
//	SAVE_NEWLINE_TO_DEBUG_FILE 
//	SAVE_STRING_TO_DEBUG_FILE "swing_start_z = "
//	SAVE_FLOAT_TO_DEBUG_FILE swing_start_z

	please_update_swinging_bag = 1

RETURN

update_swinging_bag:

	IF please_update_swinging_bag = 1

		IF swing_timer_until_landed > 1000
		//AND NOT HAS_OBJECT_COLLIDED_WITH_ANYTHING contraband
			IF DOES_OBJECT_EXIST contraband

				// x
				IF swing_rot_x > 180.0
					swing_rot_x = -180.0
				ENDIF
				IF swing_rot_x < -180.0
					swing_rot_x = 180.0
				ENDIF

				IF swing_rot_x >= 0.0
					swing_accel_x = -0.01
				ELSE
					swing_accel_x = 0.01
				ENDIF

				swing_speed_x +=@ swing_accel_x
				IF swing_speed_x > 1.0
					swing_speed_x = 1.0
				ENDIF
				IF swing_speed_x < -1.0
					swing_speed_x = -1.0
				ENDIF 

				swing_rot_x +=@	swing_speed_x

				// figure out position
					temp_float =# swing_timer_until_landed
					temp_float2 = swing_initial_total_time - temp_float
					temp_float2 /= swing_initial_total_time
					temp_float2 *= swing_direct_dist

					vec_x = swing_vec_x * temp_float2
					vec_y = swing_vec_y * temp_float2	
					vec_z = swing_vec_z * temp_float2

					x =	swing_start_x +	vec_x
					y =	swing_start_y +	vec_y
					z =	swing_start_z +	vec_z

//				// 1. get 3d dist
//					temp_float =# swing_timer_until_landed
//					temp_float /= swing_initial_total_time
//					temp_float *= swing_direct_dist
//
//				// 2. get z dist
//					SIN swing_angle_for_z temp_float2 
//					swing_dist_z = temp_float * temp_float2
//					
//				// 3. get y dist
//					COS swing_angle_for_z temp_float2
//					swing_dist_x = temp_float * temp_float2
//					
//				// 4. get xy dist
//					COS swing_angle_for_xy temp_float2 
//					swing_2d_dist = swing_dist_x / temp_float2
//					
//				// 5. get x dist
//					SIN swing_angle_for_xy temp_float2
//					swing_dist_y = swing_2d_dist * temp_float2
//					
//					x =	swing_dest_x - swing_dist_x
//					y =	swing_dest_y + swing_dist_y
//					z =	swing_dest_z + swing_dist_z	
				

				
				contraband_x = x
				contraband_y = y
				contraband_z = z
				IF contraband_z < swing_dest_z
					contraband_z = swing_dest_z
				ENDIF

				SET_OBJECT_COORDINATES contraband contraband_x contraband_y contraband_z
				SET_OBJECT_ROTATION contraband swing_rot_x 0.0 90.0 	
			
			ENDIF
		
		ELSE

			// bag has landed
			GET_OBJECT_COORDINATES contraband contraband_x contraband_y contraband_z
			GET_GROUND_Z_FOR_3D_COORD contraband_x contraband_y contraband_z contraband_z
			contraband_z += 0.5
			IF contraband_z < swing_dest_z
				contraband_z = swing_dest_z
			ENDIF
			DELETE_OBJECT contraband
			REMOVE_BLIP contraband_blip
			CREATE_OBJECT_NO_OFFSET KMB_HOLDALL contraband_x contraband_y contraband_z contraband
			SET_OBJECT_SCALE contraband 0.3
			ADD_BLIP_FOR_OBJECT contraband contraband_blip
			FREEZE_OBJECT_POSITION contraband TRUE
			SET_OBJECT_COLLISION contraband FALSE
			please_update_swinging_bag = 0

		ENDIF

	ENDIF

RETURN


// ****************************************************************
//							PHELI
// ****************************************************************
LVAR_FLOAT pheli_x pheli_y pheli_z pheli_h
create_pheli:
	CREATE_CAR LEVIATHN pheli_x pheli_y pheli_z pheli
	SET_CAR_HEADING pheli pheli_h 
	SET_HELI_BLADES_FULL_SPEED pheli
	SET_HELI_STABILISER pheli TRUE
	
	CREATE_CHAR_INSIDE_CAR pheli PEDTYPE_MISSION1 WMYPLT pheli_pilot 

	//CHANGE_CAR_COLOUR pheli CARCOLOUR_WHITE CARCOLOUR_RED3
	SET_CAR_HEALTH pheli 5000
	SET_CAR_PROOFS pheli FALSE FALSE FALSE TRUE FALSE
	ADD_BLIP_FOR_CAR pheli pheli_blip
	SET_BLIP_AS_FRIENDLY pheli_blip TRUE
	SET_LOAD_COLLISION_FOR_CAR_FLAG pheli FALSE
	SET_CAR_ONLY_DAMAGED_BY_PLAYER pheli TRUE
RETURN

delete_pheli:
	IF DOES_CHAR_EXIST pheli_pilot
		DELETE_CHAR pheli_pilot
	ENDIF
	IF DOES_VEHICLE_EXIST pheli
		DELETE_CAR pheli
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED LEVIATHN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPLT
	REMOVE_BLIP pheli_blip		 
RETURN
process_pheli_ai:

	IF NOT IS_CAR_DEAD pheli

		// FLAG 0 - PHELI FLIES ROUND IN CIRCLE, AVOIDING OTHER HELIS ------------
		IF pheli_flag = 0

			IF pheli_goals = 0
								   				
				checkpoints_x[0] = 	-784.6607  		//-796.0797			//-825.5401
				checkpoints_y[0] = 	2442.2307  		//2376.2654			//2421.0518
				checkpoints_z[0] = 	180.0 	   	 	//184.8641			//178.3568
								   			   			
				checkpoints_x[1] = 	-804.9590  		//-778.5941			//-781.1748
				checkpoints_y[1] = 	2425.5757  		//2440.5225			//2448.8640
				checkpoints_z[1] = 	180.0 	   		//190.3923			//183.9537
								   			   		
				checkpoints_x[2] = 	-787.8075  		//-811.2608			//-761.1632
				checkpoints_y[2] = 	2405.6890  		//2433.4285			//2416.2007
				checkpoints_z[2] = 	180.0 	   		//168.9958			//175.6004
								   			   		
				checkpoints_x[3] = 	-772.5547  		//-778.6653			//-808.3519
				checkpoints_y[3] = 	2413.2227  		//2405.2563			//2353.1206
				checkpoints_z[3] = 	180.0 	   			//174.7590			//174.5634
								   			   		
				checkpoints_x[4] = 	-772.9753  		//-788.8781			//-858.5571
				checkpoints_y[4] = 	2432.2837  		//2432.1472			//2301.5857
				checkpoints_z[4] = 	180.0 	   			//177.3275			//180.0208
								   			   		
				checkpoints_x[5] = 	-789.0908  		//-818.2345			//-755.6169
				checkpoints_y[5] = 	2424.8518  		//2396.1636			//2318.3374
				checkpoints_z[5] = 	180.0 	   		//176.3614			//182.5816
								   			   		
				checkpoints_x[6] = 	-802.8000  		//-775.7371			//-825.0372
				checkpoints_y[6] = 	2431.0896  		//2455.2415			//2456.4963
				checkpoints_z[6] = 	180.0 	   		//175.0698			//165.8862
								   			   		
				checkpoints_x[7] = 	-772.5547  		//-767.5922			//-807.6311
				checkpoints_y[7] = 	2413.2227  		//2390.2625			//2390.3743
				checkpoints_z[7] = 	180.0	   		//175.2787			//175.1135
								   							
				pheli_progress = 0
				HELI_GOTO_COORDS pheli checkpoints_x[pheli_progress] checkpoints_y[pheli_progress] checkpoints_z[pheli_progress] 15.0 checkpoints_z[pheli_progress]
				ACTIVATE_HELI_SPEED_CHEAT pheli 1
				pheli_goals++
			ENDIF


			// follow route
			IF LOCATE_CAR_3D pheli checkpoints_x[pheli_progress] checkpoints_y[pheli_progress] checkpoints_z[pheli_progress] 10.0 10.0 10.0 FALSE
				GENERATE_RANDOM_INT_IN_RANGE 0 8 temp_int
				IF temp_int = pheli_progress
					temp_int += 1
					IF temp_int > 7
						temp_int = 0
					ENDIF
				ENDIF
				pheli_progress = temp_int
				HELI_GOTO_COORDS pheli checkpoints_x[pheli_progress] checkpoints_y[pheli_progress] checkpoints_z[pheli_progress] 15.0 checkpoints_z[pheli_progress] 						
			ENDIF

//			// avoid alpha heli
//			IF NOT IS_CAR_DEAD alpha_heli
//				GET_CAR_COORDINATES alpha_heli x y z
//			ENDIF
//			IF LOCATE_CAR_3D pheli x y z 15.0 15.0 15.0 FALSE
//				heli_to_move = pheli 
//				heli_to_avoid = alpha_heli
//				GOSUB heli_aviod_heli 
//			ENDIF
//
//			// avoid bravo heli
//			IF NOT IS_CAR_DEAD bravo_heli
//				GET_CAR_COORDINATES bravo_heli x y z
//			ENDIF
//			IF LOCATE_CAR_3D pheli x y z 15.0 15.0 15.0 FALSE
//				heli_to_move = pheli 
//				heli_to_avoid = bravo_heli
//				GOSUB heli_aviod_heli 
//			ENDIF

			// make heli come crashing down	---------------------------------
			GET_CAR_HEALTH pheli temp_int
			IF temp_int <= 300
				IF NOT pheli_flag = 99
					MAKE_HELI_COME_CRASHING_DOWN pheli
					pheli_flag = 99
				ENDIF
			ENDIF
			

		ENDIF
	ENDIF

RETURN

// ****************************************************************
//							ALPHA HELI
// ****************************************************************
LVAR_FLOAT alpha_x alpha_y alpha_z alpha_h
create_alpha_heli:

	// create heli ------------
	IF NOT DOES_VEHICLE_EXIST alpha_heli
		CREATE_CAR MAVERICK 0.0 0.0 0.0 alpha_heli	
		VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE alpha_heli TRUE
		SET_LOAD_COLLISION_FOR_CAR_FLAG alpha_heli FALSE
	ENDIF

	// create pilot	-----------
	IF NOT DOES_CHAR_EXIST alpha_pilot
		CREATE_CHAR_INSIDE_CAR alpha_heli PEDTYPE_MISSION2 BMYMIB alpha_pilot	
	ENDIF

	// create guys on side ----
	IF NOT DOES_CHAR_EXIST alpha_ped[0]
		CREATE_CHAR PEDTYPE_MISSION2 WMOMIB 0.0 0.0 0.0 alpha_ped[0]
	ENDIF
	IF NOT DOES_CHAR_EXIST alpha_ped[1]
		CREATE_CHAR PEDTYPE_MISSION2 BMYMIB 0.0 0.0 0.0 alpha_ped[1]
	ENDIF
	IF NOT DOES_CHAR_EXIST alpha_ped[2]
		CREATE_CHAR PEDTYPE_MISSION2 WMOMIB 0.0 0.0 0.0 alpha_ped[2]
	ENDIF
	IF NOT DOES_CHAR_EXIST alpha_ped[3]
		CREATE_CHAR PEDTYPE_MISSION2 BMYMIB 0.0 0.0 0.0 alpha_ped[3]
	ENDIF

	// heli setup --------------
	IF NOT IS_CAR_DEAD alpha_heli
		SET_CAR_COORDINATES alpha_heli alpha_x alpha_y alpha_z
		SET_CAR_HEADING alpha_heli alpha_h
		SET_HELI_BLADES_FULL_SPEED alpha_heli
		CHANGE_CAR_COLOUR alpha_heli CARCOLOUR_BLACK CARCOLOUR_BLACK
		SET_HELI_STABILISER alpha_heli TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER alpha_heli TRUE	
		LOCK_CAR_DOORS alpha_heli CARLOCK_LOCKED
	ENDIF

	// pilot setup -------------
	IF NOT IS_CHAR_DEAD alpha_pilot
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE alpha_pilot FALSE
		SET_CHAR_CANT_BE_DRAGGED_OUT alpha_pilot TRUE
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE alpha_pilot FALSE
		SET_CHAR_DECISION_MAKER alpha_pilot empty_dm
	ENDIF

	// peds setup --------------
	IF NOT IS_CAR_DEAD alpha_heli
		IF NOT IS_CHAR_DEAD alpha_ped[0]
			ATTACH_CHAR_TO_CAR alpha_ped[0] alpha_heli 1.0 1.25 -0.1 FACING_RIGHT 310.0 WEAPONTYPE_AK47
			alpha_ped_attached[0] = 1
		ENDIF
		IF NOT IS_CHAR_DEAD alpha_ped[1]
			ATTACH_CHAR_TO_CAR alpha_ped[1] alpha_heli 1.1 -0.3 -0.1 FACING_RIGHT 310.0 WEAPONTYPE_AK47
			alpha_ped_attached[1] = 1
		ENDIF
		IF NOT IS_CHAR_DEAD alpha_ped[2]
			ATTACH_CHAR_TO_CAR alpha_ped[2] alpha_heli -1.0 1.25 -0.1 FACING_LEFT 50.0 WEAPONTYPE_AK47
			alpha_ped_attached[2] = 1
		ENDIF
		IF NOT IS_CHAR_DEAD alpha_ped[3]
			ATTACH_CHAR_TO_CAR alpha_ped[3] alpha_heli -1.1 -0.3 -0.1 FACING_LEFT 50.0 WEAPONTYPE_AK47	
			alpha_ped_attached[3] = 1				
		ENDIF
		temp_int = 0
		WHILE temp_int < 4
			IF NOT IS_CHAR_DEAD alpha_ped[temp_int]
				SET_CHAR_SHOOT_RATE alpha_ped[temp_int] 20
				SET_CHAR_ACCURACY alpha_ped[temp_int] 27
				IF temp_int = 0
				OR temp_int = 2
					SET_CHAR_MAX_HEALTH alpha_ped[temp_int] 125
					SET_CHAR_HEALTH alpha_ped[temp_int] 125	
				ELSE
					SET_CHAR_MAX_HEALTH alpha_ped[temp_int] 100
					SET_CHAR_HEALTH alpha_ped[temp_int] 100
				ENDIF
				 
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER alpha_ped[temp_int] TRUE
				SET_CHAR_DECISION_MAKER alpha_ped[temp_int] empty_dm
				//SET_CHAR_RELATIONSHIP alpha_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				//SET_CHAR_RELATIONSHIP alpha_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
				alpha_ped_flag[temp_int] = 0
			ENDIF
		temp_int++
		ENDWHILE
	ENDIF

	// add blip
	IF NOT IS_CAR_DEAD alpha_heli
		IF DOES_BLIP_EXIST alpha_heli_blip
			REMOVE_BLIP alpha_heli_blip
		ENDIF
		ADD_BLIP_FOR_CAR alpha_heli alpha_heli_blip
	ENDIF	
	
//	// add searchlight
//	IF NOT IS_CAR_DEAD alpha_heli
//		CREATE_SEARCHLIGHT_ON_VEHICLE alpha_heli 0.0 0.0 -1.0 0.0 0.0 0.0 6.0 1.0 alpha_search						
//	ENDIF

	alpha_heli_ai = CHASE_PHELI
	alpha_heli_goals = 0
	total_created_helis++

RETURN
delete_alpha_heli:
	IF DOES_CHAR_EXIST alpha_pilot
		DELETE_CHAR alpha_pilot
	ENDIF
	temp_int = 0
	WHILE temp_int < 4
		IF DOES_CHAR_EXIST alpha_ped[temp_int]
			DELETE_CHAR alpha_ped[temp_int]
		ENDIF
	temp_int++
	ENDWHILE	   
	IF DOES_VEHICLE_EXIST alpha_heli
		DELETE_CAR alpha_heli
	ENDIF

//	IF DOES_SEARCHLIGHT_EXIST alpha_search
//		DELETE_SEARCHLIGHT alpha_search
//	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED AK47
RETURN
process_alpha_heli_ai:

process_alpha_heli_ai_begining:

	// if heli is still alive after four minutes destroy it.
	IF TIMERA > 240000
		IF NOT IS_CAR_DEAD alpha_heli
			EXPLODE_CAR alpha_heli
			EXPLODE_CAR alpha_heli
			TIMERA = 0
		ENDIF
	ENDIF
	

	CONST_INT CHASE_PHELI 			0
	CONST_INT DROP_GUYS_LOCATION1 	1
	CONST_INT DROP_GUYS_LOCATION2 	2
	CONST_INT DROP_GUYS_LOCATION3 	3
	CONST_INT CIRCLE_PLAYER			4
	CONST_INT DROP_GUYS_NEAR		5
	CONST_INT HELI_FUCKS_OFF		99

	// FLAG 0 - CHASE PHELI AND AVOID BRAVO HELI --------------------------

	SWITCH alpha_heli_ai 
	CASE CHASE_PHELI
	 	//IF DOES_VEHICLE_EXIST alpha_heli
			IF NOT IS_CAR_DEAD alpha_heli
				
				IF alpha_heli_goals = 0
					IF NOT IS_CAR_DEAD pheli
						HELI_FOLLOW_ENTITY alpha_heli -1 pheli 20.0
						//ACTIVATE_HELI_SPEED_CHEAT alpha_heli 1
	//					POINT_SEARCHLIGHT_AT_VEHICLE alpha_search pheli 10.0
						alpha_heli_goals++
						alpha_heli_timer = 0
					ENDIF
				ENDIF

				IF alpha_heli_goals = 1
					IF alpha_heli_timer < 10000
						// catch up with player heli
						IF NOT IS_CAR_DEAD pheli
							GET_CAR_COORDINATES pheli x y z
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 10.0 -10.0 5.0	x y z
							IF NOT LOCATE_CAR_3D alpha_heli x y z 50.0 50.0 50.0 FALSE
								GET_CAR_COORDINATES alpha_heli x2 y2 z2
								vec_x = x - x2
								vec_y = y - y2
								vec_z = z - z2
								GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 distance
								IF distance > 0.01
									vec_x /= distance
									vec_y /= distance
									vec_z /= distance
									vec_x *= 0.01
									vec_y *= 0.01
									vec_z *= 0.01
									APPLY_FORCE_TO_CAR alpha_heli vec_x vec_y vec_z 0.0 0.0 0.0
								ENDIF 
							ELSE
		//						IF LOCATE_CAR_3D alpha_heli x y z 25.0 25.0 25.0 FALSE
		//							heli_to_move = alpha_heli 
		//							heli_to_avoid = pheli
		//							GOSUB heli_aviod_heli
		//						ENDIF
							ENDIF
						ENDIF
					ELSE
						// circle player
						HELI_FOLLOW_ENTITY alpha_heli scplayer -1 20.0
						alpha_heli_goals++
						alpha_heli_timer = 0
					ENDIF
				ENDIF

				IF alpha_heli_goals = 2
					IF alpha_heli_timer > 25000
						alpha_heli_goals = 0
						alpha_heli_timer = 0	
					ENDIF
				ENDIF

			ENDIF
		//ENDIF
	BREAK


	// goto point and drop dudes off --------------------------------------------------------
	CASE DROP_GUYS_NEAR
		//IF DOES_VEHICLE_EXIST alpha_heli								
			IF NOT IS_CAR_DEAD alpha_heli

				IF alpha_heli_goals = 0
//					SET_HELI_REACHED_TARGET_DISTANCE alpha_heli 3
//					HELI_GOTO_COORDS alpha_heli -771.6 2421.3 170.0 5.0 170.0
					HELI_KEEP_ENTITY_IN_VIEW alpha_heli scplayer -1 15.0 170.0
	//				POINT_SEARCHLIGHT_AT_COORD alpha_search -771.6 2421.3 155.0 10.0
					alpha_ped_flag[0] =	0	   
					alpha_ped_flag[1] =	0
					alpha_ped_flag[2] =	0
					alpha_ped_flag[3] =	0
					alpha_heli_goals++
				ENDIF
				
				// wait for heli to get to coords
				IF alpha_heli_goals = 1
					IF m_frame_num = 10
						GET_CHAR_COORDINATES scplayer x2 y2 z2
						IF LOCATE_CAR_2D alpha_heli x2 y2 20.0 20.0 FALSE
							GET_CAR_COORDINATES alpha_heli x y z
							GET_GROUND_Z_FOR_3D_COORD x y z temp_float
							
							// check if ground isn't way below player
							temp_float2 = z2 - temp_float
							IF temp_float2 < 5.0

//								temp_float2 = z - temp_float
//								IF temp_float2 < 20.0
									alpha_goto_x = x
									alpha_goto_y = y
									alpha_goto_Z = temp_float + 10.0
									HELI_GOTO_COORDS alpha_heli alpha_goto_x alpha_goto_y alpha_goto_z 10.0 0.0
									
									alpha_heli_goals++	
//								ENDIF

							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// wait for heli to get to coords
				IF alpha_heli_goals = 2
					IF LOCATE_CAR_3D alpha_heli alpha_goto_x alpha_goto_y alpha_goto_z 4.0 4.0 8.0 FALSE
						GET_CAR_SPEED alpha_heli temp_float
						IF temp_float > 1.0
							GET_CAR_SPEED_VECTOR alpha_heli vec_x vec_y	vec_z
							vec_x *= -0.001
							vec_y *= -0.001
							vec_z *= -0.001
							APPLY_FORCE_TO_CAR alpha_heli vec_x vec_y vec_z 0.0 0.0 0.0
						ELSE
							FREEZE_CAR_POSITION alpha_heli TRUE
							alpha_heli_timer = 0
							alpha_heli_goals++					 
						ENDIF
					ELSE
						GET_CAR_SPEED alpha_heli temp_float
						IF temp_float < 10.0
							GET_CAR_COORDINATES alpha_heli x y z
							vec_x = alpha_goto_x - x
							vec_y = alpha_goto_y - y
							vec_z = alpha_goto_Z - z
							GET_DISTANCE_BETWEEN_COORDS_3D x y z alpha_goto_x alpha_goto_y alpha_goto_z distance
							IF distance > 0.01
								vec_x /= distance
								vec_y /= distance
								vec_z /= distance
								vec_x *= 0.01
								vec_y *= 0.01
								vec_z *= 0.01
								APPLY_FORCE_TO_CAR alpha_heli vec_x vec_y vec_z 0.0 0.0 0.0
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// create first swat
				IF alpha_heli_goals = 3
					IF alpha_heli_timer > 1500
						FREEZE_CAR_POSITION alpha_heli TRUE
						IF NOT IS_CHAR_DEAD alpha_ped[0]
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS alpha_heli 1.0 1.25 -0.1 x y z
							DELETE_CHAR alpha_ped[0]
							CREATE_SWAT_ROPE PEDTYPE_MISSION2 WMOMIB x y z alpha_ped[0]	
							SET_CHAR_SHOOT_RATE alpha_ped[0] 30
							SET_CHAR_ACCURACY alpha_ped[0] 27
							SET_CHAR_MAX_HEALTH alpha_ped[0] 125
							SET_CHAR_HEALTH alpha_ped[0] 125 
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER alpha_ped[0] TRUE
							SET_CHAR_DECISION_MAKER alpha_ped[0] empty_dm
							GIVE_WEAPON_TO_CHAR alpha_ped[0] WEAPONTYPE_AK47 9999	
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE alpha_ped[0] 2000.0
							alpha_ped_attached[0] = 0
							alpha_ped_flag[0] = 1
						ENDIF

						alpha_heli_timer = 0
						alpha_heli_goals += 1

					ENDIF
				ENDIF

				// 2nd swat rope
				IF alpha_heli_goals = 4
					IF alpha_heli_timer > 0
						IF NOT IS_CHAR_DEAD alpha_ped[1]
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS alpha_heli 1.1 -0.3 -0.1 x y z
							DELETE_CHAR alpha_ped[1]
							CREATE_SWAT_ROPE PEDTYPE_MISSION2 BMYMIB x y z alpha_ped[1]	
							SET_CHAR_SHOOT_RATE alpha_ped[1] 30
							SET_CHAR_ACCURACY alpha_ped[1] 27
							SET_CHAR_HEALTH alpha_ped[1] 100 
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER alpha_ped[1] TRUE
							SET_CHAR_DECISION_MAKER alpha_ped[1] empty_dm
							GIVE_WEAPON_TO_CHAR alpha_ped[1] WEAPONTYPE_AK47 9999
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE alpha_ped[1] 2000.0
							alpha_ped_attached[1] = 0
							alpha_ped_flag[1] = 1
						ENDIF

						alpha_heli_timer = 0
						alpha_heli_goals++
					ENDIF
				ENDIF
				
				// 3rd swat rope
				IF alpha_heli_goals = 5
					IF alpha_heli_timer > 2000
						IF NOT IS_CHAR_DEAD alpha_ped[2]
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS alpha_heli -1.0 1.25 -0.1 x y z
							DELETE_CHAR alpha_ped[2]
							CREATE_SWAT_ROPE PEDTYPE_MISSION2 WMOMIB x y z alpha_ped[2]	
							SET_CHAR_SHOOT_RATE alpha_ped[2] 30
							SET_CHAR_ACCURACY alpha_ped[2] 27
							SET_CHAR_MAX_HEALTH alpha_ped[2] 125
							SET_CHAR_HEALTH alpha_ped[2] 125 
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER alpha_ped[2] TRUE
							SET_CHAR_DECISION_MAKER alpha_ped[2] empty_dm
							GIVE_WEAPON_TO_CHAR alpha_ped[2] WEAPONTYPE_AK47 9999
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE alpha_ped[2] 2000.0
							alpha_ped_attached[2] = 0
							alpha_ped_flag[2] = 1
						ENDIF
						alpha_heli_timer = 0
						alpha_heli_goals++
					ENDIF
				ENDIF

				// 4th swat rope
				IF alpha_heli_goals = 6

					IF alpha_heli_timer > 500
						IF NOT IS_CHAR_DEAD alpha_ped[3]
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS alpha_heli -1.1 -0.3 -0.1 x y z
							DELETE_CHAR alpha_ped[3]
							CREATE_SWAT_ROPE PEDTYPE_MISSION2 WMOMIB x y z alpha_ped[3]	
							SET_CHAR_SHOOT_RATE alpha_ped[3] 30
							SET_CHAR_ACCURACY alpha_ped[3] 27
							SET_CHAR_HEALTH alpha_ped[3] 100 
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER alpha_ped[3] TRUE
							SET_CHAR_DECISION_MAKER alpha_ped[3] empty_dm
							GIVE_WEAPON_TO_CHAR alpha_ped[3] WEAPONTYPE_AK47 9999
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE alpha_ped[3] 2000.0
							alpha_ped_attached[3] = 0
							alpha_ped_flag[3] = 1
						ENDIF
						alpha_heli_timer = 0
						alpha_heli_goals++
					ENDIF
				ENDIF


				// wait for swat guys to finish
				IF alpha_heli_goals = 7
					// if all the swat guy have gone - make heli bugger off
					IF alpha_ped_flag[0] = 2
					OR IS_CHAR_DEAD alpha_ped[0]
						IF alpha_ped_flag[1] = 2
						OR IS_CHAR_DEAD alpha_ped[1]
							IF alpha_ped_flag[2] = 2
							OR IS_CHAR_DEAD alpha_ped[2]
								IF alpha_ped_flag[3] = 2
								OR IS_CHAR_DEAD alpha_ped[3]

									alpha_heli_timer = 0
									alpha_heli_goals++
								ENDIF
							ENDIF			
						ENDIF
					ENDIF 
				ENDIF

				// heli fucks off when dropped off guys
				IF alpha_heli_goals = 8
					alpha_heli_ai = HELI_FUCKS_OFF
				ENDIF

			ENDIF
		//ENDIF

		
		// ped ai ---------------------------------------------------------------
		temp_int = m_frame_num - 5
		IF temp_int < 4
		AND temp_int > -1
			IF DOES_CHAR_EXIST alpha_ped[temp_int]
				IF NOT IS_CHAR_DEAD alpha_ped[temp_int]
					IF alpha_ped_attached[temp_int] = 0
						IF alpha_ped_flag[temp_int] = 0
							GET_SCRIPT_TASK_STATUS alpha_ped[temp_int] TASK_KILL_CHAR_ON_FOOT temp_int2
							IF temp_int2 = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT alpha_ped[temp_int] scplayer
							ENDIF
						ENDIF
						IF alpha_ped_flag[temp_int] = 1
							GET_SCRIPT_TASK_STATUS alpha_ped[temp_int] CREATE_SWAT_ROPE temp_int2
							IF temp_int2 = FINISHED_TASK
								SET_CHAR_DECISION_MAKER alpha_ped[temp_int] tough_dm
								SET_CHAR_RELATIONSHIP alpha_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
								alpha_ped_flag[temp_int]++
							ENDIF
						ENDIF
						IF alpha_ped_flag[temp_int] = 2
							// point and shoot at player for a bit
							CLEAR_CHAR_TASKS alpha_ped[temp_int]
							OPEN_SEQUENCE_TASK temp_seq
								TASK_STAY_IN_SAME_PLACE -1 FALSE
								TASK_KILL_CHAR_ON_FOOT -1 scplayer
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK alpha_ped[temp_int] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							alpha_ped_flag[temp_int]++
						ENDIF
						IF alpha_ped_flag[temp_int] = 3
							ped_to_add = alpha_ped[temp_int]
							GOSUB d3_add_to_cleanup_peds
							alpha_ped[temp_int] = 0
							alpha_ped_flag[temp_int] = 0
							
							// add to list of peds to cleanup
							LVAR_INT cleanup_peds[24]
								
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	BREAK  // alpha_heli_ai = DROP_GUYS_LOCATION2



	// circle player ---------------------------------------------------------------------------------
	CASE CIRCLE_PLAYER
		//IF DOES_VEHICLE_EXIST alpha_heli
			IF NOT IS_CAR_DEAD alpha_heli
				IF alpha_heli_goals = 0
					HELI_KEEP_ENTITY_IN_VIEW alpha_heli scplayer -1 25.0 15.0
					//HELI_FOLLOW_ENTITY alpha_heli scplayer -1 15.0
	//				POINT_SEARCHLIGHT_AT_CHAR alpha_search scplayer 10.0
					alpha_heli_goals++
				ENDIF
				
				// keep heli moving slightly
				GET_CAR_SPEED alpha_heli temp_float
				IF temp_float < 5.0
					GET_CAR_SPEED_VECTOR alpha_heli vec_x vec_y	vec_z
					vec_x *= 0.0001
					vec_y *= 0.0001
					vec_z *= 0.0001
					APPLY_FORCE_TO_CAR alpha_heli vec_x vec_y vec_z 0.0 0.0 0.0
				ENDIF	

			ENDIF
		//ENDIF
	BREAK

	ENDSWITCH

	// heli fucks off --------------------------------------------------------------------------------
	//IF DOES_VEHICLE_EXIST alpha_heli
		IF alpha_heli_ai = HELI_FUCKS_OFF
			IF NOT IS_CAR_DEAD alpha_heli
				VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE alpha_heli FALSE
				FREEZE_CAR_POSITION alpha_heli FALSE
				GET_CAR_HEALTH alpha_heli temp_int
				IF temp_int <= 300
					MARK_CHAR_AS_NO_LONGER_NEEDED alpha_pilot
					IF NOT IS_CHAR_DEAD alpha_pilot
						SET_CHAR_DECISION_MAKER alpha_pilot empty_dm
					ENDIF
					MAKE_HELI_COME_CRASHING_DOWN alpha_heli
					MARK_CAR_AS_NO_LONGER_NEEDED alpha_heli				
					alpha_heli = 0
					alpha_pilot = 0
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED alpha_pilot
					IF NOT IS_CHAR_DEAD alpha_pilot
						SET_CHAR_DECISION_MAKER alpha_pilot empty_dm
					ENDIF
					HELI_GOTO_COORDS alpha_heli 204.0 2816.0 186.0 50.0 186.0
					MARK_CAR_AS_NO_LONGER_NEEDED alpha_heli
					alpha_heli = 0
					alpha_pilot = 0
				ENDIF
				
				IF DOES_BLIP_EXIST alpha_heli_blip
					REMOVE_BLIP alpha_heli_blip
				ENDIF
	//			DELETE_SEARCHLIGHT alpha_search
				
			ENDIF
		ENDIF
	//ENDIF


	// if all the peds on the heli are dead then the heli should fuck off -----------------------------
	//IF DOES_VEHICLE_EXIST alpha_heli
		IF NOT IS_CAR_DEAD alpha_heli
			IF alpha_ped_attached[0] = 1
			AND IS_CHAR_DEAD alpha_ped[0]
				IF alpha_ped_attached[1] = 1
				AND IS_CHAR_DEAD alpha_ped[1]
					IF alpha_ped_attached[2] = 1
					AND IS_CHAR_DEAD alpha_ped[2] 
						IF alpha_ped_attached[3] = 1
						AND IS_CHAR_DEAD alpha_ped[3]
							alpha_heli_ai = HELI_FUCKS_OFF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	//ENDIF

	// on board peds ---- shoot player when near, otherwise shoot pheli -------------------------------
	temp_int = m_frame_num - 5
	IF temp_int < 4
	AND temp_int > -1
		IF DOES_CHAR_EXIST alpha_ped[temp_int]	 
			IF NOT IS_CHAR_DEAD alpha_ped[temp_int]
				IF alpha_ped_attached[temp_int] = 1
					IF alpha_ped_flag[temp_int] = 0
						IF NOT IS_CHAR_DEAD pheli_pilot
							CLEAR_CHAR_TASKS alpha_ped[temp_int]
							OPEN_SEQUENCE_TASK temp_seq
								TASK_STAY_IN_SAME_PLACE -1 TRUE
						   		TASK_KILL_CHAR_ON_FOOT -1 pheli_pilot
								TASK_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK alpha_ped[temp_int] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							alpha_ped_flag[temp_int]++
						ELSE
							alpha_ped_flag[temp_int]++
						ENDIF
					ENDIF
					IF alpha_ped_flag[temp_int] = 1
						IF temp_int = 1
						OR temp_int = 3
						OR alpha_heli_ai = CIRCLE_PLAYER
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D alpha_ped[temp_int] scplayer 60.0 60.0 60.0 FALSE
								CLEAR_CHAR_TASKS alpha_ped[temp_int]
								OPEN_SEQUENCE_TASK temp_seq
									TASK_STAY_IN_SAME_PLACE -1 TRUE
							   		TASK_KILL_CHAR_ON_FOOT -1 scplayer
									TASK_STAY_IN_SAME_PLACE -1 TRUE
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK alpha_ped[temp_int] temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
								SET_CHAR_SHOOT_RATE alpha_ped[temp_int] 100
								alpha_ped_flag[temp_int]++
							ENDIF
						ENDIF
					ENDIF
					IF alpha_ped_flag[temp_int] = 2
						IF temp_int = 1
						OR temp_int = 3
						OR alpha_heli_ai = CIRCLE_PLAYER
							IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D alpha_ped[temp_int] scplayer 80.0 80.0 80.0 FALSE
								IF NOT IS_CHAR_DEAD pheli_pilot	
									CLEAR_CHAR_TASKS alpha_ped[temp_int]
									OPEN_SEQUENCE_TASK temp_seq
										TASK_STAY_IN_SAME_PLACE -1 TRUE
								   		TASK_KILL_CHAR_ON_FOOT -1 pheli_pilot
										TASK_STAY_IN_SAME_PLACE -1 TRUE
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK alpha_ped[temp_int] temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
									SET_CHAR_SHOOT_RATE alpha_ped[temp_int] 20
									alpha_ped_flag[temp_int] = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF 
				ENDIF
			ENDIF
		ENDIF
//	temp_int++
//	ENDWHILE
	ENDIF


//	// avoid bravo heli	---------------------------------------------
//	IF NOT IS_CAR_DEAD alpha_heli
//		IF NOT IS_CAR_DEAD bravo_heli
//			GET_CAR_COORDINATES bravo_heli x y z
//		ENDIF
//		IF LOCATE_CAR_3D alpha_heli x y z 25.0 25.0 25.0 FALSE
//			heli_to_move = alpha_heli 
//			heli_to_avoid = bravo_heli
//			GOSUB heli_aviod_heli 
//		ENDIF
//	ENDIF

	// make heli come crashing down	---------------------------------
	//IF DOES_VEHICLE_EXIST alpha_heli
		IF NOT IS_CAR_DEAD alpha_heli
			GET_CAR_HEALTH alpha_heli temp_int
			IF temp_int <= 300
				IF NOT alpha_heli_ai = HELI_FUCKS_OFF
					alpha_heli_ai = HELI_FUCKS_OFF
				ENDIF
			ENDIF
		ENDIF
	//ENDIF

//	// limit heli speed ---------------------------------------------
//	IF NOT IS_CAR_DEAD alpha_heli
//		GET_CAR_SPEED alpha_heli temp_float
//		IF temp_float > 20.0
//			GET_CAR_SPEED_VECTOR alpha_heli vec_x vec_y	vec_z
//			vec_x *= -0.0001
//			vec_y *= -0.0001
//			vec_z *= -0.0001
//			APPLY_FORCE_TO_CAR alpha_heli vec_x vec_y vec_z 0.0 0.0 0.0
//		ENDIF	
//	ENDIF
				  
	// detach peds if they die ---------------------------------------
	temp_int = 0
	WHILE temp_int < 4
		IF DOES_CHAR_EXIST alpha_ped[temp_int]
			IF IS_CHAR_DEAD alpha_ped[temp_int]
				DETACH_CHAR_FROM_CAR alpha_ped[temp_int]
				alpha_ped_flag[temp_int] = 0
				ped_to_add = alpha_ped[temp_int]
				GOSUB d3_add_to_cleanup_peds
				alpha_ped[temp_int] = 0 
			ENDIF 
		ENDIF
	temp_int++
	ENDWHILE

	//IF DOES_VEHICLE_EXIST alpha_heli
		IF IS_CAR_DEAD alpha_heli
		AND NOT alpha_heli = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED alpha_pilot
			IF NOT IS_CHAR_DEAD alpha_pilot
				SET_CHAR_DECISION_MAKER alpha_pilot empty_dm
			ENDIF
			MARK_CAR_AS_NO_LONGER_NEEDED alpha_heli
			alpha_heli = 0	
			alpha_pilot = 0

			
			temp_int = 0
			WHILE temp_int < 4
				IF DOES_CHAR_EXIST alpha_ped[temp_int]
					ped_to_add = alpha_ped[temp_int]
					GOSUB d3_add_to_cleanup_peds
					alpha_ped[temp_int] = 0 
				ENDIF
			temp_int++
			ENDWHILE

			IF DOES_BLIP_EXIST alpha_heli_blip
				REMOVE_BLIP alpha_heli_blip
			ENDIF


		ENDIF
	//ENDIF

RETURN

// ****************************************************************
//							BRAVO HELI
// ****************************************************************
LVAR_FLOAT bravo_x bravo_y bravo_z bravo_h
create_bravo_heli:

	// create heli ------------
	IF DOES_VEHICLE_EXIST bravo_heli
		MARK_CAR_AS_NO_LONGER_NEEDED bravo_heli
		bravo_heli = 0
		//WRITE_DEBUG BRAVO_NO_LONGER_NEEDED1
	ENDIF
	IF NOT DOES_VEHICLE_EXIST bravo_heli
		CREATE_CAR MAVERICK 0.0 0.0 0.0 bravo_heli
		VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE bravo_heli TRUE
		SET_LOAD_COLLISION_FOR_CAR_FLAG bravo_heli FALSE
		//WRITE_DEBUG BRAVO_CREATED	
	ENDIF

	// create pilot	-----------
	IF DOES_CHAR_EXIST bravo_pilot
		MARK_CHAR_AS_NO_LONGER_NEEDED bravo_pilot
		IF NOT IS_CHAR_DEAD bravo_pilot
			SET_CHAR_DECISION_MAKER bravo_pilot empty_dm
		ENDIF
		bravo_pilot = 0
	ENDIF
	IF NOT DOES_CHAR_EXIST bravo_pilot
		CREATE_CHAR_INSIDE_CAR bravo_heli PEDTYPE_MISSION2 BMYMIB bravo_pilot
	ENDIF

	// create guys on side ----
	temp_int = 0
	WHILE temp_int < 4
		IF DOES_CHAR_EXIST bravo_ped[temp_int]
			MARK_CHAR_AS_NO_LONGER_NEEDED bravo_ped[temp_int] 
		 	bravo_ped[temp_int] = 0
		ENDIF
	temp_int++
	ENDWHILE

	IF NOT DOES_CHAR_EXIST bravo_ped[0]
		CREATE_CHAR PEDTYPE_MISSION2 WMOMIB 0.0 0.0 0.0 bravo_ped[0]
	ENDIF
	IF NOT DOES_CHAR_EXIST bravo_ped[1]
		CREATE_CHAR PEDTYPE_MISSION2 BMYMIB 0.0 0.0 0.0 bravo_ped[1]
	ENDIF
	IF NOT DOES_CHAR_EXIST bravo_ped[2]
		CREATE_CHAR PEDTYPE_MISSION2 WMOMIB 0.0 0.0 0.0 bravo_ped[2]
	ENDIF
	IF NOT DOES_CHAR_EXIST bravo_ped[3]
		CREATE_CHAR PEDTYPE_MISSION2 BMYMIB 0.0 0.0 0.0 bravo_ped[3]
	ENDIF

	// heli setup --------------
	IF NOT IS_CAR_DEAD bravo_heli
		SET_CAR_COORDINATES bravo_heli bravo_x bravo_y bravo_z
		SET_CAR_HEADING bravo_heli bravo_h
		SET_HELI_BLADES_FULL_SPEED bravo_heli
		CHANGE_CAR_COLOUR bravo_heli CARCOLOUR_BLACK CARCOLOUR_BLACK
		SET_HELI_STABILISER bravo_heli TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER bravo_heli TRUE	
		LOCK_CAR_DOORS bravo_heli CARLOCK_LOCKED
	ENDIF

	// pilot setup -------------
	IF NOT IS_CHAR_DEAD bravo_pilot
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE bravo_pilot FALSE
		SET_CHAR_CANT_BE_DRAGGED_OUT bravo_pilot TRUE
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE bravo_pilot FALSE  
		SET_CHAR_DECISION_MAKER bravo_pilot empty_dm
	ENDIF

	// peds setup --------------
	IF NOT IS_CAR_DEAD bravo_heli
		IF NOT IS_CHAR_DEAD bravo_ped[0]
			ATTACH_CHAR_TO_CAR bravo_ped[0] bravo_heli 1.0 1.25 0.2 FACING_RIGHT 310.0 WEAPONTYPE_AK47
		ENDIF
		IF NOT IS_CHAR_DEAD bravo_ped[1]
			ATTACH_CHAR_TO_CAR bravo_ped[1] bravo_heli 1.1 -0.3 0.2 FACING_RIGHT 310.0 WEAPONTYPE_AK47
		ENDIF
		IF NOT IS_CHAR_DEAD bravo_ped[2]
			ATTACH_CHAR_TO_CAR bravo_ped[2] bravo_heli -1.0 1.25 0.2 FACING_LEFT 50.0 WEAPONTYPE_AK47
		ENDIF
		IF NOT IS_CHAR_DEAD bravo_ped[3]
			ATTACH_CHAR_TO_CAR bravo_ped[3] bravo_heli -1.1 -0.3 0.2 FACING_LEFT 50.0 WEAPONTYPE_AK47					
		ENDIF
		temp_int = 0
		WHILE temp_int < 4
			IF NOT IS_CHAR_DEAD bravo_ped[temp_int]
				SET_CHAR_SHOOT_RATE bravo_ped[temp_int] 30
				SET_CHAR_ACCURACY bravo_ped[temp_int] 27
				IF temp_int = 0
				OR temp_int = 2
					SET_CHAR_MAX_HEALTH bravo_ped[temp_int] 125
					SET_CHAR_HEALTH bravo_ped[temp_int] 125
				ELSE
					SET_CHAR_HEALTH bravo_ped[temp_int] 100
				ENDIF
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER bravo_ped[temp_int] TRUE
				SET_CHAR_DECISION_MAKER bravo_ped[temp_int] empty_dm
				//SET_CHAR_RELATIONSHIP bravo_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				//SET_CHAR_RELATIONSHIP bravo_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
				bravo_ped_flag[temp_int] = 0
			ENDIF
		temp_int++
		ENDWHILE
	ENDIF

	// add blips
	IF NOT IS_CAR_DEAD bravo_heli
		IF DOES_BLIP_EXIST bravo_heli_blip
			REMOVE_BLIP bravo_heli_blip
		ENDIF
		ADD_BLIP_FOR_CAR bravo_heli bravo_heli_blip
	ENDIF

	bravo_heli_ai = CHASE_PHELI
	bravo_heli_goals = 0
	total_created_helis++

RETURN
delete_bravo_heli:
	IF DOES_CHAR_EXIST bravo_pilot
		DELETE_CHAR bravo_pilot
	ENDIF
	temp_int = 0
	WHILE temp_int < 4
		IF DOES_CHAR_EXIST bravo_ped[temp_int]
			DELETE_CHAR bravo_ped[temp_int]
		ENDIF
	temp_int++
	ENDWHILE
	IF DOES_VEHICLE_EXIST bravo_heli
		DELETE_CAR bravo_heli
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED AK47
RETURN
process_bravo_heli_ai:

	// if heli is still alive after four minutes destroy it.
	IF TIMERB > 240000
		IF NOT IS_CAR_DEAD bravo_heli
			EXPLODE_CAR bravo_heli
			EXPLODE_CAR bravo_heli
			TIMERB = 0
		ENDIF
	ENDIF


	// bravo heli 
	//IF DOES_VEHICLE_EXIST bravo_heli
		IF NOT IS_CAR_DEAD bravo_heli
			
			// FLAG 0 - CHASE PHELI AND AVOID ALPHA HELI --------------------------
			IF bravo_heli_ai = CHASE_PHELI

				IF bravo_heli_goals = 0
					IF NOT IS_CAR_DEAD pheli
						HELI_FOLLOW_ENTITY bravo_heli -1 pheli 20.0
						//ACTIVATE_HELI_SPEED_CHEAT bravo_heli 1
						//WRITE_DEBUG BRAVO_HELI_TOLD_TO_CHASE_PHELI
						bravo_heli_goals++
						bravo_heli_timer = 0
					ENDIF
				ENDIF

				IF bravo_heli_goals = 1
					IF bravo_heli_timer < 15000
						// catch up with player heli
						IF NOT IS_CAR_DEAD pheli
							//GET_CAR_COORDINATES pheli x y z		  
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli -10.0 -10.0 0.0 x y z
							IF NOT LOCATE_CAR_3D bravo_heli x y z 50.0 50.0 50.0 FALSE
								GET_CAR_COORDINATES bravo_heli x2 y2 z2
								vec_x = x - x2
								vec_y = y - y2
								vec_z = z - z2
								GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 distance
								IF distance > 0.01
									vec_x /= distance
									vec_y /= distance
									vec_z /= distance
									vec_x *= 0.01
									vec_y *= 0.01
									vec_z *= 0.01
									APPLY_FORCE_TO_CAR bravo_heli vec_x vec_y vec_z 0.0 0.0 0.0 
								ENDIF
		//					ELSE
		//						IF LOCATE_CAR_3D bravo_heli x y z 25.0 25.0 25.0 FALSE
		//							heli_to_move = bravo_heli 
		//							heli_to_avoid = pheli
		//							GOSUB heli_aviod_heli
		//						ENDIF
							ENDIF	
						ENDIF
					ELSE
						HELI_FOLLOW_ENTITY bravo_heli scplayer -1 20.0
						bravo_heli_goals++
						bravo_heli_timer = 0
					ENDIF	
				ENDIF

				IF bravo_heli_goals = 2
					IF bravo_heli_timer > 35000
						bravo_heli_goals = 0
						bravo_heli_timer = 0	
					ENDIF
				ENDIF

			ENDIF

			// heli fucks off --------------------------------------------------------------------------------
			IF bravo_heli_ai = HELI_FUCKS_OFF

				FREEZE_CAR_POSITION bravo_heli FALSE
				GET_CAR_HEALTH bravo_heli temp_int
				IF temp_int <= 300
					MARK_CHAR_AS_NO_LONGER_NEEDED bravo_pilot
					IF NOT IS_CHAR_DEAD bravo_pilot
						SET_CHAR_DECISION_MAKER bravo_pilot empty_dm
					ENDIF
					MAKE_HELI_COME_CRASHING_DOWN bravo_heli
					MARK_CAR_AS_NO_LONGER_NEEDED bravo_heli
					//WRITE_DEBUG BRAVO_HELI_FUCKS_OFF1
					bravo_heli = 0
					bravo_pilot = 0
					//WRITE_DEBUG BRAVO_HELI_COME_CRASHING_DOWN
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED bravo_pilot
					IF NOT IS_CHAR_DEAD bravo_pilot
						SET_CHAR_DECISION_MAKER bravo_pilot empty_dm
					ENDIF
					HELI_GOTO_COORDS bravo_heli 204.0 2816.0 186.0 50.0 186.0 
					MARK_CAR_AS_NO_LONGER_NEEDED bravo_heli
					//WRITE_DEBUG BRAVO_HELI_FUCKS_OFF2
					bravo_heli = 0
					bravo_pilot = 0
				ENDIF
				IF DOES_BLIP_EXIST bravo_heli_blip
					REMOVE_BLIP bravo_heli_blip
				ENDIF
			ENDIF

		ENDIF
	//ENDIF

	// on board peds ---------------------------------------------------------------------------------------
	temp_int = m_frame_num
	IF temp_int < 4	
		IF DOES_CHAR_EXIST bravo_ped[temp_int] 
			IF NOT IS_CHAR_DEAD bravo_ped[temp_int]
				IF bravo_ped_flag[temp_int] = 0
					IF NOT IS_CHAR_DEAD pheli_pilot			 
						CLEAR_CHAR_TASKS bravo_ped[temp_int]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_STAY_IN_SAME_PLACE -1 TRUE
					   		TASK_KILL_CHAR_ON_FOOT -1 pheli_pilot
							TASK_STAY_IN_SAME_PLACE -1 TRUE
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bravo_ped[temp_int] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
						bravo_ped_flag[temp_int]++
					ENDIF
				ENDIF
				IF bravo_ped_flag[temp_int] = 1
					IF temp_int = 1
					OR temp_int = 3
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D bravo_ped[temp_int] scplayer 60.0 60.0 60.0 FALSE
							CLEAR_CHAR_TASKS bravo_ped[temp_int]
							OPEN_SEQUENCE_TASK temp_seq
								TASK_STAY_IN_SAME_PLACE -1 TRUE
						   		TASK_KILL_CHAR_ON_FOOT -1 scplayer
								TASK_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK bravo_ped[temp_int] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							SET_CHAR_SHOOT_RATE bravo_ped[temp_int] 100
							bravo_ped_flag[temp_int]++
						ENDIF
					ENDIF
				ENDIF
				IF bravo_ped_flag[temp_int] = 2
					IF temp_int = 1
					OR temp_int = 3
						IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D bravo_ped[temp_int] scplayer 80.0 80.0 80.0 FALSE
							IF NOT IS_CHAR_DEAD pheli_pilot
								CLEAR_CHAR_TASKS bravo_ped[temp_int]
								OPEN_SEQUENCE_TASK temp_seq
									TASK_STAY_IN_SAME_PLACE -1 TRUE
							   		TASK_KILL_CHAR_ON_FOOT -1 pheli_pilot
									TASK_STAY_IN_SAME_PLACE -1 TRUE
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK bravo_ped[temp_int] temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
								SET_CHAR_SHOOT_RATE bravo_ped[temp_int] 30
								bravo_ped_flag[temp_int] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF 
			ENDIF
		ENDIF
//	temp_int++
//	ENDWHILE
	ENDIF


	// if all the peds on the heli are dead then the heli should fuck off -----------------------------
	IF m_frame_num = 4
		//IF DOES_VEHICLE_EXIST bravo_heli
			IF NOT IS_CAR_DEAD bravo_heli
				IF IS_CHAR_DEAD bravo_ped[0]
					IF IS_CHAR_DEAD bravo_ped[1]
						IF IS_CHAR_DEAD bravo_ped[2] 
							IF IS_CHAR_DEAD bravo_ped[3]
								bravo_heli_ai = HELI_FUCKS_OFF
								//WRITE_DEBUG BRAVO_TOLD_TO_FUCK_OFF3
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		//ENDIF
	ENDIF

//	// avoid alpha heli	---------------------------------------------
//	IF NOT IS_CAR_DEAD bravo_heli
//		IF NOT IS_CAR_DEAD alpha_heli
//			GET_CAR_COORDINATES alpha_heli x y z
//		ENDIF
//		IF LOCATE_CAR_3D bravo_heli x y z 25.0 25.0 25.0 FALSE
//			heli_to_move = bravo_heli 
//			heli_to_avoid = alpha_heli
//			GOSUB heli_aviod_heli 
//		ENDIF
//	ENDIF

//	// limit heli speed ---------------------------------------------
//	IF NOT IS_CAR_DEAD bravo_heli
//		GET_CAR_SPEED bravo_heli temp_float
//		IF temp_float > 20.0
//			GET_CAR_SPEED_VECTOR bravo_heli vec_x vec_y	vec_z
//			vec_x *= -0.0001
//			vec_y *= -0.0001
//			vec_z *= -0.0001
//			APPLY_FORCE_TO_CAR bravo_heli vec_x vec_y vec_z 0.0 0.0 0.0
//		ENDIF	
//	ENDIF

	// make heli come crashing down	----------------------------------
	//IF DOES_VEHICLE_EXIST bravo_heli
		IF NOT IS_CAR_DEAD bravo_heli
			GET_CAR_HEALTH bravo_heli temp_int
			IF temp_int <= 300
				IF NOT bravo_heli_ai = HELI_FUCKS_OFF
					bravo_heli_ai = HELI_FUCKS_OFF
					//WRITE_DEBUG BRAVO_TOLD_TO_FUCK_OFF4
					VEHICLE_CAN_BE_TARGETTED_BY_HS_MISSILE bravo_heli FALSE
				ENDIF
			ENDIF
		ENDIF
	//ENDIF

	// detach peds if they die ---------------------------------------
	temp_int = m_frame_num
	IF temp_int < 4
		IF DOES_CHAR_EXIST bravo_ped[temp_int]
			IF IS_CHAR_DEAD bravo_ped[temp_int]
				DETACH_CHAR_FROM_CAR bravo_ped[temp_int]
				bravo_ped_flag[temp_int] = 0 
				ped_to_add = bravo_ped[temp_int]
				GOSUB d3_add_to_cleanup_peds
				bravo_ped[temp_int] = 0
			ENDIF 
		ENDIF
	ENDIF

	//IF DOES_VEHICLE_EXIST bravo_heli
//		IF IS_CAR_DEAD bravo_heli
//			MARK_CAR_AS_NO_LONGER_NEEDED bravo_heli
//			MARK_CHAR_AS_NO_LONGER_NEEDED bravo_pilot
//			bravo_pilot = 0
//			bravo_heli = 0	
//		ENDIF
	//ENDIF


		IF IS_CAR_DEAD bravo_heli
		AND NOT bravo_heli = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED bravo_pilot
			IF NOT IS_CHAR_DEAD bravo_pilot
				SET_CHAR_DECISION_MAKER bravo_pilot empty_dm
			ENDIF
			MARK_CAR_AS_NO_LONGER_NEEDED bravo_heli
			bravo_heli = 0	
			bravo_pilot = 0
			temp_int = 0
			WHILE temp_int < 4
				IF DOES_CHAR_EXIST bravo_ped[temp_int]
					ped_to_add = bravo_ped[temp_int]
					GOSUB d3_add_to_cleanup_peds
					bravo_ped[temp_int] = 0 
				ENDIF
			temp_int++
			ENDWHILE   
			IF DOES_BLIP_EXIST bravo_heli_blip
				REMOVE_BLIP bravo_heli_blip
			ENDIF
		ENDIF




RETURN


// ========================================
//	 D3_ADD_TO_CLEANUP_PEDS
// ========================================
// input
LVAR_INT ped_to_add
// workings
LVAR_INT cleanup_ped_counter 
LVAR_INT cleanup_ped[20]
d3_add_to_cleanup_peds:
	
	cleanup_ped_counter = 0
	
	// fake creates
	IF cleanup_ped_counter = -1
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 cleanup_ped[0]
	ENDIF

	WHILE cleanup_ped_counter < 20
		IF NOT DOES_CHAR_EXIST cleanup_ped[cleanup_ped_counter]
			cleanup_ped[cleanup_ped_counter] = ped_to_add
			cleanup_ped_counter = 20
		ELSE
			cleanup_ped_counter++
		ENDIF
	ENDWHILE

RETURN

// ========================================
//	 PROCESS_CLEANUP_PEDS
// ========================================
d3_PROCESS_CLEANUP_PEDS:
	
	cleanup_ped_counter = 0
	
	WHILE cleanup_ped_counter < 20
		IF DOES_CHAR_EXIST cleanup_ped[cleanup_ped_counter]
			IF IS_CHAR_DEAD cleanup_ped[cleanup_ped_counter]
				REMOVE_CHAR_ELEGANTLY cleanup_ped[cleanup_ped_counter]
				//WRITE_DEBUG REMOVE_CHAR_ELEGANTLY1
			ENDIF
		ENDIF
	cleanup_ped_counter++
	ENDWHILE

	cleanup_ped_counter = 0
	WHILE cleanup_ped_counter < 4
		IF DOES_CHAR_EXIST alpha_ped[cleanup_ped_counter]
			IF IS_CHAR_DEAD alpha_ped[cleanup_ped_counter]
				REMOVE_CHAR_ELEGANTLY alpha_ped[cleanup_ped_counter]
				//WRITE_DEBUG REMOVE_CHAR_ELEGANTLY2
				alpha_ped[cleanup_ped_counter] = 0
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST bravo_ped[cleanup_ped_counter]
			IF IS_CHAR_DEAD bravo_ped[cleanup_ped_counter]
				REMOVE_CHAR_ELEGANTLY bravo_ped[cleanup_ped_counter]
				//WRITE_DEBUG REMOVE_CHAR_ELEGANTLY3
				bravo_ped[cleanup_ped_counter] = 0
			ENDIF
		ENDIF
	cleanup_ped_counter++
	ENDWHILE

RETURN



// ========================================
//	 HELI_AVOID_HELI
// ========================================
// input parameters
LVAR_INT heli_to_move heli_to_avoid
heli_aviod_heli:

	IF NOT IS_CAR_DEAD heli_to_avoid
		GET_CAR_COORDINATES heli_to_avoid x y z
	ENDIF
	IF NOT IS_CAR_DEAD heli_to_move
		GET_CAR_COORDINATES heli_to_move x2 y2 z2
	
		vec_x = x - x2
		vec_y = y - y2
		vec_z = z - z2
		//GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 distance
//		IF distance > 0.01
//			vec_x /= distance
//			vec_y /= distance
//			vec_z /= distance
			vec_x *= -0.0001
			vec_y *= -0.0001
			vec_z *= -0.0001
			APPLY_FORCE_TO_CAR heli_to_move vec_x vec_y vec_z 0.0 0.0 0.0 
//		ENDIF
	ENDIF

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
	DES3_next_stage:
		m_stage++
		m_goals        = 0
		dialogue_flag  = 0
		help_flag	   = 0
		dialogue_timer = 0
		help_timer	   = 0
		TIMERA 		   = 0
		TIMERB		   = 0
	RETURN					

// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_DES3:

	//desert3_mission_attempts++
	PRINT_BIG M_FAIL 5000 1

RETURN

// PASS
mission_passed_DES3:
	PRINT_WITH_NUMBER_BIG ( M_PASS ) 1000 5000 1 //"Mission Passed!"
	ADD_SCORE player1 1000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1	
	REGISTER_MISSION_PASSED DESERT3	
	PLAYER_MADE_PROGRESS 1
	flag_desert_mission_counter++

	SET_INT_STAT PASSED_DESERT3 1

	
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
RETURN

// CLEANUP
mission_cleanup_DES3:

	IF NOT IS_GARAGE_CLOSED Ghostdr
		CLOSE_GARAGE Ghostdr
	ENDIF

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	MARK_MODEL_AS_NO_LONGER_NEEDED BANDITO
	MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	MARK_MODEL_AS_NO_LONGER_NEEDED TEARGAS
	MARK_MODEL_AS_NO_LONGER_NEEDED HEATSEEK
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPLT
	MARK_MODEL_AS_NO_LONGER_NEEDED AK47
	MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
	MARK_MODEL_AS_NO_LONGER_NEEDED KMB_HOLDALL
	MARK_MODEL_AS_NO_LONGER_NEEDED LEVIATHN
	MARK_MODEL_AS_NO_LONGER_NEEDED JOURNEY
	MARK_MODEL_AS_NO_LONGER_NEEDED KMB_PARACHUTE
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA
	MARK_MODEL_AS_NO_LONGER_NEEDED SWFOPRO

	REMOVE_BLIP location_blip
	REMOVE_BLIP alpha_heli_blip
	REMOVE_BLIP bravo_heli_blip
	REMOVE_BLIP pheli_blip
	REMOVE_BLIP	contraband_blip
	REMOVE_BLIP weapon_blip
	REMOVE_BLIP escape_car_blip

	REMOVE_ANIMATION BOMBER

	RELEASE_WEATHER

	CLEAR_ONSCREEN_COUNTER pheli_health
	CLEAR_ONSCREEN_COUNTER des3_heat

	REMOVE_PICKUP m_pickup
	REMOVE_PICKUP m_pickup_health
	REMOVE_PICKUP m_pickup_armour

	IF IS_PLAYER_PLAYING player1
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	ENDIF

	// remove decision makers
	REMOVE_DECISION_MAKER empty_dm
	REMOVE_DECISION_MAKER tough_dm
	
	KILL_FX_SYSTEM m_fx


	SET_PLAYER_CONTROL player1 TRUE

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0
	//SET_MAX_WANTED_LEVEL 6

	GET_GAME_TIMER timer_mobile_start
	SWITCH_POLICE_HELIS	TRUE
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN
	 

}
