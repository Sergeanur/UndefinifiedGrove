MISSION_START

{///////////////////////////////////////////////////////////////////////////////
crane3_script:////////////// QUARRY SITE CRANE /////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME CRANE3

// parameters
LVAR_INT base 
LVAR_INT base_stand 
LVAR_INT arm 
	
LVAR_INT flag

LVAR_FLOAT base_stand_h 
LVAR_FLOAT trigger_x trigger_y trigger_z
LVAR_FLOAT temp_float

LVAR_FLOAT c3_campos_x c3_campos_y c3_campos_z
LVAR_FLOAT c3_camlook_x c3_camlook_y c3_camlook_z

LVAR_INT crane3_sound_flag
LVAR_INT crane3_sound_flag2


flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 base
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 base_stand
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 arm 	
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 flag
ENDIF

crane_help3 = 0

crane3_script_loop:
    
	WAIT 0

	IF DOES_OBJECT_EXIST base
		IF IS_PLAYER_PLAYING player1
			IF disable_all_cranes = 0

				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer base 100.0 100.0 FALSE

					// WAIT TO SEE IF PLAYER GETS INTO CRANE ++++++++
					IF flag = 0
					  	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base 3.0899 2.9792 0.0081 trigger_x trigger_y trigger_z
					flag++
					ENDIF

					IF flag = 1
					 
						IF LOCATE_CHAR_ON_FOOT_3D scplayer trigger_x trigger_y trigger_z 1.2 1.2 1.5 TRUE
							IF IS_CHAR_ON_FOOT scplayer 
								// display help
								IF crane_help3 = 0
									PRINT_HELP_FOREVER CR_2 
									crane_help3 = 1
								ENDIF

								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									flag++
								ENDIF	
							ENDIF
						ELSE
							IF NOT crane_help3 = 0
								CLEAR_HELP
								crane_help3 = 0
							ENDIF
						ENDIF
					ENDIF

					// TRANSFER PLAYER INTO CRANE ++++++++++++++++++++
					IF flag = 2
						
						IF DOES_OBJECT_EXIST base 
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT base SOUND_CRANE_ENTER
						ENDIF

						SET_PLAYER_CONTROL player1 OFF	
						SET_MINIGAME_IN_PROGRESS TRUE

						DO_FADE 500 FADE_OUT 
						WHILE GET_FADING_STATUS 
							WAIT 0
						ENDWHILE
						PLAYER_ENTERED_QUARRY_CRANE	 
						
						DISPLAY_NON_MINIGAME_HELP_MESSAGES TRUE

						// get current component details
						GET_OBJECT_HEADING base_stand base_stand_h
						
						IF DOES_OBJECT_EXIST base
							IF IS_PLAYER_PLAYING player1
							ENDIF
						ENDIF

						// set player position
						IF IS_PLAYER_PLAYING player1
							FREEZE_CHAR_POSITION scplayer TRUE
							SET_CHAR_COORDINATES scplayer 713.5652 906.2935 -18.0674 
							SET_CHAR_VISIBLE scplayer FALSE
							SET_PLAYER_CONTROL player1 ON
							SET_CHAR_COLLISION scplayer OFF
							SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE 
							crane3_sound_flag = 0
						ENDIF
							
						// display help
						PRINT_HELP_FOREVER QUAR_1

						GOSUB update_crane_camera1

						SET_EVERYONE_IGNORE_PLAYER player1 OFF

						DO_FADE 500 FADE_IN 
						WHILE GET_FADING_STATUS 
							WAIT 0
						ENDWHILE
						flag++
					ENDIF

					// CONTROL CRANE ++++++++++++++++++++++++++++++
					IF flag = 3
						IF IS_PLAYER_PLAYING player1
							
							// update camera ------
							GOSUB update_crane_camera1

							// rotate base_stand -------
							IF IS_BUTTON_PRESSED PAD1 LEFTSTICKX
								GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
								temp_float =# LStickX
								temp_float /= 500.0
								base_stand_h -=@ temp_float

								IF NOT crane3_sound_flag = 1
									IF DOES_OBJECT_EXIST base
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT base SOUND_CRANE_MOVE_START
										crane3_sound_flag = 1
									ENDIF
								ENDIF
							ELSE
								IF NOT crane3_sound_flag = 0
									IF DOES_OBJECT_EXIST base
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT base SOUND_CRANE_MOVE_STOP
										crane3_sound_flag = 0
									ENDIF
								ENDIF
							ENDIF
							// raise / lower arm -------
						   	IF IS_BUTTON_PRESSED PAD1 LEFTSTICKY
								GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
								temp_float =# LStickY
								temp_float /= 1000.0
								temp_float *= 1.5
								arm_rotate_y -=@ temp_float
								WHILE arm_rotate_y < 0.0
									arm_rotate_y += 360.0
								ENDWHILE
								WHILE arm_rotate_y > 360.0
									arm_rotate_y += -360.0
								ENDWHILE
								IF arm_rotate_y > 70.0
									arm_rotate_y = 70.0
								ENDIF
								IF arm_rotate_y < 10.0
									arm_rotate_y = 10.0
								ENDIF

								IF NOT crane3_sound_flag2 = 1
									IF DOES_OBJECT_EXIST base
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_MOVE_START
										crane3_sound_flag2 = 1
									ENDIF
								ENDIF
							ELSE
								IF NOT crane3_sound_flag2 = 0
									IF DOES_OBJECT_EXIST base
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT arm SOUND_CRANE_MOVE_STOP
										crane3_sound_flag2 = 0
									ENDIF
								ENDIF
							ENDIF

							IF DOES_OBJECT_EXIST base_stand																						 		 
								SET_OBJECT_HEADING base_stand base_stand_h															 					
								SET_CHAR_HEADING scplayer base_stand_h
							ENDIF
							// arm
							IF DOES_OBJECT_EXIST arm
								IF DOES_OBJECT_EXIST base_stand
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base_stand 0.0 0.0 1.0206 x y z
									SET_OBJECT_COORDINATES_AND_VELOCITY arm x y z
									SET_OBJECT_ROTATION arm 0.0 arm_rotate_y base_stand_h
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
							CLEAR_HELP
							IF DOES_OBJECT_EXIST base 
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT base SOUND_CRANE_EXIT
							ENDIF
							PLAYER_LEFT_CRANE
							SET_MINIGAME_IN_PROGRESS FALSE
							FREEZE_CHAR_POSITION scplayer FALSE
							SET_CHAR_VISIBLE scplayer TRUE
							SET_CHAR_COLLISION scplayer TRUE
							SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
							SET_PLAYER_CONTROL player1 ON
							SET_CHAR_COORDINATES scplayer 706.2595 918.6275 -19.6407 
							SET_CHAR_HEADING scplayer 127.1840
							RESTORE_CAMERA_JUMPCUT
							flag = 0
						ENDIF
					ENDIF
				ELSE
					flag = 0
					TERMINATE_THIS_SCRIPT
				ENDIF
			ENDIF
		ENDIF
	ELSE
		flag = 0
		TERMINATE_THIS_SCRIPT
	ENDIF

GOTO crane3_script_loop



update_crane_camera1:
	
	// camera position 1
	IF DOES_OBJECT_EXIST base_stand
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS base_stand -9.0 -6.8 6.0 c3_campos_x c3_campos_y c3_campos_z
	ENDIF

	IF DOES_OBJECT_EXIST arm
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS arm 0.0 0.0 59.0  x y z
		GET_ROPE_HEIGHT_FOR_OBJECT arm temp_float
		temp_float += -1.0
		temp_float *= -1.0
		temp_float *= 72.0
		z -= temp_float
		c3_camlook_x = x
		c3_camlook_y = y
		c3_camlook_z = z
	ENDIF

	SET_FIXED_CAMERA_POSITION  c3_campos_x c3_campos_y c3_campos_z 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT c3_camlook_x c3_camlook_y c3_camlook_z JUMP_CUT

RETURN


}


MISSION_END
