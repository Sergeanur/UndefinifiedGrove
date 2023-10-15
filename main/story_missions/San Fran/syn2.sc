MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION MISSNAM : Blonde Ambition (syn2.sc)
//			  		 AUTHOR : Neil	
//			   DESICRIPTION : Meet up with jizzy and do some pimping
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************
SCRIPT_NAME SYN2
GOSUB mission_start_SYN2
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_SYN2
ENDIF
GOSUB mission_cleanup_SYN2
MISSION_END

mission_start_SYN2:
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed
LVAR_INT m_this_frame_time
LVAR_INT m_last_frame_time
LVAR_INT m_time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT vec_x vec_y vec_z
LVAR_INT temp_seq
LVAR_INT dialogue_timer

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0

mission_loop_SYN2:

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	m_passed = 1
ENDIF


// timer
LVAR_INT hippy_timer
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time



get_back_in_car_reminder_timer += m_time_diff
convoy_stopped_timer += m_time_diff
rich_backup_timer += m_time_diff
dialogue_timer += m_time_diff
//rich_chase_car_timer[0]	+= m_time_diff
//rich_chase_car_timer[1]	+= m_time_diff
//rich_chase_car_timer[2]	+= m_time_diff
//rich_chase_car_timer[3]	+= m_time_diff
rich_car_timer += m_time_diff
suspicion_timer += m_time_diff
hippy_timer += m_time_diff

damaged_preacher_timer += m_time_diff


WAIT 0


	GOSUB SYN2_debug_shit

	SWITCH m_stage
		CASE 0
			GOSUB SYN2_m_stage_0
		BREAK
		CASE 1
			GOSUB SYN2_m_stage_1
		BREAK
		CASE 2
			GOSUB SYN2_m_stage_2
		BREAK
		CASE 3
			GOSUB SYN2_m_stage_3
		BREAK
		CASE 4
			GOSUB SYN2_m_stage_4
		BREAK
		CASE 5
			GOSUB SYN2_m_stage_5
		BREAK
		CASE 6
			GOSUB SYN2_m_stage_6
		BREAK
		CASE 7
			GOSUB SYN2_m_stage_7
		BREAK
		CASE 8
			GOSUB SYN2_m_stage_8
		BREAK
	ENDSWITCH

	GOSUB SYN2_GLOBAL_SHIT

//	temp_int = 0
//	temp_int2 = 1
//	WHILE temp_int2 < 38
//		LINE convoy_route_x[temp_int] convoy_route_y[temp_int] convoy_route_z[temp_int] convoy_route_x[temp_int2] convoy_route_y[temp_int2] convoy_route_z[temp_int2] 
//		IF IS_POINT_ON_SCREEN convoy_route_x[temp_int2] convoy_route_y[temp_int2] convoy_route_z[temp_int2] 5.0 
//			DRAW_CORONA  convoy_route_x[temp_int] convoy_route_y[temp_int] convoy_route_z[temp_int]	1.0 CORONATYPE_MOON FLARETYPE_NONE 255 0 0
//   		ENDIF
//   	temp_int++
//	temp_int2++
//	ENDWHILE


// end of main loop
IF m_failed = 0
	IF m_passed = 0																 
		GOTO mission_loop_SYN2 
	ELSE
		GOSUB mission_passed_SYN2
		RETURN
	ENDIF
ELSE
	GOSUB mission_failed_SYN2
	RETURN
ENDIF



SYN2_debug_shit:

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
		VAR_FLOAT syn2_view_debug_f[8]
		VAR_INT syn2_view_debug[8]
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
			CLEAR_ALL_VIEW_VARIABLES
			display_debug++
			IF display_debug > 3
				display_debug = 0
			ENDIF
		ENDIF
		IF display_debug = 1
			syn2_view_debug[0] = m_stage
			syn2_view_debug[1] = m_goals
			syn2_view_debug[2] = convoy_progress
			syn2_view_debug[3] = TIMERA
			syn2_view_debug[4] = on_cutscene_duty
			syn2_view_debug[5] = audio_line_is_active
			syn2_view_debug[6] = dialogue_timer
			syn2_view_debug[7] = dialogue_flag
			VIEW_INTEGER_VARIABLE syn2_view_debug[0] m_stage
			VIEW_INTEGER_VARIABLE syn2_view_debug[1] m_goals
			VIEW_INTEGER_VARIABLE syn2_view_debug[2] convoy_progress 
			VIEW_INTEGER_VARIABLE syn2_view_debug[3] TIMERA
			VIEW_INTEGER_VARIABLE syn2_view_debug[4] on_cutscene_duty
			VIEW_INTEGER_VARIABLE syn2_view_debug[5] audio_line_is_active
			VIEW_INTEGER_VARIABLE syn2_view_debug[6] dialogue_timer
			VIEW_INTEGER_VARIABLE syn2_view_debug[7] dialogue_flag
		ENDIF

		IF display_debug = 2
			syn2_view_debug[0] = syn2_mission_attempts
			syn2_view_debug[1] = this_Car_health
			syn2_view_debug[2] = last_car_health
			syn2_view_debug[3] = player_has_attacked_convoy
			syn2_view_debug[4] = syn2_time_until_backup
			syn2_view_debug[5] = TIMERB
//			syn2_view_debug[6] = backup_call_dialogue
			//syn2_view_debug[7] = drop_phone_text
			VIEW_INTEGER_VARIABLE syn2_view_debug[0] syn2_mission_attempts
			VIEW_INTEGER_VARIABLE syn2_view_debug[1] this_Car_health
			VIEW_INTEGER_VARIABLE syn2_view_debug[2] last_car_health
			VIEW_INTEGER_VARIABLE syn2_view_debug[3] player_has_attacked_convoy
			VIEW_INTEGER_VARIABLE syn2_view_debug[4] syn2_time_until_backup
			VIEW_INTEGER_VARIABLE syn2_view_debug[5] TIMERB
//			VIEW_INTEGER_VARIABLE syn2_view_debug[6] backup_call_dialogue
			//VIEW_INTEGER_VARIABLE syn2_view_debug[7] drop_phone_text
		ENDIF

		IF display_debug = 3
			syn2_view_debug[0] = rich_backup_timer
			//syn2_view_debug[1] = car_has_been_rammed
//			syn2_view_debug[2] = mib_car_task[0]
//			syn2_view_debug[3] = mib_car_task[1]
//			syn2_view_debug[4] = mib_car_task[2]
			syn2_view_debug_f[5] = limo_playback_speed
			//syn2_view_debug_f[6] = mib_car_playback_speed[1]
	//		syn2_view_debug[7] = 
			VIEW_INTEGER_VARIABLE syn2_view_debug[0] rich_backup_timer
			//VIEW_INTEGER_VARIABLE syn2_view_debug[1] car_has_been_rammed
//			VIEW_INTEGER_VARIABLE syn2_view_debug[2] mib_car_task[0]
//			VIEW_INTEGER_VARIABLE syn2_view_debug[3] mib_car_task[1]
//			VIEW_INTEGER_VARIABLE syn2_view_debug[4] mib_car_task[2]
			VIEW_FLOAT_VARIABLE syn2_view_debug_f[5] limo_playback_speed
			//VIEW_FLOAT_VARIABLE syn2_view_debug_f[6] mib_car_playback_speed[1]
	//		VIEW_INTEGER_VARIABLE syn2_view_debug[7] 
		ENDIF


	ENDIF

RETURN


// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
SYN2_m_stage_0:
		
	// Specific mission variables		

	// Fake create
	IF m_goals = -1		
		LVAR_INT jizzy
		LVAR_INT jizzy_car
		//VAR_INT syn2_mission_attempts // global variable - moved to initial.sc

		// blips
		LVAR_INT location_blip
		LVAR_INT jizzy_car_blip
			LVAR_INT hippy1_blip
		LVAR_INT hippy2_blip
		LVAR_INT rival_pimp_blip
			
		LVAR_INT text_flag clear_text_flag
		LVAR_INT girl1 
		LVAR_INT girl2 
		LVAR_INT girl3
		LVAR_INT girl2_dm
		LVAR_INT rival_pimp rival_pimp_dm

		LVAR_INT get_back_in_car_reminder_timer

		VAR_INT  syn2_timer

		LVAR_INT hippy_van
		LVAR_INT hippy_1 hippy_2

		LVAR_INT rival_pimp_car
		LVAR_INT girl_is_with_rival
		LVAR_INT fix_rival_pimp_health
		LVAR_INT convoy_stopped_timer
		LVAR_INT rich_backup_timer
		LVAR_INT rich_car_health
		LVAR_INT next_rich_car_stop_health
		LVAR_INT pimp_attack_player

		LVAR_INT jizzy_phone

		VAR_INT syn2_time_until_backup
		LVAR_INT pimp_is_waiting
		LVAR_INT rich_car_timer

		LVAR_INT girl3_flag
		LVAR_INT suspicion_timer

		LVAR_INT phone_ringing


	ENDIF

	// Initialise any variables
	IF m_goals = 0
		LVAR_FLOAT convoy_route_x[38] convoy_route_y[38] convoy_route_z[38]
		LVAR_INT convoy_progress
		convoy_progress = 0

		convoy_route_X[0] = -1863.8798 
		convoy_route_Y[0] = 727.8510 	
		convoy_route_Z[0] = 44.0619		

		convoy_route_X[1] = -1816.7933 
		convoy_route_Y[1] = 727.1199 
		convoy_route_Z[1] = 36.4934 

		convoy_route_X[2] = -1717.4587 
		convoy_route_Y[2] = 729.5344 
		convoy_route_Z[2] = 24.6517 

		convoy_route_X[3] = -1709.8418 
		convoy_route_Y[3] = 829.3579 
		convoy_route_Z[3] = 24.6505 

		convoy_route_X[4] = -1663.0173 
		convoy_route_Y[4] = 917.9272 
		convoy_route_Z[4] = 24.5226 

		convoy_route_X[5] = -1563.8864 
		convoy_route_Y[5] = 916.7482 
		convoy_route_Z[5] = 6.9176 

		convoy_route_X[6] = -1573.6129 
		convoy_route_Y[6] = 1016.9177 
		convoy_route_Z[6] = 6.9562 

		convoy_route_X[7] = -1585.4546 
		convoy_route_Y[7] = 1116.8708 
		convoy_route_Z[7] = 6.9488 

		convoy_route_X[8] = -1608.5723 
		convoy_route_Y[8] = 1214.6409 
		convoy_route_Z[8] = 6.9488 

		convoy_route_X[9] = -1678.0173 
		convoy_route_Y[9] = 1287.1173 
		convoy_route_Z[9] = 6.9487 

		convoy_route_X[10] = -1752.3992 
		convoy_route_Y[10] = 1354.9885 
		convoy_route_Z[10] = 6.9473 

		convoy_route_X[11] = -1852.1379 
		convoy_route_Y[11] = 1364.5398 
		convoy_route_Z[11] = 6.9488 

		convoy_route_X[12] = -1938.2400 
		convoy_route_Y[12] = 1312.3474 
		convoy_route_Z[12] = 6.9538 

		convoy_route_X[13] = -2037.6919 
		convoy_route_Y[13] = 1298.7739 
		convoy_route_Z[13] = 7.1712 

		convoy_route_X[14] = -2135.2407 
		convoy_route_Y[14] = 1279.3173 
		convoy_route_Z[14] = 22.4081 

		convoy_route_X[15] = -2234.5024 
		convoy_route_Y[15] = 1276.2395 
		convoy_route_Z[15] = 39.3992 

		convoy_route_X[16] = -2265.7432 
		convoy_route_Y[16] = 1182.4407 
		convoy_route_Z[16] = 55.4933 

		convoy_route_X[17] = -2266.2886 
		convoy_route_Y[17] = 1085.1447 
		convoy_route_Z[17] = 79.7480 

		convoy_route_X[18] = -2267.6042 
		convoy_route_Y[18] = 984.6959 
		convoy_route_Z[18] = 73.0856 

		convoy_route_X[19] = -2268.9946 
		convoy_route_Y[19] = 884.5173 
		convoy_route_Z[19] = 66.4014 

		convoy_route_X[20] = -2333.2935 
		convoy_route_Y[20] = 811.5989 
		convoy_route_Z[20] = 42.8954 

		convoy_route_X[21] = -2390.3655 
		convoy_route_Y[21] = 729.2283 
		convoy_route_Z[21] = 34.9301 

		convoy_route_X[22] = -2489.3591 
		convoy_route_Y[22] = 711.0146 
		convoy_route_Z[22] = 34.9302 

		convoy_route_X[23] = -2589.5073 
		convoy_route_Y[23] = 712.4084 
		convoy_route_Z[23] = 27.7727 

		convoy_route_X[24] = -2689.8579 
		convoy_route_Y[24] = 710.8961 
		convoy_route_Z[24] = 32.4395 

		convoy_route_X[25] = -2753.0542 
		convoy_route_Y[25] = 633.0150 
		convoy_route_Z[25] = 27.6617 

		convoy_route_X[26] = -2744.7925 
		convoy_route_Y[26] = 534.2344 
		convoy_route_Z[26] = 11.9062 

		convoy_route_X[27] = -2707.7407 
		convoy_route_Y[27] = 441.3501 
		convoy_route_Z[27] = 4.0892 

		convoy_route_X[28] = -2751.3872 
		convoy_route_Y[28] = 351.2291 
		convoy_route_Z[28] = 4.0969 

		convoy_route_X[29] = -2707.8999 
		convoy_route_Y[29] = 261.1717 
		convoy_route_Z[29] = 4.0968 

		convoy_route_X[30] = -2711.8862 
		convoy_route_Y[30] = 161.0435 
		convoy_route_Z[30] = 4.0971 

		convoy_route_X[31] = -2809.1890 
		convoy_route_Y[31] = 137.7394 
		convoy_route_Z[31] = 6.9555 

		convoy_route_X[32] = -2811.9087 
		convoy_route_Y[32] = 36.8614 
		convoy_route_Z[32] = 6.9488 

		convoy_route_X[33] = -2811.4033 
		convoy_route_Y[33] = -63.2554 
		convoy_route_Z[33] = 6.9488 

		convoy_route_X[34] = -2811.2791 
		convoy_route_Y[34] = -164.2748 
		convoy_route_Z[34] = 6.9381 

		convoy_route_X[35] = -2811.6013 
		convoy_route_Y[35] = -264.5668 
		convoy_route_Z[35] = 6.9488 

		convoy_route_X[36] = -2820.2043 
		convoy_route_Y[36] = -364.2359 
		convoy_route_Z[36] = 6.9488 

		convoy_route_X[37] = -2818.2666 
		convoy_route_Y[37] = -465.0786 
		convoy_route_Z[37] = 6.9941 


		LVAR_FLOAT pimp_route_x[8] pimp_route_y[8] pimp_route_z[8]
		LVAR_INT pimp_route_progress
		pimp_route_x[0] = -2428.2322 
		pimp_route_y[0] = -164.7728 
		pimp_route_z[0] =  34.3085
		pimp_route_x[1] = -2433.5598 
		pimp_route_y[1] = -164.5040 
		pimp_route_z[1] = 34.3446 
		pimp_route_x[2] = -2452.7532 
		pimp_route_y[2] = -165.8061 
		pimp_route_z[2] = 34.3047 
		pimp_route_x[3] = -2469.1365 
		pimp_route_y[3] = -165.8884 
		pimp_route_z[3] = 24.9573 
		pimp_route_x[4] = -2469.6162 
		pimp_route_y[4] = -162.4438 
		pimp_route_z[4] = 24.6236 
		pimp_route_x[5] = -2463.0063 
		pimp_route_y[5] = -162.4879 
		pimp_route_z[5] = 24.9077 
		pimp_route_x[6] = -2460.3743 
		pimp_route_y[6] = -134.7403 
		pimp_route_z[6] = 24.8394 		
		pimp_route_x[7] = -2478.1147 
		pimp_route_y[7] = -129.3914 
		pimp_route_z[7] = 24.6250 

		girl_is_with_rival = 0
		fix_rival_pimp_health = 0
		convoy_stopped_timer = 0
		pimp_attack_player = 0
		pimp_is_waiting = 0
		phone_ringing = 0

	m_goals++				
	ENDIF

	// Load any text / textures	etc.
	IF m_goals = 1

		LOAD_MISSION_TEXT SYN2
		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

	m_goals++
	ENDIF
	
	// Environment Settings	
	IF m_goals = 2
		//SET_PED_DENSITY_MULTIPLIER 1.0
		//SET_CAR_DENSITY_MULTIPLIER 0.3
		//SET_TIME_OF_DAY 23 1
		SET_WANTED_MULTIPLIER 0.5
		SUPPRESS_CAR_MODEL BROADWAY
		SUPPRESS_CAR_MODEL HUSTLER
		m_goals = 99
	ENDIF

	// End of stage
	IF m_goals = 99
		m_stage++
		//m_stage = 6
		m_goals = 0
	ENDIF

RETURN

// *************************************************************************************************************
//						STAGE 1 - Trigger - at garage - play cutscene then mission passed 
// *************************************************************************************************************
SYN2_m_stage_1:
		

	IF m_goals = 0
		// choose whether to show first cutscene or not
		IF NOT syn2_mission_attempts = 0										   
			m_stage++
			m_goals = 0
		ELSE
			m_goals++
		ENDIF
		ENDIF
	
	// play first cutscene
	IF m_goals = 1
		SET_PLAYER_CONTROL player1 OFF
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SET_AREA_VISIBLE 1
		LOAD_CUTSCENE SYND_2A
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
		SET_CHAR_COORDINATES scplayer -2031.2542 161.4080 28.0391
		SET_CHAR_HEADING scplayer 270.0
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SET_PLAYER_CONTROL player1 ON
	m_goals = 99
	ENDIF	

	// mission passed
	IF m_goals = 99
		m_passed = 1

		// also add a pathing blip for jizzy's club
		// trigger this blip through c++.. because there's two missions here and managing the blip between them is a bad recipe
		//ADD_BLIP_FOR_COORD syndX syndY syndZ stage1_pathing_blip
	ENDIF

RETURN 


// *************************************************************************************************************
//						STAGE 2 -  Trigger - at pleasure dome - play 2nd cutscene  
// *************************************************************************************************************
SYN2_m_stage_2:
	// cutscene
	IF m_goals = 0
		SET_PLAYER_CONTROL player1 OFF
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

		SET_AREA_VISIBLE 3
		LOAD_CUTSCENE SYND_2B
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
	m_goals++
	ENDIF
	
	// create girl1 sitting in car
	IF m_goals = 1
		
		REQUEST_MODEL BROADWAY
		REQUEST_MODEL HFYPRO
		LOAD_SPECIAL_CHARACTER 1 JIZZY
		REQUEST_ANIMATION GANGS
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED BROADWAY
		   OR NOT HAS_MODEL_LOADED HFYPRO
		   OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		   OR NOT HAS_ANIMATION_LOADED GANGS
			WAIT 0
		ENDWHILE

		REQUEST_MODEL CELLPHONE
		REQUEST_ANIMATION CAR_CHAT
		WHILE NOT HAS_MODEL_LOADED CELLPHONE
		   OR NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0
		ENDWHILE


		LVAR_INT empty_dm
		LVAR_INT tough_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm

		CLEAR_AREA -2623.3591 1410.7860 6.1629 20.0 TRUE

		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -2623.3591 1410.7860 6.1629 jizzy
		SET_CHAR_HEADING jizzy 192.3576

		SET_CHAR_COORDINATES scplayer -2622.5464 1410.9503 6.1702
		SET_CHAR_HEADING scplayer 199.6292

		CUSTOM_PLATE_FOR_NEXT_CAR BROADWAY HO_2_HO_
		CREATE_CAR BROADWAY -2625.4495 1401.6807 6.0877 jizzy_car
		SET_CAR_HEADING jizzy_car 188.0679
		SET_CAR_HEALTH jizzy_car 3000
		CHANGE_CAR_COLOUR jizzy_car 123 5
		SET_CAR_HYDRAULICS jizzy_car FALSE
		CREATE_CHAR_AS_PASSENGER jizzy_car PEDTYPE_PROSTITUTE HFYPRO 0 girl1
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR girl1 FALSE
		SET_CHAR_CANT_BE_DRAGGED_OUT girl1 TRUE
		SET_CHAR_SUFFERS_CRITICAL_HITS girl1 FALSE
		SET_CHAR_NEVER_TARGETTED girl1 TRUE
		SET_CHAR_DECISION_MAKER girl1 empty_dm
		MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO
		SET_CAN_BURST_CAR_TYRES jizzy_car FALSE

		ADD_BLIP_FOR_CAR jizzy_car jizzy_car_blip	
		SET_BLIP_AS_FRIENDLY jizzy_car_blip TRUE

		SET_NEAR_CLIP 0.1
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION -2622.9182 1410.8909 7.0207 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2622.9207 1409.8911 6.9998 JUMP_CUT
		//LOAD_SCENE -2622.9207 1409.8911 6.9998
		LOAD_SCENE_IN_DIRECTION	-2618.0 1396.0 7.0 211.0

		DO_FADE 1000 FADE_IN		
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		m_goals++

		TIMERB = 0

//			PRINT_NOW SYN2_31 3000 1 // JIZZY - 'There a ho sitting in my car.' 
//			PRINT SYN2_32 3000 1 //JIZZY - 'Take her to the hotel downtown.'
//			PRINT SYN2_33 3000 1 // JIZZY - 'I'll keep in touch on the car phone.'
//			PRINT  SYN2_34 3000 1 //JIZZY - 'And mind the body work.' 

	ENDIF
		
	IF m_goals > 1
	AND m_goals < 7
		IF IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 7
		ENDIF
	ENDIF

	IF m_goals = 2	
		IF NOT IS_CHAR_DEAD jizzy
			TASK_GO_STRAIGHT_TO_COORD jizzy -2623.4216 1404.9100 6.1537 PEDMOVE_WALK 15000
		ENDIF
		TASK_GO_STRAIGHT_TO_COORD scplayer -2622.1274 1405.6326 6.1587 PEDMOVE_WALK	15000
		m_goals++
	ENDIF

	IF m_goals = 3
		IF NOT IS_CHAR_DEAD jizzy
			GET_SCRIPT_TASK_STATUS jizzy TASK_GO_STRAIGHT_TO_COORD temp_int
			IF temp_int = FINISHED_TASK
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF	
	ENDIF

	IF m_goals = 4
		
	//IF TIMERA > 400
		SET_FIXED_CAMERA_POSITION -2621.6973 1403.4926 7.8087 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2622.5742 1403.0470 7.6296 JUMP_CUT
		
		IF NOT IS_CHAR_DEAD jizzy
			SET_CHAR_COORDINATES jizzy -2624.0493 1402.8881 6.1406 
			SET_CHAR_HEADING jizzy 248.4552 
		ENDIF
		SET_CHAR_COORDINATES scplayer -2622.9736 1402.1765 6.1322
		SET_CHAR_HEADING scplayer 86.0103

		
		IF NOT IS_CHAR_DEAD jizzy
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PLAY_ANIM -1 prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkG GANGS 4.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE TRUE -1
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK jizzy temp_Seq
			CLEAR_SEQUENCE_TASK temp_Seq
		ENDIF

		TIMERA = 0
		m_goals++
	//ENDIF

		
	ENDIF

	IF m_goals = 5
		IF dialogue_flag > 6
			IF NOT IS_CHAR_DEAD jizzy
				TASK_GO_STRAIGHT_TO_COORD jizzy -2623.3591 1410.7860 6.1629 PEDMOVE_WALK 15000
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 6
		IF TIMERA > 1500
		AND dialogue_flag > 6
			m_goals++
		ENDIF
	ENDIF

	IF m_goals > 1
	AND m_goals < 7
		IF audio_line_is_active = 0 
			IF dialogue_timer > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_BA					  
						audio_sound_file = SOUND_SYN2_BA
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_BB					  
						audio_sound_file = SOUND_SYN2_BB
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_BC					  
						audio_sound_file = SOUND_SYN2_BC
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_BD					  
						audio_sound_file = SOUND_SYN2_BD
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_BE					  
						audio_sound_file = SOUND_SYN2_BE
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_BF					  
						audio_sound_file = SOUND_SYN2_BF
						START_NEW_SCRIPT audio_line jizzy 0 1 1 0
						dialogue_flag++
					BREAK
					CASE 6
						dialogue_flag++
					BREAK
				ENDSWITCH
				dialogue_timer = 0
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 7			
	
		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

		CLEAR_PRINTS
		IF NOT IS_CHAR_DEAD jizzy
			DELETE_CHAR jizzy
		ENDIF
			
		REMOVE_ANIMATION GANGS
		UNLOAD_SPECIAL_CHARACTER 1
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
		SET_CHAR_COORDINATES scplayer -2622.9736 1402.1765 6.1322
		SET_CHAR_HEADING scplayer 86.0103

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

		m_goals = 99
	ENDIF



	// exit
	IF m_goals = 99
		m_goals = 0
		m_stage++
	ENDIF

RETURN 

// *************************************************************************************************************
//						STAGE 3 -  Take girl to hotel  
// *************************************************************************************************************
SYN2_m_stage_3:

	IF m_goals = -1
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
	ENDIF

	// initialisation
	IF m_goals = 0
		
		text_flag = 0
		clear_text_flag = 0
		syn2_timer = 540000
		//DISPLAY_ONSCREEN_TIMER_WITH_STRING syn2_timer TIMER_DOWN SYN_T_1
		
		PRINT_NOW SYN2_37 7000 1 // get in the car with the ho. 

		TIMERA = 0	
		m_goals++
	
	ENDIF 

	// wait for player to get in car with jizzy and girl
	IF m_goals = 1
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CHAR_IN_CAR scplayer jizzy_car
				REMOVE_BLIP jizzy_car_blip						
				ADD_BLIP_FOR_COORD -1929.9542 724.0631 44.3047 location_blip
				PRINT_NOW SYN2_01 7000 1 // drive jizzy's girl down town
				REQUEST_MODEL MICRO_UZI
				WHILE NOT HAS_MODEL_LOADED MICRO_UZI
					WAIT 0
				ENDWHILE
				ENSURE_PLAYER_HAS_DRIVE_BY_WEAPON player1 250
				MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
				on_cutscene_duty = -1
				TIMERA = 0
				text_flag = 0
				m_goals++
			ENDIF
		ENDIF

		IF TIMERA > 60000
			PRINT_NOW SYN2_37 7000 1 // get in the car with the ho.
			TIMERA = 0	
		ENDIF

	ENDIF

	IF m_goals > 1
		IF DOES_BLIP_EXIST jizzy_car_blip
			IF DOES_BLIP_EXIST location_blip
				REMOVE_BLIP location_blip
			ENDIF
		ELSE
			IF NOT DOES_BLIP_EXIST location_blip
				ADD_BLIP_FOR_COORD -1929.9542 724.0631 44.3047 location_blip
				CHANGE_BLIP_COLOUR location_blip YELLOW
			ENDIF
		ENDIF
	ENDIF
	
	// wait for player to arrive
	IF m_goals = 2
		IF DOES_BLIP_EXIST location_blip
			IF NOT IS_CAR_DEAD jizzy_car
				IF LOCATE_CAR_3D jizzy_car -1929.9542 724.0631 44.3047 4.0 4.0 4.0 TRUE
					IF IS_CHAR_IN_CAR scplayer jizzy_car
						IF IS_VEHICLE_ON_ALL_WHEELS jizzy_car
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
							SET_PLAYER_CONTROL player1 OFF
		
							REMOVE_BLIP location_blip
							m_goals++
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				SET_CHAR_COORDINATES scplayer -1929.9542 724.0631 44.3047
			ENDIF
		ENDIF


		IF text_flag < 8
			IF NOT IS_MESSAGE_BEING_DISPLAYED
				IF NOT IS_CHAR_DEAD girl1
					IF NOT IS_CAR_DEAD jizzy_car
						IF IS_CHAR_IN_CAR scplayer jizzy_car
							IF audio_line_is_active = 0 
								IF dialogue_timer > 1000
									SWITCH text_flag
										CASE 0
											$audio_string    = &SYN2_LM					  
											audio_sound_file = SOUND_SYN2_LM
											START_NEW_SCRIPT audio_line girl1 0 1 1 0
											dialogue_timer = -4000
											text_flag++
										BREAK
										CASE 1
											$audio_string    = &SYN2_LN					  
											audio_sound_file = SOUND_SYN2_LN
											START_NEW_SCRIPT audio_line girl1 0 1 1 0
											dialogue_timer = -2000		
											text_flag++						
										BREAK
										CASE 2
											$audio_string    = &SYN2_LO					  
											audio_sound_file = SOUND_SYN2_LO
											START_NEW_SCRIPT audio_line scplayer 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
										CASE 3
											$audio_string    = &SYN2_LP					  
											audio_sound_file = SOUND_SYN2_LP
											START_NEW_SCRIPT audio_line girl1 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
										CASE 4
											$audio_string    = &SYN2_LQ					  
											audio_sound_file = SOUND_SYN2_LQ
											START_NEW_SCRIPT audio_line scplayer 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
										CASE 5
											$audio_string    = &SYN2_LR					  
											audio_sound_file = SOUND_SYN2_LR
											START_NEW_SCRIPT audio_line girl1 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
										CASE 6
											$audio_string    = &SYN2_LS					  
											audio_sound_file = SOUND_SYN2_LS
											START_NEW_SCRIPT audio_line scplayer 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
										CASE 7
											$audio_string    = &SYN2_LT					  
											audio_sound_file = SOUND_SYN2_LT
											START_NEW_SCRIPT audio_line girl1 0 1 1 0
											dialogue_timer = 0
											text_flag++
										BREAK
									ENDSWITCH
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF on_cutscene_duty = -1
			IF TIMERA > 7000
				on_cutscene_duty = 0
				get_back_in_car_reminder_timer = 30000
			ENDIF
		ENDIF
		IF on_cutscene_duty = 0
			// if player gets out car - tell him to get back in
			IF NOT IS_CAR_DEAD jizzy_car
				IF NOT IS_CHAR_IN_CAR scplayer jizzy_car
					IF get_back_in_car_reminder_timer > 30000
						PRINT_NOW SYN_M_1 5000 1 // get back in the pimpmobile
						get_back_in_car_reminder_timer = 0
					ENDIF
				ELSE
					IF get_back_in_car_reminder_timer < 30000
						CLEAR_PRINTS
						get_back_in_car_reminder_timer = 30000
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	// waif for car to stop
	IF m_goals = 3
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CAR_STOPPED jizzy_car
				m_goals++
//				DO_FADE 500 FADE_OUT
//				WHILE GET_FADING_STATUS
//					WAIT 0
//				ENDWHILE
			ENDIF
		ENDIF	
	ENDIF

	
	// show girl1 leaving car
	IF m_goals = 4
		stored_weapon_model = -1
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		//FREEZE_ONSCREEN_TIMER TRUE
		CLEAR_PRINTS		  	  
		SET_FIXED_CAMERA_POSITION -1941.8452 731.7922 45.4219 0.0 0.0 0.0 //-1929.8002 728.8456 46.5413 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1940.9730 731.3325 45.5894 JUMP_CUT  //-1929.0494 728.1935 46.4354 JUMP_CUT
		CLEAR_AREA -1924.9542 724.0631 44.3047 20.0 TRUE
		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0

//		DO_FADE 500 FADE_IN
//		WHILE GET_FADING_STATUS
//			WAIT 0
//		ENDWHILE
		
		IF NOT IS_CHAR_DEAD girl1
			OPEN_SEQUENCE_TASK temp_seq
				IF NOT IS_CAR_DEAD jizzy_car
					TASK_LEAVE_CAR -1 jizzy_car
				ENDIF
				TASK_GO_STRAIGHT_TO_COORD -1 -1921.5046 719.3201 44.4375 PEDMOVE_WALK 20000
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK girl1 temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		TIMERA = 0
		TIMERB = 0

		$audio_string    = &SYN2_LU				  
		audio_sound_file = SOUND_SYN2_LU
		START_NEW_SCRIPT audio_line girl1 0 1 1 0

		m_goals++
	ENDIF

	// wait a couple of tics before starting phone coversation
	IF m_goals = 5
		IF TIMERA > 1000
		AND audio_line_is_active = 0
			dialogue_flag = 0
			dialogue_timer = 0
			TIMERB = 0
			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF

	IF m_goals > 5
	AND m_goals < 99
		IF audio_line_is_active = 0 
			IF dialogue_timer > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_CA					  
						audio_sound_file = SOUND_SYN2_CA
						START_NEW_SCRIPT audio_line scplayer 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_CB
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_CB					  
						audio_sound_file = SOUND_SYN2_CB
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_CC
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_CC					  
						audio_sound_file = SOUND_SYN2_CC
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_CD
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_CD					  
						audio_sound_file = SOUND_SYN2_CD
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_CE
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_CE					  
						audio_sound_file = SOUND_SYN2_CE
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_CF
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_CF					  
						audio_sound_file = SOUND_SYN2_CF
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_CG
						dialogue_flag++
					BREAK
					CASE 6
						$audio_string    = &SYN2_CG					  
						audio_sound_file = SOUND_SYN2_CG
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						dialogue_flag++
					BREAK
					CASE 7
						dialogue_flag++
					BREAK
				ENDSWITCH
				dialogue_timer = 0
			ENDIF
		ENDIF
	ENDIF

	// wait to shift camera view
	IF m_goals = 6
		IF TIMERA > 4000
			m_goals++
		ELSE
			IF NOT IS_CHAR_DEAD girl1
				GET_SCRIPT_TASK_STATUS girl1 PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					m_goals++
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF m_goals = 7
		SET_FIXED_CAMERA_POSITION -1919.1353 714.1419 46.9459 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1919.6941 714.9576 46.7967 JUMP_CUT
		SET_NEAR_CLIP 0.01

		IF NOT IS_CHAR_DEAD girl1
			CLEAR_CHAR_TASKS_IMMEDIATELY girl1
			SET_CHAR_COORDINATES girl1 -1920.4792 716.6052 45.6103
			SET_CHAR_HEADING girl1 190.5830	
			TASK_GO_STRAIGHT_TO_COORD girl1 -1919.6058 712.0557 45.6103 PEDMOVE_WALK 20000 
		ENDIF	
		TIMERA = 0	
		m_goals++
	ENDIF

	// skip
	IF m_goals > 4
	AND TIMERB > 3000
		IF IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	// final cut
	IF m_goals = 8

		IF TIMERA > 3000
			car_phone_cam_int = 0
			GOSUB setup_car_phone_cam
			TIMERA = 0
			m_goals++
		ENDIF

	ENDIF

	IF m_goals = 9
		IF TIMERA > 4000
		AND dialogue_flag > 7
			m_goals = 99
		ENDIF
	ENDIF
	
	IF m_goals = 99

		GOSUB cleanup_car_phone_cam

		REMOVE_BLIP jizzy_car_blip
		REMOVE_BLIP location_blip
		DELETE_CHAR girl1
		SET_CAR_DENSITY_MULTIPLIER 1.0
		SET_PED_DENSITY_MULTIPLIER 1.0
		m_stage++
		m_goals = 0	
	ENDIF

	// FUNCTIONS GLOBAL FOR STAGE -------------------------

RETURN

// *************************************************************************************************************
//						STAGE 4 -  Go pickup girl 2  
// *************************************************************************************************************
SYN2_m_stage_4:

	IF m_goals = 0
						   
		VAR_INT girl2_timer
		girl2_timer = 120000

		ADD_BLIP_FOR_COORD -2427.9185 -133.4192 34.3525 location_blip
		CHANGE_BLIP_COLOUR location_blip YELLOW
		PRINT_NOW SYN2_06 10000 1 // ~s~Waste the pimp in ~y~Hashbury~s~.
		
		DISPLAY_ONSCREEN_TIMER girl2_timer TIMER_DOWN

		// set up
		REQUEST_MODEL SHFYPRO
		REQUEST_MODEL BMYPIMP
		REQUEST_MODEL HUSTLER
		REQUEST_MODEL COLT45
		WHILE NOT HAS_MODEL_LOADED SHFYPRO
		   OR NOT HAS_MODEL_LOADED BMYPIMP
		   OR NOT HAS_MODEL_LOADED HUSTLER
		   OR NOT HAS_MODEL_LOADED COLT45
			WAIT 0
		ENDWHILE

		LOAD_MISSION_AUDIO 3 SOUND_CAR_PHONE_RING
		WHILE NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE
		
		// create pimp
		CREATE_CHAR PEDTYPE_CIVMALE BMYPIMP -2427.9185 -133.4192 34.3525 rival_pimp
		SET_CHAR_HEADING rival_pimp 143.0822
		//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY rival_pimp_dm 
		SET_CHAR_DECISION_MAKER rival_pimp empty_dm
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER rival_pimp TRUE

		SET_CHAR_MAX_HEALTH rival_pimp 300
		SET_CHAR_HEALTH rival_pimp 300

		// create girl
		CREATE_CHAR PEDTYPE_CIVFEMALE SHFYPRO -2428.9536 -134.2400 34.3525 girl2
		SET_CHAR_HEADING girl2 359.5201
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER girl2 TRUE	 
		//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY girl2_dm
		SET_CHAR_DECISION_MAKER girl2 empty_dm
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER girl2 TRUE
		SET_CHAR_NEVER_TARGETTED girl2 TRUE
		
		// pimp car
		CREATE_CAR HUSTLER	-2481.0593 -126.9303 24.6250 rival_pimp_car
		SET_CAR_HEADING rival_pimp_car 102.8779
		//SET_CAR_ONLY_DAMAGED_BY_PLAYER rival_pimp_car TRUE
		SET_CAR_HEALTH rival_pimp_car 600


		// make pimp walk round girl
		TASK_LOOK_AT_CHAR rival_pimp girl2 -2
		OPEN_SEQUENCE_TASK temp_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2430.0437 -133.1959 34.3525 PEDMOVE_WALK 10000
			TASK_TURN_CHAR_TO_FACE_CHAR -1 girl2
			TASK_PLAY_ANIM -1 idle_chat PED 4.0 FALSE FALSE FALSE FALSE -1
			TASK_GO_STRAIGHT_TO_COORD -1 -2429.9761 -135.4876 34.3525 PEDMOVE_WALK 10000
			TASK_GO_STRAIGHT_TO_COORD -1 -2428.1619 -135.4792 34.3525 PEDMOVE_WALK 10000
			TASK_GO_STRAIGHT_TO_COORD -1 -2427.9185 -133.4192 34.3525 PEDMOVE_WALK 10000
			SET_SEQUENCE_TO_REPEAT temp_seq 1
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK rival_pimp temp_seq
		CLEAR_SEQUENCE_TASK	temp_seq


		// switch peds off around area 
		SWITCH_PED_ROADS_OFF -2431.1145 -169.4874 33.0 -2426.5127 -117.2969 36.0

		// make girl stand still
		TASK_STAY_IN_SAME_PLACE girl2 TRUE
		TASK_LOOK_AT_CHAR girl2 rival_pimp -2


		// play pimp dialogue
		LVAR_INT pimp_dialogue
		pimp_dialogue = 0

		m_goals++
			
	ENDIF
													    
	// wait for player to get near
	IF m_goals = 1	
								 

		IF NOT IS_CHAR_DEAD rival_pimp
			IF audio_line_is_active = 0 
				IF dialogue_timer > 1000
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer rival_pimp 15.0 15.0 15.0 FALSE
						SWITCH pimp_dialogue
							CASE 0
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LF
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 1
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LG
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 2
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LH
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 3
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LI
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 4
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LJ
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 5
								$audio_string    = &DUMMY					  
								audio_sound_file = SOUND_SYN2_LK
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue = 0
							BREAK
						ENDSWITCH
						dialogue_timer = -4000
					ELSE
						SWITCH pimp_dialogue
							CASE 0
								$audio_string    = &SYN2_LF					  
								audio_sound_file = SOUND_SYN2_LF
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 1
								$audio_string    = &SYN2_LG					  
								audio_sound_file = SOUND_SYN2_LG
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 2
								$audio_string    = &SYN2_LH					  
								audio_sound_file = SOUND_SYN2_LH
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 3
								$audio_string    = &SYN2_LI					  
								audio_sound_file = SOUND_SYN2_LI
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 4
								$audio_string    = &SYN2_LJ					  
								audio_sound_file = SOUND_SYN2_LJ
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue++
							BREAK
							CASE 5
								$audio_string    = &SYN2_LK					  
								audio_sound_file = SOUND_SYN2_LK
								START_NEW_SCRIPT audio_line rival_pimp 1 1 1 0
								pimp_dialogue = 0
							BREAK
						ENDSWITCH
						dialogue_timer = -4000
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				SET_CHAR_COORDINATES scplayer -2421.1816 -15.8972 34.1601 
			ENDIF
		ENDIF
		IF IS_CHAR_ON_FOOT scplayer 
			IF NOT IS_CHAR_DEAD rival_pimp
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer rival_pimp 10.0 10.0 10.0 FALSE
					m_goals++
					CLEAR_ONSCREEN_TIMER girl2_timer
					GOTO wait_for_player_to_get_near_end
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD rival_pimp
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer rival_pimp 20.0 20.0 20.0 FALSE
					m_goals++
					CLEAR_ONSCREEN_TIMER girl2_timer
					GOTO wait_for_player_to_get_near_end
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD rival_pimp
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR rival_pimp scplayer
				m_goals++
				CLEAR_ONSCREEN_TIMER girl2_timer
				GOTO wait_for_player_to_get_near_end
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD rival_pimp
			IF IS_PLAYER_TARGETTING_CHAR player1 rival_pimp
				m_goals++
				CLEAR_ONSCREEN_TIMER girl2_timer
				GOTO wait_for_player_to_get_near_end
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD rival_pimp
			m_goals++
			CLEAR_ONSCREEN_TIMER girl2_timer
			GOTO wait_for_player_to_get_near_end
		ENDIF

		IF girl2_timer <= 0
			CLEAR_ONSCREEN_TIMER girl2_timer
			GOSUB cleanup_car_phone_cam
			PRINT_NOW SYN_F_6 5000 1
			m_failed = 1
		ENDIF

		wait_for_player_to_get_near_end:
	ENDIF

	// make pimp do a bolt
	IF m_goals = 2

		// girl says something
		IF NOT IS_CHAR_DEAD girl2
			$audio_string    = &SYN2_LA					  
			audio_sound_file = SOUND_SYN2_LA
			START_NEW_SCRIPT audio_line girl2 0 1 1 0
		ENDIF

		REMOVE_BLIP location_blip
		IF NOT IS_CHAR_DEAD rival_pimp
			GIVE_WEAPON_TO_CHAR rival_pimp WEAPONTYPE_PISTOL 9999
			ADD_BLIP_FOR_CHAR rival_pimp rival_pimp_blip
			TASK_LOOK_AT_CHAR rival_pimp scplayer 3000
		ENDIF

		IF NOT IS_CHAR_DEAD rival_pimp
			TIMERA = 0
			m_goals++
		ELSE
			m_goals++
		ENDIF
	ENDIF

	// wait for dialogue to finish before bolting
	IF m_goals = 3
		IF NOT IS_CHAR_DEAD rival_pimp
			IF TIMERA > 1000
			AND audio_line_is_active = 0
				IF NOT IS_CHAR_DEAD girl2
					CLEAR_CHAR_TASKS rival_pimp
					CLEAR_CHAR_TASKS girl2
					CLEAR_LOOK_AT girl2

					pimp_route_progress = 0

					GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
					SWITCH temp_int
						CASE 0
							$audio_string    = &SYN2_LB					  
							audio_sound_file = SOUND_SYN2_LB
							START_NEW_SCRIPT audio_line rival_pimp 0 1 1 0
						BREAK
						CASE 1
							$audio_string    = &SYN2_LC					  
							audio_sound_file = SOUND_SYN2_LC
							START_NEW_SCRIPT audio_line rival_pimp 0 1 1 0
						BREAK
						CASE 2
							$audio_string    = &SYN2_LD					  
							audio_sound_file = SOUND_SYN2_LD
							START_NEW_SCRIPT audio_line rival_pimp 0 1 1 0
						BREAK
						CASE 3
							$audio_string    = &SYN2_LE					  
							audio_sound_file = SOUND_SYN2_LE
							START_NEW_SCRIPT audio_line rival_pimp 0 1 1 0
						BREAK
					ENDSWITCH

					SET_CHAR_ACCURACY rival_pimp 90
					SET_CHAR_SHOOT_RATE rival_pimp 100
					OPEN_SEQUENCE_TASK temp_seq						
						TASK_SHOOT_AT_CHAR -1 scplayer 3000
						TASK_GO_STRAIGHT_TO_COORD -1 pimp_route_x[pimp_route_progress] pimp_route_y[pimp_route_progress] pimp_route_z[pimp_route_progress] PEDMOVE_SPRINT 9999999						
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK rival_pimp temp_seq
					CLEAR_SEQUENCE_TASK	temp_seq

					LVAR_INT pimp_damage_flag
					pimp_damage_flag = 0

					GOSUB pimp_ditches_girl

					LVAR_INT kill_pimp_text
					kill_pimp_text = 0
	
					TIMERA = 0
					m_goals++
				ENDIF	
			ENDIF
		ELSE
			GOSUB pimp_ditches_girl
			m_goals++
		ENDIF
	ENDIF	
						  
	
	// run along route
	IF m_goals = 4
		
		IF kill_pimp_text = 0
			IF TIMERA > 1000
				IF audio_line_is_active = 0
					PRINT SYN2_38 7000 1 // kill the pimp
					kill_pimp_text++
				ENDIF
			ENDIF
		ENDIF


		// pimp ai ----------------------------------------------			
		IF NOT IS_CHAR_DEAD rival_pimp
			IF LOCATE_CHAR_ON_FOOT_3D rival_pimp pimp_route_x[pimp_route_progress] pimp_route_y[pimp_route_progress] pimp_route_z[pimp_route_progress] 1.5 1.5 2.0 FALSE
				pimp_route_progress++
				IF pimp_route_progress < 8
//					IF pimp_attack_player = 0
//						CLEAR_CHAR_TASKS rival_pimp
//						TASK_KILL_CHAR_ON_FOOT rival_pimp scplayer
//						pimp_attack_player++
//					ELSE

						


						// run from player
						IF pimp_route_progress = 1
						OR pimp_route_progress = 5
						OR pimp_route_progress = 7
							TASK_GO_TO_COORD_WHILE_SHOOTING rival_pimp pimp_route_x[pimp_route_progress] pimp_route_y[pimp_route_progress] pimp_route_z[pimp_route_progress] PEDMOVE_SPRINT 0.0 0.0 scplayer
						ELSE
							TASK_GO_STRAIGHT_TO_COORD rival_pimp pimp_route_x[pimp_route_progress] pimp_route_y[pimp_route_progress] pimp_route_z[pimp_route_progress] PEDMOVE_SPRINT 99999
						ENDIF
//					ENDIF
				ELSE
					IF NOT IS_CAR_DEAD rival_pimp_car
						GET_CHAR_COORDINATES rival_pimp x y z
						IF LOCATE_CAR_3D rival_pimp_car x y z 10.0 10.0 4.0 FALSE
							CLEAR_CHAR_TASKS rival_pimp
							OPEN_SEQUENCE_TASK temp_seq
								TASK_ENTER_CAR_AS_DRIVER -1 rival_pimp_car 20000
								TASK_CAR_DRIVE_TO_COORD -1 rival_pimp_car -2599.2866 -127.2555 3.1797 15.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH
								TASK_CAR_DRIVE_WANDER -1 rival_pimp_car 15.0 DRIVINGMODE_PLOUGHTHROUGH
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK rival_pimp temp_seq
							CLEAR_SEQUENCE_TASK temp_seq 
							m_goals++
						ELSE
							TASK_FLEE_CHAR_ANY_MEANS rival_pimp scplayer 100000.0 999999 TRUE 3000 5000 50.0
							m_goals++								
						ENDIF
					ELSE
						TASK_FLEE_CHAR_ANY_MEANS rival_pimp scplayer 100000.0 999999 TRUE 3000 5000 50.0
						m_goals++
					ENDIF
				ENDIF
			ELSE

				IF pimp_route_progress = 0
					IF IS_CHAR_IN_AREA_3D scplayer -2430.8787 -167.3782	32.3125	-2426.9165 -143.2979 36.3125 FALSE
						GET_CHAR_COORDINATES scplayer x y z
						GET_CHAR_COORDINATES rival_pimp x2 y2 z2
						IF y < y2
							OPEN_SEQUENCE_TASK temp_seq
								TASK_SHOOT_AT_CHAR -1 scplayer 3000
								TASK_FLEE_CHAR_ANY_MEANS -1 scplayer 100000.0 999999 TRUE 3000 5000 50.0	
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK rival_pimp temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							m_goals++
						ENDIF
					ELSE
						IF pimp_damage_flag = 0
							IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR scplayer rival_pimp
								TASK_GO_STRAIGHT_TO_COORD rival_pimp pimp_route_x[0] pimp_route_y[0] pimp_route_z[0] PEDMOVE_SPRINT 99999
								pimp_damage_flag++
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			ENDIF
		ELSE
			m_goals++
		ENDIF
	ENDIF

	// wait for pimp to get killed 
	IF m_goals = 5

		IF fix_rival_pimp_health = 0
			IF NOT IS_CHAR_DEAD rival_pimp
				IF NOT IS_CAR_DEAD rival_pimp_car
					IF IS_CHAR_IN_CAR rival_pimp rival_pimp_car
						GET_CHAR_HEALTH rival_pimp temp_int
						IF temp_int > 100
							SET_CHAR_HEALTH rival_pimp 100
						ENDIF
						fix_rival_pimp_health = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF IS_CHAR_DEAD rival_pimp
			TIMERA = 0
			m_goals++
		ELSE
			IF NOT IS_CAR_DEAD rival_pimp_car
				IF IS_CHAR_IN_CAR rival_pimp rival_pimp_car
					IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer rival_pimp 15.0 15.0 15.0 FALSE
						SET_CAR_CRUISE_SPEED rival_pimp_car 25.0
					ELSE
						SET_CAR_CRUISE_SPEED rival_pimp_car 15.0
					ENDIF
				ENDIF
			ENDIF

			// if pimp is not in any car
			IF NOT IS_CHAR_IN_ANY_CAR rival_pimp
				GET_SCRIPT_TASK_STATUS rival_pimp PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					GET_SCRIPT_TASK_STATUS rival_pimp TASK_FLEE_CHAR_ANY_MEANS temp_int
					IF temp_int = FINISHED_TASK
						TASK_FLEE_CHAR_ANY_MEANS rival_pimp scplayer 100000.0 999999 FALSE 0 0 50.0	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				IF NOT IS_CHAR_DEAD rival_pimp
					TASK_DIE rival_pimp
				ENDIF
			ENDIF
		ENDIF


	ENDIF

	// pimp is dead	- get back in car
	IF m_goals = 6
		CLEAR_PRINTS
		REMOVE_BLIP rival_pimp_blip
		IF TIMERA > 1000
			IF NOT IS_CAR_DEAD jizzy_car
				IF NOT IS_CHAR_IN_CAR scplayer jizzy_car
					IF on_cutscene_duty = 0
						PRINT_NOW SYN_M_1 7000 1 // get back in car
						ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car
						PLAY_MISSION_AUDIO 3
						phone_ringing = 1
						//WRITE_DEBUG phone_told_to_ring1
					ENDIF
					TIMERA = 0
					m_goals++
				ELSE
					ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car
					PLAY_MISSION_AUDIO 3
					phone_ringing = 1
					//WRITE_DEBUG phone_told_to_ring2
					TIMERA = 0
					m_goals++	
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					TIMERA = 0
					ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car
					PLAY_MISSION_AUDIO 3
					phone_ringing = 1
					//WRITE_DEBUG phone_told_to_ring3
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// start phone ringing
	IF m_goals = 7
		IF TIMERA > 3000
//			// start phone sound
//			IF NOT IS_CAR_DEAD jizzy_car 
//				GET_CAR_COORDINATES jizzy_car x y z
//				ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car 
//				PLAY_MISSION_AUDIO 3
//			ENDIF
			
			TIMERA = 0
			//PRINT_NOW SYN_M_2 4000 1
			m_goals++
		ENDIF
	ENDIF

	// wait for player to get in car
	IF m_goals = 8
		IF TIMERA > 500
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					SET_PLAYER_CONTROL player1 OFF
					TIMERA = 0
					m_goals++	
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	// wait a couple of rings before player answers phone		
	IF m_goals = 9
		//IF TIMERA > 2000
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					CLEAR_MISSION_AUDIO 3
					phone_ringing = 0
					CLEAR_PRINTS
					m_goals++
				ENDIF 	
			ENDIF
		//ENDIF
	ENDIF


	// cutscene - what next?
	IF m_goals = 10
		stored_weapon_model = -1
		car_phone_cam_int = 1
		CLEAR_PRINTS

		DO_FADE 150 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		GOSUB setup_car_phone_cam

		DO_FADE 150 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		m_goals++
	ENDIF

	// wait a tic before starting text
	IF m_goals = 11
		IF TIMERA > 1000
//				PRINT SYN2_10 3000 1 // CJ - 'Ok, what next?'
//				PRINT SYN2_11 3000 1 // JIZZY ON PHONE - 'Theres a girl in trouble at xxxx'
//				PRINT SYN2_12 3000 1 // JIZZY ON PHONE - 'Get there fast!'
//				PRINT SYN2_13 3000 1 // JIZZY ON PHONE - 'Kill the mutherfuckers who mess with my bitches'
			dialogue_flag = 0
			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF

	IF m_goals > 11
	AND m_goals < 99
		IF audio_line_is_active = 0 
			IF dialogue_timer > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_DA					  
						audio_sound_file = SOUND_SYN2_DA
						START_NEW_SCRIPT audio_line scplayer 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_DB
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_DB					  
						audio_sound_file = SOUND_SYN2_DB
						START_NEW_SCRIPT audio_line scplayer 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_DC
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_DC					  
						audio_sound_file = SOUND_SYN2_DC
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_DD
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_DD					  
						audio_sound_file = SOUND_SYN2_DD
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_DE
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_DE					  
						audio_sound_file = SOUND_SYN2_DE
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_DF
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_DF					  
						audio_sound_file = SOUND_SYN2_DF
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_DG
						dialogue_flag++
					BREAK
					CASE 6
						$audio_string    = &SYN2_DG					  
						audio_sound_file = SOUND_SYN2_DG
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						dialogue_flag++
					BREAK
					CASE 7
						dialogue_flag++
					BREAK
				ENDSWITCH
				dialogue_timer = 0
			ENDIF
		ENDIF
	ENDIF

	// wait for cutscene to finish
	IF m_goals = 12
		IF dialogue_flag > 7
		OR IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	// end of cutscene - move to next stage
	IF m_goals = 99

		GOSUB cleanup_car_phone_cam

		MARK_CAR_AS_NO_LONGER_NEEDED rival_pimp_car
		MARK_CHAR_AS_NO_LONGER_NEEDED rival_pimp
		MARK_CHAR_AS_NO_LONGER_NEEDED girl2

		MARK_MODEL_AS_NO_LONGER_NEEDED SHFYPRO
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYPIMP
		MARK_MODEL_AS_NO_LONGER_NEEDED HUSTLER

		m_goals = 0
		m_stage++
	ENDIF

RETURN

// *************************************************************************************************************
//						STAGE 8 -  go back to the docks 
// *************************************************************************************************************
SYN2_m_stage_5:

	IF m_goals = 0
		ADD_BLIP_FOR_COORD -1911.9188 -629.9120 23.6093 location_blip
		CHANGE_BLIP_COLOUR location_blip YELLOW
		PRINT_NOW SYN2_14 10000 1 // save jizzy's girl
		girl3_flag = 0
		m_goals++	
	ENDIF

	IF m_goals = 1 
		
		// create punters
		REQUEST_MODEL CAMPER
		REQUEST_MODEL SWMYHP1
		REQUEST_MODEL SWMYHP2
		REQUEST_MODEL VBFYPRO
		REQUEST_MODEL COLT45
		WHILE NOT HAS_MODEL_LOADED CAMPER
		   OR NOT HAS_MODEL_LOADED SWMYHP1
		   OR NOT HAS_MODEL_LOADED SWMYHP2
		   OR NOT HAS_MODEL_LOADED VBFYPRO
		   OR NOT HAS_MODEL_LOADED COLT45
			WAIT 0
		ENDWHILE	
		
		LOAD_MISSION_AUDIO 3 SOUND_CAR_PHONE_RING
		WHILE NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE
								
		CREATE_CHAR PEDTYPE_CIVMALE SWMYHP1 -1911.9188 -629.2120 23.6093 hippy_1
		SET_CHAR_HEADING hippy_1 180.0	
		CREATE_CHAR PEDTYPE_CIVMALE SWMYHP1 -1907.6439 -623.0884 23.6093 hippy_2
		SET_CHAR_HEADING hippy_2 350.5407 
		GIVE_WEAPON_TO_CHAR hippy_2 WEAPONTYPE_PISTOL 99999

		CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO -1911.9188 -629.9120 23.6093 girl3
		SET_CHAR_HEADING girl3 331.4920
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER girl3 TRUE
		SET_CHAR_NEVER_TARGETTED girl3 TRUE
		SET_CHAR_MAX_HEALTH girl3 120
		SET_CHAR_HEALTH girl3 120 

		CREATE_CAR CAMPER -1911.9517 -624.4892 23.6093 hippy_van
		SET_CAR_HEADING hippy_van 304.9144
		CHANGE_CAR_COLOUR hippy_van 104 61
		FORCE_CAR_LIGHTS hippy_van FORCE_CAR_LIGHTS_ON
		OPEN_CAR_DOOR hippy_van FRONT_RIGHT_DOOR
		OPEN_CAR_DOOR hippy_van REAR_RIGHT_DOOR
		 
		// load decision makers
		LVAR_INT hippy_dm
		LVAR_INT girl3_dm
		//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH hippy_dm 
		SET_CHAR_DECISION_MAKER hippy_1 tough_dm
		SET_CHAR_DECISION_MAKER hippy_2 tough_dm

		//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY girl3_dm
		SET_CHAR_DECISION_MAKER girl3 empty_dm
		SET_CHAR_RELATIONSHIP hippy_2 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1 

		// set char behaviour
		SET_CHAR_STAY_IN_SAME_PLACE hippy_2 TRUE
		SET_CHAR_STAY_IN_SAME_PLACE girl3 TRUE

		VAR_INT girl_health
		GET_CHAR_HEALTH girl3 girl_health
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING girl_health COUNTER_DISPLAY_BAR 1 SYN2_63

		// make girl lie on ground - taking a beating (she probably deserves)
		REQUEST_ANIMATION CRACK
		REQUEST_ANIMATION MISC
		WHILE NOT HAS_ANIMATION_LOADED CRACK
		   OR NOT HAS_ANIMATION_LOADED MISC
			WAIT 0
		ENDWHILE
		IF NOT IS_CHAR_DEAD girl3
			TASK_PLAY_ANIM_NON_INTERRUPTABLE girl3 crckdeth2 CRACK 4.0 TRUE FALSE FALSE FALSE -1
		ENDIF
		// make hippy beat up girl
		IF NOT IS_CHAR_DEAD hippy_1
			OPEN_SEQUENCE_TASK temp_seq
			TASK_PLAY_ANIM -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 BITCHSLAP MISC 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 BITCHSLAP MISC 4.0 FALSE FALSE FALSE TRUE -1
			SET_SEQUENCE_TO_REPEAT temp_seq 1
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK hippy_1 temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF

		m_goals++
	ENDIF

	IF NOT IS_CHAR_DEAD hippy_1
		GET_SCRIPT_TASK_STATUS hippy_1 PERFORM_SEQUENCE_TASK temp_int
		IF NOT temp_int = FINISHED_TASK
			IF hippy_timer > 3000
				SET_CHAR_SAY_CONTEXT hippy_1	CONTEXT_GLOBAL_FIGHT temp_int
				hippy_timer = 0
				//WRITE_DEBUG hippy1_context_speech
			ENDIF
		ENDIF
	ENDIF

	// wait for player to get close
	IF m_goals = 2
		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				SET_CHAR_COORDINATES scplayer -1904.0409 -602.5592 23.6067
			ENDIF
		ENDIF
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1911.9188 -629.9120 23.6093 40.0 40.0 5.0 FALSE
			REMOVE_BLIP location_blip
			PRINT_NOW SYN2_15 10000 1 // kill both punters

			IF NOT IS_CHAR_DEAD hippy_1
				ADD_BLIP_FOR_CHAR hippy_1 hippy1_blip
			ENDIF
			IF NOT IS_CHAR_DEAD hippy_2
				ADD_BLIP_FOR_CHAR hippy_2 hippy2_blip
			ENDIF
			m_goals++
		ENDIF
	ENDIF

	// wait for player to kill hippies
	IF m_goals = 3
		IF IS_CHAR_DEAD hippy_1
			IF DOES_BLIP_EXIST hippy1_blip
				REMOVE_BLIP hippy1_blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD hippy_2
			IF DOES_BLIP_EXIST hippy2_blip
				REMOVE_BLIP hippy2_blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD hippy_1
		AND IS_CHAR_DEAD hippy_2
			m_goals++
			TIMERA = 0
		ENDIF
	ENDIF

	// wait a couple of tics before ho gets up
	IF m_goals = 4
		IF TIMERA > 1000

			// get hippies last living coordinates
			LVAR_FLOAT hip_x hip_y hip_z
			LVAR_FLOAT hip_off_x hip_off_y hip_off_z
			IF IS_CHAR_DEAD hippy_1
				//GET_CHAR_COORDINATES hippy_1 hip_x hip_y hip_z
				GET_DEAD_CHAR_COORDINATES hippy_1 hip_x hip_y hip_z
				IF NOT IS_CHAR_DEAD girl3
					GET_CHAR_COORDINATES girl3 x y z
				ENDIF
				vec_x = hip_x - x
				vec_y = hip_y - y
				vec_z = hip_z - z
				GET_DISTANCE_BETWEEN_COORDS_2D hip_x hip_y  x y  temp_float
				IF temp_float > 0.01
					vec_x /= temp_float
					vec_y /= temp_float
				ENDIF
				vec_x *= -0.5
				vec_y *= -0.5
				hip_off_x = hip_x + vec_x
				hip_off_y = hip_y + vec_y
				hip_off_z = hip_z
				//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS hippy_1 0.0 -0.5 0.0 hip_off_x hip_off_y hip_off_z
			ENDIF

			// make prossie get up and start kicking hippy
			OPEN_SEQUENCE_TASK temp_seq
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 getup PED 6.0 FALSE FALSE FALSE TRUE 0
				//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 getup PED 4.0 FALSE FALSE FALSE TRUE -1
				TASK_GO_STRAIGHT_TO_COORD -1 hip_off_x hip_off_y hip_off_z PEDMOVE_RUN 99999
				TASK_TURN_CHAR_TO_FACE_COORD -1 hip_x hip_y hip_z
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 FIGHTA_G PED 4.0 FALSE FALSE FALSE FALSE -1 
			CLOSE_SEQUENCE_TASK temp_seq

			IF NOT IS_CHAR_DEAD girl3
				SET_CHAR_BLEEDING girl3 FALSE
				SET_CHAR_STAY_IN_SAME_PLACE girl3 FALSE
				PERFORM_SEQUENCE_TASK girl3 temp_seq
				girl3_flag = 1
			ENDIF
			CLEAR_SEQUENCE_TASK temp_seq
			CLEAR_ONSCREEN_COUNTER girl_health		 
			m_goals++
			dialogue_flag = 0
			TIMERA = 0
			TIMERB = 2000 
		ENDIF
	ENDIF
	

	// yo ok ho? dialogue
	IF m_goals > 4
		IF NOT IS_CHAR_DEAD girl3
			
			IF girl3_flag = 1
//				IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer girl3 5.0 5.0 3.0 FALSE
//					IF IS_CHAR_PLAYING_ANIM girl3 FIGHTA_G
//						IF dialogue_flag = 0
//							IF TIMERA > 4000
//								PRINT_NOW SYN2_48 4000 1 //You ok ho?
//								TIMERA = 0
//								dialogue_flag++
//							ENDIF
//						ENDIF
//						IF dialogue_flag = 1
//							IF TIMERA > 4000
//								PRINT_NOW SYN2_49 4000 1 //Prosse - 'Motherfucking hippy'
//								dialogue_flag++
//								TIMERA = 0
//							ENDIF
//						ENDIF
//						IF dialogue_flag = 2
//							IF TIMERA > 4000
//								PRINT_NOW SYN2_50 4000 1 //Prosse - 'Put this in your pipe and smoke it'
//								dialogue_flag++
//								TIMERA = 0
//							ENDIF
//						ENDIF
//						IF dialogue_flag = 3
//							IF TIMERA > 4000
//								PRINT_NOW SYN2_51 4000 1 //Prosse - 'Not so tough now!
//								dialogue_flag++
//								TIMERA = 0
//							ENDIF
//						ENDIF
//					ENDIF
//				ENDIF
				
				IF TIMERB > 3000
					SET_CHAR_SAY_CONTEXT girl3	CONTEXT_GLOBAL_FIGHT	temp_int
					TIMERB = 0
				ENDIF


				GET_SCRIPT_TASK_STATUS girl3 PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					girl3_flag++
				ENDIF
			ENDIF

			IF girl3_flag = 2
				IF NOT IS_CAR_DEAD hippy_van
					IF LOCATE_CHAR_ANY_MEANS_CAR_3D girl3 hippy_van 30.0 30.0 10.0 FALSE
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CAR -1 hippy_van 20000 5.0
							TASK_ENTER_CAR_AS_DRIVER -1 hippy_van 999999
							TASK_CAR_DRIVE_WANDER -1 hippy_van 40.0 DRIVINGMODE_AVOIDCARS
						CLOSE_SEQUENCE_TASK temp_seq
					ELSE
						OPEN_SEQUENCE_TASK temp_seq
						TASK_WANDER_STANDARD -1
						CLOSE_SEQUENCE_TASK temp_seq	
					ENDIF
				ELSE
					OPEN_SEQUENCE_TASK temp_seq
					TASK_WANDER_STANDARD -1
					CLOSE_SEQUENCE_TASK temp_seq
				ENDIF
				PERFORM_SEQUENCE_TASK girl3 temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				girl3_flag++
			ENDIF

		ENDIF
	ENDIF
					   

	// pimp is dead	- get back in car
	IF m_goals = 5
		IF TIMERA > 1000
			IF NOT IS_CAR_DEAD jizzy_car
				IF NOT IS_CHAR_IN_CAR scplayer jizzy_car
					IF on_cutscene_duty = 0
						PRINT_NOW SYN_M_1 999999 1 // get back in car
					ENDIF
					TIMERA = 0
					m_goals++
				ELSE
					TIMERA = 0
					m_goals++	
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					TIMERA = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// start phone ringing
	IF m_goals = 6
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CHAR_IN_CAR scplayer jizzy_car
				CLEAR_THIS_PRINT SYN_M_1
			ENDIF
		ENDIF
		
		IF TIMERA > 6000
			// start phone sound
			IF NOT IS_CAR_DEAD jizzy_car 
				GET_CAR_COORDINATES jizzy_car x y z
				ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car
				PLAY_MISSION_AUDIO 3
				phone_ringing = 1
			ENDIF
			TIMERA = 0
			//PRINT_NOW SYN_M_2 4000 1
			m_goals++
		ENDIF
	ENDIF

	// wait for player to get in car
	IF m_goals = 7
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CHAR_IN_CAR scplayer jizzy_car
				CLEAR_THIS_PRINT SYN_M_1
			ENDIF
		ENDIF

		IF TIMERA > 500
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					SET_PLAYER_CONTROL player1 OFF
					stored_weapon_model = -1
					TIMERA = 0
					m_goals++							
				ENDIF
			ENDIF
		ENDIF
	ENDIF
  	
	// wait a couple of rings before player answers phone		
	IF m_goals = 8
		//IF TIMERA > 2000
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					CLEAR_MISSION_AUDIO 3
					phone_ringing = 0
					CLEAR_PRINTS
					m_goals++
				ENDIF 	
			ENDIF
		//ENDIF
	ENDIF


	// phone cutscene
	IF m_goals = 9
		stored_weapon_model = -1
		car_phone_cam_int = 2
		CLEAR_PRINTS
		
		DO_FADE 150 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		GOSUB setup_car_phone_cam

		DO_FADE 150 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		m_goals++
	ENDIF

	// wait a tic before starting text
	IF m_goals = 10
		IF TIMERA > 1000
//				PRINT SYN2_16 3000 1 // JIZZY ON PHONE - 'The girl you dropped off in Downtown has decided she's out the game.'
//				PRINT SYN2_17 3000 1 // JIZZY ON PHONE - 'Her rich punter wants to take her off the streets. I'm not having it!!'
//				PRINT SYN2_18 3000 1 // JIZZY ON PHONE - 'Get to the hotel and sort out the punter.'
			dialogue_flag = 0
			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF

	IF m_goals > 10
	AND m_goals < 99
		IF audio_line_is_active = 0 
			IF dialogue_timer > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_FA					  
						audio_sound_file = SOUND_SYN2_FA
						START_NEW_SCRIPT audio_line scplayer 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_FB
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_FB					  
						audio_sound_file = SOUND_SYN2_FB
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_FC
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_FC					  
						audio_sound_file = SOUND_SYN2_FC
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_FD
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_FD					  
						audio_sound_file = SOUND_SYN2_FD
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_FE
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_FE					  
						audio_sound_file = SOUND_SYN2_FE
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_FF
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_FF					  
						audio_sound_file = SOUND_SYN2_FF
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						dialogue_flag++
					BREAK
					CASE 6
						dialogue_flag++
					BREAK
				ENDSWITCH
				dialogue_timer = 0
			ENDIF
		ENDIF
	ENDIF

	// wait for cutscene to finish
	IF m_goals = 11
		IF dialogue_flag > 6
		OR IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	// end of cutscene - move to next stage
	IF m_goals = 99

		GOSUB cleanup_car_phone_cam

		MARK_CHAR_AS_NO_LONGER_NEEDED hippy_1
		MARK_CHAR_AS_NO_LONGER_NEEDED hippy_2
		MARK_CHAR_AS_NO_LONGER_NEEDED girl3
		MARK_CAR_AS_NO_LONGER_NEEDED  hippy_van

		MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER
		MARK_MODEL_AS_NO_LONGER_NEEDED SWMYHP1
		MARK_MODEL_AS_NO_LONGER_NEEDED SWMYHP2
		MARK_MODEL_AS_NO_LONGER_NEEDED VBFYPRO
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

		REMOVE_ANIMATION CRACK
		REMOVE_ANIMATION MISC


		REMOVE_BLIP jizzy_car_blip

		m_goals = 0
		m_stage++
	ENDIF
		
	// get girl health
	IF DOES_CHAR_EXIST girl3
		IF NOT IS_CHAR_DEAD girl3
			IF NOT IS_CHAR_DEAD hippy_1
				//GET_SCRIPT_TASK_STATUS hippy_1 PERFORM_SEQUENCE_TASK temp_int
				//IF NOT temp_int = FINISHED_TASK
				IF IS_CHAR_PLAYING_ANIM hippy_1 FIGHTA_G 
				OR IS_CHAR_PLAYING_ANIM hippy_1 BITCHSLAP
					GET_CHAR_HEALTH girl3 girl_health
					IF TIMERB > 1000
						girl_health += -1
						TIMERB = 0
					ENDIF	
				ENDIF

//					GET_SCRIPT_TASK_STATUS girl3 TASK_PLAY_ANIM temp_int
//					IF temp_int = FINISHED_TASK
//						TASK_PLAY_ANIM girl3 crckdeth2 CRACK 4.0 TRUE FALSE FALSE FALSE -1
//					ENDIF	
				//ENDIF
			ENDIF

			IF girl_health <= 1
				girl_health = 1
				TASK_DIE girl3
			ELSE
				SET_CHAR_HEALTH girl3 girl_health	
			ENDIF

		ENDIF
	ENDIF


	// cleanup from previous stage
	// fail if player kills girl 2
	IF DOES_CHAR_EXIST girl2
		// cleanup girl2  
		IF NOT IS_CHAR_DEAD girl2
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer girl3 50.0 50.0 FALSE
				IF NOT IS_CHAR_ON_SCREEN girl2
					DELETE_CHAR girl2
				ENDIF
			ENDIF
		ENDIF		
	ENDIF

RETURN



// *************************************************************************************************************
//						STAGE 9 -  go back to hotel and stop girl from leaving jizzy
// *************************************************************************************************************
SYN2_m_stage_6:
		
	// fake create
	IF m_goals = -1
		CREATE_CAR PONY 0.0 0.0 0.0 mib_car[0]
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 mib_driver[0]
		//CREATE_CAR BUFFALO 0.0 0.0 0.0 pcar
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 rich_dude
		CREATE_CAR PONY 0.0 0.0 0.0 rich_car
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 girl1
	ENDIF


	IF m_goals = 0
		
		// debug only
		IF NOT DOES_VEHICLE_EXIST jizzy_car
			// create jizzy's car if it doesn't exist
			IF NOT HAS_MODEL_LOADED BROADWAY
				REQUEST_MODEL BROADWAY
				WHILE NOT HAS_MODEL_LOADED BROADWAY 
					WAIT 0
				ENDWHILE
			ENDIF
			CREATE_CAR BROADWAY -1894.3076 712.9404 44.2969 jizzy_car
			SET_CAR_HEADING jizzy_car 0.0 
			IF IS_PLAYER_PLAYING player1
				WARP_CHAR_INTO_CAR scplayer jizzy_car
			ENDIF
			LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm
			LOAD_SCENE -1900.7535 645.3122 35.4401
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			DO_FADE 0 FADE_IN
		ENDIF
	
		// add location blip
		ADD_BLIP_FOR_COORD -1964.3716 724.4017 44.2969 location_blip
		CHANGE_BLIP_COLOUR location_blip YELLOW
		PRINT_NOW SYN2_19 10000 1 // go to the hotel downtown

		m_goals++

	ENDIF



	// wait for player to arrive
	IF m_goals = 1
		IF DOES_BLIP_EXIST location_blip
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					IF LOCATE_CAR_3D jizzy_car -1964.3716 724.4017 44.2969 4.0 4.0 4.0 TRUE
						IF IS_VEHICLE_ON_ALL_WHEELS jizzy_car
							CLEAR_PRINTS
							SET_PLAYER_CONTROL player1 OFF
							DO_FADE 500 FADE_OUT
							WHILE GET_FADING_STATUS 
								WAIT 0
							ENDWHILE
							REMOVE_BLIP location_blip	
							START_NEW_SCRIPT cleanup_audio_lines
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
							m_goals++
						ENDIF
					ENDIF
				ELSE
					REMOVE_BLIP location_blip
					PRINT SYN_M_1 7000 1 // get back in the pimpmobile
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD jizzy_car
				IF IS_CHAR_IN_CAR scplayer jizzy_car
					ADD_BLIP_FOR_COORD -1964.3716 724.4017 44.2969 location_blip
					CHANGE_BLIP_COLOUR location_blip YELLOW
					CLEAR_THIS_PRINT SYN_M_1
					PRINT SYN2_19 7000 1 // go to the hotel downtown					
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// do cutscene of cars arriving	 -  load stuff
	IF m_goals = 2

		//SET_PED_DENSITY_MULTIPLIER 0.0
		SET_CAR_DENSITY_MULTIPLIER 0.0

		CLEAR_AREA -1964.3716 724.4017 44.2969 100.0 TRUE

		LVAR_INT rich_car
		LVAR_INT rich_car_driver
		LVAR_INT rich_dude
		LVAR_INT dialogue_flag

		REQUEST_MODEL STRETCH
		REQUEST_MODEL HUNTLEY
		REQUEST_MODEL HFYPRO
		REQUEST_MODEL BMYMIB 
		REQUEST_MODEL WMOPREA
		REQUEST_MODEL COLT45   
		REQUEST_MODEL MP5LNG
		REQUEST_CAR_RECORDING 539
		REQUEST_CAR_RECORDING 540
		REQUEST_CAR_RECORDING 541

		WHILE NOT HAS_MODEL_LOADED STRETCH
		   OR NOT HAS_MODEL_LOADED HFYPRO 	// prossie
		   OR NOT HAS_MODEL_LOADED HUNTLEY
		   OR NOT HAS_MODEL_LOADED BMYMIB	// driver/guard
		   OR NOT HAS_MODEL_LOADED WMOPREA	// rich dude
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 539
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 540
		   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 541
		   	WAIT 0
		ENDWHILE 

		WHILE NOT HAS_MODEL_LOADED COLT45
		   OR NOT HAS_MODEL_LOADED MP5LNG
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD jizzy_car
			SET_CAR_COORDINATES jizzy_car -1964.3716 724.4017 44.2969
			SET_CAR_HEADING jizzy_car 266.5514
		ENDIF

		CREATE_CAR STRETCH -1973.8113 728.3975 44.2969 rich_car
		SET_CAR_HEADING rich_car 269.8438
		CREATE_CHAR_INSIDE_CAR rich_car PEDTYPE_CIVMALE BMYMIB rich_car_driver 
		SET_CHAR_DECISION_MAKER rich_car_driver empty_dm 
		SET_CAR_CRUISE_SPEED rich_car 15.0
		CHANGE_CAR_COLOUR rich_car 1 0
		START_PLAYBACK_RECORDED_CAR rich_car 540
		SKIP_IN_PLAYBACK_RECORDED_CAR rich_car 3.0
		SET_CAR_ONLY_DAMAGED_BY_PLAYER rich_car TRUE

		CREATE_CAR HUNTLEY -1987.8113 728.3975 44.2969 mib_car[0]
		SET_CAR_HEADING mib_car[0] 269.8438
		CREATE_CHAR_INSIDE_CAR mib_car[0] PEDTYPE_CIVMALE BMYMIB mib_driver[0]
		GIVE_WEAPON_TO_CHAR mib_driver[0] WEAPONTYPE_PISTOL 99999
		SET_CHAR_DECISION_MAKER mib_driver[0] empty_dm
		SET_CAR_CRUISE_SPEED mib_car[0] 15.0
		CHANGE_CAR_COLOUR mib_car[0] 0 0
		START_PLAYBACK_RECORDED_CAR mib_car[0] 541
		SKIP_IN_PLAYBACK_RECORDED_CAR mib_car[0] 3.0

		LVAR_INT mib_passenger
		CREATE_CHAR_AS_PASSENGER mib_car[0] PEDTYPE_CIVMALE BMYMIB 0 mib_passenger
		GIVE_WEAPON_TO_CHAR mib_passenger WEAPONTYPE_MP5 99999
		SET_CHAR_DECISION_MAKER mib_passenger empty_dm		

		SWITCH_WIDESCREEN ON
		CLEAR_HELP
		CLEAR_PRINTS
		SET_FIXED_CAMERA_POSITION -1968.5795 721.6734 47.3198 0.0 0.0 0.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car -5.0 -2.0 0.0 x y z
		POINT_CAMERA_AT_POINT x y z JUMP_CUT

		DO_FADE 500 FADE_IN
 
		TIMERA = 0
		m_goals++
	
	ENDIF

	IF m_goals > 2
		IF IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	IF m_goals = 3

		IF NOT IS_CAR_DEAD rich_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car -5.0 -2.0 0.0 x y z
			POINT_CAMERA_AT_POINT x y z JUMP_CUT
		ENDIF

		IF TIMERA > 4000
			SET_FIXED_CAMERA_POSITION -1929.4021 738.0927 44.7662 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT -1929.3257 737.1227 44.9969 JUMP_CUT
			m_goals++
		ENDIF

	ENDIF

	IF m_goals = 4
		IF NOT IS_CAR_DEAD rich_car
			IF NOT IS_CAR_DEAD mib_car[0]
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR rich_car
				AND NOT IS_PLAYBACK_GOING_ON_FOR_CAR mib_car[0]
					WAIT 1500
					dialogue_flag = 0
					TIMERB = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// dialogue
	IF m_goals >= 5
		IF TIMERB > 1000
			IF audio_line_is_active = 0
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_HA					  
						audio_sound_file = SOUND_SYN2_HA
						START_NEW_SCRIPT audio_line rich_dude 0 1 1 0  // The Lord sent me to save your thread-bear soul, harlot!
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_HB
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_HB					  
						audio_sound_file = SOUND_SYN2_HB
						START_NEW_SCRIPT audio_line rich_dude 0 1 2 1  // Get in the car and remove your filthy vestments 
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_HC
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_HC					  
						audio_sound_file = SOUND_SYN2_HC
						START_NEW_SCRIPT audio_line rich_dude 0 1 1 1  // so I my better gaze upon thine corruption!
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_HD
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_HD					  
						audio_sound_file = SOUND_SYN2_HD
						START_NEW_SCRIPT audio_line rich_dude 0 1 2 1  // Driver, get us out of here, 
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_HE
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_HE					  
						audio_sound_file = SOUND_SYN2_HE
						START_NEW_SCRIPT audio_line rich_dude 0 1 1 1  // before the devil’s right hand snatches this po’girl from salvation!
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_HF
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_HF					  
						audio_sound_file = SOUND_SYN2_HF
						START_NEW_SCRIPT audio_line rich_car_driver 0 1 2 1  // Right away, your evangelical holiness!
						dialogue_flag++
					BREAK
				ENDSWITCH
				TIMERB = 0
			ENDIF
		ENDIF
	ENDIF


	IF m_goals = 5

		CLEAR_AREA -1924.4874 724.8195 45.1340 100.0 TRUE
		SET_PED_DENSITY_MULTIPLIER 0.0 
		SET_CAR_DENSITY_MULTIPLIER 0.0
		
		IF NOT IS_CAR_DEAD rich_car
			SET_CAR_COORDINATES rich_car -1924.4874 725.2195 45.1340
			SET_CAR_HEADING rich_car 270.0
			OPEN_CAR_DOOR rich_car REAR_RIGHT_DOOR
			FREEZE_CAR_POSITION rich_car TRUE
		ENDIF
		IF NOT IS_CAR_DEAD mib_car[0]
			SET_CAR_COORDINATES mib_car[0] -1932.3870 725.1635 45.2749
			SET_CAR_HEADING mib_car[0] 270.0
			FREEZE_CAR_POSITION mib_car[0] TRUE
		ENDIF	

		IF NOT IS_CHAR_DEAD rich_car_driver
			CLEAR_CHAR_TASKS_IMMEDIATELY rich_car_driver
			SET_CHAR_COORDINATES rich_car_driver -1924.2238 723.2358 44.4453 
			SET_CHAR_HEADING rich_car_driver 159.4748
		ENDIF

		// create prossie bitch
		CREATE_CHAR PEDTYPE_CIVFEMALE HFYPRO -1920.8837 718.9793 44.4453 girl1 
		SET_CHAR_HEADING girl1 0.0
		FLUSH_ROUTE
		EXTEND_ROUTE -1921.8186 720.5550 44.4453
		EXTEND_ROUTE -1926.2904 722.7397 44.4453
		OPEN_SEQUENCE_TASK temp_seq
			TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			IF NOT IS_CAR_DEAD rich_car
				TASK_ENTER_CAR_AS_PASSENGER -1 rich_car 20000 2
			ENDIF	
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK girl1 temp_seq
		CLEAR_SEQUENCE_TASK temp_seq
		SET_CHAR_NEVER_TARGETTED girl1 TRUE
		SET_CHAR_DECISION_MAKER girl1 empty_dm

		// create preacher
		CREATE_CHAR PEDTYPE_CIVMALE WMOPREA	-1923.5192 718.0508 44.9828 rich_dude
		SET_CHAR_HEADING rich_dude 0.0 
		SET_CHAR_DECISION_MAKER rich_dude empty_dm
		FLUSH_ROUTE
		EXTEND_ROUTE -1922.7072 719.7217 45.4453
		EXTEND_ROUTE -1924.6691 720.7556 45.4453
		EXTEND_ROUTE -1925.0269 720.9464 45.4453
		EXTEND_ROUTE -1926.1879 721.5566 45.4453	
		EXTEND_ROUTE -1927.0065 722.2399 45.4453
		OPEN_SEQUENCE_TASK temp_seq
			TASK_PAUSE -1 500
			TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			IF NOT IS_CHAR_DEAD girl1
				TASK_TURN_CHAR_TO_FACE_CHAR -1 girl1
			ENDIF
		CLOSE_SEQUENCE_TASK temp_seq
		PERFORM_SEQUENCE_TASK rich_dude temp_seq
		CLEAR_SEQUENCE_TASK temp_seq


		TIMERA  = 0

		SET_FIXED_CAMERA_POSITION -1918.2228 720.1056 46.0226 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1919.1565 720.4551 45.9459 JUMP_CUT

		m_goals++
	ENDIF

	IF m_goals = 6
		IF TIMERA > 7050
			SET_FIXED_CAMERA_POSITION -1933.5402 728.0091 46.4366 0.0 0.0 0.0
			IF NOT IS_CAR_DEAD rich_car
				POINT_CAMERA_AT_CAR rich_car FIXED JUMP_CUT

				IF NOT IS_CAR_DEAD mib_car[0]
					FREEZE_CAR_POSITION mib_car[0] FALSE
				ENDIF

				FREEZE_CAR_POSITION rich_car FALSE

				IF NOT IS_CHAR_DEAD girl1
					CLEAR_CHAR_TASKS_IMMEDIATELY girl1 
					WARP_CHAR_INTO_CAR_AS_PASSENGER girl1 rich_car 1
				ENDIF
					
				OPEN_CAR_DOOR rich_car REAR_RIGHT_DOOR

				IF NOT IS_CHAR_DEAD rich_dude
					CLEAR_CHAR_TASKS_IMMEDIATELY rich_dude
					SET_CHAR_COORDINATES rich_dude -1927.0804 723.2823 44.4453 
					SET_CHAR_HEADING rich_dude 299.9935
					TASK_ENTER_CAR_AS_PASSENGER rich_dude rich_car 20000 2
				ENDIF

				IF NOT IS_CHAR_DEAD rich_car_driver
					CLEAR_CHAR_TASKS_IMMEDIATELY rich_car_driver
					CLEAR_LOOK_AT rich_car_driver
					IF NOT IS_CAR_DEAD rich_car
						TASK_ENTER_CAR_AS_DRIVER rich_car_driver rich_car 5000
					ENDIF
				ENDIF

				m_goals++

			ENDIF
		ENDIF
	ENDIF

	// wait for everyone to get in car
	IF m_goals = 7
		IF NOT IS_CAR_DEAD rich_car
			IF NOT IS_CHAR_DEAD rich_car_driver
				IF IS_CHAR_IN_CAR rich_car_driver rich_car
					IF NOT IS_CHAR_DEAD rich_dude
						IF IS_CHAR_IN_CAR rich_dude rich_car
							IF dialogue_flag > 5	
								TASK_CAR_DRIVE_TO_COORD rich_car_driver rich_car -1902.0270 726.5126 45.2216 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
								TIMERA = 0
								m_goals++
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

//								IF NOT IS_CAR_DEAD mib_car[0]
//									SET_CAR_ESCORT_CAR_REAR mib_car[0] rich_car
//								ENDIF

	// wait for cars to drive off
	IF m_goals = 8
		IF TIMERA > 500
			IF NOT IS_CHAR_DEAD mib_driver[0]
				IF NOT IS_CAR_DEAD mib_car[0]
					TASK_CAR_DRIVE_TO_COORD mib_driver[0] mib_car[0] -1902.0270 726.5126 45.2216 18.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				ENDIF
			ENDIF
			TIMERA = 0
			m_goals++
		ENDIF	
	ENDIF
	
	IF m_goals = 9
		IF TIMERA > 2000
			m_goals = 99
		ENDIF
	ENDIF


	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_O
		IF NOT IS_CHAR_DEAD girl1
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS girl1 -1.0 0.0 0.0 x y z
			GET_CHAR_HEADING girl1 heading
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "preacher point = "
			SAVE_FLOAT_TO_DEBUG_FILE x
			SAVE_FLOAT_TO_DEBUG_FILE y
			SAVE_FLOAT_TO_DEBUG_FILE z
			SAVE_FLOAT_TO_DEBUG_FILE heading
		ENDIF
	ENDIF

	IF m_goals = 888
		IF TIMERA > 1000
			IF NOT IS_CAR_DEAD rich_car
				IF IS_CAR_STOPPED rich_car
					IF IS_RECORDING_GOING_ON_FOR_CAR rich_car
						STOP_RECORDING_CAR rich_car	
						GET_CAR_COORDINATES rich_car x y z
						GET_CAR_HEADING rich_car heading
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "rich car stopped at "
						SAVE_FLOAT_TO_DEBUG_FILE x
						SAVE_FLOAT_TO_DEBUG_FILE y
						SAVE_FLOAT_TO_DEBUG_FILE z
						SAVE_FLOAT_TO_DEBUG_FILE heading
					ENDIF
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD mib_car[0]
				IF IS_CAR_STOPPED mib_car[0]
					IF IS_RECORDING_GOING_ON_FOR_CAR mib_car[0]
						STOP_RECORDING_CAR mib_car[0]
						GET_CAR_COORDINATES mib_car[0] x y z
						GET_CAR_HEADING mib_car[0] heading
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "mib_car stopped at "
						SAVE_FLOAT_TO_DEBUG_FILE x
						SAVE_FLOAT_TO_DEBUG_FILE y
						SAVE_FLOAT_TO_DEBUG_FILE z
						SAVE_FLOAT_TO_DEBUG_FILE heading
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// cleanup cutscene
	IF m_goals = 99

		IF NOT DOES_CHAR_EXIST rich_dude
			CREATE_CHAR PEDTYPE_CIVMALE WMOPREA	0.0 0.0 0.0 rich_dude
			SET_CHAR_DECISION_MAKER rich_dude empty_dm
		ENDIF
		
		IF NOT DOES_CHAR_EXIST girl1
			CREATE_CHAR PEDTYPE_CIVFEMALE HFYPRO 0.0 0.0 0.0 girl1
			SET_CHAR_DECISION_MAKER girl1 empty_dm
		ENDIF


		MARK_MODEL_AS_NO_LONGER_NEEDED STRETCH
		MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
		MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB 
		MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45   
		MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG


		//GOSUB cleanup_car_phone_cam

		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

		SET_CAMERA_BEHIND_PLAYER 
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF

		IF NOT IS_CAR_DEAD mib_car[0]
			IF IS_PLAYBACK_GOING_ON_FOR_CAR mib_car[0]
				STOP_PLAYBACK_RECORDED_CAR mib_car[0]
			ENDIF
			FREEZE_CAR_POSITION mib_car[0] FALSE
			IF NOT IS_CHAR_DEAD mib_driver[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY mib_driver[0]
				WARP_CHAR_INTO_CAR mib_driver[0] mib_car[0]
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD rich_car
			IF IS_PLAYBACK_GOING_ON_FOR_CAR rich_car
				STOP_PLAYBACK_RECORDED_CAR rich_car
			ENDIF
			FREEZE_CAR_POSITION rich_car FALSE
		ENDIF

		IF NOT IS_CAR_DEAD rich_car
			IF NOT IS_CHAR_DEAD rich_car_driver
				CLEAR_CHAR_TASKS_IMMEDIATELY rich_car_driver
				WARP_CHAR_INTO_CAR rich_car_driver rich_car
			ENDIF
			IF NOT IS_CHAR_DEAD girl1
				CLEAR_CHAR_TASKS_IMMEDIATELY girl1
				WARP_CHAR_INTO_CAR_AS_PASSENGER girl1 rich_car 1
			ENDIF
			IF NOT IS_CHAR_DEAD rich_dude
				CLEAR_CHAR_TASKS_IMMEDIATELY rich_dude
				WARP_CHAR_INTO_CAR_AS_PASSENGER rich_dude rich_car 2
			ENDIF
			CLOSE_ALL_CAR_DOORS rich_car
		ENDIF

		IF NOT IS_CAR_DEAD mib_car[0]
			IF NOT IS_CAR_DEAD rich_car
				IF NOT IS_CHAR_DEAD mib_driver[0]
					START_PLAYBACK_RECORDED_CAR_USING_AI mib_car[0] 539
					SKIP_IN_PLAYBACK_RECORDED_CAR mib_car[0] 0.0
					SET_CAR_DRIVING_STYLE  mib_car[0] DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
					SET_CAR_PROOFS mib_car[0] FALSE FALSE FALSE FALSE FALSE
					LOCK_CAR_DOORS mib_car[0] CARLOCK_LOCKOUT_PLAYER_ONLY
					SET_CAR_HEAVY mib_car[0] TRUE
					SET_CAR_TRACTION mib_car[0] 2.0
				ENDIF
			ENDIF	
		ENDIF


		IF NOT IS_CAR_DEAD rich_car
			CLEAR_AREA -1908.7855 726.1257 45.0970 5.0 TRUE
			SET_CAR_COORDINATES rich_car -1908.7855 726.1257 45.0970
			SET_CAR_HEADING rich_car 273.1095
			FREEZE_CAR_POSITION rich_car FALSE
			SET_CAR_FORWARD_SPEED rich_car 10.0
			SET_CAR_PROOFS rich_car FALSE FALSE FALSE FALSE FALSE
			LOCK_CAR_DOORS rich_car CARLOCK_LOCKOUT_PLAYER_ONLY
			SET_CAR_HEAVY rich_car TRUE
			SET_CAR_TRACTION rich_car 2.0
			SET_CAR_HEALTH rich_car 1850
			START_PLAYBACK_RECORDED_CAR_USING_AI rich_Car 539
			SKIP_IN_PLAYBACK_RECORDED_CAR rich_Car 5.0
			SET_CAR_DRIVING_STYLE  rich_Car DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
			ADD_UPSIDEDOWN_CAR_CHECK rich_Car
			ADD_STUCK_CAR_CHECK rich_car 2.0 3000
		ENDIF

		SWITCH_PED_ROADS_ON -1950.0 718.0 44.0 -1912.0 731.0 48.0 
		SWITCH_ROADS_ON -1950.0 718.0 44.0 -1912.0 731.0 48.0 
		
		CLEAR_PRINTS
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 ON
		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		m_stage++
		m_goals = 0
	ENDIF 
RETURN

// *************************************************************************************************************
//						STAGE 10 -  End chase
// *************************************************************************************************************
SYN2_m_stage_7:

	LVAR_INT rich_dude_blip
	LVAR_INT player_has_attacked_convoy
	LVAR_INT rich_help
	LVAR_FLOAT car_speed 	   
	LVAR_INT last_car_health
	LVAR_INT this_car_health


	LVAR_INT mib_car[4]
	LVAR_INT mib_driver[4]

	LVAR_INT mib_driver_blip
	LVAR_INT mib_passenger_blip

//		LVAR_INT test_car1
//		LVAR_INT test_driver1

	// initialise chase section
	IF m_goals = 0
		
		SUPPRESS_CAR_MODEL STRETCH
		SUPPRESS_CAR_MODEL HUNTLEY
  	  
		car_speed = 15.0

		IF NOT IS_CHAR_DEAD rich_dude
			ADD_BLIP_FOR_CHAR rich_dude rich_dude_blip
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR rich_dude FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD girl1
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR girl1 FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD rich_car_driver
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR rich_car_driver FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD mib_driver[0]
			ADD_BLIP_FOR_CHAR mib_driver[0] mib_driver_blip
		ENDIF
		IF NOT IS_CHAR_DEAD mib_passenger
			ADD_BLIP_FOR_CHAR mib_passenger mib_passenger_blip
		ENDIF
		
	
		player_has_attacked_convoy = 0
		rich_help = 0
		syn2_time_until_backup = 30000
		//backup_call_state = 0
		player_has_attacked_convoy = 0
		rich_car_timer = 0

		CLEAR_PRINTS
		PRINT_NOW SYN2_70 10000 1 // kill the rich punter
		
		IF NOT IS_CAR_DEAD rich_car
			LOCK_CAR_DOORS rich_car CARLOCK_LOCKED
		ENDIF

		
		SET_CAR_DENSITY_MULTIPLIER 0.65
		SET_PED_DENSITY_MULTIPLIER 1.0

		LVAR_INT tell_player_to_kill_punter
		tell_player_to_kill_punter = 0

		m_goals++
	ENDIF

	
	IF tell_player_to_kill_punter = 0
		IF DOES_BLIP_EXIST jizzy_car_blip
			PRINT SYN_M_1 5000 1 // get back in the pimpmobile
			tell_player_to_kill_punter = 1	
		ENDIF
	ELSE
		IF NOT DOES_BLIP_EXIST jizzy_car_blip
			CLEAR_THIS_PRINT SYN_M_1 
			PRINT SYN2_70 5000 1 // kill the rich punter
			tell_player_to_kill_punter = 0	
		ENDIF
	ENDIF

	IF m_goals = 1

		// ******* CONTROL SPEED OF LIMO ****************
		LVAR_FLOAT limo_playback_speed
		LVAR_FLOAT last_limo_distance
		LVAR_INT limo_flag
		IF NOT IS_CAR_DEAD rich_car
			IF player_has_attacked_convoy = 0
				IF IS_PLAYBACK_GOING_ON_FOR_CAR rich_car 
					limo_playback_speed = 0.3
				ELSE
					IF NOT limo_flag = 1
						IF NOT IS_CHAR_DEAD rich_car_driver
							TASK_CAR_DRIVE_WANDER rich_car_driver rich_car 50.0 DRIVINGMODE_AVOIDCARS
							limo_flag = 1
						ENDIF
					ENDIF
				ENDIF	
			ELSE
				
				IF IS_PLAYBACK_GOING_ON_FOR_CAR rich_car

					// determine if player is ahead of or behind limo
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 10.0 0.0 x y z
					GET_CHAR_COORDINATES scplayer x2 y2 z2
					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float

					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 -10.0 0.0 x y z
					GET_CHAR_COORDINATES scplayer x2 y2 z2
					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2

					// ahead of limo
					IF temp_float < temp_float2

						limo_playback_speed +=@ 0.1 

					ELSE
					// behind limo

						GET_CAR_COORDINATES rich_car x y z
						GET_CHAR_COORDINATES scplayer x2 y2 z2
						GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 temp_float

						IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer rich_car 20.0 20.0 FALSE
							limo_playback_speed +=@ 0.05
						ELSE
							IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer rich_car 25.0 25.0 FALSE
								limo_playback_speed +=@ -0.01
							ENDIF
						ENDIF

						IF limo_playback_speed < 0.6
							limo_playback_speed = 0.6
						ENDIF

						
						last_limo_distance = temp_float  
								
					ENDIF

				ELSE

					IF NOT limo_flag = 1
						IF NOT IS_CHAR_DEAD rich_car_driver
							TASK_CAR_DRIVE_WANDER rich_car_driver rich_car 50.0 DRIVINGMODE_AVOIDCARS
							limo_flag = 1
						ENDIF
					ENDIF

				ENDIF

			ENDIF
			
			IF limo_playback_speed > 1.5
				limo_playback_speed = 1.5
			ENDIF				

			IF IS_PLAYBACK_GOING_ON_FOR_CAR rich_car
				//WRITE_DEBUG_WITH_FLOAT limo_playback_speed limo_playback_speed 
				SET_PLAYBACK_SPEED rich_car limo_playback_speed
			ENDIF

			// **** check if it's stuck on roof ****
			IF IS_CAR_STUCK_ON_ROOF rich_car
				IF NOT IS_CAR_ON_SCREEN rich_Car
					GET_CAR_COORDINATES rich_Car x y z
					GET_CAR_HEADING rich_car heading
					z += 1.0
					SET_CAR_COORDINATES rich_car x y z
					SET_CAR_HEADING rich_car heading
				ENDIF
			ENDIF

			// *** check if it's stuck ***
			IF IS_CAR_STUCK	rich_car
				IF NOT IS_CAR_ON_SCREEN rich_car
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer rich_car 20.0 20.0 20.0 FALSE
						GET_CAR_COORDINATES rich_car x y z
						temp_int = 1
						WHILE temp_int < 20
							GET_NTH_CLOSEST_CAR_NODE x y z temp_int x2 y2 z2
							IF NOT IS_POINT_ON_SCREEN x2 y2 z2 5.0
								IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x2 y2 z2 5.0 5.0 5.0
									temp_int = 25
								ELSE
									temp_int++
								ENDIF
							ELSE
								temp_int++
							ENDIF	
						ENDWHILE
						IF temp_int = 25
							SET_CAR_COORDINATES rich_car x y z
						ENDIF
					ELSE
						IF NOT IS_CHAR_DEAD rich_car_driver
							TASK_CAR_TEMP_ACTION rich_car_driver rich_car TEMPACT_REVERSE 1000
						ENDIF
					ENDIF
				ELSE
					SET_CAR_FORWARD_SPEED rich_car -10.0	
				ENDIF
			ENDIF

		ENDIF

		

		// ******* SLOW DOWN / SPEED UP BACK CAR ************************
		LVAR_FLOAT mib_car_playback_speed[2]
		LVAR_INT mib_car_flag[2]
		IF NOT IS_CAR_DEAD mib_Car[0]
			IF IS_PLAYBACK_GOING_ON_FOR_CAR mib_car[0]
				IF NOT IS_CAR_DEAD rich_car

					// work out if car is behind or infront of limo
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 -10.0 0.0 x y z
					GET_CAR_COORDINATES mib_car[0] x2 y2 z2
					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float

					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 -20.0 0.0 x y z
					GET_CAR_COORDINATES mib_car[0] x2 y2 z2
					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2

					// ahead of limo offset
					IF temp_float < temp_float2
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 -15.0 0.0 x y z
						IF NOT LOCATE_CAR_3D  mib_car[0]	x y z 5.0 5.0 5.0 FALSE
							mib_car_playback_speed[0] +=@ -0.2
						ELSE
							mib_car_playback_speed[0] = limo_playback_speed
						ENDIF
					ELSE
					// behind limo offset
						IF NOT mib_car_flag[0] = 2
							SET_CAR_DRIVING_STYLE mib_car[0] DRIVINGMODE_PLOUGHTHROUGH 
							mib_car_flag[0] = 2
						ENDIF
						mib_car_playback_speed[0] = limo_playback_speed	+ 3.0
					ENDIF
					SET_PLAYBACK_SPEED mib_car[0] mib_car_playback_speed[0]
					LVAR_FLOAT rich_car_x rich_car_y rich_car_z
					GET_CAR_COORDINATES rich_car rich_car_x rich_car_y rich_car_z
				ELSE
					STOP_PLAYBACK_RECORDED_CAR mib_car[0]
				ENDIF
			
				
			ELSE

		
				IF NOT IS_CAR_DEAD rich_car
					IF NOT mib_car_flag[0] = 99
						IF NOT IS_CHAR_DEAD mib_driver[0]
							TASK_CAR_MISSION mib_driver[0] mib_car[0] rich_Car MISSION_ESCORT_REAR 100.0 DRIVINGMODE_PLOUGHTHROUGH	
							mib_car_flag[0] = 99
						ENDIF
					ENDIF
					GET_CAR_COORDINATES rich_car rich_car_x rich_car_y rich_car_z
				ELSE
					IF mib_car_flag[0] < 100
						IF NOT IS_CHAR_DEAD mib_driver[0]
							TASK_CAR_DRIVE_TO_COORD mib_driver[0] mib_car[0] rich_car_x rich_car_y rich_car_z 40.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS	
							mib_car_flag[0] = 100
						ENDIF
					ELSE
						IF LOCATE_CAR_3D mib_car[0] rich_car_x rich_car_y rich_car_z 20.0 20.0 10.0 FALSE
							IF NOT mib_car_flag[0] = 101
								IF NOT IS_CHAR_DEAD mib_driver[0]
									OPEN_SEQUENCE_TASK temp_seq
										TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
										TASK_KILL_CHAR_ON_FOOT -1 scplayer
										TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK mib_driver[0] temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								IF NOT IS_CHAR_DEAD mib_passenger
									OPEN_SEQUENCE_TASK temp_seq
										TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
										TASK_KILL_CHAR_ON_FOOT -1 scplayer
										TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK mib_passenger temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								mib_car_flag[0] = 101
							ENDIF	
						ENDIF
					ENDIF
				ENDIF


			ENDIF
		ENDIF


		// ******* WAIT FOR PLAYER TO ATTACK LIMO **********************
		IF player_has_attacked_convoy = 0
			IF NOT IS_CAR_DEAD rich_car	
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR rich_car scplayer
					//CLEAR_PRINTS						
					//PRINT_NOW SYN2_21 4000 1 // RICH PUNTER - 'Ahhh, we're being attacked!'
					player_has_attacked_convoy = 1
					attack_conversation = 1
					TIMERA = 0
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD mib_car[0]
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR mib_car[0] scplayer
					//CLEAR_PRINTS						
					//PRINT_NOW SYN2_21 4000 1 // RICH PUNTER - 'Ahhh, we're being attacked!'
					player_has_attacked_convoy = 1
					attack_conversation = 1
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD rich_car
				IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer rich_car 30.0 30.0 30.0 FALSE
					IF suspicion_timer > 5000
						//CLEAR_PRINTS						
						attack_conversation = 1
						player_has_attacked_convoy = 1
					ENDIF	
				ELSE
					suspicion_timer = 0 
				ENDIF
			ENDIF



			IF player_has_attacked_convoy = 1
				IF NOT IS_CAR_DEAD rich_car
					SET_CAR_DRIVING_STYLE rich_car DRIVINGMODE_AVOIDCARS
				ENDIF
				IF NOT IS_CAR_DEAD rich_car
					GET_CAR_HEALTH rich_car rich_car_health
					next_rich_car_stop_health = rich_car_health - 100
					rich_backup_timer = 0
				ENDIF
			ENDIF
		ENDIF
		


		// **************** DIALOGUE WHEN PLAYER FIRST ATTACKS CONVOY ****************
		LVAR_INT attack_conversation
		IF attack_conversation > 0
		AND attack_conversation < 7
			IF TIMERB > 1000
				IF NOT IS_CHAR_DEAD rich_dude
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer rich_dude 20.0 20.0 20.0 FALSE
						IF audio_line_is_active = 0
							SWITCH attack_conversation
								CASE 1
									//[SYN2_JA]	The Devil comes to claim his own, Godspeed, driver, Godspeed!
									$audio_string    = &SYN2_JA					  
									audio_sound_file = SOUND_SYN2_JA
									START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
									CLEAR_MISSION_AUDIO 2
									LOAD_MISSION_AUDIO 2 SOUND_SYN2_JB
									attack_conversation++
									TIMERB = 0
								BREAK
								CASE 2
									// [SYN2_JB]	We need more than just the Lord’s benifaction!
									$audio_string    = &SYN2_JB					  
									audio_sound_file = SOUND_SYN2_JB
									START_NEW_SCRIPT audio_line rich_dude 0 1 2 1
									CLEAR_MISSION_AUDIO 1
									LOAD_MISSION_AUDIO 1 SOUND_SYN2_JE
									attack_conversation++
									TIMERB = 0
								BREAK
								CASE 3
									// [SYN2_JE]	Don’t worry, girl, the Lord’s army will come to our aid!
									$audio_string    = &SYN2_JE					  
									audio_sound_file = SOUND_SYN2_JE
									START_NEW_SCRIPT audio_line rich_dude 0 1 1 1
									CLEAR_MISSION_AUDIO 2
									LOAD_MISSION_AUDIO 2 SOUND_SYN2_JF
									attack_conversation++
									TIMERB = 0
								BREAK
								CASE 4
									// [SYN2_JF]	Now just keep undressing...
									$audio_string    = &SYN2_JF					  
									audio_sound_file = SOUND_SYN2_JF
									START_NEW_SCRIPT audio_line rich_dude 0 1 2 1
									CLEAR_MISSION_AUDIO 1
									LOAD_MISSION_AUDIO 1 SOUND_SYN2_JG
									attack_conversation++
									TIMERB = 0
								BREAK
								CASE 5
									// [SYN2_JF]	That’s right, put your hand there...
									$audio_string    = &SYN2_JG					  
									audio_sound_file = SOUND_SYN2_JG
									START_NEW_SCRIPT audio_line rich_dude 0 1 1 1
		  							attack_conversation++
									TIMERB = 0
								BREAK
								CASE 6
									attack_conversation++
									IF NOT IS_CAR_DEAD rich_car
										CLEAR_CAR_LAST_DAMAGE_ENTITY rich_car
									ENDIF
								BREAK
							ENDSWITCH
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
				
		// *************  DIALOGUE IF PLAYER HITS CAR ********************
		LVAR_INT damaged_preacher_dialogue
		LVAR_INT damaged_preacher_timer
		IF attack_conversation = 7
		AND damaged_preacher_timer > 10000
			IF NOT IS_CAR_DEAD rich_car
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR rich_car scplayer
					IF NOT IS_MESSAGE_BEING_DISPLAYED 
						damaged_preacher_dialogue++
						IF damaged_preacher_dialogue > 4
							damaged_preacher_dialogue = 0
						ENDIF

						SWITCH damaged_preacher_dialogue
							CASE 0
								// Be gone, foul abomination!
								$audio_string    = &SYN2_KA					  
								audio_sound_file = SOUND_SYN2_KA
								START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
							BREAK
							CASE 1
								// Argh, he comes for us!
								$audio_string    = &SYN2_KB					  
								audio_sound_file = SOUND_SYN2_KB
								START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
							BREAK
							CASE 2
								// Faster, driver, faster!
								$audio_string    = &SYN2_KC					  
								audio_sound_file = SOUND_SYN2_KC
								START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
							BREAK
							CASE 3
								// Ow! Watch those teeth, girl!
								$audio_string    = &SYN2_KD					  
								audio_sound_file = SOUND_SYN2_KD
								START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
							BREAK
							CASE 4
								// Argh! Careful with the ‘little bishop’, whore!
								$audio_string    = &SYN2_KE					  
								audio_sound_file = SOUND_SYN2_KE
								START_NEW_SCRIPT audio_line rich_dude 0 1 1 0
							BREAK
						ENDSWITCH
						damaged_preacher_timer = 0
						CLEAR_CAR_LAST_DAMAGE_ENTITY rich_car
					ELSE
						CLEAR_CAR_LAST_DAMAGE_ENTITY rich_car
					ENDIF
				ENDIF
			ENDIF
		ENDIF 


				
//		// ****************       UPDATE BACK CAR AI      ******************
		IF player_has_attacked_convoy = 1
			IF NOT IS_CHAR_DEAD mib_passenger
				IF NOT IS_CAR_DEAD mib_car[0]
					IF NOT IS_CHAR_DEAD mib_driver[0]
						IF IS_CHAR_IN_CAR mib_passenger mib_car[0]
//							GET_SCRIPT_TASK_STATUS mib_passenger TASK_DRIVE_BY temp_int
//							IF temp_int = FINISHED_TASK
//								TASK_DRIVE_BY mib_passenger scplayer -1 0.0 0.0 0.0 20.0 DRIVEBY_AI_ALL_DIRN FALSE 20
//							ENDIF
						ELSE
							GET_SCRIPT_TASK_STATUS mib_passenger PERFORM_SEQUENCE_TASK temp_int
							IF temp_int = FINISHED_TASK 
								OPEN_SEQUENCE_TASK temp_seq
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									TASK_KILL_CHAR_ON_FOOT -1 scplayer
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK mib_passenger temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
							ENDIF
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS mib_passenger PERFORM_SEQUENCE_TASK temp_int
						IF temp_int = FINISHED_TASK 
							OPEN_SEQUENCE_TASK temp_seq
								TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								TASK_KILL_CHAR_ON_FOOT -1 scplayer
								TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK mib_passenger temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	

		// ************ warp landrovers if they get stuck *************
		// if car is no where near player or rich car warp closer to rich car
		temp_int = 0
		WHILE temp_int < 2
			IF DOES_VEHICLE_EXIST mib_car[temp_int]
				IF NOT IS_CAR_DEAD mib_car[temp_int]
					IF NOT IS_CAR_DEAD rich_car
						GET_CAR_COORDINATES rich_car x y z
						IF NOT LOCATE_CAR_3D mib_car[temp_int] x y z 80.0 80.0 80.0 FALSE
							GET_CHAR_COORDINATES scplayer x y z
							IF NOT LOCATE_CAR_3D mib_car[temp_int] x y z 40.0 40.0 40.0 FALSE
								IF NOT IS_CAR_ON_SCREEN mib_car[temp_int]
									GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rich_car 0.0 -15.0 0.0 x y z
									temp_int2 = 1
									WHILE temp_int2 < 20
										GET_NTH_CLOSEST_CAR_NODE x y z temp_int2 x2 y2 z2
										IF NOT IS_POINT_ON_SCREEN x2 y2 z2 5.0
											IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x2 y2 z2 5.0 5.0 5.0
												temp_int2 = 25
											ELSE
												temp_int2++
											ENDIF
										ELSE
											temp_int2++
										ENDIF	
									ENDWHILE
									IF temp_int2 = 25
										SET_CAR_COORDINATES mib_car[temp_int] x2 y2 z2
										IF NOT IS_CAR_DEAD rich_car
											GET_CAR_COORDINATES rich_car x y z
										ENDIF
										vec_x = x - x2
										vec_y = y - y2
										GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
										SET_CAR_HEADING mib_car[temp_int] heading
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE


				
		IF debug_on = 1
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				IF NOT IS_CAR_DEAD rich_car
					SET_CAR_HEALTH rich_Car 499
				ENDIF
			ENDIF
		ENDIF

		// if all guys are dead goto next stage
		temp_int = 0
		IF IS_CHAR_DEAD rich_dude
			IF DOES_BLIP_EXIST rich_dude_blip
				REMOVE_BLIP rich_dude_blip
			ENDIF
			temp_int++
 		ENDIF
		IF IS_CHAR_DEAD mib_driver[0]
			IF DOES_BLIP_EXIST mib_driver_blip
				REMOVE_BLIP mib_driver_blip
			ENDIF
			temp_int++
		ENDIF
		IF IS_CHAR_DEAD mib_passenger
			IF DOES_BLIP_EXIST mib_passenger_blip
				REMOVE_BLIP mib_passenger_blip
			ENDIF
			temp_int++
		ENDIF
		IF temp_int = 3
			//WRITE_DEBUG stage_passed
			m_goals = 99
		ENDIF

		// check if player loses limo
		IF NOT IS_CHAR_DEAD rich_dude
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rich_dude	150.0 150.0 FALSE 
				IF rich_car_timer > 20000
					GOSUB cleanup_car_phone_cam
					PRINT_NOW SYN2_71 5000 1 // you lost the preacher
					m_failed = 1
				ENDIF
			ELSE
				rich_car_timer = 0
			ENDIF
		ENDIF
		
							
		//GOSUB update_rich_chasers	  

	ENDIF
	

	IF m_goals = 99
		//CLEAR_ONSCREEN_TIMER syn2_time_until_backup
		m_stage++
		m_goals = 0
		TIMERA = 0

		LOAD_MISSION_AUDIO 3 SOUND_CAR_PHONE_RING
		WHILE NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE
	ENDIF

RETURN







// *************************************************************************************************************
//						STAGE 11 -  Final Phone call from Jizzy
// *************************************************************************************************************
SYN2_m_stage_8:

	// make phone ring
	IF m_goals = 0
		IF TIMERA > 3000
			// start phone sound
			IF NOT IS_CAR_DEAD jizzy_car 
				IF NOT IS_CHAR_IN_CAR scplayer jizzy_car
					PRINT SYN_M_1 7000 1 // get back in the pimpmobile
				ENDIF
				GET_CAR_COORDINATES jizzy_car x y z
				ATTACH_MISSION_AUDIO_TO_CAR 3 jizzy_car
				PLAY_MISSION_AUDIO 3
				phone_ringing = 1
			ENDIF
			TIMERA = 0
			//PRINT_NOW SYN_M_2 4000 1
			m_goals++
		ENDIF
	ENDIF

	// wait for player to get in car
	IF m_goals = 1
		
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CHAR_IN_CAR scplayer jizzy_car
				IF TIMERA > 2000
					SET_PLAYER_CONTROL player1 OFF
					stored_weapon_model = -1
					TIMERA = 0
					m_goals++	 
				ENDIF
				CLEAR_THIS_PRINT SYN_M_1						
			ENDIF
		ENDIF
		
	ENDIF
			
	// wait a couple of rings before player answers phone		
	IF m_goals = 2
		IF NOT IS_CAR_DEAD jizzy_car
			IF IS_CHAR_IN_CAR scplayer jizzy_car
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 3
				phone_ringing = 0
				m_goals++
			ENDIF 	
		ENDIF
	ENDIF


	// phone cutscene
	IF m_goals = 3
		car_phone_cam_int = 0
		stored_weapon_model = -1
		CLEAR_PRINTS
		
		DO_FADE 150 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		GOSUB setup_car_phone_cam


		DO_FADE 150 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		IF NOT IS_CAR_DEAD mib_car[0]
			DELETE_CAR mib_car[0]
		ENDIF
		IF NOT IS_CAR_DEAD mib_car[1]
			DELETE_CAR mib_car[1]
		ENDIF

		m_goals++
	ENDIF

	// wait a tic before starting text
	IF m_goals = 4
		IF TIMERA > 1000

			dialogue_flag = 0

			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF

	IF m_goals > 4
	AND m_goals < 99
		IF audio_line_is_active = 0 
			IF dialogue_timer > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &SYN2_GA					  
						audio_sound_file = SOUND_SYN2_GA
						START_NEW_SCRIPT audio_line scplayer 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_GB
						dialogue_flag++
					BREAK
					CASE 1
						$audio_string    = &SYN2_GB					  
						audio_sound_file = SOUND_SYN2_GB
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_GC
						dialogue_flag++
					BREAK
					CASE 2
						$audio_string    = &SYN2_GC					  
						audio_sound_file = SOUND_SYN2_GC
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_GD
						dialogue_flag++
					BREAK
					CASE 3
						$audio_string    = &SYN2_GD					  
						audio_sound_file = SOUND_SYN2_GD
						START_NEW_SCRIPT audio_line -1 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SYN2_GF
						dialogue_flag++
					BREAK
					CASE 4
						$audio_string    = &SYN2_GF					  
						audio_sound_file = SOUND_SYN2_GF
						START_NEW_SCRIPT audio_line -1 0 1 1 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_SYN2_GE
						dialogue_flag++
					BREAK
					CASE 5
						$audio_string    = &SYN2_GE					  
						audio_sound_file = SOUND_SYN2_GE
						START_NEW_SCRIPT audio_line scplayer 0 1 2 1
						dialogue_flag++
					BREAK
					CASE 6
						dialogue_flag++
					BREAK
				ENDSWITCH
				dialogue_timer = 0
			ENDIF
		ENDIF
	ENDIF

	// wait for cutscene to finish
	IF m_goals = 5
		IF dialogue_flag > 6
		OR IS_BUTTON_PRESSED PAD1 CROSS
			m_goals = 99
		ENDIF
	ENDIF

	IF m_goals = 99
		
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		GOSUB cleanup_car_phone_cam
		IF NOT IS_CAR_DEAD jizzy_car
			GET_CAR_COORDINATES jizzy_car x y z
			CLEAR_AREA x y z 15.0 TRUE
		ENDIF

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		audio_line_is_active = 0

		IF NOT IS_WANTED_LEVEL_GREATER player1 1
			ALTER_WANTED_LEVEL player1 2
		ENDIF

		m_passed = 1
		m_stage++
	ENDIF

RETURN





// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
SYN2_GLOBAL_SHIT:

	IF phone_ringing = 1
		IF HAS_MISSION_AUDIO_FINISHED 3
			PLAY_MISSION_AUDIO 3
		ENDIF
	ENDIF

	IF m_stage < 7
		IF DOES_CHAR_EXIST girl1
			IF IS_CHAR_DEAD girl1
				GOSUB cleanup_car_phone_cam
				PRINT_NOW SYN_F_5 5000 1 // Jizzy's girl is history
				m_failed = 1
			ENDIF
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST girl2
		IF IS_CHAR_DEAD girl2
			GOSUB cleanup_car_phone_cam
			PRINT_NOW SYN_F_5 5000 1 // Jizzy's girl is history
			m_failed = 1
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST girl3
		IF IS_CHAR_DEAD girl3
			GOSUB cleanup_car_phone_cam
			PRINT_NOW SYN_F_5 5000 1 // Jizzy's girl is history
			m_failed = 1
		ENDIF
	ENDIF

	// fail conditions
	IF DOES_VEHICLE_EXIST jizzy_car
		IF IS_CAR_DEAD jizzy_car
			GOSUB cleanup_car_phone_cam
			PRINT_NOW SYN_F_2 5000 1 // ~s~You destroyed Jizzy's car.
			m_failed = 1
		ENDIF
	ENDIF	

//	// run out of time
//	IF m_stage > 2
//		IF syn2_timer = 0
//			PRINT_NOW SYN_F_6 5000 1 // you took too long
//			m_failed = 1
//		ENDIF
//	ENDIF

	// remove / create blip for jizzy's car
	IF m_stage > 2
		IF NOT IS_CAR_DEAD jizzy_car
			IF NOT IS_CHAR_IN_CAR scplayer jizzy_car
				IF NOT DOES_BLIP_EXIST jizzy_car_blip
					ADD_BLIP_FOR_CAR jizzy_car jizzy_car_blip
					SET_BLIP_AS_FRIENDLY jizzy_car_blip TRUE
				ENDIF
			ELSE
				IF DOES_BLIP_EXIST jizzy_car_blip
					REMOVE_BLIP jizzy_car_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************
pimp_ditches_girl:

	//IF girl_is_with_rival = 1
		IF NOT IS_CHAR_DEAD girl2
			CLEAR_CHAR_TASKS girl2
			OPEN_SEQUENCE_TASK temp_seq
				//TASK_FALL_AND_GET_UP -1 1 3000
				TASK_DUCK -1 2000
				IF NOT IS_CHAR_DEAD rival_pimp
					TASK_SMART_FLEE_CHAR -1 rival_pimp 100.0 60000
				ENDIF
				TASK_WANDER_STANDARD -1
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK girl2 temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
			//PRINT_NOW SYN_D07 5000 1 // your not worth the trouble bitch	
			girl_is_with_rival = 0
			pimp_is_waiting = 99
		ENDIF
	//ENDIF

RETURN



	   

LVAR_INT car_phone_cam_int 
LVAR_INT on_cutscene_duty
LVAR_INT stored_weapontype
LVAR_INT stored_ammo
LVAR_INT stored_weapon_model
LVAR_INT player_has_brassknuckle
LVAR_INT current_char_weapontype
setup_car_phone_cam:

	on_cutscene_duty = 1


	// weapon stuff ////////////////////////////

	// store current weapon
	GET_CURRENT_CHAR_WEAPON scplayer current_char_weapontype

	// remove brassknuckle
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE
		player_has_brassknuckle = 1
		REQUEST_MODEL BRASSKNUCKLE
		WHILE NOT HAS_MODEL_LOADED BRASSKNUCKLE
			WAIT 0
		ENDWHILE
	ELSE
		player_has_brassknuckle = 0
	ENDIF
	
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

	/////////////////////////////////////////////


	// INCAR CUTSCENE ------------------
	IF NOT HAS_ANIMATION_LOADED CAR_CHAT
		REQUEST_ANIMATION CAR_CHAT
		WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0
		ENDWHILE
	ENDIF

	IF NOT HAS_MODEL_LOADED CELLPHONE
		REQUEST_MODEL CELLPHONE
		WHILE NOT HAS_MODEL_LOADED CELLPHONE
			WAIT 0
		ENDWHILE
	ENDIF

//	IF NOT HAS_MODEL_LOADED COLT45
//		REQUEST_MODEL COLT45
//		WHILE NOT HAS_MODEL_LOADED COLT45
//			WAIT 0
//		ENDWHILE
//	ENDIF

	IF NOT IS_CAR_DEAD jizzy_car
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car -0.4900 0.0500 0.1830  x y z
		CLEAR_AREA x y z 2.0 TRUE

		GET_CAR_COORDINATES jizzy_car x y z
		GET_CAR_HEADING jizzy_car heading
		SET_CAR_COORDINATES jizzy_car x y z
		SET_CAR_HEADING jizzy_car heading

	ENDIF

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	//FREEZE_ONSCREEN_TIMER TRUE


	IF NOT IS_CAR_DEAD jizzy_car
		FREEZE_CAR_POSITION jizzy_car TRUE
		CLOSE_ALL_CAR_DOORS jizzy_car
	ENDIF
	
	GET_CHAR_COORDINATES scplayer x y z
	
	IF car_phone_cam_int = 0
		IF NOT IS_CAR_DEAD jizzy_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car 0.0100 0.7500 0.7700  x y z	
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car -0.3600 0.1200 0.6600  vec_x vec_y vec_z	
		ENDIF
	ENDIF
	IF car_phone_cam_int = 1							  
		IF NOT IS_CAR_DEAD jizzy_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car -0.0400 0.6800 0.6900  x y z	
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car -0.5500 -0.2900 0.6400   vec_x vec_y vec_z	
		ENDIF
	ENDIF
	IF car_phone_cam_int = 2
		IF NOT IS_CAR_DEAD jizzy_car
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car 0.5100 -0.9000 0.8400  x y z	
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS jizzy_car -0.4100 -0.1400 0.6400   vec_x vec_y vec_z	
		ENDIF
	ENDIF

	CREATE_OBJECT cellphone x y z jizzy_phone

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	IF NOT IS_CAR_DEAD jizzy_car			  
		ATTACH_CHAR_TO_CAR scplayer jizzy_car -0.4900 0.0500 0.1830 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED 
	ENDIF	
	

	IF car_phone_cam_int = 0
		OPEN_SEQUENCE_TASK temp_seq
			TASK_PICK_UP_OBJECT -1 jizzy_phone 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_out CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
		CLOSE_SEQUENCE_TASK temp_seq
	ENDIF

	IF car_phone_cam_int = 1					
		OPEN_SEQUENCE_TASK temp_seq
			TASK_PICK_UP_OBJECT -1 jizzy_phone 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA_to_B CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopB CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopB_to_A CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 4.0 TRUE FALSE FALSE TRUE -1
			//TASK_PLAY_ANIM -1 carfone_out CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
		CLOSE_SEQUENCE_TASK temp_seq
	ENDIF

	IF car_phone_cam_int = 2
		OPEN_SEQUENCE_TASK temp_seq
			TASK_PICK_UP_OBJECT -1 jizzy_phone 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 10000.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA_to_B CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopB CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopB_to_A CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_loopA CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PLAY_ANIM -1 carfone_out CAR_CHAT 4.0 FALSE FALSE FALSE TRUE -1
		CLOSE_SEQUENCE_TASK temp_seq
	ENDIF

	PERFORM_SEQUENCE_TASK scplayer temp_seq
	CLEAR_SEQUENCE_TASK temp_seq
			
	WAIT 0
												 
	SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT vec_x vec_y vec_z JUMP_CUT
	SET_NEAR_CLIP 0.01
	
	TIMERA = 0

RETURN

cleanup_car_phone_cam:

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	// weapon stuff ////////////////////////////
	// give smg weapon back to char
	
	// give back brassknuckle
	IF player_has_brassknuckle = 1
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE 1
		player_has_brassknuckle = 0
		MARK_MODEL_AS_NO_LONGER_NEEDED BRASSKNUCKLE
	ENDIF

	// give back current weapon
	SET_CURRENT_CHAR_WEAPON scplayer current_char_weapontype

	////////////////////////////////////////////


	CLEAR_PRINTS
	SET_PLAYER_CONTROL player1 ON

	IF NOT IS_CAR_DEAD jizzy_car
		//SET_CAN_BURST_CAR_TYRES jizzy_car TRUE
		FREEZE_CAR_POSITION jizzy_car FALSE
		IF on_cutscene_duty = 1
			DETACH_CHAR_FROM_CAR scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			WARP_CHAR_INTO_CAR scplayer jizzy_car 
			DELETE_OBJECT jizzy_phone
		ENDIF
	ENDIF

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF

	REMOVE_BLIP mib_driver_blip
	REMOVE_BLIP mib_passenger_blip

//	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
//	REMOVE_ANIMATION CAR_CHAT

	on_cutscene_duty = 0

//	// dunno if we need these
//	RESTORE_CAMERA_JUMPCUT
//	SET_CAMERA_BEHIND_PLAYER
//	SET_PLAYER_CONTROL player1 ON
//	SWITCH_WIDESCREEN OFF
//	FREEZE_ONSCREEN_TIMER FALSE
//	CLEAR_PRINTS

RETURN


// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_SYN2:

	PRINT_BIG M_FAIL 5000 1

RETURN

// PASS
mission_passed_SYN2:
	
	IF syn2_mission_attempts = 0
		REMOVE_BLIP synd_contact_blip
		REMOVE_BLIP garage_contact_blip
		REMOVE_BLIP scrash_contact_blip
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT syndX syndY syndZ synd_blip_icon synd_contact_blip
		flag_synd_mission_counter ++
		syn2_mission_attempts = 1
		PRINT_NOW SYN2_60 10000 1 // go to jizzy's club

	ELSE

		flag_synd_mission_counter ++

		REGISTER_MISSION_PASSED SYND_2

		PRINT_WITH_NUMBER_BIG ( M_PASSS ) 3000 5000 1 //"Mission Passed!"
		ADD_SCORE player1 3000 //amount of cash reward
		AWARD_PLAYER_MISSION_RESPECT 20 //amount of respect
		PLAY_MISSION_PASSED_TUNE 1
		
		SET_INT_STAT PASSED_SYNDICATE2 1

		PLAYER_MADE_PROGRESS 1
		
	ENDIF

RETURN

// CLEANUP
mission_cleanup_SYN2:

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
	ENDIF

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3

	IF NOT IS_CAR_DEAD jizzy_car
		SET_CAN_BURST_CAR_TYRES jizzy_car TRUE
	ENDIF

	IF IS_PLAYER_PLAYING player1
		CLEAR_CHAR_TASKS scplayer
	ENDIF

	SWITCH_PED_ROADS_ON -2431.1145 -169.4874 33.0 -2426.5127 -117.2969 36.0

	SWITCH_PED_ROADS_ON -1950.0 718.0 44.0 -1912.0 731.0 48.0 
	SWITCH_ROADS_ON -1950.0 718.0 44.0 -1912.0 731.0 48.0

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	MARK_MODEL_AS_NO_LONGER_NEEDED BROADWAY
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO
	UNLOAD_SPECIAL_CHARACTER 1
	REMOVE_ANIMATION GANGS
	MARK_MODEL_AS_NO_LONGER_NEEDED SHFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYPIMP
	MARK_MODEL_AS_NO_LONGER_NEEDED HUSTLER
	MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER
	MARK_MODEL_AS_NO_LONGER_NEEDED SWMYHP1
	MARK_MODEL_AS_NO_LONGER_NEEDED SWMYHP2
	MARK_MODEL_AS_NO_LONGER_NEEDED VBFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	REMOVE_ANIMATION CRACK
	REMOVE_ANIMATION FIGHT_D
	REMOVE_ANIMATION MISC
	MARK_MODEL_AS_NO_LONGER_NEEDED STRETCH
	MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA


	MARK_MODEL_AS_NO_LONGER_NEEDED STRETCH
	MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB 
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45   
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	REMOVE_ANIMATION CAR_CHAT

	// === MARK ENTITIES AS NO LONGER NEEDED === (cars,peds,objects,blips,attractors)
    
    REMOVE_BLIP location_blip
	REMOVE_BLIP jizzy_car_blip
	REMOVE_BLIP hippy1_blip
	REMOVE_BLIP hippy2_blip
	REMOVE_BLIP rival_pimp_blip
//	REMOVE_BLIP rich_chase_car_blip[0]
//	REMOVE_BLIP rich_chase_car_blip[1]
//	REMOVE_BLIP rich_chase_car_blip[2]
//	REMOVE_BLIP rich_chase_car_blip[3]
	REMOVE_BLIP rich_dude_blip
//	REMOVE_BLIP rich_car_to_create_blip

	REMOVE_DECISION_MAKER tough_dm
	REMOVE_DECISION_MAKER empty_dm

	//CLEAR_ONSCREEN_TIMER syn2_timer
 	CLEAR_ONSCREEN_COUNTER girl_health
	CLEAR_ONSCREEN_TIMER syn2_time_until_backup
	CLEAR_ONSCREEN_TIMER girl2_timer

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0
	DONT_SUPPRESS_CAR_MODEL BROADWAY
	DONT_SUPPRESS_CAR_MODEL HUSTLER

	GET_GAME_TIMER timer_mobile_start

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
}
