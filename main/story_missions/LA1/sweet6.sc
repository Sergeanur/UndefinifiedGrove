MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Cesar Vialpando
//				     AUTHOR : Neil
//		       DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************
SCRIPT_NAME SWEET6
GOSUB mission_start_SWEET6
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_SWEET6
ENDIF
GOSUB mission_cleanup_SWEET6
MISSION_END

mission_start_SWEET6:
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

//m_stage = 5
//m_goals = 0
//LOAD_MISSION_TEXT SWEET6

mission_loop_SWEET6:
WAIT 0
	
// for filshie
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	m_passed = 1
ENDIF

// end of debug tools **************		
									
// Frame counter
m_frame_num++
IF m_frame_num > 22
	m_frame_num = 0
ENDIF

// Additional Timers
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time

dialogue_timer += m_time_diff
help_timer	   += m_time_diff

meet_jump_timer[0] += m_time_diff
meet_jump_timer[1] += m_time_diff
meet_jump_timer[2] += m_time_diff
camera_timer	+= m_time_diff
pscore_timer	+= m_time_diff

	GOSUB sweet6_debug_tools

	SWITCH m_stage

		CASE 0
			GOSUB sweet6_m_stage_0
		BREAK
		CASE 1
			GOSUB sweet6_m_stage_1
		BREAK
		CASE 2
			GOSUB sweet6_m_stage_2
		BREAK
		CASE 3
			GOSUB sweet6_m_stage_3
		BREAK
		CASE 4
			GOSUB sweet6_m_stage_4
		BREAK
		CASE 5
			GOSUB sweet6_m_stage_5
		BREAK
		CASE 6
			GOSUB sweet6_m_stage_6
		BREAK

	ENDSWITCH

	GOSUB sweet6_global_functions

//  end of main loop *** don't change ***
end_of_main_loop_SWEET6:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_SWEET6 
		ELSE
			GOSUB mission_passed_SWEET6
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_SWEET6
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF




// *************************************************************************************************************
// 												DEBUG TOOLS   
// *************************************************************************************************************
sweet6_debug_tools:

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
		VAR_INT SWEET6_view_debug[8]
		
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
			display_debug++
			IF display_debug > 3
				display_debug = 0
			ENDIF
			CLEAR_ALL_VIEW_VARIABLES
		ENDIF
		IF display_debug = 1
			// system variables
			SWEET6_view_debug[0] = m_stage
			SWEET6_view_debug[1] = m_goals
			SWEET6_view_debug[2] = dialogue_flag
			SWEET6_view_debug[3] = dialogue_timer
			SWEET6_view_debug[4] = help_flag
			SWEET6_view_debug[5] = help_timer
			SWEET6_view_debug[6] = TIMERA
			SWEET6_view_debug[7]	= TIMERB
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[0] m_stage
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[1] m_goals
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[2] dialogue_flag
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[3] dialogue_timer
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[4] help_flag
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[5] help_timer
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[6] TIMERA
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[7] TIMERB
		ENDIF
		IF display_debug = 2

		// put mission variable for display in here

			SWEET6_view_debug[0] = car_hyd_flag
			SWEET6_view_debug[1] = sw6_mission_attempts
			SWEET6_view_debug[7]	= player_has_attacked_meet
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[0] car_hyd_flag
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[1] sw6_mission_attempts
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[7] player_has_attacked_meet
		ENDIF
		IF display_debug = 3
			// system variables
			SWEET6_view_debug[0] = meeting_is_loaded
			SWEET6_view_debug[1] = current_wager
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[0] meeting_is_loaded
			VIEW_INTEGER_VARIABLE SWEET6_view_debug[1] current_wager
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
			GOTO end_of_main_loop_SWEET6
		ENDIF

		// progress m_goals	& m_stage
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_PLUS
			m_goals++
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_MINUS
			m_goals--
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_ASTERISK 
			m_stage++
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_SLASH 
			m_stage--
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_9
			m_goals = 99
		ENDIF
		
	ENDIF	
RETURN


// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
sweet6_m_stage_0:

		// fake creates
		IF m_goals = -1
			CREATE_CAR PONY 0.0 0.0 0.0 meet_car[0]
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 meet_ped[0]
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 car_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 mod_garage_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 mod_garage1
		ENDIF
		
		// necessary global variables
		VAR_INT sweet6_contact_blip

		// cars
		LVAR_INT pickup_car
		LVAR_INT meet_car[7] 

		// peds 
		LVAR_INT mechanic_dude	
		LVAR_INT meet_ped[22]
		LVAR_INT bounce_girl
		LVAR_INT sw6_kendal
		LVAR_INT sw6_cesar

		// blips
		LVAR_INT location_blip
		LVAR_INT car_blip

		// flags 
		LVAR_INT car_hyd_flag
		LVAR_INT meet_ped_flag[22]
		LVAR_INT player_has_attacked_meet
		LVAR_INT meeting_is_loaded
		LVAR_INT input_mode
		LVAR_INT camera_mode
		LVAR_INT cross_pressed
		LVAR_INT select_pressed

		// timers
		LVAR_INT meet_jump_timer[3]
		LVAR_INT camera_timer
		LVAR_INT pscore_timer

		// floats
		LVAR_FLOAT load_car_x[7]
		LVAR_FLOAT load_car_y[7]
		LVAR_FLOAT load_car_z[7]
		LVAR_FLOAT load_car_h[7]
		LVAR_FLOAT load_ped_x[22]
		LVAR_FLOAT load_ped_y[22]
		LVAR_FLOAT load_ped_z[22]
		LVAR_FLOAT load_ped_h[22]
		LVAR_FLOAT meet_wheel_fr[3]
		LVAR_FLOAT meet_wheel_fl[3]
		LVAR_FLOAT meet_wheel_br[3]
		LVAR_FLOAT meet_wheel_bl[3]
		LVAR_FLOAT offx offy offz
		LVAR_FLOAT force_multiplier

		// wager stuff
		LVAR_INT bet_step
		LVAR_INT min_bet
		LVAR_INT max_bet
		LVAR_INT current_wager
		LVAR_INT cross_is_pressed
		LVAR_INT initial_stake
		LVAR_INT square_is_pressed

		// set flags 
		car_hyd_flag 				= 0
		temp_int = 0
		WHILE temp_int < 22
			meet_ped_flag[temp_int] = 0
		temp_int++
		ENDWHILE
		player_has_attacked_meet	= 0
		meeting_is_loaded			= 0
		input_mode					= 0
		camera_mode					= 0
		select_pressed				= 0

		// set floats
		force_multiplier = 0.01

		// fake creates
		IF m_goals = -1
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 sweet6_contact_blip
		ENDIF

		LOAD_MISSION_TEXT SWEET6

		SUPPRESS_CAR_MODEL SAVANNA

		min_bet = 50
		max_bet = 1000

		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

		GOSUB SWEET6_next_stage	
		
RETURN

// *************************************************************************************************************
//											STAGE 1 - Animated cutscene  
// *************************************************************************************************************
sweet6_m_stage_1:
		
		// initialisation for stage
		IF m_goals = 0
			SET_AREA_VISIBLE 1
			LOAD_CUTSCENE SWEET6A
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			START_CUTSCENE
			DO_FADE 1000 FADE_IN
			WHILE NOT HAS_CUTSCENE_FINISHED
				WAIT 0
			ENDWHILE
			
			CLEAR_CUTSCENE
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_AREA_VISIBLE 0		
		m_goals++
		ENDIF
		
		// stage specific stuff
		IF m_goals = 1
			LOAD_SCENE 2519.5752 -1677.8802 13.7661
			SET_CHAR_COORDINATES scplayer 2519.5752 -1677.8802 13.7661 
			SET_CHAR_HEADING scplayer 107.8934
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			RESTORE_CAMERA_JUMPCUT

			CLEAR_AREA 2519.5752 -1677.8802 13.7661 1000.0 TRUE

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS 
				WAIT 0
			ENDWHILE
			PRINT S6_01 10000 1 // Go to the garage in willowfield
			ADD_BLIP_FOR_COORD 2653.0159 -2009.1724 12.5547	location_blip
			m_goals++

		ENDIF

		IF m_goals = 2
			m_goals = 99
		ENDIF
			
		IF m_goals = 99
			GOSUB SWEET6_next_stage
		ENDIF

RETURN 


// *************************************************************************************************************
//							STAGE 2 - 2nd Launch of level - triggered when player goes to
//							          mod garage.  
// *************************************************************************************************************
sweet6_m_stage_2:
		
		// wait for player to arrive at blip
		IF m_goals = 0
			temp_int = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2653.0159 -2009.1724 12.5547 4.0 4.0 4.0 TRUE
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF IS_VEHICLE_ON_ALL_WHEELS	car
						SET_PLAYER_CONTROL player1 OFF
						temp_int = 1
					ENDIF
				ENDIF
				IF IS_CHAR_ON_FOOT scplayer
					IF IS_CHAR_STOPPED scplayer
						SET_PLAYER_CONTROL player1 OFF
						temp_int = 1
					ENDIF
				ENDIF
				IF temp_int = 1
					IF DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_blip
					ENDIF
					m_goals++	
				ENDIF
			ENDIF
			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					SET_CHAR_COORDINATES scplayer lowrideX lowrideY lowrideZ
				ENDIF
			ENDIF
		ENDIF

		// wait for car to stop
		IF m_goals = 1
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF IS_CAR_STOPPED car
					m_goals++	
				ENDIF
			ELSE
				m_goals++
			ENDIF
		ENDIF


		// initialisation for stage
		IF m_goals = 2
		
			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			CLEAR_PRINTS

			REQUEST_COLLISION 2644.2524 -2028.2457
			LOAD_SCENE 2644.2524 -2028.2457 12.5547
			
			SWITCH_WIDESCREEN ON	 
			// set camera position	  
			SET_FIXED_CAMERA_POSITION 2640.3623 -2011.1040 14.9634  0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 2640.9590 -2011.8862 14.7846 JUMP_CUT

			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			MAKE_PLAYER_GANG_DISAPPEAR 

			CLEAR_AREA 2648.9414 -2017.2471 12.5625 15.0 TRUE
			CLEAR_AREA 2643.8755 -2029.4388 12.5547	15.0 TRUE
					
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer 2641.7434 -2015.4962 12.6446
			SET_CHAR_HEADING scplayer 214.4731

			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0

			stop_gargae_for_neil = 1

			// requests	
			REQUEST_MODEL WMYMECH
			REQUEST_MODEL SAVANNA
			LOAD_ALL_MODELS_NOW
			WHILE NOT HAS_MODEL_LOADED WMYMECH
			   OR NOT HAS_MODEL_LOADED SAVANNA
			   
				WAIT 0
			ENDWHILE
			REQUEST_ANIMATION CAR 
			REQUEST_ANIMATION GANGS
			WHILE NOT HAS_ANIMATION_LOADED CAR 
			   OR NOT HAS_ANIMATION_LOADED GANGS
				WAIT 0
			ENDWHILE

			REQUEST_CAR_RECORDING 552
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 552
				WAIT 0
			ENDWHILE

			// create car
			CREATE_CAR SAVANNA 2644.2524 -2028.2457 12.5547 pickup_car	
			SET_CAR_HEADING pickup_car 358.8272
			SET_CAR_HYDRAULICS pickup_car TRUE
			SET_CAR_STRAIGHT_LINE_DISTANCE pickup_car 40
			SET_CAR_DRIVING_STYLE  pickup_car DRIVINGMODE_PLOUGHTHROUGH
			
			CREATE_CHAR_INSIDE_CAR pickup_car PEDTYPE_CIVMALE WMYMECH mechanic_dude
			SET_CHAR_DECISION_MAKER mechanic_dude empty_dm
			TASK_LOOK_AT_CHAR scplayer mechanic_dude 99999
			
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			START_NEW_SCRIPT cleanup_audio_lines
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

		m_goals++
		ENDIF
			
		// skip
		IF m_goals > 2
		AND m_goals < 17
	        IF IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 17
		    ENDIF
		ENDIF
		
		// guy drives car out
		IF m_goals = 3
			IF NOT IS_CAR_DEAD pickup_car
				FREEZE_CAR_POSITION pickup_car FALSE
				SET_CAR_FORWARD_SPEED pickup_car 2.0
				SET_CAR_CRUISE_SPEED pickup_car 4.0
				CAR_GOTO_COORDINATES_ACCURATE pickup_car 2644.4622 -2016.2823 12.5547
				TIMERA = 0
				TIMERB = 0
				temp_int = 0 
				SET_CAR_STATUS pickup_car STATUS_PHYSICS
				m_goals++
			ENDIF
 		ENDIF	

		// wait for car to stop
		IF m_goals = 4
			IF TIMERA < 15000
				IF NOT IS_CAR_DEAD pickup_car
					IF LOCATE_STOPPED_CAR_3D pickup_car 2644.4622 -2017.2823 12.5547 4.0 4.0 4.0 FALSE
						IF DOES_CAR_HAVE_HYDRAULICS pickup_car	
							CONTROL_CAR_HYDRAULICS pickup_car 0.0 0.0 0.0 0.0
						ENDIF
						m_goals++
					ELSE
						IF temp_int < 2 // do 2 bounces
							IF TIMERB < 1800
							AND TIMERB > 1500
								wheel_fl = 1.0 
								wheel_bl = 0.0
								wheel_fr = 1.0
								wheel_br = 0.0
							ELSE
								IF TIMERB > 1800
									IF TIMERB < 2500
										wheel_fl = 0.0 
										wheel_bl = 0.0
										wheel_fr = 0.0
										wheel_br = 0.0
									ELSE
										TIMERB = 1300
										temp_int++
									ENDIF
								ENDIF
							ENDIF
						ENDIF	
						
						// make car bounce
						IF DOES_CAR_HAVE_HYDRAULICS	pickup_car
							CONTROL_CAR_HYDRAULICS pickup_car wheel_fl wheel_bl wheel_fr wheel_br 
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_CAR_DEAD pickup_car
					SET_CAR_COORDINATES pickup_car 2644.4622 -2017.2823 12.5547
					SET_CAR_HEADING pickup_car 358.5157
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// mechanic dude gets out and talks to player.
		IF m_goals = 5
			IF NOT IS_CHAR_DEAD mechanic_dude
				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CAR_DEAD pickup_car
						TASK_LEAVE_CAR -1 pickup_car
					ENDIF
					TASK_TURN_CHAR_TO_FACE_CHAR	-1 scplayer
					TASK_LOOK_AT_CHAR -1 scplayer -2 
					TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK mechanic_dude temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				
				TIMERA = 0
				m_goals++ 
			ENDIF
		ENDIF


		// wait for the mechanic dude to get outside car
		IF m_goals = 6
			IF NOT IS_CHAR_DEAD mechanic_dude
				IF NOT IS_CAR_DEAD pickup_car
					IF NOT IS_CHAR_IN_CAR mechanic_dude pickup_car
						IF IS_CHAR_ON_FOOT mechanic_dude 							
							m_goals++
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// second camera angle
		IF m_goals = 7
			
			SET_FIXED_CAMERA_POSITION 2640.2000 -2018.0593 14.3965  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT     2641.1350 -2017.7582 14.2101  JUMP_CUT

			SET_CHAR_COORDINATES scplayer 2642.6584 -2015.2910 12.5947 
			SET_CHAR_HEADING scplayer 181.4479
												 
			IF NOT IS_CHAR_DEAD mechanic_dude	   
				SET_CHAR_COORDINATES mechanic_dude 2641.8618 -2018.1082 12.2327 //2641.9785 -2017.1935 12.7019 
				SET_CHAR_HEADING mechanic_dude 353.0400	
				TASK_LOOK_AT_CHAR mechanic_dude scplayer -2
			ENDIF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			OPEN_SEQUENCE_TASK temp_seq
				IF NOT IS_CHAR_DEAD mechanic_dude
					TASK_LOOK_AT_CHAR -1 mechanic_dude 99999
					TASK_GO_STRAIGHT_TO_COORD -1 2642.3914 -2016.5813 12.5947 PEDMOVE_WALK 5000
					TASK_TURN_CHAR_TO_FACE_CHAR -1 mechanic_dude
					//TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
				ELSE
					TASK_PAUSE -1 1000
				ENDIF
	 		CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK scplayer temp_seq
			CLEAR_SEQUENCE_TASK temp_seq

			IF NOT IS_CHAR_DEAD mechanic_dude
				CLEAR_CHAR_TASKS_IMMEDIATELY mechanic_dude
				OPEN_SEQUENCE_TASK temp_seq
					TASK_LOOK_AT_CHAR -1 scplayer -2
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK mechanic_dude temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF			

			dialogue_flag = 0

			TIMERA = 500
			m_goals++
		ENDIF

		// wait for dialogue to finish
		IF m_goals = 8

			// make mechanic face player
			IF NOT IS_CHAR_DEAD mechanic_dude
				GET_CHAR_COORDINATES scplayer x y z
				GET_CHAR_COORDINATES mechanic_dude x2 y2 z2
				vec_x = x - x2
				vec_y = y - y2
				GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
				SET_CHAR_HEADING mechanic_dude heading
			ENDIF

			IF audio_line_is_active = 0
			AND TIMERA > 500
				SWITCH dialogue_flag
					CASE 0
						// So, you must be Sweet’s bro’ huh?
						$audio_string    = &LOWR_KA		   
						audio_sound_file = SOUND_LOWR_KA
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_LOWR_KB
						dialogue_flag++		
					BREAK	
					CASE 1
						
						$audio_string    = &LOWR_KB		    // He phoned ahead, said you might be looking for a car that bounces.
						audio_sound_file = SOUND_LOWR_KB
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_LOWR_KC
						dialogue_flag++
					BREAK
					CASE 2
						
						$audio_string    = &LOWR_KC		   // Well I owe him big from way back, so here, this should do the trick. 
						audio_sound_file = SOUND_LOWR_KC
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 1 1
						dialogue_flag++
					BREAK
					CASE 3
						IF NOT IS_CHAR_DEAD mechanic_dude
							CLEAR_CHAR_TASKS mechanic_dude
						ENDIF
						m_goals++
					BREAK
				ENDSWITCH
			ENDIF
		ENDIF


		// player gets in car
		IF m_goals = 9
			IF NOT IS_CAR_DEAD pickup_car
				SET_RADIO_CHANNEL  RS_CLASSIC_HIP_HOP
				CLEAR_LOOK_AT scplayer
				OPEN_SEQUENCE_TASK temp_seq
					TASK_ENTER_CAR_AS_DRIVER -1 pickup_car 10000
					TASK_PLAY_ANIM -1 sit_relaxed CAR 4.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK scplayer temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				TIMERA = 0

				IF NOT IS_CHAR_DEAD mechanic_dude
					TASK_LOOK_AT_CHAR mechanic_dude scplayer -2
				ENDIF	

				m_goals++
			ENDIF
		ENDIF

		// wait for player to get in car
		IF m_goals = 10
			// turn mechanic to face player once he's in the car
			IF NOT IS_CAR_DEAD pickup_car
				IF IS_CHAR_IN_CAR scplayer pickup_car
				AND NOT IS_CHAR_ON_FOOT scplayer
					IF NOT IS_CHAR_DEAD mechanic_dude
						FREEZE_CHAR_POSITION mechanic_dude TRUE
					ENDIF
					m_goals++	
				ENDIF
			ENDIF
		ENDIF

		// mechanic talks some more
		IF m_goals = 11
			IF NOT IS_CHAR_DEAD mechanic_dude
				OPEN_SEQUENCE_TASK temp_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_LOOK_AT_CHAR -1 scplayer -2
					TASK_PLAY_ANIM -1 prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkG GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 idle_chat PED 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK mechanic_dude temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				m_goals++
				temp_int = 0
				TIMERA = 500
				TIMERB = 0
				wheel_fl = 0.0 
				wheel_bl = 0.0
				wheel_fr = 0.0
				wheel_br = 0.0
				dialogue_flag = 0
				IF NOT IS_CAR_DEAD pickup_car
					SET_CAR_STATUS pickup_car STATUS_PHYSICS
				ENDIF
			ENDIF
		ENDIF	



		// do dialogue
		IF m_goals = 12
			IF audio_line_is_active = 0
			AND TIMERA > 500
				SWITCH dialogue_flag
					CASE 0
						// Custom springs should see you hopping all the way home! Try her out.
						$audio_string    = &LOWR_KD		   
						audio_sound_file = SOUND_LOWR_KD
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 2 0
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_LOWR_KE
						dialogue_flag++		
					BREAK	
					CASE 1
						// Very popular with the ese’s – they compete in these things.
						$audio_string    = &LOWR_KE		   
						audio_sound_file = SOUND_LOWR_KE
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_LOWR_KF
						dialogue_flag++
					BREAK
					CASE 2
						// You can usually find them over by Unity Station.
						$audio_string    = &LOWR_KF		   
						audio_sound_file = SOUND_LOWR_KF
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_LOWR_KG
						dialogue_flag++
					BREAK
					CASE 3
					  	// If you ever want to mod your ride, come back any time, man.
						$audio_string    = &LOWR_KG		   
						audio_sound_file = SOUND_LOWR_KG
						START_NEW_SCRIPT audio_line mechanic_dude 0 1 1 1
						dialogue_flag++
					BREAK
					CASE 4
						IF NOT IS_CHAR_DEAD mechanic_dude
							CLEAR_CHAR_TASKS mechanic_dude
						ENDIF
						m_goals++
					BREAK
				ENDSWITCH
			ENDIF

			IF NOT IS_CAR_DEAD pickup_car
				// player bounces car
				IF temp_int < 4 // do 4 bounces
					IF TIMERB < 1800
					AND TIMERB > 1500
						wheel_fl = 1.0 
						wheel_bl = 0.0
						wheel_fr = 1.0
						wheel_br = 0.0
					ELSE
						IF TIMERB > 1800
							IF TIMERB < 2500
								wheel_fl = 0.0 
								wheel_bl = 0.0
								wheel_fr = 0.0
								wheel_br = 0.0
							ELSE
								TIMERB = 1300
								temp_int++
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF temp_int = 4
						TASK_PLAY_ANIM scplayer tap_hand CAR 4.0 TRUE FALSE FALSE FALSE -1
						temp_int++
					ENDIF
				ENDIF	
				IF DOES_CAR_HAVE_HYDRAULICS pickup_car
					CONTROL_CAR_HYDRAULICS pickup_car wheel_fl wheel_bl wheel_fr wheel_br
				ENDIF
			ENDIF

		ENDIF


		IF IS_PLAYER_PLAYING player1
			IF IS_CHAR_PLAYING_ANIM scplayer tap_hand
				SET_CHAR_ANIM_SPEED scplayer tap_hand 0.5
			ENDIF
		ENDIF
		
		// wait for dialogue to finish
		IF m_goals = 13
			IF sw6_mission_attempts = 0
				//PRINT S6_21 4000 1 //CJ - 'I think I'll have a look just now.'
				IF NOT IS_CHAR_DEAD mechanic_dude
					CLEAR_CHAR_TASKS mechanic_dude
					FREEZE_CHAR_POSITION mechanic_dude FALSE
					TASK_GO_STRAIGHT_TO_COORD mechanic_dude 2641.2310 -2020.6215 12.7578 PEDMOVE_WALK 5000
				ENDIF
				IF NOT IS_CAR_DEAD pickup_car
					IF DOES_CAR_HAVE_HYDRAULICS pickup_car
						CONTROL_CAR_HYDRAULICS pickup_car 0.0 0.0 0.0 0.0 
					ENDIF
				ENDIF
				m_goals++
				TIMERA = 0
				TIMERB = 0
			ELSE
				m_goals = 17
			ENDIF
		ENDIF

		// switch camera angle
		IF m_goals = 14
			IF TIMERA > 1000
					  
				SET_FIXED_CAMERA_POSITION 2643.9846 -2023.7491 14.9500 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT     2644.0908 -2022.8002 14.6533 JUMP_CUT
				IF NOT IS_CAR_DEAD pickup_car 
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					WARP_CHAR_INTO_CAR scplayer pickup_car
					START_PLAYBACK_RECORDED_CAR pickup_car 552
					PAUSE_PLAYBACK_RECORDED_CAR pickup_car
					m_goals++
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF

		IF m_goals = 15
			IF TIMERA  > 1000
				IF NOT IS_CAR_DEAD pickup_car 
					IF IS_PLAYBACK_GOING_ON_FOR_CAR pickup_car
						UNPAUSE_PLAYBACK_RECORDED_CAR pickup_car
						m_goals++
						TIMERA = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		// wait for car to go off screen
		IF m_goals = 16
			IF NOT IS_CAR_DEAD pickup_car
				IF NOT IS_CAR_ON_SCREEN pickup_car
				OR TIMERA > 3000
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// fade out and set up car for mod garage
		IF m_goals = 17
			CLEAR_PRINTS
			SWITCH_WIDESCREEN OFF
			MAKE_PLAYER_GANG_REAPPEAR
			RESTORE_CAMERA_JUMPCUT
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			stop_gargae_for_neil = 0
			IF NOT IS_CAR_DEAD pickup_car
				IF IS_PLAYBACK_GOING_ON_FOR_CAR pickup_car
					STOP_PLAYBACK_RECORDED_CAR pickup_car
				ENDIF
				FREEZE_CAR_POSITION pickup_car FALSE
				IF DOES_CHAR_EXIST mechanic_dude
					DELETE_CHAR mechanic_dude
				ENDIF
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				WARP_CHAR_INTO_CAR scplayer pickup_car
				IF IS_PLAYBACK_GOING_ON_FOR_CAR pickup_car
					STOP_PLAYBACK_RECORDED_CAR pickup_car
				ENDIF
				GET_CAR_HEADING pickup_car heading
				SET_CAR_COORDINATES pickup_car 2644.2583 -2026.5259 12.5469 
				SET_CAR_HEADING pickup_car 2.3109
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_IN_FRONT_OF_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF

			DELETE_CHAR mechanic_dude
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			REMOVE_ANIMATION CAR 
			REMOVE_ANIMATION GANGS
			
			START_NEW_SCRIPT cleanup_audio_lines
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2	

			IF NOT sw6_mission_attempts = 0
				m_goals = 99
			ELSE	
				m_goals++
			ENDIF

		ENDIF

		// tell player to go into garage
		IF m_goals = 18
			REMOVE_BLIP location_blip
			ADD_BLIP_FOR_COORD LS_LR_GARAGEX LS_LR_GARAGEY LS_LR_GARAGEZ location_blip

			ACTIVATE_GARAGE modlast 

			PRINT_NOW S6_38 7000 1 // ~s~Back up the lowrider into the ~y~mod garage~s~.

			m_goals++
		ENDIF

		// wait for player to get into garage
		IF m_goals = 19
			IF NOT mod_flag = 0
				CLEAR_PRINTS
				REMOVE_BLIP location_blip
				m_goals++ 
			ELSE

				IF NOT IS_CAR_DEAD pickup_car
					IF NOT IS_CHAR_IN_CAR scplayer pickup_car
						IF DOES_BLIP_EXIST location_blip
							REMOVE_BLIP location_blip
						ENDIF
						IF NOT DOES_BLIP_EXIST car_blip
							ADD_BLIP_FOR_CAR pickup_car car_blip
							SET_BLIP_AS_FRIENDLY car_blip TRUE
							PRINT_NOW S6_23 5000 1 // get in the lowrider
						ENDIF 
					ELSE
						IF DOES_BLIP_EXIST car_blip
							REMOVE_BLIP car_blip
							CLEAR_THIS_PRINT S6_23
						ENDIF
						IF NOT DOES_BLIP_EXIST location_blip
							ADD_BLIP_FOR_COORD LS_LR_GARAGEX LS_LR_GARAGEY LS_LR_GARAGEZ location_blip
							PRINT_NOW S6_38 7000 1 // ~s~Back up the lowrider into the ~y~mod garage~s~.
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		// wait for player to finish in mod garage
		IF m_goals = 20
			IF mod_flag = 3
			AND control_flag_mod = 3
				IF NOT IS_CAR_DEAD pickup_car
					IF IS_CHAR_IN_CAR scplayer pickup_car
				 		SET_CAR_HEADING pickup_car 0.0
					ENDIF
				ENDIF
				m_goals = 99
			ENDIF
			IF mod_flag = 0
				IF NOT IS_CAR_DEAD pickup_car
					IF IS_CHAR_IN_CAR scplayer pickup_car
						SET_CAR_HEADING pickup_car 0.0
					ENDIF
				ENDIF
				m_goals = 99
			ENDIF
		ENDIF

		
	
		// if car is in any car with hydraulics - make it the pickup car
		IF m_goals > 12
			temp_int = 0
			IF mod_flag = 3
			AND control_flag_mod = 3
				temp_int = 1
			ENDIF
			IF mod_flag = 0
				temp_int = 1
			ENDIF
			
			IF temp_int = 1
				// if car is in any car with hydraulics - make it the pickup car
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF NOT IS_CAR_DEAD pickup_car
						IF NOT IS_CHAR_IN_CAR scplayer pickup_car
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							IF DOES_CAR_HAVE_HYDRAULICS car
								MARK_CAR_AS_NO_LONGER_NEEDED pickup_car
								STORE_CAR_CHAR_IS_IN scplayer pickup_car
							ENDIF
						ENDIF
					ELSE
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF DOES_CAR_HAVE_HYDRAULICS car
							MARK_CAR_AS_NO_LONGER_NEEDED pickup_car
							STORE_CAR_CHAR_IS_IN scplayer pickup_car
						ENDIF
					ENDIF
				ENDIF
			
			ENDIF
		ENDIF


		
		// exit
		IF m_goals = 99
			
			MAKE_PLAYER_GANG_REAPPEAR

			sw6_mission_attempts++
			//WRITE_DEBUG_WITH_INT sw6_mission_attempts sw6_mission_attempts
			ACTIVATE_GARAGE modlast

			CLEAR_PRINTS

			IF DOES_CHAR_EXIST mechanic_dude
				DELETE_CHAR mechanic_dude
			ENDIF

			stop_gargae_for_neil = 1

			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			REMOVE_ANIMATION CAR 
			REMOVE_ANIMATION GANGS
			REMOVE_CAR_RECORDING 552

			SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0

			GOSUB SWEET6_next_stage
		ENDIF

RETURN 

// *************************************************************************************************************
//							STAGE 3 - Player has to go to lowrider meeting							          
// *************************************************************************************************************

sweet6_m_stage_3:
		
		IF m_goals = 0
			
			// put up text and blip
			IF NOT IS_CAR_DEAD pickup_car
				IF IS_CHAR_IN_CAR scplayer pickup_car
					PRINT_NOW S6_03 7000 1 // get to the lowrider meeting
					GOTO sweet6_m_stage_3_blip
				ENDIF
			ENDIF
			
sweet6_m_stage_3_blip:
			IF NOT DOES_BLIP_EXIST location_blip
				ADD_BLIP_FOR_COORD 1793.0676 -1904.2538 12.3989 location_blip
			ENDIF

			// set flags
			player_has_attacked_meet 	= 0
			meeting_is_loaded			= 0

			// set floats
			load_car_x[0] =	1796.9468 	 // top left
			load_car_y[0] =	-1924.3765 
			load_car_z[0] =	13.2937 
			load_car_h[0] =	60.9627 

			load_car_x[1] =	1802.8647 	 // infront of middle car
			load_car_y[1] =	-1918.7424 
			load_car_z[1] =	13.2965 
			load_car_h[1] =	357.6195

			load_car_x[2] =	1788.9922 	  // top right
			load_car_y[2] =	-1924.5015 
			load_car_z[2] =	13.2937 
			load_car_h[2] =	305.5176

			load_car_x[3] =	1784.1887 	 // behind middle car
			load_car_y[3] =	-1913.4397 
			load_car_z[3] =	13.2957 
			load_car_h[3] =	188.9639

			load_car_x[4] =	1790.9639 	 // middle car
			load_car_y[4] =	-1913.8983 
			load_car_z[4] =	13.2985 
			load_car_h[4] =	266.2616

			load_car_x[5] =	1799.8992 	 // nearest entrance left
			load_car_y[5] =	-1906.4248 
			load_car_z[5] =	13.3025 
			load_car_h[5] =	184.4735
								  
			load_car_x[6] =	1785.9397 	 // entrance right
			load_car_y[6] =	-1904.8394 
			load_car_z[6] =	13.2970 
			load_car_h[6] =	152.5742

			load_ped_x[0] = 1798.6031 
			load_ped_y[0] = -1908.2596
			load_ped_z[0] = 12.4388 
			load_ped_h[0] = 102.3995
			 
			load_ped_x[1] = 1798.8867 
			load_ped_y[1] = -1907.6678
			load_ped_z[1] = 12.4399 
			load_ped_h[1] = 174.1189

			load_ped_x[2] = 1798.8340 
			load_ped_y[2] = -1911.7822
			load_ped_z[2] = 12.4374 
			load_ped_h[2] = 158.2591
							
			load_ped_x[3] = 1799.5836 
			load_ped_y[3] = -1912.7948
			load_ped_z[3] = 12.4368 
			load_ped_h[3] = 74.6392 

			load_ped_x[4] = 1800.9021 
			load_ped_y[4] = -1918.7135
			load_ped_z[4] = 12.4331 
			load_ped_h[4] = 256.6391
			  
			load_ped_x[5] = 1798.1663 
			load_ped_y[5] = -1920.4265
			load_ped_z[5] = 12.4321 
			load_ped_h[5] = 19.6592 

			load_ped_x[6] = 1796.8108 
			load_ped_y[6] = -1920.4260
			load_ped_z[6] = 12.4321 
			load_ped_h[6] = 331.8393

			load_ped_x[7] = 1787.7799 
			load_ped_y[7] = -1919.0714
			load_ped_z[7] = 12.4329 
			load_ped_h[7] = 331.8393

			load_ped_x[8] = 1786.7098 
			load_ped_y[8] = -1917.9816
			load_ped_z[8] = 12.4332 
			load_ped_h[8] = 285.8198

			load_ped_x[9] = 1786.1943 
			load_ped_y[9] = -1907.3561
			load_ped_z[9] = 12.4327 
			load_ped_h[9] = 260.4003

			load_ped_x[10] = 1786.8982 
			load_ped_y[10] = -1906.5308
			load_ped_z[10] = 12.4335 
			load_ped_h[10] = 141.9807

			load_ped_x[11] = 1786.2305 
			load_ped_y[11] = -1908.6224
			load_ped_z[11] = 12.4328 
			load_ped_h[11] = 327.1608

			load_ped_x[12] = 1789.1875 
			load_ped_y[12] = -1919.6648
			load_ped_z[12] = 12.4325 
			load_ped_h[12] = 357.2196

			load_ped_x[13] = 1788.1033 
			load_ped_y[13] = -1907.9491
			load_ped_z[13] = 12.4352 
			load_ped_h[13] = 200.9411 

			load_ped_x[14] = 1797.9966 
			load_ped_y[14] = -1912.7402
			load_ped_z[14] = 12.4368 
			load_ped_h[14] = 312.2806 

			load_ped_x[15] = 1796.4597 
			load_ped_y[15] = -1908.8817
			load_ped_z[15] = 12.4368 
			load_ped_h[15] = 312.2806 


			REQUEST_MODEL VLA1
			REQUEST_MODEL VLA2
			REQUEST_MODEL VLA3
			REQUEST_MODEL BFYPRO
			REQUEST_MODEL HFYST

			REQUEST_MODEL SAVANNA
			REQUEST_MODEL REMINGTN

			REQUEST_ANIMATION LOWRIDER

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED VLA1
			   OR NOT HAS_MODEL_LOADED VLA2
			   OR NOT HAS_MODEL_LOADED VLA3
			   OR NOT HAS_MODEL_LOADED BFYPRO
			   OR NOT HAS_MODEL_LOADED HFYST
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED SAVANNA
			   OR NOT HAS_MODEL_LOADED REMINGTN
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_ANIMATION_LOADED LOWRIDER
				WAIT 0
			ENDWHILE
			
			SWITCH_AUDIO_ZONE LOWRIDE TRUE
	
			m_goals++

		ENDIF

		// update gathering behaviour
		IF m_goals = 1	
				
			// debug warp
			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					IF NOT IS_CAR_DEAD pickup_car
						SET_CAR_COORDINATES pickup_car 1800.9784 -1895.7749 12.4046 
						SET_CAR_HEADING pickup_car 136.3521	
					ENDIF
				ENDIF
			ENDIF
				
			// if player gets out car remove location blip
			IF NOT IS_CAR_DEAD pickup_car
				IF NOT IS_CHAR_IN_CAR scplayer pickup_car
					IF DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_blip
					ENDIF
					IF NOT DOES_BLIP_EXIST car_blip
						//PRINT_NOW S6_23 7000 1 // get back in the lowrider
						PRINT_NOW S6_35 5000 1 // you need to take the lowrider with you
						ADD_BLIP_FOR_CAR pickup_car car_blip
						SET_BLIP_AS_FRIENDLY car_blip TRUE
					ENDIF
				ELSE
					IF NOT DOES_BLIP_EXIST location_blip
						PRINT_NOW S6_03 7000 1 // get to the lowrider meeting
						ADD_BLIP_FOR_COORD 1793.0676 -1904.2538 12.3989 location_blip
					ENDIF
					IF DOES_BLIP_EXIST car_blip
						REMOVE_BLIP car_blip
					ENDIF
				ENDIF
			ENDIF


			// if car is in any car with hydraulics - make it the pickup car

			temp_int = 0
			IF mod_flag = 3
			AND control_flag_mod = 3
				temp_int = 1
			ENDIF
			IF mod_flag = 0
				temp_int = 1
			ENDIF
			
			IF temp_int = 1
				// if car is in any car with hydraulics - make it the pickup car
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF NOT IS_CAR_DEAD pickup_car
						IF NOT IS_CHAR_IN_CAR scplayer pickup_car
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							IF DOES_CAR_HAVE_HYDRAULICS car
								MARK_CAR_AS_NO_LONGER_NEEDED pickup_car
								STORE_CAR_CHAR_IS_IN scplayer pickup_car
							ENDIF
						ENDIF
					ELSE
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF DOES_CAR_HAVE_HYDRAULICS car
							MARK_CAR_AS_NO_LONGER_NEEDED pickup_car
							STORE_CAR_CHAR_IS_IN scplayer pickup_car
						ENDIF
					ENDIF
				ENDIF
			
			ENDIF

			
			IF meeting_is_loaded = 0
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1792.9243 -1918.9873 150.0 150.0 FALSE

					// create initial cars												
					CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[0]	  // top left						
					CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[1]	  // infront of middle car						
					CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[2]	  // top right						
					CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[3]	  // behind middle car
					CHANGE_CAR_COLOUR meet_car[0] 1  0
					CHANGE_CAR_COLOUR meet_car[1] 2  1									 
					CHANGE_CAR_COLOUR meet_car[2] 3  2
					CHANGE_CAR_COLOUR meet_car[3] 10 3					
					SET_CAR_STATUS meet_car[0] STATUS_PHYSICS						
					SET_CAR_STATUS meet_car[3] STATUS_PHYSICS
					SET_CAR_HYDRAULICS meet_car[0] TRUE
					SET_CAR_HYDRAULICS meet_car[3] TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[0] TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[3] TRUE

					WAIT 0

					CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[4]	  // middle car						
					CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[5]	  // nearest entrance left						
					CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[6]	  // entrance right
					CHANGE_CAR_COLOUR meet_car[4] 9	 4
					CHANGE_CAR_COLOUR meet_car[5] 6	 5
					CHANGE_CAR_COLOUR meet_car[6] 7	 6
					SET_CAR_STATUS meet_car[4] STATUS_PHYSICS
					SET_CAR_HYDRAULICS meet_car[4] TRUE									
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[4] TRUE
					
					WAIT 0

					temp_int = 0
					WHILE temp_int < 7
						IF NOT IS_CAR_DEAD meet_car[temp_int]
							SET_CAR_COORDINATES meet_car[temp_int] load_car_x[temp_int] load_car_y[temp_int] load_car_z[temp_int]
							SET_CAR_HEADING meet_car[temp_int] load_car_h[temp_int]
							LOCK_CAR_DOORS meet_car[temp_int] CARLOCK_LOCKOUT_PLAYER_ONLY
						ENDIF			
					temp_int++
					ENDWHILE

					WAIT 0

					// create initial peds
					// guys																
					CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[0] 
					CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[2]
					CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[3]
					CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[5]
					CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[6]			
					CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[8]
					CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[9]
					CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[11] 
					CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[12]
					
					WAIT 0

					// girls
					CREATE_CHAR PEDTYPE_MISSION1 HFYST   0.0 0.0 0.0 meet_ped[1]
					CREATE_CHAR PEDTYPE_MISSION1 BFYPRO  0.0 0.0 0.0 meet_ped[4]
					CREATE_CHAR PEDTYPE_MISSION1 HFYST   0.0 0.0 0.0 meet_ped[7]
					CREATE_CHAR PEDTYPE_MISSION1 BFYPRO  0.0 0.0 0.0 meet_ped[10]

					WAIT 0

					// wanderers
					CREATE_CHAR PEDTYPE_MISSION1 VLA1   0.0 0.0 0.0  meet_ped[13]
					CREATE_CHAR PEDTYPE_MISSION1 VLA2   0.0 0.0 0.0  meet_ped[14]
					CREATE_CHAR PEDTYPE_MISSION1 HFYST  0.0 0.0 0.0  meet_ped[15]
	
					WAIT 0

					// drivers
					CREATE_CHAR PEDTYPE_MISSION1 VLA1  0.0 0.0 0.0 meet_ped[16]
					CREATE_CHAR PEDTYPE_MISSION1 VLA2  0.0 0.0 0.0 meet_ped[17]
					CREATE_CHAR PEDTYPE_MISSION1 VLA3  0.0 0.0 0.0 meet_ped[18]
					CREATE_CHAR PEDTYPE_MISSION1 VLA2  0.0 0.0 0.0 meet_ped[19]
					CREATE_CHAR PEDTYPE_MISSION1 VLA3  0.0 0.0 0.0 meet_ped[20]

					// passenger
					CREATE_CHAR PEDTYPE_MISSION1 BFYPRO  0.0 0.0 0.0 meet_ped[21]

					WAIT 0

					// set all on foot chars positions
					temp_int = 0
					WHILE temp_int < 16
						IF DOES_CHAR_EXIST meet_ped[temp_int]
							IF NOT IS_CHAR_DEAD meet_ped[temp_int]
								SET_CHAR_COORDINATES meet_ped[temp_int] load_ped_x[temp_int] load_ped_y[temp_int] load_ped_z[temp_int]
								SET_CHAR_HEADING meet_ped[temp_int] load_ped_h[temp_int]
							ENDIF
						ENDIF
					temp_int++
					ENDWHILE

					// set some of the guys to tough decision makers
					temp_int = 0
					WHILE temp_int < 22
						IF DOES_CHAR_EXIST meet_ped[temp_int]
							IF NOT IS_CHAR_DEAD meet_ped[temp_int]
								SET_CHAR_DECISION_MAKER meet_ped[temp_int] tough_dm
							ENDIF
						ENDIF
					temp_int += 4
					ENDWHILE

					// put drivers in cars
					IF NOT IS_CHAR_DEAD meet_ped[16]
						IF NOT IS_CAR_DEAD meet_car[1]
							WARP_CHAR_INTO_CAR meet_ped[16] meet_car[1]
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD meet_ped[17]
						IF NOT IS_CAR_DEAD meet_car[2]
							WARP_CHAR_INTO_CAR meet_ped[17] meet_car[2]
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD meet_ped[18]
						IF NOT IS_CAR_DEAD meet_car[4]
							WARP_CHAR_INTO_CAR meet_ped[18] meet_car[4]
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD meet_ped[19]
						IF NOT IS_CAR_DEAD meet_car[0]
							WARP_CHAR_INTO_CAR meet_ped[19] meet_car[0]
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD meet_ped[20]
						IF NOT IS_CAR_DEAD meet_car[3]
							WARP_CHAR_INTO_CAR meet_ped[20] meet_car[3]
						ENDIF
					ENDIF 

					// put passenger in car
					IF DOES_CHAR_EXIST meet_ped[21]
						IF NOT IS_CHAR_DEAD meet_ped[21]
							IF NOT IS_CAR_DEAD meet_car[2]
								WARP_CHAR_INTO_CAR_AS_PASSENGER meet_ped[21] meet_car[2] 0
							ENDIF
						ENDIF
					ENDIF

					meeting_is_loaded = 1
					
				ENDIF
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1792.9243 -1918.9873 150.0 150.0 FALSE
					temp_int = 0
					WHILE temp_int < 7
						DELETE_CAR meet_car[temp_int]													// car 0 - 0
					temp_int++
					ENDWHILE
					temp_int = 0
					WHILE temp_int < 22
						DELETE_CHAR meet_ped[temp_int]
					temp_int++
					ENDWHILE

					meeting_is_loaded = 0
				ENDIF
			ENDIF

			IF meeting_is_loaded = 1
				IF player_has_attacked_meet = 0


					IF meet_jump_timer[0] < 300
						meet_wheel_fr[0] = 1.0
						meet_wheel_fl[0] = 1.0
						meet_wheel_br[0] = 0.0
						meet_wheel_bl[0] = 0.0
					ELSE
						IF meet_jump_timer[0] > 500 
							meet_jump_timer[0] = 0
						ELSE
							meet_wheel_fr[0] = 0.0
							meet_wheel_fl[0] = 0.0
							meet_wheel_br[0] = 0.0
							meet_wheel_bl[0] = 0.0		
						ENDIF
					ENDIF

					// car 1 - 3 
					IF meet_jump_timer[1] > 2000
						GENERATE_RANDOM_INT_IN_RANGE 0 16 hyd_index
						GOSUB set_hydraulics
						meet_wheel_fr[1] = hyd_rf			
						meet_wheel_fl[1] = hyd_lf 
						meet_wheel_br[1] = hyd_rb
						meet_wheel_bl[1] = hyd_lb
						meet_jump_timer[1] = 0
					ENDIF

					// car 2 - 4
					IF meet_jump_timer[2] > 300
						GENERATE_RANDOM_INT_IN_RANGE 0 16 hyd_index
						GOSUB set_hydraulics
						meet_wheel_fr[2] = hyd_rf			
						meet_wheel_fl[2] = hyd_lf 
						meet_wheel_br[2] = hyd_rb
						meet_wheel_bl[2] = hyd_lb
						meet_jump_timer[2] = 0
					ENDIF

					IF NOT IS_CAR_DEAD meet_car[0] 
						IF DOES_CAR_HAVE_HYDRAULICS meet_car[0]
							CONTROL_CAR_HYDRAULICS meet_car[0] meet_wheel_fl[0] meet_wheel_bl[0] meet_wheel_fr[0] meet_wheel_br[0]
							GET_CAR_COORDINATES meet_car[0] x y z
							vec_x = load_car_x[0] - x
							vec_y =	load_car_y[0] - y
							vec_x *= force_multiplier
							vec_y *= force_multiplier
							APPLY_FORCE_TO_CAR meet_car[0] vec_x vec_y 0.0 0.0 0.0 0.0
						ENDIF
					ENDIF
					IF NOT IS_CAR_DEAD meet_car[3]
						IF DOES_CAR_HAVE_HYDRAULICS meet_car[3]
							CONTROL_CAR_HYDRAULICS meet_car[3] meet_wheel_fl[1] meet_wheel_bl[1] meet_wheel_fr[1] meet_wheel_br[1]
							GET_CAR_COORDINATES meet_car[3] x y z
							vec_x = load_car_x[3] - x
							vec_y =	load_car_y[3] - y
							vec_x *= force_multiplier
							vec_y *= force_multiplier
							APPLY_FORCE_TO_CAR meet_car[3] vec_x vec_y 0.0 0.0 0.0 0.0
						ENDIF
					ENDIF
					IF NOT IS_CAR_DEAD meet_car[4]
						IF DOES_CAR_HAVE_HYDRAULICS meet_car[4]
							CONTROL_CAR_HYDRAULICS meet_car[4] meet_wheel_fl[2] meet_wheel_bl[2] meet_wheel_fr[2] meet_wheel_br[2]
							GET_CAR_COORDINATES meet_car[4] x y z
							vec_x = load_car_x[4] - x
							vec_y =	load_car_y[4] - y
							vec_x *= force_multiplier
							vec_y *= force_multiplier
							APPLY_FORCE_TO_CAR meet_car[4] vec_x vec_y 0.0 0.0 0.0 0.0
						ENDIF
					ENDIF


					SWITCH m_frame_num

						CASE 0
							// peds
							IF NOT IS_CHAR_DEAD meet_ped[0]
								GET_SCRIPT_TASK_STATUS meet_ped[0] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CAR_DEAD meet_car[5]
											IF LOCATE_CAR_3D meet_car[5] load_car_x[5] load_car_y[5] load_car_z[5] 0.2 0.2 1.0 FALSE
												TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ELSE
												TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ENDIF
										ELSE
											TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[0] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ELSE
									IF IS_CHAR_PLAYING_ANIM meet_ped[0] M_smklean_loop
										IF NOT IS_CAR_DEAD meet_car[5]
											IF NOT LOCATE_CAR_3D meet_car[5] load_car_x[5] load_car_y[5] load_car_z[5] 0.2 0.2 1.0 FALSE
												CLEAR_CHAR_TASKS meet_ped[0]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						BREAK
						CASE 1
							IF NOT IS_CHAR_DEAD meet_ped[1]
								GET_SCRIPT_TASK_STATUS meet_ped[1] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CAR_DEAD meet_car[5]
											IF LOCATE_CAR_3D meet_car[5] load_car_x[5] load_car_y[5] load_car_z[5] 0.2 0.2 1.0 FALSE
												TASK_PLAY_ANIM -1 F_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ELSE
												TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ENDIF
										ELSE
											TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[1] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ELSE
									IF IS_CHAR_PLAYING_ANIM meet_ped[1] F_smklean_loop
										IF NOT IS_CAR_DEAD meet_car[5] 
											IF NOT LOCATE_CAR_3D meet_car[5] load_car_x[5] load_car_y[5] load_car_z[5] 0.2 0.2 1.0 FALSE
												CLEAR_CHAR_TASKS meet_ped[1]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						BREAK
						CASE 2
							IF NOT IS_CHAR_DEAD meet_ped[2]
								GET_SCRIPT_TASK_STATUS meet_ped[2] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CHAR_DEAD meet_ped[3]
											TASK_CHAT_WITH_CHAR -1 meet_ped[3] TRUE TRUE
										ELSE
											TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[2] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 3
							IF NOT IS_CHAR_DEAD meet_ped[3]
								GET_SCRIPT_TASK_STATUS meet_ped[3] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CHAR_DEAD meet_ped[2]
											TASK_CHAT_WITH_CHAR -1 meet_ped[2] FALSE TRUE
										ELSE
											TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[3] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 4
							IF NOT IS_CHAR_DEAD meet_ped[4]
								GET_SCRIPT_TASK_STATUS meet_ped[4] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 car_hookertalk PED 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[4] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 5
							IF NOT IS_CHAR_DEAD meet_ped[5]
								GET_SCRIPT_TASK_STATUS meet_ped[5] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CHAR_DEAD meet_ped[6]
											TASK_CHAT_WITH_CHAR -1 meet_ped[6] FALSE TRUE
										ELSE
											TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[5] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 6			
							IF NOT IS_CHAR_DEAD meet_ped[6]
								GET_SCRIPT_TASK_STATUS meet_ped[6] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CHAR_DEAD meet_ped[5]
											TASK_CHAT_WITH_CHAR -1 meet_ped[5] TRUE TRUE
										ELSE
											TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[6] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF	
						BREAK
						CASE 7			
							IF NOT IS_CHAR_DEAD meet_ped[7]
								GET_SCRIPT_TASK_STATUS meet_ped[7] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[7] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 8
							IF NOT IS_CHAR_DEAD meet_ped[8]
								GET_SCRIPT_TASK_STATUS meet_ped[8] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[8] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 9
							IF NOT IS_CHAR_DEAD meet_ped[9]
								GET_SCRIPT_TASK_STATUS meet_ped[9] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										IF NOT IS_CAR_DEAD meet_car[6]
											IF LOCATE_CAR_3D meet_car[6] load_car_x[6] load_car_y[6] load_car_z[6] 0.2 0.2 1.0 FALSE 
												TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ELSE
												TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
											ENDIF
										ELSE
											TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										ENDIF
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[9] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ELSE 
									IF IS_CHAR_PLAYING_ANIM meet_ped[9] M_smklean_loop
										IF NOT IS_CAR_DEAD meet_car[6]
											IF NOT LOCATE_CAR_3D meet_car[6] load_car_x[6] load_car_y[6] load_car_z[6] 0.2 0.2 1.0 FALSE
												CLEAR_CHAR_TASKS meet_ped[9]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						BREAK
						CASE 10
							IF NOT IS_CHAR_DEAD meet_ped[10]
								GET_SCRIPT_TASK_STATUS meet_ped[10] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
				  						TASK_PLAY_ANIM -1 fucku PED 4.0 FALSE FALSE FALSE TRUE -1
										TASK_PLAY_ANIM -1 woman_idlestance PED 4.0 FALSE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[10] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 11
							IF NOT IS_CHAR_DEAD meet_ped[11]
								GET_SCRIPT_TASK_STATUS meet_ped[11] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[11] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 12
							IF NOT IS_CHAR_DEAD meet_ped[12]
								GET_SCRIPT_TASK_STATUS meet_ped[12] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE TRUE TRUE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[12] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 13
							IF NOT IS_CHAR_DEAD meet_ped[13]
								GET_SCRIPT_TASK_STATUS meet_ped[13] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_GO_STRAIGHT_TO_COORD -1 1786.7185 -1915.8783 12.3942 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1796.1786 -1919.4648 12.3928 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1786.7185 -1915.8783 12.3942 PEDMOVE_WALK 60000
										TASK_GO_STRAIGHT_TO_COORD -1 load_ped_x[13] load_ped_y[13] load_ped_z[13] PEDMOVE_WALK 60000
										IF NOT IS_CHAR_DEAD meet_ped[9]
											TASK_TURN_CHAR_TO_FACE_CHAR -1 meet_ped[9]
										ENDIF
										TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[13] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 14
							IF NOT IS_CHAR_DEAD meet_ped[14]
								GET_SCRIPT_TASK_STATUS meet_ped[14] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_GO_STRAIGHT_TO_COORD -1 1800.3669 -1917.3940 12.3938 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1789.0570 -1917.9011 12.3936 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 FALSE TRUE TRUE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1788.5259 -1907.2943 12.3955 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1796.4777 -1908.1738 12.3983 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[14] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 15
							IF NOT IS_CHAR_DEAD meet_ped[15]
								GET_SCRIPT_TASK_STATUS meet_ped[15] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq	 
										TASK_GO_STRAIGHT_TO_COORD -1 1787.6718 -1908.5668 12.3949 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1786.7195 -1917.1067 12.3942 PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
										TASK_GO_STRAIGHT_TO_COORD -1 1787.6718 -1908.5668 12.3949 PEDMOVE_WALK 60000
										TASK_GO_STRAIGHT_TO_COORD -1 load_ped_x[15] load_ped_y[15] load_ped_z[15] PEDMOVE_WALK 60000
										TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[15] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 16
							IF NOT IS_CHAR_DEAD meet_ped[16]
								GET_SCRIPT_TASK_STATUS meet_ped[16] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq	 
										TASK_PLAY_ANIM -1 TAP_HAND PED 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[16] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 17
							IF NOT IS_CHAR_DEAD meet_ped[17]
								GET_SCRIPT_TASK_STATUS meet_ped[17] PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq	 
										TASK_PLAY_ANIM -1 TAP_HAND PED 4.0 TRUE FALSE FALSE FALSE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK meet_ped[17] temp_seq 
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						BREAK
						CASE 18
							// check if player has attacked meeting
							temp_int = 0
							WHILE temp_int < 7
								IF DOES_VEHICLE_EXIST meet_car[temp_int]
									IF NOT IS_CAR_DEAD meet_car[temp_int]
										IF HAS_CAR_BEEN_DAMAGED_BY_CHAR meet_car[temp_int] scplayer
											GET_CAR_HEALTH meet_car[temp_int] temp_int2
											IF temp_int2 < 950
												PRINT_NOW S6_32 5000 1 // ~r~The meeting is dispersing because fucked with one of their cars.
												player_has_attacked_meet = 1
												temp_int = 7
											ENDIF
										ENDIF
									ELSE
										PRINT_NOW S6_32 5000 1 // ~r~The meeting is dispersing because fucked with one of their cars.
										player_has_attacked_meet = 1
										temp_int = 7
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
						BREAK
						CASE 19
							temp_int = 0
							WHILE temp_int < 22
								IF DOES_CHAR_EXIST meet_ped[temp_int]
									IF NOT IS_CHAR_DEAD meet_ped[temp_int]
										IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR meet_ped[temp_int] scplayer
											PRINT_NOW S6_04 5000 1 // ~r~The meeting is dispersing because you attacked a group member.
											player_has_attacked_meet = 1
											temp_int = 22
										ENDIF
									ELSE	
										PRINT_NOW S6_04 5000 1 // ~r~The meeting is dispersing because you attacked a group member.
										player_has_attacked_meet = 1
										temp_int = 22	
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
						BREAK
					ENDSWITCH

							
				ELSE
					
					IF player_has_attacked_meet = 1
						
						// player has attacked meeting
						temp_int = 0
						WHILE temp_int < 12	
							IF NOT IS_CHAR_DEAD meet_ped[temp_int]
								CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[temp_int]
							ENDIF
						temp_int++
						ENDWHILE

						temp_int = 0
						WHILE temp_int < 22
							IF NOT IS_CHAR_DEAD meet_ped[temp_int]
								SET_CHAR_RELATIONSHIP meet_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							ENDIF
						temp_int++
						ENDWHILE

						// mark stuff as no longer needed
						temp_int = 0
						WHILE temp_int < 22
							MARK_CHAR_AS_NO_LONGER_NEEDED meet_ped[temp_int] 
						temp_int++
						ENDWHILE
						temp_int = 0
						WHILE temp_int < 7
							MARK_CAR_AS_NO_LONGER_NEEDED meet_car[temp_int]	
							temp_int++
						ENDWHILE

						m_failed = 1
						
						player_has_attacked_meet++

					ENDIF

				ENDIF
			ENDIF

			LVAR_INT told_player_no_cash
			// check if player has arrived
			IF NOT IS_CAR_DEAD pickup_car
				IF IS_CHAR_IN_CAR scplayer pickup_car
					IF LOCATE_CHAR_IN_CAR_3D scplayer 1793.0676 -1904.2538 12.3989 4.0 4.0 4.0 TRUE
						IF IS_VEHICLE_ON_ALL_WHEELS	pickup_car
							STORE_SCORE player1 temp_int 
							IF temp_int >= min_bet

								SET_PLAYER_CONTROL player1 OFF
								STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
								//TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
								m_goals = 99
							ELSE
								//PRINT_NOW NOMONY 5000 1
								IF audio_line_is_active = 0
								AND NOT IS_MESSAGE_BEING_DISPLAYED
									IF told_player_no_cash = 0
										$audio_string    = &LOWR_AK
										audio_sound_file = SOUND_LOWR_AK
										START_NEW_SCRIPT audio_line -1 0 0 1 0
										told_player_no_cash++
										PRINT_HELP S6_40
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE
						told_player_no_cash	= 0
					ENDIF
				ENDIF
			ENDIF

		ENDIF	


		// if player is far away from garage enable it
		LVAR_INT mod_garage_help_displayed
		IF mod_garage_help_displayed = 0
			IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2644.0686 -2029.1355 20.0 20.0 FALSE
				IF NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					PRINT_HELP S6_22
					REMOVE_BLIP mod_garage_blip
					ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2644.2524 -2028.2457 12.5547 RADAR_SPRITE_MOD_GARAGE mod_garage_blip
					mod_garage_help_displayed++
				ENDIF
			ENDIF

		ELSE
			// display help for lowrider car
			LVAR_INT lowrider_instructions_been_displayed
			IF lowrider_instructions_been_displayed = 0
				IF NOT IS_CAR_DEAD pickup_car
					IF IS_CHAR_IN_CAR scplayer pickup_car
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							
							// This is not true anymore and needs to be corrected...

							PRINT_HELP S6_36  

							lowrider_instructions_been_displayed++
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF lowrider_instructions_been_displayed = 1
					IF NOT IS_CAR_DEAD pickup_car
						IF IS_CHAR_IN_CAR scplayer pickup_car
							IF NOT IS_MESSAGE_BEING_DISPLAYED
							AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
								PRINT_HELP S6_41  
								
								lowrider_instructions_been_displayed++
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF

		// exit
		IF m_goals = 99
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF IS_CAR_STOPPED car
				 	GOSUB SWEET6_next_stage
				ENDIF
			ENDIF
		ENDIF

RETURN


// *************************************************************************************************************
//						Stage 4 - cutscene of guy getting out of car - and - wager section  
// *************************************************************************************************************

sweet6_m_stage_4:

		// reset car positions
		IF m_goals = 0			 
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF NOT IS_CAR_DEAD pickup_car
				RESET_VEHICLE_HYDRAULICS pickup_car
			ENDIF

			SET_CAR_DENSITY_MULTIPLIER 0.0
			SET_PED_DENSITY_MULTIPLIER 0.0
			CLEAR_AREA 1793.0676 -1904.2538 12.3989 30.0 TRUE

			IF DOES_BLIP_EXIST location_blip
				REMOVE_BLIP location_blip
			ENDIF

			// set players position
			IF NOT IS_CAR_DEAD pickup_car
				SET_CAR_COORDINATES pickup_car 1793.0676 -1904.2538 12.3989 
				SET_CAR_HEADING pickup_car 171.6728
			ENDIF

			// clear all char tasks
			temp_int = 0
			WHILE temp_int < 16
				IF NOT IS_CHAR_DEAD meet_ped[temp_int]
					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			
			// give all the chars empty decision makers
			temp_int = 0
			WHILE temp_int < 22
				IF NOT IS_CHAR_DEAD meet_ped[temp_int]
					SET_CHAR_DECISION_MAKER meet_ped[temp_int] empty_dm
				ENDIF
			temp_int++
			ENDWHILE

			// reset all peds positions
			temp_int = 0
			WHILE temp_int < 16
				IF NOT IS_CHAR_DEAD meet_ped[temp_int]
					SET_CHAR_COORDINATES meet_ped[temp_int] load_ped_x[temp_int] load_ped_y[temp_int] load_ped_z[temp_int]
					SET_CHAR_HEADING meet_ped[temp_int] load_ped_h[temp_int]
				ENDIF
			temp_int++
			ENDWHILE

			// reset all car positions
			temp_int = 0
			WHILE temp_int < 7
				IF NOT IS_CAR_DEAD meet_car[temp_int]
					SET_CAR_COORDINATES meet_car[temp_int] load_car_x[temp_int] load_car_y[temp_int] load_car_z[temp_int]
					SET_CAR_HEADING meet_car[temp_int] load_car_h[temp_int]	
				ENDIF		
			temp_int++
			ENDWHILE

			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
									  
			SET_FIXED_CAMERA_POSITION 1796.7489 -1916.9606 13.8583 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1796.1248 -1916.1810 13.8070 JUMP_CUT

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			m_goals++
		ENDIF

		// make guy in centre car get out and walk towards players car
		IF m_goals = 1
			IF NOT IS_CHAR_DEAD meet_ped[18]
				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CAR_DEAD meet_car[4]
						TASK_LEAVE_CAR -1 meet_car[4]
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1 1792.6309 -1909.2736 12.4371 PEDMOVE_WALK 10000
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK meet_ped[18] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
			TIMERA = 0
			m_goals++
		ENDIF

		// wait for guy to get to point
		IF m_goals = 2
			
			IF TIMERA > 5000
			OR IS_BUTTON_PRESSED PAD1 CROSS
				IF NOT IS_CHAR_DEAD meet_ped[18]
					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[18]
					SET_CHAR_COORDINATES meet_ped[18] 1792.6309 -1909.2736 12.4371
					SET_CHAR_HEADING meet_ped[18] 331.0204
											  
					SET_FIXED_CAMERA_POSITION 1793.8159 -1910.7128 14.0346 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1793.4528 -1909.8005 13.8451 JUMP_CUT
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// guy speaks to player
		IF m_goals = 3
			IF NOT IS_CHAR_DEAD meet_ped[18]
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM -1 prtial_gngtlkD LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkE LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkF LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkG LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK meet_ped[18] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq

				GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int

				IF temp_int = 0
					$audio_string    = &LOWR_BA
					audio_sound_file = SOUND_LOWR_BA
					START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
				ENDIF
				IF temp_int = 1
					$audio_string    = &LOWR_BB
					audio_sound_file = SOUND_LOWR_BB
					START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
				ENDIF
				IF temp_int = 2
					$audio_string    = &LOWR_BC
					audio_sound_file = SOUND_LOWR_BC
					START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
				ENDIF

				WAIT 1000
				
				USE_TEXT_COMMANDS TRUE
				WAIT 1

				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		// wait for dialogue to finish
		IF m_goals = 4
			
			IF audio_line_is_active = 0
			OR IS_BUTTON_PRESSED PAD1 CROSS
				m_goals++
				cross_pressed = 1
				START_NEW_SCRIPT cleanup_audio_lines
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD meet_ped[18]
					CLEAR_CHAR_TASKS meet_ped[18]
				ENDIF
			ENDIF
		ENDIF

		// show wager window
		IF m_goals = 5								

			SWITCH_WIDESCREEN OFF

			SET_FIXED_CAMERA_POSITION 1793.4847 -1907.3541 13.4436 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1793.1289 -1908.2886 13.4481 JUMP_CUT 

			current_wager = min_bet
			temp_int = current_wager * -1

			ADD_SCORE player1 temp_int
			
		    PRINT_HELP_FOREVER S6_37  
			
			m_goals++
		
		ENDIF

		// wager screen
		IF m_Goals = 6

			// check for language change
			IF HAS_GAME_JUST_RETURNED_FROM_FRONTEND
			AND HAS_LANGUAGE_CHANGED
				CLEAR_HELP 
				PRINT_HELP_FOREVER S6_37
			ENDIF
			
			// ' X '  to increase bet
			
			IF IS_BUTTON_PRESSED PAD1 BUTTON_BET_UP
			
				IF NOT cross_is_pressed = 1 
				AND NOT cross_is_pressed = -1

					// sort out betting step
					IF current_wager >= 10000
						bet_step = 1000
					ELSE
						IF current_wager >= 1000
							bet_step = 100
						ELSE
							IF current_wager >= 100
								bet_step = 10
							ELSE
								IF current_wager = 0
									bet_step = 2
								ELSE
									bet_step = 1	
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					// check betting step doesn't go over players cash
					STORE_SCORE player1 temp_int
					IF bet_step > temp_int
						bet_step = temp_int
					ENDIF

					// check betting step won't push us over the maximum bet
					temp_int = current_wager + bet_step
					IF temp_int > max_bet
						bet_step = max_bet - current_wager
					ENDIF

					// take cash off player
					IF bet_step < 0
						bet_step *= -1
					ENDIF

					current_wager += bet_step
					initial_stake += bet_step
					bet_step *= -1
					ADD_SCORE player1 bet_step
						
					cross_is_pressed++
					IF cross_is_pressed > 1
						cross_is_pressed = 2
					ELSE
						TIMERA = 0	
					ENDIF

				ELSE
					IF cross_is_pressed = 1
						IF TIMERA > 500
							cross_is_pressed = 2	
						ENDIF 
					ENDIF
				ENDIF
			ELSE

				// reset cross button flag
				IF NOT cross_is_pressed = 0 
					cross_is_pressed = 0	
				ENDIF

				// remove wager
				IF IS_BUTTON_PRESSED PAD1 BUTTON_BET_DOWN
					IF NOT square_is_pressed = 1

						// sort out betting step
						IF current_wager > 10000
							bet_step = -1000
						ELSE
							IF current_wager > 1000
								bet_step = -100
							ELSE
								IF current_wager > 100
									bet_step = -10
								ELSE
									bet_step = -1
								ENDIF
							ENDIF
						ENDIF

						// check we don't go below minimum bet
						temp_int = current_wager
						
						current_wager += bet_step
						IF current_wager < min_bet
							current_wager = 0
							bet_step = temp_int
							bet_step *= -1
						ENDIF 
					
						IF current_wager < min_bet
							current_wager = min_bet
						ELSE
							bet_step *= -1
							initial_stake -= bet_step
							ADD_SCORE player1 bet_step
						ENDIF
					
						square_is_pressed++
						IF square_is_pressed > 1
							square_is_pressed = 2
						ELSE
							TIMERA = 0
						ENDIF
					ELSE
						IF TIMERA > 500
							square_is_pressed = 2
						ENDIF
					ENDIF
				ELSE
					IF NOT square_is_pressed = 0
						square_is_pressed = 0
					ENDIF
				ENDIF

			ENDIF

		    GOSUB sw6_draw_window_1

			// ' O '  to ok the bet
			IF cross_pressed = 0
				
				IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
					temp_float =# current_wager
					temp_float *= 0.1
					CLEAR_HELP
					m_goals++
				ENDIF
			
			ELSE
				IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
					cross_pressed = 0
				ENDIF
			ENDIF

		ENDIF


		// wait for player to place bet
		IF m_goals = 7
			USE_TEXT_COMMANDS FALSE
			SWITCH_WIDESCREEN ON
			m_goals++
		ENDIF

		IF m_goals = 8
			IF NOT IS_CHAR_DEAD meet_ped[18]
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		IF m_goals = 9
			IF NOT IS_CHAR_DEAD meet_ped[18]
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				OPEN_SEQUENCE_TASK temp_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1791.4329 -1911.4141 12.4371 PEDMOVE_WALK 10000
					IF NOT IS_CAR_DEAD meet_car[4]
						TASK_ENTER_CAR_AS_DRIVER -1 meet_car[4] 5000
					ENDIF
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK meet_ped[18] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				TIMERA = 0
				m_goals++ 
			ENDIF
		ENDIF

		IF m_goals = 10
			IF TIMERA > 2000
				CLEAR_PRINTS
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				m_goals = 99
			ENDIF
		ENDIF

		GOSUB meeting_stage3_update_ai
		
		// exit
		IF m_goals = 99
			GOSUB SWEET6_next_stage
		ENDIF

RETURN

// *************************************************************************************************************
//						Stage 5 - set up minigame scene - cutscene of girl getting into car  
// *************************************************************************************************************
sweet6_m_stage_5:
		
		IF m_goals = 0
			
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			CLEAR_PRINTS
			SET_FIXED_CAMERA_POSITION 1788.1068 -1903.8041 13.3553 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1788.9438 -1904.3428 13.2601 JUMP_CUT

			LOAD_SCENE 1788.9438 -1904.3428 13.2601

			m_goals++
		ENDIF

		IF m_goals = 1				   

			REQUEST_MODEL VLA1
			REQUEST_MODEL VLA2
			REQUEST_MODEL VLA3
			REQUEST_MODEL BFYPRO
			REQUEST_MODEL HFYST

			REQUEST_MODEL SAVANNA
			REQUEST_MODEL REMINGTN

			REQUEST_ANIMATION LOWRIDER

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED VLA1
			   OR NOT HAS_MODEL_LOADED VLA2
			   OR NOT HAS_MODEL_LOADED VLA3
			   OR NOT HAS_MODEL_LOADED BFYPRO
			   OR NOT HAS_MODEL_LOADED HFYST
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED SAVANNA
			   OR NOT HAS_MODEL_LOADED REMINGTN
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_ANIMATION_LOADED LOWRIDER
				WAIT 0
			ENDWHILE


			// set floats
			load_car_x[0] =	1798.2028  // top left
			load_car_y[0] =	-1926.2216
			load_car_z[0] =	12.3895 
			load_car_h[0] =	73.1404 

			load_car_x[1] =	1802.8640  // infront of middle car
			load_car_y[1] =	-1918.7413
			load_car_z[1] =	12.3931 
			load_car_h[1] =	357.6193

			load_car_x[2] =	1784.6350   // top right
			load_car_y[2] =	-1924.9684
			load_car_z[2] =	12.3901 
			load_car_h[2] =	305.0620

			load_car_x[3] =	1784.1881  // behind middle car
			load_car_y[3] =	-1913.4393
			load_car_z[3] =	12.3923 
			load_car_h[3] =	183.5814

			load_car_x[4] =	1792.3180  // middle car
			load_car_y[4] =	-1919.5132
			load_car_z[4] =	12.3928 
			load_car_h[4] =	8.2504 	

			load_car_x[5] =	1801.4617  // nearest entrance left
			load_car_y[5] =	-1905.6914
			load_car_z[5] =	12.3995 
			load_car_h[5] =	189.9314
							
			load_car_x[6] =	1787.6423  // entrance right
			load_car_y[6] =	-1901.1360
			load_car_z[6] =	12.3949 
			load_car_h[6] =	145.4181

			// peds
			load_ped_x[0] =	  1800.0 
			load_ped_y[0] =	  -1907.8301 
			load_ped_z[0] =	  12.4398 
			load_ped_h[0] =	  73.7198
							   
			load_ped_x[1] =	  1799.8458 
			load_ped_y[1] =	  -1912.1237 
			load_ped_z[1] =	  12.4582 
			load_ped_h[1] =	  48.2386 
			
			load_ped_x[2] =	  1800.0861 
			load_ped_y[2] =	  -1910.4337 
			load_ped_z[2] =	  12.4372 
			load_ped_h[2] =	  83.7398 

			load_ped_x[3] =	  1801.3206 
			load_ped_y[3] =	  -1916.4268 
			load_ped_z[3] =	  12.4343 
			load_ped_h[3] =	  83.7398 

			load_ped_x[4] =	  1798.5405 
			load_ped_y[4] =	  -1922.1797 
			load_ped_z[4] =	  12.4310 
			load_ped_h[4] =	  46.5794 

			load_ped_x[5] =	  1799.4904 
			load_ped_y[5] =	  -1920.6166 
			load_ped_z[5] =	  12.4322 
			load_ped_h[5] =	  46.5794 

			load_ped_x[6] =	  1787.2549 
			load_ped_y[6] =	  -1926.5966 
			load_ped_z[6] =	  12.4283 
			load_ped_h[6] =	  323.6194 

			load_ped_x[7] =	  1786.4419 
			load_ped_y[7] =	  -1920.2008 
			load_ped_z[7] =	  12.4322 
			load_ped_h[7] =	  306.1995 

			load_ped_x[8] =	  1785.8242 
			load_ped_y[8] =	  -1919.1622 
			load_ped_z[8] =	  12.4324 
			load_ped_h[8] =	  294.6196 

			load_ped_x[9] =	  1786.1021 
			load_ped_y[9] =	  -1913.8329 
			load_ped_z[9] =	  12.4327 
			load_ped_h[9] =	  280.7204

			load_ped_x[10] =  1786.8058 
			load_ped_y[10] =  -1905.2460 
			load_ped_z[10] =  12.4342 
			load_ped_h[10] =  262.8214

			load_ped_x[11] =  1788.6932 
			load_ped_y[11] =  -1902.1385 
			load_ped_z[11] =  12.4348 
			load_ped_h[11] =  247.1610

			load_ped_x[12] =  1794.6162 
			load_ped_y[12] =  -1899.6078 
			load_ped_z[12] =  12.4398 
			load_ped_h[12] =  191.0618

			load_ped_x[13] =  1799.1346 
			load_ped_y[13] =  -1902.4882 
			load_ped_z[13] =  12.4431 
			load_ped_h[13] =  157.4622

			load_ped_x[14] =  1795.6742 
			load_ped_y[14] =  -1899.5686 
			load_ped_z[14] =  12.4407 
			load_ped_h[14] =  162.6424

			load_ped_x[15] =  1800.3689 
			load_ped_y[15] =  -1907.0029
			load_ped_z[15] =  12.4403 
			load_ped_h[15] =  77.6197 

			load_ped_x[16] =  1800.8259 
			load_ped_y[16] =  -1917.3248
			load_ped_z[16] =  12.4340 
			load_ped_h[16] =  36.1793 

			load_ped_x[17] =  1785.8500 
			load_ped_y[17] =  -1917.9550
			load_ped_z[17] =  12.4324 
			load_ped_h[17] =  287.0602

			load_ped_x[18] =  1788.5383 
			load_ped_y[18] =  -1927.0908
			load_ped_z[18] =  12.4280 
			load_ped_h[18] =  345.1206

			load_ped_x[19] =  1787.3230 
			load_ped_y[19] =  -1904.1097
			load_ped_z[19] =  12.4337 
			load_ped_h[19] =  263.3813

			load_ped_x[20] =  1798.3904 
			load_ped_y[20] =  -1901.7328 
			load_ped_z[20] =  12.4429 
			load_ped_h[20] =  142.4208

						
			force_multiplier = 0.01
			
			// create all stuff required for lowrider minigame ---------------

			// delete all previously created cars and peds
			temp_int = 0
			WHILE temp_int < 7
				IF DOES_VEHICLE_EXIST meet_car[temp_int]
					DELETE_CAR meet_car[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0
			WHILE temp_int < 22
				IF DOES_CHAR_EXIST meet_ped[temp_int]
					DELETE_CHAR meet_ped[temp_int]
				ENDIF
			temp_int++
			ENDWHILE


			// ---- setup player --------------------------------------------
			IF NOT DOES_VEHICLE_EXIST pickup_car
				CREATE_CAR SAVANNA 1793.4956 -1907.4233 12.3992 pickup_car
				SET_CAR_HEADING pickup_car 181.5231
			ELSE
				IF NOT IS_CAR_DEAD pickup_car
					SET_CAR_COORDINATES pickup_car 1793.4956 -1907.4233 12.3992 
					SET_CAR_HEADING pickup_car 181.5231
				ENDIF
			ENDIF
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			IF NOT IS_CAR_DEAD pickup_car
				WARP_CHAR_INTO_CAR scplayer pickup_car	
				TASK_PLAY_ANIM scplayer sit_relaxed LOWRIDER 4.0 TRUE FALSE FALSE TRUE -1
			ENDIF

			// create all spector peds and cars	---------------------------------------										
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[0]	  // top left						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[1]	  // infront of middle car						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[2]	  // top right						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[3]	  // behind middle car						
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[4]	  // middle car						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[5]	  // nearest entrance left						
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[6]	  // entrance right
					   
			CHANGE_CAR_COLOUR meet_car[0] 1  0
			CHANGE_CAR_COLOUR meet_car[1] 2  1									 
			CHANGE_CAR_COLOUR meet_car[2] 3  2
			CHANGE_CAR_COLOUR meet_car[3] 10 3
			CHANGE_CAR_COLOUR meet_car[4] 9	 4
			CHANGE_CAR_COLOUR meet_car[5] 6	 5
			CHANGE_CAR_COLOUR meet_car[6] 7	 6
			
			// bouncing cars -----------------------------------------------------------
			temp_int = 0
			WHILE temp_int < 7
				SET_CAR_COORDINATES meet_car[temp_int] load_car_x[temp_int] load_car_y[temp_int] load_car_z[temp_int]
				SET_CAR_HEADING meet_car[temp_int] load_car_h[temp_int]	
				FREEZE_CAR_POSITION meet_car[temp_int] TRUE	
				LOCK_CAR_DOORS meet_car[temp_int] CARLOCK_LOCKOUT_PLAYER_ONLY	
			temp_int++
			ENDWHILE

			FREEZE_CAR_POSITION meet_car[4] FALSE
			SET_CAR_STATUS meet_car[4] STATUS_PHYSICS
			SET_CAR_HYDRAULICS meet_car[4] TRUE									
			SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[4] TRUE
			SET_CAR_PROOFS meet_car[4] TRUE TRUE TRUE TRUE TRUE
			

			// guys	---------------------------------------------------------------------															
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[0] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[1]
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[2]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[3]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[4]			
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[5]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[6]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[7] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[8]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[9]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[10]
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[11] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[12]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[13]

			// girls --------------------------------------------------------------------
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[14]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[15]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[16]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[17]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[18]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[19]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[20]

			// driver -------------------------------------------------------------------
			CREATE_CHAR_INSIDE_CAR meet_car[4] PEDTYPE_MISSION1 VLA3 meet_ped[21]

			temp_int = 0
			WHILE temp_int < 21
				SET_CHAR_COORDINATES meet_ped[temp_int] load_ped_x[temp_int] load_ped_y[temp_int] load_ped_z[temp_int]
				SET_CHAR_HEADING meet_ped[temp_int] load_ped_h[temp_int]
			temp_int++
			ENDWHILE

			// bounce girl --------------------------------------------------------------
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 1788.2734 -1903.3641 12.4345 bounce_girl
			SET_CHAR_HEADING bounce_girl 232.5412

			LVAR_INT gang_member_to_delete
			IF NOT IS_CAR_DEAD pickup_car
				IF NOT IS_CAR_PASSENGER_SEAT_FREE pickup_car 0									
					GET_CHAR_IN_CAR_PASSENGER_SEAT pickup_car 0 gang_member_to_delete
					DELETE_CHAR gang_member_to_delete
				ENDIF
			ENDIF
			

			// load music track ---------------------------------------------------------
			PRELOAD_BEAT_TRACK 0
			temp_int = 0
			WHILE NOT temp_int = CUTSCENE_TRACK_LOADED
				//WRITE_DEBUG_WITH_INT TRACK_STATUS temp_int
				WAIT 0
				GET_BEAT_TRACK_STATUS	temp_int
			ENDWHILE

			m_goals++

		ENDIF
		
		// girl gets into car -----------------------------------------------------------
		IF m_goals = 2

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF NOT IS_CHAR_DEAD bounce_girl
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				OPEN_SEQUENCE_TASK temp_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1791.1782 -1905.8403 12.3975 PEDMOVE_WALK 10000
					IF NOT IS_CAR_DEAD pickup_car
						TASK_ENTER_CAR_AS_PASSENGER -1 pickup_car 5000 0 
					ENDIF
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK bounce_girl temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF

			CLEAR_PRINTS
			
			m_goals++
		ENDIF

		// wait for girl to get in car ---------------------------------------------------
		IF m_goals = 3
			
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				IF NOT IS_CHAR_DEAD bounce_girl
					IF NOT IS_CAR_DEAD pickup_car
						IF IS_CHAR_IN_CAR bounce_girl pickup_car
							m_goals++
							TIMERA = 0
						ENDIF
					ENDIF
				ENDIF
			ELSE
				m_goals++
				TIMERA = 0
			ENDIF
		ENDIF

		// switch camera view ------------------------------------------------------------
		IF m_goals = 4
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE


			DISPLAY_HUD FALSE
			SWITCH_WIDESCREEN OFF
			SET_FIXED_CAMERA_POSITION 1788.5157 -1910.3184 14.8901 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1789.3551 -1909.8429 14.6269 JUMP_CUT
			DISPLAY_RADAR FALSE
			IF NOT IS_CAR_DEAD pickup_car
				LOCK_CAR_DOORS pickup_car CARLOCK_LOCKED_PLAYER_INSIDE
				APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
				SET_CAR_PROOFS pickup_car TRUE TRUE TRUE TRUE TRUE
			ENDIF
			IF NOT IS_CHAR_DEAD bounce_girl
				CLEAR_CHAR_TASKS_IMMEDIATELY bounce_girl
			
				IF NOT IS_CAR_DEAD pickup_car
					IF IS_CAR_PASSENGER_SEAT_FREE pickup_car 0
						WARP_CHAR_INTO_CAR_AS_PASSENGER bounce_girl pickup_car 0		
					ENDIF	
				ENDIF	
			ENDIF


			offx = 0.6200 
			offy = 0.3100 
			offz = -0.1670
			IF NOT IS_CAR_DEAD pickup_car
				IF NOT IS_CHAR_DEAD bounce_girl
					IF IS_CAR_MODEL pickup_car SAVANNA
						CLEAR_CHAR_TASKS_IMMEDIATELY bounce_girl
						ATTACH_CHAR_TO_CAR bounce_girl pickup_car offx offy offz FACING_FORWARD 0.0 WEAPONTYPE_UNARMED 
					ENDIF
				ENDIF
			ENDIF
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hair  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hurry LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
			CLOSE_SEQUENCE_TASK temp_seq
			IF NOT IS_CHAR_DEAD bounce_girl
				IF IS_CHAR_ATTACHED_TO_ANY_CAR	bounce_girl
					PERFORM_SEQUENCE_TASK bounce_girl temp_seq
				ENDIF
			ENDIF
			CLEAR_SEQUENCE_TASK temp_seq
			TIMERA = 0
			CLEAR_PRINTS


			// flags set for the lowrider minigame
			lowrider_level = -1
			lowrider_last_level = -1
			IF sw6_mission_attempts = 0
				lowrider_opposition_skill = 2
			ELSE	
				IF lowrider_opposition_skill < 1
					lowrider_opposition_skill = 1
				ENDIF
			ENDIF

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			PLAY_BEAT_TRACK
			m_goals++
		ENDIF


		// wait a few secs before starting game, to explain help etc ---------------------
		IF m_goals = 5
			SET_PLAYER_CONTROL player1 ON
			START_NEW_SCRIPT beat_display
			//lowrider_opposition_skill = 1
			START_NEW_SCRIPT lowrider_game pickup_car meet_car[4] bounce_girl
			TIMERA = 0
			camera_timer = 0
			camera_mode = 0
			CLEAR_PRINTS
			CLEAR_HELP 
			PRINT_HELP S6_39  // Push the right analog stick in time with the music.
			help_timer = 0
			help_flag = 0
			TIMERA = 0
			m_goals++
		ENDIF


		// wait for lowrider minigame to start
		IF m_goals = 6
			IF lowrider_game_is_active = 1 
				m_goals++
			ENDIF
		ENDIF


		// lowrider minigame --------------------------------------------------------------
		IF m_goals = 7
			IF lowrider_game_is_active = 1
				// do camera work
				IF select_pressed = 0
					IF camera_timer > 5000
					OR IS_BUTTON_PRESSED PAD1 SELECT
						camera_mode++
						IF camera_mode >= 5
							camera_mode = 0
						ENDIF
						
						IF NOT IS_CAR_DEAD pickup_car
							GET_CAR_COORDINATES pickup_car x y z
						ENDIF

						IF camera_mode = 0
							SET_FIXED_CAMERA_POSITION  1803.9780 -1909.3691 15.0671	0.0 0.0 0.0
							POINT_CAMERA_AT_POINT x y z	JUMP_CUT
						ENDIF
						IF camera_mode = 1
							SET_FIXED_CAMERA_POSITION  1795.3690 -1900.9991 14.1795 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT x y z	JUMP_CUT
						ENDIF
						IF camera_mode = 2
							SET_FIXED_CAMERA_POSITION  1788.7931 -1902.9899 13.7203	0.0 0.0 0.0
							POINT_CAMERA_AT_POINT x y z	JUMP_CUT
						ENDIF
						IF camera_mode = 3
							SET_FIXED_CAMERA_POSITION  1789.3766 -1912.1953 14.0671 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT x y z	JUMP_CUT
						ENDIF
						IF camera_mode = 4
							SET_FIXED_CAMERA_POSITION  1796.4092 -1912.2311 13.8316 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT x y z	JUMP_CUT
						ENDIF
						select_pressed++
						camera_timer = 0
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 SELECT
						select_pressed = 0
					ENDIF
				ENDIF

				IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
					//IF IS_DEBUG_CAMERA_ON

						// for camera position
						GET_ACTIVE_CAMERA_COORDINATES x y z
						IF NOT IS_CAR_DEAD pickup_car
							GET_CAR_COORDINATES pickup_car x2 y2 z2
							GET_CAR_HEADING pickup_car heading
						ENDIF
						heading *= -1.0
						COS heading temp_float
						SIN heading temp_float2
						// get coords relative to car 
						vec_x = x - x2
						vec_y = y - y2
						vec_z = z - z2
						// work out new vec_x
						vec2_x = vec_x * temp_float
						temp_float3 = vec_y * temp_float2
						vec2_x -= temp_float3
						// work out new vec_y
						vec2_y = vec_x * temp_float2
						temp_float3 = vec_y * temp_float
						vec2_y += temp_float3
						// save
						SAVE_NEWLINE_TO_DEBUG_FILE 
						SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM CAR = "
						SAVE_FLOAT_TO_DEBUG_FILE vec2_x
						SAVE_FLOAT_TO_DEBUG_FILE vec2_y
						SAVE_FLOAT_TO_DEBUG_FILE vec_z

						// for camera point at
						GET_ACTIVE_CAMERA_POINT_AT x y z
						// get coords relative to car 
						vec_x = x - x2
						vec_y = y - y2
						vec_z = z - z2
						// work out new vec_x
						vec2_x = vec_x * temp_float
						temp_float3 = vec_y * temp_float2
						vec2_x -= temp_float3
						// work out new vec_y
						vec2_y = vec_x * temp_float2
						temp_float3 = vec_y * temp_float
						vec2_y += temp_float3
						// save
						SAVE_NEWLINE_TO_DEBUG_FILE 
						SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM CAR = "
						SAVE_FLOAT_TO_DEBUG_FILE vec2_x
						SAVE_FLOAT_TO_DEBUG_FILE vec2_y
						SAVE_FLOAT_TO_DEBUG_FILE vec_z

						 
					//ENDIF
				ENDIF
			ELSE
				m_goals++
			ENDIF
		ENDIF

		// finish minigame
		IF m_goals = 8
			
			SET_PLAYER_CONTROL player1 FALSE
			
			bd_terminate_script = 1
			
			DO_FADE 500 FADE_OUT 
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			m_goals = 99
		ENDIF

		GOSUB meeting_stage5_update_ai

		// exit
		IF m_goals = 99
			GOSUB SWEET6_next_stage
		ENDIF


RETURN


// *************************************************************************************************************
//						Stage 6 - cutscene at end of minigame  
// *************************************************************************************************************
sweet6_m_stage_6:


		IF m_goals = 0
			STOP_BEAT_TRACK

			CLEAR_PRINTS
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			SET_FIXED_CAMERA_POSITION 1785.7662 -1924.3331 14.4648 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1786.2827 -1923.4926 14.3015 JUMP_CUT

			IF NOT IS_CAR_DEAD pickup_car
				RESET_VEHICLE_HYDRAULICS pickup_car
			ENDIF

			m_goals++
		ENDIF

		IF m_goals = 1

			// delete all previously created cars and peds
			temp_int = 0
			WHILE temp_int < 7
				IF DOES_VEHICLE_EXIST meet_car[temp_int]
					DELETE_CAR meet_car[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0
			WHILE temp_int < 22
				IF DOES_CHAR_EXIST meet_ped[temp_int]
					DELETE_CHAR meet_ped[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			IF DOES_CHAR_EXIST bounce_girl
				DELETE_CHAR bounce_girl
			ENDIF


			// ---- setup player --------------------------------------------
			IF NOT DOES_VEHICLE_EXIST pickup_car
				CREATE_CAR SAVANNA 1793.4956 -1907.4233 12.3992 pickup_car
				SET_CAR_HEADING pickup_car 181.5231
			ELSE
				IF NOT IS_CAR_DEAD pickup_car
					SET_CAR_COORDINATES pickup_car 1793.4956 -1907.4233 12.3992 
					SET_CAR_HEADING pickup_car 181.5231
				ENDIF
			ENDIF

			// create all spector peds and cars	---------------------------------------										
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[0]	  // top left						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[1]	  // infront of middle car						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[2]	  // top right						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[3]	  // behind middle car						
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[4]	  // middle car						
			CREATE_CAR SAVANNA  0.0 0.0 0.0 meet_car[5]	  // nearest entrance left						
			CREATE_CAR REMINGTN 0.0 0.0 0.0 meet_car[6]	  // entrance right
					   
			CHANGE_CAR_COLOUR meet_car[0] 1  0
			CHANGE_CAR_COLOUR meet_car[1] 2  1									 
			CHANGE_CAR_COLOUR meet_car[2] 3  2
			CHANGE_CAR_COLOUR meet_car[3] 10 3
			CHANGE_CAR_COLOUR meet_car[4] 9	 4
			CHANGE_CAR_COLOUR meet_car[5] 6	 5
			CHANGE_CAR_COLOUR meet_car[6] 7	 6
			
			// bouncing cars -----------------------------------------------------------
			temp_int = 0
			WHILE temp_int < 7
				SET_CAR_COORDINATES meet_car[temp_int] load_car_x[temp_int] load_car_y[temp_int] load_car_z[temp_int]
				SET_CAR_HEADING meet_car[temp_int] load_car_h[temp_int]	
				FREEZE_CAR_POSITION meet_car[temp_int] TRUE		
				LOCK_CAR_DOORS meet_car[temp_int] CARLOCK_LOCKOUT_PLAYER_ONLY
			temp_int++
			ENDWHILE

			FREEZE_CAR_POSITION meet_car[4] FALSE
			SET_CAR_STATUS meet_car[4] STATUS_PHYSICS
			SET_CAR_HYDRAULICS meet_car[4] TRUE									
			SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[4] TRUE
			SET_CAR_PROOFS meet_car[4] TRUE TRUE TRUE TRUE TRUE
			

			// guys	---------------------------------------------------------------------															
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[0] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[1]
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[2]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[3]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[4]			
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[5]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[6]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[7] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[8]
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[9]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[10]
			CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[11] 
			CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[12]
			CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[13]

			// girls --------------------------------------------------------------------
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[14]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[15]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[16]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[17]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[18]
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 0.0 0.0 0.0 meet_ped[19]
			CREATE_CHAR PEDTYPE_MISSION1 HFYST 0.0 0.0 0.0 meet_ped[20]

			// driver -------------------------------------------------------------------
			CREATE_CHAR_INSIDE_CAR meet_car[4] PEDTYPE_MISSION1 VLA3 meet_ped[21]

			temp_int = 0
			WHILE temp_int < 21
				SET_CHAR_COORDINATES meet_ped[temp_int] load_ped_x[temp_int] load_ped_y[temp_int] load_ped_z[temp_int]
				SET_CHAR_HEADING meet_ped[temp_int] load_ped_h[temp_int]
			temp_int++
			ENDWHILE

			// bounce girl --------------------------------------------------------------
			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 1788.2734 -1903.3641 12.4345 bounce_girl
			SET_CHAR_HEADING bounce_girl 232.5412

			IF NOT IS_CAR_DEAD pickup_car
				GET_NUMBER_OF_PASSENGERS pickup_car temp_int
				IF temp_int > 0
					// do fuck all
				ELSE
					IF NOT IS_CHAR_DEAD bounce_girl
						WARP_CHAR_INTO_CAR_AS_PASSENGER bounce_girl pickup_car 0
					ENDIF			   	
				ENDIF
			ENDIF

			// put player into car
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			IF NOT IS_CAR_DEAD pickup_car
				WARP_CHAR_INTO_CAR scplayer pickup_car	
				TASK_PLAY_ANIM scplayer sit_relaxed LOWRIDER 4.0 TRUE FALSE FALSE TRUE -1
			ENDIF
			
			LOAD_SCENE 1791.5741 -1911.4371 12.4378

            DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			m_goals++

		ENDIF

		// make opposition dude get out and walk towards player
		IF m_goals = 2
			IF NOT IS_CHAR_DEAD meet_ped[21]
				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CAR_DEAD meet_car[4]
						TASK_LEAVE_CAR -1 meet_car[4]
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1 1791.5741 -1911.4371 12.4378 PEDMOVE_WALK 10000
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK meet_ped[21] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		// cut to dude talking
		IF m_goals = 3
			IF TIMERA > 5000			   
				SET_FIXED_CAMERA_POSITION 1795.4304 -1905.0176 13.6653 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1795.1445 -1905.9680 13.5431 JUMP_CUT

				IF NOT IS_CHAR_DEAD meet_ped[21]
					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[21]
					SET_CHAR_COORDINATES meet_ped[21] 1795.9678 -1909.3329 12.4389 
					SET_CHAR_HEADING meet_ped[21] 20.3199
					OPEN_SEQUENCE_TASK temp_seq
						TASK_LOOK_AT_CHAR -1 scplayer 99999
						TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[21] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
	
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		// play dialogue depending on whether player won or lost
		IF m_goals = 4

			IF lowrider_pscore > lowrider_oscore
			   	GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0
					$audio_string    = &LOWR_CA
					audio_sound_file = SOUND_LOWR_CA
					START_NEW_SCRIPT audio_line meet_ped[21] 0 1 1 0
				ELSE
					$audio_string    = &LOWR_CB
					audio_sound_file = SOUND_LOWR_CB
					START_NEW_SCRIPT audio_line meet_ped[21] 0 1 1 0
				ENDIF			   		
				
				TIMERA = 0
				m_goals++
			ELSE
				lowrider_opposition_skill--
			   	GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0
					$audio_string    = &LOWR_DA
					audio_sound_file = SOUND_LOWR_DA
					START_NEW_SCRIPT audio_line meet_ped[21] 0 1 1 0
				ELSE
					$audio_string    = &LOWR_DB
					audio_sound_file = SOUND_LOWR_DB
					START_NEW_SCRIPT audio_line meet_ped[21] 0 1 1 0
				ENDIF

				TIMERA = 0
				m_goals += 2
				current_wager *= -1
				TIMERA = 0
			ENDIF
			WAIT 1000
		ENDIF

		// turn around and walk back to car
		IF m_goals = 5
			IF audio_line_is_active = 0
			OR TIMERA > 3000
				IF NOT IS_CHAR_DEAD meet_ped[21]
					CLEAR_CHAR_TASKS meet_ped[21]
					CLEAR_LOOK_AT meet_ped[21]
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1794.2987 -1917.2456 12.4339 PEDMOVE_WALK 10000
						IF NOT IS_CAR_DEAD meet_car[4]
							TASK_ENTER_CAR_AS_DRIVER -1 meet_car[4] 10000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[21] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					TIMERA = 0
					m_goals += 2
				ENDIF				
			ENDIF
		ENDIF

		// turn around and walk back to car
		IF m_goals = 6
			IF audio_line_is_active = 0
			OR TIMERA > 3000
				IF NOT IS_CHAR_DEAD meet_ped[21]
					CLEAR_CHAR_TASKS meet_ped[21]
					CLEAR_LOOK_AT meet_ped[21]
					SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1794.2987 -1917.2456 12.4339 PEDMOVE_WALK 10000
						IF NOT IS_CAR_DEAD meet_car[4]
							TASK_ENTER_CAR_AS_DRIVER -1 meet_car[4] 10000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[21] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					TIMERA = 0
					m_goals++
				ENDIF				
			ENDIF
		ENDIF

		// wait for a couple of secs 
		IF m_goals = 7
			IF TIMERA > 3000
				IF lowrider_pscore > lowrider_oscore
					m_goals++
					TIMERA = 0	
				ELSE
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF
		
		// show cesar and kendal getting out car
		IF m_goals = 8
			TIMERA = 0
			m_goals++
		ENDIF 

		// start animated cutscene
		IF m_goals = 9
			//IF TIMERA > 4500
				// load animated cutscene
				
				//WRITE_DEBUG fading_out
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				//WRITE_DEBUG faded_out

				// delete all stuff at gathering
				temp_int = 0
				WHILE temp_int < 7
					IF DOES_VEHICLE_EXIST meet_car[temp_int]
						DELETE_CAR meet_car[temp_int]
					ENDIF
				temp_int++
				ENDWHILE
				temp_int = 0
				WHILE temp_int < 22
					IF DOES_CHAR_EXIST meet_ped[temp_int]
						DELETE_CHAR meet_ped[temp_int]
					ENDIF
				temp_int++
				ENDWHILE

				IF DOES_CHAR_EXIST bounce_girl
					DELETE_CHAR bounce_girl
				ENDIF

				// remove stuff from memory
				MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
				MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
				MARK_MODEL_AS_NO_LONGER_NEEDED VLA1
				MARK_MODEL_AS_NO_LONGER_NEEDED VLA2
				MARK_MODEL_AS_NO_LONGER_NEEDED VLA3
				MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
				MARK_MODEL_AS_NO_LONGER_NEEDED HFYST
				MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
				MARK_MODEL_AS_NO_LONGER_NEEDED REMINGTN

				REMOVE_ANIMATION LOWRIDER


				// shift pickup car to position
				IF NOT IS_CAR_DEAD pickup_car
					RESET_VEHICLE_HYDRAULICS pickup_car
					CLEAR_AREA 1784.757 -1890.923 12.399 25.0 TRUE
					SET_CAR_COORDINATES pickup_car 1784.757 -1889.923 12.399
					SET_CAR_HEADING pickup_car 90.0
					FREEZE_CAR_POSITION pickup_car TRUE
				ENDIF

                WAIT 500

				LOAD_CUTSCENE SWEET6B
				WHILE NOT HAS_CUTSCENE_LOADED
					WAIT 0
				ENDWHILE
				START_CUTSCENE
				DO_FADE 1000 FADE_IN
				WHILE NOT HAS_CUTSCENE_FINISHED
					WAIT 0
				ENDWHILE
				CLEAR_CUTSCENE
				DO_FADE 0 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				m_goals = 99
			//ENDIF
		ENDIF
	
		// cleanup 
		IF m_goals = 99
						
			// delete all stuff at gathering
			temp_int = 0
			WHILE temp_int < 7
				IF DOES_VEHICLE_EXIST meet_car[temp_int]
					DELETE_CAR meet_car[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0
			WHILE temp_int < 22
				IF DOES_CHAR_EXIST meet_ped[temp_int]
					DELETE_CHAR meet_ped[temp_int]
				ENDIF
			temp_int++
			ENDWHILE

			IF DOES_CHAR_EXIST bounce_girl
				DELETE_CHAR bounce_girl
			ENDIF
			

			// create a couple of cars that leave
			LVAR_INT leaving_car1 leaving_car2
			LVAR_INT leaving_driver1 leaving_driver2
			LVAR_INT leaving_ped[3]
			REQUEST_MODEL REMINGTN
			REQUEST_MODEL SAVANNA
			REQUEST_MODEL VLA1
			REQUEST_MODEL VLA2
			REQUEST_MODEL VLA3
			WHILE NOT HAS_MODEL_LOADED REMINGTN
			   OR NOT HAS_MODEL_LOADED SAVANNA
			   OR NOT HAS_MODEL_LOADED VLA1
			   OR NOT HAS_MODEL_LOADED VLA2
			   OR NOT HAS_MODEL_LOADED VLA3
				WAIT 0
			ENDWHILE
			CREATE_CAR REMINGTN 1787.2657 -1925.1617 12.3900 leaving_car1
			SET_CAR_HEADING leaving_car1 24.6517		
			CREATE_CAR SAVANNA  1799.9189 -1926.1404 12.3895 leaving_car2
			SET_CAR_HEADING leaving_car2 341.4824
			CREATE_CHAR_INSIDE_CAR leaving_car1 PEDTYPE_CIVMALE VLA1 leaving_driver1 
			CREATE_CHAR_INSIDE_CAR leaving_car2 PEDTYPE_CIVMALE VLA1 leaving_driver2

			CREATE_CHAR PEDTYPE_CIVMALE VLA1 1796.6996 -1915.7422 12.3946 leaving_ped[0]
			SET_CHAR_HEADING leaving_ped[0] 80.7
			CREATE_CHAR PEDTYPE_CIVMALE VLA2 1791.6226 -1919.6207 12.3927 leaving_ped[1]
			SET_CHAR_HEADING leaving_ped[1] 10.9
			CREATE_CHAR PEDTYPE_CIVMALE VLA3 1786.0667 -1911.4623 12.3937 leaving_ped[2]
			SET_CHAR_HEADING leaving_ped[2]	120.0

			MARK_MODEL_AS_NO_LONGER_NEEDED 	REMINGTN
			MARK_MODEL_AS_NO_LONGER_NEEDED	SAVANNA
			MARK_MODEL_AS_NO_LONGER_NEEDED	VLA1
			MARK_MODEL_AS_NO_LONGER_NEEDED	VLA2
			MARK_MODEL_AS_NO_LONGER_NEEDED	VLA3


			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
	
			MARK_CHAR_AS_NO_LONGER_NEEDED leaving_ped[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED leaving_ped[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED leaving_ped[2]
			MARK_CHAR_AS_NO_LONGER_NEEDED leaving_driver1
			MARK_CHAR_AS_NO_LONGER_NEEDED leaving_driver2
			MARK_CAR_AS_NO_LONGER_NEEDED leaving_car1
			MARK_CAR_AS_NO_LONGER_NEEDED leaving_car2
		
			IF NOT IS_CAR_DEAD pickup_car	
				FREEZE_CAR_POSITION pickup_car TRUE
			ENDIF
			
			LOAD_SCENE 1793.9894 -1908.4166 12.3995
			
			DO_FADE 1500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			IF lowrider_pscore > lowrider_oscore
				m_passed = 1
			ELSE
				PRINT_NOW S6_33 5000 1 // you failed the lowrider challenge
				m_failed = 1
			ENDIF

			WAIT 0

			GOSUB SWEET6_next_stage

		ENDIF

		GOSUB meeting_stage5_update_ai
RETURN




// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************

sweet6_global_functions:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_4
		force_multiplier += 0.01
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_5
		force_multiplier += -0.01
	ENDIF

	IF DOES_VEHICLE_EXIST pickup_car
		IF IS_CAR_DEAD pickup_car
			PRINT_NOW S6_34 5000 1 // your lowrider is history
			m_failed = 1
		ENDIF
	ENDIF

	LVAR_INT ped_density_flag
	IF IS_PLAYER_PLAYING player1
		IF m_stage > 2
			IF ped_density_flag = 0
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1793.0676 -1904.2538 125.0 125.0 FALSE
					SET_PED_DENSITY_MULTIPLIER 0.0
					SET_CAR_DENSITY_MULTIPLIER 0.0
					ped_density_flag = 1
				ENDIF	
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1793.0676 -1904.2538 150.0 150.0 FALSE
					SET_PED_DENSITY_MULTIPLIER 1.0
					SET_CAR_DENSITY_MULTIPLIER 1.0
					ped_density_flag = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************

update_attached_girl:

	IF NOT IS_CAR_DEAD pickup_car
		IF NOT IS_CHAR_DEAD bounce_girl
			ATTACH_CHAR_TO_CAR bounce_girl pickup_car offx offy offz FACING_FORWARD 0.0 WEAPONTYPE_UNARMED 
		ENDIF
	ENDIF

RETURN

LVAR_FLOAT hyd_lf hyd_lb hyd_rf hyd_rb
LVAR_INT hyd_index
set_hydraulics:
	IF hyd_index = 0
		hyd_lf = 0.0
		hyd_lb = 0.0
		hyd_rf = 0.0
		hyd_rb = 0.0
	ENDIF 
	IF hyd_index = 1
		hyd_lf = 0.0
		hyd_lb = 0.0
		hyd_rf = 0.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 2
		hyd_lf = 0.0
		hyd_lb = 0.0
		hyd_rf = 1.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 3
		hyd_lf = 0.0
		hyd_lb = 0.0
		hyd_rf = 1.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 4
		hyd_lf = 0.0
		hyd_lb = 1.0
		hyd_rf = 0.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 5
		hyd_lf = 0.0
		hyd_lb = 1.0
		hyd_rf = 0.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 6
		hyd_lf = 0.0
		hyd_lb = 1.0
		hyd_rf = 1.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 7
		hyd_lf = 0.0
		hyd_lb = 1.0
		hyd_rf = 1.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 8
		hyd_lf = 1.0
		hyd_lb = 0.0
		hyd_rf = 0.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 9
		hyd_lf = 1.0
		hyd_lb = 0.0
		hyd_rf = 0.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 10
		hyd_lf = 1.0
		hyd_lb = 0.0
		hyd_rf = 1.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 11
		hyd_lf = 1.0
		hyd_lb = 0.0
		hyd_rf = 1.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 12
		hyd_lf = 1.0
		hyd_lb = 1.0
		hyd_rf = 0.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 13
		hyd_lf = 1.0
		hyd_lb = 1.0
		hyd_rf = 0.0
		hyd_rb = 1.0
	ENDIF
	IF hyd_index = 14
		hyd_lf = 1.0
		hyd_lb = 1.0
		hyd_rf = 1.0
		hyd_rb = 0.0
	ENDIF
	IF hyd_index = 15
		hyd_lf = 1.0
		hyd_lb = 1.0
		hyd_rf = 1.0
		hyd_rb = 1.0
	ENDIF
RETURN

meeting_stage3_update_ai:
	// car 0 - 0
	IF meet_jump_timer[0] < 300
		meet_wheel_fr[0] = 1.0
		meet_wheel_fl[0] = 1.0
		meet_wheel_br[0] = 0.0
		meet_wheel_bl[0] = 0.0
	ELSE
		IF meet_jump_timer[0] > 500 
			meet_jump_timer[0] = 0
		ELSE
			meet_wheel_fr[0] = 0.0
			meet_wheel_fl[0] = 0.0
			meet_wheel_br[0] = 0.0
			meet_wheel_bl[0] = 0.0		
		ENDIF
	ENDIF

	// car 1 - 3 
	IF meet_jump_timer[1] > 2000
		GENERATE_RANDOM_INT_IN_RANGE 0 16 hyd_index
		GOSUB set_hydraulics
		meet_wheel_fr[1] = hyd_rf			
		meet_wheel_fl[1] = hyd_lf 
		meet_wheel_br[1] = hyd_rb
		meet_wheel_bl[1] = hyd_lb
		meet_jump_timer[1] = 0
	ENDIF

	IF NOT IS_CAR_DEAD meet_car[0] 
		IF DOES_CAR_HAVE_HYDRAULICS meet_car[0]
			CONTROL_CAR_HYDRAULICS meet_car[0] meet_wheel_fl[0] meet_wheel_bl[0] meet_wheel_fr[0] meet_wheel_br[0]
			GET_CAR_COORDINATES meet_car[0] x y z
			vec_x = load_car_x[0] - x
			vec_y =	load_car_y[0] - y
			vec_x *= force_multiplier
			vec_y *= force_multiplier
			APPLY_FORCE_TO_CAR meet_car[0] vec_x vec_y 0.0 0.0 0.0 0.0
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD meet_car[3]
		IF DOES_CAR_HAVE_HYDRAULICS meet_car[3]
			CONTROL_CAR_HYDRAULICS meet_car[3] meet_wheel_fl[1] meet_wheel_bl[1] meet_wheel_fr[1] meet_wheel_br[1]
			GET_CAR_COORDINATES meet_car[3] x y z
			vec_x = load_car_x[3] - x
			vec_y =	load_car_y[3] - y
			vec_x *= force_multiplier
			vec_y *= force_multiplier
			APPLY_FORCE_TO_CAR meet_car[3] vec_x vec_y 0.0 0.0 0.0 0.0
		ENDIF
	ENDIF

	// peds

	SWITCH m_frame_num
		CASE 0
			IF NOT IS_CHAR_DEAD meet_ped[0]
				GET_SCRIPT_TASK_STATUS meet_ped[0] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[0] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 1
			IF NOT IS_CHAR_DEAD meet_ped[1]
				GET_SCRIPT_TASK_STATUS meet_ped[1] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 F_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[1] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 2
			IF NOT IS_CHAR_DEAD meet_ped[2]
				GET_SCRIPT_TASK_STATUS meet_ped[2] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						IF NOT IS_CHAR_DEAD meet_ped[3]
							TASK_CHAT_WITH_CHAR -1 meet_ped[3] TRUE TRUE
						ELSE
							TASK_PAUSE -1 1000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[2] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 3
			IF NOT IS_CHAR_DEAD meet_ped[3]
				GET_SCRIPT_TASK_STATUS meet_ped[3] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						IF NOT IS_CHAR_DEAD meet_ped[2]
							TASK_CHAT_WITH_CHAR -1 meet_ped[2] FALSE TRUE
						ELSE
							TASK_PAUSE -1 1000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[3] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 4
			IF NOT IS_CHAR_DEAD meet_ped[4]
				GET_SCRIPT_TASK_STATUS meet_ped[4] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 car_hookertalk PED 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[4] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 5
			IF NOT IS_CHAR_DEAD meet_ped[5]
				GET_SCRIPT_TASK_STATUS meet_ped[5] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						IF NOT IS_CHAR_DEAD meet_ped[6]
							TASK_CHAT_WITH_CHAR -1 meet_ped[6] FALSE TRUE
						ELSE
							TASK_PAUSE -1 1000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[5] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF	
		BREAK
		CASE 6		
			IF NOT IS_CHAR_DEAD meet_ped[6]
				GET_SCRIPT_TASK_STATUS meet_ped[6] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						IF NOT IS_CHAR_DEAD meet_ped[5]
							TASK_CHAT_WITH_CHAR -1 meet_ped[5] TRUE TRUE
						ELSE
							TASK_PAUSE -1 1000
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[6] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF		
		BREAK
		CASE 7		
			IF NOT IS_CHAR_DEAD meet_ped[7]
				GET_SCRIPT_TASK_STATUS meet_ped[7] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[7] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 8
			IF NOT IS_CHAR_DEAD meet_ped[8]
				GET_SCRIPT_TASK_STATUS meet_ped[8] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[8] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 9
			IF NOT IS_CHAR_DEAD meet_ped[9]
				GET_SCRIPT_TASK_STATUS meet_ped[9] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[9] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 10
			IF NOT IS_CHAR_DEAD meet_ped[10]
				GET_SCRIPT_TASK_STATUS meet_ped[10] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
							TASK_PLAY_ANIM -1 fucku PED 4.0 FALSE FALSE FALSE TRUE -1
						TASK_PLAY_ANIM -1 woman_idlestance PED 4.0 FALSE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[10] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 11
			IF NOT IS_CHAR_DEAD meet_ped[11]
				GET_SCRIPT_TASK_STATUS meet_ped[11] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[11] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 12
			IF NOT IS_CHAR_DEAD meet_ped[12]
				GET_SCRIPT_TASK_STATUS meet_ped[12] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE TRUE TRUE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[12] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 13
			IF NOT IS_CHAR_DEAD meet_ped[13]
				GET_SCRIPT_TASK_STATUS meet_ped[13] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[13] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 14
			IF NOT IS_CHAR_DEAD meet_ped[14]
				GET_SCRIPT_TASK_STATUS meet_ped[14] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[14] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 15
			IF NOT IS_CHAR_DEAD meet_ped[15]
				GET_SCRIPT_TASK_STATUS meet_ped[15] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[15] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 16
			IF NOT IS_CHAR_DEAD meet_ped[16]
				GET_SCRIPT_TASK_STATUS meet_ped[16] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 TAP_HAND PED 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[16] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 17
			IF NOT IS_CHAR_DEAD meet_ped[17]
				GET_SCRIPT_TASK_STATUS meet_ped[17] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 TAP_HAND PED 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[17] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
	ENDSWITCH

RETURN

meeting_stage5_update_ai:

	SWITCH m_frame_num
		CASE 0
			// peds
			IF NOT IS_CHAR_DEAD meet_ped[0]
				GET_SCRIPT_TASK_STATUS meet_ped[0] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[0] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 1
			IF NOT IS_CHAR_DEAD meet_ped[1]
				GET_SCRIPT_TASK_STATUS meet_ped[1] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[1] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 2
			IF NOT IS_CHAR_DEAD meet_ped[2]
				GET_SCRIPT_TASK_STATUS meet_ped[2] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[2] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 3
			IF NOT IS_CHAR_DEAD meet_ped[3]
				GET_SCRIPT_TASK_STATUS meet_ped[3] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smkstnd_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[3] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 4
			IF NOT IS_CHAR_DEAD meet_ped[4]
				GET_SCRIPT_TASK_STATUS meet_ped[4] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smkstnd_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[4] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 5
			IF NOT IS_CHAR_DEAD meet_ped[5]
				GET_SCRIPT_TASK_STATUS meet_ped[5] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[5] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF	
		BREAK
		CASE 6		
			IF NOT IS_CHAR_DEAD meet_ped[6]
				GET_SCRIPT_TASK_STATUS meet_ped[6] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smkstnd_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[6] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF	
		BREAK
		CASE 7			
			IF NOT IS_CHAR_DEAD meet_ped[7]
				GET_SCRIPT_TASK_STATUS meet_ped[7] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[7] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 8
			IF NOT IS_CHAR_DEAD meet_ped[8]
				GET_SCRIPT_TASK_STATUS meet_ped[8] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[8] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 9
			IF NOT IS_CHAR_DEAD meet_ped[9]
				GET_SCRIPT_TASK_STATUS meet_ped[9] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[9] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 10
			IF NOT IS_CHAR_DEAD meet_ped[10]
				GET_SCRIPT_TASK_STATUS meet_ped[10] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[10] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 11
			IF NOT IS_CHAR_DEAD meet_ped[11]
				GET_SCRIPT_TASK_STATUS meet_ped[11] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smklean_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[11] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 12
			IF NOT IS_CHAR_DEAD meet_ped[12]
				GET_SCRIPT_TASK_STATUS meet_ped[12] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 M_smkstnd_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[12] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 13
			IF NOT IS_CHAR_DEAD meet_ped[13]
				GET_SCRIPT_TASK_STATUS meet_ped[13] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[13] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 14
			IF NOT IS_CHAR_DEAD meet_ped[14]
				GET_SCRIPT_TASK_STATUS meet_ped[14] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM -1 RAP_B_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[14] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 15
			IF NOT IS_CHAR_DEAD meet_ped[15]
				GET_SCRIPT_TASK_STATUS meet_ped[15] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[15] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 16
			IF NOT IS_CHAR_DEAD meet_ped[16]
				GET_SCRIPT_TASK_STATUS meet_ped[16] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_C_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[16] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 17
			IF NOT IS_CHAR_DEAD meet_ped[17]
				GET_SCRIPT_TASK_STATUS meet_ped[17] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[17] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 18
			IF NOT IS_CHAR_DEAD meet_ped[18]
				GET_SCRIPT_TASK_STATUS meet_ped[18] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[18] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 19
			IF NOT IS_CHAR_DEAD meet_ped[19]
				GET_SCRIPT_TASK_STATUS meet_ped[19] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[19] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
		CASE 20
			IF NOT IS_CHAR_DEAD meet_ped[20]
				GET_SCRIPT_TASK_STATUS meet_ped[20] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq	 
						TASK_PLAY_ANIM -1 RAP_A_Loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK meet_ped[20] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
		BREAK
	ENDSWITCH

RETURN


// ******************************************************************************************
//							ONSCREEN TEXT GOSUBS
// ******************************************************************************************
s6_txt_big_blue_centre:
	SET_TEXT_SCALE 0.9 2.4 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
s6_txt_big_red_centre:
	SET_TEXT_SCALE 0.9 2.4 
	SET_TEXT_COLOUR 081 25 29 255
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
s6_txt_med_blue_left:
	SET_TEXT_SCALE 0.54 1.44 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN



sw6_draw_window_1:
	SWITCH current_Language
		CASE LANGUAGE_ITALIAN
			DRAW_WINDOW 29.0 220.0 173.0 409.0 BJ_TITL 2
		BREAK
		DEFAULT
			DRAW_WINDOW 29.0 220.0 157.0 409.0 BJ_TITL 2
		BREAK
	ENDSWITCH

	GOSUB text_sw6
	SET_TEXT_COLOUR 134 155 184 255
	DISPLAY_TEXT 36.0 240.0 WOF_04

	GOSUB text_sw6
	SET_TEXT_SCALE 0.6253 2.7876
	DISPLAY_TEXT_WITH_NUMBER 36.0 260.0 DOLLAR current_wager

	GOSUB text_sw6
	SET_TEXT_COLOUR 134 155 184 255
	DISPLAY_TEXT 36.0 290.0 BJ_02

	GOSUB text_sw6
	SET_TEXT_SCALE 0.6253 2.7876
	DISPLAY_TEXT_WITH_NUMBER 36.0 310.0 DOLLAR min_bet

	GOSUB text_sw6
	SET_TEXT_COLOUR 134 155 184 255
	DISPLAY_TEXT 36.0 340.0 BJ_03

	GOSUB text_sw6
	SET_TEXT_SCALE 0.6253 2.7876
	DISPLAY_TEXT_WITH_NUMBER 36.0 360.0 DOLLAR max_bet

RETURN


text_sw6:
	SET_TEXT_COLOUR 180 180 180 255
	SET_TEXT_SCALE 0.4714 2.5077
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
RETURN


// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
	SWEET6_next_stage:
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
mission_failed_SWEET6:

	REMOVE_BLIP mod_garage_blip
	stop_gargae_for_neil = 1
	PRINT_BIG M_FAIL 5000 1

	DEACTIVATE_GARAGE modlast
	DEACTIVATE_GARAGE bodLAwN

RETURN

// PASS
mission_passed_SWEET6:

		SWITCH_AUDIO_ZONE LOWRIDE FALSE
		REMOVE_BLIP mod_garage_blip
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2644.2524 -2028.2457 12.5547 RADAR_SPRITE_MOD_GARAGE mod_garage_blip
		REMOVE_BLIP mod_garage1
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1043.4 -1025.3 34.4 RADAR_SPRITE_MOD_GARAGE mod_garage1

		REMOVE_BLIP sweet_contact_blip

		ACTIVATE_GARAGE modlast 
		ACTIVATE_GARAGE bodLAwN
		
		current_wager *= 2
		PRINT_WITH_NUMBER_BIG ( M_PASS ) current_wager 5000 1 //"Mission Passed!"
		ADD_SCORE player1 current_wager

		PLAY_MISSION_PASSED_TUNE 1
		
		CLEAR_WANTED_LEVEL PLAYER1
        PLAYER_MADE_PROGRESS 1
		CLEAR_WANTED_LEVEL PLAYER1
        flag_sweet_mission_counter ++
		lowrider_minigame_unlocked = 1
		stop_gargae_for_neil = 0 // unlock mod garage
		REGISTER_MISSION_PASSED SWEET_6

		SWITCH_CAR_GENERATOR sw6_car_gen1 101
RETURN

// CLEANUP
mission_cleanup_SWEET6:

	START_NEW_SCRIPT cleanup_audio_lines
	
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	SWITCH_AUDIO_ZONE LOWRIDE FALSE
	
	IF DOES_VEHICLE_EXIST pickup_car
		IF NOT IS_CAR_DEAD pickup_car
			SET_CAR_STATUS pickup_car STATUS_ABANDONED
			FREEZE_CAR_POSITION pickup_car FALSE
			SET_CAR_PROOFS pickup_car FALSE FALSE FALSE FALSE FALSE
			IF IS_PLAYER_PLAYING player1
				APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE
			ENDIF 
			LOCK_CAR_DOORS pickup_car CARLOCK_UNLOCKED

			IF IS_CHAR_IN_CAR scplayer pickup_car
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				WARP_CHAR_INTO_CAR scplayer pickup_car
			ENDIF
		ENDIF
	ENDIF

	// remove everything
	temp_int = 0
	WHILE temp_int < 7
		IF DOES_VEHICLE_EXIST meet_car[temp_int]
			IF NOT IS_CAR_DEAD meet_car[temp_int]

				IF temp_int = 0
				OR temp_int = 3
				OR temp_int = 4
					IF DOES_CAR_HAVE_HYDRAULICS meet_car[temp_int]
						CONTROL_CAR_HYDRAULICS meet_car[temp_int] 0.0 0.0 0.0 0.0
					ENDIF
				ENDIF

				SET_CAR_STATUS meet_car[temp_int] STATUS_SIMPLE
				SET_CAR_HYDRAULICS meet_car[temp_int] FALSE
			ENDIF
			MARK_CAR_AS_NO_LONGER_NEEDED meet_car[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

	temp_int = 0
	WHILE temp_int < 22
		IF DOES_CHAR_EXIST meet_ped[temp_int]
			IF NOT IS_CHAR_DEAD meet_ped[temp_int]
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED meet_ped[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

	IF DOES_CHAR_EXIST mechanic_dude
		MARK_CHAR_AS_NO_LONGER_NEEDED mechanic_dude
	ENDIF
	IF DOES_CHAR_EXIST bounce_girl
		MARK_CHAR_AS_NO_LONGER_NEEDED bounce_girl
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
	MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
	MARK_MODEL_AS_NO_LONGER_NEEDED VLA1
	MARK_MODEL_AS_NO_LONGER_NEEDED VLA2
	MARK_MODEL_AS_NO_LONGER_NEEDED VLA3
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYST
	MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
	MARK_MODEL_AS_NO_LONGER_NEEDED REMINGTN

	REMOVE_ANIMATION CAR 
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION LOWRIDER

	REMOVE_BLIP location_blip
	REMOVE_BLIP car_blip

	CLEAR_ONSCREEN_COUNTER lowrider_pscore
	CLEAR_ONSCREEN_COUNTER lowrider_oscore
	DISPLAY_RADAR TRUE

	GET_BEAT_TRACK_STATUS temp_int
	IF NOT temp_int = CUTSCENE_TRACK_STOPPED
		STOP_BEAT_TRACK
	ENDIF
		
	DONT_SUPPRESS_CAR_MODEL SAVANNA

	// terminate beat display script (if it's running)
	bd_terminate_script = 1
		
	// remove decision makers
	REMOVE_DECISION_MAKER empty_dm
	REMOVE_DECISION_MAKER tough_dm

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	GET_GAME_TIMER timer_mobile_start
	
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN
	 

}