MISSION_START
// *****************************************************************************************
// *************************************  wheel of fortune *********************************
// *****************************************************************************************

// globals
//VAR_INT wof_seq_place_bet
//VAR_INT attractor_ped

VAR_INT winning_streak
VAR_INT win_dialogue

										 
// game variables
VAR_INT current_bet[6] total_bet
VAR_INT current_bet_type
VAR_INT betting_step
VAR_INT wheel_position
VAR_INT wheel_position_value
VAR_INT wof_winnings

VAR_INT chip_set_1[6]
VAR_INT chip_set_2[6]
VAR_INT chip_set_3[6]
VAR_INT chip_set_4[6]
VAR_INT chip_set_5[6]
VAR_INT chip_set_6[6]
VAR_INT chip_set_7[6]
VAR_INT chip_set_8[6]
VAR_FLOAT chip_set_z
VAR_FLOAT chip_set_1_z[6]
VAR_FLOAT chip_set_2_z[6]
VAR_FLOAT chip_set_3_z[6]
VAR_FLOAT chip_set_4_z[6]
VAR_FLOAT chip_set_5_z[6]
VAR_FLOAT chip_set_6_z[6]
VAR_FLOAT chip_set_7_z[6]
VAR_FLOAT chip_set_8_z[6]

VAR_INT marker
VAR_INT wof_betting_menu

VAR_INT wof_initial_stake
VAR_INT wof_refund

VAR_INT active_wheel

// remove these - used for finding camera positions
VAR_FLOAT wof_x2 wof_y2 wof_z2
VAR_FLOAT wof_vec_x wof_vec_y wof_vec_z
VAR_FLOAT wof_vec2_x wof_vec2_y 
//wof_vec2_z
VAR_FLOAT wof_temp_float3

VAR_INT total_wof_tables


{///////////////////////////////////////////////////////////////////////////////
wof_script: 	////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

	SCRIPT_NAME WOF

	LVAR_INT wheel
	LVAR_INT m_clicker
	LVAR_INT table
	LVAR_INT table_nos
	LVAR_INT m_stand
	LVAR_INT m_stage m_goals
	LVAR_INT croupier

	LVAR_FLOAT temp_float temp_float2
	LVAR_INT temp_int temp_int2 temp_int3

	LVAR_INT m_quit
	LVAR_INT help_flag

	LVAR_INT tri_is_pressed
	LVAR_INT cross_is_pressed
	LVAR_INT dpad_is_pressed
	LVAR_INT square_is_pressed

	LVAR_INT max_bet

	// wheel data
	LVAR_FLOAT wheel_heading
	LVAR_FLOAT wheel_stop_position
	LVAR_FLOAT wheel_ry 
	LVAR_FLOAT wheel_rotation_speed
	LVAR_FLOAT wheel_acceleration
	LVAR_INT wheel_status // 0 - stationary, 1 - speeding up, 2 - slowing down, 3 - finishing
	LVAR_FLOAT clicker_ry
	LVAR_INT wof_clacker_sound_flag


	m_stage = 0
	m_goals = 0
	wheel_ry = 0.0
	wheel_rotation_speed = 0.0
	m_quit = 0
	tri_is_pressed = 0 	
	cross_is_pressed = 0
	wheel_status = -1
	wof_clacker_sound_flag = 0

	GENERATE_RANDOM_INT_IN_RANGE 0 30000 TIMERA

	GOTO wof_fake_create
		CREATE_OBJECT WHEEL_O_FORTUNE 0.0 0.0 0.0 wheel
		CREATE_OBJECT WHEEL_O_FORTUNE 0.0 0.0 0.0 m_stand
		CREATE_OBJECT WHEEL_O_FORTUNE 0.0 0.0 0.0 table
		CREATE_OBJECT WHEEL_O_FORTUNE 0.0 0.0 0.0 m_clicker
		CREATE_OBJECT WHEEL_O_FORTUNE 0.0 0.0 0.0 table_nos
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 croupier
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_1[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_2[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_3[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_4[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_5[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_6[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_7[0]
		CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 chip_set_8[0]
		CREATE_OBJECT ROULETTE_MARKER 0.0 0.0 0.0 marker
	wof_fake_create:


	wof_loop:
	WAIT 0

		IF iTerminateAllAmbience = 0
			IF DOES_OBJECT_EXIST wheel
				IF IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE wheel
					IF active_wheel = 0
						IF IS_PLAYER_PLAYING player1

								// create stuff
								IF m_stage = 0

									IF m_goals = 0
										REQUEST_MODEL VBFYCRP
										REQUEST_ANIMATION CASINO
										m_goals++
									ENDIF	
									IF m_goals = 1
										IF HAS_MODEL_LOADED	VBFYCRP
										AND HAS_ANIMATION_LOADED CASINO
											m_goals++
										ENDIF
									ENDIF
									IF m_goals = 2
										// croupier
										IF NOT DOES_CHAR_EXIST croupier
											
											GET_OBJECT_HEADING wheel wheel_heading
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS wheel 0.5 -0.3974 0.0 x y z
											GET_GROUND_Z_FOR_3D_COORD x y z z
											CREATE_CHAR PEDTYPE_CIVFEMALE VBFYCRP x y z croupier

											SET_CHAR_DECISION_MAKER croupier iGlobalPedPanicDM

											heading = wheel_heading + 180.0
											SET_CHAR_HEADING croupier heading
											FREEZE_CHAR_POSITION croupier TRUE
											MARK_MODEL_AS_NO_LONGER_NEEDED VBFYCRP
										ENDIF
										// objects
										IF NOT DOES_OBJECT_EXIST m_stand
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS wheel 0.0 0.159 0.0 x y z
											CREATE_OBJECT_NO_OFFSET WHEEL_SUPPORT x y z m_stand
											SET_OBJECT_HEADING m_stand wheel_heading
										ENDIF
										IF NOT DOES_OBJECT_EXIST table
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS wheel -0.132 -1.049194 -0.834192 x y z
											GET_GROUND_Z_FOR_3D_COORD x y z z
											z += 1.0
											CREATE_OBJECT_NO_OFFSET WHEEL_TABLE x y z table
											SET_OBJECT_HEADING table wheel_heading
											GOSUB wof_get_max_bet
										ENDIF
										IF NOT DOES_OBJECT_EXIST m_clicker
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS wheel 0.0 0.0 1.05 x y z
											CREATE_OBJECT_NO_OFFSET CLICKER x y z m_clicker
											SET_OBJECT_HEADING m_clicker wheel_heading
										ENDIF
			
										m_goals++
									ENDIF

									IF m_goals = 3
										GENERATE_RANDOM_INT_IN_RANGE 0 50000 TIMERA
										m_stage++
									ENDIF

								ENDIF

								// wait for player to trigger game
								IF m_stage = 1
									
									IF NOT IS_CHAR_DEAD croupier
										
										IF TIMERA > 50000
											TASK_PLAY_ANIM croupier wof casino 4.0 FALSE FALSE FALSE FALSE -1
											GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
											SWITCH temp_int
												CASE 0
													SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_1 TRUE TRUE FALSE
												BREAK
												CASE 1
													SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_2 TRUE TRUE FALSE
												BREAK
												CASE 2
													SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_3 TRUE TRUE FALSE
												BREAK
											ENDSWITCH
											
											wheel_status = 0
											TIMERA = 0	
										ENDIF							
									ENDIF

									IF NOT IS_CHAR_DEAD croupier 
										IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR croupier scplayer
											iSetCasinoPanic = 1
										ENDIF
										GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 -1.0 0.0 x y z
										IF iSetCasinoPanic = 0
											IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.5 0.5 2.0 FALSE
											//IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer table 1.0 1.0 2.0 FALSE
												IF IS_CHAR_ON_FOOT scplayer
													IF tri_is_pressed = 0
														
														IF IS_BUTTON_PRESSED PAD1 TRIANGLE

															// check the player has enough stats
															temp_int = 0
															GET_FLOAT_STAT GAMBLING temp_float
															
															IF max_bet = 1000000
																IF temp_float < 1000.0
																	PRINT_NOW GAMBSTA 5000 1
																	GOSUB wof_not_enough_stats
																	temp_int = 1
																ENDIF
															ELSE
																IF max_bet = 100000
																	IF temp_float < 100.0
																		PRINT_NOW GAMBSTA 5000 1 
																		GOSUB wof_not_enough_stats
																		temp_int = 1
																	ENDIF
																ELSE
																	IF max_bet = 10000
																		IF temp_float < 10.0
																			PRINT_NOW GAMBSTA 5000 1  
																			GOSUB wof_not_enough_stats
																			temp_int = 1
																		ENDIF
																	ELSE
																		IF max_bet = 1000
																			IF temp_float < 1.0
																				PRINT_NOW GAMBSTA 5000 1 
																				GOSUB wof_not_enough_stats
																				temp_int = 1
																			ENDIF
																		ENDIF
																	ENDIF
																ENDIF
															ENDIF

															IF temp_int = 0
																IF help_flag = 1
																	CLEAR_HELP
																	help_flag = 0
																ENDIF

																m_stage = 0
																m_goals = 0 
																active_wheel = wheel
																tri_is_pressed = 1
															ENDIF

														ELSE
															IF help_flag = 0

																IF  NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
																AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
																AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
																AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
																	
																	SWITCH max_bet
																		CASE 100
																			PRINT_HELP_FOREVER WOF_06A  
																		BREAK
																		CASE 1000
																			PRINT_HELP_FOREVER WOF_06B  
																		BREAK
																		CASE 10000
																			PRINT_HELP_FOREVER WOF_06C  
																		BREAK
																		CASE 100000
																			PRINT_HELP_FOREVER WOF_06D  
																		BREAK
																		CASE 1000000
																			PRINT_HELP_FOREVER WOF_06E  
																		BREAK
																		DEFAULT
																			PRINT_HELP_FOREVER WOF_06  
																		BREAK
																	ENDSWITCH
																	help_flag = 1

																ENDIF
															ENDIF
														ENDIF
													ELSE
														IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
															tri_is_pressed = 0
														ENDIF
													ENDIF
												ENDIF			  
											ELSE
												IF help_flag = 1
													CLEAR_HELP
													help_flag = 0
												ENDIF	
											ENDIF
										ENDIF
									ELSE
										iSetCasinoPanic = 1
									ENDIF
								ENDIF
	

							// process wheel
							IF IS_OBJECT_ON_SCREEN wheel
							OR LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer wheel 5.0 5.0 FALSE 
								GOSUB wheelo_process_wheel
							ENDIF



						ELSE
							GOSUB wof_cleanup_top
						ENDIF						
					ELSE 
						IF active_wheel = wheel
							IF IS_PLAYER_PLAYING player1
								IF m_quit = 0
									GOSUB wheelo_process_wheel
									GOSUB wof_loop_main
								ELSE
									GOSUB wof_cleanup
									active_wheel = 0 
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					GOSUB wof_cleanup_top
				ENDIF
			ELSE
				GOSUB wof_cleanup_top
			ENDIF
		ELSE
			GOSUB wof_cleanup_top
		ENDIF
	

	GOTO wof_loop


wof_not_enough_stats:

	temp_int = 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
	IF temp_int = 0
		SET_CHAR_SAY_SCRIPT	croupier SOUND_W_REG_1 TRUE TRUE FALSE
	ELSE
		SET_CHAR_SAY_SCRIPT	croupier SOUND_W_REG_1 TRUE TRUE FALSE
	ENDIF

RETURN


wof_cleanup_top:

	//CLEAR_PRINTS

	MARK_CHAR_AS_NO_LONGER_NEEDED croupier
	MARK_OBJECT_AS_NO_LONGER_NEEDED m_stand
	MARK_OBJECT_AS_NO_LONGER_NEEDED table
	MARK_OBJECT_AS_NO_LONGER_NEEDED m_clicker
	//MARK_OBJECT_AS_NO_LONGER_NEEDED table_nos
	REMOVE_ANIMATION CASINO
	total_wof_tables--

	TERMINATE_THIS_SCRIPT
RETURN




wof_loop_main:
	
	// initialise stuff for mini-game proper
	SWITCH m_stage 
		CASE 0
			GOSUB wof_m_stage_0
		BREAK

		CASE 1
			GOSUB wof_m_stage_1
		BREAK
				
		CASE 2
			GOSUB wof_m_stage_2
		BREAK

		CASE 3
			GOSUB wof_m_stage_3
		BREAK

		CASE 4
			GOSUB wof_m_stage_4
		BREAK

		CASE 5
			GOSUB wof_m_stage_5
		BREAK

		CASE 6
			GOSUB wof_m_stage_6
		BREAK


	ENDSWITCH

	GOSUB wof_generate_chipstack

	// player has decided to quit
	IF m_stage = 1
		IF tri_is_pressed = 0
			IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
				m_quit = 1
				tri_is_pressed = 1
			ENDIF
		ELSE
			IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
				tri_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	// if the croupier is dead quit
	IF IS_CHAR_DEAD croupier
		IF m_stage > 0
			m_quit = 1
		ENDIF
	ENDIF
			
RETURN

wof_m_stage_0:
	IF m_goals = 0 
		IF IS_PLAYER_PLAYING player1
			
			SET_MINIGAME_IN_PROGRESS TRUE

			SET_PLAYER_CONTROL player1 OFF
			GET_OBJECT_COORDINATES wheel x y z
			TASK_TURN_CHAR_TO_FACE_COORD scplayer x y z
			USE_TEXT_COMMANDS TRUE
			DISPLAY_RADAR FALSE
			REQUEST_ANIMATION CASINO

			CLEAR_PRINTS

			temp_int = 0
			WHILE temp_int < 6
				chip_set_z = -0.118
				chip_set_1_z[temp_int] = chip_set_z
				chip_set_2_z[temp_int] = chip_set_z
				chip_set_3_z[temp_int] = chip_set_z
				chip_set_4_z[temp_int] = chip_set_z
				chip_set_5_z[temp_int] = chip_set_z
				chip_set_6_z[temp_int] = chip_set_z
				chip_set_7_z[temp_int] = chip_set_z
				chip_set_8_z[temp_int] = chip_set_z
			temp_int++
			ENDWHILE


			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.634  0.253 chip_set_1_z[0] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[0]
			SET_OBJECT_HEADING chip_set_1[0] wheel_heading

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table  -0.201  0.253 chip_set_1_z[1] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[1]
			SET_OBJECT_HEADING chip_set_1[1] wheel_heading

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table  0.243  0.253 chip_set_1_z[2] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[2]
			SET_OBJECT_HEADING chip_set_1[2] wheel_heading

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.635 -0.106 chip_set_1_z[3] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[3]
			SET_OBJECT_HEADING chip_set_1[3] wheel_heading

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table  -0.201 -0.106 chip_set_1_z[4] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[4]
			SET_OBJECT_HEADING chip_set_1[4] wheel_heading

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table  0.243 -0.106 chip_set_1_z[5] x y z
			CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z chip_set_1[5]
			SET_OBJECT_HEADING chip_set_1[5] wheel_heading


			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] 0.07 0.0 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK08 x y z chip_set_2[temp_int] 
			temp_int++
			ENDWHILE
			
			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] -0.07 0.0 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK09 x y z chip_set_3[temp_int] 
			temp_int++
			ENDWHILE
			
			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] 0.07 0.07 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK10 x y z chip_set_4[temp_int]
			temp_int++
			ENDWHILE
			
			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] 0.07 -0.07 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK11 x y z chip_set_5[temp_int] 
			temp_int++
			ENDWHILE
			
			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] -0.07 0.07 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK12 x y z chip_set_6[temp_int] 
			temp_int++
			ENDWHILE
			
			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] -0.07 -0.07 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK13 x y z chip_set_7[temp_int] 
			temp_int++
			ENDWHILE  

			temp_int = 0
			WHILE temp_int < 6
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS chip_set_1[temp_int] 0.0 0.07 0.0 x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK14 x y z chip_set_8[temp_int] 
			temp_int++
			ENDWHILE

			winning_streak = 0

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.635 0.253 0.041 x y z
			CREATE_OBJECT_NO_OFFSET ROULETTE_MARKER x y z marker

		ENDIF
		m_goals++
	ENDIF
	IF m_goals = 1
		IF HAS_ANIMATION_LOADED CASINO
			m_goals++
		ENDIF
	ENDIF
	IF m_goals = 2

		m_goals = 99

	ENDIF

	IF m_goals = 99
		m_stage++
		m_goals = 0
	ENDIF
RETURN

wof_m_stage_1:
			
	IF m_goals = 0
		temp_int = 0
		WHILE temp_int < 6
			current_bet[temp_int] = 0
		temp_int++
		ENDWHILE
		total_bet			= 0
		current_bet_type	= 0
		TIMERA = 0
		TIMERB = 0

		PRINT_HELP_FOREVER WOFHD01

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 -0.7 0.0 x y z
		GET_GROUND_Z_FOR_3D_COORD x y z z
		SET_CHAR_COORDINATES scplayer x y z
		SET_CHAR_HEADING scplayer wheel_heading

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.7734 -0.6326 0.9882 x y z
		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.4684 -0.0634 0.2246 x y z
		POINT_CAMERA_AT_POINT x y z JUMP_CUT

		IF DOES_OBJECT_EXIST marker
			SET_OBJECT_VISIBLE marker TRUE 
		ENDIF

		wheel_rotation_speed = 0.0 
		wheel_acceleration = 0.0


		wof_initial_stake = 0
		wof_refund		  = 0 

		IF NOT IS_CHAR_DEAD croupier
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			SWITCH temp_int
				CASE 0
					SET_CHAR_SAY_SCRIPT	croupier SOUND_W_BET_1 TRUE TRUE FALSE
				BREAK
				CASE 1
					SET_CHAR_SAY_SCRIPT	croupier SOUND_W_BET_2 TRUE TRUE FALSE
				BREAK
			ENDSWITCH
		ENDIF
		

		m_goals++
	ENDIF

	IF m_goals = 1	
		
		// get input
		GET_POSITION_OF_ANALOGUE_STICKS PAD1 lstickx lsticky rstickx rsticky

		IF NOT dpad_is_pressed = 0
			temp_int = 0
			IF IS_BUTTON_PRESSED PAD1 DPADUP
			OR IS_BUTTON_PRESSED PAD1 DPADDOWN
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
				temp_int = 1
			ENDIF
			IF lsticky > 100
			OR lsticky < -100
			OR lstickx > 100
			OR lstickx < -100
				temp_int = 1
			ENDIF
			IF temp_int = 0
				dpad_is_pressed = 0
			ENDIF
		ENDIF

		temp_int = dpad_is_pressed

		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR lsticky < -100
			dpad_is_pressed = 1
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR lsticky > 100
			dpad_is_pressed = 2
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
		OR lstickx < -100
			dpad_is_pressed = 3
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
		OR lstickx > 100
			dpad_is_pressed = 4
		ENDIF

		IF NOT dpad_is_pressed = temp_int 

			IF dpad_is_pressed = 4
				IF current_bet_type = 0
				OR current_bet_type = 1
				OR current_bet_type = 3
				OR current_bet_type = 4
					current_bet_type += 1
				ELSE
					current_bet_type += -2	
				ENDIF
				TIMERA = 0
			ENDIF
			IF dpad_is_pressed = 3
				IF current_bet_type = 1
				OR current_bet_type = 2
				OR current_bet_type = 4
				OR current_bet_type = 5
					current_bet_type += -1
				ELSE
					current_bet_type += 2	
				ENDIF
				TIMERA = 0
			ENDIF
			IF dpad_is_pressed = 1
			OR dpad_is_pressed = 2
				IF current_bet_type = 0
				OR current_bet_type = 1
				OR current_bet_type = 2
					current_bet_type += 3
				ELSE
					current_bet_type += -3	
				ENDIF
				TIMERA = 0
			ENDIF

		ENDIF


		// add wager

		IF IS_BUTTON_PRESSED PAD1 BUTTON_BET_UP
			IF NOT cross_is_pressed = 1 
			AND NOT cross_is_pressed = -1

				// sort out betting step
				IF current_bet[current_bet_type] >= 100000
					betting_step = 10000
				ELSE
					IF current_bet[current_bet_type] >= 10000
						betting_step = 1000
					ELSE
						IF current_bet[current_bet_type] >= 1000
							betting_step = 100
						ELSE
							IF current_bet[current_bet_type] >= 100
								betting_step = 10
							ELSE
								IF current_bet[current_bet_type] = 0
									IF current_bet_type = 2
										betting_step = 5
									ELSE
										betting_step = 2
									ENDIF
								ELSE
									betting_step = 1	
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// check betting step doesn't go over players cash
				STORE_SCORE player1 temp_int
				GOSUB wof_update_casino_credit
				temp_int += casino_credit

				IF betting_step > temp_int
					betting_step = temp_int
				ENDIF

				// check betting step doesn't push us over maximum bet
				total_bet = 0
				temp_int = 0
				WHILE temp_int < 6
					total_bet += current_bet[temp_int] 	
				temp_int++
				ENDWHILE
				temp_int = max_bet - total_bet
				IF temp_int < betting_step
					betting_step = temp_int
				ENDIF

			
				IF betting_step < 0
					betting_step *= -1
				ENDIF
				current_bet[current_bet_type] += betting_step
				//WRITE_DEBUG_WITH_INT betting_step betting_step
				wof_initial_stake += betting_step
				wof_refund		  += betting_step
				betting_step *= -1
				ADD_SCORE player1 betting_step
					
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

			// Remove wager.

			IF IS_BUTTON_PRESSED PAD1 BUTTON_BET_DOWN
				IF NOT square_is_pressed = 1

					// sort out betting step
					IF current_bet[current_bet_type] > 100000
						betting_step = -10000
					ELSE
						IF current_bet[current_bet_type] > 10000
							betting_step = -1000
						ELSE
							IF current_bet[current_bet_type] > 1000
								betting_step = -100
							ELSE
								IF current_bet[current_bet_type] > 100
									betting_step = -10
								ELSE
									betting_step = -1
								ENDIF
							ENDIF
						ENDIF
					ENDIF				 

					// check we don't go below minimum bet
					temp_int = current_bet[current_bet_type]
					
					current_bet[current_bet_type] += betting_step
					IF current_bet_type = 2
						// if we go below the minimum bet, then go to zero
						IF current_bet[current_bet_type] < 5
							current_bet[current_bet_type] = 0
							betting_step = temp_int
							betting_step *= -1
						ENDIF
					ELSE
						IF current_bet[current_bet_type] < 2
							current_bet[current_bet_type] = 0
							betting_step = temp_int
							betting_step *= -1
						ENDIF 
					ENDIF

				
					IF current_bet[current_bet_type] < 0
						current_bet[current_bet_type] = 0
					ELSE
						wof_initial_stake += betting_step
						wof_refund		  += betting_step
						betting_step *= -1
						ADD_SCORE player1 betting_step
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

		// recalculate total bet
		total_bet = 0
		temp_int = 0
		WHILE temp_int < 6
			total_bet += current_bet[temp_int] 	
		temp_int++
		ENDWHILE

		// place marker on table
		IF current_bet_type = 0
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.635 0.253 0.041 x y z
			SET_OBJECT_COORDINATES marker x y z
		ENDIF
		IF current_bet_type = 1
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.201 0.253 0.041  x y z
			SET_OBJECT_COORDINATES marker x y z  
		ENDIF
		IF current_bet_type = 2
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.243 0.253 0.041  x y z
			SET_OBJECT_COORDINATES marker x y z  
		ENDIF
		IF current_bet_type = 3
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.635 -0.106 0.041 x y z
			SET_OBJECT_COORDINATES marker x y z  
		ENDIF
		IF current_bet_type = 4
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.201 -0.106 0.041 x y z
			SET_OBJECT_COORDINATES marker x y z  
		ENDIF
		IF current_bet_type = 5
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.243 -0.106 0.041 x y z
			SET_OBJECT_COORDINATES marker x y z  
		ENDIF

		SWITCH current_Language
		CASE LANGUAGE_ITALIAN
			DRAW_WINDOW 29.0 220.0 173.0 409.0 BJ_TITL SWIRLS_RIGHT
			BREAK
		DEFAULT
			DRAW_WINDOW 29.0 220.0 157.0 409.0 BJ_TITL SWIRLS_RIGHT
			BREAK
		ENDSWITCH

		GOSUB text_wof
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 240.0 WOF_04

		GOSUB text_wof 
		SET_TEXT_SCALE 0.6253 2.7876 
		DISPLAY_TEXT_WITH_NUMBER 36.0 260.0 DOLLAR current_bet[current_bet_type] 

		GOSUB text_wof
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 290.0 WOF_05

		GOSUB text_wof
		SET_TEXT_SCALE 0.6253 2.7876 
		DISPLAY_TEXT_WITH_NUMBER 36.0 310.0 DOLLAR total_bet 

		GOSUB text_wof
		SET_TEXT_COLOUR 134 155 184 255 
		DISPLAY_TEXT 36.0 340.0 BJ_03

		GOSUB text_wof
		SET_TEXT_SCALE 0.6253 2.7876 
		DISPLAY_TEXT_WITH_NUMBER 36.0 360.0 DOLLAR max_bet 


		// Player has decided to proceed.
		
		IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
		
		//OR TIMERB > 60000
			IF cross_is_pressed = 0

				IF total_bet > 0

					INCREMENT_INT_STAT MONEY_SPENT_GAMBLING total_bet
					temp_float =# total_bet
					temp_float *= 0.001
					INCREMENT_FLOAT_STAT GAMBLING temp_float

					TIMERA = 0
					wof_refund = 0
					m_goals = 99
					
				ENDIF	

				cross_is_pressed = 1
			ENDIF
		ELSE
			IF NOT cross_is_pressed = 0
				cross_is_pressed = 0
			ENDIF
		ENDIF					
		
	ENDIF			

	IF m_goals = 99
		CLEAR_HELP
		m_stage++
		m_goals = 0
	ENDIF


RETURN

wof_m_stage_2:

	IF NOT IS_CHAR_DEAD croupier
		TASK_PLAY_ANIM croupier wof casino 4.0 FALSE FALSE FALSE FALSE -1
		GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
		SWITCH temp_int
			CASE 0
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_1 TRUE TRUE FALSE
			BREAK
			CASE 1
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_2 TRUE TRUE FALSE
			BREAK
			CASE 2
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_NMB_3 TRUE TRUE FALSE
			BREAK
		ENDSWITCH

		wheel_status = 0

		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -1.3681 -2.0088 0.7317	x y z
		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.8154 -1.1754 0.7401	x y z
		POINT_CAMERA_AT_POINT x y z JUMP_CUT

		IF DOES_OBJECT_EXIST marker
			SET_OBJECT_VISIBLE marker FALSE
		ENDIF

		m_stage++
	ENDIF
RETURN
													
wof_m_stage_3:										
	IF wheel_rotation_speed < 2.0					  
	AND wheel_status > 1							  
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS m_stand -0.3059 -0.9385 0.4225 x y z
		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0 
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS m_stand -0.0298 -0.0515 0.7929 x y z
		SET_INTERPOLATION_PARAMETERS 0.0 6000
		POINT_CAMERA_AT_POINT x y z INTERPOLATION
		m_stage++
	ENDIF
RETURN

wof_m_stage_4:
	IF wheel_status = 4 // finished
//		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 1.5626 -1.7272 0.6069 x y z
//		SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
//		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.9631 -0.9279 0.5657 x y z
//		POINT_CAMERA_AT_POINT x y z JUMP_CUT   
		m_goals = 0
		m_stage++
	ENDIF
RETURN

wof_m_stage_5:
	
	IF m_goals = 0
		// which value has the wheel stopped at?
		temp_float = wheel_stop_position - 3.3333333
		IF temp_float < 0.0
			temp_float += 360.0
		ENDIF
		temp_float /= 6.6666667
		wheel_position =# temp_float
		wheel_position += 1
		GOSUB get_wheel_position_value

		//WRITE_DEBUG_WITH_INT wheel_position_value wheel_position_value 

		// figure out how much to player
		temp_int = 0
		wof_winnings = 0
		IF wheel_position_value = 1
			IF current_bet[2] > 0
				wof_winnings = 2 * current_bet[2]
			ENDIF
		ENDIF
		IF wheel_position_value = 2
			IF current_bet[4] > 0
				wof_winnings = 3 * current_bet[4]
			ENDIF
		ENDIF
		IF wheel_position_value = 5
			IF current_bet[1] > 0
				wof_winnings = 6 * current_bet[1]
			ENDIF
		ENDIF
		IF wheel_position_value = 10
			IF current_bet[3] > 0
				wof_winnings = 11 * current_bet[3]
			ENDIF
		ENDIF
		IF wheel_position_value = 20
			IF current_bet[0] > 0
				wof_winnings = 21 * current_bet[0]
			ENDIF	
		ENDIF
		IF wheel_position_value = 40
			IF current_bet[5] > 0
				wof_winnings = 41 * current_bet[5] 
			ENDIF
		ENDIF

		TIMERA = 0

		temp_int = wof_winnings - wof_initial_stake
		ADD_SCORE player1 wof_winnings // original wager

		IF temp_int > 0
			INCREMENT_INT_STAT MONEY_WON_GAMBLING temp_int
		ELSE
			//INCREMENT_INT_STAT MONEY_LOST_GAMBLING temp_int
		ENDIF

		START_NEW_SCRIPT display_win_text temp_int 3000
		SHOW_UPDATE_STATS FALSE
		
		IF temp_int > 0
		
			REGISTER_INT_STAT BIGGEST_GAMBLING_WIN temp_int
			
			// play win dialogue
			temp_float =# temp_int
			temp_float2 =# max_bet
			temp_float /= temp_float2

			winning_streak++

			IF winning_streak < 3
	
				IF temp_float > 0.9
					IF IS_PLAYER_PLAYING player1
						IF NOT IS_CHAR_DEAD croupier
							GENERATE_RANDOM_INT_IN_RANGE 0 5 temp_int
							SWITCH temp_int
								CASE 0
									SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN1 TRUE TRUE FALSE
								BREAK
								CASE 1
									SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN2 TRUE TRUE FALSE
								BREAK
								CASE 2
									SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN3 TRUE TRUE FALSE
								BREAK
								CASE 3
									SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_GAMB_CASINO_WIN	temp_int
								BREAK
								DEFAULT
									GOSUB wof_say_number
								BREAK
							ENDSWITCH
						ENDIF
					ENDIF
				ELSE
					IF NOT IS_CHAR_DEAD croupier
						GENERATE_RANDOM_INT_IN_RANGE 0 10 temp_int
						SWITCH temp_int
							CASE 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN1 TRUE TRUE FALSE
							BREAK
							CASE 1
								SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN2 TRUE TRUE FALSE
							BREAK
							CASE 2
								SET_CHAR_SAY_SCRIPT	croupier SOUND_W_PWIN3 TRUE TRUE FALSE
							BREAK
							DEFAULT
								GOSUB wof_say_number
							BREAK
						ENDSWITCH
					ENDIF
				ENDIF

			ELSE

				IF NOT IS_CHAR_DEAD croupier
					GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
					SWITCH temp_int
						CASE 0
							SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WINS1 TRUE TRUE FALSE
						BREAK
						CASE 1
							SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WINS2 TRUE TRUE FALSE
						BREAK
						CASE 2
							SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WINS3 TRUE TRUE FALSE
						BREAK
					ENDSWITCH
				ENDIF
									
				winning_streak = 0
			ENDIF
			

		ELSE
			temp_int *= -1
			REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS temp_int
			temp_int *= -1
			GOSUB wof_say_number
			winning_streak = 0
		ENDIF
		SHOW_UPDATE_STATS TRUE

		m_goals++
	ENDIF

	IF m_goals = 1
		IF TIMERA < 3000
		ELSE
			m_goals++

		ENDIF
	ENDIF

	IF m_goals = 2
		
		PRINT_HELP_FOREVER WOF_09  
		
		temp_int = 0
		WHILE temp_int < 6
			current_bet[temp_int] = 0
		temp_int++
		ENDWHILE

		TIMERA = 0
		m_goals = 99
	ENDIF

	IF m_goals = 99
		TIMERA = 0
		m_stage++
	ENDIF
		
RETURN

wof_say_number:

	IF NOT IS_CHAR_DEAD croupier
		SWITCH wheel_position_value
			CASE 1
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL1 TRUE TRUE FALSE
			BREAK
			CASE 2
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL2 TRUE TRUE FALSE
			BREAK
			CASE 5
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL3 TRUE TRUE FALSE
			BREAK
			CASE 10
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL4 TRUE TRUE FALSE
			BREAK
			CASE 20
				SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL5 TRUE TRUE FALSE
			BREAK
			CASE 40
				GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0
					SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL6 TRUE TRUE FALSE
				ELSE
					SET_CHAR_SAY_SCRIPT	croupier SOUND_W_WEEL7 TRUE TRUE FALSE
				ENDIF
			BREAK
		ENDSWITCH
	ENDIF

RETURN


wof_m_stage_6:
	
	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
	//OR TIMERA > 4000
		cross_is_pressed = -1
		CLEAR_PRINTS
		CLEAR_HELP
		m_stage = 1
		m_goals = 0
	ENDIF
RETURN

	wof_text_winner:
		GET_HUD_COLOUR HUD_COLOUR_YELLOW colour_r colour_g colour_b colour_a
		SET_TEXT_COLOUR colour_r colour_g colour_b colour_a
		SET_TEXT_FONT FONT_HEADING
		SET_TEXT_EDGE 3 0 0 0 255
		SET_TEXT_SCALE 1.2 4.0
		SET_TEXT_JUSTIFY ON
        SET_TEXT_RIGHT_JUSTIFY OFF
        SET_TEXT_WRAPX 1000.0
        SET_TEXT_PROPORTIONAL ON
        SET_TEXT_CENTRE ON
        SET_TEXT_BACKGROUND OFF
	RETURN

	wof_text_loser:
		GET_HUD_COLOUR HUD_COLOUR_RED colour_r colour_g colour_b colour_a
		SET_TEXT_COLOUR colour_r colour_g colour_b colour_a
		SET_TEXT_FONT FONT_HEADING
		SET_TEXT_EDGE 3 0 0 0 255
		SET_TEXT_SCALE 1.2 4.0
		SET_TEXT_JUSTIFY ON
        SET_TEXT_RIGHT_JUSTIFY OFF
        SET_TEXT_WRAPX 1000.0
        SET_TEXT_PROPORTIONAL ON
        SET_TEXT_CENTRE ON
        SET_TEXT_BACKGROUND OFF
	RETURN

	wof_text_nowin:
		GET_HUD_COLOUR HUD_COLOUR_WHITE colour_r colour_g colour_b colour_a
		SET_TEXT_COLOUR colour_r colour_g colour_b colour_a
		SET_TEXT_FONT FONT_HEADING
		SET_TEXT_EDGE 3 0 0 0 255
		SET_TEXT_SCALE 1.2 4.0
		SET_TEXT_JUSTIFY ON
        SET_TEXT_RIGHT_JUSTIFY OFF
        SET_TEXT_WRAPX 1000.0
        SET_TEXT_PROPORTIONAL ON
        SET_TEXT_CENTRE ON
        SET_TEXT_BACKGROUND OFF
	RETURN

	wof_text_winnings_small:
		GET_HUD_COLOUR HUD_COLOUR_WHITE	colour_r colour_g colour_b colour_a
		SET_TEXT_COLOUR colour_r colour_g colour_b colour_a
		SET_TEXT_FONT FONT_SPACEAGE
		SET_TEXT_EDGE 2 0 0 0 255
		SET_TEXT_SCALE 0.52 1.45
		SET_TEXT_JUSTIFY ON
        SET_TEXT_RIGHT_JUSTIFY OFF
        SET_TEXT_WRAPX 1000.0
        SET_TEXT_PROPORTIONAL ON
        SET_TEXT_CENTRE ON
        SET_TEXT_BACKGROUND OFF
	RETURN



wheelo_process_wheel:

		SWITCH wheel_status
																			    
			// stationary - start spinning
			CASE 0
				GENERATE_RANDOM_FLOAT_IN_RANGE 0.1 0.15 wheel_acceleration	
				TIMERB = 0
				wheel_status++	
			BREAK

			// speeding up - travelling right
			CASE 1
				wheel_rotation_speed +=@ wheel_acceleration
				wheel_ry +=@ wheel_rotation_speed
				IF TIMERB > 500
					wheel_acceleration = -0.005
					wheel_status++
				ENDIF
			BREAK

			// slowing down	- travelling left
			CASE 2
				wheel_rotation_speed +=@ wheel_acceleration
				wheel_ry +=@ wheel_rotation_speed

				//WRITE_DEBUG_WITH_FLOAT wheel_rotation_speed	wheel_rotation_speed

				// wait until it's good to finish
				IF wheel_rotation_speed < 0.3
					// find nearest peg to stop at.
					wheel_stop_position = wheel_ry
					temp_float = wheel_stop_position / 6.6667
					temp_int =# temp_float
					temp_float2 =# temp_int
					temp_float -= temp_float2
					temp_float += -1.0
					temp_float *= -1.0
					temp_float *= 6.6666667
					wheel_stop_position += temp_float
					//wheel_stop_position += 3.33333
					IF wheel_stop_position > 360.0
						wheel_stop_position += -360.0
					ENDIF
					wheel_status++		
				ENDIF
			BREAK

			// finishing
			CASE 3

				
				// do the funky finish thing
				temp_float = wheel_ry - wheel_stop_position
				IF temp_float < -180.0
					temp_float += 360.0
				ENDIF
				IF temp_float > 180.0
					temp_float += -360.0
				ENDIF

				//WRITE_DEBUG_WITH_FLOAT DIFF temp_float
				
				// traveling right
				IF wheel_rotation_speed	> 0.01 
					IF temp_float > 3.3333333333
						wheel_ry = wheel_stop_position + 3.33333333
						IF wheel_ry > 360.0
							wheel_ry += -360.0
						ENDIF
						wheel_rotation_speed *= -1.0
						wheel_acceleration *= -1.0	
					ENDIF
				ELSE
					// travelling left
					IF wheel_rotation_speed < -0.01
						IF temp_float < -3.333333333 
							wheel_ry = wheel_stop_position - 3.3333333
							IF wheel_ry < 0.0
								wheel_ry += 360.0
							ENDIF
							wheel_rotation_speed *= -1.0
							wheel_acceleration *= -1.0
						ENDIF
					ELSE
						// stop wheel
						wheel_rotation_speed = 0.0
						wheel_acceleration = 0.0
						wheel_status++
					ENDIF
				ENDIF

				wheel_rotation_speed +=@ wheel_acceleration
				wheel_ry +=@ wheel_rotation_speed

			BREAK

			// finished
			CASE 4
			BREAK

		ENDSWITCH
	

	IF NOT wheel_rotation_speed = 0.0
		IF wheel_ry > 360.0
			wheel_ry += -360.0
		ENDIF
		IF wheel_ry < 0.0
			wheel_ry += 360.0
		ENDIF		
		SET_OBJECT_ROTATION wheel 0.0 wheel_ry wheel_heading	
	ENDIF


	// move clicker
	IF DOES_OBJECT_EXIST m_clicker
		//IF active_wheel = wheel
			IF wheel_rotation_speed > 0.2
				IF wheel_rotation_speed < 0.8

					temp_float = wheel_ry
					temp_float += 3.33333
					temp_float /= 6.6667
					temp_int =# temp_float
					temp_float2 =# temp_int
					temp_float -= temp_float2
					temp_float += -1.0
					temp_float *= -1.0
					temp_float *= 6.6666667

					IF temp_float > 5.0
						temp_float += -5.0
						temp_float /= 1.66667
						temp_float += -1.0
						temp_float *= -1.0
						temp_float *= 5.0
						clicker_ry = temp_float	* -1.0
						//WRITE_DEBUG CLICKER1
						IF NOT wof_clacker_sound_flag = 1
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_clicker SOUND_WHEEL_OF_FORTUNE_CLACKER
							wof_clacker_sound_flag = 1
						ENDIF
					ELSE
						clicker_ry +=@ 0.5 
						//WRITE_DEBUG CLICKER2
						IF clicker_ry > 0.0
							clicker_ry = 0.0
							//WRITE_DEBUG CLICKER3
						ENDIF
						IF NOT wof_clacker_sound_flag = 0
							wof_clacker_sound_flag = 0
						ENDIF
					ENDIF
					
				ELSE
					IF clicker_ry = -5.0
						clicker_ry = -2.0
						//WRITE_DEBUG CLICKER4
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_clicker SOUND_WHEEL_OF_FORTUNE_CLACKER
					ELSE
						clicker_ry = -5.0
						//WRITE_DEBUG CLICKER5
					ENDIF	
				ENDIF
			ELSE
				IF clicker_ry < 0.0
					clicker_ry +=@ 0.5
					//WRITE_DEBUG CLICKER6
					IF clicker_ry > 0.0
						clicker_ry = 0.0
						//WRITE_DEBUG CLICKER7
					ENDIF
				ENDIF
			ENDIF
			SET_OBJECT_ROTATION m_clicker 0.0 clicker_ry wheel_heading
		//ELSE

			
		
		//ENDIF
	ENDIF



RETURN

wof_cleanup:

	SET_MINIGAME_IN_PROGRESS FALSE

	m_quit = 0
	m_stage = 0
	m_goals = 0
				
	IF wof_refund > 0
		ADD_SCORE player1 wof_refund
	ENDIF
	wof_refund = 0

	IF NOT IS_CHAR_DEAD croupier
		GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
		IF temp_int = 0
			SET_CHAR_SAY_SCRIPT	croupier SOUND_W_THX_1 TRUE TRUE FALSE
		ELSE
			SET_CHAR_SAY_SCRIPT	croupier SOUND_W_THX_2 TRUE TRUE FALSE
		ENDIF

	ENDIF



	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 ON
	ENDIF
	
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME cashwin
	USE_TEXT_COMMANDS FALSE
	DISPLAY_RADAR TRUE
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	temp_int = 0
	WHILE temp_int < 6
		DELETE_OBJECT chip_set_1[temp_int]
		DELETE_OBJECT chip_set_2[temp_int]
		DELETE_OBJECT chip_set_3[temp_int]
		DELETE_OBJECT chip_set_4[temp_int]
		DELETE_OBJECT chip_set_5[temp_int]
		DELETE_OBJECT chip_set_6[temp_int]
		DELETE_OBJECT chip_set_7[temp_int]
		DELETE_OBJECT chip_set_8[temp_int]
	temp_int++
	ENDWHILE
		
	DELETE_OBJECT marker
	DELETE_MENU wof_betting_menu

	IF  NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
		CLEAR_HELP
	ENDIF

RETURN

wof_update_casino_credit:

	GET_FLOAT_STAT GAMBLING temp_float
	IF temp_float < 1.0
		casino_credit = 100
	ELSE
		IF temp_float < 10.0
			casino_credit = 1000
		ELSE
			IF temp_float < 100.0
				casino_credit = 10000
			ELSE
				IF temp_float < 1000.0
					casino_credit = 100000
				ELSE
					casino_credit = 1000000
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN



wof_hud_text_1:
	SET_TEXT_SCALE 0.4487 1.5974 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN

text_wof:
	SET_TEXT_COLOUR 180 180 180 255
	SET_TEXT_SCALE 0.4714 2.5077
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
RETURN



get_wheel_position_value:
	IF wheel_position = 1
		wheel_position_value = 40
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 2
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 3
		wheel_position_value = 10
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 4
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 5
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 6
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 7
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 8
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 9
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 10
		wheel_position_value = 10
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 11
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 12
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 13
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 14
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 15
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 16
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 17
		wheel_position_value = 20
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 18
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 19
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 20
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 21
		wheel_position_value = 10
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 22
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 23
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 24
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 25
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 26
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 27
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 28
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 29
		wheel_position_value = 40
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 30
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 31
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 32
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 33
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 34
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 35
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 36
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 37
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 38
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 39
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 40
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 41
		wheel_position_value = 20	  
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 42
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 43
		wheel_position_value = 10
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 44
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 45
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 46
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 47
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 48
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 49
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 50
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 51
		wheel_position_value = 5
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 52
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 53
		wheel_position_value = 2
		GOTO get_wheel_position_value_end
	ENDIF

	IF wheel_position = 54
		wheel_position_value = 1
		GOTO get_wheel_position_value_end
	ENDIF		
	
get_wheel_position_value_end:
RETURN

wof_generate_chipstack:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
		chip_set_z += 0.001
		WRITE_DEBUG_WITH_FLOAT chip_set_z chip_set_z
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		chip_set_z += -0.001
		WRITE_DEBUG_WITH_FLOAT chip_set_z chip_set_z
	ENDIF

	temp_int = 0
	WHILE temp_int < 6
		// for each betting square
		
		temp_int3 = current_bet[temp_int]

		// number of 1s
		temp_float =# temp_int3
		temp_float /= 1.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_1_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -1
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_1[temp_int]
			GET_OBJECT_COORDINATES chip_set_1[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_1_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_1[temp_int] x y z	
		ENDIF

		// number of 5s
		temp_float =# temp_int3
		temp_float /= 5.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_2_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -5
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_2[temp_int]
			GET_OBJECT_COORDINATES chip_set_2[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_2_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_2[temp_int] x y z	
		ENDIF

		// number of 10s
		temp_float =# temp_int3
		temp_float /= 10.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_3_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -10
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_3[temp_int]
			GET_OBJECT_COORDINATES chip_set_3[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_3_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_3[temp_int] x y z	
		ENDIF

		// number of 50s
		temp_float =# temp_int3
		temp_float /= 50.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_4_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -50
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_4[temp_int]
			GET_OBJECT_COORDINATES chip_set_4[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_4_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_4[temp_int] x y z	
		ENDIF

		// number of 100s
		temp_float =# temp_int3
		temp_float /= 100.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_5_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -100
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_5[temp_int]
			GET_OBJECT_COORDINATES chip_set_5[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_5_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_5[temp_int] x y z	
		ENDIF

		// number of 200s
		temp_float =# temp_int3
		temp_float /= 200.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_6_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -200
		temp_int3 += temp_int2	
		IF DOES_OBJECT_EXIST chip_set_6[temp_int]
			GET_OBJECT_COORDINATES chip_set_6[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_6_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_6[temp_int] x y z	
		ENDIF

		// number of 500s
		temp_float =# temp_int3
		temp_float /= 500.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_7_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -500
		temp_int3 += temp_int2
		IF DOES_OBJECT_EXIST chip_set_7[temp_int]
			GET_OBJECT_COORDINATES chip_set_7[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_7_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_7[temp_int] x y z	
		ENDIF

		// number of 1000s
		temp_float =# temp_int3
		temp_float /= 1000.0
		IF temp_float > 10.0
			temp_float = 10.0
		ENDIF
		temp_int2 =# temp_float
		temp_float2 =# temp_int2 
		temp_float2 *= 0.0126
		IF temp_int2 > 0
			temp_float2 += 0.03
		ENDIF
		chip_set_8_z[temp_int] = chip_set_z + temp_float2
		temp_int2 *= -1000
		temp_int3 += temp_int2
		IF DOES_OBJECT_EXIST chip_set_8[temp_int]
			GET_OBJECT_COORDINATES chip_set_8[temp_int] x y z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 chip_set_8_z[temp_int] temp_float	temp_float z
			SET_OBJECT_COORDINATES chip_set_8[temp_int] x y z	
		ENDIF

	temp_int++
	ENDWHILE

RETURN

wof_get_max_bet:

	IF DOES_OBJECT_EXIST wheel

		GET_FLOAT_STAT GAMBLING temp_float

		IF total_wof_tables = 0
			IF temp_float < 1.0
				max_bet = 100
			ELSE
				IF temp_float < 10.0
					max_bet = 1000
				ELSE
					IF temp_float < 100.0
						max_bet = 10000
					ELSE
						IF temp_float < 1000.0
							max_bet = 100000
						ELSE
							max_bet = 1000000
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			total_wof_tables++
		ELSE
			GENERATE_RANDOM_INT_IN_RANGE 0 5 temp_int
			SWITCH temp_int
				CASE 0
					max_bet = 100		
				BREAK
				CASE 1
					max_bet = 1000
				BREAK
				CASE 2
					max_bet = 10000
				BREAK
				CASE 3
					max_bet = 100000
				BREAK
				CASE 4
					max_bet = 1000000
				BREAK
			ENDSWITCH
		ENDIF
	ENDIF

RETURN


wof_find_camera_position:

	// for camera position
	GET_DEBUG_CAMERA_COORDINATES x y z
	IF DOES_OBJECT_EXIST m_stand
		GET_OBJECT_COORDINATES m_stand wof_x2 wof_y2 wof_z2
		GET_OBJECT_HEADING m_stand heading
	ENDIF
	heading *= -1.0
	COS heading temp_float
	SIN heading temp_float2
	// get coords relative to car 
	wof_vec_x = x - wof_x2
	wof_vec_y = y - wof_y2
	wof_vec_z = z - wof_z2
	// work out new wof_vec_x
	wof_vec2_x = wof_vec_x * temp_float
	wof_temp_float3 = wof_vec_y * temp_float2
	wof_vec2_x -= wof_temp_float3
	// work out new wof_vec_y
	wof_vec2_y = wof_vec_x * temp_float2
	wof_temp_float3 = wof_vec_y * temp_float
	wof_vec2_y += wof_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec_z

	// for camera point at
	GET_DEBUG_CAMERA_POINT_AT x y z
	// get coords relative to car 
	wof_vec_x = x - wof_x2
	wof_vec_y = y - wof_y2
	wof_vec_z = z - wof_z2
	// work out new wof_vec_x
	wof_vec2_x = wof_vec_x * temp_float
	wof_temp_float3 = wof_vec_y * temp_float2
	wof_vec2_x -= wof_temp_float3
	// work out new wof_vec_y
	wof_vec2_y = wof_vec_x * temp_float2
	wof_temp_float3 = wof_vec_y * temp_float
	wof_vec2_y += wof_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE wof_vec_z

RETURN

}


MISSION_END

