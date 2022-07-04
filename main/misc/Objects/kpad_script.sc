MISSION_START

// ***********************************************************************************************
//
//						 -------------		  
//						|  [****] ::  |	  KEYPAD - Neil
//						|			  |
//						|  A   B   C  |	  Used for opening doors.	
//						|  D   E   F  |	  They operate exactly the same way as 'Mastermind'
//						 ------------- 
//
// ***********************************************************************************************
VAR_INT kpad_numbers
VAR_INT kpad_press
VAR_INT kpad_lightA[4]
VAR_INT kpad_lightB[4]
VAR_INT kpad_display[4]
VAR_INT kpad_denied_obj
VAR_INT kpad_granted_obj
VAR_INT kpad_input[4]
VAR_INT kpad_partial[4]
VAR_INT kpad_this_is_partial
VAR_INT kpad_right kpad_half_right
VAR_INT kpad_highlight

VAR_INT kpad_automatic_solve
VAR_INT kpad_attempts
VAR_INT kpad_highlighted_pos
VAR_INT kpad_button_is_pressed


VAR_INT kpad_help_text1

// parameters
VAR_INT kpad_solution[4]
// return variables	 
VAR_INT   kpad_result
VAR_FLOAT kpad_x kpad_y kpad_z kpad_h


{///////////////////////////////////////////////////////////////////////////////
kpad_script: 	////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME KPAD

LVAR_INT kpad
LVAR_INT kpad_comb_A kpad_comb_B kpad_comb_C kpad_comb_D

LVAR_INT flag
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT quit
LVAR_INT kpad_loaded

GOTO kpad_fake_create
	CREATE_OBJECT KMB_KEYPAD 0.0 0.0 0.0 kpad
kpad_fake_create:

quit = 0
kpad_loaded	= 0

kpad_comb_A = 0
kpad_comb_B = 1
kpad_comb_C = 2
kpad_comb_D	= 3



kpad_loop:
WAIT 0														   
			
	//VIEW_INTEGER_VARIABLE kpad_result kpad_result
															   
	IF DOES_OBJECT_EXIST kpad
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer kpad 10.0 10.0 10.0 FALSE	
				IF kpad_automatic_solve = 0							   												   												   
		// *************************************************************************************
		// 		LOAD / UNLOAD AS PLAYER GETS NEAR
		// *************************************************************************************
				
					IF IS_PLAYER_PLAYING player1
						IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer kpad 5.0 5.0 5.0 FALSE
							WAIT 100
							IF kpad_loaded = 0
								
								GET_OBJECT_COORDINATES kpad kpad_x kpad_y kpad_z
								GET_OBJECT_HEADING kpad kpad_h

								// numbers ---------------------------
								GET_OBJECT_COORDINATES kpad x y z
								GET_OBJECT_HEADING kpad heading
								CREATE_OBJECT_NO_OFFSET  KEYPAD_LETTERS x y z kpad_numbers
								SET_OBJECT_HEADING kpad_numbers heading
								SET_OBJECT_COLLISION kpad_numbers FALSE
								SET_OBJECT_DYNAMIC kpad_numbers FALSE

								// highlight button ------------------
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z // set initially in button A position
								CREATE_OBJECT_NO_OFFSET KEYPAD_BUTY x y z kpad_highlight	   
								SET_OBJECT_HEADING kpad_highlight kpad_h
								SET_OBJECT_COLLISION kpad_highlight FALSE
								SET_OBJECT_DYNAMIC kpad_highlight FALSE
								SET_OBJECT_VISIBLE kpad_highlight FALSE

								// pressed button --------------------
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z // set initially in button A position
								CREATE_OBJECT_NO_OFFSET KEYPAD_BUTG x y z kpad_press	   
								SET_OBJECT_HEADING kpad_press kpad_h
								SET_OBJECT_COLLISION kpad_press FALSE
								SET_OBJECT_DYNAMIC kpad_press FALSE
								SET_OBJECT_VISIBLE kpad_press FALSE

								// lights ----------------------------
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTG x y z kpad_lightA[0]	   
								SET_OBJECT_HEADING kpad_lightA[0] kpad_h
								SET_OBJECT_COLLISION kpad_lightA[0] FALSE
								SET_OBJECT_DYNAMIC kpad_lightA[0] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[0] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.036 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTG x y z kpad_lightA[1]	   
								SET_OBJECT_HEADING kpad_lightA[1] kpad_h
								SET_OBJECT_COLLISION kpad_lightA[1] FALSE
								SET_OBJECT_DYNAMIC kpad_lightA[1] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[1] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 -0.034 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTG x y z kpad_lightA[2]	   
								SET_OBJECT_HEADING kpad_lightA[2] kpad_h
								SET_OBJECT_COLLISION kpad_lightA[2] FALSE
								SET_OBJECT_DYNAMIC kpad_lightA[2] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[2] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.036 0.0 -0.034 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTG x y z kpad_lightA[3]	   
								SET_OBJECT_HEADING kpad_lightA[3] kpad_h
								SET_OBJECT_COLLISION kpad_lightA[3] FALSE
								SET_OBJECT_DYNAMIC kpad_lightA[3] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[3] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTY x y z kpad_lightB[0]	   
								SET_OBJECT_HEADING kpad_lightB[0] kpad_h
								SET_OBJECT_COLLISION kpad_lightB[0] FALSE
								SET_OBJECT_DYNAMIC kpad_lightB[0] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[0] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.036 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTY x y z kpad_lightB[1]	   
								SET_OBJECT_HEADING kpad_lightB[1] kpad_h
								SET_OBJECT_COLLISION kpad_lightB[1] FALSE
								SET_OBJECT_DYNAMIC kpad_lightB[1] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[1] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 -0.034 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTY x y z kpad_lightB[2]	   
								SET_OBJECT_HEADING kpad_lightB[2] kpad_h
								SET_OBJECT_COLLISION kpad_lightB[2] FALSE
								SET_OBJECT_DYNAMIC kpad_lightB[2] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[2] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.036 0.0 -0.034 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_LIGHTY x y z kpad_lightB[3]	   
								SET_OBJECT_HEADING kpad_lightB[3] kpad_h
								SET_OBJECT_COLLISION kpad_lightB[3] FALSE
								SET_OBJECT_DYNAMIC kpad_lightB[3] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[3] FALSE

								// display ----------------------------
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_HASH x y z kpad_display[0]	   
								SET_OBJECT_HEADING kpad_display[0] kpad_h
								SET_OBJECT_COLLISION kpad_display[0] FALSE
								SET_OBJECT_DYNAMIC kpad_display[0] FALSE
								SET_OBJECT_VISIBLE kpad_display[0] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.029 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_HASH x y z kpad_display[1]	   
								SET_OBJECT_HEADING kpad_display[1] kpad_h
								SET_OBJECT_COLLISION kpad_display[1] FALSE
								SET_OBJECT_DYNAMIC kpad_display[1] FALSE
								SET_OBJECT_VISIBLE kpad_display[1] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.058 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_HASH x y z kpad_display[2]	   
								SET_OBJECT_HEADING kpad_display[2] kpad_h
								SET_OBJECT_COLLISION kpad_display[2] FALSE
								SET_OBJECT_DYNAMIC kpad_display[2] FALSE
								SET_OBJECT_VISIBLE kpad_display[2] FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.087 0.0 0.0 x y z 			   
								CREATE_OBJECT_NO_OFFSET KEYPAD_HASH x y z kpad_display[3]	   				   
								SET_OBJECT_HEADING kpad_display[3] kpad_h									   
								SET_OBJECT_COLLISION kpad_display[3] FALSE									   
								SET_OBJECT_DYNAMIC kpad_display[3] FALSE									   
								SET_OBJECT_VISIBLE kpad_display[3] FALSE									   
																											   
								// granted & denied ---------------------
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_DENIED x y z kpad_denied_obj	   
								SET_OBJECT_HEADING kpad_denied_obj kpad_h
								SET_OBJECT_COLLISION kpad_denied_obj FALSE
								SET_OBJECT_DYNAMIC kpad_denied_obj FALSE
								SET_OBJECT_VISIBLE kpad_denied_obj FALSE

								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z 
								CREATE_OBJECT_NO_OFFSET KEYPAD_OK x y z kpad_granted_obj	   
								SET_OBJECT_HEADING kpad_granted_obj kpad_h
								SET_OBJECT_COLLISION kpad_granted_obj FALSE
								SET_OBJECT_DYNAMIC kpad_granted_obj FALSE
								SET_OBJECT_VISIBLE kpad_granted_obj FALSE

								kpad_result = FALSE
			 					kpad_automatic_solve = 0
								kpad_attempts = 0

								kpad_loaded = 1
							ENDIF
						
						ELSE
							IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer kpad 10.0 10.0 10.0 FALSE
								IF kpad_loaded = 1
									GOSUB kpad_delete_bits
									kpad_loaded = 0
									TERMINATE_THIS_SCRIPT
								ENDIF					
							ENDIF				
						ENDIF
					ENDIF
					
		// *************************************************************************************
		// 		TRIGGER
		// *************************************************************************************
					IF kpad_loaded = 1
						IF flag = 0
							IF IS_PLAYER_PLAYING player1
								
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.7 0.0 x y z	
								GET_OBJECT_HEADING kpad heading
								//GET_GROUND_Z_FOR_3D_COORD x y z z

								IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.7 0.7 2.0 FALSE
									GET_CHAR_HEADING scplayer temp_float
									temp_float2 = temp_float - heading
									IF temp_float2 < 0.0
										temp_float2 *= -1.0
									ENDIF
									IF temp_float2 > 135.0
									AND temp_float2 < 225.0
										IF kpad_help_text1 = 0
											PRINT_HELP KPAD1	
											kpad_help_text1 = 1
										ENDIF
										
										IF IS_BUTTON_PRESSED PAD1 TRIANGLE
													
											// remove help text
											IF kpad_help_text1 = 1
												CLEAR_HELP
												kpad_help_text1 = 0
											ENDIF

											// make all lights invisible
											temp_int = 0
											WHILE temp_int < 4
												SET_OBJECT_VISIBLE kpad_lightA[temp_int] FALSE
												SET_OBJECT_VISIBLE kpad_lightB[temp_int] FALSE
												SET_OBJECT_VISIBLE kpad_display[temp_int] FALSE
											temp_int++
											ENDWHILE
											SET_OBJECT_VISIBLE kpad_granted_obj	FALSE
											SET_OBJECT_VISIBLE kpad_denied_obj FALSE
											SET_OBJECT_VISIBLE kpad_highlight FALSE
											SET_OBJECT_VISIBLE kpad_press FALSE
										

											// reset any pad flags
											kpad_input[0] = -1
											kpad_input[1] = -1
											kpad_input[2] = -1
											kpad_input[3] = -1
											kpad_highlighted_pos = 0
											kpad_result = FALSE

											// fix player position
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.8 0.0 x y z
											GET_GROUND_Z_FOR_3D_COORD x y z z
											SET_CHAR_COORDINATES scplayer x y z
											GET_OBJECT_COORDINATES kpad x y z 
											TASK_TURN_CHAR_TO_FACE_COORD scplayer x y z
											SET_PLAYER_CONTROL player1 OFF
											
											// sort out camera
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.5 0.0 x y z 
											SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
											GET_OBJECT_COORDINATES kpad x y z
											POINT_CAMERA_AT_POINT x y z JUMP_CUT
											SET_NEAR_CLIP 0.3


											kpad_highlighted_pos = 0
											flag++
											WAIT 500
										ENDIF
									ELSE
										IF kpad_help_text1 = 1
											CLEAR_HELP
											kpad_help_text1 = 0
										ENDIF
									ENDIF
								ELSE
									IF kpad_help_text1 = 1
										CLEAR_HELP
										kpad_help_text1 = 0
									ENDIF
								ENDIF
							ENDIF		
						ENDIF

		// *************************************************************************************
		// 		INPUT
		// *************************************************************************************
						IF flag = 1

							IF kpad_button_is_pressed = 1
								IF TIMERA > 300
									kpad_button_is_pressed = 0
								ENDIF
							ENDIF

							GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
							
							// move the highlighted button about
							IF kpad_button_is_pressed = 0
								IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
								OR LStickX > 100
							   		IF kpad_highlighted_pos = 0
							   			kpad_highlighted_pos = 1
									ELSE
										IF kpad_highlighted_pos = 1
								   			kpad_highlighted_pos = 2
								   		ENDIF
							   		ENDIF
							   		IF kpad_highlighted_pos = 3
							   			kpad_highlighted_pos = 4
									ELSE
										IF kpad_highlighted_pos = 4
							   				kpad_highlighted_pos = 5
							   			ENDIF
							   		ENDIF
							   		kpad_button_is_pressed = 1
							   		TIMERA = 0	
								ENDIF
								IF IS_BUTTON_PRESSED PAD1 DPADLEFT
								OR LStickX < -100
							   		IF kpad_highlighted_pos = 1
							   			kpad_highlighted_pos = 0
									ELSE
										IF kpad_highlighted_pos = 2
							   				kpad_highlighted_pos = 1
							   			ENDIF
							   		ENDIF
							   		
							   		IF kpad_highlighted_pos = 4
							   			kpad_highlighted_pos = 3
									ELSE
										IF kpad_highlighted_pos = 5
								   			kpad_highlighted_pos = 4
								   		ENDIF
							   		ENDIF
							   		kpad_button_is_pressed = 1
							   		TIMERA = 0	
								ENDIF
								IF IS_BUTTON_PRESSED PAD1 DPADUP
								OR LStickY < -100
							   		IF kpad_highlighted_pos = 3
							   			kpad_highlighted_pos = 0
							   		ENDIF
							   		IF kpad_highlighted_pos = 4
							   			kpad_highlighted_pos = 1
							   		ENDIF
							   		IF kpad_highlighted_pos = 5
							   			kpad_highlighted_pos = 2
							   		ENDIF
							   		kpad_button_is_pressed = 1
							   		TIMERA = 0	
								ENDIF
								IF IS_BUTTON_PRESSED PAD1 DPADDOWN
								OR LStickY > 100
							   		IF kpad_highlighted_pos = 0
							   			kpad_highlighted_pos = 3
							   		ENDIF
							   		IF kpad_highlighted_pos = 1
							   			kpad_highlighted_pos = 4
							   		ENDIF
							   		IF kpad_highlighted_pos = 2
							   			kpad_highlighted_pos = 5
							   		ENDIF
							   		kpad_button_is_pressed = 1
							   		TIMERA = 0	
								ENDIF

							ENDIF

							// set position of highlighted and pressed button
							GOSUB kpad_get_button_position
							SET_OBJECT_COORDINATES kpad_highlight x y z 
							SET_OBJECT_COORDINATES kpad_press x y z
							SET_OBJECT_VISIBLE kpad_highlight TRUE

							// if player presses x - input value to kpad 	
							IF IS_BUTTON_PRESSED PAD1 CROSS
								SET_OBJECT_VISIBLE kpad_highlight FALSE
								IF kpad_input[0]	= -1
									kpad_input[0] = kpad_highlighted_pos
									SET_OBJECT_VISIBLE kpad_display[0] TRUE	
								ELSE
									IF kpad_input[1]	= -1
										kpad_input[1] = kpad_highlighted_pos
										SET_OBJECT_VISIBLE kpad_display[1] TRUE	
									ELSE
										IF kpad_input[2]	= -1
											kpad_input[2] = kpad_highlighted_pos
											SET_OBJECT_VISIBLE kpad_display[2] TRUE	
										ELSE
											IF kpad_input[3]	= -1
												kpad_input[3] = kpad_highlighted_pos
												SET_OBJECT_VISIBLE kpad_display[3] TRUE	
											ENDIF
										ENDIF					
									ENDIF				
								ENDIF
								SET_OBJECT_VISIBLE kpad_press TRUE	
								ADD_ONE_OFF_SOUND kpad_x kpad_y kpad_z SOUND_MINIGAME_CLICK
								WAIT 300
								SET_OBJECT_VISIBLE kpad_highlight TRUE
								SET_OBJECT_VISIBLE kpad_press FALSE
							ENDIF

							IF NOT kpad_input[3] = -1
								flag++
							ENDIF

						ENDIF

		// *************************************************************************************
		// 		PROCESS INPUT
		// *************************************************************************************
						IF flag = 2
							SET_OBJECT_VISIBLE kpad_highlight FALSE
							SET_OBJECT_VISIBLE kpad_press FALSE

							// wait a couple of ticks - to mimic processing time
							WAIT 300

							kpad_right = 0
							kpad_half_right = 0

							// check for full right answers
							kpad_partial[0] = 0
							kpad_partial[1] = 0
							kpad_partial[2] = 0
							kpad_partial[3] = 0

							IF kpad_input[0] = kpad_comb_A
								kpad_partial[0] = 1
								kpad_right++	
							ENDIF
							IF kpad_input[1] = kpad_comb_B
								kpad_partial[1] = 1
								kpad_right++	
							ENDIF
							IF kpad_input[2] = kpad_comb_C	
								kpad_partial[2] = 1
								kpad_right++	
							ENDIF
							IF kpad_input[3] = kpad_comb_D
								kpad_partial[3] = 1
								kpad_right++	
							ENDIF

							// check for partially right answers
							kpad_this_is_partial = 0
							IF NOT kpad_input[0]	= kpad_comb_A
								IF kpad_this_is_partial = 0 
									IF kpad_partial[1] = 0
										IF kpad_input[0] = kpad_comb_B
											kpad_partial[1] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[2] = 0
										IF kpad_input[0] = kpad_comb_C
											kpad_partial[2] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[3] = 0
										IF kpad_input[0] = kpad_comb_D
											kpad_partial[3] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 1
									kpad_half_right++
									kpad_this_is_partial = 0
								ENDIF
							ENDIF
							IF NOT kpad_input[1]	= kpad_comb_B
								IF kpad_this_is_partial = 0
									IF kpad_partial[0] = 0
										IF kpad_input[1] = kpad_comb_A
											kpad_partial[0] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[2] = 0
										IF kpad_input[1] = kpad_comb_C
											kpad_partial[2] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[3] = 0
										IF kpad_input[1] = kpad_comb_D
											kpad_partial[3] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 1
									kpad_this_is_partial = 0
									kpad_half_right++
								ENDIF
							ENDIF
							IF NOT kpad_input[2]	= kpad_comb_C
								IF kpad_this_is_partial = 0
									IF kpad_partial[0] = 0
										IF kpad_input[2] = kpad_comb_A
											kpad_partial[0] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[1] = 0
										IF kpad_input[2] = kpad_comb_B
											kpad_partial[1] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[3] = 0
										IF kpad_input[2] = kpad_comb_D
											kpad_partial[3] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 1
									kpad_this_is_partial = 0
									kpad_half_right++
								ENDIF
							ENDIF
							IF NOT kpad_input[3]	= kpad_comb_D
								IF kpad_this_is_partial = 0
									IF kpad_partial[0] = 0
										IF kpad_input[3] = kpad_comb_A
											kpad_partial[0] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[1] = 0
										IF kpad_input[3] = kpad_comb_B
											kpad_partial[1] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 0
									IF kpad_partial[2] = 0
										IF kpad_input[3] = kpad_comb_C
											kpad_partial[2] = 1
											kpad_this_is_partial = 1
										ENDIF
									ENDIF
								ENDIF
								IF kpad_this_is_partial = 1
									kpad_this_is_partial = 0
									kpad_half_right++
								ENDIF
							ENDIF
							flag++
						ENDIF

		// *************************************************************************************
		// 		DISPLAY RESULTS
		// *************************************************************************************
						IF flag = 3
							IF kpad_right = 1
								SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
							ENDIF
							IF kpad_right = 2
								SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[1] TRUE
							ENDIF 
							IF kpad_right = 3
								SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[1] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[2] TRUE
							ENDIF
							IF kpad_right = 4
								SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[1] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[2] TRUE
								SET_OBJECT_VISIBLE kpad_lightA[3] TRUE
							ENDIF
							IF kpad_half_right = 1
								IF kpad_right = 0
									SET_OBJECT_VISIBLE kpad_lightB[0] TRUE
								ENDIF
								IF kpad_right = 1
									SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
								ENDIF
								IF kpad_right = 2
									SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
								ENDIF 
								IF kpad_right = 3
									SET_OBJECT_VISIBLE kpad_lightB[3] TRUE
								ENDIF
							ENDIF
							IF kpad_half_right = 2
								IF kpad_right = 0
									SET_OBJECT_VISIBLE kpad_lightB[0] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
								ENDIF
								IF kpad_right = 1
									SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
								ENDIF
								IF kpad_right = 2
									SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[3] TRUE
								ENDIF 
							ENDIF
							IF kpad_half_right = 3
								IF kpad_right = 0
									SET_OBJECT_VISIBLE kpad_lightB[0] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
								ENDIF
								IF kpad_right = 1
									SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
									SET_OBJECT_VISIBLE kpad_lightB[3] TRUE
								ENDIF
							ENDIF
							IF kpad_half_right = 4
								SET_OBJECT_VISIBLE kpad_lightB[0] TRUE
								SET_OBJECT_VISIBLE kpad_lightB[1] TRUE
								SET_OBJECT_VISIBLE kpad_lightB[2] TRUE
								SET_OBJECT_VISIBLE kpad_lightB[3] TRUE
							ENDIF 
							flag++
						ENDIF

		// *************************************************************************************
		// 		DETERMINE RESULT
		// *************************************************************************************
						IF flag = 4
							IF kpad_right = 4 
								
								// remove screen asterix
								SET_OBJECT_VISIBLE kpad_display[0] FALSE
								SET_OBJECT_VISIBLE kpad_display[1] FALSE
								SET_OBJECT_VISIBLE kpad_display[2] FALSE
								SET_OBJECT_VISIBLE kpad_display[3] FALSE

								GOSUB kpad_access_granted
								
								kpad_attempts++

			//					// go back to normal mode, but wait until player has left area before resetting variables.
			//					IF IS_PLAYER_PLAYING player1
			//						SET_PLAYER_CONTROL player1 ON
			//						RESTORE_CAMERA_JUMPCUT
			//						temp_int = 0
			//						WHILE temp_int = 0
			//							IF IS_PLAYER_PLAYING player1
			//								IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer kpad_x kpad_y kpad_z 5.0 5.0 5.0 FALSE
			//									temp_int = 1
			//								ENDIF
			//							ENDIF 
			//						WAIT 0
			//						ENDWHILE
			//						GOSUB kpad_delete_bits
			//					ENDIF

								quit = 1
								flag++
							ELSE
								// remove screen asterix
								SET_OBJECT_VISIBLE kpad_display[0] FALSE
								SET_OBJECT_VISIBLE kpad_display[1] FALSE
								SET_OBJECT_VISIBLE kpad_display[2] FALSE
								SET_OBJECT_VISIBLE kpad_display[3] FALSE
								
								GET_OBJECT_COORDINATES kpad x y z
								ADD_ONE_OFF_SOUND x y z SOUND_MINIGAME_BAD

								// flash 'Denied' sign					
								TIMERA = 0
								TIMERB = 0
								temp_int = 0
								WHILE TIMERB < 3000
									WAIT 0
									IF TIMERA > 1000
										IF temp_int = 0
											SET_OBJECT_VISIBLE kpad_denied_obj TRUE
											temp_int = 1
										ELSE
											SET_OBJECT_VISIBLE kpad_denied_obj FALSE
											temp_int = 0
										ENDIF
									ENDIF
								ENDWHILE

								kpad_attempts++
									
								// reset input variables
								kpad_input[0] = -1
								kpad_input[1] = -1
								kpad_input[2] = -1
								kpad_input[3] = -1
								kpad_highlighted_pos = 0
								SET_OBJECT_VISIBLE kpad_highlight TRUE
								SET_OBJECT_VISIBLE kpad_press FALSE
								
								// reset position of highlighted and pressed button
								IF kpad_highlighted_pos = 0
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								IF kpad_highlighted_pos = 1
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								IF kpad_highlighted_pos = 2
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								IF kpad_highlighted_pos = 3
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								IF kpad_highlighted_pos = 4
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								IF kpad_highlighted_pos = 5
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
								ENDIF
								SET_OBJECT_COORDINATES kpad_highlight x y z 
								SET_OBJECT_COORDINATES kpad_press x y z
								
								// reset display lights
								SET_OBJECT_VISIBLE kpad_lightA[0] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[1] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[2] FALSE
								SET_OBJECT_VISIBLE kpad_lightA[3] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[0] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[1] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[2] FALSE
								SET_OBJECT_VISIBLE kpad_lightB[3] FALSE

								// remove denied sign
								SET_OBJECT_VISIBLE kpad_denied_obj FALSE

								flag = 1
								
							ENDIF
						
						ENDIF

		// *************************************************************************************
		// 		GLOBAL FUNCTIONS - operate for as long as script is alive
		// *************************************************************************************
				
						// if the player presses triangle - quit out
						IF flag > 0
							IF NOT IS_BUTTON_PRESSED PAD1 CROSS
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									IF IS_PLAYER_PLAYING player1
										SET_PLAYER_CONTROL player1 ON
										RESTORE_CAMERA_JUMPCUT

										// make all lights invisible
										temp_int = 0
										WHILE temp_int < 4
											SET_OBJECT_VISIBLE kpad_lightA[temp_int] FALSE
											SET_OBJECT_VISIBLE kpad_lightB[temp_int] FALSE
											SET_OBJECT_VISIBLE kpad_display[temp_int] FALSE
										temp_int++
										ENDWHILE
										SET_OBJECT_VISIBLE kpad_granted_obj	FALSE
										SET_OBJECT_VISIBLE kpad_denied_obj FALSE
										SET_OBJECT_VISIBLE kpad_highlight FALSE
										SET_OBJECT_VISIBLE kpad_press FALSE

										flag = 0
										WAIT 500
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						IF quit = 1
							IF IS_PLAYER_PLAYING player1
								SET_PLAYER_CONTROL player1 ON
								RESTORE_CAMERA_JUMPCUT
							ENDIF
							quit = 0
						ENDIF
						
					ENDIF
		// *************************************************************************************
		// 		AUTOMATICALLY SOLVE
		// *************************************************************************************
				ELSE

					IF DOES_OBJECT_EXIST kpad 
						GET_OBJECT_COORDINATES kpad kpad_x kpad_y kpad_z
						GET_OBJECT_HEADING kpad kpad_h
					ENDIF

					// flash the correct input
					kpad_highlighted_pos = kpad_comb_A
					GOSUB kpad_get_button_position
					SET_OBJECT_COORDINATES kpad_press x y z
					SET_OBJECT_VISIBLE kpad_press TRUE
					SET_OBJECT_VISIBLE kpad_display[0] TRUE
					ADD_ONE_OFF_SOUND kpad_x kpad_y kpad_z SOUND_MINIGAME_CLICK
					WAIT 700
					SET_OBJECT_VISIBLE kpad_press FALSE
					WAIT 200

					kpad_highlighted_pos = kpad_comb_B
					GOSUB kpad_get_button_position
					SET_OBJECT_COORDINATES kpad_press x y z
					SET_OBJECT_VISIBLE kpad_press TRUE
					SET_OBJECT_VISIBLE kpad_display[1] TRUE
					ADD_ONE_OFF_SOUND kpad_x kpad_y kpad_z SOUND_MINIGAME_CLICK
					WAIT 700
					SET_OBJECT_VISIBLE kpad_press FALSE
					WAIT 200

					kpad_highlighted_pos = kpad_comb_C
					GOSUB kpad_get_button_position
					SET_OBJECT_COORDINATES kpad_press x y z
					SET_OBJECT_VISIBLE kpad_press TRUE
					SET_OBJECT_VISIBLE kpad_display[2] TRUE
					ADD_ONE_OFF_SOUND kpad_x kpad_y kpad_z SOUND_MINIGAME_CLICK
					WAIT 700
					SET_OBJECT_VISIBLE kpad_press FALSE
					WAIT 200
					
					kpad_highlighted_pos = kpad_comb_D
					GOSUB kpad_get_button_position
					SET_OBJECT_COORDINATES kpad_press x y z
					SET_OBJECT_VISIBLE kpad_press TRUE
					SET_OBJECT_VISIBLE kpad_display[3] TRUE
					ADD_ONE_OFF_SOUND kpad_x kpad_y kpad_z SOUND_MINIGAME_CLICK
					WAIT 700
					SET_OBJECT_VISIBLE kpad_press FALSE
					WAIT 200

					// wait a couple of ticks - to mimic processing time
					WAIT 500
					
					// remove screen asterix
					SET_OBJECT_VISIBLE kpad_display[0] FALSE
					SET_OBJECT_VISIBLE kpad_display[1] FALSE
					SET_OBJECT_VISIBLE kpad_display[2] FALSE
					SET_OBJECT_VISIBLE kpad_display[3] FALSE

					GOSUB kpad_access_granted
					 
					// wait a few secs before resetting
					WAIT 3000

				ENDIF
			ELSE
				// cleanup
				GOSUB kpad_delete_bits
				TERMINATE_THIS_SCRIPT
			ENDIF
		ENDIF	
	ELSE
		// cleanup
		GOSUB kpad_delete_bits
		TERMINATE_THIS_SCRIPT
	ENDIF

GOTO kpad_loop


kpad_get_button_position:
	// set position of pressed button
	IF kpad_highlighted_pos = 0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 0.0 x y z
	ENDIF
	IF kpad_highlighted_pos = 1
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.066 0.0 0.0 x y z
	ENDIF
	IF kpad_highlighted_pos = 2
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.132 0.0 0.0 x y z
	ENDIF
	IF kpad_highlighted_pos = 3
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad 0.0 0.0 -0.069 x y z
	ENDIF
	IF kpad_highlighted_pos = 4
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.066 0.0 -0.069 x y z
	ENDIF
	IF kpad_highlighted_pos = 5
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS kpad -0.132 0.0 -0.069 x y z
	ENDIF
RETURN

kpad_access_granted:

	// display 'granted' sign
	SET_OBJECT_VISIBLE kpad_granted_obj TRUE
	
	// make 4 lights flash a few times
	TIMERA = 0
	TIMERB = 0
	temp_int = 0
	IF kpad_automatic_solve = 1
		kpad_result = TRUE // don't have the delay if it's being automatically solved
	ENDIF
	WHILE TIMERB < 3000
	WAIT 0
		IF TIMERA > 1000
			IF temp_int = 0
				SET_OBJECT_VISIBLE kpad_lightA[0] FALSE
				SET_OBJECT_VISIBLE kpad_lightA[1] FALSE
				SET_OBJECT_VISIBLE kpad_lightA[2] FALSE
				SET_OBJECT_VISIBLE kpad_lightA[3] FALSE
				temp_int = 1
			ELSE
				SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
				SET_OBJECT_VISIBLE kpad_lightA[1] TRUE
				SET_OBJECT_VISIBLE kpad_lightA[2] TRUE
				SET_OBJECT_VISIBLE kpad_lightA[3] TRUE
				temp_int = 0
			ENDIF
			TIMERA = 0
		ENDIF
	ENDWHILE
	SET_OBJECT_VISIBLE kpad_lightA[0] TRUE
	SET_OBJECT_VISIBLE kpad_lightA[1] TRUE
	SET_OBJECT_VISIBLE kpad_lightA[2] TRUE
	SET_OBJECT_VISIBLE kpad_lightA[3] TRUE
	GET_OBJECT_COORDINATES kpad x y z
	ADD_ONE_OFF_SOUND x y z SOUND_MINIGAME_GOOD 
	kpad_result = TRUE

RETURN

kpad_delete_bits:
	// delete all the kpad objects, they are no longer needed.
	IF DOES_OBJECT_EXIST kpad_numbers
		DELETE_OBJECT kpad_numbers
	ENDIF
	temp_int = 0
	WHILE temp_int < 4
		IF DOES_OBJECT_EXIST kpad_lightA[temp_int] 
			DELETE_OBJECT kpad_lightA[temp_int] 
		ENDIF
		IF DOES_OBJECT_EXIST kpad_lightB[temp_int]
			DELETE_OBJECT kpad_lightB[temp_int]
		ENDIF
		IF DOES_OBJECT_EXIST kpad_display[temp_int]
			DELETE_OBJECT kpad_display[temp_int]
		ENDIF
	temp_int++
	ENDWHILE
	IF DOES_OBJECT_EXIST kpad_denied_obj
		DELETE_OBJECT kpad_denied_obj
	ENDIF
	IF DOES_OBJECT_EXIST kpad_granted_obj
		DELETE_OBJECT kpad_granted_obj
	ENDIF
	IF DOES_OBJECT_EXIST kpad_highlight
		DELETE_OBJECT kpad_highlight			
	ENDIF
	IF DOES_OBJECT_EXIST kpad_press
		DELETE_OBJECT kpad_press
	ENDIF
RETURN

}

MISSION_END

