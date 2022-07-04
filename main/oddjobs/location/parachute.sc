MISSION_START
{




//parachute_script:
SCRIPT_NAME PARACH

//GOSUB parachute_start





// Variables for mission

LVAR_INT parachute_pickup
LVAR_FLOAT freefall_speed freefall_forward_speed parachute_speed parachute_decel_speed  chute_open_forward_speed chute_open_glide_forward_speed
LVAR_INT starts_in_air jumper_id 

//global 
VAR_FLOAT ai_target_x ai_target_y ai_target_z
VAR_FLOAT para_anim_time
VAR_INT has_freefall_started

//temp float
VAR_FLOAT tempf1 tempf2 tempf3 tempf4 tempf5 para_turny_rate

//temp var
VAR_INT tempv1	tempv2 tempv3 tempv4
//aio global
VAR_FLOAT left_angle_check right_angle_check
VAR_FLOAT para_vel_change

//global - used by external script to know when player has landed
VAR_INT ai_landed

VAR_INT player_freefall

VAR_FLOAT initial_y initial_z

//camera shit
VAR_FLOAT start_cam_z start_cam_y
VAR_FLOAT para_cam_z_change para_cam_y_change
VAR_FLOAT end_cam_z end_cam_y

VAR_INT force_chute_open

VAR_FLOAT para_give_speed para_give_heading

//temp
VAR_INT p_in_air
VAR_FLOAT x_car_speed y_car_speed


//objects
	
LVAR_INT paraC parachute_pack

// flags

LVAR_INT get_models ai_action para_land parachute_State
LVAR_INT para_turning_flag para_setup_cam freefall

// stored Time

LVAR_INT jump_start_time

// one pass variables


//5 left

//ai shizzle
LVAR_FLOAT ai_heading ai_target_heading ai_turn_rateF
LVAR_INT ai_turn_rate random_change_time





LVAR_FLOAT para_start_vel 
LVAR_FLOAT para_turn_angle para_turny_angle 


//stored cam position


LVAR_FLOAT pr_heading
LVAR_FLOAT cam_percent
LVAR_FLOAT last_vert_speed

LVAR_FLOAT para_height
LVAR_INT code_check_time















 










// parachute
/*freefall_speed = -25.0
parachute_speed = -5.0
parachute_decel_speed = -2.0
chute_open_forward_speed = 5.0
chute_open_glide_forward_speed = 6.5 */

//LOAD_MISSION_TEXT STUPL


parachute_start:

	has_freefall_started = 0

    GENERATE_RANDOM_INT_IN_RANGE 0 6 code_check_time	
	
	get_models = 10
	freefall = 0
	ai_action = 0
	ai_landed = 0



IF ai_action = 100
	CREATE_PICKUP BRIEFCASE PICKUP_ONCE 32.1527 2239.7258 125.6720 parachute_pickup
	CREATE_CHAR PEDTYPE_CIVMALE wmyplt 32.1527 2239.7258 125.6720 jumper_id 
ENDIF

	IF NOT IS_CHAR_DEAD jumper_id
		GET_CHAR_HEIGHT_ABOVE_GROUND jumper_id para_height
	ENDIF




parachute_loop:

WAIT 0


	


	player_freefall = freefall
	IF ai_landed = 2
		IF jumper_id = scplayer
	//set by external script.
			GOSUB mission_cleanup_parachute
		ENDIF
	ENDIF

	IF NOT parachute_pickup = -1
		IF get_models = 10
			
			IF NOT parachute_pickup = -1
				IF HAS_PICKUP_BEEN_COLLECTED parachute_pickup
				
					get_models = 0
				ENDIF
			ELSE
			   get_models = 0
			ENDIF

		ENDIF
	ENDIF

	IF parachute_pickup = -1
		IF get_models = 10
			get_models = 0
		ENDIF
	ENDIF
		

	IF get_models = 0
		REQUEST_ANIMATION PARACHUTE
		REQUEST_MODEL PARACHUTE
		REQUEST_MODEL para_pack
		get_models = 1
	ENDIF

	IF get_models = 1
		IF HAS_ANIMATION_LOADED PARACHUTE
		AND HAS_MODEL_LOADED PARACHUTE
		AND HAS_MODEL_LOADED para_pack
			get_models = 2
		ENDIF
	ENDIF

	IF get_models = 2

		IF NOT IS_CHAR_DEAD jumper_id
			GET_CHAR_COORDINATES jumper_id player_x player_y player_z
		ENDIF

		CREATE_OBJECT PARACHUTE player_x player_y player_z ParaC
		CREATE_OBJECT para_pack player_x player_y player_z parachute_pack
//		MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
//		MARK_MODEL_AS_NO_LONGER_NEEDED para_pack
		SET_OBJECT_VISIBLE parac FALSE

		IF NOT IS_CHAR_DEAD jumper_id
			ATTACH_OBJECT_TO_CHAR parac jumper_id 0.0 0.0 0.0 0.0 0.0 0.0
			SET_OBJECT_ROTATION parachute_pack 90.0 90.0 90.0
//			ATTACH_OBJECT_TO_CHAR parachute_pack jumper_id 0.0 0.0 0.0 0.0 0.0 0.0
			TASK_PICK_UP_OBJECT jumper_id parachute_pack -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0

		ENDIF
		get_models = 3
	ENDIF

	IF get_models = 3	

		IF NOT IS_CHAR_DEAD jumper_id
	 
			
			IF code_check_time = 0
				GET_CHAR_HEIGHT_ABOVE_GROUND jumper_id para_height
				IF para_height < 5.0
					code_check_time = 2
				ELSE
					code_check_time = 5
				ENDIF
			ENDIF
			code_check_time --
				
//			para_height= 50.0

			

			IF NOT jumper_id = scplayer
				GOSUB ai_jumper
			ENDIF



			IF IS_CHAR_IN_ANY_CAR jumper_id
			//	GET_CAR_CHAR_IS_USING
				p_in_air = 1
			ELSE
				p_in_air = 0
			ENDIF
			//IF NOT IS_CHAR_IN_ANY_CAR jumper_id

			IF NOT IS_CHAR_DEAD jumper_id
				IF IS_CHAR_IN_AIR jumper_id
				OR p_in_air = 0
					
					IF freefall = 0
					AND parachute_State = 0
					AND para_height > 5.0
						IF p_in_air = 0
//							CLEAR_CHAR_TASKS_IMMEDIATELY jumper_id
							SIN para_give_heading x_car_speed
							x_car_speed *= para_give_speed
							x_car_speed *= -1.0

							COS para_give_heading y_car_speed
							y_car_speed *= para_give_speed

							GET_CHAR_VELOCITY jumper_id tempf1 tempf1 tempf1

							SET_CHAR_VELOCITY jumper_id x_car_speed y_car_speed tempf1
							 
  							
						ENDIF
						freefall = 1
						has_freefall_started = 1
						jump_start_time = TIMERA
						GET_CHAR_HEADING jumper_id pr_heading
						IF jumper_id = scplayer
							end_cam_z = 3.0
							end_cam_Y = -2.0
							para_setup_cam = 0
							GOSUB setup_camera
						ENDIF
						IF starts_in_air = FALSE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive PARACHUTE 1.0 1 0 0 0 -1
						ELSE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive PARACHUTE 1000.0 1 0 0 0 -1
						ENDIF	
//					ELSE
//						IF starts_in_air = FALSE
//							starts_in_air = TRUE
//						ENDIF 
					ENDIF
				ENDIF
			ENDIF

			IF freefall = 1



				IF IS_CHAR_IN_WATER jumper_id
					TASK_DIE_NAMED_ANIM jumper_id FALL_skydive_die PARACHUTE 1000.0 0
					freefall = 2
				ENDIF
				IF para_height < 3.0
					TASK_DIE_NAMED_ANIM jumper_id FALL_skydive_die PARACHUTE 1000.0 0
					freefall = 2
				ENDIF
				GET_CHAR_VELOCITY jumper_id tempf1 tempf1 tempf2

				IF tempf2 < freefall_speed
					tempf2 = freefall_speed
				ENDIF

				

				
				tempv1 = TIMERA - jump_start_time
				cam_percent =# tempv1
				cam_percent /= 2000.0

				IF cam_percent > 1.0
					cam_percent = 1.0
				ENDIF

				IF jumper_id = scplayer
					IF cam_percent <= 1.0
						GOSUB cam_move
					ENDIF
				ENDIF

				IF cam_percent = 1.0
					IF jumper_id = scplayer
						GET_POSITION_OF_ANALOGUE_STICKS PAD1 tempv1 tempv2 tempv3 tempv3

						tempf4 =# tempv1
						tempf4 /= 256.0
			//			para_turn_angle =# 
						para_turn_angle += tempf4
						tempf5 = para_turn_angle / 30.0

						IF tempf5 < -1.6
							tempf5 = -1.6
						ENDIF
						IF tempf5 > 1.6
							tempf5 = 1.6
						ENDIF
							
						pr_heading -= tempf5 

						
						IF tempf4 = 0.0
							IF NOT para_turn_angle = 0.0
								para_turn_angle *= 0.95
							ENDIF
						ENDIF

						tempf3 = para_turny_angle * 6.0
						para_turny_rate =# tempv2
						para_turny_rate -= tempf3
						para_turny_rate /= 256.0
						para_turny_angle += para_turny_rate

						IF para_turny_angle < -30.0
							para_turny_angle = -30.0
						ENDIF
						IF para_turny_angle > 30.0
							para_turny_angle = 30.0
						ENDIF
						
						IF para_turny_rate = 0.0
							IF NOT para_turny_angle = 0.0
								para_turny_angle *= 0.97
							ENDIF
						ENDIF



						SIN pr_heading X
						COS pr_heading Y
					
						tempf4 = para_turny_angle / -5.0 
							X *= freefall_forward_speed
							X *= tempf4
							X *= -1.0
							Y *= freefall_forward_speed
							Y *= tempf4

						GET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf1
						
						X *= -1.0
						X += tempf3
						X *= 0.1
						tempf3 -= X

						Y *= -1.0
						Y += tempf4
						Y *= 0.1
						tempf4 -= Y

						
						 
						SET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf2
//						SET_CHAR_VELOCITY jumper_id X Y tempf2





					//	SET_CHAR_HEADING jumper_id heading
						SET_CHAR_ROTATION jumper_id para_turny_angle 0.0 pr_heading
						IF tempv2 < -100
							IF NOT para_turning_flag = 4
								para_turning_flag = 4	  
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive_Accel PARACHUTE 1.0 1 0 0 1 -2
							ENDIF
						ELSE

							IF tempv1 = 0
							AND NOT para_turning_flag = 0
								para_turning_flag = 0			
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive PARACHUTE 1.0 1 0 0 1 -2
							ENDIF
							IF tempv1 < 0
							AND NOT para_turning_flag = -1
								para_turning_flag = -1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive_L PARACHUTE 1.0 1 0 0 1 -2
							ENDIF
							IF tempv1 > 0
							AND NOT para_turning_flag = 1
								para_turning_flag = 1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id FALL_skydive_R PARACHUTE 1.0 1 0 0 1 -2
							ENDIF
						ENDIF
					ELSE
						SET_CHAR_VELOCITY jumper_id 0.0  0.0 freefall_speed
					ENDIF
				ENDIF	

				IF IS_BUTTON_PRESSED PAD1 CIRCLE
				OR force_chute_open = 1
					IF NOT force_chute_open = 2
						IF jumper_id = scplayer
							freefall = 0
							para_turning_flag = 0
							parachute_state = 1
							SET_OBJECT_VISIBLE parac TRUE

							WAIT 0
							IF NOT IS_CHAR_DEAD jumper_id
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_open PARACHUTE 1000.0 0 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_open_o PARACHUTE 1000.0 0 1
								//setup camera
								GET_CHAR_VELOCITY jumper_id tempf1 tempf1 para_start_vel
								para_vel_change = para_start_vel + 5.0
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF ai_action = 1
				AND NOT jumper_id = scplayer
					freefall = 0
					para_turning_flag = 0
					parachute_state = 1
					SET_OBJECT_VISIBLE parac TRUE

					WAIT 0
					IF NOT IS_CHAR_DEAD jumper_id
						TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_open PARACHUTE 1000.0 0 0 0 1 -2
						PLAY_OBJECT_ANIM parac para_open_o PARACHUTE 1000.0 0 1
						//setup camera
					

						GET_CHAR_VELOCITY jumper_id tempf1 tempf1 para_start_vel
						para_vel_change = para_start_vel + 5.0
					ENDIF
				ENDIF


			ENDIF

			IF parachute_state = 1
				IF NOT IS_CHAR_DEAD jumper_id
					
					IF IS_CHAR_PLAYING_ANIM jumper_id PARA_open
						GET_CHAR_ANIM_CURRENT_TIME jumper_id PARA_open para_anim_time
						GET_CHAR_COORDINATES jumper_id player_x player_y player_z
						para_anim_time *= 2.0
						para_anim_time -= 0.5
						IF para_anim_time > 1.0
							para_anim_time = 1.0
						ENDIF

						IF para_anim_time < 0.0
							para_anim_time = 0.0
						ENDIF

						IF para_anim_time <= 0.25
						AND jumper_id = scplayer

							end_cam_z = -2.0
							end_cam_y = -2.0
							cam_percent = para_anim_time * 4.0
							IF para_setup_cam = 1
								para_setup_cam = 2
								GOSUB setup_camera	
							ENDIF
							GOSUB cam_move
						ENDIF

						IF para_anim_time > 0.25
						AND jumper_id = scplayer
							end_cam_z = 5.0
							end_cam_y = -5.0
							cam_percent = para_anim_time - 0.25
							cam_percent *= 1.333
							IF para_setup_cam = 2
								para_setup_cam = 3
								GOSUB setup_camera	
							ENDIF
							GOSUB cam_move
						ENDIF

						tempf1 = para_vel_change * para_anim_time
						tempf1 *= -1.0
						tempf1 += para_start_vel

						GET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf2

						SIN pr_heading X
						COS pr_heading Y
					
						X *= chute_open_forward_speed
						X *= -1.0
						Y *= chute_open_forward_speed

						X *= -1.0
						X += tempf3
						X *= 0.01
						tempf3 -= X

						Y *= -1.0
						Y += tempf4
						Y *= 0.01
						tempf4 -= Y

						


						SET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf1
							
						IF para_anim_time = 1.0			
							TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_float PARACHUTE 2.0 1 0 0 1 -2
							PLAY_OBJECT_ANIM parac para_float_o PARACHUTE 2.0 1 1
							parachute_state = 2
							last_vert_speed = tempf1
//							para_turnx_rate = 0.0
//							tempf5 = 0.0
//							para_turny_rate = 0.0
							para_turn_angle = 0.0

						ENDIF
					ENDIF
				ENDIF

				IF para_height < 3.0

					parachute_state = 3
				ENDIF
			ENDIF

			IF parachute_state = 2
				IF NOT para_land = 5
				AND IS_CHAR_IN_WATER jumper_id
					para_land = 4	
				ENDIF

				IF para_land = 0

				   	SIN pr_heading X
				   	COS pr_heading Y
				



					tempv3 = 0
					tempv4 = 0
					
					IF jumper_id = scplayer
						GET_POSITION_OF_ANALOGUE_STICKS PAD1 tempv1 tempv2 tempv3 tempv4
					ELSE
						tempv1 = ai_turn_rate
						tempv2 = 0
						tempv3 = 0
					ENDIF 

					IF NOT tempv3 = 0
					AND NOT tempv4 = 0
//						RESTORE_CAMERA_JUMPCUT
					ENDIF
					

					IF tempv2 > 100
					OR ai_action = 2
						X *= chute_open_glide_forward_speed
						X *= -1.0
						Y *= chute_open_glide_forward_speed
					ELSE
						X *= chute_open_forward_speed
						X *= -1.0
						Y *= chute_open_forward_speed
					ENDIF

					tempv4 = tempv2
					
//					VIEW_INTEGER_VARIABLE tempv4 tempv4


						

					SET_CHAR_ROTATION jumper_id 0.0 para_turn_angle pr_heading

					IF para_height > 3.0
						para_turny_rate =# tempv1
						para_turny_rate /= 128.0
			//			para_turn_angle =# 
						para_turn_angle += para_turny_rate
						IF para_turn_angle > 45.0
							para_turn_angle = 45.0
						ENDIF

						IF para_turn_angle < -45.0
							para_turn_angle = -45.0
						ENDIF

						tempf5 = para_turn_angle / 15.0
						pr_heading -= tempf5 

						IF para_turny_rate = 0.0
							IF NOT para_turn_angle = 0.0
								para_turn_angle *= 0.95
							ENDIF
						ENDIF


						SET_CHAR_ROTATION jumper_id 0.0 para_turn_angle pr_heading
						IF tempv2 > 100
						OR ai_action = 2
							Z = parachute_decel_speed
							IF NOT para_turning_flag = 4								
								para_turning_flag = 4
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_decel PARACHUTE 1.0 1 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_decel_o PARACHUTE 1.0 1 1
							ENDIF
						ELSE
							Z = parachute_speed
							IF para_turny_rate = 0.0
							AND NOT para_turning_flag = 0
								para_turning_flag = 0			
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_float PARACHUTE 1.0 1 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_float_o PARACHUTE 1.0 1 1
							ENDIF
							IF para_turny_rate < 0.0
							AND NOT para_turning_flag = -1
								para_turning_flag = -1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_steerL PARACHUTE 1.0 1 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_steerL_o PARACHUTE 1.0 1 1
							ENDIF
							IF para_turny_rate > 0.0
							AND NOT para_turning_flag = 1
								para_turning_flag = 1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_steerR PARACHUTE 1.0 1 0 0 1 -2
								PLAY_OBJECT_ANIM parac para_steerR_o PARACHUTE 1.0 1 1
							ENDIF
						ENDIF	
					ENDIF

						GET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf1
						


						
						X *= -1.0
						X += tempf3
						X *= 0.1
						tempf3 -= X

						Y *= -1.0
 						Y += tempf4
 						Y *= 0.1
						tempf4 -= Y

					   	Z *= -1.0
						Z += last_vert_speed
						Z *= 0.2
						tempf1 = last_vert_speed - Z

						


// 					   	Z += tempf1
// 					   	Z *= 0.35
//					   	tempf1 -= Z

						last_vert_speed = tempf1

						
						 
						SET_CHAR_VELOCITY jumper_id tempf3 tempf4 tempf1

						tempf3 *= tempf3
						tempf4 *= tempf4
						tempf5 = tempf3 + tempf4
						SQRT tempf5 tempf5



//					SET_CHAR_VELOCITY jumper_id X Y Z // not set so vert decel speed works. need to fix
					

				ENDIF
					
				IF para_height < 3.0
					SET_CHAR_HEADING jumper_id pr_heading
					para_turn_angle = 0.0

					SIN pr_heading X
					COS pr_heading Y
				
					X *= chute_open_glide_forward_speed
					X *= -1.0
					Y *= chute_open_glide_forward_speed

					SET_CHAR_VELOCITY jumper_id X Y -2.0
					IF para_land = 0
						para_land = 1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_decel PARACHUTE 4.0 1 0 0 1 -2	
						PLAY_OBJECT_ANIM parac para_decel_o PARACHUTE 4.0 1 1
		//				GET_CHAR_HEADING jumper_id heading
						IF jumper_id = scplayer
							end_cam_z = initial_z
							end_cam_Y = initial_y					
							GOSUB setup_camera
						ENDIF
					ENDIF
					IF para_land = 1
					AND jumper_id = scplayer
						cam_percent = 3.0 - para_height
						cam_percent *= 0.75
						IF cam_percent > 1.0
							cam_percent = 1.0
						ENDIF

						GOSUB cam_move 
					ENDIF
				ENDIF
				IF para_height < 1.5
				AND para_land = 1
					para_land = 2
					TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_land PARACHUTE 1000.0 0 1 0 1 -2	
					PLAY_OBJECT_ANIM parac para_land_o PARACHUTE 1000.0 0 1
				ENDIF
				IF para_land = 2
					IF IS_CHAR_PLAYING_ANIM jumper_id PARA_land
						GET_CHAR_ANIM_CURRENT_TIME jumper_id PARA_land para_anim_time
						IF para_anim_time = 1.0
//							FREEZE_OBJECT_POSITION parac TRUE
//							DETACH_OBJECT parac 0.0 0.0 0.0 0
							DELETE_OBJECT parac
							DELETE_OBJECT parachute_pack
							GET_DEAD_CHAR_PICKUP_COORDS jumper_id player_x player_y player_z
							IF NOT parachute_pickup = -1
								REMOVE_PICKUP parachute_Pickup
							ENDIF

							TASK_PAUSE jumper_id 1
							CLEAR_CHAR_TASKS_IMMEDIATELY jumper_id
							parachute_state = 3
							SET_CHAR_ROTATION jumper_id 0.0 0.0 0.0
							SET_CHAR_HEADING jumper_id pr_heading
//							SET_CAMERA_BEHIND_PLAYER
							GET_CHAR_COORDINATES jumper_id player_x player_y player_z
//							RESTORE_CAMERA_JUMPCUT	

							IF jumper_id = scplayer
								ai_landed = 1
							ENDIF
							IF flag_player_on_mission = 1
								GOSUB mission_cleanup_parachute
							ENDIF
							parachute_pickup = 0							 
							CREATE_PICKUP BRIEFCASE PICKUP_ONCE player_x player_y player_z parachute_pickup 
							para_land= 3
						
						ENDIF
					ENDIF
				ENDIF
				IF para_land = 4
				// player lands in water
					TASK_PLAY_ANIM_NON_INTERRUPTABLE jumper_id PARA_land_water PARACHUTE 1000.0 0 1 0 1 -2
					PLAY_OBJECT_ANIM parac para_land_water_O PARACHUTE 1.0 0 1	
					para_land = 5
					para_anim_time = 0.0								
				ENDIF

				IF para_land = 5
					IF IS_CHAR_PLAYING_ANIM jumper_id PARA_land_water
						GET_CHAR_ANIM_CURRENT_TIME jumper_id PARA_land_water para_anim_time
					ENDIF
					
					IF para_anim_time = 1.0
							
							IF jumper_id = scplayer
								ai_landed = 1
							ENDIF

						    DELETE_OBJECT parac
							DELETE_OBJECT parachute_pack
							para_land= 3
							TASK_PAUSE jumper_id 1
							CLEAR_CHAR_TASKS_IMMEDIATELY jumper_id
							parachute_state = 3
							SET_CHAR_ROTATION jumper_id 0.0 0.0 0.0
							SET_CHAR_HEADING jumper_id pr_heading
//							SET_CAMERA_BEHIND_PLAYER
							GET_CHAR_COORDINATES jumper_id player_x player_y player_z
//							RESTORE_CAMERA_JUMPCUT							
					ENDIF
				ENDIF

			ENDIF

			IF parachute_state = 3
			AND para_land = 0
				PLAY_OBJECT_ANIM parac para_land_o PARACHUTE 1.0 0 1		
				TASK_DIE_NAMED_ANIM jumper_id FALL_skydive_die PARACHUTE 1000.0 0
				
				para_land = 1
			ENDIF

			IF parachute_state = 3						 
				IF HAS_PICKUP_BEEN_COLLECTED parachute_pickup
					get_models = 10
					freefall = 0
					parachute_state = 0
					starts_in_air = FALSE
					para_land = 0
				ENDIF
				IF NOT LOCATE_CHAR_ANY_MEANS_3D jumper_id player_x player_y player_z 50.0 50.0 50.0 FALSE
					   GOTO mission_cleanup_parachute
				ENDIF
			ENDIF	
				 
		ENDIF

		
	ENDIF

	
	//IF flag_player_on_mission = 0


	
	
	IF IS_CHAR_DEAD jumper_id
		GOTO mission_cleanup_parachute
	ENDIF

	

					   
GOTO parachute_loop
	   
setup_camera:

		GET_ACTIVE_CAMERA_COORDINATES X Y Z
		GET_CHAR_COORDINATES jumper_id player_x player_y player_z

		tempf3 = player_x - X
		tempf4 = player_y - Y
		start_cam_Z = Z - player_z

		tempf1 = tempf3 * tempf3
		tempf2 = tempf4 * tempf4
		start_cam_Y = tempf1 + tempf2
		start_cam_Y *= -1.0

		SQRT start_cam_Y start_cam_Y

		para_cam_z_change = end_cam_Z - start_cam_Z
		
		start_cam_Y *= -1.0
		para_cam_y_change = end_cam_Y - start_cam_Y

		IF para_setup_cam = 0
			para_setup_cam = 1		
			initial_y = start_cam_Y
			initial_z = start_cam_Z
		ENDIF
//		heading = 0.0

RETURN 

cam_move:

		GET_CHAR_COORDINATES jumper_id player_x player_y player_z	
		

		tempf3 = cam_percent * 180.0

		COS tempf3 tempf3
		tempf3 -= 1.0
		tempf3 *= -0.5
		Z = para_cam_z_change * tempf3
		Z += start_cam_Z
		Y = para_cam_y_change * tempf3
		Y += start_cam_Y

//		ATTACH_CAMERA_TO_CHAR jumper_id 0.0 Y Z 0.0 0.0 0.5 0.0 JUMP_CUT

RETURN


	
// ************************************ MISSION CLEANUP ************************************

// mission cleanup

mission_cleanup_parachute:

REMOVE_ANIMATION PARACHUTE
MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
MARK_MODEL_AS_NO_LONGER_NEEDED para_pack


//RESTORE_CAMERA_JUMPCUT

IF NOT parachute_pickup = -1
	REMOVE_PICKUP parachute_Pickup
ENDIF

DELETE_OBJECT ParaC
DELETE_OBJECT parachute_pack


IF jumper_id = scplayer
	force_chute_open = 0
ENDIF


TERMINATE_THIS_SCRIPT




//REMOVE_BLIP finish_blip_ps
//CLEAR_ONSCREEN_COUNTER
//MARK_MODEL_AS_NO_LONGER_NEEDED VCNMAV

RETURN

ai_jumper:


	GET_CHAR_COORDINATES jumper_id player_x player_y player_z
	X = ai_target_x - player_x 
	Y = ai_target_y - player_y
	Z = player_z - ai_target_z
	
	IF freefall = 1
		X *= X
		Y *= Y
		X += Y
		SQRT X X
		tempf1 = Z / X

		tempf2 = parachute_speed / chute_open_forward_speed
		tempf2 *= -1.2


		IF tempf1 < tempf2
			ai_action = 1
		ENDIF
	ENDIF

	IF parachute_state = 2

		GET_HEADING_FROM_VECTOR_2D X Y ai_target_heading
		GET_CHAR_HEADING jumper_id ai_heading

		parachute_speed -= 1.0
		parachute_decel_speed -= 1.0

		ai_turn_rateF = ai_target_heading - ai_heading

		IF TIMERA > random_change_time
			random_change_time = TIMERA + 3000
			GENERATE_RANDOM_FLOAT_IN_RANGE -20.0 0.0 left_angle_check
			GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 20.0 right_angle_check



			X *= X
			Y *= Y
			X += Y
			SQRT X X
			tempf2 = X / chute_open_forward_speed
			tempf1 = Z / tempf2





			// ideal rate of descent = ( parachute_speed  +   parachute_decel_speed ) / 2
			tempf2 = parachute_speed  +   parachute_decel_speed
			tempf2 /= 2.0
			
			tempf3 = parachute_speed  -   parachute_decel_speed
			tempf3 /= 4.0
			tempf3 += tempf2

			tempf4 = parachute_speed  -   parachute_decel_speed
			tempf4 /= -4.0
			tempf4 += tempf2

			tempf3 *= -1.0
			tempf4 *= -1.0

			IF tempf1 < tempf3
			AND tempf1 > tempf4
				GENERATE_RANDOM_INT_IN_RANGE 0 2 tempv1
					IF tempv1 = 0
						ai_action = 2
					ENDIF

					IF tempv1 = 1
						ai_action = 0
					ENDIF
			ENDIF

			IF tempf1 >= tempf3
				ai_action = 0
			ENDIF


			IF tempf1 <= tempf4
				ai_action = 2
			ENDIF


		ENDIF

		IF ai_turn_rateF < left_angle_check
		OR ai_turn_rateF > right_angle_check
			IF ai_turn_rateF < -180.0
				ai_turn_rateF += 360.0
			ENDIF
			
			IF ai_turn_rateF > 180.0
				ai_turn_rateF -= 360.0	
			ENDIF

			ai_turn_rateF /= 180.0

			IF ai_turn_rateF > 1.0
				ai_turn_rateF = 1.0
			ENDIF 
			IF ai_turn_rateF < -1.0
				ai_turn_rateF = -1.0
			ENDIF 
			ai_turn_rateF *= -128.0
			ai_turn_rate =# ai_turn_rateF
		ELSE
			ai_turn_rate = 0
		ENDIF

//		tempf3 /= 2.0
//		tempf3 += parachute_decel_speed
//
//		GENERATE_RANDOM_FLOAT_IN_RANGE tempf3 tempf2 ai_prop_low_check
//
//		tempf3 = parachute_speed  -   parachute_decel_speed
//		tempf3 /= 2.0
//		tempf3 += tempf2		
//
//
//		GENERATE_RANDOM_FLOAT_IN_RANGE tempf2 tempf3 ai_prop_high_check		
//
//		
//
//		IF tempf1 < ai_prop_low_check
//			ai_action = 2
//		ENDIF
//
//		IF tempf1 > ai_prop_high_check
//			ai_action = 0
//		ENDIF

		parachute_speed += 1.0
		parachute_decel_speed += 1.0

	ENDIF 




	
RETURN
	
MISSION_END
		
}


