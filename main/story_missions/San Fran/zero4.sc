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
SCRIPT_NAME ZERO4
GOSUB mission_start_ZERO4
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_ZERO4
ENDIF
GOSUB mission_cleanup_ZERO4
MISSION_END

mission_start_ZERO4:
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

mission_loop_ZERO4:
WAIT 0
// *************************************************************************************************************
// 												DEBUG TOOLS   
// *************************************************************************************************************

// Display mission stage variables for debug
LVAR_INT display_debug
VAR_INT ZERO4_view_debug[8]
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
	display_debug++
	IF display_debug > 8
		display_debug = 0
	ENDIF
	CLEAR_ALL_VIEW_VARIABLES
ENDIF
IF display_debug = 1
	// system variables
	ZERO4_view_debug[0] = m_stage
	ZERO4_view_debug[1] = m_goals
	ZERO4_view_debug[2] = dialogue_flag
	ZERO4_view_debug[3] = dialogue_timer
	ZERO4_view_debug[4] = help_flag
	ZERO4_view_debug[5] = help_timer
	ZERO4_view_debug[6] = TIMERA
	ZERO4_view_debug[7]	= TIMERB
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] m_stage
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] m_goals
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] dialogue_flag
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] dialogue_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] help_flag
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] help_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] TIMERA
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] TIMERB
ENDIF
IF display_debug = 2
	ZERO4_view_debug[0] = pcar_progress
	ZERO4_view_debug[1] = pcar_lives_left
	ZERO4_view_debug[2] = first_bridge	
	ZERO4_view_debug[3] = second_bridge
	ZERO4_view_debug[4] = first_bridge_laid 
	ZERO4_view_debug[5] = second_bridge_laid
	ZERO4_view_debug[6] = first_bridge_plank
	ZERO4_view_debug[7]	= second_bridge_plank
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] pcar_progress
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] pcar_lives_left
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] first_bridge	
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] second_bridge
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] first_bridge_laid 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] second_bridge_laid
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] first_bridge_plank
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] second_bridge_plank
ENDIF
IF display_debug = 3	  
	ZERO4_view_debug[0] = pcar_is_paused
	ZERO4_view_debug[1] = pcar_is_obstructed
	ZERO4_view_debug[2] = bomb_is_armed	
	ZERO4_view_debug[3] = heli_is_carrying
	ZERO4_view_debug[4] = circle_is_pressed 
	ZERO4_view_debug[5] = rc_bomb_timer
	ZERO4_view_debug[6] = pheli_pickup_timer
	ZERO4_view_debug[7]	= oheli_flag
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] pcar_is_paused
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] pcar_is_obstructed
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] bomb_is_armed	
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] heli_is_carrying
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] circle_is_pressed 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] rc_bomb_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] pheli_pickup_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] oheli_flag
ENDIF
IF display_debug = 4	  
	ZERO4_view_debug[0] = oheli_rock
	ZERO4_view_debug[1] = oheli_goto
	ZERO4_view_debug[2] = pheli_lives	
	ZERO4_view_debug[3] = otank_flag[0]
	ZERO4_view_debug[4] = otank_flag[1] 
	ZERO4_view_debug[5] = otank_flag[2]
	ZERO4_view_debug[6] = respawned_tanks
	ZERO4_view_debug[7]	= z4_pcar_health
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] oheli_rock
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] oheli_goto
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] pheli_lives	
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] otank_flag[0]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] otank_flag[1] 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] otank_flag[2]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] respawned_tanks
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] z4_pcar_health
ENDIF
IF display_debug = 5	  
	ZERO4_view_debug[0] = otank_goto[0]
	ZERO4_view_debug[1] = otank_goto[1]
	ZERO4_view_debug[2] = otank_goto[2]
	ZERO4_view_debug[3] = last_health
	ZERO4_view_debug[4] = bandit_taking_damage
	ZERO4_view_debug[5] = pcar_timer
	ZERO4_view_debug[6] = pcar_halted
	ZERO4_view_debug[7]	= pcar_R3_pressed
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] otank_goto[0]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] otank_goto[1]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] otank_goto[2]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] last_health
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] bandit_taking_damage 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] pcar_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] pcar_halted
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] pcar_R3_pressed
ENDIF
IF display_debug = 6	  
	ZERO4_view_debug[0] = otank_target[0]
	ZERO4_view_debug[1] = otank_target[1]
	ZERO4_view_debug[2] = otank_target[2]
	ZERO4_view_debug[3] = carried_object	
	ZERO4_view_debug[4] = zero4_timer
	ZERO4_view_debug[5] = get_plank1
	ZERO4_view_debug[6] = get_plank2
//	ZERO4_view_debug[7]	= 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] otank_target[0]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] otank_target[1]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] otank_target[2]
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] carried_object	
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] zero4_timer
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] get_plank1
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] get_plank2
//	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] 
ENDIF
IF display_debug = 7	  
	ZERO4_view_debug[0] = first_pickup_help 		
	ZERO4_view_debug[1] = first_time_stuck_at_river 
	ZERO4_view_debug[2] = plank_blip				  
	ZERO4_view_debug[3] = stuck_at_river_dialogue	  
	ZERO4_view_debug[4] = obstruct_audio1 			 
	ZERO4_view_debug[5] = obstruct_audio2  			 
	ZERO4_view_debug[6] = play_first_time_blocked_audio
	ZERO4_view_debug[7]	= bomb_blip						
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] first_pickup_help 		
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] first_time_stuck_at_river 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] plank_blip				  
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] stuck_at_river_dialogue	  
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] obstruct_audio1 			 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] obstruct_audio2  			 
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] play_first_time_blocked_audio
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] bomb_blip						
ENDIF
IF display_debug = 8	  
	ZERO4_view_debug[0] = first_time_taking_damage_from_tank
	ZERO4_view_debug[1] = bomb_tank_dialogue				   
	ZERO4_view_debug[2] = dead_dialogue			  
	ZERO4_view_debug[3] = nearly_dead_dialogue	  
	ZERO4_view_debug[4] = stuck_at_river			 
//	ZERO4_view_debug[5] = obstruct_audio2  			 
//	ZERO4_view_debug[6] = play_first_time_blocked_audio
//	ZERO4_view_debug[7]	= bomb_blip						
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[0] first_time_taking_damage_from_tank
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[1] bomb_tank_dialogue				   
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[2] dead_dialogue			  
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[3] nearly_dead_dialogue	  
	VIEW_INTEGER_VARIABLE ZERO4_view_debug[4] stuck_at_river			 
//	VIEW_INTEGER_VARIABLE ZERO4_view_debug[5] obstruct_audio2  			 
//	VIEW_INTEGER_VARIABLE ZERO4_view_debug[6] play_first_time_blocked_audio
//	VIEW_INTEGER_VARIABLE ZERO4_view_debug[7] bomb_blip						
ENDIF
			
		
		


// Quit level - no mission pass/fail - cleanup only 
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC 
	m_quit = 1
ENDIF
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	GOSUB z4_mission_pass_audio
	m_passed = 1
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
	GOTO end_of_main_loop_ZERO4
ENDIF
		
// end of debug tools **************		
									
// Frame counter
m_frame_num++
IF m_frame_num > 9
	m_frame_num = 0
ENDIF

// Additional Timers
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time

dialogue_timer 	+= m_time_diff
help_timer	   	+= m_time_diff
rc_bomb_timer   += m_time_diff
pheli_pickup_timer += m_time_diff
otank_timer[0] += m_time_diff
otank_timer[1] += m_time_diff
otank_timer[2] += m_time_diff
pcar_timer	+= m_time_diff
obstruction_timer += m_time_diff
invincible_timer+= m_time_diff




// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
	IF m_stage = 0

		// fake creates
		IF m_goals = -1
			CREATE_CAR PONY 0.0 0.0 0.0 pcar
			CREATE_CAR PONY 0.0 0.0 0.0 hidden_car
			CREATE_CAR PONY 0.0 0.0 0.0 pheli
			CREATE_CAR PONY 0.0 0.0 0.0 winch_car
			CREATE_OBJECT BARREL2 0.0 0.0 0.0 rock[0]
			CREATE_OBJECT BARREL2 0.0 0.0 0.0 plank[0]
			CREATE_OBJECT BARREL2 0.0 0.0 0.0 rc_bomb
			CREATE_OBJECT BARREL2 0.0 0.0 0.0 carried_object
			CREATE_OBJECT BARREL2 0.0 0.0 0.0 winch_object
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 winch_ped
			CREATE_CAR PONY 0.0 0.0 0.0 carried_car
			CREATE_CAR PONY 0.0 0.0 0.0 pheli_old
		ENDIF
		
		// cars
		LVAR_INT pcar
		LVAR_INT hidden_car
		LVAR_INT pheli
		LVAR_INT oheli
		LVAR_INT otank[3]
		LVAR_INT winch_car
		LVAR_INT carried_car

		// objects
		LVAR_INT rock[10]
		LVAR_INT plank[5]
		LVAR_INT rc_bomb
		LVAR_INT winch_object
		LVAR_INT pmagnet
		LVAR_INT omagnet

		// ped
		LVAR_INT winch_ped
		
		// blips
		LVAR_INT pcar_blip
		LVAR_INT oheli_blip
		LVAR_INT otank_blip[3]

		// timers
		LVAR_INT rc_bomb_timer
		LVAR_INT pheli_pickup_timer
		LVAR_INT otank_timer[3]
		LVAR_INT pcar_timer
		VAR_INT zero4_timer
		LVAR_INT obstruction_timer
		LVAR_INT invincible_timer

		// flags
		LVAR_INT pcar_progress
		LVAR_INT pcar_lives_left
		LVAR_INT first_bridge
		LVAR_INT second_bridge
		LVAR_INT bride
		LVAR_INT first_bridge_laid 
		LVAR_INT second_bridge_laid
		LVAR_INT first_bridge_plank
		LVAR_INT second_bridge_plank
		LVAR_INT pcar_is_paused
		LVAR_INT pcar_is_obstructed
		LVAR_INT bomb_is_armed
		LVAR_INT heli_is_carrying
		LVAR_INT circle_is_pressed
		LVAR_INT oheli_flag
		LVAR_INT oheli_rock
		LVAR_INT oheli_goto
		LVAR_INT pheli_lives
		LVAR_INT otank_flag[3]
		LVAR_INT otank_goto[3]
		LVAR_INT respawned_tanks
		VAR_INT  z4_pcar_health
		LVAR_INT last_health
		LVAR_INT bandit_taking_damage
		LVAR_INT pcar_halted
		LVAR_INT pcar_R3_pressed
		LVAR_INT otank_target[3]
		LVAR_INT carried_object
		LVAR_INT help_text_flag[12]
		LVAR_INT get_plank1
		LVAR_INT get_plank2
		LVAR_INT obstruction_type
		LVAR_INT obstruction_count
		LVAR_INT first_pickup_help
		LVAR_INT first_time_stuck_at_river
		LVAR_INT plank_blip
		LVAR_INT stuck_at_river_dialogue
		LVAR_INT obstruct_audio1 
		LVAR_INT obstruct_audio2  
		LVAR_INT play_first_time_blocked_audio
		LVAR_INT bomb_blip
		LVAR_INT first_time_taking_damage_from_tank
		LVAR_INT bomb_tank_dialogue		
		LVAR_INT dead_dialogue
		LVAR_INT nearly_dead_dialogue		
		LVAR_INT stuck_at_river
		LVAR_INT tanks_are_all_dead

		// set flags
		pcar_progress			= 0
		pcar_lives_left 		= 0
		first_bridge			= 0
		second_bridge			= 0
		first_bridge_laid 		= 0
		second_bridge_laid		= 0
		first_bridge_plank		= -1 
		second_bridge_plank		= -1 
		pcar_is_paused			= 0
		pcar_is_obstructed		= 0 
		bomb_is_armed			= 0
		heli_is_carrying		= 0
		circle_is_pressed		= 0
		oheli_flag				= 0 
		oheli_rock				= 0
		oheli_goto				= 0
		pheli_lives				= 0
		otank_flag[0]			= 99
		otank_flag[1]			= 99
		otank_flag[2]			= 99
		otank_goto[0]			= 0					
		otank_goto[1]			= 0
		otank_goto[2]			= 0
		respawned_tanks			= 0
		z4_pcar_health			= 0
		last_health				= 0
		bandit_taking_damage	= 0
		pcar_halted				= 0
		pcar_R3_pressed			= 0
		otank_target[0]			= 0
		otank_target[1]			= 0
		otank_target[2]			= 0
		carried_object			= 0
		get_plank1				= 0
		get_plank2				= 0
		temp_int = 0
		WHILE temp_int < 12
			help_text_flag[temp_int] = 0
		temp_int++
		ENDWHILE
		obstruction_type	 	= 0
		obstruction_count		= 0
		carried_car				= 0
		first_pickup_help 		= 0
		first_time_stuck_at_river = 0
		plank_blip				  = 0
		stuck_at_river_dialogue	  = 0
		obstruct_audio1 			   = 0	
		obstruct_audio2  			   = 0
		play_first_time_blocked_audio  = 0
		bomb_blip						   = 0
		first_time_taking_damage_from_tank = 0	
		bomb_tank_dialogue				   = 0
		dead_dialogue			= 0
		nearly_dead_dialogue	= 0
		stuck_at_river			= 0
		control_help_flag 		= 0
		tanks_are_all_dead		= 0

		// route respawned tanks goto
		LVAR_FLOAT otank_route1_x[2] otank_route1_y[2] otank_route1_z[2]
		otank_route1_x[0] =	-1115.1217	   
		otank_route1_y[0] =	1029.2604 	   
		otank_route1_z[0] =	1342.4828 
		otank_route1_x[1] =	-1106.9159 
		otank_route1_y[1] =	1056.4030 
		otank_route1_z[1] =	1342.0526
		LVAR_FLOAT otank_route2_x[2] otank_route2_y[2] otank_route2_z[2]
		otank_route2_x[0] =	-1115.1217
		otank_route2_y[0] =	1029.2604 
		otank_route2_z[0] =	1342.4828 
		otank_route2_x[1] =	-1098.9312 
		otank_route2_y[1] =	1070.1931 
		otank_route2_z[1] =	1341.4669
		LVAR_FLOAT otank_route3_x[2] otank_route3_y[2] otank_route3_z[2]
		otank_route3_x[0] =	-1115.1217
		otank_route3_y[0] =	1029.2604 
		otank_route3_z[0] =	342.4828 
		otank_route3_x[1] =	-1098.2572 
		otank_route3_y[1] =	1063.3679 
		otank_route3_z[1] =	1341.7911


		// coordinates
		LVAR_FLOAT pcar_path_x[117]
		LVAR_FLOAT pcar_path_y[117]					
		LVAR_FLOAT pcar_path_z[117]

		// set coordinates
		PCAR_PATH_X[0] = -975.6996 
		PCAR_PATH_Y[0] = 1061.1622 
		PCAR_PATH_Z[0] = 1344.7998 
		PCAR_PATH_X[1] = -978.6938 
		PCAR_PATH_Y[1] = 1061.1626 
		PCAR_PATH_Z[1] = 1344.5541 
		PCAR_PATH_X[2] = -981.6267 
		PCAR_PATH_Y[2] = 1061.5938 
		PCAR_PATH_Z[2] = 1343.6774 
		PCAR_PATH_X[3] = -984.4323 
		PCAR_PATH_Y[3] = 1062.1938 
		PCAR_PATH_Z[3] = 1342.6601 
		PCAR_PATH_X[4] = -987.3215 
		PCAR_PATH_Y[4] = 1063.0416 
		PCAR_PATH_Z[4] = 1341.9409 
		PCAR_PATH_X[5] = -990.1523 
		PCAR_PATH_Y[5] = 1064.2087 
		PCAR_PATH_Z[5] = 1341.8469 
		PCAR_PATH_X[6] = -992.0640 
		PCAR_PATH_Y[6] = 1066.6281 
		PCAR_PATH_Z[6] = 1341.6920 
		PCAR_PATH_X[7] = -994.1041 
		PCAR_PATH_Y[7] = 1068.8895 
		PCAR_PATH_Z[7] = 1341.4738 
		PCAR_PATH_X[8] = -996.1589 
		PCAR_PATH_Y[8] = 1071.1047 
		PCAR_PATH_Z[8] = 1341.2610 
		PCAR_PATH_X[9] = -998.0826 
		PCAR_PATH_Y[9] = 1073.4133 
		PCAR_PATH_Z[9] = 1341.2155 
		PCAR_PATH_X[10] = -1000.1383 
		PCAR_PATH_Y[10] = 1075.6621 
		PCAR_PATH_Z[10] = 1341.3320 
		PCAR_PATH_X[11] = -1002.8577 
		PCAR_PATH_Y[11] = 1076.9185 
		PCAR_PATH_Z[11] = 1341.7087 
		PCAR_PATH_X[12] = -1005.5181 
		PCAR_PATH_Y[12] = 1078.2488 
		PCAR_PATH_Z[12] = 1342.2286 
		PCAR_PATH_X[13] = -1008.5601 
		PCAR_PATH_Y[13] = 1078.8893 
		PCAR_PATH_Z[13] = 1342.3387 
		PCAR_PATH_X[14] = -1011.5899 
		PCAR_PATH_Y[14] = 1079.1106 
		PCAR_PATH_Z[14] = 1342.3463 
		PCAR_PATH_X[15] = -1014.6602 
		PCAR_PATH_Y[15] = 1079.3392 
		PCAR_PATH_Z[15] = 1342.3180 
		PCAR_PATH_X[16] = -1017.6959 
		PCAR_PATH_Y[16] = 1079.6743 
		PCAR_PATH_Z[16] = 1342.4577 
		PCAR_PATH_X[17] = -1020.6663 
		PCAR_PATH_Y[17] = 1080.4332 
		PCAR_PATH_Z[17] = 1342.7176 
		PCAR_PATH_X[18] = -1023.6085 
		PCAR_PATH_Y[18] = 1081.3638 
		PCAR_PATH_Z[18] = 1342.5897 
		PCAR_PATH_X[19] = -1026.4972 
		PCAR_PATH_Y[19] = 1082.2883 
		PCAR_PATH_Z[19] = 1342.4518 
		PCAR_PATH_X[20] = -1029.5338 
		PCAR_PATH_Y[20] = 1082.4457 
		PCAR_PATH_Z[20] = 1342.3143 
		PCAR_PATH_X[21] = -1031.7272 
		PCAR_PATH_Y[21] = 1080.3801 
		PCAR_PATH_Z[21] = 1342.1692 
		PCAR_PATH_X[22] = -1031.4497 
		PCAR_PATH_Y[22] = 1077.3828 
		PCAR_PATH_Z[22] = 1342.2589 
		PCAR_PATH_X[23] = -1030.5564 
		PCAR_PATH_Y[23] = 1074.5190 
		PCAR_PATH_Z[23] = 1342.3673 
		PCAR_PATH_X[24] = -1028.4760 
		PCAR_PATH_Y[24] = 1072.2759 
		PCAR_PATH_Z[24] = 1342.5469 
		PCAR_PATH_X[25] = -1026.1198 
		PCAR_PATH_Y[25] = 1070.4523 
		PCAR_PATH_Z[25] = 1342.9336 
		PCAR_PATH_X[26] = -1023.3447 
		PCAR_PATH_Y[26] = 1069.2969 
		PCAR_PATH_Z[26] = 1342.8616 
		PCAR_PATH_X[27] = -1020.9244 
		PCAR_PATH_Y[27] = 1067.4546 
		PCAR_PATH_Z[27] = 1342.6794 
		PCAR_PATH_X[28] = -1019.8083 
		PCAR_PATH_Y[28] = 1064.6359 
		PCAR_PATH_Z[28] = 1342.3670 
		PCAR_PATH_X[29] = -1019.4981 
		PCAR_PATH_Y[29] = 1061.6036 
		PCAR_PATH_Z[29] = 1342.1503 
		PCAR_PATH_X[30] = -1020.3516 
		PCAR_PATH_Y[30] = 1058.7068 
		PCAR_PATH_Z[30] = 1342.0027 
		PCAR_PATH_X[31] = -1022.2053 
		PCAR_PATH_Y[31] = 1056.2479 
		PCAR_PATH_Z[31] = 1341.9458 
		PCAR_PATH_X[32] = -1024.6542 
		PCAR_PATH_Y[32] = 1054.3960 
		PCAR_PATH_Z[32] = 1342.0289 
		PCAR_PATH_X[33] = -1026.9489 
		PCAR_PATH_Y[33] = 1052.4628 
		PCAR_PATH_Z[33] = 1341.6717 
		PCAR_PATH_X[34] = -1029.5352 
		PCAR_PATH_Y[34] = 1050.9619 
		PCAR_PATH_Z[34] = 1341.4208 
		PCAR_PATH_X[35] = -1032.2040 
		PCAR_PATH_Y[35] = 1049.5392 
		PCAR_PATH_Z[35] = 1341.3980 
		PCAR_PATH_X[36] = -1035.1458 
		PCAR_PATH_Y[36] = 1048.8221 
		PCAR_PATH_Z[36] = 1341.1673 
		PCAR_PATH_X[37] = -1038.1306 
		PCAR_PATH_Y[37] = 1048.2689 
		PCAR_PATH_Z[37] = 1340.8365 
		PCAR_PATH_X[38] = -1040.2900 
		PCAR_PATH_Y[38] = 1046.1638 
		PCAR_PATH_Z[38] = 1340.9749 
		PCAR_PATH_X[39] = -1042.1487 
		PCAR_PATH_Y[39] = 1043.7614 
		PCAR_PATH_Z[39] = 1341.2780 
		PCAR_PATH_X[40] = -1043.8267 
		PCAR_PATH_Y[40] = 1041.2518 
		PCAR_PATH_Z[40] = 1341.3982 
		PCAR_PATH_X[41] = -1045.2853 
		PCAR_PATH_Y[41] = 1038.5728 
		PCAR_PATH_Z[41] = 1341.5625 
		PCAR_PATH_X[42] = -1047.2416 
		PCAR_PATH_Y[42] = 1036.2831 
		PCAR_PATH_Z[42] = 1341.9514 
		PCAR_PATH_X[43] = -1049.5583 
		PCAR_PATH_Y[43] = 1034.3478 
		PCAR_PATH_Z[43] = 1342.0216 
		PCAR_PATH_X[44] = -1052.5813 
		PCAR_PATH_Y[44] = 1034.2957 
		PCAR_PATH_Z[44] = 1342.2183 
		PCAR_PATH_X[45] = -1054.6237 
		PCAR_PATH_Y[45] = 1036.5505 
		PCAR_PATH_Z[45] = 1342.3238 
		PCAR_PATH_X[46] = -1055.7263 
		PCAR_PATH_Y[46] = 1039.3378 
		PCAR_PATH_Z[46] = 1342.6547 
		PCAR_PATH_X[47] = -1056.5907 
		PCAR_PATH_Y[47] = 1042.2220 
		PCAR_PATH_Z[47] = 1343.0162 
		PCAR_PATH_X[48] = -1057.6398 
		PCAR_PATH_Y[48] = 1045.1260 
		PCAR_PATH_Z[48] = 1343.0706 
		PCAR_PATH_X[49] = -1058.8668 
		PCAR_PATH_Y[49] = 1047.8827 
		PCAR_PATH_Z[49] = 1343.2779 
		PCAR_PATH_X[50] = -1059.9664 
		PCAR_PATH_Y[50] = 1050.7065 
		PCAR_PATH_Z[50] = 1343.3351 
		PCAR_PATH_X[51] = -1062.2821 
		PCAR_PATH_Y[51] = 1052.6555 
		PCAR_PATH_Z[51] = 1342.9733 
		PCAR_PATH_X[52] = -1065.3123 
		PCAR_PATH_Y[52] = 1052.2570 
		PCAR_PATH_Z[52] = 1342.9871 
		PCAR_PATH_X[53] = -1067.4132 
		PCAR_PATH_Y[53] = 1050.1033 
		PCAR_PATH_Z[53] = 1343.3376 
		PCAR_PATH_X[54] = -1069.6801 
		PCAR_PATH_Y[54] = 1048.0607 
		PCAR_PATH_Z[54] = 1343.3031 
		PCAR_PATH_X[55] = -1072.3973 
		PCAR_PATH_Y[55] = 1046.4901 
		PCAR_PATH_Z[55] = 1343.2991 
		PCAR_PATH_X[56] = -1074.8439 
		PCAR_PATH_Y[56] = 1044.6208 
		PCAR_PATH_Z[56] = 1343.2778 
		PCAR_PATH_X[57] = -1077.2791 
		PCAR_PATH_Y[57] = 1042.8630 
		PCAR_PATH_Z[57] = 1343.3094 
		PCAR_PATH_X[58] = -1080.0896 
		PCAR_PATH_Y[58] = 1041.3958 
		PCAR_PATH_Z[58] = 1343.3120 
		PCAR_PATH_X[59] = -1082.9011 
		PCAR_PATH_Y[59] = 1039.9681 
		PCAR_PATH_Z[59] = 1343.1588 
		PCAR_PATH_X[60] = -1085.7234 
		PCAR_PATH_Y[60] = 1038.9727 
		PCAR_PATH_Z[60] = 1342.7474 
		PCAR_PATH_X[61] = -1088.6931 
		PCAR_PATH_Y[61] = 1038.2444 
		PCAR_PATH_Z[61] = 1342.4909 
		PCAR_PATH_X[62] = -1091.7440 
		PCAR_PATH_Y[62] = 1038.0497 
		PCAR_PATH_Z[62] = 1342.3886 
		PCAR_PATH_X[63] = -1094.6344 
		PCAR_PATH_Y[63] = 1038.8562 
		PCAR_PATH_Z[63] = 1342.2133 
		PCAR_PATH_X[64] = -1096.4684 
		PCAR_PATH_Y[64] = 1041.2861 
		PCAR_PATH_Z[64] = 1342.3533 
		PCAR_PATH_X[65] = -1095.8972 
		PCAR_PATH_Y[65] = 1044.2391 
		PCAR_PATH_Z[65] = 1342.3862 
		PCAR_PATH_X[66] = -1094.4073 
		PCAR_PATH_Y[66] = 1046.8625 
		PCAR_PATH_Z[66] = 1342.3028 
		PCAR_PATH_X[67] = -1092.6156 
		PCAR_PATH_Y[67] = 1049.3525 
		PCAR_PATH_Z[67] = 1342.1440 
		PCAR_PATH_X[68] = -1090.7954 
		PCAR_PATH_Y[68] = 1051.8524 
		PCAR_PATH_Z[68] = 1342.0356 
		PCAR_PATH_X[69] = -1089.0366 
		PCAR_PATH_Y[69] = 1054.2889 
		PCAR_PATH_Z[69] = 1341.9242 
		PCAR_PATH_X[70] = -1087.1306 
		PCAR_PATH_Y[70] = 1056.6799 
		PCAR_PATH_Z[70] = 1341.9632 
		PCAR_PATH_X[71] = -1084.6295 
		PCAR_PATH_Y[71] = 1058.4758 
		PCAR_PATH_Z[71] = 1342.0459 
		PCAR_PATH_X[72] = -1082.0255 
		PCAR_PATH_Y[72] = 1060.0588 
		PCAR_PATH_Z[72] = 1342.0702 
		PCAR_PATH_X[73] = -1079.6796 
		PCAR_PATH_Y[73] = 1061.9845 
		PCAR_PATH_Z[73] = 1342.1606 
		PCAR_PATH_X[74] = -1077.3823 
		PCAR_PATH_Y[74] = 1064.0586 
		PCAR_PATH_Z[74] = 1342.0536 
		PCAR_PATH_X[75] = -1075.0227 
		PCAR_PATH_Y[75] = 1066.0609 
		PCAR_PATH_Z[75] = 1341.9560 
		PCAR_PATH_X[76] = -1072.5444 
		PCAR_PATH_Y[76] = 1067.7711 
		PCAR_PATH_Z[76] = 1341.6756 
		PCAR_PATH_X[77] = -1069.9336 
		PCAR_PATH_Y[77] = 1069.3997 
		PCAR_PATH_Z[77] = 1341.4638 
		PCAR_PATH_X[78] = -1067.3918 
		PCAR_PATH_Y[78] = 1071.0668 
		PCAR_PATH_Z[78] = 1340.9401 
		PCAR_PATH_X[79] = -1065.1719 
		PCAR_PATH_Y[79] = 1073.2172 
		PCAR_PATH_Z[79] = 1340.9801 
		PCAR_PATH_X[80] = -1063.0837 
		PCAR_PATH_Y[80] = 1075.4364 
		PCAR_PATH_Z[80] = 1341.2653 
		PCAR_PATH_X[81] = -1061.7411 
		PCAR_PATH_Y[81] = 1078.1583 
		PCAR_PATH_Z[81] = 1341.5075 
		PCAR_PATH_X[82] = -1060.9280 
		PCAR_PATH_Y[82] = 1081.1110 
		PCAR_PATH_Z[82] = 1341.7047 
		PCAR_PATH_X[83] = -1059.8513 
		PCAR_PATH_Y[83] = 1083.9655 
		PCAR_PATH_Z[83] = 1341.7174 
		PCAR_PATH_X[84] = -1059.0964 
		PCAR_PATH_Y[84] = 1086.9288 
		PCAR_PATH_Z[84] = 1341.7729 
		PCAR_PATH_X[85] = -1059.4644 
		PCAR_PATH_Y[85] = 1089.9081 
		PCAR_PATH_Z[85] = 1341.8876 
		PCAR_PATH_X[86] = -1061.6564 
		PCAR_PATH_Y[86] = 1092.0059 
		PCAR_PATH_Z[86] = 1342.1178 
		PCAR_PATH_X[87] = -1064.4136 
		PCAR_PATH_Y[87] = 1093.1716 
		PCAR_PATH_Z[87] = 1342.3784 
		PCAR_PATH_X[88] = -1067.3962 
		PCAR_PATH_Y[88] = 1093.7235 
		PCAR_PATH_Z[88] = 1342.2547 
		PCAR_PATH_X[89] = -1070.3717 
		PCAR_PATH_Y[89] = 1094.4225 
		PCAR_PATH_Z[89] = 1342.1755 
		PCAR_PATH_X[90] = -1073.3851 
		PCAR_PATH_Y[90] = 1094.0660 
		PCAR_PATH_Z[90] = 1342.0389 
		PCAR_PATH_X[91] = -1075.8994 
		PCAR_PATH_Y[91] = 1092.2640 
		PCAR_PATH_Z[91] = 1342.0265 
		PCAR_PATH_X[92] = -1078.3301 
		PCAR_PATH_Y[92] = 1090.4756 
		PCAR_PATH_Z[92] = 1341.8834 
		PCAR_PATH_X[93] = -1080.6924 
		PCAR_PATH_Y[93] = 1088.5142 
		PCAR_PATH_Z[93] = 1341.7618 
		PCAR_PATH_X[94] = -1082.7858 
		PCAR_PATH_Y[94] = 1086.3169 
		PCAR_PATH_Z[94] = 1341.7253 
		PCAR_PATH_X[95] = -1084.7147 
		PCAR_PATH_Y[95] = 1083.9258 
		PCAR_PATH_Z[95] = 1341.7179 
		PCAR_PATH_X[96] = -1086.7113 
		PCAR_PATH_Y[96] = 1081.6781 
		PCAR_PATH_Z[96] = 1341.6567 
		PCAR_PATH_X[97] = -1088.7390 
		PCAR_PATH_Y[97] = 1079.4583 
		PCAR_PATH_Z[97] = 1341.7389 
		PCAR_PATH_X[98] = -1090.5991 
		PCAR_PATH_Y[98] = 1077.0739 
		PCAR_PATH_Z[98] = 1341.7372 
		PCAR_PATH_X[99] = -1091.9250 
		PCAR_PATH_Y[99] = 1074.3112 
		PCAR_PATH_Z[99] = 1341.7544 
		PCAR_PATH_X[100] = -1093.0734 
		PCAR_PATH_Y[100] = 1071.4653 
		PCAR_PATH_Z[100] = 1341.7513 
		PCAR_PATH_X[101] = -1094.3168 
		PCAR_PATH_Y[101] = 1068.6794 
		PCAR_PATH_Z[101] = 1341.7525 
		PCAR_PATH_X[102] = -1095.8586 
		PCAR_PATH_Y[102] = 1066.0729 
		PCAR_PATH_Z[102] = 1341.7524 
		PCAR_PATH_X[103] = -1097.9113 
		PCAR_PATH_Y[103] = 1063.8552 
		PCAR_PATH_Z[103] = 1341.7389 
		PCAR_PATH_X[104] = -1100.7314 
		PCAR_PATH_Y[104] = 1062.7695 
		PCAR_PATH_Z[104] = 1341.7860 
		PCAR_PATH_X[105] = -1103.8469 
		PCAR_PATH_Y[105] = 1062.7810 
		PCAR_PATH_Z[105] = 1341.7899 
		PCAR_PATH_X[106] = -1106.7717 
		PCAR_PATH_Y[106] = 1061.8954 
		PCAR_PATH_Z[106] = 1341.7898 
		PCAR_PATH_X[107] = -1109.5758 
		PCAR_PATH_Y[107] = 1060.6584 
		PCAR_PATH_Z[107] = 1341.8246 
		PCAR_PATH_X[108] = -1112.5780 
		PCAR_PATH_Y[108] = 1060.8479 
		PCAR_PATH_Z[108] = 1341.8997 

		PCAR_PATH_X[109] = -1116.12 
		PCAR_PATH_Y[109] = 1059.64
		PCAR_PATH_Z[109] = 1342.25
		PCAR_PATH_X[110] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[110] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[110] = PCAR_PATH_Z[109]
		PCAR_PATH_X[111] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[111] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[111] = PCAR_PATH_Z[109]
		PCAR_PATH_X[112] = PCAR_PATH_X[109]
		PCAR_PATH_Y[112] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[112] = PCAR_PATH_Z[109]
		PCAR_PATH_X[113] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[113] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[113] = PCAR_PATH_Z[109]
		PCAR_PATH_X[114] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[114] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[114] = PCAR_PATH_Z[109]
		PCAR_PATH_X[115] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[115] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[115] = PCAR_PATH_Z[109]
		PCAR_PATH_X[116] = PCAR_PATH_X[109] 
		PCAR_PATH_Y[116] = PCAR_PATH_Y[109]
		PCAR_PATH_Z[116] = PCAR_PATH_Z[109]
		 
//		PCAR_PATH_X[113] = -1127.0338 
//		PCAR_PATH_Y[113] = 1058.0831 
//		PCAR_PATH_Z[113] = 1345.3900 
//		PCAR_PATH_X[114] = -1119.2037 
//		PCAR_PATH_Y[114] = 1057.8279 
//		PCAR_PATH_Z[114] = 1345.4827 
//		PCAR_PATH_X[115] = -1108.1910 
//		PCAR_PATH_Y[115] = 1058.0837 
//		PCAR_PATH_Z[115] = 1345.4279 
//		PCAR_PATH_X[116] = -1033.4384 
//		PCAR_PATH_Y[116] = 1059.8207 
//		PCAR_PATH_Z[116] = 1345.0559 

		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

		GOSUB ZERO4_next_stage
	ENDIF

// *************************************************************************************************************
//											STAGE 1 - intro cutscene  
// *************************************************************************************************************
	IF m_stage = 1
		
		// initialisation for stage
		IF m_goals = 0
			
			LOAD_MISSION_TEXT ZERO4

			SET_AREA_VISIBLE 6
			LOAD_CUTSCENE ZERO_4 
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

			SET_AREA_VISIBLE 0

			SET_PLAYER_CONTROL player1 OFF
			//SET_PLAYER_IS_IN_STADIUM TRUE
			
			HIDE_ALL_FRONTEND_BLIPS	TRUE
			SET_PLAYER_IN_CAR_CAMERA_MODE   ZOOM_TWO

			// weather and time
			STORE_CLOCK
			FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_SF
			CLEAR_WANTED_LEVEL player1
			SET_WANTED_MULTIPLIER 0.1

			REQUEST_MODEL RCBANDIT
			REQUEST_MODEL RCGOBLIN
			REQUEST_MODEL BMX
			REQUEST_MODEL RCTIGER
			LOAD_ALL_MODELS_NOW
			WHILE NOT HAS_MODEL_LOADED RCBANDIT
			   OR NOT HAS_MODEL_LOADED RCGOBLIN
			   OR NOT HAS_MODEL_LOADED BMX
			   OR NOT HAS_MODEL_LOADED RCTIGER
				WAIT 0
			ENDWHILE

			SET_CHAR_AREA_VISIBLE scplayer 10

			CLEAR_AREA -1004.0972 1000.7886 1349.7488 300.0 TRUE

			// sort player out - put him in car and make them both invisible with no collision
			CREATE_CAR BMX -1004.0972 1000.7886 1349.7488 hidden_car
			FREEZE_CAR_POSITION hidden_car TRUE
			WARP_CHAR_INTO_CAR scplayer hidden_car
			LOCK_CAR_DOORS hidden_car CARLOCK_LOCKED_PLAYER_INSIDE
			SET_VEHICLE_AREA_VISIBLE hidden_car 10

			// create rc car
			CREATE_CAR RCBANDIT -975.6997 1061.1624 1344.7998 pcar
			SET_CAR_HEADING pcar 90.0
			SET_VEHICLE_AREA_VISIBLE pcar 10
			SET_CAR_PROOFS pcar FALSE TRUE TRUE FALSE FALSE
			SET_CAR_HEALTH pcar 2000

			// create rc heli
			CREATE_CAR RCGOBLIN -973.7590 1077.1519 1350.3988 pheli
			SET_CAR_HEADING pheli 90.0
			SET_VEHICLE_AREA_VISIBLE pheli 10
			//SET_CAR_PROOFS pheli TRUE TRUE TRUE TRUE TRUE // take out when camera is fixed
			//ATTACH_WINCH_TO_HELI pheli WINCHTYPE_RCHELI
			CREATE_OBJECT MINI_MAGNET -973.7590 1077.1519 1344.3988 pmagnet
			ATTACH_OBJECT_TO_CAR pmagnet pheli 0.0 0.0 -0.2 0.0 0.0 0.0
			SET_OBJECT_COLLISION pmagnet TRUE
			FREEZE_CAR_POSITION pheli TRUE
			SET_CAR_COORDINATES pheli -973.7590 1077.1519 1346.3988
			SET_HELI_BLADES_FULL_SPEED pheli
			HELI_GOTO_COORDS pheli -973.7590 1077.1519 1344.3988 344.0 3.0

			// create opposition heli
			CREATE_CAR RCGOBLIN -1131.6940 1041.7906 1350.1413 oheli
			SET_CAR_HEADING oheli 270.0
			SET_VEHICLE_AREA_VISIBLE oheli 10
			ADD_BLIP_FOR_CAR oheli oheli_blip
			SET_BLIP_AS_FRIENDLY oheli_blip FALSE
			CHANGE_BLIP_DISPLAY oheli_blip BLIP_ONLY
			CHANGE_BLIP_SCALE oheli_blip 2
			//ATTACH_WINCH_TO_HELI oheli WINCHTYPE_RCHELI
			CREATE_OBJECT MINI_MAGNET -973.7590 1077.1519 1344.3988 omagnet
			ATTACH_OBJECT_TO_CAR omagnet oheli 0.0 0.0 -0.2 0.0 0.0 0.0
			SET_OBJECT_COLLISION omagnet TRUE
			//FREEZE_CAR_POSITION oheli TRUE
			SET_HELI_BLADES_FULL_SPEED oheli
			HELI_GOTO_COORDS oheli -1131.6940 1041.7906 1344.1413 344.0 3.0

			// create initial rocks
			CREATE_OBJECT_NO_OFFSET BARREL2 -1007.2133 1078.5345 1342.6824 rock[0]
			CREATE_OBJECT_NO_OFFSET BARREL2 -1093.1370 1037.8564 1342.7319 rock[1] 
			CREATE_OBJECT_NO_OFFSET BARREL2 -1073.4656 1094.4071 1342.3218 rock[2]
			SET_OBJECT_ROTATION rock[0] 0.0 90.0 0.0
			SET_OBJECT_ROTATION rock[1] 0.0 90.0 0.0
			SET_OBJECT_ROTATION rock[2] 0.0 90.0 0.0

			// create initial planks
			CREATE_OBJECT_NO_OFFSET KMB_PLANK -973.0 1064.0 1344.1472 plank[0]
			CREATE_OBJECT_NO_OFFSET KMB_PLANK -973.0 1065.0 1344.1461 plank[1]
			CREATE_OBJECT_NO_OFFSET KMB_PLANK -973.0 1066.0 1344.1442 plank[2]
			CREATE_OBJECT_NO_OFFSET KMB_PLANK -973.0 1067.0 1344.1431 plank[3]
			CREATE_OBJECT_NO_OFFSET KMB_PLANK -973.0 1068.0 1344.1421 plank[4]
			SET_OBJECT_HEADING plank[0] 270.0
			SET_OBJECT_HEADING plank[1] 270.0
			SET_OBJECT_HEADING plank[2] 270.0
			SET_OBJECT_HEADING plank[3] 270.0
			SET_OBJECT_HEADING plank[4] 270.0
	 
			// create initial bomb
			CREATE_OBJECT_NO_OFFSET RCBOMB -974.5125 1072.0446 1344.2984 rc_bomb
			SET_OBJECT_HEADING rc_bomb 90.0

			// create initial tanks
			CREATE_CAR RCTIGER -1076.8729 1046.5424 1343.3653 otank[0]
			CREATE_CAR RCTIGER -1073.1935 1070.4257 1341.3746 otank[1]
			CREATE_CAR RCTIGER -1106.4786 1060.9753 1341.8638 otank[2]
			SET_CAR_HEADING otank[0] 286.4071 
			SET_CAR_HEADING otank[1] 145.4881 
			SET_CAR_HEADING otank[2] 278.5302
			ADD_BLIP_FOR_CAR otank[0] otank_blip[0]
			ADD_BLIP_FOR_CAR otank[1] otank_blip[1]
			ADD_BLIP_FOR_CAR otank[2] otank_blip[2]
			CHANGE_BLIP_DISPLAY otank_blip[0] BLIP_ONLY
			CHANGE_BLIP_DISPLAY otank_blip[1] BLIP_ONLY
			CHANGE_BLIP_DISPLAY otank_blip[2] BLIP_ONLY
			SET_VEHICLE_AREA_VISIBLE otank[0] 10
			SET_VEHICLE_AREA_VISIBLE otank[1] 10
			SET_VEHICLE_AREA_VISIBLE otank[2] 10
			CHANGE_BLIP_SCALE otank_blip[0] 2
			CHANGE_BLIP_SCALE otank_blip[1] 2
			CHANGE_BLIP_SCALE otank_blip[2] 2

			// environment settings
			SET_AREA_VISIBLE 10
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0
			ENABLE_CRANE_CONTROLS FALSE FALSE FALSE
			DISPLAY_HUD FALSE
			DISPLAY_RADAR TRUE
			SET_RADAR_ZOOM 100
					   
			LOAD_SCENE -975.6997 1061.1624 344.7998
			
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -987.9526 1099.3027 1352.4498 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -987.5282 1098.5186 1351.9970 JUMP_CUT

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			//PRINT_NOW Z4_M01 4000 1 // This is the RC battlefield.

			TIMERA = 0
			TIMERB = 0

			dialogue_flag = 0
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			audio_line_is_active = 0

			
		m_goals++
		ENDIF


		// play audio
		IF m_goals > 0
			IF TIMERB > 1000
				IF audio_line_is_active = 0
					SWITCH dialogue_flag
						CASE 0
							$audio_string    = &ZER4_AA		// Behold, No Man’s Land!
							audio_sound_file = SOUND_ZER4_AA
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_ZER4_AB
						BREAK
						CASE 1
							$audio_string    = &ZER4_AB		// Hell, you guys take this shit seriously!
							audio_sound_file = SOUND_ZER4_AB
							START_NEW_SCRIPT audio_line -1 0 1 2 1
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_ZER4_AC
						BREAK
						CASE 2
							$audio_string    = &ZER4_AC		// Berkley’s HQ is across No Man’s Land.
							audio_sound_file = SOUND_ZER4_AC
							START_NEW_SCRIPT audio_line -1 0 1 1 1
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_ZER4_AD
						BREAK
						CASE 3
							$audio_string    = &ZER4_AD		// I’ll drive the Bandit, you fly the goblin and help anyway you can!							audio_sound_file = SOUND_ZER4_AC
							audio_sound_file = SOUND_ZER4_AD
							START_NEW_SCRIPT audio_line -1 0 1 2 1
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_ZER4_AE
						BREAK
						CASE 4
							$audio_string    = &ZER4_AE		// If I get the Bandit into Berkley’s base, he must leave San Fierro for good!
							audio_sound_file = SOUND_ZER4_AE
							START_NEW_SCRIPT audio_line -1 0 1 1 1
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_ZER4_AF
						BREAK
						CASE 5
							$audio_string    = &ZER4_AF		// LET BATTLE COMMENCE!
							audio_sound_file = SOUND_ZER4_AF
							START_NEW_SCRIPT audio_line -1 0 1 2 1

						BREAK
					ENDSWITCH
					dialogue_flag++
					TIMERB = 0
				ENDIF
			ENDIF		
		ENDIF



		IF m_goals = 1
			IF TIMERA > 4000
				SET_INTERPOLATION_PARAMETERS 0.0 7000
				SET_FIXED_CAMERA_POSITION -982.6354 1055.8798 1348.9258 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -981.9190 1056.5037 1348.6135 JUMP_CUT
				//PRINT_NOW Z4_M02 6000 1 // To defeat Melvin, use your RC helis to safely escort the RC Bandit.
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
		IF m_goals = 2	  
			IF TIMERA > 2000
				//PRINT_NOW Z4_M03 8000 1 // Your helis can pickup and drop objects, including bombs.
				SET_INTERPOLATION_PARAMETERS 0.0 10000
				SET_FIXED_CAMERA_POSITION -1137.9375 1058.0325 1355.2568 0.0 0.0 0.0			
				POINT_CAMERA_AT_POINT -1136.9889 1058.0991 1354.9474 INTERPOLATION
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
		IF m_goals = 3
			IF dialogue_flag > 6
			AND audio_line_is_active = 0
				m_goals = 99
			ENDIF
		ENDIF

		IF m_goals > 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				m_goals = 99
			ENDIF
		ENDIF
		
	
		// exit
		IF m_goals = 99
			
			START_NEW_SCRIPT cleanup_audio_lines
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			SET_INTERPOLATION_PARAMETERS 50.0 500

			CLEAR_PRINTS			  
			SET_FIXED_CAMERA_POSITION -968.2972 1077.4132 1346.6857 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -969.2341 1077.3680 1346.3391 JUMP_CUT
			SWITCH_WIDESCREEN OFF
			CLEAR_PRINTS
			SET_PLAYER_CONTROL player1 ON

			IF NOT IS_CAR_DEAD pheli
				FREEZE_CAR_POSITION pheli FALSE
				SET_CAR_COORDINATES pheli -973.7590 1077.1519 1349.3988 
				SET_CAR_HEADING pheli 90.0
			ENDIF
			IF NOT IS_CAR_DEAD oheli
				FREEZE_CAR_POSITION oheli FALSE
			ENDIF
		
			WAIT 0

			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT

			GOSUB ZERO4_next_stage
		ENDIF

	ENDIF 


// *************************************************************************************************************
//											STAGE 2 - minigame - 1st life
// *************************************************************************************************************
	IF m_stage = 2
		
		// initialisation for stage
		IF m_goals = 0
		
			IF NOT IS_CAR_DEAD pcar
				ADD_BLIP_FOR_CAR pcar pcar_blip
				SET_BLIP_AS_FRIENDLY pcar_blip TRUE
				CHANGE_BLIP_SCALE pcar_blip 2
			ENDIF

			IF NOT IS_CAR_DEAD pheli
				TAKE_REMOTE_CONTROL_OF_CAR player1 pheli
			ENDIF

			SET_ENABLE_RC_DETONATE FALSE	
			SET_ENABLE_RC_DETONATE_ON_CONTACT FALSE
			
			REQUEST_CAR_RECORDING 521
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 521
				WAIT 0
			ENDWHILE
			
			IF NOT IS_CAR_DEAD pcar
				START_PLAYBACK_RECORDED_CAR pcar 521
				SET_PLAYBACK_SPEED pcar 0.5
			ENDIF

			// set flags for mini game section
			pcar_progress = 0
			pcar_lives_left = 3
			pheli_lives = 3

			// setup display stuff
			zero4_timer = 480000
			DISPLAY_ONSCREEN_TIMER_WITH_STRING zero4_timer TIMER_DOWN Z4_M11						  
		   	DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING z4_pcar_health COUNTER_DISPLAY_BAR 1 Z4_M06	  

			help_timer = -1000
			dialogue_timer = 10000

		m_goals++
		ENDIF




		// mini game stage
		IF m_goals = 1		

			// make sure there is no oil left lying about (they are electric models, i think)
			REMOVE_OIL_PUDDLES_IN_AREA -963.0789 1006.7465 -1152.8236 1105.1549

			// make sure car recording is loaded
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 521
				REQUEST_CAR_RECORDING 521
			ELSE

				// stop time from progressing
				SET_TIME_OF_DAY 0 30 


				// remove stuff that falls through map ----------------------------------------------------------
				temp_int = 0
				WHILE temp_int < 10
					IF DOES_OBJECT_EXIST rock[temp_int]
						GET_OBJECT_COORDINATES rock[temp_int] x y z
						IF z < 1335.0
							DELETE_OBJECT rock[temp_int]
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE
				temp_int = 0
				WHILE temp_int < 5
					IF DOES_OBJECT_EXIST plank[temp_int]
						GET_OBJECT_COORDINATES plank[temp_int] x y z
						IF z < 1335.0
							SET_OBJECT_COORDINATES plank[temp_int] -973.0 1064.0 1345.1472
							SET_OBJECT_HEADING plank[temp_int] 270.0
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE
				temp_int = 0
				WHILE temp_int < 3
					IF DOES_VEHICLE_EXIST otank[temp_int]
						IF NOT IS_CAR_DEAD otank[temp_int]
							GET_CAR_COORDINATES otank[temp_int] x y z
							IF z < 1335.0
								EXPLODE_CAR otank[temp_int]
							ENDIF
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE
				IF DOES_OBJECT_EXIST rc_bomb
					GET_OBJECT_COORDINATES rc_bomb x y z
					IF z < 1335.0
						DELETE_OBJECT rc_bomb
					ENDIF
				ENDIF


				// -------------------------  HELP TEXT -------------------------------------
			   				


			   				
			   	// --- FIRST TIME HELI HOVERS OVER AN OBJECT --------   
				IF first_pickup_help = 0
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					IF NOT IS_CAR_DEAD pheli
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 0.0 0.0 -1.0 x y z
						temp_int = 0
						WHILE temp_int < 10
							IF DOES_OBJECT_EXIST rock[temp_int]
								IF LOCATE_OBJECT_3D rock[temp_int] x y z 1.0 1.0 1.0 FALSE
									CLEAR_HELP 
									PRINT_HELP Z4_H13
									first_pickup_help++
									temp_int = 10
								ENDIF
							ENDIF
						temp_int++
						ENDWHILE
						IF first_pickup_help = 0 
							temp_int = 0
							WHILE temp_int < 5
								IF DOES_OBJECT_EXIST plank[temp_int]
									IF LOCATE_OBJECT_3D plank[temp_int] x y z 1.0 1.0 1.0 FALSE
										CLEAR_HELP 
										PRINT_HELP Z4_H13
										first_pickup_help++
										temp_int = 5
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
						ENDIF
						IF first_pickup_help = 0
							IF DOES_OBJECT_EXIST rc_bomb
								IF LOCATE_OBJECT_3D rc_bomb x y z 1.0 1.0 1.0 FALSE
									CLEAR_HELP 
									PRINT_HELP Z4_H13
									first_pickup_help++
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF first_pickup_help = 1
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				//AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					IF NOT carried_object = 0
						first_pickup_help++	
						PRINT_HELP Z4_H14
					ENDIF
				ENDIF









				
				// --- INTRO HELP ------------------------------
				IF help_text_flag[0] = 0
				AND help_timer > 1000	
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					PRINT_HELP Z4_H1 // Your RC Goblin helicopter is fitted with a winch which can pick up objects.
					help_timer = 0
					help_text_flag[0]++	
				ENDIF

				IF help_text_flag[0] = 1
				AND help_timer > 1000	
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				AND pcar_progress > 18
					PRINT_HELP Z4_H4 // Melvin's ~r~Goblin~w~ will be constantly try and block the path of your ~b~Bandit~w~.
					help_timer = 0
					help_text_flag[0]++	
				ENDIF



				// ----- CONTROL INSTRUCTIONS HELP ---------------
				LVAR_INT control_help_flag
				IF help_timer > 1000
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED

					SWITCH control_help_flag
						CASE 0
							PRINT_HELP Z4_H17  

							help_timer = 0
							control_help_flag++
						BREAK
						CASE 1
							PRINT_HELP Z4_H18  // Use the steering controls to tilt the helicopter in the direction you wish to maneuver it.
							help_timer = 0
							control_help_flag++
						BREAK
						CASE 2
							PRINT_HELP Z4_H19  
							help_timer = 0
							control_help_flag++
						BREAK
						CASE 3
							PRINT_HELP Z4_H20  
							help_timer = 0
							control_help_flag++
						BREAK
						CASE 4
							PRINT_HELP Z4_H21  
							help_timer = 0
							control_help_flag++
						BREAK
						CASE 5
							PRINT_HELP Z4_H22  // Use the camera movement controls to look around the helicopter.
							help_timer = 0
							control_help_flag++
						BREAK
					ENDSWITCH
					
				ENDIF






				// --- FIRST TIME OBSTRUCTION HELP ------------------------
				IF NOT pcar_is_obstructed = 0
					IF obstruction_type = 1
						IF play_first_time_blocked_audio = 0
							play_first_time_blocked_audio++ 
						ENDIF
					ENDIF
				ENDIF
				// first time obstructed audio
				IF play_first_time_blocked_audio = 1
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				AND audio_line_is_active = 0

					IF play_first_time_blocked_audio = 0
						IF dialogue_timer > 1000
							GENERATE_RANDOM_INT_IN_RANGE 0 3 obstruct_audio1
							GENERATE_RANDOM_INT_IN_RANGE 0 3 obstruct_audio2
							IF obstruct_audio1 = 0
								$audio_string    = &ZER4_BA	
								audio_sound_file = SOUND_ZER4_BA
							ENDIF
							IF obstruct_audio1 = 1
								$audio_string    = &ZER4_BB	
								audio_sound_file = SOUND_ZER4_BB
							ENDIF
							IF obstruct_audio1 = 2
								$audio_string    = &ZER4_BC	
								audio_sound_file = SOUND_ZER4_BC
							ENDIF
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							dialogue_timer = 0
							play_first_time_blocked_audio++	
						ENDIF
					ENDIF	
					IF play_first_time_blocked_audio = 1
						IF dialogue_timer > 1000
							IF obstruct_audio1 = 0
								$audio_string    = &ZER4_CA	
								audio_sound_file = SOUND_ZER4_CA
							ENDIF
							IF obstruct_audio1 = 1
								$audio_string    = &ZER4_CB	
								audio_sound_file = SOUND_ZER4_CB
							ENDIF
							IF obstruct_audio1 = 2
								$audio_string    = &ZER4_CC	
								audio_sound_file = SOUND_ZER4_CC
							ENDIF
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							dialogue_timer = 0
							play_first_time_blocked_audio++	
							obstruct_audio1 = 0
						ENDIF
					ENDIF
				ENDIF
				IF play_first_time_blocked_audio = 2
				AND NOT pcar_is_obstructed = 0
					IF dialogue_timer > 1000
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							PRINT_HELP Z4_H5 // You must remove any obstructions before your ~b~Bandit can continue.
							play_first_time_blocked_audio++	
							dialogue_timer = 0	
						ENDIF	
					ENDIF	
				ENDIF

//				IF play_first_time_blocked_audio = 3
//				AND pcar_is_obstructed = 0
//					IF dialogue_timer > 1000
//						IF NOT IS_MESSAGE_BEING_DISPLAYED
//						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
//							PRINT_HELP Z4_H3 // After lifting the obstruction from the path of your ~b~Bandit ~w~press the ~o~ button to realease the object.
//							play_first_time_blocked_audio++	
//							dialogue_timer = 0	
//						ENDIF	
//					ENDIF	
//				ENDIF

				IF play_first_time_blocked_audio = 3
					IF pcar_is_obstructed = 1
					AND NOT IS_MESSAGE_BEING_DISPLAYED
					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					AND dialogue_timer > 10000
					AND audio_line_is_active = 0
						// barrels only
						IF obstruction_type = 1
							obstruct_audio1++
							IF obstruct_audio1 > 4
								obstruct_audio1 = 0
							ENDIF
							SWITCH obstruct_audio1
								CASE 0
									$audio_string    = &ZER4_DA	
									audio_sound_file = SOUND_ZER4_DA	
								BREAK
								CASE 1
									$audio_string    = &ZER4_DB	
									audio_sound_file = SOUND_ZER4_DB
								BREAK
								CASE 2
									$audio_string    = &ZER4_DC	
									audio_sound_file = SOUND_ZER4_DC
								BREAK
								CASE 3
									$audio_string    = &ZER4_DD	
									audio_sound_file = SOUND_ZER4_DD
								BREAK
								CASE 4
									$audio_string    = &ZER4_DE	
									audio_sound_file = SOUND_ZER4_DE
								BREAK
//								CASE 5
//									$audio_string    = &ZER4_DF	
//									audio_sound_file = SOUND_ZER4_DF
//								BREAK
							ENDSWITCH
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							dialogue_timer = 0
						ENDIF
					ENDIF
				ENDIF


				





				// ---- FIRST TIME ATTACKED BY TANKS -----------------------------
				IF tanks_are_all_dead = 0
					IF IS_CAR_DEAD otank[0]
					AND IS_CAR_DEAD otank[1]
					AND IS_CAR_DEAD otank[2]
						tanks_are_all_dead = 1	
					ENDIF
				ENDIF


				// if car is taking damage taking_damage_from_tank
				//IF taking_damage_from_tank = 1
				IF tanks_are_all_dead = 0
					IF pcar_progress > 42
						IF first_time_taking_damage_from_tank = 0
							IF NOT IS_MESSAGE_BEING_DISPLAYED
							AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							AND audio_line_is_active = 0
								IF dialogue_timer > 1000
									$audio_string    = &ZER4_EA	
									audio_sound_file = SOUND_ZER4_EA
									START_NEW_SCRIPT audio_line -1 0 1 1 0
									first_time_taking_damage_from_tank++
									dialogue_timer = 0		
								ENDIF	
							ENDIF	
						ENDIF
					ENDIF
					IF first_time_taking_damage_from_tank = 1
						IF  NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND audio_line_is_active = 0
							IF dialogue_timer > 1000
								$audio_string    = &ZER4_EB	
								audio_sound_file = SOUND_ZER4_EB
								START_NEW_SCRIPT audio_line -1 0 1 1 0
								first_time_taking_damage_from_tank++
								dialogue_timer = 0		
							ENDIF	
						ENDIF	
					ENDIF
					IF first_time_taking_damage_from_tank = 2
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND audio_line_is_active = 0
						AND dialogue_timer > 1000
							IF heli_is_carrying_bomb = 0	
								PRINT_NOW Z4_M26 7000 1 // pickup a bomb from the base
								IF DOES_OBJECT_EXIST rc_bomb
									ADD_BLIP_FOR_OBJECT rc_bomb bomb_blip
									SET_BLIP_AS_FRIENDLY bomb_blip TRUE
									first_time_taking_damage_from_tank++
									dialogue_timer = 0
								ENDIF
							ELSE
								first_time_taking_damage_from_tank++	
							ENDIF
						ENDIF
					ENDIF
					IF first_time_taking_damage_from_tank = 3
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND audio_line_is_active = 0
						AND dialogue_timer > 1000
							IF NOT heli_is_carrying_bomb = 0	
								IF DOES_BLIP_EXIST bomb_blip
									REMOVE_BLIP bomb_blip
								ENDIF
								PRINT_NOW Z4_M27 7000 1 // now drop the bomb on the tanks!
								first_time_taking_damage_from_tank++
	 						ENDIF
						ENDIF
					ENDIF
					IF first_time_taking_damage_from_tank = 4
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND heli_is_carrying_bomb = 0
							PRINT_HELP Z4_H11 // Other objects can also be dropped on ~r~Tigers which will cause them damage.
							first_time_taking_damage_from_tank++
							dialogue_timer = 0
						ENDIF
					ENDIF
					IF first_time_taking_damage_from_tank = 5
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND dialogue_timer > 10000
						AND audio_line_is_active = 0
					   	AND taking_damage_from_tank = 1		
							bomb_tank_dialogue++
							IF bomb_tank_dialogue > 5
								bomb_tank_dialogue = 0
							ENDIF
							temp_int = 0
							WHILE temp_int < 3
								IF NOT IS_CAR_DEAD otank[temp_int]
									temp_int = 99
								ENDIF
							temp_int++
							ENDWHILE
							IF temp_int = 99
								SWITCH bomb_tank_dialogue
									CASE 0
										$audio_string    = &ZER4_FA	
										audio_sound_file = SOUND_ZER4_FA	
									BREAK
									CASE 1
										$audio_string    = &ZER4_FB	
										audio_sound_file = SOUND_ZER4_FB
									BREAK
									CASE 2
										$audio_string    = &ZER4_FC	
										audio_sound_file = SOUND_ZER4_FC
									BREAK
									CASE 3
										$audio_string    = &ZER4_FD	
										audio_sound_file = SOUND_ZER4_FD
									BREAK
									CASE 4
										$audio_string    = &ZER4_FE	
										audio_sound_file = SOUND_ZER4_FE
									BREAK
									CASE 5
										$audio_string    = &ZER4_FF	
										audio_sound_file = SOUND_ZER4_FF
									BREAK
								ENDSWITCH
								START_NEW_SCRIPT audio_line -1 0 1 1 0
								dialogue_timer = 0
							ENDIF
							taking_damage_from_tank = 0
						ENDIF
					ENDIF
				ELSE
					IF DOES_BLIP_EXIST bomb_blip
						REMOVE_BLIP bomb_blip
					ENDIF

				ENDIF	
				



				IF DOES_BLIP_EXIST bomb_blip
					IF IS_OBJECT_ATTACHED rc_bomb
						REMOVE_BLIP bomb_blip
					ENDIF
				ENDIF




				// ---- FIRST TIME STUCK AT RIVER HELP ----------------------------
				IF stuck_at_river = 1
					IF first_time_stuck_at_river = 0
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND audio_line_is_active = 0
						AND dialogue_timer > 1000
							$audio_string    = &ZER4_GA	
							audio_sound_file = SOUND_ZER4_GA
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							first_time_stuck_at_river++
							dialogue_timer = 0		
						ENDIF	
					ENDIF
				ENDIF
				IF first_time_stuck_at_river = 1
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					AND audio_line_is_active = 0
					AND dialogue_timer > 1000
						IF stuck_at_river = 1
							IF NOT heli_is_carrying_plank = 1
								PRINT_NOW Z4_M28 7000 1 // grab a plank
								IF DOES_OBJECT_EXIST plank[2]
									ADD_BLIP_FOR_OBJECT plank[2] plank_blip
								ENDIF
							ENDIF
						ENDIF
						first_time_stuck_at_river++
						dialogue_timer = 0
					ENDIF
				ENDIF
				IF first_time_stuck_at_river = 2
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					AND audio_line_is_active = 0
					AND dialogue_timer > 1000
					AND heli_is_carrying_plank = 1
						IF stuck_at_river = 1
							PRINT_NOW Z4_M29 7000 1 // drop the plank at the river
						ENDIF
						IF DOES_BLIP_EXIST plank_blip
							REMOVE_BLIP plank_blip
						ENDIF
						first_time_stuck_at_river++
						dialogue_timer = 0
					ENDIF
				ENDIF
				IF first_time_stuck_at_river = 3
				AND stuck_at_river = 1
				AND audio_line_is_active = 0
				AND dialogue_timer > 10000
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						stuck_at_river_dialogue++
						IF stuck_at_river_dialogue > 2
							stuck_at_river_dialogue = 0
						ENDIF
						SWITCH stuck_at_river_dialogue
							CASE 0
								$audio_string    = &ZER4_HA	
								audio_sound_file = SOUND_ZER4_HA
							BREAK
							CASE 1
								$audio_string    = &ZER4_HB	
								audio_sound_file = SOUND_ZER4_HB
							BREAK
							CASE 2
								$audio_string    = &ZER4_HC	
								audio_sound_file = SOUND_ZER4_HC
							BREAK
						ENDSWITCH
						START_NEW_SCRIPT audio_line -1 0 1 1 0
						dialogue_timer = 0
					ENDIF 
				ENDIF
				
				
				   
				// ---- Bandit's nearly dead ----------------
				IF NOT IS_CAR_DEAD pcar
					GET_CAR_HEALTH pcar temp_int
					IF temp_int < 1400
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						AND dialogue_timer > 10000
						AND audio_line_is_active = 0

							GET_CAR_COORDINATES pcar x y z
							x2 = x - 5.0
							y2 = y - 5.0
							z2 = z - 5.0
							x3 = x + 5.0
							y3 = y + 5.0
							z3 = z + 5.0

							IF NOT IS_EXPLOSION_IN_AREA EXPLOSION_SMALL x2 y2 z2 x3 y3 z3
							
							 
														 
								nearly_dead_dialogue++
								IF nearly_dead_dialogue > 2
									nearly_dead_dialogue = 0
								ENDIF	
								SWITCH nearly_dead_dialogue
									CASE 0
										$audio_string    = &ZER4_KA	
										audio_sound_file = SOUND_ZER4_KA
									BREAK
									CASE 1
										$audio_string    = &ZER4_KB	
										audio_sound_file = SOUND_ZER4_KB
									BREAK
									CASE 2
										$audio_string    = &ZER4_KC	
										audio_sound_file = SOUND_ZER4_KC	
									BREAK
								ENDSWITCH
								START_NEW_SCRIPT audio_line -1 0 1 1 0
								dialogue_timer = 0


							ELSE

								$audio_string    = &ZER4_LC	
								audio_sound_file = SOUND_ZER4_LC
								START_NEW_SCRIPT audio_line -1 0 1 1 0
								dialogue_timer = 0	

							ENDIF

						ENDIF	
					ENDIF
				ENDIF
				


				// ----
				IF help_text_flag[6] = 99
				AND help_text_flag[7] = 0
					IF pcar_lives_left = 3
						CLEAR_HELP
						PRINT_HELP Z4_H12 // If the ~b~Bandit gets destroyed another will start from your base, careful as you only have 3.
						help_text_flag[7]++
						help_timer = 0
					ENDIF
				ENDIF
				IF help_text_flag[7] = 1
					//IF help_timer > 7000
					//	CLEAR_HELP
						help_text_flag[7] = 99
					//ENDIF
				ENDIF


				// if pheli gets destroyed respawn at base ------------------------------------------------------
				
				IF IS_CAR_DEAD pheli
					
					IF heli_is_carrying = 1
						IF DOES_OBJECT_EXIST carried_object
							//RELEASE_ENTITY_FROM_WINCH pheli
							DETACH_OBJECT carried_object 0.0 0.0 0.0 FALSE
//							IF IS_OBJECT_INTERSECTING_WORLD carried_object
//								GET_OBJECT_COORDINATES carried_object x y z
//								GET_OBJECT_HEADING carried_object heading
//								z += 1.0
//								SET_OBJECT_COORDINATES carried_object x y z
//								SET_OBJECT_HEADING carried_object heading
//							ENDIF
							SET_OBJECT_COLLISION pmagnet TRUE
							heli_is_carrying = 0
							pheli_pickup_timer = 0
							IF carried_object = rc_bomb
								bomb_is_armed = 1
								SET_OBJECT_RECORDS_COLLISIONS rc_bomb TRUE
								rc_bomb_timer = 0
							ENDIF
							carried_object = 0
						ELSE
							IF DOES_VEHICLE_EXIST carried_car
								//RELEASE_ENTITY_FROM_WINCH pheli
								DETACH_CAR carried_car 0.0 0.0 0.0 FALSE
								SET_OBJECT_COLLISION pmagnet TRUE
								heli_is_carrying = 0
								pheli_pickup_timer = 0
								carried_car = 0
							ENDIF
						ENDIF
					ENDIF
					
				  	 
					//EXPLODE_CAR pheli
				   	//WAIT 1000
					
					WAIT 1000

					LVAR_INT pheli_old
					pheli_old = pheli
					MARK_CAR_AS_NO_LONGER_NEEDED pheli

	

					pheli_lives--
					
					IF pheli_lives > 0
						SET_FIXED_CAMERA_POSITION -968.2972 1077.4132 1346.6857 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -969.2341 1077.3680 1346.3391 JUMP_CUT	
					ENDIF

					

					pheli = 0
				ENDIF

				LVAR_INT pheli_is_invincible
				IF NOT IS_CAR_DEAD pheli
					IF pheli_is_invincible = 1
						IF invincible_timer > 10000
							SET_CAR_PROOFS pheli FALSE FALSE FALSE FALSE FALSE
							pheli_is_invincible = 0	
						ENDIF
					ENDIF
				ENDIF

				IF pheli = 0
				
					IF NOT pheli_lives = 0
						RESTORE_CAMERA
						EXTINGUISH_FIRE_AT_POINT -973.7590 1077.1519 1350.3988 200.0
						CREATE_CAR RCGOBLIN -973.7590 1077.1519 1350.3988 pheli
						SET_CAR_PROOFS pheli TRUE TRUE TRUE TRUE TRUE
						pheli_is_invincible = 1 
						invincible_timer = 0
						SET_CAR_HEADING pheli 90.0
						SET_HELI_BLADES_FULL_SPEED pheli
						SET_VEHICLE_AREA_VISIBLE pheli 10
						//ATTACH_WINCH_TO_HELI pheli WINCHTYPE_RCHELI
						TAKE_REMOTE_CONTROL_OF_CAR player1 pheli
						IF pheli_lives = 2 
							PRINT_NOW Z4_M14 5000 1
	//						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
	//						IF temp_int = 0
	//							$audio_string    = &ZER4_MA	
	//							audio_sound_file = SOUND_ZER4_MA
	//							START_NEW_SCRIPT audio_line -1 0 1 1 0 
	//						ELSE
	//							$audio_string    = &ZER4_MB	
	//							audio_sound_file = SOUND_ZER4_MB
	//							START_NEW_SCRIPT audio_line -1 0 1 1 0
	//						ENDIF
						ENDIF
						IF pheli_lives = 1
							PRINT_NOW Z4_M23 5000 1
	//						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
	//						IF temp_int = 0
	//							$audio_string    = &ZER4_MC	
	//							audio_sound_file = SOUND_ZER4_MC
	//							START_NEW_SCRIPT audio_line -1 0 1 1 0 
	//						ELSE
	//							$audio_string    = &ZER4_MD	
	//							audio_sound_file = SOUND_ZER4_MD
	//							START_NEW_SCRIPT audio_line -1 0 1 1 0
	//						ENDIF
						ENDIF
						IF NOT DOES_OBJECT_EXIST pmagnet
							CREATE_OBJECT MINI_MAGNET -973.7590 1077.1519 1344.3988 pmagnet
						ENDIF
						ATTACH_OBJECT_TO_CAR pmagnet pheli 0.0 0.0 -0.2 0.0 0.0 0.0
						SET_OBJECT_COLLISION pmagnet TRUE
						IF pheli_lives = 0 
							SET_CAR_VISIBLE pheli FALSE
							SET_OBJECT_VISIBLE pmagnet FALSE
						ENDIF
						IF DOES_VEHICLE_EXIST pheli_old
							DELETE_CAR pheli_old
						ENDIF
					ENDIF
				ENDIF
				
				// if pcar gets destroyed respawn at base and start again ---------------------------------------
				IF pcar = 0
					CREATE_CAR RCBANDIT -975.6997 1061.1624 1344.7998 pcar
					SET_CAR_HEADING pcar 90.0
					SET_VEHICLE_AREA_VISIBLE pcar 10
					SET_CAR_PROOFS pcar FALSE TRUE TRUE FALSE FALSE
					START_PLAYBACK_RECORDED_CAR pcar 521
					SET_PLAYBACK_SPEED pcar 0.5
					ADD_BLIP_FOR_CAR pcar pcar_blip
					SET_BLIP_AS_FRIENDLY pcar_blip TRUE
					SET_CAR_HEALTH pcar 2000
					CHANGE_BLIP_SCALE pcar_blip 2
//					IF pcar_lives_left = 2
//						PRINT_NOW Z4_M16 5000 1
//					ENDIF
//					IF pcar_lives_left = 1
//						PRINT_NOW Z4_M22 5000 1
//					ENDIF

					IF pcar_lives_left = 2 
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						IF temp_int = 0
							$audio_string    = &ZER4_MA	
							audio_sound_file = SOUND_ZER4_MA
							START_NEW_SCRIPT audio_line -1 0 1 1 0 
						ELSE
							$audio_string    = &ZER4_MB	
							audio_sound_file = SOUND_ZER4_MB
							START_NEW_SCRIPT audio_line -1 0 1 1 0
						ENDIF
					ENDIF
					IF pcar_lives_left = 1
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						IF temp_int = 0
							$audio_string    = &ZER4_MC	
							audio_sound_file = SOUND_ZER4_MC
							START_NEW_SCRIPT audio_line -1 0 1 1 0 
						ELSE
							$audio_string    = &ZER4_MD	
							audio_sound_file = SOUND_ZER4_MD
							START_NEW_SCRIPT audio_line -1 0 1 1 0
						ENDIF
					ENDIF

				ENDIF
				IF NOT IS_CAR_DEAD pcar
					
					GET_CAR_HEALTH pcar temp_int

					IF temp_int < 1000


						GET_CAR_COORDINATES pcar x y z
						x2 = x - 5.0
						y2 = y - 5.0
						z2 = z - 5.0
						x3 = x + 5.0
						y3 = y + 5.0
						z3 = z + 5.0

						EXPLODE_CAR pcar

						IF NOT IS_EXPLOSION_IN_AREA EXPLOSION_SMALL x2 y2 z2 x3 y3 z3
		
							// play dialogue
							dead_dialogue++
							IF dead_dialogue > 2
								dead_dialogue = 0
							ENDIF
							SWITCH dead_dialogue
								CASE 0
									$audio_string    = &ZER4_LA	
									audio_sound_file = SOUND_ZER4_LA
								BREAK
								CASE 1
									$audio_string    = &ZER4_LB	
									audio_sound_file = SOUND_ZER4_LB
								BREAK
								CASE 2
									$audio_string    = &ZER4_LD	
									audio_sound_file = SOUND_ZER4_LD
								BREAK
							ENDSWITCH	
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							WAIT 100
							WHILE audio_line_is_active = 1
								WAIT 0
							ENDWHILE


						ELSE
							
							$audio_string    = &ZER4_LC	
							audio_sound_file = SOUND_ZER4_LC
							START_NEW_SCRIPT audio_line -1 0 1 1 0
							
							WAIT 100
							WHILE audio_line_is_active = 1
								WAIT 0
							ENDWHILE

						ENDIF




						MARK_CAR_AS_NO_LONGER_NEEDED pcar
						STOP_PLAYBACK_RECORDED_CAR pcar 
						REMOVE_BLIP pcar_blip
						pcar = 0
						pcar_lives_left--
						pcar_progress = 0
						pcar_halted = 0

					ENDIF
				ENDIF


				IF DOES_VEHICLE_EXIST oheli
					IF IS_CAR_DEAD oheli
						IF DOES_OBJECT_EXIST omagnet
							DELETE_OBJECT omagnet
						ENDIF
						IF DOES_OBJECT_EXIST rock[oheli_rock]
							IF IS_OBJECT_ATTACHED rock[oheli_rock]
								DETACH_OBJECT rock[oheli_rock] 0.0 0.0 0.0 FALSE
								SET_OBJECT_COLLISION rock[oheli_rock] TRUE
								IF IS_OBJECT_INTERSECTING_WORLD rock[oheli_rock]
									GET_OBJECT_COORDINATES rock[oheli_rock] x y z
									GET_OBJECT_HEADING rock[oheli_rock] heading
									z += 1.0
									SET_OBJECT_COORDINATES rock[oheli_rock] x y z
									SET_OBJECT_HEADING rock[oheli_rock] heading
								ENDIF
							ENDIF
						ENDIF
						oheli = 0
					ENDIF
				ENDIF

				// first bridge ----------------------------------------------------------------------------------
				IF first_bridge_laid = 0
					// check if plank is there
					temp_int = 0									
					WHILE temp_int < 5
						IF DOES_OBJECT_EXIST plank[temp_int]
							IF NOT IS_OBJECT_ATTACHED plank[temp_int]
								IF LOCATE_OBJECT_3D plank[temp_int] -1043.5081 1041.3798 1341.2938 1.0 1.0 1.0 FALSE
									SET_OBJECT_COORDINATES plank[temp_int] -1043.5081 1041.3798 1341.2938
									SET_OBJECT_ROTATION plank[temp_int] -2.7700 -2.1300 -38.1200
									FREEZE_OBJECT_POSITION plank[temp_int] TRUE
									REPORT_MISSION_AUDIO_EVENT_AT_OBJECT plank[temp_int] SOUND_BONNET_DENT
									first_bridge_laid = 1 
									first_bridge_plank = temp_int
									temp_int = 5
								ENDIF
							ENDIF
						ENDIF
					temp_int++
					ENDWHILE
				ENDIF
				IF first_bridge_laid = 1
					IF DOES_OBJECT_EXIST plank[first_bridge_plank]
						IF IS_OBJECT_ATTACHED plank[first_bridge_plank]
							FREEZE_OBJECT_POSITION plank[first_bridge_plank] FALSE
							first_bridge_laid = 0
							first_bridge_plank = -1
						ENDIF
					ENDIF
				ENDIF
				// display help for first bridge if car is waiting - and pause car
				IF NOT IS_CAR_DEAD pcar
					IF pcar_is_paused = 0
						IF first_bridge_laid = 0							
							IF LOCATE_CAR_3D pcar -1041.83 1044.25 1341.42 1.5 1.5 1.5 FALSE
								PAUSE_PLAYBACK_RECORDED_CAR pcar  
								pcar_is_paused = 1
								get_plank1 = 1
								stuck_at_river = 1
//								CLEAR_HELP 
//								PRINT_HELP_FOREVER 
							ENDIF
						ENDIF
					ELSE
						IF first_bridge_laid = 1
							IF LOCATE_CAR_3D pcar -1041.83 1044.25 1341.42 1.5 1.5 1.5 FALSE
								UNPAUSE_PLAYBACK_RECORDED_CAR pcar
								pcar_is_paused = 0
								stuck_at_river = 0
							ENDIF
						ENDIF	 
					ENDIF
				ENDIF

				// prompt text for getting plank
				IF get_plank1 = 1
					IF help_text_flag[4] = 99
						PRINT_NOW Z4_M20 7000 1 // get plank
						get_plank1++
					ENDIF
				ENDIF
				IF get_plank1 = 2
					IF DOES_OBJECT_EXIST carried_object
						temp_int = 0
						temp_int2 = 0
						WHILE temp_int < 5
							IF DOES_OBJECT_EXIST plank[temp_int]
								IF plank[temp_int] = carried_object
									temp_int2 = 1
									temp_int = 5
								ENDIF
							ENDIF
						temp_int++
						ENDWHILE
						IF temp_int2 = 1
							PRINT_NOW Z4_M21 7000 1 // drop plank at river 
							get_plank1++
						ENDIF
					ENDIF
				ENDIF
				IF get_plank1 = 3
					IF first_bridge_laid = 1
						CLEAR_PRINTS
						get_plank1 = 99
					ENDIF	
				ENDIF

				// second bridge --------------------------------------------------------
				IF second_bridge_laid = 0
					// check if plank is there
					temp_int = 0									
					WHILE temp_int < 5
						IF DOES_OBJECT_EXIST plank[temp_int]
							IF NOT IS_OBJECT_ATTACHED plank[temp_int]
								IF LOCATE_OBJECT_3D plank[temp_int] -1062.8333 1076.6279 1341.2304 1.0 1.0 1.0 FALSE
									SET_OBJECT_COORDINATES plank[temp_int] -1062.8333 1076.6279 1341.2304  
									SET_OBJECT_ROTATION plank[temp_int] 4.7000 -4.7700 -31.5800
									FREEZE_OBJECT_POSITION plank[temp_int] TRUE
									REPORT_MISSION_AUDIO_EVENT_AT_OBJECT plank[temp_int] SOUND_BONNET_DENT
									second_bridge_laid = 1 
									second_bridge_plank = temp_int
									temp_int = 5
								ENDIF
							ENDIF
						ENDIF
					temp_int++
					ENDWHILE
				ENDIF
				IF second_bridge_laid = 1
					IF DOES_OBJECT_EXIST plank[second_bridge_plank]
						IF IS_OBJECT_ATTACHED plank[second_bridge_plank]
							FREEZE_OBJECT_POSITION plank[second_bridge_plank] FALSE
							second_bridge_laid = 0
							second_bridge_plank = -1
						ENDIF
					ENDIF
				ENDIF
				// display help for second bridge if car is waiting - and pause car
				IF NOT IS_CAR_DEAD pcar
					IF pcar_is_paused = 0
						IF second_bridge_laid = 0							
							IF LOCATE_CAR_3D pcar -1064.09 1074.37 1341.32 1.5 1.5 1.5 FALSE
								PAUSE_PLAYBACK_RECORDED_CAR pcar  	   
								stuck_at_river = 1
								pcar_is_paused = 1
								get_plank2 = 1
//								CLEAR_HELP 
//								PRINT_HELP_FOREVER 
							ENDIF
						ENDIF
					ELSE
						IF second_bridge_laid = 1
							IF LOCATE_CAR_3D pcar -1064.09 1074.37 1341.32 1.5 1.5 1.5 FALSE
								UNPAUSE_PLAYBACK_RECORDED_CAR pcar
								stuck_at_river = 0
								pcar_is_paused = 0
							ENDIF
						ENDIF	 
					ENDIF
				ENDIF

				// prompt text for getting plank
				IF get_plank2 = 1
					PRINT_NOW Z4_M20 7000 1 // get plank
					get_plank2++
				ENDIF
				IF get_plank2 = 2
					IF DOES_OBJECT_EXIST carried_object
						temp_int = 0
						temp_int2 = 0
						WHILE temp_int < 5
							IF DOES_OBJECT_EXIST plank[temp_int]
								IF plank[temp_int] = carried_object
									temp_int2 = 1
									temp_int = 5
								ENDIF
							ENDIF
						temp_int++
						ENDWHILE
						IF temp_int2 = 1
							PRINT_NOW Z4_M21 7000 1 // drop plank at river 
							get_plank2++
						ENDIF
					ENDIF
				ENDIF
				IF get_plank2 = 3
					IF second_bridge_laid = 1
						CLEAR_PRINTS
						get_plank2 = 99
					ENDIF	
				ENDIF
	
				
				// check if car is obstructed ----------------------------------------------------------
				IF pcar_halted = 0
					IF pcar_is_paused = 0
						IF pcar_is_obstructed = 0
							IF NOT IS_CAR_DEAD pcar
								obstruction_timer = 0
								obstruction_type = 0

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pcar 0.0 2.0 0.0 x y z
								
								// check if rock is in way
								temp_int = 0
								WHILE temp_int < 10
									IF DOES_OBJECT_EXIST rock[temp_int]
										IF LOCATE_OBJECT_3D rock[temp_int] x y z 1.0 1.0 1.0 FALSE
											GET_OBJECT_COORDINATES rock[temp_int] x2 y2 z2
											GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
											IF temp_float < 0.8
												PAUSE_PLAYBACK_RECORDED_CAR pcar
												pcar_is_paused = 1
												pcar_is_obstructed = 1
												obstruction_type = 1
												GOTO check_car_obstruction_end
											ENDIF
										ENDIF
									ENDIF
								temp_int++
								ENDWHILE

								// check if baddie tank is in way
								temp_int = 0
								WHILE temp_int < 3
									IF DOES_VEHICLE_EXIST otank[temp_int]
										IF NOT IS_CAR_DEAD otank[temp_int]
											IF LOCATE_CAR_3D otank[temp_int] x y z 2.0 2.0 2.0 FALSE
												GET_CAR_COORDINATES otank[temp_int] x2 y2 z2
												GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
												IF temp_float < 0.9
													PAUSE_PLAYBACK_RECORDED_CAR pcar
													pcar_is_paused = 1
													pcar_is_obstructed = 1
													obstruction_type = 2
													PRINT_NOW Z4_M18 5000 1 // bandit is obstructed by rock
													GOTO check_car_obstruction_end
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								temp_int++
								ENDWHILE

								// check if players heli is in the way
								IF NOT IS_CAR_DEAD pheli
									IF LOCATE_CAR_3D pheli x y z 1.0 1.0 1.0 FALSE
										PAUSE_PLAYBACK_RECORDED_CAR pcar
										pcar_is_paused = 1
										pcar_is_obstructed = 1
										GOTO check_car_obstruction_end
									ENDIF
								ENDIF

								IF pcar_is_obstructed = 1
									obstruction_timer = 30000
								ENDIF

								check_car_obstruction_end:
							ENDIF
						ENDIF
					ELSE
						// check if obstruction has been removed
						IF pcar_is_obstructed = 1
							IF NOT IS_CAR_DEAD pcar

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pcar 0.0 2.0 0.0 x y z
								
								pcar_is_obstructed = 0

								// check if rock is in way
								temp_int = 0
								WHILE temp_int < 10
									IF DOES_OBJECT_EXIST rock[temp_int]
										IF LOCATE_OBJECT_3D rock[temp_int] x y z 2.0 2.0 2.0 FALSE
											GET_OBJECT_COORDINATES rock[temp_int] x2 y2 z2
											GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
											IF temp_float < 0.9
												pcar_is_obstructed = 1
												obstruction_type = 1
												GOTO check_car_obstruction_end2
											ENDIF
										ENDIF
									ENDIF
								temp_int++
								ENDWHILE

								// check if tank is in way
								temp_int = 0
								WHILE temp_int < 3
									IF DOES_VEHICLE_EXIST otank[temp_int]
										IF NOT IS_CAR_DEAD otank[temp_int]
											IF LOCATE_CAR_3D otank[temp_int] x y z 1.0 1.0 1.0 FALSE
												GET_CAR_COORDINATES otank[temp_int] x2 y2 z2
												GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
												IF temp_float < 0.8
													pcar_is_obstructed = 1
													obstruction_type = 2
													GOTO check_car_obstruction_end2
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								temp_int++
								ENDWHILE

								// check if players heli is in the way
								IF NOT IS_CAR_DEAD pheli
									IF LOCATE_CAR_3D pheli x y z 1.0 1.0 1.0 FALSE
										pcar_is_obstructed = 1
										GOTO check_car_obstruction_end2
									ENDIF
								ENDIF

								// display message to remove obstruction
								IF pcar_is_obstructed = 1
								AND obstruction_timer > 30000
								AND obstruction_count < 3
									IF obstruction_type = 1
										PRINT Z4_M18 5000 1 // bandit is obstructed by rock
									ENDIF
									IF obstruction_type = 2
										PRINT Z4_M25 5000 1 // bandit is obstructed by tank
									ENDIF
									obstruction_timer = 0
								ENDIF
							
								// unpause car if obstruction has been removed
								IF pcar_is_obstructed = 0
									UNPAUSE_PLAYBACK_RECORDED_CAR pcar
									pcar_is_paused = 0	
									obstruction_type = 0	
									CLEAR_PRINTS
									IF obstruction_timer > 1000
									AND obstruction_timer < 30000
										obstruction_count++
									ENDIF
								ENDIF


								check_car_obstruction_end2:
							ENDIF
						ENDIF
					ENDIF
				ENDIF




				// if pcar is taking damage then reverse for a bit ----------------------------------------
				// get pcar health
				IF pcar_halted = 0
					IF NOT IS_CAR_DEAD pcar 

						// taking damage from tank
						LVAR_INT taking_damage_from_tank
						IF HAS_CAR_BEEN_DAMAGED_BY_WEAPON pcar WEAPONTYPE_MICRO_UZI
							taking_damage_from_tank = 1
							CLEAR_CAR_LAST_WEAPON_DAMAGE pcar
						ENDIF

						GET_CAR_HEALTH pcar z4_pcar_health
						z4_pcar_health += -1000
						temp_float =# z4_pcar_health
						temp_float /= 1000.0
						temp_float *= 100.0
						z4_pcar_health =# temp_float
						IF z4_pcar_health < last_health
							//PRINT_NOW Z4_M05 4000 1 // your bandit is taking damage
							bandit_taking_damage = 1
							pcar_timer = 0
						ENDIF
						last_health = z4_pcar_health

						// reverse car if it's taking damage
						IF bandit_taking_damage = 1
							IF pcar_is_paused = 1
								UNPAUSE_PLAYBACK_RECORDED_CAR pcar
								pcar_is_paused = 0
							ENDIF

							IF IS_PLAYBACK_GOING_ON_FOR_CAR pcar
								SET_PLAYBACK_SPEED pcar -0.2
								bandit_taking_damage++
								pcar_timer = 0
							ENDIF
						ENDIF
						// wait 4 seconds
						IF bandit_taking_damage = 2
							IF pcar_timer > 4000
								bandit_taking_damage++
								pcar_timer = 0		
							ENDIF
						ENDIF
						// stop for a few secs
						IF bandit_taking_damage = 3
							SET_PLAYBACK_SPEED pcar 0.5
							PAUSE_PLAYBACK_RECORDED_CAR pcar 
							pcar_is_paused = 1
							pcar_timer = 0
							bandit_taking_damage++
						ENDIF
						// continue
						IF bandit_taking_damage = 4
							IF pcar_timer > 4000
								UNPAUSE_PLAYBACK_RECORDED_CAR pcar
								pcar_is_paused = 0
								bandit_taking_damage = 0
								pcar_timer = 0
								pcar_is_obstructed = 0	
							ENDIF
						ENDIF
					ENDIF
				ENDIF


				

				// heli picking up and dropping stuff -----------------------------------------------------

//				IF NOT IS_CAR_DEAD pheli
//
//					SET_ROPE_HEIGHT_FOR_HELI pheli 0.98
//
//					IF heli_is_carrying = 0
//						GRAB_ENTITY_ON_WINCH pheli winch_car winch_ped winch_object
//						
//						IF NOT winch_object = -1
//							// check for rocks
//							temp_int = 0
//							WHILE temp_int < 10
//								IF DOES_OBJECT_EXIST rock[temp_int]
//									IF winch_object = rock[temp_int]
//										carried_object = rock[temp_int]
//										temp_int = 10   
//										heli_is_carrying = 1  											
//										GOTO heli_pickup_end2			
//									ENDIF	
//								ENDIF
//							temp_int++
//							ENDWHILE
//							// check for planks
//							temp_int = 0
//							WHILE temp_int < 5
//								IF DOES_OBJECT_EXIST plank[temp_int]
//									IF winch_object = plank[temp_int]
//										carried_object = plank[temp_int]
//										temp_int = 5   
//										heli_is_carrying = 1  											
//										GOTO heli_pickup_end2			
//									ENDIF	
//								ENDIF
//							temp_int++
//							ENDWHILE
//							// check for bomb
//							IF DOES_OBJECT_EXIST rc_bomb
//								IF winch_object = rc_bomb
//									carried_object = rc_bomb 
//									heli_is_carrying = 1  											
//									GOTO heli_pickup_end2			
//								ENDIF	
//							ENDIF
//						ENDIF
//
//						IF NOT winch_car = -1
//							// check tanks
//							temp_int = 0
//							WHILE temp_int < 3
//								IF DOES_VEHICLE_EXIST otank[temp_int]
//									IF winch_car = otank[temp_int]
//										carried_car = otank[temp_int]
//										heli_is_carrying = 1
//										GOTO heli_pickup_end2
//									ENDIF
//								ENDIF
//							temp_int++
//							ENDWHILE	
//						ENDIF
//
//					heli_pickup_end2:
//					ENDIF
//				ENDIF

				// if heli is carrying object and check to see if the object is below the ground
				LVAR_INT check_for_ground_collision
				IF NOT IS_CAR_DEAD pheli
					IF check_for_ground_collision = 1
						IF NOT heli_is_carrying = 0

							IF heli_is_carrying_plank = 1
								GET_CAR_COORDINATES pheli x2 y2 z2
								GET_GROUND_Z_FOR_3D_COORD x2 y2 z2 z2
								
								temp_int = 0

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 0.0 1.7 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 0.0 -1.7 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 0.2 0.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object -0.2 0.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								IF temp_int = 1
									SET_OBJECT_COLLISION carried_object FALSE
									IF NOT IS_CAR_DEAD pheli
										APPLY_FORCE_TO_CAR pheli 0.0 0.0 0.005 0.0 0.0 0.0
									ENDIF
								ELSE
									SET_OBJECT_COLLISION carried_object TRUE
									check_for_ground_collision = 0
								ENDIF
							ENDIF

							IF heli_is_carrying_rock = 1
								GET_CAR_COORDINATES pheli x2 y2 z2
								GET_GROUND_Z_FOR_3D_COORD x2 y2 z2 z2
								
								temp_int = 0

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 0.0 1.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 0.0 -1.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object 1.0 0.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS carried_object -1.0 0.0 0.0 x y z
								temp_float = z + 0.5
								//LINE x y z x y temp_float
								IF z < z2
									temp_int = 1
								ENDIF

								IF temp_int = 1
									SET_OBJECT_COLLISION carried_object FALSE
									IF NOT IS_CAR_DEAD pheli
										APPLY_FORCE_TO_CAR pheli 0.0 0.0 0.005 0.0 0.0 0.0
									ENDIF
								ELSE
									SET_OBJECT_COLLISION carried_object TRUE
									check_for_ground_collision = 0
								ENDIF
							ENDIF
						ELSE
							check_for_ground_collision = 0
						ENDIF
					ENDIF
				ENDIF


				IF NOT IS_CAR_DEAD pheli
					IF heli_is_carrying = 0
						IF circle_is_pressed = 0 
							
							IF IS_BUTTON_PRESSED PAD1 CIRCLE
							AND pheli_pickup_timer > 1000
								
									circle_is_pressed = 1
									GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pheli 0.0 0.0 -1.0	x y z

									// check for rocks
									LVAR_INT heli_is_carrying_rock
									temp_int = 0
									WHILE temp_int < 10
										IF DOES_OBJECT_EXIST rock[temp_int]
											IF LOCATE_OBJECT_3D rock[temp_int] x y z 1.0 1.0 1.0 FALSE

												temp_int2 = 0
		
												// check that other heli isn't carrying this rock

												IF IS_OBJECT_ATTACHED rock[temp_int]
													temp_int2 = 1
												ENDIF

												IF temp_int2 = 0
													carried_object = rock[temp_int]
													GET_OBJECT_HEADING rock[temp_int] temp_float

													ATTACH_OBJECT_TO_CAR carried_object pheli 0.0 0.0 -1.2 0.0 90.0 0.0
													IF DOES_OBJECT_EXIST carried_object
														REPORT_MISSION_AUDIO_EVENT_AT_OBJECT carried_object SOUND_BONNET_DENT
													ENDIF
													SET_OBJECT_COLLISION pmagnet TRUE // was FALSE
													SORT_OUT_OBJECT_COLLISION_WITH_CAR carried_object pheli
													//PICK_UP_OBJECT_WITH_WINCH pheli carried_object
													temp_int = 10
													heli_is_carrying = 1
													heli_is_carrying_rock = 1
													check_for_ground_collision = 1
													GOTO heli_pickup_end
												ENDIF

											ENDIF
										ENDIF
									temp_int++
									ENDWHILE

									// check for planks
									LVAR_INT heli_is_carrying_plank
									temp_int = 0
									WHILE temp_int < 5
										IF DOES_OBJECT_EXIST plank[temp_int]
											IF LOCATE_OBJECT_3D plank[temp_int] x y z 1.0 1.0 1.0 FALSE
												carried_object = plank[temp_int]
//												GET_OBJECT_HEADING plank[temp_int] temp_float
//												temp_float += -90.0
//												IF temp_float > 360.0
//													temp_float += -360.0
//												ENDIF
//												GET_CAR_HEADING pcar heading
//												temp_float2 = temp_float - heading 
//												IF temp_float2 > 180.0
//													temp_float2 += -360.0
//												ENDIF
												//WRITE_DEBUG attaching_plank
												ATTACH_OBJECT_TO_CAR carried_object pheli 0.0 0.0 -1.0 0.0 0.0 0.0 
												IF DOES_OBJECT_EXIST carried_object
//													IF IS_OBJECT_INTERSECTING_WORLD carried_object
//														APPLY_FORCE_TO_CAR pheli 0.0 0.0 0.1 0.0 0.0 0.0
//													ENDIF
													REPORT_MISSION_AUDIO_EVENT_AT_OBJECT carried_object SOUND_BONNET_DENT
												ENDIF
												SET_OBJECT_COLLISION pmagnet TRUE // was FALSE
												SORT_OUT_OBJECT_COLLISION_WITH_CAR carried_object pheli
												temp_int = 6
												heli_is_carrying = 1
												heli_is_carrying_plank = 1 
												check_for_ground_collision = 1
												GOTO heli_pickup_end	
											ENDIF
										ENDIF
									temp_int++
									ENDWHILE

									// check for bomb
									LVAR_INT heli_is_carrying_bomb
									IF DOES_OBJECT_EXIST rc_bomb
										IF LOCATE_OBJECT_3D rc_bomb x y z 1.0 1.0 1.0 FALSE
											carried_object = rc_bomb
											//WRITE_DEBUG attaching_bomb
											ATTACH_OBJECT_TO_CAR carried_object pheli 0.0 0.0 -1.0 0.0 0.0 0.0 
											IF DOES_OBJECT_EXIST carried_object
												IF IS_OBJECT_INTERSECTING_WORLD carried_object
													APPLY_FORCE_TO_CAR pheli 0.0 0.0 0.1 0.0 0.0 0.0
												ENDIF
												REPORT_MISSION_AUDIO_EVENT_AT_OBJECT carried_object SOUND_BONNET_DENT
											ENDIF
											SET_OBJECT_COLLISION pmagnet TRUE // was FALSE
											SORT_OUT_OBJECT_COLLISION_WITH_CAR carried_object pheli
											//PICK_UP_OBJECT_WITH_WINCH pheli carried_object
											heli_is_carrying = 1
											heli_is_carrying_bomb = 1
											GOTO heli_pickup_end
										ENDIF
									ENDIF

									heli_pickup_end:
								ELSE
									circle_is_pressed = 0
								ENDIF
						ELSE
							IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
								circle_is_pressed = 0
							ENDIF	
						ENDIF
					ELSE
						IF circle_is_pressed = 1
							IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
								circle_is_pressed = 0
							ENDIF
						ELSE
							IF IS_BUTTON_PRESSED PAD1 CIRCLE
								IF DOES_OBJECT_EXIST carried_object
									IF NOT IS_OBJECT_INTERSECTING_WORLD carried_object 
										DETACH_OBJECT carried_object 0.0 0.0 0.0 FALSE

										SET_OBJECT_COLLISION pmagnet TRUE
										//RELEASE_ENTITY_FROM_WINCH pheli
										heli_is_carrying = 0
										pheli_pickup_timer = 0
										IF carried_object = rc_bomb
											bomb_is_armed = 1
											SET_OBJECT_RECORDS_COLLISIONS rc_bomb TRUE
											rc_bomb_timer = 0
										ENDIF
										carried_object = 0
										circle_is_pressed = 1
										heli_is_carrying_bomb = 0
										heli_is_carrying_plank = 0
										heli_is_carrying_rock = 0
									ENDIF
								ELSE
									IF DOES_VEHICLE_EXIST carried_car
										DETACH_CAR carried_car 0.0 0.0 0.0 FALSE
										SET_OBJECT_COLLISION pmagnet TRUE
										heli_is_carrying = 0
										pheli_pickup_timer = 0
										carried_car = 0
										circle_is_pressed = 1
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// rc_bomb - explode on impact ------------------------------------------------------------
				IF DOES_OBJECT_EXIST rc_bomb
					IF bomb_is_armed = 1
						IF HAS_OBJECT_COLLIDED_WITH_ANYTHING rc_bomb
						OR rc_bomb_timer > 10000
							IF rc_bomb_timer > 500
								GET_OBJECT_COORDINATES rc_bomb x y z
								ADD_EXPLOSION x y z EXPLOSION_SMALL
								DELETE_OBJECT rc_bomb
								bomb_is_armed = 0
							ENDIF
						ENDIF
					ENDIF
				ELSE
					CREATE_OBJECT_NO_OFFSET RCBOMB -974.5125 1072.0446 1344.2984 rc_bomb
					SET_OBJECT_HEADING rc_bomb 90.0
					bomb_is_armed = 0	
				ENDIF		

				
				// get car progress	-----------------------------------------------------------------------
				IF NOT IS_CAR_DEAD pcar 
					temp_int = pcar_progress
					WHILE temp_int < 117
						IF NOT IS_CAR_DEAD pcar
							IF LOCATE_CAR_2D pcar pcar_path_x[temp_int] pcar_path_y[temp_int] 1.0 1.0 FALSE
								pcar_progress = temp_int + 1
							ELSE
								temp_int = 117
							ENDIF
						ELSE
							pcar_progress = 0
						ENDIF
					temp_int++
					ENDWHILE
					IF pcar_progress > 116
						pcar_progress = 116
					ENDIF
				ENDIF


				
				// baddie heli ai --------------------------------------------------------------------------
				IF NOT IS_CAR_DEAD oheli
					
					// get spare rock
					IF oheli_flag = 0

						oheli_rock = -1
						temp_int = 3
						WHILE temp_int < 10
							IF NOT DOES_OBJECT_EXIST rock[temp_int]
								oheli_rock = temp_int
								temp_int = 10
							ENDIF
							temp_int++
						ENDWHILE
						
						IF oheli_rock > -1
							CREATE_OBJECT BARREL2 0.0 0.0 0.0 rock[oheli_rock]
							ATTACH_OBJECT_TO_CAR rock[oheli_rock] oheli 0.0 0.0 -1.2 0.0 90.0 0.0
							IF DOES_OBJECT_EXIST rock[oheli_rock]
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT rock[oheli_rock] SOUND_BONNET_DENT
							ENDIF 
							SET_OBJECT_COLLISION omagnet FALSE
							SORT_OUT_OBJECT_COLLISION_WITH_CAR rock[oheli_rock] oheli
							//PICK_UP_OBJECT_WITH_WINCH oheli rock[oheli_rock]
							oheli_flag++
						ELSE
							// remove a rock that is off screen	to make space for new one
							temp_int = 3
							WHILE temp_int < 10
								IF DOES_OBJECT_EXIST rock[temp_int]
									IF NOT IS_OBJECT_ON_SCREEN rock[temp_int]
										DELETE_OBJECT rock[temp_int] 
										temp_int = 10
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
						ENDIF		
					ENDIF

					// get point to fly to
					IF oheli_flag = 1
						oheli_goto = pcar_progress
						oheli_goto += 30
						IF oheli_goto > 114
							oheli_goto = 114
						ENDIF
						SET_HELI_STABILISER oheli FALSE
						SET_HELI_REACHED_TARGET_DISTANCE oheli 0
						//ACTIVATE_HELI_SPEED_CHEAT oheli 1
						HELI_GOTO_COORDS oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 5.0 352.0
						oheli_flag++
					ENDIF

					// wait for heli to get there
					IF oheli_flag = 2														
						IF LOCATE_CAR_3D oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 5.0 5.0 15.0 FALSE // put down to locat of 1.0							
							IF pcar_progress > oheli_goto
								oheli_goto = pcar_progress + 10
								IF oheli_goto > 114
									oheli_goto = 114
								ENDIF
								//ACTIVATE_HELI_SPEED_CHEAT oheli 1
					   			HELI_GOTO_COORDS oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 5.0 352.0
							ELSE
								oheli_flag++
							ENDIF
						ELSE
							IF pcar_progress > oheli_goto
								oheli_goto = pcar_progress + 10
								IF oheli_goto > 114
									oheli_goto = 114
								ENDIF
								//ACTIVATE_HELI_SPEED_CHEAT oheli 1
					   			HELI_GOTO_COORDS oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 5.0 352.0		
							ENDIF
						ENDIF
					ENDIF

					// make heli lower a bit
					IF oheli_flag = 3
						HELI_GOTO_COORDS oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 1.0 343.0
						oheli_flag++
					ENDIF

					// wait for heli to lower
					IF oheli_flag = 4
						IF LOCATE_CAR_3D oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] pcar_path_z[oheli_goto] 1.5 1.5 5.0 FALSE
							oheli_flag++
						ENDIF 
					ENDIF
					
					// drop rock
					IF oheli_flag = 5
						DETACH_OBJECT rock[oheli_rock]	0.0 0.0 0.0 FALSE
//						IF IS_OBJECT_INTERSECTING_WORLD rock[oheli_rock]
//							GET_OBJECT_COORDINATES rock[oheli_rock] x y z
//							GET_OBJECT_HEADING rock[oheli_rock] heading
//							z += 1.0
//							SET_OBJECT_COORDINATES rock[oheli_rock] x y z
//							SET_OBJECT_HEADING rock[oheli_rock] heading
//						ENDIF
						SET_OBJECT_COLLISION omagnet TRUE
//						ATTACH_OBJECT_TO_CAR rock[oheli_rock] oheli 0.0 0.0 -0.5 0.0 0.0 0.0 
//						DETACH_OBJECT rock[oheli_rock]	0.0 0.0 0.0 FALSE
						//RELEASE_ENTITY_FROM_WINCH oheli
						oheli_flag++
					ENDIF

					// return to base
					IF oheli_flag = 6
						HELI_GOTO_COORDS oheli -1131.6940 1041.7906 1345.1413 5.0 350.0
						oheli_flag++
					ENDIF
					
					// wait for heli to get back to base, or go offscreen
					IF oheli_flag = 7
						IF LOCATE_CAR_2D oheli -1131.6940 1041.7906 4.0 4.0 FALSE
							oheli_flag = 0
						ENDIF
//						IF NOT LOCATE_CAR_2D oheli pcar_path_x[oheli_goto] pcar_path_y[oheli_goto] 20.0 20.0 FALSE
//						AND NOT IS_CAR_ON_SCREEN oheli
//							oheli_flag = 0		
//						ENDIF
					ENDIF

				ELSE
					IF NOT oheli_flag = 99
						MARK_CAR_AS_NO_LONGER_NEEDED oheli
						REMOVE_BLIP oheli_blip
						oheli_flag = 99
					ENDIF
				ENDIF

				// check if tank is being hit by rock ------------------------------------------------------
				temp_int = 0
				WHILE temp_int < 3
					IF NOT IS_CAR_DEAD otank[temp_int]
						temp_int2 = 0
						WHILE temp_int2 < 10
							IF DOES_OBJECT_EXIST rock[temp_int2]
								IF IS_VEHICLE_TOUCHING_OBJECT otank[temp_int] rock[temp_int2]
									GET_OBJECT_SPEED rock[temp_int2] temp_float
									IF temp_float > 5.0
										GET_CAR_HEALTH otank[temp_int] temp_int3
										temp_int3 += -200
										IF temp_int3 <= 0
											temp_int3 = 1
										ENDIF
										SET_CAR_HEALTH otank[temp_int] temp_int3
									ENDIF
									IF heli_is_carrying = 1
										IF carried_object = rock[temp_int2]
//											IF NOT IS_CAR_DEAD pheli 
//												RELEASE_ENTITY_FROM_WINCH pheli
//											ENDIF
											DETACH_OBJECT carried_object 0.0 0.0 0.0 FALSE
//											IF IS_OBJECT_INTERSECTING_WORLD carried_object
//												GET_OBJECT_COORDINATES carried_object x y z
//												GET_OBJECT_HEADING carried_object heading
//												z += 1.0
//												SET_OBJECT_COORDINATES carried_object x y z
//												SET_OBJECT_HEADING carried_object heading
//											ENDIF
											SET_OBJECT_COLLISION pmagnet TRUE
											heli_is_carrying = 0
											pheli_pickup_timer = 0
										ENDIF	
									ENDIF
								ENDIF
							ENDIF
						temp_int2++
						ENDWHILE
					ENDIF
				temp_int++
				ENDWHILE


				// baddie tank ai --------------------------------------------------------------------------
				temp_int2 = 0
				WHILE temp_int2 < 3
					this_tank = temp_int2
					GOSUB update_tank_ai
				temp_int2++
				ENDWHILE

			
				// mission pass or fail --------------------------------------------------------------------
				// if car gets to other side mission passed 
				//IF pcar_progress = 114
				IF NOT IS_CAR_DEAD pcar
					IF LOCATE_CAR_3D pcar -1131.6 1057.7 1345.7 2.5 2.5 2.0 FALSE
						GOSUB z4_mission_pass_audio
						m_passed = 1
					ENDIF
				ENDIF

				// Fail level 
				IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
					GOSUB z4_mission_fail_audio
					m_failed = 1
				ENDIF
				// if run out of lives then mission fail
				IF pcar_lives_left = 0
					GOSUB z4_mission_fail_audio
					PRINT_NOW Z4_M15 5000 1
					m_failed = 1
				ENDIF
				IF pheli_lives = 0
					GOSUB z4_mission_fail_audio
					PRINT_NOW Z4_M13 5000 1 
					m_failed = 1
				ENDIF

				// if run out of time mission fail
				IF zero4_timer <= 0
					GOSUB z4_mission_fail_audio
					PRINT_NOW Z4_M12 5000 1
					m_failed = 1 
				ENDIF
			
			ENDIF

 		ENDIF	
		
		// exit
		IF m_goals = 99
			GOSUB ZERO4_next_stage
		ENDIF

	ENDIF 

// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************





//  end of main loop *** don't change ***
end_of_main_loop_ZERO4:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0	
			GOTO mission_loop_ZERO4 
		ELSE
			GOSUB mission_passed_ZERO4
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_ZERO4
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF



z4_mission_fail_audio:

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	CLEAR_PRINTS
	CLEAR_HELP
	CLEAR_ONSCREEN_TIMER zero4_timer
	CLEAR_ONSCREEN_COUNTER z4_pcar_health

	WAIT 100

	GENERATE_RANDOM_INT_IN_RANGE 0 3 dialogue_flag

	SWITCH	dialogue_flag
		CASE 0
			$audio_string    = &ZER4_NA	
			audio_sound_file = SOUND_ZER4_NA
		BREAK
		CASE 1
			$audio_string    = &ZER4_NB	
			audio_sound_file = SOUND_ZER4_NB
		BREAK
		CASE 2
			$audio_string    = &ZER4_NC	
			audio_sound_file = SOUND_ZER4_NC
		BREAK
	ENDSWITCH
	START_NEW_SCRIPT audio_line -1 0 1 1 0

	WAIT 100

	WHILE audio_line_is_active = 1
		WAIT 0
	ENDWHILE

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

RETURN

LVAR_INT pass_audio_int
z4_mission_pass_audio:


	CLEAR_ONSCREEN_TIMER zero4_timer
	CLEAR_ONSCREEN_COUNTER z4_pcar_health



	dialogue_flag = 0

	WHILE pass_audio_int = 0 
	WAIT 0
		IF TIMERB > 1000
		AND audio_line_is_active = 0
			SWITCH dialogue_flag
				CASE 0
					$audio_string    = &ZER4_OA	
					audio_sound_file = SOUND_ZER4_OA
				BREAK
				CASE 1
					$audio_string    = &ZER4_OB	
					audio_sound_file = SOUND_ZER4_OB
				BREAK
				CASE 2
					$audio_string    = &ZER4_OC	
					audio_sound_file = SOUND_ZER4_OC
				BREAK
				CASE 3
					$audio_string    = &ZER4_OD	
					audio_sound_file = SOUND_ZER4_OD
				BREAK
				CASE 4
					$audio_string    = &ZER4_OE	
					audio_sound_file = SOUND_ZER4_OE
				BREAK
				CASE 5
					$audio_string    = &ZER4_OF	
					audio_sound_file = SOUND_ZER4_OF
				BREAK
				CASE 6
					$audio_string    = &ZER4_OG	
					audio_sound_file = SOUND_ZER4_OG
				BREAK
				CASE 7
					pass_audio_int = 1
				BREAK
			ENDSWITCH
			IF pass_audio_int = 0
				START_NEW_SCRIPT audio_line -1 0 1 1 0
				TIMERB = 0
				dialogue_flag++
			ENDIF
		ENDIF
	ENDWHILE

RETURN

// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


LVAR_INT this_tank
LVAR_FLOAT bullet_start_x bullet_start_y bullet_start_z
LVAR_FLOAT bullet_end_x bullet_end_y bullet_end_z
LVAR_FLOAT vec
update_tank_AI:

	IF NOT IS_CAR_DEAD otank[this_tank]

		// if tank doesn't have a target, look for one
		IF otank_target[this_tank] = 0
			
			IF m_frame_num = this_tank

				// check pcar
				IF NOT IS_CAR_DEAD pcar
					GET_CAR_COORDINATES pcar x y z
					IF LOCATE_CAR_3D otank[this_tank] x y z 10.0 10.0 3.0 FALSE
						otank_target[this_tank] = pcar
					ENDIF
				ENDIF

//				// heli
//				IF otank_target[this_tank] = 0
//					IF NOT IS_CAR_DEAD pheli
//						GET_CAR_COORDINATES pheli x y z
//						IF LOCATE_CAR_3D otank[this_tank] x y z 8.0 8.0 3.0	FALSE
//							otank_target[this_tank] = pheli
//						ENDIF
//					ENDIF
//				ENDIF

				// if still no target move to position tank is supposed to sit at
				IF NOT otank_flag[this_tank] = 99 
					IF otank_target[this_tank] = 0
						
						// we're not at position, so lets go there
						IF otank_flag[this_tank] = 0
							temp_int = otank_goto[this_tank]
							IF this_tank = 0
								CAR_GOTO_COORDINATES_ACCURATE otank[this_tank] otank_route1_x[temp_int] otank_route1_y[temp_int] otank_route1_z[temp_int]							
							ENDIF
							IF this_tank = 1
								CAR_GOTO_COORDINATES_ACCURATE otank[this_tank] otank_route2_x[temp_int] otank_route2_y[temp_int] otank_route2_z[temp_int]							
							ENDIF	
							IF this_tank = 2
								CAR_GOTO_COORDINATES_ACCURATE otank[this_tank] otank_route3_x[temp_int] otank_route3_y[temp_int] otank_route3_z[temp_int]							
							ENDIF
							otank_flag[this_tank]++			
						ENDIF

						// wait for tank to reach point in route
						IF otank_flag[this_tank] = 1
							temp_int = otank_goto[this_tank]
							IF this_tank = 0
								IF LOCATE_CAR_3D otank[this_tank] otank_route1_x[temp_int] otank_route1_y[temp_int] otank_route1_z[temp_int] 2.0 2.0 1.0 FALSE							
									otank_goto[this_tank]++
									otank_flag[this_tank] = 0	
								ENDIF
							ENDIF
							IF this_tank = 1
								IF LOCATE_CAR_3D otank[this_tank] otank_route2_x[temp_int] otank_route2_y[temp_int] otank_route2_z[temp_int] 2.0 2.0 1.0 FALSE						
									otank_goto[this_tank]++
									otank_flag[this_tank] = 0	
								ENDIF
							ENDIF	
							IF this_tank = 2
								IF LOCATE_CAR_3D otank[this_tank] otank_route3_x[temp_int] otank_route3_y[temp_int] otank_route3_z[temp_int] 2.0 2.0 1.0 FALSE							
									otank_goto[this_tank]++
									otank_flag[this_tank] = 0
								ENDIF
							ENDIF 
							// check if finished route
							IF otank_goto[this_tank] > 1
								otank_flag[this_tank] = 99
								otank_goto[this_tank] = 0	
							ENDIF
						ENDIF

					ENDIF
				ENDIF

			ENDIF

		ELSE
			IF NOT IS_CAR_DEAD otank_target[this_tank]
				GET_CAR_COORDINATES otank_target[this_tank] x y z
				IF LOCATE_CAR_3D otank[this_tank] x y z 10.0 10.0 3.0 FALSE
					
					//TURN_CAR_TO_FACE_COORD otank[this_tank] x y

					// turn tank towards coords
					GET_CAR_HEADING otank[this_tank] temp_float
					GET_CAR_COORDINATES otank[this_tank] x2 y2 z2
					vec_x = x - x2
					vec_y = y - y2
					GET_HEADING_FROM_VECTOR_2D vec_x vec_y temp_float2
					// find diff
					temp_float3 = temp_float2 - temp_float
					IF temp_float3 > 180.0
						temp_float3 += -360.0
					ENDIF
					IF temp_float3 < -180.0
						temp_float3 += 360.0
					ENDIF
					temp_float3 *= 0.1
					temp_float += temp_float3
					SET_CAR_HEADING otank[this_tank] temp_float

					


					IF otank_timer[this_tank] > 500
						// make sure tank is facing roughly the right direction before it starts firing
						GET_CAR_COORDINATES otank_target[this_tank] x y z
						GET_CAR_COORDINATES otank[this_tank] x2 y2 z2
						vec_x = x - x2
						vec_y = y - y2
						vec_z = z - z2
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS otank[this_tank] 0.0 1.0 0.0 x y z 
						vec2_x = x - x2 
						vec2_y = y - y2	
						vec2_z = z - z2
						GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y vec2_x vec2_y temp_float
						IF temp_float < 5.0
							GET_ANGLE_BETWEEN_2D_VECTORS 1.0 vec_z 1.0 0.0 temp_float
							IF temp_float < 45.0
							  
								// fire shot
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS otank[this_tank] 0.0 1.5 0.35 bullet_start_x bullet_start_y bullet_start_z
								GET_CAR_COORDINATES otank_target[this_tank] x y z
								vec_x  = x - bullet_start_x
								vec_y  = y - bullet_start_y
								vec_z  = z - bullet_start_z
								GET_DISTANCE_BETWEEN_COORDS_3D x y z bullet_start_x bullet_start_y bullet_start_z temp_float
								vec_x /= temp_float
								vec_y /= temp_float
								vec_z /= temp_float

								vec_x *= 10.0 // range of bullet
								vec_y *= 10.0
								vec_z *= 10.0
								
								bullet_end_x = bullet_start_x +	vec_x
								bullet_end_y = bullet_start_y +	vec_y
								bullet_end_z = bullet_start_z +	vec_z

								IF otank_target[this_tank] = pcar
									PRINT_NOW Z4_M19 5000 1 // your bandit is taking damage from a tank
								ENDIF
								REPORT_MISSION_AUDIO_EVENT_AT_CAR otank[this_tank] SOUND_MINITANK_FIRE
								FIRE_SINGLE_BULLET bullet_start_x bullet_start_y bullet_start_z bullet_end_x bullet_end_y bullet_end_z 50
								//LINE bullet_start_x bullet_start_y bullet_start_z bullet_end_x bullet_end_y bullet_end_z
								otank_timer[this_tank] = 0
							ENDIF
						ENDIF
					ENDIF	
				ELSE
					otank_target[this_tank] = 0
				ENDIF
			ELSE
				otank_target[this_tank] = 0					  
			ENDIF											  
		ENDIF												  
															  
	ELSE
//		IF respawned_tanks < 2
//			// respawn new tank
//			otank_flag[this_tank] = 0 	
//			otank_target[this_tank] = 0
//			otank_goto[this_tank] = 0
//			MARK_CAR_AS_NO_LONGER_NEEDED otank[this_tank]
//			REMOVE_BLIP otank_blip[this_tank]
//			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -1131.5034 1028.8577 1344.8498 2.0 2.0 1.0 
//				CREATE_CAR RCTIGER -1131.5034 1028.8577 344.8498	otank[this_tank]
//				SET_CAR_HEADING otank[this_tank] 270.0
//				ADD_BLIP_FOR_CAR otank[this_tank] otank_blip[this_tank]
//				CHANGE_BLIP_DISPLAY otank_blip[this_tank] BLIP_ONLY
//				CHANGE_BLIP_SCALE otank_blip[this_tank] 2
//				SET_VEHICLE_AREA_VISIBLE otank[this_tank] 10
//				respawned_tanks++
//			ENDIF
//		ELSE
			IF DOES_BLIP_EXIST otank_blip[this_tank]
				REMOVE_BLIP otank_blip[this_tank]
				otank_timer[this_tank] = 0
			ENDIF
			MARK_CAR_AS_NO_LONGER_NEEDED otank[this_tank]
////		ENDIF
	ENDIF

RETURN




// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
	ZERO4_next_stage:
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
mission_failed_ZERO4:

	PRINT_BIG M_FAIL 5000 1
//
//	CLEAR_ONSCREEN_TIMER zero4_timer
//	CLEAR_ONSCREEN_COUNTER z4_pcar_health
//
	WAIT 5000

RETURN

// PASS
mission_passed_ZERO4:
//	CLEAR_ONSCREEN_TIMER zero4_timer
//	CLEAR_ONSCREEN_COUNTER z4_pcar_health
//	PRINT_WITH_NUMBER_BIG ( M_PASS ) 7000 5000 1 //"Mission Passed!"
//	REGISTER_MISSION_PASSED ZERO_4
//	ADD_SCORE player1 7000
//	CLEAR_WANTED_LEVEL player1
//	REMOVE_BLIP zero_contact_blip
//	flag_zero_mission_counter++
RETURN

// CLEANUP
mission_cleanup_ZERO4:

	SET_PLAYER_CONTROL player1 OFF	
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	LOAD_SCENE -2246.9832 136.9301 34.3125
	REQUEST_COLLISION -2246.9832 136.9301
	
	
	SET_CHAR_AREA_VISIBLE scplayer 0
	SET_AREA_VISIBLE 0
	SET_PLAYER_CONTROL player1 ON
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	SET_CHAR_COORDINATES scplayer -2244.6694 132.1672 34.3203  
	SET_CHAR_HEADING scplayer 92.1110
	WHILE IS_CHAR_WAITING_FOR_WORLD_COLLISION scplayer
		WAIT 0
	ENDWHILE
	SET_CHAR_COORDINATES scplayer -2244.6694 132.1672 34.3203  
	SET_CHAR_HEADING scplayer 92.1110	
	SET_CAMERA_BEHIND_PLAYER 
	RESTORE_CAMERA_JUMPCUT
	LOAD_SCENE -2244.6694 132.1672 34.3203

	SET_PLAYER_IS_IN_STADIUM FALSE
	HIDE_ALL_FRONTEND_BLIPS	FALSE

	// delete all the cars and shit
	DELETE_CAR pcar
	DELETE_CAR hidden_car
	DELETE_CAR pheli
	DELETE_CAR oheli
	DELETE_CAR otank[0]
	DELETE_CAR otank[1]
	DELETE_CAR otank[2]

	temp_int = 0
	WHILE temp_int < 10
		DELETE_OBJECT rock[temp_int]
	temp_int++
	ENDWHILE
	temp_int = 0
	WHILE temp_int < 5
		DELETE_OBJECT plank[temp_int]
	temp_int++
	ENDWHILE
	
	DELETE_OBJECT rc_bomb

	DISPLAY_HUD TRUE
	CLEAR_ONSCREEN_TIMER zero4_timer
	CLEAR_ONSCREEN_COUNTER z4_pcar_health
		
	MARK_MODEL_AS_NO_LONGER_NEEDED RCBANDIT
	MARK_MODEL_AS_NO_LONGER_NEEDED RCTIGER
	MARK_MODEL_AS_NO_LONGER_NEEDED RCGOBLIN
	MARK_MODEL_AS_NO_LONGER_NEEDED BMX

	REMOVE_BLIP pcar_blip
	REMOVE_BLIP oheli_blip
	REMOVE_BLIP otank_blip[0]
	REMOVE_BLIP otank_blip[1]	
	REMOVE_BLIP otank_blip[2]		

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0
	SET_RADAR_ZOOM 0

	RESTORE_CLOCK
	RELEASE_WEATHER

	//WAIT 2000


	// filshie mission passed stuff
	IF m_passed = 1


		CREATE_PROTECTION_PICKUP -2243.0068 136.9601 34.8203 zero_revenue zero_revenue zero_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		LOAD_SCENE -2254.8716 133.2548 38.7597
		SET_FIXED_CAMERA_POSITION -2254.8716 133.2548 38.7597 0.0 0.0 0.0 //cut scene of building
		POINT_CAMERA_AT_POINT -2253.8992 133.2602 38.5270 JUMP_CUT

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		PLAY_MISSION_PASSED_TUNE 1
		PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

		WAIT 5000
		SET_FIXED_CAMERA_POSITION -2246.7866 136.6037 36.2636 0.0 0.0 0.0 //cut scene showing pickup
		POINT_CAMERA_AT_POINT -2245.8250 136.6010 35.9893 JUMP_CUT
		PRINT_WITH_NUMBER_NOW ASS_LUV zero_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

		WAIT 6000
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

	ENDIF

	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER 
	RESTORE_CAMERA_JUMPCUT

	CLEAR_PRINTS
	REMOVE_RC_BUGGY
//	DO_FADE 500 FADE_IN
//	WHILE GET_FADING_STATUS 
//		WAIT 0
//	ENDWHILE
	

	// if mission was passed 
	IF m_passed = 1
		PRINT_WITH_NUMBER_BIG ( M_PASS ) 7000 5000 1 //"Mission Passed!"
		REGISTER_MISSION_PASSED ZERO_4
		PLAYER_MADE_PROGRESS 1
		ADD_SCORE player1 7000
		PLAY_MISSION_PASSED_TUNE 1
		CLEAR_WANTED_LEVEL player1
		REMOVE_BLIP zero_contact_blip
		flag_zero_mission_counter++
	ENDIF


	GET_GAME_TIMER timer_mobile_start

	SET_PLAYER_CONTROL player1 ON

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
}






