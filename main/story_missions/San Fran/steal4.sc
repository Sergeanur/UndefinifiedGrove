MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION STEAL4 : 
//				  AUTHOR :
//			DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************
SCRIPT_NAME STEAL4
GOSUB mission_start_STEAL4								
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_STEAL4
ENDIF
GOSUB mission_cleanup_STEAL4
MISSION_END

mission_start_STEAL4:
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
LVAR_INT dialogue_flag
LVAR_FLOAT x2 y2 z2

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0	  
m_quit		= 0

mission_loop_STEAL4:
WAIT 0

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	m_passed = 1
ENDIF


// frame count
m_frame_num++
IF m_frame_num > 9
	m_frame_num = 0
ENDIF

// timer
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time

// additional timers
LVAR_INT wrong_container_timer
LVAR_INT crane_help_timer
LVAR_INT dialogue_timer
LVAR_INT dropped_cont_timer
LVAR_INT move_car_timer
wrong_container_timer += m_time_diff
crane_help_timer += m_time_diff
dialogue_timer	+= m_time_diff
dropped_cont_timer += m_time_diff
move_car_timer += m_time_diff

	GOSUB STEAL4_debug_tools

	IF level_paused = 1
		GOTO end_of_main_loop_STEAL4
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_1
		m_stage = 10
		m_goals = 0
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer steal_car
		ENDIF
	ENDIF

	SWITCH m_stage
		CASE 0
			GOSUB STEAL4_m_stage_0
		BREAK
		CASE 1
			GOSUB STEAL4_m_stage_1
		BREAK
		CASE 2
			GOSUB STEAL4_m_stage_2
		BREAK
		CASE 3
			GOSUB STEAL4_m_stage_3
		BREAK
		CASE 4
			GOSUB STEAL4_m_stage_4
		BREAK
		CASE 5
			GOSUB STEAL4_m_stage_5
		BREAK
		CASE 6
			GOSUB STEAL4_m_stage_6
		BREAK
		CASE 7
			GOSUB STEAL4_m_stage_7
		BREAK
		CASE 8
			GOSUB STEAL4_m_stage_8
		BREAK
		CASE 9
			GOSUB STEAL4_m_stage_9
		BREAK
		CASE 10
			GOSUB STEAL4_m_stage_10
		BREAK
	ENDSWITCH

	GOSUB steal4_process_conversations

	GOSUB STEAL4_global_functions

// end of main loop
end_of_main_loop_STEAL4:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_STEAL4 
		ELSE
			GOSUB mission_passed_STEAL4
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_STEAL4
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF



// *************************************************************************************************************
//		COMMONLY USED STUFF FOR EVERY SCRIPT - DON'T CHANGE
// *************************************************************************************************************
STEAL4_debug_tools:
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
		// display mission stage variables for debug
		LVAR_INT display_debug
		VAR_INT STEAL4_view_debug[8]
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_SPACE 
			CLEAR_ALL_VIEW_VARIABLES
			display_debug++
			IF display_debug > 2
				display_debug = 0
			ENDIF
		ENDIF
		IF display_debug = 1
			STEAL4_view_debug[0] = m_stage
			STEAL4_view_debug[1] = m_goals
			STEAL4_view_debug[2] = player_on_foot
			STEAL4_view_debug[3] = conversation_flag
			STEAL4_view_debug[4] = active_conversation
			STEAL4_view_debug[5] = conversation_status
			STEAL4_view_debug[6] = crane_help_flag
			STEAL4_view_debug[7] = crane_help_timer
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[0] m_stage
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[1] m_goals
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[2] player_on_foot
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[3] conversation_flag
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[4] active_conversation
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[5] conversation_status
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[6] crane_help_flag
			VIEW_INTEGER_VARIABLE STEAL4_view_debug[7] crane_help_timer
		ENDIF

		IF display_debug = 2
			
			VIEW_FLOAT_VARIABLE magno_arm_rotate_y	 magno_arm_rotate_y
			VIEW_FLOAT_VARIABLE magno_cabin_h 	  	 magno_cabin_h 	  
			VIEW_FLOAT_VARIABLE magno_arm_h		  	 magno_arm_h		  
			VIEW_FLOAT_VARIABLE magno_base_x		 magno_base_x		
			VIEW_FLOAT_VARIABLE magno_base_y		 magno_base_y		
			VIEW_FLOAT_VARIABLE crane_rope_length	 crane_rope_length	

		ENDIF

		LVAR_INT level_paused
		IF level_paused = 0
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P
				WRITE_DEBUG LEVEL_PAUSED
				level_paused = 1
			ENDIF
		ELSE
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P
				WRITE_DEBUG LEVEL_UNPAUSED
				level_paused = 0
			ENDIF 
		ENDIF

	ENDIF

RETURN

// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
STEAL4_m_stage_0:
		
	// fake create
	IF m_goals = -1
		CREATE_CAR PONY 0.0 0.0 0.0 temp_car
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 buddy_Blip
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 container_to_delete
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 bad_wave2_car_blip
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 container_blip[0]
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 steal_car_blip
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 last_picked_up_container
		CREATE_CAR PONY 0.0 0.0 0.0 bad_wave2_car
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 bad_wave2_car_blip
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 picked_up_container
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
	ENDIF

	IF m_goals = 0

		// global
		VAR_INT st4_mission_attempts

		// peds
		LVAR_INT buddy
		LVAR_INT temp_ped
		LVAR_INT bad_wave1_ped[6]
		LVAR_INT bad_wave2_ped[4]
		LVAR_INT extra_baddie[2]

		// vehicles
		LVAR_INT buddy_car
		LVAR_INT temp_car
		LVAR_INT open_container1_car  open_container2_car
	    LVAR_INT steal_car
		LVAR_INT bad_wave1_car
		LVAR_INT bad_wave1_car2
		LVAR_INT bad_wave2_car
		LVAR_INT train

		// objects
		LVAR_INT temp_obj
		LVAR_INT container[9]
		LVAR_INT open_container1
		LVAR_INT open_container2 open_container3
		LVAR_INT container_to_delete
		LVAR_INT last_picked_up_container
		LVAR_INT picked_up_container

		// blips
		LVAR_INT buddy_blip	
		LVAR_INT location_blip
		LVAR_INT container_blip[3]
		LVAR_INT bad_wave1_ped_blip[6]
		LVAR_INT bad_wave2_car_blip
		LVAR_INT steal_car_blip
		LVAR_INT extra_baddie_blip[2]

		// particle fx
		LVAR_INT particle_fx1
		LVAR_INT particle_fx2


		// flags 
		VAR_INT  buddy_health
		LVAR_INT player_on_foot
		LVAR_INT in_car_dialogue_flag
		LVAR_INT joined_group
		LVAR_INT CLEAR_in_car_dialogue_flag
		LVAR_INT disable_crane_drop
		LVAR_INT train_is_coming
		LVAR_INT drive_by_target
		LVAR_INT wrong_container_dialogue_flag
		LVAR_INT crane_help_flag
		LVAR_INT buddy_blip_type
		LVAR_INT garage_is_open
		LVAR_INT neils_car_is_attached neils_car_is_attached2
		LVAR_INT steal_car_health
		LVAR_INT cross_is_pressed
		LVAR_INT container_collision_flag
		LVAR_INT failed_condition
		LVAR_INT crane_help_flag2[10]		
		LVAR_INT this_container_is_it
		LVAR_INT steal4_reward
		LVAR_INT active_conversation
		LVAR_INT conversation_status
		LVAR_INT conversation_flag



		// coords
		LVAR_FLOAT open_container1_x  open_container1_y	open_container1_z open_container1_h
		LVAR_FLOAT open_container2_x  open_container2_y	open_container2_z open_container2_h
		LVAR_FLOAT open_container3_x  open_container3_y	open_container3_z open_container3_h

		// decision makers
		LVAR_INT buddy_dm
		LVAR_INT baddie_dm
		LVAR_INT bad_wave2_dm
		LVAR_INT tough_dm
		LVAR_INT empty_dm
		   	
				
		// floats
		LVAR_FLOAT dropped_container_last_speed
		LVAR_FLOAT final_car_pos_x final_car_pos_y final_car_pos_z final_car_pos_h
				
		 

		// set flags -----------------------
		buddy_health					= 0
		player_on_foot					= 0
		in_car_dialogue_flag			= 0
		joined_group					= 0
		CLEAR_in_car_dialogue_flag		= 0
		disable_crane_drop				= 0
		train_is_coming					= 0
		drive_by_target					= 0
		wrong_container_dialogue_flag 	= 0
		crane_help_flag 				= 0
		buddy_blip_type					= 0
		garage_is_open					= 0
		container_collision_flag		= 0
		failed_condition				= 0
		neils_car_is_attached 			= 0
		neils_car_is_attached2			= 0
		this_container_is_it		   	= 0
		steal4_reward				   	= 0
		active_conversation			   	= 0
		conversation_status			   	= 0
		conversation_flag			   	= 0
		
		
		SET_PLAYER_CONTROL player1 OFF


		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		LOAD_MISSION_TEXT steal4

		// animated cutscene
		SET_AREA_VISIBLE 1
		SET_CHAR_AREA_VISIBLE scplayer 1
		LOAD_SCENE -2031.0 149.0 29.0
		LOAD_CUTSCENE STEAL_4
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		START_CUTSCENE
		DO_FADE 1000 FADE_IN
		WHILE NOT HAS_CUTSCENE_FINISHED
		    WAIT 0
		ENDWHILE
		
		CLEAR_CUTSCENE
		SET_PLAYER_CONTROL player1 OFF
		DO_FADE 0 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_CHAR_AREA_VISIBLE scplayer 0
		SET_AREA_VISIBLE 0


		CLEAR_AREA -2036.1702 179.2839 27.8359 10.0 TRUE

		// create 
		LOAD_SPECIAL_CHARACTER 1 CESAR
		REQUEST_MODEL SAVANNA
		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		   OR NOT HAS_MODEL_LOADED SAVANNA
		   	WAIT 0
		ENDWHILE

		CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA  LVA4L
		CREATE_CAR SAVANNA -2034.7063 177.7413 27.8359 buddy_car 
		CHANGE_CAR_COLOUR buddy_car 3 3
		SET_CAR_HEADING buddy_car 244.5958 
		MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
		
		CREATE_CHAR_AS_PASSENGER buddy_car PEDTYPE_MISSION2 SPECIAL01 0 buddy
		SET_CHAR_NEVER_TARGETTED buddy TRUE
		SET_CHAR_DROWNS_IN_WATER buddy FALSE 
																			   
		SET_CHAR_HEALTH buddy 400
		SET_CHAR_MAX_HEALTH buddy 400

		SET_CHAR_CANT_BE_DRAGGED_OUT buddy TRUE
		SET_CHAR_SUFFERS_CRITICAL_HITS buddy FALSE

		SET_CHAR_COORDINATES scplayer -2035.5303 180.7482 27.8359 
		SET_CHAR_HEADING scplayer 209.6339

		SET_CAMERA_BEHIND_PLAYER 
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON
		SWITCH_RANDOM_TRAINS OFF

		SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY buddy_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm

		// reset crane position
		IF DOES_OBJECT_EXIST magno_base
			GET_OBJECT_COORDINATES magno_base magno_base_x magno_base_y magno_base_z
		ENDIF
		magno_arm_rotate_y 	= 15.0
		magno_cabin_h 	   	= 207.615
		magno_arm_h		   	= 207.615
		magno_base_x		= -1586.70
		magno_base_y		= 84.795792
		crane_rope_length	= 0.639
		GOSUB st4_reset_crane_position

		temp_int = 0
		WHILE temp_int < 10
			crane_help_flag2[temp_int] = 0
		temp_int++
		ENDWHILE

		SWITCH_WIDESCREEN OFF
	
		SET_WANTED_MULTIPLIER 0.001
				
		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		m_goals++
	ENDIF


	IF m_goals = 1
		PRINT_NOW ST4_05 5000 1 // get in car with cesar
		TIMERA = 0
		m_stage++
		m_goals = 0
		dialogue_flag = 0

	ENDIF

RETURN 


// *************************************************************************************************************
//											STAGE 1   
// *************************************************************************************************************
STEAL4_m_stage_1:
		
	// initialisation for stage
	IF m_goals = 0		
	
		disable_crane = 1							
													
		// create objects						   
		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_BLUE -1570.9858 55.2009 17.7460 container[0] 
		SET_OBJECT_HEADING container[0] 40.4082
												   
		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_BLUE -1564.4012 62.1434 17.7537  container[1]	
		SET_OBJECT_HEADING container[1] 235.0724 //48.1600
		 
		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_BLUE -1582.2017 54.1735 17.7537 container[2]
		SET_OBJECT_HEADING container[2] 0.0

		// ground crates
		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_RED -1658.8503 44.8514 4.06 container[4]   
		SET_OBJECT_HEADING container[4] 315.2922

		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_YEL -1649.4757 9.1182  4.06 container[3]    
		SET_OBJECT_HEADING container[3] 315.1249

		temp_int = 0
		WHILE temp_int < 4
			IF DOES_OBJECT_EXIST container[temp_int]
				FREEZE_OBJECT_POSITION container[temp_int] TRUE
			ENDIF
		temp_int++
		ENDWHILE

		SET_RADIO_CHANNEL  RS_CLASSIC_HIP_HOP

	m_goals++
	ENDIF
		
	// wait for player to get in buddy's car
	IF m_goals = 1
		IF dialogue_flag = 0
			IF TIMERA > 6000
				active_conversation = 1
				dialogue_flag = 1
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD buddy_car
			IF IS_CHAR_IN_CAR scplayer buddy_car
				IF NOT IS_CHAR_DEAD buddy
					SET_GROUP_MEMBER players_group buddy
					SET_CHAR_DROWNS_IN_WATER buddy FALSE
					joined_group = 1
				ENDIF
				m_goals++
			ENDIF
		ENDIF
	ENDIF	

	// make sure buddy is in buddy's car
	IF m_goals = 2
		IF NOT IS_CHAR_DEAD buddy
			IF NOT IS_CAR_DEAD buddy_car
				IF IS_CHAR_IN_CAR buddy buddy_car
					IF active_conversation = 0
						m_goals++	
						REMOVE_BLIP location_blip
						ADD_BLIP_FOR_COORD -1585.6301 90.3697 2.7752 location_blip
						PRINT_NOW ST4_43 7000 1 // Drive to the docks
						dialogue_flag = 0
						TIMERA = 0
					ENDIF
				ENDIF
			ENDIF				
		ENDIF
	ENDIF

	IF m_goals > 2
		IF dialogue_flag = 0
			IF TIMERA > 8000
				active_conversation = 2
				dialogue_flag = 1
			ENDIF
		ENDIF
	ENDIF

	// wait for player to get close
	IF m_goals = 3
		IF NOT IS_CAR_DEAD buddy_car
			IF NOT IS_CHAR_DEAD buddy
				IF IS_CHAR_IN_CAR buddy buddy_car
					IF IS_CHAR_IN_CAR scplayer buddy_car
						IF LOCATE_CAR_2D buddy_car -1585.6301 90.3697 100.0 100.0 FALSE		
							m_goals = 99
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				IF NOT IS_CAR_DEAD buddy_car
					IF IS_CHAR_IN_CAR scplayer buddy_car
						IF NOT IS_CHAR_DEAD buddy 
							IF IS_CHAR_IN_CAR buddy buddy_car
								SET_CAR_COORDINATES buddy_car  -1585.6301 90.3 5.0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// check if they are on foot	
		IF NOT IS_CHAR_DEAD buddy
			IF LOCATE_CHAR_ON_FOOT_2D buddy -1585.6301 90.3697 70.0 70.0 FALSE
				IF LOCATE_CHAR_ON_FOOT_2D scplayer -1585.6301 90.3697 70.0 70.0 FALSE
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// exit
	IF m_goals = 99
		m_goals = 0
		m_stage++
	ENDIF

	// FUNCTIONS GLOBAL FOR STAGE -------
	IF NOT IS_CHAR_DEAD buddy
		IF NOT IS_CAR_DEAD buddy_car
			IF IS_CHAR_IN_CAR buddy buddy_car
				IF NOT IS_CHAR_IN_CAR scplayer buddy_car
					IF NOT buddy_blip_type = 1
						buddy_blip_type	= 1
						GOSUB reset_buddy_blip
					ENDIF
				ELSE
					IF NOT buddy_blip_type = 0
						buddy_blip_type = 0
						GOSUB reset_buddy_blip
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_CHAR_IN_CAR scplayer buddy_car
					IF NOT buddy_blip_type = 1
						buddy_blip_type	= 1
						GOSUB reset_buddy_blip
					ENDIF
				ENDIF	
			ENDIF
		ENDIF 
	ENDIF

	// check if cesar has become detached from group
	GOSUB steal4_buddy_group_manager

	IF NOT joined_group = 1
		IF DOES_BLIP_EXIST location_blip
			REMOVE_BLIP location_blip
		ENDIF
	ELSE
		IF NOT DOES_BLIP_EXIST location_blip
			ADD_BLIP_FOR_COORD -1585.6301 90.3697 2.7752 location_blip
			PRINT_NOW ST4_43 7000 1 // Drive to the docks
		ENDIF
	ENDIF

RETURN 

// *************************************************************************************************************
//									STAGE 2 wait for player to get in crane  
// *************************************************************************************************************
STEAL4_m_stage_2:

	IF m_goals = 0
		CLEAR_PRINTS
		PRINT_NOW ST4_13 5000 1 // get in crane
		disable_crane = 0
		dialogue_flag = 0
		TIMERA = 0
		m_goals++

	ENDIF
															   
	// wait for player to enter crane					   
	IF m_goals = 1

		cross_is_pressed = 0

		IF dialogue_flag = 0
			IF TIMERA > 5500
				active_conversation = 3
				dialogue_flag++
			ENDIF
		ENDIF

		IF NOT joined_group = 1
			IF DOES_BLIP_EXIST location_blip
				REMOVE_BLIP location_blip
				disable_crane = 1
			ENDIF
		ELSE
			IF NOT DOES_BLIP_EXIST location_blip
				ADD_BLIP_FOR_COORD -1585.6301 90.3697 2.7752 location_blip
				PRINT_NOW ST4_13 7000 1 // get in crane
				disable_crane = 0
			ENDIF
		ENDIF


		IF player_is_in_crane = 2
			disable_crane_exit = 1
			disable_crane_slide = 1

			REMOVE_BLIP location_blip

			wrong_container_timer = 30000
			crane_help_timer = 8000
			
			IF NOT IS_CHAR_DEAD buddy
				REMOVE_CHAR_FROM_GROUP buddy
				CLEAR_CHAR_TASKS_IMMEDIATELY buddy 
				SET_CHAR_DECISION_MAKER buddy buddy_dm
				SET_CHAR_COORDINATES buddy -1627.8306 63.8863 2.9167 
				SET_CHAR_HEADING buddy 299.6010	
				SET_CHAR_DROWNS_IN_WATER buddy FALSE							  
			ENDIF
			
			open_container1_x = -1620.9379  				  
			open_container1_y =	75.4195 	
			open_container1_z =	4.1 		
			open_container1_h =	20.7795

			open_container2_x =	-1632.7861   
			open_container2_y =	61.2888 			  
			open_container2_z =	4.1				    
			open_container2_h =	6.6851				  

			open_container3_x =	-1644.0168			  		   
			open_container3_y =	50.9619				
			open_container3_z =	4.1					
			open_container3_h =	39.4583				

			CLEAR_HELP
			CLEAR_PRINTS

			temp_int = 0
			WHILE temp_int < 4
				IF DOES_OBJECT_EXIST container[temp_int]
					FREEZE_OBJECT_POSITION container[temp_int] FALSE
				ENDIF
			temp_int++
			ENDWHILE

			GOSUB steal4_buddy_group_manager
			m_goals++

		ELSE
			GOSUB steal4_buddy_group_manager
		ENDIF		
	ENDIF

	// quick cutscene explaining what to do
	IF m_goals = 2
		GOSUB steal4_quit_current_conversation_now
		ENABLE_CRANE_CONTROLS FALSE FALSE FALSE
		disable_crane = 1
		SWITCH_WIDESCREEN ON	    
		SET_FIXED_CAMERA_POSITION -1517.6934 60.1951 57.7488 0.0 0.0 0.0 	
		POINT_CAMERA_AT_POINT -1518.5448 60.2080 57.2246 JUMP_CUT  			
		CLEAR_PRINTS
		TIMERA = 0
		dialogue_flag = 0
		m_goals++
	ENDIF

	IF m_goals > 2
	AND m_goals < 6
		IF cross_is_pressed = 0
			
			IF IS_BUTTON_PRESSED PAD1 CROSS
				GOSUB steal4_quit_current_conversation_now
				m_goals = 6
				cross_is_pressed = 1
			ENDIF
		ELSE
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				cross_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 3
		IF TIMERA > 1000
			active_conversation = 4
			m_goals++
		ENDIF
	ENDIF
		
	IF m_goals = 4
		IF TIMERA > 2500
			m_goals++
		ENDIF
	ENDIF

	IF m_goals = 5			  
		SET_FIXED_CAMERA_POSITION -1631.9966 64.4568 3.1424 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1631.0305 64.5624 3.3783	JUMP_CUT
		dialogue_flag = 0
		m_goals++
		TIMERA = 0
	ENDIF

	IF m_goals = 6
		IF active_conversation = 0
			m_goals++
		ENDIF	
	ENDIF

	IF m_goals = 7
		magno_camera_type = 1
		reset_crane_camera = 1
		DISPLAY_HUD FALSE
		DISPLAY_RADAR FALSE
		SWITCH_WIDESCREEN OFF 
		disable_crane = 0
		ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
		m_goals = 99	
	ENDIF

	IF m_goals = 99
		REQUEST_MODEL CLUB
		WHILE NOT HAS_MODEL_LOADED CLUB
			WAIT 0
		ENDWHILE
		m_goals = 0
		m_stage++
	ENDIF

RETURN








// *************************************************************************************************************
//									STAGE 3 - place 1st container 
// *************************************************************************************************************
STEAL4_m_stage_3:

	IF m_goals > 0
	AND m_goals < 3
		IF cross_is_pressed = 0
			
			IF IS_BUTTON_PRESSED PAD1 CROSS 
				magno_camera_type = 1
				reset_crane_camera = 1
				cross_is_pressed = 1
				m_goals = 3
			ENDIF
		ELSE
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				cross_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	SWITCH m_goals

		CASE 0
			IF cross_is_pressed = 0
				IF NOT buddy_blip_type = 3
					buddy_blip_type	= 3
					GOSUB reset_buddy_blip
				ENDIF 
				disable_crane = 1
				magno_camera_type = 0
				reset_crane_camera = 1
				TIMERA = 0
				PRINT_HELP_FOREVER ST4_H08
				IF NOT IS_CHAR_DEAD buddy
					SET_CHAR_DECISION_MAKER buddy empty_dm
					SET_CHAR_COORDINATES buddy -1626.9811 65.4201 2.5495 
					SET_CHAR_HEADING buddy 306.9205
				ENDIF
				m_goals++
			ELSE
				IF NOT buddy_blip_type = 3
					buddy_blip_type	= 3
					GOSUB reset_buddy_blip
				ENDIF 
				IF NOT IS_CHAR_DEAD buddy
					SET_CHAR_DECISION_MAKER buddy empty_dm
					SET_CHAR_COORDINATES buddy -1626.9811 65.4201 2.5495 
					SET_CHAR_HEADING buddy 306.9205
				ENDIF
				m_goals = 3
			ENDIF
		BREAK							  

		CASE 1
			IF TIMERA > 3000
				magno_camera_type = 1
				reset_crane_camera = 1
				TIMERA = 0
				m_goals++	
			ENDIF
		BREAK

		CASE 2
			IF TIMERA > 3000
				CLEAR_HELP
				magno_camera_type = 1	// set back to default crane position.
				reset_crane_camera = 1
				TIMERA = 0
				m_goals++	
			ENDIF
		BREAK
												
		CASE 3
			DISPLAY_HUD TRUE
			DISPLAY_RADAR TRUE
			CLEAR_HELP
			ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
			magno_camera_type = 1
			reset_crane_camera = 1
			disable_crane = 0
			disable_crane_rotate = 0  // test
			disable_manual_control = 0 // 1
			disable_crane_slide = 0 //1
			disable_crane_drop = 0
			PRINT_NOW ST4_T01 5000 1 //~s~Pick up blue container with the crane.  
			ADD_BLIP_FOR_OBJECT container[0] container_blip[0]
			ADD_BLIP_FOR_OBJECT container[1] container_blip[1]
			ADD_BLIP_FOR_OBJECT container[2] container_blip[2]
			crane_help_timer = 10000
			crane_help_flag = 0
			TIMERA = 0
			m_goals++
		BREAK

		// wait for container to get attached
		CASE 4

			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj	
			IF temp_obj = container[0]
			OR temp_obj = container[1]
			OR temp_obj = container[2]

				picked_up_container = temp_obj

				ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
				disable_crane_drop = 1
				IF DOES_BLIP_EXIST container_blip[0]
					REMOVE_BLIP container_blip[0]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[1]
					REMOVE_BLIP container_blip[1]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[2]
					REMOVE_BLIP container_blip[2]
				ENDIF
				REMOVE_BLIP location_blip
				ADD_BLIP_FOR_COORD open_container1_x open_container1_y open_container1_z location_blip
				disable_crane_rotate = 0
				disable_manual_control = 0
				CLEAR_HELP
				PRINT_NOW ST4_T02 5000 1 // move container into position
				//PRINT_HELP_FOREVER ST4_H02 // Use the ~q~ button to raise the rope.
				crane_help_flag = 0
				crane_help_timer = 500
				TIMERA = 20000
				TIMERB = 0
				m_goals++	
			ENDIF
		BREAK	  


		// wait for crane to get in drop position
		CASE 5

			//WRITE_DEBUG_WITH_INT disable_crane_drop disable_crane_drop

			// enable drop + help
			IF DOES_OBJECT_EXIST picked_up_container
				LOCATE_OBJECT_3D picked_up_container open_container1_x open_container1_y open_container1_z 5.0 5.0 3.0 TRUE
				IF LOCATE_OBJECT_2D picked_up_container open_container1_x open_container1_y 6.0 6.0  FALSE
					IF NOT disable_crane_drop = 0
						ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
						disable_crane_drop = 0
					ENDIF
				ELSE
					IF NOT disable_crane_drop = 1
						ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
   						disable_crane_drop = 1	 
					ENDIF
				ENDIF
				IF LOCATE_OBJECT_3D picked_up_container open_container1_x open_container1_y open_container1_z 6.0 6.0 6.0 FALSE
					IF NOT crane_help_flag = 4
						CLEAR_HELP
						PRINT_HELP_FOREVER ST4_H03 // Use the ~o~ button to release objects.
						crane_help_flag = 4
					ENDIF
				ELSE
					IF crane_help_flag  = 4
						CLEAR_HELP
						crane_help_flag = 0	 
					ENDIF
				ENDIF
			ENDIF

			// detect if container has been dropped
			IF DOES_OBJECT_EXIST picked_up_container
				GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
				IF NOT temp_obj = picked_up_container
					TIMERA = 0
					container_to_delete = picked_up_container
					CLEAR_PRINTS
					CLEAR_HELP	
					IF DOES_BLIP_EXIST container_blip[0]
						REMOVE_BLIP container_blip[0]
					ENDIF
					IF DOES_BLIP_EXIST container_blip[1]
						REMOVE_BLIP container_blip[1]
					ENDIF
					IF DOES_BLIP_EXIST container_blip[2]
						REMOVE_BLIP container_blip[2]
					ENDIF
					IF DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_blip
					ENDIF
					m_goals++
					container_collision_flag = 0
					dropped_container_last_speed = 0.0
				ENDIF
			ENDIF
			

		BREAK

		// cutscene
		CASE 6
			
			// if player drops the container from too high it should destroy the car inside - mission fail.
			IF container_collision_flag = 1
				IF HAS_OBJECT_COLLIDED_WITH_ANYTHING picked_up_container
					IF dropped_container_last_speed > 14.0
						GET_OBJECT_COORDINATES picked_up_container x y z
						CUSTOM_PLATE_FOR_NEXT_CAR CLUB N13_LLF
						CREATE_CAR CLUB x y z open_container1_car
						CHANGE_CAR_COLOUR open_container1_car 11 0
						ATTACH_CAR_TO_OBJECT open_container1_car picked_up_container  0.0 0.0 -0.8 0.0 0.0 180.0
						SORT_OUT_OBJECT_COLLISION_WITH_CAR picked_up_container open_container1_car
						BREAK_OBJECT picked_up_container TRUE
						IF NOT IS_CAR_DEAD open_container1_car
							DETACH_CAR open_container1_car 0.0 0.0 0.0 FALSE
							EXPLODE_CAR open_container1_car
						ENDIF
						failed_condition = 1
					ENDIF
					container_collision_flag = 2
				ENDIF
			ENDIF
			// get falling container speed
			IF DOES_OBJECT_EXIST picked_up_container
				GET_OBJECT_SPEED picked_up_container dropped_container_last_speed
				IF dropped_container_last_speed > 5.0
					//WRITE_DEBUG_WITH_FLOAT container_speed dropped_container_last_speed
					IF container_collision_flag = 0
						SET_OBJECT_RECORDS_COLLISIONS picked_up_container TRUE
						container_collision_flag = 1
					ENDIF
				ENDIF
			ENDIF


			IF TIMERA > 1000
				IF NOT failed_condition = 1
					IF DOES_OBJECT_EXIST picked_up_container
						IF IS_OBJECT_STATIC picked_up_container
						OR TIMERA > 2000

							CLEAR_HELP
							
							disable_crane = 1
												  
							DO_FADE 150 FADE_OUT
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE


							IF DOES_OBJECT_EXIST magno_arm
								SET_ROPE_HEIGHT_FOR_OBJECT magno_arm 0.5
							ENDIF

							RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
							DELETE_OBJECT container_to_delete

							CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_OPEN	open_container1_x open_container1_y	open_container1_z open_container1
							SET_OBJECT_HEADING open_container1 open_container1_h
												
							CUSTOM_PLATE_FOR_NEXT_CAR CLUB N13_LLF
							CREATE_CAR CLUB 0.0 0.0 0.0 open_container1_car
							CHANGE_CAR_COLOUR open_container1_car 11 0
							MARK_MODEL_AS_NO_LONGER_NEEDED CLUB

							ATTACH_CAR_TO_OBJECT open_container1_car open_container1  0.0 0.0 -0.8 0.0 0.0 180.0
							SORT_OUT_OBJECT_COLLISION_WITH_CAR open_container1 open_container1_car

							neils_car_is_attached = 1

							IF NOT IS_CHAR_DEAD buddy	   	
								SET_CHAR_COORDINATES buddy -1619.4358 68.7884 2.5495 //-1621.8356 69.4984 2.4167  
								SET_CHAR_HEADING buddy 0.0 //330.1404
							ENDIF


							SWITCH_WIDESCREEN ON						  
							SET_FIXED_CAMERA_POSITION -1618.1034 66.5178 4.5138 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT -1618.3916 67.4668 4.3861	JUMP_CUT
							ENABLE_CRANE_CONTROLS FALSE FALSE FALSE
							LOAD_SCENE open_container1_x open_container1_y	open_container1_z
							
							DO_FADE 150 FADE_IN
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE


							IF NOT IS_CHAR_DEAD buddy
								TASK_SCRATCH_HEAD buddy 
							ENDIF

							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_STL4_FA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE
							PLAY_MISSION_AUDIO 1
							PRINT_NOW &STL4_FA 5000 1
														
							
							TIMERA = 0
							m_goals++

						ENDIF
					ENDIF
				ELSE
					WAIT 2000
					m_failed = 1 
				ENDIF
			ENDIF			


		BREAK

		CASE 7
			
			IF TIMERA > 5000  
			OR IS_BUTTON_PRESSED PAD1 CROSS

				reset_crane_camera = 1
				disable_crane = 0

				CLEAR_MISSION_AUDIO 1
				DO_FADE 0 FADE_IN
				SWITCH_WIDESCREEN OFF
				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
				IF DOES_OBJECT_EXIST container[0] 
					ADD_BLIP_FOR_OBJECT container[0] container_blip[0]
				ENDIF
				IF DOES_OBJECT_EXIST container[1] 
					ADD_BLIP_FOR_OBJECT container[1] container_blip[1]
				ENDIF
				IF DOES_OBJECT_EXIST container[2] 
					ADD_BLIP_FOR_OBJECT container[2] container_blip[2]
				ENDIF

				IF NOT IS_CHAR_DEAD buddy
					OPEN_SEQUENCE_TASK temp_seq		 
						TASK_GO_STRAIGHT_TO_COORD -1 -1642.8273 54.0446 2.5495 PEDMOVE_RUN 10000
						TASK_ACHIEVE_HEADING -1 306.0
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK buddy temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
				CLEAR_PRINTS
				PRINT ST4_T04 7000 1 // pickup the other blue container
				TIMERA = 0
				TIMERB = 0
				crane_help_flag = 0
				m_goals = 99
			ENDIF
		BREAK
			
		CASE 99
			m_stage++
			m_goals = 0
		BREAK


	ENDSWITCH

	// display help
	IF m_goals > 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF crane_help_flag2[0] = 0
				PRINT_HELP ST4_H10  
				crane_help_flag2[0] = 1	
			ELSE
				IF crane_help_flag2[1] = 0
					PRINT_HELP ST4_H05  
					crane_help_flag2[1] = 1
				ELSE
					IF crane_help_flag2[2] = 0
						PRINT_HELP ST4_H07 // Use the magnet shadow to judge where objects will drop.
						crane_help_flag2[2] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN


// *************************************************************************************************************
//									STAGE 4  - place 2nd container 
// *************************************************************************************************************

STEAL4_m_stage_4:

	IF m_goals = 0
		TIMERA = 0
		TIMERB = 0
		crane_help_flag = 0

		// freeze remaining container - to stop bug 21229
		IF DOES_OBJECT_EXIST container[0]
			FREEZE_OBJECT_POSITION container[0] TRUE
		ENDIF
		IF DOES_OBJECT_EXIST container[1]
			FREEZE_OBJECT_POSITION container[1] TRUE
		ENDIF
		IF DOES_OBJECT_EXIST container[2]
			FREEZE_OBJECT_POSITION container[2] TRUE
		ENDIF  
		
		IF st4_mission_attempts = 0
			this_container_is_it = 0
		ELSE
			GENERATE_RANDOM_INT_IN_RANGE 0 2 this_container_is_it
		ENDIF

		//this_container_is_it = 1

		m_goals++	
	ENDIF

	// wait for 2nd container to get picked up
	IF m_goals = 1
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj	
		IF temp_obj = container[0]
		OR temp_obj = container[1]
		OR temp_obj = container[2]
			FREEZE_OBJECT_POSITION temp_obj FALSE
			picked_up_container = temp_obj
			ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
			disable_crane_rotate = 0
			disable_manual_control = 0
			disable_crane_drop = 1
			CLEAR_PRINTS 
			PRINT_NOW ST4_T06 7000 1 // move container into position
			IF DOES_BLIP_EXIST container_blip[0]
				REMOVE_BLIP container_blip[0]
			ENDIF
			IF DOES_BLIP_EXIST container_blip[1]
				REMOVE_BLIP container_blip[1]
			ENDIF
			IF DOES_BLIP_EXIST container_blip[2]
				REMOVE_BLIP container_blip[2]
			ENDIF
			REMOVE_BLIP location_blip
			ADD_BLIP_FOR_COORD open_container2_x  open_container2_y  open_container2_z location_blip
			CLEAR_HELP
			crane_help_flag = 0 
			TIMERA = 20000
			TIMERB = 0												  
			m_goals++
		ENDIF
	ENDIF

	IF m_goals = 2
		IF this_container_is_it = 0
			REQUEST_MODEL PEREN
			IF HAS_MODEL_LOADED PEREN
				TIMERA = 20000
				m_goals++
				PRINT_NOW ST4_T06 7000 1 // move container into position
			ENDIF
		ELSE
			REQUEST_MODEL JESTER
			IF HAS_MODEL_LOADED JESTER
				TIMERA = 20000
				m_goals++
				PRINT_NOW ST4_T06 7000 1 // move container into position
			ENDIF
		ENDIF
	ENDIF

	// wait for container to get dropped in next space
	IF m_goals = 3

//		// help text
//		IF DOES_OBJECT_EXIST picked_up_container
//			LOCATE_OBJECT_3D picked_up_container open_container2_x open_container2_y open_container2_z 5.0 5.0 3.0 TRUE
//			IF LOCATE_OBJECT_3D picked_up_container open_container2_x open_container2_y open_container2_z 6.0 6.0 5.0 FALSE
//				CLEAR_HELP
//				PRINT_HELP_FOREVER ST4_H03 // Use the ~o~ button to release objects.
//				crane_help_flag = 2
//				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
//				disable_crane_drop = 0
//			ELSE
//				IF crane_help_flag = 2
//					IF crane_help_timer > 10000
//						CLEAR_PRINTS
//					ENDIF
//					CLEAR_HELP
//					ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
//					disable_crane_drop = 1
//					crane_help_flag++
//				ENDIF
//			ENDIF	
//		ENDIF

		// enable drop + help
		IF DOES_OBJECT_EXIST picked_up_container
			LOCATE_OBJECT_3D picked_up_container open_container2_x open_container2_y open_container2_z 5.0 5.0 3.0 TRUE
			IF LOCATE_OBJECT_2D picked_up_container open_container2_x open_container2_y 6.0 6.0  FALSE
				IF NOT disable_crane_drop = 0
					ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
					disable_crane_drop = 0
				ENDIF
			ELSE
				IF NOT disable_crane_drop = 1
					ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
					disable_crane_drop = 1	 
				ENDIF
			ENDIF
			IF LOCATE_OBJECT_3D picked_up_container open_container2_x open_container2_y open_container2_z 6.0 6.0 6.0 FALSE
				IF NOT crane_help_flag = 2
					CLEAR_HELP
					PRINT_HELP_FOREVER ST4_H03 // Use the ~o~ button to release objects.
					crane_help_flag = 2
				ENDIF
			ELSE
				IF crane_help_flag  = 2
					IF crane_help_timer > 10000
						CLEAR_PRINTS
					ENDIF
					CLEAR_HELP
					crane_help_flag = 0	 
				ENDIF
			ENDIF
		ENDIF

		// wait for correct container to get placed in locate
		IF DOES_OBJECT_EXIST picked_up_container
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
			IF NOT temp_obj = picked_up_container	
				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
				disable_crane_drop = 0
				container_to_delete = picked_up_container
				IF DOES_BLIP_EXIST container_blip[0]
					REMOVE_BLIP container_blip[0]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[1]
					REMOVE_BLIP container_blip[1]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[2]
					REMOVE_BLIP container_blip[2]
				ENDIF
				IF DOES_BLIP_EXIST location_blip
					REMOVE_BLIP location_blip
				ENDIF
				TIMERA = 0
				container_collision_flag = 0
				failed_condition = 0
				m_goals++
			ENDIF
		ENDIF

	ENDIF

	IF m_goals = 4

		// if player drops the container from too high it should destroy the car inside - mission fail.
		IF container_collision_flag = 1
			IF HAS_OBJECT_COLLIDED_WITH_ANYTHING picked_up_container
				IF dropped_container_last_speed > 14.0
					GET_OBJECT_COORDINATES picked_up_container x y z
					IF this_container_is_it = 0
						CREATE_CAR PEREN x y z steal_car
					ELSE
						CREATE_CAR JESTER x y z steal_car
					ENDIF
					ATTACH_CAR_TO_OBJECT steal_car picked_up_container  0.0 0.0 -0.8 0.0 0.0 180.0
					SORT_OUT_OBJECT_COLLISION_WITH_CAR picked_up_container steal_car
					BREAK_OBJECT picked_up_container TRUE
					IF NOT IS_CAR_DEAD steal_car
						DETACH_CAR steal_car 0.0 0.0 0.0 FALSE
						EXPLODE_CAR steal_car
					ENDIF
					IF this_container_is_it = 0
						MARK_MODEL_AS_NO_LONGER_NEEDED PEREN
					ELSE
						MARK_MODEL_AS_NO_LONGER_NEEDED JESTER
					ENDIF
					failed_condition = 1
				ENDIF
				container_collision_flag = 2
			ENDIF
		ENDIF
		// get falling container speed
		IF DOES_OBJECT_EXIST picked_up_container
			GET_OBJECT_SPEED picked_up_container dropped_container_last_speed
			IF dropped_container_last_speed > 5.0
				IF container_collision_flag = 0
					SET_OBJECT_RECORDS_COLLISIONS picked_up_container TRUE
					container_collision_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF TIMERA > 1000
			IF NOT failed_condition = 1
				IF DOES_OBJECT_EXIST picked_up_container
					IF IS_OBJECT_STATIC picked_up_container
					OR TIMERA > 2000
						m_goals++
					ENDIF
				ENDIF
			ELSE
				WAIT 2000
				m_failed = 1
			ENDIF
		ENDIF

	ENDIF







	// cutscene - yes or no
	IF m_goals = 5
		IF this_container_is_it = 0
		

			// WRONG ONE !!!!!!!!!!!!!!!!!!!!
			CLEAR_HELP
			CLEAR_PRINTS

			DO_FADE 150 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SWITCH_WIDESCREEN ON

			disable_crane = 1	
			ENABLE_CRANE_CONTROLS FALSE FALSE FALSE	  

			RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
			DELETE_OBJECT container_to_delete
			CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_OPEN	open_container2_x open_container2_y	open_container2_z open_container2
			SET_OBJECT_HEADING open_container2 open_container2_h
			CREATE_CAR PEREN -1632.7860 61.2888 2.2888 open_container2_car
			GET_OBJECT_HEADING open_container2 temp_float
			temp_float += 180.0
			SET_CAR_HEADING open_container2_car temp_float 	
			SORT_OUT_OBJECT_COLLISION_WITH_CAR open_container2 open_container2_car				
			
			IF NOT IS_CHAR_DEAD buddy
				SET_CHAR_COORDINATES buddy -1631.0277 54.3581 2.5495
				SET_CHAR_HEADING buddy 5.3011
			ENDIF

			SET_FIXED_CAMERA_POSITION -1632.0006 52.1951 4.5485  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1631.9435 53.1810 4.3914	JUMP_CUT

			IF DOES_OBJECT_EXIST magno_arm
				SET_ROPE_HEIGHT_FOR_OBJECT magno_arm 0.5
			ENDIF

			DO_FADE 150 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE



			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_STL4_FB
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE
			PLAY_MISSION_AUDIO 1
			PRINT_NOW &STL4_FB 5000 1	

			// get car coord
			IF NOT IS_CAR_DEAD open_container2_car
				GET_CAR_COORDINATES open_container2_car x y z
				GET_CAR_HEADING open_container2_car heading
				
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "open_container2_car = "
				SAVE_FLOAT_TO_DEBUG_FILE x
				SAVE_FLOAT_TO_DEBUG_FILE y
				SAVE_FLOAT_TO_DEBUG_FILE z
				SAVE_FLOAT_TO_DEBUG_FILE heading

			ENDIF
			

			temp_int = 0
			TIMERA  = 0

			WHILE temp_int = 0
				
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR TIMERA  > 5000
					DO_FADE 0 FADE_IN
					m_goals++
					temp_int = 1					
				ELSE
					WAIT 0
				ENDIF
			ENDWHILE
				
			m_goals = 0
			m_stage++

		ELSE


			// RIGHT ONE !!!!!!!!!!!!!!!
			CLEAR_HELP
			CLEAR_PRINTS

			DO_FADE 150 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SWITCH_WIDESCREEN ON

			disable_crane = 1
			ENABLE_CRANE_CONTROLS FALSE FALSE FALSE

			RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
			DELETE_OBJECT container_to_delete
			CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_OPEN	open_container2_x open_container2_y	open_container2_z open_container2
			SET_OBJECT_HEADING open_container2 open_container2_h
			CREATE_CAR JESTER -1632.7860 61.2888 2.2888 steal_car
			GET_OBJECT_HEADING open_container2 temp_float
			temp_float += 180.0
			SET_CAR_HEADING steal_car temp_float  	
			SORT_OUT_OBJECT_COLLISION_WITH_CAR open_container2 steal_car		
							
			SET_FIXED_CAMERA_POSITION -1632.0006 52.1951 4.5485  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1631.9435 53.1810 4.3914	JUMP_CUT

			IF DOES_OBJECT_EXIST magno_arm
				SET_ROPE_HEIGHT_FOR_OBJECT magno_arm 0.5
			ENDIF
			IF DOES_OBJECT_EXIST magno_base
				GET_OBJECT_COORDINATES magno_base magno_base_x magno_base_y magno_base_z
			ENDIF
			magno_arm_rotate_y 	= 15.0
			magno_cabin_h 	   	= 133.526138
			magno_arm_h		   	= 133.526138
			magno_base_x		= -1610.625732
			magno_base_y		= 60.874172
			crane_rope_length	= 0.639
			GOSUB st4_reset_crane_position
				

			GOSUB st4_right_car

			DO_FADE 150 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE


			TIMERA = 0
			m_goals++

		ENDIF
	ENDIF



	IF m_goals = 6
		IF TIMERA > 1000
			
			IF NOT IS_MESSAGE_BEING_DISPLAYED
			OR IS_BUTTON_PRESSED PAD1 CROSS
				DO_FADE 0 FADE_IN

				IF NOT IS_CAR_DEAD steal_car
					SET_CAR_COORDINATES steal_car final_car_pos_x final_car_pos_y final_car_pos_z 
					SET_CAR_HEADING steal_car final_car_pos_h
				ENDIF

				m_goals = 0
				m_stage += 2
			ENDIF
		ENDIF
	ENDIF

	// display help
	IF m_goals > 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF crane_help_flag2[0] = 0
				PRINT_HELP ST4_H10  
				crane_help_flag2[0] = 1	
			ELSE
				IF crane_help_flag2[1] = 0
					PRINT_HELP ST4_H05  
					crane_help_flag2[1] = 1
				ELSE
					IF crane_help_flag2[2] = 0
						PRINT_HELP ST4_H07 // Use the magnet shadow to judge where objects will drop.
						crane_help_flag2[2] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN

// ************************************************************************************************************
//							STAGE 5 - pick up 3rd and final container
// ************************************************************************************************************
STEAL4_m_stage_5:

	IF m_goals = 0

		reset_crane_camera = 1
		disable_crane = 0

		ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
		CLEAR_MISSION_AUDIO 1
		SWITCH_WIDESCREEN OFF
		IF DOES_OBJECT_EXIST container[0] 
			ADD_BLIP_FOR_OBJECT container[0] container_blip[0]
		ENDIF
		IF DOES_OBJECT_EXIST container[1] 
			ADD_BLIP_FOR_OBJECT container[1] container_blip[1]
		ENDIF
		IF DOES_OBJECT_EXIST container[2] 
			ADD_BLIP_FOR_OBJECT container[2] container_blip[2]
		ENDIF

		IF NOT IS_CHAR_DEAD buddy
			OPEN_SEQUENCE_TASK temp_seq		 
				TASK_GO_STRAIGHT_TO_COORD -1 -1658.4763 33.2340 2.8213 PEDMOVE_RUN 10000
				TASK_ACHIEVE_HEADING -1 299.6011
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK buddy temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		CLEAR_PRINTS
		PRINT ST4_T04 7000 1 // pickup the other blue container

		TIMERA = 0
		TIMERB = 0
		crane_help_flag = 0

		IF NOT IS_CHAR_DEAD buddy
			OPEN_SEQUENCE_TASK temp_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -1650.4637 44.1144 2.5495 PEDMOVE_RUN 10000
				TASK_ACHIEVE_HEADING -1 317.7216
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK buddy temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		// freeze remaining container - to stop bug 21229
		IF DOES_OBJECT_EXIST container[0]
			FREEZE_OBJECT_POSITION container[0] TRUE
		ENDIF
		IF DOES_OBJECT_EXIST container[1]
			FREEZE_OBJECT_POSITION container[1] TRUE
		ENDIF
		IF DOES_OBJECT_EXIST container[2]
			FREEZE_OBJECT_POSITION container[2] TRUE
		ENDIF  
		
		m_goals++	
	ENDIF

	// wait for 3rd container to get picked up
	IF m_goals = 1
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj	
		IF temp_obj = container[0]
		OR temp_obj = container[1]
		OR temp_obj = container[2]
			FREEZE_OBJECT_POSITION temp_obj FALSE
			picked_up_container = temp_obj
			ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
			disable_crane_rotate = 0
			disable_manual_control = 0
			disable_crane_drop = 1
			CLEAR_PRINTS 
			PRINT_NOW ST4_T06 7000 1 // move container into position
			IF DOES_BLIP_EXIST container_blip[0]
				REMOVE_BLIP container_blip[0]
			ENDIF
			IF DOES_BLIP_EXIST container_blip[1]
				REMOVE_BLIP container_blip[1]
			ENDIF
			IF DOES_BLIP_EXIST container_blip[2]
				REMOVE_BLIP container_blip[2]
			ENDIF
			REMOVE_BLIP location_blip
			ADD_BLIP_FOR_COORD open_container3_x  open_container3_y  open_container3_z location_blip
			CLEAR_HELP
			crane_help_flag = 0 
			TIMERA = 20000
			TIMERB = 0												  
			m_goals++

		ENDIF
	ENDIF

	IF m_goals = 2
		REQUEST_MODEL JESTER
		IF HAS_MODEL_LOADED JESTER
			TIMERA = 20000
			m_goals++
			PRINT_NOW ST4_T06 7000 1 // move container into position
		ENDIF
	ENDIF

	// wait for container to get dropped in next space
	IF m_goals = 3

//		// help text
//		IF DOES_OBJECT_EXIST picked_up_container
//			LOCATE_OBJECT_3D picked_up_container open_container3_x open_container3_y open_container3_z 5.0 5.0 3.0 TRUE
//			IF LOCATE_OBJECT_3D picked_up_container open_container3_x open_container3_y open_container3_z 6.0 6.0 5.0 FALSE
//				CLEAR_HELP
//				PRINT_HELP_FOREVER ST4_H03 // Use the ~o~ button to release objects.
//				crane_help_flag = 2
//				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
//				disable_crane_drop = 0
//			ELSE
//				IF crane_help_flag = 2
//					IF crane_help_timer > 10000
//						CLEAR_PRINTS
//					ENDIF
//					CLEAR_HELP
//					ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
//					disable_crane_drop = 1
//					crane_help_flag++
//				ENDIF
//			ENDIF	
//		ENDIF

		// enable drop + help
		IF DOES_OBJECT_EXIST picked_up_container
			LOCATE_OBJECT_3D picked_up_container open_container3_x open_container3_y open_container3_z 5.0 5.0 3.0 TRUE
			IF LOCATE_OBJECT_2D picked_up_container open_container3_x open_container3_y 6.0 6.0  FALSE
				IF NOT disable_crane_drop = 0
					ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
					disable_crane_drop = 0
				ENDIF
			ELSE
				IF NOT disable_crane_drop = 1
					ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
					disable_crane_drop = 1	 
				ENDIF
			ENDIF
			IF LOCATE_OBJECT_3D picked_up_container open_container3_x open_container3_y open_container3_z 6.0 6.0 6.0 FALSE
				IF NOT crane_help_flag = 2
					CLEAR_HELP
					PRINT_HELP_FOREVER ST4_H03 // Use the ~o~ button to release objects.
					crane_help_flag = 2
				ENDIF
			ELSE
				IF crane_help_flag  = 2
					IF crane_help_timer > 10000
						CLEAR_PRINTS
					ENDIF
					CLEAR_HELP
					crane_help_flag = 0	 
				ENDIF
			ENDIF
		ENDIF

		// wait for correct container to get placed in locate
		IF DOES_OBJECT_EXIST picked_up_container
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
			IF NOT temp_obj = picked_up_container	
				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
				disable_crane_drop = 0
				container_to_delete = picked_up_container
				IF DOES_BLIP_EXIST container_blip[0]
					REMOVE_BLIP container_blip[0]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[1]
					REMOVE_BLIP container_blip[1]
				ENDIF
				IF DOES_BLIP_EXIST container_blip[2]
					REMOVE_BLIP container_blip[2]
				ENDIF
				IF DOES_BLIP_EXIST location_blip
					REMOVE_BLIP location_blip
				ENDIF
				TIMERA = 0
				container_collision_flag = 0
				failed_condition = 0
				m_goals++
			ENDIF
		ENDIF

	ENDIF

	IF m_goals = 4

		// if player drops the container from too high it should destroy the car inside - mission fail.
		IF container_collision_flag = 1
			IF HAS_OBJECT_COLLIDED_WITH_ANYTHING picked_up_container
				IF dropped_container_last_speed > 14.0
					GET_OBJECT_COORDINATES picked_up_container x y z
					CREATE_CAR JESTER x y z steal_car
					ATTACH_CAR_TO_OBJECT steal_car picked_up_container  0.0 0.0 -0.8 0.0 0.0 180.0
					SORT_OUT_OBJECT_COLLISION_WITH_CAR picked_up_container steal_car
					BREAK_OBJECT picked_up_container TRUE
					IF NOT IS_CAR_DEAD steal_car
						DETACH_CAR steal_car 0.0 0.0 0.0 FALSE
						EXPLODE_CAR steal_car
					ENDIF
					MARK_MODEL_AS_NO_LONGER_NEEDED JESTER
					failed_condition = 1
				ENDIF
				container_collision_flag = 2
			ENDIF
		ENDIF
		// get falling container speed
		IF DOES_OBJECT_EXIST picked_up_container
			GET_OBJECT_SPEED picked_up_container dropped_container_last_speed
			IF dropped_container_last_speed > 5.0
				IF container_collision_flag = 0
					SET_OBJECT_RECORDS_COLLISIONS picked_up_container TRUE
					container_collision_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF TIMERA > 1000
			IF NOT failed_condition = 1
				IF DOES_OBJECT_EXIST picked_up_container
					IF IS_OBJECT_STATIC picked_up_container
					OR TIMERA > 2000
						m_goals++
					ENDIF
				ENDIF
			ELSE
				WAIT 2000
				m_failed = 1
			ENDIF
		ENDIF

	ENDIF


	// cutscene 
	IF m_goals = 5

		CLEAR_HELP
		CLEAR_PRINTS
		
		DO_FADE 150 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SWITCH_WIDESCREEN ON
		ENABLE_CRANE_CONTROLS FALSE FALSE FALSE
		disable_crane = 1			 

		RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
		DELETE_OBJECT container_to_delete
		CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_OPEN	open_container3_x open_container3_y	open_container3_z open_container3
		SET_OBJECT_HEADING open_container3 open_container3_h
		CREATE_CAR JESTER -1644.0168 50.9619 3.0354 steal_car
		GET_OBJECT_HEADING open_container3 temp_float
		temp_float += 180.0
		SET_CAR_HEADING steal_car temp_float  	
		SORT_OUT_OBJECT_COLLISION_WITH_CAR open_container3 steal_car	

		SET_FIXED_CAMERA_POSITION -1637.0480 45.8035 3.6546  0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1637.9856 46.1327 3.5431	JUMP_CUT	
		IF DOES_OBJECT_EXIST magno_base
			GET_OBJECT_COORDINATES magno_base magno_base_x magno_base_y magno_base_z
		ENDIF
		magno_arm_rotate_y 	= 15.0
		magno_cabin_h 	   	= 133.526138
		magno_arm_h		   	= 133.526138
		magno_base_x		= -1610.625732
		magno_base_y		= 60.874172
		crane_rope_length	= 0.4
		GOSUB st4_reset_crane_position
			
//			// get car coord
//			IF NOT IS_CAR_DEAD steal_car
//				GET_CAR_COORDINATES steal_car x y z
//				GET_CAR_HEADING steal_car heading
//				
//				SAVE_NEWLINE_TO_DEBUG_FILE
//				SAVE_STRING_TO_DEBUG_FILE "open_container3_car (right one) = "
//				SAVE_FLOAT_TO_DEBUG_FILE x
//				SAVE_FLOAT_TO_DEBUG_FILE y
//				SAVE_FLOAT_TO_DEBUG_FILE z
//				SAVE_FLOAT_TO_DEBUG_FILE heading
//
//			ENDIF


		GOSUB st4_right_car
		
		DO_FADE 150 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		TIMERA = 0
		m_goals++
	ENDIF

	IF m_goals = 6
		IF TIMERA > 1000
			
			IF NOT IS_MESSAGE_BEING_DISPLAYED
			OR IS_BUTTON_PRESSED PAD1 CROSS
				DO_FADE 0 FADE_IN

				IF NOT IS_CAR_DEAD steal_car
					SET_CAR_COORDINATES steal_car final_car_pos_x final_car_pos_y final_car_pos_z 
					SET_CAR_HEADING steal_car final_car_pos_h
				ENDIF

				m_goals = 0
				m_stage++
			ENDIF
		ENDIF
	ENDIF

RETURN 








// *************************************************************************************************************
//									STAGE 6 cutscene of guys turning up 
// *************************************************************************************************************
STEAL4_m_stage_6:

	// fake creates
	IF m_goals = -1
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 	bad_wave1_ped[0]
		CREATE_CAR PONY 0.0 0.0 0.0 bad_wave1_car
	ENDIF	


	// initialise
	IF m_goals = 0

		// start baddie car driving
		IF NOT IS_CAR_DEAD bad_wave1_car
			OPEN_SEQUENCE_TASK temp_seq	   
				TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car -1686.4221 25.8359 2.7752 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
				TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car -1643.3943 19.6494 3.6706 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK temp_seq
			IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
				PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
			ENDIF
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		m_goals++
		TIMERA = 0
		
	ENDIF

	IF m_goals > 0
		
		IF IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	// switch to show bad car arriving
	IF m_goals = 1
		IF TIMERA > 500
			IF NOT IS_CAR_DEAD bad_wave1_car
				GET_CAR_COORDINATES bad_wave1_car x y z
			ENDIF
			LOAD_SCENE x y z
		
			IF NOT IS_CAR_DEAD steal_car
				SET_CAR_COORDINATES steal_car final_car_pos_x final_car_pos_y final_car_pos_z
				SET_CAR_HEADING steal_car final_car_pos_h
			ENDIF

			m_goals++
		ENDIF
	ENDIF

	// track baddie car 1
	IF m_goals = 2
		IF TIMERA < 6000
			IF NOT IS_CAR_DEAD bad_wave1_car
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bad_wave1_car 2.0 -3.0 0.0 x y z
			ENDIF
			SET_FIXED_CAMERA_POSITION -1680.0859 28.3067 3.8465 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT x y z JUMP_CUT 
		ELSE
			SET_FIXED_CAMERA_POSITION -1643.2233 13.6026 4.1338 0.0 0.0 0.0
			m_goals++
		ENDIF
	ENDIF

	// shot of baddie car 2
	IF m_goals = 3

		IF NOT IS_CAR_DEAD bad_wave1_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bad_wave1_car -6.0 1.0 1.0 x y z
		ENDIF
		POINT_CAMERA_AT_POINT x y z JUMP_CUT


		IF TIMERA < 20000
			IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
				GET_SCRIPT_TASK_STATUS bad_wave1_ped[0] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					dialogue_flag = 0
					play_single_audio_flag = 0
					m_goals++
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
				CLEAR_CHAR_TASKS bad_wave1_ped[0]
			ENDIF
			m_goals++
			dialogue_flag = 0
			play_single_audio_flag = 0
		ENDIF
		
	ENDIF

	// guys run out of car
	IF m_goals = 4
		IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PAUSE -1 1800
				IF NOT IS_CAR_DEAD bad_wave1_car
					TASK_LEAVE_CAR -1 bad_wave1_car
				ENDIF
				TASK_GO_STRAIGHT_TO_COORD -1 -1638.8365 27.1035 2.8213 PEDMOVE_RUN 999999
				TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PAUSE -1 0
				IF NOT IS_CAR_DEAD bad_wave1_car
					TASK_LEAVE_CAR -1 bad_wave1_car
				ENDIF
				TASK_GO_STRAIGHT_TO_COORD -1 -1639.6755 29.1963 2.8213 PEDMOVE_RUN 999999
				TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF	

		IF NOT IS_CHAR_DEAD bad_wave1_ped[2] 
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PAUSE -1 1000
				IF NOT IS_CAR_DEAD bad_wave1_car
					TASK_LEAVE_CAR -1 bad_wave1_car
				ENDIF
				TASK_GO_STRAIGHT_TO_COORD -1 -1641.2803 27.2014 2.8213 PEDMOVE_RUN 999999
				TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		dialogue_flag = 0
		play_single_audio_flag = 0
		m_goals++
		TIMERA = 0
	ENDIF

	IF m_goals = 5

		IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
			IF NOT IS_CHAR_IN_ANY_CAR bad_wave1_ped[0]
				IF dialogue_flag = 0
					$play_single_audio_text = &STL4_GA
					play_single_audio_sound	= SOUND_STL4_GA	// HEY! What the fuck do you think you’re doing?
					play_single_audio_ped = bad_wave1_ped[0]
					play_single_audio_attached = 0
					GOSUB STEAL4_play_single_audio
					IF play_single_audio_flag = 4
						play_single_audio_flag = 0
						dialogue_flag++
						m_goals = 99
					ENDIF	
				ENDIF
			ENDIF
		ENDIF

	ENDIF


	IF m_goals = 99
		
		//magno_camera_type = 0	// set camera view to close 
		magno_arm_h	= 130.0

		// set final positions of all the shit - and tell peds to do shit.
		IF NOT IS_CAR_DEAD bad_wave1_car
			SET_CAR_COORDINATES bad_wave1_car -1642.8761 19.5037 2.5547 
			SET_CAR_HEADING bad_wave1_car 259.5122
		ENDIF

		IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
			CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[0]
			SET_CHAR_COORDINATES bad_wave1_ped[0] -1638.8365 27.1035 2.8213
			SET_CHAR_HEADING bad_wave1_ped[0] 0.0
			OPEN_SEQUENCE_TASK temp_seq
				IF NOT IS_CHAR_DEAD buddy
					TASK_GOTO_CHAR -1 buddy 10000 25.0
				ENDIF
				//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
			CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[1]
			SET_CHAR_COORDINATES bad_wave1_ped[1] -1639.6755 29.1963 2.8213
			SET_CHAR_HEADING bad_wave1_ped[1] 0.0
			OPEN_SEQUENCE_TASK temp_seq
				IF NOT IS_CHAR_DEAD buddy
					TASK_GOTO_CHAR -1 buddy 10000 20.0
				ENDIF
				//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
			CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[2]
			SET_CHAR_COORDINATES bad_wave1_ped[2] -1641.2803 27.2014 2.8213
			SET_CHAR_HEADING bad_wave1_ped[2] 0.0
			OPEN_SEQUENCE_TASK temp_seq
				IF NOT IS_CHAR_DEAD buddy
					TASK_GOTO_CHAR -1 buddy 10000 18.0
				ENDIF
				//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
				IF NOT IS_CHAR_DEAD buddy
					TASK_KILL_CHAR_ON_FOOT -1 buddy
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF


		CLEAR_PRINTS  
		CLEAR_HELP
		reset_crane_camera = 1
		disable_crane_exit = 0
		SWITCH_WIDESCREEN OFF
		ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
		disable_crane = 0
		m_goals = 0
		m_stage++

	ENDIF

RETURN


st4_right_car:
	
	IF NOT IS_CHAR_DEAD buddy
		IF NOT IS_CAR_DEAD steal_car
			WARP_CHAR_INTO_CAR buddy steal_car
		ENDIF				
	ENDIF
	IF NOT IS_CHAR_DEAD buddy
		GIVE_WEAPON_TO_CHAR buddy WEAPONTYPE_PISTOL 9999
	ENDIF			
	CLEAR_MISSION_AUDIO 1
	LOAD_MISSION_AUDIO 1 SOUND_STL4_EA
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE
	PLAY_MISSION_AUDIO 1
	PRINT_NOW &STL4_EA 5000 1
	IF NOT IS_CAR_DEAD steal_car 	
		SET_CAR_CRUISE_SPEED steal_car 3.5
		SET_CAR_HEALTH steal_car 2000
		SET_CAN_BURST_CAR_TYRES steal_car FALSE
		VEHICLE_DOES_PROVIDE_COVER  steal_car FALSE		   
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS steal_car 0.0 15.0 0.0 final_car_pos_x final_car_pos_y final_car_pos_z 
		GET_CAR_HEADING steal_car final_car_pos_h					   
		CAR_GOTO_COORDINATES_ACCURATE steal_car final_car_pos_x final_car_pos_y final_car_pos_z
	ENDIF

	GOSUB st4_create_baddies1

RETURN
			

// *************************************************************************************************************
//						STAGE 5 - 1st wave, bad guys turn up wait for player to drop box on them 
// *************************************************************************************************************
STEAL4_m_stage_7:

		SWITCH m_goals 
		CASE 0 
			CLEAR_PRINTS

			IF NOT buddy_blip_type = 2
				buddy_blip_type	= 2
				GOSUB reset_buddy_blip
			ENDIF
			play_single_audio_flag = 0	
			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING buddy_health COUNTER_DISPLAY_BAR 1 ST4_30 
			PRINT_HELP ST4_H09 // Press the ~t~ button to exit the crane.

			IF NOT IS_CHAR_DEAD buddy
				SET_CHAR_DECISION_MAKER buddy buddy_dm
			ENDIF
			m_goals++
		BREAK
		

		// wait for a bit before cesar gets out the car and starts shooting
		CASE 1

			IF NOT IS_CHAR_DEAD buddy

				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CAR_DEAD steal_car
						TASK_LEAVE_CAR -1 steal_car
					ENDIF						  
					//TASK_GO_STRAIGHT_TO_COORD -1 -1637.4160 47.2271 2.8213 PEDMOVE_RUN 999999
					//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[0]
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
						TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[1]
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
						TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[2]
					ENDIF
					
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK buddy temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				dialogue_flag = 0
				play_single_audio_flag = 0
				m_goals++
			ENDIF

			TIMERA = 0
			TIMERB = 0
		BREAK


		// wait a while before 2nd attack from wave 1 arrive
		CASE 2
			
			IF dialogue_flag = 0
				$play_single_audio_text = &STL4_HA
				play_single_audio_sound	= SOUND_STL4_HA	// CJ, I could do with some help!
				play_single_audio_ped = buddy
				play_single_audio_attached = 0
				GOSUB STEAL4_play_single_audio
				IF play_single_audio_flag = 4
					play_single_audio_flag = 0
					dialogue_flag++
					TIMERA = 0
				ENDIF
			ENDIF

			IF TIMERA > 45000
				m_goals++
			ELSE
				IF IS_CHAR_DEAD bad_wave1_ped[0]
				AND IS_CHAR_DEAD bad_wave1_ped[1]
				AND IS_CHAR_DEAD bad_wave1_ped[2]
					m_goals++
				ENDIF
			ENDIF

			// update baddie ai if player is on ground
			IF NOT player_is_in_crane = 2 
				IF player_on_foot = 0
					
					CLEAR_HELP

					temp_int = 0
					WHILE temp_int < 3
						IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
							SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 30
							GET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							IF temp_int2 < 200
								temp_int2 += 100
								IF temp_int2 > 200
									temp_int2 = 200
								ENDIF
								SET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							ENDIF
							CLEAR_CHAR_TASKS bad_wave1_ped[temp_int]
						ENDIF
						temp_int++
					ENDWHILE

					// give new tasks
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 20.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 24.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 22.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					
					player_on_foot = 1
				ENDIF
			ELSE
				IF player_on_foot = 1

					PRINT_HELP_FOREVER ST4_H09 // Press the ~t~ button to exit the crane.
	
					temp_int = 0
					WHILE temp_int < 3
						IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
							SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 40
							GET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							IF temp_int2 > 50
								SET_CHAR_HEALTH bad_wave1_ped[temp_int] 50
							ENDIF
							CLEAR_CHAR_TASKS bad_wave1_ped[temp_int]
						ENDIF
						temp_int++
					ENDWHILE

					// give new tasks
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 20.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 22.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy
								TASK_GOTO_CHAR -1 buddy 10000 24.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
//							IF NOT IS_CAR_DEAD steal_car
//								TASK_DESTROY_CAR -1 steal_car
//							ENDIF
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF

					player_on_foot = 0	
				ENDIF
			ENDIF

		BREAK


		CASE 3

			CLEAR_AREA -1711.5900 44.8495 2.7813 20.0 TRUE
	
			CREATE_CAR SENTINEL -1711.5900 44.8495 2.7813 bad_wave1_car2
			SET_CAR_HEADING bad_wave1_car2 232.9281
			SET_CAR_STRAIGHT_LINE_DISTANCE bad_wave1_car2 50

			CREATE_CHAR_INSIDE_CAR bad_wave1_car2 PEDTYPE_MISSION1 WBDYG1 bad_wave1_ped[3]
			CREATE_CHAR_AS_PASSENGER bad_wave1_car2 PEDTYPE_MISSION1 WBDYG1 0 bad_wave1_ped[4]
			CREATE_CHAR_AS_PASSENGER bad_wave1_car2 PEDTYPE_MISSION1 WBDYG1 1 bad_wave1_ped[5]
			
			ADD_BLIP_FOR_CHAR bad_wave1_ped[3] bad_wave1_ped_blip[3]
			ADD_BLIP_FOR_CHAR bad_wave1_ped[4] bad_wave1_ped_blip[4]
			ADD_BLIP_FOR_CHAR bad_wave1_ped[5] bad_wave1_ped_blip[5]

			CLEAR_AREA -1697.0397 14.7378 2.7813 5.0 TRUE  	
						
			IF NOT IS_CAR_DEAD bad_wave1_car2				  
				OPEN_SEQUENCE_TASK temp_seq	   
					TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car2 -1686.4221 25.8359 2.7752 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
					TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car2 -1647.9377 27.2328 2.5408 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
				CLOSE_SEQUENCE_TASK temp_seq				  
				IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
					PERFORM_SEQUENCE_TASK bad_wave1_ped[3] temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF


			temp_int = 3
			WHILE temp_int < 6
				IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
					GIVE_WEAPON_TO_CHAR bad_wave1_ped[temp_int] WEAPONTYPE_PISTOL 9999
					SET_CHAR_DECISION_MAKER bad_wave1_ped[temp_int] tough_dm
					SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 40
					SET_CHAR_HEALTH bad_wave1_ped[temp_int] 50
					SET_CHAR_SUFFERS_CRITICAL_HITS bad_wave1_ped[temp_int] FALSE
				ENDIF
			temp_int++
			ENDWHILE

			TIMERA = 0

			play_single_audio_flag = 0
			m_goals++

		BREAK

		CASE 4
			IF TIMERA < 15000
				IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
					GET_SCRIPT_TASK_STATUS bad_wave1_ped[3] PERFORM_SEQUENCE_TASK temp_int
					IF temp_int = FINISHED_TASK
						m_goals++
					ENDIF
				ELSE
					m_goals++	
				ENDIF
			ELSE
				IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
					CLEAR_CHAR_TASKS bad_wave1_ped[3]
				ENDIF
				m_goals++
			ENDIF
		BREAK

		CASE 5

			IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CAR_DEAD bad_wave1_car2
						TASK_LEAVE_CAR -1 bad_wave1_car2
					ENDIF
					//TASK_GO_STRAIGHT_TO_COORD -1 -1646.0371 36.7965 2.8213 PEDMOVE_RUN 999999
					IF NOT IS_CHAR_DEAD buddy
						TASK_GOTO_CHAR -1 buddy 10000 20.0
					ENDIF
					//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
					IF NOT IS_CHAR_DEAD buddy
						TASK_KILL_CHAR_ON_FOOT -1 buddy
					ENDIF
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK bad_wave1_ped[3] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF

			IF NOT IS_CHAR_DEAD bad_wave1_ped[4]
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 1000
					IF NOT IS_CAR_DEAD bad_wave1_car2
						TASK_LEAVE_CAR -1 bad_wave1_car2
					ENDIF
					//TASK_GO_STRAIGHT_TO_COORD -1 -1646.4087 34.4707 2.8213 PEDMOVE_RUN 999999
					IF NOT IS_CHAR_DEAD buddy
						TASK_GOTO_CHAR -1 buddy 10000 22.0
					ENDIF
					//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
					IF NOT IS_CHAR_DEAD buddy
						TASK_KILL_CHAR_ON_FOOT -1 buddy
					ENDIF
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK bad_wave1_ped[4] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF	

			IF NOT IS_CHAR_DEAD bad_wave1_ped[5] 
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 1500
					IF NOT IS_CAR_DEAD bad_wave1_car2
						TASK_LEAVE_CAR -1 bad_wave1_car2
					ENDIF
					//TASK_GO_STRAIGHT_TO_COORD -1 -1648.1427 36.3543 2.8213 PEDMOVE_RUN 999999
					IF NOT IS_CHAR_DEAD buddy
						TASK_GOTO_CHAR -1 buddy 10000 24.0
					ENDIF
					//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
//					IF NOT IS_CAR_DEAD steal_car
//						TASK_DESTROY_CAR -1 steal_car
//					ENDIF
					IF NOT IS_CHAR_DEAD buddy
						TASK_KILL_CHAR_ON_FOOT -1 buddy
					ENDIF
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK bad_wave1_ped[5] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
			
			TIMERA = 0
			dialogue_flag = 0
			play_single_audio_flag = 0
			m_goals++
		BREAK

		

		// wait for all the guys in wave1 to die
		CASE 6

			IF dialogue_flag = 0
				$play_single_audio_text = &STL4_GB
				play_single_audio_sound	= SOUND_STL4_GB	// HEY! Hold it right there!
				play_single_audio_ped = bad_wave1_ped[3]
				play_single_audio_attached = 0
				GOSUB STEAL4_play_single_audio
				IF play_single_audio_flag = 4
					play_single_audio_flag = 0
					dialogue_flag++
				ENDIF	
			ENDIF
			IF dialogue_flag = 1
				$play_single_audio_text = &STL4_HC
				play_single_audio_sound	= SOUND_STL4_HC	// CJ, I could do with some help!
				play_single_audio_ped = buddy
				play_single_audio_attached = 0
				GOSUB STEAL4_play_single_audio
				IF play_single_audio_flag = 4
					play_single_audio_flag = 0
					dialogue_flag++
				ENDIF
			ENDIF


			IF 	IS_CHAR_DEAD bad_wave1_ped[0]
			AND IS_CHAR_DEAD bad_wave1_ped[1]
			AND IS_CHAR_DEAD bad_wave1_ped[2]
			AND IS_CHAR_DEAD bad_wave1_ped[3]
			AND IS_CHAR_DEAD bad_wave1_ped[4]
				IF IS_CHAR_DEAD bad_wave1_ped[5]
					m_goals = 99
				ENDIF
			ENDIF


			// update buddie ai if player is on ground
			IF NOT player_is_in_crane = 2 
				IF player_on_foot = 0

					CLEAR_HELP

					temp_int = 0
					WHILE temp_int < 6
						IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
							SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 30
							GET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							IF temp_int2 < 200
								temp_int2 += 100
								IF temp_int2 > 200
									temp_int2 = 200
								ENDIF
								SET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							ENDIF
							CLEAR_CHAR_TASKS bad_wave1_ped[temp_int]
						ENDIF
						temp_int++
					ENDWHILE

					// give new tasks
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CHAR -1 scplayer 10000 30.0
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CHAR -1 scplayer 10000 32.0
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CHAR -1 scplayer 10000 34.0
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[3] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[4]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CHAR -1 scplayer 10000 28.0
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[4] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[5]
						OPEN_SEQUENCE_TASK temp_seq
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[5] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					
					player_on_foot = 1
				ENDIF
			ELSE
				IF player_on_foot = 1

					PRINT_HELP_FOREVER ST4_H09 // Press the ~t~ button to exit the crane.

					temp_int = 0
					WHILE temp_int < 6
						IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
							SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 40
							GET_CHAR_HEALTH bad_wave1_ped[temp_int] temp_int2
							IF temp_int2 > 50
								SET_CHAR_HEALTH bad_wave1_ped[temp_int] 50
							ENDIF
							CLEAR_CHAR_TASKS bad_wave1_ped[temp_int]
						ENDIF
						temp_int++
					ENDWHILE

					// give new tasks
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 28.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 32.0
							ENDIF
							//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 26.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
//							IF NOT IS_CAR_DEAD steal_car
//								TASK_DESTROY_CAR -1 steal_car
//							ENDIF
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 29.0
							ENDIF
							//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[3] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[4]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 24.0
							ENDIF
							//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[4] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF
					IF NOT IS_CHAR_DEAD bad_wave1_ped[5]
						OPEN_SEQUENCE_TASK temp_seq
							IF NOT IS_CHAR_DEAD buddy 
								TASK_GOTO_CHAR -1 buddy 10000 20.0
							ENDIF
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
//							IF NOT IS_CAR_DEAD steal_car
//								TASK_DESTROY_CAR -1 steal_car
//							ENDIF
							IF NOT IS_CHAR_DEAD buddy
								TASK_KILL_CHAR_ON_FOOT -1 buddy
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK bad_wave1_ped[5] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ENDIF

					player_on_foot = 0	
				ENDIF
			ENDIF


		BREAK
		CASE 99
			m_goals = 0
			m_stage += 2
		BREAK
	
		ENDSWITCH

		// make sure buddy is killing someone
		IF NOT IS_CHAR_DEAD buddy
			GET_SCRIPT_TASK_STATUS buddy PERFORM_SEQUENCE_TASK temp_int
			IF temp_int = FINISHED_TASK
				OPEN_SEQUENCE_TASK temp_seq					
					IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
						TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
						TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[0]
					ELSE
						IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
							TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[1]
						ELSE
							IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
								TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[2]
							ELSE
								IF NOT IS_CHAR_DEAD bad_wave1_ped[3]
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[3]
								ELSE
									IF NOT IS_CHAR_DEAD bad_wave1_ped[4]
										TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
										TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[4]
									ELSE
										IF NOT IS_CHAR_DEAD bad_wave1_ped[5]
											TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
											TASK_KILL_CHAR_ON_FOOT -1 bad_wave1_ped[5]
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					TASK_PAUSE -1 1000
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK buddy temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
		ENDIF

		// check to see if bad dudes have been hit by container
		IF m_frame_num < 6
			temp_int = m_frame_num
			//WHILE temp_int < 6

//				temp_int2 = 0
//				WHILE temp_int2 < 9

					IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
						IF DOES_OBJECT_EXIST last_picked_up_container
							IF NOT IS_OBJECT_STATIC last_picked_up_container
								//GET_OBJECT_VELOCITY container[temp_int2] x y z
								GET_OBJECT_SPEED last_picked_up_container temp_float
								//IF NOT x = 0.0
								//OR NOT y = 0.0
								//OR NOT z = 0.0
								IF temp_float > 4.0
									IF IS_CHAR_TOUCHING_OBJECT_ON_FOOT bad_wave1_ped[temp_int] last_picked_up_container
										GET_SCRIPT_TASK_STATUS bad_wave1_ped[temp_int] TASK_DIE temp_int3
										IF temp_int3 = FINISHED_TASK 
											TASK_DIE bad_wave1_ped[temp_int]
										ENDIF
									ENDIF 
								ENDIF
								// check if char is 'inside' container
								IF LOCATE_CHAR_ON_FOOT_OBJECT_3D bad_wave1_ped[temp_int] last_picked_up_container 2.0 2.0 2.0 FALSE
									GET_SCRIPT_TASK_STATUS bad_wave1_ped[temp_int] TASK_DIE temp_int3
									IF temp_int3 = FINISHED_TASK 
										TASK_DIE bad_wave1_ped[temp_int]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

//				temp_int2++
//				ENDWHILE

			//temp_int++
			//ENDWHILE
		ENDIF

		// if baddie car is in water make it explode
		IF m_frame_num = 1 
			IF DOES_VEHICLE_EXIST bad_wave1_car
				IF NOT IS_CAR_DEAD bad_wave1_car
					GET_CAR_COORDINATES bad_wave1_car x y z
					IF z < 0.0
						EXPLODE_CAR bad_wave1_car
					ENDIF
				ENDIF
			ENDIF
			IF DOES_VEHICLE_EXIST bad_wave1_car2
				IF NOT IS_CAR_DEAD bad_wave1_car2
					GET_CAR_COORDINATES bad_wave1_car2 x y z
					IF z < 0.0
						EXPLODE_CAR bad_wave1_car2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		// remove blips if chars die
		IF m_frame_num = 2
			temp_int = 0
			WHILE temp_int < 6
				IF IS_CHAR_DEAD bad_wave1_ped[temp_int]
					IF DOES_BLIP_EXIST bad_wave1_ped_blip[temp_int]
						REMOVE_BLIP bad_wave1_ped_blip[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
		ENDIF

//		// check if player hits baddies with swinging car on crane
//		IF m_frame_num = 3
//			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
//			IF NOT temp_car = -1
//				IF NOT IS_CAR_DEAD temp_car
//					GET_CAR_SPEED temp_car temp_float
//					IF temp_float > 6.0
//						temp_int = 0
//						WHILE temp_int < 6
//							IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
//								IF IS_CHAR_TOUCHING_VEHICLE bad_wave1_ped[temp_int] temp_car 
//									DAMAGE_CHAR bad_wave1_ped[temp_int] 100 FALSE
//								ENDIF
//							ENDIF
//						temp_int++
//						ENDWHILE
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF


RETURN

//// *************************************************************************************************************
////								STAGE 5 - 2nd wave come in do drive by  
//// *************************************************************************************************************
STEAL4_m_stage_8:
RETURN

// *************************************************************************************************************
//								STAGE 6 - train arrives  
// *************************************************************************************************************
STEAL4_m_stage_9:

		IF NOT player_is_in_crane = 2 
			IF player_on_foot = 0
				CLEAR_HELP
				player_on_foot = 1
			ENDIF
		ELSE
			IF player_on_foot = 1
				PRINT_HELP_FOREVER ST4_H09 // Press the ~t~ button to exit the crane.
				player_on_foot = 0
			ENDIF
		ENDIF


		SWITCH m_goals 
		// create train that charges into docks
		CASE 0
			TIMERA = 0
//			REQUEST_MODEL FREIGHT
//			REQUEST_MODEL FREIFLAT
//			WHILE NOT HAS_MODEL_LOADED FREIGHT
//			   OR NOT HAS_MODEL_LOADED FREIFLAT
//				WAIT 0
//			ENDWHILE

			m_goals++
		BREAK

		CASE 1
//			IF TIMERA > 4000	
//				CREATE_MISSION_TRAIN 10 -1793.88 -33.55 5.31 FALSE train
//				SET_TRAIN_SPEED train 7.0
//				SET_TRAIN_CRUISE_SPEED train 7.0
//				SET_CAR_PROOFS train TRUE TRUE TRUE TRUE TRUE
//				MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
//				MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT
//				m_goals++
//			ENDIF
			IF TIMERA > 2000
				m_goals++
			ENDIF
		BREAK

		CASE 2
			IF NOT player_is_in_crane = 2
				SET_PLAYER_CONTROL player1 OFF
			ELSE
				disable_crane = 1
			ENDIF
//			LOAD_SCENE -1793.88 -33.55 5.31
//			SWITCH_WIDESCREEN ON	  
//			SET_FIXED_CAMERA_POSITION -1748.6940 -35.7273 3.7073 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -1749.6890 -35.6875 3.7987 JUMP_CUT
			

//			IF NOT IS_CAR_DEAD steal_car
//				GET_CAR_COORDINATES steal_car x y z
//				IF IS_2D_POINT_IN_TRIANGLE x y -1536.6421 134.1915 -1538.9832 136.8876 -1652.6705 22.8175
//				OR IS_2D_POINT_IN_TRIANGLE x y -1652.6705 22.8175 -1650.6829 20.2309 -1536.6421 134.1915
//					IF z < 4.0
//
//						CLEAR_MISSION_AUDIO 1
//						LOAD_MISSION_AUDIO 1 SOUND_STL4_KA
//						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
//							WAIT 0
//						ENDWHILE
//						PLAY_MISSION_AUDIO 1
//						PRINT_NOW &STL4_KA 5000 1
//						
//					ENDIF
//				ENDIF
//			ENDIF

			TIMERA = 0
			m_goals++
		BREAK

		// show a couple more bad guys runnin around corner
		CASE 3 
			IF TIMERA > 0
				
				SWITCH_WIDESCREEN ON

				disable_crane = 1
				ENABLE_CRANE_CONTROLS FALSE FALSE FALSE

				SET_FIXED_CAMERA_POSITION -1675.5979 25.4341 2.8434 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1674.7222 25.8894 3.0036	JUMP_CUT
				
//				IF NOT IS_CAR_DEAD train
//					FREEZE_CAR_POSITION train TRUE
//				ENDIF

				// create a couple more bad guys	 
				CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 -1676.0950 26.8479 2.5869 extra_baddie[0]
				SET_CHAR_HEADING extra_baddie[0] 314.8203
				ADD_BLIP_FOR_CHAR extra_baddie[0] extra_baddie_blip[0]
				

				CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 -1674.4042 23.8107 2.6233 extra_baddie[1]
				SET_CHAR_HEADING extra_baddie[1] 314.8204
				ADD_BLIP_FOR_CHAR extra_baddie[1] extra_baddie_blip[1]
	
				temp_int = 0
				WHILE temp_int < 2
					SET_CHAR_DECISION_MAKER extra_baddie[temp_int] empty_dm
					SET_CHAR_ACCURACY extra_baddie[temp_int] 20
					SET_CHAR_HEALTH  extra_baddie[temp_int] 100
					GIVE_WEAPON_TO_CHAR extra_baddie[temp_int] WEAPONTYPE_PISTOL 9999
				temp_int++
				ENDWHILE

				TASK_GO_STRAIGHT_TO_COORD extra_baddie[0] -1664.1133 34.4836 2.5869 PEDMOVE_RUN 999999
				TASK_GO_STRAIGHT_TO_COORD extra_baddie[1] -1662.3065 31.2954 2.5869 PEDMOVE_RUN 999999
	
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_STL4_GC
 				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE
				PLAY_MISSION_AUDIO 1
				PRINT_NOW &STL4_GC 5000 1

				m_goals++
				
			ENDIF
		BREAK

		CASE 4
			IF HAS_MISSION_AUDIO_FINISHED 1
				m_goals++
			ENDIF
		BREAK

		CASE 5
				SWITCH_WIDESCREEN OFF

				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
				disable_crane = 0

//				// unfreeze train
//				IF NOT IS_CAR_DEAD train
//					FREEZE_CAR_POSITION train FALSE
//				ENDIF

				// create a couple more bad guys
				IF NOT IS_CHAR_DEAD extra_baddie[0]
					SET_CHAR_COORDINATES extra_baddie[0] -1657.3983 32.9707 2.8213
					SET_CHAR_HEADING extra_baddie[0] 320.0 
				ENDIF
				IF NOT IS_CHAR_DEAD extra_baddie[1]
					SET_CHAR_COORDINATES extra_baddie[1] -1655.2644 31.7060 2.8213
					SET_CHAR_HEADING extra_baddie[1] 320.0
				ENDIF
				temp_int = 0
				WHILE temp_int < 2
					IF NOT IS_CHAR_DEAD extra_baddie[temp_int]
						SET_CHAR_DECISION_MAKER extra_baddie[temp_int] tough_dm
						SET_CHAR_ACCURACY extra_baddie[temp_int] 20
						SET_CHAR_HEALTH  extra_baddie[temp_int] 100
						GIVE_WEAPON_TO_CHAR extra_baddie[temp_int] WEAPONTYPE_PISTOL 9999
						SET_CHAR_RELATIONSHIP extra_baddie[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 
						SET_CHAR_RELATIONSHIP extra_baddie[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						//TASK_KINDA_STAY_IN_SAME_PLACE extra_baddie[temp_int] TRUE
						IF NOT IS_CHAR_DEAD buddy
							TASK_GOTO_CHAR extra_baddie[temp_int] buddy 10000 20.0
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE			

				IF NOT IS_CHAR_DEAD buddy
					IF NOT IS_CHAR_DEAD extra_baddie[0]
						TASK_KILL_CHAR_ON_FOOT buddy extra_baddie[0]
					ENDIF
				ENDIF

				IF player_is_in_crane = 2
					disable_crane = 0
					reset_crane_camera = 1
				ELSE
					RESTORE_CAMERA_JUMPCUT
					SET_PLAYER_CONTROL player1 ON
				ENDIF
				CLEAR_PRINTS
				//PRINT_NOW ST4_32 7000 1 //Make sure the train doesn't destroy the car you came here to steal.'
				
				IF NOT IS_CAR_DEAD steal_car
					IF NOT DOES_BLIP_EXIST steal_car_blip
						ADD_BLIP_FOR_CAR steal_car steal_car_blip
						SET_BLIP_AS_FRIENDLY steal_car_blip TRUE
					ENDIF
				ENDIF

				// give char decision maker response to jump out the way of cars
				IF NOT IS_CHAR_DEAD buddy
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE buddy_dm EVENT_POTENTIAL_GET_RUN_OVER TASK_COMPLEX_GET_OUT_OF_WAY_OF_CAR 0.0 100.0 0.0 100.0 0 1
				ENDIF

				m_goals++
		BREAK
		
		// stop train once it reaches it's destination
		CASE 6

			// make cesar get in car if player is in car
			IF NOT IS_CHAR_DEAD buddy
				IF NOT IS_CAR_DEAD steal_car
					IF IS_CHAR_IN_CAR scplayer steal_car
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer buddy 15.0 15.0 5.0 FALSE
							OPEN_SEQUENCE_TASK temp_seq
								TASK_GOTO_CAR -1 steal_car 999999 10.0
								TASK_ENTER_CAR_AS_PASSENGER -1 steal_car 999999 0
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK buddy temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							//PRINT_NOW ST4_25 5000 1 // CESAR - 'Lets get the hell out of here!!'
							TIMERA = 30000
							dialogue_flag = 0
							play_single_audio_flag = 0
							m_goals++
						ENDIF
					ENDIF

					// if extra baddies are dead make cesar get in car
					IF IS_CHAR_DEAD extra_baddie[0]
					AND IS_CHAR_DEAD extra_baddie[1]
						m_goals++
						dialogue_flag = 0
						play_single_audio_flag = 0
					ENDIF

				ENDIF
			ENDIF

		BREAK


		// make cesar get in car
		CASE 7
			IF NOT IS_CHAR_DEAD buddy
				IF NOT IS_CAR_DEAD steal_car
					IF NOT IS_CHAR_IN_CAR buddy steal_car
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CAR -1 steal_car 999999 10.0
							TASK_ENTER_CAR_AS_PASSENGER -1 steal_car 999999 0
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK buddy temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
						//PRINT_NOW ST4_25 5000 1 // CESAR - 'Lets get the hell out of here!!'
						TIMERA = 30000
						m_goals++
						GENERATE_RANDOM_INT_IN_RANGE 0 2 dialogue_flag
					ELSE
						TIMERA = 30000
						m_goals++
						GENERATE_RANDOM_INT_IN_RANGE 0 2 dialogue_flag
					ENDIF
				ENDIF
			ENDIF

		BREAK


		// wait for cesar to get in car
		CASE 8

			IF dialogue_flag = 0
				$play_single_audio_text = &STL4_JA
				play_single_audio_sound	= SOUND_STL4_JA	// Let’s just grab the car and go!
				play_single_audio_ped = buddy
				play_single_audio_attached = 0
				GOSUB STEAL4_play_single_audio
				IF play_single_audio_flag = 4
					play_single_audio_flag = 0
					dialogue_flag = 2
				ENDIF
			ENDIF
			IF dialogue_flag = 1
				$play_single_audio_text = &STL4_JB
				play_single_audio_sound	= SOUND_STL4_JB	// Let’s just get the car and bug out!
				play_single_audio_ped = buddy
				play_single_audio_attached = 0
				GOSUB STEAL4_play_single_audio
				IF play_single_audio_flag = 4
					play_single_audio_flag = 0
					dialogue_flag = 2
				ENDIF
			ENDIF			

			// if player is already in car, but cesar isn't	tell player to pick up ryder
			IF NOT IS_CAR_DEAD steal_car
				IF IS_CHAR_IN_CAR scplayer steal_car
					IF NOT IS_CHAR_DEAD buddy
						IF NOT IS_CHAR_IN_CAR buddy steal_car
							IF TIMERA > 35000
								PRINT_NOW ST4_29 5000 1 //Wait for Cesar!
								TIMERA = 0
							ENDIF

							GET_SCRIPT_TASK_STATUS buddy PERFORM_SEQUENCE_TASK temp_int
							IF temp_int = FINISHED_TASK
								OPEN_SEQUENCE_TASK temp_seq
									TASK_GOTO_CAR -1 steal_car 999999 10.0
									TASK_ENTER_CAR_AS_PASSENGER -1 steal_car 999999 0
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK buddy temp_seq
								CLEAR_SEQUENCE_TASK temp_seq	
							ENDIF

						ENDIF
					ENDIF	
				ENDIF
			ENDIF
			
			// if cesar is in car but player isn't tell player to get in car with him
			IF play_single_audio_flag = 0
				IF NOT IS_CAR_DEAD steal_car
					IF NOT IS_CHAR_DEAD buddy
						IF IS_CHAR_IN_CAR buddy steal_car
							IF NOT IS_CHAR_IN_CAR scplayer steal_car
								IF TIMERA > 30000
									PRINT_NOW ST4_28 5000 1 //Get in the car with Cesar.
									TIMERA = 0
								ENDIF
							ENDIF					
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// if player and cesar are in car continue
			IF play_single_audio_flag = 0
				IF NOT IS_CAR_DEAD steal_car
					IF NOT IS_CHAR_DEAD buddy
						IF IS_CHAR_IN_CAR buddy steal_car
							IF IS_CHAR_IN_CAR scplayer steal_car
								m_goals++
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		BREAK

		CASE 9
			IF NOT IS_CAR_DEAD steal_car
				IF NOT IS_CHAR_DEAD buddy
					IF IS_CHAR_IN_CAR buddy steal_car
						SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR buddy FALSE
					ENDIF
				ENDIF
			ENDIF

			CLEAR_ONSCREEN_COUNTER buddy_health
			m_goals = 99
		BREAK

		CASE 99
			m_goals = 0
			m_stage++
		BREAK

		ENDSWITCH

		// if player is in steal car remove blip		
		IF m_goals > 3
			IF DOES_BLIP_EXIST steal_car_blip
				IF NOT IS_CAR_DEAD steal_car
					IF IS_CHAR_IN_CAR scplayer steal_car
						REMOVE_BLIP steal_car_blip
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_CAR_DEAD steal_car
					IF NOT IS_CHAR_IN_CAR scplayer steal_car
						ADD_BLIP_FOR_CAR steal_car steal_car_blip
						SET_BLIP_AS_FRIENDLY steal_car_blip TRUE
					ENDIF
				ENDIF
			ENDIF
		ENDIF


RETURN

// *************************************************************************************************************
//								STAGE 9 - get back to base  
// *************************************************************************************************************
STEAL4_m_stage_10:

		IF NOT player_is_in_crane = 2 
			IF player_on_foot = 0
				CLEAR_HELP
				player_on_foot = 1
			ENDIF
		ELSE
			IF player_on_foot = 1
				PRINT_HELP_FOREVER ST4_H09 // Press the ~t~ button to exit the crane.
				player_on_foot = 0
			ENDIF
		ENDIF

		IF m_goals = -1
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 steal_car_blip
		ENDIF

		
		IF m_goals = 0
			PRINT_NOW ST4_26 5000 1 // take cesar and the car back to your garage
			REMOVE_BLIP location_blip
			ADD_BLIP_FOR_COORD -2024.7 179.4 27.4 location_blip

			IF DOES_BLIP_EXIST extra_baddie_blip[0]
				REMOVE_BLIP extra_baddie_blip[0]
			ENDIF
			IF DOES_BLIP_EXIST extra_baddie_blip[1]
				REMOVE_BLIP extra_baddie_blip[1]
			ENDIF
//			IF NOT IS_CAR_DEAD steal_car
//				SET_TARGET_CAR_FOR_MISSION_GARAGE hbgdsfs steal_car
//			ENDIF
			TIMERA = 20000
			TIMERB = 0
			dialogue_flag = 0
			dialogue_timer = 0

			//ALTER_WANTED_LEVEL_NO_DROP player1 2

//			// spawn a baddie car
//			CREATE_CAR SENTINEL -1699.1079 39.6859 3.5773 bad_wave2_car
//			SET_CAR_HEADING bad_wave2_car 229.0821 
//			SET_CAR_HEAVY bad_wave2_car	TRUE
//			SET_CAR_STRONG bad_wave2_car TRUE
//			SET_CAR_HEALTH bad_wave2_car 1000
//			SET_CAR_CRUISE_SPEED bad_wave2_car 50.0 
//
//			CREATE_CHAR_INSIDE_CAR bad_wave2_car PEDTYPE_MISSION1 WBDYG1 bad_wave2_ped[0]
//			CREATE_CHAR_AS_PASSENGER bad_wave2_car PEDTYPE_MISSION1 WBDYG1 0 bad_wave2_ped[1]
//			CREATE_CHAR_AS_PASSENGER bad_wave2_car PEDTYPE_MISSION1 WBDYG1 1 bad_wave2_ped[2]
//			CREATE_CHAR_AS_PASSENGER bad_wave2_car PEDTYPE_MISSION1 WBDYG1 2 bad_wave2_ped[3]
//
//			IF NOT IS_CAR_DEAD steal_car
//				TASK_CAR_MISSION bad_wave2_ped[0] bad_wave2_car steal_car MISSION_BLOCKCAR_FARAWAY 50.0 DRIVINGMODE_AVOIDCARS
//				TASK_DRIVE_BY bad_wave2_ped[1] -1 steal_car 0.0 0.0 0.0 99999.0 DRIVEBY_AI_ALL_DIRN FALSE 100
//				TASK_DRIVE_BY bad_wave2_ped[2] -1 steal_car 0.0 0.0 0.0 99999.0 DRIVEBY_AI_ALL_DIRN FALSE 100
//				TASK_DRIVE_BY bad_wave2_ped[3] -1 steal_car 0.0 0.0 0.0 99999.0 DRIVEBY_AI_ALL_DIRN FALSE 100
//			ENDIF

			SET_WANTED_MULTIPLIER 1.0

			m_goals++
		ENDIF

		IF m_goals = 1


			IF dialogue_flag = 0
				IF dialogue_timer > 5500
					active_conversation = 5
					dialogue_flag++
				ENDIF
			ELSE
				IF dialogue_flag = 1
					IF active_conversation = 0
						IF NOT IS_CAR_DEAD steal_car
							GET_CAR_HEALTH steal_car steal_car_health
						ENDIF
						dialogue_flag++
					ENDIF
				ELSE
					IF dialogue_flag = 2
						IF NOT IS_CAR_DEAD steal_car
							GET_CAR_HEALTH steal_car temp_int
							IF temp_int < steal_car_health
								dialogue_flag++
							ENDIF 
						ENDIF
					ELSE
						IF dialogue_flag = 3
							active_conversation = 7
							dialogue_flag++
						ELSE
							IF dialogue_flag = 4
								IF active_conversation = 0
									dialogue_flag++
								ENDIF		 
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF


//			// open garage door when player gets near
//			IF garage_is_open = 0
//				IF NOT IS_CAR_DEAD steal_car
//					IF IS_CHAR_IN_CAR scplayer steal_car
//						IF NOT IS_CHAR_DEAD buddy
//							IF IS_CHAR_IN_CAR buddy steal_car
//								IF LOCATE_CHAR_IN_CAR_3D scplayer -2042.9 178.5 27.5 20.0 20.0 5.0 FALSE
//									garage_is_open = 1
//									OPEN_GARAGE hbgdSFS
//								ENDIF
//							ENDIF
//						ENDIF
//					ENDIF
//				ENDIF				
//			ENDIF

			IF NOT IS_CAR_DEAD steal_car
				IF IS_CHAR_IN_CAR scplayer steal_car
					IF LOCATE_CAR_3D steal_car -2024.7 179.4 27.4 4.0 4.0 4.0 TRUE
						IF NOT IS_CHAR_DEAD buddy
							IF IS_CHAR_IN_CAR buddy steal_car
								IF IS_CHAR_IN_CAR scplayer steal_car
									IF IS_VEHICLE_ON_ALL_WHEELS steal_car
										SET_PLAYER_CONTROL player1 OFF
										m_goals++
									ENDIF
								ENDIF
							ELSE
								PRINT_NOW ST4_27 6000 1 // you left cesar behind
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF


			// remove buddy blip
			IF NOT IS_CHAR_DEAD buddy
				IF NOT IS_CAR_DEAD steal_car
					IF IS_CHAR_IN_CAR buddy steal_car
						IF DOES_BLIP_EXIST buddy_blip
							REMOVE_BLIP buddy_blip
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// if player leaves car
			IF NOT IS_CAR_DEAD steal_car
				IF NOT IS_CHAR_DEAD buddy
					IF IS_CHAR_IN_CAR buddy steal_car
						IF NOT IS_CHAR_IN_CAR scplayer steal_car
							IF NOT DOES_BLIP_EXIST steal_car_blip
								ADD_BLIP_FOR_CAR steal_car steal_car_blip
								SET_BLIP_AS_FRIENDLY steal_car_blip	TRUE
								//PRINT_NOW ST4_41 6000 1 // what are you doing?
								PRINT_NOW ST4_05 5000 1 // get in the car with cesar
							
							ELSE

							ENDIF
							IF DOES_BLIP_EXIST location_blip
								REMOVE_BLIP location_blip
							ENDIF
//							IF TIMERA > 20000
//								//PRINT_NOW ST4_26 7000 1 // take cesar and the car back to your garage
//								PRINT_NOW ST4_05 7000 1 // get in the car with cesar
//								TIMERA = 0
//							ENDIF


							// get in the car dialogue
							IF NOT IS_MESSAGE_BEING_DISPLAYED
							AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							AND audio_line_is_active = 0
							AND TIMERA > 30000
								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer buddy 20.0 20.0 5.0 FALSE
									LVAR_INT cesar_dialogue_1
									cesar_dialogue_1++
									IF cesar_dialogue_1 > 5
										cesar_dialogue_1 = 0
									ENDIF
									SWITCH cesar_dialogue_1
										CASE 0
											$audio_string    = &CESX_AA	
											audio_sound_file = SOUND_CESX_AA
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
										CASE 1
											$audio_string    = &CESX_AB	
											audio_sound_file = SOUND_CESX_AB
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
										CASE 2
											$audio_string    = &CESX_AC	
											audio_sound_file = SOUND_CESX_AC
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
										CASE 3
											$audio_string    = &CESX_AD	
											audio_sound_file = SOUND_CESX_AD
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
										CASE 4
											$audio_string    = &CESX_AE	
											audio_sound_file = SOUND_CESX_AE
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
										CASE 5
											$audio_string    = &CESX_AF	
											audio_sound_file = SOUND_CESX_AF
											START_NEW_SCRIPT audio_line buddy 0 0 1 0
										BREAK
									ENDSWITCH
									TIMERA = 0
								ENDIF								
							ENDIF

						ELSE
							IF DOES_BLIP_EXIST steal_car_blip
								REMOVE_BLIP steal_car_blip
							ENDIF	
							IF NOT DOES_BLIP_EXIST location_blip
								ADD_BLIP_FOR_COORD -2024.7 179.4 27.4 location_blip  
								PRINT_NOW ST4_26 7000 1 // take cesar and the car back to your garage
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			// remove car blip if player is in car
			IF NOT IS_CAR_DEAD steal_car
				IF IS_CHAR_IN_CAR scplayer steal_car
					IF DOES_BLIP_EXIST steal_car_blip
						REMOVE_BLIP steal_car_blip
					ENDIF
				ELSE
					IF NOT DOES_BLIP_EXIST steal_car_blip
						ADD_BLIP_FOR_CAR steal_car steal_car_blip
						SET_BLIP_AS_FRIENDLY steal_car_blip	TRUE
					ENDIF
				ENDIF
			ENDIF

			// if player leaves cesar behind
			IF NOT IS_CAR_DEAD steal_car
				IF IS_CHAR_IN_CAR scplayer steal_car
					IF NOT IS_CHAR_DEAD buddy
						IF NOT IS_CHAR_IN_CAR buddy steal_car
							IF TIMERB > 20000
								PRINT_NOW ST4_27 7000 1 //You've left Cesar behind!!
								TIMERB = 0
							ENDIF
							IF DOES_BLIP_EXIST location_blip
								REMOVE_BLIP location_blip
							ENDIF
						ELSE
							IF NOT DOES_BLIP_EXIST location_blip
								ADD_BLIP_FOR_COORD -2024.7 179.4 27.4 location_blip
								PRINT_NOW ST4_26 7000 1 // take cesar and the car back to your garage	
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF

		IF m_goals = 2

			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			OPEN_GARAGE hbgdSFS
			WHILE NOT IS_GARAGE_OPEN hbgdSFS
				WAIT 0 	
			ENDWHILE

			

			SET_AREA_VISIBLE 1


			IF IS_PLAYER_PLAYING player1
				SET_CHAR_AREA_VISIBLE scplayer 1
			ENDIF
			

			LOAD_SCENE_IN_DIRECTION -2045.4 173.1 27.8 309.5
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_FIXED_CAMERA_POSITION -2045.2 170.8 29.4 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2040.1 181.6 29.1 JUMP_CUT


			vec_x = -2040.1 + 2045.2
			vec_y = 181.6 - 170.8
			vec_z = 29.1 - 29.4
			vec_x *= 4.0
			vec_y *= 4.0 
			vec_z *= 4.0
			x = -2045.2 + vec_x
			y = 170.8  + vec_y
			z = 29.4 + vec_z
			
			GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading

			LOAD_SCENE_IN_DIRECTION	x y z heading
			
	

			

			IF NOT IS_CHAR_DEAD buddy
				SET_CHAR_AREA_VISIBLE buddy 1
			ENDIF
			IF NOT IS_CAR_DEAD steal_car
				SET_VEHICLE_AREA_VISIBLE steal_car 1
			ENDIF
		
			IF NOT IS_CAR_DEAD steal_car
				IF NOT IS_CHAR_IN_CAR scplayer steal_car
					WARP_CHAR_INTO_CAR scplayer steal_car
				ENDIF 
			ENDIF

			IF NOT IS_CAR_DEAD steal_car
				SET_CAR_COORDINATES steal_car -2033.8 178.6 27.6
				SET_CAR_HEADING steal_car 90.0
			ENDIF
			
			//SET_CAR_FORWARD_SPEED st2_players_car 
			OPEN_SEQUENCE_TASK temp_seq
				TASK_CAR_DRIVE_TO_COORD -1 steal_car -2044.4 177.6 27.6 5.0 MODE_ACCURATE TRUE DRIVINGMODE_AVOIDCARS
				TASK_LEAVE_ANY_CAR -1 
				TASK_GO_STRAIGHT_TO_COORD -1 -2034.7308 177.1182 27.8359 PEDMOVE_WALK 50000				
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK scplayer temp_seq	
			CLEAR_SEQUENCE_TASK temp_seq


			//WAIT 500

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE	

			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_STL4_NA
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE
				PLAY_MISSION_AUDIO 1
				PRINT_NOW &STL4_NA 5000 1
			ELSE
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_STL4_NB
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE
				PLAY_MISSION_AUDIO 1
				PRINT_NOW &STL4_NB 5000 1
			ENDIF

			TIMERA = 0

			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				IF NOT IS_CHAR_DEAD buddy
					IF NOT IS_CAR_DEAD steal_car
						IF IS_CHAR_IN_CAR buddy steal_car
							IF NOT IS_CHAR_IN_CAR scplayer steal_Car
								GET_SCRIPT_TASK_STATUS buddy PERFORM_SEQUENCE_TASK temp_int
								IF temp_int = FINISHED_TASK
									OPEN_SEQUENCE_TASK temp_seq
										TASK_LEAVE_ANY_CAR -1 
										TASK_GO_STRAIGHT_TO_COORD -1 -2034.7308 177.1182 27.8359 PEDMOVE_WALK 50000				
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK buddy temp_seq	
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				WAIT 0 
			ENDWHILE 

			WHILE NOT TIMERA > 5000
				WAIT 0
			ENDWHILE

			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_AREA_VISIBLE 0

			IF IS_PLAYER_PLAYING player1
				SET_CHAR_AREA_VISIBLE scplayer 0
			ENDIF

			m_goals++
		ENDIF


		// show the import export being unlocked
		IF m_goals = 3

			// work out how much to award player
			IF NOT IS_CAR_DEAD steal_car
				GET_CAR_HEALTH steal_car temp_int
				temp_float =# temp_int
				temp_float /= 1000.0
				temp_float *= 5000.0
				steal4_reward =# temp_float 
			ENDIF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			DELETE_CHAR buddy
			DELETE_CAR steal_car
			CLEAR_PRINTS
			CLOSE_GARAGE hbgdSFS
			WHILE NOT IS_GARAGE_CLOSED hbgdSFS 
				WAIT 0 
			ENDWHILE


			SET_CHAR_COORDINATES scplayer -1569.6251 122.3311 2.5469
			SET_FIXED_CAMERA_POSITION -1571.1414 131.4032 3.3235 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 	  -1571.6854 132.2260 3.4872 JUMP_CUT
			LOAD_SCENE_IN_DIRECTION -1571.6854 132.2260 3.4872 0.0
			CLEAR_AREA -1570.7183 131.0493 3.3547 50.0 TRUE
			SWITCH_WIDESCREEN ON

			steal4_flag = 1
			START_NEW_SCRIPT import_export_script
			import_export_is_active = 1 // unlocks the import / export

			WHILE NOT HAS_MODEL_LOADED NF_BLACKBOARD
				WAIT 0
			ENDWHILE
									
			CLEAR_HELP
			DO_FADE 1500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_HELP

			PRINT_NOW ST4_45 5000 1	// ~s~Car exports and imports have been unlocked.
			WAIT 5000
			PRINT_NOW ST4_46 5000 1	// ~s~Check the wanted board at the Ocean Docks for vehicles wanted for exporting.
			WAIT 5000
					
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE	
									  
			SET_FIXED_CAMERA_POSITION -1554.5928 -60.4267 75.7247 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 	  -1554.7825 -59.6013 75.1930 JUMP_CUT
			LOAD_SCENE_IN_DIRECTION -1554.7825 -59.6013 75.1930	0.0

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE	

			PRINT_NOW ST4_47 5000 1 // Deliver wanted vehicles to the ship.
			WAIT 5000
			PRINT_NOW ST4_48 5000 1 // Vehicles are also imported on certain days.
			WAIT 5000

			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			//SET_CHAR_COORDINATES scplayer -2034.2960 177.3021 27.3359 
			//SET_CHAR_HEADING scplayer 257.4417

			SET_CHAR_COORDINATES scplayer -2027.2 179.5 27.3
			SET_CHAR_HEADING scplayer 277.5 

			LOAD_SCENE -2045.4 173.1 27.8
			LOAD_SCENE_IN_DIRECTION -2045.4 173.1 27.8 309.5 

			WHILE IS_CHAR_WAITING_FOR_WORLD_COLLISION scplayer
				WAIT 0
			ENDWHILE

			SET_CHAR_COORDINATES scplayer -2027.2 179.5 27.3
			SET_CHAR_HEADING scplayer 277.5

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_PLAYER_CONTROL player1 ON
			m_passed = 1
		ENDIF


//		// stop train once it gets into the docks
//		IF DOES_VEHICLE_EXIST train
//			IF train_is_coming = 0
//				IF NOT IS_CAR_DEAD train
//					IF LOCATE_CAR_2D train -1586.0 87.0 10.0 10.0 FALSE
//						SET_TRAIN_CRUISE_SPEED train 0.0
//						SET_TRAIN_SPEED train 10.0
//						train_is_coming++
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF

RETURN



// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
STEAL4_global_functions:

//	IF NOT IS_CAR_DEAD steal_car
//		GET_CAR_COORDINATES steal_car x y z
//		IF IS_2D_POINT_IN_TRIANGLE x y -1536.6421 134.1915 -1538.9832 136.8876 -1652.6705 22.8175
//			WRITE_DEBUG POINT_IN_TRI1
//		ELSE
//			LINE -1536.6421 134.1915 z -1538.9832 136.8876 z
//			LINE -1538.9832 136.8876 z -1652.6705 22.8175 z
//			LINE -1652.6705 22.8175 z -1536.6421 134.1915 z
//		ENDIF
//		IF IS_2D_POINT_IN_TRIANGLE x y -1652.6705 22.8175 -1650.6829 20.2309 -1536.6421 134.1915
//			WRITE_DEBUG POINT_IN_TRI2
//		ELSE
//			LINE -1652.6705 22.8175 z -1650.6829 20.2309 z
//			LINE -1650.6829 20.2309 z -1536.6421 134.1915 z
//			LINE -1536.6421 134.1915 z -1652.6705 22.8175 z
//		ENDIF
//		temp_float = z - 2.0
//		temp_float2	= z + 2.0
//		LINE x y temp_float x y temp_float2
//	ENDIF

	// get last carried container
	IF DOES_OBJECT_EXIST magno_arm
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
		IF NOT temp_obj = -1
			IF NOT last_picked_up_container = temp_obj
				last_picked_up_container = temp_obj
			ENDIF
		ENDIF
	ENDIF

	// check cesar hasn't been hit
	IF NOT IS_CHAR_DEAD buddy
		IF DOES_OBJECT_EXIST last_picked_up_container
			IF NOT IS_OBJECT_STATIC last_picked_up_container
				GET_OBJECT_SPEED last_picked_up_container temp_float
				IF temp_float > 4.0
					IF IS_CHAR_TOUCHING_OBJECT_ON_FOOT buddy last_picked_up_container
						GET_SCRIPT_TASK_STATUS buddy TASK_DIE temp_int3
						IF temp_int3 = FINISHED_TASK 
							TASK_DIE buddy
						ENDIF
					ENDIF 
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	LVAR_FLOAT car_throw_vec_x car_throw_vec_y car_throw_vec_z
	IF m_frame_num = 8
		IF neils_car_is_attached = 1
			IF DOES_OBJECT_EXIST open_container1
				IF DOES_VEHICLE_EXIST open_container1_car
					IF NOT IS_CAR_DEAD open_container1_car
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS open_container1 0.0 -1.0 0.0 x y z
						GET_OBJECT_COORDINATES open_container1 x2 y2 z2
						vec_x = 1.0
						vec_y = z - z2
						vec2_x = 0.0
						vec2_y = 1.0
						GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y vec2_x vec2_y temp_float
						//WRITE_DEBUG_WITH_FLOAT angle temp_float
						IF z < z2
						AND temp_float > 120.0
							GET_CAR_COORDINATES open_container1_car x y z
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS open_container1_car 0.0 1.0 0.0 x2 y2 z2
							car_throw_vec_x = x2 - x
							car_throw_vec_y = y2 - y
							car_throw_vec_z = z2 - z
							car_throw_vec_z += 0.1
							car_throw_vec_x *= 0.05
							car_throw_vec_y *= 0.05
							car_throw_vec_z *= 0.05
							DETACH_CAR open_container1_car 0.0 0.0 0.0 FALSE
							//APPLY_FORCE_TO_CAR open_container1_car vec_x vec_y vec_z 0.0 0.0 0.0
							//FREEZE_CAR_POSITION
							move_car_timer = 0
							neils_car_is_attached++   
						ENDIF
 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF neils_car_is_attached = 2
		IF move_car_timer < 300
			IF NOT IS_CAR_DEAD open_container1_car
				APPLY_FORCE_TO_CAR open_container1_car car_throw_vec_x car_throw_vec_y car_throw_vec_z 0.0 0.0 0.0
			ENDIF
		ELSE
			neils_car_is_attached++
		ENDIF
	ENDIF
	IF m_frame_num = 9
		IF neils_car_is_attached2 = 1
			IF DOES_OBJECT_EXIST open_container2
				IF DOES_VEHICLE_EXIST open_container2_car
					IF NOT IS_CAR_DEAD open_container2_car
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS open_container2 0.0 -1.0 0.0 x y z
						GET_OBJECT_COORDINATES open_container2 x2 y2 z2
						vec_x = 1.0
						vec_y = z - z2
						vec2_x = 0.0
						vec2_y = 1.0
						GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y vec2_x vec2_y temp_float
						IF z < z2
						AND temp_float > 120.0
							GET_CAR_COORDINATES open_container2_car x y z
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS open_container2_car 0.0 1.0 0.0 x2 y2 z2
							car_throw_vec_x = x2 - x
							car_throw_vec_y = y2 - y
							car_throw_vec_z = z2 - z
							car_throw_vec_z += 0.1
							car_throw_vec_x *= 0.05
							car_throw_vec_y *= 0.05
							car_throw_vec_z *= 0.05
							DETACH_CAR open_container2_car 0.0 0.0 0.0 FALSE
							move_car_timer = 0
							neils_car_is_attached2++   
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF neils_car_is_attached = 2
		IF move_car_timer < 300
			IF NOT IS_CAR_DEAD open_container2_car
				APPLY_FORCE_TO_CAR open_container2_car car_throw_vec_x car_throw_vec_y car_throw_vec_z 0.0 0.0 0.0
			ENDIF
		ELSE
			neils_car_is_attached2++
		ENDIF
	ENDIF


	// player drops a container on any of the cars it should explode
	//IF m_frame_num = 3

	IF DOES_OBJECT_EXIST magno_arm
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
		IF temp_obj = -1

//		temp_int = 0
//		WHILE temp_int < 9
			LVAR_FLOAT last_frame_container_speed
			IF DOES_OBJECT_EXIST last_picked_up_container
				//IF NOT IS_OBJECT_STATIC last_picked_up_container
					
					
					IF last_frame_container_speed > 15.0
//						GET_OBJECT_COORDINATES last_picked_up_container x y z
//						z += -2.0
						
						IF NOT IS_CAR_DEAD buddy_car
							//IF LOCATE_CAR_3D buddy_car x y z 4.0 4.0 2.0 FALSE
							IF IS_VEHICLE_TOUCHING_OBJECT buddy_car last_picked_up_container
								EXPLODE_CAR buddy_car
							ENDIF	
						ENDIF
					
						IF NOT IS_CAR_DEAD open_container1_car
							IF NOT IS_VEHICLE_ATTACHED open_container1_car
								//IF LOCATE_CAR_3D open_container1_car x y z 4.0 4.0 2.0 FALSE
								IF IS_VEHICLE_TOUCHING_OBJECT open_container1_car last_picked_up_container
									EXPLODE_CAR open_container1_car
								ENDIF
							ENDIF	
						ENDIF

						IF NOT IS_CAR_DEAD steal_car
							//IF LOCATE_CAR_3D steal_car x y z 4.0 4.0 2.0 FALSE
							IF IS_VEHICLE_TOUCHING_OBJECT steal_car last_picked_up_container
								EXPLODE_CAR steal_car
							ENDIF	
						ENDIF

						IF NOT IS_CAR_DEAD bad_wave1_car
							//IF LOCATE_CAR_3D bad_wave1_car x y z 4.0 4.0 2.0 FALSE
							IF IS_VEHICLE_TOUCHING_OBJECT bad_wave1_car last_picked_up_container
								EXPLODE_CAR bad_wave1_car
							ENDIF	
						ENDIF

						IF NOT IS_CAR_DEAD bad_wave1_car2
							//IF LOCATE_CAR_3D bad_wave1_car2 x y z 4.0 4.0 2.0 FALSE
							IF IS_VEHICLE_TOUCHING_OBJECT bad_wave1_car2 last_picked_up_container
								EXPLODE_CAR bad_wave1_car2
							ENDIF	
						ENDIF

						IF NOT IS_CAR_DEAD bad_wave2_car
							//IF LOCATE_CAR_3D bad_wave2_car x y z 4.0 4.0 2.0 FALSE
							IF IS_VEHICLE_TOUCHING_OBJECT bad_wave2_car last_picked_up_container
								EXPLODE_CAR bad_wave2_car
							ENDIF	
						ENDIF

					ENDIF	 
					
					GET_OBJECT_SPEED last_picked_up_container last_frame_container_speed
				//ENDIF
			ENDIF
		ENDIF
//		temp_int++
//		ENDWHILE 
   	ENDIF

	// store buddy car
	IF m_frame_num = 4
		IF IS_CHAR_IN_ANY_CAR scplayer
			IF NOT IS_CAR_DEAD buddy_car
				IF NOT IS_CHAR_IN_CAR scplayer buddy_car
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer temp_int
					IF NOT temp_int = steal_car
						MARK_CAR_AS_NO_LONGER_NEEDED buddy_car
						STORE_CAR_CHAR_IS_IN scplayer buddy_car
					ENDIF
				ENDIF
			ELSE
				MARK_CAR_AS_NO_LONGER_NEEDED buddy_car
				STORE_CAR_CHAR_IS_IN scplayer buddy_car
			ENDIF
		ENDIF
	ENDIF

	// buddy health
	IF m_frame_num = 5
		IF DOES_CHAR_EXIST buddy
			IF NOT IS_CHAR_DEAD buddy
				GET_CHAR_HEALTH buddy temp_int
				temp_float =# temp_int
				temp_float /= 500.0
				temp_float *= 100.0
				IF temp_float > 100.0 
					temp_float = 100.0
				ENDIF
				buddy_health =# temp_float 
			ENDIF
		ENDIF
	ENDIF


	// remove blips
	IF m_frame_num = 6
		IF DOES_VEHICLE_EXIST bad_wave2_car
			IF IS_CAR_DEAD bad_wave2_car
				IF DOES_BLIP_EXIST bad_wave2_car_blip
					REMOVE_BLIP bad_wave2_car_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// remove baddie blips if the are dead
	IF m_frame_num = 8
		temp_int = 0
		WHILE temp_int < 6
			IF DOES_BLIP_EXIST bad_wave1_ped_blip[temp_int]
				IF IS_CHAR_DEAD bad_wave1_ped[temp_int]
					REMOVE_BLIP bad_wave1_ped_blip[temp_int]
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		IF DOES_BLIP_EXIST extra_baddie_blip[0]
			IF IS_CHAR_DEAD extra_baddie[0]
				REMOVE_BLIP extra_baddie_blip[0]
			ENDIF
		ENDIF

		IF DOES_BLIP_EXIST extra_baddie_blip[1]
			IF IS_CHAR_DEAD extra_baddie[1]
				REMOVE_BLIP extra_baddie_blip[1]
			ENDIF
		ENDIF
	ENDIF

	// enable crane drop if they pick up a car
	IF disable_crane_drop = 1
		IF DOES_OBJECT_EXIST magno_arm
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
			IF NOT temp_car = -1
				ENABLE_CRANE_CONTROLS TRUE TRUE TRUE
				disable_crane_drop = 2
			ENDIF
		ENDIF
	ENDIF
	IF disable_crane_drop = 2
		IF DOES_OBJECT_EXIST magno_arm
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm temp_car temp_ped temp_obj
			IF temp_car = -1
				ENABLE_CRANE_CONTROLS TRUE TRUE FALSE
				disable_crane_drop = 1
			ENDIF
		ENDIF
	ENDIF



	// fail if steal car gets destroyed
	IF m_frame_num = 7
		IF DOES_VEHICLE_EXIST steal_car
			IF IS_CAR_DEAD steal_car
				WAIT 2000
				m_failed = 1
				failed_condition = 2
			ENDIF
		ENDIF

		// fail if buddy dies
		IF DOES_CHAR_EXIST buddy
			IF IS_CHAR_DEAD buddy
				m_failed = 1   
				failed_condition = 3
			ENDIF
		ENDIF
	ENDIF



RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************
steal4_buddy_group_manager:

	IF joined_group = 1
		IF NOT IS_CHAR_DEAD buddy
			IF NOT IS_GROUP_MEMBER buddy players_group
				IF NOT player_is_in_crane = 2
				 	PRINT_NOW ST4_42 5000 1 // you've left cesar behind 
				ENDIF
				joined_group++
				IF NOT buddy_blip_type = 1
					buddy_blip_type	= 1
					GOSUB reset_buddy_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF joined_group = 2
		IF NOT IS_CHAR_DEAD buddy
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer buddy 15.0 15.0 5.0 FALSE
				SET_GROUP_MEMBER players_group buddy
				SET_CHAR_DROWNS_IN_WATER buddy FALSE
				joined_group = 1
				IF NOT buddy_blip_type = 3
					buddy_blip_type	= 3
					GOSUB reset_buddy_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN

st4_create_baddies1:
	REQUEST_MODEL SENTINEL
	REQUEST_MODEL COLT45
	REQUEST_MODEL WBDYG1
	WHILE NOT HAS_MODEL_LOADED JESTER
	   OR NOT HAS_MODEL_LOADED SENTINEL
	   OR NOT HAS_MODEL_LOADED COLT45
	   OR NOT HAS_MODEL_LOADED WBDYG1
		WAIT 0
	ENDWHILE
	// create baddies
	CLEAR_AREA -1711.5900 44.8495 2.7813 20.0 TRUE
	CREATE_CAR SENTINEL -1711.5900 44.8495 2.7813 bad_wave1_car
	SET_CAR_HEADING bad_wave1_car 232.9281
	SET_CAR_STRAIGHT_LINE_DISTANCE bad_wave1_car 100
	CREATE_CHAR_INSIDE_CAR bad_wave1_car PEDTYPE_MISSION1 WBDYG1 bad_wave1_ped[0]
	CREATE_CHAR_AS_PASSENGER bad_wave1_car PEDTYPE_MISSION1 WBDYG1 0 bad_wave1_ped[1]
	CREATE_CHAR_AS_PASSENGER bad_wave1_car PEDTYPE_MISSION1 WBDYG1 1 bad_wave1_ped[2]
	ADD_BLIP_FOR_CHAR bad_wave1_ped[0] bad_wave1_ped_blip[0]
	ADD_BLIP_FOR_CHAR bad_wave1_ped[1] bad_wave1_ped_blip[1]
	ADD_BLIP_FOR_CHAR bad_wave1_ped[2] bad_wave1_ped_blip[2]
	temp_int = 0
	WHILE temp_int < 3
		IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
			GIVE_WEAPON_TO_CHAR bad_wave1_ped[temp_int] WEAPONTYPE_PISTOL 9999
			SET_CHAR_DECISION_MAKER bad_wave1_ped[temp_int] tough_dm
			SET_CHAR_ACCURACY bad_wave1_ped[temp_int] 40
			SET_CHAR_HEALTH bad_wave1_ped[temp_int] 70
			SET_CHAR_SUFFERS_CRITICAL_HITS bad_wave1_ped[temp_int] FALSE
		ENDIF
	temp_int++
	ENDWHILE
	CLEAR_AREA -1697.0397 14.7378 2.7813 5.0 TRUE

RETURN

st4_start_baddies_driving:

	// start baddie car driving
	IF NOT IS_CAR_DEAD bad_wave1_car
		OPEN_SEQUENCE_TASK temp_seq	   
			TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car -1686.4221 25.8359 2.7752 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			TASK_CAR_DRIVE_TO_COORD -1 bad_wave1_car -1643.3943 19.6494 3.6706 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
		CLOSE_SEQUENCE_TASK temp_seq
		IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
			PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
		ENDIF
		CLEAR_SEQUENCE_TASK temp_seq
	ENDIF
	
RETURN

st4_reset_crane_position:

	// base	
	IF DOES_OBJECT_EXIST magno_base
		SET_OBJECT_COORDINATES_AND_VELOCITY magno_base magno_base_x magno_base_y magno_base_z
	ENDIF 
	IF DOES_OBJECT_EXIST magno_base_LOD
		SET_OBJECT_COORDINATES_AND_VELOCITY magno_base_LOD magno_base_x magno_base_y magno_base_z
	ENDIF

	// magno_cabin												   
	IF DOES_OBJECT_EXIST magno_base								   
		IF DOES_OBJECT_EXIST magno_cabin							   
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base 0.0. 0.0 0.0 x y z
			SET_OBJECT_HEADING magno_cabin magno_cabin_h
			SET_OBJECT_COORDINATES_AND_VELOCITY magno_cabin x y z
			IF DOES_OBJECT_EXIST magno_cabin_LOD
				SET_OBJECT_HEADING magno_cabin_LOD magno_cabin_h
				SET_OBJECT_COORDINATES_AND_VELOCITY magno_cabin_LOD x y z
			ENDIF
		ENDIF
	ENDIF
														  
	// arm												  
	IF DOES_OBJECT_EXIST magno_arm							  
		IF DOES_OBJECT_EXIST magno_cabin
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin 0.0 -2.185 8.51 x y z
			GET_OBJECT_HEADING magno_cabin magno_arm_h
			//magno_arm_h += -45.0
			SET_OBJECT_COORDINATES_AND_VELOCITY magno_arm x y z
			SET_OBJECT_ROTATION magno_arm magno_arm_rotate_y 0.0 magno_arm_h
			IF DOES_OBJECT_EXIST magno_arm_LOD
				SET_OBJECT_COORDINATES_AND_VELOCITY magno_arm_LOD x y z
				SET_OBJECT_ROTATION magno_arm_LOD magno_arm_rotate_y 0.0 magno_arm_h
			ENDIF
		ENDIF
	ENDIF

	// rope
	IF DOES_OBJECT_EXIST magno_arm
		SET_ROPE_HEIGHT_FOR_OBJECT magno_arm crane_rope_length
		GET_ROPE_HEIGHT_FOR_OBJECT magno_arm crane_rope_length
	ENDIF

	RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
	IF DOES_OBJECT_EXIST magno_arm
		SET_ROPE_HEIGHT_FOR_OBJECT magno_arm crane_rope_length
	ENDIF


RETURN

st4_set_baddies_end_position:

	IF NOT IS_CHAR_DEAD bad_wave1_ped[0]
		CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[0]
		SET_CHAR_COORDINATES bad_wave1_ped[0] -1638.9423 26.9517 3.0869
		SET_CHAR_HEADING bad_wave1_ped[0] 339.6872
		OPEN_SEQUENCE_TASK temp_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -1638.8365 27.1035 2.8213 PEDMOVE_RUN 999999
			TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
			IF NOT IS_CHAR_DEAD buddy
				TASK_KILL_CHAR_ON_FOOT -1 buddy
			ENDIF
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK bad_wave1_ped[0] temp_seq
		CLEAR_SEQUENCE_TASK temp_seq
	ENDIF

	IF NOT IS_CHAR_DEAD bad_wave1_ped[1]
		CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[1]
		SET_CHAR_COORDINATES bad_wave1_ped[1] -1639.8961 21.6694 3.0869
		SET_CHAR_HEADING bad_wave1_ped[1] 359.1165
		OPEN_SEQUENCE_TASK temp_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -1639.8961 21.6694 3.5869 PEDMOVE_RUN 999999
			TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
			IF NOT IS_CHAR_DEAD buddy
				TASK_KILL_CHAR_ON_FOOT -1 buddy
			ENDIF
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK bad_wave1_ped[1] temp_seq
		CLEAR_SEQUENCE_TASK temp_seq
	ENDIF

	IF NOT IS_CHAR_DEAD bad_wave1_ped[2]
		CLEAR_CHAR_TASKS_IMMEDIATELY bad_wave1_ped[2]
		SET_CHAR_COORDINATES bad_wave1_ped[2] -1641.3461 27.0480 3.0869
		SET_CHAR_HEADING bad_wave1_ped[2] 331.6584
		OPEN_SEQUENCE_TASK temp_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -1641.3461 27.0480 3.5869 PEDMOVE_RUN 999999
			TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
			IF NOT IS_CHAR_DEAD buddy
				TASK_KILL_CHAR_ON_FOOT -1 buddy
			ENDIF
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK bad_wave1_ped[2] temp_seq
		CLEAR_SEQUENCE_TASK temp_seq
	ENDIF

	IF NOT IS_CAR_DEAD bad_wave1_car
		SET_CAR_COORDINATES bad_wave1_car -1642.7059 19.5606 3.4189
		SET_CAR_HEADING bad_wave1_car 263.1502
	ENDIF

	temp_int = 0
	WHILE temp_int < 3
		IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
			SET_CHAR_DECISION_MAKER bad_wave1_ped[temp_int] tough_dm
		ENDIF
	temp_int++
	ENDWHILE

RETURN

wrong_container_dialogue:
	
	SWITCH wrong_container_dialogue_flag

		CASE 0
			PRINT_NOW ST4_11 6000 1 // CESAR - 'Are you colour blind? I said the BLUE container, get this out of the way!'
			wrong_container_dialogue_flag++
		BREAK

		CASE 1
			PRINT_NOW ST4_12 6000 1 // CESAR - 'This is the wrong coloured container, get rid of this and try again!'
			wrong_container_dialogue_flag++
		BREAK
		
		DEFAULT

	ENDSWITCH

	IF wrong_container_dialogue_flag > 1
		wrong_container_dialogue_flag = 0
	ENDIF

	wrong_container_timer = 0

RETURN

reset_buddy_blip:

	IF DOES_CHAR_EXIST buddy
		IF NOT IS_CHAR_DEAD buddy
			
			IF DOES_BLIP_EXIST buddy_blip
				REMOVE_BLIP buddy_blip
			ENDIF
			
			IF buddy_blip_type = 1
				ADD_BLIP_FOR_CHAR buddy buddy_blip
				SET_BLIP_AS_FRIENDLY buddy_blip TRUE	
			ENDIF

			IF buddy_blip_type = 2
				ADD_BLIP_FOR_CHAR buddy buddy_blip
				SET_BLIP_AS_FRIENDLY buddy_blip TRUE  
				CHANGE_BLIP_DISPLAY buddy_blip BLIP_ONLY
			ENDIF

			IF buddy_blip_type = 3
				ADD_BLIP_FOR_CHAR buddy buddy_blip
				SET_BLIP_AS_FRIENDLY buddy_blip TRUE  
				CHANGE_BLIP_DISPLAY buddy_blip NEITHER
			ENDIF
			
		ENDIF
	ENDIF

RETURN


   
   
   
// *************************************************************************************************************
// 										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_STEAL4:
	
	CLEAR_PRINTS

	IF player_is_in_crane = 2
		SET_PLAYER_CONTROL player1 OFF
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS 
			WAIT 0
		ENDWHILE
			CLEAR_ONSCREEN_COUNTER buddy_health
			remove_player_from_crane = 1

		SWITCH failed_condition
			CASE 1
				IF NOT IS_CAR_DEAD buddy_car
					SET_CAR_TRACTION buddy_car 2.0
					IF NOT IS_CHAR_DEAD buddy
						WARP_CHAR_INTO_CAR buddy buddy_car
					ENDIF
					GET_CAR_COORDINATES buddy_car x y z
					GET_CLOSEST_CAR_NODE x y z x y z
					SET_CAR_COORDINATES buddy_car x y z
					SET_CAR_HEADING buddy_car 130.0
					TASK_CAR_DRIVE_WANDER buddy buddy_car 40.0 DRIVINGMODE_AVOIDCARS
				ENDIF 				
			BREAK
			CASE 2
				IF NOT IS_CAR_DEAD buddy_car
					SET_CAR_TRACTION buddy_car 2.0
					IF NOT IS_CHAR_DEAD buddy
						WARP_CHAR_INTO_CAR buddy buddy_car
					ENDIF
					GET_CAR_COORDINATES buddy_car x y z
					GET_CLOSEST_CAR_NODE x y z x y z
					SET_CAR_COORDINATES buddy_car x y z
					SET_CAR_HEADING buddy_car 130.0
					TASK_CAR_DRIVE_WANDER buddy buddy_car 40.0 DRIVINGMODE_AVOIDCARS
				ENDIF
			BREAK
			CASE 3
				temp_int = 0
				WHILE temp_int < 6
					IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int]
						TASK_KILL_CHAR_ON_FOOT bad_wave1_ped[temp_int] scplayer
					ENDIF
				temp_int++
				ENDWHILE
				IF NOT IS_CHAR_DEAD extra_baddie[0]
					TASK_KILL_CHAR_ON_FOOT extra_baddie[0] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD extra_baddie[1]
					TASK_KILL_CHAR_ON_FOOT extra_baddie[1] scplayer
				ENDIF
			BREAK
		ENDSWITCH

		IF DOES_VEHICLE_EXIST steal_car
			DELETE_CAR steal_car
		ENDIF

		IF NOT IS_CAR_DEAD open_container1_car
			DELETE_CAR open_container1_car
		ENDIF

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		st4_mission_attempts++
		SET_PLAYER_CONTROL player1 ON
	ENDIF

	SWITCH failed_condition
		CASE 1
			PRINT_NOW ST4_44 5000 1 // the car inside the container got destroyed
		BREAK
		CASE 2
			PRINT_NOW ST4_03 5000 1 // the steal car was destroyed
		BREAK
		CASE 3
			PRINT_NOW ST4_02 5000 1 // cesar died
		BREAK
	ENDSWITCH

	PRINT_BIG M_FAIL 5000 1
	
	disable_crane = 1

	//WAIT 5000

RETURN

// PASS
mission_passed_STEAL4:

	CLEAR_PRINTS

	SET_AREA_VISIBLE 0
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) steal4_reward 5000 1 //"Mission Passed!"
	ADD_SCORE player1 steal4_reward
	AWARD_PLAYER_MISSION_RESPECT 5
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1

	flag_steal_mission_counter++
	PLAYER_MADE_PROGRESS 1
	
	disable_crane = 0			// allows the crane to be used whenever
	SWITCH_CAR_GENERATOR steal_car_cargen4 101
		
	REGISTER_MISSION_PASSED STEAL_4
RETURN

// CLEANUP
mission_cleanup_STEAL4:	 

	IF IS_PLAYER_PLAYING player1
		SET_CHAR_AREA_VISIBLE scplayer 0
	ENDIF

	SET_AREA_VISIBLE 0
	START_NEW_SCRIPT cleanup_audio_lines
	//CLEAR_PRINTS
	CLEAR_HELP

	DISPLAY_HUD TRUE
	DISPLAY_RADAR TRUE

	steal4_flag = 0

	player_is_in_crane = 0
	reset_crane_camera = 0
	//disable_crane = 0 // for now
	disable_crane_exit = 0
	finish_crane_loading_script = 0
	disable_crane_slide = 0
	CLEAR_ONSCREEN_COUNTER buddy_health

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 FALSE	
		IF NOT IS_CHAR_DEAD extra_baddie[0]
			TASK_KILL_CHAR_ON_FOOT extra_baddie[0] scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD extra_baddie[1]
			TASK_KILL_CHAR_ON_FOOT extra_baddie[1] scplayer
		ENDIF
	ENDIF
	
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99


	// peds
	MARK_CHAR_AS_NO_LONGER_NEEDED buddy
	MARK_CHAR_AS_NO_LONGER_NEEDED temp_ped
	temp_int = 0 
	WHILE temp_int < 6
		IF IS_PLAYER_PLAYING player1
			IF NOT IS_CHAR_DEAD bad_wave1_ped[temp_int] 
				TASK_KILL_CHAR_ON_FOOT bad_wave1_ped[temp_int] scplayer
			ENDIF
		ENDIF
		MARK_CHAR_AS_NO_LONGER_NEEDED bad_wave1_ped[temp_int]
		temp_int ++
	ENDWHILE
	MARK_CHAR_AS_NO_LONGER_NEEDED extra_baddie[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED extra_baddie[1]


	// vehicles
	MARK_CAR_AS_NO_LONGER_NEEDED buddy_car
	MARK_CAR_AS_NO_LONGER_NEEDED temp_car
	MARK_CAR_AS_NO_LONGER_NEEDED open_container1_car
    MARK_CAR_AS_NO_LONGER_NEEDED steal_car
	MARK_CAR_AS_NO_LONGER_NEEDED bad_wave1_car
	MARK_CAR_AS_NO_LONGER_NEEDED bad_wave1_car2
	MARK_CAR_AS_NO_LONGER_NEEDED bad_wave2_car
//	MARK_CAR_AS_NO_LONGER_NEEDED train

	//KILL_FX_SYSTEM particle_fx1
	//KILL_FX_SYSTEM particle_fx2

	SWITCH_RANDOM_TRAINS ON

	UNLOAD_SPECIAL_CHARACTER 1
	MARK_MODEL_AS_NO_LONGER_NEEDED CLUB
	MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
	MARK_MODEL_AS_NO_LONGER_NEEDED JESTER
	MARK_MODEL_AS_NO_LONGER_NEEDED SOLAIR
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT		
	MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG1
	MARK_MODEL_AS_NO_LONGER_NEEDED PEREN

	REMOVE_BLIP buddy_Blip
	REMOVE_BLIP bad_wave2_car_blip
	REMOVE_BLIP location_blip
	temp_int = 0
	WHILE temp_int < 6
		REMOVE_BLIP bad_wave1_ped_blip[temp_int]
	temp_int++
	ENDWHILE
	REMOVE_BLIP steal_car_blip
	REMOVE_BLIP container_blip[0]
	REMOVE_BLIP container_blip[1]
	REMOVE_BLIP extra_baddie_blip[0]
	REMOVE_BLIP extra_baddie_blip[1]

	REMOVE_DECISION_MAKER buddy_dm
	//REMOVE_DECISION_MAKER baddie_dm
	//REMOVE_DECISION_MAKER bad_wave2_dm
	REMOVE_DECISION_MAKER tough_dm
	REMOVE_DECISION_MAKER empty_dm


	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0

	//SWITCH_CAR_GENERATOR gen_car11 101
	
	GET_GAME_TIMER timer_mobile_start
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN


// **********************************************************************************************************
//											DIALOGUE STUFF 
// **********************************************************************************************************

	

//   STL4_EA   OK, CJ, this is the one NICE WORK!
//   STL4_FA   NOT THIS ONE, CJ!
//   STL4_FB   NO LUCK, CJ, TRY ANOTHER!
//   STL4_FC   NOT THIS ONE EITHER, IT MUST BE THE LAST ONE!
//   STL4_GA   HEY! What the fuck do you think you're doing?
//   STL4_GB   HEY, hold it right there!
//   STL4_GC   Stay right there, you thieving bastard!		
//   STL4_HA   CJ, I could do with some help!
//   STL4_HB   Give me a hand with these guys, CJ!
//   STL4_HC   could do with a little help, CJ!	
//   STL4_JA   Let's just grab the car and go!
//   STL4_JB   Let's just get the car and bug out!
//   STL4_KA   CJ, that train's gonna hit the car!
//   STL4_KB   CJ, The car's on the tracks!

//   STL4_NA   Piece of cake!
//   STL4_NB   Walk in the park!


STEAL4_process_conversations:

	SWITCH active_conversation
		CASE 1
			GOSUB STEAL4_process_conversation_1
		BREAK
		CASE 2
			GOSUB STEAL4_process_conversation_2
		BREAK
		CASE 3
			GOSUB STEAL4_process_conversation_3
		BREAK
		CASE 4
			GOSUB STEAL4_process_conversation_4
		BREAK
		CASE 5
			GOSUB STEAL4_process_conversation_5
		BREAK
		CASE 6
			GOSUB STEAL4_process_conversation_6
		BREAK
		CASE 7
			GOSUB STEAL4_process_conversation_7
		BREAK
		DEFAULT
			// do fuck all - resets the conversation_status if it needs to.
			IF NOT conversation_status = CONVERSATION_INITIALISE
				conversation_status = CONVERSATION_INITIALISE
			ENDIF
		BREAK
	ENDSWITCH

RETURN


STEAL4_process_conversation_1:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			IF NOT IS_CHAR_DEAD buddy
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer buddy 15.0 15.0 FALSE
					conversation_status = CONVERSATION_PAUSED
				ENDIF	
			ENDIF
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
			// check if we should unpause
			IF NOT IS_CHAR_DEAD buddy
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer buddy	10.0 10.0 FALSE
					conversation_status = CONVERSATION_PLAYING	
				ENDIF
			ENDIF
			//check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_AA		   // C'mon, esse, you can drive!
			play_audio_sound = SOUND_STL4_AA
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_AB		   // Don't I always!
			play_audio_sound = SOUND_STL4_AB
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK

		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN

STEAL4_process_conversation_2:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
//			IF NOT IS_CHAR_IN_ANY_CAR scplayer
//				conversation_status = CONVERSATION_PAUSED
//			ELSE
//				IF NOT IS_CHAR_DEAD buddy
//					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
//					IF NOT IS_CHAR_IN_CAR buddy car
//						conversation_status = CONVERSATION_PAUSED
//					ENDIF
//				ENDIF
//			ENDIF
			IF NOT IS_CHAR_DEAD buddy
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D buddy scplayer 5.0 5.0 5.0 FALSE
					conversation_status = CONVERSATION_PAUSED	
				ENDIF
			ENDIF
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
			// check if we should unpause
//			IF IS_CHAR_IN_ANY_CAR scplayer
//				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
//				IF NOT IS_CHAR_DEAD buddy
//					IF IS_CHAR_IN_CAR buddy car
//						conversation_status = CONVERSATION_PLAYING	
//					ENDIF
//				ENDIF
//			ENDIF
			IF NOT IS_CHAR_DEAD buddy
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D buddy scplayer 5.0 5.0 5.0 FALSE
					conversation_status = CONVERSATION_PLAYING	
				ENDIF
			ENDIF
			//check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_BA		   // How we going to steal a car off a container ship, holmes?
			play_audio_sound = SOUND_STL4_BA
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_BB		   // Same way they got in on use a dockside crane!
			play_audio_sound = SOUND_STL4_BB
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 2
			$play_audio_text = &STL4_BC		   // Oh man, those things are real high up I ain't to good with ladders!
			play_audio_sound = SOUND_STL4_BC
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 3
			$play_audio_text = &STL4_BD		   // Ok, I'll use the crane, you be ready to crack the container.
			play_audio_sound = SOUND_STL4_BD
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK

		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN

STEAL4_process_conversation_3:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_CA		   // This is the ship, CJ!
			play_audio_sound = SOUND_STL4_CA
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_CB		   // I'll use that crane give me a second.
			play_audio_sound = SOUND_STL4_CB
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 2
			$play_audio_text = &STL4_CC		   // Be quick, I feel a little exposed out here!
			play_audio_sound = SOUND_STL4_CC
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK

		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN



STEAL4_process_conversation_4:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_DA		   // CESAR! NONE OF THESE IS MARKED WITH SPRAY PAINT!
			play_audio_sound = SOUND_STL4_DA
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_DB		   // WE'LL JUST HAVE TO TRY ONE AT A TIME!
			play_audio_sound = SOUND_STL4_DB
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
//		CASE 2
//			$play_audio_text = &STL4_DC		   //   Terrific!
//			play_audio_sound = SOUND_STL4_DC
//			play_audio_ped = scplayer
//			play_audio_attached = 0
//			GOSUB STEAL4_play_conversation_audio
//		BREAK

		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN

STEAL4_process_conversation_5:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				conversation_status = CONVERSATION_PAUSED
			ELSE
				IF NOT IS_CHAR_DEAD buddy
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CHAR_IN_CAR buddy car
						conversation_status = CONVERSATION_PAUSED
					ENDIF
				ENDIF
			ENDIF
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
			// check if we should unpause
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF NOT IS_CHAR_DEAD buddy
					IF IS_CHAR_IN_CAR buddy car
						conversation_status = CONVERSATION_PLAYING	
					ENDIF
				ENDIF
			ENDIF
			//check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_LA		   // There must be an easier way to earn a living.
			play_audio_sound = SOUND_STL4_LA
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_LB		   // Sure, holmes, you could sit in an offfice
			play_audio_sound = SOUND_STL4_LB
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 2
			$play_audio_text = &STL4_LC		   //   and be found dead at your desk in ten years' time!
			play_audio_sound = SOUND_STL4_LC
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 3
			$play_audio_text = &STL4_LD		   //   I hear you, esse!
			play_audio_sound = SOUND_STL4_LD
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN

STEAL4_process_conversation_6:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				conversation_status = CONVERSATION_FINISHED
			ELSE
				IF NOT IS_CHAR_DEAD buddy
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CHAR_IN_CAR buddy car
						conversation_status = CONVERSATION_FINISHED
					ENDIF
				ENDIF
			ENDIF
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_LA		   // There must be an easier way to earn a living.
			play_audio_sound = SOUND_STL4_LA
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_LB		   // Sure, holmes, you could sit in an offfice
			play_audio_sound = SOUND_STL4_LB
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 2
			$play_audio_text = &STL4_LC		   //   and be found dead at your desk in ten years' time!
			play_audio_sound = SOUND_STL4_LC
			play_audio_ped = scplayer
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 3
			$play_audio_text = &STL4_LD		   //   I hear you, esse!
			play_audio_sound = SOUND_STL4_LD
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
//		CASE 4
//			$play_audio_text = &STL4_LE		   //   Let's give these cops a run for their money!
//			play_audio_sound = SOUND_STL4_LE
//			play_audio_ped = buddy
//			play_audio_attached = 0
//			GOSUB STEAL4_play_conversation_audio
//		BREAK
		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN


STEAL4_process_conversation_7:

// ********* CONTROL MECHANISM FOR CONVERSATION ****************

	SWITCH 	conversation_status

		CASE CONVERSATION_INITIALISE
			play_audio_flag = 0
			conversation_status = CONVERSATION_PLAYING
		BREAK 

		CASE CONVERSATION_PLAYING
			// check if we should pause conversation
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				conversation_status = CONVERSATION_FINISHED
			ELSE
				IF NOT IS_CHAR_DEAD buddy
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF NOT IS_CHAR_IN_CAR buddy car
						conversation_status = CONVERSATION_FINISHED
					ENDIF
				ENDIF
			ENDIF
			// check if we should finish conversation
			IF IS_CHAR_DEAD buddy
				conversation_status = CONVERSATION_FINISHED	
			ENDIF
		BREAK

		CASE CONVERSATION_PAUSED
		BREAK

		CASE CONVERSATION_FINISHED
			active_conversation = 0
		BREAK

		CASE CONVERSATION_FINISH_GRACEFULLY
			IF HAS_MISSION_AUDIO_FINISHED 1
				conversation_status = CONVERSATION_FINISHED
				active_conversation = 0
			ENDIF
		BREAK

	ENDSWITCH

// *********** PLAY THE AUDIO *********************							

	SWITCH conversation_flag

		CASE 0
			$play_audio_text = &STL4_MA		   // Hey, CJ!
			play_audio_sound = SOUND_STL4_MA
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 1
			$play_audio_text = &STL4_MB		   // We get paid on the condition of the car, holmes!
			play_audio_sound = SOUND_STL4_MB
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 2
			$play_audio_text = &STL4_MC		   //   Be more careful!
			play_audio_sound = SOUND_STL4_MC
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		CASE 3
			$play_audio_text = &STL4_MD		   //   Yo, who's driving?	
			play_audio_sound = SOUND_STL4_MD
			play_audio_ped = buddy
			play_audio_attached = 0
			GOSUB STEAL4_play_conversation_audio
		BREAK
		DEFAULT
			conversation_status = CONVERSATION_FINISHED
			GOSUB STEAL4_play_conversation_audio
		BREAK 

	ENDSWITCH

RETURN


// ************************* DONT NEED TO CHANGE ANYTHING BELOW ******************************




// QUIT CURRENT CONVERSATION GRACEFULLY
// -------------------------------------
// 	This gosub is useful if you want to finish a conversation in script manually. 
//  It will allow the current line of dialog to finish playing.
//
STEAL4_quit_current_conversation_gracefully:
	IF NOT active_conversation = 0
		conversation_status = CONVERSATION_FINISH_GRACEFULLY
	ENDIF
RETURN




// QUIT CURRENT CONVERSATION 
// --------------------------
//  This gosub will finish the current conversation immediately.
//
STEAL4_quit_current_conversation_now:
	IF NOT active_conversation = 0
		conversation_status = CONVERSATION_FINISHED
	ENDIF
RETURN		





// PLAY AUDIO GOSUB 
// --------------------------
// 	Handles all the loading/playing/unloading of an audio thingy
//
// input parameters
LVAR_TEXT_LABEL play_audio_text
LVAR_INT play_audio_sound
LVAR_INT play_audio_ped
LVAR_INT play_audio_attached
// workings
LVAR_INT play_audio_flag
STEAL4_play_conversation_audio:

	IF NOT conversation_status = CONVERSATION_FINISHED

		SWITCH play_audio_flag

			CASE -1
				CREATE_CHAR PEDTYPE_CIVMALE MALE01  0.0 0.0 0.0 play_audio_ped
			BREAK

			CASE 0
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 play_audio_sound
				IF NOT IS_CHAR_DEAD play_audio_ped
					//DISABLE_CHAR_SPEECH play_audio_ped FALSE
				ENDIF
				play_audio_flag++		
			BREAK
			CASE 1
				IF HAS_MISSION_AUDIO_LOADED 1
					PLAY_MISSION_AUDIO 1
					PRINT_NOW $play_audio_text 3000 1
					play_audio_flag++	
					
				ENDIF
			BREAK
			CASE 2
				IF HAS_MISSION_AUDIO_FINISHED 1
					play_audio_flag++
				ELSE
					IF DOES_CHAR_EXIST play_audio_ped
						IF IS_CHAR_DEAD play_audio_ped
							play_audio_flag++
						ENDIF
					ENDIF
				ENDIF
			BREAK
			CASE 3
				CLEAR_MISSION_AUDIO 1
				CLEAR_PRINTS 
				IF NOT IS_CHAR_DEAD play_audio_ped
					//ENABLE_CHAR_SPEECH play_audio_ped
				ENDIF
				play_audio_flag++
			BREAK
			CASE 4
				// make the conversation move onwards if it's not paused.
				IF NOT conversation_status = CONVERSATION_PAUSED
					conversation_flag++
					play_audio_flag = 0
				ENDIF
			BREAK

		ENDSWITCH

	ELSE
		
		IF NOT play_audio_flag = 0
			CLEAR_MISSION_AUDIO 1
		ENDIF
			
		IF NOT IS_CHAR_DEAD play_audio_ped
			//ENABLE_CHAR_SPEECH play_audio_ped
		ENDIF 

		CLEAR_THIS_PRINT $play_audio_text
		play_audio_flag = 0
		conversation_flag = 0
	ENDIF

RETURN

// PLAY SINGLE LINE AUDIO 
// --------------------------
// 	Handles all the loading/playing/unloading of an audio thingy for a single line of dialogue, not in conversation.
//
// input parameters
LVAR_TEXT_LABEL play_single_audio_text
LVAR_INT play_single_audio_sound
LVAR_INT play_single_audio_ped
LVAR_INT play_single_audio_attached
LVAR_INT play_single_audio_flag	 // when at 4 dialog has finished
STEAL4_play_single_audio:

	SWITCH play_single_audio_flag

		CASE -1
			CREATE_CHAR PEDTYPE_CIVMALE MALE01  0.0 0.0 0.0 play_single_audio_ped
		BREAK

		CASE 0
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 play_single_audio_sound
			IF NOT IS_CHAR_DEAD play_single_audio_ped
				DISABLE_CHAR_SPEECH play_single_audio_ped FALSE
			ENDIF
			play_single_audio_flag++		
		BREAK
		CASE 1
			IF HAS_MISSION_AUDIO_LOADED 1

				IF NOT IS_CHAR_DEAD play_single_audio_ped
					IF NOT IS_CHAR_TALKING  play_single_audio_ped
						IF play_single_audio_attached = 1
							ATTACH_MISSION_AUDIO_TO_CHAR 1 play_single_audio_ped
						ENDIF
					ENDIF
				ENDIF

				PLAY_MISSION_AUDIO 1
				PRINT_NOW $play_single_audio_text 10000 1
				play_single_audio_flag++	
				
			ENDIF
		BREAK
		CASE 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				play_single_audio_flag++
			ELSE
				IF DOES_CHAR_EXIST play_single_audio_ped
					IF IS_CHAR_DEAD play_single_audio_ped
						play_single_audio_flag++
					ENDIF
				ENDIF
			ENDIF
		BREAK
		CASE 3
			CLEAR_MISSION_AUDIO 1
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD play_single_audio_ped
				ENABLE_CHAR_SPEECH play_single_audio_ped
			ENDIF
			play_single_audio_flag++
		BREAK

	ENDSWITCH

RETURN

}
