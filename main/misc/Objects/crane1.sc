MISSION_START

// global variables 

VAR_FLOAT cammy_off_X cammy_off_Y cammy_off_Z

VAR_INT crane_help1
VAR_INT crane_help2
VAR_INT crane_help3

VAR_INT disable_all_cranes

VAR_INT do_not_update_camera_crane1

{///////////////////////////////////////////////////////////////////////////////
crane1_script:////////////// MEDIUM CONSTRUCTION SITE CRANE ////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME CRANE1

LVAR_INT base  // passed in by the object script

LVAR_INT arm 
LVAR_INT trolley
LVAR_INT arm_lod
LVAR_INT base_lod
LVAR_INT flag
LVAR_FLOAT temp_float

LVAR_FLOAT arm_h trolley_off_y
LVAR_FLOAT trigger_x trigger_y trigger_z
LVAR_FLOAT x2 y2 z2

LVAR_FLOAT c1_cam_pos_x c1_cam_pos_y c1_cam_pos_z
LVAR_FLOAT c1_cam_look_x c1_cam_look_y c1_cam_look_z

LVAR_INT camera_mode

LVAR_INT crane1_sound_flag
LVAR_INT crane1_sound_flag2

flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 base
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 arm
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 trolley
ENDIF

// check if in san fran
IF DOES_OBJECT_EXIST base
	IF LOCATE_OBJECT_2D base -2080.441 256.015 10.0 10.0 FALSE
		arm		=	 sf_crane1_arm
		trolley	=	 sf_crane1_trolley
		arm_lod = 	 sf_crane1_arm_lod
		base_lod =   sf_crane1_base_lod
	ENDIF	
	IF LOCATE_OBJECT_2D base 2399.202 1879.139 10.0 10.0 FALSE
		arm		=	 lv_arm
		trolley	=	 lv_trolley
		arm_lod = 	 lv_arm_lod
		base_lod =   lv_base_lod
	ENDIF			 
ENDIF

crane_help1 = 0
camera_mode = 0

crane1_script_loop:

    WAIT 0

	IF DOES_OBJECT_EXIST base
		IF IS_PLAYER_PLAYING player1

			IF disable_all_cranes = 0

				IF reset_crane_camera = 1
					GOSUB update_crane1_camera
				ENDIF

				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D  scplayer base 100.0 100.0  FALSE
				
					IF player_is_in_crane = 1
						GOSUB update_crane1_camera
					ENDIF

					// WAIT TO SEE IF PLAYER GETS INTO CRANE ++++++++
					IF flag = 0
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base 0.0 -7.2092 -31.7581 trigger_x trigger_y trigger_z 
						flag++
					ENDIF

					IF flag = 1
						IF LOCATE_CHAR_ON_FOOT_3D scplayer trigger_x trigger_y trigger_z 1.2 1.2 1.5 TRUE
							IF IS_CHAR_ON_FOOT scplayer 
								// display help
								IF crane_help1 = 0
									PRINT_HELP_FOREVER CR_2  
									crane_help1 = 1
								ENDIF
								
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									flag++
									TIMERA = 6000 // used for help
									crane_help1 = 0
								ENDIF	
							ENDIF
						ELSE
							IF NOT crane_help1 = 0
								CLEAR_HELP 
								crane_help1 = 0
							ENDIF	
						ENDIF
					ENDIF

					// TRANSFER PLAYER INTO CRANE ++++++++++++++++++++
					IF flag = 2

						IF DOES_OBJECT_EXIST arm
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_ENTER
						ENDIF
											
						SET_PLAYER_CONTROL player1 OFF	 
						SET_MINIGAME_IN_PROGRESS TRUE
						DO_FADE 500 FADE_OUT 
						WHILE GET_FADING_STATUS 
							WAIT 0
						ENDWHILE
						
						

						IF arm = sf_crane1_arm
							PLAYER_ENTERED_BUILDINGSITE_CRANE
						ELSE
							PLAYER_ENTERED_LAS_VEGAS_CRANE
						ENDIF

						DISPLAY_NON_MINIGAME_HELP_MESSAGES TRUE

						// get current component details
						GET_OBJECT_HEADING arm arm_h
						GET_OBJECT_COORDINATES arm x y z
						GET_OBJECT_COORDINATES trolley x2 y2 z2
						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 trolley_off_y
						IF trolley_off_y > 47.5
							trolley_off_y = 47.5
						ENDIF
						IF trolley_off_y < 10.0
							trolley_off_y = 10.0
						ENDIF

						IF DOES_OBJECT_EXIST arm
							IF IS_PLAYER_PLAYING player1
								ATTACH_CHAR_TO_OBJECT scplayer base 0.0 0.0 34.5 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
							ENDIF
						ENDIF

						// set player position
						IF IS_PLAYER_PLAYING player1
							FREEZE_CHAR_POSITION scplayer TRUE 
							SET_CHAR_VISIBLE scplayer FALSE
							SET_PLAYER_CONTROL player1 ON
							SET_CHAR_COLLISION scplayer OFF
							SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE 
							player_is_in_crane = 1
							crane1_sound_flag = 0
						ENDIF

						SET_EVERYONE_IGNORE_PLAYER player1 OFF

						GOSUB update_crane1_camera

						DO_FADE 500 FADE_IN 
						WHILE GET_FADING_STATUS 
							WAIT 0
						ENDWHILE

						flag++
					ENDIF

					// CONTROL CRANE ++++++++++++++++++++++++++++++
					IF flag = 3

						// help 
						IF TIMERA > 6000 
							crane_help1++
							
							IF crane_help1 < 7
								SWITCH crane_help1
									CASE 1
										CLEAR_HELP
										IF arm = sf_crane1_arm 
											PRINT_HELP_FOREVER CONS_1  // Push the movement controls left or right to rotate the wrecker ball crane.
										ELSE
											PRINT_HELP_FOREVER CONS_1B  
										ENDIF
									BREAK
									CASE 2
										CLEAR_HELP
										IF arm = sf_crane1_arm
											PRINT_HELP_FOREVER CONS_2  // Push the movement controls up or down to move the wrecker ball closer or further away.
										ELSE
											PRINT_HELP_FOREVER CONS_2B  
										ENDIF
									BREAK
									CASE 3
										CLEAR_HELP
										IF arm = sf_crane1_arm
											PRINT_HELP_FOREVER CONS_3 
										ELSE
											PRINT_HELP_FOREVER CONS_3B // Press the ~q~ button to raise the wrecker ball.
										ENDIF
									BREAK
									CASE 4
										CLEAR_HELP
										IF arm = sf_crane1_arm
											PRINT_HELP_FOREVER CONS_4 
										ELSE
											PRINT_HELP_FOREVER CONS_4B // Press the ~x~ button to lower the wrecker ball.
										ENDIF
									BREAK
									CASE 5
										CLEAR_HELP
										PRINT_HELP_FOREVER CONS_5  
									BREAK
									CASE 6
										CLEAR_HELP
										PRINT_HELP CR_3  
									BREAK
								ENDSWITCH
							ENDIF
							
							TIMERA = 0
							
						ENDIF
		

						IF IS_PLAYER_PLAYING player1
	
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
								cammy_off_X += 0.1
							ENDIF
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_A
								cammy_off_X += -0.1
							ENDIF
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_W
								cammy_off_y += 0.1
							ENDIF
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
								cammy_off_y += -0.1
							ENDIF
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_E
								cammy_off_z += 0.1
							ENDIF
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
								cammy_off_z += -0.1
							ENDIF


							// rotate crane -------
							IF IS_BUTTON_PRESSED PAD1 LEFTSTICKX
								GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
								temp_float =# LStickX
								temp_float /= 500.0
								arm_h -=@ temp_float
								IF IS_PLAYER_PLAYING player1
									IF NOT crane1_sound_flag = 1
										//GET_CHAR_COORDINATES scplayer x y z
										//REPORT_MISSION_AUDIO_EVENT_AT_POSITION X Y Z SOUND_CRANE_MOVE_START
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_MOVE_START
										crane1_sound_flag = 1
									ENDIF	
								ENDIF
							ELSE
								IF IS_PLAYER_PLAYING player1
									IF NOT crane1_sound_flag = 0
										//GET_CHAR_COORDINATES scplayer x y z
										//REPORT_MISSION_AUDIO_EVENT_AT_POSITION X Y Z SOUND_CRANE_MOVE_STOP
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_MOVE_STOP
										crane1_sound_flag = 0
									ENDIF	
								ENDIF	
							ENDIF

							// move trolley -------
						   	IF IS_BUTTON_PRESSED PAD1 LEFTSTICKY
								GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
								temp_float =# LStickY
								temp_float /= 128.0
								IF temp_float > 0.6	// threshold
								OR temp_float < -0.6

									temp_float *= 0.15
									trolley_off_y -=@ temp_float
									IF trolley_off_y > 47.5
										trolley_off_y = 47.5
									ENDIF
									IF trolley_off_y < 10.0
										trolley_off_y = 10.0
									ENDIF

									IF NOT crane1_sound_flag2 = 1
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT trolley SOUND_CRANE_MOVE_START
										crane1_sound_flag2 = 1
									ENDIF
								ENDIF
							ELSE
								IF NOT crane1_sound_flag2 = 0
									REPORT_MISSION_AUDIO_EVENT_AT_OBJECT trolley SOUND_CRANE_MOVE_STOP
									crane1_sound_flag2 = 0
								ENDIF
							ENDIF

							// drop stuff ---------
							
							IF DOES_OBJECT_EXIST trolley
								IF IS_BUTTON_PRESSED PAD1 CROSS
									RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT trolley
								ENDIF
							ENDIF

							// UPDATE PARTS
							// arm
							IF DOES_OBJECT_EXIST arm
								IF DOES_OBJECT_EXIST base																						 
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base 0.0 0.0 32.5210 x y z		 
									SET_OBJECT_HEADING arm arm_h	
									IF DOES_OBJECT_EXIST arm_lod
										SET_OBJECT_HEADING arm_lod arm_h
									ENDIF														 					
									SET_CHAR_HEADING scplayer arm_h
								ENDIF
							ENDIF
							// trolley
							IF DOES_OBJECT_EXIST arm
								IF DOES_OBJECT_EXIST trolley
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS arm 0.0 trolley_off_y 3.453 x y z	// was -1.8
									SET_OBJECT_COORDINATES_AND_VELOCITY trolley x y z
									SET_OBJECT_HEADING trolley arm_h
								ENDIF
							ENDIF

							// exit crane ---------
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								flag++
							ENDIF
							 
						ENDIF
					ENDIF

					// TRANSFER PLAYER OUT OF CRANE +++++++++++++++++
					IF flag = 4
						IF IS_PLAYER_PLAYING player1
							GET_CHAR_COORDINATES scplayer x y z
							IF DOES_OBJECT_EXIST arm
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_EXIT
							ENDIF
							CLEAR_HELP
							PLAYER_LEFT_CRANE
							SET_MINIGAME_IN_PROGRESS FALSE
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base 2.0 -7.0 0.0 x y z
							GET_GROUND_Z_FOR_3D_COORD x y z z					
							SET_CHAR_COORDINATES scplayer x y z
							DETACH_CHAR_FROM_CAR scplayer
							FREEZE_CHAR_POSITION scplayer FALSE
							SET_CHAR_VISIBLE scplayer TRUE
							SET_CHAR_COLLISION scplayer TRUE
							SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
							SET_PLAYER_CONTROL player1 ON
							RESTORE_CAMERA_JUMPCUT
							player_is_in_crane = 0
							flag = 0
							TERMINATE_THIS_SCRIPT
						ENDIF
					ENDIF
				ELSE
					TERMINATE_THIS_SCRIPT
				ENDIF
			ENDIF
		ENDIF
	ELSE
		flag = 0
		TERMINATE_THIS_SCRIPT
	ENDIF

GOTO crane1_script_loop


update_crane1_camera:

	VAR_INT crane1_cam_mode
	LVAR_INT select_is_pressed

	IF do_not_update_camera_crane1 = 0

		IF IS_BUTTON_PRESSED PAD1 SELECT
			IF select_is_pressed = 0
				camera_mode++
				IF camera_mode > 2
					camera_mode = 0
				ENDIF
				crane1_cam_mode = camera_mode
				select_is_pressed++
			ENDIF
		ELSE
			IF NOT select_is_pressed = 0
				select_is_pressed = 0
			ENDIF
		ENDIF

		IF player_is_in_crane = 1
			IF reset_crane_camera = 1
				crane1_cam_mode = crane1_cam_mode	
				reset_crane_camera = 0
			ENDIF
		ENDIF	


	//	LVAR_FLOAT c_x c_y c_z
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
	//		c_z += 0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
	//		c_z += -0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
	//		c_x += 0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
	//		c_x += -0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
	//		c_y += 0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
	//		c_y += -0.1
	//	ENDIF
	//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
	//		SAVE_NEWLINE_TO_DEBUG_FILE
	//		SAVE_STRING_TO_DEBUG_FILE "crane1 cam offsets = "
	//		SAVE_FLOAT_TO_DEBUG_FILE c_x
	//		SAVE_FLOAT_TO_DEBUG_FILE c_y
	//		SAVE_FLOAT_TO_DEBUG_FILE c_z
	//	ENDIF


		// get camera position
		IF camera_mode = 0
			IF DOES_OBJECT_EXIST arm 
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS arm -7.18 -6.42 15.79 c1_cam_pos_x c1_cam_pos_y c1_cam_pos_z
			ENDIF
		ENDIF
		IF camera_mode = 1
			IF DOES_OBJECT_EXIST arm 
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS trolley  0.0000 -4.5000 20.1999 c1_cam_pos_x c1_cam_pos_y c1_cam_pos_z
			
				// adjust z
				// get camera look at
				IF DOES_OBJECT_EXIST trolley 
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS trolley 0.0 0.0 20.1999 x y z
					GET_ROPE_HEIGHT_FOR_OBJECT trolley temp_float
					temp_float += -1.0
					temp_float *= -1.0
					temp_float *= 72.0
					z -= temp_float
					c1_cam_pos_z =	z
				ENDIF

			ENDIF
		ENDIF

		// get camera look at
		IF DOES_OBJECT_EXIST trolley 
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS trolley 0.0 0.0 2.0 x y z
			GET_ROPE_HEIGHT_FOR_OBJECT trolley temp_float
			temp_float += -1.0
			temp_float *= -1.0
			temp_float *= 72.0
			z -= temp_float
			c1_cam_look_x =	x
			c1_cam_look_y =	y
			c1_cam_look_z =	z
		ENDIF

		SET_FIXED_CAMERA_POSITION  c1_cam_pos_x c1_cam_pos_y c1_cam_pos_z 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT c1_cam_look_x c1_cam_look_y c1_cam_look_z JUMP_CUT

	ENDIF

RETURN


}



MISSION_END