MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* desert 9 ******************************************
// *********************************** C3 Shootout *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME desert9

// Mission start stuff

GOSUB mission_start_desert9

game_over:

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_desert9_failed
ENDIF

GOSUB mission_cleanup_desert9

game_end:




MISSION_HAS_FINISHED

MISSION_END

{  
// **************************************** Mission Start **********************************

//VARIABLES DECLARED

//VEHICLES
LVAR_INT d9_plane d9_players_bike d9_enemy_car[4]

//Chars
LVAR_INT d9_enemy[8]

//objects
LVAR_INT d9_plane_ramp d9_crate[6] d9_parachute d9_locator

//timers
LVAR_INT d9_timer

//flags
LVAR_INT d9_fail_flag d9_print_text_flag d9_enemy_attacks[5] d9_skip_to_end

//sequences
LVAR_INT d9_seq

//cutscene 1 vars

LVAR_INT d9_skip_pressed 

// Flags

LVAR_INT d9_flag d9_player_killed d9_fake_death
LVAR_INT  d9_cut_flag

// scene1 vars

LVAR_INT d9_plane_leave_time d9_plane_leave_flag 

//coordinates

LVAR_FLOAT d9_plane_x d9_plane_y d9_plane_z	d9_check_x1 d9_check_x2 d9_check_y1 d9_check_y2
LVAR_FLOAT d9_hatch_angle

//cutscene2 vars

LVAR_FLOAT d9_plane_pitch
LVAR_INT d9_bottom_ramp d9_top_ramp d9_ground
LVAR_INT d9_cut_timer

//scene2 _vars

//VAR_INT d9_player_sucked_out
	LVAR_INT d9_trigger_roll d9_int1 d9_int2 d9_int3

// barrel bosug

	LVAR_INT d9_barrel[20] d9_drop_barrel d9_barrel_timer d9_dive_time d9_barrel_start_roll_time d9_time_passed
	LVAR_FLOAT d9_barrel_rotation[20] d9_velX d9_velY d9_velZ d9_addVel	d9_barrel_x1 d9_barrel_y1 d9_barrel_z1 d9_barrel_x2 d9_barrel_y2 d9_barrel_z2 d9_distance
	LVAR_FLOAT d9_barrel_move_percent d9_x d9_y
	LVAR_FLOAT d9_z d9_rot_vel

//scene

//blips
LVAR_INT  d9_plane_blip d9_bike_blip

LVAR_FLOAT d9_air_res










mission_start_desert9:

//VIEW_INTEGER_VARIABLE  d9_cut_flag  d9_cut_flag


//VIEW_INTEGER_VARIABLE d9_player_sucked_out d9_player_sucked_out

REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT DSERT9

SET_WANTED_MULTIPLIER 0.0

SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
SET_CHAR_AREA_VISIBLE scplayer 0


flag_player_on_mission = 1

SWITCH_ENTRY_EXIT Deshous FALSE

//VIEW_FLOAT_VARIABLE para_freefall_Vz para_freefall_Vz
//
//REQUEST_MODEL ANDROM
//WHILE NOT HAS_MODEL_LOADED ANDROM
//	WAIT 0
//ENDWHILE
//
//
//
//CREATE_CHECKPOINT checkpoint_tube 339.0 1000.0 10.0 0.0 0.0 0.0 6.0 cp_blah
//		CREATE_CAR ANDROM -205.6183 2507.2812 29.3479 d9_plane
//		SET_CAR_HEADING d9_plane 270.0
//		SET_CAR_FORWARD_SPEED d9_plane 50.0
//		SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER d9_plane 3.8
//		WARP_CHAR_INTO_CAR scplayer d9_plane
//
//d9_pl_speed = 35.0

//		REQUEST_CAR_RECORDING 168
//		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 168
//			WAIT 0
//		ENDWHILE
//		IF NOT IS_CAR_DEAD d9_plane 
//			START_PLAYBACK_RECORDED_CAR d9_plane 168
//		ENDIF
//
//


//		START_RECORDING_CAR	d9_plane 168

//wankers:
//
//LVAR_INT recording_now cp_blah
//LVAR_FLOAT d9_pl_speed
//
//
//
//
//WAIT 0
//
////IF NOT IS_CAR_DEAD d9_plane
////	SET_CAR_FORWARD_SPEED d9_plane 40.0
////ENDIF
////
////IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
////AND recording_now = 0
////	IF NOT IS_CAR_DEAD d9_plane
////		START_RECORDING_CAR	d9_plane 168
////		recording_now = 1
////	ENDIF
////ENDIF 
//
////IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
////AND recording_now = 0
////	IF NOT IS_CAR_DEAD d9_plane
////		STOP_RECORDING_CAR d9_plane
////		recording_now = 1
////	ENDIF
////ENDIF
//
//
//
//GOTO wankers
//




d9_z = 100.0

IF d9_z = 0.0
	CREATE_OBJECT barrel1 0.0 0.0 0.0 d9_barrel[0]
	CREATE_OBJECT barrel1 0.0 0.0 0.0 d9_box[0]
	CREATE_OBJECT kb_barrel 0.0 0.0 0.0 d9_locator
	CREATE_CAR PCJ600 426.6481 2535.7126 15.5538 d9_players_bike
	SET_CAR_HEADING d9_players_bike 107.0
	ADD_BLIP_FOR_COORD  d9_plane_x d9_plane_y d9_plane_z d9_plane_blip
	CREATE_PICKUP Gun_para  PICKUP_ONCE 1797.6023 -1308.8815 133.8128 para_pickup
	ADD_BLIP_FOR_CAR d9_players_bike d9_bike_blip
						CREATE_OBJECT cr_guncrate 365.0146 2500.6755 15.492 d9_crate[0]
					CREATE_OBJECT cr_cratestack 365.9573 2502.7632 15.4921 d9_crate[1]
					CREATE_OBJECT cr_cratestack 365.5524 2503.4602 15.492 d9_crate[2]
					CREATE_OBJECT cr_cratestack 364.5623 2503.4055 15.4921 d9_crate[3]

ENDIF 
//
//
//
//
//

//
//		REQUEST_MODEL BOBCAT
//
//
//		LOAD_ALL_MODELS_NOW
//
//		WHILE NOT HAS_MODEL_LOADED BOBCAT
//			WAIT 0
//		ENDWHILE
//
//
//		CREATE_CAR BOBCAT 366.7640 2512.6550 15.5677 d9_enemy_car[0]
//		CREATE_CAR BOBCAT 367.1340 2490.1738 15.4884 d9_enemy_car[1]
//		CREATE_CAR BOBCAT 357.6567 2509.5942 15.5221 d9_enemy_car[2]
//
//					SET_CAR_HEADING d9_enemy_car[0]	247.2312
//					SET_CAR_HEADING d9_enemy_car[1]	228.9755
//					SET_CAR_HEADING d9_enemy_car[2]	192.3090

//VAR_FLOAT d9_fX d9_fY d9_fZ  d9_oX d9_oY d9_oZ

//VIEW_FLOAT_VARIABLE d9_fX fX
//VIEW_FLOAT_VARIABLE d9_fY fY
//VIEW_FLOAT_VARIABLE d9_fZ fZ
//VIEW_FLOAT_VARIABLE d9_oX oX
//VIEW_FLOAT_VARIABLE d9_oY oY
//VIEW_FLOAT_VARIABLE d9_oZ oZ

mission_loop:


	WAIT 0


//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_A
//		d9_fX += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Z
//		d9_fX -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
//		d9_fY += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_X
//		d9_fY -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
//		d9_fZ += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_C
//		d9_fZ -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
//		d9_oX += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_V
//		d9_oX -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
//		d9_oY += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_B
//		d9_oY -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
//		d9_oZ += 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_N
//		d9_oZ -= 0.01
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
//		IF NOT IS_CAR_DEAD d9_enemy_car[0]
//		AND NOT IS_CAR_DEAD d9_enemy_car[1]
//		AND NOT IS_CAR_DEAD d9_enemy_car[2]
//			SET_CAR_COORDINATES d9_enemy_car[0] 366.7640 2512.6550 15.5677
//			SET_CAR_COORDINATES d9_enemy_car[1] 367.1340 2490.1738 15.4884
//			SET_CAR_COORDINATES d9_enemy_car[2] 357.6567 2509.5942 15.5221
//				SET_CAR_HEADING d9_enemy_car[0]	247.2312
//					SET_CAR_HEADING d9_enemy_car[1]	228.9755
//					SET_CAR_HEADING d9_enemy_car[2]	192.3090
//			SET_CAR_HEALTH d9_enemy_car[0] 1000
//			SET_CAR_HEALTH d9_enemy_car[1] 1000
//			SET_CAR_HEALTH d9_enemy_car[2] 1000
//
//
//		ENDIF
//	ENDIF
//
//
//d9_fx = 0.08
//d9_fz = 0.05
//d9_oy = 2.5
//d9_oz = 0.01
//
//
//
//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_W
//		IF NOT IS_CAR_DEAD d9_enemy_car[0]
//		AND NOT IS_CAR_DEAD d9_enemy_car[1]
//		AND NOT IS_CAR_DEAD d9_enemy_car[2]
//			APPLY_FORCE_TO_CAR d9_enemy_car[0] d9_fX d9_fY d9_fZ  d9_oX d9_oY d9_oZ
//			APPLY_FORCE_TO_CAR d9_enemy_car[1] d9_fX d9_fY d9_fZ  d9_oX d9_oY d9_oZ
//			APPLY_FORCE_TO_CAR d9_enemy_car[2] d9_fX d9_fY d9_fZ  d9_oX d9_oY d9_oZ
//		ENDIF
//	ENDIF
//





//GOTO mission_loop






	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
    	GOTO mission_desert9_passed  
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
		WHILE NOT HAS_MODEL_LOADED gun_para
			REQUEST_MODEL gun_para
			WAIT 0
		ENDWHILE
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
    	d9_flag = 0
    	d9_cut_flag = 0  
	ENDIF


	SWITCH d9_flag
		CASE 0
			GOSUB initialise_stuff
		BREAK

		CASE 20
			// Plane lands. 
			GOSUB cutscene_1a
		BREAK

		CASE 21
			// MOCAP of Toerno and CJ 
			GOSUB cutscene_1b
		BREAK

		CASE 22
			// Cut for playing entering bike 
			GOSUB cutscene_1c
		BREAK

		CASE 2
			//player races after plane
			GOSUB plane_race
		BREAK

		CASE 3
			//plane takes off. Player trapped inside
			GOSUB cutscene_2
		BREAK

		CASE 4
			//plane_fight
			GOSUB scene_2
		BREAK

		CASE 5
			//player jumps out of plane to freedom
			GOSUB cutscene_3
		BREAK

		CASE 6
			//player uses parachute to land safely
			GOSUB parachuting
		BREAK

		CASE 7
			//plane crashes with player onboard
			GOSUB cutscene_4
		BREAK

		CASE 8
			//player fails to make it on to plane
			GOSUB cutscene_5
		BREAK

		CASE 9
			//player passed - cut of plane exploding
			GOSUB cutscene_6
		BREAK



	ENDSWITCH

	GOSUB display_instructions
	GOSUB fail_checks
	GOSUB desert9_audio

GOTO mission_loop


fail_checks:
	SWITCH d9_flag
		CASE 2
			
		BREAK
	ENDSWITCH
RETURN




// ***************************************************************************************
// ************************************* LEVEL SCENES ************************************
// ***************************************************************************************

initialise_stuff:

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1
d9_flag = 20

RETURN

// ############################################################################################################################################
// ################################################  INTRO CUTSCENE  ##########################################################################
// ############################################################################################################################################


cutscene_1a:
	
	SET_FADING_COLOUR 0 0 0

	IF d9_cut_flag = 0

		SET_CHAR_AREA_VISIBLE scplayer 0
		DO_FADE 1000 FADE_OUT
		d9_cut_flag = 1

	ENDIF

	IF d9_cut_flag = 1

		IF NOT GET_FADING_STATUS


			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_AREA_OF_CARS -120.5297 2593.6650 69.1939 502.5016 2365.4182 -1.2447
			CLEAR_AREA_OF_CHARS -120.5297 2593.6650 69.1939 502.5016 2365.4182 -1.2447

			SET_CHAR_COORDINATES_NO_OFFSET scplayer 222.7755 2508.9717 15.5214
			



			


			// ************* LOAD MODELS ***************


			REQUEST_MODEL ANDROM
			REQUEST_MODEL BOBCAT
			REQUEST_MODEL WMOMIB
			REQUEST_MODEL MICRO_UZI
			REQUEST_MODEL BMYMIB

	//		REQUEST_MODEL COLT45
	//		REQUEST_MODEL PCJ600		
	//		REQUEST_MODEL SATCHEL
	//		REQUEST_MODEL BOMB
//			REQUEST_MODEL gun_para
	//		REQUEST_ANIMATION AIRPORT
	///		REQUEST_ANIMATION CARRY

	//		REQUEST_CAR_RECORDING 135
	//		REQUEST_CAR_RECORDING 149
	//		REQUEST_CAR_RECORDING 134
	//		REQUEST_CAR_RECORDING 168

			REQUEST_CAR_RECORDING 450
			REQUEST_CAR_RECORDING 168
			REQUEST_CAR_RECORDING 452
			REQUEST_CAR_RECORDING 453


			LOAD_ALL_MODELS_NOW

			d9_cut_flag = 1
		ENDIF
	ENDIF

	IF d9_cut_flag = 1
		IF HAS_MODEL_LOADED ANDROM
		AND HAS_MODEL_LOADED BOBCAT
		AND HAS_MODEL_LOADED WMOMIB
		AND HAS_MODEL_LOADED MICRO_UZI
		AND HAS_MODEL_LOADED BMYMIB
			IF HAS_CAR_RECORDING_BEEN_LOADED 450
			AND HAS_CAR_RECORDING_BEEN_LOADED 168
			AND HAS_CAR_RECORDING_BEEN_LOADED 452
			AND HAS_CAR_RECORDING_BEEN_LOADED 453
				
				CREATE_CAR ANDROM 339.9034 2499.8049 15.4884 d9_plane
				
//				

				// setup cars to chase plane down runway
				CREATE_CAR BOBCAT -22.1321 2504.9424 15.2566 d9_enemy_car[0] 
				CREATE_CAR BOBCAT -15.9009 2507.2803 15.2543 d9_enemy_car[1]
				CREATE_CAR BOBCAT -17.3227 2501.0107 15.2520 d9_enemy_car[2]
				CHANGE_CAR_COLOUR d9_enemy_car[0] CARCOLOUR_BLACK CARCOLOUR_BLACK
				CHANGE_CAR_COLOUR d9_enemy_car[1] CARCOLOUR_BLACK CARCOLOUR_BLACK
				CHANGE_CAR_COLOUR d9_enemy_car[2] CARCOLOUR_BLACK CARCOLOUR_BLACK
				SET_CAR_HEADING d9_enemy_car[0] 270.0
				SET_CAR_HEADING d9_enemy_car[1] 270.0
				SET_CAR_HEADING d9_enemy_car[2] 270.0
				CREATE_CHAR_INSIDE_CAR d9_enemy_car[0] PEDTYPE_MISSION1 BMYMIB d9_enemy[0]
				CREATE_CHAR_INSIDE_CAR d9_enemy_car[1] PEDTYPE_MISSION1 WMOMIB d9_enemy[1]
				CREATE_CHAR_INSIDE_CAR d9_enemy_car[2] PEDTYPE_MISSION1 WMOMIB d9_enemy[2]
				CREATE_CHAR_AS_PASSENGER d9_enemy_car[0] PEDTYPE_MISSION1 WMOMIB 0 d9_enemy[3]
				CREATE_CHAR_AS_PASSENGER d9_enemy_car[1] PEDTYPE_MISSION1 BMYMIB 0 d9_enemy[4]
				CREATE_CHAR	PEDTYPE_MISSION1 WMOMIB -28.1475 2492.8069 15.2513 d9_enemy[5]
				CREATE_CHAR	PEDTYPE_MISSION1 BMYMIB -28.1475 2493.8069 15.2513 d9_enemy[6]
				CREATE_CHAR	PEDTYPE_MISSION1 BMYMIB -28.1475 2494.8069 15.2513 d9_enemy[7]

				ATTACH_CHAR_TO_CAR d9_enemy[5] d9_enemy_car[0] 0.5 -1.0 0.4 FACING_FORWARD 10.0 WEAPONTYPE_MICRO_UZI
				ATTACH_CHAR_TO_CAR d9_enemy[6] d9_enemy_car[1] -0.5 -1.0 0.4 FACING_FORWARD 10.0 WEAPONTYPE_MICRO_UZI
				ATTACH_CHAR_TO_CAR d9_enemy[7] d9_enemy_car[1] 0.5 -1.0 0.4 FACING_FORWARD 10.0 WEAPONTYPE_MICRO_UZI


				d9_cut_timer = TIMERA + 1500
				d9_cut_flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF d9_cut_flag = 2
		IF d9_cut_timer > TIMERA
	
			IF NOT IS_CAR_DEAD d9_enemy_car[0]
			AND NOT IS_CAR_DEAD d9_enemy_car[1]
			AND NOT IS_CAR_DEAD d9_enemy_car[2]
			AND NOT IS_CAR_DEAD d9_plane
				IF HAS_CAR_RECORDING_BEEN_LOADED 450
				AND HAS_CAR_RECORDING_BEEN_LOADED 168
				AND HAS_CAR_RECORDING_BEEN_LOADED 452
				AND HAS_CAR_RECORDING_BEEN_LOADED 453
					
					START_PLAYBACK_RECORDED_CAR d9_enemy_car[0] 450
					START_PLAYBACK_RECORDED_CAR d9_enemy_car[1] 453		
					START_PLAYBACK_RECORDED_CAR d9_enemy_car[2] 452
					START_PLAYBACK_RECORDED_CAR d9_plane 168
					SET_CAR_ENGINE_ON d9_plane TRUE
					SET_PLAYBACK_SPEED d9_enemy_car[0] 0.8
					SET_PLAYBACK_SPEED d9_enemy_car[1] 0.9
					SET_PLAYBACK_SPEED d9_enemy_car[2] 0.8
					
																	  
					SET_FIXED_CAMERA_POSITION -123.0824 2533.0571 21.6790 0.0 0.0 0.0
					
					LOAD_SCENE_IN_DIRECTION -123.0824 2533.0571 21.6790 100.0
					REQUEST_COLLISION -123.0824 2533.0571

					DO_FADE 600 FADE_IN

	//				CAMERA_RESET_NEW_SCRIPTABLES
	//				CAMERA_PERSIST_TRACK TRUE                   
	//				CAMERA_PERSIST_POS TRUE                       
	//				CAMERA_SET_VECTOR_MOVE -151.1351 2541.5195 25.3160 -130.0832 2535.8176 24.9708 5500 TRUE
	//				CAMERA_SET_VECTOR_TRACK -152.1043 2541.2839 25.3875 -129.1079 2535.6426 24.8358 5500 TRUE

					d9_cut_timer = TIMERA + 5000
					d9_audio_time = TIMERA + 1000
					d9_cut_flag = 3

				ELSE
					REQUEST_CAR_RECORDING 450
					REQUEST_CAR_RECORDING 168				
					REQUEST_CAR_RECORDING 452
					REQUEST_CAR_RECORDING 453
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF d9_cut_flag = 3
		IF NOT IS_CAR_DEAD d9_enemy_car[0]
		AND NOT IS_CAR_DEAD d9_enemy_car[1]
		AND NOT IS_CAR_DEAD d9_enemy_car[2]
		AND NOT IS_CAR_DEAD d9_plane
			d9_x = 0.0
			d9_y = 0.0
			d9_z = 0.0
			GET_CAR_COORDINATES d9_enemy_car[0] x y z
			d9_x += x
			d9_y += y
			d9_z += z
			GET_CAR_COORDINATES d9_enemy_car[1] x y z
			d9_x += x
			d9_y += y
			d9_z += z
			GET_CAR_COORDINATES d9_enemy_car[2] x y z
			d9_x += x
			d9_y += y
			d9_z += z
			d9_x /= 3.0
			d9_y /= 3.0
			d9_z /= 3.0
			POINT_CAMERA_AT_POINT d9_x d9_y d9_z JUMP_CUT
			IF TIMERA > d9_cut_timer
				d9_cut_flag = 4
			ENDIF

		LVAR_INT d9_audio_time

			IF NOT d9_audio_time = 0
				IF TIMERA > d9_audio_time
					play_audio = 1
					d9_audio_time = 0
				ENDIF
			ENDIF

		ENDIF
	ENDIF

	IF d9_cut_flag = 4

		IF NOT IS_CAR_DEAD d9_enemy_car[0]
		AND NOT IS_CAR_DEAD d9_enemy_car[1]
		AND NOT IS_CAR_DEAD d9_enemy_car[2]

			IF NOT IS_CHAR_DEAD d9_enemy[0]
			AND NOT IS_CHAR_DEAD d9_enemy[1]
			AND NOT IS_CHAR_DEAD d9_enemy[2]

				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[0]
				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[1]
				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[2]

				SET_CAR_COORDINATES d9_enemy_car[0]	90.1321 2504.9424 15.4766
				SET_CAR_COORDINATES d9_enemy_car[1]	83.9009 2510.2803 15.4766
				SET_CAR_COORDINATES d9_enemy_car[2]	85.3227 2497.0107 15.4766

				TASK_CAR_DRIVE_TO_COORD d9_enemy[0] d9_enemy_car[0] 400.2219 2504.9424 15.4766 12.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
				TASK_CAR_DRIVE_TO_COORD d9_enemy[1] d9_enemy_car[1] 400.2219 2510.2803 15.4766 12.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
				TASK_CAR_DRIVE_TO_COORD d9_enemy[2] d9_enemy_car[2] 400.2219 2497.0107 15.4766 12.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH

				SET_CAR_HEADING d9_enemy_car[0] 270.0
				SET_CAR_HEADING d9_enemy_car[1] 270.0
				SET_CAR_HEADING d9_enemy_car[2]	270.0

				SET_CAR_FORWARD_SPEED d9_enemy_car[0] 12.0  
				SET_CAR_FORWARD_SPEED d9_enemy_car[1] 12.0
				SET_CAR_FORWARD_SPEED d9_enemy_car[2] 12.0
			ENDIF
		ENDIF



		SET_PLAYBACK_SPEED d9_plane 0.7


		SET_CHAR_HEADING scplayer 270.0
		OPEN_SEQUENCE_TASK d9_seq
			TASK_PLAY_ANIM -1 flee_lkaround_01 PED 8.0 FALSE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK d9_seq
		PERFORM_SEQUENCE_TASK scplayer d9_seq
		CLEAR_SEQUENCE_TASK d9_seq

		play_audio = 2
		
		SET_FIXED_CAMERA_POSITION 224.1743 2508.4255 16.7184 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 223.1940 2508.5354 16.8827 JUMP_CUT

		d9_cut_flag = 5
		d9_cut_timer = TIMERA + 1700
	ENDIF


	// cut of plane landing and cars following on
	IF d9_cut_flag = 5
		IF TIMERA > d9_cut_timer
		 
			IF NOT IS_CAR_DEAD d9_enemy_car[0]
			AND NOT IS_CAR_DEAD d9_enemy_car[1]
			AND NOT IS_CAR_DEAD d9_enemy_car[2]
					IF NOT IS_CHAR_DEAD d9_enemy[0]
					AND NOT IS_CHAR_DEAD d9_enemy[1]
					AND NOT IS_CHAR_DEAD d9_enemy[2]
						SET_CAR_COORDINATES d9_enemy_car[0]	90.1321 2504.9424 15.4766
						SET_CAR_COORDINATES d9_enemy_car[1]	83.9009 2510.2803 15.4766
						SET_CAR_COORDINATES d9_enemy_car[2]	85.3227 2497.0107 15.4766

						TASK_CAR_DRIVE_TO_COORD d9_enemy[0] d9_enemy_car[0] 400.2219 2504.9424 15.4766 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
						TASK_CAR_DRIVE_TO_COORD d9_enemy[1] d9_enemy_car[1] 400.2219 2510.2803 15.4766 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_CAR_DRIVE_TO_COORD d9_enemy[2] d9_enemy_car[2] 400.2219 2497.0107 15.4766 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH




				SET_CAR_HEADING d9_enemy_car[0] 270.0
				SET_CAR_HEADING d9_enemy_car[1] 270.0
				SET_CAR_HEADING d9_enemy_car[2]	270.0

					SET_CAR_FORWARD_SPEED d9_enemy_car[0] 30.0  
					SET_CAR_FORWARD_SPEED d9_enemy_car[1] 30.0
					SET_CAR_FORWARD_SPEED d9_enemy_car[2] 30.0
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD d9_plane
				STOP_PLAYBACK_RECORDED_CAR d9_plane
			ENDIF




			SET_FIXED_CAMERA_POSITION 125.3001 2504.3286 15.5565  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 124.3120 2504.3611 15.7067 JUMP_CUT
			d9_cut_timer = TIMERA + 2700
			d9_cut_flag = 6
			IF NOT IS_CAR_DEAD d9_plane
				SET_CAR_ENGINE_ON d9_plane TRUE
				SET_HELI_BLADES_FULL_SPEED d9_plane
			ENDIF
			SET_CHAR_COORDINATES scplayer 412.6561 2530.9084 15.617
			SET_CHAR_HEADING scplayer 136.0
		   	d9_plane_x = 25.6646
			d9_plane_y = 2504.6455
			d9_plane_z = 29.1834
//			PRINT des9_35 8500 1	   		
		ENDIF
	ENDIF

//CJ runs to hide
	IF d9_cut_flag = 6

		//plane control here
		IF NOT IS_CAR_DEAD d9_plane
			LVAR_FLOAT d9_a_float
//			VIEW_FLOAT_VARIABLE d9_a_float d9_a_float

			d9_time_passed = TIMERA - d9_cut_timer
			d9_time_passed += 2700
			d9_a_float =# d9_time_passed
			d9_a_float /= 20.0
			x = d9_plane_x + d9_a_float
			d9_a_float /= 14.0
			z = d9_plane_z - d9_a_float
			SET_CAR_COORDINATES_NO_OFFSET d9_plane x d9_plane_y z
		ENDIF

		IF TIMERA > d9_cut_timer
			SET_CHAR_COORDINATES scplayer 403.0466 2522.1899 15.4843
			TASK_GO_STRAIGHT_TO_COORD scplayer 415.1068 2528.6240 15.5375 PEDMOVE_RUN -2

			LOAD_SCENE_IN_DIRECTION 404.7372 2524.8567 15.5671 300.0
			SET_FIXED_CAMERA_POSITION 404.7372 2524.8567 15.5671 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 405.6275 2525.3010 15.6661 JUMP_CUT
													
			d9_cut_timer = TIMERA + 2500
			d9_cut_flag = 8
		ENDIF			 
	ENDIF


	//carl hiding crouched behind pillar
	IF d9_cut_flag = 7


		

		IF TIMERA > d9_cut_timer
			d9_cut_flag = 8
			IF NOT IS_CAR_DEAD d9_plane				
				SET_CAR_COORDINATES d9_plane 380.6384 2503.9270 15.4884
				IF NOT IS_CAR_DEAD d9_enemy_car[0]
				AND NOT IS_CAR_DEAD d9_enemy_car[1]
				AND NOT IS_CAR_DEAD d9_enemy_car[2]
					IF NOT IS_CHAR_DEAD d9_enemy[0]
					AND NOT IS_CHAR_DEAD d9_enemy[1]
					AND NOT IS_CHAR_DEAD d9_enemy[2]
						SET_CAR_COORDINATES d9_enemy_car[0] 331.2219 2504.9424 15.4766
						SET_CAR_COORDINATES d9_enemy_car[1] 338.2219 2507.9424 15.4766
						SET_CAR_COORDINATES d9_enemy_car[2] 325.2219 2501.9424 15.4766
						TASK_CAR_DRIVE_TO_COORD d9_enemy[0] d9_enemy_car[0] 430.2219 2504.9424 15.4766 13.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
						TASK_CAR_DRIVE_TO_COORD d9_enemy[1] d9_enemy_car[1] 430.2219 2507.2803 15.4766 13.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_CAR_DRIVE_TO_COORD d9_enemy[2] d9_enemy_car[2] 430.2219 2501.0107 15.4766 13.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
					ENDIF
				ENDIF



				SET_CAR_HEADING d9_plane 270.0
				SET_CAR_FORWARD_SPEED d9_plane 7.0
				SET_HELI_BLADES_FULL_SPEED d9_plane			
				SET_FIXED_CAMERA_POSITION 413.1315 2533.2974 15.8551 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 412.5080 2532.5188 15.9255 JUMP_CUT
				d9_hatch_angle = 0.0
				d9_cut_timer = TIMERA + 4000
			ENDIF
		ENDIF
	ENDIF

		
	IF d9_cut_flag = 8
		IF TIMERA > d9_cut_timer
			DO_FADE 800 FADE_OUT
			IF NOT IS_CAR_DEAD d9_plane
				STOP_PLAYBACK_RECORDED_CAR d9_plane			
				d9_cut_flag = 9
			ENDIF
		ENDIF
	ENDIF

	IF d9_cut_flag = 9
		IF NOT GET_FADING_STATUS

			IF NOT IS_CAR_DEAD d9_plane
				STOP_PLAYBACK_RECORDED_CAR d9_plane			
			ENDIF 

			IF NOT IS_CAR_DEAD d9_enemy_car[0]
			AND NOT IS_CAR_DEAD d9_enemy_car[1]
			AND NOT IS_CAR_DEAD d9_enemy_car[2]

				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[0]
				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[1]
				STOP_PLAYBACK_RECORDED_CAR d9_enemy_car[2]
			ENDIF

			MARK_MODEL_AS_NO_LONGER_NEEDED ANDROM
			MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
			MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
			MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB

			REMOVE_CAR_RECORDING 450
			REMOVE_CAR_RECORDING 168
			REMOVE_CAR_RECORDING 452
			REMOVE_CAR_RECORDING 453

			DELETE_CAR d9_enemy_car[0]
			DELETE_CAR d9_enemy_car[1]
			DELETE_CAR d9_enemy_car[2]
//			DELETE_CAR d9_plane

			DELETE_CHAR d9_enemy[0]
			DELETE_CHAR d9_enemy[1]
			DELETE_CHAR d9_enemy[2]
			DELETE_CHAR d9_enemy[3]			
			DELETE_CHAR d9_enemy[4]
			DELETE_CHAR d9_enemy[5]
			DELETE_CHAR d9_enemy[6]
			DELETE_CHAR d9_enemy[7]

			IF NOT IS_CAR_DEAD d9_plane
				SET_CAR_COORDINATES d9_plane 394.6384 2497.9270 15.4884
				SET_CAR_HEADING d9_plane 90.0
			ENDIF

			LVAR_INT d9_skip_flag			
			d9_cut_flag = 0
			IF d9_skip_flag > 0
				d9_flag = 22
			ELSE
				d9_flag = 21
			ENDIF
		ENDIF
	ENDIF

	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		IF NOT GET_FADING_STATUS
			IF d9_skip_flag = 0
				DO_FADE 600 FADE_OUT
				d9_skip_flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF d9_skip_flag = 1
		IF NOT GET_FADING_STATUS
			audio_flag = 5
			d9_skip_flag = 2
			d9_cut_flag = 9				
		ENDIF
	ENDIF

RETURN

















// ############################################################################################################################################
// ################################################  INTRO CUTSCENE  ##########################################################################
// ############################################################################################################################################


cutscene_1b:

			LOAD_CUTSCENE Desert9
			 
			WHILE NOT HAS_CUTSCENE_LOADED
			            WAIT 0
			ENDWHILE
			 
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
			            WAIT 0
			ENDWHILE
			
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			    IF d9_skip_flag = 0
			 	    d9_skip_flag = 2
				ENDIF
			ENDIF

			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
		

			DO_FADE 0 FADE_OUT

			WHILE GET_FADING_STATUS
			            WAIT 0
			ENDWHILE

			CLEAR_CUTSCENE
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF

			d9_flag = 22


RETURN








// ############################################################################################################################################
// ################################################  INTRO CUTSCENE  ##########################################################################
// ############################################################################################################################################


cutscene_1c:


	IF d9_cut_flag = 0
		SET_CHAR_AREA_VISIBLE scplayer 0
		REQUEST_MODEL ANDROM
		REQUEST_MODEL BOBCAT
		REQUEST_MODEL WMOMIB
		REQUEST_MODEL MICRO_UZI
		REQUEST_MODEL BMYMIB
		REQUEST_MODEL PCJ600		
		REQUEST_MODEL SATCHEL
		REQUEST_MODEL BOMB
		REQUEST_ANIMATION AIRPORT
		REQUEST_ANIMATION CARRY
		REQUEST_CAR_RECORDING 149
		REQUEST_CAR_RECORDING 135
		REQUEST_CAR_RECORDING 134

		d9_cut_flag = 1
	ENDIF

	IF d9_cut_flag = 1
		IF	HAS_MODEL_LOADED BOBCAT
		AND	HAS_MODEL_LOADED WMOMIB
		AND	HAS_MODEL_LOADED MICRO_UZI
		AND	HAS_MODEL_LOADED BMYMIB
		AND	HAS_MODEL_LOADED PCJ600	
		AND HAS_CAR_RECORDING_BEEN_LOADED 134	
			IF	HAS_MODEL_LOADED SATCHEL
			AND	HAS_MODEL_LOADED BOMB
			AND	HAS_ANIMATION_LOADED AIRPORT
			AND	HAS_ANIMATION_LOADED CARRY
			AND HAS_CAR_RECORDING_BEEN_LOADED 149
			AND HAS_CAR_RECORDING_BEEN_LOADED 135
				d9_cut_flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF d9_cut_flag = 2

		// setup cars to chase plane down runway
		CREATE_CAR BOBCAT 366.7640 2512.6550 15.5677 d9_enemy_car[0] 
		CREATE_CAR BOBCAT 367.1340 2490.1738 15.4884 d9_enemy_car[1]
		CREATE_CAR BOBCAT 357.6567 2509.5942 15.5221 d9_enemy_car[2]
		CHANGE_CAR_COLOUR d9_enemy_car[0] CARCOLOUR_BLACK CARCOLOUR_BLACK
		CHANGE_CAR_COLOUR d9_enemy_car[1] CARCOLOUR_BLACK CARCOLOUR_BLACK
		CHANGE_CAR_COLOUR d9_enemy_car[2] CARCOLOUR_BLACK CARCOLOUR_BLACK
		CREATE_CHAR PEDTYPE_MISSION1 BMYMIB 359.7588 2511.4741 15.5501 d9_enemy[0]
		CREATE_CHAR PEDTYPE_MISSION1 WMOMIB 369.7111 2505.8376 15.4921 d9_enemy[1]
		CREATE_CHAR PEDTYPE_MISSION1 WMOMIB 363.5374 2497.6741 15.4921 d9_enemy[2]
		CREATE_CHAR_AS_PASSENGER d9_enemy_car[0] PEDTYPE_MISSION1 WMOMIB 0 d9_enemy[3]
		CREATE_CHAR_AS_PASSENGER d9_enemy_car[1] PEDTYPE_MISSION1 BMYMIB 0 d9_enemy[4]
		CREATE_CHAR	PEDTYPE_MISSION1 WMOMIB 363.6662 2500.0938 15.4921 d9_enemy[5]
	//	CREATE_CHAR	PEDTYPE_MISSION1 BMYMIB -28.1475 2493.8069 15.2513 d9_enemy[6]
	//	CREATE_CHAR	PEDTYPE_MISSION1 BMYMIB -28.1475 2494.8069 15.2513 d9_enemy[7]

		IF NOT IS_CAR_DEAD d9_plane
			SET_CAR_HEADING d9_plane 90.0
		ENDIF

				
		CONTROL_MOVABLE_VEHICLE_PART d9_plane 1.0

		GIVE_WEAPON_TO_CHAR d9_enemy[0] WEAPONTYPE_MICRO_UZI 9999
		GIVE_WEAPON_TO_CHAR d9_enemy[1] WEAPONTYPE_MICRO_UZI 9999
		GIVE_WEAPON_TO_CHAR d9_enemy[2] WEAPONTYPE_MICRO_UZI 9999

		OPEN_CAR_DOOR d9_enemy_car[0] FRONT_LEFT_DOOR
		OPEN_CAR_DOOR d9_enemy_car[0] FRONT_RIGHT_DOOR

		CREATE_OBJECT cr_guncrate 365.0146 2500.6755 15.492 d9_crate[0]
		CREATE_OBJECT cr_cratestack 365.9573 2502.7632 15.4921 d9_crate[1]
		CREATE_OBJECT cr_cratestack 365.5524 2503.4602 15.492 d9_crate[2]
		CREATE_OBJECT cr_cratestack 364.5623 2503.4055 15.4921 d9_crate[3]

		SET_OBJECT_HEADING d9_crate[1] 245.0



		CLEAR_AREA 435.2751 2527.5227 15.8710 10.0 FALSE
		CREATE_CAR PCJ600 435.2751 2527.5227 15.8710 d9_players_bike
		SET_CAR_HEADING d9_players_bike 90.0
		SET_CAN_BURST_CAR_TYRES d9_players_bike FALSE

		SET_CHAR_HEADING d9_enemy[0] 340.9066
		SET_CHAR_HEADING d9_enemy[1] 309.4871 
		SET_CHAR_HEADING d9_enemy[2] 175.4073

		TASK_PLAY_ANIM d9_enemy[0] IDLE_ARMED PED 4.0 TRUE 0 0 0 0
		TASK_PLAY_ANIM d9_enemy[1] IDLE_ARMED PED 4.0 TRUE 0 0 0 0
		TASK_PLAY_ANIM d9_enemy[2] IDLE_ARMED PED 4.0 TRUE 0 0 0 0
		


	//					SET_CHAR_COORDINATES scplayer 426.6481 2535.7126 15.5538

		SET_CHAR_COORDINATES scplayer 412.6561 2530.9084 15.617
		SET_CHAR_HEADING scplayer 136.0



		MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
		MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
		MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
		MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45



			IF NOT IS_CAR_DEAD d9_plane
				START_PLAYBACK_RECORDED_CAR d9_plane 149
				SET_PLAYBACK_SPEED d9_plane 0.0
			ENDIF

			SET_FIXED_CAMERA_POSITION 350.8692 2488.9319 17.5906 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 351.4443 2489.7468 17.520 JUMP_CUT

			LOAD_SCENE 367.1340 2490.1738 15.4884

		d9_cut_flag = 3
		d9_cut_timer = TIMERA + 1000
	ENDIF

	IF d9_cut_flag = 3
		IF d9_cut_timer > TIMERA


			IF NOT IS_CHAR_DEAD d9_enemy[5]
			AND DOES_OBJECT_EXIST d9_crate[3]
				OPEN_SEQUENCE_TASK d9_seq
					TASK_COMPLEX_PICKUP_OBJECT -1 d9_crate[3]
					TASK_GO_STRAIGHT_TO_COORD -1 360.2195 2500.0229 15.4921 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK d9_seq
				PERFORM_SEQUENCE_TASK d9_enemy[5] d9_seq
				CLEAR_SEQUENCE_TASK d9_seq
			ENDIF


			DO_FADE 1000 FADE_IN

			IF d9_skip_flag = 0
				d9_cut_timer = TIMERA + 4000
			ELSE
				d9_cut_timer = 0
			ENDIF
			d9_cut_flag = 4

			CLEAR_PRINTS
			PRINT DES9_3 4000 1	
		  
		ENDIF
	ENDIF

	IF d9_cut_flag = 4

		IF TIMERA > d9_cut_timer
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION 438.1800 2526.1931 17.1554 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 437.2076 2526.3389 16.9735 JUMP_CUT
			SET_CHAR_COORDINATES scplayer 426.8950 2529.4785 15.6483
			IF NOT IS_CAR_DEAD d9_players_bike
				TASK_ENTER_CAR_AS_DRIVER scplayer d9_players_bike -2 
			ENDIF
			PRINT DES9_4 3000 1
			IF d9_skip_flag = 0
				d9_cut_timer = TIMERA + 3000
			ELSE
				d9_cut_timer = 0
			ENDIF
			d9_cut_flag = 5	
		ENDIF
	ENDIF


	IF d9_cut_flag = 5
		IF TIMERA > d9_cut_timer

			CLEAR_PRINTS
			PRINT DES9_44 4000 1
			IF NOT IS_CAR_DEAD d9_players_bike
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					WARP_CHAR_INTO_CAR scplayer d9_players_bike
				ENDIF
			ENDIF



			// Attach invisible barrel object on planes ramp. The coords for this will be used to check if 
			// player has driven up the ramp. 
			CREATE_OBJECT kb_barrel 0.0 0.0 0.0 d9_locator
			SET_OBJECT_COLLISION d9_locator FALSE
			SET_OBJECT_VISIBLE d9_locator FALSE
 
			IF NOT IS_CAR_DEAD d9_plane
				ATTACH_OBJECT_TO_CAR d9_locator d9_plane 0.0 -15.223 -1.489 0.0 0.0 0.0
			ENDIF
			LVAR_INT d9_ammo
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE d9_ammo
			IF d9_ammo = 0
				GIVE_WEAPON_TO_CHAR	scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE 1
			ENDIF

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			d9_cut_timer = TIMERA + 3000
			d9_cut_flag = 6
		ENDIF
	ENDIF

	IF d9_cut_flag = 6
		IF TIMERA > d9_cut_timer
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON


			CLEAR_PRINTS
			PRINT DES9_A4 4000 1
			d9_flag = 2

		ENDIF
	ENDIF

RETURN



// ############################################################################################################################################
// ################################################  PLANE RACE  ##############################################################################
// ############################################################################################################################################
							   
plane_race:

		GET_CHAR_COORDINATES scplayer player_X player_y player_z

		IF d9_plane_leave_flag > 0
			IF NOT IS_CAR_DEAD d9_plane
				GET_CAR_COORDINATES d9_plane d9_plane_x d9_plane_y d9_plane_z
				REMOVE_BLIP d9_plane_blip
				ADD_BLIP_FOR_COORD  d9_plane_x d9_plane_y d9_plane_z d9_plane_blip
				CHANGE_BLIP_DISPLAY d9_plane_blip BLIP_ONLY
				IF d9_plane_x < -83.0
					d9_flag = 8
					d9_cut_flag = 0				
				ENDIF
			ENDIF
		ENDIF


		IF NOT IS_CAR_DEAD d9_players_bike
			GET_CAR_COORDINATES d9_players_bike d9_x d9_y d9_z			

			d9_air_res = 2499.7 - d9_y
			ABS d9_air_res
			d9_air_res /= 10.0

			IF d9_air_res > 2.0
				d9_air_res = 2.0
			ENDIF

			IF d9_air_res < 0.2
				d9_air_res = 0.2
			ENDIF
			SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER  d9_players_bike d9_air_res
			d9_air_res -= 2.0
			ABS d9_air_res
			d9_air_res *= 50.0
			LVAR_FLOAT d9_float1 d9_float2 d9_float3
			d9_float1 = d9_plane_x - d9_x
			ABS d9_float1
			d9_float2 = d9_float1 / 100.0
			IF d9_float2 > 1.0
				d9_float2 = 1.0
			ENDIF
			d9_float1 = 1.0 - d9_float2
			d9_float1 *= d9_air_res
			d9_int1 =# d9_float1
			SHAKE_CAM d9_int1
   
			d9_int1 -= 256
			ABS d9_int1

		ENDIF


		IF d9_plane_leave_flag = 0
			IF NOT IS_CAR_DEAD d9_players_bike
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 412.6561 2530.9084 15.617 5.0 5.0 5.0 FALSE
					d9_plane_leave_time = TIMERA + 8000
					d9_plane_leave_flag = 2					
				ENDIF
			ENDIF
		ENDIF



		IF d9_plane_leave_flag = 2


			IF IS_CHAR_IN_ANY_CAR scplayer
			OR TIMERA > d9_plane_leave_time
			OR LOCATE_CHAR_ANY_MEANS_3D scplayer 354.8564 2500.1516 18.3701 50.0 50.0 50.0 FALSE
				IF NOT IS_CAR_DEAD d9_plane
					d9_plane_leave_time = TIMERA + 1350

					d9_plane_leave_flag = 3

				IF NOT IS_CHAR_DEAD d9_enemy[0]
					TASK_KILL_CHAR_ON_FOOT d9_enemy[0] scplayer
				ENDIF

				IF NOT IS_CHAR_DEAD d9_enemy[1]
					TASK_TOGGLE_DUCK d9_enemy[1] TRUE
					TASK_KILL_CHAR_ON_FOOT d9_enemy[1] scplayer
				ENDIF

				IF NOT IS_CHAR_DEAD d9_enemy[2]
					TASK_KILL_CHAR_ON_FOOT d9_enemy[2] scplayer
				ENDIF

				IF NOT IS_CHAR_DEAD d9_enemy[5]
					CLEAR_CHAR_TASKS_IMMEDIATELY d9_enemy[5]
				ENDIF

				ENDIF
			ENDIF
		ENDIF

		IF d9_plane_leave_flag = 3

			IF TIMERA > d9_plane_leave_time
				SET_PLAYBACK_SPEED d9_plane 1.0
				d9_plane_leave_flag = 4
			ENDIF
		ENDIF

		IF d9_plane_leave_flag = 4

			LVAR_FLOAT d9_speed
			GET_OBJECT_COORDINATES d9_locator x y z
			GET_CHAR_COORDINATES scplayer d9_x d9_y d9_z
			GET_DISTANCE_BETWEEN_COORDS_2D x y d9_x d9_y d9_distance
			d9_speed = 200.0 / d9_distance//INCREASE NUMBER TO MAKE CAR GO FASTER
			IF d9_speed > 1.0
				d9_speed = 1.0
			ENDIF
			IF d9_speed < 0.3
				d9_speed = 0.3
			ENDIF
			IF NOT IS_CAR_DEAD d9_plane
				SET_PLAYBACK_SPEED d9_plane d9_speed
			ENDIF


			LVAR_INT dropping_stuff
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer x y z 15.0 15.0 5.0 FALSE
				IF dropping_stuff < 2
					GOSUB drop_shit_from_plane
					dropping_stuff = 1
				ENDIF
			ELSE
				IF dropping_stuff = 1
					dropping_stuff = 2
				ENDIF
			ENDIF
			IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer d9_locator 4.0 1.0 1.0 FALSE
				IF NOT IS_CAR_DEAD d9_players_bike
					IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
						SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_NEVER
						REMOVE_BLIP d9_plane_blip
						SET_PLAYER_CONTROL player1 OFF
						d9_cut_flag = 20
						d9_flag = 3
						SET_RADIO_CHANNEL RS_OFF		

					ENDIF
				ENDIF
			ENDIF
		ENDIF

RETURN



// *****************************************************************************************
// *********** Cut of plane taking off, player dodges barrel, instructions  ****************
// *****************************************************************************************

cutscene_2:

	d9_enemy_stops_rolling_barrels = 1

	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		IF d9_cut_flag < 11
			d9_cut_timer = 0
			d9_cut_flag = 11
		ENDIF
		IF d9_cut_flag > 19
		AND d9_cut_flag < 23
			d9_cut_timer = 0
			d9_cut_flag = 24
			d9_skip_to_end = 1
		ENDIF
	ENDIF

	IF d9_cut_flag = 20
		IF NOT IS_CAR_DEAD d9_plane
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_plane
				STOP_PLAYBACK_RECORDED_CAR d9_plane
			ENDIF
			START_PLAYBACK_RECORDED_CAR d9_plane 135
			IF NOT IS_CAR_DEAD d9_players_bike
				FREEZE_CAR_POSITION d9_players_bike TRUE
				SET_CAR_VISIBLE d9_players_bike FALSE
			ENDIF			
			SET_CHAR_VISIBLE scplayer FALSE
		
			DELETE_CAR d9_enemy_car[0]
			DELETE_CAR d9_enemy_car[1]
			DELETE_CAR d9_enemy_car[2]

			MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
			REQUEST_ANIMATION AIRPORT
						 
			d9_cut_flag = 21			
		ENDIF
	ENDIF

	IF d9_cut_flag = 21
		CAMERA_RESET_NEW_SCRIPTABLES
		CAMERA_PERSIST_TRACK TRUE                   
		CAMERA_PERSIST_POS TRUE                       
		CAMERA_SET_VECTOR_MOVE 66.4737 2494.1182 17.9816 -43.8545 2492.9021 19.8651 5500 TRUE
		CAMERA_SET_VECTOR_TRACK 67.4593 2494.2866 17.9942 -44.3218 2493.1838 20.7031 5500 TRUE									  
		d9_cut_flag = 22
		d9_cut_timer = TIMERA + 4500
	ENDIF

	IF d9_cut_flag = 22
		IF TIMERA > d9_cut_timer
			DO_FADE 1000 FADE_OUT
//			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			d9_cut_flag = 23
		ENDIF
	ENDIF

	IF d9_cut_flag = 23
		IF NOT GET_FADING_STATUS
			d9_cut_flag = 0
		ENDIF
	ENDIF

	IF d9_cut_flag = 24
		DO_FADE 1000 FADE_OUT
//		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		d9_cut_flag = 25
	ENDIF

	IF d9_cut_flag = 25
		IF NOT GET_FADING_STATUS
			d9_cut_flag = 0
			d9_skip_to_end = 1
		ENDIF
	ENDIF	

	IF d9_cut_flag = 0

		

			IF NOT IS_CAR_DEAD d9_players_bike
				FREEZE_CAR_POSITION d9_players_bike FALSE
				SET_CAR_VISIBLE d9_players_bike TRUE
			ENDIF	

		SET_AREA_VISIBLE 9

		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_AREA_VISIBLE scplayer 9
		ENDIF

		DELETE_CHAR d9_enemy[4]
		CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  316.0887 983.9927 1958.1381 d9_enemy[4]
		SET_CHAR_HEADING d9_enemy[4] 90.0
		IF NOT IS_CHAR_DEAD d9_enemy[4]
			SET_CHAR_AREA_VISIBLE d9_enemy[4] 9
		ENDIF

		IF NOT IS_CHAR_DEAD d9_enemy[4]
			SET_CHAR_COORDINATES d9_enemy[4] 317.081 979.907 1960.108
			SET_CHAR_HEADING d9_enemy[4] 90.0
		ENDIF







		IF NOT IS_CAR_DEAD d9_players_bike 
			SET_VEHICLE_AREA_VISIBLE d9_players_bike 9
		ENDIF

		DISPLAY_RADAR FALSE

		CREATE_OBJECT d9_runway 305.1910 1030.7520 1938.1339 d9_ground
		
		REQUEST_CAR_RECORDING 150
		REQUEST_CAR_RECORDING 152
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 150
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 152
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD d9_players_bike

			SET_CAR_COORDINATES d9_players_bike 331.8417 1038.3140 1956.1768
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_INTO_CAR scplayer d9_players_bike
			ENDIF
			CLEAR_PRINTS
	
			SET_CHAR_AREA_VISIBLE scplayer 9
			SET_CHAR_VISIBLE scplayer TRUE
			START_PLAYBACK_RECORDED_CAR d9_players_bike 150
			SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR d9_players_bike
			IF NOT IS_CHAR_IN_CAR scplayer d9_players_bike
				WARP_CHAR_INTO_CAR scplayer d9_players_bike
			ENDIF
		ENDIF


		LOAD_SCENE 331.8417 1038.3140 1956.1768
		REQUEST_COLLISION 331.8417 1038.3140

		WAIT 800

		CAMERA_RESET_NEW_SCRIPTABLES		

		LVAR_FLOAT d9_ground_x d9_ground_y d9_ground_z
		
		SET_FIXED_CAMERA_POSITION 320.1115 1035.373 1948.154 0.0 0.0 0.0

		POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT


		LVAR_INT d9_prime_recording d9_first_barrel_time

		LVAR_INT d9_percent
		LVAR_FLOAT d9_percentF

		d9_first_barrel_time = TIMERA

		WAIT 500

		d9_cut_timer = TIMERA + 2200
		REQUEST_MODEL gun_para
		WHILE NOT HAS_MODEL_LOADED gun_para
			WAIT 0
		ENDWHILE
		DO_FADE 600 FADE_IN

		PRINT DES9_A5 4000 1

		IF d9_skip_to_end = 1
			d9_cut_flag = 11
			d9_cut_timer = 0
		ELSE
			d9_cut_flag = 1
		ENDIF
	ENDIF

	IF d9_cut_flag > 0
	AND d9_cut_flag < 20
		GOSUB drop_barrels
	ENDIF



		IF d9_barrel_set_to_drop = 0
			IF TIMERA > d9_first_barrel_time
				d9_barrel_set_to_drop = 1
			ENDIF
		ENDIF
			


		IF DOES_OBJECT_EXIST d9_ground 

			GET_OBJECT_COORDINATES d9_ground d9_ground_x d9_ground_y d9_ground_z

			d9_ground_y += 0.05

		ENDIF

		IF d9_cut_flag = 1
			IF TIMERA > d9_cut_timer
				TASK_LEAVE_ANY_CAR scplayer
				d9_cut_flag = 2
				d9_cut_timer = TIMERA + 300	
			ENDIF
		ENDIF


    	// camera looks at bike on ramp
		IF d9_cut_flag = 2
			IF TIMERA > d9_cut_timer
				IF NOT IS_CAR_DEAD d9_plane
					DELETE_CAR d9_plane
				ENDIF	
		   		SET_FIXED_CAMERA_POSITION 317.7203 1027.3005 1949.0709 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 316.9413 1026.6750 1949.0266 JUMP_CUT
				
				d9_cut_timer = TIMERA + 1000
				d9_cut_flag = 3
			ENDIF
		ENDIF

		// set camera to pan as bike drives up ramp
		IF d9_cut_flag = 3
			IF TIMERA > d9_cut_timer


					DELETE_OBJECT d9_ground

					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 317.7203 1027.3005 1949.0709 317.7203 1027.3005 1949.0709 1000 TRUE
					CAMERA_SET_VECTOR_TRACK 316.9413 1026.6750 1949.0266 317.3408 1026.3773 1949.1305 1000 TRUE

				
				d9_cut_flag = 4
				d9_cut_timer = TIMERA + 1500
			ENDIF
		ENDIF


		// start playback of bike, cam pans to barrel rolling down plane
		IF d9_cut_flag = 4

			d9_percent = d9_cut_timer - TIMERA
			d9_percentF =# d9_percent
			d9_percentF /= 2000.0
			d9_percentF -= 1.0
			ABS d9_percentF
			GET_OBJECT_COORDINATES d9_barrel[5] d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0]

			IF d9_barrel_y[0] > 1020.2140
				IF NOT IS_CAR_DEAD d9_players_bike
					START_PLAYBACK_RECORDED_CAR d9_players_bike 152
				ENDIF
				TASK_DIVE_AND_GET_UP scplayer 1.0 0.0 1000
//				PRINT_NOW DES9_22 3500 1
				d9_cut_flag = 6
				d9_cut_timer = TIMERA + 2000
					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 317.7203 1027.3005 1949.0709 317.7203 1027.3005 1949.0709 2000 TRUE
					CAMERA_SET_VECTOR_TRACK 317.3408 1026.3773 1949.1305 317.1811 1027.9913 1948.5891 2000 TRUE
			ENDIF


			d9_x = d9_barrel_x[0] - 317.5107
			d9_y = d9_barrel_y[0] - 1024.8152 
			d9_z = d9_barrel_z[0] - 1949.3497

			d9_x *= d9_percentF
			d9_y *= d9_percentF
			d9_z *= d9_percentF

			d9_x += 317.5107
			d9_y += 1024.8152
			d9_z += 1949.3497
			
		ENDIF

		

		IF d9_cut_flag = 5
			GET_OBJECT_COORDINATES d9_barrel[5] d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0]
		ENDIF


		// player says "What the fuck...?"
		IF d9_cut_flag = 6
			GET_OBJECT_COORDINATES d9_barrel[5] d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0]
//			POINT_CAMERA_AT_POINT d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0] JUMP_CUT
			IF TIMERA > d9_cut_timer
				d9_cut_flag = 11 //was 7
				CLEAR_PRINTS
			ENDIF
		ENDIF

		// Thug says "Fuck you cunt"
		IF d9_cut_flag = 7
				CAMERA_RESET_NEW_SCRIPTABLES
				SET_FIXED_CAMERA_POSITION 316.0625 988.6853 1957.7406  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 316.0338 987.7417 1958.0701 JUMP_CUT
				d9_cut_flag = 8			 
				d9_cut_timer = TIMERA + 2000
				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_COORDINATES d9_enemy[4] 315.7535 983.4348 1958.2650
					SET_CHAR_HEADING d9_enemy[4] 0.0
					TASK_PLAY_ANIM d9_enemy[4] FUCKU PED 4.0 FALSE 0 0 0 3000
				ENDIF
		ENDIF

//		camera points at player with Satchel
		IF d9_cut_flag = 8
			IF HAS_MODEL_LOADED gun_para
				IF TIMERA > d9_cut_timer
					CLEAR_PRINTS
					PRINT DES9_45 4000 1 
					SET_FIXED_CAMERA_POSITION 317.5727 1024.7908 1949.8541 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 317.4102 1023.8087 1949.9496 JUMP_CUT

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer 317.9964 1023.4773 1949.0326
					SET_CHAR_HEADING scplayer 121.0
//					CREATE_OBJECT para_pack 315.6801 973.3562 1960.5784 d9_parachute
					IF NOT IS_CHAR_DEAD d9_enemy[4]
//					TASK_PICK_UP_OBJECT d9_enemy[4] d9_parachute -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0
						GIVE_WEAPON_TO_CHAR d9_enemy[4] WEAPONTYPE_PARACHUTE 1
						SET_DEATH_WEAPONS_PERSIST d9_enemy[4] TRUE

					//give parachute weapon to char
					ENDIF
					d9_cut_flag = 9
					d9_cut_timer = TIMERA + 4000
				ENDIF
			ELSE
				REQUEST_MODEL gun_para
			ENDIF
		ENDIF

		// camera points at guard to be killed
		IF d9_cut_flag = 9
			IF TIMERA > d9_cut_timer
				SET_FIXED_CAMERA_POSITION 318.8293 975.9706 1960.7589 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 318.3584 976.8294 1960.5571 JUMP_CUT

				IF NOT IS_CHAR_DEAD d9_enemy[4]

						SET_CHAR_COORDINATES d9_enemy[4] 317.4929 981.1245 1958.8062
						SET_CHAR_HEADING d9_enemy[4] 90.0
						WHILE NOT HAS_ANIMATION_LOADED AIRPORT
							REQUEST_ANIMATION AIRPORT
							WAIT 0
						ENDWHILE
						TASK_PLAY_ANIM d9_enemy[4] thrw_barl_thrw AIRPORT 4.0 FALSE 0 0 0 -1

				ENDIF



				PRINT DES9_46 4000 1
				d9_cut_flag = 10
				d9_cut_timer = TIMERA + 1100
			ENDIF
		ENDIF

		IF d9_cut_flag = 10
			IF TIMERA > d9_cut_timer
				d9_barrel_set_to_drop = 2				
				d9_cut_flag = 11
				d9_cut_timer = TIMERA + 2900
			ENDIF
		ENDIF

		IF d9_cut_flag = 11
			IF TIMERA > d9_cut_timer

				REQUEST_MODEL gun_para
				WHILE NOT HAS_MODEL_LOADED gun_para
					WAIT 0
				ENDWHILE

				CAMERA_RESET_NEW_SCRIPTABLES
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 318.1163 1024.7913 1948.7294
				ELSE
					SET_CHAR_COORDINATES scplayer 318.1163 1024.7913 1948.7294
				ENDIF

				SET_CHAR_HEADING scplayer 121.0

				

				IF NOT IS_CAR_DEAD d9_players_bike
					IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_players_bike
						STOP_PLAYBACK_RECORDED_CAR d9_players_bike						
					ENDIF
					DELETE_CAR d9_players_bike
					MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
				ENDIF

				SET_CHAR_HEADING scplayer 225.0

				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_COORDINATES d9_enemy[4] 317.4565 980.5405 1958.9347
				ENDIF

				IF NOT IS_CAR_DEAD d9_players_bike
					IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_players_bike
						STOP_PLAYBACK_RECORDED_CAR d9_players_bike
					ENDIF					
				ENDIF

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				DELETE_OBJECT d9_ground

//				PRINT DES9_25 4000 1

				IF NOT IS_CAR_DEAD d9_plane
					DELETE_CAR d9_plane
				ENDIF

				IF IS_PLAYER_PLAYING player1
					DISABLE_PLAYER_SPRINT player1 TRUE
				ENDIF

				CREATE_FX_SYSTEM cloudfast 315.2994 1028.1514 1936.4642 TRUE des_cloud_FX
				PLAY_FX_SYSTEM des_cloud_FX

				DELETE_CHAR d9_enemy[0]
				DELETE_CHAR d9_enemy[1]
				DELETE_CHAR d9_enemy[2]
				DELETE_CHAR d9_enemy[3]

				
				CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  313.4011 1012.3696 1951.5879 d9_enemy[0]
				CREATE_CHAR PEDTYPE_MISSION3 BMYMIB 312.5008 1004.9619 1953.2974 d9_enemy[1]
				CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  318.7976 988.7245 1957.0443 d9_enemy[2]
				CREATE_CHAR PEDTYPE_MISSION3 BMYMIB 313.1324 991.6313 1956.3735 d9_enemy[3]


				IF NOT IS_CAR_DEAD d9_players_bike
					FREEZE_CAR_POSITION d9_players_bike FALSE
					SET_CAR_VISIBLE d9_players_bike TRUE
				ENDIF
				SET_CHAR_VISIBLE scplayer TRUE

				SET_CHAR_SHOOT_RATE d9_enemy[0] 100
				SET_CHAR_SHOOT_RATE d9_enemy[1] 100	
				SET_CHAR_SHOOT_RATE d9_enemy[2] 100
				SET_CHAR_SHOOT_RATE d9_enemy[3] 100

				SET_CHAR_WEAPON_SKILL d9_enemy[0] WEAPONSKILL_PRO
				SET_CHAR_WEAPON_SKILL d9_enemy[1] WEAPONSKILL_PRO
				SET_CHAR_WEAPON_SKILL d9_enemy[2] WEAPONSKILL_PRO
				SET_CHAR_WEAPON_SKILL d9_enemy[3] WEAPONSKILL_PRO
				SET_CHAR_WEAPON_SKILL d9_enemy[4] WEAPONSKILL_PRO
				
				SET_CHAR_HEADING d9_enemy[0] 288.4161
				SET_CHAR_HEADING d9_enemy[1] 298.9878
				SET_CHAR_HEADING d9_enemy[2] 85.3329 
				SET_CHAR_HEADING d9_enemy[3] 308.2083	


				IF NOT IS_CHAR_DEAD d9_enemy[0]
					SET_CHAR_AREA_VISIBLE d9_enemy[0] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[1]
					SET_CHAR_AREA_VISIBLE d9_enemy[1] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[2]
					SET_CHAR_AREA_VISIBLE d9_enemy[2] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[3]
					SET_CHAR_AREA_VISIBLE d9_enemy[3] 9
				ENDIF

				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_COORDINATES d9_enemy[4] 317.881 979.907 1960.108
					IF NOT HAS_CHAR_GOT_WEAPON d9_enemy[4] WEAPONTYPE_PARACHUTE
						GIVE_WEAPON_TO_CHAR d9_enemy[4] WEAPONTYPE_PARACHUTE 1
						SET_DEATH_WEAPONS_PERSIST d9_enemy[4] TRUE
						SET_CHAR_SHOOT_RATE d9_enemy[4] 100
					ENDIF
				ENDIF

				d9_flag = 4
				d9_SATCHEL_planted = 0				
				d9_enemy_stops_rolling_barrels = 0
//				CLEAR_CUTSCENE
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_PROOFS scplayer FALSE TRUE TRUE FALSE FALSE
				ENDIF
				

				LVAR_INT print_instructions
				print_instructions = 1
				para_control_off = 1 //stops player from being able to open chute

		// You're trapped on the plane. 
		// One of the crew is wearing a parachute. Aquire it, then place your satchel charge.
		 // Once planted, trigger the satchel charge with the detonator or by jumping out the back of the plane.
		 // Clear the plane before being engulfed in the explosive's fireball.



			ENDIF
			
		ENDIF		
   



RETURN









/*


// *****************************************************************************************
// *********** Cut of plane taking off, player dodges barrel, instructions  ****************
// *****************************************************************************************





cutscene_2:

//	LOAD_SCENE 316.0887 983.9927 1958.1381

	d9_enemy_stops_rolling_barrels = 1

	IF d9_cut_flag = 0
		IF NOT IS_CAR_DEAD d9_plane
			d9_first_barrel_time = TIMERA + 6500
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_plane
				STOP_PLAYBACK_RECORDED_CAR d9_plane
			ENDIF
			START_PLAYBACK_RECORDED_CAR d9_plane 135
			IF NOT IS_CAR_DEAD d9_players_bike
				FREEZE_CAR_POSITION d9_players_bike TRUE
				SET_CAR_VISIBLE d9_players_bike FALSE
			ENDIF			
			SET_CHAR_VISIBLE scplayer FALSE

			DELETE_CAR d9_enemy_car[0]
			DELETE_CAR d9_enemy_car[1]
			DELETE_CAR d9_enemy_car[2]

			MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
			REQUEST_ANIMATION AIRPORT
						 
			d9_cut_flag = 1			
		ENDIF
	ENDIF

	IF d9_cut_flag = 1
		CAMERA_RESET_NEW_SCRIPTABLES
		CAMERA_PERSIST_TRACK TRUE                   
		CAMERA_PERSIST_POS TRUE                       
		CAMERA_SET_VECTOR_MOVE 66.4737 2494.1182 17.9816 -43.8545 2492.9021 19.8651 5500 TRUE
		CAMERA_SET_VECTOR_TRACK 67.4593 2494.2866 17.9942 -44.3218 2493.1838 20.7031 5500 TRUE									  

		d9_cut_flag = 2
		d9_cut_timer = TIMERA + 4500
	ENDIF

	IF d9_cut_flag = 2
		IF TIMERA > d9_cut_timer
			DO_FADE 1000 FADE_OUT
//			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			d9_cut_flag = 3
		ENDIF
	ENDIF

	IF d9_cut_flag = 3
		IF NOT GET_FADING_STATUS
			d9_cut_flag = 4
		ENDIF
	ENDIF

	IF d9_cut_flag = 4

		IF NOT IS_CAR_DEAD d9_players_bike
			FREEZE_CAR_POSITION d9_players_bike FALSE
			SET_CAR_VISIBLE d9_players_bike TRUE
		ENDIF	

		SET_AREA_VISIBLE 9

		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_VISIBLE scplayer TRUE
			SET_CHAR_AREA_VISIBLE scplayer 9
		ENDIF

		DELETE_CHAR d9_enemy[4]
		CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  316.0887 983.9927 1958.1381 d9_enemy[4]
		SET_CHAR_HEADING d9_enemy[4] 90.0
		IF NOT IS_CHAR_DEAD d9_enemy[4]
			SET_CHAR_AREA_VISIBLE d9_enemy[4] 9
		ENDIF

		IF NOT IS_CHAR_DEAD d9_enemy[4]
			SET_CHAR_COORDINATES d9_enemy[4] 317.081 979.907 1960.108
			SET_CHAR_HEADING d9_enemy[4] 90.0
		ENDIF

		IF NOT IS_CAR_DEAD d9_players_bike 
			SET_VEHICLE_AREA_VISIBLE d9_players_bike 9
		ENDIF

		REQUEST_CAR_RECORDING 150
		REQUEST_CAR_RECORDING 152

		IF NOT IS_CAR_DEAD d9_players_bike

			SET_CAR_COORDINATES d9_players_bike 331.8417 1038.3140 1956.1768
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_INTO_CAR scplayer d9_players_bike
			ENDIF
			CLEAR_PRINTS
			SET_VEHICLE_AREA_VISIBLE d9_players_bike 9
			SET_CHAR_AREA_VISIBLE scplayer 9
			SET_CHAR_VISIBLE scplayer TRUE
		ENDIF

			SET_FIXED_CAMERA_POSITION 320.1115 1035.3733 1948.1537 0.0 0.0 0.0
			POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT


		d9_cut_timer = TIMERA + 1500

		d9_cut_flag = 5

	ENDIF

	IF d9_cut_flag = 5
		IF TIMERA > d9_cut_timer


			d9_cut_flag = 6
		ENDIF
	ENDIF

	IF d9_cut_flag = 6
		IF HAS_CAR_RECORDING_BEEN_LOADED 150
		AND HAS_CAR_RECORDING_BEEN_LOADED 152
			// Set bike to top of ramp.


			IF NOT IS_CAR_DEAD d9_players_bike
				START_PLAYBACK_RECORDED_CAR d9_players_bike 150
				SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR d9_players_bike
			ENDIF

			CAMERA_RESET_NEW_SCRIPTABLES		

			LVAR_FLOAT d9_ground_x d9_ground_y d9_ground_z
			
			LVAR_INT d9_prime_recording d9_first_barrel_time

			LVAR_INT d9_percent
			LVAR_FLOAT d9_percentF

			
			d9_cut_flag = 7
		ENDIF
	ENDIF

	IF d9_cut_flag = 7
		REQUEST_MODEL gun_para
		d9_cut_timer = TIMERA + 2200
		
		DO_FADE 600 FADE_IN

		d9_cut_flag = 8
	ENDIF

	IF d9_cut_flag = 8
		IF TIMERA > d9_cut_timer
			TASK_LEAVE_ANY_CAR scplayer
			d9_cut_flag = 9
			d9_cut_timer = TIMERA + 300	
		ENDIF
	ENDIF


		

			

		
		

			 // Your trapped on the plane. Locate the guard wearing a parachute then destroy the plane with your satchel charge.
			 // The satchel charge will destroy the plane in seconds. Plant it to the rear of the plane to maximise your escape time.




	IF d9_cut_flag > 0
	AND d9_cut_flag < 20
		GOSUB drop_barrels
	ENDIF



		IF d9_barrel_set_to_drop = 0
			IF TIMERA > d9_first_barrel_time
				d9_barrel_set_to_drop = 1
//				d9_drop_a_barrel = TIMERA + 10000
			ENDIF
		ENDIF




  
//317.7203 1027.3005 1949.0709 317.3408 1026.3773 1949.1305 
//317.7203 1027.3005 1949.0709 317.1811 1027.9913 1948.5891 



		// camera looks at bike on ramp
		IF d9_cut_flag = 9
			IF TIMERA > d9_cut_timer
				IF NOT IS_CAR_DEAD d9_plane
					DELETE_CAR d9_plane
				ENDIF	
		   		SET_FIXED_CAMERA_POSITION 317.7203 1027.3005 1949.0709 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 316.9413 1026.6750 1949.0266 JUMP_CUT
				
				d9_cut_timer = TIMERA + 1000
				d9_cut_flag = 10
			ENDIF
		ENDIF

		// set camera to pan as bike drives up ramp
		IF d9_cut_flag = 10
			IF TIMERA > d9_cut_timer




					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 317.7203 1027.3005 1949.0709 317.7203 1027.3005 1949.0709 1000 TRUE
					CAMERA_SET_VECTOR_TRACK 316.9413 1026.6750 1949.0266 317.3408 1026.3773 1949.1305 1000 TRUE

				
				d9_cut_flag = 11
				d9_cut_timer = TIMERA + 1500
			ENDIF
		ENDIF


		// start playback of bike, cam pans to barrel rolling down plane
		IF d9_cut_flag = 11

			d9_percent = d9_cut_timer - TIMERA
			d9_percentF =# d9_percent
			d9_percentF /= 2000.0
			d9_percentF -= 1.0
			ABS d9_percentF
			GET_OBJECT_COORDINATES d9_barrel[5] d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0]

			IF d9_barrel_y[0] > 1020.2140
				IF NOT IS_CAR_DEAD d9_players_bike
					START_PLAYBACK_RECORDED_CAR d9_players_bike 152
				ENDIF
				TASK_DIVE_AND_GET_UP scplayer 1.0 0.0 1000
				PRINT_NOW DES9_22 3500 1
				d9_cut_flag = 12
				d9_cut_timer = TIMERA + 2000
					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 317.7203 1027.3005 1949.0709 317.7203 1027.3005 1949.0709 2000 TRUE
					CAMERA_SET_VECTOR_TRACK 317.3408 1026.3773 1949.1305 317.1811 1027.9913 1948.5891 2000 TRUE
			ENDIF


			d9_x = d9_barrel_x[0] - 317.5107
			d9_y = d9_barrel_y[0] - 1024.8152 
			d9_z = d9_barrel_z[0] - 1949.3497

			d9_x *= d9_percentF
			d9_y *= d9_percentF
			d9_z *= d9_percentF

			d9_x += 317.5107
			d9_y += 1024.8152
			d9_z += 1949.3497
			
//			POINT_CAMERA_AT_POINT d9_x d9_y d9_z JUMP_CUT

		ENDIF

		// player says "What the fuck...?"
		IF d9_cut_flag = 12
			GET_OBJECT_COORDINATES d9_barrel[5] d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0]
//			POINT_CAMERA_AT_POINT d9_barrel_x[0] d9_barrel_y[0] d9_barrel_z[0] JUMP_CUT
			IF TIMERA > d9_cut_timer
				d9_cut_flag = 13
				CLEAR_PRINTS
//				PRINT DES9_23 2000 1
				IF NOT IS_CHAR_DEAD d9_enemy[4]
					TASK_SAY d9_enemy[4] CONTEXT_GLOBAL_GENERIC_INSULT_MALE
				ENDIF
			ENDIF
		ENDIF


		IF d9_cut_flag = 13
			IF TIMERA > d9_cut_timer

				CAMERA_RESET_NEW_SCRIPTABLES
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 318.1163 1024.7913 1948.7294
				ELSE
					SET_CHAR_COORDINATES scplayer 318.1163 1024.7913 1948.7294
				ENDIF

				SET_CHAR_HEADING scplayer 121.0

				

				IF NOT IS_CAR_DEAD d9_players_bike
					IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_players_bike
						STOP_PLAYBACK_RECORDED_CAR d9_players_bike						
					ENDIF
					DELETE_CAR d9_players_bike
					MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
				ENDIF

				SET_CHAR_HEADING scplayer 225.0

				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_COORDINATES d9_enemy[4] 317.4565 980.5405 1958.9347
				ENDIF

				IF NOT IS_CAR_DEAD d9_players_bike
					IF IS_PLAYBACK_GOING_ON_FOR_CAR d9_players_bike
						STOP_PLAYBACK_RECORDED_CAR d9_players_bike
					ENDIF					
				ENDIF

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
		

				PRINT DES9_25 4000 1

				IF NOT IS_CAR_DEAD d9_plane
					DELETE_CAR d9_plane
				ENDIF

				IF IS_PLAYER_PLAYING player1
					DISABLE_PLAYER_SPRINT player1 TRUE
				ENDIF

				CREATE_FX_SYSTEM cloudfast 315.2994 1028.1514 1936.4642 TRUE des_cloud_FX
				PLAY_FX_SYSTEM des_cloud_FX

				DELETE_CHAR d9_enemy[0]
				DELETE_CHAR d9_enemy[1]
				DELETE_CHAR d9_enemy[2]
				DELETE_CHAR d9_enemy[3]

				
				CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  313.4011 1012.3696 1951.5879 d9_enemy[0]
				CREATE_CHAR PEDTYPE_MISSION3 BMYMIB 312.5008 1004.9619 1953.2974 d9_enemy[1]
				CREATE_CHAR PEDTYPE_MISSION3 WMOMIB  318.7976 988.7245 1957.0443 d9_enemy[2]
				CREATE_CHAR PEDTYPE_MISSION3 BMYMIB 313.1324 991.6313 1956.3735 d9_enemy[3]


				IF NOT IS_CAR_DEAD d9_players_bike
					FREEZE_CAR_POSITION d9_players_bike FALSE
					SET_CAR_VISIBLE d9_players_bike TRUE
				ENDIF
				SET_CHAR_VISIBLE scplayer TRUE


				
				SET_CHAR_HEADING d9_enemy[0] 288.4161
				SET_CHAR_HEADING d9_enemy[1] 298.9878
				SET_CHAR_HEADING d9_enemy[2] 85.3329 
				SET_CHAR_HEADING d9_enemy[3] 308.2083	


				IF NOT IS_CHAR_DEAD d9_enemy[0]
					SET_CHAR_AREA_VISIBLE d9_enemy[0] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[1]
					SET_CHAR_AREA_VISIBLE d9_enemy[1] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[2]
					SET_CHAR_AREA_VISIBLE d9_enemy[2] 9
				ENDIF
				IF NOT IS_CHAR_DEAD d9_enemy[3]
					SET_CHAR_AREA_VISIBLE d9_enemy[3] 9
				ENDIF



				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_COORDINATES d9_enemy[4] 317.881 979.907 1960.108
					IF NOT HAS_CHAR_GOT_WEAPON d9_enemy[4] WEAPONTYPE_PARACHUTE
						GIVE_WEAPON_TO_CHAR d9_enemy[4] WEAPONTYPE_PARACHUTE 1
					ENDIF
				ENDIF

				d9_flag = 4
				d9_SATCHEL_planted = 0				
				d9_enemy_stops_rolling_barrels = 0
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_PROOFS scplayer FALSE TRUE TRUE FALSE FALSE
				ENDIF
			ENDIF
			
		ENDIF		
   



RETURN

*/

scene_2:

	LVAR_INT d9_para_blip d9_player_has_chute des_cloud_FX 
	LVAR_FLOAT d9_pickup_x d9_pickup_y d9_pickup_z
//	LVAR_INT d9_countdown
	LVAR_INT d9_SATCHEL_planted



//	VIEW_INTEGER_VARIABLE d9_SATCHEL_planted d9_SATCHEL_planted 


		GET_CHAR_COORDINATES scplayer player_x player_y player_z

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
			IF NOT IS_CAR_DEAD d9_players_bike
				START_RECORDING_CAR d9_players_bike 152
			ENDIF
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
			IF NOT IS_CAR_DEAD d9_players_bike
				IF IS_RECORDING_GOING_ON_FOR_CAR d9_players_bike
					STOP_RECORDING_CAR d9_players_bike
				ENDIF
			ENDIF
		ENDIF



//		IF IS_CHAR_SHOOTING_IN_AREA scplayer 308.4034 1065.3812 1942.6986 325.2043 949.9332 1980.3237
//			
//		ENDIF 

		LVAR_INT building_hit




		IF d9_SATCHEL_planted = 0
			IF IS_LAST_BUILDING_MODEL_SHOT_BY_PLAYER Player1 cargo_test
			OR IS_LAST_BUILDING_MODEL_SHOT_BY_PLAYER Player1 cargo_stuff
			OR IS_LAST_BUILDING_MODEL_SHOT_BY_PLAYER Player1 cargo_store
				CLEAR_LAST_BUILDING_MODEL_SHOT_BY_PLAYER player1
				ADD_EXPLOSION 316.0 1000.0 1954.6  EXPLOSION_HELI 
			ENDIF
			IF IS_EXPLOSION_IN_AREA -1 325.0714 967.9350 1971.8379 310.7184 1047.5745 1939.6830 //was explosion_grenade
				d9_SATCHEL_planted = 1
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				ENDIF
				d9_float1 = 972.5745
				d9_int1 = 0

				WHILE d9_float1 <= 1050.5745
					d9_float2 = d9_float1 - 5.0

					IF IS_EXPLOSION_IN_AREA -1 325.0714 d9_float1 1971.8379 310.7184 d9_float2 1939.6830 //was explosion grenadde
						d9_area_explode[d9_int1] = 1
					ENDIF
						  
					d9_int1 ++
					d9_float1 += 5.0
				ENDWHILE

			ENDIF
		ENDIF

		IF d9_SATCHEL_planted = 1
			GOSUB d9_boom
		ENDIF
 

		IF d9_player_has_chute = 0
			IF IS_CHAR_DEAD d9_enemy[4]
				PRINT DES9_27 4000 1
				d9_player_has_chute = 1
			ENDIF
		ENDIF


		IF d9_player_has_chute = 1
			IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
				SET_CURRENT_CHAR_WEAPON  scplayer WEAPONTYPE_PARACHUTE
				
				CLEAR_PRINTS

				IF d9_SATCHEL_planted = 0
					PRINT DES9_A1 4000 1  // ~s~You have the parachute.
					PRINT DES9_A2 4000 1  // ~s~Place a satchel charge on the plane then get out the back.
				ELSE
					PRINT DES9_26 4000 1  // ~s~You have the parachute, now get the hell out of there!
				ENDIF
				
				d9_player_has_chute = 2
			ENDIF
		ENDIF  

		IF detonated = 0
			IF player_z < 1942.27
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				ENDIF
				LVAR_INT detonated
				detonated = 1
				USE_DETONATOR
			ENDIF
		ENDIF

		IF d9_player_has_chute = 2
			IF player_z < 1940.27
//			OR player_y > 1039.38
				
				IF d9_SATCHEL_planted = 1			
					IF NOT IS_CHAR_DEAD scplayer
		//				DELETE_OBJECT d9_parachute
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE 
						
						KILL_FX_SYSTEM des_cloud_FX
						d9_flag = 9
						d9_cut_flag = 0
						player_fall_safe = 1

					ENDIF	
				ELSE
					CLEAR_PRINTS 
					PRINT DES9_39 4000 1  // ~r~You were supposed to destroy the plane!
					PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
					GOSUB mission_cleanup_desert9

					SET_AREA_VISIBLE 0
					GOTO game_end
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD d9_enemy[4]
			GET_CHAR_COORDINATES d9_enemy[4] d9_x d9_y d9_z
			IF d9_z < 1940.0
			AND NOT IS_CHAR_IN_AIR scplayer			
				CLEAR_PRINTS
				PRINT DES9_29 5000 1
				PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
				REMOVE_PICKUP para_pickup

				WAIT 1000
				
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				SET_AREA_VISIBLE 0

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 408.6996 2538.2720 15.6057
				
				SET_CHAR_HEADING scplayer 139.0

				GOSUB mission_cleanup_desert9

				WAIT 1500

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE


				GOTO game_end 		
			ENDIF
		ENDIF

		IF player_z < 1938.0
		AND NOT IS_CHAR_DEAD scplayer
			CLEAR_PRINTS

			IF d9_player_has_chute < 2
//				PRINT DES9_30 5000 1
//				GOSUB mission_desert9_failed
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				SET_AREA_VISIBLE 0
				SET_CHAR_AREA_VISIBLE scplayer 0

				LOAD_SCENE 125.1739 1074.0673 12.0
				
				REQUEST_MODEL ADMIRAL
				REQUEST_MODEL WFYRI
				REQUEST_ANIMATION PARACHUTE

				WHILE NOT HAS_MODEL_LOADED ADMIRAL
				OR NOT HAS_MODEL_LOADED WFYRI
				OR NOT HAS_ANIMATION_LOADED PARACHUTE
					WAIT 0
				ENDWHILE

				SET_FIXED_CAMERA_POSITION 125.1739 1074.0673 12.8138 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 124.5910 1073.2697 12.9688 JUMP_CUT
				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL player1 OFF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 122.3556 1069.8918 99.6832

				LVAR_INT d9_acar d9_achar
				CREATE_CAR ADMIRAL 119.3556 1069.8918 13.6650 d9_acar
				CREATE_CHAR_AS_PASSENGER d9_acar PEDTYPE_CIVMALE WFYRI 0 d9_achar

				MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
				MARK_MODEL_AS_NO_LONGER_NEEDED ADMIRAL
				MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL
				MARK_MODEL_AS_NO_LONGER_NEEDED BOMB

				SET_CAR_HEADING d9_acar 180.0

				SET_CHAR_COORDINATES scplayer 119.30 1070.47 189.3266
				SET_CHAR_VELOCITY scplayer 0.0 0.0 0.0

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				SET_CHAR_COORDINATES scplayer 119.30 1070.47 80.3266
				SET_CHAR_VELOCITY scplayer 0.0 0.0 0.0  

				LVAR_INT d9_dead_now
				LVAR_FLOAT d9_float
				WHILE NOT IS_CHAR_DEAD scplayer

					GET_CHAR_HEIGHT_ABOVE_GROUND scplayer d9_float
					IF d9_float < 4.8
					AND d9_dead_now = 0
						d9_dead_now = 1
						TASK_DIE_NAMED_ANIM scplayer FALL_skydive_die PARACHUTE 1000.0 0
						IF NOT IS_CAR_DEAD d9_acar
							POP_CAR_DOOR d9_acar FRONT_LEFT_DOOR TRUE
							POP_CAR_DOOR d9_acar REAR_LEFT_DOOR TRUE
							POP_CAR_DOOR d9_acar FRONT_RIGHT_DOOR TRUE
							BURST_CAR_TYRE d9_acar FRONT_LEFT_WHEEL
							BURST_CAR_TYRE d9_acar FRONT_RIGHT_WHEEL
							BURST_CAR_TYRE d9_acar REAR_LEFT_WHEEL
							DAMAGE_CAR_PANEL d9_acar WINDSCREEN_PANEL
							DAMAGE_CAR_PANEL d9_acar FRONT_LEFT_PANEL
							DAMAGE_CAR_PANEL d9_acar REAR_BUMPER
							DAMAGE_CAR_PANEL d9_acar REAR_BUMPER
							APPLY_FORCE_TO_CAR d9_acar 0.0 0.0 -0.15 0.0 0.0 0.0							
							IF NOT IS_CHAR_DEAD d9_achar
								CLEAR_CHAR_TASKS d9_achar
								TASK_LEAVE_CAR_AND_FLEE d9_achar d9_acar 119.3556 1069.8918 13.6650
							ENDIF
						ENDIF
					ENDIF

					WAIT 0

				ENDWHILE

				REMOVE_ANIMATION PARACHUTE
			ENDIF
		ENDIF

		IF player_y > 1033.0
			SHAKE_CAM 40
		ELSE 
			SHAKE_CAM 10
		ENDIF
		GOSUB drop_barrels
RETURN



cutscene_3:


//CLEAR_ONSCREEN_TIMER d9_countdown
SWITCH_WIDESCREEN ON													
//SET_PLAYER_CONTROL player1 OFF



//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

LVAR_INT d9_jumped

para_control_off = 1

d9_cut3_loop:

	IF d9_jumped = 0
		SET_AREA_VISIBLE 0		
		IF NOT IS_CAR_DEAD d9_plane
			DELETE_CAR d9_plane
		ENDIF
//		SWITCH_WIDESCREEN ON
//		SET_PLAYER_CONTROL player1 OFF

		CREATE_CAR ANDROM 316.2120 1003.4950 1957.5293 d9_plane
		START_PLAYBACK_RECORDED_CAR d9_plane 134
		SET_CAR_HEADING d9_plane 180.0
		ATTACH_CAMERA_TO_CHAR scplayer 0.0 2.0 -2.0 0.0 0.0 0.0 0.0 JUMP_CUT
		d9_cut_timer = TIMERA + 4000
		d9_jumped = 1
	ENDIF

	IF d9_jumped = 1
		IF TIMERA > d9_cut_timer
//			SET_PLAYER_CONTROL player1 ON
			para_control_off = 0
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			RETURN
		ENDIF
	ENDIF

	WAIT 0


GOTO d9_cut3_loop



RETURN

parachuting:


SET_AREA_VISIBLE 0


d9_parachute_loop:
	WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
            GOTO mission_desert9_passed  
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_T
//	para_freefall_Vz = 0.0
ENDIF
//
//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Y
//	para_control_off = 0
//ENDIF



//	IF player_landed = 1
//		RETURN
//	ENDIF

GOTO d9_parachute_loop

RETURN







// player dies on plane

cutscene_4:

	IF d9_cut_flag = 0

		LVAR_INT d9_track_plane
		CREATE_CAR ANDROM 339.9034 2499.8049 15.4884 d9_track_plane
		START_PLAYBACK_RECORDED_CAR d9_track_plane 134
		SET_PLAYBACK_SPEED d9_track_plane 1.0



		d9_cut_timer = TIMERA + 1000
		d9_cut_flag = 1
	ENDIF

	IF d9_cut_flag = 1
		IF TIMERA > d9_cut_timer

//			PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
			CLEAR_PRINTS
			PRINT DES9_42 5000 1
			SET_CHAR_VISIBLE scplayer FALSE
			SET_AREA_VISIBLE 0
			CREATE_CAR ANDROM 339.9034 2499.8049 15.4884 d9_plane
			SET_CAR_PROOFS d9_PLANE TRUE TRUE TRUE TRUE TRUE
			START_PLAYBACK_RECORDED_CAR d9_plane 134
			SET_CAR_HEALTH d9_plane 350

			IF NOT IS_CAR_DEAD d9_track_plane				
				SET_PLAYBACK_SPEED d9_track_plane 0.5
				ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE d9_track_plane 15.0 15.0 10.0 d9_plane 15.0 JUMP_CUT
				SET_CAR_VISIBLE d9_track_plane FALSE
			ENDIF

//			CLEAR_ONSCREEN_TIMER d9_countdown
			SWITCH_WIDESCREEN ON													
			SET_PLAYER_CONTROL player1 OFF

			
			make_explosions = 1
			d9_cut_flag = 3
			d9_cut_timer = TIMERA + 5000
		ENDIF
	ENDIF

   	IF make_explosions = 1
		IF TIMERA > new_explosion_time
			IF NOT IS_CAR_DEAD d9_plane
				GET_CAR_COORDINATES d9_plane x y z
				IF explosion_count < 6
					ADD_EXPLOSION x y z  EXPLOSION_HELI
				ELSE
					ADD_EXPLOSION_NO_SOUND x y z  EXPLOSION_HELI
				ENDIF
				explosion_count ++
				GENERATE_RANDOM_INT_IN_RANGE 100 250 new_explosion_time
				new_explosion_time += TIMERA
			ENDIF
		ENDIF
	ENDIF


	IF d9_cut_flag = 3
		IF TIMERA > d9_cut_timer
			d9_cut_flag = 4
			DO_FADE 1000 FADE_OUT
			d9_cut_timer = TIMERA + 1000
		ENDIF
	ENDIF

	IF d9_cut_flag = 4
		IF NOT GET_FADING_STATUS
			FORCE_DEATH_RESTART
			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT
			GOTO game_over
//		IF TIMERA > d9_cut_timer
//			DO_FADE 800 FADE_IN
//			SET_PLAYER_CONTROL player1 ON
//			SWITCH_WIDESCREEN OFF
//			SET_CAMERA_BEHIND_PLAYER
//			RESTORE_CAMERA_JUMPCUT
		ENDIF
	ENDIF


	//SET_PLAYER_CONTROL player1 OFF

RETURN



// player fails to make it on to plane in time
cutscene_5:

	IF d9_cut_flag = 0
		IF NOT IS_CAR_DEAD d9_plane
//			SET_PLAYBACK_SPEED d9_plane 0.0
			SET_FIXED_CAMERA_POSITION -158.5012 2492.1609 31.0353 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -157.5606 2492.4451 31.2209 JUMP_CUT
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			SET_PLAYBACK_SPEED d9_plane 1.0


		IF NOT IS_CAR_DEAD d9_enemy_car[0]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[0]
				DELETE_CAR d9_enemy_car[0]
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD d9_track_plane
			DELETE_CAR d9_track_plane
		ENDIF

		IF NOT IS_CAR_DEAD d9_enemy_car[1]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[1]
				DELETE_CAR d9_enemy_car[1]
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD d9_enemy_car[2]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[2]
				DELETE_CAR d9_enemy_car[2]
			ENDIF
		ENDIF

		d9_i = 0
		WHILE d9_i < 8	
				DELETE_CHAR d9_enemy[d9_i]
   
			d9_i ++
		ENDWHILE

		d9_i = 0
		WHILE d9_i < 15
			DELETE_OBJECT d9_box[d9_i]	   
			d9_i ++
		ENDWHILE

		d9_i = 0
		WHILE d9_i < 5
			DELETE_OBJECT d9_crate[d9_i]	   
			d9_i ++
		ENDWHILE







			d9_cut_flag = 1
			d9_cut_timer = TIMERA + 3000
		ENDIF
	ENDIF

	IF d9_cut_flag = 1
		IF TIMERA > d9_cut_timer
			PRINT DES9_32 4000 1
			PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			GOTO game_over
		ENDIF
	ENDIF
			

RETURN











// cut of player freefalling from plane exploding in background

cutscene_6:

	VAR_INT cut_timerr start_timerr
	LVAR_FLOAT move_x move_y move_z cam_start_x cam_start_y cam_start_z cam_end_x cam_end_y cam_end_z percent_done
	LVAR_FLOAT point_start_x point_start_y point_start_z point_end_x point_end_y point_end_z

	cut_timerr = TIMERA - start_timerr

//	VIEW_INTEGER_VARIABLE cut_timerr cut_timerr

	IF d9_cut_flag = 0

		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
		ENDIF

		start_timerr = TIMERA
		cut_timerr = TIMERA - start_timerr

//		LVAR_INT d9_track_plane
		GET_CHAR_VELOCITY scplayer x y z
		CREATE_CAR ANDROM 339.9034 2499.8049 15.4884 d9_track_plane
		START_PLAYBACK_RECORDED_CAR d9_track_plane 134
		SET_PLAYBACK_SPEED d9_track_plane 0.5
		
		SET_CHAR_COORDINATES scplayer 177.9967 1099.6215 1977.4908
		SET_CHAR_HEADING scplayer 180.0
		SET_CHAR_VELOCITY scplayer x -30.0 z
		SET_CAR_VISIBLE d9_track_plane TRUE
		CONTROL_MOVABLE_VEHICLE_PART d9_track_plane 1.0
		SET_AREA_VISIBLE 0
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE

		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL player1 OFF

//		FREEZE_CHAR_POSITION scplayer TRUE
//		SET_PLAYBACK_SPEED d9_track_plane 0.0

//		ATTACH_CAMERA_TO_CHAR scplayer 0.0 2.0 -0.5 0.0 0.0 0.0 0.0 JUMP_CUT 


		d9_cut_timer = TIMERA + 5300

		point_start_x = 175.5399 
		point_start_y = 1097.8403 
		point_start_z = 1978.9117

		point_end_x =	185.4931  
		point_end_y = 1028.0302 						     
		point_end_z = 1763.1604

		cam_start_x = 175.0676 
		cam_start_y = 1097.0249 
		cam_start_z = 1979.2462 

		cam_end_x =	185.4931  
		cam_end_y = 1028.0302 
		cam_end_z = 1763.1604  		
		
//		CAMERA_SET_VECTOR_TRACK 175.2589 1100.9034 1976.9786 188.1609 1026.7855 1848.6123 4911 FALSE
		d9_cut_flag = 999
		LVAR_INT make_explosions new_explosion_time	show_mission_passed	explosion_count
		make_explosions = 1

	ENDIF

	IF make_explosions = 1
		IF TIMERA > new_explosion_time
			IF NOT IS_CAR_DEAD d9_track_plane
				GET_CAR_COORDINATES d9_track_plane x y z
				IF explosion_count < 6
					ADD_EXPLOSION x y z EXPLOSION_HELI
				ELSE
					ADD_EXPLOSION_NO_SOUND x y z EXPLOSION_HELI
				ENDIF
				explosion_count ++
				GENERATE_RANDOM_INT_IN_RANGE 100 250 new_explosion_time
				new_explosion_time += TIMERA
			ENDIF
		ENDIF
	ENDIF

	IF NOT d9_player_killed = 1
		IF show_mission_passed = 0
			IF cut_timerr > 4070
				GOSUB mission_desert9_passed
				show_mission_passed = 1
			ENDIF
		ENDIF
	ENDIF

	IF cut_timerr > 8070
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		GOTO game_over 
	ENDIF


	IF d9_cut_flag = 999


//				CAMERA_RESET_NEW_SCRIPTABLES
//				CAMERA_PERSIST_TRACK TRUE                   
//				CAMERA_PERSIST_POS TRUE


	   
//		move_X = cam_end_x - cam_start_x
//		move_y = cam_end_y - cam_start_y
//		move_z = cam_end_z - cam_start_z
//
		percent_done =# cut_timerr
		percent_done /= 8070.0

		
//
//		move_x *= percent_done
//		move_y *= percent_done
//		move_z *= percent_done
//
//		move_x += cam_start_x
//		move_y += cam_start_y
//		move_z += cam_start_z

		GET_CHAR_COORDINATES scplayer x y z

		move_x = x

		move_z = percent_done * 7.5
		move_z += z
		move_z -= 2.5

		move_y = y - 5.0


//		SET_FIXED_CAMERA_POSITION move_x move_y move_z 0.0 0.0 0.0

		move_X = point_end_x - point_start_x
		move_y = point_end_y - point_start_y
		move_z = point_end_z - point_start_z

		percent_done =# cut_timerr
		percent_done /= 8070.0

		move_x *= percent_done
		move_y *= percent_done
		move_z *= percent_done

		move_x += point_start_x
		move_y += point_start_y
		move_z += point_start_z

		

//		CAMERA_SET_VECTOR_MOVE move_x move_Y move_Z move_x move_Y point_end_Z 8070 TRUE
//		CAMERA_SET_VECTOR_TRACK 560.3911 -1234.2836 17.4052 557.8772 -1234.4658 16.1925 2000 TRUE

		ATTACH_CAMERA_TO_CHAR scplayer 0.0 5.0 1.5 0.0 -5.0 -1.5 0.0 JUMP_CUT

//		POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT
		d9_cut_flag = 1020
	ENDIF

	IF D9_CUT_FLAG = 1020
		SET_CHAR_HEADING scplayer 180.0
	ENDIF


//182.9492 1090.7356 1973.7246 182.6102 1091.6763 1973.7229

	IF d9_cut_flag = 40
			VAR_INT d9_counting
 			d9_int1 = TIMERA
//			VIEW_INTEGER_VARIABLE d9_counting d9_counting
			d9_cut_flag = 41

	ENDIF

	IF NOT d9_player_killed = 1
		IF d9_cut_flag = 41
			d9_counting = TIMERA - d9_int1
			IF TIMERA > d9_cut_timer
				d9_cut_flag = 42
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				GOSUB mission_desert9_passed
				GOTO game_over
			ENDIF
		ENDIF
	ENDIF

	IF d9_cut_flag = 1
		IF TIMERA > d9_cut_timer

//			PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
			CLEAR_PRINTS
			PRINT DES9_42 5000 1
			SET_CHAR_VISIBLE scplayer FALSE
			SET_AREA_VISIBLE 0
			CREATE_CAR ANDROM 339.9034 2499.8049 15.4884 d9_plane
			SET_CAR_PROOFS d9_PLANE TRUE TRUE TRUE TRUE TRUE
			START_PLAYBACK_RECORDED_CAR d9_plane 134
			SET_CAR_HEALTH d9_plane 350

			IF NOT IS_CAR_DEAD d9_track_plane				
				SET_PLAYBACK_SPEED d9_track_plane 0.5
				ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE d9_track_plane 15.0 15.0 10.0 d9_plane 15.0 JUMP_CUT
				SET_CAR_VISIBLE d9_track_plane FALSE
			ENDIF

//			CLEAR_ONSCREEN_TIMER d9_countdown
			SWITCH_WIDESCREEN ON													
			SET_PLAYER_CONTROL player1 OFF
			ADD_EXPLOSION 179.0872 1127.1095 1959.8048 EXPLOSION_HELI
			

			d9_cut_flag = 10
			d9_cut_timer = TIMERA + 80
		ENDIF
	ENDIF

	IF d9_cut_flag = 10
		ADD_EXPLOSION 178.0018 1120.3190 1963.4852 EXPLOSION_HELI
		d9_cut_timer = TIMERA + 80
		d9_cut_flag = 11																				   
	ENDIF
																										   
	IF d9_cut_flag = 11
		ADD_EXPLOSION 177.9817 1114.1306 1967.3561 EXPLOSION_HELI										   
		d9_cut_timer = TIMERA + 80
		d9_cut_flag = 12
	ENDIF
	
	IF d9_cut_flag = 12
		ADD_EXPLOSION 179.3860 1109.9189 1972.5035 EXPLOSION_HELI
		d9_cut_timer = TIMERA + 3760
		d9_cut_flag = 3
	ENDIF

	IF d9_cut_flag = 2
	ENDIF

	IF d9_cut_flag = 3
		IF TIMERA > d9_cut_timer
			d9_cut_flag = 4
			d9_cut_timer = TIMERA + 6000
		ENDIF
	ENDIF

	IF d9_cut_flag = 4
		IF TIMERA > d9_cut_timer

			FORCE_DEATH_RESTART
			GOTO game_over
		ENDIF
	ENDIF


	//SET_PLAYER_CONTROL player1 OFF

RETURN













fail_cam:

	SET_FIXED_CAMERA_POSITION -189.0987 2493.9573 66.7341 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -188.1982 2494.1050 66.3252 JUMP_CUT
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	IF NOT IS_CAR_DEAD d9_plane
//		POINT_CAMERA_AT_CAR d9_plane FIXED JUMP_CUT
	ENDIF

	d9_timer = TIMERA + 4000



	fail_cam_loop:

		WAIT 0

		IF d9_fail_flag = 0
			IF TIMERA > d9_timer
				DO_FADE 1000 FADE_OUT 
				d9_timer = TIMERA + 1000
				d9_fail_flag = 1
			ENDIF
		ENDIF

		IF d9_fail_flag = 1
			

			IF TIMERA > d9_timer

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				DO_FADE 0 FADE_IN
					PRINT des9_32 5000 1
				GOSUB mission_desert9_failed
				GOTO game_over
			ENDIF
		ENDIF
		
	GOTO fail_cam_loop



RETURN

drop_shit_from_plane:

	LVAR_INT d9_drop_boxes d9_drop_box_time[15]	ds9_int	d9_next_box	d9_box[15] d9_drop_object_type[15] ds9_i d9_dropcar
	LVAR_FLOAT d9_drop_x d9_drop_z d9_plane_speed d9_object_vel	d9_y_vel

	LVAR_INT d9_blip[15]


	IF d9_drop_boxes = 0
		d9_drop_box_time[0] = 2000
		d9_drop_box_time[1] = 2300
		d9_drop_box_time[2] = 2600

		d9_drop_box_time[3] = 4000
		d9_drop_box_time[4] = 4300
		d9_drop_box_time[5] = 4600

		d9_drop_box_time[6] = 6000
		d9_drop_box_time[7] = 6300
		d9_drop_box_time[8] = 6600

		d9_drop_box_time[9] = 8000
		d9_drop_box_time[10] = 8300
		d9_drop_box_time[11] = 8600

		d9_drop_box_time[12] = 10000
		d9_drop_box_time[13] = 10300
		d9_drop_box_time[14] = 10600

		d9_drop_object_type[0] = 1
		d9_drop_object_type[1] = 1
		d9_drop_object_type[2] = 1

		d9_drop_object_type[3] = 1
		d9_drop_object_type[4] = 1
		d9_drop_object_type[5] = 1

		d9_drop_object_type[6] = 1
		d9_drop_object_type[7] = 1
		d9_drop_object_type[8] = 0

		d9_drop_object_type[9] = 1
		d9_drop_object_type[10] = 1
		d9_drop_object_type[11] = 1

		d9_drop_object_type[12] = 1
		d9_drop_object_type[13] = 1
		d9_drop_object_type[14] = 1

		ds9_i = 0
		WHILE ds9_i < 15
			GENERATE_RANDOM_INT_IN_RANGE -500 500 ds9_int
			d9_drop_box_time[ds9_i] += TIMERA
			d9_drop_box_time[ds9_i] += ds9_int
//			GENERATE_RANDOM_INT_IN_RANGE -150 150 ds9_int	
//			d9_drop_box_time[ds9_i] += ds9_int
			ds9_i ++
			d9_drop_box_time[ds9_i] += TIMERA
			d9_drop_box_time[ds9_i] += ds9_int
//			GENERATE_RANDOM_INT_IN_RANGE -150 150 ds9_int	
//			d9_drop_box_time[ds9_i] += ds9_int			
			ds9_i ++
			d9_drop_box_time[ds9_i] += TIMERA
			d9_drop_box_time[ds9_i] += ds9_int	
//			GENERATE_RANDOM_INT_IN_RANGE -150 150 ds9_int	
//			d9_drop_box_time[ds9_i] += ds9_int
			ds9_i ++
		ENDWHILE


		d9_drop_boxes = 1
	ENDIF

	IF d9_drop_boxes = 1
		IF d9_next_box < 15 
			IF TIMERA > d9_drop_box_time[d9_next_box]

				LVAR_INT d9_int
				d9_int = 0
				WHILE	d9_int < 15
					IF DOES_OBJECT_EXIST d9_box[d9_int]
						IF NOT IS_OBJECT_ON_SCREEN d9_box[d9_int]
							GET_OBJECT_COORDINATES d9_box[d9_int] x y z
							IF player_x < x
								DELETE_OBJECT d9_box[d9_int]
							ENDIF
						ENDIF
					ENDIF
					d9_int ++
				ENDWHILE

				IF NOT IS_CAR_DEAD d9_plane
					GET_CAR_SPEED d9_plane d9_plane_speed 
				ENDIF
				d9_drop_x = d9_plane_x + 18.538
//				16.938
				d9_drop_z = 15.61
//				d9_plane_z - 0.904
				d9_object_vel = d9_plane_speed - 18.0
				d9_object_vel *= -1.0


				IF d9_drop_object_type[d9_next_box] = 1

					GENERATE_RANDOM_FLOAT_IN_RANGE -10.0 10.0 d9_y_vel

					CREATE_OBJECT kb_barrel d9_drop_x d9_plane_y d9_drop_z d9_box[d9_next_box]
					SET_OBJECT_HEADING d9_box[d9_next_box] 90.0
					SET_OBJECT_DYNAMIC d9_box[d9_next_box] FALSE
					SET_OBJECT_DYNAMIC d9_box[d9_next_box] TRUE

					SET_OBJECT_VELOCITY d9_box[d9_next_box] d9_object_vel d9_y_vel 0.0
//					SET_OBJECT_VELOCITY d9_box[d9_next_box] d9_object_vel d9_y_vel 0.0
					SET_OBJECT_ROTATION d9_box[d9_next_box] 0.0 0.0 0.0
					d9_rot_vel = d9_object_vel * 2.0
					ADD_TO_OBJECT_ROTATION_VELOCITY d9_box[d9_next_box] 0.0 d9_rot_vel 0.0

				ENDIF
				IF d9_drop_object_type[d9_next_box] = 2
					CREATE_CAR BOBCAT d9_drop_x d9_plane_y d9_drop_z d9_dropcar
					SET_CAR_HEADING d9_dropcar 270.0
					SET_CAR_FORWARD_SPEED d9_dropcar d9_object_vel
					SET_CAR_ROTATION_VELOCITY d9_dropcar 0.0 23.0 0.0
				ENDIF
				d9_next_box++
			ENDIF
		ENDIF
	ENDIF

RETURN

drop_barrels:

	IF d9_drop_barrel = 0
		d9_drop_barrel = 1

		d9_barrel_timer = TIMERA + 1000

		LVAR_FLOAT 	barrel_posX[4] barrel_posY[4] barrel_posZ[4] barrel_Y_offset barrel_Z_offset d9_barrel_x[20] d9_barrel_y[20] d9_barrel_z[20]
		LVAR_INT barrel_drop_time[20] barrel_time_offset[20]

		barrel_posX[0] = 316.465
		barrel_posY[0] = 981.694
		barrel_posZ[0] = 1958.96

		barrel_posX[1] = 315.248
		barrel_posY[1] = 981.694
		barrel_posZ[1] = 1958.96

		barrel_posX[2] = 316.465
		barrel_posY[2] = 981.909
		barrel_posZ[2] = 1959.92

		barrel_posX[3] = 315.248
		barrel_posY[3] = 981.909
		barrel_posZ[3] = 1959.92


		barrel_Y_offset = -0.996
		barrel_Z_offset = 0.232

		barrel_drop_time[0] = 3
		barrel_drop_time[1] = 4
		barrel_drop_time[2] = 4
		barrel_drop_time[3] = 5
		barrel_drop_time[4] = 6
		barrel_drop_time[5] = 1
		barrel_drop_time[6] = 3
		barrel_drop_time[7] = 3
		barrel_drop_time[8] = 4
		barrel_drop_time[9] = 5
		barrel_drop_time[10] = 2
		barrel_drop_time[11] = 2
		barrel_drop_time[12] = 6
		barrel_drop_time[13] = 6
		barrel_drop_time[14] = 7
		barrel_drop_time[15] = 6
		barrel_drop_time[16] = 6
		barrel_drop_time[17] = 7
		barrel_drop_time[18] = 7
		barrel_drop_time[19] = 8

		barrel_time_offset[0] = 0
		barrel_time_offset[1] = 0
		barrel_time_offset[2] = 300
		barrel_time_offset[3] = 0
		barrel_time_offset[4] = 0
		barrel_time_offset[5] = 0
		barrel_time_offset[6] = 0
		barrel_time_offset[7] = 0
		barrel_time_offset[8] = 0
		barrel_time_offset[9] = 0
		barrel_time_offset[10] = 0
		barrel_time_offset[11] = 0
		barrel_time_offset[12] = 300
		barrel_time_offset[13] = 0
		barrel_time_offset[14] = 300
		barrel_time_offset[15] = 0
		barrel_time_offset[16] = 300
		barrel_time_offset[17] = 0
		barrel_time_offset[18] = 300
		barrel_time_offset[19] = 0

	ENDIF

	IF d9_drop_barrel = 1


		LVAR_INT barrel_rack barrel_id barrel_blip[20] d9_i	barrel_move_flag[20] d9_barrel_set_to_drop d9_drop_a_barrel d9_this_drop_time d9_this_barrel_drop_time
		LVAR_INT d9_enemy_stops_rolling_barrels
	
		LVAR_FLOAT this_barrel enemy_x enemy_y enemy_z
	 

		barrel_id = 0
		barrel_rack = 0
		WHILE barrel_rack < 4
			this_barrel = 0.0
			WHILE this_barrel < 5.0

				
				
				d9_barrel_x[barrel_id] = barrel_posX[barrel_rack]
				d9_barrel_y[barrel_id] = this_barrel * barrel_Y_offset
				d9_barrel_y[barrel_id] += barrel_posY[barrel_rack] 
				d9_barrel_z[barrel_id] = this_barrel * barrel_Z_offset
				d9_barrel_z[barrel_id] += barrel_posZ[barrel_rack] 

			
				CREATE_OBJECT kb_barrel d9_barrel_x[barrel_id] d9_barrel_y[barrel_id] d9_barrel_z[barrel_id] d9_barrel[barrel_id]

	//			ADD_BLIP_FOR_OBJECT d9_barrel[barrel_id] barrel_blip[barrel_id] 

				d9_barrel_start_roll_time = TIMERA

				SET_OBJECT_ROTATION d9_barrel[barrel_id] 0.0 13.0 90.0
				SET_OBJECT_DYNAMIC d9_barrel[barrel_id] FALSE
				SET_OBJECT_DYNAMIC d9_barrel[barrel_id] TRUE
	//			SET_OBJECT_VELOCITY d9_barrel[barrel_id] 0.0 15.0 0.0

			//	SET_OBJECT_ROTATION d9_barrel 90.0 0.0 0.0
	//			SET_OBJECT_ROTATION_VELOCITY d9_barrel[barrel_id] -25.0 0.0 0.0

//				SET_OBJECT_COLLISION d9_barrel[barrel_id] FALSE
				FREEZE_OBJECT_POSITION d9_barrel[barrel_id] TRUE

				barrel_id ++
				this_barrel += 1.0

			ENDWHILE
			barrel_rack ++
		ENDWHILE


				

		d9_drop_barrel = 2

			
	//	SET_OBJECT_ROTATION d9_barrel 0.0 0.0 90.0
	ENDIF						   

	IF d9_drop_barrel = 2


		d9_i = 0
		GET_CHAR_COORDINATES scplayer player_x player_y player_z

		IF d9_enemy_stops_rolling_barrels = 0
			IF player_y <= 990.0
				d9_enemy_stops_rolling_barrels = 1
				IF NOT IS_CHAR_DEAD d9_enemy[4]
					SET_CHAR_RELATIONSHIP d9_enemy[4] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				ENDIF
			ENDIF
		ENDIF

		IF d9_enemy_attacks[0] = 0
			IF player_y < 1021.0
				IF NOT IS_CHAR_DEAD d9_enemy[0]
					SET_CHAR_RELATIONSHIP d9_enemy[0] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
					d9_enemy_attacks[0] = 1
				ENDIF
			ENDIF
		ENDIF

		IF d9_enemy_attacks[1] = 0
			IF player_y < 1011.0
				IF NOT IS_CHAR_DEAD d9_enemy[1]
					SET_CHAR_RELATIONSHIP d9_enemy[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
					d9_enemy_attacks[1] = 1
				ENDIF
			ENDIF
		ENDIF

		IF d9_enemy_attacks[2] = 0
			IF player_y < 1000.0
				IF NOT IS_CHAR_DEAD d9_enemy[2]
					SET_CHAR_RELATIONSHIP d9_enemy[2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
					d9_enemy_attacks[2] = 1
				ENDIF
			ENDIF
		ENDIF

		IF d9_enemy_attacks[3] = 0
			IF player_y < 1000.0
				IF NOT IS_CHAR_DEAD d9_enemy[3]
					SET_CHAR_RELATIONSHIP d9_enemy[3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
					d9_enemy_attacks[3] = 1
				ENDIF
			ENDIF
		ENDIF


				
			

		
		IF d9_enemy_stops_rolling_barrels = 0
			IF player_y < 1021.0
			OR d9_barrel_set_to_drop = 1
				IF TIMERA > d9_drop_a_barrel
					d9_drop_a_barrel = TIMERA + 6500
					d9_this_drop_time = TIMERA
					d9_barrel_set_to_drop ++
					IF NOT IS_CHAR_DEAD d9_enemy[4]
						IF HAS_ANIMATION_LOADED AIRPORT
							TASK_PLAY_ANIM d9_enemy[4] thrw_barl_thrw AIRPORT 4.0 FALSE 0 0 0 3000
						ELSE
							REQUEST_ANIMATION AIRPORT
						ENDIF
					ENDIF
					
				ENDIF
			ENDIF
		ENDIF




		WHILE d9_i < 20
			IF barrel_drop_time[d9_i] <= d9_barrel_set_to_drop

				 
				IF barrel_move_flag[d9_i] = 0
					d9_this_barrel_drop_time = d9_this_drop_time + barrel_time_offset[d9_i]
					IF TIMERA > d9_this_barrel_drop_time
						barrel_move_flag[d9_i] = 1
						IF d9_i = 5
							SET_OBJECT_COORDINATES d9_barrel[5] 315.1264 986.6496 1957.9255
							barrel_move_flag[d9_i] = 6
						ENDIF
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 1
					IF d9_i < 10
						barrel_move_flag[d9_i] = 2
					ELSE
						barrel_move_flag[d9_i] = 3
					ENDIF
					SET_OBJECT_COLLISION d9_barrel[d9_i] FALSE			   
				ENDIF

				IF barrel_move_flag[d9_i] = 2
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i] 981.691 1959.418 0.0 0.183 0.0412 FALSE
						barrel_move_flag[d9_i] = 4
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 3
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]  981.909 1960.339 0.0 0.183 0.0412 FALSE
						barrel_move_flag[d9_i] = 5
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 4
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]  984.242 1958.532 0.0 0.287 0.1 FALSE
						barrel_move_flag[d9_i] = 5
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 5
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]   984.242 1958.532 0.0 0.21 0.162 FALSE
						barrel_move_flag[d9_i] = 6
					ENDIF
				ENDIF


				IF barrel_move_flag[d9_i] = 6
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]  1027.961 1948.424 0.0 0.244 0.0564 FALSE
						barrel_move_flag[d9_i] = 7
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 7
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]  1039.4939 1942.6927 0.0 0.432 0.2145 FALSE
						barrel_move_flag[d9_i] = 8
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 8
					IF SLIDE_OBJECT d9_barrel[d9_i] d9_barrel_x[d9_i]  1086.9092 1908.6315 0.0 0.882 0.6645 FALSE
						barrel_move_flag[d9_i] = 9
					ENDIF
				ENDIF

				IF barrel_move_flag[d9_i] = 9
					SET_OBJECT_VISIBLE d9_barrel[d9_i] FALSE
					barrel_move_flag[d9_i] = 10
				ENDIF


				IF TIMERA > d9_dive_time
					IF NOT barrel_move_flag[d9_i] < 2
					AND NOT barrel_move_flag[d9_i] >= 8
						GET_OBJECT_COORDINATES d9_barrel[d9_i] d9_barrel_x[d9_i] d9_barrel_y2 d9_barrel_z2
						d9_x = player_x - d9_barrel_x[d9_i]
						d9_y = player_y - d9_barrel_y2
						d9_z = player_z - d9_barrel_z2

						ABS d9_x

						IF d9_x < 0.6
							IF d9_y < 0.5
							AND d9_y > 0.0
								IF d9_z < 0.6
					//				TASK_DIVE_AND_GET_UP scplayer 0.0 1.0 1000
									CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
									TASK_FALL_AND_GET_UP scplayer 0 0
									d9_dive_time = TIMERA + 1500
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF


				LVAR_INT d9_v d9_health
				d9_v = 0

				WHILE d9_v < 4
					IF NOT IS_CHAR_DEAD d9_enemy[d9_v]
						IF NOT barrel_move_flag[d9_i] < 2
						AND NOT barrel_move_flag[d9_i] >= 8
							GET_CHAR_COORDINATES d9_enemy[d9_v] enemy_x enemy_y enemy_z	
							GET_OBJECT_COORDINATES d9_barrel[d9_i] d9_barrel_x[d9_i] d9_barrel_y2 d9_barrel_z2
							d9_x = enemy_x - d9_barrel_x[d9_i]
							d9_y = enemy_y - d9_barrel_y2
							d9_z = enemy_z - d9_barrel_z2

							ABS d9_x

							IF d9_x < 0.6
								IF d9_y < 0.5
								AND d9_y > 0.0
									IF d9_z < 0.6
						//				TASK_DIVE_AND_GET_UP scplayer 0.0 1.0 1000
										CLEAR_CHAR_TASKS_IMMEDIATELY d9_enemy[d9_v]									
										TASK_FALL_AND_GET_UP d9_enemy[d9_v] 0 0
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					d9_v ++
				ENDWHILE

				d9_barrel_rotation[d9_i] += 20.0
				SET_OBJECT_ROTATION d9_barrel[d9_i] 0.0 d9_barrel_rotation[d9_i] 90.0
	//
				IF d9_barrel_rotation[d9_i] = 360.0
					d9_barrel_rotation[d9_i] = 0.0
				ENDIF
	
			ENDIF
			d9_i ++
		ENDWHILE
	ENDIF

RETURN

d9_boom:

LVAR_INT d9_trigger_time d9_area_explode_time[15]
LVAR_INT d9_area_explode[15]

d9_int1 = 0
WHILE d9_int1 < 15
	IF d9_area_explode[d9_int1] = 1
		d9_area_explode_time[d9_int1] = TIMERA + 120
		d9_area_explode[d9_int1] = 2
	ENDIF
	IF d9_area_explode[d9_int1] = 2
		IF TIMERA > d9_area_explode_time[d9_int1]

			d9_area_explode[d9_int1] = 3
			d9_int2 =  d9_int1 - 1
			d9_int3 = d9_int1 + 1
			IF d9_int2 < 0
				d9_int2 = 0
			ENDIF
			IF d9_int3 > 14
				d9_int3 = 14
			ENDIF
			IF d9_area_explode[d9_int2] = 0
				d9_area_explode[d9_int2] = 1	
			ENDIF
			IF d9_area_explode[d9_int3] = 0						
				d9_area_explode[d9_int3] = 1	
			ENDIF

			d9_float3 =# d9_int1

			d9_float2 = 5.0 * d9_float3
			d9_float1 = 967.5745 + d9_float2
			d9_float2 = d9_float1 + 5.0

			IF player_y < d9_float2
			AND player_y > d9_float1
				IF player_fall_state = 0
//				IF NOT player_z < 1939.0
					d9_player_killed = 1					
//				ENDIF
				ENDIF
			ENDIF

			d9_float1 += 2.25
			d9_float2 = d9_float3 * -1.19
			d9_float2 += 1962.3799

			ADD_EXPLOSION 315.8258 d9_float1 d9_float2 EXPLOSION_HELI

		ENDIF
	ENDIF
	d9_int1 ++
ENDWHILE


IF d9_player_killed = 1
	TASK_FALL_AND_GET_UP scplayer 0 0

	d9_flag = 7
	d9_cut_flag = 0
	
	CLEAR_PRINTS
	PRINT DES9_42 4000 1

ENDIF

RETURN

// ****************************************START OF CUTSCENE********************************

// fades the screen in 

/*
LOAD_CUTSCENE desert_1
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
*/
// ****************************************END OF CUTSCENE**********************************

desert9_audio:

	LVAR_INT play_timed_audio play_timed_audio_flag audio_time_start audio_timer_flag audio_time[10] audio_to_play[10] play_timed_audio_for
 
	// play timed audio
	IF NOT play_timed_audio = 0
		IF play_timed_audio_flag = 0
			play_timed_audio_flag = 1
			audio_time_start = TIMERA
			audio_timer_flag = 0
			play_delay = audio_time_start + audio_time[audio_timer_flag]
		ENDIF	

		IF play_timed_audio_flag = 1
			IF TIMERA > play_delay
				play_audio = audio_to_play[audio_timer_flag]
				audio_timer_flag ++
				play_timed_audio_for --
				IF play_timed_audio_for = 0
					play_timed_audio = 0
					play_timed_audio_flag = 0
				ELSE
					play_delay = audio_time_start + audio_time[audio_timer_flag]
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	SWITCH audio_flag

		CASE 0 //first time game starts
	
			LVAR_TEXT_LABEL audio_text[40]
			LVAR_INT audio_sound[40] audio_slot[3] play_slot  
			LVAR_INT play_audio next_audio play_audio_for audio_flag play_audio_delay

			LVAR_INT audio_for_char[40] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay

//			$audio_text[0] = &CAT4_BI

			$audio_text[1] = &DES9_BA
			$audio_text[2] = &DES9_BB
 //Yo.



			audio_sound[1] = SOUND_DES9_BA
			audio_sound[2] = SOUND_DES9_BB


			audio_for_char[1] = 0
			audio_for_char[2] = 0
		   

			audio_actor[1] = scplayer


			
			//1 = catalina
			//2 = player

			audio_flag = 1
//			play_audio = 0

			LOAD_MISSION_AUDIO 1 audio_sound[1]
			LOAD_MISSION_AUDIO 2 audio_sound[2]

			audio_slot[1] = 1
			audio_slot[2] = 2


		BREAK

		CASE 1 //waiting to play audio
			
			IF NOT play_audio = 0
				IF TIMERA > play_audio_delay
					IF HAS_MISSION_AUDIO_FINISHED 1
					AND HAS_MISSION_AUDIO_FINISHED 2
						IF audio_slot[1] = play_audio
							play_slot = 1
						ELSE
							IF audio_slot[2] = play_audio
								play_slot = 2
							ELSE
								play_slot = 1
								audio_slot[1] = play_audio
								CLEAR_MISSION_AUDIO 1
								LOAD_MISSION_AUDIO 1 audio_sound[play_audio]
								//audio hasn't been requested yet
							ENDIF
						ENDIF			

						IF HAS_MISSION_AUDIO_LOADED play_slot
							IF NOT audio_for_char[play_audio] = 0
								actor_int = audio_for_char[play_audio]
								this_actor = audio_actor[actor_int]
								IF NOT IS_CHAR_DEAD this_actor   
									ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor								 
									IF NOT IS_CHAR_TALKING this_actor
										play_audio_now = 1
										START_CHAR_FACIAL_TALK this_actor 15000
									ELSE
										DISABLE_CHAR_SPEECH this_actor FALSE
									ENDIF
								ENDIF
							ENDIF

							IF audio_for_char[play_audio] = 0
								play_audio_now = 1
							ENDIF

							IF play_audio_now = 1							
								PLAY_MISSION_AUDIO play_slot
								CLEAR_PRINTS
								PRINT $audio_text[play_audio] 10000 1
								audio_flag ++
								play_audio_now = 0

								play_audio ++
								next_audio = play_audio

								// if the other slot doesn't already have the next audio loaded, then load it.
								IF NOT audio_sound[play_audio] = 0
									IF play_slot = 1									
										IF NOT audio_slot[2] = play_audio
											LOAD_MISSION_AUDIO 2 audio_sound[play_audio]	
											audio_slot[2] = play_audio
										ENDIF
									ELSE
										IF NOT audio_slot[1] = play_audio
											LOAD_MISSION_AUDIO 1 audio_sound[play_audio]	
											audio_slot[1] = play_audio
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ELSE
							LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
						ENDIF
					ENDIF

				ENDIF
			ENDIF
		BREAK


		CASE 2 // check if audio has/should finish
			IF HAS_MISSION_AUDIO_FINISHED play_slot
				audio_flag++
			ELSE
				IF DOES_CHAR_EXIST this_actor
					IF IS_CHAR_DEAD this_actor
						audio_flag++
					ENDIF
				ENDIF
			ENDIF
		BREAK

		CASE 3 //clear audio
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD this_actor
				STOP_CHAR_FACIAL_TALK this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF
			audio_flag++
		BREAK

		CASE 4 //request next audio

			play_audio ++
			IF NOT audio_sound[play_audio] = 0 
				LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
				audio_slot[play_slot] = play_audio
			ENDIF
			
			play_audio_for -= 1
			IF NOT play_audio_for > 0
				play_audio = 0
			ELSE
				play_audio = next_audio
			ENDIF
			audio_flag = 1
		BREAK

		CASE 5 // clear all for cut scene skip

			audio_flag = 1
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK
	ENDSWITCH


RETURN

display_instructions:

	IF print_instructions = 1
		IF print_instructions_flag = 0
			LVAR_INT instructions_timer	print_instructions_flag
			instructions_timer = TIMERA + 5000
			PRINT DES9_A9 4000 1
			print_instructions_flag = 1
		ENDIF
		
		IF print_instructions_flag = 1
			IF TIMERA > instructions_timer
				instructions_timer = TIMERA + 4500
				PRINT DES9_A6 4500 1
				print_instructions_flag = 2
			ENDIF
		ENDIF
	ENDIF
RETURN



 // **************************************** Mission desert9 failed ***********************

mission_desert9_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
REMOVE_PICKUP para_pickup

RETURN

   

// **************************************** mission desert9 passed ************************

mission_desert9_passed:

IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_DETONATOR
	REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_DETONATOR
ENDIF

flag_desert_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
REGISTER_MISSION_PASSED ( DESERT9 ) //Used in the stats 
PRINT_WITH_NUMBER_BIG ( M_PASS ) 20000 5000 1 //"Mission Passed!"
ADD_SCORE Player1 20000 //amount of cash reward

PLAYER_MADE_PROGRESS 1
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_desert9:

	player_Fall_Safe = 0

	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
	ENDIF


	SET_PLAYER_FIRE_BUTTON Player1 TRUE

		IF NOT IS_CHAR_DEAD scplayer
		   SET_CHAR_AREA_VISIBLE scplayer 0
		   SET_AREA_VISIBLE 0
		   SET_CHAR_VISIBLE scplayer TRUE
		ENDIF

		CAMERA_RESET_NEW_SCRIPTABLES

	    IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
			FREEZE_CHAR_POSITION scplayer FALSE
			DISABLE_PLAYER_SPRINT player1 FALSE
		ENDIF

		SWITCH_ENTRY_EXIT Deshous TRUE

		MARK_MODEL_AS_NO_LONGER_NEEDED ADMIRAL
		MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
		MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
		MARK_MODEL_AS_NO_LONGER_NEEDED ANDROM
		MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
		MARK_MODEL_AS_NO_LONGER_NEEDED WMOMIB
		MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI

		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYMIB
		MARK_MODEL_AS_NO_LONGER_NEEDED BOMB
		MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL
		MARK_MODEL_AS_NO_LONGER_NEEDED gun_para

		REMOVE_ANIMATION AIRPORT
		REMOVE_ANIMATION CARRY

		MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR

		REMOVE_CAR_RECORDING 150
		REMOVE_CAR_RECORDING 152
		REMOVE_CAR_RECORDING 149
		REMOVE_CAR_RECORDING 167
		REMOVE_CAR_RECORDING 168

		UNLOAD_SPECIAL_CHARACTER 1

		REMOVE_CAR_RECORDING 149

		d9_i = 0
		WHILE d9_i < 8	
			IF NOT IS_CHAR_DEAD d9_enemy[d9_i]
				DELETE_CHAR d9_enemy[d9_i]
			ENDIF	   
			d9_i ++
		ENDWHILE

		d9_i = 0
		WHILE d9_i < 15
			DELETE_OBJECT d9_box[d9_i]	   
			d9_i ++
		ENDWHILE

		REMOVE_ALL_SCRIPT_FIRES

		IF NOT IS_CAR_DEAD d9_enemy_car[0]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[0]
				DELETE_CAR d9_enemy_car[0]
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD d9_track_plane
			DELETE_CAR d9_track_plane
		ENDIF

		IF NOT IS_CAR_DEAD d9_enemy_car[1]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[1]
				DELETE_CAR d9_enemy_car[1]
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD d9_enemy_car[2]
			IF NOT IS_CHAR_IN_CAR scplayer d9_enemy_car[2]
				DELETE_CAR d9_enemy_car[2]
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD d9_plane
			DELETE_CAR d9_plane
		ENDIF


		IF NOT IS_CAR_DEAD d9_players_bike
			IF NOT IS_CHAR_IN_CAR scplayer d9_players_bike
				SET_VEHICLE_AREA_VISIBLE d9_players_bike 0
				SET_CAR_VISIBLE d9_players_bike TRUE
				MARK_CAR_AS_NO_LONGER_NEEDED d9_players_bike
			ENDIF
		ENDIF

		IF NOT d9_flag = 9
			IF NOT IS_CAR_DEAD d9_track_plane
				DELETE_CAR d9_track_plane
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD d9_track_plane
				MARK_CAR_AS_NO_LONGER_NEEDED d9_track_plane
			ENDIF
		ENDIF

		KILL_FX_SYSTEM des_cloud_FX

		REMOVE_BLIP d9_plane_blip


		IF IS_PLAYER_PLAYING player1
			DISABLE_PLAYER_SPRINT player1 FALSE
		ENDIF

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_DEFAULT

		REMOVE_BLIP d9_plane_blip
		REMOVE_BLIP d9_bike_blip

		DISPLAY_RADAR TRUE
		
		flag_player_on_mission = 0
		GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
		para_control_off = 0

RETURN

}

			 
RETURN

