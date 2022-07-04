MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION MISSNAM : POOL2 
//				  AUTHOR : Neil
//			DESICRIPTION : minigame (triggered by pool.sc the object script)
//
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************

SCRIPT_NAME POOL2
GOSUB mission_start_POOL2
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_POOL2
ENDIF
GOSUB mission_cleanup_POOL2
MISSION_END

mission_start_POOL2:										   
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

VAR_INT pool_view_debug[8]
VAR_FLOAT pool_view_debug2[8]

CONST_FLOAT TEXT_CLAMP_WIDTH_5 115.0
CONST_FLOAT TEXT_CLAMP_WIDTH_1 130.0
CONST_FLOAT TEXT_CLAMP_WIDTH_2 115.0
CONST_FLOAT TEXT_CLAMP_WIDTH_3 115.0

CONST_FLOAT TEXT_CLAMP_WIDTH_1_TWO_LINES 180.0
			
						

// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT flag3
LVAR_INT m_passed
LVAR_INT m_failed
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3 temp_float4 temp_float5
LVAR_INT temp_seq	

// used for debug
LVAR_FLOAT cue_ball_speed
LVAR_FLOAT cue_ball_rot_x cue_ball_rot_y cue_ball_rot_z


// mission variables
LVAR_FLOAT pool_ball_radius
LVAR_FLOAT pool_ball_diameter
LVAR_FLOAT pocket_x[6] pocket_y[6]
LVAR_INT ball_potted[16]
LVAR_INT p_ball[16]
LVAR_FLOAT pool_table_min_x pool_table_max_x pool_table_min_y pool_table_max_y
LVAR_FLOAT table_x table_y table_z table_h
LVAR_INT cue1 cue2 	
LVAR_INT quit
LVAR_INT ball_is_stationary[16]
	
// flags
LVAR_INT pl_circle_pressed
LVAR_INT cross_is_pressed
LVAR_INT triangle_is_pressed
LVAR_INT square_is_pressed
LVAR_INT output_text

// WAGER WINDOW STUFF - can be removed once finalised
LVAR_FLOAT pl_rect_x
LVAR_FLOAT pl_rect_y
LVAR_FLOAT pl_rect_w
LVAR_FLOAT pl_rect_h
LVAR_INT pl_gray
LVAR_INT pl_alpha
LVAR_FLOAT pl_wager_text_x[9]
LVAR_FLOAT pl_wager_text_y[9]
LVAR_INT pl_current_text		
LVAR_INT pl_current_wager
LVAR_INT pl_players_cash		 
LVAR_INT pl_wager_too_high
LVAR_INT pl_wager_too_low
LVAR_INT pl_players_total_cash
LVAR_INT pl_bet_step
LVAR_INT pl_refund
LVAR_INT pl_initial_stake

LVAR_INT table
LVAR_INT opp
LVAR_FLOAT x2 y2 z2
LVAR_INT camera_object
LVAR_FLOAT vec_x vec_y vec_z vec2_x vec2_y vec2_z

LVAR_FLOAT light_x light_y light_z

LVAR_INT two_players

LVAR_INT last_m_stage

LVAR_INT current_pad

LVAR_INT last_frame_time time_diff
LVAR_INT triangle_timer

LVAR_INT this_is_break
LVAR_INT proj_hit_ball_objects[10]
LVAR_INT proj_used_hit_balls

LVAR_FLOAT last_drawn_aim_ball_x last_drawn_aim_ball_y

LVAR_INT aim_help_flag


CONST_INT POOL_COLOUR_NONE 	   	-1
CONST_INT POOL_COLOUR_SPOTS 	0
CONST_INT POOL_COLOUR_STRIPES 	1
CONST_INT POOL_COLOUR_BLACK 	2


GET_GAME_TIMER last_frame_time

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0

two_players = 0

edit_x1 = -0.9570 
edit_y1 = -0.4900
edit_x2 = 0.9570 
edit_y2 = 0.4900 

LVAR_FLOAT pool_stop_velocity
LVAR_FLOAT pool_stop_rotation_velocity
pool_stop_velocity				= 0.05
pool_stop_rotation_velocity		= 2.0

mission_loop_POOL2:

    WAIT 0

	LVAR_INT help_timer
	LVAR_INT shot_timer
	GET_GAME_TIMER temp_int
	time_diff = temp_int - last_frame_time
	last_frame_time = temp_int
	triangle_timer += time_diff
	help_timer += time_diff
	shot_timer += time_diff

	GOSUB pool2_debug
	
	pool_switch_start:
	SWITCH m_stage
		CASE 0
			GOSUB pl_stage_0
		BREAK
		CASE 1
			GOSUB pl_stage_1
		BREAK
		CASE 2
			GOSUB pl_stage_2
		BREAK
		CASE 3
			GOSUB pl_stage_3
		BREAK
		CASE 4
			GOSUB pl_stage_4
		BREAK
		CASE 5
			GOSUB pl_stage_5
		BREAK
		CASE 6
			GOSUB pl_stage_6
		BREAK
		CASE 7
			GOSUB pl_stage_7
		BREAK
		CASE 8
			GOSUB pl_stage_8
		BREAK
		CASE 9
			GOSUB pl_stage_9
		BREAK
		CASE 10
			GOSUB pl_stage_10
		BREAK
		CASE 11
			GOSUB pl_stage_11
		BREAK
		CASE 12
			GOSUB pl_stage_12
		BREAK
	ENDSWITCH
	
	IF NOT last_m_stage = m_stage
		last_m_stage = m_stage
		GOTO pool_switch_start	
	ENDIF

	GOSUB pl_global_functions
	GOSUB edit_pool_turn_mass

// end of main loop

IF quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_POOL2 
		ELSE
			GOSUB mission_passed_POOL2
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_POOL2
		RETURN
	ENDIF
ELSE
	RETURN
ENDIF



// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
pl_stage_0:
    
	// fake create
	IF m_goals = -1
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 table
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 active_pool_table
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 opp
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 active_pool_opponent
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 camera_object
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 object_anim_char
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 object_anim_object
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 p_ball[0]
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 active_initial_cue
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 proj_cue_ball_objects[0]
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 player1_char
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 player2_char
	ENDIF

	IF m_goals = 0
		IF DOES_OBJECT_EXIST active_pool_table

			table = active_pool_table
			opp =  active_pool_opponent

			IF NOT IS_CHAR_DEAD opp
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER opp TRUE
				SET_CHAR_PROOFS opp TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_COLLISION opp FALSE
				LVAR_INT dm
				LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm
				SET_CHAR_DECISION_MAKER opp dm
			ENDIF
			
			GET_OBJECT_COORDINATES table x y z
			CLEAR_AREA x y z 100.0 TRUE
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_NEAR_CLIP 0.01
			MAKE_PLAYER_GANG_DISAPPEAR
			output_text = 0
			quit = 0

			GOSUB init_pool_screen_coords

			m_goals++
		ENDIF
	ENDIF

	IF m_goals = 1
		IF DOES_OBJECT_EXIST table

			GET_OBJECT_COORDINATES table table_x table_y table_z
			GET_OBJECT_HEADING table table_h
			table_z += -0.1
			
			// load stuff
			LOAD_MISSION_TEXT POOL
			LOAD_MISSION_AUDIO 4 SOUND_BANK_POOL

			REQUEST_MODEL K_POOLQ
			REQUEST_ANIMATION POOL
			WHILE NOT HAS_ANIMATION_LOADED POOL
			   OR NOT HAS_MODEL_LOADED K_POOLQ
			   OR NOT HAS_MISSION_AUDIO_LOADED 4
				WAIT 0
			ENDWHILE
			LOAD_TEXTURE_DICTIONARY ld_pool
			LOAD_SPRITE 1 ball
			LOAD_SPRITE 2 nib

			// set flags 
			pl_circle_pressed = 0

			m_goals++
		ENDIF
	ENDIF
			
	IF m_goals = 2
		m_stage++
		m_goals = 0
	ENDIF		

RETURN


pool_edit_table_cushions:

	LINE pool_table_min_x pool_table_min_y z  pool_table_max_x pool_table_min_y z
	LINE pool_table_min_x pool_table_min_y z  pool_table_min_x pool_table_max_y z 
	LINE pool_table_max_x pool_table_max_y z  pool_table_max_x pool_table_min_y z
	LINE pool_table_max_x pool_table_max_y z  pool_table_min_x pool_table_max_y z

	LVAR_FLOAT edit_x1 edit_x2 edit_y1 edit_y2

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
		edit_x1 += -0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
		edit_x1 += 0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		edit_y1 += -0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		edit_y1 += 0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
		edit_x2 += -0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
		edit_x2 += 0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
		edit_y2 += -0.0001
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
		edit_y2 += 0.0001
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "edit_x1 = "
		SAVE_FLOAT_TO_DEBUG_FILE edit_x1
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "edit_y1 = "
		SAVE_FLOAT_TO_DEBUG_FILE edit_y1
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "edit_x2 = "
		SAVE_FLOAT_TO_DEBUG_FILE edit_x2
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "edit_y2 = "
		SAVE_FLOAT_TO_DEBUG_FILE edit_y2
 
	ENDIF

	IF DOES_OBJECT_EXIST table
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table edit_x1 edit_y1 0.70 pool_table_min_x pool_table_min_y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table edit_x2 edit_y2 0.70 pool_table_max_x pool_table_max_y z
	ENDIF

RETURN


pool2_debug:

LVAR_INT pool2_debug_mode
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_SPACE
	++ pool2_debug_mode
	IF pool2_debug_mode > 11
		pool2_debug_mode = 0
	ENDIF
	CLEAR_ALL_VIEW_VARIABLES
ENDIF

IF pool2_debug_mode = 1
	
	pool_view_debug[0] = m_stage
	pool_view_debug[1] = m_goals
	pool_view_debug[2] = flag3
	pool_view_debug[3] = quit
	pool_view_debug[4] = pl_circle_pressed
	pool_view_debug[5] = output_text
	pool_view_debug[6] = player1_colour
	pool_view_debug[7] = player2_colour

	VIEW_INTEGER_VARIABLE pool_view_debug[0] m_stage
	VIEW_INTEGER_VARIABLE pool_view_debug[1] m_goals
	VIEW_INTEGER_VARIABLE pool_view_debug[2] flag3
	VIEW_INTEGER_VARIABLE pool_view_debug[3] quit
	VIEW_INTEGER_VARIABLE pool_view_debug[4] pl_circle_pressed
	VIEW_INTEGER_VARIABLE pool_view_debug[5] output_text
	VIEW_INTEGER_VARIABLE pool_view_debug[6] player1_colour
	VIEW_INTEGER_VARIABLE pool_view_debug[7] player2_colour

ENDIF

IF pool2_debug_mode = 2
	
	pool_view_debug[0] = ball_potted[0]
	pool_view_debug[1] = ball_potted[1]
	pool_view_debug[2] = ball_potted[2]
	pool_view_debug[3] = ball_potted[3]
	pool_view_debug[4] = ball_potted[4]
	pool_view_debug[5] = ball_potted[5]
	pool_view_debug[6] = ball_potted[6]
	pool_view_debug[7] = ball_potted[7]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] ball_potted[0]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] ball_potted[1]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] ball_potted[2]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] ball_potted[3]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] ball_potted[4]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] ball_potted[5]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] ball_potted[6]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] ball_potted[7]

ENDIF

IF pool2_debug_mode = 3
	
	pool_view_debug[0] = ball_potted[8]
	pool_view_debug[1] = ball_potted[9]
	pool_view_debug[2] = ball_potted[10]
	pool_view_debug[3] = ball_potted[11]
	pool_view_debug[4] = ball_potted[12]
	pool_view_debug[5] = ball_potted[13]
	pool_view_debug[6] = ball_potted[14]
	pool_view_debug[7] = ball_potted[15]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] ball_potted[8]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] ball_potted[9]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] ball_potted[10]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] ball_potted[11]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] ball_potted[12]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] ball_potted[13]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] ball_potted[14]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] ball_potted[15]

ENDIF

IF pool2_debug_mode = 4
	
	pool_view_debug[0] = player1_is_human
	pool_view_debug[1] = player2_is_human
	pool_view_debug[2] = turn_player
	pool_view_debug[3] = current_char_is_human
	pool_view_debug[4] = turn_colour
	pool_view_debug[5] = turn_is_scratch
	pool_view_debug[6] = this_char_got_task
	pool_view_debug[7] = shot_played

	VIEW_INTEGER_VARIABLE pool_view_debug[0] player1_is_human
	VIEW_INTEGER_VARIABLE pool_view_debug[1] player2_is_human
	VIEW_INTEGER_VARIABLE pool_view_debug[2] turn_player
	VIEW_INTEGER_VARIABLE pool_view_debug[3] current_char_is_human
	VIEW_INTEGER_VARIABLE pool_view_debug[4] turn_colour
	VIEW_INTEGER_VARIABLE pool_view_debug[5] turn_is_scratch
	VIEW_INTEGER_VARIABLE pool_view_debug[6] this_char_got_task
	VIEW_INTEGER_VARIABLE pool_view_debug[7] shot_played

ENDIF

IF pool2_debug_mode = 5
	
	pool_view_debug[0] = aim_cue
	pool_view_debug[1] = skip_chalk_cue
	pool_view_debug[2] = no_of_balls_potted_this_turn
	pool_view_debug[3] = spots_potted
	pool_view_debug[4] = stripes_potted
	pool_view_debug[5] = foul_type
	pool_view_debug[6] = first_moving_ball
	pool_view_debug[7] = camera_mode

	VIEW_INTEGER_VARIABLE pool_view_debug[0] aim_cue
	VIEW_INTEGER_VARIABLE pool_view_debug[1] skip_chalk_cue
	VIEW_INTEGER_VARIABLE pool_view_debug[2] no_of_balls_potted_this_turn
	VIEW_INTEGER_VARIABLE pool_view_debug[3] spots_potted
	VIEW_INTEGER_VARIABLE pool_view_debug[4] stripes_potted
	VIEW_INTEGER_VARIABLE pool_view_debug[5] foul_type
	VIEW_INTEGER_VARIABLE pool_view_debug[6] first_moving_ball
	VIEW_INTEGER_VARIABLE pool_view_debug[7] camera_mode

ENDIF

IF pool2_debug_mode = 6
	
	pool_view_debug[0] = balls_potted_this_turn[0]
	pool_view_debug[1] = balls_potted_this_turn[1]
	pool_view_debug[2] = balls_potted_this_turn[2]
	pool_view_debug[3] = balls_potted_this_turn[3]
	pool_view_debug[4] = balls_potted_this_turn[4]
	pool_view_debug[5] = balls_potted_this_turn[5]
	pool_view_debug[6] = balls_potted_this_turn[6]
	pool_view_debug[7] = balls_potted_this_turn[7]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] balls_potted_this_turn[0]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] balls_potted_this_turn[1]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] balls_potted_this_turn[2]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] balls_potted_this_turn[3]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] balls_potted_this_turn[4]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] balls_potted_this_turn[5]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] balls_potted_this_turn[6]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] balls_potted_this_turn[7]

ENDIF

IF pool2_debug_mode = 7
	
	pool_view_debug[0] = balls_potted_this_turn[8]
	pool_view_debug[1] = balls_potted_this_turn[9]
	pool_view_debug[2] = balls_potted_this_turn[10]
	pool_view_debug[3] = balls_potted_this_turn[11]
	pool_view_debug[4] = balls_potted_this_turn[12]
	pool_view_debug[5] = balls_potted_this_turn[13]
	pool_view_debug[6] = balls_potted_this_turn[14]
	pool_view_debug[7] = balls_potted_this_turn[15]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] balls_potted_this_turn[8]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] balls_potted_this_turn[9]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] balls_potted_this_turn[10]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] balls_potted_this_turn[11]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] balls_potted_this_turn[12]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] balls_potted_this_turn[13]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] balls_potted_this_turn[14]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] balls_potted_this_turn[15]

ENDIF

IF pool2_debug_mode = 8
	
	pool_view_debug[0] = ball_is_stationary[0]
	pool_view_debug[1] = ball_is_stationary[1]
	pool_view_debug[2] = ball_is_stationary[2]
	pool_view_debug[3] = ball_is_stationary[3]
	pool_view_debug[4] = ball_is_stationary[4]
	pool_view_debug[5] = ball_is_stationary[5]
	pool_view_debug[6] = ball_is_stationary[6]
	pool_view_debug[7] = ball_is_stationary[7]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] ball_is_stationary[0]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] ball_is_stationary[1]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] ball_is_stationary[2]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] ball_is_stationary[3]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] ball_is_stationary[4]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] ball_is_stationary[5]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] ball_is_stationary[6]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] ball_is_stationary[7]

ENDIF

IF pool2_debug_mode = 9
	
	pool_view_debug[0] = ball_is_stationary[8]
	pool_view_debug[1] = ball_is_stationary[9]
	pool_view_debug[2] = ball_is_stationary[10]
	pool_view_debug[3] = ball_is_stationary[11]
	pool_view_debug[4] = ball_is_stationary[12]
	pool_view_debug[5] = ball_is_stationary[13]
	pool_view_debug[6] = ball_is_stationary[14]
	pool_view_debug[7] = ball_is_stationary[15]

	VIEW_INTEGER_VARIABLE pool_view_debug[0] ball_is_stationary[8]
	VIEW_INTEGER_VARIABLE pool_view_debug[1] ball_is_stationary[9]
	VIEW_INTEGER_VARIABLE pool_view_debug[2] ball_is_stationary[10]
	VIEW_INTEGER_VARIABLE pool_view_debug[3] ball_is_stationary[11]
	VIEW_INTEGER_VARIABLE pool_view_debug[4] ball_is_stationary[12]
	VIEW_INTEGER_VARIABLE pool_view_debug[5] ball_is_stationary[13]
	VIEW_INTEGER_VARIABLE pool_view_debug[6] ball_is_stationary[14]
	VIEW_INTEGER_VARIABLE pool_view_debug[7] ball_is_stationary[15]

ENDIF

IF pool2_debug_mode = 10
	
	pool_view_debug2[0] = cue_ball_speed
	pool_view_debug2[1] = cue_ball_rot_x
	pool_view_debug2[2] = cue_ball_rot_y
	pool_view_debug2[3] = cue_ball_rot_z
	pool_view_debug2[4] = pool_table_min_x
	pool_view_debug2[5] = pool_table_max_x
	pool_view_debug2[6] = pool_table_min_y
	pool_view_debug2[7] = pool_table_max_y

	VIEW_FLOAT_VARIABLE pool_view_debug2[0] cue_ball_speed
	VIEW_FLOAT_VARIABLE pool_view_debug2[1] cue_ball_rot_x
	VIEW_FLOAT_VARIABLE pool_view_debug2[2] cue_ball_rot_y
	VIEW_FLOAT_VARIABLE pool_view_debug2[3] cue_ball_rot_z
	VIEW_FLOAT_VARIABLE pool_view_debug2[4] pool_table_min_x
	VIEW_FLOAT_VARIABLE pool_view_debug2[5] pool_table_max_x
	VIEW_FLOAT_VARIABLE pool_view_debug2[6] pool_table_min_y
	VIEW_FLOAT_VARIABLE pool_view_debug2[7] pool_table_max_y

ENDIF

IF pool2_debug_mode = 11
	
	pool_view_debug[0] = TIMERA
	pool_view_debug[1] = TIMERB
	pool_view_debug2[2] = ball_turn_mass
	pool_view_debug2[3] = pool_stop_velocity
	pool_view_debug2[4] = pool_stop_rotation_velocity
	pool_view_debug[5] = ball_strikes_cushion

	VIEW_INTEGER_VARIABLE pool_view_debug[0] TIMERA
	VIEW_INTEGER_VARIABLE pool_view_debug[1] TIMERB
	VIEW_FLOAT_VARIABLE pool_view_debug2[2] ball_turn_mass
	VIEW_FLOAT_VARIABLE pool_view_debug2[3] pool_stop_velocity
	VIEW_FLOAT_VARIABLE pool_view_debug2[4] pool_stop_rotation_velocity
	VIEW_INTEGER_VARIABLE pool_view_debug[5] ball_strikes_cushion

ENDIF

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
	IF player1_is_human = 1
		player1_is_human = 0
		WRITE_DEBUG cheat_player1_not_human
	ELSE
		player1_is_human = 1
		WRITE_DEBUG cheat_player1_human
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
	IF DOES_OBJECT_EXIST p_ball[0]
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.0 0.01 0.0 x y z
		SET_OBJECT_COORDINATES p_ball[0] x y z
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
	IF DOES_OBJECT_EXIST p_ball[0]
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.0 -0.01 0.0 x y z
		SET_OBJECT_COORDINATES p_ball[0] x y z
	ENDIF
ENDIF

RETURN


// *************************************************************************************************************
//										   WAGER SCREEN  
// *************************************************************************************************************

pl_stage_1:
		
    // fake
	IF m_goals = -1
		CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue2
	ENDIF

	// initialisation wager window
	IF m_goals = 0

		DISPLAY_HUD TRUE
		DISPLAY_RADAR FALSE
		
		// check the player has enough cash
		STORE_SCORE player1 bj_players_cash
		IF bj_players_cash < pool_min_bet
			quit = 1
			CLEAR_PRINTS
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			GOTO pl_stage_1_end
		ENDIF

		CLEAR_AREA table_x table_y table_z 15.0 TRUE
		SET_OBJECT_COORDINATES table table_x table_y table_z

		// set table max mins										 // 0.8557
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.9098 -0.4589 0.70 pool_table_min_x pool_table_min_y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.9242 0.4809 0.70   pool_table_max_x pool_table_max_y z

		IF pool_table_min_x > pool_table_max_x
			x = pool_table_min_x
			pool_table_min_x = pool_table_max_x
			pool_table_max_x = x
		ENDIF
		IF pool_table_min_y > pool_table_max_y
			y = pool_table_min_y
			pool_table_min_y = pool_table_max_y
			pool_table_max_y = y
		ENDIF
		SET_POOL_TABLE_COORDS pool_table_min_x pool_table_min_y z pool_table_max_x pool_table_max_y z

		// get light position
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 1.5 light_x light_y light_z

		// get pocket positions work out middle pockets
		GET_DISTANCE_BETWEEN_COORDS_2D pool_table_min_x pool_table_min_y pool_table_max_x pool_table_min_y temp_float
		GET_DISTANCE_BETWEEN_COORDS_2D pool_table_min_x pool_table_min_y pool_table_min_x pool_table_max_y temp_float2 
		
		IF temp_float > temp_float2
			temp_float = pool_table_min_x + pool_table_max_x
			temp_float /= 2.0	
			pocket_x[1] = temp_float
			pocket_y[1] = pool_table_min_y
			pocket_x[4] = temp_float
			pocket_y[4] = pool_table_max_y
		ELSE
			temp_float = pool_table_min_y + pool_table_max_y
			temp_float /= 2.0	
			pocket_x[1] = pool_table_min_x
			pocket_y[1] = temp_float
			pocket_x[4] = pool_table_max_x
			pocket_y[4] = temp_float				
		ENDIF 
		
		// remaining pockets
		pocket_x[0] = pool_table_min_x
		pocket_y[0]	= pool_table_min_y

		pocket_x[2] = pool_table_min_x
		pocket_y[2]	= pool_table_max_y

		pocket_x[3] = pool_table_max_x
		pocket_y[3]	= pool_table_max_y

		pocket_x[5] = pool_table_max_x
		pocket_y[5]	= pool_table_min_y

		// set ball radius
		pool_ball_radius = 0.037
		pool_ball_diameter = pool_ball_radius * 2.0

		// delete all balls
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
			    DELETE_OBJECT p_ball[temp_int]
			ENDIF
		temp_int++
		ENDWHILE

		// cue ball
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.2734375 -0.0546875 	0.9296875  x y z	 //0.9296875
		CREATE_OBJECT_NO_OFFSET K_POOLBALLCUE x y z p_ball[0]
		GET_OBJECT_HEADING table heading
		heading += 270.0
		SET_OBJECT_HEADING p_ball[0] heading

		// other balls
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.3408 -0.0284 0.9297  ball_tri_x ball_tri_y ball_tri_z	//0.9297
		GET_OBJECT_HEADING table ball_tri_h 
		ball_tri_h += 90.0
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT01	ball_tri_x ball_tri_y ball_tri_z p_ball[1]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT02 0.0 0.0 0.0 p_ball[2]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT03 0.0 0.0 0.0 p_ball[3]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT04 0.0 0.0 0.0 p_ball[4]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT05 0.0 0.0 0.0 p_ball[5]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT06 0.0 0.0 0.0 p_ball[6]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT07 0.0 0.0 0.0 p_ball[7]
		CREATE_OBJECT_NO_OFFSET K_POOLBALL8 	0.0 0.0 0.0 p_ball[8]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP01 0.0 0.0 0.0 p_ball[9]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP02 0.0 0.0 0.0 p_ball[10]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP03 0.0 0.0 0.0 p_ball[11]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP04 0.0 0.0 0.0 p_ball[12]	
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP05 0.0 0.0 0.0 p_ball[13]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP06 0.0 0.0 0.0 p_ball[14]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP07 0.0 0.0 0.0 p_ball[15]
		GOSUB update_ball_triangle_position

		DISPLAY_RADAR FALSE
		SET_PLAYER_CONTROL player1 OFF
		CLEAR_HELP

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.5261 -1.9684 1.6370 x y z
		SET_FIXED_CAMERA_POSITION  x y z 0.0 0.0 0.0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.0746 -1.0853 1.5092 x y z
		POINT_CAMERA_AT_POINT x y z JUMP_CUT

		IF NOT IS_CHAR_DEAD opp
			CLEAR_CHAR_TASKS_IMMEDIATELY opp
			DROP_OBJECT opp FALSE
			IF DOES_OBJECT_EXIST active_initial_cue
				DELETE_OBJECT active_initial_cue
			ENDIF
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0219 -1.1236 0.0363 x y z
			SET_CHAR_COORDINATES opp x y z
			temp_float = table_h + 130.0
			SET_CHAR_HEADING  opp temp_float
			OPEN_SEQUENCE_TASK temp_seq
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.5261 -1.9684 1.6370 x y z
				TASK_LOOK_AT_COORD -1 x y z 999999
				IF quit = 0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Idle_Stance POOL 1000.0 TRUE FALSE FALSE TRUE -1
				ENDIF
			CLOSE_SEQUENCE_TASK	temp_seq
			PERFORM_SEQUENCE_TASK opp temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		IF NOT DOES_OBJECT_EXIST cue2
			CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue2
			SET_OBJECT_COLLISION cue2 FALSE
		ENDIF
		
		IF IS_PLAYER_PLAYING player1
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -1.9295 -2.2083 0.0363 x y z
			SET_CHAR_COORDINATES scplayer x y z
			player1_home_x = x
			player1_home_y = y
			player1_home_z = z
			temp_float = table_h + 390.0
			SET_CHAR_HEADING scplayer temp_float
		ENDIF

		object_anim_char = opp
		object_anim_object = cue2
		GOSUB pool_update_object_anims
	
		SET_SCRIPT_COOP_GAME FALSE

		pl_stage_1_end:
		
		START_NEW_SCRIPT cleanup_audio_lines

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

	m_goals++
	ENDIF
	
	// choose your wager
	IF m_goals = 1

		IF NOT IS_CHAR_DEAD opp
			IF IS_CHAR_MODEL opp BMYPOL1
				
				GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
				SWITCH temp_int
					CASE 0
						$audio_string    = &POOL_B1
						audio_sound_file = SOUND_POOL_B1  // Let’s make a little wager.
					BREAK
					CASE 1
						$audio_string    = &POOL_B2
						audio_sound_file = SOUND_POOL_B2 // Game of pool ain’t the same without a little money involved.
					BREAK
					CASE 2
						$audio_string    = &POOL_B3
						audio_sound_file = SOUND_POOL_B3 // Show me your the color of your money
					BREAK
				ENDSWITCH
				START_NEW_SCRIPT audio_line opp 0 1 1 0  

			ELSE

				GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
				SWITCH temp_int
					CASE 0
						$audio_string    = &POOL_B4
						audio_sound_file = SOUND_POOL_B4  // I’ll only play if you want to make it interesting.
					BREAK
					CASE 1
						$audio_string    = &POOL_B5
						audio_sound_file = SOUND_POOL_B5  // You want to place a small wager?
					BREAK
					CASE 2
						$audio_string    = &POOL_B6
						audio_sound_file = SOUND_POOL_B6  // C’mon, put your money where your mouth is.
					BREAK
				ENDSWITCH
				START_NEW_SCRIPT audio_line opp 0 1 1 0 

			ENDIF
		ENDIF

		USE_TEXT_COMMANDS TRUE
		TIMERA = 0	  
		m_goals++
	ENDIF	
	
	// set initial wager drawing variables
	IF m_goals = 2
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		OR IS_BUTTON_PRESSED PAD1 CROSS 
				
			CLEAR_PRINTS
							
			IF IS_PLAYER_PLAYING player1												
				STORE_SCORE player1 pl_players_total_cash
			ENDIF						
			
			SET_HELP_MESSAGE_BOX_SIZE 200

			PRINT_HELP_FOREVER PL_H1  

			pool_min_bet = 50																	 
			pool_max_bet = 1000   

			pl_refund =	pool_min_bet	
			pl_initial_stake = pool_min_bet
			pl_current_wager = pool_min_bet
			
			temp_int = pool_min_bet * -1
			ADD_SCORE player1 temp_int
			SWITCH_WIDESCREEN OFF

			START_NEW_SCRIPT cleanup_audio_lines

			m_goals++																 
		ENDIF
	ENDIF

	// draw wager window
	IF m_goals = 3
	    

		GOSUB pool_draw_window_1			
        STORE_SCORE player1 bj_players_cash
			

		// ' X '  to increase bet
		
		IF IS_BUTTON_PRESSED PAD1 SQUARE
			IF NOT cross_is_pressed = 1 
			AND NOT cross_is_pressed = -1

				// sort out betting step
				IF pl_current_wager >= 10000
					pl_bet_step = 1000
				ELSE
					IF pl_current_wager >= 1000
						pl_bet_step = 100
					ELSE
						IF pl_current_wager >= 100
							pl_bet_step = 10
						ELSE
							IF pl_current_wager = 0
								pl_bet_step = 2
							ELSE
								pl_bet_step = 1	
							ENDIF
						ENDIF
					ENDIF
				ENDIF


				// check betting step doesn't go over players cash
				IF pl_bet_step > bj_players_cash
					pl_bet_step = bj_players_cash
				ENDIF

				// check betting step won't push us over the maximum bet
				temp_int = pl_current_wager + pl_bet_step
				IF temp_int > pool_max_bet
					pl_bet_step = pool_max_bet - pl_current_wager
				ENDIF

				// take cash off player
				IF pl_bet_step < 0
					pl_bet_step *= -1
				ENDIF

				pl_current_wager += pl_bet_step
				pl_refund        += pl_bet_step
				pl_initial_stake += pl_bet_step
				pl_bet_step *= -1
				ADD_SCORE player1 pl_bet_step
					
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

			// reset cross button m_stage
			IF NOT cross_is_pressed = 0 
				cross_is_pressed = 0	
			ENDIF

			// remove wager
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
				IF NOT square_is_pressed = 1

					// sort out betting step
					IF pl_current_wager > 10000
						pl_bet_step = -1000
					ELSE
						IF pl_current_wager > 1000
							pl_bet_step = -100
						ELSE
							IF pl_current_wager > 100
								pl_bet_step = -10
							ELSE
								pl_bet_step = -1
							ENDIF
						ENDIF
					ENDIF

					// check bet step wont go below minimum bet
					temp_int = pl_current_wager + pl_bet_step
					IF temp_int < pool_min_bet
						pl_bet_step = pl_current_wager - pool_min_bet
						pl_bet_step *= -1
					ENDIF
					
					// update cash
					pl_current_wager += pl_bet_step
					pl_refund        += pl_bet_step
					pl_initial_stake += pl_bet_step
					pl_bet_step *= -1
					ADD_SCORE player1 pl_bet_step
				
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

		
		// ' O '  to ok the bet
		
		IF IS_BUTTON_PRESSED PAD1 CROSS
			IF NOT IS_CHAR_DEAD	opp 
				SET_CHAR_MONEY opp pl_initial_stake
			ENDIF

			pl_refund = 0
			two_players = 0
			m_goals = 99
		
		ELSE

			// check if 2 player mode was activated
			IF IS_BUTTON_PRESSED PAD2 START
				DISABLE_2ND_PAD_FOR_DEBUG TRUE
				SET_SCRIPT_COOP_GAME TRUE
				disable_debug = 1 
				ADD_SCORE player1 pl_refund
				pl_refund = 0
				two_players = 1
				m_goals = 99
			ENDIF

		ENDIF


		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
		    
			IF triangle_is_pressed = 0
				// set chars to their home position
				IF NOT IS_CHAR_DEAD player1_char
					player1_home_z += 1.1
					CLEAR_CHAR_TASKS_IMMEDIATELY player1_char
					GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
					SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
					SET_CHAR_HEADING player1_char player1_home_h
				ENDIF
				IF NOT IS_CHAR_DEAD player2_char
					player2_home_z += 1.1
					CLEAR_CHAR_TASKS_IMMEDIATELY player2_char
					GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
					SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
					SET_CHAR_HEADING player2_char player2_home_h
				ENDIF
				CLEAR_HELP
				quit = 1
				triangle_is_pressed = 1
			ENDIF
		ELSE
			IF triangle_is_pressed = 1
				triangle_is_pressed = 0
			ENDIF
		ENDIF

	ENDIF

	// exit
	IF m_goals = 99
		IF NOT IS_CHAR_DEAD opp
			CLEAR_LOOK_AT opp
		ENDIF

		DISPLAY_HUD FALSE
		CLEAR_HELP
		m_goals = 0
		m_stage++
	ENDIF

RETURN 


// *************************************************************************************************************
//											MINIGAME - start 
// *************************************************************************************************************

LVAR_INT player1_char player2_char current_char	current_cue
LVAR_INT player1_colour	player2_colour
LVAR_INT player1_is_human player2_is_human
LVAR_FLOAT player1_home_x player1_home_y player1_home_z	player1_home_h
LVAR_FLOAT player2_home_x player2_home_y player2_home_z	player2_home_h

LVAR_INT turn_player current_char_is_human turn_colour turn_is_scratch

pl_stage_2:
    
	// fake creates
	IF m_goals = -1
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 player1_char
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 player2_char
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 current_char
		CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue1
		CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue2
		CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 current_cue
	ENDIF
	
	// initialise game start
	IF m_goals = 0
		
		IF two_players = 0
			player1_colour		= POOL_COLOUR_NONE 				
			player2_colour		= POOL_COLOUR_NONE
			player1_is_human	= 1
			player2_is_human	= 0
		ELSE
			player1_colour		= POOL_COLOUR_NONE 				
			player2_colour		= POOL_COLOUR_NONE
			player1_is_human	= 1
			player2_is_human	= 1
		ENDIF

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 1.8 0.0 player1_home_x player1_home_y player1_home_z
		player1_home_h = table_h + 180.0

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 -1.8 0.0 player2_home_x player2_home_y player2_home_z
		player2_home_h = table_h

		player1_char = scplayer
		player2_char = opp

		// create cues
		IF NOT DOES_OBJECT_EXIST cue1
			CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue1
			SET_OBJECT_COLLISION cue1 FALSE
		ENDIF
		IF NOT DOES_OBJECT_EXIST cue2
			CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 cue2
			SET_OBJECT_COLLISION cue2 FALSE
		ENDIF			

		// set chars to their home position
		IF NOT IS_CHAR_DEAD player1_char
			SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
			SET_CHAR_HEADING player1_char player1_home_h
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE player1_char TRUE
		ENDIF
		IF NOT IS_CHAR_DEAD player2_char
			SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
			SET_CHAR_HEADING player2_char player2_home_h
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE player2_char TRUE
		ENDIF

		// set current char
		turn_player = 1
		current_char = player1_char
		current_cue = cue1			
		current_char_is_human = player1_is_human
		turn_colour = player1_colour
		turn_is_scratch = 1
		
		current_pad = PAD1

		first_moving_ball = -1
		cue_ball_hit_cushion_first = 0

		camera_mode = 1

		this_is_break = 1

		// go to next stage
		m_goals = 0
		m_stage++
	ENDIF
RETURN

		
// ***************************************************************************************************
//				CHALKING CUE
// ***************************************************************************************************

pl_stage_3:
	
    LVAR_INT skip_chalk_cue
	IF skip_chalk_cue = 0
		IF m_goals = 0 

			// set chars to their home position
			IF NOT IS_CHAR_DEAD player1_char
				player1_home_z += 1.0
				GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				SET_CHAR_HEADING player1_char player1_home_h
			ENDIF
			IF NOT IS_CHAR_DEAD player2_char
				player2_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				SET_CHAR_HEADING player2_char player2_home_h
			ENDIF

			IF NOT IS_CHAR_DEAD current_char
				DROP_OBJECT current_char FALSE	
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char 1.0 3.0 1.0 x y z
				SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
				POINT_CAMERA_AT_CHAR current_char FIXED JUMP_CUT
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_ChalkCue POOL 1000.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Idle_Stance POOL 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK current_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
				TIMERA = 0
				m_goals++
			ENDIF 
																									  
		ENDIF

		IF m_goals = 1
			IF NOT IS_CHAR_DEAD current_char
				GET_SCRIPT_TASK_STATUS current_char PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
				OR TIMERA > 3000
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF

		IF TIMERA > 500
			IF IS_BUTTON_PRESSED current_pad CROSS
				IF NOT IS_CHAR_DEAD current_char
					cross_is_pressed = 1
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD current_char
			m_goals = 99	 
		ENDIF
	ENDIF

	IF m_goals = 99
		CLEAR_HELP
		TIMERA = 0
		m_stage++
		m_goals = 0
	ENDIF

	IF IS_BUTTON_PRESSED current_pad TRIANGLE  // Bail on the chalking; quits the game.
		
		IF triangle_is_pressed = 0
		AND triangle_timer > 1000
		
			// set chars to their home position
			IF NOT IS_CHAR_DEAD player1_char
				player1_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				SET_CHAR_HEADING player1_char player1_home_h
			ENDIF
			IF NOT IS_CHAR_DEAD player2_char
				player2_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				SET_CHAR_HEADING player2_char player2_home_h
			ENDIF
			CLEAR_HELP
			quit = 1
			triangle_is_pressed = 1
		ENDIF
	
	ELSE
		IF triangle_is_pressed = 1
			triangle_is_pressed = 0
		ENDIF
		triangle_timer = 0
	ENDIF

RETURN


// ***************************************************************************************************
//				SCRATCH
// ***************************************************************************************************		

pl_stage_4:

	IF NOT turn_is_scratch = 0
	AND current_char_is_human = 1
		GOSUB pool_draw_window_4
	ENDIF

	// 2. IF scratch - place white
		
	IF NOT turn_is_scratch = 0
		IF ball_potted[0] = 1
			IF NOT DOES_OBJECT_EXIST p_ball[0]
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.35 0.0 0.9296875 x y z
				safe_x = x
				safe_y = y
				GOSUB check_position_is_safe
				x = safe_x
				y = safe_y
				CREATE_OBJECT_NO_OFFSET K_POOLBALLCUE x y z p_ball[0]
				GET_OBJECT_HEADING table heading
				heading += 270.0
				SET_OBJECT_HEADING p_ball[0] heading
			ENDIF
			ball_potted[0] = 0
		ENDIF

		IF IS_BUTTON_PRESSED current_pad SQUARE
			IF square_is_pressed = 0
				IF aim_help_flag = 2
					aim_help_flag = 0
				ELSE	
					help_timer = 3001		
				ENDIF
				square_is_pressed = 1
			ENDIF
		ELSE
			IF square_is_pressed = 1
				square_is_pressed = 0
			ENDIF
		ENDIF

	ENDIF

	// if scratch type 1
	IF turn_is_scratch = 1
		IF m_goals = 0
			// if human
			IF current_char_is_human = 1
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.35 0.0 0.0  x y z
				GET_OBJECT_HEADING table heading
				heading += 270.0
				SET_OBJECT_COORDINATES p_ball[0] x y ball_tri_z 
				SET_OBJECT_HEADING p_ball[0] heading
				// show help
				TIMERA = 0
				m_goals = 99
				GOTO pl_stage_4_end
			ELSE
				// if cpu
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.35 0.0 0.0  x y z
				GET_OBJECT_HEADING table heading
				heading += 270.0
				SET_OBJECT_COORDINATES p_ball[0] x y ball_tri_z 
				SET_OBJECT_HEADING p_ball[0] heading
				m_goals = 99
				GOTO pl_stage_4_end
			ENDIF
		ENDIF
	ENDIF
	
	// if scratch type 2
	IF turn_is_scratch = 2
		IF m_goals = 0
			// if human
			IF current_char_is_human = 1
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.35 0.0 0.0  x y z
				GET_OBJECT_HEADING table heading
				heading += 270.0
				
				safe_x = x
				safe_y = y
				GOSUB check_position_is_safe
				x = safe_x
				y = safe_y

				SET_OBJECT_COORDINATES p_ball[0] x y ball_tri_z 
				SET_OBJECT_HEADING p_ball[0] heading
				// show help
				TIMERA = 0
				m_goals++

				aim_help_flag = 0
				help_timer = 0

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 -0.001 2.5 x y z
				SET_FIXED_CAMERA_POSITION  x y z 0.0 0.0 0.0
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 0.001 0.0 x y z
				POINT_CAMERA_AT_POINT x y z JUMP_CUT

			ELSE
				// if cpu
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.35 0.0	0.0  x y z
				GET_OBJECT_HEADING table heading
				heading += 270.0

				safe_x = x
				safe_y = y
				GOSUB check_position_is_safe
				x = safe_x
				y = safe_y

				SET_OBJECT_COORDINATES p_ball[0] x y ball_tri_z 
				SET_OBJECT_HEADING p_ball[0] heading
				m_goals = 99
				GOTO pl_stage_4_end
			ENDIF
		ENDIF
		
		
		// Move the cue ball placement for ball in hand.

		IF m_goals = 1
			
			// print help

			GET_OBJECT_COORDINATES p_ball[0] x y z

			temp_int = 0

			IF IS_BUTTON_PRESSED current_pad LEFTSTICKX
			OR IS_BUTTON_PRESSED current_pad LEFTSTICKY
				temp_int = 1
			ENDIF

			IF temp_int = 1

				LVAR_FLOAT rstk_y_f rstk_x_f

		 		GET_POSITION_OF_ANALOGUE_STICKS current_pad LStickX LStickY RStickX RStickY
		 		
				rstk_x_f =# LStickX 
		 		rstk_y_f =# LStickY
		 		rstk_x_f /= 128.0
		 		rstk_y_f /= -128.0

				rstk_x_f *= 0.01
		 		rstk_y_f *= 0.01
				
				GOSUB pl_update_camera_vector
				
				cam_nvec_x *= rstk_y_f
				cam_nvec_y *= rstk_y_f

		 		x += cam_nvec_x
		 		y += cam_nvec_y

				cam_rvec_x *= rstk_x_f
				cam_rvec_y *= rstk_x_f

				x += cam_rvec_x
		 		y += cam_rvec_y
								
				
				// check new position is safe
				
				safe_x = x
				safe_y = y
				
				GOSUB check_position_is_safe
				
				x = safe_x
				y = safe_y

				// set ball to new position
			
				SET_OBJECT_COORDINATES p_ball[0] x y ball_tri_z
			ENDIF

			IF IS_BUTTON_PRESSED current_pad CROSS
				IF cross_is_pressed = 0	
					m_goals = 99
					cross_is_pressed = 1
					GOTO pl_stage_4_end
				ENDIF
			ELSE
				IF cross_is_pressed = 1
					cross_is_pressed = 0
				ENDIF
			ENDIF

		ENDIF

	ENDIF

	IF turn_is_scratch = 0
		m_goals = 99
		GOTO pl_stage_4_end
	ENDIF

	IF IS_BUTTON_PRESSED current_pad TRIANGLE  
	    
		IF triangle_is_pressed = 0  
		AND triangle_timer > 1000

	    	// set chars to their home position
			IF NOT IS_CHAR_DEAD player1_char
				player1_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				SET_CHAR_HEADING player1_char player1_home_h
			ENDIF
			IF NOT IS_CHAR_DEAD player2_char
				player2_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				SET_CHAR_HEADING player2_char player2_home_h
			ENDIF
			CLEAR_HELP
			quit = 1
			triangle_is_pressed = 1
		ENDIF
	
	ELSE
		IF triangle_is_pressed = 1
			triangle_is_pressed = 0
		ENDIF
		triangle_timer = 0
	ENDIF

	pl_stage_4_end:
	
	IF m_goals = 99
		m_stage++
		m_goals = 0
		CLEAR_HELP
	ENDIF
		
RETURN
		
// ******************************************************************************************************
//			AIMING
// ******************************************************************************************************		

pl_stage_5:
    
	LVAR_INT camera_mode
	LVAR_INT select_is_pressed

	
	// 3. Aiming - show overhead / or behind ball cam - using select
	LVAR_INT aim_cue  
	IF current_char_is_human = 1

		GOSUB pool_draw_window_3

		// fake create
		IF m_goals = -1
			CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 aim_cue
		ENDIF

		IF m_goals = 0
			// set chars to their home position
			IF NOT IS_CHAR_DEAD player1_char
				player1_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				SET_CHAR_HEADING player1_char player1_home_h
			ENDIF
			IF NOT IS_CHAR_DEAD player2_char
				player2_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				SET_CHAR_HEADING player2_char player2_home_h
			ENDIF

			// hide current aiming dude
			IF NOT IS_CHAR_DEAD current_char
				GET_CHAR_COORDINATES current_char x y z
				z += -10.0
				SET_CHAR_COORDINATES current_char x y z
				FREEZE_CHAR_POSITION current_char TRUE
			ENDIF

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 -0.001 2.5 x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 0.001 0.0 x y z
			
			IF DOES_OBJECT_EXIST p_ball[0]
				GET_OBJECT_HEADING p_ball[0] heading
				SET_OBJECT_ROTATION p_ball[0] 0.0 0.0 heading
			ENDIF
			IF NOT DOES_OBJECT_EXIST aim_cue
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 -10.0 x y z
				CREATE_OBJECT_NO_OFFSET K_POOLQ2 x y z aim_cue
				SET_OBJECT_DYNAMIC aim_cue FALSE
				SET_OBJECT_COLLISION aim_cue FALSE
				SET_OBJECT_DRAW_LAST aim_cue TRUE
			ELSE
				SET_OBJECT_DYNAMIC aim_cue FALSE
				SET_OBJECT_COLLISION aim_cue FALSE
				SET_OBJECT_DRAW_LAST aim_cue TRUE
			ENDIF

			
			help_timer = 0
			aim_help_flag = 0

 			
			// find ball to aim at
			
			IF back_from_shooting = 0
				LVAR_INT ball_to_aim_at
				ball_to_aim_at = -1
				temp_float = 99999.9
				IF DOES_OBJECT_EXIST p_ball[0]
					GET_OBJECT_COORDINATES p_ball[0] x y z
					IF turn_colour = POOL_COLOUR_NONE
						// get nearest ball
						temp_int = 1
						WHILE temp_int < 16
							IF DOES_OBJECT_EXIST p_ball[temp_int]
								GET_OBJECT_COORDINATES p_ball[temp_int] x2 y2 z2
								GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2
								IF temp_float2 < temp_float
									//WRITE_DEBUG_WITH_INT nearest_ball_neg_1 temp_int
									ball_to_aim_at = temp_int
									temp_float = temp_float2
								ENDIF
							ENDIF		
						temp_int++
						ENDWHILE
					ELSE
						IF turn_colour = POOL_COLOUR_SPOTS
							// get nearest spot
							temp_int = 1
							WHILE temp_int < 8
								IF DOES_OBJECT_EXIST p_ball[temp_int]
									GET_OBJECT_COORDINATES p_ball[temp_int] x2 y2 z2
									GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2
									IF temp_float2 < temp_float
										//WRITE_DEBUG_WITH_INT nearest_ball_1 temp_int
										ball_to_aim_at = temp_int
										temp_float = temp_float2
									ENDIF
								ENDIF		
							temp_int++
							ENDWHILE

						ELSE
							IF turn_colour = POOL_COLOUR_STRIPES
								// get nearest stripe
								temp_int = 9
								WHILE temp_int < 16
									IF DOES_OBJECT_EXIST p_ball[temp_int]
										GET_OBJECT_COORDINATES p_ball[temp_int] x2 y2 z2
										GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2
										IF temp_float2 < temp_float
											//WRITE_DEBUG_WITH_INT nearest_ball_0 temp_int
											ball_to_aim_at = temp_int
											temp_float = temp_float2
										ENDIF
									ENDIF		
								temp_int++
								ENDWHILE
							ELSE
								IF turn_colour = POOL_COLOUR_BLACK
									// aim at black
									IF DOES_OBJECT_EXIST p_ball[8]
										//WRITE_DEBUG_WITH_INT nearest_ball_black 8
										ball_to_aim_at = 8	
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// point cue ball towards aiming ball
				IF NOT ball_to_aim_at = -1
				AND ball_to_aim_at < 16
					IF DOES_OBJECT_EXIST p_ball[0]
						GET_OBJECT_COORDINATES p_ball[0] x y z
						IF DOES_OBJECT_EXIST p_ball[ball_to_aim_at]
							GET_OBJECT_COORDINATES p_ball[ball_to_aim_at] x2 y2 z2
						ENDIF
						vec_x = x2 - x
						vec_y = y2 - y
						GET_HEADING_FROM_VECTOR_2D vec_x vec_y temp_float
						SET_OBJECT_HEADING p_ball[0] temp_float
					ENDIF
				ENDIF 

			ELSE
				back_from_shooting = 0
			ENDIF
				

			projection_calculated = 0
			GOSUB pool_draw_projection

			m_goals++
		ENDIF

		IF m_goals = 1
			IF IS_BUTTON_PRESSED current_pad SELECT
				IF select_is_pressed = 0
					IF camera_mode = 0
						camera_mode = 1
						select_is_pressed = 1
					ELSE
						camera_mode = 0
						select_is_pressed = 1
					ENDIF
					help_timer = 0
				ENDIF


			ELSE
				IF select_is_pressed = 1
					select_is_pressed = 0
				ENDIF
			ENDIF
		ENDIF


		IF camera_mode = 0
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 -0.001 2.5 x y z
			SET_FIXED_CAMERA_POSITION  x y z 0.0 0.0 0.0
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.00 0.001 0.0 x y z
			POINT_CAMERA_AT_POINT x y z JUMP_CUT
		ENDIF
		IF camera_mode = 1
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.00 -0.5 0.3 x y z
			SET_FIXED_CAMERA_POSITION  x y z 0.0 0.0 0.0
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.00 0.5 0.0 x y z
			POINT_CAMERA_AT_POINT x y z JUMP_CUT
		ENDIF

		IF camera_mode = 0
			IF IS_BUTTON_PRESSED current_pad SQUARE
				IF square_is_pressed = 0
					IF aim_help_flag = 2
						aim_help_flag = 0
					ELSE	
						help_timer = 3001		
					ENDIF
					square_is_pressed = 1
				ENDIF
			ELSE
				IF square_is_pressed = 1
					square_is_pressed = 0
				ENDIF
			ENDIF
		ENDIF

		// set aim --------
		IF m_goals = 2
			x = 0.0
			y = -0.5
			z = -1.7

			IF IS_BUTTON_PRESSED current_pad RIGHTSTICKX
			OR IS_BUTTON_PRESSED current_pad RIGHTSTICKY
				GET_POSITION_OF_ANALOGUE_STICKS current_pad LStickX LStickY RStickX RStickY
				
				temp_float =# RStickX
				temp_float /= 128.0
				temp_float *= 0.2
				x += temp_float
				temp_float =# RStickY
				temp_float /= 128.0
				IF temp_float < -0.1
					temp_float = -0.1
				ENDIF
				temp_float *= 0.5
				z += temp_float
			ENDIF

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] x y z x y z
			SET_OBJECT_COORDINATES camera_object x y z
 			GET_OBJECT_COORDINATES p_ball[0] x2 y2 z2
			
			vec_x = x2 - x
			vec_y = y2 - y
			GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
			SET_OBJECT_HEADING camera_object heading

			IF IS_BUTTON_PRESSED current_pad SELECT
				RESTORE_CAMERA_JUMPCUT
				IF DOES_OBJECT_EXIST camera_object
					DELETE_OBJECT camera_object
				ENDIF
				m_goals = 0 
			ENDIF
		ENDIF

		IF IS_BUTTON_PRESSED current_pad LEFTSTICKX
			GET_OBJECT_HEADING p_ball[0] heading
			GET_POSITION_OF_ANALOGUE_STICKS current_pad LStickX LStickY RStickX RStickY
			// adjust cue balls heading
			temp_float =# LStickX
			temp_float /= 128.0
			temp_float *= 2.0
			heading += temp_float
			SET_OBJECT_ROTATION p_ball[0] 0.0 0.0 0.0
			SET_OBJECT_HEADING p_ball[0] heading
		ENDIF

		// draw aim	-------
		
		IF DOES_OBJECT_EXIST aim_cue
			IF camera_mode = 0
				BUTT_ANGLE = 0.0
				RAISE_CUE = 0.063
				CUE_X_CORRECTION = 0.0063
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] cue_x_correction -0.05 raise_cue x2 y2 z2
			ELSE
				BUTT_ANGLE = 22.5000 
				RAISE_CUE = 0.021
				CUE_X_CORRECTION = 0.0063
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] cue_x_correction -0.05 raise_cue x2 y2 z2
			ENDIF
			GET_OBJECT_HEADING p_ball[0] heading
			heading += 180.0
			SET_OBJECT_COORDINATES aim_cue x2 y2 z2
			IF camera_mode = 0
				SET_OBJECT_ROTATION aim_cue 0.0 0.0 heading
			ELSE
 				SET_OBJECT_ROTATION aim_cue butt_angle 0.0 heading
			ENDIF
		ENDIF

		// draw ball projection	 
		LVAR_INT projection_calculated
		LVAR_INT got_first_hit_ball
		IF NOT IS_BUTTON_PRESSED current_pad LEFTSTICKX
			projection_calculated = 0
		   	GOSUB pool_draw_projection
		ELSE
			IF NOT projection_calculated = 0
				temp_int = 0
				WHILE temp_int < 10
					IF DOES_OBJECT_EXIST proj_cue_ball_objects[temp_int]
						DELETE_OBJECT proj_cue_ball_objects[temp_int]
					ENDIF
				temp_int++
				ENDWHILE
			ENDIF

			projection_calculated = 0
			TIMERA = 0
		ENDIF  

		// goto shooting ----
		
		LVAR_FLOAT butt_angle  
		LVAR_FLOAT raise_cue
		LVAR_FLOAT cue_x_correction

		IF IS_BUTTON_PRESSED current_pad CROSS

			IF cross_is_pressed = 0
				IF DOES_OBJECT_EXIST aim_cue
					DELETE_OBJECT aim_cue
				ENDIF
				m_goals = 99
				cross_is_pressed = 1
			ENDIF
		ELSE
			IF cross_is_pressed = 1
				cross_is_pressed = 0
			ENDIF
		ENDIF

			// show help

		// AI ---
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
			GOSUB find_best_shot
			SET_OBJECT_HEADING p_ball[0] ai_shot_heading
		ENDIF
	
	ELSE
	// if cpu
		// get best shot
		GOSUB find_best_shot
		SET_OBJECT_HEADING p_ball[0] ai_shot_heading
		m_goals = 99
	ENDIF
	
	IF IS_BUTTON_PRESSED current_pad TRIANGLE
	    
		IF triangle_is_pressed = 0
		AND triangle_timer > 1000
		
			// set chars to their home position
			IF NOT IS_CHAR_DEAD player1_char
				player1_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player1_home_x player1_home_y player1_home_z player1_home_z
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				SET_CHAR_HEADING player1_char player1_home_h
			ENDIF
			IF NOT IS_CHAR_DEAD player2_char
				player2_home_z += 1.1
				GET_GROUND_Z_FOR_3D_COORD player2_home_x player2_home_y player2_home_z player2_home_z
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				SET_CHAR_HEADING player2_char player2_home_h
			ENDIF
			CLEAR_HELP
			quit = 1
			triangle_is_pressed = 1
		ENDIF
	
	ELSE
		IF triangle_is_pressed = 1
			triangle_is_pressed = 0
		ENDIF
		triangle_timer = 0
	ENDIF


	IF m_goals = 99

		temp_int = 0
		WHILE temp_int < 10
			IF DOES_OBJECT_EXIST proj_cue_ball_objects[temp_int]
				DELETE_OBJECT proj_cue_ball_objects[temp_int]
			ENDIF
		temp_int++
		ENDWHILE

		m_stage += 2
		m_goals = 0
		CLEAR_HELP
	ENDIF

RETURN



// ***************************************************************************************************
//				WALK TO TABLE 
// ***************************************************************************************************

pl_stage_6:
RETURN


// ******************************************************************************************************
//					SHOOTING 
// ******************************************************************************************************

pl_stage_7:
    
	GOSUB pool_draw_window_3

	// 5. set shot position and anim
	IF current_char_is_human = 1
	
		LVAR_INT back_from_shooting
		
		IF IS_BUTTON_PRESSED current_pad TRIANGLE  // Go back to the aiming screen.
			
			IF triangle_is_pressed  = 0
				m_stage = 5
				m_goals = 0
				triangle_is_pressed = 1
				back_from_shooting = 1
			ENDIF
		
		ELSE
			IF triangle_is_pressed = 1
				triangle_is_pressed = 0
			ENDIF

			//GOSUB display_help_shooting
			
			LVAR_FLOAT requested_anim_time current_anim_time stance_anim_speed
			LVAR_FLOAT shot_power
			LVAR_INT shot_played  
			
			IF m_goals = 0
				GOSUB pool_init_shooting
				m_goals++
			ENDIF

			IF shot_played = 0
				GOSUB draw_ball_and_nib
			ENDIF

			IF shot_played = 0
				GOSUB pool_check_is_in_shooting_position
				
				IF is_in_shooting_position = 1
					
					IF IS_BUTTON_PRESSED current_pad RIGHTSTICKY
						
						GET_POSITION_OF_ANALOGUE_STICKS current_pad LStickX LStickY RStickX RStickY
						temp_float =# RStickY
						temp_float /= 128.0
						temp_float *= -0.2
						requested_anim_time = 0.2
						requested_anim_time += temp_float
					ELSE
						requested_anim_time = 0.2
					ENDIF

				ELSE
				    requested_anim_time = 0.0
				ENDIF
			ENDIF

			
			// Play the shot when they release the button.

			IF shot_played = 0
				IF requested_anim_time > 0.25 
					GOSUB pool_apply_shot
				ELSE
					GOSUB pool_update_animation_buffer
				ENDIF
			ENDIF


			LVAR_INT this_char_got_task	 
			IF m_goals = 1
				IF NOT IS_CHAR_DEAD current_char
					GOSUB pool_set_camera1
					m_goals++	
				ENDIF
			ENDIF

			IF m_goals = 2
				GOSUB pool_update_shoot_animations
			ENDIF

			IF m_goals = 3
				IF NOT IS_CHAR_DEAD current_char
					close_aim_stance_z += 1.1 //00.0
					GET_GROUND_Z_FOR_3D_COORD close_aim_stance_x close_aim_stance_y close_aim_stance_z close_aim_stance_z
					SET_CHAR_COORDINATES current_char close_aim_stance_x close_aim_stance_y close_aim_stance_z 
					SET_CHAR_HEADING current_char close_aim_stance_h
					SET_CHAR_COLLISION current_char TRUE
					TIMERA = 0
					m_goals = 99
				ENDIF
			ENDIF

			IF m_goals = 99
				CLEAR_HELP
				TIMERA = 0
				m_stage++ 
				m_goals = 0
			ENDIF

		ENDIF // pressed triangle

	ELSE // IS PLAYER CPU

		IF m_goals = 0
			GOSUB pool_init_shooting
			m_goals++
		ENDIF

		IF shot_played = 0
			GOSUB pool_check_is_in_shooting_position
			IF is_in_shooting_position = 1
				IF TIMERA < 2000
					requested_anim_time = 0.2
				ELSE
					IF TIMERA < 3000
						requested_anim_time = 0.0
					ELSE
						requested_anim_time = 1.0
					ENDIF
				ENDIF
			ELSE
				TIMERA = 0
				requested_anim_time = 0.0
			ENDIF
		ENDIF

		// play shot if current anim time goes above 0.25
		IF shot_played = 0
			IF requested_anim_time > 0.25
				GOSUB pool_apply_shot
			ELSE

				GOSUB pool_update_animation_buffer
			ENDIF
		ENDIF

		IF m_goals = 1
			// set chars position
			IF NOT IS_CHAR_DEAD current_char
				GOSUB pool_set_camera1
				m_goals++	
			ENDIF
		ENDIF

		IF m_goals = 2
			// hold stance position
			GOSUB pool_update_shoot_animations
		ENDIF

		IF m_goals = 3
			IF NOT IS_CHAR_DEAD current_char
				close_aim_stance_z += 1.1 //00.0
				GET_GROUND_Z_FOR_3D_COORD close_aim_stance_x close_aim_stance_y close_aim_stance_z close_aim_stance_z
				SET_CHAR_COORDINATES current_char close_aim_stance_x close_aim_stance_y close_aim_stance_z 
				SET_CHAR_HEADING current_char close_aim_stance_h
				SET_CHAR_COLLISION current_char TRUE
				TIMERA = 0
				m_goals = 99
			ENDIF
		ENDIF

		IF m_goals = 99
			CLEAR_HELP
			TIMERA = 0
			m_stage++ 
			m_goals = 0
		ENDIF
	ENDIF
RETURN


LVAR_INT is_in_shooting_position

pool_check_is_in_shooting_position:

	is_in_shooting_position = 0

	IF NOT IS_CHAR_DEAD current_char
		IF current_stance = 1
			IF IS_CHAR_PLAYING_ANIM current_char POOL_Short_Shot
				is_in_shooting_position = 1
			ENDIF
		ENDIF 
		IF current_stance = 2
			IF IS_CHAR_PLAYING_ANIM current_char POOL_Med_shot
				is_in_shooting_position = 1
			ENDIF
		ENDIF
		IF current_stance = 3
			IF IS_CHAR_PLAYING_ANIM current_char POOL_Long_Shot
				is_in_shooting_position = 1
			ENDIF
		ENDIF
		IF current_stance = 4
			IF IS_CHAR_PLAYING_ANIM current_char POOL_XLong_Shot
				is_in_shooting_position = 1
			ENDIF
		ENDIF
	ENDIF
RETURN

pool_update_shoot_animations:

	IF NOT IS_CHAR_DEAD current_char
		// hold stance position
		IF this_char_got_task = 0
			// close
			IF current_stance = 1
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Short_Start POOL 8.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Short_Shot POOL 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK current_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
			// medium
			IF current_stance = 2
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Med_Start POOL 8.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Med_shot POOL 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK current_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
			// far
			IF current_stance = 3
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Long_Start POOL 8.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Long_Shot POOL 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK current_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
			// v.far
			IF current_stance = 4
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_XLong_Start POOL 8.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_XLong_Shot POOL 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK current_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq	
			ENDIF
			this_char_got_task++
		ENDIF
	ENDIF

	// set anim position
	IF this_char_got_task = 1
		IF shot_played = 0
			IF NOT IS_CHAR_DEAD current_char
				GET_SCRIPT_TASK_STATUS current_char PERFORM_SEQUENCE_TASK temp_int
				IF NOT temp_int = FINISHED_TASK
					IF current_stance = 1
						IF IS_CHAR_PLAYING_ANIM current_char POOL_Short_Shot
							SET_CHAR_ANIM_CURRENT_TIME current_char POOL_Short_Shot current_anim_time 
							SET_CHAR_ANIM_SPEED current_char POOL_Short_Shot stance_anim_speed
						ENDIF
					ENDIF 
					IF current_stance = 2
						IF IS_CHAR_PLAYING_ANIM current_char POOL_Med_Shot
							SET_CHAR_ANIM_CURRENT_TIME current_char POOL_Med_Shot current_anim_time
							SET_CHAR_ANIM_SPEED current_char POOL_Med_Shot stance_anim_speed
						ENDIF
					ENDIF 
					IF current_stance = 3
						IF IS_CHAR_PLAYING_ANIM current_char POOL_Long_Shot
							SET_CHAR_ANIM_CURRENT_TIME current_char POOL_Long_Shot current_anim_time
							SET_CHAR_ANIM_SPEED current_char POOL_Long_Shot stance_anim_speed
						ENDIF
					ENDIF 
					IF current_stance = 4
						IF IS_CHAR_PLAYING_ANIM current_char POOL_XLong_Shot
							SET_CHAR_ANIM_CURRENT_TIME current_char POOL_XLong_Shot current_anim_time
							SET_CHAR_ANIM_SPEED current_char POOL_XLong_Shot stance_anim_speed
						ENDIF
					ENDIF 
	   			ELSE
					TIMERA = 0
					this_char_got_task++	
				ENDIF
			ENDIF
		ELSE
			TIMERA = 0
			this_char_got_task++	
		ENDIF
	ENDIF

	// wait for anim to finish
	IF this_char_got_task = 2
		IF NOT IS_CHAR_DEAD current_char
			GET_SCRIPT_TASK_STATUS current_char PERFORM_SEQUENCE_TASK temp_int
			IF NOT temp_int = FINISHED_TASK
			AND TIMERA < 5000
				// interpolate speed back to 1.0
				temp_float = 1.0 - stance_anim_speed
				temp_float /= 20.0
				stance_anim_speed += temp_float	
				IF stance_anim_speed < 0.8
					stance_anim_speed += 0.01	
				ENDIF
				IF stance_anim_speed < 1.2
				AND stance_anim_speed > 0.8
					stance_anim_speed = 1.0
				ENDIF				
				IF IS_CHAR_PLAYING_ANIM current_char POOL_Short_Shot
					SET_CHAR_ANIM_SPEED current_char POOL_Short_Shot stance_anim_speed
				ENDIF
				IF IS_CHAR_PLAYING_ANIM current_char POOL_Med_Shot
					SET_CHAR_ANIM_SPEED current_char POOL_Med_Shot stance_anim_speed
				ENDIF
				IF IS_CHAR_PLAYING_ANIM current_char POOL_Long_Shot
					SET_CHAR_ANIM_SPEED current_char POOL_Long_Shot stance_anim_speed
				ENDIF
				IF IS_CHAR_PLAYING_ANIM current_char POOL_XLong_Shot
					SET_CHAR_ANIM_SPEED current_char POOL_XLong_Shot stance_anim_speed
				ENDIF
			ELSE
				this_char_got_task++
			ENDIF
		ENDIF
	ENDIF

	IF this_char_got_task = 3
		IF NOT IS_CHAR_DEAD current_char
			IF quit = 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE current_char pool_idle_stance pool 4.0 TRUE FALSE FALSE FALSE -1
			ENDIF
		ENDIF
		m_goals++
	ENDIF
RETURN


pool_update_animation_buffer:

	// animation buffer - workout what current_anim_time should be at
	temp_float = requested_anim_time - current_anim_time
	IF temp_float >= 0.05
	OR temp_float <= -0.05
		temp_float /= 3.0
		IF temp_float < 0.01
		AND temp_float > -0.01
			IF temp_float > 0.0
				temp_float = 0.01
			ELSE
				temp_float = -0.01
			ENDIF		
		ENDIF
		current_anim_time += temp_float
	ELSE
		current_anim_time = requested_anim_time
	ENDIF
RETURN

pool_init_shooting:

	shot_played = 0
	this_char_got_task = 0
	ball_sprite_x = 565.0
	ball_sprite_y = 386.0
	nib_sprite_x = ball_sprite_x
	nib_sprite_y = ball_sprite_y
	BALL_SPRITE_HEIGHT = 67.0000 
	BALL_SPRITE_WIDTH = 78.0000 
	NIB_SPRITE_HEIGHT = 19.0000 
	NIB_SPRITE_WIDTH = 16.0000
	stance_anim_speed = 0.0 
	current_anim_time = 0.0
	stance_anim_speed = 0.0
	requested_anim_time = 0.0
	TIMERA = 0
	TIMERB = 0
	
	IF NOT current_char_is_human = 0
		PRINT_HELP_FOREVER PL_H4
	ENDIF
	GOSUB update_stance_coords

RETURN

pool_set_camera1:

	SET_CHAR_COLLISION current_char FALSE  
	shoot_stance_z += 1.1
	GET_GROUND_Z_FOR_3D_COORD shoot_stance_x shoot_stance_y shoot_stance_z shoot_stance_z
	SET_CHAR_COORDINATES current_char shoot_stance_x shoot_stance_y shoot_stance_z
	SET_CHAR_HEADING current_char shoot_stance_h


	// check if other dude is standing behind
	IF player1_char = current_char
		IF NOT IS_CHAR_DEAD player1_char
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS player1_char 0.0 -1.5 0.0 x y z
			IF NOT IS_CHAR_DEAD player2_char
				SET_CHAR_COORDINATES player2_char player2_home_x player2_home_y player2_home_z
				IF LOCATE_CHAR_ANY_MEANS_2D player2_char x y 2.0 2.0 FALSE
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS player2_char -2.0 0.0 -1.0 x y z
					SET_CHAR_COORDINATES player2_char x y z
				ENDIF
				IF DOES_OBJECT_EXIST p_ball[0]
					TASK_LOOK_AT_OBJECT player2_char p_ball[0] 10000
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD player2_char
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS player2_char 0.0 -1.5 0.0 x y z
			IF NOT IS_CHAR_DEAD player1_char
				SET_CHAR_COORDINATES player1_char player1_home_x player1_home_y player1_home_z
				IF LOCATE_CHAR_ANY_MEANS_2D player1_char x y 2.0 2.0 FALSE
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS player1_char -2.0 0.0 -1.0 x y z
					SET_CHAR_COORDINATES player1_char x y z
					IF DOES_OBJECT_EXIST p_ball[0]
						TASK_LOOK_AT_OBJECT player1_char p_ball[0] 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF		
	ENDIF


	// setup camera
	IF current_stance = 1
		//x =	0.4
		//y =	-1.0
		//z =	1.0
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char 0.1532 -0.9733 1.1819 x y z
		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char -0.0267 -0.3468 0.4236 x y z
		POINT_CAMERA_AT_POINT x y z	JUMP_CUT 
		 
	ENDIF
	IF current_stance = 2
		x =	0.4
		y =	-1.0
		z =	1.0 
	ENDIF
	IF current_stance = 3
		x =	0.3
		y =	-0.5
		z =	1.0 
	ENDIF
	IF current_stance = 4
		x =	0.3
		y =	-0.5
		z =	1.0 
	ENDIF
	IF NOT current_stance = 1
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char x y z x y z
		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.0 0.3 0.0 x y z
		POINT_CAMERA_AT_POINT x y z	JUMP_CUT
	ENDIF


RETURN


pool_apply_shot:
    
	// work out shot power

	IF current_char_is_human = 1
		
		// This is how it used to be computed, where the player pushed the mouse forward quickly.
		
		shot_power = requested_anim_time - current_anim_time
		shot_power /= 0.1

		stance_anim_speed = shot_power * 2.0
		IF stance_anim_speed < 0.6
			stance_anim_speed = 0.6
		ENDIF
	    

	// Computer player; work out shot power.

	ELSE
		stance_anim_speed = ai_shot_power * 2.0
		IF stance_anim_speed < 0.6
			stance_anim_speed = 0.6
		ENDIF
		shot_power = ai_shot_power
	ENDIF

	// apply speed to ball
	IF DOES_OBJECT_EXIST p_ball[0]
		
		GET_OBJECT_HEADING p_ball[0] heading
		
		// convert heading to vector
		SIN heading temp_float
		vec_x = temp_float
		vec_x *= -1.0
		COS heading temp_float
		vec_y = temp_float
		vec_z	= 0.0
		
		// normalise vector
		GET_OBJECT_COORDINATES p_ball[0] x y z
		x2 = x + vec_x
		y2 = y + vec_y
		GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
		vec_x /= temp_float
		vec_y /= temp_float
		
		// multiply by power
		vec_x *= shot_power
		vec_y *= shot_power
		FREEZE_OBJECT_POSITION p_ball[0] FALSE
		SET_OBJECT_COLLISION p_ball[0] FALSE 	  
    	SET_OBJECT_DYNAMIC p_ball[0] FALSE

		SET_OBJECT_VELOCITY p_ball[0] vec_x vec_y vec_z
		first_moving_ball = -1
		cue_ball_hit_cushion_first = 0	
		ball_strikes_cushion = 0
		shot_timer = 0

		temp_int = 0
		WHILE temp_int < 16
			ball_is_stationary[temp_int] = 0
		temp_int++
		ENDWHILE
		
		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT p_ball[0] SOUND_POOL_HIT_WHITE
		
		// apply spin to ball according to nib

		LVAR_FLOAT cue_ball_spin_x
		LVAR_FLOAT cue_ball_spin_y

		cue_ball_spin_x = 0.0
		cue_ball_spin_y = 0.0

		x = nib_sprite_x - ball_sprite_x   
		y = nib_sprite_y - ball_sprite_y
		x /= ball_sprite_width
		y /= ball_sprite_height
		
		IF current_char_is_human = 0 
			GENERATE_RANDOM_FLOAT_IN_RANGE -0.2 0.2 x
			GENERATE_RANDOM_FLOAT_IN_RANGE -0.2 0.2 y
		ENDIF
		
		IF x > 0.2
		OR x < -0.2
		OR y > 0.2
		OR y < -0.2
		OR current_char_is_human = 0

			x *= shot_power
			y *= shot_power

			cue_ball_spin_x = x	* 0.5
			cue_ball_spin_y	= y	* -0.5

			x *= 10.0
			y *= 10.0 

			IF current_char_is_human = 1
				SET_OBJECT_ROTATION_VELOCITY p_ball[0] y 0.0 x
			ENDIF  

		ELSE
			cue_ball_spin_x = 0.0
			cue_ball_spin_y	= 0.0
		ENDIF

		nib_sprite_x = 559.0
		nib_sprite_y = 381.0

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "cue ball data = "
			SAVE_FLOAT_TO_DEBUG_FILE shot_power
			SAVE_FLOAT_TO_DEBUG_FILE x					
			SAVE_FLOAT_TO_DEBUG_FILE y
			SAVE_FLOAT_TO_DEBUG_FILE z
		ENDIF	

		SET_OBJECT_COLLISION p_ball[0] TRUE 	  
    	SET_OBJECT_DYNAMIC p_ball[0] TRUE

		IF NOT IS_CHAR_DEAD player1_char
			IF DOES_OBJECT_EXIST p_ball[0]
				TASK_LOOK_AT_OBJECT player1_char p_ball[0] 10000
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD player2_char
			IF DOES_OBJECT_EXIST p_ball[0]
				TASK_LOOK_AT_OBJECT player2_char p_ball[0] 10000
			ENDIF
		ENDIF
	ENDIF
		
	shot_played = 1

RETURN


// ******************************************************************************************************
//					6. DETERMINE OUTCOME  
// ******************************************************************************************************   		

pl_stage_8:
		
	// set camera to viewing position
	IF m_goals = 0	
		IF NOT IS_CHAR_DEAD current_char
			// get vec from char to table
			GET_OBJECT_COORDINATES table x y z
			vec_x = x - close_aim_stance_x
			vec_y = y - close_aim_stance_y
			x += vec_x
			y += vec_y
			z += 2.5
			SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
			GET_OBJECT_COORDINATES table x y z
			z += 0.5
			POINT_CAMERA_AT_POINT x y z JUMP_CUT
			TIMERA = 0

			// move char back a bit
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char 0.0 -0.2 0.0 x2 y2 z2
			GET_GROUND_Z_FOR_3D_COORD x2 y2 z2 z2
			GET_CHAR_HEADING current_char heading

			SET_CHAR_COORDINATES current_char safe_stance_x safe_stance_y safe_stance_z
			SET_CHAR_HEADING current_char heading

			IF quit = 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE current_char POOL_Idle_Stance POOL 1000.0 TRUE TRUE TRUE FALSE -1
			ENDIF

			m_goals++
		ENDIF
	ENDIF

	// wait for balls to stop moving
	IF m_goals = 1
		temp_int = 0
		temp_int2 = 0
		WHILE temp_int < 16
			IF ball_is_stationary[temp_int] = 1
				temp_int2 ++
			ENDIF					
		temp_int++
		ENDWHILE
		IF temp_int2 = 16
			m_goals++
			TIMERA = 0
		ENDIF
	ENDIF

	IF m_goals = 2
		IF TIMERA > 700
			m_goals = 99
		ENDIF
	ENDIF

	IF m_goals = 99
		CLEAR_HELP
		m_stage++
		m_goals = 0
	ENDIF

RETURN

// **********************************************************************************
//							CHECK FOR FOULS
// **********************************************************************************

pl_stage_9:
	
	LVAR_INT  balls_potted_this_turn[16]
	LVAR_INT  no_of_balls_potted_this_turn
	LVAR_INT  spots_potted stripes_potted
	LVAR_INT  foul_type
	// 	1. missed all balls	
	//	2. hit wrong ball first
	//	3. potted white
	//	4. potted wrong colour
	//	5. potted black out of turn
	//  6. rail foul
	
	// initialise variables for fouls
	IF m_goals = 0
		foul_type = 0
		m_goals++
	ENDIF
	
	// 	1. missed all balls
	IF m_goals = 1
		IF first_moving_ball = -1
			foul_type = 1
		ENDIF
		m_goals++
	ENDIF

	//	2. hit wrong ball first
	IF m_goals = 2
		IF NOT first_moving_ball = -1
			IF turn_colour = POOL_COLOUR_NONE
				IF first_moving_ball = 8
					foul_type = 2
				ENDIF
			ELSE
				IF turn_colour = POOL_COLOUR_SPOTS
					IF first_moving_ball >= 8
						foul_type = 2	
					ENDIF
				ELSE
					IF turn_colour = POOL_COLOUR_STRIPES
						IF first_moving_ball <= 8
							foul_type = 2	
						ENDIF
					ELSE
						IF turn_colour = POOL_COLOUR_BLACK
							IF NOT first_moving_ball = 8
								foul_type = 2
							ENDIF
						ENDIF	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		m_goals++
	ENDIF
		
	// 3. potted white
	IF m_goals = 3
		IF balls_potted_this_turn[0] = 1
			foul_type = 3		
		ENDIF
		m_goals++
	ENDIF

	// 4. potted wrong colour
	IF m_goals = 4
		IF no_of_balls_potted_this_turn = 1
			IF NOT foul_type = 3 
				IF turn_colour = POOL_COLOUR_NONE
                    IF NOT balls_potted_this_turn[8] = 1
						IF this_is_break = 0
						ENDIF
					ENDIF
				ELSE
					IF turn_colour = POOL_COLOUR_SPOTS
						IF stripes_potted > 0
					   		foul_type = 4
						ENDIF
					ELSE
						IF turn_colour = POOL_COLOUR_STRIPES
							IF spots_potted > 0 
						   		foul_type = 4
							ENDIF
						ELSE
							IF turn_colour = POOL_COLOUR_BLACK
								IF spots_potted > 0
								OR stripes_potted > 0
							   		foul_type = 4
								ENDIF
							ENDIF	
						ENDIF 
					ENDIF		
				ENDIF
			ENDIF
		ELSE
			IF no_of_balls_potted_this_turn > 1

					IF turn_colour = POOL_COLOUR_NONE
						IF spots_potted > 0
						AND stripes_potted = 0
							IF NOT balls_potted_this_turn[8] = 1
								IF this_is_break = 0
								ENDIF
							ENDIF
						ENDIF
						IF spots_potted = 0
						AND stripes_potted > 0
							IF NOT balls_potted_this_turn[8] = 1
								IF this_is_break = 0
								ENDIF
							ENDIF
						ENDIF
					ELSE
						IF turn_colour = POOL_COLOUR_SPOTS
							IF stripes_potted > 0
								foul_type = 4
							ENDIF
						ELSE
							IF turn_colour = POOL_COLOUR_STRIPES
								IF spots_potted > 0
									foul_type = 4
								ENDIF
							ELSE
								IF turn_colour = POOL_COLOUR_BLACK
									IF stripes_potted > 0
									OR spots_potted > 0
										foul_type = 4
									ENDIF
								ENDIF
							ENDIF			
						ENDIF 
					ENDIF

			ENDIF	 
		ENDIF
		m_goals++
	ENDIF

	// 5. potted black out of turn
	IF m_goals = 5
		IF NOT turn_colour = POOL_COLOUR_BLACK
			IF balls_potted_this_turn[8] = 1
				foul_type = 5
			ENDIF
		ELSE 
			// if fouled on black, when black got pocketted
			IF balls_potted_this_turn[8] = 1
				IF NOT foul_type = 0
					foul_type = 5
				ENDIF
			ENDIF 	
		ENDIF
		m_goals++
	ENDIF

	// 6. rail foul
	IF m_goals = 6
		//IF this_is_break = 0
			IF foul_type = 0
				IF no_of_balls_potted_this_turn = 0	
					IF ball_strikes_cushion = 0
						foul_type = 6
					ENDIF	
				ENDIF
			ENDIF
		m_goals = 99
	ENDIF


	IF m_goals = 99

		// assign colours if no foul was committed and a ball was potted
		IF foul_type = 0
		AND this_is_break = 0
		AND no_of_balls_potted_this_turn > 0
		AND turn_colour = POOL_COLOUR_NONE

			IF spots_potted > stripes_potted
				
				IF turn_player = 1
					player1_colour = POOL_COLOUR_SPOTS
					player2_colour = POOL_COLOUR_STRIPES
				ELSE
					player1_colour = POOL_COLOUR_STRIPES
					player2_colour = POOL_COLOUR_SPOTS
				ENDIF

			ELSE
				IF stripes_potted > spots_potted

					IF turn_player = 1
						player1_colour = POOL_COLOUR_STRIPES 
						player2_colour = POOL_COLOUR_SPOTS
					ELSE
						player1_colour = POOL_COLOUR_SPOTS 
						player2_colour = POOL_COLOUR_STRIPES
					ENDIF

				ENDIF
			ENDIF
		ENDIF

		CLEAR_HELP
		m_goals = 0
		m_stage++
	ENDIF

RETURN

// *******************************************************************************************
//							DETERMINE WHO PLAYS NEXT
// *******************************************************************************************			

pl_stage_10:

	// 	1. missed all balls	
	//	2. hit wrong ball first
	//	3. potted white
	//	4. potted wrong colour
	//	5. potted black out of turn
		
	IF m_goals = 0

		TIMERA = 0

		// find out if either player is going for black
		IF  ball_potted[1] = 1
		AND ball_potted[2] = 1
		AND ball_potted[3] = 1
		AND ball_potted[4] = 1
		AND ball_potted[5] = 1
		IF 	ball_potted[6] = 1
		AND ball_potted[7] = 1
			IF player1_colour = POOL_COLOUR_SPOTS
				player1_colour = POOL_COLOUR_BLACK
			ENDIF
			IF player2_colour = POOL_COLOUR_SPOTS
				player2_colour = POOL_COLOUR_BLACK
			ENDIF
		ENDIF
		ENDIF 
		IF  ball_potted[9] = 1
		AND ball_potted[10] = 1
		AND ball_potted[11] = 1
		AND ball_potted[12] = 1
		AND ball_potted[13] = 1
		IF 	ball_potted[14] = 1
		AND ball_potted[15] = 1
			IF player1_colour = POOL_COLOUR_STRIPES
				player1_colour = POOL_COLOUR_BLACK
			ENDIF
			IF player2_colour = POOL_COLOUR_STRIPES
				player2_colour = POOL_COLOUR_BLACK
			ENDIF
		ENDIF
		ENDIF


		// if no foul occured and player potted a ball it's still current players turn
		SWITCH foul_type
			CASE 0
				IF no_of_balls_potted_this_turn > 0
					IF NOT ball_potted[8] = 1
						turn_is_scratch = 0
						skip_chalk_cue = 1
						IF turn_player = 1
							turn_colour = player1_colour
						ELSE
							turn_colour = player2_colour
						ENDIF 
						m_goals = 99
					ELSE
						// this player has won!
						game_over = 1
						this_player_wins = 1
						m_goals = 99
					ENDIF
				ELSE
					IF turn_player = 1
						turn_is_scratch = 0
						turn_player = 2
						current_char = player2_char
						current_cue = cue2
						current_char_is_human = player2_is_human
						skip_chalk_cue = 0
						turn_colour = player2_colour
						IF player2_is_human = 1
							current_pad = PAD2
						ELSE
							current_pad = PAD1
						ENDIF
						m_goals = 99
					ELSE
						turn_is_scratch = 0
						turn_player = 1		
						current_char = player1_char
						current_cue =  cue1
						current_char_is_human = player1_is_human
						skip_chalk_cue = 0
						turn_colour = player1_colour
						current_pad = PAD1
						m_goals = 99
					ENDIF	
				ENDIF
			BREAK
			CASE 1 // 1. missed all balls
				
				IF turn_player = 1
					turn_player = 2
					current_char = player2_char
					current_cue = cue2
					current_char_is_human = player2_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player2_colour
					IF player2_is_human = 1
						current_pad = PAD2
					ELSE
						current_pad = PAD1
					ENDIF
					m_goals = 99
				ELSE
					turn_player = 1
					current_char = player1_char
					current_cue = cue1
					current_char_is_human = player1_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player1_colour
					current_pad = PAD1
					m_goals = 99
				ENDIF
			BREAK
		

			CASE 2 // 2. hit wrong ball first
				//PRINT_NOW FOUL2	4000 1
				IF turn_player = 1
					turn_player = 2
					current_char = player2_char
					current_cue = cue2
					current_char_is_human = player2_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player2_colour
					IF player2_is_human = 1
						current_pad = PAD2
					ELSE
						current_pad = PAD1
					ENDIF
					m_goals = 99
				ELSE
					turn_player = 1
					current_char = player1_char
					current_cue = cue1
					current_char_is_human = player1_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player1_colour
					current_pad = PAD1
					m_goals = 99
				ENDIF
			BREAK

			CASE 3	// 3. potted white
				//PRINT_NOW FOUL3	4000 1
				IF turn_player = 1
					turn_player = 2
					current_char = player2_char
					current_cue = cue2
					current_char_is_human = player2_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player2_colour 
					IF player2_is_human = 1
						current_pad = PAD2
					ELSE
						current_pad = PAD1
					ENDIF
					m_goals = 99
				ELSE
					turn_player = 1
					current_char = player1_char
					current_cue = cue1
					current_char_is_human = player1_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player1_colour
					current_pad = PAD1
					m_goals = 99
				ENDIF
			BREAK

			CASE 4	// 4. potted wrong colour
				//PRINT_NOW FOUL4	4000 1
				IF turn_player = 1
					turn_player = 2
					current_char = player2_char
					current_cue = cue2
					current_char_is_human = player2_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player2_colour
					IF player2_is_human = 1
						current_pad = PAD2
					ELSE
						current_pad = PAD1
					ENDIF
					m_goals = 99
				ELSE
					turn_player = 1
					current_char = player1_char
					current_cue = cue1
					current_char_is_human = player1_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player1_colour
					current_pad = PAD1
					m_goals = 99
				ENDIF
			BREAK

			CASE 5	// 5. potted black out of turn
				//PRINT_NOW FOUL5	4000 1
				LVAR_INT this_player_wins
				LVAR_INT game_over
				IF this_is_break = 0
					this_player_wins = 0
				ELSE
					this_player_wins = 1 
				ENDIF	
				game_over = 1
				m_goals = 99
			BREAK

			CASE 6
				IF turn_player = 1
					turn_player = 2
					current_char = player2_char
					current_cue = cue2
					current_char_is_human = player2_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player2_colour
					IF player2_is_human = 1
						current_pad = PAD2
					ELSE
						current_pad = PAD1
					ENDIF
					m_goals = 99
				ELSE
					turn_player = 1
					current_char = player1_char
					current_cue = cue1
					current_char_is_human = player1_is_human
					turn_is_scratch = 2
					skip_chalk_cue = 0
					turn_colour = player1_colour
					current_pad = PAD1
					m_goals = 99
				ENDIF
			BREAK

		ENDSWITCH
		

		IF two_players = 0
			IF current_char_is_human = 1	
				PRINT_HELP_FOREVER PL_H5  
			ENDIF
		ELSE
			IF current_pad = PAD1
				PRINT_HELP_FOREVER PL_H8  
			ELSE
				PRINT_HELP_FOREVER PL_H9  
			ENDIF
		ENDIF

		TIMERA = 0
		TIMERB = 0
						
	ENDIF
	

	IF m_goals = 99
		GOSUB pool_draw_window_2
		IF current_char_is_human = 1
			IF IS_BUTTON_PRESSED current_pad CROSS
				m_goals++
			ENDIF
		ELSE
			IF TIMERB > 2000
			OR IS_BUTTON_PRESSED current_pad CROSS
				m_goals++
			ENDIF

		ENDIF
	ENDIF


	IF m_goals = 100
		CLEAR_HELP
		temp_int = 0
		no_of_balls_potted_this_turn = 0
		WHILE temp_int < 16
		balls_potted_this_turn[temp_int] = 0
		temp_int++
		ENDWHILE
		spots_potted	= 0
		stripes_potted	= 0
	
		IF game_over = 1
			this_is_break = 1
			m_stage++
			m_goals = 0
		ELSE
			this_is_break = 0
			m_stage = 3
			m_goals = 0
		ENDIF
		
		WAIT 0

	ENDIF
  
RETURN

// *******************************************************************************************
//							SHOW WINNER
// *******************************************************************************************			

pl_stage_11:

	IF m_goals = 0
		IF this_player_wins = 0
			IF turn_player = 1
				current_char = player2_char
				current_cue = cue2
			ELSE
				current_char = player1_char
				current_cue = cue1
			ENDIF
		ELSE
			IF turn_player = 1
				current_char = player1_char
				current_cue = cue1
			ELSE
				current_char = player2_char
				current_cue = cue2
			ENDIF						
		ENDIF
		IF NOT IS_CHAR_DEAD current_char
			//VAR_INT casino_anim_loaded
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS current_char 1.0 2.0 0.0 x y z
			SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
			POINT_CAMERA_AT_CHAR current_char FIXED JUMP_CUT
			
			IF two_players = 0
				// you win
				IF current_char = player1_char
					temp_int = pl_initial_stake * 2
					ADD_SCORE player1 temp_int
					START_NEW_SCRIPT display_win_text temp_int 5000 80
					IF NOT IS_CHAR_DEAD	opp 
						SET_CHAR_MONEY opp 0
					ENDIF
				ELSE
				// you lost
					temp_int = pl_initial_stake * -1
					START_NEW_SCRIPT display_win_text temp_int 5000	80
				ENDIF

			ELSE
				// player1 wins
				IF current_char = player1_char
					PRINT_BIG PL_08 5000 1
				ELSE
				// player2 wins
					PRINT_BIG PL_09 5000 1
				ENDIF
			ENDIF
			
			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF
	IF m_goals = 1
		IF TIMERA > 5000
			m_goals++
		ENDIF
	ENDIF
	// fade out and delete any remaining balls
	IF m_goals = 2
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
				DELETE_OBJECT p_ball[temp_int]
			ENDIF
		temp_int++
		ENDWHILE

		// recreate balls
		// cue ball
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.2734375 -0.0546875 	0.9296875  x y z  // 0.9296875 
		CREATE_OBJECT_NO_OFFSET K_POOLBALLCUE x y z p_ball[0]
		GET_OBJECT_HEADING table heading
		heading += 270.0
		SET_OBJECT_HEADING p_ball[0] heading

		// other balls
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.3408 -0.0284 0.9297  ball_tri_x ball_tri_y ball_tri_z  // 0.9297
		GET_OBJECT_HEADING table ball_tri_h 
		ball_tri_h += 90.0
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT01	ball_tri_x ball_tri_y ball_tri_z p_ball[1]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT02 0.0 0.0 0.0 p_ball[2]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT03 0.0 0.0 0.0 p_ball[3]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT04 0.0 0.0 0.0 p_ball[4]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT05 0.0 0.0 0.0 p_ball[5]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT06 0.0 0.0 0.0 p_ball[6]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSPT07 0.0 0.0 0.0 p_ball[7]
		CREATE_OBJECT_NO_OFFSET K_POOLBALL8 	0.0 0.0 0.0 p_ball[8]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP01 0.0 0.0 0.0 p_ball[9]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP02 0.0 0.0 0.0 p_ball[10]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP03 0.0 0.0 0.0 p_ball[11]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP04 0.0 0.0 0.0 p_ball[12]	
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP05 0.0 0.0 0.0 p_ball[13]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP06 0.0 0.0 0.0 p_ball[14]
		CREATE_OBJECT_NO_OFFSET K_POOLBALLSTP07 0.0 0.0 0.0 p_ball[15]
		GOSUB update_ball_triangle_position

		m_goals = 99
	ENDIF
	IF m_goals = 99
		CLEAR_HELP
		m_stage++
		m_goals = 0
	ENDIF
RETURN

setup_pool_win_text:
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.3 3.36
	SET_TEXT_CENTRE ON
	SET_TEXT_EDGE 2 0 0 0 255
	SET_TEXT_FONT FONT_HEADING
	GET_HUD_COLOUR HUD_COLOUR_YELLOW temp_integer_2 temp_integer_3 temp_integer_4 an
	set_text_colour	temp_integer_2 temp_integer_3 temp_integer_4 255
RETURN


// *******************************************************************************************
//							ANOTHER GAME ?
// *******************************************************************************************			

pl_stage_12:

	this_player_wins = 0
	no_of_balls_potted_this_turn = 0
	spots_potted = 0
	stripes_potted = 0
	foul_type = 0
	temp_int = 0
	WHILE temp_int < 16
		balls_potted_this_turn[temp_int] = 0
		ball_potted[temp_int] = 0
	temp_int++
	ENDWHILE
	turn_is_scratch = 0
	game_over = 0

	m_stage = 1
	m_goals = 0

RETURN


// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************

pl_global_functions:

	IF DOES_OBJECT_EXIST table
		GET_OBJECT_COORDINATES table x y z
		CLEAR_AREA x y z 10.0 TRUE
	ENDIF

	IF DOES_OBJECT_EXIST P_ball[0]
		GET_OBJECT_SPEED P_ball[0] cue_ball_speed
		GET_OBJECT_ROTATION_VELOCITY P_ball[0] cue_ball_rot_x cue_ball_rot_y cue_ball_rot_z
	ENDIF

	// stop balls rolling when the reach a certain speed
	IF shot_timer > 2000
		x = pool_stop_rotation_velocity * -1.0
		temp_int = 0
		WHILE temp_int < 16
		    IF DOES_OBJECT_EXIST p_ball[temp_int]
			    
						GET_OBJECT_SPEED p_ball[temp_int] temp_float

						IF temp_float < pool_stop_velocity


							IF temp_float < 0.005

								GET_OBJECT_ROTATION_VELOCITY p_ball[temp_int] temp_float temp_float2 temp_float3

								temp_int2 = 0
								IF temp_float > pool_stop_rotation_velocity
								OR temp_float < x
									temp_int2 = 1
								ENDIF
								IF temp_float2 > pool_stop_rotation_velocity
								OR temp_float2 < x
									temp_int2 = 1
								ENDIF
								IF temp_float3 > pool_stop_rotation_velocity
								OR temp_float3 < x
									temp_int2 = 1
								ENDIF
								IF temp_int2 = 0
								 	SET_OBJECT_VELOCITY p_ball[temp_int] 0.0 0.0 0.0
									SET_OBJECT_ROTATION_VELOCITY p_ball[temp_int] 0.0 0.0 0.0 
									ball_is_stationary[temp_int] = 1
								ELSE   
									ball_is_stationary[temp_int] = 0
								ENDIF

							ELSE
								
								GET_OBJECT_VELOCITY p_ball[temp_int] x y z
							x *= 0.9
							y *= 0.9
							z *= 0.9
								SET_OBJECT_VELOCITY p_ball[temp_int] x y z

								ball_is_stationary[temp_int] = 0

							ENDIF

						ELSE
							ball_is_stationary[temp_int] = 0
						ENDIF
					
				ELSE
					ball_is_stationary[temp_int] = 1	
				ENDIF
			
		temp_int++
		ENDWHILE
	ENDIF


	// update object anims
	IF NOT quit = 1
		object_anim_char   = player1_char
		object_anim_object = cue1
		GOSUB pool_update_object_anims

		object_anim_char   = player2_char
		object_anim_object = cue2
		GOSUB pool_update_object_anims
	ENDIF
	
	// decrease pool ball spin
	cue_ball_spin_x *= 0.98
	cue_ball_spin_y *= 0.98
	

	// any potted balls - delete		
	temp_int = 0
	WHILE temp_int < 16
		IF DOES_OBJECT_EXIST p_ball[temp_int]
			GET_OBJECT_COORDINATES p_ball[temp_int]	x y z
			temp_float2 = pool_ball_radius * 2.0
			temp_float = ball_tri_z - temp_float2
			IF z < temp_float
				IF ball_potted[temp_int] = 0 
					
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION X Y Z SOUND_POOL_BALL_POT

					IF DOES_OBJECT_EXIST p_ball[temp_int]
						DELETE_OBJECT p_ball[temp_int]
					ENDIF
					ball_potted[temp_int] = 1
					balls_potted_this_turn[temp_int] = 1
					no_of_balls_potted_this_turn++
					IF temp_int > 0
					AND temp_int < 8
						spots_potted++
					ENDIF
					IF temp_int > 8
						stripes_potted++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	temp_int++
	ENDWHILE


	// record cushion strikes and cue ball strikes
	LVAR_INT ball_strikes_cushion 
	LVAR_INT first_moving_ball
	LVAR_INT cue_ball_hit_cushion_first
	temp_int = 0
	WHILE temp_int < 16

		IF DOES_OBJECT_EXIST p_ball[temp_int]

			// check if ball has hit cushion
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON p_ball[temp_int] WEAPONTYPE_FALL
				ball_strikes_cushion++
				IF temp_int = 0
					cue_ball_hit_cushion_first = 1
				ENDIF
			ENDIF

			// check if ball has been struck by cue ball
			IF first_moving_ball = -1
				IF temp_int > 0
					IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON p_ball[temp_int] WEAPONTYPE_DROWNING
						first_moving_ball = temp_int
						// apply spin to cue ball
						IF cue_ball_hit_cushion_first = 0
							IF DOES_OBJECT_EXIST p_ball[0]
								ADD_VELOCITY_RELATIVE_TO_OBJECT_VELOCITY p_ball[0] cue_ball_spin_x cue_ball_spin_y 0.0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// check if ball has hit cushion and cue ball in same frame
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON p_ball[temp_int] WEAPONTYPE_RUNOVERBYCAR
				ball_strikes_cushion++
				IF temp_int = 0
					cue_ball_hit_cushion_first = 1
				ENDIF
				IF first_moving_ball = -1
					IF temp_int > 0
						first_moving_ball = temp_int
						// apply spin to cue ball
						IF cue_ball_hit_cushion_first = 0
							IF DOES_OBJECT_EXIST p_ball[0]
								ADD_VELOCITY_RELATIVE_TO_OBJECT_VELOCITY p_ball[0] cue_ball_spin_x cue_ball_spin_y 0.0
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
			ENDIF
	 

			// clear damage on this ball
			CLEAR_OBJECT_LAST_WEAPON_DAMAGE p_ball[temp_int]

		ENDIF

	temp_int++
	ENDWHILE


	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		IF output_text = 1
			output_text = 0
			WRITE_DEBUG output_text_off
		ELSE
			output_text = 1
			WRITE_DEBUG output_text_on
		ENDIF
	ENDIF
	
	IF NOT DOES_OBJECT_EXIST table
		m_stage = 99
	ENDIF

	// debug - opp health
	VAR_INT debug_char_health
	IF NOT IS_CHAR_DEAD opp
		GET_CHAR_HEALTH opp debug_char_health	
	ENDIF

	IF IS_CHAR_DEAD opp
		m_stage = 99
	ENDIF

	// quit
	IF quit = 1
		
		DISPLAY_HUD TRUE

		// give refund
		IF pl_refund > 0
			ADD_SCORE player1 pl_refund
		ENDIF

		IF NOT IS_CHAR_DEAD opp
			FREEZE_CHAR_POSITION opp FALSE
			REMOVE_DECISION_MAKER dm
			LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm
			SET_CHAR_DECISION_MAKER opp dm
			SET_CHAR_COLLISION opp TRUE
			SET_CHAR_PROOFS opp FALSE FALSE FALSE FALSE FALSE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE opp TRUE
		ENDIF
		
		SET_PED_DENSITY_MULTIPLIER 1.0
		USE_TEXT_COMMANDS FALSE
		DISPLAY_RADAR TRUE
		temp_int = 0
		WHILE temp_int < 16
			MARK_OBJECT_AS_NO_LONGER_NEEDED p_ball[temp_int]
			temp_int++
		ENDWHILE
		RESTORE_CAMERA_JUMPCUT
		IF DOES_OBJECT_EXIST aim_cue
			DELETE_OBJECT aim_cue
		ENDIF

		//SET_OBJECT_COLLISION table ON
		table_z += 0.1

		SET_OBJECT_COORDINATES table table_x table_y table_z
		
		// set table max mins										 // 0.8557
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.9600 -0.4900 0.70 pool_table_min_x pool_table_min_y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.9600 0.4900 0.70 pool_table_max_x pool_table_max_y z
		IF pool_table_min_x > pool_table_max_x
			x = pool_table_min_x
			pool_table_min_x = pool_table_max_x
			pool_table_max_x = x
		ENDIF
		IF pool_table_min_y > pool_table_max_y
			y = pool_table_min_y
			pool_table_min_y = pool_table_max_y
			pool_table_max_y = y
		ENDIF
		SET_POOL_TABLE_COORDS pool_table_min_x pool_table_min_y z pool_table_max_x pool_table_max_y z

		// move pool balls up a bit
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
				GET_OBJECT_COORDINATES p_ball[temp_int] x y z
				z += 0.1
				SET_OBJECT_COORDINATES p_ball[temp_int] x y z
			ENDIF
		temp_int++
		ENDWHILE

		IF IS_PLAYER_PLAYING player1
			//WRITE_DEBUG CLEARED_CHAR_TASKS
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			FREEZE_CHAR_POSITION scplayer FALSE
			SET_PLAYER_CONTROL player1 ON
			SET_CHAR_COLLISION scplayer TRUE
		ENDIF

		DELETE_OBJECT cue1
		DELETE_OBJECT cue2

		MARK_MODEL_AS_NO_LONGER_NEEDED K_POOLQ
		REMOVE_ANIMATION POOL
	ENDIF

	// quit
	IF m_stage = 99
	AND quit = 0
		USE_TEXT_COMMANDS FALSE
		DISPLAY_RADAR TRUE
		m_stage = 0
		WAIT 500
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_I
		IF DOES_OBJECT_EXIST p_ball[0]
			FREEZE_OBJECT_POSITION p_ball[0] FALSE
			SET_OBJECT_COLLISION p_ball[0] FALSE 	  
			SET_OBJECT_DYNAMIC p_ball[0] FALSE
			GENERATE_RANDOM_FLOAT_IN_RANGE -5.0 5.0 x
			GENERATE_RANDOM_FLOAT_IN_RANGE -5.0 5.0 y
			SET_OBJECT_VELOCITY p_ball[0] x y 0.0
			SET_OBJECT_COLLISION p_ball[0] TRUE 	  
	    	SET_OBJECT_DYNAMIC p_ball[0] TRUE
		ENDIF
	ENDIF

RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************

// params 
LVAR_FLOAT proj_dist

// workings
LVAR_INT proj_stage
LVAR_FLOAT proj_vec_x
LVAR_FLOAT proj_vec_y

LVAR_INT line_num

LVAR_INT proj_cue[10]
LVAR_INT proj_hit[5]

LVAR_FLOAT proj_spacing
LVAR_FLOAT proj_hit_dist proj_hit_dist2 proj_hit_dist3

// lines
LVAR_FLOAT proj_line_x1[3] proj_line_y1[3] proj_line_x2[3] proj_line_y2[3]
LVAR_FLOAT proj_lineB_x1[3] proj_lineB_y1[3] proj_lineB_x2[3] proj_lineB_y2[3]


// hit ball
LVAR_INT proj_hit_ball
LVAR_FLOAT proj_hit_ball_dist
LVAR_FLOAT proj_hit_ball_vec_x
LVAR_FLOAT proj_hit_ball_vec_y

LVAR_FLOAT proj_cue_path_x[4]
LVAR_FLOAT proj_cue_path_y[4]
LVAR_FLOAT proj_hit_start_x proj_hit_end_x
pool_draw_projection:

	proj_stage = 0
	proj_dist = 1.7


	WHILE NOT proj_stage = 99

		IF NOT projection_calculated = 0
			proj_stage = 2
		ENDIF

		IF proj_stage = 0
			// get required initial coords
			IF DOES_OBJECT_EXIST p_ball[0]
				
				// clear all lines
				temp_int = 0
				WHILE temp_int < 3
					proj_line_x1[temp_int] = -999999.9
					proj_line_y1[temp_int] = -999999.9
					proj_line_x2[temp_int] = -999999.9
					proj_line_y2[temp_int] = -999999.9
					proj_lineB_x1[temp_int] = -999999.9				
					proj_lineB_y1[temp_int] = -999999.9
					proj_lineB_x2[temp_int] = -999999.9
					proj_lineB_y2[temp_int] = -999999.9
				temp_int++
				ENDWHILE
				
				proj_hit_dist  = proj_dist
				proj_hit_dist  = proj_dist
				proj_spacing = proj_dist / 10.0

				GET_OBJECT_HEADING p_ball[0] heading
				SET_OBJECT_ROTATION p_ball[0] 0.0 0.0 heading
				GET_OBJECT_COORDINATES p_ball[0] x y z 
				// adjust coordinates so they're always safe
				IF x < pool_table_min_x
					x = pool_table_min_x + 0.01
				ENDIF
				IF x > pool_table_max_x
					x = pool_table_max_x - 0.01
				ENDIF
				IF y < pool_table_min_y
					y = pool_table_min_y + 0.01
				ENDIF 
				IF y > pool_table_max_y
					y = pool_table_max_y - 0.01
				ENDIF

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.0 1.0 0.0 x2 y2 z2
				proj_vec_x = x2 - x
				proj_vec_y = y2 - y
				GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
				proj_vec_x /= temp_float
				proj_vec_y /= temp_float
				proj_vec_x *= proj_hit_dist
				proj_vec_y *= proj_hit_dist
				p_x1 = x
				p_y1 = y
				proj_line_x1[0] = x
				proj_line_y1[0] = y
				path_dest_x = x + proj_vec_x
				path_dest_y = y + proj_vec_y 
				line_num = 0
				proj_hit_ball = 0
				ignore_cushion = 0
				proj_stage++
			ENDIF
		ENDIF


		IF proj_stage = 1

			got_first_hit_ball = 0

			// cue ball
			WHILE proj_hit_dist > 0.0
			AND line_num < 3
		
				//WRITE_DEBUG_WITH_INT line_num line_num
				GOSUB is_path_closest_clear
				proj_line_x2[line_num] = new_end_point_x
				proj_line_y2[line_num] = new_end_point_y

				temp_int = line_num + 1
				IF temp_int < 3
					proj_line_x1[temp_int] = new_end_point_x
					proj_line_y1[temp_int] = new_end_point_y
				ENDIF
			
				GET_DISTANCE_BETWEEN_COORDS_2D proj_line_x1[line_num] proj_line_y1[line_num] proj_line_x2[line_num] proj_line_y2[line_num] temp_float
				proj_hit_dist -= temp_float
				
				// reduce distance if something is hit
				IF path_is_clear = 0
					IF NOT obstructing_ball = -1
						proj_hit_dist *= 0.5 
					ELSE
						proj_hit_dist *= 0.75
					ENDIF

					// store if another ball is hit
					IF proj_hit_ball = 0
						IF NOT obstructing_ball = -1
							proj_hit_ball = obstructing_ball
							proj_hit_ball_dist = proj_hit_dist
							proj_hit_ball_vec_x = hit_ball_bounce_vec_x
							proj_hit_ball_vec_y = hit_ball_bounce_vec_y 
						ENDIF
					ENDIF	 	

				   	// work out the next path
				   	p_x1 = new_end_point_x
				   	p_y1 = new_end_point_y
					new_bounce_vec_x *= proj_hit_dist 
					new_bounce_vec_y *= proj_hit_dist
					path_dest_x	= p_x1 + new_bounce_vec_x
					path_dest_y	= p_y1 + new_bounce_vec_y
					    
					line_num++
				ELSE
					// end 
					line_num = 3
				ENDIF				
						  
			ENDWHILE
			
			projection_calculated++
			proj_stage++
		ENDIF
		

		// actually draw lines
		IF proj_stage = 2

			LVAR_FLOAT proj_offset_dist
			LVAR_FLOAT proj_offset_speed
			LVAR_FLOAT proj_offset_vec_x
			LVAR_FLOAT proj_offset_vec_y
			LVAR_FLOAT proj_next_offset

			// cue ball line
			LVAR_INT proj_cue_ball_objects[10]
			LVAR_INT proj_used_cue_balls
			temp_int = 0
			WHILE temp_int < 10
				IF DOES_OBJECT_EXIST proj_cue_ball_objects[temp_int]
					DELETE_OBJECT proj_cue_ball_objects[temp_int]
				ENDIF 
				CREATE_OBJECT K_POOLBALLCUE 0.0 0.0 0.0 proj_cue_ball_objects[temp_int] 
				SET_OBJECT_COLLISION proj_cue_ball_objects[temp_int] FALSE
				SET_OBJECT_SCALE proj_cue_ball_objects[temp_int] 0.3
			temp_int++
			ENDWHILE
			proj_used_cue_balls = 0


			proj_offset_speed = 0.005
			proj_offset_dist +=@ proj_offset_speed
			IF proj_offset_dist > proj_spacing
				proj_offset_dist -= proj_spacing
			ENDIF
			proj_next_offset = 0.0

			// travel along lines
			temp_int = 0
			WHILE temp_int < 3
				IF  proj_line_x1[temp_int] > -9999.9
				AND proj_line_y1[temp_int] > -9999.9
				AND proj_line_x2[temp_int] > -9999.9
				AND proj_line_y2[temp_int] > -9999.9

					GET_DISTANCE_BETWEEN_COORDS_2D proj_line_x1[temp_int] proj_line_y1[temp_int] proj_line_x2[temp_int] proj_line_y2[temp_int] temp_float
					vec_x = proj_line_x2[temp_int] - proj_line_x1[temp_int] 
					vec_y = proj_line_y2[temp_int] - proj_line_y1[temp_int]
					temp_float2 = temp_float / proj_spacing
					temp_int2 =# temp_float2 
					temp_int2 += 1
					vec_x /= temp_float
					vec_y /= temp_float

					proj_offset_vec_x =	vec_x 
					proj_offset_vec_y = vec_y 
					proj_offset_vec_x *= proj_offset_dist
					proj_offset_vec_y *= proj_offset_dist
					vec_x *= proj_spacing
					vec_y *= proj_spacing

					IF DOES_OBJECT_EXIST p_ball[0]
						GET_OBJECT_COORDINATES p_ball[0] x y z
						
						// adjust coordinates so they're always safe
						IF x < pool_table_min_x
							x = pool_table_min_x + 0.01
						ENDIF
						IF x > pool_table_max_x
							x = pool_table_max_x - 0.01
						ENDIF
						IF y < pool_table_min_y
							y = pool_table_min_y + 0.01
						ENDIF 
						IF y > pool_table_max_y
							y = pool_table_max_y - 0.01
						ENDIF

					ENDIF

					last_drawn_aim_ball_x =	proj_line_x1[temp_int] - vec_x
					last_drawn_aim_ball_y =	proj_line_y1[temp_int] - vec_y
					
					last_drawn_aim_ball_x += proj_offset_vec_x
					last_drawn_aim_ball_y += proj_offset_vec_y

					temp_int2 += proj_used_cue_balls

					// place balls
					WHILE proj_used_cue_balls < temp_int2
					AND proj_used_cue_balls < 10
						
						x = last_drawn_aim_ball_x + vec_x 
						y = last_drawn_aim_ball_y + vec_y
						last_drawn_aim_ball_x =	x
						last_drawn_aim_ball_y =	y
						IF DOES_OBJECT_EXIST proj_cue_ball_objects[proj_used_cue_balls]

							IF x > pool_table_max_x
							OR x < pool_table_min_x
							OR y > pool_table_max_y
							OR y < pool_table_min_y
								SET_OBJECT_VISIBLE proj_cue_ball_objects[proj_used_cue_balls] FALSE
								SET_OBJECT_COORDINATES proj_cue_ball_objects[proj_used_cue_balls] x y z
							ELSE
								SET_OBJECT_VISIBLE proj_cue_ball_objects[proj_used_cue_balls] TRUE
								SET_OBJECT_COORDINATES proj_cue_ball_objects[proj_used_cue_balls] x y z		
							ENDIF
						ENDIF
						
						proj_used_cue_balls++
					ENDWHILE
																	    
				ENDIF
			temp_int++
			ENDWHILE

			proj_stage++
		ENDIF

		// draw hit ball lines
		IF proj_stage = 3
			proj_stage = 99
		ENDIF

	ENDWHILE

RETURN


//////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB  PLAY_OBJECT_ANIMS     //////////////////////////////////////////////////////////////////////////
LVAR_INT object_anim_char
LVAR_INT object_anim_object
LVAR_INT chalk_cue_audio_num
LVAR_INT audio_event_chalk_cue[2]
pool_update_object_anims:

	IF NOT IS_CHAR_DEAD object_anim_char

		IF object_anim_char = scplayer
			chalk_cue_audio_num = 0
		ELSE
			chalk_cue_audio_num = 1
		ENDIF

		// xlong shot
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_xlong_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_xlong_shot temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_xlong_shot_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_xlong_shot_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_xlong_shot_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_xlong_shot_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// xlong shot start
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_xlong_start
		AND NOT IS_CHAR_PLAYING_ANIM object_anim_char pool_xlong_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_xlong_start temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_xlong_start_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_xlong_start_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_xlong_start_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_xlong_start_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// long shot
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_long_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_long_shot temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_long_shot_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_long_shot_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_long_shot_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_long_shot_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// long shot start
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_long_start
		AND NOT IS_CHAR_PLAYING_ANIM object_anim_char pool_long_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_long_start temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_long_start_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_long_start_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_long_start_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_long_start_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// med shot
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_med_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_med_shot temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_med_shot_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_med_shot_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_med_shot_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_med_shot_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// med shot start
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_med_start
		AND NOT IS_CHAR_PLAYING_ANIM object_anim_char pool_med_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_med_start temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_med_start_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_med_start_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_med_start_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_med_start_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// short shot
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_short_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_short_shot temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_short_shot_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_short_shot_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_short_shot_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_short_shot_O 0.0
				ENDIF
			ENDIF
		ENDIF

		// short shot start
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_short_start
		AND NOT IS_CHAR_PLAYING_ANIM object_anim_char pool_short_shot
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char pool_short_start temp_float
			IF IS_OBJECT_PLAYING_ANIM object_anim_object pool_short_start_O
				SET_OBJECT_ANIM_CURRENT_TIME object_anim_object pool_short_start_O temp_float
			ELSE
				IF PLAY_OBJECT_ANIM object_anim_object pool_short_start_O POOL 10000.0 FALSE TRUE
					SET_OBJECT_ANIM_SPEED object_anim_object pool_short_start_O 0.0
				ENDIF
			ENDIF
		ENDIF

		
		// chalk cue
		IF IS_CHAR_PLAYING_ANIM object_anim_char POOL_ChalkCue 
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
			GET_CHAR_ANIM_CURRENT_TIME object_anim_char POOL_ChalkCue temp_float
			IF temp_float > 0.28
				IF NOT audio_event_chalk_cue[chalk_cue_audio_num] = 1
					GET_OBJECT_COORDINATES object_anim_object x y z
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION X Y Z SOUND_POOL_CHALK_CUE
					audio_event_chalk_cue[chalk_cue_audio_num] = 1
				ENDIF
			ENDIF
		ELSE
			IF NOT audio_event_chalk_cue[chalk_cue_audio_num] = 0
				audio_event_chalk_cue[chalk_cue_audio_num] = 0		
			ENDIF
		ENDIF

		// idle stance
		IF IS_CHAR_PLAYING_ANIM object_anim_char pool_idle_stance
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
		ENDIF

		// walk
		IF IS_CHAR_PLAYING_ANIM object_anim_char POOL_Walk  
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
		ENDIF

		// walk	start
		IF IS_CHAR_PLAYING_ANIM object_anim_char POOL_Walk_start  
			IF NOT IS_CHAR_HOLDING_OBJECT object_anim_char object_anim_object
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
			ENDIF
		ENDIF

		// if ped is just doing an idle stance, make him at least stand with pool cue
		temp_int = 0
		IF IS_CHAR_PLAYING_ANIM object_anim_char idle_stance
		AND NOT current_char = object_anim_char
			temp_int = 1
		ENDIF
		IF IS_CHAR_PLAYING_ANIM object_anim_char idle_stance
		AND m_goals = 6
			temp_int = 1
		ENDIF
		IF temp_int = 1
			GET_SCRIPT_TASK_STATUS object_anim_char PERFORM_SEQUENCE_TASK temp_int2
			IF temp_int2 = FINISHED_TASK
				DROP_OBJECT object_anim_char FALSE
				TASK_PICK_UP_OBJECT object_anim_char object_anim_object 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
				OPEN_SEQUENCE_TASK temp_seq
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Idle_Stance POOL 4.0 TRUE TRUE TRUE FALSE -1
				   //	WRITE_DEBUG idle_stance5
					//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_ChalkCue POOL 4.0 FALSE TRUE TRUE FALSE -1
					//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Idle_Stance POOL 4.0 FALSE TRUE TRUE TRUE 5000
				   //	WRITE_DEBUG idle_stance6
				CLOSE_SEQUENCE_TASK temp_seq
				IF quit = 0
					PERFORM_SEQUENCE_TASK object_anim_char temp_seq
				ENDIF
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF
		ENDIF

	ENDIF

RETURN


//////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB  FIND_BEST_SHOT     /////////////////////////////////////////////////////////////////////////////
// params
// return
LVAR_FLOAT ai_shot_heading
LVAR_FLOAT ai_shot_power
LVAR_FLOAT ai_shot_angle // used for determining whether to put spin on ball
// working
LVAR_INT best_shot_int best_shot_int2
LVAR_INT this_ball_colour
LVAR_FLOAT final_shot_x final_shot_y
LVAR_FLOAT final_shot_distance
LVAR_FLOAT b_x1 b_y1 b_z1
LVAR_FLOAT b_x2 b_y2 b_z2
LVAR_FLOAT b_x3 b_y3 b_z3
LVAR_INT shot_impossible
LVAR_FLOAT ball_01_score_pocket[6]										
LVAR_FLOAT ball_02_score_pocket[6]
LVAR_FLOAT ball_03_score_pocket[6]										
LVAR_FLOAT ball_04_score_pocket[6]										
LVAR_FLOAT ball_05_score_pocket[6]										
LVAR_FLOAT ball_06_score_pocket[6]										
LVAR_FLOAT ball_07_score_pocket[6]
LVAR_FLOAT ball_08_score_pocket[6]										
LVAR_FLOAT ball_09_score_pocket[6]										
LVAR_FLOAT ball_10_score_pocket[6]										
LVAR_FLOAT ball_11_score_pocket[6]
LVAR_FLOAT ball_12_score_pocket[6]
LVAR_FLOAT ball_13_score_pocket[6]
LVAR_FLOAT ball_14_score_pocket[6]
LVAR_FLOAT ball_15_score_pocket[6]
// scores
LVAR_FLOAT cue_dist_score
LVAR_FLOAT pocket_path_score
LVAR_FLOAT cue_path_score
LVAR_FLOAT pocket_dist_score
LVAR_FLOAT pocket_angle_score
LVAR_FLOAT shot_total_score
find_best_shot:

	final_shot_x 	= 99999.9 
	final_shot_y 	= 99999.9
	final_shot_distance = 99999.9

	ai_shot_heading = 99999.9			   
	ai_shot_angle 	= 0.0
	ai_shot_power 	= 99999.9

	// A. clear scores
	best_shot_int = 0
	WHILE best_shot_int < 6
		ball_01_score_pocket[best_shot_int] = 0.0
		ball_02_score_pocket[best_shot_int] = 0.0
		ball_03_score_pocket[best_shot_int] = 0.0
		ball_04_score_pocket[best_shot_int] = 0.0
		ball_05_score_pocket[best_shot_int] = 0.0
		ball_06_score_pocket[best_shot_int] = 0.0
		ball_07_score_pocket[best_shot_int] = 0.0
		ball_08_score_pocket[best_shot_int] = 0.0
		ball_09_score_pocket[best_shot_int] = 0.0
		ball_10_score_pocket[best_shot_int] = 0.0
		ball_11_score_pocket[best_shot_int] = 0.0
		ball_12_score_pocket[best_shot_int] = 0.0
		ball_13_score_pocket[best_shot_int] = 0.0
		ball_14_score_pocket[best_shot_int] = 0.0
		ball_15_score_pocket[best_shot_int] = 0.0
	best_shot_int++
	ENDWHILE 

	// B. For each ball work out a score for each pocket
	best_shot_int = 1 // don't need to do the cue ball
	WHILE best_shot_int < 16
	WAIT 0

		shot_impossible = 0
	
		IF DOES_OBJECT_EXIST p_ball[best_shot_int]
			IF ball_potted[best_shot_int] = 0
				// get colour of ball we're aiming at
				IF best_shot_int < 8
					this_ball_colour = POOL_COLOUR_SPOTS
				ELSE
					IF best_shot_int = 8
						this_ball_colour = POOL_COLOUR_BLACK		
					ELSE
						this_ball_colour = POOL_COLOUR_STRIPES
					ENDIF
				ENDIF


				// check if ball we're aiming at is same as we're supposed to be aiming at
				IF this_ball_colour = turn_colour
				OR turn_colour = POOL_COLOUR_NONE
					

					// special case for when no colour is assigned (ie. off the break)
					IF turn_colour = POOL_COLOUR_NONE
					AND this_ball_colour = POOL_COLOUR_BLACK
						shot_impossible = 1	
					ELSE
					
						// NOW COMES THE TRICKY PART!!!
						// for each of the remaining balls a score must be figured out for each pocket 
	  					  
						IF output_text = 1
		  					SAVE_NEWLINE_TO_DEBUG_FILE  
							SAVE_NEWLINE_TO_DEBUG_FILE 
							SAVE_STRING_TO_DEBUG_FILE "BALL "
							SAVE_INT_TO_DEBUG_FILE best_shot_int
						ENDIF
						

						best_shot_int2 = 0
						WHILE best_shot_int2 < 6
							
							IF output_text = 1
								SAVE_NEWLINE_TO_DEBUG_FILE
								SAVE_STRING_TO_DEBUG_FILE "POCKET "
								SAVE_INT_TO_DEBUG_FILE best_shot_int2
							ENDIF

							angle_ball = p_ball[best_shot_int]
							angle_pocket = best_shot_int2
							GOSUB is_ball_pottable_into_pocket

							IF output_text = 1
								SAVE_NEWLINE_TO_DEBUG_FILE
								SAVE_STRING_TO_DEBUG_FILE " REQUIRED ANGLE = "
								SAVE_FLOAT_TO_DEBUG_FILE required_angle_of_shot
							ENDIF

								IF angle_is_possible = 1
									
									// CALCULATE SCORES
									// 1. find if pocket path is clear and give score
									path_ball = p_ball[best_shot_int]
									path_ball2 = 0
									path_dest_x = pocket_x[best_shot_int2]
									path_dest_y	= pocket_y[best_shot_int2]
									GOSUB is_path_clear
									IF path_is_clear = 1
										pocket_path_score = 1.0
									ELSE
										IF obstructing_colour = this_ball_colour
											pocket_path_score = 0.20
										ELSE
											pocket_path_score = 0.10
										ENDIF
									ENDIF
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE " pocket_path_score = "
										SAVE_FLOAT_TO_DEBUG_FILE pocket_path_score
									ENDIF

									// 3. find pocket distance score
									GET_OBJECT_COORDINATES p_ball[best_shot_int] x y z
									GET_DISTANCE_BETWEEN_COORDS_2D x y pocket_x[best_shot_int2] pocket_y[best_shot_int2] temp_float
									pocket_dist_score = 2.3 - temp_float
									pocket_dist_score /= 2.3
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE " pocket_dist_score = "
										SAVE_FLOAT_TO_DEBUG_FILE pocket_dist_score
									ENDIF

									// 4. get angle score
									pocket_angle_score = 90.0 - required_angle_of_shot
									pocket_angle_score /= 90.0
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE " pocket_angle_score = "
										SAVE_FLOAT_TO_DEBUG_FILE pocket_angle_score
									ENDIF

									// 5. find if path to required contact point is possible
									path_ball = p_ball[0]
									path_ball2 = p_ball[best_shot_int]
									path_dest_x = angle_x1 
									path_dest_y = angle_y1
									GOSUB is_path_clear
									IF path_is_clear = 1
										cue_path_score = 1.0
									ELSE
										IF output_text = 1
											SAVE_NEWLINE_TO_DEBUG_FILE
											SAVE_STRING_TO_DEBUG_FILE "   (path not clear from cue ball to this ball) "
										ENDIF
										IF turn_colour = POOL_COLOUR_NONE
											cue_path_score = 0.05
										ELSE
											IF obstructing_colour = turn_colour
												cue_path_score = 0.05
											ELSE
												cue_path_score = -1000.0
											ENDIF
										ENDIF
  									ENDIF
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE " cue_path_score = "
										SAVE_FLOAT_TO_DEBUG_FILE cue_path_score
									ENDIF
									
									// 6. Work out the cue distance score
									GET_OBJECT_COORDINATES p_ball[0] x y z
									GET_DISTANCE_BETWEEN_COORDS_2D x y angle_x1 angle_y1 temp_float
									// optimum distance to cue ball is 0.3m
									IF temp_float < 0.3
										temp_float2 = 0.3 - temp_float
										temp_float = 0.3 + temp_float2	
									ENDIF
									temp_float += -0.3
									temp_float2 = 2.0 - temp_float
									temp_float2 /= 2.0
									cue_dist_score = temp_float2
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE " cue_dist_score = "
										SAVE_FLOAT_TO_DEBUG_FILE cue_dist_score
									ENDIF
									
									// 7. CALCULATE TOTAL SCORE FOR SHOT (may require some tweaking to each categories weighting)
									pocket_path_score 	*= 1.0
									cue_path_score		*= 1.0
									cue_dist_score	  	*= 0.3
									pocket_dist_score	*= 0.4
									pocket_angle_score 	*= 0.8
									shot_total_score = pocket_path_score
									shot_total_score += cue_path_score
									shot_total_score += cue_dist_score	  
									shot_total_score += pocket_dist_score	
									shot_total_score += pocket_angle_score
									shot_total_score /= 5.0

									// 8. save the score

									SWITCH best_shot_int
										CASE 1
											ball_01_score_pocket[best_shot_int2] = shot_total_score	
										BREAK
										CASE 2
											ball_02_score_pocket[best_shot_int2] = shot_total_score
										BREAK	
										CASE 3
											ball_03_score_pocket[best_shot_int2] = shot_total_score	
										BREAK
										CASE 4
											ball_04_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 5
											ball_05_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 6
											ball_06_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 7
											ball_07_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 8
											ball_08_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 9
											ball_09_score_pocket[best_shot_int2] = shot_total_score
										BREAK	
										CASE 10
											ball_10_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 11
											ball_11_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 12
											ball_12_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 13
											ball_13_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 14
											ball_14_score_pocket[best_shot_int2] = shot_total_score
										BREAK
										CASE 15
											ball_15_score_pocket[best_shot_int2] = shot_total_score
										BREAK
									ENDSWITCH

									
								ELSE // it's not possible to make shot as angle > 90.0


									SWITCH best_shot_int
										CASE 1
											ball_01_score_pocket[best_shot_int2] = -1.0	
										BREAK
										CASE 2
											ball_02_score_pocket[best_shot_int2] = -1.0
										BREAK	
										CASE 3
											ball_03_score_pocket[best_shot_int2] = -1.0	
										BREAK
										CASE 4
											ball_04_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 5
											ball_05_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 6
											ball_06_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 7
											ball_07_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 8
											ball_08_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 9
											ball_09_score_pocket[best_shot_int2] = -1.0
										BREAK	
										CASE 10
											ball_10_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 11
											ball_11_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 12
											ball_12_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 13
											ball_13_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 14
											ball_14_score_pocket[best_shot_int2] = -1.0
										BREAK
										CASE 15
											ball_15_score_pocket[best_shot_int2] = -1.0
										BREAK
									ENDSWITCH
								ENDIF
						best_shot_int2++
						ENDWHILE
					ENDIF
				ELSE
					shot_impossible = 1
				ENDIF 			
			ELSE
				shot_impossible = 1
			ENDIF
		ELSE
			shot_impossible = 1	
		ENDIF

		// this ball is impossible set all it's pocket scores to -1.0
		IF shot_impossible = 1
			SWITCH best_shot_int
				CASE 1
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_01_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE
				BREAK
				CASE  2
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_02_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  3
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_03_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  4
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_04_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  5
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_05_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  6
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_06_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  7
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_07_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  8
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_08_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  9
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_09_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  10
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_10_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  11
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_11_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  12
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_12_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  13
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_13_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  14
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_14_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
				CASE  15
					best_shot_int2 = 0
					WHILE best_shot_int2 < 6
						ball_15_score_pocket[best_shot_int2] = -1.0
					best_shot_int2++
					ENDWHILE	
				BREAK
			ENDSWITCH
		ENDIF // shot_impossible = 1

	best_shot_int++
	ENDWHILE

	// C. Now we've got scores for a plethora of possible shots, find the best one
	LVAR_FLOAT highest_score
	LVAR_INT highest_score_ball
	LVAR_INT highest_score_pocket
	highest_score = -9999.9
	highest_score_ball = -1
	highest_score_pocket = -1
	best_shot_int = 0
	IF output_text = 1
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "Pool AI potential shot scores"
	ENDIF
	WHILE best_shot_int < 6
		
		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE 
			SAVE_STRING_TO_DEBUG_FILE "Pocket"
			SAVE_INT_TO_DEBUG_FILE best_shot_int
			
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 01 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_01_score_pocket[best_shot_int]
		ENDIF
		IF ball_01_score_pocket[best_shot_int] > highest_score
			highest_score = ball_01_score_pocket[best_shot_int] 
			highest_score_ball = 1
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 02 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_02_score_pocket[best_shot_int]
		ENDIF
		IF ball_02_score_pocket[best_shot_int] > highest_score
			highest_score = ball_02_score_pocket[best_shot_int] 
			highest_score_ball = 2
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 03 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_03_score_pocket[best_shot_int]
		ENDIF
		IF ball_03_score_pocket[best_shot_int] > highest_score
			highest_score = ball_03_score_pocket[best_shot_int] 
			highest_score_ball = 3
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 04 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_04_score_pocket[best_shot_int]
		ENDIF
		IF ball_04_score_pocket[best_shot_int] > highest_score
			highest_score = ball_04_score_pocket[best_shot_int] 
			highest_score_ball = 4
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 05 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_05_score_pocket[best_shot_int]
		ENDIF
		IF ball_05_score_pocket[best_shot_int] > highest_score
			highest_score = ball_05_score_pocket[best_shot_int] 
			highest_score_ball = 5
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 06 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_06_score_pocket[best_shot_int]
		ENDIF
		IF ball_06_score_pocket[best_shot_int] > highest_score
			highest_score = ball_06_score_pocket[best_shot_int] 
			highest_score_ball = 6
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 07 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_07_score_pocket[best_shot_int]
		ENDIF
		IF ball_07_score_pocket[best_shot_int] > highest_score
			highest_score = ball_07_score_pocket[best_shot_int] 
			highest_score_ball = 7
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 08 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_08_score_pocket[best_shot_int]
		ENDIF
		IF ball_08_score_pocket[best_shot_int] > highest_score
			highest_score = ball_08_score_pocket[best_shot_int] 
			highest_score_ball = 8
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 09 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_09_score_pocket[best_shot_int]
		ENDIF
		IF ball_09_score_pocket[best_shot_int] > highest_score
			highest_score = ball_09_score_pocket[best_shot_int] 
			highest_score_ball = 9
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 10 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_10_score_pocket[best_shot_int]
		ENDIF
		IF ball_10_score_pocket[best_shot_int] > highest_score
			highest_score = ball_10_score_pocket[best_shot_int] 
			highest_score_ball = 10
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 11 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_11_score_pocket[best_shot_int]
		ENDIF
		IF ball_11_score_pocket[best_shot_int] > highest_score
			highest_score = ball_11_score_pocket[best_shot_int] 
			highest_score_ball = 11
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 12 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_12_score_pocket[best_shot_int]
		ENDIF
		IF ball_12_score_pocket[best_shot_int] > highest_score
			highest_score = ball_12_score_pocket[best_shot_int] 
			highest_score_ball = 12
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 13 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_13_score_pocket[best_shot_int]
		ENDIF
		IF ball_13_score_pocket[best_shot_int] > highest_score
			highest_score = ball_13_score_pocket[best_shot_int] 
			highest_score_ball = 13
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 14 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_14_score_pocket[best_shot_int]
		ENDIF
		IF ball_14_score_pocket[best_shot_int] > highest_score
			highest_score = ball_14_score_pocket[best_shot_int] 
			highest_score_ball = 14
			highest_score_pocket = best_shot_int	
		ENDIF

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "ball 15 = "
			SAVE_FLOAT_TO_DEBUG_FILE ball_15_score_pocket[best_shot_int]
		ENDIF
		IF ball_15_score_pocket[best_shot_int] > highest_score
			highest_score = ball_15_score_pocket[best_shot_int] 
			highest_score_ball = 15
			highest_score_pocket = best_shot_int	
		ENDIF
		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE
		ENDIF
		
	best_shot_int++
	ENDWHILE

	// work out power for shot
	IF highest_score > 0.0
		
		GET_OBJECT_COORDINATES p_ball[highest_score_ball] b_x1 b_y1 b_z1
		GET_OBJECT_COORDINATES p_ball[0] b_x3 b_y3 b_z3
		// get vector from ball to pocket
		b_x2 = pocket_x[highest_score_pocket] - b_x1
		b_y2 = pocket_y[highest_score_pocket] - b_y1
		// normalise
		GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 pocket_x[highest_score_pocket] pocket_y[highest_score_pocket] temp_float
		b_x2 /= temp_float
		b_y2 /= temp_float
		// find contact point
		temp_float2 = pool_ball_radius * -2.0
		b_x2 *= temp_float2 
		b_y2 *= temp_float2
		final_shot_x = b_x1 + b_x2
		final_shot_y = b_y1 + b_y2

		// reduce angle of shot - to allow for ball physics turning angle on contact.
		x = b_x1 - final_shot_x
		y = b_y1 - final_shot_y
		vec_x = final_shot_x - b_x3
		vec_y = final_shot_y - b_y3
		GET_ANGLE_BETWEEN_2D_VECTORS x y vec_x vec_y ai_shot_angle

//		// adjust shot angle 
//		IF ai_shot_angle > 80.0
			//WRITE_DEBUG_WITH_FLOAT ai_shot_angle ai_shot_angle
			temp_float = ai_shot_angle / 90.0
			temp_float *= 0.25
			x *= temp_float
			y *= temp_float
			final_shot_x += x
			final_shot_y += y
//		ENDIF


		// find shot distance
		GET_DISTANCE_BETWEEN_COORDS_2D b_x3 b_y3 final_shot_x final_shot_y temp_float
		GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 final_shot_x final_shot_y temp_float2
		final_shot_distance = temp_float + temp_float2
		GENERATE_RANDOM_FLOAT_IN_RANGE 2.0 6.0 temp_float
		final_shot_distance	*= temp_float

	ELSE

	// D. If there were no scores above 0 we've got a possible snooker situation.

		IF output_text = 1
			SAVE_NEWLINE_TO_DEBUG_FILE 
			SAVE_STRING_TO_DEBUG_FILE "hit and hope"
		ENDIF

		// ##### HIT AND HOPE #####
		// 1. find if any part of the players balls are visible - if so choose that shot
		best_shot_int = 1 // ignore cue ball
		WHILE best_shot_int < 16
		WAIT 0
			IF DOES_OBJECT_EXIST p_ball[best_shot_int]
				IF ball_potted[best_shot_int] = 0
					// get colour of ball we're aiming at
					IF best_shot_int = 0
						this_ball_colour = 3
					ELSE
						IF best_shot_int < 8
							this_ball_colour = POOL_COLOUR_SPOTS
						ELSE
							IF best_shot_int = 8
								this_ball_colour = POOL_COLOUR_BLACK		
							ELSE
								this_ball_colour = POOL_COLOUR_STRIPES
							ENDIF
						ENDIF
					ENDIF
					// check if ball we're aiming at is same as we're supposed to be aiming at
					IF this_ball_colour = turn_colour
					OR turn_colour = POOL_COLOUR_NONE

						// special case for when no colour is assigned (ie. off the break)
						IF turn_colour = POOL_COLOUR_SPOTS
						AND this_ball_colour = POOL_COLOUR_BLACK
							// do nothing
						ELSE
							// check if we can see either side of the ball
							see_ball1 =	p_ball[0]
							see_ball2 =	p_ball[best_shot_int]
							GOSUB can_we_see_ball
							IF NOT ball_can_see_ball = 0
								IF ball_can_see_ball = 1
									// hit right side
									final_shot_x = see_x_right 
									final_shot_y = see_y_right
								ELSE
									IF ball_can_see_ball = 2
										// hit left side
										final_shot_x = see_x_left 
										final_shot_y = see_y_left
									ELSE
										// we can see whole ball, so randomly hit ball between right and left coords
										// get vector from right to left
										x = see_x_left - see_x_right
										y = see_y_left - see_y_right
										// normalise
										GET_DISTANCE_BETWEEN_COORDS_2D see_x_right see_y_right see_x_left see_y_left  temp_float
										x /= temp_float
										y /= temp_float
										temp_float = pool_ball_radius * 2.0
										temp_float *= 0.8
										temp_float	*= 2.0
										GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 temp_float temp_float2
										x *= temp_float2
										y *= temp_float2
										final_shot_x = see_x_right + x
										final_shot_y = see_y_right + y
									ENDIF
								ENDIF

								// get distance
								GET_OBJECT_COORDINATES p_ball[0] x y z
								GET_DISTANCE_BETWEEN_COORDS_2D x y final_shot_x final_shot_y temp_float
								GENERATE_RANDOM_FLOAT_IN_RANGE 2.0 5.0 temp_float2
								final_shot_distance = temp_float * temp_float2

								best_shot_int = 16 // exit 'cause we've got a shot now.

							ENDIF	
						ENDIF
					ENDIF
				ENDIF		
			ENDIF
		best_shot_int++
		ENDWHILE // end of HIT AND HOPE 


		// ######  SNOOKER TYPE A ######
		IF final_shot_x = 99999.9
		AND final_shot_y = 99999.9
			
			// 2. IF NOT, WE'VE GOT A SNOOKER SITUATION A, WE NEED TO BOUNCE OFF CUSHION.
			IF output_text = 1
				SAVE_NEWLINE_TO_DEBUG_FILE 
				SAVE_STRING_TO_DEBUG_FILE "bounce off cushion"
			ENDIF

			// find the first available place to bounce off
			best_shot_int = 1
			WHILE best_shot_int < 16
			WAIT 0
				IF DOES_OBJECT_EXIST p_ball[best_shot_int]
					IF ball_potted[best_shot_int] = 0
						// get colour of ball we're aiming at
						IF best_shot_int = 0
							this_ball_colour = 3
						ELSE
							IF best_shot_int < 8
								this_ball_colour = POOL_COLOUR_SPOTS
							ELSE
								IF best_shot_int = 8
									this_ball_colour = POOL_COLOUR_BLACK		
								ELSE
									this_ball_colour = POOL_COLOUR_STRIPES
								ENDIF
							ENDIF
						ENDIF
						// check if ball we're aiming at is same as we're supposed to be aiming at
						IF this_ball_colour = turn_colour
						OR turn_colour = POOL_COLOUR_NONE
							// special case for when no colour is assigned (ie. off the break)
							IF turn_colour = POOL_COLOUR_NONE
							AND this_ball_colour = POOL_COLOUR_BLACK
								// do nothing
							ELSE
								// check x cushions
								GET_OBJECT_COORDINATES p_ball[0] b_x1 b_y1 b_z1
								GET_OBJECT_COORDINATES p_ball[best_shot_int] b_x2 b_y2 b_z2
								
								// TRY CUSHION 1
								IF output_text = 1
									SAVE_NEWLINE_TO_DEBUG_FILE
									SAVE_STRING_TO_DEBUG_FILE "  Cushion 1 data = "
								ENDIF
								// get x midpoint
								b_x3 = b_x1 + b_x2
								b_x3 /= 2.0
								b_y3 = pool_table_min_y
								// check point doesn't lie on pocket
								check_for_pocket_x = b_x3
								check_for_pocket_y = b_y3
								GOSUB is_point_too_near_pocket
								IF point_lies_on_pocket = 0
									// check if bounce shot is clear
									bounce_ball1 = p_ball[0]
									bounce_ball2 = p_ball[best_shot_int]
									bounce_cushion_x = b_x3
									bounce_cushion_y = b_y3
									GOSUB is_bounce_shot_path_clear
									IF bounce_path_is_clear = 1	// success!
										final_shot_x = b_x3
										final_shot_y = b_y3
										// work out shot distance - over hit by between 3.0 and 6.0
										GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
										GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
										final_shot_distance	= temp_float + temp_float2
										GENERATE_RANDOM_FLOAT_IN_RANGE 3.0 6.0 temp_float
										final_shot_distance *= temp_float 
										best_shot_int = 16											
									ENDIF
								ENDIF
								
								// TRY CUSHION 2
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
								IF output_text = 1
									SAVE_NEWLINE_TO_DEBUG_FILE
									SAVE_STRING_TO_DEBUG_FILE "  Cushion 2 data = "
								ENDIF

									b_y3 = pool_table_max_y
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce_ball1 = p_ball[0]
										bounce_ball2 = p_ball[best_shot_int]
										bounce_cushion_x = b_x3
										bounce_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear
										IF bounce_path_is_clear = 1	// success!
											final_shot_x = b_x3
											final_shot_y = b_y3
											// work out shot distance - over hit by between 3.0 and 6.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 3.0 6.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

								// TRY CUSHION 3
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
									IF output_text = 1 
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE "  Cushion 3 data = "
									ENDIF
									// get y midpoint
									b_y3 = b_y1 + b_y2
									b_y3 /= 2.0
									b_x3 = pool_table_min_x
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce_ball1 = p_ball[0]
										bounce_ball2 = p_ball[best_shot_int]
										bounce_cushion_x = b_x3
										bounce_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear
										IF bounce_path_is_clear = 1	// success!
											final_shot_x = b_x3
											final_shot_y = b_y3
											// work out shot distance - over hit by between 3.0 and 6.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 3.0 6.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

								// TRY CUSHION 4
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE "  Cushion 4 data = "
									ENDIF
									b_x3 = pool_table_max_x
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce_ball1 = p_ball[0]
										bounce_ball2 = p_ball[best_shot_int]
										bounce_cushion_x = b_x3
										bounce_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear
										IF bounce_path_is_clear = 1	// success!
											final_shot_x = b_x3
											final_shot_y = b_y3
											// work out shot distance - over hit by between 3.0 and 6.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 3.0 6.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

							ENDIF
						ENDIF			
					ENDIF 
				ENDIF
			best_shot_int++
			ENDWHILE

		ENDIF // end SNOOKER TYPE A


		// #####  SNOOKER TYPE B  #####
		IF final_shot_x = 99999.9
		AND final_shot_y = 99999.9
			// 3. snooker situation B - will happen occasionally, choose a ball to bounce off cushion, and add a randomness to it - and belt it!!
			// find the first available place to bounce off
			IF output_text = 1
				SAVE_NEWLINE_TO_DEBUG_FILE 
				SAVE_STRING_TO_DEBUG_FILE "snooker - bounce 2"
			ENDIF

			best_shot_int = 1
			WHILE best_shot_int < 16
			WAIT 0
				IF DOES_OBJECT_EXIST p_ball[best_shot_int]
					IF ball_potted[best_shot_int] = 0
						// get colour of ball we're aiming at
						IF best_shot_int = 0
							this_ball_colour = 3
						ELSE
							IF best_shot_int < 8
								this_ball_colour = POOL_COLOUR_SPOTS
							ELSE
								IF best_shot_int = 8
									this_ball_colour = POOL_COLOUR_BLACK		
								ELSE
									this_ball_colour = POOL_COLOUR_STRIPES
								ENDIF
							ENDIF
						ENDIF
						// check if ball we're aiming at is same as we're supposed to be aiming at
						IF this_ball_colour = turn_colour
						OR turn_colour = POOL_COLOUR_NONE
							// special case for when no colour is assigned (ie. off the break)
							IF turn_colour = POOL_COLOUR_NONE
							AND this_ball_colour = POOL_COLOUR_BLACK
								// do nothing
							ELSE
								// check x cushions
								GET_OBJECT_COORDINATES p_ball[0] b_x1 b_y1 b_z1
								GET_OBJECT_COORDINATES p_ball[best_shot_int] b_x2 b_y2 b_z2
								
								// TRY CUSHION 1
								// get x midpoint
								b_x3 = b_x1 + b_x2
								b_x3 /= 2.0
								b_y3 = pool_table_min_y
								// check point doesn't lie on pocket
								check_for_pocket_x = b_x3
								check_for_pocket_y = b_y3
								GOSUB is_point_too_near_pocket
								IF point_lies_on_pocket = 0
									// check if bounce shot is clear
									bounce2_ball1 = p_ball[0]
 									bounce2_cushion_x = b_x3
									bounce2_cushion_y = b_y3
									GOSUB is_bounce_shot_path_clear_first_half_only
									IF bounce2_path_is_clear = 1	// success!
										final_shot_x = b_x3
										// add some randomness
										GENERATE_RANDOM_FLOAT_IN_RANGE -0.05 0.05 temp_float
										final_shot_x += temp_float 
										final_shot_y = b_y3
										// work out shot distance - over hit by between 5.0 and 8.0
										GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
										GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
										final_shot_distance	= temp_float + temp_float2
										GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 8.0 temp_float
										final_shot_distance *= temp_float 
										best_shot_int = 16											
									ENDIF
								ENDIF
								
								// TRY CUSHION 2
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
									b_y3 = pool_table_max_y
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce2_ball1 = p_ball[0]
										bounce2_cushion_x = b_x3
										bounce2_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear_first_half_only
										IF bounce2_path_is_clear = 1	// success!
											final_shot_x = b_x3
											// add some randomness
											GENERATE_RANDOM_FLOAT_IN_RANGE -0.05 0.05 temp_float
											final_shot_x += temp_float 
											final_shot_y = b_y3
											// work out shot distance - over hit by between 5.0 and 8.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 8.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

								// TRY CUSHION 3
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
									// get y midpoint
									b_y3 = b_y1 + b_y2
									b_y3 /= 2.0
									b_x3 = pool_table_min_x
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce2_ball1 = p_ball[0]
										bounce2_cushion_x = b_x3
										bounce2_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear_first_half_only
										IF bounce2_path_is_clear = 1	// success!
											final_shot_x = b_x3
											final_shot_y = b_y3
											// add some randomness
											GENERATE_RANDOM_FLOAT_IN_RANGE -0.05 0.05 temp_float
											final_shot_y += temp_float
											// work out shot distance - over hit by between 5.0 and 8.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 8.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

								// TRY CUSHION 4
								IF final_shot_x = 99999.9
								AND final_shot_y = 99999.9
									b_x3 = pool_table_max_x
									// check point doesn't lie on pocket
									check_for_pocket_x = b_x3
									check_for_pocket_y = b_y3
									GOSUB is_point_too_near_pocket
									IF point_lies_on_pocket = 0
										// check if bounce shot is clear
										bounce2_ball1 = p_ball[0]
										bounce2_cushion_x = b_x3
										bounce2_cushion_y = b_y3
										GOSUB is_bounce_shot_path_clear_first_half_only
										IF bounce2_path_is_clear = 1	// success!
											final_shot_x = b_x3
											final_shot_y = b_y3
											// add some randomness
											GENERATE_RANDOM_FLOAT_IN_RANGE -0.05 0.05 temp_float
											final_shot_y += temp_float
											// work out shot distance - over hit by between 5.0 and 8.0
											GET_DISTANCE_BETWEEN_COORDS_2D b_x1 b_y1 b_x3 b_y3 temp_float
											GET_DISTANCE_BETWEEN_COORDS_2D b_x2 b_y2 b_x3 b_y3 temp_float2
											final_shot_distance	= temp_float + temp_float2
											GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 8.0 temp_float
											final_shot_distance *= temp_float 
											best_shot_int = 16											
										ENDIF
									ENDIF
								ENDIF

							ENDIF
						ENDIF			
					ENDIF 
				ENDIF
			best_shot_int++
			ENDWHILE
		ENDIF // end SNOOKER TYPE B
			
		
		// ##### SNOOKER TYPE C #####
		IF final_shot_x = 99999.9
		AND final_shot_y = 99999.9
			IF output_text = 1
				SAVE_NEWLINE_TO_DEBUG_FILE 
				SAVE_STRING_TO_DEBUG_FILE "snooker - last resort"
			ENDIF
	
		 	// if we still don't have a shot, resort to just picking a random heading and power and belt it.
			GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 360.0 ai_shot_heading 
			GENERATE_RANDOM_FLOAT_IN_RANGE 4.0 8.0 ai_shot_power
			final_shot_x = 0.0
			final_shot_y = 0.0
		ENDIF  

	ENDIF
			
	// E. We should now have the shot we want to play, now we just need to calculate the power and heading
	IF ai_shot_heading = 99999.9
	AND ai_shot_power  = 99999.9 
		
		// get heading
		GET_OBJECT_COORDINATES p_ball[0] b_x1 b_y1 b_z1
		b_x2 = final_shot_x - b_x1
		b_y2 = final_shot_y - b_y1
		GET_HEADING_FROM_VECTOR_2D b_x2 b_y2 ai_shot_heading
		 
		// might need to somehow take a bit off the heading to make it more accurate

		// get power
		ai_shot_power = final_shot_distance	* 1.0
	
		// add more power if it's a tighter angle
		IF ai_shot_angle < 90.0
			temp_float = ai_shot_angle / 90.0
			temp_float *= 2.0 
			ai_shot_power += temp_float
		ENDIF 

	ENDIF

	IF ai_shot_power < 0.7
		ai_shot_power = 0.7
	ENDIF
	IF ai_shot_power > 4.0
		ai_shot_power = 4.0
	ENDIF	  

RETURN



/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB IS_PATH_CLEAR 	/////////////////////////////////////////////////////////////////////////////////
// params
LVAR_INT   path_ball
LVAR_INT   path_ball2 // optional (use if destination x,y will overlap with a known other ball - set to 0 if not used)
LVAR_FLOAT path_dest_x path_dest_y 
// return
LVAR_INT path_is_clear
LVAR_INT obstructing_colour
// workings
LVAR_FLOAT p_x1 p_y1
LVAR_FLOAT p_x2 p_y2
LVAR_FLOAT p_vec1_x p_vec1_y
LVAR_FLOAT p_vec2_x p_vec2_y
LVAR_FLOAT p_vec3_x p_vec3_y
LVAR_FLOAT p_vec1_dist p_vec2_dist	p_dist_between_dest_and_ball
//LVAR_FLOAT p_cos_angle
LVAR_FLOAT p_sin_angle
LVAR_INT path_int path_int2 path_int3
LVAR_INT obstructing_ball
is_path_clear:

	// fake create
	GOTO is_path_clear_skip
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 path_ball
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 path_ball2
	is_path_clear_skip:

	path_is_clear = 1 // default - path is clear
	obstructing_ball = -1

	// vec 1 - ball to destination
	IF DOES_OBJECT_EXIST path_ball
		GET_OBJECT_COORDINATES path_ball p_x1 p_y1 z
		p_vec1_x = path_dest_x - p_x1
		p_vec1_y = path_dest_y - p_y1
		GET_DISTANCE_BETWEEN_COORDS_2D p_x1 p_y1 path_dest_x path_dest_y p_vec1_dist
	ENDIF

	WAIT 0

	// if there are no colours / or black / or stripes assigned start at begining
	IF turn_colour = POOL_COLOUR_NONE
	OR turn_colour = POOL_COLOUR_BLACK
	OR turn_colour = POOL_COLOUR_STRIPES
		path_int = 1
		WHILE path_int < 16
			GOSUB is_path_clear_internal_gosub
		path_int++
		ENDWHILE
	ELSE
		// if spots are assigned start at the black
		path_int3 = 1
		WHILE path_int3 < 16
			
			path_int = path_int3 + 7
			IF path_int > 15
				path_int += -15
			ENDIF
			GOSUB is_path_clear_internal_gosub

		path_int3++
		ENDWHILE

	ENDIF


path_ball2 = 0

is_path_clear_end:
RETURN

is_path_clear_internal_gosub:

	IF DOES_OBJECT_EXIST p_ball[path_int]
		IF ball_potted[path_int] = 0
			IF NOT p_ball[path_int] = path_ball
				
				// ingnore if this is path_ball2
				path_int2 = 0
				IF DOES_OBJECT_EXIST path_ball2
					IF p_ball[path_int] = path_ball2
						path_int2 = 1
					ENDIF  
				ENDIF

				IF path_int2 = 0						
					// vec 2 - path_ball to checking ball
					GET_OBJECT_COORDINATES p_ball[path_int] p_x2 p_y2 z
					p_vec2_x = p_x2 - p_x1
					p_vec2_y = p_y2 - p_y1
					GET_DISTANCE_BETWEEN_COORDS_2D p_x1 p_y1 p_x2 p_y2 p_vec2_dist

					// get angle between vectors
					GET_ANGLE_BETWEEN_2D_VECTORS p_vec1_x p_vec1_y p_vec2_x p_vec2_y temp_float

					// get distance between destination and checking ball
					GET_DISTANCE_BETWEEN_COORDS_2D p_x2 p_y2 path_dest_x path_dest_y p_dist_between_dest_and_ball

					IF p_dist_between_dest_and_ball < pool_ball_diameter
						// ball is obstructing
						IF path_int = 0
							obstructing_colour = 3
						ELSE
							IF path_int < 8
								obstructing_colour = POOL_COLOUR_SPOTS
							ELSE
								IF path_int = 8
									obstructing_colour = POOL_COLOUR_BLACK
								ELSE
									obstructing_colour = POOL_COLOUR_STRIPES
								ENDIF
							ENDIF
						ENDIF 
						IF output_text = 1
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_STRING_TO_DEBUG_FILE "    BALL OBSTRUCTING PATH (too close to target destination) = "
							SAVE_INT_TO_DEBUG_FILE path_int
						ENDIF
						obstructing_ball = path_int
						path_is_clear = 0

						path_int = 16
						path_int3 = 16

					ELSE
						// if angle is greater than 90.0 ball cannot be in path
						IF temp_float <= 90.0
							
							// if vec2_dist is greater than vec1_dist then it can't be obstructing
							IF p_vec2_dist < p_vec1_dist

								// find distance from ball to the path path_ball will travel
								SIN temp_float p_sin_angle
								temp_float = p_sin_angle * p_vec2_dist
								
								// if it's less than 2 radius then it will collide
								IF temp_float < pool_ball_diameter
									
									IF path_int = 0
										obstructing_colour = 3
									ELSE
										IF path_int < 8
											obstructing_colour = POOL_COLOUR_SPOTS
										ELSE
											IF path_int = 8
												obstructing_colour = POOL_COLOUR_BLACK
											ELSE
												obstructing_colour = POOL_COLOUR_STRIPES
											ENDIF
										ENDIF
									ENDIF 
									IF output_text = 1
										SAVE_NEWLINE_TO_DEBUG_FILE
										SAVE_STRING_TO_DEBUG_FILE "    BALL OBSTRUCTING PATH (overlapping path) = "
										SAVE_INT_TO_DEBUG_FILE path_int
									ENDIF
									obstructing_ball = path_int
									path_is_clear = 0
									path_int = 16
									path_int3 = 16
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


LVAR_FLOAT new_end_point_x new_end_point_y
LVAR_FLOAT new_bounce_vec_x new_bounce_vec_y
LVAR_FLOAT hit_ball_bounce_vec_x  hit_ball_bounce_vec_y

// workings
LVAR_FLOAT min_distance_to_test
LVAR_FLOAT closest_obstructing_ball
LVAR_FLOAT closest_ball_contact_distance_to_vector
LVAR_FLOAT closest_obstructing_ball_distance_to_path
is_path_closest_clear:

	path_is_clear = 1 // default - path is clear
	obstructing_ball = -1

	// vec 1 - ball to destination
	p_vec1_x = path_dest_x - p_x1
	p_vec1_y = path_dest_y - p_y1

		// check to see if we've hit a cushion
		IF path_dest_x < pool_table_min_x
		OR path_dest_x > pool_table_max_x
		OR path_dest_y < pool_table_min_y
		OR path_dest_y > pool_table_max_y


			new_end_point_x = -99999.9
			new_end_point_y	= -99999.9

			temp_int = 0
			// find contact point on cushion
			IF GET_2D_LINES_INTERSECT_POINT p_x1 p_y1 path_dest_x path_dest_y  pool_table_min_x pool_table_min_y pool_table_min_x pool_table_max_y new_end_point_x new_end_point_y
			AND NOT ignore_cushion = 1
				temp_int = 1
			ELSE
			IF GET_2D_LINES_INTERSECT_POINT p_x1 p_y1 path_dest_x path_dest_y  pool_table_max_x pool_table_min_y pool_table_max_x pool_table_max_y new_end_point_x new_end_point_y
			AND NOT ignore_cushion = 2
				temp_int = 2	
			ELSE
			IF GET_2D_LINES_INTERSECT_POINT p_x1 p_y1 path_dest_x path_dest_y  pool_table_min_x pool_table_min_y pool_table_max_x pool_table_min_y new_end_point_x new_end_point_y
			AND NOT ignore_cushion = 3
				temp_int = 3
			ELSE
			IF GET_2D_LINES_INTERSECT_POINT p_x1 p_y1 path_dest_x path_dest_y  pool_table_min_x pool_table_max_y pool_table_max_x pool_table_max_y new_end_point_x new_end_point_y
			AND NOT ignore_cushion = 4
				temp_int = 4
			ENDIF
			ENDIF
			ENDIF
			ENDIF

			// find bounce vector
			GET_DISTANCE_BETWEEN_COORDS_2D path_dest_x path_dest_y p_x1	p_y1 temp_float
			IF temp_float > 0.0
				p_vec1_x /= temp_float
				p_vec1_y /= temp_float
			ENDIF

			// find bounce vector
			IF temp_int = 1
			OR temp_int = 2
				new_bounce_vec_x = p_vec1_x * -1.0	 
				new_bounce_vec_y = p_vec1_y
			ENDIF
			IF temp_int = 3
			OR temp_int = 4
				new_bounce_vec_x = p_vec1_x 	
				new_bounce_vec_y = p_vec1_y	* -1.0
			ENDIF

			// ignore cushion just bounced off
			LVAR_INT ignore_cushion
			IF NOT temp_int = 0
				ignore_cushion = temp_int
			ENDIF

			path_is_clear = 0

		ELSE
			// nothing
			new_end_point_x = path_dest_x
			new_end_point_y	= path_dest_y

			path_is_clear = 1
		ENDIF

is_path_closest_clear_end:
RETURN




/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB IS_BALL_POTTABLE_INTO_POCKET 	/////////////////////////////////////////////////////////////////
LVAR_INT angle_ball
LVAR_INT angle_pocket
// return
LVAR_INT angle_is_possible
LVAR_FLOAT required_angle_of_shot
// workings
LVAR_FLOAT angle_vec1_x angle_vec1_y
LVAR_FLOAT angle_vec2_x angle_vec2_y
LVAR_FLOAT angle_vec3_x angle_vec3_y
LVAR_FLOAT angle_x1 angle_y1	// stores position of angle ball & required contact point
is_ball_pottable_into_pocket:

	// vec1. vector from angle ball to pocket
	IF DOES_OBJECT_EXIST angle_ball
		GET_OBJECT_COORDINATES angle_ball angle_x1 angle_y1 z
		angle_vec1_x = pocket_x[angle_pocket] - angle_x1
		angle_vec1_y = pocket_y[angle_pocket] - angle_y1 
	ENDIF 

	// vec2. vector from angle ball to required contact point
	GET_DISTANCE_BETWEEN_COORDS_2D x y pocket_x[angle_pocket] pocket_y[angle_pocket] temp_float
	angle_vec2_x = angle_vec1_x / temp_float
	angle_vec2_y = angle_vec1_y / temp_float
	angle_vec2_x *= -1.0 
	angle_vec2_y *= -1.0
	temp_float = pool_ball_radius * 2.0
	angle_vec2_x *= temp_float	
	angle_vec2_y *= temp_float
	angle_x1 += angle_vec2_x // changes angle_x1 to the required contact point
	angle_y1 += angle_vec2_y

	// vec3. vector from cue ball to required contact point
	IF DOES_OBJECT_EXIST p_ball[0]
		GET_OBJECT_COORDINATES p_ball[0] x y z
		angle_vec3_x = angle_x1 - x
		angle_vec3_y = angle_y1 - y
	ENDIF

	// find angle between vec1 and vec3 - this is the required angle to pot ball into this pocket
	GET_ANGLE_BETWEEN_2D_VECTORS angle_vec1_x angle_vec1_y angle_vec3_x angle_vec3_y required_angle_of_shot
	 
	IF required_angle_of_shot < 90.0
		angle_is_possible = 1
	ELSE
		angle_is_possible = 0	
	ENDIF
	 
RETURN


/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB CAN_WE_SEE_BALL			 	/////////////////////////////////////////////////////////////////
// params
LVAR_INT see_ball1 see_ball2
// return
LVAR_INT ball_can_see_ball // 0:cannot see ball, 1:can see right side, 2:can see left side, 3:can see both sides
// workings
LVAR_INT ball_can_see_right
LVAR_INT ball_can_see_left
LVAR_FLOAT see_vec_x1 see_vec_y1
LVAR_FLOAT see_vec_x2 see_vec_y2
LVAR_FLOAT see_x1 see_y1
LVAR_FLOAT see_x_right see_y_right
LVAR_FLOAT see_x_left see_y_left
can_we_see_ball:

	ball_can_see_ball  = 0
	ball_can_see_right = 0
	ball_can_see_left  = 0
	
	IF DOES_OBJECT_EXIST see_ball1
	AND DOES_OBJECT_EXIST see_ball2
		
		// TEST RIGHT SIDE
		// 1. get vector from ball1 to ball2
		GET_OBJECT_COORDINATES see_ball1 x y z
		GET_OBJECT_COORDINATES see_ball2 see_x1 see_y1 z
		see_vec_x1 = see_x1 - x
		see_vec_y1 = see_y1 - y
		// normalise
		GET_DISTANCE_BETWEEN_COORDS_2D x y see_x1 see_y1 temp_float
		see_vec_x1 /= temp_float
		see_vec_y1 /= temp_float
		
		// 2. get right hand vector
		see_vec_x2 = see_vec_y1
		see_vec_y2 = see_vec_x1 * -1.0

		// 3. get point we want to aim at (right hand side of ball - 80% of ball diameter to make sure we definately hit)
		temp_float = pool_ball_radius * 2.0
		temp_float *= 0.80
		temp_float2 = see_vec_x2 * temp_float
		see_x_right = see_x1 + temp_float2
		temp_float2 = see_vec_y2 * temp_float
		see_y_right = see_y1 + temp_float2
		// check if path is clear
		path_ball	= see_ball1
		path_ball2  = see_ball2
		path_dest_x = see_x_right
		path_dest_y = see_y_right
		GOSUB is_path_clear
		IF path_is_clear = 1
			ball_can_see_right = 1	
		ENDIF

		// TEST LEFT SIDE
		temp_float = pool_ball_radius * 2.0
		temp_float *= -0.80
		temp_float2 = see_vec_x2 * temp_float
		see_x_left = see_x1 + temp_float2
		temp_float2 = see_vec_y2 * temp_float
		see_y_left = see_y1 + temp_float2
		// check if path is clear
		path_ball	= see_ball1
		path_ball2  = see_ball2
		path_dest_x = see_x_left
		path_dest_y = see_y_left
		GOSUB is_path_clear
		IF path_is_clear = 1
			ball_can_see_left = 1	
		ENDIF

		// RETURN RESULT
		IF ball_can_see_right = 0
		AND ball_can_see_left = 0
			ball_can_see_ball = 0 
		ENDIF
		IF ball_can_see_right = 1
		AND ball_can_see_left = 0
			ball_can_see_ball = 1 
		ENDIF
		IF ball_can_see_right = 0
		AND ball_can_see_left = 1
			ball_can_see_ball = 2 
		ENDIF
		IF ball_can_see_right = 1
		AND ball_can_see_left = 1
			ball_can_see_ball = 3 
		ENDIF 
	ENDIF
RETURN


/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB IS_POINT_TOO_NEAR_POCKET	 	/////////////////////////////////////////////////////////////////
// params
LVAR_FLOAT check_for_pocket_x 
LVAR_FLOAT check_for_pocket_y
// return
LVAR_INT point_lies_on_pocket
// workings
LVAR_FLOAT pocket_dist
LVAR_FLOAT check_pocket_float
LVAR_INT check_pocket_int
is_point_too_near_pocket:

	point_lies_on_pocket = 0
	check_pocket_float = 2.5 * pool_ball_radius
	
	check_pocket_int = 0
	WHILE check_pocket_int < 6
		GET_DISTANCE_BETWEEN_COORDS_2D check_for_pocket_x check_for_pocket_y pocket_x[check_pocket_int]	pocket_y[check_pocket_int] pocket_dist
		IF pocket_dist < check_pocket_float
			SAVE_NEWLINE_TO_DEBUG_FILE 
			SAVE_STRING_TO_DEBUG_FILE "    point lies on pocket"
			point_lies_on_pocket = 1
			check_pocket_int = 6
		ENDIF
	check_pocket_int++
	ENDWHILE

RETURN



/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB IS_BOUNCE_SHOT_PATH_CLEAR		 	/////////////////////////////////////////////////////////////
// params
LVAR_INT bounce_ball1
LVAR_INT bounce_ball2
LVAR_FLOAT bounce_cushion_x bounce_cushion_y
// return
LVAR_INT bounce_path_is_clear
// workings
is_bounce_shot_path_clear:

   	bounce_path_is_clear = 0
   
	SAVE_NEWLINE_TO_DEBUG_FILE
	SAVE_STRING_TO_DEBUG_FILE "       bounce_shot_path_clear data = "

   	// is path from cue ball to cushion clear
   	path_ball  = bounce_ball1
	path_ball2 = 0
	path_dest_x = bounce_cushion_x
	path_dest_y = bounce_cushion_y
	GOSUB is_path_clear
	IF path_is_clear = 1
		SAVE_STRING_TO_DEBUG_FILE "1st path clear "
		// is path from ball2 to cushion clear
		path_ball  = bounce_ball2
		path_ball2 = 0
		path_dest_x = bounce_cushion_x
		path_dest_y = bounce_cushion_y
		GOSUB is_path_clear
		IF path_is_clear = 1
			SAVE_STRING_TO_DEBUG_FILE "2nd path clear "
			bounce_path_is_clear = 1
		ENDIF 
	ENDIF	

RETURN

/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB IS_BOUNCE_SHOT_PATH_CLEAR_FIRST_HALF_ONLY //////////////////////////////////////////////////////
// params
LVAR_INT bounce2_ball1
LVAR_FLOAT bounce2_cushion_x bounce2_cushion_y
// return
LVAR_INT bounce2_path_is_clear
// workings
is_bounce_shot_path_clear_first_half_only:

   	bounce2_path_is_clear = 0
   
   	// is path from ball1 to cushion clear
   	path_ball  = bounce_ball1
	path_ball2 = 0
	path_dest_x = bounce_cushion_x
	path_dest_y = bounce_cushion_y
	GOSUB is_path_clear
	IF path_is_clear = 1
		bounce2_path_is_clear = 1 
	ENDIF	

RETURN

/////////////////////////////////////////////////////////////////////////////////////////////////////////
// GOSUB UPDATE_BALL_TRIANGLE_POSITION 	 ////////////////////////////////////////////////////////////////
// params
LVAR_FLOAT ball_tri_x ball_tri_y ball_tri_z ball_tri_h
// workings
LVAR_FLOAT ball_radius_neg 
LVAR_FLOAT ball_radius_spacer ball_radius_spacer_neg 
LVAR_FLOAT ball_diameter	ball_diameter_neg
LVAR_INT triangle_int
update_ball_triangle_position:

	ball_diameter = pool_ball_radius * 2.0
	ball_radius_neg = pool_ball_radius * -1.0
	ball_diameter_neg = ball_diameter * -1.0

	temp_float = ball_diameter * ball_diameter 
	temp_float2 = pool_ball_radius * pool_ball_radius
	
	ball_radius_spacer = temp_float - temp_float2
	SQRT ball_radius_spacer ball_radius_spacer
	ball_radius_spacer_neg = ball_radius_spacer * -1.0
	

	triangle_int = 1
	WHILE triangle_int < 15
		FREEZE_OBJECT_POSITION p_ball[triangle_int] FALSE
	triangle_int++
	ENDWHILE

	// 1st row
	SET_OBJECT_COORDINATES p_ball[1] ball_tri_x ball_tri_y ball_tri_z 
	SET_OBJECT_HEADING p_ball[1] ball_tri_h
	// 2nd row
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[1] ball_radius_neg ball_radius_spacer_neg 0.0  x y z
	SET_OBJECT_COORDINATES p_ball[9] x y z
	SET_OBJECT_HEADING p_ball[9] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[9] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[3] x y z 
	SET_OBJECT_HEADING p_ball[3] ball_tri_h
	// 3rd row
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[9] ball_radius_neg ball_radius_spacer_neg 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[7] x y z 
	SET_OBJECT_HEADING p_ball[7] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[7] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[8] x y z
	SET_OBJECT_HEADING p_ball[8] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[8] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[10] x y z
	SET_OBJECT_HEADING p_ball[10] ball_tri_h
	// 4th row
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[7] ball_radius_neg ball_radius_spacer_neg 0.0  x y z
	SET_OBJECT_COORDINATES p_ball[11] x y z
	SET_OBJECT_HEADING p_ball[11] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[11] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[2] x y z
	SET_OBJECT_HEADING p_ball[2] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[2] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[15] x y z 
	SET_OBJECT_HEADING p_ball[15] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[15] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[5] x y z
	SET_OBJECT_HEADING p_ball[5] ball_tri_h
	// 5th row
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[11] ball_radius_neg ball_radius_spacer_neg 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[6] x y z 
	SET_OBJECT_HEADING p_ball[6] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[6] ball_diameter 0.0 0.0  x y z
	SET_OBJECT_COORDINATES p_ball[12] x y z 
	SET_OBJECT_HEADING p_ball[12] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[12] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[13] x y z 
	SET_OBJECT_HEADING p_ball[13] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[13] ball_diameter 0.0 0.0 x y z
	SET_OBJECT_COORDINATES p_ball[4] x y z 
	SET_OBJECT_HEADING p_ball[4] ball_tri_h
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[4] ball_diameter 0.0 0.0  x y z
	SET_OBJECT_COORDINATES p_ball[14] x y z 	
	SET_OBJECT_HEADING p_ball[14] ball_tri_h

RETURN

// *******************************************************************************************************
//									UPDATE_STANCE_COORDS
// *******************************************************************************************************
// TEMPORARY STUFF - floats and ints that don't need to be initialised
LVAR_FLOAT ball_0_x	ball_0_y ball_0_z
LVAR_FLOAT La_x1 La_y1 La_z1 La_x2 La_y2 La_z2
LVAR_FLOAT Lb_x1 Lb_y1 Lb_z1 Lb_x2 Lb_y2 Lb_z2
LVAR_FLOAT far_aim_stance_x close_aim_stance_x stance_x	shoot_stance_x
LVAR_FLOAT far_aim_stance_y close_aim_stance_y stance_y	shoot_stance_y
LVAR_FLOAT far_aim_stance_z close_aim_stance_z stance_z	shoot_stance_z
LVAR_FLOAT far_aim_stance_h close_aim_stance_h stance_h	shoot_stance_h
LVAR_FLOAT stance_distance
LVAR_INT current_stance	last_stance
LVAR_FLOAT table_path_v1_x table_path_v1_y	
LVAR_FLOAT table_path_v2_x table_path_v2_y	
LVAR_FLOAT table_path_v3_x table_path_v3_y	
LVAR_FLOAT table_path_v4_x table_path_v4_y
LVAR_FLOAT safe_stance_x safe_stance_y safe_stance_z	
update_stance_coords:

	table_path_v1_x = -0.9600 	  
	table_path_v1_y	= 0.4900 	
	table_path_v2_x = 0.9700 	
	table_path_v2_y	= 0.4900 
	table_path_v3_x = 0.9700 
	table_path_v3_y	= -0.4900 
	table_path_v4_x = -0.9600 
	table_path_v4_y	= -0.4900

	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS p_ball[0] 0.0 -5.0 0.0 La_x1 La_y1 La_z1
	GET_OBJECT_COORDINATES p_ball[0] La_x2 La_y2 La_z2
	GET_GROUND_Z_FOR_3D_COORD La_x2 La_y2 La_z2	z
	// find intesect point with path
	// v1 --- v2
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table table_path_v1_x table_path_v1_y 0.0 Lb_x1 Lb_y1 Lb_z1
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table table_path_v2_x table_path_v2_y 0.0 Lb_x2 Lb_y2 Lb_z2
	IF GET_2D_LINES_INTERSECT_POINT La_x1 La_y1 La_x2 La_y2 Lb_x1 Lb_y1 Lb_x2 Lb_y2 x y
		stance_x = x
		stance_y = y
		stance_z = z
	ENDIF
	// v2 --- v3
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table table_path_v3_x table_path_v3_y 0.0 Lb_x1 Lb_y1 Lb_z1
	IF GET_2D_LINES_INTERSECT_POINT La_x1 La_y1 La_x2 La_y2 Lb_x1 Lb_y1 Lb_x2 Lb_y2 x y
		stance_x = x
		stance_y = y
		stance_z = z
	ENDIF
	// v3 --- v4
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table table_path_v4_x table_path_v4_y 0.0 Lb_x2 Lb_y2 Lb_z2
	IF GET_2D_LINES_INTERSECT_POINT La_x1 La_y1 La_x2 La_y2 Lb_x1 Lb_y1 Lb_x2 Lb_y2 x y
		stance_x = x
		stance_y = y
		stance_z = z
	ENDIF
	// v4 --- v1
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table table_path_v1_x table_path_v1_y 0.0 Lb_x1 Lb_y1 Lb_z1
	IF GET_2D_LINES_INTERSECT_POINT La_x1 La_y1 La_x2 La_y2 Lb_x1 Lb_y1 Lb_x2 Lb_y2 x y
		stance_x = x
		stance_y = y
		stance_z = z
	ENDIF

	// get heading
	vec_x = La_x2 - stance_x
	vec_y =	La_y2 -	stance_y
	vec_z =	La_z2 -	stance_z
	GET_HEADING_FROM_VECTOR_2D vec_x vec_y stance_h
	far_aim_stance_h = stance_h
	close_aim_stance_h = stance_h
	shoot_stance_h = stance_h

	// set zeds
	shoot_stance_z = stance_z
	far_aim_stance_z = stance_z
	close_aim_stance_z = stance_z
	
	// normalise aim vector
	GET_DISTANCE_BETWEEN_COORDS_2D La_x2 La_y2 stance_x stance_y stance_distance
	vec_x /= stance_distance
	vec_y /= stance_distance

	// ======================== FAR AIM STANCE =============================
	// how far do we want to stand from the table? 2.0m say
	x = vec_x * 3.0
	y = vec_y * 3.0
	// update this stance coords
	far_aim_stance_x = stance_x - x
	far_aim_stance_y = stance_y - y

		
	x = vec_x * 1.0
	y = vec_y * 1.0
	safe_stance_x = stance_x - x
	safe_stance_y =	stance_y - y
	safe_stance_z =	stance_z

	// ====================== CLOSE AIM STANCE =========================== 
	
	// choose which stance to use
	// close
	IF stance_distance < 0.25
		current_stance = 1
	ELSE
	// medium
		IF stance_distance < 0.65
			current_stance = 2
		ELSE
			// far
			IF stance_distance < 1.1
				current_stance = 3
			ELSE
				// v.far
				current_stance = 4
			ENDIF
		ENDIF
	ENDIF

	// adjust stance coordinates depending on type of stance
	IF current_stance = 1
		x = vec_x * 0.615
		y = vec_y * 0.615
		close_aim_stance_x = La_x2 - x
		close_aim_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 2
		x = vec_x * 1.0
		y = vec_y * 1.0
		close_aim_stance_x = La_x2 - x
		close_aim_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 3
		x = vec_x * 1.6
		y = vec_y * 1.6
		close_aim_stance_x = La_x2 - x
		close_aim_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 4
		x = vec_x * 2.1
		y = vec_y * 2.1
		close_aim_stance_x = La_x2 - x
		close_aim_stance_y = La_y2 - y
	ENDIF

	GET_DISTANCE_BETWEEN_COORDS_2D La_x2 La_y2 close_aim_stance_x close_aim_stance_y distance
	// get distance from ball to close aim, if it's smaller than ball to cushion then change it
	temp_float = stance_distance
	temp_float += 0.3 // safe distance away from side of table
	IF distance < temp_float
		temp_float = stance_distance + 0.3
		x = vec_x * temp_float
		y = vec_y * temp_float
		close_aim_stance_x = La_x2 - x
		close_aim_stance_y = La_y2 - y
	ENDIF


	// ======================= SHOOT STANCE ==========================
	// work out shoot_stance position for next stange
	IF current_stance = 1
		x = vec_x * 0.655
		y = vec_y * 0.655
		shoot_stance_x = La_x2 - x
		shoot_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 2
		x = vec_x * 1.0
		y = vec_y * 1.0
		shoot_stance_x = La_x2 - x
		shoot_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 3
		x = vec_x * 1.6
		y = vec_y * 1.6
		shoot_stance_x = La_x2 - x
		shoot_stance_y = La_y2 - y
	ENDIF
	IF current_stance = 4
		x = vec_x * 2.1
		y = vec_y * 2.1
		shoot_stance_x = La_x2 - x
		shoot_stance_y = La_y2 - y
	ENDIF

RETURN

// ball and nib stuff

LVAR_FLOAT ball_sprite_x ball_sprite_y
LVAR_FLOAT ball_sprite_width ball_sprite_height
LVAR_FLOAT nib_sprite_x nib_sprite_y
LVAR_FLOAT nib_sprite_height nib_sprite_width
LVAR_FLOAT nib_speed

draw_ball_and_nib:

	// draw ball
	DRAW_SPRITE 1 ball_sprite_x ball_sprite_y ball_sprite_width ball_sprite_height 128 128 128 255			
	
	// draw nib
	DRAW_SPRITE 2 nib_sprite_x nib_sprite_y nib_sprite_height nib_sprite_width 128 128 128 255

	IF IS_BUTTON_PRESSED current_pad LEFTSTICKY
	OR IS_BUTTON_PRESSED current_pad LEFTSTICKX

		GET_POSITION_OF_ANALOGUE_STICKS current_pad LStickX LStickY RStickX RStickY
		x =# LStickX 
		y =# LStickY
		x /= 128.0
		y /= 128.0
		x *= 5.0
		y *= 5.0

		temp_float = nib_sprite_x + x												   
		temp_float3 = ball_sprite_width / 2.0										   
		temp_float2 = ball_sprite_x + temp_float3
		IF temp_float < temp_float2
			temp_float2 = ball_sprite_x - temp_float3
			IF temp_float > temp_float2
				x = temp_float
			ENDIF 
		ENDIF

		temp_float = nib_sprite_y + y
		temp_float3 = ball_sprite_height / 2.0
		temp_float2 = ball_sprite_y + temp_float3
		IF temp_float < temp_float2
			temp_float2 = ball_sprite_y - temp_float3
			IF temp_float > temp_float2
				y = temp_float
			ENDIF 
		ENDIF
		
		// test new nib positions are ok
		
		GET_DISTANCE_BETWEEN_COORDS_2D x y ball_sprite_x ball_sprite_y temp_float
		temp_float2 = ball_sprite_width / 3.2
		
		IF temp_float > temp_float2
		
			vec_x =	x - ball_sprite_x
			vec_y =	y - ball_sprite_y
			
			vec_x /= temp_float
			vec_y /= temp_float

			vec_x *= temp_float2
			vec_y *= temp_float2

			nib_sprite_x = ball_sprite_x + vec_x
			nib_sprite_y = ball_sprite_y + vec_y

		ELSE
			nib_sprite_x = x
			nib_sprite_y = y
		ENDIF
	ENDIF
RETURN


LVAR_FLOAT safe_x safe_y
LVAR_INT safe_int 
LVAR_FLOAT safe_float
LVAR_FLOAT safe_distance
LVAR_FLOAT safe_vec_x safe_vec_y
check_position_is_safe:

	// check distance between safe coords and all the balls

	safe_distance = pool_ball_radius * 2.0

	safe_int = 1
	WHILE safe_int < 16

		IF DOES_OBJECT_EXIST p_ball[safe_int]
			GET_OBJECT_COORDINATES p_ball[safe_int] x y z 
			GET_DISTANCE_BETWEEN_COORDS_2D x y safe_x safe_y safe_float
			IF safe_float < safe_distance
				safe_vec_x = safe_x	- x
				safe_vec_y = safe_y	- y
				safe_vec_x /= safe_float
				safe_vec_y /= safe_float
				safe_vec_x *= safe_distance
				safe_vec_y *= safe_distance
				safe_x = x + safe_vec_x
				safe_y = y + safe_vec_y
			ENDIF
		ENDIF

	safe_int++
	ENDWHILE
	
	// check we're still on the table surface
	safe_float = pool_table_min_x 
	IF safe_x < safe_float
		safe_x = safe_float
	ENDIF  
	safe_float = pool_table_max_x 
	IF safe_x > safe_float
		safe_x = safe_float
	ENDIF 
	safe_float = pool_table_min_y 
	IF safe_y < safe_float
		safe_y = safe_float
	ENDIF
	safe_float = pool_table_max_y 
	IF safe_y > safe_float
		safe_y = safe_float
	ENDIF

RETURN


VAR_FLOAT cam_x1 cam_y1 cam_z1
VAR_FLOAT cam_x2 cam_y2 cam_z2
VAR_FLOAT cam_nvec_x cam_rvec_x
VAR_FLOAT cam_nvec_y cam_rvec_y
VAR_FLOAT cam_dist

pl_update_camera_vector:
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 0.0 cam_x1 cam_y1 cam_z1
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 -1.0 0.0 cam_x2 cam_y2 cam_z2	

	cam_nvec_x = cam_x1 - cam_x2
	cam_nvec_y = cam_y1 - cam_y2
	GET_DISTANCE_BETWEEN_COORDS_2D cam_x1 cam_y1 cam_x2 cam_y2 cam_dist
	cam_nvec_x /= cam_dist 
	cam_nvec_y /= cam_dist
	
	cam_rvec_x = cam_nvec_y
	cam_rvec_y = cam_nvec_x
	cam_rvec_y *= -1.0
RETURN



// ******************************************************************************************
//							DISPLAY HELP SCREENS
// ******************************************************************************************
 
pool_draw_window_1:

	DRAW_WINDOW 29.0 220.0 screen_coord_x[5] screen_coord_y[5] BJ_TITL SWIRLS_RIGHT 
	GOSUB pl_text 
	SET_TEXT_COLOUR 134 155 184 255 
	SET_TEXT_WRAPX 640.0 
	DISPLAY_TEXT 36.0 240.0 BJ_01

	GOSUB pl_text 
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 260.0 DOLLAR pl_current_wager

	GOSUB pl_text 
	SET_TEXT_COLOUR 134 155 184 255 
	SET_TEXT_WRAPX 640.0 
	DISPLAY_TEXT 36.0 290.0 BJ_02

	GOSUB pl_text 
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 310.0 DOLLAR pool_min_bet 

	GOSUB pl_text 
	SET_TEXT_COLOUR 134 155 184 255 
	SET_TEXT_WRAPX 640.0 
	DISPLAY_TEXT 36.0 340.0 BJ_03

	GOSUB pl_text
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 360.0 DOLLAR pool_max_bet 

RETURN

LVAR_INT win2_longest_width

pool_draw_window_2:
    
	IF foul_type = 0
	AND no_of_balls_potted_this_turn = 0
		win3_dont_draw_help = 1
		GOSUB pool_draw_window_3
	ELSE
		
	//IF temp_int = 1

		DRAW_WINDOW 29.0 220.0 screen_coord_x[1] screen_coord_y[1] SCORE SWIRLS_RIGHT
			
		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 240.0 PL_01

		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player1_colour = POOL_COLOUR_SPOTS
			DISPLAY_TEXT 36.0 260.0 PL_12  // Player1, Solids
		ENDIF
		IF player1_colour = POOL_COLOUR_STRIPES
			DISPLAY_TEXT 36.0 260.0 PL_13  // Player1, Stripes
		ENDIF
		IF player1_colour = POOL_COLOUR_NONE
			DISPLAY_TEXT 36.0 260.0 PL_15  // Player1, Open Table
		ENDIF
		IF player1_colour = POOL_COLOUR_BLACK
			DISPLAY_TEXT 36.0 260.0 PL_14  // Player1, Black
		ENDIF

		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 

		IF TWO_PLAYERS = 1
		    DISPLAY_TEXT 36.0 290.0 PL_02
		ELSE
		    DISPLAY_TEXT 36.0 290.0 PL_03
		ENDIF

		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player2_colour = POOL_COLOUR_SPOTS
		    DISPLAY_TEXT 36.0 310.0 PL_12  // CPU, Solids
		ENDIF
		IF player2_colour = POOL_COLOUR_STRIPES
		    DISPLAY_TEXT 36.0 310.0 PL_13  // CPU, Stripes
		ENDIF
		IF player2_colour = POOL_COLOUR_NONE
		    DISPLAY_TEXT 36.0 310.0 PL_15  // CPU, Open Table
		ENDIF
		IF player2_colour = POOL_COLOUR_BLACK
		    DISPLAY_TEXT 36.0 310.0 PL_14  // CPU, Black
		ENDIF


		// outcome
		IF foul_type > 0

			GOSUB pl_text
			SET_TEXT_COLOUR 134 155 184 255 
		    DISPLAY_TEXT 36.0 340.0 PL_04

			GOSUB pl_text
			SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]
		    
			SWITCH foul_type  // We'll let these strings wrap onto two lines.
			
				CASE 0
				BREAK
				CASE 1 
					DISPLAY_TEXT 36.0 360.0 FOUL1  // FOUL! Missed all balls
				BREAK
				CASE 2
					DISPLAY_TEXT 36.0 360.0 FOUL2  // FOUL! Hit wrong ball first! 
				BREAK
				CASE 3
					DISPLAY_TEXT 36.0 360.0 FOUL3  // FOUL! Pocketed white! 
				BREAK
				CASE 4
					DISPLAY_TEXT 36.0 360.0 FOUL4  // FOUL! Pocketed wrong color!
				BREAK
				CASE 5
					DISPLAY_TEXT 36.0 360.0 FOUL5  // FOUL! Pocketed black out of turn!
				BREAK
				CASE 6
					DISPLAY_TEXT 36.0 360.0 FOUL6  // FOUL! No balls hit the rail!
				BREAK
			ENDSWITCH
        
		
		// some balls have been potted 

		ELSE

			IF no_of_balls_potted_this_turn > 0

				IF no_of_balls_potted_this_turn = 1
					
					// only 1
					GOSUB pl_text
					SET_TEXT_COLOUR 134 155 184 255
					
					DISPLAY_TEXT 36.0 340.0 PL_05  // Pocketed Ball!
					
					// show which ball was potted
					temp_int = 1
					WHILE temp_int < 16
						IF NOT balls_potted_this_turn[temp_int]	= 0
							GOSUB pl_text
							SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]
							DISPLAY_TEXT_WITH_NUMBER 36.0 360.0 NUM temp_int
							temp_int = 16
						ENDIF
					temp_int++
					ENDWHILE

				ELSE
					// more than 1
					GOSUB pl_text
					SET_TEXT_COLOUR 134 155 184 255
					DISPLAY_TEXT 36.0 340.0 PL_06   // Pocketed Balls!

					// display balls that have been potted 
					temp_int = 1
					temp_int2 = no_of_balls_potted_this_turn
					temp_float = 36.0
																			
					WHILE temp_int2 > 0

						WHILE temp_int < 16
							
							IF NOT balls_potted_this_turn[temp_int]	= 0
									
								// If it's not the last in the list it needs a comma after it.
								
								IF temp_int2 > 1
								
									GOSUB pl_text
									SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]
								
									GET_STRING_WIDTH_WITH_NUMBER COMMA temp_int temp_int3
									temp_float2 =# temp_int3
									
									DISPLAY_TEXT_WITH_NUMBER temp_float 360.0 COMMA temp_int
									temp_float += temp_float2
									
									temp_int++
									temp_int2--
								
								// If it's the last in the list, it does not need a comma.
								 
								ELSE
								
									GOSUB pl_text
									SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

									DISPLAY_TEXT_WITH_NUMBER temp_float 360.0 NUM temp_int
									temp_int2 = -1
									temp_int = 16
								ENDIF
							ELSE
								temp_int++
							ENDIF
							IF temp_int >= 16
								temp_int2 = -1
							ENDIF
						ENDWHILE
					ENDWHILE
				ENDIF
			ENDIF

		ENDIF
	ENDIF
RETURN

pool_edit_sprites:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
		ball_sprite_height += 1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z
		ball_sprite_height += -1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		ball_sprite_width += 1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
		ball_sprite_width += -1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
		nib_sprite_height += 1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z
		nib_sprite_height += -1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		nib_sprite_width += 1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
	OR IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
		nib_sprite_width += -1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "ball_sprite_height = "
		SAVE_FLOAT_TO_DEBUG_FILE ball_sprite_height
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "ball_sprite_width = "
		SAVE_FLOAT_TO_DEBUG_FILE ball_sprite_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "nib_sprite_height = "
		SAVE_FLOAT_TO_DEBUG_FILE nib_sprite_height
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "nib_sprite_width = "
		SAVE_FLOAT_TO_DEBUG_FILE nib_sprite_width
	ENDIF
RETURN


LVAR_INT win3_dont_draw_help
pool_draw_window_3:

	// hide in overhead view after 3 secs

	IF win3_dont_draw_help = 0
		temp_int = 0
		IF help_timer < 3000
			temp_int = 1
		ENDIF
		IF camera_mode = 1
			help_timer = 0
			temp_int = 1
			IF aim_help_flag > 1
				aim_help_flag = 0
			ENDIF
		ENDIF 

		IF aim_help_flag = 0 
			CLEAR_HELP

			PRINT_HELP_FOREVER PL_H3  
			
			help_timer = 0
			aim_help_flag++
		ENDIF
		
		IF m_stage = 5
			IF aim_help_flag = 1
				IF help_timer > 3000
					CLEAR_HELP
					PRINT_HELP_FOREVER PL_H7
					aim_help_flag++
				ENDIF
			ENDIF
		ELSE
			temp_int = 1
		ENDIF
		
	ELSE
		temp_int = 1
		win3_dont_draw_help = 0
	ENDIF


	IF temp_int = 1

		DRAW_WINDOW 29.0 270.0 screen_coord_x[2] screen_coord_y[2] SCORE SWIRLS_RIGHT 
		GOSUB pl_text 
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 290.0 PL_01
		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player1_colour = POOL_COLOUR_SPOTS
		    DISPLAY_TEXT 36.0 310.0 PL_12  // Player 1, Solids
		ENDIF
		IF player1_colour = POOL_COLOUR_STRIPES
		    DISPLAY_TEXT 36.0 310.0 PL_13  // Player 1, Stripes
		ENDIF
		IF player1_colour = POOL_COLOUR_NONE
		    DISPLAY_TEXT 36.0 310.0 PL_15  // Player 1, Open Table
		ENDIF
		IF player1_colour = POOL_COLOUR_BLACK
		    DISPLAY_TEXT 36.0 310.0 PL_14  // Player 1, Black
		ENDIF

		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 
		IF two_players = 1
			DISPLAY_TEXT 36.0 340.0 PL_02
		ELSE
			DISPLAY_TEXT 36.0 340.0 PL_03
		ENDIF
		
		GOSUB pl_text 
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player2_colour = POOL_COLOUR_SPOTS
		    DISPLAY_TEXT 36.0 360.0 PL_12  // CPU, Solids
		ENDIF
		IF player2_colour = POOL_COLOUR_STRIPES
		    DISPLAY_TEXT 36.0 360.0 PL_13  // CPU, Stripes
		ENDIF
		IF player2_colour = POOL_COLOUR_NONE
		    DISPLAY_TEXT 36.0 360.0 PL_15  // CPU, Open Table
		ENDIF
		IF player2_colour = POOL_COLOUR_BLACK
		    DISPLAY_TEXT 36.0 360.0 PL_14  // CPU, Black
		ENDIF
	ENDIF
RETURN


init_pool_screen_coords:

    // text scale 2
    SCREEN_COORD_X[0] = 0.4753 
    SCREEN_COORD_Y[0] = 2.3176  

    // window 2	- bottom corner
	SWITCH current_Language
	CASE LANGUAGE_FRENCH
		SCREEN_COORD_X[1] = 210.6556 
		SCREEN_COORD_Y[1] = 426.4957
		BREAK
	CASE LANGUAGE_SPANISH
		SCREEN_COORD_X[1] = 175.0805 
		SCREEN_COORD_Y[1] = 426.4957
		BREAK
	CASE LANGUAGE_GERMAN
		SCREEN_COORD_X[1] = 190.782 
		SCREEN_COORD_Y[1] = 409.0
		BREAK
	CASE LANGUAGE_ITALIAN
		SCREEN_COORD_X[1] = 214.9799 
		SCREEN_COORD_Y[1] = 409.0
		BREAK
	DEFAULT
		SCREEN_COORD_X[1] = 174.4835 
		SCREEN_COORD_Y[1] = 409.0
		BREAK
	ENDSWITCH

    // window 3 - bottom corner
	SWITCH current_Language
	CASE LANGUAGE_SPANISH
		SCREEN_COORD_X[2] = 179.098 
		SCREEN_COORD_Y[2] = 396.8582
		BREAK
	CASE LANGUAGE_GERMAN
		SCREEN_COORD_X[2] = 166.0992 
		SCREEN_COORD_Y[2] = 396.8582
		BREAK
	CASE LANGUAGE_ITALIAN
		SCREEN_COORD_X[2] = 205.4915 
		SCREEN_COORD_Y[2] = 396.8582
		BREAK
	DEFAULT
		SCREEN_COORD_X[2] = 157.0000 
		SCREEN_COORD_Y[2] = 396.8582
		BREAK
	ENDSWITCH

    // window 4 - bottom corner
	SWITCH current_Language
	CASE LANGUAGE_ITALIAN
		SCREEN_COORD_X[3] = SCREEN_COORD_X[2]	
		SCREEN_COORD_Y[3] = 394.4953
		BREAK
	DEFAULT
		SCREEN_COORD_X[3] = 168.1938 	
		SCREEN_COORD_Y[3] = 394.4953 
		BREAK
	ENDSWITCH

    // text scale 1
    SCREEN_COORD_X[4] = 0.4914 
    SCREEN_COORD_Y[4] = 2.3977 

    // wager win - bottom corner
	SWITCH current_Language
	CASE LANGUAGE_ITALIAN
		SCREEN_COORD_X[5] = 178.0981 
		SCREEN_COORD_Y[5] = 409.0
		BREAK
	DEFAULT
		SCREEN_COORD_X[5] = 157.0 
		SCREEN_COORD_Y[5] = 409.0
		BREAK
	ENDSWITCH

RETURN


LVAR_FLOAT screen_coord_x[6] screen_coord_y[6]
LVAR_INT screen_edit
pool_edit_text_size:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
		screen_edit++
		IF screen_edit > 5
			screen_edit = 0
		ENDIF
		WRITE_DEBUG_WITH_INT screen_edit screen_edit
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		screen_coord_y[screen_edit] += -0.1	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		screen_coord_y[screen_edit] += 0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
		screen_coord_x[screen_edit] += 0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
		screen_coord_x[screen_edit] += -0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER

		SAVE_NEWLINE_TO_DEBUG_FILE 
		SAVE_STRING_TO_DEBUG_FILE "POOL - SCREEN_COORDS"
		SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int = 0
		WHILE temp_int < 6
			SAVE_STRING_TO_DEBUG_FILE "screen_coord_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE screen_coord_x[temp_int] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "screen_coord_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE screen_coord_y[temp_int] 
			SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int++
		ENDWHILE
	ENDIF
RETURN


pool_draw_window_4:

	// hide after 3 secs
	temp_int = 0
	IF help_timer < 3000
		temp_int = 1
	ENDIF

	IF aim_help_flag = 0 
		CLEAR_HELP
		PRINT_HELP_FOREVER PL_H6
		help_timer = 0
		aim_help_flag++
	ENDIF
	
	IF m_stage = 4
		IF aim_help_flag = 1
			IF help_timer > 3000
				CLEAR_HELP
				PRINT_HELP_FOREVER PL_H7
				aim_help_flag++
			ENDIF
		ENDIF
	ENDIF
	
	IF temp_int = 1
		DRAW_WINDOW 29.0 220.0 screen_coord_x[3] screen_coord_y[3] SCORE SWIRLS_RIGHT
		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 240.0 PL_01
		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player1_colour = POOL_COLOUR_SPOTS
			DISPLAY_TEXT 36.0 260.0 PL_12
		ENDIF

		IF player1_colour = POOL_COLOUR_STRIPES
			DISPLAY_TEXT 36.0 260.0 PL_13
		ENDIF

		IF player1_colour = POOL_COLOUR_NONE
			DISPLAY_TEXT 36.0 260.0 PL_15
		ENDIF

		IF player1_colour = POOL_COLOUR_BLACK
			DISPLAY_TEXT 36.0 260.0 PL_14
		ENDIF

		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 

		IF two_players = 1
			DISPLAY_TEXT 36.0 290.0 PL_02
		ELSE
			DISPLAY_TEXT 36.0 290.0 PL_03
		ENDIF

		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]

		IF player2_colour = POOL_COLOUR_SPOTS
			DISPLAY_TEXT 36.0 310.0 PL_12
		ENDIF

		IF player2_colour = POOL_COLOUR_STRIPES
			DISPLAY_TEXT 36.0 310.0 PL_13
		ENDIF

		IF player2_colour = POOL_COLOUR_NONE
			DISPLAY_TEXT 36.0 310.0 PL_15
		ENDIF

		IF player2_colour = POOL_COLOUR_BLACK
			DISPLAY_TEXT 36.0 310.0 PL_14
		ENDIF

		GOSUB pl_text
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 340.0 PL_16

		GOSUB pl_text
		SET_TEXT_SCALE screen_coord_x[0] screen_coord_y[0]
		DISPLAY_TEXT 36.0 360.0 PL_17
	ENDIF
RETURN



// ******************************************************************************************
//							ONSCREEN TEXT GOSUBS
// ******************************************************************************************

pl_help_text_1:
	SET_TEXT_SCALE 0.4487 1.5974 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
pl_help_text_2:
	SET_TEXT_SCALE 0.4487 1.5974 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN

pl_txt_big_blue_centre:
	SET_TEXT_SCALE 0.9 2.4 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
pl_txt_big_red_centre:
	SET_TEXT_SCALE 0.9 2.4 
	SET_TEXT_COLOUR 081 25 29 255
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
pl_txt_med_blue_left:
	SET_TEXT_SCALE 0.54 1.44 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
pl_txt_small_blue_left:
	SET_TEXT_SCALE 0.405 1.08 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
pl_txt_med_red_left:
	SET_TEXT_SCALE 0.54 1.44 
	SET_TEXT_COLOUR 081 25 29 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN

LVAR_FLOAT text_float
pl_text:
	SET_TEXT_COLOUR 180 180 180 255
	SET_TEXT_SCALE screen_coord_x[4] screen_coord_y[4]
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	text_float = screen_coord_x[1] - 20.0
	SET_TEXT_WRAPX text_float
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
RETURN


LVAR_FLOAT ball_turn_mass
edit_pool_turn_mass:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP

		IF DOES_OBJECT_EXIST p_ball[0]
			GET_OBJECT_TURN_MASS p_ball[0] ball_turn_mass
		ENDIF
		ball_turn_mass += 0.001

		// update all the balls
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
				SET_OBJECT_TURN_MASS p_Ball[temp_int] ball_turn_mass
			ENDIF
		temp_int++
		ENDWHILE
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN

		IF DOES_OBJECT_EXIST p_ball[0]
			GET_OBJECT_TURN_MASS p_ball[0] ball_turn_mass
		ENDIF
		ball_turn_mass += -0.0001

		IF ball_turn_mass <= 0.0
			ball_turn_mass = 0.0001
		ENDIF

		// update all the balls
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
				SET_OBJECT_TURN_MASS p_Ball[temp_int] ball_turn_mass
			ENDIF
		temp_int++
		ENDWHILE
	ENDIF

	// update all the balls
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
		temp_int = 0
		WHILE temp_int < 16
			IF DOES_OBJECT_EXIST p_ball[temp_int]
				SET_OBJECT_TURN_MASS p_Ball[temp_int] ball_turn_mass
			ENDIF
		temp_int++
		ENDWHILE
	ENDIF



	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
		// increase 
		pool_stop_velocity += 0.001
		//WRITE_DEBUG_WITH_FLOAT pool_stop_velocity pool_stop_velocity
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
		// increase 
		pool_stop_velocity += -0.001
		//WRITE_DEBUG_WITH_FLOAT pool_stop_velocity pool_stop_velocity
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
		// increase 
		pool_stop_rotation_velocity += 0.001
		//WRITE_DEBUG_WITH_FLOAT pool_stop_rotation_velocity pool_stop_rotation_velocity
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
		// increase 
		pool_stop_rotation_velocity += -0.001
		//WRITE_DEBUG_WITH_FLOAT pool_stop_rotation_velocity pool_stop_rotation_velocity
	ENDIF

RETURN



// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_POOL2:
	PRINT_BIG M_FAIL 5000 1
RETURN

// PASS
mission_passed_POOL2:
	PRINT_WITH_NUMBER_BIG ( M_PASS ) 10000 5000 1 //"Mission Passed!"
	ADD_SCORE player1 10000
	CLEAR_WANTED_LEVEL player1
RETURN

// CLEANUP
mission_cleanup_POOL2:

	MAKE_PLAYER_GANG_REAPPEAR
	DISABLE_2ND_PAD_FOR_DEBUG FALSE
	SET_SCRIPT_COOP_GAME FALSE

	disable_debug = 0

	CLEAR_MISSION_AUDIO 4

	temp_int = 0
	WHILE temp_int < 10
		IF DOES_OBJECT_EXIST proj_cue_ball_objects[temp_int]
			DELETE_OBJECT proj_cue_ball_objects[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

	IF NOT IS_CHAR_DEAD active_pool_opponent
		CLEAR_LOOK_AT active_pool_opponent
		GET_CHAR_COORDINATES active_pool_opponent x y z
		GET_GROUND_Z_FOR_3D_COORD x y z z
		player1_home_z = z
		player2_home_z = z
	ENDIF

	// set chars to their home position
	
	IF NOT IS_CHAR_DEAD active_pool_opponent
		GET_CHAR_COORDINATES active_pool_opponent x y z
		SET_CHAR_COORDINATES active_pool_opponent x y player2_home_z
	ENDIF
	
	IF IS_PLAYER_PLAYING player1 
		GET_CHAR_COORDINATES scplayer x y z
		SET_CHAR_COORDINATES scplayer player1_home_x player1_home_y player1_home_z
		IF NOT IS_CHAR_DEAD active_pool_opponent
			GET_CHAR_COORDINATES active_pool_opponent x2 y2 z2
		ENDIF
		vec_x = x2 - x
		vec_y = y2 - y
		GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
		SET_CHAR_HEADING scplayer heading
	ENDIF



	REQUEST_MODEL POOLCUE
	WHILE NOT HAS_MODEL_LOADED POOLCUE
		WAIT 0
	ENDWHILE
	
	// give both chars weapons
	IF IS_PLAYER_PLAYING player1
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		DROP_OBJECT scplayer FALSE
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_POOL_CUE 9999
	ENDIF

	// delete cues
	IF DOES_OBJECT_EXIST cue1 
		DELETE_OBJECT cue1
	ENDIF
	IF DOES_OBJECT_EXIST cue2
		DELETE_OBJECT cue2
	ENDIF
	IF DOES_OBJECT_EXIST aim_cue
		DELETE_OBJECT aim_cue
	ENDIF
	IF DOES_OBJECT_EXIST active_initial_cue
		DELETE_OBJECT active_initial_cue
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED POOLCUE
	MARK_MODEL_AS_NO_LONGER_NEEDED K_POOLQ
	REMOVE_ANIMATION POOL
	
	// === MARK ENTITIES AS NO LONGER NEEDED === (cars,peds,objects,blips,attractors)
	
	// === REMOVE ANY OTHER LOADED STUFF === (models,animations,textures)
	
	IF IS_PLAYER_PLAYING player1
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COLLISION scplayer TRUE
	ENDIF

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0


	CLEAR_PRINTS
	CLEAR_HELP

	flag_player_on_mission = 0
	
	IF IS_PLAYER_PLAYING player1
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_LOOK_AT scplayer
		OPEN_SEQUENCE_TASK temp_seq
			TASK_STAND_STILL -1 1
			TASK_PAUSE -1 1
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK scplayer temp_seq
		CLEAR_SEQUENCE_TASK temp_seq
	ENDIF

	MISSION_HAS_FINISHED
RETURN

POOL_find_camera_position:

	// for camera position
	GET_DEBUG_CAMERA_COORDINATES x y z
	IF DOES_OBJECT_EXIST table
		GET_OBJECT_COORDINATES table x2 y2 z2
		GET_OBJECT_HEADING table heading
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
	SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE vec_z

	// for camera point at
	GET_DEBUG_CAMERA_POINT_AT x y z
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
	SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE vec_z

RETURN

POOL_find_camera_position_from_char:

	// for camera position
	GET_DEBUG_CAMERA_COORDINATES x y z
	IF DOES_CHAR_EXIST current_char
		IF NOT IS_CHAR_DEAD current_char
			GET_CHAR_COORDINATES current_char x2 y2 z2
			GET_CHAR_HEADING current_char heading
		ENDIF
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
	SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM char = "
	SAVE_FLOAT_TO_DEBUG_FILE vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE vec_z

	// for camera point at
	GET_DEBUG_CAMERA_POINT_AT x y z
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
	SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM char = "
	SAVE_FLOAT_TO_DEBUG_FILE vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE vec_z
RETURN
}