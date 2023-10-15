MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION MISSNAM : VIDEO POKER
//				     AUTHOR : NEIL
//		       DESICRIPTION : Press buttons, win money.
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


// GLOBAL VARIABLES 
VAR_INT no_of_coins
VAR_INT card_value[5]
VAR_INT hold_status[5]
VAR_INT hold_value[5]
VAR_INT bet_one_value
VAR_INT deal_value
VAR_INT highlighted_button

VAR_INT hand_is_royal_flush
VAR_INT hand_is_straight_flush
VAR_INT hand_is_four_of_kind
VAR_INT hand_is_full_house
VAR_INT hand_is_flush
VAR_INT hand_is_straight
VAR_INT hand_is_three_of_kind
VAR_INT hand_is_two_pairs
VAR_INT hand_is_jacks_or_better

VAR_INT pair1									  
VAR_INT pair2
VAR_INT card_value_number[5]
VAR_INT card_value_suit[5]  

VAR_INT hand_result
VAR_INT active_vidpok_machine
VAR_INT high_cards_held
VAR_INT vp_cross_is_pressed
VAR_INT vp_triangle_is_pressed
VAR_INT vp_dpad_is_pressed
VAR_INT vp_initial_stake

// coords - could be hardwired if we need space
VAR_FLOAT card_x[5]
VAR_FLOAT card_y[5]
VAR_FLOAT bet_one_x
VAR_FLOAT bet_one_y
VAR_FLOAT deal_x
VAR_FLOAT deal_y
VAR_FLOAT col_x[6]
VAR_FLOAT row_y[10]

{
vidpok_script:
SCRIPT_NAME VIDPOK

LVAR_INT machine
LVAR_INT machine_value
LVAR_INT help_flag

// general variables that are in every mission 
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed  
LVAR_INT m_quit
LVAR_INT frame_num
LVAR_INT this_frame_time
LVAR_INT last_frame_time
LVAR_INT time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT temp_seq
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT vec2_x vec2_y vec2_z

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0	  
m_quit		= 0

// fake creates 
IF m_goals = -1
	CREATE_OBJECT KMB_ROCK 0.0 0.0 0.0 machine
	CREATE_OBJECT KMB_ROCK 0.0 0.0 0.0 active_vidpok_machine
ENDIF


// give this machine a random value
IF IS_PLAYER_PLAYING player1
	STORE_SCORE player1 temp_int
ENDIF
	
IF temp_int < 100
	temp_int2 = 5
ELSE
	IF temp_int < 1000
		temp_int2 = 6
	ELSE
		IF temp_int < 10000
			temp_int2 = 7
		ELSE
			IF temp_int < 50000
				temp_int2 = 8
			ELSE
				IF temp_int < 100000
					temp_int2 = 9
				ELSE
					temp_int2 = 10
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

GENERATE_RANDOM_INT_IN_RANGE 0 temp_int2 temp_int
SWITCH temp_int

/////// these ones are always unlocked ////////
	CASE 0
		machine_value = 1
	BREAK	
	CASE 1
		machine_value = 5	
	BREAK
	CASE 2
		machine_value = 10
	BREAK
	CASE 3
		machine_value = 25
	BREAK
	CASE 4
		machine_value = 50
	BREAK

//////// big money machines ////////////


	CASE 5
		machine_value = 100
	BREAK
	CASE 6
		machine_value = 500
	BREAK
	CASE 7
		machine_value = 1000
	BREAK
	CASE 8
		machine_value = 5000
	BREAK
	CASE 9
		machine_value = 10000
	BREAK

ENDSWITCH
	

	vidpok_loop:
	    
		WAIT 0

		IF DOES_OBJECT_EXIST machine
			IF active_vidpok_machine = 0
				IF IS_PLAYER_PLAYING player1
					IF iSetCasinoPanic = 0
						IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer machine 5.0 5.0 FALSE
							temp_int = 0
							GET_OBJECT_MODEL machine temp_int2
							GET_MODEL_DIMENSIONS temp_int2 vec_x vec_y vec_z vec2_x vec2_y vec2_z
							temp_float = vec2_x - vec_x
							
							IF temp_float > 4.0
								// bank	of slots
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine 0.0 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine -0.838 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine -1.676 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine 0.838 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine 1.676 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
							ELSE
								// single slot
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS machine 0.0 -1.0 0.0 x y z
								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.3 0.3 1.5 FALSE
									temp_int = 1
								ENDIF
							ENDIF

							
							IF temp_int = 1
								
								IF vp_triangle_is_pressed = 0
									IF IS_BUTTON_PRESSED PAD1 TRIANGLE

										IF help_flag = 1
											CLEAR_HELP
											help_flag = 0
										ENDIF
										m_stage 	= 0
										m_goals 	= 0
										active_vidpok_machine =	machine
									ELSE
										IF help_flag = 0
											PRINT_HELP_FOREVER VP01
											help_flag = 1
										ENDIF
									ENDIF
								ELSE
									IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
										vp_triangle_is_pressed = 0
									ENDIF
								ENDIF

							ELSE
								IF help_flag = 1
									CLEAR_HELP
									help_flag = 0
								ENDIF
							ENDIF
						ELSE
							IF help_flag = 1							
								CLEAR_HELP
								help_flag = 0
							ENDIF
							TERMINATE_THIS_SCRIPT
						ENDIF
					ELSE
						IF help_flag = 1
							CLEAR_HELP
							help_flag = 0
						ENDIF
						TERMINATE_THIS_SCRIPT
					ENDIF	
				ELSE 
					IF help_flag = 1
						CLEAR_HELP
						help_flag = 0
					ENDIF
					TERMINATE_THIS_SCRIPT
				ENDIF
			ELSE
				IF active_vidpok_machine = machine
					IF IS_PLAYER_PLAYING player1
						IF m_quit = 0
							GOSUB mission_loop_VIDPOK
						ELSE
							GOSUB vidpok_cleanup
							active_vidpok_machine = 0 
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF help_flag = 1
				CLEAR_HELP
				help_flag = 0
			ENDIF
			TERMINATE_THIS_SCRIPT
		ENDIF

	GOTO vidpok_loop

RETURN





// *************************************************************************************************************
//						MINI-GAME GOSUBS - MAIN LOOP FIRST
// *************************************************************************************************************

mission_loop_VIDPOK:

	// display mission stage variables for debug
	LVAR_INT display_debug
	VAR_INT VIDPOK_view_debug[2]
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		display_debug = 1	
	ENDIF
	IF display_debug = 1
		VIDPOK_view_debug[0] = m_stage
		VIDPOK_view_debug[1] = m_goals
		VIEW_INTEGER_VARIABLE VIDPOK_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE VIDPOK_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE high_cards_held high_cards_held
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

	// *************************************************************************************************************
	//								INITIALISATION - LOADING UP FROM GAME WORLD
	// *************************************************************************************************************
		IF m_stage = 0
			
			
			
			// initialise
			IF m_goals = 0
					
				SET_MINIGAME_IN_PROGRESS TRUE

				IF DOES_OBJECT_EXIST machine
					GET_OBJECT_HEADING machine heading
					//heading += 180.0
				ENDIF

				SET_PLAYER_CONTROL player1 OFF
				TASK_ACHIEVE_HEADING scplayer heading
				DISPLAY_HUD FALSE


VAR_INT vp_yellow
vp_yellow = 200

VP_SCREEN_X[0] = 529.0000   
VP_SCREEN_Y[0] = 57.0000  

VP_SCREEN_X[1] = 565.0000 
VP_SCREEN_Y[1] = 384.0000 

VP_SCREEN_X[2] = 527.0000  
VP_SCREEN_Y[2] = 133.0000 

VP_SCREEN_X[3] = 323.0000 
VP_SCREEN_Y[3] = 291.0000 
VP_SCREEN_X[4] = 180.0000 
VP_SCREEN_Y[4] = 413.0000 
VP_SCREEN_X[5] = 79.0000 
VP_SCREEN_Y[5] = 34.0000 

VP_SCREEN_X[6] = 531.0000 
VP_SCREEN_Y[6] = 79.0000 

VP_SCREEN_X[7] = 527.0000 
VP_SCREEN_Y[7] = 157.0000 



CARD_X[0] = 122.0000 
CARD_Y[0] = 314.0

CARD_X[1] = 222.0000 
CARD_Y[1] = 314.0

CARD_X[2] = 320.0000 
CARD_Y[2] = 314.0

CARD_X[3] = 419.0000 
CARD_Y[3] = 314.0

CARD_X[4] = 517.0000 
CARD_Y[4] = 314.0


VP_CARD_WIDTH = 91.0000 
VP_CARD_HEIGHT = 137.0000 


ROW_Y[0] = 33.0000 
ROW_Y[1] = 61.0000 
ROW_Y[2] = 76.0000 
ROW_Y[3] = 92.0000 
ROW_Y[4] = 108.0000 
ROW_Y[5] = 124.0000 
ROW_Y[6] = 140.0000 
ROW_Y[7] = 156.0000 
ROW_Y[8] = 172.0000 
ROW_Y[9] = 188.0000 


COL_X[0] = 86.0  // Name of hand

COL_X[1] = 239.0  // 1 coin 
COL_X[2] = 286.0  // 2 coins
COL_X[3] = 330.0  // 3 coins 
COL_X[4] = 378.0  // 4 coins 
COL_X[5] = 450.0  // 5 coins 


vp_hold_button_offset = -86.0 
VP_HOLD_WIDTH = 49.0000 
VP_HOLD_HEIGHT = 18.0000 


BET_ONE_X = 123.0000 
BET_ONE_Y = 401.0000 
VP_BET_ONE_WIDTH = 81.0000 
VP_BET_ONE_HEIGHT = 18.0000 


DEAL_X = 222.0000 
DEAL_Y = 401.0000 
VP_DEAL_WIDTH = 87.0000 
VP_DEAL_HEIGHT = 18.0000 


VP_BORDER_THICKNESS = 4.0000 


VP_LINE_X[0] = 384.0000        // Vertical lines for the columns.
VP_LINE_Y[0] = 131.0000 
VP_LINE_WIDTH[0] = 2.0000 
VP_LINE_HEIGHT[0] = 152.0000         

VP_LINE_X[1] = 458.0000        
VP_LINE_Y[1] = 131.0000        // Right vertical line.
VP_LINE_WIDTH[1] = 2.0000 							
VP_LINE_HEIGHT[1] = 154.0000 			

VP_LINE_X[2] = 267.0000        // Bottom horizontal line.
VP_LINE_Y[2] = 207.0000 							
VP_LINE_WIDTH[2] = 382.0000 						
VP_LINE_HEIGHT[2] = 2.0000 

VP_LINE_X[3] = 77.0000 		   // left vertical line.						
VP_LINE_Y[3] = 131.0000 							
VP_LINE_WIDTH[3] = 2.0000 
VP_LINE_HEIGHT[3] = 152.0000 						

VP_LINE_X[4] = 267.0000 	   // Box around the outside.					
VP_LINE_Y[4] = 54.0000         // Top horizontal line.
VP_LINE_WIDTH[4] = 383.0000 						
VP_LINE_HEIGHT[4] = 2.0000 										               

VP_LINE_X[5] = 199.0
VP_LINE_Y[5] = 131.0000 
VP_LINE_WIDTH[5] = 2.0000 
VP_LINE_HEIGHT[5] = 152.0000 

VP_LINE_X[6] = 246.0
VP_LINE_Y[6] = 131.0000 
VP_LINE_WIDTH[6] = 2.0000 
VP_LINE_HEIGHT[6] = 152.0000 

VP_LINE_X[7] = 292.0
VP_LINE_Y[7] = 131.0000 
VP_LINE_WIDTH[7] = 2.0000 
VP_LINE_HEIGHT[7] = 153.0000 

VP_LINE_X[8] = 338.0000 
VP_LINE_Y[8] = 131.0000 
VP_LINE_WIDTH[8] = 2.0000 
VP_LINE_HEIGHT[8] = 153.0000 

TEXT_SCALE_X[0] = 0.82 
TEXT_SCALE_Y[0] = 3.02 

TEXT_COLOUR_R[0] = 128
TEXT_COLOUR_G[0] = 0
TEXT_COLOUR_B[0] = 0

TEXT_FONT[0] = 1
TEXT_CENTRE[0] = 1 
TEXT_EDGE[0] = 1

//GET_HUD_COLOUR HUD_COLOUR_YELLOW TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] temp_int

TEXT_EDGE_R[0] = vp_yellow
TEXT_EDGE_G[0] = vp_yellow
TEXT_EDGE_B[0] = 0

TEXT_SCALE_X[1] = 0.5200 
TEXT_SCALE_Y[1] = 2.3900 

//GET_HUD_COLOUR HUD_COLOUR_RED TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] temp_int

TEXT_COLOUR_R[1] = 128
TEXT_COLOUR_G[1] = 0
TEXT_COLOUR_B[1] = 0

TEXT_FONT[1] = 1 
TEXT_CENTRE[1] = 0 
TEXT_EDGE[1] = 1 

//GET_HUD_COLOUR HUD_COLOUR_YELLOW TEXT_EDGE_R[1] TEXT_EDGE_G[1] TEXT_EDGE_B[1] temp_int

TEXT_EDGE_R[1] = vp_yellow
TEXT_EDGE_G[1] = vp_yellow
TEXT_EDGE_B[1] = 0

TEXT_SCALE_X[2] = 0.3600 
TEXT_SCALE_Y[2] = 1.5300 

//GET_HUD_COLOUR HUD_COLOUR_YELLOW TEXT_COLOUR_R[2] TEXT_COLOUR_G[2] TEXT_COLOUR_B[2] temp_int
TEXT_COLOUR_R[2] = vp_yellow
TEXT_COLOUR_G[2] = vp_yellow
TEXT_COLOUR_B[2] = 0

TEXT_FONT[2] = 1 
TEXT_CENTRE[2] = 0 
TEXT_EDGE[2] = 1 
TEXT_EDGE_R[2] = 0 
TEXT_EDGE_G[2] = 0 
TEXT_EDGE_B[2] = 0 
TEXT_SCALE_X[3] = 0.3600 
TEXT_SCALE_Y[3] = 1.7200 

//GET_HUD_COLOUR HUD_COLOUR_YELLOW TEXT_COLOUR_R[3] TEXT_COLOUR_G[3] TEXT_COLOUR_B[3] temp_int
TEXT_COLOUR_R[3] = vp_yellow
TEXT_COLOUR_G[3] = vp_yellow
TEXT_COLOUR_B[3] = 0

TEXT_FONT[3] = 1 
TEXT_CENTRE[3] = 0 
TEXT_EDGE[3] = 1 
TEXT_EDGE_R[3] = 0 
TEXT_EDGE_G[3] = 0 
TEXT_EDGE_B[3] = 0 
TEXT_SCALE_X[4] = 0.4200 
TEXT_SCALE_Y[4] = 1.6 

TEXT_COLOUR_R[4] = vp_yellow
TEXT_COLOUR_G[4] = vp_yellow
TEXT_COLOUR_B[4] = 0
 
TEXT_FONT[4] = 1 
TEXT_CENTRE[4] = 0 
TEXT_EDGE[4] = 1 
TEXT_EDGE_R[4] = 0 
TEXT_EDGE_G[4] = 0 
TEXT_EDGE_B[4] = 0 
TEXT_SCALE_X[5] = 0.4400 
TEXT_SCALE_Y[5] = 1.3500 

//GET_HUD_COLOUR HUD_COLOUR_YELLOW TEXT_COLOUR_R[5] TEXT_COLOUR_G[5] TEXT_COLOUR_B[5] temp_int
TEXT_COLOUR_R[5] = vp_yellow
TEXT_COLOUR_G[5] = vp_yellow
TEXT_COLOUR_B[5] = 0

TEXT_FONT[5] = 1 
TEXT_CENTRE[5] = 1 
TEXT_EDGE[5] = 1 
TEXT_EDGE_R[5] = 0 
TEXT_EDGE_G[5] = 0 
TEXT_EDGE_B[5] = 0 

TILE_HEIGHT = 81.0
TILE_WIDTH = 63.0

VP_TABLE_X = 268.0000   // Black background draws here.
VP_TABLE_Y = 130.0000 
VP_TABLE_WIDTH = 384.0000 
VP_TABLE_HEIGHT = 152.0000 

//VP_HIGHL_WIDTH = 58.5 
//VP_HIGHL_HEIGHT = 146.0000  // This stuff is for highlighted columns.

VP_HIGHL_X[0] = 222.0 
VP_HIGHL_Y[0] = 130.0
VP_HIGHL_WIDTH[0] = 49.0
VP_HIGHL_HEIGHT[0] = 152.0

VP_HIGHL_X[1] = 269.0 
VP_HIGHL_Y[1] = 130.0 
VP_HIGHL_WIDTH[1] = 46.0
VP_HIGHL_HEIGHT[1] = 152.0

VP_HIGHL_X[2] = 314.0 
VP_HIGHL_Y[2] = 130.0 
VP_HIGHL_WIDTH[2] = 48.0
VP_HIGHL_HEIGHT[2] = 152.0

VP_HIGHL_X[3] = 360.0
VP_HIGHL_Y[3] = 130.0 
VP_HIGHL_WIDTH[3] = 46.0
VP_HIGHL_HEIGHT[3] = 152.0

VP_HIGHL_X[4] = 421.0 
VP_HIGHL_Y[4] = 130.0 
VP_HIGHL_WIDTH[4] = 72.0
VP_HIGHL_HEIGHT[4] = 152.0

button_text_offset = 2.5


				
			m_goals++ 
			ENDIF

			// setup camera and player
			IF m_goals = 1
				DO_FADE 500 FADE_OUT
				m_goals++
			ENDIF
			
			IF m_goals = 2
				IF NOT GET_FADING_STATUS
					m_goals++
				ENDIF
			ENDIF
			
			IF m_goals = 3

				USE_TEXT_COMMANDS TRUE
				LOAD_TEXTURE_DICTIONARY ld_poke

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

				LOAD_SPRITE 54 addcoin
				LOAD_SPRITE 57 deal
				LOAD_SPRITE 60 holdoff 
				LOAD_SPRITE 61 holdmid
				LOAD_SPRITE 62 holdon
				LOAD_SPRITE 63 tvcorn
				LOAD_SPRITE 64 backred
				LOAD_SPRITE 65 backcyan
				
				LOAD_MISSION_AUDIO 4 SOUND_BANK_VIDEO_POKER
				WHILE NOT HAS_MISSION_AUDIO_LOADED 4
					WAIT 0
				ENDWHILE


				// set intial bet to 1 coin
				IF IS_PLAYER_PLAYING player1
					STORE_SCORE player1 temp_int
					GOSUB vp_update_casino_credit  
					temp_int += casino_credit
		//			IF temp_int < machine_value
		//				// print - 'you don't have enough cash to play this machine'
		//				m_quit = 1
		//				GOTO end_of_main_loop_VIDPOK
		//			ELSE
						no_of_coins = 1 // initial starting bet is always 1 coin	
		//			ENDIF
				ENDIF


				DO_FADE 0 FADE_IN

				m_goals = 99
			ENDIF

			IF m_goals = 99
				highlighted_button = 0  // Deal button highlighted.
				m_stage++
				m_goals = 0
			ENDIF
		ENDIF

	// *************************************************************************************************************
	//											STAGE 1 - Place Bet   
	// *************************************************************************************************************
		IF m_stage = 1

			// initialisation for stage
			IF m_goals = 0

				deal_value = 2
				bet_one_value = 2
				
				// clear any cards or hold buttons that might still be on.
				temp_int = 0
				WHILE temp_int < 5
					card_value[temp_int] = 0
					hold_value[temp_int] = 0
				temp_int++
				ENDWHILE

				// check we've got enough credit to continue with last bet
				STORE_SCORE player1 temp_int
				GOSUB vp_update_casino_credit  
				temp_int += casino_credit
				temp_int2 = no_of_coins * machine_value
				IF temp_int < temp_int2
					no_of_coins = 1
				ENDIF


			m_goals++
			ENDIF
			
			// get user input
			IF m_goals = 1

				IF highlighted_button = 0
					bet_one_value = 3
					deal_value = 2
				ELSE
					bet_one_value = 2
					deal_value = 3
				ENDIF
			    
				GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

				IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
				OR LStickX > 64
					IF vp_dpad_is_pressed = 0
						IF highlighted_button = 0
							highlighted_button = 1
						ELSE
							highlighted_button = 0
						ENDIF
						vp_dpad_is_pressed ++
						GOTO vp_end_of_input_2
					ENDIF
				ELSE
					IF IS_BUTTON_PRESSED PAD1 DPADLEFT
					OR LStickX < -64
						IF vp_dpad_is_pressed = 0
							IF highlighted_button = 1
								highlighted_button = 0
							ELSE
								highlighted_button = 1
							ENDIF
							vp_dpad_is_pressed ++
							GOTO vp_end_of_input_2
						ENDIF
					ELSE
						IF LStickX > -64
						AND LStickX < 64
							vp_dpad_is_pressed = 0
						ENDIF
					ENDIF
				ENDIF
				

				// Go to next stage when they press deal.

				IF vp_cross_is_pressed = 0 
					IF IS_BUTTON_PRESSED PAD1 CROSS
						IF highlighted_button = 0
							vp_cross_is_pressed++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
							m_goals ++
							GOTO vp_end_of_input_2
						ELSE
							vp_cross_is_pressed++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
							deal_value = 2
							TIMERA = 0
							m_goals += 3
							GOTO vp_end_of_input_2
						ENDIF
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						vp_cross_is_pressed = 0
					ENDIF	
				ENDIF

				vp_end_of_input_2:
			ENDIF
			

			// 'bet one' -------------------------------------------
			IF m_goals = 2
				IF no_of_coins < 5
					no_of_coins ++
				ELSE
					no_of_coins = 1
				ENDIF

				temp_int = no_of_coins * machine_value
				STORE_SCORE player1 temp_int2
				GOSUB vp_update_casino_credit  
				temp_int2 += casino_credit

				IF temp_int > temp_int2
					no_of_coins = 1
				ENDIF
				TIMERA = 0
				m_goals++
			ENDIF 	
			
			// wait a sec so it's not crazily fast
			IF m_goals = 3
				m_goals = 1 // go back to user input
			ENDIF

			// 'deal' ------------------------------------------------
			IF m_goals = 4
				STORE_SCORE player1 temp_int
				GOSUB vp_update_casino_credit  
				temp_int += casino_credit
				temp_int2 = no_of_coins * machine_value

				IF temp_int < temp_int2
					m_goals = 1 // go back to user input

				ELSE
					m_goals = 99
				ENDIF
			ENDIF
			vp_end_of_input_3:
			// exit	-------------------------------------------------
			IF m_goals = 99
				IF no_of_coins = 6
					no_of_coins = 4
				ENDIF
				IF no_of_coins = 7
					no_of_coins = 3
				ENDIF
				IF no_of_coins = 8
					no_of_coins = 2
				ENDIF
				IF no_of_coins = 9
					no_of_coins = 1
				ENDIF

				temp_int = no_of_coins * machine_value
				INCREMENT_INT_STAT_NO_MESSAGE MONEY_SPENT_GAMBLING temp_int
				temp_float =# temp_int
				temp_float *= 0.001

				INCREMENT_FLOAT_STAT_NO_MESSAGE GAMBLING temp_float
				vp_initial_stake = temp_int

				temp_int *= -1
				ADD_SCORE player1 temp_int

				m_goals = 0
				m_stage++
				GOTO m_stage_1_end
			ENDIF

			GOSUB vidpok_draw_hud

			m_stage_1_end:
		ENDIF 


	// *************************************************************************************************************
	//											STAGE 2 - Deal 5 cards  
	// *************************************************************************************************************
		IF m_stage = 2
			
			// initialisation for stage
			IF m_goals = 0
			
				deal_value = 0
				bet_one_value = 0

				// switch all lights off
				
				temp_int = 0
				WHILE temp_int < 5
					card_value[temp_int] = 0
					hold_value[temp_int] = 0
				temp_int++
				ENDWHILE

				// shuffle cards for every 40 cards dealt
//				IF video_poker_cards_dealt > 40
					SHUFFLE_CARD_DECKS 1
//					video_poker_cards_dealt = 0
//				ENDIF

			m_goals++
			ENDIF
			
			// DEAL CARD[0]
			IF m_goals = 1
				FETCH_NEXT_CARD temp_int
				GOSUB bastardise_dealing1		
				card_value[0] = temp_int
				TIMERA = 0
				//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
				m_goals++
			ENDIF	
			
			IF m_goals = 2

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			
			// DEAL CARD[1]
			IF m_goals = 3
				FETCH_NEXT_CARD temp_int
				GOSUB bastardise_dealing1
				//video_poker_cards_dealt++
				card_value[1] = temp_int
				TIMERA = 0
				//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
				m_goals++
			ENDIF
			IF m_goals = 4
				
				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			// DEAL CARD[2]
			IF m_goals = 5
				FETCH_NEXT_CARD temp_int
				GOSUB bastardise_dealing1
				//video_poker_cards_dealt++
				card_value[2] = temp_int
				TIMERA = 0
				//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
				m_goals++
			ENDIF
			IF m_goals = 6
				
				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			// DEAL CARD[3]
			IF m_goals = 7
				FETCH_NEXT_CARD temp_int 
				GOSUB bastardise_dealing1
				//video_poker_cards_dealt++
				card_value[3] = temp_int
				TIMERA = 0
				//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
				m_goals++
			ENDIF
			IF m_goals = 8

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			// DEAL CARD[4]
			IF m_goals = 9
				FETCH_NEXT_CARD temp_int
				GOSUB bastardise_dealing1
				//video_poker_cards_dealt++
				card_value[4] = temp_int
				TIMERA = 0
				//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
				m_goals++
			ENDIF

			// wait a couple of ticks before going to 'hold' stage
			IF m_goals = 10
				
				IF TIMERA > 800
					m_goals = 99
				ENDIF
			ENDIF
			
			// exit
			IF m_goals = 99
				m_goals = 0
				m_stage++
				GOTO m_stage_2_end
			ENDIF

			// FUNCTIONS GLOBAL FOR STAGE -------
			GOSUB vidpok_draw_hud

			m_stage_2_end:

		ENDIF 

	// *************************************************************************************************************
	//											STAGE 3   
	// *************************************************************************************************************
		IF m_stage = 3
			
			// initialisation for stage
			IF m_goals = 0
			
				// switch hold buttons on and deal button
				
				deal_value = 2
				bet_one_value = 0

				temp_int = 0
				WHILE temp_int < 5
					hold_value[temp_int] = 2
					hold_status[temp_int] = 0
				temp_int++
				ENDWHILE
				
				highlighted_button = 0 // make the leftmost 'hold' button the default

			m_goals++
			ENDIF

			// get user input
			IF m_goals = 1

				GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

				IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
				OR LStickX > 64
					IF vp_dpad_is_pressed = 0
						IF highlighted_button < 5
							highlighted_button++
							IF highlighted_button = 5
								highlighted_button = 0
							ENDIF
							m_goals++
							TIMERA = 0
							vp_dpad_is_pressed++
							GOTO vp_end_of_input_1
						ENDIF
					ENDIF
				ELSE
					IF IS_BUTTON_PRESSED PAD1 DPADLEFT
					OR LStickX < -64
						IF vp_dpad_is_pressed = 0
							IF highlighted_button > -1
							AND highlighted_button < 5
								highlighted_button--
								IF highlighted_button = -1
									highlighted_button = 4
								ENDIF
								m_goals ++
								vp_dpad_is_pressed++
								TIMERA = 0
								GOTO vp_end_of_input_1
							ENDIF
						ENDIF
					ELSE
						IF LStickX > -64
						AND LStickX < 64
							vp_dpad_is_pressed = 0
						ENDIF
					ENDIF
				ENDIF
				
				IF IS_BUTTON_PRESSED PAD1 DPADDOWN
				OR LStickY > 64
					highlighted_button = 5
					m_goals ++
					TIMERA = 0
					GOTO vp_end_of_input_1
				ENDIF

				IF IS_BUTTON_PRESSED PAD1 DPADUP
				OR LStickY < -64
					IF highlighted_button = 5
						
						highlighted_button = 1
						m_goals ++
						TIMERA = 0
						GOTO vp_end_of_input_1
					ENDIF
				ENDIF
				
				IF IS_BUTTON_PRESSED PAD1 CROSS
				
					IF vp_cross_is_pressed = 0
						IF highlighted_button < 5
							IF hold_status[highlighted_button] = 0
								hold_status[highlighted_button] = 1
							ELSE
								hold_status[highlighted_button] = 0
							ENDIF 
							TIMERA = 0
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
							m_goals++
							vp_cross_is_pressed++
							GOTO vp_end_of_input_1
						ELSE
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
							TIMERA = 0
							m_goals += 2
							vp_cross_is_pressed++
							GOTO vp_end_of_input_1	
						ENDIF
					ENDIF
				ELSE
					IF NOT vp_cross_is_pressed = 0
						vp_cross_is_pressed = 0
					ENDIF
				ENDIF

				vp_end_of_input_1:

				// highlight correct button
				temp_int = 0
				WHILE temp_int < 6
					IF temp_int < 5
						IF NOT temp_int = highlighted_button
							IF hold_status[temp_int] = 0
								hold_value[temp_int] = 2
							ELSE
								hold_value[temp_int] = 4
							ENDIF
						ELSE
							IF hold_status[temp_int] = 0
								hold_value[temp_int] = 3
							ELSE
								hold_value[temp_int] = 5
							ENDIF	
						ENDIF
					ELSE
						IF NOT temp_int = highlighted_button
							deal_value = 2
						ELSE
							deal_value = 3
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE
				
					  
			ENDIF

			// wait a couple of tics
			IF m_goals = 2
				m_goals = 1	// go back to input select
			ENDIF

			// the deal button was pressed
			IF m_goals = 3
				m_goals = 99
			ENDIF

			// exit
			IF m_goals = 99
				m_goals = 0
				m_stage++
				GOTO m_stage_3_end
			ENDIF

			// FUNCTIONS GLOBAL FOR STAGE -------
			GOSUB vidpok_draw_hud

			m_stage_3_end:

		ENDIF


	// *************************************************************************************************************
	//										STAGE 4 - deal 2nd round of cards  
	// *************************************************************************************************************
		IF m_stage = 4
		    	
			IF m_goals = 0
				// switch off all lights

				deal_value = 0
				bet_one_value = 0

				temp_int = 0
				WHILE temp_int < 5
					hold_value[temp_int] = 0
				temp_int++
				ENDWHILE
			
				// analysize held cards (count high cards held)
				high_cards_held = 0
				temp_int = 0
				WHILE temp_int < 5
					IF NOT hold_status[temp_int] = 0
						vp_in_card_value = card_value[temp_int]	
						GOSUB vidpok_convert_card_value
						IF vp_out_card_value = 1
						OR vp_out_card_value = 11
						OR vp_out_card_value = 12
						OR vp_out_card_value = 13
							high_cards_held++
						ENDIF
					ENDIF
				temp_int++
				ENDWHILE

				m_goals++
			ENDIF

			// remove unwanted cards
			IF m_goals = 1
				temp_int = 0
				WHILE temp_int < 5
					IF hold_status[temp_int] = 0
						card_value[temp_int] = 0
					ENDIF
				temp_int++
				ENDWHILE
				TIMERA = 0
				m_goals++
			ENDIF
			
			// wait a couple of tics before dealing new cards
			IF m_goals = 2

				IF TIMERA > 500
					m_goals++
				ENDIF
			ENDIF

			// check first card
			IF m_goals = 3
				IF hold_status[0] = 0
					FETCH_NEXT_CARD temp_int
					GOSUB bastardise_dealing2
					//video_poker_cards_dealt++		
					card_value[0] = temp_int
					TIMERA = 0
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
					m_goals++
				ELSE
					m_goals += 2
				ENDIF
			ENDIF

	 		IF m_goals = 4

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			
			// check 2nd card
			IF m_goals = 5
				IF hold_status[1] = 0
					FETCH_NEXT_CARD temp_int
					GOSUB bastardise_dealing2
					//video_poker_cards_dealt++		
					card_value[1] = temp_int
					TIMERA = 0
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
					m_goals++
				ELSE
					m_goals += 2
				ENDIF
			ENDIF
	 		
			IF m_goals = 6

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			
			// check 3rd card
			IF m_goals = 7
				IF hold_status[2] = 0
					FETCH_NEXT_CARD temp_int
					GOSUB bastardise_dealing2
					//video_poker_cards_dealt++		
					card_value[2] = temp_int
					TIMERA = 0
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
					m_goals++
				ELSE
					m_goals += 2
				ENDIF
			ENDIF
	 		
			IF m_goals = 8

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			
			// check 4th card
			IF m_goals = 9
				IF hold_status[3] = 0
					FETCH_NEXT_CARD temp_int
					GOSUB bastardise_dealing2
					//video_poker_cards_dealt++		
					card_value[3] = temp_int
					TIMERA = 0
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
					m_goals++
				ELSE
					m_goals += 2
				ENDIF
			ENDIF
	 		
			IF m_goals = 10

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF
			
			// check 5th card
			IF m_goals = 11
				IF hold_status[4] = 0
					FETCH_NEXT_CARD temp_int
					GOSUB bastardise_dealing2
					//video_poker_cards_dealt++		
					card_value[4] = temp_int
					TIMERA = 0
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON
					m_goals++
				ELSE
					m_goals += 2
				ENDIF
			ENDIF
	 		
			IF m_goals = 12

				IF TIMERA > 400
					m_goals++
				ENDIF
			ENDIF

			// wait a couple of ticks before going to 'hold' stage
			IF m_goals = 13
			    //highlighted_button = 8  // Deal button highlighted.

				IF TIMERA > 800
					m_goals = 99
				ENDIF
			ENDIF

			// exit
			IF m_goals = 99
				m_goals = 0
				m_stage++
				GOTO m_stage_4_end
			ENDIF

			// FUNCTIONS GLOBAL FOR STAGE -------
			GOSUB vidpok_draw_hud

			m_stage_4_end:
		ENDIF

	// *************************************************************************************************************
	//							STAGE 5 - analyze results  
	// *************************************************************************************************************
		IF m_stage = 5

			IF m_goals = 0
				hand_is_royal_flush		= 0
				hand_is_straight_flush	= 0
				hand_is_four_of_kind	= 0
				hand_is_full_house		= 0
				hand_is_flush			= 0
				hand_is_straight		= 0
				hand_is_three_of_kind	= 0
				hand_is_two_pairs		= 0
				hand_is_jacks_or_better	= 0
				pair1					= 0
				pair2					= 0
				m_goals++
			ENDIF
			
			// organise cards to make them easy to analyse
			IF m_goals = 1

				temp_int = 0
				WHILE temp_int < 5
					// get number 
					vp_in_card_value = card_value[temp_int]	
					GOSUB vidpok_convert_card_value
					card_value_number[temp_int] = vp_out_card_value
					// get suit	
				    vp_in_card_value = card_value[temp_int]
					GOSUB vidpok_convert_card_suit
					card_value_suit[temp_int] = vp_out_card_value
				temp_int++
				ENDWHILE

				SAVE_NEWLINE_TO_DEBUG_FILE

				// sort numbers
				temp_int3 = 1 // stores number of changes
				WHILE temp_int3 > 0
					temp_int = 0
					temp_int2 = 0 // used for 'next card'
					temp_int3 = 0 
					WHILE temp_int < 5
						temp_int2 = temp_int + 1
						// compare card with next card
						IF temp_int2 < 5
							IF card_value_number[temp_int] > card_value_number[temp_int2]
								// swap
								LVAR_INT temp_swap_value
								temp_swap_value = card_value_number[temp_int2]
								card_value_number[temp_int2] = card_value_number[temp_int]
								card_value_number[temp_int] = temp_swap_value
								temp_int3++
							ENDIF
						ENDIF
					temp_int++
					ENDWHILE
				ENDWHILE

				// check for flush
				IF 	card_value_suit[0] = card_value_suit[1]
				AND card_value_suit[1] = card_value_suit[2]
				AND card_value_suit[2] = card_value_suit[3]
				AND card_value_suit[3] = card_value_suit[4]
					hand_is_flush = 1
				ENDIF

				// check for straight, straight flush, royal flush
				temp_int = card_value_number[0]	+ 1
				IF temp_int = card_value_number[1]
					temp_int = card_value_number[1] + 1
					IF temp_int = card_value_number[2]
						temp_int = card_value_number[2] + 1
						IF temp_int = card_value_number[3] 
							temp_int = card_value_number[3] + 1
							IF temp_int = card_value_number[4]	  
								// regular straight
								hand_is_straight = 1
								IF hand_is_flush = 1
									hand_is_straight_flush = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF 
				ENDIF 

				// check for straights with an ace at the end - including royal flush
				IF card_value_number[0] = 1
				AND card_value_number[1] = 10
				AND card_value_number[2] = 11
				AND card_value_number[3] = 12
				AND card_value_number[4] = 13
					hand_is_straight = 1
					IF hand_is_flush = 1
						hand_is_straight_flush = 1
						// holy fuck! a royal flush!!
						hand_is_royal_flush = 1
					ENDIF 
				ENDIF
				

				// check for matches
				temp_int = 0
				temp_int2 = 0 
				temp_int3 = 0 // used for in a row
				WHILE temp_int < 5
					temp_int2 = temp_int + 1
					IF temp_int2 < 5
						IF card_value_number[temp_int] = card_value_number[temp_int2]
							temp_int3++
							//WRITE_DEBUG_WITH_INT temp_int3 temp_int3
							IF temp_int2 = 4
								GOTO check_end_of_run
							ENDIF
						ELSE
							check_end_of_run:
							// end of run, record what it was
							IF temp_int3 > 0
								
								// pair
								IF temp_int3 = 1
									IF pair1 = 0
										pair1 = card_value_number[temp_int]
									ELSE
										pair2 = card_value_number[temp_int]
									ENDIF
								ENDIF

								// 3 of a kind
								IF temp_int3 = 2
									hand_is_three_of_kind = 1
								ENDIF

								// 4 of a kind
								IF temp_int3 = 3
									hand_is_four_of_kind = 1
								ENDIF

								temp_int3 = 0

							ENDIF
						ENDIF
					ENDIF 
				temp_int++
				ENDWHILE

				// check for full house
				IF hand_is_three_of_kind = 1
				AND NOT pair1 = 0
					hand_is_full_house = 1
				ENDIF

				// check 2 pairs
				IF NOT pair1 = 0
				AND NOT pair2 = 0
					hand_is_two_pairs = 1
				ENDIF

				// check for jacks or better
				IF NOT pair1 = 0
					IF pair1 > 10
					OR pair1 = 1
						hand_is_jacks_or_better = 1
					ENDIF
				ENDIF

				// GET BEST RESULT		   No.
				// ---------------		   ---
				// hand_is_royal_flush		9
				// hand_is_straight_flush	8
				// hand_is_four_of_kind		7
				// hand_is_full_house		6
				// hand_is_flush			5
				// hand_is_straight			4
				// hand_is_three_of_kind	3
				// hand_is_two_pairs		2
				// hand_is_jacks_or_better	1
				// fuck all 				0
				
				IF hand_is_royal_flush = 1
					hand_result = 9 
				ELSE
					IF hand_is_straight_flush = 1
						hand_result = 8 
					ELSE
						IF hand_is_four_of_kind = 1
							hand_result = 7 
						ELSE
							IF hand_is_full_house = 1
								hand_result = 6 
							ELSE
								IF hand_is_flush = 1
									hand_result = 5 
								ELSE
									IF hand_is_straight = 1
										hand_result = 4 
									ELSE
										IF hand_is_three_of_kind = 1
											hand_result = 3 
										ELSE
											IF hand_is_two_pairs = 1
												hand_result = 2 
											ELSE
												IF hand_is_jacks_or_better = 1
													hand_result = 1 
												ELSE
													hand_result = 0
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				m_goals++
			ENDIF

			IF m_goals = 2
				m_goals = 99
			ENDIF

			IF m_goals = 99
				m_stage++
				m_goals = 0
				TIMERA = 0
				GOTO m_stage_5_end
			ENDIF

			// FUNCTIONS GLOBAL FOR STAGE -------
			GOSUB vidpok_draw_hud

			m_stage_5_end:
		ENDIF

	// *************************************************************************************************************
	//							STAGE 6 - display results and payout  
	// *************************************************************************************************************
		IF m_stage = 6

			// calculate payout
			IF m_goals = 0

				// current bet
				temp_int = machine_value
				
				// if royal flush
				IF hand_result = 9
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 250
						BREAK 
						CASE 2
							temp_int3 = temp_int * 500
						BREAK
						CASE 3
							temp_int3 = temp_int * 750
						BREAK
						CASE 4
							temp_int3 = temp_int * 1000
						BREAK
						CASE 5
							temp_int3 = temp_int * 4000
						BREAK 
					ENDSWITCH
				ENDIF

				// if straight flush
				IF hand_result = 8
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 50
						BREAK 
						CASE 2
							temp_int3 = temp_int * 100
						BREAK
						CASE 3
							temp_int3 = temp_int * 150
						BREAK
						CASE 4
							temp_int3 = temp_int * 200
						BREAK
						CASE 5
							temp_int3 = temp_int * 250
						BREAK 
					ENDSWITCH
				ENDIF

				// if four of a kind
				IF hand_result = 7
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 25
						BREAK 
						CASE 2
							temp_int3 = temp_int * 50
						BREAK
						CASE 3
							temp_int3 = temp_int * 75
						BREAK
						CASE 4
							temp_int3 = temp_int * 100
						BREAK
						CASE 5
							temp_int3 = temp_int * 125
						BREAK 
					ENDSWITCH
				ENDIF

				// if full house
				IF hand_result = 6
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 9
						BREAK 
						CASE 2
							temp_int3 = temp_int * 18
						BREAK
						CASE 3
							temp_int3 = temp_int * 27
						BREAK
						CASE 4
							temp_int3 = temp_int * 36
						BREAK
						CASE 5
							temp_int3 = temp_int * 45
						BREAK 
					ENDSWITCH
				ENDIF

				// if Flush
				IF hand_result = 5
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 6
						BREAK 
						CASE 2
							temp_int3 = temp_int * 12
						BREAK
						CASE 3
							temp_int3 = temp_int * 18
						BREAK
						CASE 4
							temp_int3 = temp_int * 24
						BREAK
						CASE 5
							temp_int3 = temp_int * 30
						BREAK 
					ENDSWITCH
				ENDIF

				// if straight
				IF hand_result = 4
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 4
						BREAK 
						CASE 2
							temp_int3 = temp_int * 8
						BREAK
						CASE 3
							temp_int3 = temp_int * 12
						BREAK
						CASE 4
							temp_int3 = temp_int * 16
						BREAK
						CASE 5
							temp_int3 = temp_int * 20
						BREAK 
					ENDSWITCH
				ENDIF

				// if 3 of a kind
				IF hand_result = 3
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 3
						BREAK 
						CASE 2
							temp_int3 = temp_int * 6
						BREAK
						CASE 3
							temp_int3 = temp_int * 9
						BREAK
						CASE 4
							temp_int3 = temp_int * 12
						BREAK
						CASE 5
							temp_int3 = temp_int * 15
						BREAK 
					ENDSWITCH
				ENDIF

				// if 2 pairs
				IF hand_result = 2
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 2
						BREAK 
						CASE 2
							temp_int3 = temp_int * 4
						BREAK
						CASE 3
							temp_int3 = temp_int * 6
						BREAK
						CASE 4
							temp_int3 = temp_int * 8
						BREAK
						CASE 5
							temp_int3 = temp_int * 10
						BREAK 
					ENDSWITCH
				ENDIF

				// if jacks or better
				IF hand_result = 1
					SWITCH no_of_coins 
						CASE 1
							temp_int3 = temp_int * 1
						BREAK 
						CASE 2
							temp_int3 = temp_int * 2
						BREAK
						CASE 3
							temp_int3 = temp_int * 3
						BREAK
						CASE 4
							temp_int3 = temp_int * 4
						BREAK
						CASE 5
							temp_int3 = temp_int * 5
						BREAK 
					ENDSWITCH
				ENDIF

				m_goals++
	
			ENDIF

			IF NOT hand_result = 0
				IF TIMERA > 4000
				OR IS_BUTTON_PRESSED PAD1 CROSS
				    
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_PAYOUT

					ADD_SCORE player1 temp_int3
					
					temp_int = temp_int3 - vp_initial_stake
					INCREMENT_INT_STAT_NO_MESSAGE MONEY_WON_GAMBLING temp_int

					SHOW_UPDATE_STATS FALSE
					REGISTER_INT_STAT BIGGEST_GAMBLING_WIN temp_int
					SHOW_UPDATE_STATS TRUE

					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_VIDEO_POKER_BUTTON

					m_stage = 1
					m_goals = 0	
					
					highlighted_button = 1

					vp_cross_is_pressed = 1
					vp_initial_stake = 0
				ENDIF
			ELSE
				IF TIMERA > 4000
				OR IS_BUTTON_PRESSED PAD1 CROSS
				    
					SHOW_UPDATE_STATS FALSE
					REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS vp_initial_stake
					SHOW_UPDATE_STATS TRUE

					vp_initial_stake = 0

					vp_cross_is_pressed = 1
					m_stage = 1
					m_goals = 0	
					
					highlighted_button = 1
				ENDIF
			ENDIF	

			GOSUB vidpok_draw_hud
						
		ENDIF


	// *************************************************************************************************************
	//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
	// *************************************************************************************************************
	
	GOSUB vidpok_edit_hud

	IF m_stage > 0
		
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			
			m_quit = 1
			vp_triangle_is_pressed = 1
		ENDIF
	ENDIF


	// end of main loop
	end_of_main_loop_VIDPOK:

RETURN


// ************************************************************
//	   GOSUB - vidpok_draw_hud
// ************************************************************
VAR_INT vp_edit_mode
VAR_INT vp_edit_counter
VAR_FLOAT vp_screen_x[8]
VAR_FLOAT vp_screen_y[8]
VAR_FLOAT vp_card_height vp_card_width
VAR_FLOAT vp_hold_button_offset
VAR_FLOAT vp_hold_width vp_hold_height
VAR_FLOAT vp_border_thickness
VAR_FLOAT vp_border_width vp_border_height
VAR_FLOAT vp_deal_width vp_deal_height
VAR_FLOAT vp_bet_one_width vp_bet_one_height
VAR_FLOAT vp_line_x[9] vp_line_y[9]	vp_line_width[9] vp_line_height[9]

VAR_FLOAT text_scale_x[6] text_scale_y[6]
VAR_INT text_colour_r[6] text_colour_g[6] text_colour_b[6]
VAR_INT text_font[6]
VAR_INT text_centre[6]
VAR_INT text_edge[6] text_edge_r[6] text_edge_g[6] text_edge_b[6]

VAR_FLOAT button_text_offset

vidpok_edit_hud:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
		vp_edit_mode++
		IF vp_edit_mode > 13
			vp_edit_mode = 0
		ENDIF
		IF vp_edit_mode = 1
			WRITE_DEBUG EDIT_BET_DETAILS
		ENDIF	
		IF vp_edit_mode = 2
			WRITE_DEBUG EDIT_SHIFT_CARDS
		ENDIF
		IF vp_edit_mode = 3
			WRITE_DEBUG EDIT_SHIFT_TABLE_ROWS_AND_COLUMNS
		ENDIF
		IF vp_edit_mode = 4
			WRITE_DEBUG EDIT_HOLD_BUTTONS
		ENDIF
		IF vp_edit_mode = 5
			WRITE_DEBUG EDIT_BET_ONE
		ENDIF
		IF vp_edit_mode = 6
			WRITE_DEBUG EDIT_DEAL
		ENDIF
		IF vp_edit_mode = 7
			WRITE_DEBUG EDIT_BORDER_THICKNESS
		ENDIF
		IF vp_edit_mode = 8
			WRITE_DEBUG EDIT_TABLE_LINES
		ENDIF
		IF vp_edit_mode = 9
			WRITE_DEBUG EDIT_TEXT
		ENDIF
		IF vp_edit_mode = 10
			WRITE_DEBUG EDIT_TILES
		ENDIF
		IF vp_edit_mode = 11
			WRITE_DEBUG EDIT_TABLE_BACKGROUND
		ENDIF
		IF vp_edit_mode = 12
			WRITE_DEBUG EDIT_TABLE_HIGHLIGHTED_COLUMNS
		ENDIF
		IF vp_edit_mode = 13
			WRITE_DEBUG EDIT_BUTTON_TEXT_OFFSET
		ENDIF
	ENDIF

	// shift betting details
	IF vp_edit_mode = 1
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 7
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF 
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP 
			vp_screen_y[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN 
			vp_screen_y[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			vp_screen_x[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			vp_screen_x[vp_edit_counter] += -1.0
		ENDIF
	ENDIF

	// shift cards
	IF vp_edit_mode = 2
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 4
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP 
			
			card_y[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN 
			card_y[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			card_x[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			card_x[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_card_width += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_card_width += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_card_height += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_card_height += -1.0
		ENDIF
	ENDIF

	// shift table rows, colums
	IF vp_edit_mode = 3
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 9
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP 
			row_y[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN 
			row_y[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			IF vp_edit_counter > 5
				vp_edit_counter = 0
			ENDIF
			col_x[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			IF vp_edit_counter > 5
				vp_edit_counter = 0
			ENDIF
			col_x[vp_edit_counter] += -1.0
		ENDIF
	ENDIF

	// hold buttons
	IF vp_edit_mode = 4
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN

		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
			vp_hold_button_offset += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
			vp_hold_button_offset += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_hold_width += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_hold_width += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_hold_height += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_hold_height += -1.0
		ENDIF
	ENDIF			  
					  
	// bet one		  
	IF vp_edit_mode = 5
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN

		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			bet_one_y += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			bet_one_y += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			bet_one_x += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			bet_one_x += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_bet_one_width += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_bet_one_width += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_bet_one_height += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_bet_one_height += -1.0
		ENDIF
	ENDIF

	// deal
	IF vp_edit_mode = 6
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN

		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			deal_y += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			deal_y += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			deal_x += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			deal_x += -1.0
		ENDIF																	   
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6					   
			vp_deal_width += 1.0												   
		ENDIF																	   
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_deal_width += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_deal_height += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_deal_height += -1.0
		ENDIF
	ENDIF

	// border thickness
	IF vp_edit_mode = 7
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			vp_border_thickness += 0.1
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			vp_border_thickness += -0.1
		ENDIF
	ENDIF

	// lines in table
	IF vp_edit_mode = 8
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 8
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			vp_line_y[vp_edit_counter] += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			vp_line_y[vp_edit_counter] += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			vp_line_x[vp_edit_counter] += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			vp_line_x[vp_edit_counter] += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_5
			temp_float = vp_line_width[vp_edit_counter]
			vp_line_width[vp_edit_counter] = vp_line_height[vp_edit_counter]
			vp_line_height[vp_edit_counter] = temp_float
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_line_width[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_line_width[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_line_height[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_line_height[vp_edit_counter] += -1.0
		ENDIF
	ENDIF

	// text 
	IF vp_edit_mode = 9

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 5
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			text_scale_y[vp_edit_counter] += -0.01	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			text_scale_y[vp_edit_counter] += 0.01	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			text_scale_x[vp_edit_counter] += 0.01	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			text_scale_x[vp_edit_counter] += -0.01	
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_R
			text_colour_r[vp_edit_counter] += 1
			WRITE_DEBUG_WITH_INT red text_colour_r[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_V
			text_colour_r[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT red text_colour_r[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_T
			text_colour_g[vp_edit_counter] += 1
			WRITE_DEBUG_WITH_INT green text_colour_g[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_G
			text_colour_g[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT green text_colour_g[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Y
			text_colour_b[vp_edit_counter] += 1
			WRITE_DEBUG_WITH_INT blue text_colour_b[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H
			text_colour_b[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT blue text_colour_b[vp_edit_counter]
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
			text_edge_r[vp_edit_counter] += 1		
			WRITE_DEBUG_WITH_INT red text_edge_r[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
			text_edge_r[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT red text_edge_r[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_I
			text_edge_g[vp_edit_counter] += 1
			WRITE_DEBUG_WITH_INT green text_edge_g[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K
			text_edge_g[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT green text_edge_g[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_O
			text_edge_b[vp_edit_counter] += 1
			WRITE_DEBUG_WITH_INT blue text_edge_b[vp_edit_counter]
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_L
			text_edge_b[vp_edit_counter] += -1
			WRITE_DEBUG_WITH_INT blue text_edge_b[vp_edit_counter]
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			text_edge[vp_edit_counter] += 1
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			text_edge[vp_edit_counter] += -1
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_PLUS
			text_font[vp_edit_counter] += 1
			IF text_font[vp_edit_counter] > 3
				text_font[vp_edit_counter] = 0
			ENDIF
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_MINUS
			text_font[vp_edit_counter] += -1
			IF text_font[vp_edit_counter] < 0
				text_font[vp_edit_counter] = 3
			ENDIF
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_SLASH
			IF text_centre[vp_edit_counter] = 0
				text_centre[vp_edit_counter] = 1
			ELSE
				text_centre[vp_edit_counter] = 0
			ENDIF
		ENDIF
	ENDIF

	// tiles														 
	IF vp_edit_mode = 10												 
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			TILE_HEIGHT += -1.0
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			TILE_HEIGHT += 1.0
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			TILE_WIDTH += 1.0
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			TILE_WIDTH += -1.0
		ENDIF
		
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			VAR_INT vp_tile_type
			vp_tile_type++
			IF vp_tile_type > 3
				vp_tile_type = 0
			ENDIF
			WRITE_DEBUG_WITH_INT vp_tile_type vp_tile_type
		ENDIF
	ENDIF

	// table background
	VAR_FLOAT vp_table_x vp_table_y vp_table_width vp_table_height
	IF vp_edit_mode = 11
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			vp_table_y += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			vp_table_y += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			vp_table_x += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			vp_table_x += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_table_height += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_table_height += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_table_width += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_table_width += -1.0
		ENDIF
	ENDIF

	// highlighted column
	VAR_FLOAT vp_highl_x[5] vp_highl_y[5] vp_highl_width[5] vp_highl_height[5]
		
	IF vp_edit_mode = 12
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
			vp_edit_counter++
			IF vp_edit_counter > 4
				vp_edit_counter = 0
			ENDIF
			WRITE_DEBUG_WITH_INT EDIT_COUNTER vp_edit_counter
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			vp_highl_y[vp_edit_counter] += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			vp_highl_y[vp_edit_counter] += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
			vp_highl_x[vp_edit_counter] += 1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
			vp_highl_x[vp_edit_counter] += -1.0	
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
			vp_highl_height[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
			vp_highl_height[vp_edit_counter] += -1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
			vp_highl_width[vp_edit_counter] += 1.0
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
			vp_highl_width[vp_edit_counter] += -1.0
		ENDIF
	ENDIF

	// button text offset
	IF vp_edit_mode = 13
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
			button_text_offset += 0.05
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
			button_text_offset += -0.05
		ENDIF
	ENDIF


	// output everything
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
	
		temp_int = 0
		WHILE temp_int < 8
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_screen_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_screen_x[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_screen_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_screen_y[temp_int]
		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int = 0
		WHILE temp_int < 5
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "card_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE card_x[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "card_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE card_y[temp_int]
		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_card_width = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_card_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_card_height = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_card_height

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		
		temp_int = 0
		WHILE temp_int < 10
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "row_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE row_y[temp_int]
		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int = 0
		WHILE temp_int < 6								   
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "col_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE col_x[temp_int]
		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE		

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_hold_button_offset = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_hold_button_offset
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_hold_width = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_hold_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_hold_height = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_hold_height

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "bet_one_x = "
		SAVE_FLOAT_TO_DEBUG_FILE bet_one_x
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "bet_one_y = "
		SAVE_FLOAT_TO_DEBUG_FILE bet_one_y
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_bet_one_width = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_bet_one_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_bet_one_height = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_bet_one_height

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE


		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "deal_x = "
		SAVE_FLOAT_TO_DEBUG_FILE deal_x
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "deal_y = "
		SAVE_FLOAT_TO_DEBUG_FILE deal_y
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_deal_width = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_deal_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_deal_height = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_deal_height

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_border_thickness = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_border_thickness

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int = 0
		WHILE temp_int < 9
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_line_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_line_x[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_line_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_line_y[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_line_width["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_line_width[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_line_height["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_line_height[temp_int]
		temp_int++
		ENDWHILE
	
		temp_int = 0
		WHILE temp_int < 6
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_scale_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE text_scale_x[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_scale_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE text_scale_y[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_colour_r["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_colour_r[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_colour_g["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_colour_g[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_colour_b["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_colour_b[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_font["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_font[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_centre["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_centre[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_edge["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_edge[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_edge_r["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_edge_r[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_edge_g["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_edge_g[temp_int]

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "text_edge_b["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE text_edge_b[temp_int]

			
		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "TILE_HEIGHT = "
		SAVE_FLOAT_TO_DEBUG_FILE TILE_HEIGHT
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "TILE_WIDTH = "
		SAVE_FLOAT_TO_DEBUG_FILE TILE_WIDTH
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_table_x = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_table_x
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_table_y = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_table_y
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_table_width = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_table_width
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "vp_table_height = "
		SAVE_FLOAT_TO_DEBUG_FILE vp_table_height


		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int = 0
		WHILE temp_int < 5

			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_highl_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_highl_x[temp_int] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_highl_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_highl_y[temp_int] 
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_highl_width["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_highl_width[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "vp_highl_height["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE vp_highl_height[temp_int] 

		temp_int++
		ENDWHILE


		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "button_text_offset = "
		SAVE_FLOAT_TO_DEBUG_FILE button_text_offset


	ENDIF


RETURN

VAR_INT vphud_int
vidpok_draw_hud:

	// get selected colour value
	VAR_INT vp_selected_colour
	VAR_INT vp_selected_colour_direction
	IF vp_selected_colour_direction = 0
		vp_selected_colour += 10
		IF vp_selected_colour >= 255
			vp_selected_colour = 255
			vp_selected_colour_direction = 1
		ENDIF 
	ELSE
		vp_selected_colour += -10
		
		IF vp_selected_colour <= 0
			vp_selected_colour = 0
			vp_selected_colour_direction = 0
		ENDIF
	ENDIF

	IF NOT vp_tile_type = 0
		temp_int = 0
		x = TILE_WIDTH / 2.0
		y = TILE_HEIGHT / 2.0

		WHILE y < 480.0
		AND temp_int < 100
			WHILE x < 640.0
			AND temp_int < 100
				IF vp_tile_type = 1
					DRAW_SPRITE 64 x y TILE_WIDTH TILE_HEIGHT 150 150 150 255
				ENDIF
				IF vp_tile_type = 2
					DRAW_SPRITE 65 x y TILE_WIDTH TILE_HEIGHT 150 150 150 255
				ENDIF
				temp_int++
				x += TILE_WIDTH
			ENDWHILE
			y += TILE_HEIGHT
			x = TILE_WIDTH / 2.0
		ENDWHILE
	ELSE
		DRAW_RECT 320.0 240.0 640.0 480.0 6 16 140 255 
	ENDIF

	// draw tv border
	DRAW_SPRITE	63 160.0 112.0 320.0 224.0 150 150 150 255
	DRAW_SPRITE	63 480.0 112.0 -320.0 224.0 150 150 150 255
	DRAW_SPRITE	63 480.0 336.0 -320.0 -224.0 150 150 150 255
	DRAW_SPRITE	63 160.0 336.0 320.0 -224.0 150 150 150 255

	// bet details
	GOSUB vp_txt_1
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT vp_screen_x[0] vp_screen_y[0] VP02

	GOSUB vp_txt_0
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT_WITH_NUMBER vp_screen_x[6] vp_screen_y[6] DOLLAR machine_value
	
	STORE_SCORE player1 temp_int
	GOSUB vp_update_casino_credit
	GOSUB vp_txt_0
	SET_TEXT_RIGHT_JUSTIFY ON
	temp_int2 = casino_credit * -1
	
	IF temp_int <= temp_int2
		DISPLAY_TEXT vp_screen_x[1] vp_screen_y[1] VP18  // NO CREDIT!!
	ELSE
		DISPLAY_TEXT_WITH_NUMBER vp_screen_x[1] vp_screen_y[1] VP16 temp_int  // CASH $~1~
	ENDIF
	
	GOSUB vp_txt_1
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_CENTRE ON
	temp_int = no_of_coins * machine_value

	DISPLAY_TEXT vp_screen_x[2] vp_screen_y[2] VP03

	GOSUB vp_txt_0
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT_WITH_NUMBER vp_screen_x[7] vp_screen_y[7] DOLLAR temp_int
	
	// instructions
	GOSUB vp_txt_3
	SET_TEXT_CENTRE ON	  
	DISPLAY_TEXT 320.0 vp_screen_y[4] VP17
	DRAW_RECT VP_TABLE_X VP_TABLE_Y VP_TABLE_WIDTH VP_TABLE_HEIGHT 0 0 0 255
	
	
	// payout table ------------------------------------
	
	
	// This stuff is for highlighted columns.

	IF NOT m_stage = 6
		SWITCH no_of_coins
			CASE 1
				DRAW_RECT vp_highl_x[0] vp_highl_y[0] vp_highl_width[0] vp_highl_height[0] TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255   // This stuff is for highlighted columns.
			BREAK													 
			CASE 2													 
				DRAW_RECT vp_highl_x[1] vp_highl_y[1] vp_highl_width[1] vp_highl_height[1] TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255
			BREAK													 
			CASE 3													 
				DRAW_RECT vp_highl_x[2] vp_highl_y[2] vp_highl_width[2] vp_highl_height[2] TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255
			BREAK													 
			CASE 4													 
				DRAW_RECT vp_highl_x[3] vp_highl_y[3] vp_highl_width[3] vp_highl_height[3] TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255
			BREAK													 
			CASE 5													 
				DRAW_RECT vp_highl_x[4] vp_highl_y[4] vp_highl_width[4] vp_highl_height[4] TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255
			BREAK
		ENDSWITCH
	ENDIF
	

	
	// highlight win

	IF m_stage = 6
	AND NOT hand_result = 0
		temp_int = 10 - hand_result
		temp_float = row_y[temp_int] + 9.0
	
		SWITCH no_of_coins
			CASE 1
				DRAW_RECT vp_highl_x[0] temp_float vp_highl_width[0] 18.0 255 vp_selected_colour 0 255  // This stuff is for highlighted columns.
			BREAK
			CASE 2
				DRAW_RECT vp_highl_x[1] temp_float vp_highl_width[1] 18.0 255 vp_selected_colour 0 255
			BREAK
			CASE 3
				DRAW_RECT vp_highl_x[2] temp_float vp_highl_width[2] 18.0 255 vp_selected_colour 0 255
			BREAK
			CASE 4
				DRAW_RECT vp_highl_x[3] temp_float vp_highl_width[3] 18.0 255 vp_selected_colour 0 255
			BREAK
			CASE 5
				DRAW_RECT vp_highl_x[4] temp_float vp_highl_width[4] 18.0 255 vp_selected_colour 0 255
			BREAK
		ENDSWITCH
		
		GOSUB vp_txt_0
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT_WITH_NUMBER vp_screen_x[3] vp_screen_y[3] VP04 temp_int3

	ELSE
		IF m_stage = 6
		AND hand_result = 0
			
			temp_int3 = 0

			GOSUB vp_txt_0
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE ON
			DISPLAY_TEXT_WITH_NUMBER vp_screen_x[3] vp_screen_y[3] VP04 temp_int3

		ENDIF
	ENDIF

	// names down left hand side
	
	GOSUB vp_txt_2
	DISPLAY_TEXT col_x[0] row_y[1] VP06  // Royal Flush
	GOSUB vp_txt_2
	DISPLAY_TEXT col_x[0] row_y[2] VP07  // Straight Flush
	GOSUB vp_txt_2												   
	DISPLAY_TEXT col_x[0] row_y[3] VP08  // Four of a kind								   
	GOSUB vp_txt_2												   
	DISPLAY_TEXT col_x[0] row_y[4] VP09  // Full House									   
	GOSUB vp_txt_2												   
	DISPLAY_TEXT col_x[0] row_y[5] VP10  // Flush										   
	GOSUB vp_txt_2												   
	DISPLAY_TEXT col_x[0] row_y[6] VP11  // Straight									   
	GOSUB vp_txt_2													   
	DISPLAY_TEXT col_x[0] row_y[7] VP12  // Three of a kind							   
	GOSUB vp_txt_2													   
	DISPLAY_TEXT col_x[0] row_y[8] VP13  // Two Pairs									   
	GOSUB vp_txt_2													   
	DISPLAY_TEXT col_x[0] row_y[9] VP14  // Pair of Jacks or Better					   
																				   
	// Coins header
	GOSUB vp_txt_3
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT vp_screen_x[5] vp_screen_y[5] VP05 // Coins Wagered

	// Column 1
	
	GOSUB vp_txt_3
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[0] VP15 1 

	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[1]  VP15 250
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[2]  VP15 50
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[3] VP15 25
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[4] VP15 9
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[5] VP15 6
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[6] VP15 4
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[7] VP15 3
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[8] VP15 2
	GOSUB vp_txt_4	
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[1] row_y[9] VP15 1

	// Column 2
	GOSUB vp_txt_3
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[0] VP15 2 
	
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[1]  VP15 500
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[2]  VP15 100
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[3] VP15 50
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[4] VP15 18
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[5] VP15 12
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[6] VP15 8
	GOSUB vp_txt_4			
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[7] VP15 6
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[8] VP15 4
	GOSUB vp_txt_4	
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[2] row_y[9] VP15 2

	// Column 3
	GOSUB vp_txt_3
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[0] VP15 3 
	
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[1]  VP15 750
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[2]  VP15 150
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[3] VP15 75
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[4] VP15 27
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[5] VP15 18
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[6] VP15 12
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[7] VP15 9
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[8] VP15 6
	GOSUB vp_txt_4	
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[3] row_y[9] VP15 3

	// Column 4
	GOSUB vp_txt_3
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[0] VP15 4 
	
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[1]  VP15 1000
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[2]  VP15 200
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[3] VP15 100
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[4] VP15 36
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[5] VP15 24
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[6] VP15 16
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[7] VP15 12
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[8] VP15 8
	GOSUB vp_txt_4	
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[4] row_y[9] VP15 4	

	// Column 5
	GOSUB vp_txt_3
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[0] VP15 5 
	
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[1]  VP15 4000
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[2]  VP15 250
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[3] VP15 125
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[4] VP15 45
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[5] VP15 30
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[6] VP15 20
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[7] VP15 15
	GOSUB vp_txt_4
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[8] VP15 10
	GOSUB vp_txt_4	
	SET_TEXT_RIGHT_JUSTIFY ON
	DISPLAY_TEXT_WITH_NUMBER col_x[5] row_y[9] VP15 5

	
	// draw lines in table  

	DRAW_RECT vp_line_x[0] vp_line_y[0]	vp_line_width[0] vp_line_height[0] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255 
	DRAW_RECT vp_line_x[1] vp_line_y[1]	vp_line_width[1] vp_line_height[1] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[2] vp_line_y[2]	vp_line_width[2] vp_line_height[2] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[3] vp_line_y[3]	vp_line_width[3] vp_line_height[3] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[4] vp_line_y[4]	vp_line_width[4] vp_line_height[4] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	
	DRAW_RECT vp_line_x[5] vp_line_y[5]	vp_line_width[5] vp_line_height[5] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[6] vp_line_y[6]	vp_line_width[6] vp_line_height[6] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[7] vp_line_y[7]	vp_line_width[7] vp_line_height[7] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	DRAW_RECT vp_line_x[8] vp_line_y[8]	vp_line_width[8] vp_line_height[8] TEXT_EDGE_R[0] TEXT_EDGE_G[0] TEXT_EDGE_B[0] 255
	
	vphud_int = 0
	WHILE vphud_int < 5

		// draw cards
		IF card_value[vphud_int] = 0
			DRAW_SPRITE 53 card_x[vphud_int] card_y[vphud_int] vp_card_width vp_card_height 128 128 128 255
		ELSE
			DRAW_SPRITE card_value[vphud_int] card_x[vphud_int] card_y[vphud_int] vp_card_width vp_card_height 128 128 128 255	
		ENDIF	
			
		// draw hold buttons
		x = card_x[vphud_int]
		y = card_y[vphud_int]
		y += vp_hold_button_offset
		// blank
		IF hold_value[vphud_int] = 0
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness
			DRAW_RECT x y vp_border_width vp_border_height 0 0 0 128
			DRAW_RECT x y vp_hold_width vp_hold_height 128 128 128 255
			GOSUB vp_txt_5
			SET_TEXT_COLOUR 128 128 128 255
			SET_TEXT_EDGE text_edge[5] 0 0 0 128
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float 
			DISPLAY_TEXT x y VP19
		ENDIF

		IF hold_value[vphud_int] = 1
			
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness

			DRAW_RECT x y vp_border_width vp_border_height 255 vp_selected_colour 0 128
			DRAW_RECT x y vp_hold_width vp_hold_height 128 128 128 255
						
			GOSUB vp_txt_5
			SET_TEXT_COLOUR 128 128 128 255
			SET_TEXT_EDGE text_edge[5] 0 0 0 128
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float
			
			DISPLAY_TEXT x y VP19
		ENDIF
		
		// green 
		IF hold_value[vphud_int] = 2
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness
			
			DRAW_RECT x y vp_border_width vp_border_height 0 0 0 255
			DRAW_RECT x y vp_hold_width vp_hold_height 0 128 0 255
			
			GOSUB vp_txt_5
			SET_TEXT_COLOUR 0 255 0 255
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float
			DISPLAY_TEXT x y VP19
		ENDIF

		IF hold_value[vphud_int] = 3
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness

			DRAW_RECT x y vp_border_width vp_border_height 255 vp_selected_colour 0 255
			DRAW_RECT x y vp_hold_width vp_hold_height 0 128 0 255
			
			GOSUB vp_txt_5
			SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float
			
			DISPLAY_TEXT x y VP19
		ENDIF
		
		// red
		IF hold_value[vphud_int] = 4
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness
			DRAW_RECT x y vp_border_width vp_border_height 0 0 0 255
			DRAW_RECT x y vp_hold_width vp_hold_height TEXT_COLOUR_R[1] TEXT_COLOUR_G[1] TEXT_COLOUR_B[1] 255
			GOSUB vp_txt_5
			SET_TEXT_COLOUR 255 0 0 255
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float
			DISPLAY_TEXT x y VP19
		ENDIF
		
		IF hold_value[vphud_int] = 5
			vp_border_width = vp_hold_width	+ vp_border_thickness
			vp_border_height = vp_hold_height + vp_border_thickness

			DRAW_RECT x y vp_border_width vp_border_height 255 vp_selected_colour 0 255
			DRAW_RECT x y vp_hold_width vp_hold_height text_colour_r[1] text_colour_g[1] text_colour_b[1] 255
			
			GOSUB vp_txt_5
			SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255
			temp_float = vp_hold_height / button_text_offset
			y -= temp_float
			DISPLAY_TEXT x y VP19
		ENDIF
			
	vphud_int++
	ENDWHILE

	IF bet_one_value = 0 
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 0 0 0 128 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT 128 128 128 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 128 128 128 255 
		SET_TEXT_EDGE text_edge[5] 0 0 0 128
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF bet_one_value = 1 
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 255 vp_selected_colour 0 128 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT 128 128 128 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 128 128 128 255 
		SET_TEXT_EDGE text_edge[5] 0 0 0 128
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF bet_one_value = 2 
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 0 0 0 255 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT 0 128 0 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 0 255 0 255 
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF bet_one_value = 3 
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 255 vp_selected_colour 0 255 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT 0 128 0 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255 
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF bet_one_value = 4
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 0 0 0 255 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT text_colour_r[1] text_colour_g[1] text_colour_b[1] 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 255 0 0 255 
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF bet_one_value = 5 
		vp_border_width = VP_BET_ONE_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_BET_ONE_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT bet_one_x bet_one_y vp_border_width vp_border_height 255 vp_selected_colour 0 255 
		DRAW_RECT bet_one_x bet_one_y VP_BET_ONE_WIDTH VP_BET_ONE_HEIGHT text_colour_r[1] text_colour_g[1] text_colour_b[1] 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255  
		temp_float = VP_BET_ONE_HEIGHT / button_text_offset 
		y = bet_one_y - temp_float
		DISPLAY_TEXT bet_one_x y VP20
	ENDIF

	IF deal_value = 0 
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 0 0 0 128 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT 128 128 128 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 128 128 128 255 
		SET_TEXT_EDGE text_edge[5] 0 0 0 128
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF

	IF deal_value = 1 
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 255 vp_selected_colour 0 128 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT 128 128 128 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 128 128 128 255 
		SET_TEXT_EDGE text_edge[5] 0 0 0 128
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF

	IF deal_value = 2 
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 0 0 0 255 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT 0 128 0 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 0 255 0 255 
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF

	IF deal_value = 3 
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 255 vp_selected_colour 0 255 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT 0 128 0 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255 
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF

	IF deal_value = 4
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 0 0 0 255 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT text_colour_r[1] text_colour_g[1] text_colour_b[1] 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR 255 0 0 255 
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF

	IF deal_value = 5 
		vp_border_width = VP_DEAL_WIDTH + VP_BORDER_THICKNESS
		vp_border_height = VP_DEAL_HEIGHT + VP_BORDER_THICKNESS
		DRAW_RECT deal_x deal_y vp_border_width vp_border_height 255 vp_selected_colour 0 255 
		DRAW_RECT deal_x deal_y VP_DEAL_WIDTH VP_DEAL_HEIGHT text_colour_r[1] text_colour_g[1] text_colour_b[1] 255 

		GOSUB vp_txt_5 
		SET_TEXT_COLOUR text_colour_r[5] vp_selected_colour text_colour_b[5] 255 
		temp_float = VP_DEAL_HEIGHT / button_text_offset 
		y = deal_y - temp_float
		DISPLAY_TEXT deal_x y VP21
	ENDIF
	
RETURN

VAR_FLOAT TILE_WIDTH TILE_HEIGHT

// ************************************************************
// 				CONVERT CARD VALUE
// ************************************************************

	// Function for getting the value of a card
	VAR_INT vp_in_card_value	// input
	VAR_INT vp_out_card_value	// output
	vidpok_convert_card_value:
		IF vp_in_card_value <	27
		AND vp_in_card_value > 13
			vp_out_card_value = vp_in_card_value - 13
		ENDIF 
		
		IF vp_in_card_value <	40
		AND vp_in_card_value > 26
			vp_out_card_value = vp_in_card_value - 26
		ENDIF 
		
		IF vp_in_card_value <	53
		AND vp_in_card_value > 39
			vp_out_card_value = vp_in_card_value - 39
		ENDIF

		IF vp_in_card_value <= 13
			vp_out_card_value = vp_in_card_value
		ENDIF
	RETURN
	vidpok_convert_card_suit:
		IF vp_in_card_value <	14
			vp_out_card_value = 1
		ELSE
			IF vp_in_card_value <	27
				vp_out_card_value = 2
			ELSE
				IF vp_in_card_value <	40
					vp_out_card_value = 3
				ELSE
					vp_out_card_value = 4			
				ENDIF					
			ENDIF			
		ENDIF 
	RETURN


// ************************************************************
// 				BASTARDISE_DEALING
// ************************************************************
bastardise_dealing1:
RETURN
bastardise_dealing2:
RETURN


// ************************************************************
// 				TEXT GOSUBS
// ************************************************************
vp_txt_0:
	SET_TEXT_SCALE text_scale_x[0] text_scale_y[0]
	SET_TEXT_COLOUR text_colour_r[0] text_colour_g[0] text_colour_b[0] 255
	
	IF text_centre[0] = 1
		SET_TEXT_CENTRE ON
	ENDIF

	SWITCH text_font[0]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[0] text_edge_r[0] text_edge_g[0] text_edge_b[0] 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
vp_txt_1:
	SET_TEXT_SCALE text_scale_x[1] text_scale_y[1]
	SET_TEXT_COLOUR text_colour_r[1] text_colour_g[1] text_colour_b[1] 255
	
	IF text_centre[1] = 1
		SET_TEXT_CENTRE ON
	ENDIF	
	SWITCH text_font[1]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[1] text_edge_r[1] text_edge_g[1] text_edge_b[1] 255
	//SET_TEXT_EDGE text_edge[1] 0 0 0 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN

vp_txt_2:
	SET_TEXT_SCALE text_scale_x[2] text_scale_y[2]
	SET_TEXT_COLOUR text_colour_r[2] text_colour_g[2] text_colour_b[2] 255
	IF text_centre[2] = 1
		SET_TEXT_CENTRE ON
	ENDIF		
	SWITCH text_font[2]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[2] text_edge_r[2] text_edge_g[2] text_edge_b[2] 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
vp_txt_3:
	SET_TEXT_SCALE text_scale_x[3] text_scale_y[3]
	SET_TEXT_COLOUR text_colour_r[3] text_colour_g[3] text_colour_b[3] 255
	IF text_centre[3] = 1
		SET_TEXT_CENTRE ON
	ENDIF	
	SWITCH text_font[3]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[3] text_edge_r[3] text_edge_g[3] text_edge_b[3] 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
vp_txt_4:
	SET_TEXT_SCALE text_scale_x[4] text_scale_y[4]
	SET_TEXT_COLOUR text_colour_r[4] text_colour_g[4] text_colour_b[4] 255
	IF text_centre[4] = 1
		SET_TEXT_CENTRE ON
	ENDIF		
	SWITCH text_font[4]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[4] text_edge_r[4] text_edge_g[4] text_edge_b[4] 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN

vp_txt_5:
	SET_TEXT_SCALE text_scale_x[5] text_scale_y[5]
	
	SET_TEXT_COLOUR text_colour_r[5] text_colour_g[5] text_colour_b[5] 255
	
	IF text_centre[5] = 1
		SET_TEXT_CENTRE ON
	ENDIF		
	SWITCH text_font[5]	
		CASE 0
			SET_TEXT_FONT FONT_BANK	
		BREAK
		CASE 1
			SET_TEXT_FONT FONT_STANDARD
		BREAK
		CASE 2
			SET_TEXT_FONT FONT_SPACEAGE
		BREAK
		CASE 3
			SET_TEXT_FONT FONT_HEADING
		BREAK
		DEFAULT
			SET_TEXT_FONT FONT_BANK
		BREAK
	ENDSWITCH
	SET_TEXT_EDGE text_edge[5] text_edge_r[5] text_edge_g[5] text_edge_b[5] 255
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN



vp_txt_med_blue_left:
	SET_TEXT_SCALE 0.54 1.44 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN
vp_txt_med_blue_centre:
	SET_TEXT_SCALE 0.54 1.44 
	SET_TEXT_COLOUR 128 148 178 255
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 2000.0
	SET_TEXT_PROPORTIONAL ON
RETURN


vp_update_casino_credit:

	GET_FLOAT_STAT GAMBLING temp_float
	IF temp_float < 50.0
		casino_credit = 100
	ELSE
		IF temp_float < 150.0
			casino_credit = 1000
		ELSE
			IF temp_float < 350.0
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
// 				CLEANUP
// ************************************************************
	vidpok_cleanup:

		SET_MINIGAME_IN_PROGRESS FALSE

		m_stage 	= 0
		m_goals 	= 0
		m_passed	= 0
		m_failed	= 0	  
		m_quit		= 0

		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME cashwin
		CLEAR_PRINTS
		CLEAR_HELP

		CLEAR_MISSION_AUDIO 4
	
		REMOVE_TEXTURE_DICTIONARY
		USE_TEXT_COMMANDS FALSE
		SET_PLAYER_CONTROL player1 ON
		DISPLAY_HUD TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
	RETURN
}
MISSION_END

