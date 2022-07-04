MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : 
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
SCRIPT_NAME CAT1
GOSUB mission_start_CAT1
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_CAT1
ENDIF
GOSUB mission_cleanup_CAT1
MISSION_END

mission_start_CAT1:
//REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

DO_FADE 0 FADE_OUT

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
LVAR_INT left_cat_dialogue
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
audio_line_is_active = 0 // set this incase it was left at non-zero
left_cat_dialogue = 0

dialogue_flag = 0
help_flag	  = 0

dialogue_timer = 0
help_timer	   = 0

TIMERA = 0
TIMERB = 0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm

mission_loop_CAT1:
WAIT 0

	// switch on debug tools
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

	// Frame counter
	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

	// Additional Timers
	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	dialogue_timer += m_time_diff
	help_timer	   += m_time_diff
	briefcase_text_timer += m_time_diff


	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		m_passed = 1
	ENDIF

	IF debug_on = 1
		GOSUB cat1_debug_tools
	ENDIF

	SWITCH m_stage

		CASE 0
			GOSUB cat1_m_stage_0
		BREAK

		CASE 1
			GOSUB cat1_m_stage_1
		BREAK

		CASE 2
			GOSUB cat1_m_stage_2
		BREAK

		CASE 3
			GOSUB cat1_m_stage_3
		BREAK

		CASE 4
			GOSUB cat1_m_stage_4
		BREAK

	ENDSWITCH


	GOSUB cat1_global_functions

						

//  end of main loop *** don't change ***
end_of_main_loop_CAT1:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_CAT1 
		ELSE
			GOSUB mission_passed_CAT1
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_CAT1
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF



// *****************************************************
//			DEBUG TOOLS
// *****************************************************
cat1_debug_tools:
	// Display mission stage variables for debug
	LVAR_INT display_debug
	VAR_INT CAT1_view_debug[8]
	VAR_FLOAT CAT1_view_debug_f[8]
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
		display_debug++
		IF display_debug > 5
			display_debug = 0
		ENDIF
		CLEAR_ALL_VIEW_VARIABLES
	ENDIF
	IF display_debug = 1
		// system variables
		CAT1_view_debug[0] = m_stage
		CAT1_view_debug[1] = m_goals
		CAT1_view_debug[2] = dialogue_flag
		CAT1_view_debug[3] = dialogue_timer
		CAT1_view_debug[4] = help_flag
		CAT1_view_debug[5] = help_timer
		CAT1_view_debug[6] = TIMERA
		//CAT1_view_debug[7]	= TIMERB
		VIEW_INTEGER_VARIABLE CAT1_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE CAT1_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE CAT1_view_debug[2] dialogue_flag
		VIEW_INTEGER_VARIABLE CAT1_view_debug[3] dialogue_timer
		VIEW_INTEGER_VARIABLE CAT1_view_debug[4] help_flag
		VIEW_INTEGER_VARIABLE CAT1_view_debug[5] help_timer
		VIEW_INTEGER_VARIABLE CAT1_view_debug[6] TIMERA
		//VIEW_INTEGER_VARIABLE CAT1_view_debug[7] TIMERB
	ENDIF
	IF display_debug = 2
	// put mission variable for display in here
		CAT1_view_debug[0] = 0 //disable_group
		CAT1_view_debug[1] = 0 //in_group		
		CAT1_view_debug[2] = cat1_pickups_collected
		CAT1_view_debug[3] = paused[0]
		CAT1_view_debug[4] = paused[1]
		CAT1_view_debug[5] = paused[2]
		CAT1_view_debug[6] = paused[3]
		CAT1_view_debug[7]	= skipped_cutscene
		VIEW_INTEGER_VARIABLE CAT1_view_debug[0] disable_group
		VIEW_INTEGER_VARIABLE CAT1_view_debug[1] in_group		
		VIEW_INTEGER_VARIABLE CAT1_view_debug[2] cat1_pickups_collected
		VIEW_INTEGER_VARIABLE CAT1_view_debug[3] paused[0]
		VIEW_INTEGER_VARIABLE CAT1_view_debug[4] paused[1]
		VIEW_INTEGER_VARIABLE CAT1_view_debug[5] paused[2]
		VIEW_INTEGER_VARIABLE CAT1_view_debug[6] paused[3]
		VIEW_INTEGER_VARIABLE CAT1_view_debug[7] skipped_cutscene
	ENDIF
	IF display_debug = 3
	// put mission variable for display in here
		CAT1_view_debug[0] = robber_flag[0]
		CAT1_view_debug[1] = robber_flag[1]
		CAT1_view_debug[2] = robber_flag[2]
		CAT1_view_debug[3] = robber_flag[3]
		CAT1_view_debug[4] = chase_flag[0]															
		CAT1_view_debug[5] = chase_flag[1]															
		CAT1_view_debug[6] = chase_flag[2]															
		CAT1_view_debug[7] = player_progress										
		VIEW_INTEGER_VARIABLE CAT1_view_debug[0] robber_flag[0]						
		VIEW_INTEGER_VARIABLE CAT1_view_debug[1] robber_flag[1]						
		VIEW_INTEGER_VARIABLE CAT1_view_debug[2] robber_flag[2]						 		
		VIEW_INTEGER_VARIABLE CAT1_view_debug[3] robber_flag[3]
		VIEW_INTEGER_VARIABLE CAT1_view_debug[4] chase_flag[0]		
		VIEW_INTEGER_VARIABLE CAT1_view_debug[5] chase_flag[1]		
		VIEW_INTEGER_VARIABLE CAT1_view_debug[6] chase_flag[2]		
		VIEW_INTEGER_VARIABLE CAT1_view_debug[7] player_progress
	ENDIF
	IF display_debug = 4
	// put mission variable for display in here
		CAT1_view_debug[0] = playback_paused[0]
		CAT1_view_debug[1] = playback_paused[1]
		CAT1_view_debug[2] = playback_paused[2]
		CAT1_view_debug_f[3] = quad_playback_speed[0]						  
		CAT1_view_debug_f[4] = quad_playback_speed[1]						  				
		CAT1_view_debug_f[5] = quad_playback_speed[2]						  				
		CAT1_view_debug_f[6] = master_speed									  		
		CAT1_view_debug[7] = show_gun								  
		VIEW_INTEGER_VARIABLE CAT1_view_debug[0] playback_paused[0]			  
		VIEW_INTEGER_VARIABLE CAT1_view_debug[1] playback_paused[1]			  
		VIEW_INTEGER_VARIABLE CAT1_view_debug[2] playback_paused[2]					 		
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[3] quad_playback_speed[0]
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[4] quad_playback_speed[1]
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[5] quad_playback_speed[2]
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[6] master_speed				
		VIEW_INTEGER_VARIABLE CAT1_view_debug[7] show_gun
	ENDIF
	IF display_debug = 5
	// put mission variable for display in here
		CAT1_view_debug_f[0] = look_x
		CAT1_view_debug_f[1] = look_y
		CAT1_view_debug_f[2] = look_z
		CAT1_view_debug_f[3] = quad_dist_from_player[0]						  
		CAT1_view_debug_f[4] = quad_dist_from_player[1]						  				
		CAT1_view_debug_f[5] = quad_dist_from_player[2]						  				
	//	CAT1_view_debug_f[6] = master_speed									  		
	//	CAT1_view_debug_f[7] = player_progress								  
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[0] look_x			  
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[1] look_y			  
		VIEW_FLOAT_VARIABLE CAT1_view_debug_F[2] look_z					 		
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[3] quad_dist_from_player[0]
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[4] quad_dist_from_player[1]
		VIEW_FLOAT_VARIABLE CAT1_view_debug_f[5] quad_dist_from_player[2]
	//	VIEW_FLOAT_VARIABLE CAT1_view_debug_f[6] master_speed				
	//	VIEW_FLOAT_VARIABLE CAT1_view_debug_f[7] player_progress
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
		GOTO end_of_main_loop_CAT1
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
RETURN

// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
cat1_m_stage_0:
		
	// fake creates
	IF m_goals = -1
		CREATE_CAR PONY 0.0 0.0 0.0 pcar
		CREATE_CAR PONY 0.0 0.0 0.0 robber_quad[0]
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 robber[0]
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 catalina_blip
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 catalina
		CREATE_PICKUP BRIEFCASE PICKUP_ONCE 0.0 0.0 0.0 robber_pickup[0]
	ENDIF

	// peds
	LVAR_INT robber[4]
	LVAR_INT shop_keeper
	LVAR_INT customer

	// cars
	LVAR_INT pcar 
	LVAR_INT robber_quad[4]

	// objects
	LVAR_INT bcase[4]

	// blips
	LVAR_INT catalina_blip
	LVAR_INT robber_blip[3]
	LVAR_INT briefcase_blip[3]
	LVAR_INT location_blip

	// pickups
	LVAR_INT robber_pickup[3]

	// flags
	LVAR_INT disable_group
	LVAR_INT in_group
	VAR_INT cat1_pickups_collected
	LVAR_INT paused[4]
	LVAR_INT robber_flag[4]
	LVAR_INT skipped_cutscene
	LVAR_INT chase_flag[3]
	LVAR_INT playback_paused[3]	
	LVAR_INT player_progress
	LVAR_INT show_gun
	LVAR_INT camera_in_position_pos	camera_in_position_look
	LVAR_INT cat1_cops_dialogue
	LVAR_INT cat1_take_home_dialogue
	LVAR_INT robber_pickup_been_created[3]
	LVAR_INT alarm_sound_flag
	LVAR_INT is_in_interior
	LVAR_FLOAT stored_master_speed


	// floats
	LVAR_FLOAT quad_playback_speed[3]							   
	LVAR_FLOAT quad_dist_from_player[3]							   
	LVAR_FLOAT master_speed	
	LVAR_FLOAT cam_x cam_y cam_z
	LVAR_FLOAT look_x look_y look_z
	LVAR_FLOAT dest_look_x dest_look_y dest_look_z
	LVAR_FLOAT camera_speed_pos	camera_speed_look
	
	// decision makers
	LVAR_INT group_dm
	LVAR_INT catalina_dm		
	
	// timers
	LVAR_INT briefcase_text_timer		
						   
																   
	disable_group		= 0										   
	in_group			= 0										   
	cat1_pickups_collected = 0									   
	paused[0]			= 0
	paused[1]			= 0
	paused[2]			= 0
	paused[3]			= 0
	robber_flag[0]		= 0
	robber_flag[1]		= 0
	robber_flag[2]		= 0
	robber_flag[3]		= 0
	skipped_cutscene	= 0
	chase_flag[0]		= 0
	chase_flag[1]		= 0
	chase_flag[2]		= 0
	playback_paused[0]	= 0
	playback_paused[1]	= 0
	playback_paused[2]	= 0
	player_progress		= 0		
	show_gun			= 0
	camera_in_position_pos  = 0
	camera_in_position_look = 0
	camera_speed_pos	= 0.0
	camera_speed_look	= 0.0
	cat1_cops_dialogue = 0
	cat1_take_home_dialogue = 0
	catalina_stunt_jump_dialogue = 0
	catalina_blip		= 0
	robber_blip[0]		= 0
	robber_blip[1]		= 0
	robber_blip[2]		= 0
	briefcase_blip[0]	= 0
	briefcase_blip[1]	= 0
	briefcase_blip[2] 	= 0
	location_blip		= 0
	robber[0]			= 0
	robber[1]			= 0
	robber[2]			= 0
	robber[3]			= 0
	shop_keeper			= 0
	customer			= 0
	pcar 				= 0
	robber_quad[0]		= 0
	robber_quad[1]		= 0
	robber_quad[2]		= 0
	robber_quad[3]		= 0
	bcase[0]			= 0
	bcase[1]			= 0
	bcase[2]			= 0
	bcase[3]			= 0
	robber_pickup[0] 	= -1
	robber_pickup[1] 	= -1
	robber_pickup[2] 	= -1
	robber_pickup_been_created[0] =	0
	robber_pickup_been_created[1] =	0
	robber_pickup_been_created[2] =	0
	alarm_sound_flag = 0
	is_in_interior	= 0


	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	LOAD_MISSION_TEXT CAT

	GOSUB CAT1_next_stage
RETURN

// *************************************************************************************************************
//											STAGE 1 - Scripted Cut  
// *************************************************************************************************************
cat1_m_stage_1:
		
	
	SWITCH m_goals
	CASE 0

		REQUEST_MODEL QUAD
		REQUEST_MODEL CWMYFR
		REQUEST_MODEL CWMYHB1 
		REQUEST_MODEL COLT45
		REQUEST_MODEL HMOST
		REQUEST_MODEL SWMOTR1

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED QUAD
		   OR NOT HAS_MODEL_LOADED CWMYFR
		   OR NOT HAS_MODEL_LOADED CWMYHB1
		   OR NOT HAS_MODEL_LOADED COLT45
		   OR NOT HAS_MODEL_LOADED HMOST
		   OR NOT HAS_MODEL_LOADED SWMOTR1
			WAIT 0
		ENDWHILE

		REQUEST_CAR_RECORDING 501
		REQUEST_CAR_RECORDING 502
		REQUEST_CAR_RECORDING 503
		REQUEST_CAR_RECORDING 504
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 501
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 502
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 503
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 504
			WAIT 0
		ENDWHILE

		REQUEST_CAR_RECORDING 505
		REQUEST_CAR_RECORDING 506
		REQUEST_CAR_RECORDING 507
		REQUEST_CAR_RECORDING 509
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 505
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 506
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 507
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 509
			WAIT 0
		ENDWHILE

		TIMERA = 0
		TIMERB = 0

		LOAD_SPECIAL_CHARACTER 5 cat 
		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 5
			WAIT 0
			LOAD_SPECIAL_CHARACTER 5 cat 
		ENDWHILE

		LOAD_MISSION_AUDIO 3 SOUND_SECURITY_ALARM
		WHILE NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE

		m_goals++
		GOTO cat1_global_functions
	BREAK



	// initialisation for stage
	CASE 1

		// delete and recreate catalina
		IF DOES_CHAR_EXIST catalina				
			DELETE_CHAR catalina
		ENDIF
		CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 261.1952 -79.2717 0.4697 catalina
		SET_CHAR_HEADING catalina 41.0763
		SET_CHAR_DROWNS_IN_WATER catalina FALSE
		

		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH catalina_dm
		LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM group_dm
	
		CLEAR_PRINTS
		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL player1 OFF
		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0
		CLEAR_AREA 257.5389 -77.2300 1.3678 300.0 TRUE
		LOAD_SCENE 261.1952 -79.2717 0.4697
		
		disable_group = 1
		IF NOT IS_CHAR_DEAD catalina
			IF IS_GROUP_MEMBER catalina players_group
				REMOVE_CHAR_FROM_GROUP catalina
				in_group = 0
			ENDIF
		ENDIF

		// set car position
		IF NOT IS_CAR_DEAD pcar
			
			GET_CAR_MODEL pcar temp_int
			GET_MODEL_DIMENSIONS temp_int x y z x2 y2 z2
			temp_float = y2 - y // car length
			temp_float /= 1.8
				
			x = 259.7206
			y = -77.2067
			z = 0.5781
			
			x -= temp_float

			SET_CAR_COORDINATES pcar x y z 
			SET_CAR_HEADING pcar 265.6523

		ENDIF
		// set catalina's starting position
		IF NOT IS_CHAR_DEAD catalina
			CLEAR_CHAR_TASKS_IMMEDIATELY catalina
			IF NOT IS_CHAR_IN_ANY_CAR catalina
				SET_CHAR_COORDINATES catalina 261.1952 -79.2717 0.4697 
				SET_CHAR_HEADING catalina     41.0763
				SET_CHAR_DECISION_MAKER catalina empty_dm 
			ELSE
				WARP_CHAR_FROM_CAR_TO_COORD catalina 261.1952 -79.2717 0.4697 
				SET_CHAR_HEADING catalina            41.0763
				SET_CHAR_DECISION_MAKER catalina empty_dm
			ENDIF
	
			// give catalina weapon
			GET_CURRENT_CHAR_WEAPON catalina temp_int
			IF NOT temp_int = WEAPONTYPE_MICRO_UZI 
				IF NOT HAS_MODEL_LOADED MICRO_UZI			
					REQUEST_MODEL MICRO_UZI
					WHILE NOT HAS_MODEL_LOADED MICRO_UZI
						WAIT 0
					ENDWHILE
				ENDIF
				IF NOT IS_CHAR_DEAD catalina
					GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_MICRO_UZI 99999
					SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_MICRO_UZI
					MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
				ENDIF
			ENDIF

		ENDIF
		// set players position	 
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			SET_CHAR_COORDINATES scplayer 259.9737 -78.3268 0.6181
			SET_CHAR_HEADING scplayer 	  38.3173 
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 259.9737 -78.3268 0.6181  
			SET_CHAR_HEADING scplayer            38.3173 
		ENDIF

		// create robbers & quads
		CREATE_CAR QUAD 230.6799 -85.1536 0.4296 robber_quad[0]
		SET_CAR_HEADING robber_quad[0] 17.5494
		SET_CAR_CRUISE_SPEED robber_quad[0] 20.0
		SET_CAR_DRIVING_STYLE robber_quad[0] DRIVINGMODE_AVOIDCARS
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED robber_quad[0] TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER robber_quad[0] TRUE

		CREATE_CAR QUAD 232.2597 -90.1452 0.4297 robber_quad[1]
		SET_CAR_HEADING robber_quad[1] 17.5685
		SET_CAR_CRUISE_SPEED robber_quad[1] 20.0
		SET_CAR_DRIVING_STYLE robber_quad[1] DRIVINGMODE_AVOIDCARS
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED robber_quad[1] TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER robber_quad[1] TRUE

		CREATE_CAR QUAD 234.0826 -95.9093 0.4375 robber_quad[2]
		SET_CAR_HEADING robber_quad[2] 17.5565 
		SET_CAR_CRUISE_SPEED robber_quad[2] 20.0
		SET_CAR_DRIVING_STYLE robber_quad[2] DRIVINGMODE_AVOIDCARS
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED robber_quad[2] TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER robber_quad[2] TRUE

		CREATE_CAR QUAD 235.3937 -100.0505 0.4375  robber_quad[3]
		SET_CAR_HEADING robber_quad[3] 17.5591 
		SET_CAR_CRUISE_SPEED robber_quad[3] 20.0
		SET_CAR_DRIVING_STYLE robber_quad[3] DRIVINGMODE_AVOIDCARS
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED robber_quad[3] TRUE
		SET_CAR_ONLY_DAMAGED_BY_PLAYER robber_quad[3] TRUE
																				   
		temp_int = 0															   
		WHILE temp_int < 4
			IF NOT IS_CAR_DEAD robber_quad[temp_int]
				IF temp_int = 0
					CREATE_CHAR_INSIDE_CAR robber_quad[temp_int] PEDTYPE_MISSION1 CWMYFR robber[temp_int]
				ENDIF
				IF temp_int = 1
					CREATE_CHAR_INSIDE_CAR robber_quad[temp_int] PEDTYPE_MISSION1 CWMYHB1 robber[temp_int]
				ENDIF
				IF temp_int = 2
					CREATE_CHAR_INSIDE_CAR robber_quad[temp_int] PEDTYPE_MISSION1 CWMYFR robber[temp_int]
				ENDIF
				IF temp_int = 3
					CREATE_CHAR_INSIDE_CAR robber_quad[temp_int] PEDTYPE_MISSION1 CWMYHB1 robber[temp_int]
				ENDIF
				SET_CHAR_ACCURACY robber[temp_int] 60
				GIVE_WEAPON_TO_CHAR robber[temp_int] WEAPONTYPE_PISTOL 99999
				TASK_TOGGLE_PED_THREAT_SCANNER robber[temp_int] FALSE FALSE FALSE
				SET_CHAR_DECISION_MAKER robber[temp_int] empty_dm
				SET_CHAR_PROOFS robber[temp_int] TRUE TRUE TRUE TRUE TRUE
				SET_ANIM_GROUP_FOR_CHAR robber[temp_int] MAN 
			ENDIF
		temp_int++
		ENDWHILE

		// pause and immediately pause playbacks
		START_PLAYBACK_RECORDED_CAR robber_quad[0] 501
		START_PLAYBACK_RECORDED_CAR robber_quad[1] 502
		START_PLAYBACK_RECORDED_CAR robber_quad[2] 503
		START_PLAYBACK_RECORDED_CAR robber_quad[3] 504
		temp_int = 0
		WHILE temp_int < 4
			IF NOT temp_int = 0
				IF NOT temp_int = 3
					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[temp_int] 5.0
				ELSE
					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[temp_int] 7.0
				ENDIF
			ENDIF
			PAUSE_PLAYBACK_RECORDED_CAR robber_quad[temp_int]
			paused[temp_int] = 1
		temp_int++
		ENDWHILE

		// create shopkeeper and customer
		CREATE_CHAR PEDTYPE_CIVMALE HMOST 251.6866 -54.9554 0.6181 shop_keeper
		SET_CHAR_HEADING shop_keeper 174.9027
		SET_CHAR_DECISION_MAKER shop_keeper empty_dm
		CREATE_CHAR PEDTYPE_CIVFEMALE SWMOTR1 251.6549 -56.4859 0.6255 customer
		SET_CHAR_HEADING customer 354.6266
		SET_CHAR_DECISION_MAKER customer empty_dm
		TASK_CHAT_WITH_CHAR shop_keeper customer TRUE TRUE
		TASK_CHAT_WITH_CHAR customer shop_keeper FALSE TRUE


		cam_x 	= 261.8140 
		cam_y 	= -79.2233 
		cam_z	= 3.2479 
		look_x 	= 261.3385 
		look_y 	= -78.3447 
		look_z	= 3.2922

		x = look_x - cam_x
		y = look_y - cam_y
		z = look_z - cam_z

		GET_DISTANCE_BETWEEN_COORDS_3D look_x look_y look_z cam_x cam_y cam_z temp_float
		x /= temp_float
		y /= temp_float
		z /= temp_float

		x *= 15.0
		y *= 15.0
		z *= 15.0

		look_x = cam_x + x
		look_y = cam_y + y
		look_z = cam_z + z
							  

		x =	260.8760 - 261.4119
		y =	-77.7132 + 78.5511 
		z =	2.2067 - 2.3102
		GET_DISTANCE_BETWEEN_COORDS_3D 261.4119	-78.5511 2.3102	260.8760 -77.7132 2.2067 temp_float
		x /= temp_float
		y /= temp_float
		z /= temp_float
	
		x *= 15.0
		y *= 15.0
		z *= 15.0

		dest_look_x = 261.4119 + x
		dest_look_y = -78.5511 + y
		dest_look_z	= 2.3102   + z

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "DEST_LOOK_XYZ = "
		SAVE_FLOAT_TO_DEBUG_FILE dest_look_x
		SAVE_FLOAT_TO_DEBUG_FILE dest_look_y
		SAVE_FLOAT_TO_DEBUG_FILE dest_look_z

		SET_FIXED_CAMERA_POSITION cam_x cam_y cam_z 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT look_x look_y look_z JUMP_CUT
		SET_NEAR_CLIP 0.1
				
		WAIT 500
									
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		TIMERA = 0
		TIMERB = 0

		m_goals++
	BREAK

	CASE 2
		m_goals++
	BREAK

	
	// stage specific stuff
	CASE 3

		IF NOT IS_CHAR_DEAD catalina
			CLEAR_CHAR_TASKS_IMMEDIATELY catalina
			OPEN_SEQUENCE_TASK temp_seq
				TASK_GO_STRAIGHT_TO_COORD -1 261.2168 -77.1227 0.6181 PEDMOVE_WALK 3000
				TASK_ACHIEVE_HEADING -1 41.0763
				TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
				//TASK_PLAY_ANIM -1 WOMAN_IDLESTANCE PED 4.0 TRUE FALSE FALSE FALSE -1
				//TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE TRUE -1
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK catalina temp_seq 
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		OPEN_SEQUENCE_TASK temp_seq
			TASK_PAUSE -1 2000
			TASK_GO_STRAIGHT_TO_COORD -1 260.7615 -77.7368 0.6181 PEDMOVE_WALK 8000
			TASK_ACHIEVE_HEADING -1 5.0
			IF NOT IS_CHAR_DEAD catalina
				TASK_LOOK_AT_CHAR -1 catalina 8000
			ENDIF
			TASK_PAUSE -1 3000
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK scplayer temp_seq 
		CLEAR_SEQUENCE_TASK temp_seq

		TIMERA = 0
		TIMERB = 0
		
		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO  1 SOUND_CAT1_AA
		crane_cam_started = 0

		//CAMERA_RESET_NEW_SCRIPTABLES
		//CAMERA_SET_VECTOR_MOVE	261.8140 -79.2233 3.2479	fEndPosX	fEndPosY	fEndPosZ	fTime 	TRUE/FALSE

		m_goals++
	BREAK	

	// wait a couple of ticks before dialogue
	CASE 4

		IF TIMERA > 1500
			//PRINT_NOW CAT101 6000 1 // Catalina - 'Ok, robbing this place should be a peice of piss.'
			
			$audio_string    = &CAT1_AA	
			audio_sound_file = SOUND_CAT1_AA
			START_NEW_SCRIPT audio_line catalina 0 1 1 1

			CLEAR_MISSION_AUDIO 2
			IF cat_counter = 0
				LOAD_MISSION_AUDIO  2 SOUND_CAT1_AC 
			ELSE
				LOAD_MISSION_AUDIO 2 SOUND_CAT1_AB
			ENDIF

			TIMERA = 0
			m_goals++
			TIMERA = 0
		ELSE
			IF TIMERA > 1000
//				GOSUB cat1_update_crane_cam_1
//				SET_FIXED_CAMERA_POSITION cam_x cam_y cam_z 0.0 0.0 0.0
//				POINT_CAMERA_AT_POINT look_x look_y look_z JUMP_CUT 

				LVAR_INT crane_cam_started
				IF crane_cam_started = 0
					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_SET_VECTOR_MOVE 261.8140 -79.2233 3.2479 261.4119 -78.5511 2.3102 3000 TRUE
					CAMERA_SET_VECTOR_TRACK	261.3385 -78.3447 3.2922 253.3734 -65.9824 0.7577 3000 TRUE
					CAMERA_PERSIST_TRACK TRUE
					CAMERA_PERSIST_POS TRUE
					crane_cam_started++
				ENDIF

			ENDIF	
		ENDIF		
		TIMERB = 0
	BREAK

	// 'you mean like that last place'
	CASE 5
		IF TIMERA > 1000
			IF audio_line_is_active = 0
				
				IF NOT IS_CHAR_DEAD catalina
					CLEAR_CHAR_TASKS catalina
				ENDIF

				IF cat_counter = 0
					$audio_string    = &CAT1_AC	     
					audio_sound_file = SOUND_CAT1_AC 
				ELSE
					$audio_string    = &CAT1_AB	
					audio_sound_file = SOUND_CAT1_AB
				ENDIF
				START_NEW_SCRIPT audio_line scplayer 0 1 2 1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO  1 SOUND_CAT1_AD 
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
	BREAK

	// who are these cowboys
	CASE 6
		IF TIMERA > 1000
			IF audio_line_is_active = 0
				$audio_string    = &CAT1_AD	
				audio_sound_file = SOUND_CAT1_AD
				START_NEW_SCRIPT audio_line catalina 0 1 1 1
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO  2 SOUND_CAT1_AE
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF 
	BREAK


	// hang back a while
	CASE 7
		IF TIMERA > 1000
			IF audio_line_is_active = 0
				$audio_string    = &CAT1_AE	
				audio_sound_file = SOUND_CAT1_AE
				START_NEW_SCRIPT audio_line scplayer 0 1 2 1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO  1 SOUND_CAT1_AF
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
	BREAK

		
	// wait for all the guys to be off their quads
	CASE 8
		IF  robber_flag[0] = 2
			IF NOT IS_CHAR_DEAD catalina
				CAMERA_RESET_NEW_SCRIPTABLES
				SET_FIXED_CAMERA_POSITION  260.0450 -63.3693 2.4676 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 	   259.1025 -63.6456 2.2799	JUMP_CUT
				m_goals++
				TIMERA = 0
			ENDIF
		ENDIF
	BREAK

	// cut to inside the store
	CASE 9
		IF TIMERA > 2500			  
//			SET_FIXED_CAMERA_POSITION 251.5606 -52.7225 2.6179 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT     251.7925 -53.6661 2.3814 JUMP_CUT

			SET_FIXED_CAMERA_POSITION 250.2375 -53.7657 2.3280 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT     250.8728 -54.5222 2.1726 JUMP_CUT

			IF NOT IS_CHAR_DEAD robber[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[0]
				SET_CHAR_COORDINATES robber[0] 252.6819 -56.3618 0.6181 
				SET_CHAR_HEADING robber[0] 20.1267
				IF NOT IS_CHAR_DEAD shop_keeper	
					OPEN_SEQUENCE_TASK temp_seq
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_AIM_GUN_AT_CHAR -1 shop_keeper 999999
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[0] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD robber[1]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[1]
				SET_CHAR_COORDINATES robber[1] 252.1074 -57.8084 0.6181   
				SET_CHAR_HEADING robber[1] 21.3066
				IF NOT IS_CHAR_DEAD customer
					SET_CHAR_HEALTH customer 1
					OPEN_SEQUENCE_TASK temp_seq
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_LOOK_AT_CHAR -1 customer 5000
						TASK_AIM_GUN_AT_CHAR -1 customer 2300
						TASK_KILL_CHAR_ON_FOOT -1 customer
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[1] temp_seq 
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF							   
			IF NOT IS_CHAR_DEAD robber[2]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[2]
				SET_CHAR_COORDINATES robber[2] 254.4398 -57.6653 0.6473   
				SET_CHAR_HEADING robber[2] 48.9264
			ENDIF
			IF NOT IS_CHAR_DEAD robber[3]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[3]
				SET_CHAR_COORDINATES robber[3] 255.1348 -61.2211 0.6181  
				SET_CHAR_HEADING robber[3] 170.6080
			ENDIF

			IF NOT IS_CHAR_DEAD customer
				CLEAR_CHAR_TASKS_IMMEDIATELY customer
				OPEN_SEQUENCE_TASK temp_seq
					IF NOT IS_CHAR_DEAD robber[0]
						TASK_LOOK_AT_CHAR -1 robber[0] 5000
					ENDIF
					TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 251.1549 -56.4655 0.6255 293.7874 0.02 handscower ped 4.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK customer temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF

			IF NOT IS_CHAR_DEAD shop_keeper
				TASK_HANDS_UP shop_keeper 999999
			ENDIF
			
			m_goals++

			TIMERA = 0
		ENDIF
	BREAK

	CASE 10
		IF TIMERA > 3000
			//SET_FIXED_CAMERA_POSITION	260.6280 -64.5171 2.0402  0.0 0.0 0.0
			//POINT_CAMERA_AT_POINT 		259.6584 -64.3497 1.8618  JUMP_CUT
			
			//SET_FIXED_CAMERA_POSITION 262.5057 -78.0042 1.9449 0.0 0.0 0.0
			//POINT_CAMERA_AT_POINT 261.7840 -77.3174 2.0317 JUMP_CUT

//			SET_FIXED_CAMERA_POSITION 262.9865 -78.5169 2.1154 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT 262.2247 -77.8699 2.1485 JUMP_CUT

//			SET_FIXED_CAMERA_POSITION 260.0617 -70.5637 0.9636 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT     259.3957 -69.8361 1.1276 JUMP_CUT
			
			SET_PED_DENSITY_MULTIPLIER 0.0
			CLEAR_AREA 242.0291 -62.9491 2.3886 50.0 TRUE
			 

			SET_FIXED_CAMERA_POSITION 242.0291 -62.9491 2.3886 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT     242.9384 -63.3287 2.2181 JUMP_CUT

			// create briefcases
			CREATE_OBJECT BRIEFCASE 254.2335 -56.9540 0.6181 bcase[0]
			CREATE_OBJECT BRIEFCASE 254.2335 -56.9540 0.6181 bcase[1]
			CREATE_OBJECT BRIEFCASE 254.2335 -56.9540 0.6181 bcase[2]
			CREATE_OBJECT BRIEFCASE 254.2335 -56.9540 0.6181 bcase[3]

			// set robbers positions
			IF NOT IS_CHAR_DEAD robber[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[0]
				TASK_PICK_UP_OBJECT robber[0] bcase[0] 0.0 0.15 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				SET_CHAR_COORDINATES robber[0] 254.2335 -56.9540 0.6181  
				SET_CHAR_HEADING robber[0]	   190.0
			ENDIF
			IF NOT IS_CHAR_DEAD robber[3]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[3]
				TASK_PICK_UP_OBJECT robber[3] bcase[3] 0.0 0.15 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				SET_CHAR_COORDINATES robber[3] 254.2521 -58.7186 0.6181   						
				SET_CHAR_HEADING robber[3]	   190.0											
			ENDIF																				
			IF NOT IS_CHAR_DEAD robber[2]														
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[2]
				TASK_PICK_UP_OBJECT robber[2] bcase[2] 0.0 0.15 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				SET_CHAR_COORDINATES robber[2] 254.2762 -60.0254 0.6255   
				SET_CHAR_HEADING robber[2]	   190.0 
			ENDIF
			IF NOT IS_CHAR_DEAD robber[1]
				CLEAR_CHAR_TASKS_IMMEDIATELY robber[1]
				TASK_PICK_UP_OBJECT robber[1] bcase[1] 0.0 0.15 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				SET_CHAR_COORDINATES robber[1] 255.1927 -54.9104 0.6181 
				SET_CHAR_HEADING robber[1]	   190.0
			ENDIF

			TIMERA = 0
			m_goals++

		ENDIF
	BREAK

	// make alarm go off
	CASE 11
		//IF TIMERA > 1000
			//ADD_CONTINUOUS_SOUND 251.5757 -62.9454 3.3786 SOUND_BANK_ALARM_LOOP snd_fx
			SET_MISSION_AUDIO_POSITION 3 251.5757 -62.9454 3.3786
			PLAY_MISSION_AUDIO 3
			alarm_sound_flag = 1
			m_goals++
			TIMERA = 0
		//ENDIF
	BREAK

	CASE 12
		temp_int = 0
		WHILE temp_int < 4
			IF NOT IS_CHAR_DEAD robber[temp_int]
				//SET_CHAR_COLLISION robber[temp_int] FALSE
				robber_flag[temp_int]++	
			ENDIF
		temp_int++
		ENDWHILE


		//PRINT_NOW CAT105 3000 1 // Catalina - 'They stole our idea!!'
		TIMERA = 0
		m_goals++
	BREAK

	CASE 13

		IF TIMERA > 1000
			IF audio_line_is_active = 0
				$audio_string    = &CAT1_AF	
				audio_sound_file = SOUND_CAT1_AF
				START_NEW_SCRIPT audio_line robber[3] 0 1 1 1
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO  2 SOUND_CAT1_AG
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF


	BREAK

	CASE 14
		IF TIMERA > 1000
			IF audio_line_is_active = 0
				$audio_string    = &CAT1_AG	
				audio_sound_file = SOUND_CAT1_AG
				START_NEW_SCRIPT audio_line catalina 0 1 2 1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO  1 SOUND_CAT1_AH
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
	BREAK

	CASE 15
		IF TIMERA < 10000
			IF NOT IS_CHAR_DEAD robber[3]
				IF NOT IS_CAR_DEAD robber_quad[3]
					IF IS_CHAR_IN_CAR robber[3] robber_quad[3]
						m_goals++
						TIMERA = 0
					ENDIF
				ENDIF
			ENDIF
		ELSE
			m_goals++
		ENDIF
	BREAK

	CASE 16
		IF TIMERA > 500
			m_goals++
		ENDIF
	BREAK

	CASE 17

		//IF TIMERA > 5000
 
//			// stop playback for each quad, we are going to restart them
//			temp_int = 0
//			WHILE temp_int < 4
//				IF NOT IS_CAR_DEAD robber_quad[temp_int]
//					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[temp_int]
//						STOP_PLAYBACK_RECORDED_CAR robber_quad[temp_int]
//					ENDIF
//				ENDIF
//			temp_int++
//			ENDWHILE
//
//			REQUEST_CAR_RECORDING 505
//			REQUEST_CAR_RECORDING 506
//			REQUEST_CAR_RECORDING 507
//			REQUEST_CAR_RECORDING 509
//			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 505
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 506
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 507
//			   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 509
//				WAIT 0
//			ENDWHILE							   

			// delete briefcases							
			temp_int = 0
			WHILE temp_int < 4
				IF DOES_OBJECT_EXIST bcase[temp_int]
					DELETE_OBJECT bcase[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			
			// warp all dudes onto their cars
			temp_int = 0
			WHILE temp_int < 4
				IF NOT IS_CHAR_DEAD robber[temp_int]
					IF NOT IS_CAR_DEAD robber_quad[temp_int]
						IF NOT IS_CHAR_IN_CAR robber[temp_int] robber_quad[temp_int]
							WARP_CHAR_INTO_CAR robber[temp_int] robber_quad[temp_int]
						ENDIF
					ENDIF
				ENDIF
			   	robber_flag[temp_int] = 6
				temp_int++
			ENDWHILE

			// restart playback for each car and skip to the point we want
			IF NOT IS_CAR_DEAD robber_quad[0]
//				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
//					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[0] -999.0
//				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 509
						REQUEST_CAR_RECORDING 509
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 509
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[0]
						START_PLAYBACK_RECORDED_CAR robber_quad[0] 509
						SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[0] 2.0
					ENDIF  
				ENDIF 
			ENDIF
			IF NOT IS_CAR_DEAD robber_quad[1]
//				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
//					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[1] -999.0
//				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 505
						REQUEST_CAR_RECORDING 505
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 505
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[1]
						START_PLAYBACK_RECORDED_CAR robber_quad[1] 505
						SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[1] 0.0 
					ENDIF 
				ENDIF 
			ENDIF
			IF NOT IS_CAR_DEAD robber_quad[2]
//				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
//					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[2] -999.0
//				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 506
						REQUEST_CAR_RECORDING 506
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 506
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[2]
						START_PLAYBACK_RECORDED_CAR robber_quad[2] 506
						SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[2] 6.0  
					ENDIF
				ENDIF 
			ENDIF
			IF NOT IS_CAR_DEAD robber_quad[3]
//				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[3]
//					SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[3] -999.0
//				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[3]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 507
						REQUEST_CAR_RECORDING 507
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 507
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[3]
						START_PLAYBACK_RECORDED_CAR robber_quad[3] 507
						SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[3]  3.0  
					ENDIF
				ENDIF 
			ENDIF
			
			IF NOT IS_CHAR_DEAD catalina
				CLEAR_CHAR_TASKS_IMMEDIATELY catalina
				SET_CHAR_COORDINATES catalina 260.2472 -75.3335 0.4697
			ENDIF
			
			TIMERA = 0
			

		//ENDIF

			SET_FIXED_CAMERA_POSITION 261.7350 -75.3817 2.1270 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 260.8360 -74.9604 2.0068 JUMP_CUT

			m_goals++
		
	BREAK

	// after a couple of seconds catalina starts shooting
	CASE 18
		//IF TIMERA > 2000
			IF NOT IS_CHAR_DEAD catalina
				 
				SET_CHAR_HEADING catalina 53.9394
				SET_CHAR_SHOOT_RATE catalina 100
				IF NOT IS_CHAR_DEAD robber[3]
					OPEN_SEQUENCE_TASK temp_seq
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_LOOK_AT_CHAR -1 robber[3] 1000	
						TASK_AIM_GUN_AT_CHAR -1 robber[3] 5000
						//TASK_KILL_CHAR_ON_FOOT -1 robber[3]
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK catalina temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF
			TIMERA = 0
			m_goals++													  
		//ENDIF
	BREAK

	CASE 19
		IF audio_line_is_active = 0
			$audio_string    = &CAT1_AH	
			audio_sound_file = SOUND_CAT1_AH
			START_NEW_SCRIPT audio_line catalina 0 1 1 1
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 2 SOUND_CATX_TD
			TIMERA = 0
			m_goals++
		ENDIF
	BREAK

		
	// show catalina shooting back guy
	CASE 20
		IF TIMERA > 2000
			IF NOT IS_CHAR_DEAD catalina
				IF NOT IS_CHAR_DEAD robber[3]
					SET_CHAR_SHOOT_RATE catalina 100
					TASK_KILL_CHAR_ON_FOOT catalina robber[3]
					SET_CHAR_PROOFS robber[3] TRUE TRUE TRUE TRUE TRUE
				ENDIF
			ENDIF
		ENDIF

		IF TIMERA > 2500
			SET_FIXED_CAMERA_POSITION 252.0826 -71.4979 1.3761 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 253.0449 -71.7658 1.4225 JUMP_CUT
		ENDIF


		IF TIMERA > 2800 
			IF NOT IS_CHAR_DEAD robber[3]
				IF NOT IS_CAR_DEAD robber_quad[3]
					CLEAR_CHAR_TASKS_IMMEDIATELY robber[3]
					OPEN_SEQUENCE_TASK temp_seq
						TASK_DIE_NAMED_ANIM -1 BIKE_FALL_OFF PED 4.0 TRUE
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[3] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq

						$audio_string    = &CATX_TD	
						audio_sound_file = SOUND_CATX_TD
						START_NEW_SCRIPT audio_line catalina 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_CAT1_BA
						TIMERA = 0

					m_goals++ 
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF
	BREAK

	// pause for a couple of secs
	CASE 21
		IF TIMERA > 1000
			m_goals++
		ENDIF
	BREAK

	// create dropped briefcase
	CASE 22
		IF IS_CHAR_DEAD robber[3]
			GET_DEAD_CHAR_COORDINATES robber[3] x y z
							
			SET_FIXED_CAMERA_POSITION 259.2034 -75.5284 2.0793 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 260.1396 -75.2342 1.8871 JUMP_CUT

			m_goals++
		ENDIF
	BREAK

	// make catalina get briefcase, then get on quad, make player get on quad
	CASE 23
		IF NOT IS_CHAR_DEAD catalina
			CLEAR_CHAR_TASKS catalina
			OPEN_SEQUENCE_TASK temp_seq
				//TASK_GO_STRAIGHT_TO_COORD -1 260.7693 -73.6756 1.0906 PEDMOVE_RUN 5000
				IF NOT IS_CAR_DEAD robber_quad[3]
					TASK_ENTER_CAR_AS_PASSENGER -1 robber_quad[3] 5000 0
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK catalina temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
			CLEAR_CHAR_TASKS scplayer
			OPEN_SEQUENCE_TASK temp_seq
				TASK_GO_STRAIGHT_TO_COORD -1 263.4937 -75.2639 0.4697 PEDMOVE_RUN 5000
				IF NOT IS_CAR_DEAD robber_quad[3]
					TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[3] 10000
				ENDIF
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK scplayer temp_seq
			CLEAR_SEQUENCE_TASK temp_Seq
			//PRINT_NOW CAT107 4000 1 // CJ - 'Jump on, we can catch the rest of those nob ends.'

				$audio_string    = &CAT1_BA	
				audio_sound_file = SOUND_CAT1_BA
				START_NEW_SCRIPT audio_line catalina 0 1 1 1
				TIMERA = 0

			m_goals++
		ENDIF
	BREAK
	
	// delete pickup when catalina walks into it
	CASE 24
//		IF NOT IS_CHAR_DEAD catalina
//			IF LOCATE_CHAR_ON_FOOT_3D catalina 260.7693 -73.6756 1.0906 1.0 1.0 2.0 FALSE
				//REMOVE_PICKUP robber_pickup[3]
				m_goals++
//			ENDIF
//		ENDIF
	BREAK

	// wait for catalina and player to get on quad
	CASE 25
		IF NOT IS_CAR_DEAD robber_quad[3]
			IF NOT IS_CHAR_DEAD catalina
				IF IS_CHAR_IN_CAR catalina robber_quad[3]
				AND IS_CHAR_IN_CAR scplayer robber_quad[3]
					m_goals = 99
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH

	IF m_goals > 1
		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 CIRCLE
			m_goals = 99
			skipped_cutscene = 1
		ENDIF
	ENDIF


	// control playbacks -----------------------------------------------
	IF robber_flag[0] = 0
		IF paused[0] = 1
			IF TIMERB > 1000
				IF NOT IS_CAR_DEAD robber_quad[0]
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
						UNPAUSE_PLAYBACK_RECORDED_CAR robber_quad[0]
						paused[0] = 0
						robber_flag[0]++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 0
		IF paused[1] = 1
			IF TIMERB > 2000
				IF NOT IS_CAR_DEAD robber_quad[1]
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
						UNPAUSE_PLAYBACK_RECORDED_CAR robber_quad[1]
						paused[1] = 0
						robber_flag[1]++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[2] = 0
		IF paused[2] = 1
			IF TIMERB > 2000
				IF NOT IS_CAR_DEAD robber_quad[2]
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
						UNPAUSE_PLAYBACK_RECORDED_CAR robber_quad[2]
						paused[2] = 0
						robber_flag[2]++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[3] = 0
		IF paused[3] = 1
			IF TIMERB > 3000
				IF NOT IS_CAR_DEAD robber_quad[3]		
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[3]
						UNPAUSE_PLAYBACK_RECORDED_CAR robber_quad[3]
						paused[3] = 0
						robber_flag[3]++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
						 
	// robbers get off quads and enter shop ----------------------------------
	IF robber_flag[0] = 1
		IF NOT IS_CAR_DEAD robber_quad[0] 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
				IF NOT IS_CHAR_DEAD robber[0]
					FLUSH_ROUTE
					EXTEND_ROUTE 254.5467 -62.6116 0.5625
					EXTEND_ROUTE 254.0845 -57.0229 0.5781
					OPEN_SEQUENCE_TASK temp_seq
						TASK_LEAVE_CAR -1 robber_quad[0]
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[0] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					robber_flag[0]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 1
		IF NOT IS_CAR_DEAD robber_quad[1] 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
				IF NOT IS_CHAR_DEAD robber[1]
					FLUSH_ROUTE
					EXTEND_ROUTE 254.5467 -62.6116 0.5625
					EXTEND_ROUTE 254.0845 -57.0229 0.5781
					OPEN_SEQUENCE_TASK temp_seq
						TASK_LEAVE_CAR -1 robber_quad[1]
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[1] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					robber_flag[1]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[2] = 1
		IF NOT IS_CAR_DEAD robber_quad[2] 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
				IF NOT IS_CHAR_DEAD robber[2]
					FLUSH_ROUTE
					EXTEND_ROUTE 254.5467 -62.6116 0.5625
					EXTEND_ROUTE 254.0845 -57.0229 0.5781
					OPEN_SEQUENCE_TASK temp_seq
						TASK_LEAVE_CAR -1 robber_quad[2]
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[2] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					robber_flag[2]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[3] = 1
		IF NOT IS_CAR_DEAD robber_quad[3] 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[3]
				IF NOT IS_CHAR_DEAD robber[3]
					FLUSH_ROUTE
					EXTEND_ROUTE 254.5467 -62.6116 0.5625
					EXTEND_ROUTE 254.0845 -57.0229 0.5781
					OPEN_SEQUENCE_TASK temp_seq
						TASK_LEAVE_CAR -1 robber_quad[3]
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK robber[3] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					robber_flag[3]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// robbers leave shop ----------------------------------------------------
	IF robber_flag[0] = 3
		IF NOT IS_CHAR_DEAD robber[0]
			IF NOT IS_CAR_DEAD robber_quad[0]
				FLUSH_ROUTE
				EXTEND_ROUTE 254.6638 -62.4099 0.6181
				EXTEND_ROUTE 252.90 -64.95 0.6181 //251.7 -70.45 0.6181
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 2000
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[0] 20000
				CLOSE_SEQUENCE_TASK temp_seq
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
				PERFORM_SEQUENCE_TASK robber[0] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				robber_flag[0]++
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 3
		IF NOT IS_CHAR_DEAD robber[1]
			IF NOT IS_CAR_DEAD robber_quad[1]
				IF NOT IS_CAR_DEAD robber_quad[1]
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS robber_quad[1] 1.0 0.5 0.0 x y z
				ENDIF
				FLUSH_ROUTE
				EXTEND_ROUTE 255.0126 -62.5672 0.6181  
				EXTEND_ROUTE 255.9082 -64.7079 0.5781
				EXTEND_ROUTE x y z
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 3000
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[1] 20000
				CLOSE_SEQUENCE_TASK temp_seq
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
				PERFORM_SEQUENCE_TASK robber[1] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				robber_flag[1]++
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[2] = 3
		IF NOT IS_CHAR_DEAD robber[2]
			IF NOT IS_CAR_DEAD robber_quad[2]
				FLUSH_ROUTE
				EXTEND_ROUTE 254.3236 -62.2213 0.6181
				EXTEND_ROUTE 250.6540 -65.0175 0.6181 //245.0 -67.95 0.6181
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 0
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[2] 20000
				CLOSE_SEQUENCE_TASK temp_seq	
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN			   
				PERFORM_SEQUENCE_TASK robber[2] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				robber_flag[2]++
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[3] = 3
		IF NOT IS_CHAR_DEAD robber[3]
			IF NOT IS_CAR_DEAD robber_quad[3]
				IF NOT IS_CAR_DEAD robber_quad[3]
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS robber_quad[3] 1.0 0.5 0.0 x y z
				ENDIF
				FLUSH_ROUTE
				EXTEND_ROUTE 254.0475 -62.7343 0.6181
				//EXTEND_ROUTE 242.44 -66.15 0.6181
				EXTEND_ROUTE 247.6622 -64.1287 0.5781
				EXTEND_ROUTE x y z
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PAUSE -1 1000
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[3] 20000
				CLOSE_SEQUENCE_TASK temp_seq
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
				PERFORM_SEQUENCE_TASK robber[3] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				robber_flag[3]++
			ENDIF
		ENDIF
	ENDIF

	// robbers enter cars
	IF robber_flag[0] = 4
		IF NOT IS_CHAR_DEAD robber[0]
			IF NOT IS_CAR_DEAD robber_quad[0]
				IF LOCATE_CHAR_ON_FOOT_CAR_3D robber[0] robber_quad[0] 2.5 2.5 2.5 FALSE
					SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
					TASK_ENTER_CAR_AS_DRIVER robber[0] robber_quad[0] 5000
					robber_flag[0]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 4
		robber_flag[1]++
	ENDIF
	IF robber_flag[2] = 4
		IF NOT IS_CHAR_DEAD robber[2]
			IF NOT IS_CAR_DEAD robber_quad[2]
				IF LOCATE_CHAR_ON_FOOT_CAR_3D robber[2] robber_quad[2] 2.5 2.5 2.5 FALSE
					SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
					TASK_ENTER_CAR_AS_DRIVER robber[2] robber_quad[2] 5000
					robber_flag[2]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[3] = 4
		robber_flag[3]++
	ENDIF

	// playback of robbers leaving store
	IF robber_flag[0] = 5
		IF NOT IS_CHAR_DEAD robber[0]
			IF NOT IS_CAR_DEAD robber_quad[0]
				IF IS_CHAR_IN_CAR robber[0] robber_quad[0]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 509
						REQUEST_CAR_RECORDING 509
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 509
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[0] 
						START_PLAYBACK_RECORDED_CAR robber_quad[0] 509
					ENDIF
					robber_flag[0]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 5
		IF NOT IS_CHAR_DEAD robber[1]
			IF NOT IS_CAR_DEAD robber_quad[1]
				IF IS_CHAR_IN_CAR robber[1] robber_quad[1]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 505
						REQUEST_CAR_RECORDING 505
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 505
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[1]
						START_PLAYBACK_RECORDED_CAR robber_quad[1] 505
					ENDIF
					robber_flag[1]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[2] = 5
		IF NOT IS_CHAR_DEAD robber[2]
			IF NOT IS_CAR_DEAD robber_quad[2]
				IF IS_CHAR_IN_CAR robber[2] robber_quad[2]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 506
						REQUEST_CAR_RECORDING 506
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 506
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[2]
						START_PLAYBACK_RECORDED_CAR robber_quad[2] 506
					ENDIF
					robber_flag[2]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[3] = 5
		IF NOT IS_CHAR_DEAD robber[3]
			IF NOT IS_CAR_DEAD robber_quad[3]
				IF IS_CHAR_IN_CAR robber[3] robber_quad[3]
					IF NOT HAS_CAR_RECORDING_BEEN_LOADED 507
						REQUEST_CAR_RECORDING 507
						WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 507
							WAIT 0
						ENDWHILE
					ENDIF
					IF NOT IS_CAR_DEAD robber_quad[3]
						START_PLAYBACK_RECORDED_CAR robber_quad[3] 507
					ENDIF
					robber_flag[3]++
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// set quad bike positions
	IF robber_flag[0] = 7
		IF NOT IS_CAR_DEAD robber_quad[0]
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
				SET_CAR_COORDINATES robber_quad[0] 376.1397 -94.8354 1.8378
				SET_CAR_HEADING robber_quad[0] 285.1002
				robber_flag[0]++
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[1] = 7
		IF NOT IS_CAR_DEAD robber_quad[1]
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
				SET_CAR_COORDINATES robber_quad[1] 370.2741 -95.2991 1.3107
				SET_CAR_HEADING robber_quad[1] 273.2807
				robber_flag[1]++
			ENDIF
		ENDIF
	ENDIF
	IF robber_flag[2] = 7
		IF NOT IS_CAR_DEAD robber_quad[2]
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
				SET_CAR_COORDINATES robber_quad[2] 363.1674 -95.3071 0.7736
				SET_CAR_HEADING robber_quad[2] 273.2807
				robber_flag[2]++
			ENDIF
		ENDIF
	ENDIF

	// finish cutscene & exit
	IF m_goals = 99

		CAMERA_RESET_NEW_SCRIPTABLES
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 TRUE

		// set any flags
		disable_group 	= 0
		paused[0] 		= 0
		paused[1]		= 0
		paused[2]		= 0
		paused[3]		= 0

		// set everything to where it's meant to be
		IF NOT IS_CAR_DEAD robber_quad[0]
			IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[0]
				STOP_PLAYBACK_RECORDED_CAR robber_quad[0]
			ENDIF
			SET_CAR_COORDINATES robber_quad[0] 376.1397 -94.8354 1.8378
			SET_CAR_HEADING robber_quad[0] 285.1002
		ENDIF
		IF NOT IS_CAR_DEAD robber_quad[1]
			IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[1]
				STOP_PLAYBACK_RECORDED_CAR robber_quad[1]
			ENDIF
			SET_CAR_COORDINATES robber_quad[1] 370.2741 -95.2991 1.3107
			SET_CAR_HEADING robber_quad[1] 273.2807
		ENDIF 
		IF NOT IS_CAR_DEAD robber_quad[2]
			IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[2]
				STOP_PLAYBACK_RECORDED_CAR robber_quad[2]
			ENDIF
			SET_CAR_COORDINATES robber_quad[2] 363.1674 -95.3071 0.7736
			SET_CAR_HEADING robber_quad[2] 273.2807
		ENDIF

		IF skipped_cutscene = 1
			IF NOT IS_CAR_DEAD robber_quad[3]
				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[3]
					STOP_PLAYBACK_RECORDED_CAR robber_quad[3]
				ENDIF
				SET_CAR_COORDINATES robber_quad[3] 263.8679 -73.6352 0.4297  
				SET_CAR_HEADING robber_quad[3] 272.3554
				SET_CAR_HEALTH robber_quad[3] 1500
			ENDIF
		ENDIF

		// delete robbers
		DELETE_CHAR robber[0]
		DELETE_CHAR robber[1]
		DELETE_CHAR robber[2]
		DELETE_CHAR robber[3]

		// delete briefcases
		DELETE_OBJECT bcase[0]
		DELETE_OBJECT bcase[1]
		DELETE_OBJECT bcase[2]
		DELETE_OBJECT bcase[3]

		IF NOT IS_CHAR_DEAD catalina 
			SET_CHAR_DECISION_MAKER catalina tough_dm
			CLEAR_CHAR_TASKS_IMMEDIATELY catalina
			IF NOT IS_CAR_DEAD robber_quad[3]
				WARP_CHAR_INTO_CAR_AS_PASSENGER catalina robber_quad[3] 0
			ENDIF
		ENDIF

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
		IF NOT IS_CAR_DEAD robber_quad[3]
			WARP_CHAR_INTO_CAR scplayer robber_quad[3]
		ENDIF

		// recreate robbers
		temp_int = 0															   
		WHILE temp_int < 4
			IF NOT IS_CAR_DEAD robber_quad[temp_int]
				IF temp_int = 0
					CREATE_CHAR PEDTYPE_MISSION1 CWMYFR 0.0 0.0 0.0 robber[temp_int]
				ENDIF
				IF temp_int = 1
					CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 0.0 0.0 0.0 robber[temp_int]
				ENDIF
				IF temp_int = 2
					CREATE_CHAR PEDTYPE_MISSION1 CWMYFR 0.0 0.0 0.0 robber[temp_int]
				ENDIF
				IF temp_int = 3
					CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 0.0 0.0 0.0 robber[temp_int]
				ENDIF
				SET_CHAR_ACCURACY robber[temp_int] 60
				GIVE_WEAPON_TO_CHAR robber[temp_int] WEAPONTYPE_PISTOL 99999
				TASK_TOGGLE_PED_THREAT_SCANNER robber[temp_int] FALSE FALSE FALSE
				SET_CHAR_DECISION_MAKER robber[temp_int] tough_dm
				SET_CHAR_PROOFS robber[temp_int] FALSE FALSE FALSE FALSE FALSE
				SET_ANIM_GROUP_FOR_CHAR robber[temp_int] MAN 
				SET_CHAR_SUFFERS_CRITICAL_HITS robber[temp_int] FALSE
				SET_CHAR_MAX_HEALTH robber[temp_int] 100
				SET_CHAR_HEALTH	robber[temp_int] 100
				
			ENDIF
		temp_int++
		ENDWHILE	
		
		// put robbers in their quads
		IF NOT IS_CAR_DEAD robber_quad[0]
			WARP_CHAR_INTO_CAR robber[0] robber_quad[0]
		ENDIF
		IF NOT IS_CAR_DEAD robber_quad[1]
			WARP_CHAR_INTO_CAR robber[1] robber_quad[1]
		ENDIF
		IF NOT IS_CAR_DEAD robber_quad[2]
			WARP_CHAR_INTO_CAR robber[2] robber_quad[2]
		ENDIF	
		
		// set quad settings
		temp_int = 0
		WHILE temp_int < 3
			SET_CAR_CRUISE_SPEED robber_quad[temp_int] 20.0
			SET_CAR_DRIVING_STYLE robber_quad[temp_int] DRIVINGMODE_AVOIDCARS
			SET_UPSIDEDOWN_CAR_NOT_DAMAGED robber_quad[temp_int] TRUE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER robber_quad[temp_int] TRUE
		temp_int++
		ENDWHILE	
				
		// set camera
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		// create dead robber
		SET_CHAR_COORDINATES robber[3] 257.1983 -72.6410 1.4697
		TASK_DEAD robber[3]

		MARK_CHAR_AS_NO_LONGER_NEEDED shop_keeper
		MARK_CHAR_AS_NO_LONGER_NEEDED customer
		MARK_CHAR_AS_NO_LONGER_NEEDED robber[3]

		MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
		MARK_MODEL_AS_NO_LONGER_NEEDED CWMYFR
		MARK_MODEL_AS_NO_LONGER_NEEDED CWMYHB1
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
		MARK_MODEL_AS_NO_LONGER_NEEDED HMOST
		MARK_MODEL_AS_NO_LONGER_NEEDED SWMOTR1

		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		IF NOT alarm_sound_flag = 1
			SET_MISSION_AUDIO_POSITION 3 251.5757 -62.9454 3.3786
			PLAY_MISSION_AUDIO 3
			alarm_sound_flag = 1
		ENDIF

		disable_group = 1


		IF NOT IS_GROUP_MEMBER catalina Players_Group
			IF DOES_BLIP_EXIST catalina_blip
				REMOVE_BLIP catalina_blip
			ENDIF
			MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
			SET_GROUP_MEMBER Players_Group catalina
			SET_CHAR_DROWNS_IN_WATER catalina FALSE
			in_group = 1
			left_cat_dialogue = 0
		ELSE
			in_group = 1
		ENDIF

		SET_RADIO_CHANNEL  RS_COUNTRY

		GOSUB CAT1_next_stage
	ENDIF

RETURN 


// *************************************************************************************************************
//											STAGE 2 - Quad bike chase  
// *************************************************************************************************************
cat1_m_stage_2:
		
	// initialisation for stage
	IF m_goals = 0

		SET_CREATE_RANDOM_GANG_MEMBERS FALSE

		GENERATE_RANDOM_INT_IN_RANGE 0 8 cat_they_are_getting_away_dialogue

		temp_int = 0
		WHILE temp_int < 3
			// set robber settings
			IF NOT IS_CHAR_DEAD robber[temp_int]
				ADD_BLIP_FOR_CHAR robber[temp_int] robber_blip[temp_int]
				SET_CHAR_MAX_HEALTH robber[temp_int] 200  
				SET_CHAR_HEALTH robber[temp_int] 200
				SET_CHAR_RELATIONSHIP robber[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
				SET_CHAR_RELATIONSHIP robber[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				TASK_TOGGLE_PED_THREAT_SCANNER robber[temp_int] 1 0 0
				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE robber[temp_int] KNOCKOFFBIKE_DEFAULT
			ENDIF
			// set robber car settings
			IF NOT IS_CAR_DEAD robber_quad[temp_int]
				SET_CAR_DRIVING_STYLE robber_quad[temp_int] DRIVINGMODE_AVOIDCARS
				SET_CAR_CRUISE_SPEED  robber_quad[temp_int] 25.0
				SET_CAR_FORWARD_SPEED robber_quad[temp_int] 5.0
			ENDIF
		temp_int++
		ENDWHILE
	
		// initialise flags
		chase_flag[0] = 0
		chase_flag[1] = 0
		chase_flag[2] = 0
		playback_paused[0] = 0
		playback_paused[1] = 0
		playback_paused[2] = 0
		master_speed = 1.05

		REQUEST_CAR_RECORDING 553
		REQUEST_CAR_RECORDING 554
		REQUEST_CAR_RECORDING 555
		//LOAD_MISSION_AUDIO 3 SOUND_PART_MISSION_COMPLETE //SOUND_PICKUP_STANDARD
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 553
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED	554
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED	555
		   	WAIT 0
		ENDWHILE 


		// start playback
		IF NOT IS_CAR_DEAD robber_quad[0]
			START_PLAYBACK_RECORDED_CAR_USING_AI robber_quad[0] 553
			SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[0] 1.0
			ADD_STUCK_CAR_CHECK robber_quad[0] 2.0 2000
		ENDIF

		IF NOT IS_CAR_DEAD robber_quad[1]
			START_PLAYBACK_RECORDED_CAR_USING_AI robber_quad[1] 554
			SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[1] 1.0
			ADD_STUCK_CAR_CHECK robber_quad[1] 2.0 2000
		ENDIF

		IF NOT IS_CAR_DEAD robber_quad[2]
			START_PLAYBACK_RECORDED_CAR_USING_AI robber_quad[2] 555
			SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[2] 1.0
			ADD_STUCK_CAR_CHECK robber_quad[2] 2.0 2000
		ENDIF  

		// setup catalina
		IF NOT IS_CHAR_DEAD catalina
			CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE group_dm EVENT_ACQUAINTANCE_PED_HATE
			SET_GROUP_DECISION_MAKER Players_Group group_dm
			SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
			SET_CHAR_DECISION_MAKER catalina catalina_dm 																			
			TASK_TOGGLE_PED_THREAT_SCANNER catalina 1 0 0
			ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE group_dm EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_USE_MEMBER_DECISION 0.0 100.0 0.0 0.0 TRUE FALSE
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE catalina_dm EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
			SET_CHAR_ACCURACY catalina 35
			SET_CHAR_SHOOT_RATE catalina 100
			SET_CHAR_DROWNS_IN_WATER catalina FALSE
		ENDIF

		//PRINT_NOW CAT136 3000 1 // CAT: 'Get those guys'
		cat1_pickups_collected = 1
		//DISPLAY_ONSCREEN_COUNTER_WITH_STRING cat1_pickups_collected COUNTER_DISPLAY_NUMBER CAT137

		// reset robbers flags
		robber_flag[0] = 0
		robber_flag[1] = 0
		robber_flag[2] = 0

		disable_group = 0

		GENERATE_RANDOM_INT_IN_RANGE 1 3 temp_int
		IF temp_int = 0
			$audio_string    = &CAT1_BA
			audio_sound_file = SOUND_CAT1_BA
			START_NEW_SCRIPT audio_line scplayer 0 1 1 0	
		ENDIF
		IF temp_int = 1
			$audio_string    = &CAT1_BB
			audio_sound_file = SOUND_CAT1_BB
			START_NEW_SCRIPT audio_line scplayer 0 1 1 0	
		ENDIF
		IF temp_int = 2
			$audio_string    = &CAT1_BC
			audio_sound_file = SOUND_CAT1_BC
			START_NEW_SCRIPT audio_line scplayer 0 1 1 0
		ENDIF

		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		m_goals++
	ENDIF
	
	// stage specific stuff
	IF m_goals = 1

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			master_speed += 0.1
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			master_speed += -0.1
			IF master_speed < 0.0
				master_Speed = 0.0
			ENDIF
		ENDIF
		
		
		// show catalinas gun --------------------------------------------------------------
		IF NOT IS_CHAR_DEAD catalina
			IF NOT IS_CAR_DEAD pcar
				IF IS_CHAR_IN_CAR catalina pcar
					IF show_gun = 0
						SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_MICRO_UZI
						show_gun = 1
					ENDIF
				ELSE
					show_gun = 0	
				ENDIF	
			ELSE
				show_gun = 0
			ENDIF
		ENDIF

		// update chase ai -----------------------------------------------------------------
		temp_int = 0
		WHILE temp_int < 3
			check_int = temp_int
			GOSUB cat1_adjust_playback_speed
			quad_playback_speed[temp_int] = playback_speed
		temp_int++
		ENDWHILE

		// check if robber has finished chase ---------------------------------------------
		temp_int = 0
		WHILE temp_int < 3
			IF NOT IS_CHAR_DEAD robber[temp_int]
				IF robber_flag[temp_int] = 0
					IF NOT IS_CAR_DEAD robber_quad[temp_int]
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[temp_int]
							robber_flag[temp_int]++

							// give char some behaviour
							FLUSH_ROUTE
							EXTEND_ROUTE 2346.2715 -1044.4469 52.6699
							EXTEND_ROUTE 2349.3369 -1043.6219 52.9884
							EXTEND_ROUTE 2354.3530 -1041.7982 53.1360
							EXTEND_ROUTE 2355.8435 -1039.7972 53.1562
							EXTEND_ROUTE 2355.5593 -1038.5750 53.3358

							OPEN_SEQUENCE_TASK temp_seq
								IF IS_CHAR_IN_CAR robber[temp_int] robber_quad[temp_int]
									TASK_LEAVE_CAR -1 robber_quad[temp_int]
								ENDIF
								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK robber[temp_int] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq

							SET_CHAR_ACCURACY robber[temp_int] 90
						ENDIF
					ENDIF
				ENDIF
				IF robber_flag[temp_int] = 1
					IF LOCATE_CHAR_ON_FOOT_3D robber[temp_int] 2355.5593 -1038.5750 53.3358 1.0 1.0 2.0 FALSE
						REMOVE_CHAR_ELEGANTLY robber[temp_int]
						robber_flag[temp_int] += 1
						GOSUB CAT1_next_stage // you fail!
					ENDIF	
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// if robber falls off -------------------------------------------------------------
		temp_int = 0
		WHILE temp_int < 3
			IF NOT IS_CHAR_DEAD robber[temp_int]
				IF robber_flag[temp_int] = 0
					IF NOT IS_CHAR_IN_ANY_CAR robber[temp_int] 
						GET_SCRIPT_TASK_STATUS robber[temp_int] PERFORM_SEQUENCE_TASK temp_int2
						IF temp_int2 = FINISHED_TASK
							// if the car is still alive
							IF NOT IS_CAR_DEAD robber_quad[temp_int]
								OPEN_SEQUENCE_TASK temp_seq
									TASK_GOTO_CAR -1 robber_quad[temp_int] 60000 5.0
									TASK_ENTER_CAR_AS_DRIVER -1 robber_quad[temp_int] 60000
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK robber[temp_int] temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
							ELSE
								
							ENDIF
						ELSE
							// warp char onto car if off screen
							IF NOT IS_CAR_DEAD robber_quad[temp_int]
								IF NOT IS_CAR_ON_SCREEN robber_quad[temp_int]
									IF NOT IS_CHAR_ON_SCREEN robber[temp_int]
										IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer robber[temp_int] 20.0 20.0 5.0 FALSE
											GET_DRIVER_OF_CAR robber_quad[temp_int] temp_int2
											IF temp_int2 = -1
												CLEAR_CHAR_TASKS_IMMEDIATELY robber[temp_int]
												WARP_CHAR_INTO_CAR robber[temp_int] robber_quad[temp_int] 
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
			IF NOT IS_CHAR_DEAD robber[1]
				IF NOT IS_CAR_DEAD robber_quad[1]
					IF IS_CHAR_IN_CAR robber[1] robber_quad[1]
						TASK_LEAVE_CAR_IMMEDIATELY robber[1] robber_quad[1]
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		// check player progress and adjust master speed ----------------------------------
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1397.2400 216.0877 50.0 50.0 FALSE
			IF NOT player_progress = 1
				master_speed += -0.05
				player_progress = 1
				IF NOT IS_CHAR_DEAD catalina
					SET_CHAR_ACCURACY catalina 50
				ENDIF
			ENDIF
		ENDIF	
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2711.1191 -53.5082 50.0 50.0 FALSE
			IF NOT player_progress = 2
				master_speed += 0.25
				player_progress = 2
				IF NOT IS_CHAR_DEAD catalina
					SET_CHAR_ACCURACY catalina 70
				ENDIF
			ENDIF
		ENDIF

		// create suitcase pickup if char dies -------------------------------------------
		temp_int = 0
		WHILE temp_int < 3 
			IF IS_CHAR_DEAD robber[temp_int]
				IF robber_pickup_been_created[temp_int] = 0
					IF robber_pickup[temp_int] = -1
						REMOVE_BLIP robber_blip[temp_int]
						robber_blip[temp_int] = 0
						GET_DEAD_CHAR_PICKUP_COORDS robber[temp_int] x y z
						CREATE_PICKUP BRIEFCASE PICKUP_ONCE x y z robber_pickup[temp_int]
						ADD_BLIP_FOR_COORD x y z briefcase_blip[temp_int]
						CHANGE_BLIP_COLOUR briefcase_blip[temp_int] GREEN
						briefcase_text_timer = 29000
						robber_pickup_been_created[temp_int] = 1
						MARK_CHAR_AS_NO_LONGER_NEEDED robber[temp_int]
						MARK_CAR_AS_NO_LONGER_NEEDED robber_quad[temp_int]
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// check if pickups have been picked up	-------------------------------------------
		temp_int = 0
		WHILE temp_int < 3	
			 IF DOES_PICKUP_EXIST	robber_pickup[temp_int]
				//WRITE_DEBUG_WITH_INT pickup_exists temp_int
				IF briefcase_text_timer > 30000
					PRINT_NOW CAT108 7000 1 // pick up briefcase
					briefcase_text_timer = 0
				ENDIF
				GET_PICKUP_COORDINATES robber_pickup[temp_int] X Y Z
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer x y z 1.0 1.0 2.0 FALSE
					REMOVE_BLIP briefcase_blip[temp_int]
					REMOVE_PICKUP robber_pickup[temp_int]
					robber_pickup[temp_int] = -1 
					CLEAR_PRINTS
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
					//PLAY_MISSION_AUDIO 3
					//WRITE_DEBUG pickup_collected1
					cat1_pickups_collected++
				ELSE
					IF HAS_PICKUP_BEEN_COLLECTED robber_pickup[temp_int]
						REMOVE_BLIP briefcase_blip[temp_int]
						REMOVE_PICKUP robber_pickup[temp_int]
						robber_pickup[temp_int] = -1
						CLEAR_PRINTS
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
						//PLAY_MISSION_AUDIO 3
						//WRITE_DEBUG pickup_collected2
						cat1_pickups_collected++
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// if catalina is dead mission fail	-----------------------------------------------
		IF IS_CHAR_DEAD catalina
			PRINT_NOW LOSE2 5000 1 // catalina is history
			m_failed = 1
		ENDIF	

		// check if player has lost all the robbers	---------------------------------------
		temp_int = 0
		IF NOT IS_CHAR_DEAD robber[0]
			IF quad_dist_from_player[0] < 300.0
			OR IS_CHAR_ON_SCREEN robber[0]
				temp_int = 1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD robber[1]
			IF quad_dist_from_player[1] < 300.0
			OR IS_CHAR_ON_SCREEN robber[1]
				temp_int = 1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD robber[2]
			IF quad_dist_from_player[2] < 300.0
			OR IS_CHAR_ON_SCREEN robber[2]	
				temp_int = 1
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD robber[0]
		AND IS_CHAR_DEAD robber[1]
		AND IS_CHAR_DEAD robber[2]
			temp_int = 1
		ENDIF
		IF temp_int = 0
			IF is_in_interior = 0
				GOSUB CAT1_next_stage
			ENDIF
			//m_failed = 1
		ENDIF


		GOSUB cat1_chase_dialogue

		// if player has collected all the money -------------------------------------------
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
			cat1_pickups_collected = 4
		ENDIF

		IF cat1_pickups_collected = 4
			//m_passed = 1
			//CLEAR_MISSION_AUDIO 3
			GOSUB CAT1_next_stage
			GOSUB CAT1_next_stage
			//m_goals = 99
		ENDIF

	ENDIF	
	
//	// exit
//	IF m_goals = 99
//		GOSUB CAT1_next_stage
//	ENDIF

RETURN


// *************************************************************************************************************
//								STAGE 3 - Mission Fail - show catalina pissed off  
// *************************************************************************************************************

cat1_m_stage_3:

	LVAR_INT cat1_visible_area

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	GET_AREA_VISIBLE cat1_visible_area

	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

    IF NOT cat1_visible_area = 0
        DO_FADE 250 FADE_OUT
        WHILE GET_FADING_STATUS
        	WAIT 0
        ENDWHILE
        IF NOT IS_CHAR_DEAD catalina
            SET_AREA_VISIBLE 0 
            FREEZE_CHAR_POSITION scplayer TRUE
            GET_CHAR_COORDINATES catalina x y z
            SET_CHAR_PROOFS catalina TRUE TRUE TRUE TRUE TRUE
            LOAD_SCENE x y z 
            DO_FADE 250 FADE_IN
        ENDIF
    ENDIF
		   
	//////////

   	IF NOT IS_CHAR_DEAD catalina
    	SET_CAMERA_IN_FRONT_OF_CHAR catalina
    	TASK_WANDER_STANDARD catalina
    	GET_CHAR_COORDINATES catalina x y z
   		CLEAR_AREA x y 100.0 100.0 FALSE   
	ENDIF   	

	//////////

	GENERATE_RANDOM_INT_IN_RANGE 0 10 temp_int
	SWITCH temp_int
		CASE 0
			$audio_string    = &CATX_UA
			audio_sound_file = SOUND_CATX_UA
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 1
			$audio_string    = &CATX_UB
			audio_sound_file = SOUND_CATX_UB
			START_NEW_SCRIPT audio_line catalina 0 1 1 0					
		BREAK
		CASE 2
			$audio_string    = &CATX_UC
			audio_sound_file = SOUND_CATX_UC
			START_NEW_SCRIPT audio_line catalina 0 1 1 0						
		BREAK
		CASE 3
			$audio_string    = &CATX_UD
			audio_sound_file = SOUND_CATX_UD
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 4
			$audio_string    = &CATX_UE
			audio_sound_file = SOUND_CATX_UE
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 5
			$audio_string    = &CATX_UF
			audio_sound_file = SOUND_CATX_UF
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 6
			$audio_string    = &CATX_UG
			audio_sound_file = SOUND_CATX_UG
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 7
			$audio_string    = &CATX_UH
			audio_sound_file = SOUND_CATX_UH
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 8
			$audio_string    = &CATX_UJ
			audio_sound_file = SOUND_CATX_UJ
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
		CASE 9
			$audio_string    = &CATX_UK
			audio_sound_file = SOUND_CATX_UK
			START_NEW_SCRIPT audio_line catalina 0 1 1 0
		BREAK
	ENDSWITCH

	WAIT 100

	WHILE NOT audio_line_is_active = 0
		WAIT 0
	ENDWHILE

	WAIT 500

	/////////

    IF NOT cat1_visible_area = 0
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SET_AREA_VISIBLE cat1_visible_area
		FREEZE_CHAR_POSITION scplayer FALSE
		GET_CHAR_COORDINATES scplayer x y z
		LOAD_SCENE x y z
		DO_FADE 250 FADE_IN
    ENDIF

    /////////                

    SWITCH_WIDESCREEN OFF
    SET_PLAYER_CONTROL PLAYER1 ON
    RESTORE_CAMERA_JUMPCUT
    SET_CAMERA_BEHIND_PLAYER 
    DELETE_CHAR catalina

	PRINT_NOW LOSE 5000 1 // you lost the robbers!
	m_failed = 1

RETURN


// *************************************************************************************************************
//								STAGE 4 - Mission has passed take catalina back to hideout  
// *************************************************************************************************************

cat1_m_stage_4:

	// print mission failed message
	IF m_goals = 0
		
		SET_CREATE_RANDOM_GANG_MEMBERS TRUE

		ADD_BLIP_FOR_COORD catX[0] catY[0] catZ[0] location_blip

		PRINT_NOW CAT112 7000 1 //~s~Take ~b~Catalina~s~ back to the ~y~hideout~s~.

		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		//TIMERA = 6000

		conversation_home =  0
		cat1_take_home_dialogue = 0

		m_goals++
	ENDIF

	// wait for player to arrive at hideout 
	IF m_goals = 1

		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		//AND TIMERA > 7000
			IF cat1_take_home_dialogue = 0
				GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0	
					$audio_string    = &CAT1_FA
					audio_sound_file = SOUND_CAT1_FA
					START_NEW_SCRIPT audio_line catalina 0 0 1 0
				ELSE
					$audio_string    = &CAT1_FB
					audio_sound_file = SOUND_CAT1_FB
					START_NEW_SCRIPT audio_line catalina 0 0 1 0
				ENDIF
				dialogue_flag = 0
				conversation_home = 1
				cat1_take_home_dialogue++
				TIMERA = -5000
			ENDIF
		ENDIF
					
//		WRITE_DEBUG_WITH_INT cat1_take_home_dialogue cat1_take_home_dialogue
//		WRITE_DEBUG_WITH_INT audio_line_is_active audio_line_is_active
			
		
		LVAR_INT conversation_home
		IF conversation_home = 1
			IF NOT IS_MESSAGE_BEING_DISPLAYED
			AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED	  
			AND TIMERA > 1000

				IF NOT IS_CHAR_DEAD catalina
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer catalina 10.0 10.0 5.0 FALSE
						IF audio_line_is_active = 0
							IF cat_getaway_dialogue = 0
								SWITCH dialogue_flag 
									CASE 0
										IF cat_counter = 0
											$audio_string    = &CAT_DA
											audio_sound_file = SOUND_CAT_DA
											START_NEW_SCRIPT audio_line catalina 0 1 1 0   // I have a hideout on Fern Ridge.
										ENDIF
									BREAK
									CASE 1		
										IF cat_counter = 0
											// figure out what to say next (north, west , east)
											IF NOT IS_CHAR_DEAD catalina
												GET_CHAR_COORDINATES catalina x y z
											ENDIF

											// what dist is biggest
											temp_float  = catY[0] - y  // north distance
											temp_float2 = catX[0] - x  // east/west distance (+ve east, -ve west)
											
											// is north the biggest
											IF temp_float2 < 0.0
												temp_float3 = -1.0 * temp_float2
											ELSE
												temp_float3 = temp_float2
											ENDIF
											IF temp_float > temp_float3
												$audio_string    = &CAT_DD
										   		audio_sound_file = SOUND_CAT_DD
												START_NEW_SCRIPT audio_line catalina 0 1 1 0  // it's north of here 
											ELSE
												IF temp_float2 > 0.0 
													$audio_string    = &CAT_DC
										   			audio_sound_file = SOUND_CAT_DC
													START_NEW_SCRIPT audio_line catalina 0 1 1 0  // it's east of here	
												ELSE
													$audio_string    = &CAT_DB
										   			audio_sound_file = SOUND_CAT_DB
													START_NEW_SCRIPT audio_line catalina 0 1 1 0  // it's west of here
												ENDIF
											ENDIF
											//CLEAR_MISSION_AUDIO 2
											//LOAD_MISSION_AUDIO 2 SOUND_CAT_DE
										ENDIF
									BREAK
									CASE 2
										IF cat_counter = 0
										 	$audio_string    = &CAT_DE
											audio_sound_file = SOUND_CAT_DE
											START_NEW_SCRIPT audio_line catalina 0 1 2 0   // now drive 
											CLEAR_MISSION_AUDIO 1
											LOAD_MISSION_AUDIO 1 SOUND_CAT_DF 
										ENDIF
									BREAK
									CASE 3
									 	$audio_string    = &CAT_DF
										audio_sound_file = SOUND_CAT_DF
										START_NEW_SCRIPT audio_line scplayer 0 1 1 0   // You’re fucking psycho! 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_DG 
									BREAK
									CASE 4
									 	$audio_string    = &CAT_DG
										audio_sound_file = SOUND_CAT_DG
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // All you little men are scared of strong women!
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_DH 
									BREAK
									CASE 5
									 	$audio_string    = &CAT_DH
										audio_sound_file = SOUND_CAT_DH
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // If we’re passionate you say we crazy. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_DI 
									BREAK
									CASE 6
									 	$audio_string    = &CAT_DI
										audio_sound_file = SOUND_CAT_DI
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // If we’re upset you say we hysterical. 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_DJ 
									BREAK
									CASE 8
									 	$audio_string    = &CAT_DJ
										audio_sound_file = SOUND_CAT_DJ
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // We sleep with men, we’re sluts. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_DK 
									BREAK
									CASE 9
									 	$audio_string    = &CAT_DK
										audio_sound_file = SOUND_CAT_DK
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // If we don’t put out we’re frigid bitches. 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_DL 
									BREAK
									CASE 10
									 	$audio_string    = &CAT_DL
										audio_sound_file = SOUND_CAT_DL
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // Who you calling little men? You went berserk back there! 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_DM 
									BREAK
									CASE 11
									 	$audio_string    = &CAT_DM
										audio_sound_file = SOUND_CAT_DM
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // That? That was just another day at the office! 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_DN 
									BREAK
									CASE 12
									 	$audio_string    = &CAT_DN
										audio_sound_file = SOUND_CAT_DN
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // You can’t stand the heat, go put your tiny balls in the freezer! 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_DO 
									BREAK
									CASE 13
									 	$audio_string    = &CAT_DO
										audio_sound_file = SOUND_CAT_DO
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // Tiny balls? Now just wait a minute- 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_DP 
									BREAK
									CASE 14
									 	$audio_string    = &CAT_DP
										audio_sound_file = SOUND_CAT_DP
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Enough! Just shut up and drive, I’m counting the fucking money! 
									BREAK
									CASE 15
										conversation_home = 0
									BREAK
								ENDSWITCH
								dialogue_flag++
								TIMERA = 0
							ENDIF
						

							IF cat_getaway_dialogue = 1
								SWITCH dialogue_flag 
									CASE 0
										$audio_string    = &CAT_GA
										audio_sound_file = SOUND_CAT_GA
										START_NEW_SCRIPT audio_line catalina 0 1 2 0   // Take me home, Carl.
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GB
									BREAK
									CASE 1
										$audio_string    = &CAT_GB
										audio_sound_file = SOUND_CAT_GB
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // Okay. Look, but we gotta talk about something. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GC
									BREAK
									CASE 2
									 	$audio_string    = &CAT_GC
										audio_sound_file = SOUND_CAT_GC
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // What? What do I have to say to you? 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GD 
									BREAK
									CASE 3
									 	$audio_string    = &CAT_GD
										audio_sound_file = SOUND_CAT_GD
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // You’re a great girl, and all, but you gotta calm down. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GE 
									BREAK
									CASE 4
									 	$audio_string    = &CAT_GE
										audio_sound_file = SOUND_CAT_GE
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // I know some cold blooded cats wouldn’t act like you.
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GF 
									BREAK
									CASE 5
									 	$audio_string    = &CAT_GF
										audio_sound_file = SOUND_CAT_GF
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Oh, you get given a lioness and you want a pussy cat? Wimp! 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GG 
									BREAK
									CASE 6
									 	$audio_string    = &CAT_GG
										audio_sound_file = SOUND_CAT_GG
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // Nah, I just wanna easy life 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GH 
									BREAK
									CASE 7
									 	$audio_string    = &CAT_GH
										audio_sound_file = SOUND_CAT_GH
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // You know why I act like this. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GI 
									BREAK
									CASE 8
									 	$audio_string    = &CAT_GI
										audio_sound_file = SOUND_CAT_GI
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // NO
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GJ 
									BREAK
									CASE 9
									 	$audio_string    = &CAT_GJ
										audio_sound_file = SOUND_CAT_GJ
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // I’m in love, Carl. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GK 
									BREAK
									CASE 10
									 	$audio_string    = &CAT_GK
										audio_sound_file = SOUND_CAT_GK
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // A woman’s heart is a tempestuous place, 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GL 
									BREAK
									CASE 11
									 	$audio_string    = &CAT_GL
										audio_sound_file = SOUND_CAT_GL
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // and you will break my heart. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_GM 
									BREAK
									CASE 12
									 	$audio_string    = &CAT_GM
										audio_sound_file = SOUND_CAT_GM
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // Sometimes I want to kill us both! 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_GN 
									BREAK
									CASE 13
									 	$audio_string    = &CAT_GN
										audio_sound_file = SOUND_CAT_GN
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // Please, don’t do that. Just relax a little.- 

									BREAK
									CASE 14
									 	conversation_home = 0
									BREAK
								ENDSWITCH
								dialogue_flag++
								TIMERA = 0
							ENDIF



									
									
//							IF cat_getaway_dialogue = 2
//								SWITCH dialogue_flag 
//									CASE 0
//										$audio_string    = &CAT_LA
//										audio_sound_file = SOUND_CAT_LA
//										START_NEW_SCRIPT audio_line scplayer 0 1 2 0   // You a lunatic, bitch?
//										CLEAR_MISSION_AUDIO 1
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_LB
//									BREAK
//									CASE 1
//										$audio_string    = &CAT_LB
//										audio_sound_file = SOUND_CAT_LB
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Shut the fuck up. Home! Wimp! 
//  									BREAK
//									CASE 3
//									 	conversation_home = 0
//									BREAK
//								ENDSWITCH
//								dialogue_flag++
//								TIMERA = 0
//							ENDIF													
//				
	
							IF cat_getaway_dialogue = 2
								SWITCH dialogue_flag 
									CASE 0
										$audio_string    = &CAT_OA
										audio_sound_file = SOUND_CAT_OA
										START_NEW_SCRIPT audio_line catalina 0 1 2 0   // Take me home now, big man.										
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_OB
									BREAK
									CASE 1
										$audio_string    = &CAT_OB
										audio_sound_file = SOUND_CAT_OB
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // How we do? 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_OC
									BREAK
									CASE 2
									 	$audio_string    = &CAT_OC
										audio_sound_file = SOUND_CAT_OC
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // Is that all you care about? Money? 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_OD 
									BREAK
									CASE 3
									 	$audio_string    = &CAT_OD
										audio_sound_file = SOUND_CAT_OD
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // No, but I really need the paper--. 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_OE 
									BREAK
									CASE 4
									 	$audio_string    = &CAT_OE
										audio_sound_file = SOUND_CAT_OE
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // You revolt me. You make my skin crawl.
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_OF 
									BREAK
									CASE 5
									 	$audio_string    = &CAT_OF
										audio_sound_file = SOUND_CAT_OF
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // Well I ain’t crazy about you, either! 
										CLEAR_MISSION_AUDIO 2
										LOAD_MISSION_AUDIO 2 SOUND_CAT_OG 
									BREAK
									CASE 6
									 	$audio_string    = &CAT_OG
										audio_sound_file = SOUND_CAT_OG
										START_NEW_SCRIPT audio_line catalina 0 1 2 1   // That’s just it. How little you know. 
										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_CAT_OH 
									BREAK
									CASE 7
									 	$audio_string    = &CAT_OH
										audio_sound_file = SOUND_CAT_OH
										START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Don’t talk. Let us enjoy the peace and quiet. 
									BREAK
									CASE 14
									 	conversation_home = 0
									BREAK
								ENDSWITCH
								dialogue_flag++
								TIMERA = 0
							ENDIF
																						
																						
						ENDIF															
					ENDIF																
				ENDIF																	
			ENDIF																		
		ENDIF																			
																						
																						
																						
		IF conversation_home = 0														
			IF NOT IS_MESSAGE_BEING_DISPLAYED											
			AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED										
			AND TIMERA > 30000															
																						
					GENERATE_RANDOM_INT_IN_RANGE 0 16 temp_int							
																						
					SWITCH temp_int														
																						
						CASE 0 															
							$audio_string = &CATX_VA									
							audio_sound_file = SOUND_CATX_VA							
						BREAK															
						CASE 1 															
							$audio_string = &CATX_VB									
							audio_sound_file = SOUND_CATX_VB							
						BREAK															
						CASE 2 															
							$audio_string = &CATX_VC									
							audio_sound_file = SOUND_CATX_VC							
						BREAK															
						CASE 3 															
							$audio_string = &CATX_VD									
							audio_sound_file = SOUND_CATX_VD							
						BREAK															
						CASE 4 															
							$audio_string = &CATX_VE									
							audio_sound_file = SOUND_CATX_VE							
						BREAK
						CASE 5 
							$audio_string = &CATX_VF
							audio_sound_file = SOUND_CATX_VF
						BREAK
						CASE 6 
							$audio_string = &CATX_VG
							audio_sound_file = SOUND_CATX_VG
						BREAK
						CASE 7 
							$audio_string = &CATX_VH
							audio_sound_file = SOUND_CATX_VH
						BREAK
						CASE 8 
							$audio_string = &CATX_VJ
							audio_sound_file = SOUND_CATX_VJ
						BREAK
						CASE 9 
							$audio_string = &CATX_VK
							audio_sound_file = SOUND_CATX_VK
						BREAK
						CASE 10 
							IF NOT IS_CHAR_IN_ANY_CAR scplayer
								$audio_string = &CATX_VK
								audio_sound_file = SOUND_CATX_VK
							ELSE
								$audio_string = &CATX_VL
								audio_sound_file = SOUND_CATX_VL
							ENDIF
						BREAK
						CASE 11 
							$audio_string = &CATX_VM
							audio_sound_file = SOUND_CATX_VM
						BREAK
						CASE 12 
							$audio_string = &CATX_VN
							audio_sound_file = SOUND_CATX_VN
						BREAK
						CASE 13 
							$audio_string = &CATX_VO
							audio_sound_file = SOUND_CATX_VO
						BREAK
						CASE 14
							IF NOT IS_CHAR_IN_ANY_CAR scplayer
								$audio_string = &CATX_VO
								audio_sound_file = SOUND_CATX_VO
							ELSE 
								$audio_string = &CATX_VP
								audio_sound_file = SOUND_CATX_VP
							ENDIF
						BREAK
						CASE 15 
							$audio_string = &CATX_VQ
							audio_sound_file = SOUND_CATX_VQ
						BREAK

					ENDSWITCH

					START_NEW_SCRIPT audio_line catalina 0 0 1 0
					TIMERA = 0
			ENDIF
		ENDIF

	
		IF in_group = 1
			
			IF NOT DOES_BLIP_EXIST location_blip
				m_goals = 0
			ELSE
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer catX[0] catY[0] catZ[0] 3.0 3.0 3.0 TRUE

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF IS_VEHICLE_ON_ALL_WHEELS car
							//TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
							SET_PLAYER_CONTROL player1 OFF
							m_goals++
						ENDIF
					ELSE
						m_goals++	
					ENDIF

					
				ENDIF
			ENDIF

		ELSE
			REMOVE_BLIP location_blip
		ENDIF

	ENDIF


	// wait for car to stop
	IF m_goals = 2
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
			IF IS_CAR_STOPPED car
				m_goals++
			ENDIF
		ELSE
			m_goals++
		ENDIF 
	ENDIF


	// small cut of catalina walking into cabin
	IF m_goals = 3

		SET_PLAYER_CONTROL player1 FALSE

		SET_PED_DENSITY_MULTIPLIER 0.0
		SET_CAR_DENSITY_MULTIPLIER 0.0
		SWITCH_WIDESCREEN ON

		CLEAR_AREA catX[0] catY[0] catZ[0] 20.0 TRUE

		SET_FIXED_CAMERA_POSITION 856.0245 -29.9962 63.9179 0.0 0.0 0.0 
		POINT_CAMERA_AT_POINT 856.9926 -29.7496 63.8739	JUMP_CUT

		IF NOT IS_CHAR_DEAD catalina
			IF NOT IS_CHAR_IN_ANY_CAR catalina
				IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina catX[0] catY[0] catZ[0] 5.0 5.0 5.0 FALSE
					SET_CHAR_COORDINATES catalina 868.8380 -28.2257 62.1875 
					SET_CHAR_HEADING catalina 161.4460
				ENDIF
			ENDIF
			OPEN_SEQUENCE_TASK temp_seq
				IF IS_CHAR_IN_ANY_CAR catalina
					STORE_CAR_CHAR_IS_IN_NO_SAVE catalina car
					TASK_LEAVE_CAR -1 car
				ENDIF  
				TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
				TASK_LOOK_AT_CHAR -1 scplayer 99999
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK catalina temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		
		GENERATE_RANDOM_INT_IN_RANGE 0 10 temp_int

		TIMERA = 0


		LVAR_INT last_cutscene
		last_cutscene = 0
		dialogue_flag = 0
		dont_give_catalina_end_bit = 0

		m_goals++
	ENDIF

	// do last bit of dialogue
	IF m_goals = 4

		IF last_cutscene = 0

			IF audio_line_is_active = 0
			AND TIMERA > 1000
				IF cat_counter = 0
					SWITCH dialogue_flag 
						CASE 0
							$audio_string    = &CAT_EA
							audio_sound_file = SOUND_CAT_EA
							START_NEW_SCRIPT audio_line catalina 0 1 2 0   // That was fun, homeboy.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_EB
						BREAK
						CASE 1
							$audio_string    = &CAT_EB
				   			audio_sound_file = SOUND_CAT_EB
							START_NEW_SCRIPT audio_line catalina 0 1 1 1  // You wanna play again tomorrow?
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EC
						BREAK
						CASE 2
						 	$audio_string    = &CAT_EC
							audio_sound_file = SOUND_CAT_EC
							START_NEW_SCRIPT audio_line catalina 0 1 2 1   // Here. This is your cut. 
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_ED 
						BREAK
						CASE 3
						 	$audio_string    = &CAT_ED
							audio_sound_file = SOUND_CAT_ED
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // Lady, I gotta take it easy. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EE 
						BREAK
						CASE 4
						 	$audio_string    = &CAT_EE
							audio_sound_file = SOUND_CAT_EE
							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // Like you said, I got some other shit going on.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_EF 
						BREAK
						CASE 5
						 	$audio_string    = &CAT_EF
							audio_sound_file = SOUND_CAT_EF
							START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Whatever, baby. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EG 
						BREAK
						CASE 6
						 	$audio_string    = &CAT_EG
							audio_sound_file = SOUND_CAT_EG
							START_NEW_SCRIPT audio_line catalina 0 1 2 1   // If I play too rough, you do whatever makes you feel safe.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_EH 
						BREAK
						CASE 7
						 	$audio_string    = &CAT_EH
							audio_sound_file = SOUND_CAT_EH
							START_NEW_SCRIPT audio_line catalina 0 1 1 1   // (maracon). See you around. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EI 
						BREAK
						CASE 8
						 	$audio_string    = &CAT_EI
							audio_sound_file = SOUND_CAT_EI
							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // Thanks and everything bitch, but you crazy.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_EJ 
						BREAK
						CASE 9
						 	$audio_string    = &CAT_EJ
							audio_sound_file = SOUND_CAT_EJ
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // If you was the last bitch on earth, 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EK 
						BREAK
						CASE 10
						 	$audio_string    = &CAT_EK
							audio_sound_file = SOUND_CAT_EK
							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // I’d start going out with an animal.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_EL 
						BREAK
						CASE 11
						 	$audio_string    = &CAT_EL
							audio_sound_file = SOUND_CAT_EL
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // You’re too fucking loco, senorita. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_EM 

							// make catalina walk into cabin
							dont_give_catalina_end_bit = 1
							IF NOT IS_CHAR_DEAD catalina
								CLEAR_LOOK_AT catalina
								OPEN_SEQUENCE_TASK temp_seq
									TASK_GO_STRAIGHT_TO_COORD -1 869.0271 -28.2591 62.1893 PEDMOVE_WALK	20000
									TASK_GO_STRAIGHT_TO_COORD -1 870.2891 -25.0424 62.9858 PEDMOVE_WALK	5000
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK catalina temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
							ENDIF


						BREAK

						// ---   voice over bit   ---
						CASE 12

							//WAIT 2000

//							SET_CAMERA_IN_FRONT_OF_PLAYER 
//							RESTORE_CAMERA_JUMPCUT
//							IF DOES_CHAR_EXIST catalina
//								DELETE_CHAR catalina
//							ENDIF

							dialogue_flag  = 16
							last_cutscene = 1
//						 	$audio_string    = &CAT_EM
//							audio_sound_file = SOUND_CAT_EM
//							START_NEW_SCRIPT audio_line -1 0 1 2 1   // Whatever else went down, 
//							CLEAR_MISSION_AUDIO 1
//							LOAD_MISSION_AUDIO 1 SOUND_CAT_EN 
						BREAK
						CASE 13
//						 	$audio_string    = &CAT_EN 
//							audio_sound_file = SOUND_CAT_EN 
//							START_NEW_SCRIPT audio_line -1 0 1 1 1   // I knew I would do anything I could to avoid 
//							CLEAR_MISSION_AUDIO 2
//							LOAD_MISSION_AUDIO 2 SOUND_CAT_EO 
						BREAK
						CASE 14
//						 	$audio_string    = &CAT_EO
//							audio_sound_file = SOUND_CAT_EO
//							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // hanging out with that crazy bitch. 
						BREAK
						CASE 15
							last_cutscene = 1
						BREAK
					ENDSWITCH
					dialogue_flag++
					TIMERA = 0
				ENDIF


				IF cat_counter = 1
					SWITCH dialogue_flag 
						CASE 0
							$audio_string    = &CAT_HA
							audio_sound_file = SOUND_CAT_HA
							START_NEW_SCRIPT audio_line catalina 0 1 2 0   // See you soon, handsome. Next time, we play REALLY rough.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_HB
						BREAK
						CASE 1
							$audio_string    = &CAT_HB
				   			audio_sound_file = SOUND_CAT_HB
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1  // Yeah, that's cool. But we can also play for some real money
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_HC
						BREAK
						CASE 2
						 	$audio_string    = &CAT_HC
							audio_sound_file = SOUND_CAT_HC
							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // I gotta get some money to… well 
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_HD 
						BREAK
						CASE 3
						 	$audio_string    = &CAT_HD
							audio_sound_file = SOUND_CAT_HD
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1   // it’s a long story,but I need some serious paper, fast. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_HE 
						BREAK
						CASE 4
						 	$audio_string    = &CAT_HE
							audio_sound_file = SOUND_CAT_HE
							START_NEW_SCRIPT audio_line catalina 0 1 2 1   // You come and see me again soon.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_HF 
						BREAK
						CASE 5
						 	$audio_string    = &CAT_HF
							audio_sound_file = SOUND_CAT_HF
							START_NEW_SCRIPT audio_line catalina 0 1 1 1   // We rob a real bank.
						BREAK
						CASE 6
							last_cutscene = 1
						BREAK
					ENDSWITCH
					dialogue_flag++
					TIMERA = 0
				ENDIF

				
				IF cat_counter = 2
					SWITCH dialogue_flag 
						CASE 0
							$audio_string    = &CAT_MA
							audio_sound_file = SOUND_CAT_MA
							START_NEW_SCRIPT audio_line catalina 0 1 2 0   // Carl, you have much to learn about the female heart. 
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_MB
						BREAK
						CASE 1
							$audio_string    = &CAT_MB
				   			audio_sound_file = SOUND_CAT_MB
							START_NEW_SCRIPT audio_line catalina 0 1 1 1  // Come and see me again when you think you understand it.
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_MC
						BREAK
						CASE 2
						 	$audio_string    = &CAT_MC
							audio_sound_file = SOUND_CAT_MC
							START_NEW_SCRIPT audio_line catalina 0 1 2 1   // Goodbye! 
						BREAK
						CASE 3
							last_cutscene = 1
						BREAK
					ENDSWITCH
					dialogue_flag++
					TIMERA = 0
				ENDIF

				IF cat_counter = 3
					SWITCH dialogue_flag 
						CASE 0
							$audio_string    = &CAT_PA
							audio_sound_file = SOUND_CAT_PA
							START_NEW_SCRIPT audio_line catalina 0 1 2 0   // There you go.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_PB
						BREAK
						CASE 1
							$audio_string    = &CAT_PB
				   			audio_sound_file = SOUND_CAT_PB
							START_NEW_SCRIPT audio_line scplayer 0 1 1 1  // Thanks
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_PC
						BREAK
						CASE 2
						 	$audio_string    = &CAT_PC
							audio_sound_file = SOUND_CAT_PC
							START_NEW_SCRIPT audio_line scplayer 0 1 2 1   // One day, Carl Johnson, you will realise. 
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_PD 
						BREAK
						CASE 3
						 	$audio_string    = &CAT_PD
							audio_sound_file = SOUND_CAT_PD
							START_NEW_SCRIPT audio_line catalina 0 1 1 1   // She, she truly loved me, and your heart will break in two. 
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_CAT_PE 
						BREAK
						CASE 4
						 	$audio_string    = &CAT_PE
							audio_sound_file = SOUND_CAT_PE
							START_NEW_SCRIPT audio_line catalina 0 1 2 1   // But you are more like the spiney lizard than a man.
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_CAT_PF 
						BREAK
						CASE 5
						 	$audio_string    = &CAT_PF
							audio_sound_file = SOUND_CAT_PF
							START_NEW_SCRIPT audio_line catalina 0 1 1 1   // Goodbye!
						BREAK
						CASE 6
							last_cutscene = 1
						BREAK
					ENDSWITCH
					dialogue_flag++
					TIMERA = 0
				ENDIF
	

			ENDIF




		ELSE
			IF NOT IS_CHAR_DEAD catalina
				
				LVAR_INT dont_give_catalina_end_bit

				IF dont_give_catalina_end_bit = 0
					CLEAR_LOOK_AT catalina
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 869.0271 -28.2591 62.1893 PEDMOVE_WALK	20000
						TASK_GO_STRAIGHT_TO_COORD -1 870.2891 -25.0424 62.9858 PEDMOVE_WALK	5000
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK catalina temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			ENDIF		
			m_goals++
		ENDIF

	ENDIF

	// wait to finish
	IF m_goals = 5
		
		IF NOT IS_CHAR_DEAD catalina
			GET_SCRIPT_TASK_STATUS catalina PERFORM_SEQUENCE_TASK temp_int
			IF temp_int = FINISHED_TASK
				temp_int = 1
			ELSE
				temp_int = 0
			ENDIF
		ENDIF
		IF TIMERA > 10000
			temp_int = 1
		ENDIF

		IF temp_int = 1 

//			DO_FADE 500 FADE_OUT
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE
			
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			IF DOES_CHAR_EXIST catalina
				DELETE_CHAR catalina
			ENDIF
			SET_PLAYER_CONTROL player1 TRUE
			SWITCH_WIDESCREEN OFF

//			DO_FADE 500 FADE_IN
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE

			m_passed = 1

		ENDIF
	ENDIF

RETURN



// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
cat1_global_functions:

	// if player goes into an interior pause the robbers ------
	GET_AREA_VISIBLE temp_int
	IF NOT temp_int = 0
		IF is_in_interior = 0
			stored_master_speed = master_speed
			master_speed = 0.0
			is_in_interior = 1
		ENDIF
	ELSE
		IF is_in_interior = 1
			master_speed = stored_master_speed
			is_in_interior = 0
		ENDIF	
	ENDIF


	// store players car ----------------------------------
	IF IS_CHAR_IN_ANY_CAR scplayer
		IF NOT IS_CAR_DEAD pcar
			IF NOT IS_CHAR_IN_CAR scplayer pcar
				// check the last pcar is not a mission car before marking it as no longer needed
				temp_int = 0
				temp_int2 = 0
				WHILE temp_int < 4
					IF pcar = robber_quad[temp_int]
						temp_int2 = 1
						temp_int = 4
					ENDIF
				temp_int++
				ENDWHILE
				IF temp_int2 = 1
					MARK_CAR_AS_NO_LONGER_NEEDED pcar
				ENDIF
				STORE_CAR_CHAR_IS_IN scplayer pcar
			ENDIF
		ELSE
			STORE_CAR_CHAR_IS_IN scplayer pcar
		ENDIF
	ELSE
		// pcar = 0	 // will clear pcar (variable only) if player leaves
	ENDIF


	// check if catalina is separated from group ----------
	IF NOT IS_CHAR_DEAD catalina
		IF NOT IS_MESSAGE_BEING_DISPLAYED 
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND is_in_interior = 0
			IF disable_group = 0
				IF in_group = 1
					IF NOT IS_GROUP_MEMBER catalina Players_Group
						PRINT LEFTC 4000 1 // you've left catalina!
						IF NOT DOES_BLIP_EXIST catalina_blip
							ADD_BLIP_FOR_CHAR catalina catalina_blip
							SET_BLIP_AS_FRIENDLY catalina_blip TRUE
						ENDIF
						left_cat_dialogue = 1
						dialogue_timer = 0
						in_group = 0
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D catalina scplayer 10.0 10.0 10.0 FALSE
						IF NOT IS_GROUP_MEMBER catalina Players_Group
							IF DOES_BLIP_EXIST catalina_blip
								REMOVE_BLIP catalina_blip
							ENDIF
							MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
							SET_GROUP_MEMBER Players_Group catalina
							SET_CHAR_DROWNS_IN_WATER catalina FALSE
							in_group = 1
							left_cat_dialogue = 0
						ELSE
							in_group = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD catalina
		IF NOT IS_CHAR_IN_ANY_CAR catalina
			IF NOT DOES_BLIP_EXIST catalina_blip
				ADD_BLIP_FOR_CHAR catalina catalina_blip
				SET_BLIP_AS_FRIENDLY catalina_blip TRUE
			ENDIF
		ELSE
			IF DOES_BLIP_EXIST catalina_blip
				STORE_CAR_CHAR_IS_IN_NO_SAVE catalina car
				IF IS_CHAR_IN_CAR scplayer car
					REMOVE_BLIP catalina_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST catalina
		IF IS_CHAR_DEAD catalina
			PRINT_NOW LOSE2 5000 1 // catalina is history
			m_failed = 1
		ENDIF	
	ENDIF

	// check if catalina is damaged by player
	LVAR_INT hit_catalina_dialogue
	IF is_in_interior = 0
		IF NOT IS_CHAR_DEAD catalina
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR catalina scplayer
			AND audio_line_is_active = 0	
			 			
				hit_catalina_dialogue++
				IF hit_catalina_dialogue > 9
					hit_catalina_dialogue = 0
				ENDIF			
																						
				SWITCH hit_catalina_dialogue														
																					
					CASE 0 															
						$audio_string = &CATX_UA									
						audio_sound_file = SOUND_CATX_UA							
					BREAK															
					CASE 1 															
						$audio_string = &CATX_UB									
						audio_sound_file = SOUND_CATX_UB							
					BREAK															
					CASE 2 															
						$audio_string = &CATX_UC									
						audio_sound_file = SOUND_CATX_UC							
					BREAK														   
					CASE 3 														   
						$audio_string = &CATX_UD								   
						audio_sound_file = SOUND_CATX_UD						   
					BREAK														   
					CASE 4 														   
						$audio_string = &CATX_UE								   
						audio_sound_file = SOUND_CATX_UE						   
					BREAK
					CASE 5 
						$audio_string = &CATX_UF
						audio_sound_file = SOUND_CATX_UF
					BREAK
					CASE 6 
						$audio_string = &CATX_UG
						audio_sound_file = SOUND_CATX_UG
					BREAK
					CASE 7 
						$audio_string = &CATX_UH
						audio_sound_file = SOUND_CATX_UH
					BREAK
					CASE 8 
						$audio_string = &CATX_UJ
						audio_sound_file = SOUND_CATX_UJ
					BREAK
					CASE 9 
						$audio_string = &CATX_UK
						audio_sound_file = SOUND_CATX_UK
					BREAK

				ENDSWITCH

				START_NEW_SCRIPT audio_line catalina 0 0 1 0
				CLEAR_CHAR_LAST_DAMAGE_ENTITY catalina

			ENDIF
		ENDIF
	ENDIF


	// if player's car is on fire
	LVAR_INT catalina_car_on_fire_dialogue
//	IF IS_CHAR_IN_ANY_CAR scplayer
//		STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
//		IF NOT IS_CHAR_DEAD catalina
//			IF IS_CHAR_IN_CAR catalina car
//				IF IS_CAR_ON_FIRE car
//					IF NOT IS_MESSAGE_BEING_DISPLAYED 
//					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
//					AND audio_line_is_active = 0
//						
//						catalina_car_on_fire_dialogue++
//						IF catalina_car_on_fire_dialogue > 3
//							catalina_car_on_fire_dialogue = 0
//						ENDIF
//						
//						SWITCH catalina_car_on_fire_dialogue
//							CASE 0
//								$audio_string = &CATX_GA
//								audio_sound_file = SOUND_CATX_GA
//							BREAK
//							CASE 1
//								$audio_string = &CATX_GB
//								audio_sound_file = SOUND_CATX_GB
//							BREAK
//							CASE 2
//								$audio_string = &CATX_GC
//								audio_sound_file = SOUND_CATX_GC
//							BREAK
//							CASE 3
//								$audio_string = &CATX_GD
//								audio_sound_file = SOUND_CATX_GD
//							BREAK
//						ENDSWITCH
//
//						START_NEW_SCRIPT audio_line catalina 0 0 1 0
//
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF


	// if player goes over a unique stunt jump
	LVAR_INT catalina_stunt_jump_dialogue
//	IF catalina_stunt_jump_dialogue = 0
//		IF IS_CHAR_IN_ANY_CAR scplayer
//			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
//			IF NOT IS_CHAR_DEAD catalina
//				IF IS_CHAR_IN_CAR catalina car
//					IF NOT HAS_STUNT_ENDED
//
//						IF NOT IS_MESSAGE_BEING_DISPLAYED 
//						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
//						AND audio_line_is_active = 0
//							
//							GENERATE_RANDOM_INT_IN_RANGE 1 7 catalina_stunt_jump_dialogue
//							
//							SWITCH catalina_stunt_jump_dialogue
//								CASE 1
//									$audio_string = &CATX_HA
//									audio_sound_file = SOUND_CATX_HA
//								BREAK
//								CASE 2
//									$audio_string = &CATX_HB
//									audio_sound_file = SOUND_CATX_HB
//								BREAK
//								CASE 3
//									$audio_string = &CATX_HC
//									audio_sound_file = SOUND_CATX_HC
//								BREAK
//								CASE 4
//									$audio_string = &CATX_HD
//									audio_sound_file = SOUND_CATX_HD
//								BREAK
//								CASE 5
//									$audio_string = &CATX_HD
//									audio_sound_file = SOUND_CATX_HE
//								BREAK
//								CASE 6
//									$audio_string = &CATX_HD
//									audio_sound_file = SOUND_CATX_HF
//								BREAK
//							ENDSWITCH
//
//							START_NEW_SCRIPT audio_line catalina 0 0 1 0
//
//						ENDIF
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF



//	LVAR_INT player_is_in_water
//	IF IS_CHAR_IN_WATER scplayer
//		IF player_is_in_water = 0
//			IF NOT IS_CHAR_DEAD catalina
//				TASK_STAND_STILL catalina 9999999
//			ENDIF
//			player_is_in_water = 1
//		ENDIF
//	ELSE
//		IF player_is_in_water = 1
//			IF NOT IS_CHAR_DEAD catalina
//				TASK_STAND_STILL catalina 0
//			ENDIF
//			player_is_in_water = 0
//		ENDIF
//	ENDIF



//	IF cat1_pickups_collected = 4
//		m_passed = 1	
//	ENDIF

RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************

	cat1_update_crane_cam_1:

		IF camera_in_position_pos = 0
			camera_speed_pos += 0.001	
			IF camera_speed_pos > 0.03
				camera_speed_pos = 0.03
			ENDIF	
		ELSE
			camera_speed_pos += -0.001
			IF camera_speed_pos < 0.0
				camera_speed_pos = 0.0
			ENDIF	
		ENDIF

//		IF camera_in_position_look = 0
			camera_speed_look += 0.005
			IF camera_speed_look > 0.1
				camera_speed_look = 0.1
			ENDIF
//		ELSE
//			camera_speed_look += -0.001
//			IF camera_speed_look < 0.0
//				camera_speed_look = 0.0
//			ENDIF
//		ENDIF


		cat1_intrp_dest_x = 261.4119
		cat1_intrp_dest_y = -78.5511
		cat1_intrp_dest_z = 2.3102
		cat1_intrp_start_x =	cam_x
		cat1_intrp_start_y =	cam_y
		cat1_intrp_start_z =	cam_z
		cat1_intrp_speed = camera_speed_pos
		GOSUB cat1_interpolate_to_coord
		IF temp_float2 = 99.9
			IF camera_in_position_pos = 0
				camera_in_position_pos = 1
			ENDIF
		ENDIF
		cam_x = cat1_intrp_start_x 	
		cam_y = cat1_intrp_start_y 	
		cam_z = cat1_intrp_start_z 	

		cat1_intrp_dest_x = dest_look_x 
		cat1_intrp_dest_y = dest_look_y 									  
		cat1_intrp_dest_z = dest_look_z
		cat1_intrp_start_x =	look_x
		cat1_intrp_start_y =	look_y
		cat1_intrp_start_z =	look_z
		cat1_intrp_speed = camera_speed_look 
		GOSUB cat1_interpolate_to_coord
		IF temp_float2 = 99.9
			IF camera_in_position_look = 0
				camera_in_position_look = 1
			ENDIF
		ENDIF
		look_x = cat1_intrp_start_x 	
		look_y = cat1_intrp_start_y 	
		look_z = cat1_intrp_start_z
		
		IF debug_on = 1
			x2 = look_x - 0.5
			x3 = look_x + 0.5
			y2 = look_y - 0.5
			y3 = look_y + 0.5
			z2 = look_z - 0.5
			z3 = look_z + 0.5
			
			LINE x2 look_y look_z x3 look_y look_z
			LINE look_x y2 look_z look_x y3 look_z
			LINE look_x look_y z2 look_x look_y z3
		ENDIF	

	RETURN



	LVAR_FLOAT cat1_intrp_dest_x cat1_intrp_dest_y cat1_intrp_dest_z
	LVAR_FLOAT cat1_intrp_start_x cat1_intrp_start_y cat1_intrp_start_z
	LVAR_FLOAT cat1_intrp_speed
	cat1_interpolate_to_coord:

		x = cat1_intrp_dest_x - cat1_intrp_start_x
		y = cat1_intrp_dest_y - cat1_intrp_start_y
		z = cat1_intrp_dest_z - cat1_intrp_start_z

		GET_DISTANCE_BETWEEN_COORDS_3D cat1_intrp_start_x cat1_intrp_start_y cat1_intrp_start_z cat1_intrp_dest_x cat1_intrp_dest_y cat1_intrp_dest_z temp_float

		temp_float2 = cat1_intrp_speed * 20.0
		IF temp_float < temp_float2
			temp_float2 = 99.9
		ENDIF 

		IF temp_float > cat1_intrp_speed
			x /= temp_float
			y /= temp_float
			z /= temp_float
		
			x *= cat1_intrp_speed
			y *= cat1_intrp_speed
			z *= cat1_intrp_speed

			cat1_intrp_start_x += x
			cat1_intrp_start_y += y
			cat1_intrp_start_z += z
//		ELSE
//			
//			cat1_intrp_start_x = cat1_intrp_dest_x		
//			cat1_intrp_start_y = cat1_intrp_dest_y
//			cat1_intrp_start_z = cat1_intrp_dest_z

//		ELSE
//
//			WRITE_DEBUG camera_in_position
//			WRITE_DEBUG_WITH_FLOAT temp_float temp_float
//
//			camera_in_position = 1
		ENDIF



		
		
	RETURN



// =================================================================
// GOSUB: adjust_playback_speed - of robber's quads
// =================================================================
	// input parameters
	LVAR_INT check_int
	// workings
	LVAR_FLOAT debug_float
	LVAR_FLOAT playback_speed
	cat1_adjust_playback_speed:
		IF NOT IS_CAR_DEAD robber_quad[check_int]	
			IF NOT IS_CHAR_DEAD robber[check_int]
				IF IS_CHAR_IN_CAR robber[check_int] robber_quad[check_int]	

					IF playback_paused[check_int] = 1
						IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
							UNPAUSE_PLAYBACK_RECORDED_CAR robber_quad[check_int]
							playback_paused[check_int] = 0
						ENDIF
					ENDIF

					playback_speed = 0.0
					
					GET_CAR_COORDINATES robber_quad[check_int] x y z
					GET_CHAR_COORDINATES scplayer x2 y2 z2 
					
					// check if quad has reached the checkpoint (switching from travelling east to south)
					IF chase_flag[check_int] = 0
						IF LOCATE_CAR_3D robber_quad[check_int] 2711.1191 -53.5082 39.4695 20.0 20.0 5.0 FALSE
							chase_flag[check_int] = 1
						ENDIF
					ENDIF


					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 quad_dist_from_player[check_int]

					IF chase_flag[check_int] = 0
						IF x2 > x
							// ai car is behind, so speed up
							playback_speed = 1.5
							// check if miles away
							IF quad_dist_from_player[check_int] > 100.0
								IF NOT IS_CAR_ON_SCREEN robber_quad[check_int] 
									IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
										SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[check_int] 1.0
									ENDIF	
								ENDIF						
							ENDIF
						ENDIF
					ELSE
						IF y2 < y
							// ai car is behind, so speed up
							playback_speed = 1.5 
							// check if miles away
							IF quad_dist_from_player[check_int] > 100.0
								IF NOT IS_CAR_ON_SCREEN robber_quad[check_int] 
									IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
										SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[check_int] 1.0
									ENDIF	
								ENDIF						
							ENDIF
						ENDIF
					ENDIF


					IF playback_speed = 0.0
						// ai car is in front, so slow down						
						IF quad_dist_from_player[check_int] < 30.0
							playback_speed = 1.0
						ELSE
							temp_float = quad_dist_from_player[check_int]
							IF temp_float > 200.0
								temp_float = 200.0
							ENDIF			
							temp_float -= 30.0
							temp_float /= 170.0	
							playback_speed = 1.0 - temp_float
							playback_speed *= 0.8 
							playback_speed += 0.2
						ENDIF	
					ENDIF

					// master slowdown / speed up
					playback_speed *= master_speed

					// speed up when approaching bridge
					IF IS_CAR_IN_AREA_2D robber_quad[check_int] 1940.8201 72.1365 2145.4385 140.5141 FALSE
						playback_speed = 2.5
					ENDIF 

					// set minimum speed 
					IF playback_speed < 0.1
						playback_speed = 0.1
					ENDIF

					// apply the new speed
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
						SET_PLAYBACK_SPEED robber_quad[check_int] playback_speed
					ENDIF

					// check if car is stuck
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
						IF DOES_CAR_HAVE_STUCK_CAR_CHECK robber_quad[check_int]
							IF IS_CAR_STUCK robber_quad[check_int]
								IF NOT IS_CAR_ON_SCREEN robber_quad[check_int]
									IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer robber_quad[check_int] 40.0 40.0 5.0 FALSE
										SKIP_IN_PLAYBACK_RECORDED_CAR robber_quad[check_int] 0.1
									ENDIF	
								ENDIF
							ENDIF
						ENDIF
					ENDIF
			
				ELSE // is char in car
					IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
						IF playback_paused[check_int] = 0
							PAUSE_PLAYBACK_RECORDED_CAR robber_quad[check_int]
							playback_paused[check_int] = 1
						ENDIF
					ENDIF
				ENDIF

			ELSE // is char dead
				IF IS_PLAYBACK_GOING_ON_FOR_CAR robber_quad[check_int]
					STOP_PLAYBACK_RECORDED_CAR robber_quad[check_int] 
				ENDIF
				quad_dist_from_player[check_int] = 0.0
			ENDIF

		ENDIF // is car dead

	RETURN


// =================================================================
// GOSUB: chase_dialogue (makes catalina say stuff during chase)
// =================================================================

LVAR_INT cat_they_are_getting_away_dialogue
cat1_chase_dialogue:
	
	IF dialogue_timer > 10000
		
		// they're getting away!
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND is_in_interior = 0
			IF NOT IS_CHAR_DEAD catalina
				IF NOT IS_CHAR_SHOOTING catalina
					IF IS_CHAR_IN_ANY_CAR scplayer 
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF IS_CHAR_IN_CAR catalina car
							LVAR_INT last_cat_dialogue1
							LVAR_INT this_cat_dialogue1
							temp_int = 0
							temp_int2 = 0
							WHILE temp_int < 3
								IF NOT IS_CHAR_DEAD robber[temp_int]
									temp_int2++
								ENDIF
							temp_int++
							ENDWHILE
							temp_int = 0
							IF NOT IS_CHAR_DEAD robber[0]
								IF quad_dist_from_player[0] < 50.0
									temp_int++	
								ENDIF
							ENDIF
							IF NOT IS_CHAR_DEAD robber[1]
								IF quad_dist_from_player[1] < 50.0
									temp_int++	
								ENDIF
							ENDIF
							IF NOT IS_CHAR_DEAD robber[2]
								IF quad_dist_from_player[2] < 50.0
									temp_int++	
								ENDIF
							ENDIF
							IF IS_CHAR_DEAD robber[0]
							AND IS_CHAR_DEAD robber[1]
							AND IS_CHAR_DEAD robber[2]
								temp_int++
							ENDIF

							IF temp_int = 0
								IF audio_line_is_active = 0
//									GENERATE_RANDOM_INT_IN_RANGE 0 9 this_cat_dialogue1
//									IF this_cat_dialogue1 = last_cat_dialogue1
//										this_cat_dialogue1++
//										IF this_cat_dialogue1 > 8
//											this_cat_dialogue1 = 0
//										ENDIF
//									ENDIF
									cat_they_are_getting_away_dialogue++
									IF cat_they_are_getting_away_dialogue > 8
										cat_they_are_getting_away_dialogue = 0 
									ENDIF
									

//									last_cat_dialogue1 = this_cat_dialogue1 
									SWITCH cat_they_are_getting_away_dialogue
										CASE 0
											IF temp_int2 > 1
												$audio_string    = &CAT1_CA	
												audio_sound_file = SOUND_CAT1_CA
											ELSE
												$audio_string    = &CATX_AA	
												audio_sound_file = SOUND_CATX_AA
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0	
										BREAK
										CASE 1
											$audio_string    = &CAT1_CB	
											audio_sound_file = SOUND_CAT1_CB
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
										CASE 2
											$audio_string    = &CAT1_CC	
											audio_sound_file = SOUND_CAT1_CC
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
										CASE 3
											IF temp_int2 > 1
												$audio_string    = &CAT1_CD	
												audio_sound_file = SOUND_CAT1_CD
											ELSE
												$audio_string    = &CATX_AD	
												audio_sound_file = SOUND_CATX_AD
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0	
										BREAK
										CASE 4
											IF temp_int2 > 1
												$audio_string    = &CAT1_CE	
												audio_sound_file = SOUND_CAT1_CE
											ELSE
												$audio_string    = &CATX_AG
												audio_sound_file = SOUND_CATX_AG
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
										CASE 5
											IF temp_int2 > 1
												$audio_string    = &CAT1_DA	
												audio_sound_file = SOUND_CAT1_DA
											ELSE
												$audio_string    = &CATX_AH
												audio_sound_file = SOUND_CATX_AH
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
										CASE 6
											IF temp_int2 > 1
												$audio_string    = &CAT1_DB	
												audio_sound_file = SOUND_CAT1_DB
											ELSE
												$audio_string    = &CATX_AC	
												audio_sound_file = SOUND_CATX_AC
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0	
										BREAK
										CASE 7
											IF temp_int2 > 1
												$audio_string    = &CAT1_DC	
												audio_sound_file = SOUND_CAT1_DC
											ELSE
												$audio_string    = &CATX_AB	
												audio_sound_file = SOUND_CATX_AB
											ENDIF
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
										CASE 8
											$audio_string    = &CAT1_DD	
											audio_sound_file = SOUND_CAT1_DD
											START_NEW_SCRIPT audio_line catalina 0 0 1 0
										BREAK
									ENDSWITCH
									dialogue_timer = 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// don't leave me - player or catalina is on foot and more than 10m away
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND is_in_interior = 0
			IF audio_line_is_active = 0
				IF NOT IS_CHAR_DEAD catalina
					IF NOT IS_CHAR_SHOOTING catalina
						IF NOT IS_CHAR_IN_ANY_CAR scplayer 
						OR NOT IS_CHAR_IN_ANY_CAR catalina
							IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D catalina scplayer 15.0 15.0 5.0 FALSE

								LVAR_INT last_cat_dialogue3

								GENERATE_RANDOM_INT_IN_RANGE 0 8 temp_int

								IF temp_int = last_cat_dialogue3
									temp_int++
									IF temp_int > 7
										temp_int = 0
									ENDIF
								ENDIF
								last_cat_dialogue3 = temp_int

								SWITCH temp_int

									CASE 0
										$audio_string    = &CATX_JA	
										audio_sound_file = SOUND_CATX_JA
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 1
										$audio_string    = &CATX_JB	
										audio_sound_file = SOUND_CATX_JB
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 2
										$audio_string    = &CATX_JC	
										audio_sound_file = SOUND_CATX_JC
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 3
										$audio_string    = &CATX_JD	
										audio_sound_file = SOUND_CATX_JD
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 4
										$audio_string    = &CATX_JE	
										audio_sound_file = SOUND_CATX_JE
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 5
										$audio_string    = &CATX_JF	
										audio_sound_file = SOUND_CATX_JF
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 6
										$audio_string    = &CATX_JG	
										audio_sound_file = SOUND_CATX_JG
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK
									CASE 7
										$audio_string    = &CATX_JH	
										audio_sound_file = SOUND_CATX_JH
										START_NEW_SCRIPT audio_line catalina 0 0 1 0
									BREAK


								ENDSWITCH

								dialogue_timer = 0
		
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
						



		// carl moans at catalina for shooting beside his ear-drum
		LVAR_INT this_cat_dialogue2
		LVAR_INT last_cat_dialogue2
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND is_in_interior = 0
			IF audio_line_is_active = 0
				IF NOT IS_CHAR_DEAD catalina
					IF IS_CHAR_SHOOTING catalina
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D catalina scplayer 2.0 2.0 2.0 FALSE
							temp_int = 0
							WHILE temp_int < 4
								GENERATE_RANDOM_INT_IN_RANGE 0 25 this_cat_dialogue2
								IF this_cat_dialogue2 < 5
									temp_int = 4
								ENDIF
								temp_int++
							ENDWHILE
							IF this_cat_dialogue2 = last_cat_dialogue2
								this_cat_dialogue2++
								IF this_cat_dialogue2 > 24
									this_cat_dialogue2 = 0
								ENDIF		
							ENDIF  
							last_cat_dialogue2 = this_cat_dialogue2
							SWITCH this_cat_dialogue2
								CASE 0
								CASE 1
									$audio_string    = &CAT1_HA
									audio_sound_file = SOUND_CAT1_HA
									START_NEW_SCRIPT audio_line scplayer 0 0 1 0
								BREAK
//								CASE 1
//									$audio_string    = &CAT1_HB
//									audio_sound_file = SOUND_CAT1_HB
//									START_NEW_SCRIPT audio_line scplayer 0 0 1 0
//								BREAK
								CASE 2
									$audio_string    = &CAT1_HC
									audio_sound_file = SOUND_CAT1_HC
									START_NEW_SCRIPT audio_line scplayer 0 0 1 0
								BREAK
								CASE 3
									$audio_string    = &CAT1_HD
									audio_sound_file = SOUND_CAT1_HD
									START_NEW_SCRIPT audio_line scplayer 0 0 1 0
								BREAK
								CASE 4
									$audio_string    = &CAT1_HE
									audio_sound_file = SOUND_CAT1_HE
									START_NEW_SCRIPT audio_line scplayer 0 0 1 0	
								BREAK
								CASE 5
									$audio_string    = &CATX_TA
									audio_sound_file = SOUND_CATX_TA
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 6
									$audio_string    = &CATX_TB
									audio_sound_file = SOUND_CATX_TB
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 7
									$audio_string    = &CATX_TC
									audio_sound_file = SOUND_CATX_TC
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 8
									$audio_string    = &CATX_TD
									audio_sound_file = SOUND_CATX_TD
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 9
									$audio_string    = &CATX_TE
									audio_sound_file = SOUND_CATX_TE
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 10
									$audio_string    = &CATX_TF
									audio_sound_file = SOUND_CATX_TF
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 11
									$audio_string    = &CATX_TG
									audio_sound_file = SOUND_CATX_TG
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 12
									$audio_string    = &CATX_TH
									audio_sound_file = SOUND_CATX_TH
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 13
									$audio_string    = &CATX_TJ
									audio_sound_file = SOUND_CATX_TJ
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 14
									$audio_string    = &CATX_TK
									audio_sound_file = SOUND_CATX_TK
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 15
									$audio_string    = &CATX_TL
									audio_sound_file = SOUND_CATX_TL
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 16
									$audio_string    = &CATX_TM
									audio_sound_file = SOUND_CATX_TM
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 17
									$audio_string    = &CATX_TN
									audio_sound_file = SOUND_CATX_TN
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 18
									$audio_string    = &CATX_TO
									audio_sound_file = SOUND_CATX_TO
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 19
									$audio_string    = &CATX_TP
									audio_sound_file = SOUND_CATX_TP
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 20
									$audio_string    = &CATX_TQ
									audio_sound_file = SOUND_CATX_TQ
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 21
									$audio_string    = &CATX_TR
									audio_sound_file = SOUND_CATX_TR
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 22
									$audio_string    = &CATX_TS
									audio_sound_file = SOUND_CATX_TS
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 23
									$audio_string    = &CATX_TT
									audio_sound_file = SOUND_CATX_TT
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK
								CASE 24
									$audio_string    = &CATX_TU
									audio_sound_file = SOUND_CATX_TU
									START_NEW_SCRIPT audio_line catalina 0 0 1 0
								BREAK

							ENDSWITCH 
							dialogue_timer = 0	
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF



//		// cops dialogue
//		IF cat1_cops_dialogue = 0
//			IF IS_COPS_IN_PURSUIT
//				IF audio_line_is_active = 0
//					GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
//					SWITCH temp_int
//						CASE 0
//							$audio_string    = &CAT1_EA
//							audio_sound_file = SOUND_CAT1_EA
//							START_NEW_SCRIPT audio_line scplayer 0 0 1 0
//						BREAK
//						CASE 1
//							$audio_string    = &CAT1_EB
//							audio_sound_file = SOUND_CAT1_EB
//							START_NEW_SCRIPT audio_line scplayer 0 0 1 0
//						BREAK
//						CASE 2
//							$audio_string    = &CAT1_EC
//							audio_sound_file = SOUND_CAT1_EC
//							START_NEW_SCRIPT audio_line scplayer 0 0 1 0
//						BREAK
//						CASE 3
//							$audio_string    = &CAT1_ED
//							audio_sound_file = SOUND_CAT1_ED
//							START_NEW_SCRIPT audio_line scplayer 0 0 1 0
//						BREAK
//					ENDSWITCH
//					cat1_cops_dialogue++	
//				ENDIF
//			ENDIF
//		ENDIF





	ENDIF

	// left catalina
	IF NOT IS_MESSAGE_BEING_DISPLAYED
	AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
	AND is_in_interior = 0
		IF left_cat_dialogue = 1
			IF dialogue_timer > 4000
				IF audio_line_is_active = 0
					GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
					SWITCH temp_int
						CASE 0
							$audio_string    = &CAT1_GA
							audio_sound_file = SOUND_CAT1_GA
							START_NEW_SCRIPT audio_line catalina 0 0 1 0
						BREAK
						CASE 1
							$audio_string    = &CAT1_GB
							audio_sound_file = SOUND_CAT1_GB
							START_NEW_SCRIPT audio_line catalina 0 0 1 0
						BREAK
						CASE 2
							$audio_string    = &CAT1_GC
							audio_sound_file = SOUND_CAT1_GC
							START_NEW_SCRIPT audio_line catalina 0 0 1 0
						BREAK
						CASE 3
							$audio_string    = &CAT1_GD
							audio_sound_file = SOUND_CAT1_GD
							START_NEW_SCRIPT audio_line catalina 0 0 1 0
						BREAK
					ENDSWITCH
					left_cat_dialogue = 2	
				ENDIF	
			ENDIF
		ENDIF
	ENDIF
	


//	// filler
//	IF audio_line_is_active = 0
//		IF dialogue_timer > 10000
//
//			LVAR_INT last_filler_dialogue
//		
//			GENERATE_RANDOM_INT_IN_RANGE 0 20 temp_int
//			IF temp_int = last_filler_dialogue
//				temp_int++
//				IF temp_int > 19
//					temp_int = 0
//				ENDIF
//			ENDIF
//			SWITCH temp_int
//				CASE 0
//					$audio_string    = &CATX_TA
//					audio_sound_file = SOUND_CATX_TA
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 1
//					$audio_string    = &CATX_TB
//					audio_sound_file = SOUND_CATX_TB
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 2
//					$audio_string    = &CATX_TC
//					audio_sound_file = SOUND_CATX_TC
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 3
//					$audio_string    = &CATX_TD
//					audio_sound_file = SOUND_CATX_TD
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 4
//					$audio_string    = &CATX_TE
//					audio_sound_file = SOUND_CATX_TE
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 5
//					$audio_string    = &CATX_TF
//					audio_sound_file = SOUND_CATX_TF
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 6
//					$audio_string    = &CATX_TG
//					audio_sound_file = SOUND_CATX_TG
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 7
//					$audio_string    = &CATX_TH
//					audio_sound_file = SOUND_CATX_TH
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 8
//					$audio_string    = &CATX_TJ
//					audio_sound_file = SOUND_CATX_TJ
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 9
//					$audio_string    = &CATX_TK
//					audio_sound_file = SOUND_CATX_TK
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 10
//					$audio_string    = &CATX_TL
//					audio_sound_file = SOUND_CATX_TL
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 11
//					$audio_string    = &CATX_TM
//					audio_sound_file = SOUND_CATX_TM
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 12
//					$audio_string    = &CATX_TN
//					audio_sound_file = SOUND_CATX_TN
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 13
//					$audio_string    = &CATX_TO
//					audio_sound_file = SOUND_CATX_TO
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 14
//					$audio_string    = &CATX_TP
//					audio_sound_file = SOUND_CATX_TP
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 15
//					$audio_string    = &CATX_TQ
//					audio_sound_file = SOUND_CATX_TQ
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 16
//					$audio_string    = &CATX_TR
//					audio_sound_file = SOUND_CATX_TR
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 17
//					$audio_string    = &CATX_TS
//					audio_sound_file = SOUND_CATX_TS
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 18
//					$audio_string    = &CATX_TT
//					audio_sound_file = SOUND_CATX_TT
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//				CASE 19
//					$audio_string    = &CATX_TU
//					audio_sound_file = SOUND_CATX_TU
//					START_NEW_SCRIPT audio_line catalina 0 0 1 0
//				BREAK
//			ENDSWITCH
//			dialogue_timer = 0
//		ENDIF
//	ENDIF
	
RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
	CAT1_next_stage:
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
mission_failed_CAT1:

	PRINT_BIG M_FAIL 5000 1

RETURN

// PASS
mission_passed_CAT1:

	flag_cat_mission1_passed = 1

	PRINT_WITH_NUMBER_BIG ( M_PASS ) 1000 5000 1 //"Mission Passed!"
	ADD_SCORE player1 1000
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1

	REGISTER_MISSION_PASSED CAT_1

	PLAYER_MADE_PROGRESS 1

	// stuff for rothwell
	IF cat_otb_banter = 1
    	cat_otb_banter = 2
	ENDIF
	IF cat_liquor_banter = 1
		cat_liquor_banter = 2
	ENDIF
	cat_getaway_dialogue++

	SET_INT_STAT PASSED_CAT1 1

	// stuff for filshie
	cat_counter++



RETURN

// CLEANUP
mission_cleanup_CAT1:

	SWITCH_WIDESCREEN OFF

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 FALSE
	ENDIF

	SET_CREATE_RANDOM_GANG_MEMBERS TRUE

	//CLEAR_ONSCREEN_COUNTER cat1_pickups_collected
	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3

	REMOVE_BLIP catalina_blip
	REMOVE_BLIP robber_blip[0]
	REMOVE_BLIP robber_blip[1]
	REMOVE_BLIP robber_blip[2]
	REMOVE_BLIP briefcase_blip[0]
	REMOVE_BLIP briefcase_blip[1]
	REMOVE_BLIP briefcase_blip[2]
	REMOVE_BLIP location_blip

	REMOVE_PICKUP robber_pickup[0]
	REMOVE_PICKUP robber_pickup[1]
	REMOVE_PICKUP robber_pickup[2]

	// remove decision makers
	REMOVE_DECISION_MAKER empty_dm
	REMOVE_DECISION_MAKER tough_dm
	REMOVE_DECISION_MAKER catalina_dm
	REMOVE_DECISION_MAKER group_dm

	UNLOAD_SPECIAL_CHARACTER 5

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	GET_GAME_TIMER timer_mobile_start

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN


	 

}












//							IF cat_counter = 2
//								SWITCH dialogue_flag 
//									CASE 0
//										$audio_string    = &CAT_KA
//										audio_sound_file = SOUND_CAT_KA
//										START_NEW_SCRIPT audio_line catalina 0 1 2 0   	//	CATALINA	[CAT_KA]	How was it?   
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KB				
//									BREAK												
//									CASE 1												
//										$audio_string    = &CAT_KB						
//										audio_sound_file = SOUND_CAT_KB					
//										START_NEW_SCRIPT audio_line scplayer 0 1 1 1    //	CARL		[CAT_KB]	Different…   
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KC				   
//									BREAK												
//									CASE 2												
//									 	$audio_string    = &CAT_KC						
//										audio_sound_file = SOUND_CAT_KC					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   	//	CATALINA	[CAT_KC]	I knew you would like it!
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KD 				
//									BREAK												
//									CASE 3												
//									 	$audio_string    = &CAT_KD						
//										audio_sound_file = SOUND_CAT_KD					
//										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   	//	CARL		[CAT_KD]	Look, baby, I thought we was going to make some serious paper.
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KE 				
//									BREAK												
//									CASE 4												
//									 	$audio_string    = &CAT_KE						
//										audio_sound_file = SOUND_CAT_KE					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   	//	CATALINA	[CAT_KE]	I’m starting to get really bored of you.
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KF 				
//									BREAK												
//									CASE 5												
//									 	$audio_string    = &CAT_KF						
//										audio_sound_file = SOUND_CAT_KF					
//										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   	//	CARL		[CAT_KF]	I just need the money.
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KG 				
//									BREAK												
//									CASE 6												
//									 	$audio_string    = &CAT_KG						
//										audio_sound_file = SOUND_CAT_KG					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   	//	CATALINA	[CAT_KG]	And I’m just a cheap fuck? 
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KH 				
//									BREAK												
//									CASE 7												
//									 	$audio_string    = &CAT_KH						
//										audio_sound_file = SOUND_CAT_KH					
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   	//				[CAT_KH]	A whore you don’t even pay?
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KI 				
//									BREAK												
//									CASE 8												
//									 	$audio_string    = &CAT_KI						
//										audio_sound_file = SOUND_CAT_KI					
//										START_NEW_SCRIPT audio_line scplayer 0 1 2 1   	//	CARL		[CAT_KI]	No, I didn’t say that.
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KJ 				
//									BREAK												
//									CASE 9												
//									 	$audio_string    = &CAT_KJ						
//										audio_sound_file = SOUND_CAT_KJ					
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   	//	CATALINA	[CAT_KJ]	Carl, I say I’m in love with you, and you act like I’m an idiot. 
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KK 				
//									BREAK												
//									CASE 10												
//									 	$audio_string    = &CAT_KK						
//										audio_sound_file = SOUND_CAT_KK					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   	//				[CAT_KK]	I see the way you look at other women. 
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KL 				
//									BREAK												
//									CASE 11												
//									 	$audio_string    = &CAT_KL						
//										audio_sound_file = SOUND_CAT_KL					
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   	//				[CAT_KL]	I know your kind. Carl. I am serious. 
//										CLEAR_MISSION_AUDIO 2							
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KM 				
//									BREAK												
//									CASE 12												
//									 	$audio_string    = &CAT_KM						
//										audio_sound_file = SOUND_CAT_KM					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   	//				[CAT_KM]	I will kill you if you ever mess around. 
//										CLEAR_MISSION_AUDIO 1							
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KN 				
//									BREAK												
//									CASE 13												
//									 	$audio_string    = &CAT_KN						
//										audio_sound_file = SOUND_CAT_KN					
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   	//				[CAT_KN]	I will castrate you first. 
//										CLEAR_MISSION_AUDIO 2				
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KN 												
//									BREAK
//									CASE 14												
//									 	$audio_string    = &CAT_KO						
//										audio_sound_file = SOUND_CAT_KO					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   //				[CAT_KO]	Then I will make you eat them.	
//										CLEAR_MISSION_AUDIO 1				
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KP 				
//									BREAK
//									CASE 15												
//									 	$audio_string    = &CAT_KP						
//										audio_sound_file = SOUND_CAT_KP					
//										START_NEW_SCRIPT audio_line catalina 0 1 1 1   //	CARL		[CAT_KP]	Enough! I need some fucking money!	
//										CLEAR_MISSION_AUDIO 2				
//										LOAD_MISSION_AUDIO 2 SOUND_CAT_KQ 				
//									BREAK
//									CASE 16												
//									 	$audio_string    = &CAT_KQ						
//										audio_sound_file = SOUND_CAT_KQ					
//										START_NEW_SCRIPT audio_line catalina 0 1 2 1   //	CATALINA	[CAT_KQ]	Carl, you are really boring me now.	
//										CLEAR_MISSION_AUDIO 1				
//										LOAD_MISSION_AUDIO 1 SOUND_CAT_KR 				
//									BREAK
//									CASE 17												
//									 	$audio_string    = &CAT_KR						
//										audio_sound_file = SOUND_CAT_KR					
//										START_NEW_SCRIPT audio_line scplayer 0 1 1 1   //	CARL   		[CAT_KR]	Please, sweet hear. I’ve got some real, real deep shit.													
//									BREAK																				
//									CASE 18												
//									 	conversation_home = 0							
//									BREAK												
//								ENDSWITCH												
//								dialogue_flag++											
//								TIMERA = 0												
//							ENDIF														
