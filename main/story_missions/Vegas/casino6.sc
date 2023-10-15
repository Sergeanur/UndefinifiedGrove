MISSION_START

// ########################
// ##
// ##	Casino6.sc 
// ##
// ##	The Meat Business
// ##
// ## 	Simon Lashley
// ##
// ######################## 
										   
SCRIPT_NAME CASINO6
																
GOSUB mission_casino6_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino6_FAILED
ENDIF

GOSUB mission_casino6_CLEANUP

MISSION_END

{

// decision makers
LVAR_INT empty_dm_C6 attack_dm_C6

// player
LVAR_INT player_trapped_C6 player_fire_ex_C6 freezer_armour_C6

// rosenberg
LVAR_INT rosenberg_C6 rosenberg_blip_C6 rosenberg_status_C6 rosenberg_timer_C6 fire_ex_C6 rosenberg_trapped_C6
VAR_INT rosenbergs_health_C6
LVAR_INT rosenberg_text_flag_C6[6] rosenberg_text_timer_C6 rosenberg_random_text_C6

// mafia
LVAR_INT mafia_C6[29] mafia_blip_C6[29] mafia_status_C6[29] number_of_mafia_C6 mafia_dead_C6 

// meat track variables
LVAR_INT mt_carcass_C6[6] mt_obj_movement_C6[6] mt_obj_C6[6] mt_switch_C6 mt_status_C6 mt_stopped_C6 mt_timer_C6
LVAR_FLOAT mt_obj_angle_C6[6] mt_obj_rot_C6[6]
  
LVAR_FLOAT mt_speed_C6 mt_accel_C6 mt_dis_per_degree_C6 mt_degrees_C6 mt_radius_C6
LVAR_FLOAT mt_X_C6[8] mt_Y_C6[8] mt_Z_C6 
 
LVAR_FLOAT cos_angle_C6 sin_angle_C6 new_X_C6 new_Y_C6

// freezer
LVAR_INT fd_C6 fd_switch_C6 fd_status_C6 fd_attractor_C6 fd_attractor_seq_C6 f_timer_C6 f_carcass_C6[6] 
LVAR_FLOAT fd_speed_C6 fd_accel_C6

// general
LVAR_INT pointer_C6 pointer2_C6 goto_blip_C6 cutscene_status_C6 weapon_type_C6

// timers
LVAR_INT this_frame_time_C6 last_frame_time_C6 time_diff_C6 

// multi-use
LVAR_FLOAT x2_C6 y2_C6 z2_C6

// fail mission
LVAR_INT fail_text_flag_C6
LVAR_TEXT_LABEL fail_text_C6


mission_casino6_START:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	SWITCH_ENTRY_EXIT abatoir TRUE

	// text
	LOAD_MISSION_TEXT CASINO6					   

	SET_CHAR_COORDINATES scplayer 2179.9343 1684.1483 10.0533
	SET_CHAR_HEADING scplayer 108.0825 
	//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 10.0
	FREEZE_CHAR_POSITION scplayer TRUE
	SET_PLAYER_CONTROL player1 OFF

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

	iTerminateAllAmbience = 1


// **************************************** START OF CUTSCENE

	SET_AREA_VISIBLE 2

	LOAD_CUTSCENE CAS_6a
	 
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 
	START_CUTSCENE

	DO_FADE 1000 FADE_IN
	  
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 0 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	CLEAR_CUTSCENE

	SET_AREA_VISIBLE 0

// **************************************** END OF CUTSCENE


	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

LVAR_INT never_run_this_C6
	IF never_run_this_C6 = 1 
		CREATE_CAR SENTINEL 0.0 0.0 0.0 player_vehicle_C6
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 goto_blip_C6
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 rosenberg_blip_C6
	ENDIF

// ########################
// ##
// ##	DRIVE TO THE ABATTOIR
// ##
// ########################

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

	// audio controller
	START_NEW_SCRIPT start_audio_controller

	SET_CHAR_AREA_VISIBLE scplayer 0
	LOAD_SCENE_IN_DIRECTION 2179.9343 1684.1483 10.0533 108.0825
	
	// DECISION MAKERS
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_C6
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH attack_dm_C6

   	// rosenberg
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL PEDTYPE_MISSION2
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_SPECIAL PEDTYPE_PLAYER1

	// mafia
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_SPECIAL
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

	GET_PLAYER_GROUP player1 players_group_C6
	SET_GROUP_DEFAULT_TASK_ALLOCATOR players_group_C6 DEFAULT_TASK_ALLOCATOR_FOLLOW_LIMITED

	// chars
	LOAD_SPECIAL_CHARACTER 1 ROSE

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		WAIT 0
	ENDWHILE


	// rosenberg
	CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2181.3718 1680.9257 10.0636 rosenberg_C6
	SET_CHAR_HEADING rosenberg_C6 71.6745  
	SET_CHAR_AREA_VISIBLE rosenberg_C6 0

	REMOVE_BLIP rosenberg_blip_C6
	ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
	CHANGE_BLIP_DISPLAY rosenberg_blip_C6 BLIP_ONLY
	SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE 

	SET_CHAR_DECISION_MAKER rosenberg_C6 empty_dm_C6
	SET_CHAR_NEVER_TARGETTED rosenberg_C6 TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS rosenberg_C6 FALSE
	SET_CHAR_HEALTH rosenberg_C6 300

	SET_CHAR_NEVER_LEAVES_GROUP rosenberg_C6 TRUE
	SET_GROUP_MEMBER players_group_C6 rosenberg_C6
	
	SET_CHAR_SHOOT_RATE rosenberg_C6 100

	ADD_BLIP_FOR_COORD 967.3132 2161.1538 9.8203 goto_blip_C6
	PRINT_NOW CAS6_10 8000 1 //

	FREEZE_CHAR_POSITION scplayer FALSE
	SET_PLAYER_CONTROL player1 ON

	LVAR_INT health_pickup_C6
	CREATE_PICKUP HEALTH PICKUP_ONCE 958.9762 2096.8362 1011.1 health_pickup_C6

	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	
	// audio variables
	// banter on the streets
	$audio_text_label_C6[0] = &CAS6_AA
	audio_sound_label_C6[0]	= SOUND_CAS6_AA
	audio_speaker_C6[0] 	= scplayer
	$audio_text_label_C6[1] = &CAS6_AB
	audio_sound_label_C6[1]	= SOUND_CAS6_AB
	audio_speaker_C6[1] 	= rosenberg_C6
	$audio_text_label_C6[2] = &CAS6_AC
	audio_sound_label_C6[2]	= SOUND_CAS6_AC
	audio_speaker_C6[2] 	= rosenberg_C6
	$audio_text_label_C6[3] = &CAS6_AD
	audio_sound_label_C6[3]	= SOUND_CAS6_AD
	audio_speaker_C6[3] 	= rosenberg_C6
	$audio_text_label_C6[4] = &CAS6_AE
	audio_sound_label_C6[4]	= SOUND_CAS6_AE
	audio_speaker_C6[4] 	= scplayer
	$audio_text_label_C6[5] = &CAS6_AF
	audio_sound_label_C6[5]	= SOUND_CAS6_AF
	audio_speaker_C6[5] 	= rosenberg_C6

	// in car banter
	$audio_text_label_C6[6] = &CAS6_BA
	audio_sound_label_C6[6]	= SOUND_CAS6_BA
	audio_speaker_C6[6] 	= rosenberg_C6
	$audio_text_label_C6[7] = &CAS6_BB
	audio_sound_label_C6[7]	= SOUND_CAS6_BB
	audio_speaker_C6[7] 	= rosenberg_C6
	$audio_text_label_C6[8] = &CAS6_BC
	audio_sound_label_C6[8]	= SOUND_CAS6_BC
	audio_speaker_C6[8] 	= rosenberg_C6
	$audio_text_label_C6[9] = &CAS6_BD
	audio_sound_label_C6[9]	= SOUND_CAS6_BD
	audio_speaker_C6[9] 	= scplayer
	$audio_text_label_C6[10]= &CAS6_BE
	audio_sound_label_C6[10]= SOUND_CAS6_BE
	audio_speaker_C6[10] 	= rosenberg_C6
	$audio_text_label_C6[11]= &CAS6_BF
	audio_sound_label_C6[11]= SOUND_CAS6_BF
	audio_speaker_C6[11] 	= rosenberg_C6
	$audio_text_label_C6[12]= &CAS6_BH
	audio_sound_label_C6[12]= SOUND_CAS6_BH
	audio_speaker_C6[12] 	= rosenberg_C6
	$audio_text_label_C6[13]= &CAS6_BK
	audio_sound_label_C6[13]= SOUND_CAS6_BK
	audio_speaker_C6[13] 	= scplayer
	$audio_text_label_C6[14]= &CAS6_BL
	audio_sound_label_C6[14]= SOUND_CAS6_BL
	audio_speaker_C6[14] 	= rosenberg_C6
	$audio_text_label_C6[15]= &CAS6_BM
	audio_sound_label_C6[15]= SOUND_CAS6_BM
	audio_speaker_C6[15] 	= rosenberg_C6
	$audio_text_label_C6[16]= &CAS6_BN
	audio_sound_label_C6[16]= SOUND_CAS6_BN
	audio_speaker_C6[16] 	= rosenberg_C6

	// near abattoir
	$audio_text_label_C6[17]= &CAS6_CA
	audio_sound_label_C6[17]= SOUND_CAS6_CA
	audio_speaker_C6[17] 	= rosenberg_C6
	$audio_text_label_C6[18]= &CAS6_CB
	audio_sound_label_C6[18]= SOUND_CAS6_CB
	audio_speaker_C6[18] 	= rosenberg_C6

	//audio_pointer_C6 = 0
	//audio_status_C6	= 0
	TIMERB = 0



LVAR_INT entry_exit_status_C6
mission_casino6_MAIN_drive_to_abattoir_loop:

	WAIT 0

	IF NOT IS_CHAR_DEAD rosenberg_C6

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
			SWITCH_ENTRY_EXIT abatoir TRUE
			
			entry_exit_area = 0
			entry_exit_status = FALSE
			$entry_exit_name = abatoir
			START_NEW_SCRIPT switch_entry_exit_after_mission

	        GOTO mission_casino6_PASSED  
		ENDIF


		// interior check
		GET_AREA_VISIBLE active_area_C6  
		IF NOT active_area_C6 = 0
			
			CLEAR_PRINTS
			
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 967.3132 2161.1538 5.0 5.0 FALSE
				GOTO mission_casino6_MAIN_player_entered_abattoir
			ELSE
				FREEZE_CHAR_POSITION rosenberg_C6 TRUE
				
				REMOVE_BLIP goto_blip_C6
				REMOVE_BLIP rosenberg_blip_C6
				REMOVE_CHAR_FROM_GROUP rosenberg_C6
				rosenberg_blip_status_C6 = 0
				GOTO mission_casino6_MAIN_goto_rosenberg_interior
			ENDIF 	
		ENDIF


		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 967.3132 2161.1538 30.0 30.0 FALSE
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 30.0 30.0 FALSE	
				IF rosenberg_text_status_C6 < 2 
					rosenberg_text_status_C6 = 2
				ENDIF
				IF entry_exit_status_C6 = 0
					SWITCH_ENTRY_EXIT abatoir FALSE
					entry_exit_status_C6 = 1
				ENDIF
			ELSE
				IF audio_status_C6 < 5
					audio_pointer_C6 = 17
					audio_status_C6 = 5
				ENDIF
				IF entry_exit_status_C6 = 1
					SWITCH_ENTRY_EXIT abatoir TRUE
					entry_exit_status_C6 = 0
				ENDIF
	
			ENDIF
		ENDIF

		GOSUB mission_casino6_SUB_rosenberg_blip
		GOSUB mission_casino6_SUB_goto_rosenberg_text
		GOSUB mission_casino6_SUB_goto_dialogue

	ELSE
		fail_text_flag_C6 = 1
		$fail_text_C6 = CAS6_F0		
		GOTO mission_casino6_FAILED
	ENDIF

GOTO mission_casino6_MAIN_drive_to_abattoir_loop





mission_casino6_MAIN_goto_rosenberg_interior:

	WAIT 0

	IF NOT IS_CHAR_DEAD rosenberg_C6	
		GET_AREA_VISIBLE active_area_C6 
		IF active_area_C6 = 0

			FREEZE_CHAR_POSITION rosenberg_C6 FALSE
			SET_CHAR_NEVER_LEAVES_GROUP rosenberg_C6 TRUE
			SET_GROUP_MEMBER players_group_C6 rosenberg_C6	
			
			REMOVE_BLIP rosenberg_blip_C6
			ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
			SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE 
			CHANGE_BLIP_DISPLAY rosenberg_blip_C6 BLIP_ONLY

			ADD_BLIP_FOR_COORD 967.3132 2161.1538 9.8203 goto_blip_C6
			
			TIMERA = 0
			rosenberg_text_status_C6 = 3

			GOTO mission_casino6_MAIN_drive_to_abattoir_loop
		ENDIF
	ELSE
		// just in for safety reasons
		fail_text_flag_C6 = 1
		$fail_text_C6 = CAS6_F0		
		GOTO mission_casino6_FAILED
	ENDIF

GOTO mission_casino6_MAIN_goto_rosenberg_interior




mission_casino6_SUB_goto_dialogue:

	SWITCH audio_status_C6

		CASE 0
			IF TIMERB > 8000
				audio_status_C6 ++
			ENDIF	
		BREAK

		// start dialogue
		CASE 1
			IF audio_pointer_C6 < 6
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 20.0 20.0 FALSE
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_C6[audio_pointer_C6]
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ELSE
				audio_status_C6 = 3
				BREAK
			ENDIF
		CASE 2
			IF SLOT_status[preload_slot] = -3
				IF rosenberg_blip_status_C6 = 1
					audio_pointer_C6 = 6
					audio_status_C6 = 3
				ELSE
					audio_status_C6 = 1
				ENDIF
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK		

		// in car dialogue
		CASE 3
			IF rosenberg_blip_status_C6 = 1 
				IF audio_pointer_C6 < 17
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 1 100 audio_speaker_C6[audio_pointer_C6]
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ENDIF
		CASE 4
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 3
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		// end dialogue
		CASE 5
			IF audio_pointer_C6 < 19
				load_sample = audio_sound_label_C6[audio_pointer_C6]
				$load_text = $audio_text_label_C6[audio_pointer_C6]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_C6[audio_pointer_C6] 
				audio_pointer_C6 ++
				audio_status_C6 ++
			ENDIF
		CASE 6
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 5
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

	ENDSWITCH

RETURN


mission_casino6_SUB_goto_rosenberg_text:

	SWITCH rosenberg_text_status_C6
	
		CASE 0
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 60.0 60.0 FALSE
				PRINT_NOW CAS6_11 8000 1
				TIMERA = 0
				rosenberg_text_status_C6 ++
			ENDIF
		BREAK

		CASE 1
			IF TIMERA > 20000
				rosenberg_text_status_C6 --
			ENDIF
		BREAK

		CASE 2
			PRINT_NOW CAS6_12 8000 1
			TIMERA = 0
			rosenberg_text_status_C6 ++
		BREAK

		CASE 3
			IF TIMERA > 20000
				rosenberg_text_status_C6 = 0
			ENDIF
		BREAK

	ENDSWITCH
	 
RETURN




































	  
  

// ########################
// ##
// ##	INSIDE THE ABATTOIR
// ##
// ########################

mission_casino6_MAIN_player_entered_abattoir:

// store_players vehicle

LVAR_INT save_car_C6 car_model_C6 car_colours_C6[4] car_tyres_C6 car_doors_C6
LVAR_FLOAT car_pos_C6[4]

	CLEAR_WANTED_LEVEL player1
	
	IF NOT IS_CAR_DEAD player_vehicle_C6
		
		IF IS_CAR_IN_AREA_2D player_vehicle_C6 960.4431 2087.9746 996.5055 2182.1526 FALSE
			IF NOT IS_CAR_UPSIDEDOWN player_vehicle_C6
				save_car_C6 = 1
					
				GET_CAR_COORDINATES player_vehicle_C6 car_pos_C6[0] car_pos_C6[1] car_pos_C6[2] 
				GET_CAR_HEADING player_vehicle_C6 car_pos_C6[3]

				GET_CAR_MODEL player_vehicle_C6 car_model_C6
				GET_CAR_COLOURS player_vehicle_C6 car_colours_C6[0] car_colours_C6[1] 
				GET_EXTRA_CAR_COLOURS player_vehicle_C6 car_colours_C6[2] car_colours_C6[3] 

				// check tyres
				pointer_C6 = 0
				WHILE pointer_C6 < 4
					IF IS_CAR_TYRE_BURST player_vehicle_C6 pointer_C6
						SET_BIT car_tyres_C6 pointer_C6 	
					ENDIF
					pointer_C6 ++
				ENDWHILE	

				// check doors
				IF IS_THIS_MODEL_A_CAR car_model_C6
					pointer_C6 = 0
					WHILE pointer_C6 < 6
						IF IS_CAR_DOOR_DAMAGED player_vehicle_C6 pointer_C6
							SET_BIT car_doors_C6 pointer_C6 	
						ENDIF
						pointer_C6 ++
					ENDWHILE
				ELSE
					car_doors_C6 = -1
				ENDIF
						
			ENDIF
		ENDIF

		FIX_CAR player_vehicle_C6
		MARK_MODEL_AS_NO_LONGER_NEEDED car_model_C6
	ENDIF

	active_area_C6 = 0
	WHILE active_area_C6 = 0 
		WAIT 0
		GET_CHAR_AREA_VISIBLE scplayer active_area_C6
	ENDWHILE  

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_CHAR_COORDINATES_DONT_WARP_GANG	scplayer 963.7808 2165.3496 1010.0312
	SET_PLAYER_CONTROL player1 OFF
	DISABLE_PLAYER_SPRINT player1 TRUE
	//SET_CHAR_AREA_VISIBLE scplayer 1
	//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 10.0

	IF NOT IS_CHAR_DEAD rosenberg_C6
		IF NOT IS_CHAR_IN_ANY_CAR rosenberg_C6
			SET_CHAR_COORDINATES rosenberg_C6 963.8596 2162.6147 1010.0312 
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD rosenberg_C6 963.8596 2162.6147 1010.0312 
		ENDIF
		FREEZE_CHAR_POSITION rosenberg_C6 TRUE
		REMOVE_BLIP rosenberg_blip_C6
		REMOVE_CHAR_FROM_GROUP rosenberg_C6
	ENDIF

	REMOVE_BLIP goto_blip_C6
	
	IF NOT IS_CAR_DEAD player_vehicle_C6
		DELETE_CAR player_vehicle_C6
	ENDIF	
	audio_status_C6 = 0
	audio_pointer_C6 = 0

	CLEAR_AREA_OF_CHARS 930.0 2178.0 1008.0 964.0 2095.0 1014.0

	MAKE_PLAYER_SAFE_FOR_CUTSCENE player1

// **************************************** START OF CUTSCENE 

	SET_AREA_VISIBLE 1

	LOAD_CUTSCENE CAS6b_1
     
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
        LOAD_CUTSCENE CAS6b_2
         
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

// **************************************** END OF CUTSCENE

	
	SWITCH_WIDESCREEN ON 

	//SET_AREA_VISIBLE 1
	LOAD_SCENE 942.8142 2111.4839 1010.0312
	REQUEST_COLLISION 942.8142 2111.4839 

	// player
//	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 968.3954 2160.9651 1.0

	SET_CHAR_COORDINATES scplayer 961.4175 2111.3899 1010.0303
	SET_CHAR_HEADING scplayer 100.0
	FREEZE_CHAR_POSITION scplayer FALSE
	
	player_trapped_C6 = 0

	// ##########################
	// ## Models and animations
	// ##########################

	REQUEST_MODEL VMAFF1
	REQUEST_MODEL VMAFF2
	REQUEST_MODEL VMAFF3 
	REQUEST_MODEL VMAFF4 

	// weapons
	REQUEST_MODEL MOLOTOV
	REQUEST_MODEL MP5LNG
	REQUEST_MODEL CHNSAW
	REQUEST_MODEL BAT
	REQUEST_MODEL FIRE_EX
	REQUEST_MODEL BODYARMOUR

	// objects
	REQUEST_MODEL ab_carcass
	REQUEST_MODEL ab_hook
	REQUEST_MODEL freezer_door
	REQUEST_MODEL sec_keypad
	REQUEST_MODEL cardboardbox2
	REQUEST_MODEL cj_meat_bag_1
	REQUEST_MODEL cj_meat_1
	REQUEST_MODEL cj_meat_2

	// sfx bank
	LOAD_MISSION_AUDIO 3 SOUND_BANK_MEAT_BUSINESS 
	 	
	WHILE NOT HAS_MODEL_LOADED VMAFF1
	OR NOT HAS_MODEL_LOADED VMAFF2
	OR NOT HAS_MODEL_LOADED VMAFF3 
	OR NOT HAS_MODEL_LOADED VMAFF4 
	OR NOT HAS_MODEL_LOADED MOLOTOV
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED MP5LNG
	OR NOT HAS_MODEL_LOADED CHNSAW
	OR NOT HAS_MODEL_LOADED BAT
	OR NOT HAS_MODEL_LOADED FIRE_EX
	OR NOT HAS_MODEL_LOADED BODYARMOUR 
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED ab_carcass
	OR NOT HAS_MODEL_LOADED ab_hook
	OR NOT HAS_MODEL_LOADED freezer_door
	OR NOT HAS_MODEL_LOADED sec_keypad
	OR NOT HAS_MODEL_LOADED cardboardbox2
	OR NOT HAS_MODEL_LOADED cj_meat_bag_1
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED cj_meat_1
	OR NOT HAS_MODEL_LOADED cj_meat_2
	OR NOT HAS_MISSION_AUDIO_LOADED 3 
		WAIT 0
	ENDWHILE




	// check for chainsaw (causes sound issues if more than one)
	GET_CHAR_WEAPON_IN_SLOT scplayer 2 weapon_type_C6 never_run_this_C6 never_run_this_C6
	IF weapon_type_C6 = WEAPONTYPE_CHAINSAW 
		weapon_type_C6 = WEAPONTYPE_BASEBALLBAT
		MARK_MODEL_AS_NO_LONGER_NEEDED CHNSAW
	ELSE
		weapon_type_C6 = WEAPONTYPE_CHAINSAW
	ENDIF
	
	SET_MAX_FIRE_GENERATIONS 0

	
	// ##################
	// ## Mission start
	// ##################

//	//SET_AREA_VISIBLE 1
//	LOAD_SCENE 942.8142 2111.4839 1010.0312
//	REQUEST_COLLISION 942.8142 2111.4839 
//
//	// player
//	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 968.3954 2160.9651 1.0
//
//	SET_CHAR_COORDINATES scplayer 961.4175 2111.3899 1010.0303
//	SET_CHAR_HEADING scplayer 100.0
//	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 24
//	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN
	
	//player_trapped_C6 = 0
	
	IF NOT IS_CHAR_DEAD rosenberg_C6
//	// rosenberg
		SET_CHAR_COORDINATES rosenberg_C6 959.7906 2108.6702 1010.0303
		SET_CHAR_HEADING rosenberg_C6 51.5  
		SET_CHAR_AREA_VISIBLE rosenberg_C6 1
		 
		FREEZE_CHAR_POSITION rosenberg_C6 FALSE
		
		REMOVE_BLIP rosenberg_blip_C6
		ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
		CHANGE_BLIP_DISPLAY rosenberg_blip_C6 BLIP_ONLY
		SET_BLIP_ENTRY_EXIT rosenberg_blip_C6 968.3954 2160.9651 1.0 
		SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE 
		SET_CHAR_DECISION_MAKER rosenberg_C6 empty_dm_C6
	//	SET_CHAR_NEVER_TARGETTED rosenberg_C6 TRUE
	//	SET_CHAR_SUFFERS_CRITICAL_HITS rosenberg_C6 FALSE
		SET_CHAR_HEALTH rosenberg_C6 300
	ENDIF

	DISPLAY_ONSCREEN_COUNTER_WITH_STRING rosenbergs_health_C6 COUNTER_DISPLAY_BAR CAS6_C0

//	pointer_C6 = 0
//	WHILE pointer_C6 < 20
//		rosenberg_text_flag_C6[pointer_C6] = 0
//		pointer_C6 ++
//	ENDWHILE
//
	rosenberg_status_C6 = 0
	rosenberg_timer_C6 = 0
	rosenberg_trapped_C6 = 0
	
	rosenberg_text_timer_C6 = 0

	// fire extinguishers
	CREATE_PICKUP FIRE_EX PICKUP_ONCE 962.9547 2101.9185 1011.0 player_fire_ex_C6
	
	CREATE_OBJECT_NO_OFFSET FIRE_EX 959.9 2098.1 1010.6 fire_ex_C6
	SET_OBJECT_ROTATION fire_ex_C6 0.0 45.0 90.0

	// armour pickup
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 958.4733 2144.2510 1011.0 freezer_armour_C6 
	
	// mission
	SET_RADAR_ZOOM 95
	abattoir_status_C6 = 0
	fail_text_flag_C6 = 0

	// game and help text
	g_text_status_C6 = 0
	h_text_status_C6 = 0
	h_text_timer_C6 = 0


	// ##################
	// ## Meat track
	// ##################

	mt_radius_C6 = 1.062 

	mt_dis_per_degree_C6 = mt_radius_C6 * 2.0
	mt_dis_per_degree_C6 *= 3.14159265 //pie
	mt_dis_per_degree_C6 /= 360.0 
	 
	mt_speed_C6 = 0.0
	mt_accel_C6 = -0.0001 // make this 0.0001 to get meat track moving at start

	mt_stopped_C6 = TRUE
	mt_status_C6 = 0
	mt_timer_C6 = 0

	// coords of meat track
	mt_X_C6[0] = 945.725
	mt_Y_C6[0] = 2175.24 

	mt_X_C6[1] = 946.787
	mt_Y_C6[1] = 2174.185

	mt_X_C6[2] = 946.787
	mt_Y_C6[2] = 2118.155 

	mt_X_C6[3] = 945.725
	mt_Y_C6[3] = 2117.069 

	mt_X_C6[4] = 938.357
	mt_Y_C6[4] = 2117.069 

	mt_X_C6[5] = 937.262
	mt_Y_C6[5] = 2118.155 

	mt_X_C6[6] = 937.262
	mt_Y_C6[6] = 2174.185

	mt_X_C6[7] = 938.357
	mt_Y_C6[7] = 2175.24

	mt_Z_C6 = 1016.288

	CREATE_OBJECT_NO_OFFSET sec_keypad 946.35 2113.5496 1011.5222 mt_switch_C6
	SET_OBJECT_COLLISION mt_switch_C6 FALSE // temp to stop player falling over
	FREEZE_OBJECT_POSITION mt_switch_C6 TRUE
	  
	// carcasses
	// carcass top right
	CREATE_OBJECT_NO_OFFSET cardboardbox2 937.262 2126.155 mt_Z_C6 mt_obj_C6[0]
	SET_OBJECT_DYNAMIC mt_obj_C6[0] FALSE 
	SET_OBJECT_COLLISION mt_obj_C6[0] FALSE
	SET_OBJECT_VISIBLE mt_obj_C6[0] FALSE
	mt_obj_movement_C6[0] = 6
	mt_obj_angle_C6[0] = 0.0
	mt_obj_rot_C6[0] = 0.0 

	CREATE_OBJECT_NO_OFFSET ab_carcass 937.228 2121.157 mt_Z_C6 mt_carcass_C6[0]
	ATTACH_OBJECT_TO_OBJECT mt_carcass_C6[0] mt_obj_C6[0] 0.0 0.0 0.0 0.0 0.0 0.0

	// carcass bottom
	CREATE_OBJECT_NO_OFFSET cardboardbox2 940.356 2175.24 mt_Z_C6 mt_obj_C6[1]
	SET_OBJECT_DYNAMIC mt_obj_C6[1] FALSE 
	SET_OBJECT_COLLISION mt_obj_C6[1] FALSE
	SET_OBJECT_VISIBLE mt_obj_C6[1] FALSE
	mt_obj_movement_C6[1] = 0
	mt_obj_angle_C6[1] = 90.0
	mt_obj_rot_C6[1] = 0.0 
	 
	CREATE_OBJECT_NO_OFFSET ab_carcass 940.356 2175.264 mt_Z_C6 mt_carcass_C6[1]
	ATTACH_OBJECT_TO_OBJECT mt_carcass_C6[1] mt_obj_C6[1] 0.0 0.0 0.0 0.0 0.0 0.0

	// carcass left side
	CREATE_OBJECT_NO_OFFSET cardboardbox2 946.846 2135.264 mt_Z_C6 mt_obj_C6[2]
	SET_OBJECT_DYNAMIC mt_obj_C6[2] FALSE 
	SET_OBJECT_COLLISION mt_obj_C6[2] FALSE
	SET_OBJECT_VISIBLE mt_obj_C6[2] FALSE
	mt_obj_movement_C6[2] = 2
	mt_obj_angle_C6[2] = 0.0
	mt_obj_rot_C6[2] = 0.0 
	 
	CREATE_OBJECT_NO_OFFSET ab_carcass 946.846 2135.264 mt_Z_C6 mt_carcass_C6[2]
	ATTACH_OBJECT_TO_OBJECT mt_carcass_C6[2] mt_obj_C6[2] 0.0 0.0 0.0 0.0 0.0 0.0

	// hooks
	// top
	CREATE_OBJECT_NO_OFFSET ab_hook 941.727 2117.076 mt_Z_C6 mt_obj_C6[3]
	mt_obj_movement_C6[3] = 4
	mt_obj_angle_C6[3] = 90.0
	mt_obj_rot_C6[3] = 45.0 
	SET_OBJECT_ROTATION mt_obj_C6[3] 0.0 0.0 mt_obj_rot_C6[3]  

	// bottom right
	CREATE_OBJECT_NO_OFFSET ab_hook 937.228 2162.24 mt_Z_C6 mt_obj_C6[4]
	mt_obj_movement_C6[4] = 6
	mt_obj_angle_C6[4] = 0.0
	mt_obj_rot_C6[4] = -45.0
	SET_OBJECT_ROTATION mt_obj_C6[4] 0.0 0.0 mt_obj_rot_C6[4]

	// bottom left
	CREATE_OBJECT_NO_OFFSET ab_hook 946.846 2172.24 mt_Z_C6 mt_obj_C6[5]
	mt_obj_movement_C6[5] = 2
	mt_obj_angle_C6[5] = 0.0
	mt_obj_rot_C6[5] = 135.0
	SET_OBJECT_ROTATION mt_obj_C6[5] 0.0 0.0 mt_obj_rot_C6[5]

	// hunks of meat lying around
	LVAR_INT meat_chunks_C6[9]

	CREATE_OBJECT_NO_OFFSET cj_meat_bag_1 933.6226 2118.9478 1012.3132 meat_chunks_C6[0]	 
	SET_OBJECT_HEADING meat_chunks_C6[0] 65.0793 						 
	CREATE_OBJECT_NO_OFFSET cj_meat_bag_1 933.1715 2133.3657 1012.7674 meat_chunks_C6[1]	
	SET_OBJECT_HEADING meat_chunks_C6[1] 65.0783 						
	CREATE_OBJECT_NO_OFFSET cj_meat_bag_1 942.9017 2155.6440 1013.4786 meat_chunks_C6[2]	
	SET_OBJECT_HEADING meat_chunks_C6[2] 64.6149							
	CREATE_OBJECT_NO_OFFSET cj_meat_bag_1 950.8132 2166.6699 1012.2961 meat_chunks_C6[3]	
	SET_OBJECT_HEADING meat_chunks_C6[3] 88.7010							

	CREATE_OBJECT_NO_OFFSET cj_meat_2 942.7145 2154.6328 1011.2128 meat_chunks_C6[4]	
	SET_OBJECT_HEADING meat_chunks_C6[4] 73.9730
	CREATE_OBJECT_NO_OFFSET cj_meat_2 950.4804 2155.5720 1011.9017 meat_chunks_C6[5]	
	SET_OBJECT_HEADING meat_chunks_C6[5] 131.2515

	CREATE_OBJECT_NO_OFFSET cj_meat_2 942.3651 2118.8679 1011.2410 meat_chunks_C6[6]	
	SET_OBJECT_HEADING meat_chunks_C6[6] 64.6148 						
	CREATE_OBJECT_NO_OFFSET cj_meat_2 950.6234 2132.0959 1011.9471 meat_chunks_C6[7]	
	SET_OBJECT_HEADING meat_chunks_C6[7] 24.2141
	CREATE_OBJECT_NO_OFFSET cj_meat_2 934.5715 2171.1729 1011.2660 meat_chunks_C6[8]	
	SET_OBJECT_HEADING meat_chunks_C6[8] 354.6342
		 
	// ############
	// ## freezer
	// ############
	
	CREATE_OBJECT_NO_OFFSET freezer_door 951.859 2121.648 1012.094 fd_C6
	SET_OBJECT_PROOFS fd_C6 TRUE TRUE TRUE TRUE TRUE
	CREATE_OBJECT_NO_OFFSET sec_keypad 950.9305 2114.3477 1011.5222 fd_switch_C6
	SET_OBJECT_HEADING fd_switch_C6 180.0
	FREEZE_OBJECT_POSITION fd_switch_C6 TRUE
	SET_OBJECT_COLLISION fd_switch_C6 FALSE // TEMP stop player falling over		
	fd_status_C6 = 0 // open
	fd_speed_C6 = 0.0
	fd_accel_C6	= 0.001

	mafia_using_switch_C6 = -1
	// attractor sequence for door switch
	OPEN_SEQUENCE_TASK fd_attractor_seq_C6
		TASK_ACHIEVE_HEADING -1 180.0
		TASK_STAND_STILL -1 800
	CLOSE_SEQUENCE_TASK fd_attractor_seq_C6	

	// carcasses inside freezer
	CREATE_OBJECT_NO_OFFSET ab_carcass 962.4035 2119.8171 mt_Z_C6 f_carcass_C6[0]
	CREATE_OBJECT_NO_OFFSET ab_carcass 962.4035 2121.6411 mt_Z_C6 f_carcass_C6[1]
	CREATE_OBJECT_NO_OFFSET ab_carcass 962.4035 2124.8420 mt_Z_C6 f_carcass_C6[2]
	
	CREATE_OBJECT_NO_OFFSET ab_carcass 955.9693 2128.8171 mt_Z_C6 f_carcass_C6[3]
	CREATE_OBJECT_NO_OFFSET ab_carcass 955.9693 2131.6411 mt_Z_C6 f_carcass_C6[4]
	CREATE_OBJECT_NO_OFFSET ab_carcass 955.9693 2134.8420 mt_Z_C6 f_carcass_C6[5]
	

	// ###############
	// ## mafia guys
	// ###############

LVAR_INT  molotov_C6[2] molotov_particleFX_C6[3] script_fire_C6[6] script_fire_check_C6[3]

LVAR_FLOAT mol_X_C6 mol_Y_C6 mol_Z_C6 mol_rot_X_C6 mol_rot_Y_C6 mol_rot_Z_C6  
LVAR_FLOAT mol_speed_C6 mol_speed_Z_C6 mol_acc_C6 mol_acc_Z_C6 dis_thrown_C6 dis_thrown_Z_C6
LVAR_FLOAT time_scale_C6


	number_of_mafia_C6 = 29
	mafia_dead_C6 = 0

	pointer_C6 = 0
	WHILE pointer_C6 < number_of_mafia_C6
		mafia_status_C6[pointer_C6] = -1
		pointer_C6 ++
	ENDWHILE

	// guy at start
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 937.3484 2112.0779 1010.0312 mafia_C6[0]
	SET_CHAR_HEADING mafia_C6[0] 264.0 
	SET_CHAR_HEALTH mafia_C6[0] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[0] 200
	ADD_BLIP_FOR_CHAR mafia_C6[0] mafia_blip_C6[0]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[0] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[0] 968.3954 2160.9651 1.0
	TASK_STAY_IN_SAME_PLACE mafia_C6[0] TRUE
	GIVE_WEAPON_TO_CHAR mafia_C6[0] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[0] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[0] 30
	SET_CHAR_DECISION_MAKER mafia_C6[0] empty_dm_C6
	mafia_status_C6[0] = 0

	// guy at start
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF4 937.1350 2108.9404 1010.0312 mafia_C6[1]
	SET_CHAR_HEADING mafia_C6[1] 271.5
	SET_CHAR_HEALTH mafia_C6[1] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[1] 200
	ADD_BLIP_FOR_CHAR mafia_C6[1] mafia_blip_C6[1]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[1] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[1] 968.3954 2160.9651 1.0
	TASK_STAY_IN_SAME_PLACE mafia_C6[1] TRUE
	GIVE_WEAPON_TO_CHAR mafia_C6[1] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[1] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[1] 30
	SET_CHAR_DECISION_MAKER mafia_C6[1] empty_dm_C6
	mafia_status_C6[1] = 0

	// first guy to jump through the fire
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 939.3190 2124.8882 1010.0312 mafia_C6[2] 
	SET_CHAR_HEADING mafia_C6[2] 195.0
	SET_CHAR_HEALTH mafia_C6[0] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[0] 200

	ADD_BLIP_FOR_CHAR mafia_C6[2] mafia_blip_C6[2]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[2] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[2] 968.3954 2160.9651 1.0
	GIVE_WEAPON_TO_CHAR mafia_C6[2] weapon_type_C6 0
	SET_CURRENT_CHAR_WEAPON mafia_C6[2] weapon_type_C6
	mafia_status_C6[2] = 0

	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		TASK_TOGGLE_DUCK -1 FALSE 
		TASK_STAND_STILL -1 800
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		TASK_GO_STRAIGHT_TO_COORD -1 940.5833 2116.1689 1010.0312 PEDMOVE_RUN -1
		TASK_JUMP -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 rosenberg_C6
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK mafia_C6[2] sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6

	// second guy to jump through the fire
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 937.8190 2137.4871 1010.0303 mafia_C6[3] 
	SET_CHAR_HEADING mafia_C6[3] 188.0
	SET_CHAR_HEALTH mafia_C6[0] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[0] 200
	ADD_BLIP_FOR_CHAR mafia_C6[3] mafia_blip_C6[3]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[3] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[3] 968.3954 2160.9651 1.0
	GIVE_WEAPON_TO_CHAR mafia_C6[3] WEAPONTYPE_BASEBALLBAT 0
	SET_CURRENT_CHAR_WEAPON mafia_C6[3] WEAPONTYPE_BASEBALLBAT
	SET_CHAR_ACCURACY mafia_C6[3] 35
	mafia_status_C6[3] = 0

	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		TASK_TOGGLE_DUCK -1 FALSE 
		TASK_STAND_STILL -1 800
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		TASK_GO_STRAIGHT_TO_COORD -1 940.5833 2116.1689 1010.0312 PEDMOVE_RUN -1
		TASK_JUMP -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 rosenberg_C6
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK mafia_C6[3] sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6	

	// guy who throws molotov in cutscene
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF4 948.0 2124.0 1010.0312 mafia_C6[4] 
	SET_CHAR_HEADING mafia_C6[4] 135.0
	SET_CHAR_HEALTH mafia_C6[4] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[4] 200
	ADD_BLIP_FOR_CHAR mafia_C6[4] mafia_blip_C6[4]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[4] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[4] 968.3954 2160.9651 1.0
	TASK_STAY_IN_SAME_PLACE mafia_C6[4] TRUE
	SET_CHAR_ACCURACY mafia_C6[4] 30
	mafia_status_C6[4] = 0

	CREATE_OBJECT_NO_OFFSET MOLOTOV 948.0 2124.0 1010.0312 molotov_C6[0]
	SET_OBJECT_DYNAMIC molotov_C6[0] FALSE
	SET_OBJECT_COLLISION molotov_C6[0] FALSE
	TASK_PICK_UP_OBJECT mafia_C6[4] molotov_C6[0] 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE	
	CREATE_FX_SYSTEM_ON_OBJECT fire molotov_C6[0] 0.0 0.0 0.6 TRUE molotov_particleFX_C6[0]
		
	OPEN_SEQUENCE_TASK sequence_C6
		TASK_STAND_STILL -1 200
		TASK_PLAY_ANIM -1 UZI_reload UZI 4.0 FALSE FALSE FALSE FALSE 300
		TASK_PLAY_ANIM -1 WEAPON_start_throw GRENADE 10.0 FALSE FALSE FALSE FALSE -1	
		TASK_PLAY_ANIM -1 WEAPON_throw GRENADE 10.0 FALSE FALSE FALSE FALSE -1
		TASK_STAND_STILL -1 500
		TASK_GO_TO_COORD_ANY_MEANS -1 944.0320 2119.9067 1010.0312 PEDMOVE_RUN -1 
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_STAY_IN_SAME_PLACE -1 TRUE
	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK mafia_C6[4] sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6	

	CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 940.7016 2138.9333 1010.0312 mafia_C6[5]
	SET_CHAR_HEADING mafia_C6[5] 180.0
	SET_CHAR_HEALTH mafia_C6[5] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[5] 200
	ADD_BLIP_FOR_CHAR mafia_C6[5] mafia_blip_C6[5]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[5] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[5] 968.3954 2160.9651 1.0
	GIVE_WEAPON_TO_CHAR mafia_C6[5] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[5] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[5] 30
	mafia_status_C6[5] = 0
	seq_performer_C6 = mafia_C6[5]
	seq_duck_start_C6 =	FALSE
	seq_duck_end_C6 = TRUE
	seq_goto_X_C6 =	940.8504
	seq_goto_Y_C6 = 2120.2097
	seq_goto_Z_C6 =	1010.0312	
	seq_heading_C6 = 184.0
	GOSUB mission_casino6_SUB_ped_goto_and_stay

	CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 938.9517 2132.4031 1010.0312 mafia_C6[6]
	SET_CHAR_HEADING mafia_C6[6] 170.0
	SET_CHAR_HEALTH mafia_C6[6] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[6] 200
	ADD_BLIP_FOR_CHAR mafia_C6[6] mafia_blip_C6[6]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[6] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[6] 968.3954 2160.9651 1.0
	GIVE_WEAPON_TO_CHAR mafia_C6[6] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[6] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[6] 30
	mafia_status_C6[6] = 0
	seq_performer_C6 = mafia_C6[6]
	seq_duck_start_C6 =	FALSE
	seq_duck_end_C6 = TRUE
	seq_goto_X_C6 =	933.7762
	seq_goto_Y_C6 = 2120.5891
	seq_goto_Z_C6 =	1010.0312	
	seq_heading_C6 = 230.0
	GOSUB mission_casino6_SUB_ped_goto_and_stay

	// guy on machine
	CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 942.4279 2129.2620 1011.2277 mafia_C6[7]
	SET_CHAR_HEADING mafia_C6[7] 180.0
	SET_CHAR_HEALTH mafia_C6[7] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[7] 200
	ADD_BLIP_FOR_CHAR mafia_C6[7] mafia_blip_C6[7]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[7] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[7] 968.3954 2160.9651 1.0
	TASK_TOGGLE_DUCK mafia_C6[7] TRUE
	TASK_STAY_IN_SAME_PLACE mafia_C6[7] TRUE
	GIVE_WEAPON_TO_CHAR mafia_C6[7] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[7] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[7] 30
	//SET_CHAR_DECISION_MAKER mafia_C6[7] attack_dm_C6
	mafia_status_C6[7] = 0

	CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 941.8554 2141.0500 1010.0303 mafia_C6[8]
	SET_CHAR_HEADING mafia_C6[8] 198.3264
	SET_CHAR_HEALTH mafia_C6[8] 200	
	SET_CHAR_MAX_HEALTH mafia_C6[8] 200
	ADD_BLIP_FOR_CHAR mafia_C6[8] mafia_blip_C6[8]
	CHANGE_BLIP_DISPLAY mafia_blip_C6[8] BLIP_ONLY
	SET_BLIP_ENTRY_EXIT mafia_blip_C6[8] 968.3954 2160.9651 1.0
	GIVE_WEAPON_TO_CHAR mafia_C6[8] WEAPONTYPE_MP5 3000
	SET_CURRENT_CHAR_WEAPON mafia_C6[8] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY mafia_C6[8] 30
	mafia_status_C6[8] = 0
	seq_performer_C6 = mafia_C6[8]
	seq_duck_start_C6 =	FALSE
	seq_duck_end_C6 = TRUE
	seq_goto_X_C6 =	950.5577	     
	seq_goto_Y_C6 = 2126.7339
	seq_goto_Z_C6 =	1010.0312	
	seq_heading_C6 = 150.0
	GOSUB mission_casino6_SUB_ped_goto_and_stay

	SET_FIXED_CAMERA_POSITION 946.2746 2122.1992 1011.2625 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 947.4866 2124.3857 1011.2625 JUMP_CUT
	
	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

	CLEAR_WANTED_LEVEL player1
	SET_MAX_WANTED_LEVEL 0
	
	cutscene_status_C6 = 0

	WAIT 0
		
	time_scale_C6 = 0.2

    SET_TIME_SCALE time_scale_C6
	TIMERA = 0
	WHILE TIMERA < 100
		WAIT 0
	ENDWHILE
	DO_FADE 0 FADE_IN

SKIP_CUTSCENE_START

	mol_speed_C6 = 0.5 * time_scale_C6
	mol_acc_C6 = -0.0002 * time_scale_C6
	dis_thrown_C6 = 0.8 * time_scale_C6

	mol_speed_Z_C6 = 0.3 * time_scale_C6	
	mol_acc_Z_C6 = -0.03 * time_scale_C6
	mol_acc_Z_C6 *= time_scale_C6
	dis_thrown_Z_C6 = 0.0


	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE
	
	 

	PLAY_FX_SYSTEM molotov_particleFX_C6[0]

	//WHILE TIMERA < 1290
	
	WHILE TIMERA < 1390
		WAIT 0
	ENDWHILE
	
	IF NOT IS_CHAR_DEAD mafia_C6[4]
		GET_OBJECT_COORDINATES molotov_C6[0] mol_X_C6 mol_Y_C6 mol_Z_C6

		DROP_OBJECT mafia_C6[4] FALSE
		KILL_FX_SYSTEM molotov_particleFX_C6[0]
		DELETE_OBJECT molotov_C6[0] 

		cutscene_status_C6 = 1

		CREATE_OBJECT_NO_OFFSET MOLOTOV mol_X_C6 mol_Y_C6 mol_Z_C6 molotov_C6[1]
		CREATE_FX_SYSTEM_ON_OBJECT fire molotov_C6[1] 0.0 0.0 0.6 TRUE molotov_particleFX_C6[1]
		PLAY_FX_SYSTEM molotov_particleFX_C6[1]
		mol_rot_X_C6 = 0.0
		mol_rot_Y_C6 = 0.0
		mol_rot_Z_C6 = 0.0
	ENDIF

	COS 241.0 cos_angle_C6
	SIN 241.0 sin_angle_C6

LVAR_FLOAT camera_pos_dis_C6 camera_look_dis_C6
	
	z = 5000.0
	WHILE NOT z <= 1010.0312
	
		WAIT 0
		
		mol_speed_C6 += mol_acc_C6
		dis_thrown_C6 += mol_speed_C6
		
		// molotov			
		new_X_C6 = dis_thrown_C6 * cos_angle_C6
		new_Y_C6 = dis_thrown_C6 * sin_angle_C6
		mol_speed_Z_C6 += mol_acc_Z_C6
		dis_thrown_Z_C6 += mol_speed_Z_C6
		x = mol_X_C6 + new_X_C6
		y = mol_Y_C6 + new_Y_C6
		z = mol_Z_C6 + dis_thrown_Z_C6
		SET_OBJECT_COORDINATES molotov_C6[1] x y z
		
		mol_rot_X_C6 += 1.0
		mol_rot_Y_C6 += 1.0
		mol_rot_Z_C6 += 1.0 
		SET_OBJECT_ROTATION molotov_C6[1] mol_rot_X_C6 mol_rot_Y_C6 mol_rot_Z_C6 
		
		// camera
		camera_pos_dis_C6 = dis_thrown_C6 + 1.5 
		camera_look_dis_C6 = dis_thrown_C6 - 1.0

		new_X_C6 = camera_pos_dis_C6 * cos_angle_C6
		new_Y_C6 = camera_pos_dis_C6 * sin_angle_C6
		x = mol_X_C6 + new_X_C6
		y = mol_Y_C6 + new_Y_C6
		SET_FIXED_CAMERA_POSITION x y mol_Z_C6 0.0 0.0 0.0
	
		new_X_C6 = camera_look_dis_C6 * cos_angle_C6
		new_Y_C6 = camera_look_dis_C6 * sin_angle_C6
		x = mol_X_C6 + new_X_C6
		y = mol_Y_C6 + new_Y_C6
		POINT_CAMERA_AT_POINT x y mol_Z_C6 JUMP_CUT

		// molotov has landed
		IF z <= 1010.5
			FREEZE_OBJECT_POSITION molotov_C6[1] TRUE 
		ENDIF

	ENDWHILE

	// kill molotov and make explosion
	KILL_FX_SYSTEM molotov_particleFX_C6[1]
	DELETE_OBJECT molotov_C6[1]
	MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV

	cutscene_status_C6 = 2

	CREATE_FX_SYSTEM explosion_molotov x y 1010.0 TRUE molotov_particleFX_C6[2]
	PLAY_AND_KILL_FX_SYSTEM molotov_particleFX_C6[2] 
	SET_HEATHAZE_EFFECT TRUE

   	START_SCRIPT_FIRE 940.35 2114.0 1010.03 0 2 script_fire_C6[0]
	cutscene_status_C6 = 3
	START_SCRIPT_FIRE 941.1 2114.0 1010.03 0 1 script_fire_C6[1]
	cutscene_status_C6 = 4

	START_SCRIPT_FIRE 941.5 2114.0 1010.03 0 1 script_fire_C6[2]
	cutscene_status_C6 = 5
	START_SCRIPT_FIRE 942.3 2114.0 1010.03 0 1 script_fire_C6[3]
	cutscene_status_C6 = 6

	START_SCRIPT_FIRE 942.9 2114.0 1010.03 0 1 script_fire_C6[4]
	cutscene_status_C6 = 7
	START_SCRIPT_FIRE 943.65 2114.0 1010.03 0 2 script_fire_C6[5]
	cutscene_status_C6 = 8
	// create big fire here

	TIMERA = 0

	WHILE TIMERA < 220
		
		WAIT 0
		
		dis_thrown_C6 += mol_speed_C6
		camera_pos_dis_C6 = dis_thrown_C6 + 1.5 
		camera_look_dis_C6 = dis_thrown_C6 - 1.0
		
		new_X_C6 = camera_pos_dis_C6 * cos_angle_C6
		new_Y_C6 = camera_pos_dis_C6 * sin_angle_C6
		x = mol_X_C6 + new_X_C6
		y = mol_Y_C6 + new_Y_C6
		SET_FIXED_CAMERA_POSITION x y mol_Z_C6 0.0 0.0 0.0

		new_X_C6 = camera_look_dis_C6 * cos_angle_C6
		new_Y_C6 = camera_look_dis_C6 * sin_angle_C6
		x = mol_X_C6 + new_X_C6
		y = mol_Y_C6 + new_Y_C6
		POINT_CAMERA_AT_POINT x y mol_Z_C6 JUMP_CUT

	ENDWHILE

LVAR_FLOAT camera_angle_C6 

	camera_angle_C6 = 61.0
	GET_ACTIVE_CAMERA_COORDINATES x2_C6 y2_C6 z2_C6 

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE
		
	TIMERA = 0
	WHILE TIMERA < 1300

		WAIT 0

		COS camera_angle_C6 cos_angle_C6
		SIN camera_angle_C6 sin_angle_C6

		new_X_C6 = camera_look_dis_C6 * cos_angle_C6
		new_Y_C6 = camera_look_dis_C6 * sin_angle_C6
		x = x2_C6 + new_X_C6
		y = y2_C6 + new_Y_C6
		POINT_CAMERA_AT_POINT x y mol_Z_C6 JUMP_CUT
		
		camera_angle_C6 -= 0.25
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_END

	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	
	// cutscene tidyup if it was skipped
	SWITCH cutscene_status_C6

		CASE 0
			IF NOT IS_CHAR_DEAD mafia_C6[4] 
				DROP_OBJECT mafia_C6[4] FALSE
			ENDIF
			KILL_FX_SYSTEM molotov_particleFX_C6[0]
			DELETE_OBJECT molotov_C6[0]
		CASE 1
			KILL_FX_SYSTEM molotov_particleFX_C6[1]
			DELETE_OBJECT molotov_C6[1]
			MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
		CASE 2
			START_SCRIPT_FIRE 940.35 2114.0 1010.03 0 2 script_fire_C6[0]
		CASE 3
			START_SCRIPT_FIRE 941.1 2114.0 1010.03 0 1 script_fire_C6[1]
		CASE 4
			START_SCRIPT_FIRE 941.5 2114.0 1010.03 0 1 script_fire_C6[2]
		CASE 5
			START_SCRIPT_FIRE 942.3 2114.0 1010.03 0 1 script_fire_C6[3]
		CASE 6
			START_SCRIPT_FIRE 942.9 2114.0 1010.03 0 1 script_fire_C6[4]
		CASE 7
			START_SCRIPT_FIRE 943.65 2114.0 1010.03 0 2 script_fire_C6[5]
		BREAK

	ENDSWITCH

 	KILL_FX_SYSTEM molotov_particleFX_C6[2]

	CLEAR_HELP
	PRINT_HELP CAS6_G0
	TIMERA = 0

	SET_HEATHAZE_EFFECT FALSE
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	
	SWITCH_ENTRY_EXIT abatoir FALSE

	// script fire control
	script_fire_check_C6[0] = 0
	script_fire_check_C6[1] = 0
	script_fire_check_C6[2] = 0

	SET_TIME_SCALE 1.0

	IF NOT IS_CHAR_DEAD mafia_C6[0]
		mafia_status_C6[0] = 0
		seq_performer_C6 = mafia_C6[0]
		seq_duck_start_C6 =	FALSE
		seq_goto_X_C6 =	945.2795
		seq_goto_Y_C6 = 2108.6575
		seq_goto_Z_C6 =	1010.0312
		seq_attack_C6 = scplayer
		GOSUB mission_casino6_SUB_ped_goto_and_attack
	ENDIF
	
	IF NOT IS_CHAR_DEAD mafia_C6[1]
		mafia_status_C6[1] = 0
		seq_performer_C6 = mafia_C6[1]
		seq_duck_start_C6 =	FALSE
		seq_goto_X_C6 =	937.1350  
		seq_goto_Y_C6 = 2108.9404
		seq_goto_Z_C6 =	1010.0312
		seq_attack_C6 = scplayer
		GOSUB mission_casino6_SUB_ped_goto_and_attack
	ENDIF

	IF NOT IS_CHAR_DEAD mafia_C6[2]
		CLEAR_CHAR_TASKS mafia_C6[2]
		SET_CHAR_COORDINATES mafia_C6[2] 941.4810 2111.8875 1010.0312 
		SET_CHAR_HEADING mafia_C6[2] 260.0
		IF NOT IS_CHAR_DEAD rosenberg_C6
			TASK_KILL_CHAR_ON_FOOT mafia_C6[2] rosenberg_C6
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD mafia_C6[3]
		CLEAR_CHAR_TASKS mafia_C6[3]
		SET_CHAR_COORDINATES mafia_C6[3] 939.6515 2123.3062 1010.0312 
		SET_CHAR_HEADING mafia_C6[3] 187.5

		OPEN_SEQUENCE_TASK sequence_C6
			
			TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
			TASK_TOGGLE_DUCK -1 FALSE 
			TASK_STAND_STILL -1 800
			TASK_STAY_IN_SAME_PLACE -1 FALSE
			TASK_GO_STRAIGHT_TO_COORD -1 940.5833 2116.1689 1010.0312 PEDMOVE_RUN -1
			TASK_JUMP -1 TRUE
			IF NOT IS_CHAR_DEAD rosenberg_C6
				TASK_KILL_CHAR_ON_FOOT -1 rosenberg_C6
			ENDIF
			TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

		CLOSE_SEQUENCE_TASK sequence_C6	
		
		PERFORM_SEQUENCE_TASK mafia_C6[3] sequence_C6
		CLEAR_SEQUENCE_TASK sequence_C6

	ENDIF

	IF NOT IS_CHAR_DEAD mafia_C6[4]
		CLEAR_CHAR_TASKS mafia_C6[4] 
		SET_CHAR_COORDINATES mafia_C6[4] 944.0320 2119.9067 1010.0312
		SET_CHAR_HEADING mafia_C6[4] 160.0
		TASK_TOGGLE_DUCK mafia_C6[4] TRUE
		TASK_STAY_IN_SAME_PLACE mafia_C6[4] TRUE
		GIVE_WEAPON_TO_CHAR mafia_C6[4] WEAPONTYPE_MP5 3000
		SET_CURRENT_CHAR_WEAPON mafia_C6[4] WEAPONTYPE_MP5
		//SET_CHAR_DECISION_MAKER mafia_C6[4] attack_dm_C6
		TASK_KILL_CHAR_ON_FOOT mafia_C6[4] scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD mafia_C6[7]
		TASK_KILL_CHAR_ON_FOOT mafia_C6[7] scplayer
	ENDIF

	$audio_text_label_C6[0] = &CAS6_EP		// shit, they have started a fire to keep us back
	audio_sound_label_C6[0]	= SOUND_CAS6_EP
	audio_speaker_C6[0] 	= scplayer
	$audio_text_label_C6[1] = &CAS6_EQ		// there must be an extinguisher here somewhere find it
	audio_sound_label_C6[1]	= SOUND_CAS6_EQ
	audio_speaker_C6[1] 	= scplayer
	$audio_text_label_C6[2] = &CAS6_ED		// Carl, leave no witnesses
	audio_sound_label_C6[2]	= SOUND_CAS6_ED
	audio_speaker_C6[2] 	= rosenberg_C6

	$audio_text_label_C6[3] = &CAS6_DA		// What did I say what did I do
	audio_sound_label_C6[3]	= SOUND_CAS6_DA
	audio_speaker_C6[3] 	= rosenberg_C6
	$audio_text_label_C6[4] = &CAS6_DB		// Nothing My bad, Now take cover
	audio_sound_label_C6[4]	= SOUND_CAS6_DB
	audio_speaker_C6[4] 	= scplayer
	$audio_text_label_C6[5] = &CAS6_ET		// This is so exciting tommy
	audio_sound_label_C6[5]	= SOUND_CAS6_ET
	audio_speaker_C6[5] 	= rosenberg_C6
	$audio_text_label_C6[6] = &CAS6_EU		// who the fuck is tommy
	audio_sound_label_C6[6]	= SOUND_CAS6_EU
	audio_speaker_C6[6] 	= scplayer

	$audio_text_label_C6[7] = &CAS6_EA		// Holy fuck what is it about my luck 		
	audio_sound_label_C6[7]	= SOUND_CAS6_EA
	audio_speaker_C6[7] 	= rosenberg_C6	
	$audio_text_label_C6[8] = &CAS6_EB		// Im screwed
	audio_sound_label_C6[8]	= SOUND_CAS6_EB
	audio_speaker_C6[8] 	= rosenberg_C6
	$audio_text_label_C6[9] = &CAS6_EC		// We gotta do something
	audio_sound_label_C6[9]	= SOUND_CAS6_EC
	audio_speaker_C6[9] 	= rosenberg_C6
	$audio_text_label_C6[10]= &CAS6_GH		// Well done CJ 		
	audio_sound_label_C6[10]= SOUND_CAS6_GH	
	audio_speaker_C6[10] 	= rosenberg_C6
	$audio_text_label_C6[11]= &CAS6_GJ		// Shit this is insane
	audio_sound_label_C6[11]= SOUND_CAS6_GJ
	audio_speaker_C6[11] 	= rosenberg_C6
	$audio_text_label_C6[12]= &CAS6_GK		// Im never doing drigs again, never ever ever ever
	audio_sound_label_C6[12]= SOUND_CAS6_GK
	audio_speaker_C6[12] 	= rosenberg_C6
	$audio_text_label_C6[13]= &CAS6_FM		// The exit is through the stock house
	audio_sound_label_C6[13]= SOUND_CAS6_FM
	audio_speaker_C6[13] 	= rosenberg_C6

// ######################################
// ##
// ## inside the meat factory MAIN LOOP
// ##
// ######################################

	GET_GAME_TIMER last_frame_time_C6

LVAR_INT active_area_C6
mission_casino6_MAIN_inside_abattoir:

	WAIT 0

	IF NOT IS_CHAR_DEAD rosenberg_C6
		// TEMP
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
			SWITCH_ENTRY_EXIT abatoir TRUE
			
			entry_exit_area = 0
			entry_exit_status = FALSE
			$entry_exit_name = abatoir
			START_NEW_SCRIPT switch_entry_exit_after_mission

	        GOTO mission_casino6_PASSED  
		ENDIF

		// check for player and rosenberg getting trapped
		IF fail_text_flag_C6 = 2
			GOTO mission_casino6_FAILED
		ENDIF
		
		
		//timers
		GET_GAME_TIMER this_frame_time_C6
		time_diff_C6 = this_frame_time_C6 - last_frame_time_C6 
		last_frame_time_C6 = this_frame_time_C6

		// update custom timers
		rosenberg_timer_C6 += time_diff_C6
		rosenberg_text_timer_C6 += time_diff_C6
		mt_timer_C6 += time_diff_C6
		f_timer_C6 += time_diff_C6
		h_text_timer_C6 += time_diff_C6

		// status controllers
		GOSUB mission_casino6_SUB_abattoir_status
		GOSUB mission_casino6_SUB_rosenberg_status
		
		// make mafia lock player and/or rosenberg in freezer
		GOSUB mission_casino6_SUB_mafia_freezer_check

		//ambient
		GOSUB mission_casino6_SUB_track_movement
		GOSUB mission_casino6_SUB_freezer_door
		IF NOT heathaze_active_C6 = 2 // all fires have been put out
			GOSUB mission_casino6_SUB_heathaze_effect
		ENDIF

		// update rosenbergs health counter
		GET_CHAR_HEALTH rosenberg_C6 pointer_C6
		rosenbergs_health_C6 = pointer_C6 / 3
				
		// remove mafia blips if dead
		IF NOT mafia_dead_C6 = number_of_mafia_C6
		  	pointer_C6 = 0
			WHILE pointer_C6 < number_of_mafia_C6
				IF NOT mafia_status_C6[pointer_C6] = -1
					IF IS_CHAR_DEAD mafia_C6[pointer_C6]
						MARK_CHAR_AS_NO_LONGER_NEEDED mafia_C6[pointer_C6]
						REMOVE_BLIP mafia_blip_C6[pointer_C6]
						mafia_status_C6[pointer_C6] = -1
						mafia_dead_C6 ++
						IF mafia_dead_C6 = number_of_mafia_C6 
							SET_CHAR_DECISION_MAKER rosenberg_C6 empty_dm_C6
						ENDIF
					ENDIF
				ENDIF
				pointer_C6 ++
			ENDWHILE	
		ELSE
			GOSUB mission_casino6_SUB_game_text			 
			
			GET_AREA_VISIBLE active_area_C6
			IF active_area_C6 = 0
				GOTO mission_casino6_MAIN_drive_back_to_casino
			ENDIF
		ENDIF

		// ambient audio
		GOSUB mission_casino6_SUB_ambient_audio

		// freezer health check
		GOSUB mission_casino6_SUB_freezer_health_C6

		// help text
		GOSUB mission_casino6_SUB_help_text
									
	ELSE
		IF fail_text_flag_C6 = 0
			fail_text_flag_C6 = 1
			$fail_text_C6 = CAS6_F0
		ENDIF
		GOTO mission_casino6_FAILED
	ENDIF

GOTO mission_casino6_MAIN_inside_abattoir 




// ####################
// ## abattoir status
// ####################

// controls where the player is inside the abattoir and controls rosenberg and mafia accordingly 
LVAR_INT abattoir_status_C6
mission_casino6_SUB_abattoir_status:

	SWITCH abattoir_status_C6
	
		CASE 0 
			IF IS_CHAR_IN_AREA_2D scplayer 935.0 2114.0 947.0 2116.0 FALSE
				
				abattoir_status_C6 ++
				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 944.9470 2146.8210 1010.0303 mafia_C6[9]
				SET_CHAR_HEADING mafia_C6[9] 180.0
				SET_CHAR_HEALTH mafia_C6[9] 200	
				SET_CHAR_MAX_HEALTH mafia_C6[9] 200
				ADD_BLIP_FOR_CHAR mafia_C6[9] mafia_blip_C6[9]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[9] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[9] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[9] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[9] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[9] 35
				mafia_status_C6[9] = 0
				seq_performer_C6 = mafia_C6[9]
				seq_duck_start_C6 =	FALSE
				seq_duck_end_C6 = FALSE
				seq_goto_X_C6 =	950.4513
				seq_goto_Y_C6 = 2133.9385
				seq_goto_Z_C6 =	1010.0303
				seq_heading_C6 = 156.0
				GOSUB mission_casino6_SUB_ped_goto_and_stay
				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 938.7369 2146.9275 1010.0312 mafia_C6[10]
				SET_CHAR_HEADING mafia_C6[10] 180.0
				SET_CHAR_HEALTH mafia_C6[10] 200	
				SET_CHAR_MAX_HEALTH mafia_C6[10] 200
				ADD_BLIP_FOR_CHAR mafia_C6[10] mafia_blip_C6[10]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[10] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[10] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[10] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[10] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[10] 40
				mafia_status_C6[10] = 0
				seq_performer_C6 = mafia_C6[10]
				seq_duck_start_C6 =	FALSE
				seq_duck_end_C6 = FALSE
				seq_goto_X_C6 =	935.2033
				seq_goto_Y_C6 = 2135.9231
				seq_goto_Z_C6 =	1010.0312
				seq_heading_C6 = 195.0
				GOSUB mission_casino6_SUB_ped_goto_and_stay
				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 938.8906 2150.7195 1010.0312 mafia_C6[11]
				SET_CHAR_HEADING mafia_C6[11] 222.0
				SET_CHAR_HEALTH mafia_C6[11] 200	
				SET_CHAR_MAX_HEALTH mafia_C6[11] 200
				ADD_BLIP_FOR_CHAR mafia_C6[11] mafia_blip_C6[11]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[11] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[11] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[11] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[11] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[11] 35
				mafia_status_C6[11] = 0
				seq_performer_C6 = mafia_C6[11]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	941.6102
				seq_goto_Y_C6 = 2144.1328
				seq_goto_Z_C6 =	1010.0303
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 951.4207 2146.3667 1010.0303 mafia_C6[12]
				SET_CHAR_HEADING mafia_C6[12] 148.0
				ADD_BLIP_FOR_CHAR mafia_C6[12] mafia_blip_C6[12]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[12] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[12] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[12] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[12] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[12] 40
				SET_CHAR_SHOOT_RATE mafia_C6[12] 60
				mafia_status_C6[12] = 0
				seq_performer_C6 = mafia_C6[12]
				seq_duck_start_C6 =	FALSE
				seq_duck_end_C6 = FALSE
				seq_goto_X_C6 =	946.5566
				seq_goto_Y_C6 = 2141.3943
				seq_goto_Z_C6 =	1010.0312
				seq_heading_C6 = 180.0
				GOSUB mission_casino6_SUB_ped_goto_and_stay

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 932.7236 2146.7837 1010.0312 mafia_C6[13]
				SET_CHAR_HEADING mafia_C6[13] 220.0
				SET_CHAR_HEALTH mafia_C6[13] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[13] 150
				ADD_BLIP_FOR_CHAR mafia_C6[13] mafia_blip_C6[13]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[13] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[13] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[13] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[13] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[13] 35
				mafia_status_C6[13] = 0
				seq_performer_C6 = mafia_C6[13]
				seq_duck_start_C6 =	FALSE
				seq_duck_end_C6 = FALSE
				seq_goto_X_C6 =	937.0684
				seq_goto_Y_C6 = 2141.9956
				seq_goto_Z_C6 =	1010.0312
				seq_heading_C6 = 180.0
				GOSUB mission_casino6_SUB_ped_goto_and_stay

				IF NOT IS_CHAR_DEAD mafia_C6[0]
					IF rosenberg_status_C6 < 2
						seq_goto_X_C6 =	940.4404
						seq_goto_Y_C6 = 2111.7808
						seq_goto_Z_C6 =	1010.0312
						seq_heading_C6 = 350.3034
					ELSE
						seq_goto_X_C6 =	959.4180
						seq_goto_Y_C6 = 2104.8674
						seq_goto_Z_C6 =	1010.0303
						seq_heading_C6 = 167.1333
					ENDIF
					seq_performer_C6 = mafia_C6[0]
					seq_duck_start_C6 =	FALSE
					seq_duck_end_C6 = TRUE
					CLEAR_CHAR_TASKS mafia_C6[0]
					GOSUB mission_casino6_SUB_ped_goto_and_attack_rosenberg
				ENDIF


				IF NOT IS_CHAR_DEAD mafia_C6[1]
					IF rosenberg_status_C6 < 2	
						seq_goto_X_C6 =	943.3311
						seq_goto_Y_C6 = 2111.7134
						seq_goto_Z_C6 =	1010.0312
						seq_heading_C6 = 0.2979
					ELSE
						seq_performer_C6 = mafia_C6[1]
						seq_duck_start_C6 =	FALSE
						seq_duck_end_C6 = TRUE
						CLEAR_CHAR_TASKS mafia_C6[1]

						IF NOT IS_CHAR_DEAD mafia_C6[0]
							seq_goto_X_C6 =	943.3311
							seq_goto_Y_C6 = 2111.7134
							seq_goto_Z_C6 =	1010.0312
							seq_heading_C6 = 0.2979

							GOSUB mission_casino6_SUB_ped_goto_and_stay
						ELSE
							seq_goto_X_C6 =	959.4180
							seq_goto_Y_C6 = 2104.8674
							seq_goto_Z_C6 =	1010.0303
							seq_heading_C6 = 167.1333

							GOSUB mission_casino6_SUB_ped_goto_and_attack_rosenberg
						ENDIF
					ENDIF
				ENDIF
					
			ENDIF
		BREAK

		CASE 1
			IF IS_CHAR_IN_AREA_2D scplayer 930.0 2120.0 952.25 2125.0 FALSE
			OR IS_CHAR_IN_AREA_2D scplayer 952.25 2140.0 967.0 2147.0 FALSE
		
				abattoir_status_C6 ++

				// guys with baseball bats				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 951.3571 2143.1597 1010.0312 mafia_C6[14]
				SET_CHAR_HEADING mafia_C6[14] 90.0
				SET_CHAR_HEALTH mafia_C6[14] 200	
				SET_CHAR_MAX_HEALTH mafia_C6[14] 200
				ADD_BLIP_FOR_CHAR mafia_C6[14] mafia_blip_C6[14]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[14] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[14] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[14] WEAPONTYPE_BASEBALLBAT 3000
				mafia_status_C6[14] = 0
				seq_performer_C6 = mafia_C6[14]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	943.5978
				seq_goto_Y_C6 = 2138.5715
				seq_goto_Z_C6 =	1010.0312
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack
				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF4 932.6479 2144.1184 1010.0312 mafia_C6[15]
				SET_CHAR_HEADING mafia_C6[15] 270.0
				SET_CHAR_HEALTH mafia_C6[15] 200	
				SET_CHAR_MAX_HEALTH mafia_C6[15] 200
				ADD_BLIP_FOR_CHAR mafia_C6[15] mafia_blip_C6[15]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[15] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[15] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[15] WEAPONTYPE_BASEBALLBAT 3000
				mafia_status_C6[15] = 0
				seq_performer_C6 = mafia_C6[15]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	940.2230			
				seq_goto_Y_C6 = 2139.4192
				seq_goto_Z_C6 =	1010.0312
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 950.7346 2156.7163 1010.0303 mafia_C6[16]
				SET_CHAR_HEADING mafia_C6[16] 135.0
				SET_CHAR_HEALTH mafia_C6[16] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[16] 150				
				ADD_BLIP_FOR_CHAR mafia_C6[16] mafia_blip_C6[16]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[16] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[16] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[16] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[16] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[16] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[16] 35
				SET_CHAR_DECISION_MAKER mafia_C6[16] attack_dm_C6
				TASK_KILL_CHAR_ON_FOOT mafia_C6[16] scplayer
				mafia_status_C6[16] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 933.3263 2160.1885 1010.0312 mafia_C6[17]
				SET_CHAR_HEADING mafia_C6[17] 214.0
				ADD_BLIP_FOR_CHAR mafia_C6[17] mafia_blip_C6[17]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[17] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[17] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[17] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[17] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[17] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[17] 40
				SET_CHAR_DECISION_MAKER mafia_C6[17] attack_dm_C6
				TASK_KILL_CHAR_ON_FOOT mafia_C6[17] scplayer
				mafia_status_C6[17] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 942.5138 2165.0999 1011.2277 mafia_C6[18]
				SET_CHAR_HEADING mafia_C6[18] 180.0
				SET_CHAR_HEALTH mafia_C6[18] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[18] 150
				ADD_BLIP_FOR_CHAR mafia_C6[18] mafia_blip_C6[18]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[18] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[18] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[18] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[18] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[18] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[18] 35
				SET_CHAR_SHOOT_RATE mafia_C6[18] 60
				//SET_CHAR_DECISION_MAKER mafia_C6[18] attack_dm_C6
				TASK_TOGGLE_DUCK mafia_C6[18] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[18] scplayer
				mafia_status_C6[18] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 950.2886 2172.3796 1010.0303 mafia_C6[19]
				SET_CHAR_HEADING mafia_C6[19] 163.5
				ADD_BLIP_FOR_CHAR mafia_C6[19] mafia_blip_C6[19]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[19] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[19] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[19] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[19] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[19] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[19] 40
				SET_CHAR_DECISION_MAKER mafia_C6[19] attack_dm_C6
				TASK_KILL_CHAR_ON_FOOT mafia_C6[19] scplayer
				mafia_status_C6[19] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 934.5824 2172.3184 1010.0303 mafia_C6[20]
				SET_CHAR_HEADING mafia_C6[20] 197.0
				SET_CHAR_HEALTH mafia_C6[20] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[20] 150
				ADD_BLIP_FOR_CHAR mafia_C6[20] mafia_blip_C6[20]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[20] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[20] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[20] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[20] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[20] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[20] 35
				SET_CHAR_DECISION_MAKER mafia_C6[20] attack_dm_C6
				TASK_KILL_CHAR_ON_FOOT mafia_C6[20] scplayer				
				mafia_status_C6[20] = 0

			ENDIF
		BREAK

		CASE 2
 			IF IS_CHAR_IN_AREA_2D scplayer 951.5627 2146.9443 932.6420 2141.2834 FALSE			
			
				abattoir_status_C6 ++

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF4 954.0464 2157.8252 1010.0312 mafia_C6[21]
				SET_CHAR_HEADING mafia_C6[21] 30.0
				SET_CHAR_HEALTH mafia_C6[21] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[21] 150
				ADD_BLIP_FOR_CHAR mafia_C6[21] mafia_blip_C6[21]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[21] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[21] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[21] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[21] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[21] 40
				mafia_status_C6[21] = 0
				seq_performer_C6 = mafia_C6[21]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	944.0997
				seq_goto_Y_C6 = 2173.7312
				seq_goto_Z_C6 =	1010.0303
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack
				
				CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 952.8911 2165.8428 1010.0312 mafia_C6[22]
				SET_CHAR_HEADING mafia_C6[22] 180.0
				SET_CHAR_HEALTH mafia_C6[22] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[22] 150
				ADD_BLIP_FOR_CHAR mafia_C6[22] mafia_blip_C6[22]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[22] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[22] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[22] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[22] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[22] 35
				SET_CHAR_SHOOT_RATE mafia_C6[22] 70
				mafia_status_C6[22] = 0
				seq_performer_C6 = mafia_C6[22]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	949.5583
				seq_goto_Y_C6 = 2159.8477
				seq_goto_Z_C6 =	1010.0312
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 953.7090 2167.3530 1010.0312 mafia_C6[23]
				SET_CHAR_HEADING mafia_C6[23] 180.0
				SET_CHAR_HEALTH mafia_C6[23] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[23] 150
				ADD_BLIP_FOR_CHAR mafia_C6[23] mafia_blip_C6[23]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[23] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[23] 968.3954 2160.9651 1.0
				GIVE_WEAPON_TO_CHAR mafia_C6[23] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[23] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[23] 40
				mafia_status_C6[23] = 0
				seq_performer_C6 = mafia_C6[23]
				seq_duck_start_C6 =	FALSE
				seq_goto_X_C6 =	947.7441
				seq_goto_Y_C6 = 2162.7581
				seq_goto_Z_C6 =	1010.0312
				seq_attack_C6 = scplayer
				GOSUB mission_casino6_SUB_ped_goto_and_attack
				
				// guys in the end room
 				CREATE_CHAR PEDTYPE_MISSION2 VMAFF4 955.1686 2155.9680 1013.6431 mafia_C6[24]
				SET_CHAR_HEADING mafia_C6[24] 25.0
				SET_CHAR_HEALTH mafia_C6[24] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[24] 150
				ADD_BLIP_FOR_CHAR mafia_C6[24] mafia_blip_C6[24]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[24] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[24] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[24] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[24] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[24] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[24] 35
				TASK_TOGGLE_DUCK mafia_C6[24] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[24] scplayer
				mafia_status_C6[24] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF1 957.3302 2172.3586 1011.8324 mafia_C6[25]
				SET_CHAR_HEADING mafia_C6[25] 88.0
				SET_CHAR_HEALTH mafia_C6[25] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[25] 150
				ADD_BLIP_FOR_CHAR mafia_C6[25] mafia_blip_C6[25]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[25] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[25] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[25] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[25] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[25] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[25] 40
				TASK_TOGGLE_DUCK mafia_C6[25] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[25] scplayer
				mafia_status_C6[25] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 955.5260 2175.9839 1010.0312 mafia_C6[26]
				SET_CHAR_HEADING mafia_C6[26] 169.0
				SET_CHAR_HEALTH mafia_C6[26] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[26] 150
				ADD_BLIP_FOR_CHAR mafia_C6[26] mafia_blip_C6[26]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[26] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[26] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[26] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[26] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[26] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[26] 35
				TASK_TOGGLE_DUCK mafia_C6[26] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[26] scplayer
				mafia_status_C6[26] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF2 959.0216 2172.0002 1010.0312 mafia_C6[27]
				SET_CHAR_HEADING mafia_C6[27] 283.0
				SET_CHAR_HEALTH mafia_C6[27] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[27] 150
				ADD_BLIP_FOR_CHAR mafia_C6[27] mafia_blip_C6[27]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[27] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[27] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[27] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[27] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[27] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[27] 40
				TASK_TOGGLE_DUCK mafia_C6[27] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[27] scplayer
				mafia_status_C6[27] = 0

				CREATE_CHAR PEDTYPE_MISSION2 VMAFF3 958.8845 2152.1169 1011.8324 mafia_C6[28]
				SET_CHAR_HEADING mafia_C6[28] 0.0
				SET_CHAR_HEALTH mafia_C6[28] 150	
				SET_CHAR_MAX_HEALTH mafia_C6[28] 150
				ADD_BLIP_FOR_CHAR mafia_C6[28] mafia_blip_C6[28]
				CHANGE_BLIP_DISPLAY mafia_blip_C6[28] BLIP_ONLY
				SET_BLIP_ENTRY_EXIT mafia_blip_C6[28] 968.3954 2160.9651 1.0
				TASK_STAY_IN_SAME_PLACE mafia_C6[28] TRUE
				GIVE_WEAPON_TO_CHAR mafia_C6[28] WEAPONTYPE_MP5 3000
				SET_CURRENT_CHAR_WEAPON mafia_C6[28] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY mafia_C6[28] 35
				TASK_TOGGLE_DUCK mafia_C6[28] TRUE
				TASK_KILL_CHAR_ON_FOOT mafia_C6[28] scplayer
				mafia_status_C6[28] = 0
			ENDIF
		BREAK

		CASE 3
			IF IS_CHAR_IN_AREA_2D scplayer 952.7594 2156.4246 955.3043 2166.7607 FALSE
			
				abattoir_status_C6 ++

				IF NOT IS_CHAR_DEAD mafia_C6[26]
					seq_performer_C6 = mafia_C6[26]
					seq_duck_start_C6 =	FALSE
					seq_duck_end_C6 = FALSE
					seq_goto_X_C6 =	960.9915
					seq_goto_Y_C6 = 2177.3870
					seq_goto_Z_C6 =	1010.0312
					seq_heading_C6 = 107.0
					GOSUB mission_casino6_SUB_ped_goto_and_stay	
				ENDIF
			ENDIF
		BREAK

		CASE 4
			IF IS_CHAR_IN_AREA_2D scplayer 961.7650 2174.8989 965.3094 2171.5881 FALSE

				abattoir_status_C6 ++

				IF NOT IS_CHAR_DEAD mafia_C6[27]
					seq_performer_C6 = mafia_C6[27]
					seq_duck_start_C6 =	FALSE
					seq_duck_end_C6 = FALSE
					seq_goto_X_C6 =	958.9090
					seq_goto_Y_C6 = 2158.5020
					seq_goto_Z_C6 =	1010.0312
					seq_heading_C6 = 0.0
					GOSUB mission_casino6_SUB_ped_goto_and_stay
				ENDIF
			ENDIF
		BREAK


	ENDSWITCH
		
RETURN


// #####################
// ## Rosenberg gosubs 
// #####################

LVAR_INT rosenberg_progress_C6  

mission_casino6_SUB_rosenberg_status:
	
	SWITCH rosenberg_status_C6

	// ## Rosenbergs behaviour in start area 
	CASE 0
		TASK_STAY_IN_SAME_PLACE rosenberg_C6 TRUE
		TASK_TOGGLE_DUCK rosenberg_C6 TRUE 
		rosenberg_status_C6 ++		
	BREAK

	// checks for two guys who jump over fire are dead before rosenberg runs for fire extinguisher
	CASE 1
		IF IS_CHAR_DEAD mafia_C6[2]
		AND IS_CHAR_DEAD mafia_C6[3]
			
			OPEN_SEQUENCE_TASK sequence_C6
				TASK_TOGGLE_DUCK -1 FALSE
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				TASK_GO_TO_COORD_ANY_MEANS -1 959.2689 2098.5903 1010.0303 PEDMOVE_RUN -1
				TASK_ACHIEVE_HEADING -1 255.0 
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAND_STILL -1 300
				TASK_STAND_STILL -1 300
				TASK_TOGGLE_DUCK -1 FALSE
			CLOSE_SEQUENCE_TASK sequence_C6	
			
			PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
			CLEAR_SEQUENCE_TASK sequence_C6			

			rosenberg_status_C6 ++
		ELSE
			IF player_trapped_C6 = 1
				OPEN_SEQUENCE_TASK sequence_C6
					TASK_TOGGLE_DUCK -1 FALSE
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_GO_TO_COORD_ANY_MEANS -1 959.2689 2098.5903 1010.0303 PEDMOVE_RUN -1
					TASK_ACHIEVE_HEADING -1 255.0 
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAND_STILL -1 300
					TASK_STAND_STILL -1 300
					TASK_TOGGLE_DUCK -1 FALSE
				CLOSE_SEQUENCE_TASK sequence_C6	
				
				PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
				CLEAR_SEQUENCE_TASK sequence_C6			

				rosenberg_status_C6 ++
			ELSE
				IF rosenberg_text_flag_C6[0] = 0
					GENERATE_RANDOM_INT_IN_RANGE 0 101 rosenberg_random_text_C6
					IF rosenberg_random_text_C6 < 50
						load_sample = SOUND_CAS6_EG
						$load_text = &CAS6_EG
					ELSE
						load_sample = SOUND_CAS6_EE
						$load_text = &CAS6_EE
					ENDIF

					START_NEW_SCRIPT audio_load_and_play 2 50 rosenberg_C6 // Typical end for me, shot, beaten and then barbeque! 

					rosenberg_text_flag_C6[0] = 1
				ENDIF
			ENDIF
		ENDIF 
	BREAK

	CASE 2
		GET_SEQUENCE_PROGRESS rosenberg_C6 rosenberg_progress_C6
		IF rosenberg_progress_C6 = 6 
			GIVE_WEAPON_TO_CHAR rosenberg_C6 WEAPONTYPE_EXTINGUISHER 3000
			SET_CURRENT_CHAR_WEAPON rosenberg_C6 WEAPONTYPE_EXTINGUISHER
			DELETE_OBJECT fire_ex_C6

			load_sample = SOUND_CAS6_EH
			$load_text = &CAS6_EH
			START_NEW_SCRIPT audio_load_and_play 2 100 rosenberg_C6 // I got the extinguisher
			rosenberg_text_timer_C6 = 0

			IF IS_CHAR_DEAD mafia_C6[0]
			AND IS_CHAR_DEAD mafia_C6[1]
				
				CLEAR_CHAR_TASKS rosenberg_C6
				OPEN_SEQUENCE_TASK sequence_C6
					
					TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
					TASK_TOGGLE_DUCK -1 FALSE 
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_GO_TO_COORD_ANY_MEANS -1 946.1957 2112.3716 1010.0312 PEDMOVE_RUN -1
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					TASK_ACHIEVE_HEADING -1 79.1071  

				CLOSE_SEQUENCE_TASK sequence_C6	
				
				PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
				CLEAR_SEQUENCE_TASK sequence_C6
				rosenberg_text_timer_C6 = 0
				rosenberg_status_C6 = 4
			ELSE
				IF player_trapped_C6 = 1
					CLEAR_CHAR_TASKS rosenberg_C6
					OPEN_SEQUENCE_TASK sequence_C6
						
						TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
						TASK_TOGGLE_DUCK -1 FALSE 
						TASK_STAY_IN_SAME_PLACE -1 FALSE
						TASK_GO_TO_COORD_ANY_MEANS -1 946.1957 2112.3716 1010.0312 PEDMOVE_RUN -1
						TASK_TOGGLE_DUCK -1 TRUE
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_ACHIEVE_HEADING -1 79.1071  

					CLOSE_SEQUENCE_TASK sequence_C6	
					
					PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
					CLEAR_SEQUENCE_TASK sequence_C6
					rosenberg_status_C6 = 4
				ELSE
					seq_performer_C6 = rosenberg_C6
					seq_duck_start_C6 =	FALSE
					seq_duck_end_C6 = FALSE
					seq_goto_X_C6 =	957.9808
					seq_goto_Y_C6 = 2100.7136
					seq_goto_Z_C6 =	1010.0312
					seq_heading_C6 = 346.0
					GOSUB mission_casino6_SUB_ped_goto_and_stay_rosenberg
					rosenberg_status_C6 ++
				ENDIF
			ENDIF
			
		ENDIF	
	BREAK

	CASE 3 // waiting at office door with extinguisher
		IF IS_CHAR_DEAD mafia_C6[0]
		AND IS_CHAR_DEAD mafia_C6[1]
			
			CLEAR_CHAR_TASKS rosenberg_C6
			OPEN_SEQUENCE_TASK sequence_C6
				
				TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
				TASK_TOGGLE_DUCK -1 FALSE 
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				TASK_GO_TO_COORD_ANY_MEANS -1 945.1309 2112.7109 1010.0312 PEDMOVE_RUN -1
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_ACHIEVE_HEADING -1 79.1071  

			CLOSE_SEQUENCE_TASK sequence_C6	
			
			PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
			CLEAR_SEQUENCE_TASK sequence_C6		
			rosenberg_status_C6 ++
		ELSE
			IF player_trapped_C6 = 1
				CLEAR_CHAR_TASKS rosenberg_C6
				OPEN_SEQUENCE_TASK sequence_C6
					
					TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
					TASK_TOGGLE_DUCK -1 FALSE 
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_GO_TO_COORD_ANY_MEANS -1 945.1309 2112.7109 1010.0312 PEDMOVE_RUN -1
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					TASK_ACHIEVE_HEADING -1 79.1071  

				CLOSE_SEQUENCE_TASK sequence_C6	
				
				PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
				CLEAR_SEQUENCE_TASK sequence_C6		
				rosenberg_status_C6 ++
				rosenberg_text_timer_C6 = 0				
			ELSE
				IF rosenberg_text_flag_C6[1] = 0
				AND rosenberg_text_timer_C6 > 500
//
					load_sample = SOUND_CAS6_EF
					$load_text = &CAS6_EF
					START_NEW_SCRIPT audio_load_and_play 2 100 rosenberg_C6 // I aint coming out until its safe.

					rosenberg_text_flag_C6[1] = 1
//					rosenberg_text_timer_C6 = 0
				ENDIF
			ENDIF
		ENDIF
	BREAK

	CASE 4 // been told to stand next to the fire 
		IF LOCATE_CHAR_ON_FOOT_2D rosenberg_C6 945.1309 2112.7109 1.5 1.5 FALSE
			IF mt_status_C6 = 0
				rosenberg_status_C6 = 21	 
			ELSE
				IF abattoir_status_C6 > 0
					rosenberg_status_C6 = 6
				ELSE
					IF IS_CHAR_DEAD mafia_C6[4]
					AND IS_CHAR_DEAD mafia_C6[5]
						rosenberg_status_C6 = 6 	
					ELSE

						rosenberg_status_C6 ++
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF rosenberg_text_flag_C6[2] = 0
			AND rosenberg_text_timer_C6 > 2500
				IF player_trapped_C6 = 0
					IF NOT IS_CHAR_DEAD mafia_C6[4]
					OR NOT IS_CHAR_DEAD mafia_C6[5]
						load_sample = SOUND_CAS6_EJ
						$load_text = &CAS6_EJ
						START_NEW_SCRIPT audio_load_and_play 2 101 rosenberg_C6 // Take those guys out and i'll put out the flames						
						rosenberg_text_flag_C6[2] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF 
	BREAK

	CASE 5 // waiting to put fire out
		IF abattoir_status_C6 > 0
			rosenberg_status_C6 ++
		ELSE
			IF IS_CHAR_DEAD mafia_C6[4]
			AND IS_CHAR_DEAD mafia_C6[5]
				IF NOT LOCATE_CHAR_ON_FOOT_2D rosenberg_C6 945.1309 2112.7109 1.5 1.5 FALSE  
					TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 945.1309 2112.7109 1010.0312 PEDMOVE_RUN -1
					rosenberg_status_C6 --
				ELSE
					rosenberg_status_C6 ++
				ENDIF
			ENDIF
		ENDIF
	BREAK

	// ## Putting out the fire (if it exists)

	CASE 6 // put out fire sequence
		
		IF NOT mafia_dead_C6 = number_of_mafia_C6
			IF player_trapped_C6 = 0
				IF IS_CHAR_IN_AREA_3D scplayer 930.0 2114.0 1010.0 966.0 2095.0 1012.0 FALSE
					load_sample = SOUND_CAS6_EK
					$load_text = &CAS6_EK
					START_NEW_SCRIPT audio_load_and_play 2 101 rosenberg_C6 // Stand back i'm gonna blast these flames
					//PRINT_NOW CAS6_05 5000 1 // stand back im gonna put out the fire
				ELSE
					load_sample = SOUND_CAS6_EL
					$load_text = &CAS6_EL
					START_NEW_SCRIPT audio_load_and_play 2 101 rosenberg_C6 // Hey, CJ Wait for me!
					//PRINT_NOW CAS6_07 5000 1 // CJ you son of a bitch, wait for me
				ENDIF  
			ENDIF
		ENDIF

		CLEAR_CHAR_TASKS rosenberg_C6
		rosenberg_timer_C6 = 0
		
		IF NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[5]
		OR NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[4]		
			CLEAR_CHAR_TASKS rosenberg_C6
			OPEN_SEQUENCE_TASK sequence_C6
				TASK_SHOOT_AT_COORD -1 943.2 2114.0 1011.0 3000
				TASK_STAND_STILL -1 500
			CLOSE_SEQUENCE_TASK sequence_C6	
			PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
			CLEAR_SEQUENCE_TASK sequence_C6
		ELSE
			rosenberg_timer_C6 = 10000
		ENDIF
		rosenberg_status_C6 ++
	BREAK

	CASE 7
		IF rosenberg_timer_C6 > 2850
			REMOVE_SCRIPT_FIRE script_fire_C6[5]
			REMOVE_SCRIPT_FIRE script_fire_C6[4]
			script_fire_check_C6[0] = 1
			rosenberg_status_C6 ++			
		ENDIF	
	BREAK
			
	CASE 8 // put out fire sequence
		CLEAR_CHAR_TASKS rosenberg_C6
		rosenberg_timer_C6 = 0

		IF NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[3]
		OR NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[2]		
			CLEAR_CHAR_TASKS rosenberg_C6
			OPEN_SEQUENCE_TASK sequence_C6
				TASK_SHOOT_AT_COORD -1 941.9 2114.0 1011.0 2000
				TASK_STAND_STILL -1 500
			CLOSE_SEQUENCE_TASK sequence_C6	
			PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
			CLEAR_SEQUENCE_TASK sequence_C6
		ELSE
			rosenberg_timer_C6 = 10000
		ENDIF
		rosenberg_status_C6 ++
	BREAK

	CASE 9
		IF rosenberg_timer_C6 > 1250
			REMOVE_SCRIPT_FIRE script_fire_C6[3]
			REMOVE_SCRIPT_FIRE script_fire_C6[2]
			script_fire_check_C6[1] = 1
			rosenberg_status_C6 ++			
		ENDIF
	BREAK

	CASE 10 // put out fire sequence
		CLEAR_CHAR_TASKS rosenberg_C6
		rosenberg_timer_C6 = 0

		IF NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[1]
		OR NOT IS_SCRIPT_FIRE_EXTINGUISHED script_fire_C6[0]		
			CLEAR_CHAR_TASKS rosenberg_C6
		 	TASK_SHOOT_AT_COORD rosenberg_C6 940.7 2114.0 1011.0 3000
		ELSE
			rosenberg_timer_C6 = 10000
		ENDIF
		rosenberg_status_C6 ++
	BREAK

	CASE 11
		IF rosenberg_timer_C6 > 2850
			REMOVE_SCRIPT_FIRE script_fire_C6[1]
			REMOVE_SCRIPT_FIRE script_fire_C6[0]
			script_fire_check_C6[2] = 1
			heathaze_active_C6 = 2
			SET_HEATHAZE_EFFECT FALSE
			rosenberg_status_C6 ++			
		ENDIF	
	BREAK 

	// ## Going into first machine room
 
	CASE 12
		IF abattoir_status_C6 > 0
		
			IF abattoir_status_C6 = 1
				// hide in freezer
				// check if freezer door is open
				IF fd_status_C6 = 0 // open
				OR fd_status_C6 = 3 // opening
					CLEAR_CHAR_TASKS rosenberg_C6
					TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 960.0 2110.0 1010.0312 PEDMOVE_RUN -1
					rosenberg_status_C6 = 15 // go into freezer
				ELSE
					rosenberg_status_C6 = 13 // go open freezer
				ENDIF
			ELSE
				IF abattoir_status_C6 = 2
					IF fd_status_C6 = 0 // open
					OR fd_status_C6 = 3 // opening
						CLEAR_CHAR_TASKS rosenberg_C6	
						TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 960.0 2110.0 1010.0312 PEDMOVE_RUN -1
						rosenberg_status_C6 = 15 // go into freezer
					ELSE
						rosenberg_status_C6 = 13 // go open freezer
					ENDIF
				ELSE
					IF abattoir_status_C6 > 2
						CLEAR_CHAR_TASKS rosenberg_C6
						rosenberg_status_C6 = 19
						BREAK	
					ENDIF
				ENDIF
			ENDIF 	
			
			IF player_trapped_C6 = 0
				
				load_sample = SOUND_CAS6_FA
				$load_text = &CAS6_FA
				START_NEW_SCRIPT audio_load_and_play 2 100 rosenberg_C6 // Screw this, I'm going to hide in the freezer

				//PRINT_NOW CAS6_10 5000 1 // im gonna hide in the freezer
				rosenberg_text_timer_C6 = 0			
			ENDIF 
		ELSE
			IF rosenberg_text_flag_C6[3] = 0
			//AND rosenberg_text_timer_C6 > 5000
				GENERATE_RANDOM_INT_IN_RANGE 0 101 rosenberg_random_text_C6
				IF rosenberg_random_text_C6 < 50
					load_sample = SOUND_CAS6_EM
					$load_text = &CAS6_EM
				ELSE
					load_sample = SOUND_CAS6_EN
					$load_text = &CAS6_EN
				ENDIF
				START_NEW_SCRIPT audio_load_and_play 2 100 rosenberg_C6 // I ain't leading the way
				//PRINT_NOW CAS6_08 5000 1 // im not going in there first
				rosenberg_text_flag_C6[3] = 1
				rosenberg_text_timer_C6 = 0
			ENDIF
		ENDIF 
	BREAK

	CASE 13
		// rosenberg presses switch
		ADD_ATTRACTOR 950.9462 2114.9719 1011.5 180.0 15.0 fd_attractor_seq_C6 fd_attractor_C6
		ADD_PEDTYPE_AS_ATTRACTOR_USER fd_attractor_C6 PEDTYPE_MISSION1

		CLEAR_CHAR_TASKS rosenberg_C6
		OPEN_SEQUENCE_TASK sequence_C6
			TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
			TASK_GO_TO_COORD_ANY_MEANS -1 950.2319 2115.9265 1010.0312 PEDMOVE_RUN -1
			TASK_USE_ATTRACTOR -1 fd_attractor_C6
		CLOSE_SEQUENCE_TASK sequence_C6	
		PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
		CLEAR_SEQUENCE_TASK sequence_C6
		rosenberg_status_C6 ++
	BREAK
	
	CASE 14
		IF IS_CHAR_AT_SCRIPTED_ATTRACTOR rosenberg_C6 fd_attractor_C6 
			IF fd_status_C6 = 1 // closing
			OR fd_status_C6 = 2 // closed
				fd_speed_C6 = 0.0
				fd_accel_C6 = 0.0005
				fd_status_C6 = 3
			ENDIF

			IF player_trapped_C6 = 1
				//PRINT_NOW CAS6_11 5000 1 // you owe me CJ
				rosenberg_text_timer_C6 = 0
			ENDIF

			SET_CHAR_PROOFS rosenberg_C6 FALSE FALSE FALSE FALSE FALSE						 
			TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 963.4875 2119.2827 1010.0312 PEDMOVE_RUN -1
			CLEAR_ATTRACTOR fd_attractor_C6
			
			IF NOT mafia_dead_C6 = number_of_mafia_C6 
				rosenberg_status_C6 ++
			ELSE
				rosenberg_status_C6 = 19
			ENDIF			
		ENDIF 
	BREAK
	
	CASE 15
		// rosenberg going to get in freezer, check for player trying to shut door on rosenberg
		IF IS_CHAR_IN_AREA_3D rosenberg_C6 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
			rosenberg_status_C6 ++
		ELSE
			GET_SCRIPT_TASK_STATUS rosenberg_C6 TASK_GO_TO_COORD_ANY_MEANS rosenberg_progress_C6
			IF rosenberg_progress_C6 = FINISHED_TASK
				TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 963.4875 2119.2827 1010.0312 PEDMOVE_RUN -1
			ENDIF
		ENDIF
	BREAK
	
	CASE 16
		// checking for when rosenberg_C6 is in back of freezer and makes him duck and adds attack dm
		GET_SCRIPT_TASK_STATUS rosenberg_C6 TASK_GO_TO_COORD_ANY_MEANS rosenberg_progress_C6
		IF rosenberg_progress_C6 = FINISHED_TASK
			IF LOCATE_CHAR_ON_FOOT_2D rosenberg_C6 963.4875 2119.2827 1.2 1.2 FALSE
				CLEAR_CHAR_TASKS rosenberg_C6
				OPEN_SEQUENCE_TASK sequence_C6
					TASK_ACHIEVE_HEADING -1 104.0
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6
				CLOSE_SEQUENCE_TASK sequence_C6	
				PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
				CLEAR_SEQUENCE_TASK sequence_C6
				rosenberg_status_C6 ++
			ELSE
				TASK_GO_TO_COORD_ANY_MEANS rosenberg_C6 963.4875 2119.2827 1010.0312 PEDMOVE_RUN -1	
			ENDIF
		ENDIF
	BREAK
	
	CASE 17 // rosenberg ready crouched down in freezer waiting for event to bring him out
		IF fd_status_C6 = 0 // open
		OR fd_status_C6 = 3 // opening
			IF abattoir_status_C6 >= 2
				// check for someone locking in rosenberg
				IF NOT IS_CHAR_DEAD mafia_C6[14] 
				OR NOT IS_CHAR_DEAD mafia_C6[15]
					// run script to lock rosenberg in?
					BREAK 
				ELSE
					// check first lot of guys are gone so rosenberg can come in
					IF NOT IS_CHAR_DEAD mafia_C6[4]
					OR NOT IS_CHAR_DEAD mafia_C6[5]
					OR NOT IS_CHAR_DEAD mafia_C6[6]
					OR NOT IS_CHAR_DEAD mafia_C6[7]
					OR NOT IS_CHAR_DEAD mafia_C6[8]
					OR NOT IS_CHAR_DEAD mafia_C6[9] 
						BREAK
					ELSE
						IF NOT IS_CHAR_DEAD mafia_C6[10]
						OR NOT IS_CHAR_DEAD mafia_C6[11]
						OR NOT IS_CHAR_DEAD mafia_C6[1] 
							BREAK
						ELSE
							
							seq_performer_C6 = rosenberg_C6
							seq_duck_start_C6 =	FALSE
							seq_duck_end_C6 = TRUE						
							
							IF NOT IS_CHAR_DEAD mafia_C6[12]
							OR NOT IS_CHAR_DEAD mafia_C6[13] 
								seq_goto_X_C6 =	942.1362
								seq_goto_Y_C6 = 2116.9773
								seq_goto_Z_C6 =	1010.0312
								seq_heading_C6 = 310.9558

								rosenberg_status_C6 = 18
							ELSE
								seq_goto_X_C6 =	944.9211
								seq_goto_Y_C6 = 2138.5659
								seq_goto_Z_C6 =	1010.0312
								seq_heading_C6 = 49.7997

								rosenberg_status_C6 = 19
							ENDIF

							GOSUB mission_casino6_SUB_ped_goto_and_stay_rosenberg

							IF NOT mafia_dead_C6 = number_of_mafia_C6
								IF waiting_C6 = 1
									load_sample = SOUND_CAS6_ES
									$load_text = &CAS6_ES
									START_NEW_SCRIPT audio_load_and_play 1 100 scplayer // Ok, It's clear
								ENDIF							
							ENDIF
							
							IF rosenberg_text_flag_C6[4] = 0
								GENERATE_RANDOM_INT_IN_RANGE 0 101 rosenberg_random_text_C6
								IF rosenberg_random_text_C6 < 50
									audio_status_C6 = 2
									audio_pointer_C6 = 3
								ELSE
									audio_status_C6 = 4
									audio_pointer_C6 = 5
								ENDIF
								TIMERA = 0
								rosenberg_text_flag_C6[4] = 1
							ENDIF

							rosenberg_text_timer_C6 = 0
						ENDIF
					ENDIF 	
				ENDIF
			ENDIF 
		ELSE
			GOSUB mission_casino6_SUB_infreezer_audio
			// text for being locked in?
		ENDIF 
	BREAK 
	
	CASE 18
		IF IS_CHAR_DEAD mafia_C6[12]
		AND IS_CHAR_DEAD mafia_C6[13]
			seq_performer_C6 = rosenberg_C6
			seq_duck_start_C6 =	FALSE
			seq_duck_end_C6 = TRUE
			seq_goto_X_C6 =	944.9211
			seq_goto_Y_C6 = 2138.5659
			seq_goto_Z_C6 =	1010.0312
			seq_heading_C6 = 49.7997
			GOSUB mission_casino6_SUB_ped_goto_and_stay_rosenberg

			rosenberg_status_C6 ++
		ENDIF
	BREAK
	
	CASE 19
		IF abattoir_status_C6 > 2
			IF NOT IS_CHAR_DEAD mafia_C6[16]
			OR NOT IS_CHAR_DEAD mafia_C6[17]
			OR NOT IS_CHAR_DEAD mafia_C6[18]
			OR NOT IS_CHAR_DEAD mafia_C6[19]
			OR NOT IS_CHAR_DEAD mafia_C6[20]
			OR NOT IS_CHAR_DEAD mafia_C6[21]
				BREAK
			ELSE
				IF NOT IS_CHAR_DEAD mafia_C6[22]
				OR NOT IS_CHAR_DEAD mafia_C6[23]
					BREAK
				ELSE				
					seq_performer_C6 = rosenberg_C6
					seq_duck_start_C6 =	FALSE
											
					IF NOT IS_CHAR_DEAD mafia_C6[24]
					OR NOT IS_CHAR_DEAD mafia_C6[25]
					OR NOT IS_CHAR_DEAD mafia_C6[26]
					OR NOT IS_CHAR_DEAD mafia_C6[27]
					OR NOT IS_CHAR_DEAD mafia_C6[28]
						seq_duck_end_C6 = TRUE
						seq_goto_X_C6 =	949.2078
						seq_goto_Y_C6 = 2153.6484
						seq_goto_Z_C6 =	1010.0303
						seq_heading_C6 = 0.0

						rosenberg_status_C6 = 20
					ELSE
						seq_duck_end_C6 = FALSE
						seq_goto_X_C6 =	965.2232
				   		seq_goto_Y_C6 = 2156.2937
						seq_goto_Z_C6 =	1010.0312
				   		seq_heading_C6 = 22.0

						rosenberg_status_C6 = 30
					ENDIF

					GOSUB mission_casino6_SUB_ped_goto_and_stay_rosenberg

					IF NOT mafia_dead_C6 = number_of_mafia_C6
						load_sample = SOUND_CAS6_ES
						$load_text = &CAS6_ES
						START_NEW_SCRIPT audio_load_and_play 1 100 scplayer // Ok, It's clear
					ENDIF
					//PRINT_NOW CAS6_25 5000 1
					rosenberg_text_timer_C6 = 0
				
				ENDIF
			ENDIF
		ENDIF
	BREAK	
	
	CASE 20
		IF NOT IS_CHAR_DEAD mafia_C6[24]
		OR NOT IS_CHAR_DEAD mafia_C6[25]
		OR NOT IS_CHAR_DEAD mafia_C6[26]
		OR NOT IS_CHAR_DEAD mafia_C6[27]
		OR NOT IS_CHAR_DEAD mafia_C6[28]
			BREAK
		ELSE	
			seq_performer_C6 = rosenberg_C6
			seq_duck_start_C6 =	FALSE
			seq_duck_end_C6 = FALSE
			seq_goto_X_C6 =	965.2232
	   		seq_goto_Y_C6 = 2156.2937
			seq_goto_Z_C6 =	1010.0312
	   		seq_heading_C6 = 22.0
			GOSUB mission_casino6_SUB_ped_goto_and_stay_rosenberg

			rosenberg_status_C6 = 30
		ENDIF
	BREAK
	
	// added rosenberg turning on meat track
	CASE 21
		CLEAR_CHAR_TASKS rosenberg_C6
		OPEN_SEQUENCE_TASK sequence_C6
			TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
			TASK_TOGGLE_DUCK -1 FALSE
			TASK_ACHIEVE_HEADING -1 0.0
			TASK_STAND_STILL -1 500
			TASK_ACHIEVE_HEADING -1 90.0
			TASK_TOGGLE_DUCK -1 TRUE
		CLOSE_SEQUENCE_TASK sequence_C6	
		PERFORM_SEQUENCE_TASK rosenberg_C6 sequence_C6
		CLEAR_SEQUENCE_TASK sequence_C6

		rosenberg_status_C6 ++
	BREAK
	
	
	CASE 22
		GET_SEQUENCE_PROGRESS rosenberg_C6 rosenberg_progress_C6
		IF rosenberg_progress_C6 = 3
			
			IF mt_status_C6 = 0
				mt_accel_C6 = 0.0001
				mt_status_C6 = 1
				mt_timer_C6 = 0
				
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 941.9891 2144.2883 1016.2064 SOUND_MEAT_TRACK_START

				IF player_trapped_C6 = 0
					IF NOT mafia_dead_C6 = number_of_mafia_C6
						IF IS_CHAR_IN_AREA_3D scplayer 930.0 2114.25 1010.0 966.0 2095.0 1012.0 FALSE	
							
							load_sample = SOUND_CAS6_EO
							$load_text = &CAS6_EO
							START_NEW_SCRIPT audio_load_and_play 2 100 rosenberg_C6 // I'm gonna set the meat track going									
							//PRINT_NOW CAS6_03 5000 1 // this should cause the goons some problems. 
						ELSE
							load_sample = SOUND_CAS6_ER
							$load_text = &CAS6_ER
							START_NEW_SCRIPT audio_load_and_play 1 100 scplayer // What did you do that for	
							//PRINT_NOW CAS6_09 5000 1 // watch out CJ im gonna set the meat track going.
						ENDIF
					ENDIF
					rosenberg_text_timer_C6 = 0
				ENDIF

			ENDIF
			rosenberg_status_C6 = 4
		ENDIF	
	BREAK
		
	ENDSWITCH

RETURN



// #################
// ## mafia gosubs
// #################

mission_casino6_SUB_mafia_freezer_check:

	IF NOT mafia_using_switch_C6 = -2 
		IF abattoir_status_C6 >= 2
			IF fd_status_C6 = 0
				IF IS_CHAR_IN_AREA_3D scplayer 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
				OR IS_CHAR_IN_AREA_3D rosenberg_C6 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
					GOSUB mission_casino6_SUB_mafia_close_freezer
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


LVAR_INT mafia_using_switch_C6
mission_casino6_SUB_mafia_close_freezer:
		
	IF mafia_using_switch_C6 = -1
		
		IF NOT IS_CHAR_DEAD mafia_C6[14]
			mafia_using_switch_C6 = 14
		ELSE
			IF NOT IS_CHAR_DEAD mafia_C6[15]
				mafia_using_switch_C6 = 15
			ELSE
				mafia_using_switch_C6 = -2
   			ENDIF
		ENDIF		 	
	
		IF NOT mafia_using_switch_C6 = -2 
			IF fd_status_C6 = 0	// open
				ADD_ATTRACTOR 950.9462 2114.9719 1011.5 180.0 15.0 fd_attractor_seq_C6 fd_attractor_C6
				ADD_PEDTYPE_AS_ATTRACTOR_USER fd_attractor_C6 PEDTYPE_MISSION2
				
				CLEAR_CHAR_TASKS mafia_C6[mafia_using_switch_C6]
				OPEN_SEQUENCE_TASK sequence_C6
					TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
					TASK_GO_TO_COORD_ANY_MEANS -1 950.2319 2115.9265 1010.0312 PEDMOVE_RUN -1
					TASK_USE_ATTRACTOR -1 fd_attractor_C6
				CLOSE_SEQUENCE_TASK sequence_C6	
				
				PERFORM_SEQUENCE_TASK mafia_C6[mafia_using_switch_C6] sequence_C6
				CLEAR_SEQUENCE_TASK sequence_C6
			ELSE
				mafia_using_switch_C6 = -1	
			ENDIF
		ENDIF
	ELSE
		IF IS_CHAR_DEAD mafia_C6[mafia_using_switch_C6]
			CLEAR_ATTRACTOR fd_attractor_C6
			mafia_using_switch_C6 = -1
		ELSE
			IF IS_CHAR_AT_SCRIPTED_ATTRACTOR mafia_C6[mafia_using_switch_C6] fd_attractor_C6 
				IF fd_status_C6 = 0 // open
				OR fd_status_C6 = 3 // opening
					fd_speed_C6 = 0.0
					fd_accel_C6 = -0.0005
					fd_status_C6 = 1
				ENDIF						 
				CLEAR_CHAR_TASKS mafia_C6[mafia_using_switch_C6]
				SET_CHAR_DECISION_MAKER mafia_C6[mafia_using_switch_C6] attack_dm_C6 
				CLEAR_ATTRACTOR fd_attractor_C6
				mafia_using_switch_C6 = -1
			ELSE
				IF fd_status_C6 = 1 // closing
				OR fd_status_C6 = 2 // closed
					CLEAR_CHAR_TASKS mafia_C6[mafia_using_switch_C6]
					SET_CHAR_DECISION_MAKER mafia_C6[mafia_using_switch_C6] attack_dm_C6 
					CLEAR_ATTRACTOR fd_attractor_C6
					mafia_using_switch_C6 = -1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	 
RETURN



// #######################
// ## ped related gosubs
// #######################

LVAR_INT sequence_C6 seq_performer_C6 seq_duck_start_C6 seq_duck_end_C6 seq_attack_C6 
LVAR_FLOAT seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 seq_heading_C6

mission_casino6_SUB_ped_goto_and_stay:
	CLEAR_CHAR_TASKS seq_performer_C6
	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		//TASK_TOGGLE_DUCK -1 seq_duck_start_C6 
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		
//		TASK_FOLLOW_PATH_NODES_TO_COORD -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -2
		TASK_GO_TO_COORD_ANY_MEANS -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 seq_duck_end_C6
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		IF NOT seq_heading_C6 = -1.0
			TASK_ACHIEVE_HEADING -1 seq_heading_C6  
		ENDIF 
		TASK_KILL_CHAR_ON_FOOT -1 scplayer
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK seq_performer_C6 sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6
RETURN

mission_casino6_SUB_ped_goto_and_attack_rosenberg:
	CLEAR_CHAR_TASKS seq_performer_C6
	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		//TASK_TOGGLE_DUCK -1 seq_duck_start_C6 
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		
//		TASK_FOLLOW_PATH_NODES_TO_COORD -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -2
		TASK_GO_TO_COORD_ANY_MEANS -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 seq_duck_end_C6
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		IF NOT seq_heading_C6 = -1.0
			TASK_ACHIEVE_HEADING -1 seq_heading_C6  
		ENDIF 
		TASK_KILL_CHAR_ON_FOOT -1 rosenberg_C6
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK seq_performer_C6 sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6
RETURN

mission_casino6_SUB_ped_goto_and_attack:
	CLEAR_CHAR_TASKS seq_performer_C6
	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		TASK_TOGGLE_DUCK -1 seq_duck_start_C6 
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		IF NOT seq_goto_X_C6 = -1.0 
//			TASK_FOLLOW_PATH_NODES_TO_COORD -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -2
			TASK_GO_TO_COORD_ANY_MEANS -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -1
		ENDIF
		TASK_KILL_CHAR_ON_FOOT -1 seq_attack_C6 
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK seq_performer_C6 sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6
RETURN

mission_casino6_SUB_ped_goto_and_stay_rosenberg:
	CLEAR_CHAR_TASKS seq_performer_C6
	OPEN_SEQUENCE_TASK sequence_C6
		
		TASK_SET_CHAR_DECISION_MAKER -1 empty_dm_C6
		//TASK_TOGGLE_DUCK -1 seq_duck_start_C6 
		TASK_STAY_IN_SAME_PLACE -1 FALSE
		
//		TASK_FOLLOW_PATH_NODES_TO_COORD -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -2
		TASK_GO_TO_COORD_ANY_MEANS -1 seq_goto_X_C6 seq_goto_Y_C6 seq_goto_Z_C6 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 seq_duck_end_C6
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		IF NOT seq_heading_C6 = -1.0
			TASK_ACHIEVE_HEADING -1 seq_heading_C6  
		ENDIF 
		TASK_SET_CHAR_DECISION_MAKER -1 attack_dm_C6

	CLOSE_SEQUENCE_TASK sequence_C6	
	
	PERFORM_SEQUENCE_TASK seq_performer_C6 sequence_C6
	CLEAR_SEQUENCE_TASK sequence_C6
RETURN


// ######################
// ## meat track gosubs
// ######################


// controls the moving of objects (meat carcasses and hooks) around the meat track
mission_casino6_SUB_track_movement:

	// TEMP FOR NOW
	// PRESS G TO START TRACK AND S TO STOP IT
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
		mt_accel_C6 = 0.0001
	ELSE
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
			mt_accel_C6 = -0.0001
		ENDIF
	ENDIF

	mt_speed_C6 +=@ mt_accel_C6
	IF mt_speed_C6 > 0.06  // increase this value to make objects go quicker around the track
		mt_speed_C6 = 0.06
	ELSE
		IF mt_speed_C6 < 0.0
			mt_speed_C6 = 0.0
		ENDIF
	ENDIF

	IF mt_status_C6 = 1
	OR mt_speed_C6 > 0.0
		pointer_C6 = 0
		WHILE pointer_C6 < 6
		
			GET_OBJECT_COORDINATES mt_obj_C6[pointer_C6] x y z

			pointer2_C6 = mt_obj_movement_C6[pointer_C6]

			SWITCH pointer2_C6

				CASE 0
					IF x >= mt_X_C6[pointer2_C6]
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						x += mt_speed_C6
						BREAK
					ENDIF
				
				CASE 1 // rotate
					IF mt_obj_angle_C6[pointer_C6] >= 180.0 
						mt_obj_angle_C6[pointer_C6] = 0.0
						y -= mt_speed_C6
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						mt_degrees_C6 = mt_speed_C6 / mt_dis_per_degree_C6
						mt_obj_angle_C6[pointer_C6] += mt_degrees_C6
						IF mt_obj_angle_C6[pointer_C6] >= 180.0
							mt_obj_angle_C6[pointer_C6] = 180.0
						ENDIF
						
						mt_obj_rot_C6[pointer_C6] -= mt_degrees_C6 
						IF mt_obj_rot_C6[pointer_C6] <= -180.0
							mt_obj_rot_C6[pointer_C6] += 360.0
						ELSE
							IF mt_obj_rot_C6[pointer_C6] >= 180.0
								mt_obj_rot_C6[pointer_C6] += -360.0
							ENDIF
						ENDIF
						SET_OBJECT_ROTATION mt_obj_C6[pointer_C6] 0.0 0.0 mt_obj_rot_C6[pointer_C6]  

						COS mt_obj_angle_C6[pointer_C6] cos_angle_C6
						SIN mt_obj_angle_C6[pointer_C6] sin_angle_C6
					
						new_X_C6 = mt_radius_C6 * cos_angle_C6
						new_Y_C6 = mt_radius_C6 * sin_angle_C6
						
						x = mt_X_C6[0] - new_X_C6
						y = mt_Y_C6[1] + new_Y_C6
					ENDIF
				BREAK

				CASE 2
					IF y <=  mt_Y_C6[pointer2_C6]
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						y -= mt_speed_C6
						BREAK
					ENDIF
				
				CASE 3 // rotate
					IF mt_obj_angle_C6[pointer_C6] <= -90.0 
						mt_obj_angle_C6[pointer_C6] = 90.0
						x -= mt_speed_C6
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						mt_degrees_C6 = mt_speed_C6 / mt_dis_per_degree_C6
						mt_obj_angle_C6[pointer_C6] -= mt_degrees_C6
						IF mt_obj_angle_C6[pointer_C6] <= -90.0
							mt_obj_angle_C6[pointer_C6] = -90.0
						ENDIF

						mt_obj_rot_C6[pointer_C6] -= mt_degrees_C6 
						IF mt_obj_rot_C6[pointer_C6] <= -180.0
							mt_obj_rot_C6[pointer_C6] += 360.0
						ELSE
							IF mt_obj_rot_C6[pointer_C6] >= 180.0
								mt_obj_rot_C6[pointer_C6] += -360.0
							ENDIF
						ENDIF
						SET_OBJECT_ROTATION mt_obj_C6[pointer_C6] 0.0 0.0 mt_obj_rot_C6[pointer_C6] 

						COS mt_obj_angle_C6[pointer_C6] cos_angle_C6
						SIN mt_obj_angle_C6[pointer_C6] sin_angle_C6
					
						new_X_C6 = mt_radius_C6 * cos_angle_C6
						new_Y_C6 = mt_radius_C6 * sin_angle_C6

						x = mt_X_C6[3] + new_X_C6
						y = mt_Y_C6[2] + new_Y_C6
					ENDIF
				BREAK

				CASE 4
					IF x <= mt_X_C6[pointer2_C6]
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						x -= mt_speed_C6
						BREAK
					ENDIF
				
				CASE 5 // rotate 
					IF mt_obj_angle_C6[pointer_C6] >= 180.0 
						mt_obj_angle_C6[pointer_C6] = 0.0
						y += mt_speed_C6
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						mt_degrees_C6 = mt_speed_C6 / mt_dis_per_degree_C6
						mt_obj_angle_C6[pointer_C6] += mt_degrees_C6
						IF mt_obj_angle_C6[pointer_C6] >= 180.0
							mt_obj_angle_C6[pointer_C6] = 180.0
						ENDIF

						mt_obj_rot_C6[pointer_C6] -= mt_degrees_C6 
						IF mt_obj_rot_C6[pointer_C6] <= -180.0
							mt_obj_rot_C6[pointer_C6] += 360.0
						ELSE
							IF mt_obj_rot_C6[pointer_C6] >= 180.0
								mt_obj_rot_C6[pointer_C6] += -360.0
							ENDIF
						ENDIF
						SET_OBJECT_ROTATION mt_obj_C6[pointer_C6] 0.0 0.0 mt_obj_rot_C6[pointer_C6] 

						COS mt_obj_angle_C6[pointer_C6] cos_angle_C6
						SIN mt_obj_angle_C6[pointer_C6] sin_angle_C6
					
						new_X_C6 = mt_radius_C6 * cos_angle_C6
						new_Y_C6 = mt_radius_C6 * sin_angle_C6
						 
						x = mt_X_C6[4] + new_X_C6
						y = mt_Y_C6[5] - new_Y_C6
					ENDIF
				BREAK

				CASE 6
					IF y >=  mt_Y_C6[pointer2_C6]
						mt_obj_movement_C6[pointer_C6] ++
					ELSE
						y += mt_speed_C6
						BREAK
					ENDIF
				
				CASE 7 // rotate
					IF mt_obj_angle_C6[pointer_C6] <= -90.0 
						mt_obj_angle_C6[pointer_C6] = 90.0
						x += mt_speed_C6
						mt_obj_movement_C6[pointer_C6] = 0
					ELSE
						mt_degrees_C6 = mt_speed_C6 / mt_dis_per_degree_C6
						mt_obj_angle_C6[pointer_C6] -= mt_degrees_C6
						
						IF mt_obj_angle_C6[pointer_C6] <= -90.0
							mt_obj_angle_C6[pointer_C6] = -90.0
						ENDIF

						mt_obj_rot_C6[pointer_C6] -= mt_degrees_C6 
						IF mt_obj_rot_C6[pointer_C6] <= -180.0
							mt_obj_rot_C6[pointer_C6] += 360.0
						ELSE
							IF mt_obj_rot_C6[pointer_C6] >= 180.0
								mt_obj_rot_C6[pointer_C6] += -360.0
							ENDIF
						ENDIF
						SET_OBJECT_ROTATION mt_obj_C6[pointer_C6] 0.0 0.0 mt_obj_rot_C6[pointer_C6] 

						COS mt_obj_angle_C6[pointer_C6] cos_angle_C6
						SIN mt_obj_angle_C6[pointer_C6] sin_angle_C6
					
						new_X_C6 = mt_radius_C6 * cos_angle_C6
						new_Y_C6 = mt_radius_C6 * sin_angle_C6

						x = mt_X_C6[7] - new_X_C6
						y = mt_Y_C6[6] - new_Y_C6
					ENDIF
			   	BREAK	

			ENDSWITCH

			SET_OBJECT_COORDINATES_AND_VELOCITY mt_obj_C6[pointer_C6] x y z

			pointer_C6 ++

		ENDWHILE

	ENDIF

	IF mt_timer_C6 > 500
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			IF IS_CHAR_IN_AREA_2D scplayer 945.7303 2113.1968 947.4120 2112.3186 FALSE
			 
			//LOCATE_CHAR_ON_FOOT_2D scplayer 944.4756 2112.9255 1.2 1.2 FALSE
				IF mt_status_C6 = 0
					mt_accel_C6 = 0.0001
					mt_status_C6 = 1
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 941.9891 2144.2883 1016.2064 SOUND_MEAT_TRACK_START
				ELSE
					mt_accel_C6 = -0.0001
					mt_status_C6 = 0
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 941.9891 2144.2883 1016.2064 SOUND_MEAT_TRACK_STOP
				ENDIF
				mt_timer_C6 = 0
			ENDIF
		ENDIF
	ENDIF 	

RETURN

// ###################
// ## freezer gosubs
// ###################

// freezer door controller
LVAR_INT opening_sound_C6 waiting_C6
LVAR_FLOAT collision_y_C6
mission_casino6_SUB_freezer_door:
	
	SWITCH fd_status_C6

		CASE 0 // open
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE	  
				IF IS_CHAR_IN_AREA_2D scplayer 951.9540 2115.7444 949.9608 2114.6987 FALSE  
					fd_speed_C6 = 0.0
					fd_accel_C6 = -0.0005
					
					fd_status_C6 ++
				ENDIF				
			ENDIF
		BREAK

		CASE 1 // closing
			IF opening_sound_C6 = 0
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT fd_C6 SOUND_FREEZER_OPEN
				opening_sound_C6 = 1	
			ENDIF				 
			
			GET_OBJECT_COORDINATES fd_C6 x y z

			collision_y_C6 = y - 2.5
			IF LOCATE_CHAR_ON_FOOT_2D scplayer x collision_y_C6 0.8 0.8 TRUE
				BREAK
			ENDIF

			
			fd_speed_C6 += fd_accel_C6
			IF fd_speed_C6 < -0.4
				fd_speed_C6 = -0.4
			ENDIF
			y += fd_speed_C6
			IF y <= 2117.448
				y = 2117.448
				fd_status_C6 ++

				
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT fd_C6 SOUND_FREEZER_CLOSE
				opening_sound_C6 = 0	
				
				IF IS_CHAR_IN_AREA_3D scplayer 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
					player_trapped_C6 = 1
					f_timer_C6 = 0
				ELSE
					player_trapped_C6 = 0
				ENDIF

				IF IS_CHAR_IN_AREA_3D rosenberg_C6 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
					rosenberg_trapped_C6 = 1
					f_timer_C6 = 0
					IF player_trapped_C6 = 1
						fail_text_flag_C6 = 2
						$fail_text_C6 = CAS6_F2
					ELSE
						load_sample = SOUND_CAS6_FC
						$load_text = &CAS6_FC
						START_NEW_SCRIPT audio_load_and_play 2 102 rosenberg_C6 // CJ, some guy's locked me in the freezer!							
						//PRINT_NOW CAS6_12 5000 1 // CJ some fucker has locked me in the freezer
						rosenberg_text_timer_C6 = 0
						rosenberg_moan_C6 = 1
						TIMERB = 0
					ENDIF
				ELSE
					IF player_trapped_C6 = 1
						// get rosenberg to open door for player
						load_sample = SOUND_CAS6_FP
						$load_text = &CAS6_FP
						START_NEW_SCRIPT audio_load_and_play 1 102 scplayer // Get me out of this freezer, Rosie!							
						//PRINT_NOW CAS6_15 5000 1 // CJ : rosenberg im trapped in this fucking freezer get me out of here.
						rosenberg_text_timer_C6 = 0

						SET_CHAR_PROOFS rosenberg_C6 TRUE TRUE TRUE TRUE TRUE
						IF rosenberg_status_C6 >= 15
							rosenberg_status_C6 = 13 // open door
						ENDIF
					ELSE
						// check to see if rosenberg was going in the freezer and the door was shut on him
						IF rosenberg_status_C6 = 15
							rosenberg_status_C6 = 13 // open door
						ENDIF
					ENDIF
					rosenberg_trapped_C6 = 0
				ENDIF
			ELSE
				// check for player getting stuck in the collision of the door

			ENDIF 
			SET_OBJECT_COORDINATES_AND_VELOCITY fd_C6 x y z
		BREAK

		CASE 2 // closed
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				IF IS_CHAR_IN_AREA_2D scplayer 951.9540 2115.7444 949.9608 2114.6987 FALSE
				
					fd_speed_C6 = 0.0
					fd_accel_C6 = 0.0005

					fd_status_C6 ++

					IF rosenberg_trapped_C6 = 1

						IF NOT IS_CHAR_DEAD mafia_C6[14] 
						OR NOT IS_CHAR_DEAD mafia_C6[15]
							load_sample = SOUND_CAS6_LD
							$load_text = &CAS6_LD
							START_NEW_SCRIPT audio_load_and_play 1 102 scplayer // Stay in there while I finish off these assholes!
							waiting_C6 = 1 
							rosenberg_text_timer_C6 = 0					
					 	ELSE
							IF NOT IS_CHAR_DEAD mafia_C6[4]
							OR NOT IS_CHAR_DEAD mafia_C6[5]
							OR NOT IS_CHAR_DEAD mafia_C6[6]
							OR NOT IS_CHAR_DEAD mafia_C6[7]
							OR NOT IS_CHAR_DEAD mafia_C6[8]
							OR NOT IS_CHAR_DEAD mafia_C6[9] 
								load_sample = SOUND_CAS6_LD
								$load_text = &CAS6_LD
								START_NEW_SCRIPT audio_load_and_play 1 102 scplayer// Stay in there while I finish off these assholes!	
								waiting_C6 = 1 
								rosenberg_text_timer_C6 = 0						
							ELSE
								IF NOT IS_CHAR_DEAD mafia_C6[10]
								OR NOT IS_CHAR_DEAD mafia_C6[11]
								OR NOT IS_CHAR_DEAD mafia_C6[1] 
									load_sample = SOUND_CAS6_LD
									$load_text = &CAS6_LD
									START_NEW_SCRIPT audio_load_and_play 1 102 scplayer// Stay in there while I finish off these assholes!	
									waiting_C6 = 1 
									rosenberg_text_timer_C6 = 0
								ELSE
									// random sample
									GENERATE_RANDOM_INT_IN_RANGE 0 151 rosenberg_random_text_C6
									IF rosenberg_random_text_C6 < 50
										load_sample = SOUND_CAS6_LC
										$load_text = &CAS6_LC
									ELSE
										IF rosenberg_random_text_C6 > 100
											load_sample = SOUND_CAS6_FK
											$load_text = &CAS6_FK
										ELSE
											load_sample = SOUND_CAS6_LB
											$load_text = &CAS6_LB
										ENDIF
									ENDIF

									START_NEW_SCRIPT audio_load_and_play 2 102 rosenberg_C6 // Thank you, man, I thought I was a popsicle for sure!
									rosenberg_text_timer_C6 = 0	
								ENDIF	 	
							ENDIF
						ENDIF

					ENDIF
					player_trapped_C6 = 0
					rosenberg_trapped_C6 = 0
				ENDIF	
			ENDIF				
		BREAK

		CASE 3 // opening
			IF opening_sound_C6 = 0
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT fd_C6 SOUND_FREEZER_OPEN
				opening_sound_C6 = 1	
			ENDIF	

			GET_OBJECT_COORDINATES fd_C6 x y z
			
			fd_speed_C6 += fd_accel_C6
			IF fd_speed_C6 > 0.4
				fd_speed_C6 = 0.4
			ENDIF
			y += fd_speed_C6
			IF y >= 2121.648
				y = 2121.648
				fd_status_C6 = 0
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT fd_C6 SOUND_FREEZER_CLOSE
				opening_sound_C6 = 0	
			ENDIF 
			SET_OBJECT_COORDINATES_AND_VELOCITY fd_C6 x y z	
			
			player_trapped_C6 = 0
			rosenberg_trapped_C6 = 0	
		BREAK

	ENDSWITCH
RETURN


mission_casino6_SUB_freezer_health_C6:

	IF player_trapped_C6 = 1
		IF f_timer_C6 > 1500
			GET_CHAR_HEALTH scplayer pointer_C6
			pointer_C6 -= 3
			IF pointer_C6 <= 0
				//TASK_DIE scplayer
				fail_text_flag_C6 = 1					  
				$fail_text_C6 = CAS6_F3
				SET_CHAR_HEALTH scplayer 0
			ELSE
				DAMAGE_CHAR scplayer 3 FALSE
			ENDIF
			f_timer_C6 = 0
		ENDIF
	ELSE
		IF rosenberg_trapped_C6 = 1
			IF f_timer_C6 > 1500
				GET_CHAR_HEALTH rosenberg_C6 pointer_C6
				pointer_C6 -= 6
				IF pointer_C6 <= 0
					//TASK_DIE rosenberg_C6
					fail_text_flag_C6 = 1
					$fail_text_C6 = CAS6_F1
					SET_CHAR_HEALTH rosenberg_C6 0
				ELSE
					DAMAGE_CHAR rosenberg_C6 6 FALSE
				ENDIF
				f_timer_C6 = 0
			ENDIF
		ENDIF
	ENDIF

RETURN

// ###################
// ## ambient gosubs
// ###################

LVAR_INT heathaze_active_C6
mission_casino6_SUB_heathaze_effect:

	SWITCH heathaze_active_C6
		CASE 0 // heathaze off
			IF LOCATE_CHAR_ON_FOOT_2D scplayer 941.5 2114.0 9.0 9.0 FALSE
				SET_HEATHAZE_EFFECT TRUE
				heathaze_active_C6 ++
			ENDIF 
		BREAK 

		CASE 1 // heathaze on
			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer 941.5 2114.0 11.0 11.0 FALSE
				SET_HEATHAZE_EFFECT FALSE
				heathaze_active_C6 --
			ENDIF 
		BREAK

	ENDSWITCH

RETURN

// ##############								   
// ## ambient audio
// ##############

mission_casino6_SUB_ambient_audio:

	SWITCH audio_status_C6
		CASE 0			
			IF audio_pointer_C6 < 3
				load_sample = audio_sound_label_C6[audio_pointer_C6]
				$load_text = $audio_text_label_C6[audio_pointer_C6]
				START_NEW_SCRIPT audio_load_and_play 2 70 audio_speaker_C6[audio_pointer_C6] 
				audio_pointer_C6 ++
				audio_status_C6 ++
			ENDIF
		CASE 1
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 0
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		
		CASE 2			
			IF audio_pointer_C6 < 5
				IF TIMERA > 2500
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 2 70 audio_speaker_C6[audio_pointer_C6] 
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ELSE
				TIMERA = 0
				audio_status_C6 = 6
				audio_pointer_C6 = 7
			ENDIF
		CASE 3
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 2
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		CASE 4			
			IF audio_pointer_C6 < 7
				IF TIMERA > 2500
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 2 70 audio_speaker_C6[audio_pointer_C6] 
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ELSE
				TIMERA = 0
				audio_status_C6 = 6
				audio_pointer_C6 = 7
			ENDIF
		CASE 5
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 4
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		CASE 6			
			IF audio_pointer_C6 < 13
				IF TIMERA > 7000
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 2 70 audio_speaker_C6[audio_pointer_C6] 
					audio_pointer_C6 ++
					audio_status_C6 ++
					TIMERA = 0
				ENDIF
			ENDIF
		CASE 7
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 6
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		CASE 8			
			IF TIMERA > 9000
				load_sample = audio_sound_label_C6[13]
				$load_text = $audio_text_label_C6[13]
				START_NEW_SCRIPT audio_load_and_play 2 70 audio_speaker_C6[audio_pointer_C6] 
				audio_pointer_C6 ++
				audio_status_C6 ++
				TIMERA = 0
			ENDIF
		
	ENDSWITCH	
RETURN

LVAR_INT rosenberg_moan_C6 text_switch_C6
mission_casino6_SUB_infreezer_audio:

	IF rosenberg_moan_C6 = 1
	AND TIMERB > 20000
		IF text_switch_C6 = 0 
			load_sample = SOUND_CAS6_FF
			$load_text = &CAS6_FF
			START_NEW_SCRIPT audio_load_and_play 2 75 rosenberg_C6
			text_switch_C6 = 1
		ELSE
			load_sample = SOUND_CAS6_LA
			$load_text = &CAS6_LA
			START_NEW_SCRIPT audio_load_and_play 2 75 scplayer
			text_switch_C6 = 0
		ENDIF

		TIMERB = 0 		
	ENDIF

RETURN
// ##############
// ## help text
// ##############

LVAR_INT h_text_status_C6 h_text_timer_C6 h_text_player_C6 h_text_rosenberg_C6 
mission_casino6_SUB_help_text:

	SWITCH h_text_status_C6
	
		CASE 0
			IF IS_CHAR_IN_AREA_2D scplayer 950.4901 2113.2043 940.3504 2103.8396 FALSE
				PRINT_HELP_FOREVER CAS6_H0  
				h_text_timer_C6 = 0
				h_text_player_C6 = 0
				h_text_rosenberg_C6 = 0
				h_text_status_C6 ++
			ENDIF
		BREAK

		CASE 1
			IF h_text_timer_C6 > 8000
				CLEAR_HELP
				h_text_status_C6 ++
			ELSE
				IF player_trapped_C6 = 1
				OR rosenberg_trapped_C6 = 1
					CLEAR_HELP
					h_text_status_C6 ++
				ENDIF
			ENDIF
		BREAK

		CASE 2
			IF player_trapped_C6 = 1
			AND h_text_player_C6 = 0
				IF NOT rosenberg_trapped_C6 = 1
					PRINT_HELP_FOREVER CAS6_H3
					h_text_timer_C6 = 0
					h_text_status_C6 = 3
				ENDIF
			ELSE
				IF rosenberg_trapped_C6 =1
					IF h_text_rosenberg_C6 = 0
						PRINT_HELP_FOREVER CAS6_H1  
						h_text_timer_C6 = 0
						h_text_status_C6 = 4
					ELSE
						IF h_text_rosenberg_C6 = 1
							h_text_status_C6 = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
 
		BREAK

		CASE 3
			IF h_text_timer_C6 > 8000
			OR player_trapped_C6 = 0
				CLEAR_HELP
				h_text_player_C6 = 1
				IF h_text_rosenberg_C6 = 3
					h_text_status_C6 = -1
				ELSE
					h_text_status_C6 = 2
				ENDIF
			ENDIF
		BREAK

		CASE 4
			IF h_text_rosenberg_C6 = 0
				IF h_text_timer_C6 > 8000
					CLEAR_HELP
					PRINT_HELP_FOREVER CAS6_H2
					h_text_timer_C6 = 0
					h_text_rosenberg_C6 = 2
				ELSE
					IF rosenberg_trapped_C6 = 0
						CLEAR_HELP
						h_text_rosenberg_C6 = 1
						h_text_status_C6 = 2
					ENDIF
				ENDIF	
			ELSE
				IF h_text_rosenberg_C6 = 1
					PRINT_HELP_FOREVER CAS6_H2
					h_text_timer_C6 = 0
					h_text_rosenberg_C6 = 2
				ELSE
					IF h_text_rosenberg_C6 = 2
						IF h_text_timer_C6 > 8000
						OR rosenberg_trapped_C6 = 0
							CLEAR_HELP
							h_text_rosenberg_C6 = 3
							h_text_status_C6 = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF				 
		BREAK

	ENDSWITCH
	  
RETURN


LVAR_INT g_text_status_C6
mission_casino6_SUB_game_text:
	
	SWITCH g_text_status_C6

		CASE 0
			IF rosenberg_trapped_C6 = 1
				PRINT_NOW CAS6_G2 8000 1
				g_text_status_C6 = 1
			ELSE
				IF player_trapped_C6 = 0
					PRINT_NOW CAS6_G1 8000 1
					IF NOT DOES_BLIP_EXIST goto_blip_C6
						ADD_BLIP_FOR_COORD 964.7880 2159.5039 1011.0546 goto_blip_C6
						SET_BLIP_ENTRY_EXIT goto_blip_C6 964.7880 2159.5039 4.0
					ENDIF
					SWITCH_ENTRY_EXIT abatoir TRUE 
					g_text_status_C6 = 3
					IF rosenberg_text_flag_C6[5] = 0
						audio_status_C6 = 8
						TIMERA = 0
						rosenberg_text_flag_C6[5] = 1
					ENDIF
				ELSE
					PRINT_NOW CAS6_G3 8000 1
					g_text_status_C6 = 2	
				ENDIF
			ENDIF
		BREAK

		CASE 1
			IF rosenberg_trapped_C6 = 0
				PRINT_NOW CAS6_G4 8000 1
				IF NOT DOES_BLIP_EXIST goto_blip_C6
					ADD_BLIP_FOR_COORD 964.7880 2159.5039 1011.0546 goto_blip_C6
					SET_BLIP_ENTRY_EXIT goto_blip_C6 964.7880 2159.5039 4.0
				ENDIF
				SWITCH_ENTRY_EXIT abatoir TRUE 
				g_text_status_C6 = 3
			ENDIF
		BREAK

		CASE 2
			IF player_trapped_C6 = 0
				PRINT_NOW CAS6_G4 8000 1
				IF NOT DOES_BLIP_EXIST goto_blip_C6
					ADD_BLIP_FOR_COORD 964.7880 2159.5039 1011.0546 goto_blip_C6
					SET_BLIP_ENTRY_EXIT goto_blip_C6 964.7880 2159.5039 4.0
				ENDIF
				SWITCH_ENTRY_EXIT abatoir TRUE 
				g_text_status_C6 = 3
			ENDIF
		BREAK

		CASE 3
			IF player_trapped_C6 = 1
			OR rosenberg_trapped_C6 = 1
				REMOVE_BLIP goto_blip_C6
				SWITCH_ENTRY_EXIT abatoir FALSE
				g_text_status_C6 = 0
			ENDIF
		BREAK

	ENDSWITCH
RETURN


mission_casino6_SUB_tidy_up_factory:
	
	// objects
	pointer_C6 = 0
	WHILE pointer_C6 < 6
		MARK_OBJECT_AS_NO_LONGER_NEEDED mt_obj_C6[pointer_C6]
		MARK_OBJECT_AS_NO_LONGER_NEEDED mt_carcass_C6[pointer_C6]
		MARK_OBJECT_AS_NO_LONGER_NEEDED f_carcass_C6[pointer_C6]
		pointer_C6 ++ 	
	ENDWHILE

	REMOVE_PICKUP player_fire_ex_C6
	REMOVE_PICKUP freezer_armour_C6

	MARK_OBJECT_AS_NO_LONGER_NEEDED fire_ex_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED mt_switch_C6  
	MARK_OBJECT_AS_NO_LONGER_NEEDED fd_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED fd_switch_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED molotov_C6[0]
	MARK_OBJECT_AS_NO_LONGER_NEEDED molotov_C6[1]

	// chars
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3 
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4 

	// objects
	MARK_MODEL_AS_NO_LONGER_NEEDED ab_carcass
	MARK_MODEL_AS_NO_LONGER_NEEDED ab_hook
	MARK_MODEL_AS_NO_LONGER_NEEDED freezer_door
	MARK_MODEL_AS_NO_LONGER_NEEDED sec_keypad
	MARK_MODEL_AS_NO_LONGER_NEEDED cardboardbox2

	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	MARK_MODEL_AS_NO_LONGER_NEEDED BAT
	MARK_MODEL_AS_NO_LONGER_NEEDED CHNSAW
	MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX
	MARK_MODEL_AS_NO_LONGER_NEEDED BODYARMOUR
	
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_bag_1
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_1
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_2
RETURN



// ###########################
// ##
// ## DRIVE BACK TO THE CASINO
// ##
// ###########################

LVAR_INT players_group_C6 spare_car_C6 
mission_casino6_MAIN_drive_back_to_casino:

	active_area_C6 = 1
	WHILE active_area_C6 = 1 
		WAIT 0
		GET_CHAR_AREA_VISIBLE scplayer active_area_C6
	ENDWHILE  

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	DISABLE_PLAYER_SPRINT player1 FALSE

	CLEAR_ONSCREEN_COUNTER rosenbergs_health_C6
	REMOVE_BLIP rosenberg_blip_C6
	CLEAR_HELP
	SET_RADAR_ZOOM 0

	GOSUB mission_casino6_SUB_tidy_up_factory

	SWITCH_ENTRY_EXIT abatoir FALSE

	REMOVE_BLIP goto_blip_C6
	ADD_BLIP_FOR_COORD 2165.1851 1692.3173 9.8203 goto_blip_C6
	
	CLEAR_WANTED_LEVEL player1

	IF NOT IS_CHAR_DEAD rosenberg_C6
		SET_CHAR_COORDINATES rosenberg_C6 970.7965 2155.6436 9.8203
		SET_CHAR_HEADING rosenberg_C6 295.3334
		CLEAR_CHAR_TASKS_IMMEDIATELY rosenberg_C6
		SET_CHAR_AREA_VISIBLE rosenberg_C6 0
		SET_CHAR_NEVER_LEAVES_GROUP rosenberg_C6 TRUE
		SET_GROUP_MEMBER players_group_C6 rosenberg_C6
		REMOVE_ALL_CHAR_WEAPONS rosenberg_C6
		REMOVE_BLIP rosenberg_blip_C6
		ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
		SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE
	ENDIF

	IF save_car_C6 = 1

		REQUEST_MODEL car_model_C6
		WHILE NOT HAS_MODEL_LOADED car_model_C6
			WAIT 0
		ENDWHILE
					  
		CREATE_CAR car_model_C6 977.1431 2164.0625 9.8203 player_vehicle_C6 
		SET_CAR_HEADING player_vehicle_C6 194.2554

//		CREATE_CAR car_model_C6 car_pos_C6[0] car_pos_C6[1] car_pos_C6[2] player_vehicle_C6 
//		SET_CAR_HEADING player_vehicle_C6 car_pos_C6[3]
										  
		CHANGE_CAR_COLOUR player_vehicle_C6 car_colours_C6[0] car_colours_C6[1] 
		SET_EXTRA_CAR_COLOURS player_vehicle_C6 car_colours_C6[2] car_colours_C6[3] 

		// check tyres
		pointer_C6 = 0
		WHILE pointer_C6 < 4
			IF IS_BIT_SET car_tyres_C6 pointer_C6
				BURST_CAR_TYRE player_vehicle_C6 pointer_C6 	
			ENDIF
			pointer_C6 ++
		ENDWHILE	

		// check doors
		IF NOT car_doors_C6 = -1 
			pointer_C6 = 0
			WHILE pointer_C6 < 6
				IF IS_BIT_SET car_doors_C6 pointer_C6
					DAMAGE_CAR_DOOR  player_vehicle_C6 pointer_C6 	
				ENDIF
				pointer_C6 ++
			ENDWHILE	
		ENDIF

	ENDIF

//	REQUEST_MODEL SENTINEL 
//
//	WHILE NOT HAS_MODEL_LOADED SENTINEL 
//		WAIT 0
//	ENDWHILE
//		
//	CREATE_CAR SENTINEL 983.2769 2164.4954 9.8203 spare_car_C6
//	SET_CAR_HEADING spare_car_C6 198.0331
	
	DO_FADE 500 FADE_IN
	
	PRINT_NOW CAS6_G5 8000 1

	rosenberg_text_status_C6 = 0

	CLEAR_WANTED_LEVEL player1
	SET_MAX_WANTED_LEVEL 6

	IF NOT SLOT_status[0] < 0
		SLOT_status[0] = -2
	ENDIF
	IF NOT SLOT_status[0] < 0
		SLOT_status[1] = -2
	ENDIF

	// audio variables
	// outside banter/instructions
	$audio_text_label_C6[0] = &CAS6_HA
	audio_sound_label_C6[0]= SOUND_CAS6_HA
	audio_speaker_C6[0] = rosenberg_C6
	$audio_text_label_C6[1] = &CAS6_HB
	audio_sound_label_C6[1]= SOUND_CAS6_HB
	audio_speaker_C6[1] = rosenberg_C6
	$audio_text_label_C6[2] = &CAS6_HC
	audio_sound_label_C6[2]= SOUND_CAS6_HC
	audio_speaker_C6[2] = rosenberg_C6
	$audio_text_label_C6[3] = &CAS6_HD
	audio_sound_label_C6[3]= SOUND_CAS6_HD
	audio_speaker_C6[3] = scplayer

	// en route back in car
	$audio_text_label_C6[4] = &CAS6_JA
	audio_sound_label_C6[4]= SOUND_CAS6_JA
	audio_speaker_C6[4] = rosenberg_C6
	$audio_text_label_C6[5] = &CAS6_JB
	audio_sound_label_C6[5]= SOUND_CAS6_JB
	audio_speaker_C6[5] = rosenberg_C6
	$audio_text_label_C6[6] = &CAS6_JC
	audio_sound_label_C6[6]= SOUND_CAS6_JC
	audio_speaker_C6[6] = rosenberg_C6
	$audio_text_label_C6[7] = &CAS6_JD
	audio_sound_label_C6[7]= SOUND_CAS6_JD
	audio_speaker_C6[7] = scplayer
	$audio_text_label_C6[8] = &CAS6_JE
	audio_sound_label_C6[8]= SOUND_CAS6_JE
	audio_speaker_C6[8] = rosenberg_C6
	$audio_text_label_C6[9] = &CAS6_JF
	audio_sound_label_C6[9]= SOUND_CAS6_JF
	audio_speaker_C6[9] = rosenberg_C6
	$audio_text_label_C6[10] = &CAS6_JG
	audio_sound_label_C6[10]= SOUND_CAS6_JG
	audio_speaker_C6[10] = rosenberg_C6
//	$audio_text_label_C6[11] = &CAS6_JH
//	audio_sound_label_C6[11]= SOUND_CAS6_JH
//	audio_speaker_C6[11] = rosenberg_C6
	$audio_text_label_C6[11] = &CAS6_JJ
	audio_sound_label_C6[11]= SOUND_CAS6_JJ
	audio_speaker_C6[11] = scplayer
	$audio_text_label_C6[12] = &CAS6_JK
	audio_sound_label_C6[12]= SOUND_CAS6_JK
	audio_speaker_C6[12] = scplayer
	$audio_text_label_C6[13] = &CAS6_JL
	audio_sound_label_C6[13]= SOUND_CAS6_JL
	audio_speaker_C6[13] = scplayer
	$audio_text_label_C6[14] = &CAS6_JM
	audio_sound_label_C6[14]= SOUND_CAS6_JM
	audio_speaker_C6[14] = rosenberg_C6
	$audio_text_label_C6[15] = &CAS6_JN
	audio_sound_label_C6[15]= SOUND_CAS6_JN
	audio_speaker_C6[15] = scplayer
	$audio_text_label_C6[16] = &CAS6_JO
	audio_sound_label_C6[16]= SOUND_CAS6_JO
	audio_speaker_C6[16] = scplayer

	// cutscene of rosenberg going in casino
	$audio_text_label_C6[17] = &CAS6_KA
	audio_sound_label_C6[17]= SOUND_CAS6_KA
	audio_speaker_C6[17] = scplayer
	$audio_text_label_C6[18] = &CAS6_KB
	audio_sound_label_C6[18]= SOUND_CAS6_KB
	audio_speaker_C6[18] = rosenberg_C6
	$audio_text_label_C6[19] = &CAS6_KC
	audio_sound_label_C6[19]= SOUND_CAS6_KC
	audio_speaker_C6[19] = rosenberg_C6
	$audio_text_label_C6[20] = &CAS6_KD
	audio_sound_label_C6[20]= SOUND_CAS6_KD
	audio_speaker_C6[20] = rosenberg_C6
	$audio_text_label_C6[21] = &CAS6_KE
	audio_sound_label_C6[21]= SOUND_CAS6_KE
	audio_speaker_C6[21] = rosenberg_C6

	audio_pointer_C6 = 0
	audio_status_C6	= 0
	TIMERB = 0

	SET_PLAYER_GROUP_RECRUITMENT player1 TRUE

mission_casino6_MAIN_drive_back_to_casino_loop:

	WAIT 0

	IF NOT IS_CHAR_DEAD rosenberg_C6
		
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2165.1851 1692.3173 9.8203 4.0 4.0 4.0 TRUE
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 15.0 15.0 FALSE
				GOTO mission_casino6_CUT_end_of_mission
			ELSE
				IF rosenberg_text_status_C6 < 2 
					rosenberg_text_status_C6 = 2
				ENDIF
				GOSUB mission_casino6_SUB_rosenberg_text
				GOSUB mission_casino6_SUB_return_dialogue	
			ENDIF
		ELSE
			GOSUB mission_casino6_SUB_rosenberg_blip
			GOSUB mission_casino6_SUB_rosenberg_text
			GOSUB mission_casino6_SUB_return_dialogue
		ENDIF

		// interior check
		GET_AREA_VISIBLE active_area_C6  
		IF NOT active_area_C6 = 0
			CLEAR_PRINTS
			FREEZE_CHAR_POSITION rosenberg_C6 TRUE

			REMOVE_BLIP goto_blip_C6
			REMOVE_BLIP rosenberg_blip_C6
			REMOVE_CHAR_FROM_GROUP rosenberg_C6
			
			rosenberg_blip_status_C6 = 0

			GOTO mission_casino6_MAIN_rosenberg_interior 	
		ENDIF

	ELSE
		fail_text_flag_C6 = 1
		$fail_text_C6 = CAS6_F0		
		GOTO mission_casino6_FAILED
	ENDIF


GOTO mission_casino6_MAIN_drive_back_to_casino_loop

LVAR_FLOAT ped_node_X_C6 ped_node_Y_C6 ped_node_Z_C6  
mission_casino6_MAIN_rosenberg_interior:

	WAIT 0

	IF NOT IS_CHAR_DEAD rosenberg_C6	
		GET_AREA_VISIBLE active_area_C6 
		IF active_area_C6 = 0

			FREEZE_CHAR_POSITION rosenberg_C6 FALSE
			SET_CHAR_NEVER_LEAVES_GROUP rosenberg_C6 TRUE
			SET_GROUP_MEMBER players_group_C6 rosenberg_C6	
			
			REMOVE_BLIP rosenberg_blip_C6
			ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
			SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE 
			CHANGE_BLIP_DISPLAY rosenberg_blip_C6 BLIP_ONLY

			ADD_BLIP_FOR_COORD 2165.1851 1692.3173 9.8203 goto_blip_C6
			
			TIMERA = 0
			rosenberg_text_status_C6 = 3

			GOTO mission_casino6_MAIN_drive_back_to_casino_loop
		ENDIF
	ELSE
		// just in for safety reasons
		fail_text_flag_C6 = 1
		$fail_text_C6 = CAS6_F0		
		GOTO mission_casino6_FAILED
	ENDIF
	 

GOTO mission_casino6_MAIN_rosenberg_interior




LVAR_INT rosenberg_blip_status_C6 player_vehicle_C6
mission_casino6_SUB_rosenberg_blip:

	SWITCH rosenberg_blip_status_C6
	
		CASE 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer player_vehicle_C6
				IF IS_CHAR_IN_CAR rosenberg_C6 player_vehicle_C6
					REMOVE_BLIP rosenberg_blip_C6
					rosenberg_blip_status_C6 ++ 
				ENDIF
			ENDIF 	 	
		BREAK

		CASE 1
			IF NOT IS_CHAR_IN_ANY_CAR rosenberg_C6
				REMOVE_BLIP rosenberg_blip_C6
				ADD_BLIP_FOR_CHAR rosenberg_C6 rosenberg_blip_C6
				SET_BLIP_AS_FRIENDLY rosenberg_blip_C6 TRUE
				CHANGE_BLIP_DISPLAY rosenberg_blip_C6 BLIP_ONLY
				rosenberg_blip_status_C6 --
			ENDIF
		BREAK

	ENDSWITCH 

RETURN


LVAR_INT rosenberg_text_status_C6
mission_casino6_SUB_rosenberg_text:

	SWITCH rosenberg_text_status_C6
	
		CASE 0
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 60.0 60.0 FALSE
				PRINT_NOW CAS6_G6 8000 1
				TIMERA = 0
				rosenberg_text_status_C6 ++
			ENDIF
		BREAK

		CASE 1
			IF TIMERA > 20000
				rosenberg_text_status_C6 --
			ENDIF
		BREAK

		CASE 2
			PRINT_NOW CAS6_G7 8000 1
			TIMERA = 0
			rosenberg_text_status_C6 ++
		BREAK

		CASE 3
			IF TIMERA > 20000
				rosenberg_text_status_C6 = 0
			ENDIF
		BREAK

	ENDSWITCH
	 
RETURN


LVAR_INT audio_pointer_C6 audio_status_C6 audio_sound_label_C6[23] audio_speaker_C6[23]
LVAR_TEXT_LABEL $audio_text_label_C6[23] 
mission_casino6_SUB_return_dialogue:

	SWITCH audio_status_C6

		CASE 0
			IF TIMERB > 8000
				audio_status_C6 ++
			ENDIF	
		BREAK

		// start dialogue
		CASE 1
			IF audio_pointer_C6 < 4
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer rosenberg_C6 15.0 15.0 FALSE
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_C6[audio_pointer_C6] 
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ELSE
				audio_status_C6 = 3
				BREAK
			ENDIF
		CASE 2
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 1
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK		

		// in car dialogue
		CASE 3
			IF rosenberg_blip_status_C6 = 1 
				IF audio_pointer_C6 < 17
					load_sample = audio_sound_label_C6[audio_pointer_C6]
					$load_text = $audio_text_label_C6[audio_pointer_C6]
					START_NEW_SCRIPT audio_load_and_play 1 100 audio_speaker_C6[audio_pointer_C6]
					audio_pointer_C6 ++
					audio_status_C6 ++
				ENDIF
			ELSE
				IF NOT audio_pointer_C6 = 4 
					IF NOT SLOT_status[0] < 0
						SLOT_status[0] = -2
					ENDIF
					IF NOT SLOT_status[0] < 0
						SLOT_status[1] = -2
					ENDIF
				ENDIF
				BREAK 
			ENDIF
		CASE 4
			IF SLOT_status[preload_slot] = -3
				audio_status_C6 = 3
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

	ENDSWITCH

RETURN


mission_casino6_CUT_end_of_mission:

	SET_PLAYER_CONTROL player1 OFF
	
	// clear audio slots if still playing
	IF NOT SLOT_status[0] < 0
		SLOT_status[0] = -2
	ENDIF
	IF NOT SLOT_status[0] < 0
		SLOT_status[1] = -2
	ENDIF

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2171.5107 1687.3682 9.8203
	ELSE
		SET_CHAR_COORDINATES scplayer 2171.5107 1687.3682 9.8203
	ENDIF
	SET_CHAR_HEADING scplayer 352.2379
	
	IF NOT IS_CHAR_DEAD rosenberg_C6
		IF IS_CHAR_IN_ANY_CAR rosenberg_C6
			WARP_CHAR_FROM_CAR_TO_COORD rosenberg_C6 2171.5193 1688.5265 9.8203
		ELSE
			SET_CHAR_COORDINATES rosenberg_C6 2171.5193 1688.5265 9.8203
		ENDIF
		SET_CHAR_HEADING rosenberg_C6 194.0510
	ENDIF   

	SET_FIXED_CAMERA_POSITION 2172.8564 1688.2550 11.0927 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2171.8906 1688.0133 11.1875 JUMP_CUT
  
	CLEAR_AREA 2171.5107 1687.3682 9.8203 60.0 TRUE 
	
	DELETE_CAR player_vehicle_C6

	audio_pointer_C6 = 17

	SWITCH_WIDESCREEN ON

	DO_FADE 500 FADE_IN

SKIP_CUTSCENE_START


	WHILE audio_pointer_C6 < 22 

		load_sample = audio_sound_label_C6[audio_pointer_C6]
		$load_text = $audio_text_label_C6[audio_pointer_C6]
		START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_C6[audio_pointer_C6]

		WHILE NOT SLOT_status[preload_slot] = -3
			WAIT 0
		ENDWHILE			
		SLOT_status[preload_slot] = -4

		audio_pointer_C6 ++

	ENDWHILE

	IF NOT IS_CHAR_DEAD rosenberg_C6
		TASK_GO_STRAIGHT_TO_COORD rosenberg_C6 2178.0149 1689.6980 9.8203 PEDMOVE_WALK -2
		DO_FADE 2500 FADE_OUT
	ENDIF

	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_END

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE
	
	CLEAR_PRINTS
	SET_CHAR_HEADING scplayer 95.0
	
	IF NOT IS_CHAR_DEAD rosenberg_C6
		DELETE_CHAR rosenberg_C6	
	ENDIF

	DO_FADE 0 FADE_IN 
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER







  
// ###########################
// ##
// ## END OF MISSION SCRIPTS
// ##
// ###########################

mission_casino6_PASSED:
	
	flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( CASINO6 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 8000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 8000//amount of cash
	AWARD_PLAYER_MISSION_RESPECT 25//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

	// stuff for craig
	SET_INT_STAT PASSED_CASINO6 1

	START_NEW_SCRIPT doc_mission_loop
	REMOVE_BLIP doc_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT docX docY docZ doc_blip_icon doc_contact_blip
	
	REMOVE_BLIP heist_contact_blip
	
	REMOVE_BLIP casino_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
	SET_BLIP_ENTRY_EXIT casino_contact_blip 2026.6028 1007.7353 20.0

RETURN


mission_casino6_FAILED:
	
	SWITCH fail_text_flag_C6
	
		CASE 1
			PRINT_NOW $fail_text_C6 5000 1
			IF player_trapped_C6 = 1
				SET_OBJECT_COORDINATES fd_C6 951.859 2121.648 1012.094 	
			ENDIF

		CASE 0
			PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
		BREAK

		CASE 2
			DO_FADE 0 FADE_OUT 

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON

			SET_FIXED_CAMERA_POSITION 963.9219 2124.6304 1013.8499 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 963.0831 2124.1853 1013.5363 JUMP_CUT

			SET_CHAR_COORDINATES scplayer 955.6613 2116.2026 1010.0303 
			SET_CHAR_HEADING scplayer 72.8077 

			IF NOT IS_CHAR_DEAD rosenberg_C6
				CLEAR_CHAR_TASKS_IMMEDIATELY rosenberg_C6
				TASK_TOGGLE_DUCK rosenberg_C6 FALSE
				SET_CHAR_DECISION_MAKER rosenberg_C6 empty_dm_C6
				SET_CHAR_COORDINATES rosenberg_C6 955.3200 2119.4722 1010.0312
				SET_CHAR_HEADING rosenberg_C6 113.9337
			ENDIF 

			// delete any mafia who might have got caught in the freezer
			pointer_C6 = 0
			WHILE pointer_C6 < number_of_mafia_C6
				IF NOT IS_CHAR_DEAD mafia_C6[pointer_C6]
					IF IS_CHAR_IN_AREA_3D mafia_C6[pointer_C6] 952.25 2147.0 1010.0 967.0 2114.0 1012.0 FALSE
						DELETE_CHAR mafia_C6[pointer_C6]
					ENDIF
				ENDIF
				pointer_C6 ++
			ENDWHILE

			TIMERA = 0
			WHILE TIMERA < 400
				WAIT 0
			ENDWHILE
			
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			PRINT_BIG ( M_FAIL ) 4000 1 //"Mission Failed"
			PRINT_NOW $fail_text_C6 4000 1

			TIMERA = 0
			WHILE TIMERA < 4000
				WAIT 0
			ENDWHILE
			
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			LOAD_SCENE 1578.4460 1770.6816 9.8280
			REQUEST_COLLISION 1578.4460 1770.6816

			SET_CHAR_COORDINATES_NO_OFFSET scplayer 1578.4460 1770.6816 9.8280
			SET_CHAR_HEADING scplayer 99.7567 

			RESET_STUFF_UPON_RESURRECTION

			TIMERA = 0
			WHILE TIMERA < 400
				WAIT 0
			ENDWHILE
		BREAK

	ENDSWITCH

	GET_AREA_VISIBLE active_area_C6
	IF NOT active_area_C6 = 0
		SWITCH_ENTRY_EXIT abatoir TRUE
	ENDIF
	
RETURN


mission_casino6_CLEANUP:
	
	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	ENDIF

	START_NEW_SCRIPT terminate_audio_controller

	entry_exit_area = 0
	entry_exit_status = FALSE
	$entry_exit_name = abatoir
	START_NEW_SCRIPT switch_entry_exit_after_mission

	SET_MAX_WANTED_LEVEL 6
	
	CLEAR_ONSCREEN_COUNTER rosenbergs_health_C6
	REMOVE_BLIP goto_blip_C6
	
	CLEAR_HELP
	
	SET_HEATHAZE_EFFECT FALSE
	SET_RADAR_ZOOM 0

	IF script_fire_check_C6[0] = 0
		REMOVE_SCRIPT_FIRE script_fire_C6[5]
		REMOVE_SCRIPT_FIRE script_fire_C6[4]		
	ENDIF
	IF script_fire_check_C6[1] = 0
		REMOVE_SCRIPT_FIRE script_fire_C6[3]
		REMOVE_SCRIPT_FIRE script_fire_C6[2]		
	ENDIF
	IF script_fire_check_C6[2] = 0
		REMOVE_SCRIPT_FIRE script_fire_C6[1]
		REMOVE_SCRIPT_FIRE script_fire_C6[0]		
	ENDIF

	// chars
	IF IS_PLAYER_PLAYING player1

		DISABLE_PLAYER_SPRINT player1 FALSE
		
		pointer_C6 = 0
		WHILE pointer_C6 < number_of_mafia_C6
			IF NOT IS_CHAR_DEAD mafia_C6[pointer_C6]
				SET_CHAR_DECISION_MAKER mafia_C6[pointer_C6] empty_dm_C6
				TASK_KILL_CHAR_ON_FOOT mafia_C6[pointer_C6] scplayer
				SET_CHAR_KEEP_TASK mafia_C6[pointer_C6] TRUE
			ENDIF
			//MARK_CHAR_AS_NO_LONGER_NEEDED mafia_C6[pointer_C6]
			REMOVE_BLIP mafia_blip_C6[pointer_C6]
			pointer_C6 ++
		ENDWHILE
	ENDIF

	MARK_CHAR_AS_NO_LONGER_NEEDED rosenberg_C6
	REMOVE_BLIP rosenberg_blip_C6

	// objects
	pointer_C6 = 0
	WHILE pointer_C6 < 6
		MARK_OBJECT_AS_NO_LONGER_NEEDED mt_obj_C6[pointer_C6]
		MARK_OBJECT_AS_NO_LONGER_NEEDED mt_carcass_C6[pointer_C6]
		MARK_OBJECT_AS_NO_LONGER_NEEDED f_carcass_C6[pointer_C6]
		pointer_C6 ++ 	
	ENDWHILE

	pointer_C6 = 0
	WHILE pointer_C6 < 6
		MARK_OBJECT_AS_NO_LONGER_NEEDED meat_chunks_C6[pointer_C6]
		pointer_C6 ++
	ENDWHILE

	REMOVE_PICKUP player_fire_ex_C6
	REMOVE_PICKUP freezer_armour_C6

	//MARK_CAR_AS_NO_LONGER_NEEDED spare_car_C6
	MARK_CAR_AS_NO_LONGER_NEEDED player_vehicle_C6
	
	MARK_OBJECT_AS_NO_LONGER_NEEDED fire_ex_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED mt_switch_C6  
	MARK_OBJECT_AS_NO_LONGER_NEEDED fd_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED fd_switch_C6
	MARK_OBJECT_AS_NO_LONGER_NEEDED molotov_C6[0]
	MARK_OBJECT_AS_NO_LONGER_NEEDED molotov_C6[1]

	KILL_FX_SYSTEM molotov_particleFX_C6[0]
	KILL_FX_SYSTEM molotov_particleFX_C6[1]

	// chars
	UNLOAD_SPECIAL_CHARACTER 1 // ROSENBERG
	
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3 
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4 

	// weapons
	MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	MARK_MODEL_AS_NO_LONGER_NEEDED CHNSAW
	MARK_MODEL_AS_NO_LONGER_NEEDED BAT
	MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX
	MARK_MODEL_AS_NO_LONGER_NEEDED BODYARMOUR

	// objects
	MARK_MODEL_AS_NO_LONGER_NEEDED ab_carcass
	MARK_MODEL_AS_NO_LONGER_NEEDED ab_hook
	MARK_MODEL_AS_NO_LONGER_NEEDED freezer_door
	MARK_MODEL_AS_NO_LONGER_NEEDED sec_keypad
	MARK_MODEL_AS_NO_LONGER_NEEDED cardboardbox2
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_bag_1
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_1
	MARK_MODEL_AS_NO_LONGER_NEEDED cj_meat_2
	
	MARK_MODEL_AS_NO_LONGER_NEEDED car_model_C6

	// stuff for craig
	GET_GAME_TIMER timer_mobile_start
	
	IF fail_text_flag_C6 = 2
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		DO_FADE 1000 FADE_IN
	ENDIF

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN

}

