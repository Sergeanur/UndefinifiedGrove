MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Crash 2 *****************************************
// *************************************** Doberman ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME crash4

// Mission start stuff

GOSUB mission_start_crash4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_crash4_failed
ENDIF

GOSUB mission_cleanup_crash4

MISSION_END

{
 
// Variables for mission 

// bdup character
LVAR_INT enemy_crash4 enemy_blip_crash4 flag_enemy_taunt_crash4 enemy_created_crash4 enemy_dead_crash4 enemy_out_car_crash4

LVAR_INT enemy2_crash4 car_crash4 enemy2_out_car_crash4

// general stuff
LVAR_INT tough_decisionmaker_crash4 watched_cutscene1_crash4

// area blips
LVAR_INT area_blip_crash4 flag_zone_blip_on_crash4

// gang war stuff
LVAR_INT gang_strenth1_crash4

// stuff for mobile phone calls
LVAR_INT task_status_crash4 guys_shaking_crash4

LVAR_INT flag_gang_help_crash4

// emmetts house for weapons
LVAR_INT emmetts_blip_crash4

LVAR_INT weapon_pickup_blip_crash4

// mini cuts 
LVAR_INT cut_guy1_crash4 cut_guy2_crash4 cut_guy3_crash4

LVAR_FLOAT return_animation_time_guy2_crash4 return_animation_time_guy3_crash4

LVAR_INT flag_guy2_finished_crash4 flag_guy3_finished_crash4 cigar_crash4 flag_watched_end_cut2_crash4

LVAR_INT created_cutscene_guys1_crash4 created_cutscene_guys2_crash4

LVAR_INT watched_baddie_cut_crash4

LVAR_INT flag_had_gang_war_message_crash4

LVAR_INT flag_had_gang_war_message2_crash4

LVAR_INT flag_had_flag_message_crash4

// NEW Ammunation stuff
LVAR_INT flag_ammu_help_crash4 dummy_car_crash4 flag_gun_message_crash4

// gang stuff
LVAR_INT stored_nmex_strength_crash4 stored_smex_strength_crash4

// new start gang war message
LVAR_INT flag_had_kill_message_crash2

// player gang strenth
LVAR_INT player_gang_strenth_crash4

LVAR_INT stored_car_crash4

// **************************************** Mission Start **********************************

mission_start_crash4:

flag_player_on_mission = 1

flag_on_doberman_mission = 1

// phone message stuff
guys_shaking_crash4 = 0

flag_gang_help_crash4 = 0

// flat stuff
flag_zone_blip_on_crash4 = 0

// enemy taunt stuff
flag_enemy_taunt_crash4 = 0

enemy_created_crash4 = 0

enemy_dead_crash4 = 0

enemy2_out_car_crash4 = 0

enemy2_out_car_crash4 = 0

// general stuff
watched_cutscene1_crash4 = 0

return_animation_time_guy2_crash4 = 0.0

return_animation_time_guy3_crash4 = 0.0

flag_guy3_finished_crash4 = 0

flag_guy2_finished_crash4 = 0

flag_watched_end_cut2_crash4 = 0

created_cutscene_guys1_crash4 = 0

created_cutscene_guys2_crash4 = 0

watched_baddie_cut_crash4 = 0

flag_had_gang_war_message_crash4 = 0

flag_had_gang_war_message2_crash4 = 0

flag_had_flag_message_crash4 = 0

flag_ammu_help_crash4 = 0

flag_gun_message_crash4 = 0

flag_bought_gun_for_doberman = 0

stored_nmex_strength_crash4 = 0
stored_smex_strength_crash4 = 0

// new gangwar message
flag_had_kill_message_crash2 = 0

// player gang strenth
player_gang_strenth_crash4 = 0

// mobile and audio stuff
//crash4_index = 0 
//crash4_audio_is_playing = 0 
//crash4_cutscene_flag = 0 
//crash4_chat_switch = 0

REGISTER_MISSION_GIVEN

WAIT 0

// ********************** NO ANIMATED CUTSCENE IN THIS MISSION ************************************

CLEAR_THIS_PRINT_BIG_NOW 1

WAIT 1000

LOAD_MISSION_TEXT CRASH2

SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40
SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0

GET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX stored_nmex_strength_crash4
GET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX stored_smex_strength_crash4

SET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX 0
SET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX 0

SET_EVERYONE_IGNORE_PLAYER player1 ON
SET_POLICE_IGNORE_PLAYER player1 ON
   
// Decision Makers
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_decisionmaker_crash4

REQUEST_MODEL BALLAS1
REQUEST_MODEL BALLAS2
REQUEST_MODEL TEC9
REQUEST_ANIMATION GANGS
REQUEST_ANIMATION SMOKING
REQUEST_MODEL CIGAR
REQUEST_MODEL FAM2
REQUEST_MODEL FAM3

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED BALLAS2
OR NOT HAS_MODEL_LOADED TEC9
OR NOT HAS_MODEL_LOADED BALLAS1
OR NOT HAS_ANIMATION_LOADED GANGS

	WAIT 0

ENDWHILE

WHILE NOT HAS_ANIMATION_LOADED SMOKING
OR NOT HAS_MODEL_LOADED CIGAR
OR NOT HAS_MODEL_LOADED FAM2
OR NOT HAS_MODEL_LOADED FAM3

	WAIT 0

ENDWHILE

// ***************************************** CUTSCENE SHOWING AMMUNATION ************************************
SET_PED_DENSITY_MULTIPLIER 0.0

CLEAR_AREA 1363.6891 -1280.4408 12.5469 1.0 TRUE 
SET_CHAR_COORDINATES scplayer 1363.6891 -1280.4408 12.5469
LOAD_SCENE 1363.6891 -1280.4408 12.5469  
SET_CHAR_HEADING scplayer 90.0
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
MAKE_PLAYER_GANG_DISAPPEAR

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

SET_EVERYONE_IGNORE_PLAYER player1 ON
SET_POLICE_IGNORE_PLAYER player1 ON

CLEAR_AREA 1350.1770 -1264.9207 14.7166 1.0 TRUE 
SET_FIXED_CAMERA_POSITION 1350.1770 -1264.9207 14.7166 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 1350.9867 -1265.4988 14.8167 JUMP_CUT

SET_FADING_COLOUR 0 0 0
DO_FADE 1500 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0
	
ENDWHILE

PRINT_NOW (AMUHLP) 10000 1 //"This is AmmuNation"

SKIP_CUTSCENE_START

TIMERA = 0

WHILE TIMERA < 5000

	WAIT 0
	
ENDWHILE

SKIP_CUTSCENE_END


// ******************************************************* CUTSCENE SHOWING TERRITORY ***********************

CLEAR_THIS_PRINT (AMUHLP) 

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

REQUEST_MODEL Laejeffersalpha
REQUEST_MODEL LaeLODpark02
REQUEST_MODEL Laemacpark02

WHILE NOT HAS_MODEL_LOADED Laejeffersalpha
OR NOT HAS_MODEL_LOADED LaeLODpark02
OR NOT HAS_MODEL_LOADED Laemacpark02 

	WAIT 0

ENDWHILE

LOAD_ALL_MODELS_NOW

SWITCH_WIDESCREEN ON

IF played_cut1_before_crash4 = 1 
	SKIP_CUTSCENE_START
ENDIF

SET_CHAR_COORDINATES scplayer 1859.931 -1137.139 22.946
//LOAD_SCENE 1859.931 -1137.139 22.946

LOAD_SCENE_IN_DIRECTION 1859.931 -1137.139 22.946 89.9 

IF created_cutscene_guys1_crash4 = 0
	CLEAR_AREA 1924.632 -1126.623 24.135 30.0 TRUE 
	CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 1924.632 -1126.623 24.135 cut_guy1_crash4 // leaning against wall smoking
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cut_guy1_crash4 TRUE 
	SET_CHAR_HEADING cut_guy1_crash4 180.0
	CREATE_OBJECT cigar 1924.632 -1126.623 24.135 cigar_crash4
	TASK_PICK_UP_OBJECT cut_guy1_crash4 cigar_crash4 0.04 0.1 -0.02 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  cut_guy1_crash4 M_smklean_loop SMOKING 4.0 TRUE FALSE FALSE FALSE -1

	CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 1921.157 -1127.438 24.048 cut_guy2_crash4
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cut_guy2_crash4 TRUE
	SET_CHAR_HEADING cut_guy2_crash4 270.0
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  cut_guy2_crash4 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1

	CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 1922.157 -1127.438 24.048 cut_guy3_crash4
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cut_guy3_crash4 TRUE
	SET_CHAR_HEADING cut_guy3_crash4 90.0
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  cut_guy3_crash4 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
	created_cutscene_guys1_crash4 = 1
ENDIF

SET_FIXED_CAMERA_POSITION 1858.9087 -1136.1016 26.7903 0.0 0.0 0.0  
POINT_CAMERA_AT_POINT 1859.8669 -1136.3796 26.7250 JUMP_CUT

SET_PED_DENSITY_MULTIPLIER 0.0

SET_FADING_COLOUR 0 0 0
DO_FADE 2000 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

PRINT_NOW (CM2_10 ) 20000 1 //"This is Glen Park, which is Ballas territory, it is marked on the radar with purple."

TIMERA = 0

WHILE TIMERA < 10000
	WAIT 0
ENDWHILE

CLEAR_THIS_PRINT (CM2_10)

SET_FIXED_CAMERA_POSITION 1916.7703 -1130.4788 26.9978 0.0 0.0 0.0  
POINT_CAMERA_AT_POINT 1917.7384 -1130.2701 26.8593 JUMP_CUT

PRINT_NOW (CM2_11) 20000 1 //"You must take out the enemy gang to gain this territory as your own."

TIMERA = 0

WHILE TIMERA < 10000

	WAIT 0

	IF guys_shaking_crash4 = 0

		IF TIMERA > 7000

			IF NOT IS_CHAR_DEAD cut_guy2_crash4

				IF NOT IS_CHAR_DEAD cut_guy3_crash4
					TASK_PLAY_ANIM_NON_INTERRUPTABLE  cut_guy2_crash4 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
					return_animation_time_guy2_crash4 = 0.0
				
					TASK_PLAY_ANIM_NON_INTERRUPTABLE  cut_guy3_crash4 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
					return_animation_time_guy3_crash4 = 0.0

					guys_shaking_crash4 = 1

				ENDIF

			ENDIF

		ENDIF

	ENDIF
	
ENDWHILE

CLEAR_THIS_PRINT (CM2_11)

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

MARK_MODEL_AS_NO_LONGER_NEEDED Laejeffersalpha
MARK_MODEL_AS_NO_LONGER_NEEDED LaeLODpark02
MARK_MODEL_AS_NO_LONGER_NEEDED Laemacpark02

CLEAR_THIS_PRINT (CM2_2)

REQUEST_COLLISION 1363.6891 -1280.4408

CLEAR_AREA 1363.6891 -1280.4408 12.5469 1.0 TRUE 
SET_CHAR_COORDINATES scplayer 1363.6891 -1280.4408 12.5469 
SET_CHAR_HEADING scplayer 90.0

//LOAD_SCENE 1363.6891 -1280.4408 12.5469

LOAD_SCENE_IN_DIRECTION 1363.6891 -1280.4408 12.5469 90.0 

REMOVE_ANIMATION GANGS
REMOVE_ANIMATION SMOKING

IF created_cutscene_guys1_crash4 = 1
	DELETE_CHAR cut_guy1_crash4
	DELETE_CHAR cut_guy2_crash4
	DELETE_CHAR cut_guy3_crash4
	DELETE_OBJECT cigar_crash4
ENDIF

MARK_MODEL_AS_NO_LONGER_NEEDED cigar

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SWITCH_WIDESCREEN OFF
MAKE_PLAYER_GANG_REAPPEAR

SET_PED_DENSITY_MULTIPLIER 1.0

SET_FADING_COLOUR 0 0 0
DO_FADE 1500 FADE_IN

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_POLICE_IGNORE_PLAYER player1 OFF

IF played_cut1_before_crash4 = 1

	watched_cutscene1_crash4 = 1
	
	SKIP_CUTSCENE_END
	IF watched_cutscene1_crash4 = 0

		CLEAR_PRINTS

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS

			WAIT 0 

		ENDWHILE

		SET_PED_DENSITY_MULTIPLIER 1.0

		REQUEST_COLLISION 1363.6891 -1280.4408

		CLEAR_AREA 1363.6891 -1280.4408 12.5469 1.0 TRUE 
		SET_CHAR_COORDINATES scplayer 1363.6891 -1280.4408 12.5469 
		SET_CHAR_HEADING scplayer 90.0
				
//		LOAD_SCENE 1363.6891 -1280.4408 12.5469
		
		LOAD_SCENE_IN_DIRECTION 1363.6891 -1280.4408 12.5469 90.0 
							 
		REMOVE_ANIMATION GANGS
		REMOVE_ANIMATION SMOKING

		MARK_MODEL_AS_NO_LONGER_NEEDED Laejeffersalpha
		MARK_MODEL_AS_NO_LONGER_NEEDED LaeLODpark02
		MARK_MODEL_AS_NO_LONGER_NEEDED Laemacpark02

		IF created_cutscene_guys1_crash4 = 1
			DELETE_CHAR cut_guy1_crash4
			DELETE_CHAR cut_guy2_crash4
			DELETE_CHAR cut_guy3_crash4
			DELETE_OBJECT cigar_crash4
		ENDIF

		MARK_MODEL_AS_NO_LONGER_NEEDED CIGAR

		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		MAKE_PLAYER_GANG_REAPPEAR

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1500 FADE_IN

		WHILE GET_FADING_STATUS

			WAIT 0

		ENDWHILE

		SET_CAMERA_BEHIND_PLAYER
		SET_EVERYONE_IGNORE_PLAYER player1 OFF
		SET_POLICE_IGNORE_PLAYER player1 OFF

	ENDIF
	
ENDIF

played_cut1_before_crash4 = 1

// ************************************** END OF CUT SCENE ONE ************************************

// ******************* PLAYER NOW TOLD TO GO INTO AMMUNATION HAD BUY A GUN ************************

//PRINT_NOW (CM2_22) 8000 1 //"Enter the shop and buy a gun."
ADD_SPRITE_BLIP_FOR_COORD 1362.895 -1281.008 12.586 RADAR_SPRITE_GUN weapon_pickup_blip_crash4

SWITCH_ENTRY_EXIT ammun1 TRUE

// switches on the gang war stuff
SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40
SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0

SET_ONLY_CREATE_GANG_MEMBERS TRUE

SET_GANG_WARS_TRAINING_MISSION TRUE
SET_ZONE_FOR_GANG_WARS_TRAINING GLN1

SET_WANTED_MULTIPLIER 0.5

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

SET_GANG_WARS_ACTIVE TRUE  
flag_gang_war_active = 1

PRINT_NOW (CM2_3) 8000 1 //"Goto Ballas territory and start and gang war. 
ADD_SPRITE_BLIP_FOR_COORD 1932.351 -1189.647 19.063 RADAR_SPRITE_GANG_G area_blip_crash4

WHILE NOT IS_PLAYER_IN_INFO_ZONE player1 GLN1

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_crash4_passed
	ENDIF

	IF flag_had_flag_message_crash4 = 0

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2444.8950 -1981.5243 13.933 880.0 880.0 100.0 FALSE

			IF IS_STRING_EMPTY $shop_name
	 			PRINT_HELP (CM2_18) //"Ballas territory is marked in purple on the radar."
				TIMERB = 0
				flag_had_flag_message_crash4 = 1
			ENDIF

		ENDIF

	ENDIF

	IF flag_had_flag_message_crash4 = 1

		IF IS_STRING_EMPTY $shop_name
	
			IF TIMERB > 5000
				PRINT_HELP (CM2_19) //"The radar blip indicates the territory to attack, it will stay on the radar until you own the territory."
				flag_had_flag_message_crash4 = 2
			ENDIF

		ENDIF
		
	ENDIF 

	GET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT gang_strenth1_crash4
		
ENDWHILE

REMOVE_BLIP weapon_pickup_blip_crash4

PRINT_NOW (CM2_5) 8000 1 //"Kill enemy gang members while on foot to start a gang war."

TIMERA = 0

// waiting for the player to start a gang war
WHILE NOT IS_GANG_WAR_GOING_ON

	WAIT 0

	IF NOT IS_PLAYER_IN_INFO_ZONE player1 GLN1
		
		IF flag_zone_blip_on_crash4 = 0
		   //	ADD_SPRITE_BLIP_FOR_COORD 1932.351 -1189.647 19.063 RADAR_SPRITE_ENEMYATTACK area_blip_crash4
			PRINT_NOW (CM2_6) 8000 1 //"Get back to Glen Park!"
			flag_zone_blip_on_crash4 = 1
		ENDIF

	ELSE

		IF flag_zone_blip_on_crash4 = 1
//			REMOVE_BLIP area_blip_crash4
			CLEAR_THIS_PRINT (CM2_6)
			PRINT_NOW (CM2_5) 8000 1 //"Kill enemy gang members while on foot to start a gang war."  
			flag_zone_blip_on_crash4 = 0
		ENDIF

	ENDIF

	IF flag_had_kill_message_crash2 = 0

		IF TIMERA >= 60000		
			PRINT_NOW (CM2_5) 8000 1 //"Kill enemy gang members while on foot to start a gang war."
			flag_had_kill_message_crash2 = 1
		ENDIF

	ENDIF
	
	IF flag_had_kill_message_crash2 = 1
		
		IF TIMERA >= 70000
			TIMERA = 0
			flag_had_kill_message_crash2 = 0
		ENDIF
		
	ENDIF	
	 	

ENDWHILE

CLEAR_THIS_PRINT (CM2_5)

TIMERA = 0 // To let message stuff start for the gang war

WHILE NOT gang_strenth1_crash4 = 0

	WAIT 0

	GET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT gang_strenth1_crash4
	GET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE player_gang_strenth_crash4
	
	IF NOT IS_PLAYER_IN_INFO_ZONE player1 GLN1
		
		IF flag_zone_blip_on_crash4 = 0
			PRINT_NOW (CM2_6) 8000 1 //"Get back to Glen Park!"
			flag_zone_blip_on_crash4 = 1
		ENDIF

		IF NOT IS_GANG_WAR_GOING_ON

			IF player_gang_strenth_crash4 = 0
		
				IF flag_had_gang_war_message2_crash4 = 0
					PRINT_NOW (CM2_17) 8000 1 //"The enemy have retaken the territory!"
					flag_had_gang_war_message2_crash4 = 1
				ENDIF

			ENDIF

		ELSE
			flag_had_gang_war_message2_crash4 = 0	
		ENDIF 

	ELSE

		IF flag_zone_blip_on_crash4 = 1
			CLEAR_THIS_PRINT (CM2_6)  
			flag_zone_blip_on_crash4 = 0
		ENDIF

		IF NOT IS_GANG_WAR_GOING_ON

			IF player_gang_strenth_crash4 = 0

				IF flag_had_gang_war_message_crash4 = 0
					PRINT_NOW (CM2_16) 8000 1 //"Attack enemy gang members to start the gang war!"
					flag_had_gang_war_message_crash4 = 1
				ENDIF

			ENDIF

		ELSE
			flag_had_gang_war_message_crash4 = 0
		ENDIF

	ENDIF
		
ENDWHILE

REMOVE_BLIP area_blip_crash4

CLEAR_THIS_PRINT (CM2_16)
CLEAR_THIS_PRINT (CM2_17)
CLEAR_THIS_PRINT (CM2_6)
CLEAR_THIS_PRINT (CM2_5)

SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 0

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

WAIT 5000

SET_ONLY_CREATE_GANG_MEMBERS FALSE

// *********************************** Cutscene showing player has taken over the territory *****************

GOSUB check_player_is_safe

WHILE NOT player_is_completely_safe = 1

	WAIT 0

	GOSUB check_player_is_safe

ENDWHILE

CLEAR_PRINTS
SET_PLAYER_CONTROL player1 OFF
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_PED_DENSITY_MULTIPLIER 0.0
MAKE_PLAYER_GANG_DISAPPEAR

SWITCH_WIDESCREEN ON

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	CLEAR_AREA 1859.931 -1137.139 22.946 2.0 TRUE 
	SET_CHAR_COORDINATES scplayer 1859.931 -1137.139 22.946
	SET_CHAR_HEADING scplayer 284.0
	LOAD_SCENE 1859.931 -1137.139 22.946
ELSE
	STORE_CAR_CHAR_IS_IN scplayer stored_car_crash4
	CLEAR_AREA 1859.931 -1137.139 22.946 2.0 TRUE 
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 1859.931 -1137.139 22.946

	WHILE IS_CHAR_IN_ANY_CAR scplayer

		WAIT 0

	ENDWHILE

	SET_CHAR_HEADING scplayer 284.0
	LOAD_SCENE 1859.931 -1137.139 22.946
ENDIF
 

SET_FIXED_CAMERA_POSITION 1859.8280 -1136.1796 33.7679 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 1860.7511 -1136.4626 33.5080 JUMP_CUT

IF created_cutscene_guys2_crash4 = 0
	CREATE_CHAR PEDTYPE_MISSION1 FAM2 1910.336 -1154.475 22.699 cut_guy1_crash4
	SET_CHAR_HEADING cut_guy1_crash4 131.345
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cut_guy1_crash4 TRUE
   	TASK_PLAY_ANIM_NON_INTERRUPTABLE cut_guy1_crash4 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1

	CREATE_CHAR PEDTYPE_MISSION1 FAM3 1907.990 -1157.554 22.932 cut_guy2_crash4
	SET_CHAR_HEADING cut_guy2_crash4 318.746
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cut_guy2_crash4 TRUE
   	TASK_PLAY_ANIM_NON_INTERRUPTABLE cut_guy2_crash4 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1

	created_cutscene_guys2_crash4 = 1
ENDIF

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0
	
ENDWHILE

IF played_cut2_before_crash4 = 1
	SKIP_CUTSCENE_START
ENDIF

PRINT_NOW (CM2_12) 20000 1 //"The territory is now owned by the families and is marked on the radar in green."

TIMERA = 0

WHILE TIMERA < 10000

	WAIT 0

ENDWHILE

CLEAR_THIS_PRINT (CM2_12)

SET_FIXED_CAMERA_POSITION 1891.8889 -1163.3944 25.5792 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 1892.8651 -1163.1869 25.5167 JUMP_CUT

PRINT_NOW (CM2_13) 20000 1 //"Your gang members can now be found in this territory.

TIMERA = 0

WHILE TIMERA < 10000

	WAIT 0

ENDWHILE

CLEAR_THIS_PRINT (CM2_13)

PRINT_NOW (CM2_14) 20000 1 //"Your territory will come under constant attack from enemy gangs and you must defend it.

TIMERA = 0

WHILE TIMERA < 10000

	WAIT 0

ENDWHILE

CLEAR_THIS_PRINT (CM2_14)

SET_FIXED_CAMERA_POSITION 1859.8280 -1136.1796 33.7679 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 1860.7511 -1136.4626 33.5080 JUMP_CUT

PRINT_NOW (CM2_15) 20000 1 //"When an attack takes place the territory will flash red.

TIMERA = 0

WHILE TIMERA < 10000

	WAIT 0

ENDWHILE

CLEAR_THIS_PRINT (CM2_15)

PRINT_NOW (CM2_20) 20000 1 //"You will gain respect and money for taking over territory, the money will appear at the Grove Street hub."

TIMERA = 0

WHILE TIMERA < 10000
	WAIT 0
ENDWHILE

CLEAR_THIS_PRINT (CM2_20)

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_PRINTS

IF created_cutscene_guys2_crash4 = 1
	DELETE_CHAR cut_guy1_crash4 
	DELETE_CHAR cut_guy2_crash4
ENDIF


SET_PED_DENSITY_MULTIPLIER 1.0

IF played_cut2_before_crash4 = 1

	flag_watched_end_cut2_crash4 = 1
	played_cut2_before_crash4 = 1
	SKIP_CUTSCENE_END

	IF flag_watched_end_cut2_crash4 = 0

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT
		
		WHILE GET_FADING_STATUS
		
			WAIT 0
			
		ENDWHILE

		CLEAR_PRINTS

		SET_PED_DENSITY_MULTIPLIER 1.0
		
		IF created_cutscene_guys2_crash4 = 1
			DELETE_CHAR cut_guy1_crash4 
			DELETE_CHAR cut_guy2_crash4
		ENDIF

	ENDIF
	
ENDIF

played_cut2_before_crash4 = 1 

//// ********************************************* CUT SCENE SHOWING ENEMY RUNNING AWAY ***********************

SET_FIXED_CAMERA_POSITION 1914.1069 -1130.9935 27.8685 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 1914.8413 -1130.3322 27.7162 JUMP_CUT

//SWITCH_WIDESCREEN ON

IF flag_end_guy_audio_crash4 = 0
	LOAD_MISSION_AUDIO 1 SOUND_CRA2_ZA 
ELSE
	IF flag_end_guy_audio_crash4 = 1
		LOAD_MISSION_AUDIO 1 SOUND_CRA2_ZB
	ELSE

		IF flag_end_guy_audio_crash4 = 2 
			LOAD_MISSION_AUDIO 1 SOUND_CRA2_ZC 
		ENDIF
	
	ENDIF

ENDIF 

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0
ENDWHILE 

CLEAR_AREA 1922.439 -1118.043 25.125 10.0 FALSE
CREATE_CHAR PEDTYPE_MISSION2 BALLAS2 1922.439 -1118.043 25.125 enemy_crash4
SET_CHAR_HEADING enemy_crash4 180.0
SET_CHAR_ONLY_DAMAGED_BY_PLAYER enemy_crash4 TRUE 
GIVE_WEAPON_TO_CHAR enemy_crash4 WEAPONTYPE_TEC9 30000 // Set to infinate ammo
SET_CHAR_PROOFS enemy_crash4 TRUE TRUE TRUE TRUE TRUE

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

IF NOT IS_CHAR_DEAD enemy_crash4
	TASK_GO_STRAIGHT_TO_COORD enemy_crash4 1922.328 -1127.938 24.078 PEDMOVE_RUN -1
ELSE
	enemy_dead_crash4 = 1
ENDIF

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

IF NOT IS_CHAR_DEAD enemy_crash4

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH enemy_crash4 TRUE

	IF flag_end_guy_audio_crash4 = 0
		PRINT_NOW (CRA2_ZA) 10000 1 //"That fucker Paulaski sold me out, die mofo!""
		PLAY_MISSION_AUDIO 1
		START_CHAR_FACIAL_TALK enemy_crash4 999999
	ELSE

		IF flag_end_guy_audio_crash4 = 1
			PRINT_NOW (CRA2_ZB) 10000 1 //"That fucker Paulaski sold me out, die mofo!""
			PLAY_MISSION_AUDIO 1
			START_CHAR_FACIAL_TALK enemy_crash4 999999
		ELSE

			IF flag_end_guy_audio_crash4 = 2
				PRINT_NOW (CRA2_ZC) 10000 1 //"That fucker Paulaski sold me out, die mofo!""
				PLAY_MISSION_AUDIO 1
				START_CHAR_FACIAL_TALK enemy_crash4 999999
			ENDIF

		ENDIF

	ENDIF
		 
//	PRINT_NOW (CM2_7) 8000 1 //"That fucker Paulaski sold me out, die mofo!"
ELSE
//	CLEAR_THIS_PRINT (CM2_7)
	CLEAR_THIS_PRINT (CRA2_ZA)
	CLEAR_THIS_PRINT (CRA2_ZB)
	CLEAR_THIS_PRINT (CRA2_ZC)
	enemy_dead_crash4 = 1
ENDIF


SKIP_CUTSCENE_START

TIMERA = 0

WHILE TIMERA < 2000

	WAIT 0

	IF enemy_dead_crash4 = 0
				
		IF IS_CHAR_DEAD enemy_crash4

			IF NOT HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_MISSION_AUDIO 1
			ENDIF

			CLEAR_THIS_PRINT (CRA2_ZA)
			CLEAR_THIS_PRINT (CRA2_ZB)
			CLEAR_THIS_PRINT (CRA2_ZC)
			enemy_dead_crash4 = 1
		ELSE
			
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_THIS_PRINT (CRA2_ZA)
				CLEAR_THIS_PRINT (CRA2_ZB)
				CLEAR_THIS_PRINT (CRA2_ZC)
			ENDIF

		ENDIF

	ENDIF

ENDWHILE

//CLEAR_THIS_PRINT (CM2_7)

IF HAS_MISSION_AUDIO_FINISHED 1
	CLEAR_THIS_PRINT (CRA2_ZA)
	CLEAR_THIS_PRINT (CRA2_ZB)
	CLEAR_THIS_PRINT (CRA2_ZC)
ENDIF

IF NOT IS_CHAR_DEAD enemy_crash4
	STOP_CHAR_FACIAL_TALK enemy_crash4
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH enemy_crash4 FALSE
ELSE 
	enemy_dead_crash4 = 1
ENDIF

MAKE_PLAYER_GANG_REAPPEAR

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
SET_POLICE_IGNORE_PLAYER player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 OFF

IF NOT IS_CHAR_DEAD enemy_crash4 
	SET_CHAR_DECISION_MAKER enemy_crash4 tough_decisionmaker_crash4
	ADD_BLIP_FOR_CHAR enemy_crash4 enemy_blip_crash4
	TASK_KILL_CHAR_ON_FOOT enemy_crash4 scplayer
	SET_CHAR_PROOFS enemy_crash4 FALSE FALSE FALSE FALSE FALSE
ELSE
	REMOVE_BLIP enemy_blip_crash4 
	enemy_dead_crash4 = 1
ENDIF

watched_baddie_cut_crash4 = 1
SKIP_CUTSCENE_END

IF watched_baddie_cut_crash4 = 0

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT

	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

	MAKE_PLAYER_GANG_REAPPEAR

	IF NOT IS_CHAR_DEAD enemy_crash4
		CLEAR_AREA 1922.903 -1128.477 24.092 2.0 FALSE  
		SET_CHAR_COORDINATES enemy_crash4 1922.903 -1128.477 24.092
		SET_CHAR_HEADING enemy_crash4 180.0
		STOP_CHAR_FACIAL_TALK enemy_crash4
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH enemy_crash4 FALSE
	ELSE 
		enemy_dead_crash4 = 1
	ENDIF 

	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 ON
	SET_POLICE_IGNORE_PLAYER player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 OFF

	IF NOT IS_CHAR_DEAD enemy_crash4 
		ADD_BLIP_FOR_CHAR enemy_crash4 enemy_blip_crash4
		TASK_KILL_CHAR_ON_FOOT enemy_crash4 scplayer
		SET_CHAR_PROOFS enemy_crash4 FALSE FALSE FALSE FALSE FALSE
	ELSE
		REMOVE_BLIP enemy_blip_crash4 
		enemy_dead_crash4 = 1
	ENDIF 

ENDIF

++ flag_end_guy_audio_crash4

IF flag_end_guy_audio_crash4 > 2
	flag_end_guy_audio_crash4 = 0
ENDIF
	  
PRINT_NOW (CM2_8) 8000 1 //"You have flushed him out, kill him and take over the territory!"

// waiting for the enemy guy to be dead
WHILE NOT enemy_dead_crash4 = 1

	WAIT 0
			
	IF enemy_dead_crash4 = 0
				
		IF IS_CHAR_DEAD enemy_crash4
			CLEAR_THIS_PRINT (CM2_7)
			REMOVE_BLIP enemy_blip_crash4
			enemy_dead_crash4 = 1
		ENDIF

	ENDIF
	
ENDWHILE
		
GOTO mission_crash4_passed

 // **************************************** Mission crash4 failed ************************

mission_crash4_failed:
SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40
SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0

SET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX stored_nmex_strength_crash4
SET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX stored_smex_strength_crash4

SWITCH_ENTRY_EXIT ammun1 FALSE
SWITCH_ENTRY_EXIT ammun2 FALSE
SWITCH_ENTRY_EXIT ammun3 FALSE
SWITCH_ENTRY_EXIT ammun4 FALSE
SWITCH_ENTRY_EXIT ammun5 FALSE


PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
SET_GANG_WARS_ACTIVE FALSE
flag_gang_war_active = 0
RETURN

   

// **************************************** mission crash4 passed *************************

mission_crash4_passed:

flag_sweet_mission_counter ++
REGISTER_MISSION_PASSED (CRASH_2) //Used in the stats
PLAYER_MADE_PROGRESS 1

PRINT_WITH_NUMBER_BIG ( M_PASSR ) 40 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 40 // ammount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1

REMOVE_BLIP sweet_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip

CREATE_PROTECTION_PICKUP 2502.10 -1686.38 13.0 10000 territory_cash territory_pickup
START_NEW_SCRIPT territory_cash_loop

SWITCH_ENTRY_EXIT ammun1 TRUE
SWITCH_ENTRY_EXIT ammun2 TRUE
SWITCH_ENTRY_EXIT ammun3 TRUE
SWITCH_ENTRY_EXIT ammun4 TRUE
SWITCH_ENTRY_EXIT ammun5 TRUE

IF add_all_ammu_blips = 0
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1372.9 -1278.8 12.5 RADAR_SPRITE_GUN weapon_shop1_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2400.5 -1978.4 13.5 RADAR_SPRITE_GUN ammu_shop_blip[0] //AMMUN3
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2626.6 209.4 4.9 RADAR_SPRITE_GUN ammu_shop_blip[1] //AMMUN1
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2535.9 2083.5 10.8 RADAR_SPRITE_GUN ammu_shop_blip[2] //AMMUN2
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2156.5 943.2 10.8 RADAR_SPRITE_GUN ammu_shop_blip[3] //AMMUN2
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 779.7 1874.3 4.9 RADAR_SPRITE_GUN ammu_shop_blip[4] //AMMUN3
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2092.7 -2463.8 30.6 RADAR_SPRITE_GUN ammu_shop_blip[5] //AMMUN3
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 240.0 -178.2 2.0 RADAR_SPRITE_GUN ammu_shop_blip[6] //AMMUN2
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1509.4, 2611.8, 58.5 RADAR_SPRITE_GUN ammu_shop_blip[7] //NEW!!!!!!!!!
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -315.67, 829.87, 13.43 RADAR_SPRITE_GUN ammu_shop_blip[8] //NEW!!!!!!!!!
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2332.9, 63.6, 31.0, RADAR_SPRITE_GUN ammu_shop_blip[9] //NEW!!!!!!!!!
	add_all_ammu_blips = 1
ENDIF
 
RETURN
		

// ********************************** mission cleanup ************************************

mission_cleanup_crash4:

MARK_MODEL_AS_NO_LONGER_NEEDED Laejeffersalpha
MARK_MODEL_AS_NO_LONGER_NEEDED LaeLODpark02
MARK_MODEL_AS_NO_LONGER_NEEDED Laemacpark02

SET_PED_DENSITY_MULTIPLIER 1.0
SET_ONLY_CREATE_GANG_MEMBERS FALSE

flag_player_on_mission = 0
flag_on_doberman_mission = 0

REMOVE_BLIP area_blip_crash4
REMOVE_BLIP enemy_blip_crash4
REMOVE_BLIP weapon_pickup_blip_crash4

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

GET_GAME_TIMER timer_mobile_start

MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
MARK_MODEL_AS_NO_LONGER_NEEDED FAM3

REMOVE_ANIMATION GANGS
REMOVE_ANIMATION SMOKING

DELETE_OBJECT cigar_crash4
MARK_MODEL_AS_NO_LONGER_NEEDED cigar

DELETE_CHAR cut_guy1_crash4
DELETE_CHAR cut_guy2_crash4
DELETE_CHAR cut_guy3_crash4

REMOVE_DECISION_MAKER tough_decisionmaker_crash4 

MISSION_HAS_FINISHED
RETURN

}


