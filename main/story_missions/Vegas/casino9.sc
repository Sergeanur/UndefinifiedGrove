MISSION_START

// ########################
// ##
// ##	Casino9.sc 
// ##
// ##	Freefall
// ##
// ## 	Simon Lashley
// ##
// ########################

SCRIPT_NAME CASINO9

// Mission start stuff

GOSUB mission_casino9_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino9_FAILED
ENDIF

GOSUB mission_casino9_CLEANUP

MISSION_END

{
 
// Variables for mission

// dodo										   
LVAR_INT dodo_C9 dodo_blip_C9 dodo_status_C9		

// sanchez - bike in front of casino
LVAR_INT sanchez_C9

// shamal
LVAR_INT shamal_C9 shamal_status_C9

LVAR_INT pilot_C9

// general
LVAR_INT pointer_C9 pointer2_C9 goto_blip_C9 sequence_C9 cutscene_flag_C9 task_progress_C9 airport_status_C9 corona_C9 empty_dm_C9	

// fail text
LVAR_INT fail_text_flag_C9 
LVAR_TEXT_LABEL fail_text_C9

// coordinates
LVAR_FLOAT shamal_X_C9 shamal_Y_C9 shamal_Z_C9
LVAR_FLOAT dodo_X_C9 dodo_Y_C9 dodo_Z_C9  
LVAR_FLOAT corona_X_C9 corona_Y_C9 corona_Z_C9
LVAR_FLOAT cam_pos_X_C9 cam_pos_Y_C9 cam_pos_Z_C9 cam_look_X_C9 cam_look_Y_C9 cam_look_Z_C9



// **************************************** Mission Start **********************************

mission_casino9_START:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	// text
	LOAD_MISSION_TEXT CASINO9

	SET_CHAR_COORDINATES scplayer 2175.4116 1681.5483 9.8203
	SET_CHAR_HEADING scplayer 90.0 
	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 10.0

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

	iTerminateAllAmbience = 1

// ****************************************START OF CUTSCENE********************************

	SET_AREA_VISIBLE 2

	LOAD_CUTSCENE CAS_9a1
     
    WHILE NOT HAS_CUTSCENE_LOADED
                WAIT 0
    ENDWHILE
     
    START_CUTSCENE

    DO_FADE 1000 FADE_IN
      
    WHILE NOT HAS_CUTSCENE_FINISHED
                WAIT 0
    ENDWHILE
     
    CLEAR_CUTSCENE

    SET_PLAYER_CONTROL player1 OFF

    DO_FADE 0 FADE_OUT

    WHILE GET_FADING_STATUS
    	WAIT 0
    ENDWHILE

    IF NOT WAS_CUTSCENE_SKIPPED
        LOAD_CUTSCENE CAS_9a2
         
        WHILE NOT HAS_CUTSCENE_LOADED
        	WAIT 0
        ENDWHILE
         
        START_CUTSCENE

        DO_FADE 1000 FADE_IN
          
        WHILE NOT HAS_CUTSCENE_FINISHED
        	WAIT 0
        ENDWHILE
		
        CLEAR_CUTSCENE

        SET_PLAYER_CONTROL player1 OFF

        DO_FADE 0 FADE_OUT

        WHILE GET_FADING_STATUS
        	WAIT 0
        ENDWHILE
    ENDIF

	SET_AREA_VISIBLE 0

// ****************************************END OF CUTSCENE**********************************

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

	// audio
	START_NEW_SCRIPT start_audio_controller
			
	LOAD_SCENE 2175.4116 1681.5483 9.8203

	// models
	REQUEST_MODEL DODO 
	REQUEST_MODEL FCR900

	WHILE NOT HAS_MODEL_LOADED DODO
	OR NOT HAS_MODEL_LOADED FCR900
		WAIT 0
	ENDWHILE

	FORCE_WEATHER_NOW WEATHER_SUNNY_VEGAS

LVAR_INT never_run_this_C9
	never_run_this_C9 = 0
	IF never_run_this_C9 = 1
		CREATE_CAR DODO 0.0 0.0 0.0 dodo_C9
		ADD_BLIP_FOR_CAR dodo_C9 dodo_blip_C9
	ENDIF
	
	dodo_status_C9 = 0

	SET_CAMERA_BEHIND_PLAYER
				
	SWITCH_AMBIENT_PLANES FALSE	

	CREATE_CAR FCR900 2163.2490 1679.9757 9.8125 sanchez_C9
	SET_CAR_HEADING sanchez_C9 350.0

	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1581.3737 1768.9958 9.8203

	DO_FADE 500 FADE_IN


	PRINT_NOW CAS9_08 8500 1
	ADD_BLIP_FOR_COORD 1707.828 1606.639 9.055 goto_blip_C9 

	fail_text_flag_C9 = 0
	
	dodo_status_C9 = 0
	shamal_status_C9 = 0

	airport_status_C9 = 0

	shamal_X_C9 = 1465.0
	shamal_Y_C9	= 5200.0
	shamal_Z_C9	= 200.0

	corona_X_C9 = 1465.0
	corona_Y_C9	= 5220.0
	corona_Z_C9 = 215.0

// #################################
// #
// # FREEFALL - Part 1 - steal plane
// #
// #################################

mission_casino9_MAIN_steal_plane:

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_casino9_PASSED  
	ENDIF

	IF airport_status_C9 = 0

		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1707.828 1606.639 4.0 4.0 FALSE
			
			REMOVE_BLIP goto_blip_C9
							
			CREATE_CAR DODO 1279.6250 1361.6047 9.8203 dodo_C9
			SET_CAR_HEADING dodo_C9 270.0	
			ADD_BLIP_FOR_CAR dodo_C9 dodo_blip_C9
			SET_BLIP_AS_FRIENDLY dodo_blip_C9 TRUE
			PRINT_NOW CAS9_09 6000 1

			airport_status_C9 ++
		ENDIF
	ELSE
	
		IF NOT IS_CAR_DEAD dodo_C9
			
			IF IS_CHAR_IN_CAR scplayer dodo_C9
				
				// if player in plane for first time tell him to get high in the air
				IF DOES_BLIP_EXIST dodo_blip_C9
					REMOVE_BLIP dodo_blip_C9
					ADD_BLIP_FOR_COORD shamal_X_C9 shamal_Y_C9 shamal_Z_C9 goto_blip_C9
					CLEAR_PRINTS
					IF dodo_status_C9 = 0
						PRINT_NOW CAS9_50 8000 1
						dodo_status_C9 ++				
					ENDIF
				ENDIF

				GET_CAR_COORDINATES dodo_C9 x y z
	 			GET_DISTANCE_BETWEEN_COORDS_2D x y shamal_X_C9 shamal_Y_C9 distance


				SWITCH shamal_status_C9
				
					CASE 0 
						IF distance > 4400.0
							BREAK
						ENDIF

						REQUEST_MODEL SHAMAL
						REQUEST_MODEL WMYPLT
						
						MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
						MARK_CAR_AS_NO_LONGER_NEEDED sanchez_C9
		
						shamal_status_C9 ++ 

					CASE 1
						IF distance > 600.0
						OR NOT HAS_MODEL_LOADED SHAMAL
						OR NOT HAS_MODEL_LOADED WMYPLT
							BREAK
						ENDIF

						REMOVE_BLIP goto_blip_C9
						
						CREATE_CAR SHAMAL shamal_X_C9 shamal_Y_C9 shamal_Z_C9 shamal_C9
						SET_CAR_HEADING shamal_C9 180.0
						SET_PLANE_UNDERCARRIAGE_UP shamal_C9 TRUE
						FREEZE_CAR_POSITION shamal_C9 TRUE

						CREATE_CHAR_INSIDE_CAR shamal_C9 PEDTYPE_MISSION1 WMYPLT pilot_C9 	
						
						ADD_BLIP_FOR_CAR shamal_C9 goto_blip_C9
						CHANGE_BLIP_DISPLAY goto_blip_C9 BLIP_ONLY
						CHANGE_BLIP_COLOUR goto_blip_C9 YELLOW  
						
						shamal_status_C9 ++	

					CASE 2
						IF distance > 300.0
							BREAK
						ENDIF

						corona_Y_C9 = shamal_Y_C9 + 20.0
						y = corona_Y_C9 - 1.0 
			
						CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_C9 corona_Y_C9 corona_Z_C9 corona_X_C9 y corona_Z_C9 3.0 corona_C9

						PRINT_NOW CAS9_51 8000 1

						shamal_status_C9 ++
						
					CASE 3
						IF NOT IS_CAR_DEAD shamal_C9 
														
							shamal_Y_C9 -= 1.0
							SET_CAR_COORDINATES_NO_OFFSET shamal_C9 shamal_X_C9 shamal_Y_C9 shamal_Z_C9
							
							corona_Y_C9 = shamal_Y_C9 + 20.0 
							SET_CHECKPOINT_COORDS corona_C9 corona_X_C9 corona_Y_C9 corona_Z_C9
							
							z = corona_Z_C9 - 3.0
							IF LOCATE_CHAR_IN_CAR_3D scplayer corona_X_C9 corona_Y_C9 z 4.0 4.0 4.0 FALSE 
								GET_CAR_HEADING dodo_C9 heading
								IF heading < 240.0
								AND heading > 120.0 
									GOTO mission_casino9_CUT_hijack_shamal_setup 
								ENDIF
							ELSE
								TIMERA = 0
							ENDIF
							
							IF shamal_Y_C9 <= 3000.0
								
								PLANE_FLY_IN_DIRECTION shamal_C9 180.0 200.0 200.0

								fail_text_flag_C9 = 1
								$fail_text_C9 = CAS9_52
								GOTO mission_casino9_FAILED

							ENDIF 
						ENDIF
					BREAK

				ENDSWITCH
				
			ELSE

				IF NOT DOES_BLIP_EXIST dodo_blip_C9
					PRINT_NOW CAS9_04 8000 1
					ADD_BLIP_FOR_CAR dodo_C9 dodo_blip_C9
					SET_BLIP_AS_FRIENDLY dodo_blip_C9 TRUE
					REMOVE_BLIP goto_blip_C9
				ENDIF

			ENDIF
		ELSE
			fail_text_flag_C9 = 1
			$fail_text_C9 = CAS9_F1
			GOTO mission_casino9_FAILED
		ENDIF

	ENDIF

GOTO mission_casino9_MAIN_steal_plane






LVAR_INT dodo_door_C9 shamal_door_C9 cutscene_status_C9 in_air_stuff_C9
mission_casino9_CUT_hijack_shamal_setup:

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	DISPLAY_ZONE_NAMES FALSE

	in_air_stuff_C9 = 1
			
	LOAD_MISSION_AUDIO 3 SOUND_PLANE_DOOR_KICK
	
	SET_PLAYER_CONTROL player1 OFF
	IF NOT IS_CAR_DEAD shamal_C9
		SET_CAR_HEADING shamal_C9 0.0
		SET_PLANE_UNDERCARRIAGE_UP shamal_C9 TRUE
	ENDIF
	
	IF NOT IS_CAR_DEAD dodo_C9
		dodo_X_C9 = shamal_X_C9
		dodo_Y_C9 = shamal_Y_C9 - 25.0
		dodo_Z_C9 = shamal_Z_C9 + 20.0

		SET_CAR_COORDINATES dodo_C9 dodo_X_C9 dodo_Y_C9 dodo_Z_C9 
		SET_CAR_HEADING dodo_C9 0.0
		FREEZE_CAR_POSITION dodo_C9 TRUE
		SET_CAR_HEALTH dodo_C9 4000

		// just in case the player has just started to eject as cutscene started
		IF NOT IS_CHAR_IN_CAR scplayer dodo_C9
			WARP_CHAR_INTO_CAR scplayer dodo_C9
		ENDIF
	ENDIF
		
	DELETE_CHECKPOINT corona_C9 

	REQUEST_ANIMATION misc 

	WHILE NOT HAS_ANIMATION_LOADED MISC
		WAIT 0
	ENDWHILE

	cam_pos_X_C9 = -3.0 
	cam_pos_Y_C9 = -4.0
	cam_pos_Z_C9 = 0.0
	
	cam_look_X_C9 = -1.0
	cam_look_Y_C9 = 0.0
	cam_look_Z_C9 = 0.0

	IF NOT IS_CAR_DEAD dodo_C9
		ATTACH_CAMERA_TO_VEHICLE dodo_C9 cam_pos_X_C9 cam_pos_Y_C9 cam_pos_Z_C9 cam_look_X_C9 cam_look_Y_C9 cam_look_Z_C9 2.0 JUMP_CUT
	ENDIF

	cutscene_status_C9 = 0

	DO_FADE 400 FADE_IN
	SWITCH_WIDESCREEN ON

	TIMERA = 0
	TIMERB = 0

	CLEAR_WANTED_LEVEL player1
	SET_MAX_WANTED_LEVEL 0

SKIP_CUTSCENE_START





LVAR_FLOAT dodo_roll_C9

mission_casino9_CUT_hijack_shamal:
	
	WAIT 0

	IF NOT IS_CAR_DEAD shamal_C9
	AND NOT IS_CAR_DEAD dodo_C9
		
		SHAKE_CAM 20

		dodo_Y_C9 += 1.0
		SET_CAR_COORDINATES dodo_C9 dodo_X_C9 dodo_Y_C9 dodo_Z_C9

		shamal_Y_C9 += 1.0
		SET_CAR_COORDINATES shamal_C9 shamal_X_C9 shamal_Y_C9 shamal_Z_C9

		SWITCH cutscene_status_C9

			CASE 0
				
				IF TIMERB < 1000
					BREAK
				ENDIF

				ATTACH_CHAR_TO_CAR scplayer dodo_C9 0.0 0.0 0.0 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED

				TASK_PLAY_ANIM scplayer plane_exit MISC 1000.0 FALSE FALSE FALSE FALSE 0

					frame_time_C9[0] = 741
					door_ratio_C9[0] = 0.0

					frame_time_C9[1] = 794
					door_ratio_C9[1] = 0.33

					frame_time_C9[2] = 847
					door_ratio_C9[2] = 0.71

					frame_time_C9[3] = 953
					door_ratio_C9[3] = 1.0

					frame_time_C9[4] = 1006
					door_ratio_C9[4] = 0.87

					frame_time_C9[5] = 1059
					door_ratio_C9[5] = 0.83

					frame_time_C9[6] = 1165
					door_ratio_C9[6] = 0.81

					frame_time_C9[7] = 2118
					door_ratio_C9[7] = 0.81

					frame_time_C9[8] = 2277
					door_ratio_C9[8] = 0.78

					frame_time_C9[9] = 3336
					door_ratio_C9[9] = 0.78

					frame_time_C9[10] = 4000
					door_ratio_C9[10] = 0.78

					door_counter_C9	= 0
					step_size_C9 = 0.0
					animate_plane_C9 = dodo_C9

					
				//PLAY_OBJECT_ANIM dodo_door_C9 smlplane_door MISC 1000.0 FALSE TRUE
				
				cutscene_status_C9 ++					
			
			CASE 1	
				IF TIMERB < 1000
					BREAK
				ENDIF
				
				IF TIMERB < 1500
					GOSUB mission_casino9_SUB_animate_door
					BREAK
				ELSE
					TIMERA = 0
					cutscene_status_C9 ++
				ENDIF

				
			CASE 2

				GOSUB mission_casino9_SUB_animate_door

				IF HAS_MISSION_AUDIO_LOADED 3
					ATTACH_MISSION_AUDIO_TO_CAR 3 dodo_C9
					PLAY_MISSION_AUDIO 3

					cutscene_status_C9 ++	
				ELSE
					BREAK
				ENDIF
					
			CASE 3
				IF TIMERB < 5000
					IF cam_pos_Y_C9 < -1.4 
						cam_pos_Y_C9 += 0.1
						ATTACH_CAMERA_TO_VEHICLE dodo_C9 cam_pos_X_C9 cam_pos_Y_C9 cam_pos_Z_C9 cam_look_X_C9 cam_look_Y_C9 cam_look_Z_C9 2.0 JUMP_CUT
					ENDIF
					GOSUB mission_casino9_SUB_animate_door
					BREAK
				ENDIF
				cutscene_status_C9 ++

			CASE 4
				IF cam_pos_X_C9 > -7.0
					cam_pos_X_C9 -= 0.1
					cam_pos_Z_C9 -= 0.045
					ATTACH_CAMERA_TO_VEHICLE dodo_C9 cam_pos_X_C9 cam_pos_Y_C9 cam_pos_Z_C9 cam_look_X_C9 cam_look_Y_C9 cam_look_Z_C9 2.0 JUMP_CUT
				ENDIF

				LVAR_FLOAT anim_time_C9
				GET_CHAR_ANIM_CURRENT_TIME scplayer plane_exit anim_time_C9
				IF anim_time_C9 > 0.95
					
					SKIP_CUTSCENE_END

					DO_FADE 0 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					DETACH_CHAR_FROM_CAR scplayer
					FREEZE_CHAR_POSITION scplayer TRUE
					
					WHILE IS_CHAR_PLAYING_ANIM scplayer plane_exit
						WAIT 0
					ENDWHILE
					
					IF NOT IS_CAR_DEAD shamal_C9
						ATTACH_CHAR_TO_CAR scplayer shamal_C9 0.0 0.0 0.0 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
					ENDIF
						
					WAIT 0 // wait a frame so object realises its on screen and anim will show???

					DO_FADE 50 FADE_IN
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Plane_hijack MISC 1000.0 FALSE FALSE FALSE FALSE 0


					frame_time_C9[0] = 3229
					door_ratio_C9[0] = 0.0

					frame_time_C9[1] = 3282
					door_ratio_C9[1] = 0.04

					frame_time_C9[2] = 3335
					door_ratio_C9[2] = 0.09

					frame_time_C9[3] = 3388
					door_ratio_C9[3] = 0.14

					frame_time_C9[4] = 3440
					door_ratio_C9[4] = 0.19

					frame_time_C9[5] = 3493
					door_ratio_C9[5] = 0.22

					frame_time_C9[6] = 5028
					door_ratio_C9[6] = 0.22

					frame_time_C9[7] = 5081
					door_ratio_C9[7] = 0.15

					frame_time_C9[8] = 5134
					door_ratio_C9[8] = 0.08

					frame_time_C9[9] = 5187
					door_ratio_C9[9] = 0.01

					frame_time_C9[10] = 5240
					door_ratio_C9[10] = 0.0

					door_counter_C9	= 0
					step_size_C9 = 0.0
					animate_plane_C9 = shamal_C9

					// start
					cam_pos_X_C9 = -3.0
					cam_pos_Y_C9 = 6.0
					cam_pos_Z_C9 = -0.5
					cam_look_X_C9 = -2.0
					cam_look_Y_C9 = -8.0
					cam_look_Z_C9 = 1.0

					CAMERA_SET_SHAKE_SIMULATION_SIMPLE 1 20000.0 3.0

					TIMERA = 0						   
					TIMERB = 0
					
					IF NOT IS_CAR_DEAD dodo_C9
						FREEZE_CAR_POSITION dodo_C9 FALSE
					ENDIF
					dodo_roll_C9 = 0.0 
					SKIP_CUTSCENE_START

					cutscene_status_C9 = 5
				ELSE
					GOSUB mission_casino9_SUB_animate_door
					BREAK
				ENDIF 

			CASE 5
				IF TIMERB < 7000
				//IF IS_CHAR_PLAYING_ANIM scplayer Plane_hijack
				
					IF NOT IS_CAR_DEAD dodo_C9	
						dodo_roll_C9 -= 3.0
						
						dodo_X_C9 -= 0.3
						dodo_Y_C9 += 0.3
						dodo_Z_C9 -= 0.3
						
						SET_CAR_COORDINATES dodo_C9 dodo_X_C9 dodo_Y_C9 dodo_Z_C9
						SET_CAR_ROLL dodo_C9 dodo_roll_C9   
					ENDIF

					GOSUB mission_casino9_SUB_animate_door
					GOSUB mission_casino9_SUB_slide_camera_along

					BREAK
				ENDIF

				SKIP_CUTSCENE_END

				DO_FADE 0 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				CAMERA_RESET_NEW_SCRIPTABLES
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				DETACH_CHAR_FROM_CAR scplayer
				FREEZE_CHAR_POSITION scplayer TRUE

				WHILE IS_CHAR_PLAYING_ANIM scplayer Plane_hijack
					WAIT 0
				ENDWHILE 
				
				MARK_CHAR_AS_NO_LONGER_NEEDED pilot_C9
				MARK_CAR_AS_NO_LONGER_NEEDED dodo_C9
				MARK_CAR_AS_NO_LONGER_NEEDED shamal_C9
				
				MARK_MODEL_AS_NO_LONGER_NEEDED WMYPLT
				MARK_MODEL_AS_NO_LONGER_NEEDED DODO
				MARK_MODEL_AS_NO_LONGER_NEEDED SHAMAL
				
				REMOVE_ANIMATION MISC
				
				SET_AREA_VISIBLE 1
				
				SET_CHAR_COORDINATES scplayer 1.6127 34.7411 1199.0
				SET_CHAR_HEADING scplayer 180.0
				FREEZE_CHAR_POSITION scplayer FALSE
				
				SET_CHAR_AREA_VISIBLE scplayer 1
								
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				
				GOTO mission_casino9_MAIN_inside_shamal_setup
		ENDSWITCH

	ENDIF

GOTO mission_casino9_CUT_hijack_shamal	



LVAR_INT frame_time_C9[11] door_C9 door_status_C9 door_counter_C9 animate_plane_C9
LVAR_FLOAT door_ratio_C9[11] step_size_C9 current_door_ratio_C9
LVAR_FLOAT ft1_C9 ft2_C9 dr1_C9 dr2_C9

mission_casino9_SUB_animate_door:

	IF door_counter_C9 < 11
	
		IF TIMERA < frame_time_C9[door_counter_C9]
			current_door_ratio_C9 += step_size_C9
			 
			IF step_size_C9 >= 0.0
				IF current_door_ratio_C9 > door_ratio_C9[door_counter_C9]
					current_door_ratio_C9 = door_ratio_C9[door_counter_C9]	
				ENDIF  
			ELSE
				IF current_door_ratio_C9 < door_ratio_C9[door_counter_C9]
					current_door_ratio_C9 = door_ratio_C9[door_counter_C9]
				ENDIF  			
			ENDIF
 
		ELSE 
			ft1_C9 =# frame_time_C9[door_counter_C9]
			dr1_C9 = door_ratio_C9[door_counter_C9]

			door_counter_C9 ++
			
			IF door_counter_C9 > 10
				RETURN
			ENDIF
			ft2_C9 =# frame_time_C9[door_counter_C9]
			dr2_C9 = door_ratio_C9[door_counter_C9]

			ft2_C9 -= ft1_C9
			dr2_C9 -= dr1_C9
			 
			dr2_C9 /= ft2_C9
			step_size_C9 = dr2_C9 * ft2_C9

			current_door_ratio_C9 += step_size_C9
		ENDIF

	ENDIF
				 
	IF NOT IS_CAR_DEAD animate_plane_C9	
		OPEN_CAR_DOOR_A_BIT animate_plane_C9 FRONT_LEFT_DOOR current_door_ratio_C9
	ENDIF		

RETURN

LVAR_INT camera_finished_C9
mission_casino9_SUB_slide_camera_along:

	IF NOT IS_CAR_DEAD shamal_C9
		IF camera_finished_C9 = 0
			IF cam_pos_X_C9 <= -4.0
				camera_finished_C9 = 1
				RETURN
			ELSE  
				cam_pos_X_C9 += -0.02
				cam_pos_Y_C9 += 0.02
				cam_pos_Z_C9 += -0.01
				cam_look_Y_C9 += 0.23
				cam_look_Z_C9 += -0.02
			ENDIF
			ATTACH_CAMERA_TO_VEHICLE shamal_C9 cam_pos_X_C9 cam_pos_Y_C9 cam_pos_Z_C9 cam_look_X_C9 cam_look_Y_C9 cam_look_Z_C9 0.0 JUMP_CUT
		ENDIF
	ENDIF

RETURN


// hitman stuff			
LVAR_INT hitman_C9[4] hitman_status_C9[4] hitman_shooting_C9[4] hitman_timer_C9[4] hitman_timer_limit_C9[4] hitman_ducked_C9[4] hitman_opposite_C9[4]  
LVAR_INT total_hitmen_C9 hitman_standing_C9 hitmen_available_C9 last_hitman_standing_C9 hitman_health_C9

LVAR_INT hitman_order_C9[4] hitman_pointer_C9
LVAR_FLOAT min_heading_C9[4] max_heading_C9[4] min_Z_C9[4] max_Z_C9[4] 

// player stuff
LVAR_INT player_hiding_C9 player_status_C9
LVAR_INT lean_box_C9
LVAR_FLOAT lean_X_C9 lean_Y_C9 lean_Z_C9 

LVAR_INT baggage_doors_C9[3]

mission_casino9_MAIN_inside_shamal_setup:
	//view_integer_variable player_hiding_c9 player_hiding_c9
	in_air_stuff_C9 = 0
 

	// mafia
	REQUEST_MODEL VMAFF1
	REQUEST_MODEL VMAFF2
	REQUEST_MODEL VMAFF3
	REQUEST_MODEL VMAFF4

	// weapons
	REQUEST_MODEL COLT45

	// objects
	REQUEST_MODEL JET_BAGGAGE_DOOR
	REQUEST_MODEL CARDBOARDBOX2

	//actual jet - help streaming
	REQUEST_MODEL jet_interior
	
	WHILE NOT HAS_MODEL_LOADED VMAFF1
	OR NOT HAS_MODEL_LOADED VMAFF2
	OR NOT HAS_MODEL_LOADED VMAFF3
	OR NOT HAS_MODEL_LOADED VMAFF4
	OR NOT HAS_MODEL_LOADED COLT45
	OR NOT HAS_MODEL_LOADED JET_BAGGAGE_DOOR
		WAIT 0
	ENDWHILE
	
	WHILE NOT HAS_MODEL_LOADED CARDBOARDBOX2
	OR NOT HAS_MODEL_LOADED jet_interior
		WAIT 0
	ENDWHILE

	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_C9

	LOAD_SCENE_IN_DIRECTION 2.3947 32.9835 1198.5938 155.9863

	lean_X_C9 = 2.90
	lean_Y_C9 = 32.4
	lean_Z_C9 = 1198.75
	
	// 0 = standing, 1 = moving, 2 = ducked
	player_status_C9 = 0

	CREATE_OBJECT CARDBOARDBOX2 lean_X_C9 lean_Y_C9 lean_Z_C9 lean_box_C9
	SET_OBJECT_AREA_VISIBLE lean_box_C9 1
	
	SET_OBJECT_DYNAMIC lean_box_C9 FALSE
	SET_OBJECT_COLLISION lean_box_C9 FALSE
	SET_OBJECT_VISIBLE lean_box_C9 FALSE
		
	ATTACH_CHAR_TO_OBJECT scplayer lean_box_C9 0.0 0.0 0.5 FACING_BACK 85.0 WEAPONTYPE_PISTOL 
	SET_HEADING_FOR_ATTACHED_PLAYER player1 30.0 30.0
	FREEZE_CHAR_POSITION scplayer TRUE
	
	DISPLAY_RADAR FALSE
 
  	// hitman[0] front left
  	CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2.9739 28.7207 1198.5938 hitman_C9[0] 
  	SET_CHAR_AREA_VISIBLE hitman_C9[0] 1
	SET_CHAR_HEADING hitman_C9[0] 0.0
	SET_CHAR_HEALTH hitman_C9[0] 150 

	GIVE_WEAPON_TO_CHAR hitman_C9[0] WEAPONTYPE_PISTOL 3000
	SET_CURRENT_CHAR_WEAPON hitman_C9[0] WEAPONTYPE_PISTOL

	SET_CHAR_DECISION_MAKER hitman_C9[0] empty_dm_C9
	SET_CHAR_NEVER_TARGETTED hitman_C9[0] TRUE
	TASK_STAY_IN_SAME_PLACE hitman_C9[0] TRUE
	TASK_SIT_DOWN hitman_C9[0] 80000
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hitman_C9[0] FALSE

	SET_CHAR_SHOOT_RATE hitman_C9[0] 90
	SET_CHAR_ACCURACY hitman_C9[0] 0 

	hitman_opposite_C9[0] = 2

	min_heading_C9[0] =	197.6
	max_heading_C9[0] =	214.0
	min_Z_C9[0]	= 1199.851
	max_Z_C9[0]	= 1200.366


	// hitman[1] front right
	CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 0.5102 27.6804 1198.5938 hitman_C9[1] 
	SET_CHAR_AREA_VISIBLE hitman_C9[1] 1
	SET_CHAR_HEADING hitman_C9[1] 0.0 
	SET_CHAR_HEALTH hitman_C9[1] 150
	
	GIVE_WEAPON_TO_CHAR hitman_C9[1] WEAPONTYPE_PISTOL 3000
	SET_CURRENT_CHAR_WEAPON hitman_C9[1] WEAPONTYPE_PISTOL
	
	SET_CHAR_DECISION_MAKER hitman_C9[1] empty_dm_C9
	SET_CHAR_NEVER_TARGETTED hitman_C9[1] TRUE
	TASK_STAY_IN_SAME_PLACE hitman_C9[1] TRUE
	TASK_SIT_DOWN hitman_C9[1] 80000	
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hitman_C9[1] FALSE

	SET_CHAR_SHOOT_RATE hitman_C9[1] 90
	SET_CHAR_ACCURACY hitman_C9[1] 0 

	hitman_opposite_C9[1] = 3

	min_heading_C9[1] =	158.7
	max_heading_C9[1] =	170.2
	min_Z_C9[1]	= 1199.9723
	max_Z_C9[1]	= 1200.34

	
	// hitman[2] back left
	CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2.8831 26.4404 1198.6011 hitman_C9[2] 
	SET_CHAR_AREA_VISIBLE hitman_C9[2] 1
	SET_CHAR_HEADING hitman_C9[2] 0.0  
	SET_CHAR_HEALTH hitman_C9[2] 150
	
	GIVE_WEAPON_TO_CHAR hitman_C9[2] WEAPONTYPE_PISTOL 3000
	SET_CURRENT_CHAR_WEAPON hitman_C9[2] WEAPONTYPE_PISTOL
		
	SET_CHAR_DECISION_MAKER hitman_C9[2] empty_dm_C9
	SET_CHAR_NEVER_TARGETTED hitman_C9[2] TRUE
	TASK_STAY_IN_SAME_PLACE hitman_C9[2] TRUE
	TASK_SIT_DOWN hitman_C9[2] 80000
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hitman_C9[2] FALSE

	SET_CHAR_SHOOT_RATE hitman_C9[2] 90
	SET_CHAR_ACCURACY hitman_C9[2] 0 

	hitman_opposite_C9[2] = 0
	
	min_heading_C9[2] =	189.4
	max_heading_C9[2] =	196.9
	min_Z_C9[2]	= 200.1145
	max_Z_C9[2]	= 200.3232

	
	// hitman[3] back right			
	CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 0.5068 25.3824 1198.6011 hitman_C9[3] 
  	SET_CHAR_AREA_VISIBLE hitman_C9[3] 1
	SET_CHAR_HEADING hitman_C9[3] 0.0
	SET_CHAR_HEALTH hitman_C9[3] 150 

	GIVE_WEAPON_TO_CHAR hitman_C9[3] WEAPONTYPE_PISTOL 3000
	SET_CURRENT_CHAR_WEAPON hitman_C9[3] WEAPONTYPE_PISTOL

	SET_CHAR_DECISION_MAKER hitman_C9[3] empty_dm_C9
	SET_CHAR_NEVER_TARGETTED hitman_C9[3] TRUE
	TASK_STAY_IN_SAME_PLACE hitman_C9[3] TRUE
	TASK_SIT_DOWN hitman_C9[3] 80000
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hitman_C9[3] FALSE

	SET_CHAR_SHOOT_RATE hitman_C9[3] 90
	SET_CHAR_ACCURACY hitman_C9[3] 0 

	hitman_opposite_C9[3] = 1
	
	min_heading_C9[3] =	167.51
	max_heading_C9[3] =	174.11
	min_Z_C9[3]	= 1200.1476
	max_Z_C9[3]	= 1200.3279

	// start audio
	$audio_text_enter_C9[0] = &CAS9_AA
	audio_sound_enter_C9[0]= SOUND_CAS9_AA
	audio_speaker_enter_C9[0] = hitman_C9[0]
	$audio_text_enter_C9[1] = &CAS9_AB
	audio_sound_enter_C9[1]= SOUND_CAS9_AB
	audio_speaker_enter_C9[1] = hitman_C9[1]
	$audio_text_enter_C9[2] = &CAS9_AC
	audio_sound_enter_C9[2]= SOUND_CAS9_AC
	audio_speaker_enter_C9[2] = hitman_C9[2]
	$audio_text_enter_C9[3] = &CAS9_AD
	audio_sound_enter_C9[3]= SOUND_CAS9_AD
	audio_speaker_enter_C9[3] = hitman_C9[2]

	// fight audio
	$audio_text_fight_C9[0] = &CAS9_BA
	audio_sound_fight_C9[0] = SOUND_CAS9_BA
	$audio_text_fight_C9[1] = &CAS9_BB
	audio_sound_fight_C9[1] = SOUND_CAS9_BB
	$audio_text_fight_C9[2] = &CAS9_BC
	audio_sound_fight_C9[2] = SOUND_CAS9_BC
	$audio_text_fight_C9[3] = &CAS9_BD
	audio_sound_fight_C9[3] = SOUND_CAS9_BD
	
	// hide audio
	$audio_text_hide_C9[0] = &CAS9_CA
	audio_sound_hide_C9[0] = SOUND_CAS9_CA
	$audio_text_hide_C9[1] = &CAS9_CB
	audio_sound_hide_C9[1] = SOUND_CAS9_CB
	$audio_text_hide_C9[2] = &CAS9_CC
	audio_sound_hide_C9[2] = SOUND_CAS9_CC
	$audio_text_hide_C9[3] = &CAS9_CD
	audio_sound_hide_C9[3] = SOUND_CAS9_CD

		
	SET_PLAYER_FIRE_BUTTON player1 FALSE
	SET_PLAYER_DUCK_BUTTON player1 FALSE

	player_hiding_C9 = FALSE

	/// HITMEN SETUP
	hitman_status_C9[0] = 0
	hitman_status_C9[1] = 0
	hitman_status_C9[2] = 0
	hitman_status_C9[3] = 0

	hitmen_available_C9 = 4
	last_hitman_standing_C9 = -1
	hitman_standing_C9 = -1

	CREATE_OBJECT_NO_OFFSET JET_BAGGAGE_DOOR 0.752 25.518 1201.15 baggage_doors_C9[0]
	CREATE_OBJECT_NO_OFFSET JET_BAGGAGE_DOOR 0.752 28.024 1201.15 baggage_doors_C9[1]
	CREATE_OBJECT_NO_OFFSET JET_BAGGAGE_DOOR 0.752 30.403 1201.15 baggage_doors_C9[2]

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN ON

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE
		
	DO_FADE 500 FADE_IN
	
	PRINT_HELP_FOREVER CAS9_H2

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 2000
		WAIT 0
	ENDWHILE

	SET_PLAYER_FIRE_BUTTON player1 TRUE
	SWITCH_WIDESCREEN OFF
	
	help_text_timer_C9 = 0

	GET_GAME_TIMER last_frame_time_C9

LVAR_INT this_frame_time_C9 last_frame_time_C9 time_diff_C9 random_C9 inside_mode_C9 hitman_sequence_C9 player_pos_timer_C9  
LVAR_INT help_text_timer_C9 help_text_flag_C9

mission_casino9_MAIN_inside_shamal:

	WAIT 0
					
	// update main timer
	GET_GAME_TIMER this_frame_time_C9
	time_diff_C9 = this_frame_time_C9 - last_frame_time_C9 
	last_frame_time_C9 = this_frame_time_C9

	// update custom timers
	hitman_timer_C9[0] += time_diff_C9
	hitman_timer_C9[1] += time_diff_C9
	hitman_timer_C9[2] += time_diff_C9
	hitman_timer_C9[3] += time_diff_C9

	help_text_timer_C9 += time_diff_C9

	// makes player duck and lean left and right
	GOSUB mission_casino9_SUB_player_controls

	// help text (ducking)
	GOSUB mission_casino9_help_text

	IF inside_mode_C9 = 0
		
		IF player_hiding_C9 = 0
		OR IS_BUTTON_PRESSED PAD1 CIRCLE
		OR IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1

			IF NOT IS_CHAR_DEAD hitman_C9[0]
			AND NOT IS_CHAR_DEAD hitman_C9[1]
			AND NOT IS_CHAR_DEAD hitman_C9[2]
			AND NOT IS_CHAR_DEAD hitman_C9[3]

				// hitman[0] get up from chair turn and duck
				CLEAR_CHAR_TASKS hitman_C9[0]
				TASK_GO_TO_COORD_WHILE_SHOOTING hitman_C9[0] 3.0228 29.7474 1198.5938 PEDMOVE_RUN 20.0 0.5 scplayer
				hitman_status_C9[0] = -1

				// hitman[1] get up from chair turn and duck
				CLEAR_CHAR_TASKS hitman_C9[1]
				TASK_GO_TO_COORD_WHILE_SHOOTING hitman_C9[1] 0.7390 28.6978 1198.5938 PEDMOVE_RUN 20.0 0.5 scplayer 
				hitman_status_C9[1] = -1

				// hitman[2] get up from chair move and duck
				CLEAR_CHAR_TASKS hitman_C9[2]
			   	TASK_GO_TO_COORD_WHILE_SHOOTING hitman_C9[2] 2.9224 27.2966 1198.6011 PEDMOVE_RUN 20.0 0.5 scplayer
				hitman_status_C9[2] = -1
			   
				// hitman[3] get up from chair move and duck
				CLEAR_CHAR_TASKS hitman_C9[3]
				TASK_GO_TO_COORD_WHILE_SHOOTING hitman_C9[3] 0.5596 26.2475 1198.6011 PEDMOVE_RUN 20.0 0.5 scplayer
				hitman_status_C9[3] = -1

				inside_mode_C9 = 1
			ENDIF
		ENDIF

	ELSE 
		
		IF NOT audio_status_C9 = 2
			GOSUB mission_casino9_AUDIO_dialogue
		ENDIF

		IF NOT hitmen_available_C9 = 0 
						
			// checks hitmen in random order - used to avoid loads of checks later on
			GENERATE_RANDOM_INT_IN_RANGE 0 24 random_C9 
			
			SWITCH random_C9
				
				CASE 0
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 3
				BREAK
				CASE 1 
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 2
		 		BREAK
				CASE 2 
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 3
		 		BREAK
				CASE 3 
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 1
		 		BREAK
				CASE 4 
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 2
		 		BREAK
				CASE 5 
					hitman_order_C9[0] = 0
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 1
				BREAK
				CASE 6
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 3
				BREAK
				CASE 7 
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 2
		 		BREAK
				CASE 8 
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 3
		 		BREAK
				CASE 9 
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 0
		 		BREAK
				CASE 10 
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 2
		 		BREAK
				CASE 11 
					hitman_order_C9[0] = 1
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 0
				BREAK
				CASE 12
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 3
				BREAK
				CASE 13 
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 1
		 		BREAK
				CASE 14 
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 3
		 		BREAK
				CASE 15 
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 3
					hitman_order_C9[3] = 0
		 		BREAK
				CASE 16 
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 1
		 		BREAK
				CASE 17 
					hitman_order_C9[0] = 2
					hitman_order_C9[1] = 3
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 0
				BREAK

				CASE 18
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 2
				BREAK
				CASE 19 
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 0
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 1
		 		BREAK
				CASE 20 
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 2
		 		BREAK
				CASE 21 
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 1
					hitman_order_C9[2] = 2
					hitman_order_C9[3] = 0
		 		BREAK
				CASE 22 
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 0
					hitman_order_C9[3] = 1
		 		BREAK
				CASE 23 
					hitman_order_C9[0] = 3
					hitman_order_C9[1] = 2
					hitman_order_C9[2] = 1
					hitman_order_C9[3] = 0
				BREAK

			ENDSWITCH

			GOSUB mission_casino9_SUB_hitmen

		ELSE
			GOTO mission_casino9_MAIN_pilot_entry
		ENDIF
	ENDIF

GOTO mission_casino9_MAIN_inside_shamal


mission_casino9_SUB_hitmen:

	pointer_C9 = 0

	WHILE pointer_C9 < 4
		
		hitman_pointer_C9 = hitman_order_C9[pointer_C9]
	
		IF NOT IS_CHAR_DEAD hitman_C9[hitman_pointer_C9]
			
			SWITCH hitman_status_C9[hitman_pointer_C9]
				
				CASE -1 // running into position

					GET_SCRIPT_TASK_STATUS hitman_C9[hitman_pointer_C9] TASK_GO_TO_COORD_WHILE_SHOOTING task_progress_C9
	                IF task_progress_C9 = FINISHED_TASK
						FREEZE_CHAR_POSITION hitman_C9[hitman_pointer_C9] TRUE
						TASK_TOGGLE_DUCK hitman_C9[hitman_pointer_C9] TRUE
						SET_CHAR_ACCURACY hitman_C9[hitman_pointer_C9] 65
						hitman_status_C9[hitman_pointer_C9] = 0	
					ENDIF

				CASE 0 // ducked down not shooting

					IF hitman_standing_C9 = -1
						

						GET_SCRIPT_TASK_STATUS hitman_C9[hitman_pointer_C9] TASK_TOGGLE_DUCK task_progress_C9
						IF task_progress_C9 = FINISHED_TASK

							// check to make hitmen pop up either side of plane if still alive
							pointer2_C9 = hitman_opposite_C9[hitman_pointer_C9]
				
							IF NOT last_hitman_standing_C9 = pointer2_C9
								hitman_standing_C9 = hitman_pointer_C9
							ELSE
								IF hitmen_available_C9 = 1
									hitman_standing_C9 = hitman_pointer_C9
								ELSE
									IF hitmen_available_C9 = 2
										IF NOT IS_CHAR_DEAD hitman_C9[pointer2_C9]
											hitman_standing_C9 = hitman_pointer_C9	
										ENDIF
									ENDIF 
								ENDIF
							ENDIF

						ENDIF

						IF hitman_standing_C9 = hitman_pointer_C9

							hitman_status_C9[hitman_pointer_C9] = 4
							hitman_shooting_C9[hitman_pointer_C9] = 0
							hitman_timer_C9[hitman_pointer_C9] = 0

							GET_CHAR_HEALTH hitman_C9[hitman_pointer_C9] hitman_health_C9
							hitman_timer_limit_C9[hitman_pointer_C9] = hitman_health_C9 * 15

							IF hitman_timer_limit_C9[hitman_pointer_C9] < 2000
								hitman_timer_limit_C9[hitman_pointer_C9] = 2000
							ELSE
								IF hitman_timer_limit_C9[hitman_pointer_C9] > 5000
									hitman_timer_limit_C9[hitman_pointer_C9] = 5000
								ENDIF
							ENDIF

							CLEAR_CHAR_TASKS/*_IMMEDIATELY*/ hitman_C9[hitman_pointer_C9] 
															
							TASK_TOGGLE_DUCK hitman_C9[hitman_pointer_C9] FALSE

							SET_CHAR_ALLOWED_TO_DUCK hitman_C9[hitman_pointer_C9] FALSE

						ENDIF  
							
					ENDIF

				BREAK

				CASE 1 // stood up shooting

					// check if this hitman has been stood up for enough time, or player is aiming at him
					IF hitman_standing_C9 = hitman_pointer_C9
						
						IF NOT hitmen_available_C9 = 1
							
							IF hitman_timer_C9[hitman_pointer_C9] >= hitman_timer_limit_C9[hitman_pointer_C9]
								
								//hitman_standing_C9 = -1
								// make hitman stay standing for a short time before he sits down
								hitman_status_C9[hitman_pointer_C9] = 2
								hitman_timer_C9[hitman_pointer_C9] = 0
								hitman_timer_limit_C9[hitman_pointer_C9] = 500
								BREAK
								
							ELSE
								GET_CHAR_HEADING scplayer heading
								
								IF heading >= min_heading_C9[hitman_pointer_C9]
								AND heading <= max_heading_C9[hitman_pointer_C9] 
								
									GET_ACTIVE_CAMERA_POINT_AT x y z

									IF z >= min_Z_C9[hitman_pointer_C9]
									AND z <= max_Z_C9[hitman_pointer_C9]
										//hitman_standing_C9 = -1
										
										// make hitman stay standing for a short time before he sits down
										hitman_status_C9[hitman_pointer_C9] = 2
										hitman_timer_C9[hitman_pointer_C9] = 0
										hitman_timer_limit_C9[hitman_pointer_C9] = 700
										BREAK
									ENDIF
								ENDIF
									
							ENDIF
						ENDIF 
						
						IF player_hiding_C9 = 0
							
							IF NOT hitman_shooting_C9[hitman_pointer_C9] = 1
								hitman_shooting_C9[hitman_pointer_C9] = 1
								CLEAR_CHAR_TASKS/*_IMMEDIATELY*/ hitman_C9[hitman_pointer_C9] 

								TASK_KILL_CHAR_ON_FOOT hitman_C9[hitman_pointer_C9] scplayer

							ENDIF
							
							IF fight_hide_status_C9 = 1
							AND audio_fight_used_C9[hitman_pointer_C9] = 0
								
								load_sample = audio_sound_fight_C9[hitman_pointer_C9]
								$load_text = $audio_text_fight_C9[hitman_pointer_C9]
								START_NEW_SCRIPT audio_load_and_play 2 100 hitman_C9[hitman_pointer_C9] 
								
								fight_hide_status_C9 = 0
								TIMERB = 0
								audio_fight_used_C9[hitman_pointer_C9] ++
							ENDIF
							 
						ELSE

							IF fight_hide_status_C9 = 2
							AND audio_hide_used_C9[hitman_pointer_C9] = 0
								
								load_sample = audio_sound_hide_C9[hitman_pointer_C9]
								$load_text = $audio_text_hide_C9[hitman_pointer_C9]
								START_NEW_SCRIPT audio_load_and_play 2 100 hitman_C9[hitman_pointer_C9] 
								
								fight_hide_status_C9 = 0
								TIMERB = 0
								audio_hide_used_C9[hitman_pointer_C9] ++
							ENDIF

							IF NOT hitman_shooting_C9[hitman_pointer_C9] = 2	
								hitman_shooting_C9[hitman_pointer_C9] = 2
								CLEAR_CHAR_TASKS/*_IMMEDIATELY*/ hitman_C9[hitman_pointer_C9]
								TASK_SHOOT_AT_COORD hitman_C9[hitman_pointer_C9] 1.8121 37.2617 1200.3959 -1
							ENDIF 
						ENDIF

					ENDIF
				BREAK

				CASE 2 // 

					IF hitman_timer_C9[hitman_pointer_C9] >= hitman_timer_limit_C9[hitman_pointer_C9]  
						hitman_status_C9[hitman_pointer_C9] = 0
						CLEAR_CHAR_TASKS/*_IMMEDIATELY*/ hitman_C9[hitman_pointer_C9]
						SET_CHAR_ALLOWED_TO_DUCK hitman_C9[hitman_pointer_C9] TRUE
						
						GET_SCRIPT_TASK_STATUS hitman_C9[hitman_pointer_C9] TASK_TOGGLE_DUCK task_progress_C9
						IF task_progress_C9 = FINISHED_TASK 
							TASK_TOGGLE_DUCK hitman_C9[hitman_pointer_C9] TRUE
						ELSE
							hitman_status_C9[hitman_pointer_C9] = 3	
						ENDIF
						last_hitman_standing_C9 = hitman_pointer_C9
						hitman_standing_C9 = -1
							
					ELSE
						IF player_hiding_C9 = FALSE
							IF NOT hitman_shooting_C9[hitman_pointer_C9] = 1
								hitman_shooting_C9[hitman_pointer_C9] = 1
								CLEAR_CHAR_TASKS hitman_C9[hitman_pointer_C9] 
								TASK_KILL_CHAR_ON_FOOT hitman_C9[hitman_pointer_C9] scplayer
							ENDIF 
						ELSE
							IF NOT hitman_shooting_C9[hitman_pointer_C9] = 2	
								hitman_shooting_C9[hitman_pointer_C9] = 2
								CLEAR_CHAR_TASKS hitman_C9[hitman_pointer_C9]
								TASK_SHOOT_AT_COORD hitman_C9[hitman_pointer_C9] 1.8121 37.2617 1200.3959 -1
							ENDIF 
						ENDIF
					ENDIF
				BREAK

				// wanting to sit but for some reason couldnt
				CASE 3 
					GET_SCRIPT_TASK_STATUS hitman_C9[hitman_pointer_C9] TASK_TOGGLE_DUCK task_progress_C9
					IF task_progress_C9 = FINISHED_TASK 
						CLEAR_CHAR_TASKS hitman_C9[hitman_pointer_C9]
						TASK_TOGGLE_DUCK hitman_C9[hitman_pointer_C9] TRUE
						hitman_status_C9[hitman_pointer_C9] = 0	
					ENDIF
				BREAK
				
				CASE 4
					IF hitman_timer_C9[hitman_pointer_C9] >= 200
						hitman_timer_C9[hitman_pointer_C9] = 0
						hitman_status_C9[hitman_pointer_C9] = 1
					ENDIF
				BREAK

			ENDSWITCH		

		ELSE
			IF NOT hitman_status_C9[hitman_pointer_C9] = -2
				
				hitman_status_C9[hitman_pointer_C9] = -2
				hitmen_available_C9 --

				IF hitman_standing_C9 = hitman_pointer_C9
					last_hitman_standing_C9 = hitman_pointer_C9
					hitman_standing_C9 = -1
				ENDIF

			ENDIF
		ENDIF

		pointer_C9 ++

	ENDWHILE

RETURN


LVAR_INT moving_stick_C9
mission_casino9_SUB_player_controls:

	GET_OBJECT_COORDINATES lean_box_C9 lean_X_C9 lean_Y_C9 lean_Z_C9
	
	// PLAYER HIDING - also sets hitmen text
	IF lean_X_C9 > 2.6
	OR lean_X_C9 < 0.2
		IF player_hiding_C9 = 0
			player_hiding_C9 = 1
			TIMERB = 0
		ELSE
			IF TIMERB > 9000
				IF audio_status_C9 = 2
					fight_hide_status_C9 = 2
				ENDIF
			ENDIF		
		ENDIF
	ELSE
		IF player_hiding_C9 = 1
			player_hiding_C9 = 0
			TIMERB = 0
		ELSE
			IF TIMERB > 9000
				IF audio_status_C9 = 2
					fight_hide_status_C9 = 1
				ENDIF			
			ENDIF
		ENDIF
	ENDIF

	// FIRST PERSON CONTROLS IN PLANE

	IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
	OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
		moving_stick_C9 = 1
	ELSE
		moving_stick_C9 = 0
	ENDIF

	
	IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
	AND NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2

		// NONE
		SLIDE_OBJECT lean_box_C9 1.7 lean_Y_C9 lean_Z_C9 0.18 0.0 0.0 FALSE
		IF NOT lean_X_C9 = 1.7
		AND moving_stick_C9 = 0 
			SET_HEADING_FOR_ATTACHED_PLAYER player1 90.0 9.0
		ENDIF

	ELSE

		// IF BOTH BUTTONS ARE PRESSED JUST GO BACK TO THE CENTER
		IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
		AND IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2

			SLIDE_OBJECT lean_box_C9 1.7 lean_Y_C9 lean_Z_C9 0.18 0.0 0.0 FALSE
			IF NOT lean_X_C9 = 1.7
			AND moving_stick_C9 = 0
				SET_HEADING_FOR_ATTACHED_PLAYER player1 90.0 9.0
			ENDIF		 				

		ELSE
			// LEAN LEFT
			IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
				SLIDE_OBJECT lean_box_C9 2.9 lean_Y_C9 lean_Z_C9 0.18 0.0 0.0 FALSE
				IF lean_X_C9 < 2.9
				AND moving_stick_C9 = 0
					SET_HEADING_FOR_ATTACHED_PLAYER player1 30.0 9.0
				ENDIF
			ELSE
				// LEAN RIGHT
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
					SLIDE_OBJECT lean_box_C9 0.5 lean_Y_C9 lean_Z_C9 0.18 0.0 0.0 FALSE
					IF lean_X_C9 > 0.5
					AND moving_stick_C9 = 0
						SET_HEADING_FOR_ATTACHED_PLAYER player1 150.0 9.0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
			
	ENDIF
	 	
RETURN



mission_casino9_help_text:

	SWITCH help_text_flag_C9
		
		CASE 0
			IF help_text_timer_C9 >= 6000
				CLEAR_HELP 
				help_text_flag_C9 ++
				help_text_timer_C9 = 0
			ENDIF	 
		BREAK

		CASE 1
			IF player_hiding_C9 = 0
				IF help_text_timer_C9 > 20000
					PRINT_HELP_FOREVER CAS9_H2
					help_text_flag_C9 ++
					help_text_timer_C9 = 0
				ENDIF
			ELSE
				help_text_timer_C9 = 0	
			ENDIF
		BREAK

		CASE 2
			IF help_text_timer_C9 > 6000
				CLEAR_HELP
				help_text_flag_C9 --
				help_text_timer_C9 = 0	
			ENDIF									
		BREAK

	ENDSWITCH
 
RETURN

LVAR_INT audio_status_C9 audio_pointer_C9 fight_hide_status_C9
LVAR_INT audio_sound_enter_C9[4] audio_speaker_enter_C9[4] audio_sound_fight_C9[4] audio_sound_hide_C9[4] audio_fight_used_C9[4] audio_hide_used_C9[4]
LVAR_TEXT_LABEL audio_text_enter_C9[4] audio_text_fight_C9[4] audio_text_hide_C9[4] 

mission_casino9_AUDIO_dialogue:

	SWITCH audio_status_C9

		CASE 0
			IF audio_pointer_C9 < 4
				load_sample = audio_sound_enter_C9[audio_pointer_C9]
				$load_text = $audio_text_enter_C9[audio_pointer_C9]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_enter_C9[audio_pointer_C9]
				audio_pointer_C9 ++
				audio_status_C9 ++
			ELSE
				TIMERB = 0
				audio_status_C9 = 2
				BREAK
			ENDIF
		CASE 1
			IF SLOT_status[preload_slot] = -3
				audio_status_C9 --
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK
		
	ENDSWITCH	

RETURN





mission_casino9_MAIN_pilot_entry:

	IF NOT SLOT_status[0] < 0
		SLOT_status[0] = -2
	ENDIF
	IF NOT SLOT_status[0] < 0
		SLOT_status[1] = -2
	ENDIF

 	CLEAR_PRINTS
	CLEAR_HELP

	REQUEST_MODEL WMYPLT

	WHILE NOT HAS_MODEL_LOADED WMYPLT
		WAIT 0
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE
		
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	DETACH_CHAR_FROM_CAR scplayer
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	FREEZE_CHAR_POSITION scplayer FALSE

	SET_FIXED_CAMERA_POSITION 2.7271 24.9650 1201.5353 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2.4220 25.7162 1200.9501 JUMP_CUT
 
	SET_CHAR_COORDINATES scplayer 1.8227 31.0635 1198.6089  
	SET_CHAR_HEADING scplayer 180.0

	SET_OBJECT_COORDINATES lean_box_C9 1.7 26.8 1198.75   

	OPEN_SEQUENCE_TASK sequence_C9
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_STAND_STILL -1 1000
		TASK_GO_STRAIGHT_TO_COORD -1 1.7221 26.7679 1198.6016 PEDMOVE_WALK -1
	CLOSE_SEQUENCE_TASK sequence_C9

	PERFORM_SEQUENCE_TASK scplayer sequence_C9
	CLEAR_SEQUENCE_TASK sequence_C9

	DO_FADE 500 FADE_IN

	TIMERA = 0

	WHILE TIMERA < 3000
		WAIT 0	
	ENDWHILE
	
	CREATE_CHAR PEDTYPE_MISSION1 WMYPLT 1.8810 34.9202 1198.6016 pilot_C9
	SET_CHAR_HEADING pilot_C9 180.0
	GIVE_WEAPON_TO_CHAR pilot_C9 WEAPONTYPE_PISTOL 3000
	SET_CURRENT_CHAR_WEAPON pilot_C9 WEAPONTYPE_PISTOL
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD pilot_C9 FALSE
	SET_CHAR_SHOOT_RATE pilot_C9 70 
	SET_CHAR_ACCURACY pilot_C9 65
		
	SET_CHAR_DECISION_MAKER pilot_C9 empty_dm_C9
	SET_CHAR_NEVER_TARGETTED pilot_C9 TRUE
	TASK_STAY_IN_SAME_PLACE pilot_C9 TRUE

	TASK_GO_TO_COORD_ANY_MEANS pilot_C9 1.7462 32.3896 1198.6016 PEDMOVE_WALK -1

	TIMERA = 0

	WHILE TIMERA < 3000
		WAIT 0	
	ENDWHILE

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	RESTORE_CAMERA
	
	TASK_TOGGLE_DUCK scplayer FALSE
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	ATTACH_CHAR_TO_OBJECT scplayer lean_box_C9 0.0 0.0 0.5 FACING_FORWARD 85.0 WEAPONTYPE_PISTOL 
	SET_HEADING_FOR_ATTACHED_PLAYER player1 270.0 270.0
	FREEZE_CHAR_POSITION scplayer TRUE

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	DO_FADE 0 FADE_IN
	
	pilot_status_C9 = 0

	load_sample = SOUND_CAS9_DA
	$load_text = &CAS9_DA
	START_NEW_SCRIPT audio_load_and_play 2 100 pilot_C9 

	IF NOT IS_CHAR_DEAD pilot_C9
		TASK_KILL_CHAR_ON_FOOT pilot_C9 scplayer
	ENDIF


LVAR_INT pilot_status_C9
mission_casino9_MAIN_pilot_attack:

	WAIT 0 

	IF IS_CHAR_DEAD pilot_C9
		GOTO mission_casino9_MAIN_fly_back
	ENDIF
	 
GOTO mission_casino9_MAIN_pilot_attack


mission_casino9_MAIN_fly_back:
	REQUEST_MODEL SHAMAL
	WHILE NOT HAS_MODEL_LOADED SHAMAL
		WAIT 0
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE
		
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	FREEZE_CHAR_POSITION scplayer FALSE

	DETACH_CHAR_FROM_CAR scplayer
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	DISPLAY_RADAR TRUE

	SET_FIXED_CAMERA_POSITION 3.0968 36.5960 1200.3688 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2.4708 35.9141 1199.9906 JUMP_CUT
 
	SET_CHAR_COORDINATES scplayer 1.7462 32.3896 1198.6016  
	SET_CHAR_HEADING scplayer 0.0

	OPEN_SEQUENCE_TASK sequence_C9
		TASK_STAND_STILL -1 550
		TASK_GO_TO_COORD_ANY_MEANS -1 1.7037 34.9246 1198.6016 PEDMOVE_WALK -1
	CLOSE_SEQUENCE_TASK sequence_C9

	PERFORM_SEQUENCE_TASK scplayer sequence_C9
	CLEAR_SEQUENCE_TASK sequence_C9

	DO_FADE 500 FADE_IN

	TIMERA = 0
	WHILE TIMERA < 2500
		WAIT 0	
	ENDWHILE

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[0]  
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[2]
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[3]
 
	MARK_CHAR_AS_NO_LONGER_NEEDED pilot_C9

	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPLT

	MARK_MODEL_AS_NO_LONGER_NEEDED jet_interior

	DELETE_OBJECT lean_box_C9
	DELETE_OBJECT baggage_doors_C9[0]
	DELETE_OBJECT baggage_doors_C9[1]
	DELETE_OBJECT baggage_doors_C9[2]
	
	MARK_MODEL_AS_NO_LONGER_NEEDED JET_BAGGAGE_DOOR
	MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX2

	SET_AREA_VISIBLE 0
	SET_CHAR_AREA_VISIBLE scplayer 0

	LOAD_SCENE 1405.0 6000.0 210.0 
	REQUEST_COLLISION 1405.0 6000.0
	
	CREATE_CAR shamal 1405.0 6000.0 210.0 shamal_C9
	SET_CAR_HEADING shamal_C9 210.0
	SET_VEHICLE_AREA_VISIBLE shamal_C9 0
 	
 	WARP_CHAR_INTO_CAR scplayer shamal_C9
	SET_CAR_FORWARD_SPEED shamal_C9 45.0
	SET_PLANE_UNDERCARRIAGE_UP shamal_C9 TRUE

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	DISPLAY_ZONE_NAMES TRUE
	DO_FADE 100 FADE_IN

	ADD_BLIP_FOR_COORD 1476.2190 1463.6227 9.8203 goto_blip_C9

	player_in_shamal_C9 = 1
	undercarriage_C9 = 1

	SET_MAX_WANTED_LEVEL 6

	PRINT_NOW CAS9_41 8000 1
LVAR_INT player_in_shamal_C9 undercarriage_C9 

mission_casino9_MAIN_player_in_shamal:

	WAIT 0

 	IF NOT IS_CAR_DEAD shamal_C9
	
		IF IS_CHAR_IN_CAR scplayer shamal_C9
			IF player_in_shamal_C9 = 0
				CLEAR_PRINTS
				PRINT_NOW CAS9_41 8000 1
				
				REMOVE_BLIP goto_blip_C9
				ADD_BLIP_FOR_COORD 1476.2190 1463.6227 9.8203 goto_blip_C9
				
				player_in_shamal_C9 = 1
			ENDIF
		ELSE
			IF player_in_shamal_C9 = 1
				CLEAR_PRINTS
				PRINT_NOW IN_VEH 8000 1
				
				REMOVE_BLIP goto_blip_C9
				ADD_BLIP_FOR_CAR shamal_C9 goto_blip_C9
				SET_BLIP_AS_FRIENDLY goto_blip_C9 TRUE
				
				player_in_shamal_C9 = 0
			ENDIF
		ENDIF

		IF IS_CAR_STOPPED_IN_AREA_3D shamal_C9 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE 
		AND IS_CHAR_IN_AREA_3D scplayer 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE	
			GOTO mission_casino9_PASSED
		ENDIF

	ELSE
		fail_text_flag_C9 = TRUE
		$fail_text_C9 = CAS9_F1
		GOTO mission_casino9_FAILED
	ENDIF	
	 
GOTO mission_casino9_MAIN_player_in_shamal





// **************************************** Mission casino9 failed ***********************
LVAR_INT player_vehicle_C9
mission_casino9_FAILED:

	DETACH_CHAR_FROM_CAR scplayer
	FREEZE_CHAR_POSITION scplayer FALSE

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	IF fail_text_flag_C9 = TRUE
		PRINT_NOW $fail_text_C9 6000 1
	ENDIF

	LVAR_INT skip_setup_C9
	skip_setup_C9 = 0
	IF IS_CHAR_IN_ANY_CAR scplayer
		STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer player_vehicle_C9
		IF IS_CAR_IN_WATER player_vehicle_C9
			skip_setup_C9 = 1
		ENDIF
	ELSE
		IF NOT IS_PLAYER_USING_JETPACK player1

			IF IS_CHAR_IN_WATER scplayer
				skip_setup_C9 = 1
			ELSE
				IF IS_CHAR_IN_AIR scplayer
				AND player_fall_state <= 0
					skip_setup_C9 = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
		
	IF skip_setup_C9 = 1
		SET_UP_SKIP_AFTER_MISSION 2097.0093 1728.2845 9.8203 169.1748
	ENDIF

RETURN

   

// **************************************** mission casino9 passed ************************

mission_casino9_passed:
	
	flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( CASINO9 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 15000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 15000//amount of cash
	AWARD_PLAYER_MISSION_RESPECT 30//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

	// craig stuff
	REMOVE_BLIP casino_contact_blip
	
RETURN
		


// ********************************** mission cleanup ***********************************

mission_casino9_CLEANUP:

	IF in_air_stuff_C9 = 1
		DETACH_CHAR_FROM_CAR scplayer
		RESTORE_CAMERA_JUMPCUT	
	ENDIF

	DISPLAY_ZONE_NAMES TRUE
	
	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	ENDIF
		
	START_NEW_SCRIPT terminate_audio_controller
	
	REMOVE_ANIMATION MISC

	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4

	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPLT

	MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
	MARK_MODEL_AS_NO_LONGER_NEEDED DODO
	MARK_MODEL_AS_NO_LONGER_NEEDED SHAMAL

	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

	MARK_MODEL_AS_NO_LONGER_NEEDED SMLPLANE_DOOR
	MARK_MODEL_AS_NO_LONGER_NEEDED JET_DOOR
	MARK_MODEL_AS_NO_LONGER_NEEDED JET_BAGGAGE_DOOR
	MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX2

	MARK_MODEL_AS_NO_LONGER_NEEDED jet_interior

	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[2]
	MARK_CHAR_AS_NO_LONGER_NEEDED hitman_C9[3]

	MARK_CHAR_AS_NO_LONGER_NEEDED pilot_C9

	MARK_CAR_AS_NO_LONGER_NEEDED dodo_C9
	MARK_CAR_AS_NO_LONGER_NEEDED shamal_C9

	//MARK_OBJECT_AS_NO_LONGER_NEEDED dodo_door_C9
	//MARK_OBJECT_AS_NO_LONGER_NEEDED shamal_door_C9

	MARK_OBJECT_AS_NO_LONGER_NEEDED baggage_doors_C9[0]
	MARK_OBJECT_AS_NO_LONGER_NEEDED baggage_doors_C9[1]
	MARK_OBJECT_AS_NO_LONGER_NEEDED baggage_doors_C9[2]

	MARK_OBJECT_AS_NO_LONGER_NEEDED lean_box_C9

	SET_MAX_WANTED_LEVEL 6

	SWITCH_AMBIENT_PLANES TRUE

	REMOVE_BLIP dodo_blip_C9 
	REMOVE_BLIP goto_blip_C9

	GET_GAME_TIMER timer_mobile_start

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN

 
}
