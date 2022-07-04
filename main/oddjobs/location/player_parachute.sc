MISSION_START

{
//	player_parachute_script:
SCRIPT_NAME PLCHUTE

//VAR_INT fake_code_flag
LVAR_INT code_flag
LVAR_FLOAT para_yaw para_roll para_pitch para_vel para_vel_start para_vel_startZ 
LVAR_FLOAT player_height 
VAR_FLOAT para_max_Vz
LVAR_FLOAT para_Voldx para_Voldy

LVAR_INT para_v1 para_v2 para_v3 para_v4 para_Seq para_fall_anim parachute_pack paraC 
LVAR_INT para_time_check para_time_start para_col
LVAR_FLOAT para_f1 para_f2 para_f3

LVAR_FLOAT sin_angle para_pitch_step para_roll_step


//global parameters - can be adjusted by external scripts so long as reset at end of mission.

//VAR_INT player_has_parachute player_landed
//VAR_FLOAT para_float_Vy para_float_Vz para_flare_Vy para_flare_Vz para_freefall_Vz para_freefall_Vy para_default_Vy
//VAR_INT para_pickup_flag para_control_off
//
//para_float_Vy = 5.0
//para_float_Vz = -5.0
//para_flare_Vy =	8.5
//para_flare_Vz =	-1.5
//para_freefall_Vz = -30.0
//para_freefall_Vy = 15.0
//para_default_Vy = 0.0

//VIEW_INTEGER_VARIABLE player_fall_state player_fall_state



//VIEW_INTEGER_VARIABLE player_landed player_landed
//VIEW_INTEGER_VARIABLE code_flag code_flag
//VIEW_FLOAT_VARIABLE para_Vx para_Vx
//VIEW_FLOAT_VARIABLE para_Vy para_Vy
//VIEW_FLOAT_VARIABLE para_pitch para_pitch
//VIEW_FLOAT_VARIABLE para_max_vz para_max_vz


//GOSUB parachute_start


//REQUEST_ANIMATION PARACHUTE
//REQUEST_MODEL PARACHUTE
//REQUEST_MODEL para_pack
//REQUEST_MODEL pikupparachute
REQUEST_MODEL gun_para


//OR NOT HAS_MODEL_LOADED PARACHUTE
// OR NOT HAS_MODEL_LOADED para_pack
// NOT HAS_ANIMATION_LOADED PARACHUTE
//
WHILE NOT HAS_MODEL_LOADED gun_para
//OR NOT HAS_MODEL_LOADED gun_para
	WAIT 0
ENDWHILE

VAR_INT para_pickup  

IF NOT IS_CHAR_DEAD scplayer
//	SET_CHAR_COORDINATES scplayer 1794.9514 -1308.5892 133.8128
//	CREATE_PICKUP Gun_para  PICKUP_ONCE 1797.6023 -1308.8815 133.8128 para_pickup
ENDIF

//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
//

//ENDIF


player_landed = 0
player_fall_state = 0
player_has_parachute = 0




IF player_landed = 999
	 CREATE_PICKUP Gun_para  PICKUP_ONCE player_x player_y player_z para_pickup
ENDIF


   
jump_loop:

// ************************ Add global variable for scripter to set to prevent player opening chute, and reset. ***************************************

	WAIT 0

//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_T
//		max_decel = 0.0
//		TASK_PAUSE scplayer 1
//		player_fall_state = 0
//		max_decel = 0.0
//	ENDIF

//	VIEW_INTEGER_VARIABLE player_fall_State player_fall_state
//	fake_code_flag = code_flag
//	VIEW_INTEGER_VARIABLE fake_code_flag fake_code_flag

		 


	IF NOT IS_CHAR_DEAD scplayer
		IF NOT IS_2PLAYER_GAME_GOING_ON
			IF NOT player_fall_state = 0
				SET_PLAYER_CYCLE_WEAPON_BUTTON Player1 FALSE
			ENDIF
		



	//		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
	//			IF blah = 0
	//				blah = 1			
	//			ENDIF
	//		ELSE
	//			IF blah = 1
	//				blah = 2
	//					player_fall_state = 1
	//					code_flag = 0
	//			ENDIF
	//		ENDIF
			IF player_has_parachute > 0
				IF NOT HAS_CHAR_GOT_WEAPON scplayer	WEAPONTYPE_PARACHUTE
					GOSUB parachute_cleanup
				ENDIF
			ENDIF

			IF player_has_parachute = 0
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
					REQUEST_MODEL PARACHUTE
	//				REQUEST_ANIMATION PARACHUTE
	//				REQUEST_MODEL para_pack
	//				REQUEST_MODEL gun_para
					player_has_parachute = 1
					player_landed = 0
				ENDIF
			ENDIF						 

			IF player_has_parachute = 1

	//			IF HAS_MODEL_LOADED para_pack
				IF HAS_MODEL_LOADED PARACHUTE
	//			AND HAS_ANIMATION_LOADED PARACHUTE
	//			AND HAS_MODEL_LOADED gun_para
					CREATE_OBJECT PARACHUTE player_x player_y player_z ParaC
	//				CREATE_OBJECT para_pack 0.0 0.0 0.0 parachute_pack
	//				TASK_PICK_UP_OBJECT scplayer parachute_pack -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0
					ATTACH_OBJECT_TO_CHAR parac scplayer 0.0 0.0 0.0 0.0 0.0 0.0
	//				SET_OBJECT_ROTATION parachute_pack 90.0 90.0 90.0
					SET_OBJECT_VISIBLE ParaC FALSE
	//				SET_OBJECT_VISIBLE parachute_pack FALSE 					
					player_has_parachute = 2
				ENDIF
			ENDIF 

	//		IF player_has_parachute = 2
	//		AND player_fall_state = 0
	//			IF DOES_OBJECT_EXIST parachute_pack
	//				IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE
	//					SET_OBJECT_VISIBLE parachute_pack TRUE
	//				ELSE
	//					SET_OBJECT_VISIBLE parachute_pack FALSE
	//				ENDIF					
	//			ENDIF
	//		ENDIF

			// make parachute pack appear if player goes in to freefall mode without the parachute selected.
			IF player_has_parachute = 2
			AND player_fall_state = 1
				player_has_parachute = 3
	//			IF DOES_OBJECT_EXIST parachute_pack
	//				SET_OBJECT_VISIBLE parachute_pack TRUE
	//			ENDIF
			ENDIF




			// player is on ground
			IF player_fall_state = 0
			AND player_has_parachute > 0
				IF IS_CHAR_IN_AIR scplayer
					GET_CHAR_VELOCITY scplayer para_Vx para_Vy para_Vz
					IF para_Vz < -10.0
						GET_CHAR_HEIGHT_ABOVE_GROUND scplayer player_height
						IF player_height > 20.0
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_FREEFALL_START
							player_fall_state = 1
							SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE							
							code_flag = 0
							para_max_Vz = 0.0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			//player entering freefall
			IF player_fall_state = 1
				IF code_flag = 0
					code_flag = 2
				ENDIF

				IF code_flag = 2
					LVAR_INT task_state
					GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM_NON_INTERRUPTABLE task_state
					IF task_State = FINISHED_TASK
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PED 1.0 1 0 0 0 -1
						REQUEST_ANIMATION PARACHUTE
						para_fall_anim = 1
						GET_CHAR_HEADING scplayer para_yaw
						code_flag = 3
					ENDIF
				ENDIF

				IF code_flag = 3

					GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM_NON_INTERRUPTABLE task_state	
					IF task_State = FINISHED_TASK
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PED 1.0 1 0 0 0 -1
					ENDIF


					GET_CHAR_HEIGHT_ABOVE_GROUND scplayer player_height
					IF player_height < 100.0
					AND player_height > 60.0
						IF HAS_ANIMATION_LOADED PARACHUTE
							IF flag_player_on_mission = 0
								PRINT_NOW PARA_01 1000 1
							ENDIF
						ENDIF
					ENDIF
					GET_POSITION_OF_ANALOGUE_STICKS PAD1 para_v1 para_v2 para_v3 para_v3
					IF para_control_off = 1
						para_v1 = 0
						para_v2	= 0
					ENDIF
					para_f1 =# para_v1

					// to get 30 deegrees roll either way (+- 30 deg) as a maximum roll - ie 256/4.267 = 60
					para_f1 /= 4.267
					para_f1 -= para_roll
					para_f1 /= 20.0
					para_roll += para_f1

					para_f1 = para_roll / 5.0 //was 15.0
					para_yaw -= para_f1

					IF para_yaw > 180.0
						para_yaw -= 360.0
					ENDIF

					IF para_yaw < -180.0
						para_yaw += 360.0
					ENDIF

					para_f2 =# para_v2
					para_f2 /= 4.267
					para_f2 -= para_pitch
					para_f2 /= 20.0
					para_pitch += para_f2


	  				GET_CHAR_VELOCITY scplayer para_Voldx para_Voldy para_Vz
					IF para_Vz < para_freefall_Vz
						para_Vz = para_freefall_Vz
					ENDIF
					IF para_Vz < para_max_Vz
						para_max_Vz = para_Vz
					ENDIF
					IF para_freefall_Vz > para_max_Vz
						para_max_Vz = para_freefall_Vz
					ENDIF
					IF para_Vz > para_max_Vz
					AND NOT player_fall_safe = 1
						IF NOT IS_CHAR_IN_WATER scplayer
							IF para_max_Vz < -20.0
								player_fall_state = 2					
							ELSE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PED 1.0 1 0 0 0 100
								player_fall_state = 7 //player doesn't lose chute
							ENDIF	
						ELSE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PED 1.0 1 0 0 0 100
							player_fall_state = 7 //player doesn't lose chute						
						ENDIF
	//					VAR_FLOAT max_Decel this_decel
	//					VIEW_FLOAT_VARIABLE max_decel max_decel
	//					this_decel = para_max_Vz - para_Vz
	//					IF this_decel < max_decel
	//						max_decel = this_decel
	//					ENDIF
	//					IF max_decel = 0.0
	//						max_decel = para_Vz					
	//					ENDIF
							
	//					para_max_Vz = para_Vz
					ENDIF

					para_vel = para_pitch / 30.0
					para_vel *= para_freefall_Vy

					SIN para_yaw para_Vx 
					COS para_yaw para_Vy

					para_Vx *= para_vel
					para_Vy *= para_vel
					para_Vy *= -1.0
					para_Vy += para_default_Vy

				
					// this bit acts as a kind of momentum
					para_f1 = para_Voldx - para_Vx
					para_f1 *= 0.01
					para_Vx = para_Voldx - para_f1

					para_f1 = para_Voldy - para_Vy
					para_f1 *= 0.01
					para_Vy = para_Voldy - para_f1

					



					// SET ANIM TO PLAY DURING FREEFALL

					para_v3 = para_v1
					para_v4 = para_v2

					ABS para_v3
					ABS para_v4
		
					IF para_v3 > 40
					OR para_v4 > 40

						IF para_v3 > para_v4
							
							IF para_v1 >= 0
								IF NOT para_fall_anim = 2
									IF HAS_ANIMATION_LOADED	PARACHUTE
										TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive_R PARACHUTE 1.0 1 0 0 1 -2
									ENDIF
									para_fall_anim = 2
								ENDIF
							ENDIF
							IF para_v1 < 0
								IF NOT para_fall_anim = 3
									IF HAS_ANIMATION_LOADED	PARACHUTE
										TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive_L PARACHUTE 1.0 1 0 0 1 -2
									ENDIF
									para_fall_anim = 3
								ENDIF
							ENDIF

						ELSE
							IF para_v2 >= 0
								IF NOT para_fall_anim = 4
									IF HAS_ANIMATION_LOADED	PARACHUTE
										TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PARACHUTE 1.0 1 0 0 1 -2
									ENDIF
									para_fall_anim = 4
								ENDIF
							ENDIF
							IF para_v2 < 0
								IF NOT para_fall_anim = 5
									IF HAS_ANIMATION_LOADED	PARACHUTE
										TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive_Accel PARACHUTE 1.0 1 0 0 1 -2
									ENDIF
									para_fall_anim = 5
								ENDIF
							ENDIF

						ENDIF
					ELSE
						IF NOT para_fall_anim = 1
							IF HAS_ANIMATION_LOADED PARACHUTE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FALL_skydive PARACHUTE 1.0 1 0 0 1 -2
								para_fall_anim = 1						
							ENDIF
						ENDIF	
					ENDIF
					
					IF IS_BUTTON_PRESSED PAD1 CIRCLE
					OR para_force_chute_open = 1
						IF NOT player_fall_safe = 1
							IF HAS_ANIMATION_LOADED	PARACHUTE
								IF player_has_parachute = 3
								AND para_control_off = 0
									TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_open PARACHUTE 8.0 0 0 0 1 -2				
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_FREEFALL_STOP
									VAR_INT play_open_timer
									play_open_timer = TIMERA + 1100
									
									player_fall_state = 3
									code_flag = 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF								
			ENDIF

			IF player_fall_state = 2  //die
				IF HAS_ANIMATION_LOADED PARACHUTE
					SET_CHAR_HEADING scplayer para_yaw
					TASK_DIE_NAMED_ANIM scplayer FALL_skydive_die PARACHUTE 1000.0 0		
					REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_PED_DEATH_CRUNCH
				ELSE
					TASK_DIE scplayer
				ENDIF


				GOSUB parachute_cleanup

			ENDIF

			IF player_fall_state = 3 //parachuting

				IF play_open_timer > 0
					IF TIMERA > play_open_timer
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PARACHUTE_OPEN
						play_open_timer = 0
					ENDIF
				ENDIF


				IF code_flag = 0
					//make player angle revert to 0
					para_pitch_step = para_pitch / 500.0
					para_roll_step = para_roll / 500.0
					para_time_check = TIMERA
					para_time_start = TIMERA
					para_vel_start = para_Vel * -1.0
					para_vel_startZ = para_Vz
					code_flag = 1
				ENDIF
				IF code_flag = 1
					para_v1 = TIMERA - para_time_start
					IF para_v1 < 500
						para_v1 = TIMERA - para_time_check
						para_time_check = TIMERA
						para_f1 =# para_v1
						para_f2 = para_pitch_step * para_f1
						para_f3 = para_roll_step * para_f1
						para_pitch -= para_f2
						para_roll -= para_f3
					ELSE
						para_roll = 0.0
						para_pitch = 0.0
						code_flag = 2
					ENDIF
				ENDIF
				IF code_flag = 2
					IF DOES_OBJECT_EXIST parac
						SET_OBJECT_VISIBLE parac TRUE
						SET_OBJECT_SCALE parac 0.0
						para_time_start = TIMERA
						WAIT 0
						PLAY_OBJECT_ANIM parac para_open_o PARACHUTE 1000.0 0 1
						code_flag = 3
					ENDIF
				ENDIF
				IF code_flag = 3
					// Scale the parachute from 0.0 to 100.0 percent size over the course of 1/2 second
					para_v1 = TIMERA - para_time_start
					IF para_v1 < 500
						para_f1 =# para_v1
						para_f1 /= 500.0
						SET_OBJECT_SCALE parac para_f1
					ELSE
						SET_OBJECT_SCALE parac 1.0
						code_flag = 4
					ENDIF
				ENDIF
				IF code_flag = 5
					CREATE_OBJECT para_collision 0.0 0.0 0.0 para_col
					SET_OBJECT_VISIBLE para_col FALSE
					SET_OBJECT_DYNAMIC para_col TRUE
					SET_OBJECT_RECORDS_COLLISIONS para_col TRUE
					code_flag = 6
				ENDIF

				IF code_flag = 6

	//				
	//				IF player_height < 2.0
	//				GET_CHAR_VELOCITY scplayer para_f1 para_f1 para_f3
	//
	//				ABS para_f3
	//
	//				IF para_f3 < 0.1
	//					player_fall_state = 4
	//					code_flag = 0
	//				ENDIF


					GET_POSITION_OF_ANALOGUE_STICKS PAD1 para_v1 para_v2 para_v3 para_v3
					IF para_control_off = 1
						para_v1 = 0
						para_v2	= 0
					ENDIF
					para_f1 =# para_v1

					// to get 30 deegrees roll either way (+- 30 deg) as a maximum roll - ie 256/4.267 = 60
					para_f1 /= 4.267
					para_f1 -= para_roll
					para_f1 /= 20.0
					para_roll += para_f1

					para_f1 = para_roll / 15.0
					para_yaw -= para_f1

					IF para_yaw > 180.0
						para_yaw -= 360.0
					ENDIF

					IF para_yaw < -180.0
						para_yaw += 360.0
					ENDIF
					 

			   

					SIN para_yaw para_Vx 
					COS para_yaw para_Vy

					para_Vx *= para_float_Vy
					para_Vy *= para_float_Vy
					para_Vx *= -1.0
					
	//				para_Vz = para_float_Vz


					// SET ANIM TO PLAY DURING FREEFALL

					para_v3 = para_v1
					para_v4 = para_v2

					ABS para_v3
					ABS para_v4
		
					IF para_v3 > 40
					OR para_v4 > 40

						IF para_v3 > para_v4
							para_f1 = para_float_Vz - para_Vz
							para_f1 /= 20.0
							para_Vz += para_f1
							
							IF para_v1 >= 0
								IF NOT para_fall_anim = 2
									TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_steerR PARACHUTE 1.0 1 0 0 1 -2		 
									PLAY_OBJECT_ANIM parac para_steerR_o PARACHUTE 1.0 1 1
									para_fall_anim = 2																	 
								ENDIF
							ENDIF
							IF para_v1 < 0
								IF NOT para_fall_anim = 3
									TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_steerL PARACHUTE 1.0 1 0 0 1 -2
									PLAY_OBJECT_ANIM parac para_steerL_o PARACHUTE 1.0 1 1
									para_fall_anim = 3
								ENDIF																					 
							ENDIF																						 

						ELSE
							IF para_v2 >= 0
								para_f1 = para_flare_Vz - para_Vz
								para_f1 /= 20.0
								para_Vz += para_f1

								IF NOT para_fall_anim = 4								
									TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_decel PARACHUTE 1.0 1 0 0 1 -2		 
									PLAY_OBJECT_ANIM parac para_decel_o PARACHUTE 1.0 1 1
									para_fall_anim = 4																	 
								ENDIF
							ENDIF
							IF para_v2 < 0
								para_f1 = para_float_Vz - para_Vz
								para_f1 /= 20.0
								para_Vz += para_f1
								IF NOT para_fall_anim = 5
									TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_float PARACHUTE 1.0 1 0 0 1 -2		 
									PLAY_OBJECT_ANIM parac para_float_o PARACHUTE 1.0 1 1
									para_fall_anim = 5
								ENDIF
							ENDIF

						ENDIF
					ELSE
						para_f1 = para_float_Vz - para_Vz
						para_f1 /= 20.0
						para_Vz += para_f1
						IF NOT para_fall_anim = 5
							IF NOT para_fall_anim = 1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PARA_float PARACHUTE 1.0 1 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_float_o PARACHUTE 1.0 1 1
								para_fall_anim = 1						
							ENDIF	
						ENDIF
					ENDIF

					GET_OBJECT_COORDINATES parac x y z
					SET_OBJECT_COORDINATES_AND_VELOCITY para_col x y z

					IF HAS_OBJECT_COLLIDED_WITH_ANYTHING para_col
						PLAY_OBJECT_ANIM parac para_rip_loop_o PARACHUTE 8.0 1 1
						code_flag = 7
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 TRIANGLE
						CLEAR_CHAR_TASKS scplayer
						player_fall_state = 6
						player_landed = 3
					ENDIF

				ENDIF





					// slow player down as parachute opens - speed during freefall adjust to speeds with parachute open
				IF DOES_OBJECT_EXIST parac
					IF IS_OBJECT_PLAYING_ANIM parac para_open_o
						GET_OBJECT_ANIM_CURRENT_TIME parac para_open_o para_f1
						para_f2 = para_vel_startZ - para_float_Vz 
						para_f3 = para_f2 * para_f1
						para_Vz = para_vel_startZ - para_f3
					
						para_f2 = para_vel_start - para_float_Vy
						para_f3 = para_f2 * para_f1										
						para_vel = para_vel_start - para_f3
						
						SIN para_yaw para_Vx 
						COS para_yaw para_Vy

						para_Vx *= para_vel
						para_Vy *= para_vel
						para_Vx *= -1.0  

						IF para_f1 = 1.0
						AND code_flag = 4
							code_flag = 5
						ENDIF							
		//			player_fall_state = 0
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					IF IS_CHAR_IN_WATER scplayer
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer para_land_water PARACHUTE 8.0 1 1 0 0 1000
						PLAY_OBJECT_ANIM parac para_land_water_o PARACHUTE 1000.0 0 1
						player_fall_state = 5
						code_flag = 0					
					ENDIF

					GET_CHAR_VELOCITY scplayer para_f1 para_f1 para_f3
			
					IF para_f3 > -0.1
						player_fall_state = 4
						code_flag = 0
					ENDIF
				ENDIF


			ENDIF

			IF player_fall_state = 4
				IF code_flag = 0
					player_landed = 1
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
					player_z -= 1.0
					SET_CHAR_COORDINATES scplayer player_x player_y player_z
					SET_CHAR_ROTATION scplayer 0.0 0.0 para_yaw
					IF para_Vz < -10.0
						player_fall_state = 2
						code_flag = 0	
					ELSE 
						IF para_Vz < -4.0
							OPEN_SEQUENCE_TASK para_seq
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 fall_front PED 20.0 0 0 0 1 700
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 getup_front PED 8.0 0 1 0 0 -2							 
							CLOSE_SEQUENCE_TASK para_seq
							PERFORM_SEQUENCE_TASK scplayer para_seq
							CLEAR_SEQUENCE_TASK para_seq					
						ELSE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer run_player PED 8.0 1 1 0 0 1000
						ENDIF
						code_flag = 1
					ENDIF

					PLAY_OBJECT_ANIM parac para_land_o PARACHUTE 1000.0 0 1
					DETACH_OBJECT parac 0.0 0.0 0.0 FALSE
					para_time_check = TIMERA + 1000
					
				ENDIF
				IF code_flag = 1				
					IF IS_OBJECT_PLAYING_ANIM parac para_land_o

						GET_OBJECT_ANIM_CURRENT_TIME parac para_land_o para_f1
						IF para_f1 = 1.0 
	//				IF TIMERA > para_time_check
							player_landed = 2

							GOSUB parachute_cleanup
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF player_fall_state = 5
				player_landed = 1
				IF code_flag = 0
//					IF NOT IS_OBJECT_PLAYING_ANIM parac para_land_water_o
//						GET_OBJECT_ANIM_CURRENT_TIME parac para_land_water_o para_f1
//						IF para_f1 = 1.0 
	//				IF TIMERA > para_time_check
							player_landed = 2
							GOSUB parachute_cleanup
//						ENDIF
//					ENDIF				
				ENDIF			
			ENDIF

			IF player_fall_state > 0
			AND player_fall_state < 4
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_VELOCITY scplayer para_Vx para_Vy para_Vz			
					SET_CHAR_HEADING scplayer para_yaw
					SET_CHAR_ROTATION scplayer para_pitch para_roll para_yaw

	 			ENDIF
			ENDIF

			IF player_fall_state = 6
				GOSUB parachute_cleanup					
			ENDIF

			IF player_fall_state = 7
				GOSUB parachute_cleanup_keep_chute					
			ENDIF
		ENDIF
	ENDIF

  
GOTO jump_loop

parachute_cleanup_keep_chute:

	player_fall_state = 0
	code_flag = 0
	MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
	REMOVE_ANIMATION PARACHUTE
	SET_CHAR_ROTATION scplayer 0.0 0.0 para_yaw
	SET_PLAYER_CYCLE_WEAPON_BUTTON Player1 TRUE

RETURN
	   
parachute_cleanup:


						DETACH_OBJECT parac 0.0 0.0 0.0 FALSE

						REMOVE_OBJECT_ELEGANTLY parac

						DELETE_OBJECT para_col

						REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PARACHUTE

						player_fall_state = 0
						player_has_parachute = 0
						code_flag = 0

						MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
						REMOVE_ANIMATION PARACHUTE
						MARK_MODEL_AS_NO_LONGER_NEEDED gun_para
						SET_CHAR_ROTATION scplayer 0.0 0.0 para_yaw
						SET_PLAYER_CYCLE_WEAPON_BUTTON Player1 TRUE



MISSION_END


//	RETURN

}


