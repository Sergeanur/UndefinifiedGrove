MISSION_START
{

// Coke Courier

coke_courier_script:
SCRIPT_NAME COKEC

	LVAR_INT m_stage
	LVAR_INT m_goals

	LVAR_INT temp_int temp_int2 temp_int3
	LVAR_FLOAT temp_float temp_float2 temp_float3
	LVAR_INT temp_seq	

	//LVAR_FLOAT x2 y2 z2
	//LVAR_FLOAT playback_speed

	LVAR_INT last_frame_time
	LVAR_INT lost_timer
	LVAR_INT coke_timer

	LVAR_INT mobile_phone_conv
	LVAR_INT m_passed
	LVAR_INT mission_started_properly

	LVAR_INT route_mode

	VAR_INT phone_conversation_choice
	VAR_INT phone_conversation_answer

	LVAR_INT hide_text

	route_mode = 0   // 0 - uses route finding, 1 uses car recording

	m_stage 	= 0
	m_goals 	= 0
	mobile_phone_conv = 0
	m_passed = 0
	mission_started_properly = 0
	hide_text = 0


	phone_conversation_choice =	0
	phone_conversation_answer =	0


	coke_courier_script_start:
	// if this script has been triggered on the wrong day then quit out.
	IF weekday = 1
	OR weekday = 3
	OR weekday = 5
		GOTO bcesar3_cleanup
		hide_text = 1
	ENDIF

	// If Big Smoke's Cash has not been played then quit out.
	IF bce2_played = 0
		GOTO bcesar3_cleanup
		hide_text = 1
	ENDIF

	// if the crack factory has been destroyed, this mission shouldn't be running
	IF flag_Synd_mission_counter >= 10 
		GOTO bcesar3_cleanup
		hide_text = 1
	ENDIF

	GET_AREA_VISIBLE current_visible_area_cell

	IF IS_PLAYER_PLAYING player1
		IF current_visible_area_cell = 0
			IF IS_CHAR_ON_FOOT scplayer
			AND NOT IS_CHAR_IN_AIR scplayer
			AND flag_player_on_mission = 0
			AND player_fall_state = 0
				IF NOT IS_CHAR_IN_WATER scplayer
				AND NOT IS_CHAR_SHOOTING scplayer
				//AND NOT IS_GANG_WAR_GOING_ON
				
				ELSE	
					WAIT 0
					GOTO coke_courier_script_start
				ENDIF
			ELSE
				WAIT 0
				GOTO coke_courier_script_start
			ENDIF
		ELSE
			WAIT 0
			GOTO coke_courier_script_start
		ENDIF
	ENDIF

	GOSUB check_player_is_safe_for_mobile
	IF player_is_completely_safe_for_mobile = 0
		WAIT 0
		GOTO coke_courier_script_start
	ENDIF

	IF bcesar3_passed_mission = 0
		REGISTER_MISSION_GIVEN
	ENDIF
	mission_started_properly = 1
	flag_on_courier_mission = 1


mission_loop_BCESAR3:
WAIT 0	
	

	// update timers
	GET_GAME_TIMER temp_int2
	temp_int = temp_int2 - last_frame_time
	last_frame_time = temp_int2


	// only update the lost timer when the player is NOT in an interior
	GET_AREA_VISIBLE vis_area
	//WRITE_DEBUG_WITH_INT area_visible vis_area
	IF vis_area = 0
		lost_timer += temp_int
		coke_timer += temp_int
	ENDIF 

		


//	IF debug_on = 1
//		GOSUB bce3_display_path
//		GOTO mission_loop_BCESAR3
//	ENDIF

//	// Display mission stage variables for debug
//	LVAR_INT debug_on	
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_X
//		IF debug_on = 0
//			debug_on = 1
//			WRITE_DEBUG LEVEL_DEBUG_ON
//		ELSE
//			debug_on = 0
//			WRITE_DEBUG LEVEL_DEBUG_OFF
//		ENDIF
//	ENDIF
//
//	IF debug_on = 1
//		LVAR_INT display_debug
//		VAR_INT BCESAR3_view_debug[8]
//		VAR_FLOAT BCESAR3_view_debug_f[8]
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
//			display_debug++
//			IF display_debug > 1
//				display_debug = 0
//			ENDIF
//			CLEAR_ALL_VIEW_VARIABLES
//		ENDIF
//		IF display_debug = 1
//			BCESAR3_view_debug[0] = m_stage
//			BCESAR3_view_debug[1] = m_goals
//			BCESAR3_view_debug[2] = caught_up_with_courier
//			BCESAR3_view_debug[3] = TIMERB
//			BCESAR3_view_debug[4] =	checkpoint_count
//			BCESAR3_view_debug_f[5] = playback_speed
//			VIEW_INTEGER_VARIABLE BCESAR3_view_debug[0] m_stage
//			VIEW_INTEGER_VARIABLE BCESAR3_view_debug[1] m_goals
//			VIEW_INTEGER_VARIABLE BCESAR3_view_debug[2] caught_up_with_courier
//			VIEW_INTEGER_VARIABLE BCESAR3_view_debug[3] TIMERB
//			VIEW_INTEGER_VARIABLE BCESAR3_view_debug[4] checkpoint_count
//			VIEW_FLOAT_VARIABLE BCESAR3_view_debug_f[5] playback_speed
//		ENDIF
//	ENDIF


	// =========== EXIT CONDITIONS =================
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC
		GOTO bcesar3_cleanup
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		GOTO bcesar3_cleanup
	ENDIF
	IF flag_player_on_mission = 1	
		hide_text = 1
		GOTO bcesar3_cleanup
	ENDIF
	IF NOT IS_PLAYER_PLAYING player1
		GOTO bcesar3_cleanup
	ENDIF
	IF HAS_DEATHARREST_BEEN_EXECUTED
		hide_text = 1
		GOTO bcesar3_cleanup
	ENDIF


//	// if the day has changed AND the player hasn't caught up with the courier quit out
//	IF caught_up_with_courier = 0
//		IF weekday = 1
//		OR weekday = 3
//		OR weekday = 5
//			GOTO bcesar3_cleanup
//		ENDIF
//	ENDIF

	
	// *************************************************************************************************************
	//									INITIALISATION
	// *************************************************************************************************************
		IF m_stage = 0
			
		// === Declare mission specific variables ===
			// ENTITIES	(cars, peds, objects, blips, decision makers, attractors)
			LVAR_INT coke_bike coke_courier
			LVAR_INT coke_blip
			LVAR_INT fx
			LVAR_INT coke_pickup
			LVAR_INT coke_bag

			// FLAGS - ints that need to be reset each time level is played
			LVAR_INT coke_damaged
			//VAR_INT bce3_played
			LVAR_INT caught_up_with_courier
			LVAR_INT viewing_cam
			LVAR_INT checkpoint_count

			
			LVAR_INT vis_area
			LVAR_INT freeze_dude
			
			// decision maker
			LVAR_INT dm

			// set flags / coords
			coke_damaged = -1
			coke_pickup = 0
			caught_up_with_courier = 0
			viewing_cam = 0
			checkpoint_count = 0
			freeze_dude = 0

			//LOAD_MISSION_TEXT BCESAR3
			//SET_PED_DENSITY_MULTIPLIER 1.0
			//SET_CAR_DENSITY_MULTIPLIER 0.3
			//SET_TIME_OF_DAY 23 1

			m_stage++
			m_goals = 0
		ENDIF

	// *************************************************************************************************************
	//									STAGE 1 - Set up start point 
	// *************************************************************************************************************
		IF m_stage = 1
			
			// load the coke courier models
			IF m_goals = 0
				REQUEST_MODEL wmybmx
				REQUEST_MODEL SANCHEZ
				REQUEST_MODEL COLT45
				REQUEST_MODEL CELLPHONE
				REQUEST_CAR_RECORDING 556	
				LOAD_MISSION_AUDIO 3 SOUND_PED_MOBRING
				WHILE NOT HAS_MODEL_LOADED wmybmx
				   OR NOT HAS_MODEL_LOADED SANCHEZ
				   OR NOT HAS_MODEL_LOADED COLT45
				   OR NOT HAS_MODEL_LOADED CELLPHONE
				   OR NOT HAS_CAR_RECORDING_BEEN_LOADED 556
				   OR NOT HAS_MISSION_AUDIO_LOADED 3
					WAIT 0
				ENDWHILE
				LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm
				m_goals++
			ENDIF

			// start phone conversation
			IF m_goals = 1
//				GENERATE_RANDOM_INT_IN_RANGE 1 50 temp_int
//				IF IS_PLAYER_PLAYING player1
//					GET_AREA_VISIBLE current_visible_area_cell
//					IF current_visible_area_cell = 0
//						IF IS_PLAYER_PLAYING player1
//							IF IS_CHAR_ON_FOOT scplayer
//							AND NOT IS_CHAR_IN_AIR scplayer
//							AND flag_player_on_mission = 0
//							AND player_fall_state = 0
//								IF NOT IS_CHAR_IN_WATER scplayer
//								AND NOT IS_CHAR_SHOOTING scplayer
//								//AND NOT IS_GANG_WAR_GOING_ON
//									TASK_USE_MOBILE_PHONE scplayer TRUE
//									START_NEW_SCRIPT cleanup_audio_lines
//									TIMERA = -2000
//									mobile_phone_conv = 0
//									m_goals++ 
//								ENDIF
//							ENDIF
//						ENDIF
//					ENDIF
//				ENDIF

				GOSUB check_player_is_safe_for_mobile
				IF player_is_completely_safe_for_mobile = 1

					PLAY_MISSION_AUDIO 3

					TASK_USE_MOBILE_PHONE scplayer TRUE
//					START_NEW_SCRIPT cleanup_audio_lines
//					CLEAR_MISSION_AUDIO 1
//					CLEAR_MISSION_AUDIO 2

					TIMERA = -1500
					mobile_phone_conv = 0
					m_goals++ 
				ENDIF

			ENDIF


			// do mobile phone conversation
			IF m_goals >= 2
				
				IF mobile_phone_conv < 5
					
					IF IS_BUTTON_PRESSED PAD1 TRIANGLE
						mobile_phone_conv = 4
						phone_conversation_choice = 3
						phone_conversation_answer = 2
						hide_text = 1
					ENDIF

					// initialise phone choice stuff
					IF phone_conversation_choice = 1
					AND audio_line_is_active = 0
					AND NOT IS_MESSAGE_BEING_DISPLAYED
						PRINT_HELP_FOREVER TALK_1
						phone_conversation_choice++
					ENDIF

					IF phone_conversation_choice = 2

						IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
							phone_conversation_answer = 1
							phone_conversation_choice++
							CLEAR_HELP	
						ELSE
							IF IS_BUTTON_PRESSED PAD1 DPADLEFT
								phone_conversation_answer = 2
								phone_conversation_choice++	
								CLEAR_HELP
							ENDIF
						ENDIF
					ENDIF

					
					IF phone_conversation_choice = 0
					OR phone_conversation_choice = 3
						IF audio_line_is_active = 0
						AND TIMERA > 1000
							SWITCH mobile_phone_conv
								CASE 0
									CLEAR_MISSION_AUDIO 3
									$audio_string    = &MCES08A				
									audio_sound_file = SOUND_MCES08A
									START_NEW_SCRIPT audio_line -1 0 1 1 0
								BREAK
								CASE 1
									$audio_string    = &MCES08B				
									audio_sound_file = SOUND_MCES08B
									START_NEW_SCRIPT audio_line scplayer 0 1 1 0
								BREAK
								CASE 2
									$audio_string    = &MCES08C				
									audio_sound_file = SOUND_MCES08C
									START_NEW_SCRIPT audio_line -1 0 1 1 0
									phone_conversation_choice++
								BREAK
								CASE 3
									IF phone_conversation_answer = 1
										$audio_string    = &MCES08D			// positive response	
										audio_sound_file = SOUND_MCES08D
										START_NEW_SCRIPT audio_line scplayer 0 1 1 0
									ELSE
										$audio_string = &VOFFE2N			 // negative response
										audio_sound_file = SOUND_VOFFE2N
										START_NEW_SCRIPT audio_line scplayer 0 1 1 0
									ENDIF
								BREAK
								CASE 4
									START_NEW_SCRIPT cleanup_audio_lines
									CLEAR_MISSION_AUDIO 1
									CLEAR_MISSION_AUDIO 2
									IF IS_PLAYER_PLAYING player1
										TASK_USE_MOBILE_PHONE scplayer FALSE
									ENDIF
									IF NOT phone_conversation_answer = 1
										GOTO bcesar3_cleanup										
									ENDIF 
								BREAK
							ENDSWITCH
							mobile_phone_conv++
							TIMERA = 0
						ENDIF
					ENDIF

				ENDIF

			ENDIF
			
			// create the coke courier
			IF m_goals = 2
				
				// -------- route finding method ----------- //
				IF route_mode = 0

					GENERATE_RANDOM_INT_IN_RANGE 0 60 checkpoint_count
					// check spawn point isn't too near player
					WHILE temp_int = 0
						IF IS_PLAYER_PLAYING player1
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 150.0 150.0 150.0 FALSE
								GENERATE_RANDOM_INT_IN_RANGE 0 60 checkpoint_count
								WAIT 0	
							ELSE
								temp_int = 1
							ENDIF
						ENDIF
					ENDWHILE

					CREATE_CAR SANCHEZ bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] coke_bike
					CREATE_CHAR PEDTYPE_CIVMALE WMYBMX bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] coke_courier
					CREATE_OBJECT PARA_PACK bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] coke_bag				
					WARP_CHAR_INTO_CAR coke_courier coke_bike
					TASK_PICK_UP_OBJECT coke_courier coke_bag -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0
					SET_LOAD_COLLISION_FOR_CAR_FLAG coke_bike FALSE

					temp_int = checkpoint_count + 1
					x = bce2_CHECKPOINTS_x[temp_int] - bce2_CHECKPOINTS_x[checkpoint_count]
					y = bce2_CHECKPOINTS_y[temp_int] - bce2_CHECKPOINTS_y[checkpoint_count]
					GET_HEADING_FROM_VECTOR_2D x y temp_float
					SET_CAR_HEADING coke_bike temp_float
					CAR_GOTO_COORDINATES_RACING coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count]
					

				ELSE
				// -------- car recording method ------------ //

//					CREATE_CAR SANCHEZ 0.0 0.0 0.0 coke_bike
//					CREATE_CHAR PEDTYPE_CIVMALE WMYBMX 0.0 0.0 0.0 coke_courier
//					CREATE_OBJECT PARA_PACK 0.0 0.0 0.0 coke_bag				
//					WARP_CHAR_INTO_CAR coke_courier coke_bike
//					TASK_PICK_UP_OBJECT coke_courier coke_bag -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0
//					SET_LOAD_COLLISION_FOR_CAR_FLAG coke_bike FALSE
//				
//					START_PLAYBACK_RECORDED_CAR coke_bike 556
//					playback_speed = 0.6
//					SET_PLAYBACK_SPEED coke_bike playback_speed
//					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 2000.0 temp_float
//					SKIP_IN_PLAYBACK_RECORDED_CAR coke_bike temp_float
//
				ENDIF
				
				// set coke courier ai
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER coke_courier TRUE
				SET_CHAR_ACCURACY coke_courier 95
				SET_CHAR_DECISION_MAKER coke_courier dm
				TASK_TOGGLE_PED_THREAT_SCANNER coke_courier 0 0 1
				// give weapon to char - pistol
				GIVE_WEAPON_TO_CHAR coke_courier WEAPONTYPE_PISTOL 99999
				SET_CHAR_MAX_HEALTH	coke_courier 300  
				SET_CHAR_HEALTH coke_courier 300
				// set bike properties
				SET_CAR_ONLY_DAMAGED_BY_PLAYER coke_bike TRUE
				SET_CAR_CRUISE_SPEED coke_bike 25.0
				SET_CAR_DRIVING_STYLE  coke_bike DRIVINGMODE_AVOIDCARS
				ADD_STUCK_CAR_CHECK_WITH_WARP coke_bike 2.0 2000 TRUE TRUE TRUE 2

				// set stuff to be unset when player catches up
				SET_CHAR_PROOFS coke_courier TRUE TRUE TRUE TRUE TRUE
				SET_CAR_PROOFS coke_bike TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_DROWNS_IN_WATER coke_courier FALSE 
				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE coke_courier KNOCKOFFBIKE_NEVER



				MARK_MODEL_AS_NO_LONGER_NEEDED wmybmx
				MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
				MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
				
				//PRINT BCR3_01 7000 1 // Courier was last spotted in xxxx
				IF NOT IS_CHAR_DEAD coke_courier
					ADD_BLIP_FOR_CHAR coke_courier coke_blip
				ENDIF

				caught_up_with_courier = 0
				//TIMERA = 0
				TIMERB = 0
				coke_timer = 0
				lost_timer = 0
			m_goals++
			ENDIF
			

			// courier drives along route
			IF m_goals = 3
				IF IS_PLAYER_PLAYING player1

					// attach camera to courier - #### debug only ####
					IF viewing_cam = 0
						IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
							IF NOT IS_CHAR_DEAD coke_courier
								//ATTACH_CAMERA_TO_CHAR_LOOK_AT_CHAR coke_courier 2.0 -5.0 3.0 coke_courier	0.0 JUMP_CUT
								viewing_cam = 1
							ENDIF
						ENDIF
					ELSE
						IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
							RESTORE_CAMERA_JUMPCUT
							SET_CAMERA_BEHIND_PLAYER
							viewing_cam = 0
						ENDIF	
					ENDIF

					IF viewing_cam = 1
						IF NOT IS_CHAR_DEAD coke_courier
							GET_CHAR_COORDINATES coke_courier x y z
							x += -15.0
							y += 12.0
							z += 15.0
							SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
							GET_CHAR_COORDINATES coke_courier x y z
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS coke_courier 0.0 2.0 0.0 x y z
							POINT_CAMERA_AT_POINT x y z JUMP_CUT

//							IF IS_PLAYER_PLAYING player1
//								GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS coke_courier 0.0 -10.0 0.0 x y z
//								SET_CHAR_COORDINATES scplayer x y z
//							ENDIF
						ENDIF
					ENDIF


					IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
						IF NOT IS_CHAR_DEAD coke_courier
							IF NOT IS_CAR_DEAD coke_bike
								IF IS_CHAR_IN_CAR coke_courier coke_bike
									TASK_LEAVE_CAR_IMMEDIATELY coke_courier coke_bike
								ENDIF
							ENDIF
						ENDIF
					ENDIF

			
					// warp player near -- ##### debug only #####
					IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J 
						IF NOT IS_CHAR_DEAD coke_courier
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS coke_courier 0.0 -20.0 0.0 x y z
							z += 10.0
							//GET_GROUND_Z_FOR_3D_COORD x y z z
							IF IS_PLAYER_PLAYING player1
								SET_CHAR_COORDINATES scplayer x y z
							ENDIF
						ENDIF
					ENDIF

					
					// wait for player to catch up with courier
					IF route_mode = 0

						IF caught_up_with_courier = 0
							IF IS_PLAYER_PLAYING player1
								IF NOT IS_CHAR_DEAD coke_courier
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer coke_courier 80.0 80.0 80.0 FALSE
										SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE coke_courier KNOCKOFFBIKE_DEFAULT

										// set stuff back to normal
										SET_CHAR_PROOFS coke_courier FALSE FALSE FALSE FALSE FALSE
										IF NOT IS_CAR_DEAD coke_bike
											SET_CAR_PROOFS coke_bike FALSE FALSE FALSE FALSE FALSE
										ENDIF
										SET_CHAR_DROWNS_IN_WATER coke_courier TRUE 
										SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE coke_courier KNOCKOFFBIKE_DEFAULT


										//WRITE_DEBUG player_caught_up
										caught_up_with_courier = 1
									ELSE
										IF coke_timer > 600000 //120000
											//PRINT_NOW BCR3_07 5000 1 //~s~The coke courier has made his delivery.
											GOTO bcesar3_cleanup
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ELSE

//						IF caught_up_with_courier = 0
//							IF IS_PLAYER_PLAYING player1
//								IF NOT IS_CHAR_DEAD coke_courier
//									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer coke_courier 80.0 80.0 80.0 FALSE
//									//OR IS_CHAR_ON_SCREEN coke_courier
//										IF NOT IS_CAR_DEAD coke_bike
//											IF IS_PLAYBACK_GOING_ON_FOR_CAR coke_bike
//												CHANGE_PLAYBACK_TO_USE_AI coke_bike
//												SET_LOAD_COLLISION_FOR_CAR_FLAG coke_bike TRUE
//											ENDIF
//										ENDIF
//										SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE coke_courier KNOCKOFFBIKE_DEFAULT
//										//WRITE_DEBUG player_caught_up
//										caught_up_with_courier = 1
//									ELSE
//										IF coke_timer > 120000
//											//PRINT_NOW BCR3_07 5000 1 //~s~The coke courier has made his delivery.
//											GOTO bcesar3_cleanup
//										ENDIF
//										IF NOT IS_CAR_DEAD coke_bike
//											IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR coke_bike
//												//PRINT_NOW BCR3_07 5000 1 //~s~The coke courier has made his delivery.
//												GOTO bcesar3_cleanup
//											ENDIF
//										ENDIF
//									ENDIF
//								ENDIF
//							ENDIF
//						ENDIF

					ENDIF


					// check if courier got to end of route and has gone out of sight
					IF IS_PLAYER_PLAYING player1
						IF caught_up_with_courier = 1
							IF NOT IS_CHAR_DEAD coke_courier
								IF IS_PLAYER_PLAYING player1
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer coke_courier 100.0 100.0 FALSE
									OR IS_CHAR_ON_SCREEN coke_courier
										lost_timer = 0
									ENDIF
								ENDIF
								IF lost_timer > 20000
									PRINT BCR3_02 4000 1 // you lost the coke courier
									GOTO bcesar3_cleanup
								ENDIF
							ENDIF
						ENDIF
					ENDIF		
					


					// coke damage
					IF coke_damaged = -1
						IF NOT IS_CHAR_DEAD coke_courier
							IF IS_PLAYER_PLAYING player1
								IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR coke_courier scplayer
									//CREATE_FX_SYSTEM_ON_CHAR coke_trail coke_courier 0.0 -0.2 0.1 TRUE fx
									IF DOES_OBJECT_EXIST coke_bag
										CREATE_FX_SYSTEM_ON_OBJECT_WITH_DIRECTION coke_trail coke_bag 0.0 -0.2 -0.1 0.0 0.0 -1.0 TRUE fx
										PLAY_FX_SYSTEM fx
									ENDIF
									PRINT BCR3_04 5000 1 // he's losing drugs
									coke_damaged = 1
									TIMERA = 0	
								ENDIF
							ENDIF
						ENDIF
					ELSE
						IF TIMERA > 180000
							IF coke_damaged > 0
								STOP_FX_SYSTEM fx
								coke_damaged = 0
							ENDIF 
						ENDIF	
					ENDIF

					// goto next stage if courier is dead
					IF IS_CHAR_DEAD coke_courier
						m_goals++
					ENDIF


				ENDIF // is player playing
				
			ENDIF
			
			// courier has been killed by player
			IF m_goals = 4
				IF IS_PLAYER_PLAYING player1

					IF IS_CHAR_DEAD coke_courier
						// work out how much coke is left
						STOP_FX_SYSTEM fx
						temp_float =# TIMERA
						IF temp_float > 180000.0
							temp_float = 180000.0
						ENDIF
						temp_float /= 180000.0
						temp_float -= 1.0
						temp_float *= -1.0
						temp_float *= 2000.0 // maximum $2000
						coke_damaged =# temp_float
						REMOVE_BLIP coke_blip
						IF DOES_OBJECT_EXIST coke_bag
							DELETE_OBJECT coke_bag
						ENDIF

						VAR_INT checked_coke_pickup_z

						GET_DEAD_CHAR_PICKUP_COORDS coke_courier x y z
						CREATE_PICKUP PARA_PACK PICKUP_ONCE X Y Z coke_pickup
						ADD_BLIP_FOR_COORD x y z coke_blip
						CHANGE_BLIP_COLOUR coke_blip GREEN
						PRINT_NOW BCR3_05 4000 1 // pickup the drugs
						checked_coke_pickup_z = 0

						TIMERA = 0
						m_goals++
					ENDIF
				ENDIF
			ENDIF


			// debug - kill the coke courier
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
				IF NOT IS_CHAR_DEAD coke_courier
					TASK_DIE coke_courier
				ENDIF
			ENDIF

			// check pickup is above the ground
			IF checked_coke_pickup_z = 0
				IF NOT HAS_PICKUP_BEEN_COLLECTED coke_pickup
					GET_PICKUP_COORDINATES coke_pickup X Y Z
					IF IS_PLAYER_PLAYING player1
						IF LOCATE_CHAR_ANY_MEANS_2D scplayer x y 50.0 50.0 FALSE
							GET_GROUND_Z_FOR_3D_COORD x y z temp_float
							z = temp_float + 1.0
							REMOVE_PICKUP coke_pickup
							REMOVE_BLIP coke_blip
							CREATE_PICKUP PARA_PACK PICKUP_ONCE X Y Z coke_pickup
							ADD_BLIP_FOR_COORD x y z coke_blip
							CHANGE_BLIP_COLOUR coke_blip GREEN

//							WRITE_DEBUG_WITH_FLOAT package_x x
//							WRITE_DEBUG_WITH_FLOAT package_y y
//							WRITE_DEBUG_WITH_FLOAT package_z z
							checked_coke_pickup_z = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// wait for player to collect pickup
			IF m_goals = 5
				IF IS_PLAYER_PLAYING player1
					GET_PICKUP_COORDINATES coke_pickup X Y Z
					IF LOCATE_CHAR_ANY_MEANS_2D scplayer x y 300.0 300.0 FALSE
						IF TIMERA < 180000 
							IF NOT coke_pickup = 0
								IF HAS_PICKUP_BEEN_COLLECTED coke_pickup
									IF coke_damaged > 0	  
										// mission passed
										CLEAR_PRINTS
										m_passed = 1	
									ELSE
										PRINT_NOW BCR3_06 7500 1 // All the coke was lost during the chase!
									ENDIF
									GOTO bcesar3_cleanup
								ENDIF
							ELSE
								CLEAR_PRINTS
								GOTO bcesar3_cleanup
							ENDIF
						ELSE
							CLEAR_PRINTS
							GOTO bcesar3_cleanup
						ENDIF	
					ELSE
						CLEAR_PRINTS
						GOTO bcesar3_cleanup
					ENDIF
				ENDIF
			ENDIF

			// control speed of coke courier (after player has caught up)
			IF NOT caught_up_with_courier = 0

				IF route_mode = 0
					
					IF DOES_VEHICLE_EXIST coke_bike
						IF NOT IS_CAR_DEAD coke_bike
							IF IS_PLAYER_PLAYING player1
								IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer coke_bike 50.0 50.0 50.0 FALSE
									IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer coke_bike 20.0 20.0 20.0 FALSE
										SET_CAR_CRUISE_SPEED coke_bike 45.0
									ELSE
										SET_CAR_CRUISE_SPEED coke_bike 30.0
									ENDIF
								ELSE
									SET_CAR_CRUISE_SPEED coke_bike 20.0
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				ELSE

//					IF DOES_VEHICLE_EXIST coke_bike
//						IF NOT IS_CAR_DEAD coke_bike
//							IF IS_PLAYBACK_GOING_ON_FOR_CAR coke_bike
//								IF NOT IS_CHAR_DEAD coke_courier
//									IF IS_CHAR_IN_CAR coke_courier coke_bike
//										IF NOT IS_PLAYBACK_FOR_CAR_PAUSED coke_bike
//											GET_OFFSET_FROM_CAR_IN_WORLD_COORDS coke_bike 0.0 20.0 0.0 x y z
//											IF IS_PLAYER_PLAYING player1
//												GET_CHAR_COORDINATES scplayer x2 y2 z2
//											ENDIF
//											GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
//											GET_OFFSET_FROM_CAR_IN_WORLD_COORDS coke_bike 0.0 -20.0 0.0 x y z
//											GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2
//											IF temp_float < temp_float2
//												// player is in front of courier
//												playback_speed = 2.5
//											ELSE
//												// player is behind courier
//												IF IS_PLAYER_PLAYING player1
//													IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer coke_bike 20.0 20.0 20.0 FALSE
//														playback_speed +=@ 0.001
//													ELSE
//														IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer coke_bike 50.0 50.0 50.0 FALSE	
//															playback_speed +=@ -0.001
//														ENDIF
//													ENDIF 
//												ENDIF
//												IF playback_speed < 0.3
//													playback_speed = 0.3
//												ENDIF
//												IF playback_speed > 1.3
//													playback_speed = 1.3
//												ENDIF
//											ENDIF
//											SET_PLAYBACK_SPEED coke_bike playback_speed
//										ENDIF
//									ENDIF
//								ENDIF
//							ENDIF
//						ENDIF
//					ENDIF

				ENDIF
			ENDIF


			// goto next point in route - route finding method
			IF route_mode = 0

				// goto next point in route
				IF NOT IS_CHAR_DEAD coke_courier
					IF NOT checkpoint_count = -1
						IF NOT IS_CAR_DEAD coke_bike
							IF IS_CHAR_IN_CAR coke_courier coke_bike
								IF LOCATE_CHAR_IN_CAR_3D coke_courier bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 25.0 25.0 FALSE
									checkpoint_count++
									IF checkpoint_count < 85
										TASK_CAR_DRIVE_TO_COORD coke_courier coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 MODE_RACING TRUE DRIVINGMODE_AVOIDCARS
									ELSE
										TASK_CAR_DRIVE_WANDER coke_courier coke_bike 25.0 DRIVINGMODE_AVOIDCARS
								   		checkpoint_count = -1		
									ENDIF
								ELSE
									IF checkpoint_count < 85
										GET_SCRIPT_TASK_STATUS coke_courier TASK_CAR_DRIVE_TO_COORD temp_int
										IF temp_int = FINISHED_TASK
											TASK_CAR_DRIVE_TO_COORD coke_courier coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 MODE_RACING TRUE DRIVINGMODE_AVOIDCARS
										ENDIF
									ELSE
										GET_SCRIPT_TASK_STATUS coke_courier TASK_CAR_DRIVE_WANDER temp_int
										IF temp_int = FINISHED_TASK
											TASK_CAR_DRIVE_WANDER coke_courier coke_bike 25.0 DRIVINGMODE_AVOIDCARS
								   			checkpoint_count = -1	
								   		ENDIF	
									ENDIF	
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			ENDIF


			// make sure courier always has backpack (when alive)
			IF NOT IS_CHAR_DEAD coke_courier
				IF NOT DOES_OBJECT_EXIST coke_bag
					CREATE_OBJECT PARA_PACK 0.0 0.0 0.0 coke_bag
				ENDIF
				IF NOT IS_CHAR_HOLDING_OBJECT coke_courier coke_bag
					TASK_PICK_UP_OBJECT coke_courier coke_bag -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0
				ENDIF	
			ENDIF


			// freeze coke courier if player goes into an interior
			IF NOT vis_area = 0
				IF freeze_dude = 0
					IF DOES_VEHICLE_EXIST coke_bike 
						//IF NOT IS_CAR_DEAD coke_bike
							FREEZE_CAR_POSITION coke_bike TRUE
						//ENDIF
					ENDIF
					IF DOES_CHAR_EXIST coke_courier
						//IF NOT IS_CHAR_DEAD coke_courier
							FREEZE_CHAR_POSITION coke_courier TRUE
						//ENDIF
					ENDIF
					freeze_dude = 1
				ENDIF
			ELSE
				IF freeze_dude = 1
					IF DOES_VEHICLE_EXIST coke_bike 
						//IF NOT IS_CAR_DEAD coke_bike
							FREEZE_CAR_POSITION coke_bike FALSE
						//ENDIF
					ENDIF
					IF DOES_CHAR_EXIST coke_courier
						//IF NOT IS_CHAR_DEAD coke_courier
							FREEZE_CHAR_POSITION coke_courier FALSE
						//ENDIF
					ENDIF
					freeze_dude = 0
				ENDIF
			ENDIF


			// AI - behaviour if courier falls off bike get back on and continue - dm will make him kill player if he's spotted him
			IF DOES_VEHICLE_EXIST coke_bike
				IF NOT IS_CHAR_DEAD coke_courier
					IF caught_up_with_courier = 1
						IF NOT IS_CAR_DEAD coke_bike
							IF NOT IS_CHAR_IN_CAR coke_courier coke_bike
								IF IS_PLAYER_PLAYING player1
									IF NOT LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer coke_courier 20.0 20.0 10.0 FALSE
										GET_SCRIPT_TASK_STATUS coke_courier PERFORM_SEQUENCE_TASK temp_int
										IF temp_int = FINISHED_TASK
											CLEAR_CHAR_TASKS coke_courier
											OPEN_SEQUENCE_TASK temp_seq
												TASK_GOTO_CAR -1 coke_bike 10000 5.0
												TASK_ENTER_CAR_AS_DRIVER -1 coke_bike 10000 
											CLOSE_SEQUENCE_TASK	temp_seq
											PERFORM_SEQUENCE_TASK coke_courier temp_seq
											CLEAR_SEQUENCE_TASK temp_seq
										ELSE
											GET_SCRIPT_TASK_STATUS coke_courier TASK_KILL_CHAR_ON_FOOT temp_int
											IF NOT temp_int = FINISHED_TASK
												GET_SCRIPT_TASK_STATUS coke_courier PERFORM_SEQUENCE_TASK temp_int
												IF temp_int = FINISHED_TASK
													CLEAR_CHAR_TASKS coke_courier
													OPEN_SEQUENCE_TASK temp_seq
														TASK_GOTO_CAR -1 coke_bike 10000 5.0
														TASK_ENTER_CAR_AS_DRIVER -1 coke_bike 10000 
													CLOSE_SEQUENCE_TASK	temp_seq
													PERFORM_SEQUENCE_TASK coke_courier temp_seq
													CLEAR_SEQUENCE_TASK temp_seq
												ENDIF
											ENDIF	
										ENDIF
									ELSE
										IF coke_damaged = 1
											GET_SCRIPT_TASK_STATUS coke_courier TASK_KILL_CHAR_ON_FOOT temp_int
											IF temp_int = FINISHED_TASK
												CLEAR_CHAR_TASKS coke_courier
												TASK_KILL_CHAR_ON_FOOT coke_courier scplayer
											ENDIF
										ELSE
											GET_SCRIPT_TASK_STATUS coke_courier PERFORM_SEQUENCE_TASK temp_int
											IF temp_int = FINISHED_TASK
												CLEAR_CHAR_TASKS coke_courier
												OPEN_SEQUENCE_TASK temp_seq
													TASK_GOTO_CAR -1 coke_bike 10000 5.0
													TASK_ENTER_CAR_AS_DRIVER -1 coke_bike 10000 
												CLOSE_SEQUENCE_TASK	temp_seq
												PERFORM_SEQUENCE_TASK coke_courier temp_seq
												CLEAR_SEQUENCE_TASK temp_seq
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ELSE
							GET_SCRIPT_TASK_STATUS coke_courier PERFORM_SEQUENCE_TASK temp_int
							IF temp_int = FINISHED_TASK
								OPEN_SEQUENCE_TASK temp_seq
									IF IS_PLAYER_PLAYING player1
										TASK_KILL_CHAR_ON_FOOT -1 scplayer
									ENDIF
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK coke_courier temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
							ENDIF
						ENDIF
					ELSE
						IF NOT IS_CAR_DEAD coke_bike
							IF NOT IS_CHAR_IN_CAR coke_courier coke_bike
								WARP_CHAR_INTO_CAR coke_courier coke_bike
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF



		ENDIF 


// end of main loop
GOTO mission_loop_BCESAR3 


// ------------------------------------------------------------------------------------------------
// Mission Cleanup
bcesar3_cleanup:

IF mission_started_properly = 1
	
	IF IS_PLAYER_PLAYING player1
		TASK_USE_MOBILE_PHONE scplayer FALSE
	ENDIF

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	WAIT 1
	
	CLEAR_HELP

	IF m_passed = 0
		coke_damaged = 0
	ENDIF

	IF hide_text = 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
			PRINT_NOW BCR3_08 7500 1
		ENDIF
		PRINT_WITH_NUMBER_BIG BCE2_04 coke_damaged 7500 5	
	ENDIF					
	IF m_passed = 1
		IF IS_PLAYER_PLAYING player1
			ADD_SCORE player1 coke_damaged
		ENDIF
		IF bcesar3_passed_mission = 0
			//REGISTER_MISSION_PASSED BCESAR3
			REGISTER_ODDJOB_MISSION_PASSED
			bcesar3_passed_mission++
		ENDIF
		PLAY_MISSION_PASSED_TUNE 1
		CLEAR_WANTED_LEVEL PLAYER1
	ENDIF

	// === MARK ENTITIES AS NO LONGER NEEDED === (cars,peds,objects,blips,attractors)
	MARK_CHAR_AS_NO_LONGER_NEEDED coke_courier
	MARK_CAR_AS_NO_LONGER_NEEDED coke_bike
	MARK_OBJECT_AS_NO_LONGER_NEEDED coke_bag

	REMOVE_BLIP coke_blip

	KILL_FX_SYSTEM fx
	REMOVE_PICKUP coke_pickup
	REMOVE_DECISION_MAKER dm

	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBMX
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	REMOVE_CAR_RECORDING 556

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE

	mission_started_properly = 0

ENDIF


flag_on_courier_mission = 0

TERMINATE_THIS_SCRIPT



VAR_INT bce_point_to_edit
VAR_INT start_line_point
VAR_INT end_line_point
//VAR_FLOAT bce_min_x bce_min_y bce_min_z bce_max_x bce_max_y bce_max_z
bce3_display_path:

	start_line_point = bce_point_to_edit - 2
	end_line_point = bce_point_to_edit + 2
	IF start_line_point < 0
		start_line_point = 0
	ENDIF
	IF end_line_point > 84
		end_line_point = 84
	ENDIF

	temp_int = start_line_point
	WHILE temp_int < end_line_point
		temp_int2 = temp_int + 1
		IF temp_int2 < 85
			LINE bce2_CHECKPOINTS_x[temp_int] bce2_CHECKPOINTS_y[temp_int] bce2_CHECKPOINTS_z[temp_int]	bce2_CHECKPOINTS_x[temp_int2] bce2_CHECKPOINTS_y[temp_int2] bce2_CHECKPOINTS_z[temp_int2]
		ENDIF
	temp_int++
	ENDWHILE

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_PGUP
		bce_point_to_edit++
		IF bce_point_to_edit > 84
			bce_point_to_edit = 0
		ENDIF
		WRITE_DEBUG_WITH_INT bce_point_to_edit bce_point_to_edit 
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_PGDN
		bce_point_to_edit--
		IF bce_point_to_edit < 0
			bce_point_to_edit = 84
		ENDIF
		WRITE_DEBUG_WITH_INT bce_point_to_edit bce_point_to_edit 
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		bce2_CHECKPOINTS_y[bce_point_to_edit] += 0.5	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		bce2_CHECKPOINTS_y[bce_point_to_edit] += -0.5	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
		bce2_CHECKPOINTS_x[bce_point_to_edit] += 0.5	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
		bce2_CHECKPOINTS_x[bce_point_to_edit] += -0.5	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
		bce2_CHECKPOINTS_z[bce_point_to_edit] += 0.5	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
		bce2_CHECKPOINTS_z[bce_point_to_edit] += -0.5	
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
		bce2_CHECKPOINTS_y[bce_point_to_edit] += 10.0	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		bce2_CHECKPOINTS_y[bce_point_to_edit] += -10.0	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		bce2_CHECKPOINTS_x[bce_point_to_edit] += 10.0	
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
		bce2_CHECKPOINTS_x[bce_point_to_edit] += -10.0	
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
		SAVE_NEWLINE_TO_DEBUG_FILE 
		SAVE_STRING_TO_DEBUG_FILE "COKE COURIER ROUTE - edit point -"
		SAVE_INT_TO_DEBUG_FILE bce_point_to_edit
		SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int = 0
		WHILE temp_int < 85
			SAVE_STRING_TO_DEBUG_FILE "bce2_CHECKPOINTS_x["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bce2_CHECKPOINTS_x[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "bce2_CHECKPOINTS_y["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bce2_CHECKPOINTS_y[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "bce2_CHECKPOINTS_z["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_FLOAT_TO_DEBUG_FILE bce2_CHECKPOINTS_z[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE 
		temp_int++
		ENDWHILE
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RETURN
		temp_int = bce_point_to_edit - 1
		IF temp_int < 0
			temp_int = 0
		ENDIF
		bce2_CHECKPOINTS_x[bce_point_to_edit] = bce2_CHECKPOINTS_x[temp_int]
		bce2_CHECKPOINTS_y[bce_point_to_edit] = bce2_CHECKPOINTS_y[temp_int]
		bce2_CHECKPOINTS_z[bce_point_to_edit] = bce2_CHECKPOINTS_z[temp_int]
	ENDIF	

	
	IF IS_PLAYER_PLAYING player1
		LOCATE_CHAR_ANY_MEANS_3D scplayer bce2_CHECKPOINTS_x[bce_point_to_edit] bce2_CHECKPOINTS_y[bce_point_to_edit] bce2_CHECKPOINTS_z[bce_point_to_edit]	1.0 1.0 1.0 FALSE
		LOCATE_CHAR_ANY_MEANS_3D scplayer bce2_CHECKPOINTS_x[bce_point_to_edit] bce2_CHECKPOINTS_y[bce_point_to_edit] bce2_CHECKPOINTS_z[bce_point_to_edit]	0.5 0.5 0.5 FALSE	
	ENDIF


RETURN


}
MISSION_END
















//					// goto next point in route
//					IF NOT IS_CHAR_DEAD coke_courier
//						IF NOT checkpoint_count = -1
//							IF NOT IS_CAR_DEAD coke_bike
//								IF IS_CHAR_IN_CAR coke_courier coke_bike
//									IF LOCATE_CHAR_IN_CAR_3D coke_courier bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 25.0 25.0 FALSE
//										checkpoint_count++
//										IF checkpoint_count < 85
//											TASK_CAR_DRIVE_TO_COORD coke_courier coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 MODE_RACING TRUE DRIVINGMODE_AVOIDCARS
//											//CAR_GOTO_COORDINATES_RACING coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count]
//										ELSE
//											TASK_CAR_DRIVE_WANDER coke_courier coke_bike 25.0 DRIVINGMODE_AVOIDCARS
//									   		//CAR_WANDER_RANDOMLY coke_bike
//									   		checkpoint_count = -1		
//										ENDIF
//									ELSE
//										IF checkpoint_count < 85
//											GET_SCRIPT_TASK_STATUS coke_courier TASK_CAR_DRIVE_TO_COORD temp_int
//											IF temp_int = FINISHED_TASK
//												TASK_CAR_DRIVE_TO_COORD coke_courier coke_bike bce2_CHECKPOINTS_x[checkpoint_count] bce2_CHECKPOINTS_y[checkpoint_count] bce2_CHECKPOINTS_z[checkpoint_count] 25.0 MODE_RACING TRUE DRIVINGMODE_AVOIDCARS
//											ENDIF
//										ELSE
//											GET_SCRIPT_TASK_STATUS coke_courier TASK_CAR_DRIVE_WANDER temp_int
//											IF temp_int = FINISHED_TASK
//												TASK_CAR_DRIVE_WANDER coke_courier coke_bike 25.0 DRIVINGMODE_AVOIDCARS
//									   			checkpoint_count = -1	
//									   		ENDIF	
//										ENDIF	
//									ENDIF
//								ENDIF
//							ENDIF
//						ENDIF
//					ELSE
//						m_goals++
//					ENDIF














//
//
//							IF checkpoint_count = -1
//								IF NOT IS_CHAR_DEAD coke_courier
//									IF IS_PLAYER_PLAYING player1
//										IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer coke_courier 100.0 100.0 FALSE
//											IF NOT IS_CHAR_ON_SCREEN coke_courier
//												PRINT BCR3_02 4000 1 // you lost the coke courier
//												GOTO bcesar3_cleanup	
//											ENDIF
//										ENDIF
//									ENDIF
//								ENDIF
//							ELSE // player loses him on the route
//								IF NOT IS_CHAR_DEAD coke_courier
//									IF IS_PLAYER_PLAYING player1
//										IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer coke_courier 150.0 150.0 FALSE
//						 					TIMERB = 0
//										ENDIF
//										IF IS_CHAR_ON_SCREEN coke_courier
//											TIMERB = 0
//										ENDIF
//										IF TIMERB > 20000
//											// player has lost him
//											PRINT BCR3_02 4000 1 // you lost the coke courier
//											GOTO bcesar3_cleanup
//										ENDIF
//									ENDIF
//								ENDIF
//							ENDIF
//						ELSE
//							IF TIMERB > 600000
//								GOTO bcesar3_cleanup
//							ENDIF
//						ENDIF
//					ENDIF
//