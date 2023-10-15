MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** LA1FIN2 *****************************************
// ************************************** Green Sabre **************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************


SCRIPT_NAME la1fin2

// Mission start stuff

GOSUB mission_start_la1fin2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_la1fin2_failed
ENDIF																			 

GOSUB mission_cleanup_la1fin2

MISSION_END
{
  
// **************************************** Mission Start **********************************

// **************************************** Declare variables ****************************************

LVAR_INT lf2_mission_fail LF2_sweet_alive 

// chars
LVAR_INT lf2_sweet l1f2_driver l1f2_pass1 l1f2_pass2

//cars
LVAR_INT l1f2_meet_car lf2_car_id[5] l1f2_flatpony[2] l1f2_driveby_car[2]

//blips
LVAR_INT l1f2_blip lf2_red_blip[6]

//flags
LVAR_INT lf2_blue_script_act[5] lf2_red_script_act[6] lf2_player_entered_car[5]
LVAR_INT lf2_car_action[2] lf2_red_script_event[6] l1f2_health
LVAR_INT lf2_leave_car_check lf2_leave_car_time	lf2_alive_for_firt_time

//general
LVAR_INT lv1 lf2_char_id lf2_players_weapon
LVAR_INT lf2_dead_red_counted[6]
LVAR_INT  lf2_leave_pony  l1f2_car_action_time[2] l1f2_car_Action[2]
LVAR_INT lf2_dead_reds lf2_flag lf2_scene_flag

//time check
LVAR_INT lf2_time_check

//decision makers
LVAR_INT lf2_empty_dec lf2_driveby_dec

//sequences
LVAR_INT lf2_seq

LVAR_INT lf2_car_health[5]

LVAR_FLOAT l1f2_cosheading l1f2_sinheading


IF lf2_flag = 99
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408  -1948.8669 16.3125 lf2_sweet
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 lf2_blue[0]
	CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 lf2_red[0]
	CREATE_CAR BRAVURA 1650.1147 -1055.2426 22.8984 l1f2_grovecar[0]
	CREATE_CAR SABRE 1643.9830 -1069.0099 22.8984 l1f2_flatcar[0]
	CREATE_CAR SABRE 1643.9830 -1069.0099 22.8984 lf2_CarID
	CREATE_CAR SABRE 1643.9830 -1069.0099 22.8984 lf2_Car_ID[0]
	COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH lf2_dec
	COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH lf2_reddec
	ADD_BLIP_FOR_CHAR lf2_sweet lf2_red_blip[0]
	ADD_BLIP_FOR_COORD 1648.7145 -1050.0944 22.9141 l1f2_blip
	CREATE_CAR SABRE 1643.9830 -1069.0099 22.8984 copcar_bust_cut[0]
	CREATE_CAR COPCARLA 0.0 0.0 0.0 copcar_bust_cut[2]
	CREATE_CHAR_INSIDE_CAR copcar_bust_cut[0] PEDTYPE_CIVMALE LAPD1 cop_bust_cut[0]
ENDIF




mission_start_la1fin2:

//	VIEW_INTEGER_VARIABLE lf2_car_health[0] lf2_car_health[0] 
//	VIEW_INTEGER_VARIABLE lf2_car_health[1] lf2_car_health[1]
//	VIEW_INTEGER_VARIABLE lf2_car_health[2] lf2_car_health[2]
//	VIEW_INTEGER_VARIABLE lf2_car_health[3] lf2_car_health[3]
//	VIEW_INTEGER_VARIABLE lf2_dead_reds lf2_dead_reds
//	VIEW_INTEGER_VARIABLE lf2_flag lf2_flag
//	VIEW_INTEGER_VARIABLE lf2_scene_flag lf2_scene_flag

	REGISTER_MISSION_GIVEN

	flag_player_on_mission = 1
	lf2_alive_for_firt_time = 1
	force_audio = 1
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

//	DO_FADE 1000 FADE_IN
//	REQUEST_MODEL SENTINEL
//	WHILE NOT HAS_MODEL_LOADED SENTINEL
//		WAIT 0
//	ENDWHILE
//	CREATE_CAR SENTINEL 2516.0 -1675.0 14.0 sw5_playerscar
//	WARP_CHAR_INTO_CAR scplayer sw5_playerscar



	lf2_mission_loop:



		WAIT 0

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
	    	SAVE_INT_TO_DEBUG_FILE lf2_flag
		ENDIF


		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	    	GOTO mission_la1fin2_passed  
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
//	    	SET_CHAR_COORDINATES scplayer 1624.5371 -1863.4377 12.5549  
			SET_CHAR_COORDINATES scplayer 1650.4561 -1039.6135 22.8984
			lf2_flag = 4
			lf2_scene_flag = 0
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_G
			lf2_flag = 7
			
			lf2_i = 0
			WHILE lf2_i < 6
				IF NOT IS_CHAR_DEAD lf2_red[lf2_i]
					TASK_DIE lf2_red[lf2_i]
				ENDIF
				lf2_i ++
			ENDWHILE
			IF NOT IS_CAR_DEAD copcar_bust_cut[0]
				DELETE_CAR copcar_bust_cut[0]
			ENDIF
			IF NOT IS_CAR_DEAD copcar_bust_cut[1]
				DELETE_CAR copcar_bust_cut[1]
			ENDIF
			IF NOT IS_CAR_DEAD copcar_bust_cut[2]
				DELETE_CAR copcar_bust_cut[2]
			ENDIF
			SWITCH_WIDESCREEN ON
			
			SET_PLAYER_CONTROL player1 OFF
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D
	    	SET_CHAR_COORDINATES scplayer 1624.5371 -1863.4377 12.5549  
		ENDIF


		SWITCH LF2_flag
			CASE 0
				GOSUB LF2_initialise_stuff
				LF2_flag = 1
			BREAK

			CASE 1
				// Mocap scene 
				GOSUB LF2_cutscene_1
				LF2_flag = 2			   
			BREAK

			CASE 2
				//Get over to Cesar 
				GOSUB LF2_scene_1
			BREAK

			CASE 3
				//Cut of player in car with cesar
				GOSUB LF2_cutscene_2
			BREAK

			CASE 4
				//Get to Sweet
				GOSUB LF2_scene_2
			BREAK

			CASE 5
				//Sweet injured lying on floor
				GOSUB LF2_cutscene_3
			BREAK

			CASE 6
				//Get to Sweet
				GOSUB LF2_scene_3
			BREAK

			CASE 7
				//Cops turn up - CJ gets busted.
				GOSUB LF2_cutscene_4
			BREAK

 
			CASE 8
				GOTO mission_la1fin2_passed
			BREAK


		ENDSWITCH

		GOSUB LF2_fail_checks
		GOSUB LF2_mission_checks
		GOSUB lafin2_audio

		IF lf2_mission_fail = 1
			GOTO mission_la1fin2_failed
		ENDIF

	GOTO lf2_mission_loop

RETURN


LF2_fail_checks:

	IF LF2_sweet_alive = 1
		IF IS_CHAR_DEAD LF2_sweet
			LF2_mission_fail = 1	
			PRINT LA1_16 4000 1
		ENDIF

	ENDIF

RETURN
		  


RETURN

LF2_mission_checks:

	// set ped and car density down if player is near shootout area
	IF lf2_flag > 1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1650.6447 -1059.4348 25.4852 100.0 100.0 100.0 FALSE 
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0
		ELSE
			SET_PED_DENSITY_MULTIPLIER 0.6
			SET_CAR_DENSITY_MULTIPLIER 0.6
		ENDIF		

		lf2_carid = l1f2_flatcar[0]
		lv1 = 0
		GOSUB lf2_car_damage

		lf2_carid = l1f2_flatcar[1]
		lv1 = 1
		GOSUB lf2_car_damage

		lf2_carid = l1f2_grovecar[2]
		lv1 = 2
		GOSUB lf2_car_damage

		lf2_carid = l1f2_grovecar[0]
		lv1 = 3
		GOSUB lf2_car_damage

		lf2_carid = l1f2_grovecar[1]
		lv1 = 4
		GOSUB lf2_car_damage

	ENDIF

RETURN

LF2_initialise_stuff:

	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY lf2_empty_dec
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY lf2_driveby_dec

	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_driveby_dec EVENT_ACQUAINTANCE_PED_HATE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_driveby_dec EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

RETURN


// *****************************************************************************************
// ***** DESCRIPTION *******
// *****************************************************************************************

LF2_cutscene_1:

	LOAD_MISSION_TEXT LAFIN2

	SET_AREA_VISIBLE 1

	LOAD_CUTSCENE FINAL2A
	 
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

	LOAD_SCENE 2518.7893 -1677.2203 13.5079


	// fades the screen in 

//	l2_skip1:

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	SET_CHAR_COORDINATES scplayer 2519.8254 -1677.7141 13.7425
	SET_CHAR_HEADING scplayer 67.0

	REQUEST_MODEL BRAVURA
	REQUEST_MODEL CELLPHONE

	WHILE NOT HAS_MODEL_LOADED BRAVURA
	OR NOT HAS_MODEL_LOADED CELLPHONE
		WAIT 0
	ENDWHILE

	CREATE_CAR BRAVURA 1625.0871 -1857.4377 12.5549 l1f2_meet_car
	CHANGE_CAR_COLOUR l1f2_meet_car 32 1
	FREEZE_CAR_POSITION l1f2_meet_car TRUE
	SET_CAR_PROOFS l1f2_meet_car TRUE TRUE TRUE TRUE TRUE
	SET_CAR_HEADING l1f2_meet_car 180.0

	WAIT 500

	IF lf2_skip_allowed = 1
		SET_UP_SKIP	1642.2986 -1869.6426 12.3906 90.0
	ENDIF

	DO_FADE 500 FADE_IN
	
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION 2513.4983 -1671.7406 13.2117 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2513.9429 -1672.6283 13.3312 JUMP_CUT
	
	SET_PLAYER_CONTROL player1 OFF

	OPEN_SEQUENCE_TASK lf2_sequence
		TASK_GO_STRAIGHT_TO_COORD -1 2514.9888 -1674.0801 12.7225 PEDMOVE_WALK -1
		TASK_PAUSE -1 1000
		TASK_USE_MOBILE_PHONE -1 TRUE
	CLOSE_SEQUENCE_TASK lf2_sequence
	PERFORM_SEQUENCE_TASK scplayer lf2_sequence
	CLEAR_SEQUENCE_TASK lf2_sequence

	LVAR_INT using_phone

	play_audio = 17
	phone_wait_time = TIMERA + 3500
//	play_audio_for = 11
	using_phone = 1
	   
RETURN

// *****************************************************************************************
// ********** DESCRIPTION ***********
// *****************************************************************************************

LF2_scene_1:


	IF using_phone = 1
		IF TIMERA > phone_wait_time		
			IF play_audio = 0
				play_audio = 18
				phone_wait_time = TIMERA + 2500
				using_phone = 2
//				TASK_USE_MOBILE_PHONE scplayer TRUE
			ENDIF
		ENDIF
	ENDIF

	IF using_phone = 2
		IF TIMERA > phone_wait_time
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			SWITCH_WIDESCREEN OFF
			
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
		   	SET_PLAYER_CONTROL player1 ON

			audio_flag = 1
			play_audio = 19	
			play_audio_for = 11
			using_phone = 3
			
		ENDIF
	ENDIF

	IF using_phone = 3
		IF NOT play_audio = 0
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				play_audio = 0
				audio_flag = 5
			ENDIF
		ENDIF
		IF play_audio = 0
			IF audio_flag = 1
				TASK_USE_MOBILE_PHONE scplayer FALSE
				MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
				using_phone = 0
				PRINT LA1_33 7000 1
				REMOVE_BLIP l1f2_blip
				ADD_BLIP_FOR_COORD 1631.7911 -1867.2876 12.5381	l1f2_blip
			ENDIF
		ENDIF
	ENDIF

	//get to the car
	IF lf2_scene_flag = 0 
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1631.7911 -1867.2876 12.5381 4.0 4.0 4.0 TRUE
			CLEAR_SKIP
			lf2_skip_allowed = 1
			IF IS_CHAR_IN_ANY_CAR scplayer
				SET_PLAYER_CONTROL player1 OFF
				LVAR_INT force_player_break force_time
				force_player_break = 1
				force_time = TIMERA + 1500
			ENDIF
			REMOVE_BLIP l1f2_blip
			IF NOT IS_CAR_DEAD l1f2_meet_car
				REMOVE_BLIP l1f2_blip
				ADD_BLIP_FOR_CAR l1f2_meet_car l1f2_blip
				SET_BLIP_AS_FRIENDLY l1f2_blip TRUE
			ENDIF
			PRINT LA1_35 4000 1
			lf2_scene_flag = 1
		ENDIF
	ENDIF

	IF force_player_break = 1
		IF TIMERA > force_time
			TASK_LEAVE_ANY_CAR scplayer
			SET_PLAYER_CONTROL player1 ON
			force_player_break = 2
		ENDIF
	ENDIF

	//get in the car
	IF lf2_scene_flag = 1
		IF NOT IS_CAR_DEAD l1f2_meet_car
			IF IS_CHAR_IN_CAR scplayer l1f2_meet_car
				REMOVE_BLIP l1f2_blip
				CLEAR_PRINTS
				lf2_flag = 3
			ENDIF
		ENDIF
	ENDIF				
		
RETURN


// *****************************************************************************************
// *************** Cutscene of Cesar showing CJ that Smoke set them up *********************
// *****************************************************************************************

LF2_cutscene_2:

//	GOTO l2_skip2
	MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA


	SET_FADING_COLOUR 0 0 0

	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD l1f2_meet_car
		SET_CAR_VISIBLE l1f2_meet_car FALSE
	ENDIF

	CLEAR_AREA_OF_CARS 1626.6332 -1876.4004 16.8284 1601.1116 -1902.1034 10.6931
	CLEAR_AREA_OF_CHARS 1626.6332 -1876.4004 16.8284 1601.1116 -1902.1034 10.6931

	REMOVE_BLIP l1f2_blip

	MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
	SWITCH_STREAMING OFF

	LOAD_CUTSCENE FINAL2B
	 
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

//	l2_skip2:

	SWITCH_WIDESCREEN OFF
	
	SET_PLAYER_CONTROL player1 ON

	IF NOT IS_CAR_DEAD l1f2_meet_car
		SET_CAR_VISIBLE l1f2_meet_car TRUE
		FREEZE_CAR_POSITION l1f2_meet_car FALSE
		SET_CAR_PROOFS l1f2_meet_Car FALSE FALSE FALSE FALSE FALSE
		MARK_CAR_AS_NO_LONGER_NEEDED l1f2_meet_car		
	ENDIF

	// blip for sweet
	REMOVE_BLIP l1f2_blip
	ADD_BLIP_FOR_COORD 1648.7145 -1050.0944 22.9141 l1f2_blip
	PRINT LA1_8 5000 1
	
	// Initialise Sweets health bar
	LVAR_INT l1f2_check_sweet_health l1f2_sweet_health_drop_time
	VAR_INT l1f2_sweet_health

	l1f2_sweet_health = 100
 	l1f2_check_sweet_health = 1

	GENERATE_RANDOM_INT_IN_RANGE 4000 8000 l1f2_sweet_health_drop_time
	l1f2_sweet_health_drop_time += TIMERA

	DISPLAY_ONSCREEN_COUNTER_WITH_STRING l1f2_sweet_health COUNTER_DISPLAY_BAR LA1_4
	GENERATE_RANDOM_INT_IN_RANGE 4000 8000 l1f2_sweet_health_drop_time
	l1f2_sweet_health_drop_time += TIMERA

	DO_FADE 500 FADE_IN

	IF NOT IS_CAR_DEAD l1f2_meet_car
		IF NOT IS_CHAR_IN_CAR scplayer l1f2_meet_car
		LVAR_INT lf2_DriverCharID
			GET_DRIVER_OF_CAR l1f2_meet_car lf2_DriverCharID
			IF NOT lf2_DriverCharID = scplayer
				IF NOT IS_CHAR_DEAD lf2_DriverCharID
					DELETE_CHAR lf2_DriverCharID
				ENDIF
			ENDIF 

			WARP_CHAR_INTO_CAR scplayer l1f2_meet_car
			MARK_CAR_AS_NO_LONGER_NEEDED l1f2_meet_car
			MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA
		ENDIF
	ENDIF

	TASK_LEAVE_ANY_CAR scplayer
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	lf2_flag = 4
	lf2_scene_flag = 0

	SWITCH_EMERGENCY_SERVICES OFF


	   
RETURN


// *****************************************************************************************
// ********** DESCRIPTION ***********
// *****************************************************************************************

LF2_scene_2:

	//if shootout scene not created then check player within 150m and create						
	GOSUB lf2_shootout_creation

	//do some AI stuff
	GOSUB lf2_gang_AI

	//sweet health

	LVAR_INT cj_phones_sweet
	IF cj_phones_sweet = 0
		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		IF player_y > -1800.0
			IF IS_CHAR_IN_ANY_CAR scplayer
				play_audio = 10
				play_audio_for = 2
				cj_phones_sweet = 1
				LVAR_INT phone_wait_time
				phone_wait_time = TIMERA + 6000
			ENDIF
		ENDIF
	ENDIF

	IF cj_phones_sweet = 1
		IF IS_CHAR_IN_ANY_CAR scplayer
			IF TIMERA > phone_wait_time
				play_audio = 12	
				play_audio_for = 5
				cj_phones_sweet	= 2
			ENDIF
		ENDIF
	ENDIF

	IF cj_phones_sweet > 0
	AND cj_phones_sweet < 3
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			force_audio = 0
			play_audio = 0
			play_audio_for = 0
			cj_phones_sweet = 3
		ENDIF
	ENDIF

	LVAR_INT l1f2_health_drop

	IF TIMERA > l1f2_sweet_health_drop_time
		IF NOT IS_CHAR_DEAD lf2_blue[0] 
			IF lf2_alive_for_firt_time = 1
				SET_CHAR_HEALTH lf2_blue[0] l1f2_sweet_health
				lf2_alive_for_firt_time = 0
			ENDIF
			GET_CHAR_HEALTH lf2_blue[0] l1f2_sweet_health			
		ENDIF

		GENERATE_RANDOM_INT_IN_RANGE 4000 8000 l1f2_sweet_health_drop_time
		l1f2_sweet_health_drop_time += TIMERA
		GENERATE_RANDOM_INT_IN_RANGE 4 8 l1f2_health_drop
		l1f2_sweet_health -= l1f2_health_drop
		IF l1f2_sweet_health <= 0
		   l1f2_sweet_health = 0
		   PRINT LA1_16 4000 1
		   lf2_mission_fail = 1 		   	
		ENDIF
			
		IF NOT IS_CHAR_DEAD lf2_blue[0]
			SET_CHAR_HEALTH lf2_blue[0] l1f2_sweet_health
		ENDIF
		
	ENDIF



	IF lf2_scene_flag = 0
		IF lf2_shootout_created = 2
			IF LOCATE_CHAR_ANY_MEANS_3D	scplayer 1648.7145 -1050.0944 22.9141 4.0 4.0 4.0 TRUE
				IF NOT IS_CHAR_DEAD lf2_blue[0]
					SET_CHAR_PROOFS lf2_blue[0] TRUE TRUE TRUE TRUE TRUE					
					SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				ENDIF
				lf2_scene_flag = 1
			ENDIF

			IF lf2_dead_reds = 6
				IF NOT IS_CHAR_DEAD lf2_blue[0]
					SET_CHAR_PROOFS lf2_blue[0] TRUE TRUE TRUE TRUE TRUE					
					SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				ENDIF
				lf2_scene_flag = 1
				lf2_time_check = TIMERA + 2000
			ENDIF
		ENDIF
	ENDIF

	IF lf2_scene_flag = 1
		IF TIMERA > lf2_time_check
			REMOVE_BLIP l1f2_blip
			SET_PLAYER_CONTROL player1 OFF
			
			DO_FADE 800 FADE_OUT
			lf2_scene_flag = 2

		ENDIF
	ENDIF

	IF lf2_scene_flag = 2
		IF NOT GET_FADING_STATUS
			lf2_flag = 5
			lf2_scene_flag = 0

			
			// audio for next cut		
			audio_actor[2] = lf2_blue[0]			
			play_timed_audio = 1
			play_timed_audio_for = 9
		 
			audio_time[0] = 2440 
			audio_time[1] = 5629 
			audio_time[2] = 7974 
			audio_time[3] = 10516 
			audio_time[4] = 13463 
			audio_time[5] = 16047 
			audio_time[6] = 19236 
			audio_time[7] = 21747 
			audio_time[8] = 24483

			audio_to_play[0] = 1
			audio_to_play[1] = 2
			audio_to_play[2] = 3
			audio_to_play[3] = 4
			audio_to_play[4] = 5
			audio_to_play[5] = 6
			audio_to_play[6] = 7
			audio_to_play[7] = 8
			audio_to_play[8] = 9

		ENDIF
	ENDIF







RETURN








// *****************************************************************************************
// *********** Sweet is injured and falls to floor - more Flats turn up. *******************
// ******** Huge vortex opens up and spews forth beasts from another dimension *************
// *****************************************************************************************

LF2_cutscene_3:

	//do some AI stuff
	GOSUB lf2_gang_AI

	IF lf2_scene_flag > 2
		IF NOT GET_FADING_STATUS
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			    GOSUB make_cut_wave_red
                lf2_scene_flag = 10
			    lf2_time_check = 0
			    audio_flag = 5		
			ENDIF
		ENDIF
	ENDIF
	

	IF lf2_scene_flag = 0
		lf2_scene_flag = 1
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		
		SET_FIXED_CAMERA_POSITION 1646.7035 -1046.7438 25.6308 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1646.6207 -1047.7106 25.3892 JUMP_CUT

		REQUEST_ANIMATION SWEET

		SET_NEAR_CLIP 0.1

		// set car and ped positions for any guys left alive.
		IF NOT IS_CAR_DEAD l1f2_grovecar[0]
			IF LOCATE_CAR_3D l1f2_grovecar[0] 1647.2491 -1053.4819 23.8804 10.0 10.0 10.0 FALSE
				SET_CAR_COORDINATES l1f2_grovecar[0] 1649.5554 -1055.7670 22.8984
				SET_CAR_HEADING l1f2_grovecar[0] 329.5791 			
				FREEZE_CAR_POSITION l1f2_grovecar[0] TRUE
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD l1f2_grovecar[1]
			IF LOCATE_CAR_3D l1f2_grovecar[1] 1647.2491 -1053.4819 23.8804 10.0 10.0 10.0 FALSE
				SET_CAR_COORDINATES l1f2_grovecar[1] 1644.4198 -1057.8428 22.9062
				SET_CAR_HEADING l1f2_grovecar[1] 227.8 			
				FREEZE_CAR_POSITION l1f2_grovecar[1] TRUE
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD l1f2_grovecar[2]
			IF LOCATE_CAR_3D l1f2_grovecar[2] 1647.2491 -1053.4819 23.8804 10.0 10.0 10.0 FALSE
				SET_CAR_COORDINATES l1f2_grovecar[2] 1643.8448 -1051.7357 22.9219
				SET_CAR_HEADING l1f2_grovecar[2] 149.9008
				FREEZE_CAR_POSITION l1f2_grovecar[2] TRUE				 			
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD l1f2_flatcar[0]
			IF LOCATE_CAR_3D l1f2_flatcar[0] 1647.2491 -1053.4819 23.8804 10.0 10.0 10.0 FALSE
				SET_CAR_COORDINATES l1f2_flatcar[0] 1640.6031 -1071.5366 22.9141
				SET_CAR_HEADING l1f2_flatcar[0] 61.7966 			
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD l1f2_flatcar[1]
			IF LOCATE_CAR_3D l1f2_flatcar[1] 1647.2491 -1053.4819 23.8804 10.0 10.0 10.0 FALSE
				SET_CAR_COORDINATES l1f2_flatcar[1] 1660.9432 -1059.2008 22.9141
				SET_CAR_HEADING l1f2_flatcar[1] 148.6633 			
			ENDIF
		ENDIF



 
  		IF NOT IS_CHAR_DEAD lf2_blue[0]
	  		SET_CHAR_DECISION_MAKER lf2_blue[0] lf2_empty_dec
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_blue[1]
			SET_CHAR_DECISION_MAKER lf2_blue[1] lf2_dec
			SET_CHAR_COORDINATES lf2_blue[1] 1646.2834 -1057.2091 22.8984
			SET_CHAR_HEADING lf2_blue[1] 147.1870
			SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[1] FALSE
			SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[1] TRUE
			TASK_TOGGLE_DUCK lf2_blue[1] TRUE  
		ENDIF
																						
		IF NOT IS_CHAR_DEAD lf2_blue[2]
			SET_CHAR_DECISION_MAKER lf2_blue[2] lf2_dec
			SET_CHAR_COORDINATES lf2_blue[2] 1644.1022 -1055.3192 22.9062
			SET_CHAR_HEADING lf2_blue[2] 117.4901 
			SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[2] FALSE
			SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[2] TRUE
			TASK_TOGGLE_DUCK lf2_blue[2] TRUE
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_blue[3]
			SET_CHAR_DECISION_MAKER lf2_blue[3] lf2_dec
			SET_CHAR_COORDINATES lf2_blue[3] 1646.2288 -1051.1819 23.9257
			SET_CHAR_HEADING lf2_blue[3] 90.0187
			SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[3] FALSE
			SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[3] TRUE
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_red[0]
			SET_CHAR_COORDINATES lf2_red[0] 1622.2764 -1044.6786 22.8984
			TASK_TURN_CHAR_TO_FACE_CHAR lf2_red[0] scplayer
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_red[1]
			SET_CHAR_COORDINATES lf2_red[1] 1631.5227 -1060.4169 23.0110
			TASK_TURN_CHAR_TO_FACE_CHAR lf2_red[1] scplayer 
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_red[2]
			SET_CHAR_COORDINATES lf2_red[2] 1638.7595 -1072.3597 22.8984
			TASK_TURN_CHAR_TO_FACE_CHAR lf2_red[2] scplayer 
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_red[3]
			SET_CHAR_COORDINATES lf2_red[3] 1649.3485 -1077.8683 22.9062
			TASK_TURN_CHAR_TO_FACE_CHAR lf2_red[3] scplayer 
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_red[4]
			SET_CHAR_COORDINATES lf2_red[4] 1663.6187 -1058.4191 22.8984
			TASK_TURN_CHAR_TO_FACE_CHAR lf2_red[4] scplayer 
		ENDIF


	ENDIF

	IF lf2_scene_flag = 1
		IF HAS_ANIMATION_LOADED SWEET
			DO_FADE 1000 FADE_IN
			lf2_scene_flag = 2		
		ENDIF
	ENDIF

   	IF lf2_scene_flag = 2

//		play_audio = 1
//		play_audio_for = 8
		
//		SET_CHAR_COORDINATES scplayer 1646.6677 -1052.2091 22.9641
//		SET_CHAR_HEADING scplayer 180.0

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1648.034 -1053.956 22.9
		ELSE
			SET_CHAR_COORDINATES scplayer 1648.034 -1053.956 22.9	
		ENDIF
		SET_CHAR_HEADING scplayer 150.0
		SET_CHAR_COLLISION scplayer FALSE
		GET_CURRENT_CHAR_WEAPON scplayer lf2_players_weapon
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED																   


										 		  

  		IF NOT IS_CHAR_DEAD lf2_blue[0]
			SET_CHAR_COORDINATES lf2_blue[0] 1647.71 -1054.517 22.9	
			SET_CHAR_HEADING lf2_blue[0] -30.0
			REMOVE_ALL_CHAR_WEAPONS lf2_blue[0]
			SET_CURRENT_CHAR_WEAPON lf2_blue[0] WEAPONTYPE_UNARMED
			SET_CHAR_COLLISION lf2_blue[0] FALSE
//			1648.591 -1054.125 23.862
				CLEAR_CHAR_TASKS_IMMEDIATELY lf2_blue[0]
				TASK_PLAY_ANIM_NON_INTERRUPTABLE lf2_blue[0] LaFin_Sweet SWEET 4.0 FALSE 0 0 1 -2
		ENDIF
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer LaFin_Player SWEET 4.0 FALSE 0 0 1 -2		



//		SET_FIXED_CAMERA_POSITION 1646.3777 -1050.2433 27.0958 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 1646.3544 -1050.9213 26.3611 JUMP_CUT

		SET_FIXED_CAMERA_POSITION 1647.5774 -1048.1041 25.4666 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1647.2814 -1048.9984 25.1312 JUMP_CUT
  	   
		lf2_time_check = TIMERA + 2500

		///do stuff
		lf2_scene_flag = 4
	ENDIF

	IF lf2_scene_flag = 4
		IF TIMERA > lf2_time_check

		IF NOT IS_CHAR_DEAD lf2_blue[0]
			SET_CHAR_BLEEDING lf2_blue[0] TRUE
		ENDIF
		SET_FIXED_CAMERA_POSITION 1649.1997 -1053.5917 23.6475 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1648.2789 -1053.9700 23.7426 JUMP_CUT

		lf2_time_check = TIMERA + 3700
		lf2_scene_flag = 5

		ENDIF
	ENDIF

	IF lf2_scene_flag = 5
		IF TIMERA > lf2_time_check

//		SET_FIXED_CAMERA_POSITION 1647.1965 -1054.5004 23.4234 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 1648.1252 -1054.2666 23.7111 JUMP_CUT

		lf2_time_check = TIMERA + 7800
		lf2_scene_flag = 6

		ENDIF
	ENDIF

	IF lf2_scene_flag = 6
		IF TIMERA > lf2_time_check

//		SET_FIXED_CAMERA_POSITION 1647.8448 -1053.5388 23.8781 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 1647.8250 -1054.4968 23.5924 JUMP_CUT

		lf2_time_check = TIMERA + 5000
		lf2_scene_flag = 7

		ENDIF
	ENDIF

	IF lf2_scene_flag = 7
		IF TIMERA > lf2_time_check

//		SET_FIXED_CAMERA_POSITION 1647.0857 -1054.8744 23.2338 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 1647.9115 -1054.3284 23.3744 JUMP_CUT

		lf2_time_check = TIMERA + 2000
		lf2_scene_flag = 8

							 
		ENDIF			   
	ENDIF

	IF lf2_scene_flag = 8
		//make more reds - remove some to make space

		GOSUB make_cut_wave_red
	ENDIF


	IF lf2_scene_flag = 9
		IF TIMERA > lf2_time_check

		SET_FIXED_CAMERA_POSITION 1651.9958 -1051.2218 26.0610 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1651.0891 -1051.5344 25.7780 JUMP_CUT

		SET_CURRENT_CHAR_WEAPON scplayer lf2_players_weapon 
		SET_CHAR_HEADING scplayer 90.0

		lf2_time_check = TIMERA + 6500
		lf2_scene_flag = 10

							 
		ENDIF			   
	ENDIF


	IF lf2_scene_flag = 10
		IF TIMERA > lf2_time_check
			SET_CURRENT_CHAR_WEAPON scplayer lf2_players_weapon
			SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			
			SET_PLAYER_CONTROL player1 ON

			SET_CHAR_COLLISION scplayer TRUE

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_ONSCREEN_COUNTER l1f2_sweet_health

			IF NOT IS_CHAR_DEAD lf2_blue[0]
				TASK_PLAY_ANIM_NON_INTERRUPTABLE lf2_blue[0] Sweet_injuredloop SWEET 1000.0 TRUE 0 0 0 -2
				SET_CHAR_COLLISION lf2_blue[0] TRUE
				FREEZE_CHAR_POSITION lf2_blue[0] TRUE
			ENDIF

			IF NOT IS_CAR_DEAD l1f2_grovecar[0]
				FREEZE_CAR_POSITION l1f2_grovecar[0] FALSE
			ENDIF
			IF NOT IS_CAR_DEAD l1f2_grovecar[1]
				FREEZE_CAR_POSITION l1f2_grovecar[1] FALSE
			ENDIF
			IF NOT IS_CAR_DEAD l1f2_grovecar[2]
				FREEZE_CAR_POSITION l1f2_grovecar[2] FALSE
			ENDIF
			lf2_flag = 6
			lf2_scene_flag = 0
			CLEAR_PRINTS
			PRINT LA1_31 5000 1
		ENDIF
	ENDIF

	


	   
RETURN




// *****************************************************************************************
// ********** DESCRIPTION ***********
// *****************************************************************************************

LF2_scene_3:

	//if shootout scene not created then check player within 150m and create						
	GOSUB lf2_shootout_creation

	//do some AI stuff
	GOSUB lf2_gang_AI

	IF lf2_scene_flag = 0
		IF lf2_dead_reds = 4

			REQUEST_MODEL PONY
			lf2_scene_flag = 1
		ENDIF
	ENDIF 

	IF lf2_scene_flag = 1
		IF HAS_MODEL_LOADED PONY

			CREATE_CAR PONY 1553.6967 -1046.8296 22.7992 l1f2_flatpony[0]

			CLEAR_PRINTS
			PRINT LA1_36 5000 1

			lf2_int1 = 0
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_INSIDE_CAR l1f2_flatpony[0] PEDTYPE_MISSION2 BALLAS1 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_driver = lf2_int1
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE


			SET_CAR_HEADING l1f2_flatpony[0] 270.0
			OPEN_SEQUENCE_TASK lf2_seq
				TASK_CAR_DRIVE_TO_COORD -1 l1f2_flatpony[0] 1614.7554 -1050.4410 22.9141 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
				TASK_CAR_DRIVE_TO_COORD -1 l1f2_flatpony[0] 1630.5336 -1067.9246 22.9141 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK lf2_seq
			PERFORM_SEQUENCE_TASK lf2_red[lf2_int1] lf2_seq
			CLEAR_SEQUENCE_TASK lf2_seq

			SET_CAR_FORWARD_SPEED l1f2_flatpony[0] 20.0



			lf2_int1 ++
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_AS_PASSENGER l1f2_flatpony[0] PEDTYPE_MISSION2 BALLAS1 0 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_pass1 = lf2_int1
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE


			lf2_int1 ++
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_AS_PASSENGER l1f2_flatpony[0] PEDTYPE_MISSION2 BALLAS1 1 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_pass2 = lf2_int1
			GOSUB lf2_make_red





			lf2_scene_flag = 2
																						 
			lf2_dead_reds -= 3															
		ENDIF																			
	ENDIF

	IF lf2_scene_flag = 2


		//if van is shot, peds get out

		IF l1f2_car_Action[0] = 0
			IF NOT IS_CAR_DEAD l1f2_flatpony[0]
				GET_CAR_HEALTH l1f2_flatpony[0] l1f2_health
				IF l1f2_health < 980
					IF HAS_CAR_BEEN_DAMAGED_BY_CHAR l1f2_flatpony[0] scplayer
						l1f2_car_Action[0] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF l1f2_car_Action[0] = 1
			IF TIMERA > l1f2_car_action_time[0]
				IF NOT IS_CAR_DEAD l1f2_flatpony[0]
				AND NOT IS_CHAR_DEAD lf2_red[l1f2_driver]

					GET_CAR_COORDINATES l1f2_flatpony[0] lx ly lz

					GET_CAR_HEADING l1f2_flatpony[0] lf1

					lf2 = lf1 - 20.0

					lf1 -= 90.0

					
					COS lf1 l1f2_cosheading
					SIN	lf1 l1f2_sinheading

					l1f2_cosheading *= 20.0
					l1f2_sinheading *= 20.0

						lx -= l1f2_sinheading 
						ly += l1f2_cosheading

					TASK_CAR_DRIVE_TO_COORD lf2_red[l1f2_driver] l1f2_flatpony[0] lx ly lz 30.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
	//				SET_CAR_TEMP_ACTION l1f2_flatpony[0] TEMPACT_SWERVELEFT 300
					l1f2_car_Action[0] = 2				
				ENDIF
			ENDIF
		ENDIF


		IF l1f2_car_Action[0] = 2
			IF NOT IS_CAR_DEAD l1f2_flatpony[0] 
				GET_CAR_HEADING l1f2_flatpony[0] lf1
				IF lf1 < lf2
					l1f2_car_Action[0] = 3
					l1f2_car_action_time[0] = TIMERA + 800
				ENDIF			
			ENDIF
		ENDIF

		IF l1f2_car_Action[0] = 3
			IF NOT IS_CAR_DEAD l1f2_flatpony[0]
				ADD_TO_CAR_ROTATION_VELOCITY l1f2_flatpony[0] 0.0 -0.35 0.2
			ENDIF
			IF TIMERA > l1f2_car_action_time[0]
				l1f2_car_Action[0] = 4
				l1f2_car_action_time[0] = TIMERA + 1000
			ENDIF
		ENDIF

		IF l1f2_car_Action[0] = 4
			IF TIMERA > l1f2_car_action_time[0]
				IF NOT IS_CHAR_DEAD lf2_red[l1f2_driver]
					CLEAR_CHAR_TASKS lf2_red[l1f2_driver]
					TASK_LEAVE_ANY_CAR lf2_red[l1f2_driver]
					l1f2_car_Action[0] = 5
				ENDIF
			ENDIF
		ENDIF


		// if van stops, peds get out

		IF lf2_leave_pony = 0
			IF NOT IS_CAR_DEAD l1f2_flatpony[0]
				GET_CAR_SPEED l1f2_flatpony[0] lf1
				IF lf1 < 0.5
					lf2_leave_pony = 1					
				ENDIF
			ENDIF
		ENDIF

 		IF lf2_leave_pony = 1
			IF NOT IS_CAR_DEAD l1f2_flatpony[0]
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_driver]
//					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_driver] 1630.8472 -1059.2250 24.0591 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] FALSE					 
					TASK_LEAVE_ANY_CAR lf2_red[l1f2_driver]
				   	lf2_red_script_event[l1f2_driver] = 0
				ENDIF
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_pass1]
//					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_pass1] 1634.4745 -1064.4102 24.1564 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] FALSE
					TASK_LEAVE_ANY_CAR lf2_red[l1f2_pass1]
					lf2_red_script_event[l1f2_pass1] = 0
				ENDIF
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_pass2]
//					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_pass2] 1637.7507 -1072.4222 24.0349 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] FALSE
					TASK_LEAVE_ANY_CAR lf2_red[l1f2_pass2]
					lf2_red_script_event[l1f2_pass2] = 0
				ENDIF
				lf2_scene_flag = 3
			ENDIF
		ENDIF
		IF IS_CAR_DEAD l1f2_flatpony[0]
			lf2_scene_flag = 3
		ENDIF
	ENDIF

	IF lf2_scene_flag = 3
		IF lf2_red_script_event[l1f2_driver] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_driver]			
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_driver] TASK_GO_STRAIGHT_TO_COORD lv1
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_driver] = 0					
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass1] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_pass1]	
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_pass1] TASK_GO_STRAIGHT_TO_COORD lv1
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_pass1] = 0				
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass2] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_pass2]
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_pass2] TASK_GO_STRAIGHT_TO_COORD lv1
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_pass2] = 0					
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass1] = 0
		AND lf2_red_script_event[l1f2_pass2] = 0
		AND lf2_red_script_event[l1f2_driver] = 0
			lf2_scene_flag = 4
		ENDIF
	ENDIF
	 IF lf2_scene_flag = 4
		IF lf2_dead_reds >= 4

			CREATE_CAR PONY 1525.3656 -1022.2670 22.9105 l1f2_flatpony[1]

			CLEAR_PRINTS
			PRINT LA1_37 5000 1

			lf2_int1 = 0
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_INSIDE_CAR l1f2_flatpony[1] PEDTYPE_MISSION2 BALLAS1 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_driver = lf2_int1
			GOSUB lf2_make_red


			SET_CAR_HEADING l1f2_flatpony[1] 270.0
			OPEN_SEQUENCE_TASK lf2_seq
				TASK_CAR_DRIVE_TO_COORD -1 l1f2_flatpony[1] 1637.6661 -1021.6440 22.9141 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
				TASK_CAR_DRIVE_TO_COORD -1 l1f2_flatpony[1] 1651.1274 -1028.5234 22.9141 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK lf2_seq
			PERFORM_SEQUENCE_TASK lf2_red[lf2_int1] lf2_seq
			CLEAR_SEQUENCE_TASK lf2_seq

			SET_CAR_FORWARD_SPEED l1f2_flatpony[1] 20.0



			lf2_int1 ++
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_AS_PASSENGER l1f2_flatpony[1] PEDTYPE_MISSION2 BALLAS1 0 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_pass1 = lf2_int1
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE


			lf2_int1 ++
			WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_int1 ++
			ENDWHILE

			CREATE_CHAR_AS_PASSENGER l1f2_flatpony[1] PEDTYPE_MISSION2 BALLAS1 1 lf2_red[lf2_int1]
			lf2_char_id = lf2_red[lf2_int1]
			l1f2_pass2 = lf2_int1
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE

			lf2_leave_pony = 0




			lf2_scene_flag = 5
																						 
			lf2_dead_reds -= 3															
		ENDIF																			
	ENDIF																			   
																					   
	IF lf2_scene_flag = 5															   
		IF lf2_leave_pony = 0
			IF NOT IS_CAR_DEAD l1f2_flatpony[1]
				GET_CAR_SPEED l1f2_flatpony[1] lf1
				IF lf1 < 0.5
					lf2_leave_pony = 1					
				ENDIF
			ENDIF
		ENDIF
		IF lf2_leave_pony = 1
			IF NOT IS_CAR_DEAD l1f2_flatpony[1]
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_driver]
					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_driver] 1654.9678 -1041.3721 22.8984 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] FALSE					 
				   	lf2_red_script_event[l1f2_driver] = 1
				ENDIF
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_pass1]
					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_pass1] 1650.4686 -1041.5168 22.8984 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] FALSE
					lf2_red_script_event[l1f2_pass1] = 1
				ENDIF
				IF NOT IS_CHAR_DEAD	lf2_red[l1f2_pass2]
					TASK_GO_STRAIGHT_TO_COORD lf2_red[l1f2_pass2] 1640.8055 -1041.5552 22.8984 PEDMOVE_RUN -2
					SET_CHAR_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] FALSE
					lf2_red_script_event[l1f2_pass2] = 1
				ENDIF
				lf2_scene_flag = 6
			ENDIF
		ENDIF
		IF IS_CAR_DEAD l1f2_flatpony[1]
			lf2_scene_flag = 6
		ENDIF
	ENDIF

	IF lf2_scene_flag = 6
		IF lf2_red_script_event[l1f2_driver] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_driver]			
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_driver] TASK_GO_STRAIGHT_TO_COORD lv1
				
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_driver] = 0					
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_driver] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass1] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_pass1]	
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_pass1] TASK_GO_STRAIGHT_TO_COORD lv1
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_pass1] = 0				
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass1] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass2] = 1
			IF NOT IS_CHAR_DEAD lf2_red[l1f2_pass2]
				GET_SCRIPT_TASK_STATUS lf2_red[l1f2_pass2] TASK_GO_STRAIGHT_TO_COORD lv1
				IF lv1 = FINISHED_TASK
					lf2_red_script_event[l1f2_pass2] = 0					
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[l1f2_pass2] TRUE
				ENDIF
			ENDIF
		ENDIF
		IF lf2_red_script_event[l1f2_pass1] = 0
		AND lf2_red_script_event[l1f2_pass2] = 0
		AND lf2_red_script_event[l1f2_driver] = 0
			lf2_scene_flag = 8
		ENDIF
	ENDIF

	IF lf2_scene_flag = 8
		IF lf2_dead_reds >= 3
			CLEAR_PRINTS
			PRINT LA1_38 5000 1
			GOSUB create_driveby_routes
			lf2_dead_reds -= 3
			lf2_scene_flag = 9
			lf2_red_script_event[l1f2_pass1] = 1
			lf2_red_script_event[l1f2_pass2] = 1
			lf2_red_script_event[l1f2_driver] = 1
		ENDIF
	ENDIF

	IF lf2_scene_flag = 9
		GOSUB run_driveby_cars
		lf2_int1 = 0
		lf2_dead_reds = 0
		WHILE lf2_int1 < 6
			IF IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_dead_reds ++
			ENDIF						    
			lf2_int1 ++
		ENDWHILE
		IF lf2_dead_reds >= 6
			lf2_scene_flag = 10
			lf2_time_check = TIMERA + 1500
		ENDIF
	ENDIF

	IF lf2_scene_flag = 10
		IF TIMERA > lf2_time_check
			lf2_scene_flag = 0
			lf2_flag = 7
			CLEAR_HELP
		ENDIF
	ENDIF




	

RETURN







// *****************************************************************************************
// **************************** Cops turn up - CJ gets busted. *****************************
// *****************************************************************************************

LF2_cutscene_4:

LVAR_INT copcar_bust_cut[3] cop_bust_cut[3] 
LVAR_INT cop_given_task[3]



	lf2_i = 0
	WHILE lf2_i < 3
		IF cop_given_task[lf2_i] = 1
			IF NOT IS_CAR_DEAD copcar_bust_cut[lf2_i] 
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar_bust_cut[lf2_i]
					IF NOT IS_CHAR_DEAD cop_bust_cut[lf2_i]
//						TASK_PLAY_ANIM_NON_INTERRUPTABLE cop_bust_cut[lf2_i] COP_getoutcar_LHS POLICE 1000.0 FALSE 0 0 0 -2
LVAR_INT lf2_sequence
						OPEN_SEQUENCE_TASK lf2_sequence
							TASK_LEAVE_ANY_CAR -1
							TASK_AIM_GUN_AT_COORD -1 1646.3318 -1053.7207 23.385 10000
						CLOSE_SEQUENCE_TASK lf2_sequence
						PERFORM_SEQUENCE_TASK cop_bust_cut[lf2_i] lf2_sequence
						CLEAR_SEQUENCE_TASK lf2_sequence
						cop_given_task[lf2_i] = 2
						GET_SCRIPT_TASK_STATUS scplayer TASK_HANDS_UP lf2_task_status
						IF lf2_task_Status = FINISHED_TASK
							TASK_HANDS_UP scplayer 15000
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		lf2_i ++
	ENDWHILE

	IF lf2_scene_flag = 0
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION2
//			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION3 PEDTYPE_MISSION1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION3 PEDTYPE_PLAYER1
		DO_FADE 500 FADE_OUT
		REQUEST_CAR_RECORDING 162
		REQUEST_CAR_RECORDING 163
		REQUEST_CAR_RECORDING 650
		REQUEST_MODEL COPCARLA
		REQUEST_MODEL LAPD1 
		REQUEST_MODEL COLT45
		REQUEST_ANIMATION POLICE
		lf2_scene_flag = 1
		lf2_time_check = TIMERA + 2000
		LF2_sweet_alive = 0
	ENDIF

	IF lf2_scene_flag = 1
		IF NOT GET_FADING_STATUS			
			IF HAS_CAR_RECORDING_BEEN_LOADED 162
			AND HAS_CAR_RECORDING_BEEN_LOADED 163
			AND HAS_CAR_RECORDING_BEEN_LOADED 650
				IF HAS_MODEL_LOADED COPCARLA
				AND HAS_MODEL_LOADED LAPD1
				AND HAS_MODEL_LOADED COLT45
				AND HAS_ANIMATION_LOADED POLICE
					LOAD_SCENE_IN_DIRECTION	1584.2905 -1143.8766 23.8226 180.0
//					REQUEST_MODEL paypark_lan02
//					REQUEST_MODEL bevhiltreepv
//					LOAD_SCENE 1599.7919 -1140.1249 24.6352
					lf2_scene_flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF lf2_scene_flag = 2
		IF TIMERA > lf2_time_check
			lf2_scene_flag = 3
		ENDIF
	ENDIF

	IF lf2_scene_flag = 3
		CREATE_CAR COPCARLA 0.0 0.0 0.0 copcar_bust_cut[0]
		CREATE_CAR COPCARLA 0.0 0.0 0.0 copcar_bust_cut[1]
		CREATE_CAR COPCARLA 0.0 0.0 0.0 copcar_bust_cut[2]
		CREATE_CHAR_INSIDE_CAR copcar_bust_cut[0] PEDTYPE_CIVMALE LAPD1 cop_bust_cut[0]
		CREATE_CHAR_INSIDE_CAR copcar_bust_cut[1] PEDTYPE_CIVMALE LAPD1 cop_bust_cut[1] 
		CREATE_CHAR_INSIDE_CAR copcar_bust_cut[2] PEDTYPE_CIVMALE LAPD1 cop_bust_cut[2]
		GIVE_WEAPON_TO_CHAR cop_bust_cut[0] WEAPONTYPE_PISTOL 99999
		GIVE_WEAPON_TO_CHAR cop_bust_cut[1] WEAPONTYPE_PISTOL 99999
		GIVE_WEAPON_TO_CHAR cop_bust_cut[2] WEAPONTYPE_PISTOL 99999
		SWITCH_CAR_SIREN copcar_bust_cut[0] ON
		SWITCH_CAR_SIREN copcar_bust_cut[1] ON
		SWITCH_CAR_SIREN copcar_bust_cut[2] ON
//		SET_FIXED_CAMERA_POSITION 1595.4587 -1165.6266 23.1525 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT 1595.4341 -1164.6302 23.2345 JUMP_CUT
		CAMERA_RESET_NEW_SCRIPTABLES
		CAMERA_PERSIST_TRACK TRUE                   
		CAMERA_PERSIST_POS TRUE
		CAMERA_SET_VECTOR_MOVE  1584.2905 -1143.8766 23.8226 1584.2905 -1143.8766 23.8226 3000 TRUE		  
		CAMERA_SET_VECTOR_TRACK 1583.9041 -1144.7986 23.8020 1585.2281 -1144.2233 23.8020 3000 TRUE
		SWITCH_WIDESCREEN ON
		
		SET_PLAYER_CONTROL player1 OFF
		SET_CHAR_COORDINATES scplayer 1647.2217 -1053.3633 22.9815
		START_PLAYBACK_RECORDED_CAR copcar_bust_cut[0] 162 

		LVAR_INT lf2_task_status
		lf2_scene_flag = 4
		lf2_time_check = TIMERA + 300											 
	ENDIF																		  

	IF lf2_scene_flag = 4
		IF TIMERA > lf2_time_check
			DO_FADE 600 FADE_IN
			SET_CHAR_AREA_VISIBLE scplayer 0
			IF NOT IS_CAR_DEAD copcar_bust_cut[1]
			AND NOT IS_CAR_DEAD copcar_bust_cut[2]
				IF HAS_CAR_RECORDING_BEEN_LOADED 162
				AND HAS_CAR_RECORDING_BEEN_LOADED 163
				AND HAS_CAR_RECORDING_BEEN_LOADED 650
					START_PLAYBACK_RECORDED_CAR copcar_bust_cut[1] 163
					START_PLAYBACK_RECORDED_CAR copcar_bust_cut[2] 650
					cop_given_task[0] = 1
					cop_given_task[1] = 1
					cop_given_task[2] = 1
				ELSE
					REQUEST_CAR_RECORDING 162
					REQUEST_CAR_RECORDING 163
					REQUEST_CAR_RECORDING 650
				ENDIF					
			ENDIF
			lf2_scene_flag = 5
			lf2_time_check = TIMERA + 2700
		ENDIF
	ENDIF

	IF lf2_scene_flag = 5
		IF TIMERA > lf2_time_check
			LVAR_INT flee_red[4]
			CREATE_CHAR PEDTYPE_MISSION3 BALLAS1 1648.9066 -1063.1697 22.99	flee_red[0]
			CREATE_CHAR PEDTYPE_MISSION3 BALLAS1 1639.7555 -1067.9937 22.9478 flee_red[1]
			CREATE_CHAR PEDTYPE_MISSION3 BALLAS1 1632.4451 -1058.0782 23.0987 flee_red[2]
			CREATE_CHAR PEDTYPE_MISSION3 BALLAS1 1640.2998 -1069.2991 22.9448 flee_red[3]
			TASK_FLEE_POINT flee_red[0] 1646.3318 -1053.7207 23.385 100.0 15000
			TASK_FLEE_POINT flee_red[1] 1646.3318 -1053.7207 23.385 100.0 15000
			TASK_FLEE_POINT flee_red[2] 1646.3318 -1053.7207 23.385 100.0 15000
			TASK_FLEE_POINT flee_red[3] 1646.3318 -1053.7207 23.385 100.0 15000
			IF NOT IS_CHAR_DEAD lf2_blue[1]			
				CLEAR_CHAR_TASKS_IMMEDIATELY lf2_blue[1]
				SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[1] FALSE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[1] FALSE
				SET_CHAR_COORDINATES lf2_blue[1] 1649.4648 -1059.3076 22.8984
				TASK_FLEE_POINT lf2_blue[1] 1646.3318 -1053.7207 23.385 100.0 15000
			ENDIF
			IF NOT IS_CHAR_DEAD lf2_blue[2]
				CLEAR_CHAR_TASKS_IMMEDIATELY lf2_blue[2]
				SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[2] FALSE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[2] FALSE
				SET_CHAR_COORDINATES lf2_blue[2] 1642.0807 -1057.8817 22.9062
				TASK_FLEE_POINT lf2_blue[2] 1646.3318 -1053.7207 23.385 100.0 15000
			ENDIF
			IF NOT IS_CHAR_DEAD lf2_blue[3]
				CLEAR_CHAR_TASKS_IMMEDIATELY lf2_blue[3]
				SET_CHAR_STAY_IN_SAME_PLACE lf2_blue[3] FALSE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[3] FALSE
				SET_CHAR_COORDINATES lf2_blue[3] 1640.8405 -1051.6309 22.9062
				TASK_FLEE_POINT lf2_blue[3] 1646.3318 -1053.7207 23.385 100.0 15000
			ENDIF

				
			lf2_scene_flag = 6
			lf2_time_check = TIMERA + 100
		ENDIF
	ENDIF




	IF lf2_scene_flag = 6
		IF TIMERA > lf2_time_check
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_FIXED_CAMERA_POSITION 1648.6445 -1049.3569 26.0388 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1648.5333 -1050.3365 25.8716 JUMP_CUT
//			LOAD_SCENE_IN_DIRECTION	1648.6445 -1049.3569 26.0388 180.0		
			lf2_scene_flag = 8
			lf2_time_check = TIMERA + 6500
		ENDIF
	ENDIF



	IF lf2_scene_flag = 8
		IF TIMERA > lf2_time_check
			CAMERA_RESET_NEW_SCRIPTABLES
			CAMERA_PERSIST_TRACK TRUE                   
			CAMERA_PERSIST_POS TRUE                       
			CAMERA_SET_VECTOR_MOVE  1648.6445 -1049.3569 26.0388 1662.2574 -1029.2064 46.3953 7500 TRUE		  
			CAMERA_SET_VECTOR_TRACK 1648.5333 -1050.3365 25.8716 1661.8802 -1029.8965 45.7777 7500 TRUE		  
			lf2_time_check = TIMERA + 6000
			lf2_scene_flag = 9
		ENDIF
	ENDIF

	IF lf2_scene_flag = 9										   
		IF TIMERA > lf2_time_check
			DO_FADE 1500 FADE_OUT

//			lf2_flag = 8
			lf2_scene_flag = 10
		ENDIF
	ENDIF

	IF lf2_scene_flag = 10
		IF NOT GET_FADING_STATUS
			lf2_flag = 8
			
			CAMERA_RESET_NEW_SCRIPTABLES
		ENDIF
	ENDIF

			




RETURN






make_cut_wave_red:


		DELETE_CHAR lf2_red[0]
		CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1613.2314 -1053.6843 22.8984 lf2_red[0]
		SET_CHAR_HEADING lf2_red[0] 271.7240
		lf2_char_id = lf2_red[0]
		lf2_int1 = 0
		GOSUB lf2_make_red
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[0] TRUE 
		TASK_TOGGLE_DUCK lf2_red[0] TRUE
		TASK_GO_STRAIGHT_TO_COORD lf2_red[0] 1622.5411 -1045.0079 22.8984 PEDMOVE_RUN -2



		DELETE_CHAR lf2_red[1]
		CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1612.7073 -1054.2001 22.8984 lf2_red[1]
		SET_CHAR_HEADING lf2_red[1] 285.7294
		lf2_char_id = lf2_red[1]
		lf2_int1 = 1
		GOSUB lf2_make_red
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[1] TRUE
		TASK_TOGGLE_DUCK lf2_red[1] TRUE
		TASK_GO_STRAIGHT_TO_COORD lf2_red[1] 1631.4712 -1059.3792 22.9623 PEDMOVE_RUN -2


		DELETE_CHAR lf2_red[2]
		CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1617.2190 -1057.9731 22.8984 lf2_red[2]
		SET_CHAR_HEADING lf2_red[2] 265.7294
		lf2_char_id = lf2_red[2]
		lf2_int1 = 2
		GOSUB lf2_make_red
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[2] TRUE
		TASK_TOGGLE_DUCK lf2_red[2] TRUE
		TASK_GO_STRAIGHT_TO_COORD lf2_red[2] 1633.0354 -1064.7161 22.9162 PEDMOVE_RUN -2



		IF IS_CHAR_DEAD lf2_red[3]
			CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1670.7072 -1082.1794 22.9062 lf2_red[3]
			SET_CHAR_HEADING lf2_red[3] 25.7294
			lf2_char_id = lf2_red[3]
			lf2_int1 = 3
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[3] TRUE
			TASK_TOGGLE_DUCK lf2_red[3] TRUE
			TASK_GO_STRAIGHT_TO_COORD lf2_red[3] 1659.9028 -1067.0936 23.0077 PEDMOVE_RUN -2			
		ENDIF

		IF IS_CHAR_DEAD lf2_red[4]
			CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1665.2666 -1090.4669 22.9062 lf2_red[4]
			SET_CHAR_HEADING lf2_red[4] 25.7294
			lf2_char_id = lf2_red[4]
			lf2_int1 = 4
			GOSUB lf2_make_red
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[4] TRUE
			TASK_TOGGLE_DUCK lf2_red[4] TRUE
			TASK_GO_STRAIGHT_TO_COORD lf2_red[4] 1649.4990 -1077.7948 22.9062 PEDMOVE_RUN -2			
		ENDIF

		lf2_scene_flag = 9

		lf2_int1 = 0
		lf2_dead_reds = 0
		WHILE lf2_int1 < 6
			IF IS_CHAR_DEAD lf2_red[lf2_int1]
				lf2_dead_reds ++
			ENDIF						    
			lf2_int1 ++
		ENDWHILE

RETURN
















// *****************************************************************************************
// ***** Create driveby car *******
// *****************************************************************************************


create_driveby_routes:

	LVAR_INT l1f2_route_start[10] l1f2_route_end[10] l1f2_next_route_a[10] l1f2_next_route_b[10] 
	LVAR_INT l1f2_random l1f2_next_route l1f2_driveby_reroute
	LVAR_FLOAT l1f2_end_driveby_x l1f2_end_driveby_y driveby_x[23] driveby_y[23]



	driveby_x[0] = 1609.1936
	driveby_x[1] = 1619.7217
	driveby_x[2] = 1633.4944
	driveby_x[3] = 1656.4398
	driveby_x[4] = 1665.5096
	driveby_x[5] = 1667.7563
	driveby_x[6] = 1665.7896
	driveby_x[7] = 1654.9766
	driveby_x[8] = 1651.2059
	driveby_x[9] = 1640.0569
	driveby_x[10] =	1623.2560
	driveby_x[11] =	1662.5665
	driveby_x[12] =	1643.7061			    
	driveby_x[13] =	1627.3936
	driveby_x[14] =	1627.0830
	driveby_x[15] =	1634.3109
	driveby_x[16] =	1650.9629
	driveby_x[17] =	1664.3052			 
	driveby_x[18] =	1670.2731
	driveby_x[19] =	1625.1440
	driveby_x[20] =	1639.7635
	driveby_x[21] =	1657.4904
	driveby_x[22] =	1672.0071

	driveby_y[0] = -1047.4640
	driveby_y[1] = -1036.5244
	driveby_y[2] = -1031.4369
	driveby_y[3] = -1034.0103
	driveby_y[4] = -1043.5925
	driveby_y[5] = -1045.4957
	driveby_y[6] = -1049.2482
	driveby_y[7] = -1058.4379
	driveby_y[8] = -1068.1687
	driveby_y[9] = -1079.3702
	driveby_y[10] =	-1071.9753
	driveby_y[11] =	-1048.2865
	driveby_y[12] =	-1046.5656
	driveby_y[13] =	-1052.3989
	driveby_y[14] =	-1051.4459
	driveby_y[15] =	-1057.1804
	driveby_y[16] =	-1067.0616
	driveby_y[17] =	-1071.1169
	driveby_y[18] =	-1061.6228
	driveby_y[19] =	-1063.7119
	driveby_y[20] =	-1066.0299
	driveby_y[21] =	-1071.2227
	driveby_y[22] =	-1063.6095





//1657.2800 -1054.3433 23.3597 (7)



	l1f2_route_start[0] = 0
	l1f2_route_end[0] = 5

	l1f2_route_start[1] = 6
	l1f2_route_end[1] = 10

	l1f2_route_start[2] = 11
	l1f2_route_end[2] = 13

	l1f2_route_start[3] = 14
	l1f2_route_end[3] = 18

	l1f2_route_start[4] = 19
	l1f2_route_end[4] = 22

	l1f2_route_start[5] = 5
	l1f2_route_end[5] = 0

	l1f2_route_start[6] = 10
	l1f2_route_end[6] = 6

	l1f2_route_start[7] = 13
	l1f2_route_end[7] = 11

	l1f2_route_start[8] = 18
	l1f2_route_end[8] = 14

	l1f2_route_start[9] = 22
	l1f2_route_end[9] = 19

	l1f2_next_route_a[0] = 1
	l1f2_next_route_b[0] = 9

	l1f2_next_route_a[1] = 0
	l1f2_next_route_b[1] = 7

	l1f2_next_route_a[2] = 6
	l1f2_next_route_b[2] = 4

	l1f2_next_route_a[3] = 5
	l1f2_next_route_b[3] = 2

	l1f2_next_route_a[4] = 2
	l1f2_next_route_b[4] = 5

	l1f2_next_route_a[5] = 6
	l1f2_next_route_b[5] = 3

	l1f2_next_route_a[6] = 8
	l1f2_next_route_b[6] = 5

	l1f2_next_route_a[7] = 5
	l1f2_next_route_b[7] = 9

	l1f2_next_route_a[8] = 0
	l1f2_next_route_b[8] = 6

	l1f2_next_route_a[9] = 7
	l1f2_next_route_b[9] = 0

	CREATE_CAR SENTINEL 1582.1310 -1043.6693 22.9141 l1f2_driveby_car[0]
	SET_CAR_HEADING l1f2_driveby_car[0] 270.0
	SET_CAR_HEAVY l1f2_driveby_car[0] TRUE
	SET_CAR_FORWARD_SPEED l1f2_driveby_car[0] 20.0


	lf2_int1 = 0
	WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
		lf2_int1 ++
	ENDWHILE

	CREATE_CHAR_INSIDE_CAR l1f2_driveby_car[0] PEDTYPE_MISSION3 BALLAS1 lf2_red[lf2_int1]
	lf2_char_id = lf2_red[lf2_int1]
	l1f2_driver = lf2_int1
	GOSUB lf2_make_red
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE

	lf2_int1 ++
	WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
		lf2_int1 ++
	ENDWHILE

	CREATE_CHAR_AS_PASSENGER l1f2_driveby_car[0] PEDTYPE_MISSION3 BALLAS1 0 lf2_red[lf2_int1]
	lf2_char_id = lf2_red[lf2_int1]
	l1f2_pass1 = lf2_int1
	GOSUB lf2_make_red
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE


	lf2_int1 ++
	WHILE NOT IS_CHAR_DEAD lf2_red[lf2_int1]
		lf2_int1 ++
	ENDWHILE

	CREATE_CHAR_AS_PASSENGER l1f2_driveby_car[0] PEDTYPE_MISSION3 BALLAS1 1 lf2_red[lf2_int1]
	lf2_char_id = lf2_red[lf2_int1]
	l1f2_pass2 = lf2_int1
	GOSUB lf2_make_red


	SET_CHAR_DECISION_MAKER lf2_red[l1f2_pass1] lf2_driveby_dec
	SET_CHAR_DECISION_MAKER lf2_red[l1f2_pass2] lf2_driveby_dec

	l1f2_next_route = 1
	l1f2_driveby_reroute = 1


RETURN


// *****************************************************************************************
// ***** Run driveby car *******
// *****************************************************************************************


run_driveby_cars:

	IF NOT IS_CAR_DEAD l1f2_driveby_car[0]
	AND NOT IS_CHAR_DEAD lf2_red[l1f2_driver]
		GET_CAR_SPEED l1f2_driveby_car[0] lf1
		IF lf1 < 0.5
			IF lf2_leave_car_check = 0 
				lf2_leave_car_check = 1
				lf2_leave_car_time = TIMERA + 1500
			ENDIF
		ENDIF

		IF lf2_leave_car_check = 1
			IF lf1 < 0.5
				IF TIMERA > lf2_leave_car_time
					l1f2_driveby_reroute = 2
					lf2_leave_car_check = 2
					TASK_EVERYONE_LEAVE_CAR l1f2_driveby_car[0]
					lf2_red_script_event[l1f2_pass1] = 0
					lf2_red_script_event[l1f2_pass2] = 0
					lf2_red_script_event[l1f2_driver] = 0
				ENDIF
			ELSE
				lf2_leave_car_check = 0
			ENDIF			
		ENDIF



		IF l1f2_driveby_reroute = 0
			IF LOCATE_CAR_3D l1f2_driveby_car[0] l1f2_end_driveby_x l1f2_end_driveby_y 22.0 3.0 3.0 4.0 FALSE 
				l1f2_driveby_reroute = 1
			ENDIF
		ENDIF
		IF l1f2_driveby_reroute = 1
			GENERATE_RANDOM_INT_IN_RANGE 0 2 l1f2_random

			IF l1f2_random = 0
				l1f2_next_route = l1f2_next_route_a[l1f2_next_route]
			ELSE
				l1f2_next_route = l1f2_next_route_b[l1f2_next_route]
			ENDIF

			lv1 = l1f2_route_start[l1f2_next_route]

			FLUSH_ROUTE

			LVAR_FLOAT last_x last_y last_z

			GET_CAR_COORDINATES l1f2_driveby_car[0] last_x last_y last_z
			

			IF l1f2_route_end[l1f2_next_route] > l1f2_route_start[l1f2_next_route]
				WHILE lv1 <= l1f2_route_end[l1f2_next_route]
					EXTEND_ROUTE driveby_x[lv1] driveby_y[lv1] 22.0
//					LINE last_x last_y last_z driveby_x[lv1] driveby_y[lv1] last_z
					last_x = driveby_x[lv1]
					last_y = driveby_y[lv1]					
					lv1 ++
				ENDWHILE
			ELSE
				WHILE lv1 >= l1f2_route_end[l1f2_next_route]
					EXTEND_ROUTE driveby_x[lv1] driveby_y[lv1] 22.0
//					LINE last_x last_y last_z driveby_x[lv1] driveby_y[lv1] last_z
					last_x = driveby_x[lv1]
					last_y = driveby_y[lv1]
					lv1 --
				ENDWHILE
			ENDIF

//			imstuckhere = 0
//			VAR_INT imstuckhere
//			VIEW_INTEGER_VARIABLE imstuckhere imstuckhere

			TASK_DRIVE_POINT_ROUTE_ADVANCED lf2_red[l1f2_driver] l1f2_driveby_car[0] 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			l1f2_driveby_reroute = 0

			lv1 = l1f2_route_end[l1f2_next_route]

			l1f2_end_driveby_x = driveby_x[lv1]
			l1f2_end_driveby_y = driveby_y[lv1]
		ENDIF
		IF l1f2_driveby_reroute = 2
			 
		ENDIF
	ENDIF

RETURN





// *****************************************************************************************
// ***** Creating the shootout scene *******
// *****************************************************************************************

lf2_shootout_creation:

LVAR_INT lf2_dec lf2_reddec
LVAR_INT l1f2_flatcar[2] l1f2_grovecar[3]
LVAR_INT lf2_blue[5] lf2_red[6] lf2_char_stop[6]
LVAR_INT lf2_shootout_created

	IF lf2_shootout_created = 0

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1650.6447 -1059.4348 25.4852 250.0 250.0 70.0 FALSE
			lf2_shootout_created = 1

			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2
//			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_MISSION1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_PLAYER1

			
			COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH lf2_dec
			COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH lf2_reddec

//			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_ACQUAINTANCE_PED_HATE

			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_SHOT_FIRED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_SHOT_FIRED_WHIZZED_BY
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_DAMAGE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_ACQUAINTANCE_PED_HATE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_SEEN_PANICKED_PED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_DEAD_PED


			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_SHOT_FIRED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_SHOT_FIRED_WHIZZED_BY
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_DAMAGE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_ACQUAINTANCE_PED_HATE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_GUN_AIMED_AT
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_DEAD_PED
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_reddec EVENT_VEHICLE_DAMAGE_WEAPON


			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_SHOT_FIRED TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 100.0 1 1
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 1 1
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 75.0 0.0 75.0 1 1
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_DAMAGE TASK_SIMPLE_DUCK 0.0 25.0 0.0 25.0 1 1

//			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE lf2_dec EVENT_SHOT_FIRED_WHIZZED_BY TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 100.0 1 1



			REQUEST_MODEL SABRE
			REQUEST_MODEL SENTINEL
			REQUEST_MODEL FAM1
			REQUEST_MODEL BALLAS1
			REQUEST_MODEL MP5LNG
			LOAD_SPECIAL_CHARACTER 1 SWEET

		ENDIF
	ENDIF

	IF lf2_shootout_created = 1
		IF HAS_MODEL_LOADED SABRE
		AND	HAS_MODEL_LOADED FAM1
		AND	HAS_MODEL_LOADED BALLAS1
		AND	HAS_MODEL_LOADED MP5LNG
		AND	HAS_MODEL_LOADED SENTINEL
			IF  LOCATE_CHAR_ANY_MEANS_3D scplayer 1650.6447 -1059.4348 25.4852 150.0 150.0 70.0 FALSE
			AND HAS_SPECIAL_CHARACTER_LOADED 1

				// create cars
				CREATE_CAR SENTINEL 1649.5554 -1055.7670 22.8984 l1f2_grovecar[0]
				CREATE_CAR SENTINEL 1644.4198 -1057.8428 22.9062 l1f2_grovecar[1]
				CREATE_CAR SENTINEL 1643.8448 -1051.7357 22.9219 l1f2_grovecar[2]
				CREATE_CAR SABRE 1640.6031 -1071.5366 22.9141 l1f2_flatcar[0]
				CREATE_CAR SABRE 1660.9432 -1059.2008 22.9141 l1f2_flatcar[1]

//				SET_CAR_HEALTH l1f2_grovecar[0] 3000
//				SET_CAR_HEALTH l1f2_grovecar[1] 3000
//				SET_CAR_HEALTH l1f2_grovecar[2] 3000
//				SET_CAR_HEALTH l1f2_flatcar[0] 3000
//				SET_CAR_HEALTH l1f2_flatcar[1] 3000

//	 			FREEZE_CAR_POSITION l1f2_grovecar[0] TRUE
//				FREEZE_CAR_POSITION l1f2_grovecar[1] TRUE
//				FREEZE_CAR_POSITION l1f2_grovecar[2] TRUE

//				LOCK_CAR_DOORS l1f2_grovecar[0] CARLOCK_LOCKED
//				LOCK_CAR_DOORS l1f2_grovecar[1] CARLOCK_LOCKED
//				LOCK_CAR_DOORS l1f2_grovecar[2] CARLOCK_LOCKED

	//			SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER l1f2_grovecar[0] 100.0
	//			SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER l1f2_grovecar[1] 100.0
	//			SET_VEHICLE_AIR_RESISTANCE_MULTIPLIER l1f2_grovecar[2] 100.0

//				SET_CAR_HEAVY l1f2_grovecar[0] TRUE
//				SET_CAR_HEAVY l1f2_grovecar[1] TRUE
//				SET_CAR_HEAVY l1f2_grovecar[2] TRUE



			  	


				SET_CAR_HEADING l1f2_grovecar[0] 329.5791
				SET_CAR_HEADING l1f2_grovecar[1] 227.8
				SET_CAR_HEADING l1f2_grovecar[2] 149.9008
				SET_CAR_HEADING l1f2_flatcar[0]	61.7966 
				SET_CAR_HEADING l1f2_flatcar[1]	148.6633

				SET_CAR_STRONG l1f2_grovecar[0] TRUE
				SET_CAR_STRONG l1f2_grovecar[1] TRUE
				SET_CAR_STRONG l1f2_grovecar[2] TRUE
				SET_CAR_STRONG l1f2_flatcar[0] TRUE
				SET_CAR_STRONG l1f2_flatcar[1] TRUE


				OPEN_CAR_DOOR l1f2_flatcar[0] FRONT_RIGHT_DOOR
				OPEN_CAR_DOOR l1f2_flatcar[1] FRONT_RIGHT_DOOR
				OPEN_CAR_DOOR l1f2_flatcar[1] FRONT_LEFT_DOOR

	//			SET_CAR_ONLY_DAMAGED_BY_PLAYER l1f2_grovecar[0] TRUE
	//			SET_CAR_ONLY_DAMAGED_BY_PLAYER l1f2_grovecar[1] TRUE
	//			SET_CAR_ONLY_DAMAGED_BY_PLAYER l1f2_grovecar[2] TRUE
	//			SET_CAR_ONLY_DAMAGED_BY_PLAYER l1f2_flatcar[0] TRUE
	//			SET_CAR_ONLY_DAMAGED_BY_PLAYER l1f2_flatcar[1] TRUE		


				
				// create chars
				CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 1648.5559 -1054.4727 22.8984 lf2_blue[0]
				CREATE_CHAR PEDTYPE_MISSION1 FAM1 1648.5559 -1054.4727 22.8984 lf2_blue[1]
				CREATE_CHAR PEDTYPE_MISSION1 FAM1 1646.4736 -1057.6423 22.8984 lf2_blue[2]
				CREATE_CHAR PEDTYPE_MISSION1 FAM1 1644.6101 -1053.3950 22.8984 lf2_blue[3]

				

				

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1622.2764 -1044.6786 22.8984 lf2_red[0]
				lf2_char_id = lf2_red[0]
				lf2_int1 = 0
				GOSUB lf2_make_red

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1631.5227 -1060.4169 23.0110 lf2_red[1]
				lf2_char_id = lf2_red[1]
				lf2_int1 = 1
				GOSUB lf2_make_red

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1638.7595 -1072.3597 22.8984 lf2_red[2]
				lf2_char_id = lf2_red[2]
				lf2_int1 = 2
				GOSUB lf2_make_red

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1649.3485 -1077.8683 22.9062 lf2_red[3]
				lf2_char_id = lf2_red[3]
				lf2_int1 = 3
				GOSUB lf2_make_red

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1663.6187 -1058.4191 22.8984 lf2_red[4]
				lf2_char_id = lf2_red[4]
				lf2_int1 = 4
				GOSUB lf2_make_red	 

				lf2_blue[4] = scplayer

				SET_CHAR_HEADING lf2_blue[0] 216.2367
				SET_CHAR_HEADING lf2_blue[1] 149.4687
				SET_CHAR_HEADING lf2_blue[2] 100.9368
				SET_CHAR_HEADING lf2_blue[3] 270.9368

				SET_CHAR_ACCURACY lf2_blue[0] 40
				SET_CHAR_ACCURACY lf2_blue[1] 40
				SET_CHAR_ACCURACY lf2_blue[2] 40
				SET_CHAR_ACCURACY lf2_blue[3] 40
				

				SET_CHAR_NEVER_TARGETTED lf2_blue[0] TRUE
				SET_CHAR_NEVER_TARGETTED lf2_blue[1] TRUE
				SET_CHAR_NEVER_TARGETTED lf2_blue[2] TRUE
				SET_CHAR_NEVER_TARGETTED lf2_blue[3] TRUE



				SET_SENSE_RANGE lf2_blue[0] 60.0
				SET_SENSE_RANGE lf2_blue[1] 60.0
				SET_SENSE_RANGE lf2_blue[2] 60.0
				SET_SENSE_RANGE lf2_blue[3] 60.0


				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[0] TRUE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[1] TRUE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[2] TRUE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_blue[3] TRUE

				SET_CHAR_DECISION_MAKER lf2_blue[0] lf2_dec
				SET_CHAR_DECISION_MAKER lf2_blue[1] lf2_dec
				SET_CHAR_DECISION_MAKER lf2_blue[2] lf2_dec
				SET_CHAR_DECISION_MAKER lf2_blue[3] lf2_dec


				SET_CHAR_SUFFERS_CRITICAL_HITS lf2_blue[0] FALSE
				SET_CHAR_SUFFERS_CRITICAL_HITS lf2_blue[1] FALSE
				SET_CHAR_SUFFERS_CRITICAL_HITS lf2_blue[2] FALSE
				SET_CHAR_SUFFERS_CRITICAL_HITS lf2_blue[3] FALSE


				GIVE_WEAPON_TO_CHAR lf2_blue[0] WEAPONTYPE_MP5 99999
				GIVE_WEAPON_TO_CHAR lf2_blue[1] WEAPONTYPE_MP5 99999
				GIVE_WEAPON_TO_CHAR lf2_blue[2] WEAPONTYPE_MP5 99999
				GIVE_WEAPON_TO_CHAR lf2_blue[3] WEAPONTYPE_MP5 99999


				SET_CHAR_HEALTH lf2_blue[0] 100
				SET_CHAR_HEALTH lf2_blue[1] 100
				SET_CHAR_HEALTH lf2_blue[2] 100
				SET_CHAR_HEALTH lf2_blue[3] 100

				SET_CHAR_MAX_HEALTH lf2_blue[0] 100
				SET_CHAR_MAX_HEALTH lf2_blue[1] 100
				SET_CHAR_MAX_HEALTH lf2_blue[2] 100
				SET_CHAR_MAX_HEALTH lf2_blue[3] 100

//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[0] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[1] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[2] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[3] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[4] TRUE
//
//				
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_blue[0] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_blue[1] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_blue[2] TRUE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_blue[3] TRUE

				GENERATE_RANDOM_INT_IN_RANGE 0 5 lf2_int1

				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE

				GENERATE_RANDOM_INT_IN_RANGE 0 5 lf2_int1

				SET_CHAR_ONLY_DAMAGED_BY_PLAYER lf2_red[lf2_int1] TRUE
				

				

				// create sweet


				lf2_shootout_created = 2
			ENDIF
		ENDIF		
	ENDIF

RETURN


lf2_make_red:
	SET_CHAR_ACCURACY lf2_char_id 40
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_char_id TRUE
	SET_CHAR_DECISION_MAKER lf2_char_id lf2_reddec
	GIVE_WEAPON_TO_CHAR lf2_char_id WEAPONTYPE_MP5 99999
	lf2_char_stop[lf2_int1] = 0
	lf2_dead_red_counted[lf2_int1] = 0
	REMOVE_BLIP lf2_red_blip[lf2_int1]
	IF lf2_flag > 3
		ADD_BLIP_FOR_CHAR lf2_red[lf2_int1] lf2_red_blip[lf2_int1]
	ENDIF
RETURN


lf2_gang_AI:

	LVAR_INT lf2_blue_target[3] lf2_red_target[6]
	LVAR_INT lf2_this_blue lf2_this_red lf2_i l_target
	LVAR_INT lf2_int1
	LVAR_FLOAT lx ly lz l_distance l_nearest l_heading
	LVAR_FLOAT lf1 lf2 lf3

	IF lf2_shootout_created = 2


		lf2_this_red ++
		IF lf2_this_red > 5
			lf2_this_red = 0
		ENDIF



		IF NOT IS_CHAR_DEAD lf2_red[lf2_this_red]
			IF NOT lf2_red_script_event[lf2_this_red] = 1
				IF NOT IS_CHAR_IN_ANY_CAR lf2_red[lf2_this_red]

					GET_SCRIPT_TASK_STATUS lf2_red[lf2_this_red] TASK_GO_STRAIGHT_TO_COORD lf2_int1 
					IF lf2_int1 = FINISHED_TASK
						// if char has wandered too far make him 
						IF lf2_char_stop[lf2_this_red] = 0							
							IF LOCATE_CHAR_ANY_MEANS_3D lf2_red[lf2_this_red] 1646.7769 -1054.2567 24.8043 15.0 15.0 15.0 FALSE
								TASK_TOGGLE_DUCK lf2_red[lf2_this_red] TRUE
								SET_CHAR_STAY_IN_SAME_PLACE lf2_red[lf2_this_red] TRUE
								SET_CHAR_KINDA_STAY_IN_SAME_PLACE lf2_red[lf2_this_red] FALSE
								lf2_char_stop[lf2_this_red] = 1
							ENDIF 
						ENDIF
					


						//find closest blue
						GET_CHAR_COORDINATES lf2_red[lf2_this_red] x y z
						lf2_i = 0
						l_nearest = 40.0

						WHILE lf2_i < 5
							IF NOT IS_CHAR_DEAD lf2_blue[lf2_i]
								GET_CHAR_COORDINATES lf2_blue[lf2_i] lx ly lz
								GET_DISTANCE_BETWEEN_COORDS_2D lx ly x y l_distance
								IF l_distance < l_nearest
									l_nearest = l_distance
									l_target = lf2_blue[lf2_i]
								ENDIF
							ENDIF
							lf2_i++
						ENDWHILE

						// set closest blue as target, if target has changed since last check
						IF NOT l_target = lf2_red_target[lf2_this_red]
							CLEAR_CHAR_TASKS lf2_red[lf2_this_red]
							lf2_red_target[lf2_this_red] = l_target
						ENDIF				

						// if char is not shooting set to do so.
						GET_SCRIPT_TASK_STATUS lf2_red[lf2_this_red] TASK_KILL_CHAR_ON_FOOT lf2_int1
						IF lf2_int1 = FINISHED_TASK
							IF NOT IS_CHAR_DEAD	lf2_red_target[lf2_this_red]								
								TASK_KILL_CHAR_ON_FOOT lf2_red[lf2_this_red] lf2_red_target[lf2_this_red]
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF				 
		ELSE
			IF lf2_dead_red_counted[lf2_this_red] = 0
				MARK_CHAR_AS_NO_LONGER_NEEDED lf2_red[lf2_this_red] 
				lf2_dead_reds ++
				lf2_dead_red_counted[lf2_this_red] = 1
				lf2_red_script_event[lf2_this_red] = 0
				REMOVE_BLIP lf2_red_blip[lf2_this_red]
			ENDIF
		ENDIF

		lf2_this_blue ++
		IF lf2_this_blue > 3
			lf2_this_blue = 0
		ENDIF

		IF NOT IS_CHAR_DEAD lf2_blue[lf2_this_blue]
			IF lf2_blue_script_act[lf2_this_blue] = 0		
				IF NOT LOCATE_CHAR_ANY_MEANS_3D lf2_blue[lf2_this_blue] 1647.2491 -1053.4819 23.8804 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS lf2_blue[lf2_this_blue] TASK_GO_STRAIGHT_TO_COORD lf2_int1
					IF lf2_int1 = FINISHED_TASK
						SET_CHAR_DECISION_MAKER lf2_blue[lf2_this_blue] lf2_empty_dec
						GENERATE_RANDOM_FLOAT_IN_RANGE -1.5 1.5 lf1 
						x = 1647.2491 + lf1
						GENERATE_RANDOM_FLOAT_IN_RANGE -1.5 1.5 lf1 
						y = -1053.4819 + lf1

						TASK_GO_STRAIGHT_TO_COORD lf2_blue[lf2_this_blue] x y 23.8804 PEDMOVE_RUN -1
						lf2_blue_script_act[lf2_this_blue] = 1
					ENDIF
				ENDIF			 
			ELSE
				GET_SCRIPT_TASK_STATUS lf2_blue[lf2_this_blue] TASK_GO_STRAIGHT_TO_COORD lf2_int1	
				IF lf2_int1 = FINISHED_TASK
				OR LOCATE_CHAR_ANY_MEANS_3D lf2_blue[lf2_this_blue] 1647.2491 -1053.4819 23.8804 3.0 3.0 3.0 FALSE
					lf2_blue_script_act[lf2_this_blue] = 0
					SET_CHAR_DECISION_MAKER lf2_blue[lf2_this_blue] lf2_dec
				ENDIF
			ENDIF
		ENDIF
	ENDIF




RETURN

lf2_car_damage:

	IF lf2_damage_level[lv1] < 26
		IF NOT IS_CAR_DEAD lf2_carid
			IF lf2_player_entered_car[lv1] = 0
				IF NOT IS_CHAR_IN_CAR scplayer lf2_carid				
					GET_CAR_HEALTH lf2_carid lf2_car_health[lv1]
					IF NOT IS_CAR_HEALTH_GREATER lf2_carid 550
						lf2_car_damage = lf2_damage_level[lv1]						
						IF lf2_damage_level[lv1] >= 25
							SET_CAR_HEALTH lf2_carid 350
							SET_CAR_PROOFS lf2_carid TRUE TRUE TRUE TRUE TRUE
						ELSE 
							SET_CAR_HEALTH lf2_carid 900
						ENDIF

						GOSUB lf2_damage_car
						lf2_damage_level[lv1] ++
					ENDIF
				ELSE
					lf2_player_entered_car[lv1] = 1
					SET_CAR_PROOFS lf2_carid FALSE FALSE FALSE FALSE FALSE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN

lf2_damage_car:

LVAR_INT lf2_car_damage lf2_carid lf2_damage_level[5]
	SWITCH lf2_car_damage
		CASE 0
			DAMAGE_CAR_DOOR lf2_CarID BONNET
		BREAK
		CASE 1
			DAMAGE_CAR_PANEL lf2_CarID FRONT_RIGHT_PANEL
		BREAK
		CASE 2
			DAMAGE_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR
		BREAK
		CASE 3
			DAMAGE_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR
		BREAK
		CASE 4
			DAMAGE_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR
		BREAK
		CASE 5
			BURST_CAR_TYRE lf2_CarID FRONT_RIGHT_WHEEL
		BREAK
		CASE 6
			DAMAGE_CAR_PANEL lf2_CarID FRONT_BUMPER
		BREAK
		CASE 7
			DAMAGE_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR
		BREAK
		CASE 8
			DAMAGE_CAR_PANEL lf2_CarID FRONT_RIGHT_PANEL
		BREAK
		CASE 9
			DAMAGE_CAR_DOOR lf2_CarID BOOT
		BREAK
		CASE 10
			BURST_CAR_TYRE lf2_CarID REAR_RIGHT_WHEEL
		BREAK
		CASE 11
			DAMAGE_CAR_PANEL lf2_CarID REAR_BUMPER
		BREAK
		CASE 12
			DAMAGE_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR
		BREAK
		CASE 13
			DAMAGE_CAR_DOOR lf2_CarID BONNET
		BREAK
		CASE 14
			DAMAGE_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR
		BREAK
		CASE 15
			DAMAGE_CAR_PANEL lf2_CarID FRONT_RIGHT_PANEL
		BREAK
		CASE 16
			POP_CAR_DOOR lf2_CarID BOOT TRUE
		BREAK
		CASE 17
			DAMAGE_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR
		BREAK
		CASE 18
			POP_CAR_DOOR lf2_CarID BONNET TRUE
		BREAK
		CASE 19
			POP_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR TRUE
		BREAK
		CASE 20
			DAMAGE_CAR_PANEL lf2_CarID FRONT_BUMPER
		BREAK
		CASE 21
			POP_CAR_DOOR lf2_CarID BONNET TRUE
		BREAK
		CASE 22	
			DAMAGE_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR
		BREAK
		CASE 23
			DAMAGE_CAR_DOOR lf2_CarID FRONT_RIGHT_DOOR
		BREAK
		CASE 24
			DAMAGE_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR
		BREAK
		CASE 25
			POP_CAR_DOOR lf2_CarID REAR_RIGHT_DOOR TRUE
		BREAK
			
		
ENDSWITCH

RETURN


lafin2_audio:


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
				IF play_audio = 0
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
	ENDIF
		
	SWITCH audio_flag

		CASE 0 //first time game starts
	
			LVAR_TEXT_LABEL audio_text[40]
			LVAR_INT audio_sound[40] audio_slot[3] play_slot  
			LVAR_INT next_audio  
			LVAR_INT audio_flag play_audio play_audio_for

			LVAR_INT audio_for_char[40] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay audio_i audio_char audio_count force_audio

			IF audio_flag = 1
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 this_actor
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 audio_char
			ENDIF

	  		$audio_text[1] = &FIN2_BA //Sweet, bro, shit, you’re hit!
			audio_sound[1] = SOUND_FIN2_BA

			$audio_text[2] = &FIN2_BB //CJ... Where you been?
			audio_sound[2] = SOUND_FIN2_BB

			$audio_text[3] = &FIN2_BC //Cesar called, showed me some shit.
			audio_sound[3] = SOUND_FIN2_BC

			$audio_text[4] = &FIN2_BD //It’s Smoke, he’s in deep with Tenpenny and some Ballas!
			audio_sound[4] = SOUND_FIN2_BD

			$audio_text[5] = &FIN2_BE //He sold us out!
			audio_sound[5] = SOUND_FIN2_BE

			$audio_text[6] = &FIN2_BF //It doesn’t matter, you gotta get out of here, the cops will arrive any second!
			audio_sound[6] = SOUND_FIN2_BF

			$audio_text[7] = &FIN2_BG //No way, I’m not running out on my brother!
			audio_sound[7] = SOUND_FIN2_BG

			$audio_text[8] = &FIN2_BH //Yo, Ballas! I’m taking you motherfuckers, you hear me?
			audio_sound[8] = SOUND_FIN2_BH

			$audio_text[9] = &FIN2_BJ //I’M TAKING YOU ALL DOWN, BITCHES!
			audio_sound[9] = SOUND_FIN2_BJ

			$audio_text[10] = &FIN2_AA //I gotta warn Sweet!
			audio_sound[10] = SOUND_FIN2_AA

			$audio_text[11] = &FIN2_AB //Dialing tone
			audio_sound[11] = SOUND_FIN2_AB

			$audio_text[12] = &FIN2_AB //Dialing tone
			audio_sound[12] = SOUND_FIN2_AB

			$audio_text[13] = &FIN2_AC //Dammit, Sweet, pickup! Pickup!
			audio_sound[13] = SOUND_FIN2_AC

			$audio_text[14] = &FIN2_AD //Dialoing tone
			audio_sound[14] = SOUND_FIN2_AD

			$audio_text[15] = &FIN2_AE //Yo, you called me, but I ain’t picking up, so leave a message after the –
			audio_sound[15] = SOUND_FIN2_AE

			$audio_text[16] = &FIN2_AF //Shit! Shit! Shit!
			audio_sound[16] = SOUND_FIN2_AF

//			$audio_text[17] = &MOBRING //Dialing tone
			audio_sound[17] = SOUND_MOBRING
//
//			$audio_text[18] = &MOBRING //Dialing tone
			audio_sound[18] = SOUND_MOBRING

			$audio_text[19] = &MCES03A //It doesn’t matter, you gotta get out of here, the cops will arrive any second!
			audio_sound[19] = SOUND_MCES03A

			$audio_text[20] = &MCES03B //No way, I’m not running out on my brother!
			audio_sound[20] = SOUND_MCES03B

			$audio_text[21] = &MCES03C //Yo, Ballas! I’m taking you motherfuckers, you hear me?
			audio_sound[21] = SOUND_MCES03C

			$audio_text[22] = &MCES03D //I’M TAKING YOU ALL DOWN!
			audio_sound[22] = SOUND_MCES03D

			$audio_text[23] = &MCES03E //I gotta warn Sweet!
			audio_sound[23] = SOUND_MCES03E

			$audio_text[24] = &MCES03F //Dialing tone
			audio_sound[24] = SOUND_MCES03F

			$audio_text[25] = &MCES03G //Dammit, Sweet, pickup! Pickup!
			audio_sound[25] = SOUND_MCES03G

			$audio_text[26] = &MCES03H //Dialoing tone
			audio_sound[26] = SOUND_MCES03H

			$audio_text[27] = &MCES03J //Yo, you called me, but I ain’t picking up, so leave a message after the –
			audio_sound[27] = SOUND_MCES03J

			$audio_text[28] = &MCES03L //Shit! Shit! Shit!
			audio_sound[28] = SOUND_MCES03L

			$audio_text[29] = &MCES03M //It doesn’t matter, you gotta get out of here, the cops will arrive any second!
			audio_sound[29] = SOUND_MCES03M

			audio_for_char[1] = 1
			audio_for_char[2] = 1
			audio_for_char[3] = 1
			audio_for_char[4] = 1
			audio_for_char[5] = 1
			audio_for_char[6] = 1
			audio_for_char[7] = 1
			audio_for_char[8] = 1
			audio_for_char[9] = 1
			audio_for_char[10] = 0
			audio_for_char[11] = 0
			audio_for_char[12] = 0
			audio_for_char[13] = 0
			audio_for_char[14] = 0
			audio_for_char[15] = 0
			audio_for_char[16] = 0
			audio_for_char[17] = 0
			audio_for_char[18] = 0
			audio_for_char[19] = 0
			audio_for_char[20] = 0
			audio_for_char[21] = 0
			audio_for_char[22] = 0
			audio_for_char[23] = 0
			audio_for_char[24] = 0
			audio_for_char[25] = 0
			audio_for_char[26] = 0
			audio_for_char[27] = 0
			audio_for_char[28] = 0
			audio_for_char[29] = 0

			audio_actor[1] = scplayer
			audio_actor[2] = lf2_blue[0]

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
						actor_int = audio_for_char[play_audio]
						this_actor = audio_actor[actor_int]
						IF NOT force_audio = 1 //otherwise audio will not play if no mission peds are nearby
							audio_i = 1
							audio_count = 0
							WHILE audio_i < 7
								audio_char = audio_actor[audio_i]
								IF NOT audio_char = 0
									IF NOT audio_char = this_actor
										IF NOT IS_CHAR_DEAD this_actor
											IF NOT IS_CHAR_DEAD audio_char
												IF LOCATE_CHAR_ANY_MEANS_CHAR_3D this_actor audio_char 10.0 10.0 10.0 FALSE
													audio_count ++
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								audio_i++
							ENDWHILE
						ENDIF

						IF force_audio = 1
						OR audio_count > 0
						OR audio_for_char[play_audio] = 0
							IF NOT audio_for_char[play_audio] = 0
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
								IF NOT audio_sound[play_audio] = SOUND_MOBRING
									PRINT $audio_text[play_audio] 10000 1
								ENDIF
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
						ENDIF
					ELSE
						LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
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
				IF NOT IS_CHAR_DEAD this_actor
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

 





mission_la1fin2_failed:

	PRINT_BIG ( M_FAIL ) 50  1 //"Mission Failed"

RETURN

   

// **************************************** mission la1fin2 passed *************************

mission_la1fin2_passed:
	// END CUT SCENE AS INTRO TO COUNTRYSIDE

	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -2007.5 -2542.4 35.7
	ELSE
		SET_CHAR_COORDINATES scplayer -2007.5 -2542.4 35.7
		SET_CHAR_HEADING scplayer 80.0
	ENDIF

	DELETE_CAR l1f2_flatpony[1]
	DELETE_CAR copcar_bust_cut[0]
	DELETE_CAR copcar_bust_cut[1]
	DELETE_CAR copcar_bust_cut[2]
	DELETE_CAR l1f2_driveby_car[0]
	DELETE_CAR l1f2_grovecar[0]
	DELETE_CAR l1f2_grovecar[1]
	DELETE_CAR l1f2_grovecar[2]
	DELETE_CAR l1f2_flatcar[0]
	DELETE_CAR l1f2_flatcar[1]
	DELETE_CHAR lf2_red[0]
	DELETE_CHAR lf2_red[1]
	DELETE_CHAR lf2_red[2]
	DELETE_CHAR lf2_red[3]
	DELETE_CHAR lf2_red[4]
	DELETE_CHAR lf2_red[5]
	DELETE_CHAR cop_bust_cut[0]
	DELETE_CHAR cop_bust_cut[1]
	DELETE_CHAR cop_bust_cut[2]
	DELETE_CHAR lf2_blue[0]
	DELETE_CHAR lf2_blue[1]
	DELETE_CHAR lf2_blue[2]
	DELETE_CHAR lf2_blue[3]

	// Mark models as no longer needed
	MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA
	MARK_MODEL_AS_NO_LONGER_NEEDED SABRE
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
	MARK_MODEL_AS_NO_LONGER_NEEDED PONY
//	MARK_MODEL_AS_NO_LONGER_NEEDED paypark_lan02
//	MARK_MODEL_AS_NO_LONGER_NEEDED bevhiltreepv

	REMOVE_ANIMATION SWEET


	// unload special characters
	UNLOAD_SPECIAL_CHARACTER 1 

	REMOVE_DECISION_MAKER lf2_empty_dec
	REMOVE_DECISION_MAKER lf2_driveby_dec

//	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1





	SET_INT_STAT CITIES_PASSED 1

	LOAD_CUTSCENE BCRAS1
	SET_CHAR_COORDINATES scplayer -2011.4375 -2501.2871 34.0624
	SET_PLAYER_CONTROL player1 OFF
	LOAD_SCENE -2011.4375 -2501.2871 34.0624
	CLEAR_AREA -2011.4375 -2501.2871 34.0624 100.0 TRUE
	CLEAR_HELP

	REMOVE_ALL_CHAR_WEAPONS scplayer

	LOAD_MISSION_TEXT BCRASH1
		 
	WHILE NOT HAS_CUTSCENE_LOADED
	            WAIT 0
	ENDWHILE

	WAIT 1000
	 
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

	LOAD_CUTSCENE BCRAS2
	 
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

	REQUEST_MODEL CAMERA

	WHILE NOT HAS_MODEL_LOADED CAMERA
		WAIT 0
	ENDWHILE

	 
	DO_FADE 1000 FADE_IN														

	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 30000
	MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA

	SET_PLAYER_CONTROL player1 ON


	REMOVE_BLIP bcrash_contact_blip
	REMOVE_BLIP sweet_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcrashX bcrashY bcrashZ crash_blip_icon bcrash_contact_blip

	REMOVE_BLIP save_house_blip[13]
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[13] save_pickupY[13] save_pickupZ[13] RADAR_SPRITE_SAVEHOUSE save_house_blip[13]
	CHANGE_BLIP_DISPLAY save_house_blip[13] BLIP_ONLY

	CLEAR_WANTED_LEVEL player1

	SET_INT_STAT PASSED_LAFIN2 1
	SET_INT_STAT STARTED_BADLANDS 1

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_CAT
	START_NEW_SCRIPT cell_phone_cat    
                        

	flag_la1fin1_mission_counter ++
	terminted_territory_pickup = 1


	REGISTER_MISSION_PASSED ( LA1FIN2 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	REMOVE_PICKUP grove_save_pickup[13]

	CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[13] save_pickupY[13] save_pickupZ[13] grove_save_pickup[13] //BADLANDS TRAILOR
	number_of_save_icons = 14





                        SET_ZONE_GANG_STRENGTH SUN1 GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH SUN1 GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH SUN3a GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH SUN3b GANG_FLAT 30 
                        SET_ZONE_GANG_STRENGTH SUN3c GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH SUN4 GANG_FLAT 30
            
                        SET_ZONE_GANG_STRENGTH CHC1a GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC1b GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC2a GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC2b GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC3  GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC4a GANG_NMEX 40
                        SET_ZONE_GANG_STRENGTH CHC4b GANG_NMEX 40
                                    
                        SET_ZONE_GANG_STRENGTH EBE1 GANG_NMEX  30  
                        SET_ZONE_GANG_STRENGTH EBE2a GANG_NMEX 30  
                        SET_ZONE_GANG_STRENGTH EBE2b GANG_NMEX 30                        
                        SET_ZONE_GANG_STRENGTH EBE3c GANG_NMEX 30

                        SET_ZONE_GANG_STRENGTH ELCO1 GANG_SMEX 40
                        SET_ZONE_GANG_STRENGTH ELCO2 GANG_SMEX 40

                        SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 10 
                        SET_ZONE_GANG_STRENGTH GAN2 GANG_FLAT 25 

                        SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40 
                        SET_ZONE_GANG_STRENGTH GLN2a GANG_FLAT 40

                        SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT 20

                        SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 20 
                        SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 20
                        SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 10
                        SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 20

                        SET_ZONE_GANG_STRENGTH JEF1a GANG_FLAT 40
                        SET_ZONE_GANG_STRENGTH JEF1b GANG_FLAT 40
                        SET_ZONE_GANG_STRENGTH JEF2  GANG_FLAT 40

                        SET_ZONE_GANG_STRENGTH JEF3b GANG_FLAT 40
                        SET_ZONE_GANG_STRENGTH JEF3c GANG_FLAT 40

                        SET_ZONE_GANG_STRENGTH LFL1a GANG_NMEX      40             
                        SET_ZONE_GANG_STRENGTH LFL1b GANG_NMEX      40

                        SET_ZONE_GANG_STRENGTH LMEX1a GANG_SMEX 30
                        SET_ZONE_GANG_STRENGTH LMEX1b GANG_SMEX 30

                        SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH ELS2    GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 30
                        SET_ZONE_GANG_STRENGTH ELS4    GANG_FLAT 30

                        SET_ZONE_GANG_STRENGTH PLS GANG_FLAT 10

                        SET_ZONE_GANG_STRENGTH SMB1 GANG_FLAT 10 
                        SET_ZONE_GANG_STRENGTH SMB2 GANG_FLAT 10               

                        SET_ZONE_GANG_STRENGTH VIN2 GANG_FLAT 10                 

                        SET_ZONE_GANG_STRENGTH VERO1 GANG_FLAT 10
                        SET_ZONE_GANG_STRENGTH VERO2 GANG_FLAT 10
                        SET_ZONE_GANG_STRENGTH VERO3 GANG_FLAT 10
                        SET_ZONE_GANG_STRENGTH VERO4a GANG_FLAT 10
                        SET_ZONE_GANG_STRENGTH VERO4b GANG_FLAT 10

                        // Set GROVE to 0 in case player started to gain this territory
                        SET_ZONE_GANG_STRENGTH SUN1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH SUN1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH SUN3a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH SUN3b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH SUN3c GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH SUN4 GANG_GROVE 0
            
                        SET_ZONE_GANG_STRENGTH CHC1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC1b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC2a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC2b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC3  GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC4a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH CHC4b GANG_GROVE 0
                                    
                        SET_ZONE_GANG_STRENGTH EBE1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH EBE2a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH EBE2b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH EBE3c GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH ELCO1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELCO2 GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH GLN2a GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH LIND1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LIND1b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LIND2a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LIND2b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LIND3 GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH IWD1 GANG_GROVE 0 
                        SET_ZONE_GANG_STRENGTH IWD2 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH IWD3a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH IWD3b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH IWD4 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH IWD5 GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH JEF1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH JEF1b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH JEF2  GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH JEF3a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH JEF3b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH JEF3c GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH LFL1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LFL1b GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH LMEX1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH LMEX1b GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH ELS1a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELS1b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELS2    GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELS3a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELS3b GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH ELS4    GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH PLS GANG_GROVE 0

                        SET_ZONE_GANG_STRENGTH SMB1 GANG_GROVE 0 
                        SET_ZONE_GANG_STRENGTH SMB2 GANG_GROVE 0                        

                        SET_ZONE_GANG_STRENGTH VIN2 GANG_GROVE 0              

                        SET_ZONE_GANG_STRENGTH VERO1 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH VERO2 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH VERO3 GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH VERO4a GANG_GROVE 0
                        SET_ZONE_GANG_STRENGTH VERO4b GANG_GROVE 0


	SET_GANG_WARS_ACTIVE FALSE



	START_NEW_SCRIPT bcrash_mission_loop


RETURN
		





// ********************************** mission cleanup ************************************

mission_cleanup_la1fin2:

	flag_player_on_mission = 0


	//

	CLEAR_SKIP

	IF NOT IS_CHAR_DEAD scplayer
		TASK_USE_MOBILE_PHONE scplayer FALSE
	ENDIF





//	reset gang sizes
//	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

	// remove animations
//	REMOVE_ANIMATION SWAT


	// Mark models as no longer needed
	MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA
	MARK_MODEL_AS_NO_LONGER_NEEDED SABRE
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
	MARK_MODEL_AS_NO_LONGER_NEEDED PONY
	REMOVE_ANIMATION SWEET

	REMOVE_CAR_RECORDING 162
	REMOVE_CAR_RECORDING 163
	REMOVE_CAR_RECORDING 650
	MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
	MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1 
	REMOVE_ANIMATION POLICE


	SET_PLAYER_CONTROL player1 ON

	// unload special characters
	UNLOAD_SPECIAL_CHARACTER 1 

	REMOVE_DECISION_MAKER lf2_empty_dec
	REMOVE_DECISION_MAKER lf2_driveby_dec

	IF NOT IS_CAR_DEAD l1f2_meet_car
		MARK_CAR_AS_NO_LONGER_NEEDED l1f2_meet_car
	ENDIF


	//remove car recordings
//	REMOVE_CAR_RECORDING 154

	// Remove blips
	REMOVE_BLIP l1f2_blip

	lf2_i = 0
	WHILE lf2_i < 6
		REMOVE_BLIP lf2_red_blip[lf2_i]
		lf2_i ++
	ENDWHILE

	//remove counters
	CLEAR_ONSCREEN_COUNTER l1f2_sweet_health

	REMOVE_IPL Barriers1


	// reset multipliers
	SET_WANTED_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	// set emergency services
	SWITCH_EMERGENCY_SERVICES ON

   	// remove decision makers
	REMOVE_DECISION_MAKER lf2_dec

	SET_CREATE_RANDOM_GANG_MEMBERS ON

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99







RETURN

}

 			 







