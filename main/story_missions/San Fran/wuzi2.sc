MISSION_START

// ########################
// ##
// ##	Wuzi2.sc 
// ##
// ##	Amphibious Assault
// ##
// ## 	Simon Lashley
// ##
// ######################## 

SCRIPT_NAME wuzi2				
																	  
GOSUB mission_wuzi2_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_wuzi2_FAILED
ENDIF

GOSUB mission_wuzi2_CLEANUP						   

MISSION_END


{
// decision makers
LVAR_INT stealth_dm_W2 empty_dm_W2

// blips
LVAR_INT goto_blip_W2 

// timers
LVAR_INT this_frame_time_W2 time_diff_W2 last_frame_time_W2 

// fail text
LVAR_INT fail_text_flag_W2
LVAR_TEXT_LABEL fail_text_W2

// pointers
VAR_INT pointer_W2 pointer2_W2 pointer3_W2 pointer4_W2 guard_pointer_W2 guard_limit_W2 

// general
LVAR_INT sequence_W2 progress_W2 random_W2 random2_W2

// flags
LVAR_INT skipped_cutscene_W2 player_planted_bug_W2 player_been_on_main_boat_W2 player_section_W2

// camera stuff
LVAR_FLOAT camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 camera_look_X_W2 camera_look_Y_W2 camera_look_Z_W2


// audio stuff
LVAR_INT audio_pointer_W2 audio_sound_label_W2[63] 
LVAR_INT speech_ped_W2 speech_sample_W2 speech_context_W2 speech_priority_W2 boat_lost_W2 boat_found_W2 

 
LVAR_TEXT_LABEL $audio_text_label_W2[63]




// MISSION START############################################################################################# MISSION START

mission_wuzi2_START:

	REGISTER_MISSION_GIVEN

	flag_player_on_mission = 1

	GET_MAX_WANTED_LEVEL max_wanted_level_W2

	// text
	LOAD_MISSION_TEXT WUZI2

VAR_INT swim_stamina_check	
	GET_INT_STAT UNDERWATER_BREATH_STAMINA swim_stamina_check

	
	IF NOT IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
		IF swim_stamina_check <= 50

			swim_stamina_check = 1	

			// CUTSCENE START ##################################### CUTSCENE START
		    
		    SET_AREA_VISIBLE 1

			LOAD_CUTSCENE W2_ALT
			 
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

			// CUTSCENE END ####################################### CUTSCENE END

			GOTO mission_wuzi2_CLEANUP

		ENDIF
	ENDIF

	swim_stamina_check = 0
	
	
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

	// CUTSCENE START ##################################### CUTSCENE START
    
    SET_AREA_VISIBLE 1

	LOAD_CUTSCENE WOOZIE2
	 
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

	// CUTSCENE END ####################################### CUTSCENE END

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

	// audio controller
	START_NEW_SCRIPT start_audio_controller 

	// chars
	REQUEST_MODEL DNB1
	REQUEST_MODEL DNB2
	REQUEST_MODEL DNB3

	// cars
	REQUEST_MODEL TROPIC

	// weapons								
	REQUEST_MODEL AK47
	REQUEST_MODEL KNIFECUR

	// objects
	REQUEST_MODEL a51_spotbase
	REQUEST_MODEL a51_spothousing
	REQUEST_MODEL a51_spotbulb		 

	REQUEST_ANIMATION DAM_JUMP


	WHILE NOT HAS_MODEL_LOADED DNB1
	OR NOT HAS_MODEL_LOADED DNB2
	OR NOT HAS_MODEL_LOADED DNB3
	OR NOT HAS_MODEL_LOADED TROPIC
	OR NOT HAS_MODEL_LOADED AK47			    
	OR NOT HAS_MODEL_LOADED KNIFECUR
 		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED a51_spotbase
	OR NOT HAS_MODEL_LOADED a51_spothousing
	OR NOT HAS_MODEL_LOADED a51_spotbulb
	OR NOT HAS_ANIMATION_LOADED DAM_JUMP
		WAIT 0
	ENDWHILE

	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL stealth_dm_W2
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_W2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

	FORCE_WEATHER_NOW WEATHER_FOGGY_SF

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	SET_PLAYER_CONTROL player1 ON

	SET_CHAR_COORDINATES scplayer -2153.6111 645.3100 51.3516
	SET_CHAR_HEADING scplayer 270.0

	DO_FADE 1500 FADE_IN
									
	ADD_BLIP_FOR_COORD -1641.9952 1309.2168 6.1764 goto_blip_W2

	PRINT_NOW WZI2_1 8000 1

  	fail_text_flag_W2 = 0

LVAR_INT never_run_this_W2
	
	never_run_this_W2 = 0
	IF never_run_this_W2 = 1
		CREATE_CAR TROPIC 0.0 0.0 0.0 boat_W2[pointer_W2]
		CREATE_CHAR PEDTYPE_CIVMALE DNB1 0.0 0.0 0.0 guard_W2[pointer_W2]
		ADD_BLIP_FOR_CHAR guard_W2[pointer_W2] guard_blip_W2[pointer_W2]
		CREATE_CAR TROPIC 0.0 0.0 0.0 s_boat_W2[pointer_W2]
		CREATE_CHAR PEDTYPE_CIVMALE DNB1 0.0 0.0 0.0 s_boat_guard_W2[pointer_W2]
		ADD_BLIP_FOR_CHAR s_boat_guard_W2[pointer_W2] stealth_knife_blip_W2
		CREATE_SEARCHLIGHT 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 searchlight_W2[pointer_W2]
		CREATE_CHECKPOINT CHECKPOINT_TORUS 0.0 0.0 0.0 0.0 0.0 0.0 0.0 corona_W2
	ENDIF

	// audio stuff
	// player seen in water 
	$audio_text_label_W2[0] = &WUZ2_AA
	audio_sound_label_W2[0] = SOUND_WUZ2_AA
	$audio_text_label_W2[1] = &WUZ2_NA
	audio_sound_label_W2[1] = SOUND_WUZ2_NA
	$audio_text_label_W2[2] = &WUZ2_ZA
	audio_sound_label_W2[2] = SOUND_WUZ2_ZA
	$audio_text_label_W2[3] = &WUZ2_AB
	audio_sound_label_W2[3] = SOUND_WUZ2_AB
	$audio_text_label_W2[4] = &WUZ2_NB
	audio_sound_label_W2[4] = SOUND_WUZ2_NB
	$audio_text_label_W2[5] = &WUZ2_ZB
	audio_sound_label_W2[5] = SOUND_WUZ2_ZB
	$audio_text_label_W2[6] = &WUZ2_AC
	audio_sound_label_W2[6] = SOUND_WUZ2_AC
	$audio_text_label_W2[7] = &WUZ2_NC
	audio_sound_label_W2[7] = SOUND_WUZ2_NC
	$audio_text_label_W2[8] = &WUZ2_ZC
	audio_sound_label_W2[8] = SOUND_WUZ2_ZC
	// player goes underwater non positive 
	$audio_text_label_W2[9] = &WUZ2_BA
	audio_sound_label_W2[9] = SOUND_WUZ2_BA
	$audio_text_label_W2[10] = &WUZ2_OA
	audio_sound_label_W2[10] = SOUND_WUZ2_OA
	$audio_text_label_W2[11] = &WUZ2_ZD
	audio_sound_label_W2[11] = SOUND_WUZ2_ZD
	$audio_text_label_W2[12] = &WUZ2_BB
	audio_sound_label_W2[12] = SOUND_WUZ2_BB
	$audio_text_label_W2[13] = &WUZ2_OB
	audio_sound_label_W2[13] = SOUND_WUZ2_OB
	$audio_text_label_W2[14] = &WUZ2_ZE
	audio_sound_label_W2[14] = SOUND_WUZ2_ZE
	$audio_text_label_W2[15] = &WUZ2_BC
	audio_sound_label_W2[15] = SOUND_WUZ2_BC
	$audio_text_label_W2[16] = &WUZ2_OC
	audio_sound_label_W2[16] = SOUND_WUZ2_OC
	$audio_text_label_W2[17] = &WUZ2_ZF
	audio_sound_label_W2[17] = SOUND_WUZ2_ZF
	// player in water definate sighting 
	$audio_text_label_W2[18] = &WUZ2_CA
	audio_sound_label_W2[18] = SOUND_WUZ2_CA
	$audio_text_label_W2[19] = &WUZ2_PA
	audio_sound_label_W2[19] = SOUND_WUZ2_PA
	$audio_text_label_W2[20] = &WUZ2_ZG
	audio_sound_label_W2[20] = SOUND_WUZ2_ZG
	$audio_text_label_W2[21] = &WUZ2_CB
	audio_sound_label_W2[21] = SOUND_WUZ2_CB
	$audio_text_label_W2[22] = &WUZ2_PB
	audio_sound_label_W2[22] = SOUND_WUZ2_PB
	$audio_text_label_W2[23] = &WUZ2_ZH
	audio_sound_label_W2[23] = SOUND_WUZ2_ZH
	$audio_text_label_W2[24] = &WUZ2_CC
	audio_sound_label_W2[24] = SOUND_WUZ2_CC
	$audio_text_label_W2[25] = &WUZ2_PC
	audio_sound_label_W2[25] = SOUND_WUZ2_PC
	$audio_text_label_W2[26] = &WUZ2_ZJ
	audio_sound_label_W2[26] = SOUND_WUZ2_ZJ
	// player goes underwater after a definate sighting 
	$audio_text_label_W2[27] = &WUZ2_DA
	audio_sound_label_W2[27] = SOUND_WUZ2_DA
	$audio_text_label_W2[28] = &WUZ2_QA
	audio_sound_label_W2[28] = SOUND_WUZ2_QA
	$audio_text_label_W2[29] = &WUZ2_ZK
	audio_sound_label_W2[29] = SOUND_WUZ2_ZK
	$audio_text_label_W2[30] = &WUZ2_DB
	audio_sound_label_W2[30] = SOUND_WUZ2_DB
	$audio_text_label_W2[31] = &WUZ2_QB
	audio_sound_label_W2[31] = SOUND_WUZ2_QB
	$audio_text_label_W2[32] = &WUZ2_ZL
	audio_sound_label_W2[32] = SOUND_WUZ2_ZL
	$audio_text_label_W2[33] = &WUZ2_DC
	audio_sound_label_W2[33] = SOUND_WUZ2_DC
	$audio_text_label_W2[34] = &WUZ2_QC
	audio_sound_label_W2[34] = SOUND_WUZ2_QC
	$audio_text_label_W2[35] = &WUZ2_ZM
	audio_sound_label_W2[35] = SOUND_WUZ2_ZM
	// player has hidden for long enough after a positive sighting 
	$audio_text_label_W2[36] = &WUZ2_EA
	audio_sound_label_W2[36] = SOUND_WUZ2_EA
	$audio_text_label_W2[37] = &WUZ2_RA
	audio_sound_label_W2[37] = SOUND_WUZ2_RA
	$audio_text_label_W2[38] = &WUZ2_ZN
	audio_sound_label_W2[38] = SOUND_WUZ2_ZN
	$audio_text_label_W2[39] = &WUZ2_EB
	audio_sound_label_W2[39] = SOUND_WUZ2_EB
	$audio_text_label_W2[40] = &WUZ2_RB
	audio_sound_label_W2[40] = SOUND_WUZ2_RB
	$audio_text_label_W2[41] = &WUZ2_ZO
	audio_sound_label_W2[41] = SOUND_WUZ2_ZO
	$audio_text_label_W2[42] = &WUZ2_EC
	audio_sound_label_W2[42] = SOUND_WUZ2_EC
	$audio_text_label_W2[43] = &WUZ2_RC
	audio_sound_label_W2[43] = SOUND_WUZ2_RC
	$audio_text_label_W2[44] = &WUZ2_ZP
	audio_sound_label_W2[44] = SOUND_WUZ2_ZP
	// player has been sighted again by same boat 
	$audio_text_label_W2[45] = &WUZ2_FA
	audio_sound_label_W2[45] = SOUND_WUZ2_FA
	$audio_text_label_W2[46] = &WUZ2_SA
	audio_sound_label_W2[46] = SOUND_WUZ2_SA
	$audio_text_label_W2[47] = &WUZ2_ZQ
	audio_sound_label_W2[47] = SOUND_WUZ2_ZQ
	$audio_text_label_W2[48] = &WUZ2_FB
	audio_sound_label_W2[48] = SOUND_WUZ2_FB
	$audio_text_label_W2[49] = &WUZ2_SB
	audio_sound_label_W2[49] = SOUND_WUZ2_SB
	$audio_text_label_W2[50] = &WUZ2_ZR
	audio_sound_label_W2[50] = SOUND_WUZ2_ZR
	$audio_text_label_W2[51] = &WUZ2_FC
	audio_sound_label_W2[51] = SOUND_WUZ2_FC
	$audio_text_label_W2[52] = &WUZ2_SC
	audio_sound_label_W2[52] = SOUND_WUZ2_SC
	$audio_text_label_W2[53] = &WUZ2_ZS
	audio_sound_label_W2[53] = SOUND_WUZ2_ZS
	// player has been sighted again by another boat 
	$audio_text_label_W2[54] = &WUZ2_GA
	audio_sound_label_W2[54] = SOUND_WUZ2_GA
	$audio_text_label_W2[55] = &WUZ2_TA
	audio_sound_label_W2[55] = SOUND_WUZ2_TA
	$audio_text_label_W2[56] = &WUZ2_ZT
	audio_sound_label_W2[56] = SOUND_WUZ2_ZT
	$audio_text_label_W2[57] = &WUZ2_GB
	audio_sound_label_W2[57] = SOUND_WUZ2_GB
	$audio_text_label_W2[58] = &WUZ2_TB
	audio_sound_label_W2[58] = SOUND_WUZ2_TB
	$audio_text_label_W2[59] = &WUZ2_ZU
	audio_sound_label_W2[59] = SOUND_WUZ2_ZU
	$audio_text_label_W2[60] = &WUZ2_GC
	audio_sound_label_W2[60] = SOUND_WUZ2_GC
	$audio_text_label_W2[61] = &WUZ2_TC
	audio_sound_label_W2[61] = SOUND_WUZ2_TC
	$audio_text_label_W2[62] = &WUZ2_ZV
	audio_sound_label_W2[62] = SOUND_WUZ2_ZV

	GENERATE_RANDOM_INT_IN_RANGE 0 3 speech_ped_W2  
	GENERATE_RANDOM_INT_IN_RANGE 0 3 speech_sample_W2

	speech_context_W2 = 0
	speech_priority_W2 = 10

mission_wuzi2_MAIN_get_to_docks:

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_wuzi2_PASSED
	ENDIF	
	
	IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -1641.9952 1309.2168 6.1764 1.2 1.2 2.0 TRUE	
		SET_PLAYER_CONTROL player1 OFF
		REMOVE_BLIP goto_blip_W2
		GOTO mission_wuzi2_CUT_jump_into_water
	ENDIF

GOTO mission_wuzi2_MAIN_get_to_docks




LVAR_INT fx_status_W2 water_splash_W2 water_ripple_W2
mission_wuzi2_CUT_jump_into_water:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	LVAR_INT max_wanted_level_W2
	CLEAR_WANTED_LEVEL player1
	SET_MAX_WANTED_LEVEL 0

	CLEAR_AREA_OF_CARS -1648.8752 1319.2418 6.0 -1636.3673 1297.5399 9.0
	CLEAR_AREA_OF_CHARS -1648.8752 1319.2418 6.0 -1636.3673 1297.5399 9.0

	GOSUB mission_wuzi2_SUB_create_boats
	GOSUB mission_wuzi2_sub_create_searchlights

	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -1640.90 1310.60 6.1797
	ELSE
		SET_CHAR_COORDINATES scplayer -1640.90 1310.60 6.1797  
	ENDIF

	SET_CHAR_HEADING scplayer 315.0
	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	skipped_cutscene_W2 = 0

	LOAD_SCENE -1455.9989 1529.1436 19.1790 
	 
	SET_FIXED_CAMERA_POSITION -1358.4844 1468.9941 18.4397 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1359.3120 1469.5398 18.3092 JUMP_CUT  
	
//	SET_FIXED_CAMERA_POSITION -1455.9611 1530.0610 19.5749 0.0 0.0 0.0
//	POINT_CAMERA_AT_POINT -1455.9989 1529.1436 19.1790 JUMP_CUT  
	
 	// TEMP TEMP TEMP
	//GOSUB mission_wuzi2_SUB_store_players_weapons
	//REMOVE_ALL_CHAR_WEAPONS scplayer
 	GOSUB mission_wuzi2_SUB_knife_status

	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

 	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


SKIP_CUTSCENE_START

	PRINT_NOW WZI2_2 6500 1

	TIMERA = 0
	WHILE TIMERA < 6500
		WAIT 0
	ENDWHILE	
	
	SET_FIXED_CAMERA_POSITION -1511.4271 1374.0748 5.9802 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1511.2897 1373.1028 5.7898 JUMP_CUT
		
//	SET_FIXED_CAMERA_POSITION -1461.3271 1364.4674 20.4951 0.0 0.0 0.0
//	POINT_CAMERA_AT_POINT -1461.0702 1365.3855 20.1934 JUMP_CUT 

	PRINT_NOW WZI2_3 6500 1

	TIMERA = 0
	WHILE TIMERA < 6500
		WAIT 0
	ENDWHILE	
	  
SKIP_CUTSCENE_END

	CLEAR_PRINTS

SKIP_CUTSCENE_START

	SET_FIXED_CAMERA_POSITION -1642.4387 1308.8817 11.1649 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1641.9141 1309.4020 10.4912 JUMP_CUT 

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE

	TASK_PLAY_ANIM scplayer SF_JumpWall DAM_JUMP 1000.0 FALSE FALSE FALSE FALSE 0

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE

	SET_TIME_SCALE 0.20
	SET_FIXED_CAMERA_POSITION -1636.6643 1316.3539 -3.7183 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1636.7286 1315.7485 -2.9250 JUMP_CUT 
	z = -2.9250

	TIMERA = 0
	fx_status_W2 = 0
	// move camera lookat down to follow player in water
	WHILE z > -3.2406  
		WAIT 0
		z -= 0.003
		IF z < -3.2406
			z = -3.2406
		ENDIF 
		POINT_CAMERA_AT_POINT -1636.7286 1315.7485 z JUMP_CUT
		IF TIMERA > 200
		AND fx_status_W2 < 2
			IF fx_status_W2 = 0
				GET_WATER_HEIGHT_AT_COORDS -1637.3536 1314.1102 TRUE x
				CREATE_FX_SYSTEM water_splash -1637.3536 1314.1102 x TRUE water_splash_W2
				CREATE_FX_SYSTEM water_ripples -1637.3536 1314.1102 x TRUE water_ripple_W2
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1637.3536 1314.1102 x SOUND_PED_HIT_WATER_SPLASH
				fx_status_W2 ++
			ELSE
				PLAY_FX_SYSTEM water_splash_W2
				PLAY_FX_SYSTEM water_ripple_W2
				fx_status_W2 ++
			ENDIF
		ENDIF
	ENDWHILE


	// slowly speed time back up to 0.5 when player starts to go back to the surface
	z = 0.20
	WHILE z < 0.5
		WAIT 0
		z+= 0.014
		IF z > 0.5
			z = 0.5
		ENDIF
		SET_TIME_SCALE z
	ENDWHILE

	KILL_FX_SYSTEM water_splash_W2
	KILL_FX_SYSTEM water_ripple_W2
		
	WHILE IS_CHAR_PLAYING_ANIM scplayer SF_JumpWall
		WAIT 0
	ENDWHILE

	SET_CHAR_COORDINATES scplayer -1636.33 1315.36 -1.0
	SET_CHAR_HEADING scplayer 285.0

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE

	SET_TIME_SCALE 1.0
	SET_FIXED_CAMERA_POSITION -1641.3987 1314.4135 1.3246 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1640.4224 1314.5736 1.1792 JUMP_CUT

	skipped_cutscene_W2 = 1

	TIMERA = 0
	WHILE TIMERA < 1500
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_END
	
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

	KILL_FX_SYSTEM water_splash_W2
	KILL_FX_SYSTEM water_ripple_W2	

	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON 
	
	SET_ALWAYS_DRAW_3D_MARKERS TRUE

	// first corona
	current_corona_W2 = 0
	corona_status_W2 = 0
	corona_X_W2	= -1625.6505
	corona_Y_W2	= 1317.9204
	//CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 0.0 -1626.6500 1317.9476 0.0 1.9067 corona_W2
	
	bounce_W2 = 1

	IF skipped_cutscene_W2 = 0
		SET_CHAR_COORDINATES scplayer -1636.33 1315.36 -1.0
		SET_CHAR_HEADING scplayer 285.0
		SET_TIME_SCALE 1.0
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
	ELSE
		RESTORE_CAMERA
	ENDIF

	REMOVE_ANIMATION DAM_JUMP
	skipped_cutscene_W2 = 0

	CLEAR_PRINTS
	PRINT_NOW WZI2_8 8000 1
	ADD_BLIP_FOR_COORD -1449.6420 1501.1354 0.7366 goto_blip_W2

// swimming tutorial stuff

LVAR_INT stealth_knife_W2 stealth_knife_blip_W2
VAR_INT help_text_switch_W2 help_text_flag_W2  
LVAR_TEXT_LABEL help_text_W2[8]

	help_text_switch_W2 = 0
	help_text_flag_W2 = 0

	// swimming tutorial text for classic and HID controls
	$help_text_W2[0] = WZI2_A0
	$help_text_W2[1] = WZI2_A1
	$help_text_W2[2] = WZI2_A2
	$help_text_W2[3] = WZI2_A3
	$help_text_W2[4] = WZI2_A4
	$help_text_W2[5] = WZI2_A5
	$help_text_W2[6] = WZI2_A6
	$help_text_W2[7] = WZI2_A7

	// knife in underwater cave
	IF knife_status_W2 = 0
		CREATE_PICKUP_WITH_AMMO KNIFECUR PICKUP_ONCE 1 -1595.0 1345.0 -7.5 stealth_knife_W2
	ENDIF

	TIMERB = 0

mission_wuzi2_MAIN_reef:

	WAIT 0

	GOSUB mission_wuzi2_SUB_gun_check
	IF stop_and_attack_W2 = 2
		
		REMOVE_BLIP	stealth_knife_blip_W2
		//ADD_BLIP_FOR_COORD -1449.6420 1501.1354 0.7366 goto_blip_W2
		//PRINT_NOW WZI2_8 6000 1		
		
		GOTO mission_wuzi2_MAIN_in_open_water 
	ENDIF

	GOSUB mission_wuzi2_SUB_player_swimming_status

	IF reef_status_W2 = 2
		GOTO mission_wuzi2_MAIN_cut_open_water
	ENDIF

	SWITCH help_text_switch_W2
					
		CASE 0

			IF IS_CHAR_IN_AREA_3D scplayer -1602.7825 1320.6877 -8.0 -1606.1187 1331.5096 1.0 FALSE
			AND player_underwater_W2 = 0

				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL player1 OFF

				CLEAR_HELP
				CLEAR_PRINTS

				CAMERA_RESET_NEW_SCRIPTABLES
			    CAMERA_PERSIST_TRACK TRUE 
			    CAMERA_PERSIST_POS TRUE
			    CAMERA_SET_VECTOR_TRACK -1600.9977 1323.0409 1.9093 -1600.3146 1322.4142 -2.0672 6000 TRUE
				CAMERA_SET_VECTOR_MOVE -1598.5248 1318.5944 2.3701 -1598.5248 1318.5944 -2.3701 6000 TRUE
				
				PRINT_NOW WZI2_5 8000 1 // the route ahead is blocked
				
				TIMERA = 0
				WHILE TIMERA < 800
					WAIT 0
				ENDWHILE

			SKIP_CUTSCENE_START
			                                                                        
				WHILE CAMERA_IS_VECTOR_TRACK_RUNNING
					WAIT 0
				ENDWHILE
				
				TIMERA = 0
				WHILE TIMERA < 1500
					WAIT 0
				ENDWHILE

				SET_FIXED_CAMERA_POSITION -1598.5248 1318.5944 1.3701 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1599.1813 1319.2881 1.0744 JUMP_CUT
 
			SKIP_CUTSCENE_END
			
				CLEAR_PRINTS

				CAMERA_RESET_NEW_SCRIPTABLES	
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				help_text_switch_W2 ++
				TIMERB = 0  
			ELSE
				IF IS_CHAR_IN_AREA_3D scplayer -1611.8329 1334.3130 0.8012 -1615.8120 1329.3939 -7.9862 FALSE
					help_text_switch_W2 = 3
				ELSE
					// in open water
					IF LOCATE_CHAR_ON_FOOT_2D scplayer -1549.1251 1339.9611 3.5 3.5 FALSE
						exit_status_W2 = 0
						GOTO mission_wuzi2_MAIN_enter_open_water
					ENDIF						
				ENDIF
			ENDIF
		BREAK

		CASE 1
			IF IS_CHAR_IN_ANGLED_AREA_ON_FOOT_3D scplayer -1611.8329 1334.3130 0.8012 -1615.8120 1329.3939 -7.9862 2.0 FALSE
				IF player_underwater_W2 = 1
					PRINT_NOW WZI2_6 5000 1 // you can now resurface
				ENDIF
				help_text_switch_W2 ++
			ELSE
				IF LOCATE_CHAR_ON_FOOT_2D scplayer -1549.1251 1339.9611 3.5 3.5 FALSE
					exit_status_W2 = 0
					GOTO mission_wuzi2_MAIN_enter_open_water
				ENDIF
			ENDIF
		BREAK

		CASE 2
			IF LOCATE_CHAR_ON_FOOT_2D scplayer -1612.2080 1349.5525 3.5 3.5 FALSE
				CLEAR_HELP
				
				IF knife_status_W2 = 0
					PRINT_NOW WZI2_7 6000 1 // hint about the knife
					ADD_BLIP_FOR_PICKUP stealth_knife_W2 stealth_knife_blip_W2
					TIMERB = 0
				ELSE
					TIMERB = 6001
				ENDIF
				help_text_switch_W2 ++
			ELSE
				IF LOCATE_CHAR_ON_FOOT_2D scplayer -1549.1251 1339.9611 3.5 3.5 FALSE
					exit_status_W2 = 0
					GOTO mission_wuzi2_MAIN_enter_open_water
				ENDIF
			ENDIF
		BREAK

		CASE 3
			IF LOCATE_CHAR_ON_FOOT_2D scplayer -1549.1251 1339.9611 3.5 3.5 FALSE
				exit_status_W2 = 0
				GOTO mission_wuzi2_MAIN_enter_open_water
			ENDIF
		BREAK

	ENDSWITCH
	 
	GOSUB mission_wuzi2_SUB_swimming_coronas
	// debug grid
	GOSUB mission_wuzi2_DEBUG_draw_grid
	 		
GOTO mission_wuzi2_MAIN_reef					    
 

LVAR_INT corona_W2 current_corona_W2 corona_status_W2 bounce_W2
LVAR_FLOAT corona_X_W2 corona_Y_W2
mission_wuzi2_SUB_swimming_coronas:

	SWITCH corona_status_W2

		CASE 0 // locate player to remove (bounce)
			IF LOCATE_CHAR_ON_FOOT_2D scplayer corona_X_W2 corona_Y_W2 4.0 4.0 bounce_W2
				DELETE_CHECKPOINT corona_W2
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
				IF current_corona_W2 < 6
					corona_status_W2 ++  
				ELSE
					corona_status_W2 = -1
				ENDIF 
//			ELSE
//				IF bounce_W2 = 1
//					GET_WATER_HEIGHT_AT_COORDS corona_X_W2 corona_Y_W2 TRUE water_W2
//					SET_CHECKPOINT_COORDS corona_W2 corona_X_W2 corona_Y_W2 water_W2 
//				ENDIF
			ENDIF 
		BREAK

		CASE 1
			GOSUB mission_wuzi2_SUB_create_new_swimming_corona
			corona_status_W2 --
		BREAK

	ENDSWITCH

RETURN 

mission_wuzi2_SUB_create_new_swimming_corona:

	current_corona_W2 ++
	SWITCH current_corona_W2

		CASE 1
			corona_X_W2	= -1604.5222
			corona_Y_W2	= 1312.6744
			//CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 0.0 -1605.5205 1312.6179 0.0 2.4133 corona_W2
			bounce_W2 = 1
		BREAK

		CASE 2
			corona_X_W2	= -1611.5571
			corona_Y_W2	= 1330.7891
			CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 -2.2294 -1610.6716 1330.3246 -2.2294 0.8/*1.6533*/ corona_W2
			bounce_W2 = 0
		BREAK

		CASE 3
			corona_X_W2	= -1626.0983
			corona_Y_W2	= 1343.8113
			//CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 0.0 -1626.2007 1342.8165 0.0 2.0267 corona_W2
			bounce_W2 = 1
		BREAK

		CASE 4
			IF knife_status_W2 > 0
				corona_X_W2	= -1596.3333
				corona_Y_W2	= 1345.0532
				CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 -6.2933 -1597.2726 1345.3959 -6.2933 0.8/*1.6667*/ corona_W2
				bounce_W2 = 0
				BREAK
			ELSE
				current_corona_W2 ++
			ENDIF

		CASE 5
			corona_X_W2	= -1576.7216
			corona_Y_W2	= 1338.4773
			//CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 0.0 -1577.6342 1338.8859 0.0 1.9067 corona_W2
			bounce_W2 = 1
		BREAK

  
		CASE 6
			corona_X_W2	= -1559.1379
			corona_Y_W2	= 1333.9536
			CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 -5.3882 -1560.1190 1334.1466 -5.3819 1.2/*2.5600*/ corona_W2
			bounce_W2 = 0
		BREAK


		CASE 7
			corona_X_W2	= -1548.2164
			corona_Y_W2	= 1342.1975
			CREATE_CHECKPOINT CHECKPOINT_TORUS corona_X_W2 corona_Y_W2 -7.0752 -1548.6078 1341.2772 -7.0752 1.2/*2.5600*/ corona_W2
			bounce_W2 = 0
		BREAK

	ENDSWITCH
	 
RETURN




LVAR_INT exit_status_W2
mission_wuzi2_MAIN_enter_open_water:

	WAIT 0
	
	// check players current position/square
	GOSUB mission_wuzi2_SUB_player_swimming_status
	IF reef_status_W2 = 2
		GOTO mission_wuzi2_MAIN_cut_open_water
	ENDIF

	IF player_underwater_W2 = 0
		IF exit_status_W2 = 0 
			IF NOT IS_CHAR_IN_ANGLED_AREA_ON_FOOT_3D scplayer -1558.9320 1338.2853 -5.0 -1563.0785 1329.2897 5.0 10.0 FALSE
				IF reef_status_W2 = 0
					GOTO mission_wuzi2_MAIN_cut_open_water
				ENDIF
			ELSE
				exit_status_W2 = 1
			ENDIF
		ENDIF
	ELSE
		IF exit_status_W2 = 1
			IF LOCATE_CHAR_ON_FOOT_2D scplayer -1549.1251 1339.9611 3.5 3.5 FALSE
				exit_status_W2 = 0
			ENDIF	
		ENDIF
	ENDIF

GOTO mission_wuzi2_MAIN_enter_open_water




mission_wuzi2_MAIN_cut_open_water:

	player_status_W2 = -2
	CLEAR_HELP
	CLEAR_PRINTS
	REMOVE_BLIP stealth_knife_blip_W2
	DELETE_CHECKPOINT corona_W2
	
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	// normal cutscene
	IF NOT reef_status_W2 = 2

			SET_CAMERA_BEHIND_PLAYER 
			 
			TIMERA = 0
			WHILE TIMERA < 500
				WAIT 0
			ENDWHILE

		    GET_ACTIVE_CAMERA_COORDINATES camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2
			
			GET_ACTIVE_CAMERA_POINT_AT camera_look_X_W2 camera_look_Y_W2 camera_look_Z_W2

			IF NOT IS_CAR_DEAD boat_W2[1]
				GET_CAR_COORDINATES boat_W2[1] x y z
			ENDIF
		    
		    CAMERA_RESET_NEW_SCRIPTABLES
		    CAMERA_PERSIST_TRACK TRUE 
		    CAMERA_PERSIST_POS TRUE
		    CAMERA_SET_VECTOR_TRACK camera_look_X_W2 camera_look_Y_W2 camera_look_Z_W2 x y z 3500 TRUE
			CAMERA_SET_VECTOR_MOVE camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 3500 TRUE
		    
			PRINT_NOW WZI2_40 8000 1

			TIMERA = 0
			WHILE TIMERA < 1000
				WAIT 0
			ENDWHILE

SKIP_CUTSCENE_START
		                                                                   
			WAIT 0
			WHILE CAMERA_IS_VECTOR_TRACK_RUNNING
				WAIT 0
			ENDWHILE

		    IF NOT IS_CAR_DEAD boat_W2[0]
				GET_CAR_COORDINATES boat_W2[0] player_x player_y player_z
			ENDIF

		    CAMERA_RESET_NEW_SCRIPTABLES
		    CAMERA_PERSIST_TRACK TRUE 
		    CAMERA_PERSIST_POS TRUE
		    CAMERA_SET_VECTOR_TRACK x y z player_x player_y player_z 3000 FALSE
			CAMERA_SET_VECTOR_MOVE camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 3000 FALSE	
			WAIT 0
			WHILE CAMERA_IS_VECTOR_TRACK_RUNNING
				WAIT 0
				GOSUB mission_wuzi2_SUB_player_grid_square
				GOSUB mission_wuzi2_SUB_boat_status
			ENDWHILE

		    PRINT_NOW WZI2_41 8000 1

			CAMERA_RESET_NEW_SCRIPTABLES
		    CAMERA_PERSIST_TRACK TRUE 
		    CAMERA_PERSIST_POS TRUE
		    CAMERA_SET_VECTOR_TRACK player_x player_y player_z camera_look_X_W2 camera_look_Y_W2 camera_look_Z_W2 4500 TRUE
			CAMERA_SET_VECTOR_MOVE camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 camera_pos_X_W2 camera_pos_Y_W2 camera_pos_Z_W2 4500 TRUE	
			
			WAIT 0
			WHILE CAMERA_IS_VECTOR_TRACK_RUNNING
				WAIT 0
				GOSUB mission_wuzi2_SUB_player_grid_square
				GOSUB mission_wuzi2_SUB_boat_status
			ENDWHILE

			
			TIMERA = 0
			WHILE TIMERA < 1000
				WAIT 0
				GOSUB mission_wuzi2_SUB_player_grid_square
				GOSUB mission_wuzi2_SUB_boat_status
			ENDWHILE

		SKIP_CUTSCENE_END

	ELSE
		SKIP_CUTSCENE_START
			// jumped out of reef cutscene
			SET_FIXED_CAMERA_POSITION -1568.7422 1385.3656 5.9871 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1569.7065 1385.1256 5.8767 JUMP_CUT

			PRINT_NOW WZI2_40 6000 1

			TIMERA = 0
			WHILE TIMERA < 6000
				WAIT 0
			ENDWHILE

			SET_FIXED_CAMERA_POSITION -1494.6490 1350.3582 6.0727 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1495.1637 1349.5155 5.9146 JUMP_CUT
	 
			PRINT_NOW WZI2_41 6000 1

			TIMERA = 0
			WHILE TIMERA < 6000
				WAIT 0
			ENDWHILE
 
		SKIP_CUTSCENE_END
	ENDIF
 
	PRINT_NOW WZI2_8 6000 1

	IF NOT IS_CAR_DEAD boat_W2[0]
		ADD_BLIP_FOR_CAR boat_W2[0] boat_blip_W2[0]
		CHANGE_BLIP_DISPLAY boat_blip_W2[0] BLIP_ONLY
	ENDIF
	IF NOT IS_CAR_DEAD boat_W2[1]
		ADD_BLIP_FOR_CAR boat_W2[1] boat_blip_W2[1]
		CHANGE_BLIP_DISPLAY boat_blip_W2[1] BLIP_ONLY
	ENDIF

	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[0] searchlight_blip_W2[0]
	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[1] searchlight_blip_W2[1]
	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[2] searchlight_blip_W2[2]
	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[3] searchlight_blip_W2[3]
//	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[4] searchlight_blip_W2[4]
//	ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[5] searchlight_blip_W2[5]

	IF NOT IS_CAR_DEAD s_boat_W2[0]
		ADD_BLIP_FOR_CAR s_boat_W2[0] s_boat_blip_W2[0]
		CHANGE_BLIP_DISPLAY s_boat_blip_W2[0] BLIP_ONLY
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[1]
		ADD_BLIP_FOR_CAR s_boat_W2[1] s_boat_blip_W2[1]
		CHANGE_BLIP_DISPLAY s_boat_blip_W2[1] BLIP_ONLY
	ENDIF

	// main boat
	//ADD_BLIP_FOR_COORD -1449.6420 1501.1354 0.7366 goto_blip_W2

	PRINT_NOW WZI2_8 6000 1

	player_breath_W2 = 1
	player_timer_W2 = 0
	player_first_spot_W2 = 0
	
	CAMERA_RESET_NEW_SCRIPTABLES 
	RESTORE_CAMERA_JUMPCUT
	
	SET_CAMERA_BEHIND_PLAYER
	CLEAR_CHAR_TASKS scplayer 
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	
	stealth_active_W2 = 0

LVAR_INT stealth_active_W2 explain_searchlights_W2
mission_wuzi2_MAIN_in_open_water:

	WAIT 0

	GET_GAME_TIMER this_frame_time_W2
	time_diff_W2 = this_frame_time_W2 - last_frame_time_W2 
	last_frame_time_W2 = this_frame_time_W2

	// player timer
	player_timer_W2 += time_diff_W2
	// guard timers
	guard_timer_W2[0] += time_diff_W2
	guard_timer_W2[1] += time_diff_W2
	
	IF NOT stop_and_attack_W2 = 2
	
		// get status of player underwater/crawling
		GOSUB mission_wuzi2_SUB_player_swimming_status
		
		// check players current position/square
		GOSUB mission_wuzi2_SUB_player_grid_square
		
		// controls moving of the boat
		GOSUB mission_wuzi2_SUB_boat_status
		
		// controls guards on the boat
		GOSUB mission_wuzi2_SUB_guard_status

		// controls text for guys on the boat
		GOSUB mission_wuzi2_sub_player_status

		// check searchlights if player is near big boat
		IF p_current_square > 35
			IF explain_searchlights_W2 = 0
				explain_searchlights_W2 = 1
			ENDIF	
			GOSUB mission_wuzi2_SUB_check_spotlights 
		ENDIF
		
		// debug grid
		GOSUB mission_wuzi2_DEBUG_draw_grid

		GOSUB mission_wuzi2_SUB_gun_check

	ELSE
		GOSUB mission_wuzi2_SUB_stop_and_attack  
	ENDIF

	// check is player has shot bulbs
	GOSUB mission_wuzi2_SUB_check_bulbs
	

	// stealth guys on boat
	IF stealth_active_W2 = 0
		IF IS_CHAR_IN_WATER scplayer
			IF player_underwater_W2 = 0
				IF LOCATE_CHAR_ON_FOOT_2D scplayer -1447.1284 1501.2410 6.0 6.0 FALSE
					CLEAR_HELP
					
					PRINT_HELP_FOREVER WZI2_C0  
					
					TIMERB = 0
					stealth_active_W2 ++
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF stealth_active_W2 = 1
			IF TIMERB > 8000
				CLEAR_HELP
			ENDIF
			IF NOT IS_CHAR_IN_WATER scplayer
				IF LOCATE_CHAR_ON_FOOT_3D scplayer -1449.6420 1501.1354 0.7366 2.0 2.0 2.0 FALSE
					CLEAR_HELP
					GOSUB mission_wuzi2_SUB_start_boat
					stealth_active_W2 ++
				ENDIF
			ENDIF
		ELSE
			// stealth guards
			GOSUB mission_wuzi2_sub_guard_stealth_status
			GOSUB mission_wuzi2_SUB_idle_guards_still
			GOSUB mission_wuzi2_SUB_idle_guards_patrol

			IF player_planted_bug_W2 = 0
				
				IF LOCATE_CHAR_ON_FOOT_3D scplayer -1372.7328 1495.5503 0.8578 1.2 1.2 2.0 TRUE
					IF NOT p_stealth_status_W2 = 2
						
						PRINT_NOW WZI2_30 1000 1  

						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							CLEAR_PRINTS
							PRINT_NOW WZI2_32 6000 1
							REMOVE_BLIP goto_blip_W2
							ADD_BLIP_FOR_COORD -1508.5582 1296.0671 0.3970 goto_blip_W2

							player_planted_bug_W2 = 1

							GOSUB mission_wuzi2_SUB_reduce_guard_accuracy

						ENDIF 
					ELSE
						PRINT_NOW WZI2_31 3000 1
					ENDIF 
				ENDIF

			ELSE
				IF LOCATE_CHAR_ON_FOOT_3D scplayer -1508.5582 1296.0671 0.3970 1.2 1.2 2.0 TRUE
					GOTO mission_wuzi2_PASSED	
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	GOSUB mission_wuzi2_SUB_control_radar

GOTO mission_wuzi2_MAIN_in_open_water













 
 
LVAR_INT player_swimstate_W2 player_crawling_W2 player_underwater_W2 player_timer_W2 reef_status_W2


// MAIN CONTROLLER FOR SWIMMING SECTION
mission_wuzi2_SUB_player_swimming_status:

	player_underwater_W2 = 0
	player_crawling_W2 = 0	

	IF IS_CHAR_SWIMMING scplayer
    
		GET_CHAR_SWIM_STATE scplayer player_swimstate_W2
            
		IF player_swimstate_W2 = SWIM_UNDERWATER
			player_underwater_W2 = 1
			IF player_status_W2 <= 0
				player_breath_W2 = 1
				player_status_W2 = -2
			ENDIF 	
		ELSE
			IF player_swimstate_W2 = SWIM_CRAWL
				player_crawling_W2 = 1
			ENDIF 
		ENDIF
	
		// check to make sure player hasnt climbed out of the reef
		IF reef_status_W2 = 1
		 	
		 	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer -1559.2874 1337.1991 -1562.5834 1327.2573 -62.9096 FALSE
		 	OR IS_CHAR_IN_ANGLED_AREA_2D scplayer -1590.3267 1336.8811 -1607.2059 1307.5653 -37.0915 FALSE
		 	OR IS_CHAR_IN_ANGLED_AREA_2D scplayer -1616.5742 1319.3361 -1632.8600 1303.4518 -23.9075 FALSE
		 	OR IS_CHAR_IN_ANGLED_AREA_2D scplayer -1595.1842 1316.6805 -1603.4827 1308.8937 -8.3514 FALSE		 	   
				reef_status_W2 = 0
			ELSE
				reef_status_W2 = 2
			ENDIF			
		ENDIF	
	ELSE
		IF reef_status_W2 = 0
			reef_status_W2 = 1 // out of water
		ENDIF
		IF IS_CHAR_IN_ANY_CAR scplayer
			player_crawling_W2 = 1
		ENDIF 
	ENDIF

RETURN


// ##########
// ##
// ## BOATS 
// ##
// ##########################################################################################################

LVAR_INT boat_W2[2] boat_blip_W2[2] boat_status_W2[2] boat_position_W2[2] boat_attack_W2[2]  
LVAR_INT boat_driver_W2[2] boat_guard1_W2[2] boat_guard2_W2[2] boat_guard3_W2[2]

LVAR_INT boat_guards_W2[6]

VAR_INT guard_status_W2[2] guard_timer_W2[2] square_attack_status_W2 attack_status_W2
LVAR_FLOAT boat_speed_W2[2] boat_X_W2[2] boat_Y_W2[2] boat_heading_W2[2] guard_heading_W2[6] guard1_heading_W2 guard2_heading_W2 guard3_heading_W2 

LVAR_FLOAT swim_grid_X_W2[7] swim_grid_Y_W2[5] boat_square_X_W2[6] boat_square_Y_W2[4] 
LVAR_INT boat_goto_X_W2[2] boat_goto_Y_W2[2] 

LVAR_FLOAT max_speed_W2 acceleration_W2 turn_factor_W2 


mission_wuzi2_SUB_create_boats:

	SWITCH_ROADS_OFF -1328.9994 1239.9972 -5.0 -1655.8684 1638.7549 10.0
	CLEAR_AREA_OF_CARS -1328.9994 1239.9972 -5.0 -1655.8684 1638.7549 10.0

	swim_grid_X_W2[0] = -1605.0
	swim_grid_X_W2[1] = -1565.0
	swim_grid_X_W2[2] = -1525.0
	swim_grid_X_W2[3] = -1485.0
	swim_grid_X_W2[4] = -1445.0
	swim_grid_X_W2[5] = -1405.0
	swim_grid_X_W2[6] = -1365.0

	swim_grid_Y_W2[0] = 1315.0
	swim_grid_Y_W2[1] = 1355.0
	swim_grid_Y_W2[2] = 1395.0
	swim_grid_Y_W2[3] = 1435.0
	swim_grid_Y_W2[4] = 1475.0
	
	boat_square_X_W2[0] = -1585.0
	boat_square_X_W2[1] = -1545.0
	boat_square_X_W2[2] = -1505.0
	boat_square_X_W2[3] = -1465.0
	boat_square_X_W2[4] = -1425.0
	boat_square_X_W2[5] = -1385.0

	boat_square_Y_W2[0] = 1335.0
	boat_square_Y_W2[1] = 1375.0
	boat_square_Y_W2[2] = 1415.0
	boat_square_Y_W2[3] = 1455.0

	p_current_square = 0
	p_last_square = -1
	turn_factor_W2 = 40.0

	max_speed_W2 = 3.0
	acceleration_W2 = 0.1

	
	// boat 0
	CREATE_CAR TROPIC -1585.0 1375.0 1.0 boat_W2[0]
	ANCHOR_BOAT boat_W2[0] TRUE
	 
	CREATE_CHAR_INSIDE_CAR boat_W2[0] PEDTYPE_MISSION1 DNB1 boat_driver_W2[0]

	CREATE_CHAR PEDTYPE_MISSION1 DNB1 -1585.0 1375.0 4.0 boat_guards_W2[0]
	ATTACH_CHAR_TO_CAR boat_guards_W2[0] boat_W2[0] 0.0 9.0 2.1 FACING_FORWARD 360.0 WEAPONTYPE_AK47
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[0] TRUE  
	SET_CHAR_ACCURACY boat_guards_W2[0] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[0] 20
	guard_heading_W2[0] = 0.0

	CREATE_CHAR PEDTYPE_MISSION1 DNB2 -1585.0 1375.0 4.0 boat_guards_W2[1]
	ATTACH_CHAR_TO_CAR boat_guards_W2[1] boat_W2[0] -1.0 -1.0 3.70 FACING_FORWARD 360.0 WEAPONTYPE_AK47
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[1] TRUE 
	SET_CHAR_ACCURACY boat_guards_W2[1] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[1] 20
	guard_heading_W2[1] = -90.0

	CREATE_CHAR PEDTYPE_MISSION1 DNB3 -1585.0 1375.0 4.0 boat_guards_W2[2]
	ATTACH_CHAR_TO_CAR boat_guards_W2[2] boat_W2[0] 1.0 -4.0 1.15 FACING_FORWARD 360.0 WEAPONTYPE_AK47
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[2] TRUE 
	SET_CHAR_ACCURACY boat_guards_W2[2] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[2] 20
	guard_heading_W2[2] = 140.0

	boat_status_W2[0] = 0 
	boat_position_W2[0] = 17
	guard_status_W2[0] = 0

	// boat 1
	CREATE_CAR TROPIC -1505.0 1335.0 1.0 boat_W2[1]
	ANCHOR_BOAT boat_W2[1] TRUE
	CREATE_CHAR_INSIDE_CAR boat_W2[1] PEDTYPE_MISSION1 DNB1 boat_driver_W2[1]
	
	CREATE_CHAR PEDTYPE_MISSION1 DNB1 -1585.0 1375.0 4.0 boat_guards_W2[3]
	ATTACH_CHAR_TO_CAR boat_guards_W2[3] boat_W2[1] 0.0 9.0 2.1 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[3] TRUE 
	SET_CHAR_ACCURACY boat_guards_W2[3] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[3] 20
	guard_heading_W2[3] = 0.0

	CREATE_CHAR PEDTYPE_MISSION1 DNB2 -1585.0 1375.0 4.0 boat_guards_W2[4] 
	ATTACH_CHAR_TO_CAR boat_guards_W2[4] boat_W2[1] -1.0 -1.0 3.70 FACING_FORWARD 360.0 WEAPONTYPE_AK47
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[4] TRUE
	SET_CHAR_ACCURACY boat_guards_W2[4] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[4] 20
	guard_heading_W2[4] = -90.0

	CREATE_CHAR PEDTYPE_MISSION1 DNB3 -1585.0 1375.0 4.0 boat_guards_W2[5]
	ATTACH_CHAR_TO_CAR boat_guards_W2[5] boat_W2[1] 1.0 -4.0 1.15 FACING_FORWARD 360.0 WEAPONTYPE_AK47
	TASK_STAY_IN_SAME_PLACE boat_guards_W2[5] TRUE
	SET_CHAR_ACCURACY boat_guards_W2[5] 80
	SET_CHAR_SHOOT_RATE boat_guards_W2[5] 20
	guard_heading_W2[5] = 140.0
				 
	boat_status_W2[1] = 0 
	boat_position_W2[1] = 11
	guard_status_W2[1] = 0
	
	attack_status_W2 = 0

RETURN



VAR_INT p_current_square p_last_square x_grid_pos_W2 y_grid_pos_W2  
mission_wuzi2_SUB_player_grid_square:

	GET_CHAR_COORDINATES scplayer x y z
	
	x_grid_pos_W2 = -1
	y_grid_pos_W2 = -1

	// x grid position
	pointer_W2 = 0
	WHILE pointer_W2 < 7
		IF x < swim_grid_X_W2[pointer_W2]
			x_grid_pos_W2 = pointer_W2
			pointer_W2 = 7
		ENDIF
		pointer_W2++
	ENDWHILE
	IF x_grid_pos_W2 = -1
		x_grid_pos_W2 = 7
	ENDIF

	// y grid position
	pointer_W2 = 0
	WHILE pointer_W2 < 5
		IF y < swim_grid_Y_W2[pointer_W2]
			y_grid_pos_W2 = pointer_W2
			pointer_W2 = 5
		ENDIF
		pointer_W2++
	ENDWHILE
	IF y_grid_pos_W2 = -1
		y_grid_pos_W2 = 5
	ENDIF	 

	// calculate square number
	y_grid_pos_W2 *= 8
	p_current_square = x_grid_pos_W2 + y_grid_pos_W2 

	// if players in a new square move the boats
	IF NOT p_current_square = p_last_square
		GOSUB mission_wuzi2_SUB_grid_controller
		p_last_square = p_current_square  
	ENDIF

RETURN


LVAR_INT boat_pos_temp_W2[2] boat_goto_X_temp_W2[2] boat_goto_Y_temp_W2[2] 
mission_wuzi2_SUB_grid_controller:

	SWITCH p_current_square

		CASE 0
		CASE 1
			boat_pos_temp_W2[0] = 17		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 11		
			boat_goto_X_temp_W2[1] = 2
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = -1
		BREAK
			
		CASE 2
		CASE 3	
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 11		
			boat_goto_X_temp_W2[1] = 2
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 4
		BREAK

		CASE 4
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 12		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 4
		BREAK

		CASE 5
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 13		
			boat_goto_X_temp_W2[1] = 4
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 4
		BREAK

		CASE 6
		CASE 7
		CASE 15
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 14		
			boat_goto_X_temp_W2[1] = 5
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 4
		BREAK

		CASE 8
			boat_pos_temp_W2[0] = 17		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 11		
			boat_goto_X_temp_W2[1] = 2
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 3
		BREAK

		CASE 9
		CASE 10
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 11		
			boat_goto_X_temp_W2[1] = 2
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 5
		BREAK

		CASE 11
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 12		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 0
			square_attack_status_W2 = 2
		BREAK

		CASE 12
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 20		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 1
		BREAK

		CASE 13
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 21		
			boat_goto_X_temp_W2[1] = 4
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 1
		BREAK

		CASE 14
			boat_pos_temp_W2[0] = 18		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 22		
			boat_goto_X_temp_W2[1] = 5
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 1
		BREAK

		CASE 16
			boat_pos_temp_W2[0] = 17		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 1
			boat_pos_temp_W2[1] = 20		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 3
		BREAK

		CASE 17
			boat_pos_temp_W2[0] = 25		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 20		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 0
		BREAK

		CASE 18
			boat_pos_temp_W2[0] = 26		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 20		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 0
		BREAK


		CASE 19
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 20		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 2
		BREAK
		
		CASE 20
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 1
		BREAK
		
		CASE 21
		CASE 30
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			IF p_last_square = 29
				boat_pos_temp_W2[1] = 22		
				boat_goto_X_temp_W2[1] = 5
				boat_goto_Y_temp_W2[1] = 1
			ELSE
				boat_pos_temp_W2[1] = 29		
				boat_goto_X_temp_W2[1] = 4
				boat_goto_Y_temp_W2[1] = 2
			ENDIF
			square_attack_status_W2 = 1
		BREAK

		CASE 22
		CASE 29
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			IF p_last_square = 30
				boat_pos_temp_W2[1] = 21		
				boat_goto_X_temp_W2[1] = 4
				boat_goto_Y_temp_W2[1] = 1
			ELSE
				boat_pos_temp_W2[1] = 30		
				boat_goto_X_temp_W2[1] = 5
				boat_goto_Y_temp_W2[1] = 2
			ENDIF
			square_attack_status_W2 = 1
		BREAK

		CASE 23
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 22		
			boat_goto_X_temp_W2[1] = 5
			boat_goto_Y_temp_W2[1] = 1
			square_attack_status_W2 = 4
		BREAK

		CASE 24
			boat_pos_temp_W2[0] = 25		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 3
		BREAK

		CASE 25
			boat_pos_temp_W2[0] = 33		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 0
		BREAK

		CASE 26
		CASE 35
			IF p_last_square = 34
				boat_pos_temp_W2[0] = 27		
				boat_goto_X_temp_W2[0] = 2
				boat_goto_Y_temp_W2[0] = 2
			ELSE
				boat_pos_temp_W2[0] = 34		
				boat_goto_X_temp_W2[0] = 1
				boat_goto_Y_temp_W2[0] = 3
			ENDIF
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 0
		BREAK

		CASE 27
		CASE 34
			IF p_last_square = 35
				boat_pos_temp_W2[0] = 26		
				boat_goto_X_temp_W2[0] = 1
				boat_goto_Y_temp_W2[0] = 2
			ELSE
				boat_pos_temp_W2[0] = 35		
				boat_goto_X_temp_W2[0] = 2
				boat_goto_Y_temp_W2[0] = 3
			ENDIF
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			IF p_current_square = 27 
				square_attack_status_W2 = 2
			ELSE
				square_attack_status_W2 = 0
			ENDIF
		BREAK

		CASE 28
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 29		
			boat_goto_X_temp_W2[1] = 4
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 2
		BREAK		

		CASE 31
			boat_pos_temp_W2[0] = 27		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 2
			boat_pos_temp_W2[1] = 30		
			boat_goto_X_temp_W2[1] = 5
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 4
		BREAK

		CASE 32
		CASE 40
		CASE 41
			boat_pos_temp_W2[0] = 33		
			boat_goto_X_temp_W2[0] = 0
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			square_attack_status_W2 = 3
		BREAK

		CASE 33
		CASE 42
			boat_pos_temp_W2[0] = 34		
			boat_goto_X_temp_W2[0] = 1
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			IF square_attack_status_W2 = 33
				square_attack_status_W2 = 0
			ELSE
				square_attack_status_W2 = 3
			ENDIF
		BREAK

		CASE 36
		CASE 43
		CASE 44
			boat_pos_temp_W2[0] = 35		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 28		
			boat_goto_X_temp_W2[1] = 3
			boat_goto_Y_temp_W2[1] = 2
			IF square_attack_status_W2 = 36
				square_attack_status_W2 = 5
			ELSE
				square_attack_status_W2 = 3
			ENDIF
		BREAK

		CASE 37
		CASE 45
			boat_pos_temp_W2[0] = 35		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 29		
			boat_goto_X_temp_W2[1] = 4
			boat_goto_Y_temp_W2[1] = 2
			IF square_attack_status_W2 = 37
				square_attack_status_W2 = 4
			ELSE
				square_attack_status_W2 = -1
			ENDIF
		BREAK

		CASE 38
		CASE 39
		CASE 46
		CASE 47
			boat_pos_temp_W2[0] = 35		
			boat_goto_X_temp_W2[0] = 2
			boat_goto_Y_temp_W2[0] = 3
			boat_pos_temp_W2[1] = 30		
			boat_goto_X_temp_W2[1] = 5
			boat_goto_Y_temp_W2[1] = 2
			IF square_attack_status_W2 < 40
				square_attack_status_W2 = 4
			ELSE
				square_attack_status_W2 = -1
			ENDIF
		BREAK

	ENDSWITCH

	pointer_W2 = 0
	WHILE pointer_W2 < 2
		IF NOT boat_position_W2[pointer_W2] = boat_pos_temp_W2[pointer_W2]
			boat_goto_X_W2[pointer_W2] = boat_goto_X_temp_W2[pointer_W2]
			boat_goto_Y_W2[pointer_W2] = boat_goto_Y_temp_W2[pointer_W2]
			boat_status_W2[pointer_W2] = 1
			boat_position_W2[pointer_W2] = boat_pos_temp_W2[pointer_W2]
		ENDIF
		pointer_W2 ++		
	ENDWHILE

RETURN

LVAR_INT x_pointer_W2 y_pointer_W2
mission_wuzi2_SUB_boat_status:

	pointer_W2 = 0

	WHILE pointer_W2 < 2

		IF NOT IS_CAR_DEAD boat_W2[pointer_W2]

			SWITCH boat_status_W2[pointer_W2]

				// goto coord
				CASE 1
					x_pointer_W2 = boat_goto_X_W2[pointer_W2]
					y_pointer_W2 = boat_goto_Y_W2[pointer_W2]
					
					ANCHOR_BOAT boat_W2[pointer_W2] FALSE
					
					BOAT_GOTO_COORDS boat_W2[pointer_W2] boat_square_X_W2[x_pointer_W2] boat_square_Y_W2[y_pointer_W2] 0.0
					SET_BOAT_CRUISE_SPEED boat_W2[pointer_W2] 7.0
					GET_CAR_SPEED boat_W2[pointer_W2] boat_speed_W2[pointer_W2]

					boat_status_W2[pointer_W2] ++
				BREAK

				
				// check if the boat is in the middle of the square
				CASE 2
					x_pointer_W2 = boat_goto_X_W2[pointer_W2]
					y_pointer_W2 = boat_goto_Y_W2[pointer_W2]

					IF LOCATE_CAR_2D boat_W2[pointer_W2] boat_square_X_W2[x_pointer_W2] boat_square_Y_W2[y_pointer_W2] 8.0 8.0 FALSE
						GET_CAR_SPEED boat_W2[pointer_W2] boat_speed_W2[pointer_W2]
						boat_status_W2[pointer_W2] ++	
					ELSE 
						// set boats speed
						IF boat_speed_W2[pointer_W2] < max_speed_W2  
							boat_speed_W2[pointer_W2] += acceleration_W2
							SET_CAR_FORWARD_SPEED boat_W2[pointer_W2] boat_speed_W2[pointer_W2]
						ENDIF
						 
						// set boat heading
						GET_CAR_COORDINATES boat_W2[pointer_W2] boat_X_W2[pointer_W2] boat_Y_W2[pointer_W2] z
						GET_CAR_HEADING boat_W2[pointer_W2] boat_heading_W2[pointer_W2]
						 
						x = boat_square_X_W2[x_pointer_W2] - boat_X_W2[pointer_W2]
						y = boat_square_Y_W2[y_pointer_W2] - boat_Y_W2[pointer_W2]
						WHILE boat_heading_W2[pointer_W2] >= 180.0
							boat_heading_W2[pointer_W2] += -360.0
						ENDWHILE

						// get desired heading 
						GET_HEADING_FROM_VECTOR_2D x y heading
						WHILE heading >= 180.0
							heading += -360.0
						ENDWHILE
						z = heading - boat_heading_W2[pointer_W2]
						IF z <= -180.0
							z += 360.0
						ENDIF
						IF z >= 180.0
							z += -360.0
						ENDIF

						// divide by factor
						z /= turn_factor_W2
						boat_heading_W2[pointer_W2] += z

						SET_CAR_HEADING boat_W2[pointer_W2] boat_heading_W2[pointer_W2]
					ENDIF
				BREAK

				CASE 3					
					boat_speed_W2[pointer_W2] -= acceleration_W2
					SET_CAR_FORWARD_SPEED boat_W2[pointer_W2] boat_speed_W2[pointer_W2]

					IF boat_speed_W2[pointer_W2] < 2.5
						SET_CAR_FORWARD_SPEED boat_W2[pointer_W2] 1.0  
						BOAT_STOP boat_W2[pointer_W2]
						ANCHOR_BOAT boat_W2[pointer_W2] TRUE
 						boat_status_W2[pointer_W2]  = 0
					ENDIF
				BREAK

			ENDSWITCH
		
		ENDIF
		
		pointer_W2 ++
	ENDWHILE
RETURN


LVAR_INT boat_can_attack_W2[2]
mission_wuzi2_SUB_guard_status:

	pointer_W2 = 0

	GET_CHAR_COORDINATES scplayer player_x player_y player_z
	WHILE pointer_W2 < 2
		
		IF NOT IS_CAR_DEAD boat_W2[pointer_W2]

			boat_can_attack_W2[pointer_W2] = 0
			
			SWITCH pointer_W2
				CASE 0
					IF square_attack_status_W2 = 0
					OR square_attack_status_W2 = 2
						boat_can_attack_W2[pointer_W2] = 1
					ELSE
						IF square_attack_status_W2 = 3
						OR square_attack_status_W2 = 5
							GET_CAR_COORDINATES boat_W2[pointer_W2] x y z
							GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance
							IF distance < 29.0
								boat_can_attack_W2[pointer_W2] = 1
							ENDIF
						ENDIF
					ENDIF	
				BREAK

				CASE 1
					IF square_attack_status_W2 = 1
					OR square_attack_status_W2 = 2
						boat_can_attack_W2[pointer_W2] = 1
					ELSE
						IF square_attack_status_W2 = 4
						OR square_attack_status_W2 = 5
							IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer boat_W2[pointer_W2] 30.0 30.0 FALSE
								boat_can_attack_W2[pointer_W2] = 1
							ENDIF
						ENDIF
					ENDIF
				BREAK
			ENDSWITCH 
			
			SWITCH guard_status_W2[pointer_W2]
				
				CASE 0 // guards just moving with boat
					IF player_breath_W2 = 0

						IF boat_can_attack_W2[pointer_W2] = 0
						OR player_underwater_W2 = 1
							
							guard_pointer_W2 = pointer_W2 * 3
							guard_limit_W2 =  guard_pointer_W2 + 3

							WHILE guard_pointer_W2 < guard_limit_W2 
								IF NOT IS_CHAR_DEAD boat_guards_W2[guard_pointer_W2]
									heading = boat_heading_W2[pointer_W2] - guard_heading_W2[guard_pointer_W2]
									SET_CHAR_HEADING boat_guards_W2[guard_pointer_W2] heading
								ENDIF
								guard_pointer_W2 ++
							ENDWHILE
						ELSE
							IF player_crawling_W2 = 1
							OR player_status_W2 >= 3
								GOSUB mission_wuzi2_SUB_guard_attack
								guard_status_W2[pointer_W2] = 3
							ELSE	
								IF player_status_W2 <= 0
									player_timer_W2 = 0
								ENDIF
								guard_status_W2[pointer_W2] ++
							ENDIF
						ENDIF					
					ENDIF 
				BREAK

				CASE 1 // boat has spotted player but no identified him
					IF boat_can_attack_W2[pointer_W2] = 0
					OR player_underwater_W2 = 1
						guard_timer_W2[pointer_W2] = 0
						guard_status_W2[pointer_W2] ++	
					ELSE
						IF player_timer_W2 > 6000
						OR player_crawling_W2 = 1
							GOSUB mission_wuzi2_SUB_guard_attack
							guard_status_W2[pointer_W2] = 3
						ENDIF
					ENDIF
				BREAK

				CASE 2 // boat has lost player after spotting him
					IF boat_can_attack_W2[pointer_W2] = 0
					OR player_underwater_W2 = 1
						IF guard_timer_W2[pointer_W2] > 3000
							guard_status_W2[pointer_W2] = 0
						ENDIF	
					ELSE
						IF player_status_W2 < 3
						AND player_crawling_W2 = 0
							player_timer_W2 = 0
							guard_status_W2[pointer_W2] --
						ELSE
							GOSUB mission_wuzi2_SUB_guard_attack
							guard_status_W2[pointer_W2] = 3
						ENDIF
					ENDIF	
				BREAK

				CASE 3 // guard has identified player either by timer or player crawling to close to boat
					IF boat_can_attack_W2[pointer_W2] = 0
					OR player_underwater_W2 = 1

						guard_pointer_W2 = pointer_W2 * 3
						guard_limit_W2 =  guard_pointer_W2 + 3

						WHILE guard_pointer_W2 < guard_limit_W2
							IF NOT IS_CHAR_DEAD boat_guards_W2[guard_pointer_W2] 
								CLEAR_CHAR_TASKS boat_guards_W2[guard_pointer_W2]
							ENDIF
							guard_pointer_W2 ++
						ENDWHILE

						guard_timer_W2[pointer_W2] = 0
						guard_status_W2[pointer_W2] ++
					ENDIF	
				BREAK

				CASE 4 // guard has lost player after he was identified
					IF boat_can_attack_W2[pointer_W2] = 0
					OR player_underwater_W2 = 1 
						IF guard_timer_W2[pointer_W2] > 5000
							guard_status_W2[pointer_W2] = 0
						ENDIF	
					ELSE
						GOSUB mission_wuzi2_SUB_guard_attack
						guard_status_W2[pointer_W2] = 3
					ENDIF
				BREAK

			ENDSWITCH
				
		ELSE
			IF DOES_BLIP_EXIST boat_blip_W2[pointer_W2]
				REMOVE_BLIP boat_blip_W2[pointer_W2]
			ENDIF
			IF NOT guard_status_W2[pointer_W2] = 0
				guard_status_W2[pointer_W2] = 0
			ENDIF
		ENDIF

		pointer_W2 ++

	ENDWHILE

RETURN

mission_wuzi2_SUB_guard_attack:
	
	guard_pointer_W2 = pointer_W2 * 3
	guard_limit_W2 =  guard_pointer_W2 + 3

	WHILE guard_pointer_W2 < guard_limit_W2
		IF NOT IS_CHAR_DEAD boat_guards_W2[guard_pointer_W2] 
			CLEAR_CHAR_TASKS boat_guards_W2[guard_pointer_W2]
			TASK_KILL_CHAR_ON_FOOT boat_guards_W2[guard_pointer_W2] scplayer
		ENDIF 
		guard_pointer_W2 ++
	ENDWHILE

RETURN



VAR_INT player_status_W2 player_breath_W2 player_first_spot_W2
LVAR_FLOAT player_breath_X_W2 player_breath_Y_W2
mission_wuzi2_SUB_player_status:
						  	
	SWITCH player_status_W2

		CASE -2
			IF player_underwater_W2 = 0
				GET_CHAR_COORDINATES scplayer player_breath_X_W2 player_breath_Y_W2 z
				player_status_W2 = -1 	
			ENDIF
		BREAK
		
		CASE -1
			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer player_breath_X_W2 player_breath_Y_W2 2.5 2.5 FALSE
				player_breath_W2 = 0
				player_status_W2 = 0	
			ENDIF 
		BREAK
		
		// player has not been spotted or boats have gone back to a normal patrol after spotting player
		CASE 0
			IF guard_status_W2[0] > 0
			OR guard_status_W2[1] > 0
				// player spotted
				IF guard_status_W2[0] < 3
				AND guard_status_W2[1] < 3
					
					speech_context_W2 = 0
					GOSUB mission_wuzi2_SUB_play_dialogue 

					player_status_W2 = 1
					IF player_first_spot_W2 = 0
						player_first_spot_W2 = 1
					ENDIF 
				ELSE
					
					speech_context_W2 = 18
					GOSUB mission_wuzi2_SUB_play_dialogue

					player_status_W2 = 3
					IF player_first_spot_W2 = 0
						player_first_spot_W2 = 1
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		// boats have spotted player but not identified him
		CASE 1
			IF guard_status_W2[0] >= 3
			OR guard_status_W2[1] >= 3
				
				speech_context_W2 = 18
				GOSUB mission_wuzi2_SUB_play_dialogue

				player_status_W2 = 3
			ELSE
				IF NOT guard_status_W2[0] = 1
				AND NOT guard_status_W2[1] = 1
					IF guard_status_W2[0] = 2
					OR guard_status_W2[1] = 2
						player_status_W2 = 2
					ELSE
						IF guard_status_W2[0] = 0
						OR guard_status_W2[1] = 0
							
							speech_context_W2 = 9
							GOSUB mission_wuzi2_SUB_play_dialogue

							player_breath_W2 = 1
							player_status_W2 = -2
						ENDIF
					ENDIF						
				ENDIF
			ENDIF
		BREAK

		// player has lost the attencion of the boats
		CASE 2
			IF guard_status_W2[0] >= 3
			OR guard_status_W2[1] >= 3
				
				speech_context_W2 = 18
				GOSUB mission_wuzi2_SUB_play_dialogue

				player_status_W2 = 3
			ELSE
				IF NOT guard_status_W2[0] = 2
				AND NOT guard_status_W2[1] = 2
					IF guard_status_W2[0] = 1
					OR guard_status_W2[1] = 1
						
						speech_context_W2 = 0
						GOSUB mission_wuzi2_SUB_next_speaker
						GOSUB mission_wuzi2_SUB_play_dialogue

						player_status_W2 = 1
					ELSE
						IF guard_status_W2[0] = 0
						OR guard_status_W2[1] = 0

							speech_context_W2 = 9
							GOSUB mission_wuzi2_SUB_play_dialogue

							GOSUB mission_wuzi2_SUB_next_speaker
							player_breath_W2 = 1
							player_status_W2 = -2
						ENDIF
					ENDIF						
				ENDIF
			ENDIF
		BREAK

		// boats are attacking the player
		CASE 3
			IF NOT guard_status_W2[0] = 3
			AND NOT guard_status_W2[1] = 3
				IF guard_status_W2[0] = 4
				OR guard_status_W2[1] = 4

					speech_context_W2 = 27
					IF guard_status_W2[0] = 4
						boat_lost_W2 = 0
					ELSE
						boat_lost_W2 = 1
					ENDIF
					GOSUB mission_wuzi2_SUB_play_dialogue

					player_status_W2 = 4

					GOSUB mission_wuzi2_SUB_next_speaker 
				ENDIF						
			ENDIF
		BREAK

		// player has lost the attencion of the boats
		CASE 4
			IF NOT guard_status_W2[0] = 4
			AND NOT guard_status_W2[1] = 4
				IF guard_status_W2[0] = 3
				OR guard_status_W2[1] = 3
					
					IF guard_status_W2[0] = 3
						IF boat_lost_W2 = 0
							speech_context_W2 = 45
						ELSE
							speech_context_W2 = 45
						ENDIF
					ELSE
						IF boat_lost_W2 = 1
							speech_context_W2 = 45
						ELSE
							speech_context_W2 = 45
						ENDIF
					ENDIF

					GOSUB mission_wuzi2_SUB_next_speaker
					GOSUB mission_wuzi2_SUB_play_dialogue

					player_status_W2 = 3
				ELSE
					IF guard_status_W2[0] = 0
					AND guard_status_W2[1] = 0

						speech_context_W2 = 36
						GOSUB mission_wuzi2_SUB_play_dialogue

						player_breath_W2 = 1
						player_status_W2 = -2
					ENDIF					
				ENDIF						
			ENDIF
		BREAK

	ENDSWITCH

	
	SWITCH player_first_spot_W2

		CASE 0
			IF explain_searchlights_W2 = 1
				PRINT_HELP_FOREVER WZI2_42 
				TIMERB = 0
				explain_searchlights_W2 ++				
			ENDIF
		BREAK

		CASE 1
			PRINT_HELP_FOREVER WZI2_36 
			TIMERB = 0
			player_first_spot_W2 ++
		BREAK

		CASE 2
			IF TIMERB > 6000
				CLEAR_HELP
				IF explain_searchlights_W2 = 1
					PRINT_HELP_FOREVER WZI2_42 
					TIMERB = 0
					explain_searchlights_W2 ++				
				ELSE
					PRINT_HELP_FOREVER WZI2_37 
					TIMERB = 0
					player_first_spot_W2 ++
				ENDIF
			ENDIF
		BREAK

		CASE 3
			IF TIMERB > 6000
				CLEAR_HELP
				IF explain_searchlights_W2 = 1
					PRINT_HELP_FOREVER WZI2_42 
					TIMERB = 0
					explain_searchlights_W2 ++				
				ELSE
					PRINT_HELP_FOREVER WZI2_38 
					TIMERB = 0
					player_first_spot_W2 ++
				ENDIF
			ENDIF
		BREAK

		CASE 4
			IF TIMERB > 6000
				CLEAR_HELP
				IF explain_searchlights_W2 = 1
					PRINT_HELP_FOREVER WZI2_42 
					TIMERB = 0
					explain_searchlights_W2 ++				
				ELSE
					PRINT_HELP_FOREVER WZI2_39 
					TIMERB = 0
					player_first_spot_W2 ++
				ENDIF
			ENDIF
		BREAK

		CASE 5
			IF TIMERB > 6000
				CLEAR_HELP
				IF explain_searchlights_W2 = 1
					PRINT_HELP_FOREVER WZI2_42 
					TIMERB = 0
					explain_searchlights_W2 ++
					player_first_spot_W2 ++
				ELSE
					IF explain_searchlights_W2 = 2
						player_first_spot_W2 ++
					ENDIF
				ENDIF	 
			ENDIF
		BREAK
				
	ENDSWITCH
RETURN



// ##########################################################################################################
// ##########################################################################################################
// ##########################################################################################################
// ##########################################################################################################

//boat_W2 //boat_guards_W2 
LVAR_INT gun_pointer_W2 stop_and_attack_W2 
mission_wuzi2_SUB_gun_check:

	gun_pointer_W2 = 0
	WHILE gun_pointer_W2 < 6
		IF NOT IS_CHAR_DEAD boat_guards_W2[gun_pointer_W2]
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR boat_guards_W2[gun_pointer_W2] scplayer
				stop_and_attack_W2 = 1
				gun_pointer_W2 = 6
			ELSE
				gun_pointer_W2 ++
			ENDIF
		ELSE
			stop_and_attack_W2 = 1
			gun_pointer_W2 = 6
		ENDIF
	ENDWHILE
	
	IF stop_and_attack_W2 = 0 
		gun_pointer_W2 = 0
		WHILE gun_pointer_W2 < 6
			IF NOT IS_CHAR_DEAD s_boat_guard_W2[gun_pointer_W2]
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR s_boat_guard_W2[gun_pointer_W2] scplayer
					stop_and_attack_W2 = 1
					gun_pointer_W2 = 6
				ELSE
					gun_pointer_W2 ++
				ENDIF
			ELSE
				stop_and_attack_W2 = 1
				gun_pointer_W2 = 6
			ENDIF
		ENDWHILE
	ENDIF

	IF stop_and_attack_W2 = 1
		GOSUB mission_wuzi2_SUB_stop_and_attack_setup
		stop_and_attack_W2 = 2
	ENDIF
	    	
RETURN



LVAR_INT s_and_a_status_W2[4]
mission_wuzi2_SUB_stop_and_attack_setup:

	
	CLEAR_HELP
	CLEAR_PRINTS

	IF NOT IS_CAR_DEAD boat_W2[0]
		//FREEZE_CAR_POSITION boat_W2[0] TRUE
		IF NOT DOES_BLIP_EXIST boat_blip_W2[0]
			ADD_BLIP_FOR_CAR boat_W2[0] boat_blip_W2[0]
			CHANGE_BLIP_DISPLAY boat_blip_W2[0] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD boat_W2[1]
		//FREEZE_CAR_POSITION boat_W2[1] TRUE
		IF NOT DOES_BLIP_EXIST boat_blip_W2[1]
			ADD_BLIP_FOR_CAR boat_W2[1] boat_blip_W2[1]
			CHANGE_BLIP_DISPLAY boat_blip_W2[1] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[0]
		//FREEZE_CAR_POSITION s_boat_W2[0] TRUE
		IF NOT DOES_BLIP_EXIST s_boat_blip_W2[0]  
			ADD_BLIP_FOR_CAR s_boat_W2[0] s_boat_blip_W2[0]
			CHANGE_BLIP_DISPLAY s_boat_blip_W2[0] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[1]
		//FREEZE_CAR_POSITION s_boat_W2[1] TRUE
		IF NOT DOES_BLIP_EXIST s_boat_blip_W2[1]
			ADD_BLIP_FOR_CAR s_boat_W2[1] s_boat_blip_W2[1]
			CHANGE_BLIP_DISPLAY s_boat_blip_W2[1] BLIP_ONLY
		ENDIF
	ENDIF
	
	gun_pointer_W2 = 0
	WHILE gun_pointer_W2 < 6
		IF NOT IS_CHAR_DEAD boat_guards_W2[gun_pointer_W2] 
			SET_CHAR_ACCURACY boat_guards_W2[gun_pointer_W2] 80
			SET_CHAR_SHOOT_RATE boat_guards_W2[gun_pointer_W2] 50
		ENDIF
		IF NOT IS_CHAR_DEAD s_boat_guard_W2[gun_pointer_W2]
			SET_CHAR_ACCURACY s_boat_guard_W2[gun_pointer_W2] 80
			SET_CHAR_SHOOT_RATE s_boat_guard_W2[gun_pointer_W2] 50
		ENDIF
		gun_pointer_W2 ++
	ENDWHILE

	s_and_a_status_W2[0] = 0
	s_and_a_status_W2[1] = 0
	s_and_a_status_W2[2] = 0
	s_and_a_status_W2[3] = 0

RETURN


mission_wuzi2_SUB_stop_and_attack:

	GET_CHAR_COORDINATES scplayer player_x player_y player_z

	IF NOT IS_CAR_DEAD boat_W2[0] 
		IF s_and_a_status_W2[0] = 0
			IF LOCATE_CAR_2D boat_W2[0] player_x player_y 75.0 75.0 FALSE
				IF NOT IS_CHAR_DEAD boat_guards_W2[0]
					CLEAR_CHAR_TASKS boat_guards_W2[0]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[0] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[1]
					CLEAR_CHAR_TASKS boat_guards_W2[1]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[1] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[2]
					CLEAR_CHAR_TASKS boat_guards_W2[2]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[2] scplayer
				ENDIF
				s_and_a_status_W2[0] = 1
				//write_debug boat_0_shooting 
			ENDIF 
		ELSE
			IF NOT LOCATE_CAR_2D boat_W2[0] player_x player_y 85.0 85.0 FALSE
				IF NOT IS_CHAR_DEAD boat_guards_W2[0]
					CLEAR_CHAR_TASKS boat_guards_W2[0]
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[1]
					CLEAR_CHAR_TASKS boat_guards_W2[1]
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[2]
					CLEAR_CHAR_TASKS boat_guards_W2[2]
				ENDIF
				s_and_a_status_W2[0] = 0
				//write_debug boat_0_stopped_shooting 
			ENDIF 
		ENDIF
	ELSE
		IF DOES_BLIP_EXIST boat_blip_W2[0]
			REMOVE_BLIP boat_blip_W2[0]
		ENDIF 
	ENDIF 

	IF NOT IS_CAR_DEAD boat_W2[1] 
		IF s_and_a_status_W2[1] = 0
			IF LOCATE_CAR_2D boat_W2[1] player_x player_y 75.0 75.0 FALSE
				IF NOT IS_CHAR_DEAD boat_guards_W2[3]
					CLEAR_CHAR_TASKS boat_guards_W2[3]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[3] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[4]
					CLEAR_CHAR_TASKS boat_guards_W2[4]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[4] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[5]
					CLEAR_CHAR_TASKS boat_guards_W2[5]
					TASK_KILL_CHAR_ON_FOOT boat_guards_W2[5] scplayer
				ENDIF
				s_and_a_status_W2[1] = 1
				//write_debug boat_1_stopped_shooting 
			ENDIF 
		ELSE
			IF NOT LOCATE_CAR_2D boat_W2[1] player_x player_y 85.0 85.0 FALSE
				IF NOT IS_CHAR_DEAD boat_guards_W2[3]
					CLEAR_CHAR_TASKS boat_guards_W2[3]
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[4]
					CLEAR_CHAR_TASKS boat_guards_W2[4]
				ENDIF
				IF NOT IS_CHAR_DEAD boat_guards_W2[5]
					CLEAR_CHAR_TASKS boat_guards_W2[5]
				ENDIF
				s_and_a_status_W2[1] = 0
				//write_debug boat_1_stopped_shooting 
			ENDIF 
		ENDIF
	ELSE
		IF DOES_BLIP_EXIST boat_blip_W2[1]
			REMOVE_BLIP boat_blip_W2[1]
		ENDIF 
	ENDIF
	

	// spotlight boats
	IF NOT IS_CAR_DEAD s_boat_W2[0] 
		IF s_and_a_status_W2[2] = 0
			IF LOCATE_CAR_2D s_boat_W2[0] player_x player_y 75.0 75.0 FALSE
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[0]
					CLEAR_CHAR_TASKS s_boat_guard_W2[0]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[0] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[1]
					CLEAR_CHAR_TASKS s_boat_guard_W2[1]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[1] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[2]
					CLEAR_CHAR_TASKS s_boat_guard_W2[2]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[2] scplayer
				ENDIF
				s_and_a_status_W2[2] = 1 
			ENDIF 
		ELSE
			IF NOT LOCATE_CAR_2D s_boat_W2[0] player_x player_y 85.0 85.0 FALSE
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[0]
					CLEAR_CHAR_TASKS s_boat_guard_W2[0]
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[1]
					CLEAR_CHAR_TASKS s_boat_guard_W2[1]
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[2]
					CLEAR_CHAR_TASKS s_boat_guard_W2[2]
				ENDIF
				s_and_a_status_W2[2] = 0 
			ENDIF 
		ENDIF
	ELSE
		IF DOES_BLIP_EXIST s_boat_blip_W2[0]
			REMOVE_BLIP s_boat_blip_W2[0]
		ENDIF 
	ENDIF

	IF NOT IS_CAR_DEAD s_boat_W2[1] 
		IF s_and_a_status_W2[3] = 0
			IF LOCATE_CAR_2D s_boat_W2[1] player_x player_y 75.0 75.0 FALSE
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[3]
					CLEAR_CHAR_TASKS s_boat_guard_W2[3]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[3] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[4]
					CLEAR_CHAR_TASKS s_boat_guard_W2[4]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[4] scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[5]
					CLEAR_CHAR_TASKS s_boat_guard_W2[5]
					TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[5] scplayer
				ENDIF
				s_and_a_status_W2[3] = 1 
			ENDIF 
		ELSE
			IF NOT LOCATE_CAR_2D s_boat_W2[1] player_x player_y 85.0 85.0 FALSE
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[3]
					CLEAR_CHAR_TASKS s_boat_guard_W2[3]
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[4]
					CLEAR_CHAR_TASKS s_boat_guard_W2[4]
				ENDIF
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[5]
					CLEAR_CHAR_TASKS s_boat_guard_W2[5]
				ENDIF
				s_and_a_status_W2[3] = 0 
			ENDIF 
		ENDIF
	ELSE
		IF DOES_BLIP_EXIST s_boat_blip_W2[1]
			REMOVE_BLIP s_boat_blip_W2[1]
		ENDIF 
	ENDIF

RETURN	   	









// ##########################################################################################################
// ##########################################################################################################
// ##########################################################################################################
// ##########################################################################################################


LVAR_INT speaker_W2
mission_wuzi2_SUB_play_dialogue:

	audio_pointer_W2 = speech_sample_W2 * 3
	audio_pointer_W2 += speech_ped_W2
	audio_pointer_W2 += speech_context_W2

	IF boat_can_attack_W2[0] = 1
		speaker_W2 = speech_ped_W2
	ELSE
		speaker_W2 = speech_ped_W2+3		 	 
	ENDIF

	load_sample = audio_sound_label_W2[audio_pointer_W2]
	$load_text = $audio_text_label_W2[audio_pointer_W2] 

	// audio now played through front end
	START_NEW_SCRIPT audio_load_and_play 2 speech_priority_W2 boat_guards_W2[speaker_W2]
	//START_NEW_SCRIPT audio_load_and_play 1 speech_priority_W2 boat_guards_W2[speaker_W2]

	speech_priority_W2 ++

RETURN

mission_wuzi2_SUB_next_speaker:

	speech_ped_W2 ++ 
	IF speech_ped_W2 = 3
		speech_ped_W2 = 0
		speech_sample_W2 ++
		IF speech_sample_W2 = 3
			speech_sample_W2 = 0
		ENDIF
	ENDIF
RETURN



LVAR_INT knife_status_W2 weapon_type_W2 void_W2
mission_wuzi2_SUB_knife_status:

	GET_CHAR_WEAPON_IN_SLOT scplayer 2 weapon_type_W2 void_W2 void_W2
	IF weapon_type_W2 = WEAPONTYPE_UNARMED
		knife_status_W2 = 0
	ELSE
		IF weapon_type_W2 = WEAPONTYPE_KNIFE
			knife_status_W2 = 1
		ELSE
			knife_status_W2 = 2
		ENDIF
	ENDIF 		

RETURN


mission_wuzi2_SUB_reduce_guard_accuracy:
	
	guard_pointer_W2 = 0
	WHILE guard_pointer_W2 < 6
		IF NOT IS_CHAR_DEAD boat_guards_W2[guard_pointer_W2]
			SET_CHAR_ACCURACY boat_guards_W2[guard_pointer_W2] 30
			SET_CHAR_SHOOT_RATE boat_guards_W2[guard_pointer_W2] 30
		ENDIF
		guard_pointer_W2 ++
	ENDWHILE
RETURN


LVAR_INT grid_status_W2	
mission_wuzi2_DEBUG_draw_grid:	
	IF grid_status_W2 = 1
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
		AND TIMERB > 200
			TIMERB = 0
			grid_status_W2 = 0	
		ENDIF
		
		LINE swim_grid_X_W2[0] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[0] 2.0  
		LINE swim_grid_X_W2[0] swim_grid_Y_W2[1] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[1] 2.0
		LINE swim_grid_X_W2[0] swim_grid_Y_W2[2] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[2] 2.0
		LINE swim_grid_X_W2[0] swim_grid_Y_W2[3] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[3] 2.0
		LINE swim_grid_X_W2[0] swim_grid_Y_W2[4] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[4] 2.0

		LINE swim_grid_X_W2[0] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[0] swim_grid_Y_W2[4] 2.0  
		LINE swim_grid_X_W2[1] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[1] swim_grid_Y_W2[4] 2.0
		LINE swim_grid_X_W2[2] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[2] swim_grid_Y_W2[4] 2.0
		LINE swim_grid_X_W2[3] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[3] swim_grid_Y_W2[4] 2.0
		LINE swim_grid_X_W2[4] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[4] swim_grid_Y_W2[4] 2.0
		LINE swim_grid_X_W2[5] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[5] swim_grid_Y_W2[4] 2.0
		LINE swim_grid_X_W2[6] swim_grid_Y_W2[0] 2.0 swim_grid_X_W2[6] swim_grid_Y_W2[4] 2.0
	ELSE
		IF grid_status_W2 = 0
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
			AND TIMERB > 200
				TIMERB = 0
				grid_status_W2 = 1	
			ENDIF
		ENDIF
	ENDIF
RETURN



// ##########
// ##
// ## SEARCHLIGHTS  
// ##
// ##########################################################################################################

LVAR_INT searchlight_W2[6] searchlight_base_W2[6] searchlight_housing_W2[6] searchlight_bulb_W2[6] searchlight_blip_W2[6]
LVAR_INT s_boat_W2[2] s_boat_blip_W2[2] s_boat_guard_W2[6]


mission_wuzi2_SUB_create_searchlights:
	
	CREATE_SEARCHLIGHT -1474.1450 1484.0377 11.3824 -1465.1536 1463.0044 -0.5 25.0 1.0 searchlight_W2[0]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBASE -1474.1450 1484.0377 10.8824 searchlight_base_W2[0]
	SET_OBJECT_HEADING searchlight_base_W2[0] 326.1907 
	CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING -1474.1450 1484.0377 10.8824 searchlight_housing_W2[0]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBULB -1474.1450 1484.0377 10.8824 searchlight_bulb_W2[0]
	ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT searchlight_W2[0] searchlight_base_W2[0] searchlight_housing_W2[0] searchlight_bulb_W2[0] 0.0 1.181 0.768
	SET_OBJECT_HEALTH searchlight_bulb_W2[0] 1000	     
	MOVE_SEARCHLIGHT_BETWEEN_COORDS searchlight_W2[0] -1465.1536 1463.0044 -0.5 -1489.6720 1472.1106 -0.5 0.1
	SET_SEARCHLIGHT_CLIP_IF_COLLIDING searchlight_W2[0]	FALSE
	bulb_status_W2[0] = 0

 	CREATE_SEARCHLIGHT -1474.1984 1494.9270 11.6265 -1459.3628 1514.4128 -0.5 25.0 1.0 searchlight_W2[1]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBASE -1474.1984 1494.9270 10.8824 searchlight_base_W2[1]
	SET_OBJECT_HEADING searchlight_base_W2[1] 34.4591 
	CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING -1474.1984 1494.9270 10.8824 searchlight_housing_W2[1]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBULB -1474.1984 1494.9270 10.8824 searchlight_bulb_W2[1]
	ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT searchlight_W2[1] searchlight_base_W2[1] searchlight_housing_W2[1] searchlight_bulb_W2[1] 0.0 1.181 0.768
	SET_OBJECT_HEALTH searchlight_bulb_W2[1] 1000
	MOVE_SEARCHLIGHT_BETWEEN_COORDS searchlight_W2[1] -1459.3628 1514.4128 -0.5 -1491.3789 1501.3086 -0.5 0.08
	SET_SEARCHLIGHT_CLIP_IF_COLLIDING searchlight_W2[1]	FALSE
	bulb_status_W2[1] = 0

	CREATE_SEARCHLIGHT -1369.2788 1484.2191 14.3425 -1351.9365 1504.4353 -0.5 25.0 1.0 searchlight_W2[2]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBASE -1369.2788 1484.2191 13.8425 searchlight_base_W2[2]
	SET_OBJECT_HEADING searchlight_base_W2[2] 32.5701
	CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING -1369.2788 1484.2191 13.8425 searchlight_housing_W2[2]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBULB -1369.2788 1484.2191 13.8425 searchlight_bulb_W2[2]
	ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT searchlight_W2[2] searchlight_base_W2[2] searchlight_housing_W2[2] searchlight_bulb_W2[2] 0.0 1.181 0.768
	SET_OBJECT_HEALTH searchlight_bulb_W2[2] 1000
	MOVE_SEARCHLIGHT_BETWEEN_COORDS searchlight_W2[2] -1347.1427 1476.6301 -0.5 -1370.4989 1465.4791 -0.5 0.08
	SET_SEARCHLIGHT_CLIP_IF_COLLIDING searchlight_W2[2]	FALSE
	bulb_status_W2[2] = 0
													  
	CREATE_SEARCHLIGHT -1368.4672 1494.2252 14.3425 -1347.1427 1476.6301 -0.5 25.0 1.0 searchlight_W2[3]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBASE -1368.4672 1494.2252 13.8425 searchlight_base_W2[3]
	SET_OBJECT_HEADING searchlight_base_W2[3] 327.3304
	CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING -1368.4672 1494.2252 13.8425 searchlight_housing_W2[3]
	CREATE_OBJECT_NO_OFFSET A51_SPOTBULB -1368.4672 1494.2252 13.8425 searchlight_bulb_W2[3]
	ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT searchlight_W2[3] searchlight_base_W2[3] searchlight_housing_W2[3] searchlight_bulb_W2[3] 0.0 1.181 0.768
	SET_OBJECT_HEALTH searchlight_bulb_W2[3] 1000
	MOVE_SEARCHLIGHT_BETWEEN_COORDS searchlight_W2[3] -1351.9365 1504.4353 -0.5 -1377.0283 1515.7286 -0.5 0.1	
	SET_SEARCHLIGHT_CLIP_IF_COLLIDING searchlight_W2[3]	FALSE
	bulb_status_W2[3] = 0

	// boats for shooting player in searchlight
	// boat 0
	CREATE_CAR TROPIC -1491.6624 1488.5 1.0 s_boat_W2[0]
	SET_CAR_HEADING s_boat_W2[0] 180.0
	ANCHOR_BOAT s_boat_W2[0] TRUE 

	CREATE_CHAR PEDTYPE_MISSION1 DNB3 -1521.0 1492.0 5.0 s_boat_guard_W2[0]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[0] s_boat_W2[0] 0.0 9.0 2.1 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[0] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[0] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[0] 20
	
	CREATE_CHAR PEDTYPE_MISSION1 DNB2 -1521.0 1492.0 5.0 s_boat_guard_W2[1]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[1] s_boat_W2[0] -1.0 -1.0 3.70 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[1] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[1] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[1] 20

	CREATE_CHAR PEDTYPE_MISSION1 DNB1 -1521.0 1492.0 5.0 s_boat_guard_W2[2]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[2] s_boat_W2[0] 1.0 -4.0 1.15 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[2] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[2] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[2] 20

	// boat 1
	CREATE_CAR TROPIC -1350.1204 1488.5 1.0 s_boat_W2[1]
	SET_CAR_HEADING s_boat_W2[1] 0.0
	ANCHOR_BOAT s_boat_W2[1] TRUE 

	CREATE_CHAR PEDTYPE_MISSION1 DNB3 -1322.0 1489.5 5.0 s_boat_guard_W2[3]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[3] s_boat_W2[1] 0.0 9.0 2.1 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[3] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[3] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[3] 20	  

	CREATE_CHAR PEDTYPE_MISSION1 DNB2 -1322.0 1489.5 5.0 s_boat_guard_W2[4]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[4] s_boat_W2[1] -1.0 -1.0 3.70 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[4] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[4] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[4] 20	
	
	CREATE_CHAR PEDTYPE_MISSION1 DNB1 -1322.0 1489.5 5.0 s_boat_guard_W2[5]
	ATTACH_CHAR_TO_CAR s_boat_guard_W2[5] s_boat_W2[1] 1.0 -4.0 1.15 FACING_FORWARD 360.0 WEAPONTYPE_AK47 
	TASK_STAY_IN_SAME_PLACE s_boat_guard_W2[5] TRUE 
	SET_CHAR_ACCURACY s_boat_guard_W2[5] 80
	SET_CHAR_SHOOT_RATE s_boat_guard_W2[5] 20	

	spotlight_status_W2 = 0

RETURN





LVAR_INT spotlight_status_W2 in_searchlight_W2 active_boat_W2 
mission_wuzi2_SUB_check_spotlights:


	SWITCH spotlight_status_W2
		
		CASE 0 // player not in any searchlight or gone out of range?
			IF player_underwater_W2 = 0
				IF NOT IS_CHAR_IN_ANY_SEARCHLIGHT scplayer in_searchlight_W2
					
					IF NOT IS_CHAR_IN_WATER scplayer
					
						IF NOT IS_CAR_DEAD s_boat_W2[0] 
							IF IS_CHAR_TOUCHING_VEHICLE scplayer s_boat_W2[0]
								speech_context_W2 = 18
								GOSUB mission_wuzi2_SUB_play_dialogue

								active_boat_W2 = 0
								GOSUB mission_wuzi2_SUB_searchlight_guard_tasks
								spotlight_status_W2 = 1 // climbed on boat 0
								BREAK	
							ENDIF 
						ENDIF
						IF NOT IS_CAR_DEAD s_boat_W2[1] 
							IF IS_CHAR_TOUCHING_VEHICLE scplayer s_boat_W2[1]
								speech_context_W2 = 18
								GOSUB mission_wuzi2_SUB_play_dialogue

								active_boat_W2 = 1
								GOSUB mission_wuzi2_SUB_searchlight_guard_tasks
								spotlight_status_W2 = 1 // climbed on boat 1	
							ENDIF 
						ENDIF
					ELSE
						BREAK
					ENDIF

				ELSE
				
			  		speech_context_W2 = 18
					GOSUB mission_wuzi2_SUB_play_dialogue
			  		
			  		GET_CHAR_COORDINATES scplayer player_x player_y player_z
					IF player_x < -1430.0
						active_boat_W2 = 0
					ELSE
						active_boat_W2 = 1
					ENDIF
					
					GOSUB mission_wuzi2_SUB_searchlight_guard_tasks
					spotlight_status_W2 = 1

				ENDIF
			ENDIF
		BREAK
		
		CASE 1
			IF NOT IS_CAR_DEAD s_boat_W2[active_boat_W2]
				
				IF player_underwater_W2 = 0
					
					GET_CAR_COORDINATES s_boat_W2[active_boat_W2] x y z
					GET_CHAR_COORDINATES scplayer player_x player_y player_z

					GET_DISTANCE_BETWEEN_COORDS_2D x y player_x player_y distance
					
					IF distance < 55.0
						BREAK
					ENDIF

					speech_context_W2 = 36
				ELSE
					speech_context_W2 = 27
				ENDIF

				GOSUB mission_wuzi2_SUB_play_dialogue

				active_boat_W2 += 2
				GOSUB mission_wuzi2_SUB_searchlight_guard_tasks
				GOSUB mission_wuzi2_SUB_next_speaker

				spotlight_status_W2 = 0

			ELSE
				active_boat_W2 += 2
				GOSUB mission_wuzi2_SUB_searchlight_guard_tasks
				spotlight_status_W2 = 0
			ENDIF
		BREAK

	ENDSWITCH
			
 
RETURN

LVAR_INT search_pointer_W2 alive_total_W2
mission_wuzi2_SUB_searchlight_guard_tasks:

	alive_total_W2 = 0
	
	SWITCH active_boat_W2
	
		CASE 0 // boat 0 attack
		CASE 2 // boat 0 reset
			search_pointer_W2 = 0
			WHILE search_pointer_W2 < 3
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[search_pointer_W2]
					CLEAR_CHAR_TASKS s_boat_guard_W2[search_pointer_W2]
					IF active_boat_W2 = 0
						TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[search_pointer_W2] scplayer 
					ENDIF
					alive_total_W2 ++
				ENDIF
				search_pointer_W2 ++
			ENDWHILE
		BREAK

		CASE 1 // boat 1 attack
		CASE 3 // boat 1 reset
			search_pointer_W2 = 3
			WHILE search_pointer_W2 < 6
				IF NOT IS_CHAR_DEAD s_boat_guard_W2[search_pointer_W2]
					CLEAR_CHAR_TASKS s_boat_guard_W2[search_pointer_W2]
					IF active_boat_W2 = 1 
						TASK_KILL_CHAR_ON_FOOT s_boat_guard_W2[search_pointer_W2] scplayer
					ENDIF 
					alive_total_W2 ++
				ENDIF
				search_pointer_W2 ++
			ENDWHILE
		BREAK

	ENDSWITCH 

RETURN







LVAR_INT bulb_status_W2[6] health_W2
mission_wuzi2_SUB_check_bulbs:

	pointer_W2 = 0
	WHILE pointer_W2 < 4

		IF bulb_status_W2[pointer_W2] = 0
		AND DOES_OBJECT_EXIST searchlight_bulb_W2[pointer_W2]
			
			GET_OBJECT_HEALTH searchlight_bulb_W2[pointer_W2] health_W2
			IF health_W2 < 1000
				DELETE_SEARCHLIGHT searchlight_W2[pointer_W2]
				REMOVE_BLIP searchlight_blip_W2[pointer_W2]   
				bulb_status_W2[pointer_W2] ++
			ENDIF 	 

		ENDIF
		pointer_W2 ++
	ENDWHILE

RETURN



mission_wuzi2_SUB_player_swimming_help:

	IF help_text_flag_W2 > 0
	 
		IF help_text_flag_W2 = 1
		AND player_status_W2 > 0  
			
			IF TIMERB > 6500
			AND help_text_switch_W2 < 4
				help_text_switch_W2 ++
			ENDIF

			CLEAR_HELP
			PRINT_HELP_FOREVER WZI2_B4			
			TIMERB = 0
			help_text_flag_W2 ++

		ELSE

			IF help_text_switch_W2 < 5 
				SWITCH help_text_switch_W2
				
					CASE -2
						PRINT_NOW WZI2_8 7000 1
						TIMERB = 0
						help_text_switch_W2 ++	 
					BREAK

					CASE -1
						IF TIMERB > 7000
							PRINT_NOW WZI2_9 8000 1
							TIMERB = 6500
							help_text_switch_W2 ++
						ENDIF	 
					BREAK
					
					CASE 4
						IF TIMERB > 8000
							CLEAR_HELP
							help_text_switch_W2 ++
						ENDIF
					BREAK

					DEFAULT
						IF TIMERB > 8000
							CLEAR_HELP
							PRINT_HELP_FOREVER $help_text_W2[help_text_switch_W2]
							TIMERB = 0
							help_text_switch_W2 ++
						ENDIF
					BREAK
				
				ENDSWITCH
			ENDIF
		ENDIF

	ELSE
		IF player_underwater_W2 = FALSE
			help_text_flag_W2 = 1
		ENDIF
	ENDIF 

RETURN


LVAR_INT radar_status_W2
mission_wuzi2_SUB_control_radar:

	SWITCH radar_status_W2

		CASE 0
			IF NOT IS_CHAR_IN_WATER scplayer
				IF IS_CHAR_IN_AREA_3D scplayer -1491.3191 1505.3339 0.0 -1352.4364 1475.7378 15.0 FALSE
					GOSUB mission_wuzi2_SUB_add_boat_guard_blips
					radar_status_W2 ++
					BREAK
				ENDIF
			ENDIF
		BREAK

		CASE 1
			IF IS_CHAR_IN_WATER scplayer
				GOSUB mission_wuzi2_SUB_add_boat_and_search_blips
				radar_status_W2 --
				BREAK
			ENDIF			
		BREAK

	ENDSWITCH

RETURN


mission_wuzi2_SUB_add_boat_and_search_blips:

	// remove blips
	pointer_W2 = 0
	WHILE pointer_W2 < 5
		REMOVE_BLIP guard_blip_W2[pointer_W2]
		pointer_W2 ++
	ENDWHILE
	
	// add blips
	SET_RADAR_ZOOM 0

	IF NOT IS_CAR_DEAD boat_W2[0]
		IF NOT DOES_BLIP_EXIST boat_blip_W2[0]
			ADD_BLIP_FOR_CAR boat_W2[0] boat_blip_W2[0]
			CHANGE_BLIP_DISPLAY boat_blip_W2[0] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD boat_W2[1]
		IF NOT DOES_BLIP_EXIST boat_blip_W2[1]
			ADD_BLIP_FOR_CAR boat_W2[1] boat_blip_W2[1]
			CHANGE_BLIP_DISPLAY boat_blip_W2[1] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[0]
		IF NOT DOES_BLIP_EXIST s_boat_blip_W2[0]
			ADD_BLIP_FOR_CAR s_boat_W2[0] s_boat_blip_W2[0]
			CHANGE_BLIP_DISPLAY s_boat_blip_W2[0] BLIP_ONLY
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[1]
		IF NOT DOES_BLIP_EXIST s_boat_blip_W2[1]
			ADD_BLIP_FOR_CAR s_boat_W2[1] s_boat_blip_W2[1]
			CHANGE_BLIP_DISPLAY s_boat_blip_W2[1] BLIP_ONLY
		ENDIF
	ENDIF

	pointer_W2 = 0
	WHILE pointer_W2 < 4
		IF bulb_status_W2[pointer_W2] = 0
			IF NOT DOES_BLIP_EXIST searchlight_blip_W2[pointer_W2]
				ADD_BLIP_FOR_SEARCHLIGHT searchlight_W2[pointer_W2] searchlight_blip_W2[pointer_W2]
			ENDIF
		ENDIF
		pointer_W2 ++
	ENDWHILE

RETURN


mission_wuzi2_SUB_add_boat_guard_blips:

	// remove blips
	IF NOT IS_CAR_DEAD boat_W2[0]
		REMOVE_BLIP boat_blip_W2[0]
	ENDIF
	IF NOT IS_CAR_DEAD boat_W2[1]
		REMOVE_BLIP boat_blip_W2[1]
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[0]
		REMOVE_BLIP s_boat_blip_W2[0]
	ENDIF
	IF NOT IS_CAR_DEAD s_boat_W2[1]
		REMOVE_BLIP s_boat_blip_W2[1]
	ENDIF

	pointer_W2 = 0
	WHILE pointer_W2 < 4
		REMOVE_BLIP searchlight_blip_W2[pointer_W2]
		pointer_W2 ++
	ENDWHILE

	// add blips
	SET_RADAR_ZOOM 90

	pointer_W2 = 0
	WHILE pointer_W2 < 5
		IF NOT IS_CHAR_DEAD guard_W2[pointer_W2]
			IF NOT DOES_BLIP_EXIST guard_blip_W2[pointer_W2]
				ADD_BLIP_FOR_CHAR guard_W2[pointer_W2] guard_blip_W2[pointer_W2]
				CHANGE_BLIP_DISPLAY guard_blip_W2[pointer_W2] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[pointer_W2] TRUE
			ENDIF
		ENDIF
		pointer_W2 ++
	ENDWHILE

RETURN




// GUARDS ON THE MAIN BOAT ################################################################################################################

LVAR_INT guard_W2[7] guard_blip_W2[8] idle_status_W2[7] patrol_start_W2[4] patrol_stop_W2[4]
LVAR_FLOAT idle_X_W2[4] idle_Y_W2[4] idle_Z_W2[4] idle_heading_W2[4] patrol_X_W2[10] patrol_Y_W2[10] patrol_Z_W2[10] patrol_heading_W2[10]

mission_wuzi2_SUB_start_boat:

	CLEAR_HELP
	CLEAR_PRINTS

	IF knife_status_W2 = 0
		REMOVE_PICKUP stealth_knife_W2	
	ENDIF
	GOSUB mission_wuzi2_SUB_knife_status
	IF NOT knife_status_W2 = 1
	 	CREATE_PICKUP_WITH_AMMO KNIFECUR PICKUP_ONCE 1 -1456.2894 1497.9050 6.7 stealth_knife_W2
	ENDIF

 	REMOVE_BLIP goto_blip_W2
	ADD_BLIP_FOR_COORD -1372.7328 1495.5503 0.8578 goto_blip_W2
				       
	idle_X_W2[0] = -1442.2070     
	idle_Y_W2[0] = 1489.4618
	idle_Z_W2[0] = 6.1016
	idle_heading_W2[0] = 270.0
	idle_X_W2[1] = -1402.3083  
	idle_Y_W2[1] = 1486.7195
	idle_Z_W2[1] = 6.0938
	idle_heading_W2[1] = 301.3607
	idle_X_W2[2] = -1382.6575  	   
	idle_Y_W2[2] =  1480.4457
	idle_Z_W2[2] = 7.5625
	idle_heading_W2[2] = 180.0
	idle_X_W2[3] = -1370.5841  
	idle_Y_W2[3] = 1486.7400
	idle_Z_W2[3] = 10.0313
	idle_heading_W2[3] = 347.1257

	// boat guards	
	CREATE_CHAR PEDTYPE_MISSION2 DNB1 idle_X_W2[0] idle_Y_W2[0] idle_Z_W2[0] guard_W2[0]
	SET_CHAR_HEADING guard_W2[0] idle_heading_W2[0]
	GIVE_WEAPON_TO_CHAR guard_W2[0] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[0] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[0] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[0] guard_blip_W2[0] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[0] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[0] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[0] 80 
	SET_SENSE_RANGE guard_W2[0] 80.0
	idle_status_W2[0] = 0
	
	CREATE_CHAR PEDTYPE_MISSION2 DNB2 idle_X_W2[1] idle_Y_W2[1] idle_Z_W2[1] guard_W2[1]
	SET_CHAR_HEADING guard_W2[1] idle_heading_W2[1]
	GIVE_WEAPON_TO_CHAR guard_W2[1] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[1] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[1] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[1] guard_blip_W2[1] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[1] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[1] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[1] 80 	
	SET_SENSE_RANGE guard_W2[1] 80.0
	idle_status_W2[1] = 0
	
	CREATE_CHAR PEDTYPE_MISSION2 DNB3 idle_X_W2[2] idle_Y_W2[2] idle_Z_W2[2] guard_W2[2]
	SET_CHAR_HEADING guard_W2[2] idle_heading_W2[2]
	GIVE_WEAPON_TO_CHAR guard_W2[2] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[2] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[2] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[2] guard_blip_W2[2] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[2] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[2] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[2] 80 
	SET_SENSE_RANGE guard_W2[2] 80.0
	idle_status_W2[2] = 0

	CREATE_CHAR PEDTYPE_MISSION2 DNB1 idle_X_W2[3] idle_Y_W2[3] idle_Z_W2[3] guard_W2[3]
	SET_CHAR_HEADING guard_W2[3] idle_heading_W2[3]
	GIVE_WEAPON_TO_CHAR guard_W2[3] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[3] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[3] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[3] guard_blip_W2[3] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[3] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[3] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[3] 80 
	SET_SENSE_RANGE guard_W2[3] 80.0
	idle_status_W2[3] = 0

	// patrol
	patrol_X_W2[0] = -1415.1495  
	patrol_Y_W2[0] = 1489.3296
	patrol_Z_W2[0] = 6.1092
	patrol_heading_W2[0] = 274.6376
	patrol_X_W2[1] = -1393.0498  
	patrol_Y_W2[1] = 1490.0347
	patrol_Z_W2[1] = 6.1016
	patrol_heading_W2[1] = 273.1324 

    patrol_X_W2[2] = -1377.6937  
	patrol_Y_W2[2] = 1494.0095
	patrol_Z_W2[2] = 0.8594
	patrol_heading_W2[2] = 268.1749
	patrol_X_W2[3] = -1384.8552  
	patrol_Y_W2[3] = 1493.9037
	patrol_Z_W2[3] = 0.8594
	patrol_heading_W2[3] = 86.0142   
    
	patrol_X_W2[4] = -1433.3796  
	patrol_Y_W2[4] = 1483.4484
	patrol_Z_W2[4] = 0.6250
	patrol_heading_W2[4] = 272.9424
	patrol_X_W2[5] = -1393.0223  
	patrol_Y_W2[5] = 1483.6252
	patrol_Z_W2[5] = 0.6250
	patrol_heading_W2[5] = 266.0800 
	patrol_X_W2[6] = -1392.5673  
	patrol_Y_W2[6] = 1496.2734
	patrol_Z_W2[6] = 0.6312
	patrol_heading_W2[6] = 163.7750
	patrol_X_W2[7] = -1423.0598  
	patrol_Y_W2[7] = 1496.2892
	patrol_Z_W2[7] = 0.6250
	patrol_heading_W2[7] = 134.2810
	patrol_X_W2[8] = -1423.2905  
	patrol_Y_W2[8] = 1491.6144
	patrol_Z_W2[8] = 0.6250
	patrol_heading_W2[8] = 172.1411
	patrol_X_W2[9] = -1433.2311  
	patrol_Y_W2[9] = 1488.7184
	patrol_Z_W2[9] = 0.6250
	patrol_heading_W2[9] = 106.1638 

	patrol_start_W2[0] = 0
	patrol_stop_W2[0] = 1
	pointer2_W2	= patrol_start_W2[0]
	CREATE_CHAR PEDTYPE_MISSION2 DNB3 patrol_X_W2[pointer2_W2] patrol_Y_W2[pointer2_W2] patrol_Z_W2[pointer2_W2] guard_W2[4]
	SET_CHAR_HEADING guard_W2[4] patrol_heading_W2[pointer2_W2]
	GIVE_WEAPON_TO_CHAR guard_W2[4] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[4] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[4] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[4] guard_blip_W2[4] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[4] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[4] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[4] 80 
	SET_SENSE_RANGE guard_W2[4] 80.0
	idle_status_W2[4] = 0

	patrol_start_W2[1] = 2
	patrol_stop_W2[1] = 3
	pointer2_W2	= patrol_start_W2[1]
	CREATE_CHAR PEDTYPE_MISSION2 DNB2 patrol_X_W2[pointer2_W2] patrol_Y_W2[pointer2_W2] patrol_Z_W2[pointer2_W2] guard_W2[5]
	SET_CHAR_HEADING guard_W2[5] patrol_heading_W2[pointer2_W2]
	GIVE_WEAPON_TO_CHAR guard_W2[5] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[5] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[5] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[5] guard_blip_W2[5] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[5] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[5] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[5] 80 
	SET_SENSE_RANGE guard_W2[5] 80.0
	idle_status_W2[5] = 0

	patrol_start_W2[2] = 4
	patrol_stop_W2[2] = 9
	pointer2_W2	= patrol_start_W2[2]
	CREATE_CHAR PEDTYPE_MISSION2 DNB1 patrol_X_W2[pointer2_W2] patrol_Y_W2[pointer2_W2] patrol_Z_W2[pointer2_W2] guard_W2[6]
	SET_CHAR_HEADING guard_W2[6] patrol_heading_W2[pointer2_W2]
	GIVE_WEAPON_TO_CHAR guard_W2[6] WEAPONTYPE_KNIFE 1
	SET_CURRENT_CHAR_WEAPON guard_W2[6] WEAPONTYPE_KNIFE
	SET_CHAR_DECISION_MAKER guard_W2[6] stealth_dm_W2
	ADD_BLIP_FOR_CHAR guard_W2[6] guard_blip_W2[6] 
	CHANGE_BLIP_DISPLAY guard_blip_W2[6] BLIP_ONLY
	SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR guard_blip_W2[6] TRUE
	SET_CHAR_SHOOT_RATE guard_W2[6] 80 
	SET_SENSE_RANGE guard_W2[6] 80.0
	idle_status_W2[6] = 0


	PRINT_NOW WZI2_35 7000 1

	p_stealth_status_W2 = 0

	pointer_W2 = 0
	WHILE pointer_W2 < 4
		IF NOT IS_CHAR_DEAD guard_W2[pointer_W2]
			SET_CHAR_DECISION_MAKER guard_W2[pointer_W2] stealth_dm_W2
		ENDIF 
		pointer_W2 ++
	ENDWHILE

	stealth_active_W2 = 1
RETURN


LVAR_INT p_stealth_status_W2 g_stealth_status_W2[7] t_stealth_status_W2[2] guard_active_W2
  
mission_wuzi2_SUB_guard_stealth_status:

	pointer_W2 = 0
	
	t_stealth_status_W2[0] = 0
	t_stealth_status_W2[1] = 0
	t_stealth_status_W2[1] = 0

	WHILE pointer_W2 < 7

		IF NOT IS_CHAR_DEAD guard_W2[pointer_W2]

			SWITCH g_stealth_status_W2[pointer_W2]

				CASE 0
					IF IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SOUND_QUIET
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
						IF NOT IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE
							g_stealth_status_W2[pointer_W2] = 1
							idle_status_W2[pointer_W2] = -1
							t_stealth_status_W2[1] ++
						ELSE
				            g_stealth_status_W2[pointer_W2] = 2
							idle_status_W2[pointer_W2] = -1
							t_stealth_status_W2[1] ++
						ENDIF
					ELSE
						IF IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE
						OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SHOT_FIRED
						OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_DAMAGE
						OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_GUN_AIMED_AT
							g_stealth_status_W2[pointer_W2] = 2
							idle_status_W2[pointer_W2] = -1
							t_stealth_status_W2[1] ++
						ELSE
							t_stealth_status_W2[0] ++
						ENDIF
					ENDIF
				BREAK

				CASE 1 										
					IF IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE
			        OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SHOT_FIRED
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_DAMAGE
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_GUN_AIMED_AT
						g_stealth_status_W2[pointer_W2] = 2
						idle_status_W2[pointer_W2] = -1
						guard_active_W2 = pointer_W2
						t_stealth_status_W2[1] ++
					ELSE	
						IF IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
						OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SOUND_QUIET
							t_stealth_status_W2[1] ++
						ELSE
							g_stealth_status_W2[pointer_W2] = 0
							idle_status_W2[pointer_W2] = 0
							t_stealth_status_W2[0] ++
						ENDIF
					ENDIF
				BREAK

				CASE 2
					IF IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE
			        OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
			        OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SHOT_FIRED
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_DAMAGE
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_GUN_AIMED_AT
					OR IS_CHAR_RESPONDING_TO_EVENT guard_W2[pointer_W2] EVENT_SOUND_QUIET
						guard_active_W2 = pointer_W2						
						t_stealth_status_W2[1] ++
					ELSE	
						g_stealth_status_W2[pointer_W2] = 0
						idle_status_W2[pointer_W2] = 0
						t_stealth_status_W2[0] ++					
					ENDIF
				BREAK

			ENDSWITCH
		ELSE
			IF NOT g_stealth_status_W2[pointer_W2] = -1 
				REMOVE_BLIP guard_blip_W2[pointer_W2]
				g_stealth_status_W2[pointer_W2] = -1
			ENDIF 
		ENDIF

		pointer_W2 ++

	ENDWHILE

	SWITCH p_stealth_status_W2
		
		CASE 0
			IF t_stealth_status_W2[1] > 0
				p_stealth_status_W2 = 2
				//PRINT_NOW WZI2_22 3000 1 //GUARD: Theres an intruder on the boat.				 
			ELSE
				IF t_stealth_status_W2[1] > 0
					p_stealth_status_W2 = 1
					//PRINT_NOW WZI2_20 3000 1 //GUARD: What was that?
				ENDIF
			ENDIF	
		BREAK
		
		CASE 1
			IF t_stealth_status_W2[1] > 0
				p_stealth_status_W2 = 2
				//PRINT_NOW WZI2_22 3000 1 //GUARD: Theres an intruder on the boat.				 
			ELSE
				IF t_stealth_status_W2[1] = 0
					p_stealth_status_W2 = 0
					//PRINT_NOW WZI2_21 3000 1 //GUARD: Hmm..must have been my imagination.
				ENDIF
			ENDIF	
		BREAK
	
		CASE 2
			IF t_stealth_status_W2[1] = 0
			AND t_stealth_status_W2[1] = 0 
				p_stealth_status_W2 = 0
				IF NOT IS_CHAR_DEAD guard_W2[guard_active_W2]
				AND NOT IS_CHAR_DEAD scplayer 
					//PRINT_NOW WZI2_23 3000 1 //GUARD: Shit i've lost him.
				ENDIF
			ENDIF	
		BREAK
	
	ENDSWITCH 

RETURN

// guard idle controllers

// stand still guard
mission_wuzi2_SUB_idle_guards_still:
	
	pointer_W2 = 0

	WHILE pointer_W2 < 4

		IF NOT idle_status_W2[pointer_W2] = -1 // currently in stealth decision maker
			IF NOT IS_CHAR_DEAD guard_W2[pointer_W2]

				SWITCH idle_status_W2[pointer_W2]

					CASE 0 // go to idle stand point
						OPEN_SEQUENCE_TASK sequence_W2
							TASK_GO_TO_COORD_ANY_MEANS -1 idle_X_W2[pointer_W2] idle_Y_W2[pointer_W2] idle_Z_W2[pointer_W2] PEDMOVE_WALK -2 	
							TASK_ACHIEVE_HEADING -1 idle_heading_W2[pointer_W2] 
							TASK_STAND_STILL -1 5000
						CLOSE_SEQUENCE_TASK sequence_W2	
						
						PERFORM_SEQUENCE_TASK guard_W2[pointer_W2] sequence_W2
						CLEAR_SEQUENCE_TASK sequence_W2

						idle_status_W2[pointer_W2] ++
					BREAK
					
					CASE 1 // check sequence to see if hes arrived
						GET_SEQUENCE_PROGRESS guard_W2[pointer_W2] progress_W2
						IF progress_W2 = 2
							IF LOCATE_CHAR_ON_FOOT_2D guard_W2[pointer_W2] idle_X_W2[pointer_W2] idle_Y_W2[pointer_W2] 2.0 2.0 FALSE
								idle_status_W2[pointer_W2] ++
							ELSE
								CLEAR_CHAR_TASKS guard_W2[pointer_W2]
								idle_status_W2[pointer_W2] --
							ENDIF
						ENDIF  	
					BREAK
					
					CASE 2 // do some idle stuff

						GENERATE_RANDOM_INT_IN_RANGE 7000 15000 random_W2
						GENERATE_RANDOM_INT_IN_RANGE 0 500 random2_W2

						CLEAR_CHAR_TASKS guard_W2[pointer_W2] 
						OPEN_SEQUENCE_TASK sequence_W2
							TASK_STAND_STILL -1 random_W2 	
							IF random2_W2 < 250
								TASK_LOOK_ABOUT -1 8000
							ELSE
								TASK_SCRATCH_HEAD -1
							ENDIF 
							TASK_STAND_STILL -1 5000
						CLOSE_SEQUENCE_TASK sequence_W2	
						
						PERFORM_SEQUENCE_TASK guard_W2[pointer_W2] sequence_W2
						CLEAR_SEQUENCE_TASK sequence_W2

						idle_status_W2[pointer_W2] ++
					BREAK
					
					CASE 3
						GET_SEQUENCE_PROGRESS guard_W2[pointer_W2] progress_W2
						IF progress_W2 = 2
							CLEAR_CHAR_TASKS guard_W2[pointer_W2]
							idle_status_W2[pointer_W2] --
						ENDIF
					BREAK

					CASE 4
					BREAK

				ENDSWITCH
			ENDIF
		ENDIF

		pointer_W2 ++
	ENDWHILE

RETURN

// patrol guard
LVAR_INT patrol_progress_W2
mission_wuzi2_SUB_idle_guards_patrol:
	
	pointer_W2 = 4
	pointer2_W2 = 0

	WHILE pointer_W2 < 7

		IF NOT idle_status_W2[pointer_W2] = -1 // currently in stealth decision maker
			IF NOT IS_CHAR_DEAD guard_W2[pointer_W2]

				pointer3_W2 = patrol_start_W2[pointer2_W2]
				pointer4_W2 = patrol_stop_W2[pointer2_W2]

				SWITCH idle_status_W2[pointer_W2]

					CASE 0 // go to idle stand point
						OPEN_SEQUENCE_TASK sequence_W2
							
							WHILE pointer3_W2 <= pointer4_W2
								TASK_GO_TO_COORD_ANY_MEANS -1 patrol_X_W2[pointer3_W2] patrol_Y_W2[pointer3_W2] patrol_Z_W2[pointer3_W2] PEDMOVE_WALK -2
								pointer3_W2 ++
							ENDWHILE
							
							IF pointer_W2 = 6
								SET_SEQUENCE_TO_REPEAT sequence_W2 1   
								idle_status_W2[pointer_W2] = 90	
							ELSE
								TASK_ACHIEVE_HEADING -1 patrol_heading_W2[pointer4_W2]
								TASK_STAND_STILL -1 5000
								TASK_STAND_STILL -1 5000
								idle_status_W2[pointer_W2] ++
							ENDIF

						CLOSE_SEQUENCE_TASK sequence_W2	
						
						PERFORM_SEQUENCE_TASK guard_W2[pointer_W2] sequence_W2
						CLEAR_SEQUENCE_TASK sequence_W2
					BREAK
					
					CASE 1 // check sequence to see if hes arrived
						patrol_progress_W2 = pointer4_W2 - pointer3_W2
						patrol_progress_W2 += 3
						GET_SEQUENCE_PROGRESS guard_W2[pointer_W2] progress_W2
						IF progress_W2 = patrol_progress_W2
							
							IF pointer_W2 = 4
								// do greet?
							ENDIF

							idle_status_W2[pointer_W2] ++						
						ENDIF  	
					BREAK
					
					CASE 2 // do some idle stuff
						OPEN_SEQUENCE_TASK sequence_W2
							
							WHILE pointer4_W2 >= pointer3_W2
								TASK_GO_TO_COORD_ANY_MEANS -1 patrol_X_W2[pointer4_W2] patrol_Y_W2[pointer4_W2] patrol_Z_W2[pointer4_W2] PEDMOVE_WALK -2
								pointer4_W2 --
							ENDWHILE
							TASK_ACHIEVE_HEADING -1 patrol_heading_W2[pointer3_W2]
							TASK_STAND_STILL -1 5000
							TASK_STAND_STILL -1 5000
							idle_status_W2[pointer_W2] ++

						CLOSE_SEQUENCE_TASK sequence_W2	
						
						PERFORM_SEQUENCE_TASK guard_W2[pointer_W2] sequence_W2
						CLEAR_SEQUENCE_TASK sequence_W2
					BREAK
					
					CASE 3
						patrol_progress_W2 = pointer4_W2 - pointer3_W2
						patrol_progress_W2 += 3
						GET_SEQUENCE_PROGRESS guard_W2[pointer_W2] progress_W2
						IF progress_W2 = patrol_progress_W2
							idle_status_W2[pointer_W2] = 0						
						ENDIF 
					BREAK

					CASE 4
					BREAK

				ENDSWITCH
			ENDIF
		ENDIF

		pointer_W2 ++
		pointer2_W2 ++

	ENDWHILE

RETURN



LVAR_INT weapontype_W2[13] weapon_ammo_W2[13] weapon_model_W2[13] current_weapon_W2 weapon_slot_W2 weapon_totals_W2 weapon_loaded_W2
mission_wuzi2_SUB_store_players_weapons: 

	pointer_W2 = 0
	weapon_slot_W2 = 1

	GET_CURRENT_CHAR_WEAPON scplayer current_weapon_W2  
	
	WHILE pointer_W2 < 13
		GET_CHAR_WEAPON_IN_SLOT scplayer weapon_slot_W2 weapontype_W2[pointer_W2] weapon_ammo_W2[pointer_W2] weapon_model_W2[pointer_W2]
		pointer_W2 ++
		weapon_slot_W2 ++
	ENDWHILE

RETURN



mission_wuzi2_SUB_return_players_weapons:

	// request any models for weapons the player had
	pointer_W2 = 0
	WHILE pointer_W2 < 13
		IF NOT weapontype_W2[pointer_W2] = 0 //WEAPONTYPE_UNARMED
			REQUEST_MODEL weapon_model_W2[pointer_W2]
			weapon_totals_W2 ++
		ENDIF
		pointer_W2 ++
	ENDWHILE

	// wait for the weapon models to load
	WHILE NOT weapon_loaded_W2 = weapon_totals_W2 
		WAIT 0
		weapon_loaded_W2 = 0
		pointer_W2 = 0
		WHILE pointer_W2 < 13 
			IF NOT weapontype_W2[pointer_W2] = 0 //WEAPONTYPE_UNARMED
				IF HAS_MODEL_LOADED weapon_model_W2[pointer_W2]
					weapon_loaded_W2 ++
				ENDIF
			ENDIF				
			pointer_W2 ++
		ENDWHILE	
	ENDWHILE	

	REMOVE_ALL_CHAR_WEAPONS scplayer

	pointer_W2 = 0
	weapon_slot_W2 = 1
	WHILE pointer_W2 < 13
		GIVE_WEAPON_TO_CHAR scplayer weapontype_W2[pointer_W2] weapon_ammo_W2[pointer_W2]

		pointer_W2 ++
		weapon_slot_W2 ++
	ENDWHILE

	SET_CURRENT_CHAR_WEAPON scplayer current_weapon_W2

	// mark the models as no longer needed so they dont stay in script memory?
	pointer_W2 = 0
	WHILE pointer_W2 < 13
		IF NOT weapontype_W2[pointer_W2] = 0 //WEAPONTYPE_UNARMED
			MARK_MODEL_AS_NO_LONGER_NEEDED weapon_model_W2[pointer_W2]
		ENDIF
		pointer_W2 ++
	ENDWHILE

RETURN



// MISSION FAILED ########################################################################################### MISSION FAILED

mission_wuzi2_FAILED:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	IF fail_text_flag_W2 = 1
		PRINT_NOW $fail_text_W2 5000 1
	ENDIF

RETURN						


// MISSION PASSED ########################################################################################### MISSION PASSED

mission_wuzi2_PASSED:

	//GOSUB mission_wuzi2_SUB_return_players_weapons
	
	flag_wuzi_mission_counter++
	REGISTER_MISSION_PASSED ( WUZI_2 ) //Used in the stats
	PLAYER_MADE_PROGRESS 1

	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 11000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 11000//amount of cash
	AWARD_PLAYER_MISSION_RESPECT 25//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

RETURN
		

// MISSION CLEANUP ########################################################################################### MISSION CLEANUP

mission_wuzi2_CLEANUP:

	IF swim_stamina_check = 0

		SET_MAX_WANTED_LEVEL max_wanted_level_W2
		
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
			SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
		ENDIF

		START_NEW_SCRIPT terminate_audio_controller

		REMOVE_BLIP	stealth_knife_blip_W2
		REMOVE_PICKUP stealth_knife_W2 

		GET_GAME_TIMER timer_mobile_start
		
		SWITCH_ROADS_BACK_TO_ORIGINAL -1328.9994 1239.9972 -5.0 -1655.8684 1638.7549 10.0
		
		MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
		MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
		MARK_MODEL_AS_NO_LONGER_NEEDED DNB3

		// cars
		MARK_MODEL_AS_NO_LONGER_NEEDED TROPIC

		// weapons
		MARK_MODEL_AS_NO_LONGER_NEEDED AK47
		MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR

		// objects
		MARK_MODEL_AS_NO_LONGER_NEEDED a51_spotbase
		MARK_MODEL_AS_NO_LONGER_NEEDED a51_spothousing
		MARK_MODEL_AS_NO_LONGER_NEEDED a51_spotbulb		 

		REMOVE_ANIMATION DAM_JUMP	
		 
		REMOVE_BLIP goto_blip_W2

		
		pointer_W2 = 0
		WHILE pointer_W2 < 5
			REMOVE_BLIP guard_blip_W2[pointer_W2]
			MARK_CHAR_AS_NO_LONGER_NEEDED guard_W2[pointer_W2]	
			pointer_W2 ++
		ENDWHILE

		pointer_W2 = 0
		WHILE pointer_W2 < 4
			REMOVE_BLIP searchlight_blip_W2[pointer_W2]

			MARK_OBJECT_AS_NO_LONGER_NEEDED searchlight_base_W2[pointer_W2]
	 		MARK_OBJECT_AS_NO_LONGER_NEEDED searchlight_housing_W2[pointer_W2]
			MARK_OBJECT_AS_NO_LONGER_NEEDED searchlight_bulb_W2[pointer_W2]

			pointer_W2 ++
		ENDWHILE

		pointer_W2 = 0
		WHILE pointer_W2 < 6
			MARK_CHAR_AS_NO_LONGER_NEEDED boat_guards_W2[pointer_W2]
			MARK_CHAR_AS_NO_LONGER_NEEDED s_boat_guard_W2[pointer_W2]
			pointer_W2 ++
		ENDWHILE		
		
		pointer_W2 = 0
		WHILE pointer_W2 < 2
			REMOVE_BLIP boat_blip_W2[pointer_W2]
			MARK_CHAR_AS_NO_LONGER_NEEDED boat_driver_W2[pointer_W2]
			MARK_CAR_AS_NO_LONGER_NEEDED boat_W2[pointer_W2]
			
			REMOVE_BLIP s_boat_blip_W2[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED s_boat_guard_W2[pointer_W2]
			MARK_CAR_AS_NO_LONGER_NEEDED s_boat_W2[pointer_W2]

			pointer_W2 ++
		ENDWHILE

		RELEASE_WEATHER
	ELSE
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 OFF
		ENDIF
		SET_CHAR_COORDINATES scplayer -2154.8154 641.3157 51.3594 
		SET_CHAR_HEADING scplayer 268.4865
		LOAD_SCENE -2154.8154 641.3157 51.3594
		
		TIMERA = 0
		WHILE TIMERA < 500
			WAIT 0
		ENDWHILE
		
		DO_FADE 0 FADE_IN
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 ON
		ENDIF
		
		PRINT_NOW WZI2_60 8000 1
	ENDIF


	
	flag_player_on_mission = 0

	MISSION_HAS_FINISHED	
RETURN

}

/*
[WZI2_8:WUZI2]
~s~Swim to the ~y~tanker~s~ and get aboard.

[WZI2_30:WUZI2]
~s~Press the ~t~ button to plant the bug.
[WZI2_31:WUZI2]
~s~Lose the attention of the guards before you plant the bug.
[WZI2_32:WUZI2]
~s~Well done you have planted the bug, get off the tanker and back to the ~y~docks~s~.
[WZI2_35:WUZI2]
~s~Sneak to the back of the tanker and plant the ~y~bug~s~, be as quiet as possible to avoid the attention of the ~r~guards~s~ onboard.

[WZI2_36:WUZI2]
You have been spotted by one of the ~r~guards~w~ dive underwater to lose their attention.
[WZI2_37:WUZI2]
If the ~r~guards~w~ spot you swimming fast or you dont dive quick enough they will start shooting.
[WZI2_38:WUZI2]
Once you have lost the attention of the ~r~guards~w~ return to the surface to get your breath back.
[WZI2_39:WUZI2]
When you return to the surface the ~r~guards~w~ will not spot you until you move.
[WZI2_42:WUZI2]
If one of the searchlights spot you on the surface of the water the ~r~guards~w~ will shoot at you.

[WZI2_40:WUZI2]
The main route across the water is guarded by two patrol boats, if they spot you dive underwater to lose their attention.
[WZI2_41:WUZI2]
Don't swim on the surface of the water or you will be spotted.

[WZI2_C0:WUZI2]
Press the ~q~ button to jump and climb out of the water.
*/


