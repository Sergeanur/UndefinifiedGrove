MISSION_START


{///////////////////////////////////////////////////////////////////////////////
crane2_script://///////////////////  DOCKS MAGNO CRANE /////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME CRANE2

LVAR_INT flag

// global variables for control of crane through script
VAR_FLOAT 	crane_rope_length
//VAR_INT 	release_dock_crane_object

// camera coords
VAR_FLOAT cam_pos_x  cam_pos_y  cam_pos_z
VAR_FLOAT new_cam_look_x new_cam_look_y new_cam_look_z
VAR_FLOAT cam_look_x cam_look_y cam_look_z
VAR_FLOAT cam_vec_x  cam_vec_y  cam_vec_z

LVAR_FLOAT trigger_x trigger_y trigger_z
LVAR_FLOAT temp_float temp_float2

LVAR_FLOAT LeftStkX_F LeftStkY_F
LVAR_FLOAT x2 y2
LVAR_FLOAT new_cont_x new_cont_y
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT vec2_x vec2_y vec2_z
LVAR_FLOAT dist1 dist2 dist3
LVAR_FLOAT new_crane_x1 new_crane_y1 new_crane_x2 new_crane_y2
LVAR_FLOAT new_crane_h
LVAR_INT temp_int
LVAR_INT camera_mode
LVAR_INT manual_control

VAR_FLOAT crane_arm_length

VAR_FLOAT dock_crane_speed
VAR_FLOAT dock_crane_accel
VAR_FLOAT dock_crane_max_speed

VAR_FLOAT dock_crane_travel_vec_x dock_crane_travel_vec_y

VAR_INT crane2_sound_flag

flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 magno_base
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 magno_cabin
	CREATE_OBJECT_NO_OFFSET WOODENBOX 0.0 0.0 0.0 magno_arm
ENDIF

crane_help2 = 0

crane_arm_length = 33.7 //37.5 //32.0	 
magno_arm_rotate_y = 15.0
manual_control = 0
camera_mode = 1

magno_camera_type = 0

crane2_script_loop:
    
	WAIT 0

	IF DOES_OBJECT_EXIST magno_base
		IF IS_PLAYER_PLAYING player1 

			IF disable_all_cranes = 0

				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer magno_base 100.0 100.0 FALSE

					IF reset_crane_camera = 1
						GOSUB update_crane_camera
					ENDIF

					IF IS_PLAYER_PLAYING player1
					AND disable_crane = 0

						IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer magno_base 100.0 100.0 FALSE

							IF player_is_in_crane = 2
								GOSUB update_crane_camera
							ENDIF

							// WAIT TO SEE IF PLAYER GETS INTO CRANE ++++++++
							
							IF flag = 0
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base -1.7837 2.4699 -24.1519 trigger_x trigger_y trigger_z
								flag++
							ENDIF

							IF flag = 1

								IF LOCATE_CHAR_ON_FOOT_3D scplayer trigger_x trigger_y trigger_z 1.2 1.2 1.5 TRUE
									IF IS_CHAR_ON_FOOT scplayer 
										IF crane_help2 = 0
											PRINT_HELP_FOREVER CR_2  
											crane_help2 = 1
										ENDIF
										
										IF IS_BUTTON_PRESSED PAD1 TRIANGLE
											CLEAR_HELP

											flag++
										ENDIF	
									ENDIF
								ELSE
									IF NOT crane_help2 = 0
										CLEAR_HELP
										crane_help2 = 0
									ENDIF
								ENDIF
								
								// sort out arm rotation
								IF magno_arm_rotate_y < 15.00
									magno_arm_rotate_y = 15.0
									IF DOES_OBJECT_EXIST magno_arm
										SET_OBJECT_ROTATION magno_arm magno_arm_rotate_y 0.0  magno_arm_h
									ENDIF
								ENDIF
								IF magno_arm_rotate_y > 30.0
									magno_arm_rotate_y = 30.0
									IF DOES_OBJECT_EXIST magno_arm
										SET_OBJECT_ROTATION magno_arm magno_arm_rotate_y 0.0  magno_arm_h
									ENDIF
								ENDIF

							ENDIF

							// TRANSFER PLAYER INTO CRANE ++++++++++++++++++++
							IF flag = 2

								IF DOES_OBJECT_EXIST magno_cabin
									REPORT_MISSION_AUDIO_EVENT_AT_OBJECT magno_cabin SOUND_CRANE_ENTER
								ENDIF

								SET_PLAYER_CONTROL player1 OFF	 
								SET_MINIGAME_IN_PROGRESS TRUE
								DO_FADE 500 FADE_OUT 
								WHILE GET_FADING_STATUS 
									WAIT 0
								ENDWHILE
								PLAYER_ENTERED_DOCK_CRANE
								DISPLAY_NON_MINIGAME_HELP_MESSAGES TRUE
								
								// get current component details
								GET_OBJECT_HEADING magno_cabin magno_cabin_h
								GET_OBJECT_HEADING magno_arm magno_arm_h

								IF DOES_OBJECT_EXIST magno_base
									IF IS_PLAYER_PLAYING player1
										GET_OBJECT_COORDINATES magno_base magno_base_x magno_base_y magno_base_z
										ATTACH_CHAR_TO_OBJECT scplayer magno_base 0.0 0.0 2.0 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
									ENDIF
								ENDIF

								// set player position
								IF IS_PLAYER_PLAYING player1
									FREEZE_CHAR_POSITION scplayer TRUE 
									SET_CHAR_VISIBLE scplayer FALSE
									SET_PLAYER_CONTROL player1 ON
									SET_CHAR_COLLISION scplayer OFF
									SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE 
									player_is_in_crane = 2
									crane2_sound_flag = 0
								ENDIF

								// store crane's initial position
								IF DOES_OBJECT_EXIST magno_base
									GET_OBJECT_COORDINATES magno_base new_crane_x1 new_crane_y1 z
								ENDIF
								IF DOES_OBJECT_EXIST magno_arm
									GET_OBJECT_HEADING magno_arm new_crane_h
								ENDIF
							
								// get initial look at
								IF DOES_OBJECT_EXIST magno_arm
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_arm 0.0 crane_arm_length -3.0 cam_look_x cam_look_y cam_look_z
									GET_ROPE_HEIGHT_FOR_OBJECT magno_arm temp_float
									temp_float += -1.0
									temp_float *= -1.0
									temp_float *= 50.0
									cam_look_z -= temp_float

									x = cam_look_x - magno_base_x
									y = cam_look_y - magno_base_y

									GET_DISTANCE_BETWEEN_COORDS_2D cam_look_x cam_look_y magno_base_x magno_base_y temp_float
									x /= temp_float
									y /= temp_float
									x *= crane_arm_length
									y *= crane_arm_length
									cam_look_x = magno_base_x + x
									cam_look_y = magno_base_y + y 
								ENDIF

								new_cont_x = cam_look_x
								new_cont_y = cam_look_y

								dock_crane_speed = 0.0
								dock_crane_accel = 0.0
								dock_crane_max_speed = 0.3
								
								SET_EVERYONE_IGNORE_PLAYER player1 OFF

								GOSUB update_crane_camera

								DO_FADE 500 FADE_IN 
								WHILE GET_FADING_STATUS 
									WAIT 0										
									GOSUB update_crane_camera										
								ENDWHILE	

								flag++
							ENDIF

							// CONTROL CRANE ++++++++++++++++++++++++++++++
							IF flag = 3
								IF IS_PLAYER_PLAYING player1

                                  	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_UP
										crane_arm_length += 0.1
									ENDIF
									IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DOWN
										crane_arm_length += -0.1
									ENDIF 

									// move control object
									IF player_is_in_crane = 2

										IF IS_BUTTON_PRESSED PAD1 LEFTSTICKX
										OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
										//OR IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1

											IF NOT crane2_sound_flag = 1
												IF DOES_OBJECT_EXIST magno_cabin
													REPORT_MISSION_AUDIO_EVENT_AT_OBJECT magno_cabin SOUND_CRANE_MOVE_START
												ENDIF
												crane2_sound_flag = 1
											ENDIF

											IF disable_manual_control = 0
												
												manual_control = 1

												GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
											 	LeftStkX_F =# LStickX 
											 	LeftStkY_F =# LStickY
											 	LeftStkX_F /= 128.0
											 	LeftStkY_F /= 128.0
												LeftStkX_F *= 0.15
												LeftStkY_F *= 0.15
												
												vec_x = cam_vec_x
												vec_y = cam_vec_y

												vec2_x = vec_y		 // right vec
												vec2_y = vec_x
												vec2_y *= -1.0
																							
												// change normal vec
												vec_x *= LeftStkY_F
												vec_y *= LeftStkY_F
												vec_x *= -1.0
												vec_y *= -1.0
											 	new_cont_x +=@ vec_x
											 	new_cont_y +=@ vec_y

												// change right vec
												vec2_x *= LeftStkX_F
												vec2_y *= LeftStkX_F
												new_cont_x +=@ vec2_x
												new_cont_y +=@ vec2_y

												// check new_cont isn't too far from cam_look
												GET_DISTANCE_BETWEEN_COORDS_2D new_cont_x new_cont_y new_cam_look_x new_cam_look_y temp_float
												
												IF temp_float > 0.3
													x = new_cont_x - new_cam_look_x
													y = new_cont_y - new_cam_look_y
													x /= temp_float
													y /= temp_float
													x *= 0.3
													y *= 0.3
													new_cont_x = new_cam_look_x + x
													new_cont_y = new_cam_look_y + y
												ENDIF

												

												z = 0.0

												// find new crane position - 2 possibles

												// 1. find perpendicular intersect point
												y = new_cont_y
												x = y - 1671.5
																								
												x2 = new_cont_x
												y2 = x2 + 1671.5

												// mid point
												x += x2
												
												y += y2
												x /= 2.0
												
												y /= 2.0

												// CHECK NEW OBJECT POSITION IS IN DEFINED AREA
								  				
												IF x < -1648.057
													GET_DISTANCE_BETWEEN_COORDS_2D -1648.057 23.852 new_cont_x new_cont_y temp_float
													IF temp_float > crane_arm_length
														vec_x = new_cont_x + 1648.057
														vec_y = new_cont_y - 23.852
														vec_x /= temp_float
														vec_y /= temp_float
														vec_x *= crane_arm_length
														vec_y *= crane_arm_length
														new_cont_x = -1648.057 + vec_x
														new_cont_y = 23.852 + vec_y
													ENDIF	
												
												ELSE
													IF x > -1532.742
														GET_DISTANCE_BETWEEN_COORDS_2D -1532.742 138.719 new_cont_x new_cont_y temp_float
														IF temp_float > crane_arm_length
															vec_x = new_cont_x + 1532.742
															vec_y = new_cont_y - 138.719
															vec_x /= temp_float
															vec_y /= temp_float
															vec_x *= crane_arm_length
															vec_y *= crane_arm_length
															new_cont_x = -1532.742 + vec_x
															new_cont_y = 138.719 + vec_y
														ENDIF
													ELSE
														GET_DISTANCE_BETWEEN_COORDS_2D x y new_cont_x new_cont_y temp_float
														IF temp_float > crane_arm_length
															vec_x = new_cont_x - x
															vec_y = new_cont_y - y
															vec_x /= temp_float
															vec_y /= temp_float
															vec_x *= crane_arm_length
															vec_y *= crane_arm_length
															new_cont_x = x + vec_x
															new_cont_y = y + vec_y
														ENDIF
													ENDIF 
												ENDIF

												
																								
												// 2. get dist from perp intersect point to object point
												GET_DISTANCE_BETWEEN_COORDS_2D x y new_cont_x new_cont_y dist1

																																					
												// 3. find dist from perp intersect point to new crane point
												temp_float = crane_arm_length * crane_arm_length
												temp_float2 = dist1 * dist1
												dist2 = temp_float - temp_float2

												// 4. find distance to add/subtract to perp point
												dist3 = dist2 / 2.0

												SQRT dist3 dist3

												// 5. find 2 possible new crane points
												new_crane_x1 = x - dist3
												new_crane_y1 = y - dist3
												new_crane_x2 = x + dist3
												new_crane_y2 = y + dist3

												// 6. check if either of the new crane points are within range
												
												temp_int = 0
												
												IF new_crane_x1 <  -1532.742
												AND new_crane_x1 > -1648.057
													temp_int++
												ENDIF
												IF new_crane_x2 <  -1532.742
												AND new_crane_x2 > -1648.057
													temp_int += 2
												ENDIF

												IF NOT temp_int = 0
													// if only one new crane coord is viable
													IF temp_int = 1
														// do nothing new_crane_x,y1 is already set
													ENDIF
													IF temp_int = 2
														new_crane_x1 = new_crane_x2
														new_crane_y1 = new_crane_y2
													ENDIF
													
													// if two new crane coords are viable, find one closest to current position
													IF temp_int = 3
														IF DOES_OBJECT_EXIST magno_base 
															GET_DISTANCE_BETWEEN_COORDS_2D magno_base_x magno_base_y new_crane_x1 new_crane_y1 temp_float
															GET_DISTANCE_BETWEEN_COORDS_2D magno_base_x magno_base_y new_crane_x2 new_crane_y2 temp_float2
															IF temp_float2 < temp_float
																new_crane_x1 = new_crane_x2
																new_crane_y1 = new_crane_y2
															ENDIF
														ENDIF
													ENDIF
											
													// find the desired heading for crane arm
													vec_x = new_cont_x - new_crane_x1
													vec_y = new_cont_y - new_crane_y1
													GET_HEADING_FROM_VECTOR_2D vec_x vec_y new_crane_h
												ELSE
													new_crane_x1 = magno_base_x
													new_crane_y1 = magno_base_y
													vec_x = new_cont_x - magno_base_x
													vec_y = new_cont_y - magno_base_y
													GET_HEADING_FROM_VECTOR_2D vec_x vec_y new_crane_h
												ENDIF
													
											ENDIF // disable_manual_control = 0
										
										ELSE

											IF NOT crane2_sound_flag = 0
												IF DOES_OBJECT_EXIST magno_cabin
													REPORT_MISSION_AUDIO_EVENT_AT_OBJECT magno_cabin SOUND_CRANE_MOVE_STOP
												ENDIF
												crane2_sound_flag = 0
											ENDIF

											IF NOT manual_control = 0
											
												IF manual_control = 1
													// stop moving crane if player stops pushing stick
													x =	cam_look_x
													y =	cam_look_y

													vec_x = x - magno_base_x	
													vec_y = y - magno_base_y	

													GET_HEADING_FROM_VECTOR_2D vec_X vec_y new_crane_h

													vec_x = dock_crane_travel_vec_x  
													vec_y = dock_crane_travel_vec_y

												ENDIF

												IF dock_crane_speed > 0.0
													
													dock_crane_accel = -0.007
													dock_crane_speed +=@ dock_crane_accel
													IF dock_crane_speed < 0.0
														dock_crane_speed = 0.0
														manual_control = 0
													ELSE
														manual_control = 2
													ENDIF
													x = dock_crane_speed * vec_x
													y = dock_crane_speed * vec_y
													magno_base_x += x
													magno_base_y += y
												ENDIF

	 										ENDIF

										ENDIF

										// move crane into desired position
										IF manual_control = 1
											IF DOES_OBJECT_EXIST magno_base

												GET_DISTANCE_BETWEEN_COORDS_2D magno_base_x magno_base_y new_crane_x1 new_crane_y1 temp_float

												IF temp_float > 0.01
													dock_crane_travel_vec_x = new_crane_x1 - magno_base_x 
													dock_crane_travel_vec_y	= new_crane_y1 - magno_base_y
													dock_crane_travel_vec_x	/= temp_float
													dock_crane_travel_vec_y	/= temp_float
												ENDIF
												
												IF temp_float > 0.01 //dock_crane_speed

													dock_crane_accel = 0.003
													dock_crane_speed +=@ dock_crane_accel
													IF dock_crane_speed > dock_crane_max_speed
														dock_crane_speed = dock_crane_max_speed
													ENDIF
													vec_x =	dock_crane_travel_vec_x
													vec_y = dock_crane_travel_vec_y
													IF temp_float > dock_crane_speed 
														vec_x *= dock_crane_speed
														vec_y *= dock_crane_speed
													ELSE
														vec_x *= temp_float
														vec_y *= temp_float
														dock_crane_speed = temp_float		
													ENDIF
													magno_base_x += vec_x
													magno_base_y += vec_y
												ENDIF 

											ENDIF
										ENDIF
									

										// move crane arm to desired heading
										IF manual_control = 1
											IF DOES_OBJECT_EXIST magno_arm

												IF new_crane_h > 180.0
													new_crane_h += -360.0
												ENDIF
												IF new_crane_h < -180.0
													new_crane_h += 360.0
												ENDIF

												heading = magno_arm_h

												IF heading > 180.0
													heading += -360.0
												ENDIF
												IF heading < -180.0
													heading+= 360.0
												ENDIF
									
												temp_float = new_crane_h - heading
												IF temp_float > 180.0
													temp_float += -360.0
												ENDIF
												IF temp_float < -180.0
													temp_float += 360.0
												ENDIF

												IF temp_float >= 0.0
													IF temp_float > 0.2
														temp_float = 0.2
													ENDIF
												ELSE
													IF temp_float < -0.2
														temp_float = -0.2
													ENDIF
												ENDIF

												heading +=@ temp_float
												magno_cabin_h = heading
												magno_arm_h = heading

											ENDIF
										ENDIF


				// CONTROL MAGNO CRANE  -------------------------------------------------------------------------------
										
										IF disable_crane_exit = 0  // This is intentional not to let the player get out of the crane here.
											IF IS_BUTTON_PRESSED PAD1 TRIANGLE
												flag++
											ENDIF
										ENDIF

									ENDIF	

				// UPDATE PARTS ------------------------------------------------------------------------
									// base	
									IF magno_base_x <  -1532.742
									AND magno_base_x > -1648.057			
										IF DOES_OBJECT_EXIST magno_base
											SET_OBJECT_COORDINATES_AND_VELOCITY magno_base magno_base_x magno_base_y magno_base_z
										ENDIF
									ELSE
										IF DOES_OBJECT_EXIST magno_base
											GET_OBJECT_COORDINATES magno_base magno_base_x magno_base_y magno_base_z 
										ENDIF
									ENDIF
									IF DOES_OBJECT_EXIST magno_base
										IF DOES_OBJECT_EXIST magno_base_LOD
											GET_OBJECT_COORDINATES magno_base x y z
											GET_OBJECT_HEADING magno_base heading
											SET_OBJECT_COORDINATES magno_base_LOD x y z
											SET_OBJECT_HEADING magno_base_LOD heading
										ENDIF
									ENDIF
									// magno_cabin												   
									IF DOES_OBJECT_EXIST magno_base								   
										IF DOES_OBJECT_EXIST magno_cabin							   
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base 0.0. 0.0 0.0 x y z
											SET_OBJECT_HEADING magno_cabin magno_cabin_h
											SET_OBJECT_COORDINATES_AND_VELOCITY magno_cabin x y z
											SET_CHAR_HEADING scplayer magno_cabin_h
										ENDIF
										IF DOES_OBJECT_EXIST magno_cabin_LOD
											GET_OBJECT_COORDINATES magno_cabin x y z
											GET_OBJECT_HEADING magno_cabin heading
											SET_OBJECT_COORDINATES magno_cabin_LOD x y z
											SET_OBJECT_HEADING magno_cabin_LOD heading
										ENDIF
									ENDIF
									// arm												  
									IF DOES_OBJECT_EXIST magno_arm							  
										IF DOES_OBJECT_EXIST magno_cabin
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin 0.0 -2.185 8.51 x y z
											GET_OBJECT_HEADING magno_cabin magno_arm_h
											SET_OBJECT_COORDINATES_AND_VELOCITY magno_arm x y z
											SET_OBJECT_ROTATION magno_arm magno_arm_rotate_y 0.0 magno_arm_h
										ENDIF
										IF DOES_OBJECT_EXIST magno_arm_LOD
											GET_OBJECT_COORDINATES magno_arm x y z
											GET_OBJECT_HEADING magno_arm heading
											SET_OBJECT_COORDINATES magno_arm_LOD x y z
											SET_OBJECT_HEADING magno_arm_LOD heading
											SET_OBJECT_ROTATION magno_arm_LOD magno_arm_rotate_y 0.0 magno_arm_h											
										ENDIF
									ENDIF
									
								ENDIF
							ENDIF

							// TRANSFER PLAYER OUT OF CRANE +++++++++++++++++
							IF flag = 4
							OR remove_player_from_crane = 1
								IF IS_PLAYER_PLAYING player1
									GET_CHAR_COORDINATES scplayer x y z
									IF DOES_OBJECT_EXIST magno_cabin
										REPORT_MISSION_AUDIO_EVENT_AT_OBJECT magno_cabin SOUND_CRANE_EXIT
									ENDIF
									CLEAR_HELP
									PLAYER_LEFT_CRANE
									SET_MINIGAME_IN_PROGRESS FALSE
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base 2.0 -4.0 0.0 x y z
									GET_GROUND_Z_FOR_3D_COORD x y z z				
									SET_CHAR_COORDINATES scplayer x y z
									DETACH_CHAR_FROM_CAR scplayer
									FREEZE_CHAR_POSITION scplayer FALSE
									SET_CHAR_VISIBLE scplayer TRUE
									SET_CHAR_COLLISION scplayer TRUE
									SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
									SET_PLAYER_CONTROL player1 ON
									CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
									SET_CAMERA_BEHIND_PLAYER
									RESTORE_CAMERA_JUMPCUT
									player_is_in_crane = 0
									remove_player_from_crane = 0
									flag = 0

								ENDIF
							ENDIF
						ELSE
							IF NOT flag = 0
								flag = 0
							ENDIF
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

GOTO crane2_script_loop


update_crane_camera:

	LVAR_INT select_is_pressed
	IF IS_BUTTON_PRESSED PAD1 SELECT
		IF select_is_pressed = 0
			camera_mode++
			IF camera_mode > 2
				camera_mode = 0
			ENDIF
			magno_camera_type = camera_mode
			select_is_pressed++
		ENDIF
	ELSE
		IF NOT select_is_pressed = 0
			select_is_pressed = 0
		ENDIF
	ENDIF

	IF player_is_in_crane = 2
		IF reset_crane_camera = 1
			camera_mode = magno_camera_type	
			reset_crane_camera = 0
		ENDIF
	ENDIF

	IF NOT magno_camera_type = camera_mode
		magno_camera_type = camera_mode
	ENDIF	

	IF camera_mode = 0
		IF DOES_OBJECT_EXIST magno_cabin
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin 6.1 15.0 5.1 cam_pos_x  cam_pos_y  cam_pos_z
		ENDIF
	ELSE
		IF camera_mode = 1
			IF DOES_OBJECT_EXIST magno_base
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base -4.0000 17.3000 35.0000 cam_pos_x  cam_pos_y  cam_pos_z
			ENDIF
		ELSE	
			IF DOES_OBJECT_EXIST magno_cabin
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin 3.0 45.0 0.0 cam_pos_x  cam_pos_y  cam_pos_z
			ENDIF
		ENDIF
	ENDIF

//	IF camera_mode = 0
//		IF DOES_OBJECT_EXIST magno_cabin
//			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin cr_x cr_y cr_z cam_pos_x  cam_pos_y  cam_pos_z
//		ENDIF
//	ELSE
//		IF camera_mode = 1
//			IF DOES_OBJECT_EXIST magno_base
//				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base cr_x cr_y cr_z cam_pos_x  cam_pos_y  cam_pos_z
//			ENDIF
//		ELSE	
//			IF DOES_OBJECT_EXIST magno_cabin
//				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin cr_x cr_y cr_z cam_pos_x  cam_pos_y  cam_pos_z
//			ENDIF
//		ENDIF
//	ENDIF



	IF DOES_OBJECT_EXIST magno_arm
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_arm 0.0 crane_arm_length -3.0 new_cam_look_x new_cam_look_y new_cam_look_z
		GET_ROPE_HEIGHT_FOR_OBJECT magno_arm temp_float
		temp_float += -1.0
		temp_float *= -1.0
		temp_float *= 50.0
		new_cam_look_z -= temp_float

		
		x = new_cam_look_x - magno_base_x
		y = new_cam_look_y - magno_base_y

		GET_DISTANCE_BETWEEN_COORDS_2D new_cam_look_x new_cam_look_y magno_base_x magno_base_y temp_float
		x /= temp_float
		y /= temp_float
		x *= crane_arm_length
		y *= crane_arm_length
		new_cam_look_x = magno_base_x + x
		new_cam_look_y = magno_base_y + y 

		
		// interpolate to new point
		GET_DISTANCE_BETWEEN_COORDS_3D new_cam_look_x new_cam_look_y new_cam_look_z	cam_look_x cam_look_y cam_look_z temp_float
		IF temp_float > 0.3
			cam_vec_x =	new_cam_look_x - cam_look_x
			cam_vec_y =	new_cam_look_y - cam_look_y
			cam_vec_z =	new_cam_look_z - cam_look_z
//			cam_vec_x /= temp_float
//			cam_vec_y /= temp_float
//			cam_vec_z /= temp_float
//			cam_vec_x *= 0.1
//			cam_vec_y *= 0.1
//			cam_vec_z *= 0.1
			cam_vec_x *= 0.05
			cam_vec_y *= 0.05
			cam_vec_z *= 0.05
			cam_look_x += cam_vec_x
			cam_look_y += cam_vec_y
			cam_look_z += cam_vec_z
			//WRITE_DEBUG CAMERA_LOOK_INTERP_ON
//		ELSE
//			cam_look_x = new_cam_look_x
//			cam_look_y = new_cam_look_y
//			cam_look_z = new_cam_look_z
//			//WRITE_DEBUG CAMERA_LOOK_INTERP_OFF
		ENDIF

		GET_ACTIVE_CAMERA_COORDINATES x y z
		
		//GET_DISTANCE_BETWEEN_COORDS_3D cam_pos_x  cam_pos_y  cam_pos_z 	x y z temp_float

		IF disable_crane = 0
			vec_x = cam_pos_x - x
			vec_y = cam_pos_y - y
			vec_z = cam_pos_z - z
			vec_x *= 0.2
			vec_y *= 0.2
			vec_z *= 0.2
			cam_pos_x =	x +	vec_x
			cam_pos_y =	y +	vec_y
			cam_pos_z =	z +	vec_z
		ENDIF



		SET_FIXED_CAMERA_POSITION cam_pos_x  cam_pos_y  cam_pos_z 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT cam_look_x cam_look_y cam_look_z JUMP_CUT
		cam_vec_x = cam_look_x - cam_pos_x
		cam_vec_y = cam_look_y - cam_pos_y
		cam_vec_z =	cam_look_z - cam_pos_z
		GET_DISTANCE_BETWEEN_COORDS_3D cam_pos_x  cam_pos_y cam_pos_z cam_look_x cam_look_y cam_look_z temp_float
		cam_vec_x /= temp_float
		cam_vec_y /= temp_float
		cam_vec_z /= temp_float
	ENDIF

//	z = cam_look_z + 1.0
//	LINE cam_look_x cam_look_y cam_look_z cam_look_x cam_look_y z

RETURN



// put in x y z float

//VAR_FLOAT cr_x cr_y cr_z
//crane_cam_finder:
//
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
//		cr_z += 0.1
//	ENDIF
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
//		cr_z += -0.1
//	ENDIF
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
//		cr_x += 0.1
//	ENDIF
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
//		cr_x += -0.1
//	ENDIF
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
//		cr_y += 0.1
//	ENDIF
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
//		cr_y += -0.1
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
//
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_STRING_TO_DEBUG_FILE "crane camera offsets"
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_FLOAT_TO_DEBUG_FILE cr_x
//		SAVE_FLOAT_TO_DEBUG_FILE cr_y
//		SAVE_FLOAT_TO_DEBUG_FILE cr_Z
//		SAVE_NEWLINE_TO_DEBUG_FILE
//
//	ENDIF
//
//RETURN 






}



MISSION_END