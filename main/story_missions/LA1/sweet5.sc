MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* SWEET 5 ********************************************
// ************************************** RESCUE ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME hoods5

// Mission start stuff												   

GOSUB mission_start_hood5

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_hood5_failed
ENDIF

GOSUB mission_cleanup_hood5

MISSION_END

{
  
// **************************************** Mission Start **********************************

LVAR_INT sw5_task_status sw5_time sw5_i sw5_red[6]  sw5_sweetsgf 
LVAR_INT sw5_random	 sw5_red_Group sw5_red_group_dec
LVAR_INT sw5_seville_dec
LVAR_INT sw5_house_blip sw5_emmet_blip sw5_gun sw5_red_blip[6] sw5_dead_reds sw5_pause_time
LVAR_INT sw5_playerscar sw5_temp sw5_getnewcar sw5_ballascar[2] sw5_reddriver[2] sw5_chase_car_created
LVAR_INT sw5_chase_timer sw5_seq sw5_sweet_lost	sw5_sweetsgf_lost
LVAR_INT sw5_sweet_blip sw5_sweetsgf_blip sw5_finish_blip sw5_special_red_action
LVAR_INT sw5_sweet


LVAR_INT sweet_change_state_time sw5_sweet_ducking sw5_sweets_target  sw5_reds_shooting_player
VAR_INT sw5_target[6]
LVAR_INT next_red_time_check sw5_redi 
LVAR_FLOAT sw5_redx sw5_redy sw5_redz sw5_x sw5_y sw5_z sw5_newx sw5_newy sw5_newz sw5_h 
LVAR_FLOAT sw5_closest_dist_a sw5_closest_dist_b 
LVAR_INT sw5_nearest_red_a sw5_nearest_red_b
LVAR_INT sw5_player_spotted

LVAR_INT sw5_scene_made sw5_spotted_for_first_time sw5_empty_dec sw5_sweet_dec  sw5_progress sw5_playerscar_model
LVAR_INT sw5_chasing_cars_leave sw5_playerscar_type sw5_generatecar

LVAR_FLOAT sw5_red_x[6] sw5_red_y[6] sw5_red_z[6]

LVAR_INT sw5_sweet_shooting_at sw5_Sweet_alive sw5_mission_fail

LVAR_INT sw5_flag  sw5_can_skip_cut
LVAR_INT sw5_car_model sw5_enter_cut sw5_button_pressed ped_attack_event[6]	sw5_redpas[2] sw5_red_model[6]
LVAR_INT sw5_help_message_displayed
LVAR_INT sw5_skipping_cut

LVAR_INT sw5_cut_flag ab_car ab_health ab_int youre_shit can_play_audio_now_time insult[7] noninsult[3] next_insult ab_count



IF sw5_flag = 99
	CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ONCE 20 2444.8950 -1981.5243 13.933 sw5_gun
	ADD_SPRITE_BLIP_FOR_COORD 2444.8950 -1981.5243 13.933 RADAR_SPRITE_GUN sw5_emmet_blip
	ADD_SPRITE_BLIP_FOR_COORD 2444.8950 -1981.5243 13.933 RADAR_SPRITE_GUN sw5_house_blip
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_sweet
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_sweetsgf
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_red[0]
	ADD_BLIP_FOR_CHAR sw5_sweet sw5_sweet_blip
	ADD_BLIP_FOR_CHAR sw5_sweet sw5_sweetsgf_blip
	ADD_BLIP_FOR_CHAR sw5_sweet sw5_red_blip1
	ADD_BLIP_FOR_CHAR sw5_sweet sw5_red_blip2
	ADD_BLIP_FOR_PICKUP sw5_gun sw5_emmet_marker
ENDIF




mission_start_hood5:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS Player1 TRUE	

	insult[0] = 14
	insult[1] = 15
	insult[2] = 16
	insult[3] = 17
	insult[4] = 18

	noninsult[0] = 21


//	DO_FADE 1000 FADE_IN
//	REQUEST_MODEL SENTINEL
//	WHILE NOT HAS_MODEL_LOADED SENTINEL
//		WAIT 0
//	ENDWHILE
//	CREATE_CAR SENTINEL 2516.0 -1675.0 14.0 sw5_playerscar
//	WARP_CHAR_INTO_CAR scplayer sw5_playerscar

//VIEW_INTEGER_VARIABLE sw5_cut_flag sw5_cut_flag
sw5_cut_flag = 0

	SET_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

	sw5_mission_loop:

		WAIT 0

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
			GOTO mission_hood5_passed
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
			SET_CHAR_COORDINATES scplayer 2769.492 -1967.329 12.3834
			sw5_i = 0
			WHILE sw5_i < 6
				IF NOT IS_CHAR_DEAD sw5_red[sw5_i]
					TASK_DIE sw5_red[sw5_i]
				ENDIF
				sw5_i++
			ENDWHILE
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
			sw5_flag = 7
		ENDIF

		SWITCH sw5_flag
			CASE 0
				GOSUB sw5_initialise_stuff
				sw5_flag = 1
			BREAK

			CASE 1
				// Mocap cut scene followed by Sweet on phone. 
				GOSUB sw5_cutscene_1
			BREAK

			CASE 2
				//Kill the Seville gang attacking Sweet 
				GOSUB sw5_scene1
			BREAK

			CASE 3
				//Phone call - get a car
				GOSUB sw5_cutscene_2
			BREAK

			CASE 4
				//Get the car
				GOSUB sw5_scene2
			BREAK

			CASE 5
				//Cut of Sweet coming in to player's car - Seville in cars turn up.
				GOSUB sw5_cutscene_3
			BREAK

			CASE 6
				//Drive back to hub
				GOSUB sw5_Scene3
			BREAK

			CASE 7
				//Sweet says "thanks" and then goes inside to fuck his girlfriend.
				GOSUB sw5_cutscene_4
			BREAK

			CASE 8
				GOTO mission_hood5_passed
			BREAK


		ENDSWITCH

		GOSUB sw5_fail_checks
		GOSUB sw5_mission_checks
		GOSUB sweet5_audio

		IF sw5_mission_fail = 1
			GOTO mission_hood5_failed
		ENDIF

	GOTO sw5_mission_loop

RETURN


sw5_fail_checks:
	IF sw5_sweet_alive = 1
		IF IS_CHAR_DEAD sw5_sweet
			sw5_mission_fail = 1	
			PRINT SW5_L 4000 1
		ENDIF
		IF IS_CHAR_DEAD sw5_sweetsgf
			sw5_mission_fail = 1
			PRINT SW5_M 4000 1
		ENDIF
	ENDIF
RETURN

sw5_mission_checks:





	IF HAS_PICKUP_BEEN_COLLECTED emmets_gun
		REMOVE_BLIP sw5_emmet_blip
		REMOVE_BLIP sw5_emmet_marker
	ENDIF

RETURN

 

sw5_initialise_stuff:

CLEAR_AREA sweet_carX sweet_carY sweet_carZ 5.0 TRUE

SWITCH_CAR_GENERATOR gen_car7 0



	REQUEST_MODEL CELLPHONE
	REQUEST_MODEL BFYRI
	REQUEST_MODEL TEC9
	REQUEST_MODEL SENTINEL
	REQUEST_MODEL COLT45
	REQUEST_MODEL GREENWOO
	REQUEST_MODEL FAM1
	REQUEST_MODEL FAM2
	LOAD_SPECIAL_CHARACTER 1 SWEET

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED CELLPHONE
		OR NOT HAS_MODEL_LOADED BFYRI
		OR NOT HAS_MODEL_LOADED TEC9
		OR NOT HAS_MODEL_LOADED COLT45
		OR NOT HAS_MODEL_LOADED SENTINEL
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_MODEL_LOADED FAM1
		OR NOT HAS_MODEL_LOADED FAM2
		OR NOT HAS_MODEL_LOADED GREENWOO

		WAIT 0
	ENDWHILE

		REQUEST_ANIMATION SWEET
		REQUEST_ANIMATION GANGS
//		REQUEST_ANIMATION SWAT
//		OR NOT HAS_ANIMATION_LOADED SWAT
		WHILE NOT HAS_ANIMATION_LOADED SWEET
		OR NOT HAS_ANIMATION_LOADED GANGS

			WAIT 0
		ENDWHILE

	REQUEST_CAR_RECORDING 153
	REQUEST_CAR_RECORDING 154
	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 153
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 154
		WAIT 0
	ENDWHILE



	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sw5_empty_dec
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH sw5_sweet_dec
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH sw5_seville_dec
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_MISSION1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION3

	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE sw5_seville_dec EVENT_POTENTIAL_GET_RUN_OVER
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE sw5_seville_dec EVENT_DAMAGE
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE sw5_seville_dec EVENT_GUN_AIMED_AT

//	SET_SCRIPT_LIMIT_TO_GANG_SIZE 1

	sw5_target[0] = 0
	sw5_target[1] = 0
	sw5_target[2] = 0
	sw5_target[3] = 0
	sw5_target[4] = 0
	sw5_target[5] = 0

	nearest_range_1 = 99999.0
	nearest_range_2	= 99999.0
	
RETURN



// CJ enters Sweet's house but no one is inside.
// Leaves the house and gets a phone call. It's Sweet and he's pinned down.
// Asks CJ to come and give him a hand taking out the Seville gang.

sw5_cutscene_1:

LVAR_INT dont_skip
	IF sw5_cut_flag > 1
		IF dont_skip = 0
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
				sw5_cut_flag = 7
				sw5_skipping_cut = 1
				audio_flag = 5
				sw5_time = 0		
			ENDIF
		ENDIF
	ENDIF
		

	IF sw5_cut_flag = 0

		LOAD_MISSION_TEXT SWEET5

		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0

		SET_AREA_VISIBLE 1

		LOAD_CUTSCENE SWEET5A
		 
		WHILE NOT HAS_CUTSCENE_LOADED
		            WAIT 0
		ENDWHILE
		 
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
		            WAIT 0
		ENDWHILE
		 
		

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
		     WAIT 0
		ENDWHILE

		CLEAR_CUTSCENE

		SET_AREA_VISIBLE 0

		//MAKE_PLAYER_GANG_DISAPPEAR TRUE



	   


		ADD_BLIP_FOR_COORD 2775.7930 -1942.9087 15.6576 sw5_house_blip
		SET_COORD_BLIP_APPEARANCE sw5_house_blip COORD_BLIP_APPEARANCE_ENEMY

	//	CHANGE_BLIP_COLOUR sw5_house_blip RED


		ADD_SPRITE_BLIP_FOR_COORD 2444.8950 -1981.5243 13.933 RADAR_SPRITE_EMMETGUN  sw5_emmet_blip

		REMOVE_PICKUP emmets_gun
		CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 16 2447.7732 -1975.6631 13.0 emmets_gun

		LVAR_INT sw5_emmet_marker
		ADD_BLIP_FOR_PICKUP emmets_gun sw5_emmet_marker
		CHANGE_BLIP_DISPLAY sw5_emmet_marker MARKER_ONLY

		REQUEST_COLLISION 2770.1157 -1915.8955


		SET_CHAR_COORDINATES scplayer 2770.1157 -1915.8955 11.6510
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		LOAD_SCENE 2770.1157 -1915.8955 11.6510



		SET_FIXED_CAMERA_POSITION 2770.8967 -1915.9508 12.6746 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2770.3369 -1916.3344 13.4091 JUMP_CUT

		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL player1 OFF

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE 

		DO_FADE 1500 FADE_IN
		play_audio = 29

		sw5_cut_flag = 1

		sw5_time = TIMERA + 2000
	ENDIF

	IF sw5_cut_flag = 1
		IF TIMERA > sw5_time

			LVAR_INT sw9_seq

			TASK_USE_MOBILE_PHONE scplayer TRUE
			sw5_time = TIMERA + 2200
			audio_flag = 3
			sw5_cut_flag = 20
		ENDIF
	ENDIF

	IF sw5_cut_flag = 20
		IF TIMERA > sw5_time
			IF play_audio = 0

				CREATE_CHAR PEDTYPE_MISSION3 SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_sweet
				CREATE_CAR GREENWOO 2745.5449 -1942.7405 12.5394 sw5_generatecar
				SET_CAR_HEADING sw5_generatecar 90.0
				SET_CHAR_HEADING sw5_sweet 0.0

				CREATE_CHAR PEDTYPE_MISSION3 BFYRI 2751.2817 -1948.8669 16.3125 sw5_sweetsgf
				SET_CHAR_HEADING sw5_sweetsgf 0.0
				SET_CHAR_DECISION_MAKER sw5_sweetsgf sw5_empty_dec

				audio_actor[2] = sw5_sweet
				audio_actor[3] = sw5_sweetsgf

				play_audio = 1
				play_audio_for = 7
		 
				sw5_cut_flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 2
		IF TIMERA > sw5_time 

			IF NOT IS_CHAR_DEAD sw5_sweet
				OPEN_SEQUENCE_TASK sw5_seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_USE_MOBILE_PHONE -1 TRUE
				CLOSE_SEQUENCE_TASK sw5_seq
				PERFORM_SEQUENCE_TASK sw5_sweet sw5_seq
				CLEAR_SEQUENCE_TASK sw5_seq
			ENDIF
				


			IF NOT IS_CHAR_DEAD sw5_sweetsgf
				OPEN_SEQUENCE_TASK sw5_seq
					TASK_PAUSE -1 500
				    TASK_PLAY_ANIM -1 DUCK_cower PED 8.0 FALSE FALSE FALSE FALSE 16000		
				CLOSE_SEQUENCE_TASK sw5_seq
				PERFORM_SEQUENCE_TASK sw5_sweetsgf sw5_seq
				CLEAR_SEQUENCE_TASK sw5_seq
			ENDIF



		//	TASK_COWER sw5_sweetsgf

			CREATE_CHAR PEDTYPE_MISSION2 FAM2 2751.5149 -1973.1781 16.3051 sw5_red[0]
			SET_CHAR_ACCURACY sw5_red[0] 40
			SET_CHAR_DECISION_MAKER sw5_red[0] sw5_empty_dec
			GIVE_WEAPON_TO_CHAR sw5_red[0] WEAPONTYPE_PISTOL 99999
			TASK_TOGGLE_DUCK sw5_red[0] TRUE

			OPEN_SEQUENCE_TASK sw5_seq
				TASK_PAUSE -1 2500
				TASK_GO_STRAIGHT_TO_COORD -1 2751.7913 -1964.0797 16.3125 PEDMOVE_WALK -2	
				TASK_PAUSE -1 3500
				TASK_GO_STRAIGHT_TO_COORD -1 2751.7913 -1954.0797 16.3125 PEDMOVE_WALK -2
			CLOSE_SEQUENCE_TASK sw5_seq
			PERFORM_SEQUENCE_TASK sw5_red[0] sw5_seq
			CLEAR_SEQUENCE_TASK sw5_seq




			CREATE_CHAR PEDTYPE_MISSION2 FAM1 2753.1973 -1973.9462 16.3051 sw5_red[1]
			SET_CHAR_ACCURACY sw5_red[1] 40
			GIVE_WEAPON_TO_CHAR sw5_red[1] WEAPONTYPE_PISTOL 99999
			TASK_TOGGLE_DUCK sw5_red[1] TRUE
			TASK_SHOOT_AT_COORD sw5_red[1] 2753.1499 -1949.6418 16.5866 -2

 
			sw5_time = TIMERA + 2000
			sw5_cut_flag = 3
		ENDIF
	ENDIF

	IF sw5_cut_flag = 3
		IF TIMERA > sw5_time
			IF play_audio = 3
				// look at sweet
				SET_FIXED_CAMERA_POSITION 2753.7837 -1947.4662 16.4890 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2753.2551 -1948.2924 16.6835 JUMP_CUT	
				sw5_time = TIMERA + 4000
				sw5_cut_flag = 4
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 4
		IF TIMERA > sw5_time
			sw5_time = TIMERA + 4000
			sw5_cut_flag = 5
		ENDIF
	ENDIF

	IF sw5_cut_flag = 5
//		IF TIMERA > sw5_time
			IF play_audio = 7
				//look at CJ
			
				SET_FIXED_CAMERA_POSITION 2770.8967 -1915.9508 12.6746 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2770.3369 -1916.3344 13.4091 JUMP_CUT

				IF NOT IS_CHAR_DEAD sw5_sweet
					SET_CHAR_ACCURACY sw5_sweet 100
				ENDIF

				sw5_time = TIMERA + 4500
				sw5_cut_flag = 21
			ENDIF
//		ENDIF
	ENDIF

	IF sw5_cut_flag = 21
		IF TIMERA > sw5_time
			TASK_USE_MOBILE_PHONE scplayer FALSE
			sw5_cut_flag = 6
			sw5_time = TIMERA + 1000
		ENDIF
	ENDIF



	IF sw5_cut_flag = 6
		IF TIMERA > sw5_time
			SET_FIXED_CAMERA_POSITION 2756.2520 -1949.0199 17.5130 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2755.4023 -1949.5182 17.3404 JUMP_CUT

			IF NOT IS_CHAR_DEAD sw5_sweet
				CLEAR_CHAR_TASKS_IMMEDIATELY sw5_sweet
				TASK_TOGGLE_DUCK sw5_sweet TRUE
				GIVE_WEAPON_TO_CHAR sw5_sweet WEAPONTYPE_PISTOL 500
				SET_CURRENT_CHAR_WEAPON sw5_sweet WEAPONTYPE_PISTOL
				SET_CHAR_HEADING sw5_sweet 180.0

				OPEN_SEQUENCE_TASK sw5_seq
					TASK_PLAY_ANIM -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
					IF NOT IS_CHAR_DEAD sw5_red[0]
						TASK_SHOOT_AT_CHAR -1 sw5_red[0] -2
					ENDIF
				CLOSE_SEQUENCE_TASK sw5_seq
				PERFORM_SEQUENCE_TASK sw5_sweet sw5_seq
				CLEAR_SEQUENCE_TASK sw5_seq
			ENDIF
			sw5_time = TIMERA + 2500
			sw5_cut_flag = 7
		ENDIF
	ENDIF

	IF sw5_cut_flag = 7
		IF TIMERA > sw5_time
			CLEAR_PRINTS
			dont_skip = 1

			DO_FADE 1000 FADE_OUT 
			sw5_cut_flag = 8
		ENDIF
	ENDIF

	IF sw5_cut_flag = 8
		IF NOT GET_FADING_STATUS

			REQUEST_COLLISION 2518.7893 -1677.2203
			SET_CHAR_COORDINATES scplayer 2518.4475 -1677.2240 13.4109  
			SET_CHAR_HEADING scplayer 90.6910
			LOAD_SCENE 2510.2844 -1674.0657 14.5338
			sw5_time = TIMERA + 300
			sw5_cut_flag = 9




			// Initialise Sweets health bar
			LVAR_INT sw5_check_sweet_health sw5_sweet_health_drop_time
			VAR_INT sw5_sweet_health

			sw5_sweet_health = 100

			GENERATE_RANDOM_INT_IN_RANGE 2500 6000 sw5_sweet_health_drop_time
			sw5_sweet_health_drop_time += TIMERA

			DISPLAY_ONSCREEN_COUNTER_WITH_STRING sw5_sweet_health COUNTER_DISPLAY_BAR SW5_A3

			GENERATE_RANDOM_INT_IN_RANGE 2500 6000 sw5_sweet_health_drop_time
			sw5_sweet_health_drop_time += TIMERA


		ENDIF
	ENDIF

	IF sw5_cut_flag = 9
		IF TIMERA > sw5_time
			DO_FADE 1500 FADE_IN
			DELETE_CHAR sw5_sweet			   
			DELETE_CHAR sw5_red[0]
			DELETE_CHAR sw5_red[1]
			DELETE_CHAR sw5_sweetsgf

			SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0


			GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sw5_task_status

//			PRINT SW5_C 6000 1
			PRINT SW5_A2 6000 1
			PRINT SW5_17 6000 1

			SET_PLAYER_CONTROL player1 ON

			MAKE_PLAYER_GANG_REAPPEAR

			SWITCH_WIDESCREEN OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			
			SET_WANTED_MULTIPLIER 0.2
			sw5_skipping_cut = 1
			sw5_flag = 2

		ENDIF
	ENDIF

RETURN














// *****************************************************************************************
// *** CJ drives over to Sweet's place and takes out the attacking Seville gang members. ***
// *****************************************************************************************


sw5_scene1:



	IF TIMERA > sw5_sweet_health_drop_time
		LVAR_INT sw5_health_drop
		GENERATE_RANDOM_INT_IN_RANGE 2500 6000 sw5_sweet_health_drop_time
		sw5_sweet_health_drop_time += TIMERA
		GENERATE_RANDOM_INT_IN_RANGE 2 4 sw5_health_drop
		sw5_sweet_health -= sw5_health_drop
		IF sw5_sweet_health <= 0
		   sw5_sweet_health = 0
		   PRINT SW5_L 4000 1
//		   sw5_mission_fail = 1
		   sw5_sweet_alive = 1 		   	
		ENDIF
		
	ENDIF




	IF sw5_scene_made = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2775.7930 -1942.9087 15.6576 100.0 100.0 50.0 FALSE
			SET_CREATE_RANDOM_GANG_MEMBERS OFF
			sw5_scene_made = 1
			PRINT SW5_J 4000 1

			sw5_red_x[0] = 2761.5156   
			sw5_red_y[0] = -1972.0280 
			sw5_red_z[0] = 12.5459
			sw5_red_model[0] = FAM1

			sw5_red_x[1] = 2761.5989 
			sw5_red_y[1] = -1955.6726
			sw5_red_z[1] = 12.5469 
			sw5_red_model[1] = FAM2


			sw5_red_x[2] = 2768.4514 							 
			sw5_red_y[2] = -1972.6053
			sw5_red_z[2] = 12.3837 		
			sw5_red_model[2] = FAM1			 

//2758.3027 
//-1984.9614
//12.5547


			sw5_red_x[3] = 2761.551 
			sw5_red_y[3] = -1980.0991 
			sw5_red_z[3] = 12.5478 
			sw5_red_model[3] = FAM2

			sw5_red_x[4] = 2752.1594 
			sw5_red_y[4] = -1979.5736
			sw5_red_z[4] = 14.0469			 			    
			sw5_red_model[4] = FAM2

			sw5_red_x[5] = 2768.4514 
			sw5_red_y[5] = -1972.6053
			sw5_red_z[5] = 12.3837 			    
			sw5_red_model[5] = FAM1

			LVAR_INT sw5_a_car
			CREATE_CAR GREENWOO 2766.9231 -1974.3475 12.3783 sw5_a_car
			SET_CAR_HEADING sw5_a_car 4.0


			sw5_i = 0
			WHILE sw5_i < 5
				CREATE_CHAR PEDTYPE_MISSION1 sw5_red_model[sw5_i] sw5_red_x[sw5_i] sw5_red_y[sw5_i] sw5_red_z[sw5_i] sw5_red[sw5_i]
				SET_CHAR_ACCURACY sw5_red[sw5_i] 65
				IF sw5_i = 0
				OR sw5_i = 3
					GIVE_WEAPON_TO_CHAR sw5_red[sw5_i] WEAPONTYPE_TEC9 99999
				ELSE
					GIVE_WEAPON_TO_CHAR sw5_red[sw5_i] WEAPONTYPE_PISTOL 99999
				ENDIF
				TASK_TOGGLE_DUCK sw5_red[sw5_i] TRUE
				TASK_SHOOT_AT_COORD sw5_red[sw5_i] 2750.9570 -1968.0157 17.9291 -2
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE sw5_red[sw5_i] TRUE
				sw5_i ++
			ENDWHILE

//			EXTEND_ROUTE 2752.1941 -1979.4746 14.0469
//			EXTEND_ROUTE 2752.0281 -1974.9559 16.3125
//			EXTEND_ROUTE 2753.3323 -1964.8541 16.3125
		  	   
			ADD_BLIP_FOR_CHAR sw5_red[0] sw5_red_blip[0]
			ADD_BLIP_FOR_CHAR sw5_red[1] sw5_red_blip[1]
			ADD_BLIP_FOR_CHAR sw5_red[2] sw5_red_blip[2]
			ADD_BLIP_FOR_CHAR sw5_red[3] sw5_red_blip[3]
			ADD_BLIP_FOR_CHAR sw5_red[4] sw5_red_blip[4]
//			ADD_BLIP_FOR_CHAR sw5_red[5] sw5_red_blip[5]
			REMOVE_BLIP sw5_house_blip

			SET_CHAR_DECISION_MAKER sw5_red[0] sw5_seville_dec
			SET_CHAR_DECISION_MAKER sw5_red[1] sw5_seville_dec
			SET_CHAR_DECISION_MAKER sw5_red[2] sw5_seville_dec
			SET_CHAR_DECISION_MAKER sw5_red[3] sw5_seville_dec
			SET_CHAR_DECISION_MAKER sw5_red[4] sw5_seville_dec
///			SET_CHAR_DECISION_MAKER sw5_red[5] sw5_seville_dec

			SET_CHAR_IS_TARGET_PRIORITY sw5_red[0] TRUE
			SET_CHAR_IS_TARGET_PRIORITY sw5_red[1] TRUE
			SET_CHAR_IS_TARGET_PRIORITY sw5_red[2] TRUE
			SET_CHAR_IS_TARGET_PRIORITY sw5_red[3] TRUE
			SET_CHAR_IS_TARGET_PRIORITY sw5_red[4] TRUE
//			SET_CHAR_IS_TARGET_PRIORITY sw5_red[5] TRUE


			SWITCH_ROADS_OFF 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
			SWITCH_PED_ROADS_OFF 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
		ENDIF
	ENDIF



	IF sw5_scene_made = 1

		GOSUB sw5_ped_actions

		IF sw5_help_message_displayed = 0
			IF IS_CHAR_IN_AREA_3D scplayer 2782.6931 -1921.1246 11.7984 2746.0984 -1993.9053 16.9599 FALSE
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					sw5_help_message_displayed = 1
					
					PRINT_HELP SW5_19
				ENDIF
			ENDIF
		ENDIF

	 	sw5_i = 0
		sw5_dead_reds = 0
		WHILE sw5_i < 5
			IF IS_CHAR_DEAD sw5_red[sw5_i]
				REMOVE_BLIP sw5_red_blip[sw5_i]
				sw5_dead_reds ++
			ELSE
				SET_CHAR_ACCURACY sw5_red[sw5_i] new_accuracy
				SET_CHAR_SHOOT_RATE sw5_red[sw5_i] new_accuracy
			ENDIF
			sw5_i ++
		ENDWHILE

		LVAR_INT new_accuracy
		new_accuracy = sw5_dead_reds * 6
		new_accuracy += 60
	
	  
		IF sw5_dead_reds = 5
			// Skip to phone call from Sweet.
			sw5_cut_flag = 0
			sw5_enter_cut = 0
			sw5_button_pressed = 0
			sw5_can_skip_cut = 0
			sw5_flag = 3
			SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
		ENDIF
	ENDIF

RETURN







// *****************************************************************************************
// CJ gets a call from Sweet telling him to get a car outside the flat for him and his bird.
// *****************************************************************************************

sw5_cutscene_2:

//	IF sw5_can_skip_cut = 1
//		IF sw5_enter_cut = 0
//			sw5_enter_cut = 1
//			IF IS_BUTTON_PRESSED PAD1 CROSS
//				sw5_button_pressed = 1
//			ENDIF
//		ENDIF
//
//		IF IS_BUTTON_PRESSED PAD1 CROSS
//			IF sw5_button_pressed = 0
//				sw5_cut_flag = 4
//				sw5_time = 0
//			ENDIF 			
//		ELSE
//			IF sw5_button_pressed = 1
//				sw5_button_pressed = 0
//			ENDIF
//		ENDIF
//	ENDIF

	IF sw5_cut_flag = 0
		//WAit 1.5 secs for phone to ring
		sw5_time = TIMERA + 1500
		sw5_cut_flag = 1
	ENDIF

	IF sw5_cut_flag = 1
		IF TIMERA > sw5_time
			play_audio = 29
			CLEAR_ONSCREEN_COUNTER sw5_sweet_health
			sw5_time = TIMERA + 2500
			sw5_cut_flag = 2
		ENDIF
	ENDIF

	IF sw5_cut_flag = 2
		IF TIMERA > sw5_time
			play_audio = 29
			sw5_time = TIMERA + 2500
			sw5_cut_flag = 3
		ENDIF
	ENDIF

	IF sw5_cut_flag = 3
		IF TIMERA > sw5_time
			IF NOT IS_CHAR_IN_AIR scplayer
			AND NOT IS_CHAR_GETTING_IN_TO_A_CAR scplayer
				LVAR_INT phone_call_in_Car
				phone_call_in_Car = 1
				TASK_USE_MOBILE_PHONE scplayer TRUE
				sw5_time = TIMERA + 2500
				sw5_cut_flag = 4
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 4
		IF TIMERA > sw5_time
			sw5_cut_flag = 5
			play_audio = 8
			play_audio_for = 2
		ENDIF
	ENDIF

	IF sw5_cut_flag = 5
		IF play_audio = 0

			TASK_USE_MOBILE_PHONE scplayer FALSE

//			audio_flag = 5
//			sw5_can_skip_cut = 0

			
//			SET_CHAR_COORDINATES_NO_OFFSET scplayer x y z
//			SET_CHAR_HEADING scplayer sw5_heading
			ADD_BLIP_FOR_COORD 2764.2725 -1979.4939 12.5394 sw5_house_blip
			SWITCH_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
			SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
//			SWITCH_WIDESCREEN OFF
//			SET_PLAYER_CONTROL player1 ON
//			SET_CAMERA_BEHIND_PLAYER
//			RESTORE_CAMERA_JUMPCUT
//			MAKE_PLAYER_GANG_REAPPEAR
//			SET_EVERYONE_IGNORE_PLAYER Player1 OFF

			PRINT SW5_14 5000 1
			sw5_flag = 4

		ENDIF
	ENDIF



//
//	IF sw5_cut_flag = 5
//		IF TIMERA > sw5_time
//			IF NOT IS_CHAR_IN_AIR scplayer
//			AND NOT IS_CHAR_GETTING_IN_TO_A_CAR scplayer
//				IF IS_CHAR_IN_ANY_CAR scplayer
//	  					play_audio = 8
//					play_audio_for = 2
//				ENDI
//
//
//			CLEAR_ONSCREEN_COUNTER sw5_sweet_health
//
//
////			SET_PLAYER_CONTROL player1 OFF
//			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
//			SET_PLAYER_CONTROL player1 OFF
//			
//			SWITCH_WIDESCREEN ON
//			SET_EVERYONE_IGNORE_PLAYER Player1 ON
//
//			LVAR_FLOAT sw5_heading
//
//			GET_CHAR_COORDINATES scplayer x y z
//			GET_CHAR_HEADING scplayer sw5_heading
//			SET_FIXED_CAMERA_POSITION 2752.3643 -1947.8760 17.4632 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT 2753.1821 -1947.4803 17.8808 JUMP_CUT
//			SET_CHAR_HEADING scplayer 154.6564
//			SET_CHAR_COORDINATES scplayer 2752.8809 -1947.0312 16.3203
//
//			sw5_time = TIMERA + 100
//			sw5_cut_flag = 1
//		ENDIF
//	ENDIF
//
//	IF sw5_cut_flag = 1
//		IF TIMERA > sw5_time
//			PRINT SW5_P 1500 1
//			sw5_time = TIMERA + 500
//			sw5_cut_flag = 2
//		ENDIF
//	ENDIF
//
//	IF sw5_cut_flag = 2
//		IF TIMERA > sw5_time
//			sw5_can_skip_cut = 1
//			TASK_USE_MOBILE_PHONE scplayer TRUE
//			sw5_time = TIMERA + 1500
//			sw5_cut_flag = 3
//		ENDIF
//	ENDIF
//
//	IF sw5_cut_flag = 3
//		IF TIMERA > sw5_time
//			CLEAR_PRINTS
//			play_audio = 8
//			play_audio_for = 2
//			sw5_time = TIMERA + 5000
//			sw5_cut_flag = 4
//		ENDIF
//	ENDIF						
//
//	IF sw5_cut_flag = 4
//		IF TIMERA > sw5_time
//			TASK_USE_MOBILE_PHONE scplayer FALSE
//			audio_flag = 5
//			sw5_can_skip_cut = 0
//			sw5_cut_flag = 10
//			
//			SET_CHAR_COORDINATES_NO_OFFSET scplayer x y z
//			SET_CHAR_HEADING scplayer sw5_heading
//			ADD_BLIP_FOR_COORD 2764.2725 -1979.4939 12.5394 sw5_house_blip
//			SWITCH_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
//			SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
//			SWITCH_WIDESCREEN OFF
//			SET_PLAYER_CONTROL player1 ON
//			SET_CAMERA_BEHIND_PLAYER
//			RESTORE_CAMERA_JUMPCUT
//			MAKE_PLAYER_GANG_REAPPEAR
//			SET_EVERYONE_IGNORE_PLAYER Player1 OFF
//		ENDIF
//	ENDIF
//
//	IF sw5_cut_flag = 10	
//		IF audio_flag = 1
//			PRINT SW5_14 5000 1
//			sw5_flag = 4
//		ENDIF
//	ENDIF

RETURN



// *****************************************************************************************
// ******************** Go and get a car to pick up sweet and his bird.*********************
// *****************************************************************************************

sw5_scene2:

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2764.2725 -1979.4939 12.5394 4.0 4.0 4.0 TRUE			
		
		IF IS_CHAR_IN_ANY_CAR scplayer				
			STORE_CAR_CHAR_IS_IN scplayer sw5_playerscar
			IF NOT IS_CAR_DEAD sw5_playerscar
				GET_MAXIMUM_NUMBER_OF_PASSENGERS sw5_playerscar sw5_temp
				IF sw5_temp < 2
					IF sw5_getNewcar = 0 
						PRINT_NOW SW5_G 5000 1 //You need a car with at least two passenger seats.
						sw5_getNewCar = 1
					ENDIF
				ELSE
					GET_VEHICLE_CLASS sw5_playerscar sw5_playerscar_type
					GET_CAR_MODEL sw5_playerscar sw5_playerscar_model

					IF sw5_playerscar_type	= NORMAL_CAR
					OR sw5_playerscar_type	= POOR_FAMILY_CAR
					OR sw5_playerscar_type	= RICH_FAMILY_CAR
					OR sw5_playerscar_type	= EXECUTIVE_CAR
					OR sw5_playerscar_model = copcarla						
					OR sw5_playerscar_model = TAXI



						SET_PLAYER_CONTROL player1 OFF
						TASK_CAR_TEMP_ACTION scplayer sw5_playerscar TEMPACT_HANDBRAKESTRAIGHT 2000000

						DO_FADE 800 FADE_OUT

						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						sw5_flag = 5
						sw5_cut_flag = 0

						REMOVE_BLIP sw5_house_blip
					ELSE
						PRINT_NOW sw5_16 1000 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN



// *****************************************************************************************
// *********** Sweet and his bird enter player's car, but more Sevilles turn up in cars ****
// *****************************************************************************************

sw5_cutscene_3:

	IF sw5_enter_cut = 0
		sw5_enter_cut = 1
		IF IS_BUTTON_PRESSED PAD1 CROSS
			sw5_button_pressed = 1
		ENDIF
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 CROSS
		IF sw5_button_pressed = 0
			IF NOT IS_CHAR_DEAD sw5_sweet
			AND NOT IS_CHAR_DEAD sw5_sweetsgf
				IF NOT sw5_cut_flag = 0
				AND NOT sw5_cut_flag = 5
					sw5_cut_flag = 4
					sw5_time = 0
					sw5_button_pressed = 1
					audio_flag = 5
				ENDIF
			ENDIF
		ENDIF 			
	ELSE
		IF sw5_button_pressed = 1
			sw5_button_pressed = 0
		ENDIF
	ENDIF

	IF sw5_cut_flag = 5
		IF TIMERA > sw5_time
			DO_FADE 800 FADE_IN
//		DO_FADE
//		play_audio = 10
//		play_Audio_delay = TIMERA + 500
		sw5_time = TIMERA + 3000
		sw5_cut_flag = 1
		ENDIF
	ENDIF

	IF sw5_cut_flag = 0


			CLEAR_PRINTS
			//MAKE_PLAYER_GANG_DISAPPEAR TRUE


			CLEAR_AREA_OF_CARS 2746.5037 -2001.6106 10.7750 2789.5623 -1927.1542 21.6330
			SWITCH_ROADS_OFF 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
			SWITCH_PED_ROADS_OFF 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752

	//		SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE sw5_seville_dec EVENT_ACQUAINTANCE_PED_HATE
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE sw5_seville_dec EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

			CREATE_CHAR PEDTYPE_MISSION3 SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_sweet
			CREATE_CHAR PEDTYPE_MISSION3 BFYRI 2751.2817 -1948.8669 16.3125 sw5_sweetsgf

			audio_actor[2] = sw5_sweet
			audio_actor[3] = sw5_sweetsgf

			SET_CHAR_SUFFERS_CRITICAL_HITS sw5_sweet FALSE
			SET_CHAR_SUFFERS_CRITICAL_HITS sw5_sweetsgf FALSE
	 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw5_sweet FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw5_sweetsgf FALSE

			CREATE_CAR SENTINEL 2766.6650 -1901.9430 10.0050 sw5_ballascar[0]
			CREATE_CAR SENTINEL 2772.6384 -1903.1267 10.1479 sw5_ballascar[1]

			SET_CAR_HEADING sw5_ballascar[0] 180.0
			SET_CAR_HEADING sw5_ballascar[1] 180.0

			SET_CAR_CAN_GO_AGAINST_TRAFFIC sw5_ballascar[0] TRUE
			SET_CAR_CAN_GO_AGAINST_TRAFFIC sw5_ballascar[1] TRUE

			START_PLAYBACK_RECORDED_CAR sw5_ballascar[0] 153
			START_PLAYBACK_RECORDED_CAR sw5_ballascar[1] 154

			CREATE_CHAR_INSIDE_CAR sw5_ballascar[1] PEDTYPE_MISSION1 FAM1 sw5_reddriver[1]
			CREATE_CHAR_INSIDE_CAR sw5_ballascar[0] PEDTYPE_MISSION1 FAM1 sw5_reddriver[0]
	//		CREATE_CHAR_AS_PASSENGER sw5_ballascar[0] PEDTYPE_MISSION1 FAM2 0 sw5_redpas[0] 
	//		CREATE_CHAR_AS_PASSENGER sw5_ballascar[1] PEDTYPE_MISSION1 FAM2 0 sw5_redpas[1]
			
			SET_CHAR_DECISION_MAKER sw5_reddriver[0] sw5_seville_Dec 
			SET_CHAR_DECISION_MAKER sw5_reddriver[1] sw5_seville_Dec 
	//		SET_CHAR_DECISION_MAKER sw5_redpas[0] sw5_seville_Dec 
	//		SET_CHAR_DECISION_MAKER sw5_redpas[1] sw5_seville_Dec 

	//		SET_CHAR_SHOOT_RATE sw5_redpas[0] 20
	//		SET_CHAR_SHOOT_RATE sw5_redpas[1] 20

			GIVE_WEAPON_TO_CHAR sw5_reddriver[0] WEAPONTYPE_PISTOL 99999
			GIVE_WEAPON_TO_CHAR sw5_reddriver[1] WEAPONTYPE_PISTOL 99999
	//		GIVE_WEAPON_TO_CHAR sw5_redpas[0] WEAPONTYPE_PISTOL 99999
	//		GIVE_WEAPON_TO_CHAR sw5_redpas[1] WEAPONTYPE_PISTOL 99999

												
			SET_PLAYBACK_SPEED sw5_ballascar[0] 0.0
			SET_PLAYBACK_SPEED sw5_ballascar[1] 0.0

			IF NOT IS_CAR_DEAD sw5_playerscar
				SET_CAR_COORDINATES sw5_playerscar 2763.9600 -1978.8977 12.5394
				SET_CAR_HEADING sw5_playerscar 0.0 

				// make sure seats are empty
				LVAR_INT sw5_some_guya
				IF NOT IS_CAR_PASSENGER_SEAT_FREE sw5_playerscar 0
					GET_CHAR_IN_CAR_PASSENGER_SEAT sw5_playerscar 0 sw5_some_guya
					DELETE_CHAR sw5_some_guya
				ENDIF

				IF NOT IS_CAR_PASSENGER_SEAT_FREE sw5_playerscar 1
					GET_CHAR_IN_CAR_PASSENGER_SEAT sw5_playerscar 1 sw5_some_guya
					DELETE_CHAR sw5_some_guya
				ENDIF
			ENDIF

			SET_FIXED_CAMERA_POSITION 2756.2314 -1949.6669 18.0543 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2755.7507 -1950.5349 17.9303 JUMP_CUT  
			    
		
			FLUSH_ROUTE																						
			EXTEND_ROUTE 2752.4023 -1974.0765 16.3125


			SET_CHAR_COORDINATES sw5_sweetsgf 2752.3115 -1952.8958 16.3051
			SET_CHAR_HEADING sw5_sweetsgf 180.0
			TASK_FOLLOW_POINT_ROUTE sw5_sweetsgf PEDMOVE_RUN FOLLOW_ROUTE_ONCE

			SET_CHAR_COORDINATES sw5_sweet 2752.395 -1950.6991 16.3125 
			SET_CHAR_HEADING sw5_sweet 180.0
			OPEN_SEQUENCE_TASK sw5_seq
				TASK_PAUSE -1 1000
				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
			CLOSE_SEQUENCE_TASK sw5_seq
			PERFORM_SEQUENCE_TASK sw5_sweet sw5_seq
			CLEAR_SEQUENCE_TASK sw5_seq

			




			sw5_time = TIMERA + 500

			sw5_cut_flag = 5

	ENDIF

	IF sw5_cut_flag = 1
		IF TIMERA > sw5_time

			SET_FIXED_CAMERA_POSITION 2764.6011 -1986.6956 15.0210 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2764.3198 -1985.7511 14.8511 JUMP_CUT 

			IF NOT IS_CAR_DEAD sw5_playerscar
				IF NOT IS_CHAR_DEAD sw5_sweetsgf
					SET_CHAR_COORDINATES sw5_sweetsgf 2760.3577 -1979.5011 12.5394
					SET_CHAR_HEADING sw5_sweetsgf 330.0													 
					TASK_ENTER_CAR_AS_PASSENGER sw5_sweetsgf sw5_playerscar 5000 1						 
				ENDIF

				IF NOT IS_CHAR_DEAD sw5_sweet
					SET_CHAR_COORDINATES sw5_sweet 2767.2241 -1981.5243 12.3810
					SET_CHAR_HEADING sw5_sweet 0.0
					TASK_ENTER_CAR_AS_PASSENGER sw5_sweet sw5_playerscar 5000 0
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD sw5_ballascar[1]
				SET_PLAYBACK_SPEED sw5_ballascar[1] 1.0
				sw5_chase_car_created = 1
			ENDIF

			sw5_time = TIMERA + 500
			sw5_cut_flag = 2
		ENDIF
	ENDIF

	IF sw5_cut_flag = 2
		IF TIMERA > sw5_time

			
//			play_audio_for = 2

			IF NOT IS_CAR_DEAD sw5_ballascar[0]
				SET_PLAYBACK_SPEED sw5_ballascar[0] 1.0
				sw5_chase_car_created = 1
			ENDIF
			sw5_time = TIMERA + 2000
			sw5_cut_flag = 3
		ENDIF
	ENDIF

	IF sw5_cut_flag = 3
		IF TIMERA > sw5_time


			play_audio = 12
			sw5_time = TIMERA + 2000
			sw5_cut_flag = 4
		ENDIF
	ENDIF

	IF sw5_cut_flag = 4

		IF car_one_attack = 0
			IF NOT IS_CAR_DEAD sw5_ballascar[0]
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR sw5_ballascar[0]
					SET_CAR_MISSION sw5_ballascar[0] MISSION_BLOCKPLAYER_CLOSE
					SET_CAR_CRUISE_SPEED sw5_ballascar[0] 60.0
					SET_CAR_HEALTH sw5_ballascar[0] 2500
					SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER sw5_ballascar[0] 0.1
					car_one_attack = 1
				ENDIF
			ENDIF
		ENDIF

		IF car_two_attack = 0
			IF NOT IS_CAR_DEAD sw5_ballascar[1]
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR sw5_ballascar[1]
					SET_CAR_MISSION sw5_ballascar[1] MISSION_BLOCKPLAYER_CLOSE
					SET_CAR_CRUISE_SPEED sw5_ballascar[1] 60.0
					SET_CAR_HEALTH sw5_ballascar[1] 2500
					SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER sw5_ballascar[1] 0.1
					car_two_attack = 1
				ENDIF
			ENDIF
		ENDIF

		LVAR_INT car_one_attack car_two_attack			
			


		IF TIMERA > sw5_time
			IF play_audio = 0

				sw5_chase_car_created = 1

				SWITCH_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
				SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752


				IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CAR_CHAR_IS_USING scplayer sw5_playerscar
					SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER sw5_playerscar 5.0
				ENDIF

				IF sw5_button_pressed = 1
					IF NOT IS_CAR_DEAD sw5_ballascar[0]
						IF IS_PLAYBACK_GOING_ON_FOR_CAR sw5_ballascar[0]
							SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR sw5_ballascar[0]
							
						ENDIF
					ENDIF
					
					IF NOT IS_CAR_DEAD sw5_ballascar[1]
						IF IS_PLAYBACK_GOING_ON_FOR_CAR sw5_ballascar[1]
							SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR sw5_ballascar[1]
						ENDIF
					ENDIF
				ENDIF 



				IF NOT IS_CAR_DEAD sw5_ballascar[0]
				AND NOT IS_CAR_DEAD sw5_ballascar[1]
					SET_CAR_MISSION sw5_ballascar[0] MISSION_BLOCKPLAYER_CLOSE
					SET_CAR_MISSION sw5_ballascar[1] MISSION_RAMPLAYER_CLOSE
					SET_CAR_CRUISE_SPEED sw5_ballascar[0] 60.0
					SET_CAR_CRUISE_SPEED sw5_ballascar[1] 60.0
					SET_CAR_HEALTH sw5_ballascar[0] 2500
					SET_CAR_HEALTH sw5_ballascar[1] 2500
					SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER sw5_ballascar[0] 0.1
					SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER sw5_ballascar[1] 0.1
				ENDIF

				ADD_BLIP_FOR_COORD 2509.0618 -1672.5270 12.3860 sw5_finish_blip
				SET_BLIP_AS_FRIENDLY sw5_finish_blip TRUE
	  
				IF NOT IS_CHAR_DEAD sw5_sweet
				AND NOT IS_CAR_DEAD sw5_playerscar
				AND NOT IS_CHAR_DEAD sw5_sweetsgf

					SET_GROUP_MEMBER Players_group sw5_sweet
					SET_GROUP_MEMBER Players_group sw5_sweetsgf

					LISTEN_TO_PLAYER_GROUP_COMMANDS sw5_sweet FALSE
					LISTEN_TO_PLAYER_GROUP_COMMANDS sw5_sweetsgf FALSE

					GIVE_WEAPON_TO_CHAR sw5_sweet WEAPONTYPE_PISTOL 9999

					IF NOT IS_CHAR_IN_CAR sw5_sweet sw5_playerscar
						CLEAR_CHAR_TASKS_IMMEDIATELY sw5_sweet
						WARP_CHAR_INTO_CAR_AS_PASSENGER sw5_sweet sw5_playerscar 0
					ENDIF
					IF NOT IS_CHAR_IN_CAR sw5_sweetsgf sw5_playerscar
						CLEAR_CHAR_TASKS_IMMEDIATELY sw5_sweetsgf
						WARP_CHAR_INTO_CAR_AS_PASSENGER sw5_sweetsgf sw5_playerscar 1
					ENDIF

				ENDIF

				IF NOT IS_CAR_DEAD sw5_ballascar[1]
				AND NOT IS_CHAR_DEAD sw5_reddriver[1]
					OPEN_SEQUENCE_TASK sw5_seq
						TASK_CAR_TEMP_ACTION -1 sw5_ballascar[1] TEMPACT_WAIT 1500
						TASK_CAR_TEMP_ACTION -1 sw5_ballascar[1] TEMPACT_REVERSE_RIGHT 1000
					CLOSE_SEQUENCE_TASK sw5_seq
					PERFORM_SEQUENCE_TASK sw5_reddriver[1] sw5_seq
					CLEAR_SEQUENCE_TASK sw5_seq 
				ENDIF

				IF NOT IS_CAR_DEAD sw5_ballascar[0]
				AND NOT IS_CHAR_DEAD sw5_reddriver[0]
					TASK_CAR_TEMP_ACTION sw5_reddriver[0] sw5_ballascar[0] TEMPACT_WAIT 1500 
				ENDIF

				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				CLEAR_PRINTS
				PRINT SW5_I 5000 1
				sw5_flag = 6
				sw5_sweet_alive = 1
				can_play_audio_now_time = TIMERA + 3000
				next_insult = 0
				MAKE_PLAYER_GANG_REAPPEAR
			ENDIF

		ENDIF
	ENDIF
		 				
RETURN
	
	


// *****************************************************************************************
// ********** Drive back to the hub and avoid the attacking Seville cars if poss ***********
// *****************************************************************************************

sw5_scene3:						
		
		IF youre_shit = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer ab_car
				GET_CAR_HEALTH ab_car ab_health
				IF ab_health < 900
					IF play_audio = 0
						IF TIMERA > can_play_audio_now_time
							play_audio = 13
							youre_shit = 1
							can_play_audio_now_time = TIMERA + 6000
						ENDIF
					ENDIF
				ENDIF				
			ENDIF
		ENDIF

		IF TIMERA > can_play_audio_now_time
			IF NOT IS_CAR_DEAD sw5_ballascar[0]
			AND NOT IS_CAR_DEAD sw5_ballascar[1] 
				IF LOCATE_CHAR_IN_CAR_CAR_3D scplayer sw5_ballascar[0] 20.0 20.0 10.0 FALSE
				OR LOCATE_CHAR_IN_CAR_CAR_3D scplayer sw5_ballascar[1] 20.0 20.0 10.0 FALSE
					GENERATE_RANDOM_INT_IN_RANGE 0 5 ab_int
					ab_count = 0
					WHILE insult[ab_int] = 0
						ab_int ++
						ab_count ++
						IF ab_int > 4
							ab_int = 0
						ENDIF
						IF ab_count > 4							
							GOTO exit_this_loop
						ENDIF
					ENDWHILE
					play_audio = insult[ab_int]
					insult[ab_int] = 0
					exit_this_loop:					
					can_play_audio_now_time = TIMERA + 7500
				ELSE
					GENERATE_RANDOM_INT_IN_RANGE 0 1 ab_int
					ab_count = 0
					WHILE noninsult[ab_int] = 0
						ab_int ++
						ab_count ++
						IF ab_int > 1
							ab_int = 0
						ENDIF
						IF ab_count > 2
							GOTO exit_this_loopb
						ENDIF
					ENDWHILE
					play_audio = noninsult[ab_int]
					noninsult[ab_int] = 0		
					can_play_audio_now_time = TIMERA + 7500
				ENDIF
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 0 2 ab_int
				ab_count = 0
				WHILE noninsult[ab_int] = 0
					ab_int ++
					ab_count ++
					IF ab_int > 1
						ab_int = 0
					ENDIF
					IF ab_count > 2
						GOTO exit_this_loopb
					ENDIF
				ENDWHILE
				play_audio = noninsult[ab_int]
				noninsult[ab_int] = 0
				exit_this_loopb:					
				can_play_audio_now_time = TIMERA + 7500
			ENDIF

		ENDIF
			
 

		IF NOT IS_CHAR_DEAD sw5_sweet

			IF NOT IS_GROUP_MEMBER sw5_sweet Players_group
				IF sw5_sweet_lost = 0
					sw5_sweet_lost = 1
					PRINT sw5_T 4000 1
					IF NOT DOES_BLIP_EXIST sw5_sweet_blip
						ADD_BLIP_FOR_CHAR sw5_sweet sw5_sweet_blip 
						SET_BLIP_AS_FRIENDLY sw5_sweet_blip TRUE
					ENDIF
					REMOVE_BLIP	sw5_finish_blip
				ENDIF

				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw5_sweet 6.0 6.0 FALSE
				AND sw5_sweet_lost = 1
					sw5_sweet_lost = 0
					SET_GROUP_MEMBER Players_group sw5_sweet
					LISTEN_TO_PLAYER_GROUP_COMMANDS sw5_sweet FALSE
					


					REMOVE_BLIP sw5_sweet_blip
					IF sw5_sweetsgf_lost = 0 
						ADD_BLIP_FOR_COORD 2509.0618 -1672.5270 12.3860 sw5_finish_blip
					ENDIF
				ENDIF 
			ENDIF 
		ENDIF

		IF NOT IS_CHAR_DEAD sw5_sweetsgf

			IF NOT IS_GROUP_MEMBER sw5_sweetsgf Players_group
				IF sw5_sweetsgf_lost = 0
					sw5_sweetsgf_lost = 1
					PRINT sw5_U 4000 1
					IF NOT DOES_BLIP_EXIST sw5_sweetsgf_blip
						ADD_BLIP_FOR_CHAR sw5_sweetsgf sw5_sweetsgf_blip 
						SET_BLIP_AS_FRIENDLY sw5_sweetsgf_blip TRUE
					ENDIF
					REMOVE_BLIP	sw5_finish_blip
				ENDIF

				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw5_sweetsgf 6.0 6.0 FALSE
				AND sw5_sweetsgf_lost = 1
					sw5_sweetsgf_lost = 0
					SET_GROUP_MEMBER Players_group sw5_sweetsgf
					LISTEN_TO_PLAYER_GROUP_COMMANDS sw5_sweetsgf FALSE

					REMOVE_BLIP sw5_sweetsgf_blip
					IF sw5_sweet_lost = 0 
						ADD_BLIP_FOR_COORD 2509.0618 -1672.5270 12.3860 sw5_finish_blip
						SET_BLIP_AS_FRIENDLY sw5_finish_blip TRUE
					ENDIF
				ENDIF 
			ENDIF 
		ENDIF


		IF sw5_chase_car_created = 1
			IF NOT IS_CAR_DEAD sw5_ballascar[1]
			AND NOT IS_CHAR_DEAD sw5_reddriver[1]
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sw5_reddriver[1] 50.0 50.0 20.0 FALSE
					GET_CHAR_COORDINATES scplayer sw5_x sw5_y sw5_z
					GET_CHAR_HEADING scplayer sw5_h
					sw5_chase_car_created = 2
					sw5_chase_timer = TIMERA + 800
				ENDIF
			ENDIF
		ENDIF

		IF sw5_chase_car_created = 2
		AND sw5_chasing_cars_leave = 0
			IF TIMERA > sw5_chase_timer
				GET_NTH_CLOSEST_CAR_NODE player_x player_y player_z 2 sw5_newx sw5_newY sw5_newz
				IF NOT IS_POINT_ON_SCREEN sw5_x sw5_y sw5_z	8.0

					IF NOT LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer sw5_newx sw5_newY sw5_newz 10.0 10.0 10.0 FALSE
						IF NOT IS_CAR_DEAD sw5_ballascar[1]
						AND NOT IS_CHAR_DEAD sw5_reddriver[1]
							IF NOT IS_CAR_UPSIDEDOWN sw5_ballascar[1]
								IF IS_CHAR_IN_CAR sw5_reddriver[1] sw5_ballascar[1]
									SET_CAR_COORDINATES sw5_ballascar[1] sw5_x sw5_y sw5_z
									SET_CAR_HEADING sw5_ballascar[1] sw5_h
									SET_CAR_FORWARD_SPEED sw5_ballascar[1] 25.0
									sw5_chase_car_created = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD sw5_sweet
		AND NOT IS_CHAR_DEAD sw5_sweetsgf
			IF sw5_chasing_cars_leave = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2509.0618 -1672.5270 12.3860 63.0 63.0 60.0 FALSE
					sw5_chasing_cars_leave = 1

					IF NOT IS_CAR_DEAD sw5_ballascar[0] 
						SET_CAR_MISSION sw5_ballascar[0] MISSION_CRUISE
						SET_CAR_CRUISE_SPEED sw5_ballascar[0] 20.0
						
						MARK_CAR_AS_NO_LONGER_NEEDED sw5_ballascar[0]
						MARK_CHAR_AS_NO_LONGER_NEEDED sw5_reddriver[0]

					ENDIF
					IF NOT IS_CAR_DEAD sw5_ballascar[1] 
						SET_CAR_MISSION sw5_ballascar[1] MISSION_CRUISE
						SET_CAR_CRUISE_SPEED sw5_ballascar[1] 20.0

						MARK_CAR_AS_NO_LONGER_NEEDED sw5_ballascar[1]
						MARK_CHAR_AS_NO_LONGER_NEEDED sw5_reddriver[1]
					ENDIF
				ENDIF
			ENDIF
			IF sw5_sweetsgf_lost = 0
			AND sw5_sweet_lost = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2509.0618 -1672.5270 12.3860 4.0 4.0 4.0 TRUE
					IF LOCATE_CHAR_ANY_MEANS_3D sw5_sweet 2509.0618 -1672.5270 12.3860 8.0 8.0 4.0 FALSE
					AND LOCATE_CHAR_ANY_MEANS_3D sw5_sweetsgf 2509.0618 -1672.5270 12.3860 8.0 8.0 4.0 FALSE
				

						IF IS_CHAR_IN_ANY_CAR scplayer
					
							SET_PLAYER_CONTROL player1 OFF
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000

						ENDIF

						// Mission passed - view final cut scene
						sw5_flag = 7
						sw5_cut_flag = 0
					ENDIF
				ENDIF
			ENDIF			
		ENDIF

RETURN




// *****************************************************************************************
// ***** Sweet thanks CJ for his help, then enters the house to shag his dirty whore *******
// *****************************************************************************************

sw5_cutscene_4:

	IF sw5_cut_flag = 0
		sw5_sweet_alive = 0
		//MAKE_PLAYER_GANG_DISAPPEAR TRUE
		sw5_time = TIMERA + 600
		sw5_cut_flag = 12
	ENDIF
			

	IF sw5_cut_flag = 12
		IF TIMERA > sw5_time
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer sw5_playerscar
				GET_CAR_MODEL  sw5_playerscar sw5_car_model
			ENDIF

			IF NOT IS_CHAR_DEAD sw5_sweet
				SET_CHAR_PROOFS sw5_sweet TRUE TRUE TRUE TRUE TRUE

			ENDIF

			IF NOT IS_CHAR_DEAD sw5_sweetsgf
				SET_CHAR_PROOFS sw5_sweetsgf TRUE TRUE TRUE TRUE TRUE
			ENDIF

			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0

			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			

			CLEAR_AREA_OF_CARS 2472.1021 -1646.1813 19.6421 2517.3831 -1691.2318 9.4098
			CLEAR_AREA_OF_CHARS 2472.1021 -1646.1813 19.6421 2517.3831 -1691.2318 9.4098
					
			IF NOT IS_CAR_DEAD sw5_ballascar[0]
				IF NOT IS_CHAR_IN_CAR scplayer sw5_ballascar[0] 
					DELETE_CAR sw5_ballascar[0]
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD sw5_ballascar[1]
				IF NOT IS_CHAR_IN_CAR scplayer sw5_ballascar[1]
					DELETE_CAR sw5_ballascar[1]
				ENDIF
			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_CAR_DEAD sw5_playerscar
					TASK_LEAVE_ANY_CAR scplayer

					SET_CAR_COORDINATES sw5_playerscar 2507.7087 -1672.1918 13.458
					
					SET_CAR_HEADING sw5_playerscar 340.0
				ENDIF
			ENDIF

			SET_FIXED_CAMERA_POSITION 2514.2402 -1679.2682 14.5054 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2513.6494 -1678.4825 14.3221 JUMP_CUT

			IF NOT IS_CHAR_DEAD sw5_sweetsgf 
				GET_CAR_CHAR_IS_USING sw5_sweetsgf car
				IF car = -1					
					SET_CHAR_COORDINATES sw5_sweetsgf 2509.8210 -1675.8154 12.5469
					SET_CHAR_HEADING sw5_sweetsgf 337.0
					TASK_GO_STRAIGHT_TO_COORD sw5_sweetsgf 2512.2295 -1672.7843 12.4531 PEDMOVE_WALK -2
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD sw5_sweet 
				GET_CAR_CHAR_IS_USING sw5_sweet	car
				IF car = -1
					SET_CHAR_COORDINATES sw5_sweet 2508.8210 -1675.8154 12.5469
					SET_CHAR_HEADING sw5_sweet 337.0
					TASK_GO_STRAIGHT_TO_COORD sw5_sweet 2511.2295 -1672.7843 12.4531 PEDMOVE_WALK -2
				ENDIF
			ENDIF
		


			
			IF IS_CHAR_IN_ANY_CAR scplayer
				sw5_time = TIMERA + 300
				sw5_cut_flag = 10
			ELSE
				sw5_cut_flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 10
		IF TIMERA > sw5_time
			IF NOT IS_CHAR_DEAD sw5_sweetsgf 
				GET_CAR_CHAR_IS_USING sw5_sweetsgf car
				IF NOT car = -1					
					TASK_LEAVE_ANY_CAR sw5_sweetsgf					
				ENDIF
				sw5_time = TIMERA + 400			
				sw5_cut_flag = 11
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 11
		IF TIMERA > sw5_time
			IF NOT IS_CHAR_DEAD sw5_sweet 
				GET_CAR_CHAR_IS_USING sw5_sweet	car
				IF NOT car = -1
					TASK_LEAVE_ANY_CAR sw5_sweet
				ENDIF

				sw5_time = TIMERA + 2000
				sw5_cut_flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF sw5_cut_flag = 1
		IF TIMERA > sw5_time
			sw5_cut_flag = 2			
		ENDIF
	ENDIF

	IF sw5_cut_flag = 2

		IF NOT IS_CAR_DEAD sw5_playerscar
			SET_CAR_VISIBLE sw5_playerscar FALSE
		ENDIF

		SET_FIXED_CAMERA_POSITION 2509.7134 -1671.5581 13.5033 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2510.1899 -1672.389 13.8387 JUMP_CUT
 
		SET_CHAR_COORDINATES_NO_OFFSET scplayer 2509.8917 -1672.4692 13.5032
//		FREEZE_CHAR_POSITION scplayer TRUE
		SET_CHAR_HEADING scplayer 270.0																  
//		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer prtial_gngtlkH GANGS 4.0 FALSE 0 0 0 -2			  



		IF NOT IS_CHAR_DEAD sw5_sweet
		AND NOT IS_CHAR_DEAD sw5_sweetsgf

			DELETE_CHAR sw5_sweet
			CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 2752.3408 -1948.8669 16.3125 sw5_sweet

			SET_CURRENT_CHAR_WEAPON sw5_sweet WEAPONTYPE_UNARMED
			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED 

			REMOVE_CHAR_FROM_GROUP sw5_sweet
			REMOVE_CHAR_FROM_GROUP sw5_sweetsgf

			CLEAR_CHAR_TASKS_IMMEDIATELY sw5_sweet
			CLEAR_CHAR_TASKS_IMMEDIATELY sw5_sweetsgf

//			SET_CHAR_COLLISION sw5_sweet FALSE
//			SET_CHAR_COLLISION scplayer FALSE

			SET_CHAR_COORDINATES_NO_OFFSET sw5_sweet 2510.7917 -1672.4692 13.5032
			SET_CHAR_COORDINATES_NO_OFFSET sw5_sweetsgf 2510.7917 -1673.4692 13.5032 
//			FREEZE_CHAR_POSITION sw5_sweet TRUE
			SET_CHAR_HEADING sw5_sweet 90.0
			SET_CHAR_HEADING sw5_sweetsgf 90.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE sw5_sweet sweet_ass_slap SWEET 4.0 FALSE 0 0 0 -2
			TASK_PLAY_ANIM_NON_INTERRUPTABLE sw5_sweetsgf ho_ass_slapped SWEET 4.0 FALSE 0 0 0 -2
//			FREEZE_CHAR_POSITION sw5_sweet TRUE
//			FREEZE_CHAR_POSITION scplayer TRUE
		ENDIF
		audio_actor[2] = sw5_sweet

			play_audio_global = 1
			play_timed_audio = 23
			play_timed_audio_for = 5

			audio_time[0] = 100 
			audio_time[1] = 1900
			audio_time[2] = 4200 
			audio_time[3] = 6000
			audio_time[4] = 8000


			audio_to_play[0] = 23
			audio_to_play[1] = 24
			audio_to_play[2] = 25
			audio_to_play[3] = 26
			audio_to_play[4] = 27


		

		sw5_time = TIMERA + 1700
		sw5_cut_flag = 3
	ENDIF

	IF sw5_cut_flag = 3
		IF TIMERA > sw5_time

			IF NOT IS_CHAR_DEAD sw5_sweet
				TASK_PLAY_ANIM_NON_INTERRUPTABLE sw5_sweet prtial_gngtlkA GANGS 4.0 FALSE 0 0 0 -2
			ENDIF
			sw5_cut_flag = 4
			sw5_time = TIMERA + 300
		ENDIF
	ENDIF

	IF sw5_cut_flag = 4
		IF TIMERA > sw5_time
		
			IF NOT IS_CHAR_DEAD sw5_sweetsgf
				TASK_FOLLOW_PATH_NODES_TO_COORD sw5_sweetsgf 2516.6860 -1675.8606 13.1227 PEDMOVE_WALK 5500
			ENDIF

			sw5_cut_flag = 5
			sw5_time = TIMERA + 2000
		ENDIF
	ENDIF

	IF sw5_cut_flag = 5
		IF TIMERA > sw5_time
			TASK_PLAY_ANIM scplayer hndshkfa gangs 4.0 FALSE 0 0 0 -2

			IF NOT IS_CHAR_DEAD sw5_sweet
				TASK_PLAY_ANIM_NON_INTERRUPTABLE sw5_sweet hndshkfa gangs 4.0 FALSE 0 0 0 3400
			ENDIF
			sw5_cut_flag = 7
			sw5_time = TIMERA + 4000
		ENDIF
	ENDIF

	IF sw5_cut_flag = 7
		IF TIMERA > sw5_time
			IF NOT IS_CHAR_DEAD sw5_sweet
					TASK_PLAY_ANIM_NON_INTERRUPTABLE sw5_sweet sweet_hndshldr_01 SWEET 4.0 FALSE 0 0 0 -2
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer plyr_hndshldr_01 SWEET 4.0 FALSE 0 0 0 -2
					IF NOT IS_CHAR_DEAD sw5_sweetsgf
						DELETE_CHAR sw5_sweetsgf
					ENDIF

			ENDIF
			sw5_cut_flag = 8
			sw5_time = TIMERA + 3500
		ENDIF
	ENDIF

	IF sw5_cut_flag = 8
		IF TIMERA > sw5_time
			IF NOT IS_CHAR_DEAD sw5_sweet
				FREEZE_CHAR_POSITION sw5_sweet FALSE
				TASK_FOLLOW_PATH_NODES_TO_COORD sw5_sweet 2516.6860 -1675.8606 13.1227 PEDMOVE_WALK 5500
			ENDIF
			sw5_cut_flag = 9
			sw5_time = TIMERA + 1500
		ENDIF
	ENDIF

	IF sw5_cut_flag = 9
		IF TIMERA > sw5_time

			IF NOT IS_CHAR_DEAD sw5_sweet
				DELETE_CHAR sw5_sweet
			ENDIF

			IF NOT IS_CHAR_DEAD sw5_sweetsgf
				DELETE_CHAR sw5_sweetsgf
			ENDIF

			IF NOT IS_CAR_DEAD sw5_playerscar
				SET_CAR_VISIBLE sw5_playerscar TRUE
			ENDIF

			FREEZE_CHAR_POSITION scplayer FALSE


			SET_PLAYER_CONTROL player1 ON
			SET_CHAR_HEADING scplayer 90.0
			SWITCH_WIDESCREEN OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			// MISSION PASSED
			MAKE_PLAYER_GANG_REAPPEAR
			sw5_flag = 8
		ENDIF
	ENDIF
RETURN






sw5_ped_actions:

// There are five Seville gang members attacking Sweet.
// When the player gets within range, the two closest peds will shoot at the player.
// If the player moves around or peds are killed then the two nearest peds will always attack the player.
// The remaining peds will shoot up at Sweet's flat.

// TO ADD: Peds advance up the stairs and one ped cautiously leans from behind a wall.
// TO ADD: If a ped is interupted during an advance by the player then he should attack player, but return to his task if this changes.

LVAR_INT sw5_reds_attack sw5_red_check_time	sw5_this_red sw5_red_action[6] sw5_int
LVAR_FLOAT sw5_car_speed sw5_car_heading 
LVAR_FLOAT sw5_angle 

LVAR_INT sw5_check sw5_first_red_shoot_flag nearest_red_1 nearest_red_2
LVAR_FLOAT sw5_float sw5_float2 sw5_float3 nearest_range_1 nearest_range_2 sw5_distance

LVAR_INT sw5_red_blip1 sw5_red_blip2



//red_mode	1 = attack player
//			2 = attack flat
//			3 = go investigate
//			4 = take cover? (player attacks by car)
//
//
//attack_player
//attack_flat
//investigate_player


IF TIMERA > sw5_red_check_time
	sw5_red_check_time = TIMERA + 50
	sw5_this_red ++
	IF sw5_this_red = 5
		sw5_this_red = 0
	ENDIF
	
	IF NOT IS_CHAR_DEAD sw5_red[sw5_this_red]

		IF sw5_player_spotted = 0
//			IF HAS_CHAR_SPOTTED_CHAR sw5_red[sw5_this_red] scplayer
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw5_red[sw5_this_red] scplayer
				sw5_player_spotted = 1
			ENDIF
		ENDIF

		GET_CHAR_COORDINATES sw5_red[sw5_this_red] sw5_redx sw5_redy sw5_redz
		GET_CHAR_COORDINATES scplayer sw5_x sw5_y sw5_z

		GET_DISTANCE_BETWEEN_COORDS_2D sw5_x sw5_y sw5_redx sw5_redy sw5_distance



	   	IF NOT nearest_red_1 = sw5_this_red
			IF sw5_distance < nearest_range_1
//				REMOVE_BLIP sw5_red_blip1
//				ADD_BLIP_FOR_CHAR sw5_red[sw5_this_red] sw5_red_blip1
		   		nearest_red_2 = nearest_red_1
		   		nearest_range_2 = nearest_range_1
	   			nearest_range_1 = sw5_distance
	   			nearest_red_1 = sw5_this_red
			ENDIF
	   	ELSE
	   		nearest_range_1 = sw5_distance
	   	ENDIF
	   
		IF NOT nearest_red_2 = sw5_this_red
			IF NOT nearest_red_1 = sw5_this_red
				IF sw5_distance < nearest_range_2
//					REMOVE_BLIP sw5_red_blip2
//					ADD_BLIP_FOR_CHAR sw5_red[sw5_this_red] sw5_red_blip2
					nearest_red_2 = sw5_this_red
					nearest_range_2 = sw5_distance	
				ENDIF
			ENDIF
		ELSE
	   		nearest_range_2 = sw5_distance
		ENDIF
			
		IF IS_CHAR_IN_ANY_CAR scplayer
			GET_CAR_CHAR_IS_USING scplayer sw5_playerscar
			GET_CAR_SPEED sw5_playerscar sw5_car_speed

			IF sw5_car_speed > 5.0			

				GENERATE_RANDOM_FLOAT_IN_RANGE -0.2 0.2 sw5_float3

				sw5_float2 = sw5_car_speed * 1.6
				sw5_float2 += sw5_float3

				IF sw5_distance < sw5_float2

					sw5_float2 = sw5_z - sw5_redz
					ABS sw5_float2
					IF sw5_float2 < 1.0
						sw5_float = sw5_redx - sw5_x
						sw5_float2 = sw5_redy - sw5_y
						GET_HEADING_FROM_VECTOR_2D sw5_float sw5_float2 sw5_angle
					  
//							GET_ANGLE_BETWEEN_2D_VECTORS sw5_redx sw5_redy sw5_x sw5_y sw5_angle

						GET_CAR_HEADING sw5_playerscar sw5_car_heading
						sw5_float = sw5_angle - sw5_car_heading
		//				sw5_float -= 180.0
						ABS sw5_float
		//				IF sw5_float > 180.0
		//					sw5_float -= 180.0
		//					ABS sw5_float
		//				ENDIF
						IF sw5_float < 5.0
							// dive for cover (over wall?)
							sw5_player_spotted = 1
							sw5_red_action[sw5_this_red] = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		// roll
		IF 	sw5_red_action[sw5_this_red] = 4
			GET_SCRIPT_TASK_STATUS sw5_red[sw5_this_red] TASK_DIVE_AND_GET_UP sw5_task_status
			IF sw5_task_status = FINISHED_TASK
				TASK_DIVE_AND_GET_UP sw5_red[sw5_this_red] 1.0 0.0 500
				sw5_red_action[sw5_this_red] = 5
			ENDIF
		ENDIF

		IF sw5_red_action[sw5_this_red] = 5
			GET_SCRIPT_TASK_STATUS sw5_red[sw5_this_red] TASK_DIVE_AND_GET_UP sw5_task_status			
			IF sw5_task_status = FINISHED_TASK
				sw5_red_action[sw5_this_red] = 0
			ENDIF
		ENDIF

		IF sw5_red_action[sw5_this_red] = 0
			IF sw5_player_spotted = 1
				IF sw5_this_red = nearest_red_1
				OR sw5_this_red = nearest_red_2
					sw5_red_action[sw5_this_red] = 1
				ENDIF	
			ENDIF
		ENDIF


		//shoot at player
		IF sw5_red_action[sw5_this_red] = 1
			GET_SCRIPT_TASK_STATUS sw5_red[sw5_this_red] TASK_SHOOT_AT_CHAR sw5_task_status
			IF sw5_task_status = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS sw5_red[sw5_this_red] TASK_KILL_CHAR_ON_FOOT sw5_task_status
				IF sw5_task_status = FINISHED_TASK
//					IF sw5_this_red = 4
//						IF IS_CHAR_IN_AREA_3D scplayer 2751.0195 -1949.7622 19.1234 2754.6411 -1960.6786 16.5385 FALSE
//							OPEN_SEQUENCE_TASK sw5_seq
//								TASK_PLAY_ANIM -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
//								TASK_KILL_CHAR_ON_FOOT -1 scplayer
//							CLOSE_SEQUENCE_TASK sw5_seq
//							PERFORM_SEQUENCE_TASK sw5_red[4] sw5_seq
//							CLEAR_SEQUENCE_TASK sw5_seq
//							
//							sw5_red_action[4] = 2
//						ENDIF
//						IF IS_CHAR_IN_AREA_3D scplayer 2755.3232 -1949.2924 16.2442 2737.1890 -1915.9371 24.2411 FALSE
//							TASK_KILL_CHAR_ON_FOOT sw5_red[sw5_this_red] scplayer
//						ENDIF						
//					ELSE
						GENERATE_RANDOM_INT_IN_RANGE 0 2 sw5_int
						IF sw5_int = 0
							TASK_SHOOT_AT_CHAR sw5_red[sw5_this_red] scplayer -2
						ELSE
							TASK_KILL_CHAR_ON_FOOT sw5_red[sw5_this_red] scplayer
						ENDIF
//					ENDIF
				ENDIF
			ENDIF
			IF NOT sw5_this_red = nearest_red_1
			AND NOT sw5_this_red = nearest_red_2
				GET_SCRIPT_TASK_STATUS sw5_red[sw5_this_red] PERFORM_SEQUENCE_TASK sw5_task_Status
				IF sw5_task_status = FINISHED_TASK
					OPEN_SEQUENCE_TASK sw5_seq
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 sw5_red_x[sw5_this_red] sw5_red_y[sw5_this_red] sw5_red_z[sw5_this_red] PEDMOVE_RUN 6000
						TASK_SHOOT_AT_COORD -1 2750.9570 -1968.0157 17.9291 -2					
					CLOSE_SEQUENCE_TASK sw5_seq
					PERFORM_SEQUENCE_TASK sw5_red[sw5_this_red] sw5_seq
					CLEAR_SEQUENCE_TASK sw5_seq					
					sw5_red_action[sw5_this_red] = 0
				ENDIF
				
			ENDIF				
		ENDIF


	ELSE
		IF sw5_player_spotted = 0
			sw5_player_spotted = 1
		ENDIF
		IF sw5_this_red = nearest_red_1
			nearest_red_1 = nearest_red_2
			nearest_range_1 = nearest_range_2
			nearest_red_2 = 0
			nearest_range_2 = 99999.0
		ENDIF
		IF sw5_this_red = nearest_red_2
			nearest_red_2 = 0
			nearest_range_2 = 99999.0
		ENDIF
	ENDIF
ENDIF




//	IF sw5_scene_made = 1
//		IF sw5_reds_attack = 0
//			sw5_reds_attack = 1
//	
//			sw5_i = 0
//			WHILE sw5_i < 5
//				IF NOT IS_CHAR_DEAD sw5_red[sw5_i]
////					SET_CHAR_KINDA_STAY_IN_SAME_PLACE sw5_red[sw5_i] TRUE
//					TASK_KILL_CHAR_ON_FOOT sw5_red[sw5_i] scplayer
//				ENDIF
//				sw5_i ++
//			ENDWHILE
//		ENDIF
//	ENDIF







//	IF ped_attack_event[4] = 0
//		IF NOT IS_CHAR_DEAD sw5_red[4]
//			IF IS_CHAR_IN_AREA_3D scplayer 2751.0195 -1949.7622 19.1234 2754.6411 -1960.6786 16.5385 FALSE
//				TASK_PLAY_ANIM sw5_red[4] Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
//				ped_attack_event[4] = 1
//			ENDIF
//		ENDIF
//	ENDIF	
//
//	IF ped_attack_event[3] = 0
//		IF NOT IS_CHAR_DEAD sw5_red[3]
//			IF NOT sw5_nearest_red_a = 3
//			AND NOT sw5_nearest_red_b = 3
//				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sw5_red[3] 30.0 30.0 30.0 FALSE
//					TASK_GO_STRAIGHT_TO_COORD sw5_red[3] 2753.2715 -1979.6263 14.0545 PEDMOVE_WALK -2
//					ped_attack_event[3] = 1
//				ENDIF
//			ENDIF
//		ENDIF		
//	ENDIF
//
//	IF ped_attack_event[3] = 1
//		IF sw5_nearest_red_a = 3
//		OR sw5_nearest_red_b = 3
//			ped_attack_event[3] = 0
//		ELSE
//			IF NOT IS_CHAR_DEAD sw5_red[3]
//				GET_SCRIPT_TASK_STATUS sw5_red[3] TASK_GO_STRAIGHT_TO_COORD sw5_seq
//				IF sw5_seq = FINISHED_TASK
//					ped_attack_event[3] = 2
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF	
 
//			
//
//	IF TIMERA > next_red_time_check
//		next_red_time_check = TIMERA + 300
//
//		GET_CHAR_COORDINATES scplayer sw5_x sw5_y sw5_z
//
//		sw5_closest_dist_a = 99999.0
//		sw5_closest_dist_b = 99999.0
//		sw5_nearest_red_a = 10
//		sw5_nearest_red_b = 10
//
//		sw5_i = 0
//		sw5_player_spotted = 0
//		WHILE sw5_i < 5
//			IF NOT IS_CHAR_DEAD sw5_red[sw5_i]
//				GET_CHAR_COORDINATES sw5_red[sw5_i] sw5_redx sw5_redy sw5_redz
//				GET_DISTANCE_BETWEEN_COORDS_2D sw5_x sw5_y sw5_redx sw5_redy sw5_distance[sw5_i]
//				IF sw5_distance[sw5_i] < sw5_closest_dist_a
//					sw5_nearest_red_b = sw5_nearest_red_a
//					sw5_closest_dist_b = sw5_closest_dist_a
//					sw5_closest_dist_a = sw5_distance[sw5_i]
//					sw5_nearest_red_a = sw5_i
//				ELSE
//					IF sw5_distance[sw5_i] < sw5_closest_dist_b
//						sw5_nearest_red_b = sw5_i	
//						sw5_closest_dist_a = sw5_distance[sw5_i]
//					ENDIF
//				ENDIF
//
//				IF HAS_CHAR_SPOTTED_CHAR sw5_red[sw5_i] scplayer
//					IF sw5_spotted_for_first_time = 0
//						sw5_spotted_for_first_time = 1
//						PRINT sw5_15 4000 1	 // * PRINT "Hey that's Sweet's bro - get him" *
//					ENDIF							
//					sw5_player_spotted = 1
//				ENDIF
//			ENDIF
//			sw5_i ++
//		ENDWHILE
//
//	   	IF sw5_player_spotted = 0
//			sw5_i = 0
//			WHILE sw5_i < 5
//				IF NOT IS_CHAR_DEAD sw5_red[sw5_i]
//					IF sw5_target[sw5_i] = 1
//						IF NOT IS_CHAR_DEAD sw5_sweet
//							TASK_SHOOT_AT_CHAR sw5_red[sw5_i] sw5_sweet -2
//						ENDIF
//						sw5_target[sw5_i] = 0
//					ENDIF
//				ENDIF
//				sw5_i ++
//			ENDWHILE
//		ENDIF
//
//
//		IF sw5_player_spotted = 1
//			sw5_i = 0
//			WHILE sw5_i < 5
//				IF NOT IS_CHAR_DEAD sw5_red[sw5_i]
//					IF sw5_i = sw5_nearest_red_a
//					OR sw5_i = sw5_nearest_red_b
//						IF sw5_target[sw5_i] = 0
//							sw5_target[sw5_i] = 1
//							GET_SCRIPT_TASK_STATUS sw5_red[sw5_i] PERFORM_SEQUENCE_TASK sw5_seq
//							IF sw5_seq = FINISHED_TASK
//								OPEN_SEQUENCE_TASK sw5_seq
//									TASK_SHOOT_AT_CHAR -1 scplayer -2
//								CLOSE_SEQUENCE_TASK sw5_seq
//								PERFORM_SEQUENCE_TASK sw5_red[sw5_i] sw5_seq
//								CLEAR_SEQUENCE_TASK sw5_seq
//							ENDIF
//						ENDIF
//					ELSE
//						IF sw5_target[sw5_i] = 1
//							IF NOT IS_CHAR_DEAD sw5_sweet
//								GET_SCRIPT_TASK_STATUS sw5_red[sw5_i] TASK_SHOOT_AT_COORD sw5_seq
//								IF sw5_seq = FINISHED_TASK
//									TASK_SHOOT_AT_COORD sw5_red[sw5_i] 2750.9570 -1968.0157 17.9291 -2
//								ENDIF
//							ENDIF
//							sw5_target[sw5_i] = 0
//						ENDIF
//					ENDIF
//				ENDIF
//				sw5_i ++
//			ENDWHILE
//		ENDIF
//	ENDIF

RETURN



// ******************************************
// Audio
// ******************************************



sweet5_audio:


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

//			VIEW_INTEGER_VARIABLE play_audio play_audio
//			VIEW_INTEGER_VARIABLE actor_int actor_int
//			VIEW_INTEGER_VARIABLE this_actor this_actor
//			VIEW_INTEGER_VARIABLE sw5_sweet sw5_sweet
//			VIEW_INTEGER_VARIABLE audio_flag audio_flag
//			VIEW_INTEGER_VARIABLE audio_actor[2] audio_actor[2]

			LVAR_INT audio_for_char[40] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay

//			$audio_text[0] = &CAT4_BI

			$audio_text[1] = &MSWE13A //Yo.
			audio_sound[1] = SOUND_MSWE13A

			$audio_text[2] = &MSWE13B //Carl! No time to chat!
			audio_sound[2] = SOUND_MSWE13B

			$audio_text[3] = &MSWE13C //Been seeing a Seville Families girl.
			audio_sound[3] = SOUND_MSWE13C

			$audio_text[4] = &MSWE13D //Word got out and the Seville boys don’t like it.
			audio_sound[4] = SOUND_MSWE13D

			$audio_text[5] = &MSWE13E //I’m pinned down In Seville turf and we need a ride out of here!
			audio_sound[5] = SOUND_MSWE13E

			$audio_text[6] = &MSWE13F //Sure thing, man, hang in there!
			audio_sound[6] = SOUND_MSWE13F

			$audio_text[7] = &MSWE13G //Drop by Emmet’s and get strapped!
			audio_sound[7] = SOUND_MSWE13G

			$audio_text[8] = &SWE5_FA //Drop by Emmet’s and get strapped!
			audio_sound[8] = SOUND_SWE5_FA

			$audio_text[9] = &SWE5_FB //Drop by Emmet’s and get strapped!
			audio_sound[9] = SOUND_SWE5_FB

			$audio_text[10] = &SWE5_GH //Drop by Emmet’s and get strapped!
			audio_sound[10] = SOUND_SWE5_GH

			$audio_text[11] = &SWE5_CA //Drop by Emmet’s and get strapped!
			audio_sound[11] = SOUND_SWE5_CA

			$audio_text[12] = &SWE5_CC //Drop by Emmet’s and get strapped!
			audio_sound[12] = SOUND_SWE5_CC

			$audio_text[13] = &SWE5_GA //Drop by Emmet’s and get strapped!
			audio_sound[13] = SOUND_SWE5_GA

			$audio_text[14] = &SWE5_GC //Drop by Emmet’s and get strapped!
			audio_sound[14] = SOUND_SWE5_GC

			$audio_text[15] = &SWE5_GD //Drop by Emmet’s and get strapped!
			audio_sound[15] = SOUND_SWE5_GD

			$audio_text[16] = &SWE5_GE //Drop by Emmet’s and get strapped!
			audio_sound[16] = SOUND_SWE5_GE

			$audio_text[17] = &SWE5_GF //Drop by Emmet’s and get strapped!
			audio_sound[17] = SOUND_SWE5_GF

			$audio_text[18] = &SWE5_GG //Drop by Emmet’s and get strapped!
			audio_sound[18] = SOUND_SWE5_GG

			$audio_text[19] = &SWE5_GJ //Drop by Emmet’s and get strapped!
			audio_sound[19] = SOUND_SWE5_GJ

			$audio_text[20] = &SWE5_GK //Drop by Emmet’s and get strapped!
			audio_sound[20] = SOUND_SWE5_GK

			$audio_text[21] = &SWE5_GB //Drop by Emmet’s and get strapped!
			audio_sound[21] = SOUND_SWE5_GB

			$audio_text[22] = &SWE5_GL //Drop by Emmet’s and get strapped!
			audio_sound[22] = SOUND_SWE5_GL

			$audio_text[23] = &SWE5_EA //Drop by Emmet’s and get strapped!
			audio_sound[23] = SOUND_SWE5_EA

			$audio_text[24] = &SWE5_EB //Drop by Emmet’s and get strapped!
			audio_sound[24] = SOUND_SWE5_EB

			$audio_text[25] = &SWE5_EC //Drop by Emmet’s and get strapped!
			audio_sound[25] = SOUND_SWE5_EC

			$audio_text[26] = &SWE5_ED //Drop by Emmet’s and get strapped!
			audio_sound[26] = SOUND_SWE5_ED

			$audio_text[27] = &SWE5_EE //Drop by Emmet’s and get strapped!
			audio_sound[27] = SOUND_SWE5_EE

//			$audio_text[28] = &SWE5_EF //Drop by Emmet’s and get strapped!
//			audio_sound[28] = SOUND_SWE5_EF

			$audio_text[29] = &MOBRING //Drop by Emmet’s and get strapped!
			audio_sound[29] = SOUND_MOBRING



			audio_for_char[1] = 1
			audio_for_char[2] = 0
			audio_for_char[3] = 0
			audio_for_char[4] = 0
			audio_for_char[5] = 0
			audio_for_char[6] = 1
			audio_for_char[7] = 0
			audio_for_char[8] = 0
			audio_for_char[9] = 0
			audio_for_char[10] = 3
			audio_for_char[11] = 0
			audio_for_char[12] = 0
			audio_for_char[13] = 2
			audio_for_char[14] = 2
			audio_for_char[15] = 2
			audio_for_char[16] = 1
			audio_for_char[17] = 1
			audio_for_char[18] = 1
			audio_for_char[19] = 3
			audio_for_char[20] = 3
			audio_for_char[21] = 2
			audio_for_char[22] = 3
			audio_for_char[23] = 2
			audio_for_char[24] = 2
			audio_for_char[25] = 2
			audio_for_char[26] = 1
			audio_for_char[27] = 2
			audio_for_char[28] = 2
			audio_for_char[29] = 0



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

								play_audio++
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


	   



 // **************************************** Mission hood5 failed ************************

mission_hood5_failed:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN






   

// **************************************** mission hood5 passed *************************

mission_hood5_passed:

	flag_sweet_mission_counter ++
	REGISTER_MISSION_PASSED ( SWEET_5 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1 
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1

	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 7 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 7 //amount of respect

//	START_NEW_SCRIPT ryder_mission_loop
//	REMOVE_BLIP ryder_contact_blip
//	ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip


RETURN
		





// ********************************** mission cleanup ************************************

mission_cleanup_hood5:

	flag_player_on_mission = 0

	SWITCH_CAR_GENERATOR gen_car7 101

	//

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

	IF DOES_CHAR_EXIST sw5_sweet
		IF NOT IS_CHAR_DEAD sw5_sweet
			SET_CHAR_PROOFS sw5_sweet FALSE FALSE FALSE FALSE FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw5_sweet FALSE
			SET_CHAR_NEVER_TARGETTED sw5_sweet FALSE
			MARK_CHAR_AS_NO_LONGER_NEEDED sw5_sweet
		ENDIF
	ENDIF
	IF DOES_CHAR_EXIST sw5_sweetsgf
		IF NOT IS_CHAR_DEAD sw5_sweetsgf
			SET_CHAR_PROOFS sw5_sweetsgf FALSE FALSE FALSE FALSE FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw5_sweetsgf FALSE
			SET_CHAR_NEVER_TARGETTED sw5_sweetsgf FALSE
			MARK_CHAR_AS_NO_LONGER_NEEDED sw5_sweetsgf
		ENDIF
	ENDIF
	//
//	IF NOT IS_CAR_DEAD sw5_ballascar[0]
//		MARK_CAR_AS_NO_LONGER_NEEDED sw5_ballascar[0]
//	ENDIF
//
//	IF NOT IS_CAR_DEAD sw5_ballascar[1]
//		MARK_CAR_AS_NO_LONGER_NEEDED sw5_ballascar[1]
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD sw5_reddriver[0]
//		MARK_CHAR_AS_NO_LONGER_NEEDED sw5_reddriver[0]
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD sw5_reddriver[1]
//		MARK_CHAR_AS_NO_LONGER_NEEDED sw5_reddriver[1]
//	ENDIF

//	IF NOT IS_CHAR_DEAD sw5_redpas[0]
//		MARK_CHAR_AS_NO_LONGER_NEEDED sw5_redpas[0]
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD sw5_redpas[1]
//		MARK_CHAR_AS_NO_LONGER_NEEDED sw5_redpas[1]
//	ENDIF

	//
	sw5_i = 0		
	WHILE sw5_i < 5
			MARK_CHAR_AS_NO_LONGER_NEEDED sw5_red[sw5_i]
		sw5_i ++
	ENDWHILE

	IF NOT IS_CHAR_DEAD scplayer
		TASK_USE_MOBILE_PHONE scplayer FALSE
	ENDIF


	REMOVE_ANIMATION SWEET
	REMOVE_ANIMATION GANGS
//	REMOVE_ANIMATION SWAT

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI
	MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
	UNLOAD_SPECIAL_CHARACTER 1 

	REMOVE_CAR_RECORDING 153
	REMOVE_CAR_RECORDING 154

	SWITCH_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
	SWITCH_ROADS_BACK_TO_ORIGINAL 2463.3755 -1702.6946 17.9193 2508.7346 -1644.5736 10.5835

	IF NOT IS_CAR_DEAD sw5_generatecar
		MARK_CAR_AS_NO_LONGER_NEEDED sw5_generatecar
	ENDIF

	CLEAR_ONSCREEN_COUNTER sw5_sweet_health

	CLEAR_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

	//
	// 
	//
	REMOVE_BLIP sw5_red_blip[0]
	REMOVE_BLIP sw5_red_blip[1]
	REMOVE_BLIP sw5_red_blip[2]
	REMOVE_BLIP sw5_red_blip[3]
	REMOVE_BLIP sw5_red_blip[4]


	REMOVE_BLIP sw5_house_blip 
	REMOVE_BLIP sw5_finish_blip
	REMOVE_BLIP sw5_emmet_blip
	
	REMOVE_BLIP sw5_sweet_blip
	REMOVE_BLIP sw5_sweetsgf_blip
	//
	//
	////REMOVE_BLIP sw5_house_blip 
	////REMOVE_BLIP sw5_finish_blip
	////REMOVE_BLIP sw5_emmet_blip
	//
	SET_WANTED_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SWITCH_EMERGENCY_SERVICES ON
	//
	REMOVE_DECISION_MAKER sw5_seville_dec
	REMOVE_DECISION_MAKER sw5_empty_dec
	//REMOVE_GROUP sw5_red_group

	SET_CREATE_RANDOM_GANG_MEMBERS ON
	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS Player1 FALSE


	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED



RETURN

}

 			 