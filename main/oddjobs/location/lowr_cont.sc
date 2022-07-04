MISSION_START
{
lowr_minigame_manager:
SCRIPT_NAME LOWRCON

// peds
LVAR_INT contact_dude


// flags
LVAR_INT flag

LVAR_INT keep_this_script_alive

LVAR_INT dm

LVAR_INT stored_car

flag = 0		

IF flag = -1
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 contact_dude
	CREATE_CAR PONY 0.0 0.0 0.0 stored_car 
ENDIF

//WRITE_DEBUG lowr_contact_started

keep_this_script_alive = 0

lowr_loop:
WAIT 0		
		   
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
		OR keep_this_script_alive = 1
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1812.3690 -1929.9215 100.0 100.0 FALSE
				
				// if contact dude is killed quit out
				IF DOES_CHAR_EXIST contact_dude
					IF IS_CHAR_DEAD contact_dude
						GOTO lowr_cleanup
					ENDIF
				ENDIF
		
				//WRITE_DEBUG_WITH_INT flag flag
				//VIEW_INTEGER_VARIABLE lowrider_meeting_is_loaded lowrider_meeting_is_loaded

				SWITCH flag 

					// create contact dude
					CASE 0
						REQUEST_MODEL WMYMECH
						WHILE NOT HAS_MODEL_LOADED WMYMECH
							WAIT 0
						ENDWHILE	
						COPY_CHAR_DECISION_MAKER DM_PED_EMPTY dm					  	 
						CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 1812.3690 -1929.9215 12.5486 contact_dude
						SET_CHAR_HEADING contact_dude 270.0
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER contact_dude TRUE
						SET_CHAR_DECISION_MAKER contact_dude dm
						IF IS_PLAYER_PLAYING player1
							TASK_LOOK_AT_CHAR contact_dude scplayer	-2
						ENDIF
						MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
						FREEZE_CHAR_POSITION contact_dude TRUE
						flag++
					BREAK

					// wait for player to approach contact dude
					CASE 1
						// check contact dude hasn't moved
						IF NOT IS_CHAR_DEAD contact_dude 
							IF LOCATE_CHAR_ON_FOOT_3D contact_dude 1813.3690 -1929.9215 12.5486 1.2 1.2 2.0 FALSE
								IF NOT IS_CHAR_DEAD contact_dude 
									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS contact_dude 0.0 1.0 0.0 x y z
								ENDIF
								IF NOT IS_CHAR_DEAD contact_dude
									IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR contact_dude scplayer
										TASK_KILL_CHAR_ON_FOOT contact_dude scplayer
										GOTO lowr_cleanup
									ENDIF
								ENDIF
								IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer x y z 0.7 0.7 1.2 TRUE
									SET_PLAYER_CONTROL player1 OFF
									IF NOT IS_CHAR_DEAD contact_dude
										TASK_TURN_CHAR_TO_FACE_CHAR scplayer contact_dude
									ENDIF
									//SET_FIXED_CAMERA_POSITION 1815.8939 -1932.7081 15.2576 0.0 0.0 0.0
									//POINT_CAMERA_AT_POINT 1815.0942 -1932.2120 14.9195 JUMP_CUT
									flag++
								ENDIF 
							ENDIF
//							IF IS_PLAYER_PLAYING player1
//								IF IS_CHAR_IN_ANY_CAR scplayer 
//									IF NOT IS_CAR_DEAD stored_car
//										IF NOT IS_CHAR_IN_CAR scplayer stored_car
//											//MARK_CAR_AS_NO_LONGER_NEEDED stored_car
//											STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_car 
//											//WRITE_DEBUG stored_car1
//										ENDIF
//									ELSE
//										STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_car
//										//WRITE_DEBUG stored_car2
//									ENDIF
//								ENDIF
//							ENDIF
						ENDIF  
					BREAK

					// cutscene telling player where the lowrider meeting is
					CASE 2
						IF NOT IS_CHAR_DEAD contact_dude	
							IF IS_PLAYER_PLAYING player1
								TASK_LOOK_AT_CHAR contact_dude scplayer	-2
							ENDIF
							CLEAR_CHAR_TASKS contact_dude
							flag++
						ENDIF
					BREAK
		
					CASE 3
						IF NOT IS_CHAR_DEAD contact_dude
							TASK_PLAY_ANIM contact_dude IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE 2000
						ENDIF   
						$audio_string    = &MEC_HI	
						audio_sound_file = SOUND_MEC_HI
						START_NEW_SCRIPT audio_line contact_dude 0 1 1 0
						TIMERA = 0
						flag++
					BREAK

					// wait for input
					CASE 4
						IF TIMERA > 1000
							IF audio_line_is_active = 0
								PRINT_HELP_FOREVER TALK_1  

								IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
									// yea!
									$audio_string    = &SYN2_LO	
									audio_sound_file = SOUND_SYN2_LO
									START_NEW_SCRIPT audio_line scplayer 0 1 1 0

									CLEAR_HELP	
									TIMERA = 0
									keep_this_script_alive = 1
									flag++

								ELSE

									IF IS_BUTTON_PRESSED PAD1 DPADLEFT

										// nah!
										$audio_string    = &VOFFE1N	
										audio_sound_file = SOUND_VOFFE1N
										START_NEW_SCRIPT audio_line scplayer 0 1 1 0

										CLEAR_HELP
										TIMERA = 0
										RESTORE_CAMERA
										flag += 2

									ENDIF

								ENDIF

							ENDIF
						ENDIF
					BREAK
						 

					// yes
					CASE 5
						IF TIMERA > 1000
							IF audio_line_is_active = 0
								//load_and_launch_mission_if_poss = LOWR_SCRIPT
								DO_FADE 150 FADE_OUT
								WHILE GET_FADING_STATUS
									WAIT 0
								ENDWHILE
								lowrider_meeting_is_loaded = 0
//								IF IS_PLAYER_PLAYING player1
//									SET_PLAYER_CONTROL player1 ON
//								ENDIF
								load_and_launch_mission_if_poss = LOWR_SCRIPT

								flag += 2
								//GOTO lowr_cleanup
							ENDIF
						ENDIF
					BREAK

					// no
					CASE 6
						IF TIMERA > 1000
							IF audio_line_is_active = 0
								IF IS_PLAYER_PLAYING player1
									SET_PLAYER_CONTROL player1 ON
								ENDIF
								$audio_string    = &MEC_N	
								audio_sound_file = SOUND_MEC_N
								START_NEW_SCRIPT audio_line contact_dude 1 1 1 0
								GOTO lowr_cleanup
							ENDIF
						ENDIF
					BREAK

					
					// wait for loadrider meeting to load in
					CASE 7
						IF lowrider_meeting_is_loaded = 1
							SET_FIXED_CAMERA_POSITION 1815.8939 -1932.7081 15.2576 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 1815.0942 -1932.2120 14.9195 JUMP_CUT
							SWITCH_WIDESCREEN ON
							DO_FADE 150 FADE_IN
							WHILE GET_FADING_STATUS 
								WAIT 0
							ENDWHILE
							$audio_string    = &MEC_Y	
							audio_sound_file = SOUND_MEC_Y
							START_NEW_SCRIPT audio_line contact_dude 0 1 1 0
							TIMERA = 0
							flag++
						ENDIF
					BREAK

					CASE 8
						IF TIMERA > 1000
							//IF audio_line_is_active = 0
								IF IS_PLAYER_PLAYING player1
									CAMERA_RESET_NEW_SCRIPTABLES
									CAMERA_SET_VECTOR_MOVE	1815.8939 -1932.7081 15.2576 1813.4437 -1932.0631 18.5520 3000 TRUE	
									CAMERA_SET_VECTOR_TRACK	1815.0942 -1932.2120 14.9195 1812.6891 -1931.4381 18.3523 3000 TRUE
									CAMERA_PERSIST_TRACK TRUE
									CAMERA_PERSIST_POS TRUE
									TIMERA = 0
									flag++
								ENDIF
							//ENDIF
						ENDIF
					BREAK

					CASE 9
						IF TIMERA > 5000
						OR IS_BUTTON_PRESSED PAD1 CROSS
							IF IS_PLAYER_PLAYING player1
								CAMERA_RESET_NEW_SCRIPTABLES
								SWITCH_WIDESCREEN OFF
								SET_CAMERA_BEHIND_PLAYER 
								RESTORE_CAMERA_JUMPCUT
								SET_PLAYER_CONTROL player1 ON
								GOTO lowr_cleanup
							ENDIF	
						ENDIF
					BREAK


				ENDSWITCH
				
			ELSE
				GOTO lowr_cleanup
			ENDIF
		ELSE
			GOTO lowr_cleanup
		ENDIF
	ELSE
		GOTO lowr_cleanup
	ENDIF

GOTO lowr_loop

lowr_cleanup:
//START_NEW_SCRIPT cleanup_audio_lines
IF NOT IS_CHAR_DEAD contact_dude
	FREEZE_CHAR_POSITION contact_dude FALSE
	CLEAR_LOOK_AT contact_dude
	TASK_WANDER_STANDARD contact_dude
ENDIF
MARK_CHAR_AS_NO_LONGER_NEEDED contact_dude
MARK_CAR_AS_NO_LONGER_NEEDED stored_car
REMOVE_ANIMATION GANGS
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
keep_this_script_alive = 0
REMOVE_DECISION_MAKER dm

TERMINATE_THIS_SCRIPT 

}
MISSION_END