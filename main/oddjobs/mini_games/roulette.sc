MISSION_START
// *****************************************************************************************
// *************************************  roulette  ****************************************
// *****************************************************************************************


{///////////////////////////////////////////////////////////////////////////////
roulette_script:////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME ROULETE

LVAR_INT roulette_table
LVAR_INT flag

flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET ROULETTE_TBL 0.0 0.0 0.0 roulette_table
	CREATE_OBJECT_NO_OFFSET chip_stack07 x y z placed_chips[i]
ENDIF

LVAR_INT pad1_left_pressed pad1_right_pressed pad1_up_pressed pad1_down_pressed pad1_CIRCLE_pressed pad1_cross_pressed pad1_square_pressed pad1_triangle_pressed
pad1_left_pressed  = 0
pad1_right_pressed = 0
pad1_up_pressed    = 0
pad1_down_pressed  = 0
pad1_square_pressed = 0
pad1_triangle_pressed = 0
pad1_cross_pressed = 0
LVAR_INT square_press_timer circle_press_timer
square_press_timer = 500
circle_press_timer = 500

LVAR_INT dpadleft_timer dpadright_timer dpadup_timer dpaddown_timer
dpadleft_timer  = 0
dpadright_timer = 0
dpadup_timer    = 0
dpaddown_timer  = 0

lvar_float gambling_stat_fl
get_float_stat GAMBLING gambling_stat_fl
lVAR_INT chip_value spin_result
GENERATE_RANDOM_INT_IN_RANGE 0 19 spin_result

++ total_roulette_tables

if gambling_stat_fl < 1.0
	if total_roulette_tables = 1
	or total_roulette_tables = 5
		spin_result = 0
	endif
else
	if gambling_stat_fl < 10.0
		if total_roulette_tables = 1
		or total_roulette_tables = 5
			spin_result = 5
		endif
	else
		if gambling_stat_fl < 100.0
			if total_roulette_tables = 1
			or total_roulette_tables = 5
				spin_result = 10
			endif
		else
			if gambling_stat_fl < 1000.0
				if total_roulette_tables = 1
				or total_roulette_tables = 5
					spin_result = 14
				endif
			else
				if total_roulette_tables = 1
				or total_roulette_tables = 5
					spin_result = 17
				endif
			endif
		endif
	endif
endif

if spin_result = 0
or spin_result = 1
or spin_result = 2
or spin_result = 3
or spin_result = 4
	chip_value = 1
	lvar_int coloured_chips
	coloured_chips = chip_stack07
endif
if spin_result = 5
or spin_result = 6
or spin_result = 7
or spin_result = 8
or spin_result = 9
	chip_value = 10
	coloured_chips = chip_stack08
endif
if spin_result = 10
or spin_result = 11
or spin_result = 12
or spin_result = 13
	chip_value = 100
	coloured_chips = chip_stack12
endif
if spin_result = 14
or spin_result = 15
or spin_result = 16
	chip_value = 1000
	coloured_chips = chip_stack09
endif
if spin_result = 17
or spin_result = 18
	chip_value = 10000
	coloured_chips = chip_stack11
endif

spin_result = 0


lVAR_INT spot_bet max_table_bet
spot_bet = 0
max_table_bet = chip_value * 100



lVAR_INT reset_all_bets_flag
reset_all_bets_flag = 0

GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_integer_1
IF temp_integer_1 = 0
	lvar_int roulette_model
	roulette_model = VBFYCRP
ELSE
	roulette_model = VWFYCRP
ENDIF

reset_all_bets:
VAR_INT spot_bets[151]
VAR_INT i
i = 0
WHILE i < 151
	spot_bets[i] = 0
	++ i
ENDWHILE

VAR_INT winnings_this_round total_stake_this_round
winnings_this_round = 0
total_stake_this_round = 0

IF reset_all_bets_flag = 1
	reset_all_bets_flag = 0
	RETURN
ENDIF

lVAR_FLOAT wheel_rotation_speed wheel_rotation
wheel_rotation_speed = 0.0
wheel_rotation = 0.0

roulette_script_loop:

	WAIT 0

	GET_GAME_TIMER game_timer
    IF DOES_OBJECT_EXIST roulette_table
		if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE roulette_table
			if iTerminateAllAmbience = 0
				IF IS_PLAYER_PLAYING player1
					IF flag = 0
						REQUEST_MODEL roulette_model
						//REQUEST_ANIMATION CASINO
						++ flag
					endif
					IF flag = 1
						//load anims/models etc & create extra objects etc - cleanup stuff created here in '//cleanup'
						REQUEST_MODEL roulette_model
						//REQUEST_ANIMATION CASINO
						IF HAS_MODEL_LOADED	roulette_model
						//and HAS_ANIMATION_LOADED CASINO
							
							lvar_int croupier
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table 1.208 0.571 -1.144 x y z
							create_char pedtype_civfemale roulette_model x y z croupier
							get_object_heading roulette_table heading
							heading += 90.0
							set_char_heading croupier heading
							
							SET_CHAR_DECISION_MAKER croupier iGlobalPedPanicDM

							ENABLE_DISABLED_ATTRACTORS_ON_OBJECT roulette_table TRUE

							++ flag
						endif
					ENDIF
					IF flag = 2
						IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer roulette_table 5.0 5.0 0
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -0.215 1.34 -0.086 x y z//-0.102 1.136 -0.033
							lVAR_INT roulette_wheel
							CREATE_OBJECT_NO_OFFSET wheel_wee x y z roulette_wheel
							++ flag
						ENDIF
					ENDIF
					IF flag = 3
						IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer roulette_table 5.0 5.0 0
							if iSetCasinoPanic = 0
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -1.839 -2.4444 -0.6 x y z
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table 0.761 0.9556 0.6 x_temp y_temp z_temp
								IF IS_CHAR_IN_AREA_ON_FOOT_3D scplayer x y z x_temp y_temp z_temp 0 //LOCATE_CHAR_ON_FOOT_3D scplayer x y z 1.3 1.7 0.6 0
									if chip_value = 1
										PRINT_HELP_FOREVER SLOT_06 //PRESS <BUTTON> TO USE OBJECT
									endif
									if chip_value = 10
										PRINT_HELP_FOREVER SLOT_07 //PRESS <BUTTON> TO USE OBJECT
									endif
									if chip_value = 100
										PRINT_HELP_FOREVER SLOT_08 //PRESS <BUTTON> TO USE OBJECT
									endif
									if chip_value = 1000
										PRINT_HELP_FOREVER SLOT_09 //PRESS <BUTTON> TO USE OBJECT
									endif
									if chip_value = 10000
										PRINT_HELP_FOREVER SLOT_10 //PRESS <BUTTON> TO USE OBJECT
									endif
									load_MISSION_AUDIO 4 SOUND_BANK_ROULETTE 
									++ flag
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF flag = 4
						if roulette_triggered = 0
							IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer roulette_table 5.0 5.0 0
								if iSetCasinoPanic = 0
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -1.839 -2.4444 -0.6 x y z
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table 0.761 0.9556 0.6 x_temp y_temp z_temp
									IF IS_CHAR_IN_AREA_ON_FOOT_3D scplayer x y z x_temp y_temp z_temp 0 //LOCATE_CHAR_ON_FOOT_3D scplayer x y z 1.3 1.7 0.6 0
										if can_player_start_mission player1
											IF IS_BUTTON_PRESSED PAD1 TRIANGLE
												IF pad1_triangle_pressed = 0
													GET_INT_STAT GAMBLING gambling_stat
													if chip_value = 10
														
														if not gambling_stat >= 1
															
															print_now GAMBSTA 4000 1//~s~Your ~h~Gambling Skill~s~ is not high enough to play on this table.
															if not is_char_dead croupier
																generate_random_int_in_range 0 2 an
																if roulette_model = VBFYCRP
																	SET_CHAR_SAY_SCRIPT	croupier gambling_stat_too_low_b[an] true true false
																else
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_w[an] true true false
																endif
															endif
															GOTO roulette_script_loop
														endif
													endif
													if chip_value = 100
														
														if not gambling_stat >= 10
														
															print_now GAMBSTA 4000 1//~s~Your ~h~Gambling Skill~s~ is not high enough to play on this table.
															if not is_char_dead croupier
																generate_random_int_in_range 0 2 an
																if roulette_model = VBFYCRP
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_b[an] true true false
																else
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_w[an] true true false
																endif
															endif
															GOTO roulette_script_loop
														endif
													endif
													if chip_value = 1000
														
														if not gambling_stat >= 100
															
															print_now GAMBSTA 4000 1//~s~Your ~h~Gambling Skill~s~ is not high enough to play on this table.
															if not is_char_dead croupier
																generate_random_int_in_range 0 2 an
																if roulette_model = VBFYCRP
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_b[an] true true false
																else
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_w[an] true true false
																endif
															endif
															GOTO roulette_script_loop
														endif
													endif
													if chip_value = 10000
														
														if not gambling_stat > 999
															
															print_now GAMBSTA 4000 1//~s~Your ~h~Gambling Skill~s~ is not high enough to play on this table.
															if not is_char_dead croupier
																generate_random_int_in_range 0 2 an
																if roulette_model = VBFYCRP
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_b[an] true true false
																else
																	SET_CHAR_SAY_SCRIPT croupier gambling_stat_too_low_w[an] true true false
																endif
															endif
															GOTO roulette_script_loop
														endif
													endif
													CLEAR_HELP

													SET_PLAYER_CONTROL player1 OFF
													SET_EVERYONE_IGNORE_PLAYER player1 TRUE
													SET_ALL_CARS_CAN_BE_DAMAGED FALSE
													
													get_object_coordinates roulette_table x y z
													TASK_TURN_CHAR_TO_FACE_COORD scplayer x y z
													
													if IS_WIDESCREEN_ON_IN_OPTIONS
														GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -0.6320 -1.9669 1.9144 x y z
														SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
														GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -0.5003 -1.3914 1.1073 x y z
														POINT_CAMERA_AT_POINT x y z INTERPOLATION
													else
														GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -0.5689 -1.5476 1.8759 x y z
														SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
														GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table -0.4106 -1.0626 1.0159 x y z
														POINT_CAMERA_AT_POINT x y z INTERPOLATION
													endif
													SET_NEAR_CLIP 0.1

													USE_TEXT_COMMANDS TRUE
													LOAD_TEXTURE_DICTIONARY ld_roul

													if not is_char_dead croupier
														generate_random_int_in_range 0 2 an
														if roulette_model = VBFYCRP
															SET_CHAR_SAY_SCRIPT croupier place_bets_b[an] true true false
														else
															SET_CHAR_SAY_SCRIPT croupier place_bets_w[an] true true false
														endif
													endif
													
													LOAD_SPRITE 1 ROULBLA
													LOAD_SPRITE 2 ROULRED
													LOAD_SPRITE 3 ROULGRE
							
													GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table spot1_x spot1_y spot1_z x y z
													VAR_INT chips_stack
													z += 0.01
													CREATE_OBJECT_NO_OFFSET roulette_marker x y z chips_stack//coloured_chips
													//timer = game_timer + 750
													SET_MINIGAME_IN_PROGRESS TRUE
													roulette_triggered = 1
													lvar_int done_credit_speech
													done_credit_speech = 0
													++ flag
													pad1_triangle_pressed = 1
												ENDIF
											ELSE
												pad1_triangle_pressed = 0
											ENDIF
										endif
									ELSE
										CLEAR_HELP
										flag = 3
									ENDIF
								ELSE
									CLEAR_HELP
									flag = 3
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF flag = 5
						DISPLAY_RADAR FALSE
						
						IF roulette_flag > 0

							sprite_x += 2.0
							sprite_y += 2.0
							    DRAW_SPRITE 1 sprite_x sprite_y 64.0 64.0 0 0 0 255
							sprite_x -= 2.0
							sprite_y -= 2.0
							IF spin_result = 0
							   	DRAW_SPRITE 3 sprite_x sprite_y 64.0 64.0 180 180 180 255
							ELSE
								IF spin_result = 1
								OR spin_result = 3
								OR spin_result = 5
								OR spin_result = 7
								OR spin_result = 9
								OR spin_result = 12
							   		DRAW_SPRITE 2 sprite_x sprite_y 64.0 64.0 180 180 180 255
								ELSE
									IF spin_result = 14
									OR spin_result = 16
									OR spin_result = 18
									OR spin_result = 19
									OR spin_result = 21
									OR spin_result = 23
							   			DRAW_SPRITE 2 sprite_x sprite_y 64.0 64.0 180 180 180 255
									ELSE
										IF spin_result = 25
										OR spin_result = 27
										OR spin_result = 30
										OR spin_result = 32
										OR spin_result = 34
										OR spin_result = 36
							   				DRAW_SPRITE 2 sprite_x sprite_y 64.0 64.0 180 180 180 255
										ELSE
							   				DRAW_SPRITE 1 sprite_x sprite_y 64.0 64.0 180 180 180 255
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							VAR_FLOAT text_positionn_x text_positionn_y
						 	text_positionn_x = sprite_x + -1.6679
						 	text_positionn_y = sprite_y + -30.0255
						 	GOSUB setup_text_roulette
							SET_TEXT_SCALE 0.9423 4.2306
							SET_TEXT_CENTRE ON
							DISPLAY_TEXT_WITH_NUMBER text_positionn_x text_positionn_y NUMBER spin_result
						ENDIF
						
						DRAW_WINDOW rou_text_x[2] rou_text_y[2] rou_text_x[3] rou_text_y[3] ROUWAGE SWIRLS_RIGHT
						GOSUB setup_text_roulette
						SET_TEXT_COLOUR 134 155 184 255

						IF current_Language = LANGUAGE_ITALIAN
							SET_TEXT_SCALE 0.42 2.5077
						ENDIF

						DISPLAY_TEXT rou_text_x[0] rou_text_y[0] ROU_MAX
						text_positionn_y = rou_text_y[0] + rou_text_y[1]

						GOSUB setup_text_roulette
						SET_TEXT_SCALE rou_x_scale[1] rou_y_scale[1]  
						DISPLAY_TEXT_WITH_NUMBER rou_text_x[0] text_positionn_y DOLLAR max_table_bet
						text_positionn_y += rou_text_x[1]

						GOSUB setup_text_roulette
						SET_TEXT_COLOUR 134 155 184 255 
						DISPLAY_TEXT rou_text_x[0] text_positionn_y ROUYOUR
						text_positionn_y += rou_text_y[1]

						GOSUB setup_text_roulette
						SET_TEXT_SCALE rou_x_scale[1] rou_y_scale[1] 
						DISPLAY_TEXT_WITH_NUMBER rou_text_x[0] text_positionn_y DOLLAR total_stake_this_round 
						text_positionn_y += rou_text_x[1]

						GOSUB setup_text_roulette 
						IF roulette_flag > 0
							SET_TEXT_COLOUR 134 155 184 255 
							DISPLAY_TEXT rou_text_x[0] text_positionn_y ROU_WON
							text_positionn_y += rou_text_y[1]

							GOSUB setup_text_roulette
							SET_TEXT_SCALE rou_x_scale[1] rou_y_scale[1] 
							DISPLAY_TEXT_WITH_NUMBER rou_text_x[0] text_positionn_y DOLLAR winnings_this_round 
						ELSE
							SET_TEXT_COLOUR 134 155 184 255 
							DISPLAY_TEXT rou_text_x[0] text_positionn_y ROUTHIS
							text_positionn_y += rou_text_y[1]

							GOSUB setup_text_roulette
							SET_TEXT_SCALE rou_x_scale[1] rou_y_scale[1] 
							DISPLAY_TEXT_WITH_NUMBER rou_text_x[0] text_positionn_y DOLLAR spot_bet 
						ENDIF


						IF roulette_flag = 0
							PRINT_HELP_FOREVER ROUHELP
							SET_OBJECT_VISIBLE chips_stack true
							
							IF IS_BUTTON_PRESSED PAD1 CROSS
								IF pad1_cross_pressed = 0
									if total_stake_this_round > 0
										load_MISSION_AUDIO 4 SOUND_BANK_ROULETTE
										if not is_char_dead croupier
											generate_random_int_in_range 0 3 an
											if roulette_model = VBFYCRP
												SET_CHAR_SAY_SCRIPT croupier no_more_bets_b[an] true true false
											else
												SET_CHAR_SAY_SCRIPT croupier no_more_bets_w[an] true true false
											endif
										endif
										if has_mission_audio_loaded	4
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_ROULETTE_SPIN 
										endif
										increment_int_stat MONEY_SPENT_GAMBLING total_stake_this_round
										temp_float_1 =# total_stake_this_round
										temp_float_1 *= 0.001
										CLEAR_HELP
										increment_float_stat GAMBLING temp_float_1
										++ roulette_flag
									else
										if has_mission_audio_loaded	4
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_ROULETTE_NO_CASH 
										endif
									endif
									++ pad1_cross_pressed
								ENDIF
							ELSE
								pad1_cross_pressed = 0
							ENDIF
							
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								IF pad1_triangle_pressed = 0
									++ pad1_triangle_pressed
									roulette_flag = 0
									flag = 3
										
									if not is_char_dead croupier
										generate_random_int_in_range 0 2 an
										if roulette_model = VBFYCRP
											SET_CHAR_SAY_SCRIPT croupier player_leaves_b[an] true true false
										else
											SET_CHAR_SAY_SCRIPT croupier player_leaves_w[an] true true false
										endif
									endif
									
									DISPLAY_RADAR TRUE
									SET_PLAYER_CONTROL player1 ON
									SET_EVERYONE_IGNORE_PLAYER player1 FALSE
									SET_ALL_CARS_CAN_BE_DAMAGED TRUE
									SET_CAMERA_BEHIND_PLAYER
									RESTORE_CAMERA_JUMPCUT
									USE_TEXT_COMMANDS FALSE
									REMOVE_TEXTURE_DICTIONARY
									clear_help

									DELETE_OBJECT chips_stack

									add_score player1 total_stake_this_round
									
									reset_all_bets_flag = 1
									GOSUB reset_all_bets
									i = 0//CLEAR TABLE OF CHIPS
									WHILE i < 151
										DELETE_OBJECT placed_chips[i]
										++ i
									ENDWHILE
									
									SET_MINIGAME_IN_PROGRESS false
									roulette_triggered = 0
									
									GOTO roulette_script_loop
								ENDIF
							ELSE
								pad1_triangle_pressed = 0
							ENDIF

							GOSUB check_bet_on_selection
						ENDIF
						
						IF roulette_flag = 1
							SET_OBJECT_VISIBLE chips_stack FALSE
							wheel_rotation_speed +=@ 0.5
							IF wheel_rotation_speed > 20.0
							OR wheel_rotation_speed = 20.0
								wheel_rotation_timer = game_timer + 1500
								++ roulette_flag
							ENDIF
						ENDIF

						IF roulette_flag = 2
							IF wheel_rotation_timer < game_timer
								wheel_rotation_speed -=@ 0.08
								IF wheel_rotation_speed < 0.0
								OR wheel_rotation_speed = 0.0
									GENERATE_RANDOM_INT_IN_RANGE 0 37 spin_result
									
									if not is_char_dead croupier
										if roulette_model = VBFYCRP
											SET_CHAR_SAY_SCRIPT croupier roulette_result_b[spin_result] true true false
										else
											SET_CHAR_SAY_SCRIPT croupier roulette_result_w[spin_result] true true false
										endif
									endif
									
									GOSUB check_and_add_winnings
									
									++ roulette_flag
								ENDIF
							ENDIF
						ENDIF

						IF roulette_flag = 3
							PRINT_HELP_FOREVER X_CONT 
							temp_integer_1 = winnings_this_round - total_stake_this_round
							if temp_integer_1 = 0
								GOSUB setup_text_roulette
								SET_TEXT_SCALE 1.3 3.36//1.68
								SET_TEXT_CENTRE ON
								SET_TEXT_EDGE 2 0 0 0 255
								set_text_font font_heading
								DISPLAY_TEXT 320.0 180.333 NOWIN
							else
								if temp_integer_1 > 0
									GOSUB setup_text_roulette
									SET_TEXT_SCALE 1.3 3.36//1.68
									SET_TEXT_CENTRE ON
									SET_TEXT_EDGE 2 0 0 0 255
									set_text_font font_heading
									GET_HUD_COLOUR HUD_COLOUR_YELLOW temp_integer_2 temp_integer_3 temp_integer_4 an
									set_text_colour	temp_integer_2 temp_integer_3 temp_integer_4 255
									DISPLAY_TEXT_with_number 320.0 155.333 WINNER temp_integer_1
									REGISTER_INT_STAT BIGGEST_GAMBLING_WIN temp_integer_1
								else
									temp_integer_1 *= -1
									GOSUB setup_text_roulette
									SET_TEXT_SCALE 1.3 3.36//1.68
									SET_TEXT_CENTRE ON
									SET_TEXT_EDGE 2 0 0 0 255
									set_text_font font_heading
									DISPLAY_TEXT_with_number 320.0 155.333 LOSER temp_integer_1
									REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS temp_integer_1
								endif
							endif

							IF IS_BUTTON_PRESSED PAD1 CROSS
								IF pad1_cross_pressed = 0
									pad1_cross_pressed = game_timer + 999999
									ADD_SCORE player1 winnings_this_round
									increment_int_stat MONEY_WON_GAMBLING winnings_this_round
									temp_integer_1 = winnings_this_round - total_stake_this_round
									++ winning_streak
									if temp_integer_1 > 0
										generate_random_int_in_range 0 3 an
										if an = 0
										or an = 1
											if winning_streak > 1
												generate_random_int_in_range 3 6 an
											else
												generate_random_int_in_range 0 3 an
											endif
											if not is_char_dead croupier
												if roulette_model = VBFYCRP
													SET_CHAR_SAY_SCRIPT croupier player_wins_b[an] true true false
												else
													SET_CHAR_SAY_SCRIPT croupier player_wins_w[an] true true false
												endif
											endif
										else
											SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_GAMB_CASINO_WIN temp_integer_4
										endif
									else
										generate_random_int_in_range 0 2 temp_integer_2
										if temp_integer_2 = 0
											SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_GAMB_CASINO_LOSE temp_integer_4
										endif
										winning_streak = 0
									endif
									reset_all_bets_flag = 1
									GOSUB reset_all_bets
									
									i = 0//CLEAR TABLE OF CHIPS
									WHILE i < 151
										DELETE_OBJECT placed_chips[i]
										++ i
									ENDWHILE
									
									roulette_flag = 0
								ENDIF
							ELSE
								pad1_cross_pressed = 0
							ENDIF
						ENDIF

						IF roulette_flag > 0
						AND	roulette_flag < 3
							wheel_rotation +=@ wheel_rotation_speed
							SET_OBJECT_ROTATION roulette_wheel 0.0 0.0 wheel_rotation
							GENERATE_RANDOM_INT_IN_RANGE 0 37 spin_result
						ENDIF

						VAR_INT pad1_left_stick_x pad1_left_stick_y pad1_right_stick_x pad1_right_stick_y
						GET_POSITION_OF_ANALOGUE_STICKS PAD1 pad1_left_stick_x pad1_left_stick_y pad1_right_stick_x pad1_right_stick_y
						
						// If we are still setting up the bet, grab input that moves the betting placement around.

						IF IS_BUTTON_PRESSED PAD1 DPADLEFT
						OR pad1_left_stick_x < -80
							IF pad1_left_pressed = 0
								dpadleft_timer = game_timer + 80
								IF spot_selection_x < 0.0
									IF NOT spot_selection_y = 2.0//AT THE ZERO SPOT
										IF NOT spot_selection_x	= 0.0//TOP ROW NUMBER SPOTS
											IF spot_selection_y	= -24.0// 3/1 ODD STRIP SPOT
												spot_selection_x += 2.0
											ELSE
												spot_selection_x += 1.0
											ENDIF
										ENDIF
									ENDIF
								ELSE
									IF spot_selection_x = 0.0//LEFT HAND ROW OF NUMBERS
										IF spot_selection_y < 2.0
										AND spot_selection_y > -7.0
											//MOVE TO CENTRE OF 1ST 12 FIELD
											spot_selection_x += 2.0
											spot_selection_y = -3.0
										ELSE
											IF spot_selection_y < -6.0
											AND spot_selection_y > -15.0
												//MOVE TO CENTRE OF 2ND 12 FIELD
												spot_selection_x += 2.0
												spot_selection_y = -11.0
											ELSE
												IF spot_selection_y < -14.0
												AND spot_selection_y > -24.0
													//MOVE TO CENTRE OF 3RD 12 FIELD
													spot_selection_x += 2.0
													spot_selection_y = -19.0
												ENDIF
											ENDIF
										ENDIF
									ELSE
										IF spot_selection_x = 2.0//ARE THEY IN 1ST/2ND/3RD 12 FIELDS
											//MOVE TO CLOSE EVEN ODDS BET FIELD
											spot_selection_x += 2.0
											spot_selection_y += 2.0
										ENDIF
									ENDIF
								ENDIF
								++ pad1_left_pressed
							ELSE
								IF dpadleft_timer < game_timer
									pad1_left_pressed = 0
								ENDIF
							ENDIF
						ELSE
							pad1_left_pressed = 0
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
						OR pad1_left_stick_x > 80
							IF pad1_right_pressed = 0
								dpadright_timer = game_timer + 80
								IF spot_selection_x > -4.0
									IF NOT spot_selection_y = 2.0//AT THE ZERO SPOT
										IF NOT spot_selection_x	= -4.0
											IF spot_selection_y	= -24.0// 3/1 ODD STRIP SPOT
											OR spot_selection_x = 2.0//ARE THEY IN 2/1 ODDS BET FIELDS
											OR spot_selection_x = 4.0//ARE THEY IN EVEN ODDS BET FIELDS
												IF spot_selection_y = -1.0//ARE THEY IN TOP EVEN ODDS BET FIELDS
												OR spot_selection_y = -5.0
													spot_selection_y = -3.0
												ENDIF
												IF spot_selection_y = -9.0//ARE THEY IN MIDDLE EVEN ODDS BET FIELDS
												OR spot_selection_y = -13.0
													spot_selection_y = -11.0
												ENDIF
												IF spot_selection_y = -17.0//ARE THEY IN BOTTOM EVEN ODDS BET FIELDS
												OR spot_selection_y = -21.0
													spot_selection_y = -19.0
												ENDIF
												spot_selection_x += -2.0
											ELSE
												spot_selection_x += -1.0
											ENDIF
										ENDIF
									ENDIF
								ELSE
									IF spot_selection_x = -4.0
										IF spot_selection_y	> -23.0
										AND spot_selection_y < 1.0
											spot_selection_x += -1.0
										ENDIF
									ENDIF
								ENDIF
								++ pad1_right_pressed
							ELSE
								IF dpadright_timer < game_timer
									pad1_right_pressed = 0
								ENDIF
							ENDIF
						ELSE
							pad1_right_pressed = 0
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 DPADUP
						OR pad1_left_stick_y < -80  
							IF pad1_up_pressed = 0
								dpadup_timer = game_timer + 80
								IF spot_selection_y < 0.0//ARE CHIPS ON THE NUMBERS	- NOT ABOVE TOP LINE
									IF spot_selection_y = -24.0// 3/1 ODD STRIP SPOT
										spot_selection_y += 2.0
									ELSE
										IF spot_selection_x = 2.0//ARE THEY IN 2/1 ODDS BET FIELDS
											IF NOT spot_selection_y = -3.0//THE TOP
												spot_selection_y += 8.0
											ENDIF
										ELSE
											IF spot_selection_x = 4.0//ARE THEY IN EVEN ODDS BET FIELDS
												IF NOT spot_selection_y = -1.0//THE TOP
													spot_selection_y += 4.0
												ENDIF
											ELSE
												spot_selection_y += 1.0
											ENDIF
										ENDIF
									ENDIF
								ELSE
									//IS POINTER ON THE TOP ROW OF NUMBERS?
									IF spot_selection_y = 0.0
										//MOVE TO CENTRE OF THE 0
										spot_selection_y += 2.0
										spot_selection_x = -2.0
									ENDIF
								ENDIF
								++ pad1_up_pressed
							ELSE
								IF dpadup_timer < game_timer
									pad1_up_pressed = 0
								ENDIF
							ENDIF
						ELSE
							pad1_up_pressed = 0
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 DPADDOWN
						OR pad1_left_stick_y > 80
							IF pad1_down_pressed = 0
								dpaddown_timer = game_timer + 80
								IF spot_selection_y > -22.0//ARE CHIPS ON THE NUMBERS - NOT BELOW BOTTOM LINE
									IF spot_selection_y = 2.0//AT THE ZERO SPOT
										spot_selection_y += -2.0
									ELSE
										IF spot_selection_x = 2.0//ARE THEY IN 2/1 ODDS BET FIELDS
											IF NOT spot_selection_y = -19.0//THE BOTTOM
												spot_selection_y += -8.0
											ENDIF
										ELSE
											IF spot_selection_x = 4.0//ARE THEY IN EVEN ODDS BET FIELDS
												IF NOT spot_selection_y = -21.0//THE BOTTOM
													spot_selection_y += -4.0
												ENDIF
											ELSE
												spot_selection_y += -1.0
											ENDIF
										ENDIF
									ENDIF
								ELSE
									IF spot_selection_x = -4.0//RIGHT HAND LINE OF SPOTS
										IF NOT spot_selection_y = -24.0// 3/1 ODD STRIP SPOT
											spot_selection_y += -2.0
										ENDIF
									ELSE
										IF spot_selection_x = 0.0
											IF NOT spot_selection_y = -24.0// 3/1 ODD STRIP SPOT
												spot_selection_y += -2.0
											ENDIF
										ELSE
											IF NOT spot_selection_y = -24.0// 3/1 ODD STRIP SPOT
												spot_selection_x = -2.0
												spot_selection_y += -2.0
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								++ pad1_down_pressed
							ELSE
								IF dpaddown_timer < game_timer
									pad1_down_pressed = 0
								ENDIF
							ENDIF
						ELSE
							pad1_down_pressed = 0
						ENDIF
						

						spot_increment_x = spot_selection_x	* spot_size_x
						spot_increment_y = spot_selection_y	* spot_size_y
						chip_position_x = spot1_x + spot_increment_x
						chip_position_y = spot1_y + spot_increment_y
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS roulette_table chip_position_x chip_position_y spot1_z x y z
						z += 0.01
						SET_OBJECT_COORDINATES chips_stack x y z
					ENDIF
				ELSE
					IF flag > 0
						IF flag > 3
							CLEAR_HELP
						ENDIF
						delete_char	croupier
						//remove_animation CASINO
						mark_model_as_no_longer_needed roulette_model
						
						reset_all_bets_flag = 1
						GOSUB reset_all_bets
						i = 0//CLEAR TABLE OF CHIPS
						WHILE i < 151
							DELETE_OBJECT placed_chips[i]
							++ i
						ENDWHILE
						DELETE_OBJECT chips_stack
						DELETE_OBJECT roulette_wheel
						
						flag = 0
					ENDIF
				ENDIF//IS_PLAYER_PLAYING
			ELSE
				IF flag > 0
					IF flag > 3
						CLEAR_HELP
					ENDIF
					delete_char	croupier
					//remove_animation CASINO
					mark_model_as_no_longer_needed roulette_model
					
					reset_all_bets_flag = 1
					GOSUB reset_all_bets
					i = 0//CLEAR TABLE OF CHIPS
					WHILE i < 151
						DELETE_OBJECT placed_chips[i]
						++ i
					ENDWHILE
					DELETE_OBJECT chips_stack
					DELETE_OBJECT roulette_wheel
					
					flag = 0
				ENDIF
				-- total_roulette_tables
				TERMINATE_THIS_SCRIPT
			endif//IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE
		ELSE
			IF flag > 0
				IF flag > 3
					CLEAR_HELP
				ENDIF
				delete_char	croupier
				//remove_animation CASINO
				mark_model_as_no_longer_needed roulette_model
				
				reset_all_bets_flag = 1
				GOSUB reset_all_bets
				i = 0//CLEAR TABLE OF CHIPS
				WHILE i < 151
					DELETE_OBJECT placed_chips[i]
					++ i
				ENDWHILE
				DELETE_OBJECT chips_stack
				DELETE_OBJECT roulette_wheel
				
				flag = 0
			ENDIF
			-- total_roulette_tables
			TERMINATE_THIS_SCRIPT
		endif//IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE
	ELSE
		IF flag > 0
			IF flag > 3
				CLEAR_HELP
			ENDIF
			delete_char	croupier
			//remove_animation CASINO
			mark_model_as_no_longer_needed roulette_model
			
			reset_all_bets_flag = 1
			GOSUB reset_all_bets
			i = 0//CLEAR TABLE OF CHIPS
			WHILE i < 151
				DELETE_OBJECT placed_chips[i]
				++ i
			ENDWHILE
			DELETE_OBJECT chips_stack
			DELETE_OBJECT roulette_wheel
			
			flag = 0
		ENDIF
		-- total_roulette_tables
		TERMINATE_THIS_SCRIPT
	ENDIF//DOES_OBJECT_EXIST

GOTO roulette_script_loop




check_bet_on_selection:
i = 0
WHILE i < 151

	IF spot_selection_x = selected_spot_x[i]
		IF spot_selection_y = selected_spot_y[i]
			spot_bet = spot_bets[i]
				
			IF IS_BUTTON_PRESSED PAD1 SQUARE
 				
				IF pad1_square_pressed < game_timer
					
					VAR_INT temp_int_roulette
					STORE_SCORE player1 temp_int_roulette
					temp_int_roulette += 1
					VAR_INT gambling_stat
					GET_INT_STAT GAMBLING gambling_stat
					
					if gambling_stat > 999
					
						temp_integer_4 = chip_value - 1000000
						if temp_int_roulette < 1
							if done_credit_speech = 0
								if not is_char_dead croupier
									generate_random_int_in_range 0 3 an
									if roulette_model = VBFYCRP
										SET_CHAR_SAY_SCRIPT croupier player_gets_credit_b[an] true true false
									else
										SET_CHAR_SAY_SCRIPT croupier player_gets_credit_w[an] true true false
									endif
								endif
								done_credit_speech = 1
							endif
						endif
					else
						if gambling_stat > 100
							temp_integer_4 = chip_value - 100000
							if temp_int_roulette < 1
								if done_credit_speech = 0
									if not is_char_dead croupier
										generate_random_int_in_range 0 3 an
										if roulette_model = VBFYCRP
											SET_CHAR_SAY_SCRIPT croupier player_gets_credit_b[an] true true false
										else
											SET_CHAR_SAY_SCRIPT croupier player_gets_credit_w[an] true true false
										endif
									endif
									done_credit_speech = 1
								endif
							endif
						else
							if gambling_stat > 10
								temp_integer_4 = chip_value - 10000
								if temp_int_roulette < 1
									if done_credit_speech = 0
										if not is_char_dead croupier
											generate_random_int_in_range 0 3 an
											if roulette_model = VBFYCRP
												SET_CHAR_SAY_SCRIPT croupier player_gets_credit_b[an] true true false
											else
												SET_CHAR_SAY_SCRIPT croupier player_gets_credit_w[an] true true false
											endif
										endif
										done_credit_speech = 1
									endif
								endif
							else
								if gambling_stat > 1
									temp_integer_4 = chip_value - 1000
									if temp_int_roulette < 1
										if done_credit_speech = 0
											if not is_char_dead croupier
												generate_random_int_in_range 0 3 an
												if roulette_model = VBFYCRP
													SET_CHAR_SAY_SCRIPT croupier player_gets_credit_b[an] true true false
												else
													SET_CHAR_SAY_SCRIPT croupier player_gets_credit_w[an] true true false
												endif
											endif
											done_credit_speech = 1
										endif
									endif
								else
									temp_integer_4 = chip_value
								endif
							endif
						endif
					endif

					IF total_stake_this_round < max_table_bet
					AND temp_int_roulette > temp_integer_4
						load_MISSION_AUDIO 4 SOUND_BANK_ROULETTE
						if has_mission_audio_loaded	4
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_ROULETTE_ADD_CASH 
						endif
						spot_bet += chip_value
						temp_int_roulette = chip_value * -1
						ADD_SCORE player1 temp_int_roulette
						total_stake_this_round += chip_value
						temp_int_roulette = chip_value * 26
						if spot_bet < temp_int_roulette
							GET_OBJECT_COORDINATES chips_stack x y z
							z += -0.14
							temp_int_roulette = spot_bet / chip_value
							temp_float_1 =# temp_int_roulette
							temp_float_1 *= 0.005 //chip_z_dimension = 0.01 (10 TO A STACK)
							z += temp_float_1

							DELETE_OBJECT placed_chips[i]
							VAR_INT placed_chips[151]
							CREATE_OBJECT_NO_OFFSET coloured_chips x y z placed_chips[i]
						endif
						pad1_square_pressed = game_timer + square_press_timer //time_amount
						if square_press_timer = 500
							square_press_timer = 80
						endif
					else
						if temp_int_roulette <= temp_integer_4
							if not is_char_dead croupier
								generate_random_int_in_range 0 3 an
								if roulette_model = VBFYCRP
									SET_CHAR_SAY_SCRIPT croupier no_money_b[an] true true false
								else
									SET_CHAR_SAY_SCRIPT croupier no_money_w[an] true true false
								endif
							endif
						endif

						load_MISSION_AUDIO 4 SOUND_BANK_ROULETTE
						if has_mission_audio_loaded	4
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_ROULETTE_NO_CASH 
						endif
						pad1_square_pressed = game_timer + 6000000 //time_amount
					ENDIF
				ENDIF
			
			ELSE
				pad1_square_pressed = game_timer//pad1_square_pressed = 0
				square_press_timer = 500
				
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					
					IF pad1_CIRCLE_pressed < game_timer
						
						IF spot_bet > chip_value
						OR spot_bet = chip_value
							
							load_MISSION_AUDIO 4 SOUND_BANK_ROULETTE
							if has_mission_audio_loaded	4
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_ROULETTE_REMOVE_CASH 
							endif
							spot_bet -= chip_value
							total_stake_this_round -= chip_value
							ADD_SCORE player1 chip_value
							
							temp_int_roulette = chip_value * 26
							if spot_bet < temp_int_roulette
								GET_OBJECT_COORDINATES chips_stack x y z
								z += -0.14
								temp_int_roulette = spot_bet / chip_value
								temp_float_1 =# temp_int_roulette
								temp_float_1 *= 0.005 //chip_z_dimension = 0.01 (10 TO A STACK)
								z += temp_float_1

								DELETE_OBJECT placed_chips[i]
								IF spot_bet > 0
									CREATE_OBJECT_NO_OFFSET coloured_chips x y z placed_chips[i]
								ENDIF
							endif
						ENDIF
						pad1_CIRCLE_pressed = game_timer + circle_press_timer
						if circle_press_timer = 500
							circle_press_timer = 80
						endif
					ENDIF
				ELSE
					pad1_CIRCLE_pressed = game_timer
					circle_press_timer = 500
				ENDIF
			ENDIF

			spot_bets[i] = spot_bet
		ENDIF
	ENDIF
	++ i
ENDWHILE
RETURN





check_and_add_winnings:
	IF spin_result = 0
		spot_bets[0] *= 36
		winnings_this_round += spot_bets[0]
		spot_bets[0] = 0
	ENDIF
	IF spin_result = 1
		spot_bets[1] *= 36
		winnings_this_round += spot_bets[1]

		spot_bets[37] *= 18
		winnings_this_round += spot_bets[37]
		
		spot_bets[61] *= 18
		winnings_this_round += spot_bets[61]
		
		spot_bets[94] *= 12
		winnings_this_round += spot_bets[94]
		
		spot_bets[106] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]

		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
	ENDIF
	IF spin_result = 2
		spot_bets[2] *= 36
		winnings_this_round += spot_bets[2]

		spot_bets[37] *= 18
		winnings_this_round += spot_bets[37]
		
		spot_bets[38] *= 18
		winnings_this_round += spot_bets[38]
		
		spot_bets[62] *= 18
		winnings_this_round += spot_bets[62]
		
		spot_bets[94] *= 12
		winnings_this_round += spot_bets[94]
		
		spot_bets[106] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[107] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
	ENDIF
	IF spin_result = 3
		spot_bets[3] *= 36
		winnings_this_round += spot_bets[3]

		spot_bets[38] *= 18
		winnings_this_round += spot_bets[38]
		
		spot_bets[63] *= 18
		winnings_this_round += spot_bets[63]
		
		spot_bets[94] *= 12
		winnings_this_round += spot_bets[94]
		
		spot_bets[107] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
	ENDIF
	IF spin_result = 4
		spot_bets[4] *= 36
		winnings_this_round += spot_bets[4]

		spot_bets[61] *= 18
		winnings_this_round += spot_bets[61]
		
		spot_bets[39] *= 18
		winnings_this_round += spot_bets[39]
		
		spot_bets[64] *= 18
		winnings_this_round += spot_bets[64]
		
		spot_bets[95] *= 12
		winnings_this_round += spot_bets[95]
		
		spot_bets[106] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[108] *= 9
		winnings_this_round += spot_bets[108]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
	ENDIF
	IF spin_result = 5
		spot_bets[5] *= 36
		winnings_this_round += spot_bets[5]

		spot_bets[62] *= 18
		winnings_this_round += spot_bets[62]
		
		spot_bets[39] *= 18
		winnings_this_round += spot_bets[39]
		
		spot_bets[40] *= 18
		winnings_this_round += spot_bets[40]
		
		spot_bets[65] *= 18
		winnings_this_round += spot_bets[65]
		
		spot_bets[95] *= 12
		winnings_this_round += spot_bets[95]
		
		spot_bets[106] *= 9
		winnings_this_round += spot_bets[106]
		
		spot_bets[107] *= 9
		winnings_this_round += spot_bets[107]
		
		spot_bets[108] *= 9
		winnings_this_round += spot_bets[108]
		
		spot_bets[109] *= 9
		winnings_this_round += spot_bets[109]

		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
	ENDIF
	IF spin_result = 6
		spot_bets[6] *= 36
		winnings_this_round += spot_bets[6]

		spot_bets[63] *= 18
		winnings_this_round += spot_bets[63]
		
		spot_bets[40] *= 18
		winnings_this_round += spot_bets[40]
		
		spot_bets[66] *= 18
		winnings_this_round += spot_bets[66]
		
		spot_bets[95] *= 12
		winnings_this_round += spot_bets[95]
		
		spot_bets[107] *= 9
		winnings_this_round += spot_bets[107]
		
		spot_bets[109] *= 9
		winnings_this_round += spot_bets[109]

		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[140] *= 6
		winnings_this_round += spot_bets[140]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
	ENDIF
	IF spin_result = 7
		spot_bets[7] *= 36
		winnings_this_round += spot_bets[7]

		spot_bets[64] *= 18
		winnings_this_round += spot_bets[64]
		
		spot_bets[41] *= 18
		winnings_this_round += spot_bets[41]
		
		spot_bets[67] *= 18
		winnings_this_round += spot_bets[67]
		
		spot_bets[96] *= 12
		winnings_this_round += spot_bets[96]
		
		spot_bets[108] *= 9
		winnings_this_round += spot_bets[108]
		
		spot_bets[110] *= 9
		winnings_this_round += spot_bets[110]

		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
	ENDIF
	IF spin_result = 8
		spot_bets[8] *= 36
		winnings_this_round += spot_bets[8]

		spot_bets[65] *= 18
		winnings_this_round += spot_bets[65]
		
		spot_bets[41] *= 18
		winnings_this_round += spot_bets[41]
		
		spot_bets[42] *= 18
		winnings_this_round += spot_bets[42]
		
		spot_bets[68] *= 18
		winnings_this_round += spot_bets[68]
		
		spot_bets[96] *= 12
		winnings_this_round += spot_bets[96]
		
		spot_bets[108] *= 9
		winnings_this_round += spot_bets[108]
		
		spot_bets[109] *= 9
		winnings_this_round += spot_bets[109]

		spot_bets[110] *= 9
		winnings_this_round += spot_bets[110]

		spot_bets[111] *= 9
		winnings_this_round += spot_bets[111]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
	ENDIF
	IF spin_result = 9
		spot_bets[9] *= 36
		winnings_this_round += spot_bets[9]

		spot_bets[66] *= 18
		winnings_this_round += spot_bets[66]
		
		spot_bets[42] *= 18
		winnings_this_round += spot_bets[42]
		
		spot_bets[69] *= 18
		winnings_this_round += spot_bets[69]
		
		spot_bets[96] *= 12
		winnings_this_round += spot_bets[96]
		
		spot_bets[109] *= 9
		winnings_this_round += spot_bets[109]
		
		spot_bets[111] *= 9
		winnings_this_round += spot_bets[111]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[141] *= 6
		winnings_this_round += spot_bets[141]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
	ENDIF
	IF spin_result = 10
		spot_bets[10] *= 36
		winnings_this_round += spot_bets[10]

		spot_bets[67] *= 18
		winnings_this_round += spot_bets[67]
		
		spot_bets[43] *= 18
		winnings_this_round += spot_bets[43]
		
		spot_bets[70] *= 18
		winnings_this_round += spot_bets[70]
		
		spot_bets[97] *= 12
		winnings_this_round += spot_bets[97]
		
		spot_bets[110] *= 9
		winnings_this_round += spot_bets[110]
		
		spot_bets[112] *= 9
		winnings_this_round += spot_bets[112]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
	ENDIF
	IF spin_result = 11
		spot_bets[11] *= 36
		winnings_this_round += spot_bets[11]

		spot_bets[68] *= 18
		winnings_this_round += spot_bets[68]
		
		spot_bets[43] *= 18
		winnings_this_round += spot_bets[43]
		
		spot_bets[44] *= 18
		winnings_this_round += spot_bets[44]
		
		spot_bets[71] *= 18
		winnings_this_round += spot_bets[71]
		
		spot_bets[97] *= 12
		winnings_this_round += spot_bets[97]
		
		spot_bets[110] *= 9
		winnings_this_round += spot_bets[110]
		
		spot_bets[111] *= 9
		winnings_this_round += spot_bets[111]
		
		spot_bets[112] *= 9
		winnings_this_round += spot_bets[112]
		
		spot_bets[113] *= 9
		winnings_this_round += spot_bets[113]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
	ENDIF
	IF spin_result = 12
		spot_bets[12] *= 36
		winnings_this_round += spot_bets[12]

		spot_bets[69] *= 18
		winnings_this_round += spot_bets[69]
		
		spot_bets[44] *= 18
		winnings_this_round += spot_bets[44]
		
		spot_bets[72] *= 18
		winnings_this_round += spot_bets[72]
		
		spot_bets[97] *= 12
		winnings_this_round += spot_bets[97]
		
		spot_bets[111] *= 9
		winnings_this_round += spot_bets[111]
		
		spot_bets[113] *= 9
		winnings_this_round += spot_bets[113]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[131]	*= 3
		winnings_this_round += spot_bets[131]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[142] *= 6
		winnings_this_round += spot_bets[142]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
	ENDIF
	IF spin_result = 13
		spot_bets[13] *= 36
		winnings_this_round += spot_bets[13]

		spot_bets[70] *= 18
		winnings_this_round += spot_bets[70]
		
		spot_bets[45] *= 18
		winnings_this_round += spot_bets[45]
		
		spot_bets[73] *= 18
		winnings_this_round += spot_bets[73]
		
		spot_bets[98] *= 12
		winnings_this_round += spot_bets[98]
		
		spot_bets[112] *= 9
		winnings_this_round += spot_bets[112]
		
		spot_bets[114] *= 9
		winnings_this_round += spot_bets[114]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
	ENDIF
	IF spin_result = 14
		spot_bets[14] *= 36
		winnings_this_round += spot_bets[14]

		spot_bets[71] *= 18
		winnings_this_round += spot_bets[71]
		
		spot_bets[45] *= 18
		winnings_this_round += spot_bets[45]
		
		spot_bets[46] *= 18
		winnings_this_round += spot_bets[46]
		
		spot_bets[74] *= 18
		winnings_this_round += spot_bets[74]
		
		spot_bets[98] *= 12
		winnings_this_round += spot_bets[98]
		
		spot_bets[112] *= 9
		winnings_this_round += spot_bets[112]
		
		spot_bets[113] *= 9
		winnings_this_round += spot_bets[113]
		
		spot_bets[114] *= 9
		winnings_this_round += spot_bets[114]
		
		spot_bets[115] *= 9
		winnings_this_round += spot_bets[115]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
	ENDIF
	IF spin_result = 15
		spot_bets[15] *= 36
		winnings_this_round += spot_bets[15]

		spot_bets[72] *= 18
		winnings_this_round += spot_bets[72]
		
		spot_bets[46] *= 18
		winnings_this_round += spot_bets[46]
		
		spot_bets[75] *= 18
		winnings_this_round += spot_bets[75]
		
		spot_bets[98] *= 12
		winnings_this_round += spot_bets[98]
		
		spot_bets[113] *= 9
		winnings_this_round += spot_bets[113]
		
		spot_bets[115] *= 9
		winnings_this_round += spot_bets[115]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[143] *= 6
		winnings_this_round += spot_bets[143]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
	ENDIF
	IF spin_result = 16
		spot_bets[16] *= 36
		winnings_this_round += spot_bets[16]

		spot_bets[73] *= 18
		winnings_this_round += spot_bets[73]
		
		spot_bets[47] *= 18
		winnings_this_round += spot_bets[47]
		
		spot_bets[76] *= 18
		winnings_this_round += spot_bets[76]
		
		spot_bets[99] *= 12
		winnings_this_round += spot_bets[99]
		
		spot_bets[114] *= 9
		winnings_this_round += spot_bets[114]
		
		spot_bets[116] *= 9
		winnings_this_round += spot_bets[116]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
	ENDIF
	IF spin_result = 17
		spot_bets[17] *= 36
		winnings_this_round += spot_bets[17]

		spot_bets[74] *= 18
		winnings_this_round += spot_bets[74]
		
		spot_bets[47] *= 18
		winnings_this_round += spot_bets[47]
		
		spot_bets[48] *= 18
		winnings_this_round += spot_bets[48]
		
		spot_bets[77] *= 18
		winnings_this_round += spot_bets[77]
		
		spot_bets[99] *= 12
		winnings_this_round += spot_bets[99]
		
		spot_bets[114] *= 9
		winnings_this_round += spot_bets[114]
		
		spot_bets[115] *= 9
		winnings_this_round += spot_bets[115]
		
		spot_bets[116] *= 9
		winnings_this_round += spot_bets[116]
		
		spot_bets[117] *= 9
		winnings_this_round += spot_bets[117]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
	ENDIF
	IF spin_result = 18
		spot_bets[18] *= 36
		winnings_this_round += spot_bets[18]

		spot_bets[75] *= 18
		winnings_this_round += spot_bets[75]
		
		spot_bets[48] *= 18
		winnings_this_round += spot_bets[48]
		
		spot_bets[78] *= 18
		winnings_this_round += spot_bets[78]
		
		spot_bets[99] *= 12
		winnings_this_round += spot_bets[99]
		
		spot_bets[115] *= 9
		winnings_this_round += spot_bets[115]
		
		spot_bets[117] *= 9
		winnings_this_round += spot_bets[117]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[134] *= 2
		winnings_this_round += spot_bets[134]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[144] *= 6
		winnings_this_round += spot_bets[144]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
	ENDIF
	IF spin_result = 19
		spot_bets[19] *= 36
		winnings_this_round += spot_bets[19]

		spot_bets[76] *= 18
		winnings_this_round += spot_bets[76]
		
		spot_bets[49] *= 18
		winnings_this_round += spot_bets[49]
		
		spot_bets[79] *= 18
		winnings_this_round += spot_bets[79]
		
		spot_bets[100] *= 12
		winnings_this_round += spot_bets[100]
		
		spot_bets[116] *= 9
		winnings_this_round += spot_bets[116]
		
		spot_bets[118] *= 9
		winnings_this_round += spot_bets[118]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
	ENDIF
	IF spin_result = 20
		spot_bets[20] *= 36
		winnings_this_round += spot_bets[20]

		spot_bets[77] *= 18
		winnings_this_round += spot_bets[77]
		
		spot_bets[49] *= 18
		winnings_this_round += spot_bets[49]
		
		spot_bets[50] *= 18
		winnings_this_round += spot_bets[50]
		
		spot_bets[80] *= 18
		winnings_this_round += spot_bets[80]
		
		spot_bets[100] *= 12
		winnings_this_round += spot_bets[100]
		
		spot_bets[116] *= 9
		winnings_this_round += spot_bets[116]
		
		spot_bets[117] *= 9
		winnings_this_round += spot_bets[117]
		
		spot_bets[118] *= 9
		winnings_this_round += spot_bets[118]
		
		spot_bets[119] *= 9
		winnings_this_round += spot_bets[119]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
	ENDIF
	IF spin_result = 21
		spot_bets[21] *= 36
		winnings_this_round += spot_bets[21]

		spot_bets[78] *= 18
		winnings_this_round += spot_bets[78]
		
		spot_bets[50] *= 18
		winnings_this_round += spot_bets[50]
		
		spot_bets[81] *= 18
		winnings_this_round += spot_bets[81]
		
		spot_bets[100] *= 12
		winnings_this_round += spot_bets[100]
		
		spot_bets[117] *= 9
		winnings_this_round += spot_bets[117]
		
		spot_bets[119] *= 9
		winnings_this_round += spot_bets[119]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[145] *= 6
		winnings_this_round += spot_bets[145]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
	ENDIF
	IF spin_result = 22
		spot_bets[22] *= 36
		winnings_this_round += spot_bets[22]

		spot_bets[79] *= 18
		winnings_this_round += spot_bets[79]
		
		spot_bets[51] *= 18
		winnings_this_round += spot_bets[51]
		
		spot_bets[82] *= 18
		winnings_this_round += spot_bets[82]
		
		spot_bets[101] *= 12
		winnings_this_round += spot_bets[101]
		
		spot_bets[118] *= 9
		winnings_this_round += spot_bets[118]
		
		spot_bets[120] *= 9
		winnings_this_round += spot_bets[120]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
	ENDIF
	IF spin_result = 23
		spot_bets[23] *= 36
		winnings_this_round += spot_bets[23]

		spot_bets[80] *= 18
		winnings_this_round += spot_bets[80]
		
		spot_bets[51] *= 18
		winnings_this_round += spot_bets[51]
		
		spot_bets[52] *= 18
		winnings_this_round += spot_bets[52]
		
		spot_bets[83] *= 18
		winnings_this_round += spot_bets[83]
		
		spot_bets[101] *= 12
		winnings_this_round += spot_bets[101]
		
		spot_bets[118] *= 9
		winnings_this_round += spot_bets[118]
		
		spot_bets[119] *= 9
		winnings_this_round += spot_bets[119]
		
		spot_bets[120] *= 9
		winnings_this_round += spot_bets[120]
		
		spot_bets[121] *= 9
		winnings_this_round += spot_bets[121]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
	ENDIF
	IF spin_result = 24
		spot_bets[24] *= 36
		winnings_this_round += spot_bets[24]

		spot_bets[81] *= 18
		winnings_this_round += spot_bets[81]
		
		spot_bets[52] *= 18
		winnings_this_round += spot_bets[52]
		
		spot_bets[84] *= 18
		winnings_this_round += spot_bets[84]
		
		spot_bets[101] *= 12
		winnings_this_round += spot_bets[101]
		
		spot_bets[119] *= 9
		winnings_this_round += spot_bets[119]
		
		spot_bets[121] *= 9
		winnings_this_round += spot_bets[121]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[132]	*= 3
		winnings_this_round += spot_bets[132]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[146] *= 6
		winnings_this_round += spot_bets[146]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
	ENDIF
	IF spin_result = 25
		spot_bets[25] *= 36
		winnings_this_round += spot_bets[25]

		spot_bets[82] *= 18
		winnings_this_round += spot_bets[82]
		
		spot_bets[53] *= 18
		winnings_this_round += spot_bets[53]
		
		spot_bets[85] *= 18
		winnings_this_round += spot_bets[85]
		
		spot_bets[102] *= 12
		winnings_this_round += spot_bets[102]
		
		spot_bets[120] *= 9
		winnings_this_round += spot_bets[120]
		
		spot_bets[122] *= 9
		winnings_this_round += spot_bets[122]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
	ENDIF
	IF spin_result = 26
		spot_bets[26] *= 36
		winnings_this_round += spot_bets[26]

		spot_bets[83] *= 18
		winnings_this_round += spot_bets[83]
		
		spot_bets[53] *= 18
		winnings_this_round += spot_bets[53]
		
		spot_bets[54] *= 18
		winnings_this_round += spot_bets[54]
		
		spot_bets[86] *= 18
		winnings_this_round += spot_bets[86]
		
		spot_bets[102] *= 12
		winnings_this_round += spot_bets[102]
		
		spot_bets[120] *= 9
		winnings_this_round += spot_bets[120]
		
		spot_bets[121] *= 9
		winnings_this_round += spot_bets[121]
		
		spot_bets[122] *= 9
		winnings_this_round += spot_bets[122]
		
		spot_bets[123] *= 9
		winnings_this_round += spot_bets[123]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
	ENDIF
	IF spin_result = 27
		spot_bets[27] *= 36
		winnings_this_round += spot_bets[27]

		spot_bets[84] *= 18
		winnings_this_round += spot_bets[84]
		
		spot_bets[54] *= 18
		winnings_this_round += spot_bets[54]
		
		spot_bets[87] *= 18
		winnings_this_round += spot_bets[87]
		
		spot_bets[102] *= 12
		winnings_this_round += spot_bets[102]
		
		spot_bets[121] *= 9
		winnings_this_round += spot_bets[121]
		
		spot_bets[123] *= 9
		winnings_this_round += spot_bets[123]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[147] *= 6
		winnings_this_round += spot_bets[147]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
	ENDIF
	IF spin_result = 28
		spot_bets[28] *= 36
		winnings_this_round += spot_bets[28]

		spot_bets[85] *= 18
		winnings_this_round += spot_bets[85]
		
		spot_bets[55] *= 18
		winnings_this_round += spot_bets[55]
		
		spot_bets[88] *= 18
		winnings_this_round += spot_bets[88]
		
		spot_bets[103] *= 12
		winnings_this_round += spot_bets[103]
		
		spot_bets[122] *= 9
		winnings_this_round += spot_bets[122]
		
		spot_bets[124] *= 9
		winnings_this_round += spot_bets[124]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
	ENDIF
	IF spin_result = 29
		spot_bets[29] *= 36
		winnings_this_round += spot_bets[29]

		spot_bets[86] *= 18
		winnings_this_round += spot_bets[86]
		
		spot_bets[55] *= 18
		winnings_this_round += spot_bets[55]
		
		spot_bets[56] *= 18
		winnings_this_round += spot_bets[56]
		
		spot_bets[89] *= 18
		winnings_this_round += spot_bets[89]
		
		spot_bets[103] *= 12
		winnings_this_round += spot_bets[103]
		
		spot_bets[122] *= 9
		winnings_this_round += spot_bets[122]
		
		spot_bets[123] *= 9
		winnings_this_round += spot_bets[123]
		
		spot_bets[124] *= 9
		winnings_this_round += spot_bets[124]
		
		spot_bets[125] *= 9
		winnings_this_round += spot_bets[125]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
	ENDIF
	IF spin_result = 30
		spot_bets[30] *= 36
		winnings_this_round += spot_bets[30]

		spot_bets[87] *= 18
		winnings_this_round += spot_bets[87]
		
		spot_bets[56] *= 18
		winnings_this_round += spot_bets[56]
		
		spot_bets[90] *= 18
		winnings_this_round += spot_bets[90]
		
		spot_bets[103] *= 12
		winnings_this_round += spot_bets[103]
		
		spot_bets[123] *= 9
		winnings_this_round += spot_bets[123]
		
		spot_bets[125] *= 9
		winnings_this_round += spot_bets[125]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[148] *= 6
		winnings_this_round += spot_bets[148]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
	ENDIF
	IF spin_result = 31
		spot_bets[31] *= 36
		winnings_this_round += spot_bets[31]

		spot_bets[88] *= 18
		winnings_this_round += spot_bets[88]
		
		spot_bets[57] *= 18
		winnings_this_round += spot_bets[57]
		
		spot_bets[91] *= 18
		winnings_this_round += spot_bets[91]
		
		spot_bets[104] *= 12
		winnings_this_round += spot_bets[104]
		
		spot_bets[124] *= 9
		winnings_this_round += spot_bets[124]
		
		spot_bets[126] *= 9
		winnings_this_round += spot_bets[126]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF
	IF spin_result = 32
		spot_bets[32] *= 36
		winnings_this_round += spot_bets[32]

		spot_bets[89] *= 18
		winnings_this_round += spot_bets[89]
		
		spot_bets[57] *= 18
		winnings_this_round += spot_bets[57]
		
		spot_bets[58] *= 18
		winnings_this_round += spot_bets[58]
		
		spot_bets[92] *= 18
		winnings_this_round += spot_bets[92]
		
		spot_bets[104] *= 12
		winnings_this_round += spot_bets[104]
		
		spot_bets[124] *= 9
		winnings_this_round += spot_bets[124]
		
		spot_bets[125] *= 9
		winnings_this_round += spot_bets[125]
		
		spot_bets[126] *= 9
		winnings_this_round += spot_bets[126]
		
		spot_bets[127] *= 9
		winnings_this_round += spot_bets[127]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF
	IF spin_result = 33
		spot_bets[33] *= 36
		winnings_this_round += spot_bets[33]

		spot_bets[90] *= 18
		winnings_this_round += spot_bets[90]
		
		spot_bets[58] *= 18
		winnings_this_round += spot_bets[58]
		
		spot_bets[93] *= 18
		winnings_this_round += spot_bets[93]
		
		spot_bets[104] *= 12
		winnings_this_round += spot_bets[104]
		
		spot_bets[125] *= 9
		winnings_this_round += spot_bets[125]
		
		spot_bets[127] *= 9
		winnings_this_round += spot_bets[127]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[149] *= 6
		winnings_this_round += spot_bets[149]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF
	IF spin_result = 34
		spot_bets[34] *= 36
		winnings_this_round += spot_bets[34]

		spot_bets[91] *= 18
		winnings_this_round += spot_bets[91]
		
		spot_bets[59] *= 18
		winnings_this_round += spot_bets[59]
		
		spot_bets[105] *= 12
		winnings_this_round += spot_bets[105]
		
		spot_bets[126] *= 9
		winnings_this_round += spot_bets[126]
		
		spot_bets[128] *= 3
		winnings_this_round += spot_bets[128]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF
	IF spin_result = 35
		spot_bets[35] *= 36
		winnings_this_round += spot_bets[35]

		spot_bets[92] *= 18
		winnings_this_round += spot_bets[92]
		
		spot_bets[59] *= 18
		winnings_this_round += spot_bets[59]
		
		spot_bets[60] *= 18
		winnings_this_round += spot_bets[60]
		
		spot_bets[105] *= 12
		winnings_this_round += spot_bets[105]
		
		spot_bets[126] *= 9
		winnings_this_round += spot_bets[126]
		
		spot_bets[127] *= 9
		winnings_this_round += spot_bets[127]
		
		spot_bets[129] *= 3
		winnings_this_round += spot_bets[129]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[137] *= 2
		winnings_this_round += spot_bets[137]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[139] *= 2
		winnings_this_round += spot_bets[139]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF
	IF spin_result = 36
		spot_bets[36] *= 36
		winnings_this_round += spot_bets[36]

		spot_bets[93] *= 18
		winnings_this_round += spot_bets[93]
		
		spot_bets[60] *= 18
		winnings_this_round += spot_bets[60]
		
		spot_bets[105] *= 12
		winnings_this_round += spot_bets[105]
		
		spot_bets[127] *= 9
		winnings_this_round += spot_bets[127]
		
		spot_bets[130] *= 3
		winnings_this_round += spot_bets[130]
		
		spot_bets[133]	*= 3
		winnings_this_round += spot_bets[133]
		
		spot_bets[136] *= 2
		winnings_this_round += spot_bets[136]
		
		spot_bets[135] *= 2
		winnings_this_round += spot_bets[135]
		
		spot_bets[138] *= 2
		winnings_this_round += spot_bets[138]
		
		spot_bets[150] *= 6
		winnings_this_round += spot_bets[150]
	ENDIF

RETURN


setup_text_roulette:
	SET_TEXT_COLOUR 180 180 180 255
	SET_TEXT_SCALE rou_x_scale[0] rou_y_scale[0]
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
RETURN

}

MISSION_END
