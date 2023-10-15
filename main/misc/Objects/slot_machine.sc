MISSION_START

{
///////////////////////////////////////////////////////////////////////////////
slot_machine:///////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

SCRIPT_NAME BANDIT

LVAR_INT slot_machine
LVAR_INT reel[3]
LVAR_INT flag timer	reel_to_slow
flag = 0
reel_to_slow = 0
LVAR_FLOAT reel_rotation[3] reel_speed[3]
reel_speed[0] = 0.0
reel_speed[1] = 0.0
reel_speed[2] = 0.0

lvar_int loaded_tex_ld_slot
loaded_tex_ld_slot = 0

lvar_int slot_cost
generate_random_int_in_range 0 5 slot_cost

if slot_cost = 1
	slot_cost = 5
endif

if slot_cost = 0
	slot_cost = 1
endif

if slot_cost = 2
	slot_cost = 10
endif

if slot_cost = 3
	slot_cost = 20
endif

if slot_cost = 4
	slot_cost = 50
endif

IF flag = 1
	CREATE_OBJECT_NO_OFFSET KB_BANDIT_U 0.0 0.0 0.0 slot_machine 
	CREATE_OBJECT_NO_OFFSET CJ_Wheel_1 x y z reel[0]
ENDIF


slot_machine_loop:
	WAIT 0

	GET_GAME_TIMER game_timer

    IF DOES_OBJECT_EXIST slot_machine
		if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE slot_machine
			IF IS_PLAYER_PLAYING player1
			  
				SWITCH flag
			
				CASE 0
					LVAR_INT slot_machine_model
					GET_OBJECT_MODEL slot_machine slot_machine_model
					if HAS_MODEL_LOADED slot_machine_model
						GET_OBJECT_HEADING slot_machine	heading
						
						IF NOT DOES_OBJECT_EXIST reel[0]
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT slot_machine 1 x y z
							CREATE_OBJECT_NO_OFFSET CJ_Wheel_1 x y z reel[0]
							SET_OBJECT_HEADING reel[0] heading
						ENDIF

						IF NOT DOES_OBJECT_EXIST reel[1]
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT slot_machine 2 x y z
							CREATE_OBJECT_NO_OFFSET CJ_Wheel_02 x y z reel[1]
							SET_OBJECT_HEADING reel[1] heading
						ENDIF

						IF NOT DOES_OBJECT_EXIST reel[2]
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT slot_machine 3 x y z
							CREATE_OBJECT_NO_OFFSET CJ_Wheel_03 x y z reel[2]
							SET_OBJECT_HEADING reel[2] heading
						ENDIF
						++ flag
					ENDIF
				BREAK

				CASE 1
					
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS slot_machine 0.0 -1.0 -0.5 x y z
					lvar_int ped_using_slot
					GET_USER_OF_CLOSEST_MAP_ATTRACTOR x y z 1.5 KB_BANDIT_U PEDSLOT ped_using_slot
					
					IF ped_using_slot = -1
						
						IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
							
							PRINT_HELP_FOREVER_WITH_NUMBER SLOT_02 slot_cost

							SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE
							load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
							gosub draw_reel_sprites
							++ flag
						
						else
							if loaded_tex_ld_slot = 1
								remove_TEXTURE_DICTIONARY
								loaded_tex_ld_slot = 0
							endif
						ENDIF
					ENDIF
				BREAK
				
				
				CASE 2
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS slot_machine 0.0 -1.0 -0.5 x y z
					GET_USER_OF_CLOSEST_MAP_ATTRACTOR x y z 1.5 KB_BANDIT_U PEDSLOT ped_using_slot
					IF ped_using_slot = -1
						request_animation casino
						if has_animation_loaded casino
							IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
								lvar_int slot_user
								GET_RANDOM_CHAR_IN_AREA_OFFSET_NO_SAVE x y z 0.6 0.6 1.0 slot_user
								if slot_user = -1
									if not IS_MINIGAME_IN_PROGRESS
										gosub draw_reel_sprites
										
										IF IS_BUTTON_PRESSED PAD1 TRIANGLE

											temp_integer_1 = slot_cost - 1
											IF IS_SCORE_GREATER player1 temp_integer_1
												set_player_control player1 off
												HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
												GET_OBJECT_HEADING slot_machine	heading
												//OPEN_SEQUENCE_TASK sequence_task_obj
													//TASK_ACHIEVE_HEADING -1	heading
													//GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS slot_machine 0.0 -0.6 0.0 x y z
													TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM scplayer x y z heading 0.4 Slot_Plyr casino 4.0 FALSE FALSE FALSE FALSE 0
												//CLOSE_SEQUENCE_TASK sequence_task_obj
												//PERFORM_SEQUENCE_TASK scplayer sequence_task_obj
												//CLEAR_SEQUENCE_TASK sequence_task_obj
												temp_integer_1 = slot_cost * -1
												ADD_SCORE player1 temp_integer_1
												increment_int_stat MONEY_SPENT_GAMBLING	slot_cost
												temp_float_1 =#	slot_cost
												temp_float_1 *=	0.001
												increment_float_stat GAMBLING temp_float_1
												CLEAR_HELP
												
												SET_MINIGAME_IN_PROGRESS true
												timer = game_timer + 750
												++ flag
											
											ELSE
												ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_AMMUNATION_BUY_WEAPON_DENIED
											ENDIF
										ENDIF
									endif
								endif
							ELSE
								if loaded_tex_ld_slot = 1
									remove_TEXTURE_DICTIONARY
									loaded_tex_ld_slot = 0
								endif
								CLEAR_HELP
								SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
								flag = 1
							ENDIF
						ENDIF
					ELSE
						if loaded_tex_ld_slot = 1
							remove_TEXTURE_DICTIONARY
							loaded_tex_ld_slot = 0
						endif
						CLEAR_HELP
						SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
						flag = 1
					ENDIF
				BREAK

				CASE 3
					gosub draw_reel_sprites
					IF timer < game_timer
						load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
						if has_mission_audio_loaded	4
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_INSERT_COIN 
						endif
						SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
						USE_TEXT_COMMANDS TRUE
						if loaded_tex_ld_slot = 0
							LOAD_TEXTURE_DICTIONARY ld_slot
							
							LOAD_SPRITE 1 cherry  // Alphamode must manually be set to 2 in txd.txt for these textures.
							LOAD_SPRITE 2 grapes
							LOAD_SPRITE 3 r_69
							LOAD_SPRITE 4 bell
							LOAD_SPRITE 5 bar1_o
							LOAD_SPRITE 6 bar2_o
							
							loaded_tex_ld_slot = 1
						endif
						load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
						if has_mission_audio_loaded	4
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_WHEEL_START 
						endif
						++ flag
					ENDIF
				BREAK

				CASE 4
					reel_speed[0] +=@ 1.0
					IF reel_speed[0] > 10.0
						reel_speed[0] = 10.0
						GENERATE_RANDOM_INT_IN_RANGE 2000 3000 temp_integer_1
						reel_to_slow = 0
						timer = game_timer + temp_integer_1
						//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer false
						clear_char_tasks scplayer
						++ flag
					ENDIF
					reel_speed[1] +=@ 1.1
					IF reel_speed[1] > 10.0
						reel_speed[1] = 10.0
					ENDIF
					reel_speed[2] +=@ 1.05
					IF reel_speed[2] > 10.0
						reel_speed[2] = 10.0
					ENDIF

					GET_OBJECT_HEADING slot_machine	heading
					an = 0
					WHILE an < 3
						IF reel_speed[an] > 0.0
							reel_rotation[an] +=@ reel_speed[an]
							IF reel_rotation[an] > 360.0
							OR reel_rotation[an] = 360.0
								reel_rotation[an] -= 360.0
							ENDIF
							SET_OBJECT_ROTATION reel[an] reel_rotation[an] 0.0 heading
						ENDIF
						++ an
					ENDWHILE

					gosub draw_reel_sprites
					
				BREAK
				
				CASE 5
					IF timer < game_timer
						reel_speed[reel_to_slow] -=@ 0.3
						IF reel_speed[reel_to_slow] < 0.6
							temp_float_1 = reel_rotation[reel_to_slow] / 20.0
							temp_integer_1 =# temp_float_1
							temp_integer_1 *= 20
							temp_float_1 =# temp_integer_1
							temp_float_2 = reel_rotation[reel_to_slow] - temp_float_1
							IF temp_float_2 < 10.0
								reel_speed[reel_to_slow] = 0.0
								temp_float_1 = reel_rotation[reel_to_slow] / 20.0
								temp_integer_1 =# temp_float_1
								reel_rotation[reel_to_slow] =# temp_integer_1
								reel_rotation[reel_to_slow] *= 20.0
								GENERATE_RANDOM_INT_IN_RANGE 750 1000 temp_integer_1
								timer = game_timer + temp_integer_1
								IF reel_to_slow < 2
									load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
									if has_mission_audio_loaded	4
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_WHEEL_STOP 
									endif
									load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
									if has_mission_audio_loaded	4
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_WHEEL_START 
									endif
									++ reel_to_slow
								ELSE
									load_MISSION_AUDIO 4 SOUND_BANK_BANDIT
									if has_mission_audio_loaded	4
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_WHEEL_STOP 
									endif
									++ flag
								ENDIF
							ELSE
								reel_speed[reel_to_slow] = 0.6
							ENDIF
						ENDIF
					ENDIF

					GET_OBJECT_HEADING slot_machine	heading
					an = 0
					WHILE an < 3
						IF reel_speed[an] > 0.0
							reel_rotation[an] +=@ reel_speed[an]
							IF reel_rotation[an] > 360.0
							OR reel_rotation[an] = 360.0
								reel_rotation[an] -= 360.0
							ENDIF
							SET_OBJECT_ROTATION reel[an] reel_rotation[an] 0.0 heading
						ENDIF
						++ an
					ENDWHILE

					gosub draw_reel_sprites
					
				BREAK

				CASE 6
					temp_float_1 = reel_rotation[0] / 20.0
					temp_integer_1 =# temp_float_1
					temp_float_1 = reel_rotation[1] / 20.0
					temp_integer_2 =# temp_float_1
					temp_float_1 = reel_rotation[2] / 20.0
					temp_integer_3 =# temp_float_1
					IF reel1_winnings[temp_integer_1] = reel2_winnings[temp_integer_2]
					AND reel1_winnings[temp_integer_1] = reel3_winnings[temp_integer_3]
						temp_integer_4 = slot_cost * reel1_winnings[temp_integer_1]
						start_new_script display_win_text temp_integer_4
						ADD_SCORE player1 temp_integer_4
						LOAD_MISSION_AUDIO 4 SOUND_BANK_BANDIT
						IF HAS_MISSION_AUDIO_LOADED	4
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_BANDIT_PAYOUT 
						ENDIF
						increment_int_stat MONEY_WON_GAMBLING temp_integer_4
						REGISTER_INT_STAT BIGGEST_GAMBLING_WIN temp_integer_4
					ELSE
						start_new_script display_win_text 0	2500
						REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS slot_cost
					ENDIF
					set_player_control player1 on
					SET_MINIGAME_IN_PROGRESS false
					flag = 1

					gosub draw_reel_sprites
					
				BREAK
				
				ENDSWITCH

			else
				goto do_slot_machine_cleanup
			endif
		ELSE
			goto do_slot_machine_cleanup
		ENDIF
	ELSE
		goto do_slot_machine_cleanup
	ENDIF

GOTO slot_machine_loop



draw_reel_sprites:

if loaded_tex_ld_slot = 1
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_WINDOW reel_sprite_x[1] reel_sprite_y[1] reel_sprite_x[2] reel_sprite_y[2] DUMMY SWIRLS_NONE

	an = 0
	while an < 3
		temp_float_1 = reel_rotation[an] / 20.0
		temp_integer_1 =# temp_float_1
		
		if an = 0
			temp_integer_2 = reel1_winnings[temp_integer_1]
			x_temp = reel_sprite_x[0]
		endif
		if an = 1
			temp_integer_2 = reel2_winnings[temp_integer_1]
			x_temp = reel_sprite_x[0] + 64.0
		endif
		if an = 2
			temp_integer_2 = reel3_winnings[temp_integer_1]
			x_temp = reel_sprite_x[0] + 128.0
		endif
		
		
		if temp_integer_2 = cherry
		    SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	1 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		if temp_integer_2 = grape
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	2 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		if temp_integer_2 = r_69
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	3 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		if temp_integer_2 = bell
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	4 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		if temp_integer_2 = bar1_o
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	5 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		if temp_integer_2 = bar2_o
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE	6 x_temp reel_sprite_y[0] 64.0 64.0 200 200 200 255
		endif
		++ an
	endwhile
	
endif
return


do_slot_machine_cleanup:

//CLEAR_THIS_PRINT SLOT_02  //~t~ Use Slot Machine $~1~

IF flag > 0
	IF flag > 1
		CLEAR_HELP
		SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
	ENDIF
	IF flag > 2
		remove_animation casino
	ENDIF
	DELETE_OBJECT reel[0]
	DELETE_OBJECT reel[1]
	DELETE_OBJECT reel[2]
	flag = 0
ENDIF
if loaded_tex_ld_slot = 1
	remove_TEXTURE_DICTIONARY
	loaded_tex_ld_slot = 0
endif
TERMINATE_THIS_SCRIPT

return
}


MISSION_END
