MISSION_START

{
// global variables
VAR_INT status_MENU value_MENU[10] load_2p_MENU


// holds values of menu
VAR_INT amount_of_menu_items_MENU current_menu_position_MENU menu_button_delay_MENU

// holds different values of menu options
VAR_INT amount_of_option_values_MENU[10] current_option_value_MENU[10] option_value_display_status_MENU[10] amount_of_values_per_option_MENU
VAR_TEXT_LABEL title_MENU option_text_MENU[10]

// arrays for storing values of menu 
VAR_INT option_number_values_MENU[100]
VAR_TEXT_LABEL option_text_values_MENU[100] 

// temp variables used for displaying menu and grabbing values from arrays 
VAR_INT temp_int_1_MENU temp_int_2_MENU temp_int_3_MENU temp_int_4_MENU temp_int_5_MENU 
VAR_FLOAT temp_float_1_MENU temp_float_2_MENU 

SCRIPT_NAME MENUSK

SET_DEATHARREST_STATE OFF

load_2p_MENU = FALSE

skeleton_menu_wait:

	WHILE load_2p_MENU = FALSE		
		
		WAIT 0

	ENDWHILE

	status_MENU = 0

skeleton_menu_settings:

	
	LOAD_MISSION_TEXT MENU2P

	amount_of_values_per_option_MENU = 10 // only change if more values needed or tidying up menu

	// RESET GLOBAL VARIABLES
	temp_int_1_MENU = 0

	WHILE temp_int_1_MENU < 10
		value_MENU[temp_int_1_MENU] = -1
		temp_int_1_MENU ++ 
	ENDWHILE

	//LOAD CORRECT MENU FOR TWO PLAYER MISSION

	IF two_player_mission = 0
		GOSUB mulitplayer_menu_mission_0 
	ELSE
		IF two_player_mission = 1
			GOSUB mulitplayer_menu_mission_1 
		ELSE
			IF two_player_mission = 2
				GOSUB mulitplayer_menu_mission_2 
			ELSE	
				IF two_player_mission = 3
					GOSUB mulitplayer_menu_mission_3 
				ELSE
					IF two_player_mission = 4
						GOSUB mulitplayer_menu_mission_4 
					ELSE
						IF two_player_mission = 5
							GOSUB mulitplayer_menu_mission_5 
						ELSE
							IF two_player_mission = 6
								GOSUB mulitplayer_menu_mission_6 
							ELSE
								IF two_player_mission = 7
									GOSUB mulitplayer_menu_mission_7 
								ELSE
									IF two_player_mission = 8
										GOSUB mulitplayer_menu_mission_8 
									ELSE
										IF two_player_mission = 9
											GOSUB mulitplayer_menu_mission_9 
										ELSE
											IF two_player_mission = 10
												GOSUB mulitplayer_menu_mission_10 
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF																	

	USE_TEXT_COMMANDS TRUE
	
	TIMERA = 0
	menu_button_delay_MENU = 0



skeleton_menu_select:


	WAIT 0

	// ##################
	// ## PLAYER INPUT ##
	// ##################

	IF TIMERA > menu_button_delay_MENU
	
		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

		IF LStickY < -65
		//OR IS_BUTTON_PRESSED PAD1 DPADUP
			current_menu_position_MENU --
			// CHECK TO SEND PLAYER TO BOTTOM OF MENU IF THE PUSH UP AT THE TOP
			IF current_menu_position_MENU = -1
				temp_int_1_MENU = amount_of_menu_items_MENU - 1
				current_menu_position_MENU = temp_int_1_MENU
			ENDIF

			TIMERA = 0
			menu_button_delay_MENU = 170
			 	 			
		ELSE
			IF LStickY > 65
			//OR IS_BUTTON_PRESSED PAD1 DPADDOWN
				current_menu_position_MENU ++
				// CHECK TO SEND PLAYER TO TOP OF THE MENU IF THEY PUSH DON AT THE BOTTOM
				IF current_menu_position_MENU = amount_of_menu_items_MENU
					current_menu_position_MENU = 0
				ENDIF

				TIMERA = 0
				menu_button_delay_MENU = 170
					
			ELSE
				IF LStickX < -65
				//OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				
					// check for decrement					

					temp_int_1_MENU = current_menu_position_MENU * amount_of_values_per_option_MENU
					IF option_number_values_MENU[temp_int_1_MENU] = -1
					
						temp_int_1_MENU ++
						temp_int_2_MENU = option_number_values_MENU[temp_int_1_MENU] // lowest value
						temp_int_1_MENU ++
						temp_int_3_MENU = option_number_values_MENU[temp_int_1_MENU] // highest value
						temp_int_1_MENU ++
						temp_int_4_MENU = option_number_values_MENU[temp_int_1_MENU] // current value

						temp_int_4_MENU --

						IF temp_int_4_MENU < temp_int_2_MENU
							option_number_values_MENU[temp_int_1_MENU] = temp_int_3_MENU	 
						ELSE
							option_number_values_MENU[temp_int_1_MENU] = temp_int_4_MENU 
						ENDIF
						
						 
					ELSE 
					
						current_option_value_MENU[current_menu_position_MENU] --

						IF current_option_value_MENU[current_menu_position_MENU] = -1 
							temp_int_1_MENU = amount_of_option_values_MENU[current_menu_position_MENU] - 1
							current_option_value_MENU[current_menu_position_MENU] = temp_int_1_MENU
						ENDIF
					
					ENDIF

					TIMERA = 0
					menu_button_delay_MENU = 170
									
				ELSE
					IF LStickX > 65
					//OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
					
						// check for increment

						temp_int_1_MENU = current_menu_position_MENU * amount_of_values_per_option_MENU
						IF option_number_values_MENU[temp_int_1_MENU] = -1
						
							temp_int_1_MENU ++
							temp_int_2_MENU = option_number_values_MENU[temp_int_1_MENU] // lowest value
							temp_int_1_MENU ++
							temp_int_3_MENU = option_number_values_MENU[temp_int_1_MENU] // highest value
							temp_int_1_MENU ++
							temp_int_4_MENU = option_number_values_MENU[temp_int_1_MENU] // current value

							temp_int_4_MENU ++

							IF temp_int_4_MENU > temp_int_3_MENU
								option_number_values_MENU[temp_int_1_MENU] = temp_int_2_MENU	 
							ELSE
								option_number_values_MENU[temp_int_1_MENU] = temp_int_4_MENU 
							ENDIF
						
						ELSE 
											
							current_option_value_MENU[current_menu_position_MENU] ++
							
							IF current_option_value_MENU[current_menu_position_MENU] = amount_of_option_values_MENU[current_menu_position_MENU] 
								current_option_value_MENU[current_menu_position_MENU] = 0
							ENDIF

						ENDIF

						TIMERA = 0
						menu_button_delay_MENU = 170

					ELSE
						IF IS_BUTTON_PRESSED PAD1 CROSS

							temp_int_1_MENU = amount_of_menu_items_MENU - 1 
							IF current_menu_position_MENU = temp_int_1_MENU // start game
														
								temp_int_2_MENU = 0
								
								WHILE temp_int_2_MENU < amount_of_menu_items_MENU 

									temp_int_5_MENU = temp_int_2_MENU * amount_of_values_per_option_MENU
									temp_int_4_MENU = current_option_value_MENU[temp_int_2_MENU]
									temp_int_3_MENU = temp_int_5_MENU + temp_int_4_MENU
									
									IF NOT option_number_values_MENU[temp_int_5_MENU] = -4
									 
										IF option_number_values_MENU[temp_int_5_MENU] = -1
											temp_int_5_MENU = temp_int_5_MENU + 3
											temp_int_3_MENU = temp_int_5_MENU	
										ENDIF

										value_MENU[temp_int_2_MENU] = option_number_values_MENU[temp_int_3_MENU]
										 
									ENDIF

									temp_int_2_MENU ++

								ENDWHILE

								status_MENU = 1
								load_2p_MENU = FALSE
								GOTO skeleton_menu_wait 
							ENDIF

						ELSE
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								status_MENU = 2
								load_2p_MENU = FALSE
								GOTO skeleton_menu_wait 
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// ###################
	// ## SCREEN UPDATE ##
	// ###################
	 
	// title_MENU
	DRAW_RECT 310.5000 35.0000 360.0000 44.0000 0 0 0 180
	SET_TEXT_SCALE 1.3 1.5 
	SET_TEXT_COLOUR 255 255 255 150 
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT 324.0000 23.0000 $title_MENU
	
	// menu options
	temp_int_1_MENU = 0
	temp_float_1_MENU = 100.0
	temp_float_2_MENU = 90.0

	WHILE temp_int_1_MENU < amount_of_menu_items_MENU 

	temp_int_5_MENU = temp_int_1_MENU * amount_of_values_per_option_MENU
	temp_int_4_MENU = current_option_value_MENU[temp_int_1_MENU]
	temp_int_3_MENU = temp_int_5_MENU + temp_int_4_MENU

	DRAW_RECT 228.5 temp_float_1_MENU 405.0 25.0 0 0 0 130
	
	IF NOT option_number_values_MENU[temp_int_3_MENU] = -4 
		DRAW_RECT 532.0 temp_float_1_MENU 175.0 25.0 0 0 0 180
	ENDIF

	IF current_menu_position_MENU = temp_int_1_MENU
		temp_int_2_MENU = 255
	ELSE
		temp_int_2_MENU = 130
	ENDIF

	SET_TEXT_WRAPX 700.0
	
	SET_TEXT_SCALE 0.9287 1.7775 
	SET_TEXT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 temp_int_2_MENU
	
	DISPLAY_TEXT 31.0 temp_float_2_MENU $option_text_MENU[temp_int_1_MENU]
	
	IF NOT option_number_values_MENU[temp_int_3_MENU] = -4
		
		SET_TEXT_WRAPX 700.0
		SET_TEXT_SCALE 0.85 1.7775
		SET_TEXT_COLOUR 255 255 255 temp_int_2_MENU
	

		IF option_number_values_MENU[temp_int_5_MENU] = -1
			temp_int_5_MENU = temp_int_5_MENU + 3
			temp_int_3_MENU = temp_int_5_MENU	
		ENDIF

		IF option_value_display_status_MENU[temp_int_1_MENU] = 0 // BOTH TEXT AND NUMBER   
			DISPLAY_TEXT_WITH_NUMBER 450.0 temp_float_2_MENU $option_text_values_MENU[temp_int_3_MENU] option_number_values_MENU[temp_int_3_MENU]
		ELSE
			IF option_value_display_status_MENU[temp_int_1_MENU] = 1 // JUST TEXT
				DISPLAY_TEXT 450.0 temp_float_2_MENU $option_text_values_MENU[temp_int_3_MENU]	
			ELSE
				IF option_value_display_status_MENU[temp_int_1_MENU] = 2 // JUST NUMBER
					DISPLAY_TEXT_WITH_NUMBER 450.0 temp_float_2_MENU MENU_11 option_number_values_MENU[temp_int_3_MENU]
				ENDIF
			ENDIF
		ENDIF
	ENDIF			

	temp_int_1_MENU ++
	temp_float_1_MENU = temp_float_1_MENU + 35.0
	temp_float_2_MENU = temp_float_2_MENU + 35.0

	ENDWHILE

 


GOTO skeleton_menu_select







// ##
// ## SETS UP MISSION VARIABLES ############################################################################################
// ##

mulitplayer_menu_mission_0: // p2_sumo.sc - steve

	// title_MENU
	$title_MENU = MENU_00 // SUMO

	// OPTIONS
	$option_text_MENU[0] = MENU_13 	// Number of players
	$option_text_MENU[1] = MENU_14 	// CPU opponents
	$option_text_MENU[2] = MENU_15 	// Knockouts to win
	$option_text_MENU[3] = MENU_12 	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0
	
	//VALUES
	// option 1 - number of players
	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2

	amount_of_option_values_MENU[0] = 2
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 2 // numbers only

	// option 2 - CPU opponents
	option_number_values_MENU[10] = -1																						 
	option_number_values_MENU[11] = 0																						 
	option_number_values_MENU[12] = 4																						 
	option_number_values_MENU[13] = 0																						 

	amount_of_option_values_MENU[1] = 4 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 // numbers only

	// option 3 - Knockouts to win 
	option_number_values_MENU[20] = 1																						 
	option_number_values_MENU[21] = 3																						 
	option_number_values_MENU[22] = 5																						 
	option_number_values_MENU[23] = 7																						 
	option_number_values_MENU[24] = 10																						 

	amount_of_option_values_MENU[2] = 5
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 2 	// numbers only

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 
											
	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN


mulitplayer_menu_mission_1: // survive.sc - steve

	// title_MENU
	$title_MENU = MENU_01 // SURVIVE

	// OPTIONS
	$option_text_MENU[0] = MENU_13 	// Number of players
	$option_text_MENU[1] = MENU_16 	// Random pickups
	$option_text_MENU[2] = MENU_17 	// Continues
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES
	// option 1 - number of players
	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2

	amount_of_option_values_MENU[0] = 2
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 2 // numbers only

	// option 2 - Random Pickups
	$option_text_values_MENU[10] =  MENU_18 // Yes  																				 
	$option_text_values_MENU[11] =  MENU_19 // No																					 

	option_number_values_MENU[10] = 1																						 
	option_number_values_MENU[11] = 0																						 

	amount_of_option_values_MENU[1] = 2 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 1 // text only

	// option 3 - Continues 
	$option_text_values_MENU[20] =  MENU_18 // Yes  																				 
	$option_text_values_MENU[21] =  MENU_19 // No																					 
																					 
	option_number_values_MENU[20] = 1																						 
	option_number_values_MENU[21] = 0																					 
																					 
	amount_of_option_values_MENU[2] = 2
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 1 	// text only

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none
	
RETURN


mulitplayer_menu_mission_2: // boxing.sc - dave

	// title_MENU
	$title_MENU = MENU_02 // BOXING

	// OPTIONS
	$option_text_MENU[0] = MENU_20 // Number of rounds
	$option_text_MENU[1] = MENU_12 // Start Game

	amount_of_menu_items_MENU = 2 
	current_menu_position_MENU = 0

	// VALUES
	// option 1 - number of rounds
	option_number_values_MENU[0] = -1																						 
	option_number_values_MENU[1] = 1																						 
	option_number_values_MENU[2] = 15																						 
	option_number_values_MENU[3] = 1																						 

	amount_of_option_values_MENU[0] = 4 
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 2 	// numbers only

	// option 4 - Start game 
	option_number_values_MENU[10] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[1] = 1
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 3 	// none

RETURN


mulitplayer_menu_mission_3: // ram_2p.sc - paul
	
	// title_MENU
	$title_MENU = MENU_03 // ram 2p

	// OPTIONS
	$option_text_MENU[0] = MENU_21 	// Location
	$option_text_MENU[1] = MENU_26 	// Ped limit
	$option_text_MENU[2] = MENU_29 	// time limit
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES

	// option 1 - location
	$option_text_values_MENU[0] = MENU_22
	$option_text_values_MENU[1] = MENU_23
	$option_text_values_MENU[2] = MENU_24 
	$option_text_values_MENU[3] = MENU_25

	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2
	option_number_values_MENU[2] = 3
	option_number_values_MENU[3] = 4

	amount_of_option_values_MENU[0] = 4
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 1 	// text only

	// option 2 - ped limit
	option_number_values_MENU[10] = 10																						 
	option_number_values_MENU[11] = 20																						 
	option_number_values_MENU[12] = 30																						 
	option_number_values_MENU[13] = 40
	option_number_values_MENU[14] = 50

	amount_of_option_values_MENU[1] = 5 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 	// numbers only

	// option 3 - time limit 
	$option_text_values_MENU[20] = MENU_30 	// mins  																				 
	$option_text_values_MENU[21] = MENU_30																					 
	$option_text_values_MENU[22] = MENU_30  																				 
	$option_text_values_MENU[23] = MENU_30
																					 
	option_number_values_MENU[20] = 2																						 
	option_number_values_MENU[21] = 4																					 
	option_number_values_MENU[22] = 6																						 
	option_number_values_MENU[23] = 8																						 

	amount_of_option_values_MENU[2] = 4
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 0 	// both

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN


mulitplayer_menu_mission_4: // tag.sc - dave

	// title_MENU
	$title_MENU = MENU_04 // TAG

	// OPTIONS
	$option_text_MENU[0] = MENU_29 // time limit
	$option_text_MENU[1] = MENU_12 // Start Game

	amount_of_menu_items_MENU = 2 
	current_menu_position_MENU = 0

	// VALUES
	// option 1 - time limit
	$option_text_values_MENU[0] =  MENU_31  																				 
	$option_text_values_MENU[1] =  MENU_30																					 
	$option_text_values_MENU[2] =  MENU_30																			 
	$option_text_values_MENU[3] =  MENU_30																					 
	$option_text_values_MENU[4] =  MENU_30
																																 
	option_number_values_MENU[0] = 1																						 
	option_number_values_MENU[1] = 2																						 
	option_number_values_MENU[2] = 3																						 
	option_number_values_MENU[3] = 4																						 
	option_number_values_MENU[4] = 5

	amount_of_option_values_MENU[0] = 5 
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 0 	// both

	// option 2 - Start game 
	option_number_values_MENU[10] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[1] = 1
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 3 	// none

RETURN


mulitplayer_menu_mission_5: // fixed shooting.sc - dave

	// title_MENU
	$title_MENU = MENU_05 // fixed shooting

	// OPTIONS
	$option_text_MENU[0] = MENU_29 // time limit
	$option_text_MENU[1] = MENU_12 // Start Game

	amount_of_menu_items_MENU = 2 
	current_menu_position_MENU = 0

	// option 1 - time limit
	$option_text_values_MENU[0] =  MENU_31  																				 
	$option_text_values_MENU[1] =  MENU_30																					 
	$option_text_values_MENU[2] =  MENU_30																			 
	$option_text_values_MENU[3] =  MENU_30																					 
	$option_text_values_MENU[4] =  MENU_30
																																 
	option_number_values_MENU[0] = 1																						 
	option_number_values_MENU[1] = 2																						 
	option_number_values_MENU[2] = 3																						 
	option_number_values_MENU[3] = 4																						 
	option_number_values_MENU[4] = 5

	amount_of_option_values_MENU[0] = 5 
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 0 	// both

	// option 2 - Start game 
	option_number_values_MENU[10] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[1] = 1
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 3 	// none
RETURN


mulitplayer_menu_mission_6: // kill_2p.sc - dave

	// title_MENU
	$title_MENU = MENU_06 // kill_2p

	// OPTIONS
	$option_text_MENU[0] = MENU_29 // time limit
	$option_text_MENU[1] = MENU_12 // Start Game

	amount_of_menu_items_MENU = 2 
	current_menu_position_MENU = 0

	// option 1 - time limit
	$option_text_values_MENU[0] =  MENU_31  																				 
	$option_text_values_MENU[1] =  MENU_30																					 
	$option_text_values_MENU[2] =  MENU_30																			 
	$option_text_values_MENU[3] =  MENU_30																					 
	$option_text_values_MENU[4] =  MENU_30
																																 
	option_number_values_MENU[0] = 1																						 
	option_number_values_MENU[1] = 2																						 
	option_number_values_MENU[2] = 3																						 
	option_number_values_MENU[3] = 4																						 
	option_number_values_MENU[4] = 5

	amount_of_option_values_MENU[0] = 5 
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 0 	// both

	// option 2 - Start game 
	option_number_values_MENU[10] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[1] = 1
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 3 	// none
RETURN


mulitplayer_menu_mission_7: // bike_2p.sc - paul

	// title_MENU
	$title_MENU = MENU_07 // bike 2p

	// OPTIONS
	$option_text_MENU[0] = MENU_21 	// Location
	$option_text_MENU[1] = MENU_27 	// bike limit
	$option_text_MENU[2] = MENU_29 	// time limit
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES

	// option 1 - location
	$option_text_values_MENU[0] = MENU_22 
	$option_text_values_MENU[1] = MENU_23
	$option_text_values_MENU[2] = MENU_24 
	$option_text_values_MENU[3] = MENU_25

	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2
	option_number_values_MENU[2] = 3
	option_number_values_MENU[3] = 4

	amount_of_option_values_MENU[0] = 4
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 1 // text only

	// option 2 - car/bike limit
	option_number_values_MENU[10] = 5																						 
	option_number_values_MENU[11] = 10																						 
	option_number_values_MENU[12] = 15																						 
	option_number_values_MENU[13] = 20

	amount_of_option_values_MENU[1] = 4 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 // numbers only

	// option 3 - time limit 
	$option_text_values_MENU[20] = MENU_30  																				 
	$option_text_values_MENU[21] = MENU_30																					 
	$option_text_values_MENU[22] = MENU_30  																				 

	option_number_values_MENU[20] = 2																						 
	option_number_values_MENU[21] = 4																					 
	option_number_values_MENU[22] = 6																						 
																					 
	amount_of_option_values_MENU[2] = 3
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 0 	// both

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN


mulitplayer_menu_mission_8: // cars_2p.sc - paul
	
	// title_MENU
	$title_MENU = MENU_08 // cars 2p 

	// OPTIONS
	$option_text_MENU[0] = MENU_21 	// Location
	$option_text_MENU[1] = MENU_28 	// car limit
	$option_text_MENU[2] = MENU_29 	// time limit
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES

	// option 1 - location
	$option_text_values_MENU[0] = MENU_22 
	$option_text_values_MENU[1] = MENU_23
	$option_text_values_MENU[2] = MENU_24 
	$option_text_values_MENU[3] = MENU_25

	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2
	option_number_values_MENU[2] = 3
	option_number_values_MENU[3] = 4

	amount_of_option_values_MENU[0] = 4
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 1 // text only

	// option 2 - car/bike limit
	option_number_values_MENU[10] = 5																						 
	option_number_values_MENU[11] = 10																						 
	option_number_values_MENU[12] = 15																						 
	option_number_values_MENU[13] = 20

	amount_of_option_values_MENU[1] = 4 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 // numbers only

	// option 3 - time limit 
	$option_text_values_MENU[20] = MENU_30  																				 
	$option_text_values_MENU[21] = MENU_30																					 
	$option_text_values_MENU[22] = MENU_30  																				 

	option_number_values_MENU[20] = 2																						 
	option_number_values_MENU[21] = 4																					 
	option_number_values_MENU[22] = 6																						 
																					 
	amount_of_option_values_MENU[2] = 3
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 0 	// both

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN


mulitplayer_menu_mission_9: // heli_2p.sc - paul
	
	// title_MENU
	$title_MENU = MENU_09 // heli 2p

	// OPTIONS
	$option_text_MENU[0] = MENU_21 	// Location
	$option_text_MENU[1] = MENU_28 	// car limit
	$option_text_MENU[2] = MENU_29 	// time limit
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES

	// option 1 - location
	$option_text_values_MENU[0] = MENU_22 
	$option_text_values_MENU[1] = MENU_23
	$option_text_values_MENU[2] = MENU_24 
	$option_text_values_MENU[3] = MENU_25

	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2
	option_number_values_MENU[2] = 3
	option_number_values_MENU[3] = 4

	amount_of_option_values_MENU[0] = 4
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 1 // text only

	// option 2 - car/bike limit
	option_number_values_MENU[10] = 5																						 
	option_number_values_MENU[11] = 10																						 
	option_number_values_MENU[12] = 15																						 
	option_number_values_MENU[13] = 20

	amount_of_option_values_MENU[1] = 4 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 // numbers only

	// option 3 - time limit 
	$option_text_values_MENU[20] = MENU_30  																				 
	$option_text_values_MENU[21] = MENU_30																					 
	$option_text_values_MENU[22] = MENU_30  																				 

	option_number_values_MENU[20] = 2																						 
	option_number_values_MENU[21] = 4																					 
	option_number_values_MENU[22] = 6																						 
																					 
	amount_of_option_values_MENU[2] = 3
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 0 	// both

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN


mulitplayer_menu_mission_10: // peds_2p.sc - paul

	// title_MENU
	$title_MENU = MENU_10 // bike 2p

	// OPTIONS
	$option_text_MENU[0] = MENU_21 	// Location
	$option_text_MENU[1] = MENU_28 	// car limit
	$option_text_MENU[2] = MENU_29 	// time limit
	$option_text_MENU[3] = MENU_12	// Start Game

	amount_of_menu_items_MENU = 4 	// there will be 4 options for the player to choose
	current_menu_position_MENU = 0

	// VALUES

	// option 1 - location
	$option_text_values_MENU[0] = MENU_22 
	$option_text_values_MENU[1] = MENU_23
	$option_text_values_MENU[2] = MENU_24 
	$option_text_values_MENU[3] = MENU_25

	option_number_values_MENU[0] = 1
	option_number_values_MENU[1] = 2
	option_number_values_MENU[2] = 3
	option_number_values_MENU[3] = 4

	amount_of_option_values_MENU[0] = 4
	current_option_value_MENU[0] = 0
	option_value_display_status_MENU[0] = 1 // text only

	// option 2 - car/bike limit
	option_number_values_MENU[10] = 5																						 
	option_number_values_MENU[11] = 10																						 
	option_number_values_MENU[12] = 15																						 
	option_number_values_MENU[13] = 20

	amount_of_option_values_MENU[1] = 4 
	current_option_value_MENU[1] = 0
	option_value_display_status_MENU[1] = 2 // numbers only

	// option 3 - time limit 
	$option_text_values_MENU[20] = MENU_30  																				 
	$option_text_values_MENU[21] = MENU_30																					 
	$option_text_values_MENU[22] = MENU_30  																				 

	option_number_values_MENU[20] = 2																						 
	option_number_values_MENU[21] = 4																					 
	option_number_values_MENU[22] = 6																						 
																					 
	amount_of_option_values_MENU[2] = 3
	current_option_value_MENU[2] = 0
	option_value_display_status_MENU[2] = 0 	// both

	// option 4 - Start game 
	option_number_values_MENU[30] = -4 		// setting this stops a rectangle being drawn for the option value on the right of the screen																						 

	amount_of_option_values_MENU[3] = 1
	current_option_value_MENU[3] = 0
	option_value_display_status_MENU[3] = 3 	// none

RETURN

MISSION_END
 
}


