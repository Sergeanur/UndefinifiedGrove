MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION MISSNAM : BLACK JACK 
//				  AUTHOR :
//			DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************


// EACH CARD HAS AN INTEGER VALUE:
//
//		CLUBS		DIAMONDS		SPADES			HEARTS
//		-----		--------		------			------
//		A = 1		A = 14			A = 27			A = 40
//		2 = 2		2 = 15			2 = 28			2 =	41
//		3 = 3		3 = 16			3 = 29			3 = 42
//		4 = 4		4 = 17			4 = 30			4 = 43
//		5 = 5		5 = 18			5 = 31			5 = 44
//		6 = 6		6 = 19			6 = 32			6 = 45
//		7 = 7		7 = 20			7 = 33			7 = 46
//		8 = 8		8 = 21			8 = 34			8 = 47
//		9 = 9		9 = 22			9 = 35			9 = 48
//	   10 = 10	   10 = 23		   10 = 36		   10 = 49
//		J = 11		J = 24			J = 37			J = 50
//		Q = 12		Q = 25			Q = 38			Q = 51
//		K = 13		K = 26			K = 39			K = 52



// GLOBALS
VAR_FLOAT dealer_sprite_x[8]
VAR_FLOAT dealer_sprite_y[8]
VAR_FLOAT player_sprite1_x[8]
VAR_FLOAT player_sprite1_y[8]
VAR_FLOAT player_sprite1b_x[8]
VAR_FLOAT player_sprite1b_y[8]
VAR_FLOAT player_sprite2_x[8]
VAR_FLOAT player_sprite2_y[8]
VAR_FLOAT card_width
VAR_FLOAT card_height
VAR_FLOAT spacing_x
VAR_FLOAT border_width
VAR_FLOAT multiplier


// card variables
VAR_INT next_card
VAR_INT dealers_cards[8]
VAR_INT players_cards1[8]
VAR_INT players_cards2[8]
// flags 
VAR_INT player_cannot_split
VAR_INT player_has_split
VAR_INT player_cannot_double
VAR_INT player_has_doubled1 
VAR_INT player_has_doubled2
VAR_INT player_has_stuck1 
VAR_INT player_has_stuck2
VAR_INT player_has_hit
VAR_INT dealer_has_stuck
VAR_INT player_has_quit
VAR_INT player_has_won1 
VAR_INT player_has_won2
VAR_INT player_has_input
VAR_INT dealer_wins_by_default
VAR_INT player_total1
VAR_INT player_total1_b	
VAR_INT player_total2
VAR_INT player_total2_b
VAR_INT dealer_total dealer_total2
VAR_INT dealer_got_ace
VAR_INT player_got_ace1
VAR_INT player_got_ace2
VAR_INT player_got_bj
VAR_INT dealer_got_bj

VAR_INT bj_players_cash
VAR_INT bj_last_bet
VAR_INT bj_bet1
VAR_INT bj_bet2
VAR_INT bj_bet_step
VAR_FLOAT bj_half_bet_f
VAR_INT bj_half_bet
VAR_INT bj_refund
VAR_INT bj_initial_stake
VAR_INT bj_payout

VAR_INT casino_credit

VAR_INT colour_r colour_g colour_b colour_a

VAR_INT bj_initial_last_bet

VAR_INT bj_offering_credit

VAR_INT total_bj_tables

// chips
VAR_INT bj_chip_set[8]
VAR_FLOAT bj_chip_set_default_z
//VAR_FLOAT bj_chip_set_z[8]

VAR_INT active_bj_table
VAR_INT active_bj_croupier

{
blackjack_script:

SCRIPT_NAME BLACKJ

LVAR_INT table
LVAR_INT croupier


// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals m_goals2 
LVAR_INT m_quit
LVAR_INT frame_num
LVAR_INT this_frame_time
LVAR_INT last_frame_time
LVAR_INT time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3 
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT temp_seq
LVAR_FLOAT vec_x vec_y 
LVAR_FLOAT vec2_x vec2_y 

LVAR_INT help_flag

LVAR_INT tri_is_pressed
LVAR_INT cross_is_pressed
LVAR_INT square_is_pressed
LVAR_INT circle_is_pressed
LVAR_INT blackjack_model
LVAR_INT bj_croupier_dm

LVAR_INT temp_ped
LVAR_INT max_bet
LVAR_INT min_bet

//LVAR_INT bet_index

m_stage 	= 0
m_goals 	= 0
m_goals2    = 0
m_quit		= 0
tri_is_pressed	= 0
cross_is_pressed = 0
square_is_pressed = 0

// fake create
IF m_goals = -1
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 table
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 croupier
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 active_bj_table
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 active_bj_croupier
	CREATE_OBJECT CHIP_STACK07 0.0 0.0 0.0 bj_chip_set[0]
ENDIF

max_bet = -99

	blackjack_loop:
	WAIT 0

		IF iTerminateAllAmbience = 0
			IF DOES_OBJECT_EXIST table 
				IF IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE table
					IF active_bj_table = 0
						IF IS_PLAYER_PLAYING player1
								// create ambient croupier
								IF m_stage = 0
									IF m_goals = 0
										GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
										IF temp_int = 0
											blackjack_model = VWMYBJD //VBFYCRP	// VWMYBJD
										ELSE
											blackjack_model = VWMYBJD //VBFYCRP //VWFYCRP
										ENDIF
										IF NOT HAS_MODEL_LOADED	blackjack_model
											REQUEST_MODEL blackjack_model
										ENDIF
										IF NOT HAS_ANIMATION_LOADED CASINO
											REQUEST_ANIMATION CASINO
										ENDIF

										// determine what value of table this is
										IF max_bet = -99

											GET_FLOAT_STAT GAMBLING temp_float
											IF total_bj_tables = 0
												IF temp_float < 1.0
													max_bet = 100
													min_bet = 1	
												ELSE
													IF temp_float < 10.0
														max_bet = 1000
														min_bet = 10
													ELSE
														IF temp_float < 100.0
															max_bet = 10000
															min_bet = 100
														ELSE
															IF temp_float < 1000.0
																max_bet = 100000
																min_bet = 1000
															ELSE
																max_bet = 1000000
																min_bet = 10000
															ENDIF
														ENDIF
													ENDIF
												ENDIF
												total_bj_tables++
											ELSE
												intrandom:
												GENERATE_RANDOM_INT_IN_RANGE 0 5 temp_int
												//bet_index = 0
												//WHILE bet_index < 4
													//IF bj_table_max_bets[bet_index] = temp_int
													//	GOTO intrandom
													//ENDIF
													//bet_index++
												//ENDWHILE

												//bet_index = total_roulette_tables - 1

												SWITCH temp_int
													CASE 0
														max_bet = 100	
														min_bet = 1		
													BREAK
													CASE 1
														max_bet = 1000
														min_bet = 10
													BREAK
													CASE 2
														max_bet = 10000
														min_bet = 100
													BREAK
													CASE 3
														max_bet = 100000
														min_bet = 1000
													BREAK
													CASE 4
														max_bet = 1000000
														min_bet = 10000
													BREAK
												ENDSWITCH
												
											ENDIF
										ENDIF
										m_goals++
									ENDIF	
									IF m_goals = 1
										IF HAS_MODEL_LOADED	blackjack_model
										AND HAS_ANIMATION_LOADED CASINO
											m_goals++
										ENDIF
									ENDIF
									IF m_goals = 2
										IF NOT DOES_CHAR_EXIST croupier
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.4 0.0 x y z
											GET_GROUND_Z_FOR_3D_COORD x y z z
			 								CREATE_CHAR PEDTYPE_CIVMALE	blackjack_model x y z croupier
											SET_CHAR_DECISION_MAKER croupier iGlobalPedPanicDM

											GET_OBJECT_HEADING table heading
											heading += 180.0
											SET_CHAR_HEADING croupier heading
											MARK_MODEL_AS_NO_LONGER_NEEDED blackjack_model

											ENABLE_DISABLED_ATTRACTORS_ON_OBJECT table TRUE

										ENDIF
										m_stage++
										m_goals = 0
									ENDIF 
								ENDIF

								// wait for player to trigger game
								IF m_stage = 1

									// player triggers game
									IF NOT IS_CHAR_DEAD croupier 
										IF iSetCasinoPanic = 0
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.4 0.0 x y z
											IF LOCATE_CHAR_ON_FOOT_3D croupier x y z 0.4 0.4 2.0 FALSE	
												GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 -1.5 0.0 x y z
												IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.5 0.5 2.0 FALSE
													IF IS_CHAR_ON_FOOT scplayer
														IF tri_is_pressed= 0
															
															IF IS_BUTTON_PRESSED PAD1 TRIANGLE
																
																STORE_SCORE player1 bj_players_cash
																GOSUB bj_update_casino_credit
																bj_players_cash += casino_credit
																IF bj_players_cash > min_bet
																	
																	// check the player has enough stats
																	temp_int = 0
																	GET_FLOAT_STAT GAMBLING temp_float
																	
																	IF max_bet = 1000000
																		IF temp_float < 1000.0
																			PRINT_NOW GAMBSTA 5000 1
																			temp_int = 1
																		ENDIF
																	ELSE
																		IF max_bet = 100000
																			IF temp_float < 100.0
																				PRINT_NOW GAMBSTA 5000 1 
																				temp_int = 1
																			ENDIF
																		ELSE
																			IF max_bet = 10000
																				IF temp_float < 10.0
																					PRINT_NOW GAMBSTA 5000 1 
																					temp_int = 1
																				ENDIF
																			ELSE
																				IF max_bet = 1000
																					IF temp_float < 1.0
																						PRINT_NOW GAMBSTA 5000 1 
																						temp_int = 1
																					ENDIF
																				ENDIF
																			ENDIF
																		ENDIF
																	ENDIF

																	// ok to proceed
																	IF temp_int = 0
																		IF help_flag = 1
																			CLEAR_PRINTS
																			CLEAR_HELP
																			help_flag = 0
																		ENDIF

																		m_stage = 0
																		m_goals = 0
																		active_bj_table = table
																		bj_initial_last_bet = 0
																		tri_is_pressed = 1
																	ELSE
																		IF NOT IS_CHAR_DEAD croupier
																			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
																			IF temp_int = 0
																				//WRITE_DEBUG not_regular1
																				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_REG_1 TRUE TRUE FALSE
																				START_CHAR_FACIAL_TALK croupier 2000
																			ELSE
																				//WRITE_DEBUG not_regular2
																				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_REG_2 TRUE TRUE FALSE
																				START_CHAR_FACIAL_TALK croupier 2000
																			ENDIF
																		ENDIF
																	ENDIF

																ELSE
																	PRINT_NOW BJ_2 5000 1 // not enough cash to play at this table

																	GOSUB bj_not_enough_cash
																ENDIF
															ELSE
																IF NOT help_flag = 1

																	IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
																	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
																	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
																	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
																		SET_HELP_MESSAGE_BOX_SIZE 200
																		SWITCH max_bet
																			CASE 100
																				PRINT_HELP_FOREVER BJ_1A  
																			BREAK				  
																			CASE 1000
																				PRINT_HELP_FOREVER BJ_1B  
																			BREAK				  
																			CASE 10000
																				PRINT_HELP_FOREVER BJ_1C  
																			BREAK				  
																			CASE 100000
																				PRINT_HELP_FOREVER BJ_1D  
																			BREAK				  
																			CASE 1000000
																				PRINT_HELP_FOREVER BJ_1E  
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
														CLEAR_THIS_PRINT BJ_2
														CLEAR_THIS_PRINT GAMBSTA
														CLEAR_HELP
														help_flag = 0
													ENDIF	
												ENDIF
											ELSE
												IF help_flag = 1
													CLEAR_THIS_PRINT BJ_2
													CLEAR_THIS_PRINT GAMBSTA
													CLEAR_HELP
													help_flag = 0
												ENDIF	
											ENDIF
										ELSE
											IF help_flag = 1
												CLEAR_THIS_PRINT BJ_2
												CLEAR_THIS_PRINT GAMBSTA
												CLEAR_HELP
												help_flag = 0
											ENDIF	
										ENDIF
									ENDIF
								ENDIF

						ELSE
							GOSUB black_jack_cleanup_top
							TERMINATE_THIS_SCRIPT	
						ENDIF						
					ELSE
						IF active_bj_table = table
							IF IS_PLAYER_PLAYING player1
								IF m_quit = 0
									GOSUB mission_loop_BLACKJ
								ELSE
									GOSUB mission_cleanup_BLACKJ
									active_bj_table = 0 
								ENDIF
							ENDIF
						ENDIF
					ENDIF				
				ELSE 
					GOSUB black_jack_cleanup_top
					TERMINATE_THIS_SCRIPT
				ENDIF
			ELSE
				GOSUB black_jack_cleanup_top
				TERMINATE_THIS_SCRIPT
			ENDIF
		ELSE
			GOSUB black_jack_cleanup_top
			TERMINATE_THIS_SCRIPT
		ENDIF
		
	GOTO blackjack_loop


black_jack_cleanup_top:
							
	IF DOES_OBJECT_EXIST table
		ENABLE_DISABLED_ATTRACTORS_ON_OBJECT table FALSE
	ENDIF

	MARK_CHAR_AS_NO_LONGER_NEEDED croupier
	REMOVE_ANIMATION CASINO
	total_bj_tables--
	m_stage = 0
RETURN





mission_loop_BLACKJ:

// *************************************************************************************************************
//		COMMONLY USED STUFF FOR EVERY SCRIPT - DON'T CHANGE
// *************************************************************************************************************

// display mission stage variables for debug
LVAR_INT display_debug
VAR_INT BLACKJ_view_debug[9]
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_SPACE
	display_debug++
	IF display_debug > 5
		display_debug = 0
	ENDIF
	CLEAR_ALL_VIEW_VARIABLES
ENDIF
IF display_debug = 1
	BLACKJ_view_debug[0] = m_stage
	BLACKJ_view_debug[1] = m_goals
	BLACKJ_view_debug[2] = m_goals2
	BLACKJ_view_debug[3] = player_total1
	BLACKJ_view_debug[4] = player_total1_b	
	BLACKJ_view_debug[5] = player_total2
	BLACKJ_view_debug[6] = player_total2_b
	BLACKJ_view_debug[7] = dealer_total 
	BLACKJ_view_debug[8] = dealer_total2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[0] m_stage
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[1] m_goals
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[2] m_goals2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[3] player_total1
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[4] player_total1_b	
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[5] player_total2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[6] player_total2_b
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[7] dealer_total 
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[8] dealer_total2
ENDIF
IF display_debug = 2
	BLACKJ_view_debug[0] = player_cannot_split
	BLACKJ_view_debug[1] = player_has_split
	BLACKJ_view_debug[2] = player_cannot_double
	BLACKJ_view_debug[3] = player_has_doubled1 
	BLACKJ_view_debug[4] = player_has_doubled2
	BLACKJ_view_debug[5] = player_has_stuck1 
	BLACKJ_view_debug[6] = player_has_stuck2
	BLACKJ_view_debug[7] = player_has_hit
	BLACKJ_view_debug[8] = dealer_has_stuck
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[0] player_cannot_split
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[1] player_has_split
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[2] player_cannot_double
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[3] player_has_doubled1 
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[4] player_has_doubled2	
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[5] player_has_stuck1 
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[6] player_has_stuck2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[7] player_has_hit
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[8] dealer_has_stuck
ENDIF
IF display_debug = 3
	BLACKJ_view_debug[0] = player_has_quit
	BLACKJ_view_debug[1] = player_has_won1 
	BLACKJ_view_debug[2] = player_has_won2
	BLACKJ_view_debug[3] = player_has_input
	BLACKJ_view_debug[4] = dealer_wins_by_default
	BLACKJ_view_debug[5] = dealer_got_ace
	BLACKJ_view_debug[6] = player_got_ace1
	BLACKJ_view_debug[7] = player_got_ace2
	BLACKJ_view_debug[8] = player_got_bj
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[0] player_has_quit
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[1] player_has_won1 
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[2] player_has_won2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[3] player_has_input
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[4] dealer_wins_by_default
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[5] dealer_got_ace
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[6] player_got_ace1
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[7] player_got_ace2
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[8] player_got_bj
ENDIF
IF display_debug = 4
	BLACKJ_view_debug[0] = 	bj_players_cash
	BLACKJ_view_debug[1] = 	min_bet
	BLACKJ_view_debug[2] = 	max_bet
	BLACKJ_view_debug[3] = 	bj_last_bet
	BLACKJ_view_debug[4] = 	bj_bet1
	BLACKJ_view_debug[5] = 	bj_bet2												   
	BLACKJ_view_debug[6] = 	bj_bet_step												   
	BLACKJ_view_debug[7] = 	bj_half_bet												   
	BLACKJ_view_debug[8] = 	bj_refund												   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[0] bj_players_cash								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[1] min_bet								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[2] max_bet								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[3] bj_last_bet								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[4] bj_bet1								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[5] bj_bet2								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[6] bj_bet_step
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[7] bj_half_bet
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[8] bj_refund
ENDIF

IF display_debug = 5
	BLACKJ_view_debug[0] = player_got_bj
	BLACKJ_view_debug[1] = bj_payout
	BLACKJ_view_debug[2] = bj_initial_stake
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[0] player_got_bj								   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[1] bj_payout					   
	VIEW_INTEGER_VARIABLE BLACKJ_view_debug[2] bj_initial_stake					   
ENDIF


// frame count
frame_num++
IF frame_num > 9
	frame_num = 0
ENDIF

// timer
GET_GAME_TIMER this_frame_time
time_diff = this_frame_time - last_frame_time 
last_frame_time = this_frame_time

IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
OR IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
OR IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
OR IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO

	IF NOT bj_offering_credit = 1
		IF NOT IS_CHAR_DEAD croupier
			GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
			SWITCH temp_int
				CASE 0
					SET_CHAR_SAY_SCRIPT	croupier SOUND_J_LEND1 TRUE TRUE FALSE
				BREAK
				CASE 1
					SET_CHAR_SAY_SCRIPT	croupier SOUND_J_LEND2 TRUE TRUE FALSE
				BREAK
				CASE 2
					SET_CHAR_SAY_SCRIPT	croupier SOUND_J_LEND3 TRUE TRUE FALSE
				BREAK
			ENDSWITCH 
		ENDIF
		bj_offering_credit = 1
		TIMERA = 0
	ENDIF

	IF cross_is_pressed = 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			CLEAR_HELP
			cross_is_pressed = 1
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			IF NOT cross_is_pressed = 0
				cross_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	IF circle_is_pressed = 0
		IF IS_BUTTON_PRESSED PAD1 CIRCLE
			CLEAR_HELP
			circle_is_pressed = 1
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
			IF NOT circle_is_pressed = 0
				circle_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	IF square_is_pressed = 0
		IF IS_BUTTON_PRESSED PAD1 SQUARE
			CLEAR_HELP
			square_is_pressed = 1
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
			IF NOT square_is_pressed = 0
				square_is_pressed = 0
			ENDIF
		ENDIF
	ENDIF


ELSE

	IF NOT bj_offering_credit = 0
		bj_offering_credit = 0		
	ENDIF

ENDIF

	SWITCH m_stage
		CASE 0
			GOSUB bj_m_stage_0
		BREAK
		CASE 1
			GOSUB bj_m_stage_1
		BREAK
		CASE 2
			GOSUB bj_m_stage_2
		BREAK
		CASE 3
			GOSUB bj_m_stage_3
		BREAK
		CASE 4
			GOSUB bj_m_stage_4
		BREAK
		CASE 5
			GOSUB bj_m_stage_5
		BREAK
		CASE 6
			GOSUB bj_m_stage_6
		BREAK
	ENDSWITCH

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
	card_cheat_on++
	IF card_cheat_on > 1
		card_cheat_on = 0
		WRITE_DEBUG_WITH_INT CARD_CHEAT_OFF	card_cheat_on
	ELSE
		WRITE_DEBUG_WITH_INT CARD_CHEAT_ON card_cheat_on
	ENDIF
ENDIF

VAR_INT card_cheat_value
IF NOT card_cheat_on = 0
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		card_cheat_value++
		IF card_cheat_value > 52
			card_cheat_value = 1
		ENDIF
		WRITE_DEBUG_WITH_INT NEXT_CARD card_cheat_value
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		card_cheat_value--
		IF card_cheat_value < 1
			card_cheat_value = 52
		ENDIF
		WRITE_DEBUG_WITH_INT NEXT_CARD card_cheat_value
	ENDIF
ENDIF

// ' /\ ' to quit game
IF tri_is_pressed = 0
	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	AND NOT m_stage = 2

		IF NOT IS_CHAR_DEAD croupier
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_THX_1 TRUE TRUE FALSE
			ELSE
				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_THX_2 TRUE TRUE FALSE
			ENDIF
		ENDIF

		// quit game
		m_quit = 1
		tri_is_pressed = 1
		// refund player
		IF NOT bj_refund = 0
			ADD_SCORE player1 bj_refund
		ENDIF
		bj_last_bet = 0
	ENDIF
ELSE
	IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
		tri_is_pressed = 0
	ENDIF
ENDIF

IF IS_CHAR_DEAD croupier
	m_quit = 1
ENDIF

RETURN


// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
bj_m_stage_0:
		
		IF m_goals = 0
		
		SET_MINIGAME_IN_PROGRESS TRUE
		SET_PLAYER_CONTROL player1 OFF

		winning_streak = 0
		

//----- BLACKJACK SCREEN COORDS ------
CARD_WIDTH = 51.0000 
CARD_HEIGHT = 64.0000 
PLAYER_SPRITE1_X[0] = 233.0000 
PLAYER_SPRITE1_Y[0] = 381.0000 
PLAYER_SPRITE1B_X[0] = 233.0000 
PLAYER_SPRITE1B_Y[0] = 311.0000 
PLAYER_SPRITE2_X[0] = 233.0000 
PLAYER_SPRITE2_Y[0] = 381.0000 
DEALER_SPRITE_X[0] = 226.0000 
DEALER_SPRITE_Y[0] = 60.0000 
BORDER_WIDTH = 6.0000 

//------ SCREEN COORDS ------
SCREEN_POS_X[0] = 29.0  		//29.0000 
SCREEN_POS_Y[0] = 220.0 		//177.0000 

SWITCH current_Language
	CASE LANGUAGE_ITALIAN
		SCREEN_POS_X[1] = 173.9799
		SCREEN_POS_Y[1] = 409.0000
	BREAK
	DEFAULT
		SCREEN_POS_X[1] = 157.0 
		SCREEN_POS_Y[1] = 409.0000
	BREAK
ENDSWITCH

SCREEN_POS_X[2] = 36.0000 
SCREEN_POS_Y[2] = 240.0000 
SCREEN_POS_X[3] = 36.0000 
SCREEN_POS_Y[3] = 260.0000 
SCREEN_POS_X[4] = 36.0000 
SCREEN_POS_Y[4] = 290.0000 
SCREEN_POS_X[5] = 36.0000 
SCREEN_POS_Y[5] = 310.0000 
SCREEN_POS_X[6] = 36.0000 
SCREEN_POS_Y[6] = 340.0000 
SCREEN_POS_X[7] = 36.0000 
SCREEN_POS_Y[7] = 360.0000 
SCREEN_POS_X[8] = 29.0   		//29.0000 
SCREEN_POS_Y[8] = 220.0  		//177.0000 


SWITCH current_Language
	CASE LANGUAGE_SPANISH
	CASE LANGUAGE_ITALIAN
	CASE LANGUAGE_GERMAN
		SCREEN_POS_X[9] = 169.0000 
		SCREEN_POS_Y[9] = 409.0000
	BREAK
	CASE LANGUAGE_FRENCH
		SCREEN_POS_X[9] = 177.0000 
		SCREEN_POS_Y[9] = 409.0000
	BREAK
	DEFAULT
		SCREEN_POS_X[9] = 157.0  		 
		SCREEN_POS_Y[9] = 409.0  	
	BREAK
ENDSWITCH
	 
SCREEN_POS_X[10] = 36.0000 
SCREEN_POS_Y[10] = 240.0000 
SCREEN_POS_X[11] = 36.0000 
SCREEN_POS_Y[11] = 260.0000 
SCREEN_POS_X[12] = 36.0000 
SCREEN_POS_Y[12] = 290.0000 
SCREEN_POS_X[13] = 40.0000 
SCREEN_POS_Y[13] = 310.0000 
SCREEN_POS_X[14] = 40.0000 
SCREEN_POS_Y[14] = 310.0000 
SCREEN_POS_X[15] = 36.0000 
SCREEN_POS_Y[15] = 340.0000 
SCREEN_POS_X[16] = 40.0000 
SCREEN_POS_Y[16] = 360.0000 
SCREEN_POS_X[17] = 40.0000 
SCREEN_POS_Y[17] = 360.0000 
SCREEN_POS_X[18] = 40.0000 
SCREEN_POS_Y[18] = 360.0000 
SCREEN_POS_X[19] = 40.0000 
SCREEN_POS_Y[19] = 360.0000 
SCREEN_POS_X[20] = 40.0000 
SCREEN_POS_Y[20] = 382.0000 
SCREEN_POS_X[21] = 40.0000 
SCREEN_POS_Y[21] = 382.0000 
SCREEN_POS_X[22] = 209.0000				//206.0000 //228.0000 	 
SCREEN_POS_Y[22] = 293.0000				//292.0000 //310.0000 	 
SCREEN_POS_X[23] = 209.0000				//228.0000 
SCREEN_POS_Y[23] = 361.0000				//364.0000 
SCREEN_POS_X[24] = 1.0000 
SCREEN_POS_Y[24] = 4.0000 
SCREEN_POS_X[25] = 320.0000 
SCREEN_POS_Y[25] = 197.0000 
SCREEN_POS_X[26] = 320.0000 
SCREEN_POS_Y[26] = 233.0000

			GOSUB bj_update_screen_positions

			// load models  -------------------------------------
			REQUEST_MODEL blck_jack
			REQUEST_ANIMATION casino
			m_goals++
		ENDIF

		IF m_goals = 1
			IF HAS_MODEL_LOADED blck_jack
			AND HAS_ANIMATION_LOADED casino
				m_goals++
			ENDIF
		ENDIF

		IF m_goals = 2

			// load text & textures -----------------------------
			USE_TEXT_COMMANDS TRUE
			LOAD_TEXTURE_DICTIONARY ld_card

			LOAD_SPRITE 1  cd1c
			LOAD_SPRITE 2  cd2c
			LOAD_SPRITE 3  cd3c
			LOAD_SPRITE 4  cd4c
			LOAD_SPRITE 5  cd5c
			LOAD_SPRITE 6  cd6c
			LOAD_SPRITE 7  cd7c
			LOAD_SPRITE 8  cd8c
			LOAD_SPRITE 9  cd9c
			LOAD_SPRITE 10 cd10c
			LOAD_SPRITE 11 cd11c
			LOAD_SPRITE 12 cd12c
			LOAD_SPRITE 13 cd13c

			LOAD_SPRITE 14  cd1d
			LOAD_SPRITE 15  cd2d
			LOAD_SPRITE 16  cd3d
			LOAD_SPRITE 17  cd4d
			LOAD_SPRITE 18  cd5d
			LOAD_SPRITE 19  cd6d
			LOAD_SPRITE 20  cd7d
			LOAD_SPRITE 21  cd8d
			LOAD_SPRITE 22  cd9d
			LOAD_SPRITE 23 cd10d
			LOAD_SPRITE 24 cd11d
			LOAD_SPRITE 25 cd12d
			LOAD_SPRITE 26 cd13d

			LOAD_SPRITE 27  cd1s
			LOAD_SPRITE 28  cd2s
			LOAD_SPRITE 29  cd3s
			LOAD_SPRITE 30  cd4s
			LOAD_SPRITE 31  cd5s
			LOAD_SPRITE 32  cd6s
			LOAD_SPRITE 33  cd7s
			LOAD_SPRITE 34  cd8s
			LOAD_SPRITE 35  cd9s
			LOAD_SPRITE 36 cd10s
			LOAD_SPRITE 37 cd11s
			LOAD_SPRITE 38 cd12s
			LOAD_SPRITE 39 cd13s
						  
			LOAD_SPRITE 40  cd1h
			LOAD_SPRITE 41  cd2h
			LOAD_SPRITE 42  cd3h
			LOAD_SPRITE 43  cd4h
			LOAD_SPRITE 44  cd5h
			LOAD_SPRITE 45  cd6h
			LOAD_SPRITE 46  cd7h
			LOAD_SPRITE 47  cd8h
			LOAD_SPRITE 48  cd9h
			LOAD_SPRITE 49 cd10h
			LOAD_SPRITE 50 cd11h
			LOAD_SPRITE 51 cd12h
			LOAD_SPRITE 52 cd13h

			LOAD_SPRITE 53 cdback

			// environment settings -----------------------------
			SHUFFLE_CARD_DECKS 6
			DISPLAY_RADAR FALSE
			SWITCH_RUBBISH OFF

			// reset croupier position
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.4 0.0 x y z
			GET_OBJECT_HEADING table heading
			heading += 180.0
			GET_GROUND_Z_FOR_3D_COORD x y z z
			z += -0.4
			IF NOT IS_CHAR_DEAD croupier
				CLEAR_CHAR_TASKS_IMMEDIATELY croupier
				SET_CHAR_COORDINATES croupier x y z
				SET_CHAR_HEADING croupier heading 
			ENDIF

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.3192 -1.6334 0.1415 x y z
			GET_GROUND_Z_FOR_3D_COORD x y z z
			z += -0.4
			SET_CHAR_COORDINATES scplayer x y z
			
			IF NOT IS_CHAR_DEAD croupier
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer croupier
			ENDIF

			IF DOES_OBJECT_EXIST table
				// set camera position
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.4379 -2.2430 1.1600  x y z
				SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0 
				// point camera at point				   
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table -0.2822 -1.3256 0.7939 x y z
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
			ENDIF	

			// create chips
			IF DOES_OBJECT_EXIST table
				bj_chip_set_default_z = -0.14

				BJ_CHIP_OFF_X[0] = 0.0000 
				BJ_CHIP_OFF_Y[0] = -0.8850 
				BJ_CHIP_OFF_Z[0] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[1] = 0.0450 
				BJ_CHIP_OFF_Y[1] = -0.9350 
				BJ_CHIP_OFF_Z[1] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[2] = -0.0500 
				BJ_CHIP_OFF_Y[2] = -0.9300 
				BJ_CHIP_OFF_Z[2] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[3] = -0.0100 
				BJ_CHIP_OFF_Y[3] = -0.9800 
				BJ_CHIP_OFF_Z[3] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[4] = 0.0600 
				BJ_CHIP_OFF_Y[4] = -0.9950 
				BJ_CHIP_OFF_Z[4] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[5] = 0.0200 
				BJ_CHIP_OFF_Y[5] = -1.0750 
				BJ_CHIP_OFF_Z[5] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[6] = -0.0700 
				BJ_CHIP_OFF_Y[6] = -1.0300 
				BJ_CHIP_OFF_Z[6] = bj_chip_set_default_z 
				BJ_CHIP_OFF_X[7] = 0.0650 
				BJ_CHIP_OFF_Y[7] = -1.0500 
				BJ_CHIP_OFF_Z[7] = bj_chip_set_default_z 


				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[0] BJ_CHIP_OFF_Y[0] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK07 x y z bj_chip_set[0]
				SET_OBJECT_HEADING bj_chip_set[0] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[1] BJ_CHIP_OFF_Y[1] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK08 x y z bj_chip_set[1]
				SET_OBJECT_HEADING bj_chip_set[1] heading
	
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[2] BJ_CHIP_OFF_Y[2] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK09 x y z bj_chip_set[2]
				SET_OBJECT_HEADING bj_chip_set[2] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[3] BJ_CHIP_OFF_Y[3] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK10 x y z bj_chip_set[3]
				SET_OBJECT_HEADING bj_chip_set[3] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[4] BJ_CHIP_OFF_Y[4] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK11 x y z bj_chip_set[4]
				SET_OBJECT_HEADING bj_chip_set[4] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[5] BJ_CHIP_OFF_Y[5] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK12 x y z bj_chip_set[5]
				SET_OBJECT_HEADING bj_chip_set[5] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[6] BJ_CHIP_OFF_Y[6] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK13 x y z bj_chip_set[6]
				SET_OBJECT_HEADING bj_chip_set[6] heading

				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table BJ_CHIP_OFF_X[7] BJ_CHIP_OFF_Y[7] bj_chip_set_default_z x y z
				CREATE_OBJECT_NO_OFFSET CHIP_STACK14 x y z bj_chip_set[7]
				SET_OBJECT_HEADING bj_chip_set[7] heading
															   
			ENDIF

			m_goals = 99
		ENDIF

		IF m_goals = 99

			IF NOT IS_CHAR_DEAD croupier
				GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				SWITCH temp_int
					CASE 0
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BET_1 TRUE TRUE FALSE	
					BREAK
					CASE 1
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BET_2 TRUE TRUE FALSE
					BREAK
				ENDSWITCH
			ENDIF

			m_stage++
			m_goals = 0
		ENDIF

RETURN


// *************************************************************************************************************
//						STAGE 1 - BlackJack Minigame START - PLACE BET 
// *************************************************************************************************************
bj_m_stage_1:

		// initialisation for stage
		IF m_goals = 0

			// reset flags for this hand
			next_card = 0
			temp_int = 0
			WHILE temp_int < 8
				dealers_cards[temp_int]		= 0		
				players_cards1[temp_int]	= 0
				players_cards2[temp_int]	= 0
			temp_int++
			ENDWHILE
			player_cannot_split				= 0
			player_has_split				= 0
			player_cannot_double			= 0
			player_has_doubled1 			= 0
			player_has_doubled2				= 0
			player_has_stuck1 				= 0
			player_has_stuck2				= 0
			player_has_hit					= 0
			dealer_has_stuck				= 0
			player_has_quit					= 0
			player_has_won1 				= 0
			player_has_won2					= 0
			player_has_input				= 0
			dealer_wins_by_default			= 0
			player_total1					= 0
			player_total1_b					= 0
			player_total2					= 0
			player_total2_b					= 0
			dealer_total					= 0
			dealer_total2					= 0
			dealer_got_ace					= 0
			player_got_ace1					= 0
			player_got_ace2					= 0
			bj_refund						= 0

			
			// Enforce minimum bet.

			IF bj_initial_last_bet < min_bet
				bj_initial_last_bet = min_bet
			ENDIF

            bj_bet1 = bj_initial_last_bet
			bj_bet2 = 0

			STORE_SCORE player1 bj_players_cash
			GOSUB bj_update_casino_credit
			bj_players_cash += casino_credit
			IF bj_players_cash < bj_bet1
				bj_bet1 = min_bet
			ENDIF

			IF bj_players_cash < bj_bet1
				PRINT_NOW BJ_2 5000 1 // not enough cash to play at this table
				GOSUB bj_not_enough_cash
                m_quit = 1
				GOTO bj_m_stage_1_end
			ELSE
				temp_int = bj_bet1 * -1
				ADD_SCORE player1 temp_int	
				bj_refund = bj_bet1
				bj_initial_stake = bj_bet1
				IF NOT IS_CHAR_DEAD croupier
					GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
					SWITCH temp_int
						CASE 0
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BET_1 TRUE TRUE FALSE	
						BREAK
						CASE 1
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BET_2 TRUE TRUE FALSE
						BREAK
					ENDSWITCH
				ENDIF
			ENDIF


			TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CASHWIN
        
			m_goals++
		ENDIF

		// get input
		IF m_goals = 1

			IF NOT IS_MESSAGE_BEING_DISPLAYED
				IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 200
						PRINT_HELP_FOREVER BJ_H1
					ENDIF
				ENDIF
		    
				STORE_SCORE player1 bj_players_cash
				GOSUB bj_update_casino_credit
				bj_players_cash += casino_credit

				IF IS_BUTTON_PRESSED PAD1 SQUARE
					
					IF NOT square_is_pressed = 1 
					AND NOT square_is_pressed = -1
						// sort out betting step
						IF bj_bet1 >= 10000
							bj_bet_step = 1000
						ELSE
							IF bj_bet1 >= 1000
								bj_bet_step = 100
							ELSE
								IF bj_bet1 >= 100
									bj_bet_step = 10
								ELSE
									IF bj_bet1 = 0
										bj_bet_step = 2
									ELSE
										bj_bet_step = 1	
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						
						// check betting step doesn't go over players cash
						IF bj_bet_step > bj_players_cash
							bj_bet_step = bj_players_cash
						ENDIF

						// check betting step won't push us over the maximum bet
						temp_int = bj_bet1 + bj_bet_step
						IF temp_int > max_bet
							bj_bet_step = max_bet - bj_bet1
						ENDIF

						// take cash off player
						IF bj_bet_step < 0
							bj_bet_step *= -1
						ENDIF

						bj_bet1 += bj_bet_step
						bj_refund += bj_bet_step
						bj_initial_stake += bj_bet_step
						bj_bet_step *= -1
						ADD_SCORE player1 bj_bet_step
					
						square_is_pressed++
						IF square_is_pressed > 1
							square_is_pressed = 2
						ELSE
							TIMERA = 0	
						ENDIF
					ELSE
						IF square_is_pressed = 1
							IF TIMERA > 500
								square_is_pressed = 2	
							ENDIF 
						ENDIF
					ENDIF
				ELSE

					// reset cross button flag
					IF NOT circle_is_pressed = 0 
						circle_is_pressed = 0	
					ENDIF

					
					// remove wager
					
					IF IS_BUTTON_PRESSED PAD1 CIRCLE

						IF NOT circle_is_pressed = 1
							// sort out betting step
							IF bj_bet1 > 10000
								bj_bet_step = -1000
							ELSE
								IF bj_bet1 > 1000
									bj_bet_step = -100
								ELSE
									IF bj_bet1 > 100
										bj_bet_step = -10
									ELSE
										bj_bet_step = -1
									ENDIF
								ENDIF
							ENDIF

							// check we don't go below minimum bet
							temp_int = bj_bet1
						
							bj_bet1 += bj_bet_step
							IF bj_bet1 < min_bet
								bj_bet1 = 0
								bj_bet_step = temp_int
								bj_bet_step *= -1
							ENDIF 
					
							IF bj_bet1 < min_bet
								bj_bet1 = min_bet
							ELSE
								bj_bet_step *= -1
								bj_refund -= bj_bet_step
								bj_initial_stake -= bj_bet_step
								ADD_SCORE player1 bj_bet_step
							ENDIF
						
							circle_is_pressed++
							IF circle_is_pressed > 1
								circle_is_pressed = 2
							ELSE
								TIMERA = 0
							ENDIF
						ELSE
							IF TIMERA > 500
								circle_is_pressed = 2
							ENDIF
						ENDIF
					ELSE
						IF NOT circle_is_pressed = 0
							circle_is_pressed = 0
						ENDIF
					ENDIF

				ENDIF


				// ' X '  to ok the bet
				IF cross_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 CROSS

						bj_initial_last_bet = bj_bet1
					
						INCREMENT_INT_STAT MONEY_SPENT_GAMBLING bj_bet1
						temp_float =# bj_bet1
						temp_float *= 0.001
						INCREMENT_FLOAT_STAT GAMBLING temp_float

						bj_refund = 0
						bj_last_bet = bj_bet1

						GOSUB bj_draw_window_1

						cross_is_pressed = 1

						m_goals = 99
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 0
					ENDIF
				ENDIF
				
				GOSUB bj_generate_chip_stack

				IF NOT m_goals = 99	
					m_goals++
				ENDIF		
			ELSE
				IF cross_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 1
						CLEAR_PRINTS
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 0
					ENDIF
				ENDIF
			ENDIF		
		ENDIF

		// display betting text
		IF m_goals = 2
			VAR_FLOAT screen_pos_x[27]
			VAR_FLOAT screen_pos_y[27]

			GOSUB bj_draw_window_1

			m_goals = 1 // stay within loop
		ENDIF	

		// exit
		IF m_goals = 99
			GOSUB bj_clear_help
			m_goals = 0
			m_stage++
		ENDIF

bj_m_stage_1_end:
RETURN 

// *************************************************************************************************************
//									STAGE 2 - Deal first 4 cards. 
// *************************************************************************************************************
bj_m_stage_2:
		

		// deal first card - player
		IF m_goals = 0
			GOSUB deal_card
			IF NOT next_card = 0
				players_cards1[0] = next_card
			ENDIF
			m_goals++
		ENDIF
		IF m_goals = 1
			// wait for dealer to finish anim
			IF NOT IS_CHAR_DEAD croupier
				GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
				IF temp_int = FINISHED_TASK	
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// deal first card - dealer
		IF m_goals = 2
			GOSUB deal_card
			IF NOT next_card = 0
				dealers_cards[0] = next_card
			ENDIF
			m_goals++
		ENDIF
		IF m_goals = 3
			// wait for dealer to finish anim
			IF NOT IS_CHAR_DEAD croupier
				GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
				IF temp_int = FINISHED_TASK	
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// deal 2nd card - player
		IF m_goals = 4
			GOSUB deal_card
			IF NOT next_card = 0
				players_cards1[1] = next_card
			ENDIF
			m_goals++
		ENDIF
		IF m_goals = 5
			// wait for dealer to finish anim
			IF NOT IS_CHAR_DEAD croupier
				GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
				IF temp_int = FINISHED_TASK	
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// deal 2nd card - dealer
		IF m_goals = 6
			GOSUB deal_card
			IF NOT next_card = 0
				dealers_cards[1] = next_card
			ENDIF
			m_goals++
		ENDIF
		IF m_goals = 7
			// wait for dealer to finish anim
			IF NOT IS_CHAR_DEAD croupier
				GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
				IF temp_int = FINISHED_TASK	
					m_goals = 99
				ENDIF
			ENDIF
		ENDIF
		
		// exit
		IF m_goals = 99
			help_flag = 0
			m_goals = 0
			m_stage++
		ENDIF

		// FUNCTIONS GLOBAL FOR STAGE -------
		GOSUB calculate_totals
		GOSUB display_cards
		GOSUB bj_draw_window_2

RETURN 

// *************************************************************************************************************
//									STAGE 3 - Check for immediate blackjacks 
// *************************************************************************************************************
bJ_m_stage_3:
		GOSUB bj_clear_help
		
		IF m_goals = 0
			GOSUB calculate_totals
			player_got_bj = 0
			dealer_got_bj = 0
			IF player_total1 = 21
			OR dealer_total	= 21
				IF player_total1 = 21
					player_got_bj = 1
				ENDIF
				IF dealer_total = 21
					dealer_got_bj = 1
				ENDIF
				player_has_stuck1 = 1
				dealer_has_stuck = 1
				m_stage += 2
				m_goals = 0
			ELSE
				m_goals = 99 // continue
			ENDIF
		ENDIF

		IF m_goals = 99
			m_goals = 0
			m_goals2 = 0
			help_flag = 0
			m_stage++
		ENDIF

		GOSUB calculate_totals
		GOSUB display_cards
		GOSUB bj_draw_window_2

		// say total
		IF player_got_bj = 0
			GOSUB bj_say_player_total
		ELSE
			IF NOT dealer_total = 21
				IF NOT IS_CHAR_DEAD croupier
					GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
					IF temp_int = 0
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BJ_1 TRUE TRUE FALSE
					ELSE
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BJ_2 TRUE TRUE FALSE
					ENDIF
				ENDIF
			ELSE
				GOSUB bj_say_player_total	
			ENDIF	
		ENDIF


RETURN

bj_clear_help:

IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
	CLEAR_HELP
ENDIF

RETURN


// *************************************************************************************************************
//								STAGE 4 - Wait for player to finish taking more cards 
// *************************************************************************************************************
bj_m_stage_4:

		// get input
		IF m_goals = 0

			// check if player can split or not
			IF player_cannot_split = 0
				in_card_value = players_cards1[0] 
				GOSUB convert_card_value_for_splits
				temp_int = out_card_value

				in_card_value = players_cards1[1]
				GOSUB convert_card_value_for_splits
				temp_int2 = out_card_value

				IF temp_int = temp_int2
					IF NOT players_cards1[2] = 0
						GOSUB bj_clear_help
						player_cannot_split = 1
					ENDIF
				ELSE
					GOSUB bj_clear_help
					player_cannot_split = 1
				ENDIF

				IF NOT player_has_split = 0
					GOSUB bj_clear_help
					player_cannot_split = 1	
				ENDIF
				
			ENDIF

			// check if the player can double or not
			IF player_cannot_double = 0
				IF player_has_split = 0
					IF NOT players_cards1[2] = 0
						GOSUB bj_clear_help
						player_cannot_double = 1
					ENDIF
					STORE_SCORE player1 bj_players_cash
					GOSUB bj_update_casino_credit
					bj_players_cash += casino_credit

					IF bj_players_cash < bj_bet1
						GOSUB bj_clear_help
						player_cannot_double = 1
					ENDIF
				ELSE
					IF player_has_stuck1 = 0 
						IF NOT players_cards1[2] = 0
							GOSUB bj_clear_help
							player_cannot_double = 1
						ENDIF 
						STORE_SCORE player1 bj_players_cash
						GOSUB bj_update_casino_credit
						bj_players_cash += casino_credit

						IF bj_players_cash < bj_bet1
							GOSUB bj_clear_help
							player_cannot_double = 1
						ENDIF
					ELSE
						IF NOT players_cards2[2] = 0
							GOSUB bj_clear_help
							player_cannot_double = 1
						ENDIF
						STORE_SCORE player1 bj_players_cash
						GOSUB bj_update_casino_credit
						bj_players_cash += casino_credit

						IF bj_players_cash < bj_bet2
							GOSUB bj_clear_help
							player_cannot_double = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			
			IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO

				IF player_cannot_split = 0
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H3  
					ENDIF
					help_flag = 1	
				ENDIF
				
				IF player_cannot_split = 1
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H2  
					ENDIF
					help_flag = 2	
				ENDIF
				
				IF player_cannot_split = 1
				AND player_cannot_double  = 1
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H5  
					ENDIF
					help_flag = 3	
				ENDIF 

				IF player_cannot_split = 1
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H2  
					ENDIF
					help_flag = 2	
				ENDIF
				
				IF player_cannot_split = 1
				AND player_cannot_double  = 1
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H5  
					ENDIF
					help_flag = 3	
				ENDIF 

				IF player_cannot_split = 0
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H3  
					ENDIF
					help_flag = 1	
				ENDIF
				
				IF player_cannot_split = 1
				AND player_cannot_double  = 1
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H5  
					ENDIF
					help_flag = 3	
				ENDIF

				IF player_cannot_split = 0
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H3  
					ENDIF
					help_flag = 1	
				ENDIF
				
				IF player_cannot_split = 1
				AND player_cannot_double = 0
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H2  
					ENDIF
					help_flag = 2	
				ENDIF

			ENDIF
			

			IF NOT IS_MESSAGE_BEING_DISPLAYED 
				

				// hit
				
				IF circle_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 CIRCLE
						player_has_hit = 1
						m_goals2 = 0
						m_goals++
						circle_is_pressed = 1
						GOTO end_of_input2
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
						circle_is_pressed = 0
					ENDIF
				ENDIF

				// split
				IF player_cannot_split = 0
					IF player_has_split = 0
						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
							player_has_split = 1
							m_goals++
							m_goals2 = 0
							GOTO end_of_input2
						ENDIF
					ENDIF
				ENDIF

				// double
				IF player_cannot_double = 0
					IF IS_BUTTON_PRESSED PAD1 SQUARE
						IF player_has_stuck1 = 0
							player_has_doubled1 = 1
						ELSE
							player_has_doubled2 = 1	
						ENDIF
						m_goals++
						GOTO end_of_input2
					ENDIF
				ENDIF

				// stick
				IF cross_is_pressed = 0
			   		IF IS_BUTTON_PRESSED PAD1 CROSS
						IF player_has_stuck1 = 0
							player_has_stuck1 = 1
						ELSE
							player_has_stuck2 = 1
						ENDIF
						cross_is_pressed = 1
						m_goals++
						GOTO end_of_input2	
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 0
					ENDIF
				ENDIF

			ELSE
				IF circle_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 CIRCLE
						circle_is_pressed = 1
						CLEAR_PRINTS
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
						circle_is_pressed = 0
					ENDIF
				ENDIF
			ENDIF

			end_of_input2:
		ENDIF

		// process input
		IF m_goals = 1

			// players has hit
			IF player_has_hit = 1
				// deal next card
				IF m_goals2 = 0
					GOSUB deal_card
					m_goals2++
				ENDIF
				// wait for dealer to finish
				IF m_goals2 = 1
					IF NOT IS_CHAR_DEAD croupier
						GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
						IF temp_int = FINISHED_TASK
							m_goals2++
						ENDIF
					ENDIF
				ENDIF
				// decide where to put card
				IF m_goals2 = 2
					IF player_has_split = 0
						temp_int = 0
						WHILE temp_int < 8
							IF players_cards1[temp_int] = 0
 								players_cards1[temp_int] = next_card
								temp_int = 8
								m_goals2 = 99
							ENDIF
						temp_int++
						ENDWHILE
					ELSE
						IF player_has_stuck1 = 0
							temp_int = 0
							WHILE temp_int < 8
								IF players_cards1[temp_int] = 0
	 								players_cards1[temp_int] = next_card
									temp_int = 8
									m_goals2 = 99
								ENDIF
							temp_int++
							ENDWHILE
						ELSE
							temp_int = 0
							WHILE temp_int < 8
								IF players_cards2[temp_int] = 0
	 								players_cards2[temp_int] = next_card
									temp_int = 8
									m_goals2 = 99
								ENDIF
							temp_int++
							ENDWHILE								
						ENDIF	
					ENDIF
				ENDIF

				// finished hitting
				IF m_goals2 = 99
					player_has_hit = 0	   
					GOSUB bj_say_player_total
				ENDIF
			ENDIF // player has hit

			// if player has just split - split cards, and deal card to pile 1
			temp_int = 0
			IF player_has_split = 1
				IF players_cards2[0] = 0
				OR players_cards1[1] = 0
					temp_int = 1
				ENDIF
			ENDIF 
			IF player_has_split = 1
			AND temp_int = 1

				// check its cool to split
				IF m_goals2 = 0
					// increase bet
					bj_half_bet_f =# bj_bet1
					//bj_half_bet_f /= 2.0
					bj_half_bet =# bj_half_bet_f
					// check if player can affor to raise bet
					STORE_SCORE player1 bj_players_cash
					GOSUB bj_update_casino_credit
					bj_players_cash += casino_credit


					IF bj_half_bet > bj_players_cash
						player_has_split = 0
						m_goals2 = 99
						PRINT_NOW BJ_3 5000 1 // you don't have enough cash
						GOSUB bj_not_enough_cash
					ELSE
						m_goals2++
					ENDIF

				ENDIF
				
				IF m_goals2 = 1

					INCREMENT_INT_STAT MONEY_SPENT_GAMBLING bj_half_bet
					temp_float =# bj_half_bet
					temp_float *= 0.001
					INCREMENT_FLOAT_STAT GAMBLING temp_float

			   		// take money from player
					bj_half_bet *= -1
					ADD_SCORE player1 bj_half_bet
					bj_half_bet *= -1
					bj_bet1 += bj_half_bet
					bj_initial_stake += bj_half_bet

					GOSUB bj_generate_chip_stack
					m_goals2++
				ENDIF


				// split cards
				IF m_goals2 = 2
					players_cards2[0] = players_cards1[0]
					players_cards1[0] = players_cards1[1]
					//players_cards2[0] = players_cards1[1]
					players_cards1[1] = 0
					GOSUB deal_card
					m_goals2++
				ENDIF
				// wait for dealer to deal new card
				IF m_goals2 = 3
					IF NOT IS_CHAR_DEAD croupier
						GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
						IF temp_int = FINISHED_TASK
							players_cards1[1] = next_card
							m_goals2++
						ENDIF
					ENDIF
				ENDIF
				// split bet
				IF m_goals2 = 4
					bj_half_bet_f =# bj_bet1
					bj_half_bet_f /= 2.0
					bj_half_bet =# bj_half_bet_f
					bj_bet1 = bj_half_bet
					bj_bet2 = bj_half_bet
					player_cannot_double = 0
					GOSUB bj_clear_help
					GOSUB bj_say_player_total
					m_goals2 = 99
				ENDIF

			ENDIF

			// if player has split and just stuck on pile 1 - deal card for pile 2
			split_cards_mark:
			IF player_has_split = 1
			AND player_has_stuck1 = 1
			AND players_cards2[1] = 0


				IF m_goals2 = 0
					GOSUB deal_card
					m_goals2++
				ENDIF
				// wait for dealer to deal
				IF m_goals2 = 1
					IF NOT IS_CHAR_DEAD croupier
						GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
						IF temp_int = FINISHED_TASK
							m_goals2++
						ENDIF
					ENDIF
				ENDIF
				IF m_goals2 = 2
					players_cards2[1] = next_card
					player_cannot_double = 0
					GOSUB bj_clear_help
					GOSUB bj_say_player_total
					m_goals2 = 99
				ENDIF
				IF m_goals2 = 99
					// nothing
				ENDIF 
			ENDIF

			// double - if player has just doubled deal one card on that pile then stick
			IF player_has_split = 0
				IF player_has_doubled1 = 1
				AND player_has_stuck1 = 0
					
					IF m_goals2 = 0
						// increase bet
						bj_half_bet_f =# bj_bet1
						//bj_half_bet_f /= 2.0
						bj_half_bet =# bj_half_bet_f
						// check if player can affor to raise bet
						STORE_SCORE player1 bj_players_cash
						GOSUB bj_update_casino_credit
						bj_players_cash += casino_credit


						IF bj_half_bet > bj_players_cash
							player_has_doubled1 = 0
							m_goals2 = 99
							PRINT_NOW BJ_3 5000 1 // you don't have enough cash
							GOSUB bj_not_enough_cash
						ELSE
							m_goals2++
						ENDIF

					ENDIF
					
					IF m_goals2 = 1

						INCREMENT_INT_STAT MONEY_SPENT_GAMBLING bj_half_bet
						temp_float =# bj_half_bet
						temp_float *= 0.001
						INCREMENT_FLOAT_STAT GAMBLING temp_float


				   		// take money from player
						bj_half_bet *= -1
						ADD_SCORE player1 bj_half_bet
						bj_half_bet *= -1
						bj_bet1 += bj_half_bet
						bj_initial_stake += bj_half_bet

						GOSUB bj_generate_chip_stack

						// deal card
						GOSUB deal_card
						m_goals2++
					ENDIF

					// wait for dealer to deal
					IF m_goals2 = 2
						IF NOT IS_CHAR_DEAD croupier
							GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
							IF temp_int = FINISHED_TASK
								m_goals2++
							ENDIF
						ENDIF 
					ENDIF

					IF m_goals2 = 3
						players_cards1[2] = next_card
						player_has_stuck1 = 1
						GOSUB bj_say_player_total
						m_goals2 = 99
					ENDIF
					
				ENDIF
			ELSE
				// pile 1
				IF player_has_doubled1 = 1
				AND player_has_stuck1 = 0
					
					IF m_goals2 = 0
						// increase bet
						bj_half_bet_f =# bj_bet1
						//bj_half_bet_f /= 2.0
						bj_half_bet =# bj_half_bet_f
						// check if player can affor to raise bet
						STORE_SCORE player1 bj_players_cash
						GOSUB bj_update_casino_credit
						bj_players_cash += casino_credit


						IF bj_half_bet > bj_players_cash
							player_has_doubled1 = 0
							// PRINT "you don't have enough cash
							PRINT_NOW BJ_3 5000 1 // you don't have enough cash
							GOSUB bj_not_enough_cash
							m_goals2= 99
						ELSE
							m_goals2++
						ENDIF
					ENDIF

					IF m_goals2 = 1
						INCREMENT_INT_STAT MONEY_SPENT_GAMBLING bj_half_bet
						temp_float =# bj_half_bet
						temp_float *= 0.001
						INCREMENT_FLOAT_STAT GAMBLING temp_float
						
						// take money from player
						bj_half_bet *= -1
						ADD_SCORE player1 bj_half_bet
						bj_half_bet *= -1
						bj_bet1 += bj_half_bet
						bj_initial_stake += bj_half_bet

						GOSUB bj_generate_chip_stack

						// deal card
						GOSUB deal_card
						m_goals2++
					ENDIF

					// wait for dealer to deal
					IF m_goals2 = 2
						IF NOT IS_CHAR_DEAD croupier
							GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
							IF temp_int = FINISHED_TASK
								m_goals2++
							ENDIF
						ENDIF 
					ENDIF

					IF m_goals2 = 3
						players_cards1[2] = next_card
						player_has_stuck1 = 1
						GOSUB bj_say_player_total
						m_goals2 = 0
						GOTO split_cards_mark
 					ENDIF					
				ENDIF
				// pile 2
				IF player_has_doubled2 = 1
				AND player_has_stuck2 = 0

					IF m_goals2 = 0
						// increase bet
						bj_half_bet_f =# bj_bet1
						//bj_half_bet_f /= 2.0
						bj_half_bet =# bj_half_bet_f
						// check if player can affor to raise bet
						STORE_SCORE player1 bj_players_cash
						GOSUB bj_update_casino_credit
						bj_players_cash += casino_credit


						IF bj_half_bet > bj_players_cash
							player_has_doubled2 = 0
							m_goals2 = 99
							// PRINT "you don't have enough cash
							PRINT_NOW BJ_3 5000 1 // you don't have enough cash
							GOSUB bj_not_enough_cash
						ELSE
							m_goals2++
						ENDIF
					ENDIF

					IF m_goals2 = 1

						INCREMENT_INT_STAT MONEY_SPENT_GAMBLING bj_half_bet
						temp_float =# bj_half_bet
						temp_float *= 0.001
						INCREMENT_FLOAT_STAT GAMBLING temp_float

				   		// take money from player
						bj_half_bet *= -1
						ADD_SCORE player1 bj_half_bet
						bj_half_bet *= -1
						bj_bet2 += bj_half_bet
						bj_initial_stake += bj_half_bet

						GOSUB bj_generate_chip_stack

						// deal card
						GOSUB deal_card
						m_goals2++
					ENDIF

					// wait for dealer to deal
					IF m_goals2 = 2
						IF NOT IS_CHAR_DEAD croupier
							GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
							IF temp_int = FINISHED_TASK
								m_goals2++
							ENDIF
						ENDIF 
					ENDIF

					IF m_goals2 = 3
						players_cards2[2] = next_card
						player_has_stuck2 = 1
						GOSUB bj_say_player_total
						m_goals2 = 99
					ENDIF
				ENDIF
		   	ENDIF

			//finish_sorting_out_dealt_cards:


			// check if player is bust or on 21 if so stick
			GOSUB calculate_totals
			IF player_has_split = 0
				IF player_total1 > 20
					player_has_stuck1 = 1
				ENDIF	
				IF NOT players_cards1[4] = 0
					player_has_stuck1 = 1
				ENDIF
			ELSE
				IF player_has_stuck1 = 0
					IF player_total1 > 20
						player_has_stuck1 = 1
						m_goals2 = 0
						GOTO split_cards_mark
					ENDIF
					IF NOT players_cards1[4] = 0
						player_has_stuck1 = 1
						m_goals2 = 0
						GOTO split_cards_mark
					ENDIF
				ENDIF
				IF player_total2 > 20
					player_has_stuck2 = 1
				ENDIF	
				IF NOT players_cards2[4] = 0
					player_has_stuck2 = 1
				ENDIF
			ENDIF



			// check if player has blackjack on split cards
			IF player_has_split = 1
				IF player_has_stuck1 = 1
				AND player_has_stuck2 = 1
					IF player_total1 = 21
					AND players_cards1[2] = 0
						IF NOT player_total2 = 21
						OR NOT players_cards2[2] = 0
							player_got_bj = 2
						ELSE
							IF player_total2 = 21
							AND players_cards2[2] = 0
								player_got_bj = 4
							ENDIF
						ENDIF
					ELSE
						IF player_total2 = 21
						AND players_cards2[2] = 0
							player_got_bj = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// check if player is finished
			IF player_has_split = 0
				IF player_has_stuck1 = 1
					m_goals = 99
				ELSE
					IF m_goals2 = 99
						m_goals = 0 // go back to user input
						m_goals2 = 0
					ENDIF 
				ENDIF
			ELSE
				IF player_has_stuck1 = 1
				AND player_has_stuck2 = 1
					m_goals = 99
				ELSE
					IF m_goals2 = 99
						m_goals = 0 // go back to user input
						m_goals2 = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
					
									  
		// draw split arrow
		IF player_has_split = 1
			IF player_has_stuck1 = 0
				
				GOSUB bj_text_arrow
				DISPLAY_TEXT SCREEN_POS_X[22] SCREEN_POS_Y[22] BJ_ARR  // >
			ELSE
				GOSUB bj_text_arrow
				DISPLAY_TEXT SCREEN_POS_X[23] SCREEN_POS_Y[23] BJ_ARR  // >
			ENDIF
		ENDIF


		// end of players turn
		IF m_goals = 99
			GOSUB bj_clear_help
			m_goals = 0
			m_stage++
		ENDIF

		// GLOBAL STUFF FOR STAGE -----------
		
		GOSUB calculate_totals
		GOSUB display_cards
		GOSUB bj_draw_window_2
		
RETURN

// *************************************************************************************************************
//								STAGE 5 - Dealers turn 
// *************************************************************************************************************	
bj_m_stage_5:


		// check if player is bust, if so just make dealer win
		IF m_goals = 0
			// if player is bust - just make dealer win
			IF player_has_split = 0
				IF player_total1 > 21
				//AND player_total1_b > 21
					dealer_has_stuck = 1
					dealer_wins_by_default = 1
					m_goals = 99
				ENDIF
			ELSE
				IF player_total1 > 21 
				AND player_total2 > 21
					dealer_has_stuck = 1
					dealer_wins_by_default = 1
					m_goals = 99
				ENDIF
			ENDIF
			IF NOT m_goals = 99
				m_goals++
			ENDIF
		ENDIF

		// check if dealer is on 17 or over
		IF m_goals = 1

			IF dealer_total > 16			
				dealer_has_stuck = 1
			ELSE
				m_goals++
			ENDIF
			// stick on 5 cards
			IF NOT dealers_cards[4] = 0
				dealer_has_stuck = 1
			ENDIF

			IF dealer_has_stuck = 1
				m_goals = 99
			ENDIF
		ENDIF
		
		// deal dealer new card
		IF m_goals = 2
			GOSUB deal_card
			m_goals++
		ENDIF
		IF m_goals = 3
			// wait for dealer to deal
			IF NOT IS_CHAR_DEAD croupier
				GET_SCRIPT_TASK_STATUS croupier TASK_PLAY_ANIM temp_int
				IF temp_int = FINISHED_TASK
					m_goals++
				ENDIF
			ENDIF
		ENDIF  
		// give dealer next card
		IF m_goals = 4
			temp_int = 0
			WHILE temp_int < 8
				IF dealers_cards[temp_int] = 0
					dealers_cards[temp_int] = next_card
					//GOSUB bj_say_player_total
					temp_int = 8
				ENDIF
			temp_int++
			ENDWHILE
			// go back to begining of stage
			m_goals = 0
		ENDIF

		IF m_goals = 99
			m_stage++
			m_goals = 0
		ENDIF

		GOSUB calculate_totals
		GOSUB display_cards
		GOSUB bj_draw_window_2

RETURN			   

// *************************************************************************************************************
//								STAGE 6 - Determine who wins 
// *************************************************************************************************************	
bj_m_stage_6:
	

		// do players totals
		IF m_goals = 0
			IF player_has_split = 0
				
				// check player is not bust
				IF NOT player_total1 > 21
					// if dealer is bust
					IF dealer_total > 21
						player_has_won1 = 1
					ELSE
						IF player_total1 > dealer_total 
							player_has_won1 = 1
						ELSE
							IF player_total1 = dealer_total
								IF player_got_bj = 1
							   		IF dealer_got_bj = 1
										player_has_won1 = 2
									ELSE
										player_has_won1 = 1
									ENDIF
								ELSE	
									IF dealer_got_bj = 1
										player_has_won1 = 0
									ELSE	
										player_has_won1 = 2
									ENDIF
								ENDIF
							ELSE
								player_has_won1 = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			ELSE

				// check player is not bust
				IF NOT player_total1 > 21
					// if dealer is bust
					IF dealer_total > 21
						player_has_won1 = 1
					ELSE
						IF player_total1 > dealer_total 
							player_has_won1 = 1
						ELSE
							IF player_total1 = dealer_total

								IF player_got_bj = 2
								OR player_got_bj = 4
							   		IF dealer_got_bj = 1
										player_has_won1 = 2
									ELSE
										player_has_won1 = 1
									ENDIF
								ELSE	
									IF dealer_got_bj = 1
										player_has_won1 = 0
									ELSE	
										player_has_won1 = 2
									ENDIF
								ENDIF
 
							ELSE
								player_has_won1 = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// check player is not bust
				IF NOT player_total2 > 21
					// if dealer is bust
					IF dealer_total > 21
						player_has_won2 = 1
					ELSE
						IF player_total2 > dealer_total 
							player_has_won2 = 1
						ELSE
							IF player_total2 = dealer_total

								IF player_got_bj = 3
								OR player_got_bj = 4
							   		IF dealer_got_bj = 1
										player_has_won2 = 2
									ELSE
										player_has_won2 = 1
									ENDIF
								ELSE	
									IF dealer_got_bj = 1
										player_has_won2 = 0
									ELSE	
										player_has_won2 = 2
									ENDIF
								ENDIF

							ELSE
								player_has_won2 = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			TIMERA = 0

			// workout payout
			bj_payout = 0
			IF player_has_split = 0
				IF player_has_won1 = 1 // player won
					IF player_got_bj = 1
						bj_payout = 3 * bj_bet1
					ELSE
						bj_payout = 2 * bj_bet1
					ENDIF
					ELSE
					IF player_has_won1 = 2 // player draws
						bj_payout = bj_bet1
					ENDIF
				ENDIF
			ELSE
				// 1
				IF player_has_won1 = 1 // player won
					IF player_got_bj = 2
					OR player_got_bj = 4
						bj_payout = 3 * bj_bet1
					ELSE
						bj_payout = 2 * bj_bet1
					ENDIF
				ELSE
					IF player_has_won1 = 2 // player draws
					  	bj_payout = bj_bet1
					ENDIF
				ENDIF
				// 2
				IF player_has_won2 = 1 // player won
					IF player_got_bj = 3
					OR player_got_bj = 4
						temp_int = bj_bet2 * 3
						bj_payout += temp_int
					ELSE
						temp_int = bj_bet2 * 2
						bj_payout += temp_int
					ENDIF
				ELSE
					IF player_has_won2 = 2 // player draws
						bj_payout += bj_bet2
					ENDIF
				ENDIF
			ENDIF

			// pay player
			temp_int = bj_payout - bj_initial_stake
			INCREMENT_INT_STAT MONEY_WON_GAMBLING temp_int
			ADD_SCORE player1 bj_payout
			//racetour_flag = 69
			START_NEW_SCRIPT display_win_text temp_int 3000	69
			SHOW_UPDATE_STATS FALSE
			IF temp_int > 0

				REGISTER_INT_STAT BIGGEST_GAMBLING_WIN temp_int

				temp_float  =# temp_int 
				temp_float2 =# max_bet
				temp_float /= temp_float2

				winning_streak++
				IF winning_streak < 5

					IF NOT IS_CHAR_DEAD croupier 
						IF temp_float > 0.8
							GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
						ENDIF
						SWITCH temp_int
							CASE 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_PWIN1 TRUE TRUE FALSE
							BREAK
							CASE 1
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_PWIN2 TRUE TRUE FALSE
							BREAK
							CASE 2
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_PWIN3 TRUE TRUE FALSE
							BREAK
							CASE 3
								IF IS_PLAYER_PLAYING player1
									SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_GAMB_CASINO_WIN	temp_int
								ENDIF
							BREAK
						ENDSWITCH 
					ENDIF

				ELSE
					IF NOT IS_CHAR_DEAD croupier 
						GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
						SWITCH temp_int
							CASE 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_WINS1 TRUE TRUE FALSE
							BREAK
							CASE 1
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_WINS2 TRUE TRUE FALSE
							BREAK
							CASE 2
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_WINS3 TRUE TRUE FALSE
							BREAK
						ENDSWITCH 
					ENDIF
					winning_streak = 0
				ENDIF
			ELSE
				temp_int *= -1
				REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS temp_int
				temp_int *= -1

				winning_streak = 0
				
				temp_float  =# temp_int
				temp_float *= -1.0 
				temp_float2 =# max_bet
				temp_float /= temp_float2

				IF NOT IS_CHAR_DEAD croupier
					IF temp_int < 0
						IF temp_float > 0.8
							GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
						ENDIF
						SWITCH temp_int
							CASE 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_DW_1 TRUE TRUE FALSE
							BREAK
							CASE 1
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_DW_2 TRUE TRUE FALSE
							BREAK
							CASE 2
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_DW_3 TRUE TRUE FALSE
							BREAK
							CASE 3
								IF IS_PLAYER_PLAYING player1
									SET_CHAR_SAY_CONTEXT scplayer	CONTEXT_GLOBAL_GAMB_CASINO_LOSE	temp_int
								ENDIF
							BREAK
						ENDSWITCH
					ELSE
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						SWITCH temp_int
							CASE 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_DRW_1 TRUE TRUE FALSE
							BREAK
							CASE 1
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_DRW_2 TRUE TRUE FALSE
							BREAK
						ENDSWITCH
					ENDIF
				ENDIF
			ENDIF
			SHOW_UPDATE_STATS TRUE

			bj_initial_stake = 0
			GOSUB bj_generate_chip_stack

			m_goals++
		ENDIF

		// display who wins and loses
		IF m_goals = 1

			IF TIMERA > 3000
				m_goals++
			
			ELSE
				IF cross_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 1
						m_goals++
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						cross_is_pressed = 0
					ENDIF
				ENDIF

			ENDIF
		ENDIF

		// pay out bet
		IF m_goals = 2
			TIMERA = 0
			m_goals++			
		ENDIF

		// wait a couple of ticks before starting next hand
		IF m_goals = 3
			
			IF IS_BUTTON_PRESSED PAD1 CROSS
				cross_is_pressed = 1
				m_goals = 99
			ELSE
				IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
				AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						SET_HELP_MESSAGE_BOX_SIZE 128
						PRINT_HELP_FOREVER BJ_H4
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// go back to start for new hand
		IF m_goals = 99
			GOSUB bj_clear_help
			m_stage = 1
			m_goals = 0
		ENDIF

		GOSUB display_cards
		GOSUB bj_draw_window_2
	
RETURN



// *************************************************************************************************************
// *************************************************************************************************************
// *************************************************************************************************************
//							   			GOSUBS 
// *************************************************************************************************************	
// *************************************************************************************************************
// *************************************************************************************************************

bj_not_enough_cash:

	IF NOT IS_CHAR_DEAD croupier
		GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int	

		//WRITE_DEBUG_WITH_INT not_enough_cash temp_int
		SWITCH temp_int
			CASE 0
				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NEM_1 TRUE TRUE FALSE		
			BREAK
			CASE 1
				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NEM_2 TRUE TRUE FALSE
			BREAK
			CASE 2
				SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NEM_3 TRUE TRUE FALSE
			BREAK
		ENDSWITCH
	ENDIF

RETURN


bj_say_player_total:


	GOSUB calculate_totals

	IF NOT IS_CHAR_DEAD croupier

		IF player_has_split  = 0

			IF player_total1_b = 0

				SWITCH player_total1
					CASE 4
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_4 TRUE TRUE FALSE
					BREAK
					CASE 5
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_5 TRUE TRUE FALSE
					BREAK
					CASE 6
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_6 TRUE TRUE FALSE
					BREAK
					CASE 7
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_7 TRUE TRUE FALSE
					BREAK
					CASE 8
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_8 TRUE TRUE FALSE
					BREAK
					CASE 9
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_9 TRUE TRUE FALSE
					BREAK
					CASE 10
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM10 TRUE TRUE FALSE
					BREAK
					CASE 11
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM11 TRUE TRUE FALSE
					BREAK
					CASE 12
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM12 TRUE TRUE FALSE
					BREAK
					CASE 13
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM13 TRUE TRUE FALSE
					BREAK
					CASE 14
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM14 TRUE TRUE FALSE
					BREAK
					CASE 15
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM15 TRUE TRUE FALSE
					BREAK
					CASE 16
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM16 TRUE TRUE FALSE
					BREAK
					CASE 17
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM17 TRUE TRUE FALSE
					BREAK
					CASE 18
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM18 TRUE TRUE FALSE
					BREAK
					CASE 19
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM19 TRUE TRUE FALSE
					BREAK
					CASE 20
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM20 TRUE TRUE FALSE
					BREAK
					CASE 21
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM21 TRUE TRUE FALSE
					BREAK
					DEFAULT
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						IF temp_int = 0
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
						ELSE
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
						ENDIF
					BREAK
				ENDSWITCH

				START_CHAR_FACIAL_TALK croupier 1500

			ELSE

				SWITCH player_total1
					CASE 12
						SET_CHAR_SAY_SCRIPT	croupier SOUND_2_OR_12 TRUE TRUE FALSE
					BREAK
					CASE 13
						SET_CHAR_SAY_SCRIPT	croupier SOUND_3_OR_13 TRUE TRUE FALSE
					BREAK
					CASE 14
						SET_CHAR_SAY_SCRIPT	croupier SOUND_4_OR_14 TRUE TRUE FALSE
					BREAK
					CASE 15
						SET_CHAR_SAY_SCRIPT	croupier SOUND_5_OR_15 TRUE TRUE FALSE
					BREAK
					CASE 16
						SET_CHAR_SAY_SCRIPT	croupier SOUND_6_OR_16 TRUE TRUE FALSE
					BREAK
					CASE 17
						SET_CHAR_SAY_SCRIPT	croupier SOUND_7_OR_17 TRUE TRUE FALSE
					BREAK
					CASE 18
						SET_CHAR_SAY_SCRIPT	croupier SOUND_8_OR_18 TRUE TRUE FALSE
					BREAK
					CASE 19
						SET_CHAR_SAY_SCRIPT	croupier SOUND_9_OR_19 TRUE TRUE FALSE
					BREAK
					CASE 20
						SET_CHAR_SAY_SCRIPT	croupier SOUND_10_OR_20 TRUE TRUE FALSE
					BREAK
					CASE 21
						SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_21 TRUE TRUE FALSE
					BREAK
					DEFAULT
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						IF temp_int = 0
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
						ELSE
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
						ENDIF
					BREAK
				ENDSWITCH

				START_CHAR_FACIAL_TALK croupier 3000
			
			ENDIF

		ELSE

			IF player_has_stuck1 = 0

				IF player_total1_b = 0

					SWITCH player_total1
						CASE 4
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_4 TRUE TRUE FALSE
						BREAK
						CASE 5
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_5 TRUE TRUE FALSE
						BREAK
						CASE 6
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_6 TRUE TRUE FALSE
						BREAK
						CASE 7
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_7 TRUE TRUE FALSE
						BREAK
						CASE 8
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_8 TRUE TRUE FALSE
						BREAK
						CASE 9
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_9 TRUE TRUE FALSE
						BREAK
						CASE 10
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM10 TRUE TRUE FALSE
						BREAK
						CASE 11
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM11 TRUE TRUE FALSE
						BREAK
						CASE 12
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM12 TRUE TRUE FALSE
						BREAK
						CASE 13
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM13 TRUE TRUE FALSE
						BREAK
						CASE 14
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM14 TRUE TRUE FALSE
						BREAK
						CASE 15
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM15 TRUE TRUE FALSE
						BREAK
						CASE 16
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM16 TRUE TRUE FALSE
						BREAK
						CASE 17
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM17 TRUE TRUE FALSE
						BREAK
						CASE 18
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM18 TRUE TRUE FALSE
						BREAK
						CASE 19
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM19 TRUE TRUE FALSE
						BREAK
						CASE 20
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM20 TRUE TRUE FALSE
						BREAK
						CASE 21
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM21 TRUE TRUE FALSE
						BREAK
						DEFAULT
							GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
							IF temp_int = 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
							ELSE
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
							ENDIF
						BREAK
					ENDSWITCH

					START_CHAR_FACIAL_TALK croupier 1500

				ELSE

					SWITCH player_total1
						CASE 12
							SET_CHAR_SAY_SCRIPT	croupier SOUND_2_OR_12 TRUE TRUE FALSE
						BREAK
						CASE 13
							SET_CHAR_SAY_SCRIPT	croupier SOUND_3_OR_13 TRUE TRUE FALSE
						BREAK
						CASE 14
							SET_CHAR_SAY_SCRIPT	croupier SOUND_4_OR_14 TRUE TRUE FALSE
						BREAK
						CASE 15
							SET_CHAR_SAY_SCRIPT	croupier SOUND_5_OR_15 TRUE TRUE FALSE
						BREAK
						CASE 16
							SET_CHAR_SAY_SCRIPT	croupier SOUND_6_OR_16 TRUE TRUE FALSE
						BREAK
						CASE 17
							SET_CHAR_SAY_SCRIPT	croupier SOUND_7_OR_17 TRUE TRUE FALSE
						BREAK
						CASE 18
							SET_CHAR_SAY_SCRIPT	croupier SOUND_8_OR_18 TRUE TRUE FALSE
						BREAK
						CASE 19
							SET_CHAR_SAY_SCRIPT	croupier SOUND_9_OR_19 TRUE TRUE FALSE
						BREAK
						CASE 20
							SET_CHAR_SAY_SCRIPT	croupier SOUND_10_OR_20 TRUE TRUE FALSE
						BREAK
						CASE 21
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_21 TRUE TRUE FALSE
						BREAK
						DEFAULT
							GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
							IF temp_int = 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
							ELSE
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
							ENDIF
						BREAK
					ENDSWITCH

					START_CHAR_FACIAL_TALK croupier 3000

				ENDIF

			ELSE

				IF player_total2_b = 0

					SWITCH player_total2
						CASE 4
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_4 TRUE TRUE FALSE
						BREAK
						CASE 5
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_5 TRUE TRUE FALSE
						BREAK
						CASE 6
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_6 TRUE TRUE FALSE
						BREAK
						CASE 7
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_7 TRUE TRUE FALSE
						BREAK
						CASE 8
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_8 TRUE TRUE FALSE
						BREAK
						CASE 9
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_9 TRUE TRUE FALSE
						BREAK
						CASE 10
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM10 TRUE TRUE FALSE
						BREAK
						CASE 11
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM11 TRUE TRUE FALSE
						BREAK
						CASE 12
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM12 TRUE TRUE FALSE
						BREAK
						CASE 13
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM13 TRUE TRUE FALSE
						BREAK
						CASE 14
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM14 TRUE TRUE FALSE
						BREAK
						CASE 15
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM15 TRUE TRUE FALSE
						BREAK
						CASE 16
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM16 TRUE TRUE FALSE
						BREAK
						CASE 17
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM17 TRUE TRUE FALSE
						BREAK
						CASE 18
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM18 TRUE TRUE FALSE
						BREAK
						CASE 19
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM19 TRUE TRUE FALSE
						BREAK
						CASE 20
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM20 TRUE TRUE FALSE
						BREAK
						CASE 21
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM21 TRUE TRUE FALSE
						BREAK
						DEFAULT
							GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
							IF temp_int = 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
							ELSE
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
							ENDIF
						BREAK
					ENDSWITCH

					START_CHAR_FACIAL_TALK croupier 1500

				ELSE

					SWITCH player_total2
						CASE 12
							SET_CHAR_SAY_SCRIPT	croupier SOUND_2_OR_12 TRUE TRUE FALSE
						BREAK
						CASE 13
							SET_CHAR_SAY_SCRIPT	croupier SOUND_3_OR_13 TRUE TRUE FALSE
						BREAK
						CASE 14
							SET_CHAR_SAY_SCRIPT	croupier SOUND_4_OR_14 TRUE TRUE FALSE
						BREAK
						CASE 15
							SET_CHAR_SAY_SCRIPT	croupier SOUND_5_OR_15 TRUE TRUE FALSE
						BREAK
						CASE 16
							SET_CHAR_SAY_SCRIPT	croupier SOUND_6_OR_16 TRUE TRUE FALSE
						BREAK
						CASE 17
							SET_CHAR_SAY_SCRIPT	croupier SOUND_7_OR_17 TRUE TRUE FALSE
						BREAK
						CASE 18
							SET_CHAR_SAY_SCRIPT	croupier SOUND_8_OR_18 TRUE TRUE FALSE
						BREAK
						CASE 19
							SET_CHAR_SAY_SCRIPT	croupier SOUND_9_OR_19 TRUE TRUE FALSE
						BREAK
						CASE 20
							SET_CHAR_SAY_SCRIPT	croupier SOUND_10_OR_20 TRUE TRUE FALSE
						BREAK
						CASE 21
							SET_CHAR_SAY_SCRIPT	croupier SOUND_J_NUM_21 TRUE TRUE FALSE
						BREAK
						DEFAULT
							GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
							IF temp_int = 0
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST1 TRUE TRUE FALSE
							ELSE
								SET_CHAR_SAY_SCRIPT	croupier SOUND_J_BUST2 TRUE TRUE FALSE
							ENDIF
						BREAK
					ENDSWITCH

					START_CHAR_FACIAL_TALK croupier 3000

				ENDIF


			ENDIF

		ENDIF

	ENDIF // is croupier dead

RETURN



VAR_FLOAT bj_chip_off_x[8] bj_chip_off_y[8] bj_chip_off_z[8]
VAR_INT bj_chip_num
bj_move_chip_set:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
		bj_chip_num++
		IF bj_chip_num > 7
			bj_chip_num = 0
		ENDIF
		WRITE_DEBUG_WITH_INT bj_chip_num bj_chip_num
	ENDIF

	IF DOES_OBJECT_EXIST table
		IF DOES_OBJECT_EXIST bj_chip_set[bj_chip_num]
			
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table bj_chip_off_x[bj_chip_num]	bj_chip_off_y[bj_chip_num] bj_chip_off_z[bj_chip_num] x y z
			SET_OBJECT_COORDINATES bj_chip_set[bj_chip_num] x y z

			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
				bj_chip_off_z[bj_chip_num] += 0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
				bj_chip_off_z[bj_chip_num] += -0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
				bj_chip_off_x[bj_chip_num] += 0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
				bj_chip_off_x[bj_chip_num] += -0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
				bj_chip_off_y[bj_chip_num] += 0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
				bj_chip_off_y[bj_chip_num] += -0.005	
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_E
				bj_chip_set_default_z += 0.005
			ENDIF
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
				bj_chip_set_default_z += -0.005
			ENDIF

		ENDIF
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "// ------ BJ CHIP OFFSETS -------"
		SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int = 0
		WHILE temp_int < 8
			SAVE_STRING_TO_DEBUG_FILE "bj_chip_off_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bj_chip_off_x[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "bj_chip_off_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bj_chip_off_y[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "bj_chip_off_z["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bj_chip_off_z[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int++
		ENDWHILE

	ENDIF

RETURN

bj_generate_chip_stack:

	temp_int3 = bj_initial_stake

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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -1
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[0]
		GET_OBJECT_COORDINATES bj_chip_set[0] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[0] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -5
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[1]
		GET_OBJECT_COORDINATES bj_chip_set[1] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[1] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -10
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[2]
		GET_OBJECT_COORDINATES bj_chip_set[2] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[2] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -50
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[3]
		GET_OBJECT_COORDINATES bj_chip_set[3] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[3] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -100
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[4]
		GET_OBJECT_COORDINATES bj_chip_set[4] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[4] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -200
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[5]
		GET_OBJECT_COORDINATES bj_chip_set[5] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[5] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -500
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[6]
		GET_OBJECT_COORDINATES bj_chip_set[6] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[6] x y z	
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
		temp_float2 += 0.0
	ENDIF
	temp_float = bj_chip_set_default_z + temp_float2
	temp_int2 *= -1000
	temp_int3 += temp_int2	
	IF DOES_OBJECT_EXIST bj_chip_set[7]
		GET_OBJECT_COORDINATES bj_chip_set[7] x y z
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS table 0.0 0.0 temp_float temp_float	temp_float z
		SET_OBJECT_COORDINATES bj_chip_set[7] x y z	
	ENDIF

RETURN

bj_draw_window_1:

	SET_SPRITES_DRAW_BEFORE_FADE FALSE 
	DRAW_WINDOW SCREEN_POS_X[0] SCREEN_POS_Y[0] SCREEN_POS_X[1] SCREEN_POS_Y[1] BJ_TITL SWIRLS_RIGHT 

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT SCREEN_POS_X[2] SCREEN_POS_Y[2] BJ_01

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[3] SCREEN_POS_Y[3] DOLLAR bj_initial_stake 

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT SCREEN_POS_X[4] SCREEN_POS_Y[4] BJ_02

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[5] SCREEN_POS_Y[5] DOLLAR min_bet 

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT SCREEN_POS_X[6] SCREEN_POS_Y[6] BJ_03

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[7] SCREEN_POS_Y[7] DOLLAR max_bet 

RETURN

bj_draw_window_2:
    
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	DRAW_WINDOW SCREEN_POS_X[8] SCREEN_POS_Y[8] SCREEN_POS_X[9] SCREEN_POS_Y[9] BJ_TITL SWIRLS_RIGHT 

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT SCREEN_POS_X[10] SCREEN_POS_Y[10] BJ_01

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_SCALE 0.6253 2.7876 
	DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[11] SCREEN_POS_Y[11] DOLLAR bj_initial_stake 

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255 
	DISPLAY_TEXT SCREEN_POS_X[12] SCREEN_POS_Y[12] BJ_04

	GOSUB bj_text 
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_SCALE 0.6253 2.7876 

	
	////////////////////////////////////// dealer's hand //////////////////////////////////////

	IF m_stage > 4
		IF dealer_total2 = 0
			IF dealer_total > 21
				DISPLAY_TEXT SCREEN_POS_X[13] SCREEN_POS_Y[13] BJ_BUST
			ELSE										
				DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[13] SCREEN_POS_Y[13] BJ_0 dealer_total 
			ENDIF												   
		ELSE									
			DISPLAY_TEXT_WITH_2_NUMBERS SCREEN_POS_X[14] SCREEN_POS_Y[14] BJ_OR2 dealer_total dealer_total2
		ENDIF													   
	ELSE														   
		DISPLAY_TEXT SCREEN_POS_X[13] SCREEN_POS_Y[13] BJ_HIDE
	ENDIF
	

	////////////////////////////////////// single hand //////////////////////////////////////
	GOSUB bj_text
	SET_SPRITES_DRAW_BEFORE_FADE FALSE
	SET_TEXT_COLOUR 134 155 184 255
	DISPLAY_TEXT SCREEN_POS_X[15] SCREEN_POS_Y[15] BJ_05

	IF player_total2 = 0
		GOSUB bj_text
		SET_SPRITES_DRAW_BEFORE_FADE FALSE
		SET_TEXT_SCALE 0.6253 2.7876
		
		IF player_total1_b = 0
			IF player_total1 > 21
				DISPLAY_TEXT SCREEN_POS_X[16] SCREEN_POS_Y[16] BJ_BUST
			ELSE 													 
				DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[16] SCREEN_POS_Y[16] BJ_0 player_total1
			ENDIF													 
		ELSE														 
			DISPLAY_TEXT_WITH_2_NUMBERS SCREEN_POS_X[17] SCREEN_POS_Y[17] BJ_OR2 player_total1 player_total1_b
		ENDIF
	
	////////////////////////////////////// split hands //////////////////////////////////////
	
	ELSE
		
		////////////////////////////////////// top hand //////////////////////////////////////

		GOSUB bj_text
		SET_SPRITES_DRAW_BEFORE_FADE FALSE
		SET_TEXT_SCALE 0.6253 2.7876

		IF player_total1_b = 0
			
			IF player_total1 > 21
				DISPLAY_TEXT SCREEN_POS_X[18] SCREEN_POS_Y[18] BJ_BUST
			ELSE
				DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[18] SCREEN_POS_Y[18] BJ_0 player_total1
			ENDIF
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS SCREEN_POS_X[19] SCREEN_POS_Y[19] BJ_OR2 player_total1 player_total1_b
		ENDIF
		
		////////////////////////////////////// bottom hand //////////////////////////////////////

		GOSUB bj_text
		SET_SPRITES_DRAW_BEFORE_FADE FALSE
		SET_TEXT_SCALE 0.6253 2.7876
		
		IF player_total2_b = 0
			
			IF player_total2 > 21
				DISPLAY_TEXT SCREEN_POS_X[20] SCREEN_POS_Y[20] BJ_BUST
			ELSE
				DISPLAY_TEXT_WITH_NUMBER SCREEN_POS_X[20] SCREEN_POS_Y[20] BJ_0 player_total2
			ENDIF
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS SCREEN_POS_X[21] SCREEN_POS_Y[21] BJ_OR2 player_total2 player_total2_b
		ENDIF		
	ENDIF

RETURN



VAR_INT screen_pos
bj_edit_screen_position:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_L
		screen_pos++
		IF screen_pos > 26
			screen_pos = -1
		ENDIF
		WRITE_DEBUG_WITH_INT SCREEN_POSITION_EDIT screen_pos
	ENDIF

	IF screen_pos > -1
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			screen_pos_x[screen_pos] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			screen_pos_x[screen_pos] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			screen_pos_y[screen_pos] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			screen_pos_y[screen_pos] += 1.0
		ENDIF
	ENDIF
	IF screen_pos > -1
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER

				SAVE_NEWLINE_TO_DEBUG_FILE 
				SAVE_STRING_TO_DEBUG_FILE "------ SCREEN COORDS ------"

			temp_int = 0
			WHILE temp_int < 27
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "screen_pos_x["
				SAVE_INT_TO_DEBUG_FILE temp_int
				SAVE_STRING_TO_DEBUG_FILE "] = "
				SAVE_FLOAT_TO_DEBUG_FILE screen_pos_x[temp_int] 
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "screen_pos_y["
				SAVE_INT_TO_DEBUG_FILE temp_int
				SAVE_STRING_TO_DEBUG_FILE "] = "
				SAVE_FLOAT_TO_DEBUG_FILE screen_pos_y[temp_int]

			temp_int++
			ENDWHILE

				SAVE_NEWLINE_TO_DEBUG_FILE
		ENDIF
	ENDIF

RETURN


// ************************************************************
// 				CHANGE STUFF ON THE FLY
// ************************************************************
VAR_INT bj_adjust_mode
bj_adjust_sizes:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B
		bj_adjust_mode++
		IF bj_adjust_mode > 7
			bj_adjust_mode = 0
		ENDIF

		IF bj_adjust_mode = 1
			WRITE_DEBUG EDIT_CARD_SIZE
		ENDIF 
		IF bj_adjust_mode = 2
			WRITE_DEBUG EDIT_CARD_POSITIONS_X
		ENDIF 
		IF bj_adjust_mode = 3
			WRITE_DEBUG EDIT_PLAYERS_CARD_POSITION_1
		ENDIF 
		IF bj_adjust_mode = 4
			WRITE_DEBUG EDIT_PLAYERS_CARD_POSITION_1B
		ENDIF 
		IF bj_adjust_mode = 5
			WRITE_DEBUG EDIT_PLAYERS_CARD_POSITION_2
		ENDIF 
		IF bj_adjust_mode = 6
			WRITE_DEBUG EDIT_DEALERS_CARD_POSITION
		ENDIF 
		IF bj_adjust_mode = 7
			WRITE_DEBUG EDIT_border_width 
		ENDIF


	ENDIF

	// adjust size of card
	IF bj_adjust_mode = 1
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			card_height += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			card_height += -1.0
			IF card_height < 1.0
				card_height = 1.0
			ENDIF
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			card_width += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			card_width += -1.0
			IF card_width < 1.0
				card_width = 1.0
			ENDIF
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF

	// shift x position of cards
	IF bj_adjust_mode = 2
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			player_sprite1_x[0] += 1.0
			player_sprite1b_x[0] += 1.0
			player_sprite2_x[0]	+= 1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			player_sprite1_x[0] += -1.0
			player_sprite1b_x[0] += -1.0
			player_sprite2_x[0]	+= -1.0
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF

	// shift y position of players cards 1
	IF bj_adjust_mode = 3
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			player_sprite1_y[0] += -1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			player_sprite1_y[0] += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF

	// shift y position of players cards 1b
	IF bj_adjust_mode = 4
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			player_sprite1b_y[0] += -1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			player_sprite1b_y[0] += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF
	
	// shift y position of players cards 2
	IF bj_adjust_mode = 5
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			player_sprite2_y[0] += -1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			player_sprite2_y[0] += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF
	
	// shift position of dealers cards
	IF bj_adjust_mode = 6
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			dealer_sprite_x[0] += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			dealer_sprite_x[0] += -1.0
			GOSUB bj_update_screen_positions
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			dealer_sprite_y[0] += -1.0
			GOSUB bj_update_screen_positions
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			dealer_sprite_y[0] += 1.0
			GOSUB bj_update_screen_positions
		ENDIF
	ENDIF	

	// increase border width
	IF bj_adjust_mode = 7
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			border_width += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			border_width += -1.0
		ENDIF
	ENDIF

	// output everything to text file
	IF bj_adjust_mode > 0
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "----- BLACKJACK SCREEN COORDS ------"
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "card_width = "
			SAVE_FLOAT_TO_DEBUG_FILE card_width
			SAVE_NEWLINE_TO_DEBUG_FILE 
			SAVE_STRING_TO_DEBUG_FILE "card_height = "
			SAVE_FLOAT_TO_DEBUG_FILE card_height
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite1_x[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite1_x[0]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite1_y[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite1_y[0]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite1b_x[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite1b_x[0]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite1b_y[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite1b_y[0] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite2_x[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite2_x[0] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "player_sprite2_y[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE player_sprite2_y[0]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "dealer_sprite_x[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE dealer_sprite_x[0] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "dealer_sprite_y[0] = "
			SAVE_FLOAT_TO_DEBUG_FILE dealer_sprite_y[0]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "border_width = "
			SAVE_FLOAT_TO_DEBUG_FILE border_width
			SAVE_NEWLINE_TO_DEBUG_FILE

		ENDIF
	ENDIF

RETURN


// ************************************************************
// 				DEAL A CARD
// ************************************************************
	//VAR_INT card_delt
	VAR_INT card_cheat_on
	deal_card:
		IF card_cheat_on = 0
			next_card = 0
			FETCH_NEXT_CARD next_card
		ELSE
			next_card = card_cheat_value
		ENDIF
		IF NOT IS_CHAR_DEAD croupier
			TASK_PLAY_ANIM croupier dealone casino 6.0 FALSE FALSE FALSE FALSE 500
		ENDIF
	RETURN


// ************************************************************
// 		   DISPLAY CARDS
// ************************************************************
	VAR_INT counter_disp
	display_cards:

		IF NOT IS_MESSAGE_BEING_DISPLAYED

			// draw card window 
			IF player_has_split = 0
				// min x
				temp_float = card_width / 2.0
				x = player_sprite1_x[0] - temp_float
				x -= border_width 
				// min y
				temp_float = card_height / 2.0
				y = player_sprite1_y[0] - temp_float
				y -= border_width
				// max x
				temp_float = card_width / 2.0
				vec_x = player_sprite1_x[4] + temp_float
				vec_x += border_width
				// max y
				temp_float = card_height / 2.0
				vec_y = player_sprite1_y[4] + temp_float
				vec_y += border_width
				SET_SPRITES_DRAW_BEFORE_FADE TRUE
				DRAW_WINDOW x y vec_x vec_y	DUMMY SWIRLS_NONE
			ELSE
				// min x
				temp_float = card_width / 2.0
				x = player_sprite1b_x[0] - temp_float
				x -= border_width 
				// min y
				temp_float = card_height / 2.0
				y = player_sprite1b_y[0] - temp_float
				y -= border_width
				// max x
				temp_float = card_width / 2.0
				vec_x = player_sprite2_x[4] + temp_float
				vec_x += border_width
				// max y
				temp_float = card_height / 2.0
				vec_y = player_sprite2_y[4] + temp_float
				vec_y += border_width
				SET_SPRITES_DRAW_BEFORE_FADE TRUE
				DRAW_WINDOW x y vec_x vec_y	DUMMY SWIRLS_NONE
			ENDIF

   

			IF  NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO

				// draw dealer card widow
				// min x
				temp_float = card_width / 2.0
				x = dealer_sprite_x[0] - temp_float
				x -= border_width 
				// min y
				temp_float = card_height / 2.0
				y = dealer_sprite_y[0] - temp_float
				y -= border_width
				// max x
				temp_float = card_width / 2.0
				vec_x = dealer_sprite_x[4] + temp_float
				vec_x += border_width
				// max y
				temp_float = card_height / 2.0
				vec_y = dealer_sprite_y[4] + temp_float
				vec_y += border_width
				SET_SPRITES_DRAW_BEFORE_FADE TRUE
				DRAW_WINDOW x y vec_x vec_y	DUMMY SWIRLS_NONE
				
			ENDIF	

			counter_disp = 0
			WHILE counter_disp < 8
				// players cards
				IF player_has_split = 0
					IF NOT players_cards1[counter_disp] = 0 
						SET_SPRITES_DRAW_BEFORE_FADE TRUE
						DRAW_SPRITE players_cards1[counter_disp] player_sprite1_x[counter_disp] player_sprite1_y[counter_disp] card_width card_height 128 128 128 255
					ENDIF
				ELSE
					IF NOT players_cards1[counter_disp] = 0 
						SET_SPRITES_DRAW_BEFORE_FADE TRUE
						DRAW_SPRITE players_cards1[counter_disp] player_sprite1b_x[counter_disp] player_sprite1b_y[counter_disp] card_width card_height 128 128 128 255
					ENDIF
					IF NOT players_cards2[counter_disp] = 0 
						SET_SPRITES_DRAW_BEFORE_FADE TRUE
						DRAW_SPRITE players_cards2[counter_disp] player_sprite2_x[counter_disp] player_sprite2_y[counter_disp] card_width card_height 128 128 128 255
					ENDIF
				ENDIF

			IF  NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
			AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO

				// dealers cards
				IF NOT dealers_cards[counter_disp] = 0
					// if player has not stuck, dealers first card should be covered.
					IF counter_disp = 0 
						IF player_has_split = 0
							IF player_has_stuck1 = 0
							//OR dealer_wins_by_default = 1
								SET_SPRITES_DRAW_BEFORE_FADE TRUE
								DRAW_SPRITE 53 dealer_sprite_x[counter_disp] dealer_sprite_y[counter_disp] card_width card_height 128 128 128 255
							ELSE
								SET_SPRITES_DRAW_BEFORE_FADE TRUE
								DRAW_SPRITE dealers_cards[counter_disp] dealer_sprite_x[counter_disp] dealer_sprite_y[counter_disp] card_width card_height 128 128 128 255
							ENDIF
						ELSE
							IF player_has_stuck2 = 0
							//OR dealer_wins_by_default = 1
								SET_SPRITES_DRAW_BEFORE_FADE TRUE
								DRAW_SPRITE 53 dealer_sprite_x[counter_disp] dealer_sprite_y[counter_disp] card_width card_height 128 128 128 255
							ELSE
								SET_SPRITES_DRAW_BEFORE_FADE TRUE
								DRAW_SPRITE dealers_cards[counter_disp] dealer_sprite_x[counter_disp] dealer_sprite_y[counter_disp] card_width card_height 128 128 128 255
							ENDIF
						ENDIF
					ELSE
						SET_SPRITES_DRAW_BEFORE_FADE TRUE
						DRAW_SPRITE dealers_cards[counter_disp] dealer_sprite_x[counter_disp] dealer_sprite_y[counter_disp] card_width card_height 128 128 128 255
					ENDIF
				ENDIF
			ELSE
				SET_HELP_MESSAGE_BOX_SIZE 200
			ENDIF
				
				
			counter_disp++
			ENDWHILE
		ENDIF
		  
	RETURN


// ************************************************************
// 				CALCULATE TOTALS
// ************************************************************
	// internal workings
	VAR_INT ct_temp_int
	VAR_INT ct_this_card_value
	calculate_totals:

		dealer_total = 0
		dealer_total2 = 0
		player_total1 = 0
		player_total1_b = 0
		player_total2 = 0
		player_total2_b = 0
		dealer_got_ace = 0
		player_got_ace1 = 0
		player_got_ace2 = 0

		// the first totals always take first ace as high, the second always take aces as low
		
		ct_temp_int = 0
		WHILE ct_temp_int < 8
			
			// dealer
			IF NOT dealers_cards[ct_temp_int] = 0
				ct_this_card_value = dealers_cards[ct_temp_int]
				in_card_value = ct_this_card_value
				GOSUB convert_card_value
				ct_this_card_value = out_card_value
				IF ct_this_card_value = 1
					IF dealer_got_ace = 0
						dealer_total += 11
						dealer_got_ace = 1
					ELSE
						dealer_total += 1
					ENDIF	
					dealer_total2 += 1
				ELSE
					dealer_total += ct_this_card_value
					dealer_total2 += ct_this_card_value	
				ENDIF 
			ENDIF


			// player - cards1
			IF NOT players_cards1[ct_temp_int] = 0
				ct_this_card_value = players_cards1[ct_temp_int]
				in_card_value = ct_this_card_value
				GOSUB convert_card_value
				ct_this_card_value = out_card_value
				IF ct_this_card_value = 1
					IF player_got_ace1 = 0
						player_total1 += 11
						player_got_ace1 = 1
					ELSE
						player_total1 += 1
					ENDIF	
					player_total1_b += 1
				ELSE
					player_total1 += ct_this_card_value
					player_total1_b += ct_this_card_value	
				ENDIF 
			ENDIF




			// player - cards2
			IF NOT players_cards2[ct_temp_int] = 0
				ct_this_card_value = players_cards2[ct_temp_int]
				in_card_value = ct_this_card_value
				GOSUB convert_card_value
				ct_this_card_value = out_card_value
				IF ct_this_card_value = 1
					IF player_got_ace2 = 0
						player_total2 += 11
						player_got_ace2 = 1
					ELSE
						player_total2 += 1
					ENDIF	
					player_total2_b += 1
				ELSE
					player_total2 += ct_this_card_value
					player_total2_b += ct_this_card_value	
				ENDIF 
			ENDIF


		ct_temp_int++
		ENDWHILE	

		IF dealer_total > dealer_total2
			IF dealer_total > 21
				dealer_total = dealer_total2
				dealer_total2 = 0
			ENDIF
			IF dealer_total = 21
				dealer_total2 = 0
			ENDIF
		ELSE
			dealer_total2 = 0
		ENDIF


		IF player_total1 > player_total1_b
			IF player_total1 > 21
				player_total1 = player_total1_b
				player_total1_b = 0
			ENDIF
			IF player_total1 = 21
				player_total1_b = 0
			ENDIF
		ELSE
			player_total1_b = 0
		ENDIF

		IF player_total2 > player_total2_b
			IF player_total2 > 21
				player_total2 = player_total2_b
				player_total2_b = 0
			ENDIF
			IF player_total2 = 21
				player_total2_b = 0
			ENDIF
		ELSE
			player_total2_b = 0
		ENDIF


	RETURN



// ************************************************************
// 				CONVERT CARD VALUE
// ************************************************************

	// Function for getting the value of a card
	VAR_INT in_card_value	// input
	VAR_INT out_card_value	// output
	convert_card_value:

		IF in_card_value < 14
			out_card_value = in_card_value
		ENDIF

		IF in_card_value <	27
		AND in_card_value > 13
			out_card_value = in_card_value - 13
		ENDIF 
		
		IF in_card_value <	40
		AND in_card_value > 26
			out_card_value = in_card_value - 26
		ENDIF 
		
		IF in_card_value <	53
		AND in_card_value > 39
			out_card_value = in_card_value - 39
		ENDIF
	
		IF out_card_value < 14
			IF out_card_value > 10
				out_card_value = 10
			ENDIF 
		ENDIF
		
	RETURN

	// Function for getting the value of a card for splits
	convert_card_value_for_splits:

		IF in_card_value < 14
			out_card_value = in_card_value
		ENDIF

		IF in_card_value <	27
		AND in_card_value > 13
			out_card_value = in_card_value - 13
		ENDIF 
		
		IF in_card_value <	40
		AND in_card_value > 26
			out_card_value = in_card_value - 26
		ENDIF 
		
		IF in_card_value <	53
		AND in_card_value > 39
			out_card_value = in_card_value - 39
		ENDIF
				 
	RETURN



// ***************************************************************
// 			update screen positions of text, cards etc.
// ***************************************************************
bj_update_screen_positions:

	player_sprite2_x[0]  = player_sprite1_x[0]
	player_sprite2_y[0]  = player_sprite1_y[0]
	player_sprite1b_x[0] = player_sprite1_x[0]
	player_sprite1b_y[0] = player_sprite1_y[0] - card_height
	player_sprite1b_y[0] -= border_width

	// fill in the rest of the card positions
	temp_int = 0
	WHILE temp_int < 8 
		
		IF temp_int < 5
			
			spacing_x = card_width + border_width
			multiplier =# temp_int
			multiplier *= spacing_x
			
			// players 1
			player_sprite1_x[temp_int] = player_sprite1_x[0] + multiplier
			player_sprite1_y[temp_int] = player_sprite1_y[0]

			// players 1b
			player_sprite1b_x[temp_int] = player_sprite1b_x[0] + multiplier
			player_sprite1b_y[temp_int] = player_sprite1b_y[0]

			// players 2
			player_sprite2_x[temp_int] = player_sprite2_x[0] + multiplier
			player_sprite2_y[temp_int] = player_sprite2_y[0]

			// dealer
			dealer_sprite_x[temp_int] = dealer_sprite_x[0] + multiplier
			dealer_sprite_y[temp_int] = dealer_sprite_y[0]

		ELSE

			// players 1
			player_sprite1_x[temp_int] = player_sprite1_x[4] 
			player_sprite1_y[temp_int] = player_sprite1_y[4]

			// players 1b
			player_sprite1b_x[temp_int] = player_sprite1b_x[4] 
			player_sprite1b_y[temp_int] = player_sprite1b_y[4]

			// players 2
			player_sprite2_x[temp_int] = player_sprite2_x[4] 
			player_sprite2_y[temp_int] = player_sprite2_y[4]

			// dealer
			dealer_sprite_x[temp_int] = dealer_sprite_x[4] 
			dealer_sprite_y[temp_int] = dealer_sprite_y[4]
			
		ENDIF

	temp_int++
	ENDWHILE
RETURN


VAR_FLOAT bj_x2 bj_y2 bj_z2
VAR_FLOAT bj_vec_x bj_vec_y bj_vec_z
VAR_FLOAT bj_vec2_x bj_vec2_y 
//bj_vec2_z
VAR_FLOAT bj_temp_float3
bj_find_camera_position:

	// for camera position
	GET_DEBUG_CAMERA_COORDINATES x y z
	IF DOES_OBJECT_EXIST table
		GET_OBJECT_COORDINATES table bj_x2 bj_y2 bj_z2
		GET_OBJECT_HEADING table heading
	ENDIF
	heading *= -1.0
	COS heading temp_float
	SIN heading temp_float2
	// get coords relative to car 
	bj_vec_x = x - bj_x2
	bj_vec_y = y - bj_y2
	bj_vec_z = z - bj_z2
	// work out new bj_vec_x
	bj_vec2_x = bj_vec_x * temp_float
	bj_temp_float3 = bj_vec_y * temp_float2
	bj_vec2_x -= bj_temp_float3
	// work out new bj_vec_y
	bj_vec2_y = bj_vec_x * temp_float2
	bj_temp_float3 = bj_vec_y * temp_float
	bj_vec2_y += bj_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec_z

	// for camera point at
	GET_DEBUG_CAMERA_POINT_AT x y z
	// get coords relative to car 
	bj_vec_x = x - bj_x2
	bj_vec_y = y - bj_y2
	bj_vec_z = z - bj_z2
	// work out new bj_vec_x
	bj_vec2_x = bj_vec_x * temp_float
	bj_temp_float3 = bj_vec_y * temp_float2
	bj_vec2_x -= bj_temp_float3
	// work out new bj_vec_y
	bj_vec2_y = bj_vec_x * temp_float2
	bj_temp_float3 = bj_vec_y * temp_float
	bj_vec2_y += bj_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec_z

RETURN


bj_find_player_position:

	// for camera position
	GET_CHAR_COORDINATES scplayer x y z
	IF DOES_OBJECT_EXIST table
		GET_OBJECT_COORDINATES table bj_x2 bj_y2 bj_z2
		GET_OBJECT_HEADING table heading
	ENDIF
	heading *= -1.0
	COS heading temp_float
	SIN heading temp_float2
	// get coords relative to car 
	bj_vec_x = x - bj_x2
	bj_vec_y = y - bj_y2
	bj_vec_z = z - bj_z2
	// work out new bj_vec_x
	bj_vec2_x = bj_vec_x * temp_float
	bj_temp_float3 = bj_vec_y * temp_float2
	bj_vec2_x -= bj_temp_float3
	// work out new bj_vec_y
	bj_vec2_y = bj_vec_x * temp_float2
	bj_temp_float3 = bj_vec_y * temp_float
	bj_vec2_y += bj_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "PLAYER_COORDINATES - OFFSET FROM table = "
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE bj_vec_z
RETURN


bj_update_casino_credit:

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

// ************************************************************
// 					 ONSCREEN TEXT
// ************************************************************
	blackj_onscreen_text:
		SET_TEXT_COLOUR 255 255 255 255
		SET_TEXT_JUSTIFY OFF
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_WRAPX 2000.0
		SET_TEXT_PROPORTIONAL ON
		SET_TEXT_CENTRE OFF
		SET_TEXT_BACKGROUND OFF
		SET_TEXT_SCALE 0.6 1.6
	RETURN

	blackj_onscreen_text_end:
		SET_TEXT_COLOUR 255 180 180 255
		SET_TEXT_JUSTIFY OFF
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_WRAPX 2000.0
		SET_TEXT_PROPORTIONAL ON
		SET_TEXT_CENTRE ON
		SET_TEXT_BACKGROUND OFF
		SET_TEXT_SCALE 2.0 3.0
	RETURN

	bj_text:
		SET_TEXT_COLOUR 180 180 180 255
		SET_TEXT_SCALE 0.4714 2.5077
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_JUSTIFY OFF
		SET_TEXT_CENTRE OFF
		SET_TEXT_WRAPX 640.0
		SET_TEXT_PROPORTIONAL ON
		SET_TEXT_BACKGROUND OFF
	RETURN

	bj_text_centre:
		SET_TEXT_COLOUR 180 180 180 255
		SET_TEXT_SCALE 0.4714 2.5077
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		SET_TEXT_WRAPX 640.0
		SET_TEXT_PROPORTIONAL ON
		SET_TEXT_BACKGROUND OFF
	RETURN

	bj_text_arrow:
		SET_TEXT_COLOUR 255 255 0 255
		SET_TEXT_EDGE 2 0 0 0 255
		SET_TEXT_SCALE SCREEN_POS_X[24] SCREEN_POS_Y[24]
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		SET_TEXT_WRAPX 640.0
		SET_TEXT_PROPORTIONAL ON
		SET_TEXT_BACKGROUND OFF
	RETURN

	bj_text_winner:
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

	bj_text_loser:
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

	bj_text_nowin:
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

	bj_text_winnings_small:
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

// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// CLEANUP
mission_cleanup_BLACKJ:

	SET_MINIGAME_IN_PROGRESS FALSE

	m_stage 	= 0
	m_goals 	= 0
	m_goals2    = 0
	m_quit		= 0

	IF  NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPUNT
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGGAMB
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGPROF
	AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SGHIRO
		CLEAR_HELP
	ENDIF

	//CLEAR_PRINTS
	RESTORE_CAMERA

	temp_int = 0
	WHILE temp_int < 8
		IF DOES_OBJECT_EXIST bj_chip_set[temp_int]
			DELETE_OBJECT bj_chip_set[temp_int]
		ENDIF
	temp_int++
	ENDWHILE
	
	IF NOT IS_CHAR_DEAD croupier
		TASK_STAY_IN_SAME_PLACE croupier TRUE
	ENDIF

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME cashwin

	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	
	REMOVE_ANIMATION CASINO

	REMOVE_TEXTURE_DICTIONARY 
	USE_TEXT_COMMANDS FALSE

	DISPLAY_RADAR TRUE
	SWITCH_RUBBISH ON

	SET_HELP_MESSAGE_BOX_SIZE 200

RETURN
}

MISSION_END

