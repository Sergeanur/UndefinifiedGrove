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
SCRIPT_NAME LOWR
GOSUB mission_start_LOWR
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_LOWR
ENDIF
GOSUB mission_cleanup_LOWR
MISSION_END

mission_start_LOWR:
//REGISTER_MISSION_GIVEN
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

VAR_INT lowr_timer

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


mission_loop_LOWR:
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

GET_AREA_VISIBLE temp_int
IF temp_int = 0
	lowr_timer	+= m_time_diff
ENDIF

	SWITCH m_stage

		CASE 0
			GOSUB LOWR_m_stage_0
		BREAK
		CASE 1
			//GOSUB LOWR_m_stage_1
		BREAK
		CASE 2
			//GOSUB LOWR_m_stage_2
		BREAK
		CASE 3
			GOSUB LOWR_m_stage_3
		BREAK
		CASE 4
			GOSUB LOWR_m_stage_4
		BREAK
		CASE 5
			GOSUB LOWR_m_stage_5
		BREAK
		CASE 6
			GOSUB LOWR_m_stage_6
		BREAK

	ENDSWITCH

	GOSUB LOWR_global_functions

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P
		GOSUB LOWR_delete_ped
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_O
		GOSUB LOWR_delete_car
	ENDIF
	

//  end of main loop *** don't change ***
end_of_main_loop_LOWR:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_LOWR 
		ELSE
			GOSUB mission_passed_LOWR
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_LOWR
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF


// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
LOWR_m_stage_0:

		// fake creates
		IF m_goals = -1
			CREATE_CAR PONY 0.0 0.0 0.0 meet_car[0]
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 meet_ped[0]
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 car_blip
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 bounce_girl
		ENDIF

		// cars
		LVAR_INT pcar
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
		min_bet = 50
		max_bet = 1000

		car_hyd_flag = 0
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

		// added to get byte size the same
		select_pressed				= camera_mode
		select_pressed				= camera_mode
		select_pressed				= camera_mode
		select_pressed				= camera_mode
		select_pressed				= camera_mode
		select_pressed				= 0

		LVAR_INT cancel_pressed
		cancel_pressed = 0

		// set floats
		force_multiplier = 0.01

		// fake creates
		IF m_goals = -1

		ENDIF

		LOAD_MISSION_TEXT LOWR

		SUPPRESS_CAR_MODEL SAVANNA

		GOSUB LOWR_next_stage	
		m_stage = 3
RETURN




// *************************************************************************************************************
//							STAGE 3 - Player has to go to lowrider meeting							          
// *************************************************************************************************************
LOWR_m_stage_3:
		
		
		IF m_goals = 0

			//CLEAR_AREA 1793.0676 -1904.2538 12.3989 200.0 TRUE
			CLEAR_AREA_OF_CHARS 1721.1167 -1967.4893 11.0 1904.3911 -1838.3147 15.0

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

			// put up text and blip
			
			IF NOT DOES_BLIP_EXIST location_blip
				ADD_BLIP_FOR_COORD 1793.0676 -1904.2538 12.3989 location_blip
			ENDIF

			SWITCH_AUDIO_ZONE LOWRIDE TRUE

			lowr_timer = 0

			m_goals++
		ENDIF

		// update gathering behaviour
		IF m_goals = 1	
				
			IF meeting_is_loaded = 0
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1792.9243 -1918.9873 150.0 150.0 FALSE

					// create initial cars												
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
					
					// bouncing cars
					SET_CAR_STATUS meet_car[0] STATUS_PHYSICS
					SET_CAR_STATUS meet_car[4] STATUS_PHYSICS
					SET_CAR_STATUS meet_car[3] STATUS_PHYSICS
					SET_CAR_HYDRAULICS meet_car[0] TRUE
					SET_CAR_HYDRAULICS meet_car[4] TRUE									
					SET_CAR_HYDRAULICS meet_car[3] TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[0] TRUE 
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[4] TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER meet_car[3] TRUE

					temp_int = 0
					WHILE temp_int < 7
						SET_CAR_COORDINATES meet_car[temp_int] load_car_x[temp_int] load_car_y[temp_int] load_car_z[temp_int]
						SET_CAR_HEADING meet_car[temp_int] load_car_h[temp_int]
						LOCK_CAR_DOORS meet_car[temp_int] CARLOCK_LOCKOUT_PLAYER_ONLY			
					temp_int++
					ENDWHILE
					
					WAIT 0

					// create initial peds
					// guys																
					CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[0] 
					CREATE_CHAR PEDTYPE_MISSION1 VLA2 0.0 0.0 0.0 meet_ped[2]
					CREATE_CHAR PEDTYPE_MISSION1 VLA3 0.0 0.0 0.0 meet_ped[3]
					CREATE_CHAR PEDTYPE_MISSION1 VLA1 0.0 0.0 0.0 meet_ped[5]
					
					WAIT 0
					
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

					WAIT 0

					// set some of the guys to tough decision makers
					temp_int = 0
					WHILE temp_int < 22
						IF NOT IS_CHAR_DEAD meet_ped[temp_int]
							SET_CHAR_DECISION_MAKER meet_ped[temp_int] tough_dm
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
					IF NOT IS_CHAR_DEAD meet_ped[21]
						IF NOT IS_CAR_DEAD meet_car[2]
							WARP_CHAR_INTO_CAR_AS_PASSENGER meet_ped[21] meet_car[2] 0
						ENDIF
					ENDIF

					//SET_RADIO_CHANNEL 11
	
					meeting_is_loaded = 1
					lowrider_meeting_is_loaded = 1

				ENDIF
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1792.9243 -1918.9873 150.0 150.0 FALSE
					temp_int = 0
					WHILE temp_int < 7
						DELETE_CAR meet_car[temp_int]
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

					// peds

					SWITCH m_frame_num

						CASE 0
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
												PRINT_NOW LOW_32 5000 1 // ~r~The meeting is dispersing because fucked with one of their cars.
												player_has_attacked_meet = 1
												temp_int = 7
											ENDIF
										ENDIF
									ELSE
										PRINT_NOW LOW_32 5000 1 // ~r~The meeting is dispersing because fucked with one of their cars.
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
											PRINT_NOW LOW_04 5000 1 // ~r~The meeting is dispersing because you attacked a group member.
											player_has_attacked_meet = 1
											temp_int = 22
										ENDIF
									ELSE
										PRINT_NOW LOW_04 5000 1 // ~r~The meeting is dispersing because you attacked a group member.
										player_has_attacked_meet = 1
										temp_int = 22
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
						BREAK
						// cars
						CASE 20

						BREAK
			
					ENDSWITCH

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
								GOSUB lowr_set_hydraulics
								meet_wheel_fr[1] = hyd_rf			
								meet_wheel_fl[1] = hyd_lf 
								meet_wheel_br[1] = hyd_rb
								meet_wheel_bl[1] = hyd_lb
								meet_jump_timer[1] = 0
							ENDIF

							// car 2 - 4
							IF meet_jump_timer[2] > 300
								GENERATE_RANDOM_INT_IN_RANGE 0 16 hyd_index
								GOSUB lowr_set_hydraulics
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

						IF NOT m_failed = 1
							temp_int = 0
							WHILE temp_int < 22
								IF NOT IS_CHAR_DEAD meet_ped[temp_int]
									SET_CHAR_RELATIONSHIP meet_ped[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
								ENDIF
							temp_int++
							ENDWHILE
						ENDIF

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


			IF lowr_timer > 120000
				PRINT_NOW LOW_35 5000 1 // ~r~The meeting is dispersing.
				m_failed = 1

			ELSE

					// check if player has arrived
					IF LOCATE_CHAR_IN_CAR_3D scplayer 1793.0676 -1904.2538 12.3989 4.0 4.0 4.0 TRUE
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						IF IS_CAR_LOW_RIDER car
							IF DOES_CAR_HAVE_HYDRAULICS car
								STORE_SCORE player1 temp_int
								IF temp_int >= min_bet
									TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
									m_goals = 99
									SET_PLAYER_CONTROL player1 OFF
									STORE_CAR_CHAR_IS_IN scplayer pcar
									DO_FADE 500 FADE_OUT
									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE
								ELSE
									IF dialogue_flag = 0
										PRINT_WITH_NUMBER_NOW LOW_38 min_bet 5000 1 // you need $$ to compete
										dialogue_flag++
									ENDIF	
								ENDIF
							ELSE
								IF dialogue_flag = 0
									PRINT_NOW LOW_36 5000 1 // You need to have a car with hydraulics
									dialogue_flag++
								ENDIF	
							ENDIF
						ELSE
							IF dialogue_flag = 0
								PRINT_NOW LOW_36 5000 1 // You need to have a car with hydraulics
								dialogue_flag++
							ENDIF
						ENDIF
					ELSE
						IF NOT dialogue_flag = 0
							dialogue_flag = 0
						ENDIF
					ENDIF
			ENDIF
		ENDIF	

		
		// exit
		IF m_goals = 99
			GOSUB LOWR_next_stage
		ENDIF

RETURN


// *************************************************************************************************************
//						Stage 4 - cutscene of guy getting out of car - and - wager section  
// *************************************************************************************************************
LOWR_m_stage_4:

		// reset car positions
		IF m_goals = 0

			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF NOT IS_CAR_DEAD pcar
				RESET_VEHICLE_HYDRAULICS pcar
			ENDIF

			SET_CAR_DENSITY_MULTIPLIER 0.0
			SET_PED_DENSITY_MULTIPLIER 0.0
			CLEAR_AREA 1793.0676 -1904.2538 12.3989 30.0 TRUE

			IF DOES_BLIP_EXIST location_blip
				REMOVE_BLIP location_blip
			ENDIF

			// set players position
			IF NOT IS_CAR_DEAD pcar
				SET_CAR_COORDINATES pcar 1793.0676 -1904.2538 12.3989 
				SET_CAR_HEADING pcar 171.6728
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
			
			m_goals++
		ENDIF

		// make guy in centre car get out and walk towards players car
		IF m_goals = 1
			m_goals++
		ENDIF

		// wait for guy to get to point
		IF m_goals = 2
			IF NOT IS_CHAR_DEAD meet_ped[18]
				CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[18]
				SET_CHAR_COORDINATES meet_ped[18] 1792.6309 -1909.2736 12.4371
				SET_CHAR_HEADING meet_ped[18] 331.0204
										  
				SET_FIXED_CAMERA_POSITION 1793.8159 -1910.7128 14.0346 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1793.4528 -1909.8005 13.8451 JUMP_CUT

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				m_goals++
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
				//PRINT LOW_06 5000 1 // So you think you can lowride?
				//PRINT LOW_07 5000 1 // If you're so keen to take us on, how about a wager?
				

				GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
				SWITCH temp_int
					CASE 0
						$audio_string    = &LOWR_BA	
						audio_sound_file = SOUND_LOWR_BA
						START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
					BREAK
					CASE 1
						$audio_string    = &LOWR_BB	
						audio_sound_file = SOUND_LOWR_BB
						START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
					BREAK
					CASE 2
						$audio_string    = &LOWR_BC	
						audio_sound_file = SOUND_LOWR_BC
						START_NEW_SCRIPT audio_line meet_ped[18] 0 1 1 0
					BREAK
				ENDSWITCH


				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		// wait for dialogue to finish
		IF m_goals = 4
			IF TIMERA > 1000	
				IF audio_line_is_active = 0
				OR IS_BUTTON_PRESSED PAD1 CROSS
					START_NEW_SCRIPT cleanup_audio_lines
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_PRINTS
					m_goals++
					cross_pressed = 1
					IF IS_JAPANESE_VERSION
						cancel_pressed = 1
					ENDIF
					IF NOT IS_CHAR_DEAD meet_ped[18]
						CLEAR_CHAR_TASKS meet_ped[18]
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// show wager window
		IF m_goals = 5								

			CLEAR_PRINTS
			SWITCH_WIDESCREEN OFF
			SET_FIXED_CAMERA_POSITION 1793.4847 -1907.3541 13.4436 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1793.1289 -1908.2886 13.4481 JUMP_CUT 

			current_wager = min_bet
			temp_int = current_wager * -1
			ADD_SCORE player1 temp_int
			PRINT_HELP_FOREVER LOW_37
			USE_TEXT_COMMANDS TRUE
			WAIT 1
			m_goals++
		ENDIF


		// wager screen
		IF m_Goals = 6

			// check for language change
			IF HAS_GAME_JUST_RETURNED_FROM_FRONTEND
			AND HAS_LANGUAGE_CHANGED
				CLEAR_HELP 
				PRINT_HELP_FOREVER LOW_37
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

			GOSUB lowr_draw_window_1

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

			IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
			AND cancel_pressed = 0
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				// remove some of the peds and cars
				IF DOES_VEHICLE_EXIST meet_car[1]
					DELETE_CAR meet_car[1]
				ENDIF
				IF DOES_VEHICLE_EXIST meet_car[4]
					DELETE_CAR meet_car[4]
				ENDIF
				IF DOES_VEHICLE_EXIST meet_car[2]
					DELETE_CAR meet_car[2]
				ENDIF
				IF DOES_VEHICLE_EXIST meet_car[5]
					DELETE_CAR meet_car[5]
				ENDIF
				IF DOES_VEHICLE_EXIST meet_car[3]
					DELETE_CAR meet_car[3]
				ENDIF

				IF DOES_CHAR_EXIST meet_ped[1]
					DELETE_CHAR meet_ped[1]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[3]
					DELETE_CHAR meet_ped[3]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[16]
					DELETE_CHAR meet_ped[16]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[6]
					DELETE_CHAR meet_ped[6]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[7]
					DELETE_CHAR meet_ped[7]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[9]
					DELETE_CHAR meet_ped[9]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[10]
					DELETE_CHAR meet_ped[10]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[12]
					DELETE_CHAR meet_ped[12]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[20]
					DELETE_CHAR meet_ped[20]
				ENDIF
				IF DOES_CHAR_EXIST meet_ped[13]
					DELETE_CHAR meet_ped[13]
				ENDIF

				IF NOT IS_CHAR_DEAD bounce_girl
					IF NOT IS_CAR_DEAD pcar
						IF IS_CHAR_IN_CAR bounce_girl pcar
							TASK_LEAVE_CAR bounce_girl pcar
						ENDIF
					ENDIF
				ENDIF
				
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				m_failed = 1
			ELSE
				IF NOT cancel_pressed = 0
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						cancel_pressed = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		// wait for player to place bet
		IF m_goals = 7
			USE_TEXT_COMMANDS FALSE
			m_goals++
		ENDIF

		IF m_goals = 8
			m_goals++
		ENDIF

		IF m_goals = 9
			m_goals++ 
		ENDIF

		IF m_goals = 10
			IF TIMERA > 2000
				CLEAR_PRINTS
				//WRITE_DEBUG FADING_OUT
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				//WRITE_DEBUG FADED_OUT
				m_goals = 99
			ENDIF
		ENDIF

		IF NOT m_failed = 1
			GOSUB lowr_meeting_stage3_update_ai
		ENDIF
		
		// exit
		IF m_goals = 99
			GOSUB LOWR_next_stage
		ENDIF

RETURN

// *************************************************************************************************************
//						Stage 5 - set up minigame scene - cutscene of girl getting into car  
// *************************************************************************************************************
LOWR_m_stage_5:
		
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

			//WRITE_DEBUG GOT_HERE1

			WHILE NOT HAS_MODEL_LOADED VLA1
			WAIT 0
			ENDWHILE
			   WHILE NOT HAS_MODEL_LOADED VLA2
			   WAIT 0
			ENDWHILE
			   WHILE NOT HAS_MODEL_LOADED VLA3
			   WAIT 0
			ENDWHILE
			   WHILE NOT HAS_MODEL_LOADED BFYPRO
			   WAIT 0
			ENDWHILE
			   WHILE NOT HAS_MODEL_LOADED HFYST
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

						
			// flags set for the lowrider minigame
			lowrider_level = -1
			lowrider_last_level = -1
			//lowrider_opposition_skill = 1

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
			IF NOT DOES_VEHICLE_EXIST pcar
				CREATE_CAR SAVANNA 1793.4956 -1907.4233 12.3992 pcar
				SET_CAR_HEADING pcar 181.5231
				RESET_VEHICLE_HYDRAULICS pcar
			ELSE
				IF NOT IS_CAR_DEAD pcar
					SET_CAR_COORDINATES pcar 1793.4956 -1907.4233 12.3992 
					SET_CAR_HEADING pcar     181.5231
				ENDIF
			ENDIF
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			IF NOT IS_CAR_DEAD pcar
				WARP_CHAR_INTO_CAR scplayer pcar	
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

			
			// load music track ---------------------------------------------------------
			VAR_INT lowr_track_to_play
			IF lowr_track_to_play = 0
				PRELOAD_BEAT_TRACK 13
				lowr_track_to_play++
			ELSE
				PRELOAD_BEAT_TRACK 0
				lowr_track_to_play = 0
			ENDIF
			temp_int = 0
			WHILE NOT temp_int = CUTSCENE_TRACK_LOADED
				WAIT 0
				GET_BEAT_TRACK_STATUS	temp_int
			ENDWHILE

			m_goals++

		ENDIF

		// decide whether to put bounce girl in car or not
		IF m_goals = 2
			IF IS_CHAR_IN_ANY_CAR scplayer 
				
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car

				LVAR_INT gang_member_to_delete
				IF NOT IS_CAR_DEAD car
					IF NOT IS_CAR_PASSENGER_SEAT_FREE car 0									
						GET_CHAR_IN_CAR_PASSENGER_SEAT car 0 gang_member_to_delete
						DELETE_CHAR gang_member_to_delete
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD bounce_girl
 					WARP_CHAR_INTO_CAR_AS_PASSENGER bounce_girl car 0
					m_goals++
				ENDIF			   	
			ENDIF
		ENDIF


		// switch camera view ------------------------------------------------------------
		IF m_goals = 3
			
			SWITCH_WIDESCREEN OFF
			SET_FIXED_CAMERA_POSITION 1788.5157 -1910.3184 14.8901 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1789.3551 -1909.8429 14.6269 JUMP_CUT
			DISPLAY_RADAR FALSE
			DISPLAY_HUD FALSE

			IF NOT IS_CAR_DEAD pcar
				LOCK_CAR_DOORS pcar CARLOCK_LOCKED_PLAYER_INSIDE
				APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
				SET_CAR_PROOFS pcar TRUE TRUE TRUE TRUE TRUE
			ENDIF

			offx = 0.6200 
			offy = 0.3100 
			offz = -0.1670
			IF NOT IS_CAR_DEAD pcar
				IF NOT IS_CHAR_DEAD bounce_girl
					IF IS_CAR_MODEL pcar SAVANNA
						CLEAR_CHAR_TASKS_IMMEDIATELY bounce_girl
						ATTACH_CHAR_TO_CAR bounce_girl pcar offx offy offz FACING_FORWARD 0.0 WEAPONTYPE_UNARMED 
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

			// flags set for the lowrider minigame
			lowrider_level = -1
			lowrider_last_level = -1
			IF lowrider_opposition_skill < 1
				lowrider_opposition_skill = 1
			ENDIF

			// add on difficulty depending on the wager
			temp_float =# current_wager
			temp_float2 =# max_bet
			temp_float /= temp_float2
			temp_float *= 4.0
			temp_int =# temp_float

			lowrider_opposition_skill += temp_int
			
			IF lowrider_opposition_skill > 5
				lowrider_opposition_skill = 5
			ENDIF
			IF lowrider_opposition_skill < 1
				lowrider_opposition_skill = 1
			ENDIF

			WAIT 500

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_PRINTS
			
			PLAY_BEAT_TRACK

			m_goals++
		ENDIF

		// wait a few secs before starting game, to explain help etc ---------------------
		IF m_goals = 4

			SET_PLAYER_CONTROL player1 ON
			START_NEW_SCRIPT beat_display
			//lowrider_opposition_skill = 1
			START_NEW_SCRIPT lowrider_game pcar meet_car[4] bounce_girl
			TIMERA = 0
			camera_timer = 0
			camera_mode = 0
			CLEAR_PRINTS
			CLEAR_HELP 
			PRINT_HELP LOW_26  // Try to move the suspension in time with the music.
			help_timer = 0
			help_flag = 0
			TIMERA = 0
			m_goals++

		ENDIF

		// wait for lowrider minigame to start
		IF m_goals = 5
			IF lowrider_game_is_active = 1 
				m_goals++
			ENDIF
		ENDIF

		// lowrider minigame --------------------------------------------------------------
		IF m_goals = 6
			IF lowrider_game_is_active = 1
				// do camera work
				IF select_pressed = 0
					IF camera_timer > 5000
					OR IS_BUTTON_PRESSED PAD1 SELECT
						camera_mode++
						IF camera_mode >= 5
							camera_mode = 0
						ENDIF
						
						IF NOT IS_CAR_DEAD pcar
							GET_CAR_COORDINATES pcar x y z
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

						// for camera position
						GET_ACTIVE_CAMERA_COORDINATES x y z
						IF NOT IS_CAR_DEAD pcar
							GET_CAR_COORDINATES pcar x2 y2 z2
							GET_CAR_HEADING pcar heading
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

						 
				ENDIF
			ELSE
				m_goals++
			ENDIF
		ENDIF		  

		// finish minigame
		IF m_goals = 7
			SET_PLAYER_CONTROL player1 FALSE
			DO_FADE 500 FADE_OUT 
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			m_goals = 99
		ENDIF

		GOSUB lowr_meeting_stage5_update_ai

		// exit
		IF m_goals = 99
			GOSUB LOWR_next_stage
		ENDIF


RETURN


// *************************************************************************************************************
//						Stage 6 - cutscene at end of minigame  
// *************************************************************************************************************
LOWR_m_stage_6:


		IF m_goals = 0
			STOP_BEAT_TRACK
			CLEAR_PRINTS
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 OFF
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
			IF NOT DOES_VEHICLE_EXIST pcar
				CREATE_CAR SAVANNA 1793.4956 -1907.4233 12.3992 pcar
				SET_CAR_HEADING pcar 181.5231
			ELSE
				IF NOT IS_CAR_DEAD pcar
					SET_CAR_COORDINATES pcar 1793.4956 -1907.4233 12.3992 
					SET_CAR_HEADING pcar 181.5231
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

			IF NOT IS_CAR_DEAD pcar
				RESET_VEHICLE_HYDRAULICS pcar
				IF NOT IS_CAR_PASSENGER_SEAT_FREE pcar 0									
					GET_CHAR_IN_CAR_PASSENGER_SEAT pcar 0 gang_member_to_delete
					DELETE_CHAR gang_member_to_delete
				ENDIF
				IF NOT IS_CHAR_DEAD bounce_girl
					WARP_CHAR_INTO_CAR_AS_PASSENGER bounce_girl pcar 0
				ENDIF
			ENDIF


			// put player into car
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			IF NOT IS_CAR_DEAD pcar
				WARP_CHAR_INTO_CAR scplayer pcar	
				TASK_PLAY_ANIM scplayer sit_relaxed LOWRIDER 4.0 TRUE FALSE FALSE TRUE -1
			ENDIF
			
			LOAD_SCENE_IN_DIRECTION 1793.0 -1908.0 14.0 180.0

			LOAD_ALL_MODELS_NOW

			m_goals = 99

		ENDIF
		
		// cleanup 
		IF m_goals = 99

			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON

			LOAD_SCENE_IN_DIRECTION 1793.0 -1908.0 14.0 180.0

			LOAD_ALL_MODELS_NOW

			WAIT 500


			// remove some of the peds and cars
			IF DOES_VEHICLE_EXIST meet_car[1]
				DELETE_CAR meet_car[1]
			ENDIF
			IF DOES_VEHICLE_EXIST meet_car[4]
				DELETE_CAR meet_car[4]
			ENDIF
			IF DOES_VEHICLE_EXIST meet_car[2]
				DELETE_CAR meet_car[2]
			ENDIF
			IF DOES_VEHICLE_EXIST meet_car[5]
				DELETE_CAR meet_car[5]
			ENDIF
			IF DOES_VEHICLE_EXIST meet_car[3]
				DELETE_CAR meet_car[3]
			ENDIF

			IF DOES_CHAR_EXIST meet_ped[1]
				DELETE_CHAR meet_ped[1]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[3]
				DELETE_CHAR meet_ped[3]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[16]
				DELETE_CHAR meet_ped[16]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[6]
				DELETE_CHAR meet_ped[6]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[7]
				DELETE_CHAR meet_ped[7]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[9]
				DELETE_CHAR meet_ped[9]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[10]
				DELETE_CHAR meet_ped[10]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[12]
				DELETE_CHAR meet_ped[12]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[20]
				DELETE_CHAR meet_ped[20]
			ENDIF
			IF DOES_CHAR_EXIST meet_ped[13]
				DELETE_CHAR meet_ped[13]
			ENDIF

			// put drivers in the cars that are there
			IF NOT IS_CHAR_DEAD meet_ped[4]
				IF NOT IS_CAR_DEAD meet_car[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[4]
					WARP_CHAR_INTO_CAR meet_ped[4] meet_car[0]
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD meet_ped[11]
				IF NOT IS_CAR_DEAD meet_car[6]
					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[11]
					WARP_CHAR_INTO_CAR meet_ped[11] meet_car[6]
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD bounce_girl
				IF NOT IS_CAR_DEAD pcar
					TASK_LEAVE_CAR bounce_girl pcar
				ENDIF
			ENDIF

			
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF lowrider_pscore > lowrider_oscore
				current_wager *= 2
				PRINT_WITH_NUMBER_BIG ( WINNER ) current_wager 5000 1 //"Mission Passed!"
				ADD_SCORE player1 current_wager
				m_passed = 1
			ELSE
				PRINT_NOW LOW_33 5000 1 // you failed the lowrider challenge
				m_failed = 1
			ENDIF

			WAIT 0

			GOSUB LOWR_next_stage

		ENDIF

		GOSUB lowr_meeting_stage5_update_ai

RETURN


// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************

LOWR_global_functions:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_4
		force_multiplier += 0.01
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_5
		force_multiplier += -0.01
	ENDIF

	IF DOES_VEHICLE_EXIST pcar
		IF IS_CAR_DEAD pcar
			PRINT_NOW LOW_34 5000 1 // your lowrider is history
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

lowr_update_attached_girl:

	IF NOT IS_CAR_DEAD pcar
		IF NOT IS_CHAR_DEAD bounce_girl
			ATTACH_CHAR_TO_CAR bounce_girl pcar offx offy offz FACING_FORWARD 0.0 WEAPONTYPE_UNARMED 
		ENDIF
	ENDIF

RETURN

LVAR_FLOAT hyd_lf hyd_lb hyd_rf hyd_rb
LVAR_INT hyd_index
lowr_set_hydraulics:
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

lowr_meeting_stage3_update_ai:
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
		GOSUB lowr_set_hydraulics
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

lowr_meeting_stage5_update_ai:

	SWITCH m_frame_num


	// peds
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


LOWR_delete_ped:
	temp_int = 0
	WHILE temp_int < 22
		IF DOES_CHAR_EXIST meet_ped[temp_int]
			DELETE_CHAR meet_ped[temp_int]
			WRITE_DEBUG_WITH_INT PED_DELETED temp_int
			temp_int = 22
		ELSE
			temp_int++
		ENDIF
	ENDWHILE
RETURN

LOWR_delete_car:
	temp_int = 0
	WHILE temp_int < 7
		IF DOES_VEHICLE_EXIST meet_car[temp_int]
			DELETE_CAR meet_car[temp_int]
			WRITE_DEBUG_WITH_INT CAR_DELETED temp_int
			temp_int = 7
		ELSE
			temp_int++
		ENDIF
	ENDWHILE
RETURN


lowr_draw_window_1:
	
	SWITCH current_Language
	CASE LANGUAGE_ITALIAN
		DRAW_WINDOW 29.0 220.0 173.0 409.0 BJ_TITL SWIRLS_RIGHT
		BREAK
	DEFAULT
		DRAW_WINDOW 29.0 220.0 157.0 409.0 BJ_TITL SWIRLS_RIGHT
		BREAK
	ENDSWITCH

	GOSUB text_lowr
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT 36.0 240.0 WOF_04

	GOSUB text_lowr
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 260.0 DOLLAR current_wager

	GOSUB text_lowr
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT 36.0 290.0 BJ_02

	GOSUB text_lowr
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 310.0 DOLLAR min_bet

	GOSUB text_lowr
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT 36.0 340.0 BJ_03

	GOSUB text_lowr
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER 36.0 360.0 DOLLAR max_bet

RETURN


text_lowr:
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
	LOWR_next_stage:
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
mission_failed_LOWR:


RETURN

// PASS
mission_passed_LOWR:
	//REGISTER_MISSION_PASSED LOWR
	//REGISTER_ODDJOB_MISSION_PASSED
RETURN

// CLEANUP
mission_cleanup_LOWR:

	SWITCH_AUDIO_ZONE LOWRIDE FALSE

	IF DOES_VEHICLE_EXIST pcar
		IF NOT IS_CAR_DEAD pcar
			SET_CAR_STATUS pcar STATUS_ABANDONED
			FREEZE_CAR_POSITION pcar FALSE
			SET_CAR_PROOFS pcar FALSE FALSE FALSE FALSE FALSE
			IF IS_PLAYER_PLAYING player1
				APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE
			ENDIF 
			LOCK_CAR_DOORS pcar CARLOCK_UNLOCKED

			IF IS_CHAR_IN_CAR scplayer pcar
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				WARP_CHAR_INTO_CAR scplayer pcar
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
//				IF IS_CHAR_IN_ANY_CAR meet_ped[temp_int]
//					CLEAR_CHAR_TASKS_IMMEDIATELY meet_ped[temp_int]
//				ENDIF
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED meet_ped[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

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
	MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA

	REMOVE_ANIMATION LOWRIDER

	REMOVE_BLIP location_blip

	DISPLAY_RADAR TRUE

	GET_BEAT_TRACK_STATUS	temp_int
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