MISSION_START
// *****************************************************************************************
// ***************************** syn5 ***************************************************** 
// *****************************************************************************************
// *****************************************************************************************
// **************************PIMP STYLE*****************************************************
// *****************************************************************************************
SCRIPT_NAME syn5
// Mission start stuff
GOSUB mission_start_syn5
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_syn5_failed
ENDIF
GOSUB mission_cleanup_syn5
MISSION_END
{
// Variables for mission

//people
LVAR_INT syn5_toreno syn5_tbone syn5_wuzi_goons[3] syn5_ryder_goons[6] syn5_tbone_goons[6] syn5_random_goons[4] 
LVAR_INT syn5_ryders_car syn5_ryder_goon_cars[2] syn5_toreno_chopper
LVAR_INT syn5_teargas[6]
LVAR_INT syn5_boat[2]
LVAR_INT syn5_party_dudes[5]
LVAR_INT syn5_fire 
LVAR_INT syn5_sniper_anim
LVAR_INT syn5_random_car
LVAR_INT syn5_dummy_car
LVAR_INT syn5_tbones_car
LVAR_INT syn5_cesars_car
LVAR_INT syn5_woodpile

//blips
LVAR_INT syn5_blip syn5_sniper_blips[6] syn5_boat_blip   

//flags
LVAR_INT syn5_goals syn5_control_flag syn5_skip_cutscene_flag syn5_deathcheck_flag 
LVAR_INT syn5_ammo_flag syn5_killing_wuzi_men_flag syn5_wuzi_men_count syn5_wuzi_men_killed_flag[3] 
LVAR_INT syn5_wuzi_number_flag
LVAR_INT syn5_killing_sniper_flag syn5_killing_sniper_count syn5_sniper_killed_count_flag[6] 
LVAR_INT syn5_sniper_number_flag 
LVAR_INT syn5_char_select_flag syn5_char_select[7]
LVAR_INT syn5_sniper_control_flag[6]
LVAR_INT syn5_wuzi_control_flag
LVAR_INT syn5_wuzi_goons_control_flag[3]
LVAR_INT syn5_controlling_tbone
LVAR_INT syn5_flag_cesar_in_group 
LVAR_INT syn5_does_player_have_sniperrifle
LVAR_INT syn5_no_plates syn5_no_plates_flag
LVAR_INT syn5_accuracy


LVAR_FLOAT syn5_anim_time

//speech
LVAR_INT syn5_speech_goals syn5_speech_flag syn5_speech_control_flag syn5_random_last_label 
LVAR_TEXT_LABEL syn5_print_label[16] 
LVAR_INT syn5_audio_label[16] 
LVAR_INT syn5_last_label  
LVAR_INT syn5_slot1 syn5_slot2 syn5_slot_load syn5_play_which_slot 
LVAR_INT syn5_storing_speech_goals_number syn5_storing_speech_control_number
				


//coords
LVAR_FLOAT syn5_x syn5_y syn5_z syn5_animtime		

//sequences/decision makers/threat lists/attractors/groups
LVAR_INT syn5_seq syn5_ped_decisions syn5_empty_ped_decision_maker 
LVAR_INT syn5_ryder_seq2 syn5_ryder_seq3 syn5_ryder_seq5 

//debug

// ****************************************Mission Start************************************
mission_start_syn5:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT SYN5
IF flag_player_on_mission = 0
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[0]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[1]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[2]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[3]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[4]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[5]
	CREATE_CHAR PEDTYPE_MISSION1 TRIBOSS -1674.5 1337.0 6.2 wuzi
	CREATE_CHAR PEDTYPE_MISSION1 syn5_char_select[syn5_char_select_flag] -1675.7 1336.1 6.2 syn5_wuzi_goons[0]
	CREATE_CHAR PEDTYPE_MISSION1 syn5_char_select[syn5_char_select_flag] -1675.7 1336.1 6.2 syn5_wuzi_goons[1]
	CREATE_CHAR PEDTYPE_MISSION1 syn5_char_select[syn5_char_select_flag] -1675.7 1336.1 6.2 syn5_wuzi_goons[2]
	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL04 -1716.0 1349.8 6.2 syn5_tbone	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1716.8 1351.6 6.2 syn5_tbone_goons[0]	
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1717.5 1350.7 6.2 syn5_tbone_goons[1]	
	CREATE_CAR PICADOR -1774.2 1376.8 5.7 syn5_ryders_car
	CREATE_CHAR_INSIDE_CAR syn5_ryders_car PEDTYPE_MISSION2 SPECIAL02 ryder
	CREATE_CHAR_AS_PASSENGER syn5_ryders_car PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1 syn5_ryder_goons[0]    
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1774.2 1376.8 5.7 syn5_ryder_goons[1]    
	CREATE_CAR tahoma -1784.2 1379.8 5.7 syn5_ryder_goon_cars[0]
	CREATE_CHAR_INSIDE_CAR syn5_ryder_goon_cars[0] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] syn5_ryder_goons[2]
	CREATE_CHAR_AS_PASSENGER syn5_ryder_goon_cars[0] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] 0 syn5_ryder_goons[3]    
	CREATE_CAR tahoma -1795.0 1383.6 5.7 syn5_ryder_goon_cars[1]
	CREATE_CHAR_INSIDE_CAR syn5_ryder_goon_cars[1] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] syn5_ryder_goons[4]
	CREATE_CHAR_AS_PASSENGER syn5_ryder_goon_cars[1] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] 0 syn5_ryder_goons[5]    
	CREATE_CAR maverick -1583.3 1420.2 25.7 syn5_toreno_chopper
	CREATE_CHAR_AS_PASSENGER syn5_toreno_chopper PEDTYPE_MISSION2 SPECIAL03 0 syn5_toreno
ENDIF	
CLEAR_PRINTS
CLEAR_THIS_PRINT_BIG_NOW 1
WAIT 0
// *************************************Set Flags/variables*********************************
syn5_goals = 0
syn5_control_flag = 0
syn5_skip_cutscene_flag = 0
syn5_deathcheck_flag = 0 
syn5_speech_flag = 0
syn5_ammo_flag = 0
syn5_killing_wuzi_men_flag = 0
syn5_wuzi_men_count = 3
syn5_wuzi_men_killed_flag[0] = 0
syn5_wuzi_men_killed_flag[1] = 0
syn5_wuzi_men_killed_flag[2] = 0
syn5_killing_sniper_flag = 0
syn5_killing_sniper_count = 6
syn5_wuzi_number_flag = 0
syn5_sniper_number_flag = 0
syn5_sniper_killed_count_flag[0] = 0
syn5_sniper_killed_count_flag[1] = 0
syn5_sniper_killed_count_flag[2] = 0
syn5_sniper_killed_count_flag[3] = 0
syn5_sniper_killed_count_flag[4] = 0
syn5_sniper_killed_count_flag[5] = 0
syn5_char_select_flag = 0
syn5_sniper_number_flag = 0

syn5_char_select[0] = SFR1
syn5_char_select[1] = SFR2 
syn5_char_select[3] = BALLAS1
syn5_char_select[4] = BALLAS2 

syn5_sniper_control_flag[0] = 0
syn5_sniper_control_flag[1] = 0
syn5_sniper_control_flag[2] = 0
syn5_sniper_control_flag[3] = 0
syn5_sniper_control_flag[4] = 0
syn5_sniper_control_flag[5] = 0
syn5_wuzi_control_flag = 0
syn5_wuzi_goons_control_flag[0] = 0
syn5_wuzi_goons_control_flag[1] = 0
syn5_wuzi_goons_control_flag[2] = 0

syn5_controlling_tbone = 0
syn5_flag_cesar_in_group = 0

syn5_speech_goals = 0
syn5_speech_flag = 0 
syn5_speech_control_flag = 0 
syn5_random_last_label = 0 
syn5_last_label = 0 
syn5_slot1 = 0 
syn5_slot2 = 0 
syn5_slot_load = 0 
syn5_play_which_slot = 0
syn5_storing_speech_goals_number = 0
syn5_storing_speech_control_number = 0

syn5_x = 0.0  
syn5_y = 0.0 
syn5_z = 0.0

syn5_does_player_have_sniperrifle = 0

syn5_no_plates = 0
syn5_no_plates_flag = 0

syn5_accuracy = 80


// ****************************************START OF CUTSCENE********************************
// ****************************************END OF CUTSCENE**********************************
SET_FADING_COLOUR 0 0 0
WAIT 0
FORCE_WEATHER WEATHER_SUNNY_SF 
//------------------REQUEST_MODELS ------------------------------
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 

MAKE_PLAYER_GANG_DISAPPEAR

LOAD_SPECIAL_CHARACTER 1 cesar
REQUEST_MODEL SNIPER 
REQUEST_MODEL BANSHEE
REQUEST_MODEL SNIPER_anim
REQUEST_ANIMATION MISC
REQUEST_MODEL SFR1
REQUEST_MODEL SFR2
REQUEST_MODEL CELLPHONE 
REQUEST_MODEL TEC9 
REQUEST_MODEL TRIBOSS 
REQUEST_MODEL TRIADA 
REQUEST_MODEL SAVANNA 
LOAD_ALL_MODELS_NOW

REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH syn5_ped_decisions  

/////////////////////////////// DECISION MAKER SHIT ////////////////////////////////////////////// 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_PLAYER1	   ////1 is good GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2	   ////1 is good GUYS
//SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1	   ////2 is bad GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE	PEDTYPE_MISSION2 PEDTYPE_PLAYER1

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY syn5_empty_ped_decision_maker 

//creating cesar's car
CLEAR_AREA -1748.3 1263.0 7.0 5.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA &_LVA4L__ 
CREATE_CAR SAVANNA -1748.3 1263.0 7.0 syn5_cesars_car
SET_CAR_HEADING syn5_cesars_car 291.3 
CHANGE_CAR_COLOUR syn5_cesars_car 3 3 

//creating cesar
CLEAR_AREA -1720.7 1277.3 16.9 2.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 -1720.7 1277.3 16.9 cesar	
SET_CHAR_HEADING cesar 317.9
GIVE_WEAPON_TO_CHAR cesar WEAPONTYPE_SNIPERRIFLE 12 
SET_CHAR_NEVER_TARGETTED cesar TRUE  
SET_CHAR_DECISION_MAKER cesar syn5_empty_ped_decision_maker
SET_CHAR_ONLY_DAMAGED_BY_PLAYER cesar TRUE
SET_CHAR_STAY_IN_SAME_PLACE cesar TRUE

SET_CHAR_HEADING scplayer 0.0
SET_CAMERA_BEHIND_PLAYER 

ADD_BLIP_FOR_CHAR cesar syn5_blip
SET_BLIP_AS_FRIENDLY syn5_blip TRUE


CLEAR_AREA -1717.5 1281.6 6.2 1.0 TRUE
SET_CHAR_COORDINATES scplayer -1717.5 1281.6 6.2
SET_CHAR_HEADING scplayer 143.8 

SWITCH_RANDOM_TRAINS OFF
SWITCH_PED_ROADS_OFF -1755.6 1256.6 0.0 -1598.2 1660.1 50.0

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON
MAKE_PLAYER_GANG_DISAPPEAR
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

CLEAR_AREA -1716.7 1282.6 6.5 2.0 TRUE
SET_FIXED_CAMERA_POSITION -1716.7 1282.6 6.5 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -1722.3 1276.0 16.9 JUMP_CUT

ENABLE_AMBIENT_CRIME FALSE

timera = 0 




/*
/////////////DEBUG//////////////////
syn5_control_flag = 3
SET_CHAR_COORDINATES scplayer -1721.6 1273.8 16.9
/////////////DEBUG//////////////////
*/



DO_FADE 500 FADE_IN




mission_syn5_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_syn5_passed  
	ENDIF


	ALTER_WANTED_LEVEL player1 0


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB syn5_death_checks
	IF syn5_goals = 10 
		GOTO syn_goals10
	ENDIF
	IF syn5_deathcheck_flag = 1
		GOTO mission_syn5_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Waiting for player to reach cesar///////////////////////////////////////////////////////////////
	IF syn5_goals = 0
		/////////// INITIAL CUTSCENE /////////////////
		IF syn5_control_flag = 0
			IF timera > 500
				//speech of cesar telling player he is above him
				IF syn5_speech_flag = 0
					syn5_speech_goals = 1
					syn5_speech_control_flag = 0
					GOSUB syn5_dialogue_setup 
					syn5_speech_flag = 1
			
					syn5_skip_cutscene_flag = 1
					SKIP_CUTSCENE_START
			
					syn5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 1 
			IF syn5_speech_goals = 0
				timera = 0 
				syn5_control_flag = 2	 
			ENDIF
		ENDIF
		
		IF syn5_control_flag = 2
			IF timera > 1000
				syn5_skip_cutscene_flag = 0	
				SKIP_CUTSCENE_END
				GOSUB syn5_death_checks
				IF syn5_goals = 10 
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
				
				//if cutscene has been skipped ////////////////////////////////////////////
				IF syn5_skip_cutscene_flag = 1
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_PRINTS
					syn5_speech_goals = 0
				ENDIF
				
				CLEAR_AREA -1721.4 1276.2 16.9 1.0 TRUE
				SET_CHAR_COORDINATES cesar -1721.4 1276.2 16.9
				SET_CHAR_HEADING cesar 326.3 
				//TASK_AIM_GUN_AT_COORD cesar -1715.4 1287.5 16.5 -1  

				PRINT_NOW ( SYN5_01 ) 7000 1 // Go upstairs and meet Cesar.
				
				MAKE_PLAYER_GANG_REAPPEAR
				
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE		 
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				syn5_control_flag = 3

			ENDIF
		ENDIF
		
		
		// PLAYER ARRIVING AND WALKING OVER TO CESAR /////////////////              
		IF syn5_control_flag = 3 
			
			//player has reached cesar
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cesar 4.0 4.0 4.0 FALSE 
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1	
				CLEAR_MISSION_AUDIO 2
				syn5_speech_goals = 0
			
				MARK_CAR_AS_NO_LONGER_NEEDED syn5_cesars_car
				MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA 
				
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				SHUT_ALL_CHARS_UP TRUE

				IF IS_CHAR_IN_ANY_CAR scplayer 
					SET_CHAR_COORDINATES scplayer -1728.0 1257.4 16.9
					SET_CHAR_HEADING scplayer 42.2
				ENDIF
				
				CLEAR_AREA -1673.7 1369.0 7.0 150.0 TRUE

				CLEAR_AREA -1690.0 1318.0 7.0 5.0 TRUE
				
				syn5_no_plates = BANSHEE
				GOSUB syn5_my_number_plates
				CREATE_CAR BANSHEE -1690.0 1318.0 7.0 syn5_dummy_car 
				SET_CAR_HEADING syn5_dummy_car 57.0
				FREEZE_CAR_POSITION syn5_dummy_car TRUE
				LOCK_CAR_DOORS syn5_dummy_car CARLOCK_LOCKED  

				CLEAR_AREA -1721.4 1276.2 16.9 5.0 TRUE
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				SET_CHAR_COORDINATES scplayer -1722.8 1272.5 16.9
				SET_CHAR_HEADING scplayer 326.3

				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				SET_CHAR_COORDINATES cesar -1721.4 1276.2 16.9 	
				SET_CHAR_HEADING cesar 326.3
				
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE  
					REMOVE_ALL_CHAR_WEAPONS cesar 
					syn5_does_player_have_sniperrifle = 1
				ENDIF
				
				syn5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				
				OPEN_SEQUENCE_TASK syn5_seq	  
					TASK_GO_STRAIGHT_TO_COORD -1 -1720.79 1275.4 16.9 PEDMOVE_WALK -1 
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK scplayer syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
				REMOVE_BLIP syn5_blip
			
				//cutscene dialogue where cesar says hi to player
				syn5_speech_goals = 2
				syn5_speech_control_flag = 0
				syn5_random_last_label = 2 
				GOSUB syn5_dialogue_setup 
			
				SET_FIXED_CAMERA_POSITION -1725.2 1270.5 18.87 0.0 0.0 0.0  ///this is camera BEHIND plyr and cesar
				POINT_CAMERA_AT_POINT -1702.2 1316.1 22.4 JUMP_CUT
			
				syn5_control_flag = 0
				syn5_goals = 1
			ENDIF

			//////////////////////////////////DEBUG////////////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
				LOAD_SCENE -1725.7 1267.0 17.3
				SET_CHAR_COORDINATES scplayer -1725.7 1267.0 17.3
				SET_CHAR_HEADING scplayer 308.8
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF
			//////////////////////////////////DEBUG////////////////////////
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////SECTION 1 - Sniping the guards up top /////////////////////////////////////////////////////////// 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Cutscene showing guards /////////////////////////////////////////////////////////////////////// 
	IF syn5_goals = 1
	
		//cesar coming to give player the gun
		IF syn5_control_flag = 0
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status		 
			IF task_status = FINISHED_TASK
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer cesar
			
				//if player didn't have sniper at start of scene
				IF syn5_does_player_have_sniperrifle = 0
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
						TASK_PLAY_ANIM -1 idle_armed PED 4.0 FALSE FALSE FALSE TRUE -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK cesar syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				
					CREATE_OBJECT SNIPER_anim -1714.2 1266.5 18.3 syn5_sniper_anim
				ELSE
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK cesar syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF	
				syn5_control_flag = 1
			ENDIF
		ENDIF

		//Waiting for cesar to finish passing the gun to the player
		IF syn5_control_flag = 1
			IF syn5_speech_goals = 0
				GET_SCRIPT_TASK_STATUS cesar PERFORM_SEQUENCE_TASK task_status
				IF task_status = FINISHED_TASK
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cesar 0.0 1.0 -1.0 syn5_x syn5_y syn5_z 
					
					SET_CHAR_COORDINATES scplayer syn5_x syn5_y syn5_z 
					GET_CHAR_HEADING cesar syn5_x
					syn5_x += 180.0
					SET_CHAR_HEADING scplayer syn5_x  
				
					//cutscene dialogue where cesar says hi to player
					syn5_speech_goals = 2
					syn5_speech_control_flag = 2
					syn5_random_last_label = 4 
					GOSUB syn5_dialogue_setup 

					SET_FIXED_CAMERA_POSITION -1722.04 1273.2 18.81 0.0 0.0 0.0  ///this is camera BEHIND plyr and cesar
					POINT_CAMERA_AT_POINT -1708.2 1299.9 17.76 JUMP_CUT
		
					syn5_control_flag = 2
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 2
			//if player didn't have sniper at start of scene
			IF syn5_does_player_have_sniperrifle = 0
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cesar 0.0 0.5 0.0 syn5_x syn5_y syn5_z 
				REMOVE_ALL_CHAR_WEAPONS cesar
				
				SET_OBJECT_COORDINATES syn5_sniper_anim syn5_x syn5_y syn5_z 
				GET_CHAR_HEADING scplayer syn5_x  
				SET_OBJECT_HEADING syn5_sniper_anim syn5_x
				TASK_PLAY_ANIM cesar PASS_Rifle_Ped MISC 1000.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM scplayer PASS_Rifle_Ply MISC 1000.0 FALSE FALSE FALSE FALSE -1
				//TASK_PICK_UP_OBJECT scplayer syn5_sniper_anim 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				syn5_control_flag = 3
			ELSE
				IF syn5_speech_goals = 0
					syn5_control_flag = 3
				ENDIF
			ENDIF
		ENDIF
	
		IF syn5_control_flag = 3
			//if player didn't have sniper at start of scene
			IF syn5_does_player_have_sniperrifle = 0
				IF DOES_OBJECT_EXIST syn5_sniper_anim
	
					IF IS_CHAR_PLAYING_ANIM scplayer PASS_Rifle_Ply
						GET_CHAR_ANIM_CURRENT_TIME scplayer PASS_Rifle_Ply syn5_anim_time
						IF IS_OBJECT_PLAYING_ANIM syn5_sniper_anim PASS_Rifle_O
							SET_OBJECT_ANIM_CURRENT_TIME syn5_sniper_anim PASS_Rifle_O syn5_anim_time
						ELSE
							IF PLAY_OBJECT_ANIM syn5_sniper_anim PASS_Rifle_O MISC 10000.0 FALSE FALSE
								SET_OBJECT_ANIM_SPEED syn5_sniper_anim PASS_Rifle_O 0.0
							ENDIF
						ENDIF
					ENDIF

					IF IS_CHAR_PLAYING_ANIM scplayer PASS_Rifle_Ply 
						GET_CHAR_ANIM_CURRENT_TIME scplayer PASS_Rifle_Ply syn5_animtime
						IF syn5_animtime = 1.0
							syn5_control_flag = 4
						ENDIF
					ENDIF
				ENDIF
			ELSE
				syn5_control_flag = 4
			ENDIF	
		ENDIF

		IF syn5_control_flag = 4
			//if player didn't have sniper at start of scene
			IF syn5_does_player_have_sniperrifle = 0
				DELETE_OBJECT syn5_sniper_anim
			ENDIF
			
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE syn5_ammo_flag  
			IF syn5_ammo_flag < 24 
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SNIPERRIFLE 24
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE
			ELSE
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE  
			ENDIF

			TASK_AIM_GUN_AT_COORD scplayer -1715.4 1287.5 16.5 -1

			TASK_ACHIEVE_HEADING cesar 332.1

			GOSUB syn5_creating_snipers

			timera = 0
			syn5_control_flag = 5
		ENDIF
			  
		//showing player roof goons
		IF syn5_control_flag = 5 
			IF timera > 2000
				IF syn5_speech_goals = 0

					IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
						OPEN_SEQUENCE_TASK syn5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 -1711.1 1354.0 16.0 PEDMOVE_WALK -1 
							TASK_ACHIEVE_HEADING -1 200.0
							TASK_LOOK_ABOUT -1 -1 
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[0] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF

					IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
						OPEN_SEQUENCE_TASK syn5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 -1686.6 1376.2 13.8 PEDMOVE_WALK -1 
							//TASK_ACHIEVE_HEADING -1 200.0
							TASK_LOOK_ABOUT -1 -1 
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[1] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF
					
					SET_FIXED_CAMERA_POSITION -1707.1 1329.7 18.0 0.0 0.0 0.0	   ////this is camera cut looking at snipers and name of pier
					POINT_CAMERA_AT_POINT -1701.8 1360.3 9.0 JUMP_CUT 
					
					//T-Bone's security got here real early.
					syn5_speech_goals = 2
					syn5_speech_control_flag = 4
					syn5_random_last_label = 5 
					GOSUB syn5_dialogue_setup 

					timera = 0			 
					syn5_control_flag = 6
				ENDIF
			ENDIF
		ENDIF
		
		//showing player more roof goons  
		IF syn5_control_flag  = 6
			IF timera > 5000
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1682.7 1343.1 16.2 PEDMOVE_WALK -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -1672.7 1342.6 16.2 PEDMOVE_WALK -1 
						SET_CHAR_HEADING syn5_tbone_goons[3] 4.0
						TASK_LOOK_ABOUT -1 -1 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone_goons[3] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
			
				SET_NEAR_CLIP 0.1
				SET_FIXED_CAMERA_POSITION -1712.2 1354.2 18.1 0.0 0.0 0.0		 ////this is camera cut looking from behind one of the snipers
				POINT_CAMERA_AT_POINT -1675.7 1338.4 9.9 JUMP_CUT
			
				// They've got men on the roofs watching over the pier.
				syn5_speech_goals = 2
				syn5_speech_control_flag = 5
				syn5_random_last_label = 6 
				GOSUB syn5_dialogue_setup 

				GOSUB syn5_creating_wuzi_men
							 
				timera = 0					  
				syn5_control_flag = 7
			ENDIF
		ENDIF

		// getting phone call from wuzi (creating wuzi's men)
		IF syn5_control_flag = 7
			IF timera > 4000
				IF syn5_speech_goals = 0
				 
					IF NOT IS_CHAR_DEAD wuzi
						SET_CHAR_VISIBLE wuzi TRUE
					ENDIF

					SET_NEAR_CLIP 0.9
					SET_FIXED_CAMERA_POSITION -1722.2 1271.7 20.1 0.0 0.0 0.0  ///this is camera BEHIND plyr and cesar
					POINT_CAMERA_AT_POINT -1709.7 1305.0 7.4 JUMP_CUT

					TASK_USE_MOBILE_PHONE cesar TRUE
				
					//phone ringing 
					syn5_speech_goals = 2
					syn5_speech_control_flag = 6
					syn5_random_last_label = 7 
					GOSUB syn5_dialogue_setup 
				
					timera = 0					  
					syn5_control_flag = 8 
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 8 
			IF timera > 2000
				IF syn5_speech_goals = 0 
					//cutscene dialogue where cesar is on phone to wuzi
					syn5_speech_goals = 2
					syn5_speech_control_flag = 7
					syn5_random_last_label = 9 
					GOSUB syn5_dialogue_setup 
					timera = 0 
					syn5_control_flag = 9
				ENDIF
			ENDIF
		ENDIF

		//cesar to stop using the mobile phone and wuzi's men move in place
		IF syn5_control_flag = 9
			IF syn5_speech_goals = 0
				GET_SCRIPT_TASK_STATUS cesar TASK_USE_MOBILE_PHONE task_status
				IF task_status = PERFORMING_TASK
					TASK_USE_MOBILE_PHONE cesar FALSE
				ENDIF
		
				// That was Woozie's boys, they're in place. | Look, down by the side entrance. 
				syn5_speech_goals = 2
				syn5_speech_control_flag = 9
				syn5_random_last_label = 11 
				GOSUB syn5_dialogue_setup 
				syn5_control_flag = 10
			ENDIF
		ENDIF

		//cesar to stop using the mobile phone and wuzi's men move in place
		IF syn5_control_flag = 10
			IF syn5_speech_goals = 0
				GET_SCRIPT_TASK_STATUS cesar TASK_USE_MOBILE_PHONE task_status
				IF task_status = PERFORMING_TASK	  
					TASK_USE_MOBILE_PHONE cesar FALSE
				ENDIF	  
			
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0] 
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1683.4 1326.9 6.0 PEDMOVE_WALK -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -1673.4 1335.0 6.2	PEDMOVE_WALK -1
						TASK_TOGGLE_DUCK -1 FALSE 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD wuzi 
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_PAUSE -1 500 
						TASK_GO_STRAIGHT_TO_COORD -1 -1684.8 1324.7 6.0 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -1675.7 1336.1 6.2	PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK wuzi syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1] 
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_PAUSE -1 700 
						TASK_GO_STRAIGHT_TO_COORD -1 -1687.2 1323.8 6.0 PEDMOVE_WALK -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -1676.0 1334.0 6.2	PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[1] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2] 
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_PAUSE -1 800 
						TASK_GO_STRAIGHT_TO_COORD -1 -1689.0 1325.4 6.0 PEDMOVE_WALK -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -1678.0 1333.7 6.2	PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
			
				SET_FIXED_CAMERA_POSITION -1693.9 1318.9 7.7 0.0 0.0 0.0		///camera looking at wuzi's men
				POINT_CAMERA_AT_POINT -1686.1 1320.8 6.6 JUMP_CUT
			
				timera = 0 
				syn5_control_flag = 11
			ENDIF
		ENDIF

		//looking close cut at wuzi's men
		IF syn5_control_flag = 11 
			IF timera > 3000 

				IF NOT IS_CHAR_DEAD wuzi
					SET_CHAR_COORDINATES wuzi -1681.4 1326.4 6.2
					SET_CHAR_HEADING wuzi 300.1
					
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1671.6 1339.0 6.2 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK wuzi syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0]
					SET_CHAR_COORDINATES syn5_wuzi_goons[0] -1684.1 1324.1 6.2
					SET_CHAR_HEADING syn5_wuzi_goons[0] 311.1
				  
				  	OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1671.6 1339.0 6.2 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1]
					SET_CHAR_COORDINATES syn5_wuzi_goons[1] -1683.0 1321.7 6.2
					SET_CHAR_HEADING syn5_wuzi_goons[1] 333.4
				
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1671.6 1339.0 6.2 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[1] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
				
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2]
					SET_CHAR_COORDINATES syn5_wuzi_goons[2] -1685.4 1323.6 6.2
					SET_CHAR_HEADING syn5_wuzi_goons[2] 323.8
				
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -1671.6 1339.0 6.2 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				SET_FIXED_CAMERA_POSITION -1678.4 1318.3 7.2 0.0 0.0 0.0 ///camera looking at wuzi's men next to the warp up door
				POINT_CAMERA_AT_POINT -1680.6 1330.0 8.9 JUMP_CUT
			
				// Shit, they're heading up to the roof!  
				syn5_speech_goals = 2
				syn5_speech_control_flag = 11
				syn5_random_last_label = 14 
				GOSUB syn5_dialogue_setup 
				syn5_control_flag = 12 
			ENDIF
		ENDIF

	
		//finish up
		IF syn5_control_flag = 12
			IF syn5_speech_goals = 0
				syn5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END

				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS
				syn5_speech_goals = 0
				
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS					
				   WAIT 0
				ENDWHILE 
				GOSUB syn5_death_checks
				IF syn5_goals = 10 
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
					
				SET_NEAR_CLIP 0.9
				
				//if cutscene has been skipped ////////////////////////////////////////////
				IF syn5_skip_cutscene_flag = 1
					GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE syn5_ammo_flag  
					IF syn5_ammo_flag < 24 
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SNIPERRIFLE 24
					ENDIF

					DELETE_OBJECT syn5_sniper_anim
					REMOVE_ALL_CHAR_WEAPONS cesar

					CLEAR_PRINTS
				
					GET_SCRIPT_TASK_STATUS cesar TASK_USE_MOBILE_PHONE task_status
					IF task_status = PERFORMING_TASK
						TASK_USE_MOBILE_PHONE cesar FALSE
					ENDIF
					CLEAR_CHAR_TASKS_IMMEDIATELY cesar
					SET_CHAR_HEADING cesar 332.1
					SET_CHAR_HEADING scplayer 332.1

					GOSUB syn5_creating_snipers 
					GOSUB syn5_creating_wuzi_men
					IF NOT IS_CHAR_DEAD wuzi
						SET_CHAR_VISIBLE wuzi TRUE
					ENDIF

					IF NOT IS_CHAR_DEAD syn5_tbone_goons[0]
						SET_CHAR_COORDINATES syn5_tbone_goons[0] -1711.1 1354.0 16.0
						SET_CHAR_HEADING syn5_tbone_goons[0] 200.0
					ENDIF
				   
					IF NOT IS_CHAR_DEAD syn5_tbone_goons[1]
						SET_CHAR_COORDINATES syn5_tbone_goons[1] -1686.6 1376.2 13.8
						SET_CHAR_HEADING syn5_tbone_goons[1] 200.0
					ENDIF
					
					IF NOT IS_CHAR_DEAD syn5_tbone_goons[3]
						SET_CHAR_COORDINATES syn5_tbone_goons[3] -1672.7 1342.6 16.2
						SET_CHAR_HEADING syn5_tbone_goons[3] 4.0
					ENDIF
				ENDIF
		
				//positioning wuzi's men on top of the shops
				IF NOT IS_CHAR_DEAD wuzi
					CLEAR_CHAR_TASKS_IMMEDIATELY wuzi 
					SET_CHAR_COORDINATES wuzi -1681.9 1335.8 16.2  
					SET_CHAR_HEADING wuzi 337.1
					
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 -1681.7 1335.5 16.2 PEDMOVE_WALK -1 
						TASK_ACHIEVE_HEADING -1 331.0
						//TASK_SHOOT_AT_COORD -1 -1662.1 1360.4 20.4 -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK wuzi syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[0] 
					SET_CHAR_COORDINATES syn5_wuzi_goons[0] -1688.2 1334.3 16.2  
					SET_CHAR_HEADING syn5_wuzi_goons[0] 257.0
							  
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 -1686.1 1334.1 16.2 PEDMOVE_WALK -1 
						TASK_ACHIEVE_HEADING -1 294.0
						//TASK_SHOOT_AT_COORD -1 -1662.1 1360.4 20.4 -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[1] 
					SET_CHAR_COORDINATES syn5_wuzi_goons[1] -1685.8 1336.4 16.2  
					SET_CHAR_HEADING syn5_wuzi_goons[1] 320.0
			
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 -1683.9 1338.4 16.2 PEDMOVE_WALK -1 
						TASK_ACHIEVE_HEADING -1 316.8
						//TASK_SHOOT_AT_COORD -1 -1662.1 1360.4 20.4 -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[1] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
					
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[2] 
					SET_CHAR_COORDINATES syn5_wuzi_goons[2] -1688.3 1335.6 16.2  
					SET_CHAR_HEADING syn5_wuzi_goons[2] 312.6
	
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 -1685.2 1340.5 16.2 PEDMOVE_WALK -1 
						TASK_ACHIEVE_HEADING -1 310.0
						//TASK_SHOOT_AT_COORD -1 -1662.1 1360.4 20.4 -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_wuzi_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				//setting T-Bone's men to kill Wuzi's
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[0] syn5_sniper_blips[0]
					CHANGE_BLIP_SCALE syn5_sniper_blips[0] 1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[1] syn5_sniper_blips[1]
					CHANGE_BLIP_SCALE syn5_sniper_blips[1] 1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[2] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[2] syn5_sniper_blips[2]
					CHANGE_BLIP_SCALE syn5_sniper_blips[2] 1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[3] syn5_sniper_blips[3]
					CHANGE_BLIP_SCALE syn5_sniper_blips[3] 1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[4] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[4] syn5_sniper_blips[4]
					CHANGE_BLIP_SCALE syn5_sniper_blips[4] 1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[5] 
					ADD_BLIP_FOR_CHAR syn5_tbone_goons[5] syn5_sniper_blips[5]
					CHANGE_BLIP_SCALE syn5_sniper_blips[5] 1
				ENDIF

				//resetting east dude
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[4] 
					SET_CHAR_COORDINATES syn5_tbone_goons[4] -1669.5 1346.0 15.2 
					SET_CHAR_HEADING syn5_tbone_goons[4] 142.0 
				ENDIF
				
				//resetting west roof dude
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
					SET_CHAR_COORDINATES syn5_tbone_goons[1] -1694.7 1369.7 15.9 
					SET_CHAR_HEADING syn5_tbone_goons[1] 129.8 
				ENDIF
				
				SET_CHAR_COORDINATES scplayer -1720.4 1275.5 16.9
				SET_CHAR_HEADING scplayer 328.4
				
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE  
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				 							  
				DELETE_OBJECT syn5_sniper_anim 
				MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER_anim
				REMOVE_ANIMATION MISC
				MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE 
				MARK_CAR_AS_NO_LONGER_NEEDED syn5_dummy_car 
				MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE  
					
				SHUT_ALL_CHARS_UP FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
			
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
			
				PRINT_NOW ( SYN5_02 ) 7000 1 // Clear the roof so that the Triads can proceed.
			  
			  	DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS					
				   WAIT 0
				ENDWHILE 
				IF syn5_goals = 10 
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
				
				timera = 0
				syn5_control_flag = 0
				syn5_goals = 3
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Player shooting the rest of the guards //////////////////////////////////////////////////////// 
	IF syn5_goals = 3	

		///////////////////DEBUG//////////////////////////////////////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[0]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[0] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[0]
			ENDIF 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[1]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[1] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[1]
			ENDIF 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[2]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[2] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[2]
			ENDIF 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[3]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[3] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[3]
			ENDIF 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[4]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[4] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[4]
			ENDIF 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[5]
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[5] FALSE
				EXPLODE_CHAR_HEAD syn5_tbone_goons[5]
			ENDIF 
		ENDIF
		///////////////////DEBUG//////////////////////////////////////

		//waiting for snipers to be killed 
		IF syn5_control_flag = 0

			/////// SPEECH FOR THIS SECTION /////////////////
			
			IF syn5_speech_goals = 0 
				////// GOOD SPEECH ///////////////
				IF syn5_killing_wuzi_men_flag = 0
					IF timera > 7000 
						// Shit they've walked right into it!
						syn5_speech_goals = 3
						syn5_speech_control_flag = 1
						syn5_random_last_label = 2 
						GOSUB syn5_dialogue_setup  
						syn5_killing_wuzi_men_flag = 1
					ENDIF
				ENDIF	

				IF syn5_killing_wuzi_men_flag = 1
					IF syn5_wuzi_men_count < 3
						// One of those Triad boys is down!
						syn5_speech_goals = 3
						syn5_speech_control_flag = 3
						syn5_random_last_label = 4 
						GOSUB syn5_dialogue_setup  
						syn5_killing_wuzi_men_flag = 2
					ENDIF
				ENDIF	

				IF syn5_killing_wuzi_men_flag = 2
					IF syn5_wuzi_men_count < 2
						// Scratch one more Mountain Cloud Boy!
						syn5_speech_goals = 3
						syn5_speech_control_flag = 4
						syn5_random_last_label = 5 
						GOSUB syn5_dialogue_setup  
						syn5_killing_wuzi_men_flag = 3
					ENDIF
				ENDIF	

				IF syn5_speech_goals = 0
					IF syn5_killing_wuzi_men_flag = 3
						// Take 'em out, CJ, they're in the thick of it!
						syn5_speech_goals = 3
						syn5_speech_control_flag = 2
						syn5_random_last_label = 3 
						GOSUB syn5_dialogue_setup  
						syn5_killing_wuzi_men_flag = 4
					ENDIF
				ENDIF	
				 
				IF syn5_killing_wuzi_men_flag = 4
					IF syn5_wuzi_men_count < 1
						// They're getting cut to pieces, CJ, shoot those damn Rifas!
						syn5_speech_goals = 3
						syn5_speech_control_flag = 5
						syn5_random_last_label = 6 
						GOSUB syn5_dialogue_setup  
						syn5_killing_wuzi_men_flag = 5
					ENDIF
				ENDIF
		
				////// BAD SPEECH ///////////////
				
				IF syn5_killing_sniper_flag = 0
					syn5_killing_sniper_flag = 3
				ENDIF
				
				/*

					IF syn5_killing_sniper_count < 6
						// One down...
						syn5_speech_goals = 4
						syn5_speech_control_flag = 0
						syn5_random_last_label = 1 
						GOSUB syn5_dialogue_setup  
						syn5_killing_sniper_flag = 1
					ENDIF
				ENDIF
		
				IF syn5_killing_sniper_flag = 1
					IF syn5_killing_sniper_count < 5
						// That's it, CJ, keep icing those Rifa!
						syn5_speech_goals = 4
						syn5_speech_control_flag = 1
						syn5_random_last_label = 2 
						GOSUB syn5_dialogue_setup  
						syn5_killing_sniper_flag = 2
					ENDIF
				ENDIF
		
				IF syn5_killing_sniper_flag = 2
					IF syn5_killing_sniper_count < 4
						// Just a few more, holmes...
						syn5_speech_goals = 4
						syn5_speech_control_flag = 2
						syn5_random_last_label = 3 
						GOSUB syn5_dialogue_setup  
						syn5_killing_sniper_flag = 3
					ENDIF
				ENDIF
				*/
				
				IF syn5_killing_sniper_flag = 3
					IF syn5_killing_sniper_count < 3
						//EAST DUDES
						IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
						OR NOT IS_CHAR_DEAD syn5_tbone_goons[4]
						OR NOT IS_CHAR_DEAD syn5_tbone_goons[5]
							// Some more to the right..
							syn5_speech_goals = 4
							syn5_speech_control_flag = 3
							syn5_random_last_label = 4 
							GOSUB syn5_dialogue_setup  
							syn5_killing_sniper_flag = 4
						ELSE
							syn5_killing_sniper_flag = 5
						ENDIF
					ENDIF
				ENDIF

				IF syn5_speech_goals = 0
					//player has been told to kill the remaining dudes on the right
					IF syn5_killing_sniper_flag = 4
						//EAST DUDES
						IF IS_CHAR_DEAD syn5_tbone_goons[3] 
						AND IS_CHAR_DEAD syn5_tbone_goons[4]
						AND IS_CHAR_DEAD syn5_tbone_goons[5]
							// Still some on the left, CJ...
							syn5_speech_goals = 4
							syn5_speech_control_flag = 4
							syn5_random_last_label = 5 
							GOSUB syn5_dialogue_setup  
							syn5_killing_sniper_flag = 6
						ENDIF
					ENDIF
				ENDIF

				IF syn5_speech_goals = 0
					//player has already killed all the dudes on the right
					IF syn5_killing_sniper_flag = 5
						IF syn5_killing_sniper_count < 3
							//WEST DUDES
							IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
							OR NOT IS_CHAR_DEAD syn5_tbone_goons[1]
							OR NOT IS_CHAR_DEAD syn5_tbone_goons[2]
								// Still some on the left, CJ...
								syn5_speech_goals = 4
								syn5_speech_control_flag = 4
								syn5_random_last_label = 5 
								GOSUB syn5_dialogue_setup  
								syn5_killing_sniper_flag = 6
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
		
			
			// KILLING WUZI MEN AS TIME TICKS DOWN //
			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0] 
				IF timera > 10000 	    
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[0] FALSE 
					EXPLODE_CHAR_HEAD syn5_wuzi_goons[0]
				ENDIF
			ENDIF 
		
			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1] 
				IF timera > 20000 	    
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[1] FALSE 
					EXPLODE_CHAR_HEAD syn5_wuzi_goons[1]
				ENDIF
			ENDIF 

			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2] 
				IF timera > 30000 	    
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[2] FALSE 
					EXPLODE_CHAR_HEAD syn5_wuzi_goons[2]
				ENDIF
			ENDIF 

			IF NOT IS_CHAR_DEAD wuzi 
				IF timera > 40000 	    
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER wuzi FALSE 
					syn5_goals = 10
				ENDIF
			ENDIF
		
		
			// REMOVING BLIPS AS EACH CHAR DIES AND MARKING THEM AS DEAD
			IF syn5_sniper_number_flag < 6
				IF syn5_sniper_killed_count_flag[syn5_sniper_number_flag] = 0
					IF IS_CHAR_DEAD syn5_tbone_goons[syn5_sniper_number_flag]
						REMOVE_BLIP syn5_sniper_blips[syn5_sniper_number_flag]
						syn5_killing_sniper_count --
						syn5_sniper_killed_count_flag[syn5_sniper_number_flag] = 1
					ENDIF
				ENDIF
				syn5_sniper_number_flag ++
			ELSE
				syn5_sniper_number_flag = 0
			ENDIF


			IF syn5_wuzi_number_flag < 3
				IF syn5_wuzi_men_killed_flag[syn5_wuzi_number_flag] = 0
					IF IS_CHAR_DEAD syn5_wuzi_goons[syn5_wuzi_number_flag]
						syn5_wuzi_men_count --
						syn5_wuzi_men_killed_flag[syn5_wuzi_number_flag] = 1
					ENDIF
				ENDIF
				syn5_wuzi_number_flag ++
			ELSE
				syn5_wuzi_number_flag = 0
			ENDIF
		
		
			////// CONTROLLING THE AI IN THIS SECTION ///////
			GOSUB syn5_controlling_sniping_section_ai
		
			IF IS_CHAR_DEAD syn5_tbone_goons[0] 
				IF IS_CHAR_DEAD syn5_tbone_goons[1] 
					IF IS_CHAR_DEAD syn5_tbone_goons[2] 
						IF IS_CHAR_DEAD syn5_tbone_goons[3] 
							IF IS_CHAR_DEAD syn5_tbone_goons[4] 
								IF IS_CHAR_DEAD syn5_tbone_goons[5] 
									REMOVE_BLIP syn5_sniper_blips[0]
									REMOVE_BLIP syn5_sniper_blips[1]
									REMOVE_BLIP syn5_sniper_blips[2]
									REMOVE_BLIP syn5_sniper_blips[3]
									REMOVE_BLIP syn5_sniper_blips[4]
									REMOVE_BLIP syn5_sniper_blips[5]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[0]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[1]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[2]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[3]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[4]
									MARK_CHAR_AS_NO_LONGER_NEEDED syn5_tbone_goons[5]
									timera = 0 
									syn5_control_flag = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF	

		ENDIF


		//giving a bit of time between killing all the snipers and triggering the next bit
		IF syn5_control_flag = 1
			IF timera > 2000 
				SET_PLAYER_CONTROL player1 OFF	
				CLEAR_PRINTS 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks
				IF syn5_goals = 10 			  
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
				syn5_control_flag = 0
				syn5_goals = 4

			ENDIF
		ENDIF

	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// CUTSCENE OF WUZI DYING  //////////////////////////////////////////////////////////////////////// 
	syn_goals10:
	 
	IF syn5_goals = 10
		GOSUB check_player_is_safe

		//WRITE_DEBUG_WITH_INT player_is_completely_safe player_is_completely_safe
		IF player_is_completely_safe = 1
			IF syn5_control_flag = 0
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1	
				CLEAR_MISSION_AUDIO 2
				syn5_speech_goals = 0

				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				DELETE_CHAR wuzi 
				DELETE_CHAR cesar

				CLEAR_AREA -1673.7 1369.0 7.0 150.0 TRUE
				
				CLEAR_AREA -1677.2 1347.8 16.2 1.0 TRUE 
				CREATE_CHAR PEDTYPE_MISSION1 TRIBOSS -1677.2 1347.8 16.2 wuzi
				SET_CHAR_HEADING wuzi 356.6
				GIVE_WEAPON_TO_CHAR wuzi WEAPONTYPE_TEC9 30000 
				SET_CHAR_DECISION_MAKER wuzi syn5_empty_ped_decision_maker

				LOAD_SCENE -1677.2 1347.8 16.2 

				SET_FIXED_CAMERA_POSITION -1675.3 1349.0 17.0 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1684.1 1343.9 18.5 JUMP_CUT

				//SET_FIXED_CAMERA_POSITION -1676.7 1349.4 17.3 0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT -1678.2 1345.2 17.8 JUMP_CUT

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 

				IF NOT IS_CHAR_DEAD wuzi 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE wuzi KO_shot_stom PED 4.0 FALSE FALSE FALSE TRUE -1
				ENDIF
				
				IF syn5_wuzi_men_count > 0
					// You can break the news to Woozie, holmes.
					syn5_speech_goals = 5
					syn5_speech_control_flag = 3
					syn5_random_last_label = 4 
					GOSUB syn5_dialogue_setup  
				ELSE
					syn5_speech_goals = 5	
					GENERATE_RANDOM_INT_IN_RANGE 0 4 syn5_speech_control_flag
					syn5_random_last_label = syn5_speech_control_flag + 1 
					GOSUB syn5_dialogue_setup  
				ENDIF
				
				timerb = 0
				syn5_control_flag = 1
			ENDIF
		ENDIF

		IF syn5_control_flag = 1 
			IF syn5_speech_goals = 0 
				IF timerb > 4000
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
												
					GET_CHAR_COORDINATES scplayer syn5_x syn5_y syn5_z
					LOAD_SCENE syn5_x syn5_y syn5_z 
				
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SWITCH_WIDESCREEN OFF	
					MAKE_PLAYER_GANG_REAPPEAR
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT 
					SET_PLAYER_CONTROL player1 ON
					
					CLEAR_PRINTS
					PRINT_BIG M_FAIL 5000 1
					PRINT_NOW ( SYN5_05 ) 5500 1 //The Triad Leader has been killed!

					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOTO mission_syn5_failed
				ENDIF 
			ENDIF
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// CUTSCENE SHOWING MEET TAKING PLACE ///////////////////////////////////////////////////////////// 
	IF syn5_goals = 4  
		IF syn5_control_flag = 0

			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1	
			CLEAR_MISSION_AUDIO 2
			syn5_speech_goals = 0
		
			REQUEST_CAR_RECORDING 27
			REQUEST_CAR_RECORDING 28

			REQUEST_MODEL PICADOR
			REQUEST_MODEL tahoma
			REQUEST_MODEL maverick
			REQUEST_MODEL PONY

			REQUEST_MODEL BALLAS1 
			REQUEST_MODEL BALLAS2 

			LOAD_SPECIAL_CHARACTER 2 ryder2
			LOAD_SPECIAL_CHARACTER 3 torino
			LOAD_SPECIAL_CHARACTER 4 tbone
			
			REQUEST_MODEL TEARGAS 
			REQUEST_MODEL AK47
			
			//REQUEST_ANIMATION GRENADE
			
			LOAD_ALL_MODELS_NOW

			SHUT_ALL_CHARS_UP TRUE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
		
			CREATE_CAR PONY -1634.3 1253.4 6.1 syn5_tbones_car
			SET_CAR_HEADING syn5_tbones_car 44.5
			LOCK_CAR_DOORS syn5_tbones_car CARLOCK_LOCKOUT_PLAYER_ONLY
			START_PLAYBACK_RECORDED_CAR syn5_tbones_car 27 
			SKIP_IN_PLAYBACK_RECORDED_CAR syn5_tbones_car 35.0
			
			CLEAR_CHAR_TASKS_IMMEDIATELY wuzi
			SET_CHAR_COORDINATES wuzi -1678.7 1347.5 16.2
			SET_CHAR_HEADING wuzi 32.6  
			SET_CHAR_DECISION_MAKER wuzi syn5_empty_ped_decision_maker
			TASK_PLAY_ANIM wuzi weapon_crouch PED 4.0 TRUE FALSE FALSE FALSE 30000

			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[0]
				SET_CHAR_COORDINATES syn5_wuzi_goons[0] -1686.1 1348.7 16.2
				SET_CHAR_HEADING syn5_wuzi_goons[0] 33.6
				SET_CHAR_DECISION_MAKER syn5_wuzi_goons[0] syn5_empty_ped_decision_maker
				TASK_PLAY_ANIM syn5_wuzi_goons[0] weapon_crouch PED 4.0 TRUE FALSE FALSE FALSE 30000
			ENDIF

			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[1]
				SET_CHAR_COORDINATES syn5_wuzi_goons[1] -1687.7 1347.7 16.2
				SET_CHAR_HEADING syn5_wuzi_goons[1] 60.6
				SET_CHAR_DECISION_MAKER syn5_wuzi_goons[1] syn5_empty_ped_decision_maker
				TASK_PLAY_ANIM syn5_wuzi_goons[1] weapon_crouch PED 4.0 TRUE FALSE FALSE FALSE 30000	
			ENDIF
		
			IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_wuzi_goons[2]
				SET_CHAR_COORDINATES syn5_wuzi_goons[2] -1688.7 1346.1 16.2
				SET_CHAR_HEADING syn5_wuzi_goons[2] 41.8
				SET_CHAR_DECISION_MAKER syn5_wuzi_goons[2] syn5_empty_ped_decision_maker
				TASK_PLAY_ANIM syn5_wuzi_goons[2] weapon_crouch PED 4.0 TRUE FALSE FALSE FALSE 30000	
			ENDIF
		
			CLEAR_AREA -1721.4 1276.2 16.9 5.0 TRUE
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			SET_CHAR_COORDINATES scplayer -1720.4 1275.5 16.9
			SET_CHAR_HEADING scplayer 328.4

			CLEAR_CHAR_TASKS_IMMEDIATELY cesar
			SET_CHAR_COORDINATES cesar -1721.4 1276.2 16.9 	
			SET_CHAR_HEADING cesar 326.3
	
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE syn5_ammo_flag  
			IF syn5_ammo_flag < 24 
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SNIPERRIFLE 24
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE
			ELSE
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE  
			ENDIF

			//TASK_PLAY_ANIM scplayer RIFLE_FIRE PED 1000.0 FALSE FALSE FALSE TRUE 2500
			//TASK_AIM_GUN_AT_COORD scplayer -1715.4 1287.5 16.5 2000

			CLEAR_AREA -1706.4 1327.1 6.4 100.0 TRUE
			SET_CAR_DENSITY_MULTIPLIER 0.0
		
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE syn5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED 
	
			SET_FIXED_CAMERA_POSITION -1722.2 1271.7 20.1 0.0 0.0 0.0  ///this is camera BEHIND plyr and cesar
			POINT_CAMERA_AT_POINT -1709.7 1305.0 7.4 JUMP_CUT
	
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			GOSUB syn5_death_checks
			IF syn5_goals = 10 
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF
		
			// Man, my shooting was fresh!
			syn5_speech_goals = 7
			syn5_speech_control_flag = 0
			syn5_random_last_label = 1 
			GOSUB syn5_dialogue_setup  

			syn5_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			timera = 0 
			syn5_control_flag = 1
		ENDIF

		IF syn5_control_flag = 1
			IF syn5_speech_goals = 0
				IF timera > 4000
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					GOSUB syn5_death_checks
					IF syn5_goals = 10 			  
						GOTO syn_goals10
					ENDIF
					IF syn5_deathcheck_flag = 1
						GOTO mission_syn5_failed
					ENDIF

					IF NOT IS_CAR_DEAD syn5_tbones_car 
						OPEN_CAR_DOOR syn5_tbones_car REAR_LEFT_DOOR
						OPEN_CAR_DOOR syn5_tbones_car REAR_RIGHT_DOOR
					ENDIF
					
					GOSUB syn5_creating_tbone 
					
					IF NOT IS_CHAR_DEAD syn5_tbone 
						OPEN_SEQUENCE_TASK syn5_seq	  
							TASK_GO_STRAIGHT_TO_COORD -1 -1709.4 1345.3 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1697.7 1349.2 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1676.1 1360.3 6.2 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF

					IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
						OPEN_SEQUENCE_TASK syn5_seq	  
							TASK_GO_STRAIGHT_TO_COORD -1 -1709.4 1346.3 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1697.7 1350.2 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1676.1 1361.3 6.2 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[0] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF

					IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
						OPEN_SEQUENCE_TASK syn5_seq	  
							TASK_GO_STRAIGHT_TO_COORD -1 -1709.4 1343.3 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1697.7 1351.2 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1676.1 1362.3 6.2 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[1] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF
					
					IF NOT IS_CHAR_DEAD syn5_tbone_goons[2] 
						OPEN_SEQUENCE_TASK syn5_seq	  
							TASK_GO_STRAIGHT_TO_COORD -1 -1709.4 1342.3 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1697.7 1352.2 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1676.1 1363.3 6.2 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[2] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF
				
					IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
						OPEN_SEQUENCE_TASK syn5_seq	  
							TASK_GO_STRAIGHT_TO_COORD -1 -1709.4 1341.3 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1697.7 1353.2 6.2 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -1676.1 1364.3 6.2 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK syn5_tbone_goons[3] syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
					ENDIF

					GOSUB syn5_creating_ryder
				
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_CAR_DRIVE_TO_COORD -1 syn5_ryders_car -1704.0 1333.5 5.9 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 -1676.9 1360.6 6.2 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq

					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_CAR_DRIVE_TO_COORD -1 syn5_ryder_goon_cars[0] -1706.6 1340.2 5.9 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq

					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_CAR_DRIVE_TO_COORD -1 syn5_ryder_goon_cars[1] -1718.4 1345.7 6.2 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[4] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq

					SET_FIXED_CAMERA_POSITION -1711.0 1343.8 7.2 0.0 0.0 0.0  ///showing tbone and his goons getting the drugs
					POINT_CAMERA_AT_POINT -1726.7 1357.6 9.9 JUMP_CUT

					DO_FADE 2000 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					GOSUB syn5_death_checks
					IF syn5_goals = 10 			  
						GOTO syn_goals10
					ENDIF
					IF syn5_deathcheck_flag = 1
						GOTO mission_syn5_failed
					ENDIF
				
					// Here comes T-Bone....
					syn5_speech_goals = 7
					syn5_speech_control_flag = 2
					syn5_random_last_label = 3 
					GOSUB syn5_dialogue_setup  

					timera = 0
					syn5_control_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		//showing ryder pulling up
		IF syn5_control_flag = 2
			IF timera > 4000

				GOSUB syn5_creating_toreno

				SET_FIXED_CAMERA_POSITION -1698.3 1327.6 7.3  0.0 0.0 0.0		///camera looking at cars pulling up
				POINT_CAMERA_AT_POINT -1701.2 1332.3 7.6 JUMP_CUT

				// And here's that snake Ryder.	| Look at that fool, hanging out with Ballas like they was life long pals. 
				syn5_speech_goals = 7
				syn5_speech_control_flag = 3
				syn5_random_last_label = 5 
				GOSUB syn5_dialogue_setup  

				timera = 0
				syn5_control_flag = 3
			ENDIF
		ENDIF

		
		//waiting until ryder has exited the car
		IF syn5_control_flag = 3
			IF syn5_speech_goals = 0
				IF NOT IS_CHAR_DEAD ryder 
					IF NOT IS_CHAR_IN_ANY_CAR ryder 
						IF NOT IS_CHAR_DEAD syn5_ryder_goons[0] 
							OPEN_SEQUENCE_TASK syn5_seq	  
								TASK_LEAVE_ANY_CAR -1
								TASK_GO_STRAIGHT_TO_COORD -1 -1665.0 1370.9 6.2 PEDMOVE_WALK -1
							CLOSE_SEQUENCE_TASK syn5_seq
							PERFORM_SEQUENCE_TASK syn5_ryder_goons[0] syn5_seq
							CLEAR_SEQUENCE_TASK syn5_seq
						ENDIF
						timera = 0					  
						syn5_control_flag = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF 

		//fade out, moving everyone to the end of the pier and fade back in and show meet taking place
		IF syn5_control_flag = 4 
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
			GOSUB syn5_death_checks		
			IF syn5_goals = 10 
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF

			
			//setting everyone in place! ////////////////////////////////////////////
			//TBONE and GOONS 
			//east of trio
			IF NOT IS_CHAR_DEAD syn5_tbone
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone 
				SET_CHAR_COORDINATES syn5_tbone -1629.2 1414.2 6.2 
				SET_CHAR_HEADING syn5_tbone 87.6 
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE syn5_tbone TRUE
			ENDIF 
		
			//sitting behind tbone
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[0] 
				SET_CHAR_COORDINATES syn5_tbone_goons[0] -1629.3 1412.4 6.2 
				SET_CHAR_HEADING syn5_tbone_goons[0] 76.7 
				TASK_PLAY_ANIM syn5_tbone_goons[0] seat_idle PED 1000.0 FALSE FALSE FALSE TRUE -1
				GIVE_WEAPON_TO_CHAR syn5_tbone_goons[0] WEAPONTYPE_TEC9 30000 
			ENDIF 
	
			//standing behind tbone
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[1]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[1] 
				SET_CHAR_COORDINATES syn5_tbone_goons[1] -1626.5 1414.2 6.2  
				SET_CHAR_HEADING syn5_tbone_goons[1] 116.9 
				GIVE_WEAPON_TO_CHAR syn5_tbone_goons[1] WEAPONTYPE_TEC9 30000 
			ENDIF 
			
			//standing chatting behind tbone  
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[2]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[2] 
				SET_CHAR_COORDINATES syn5_tbone_goons[2] -1628.5 1414.9 6.2  
				SET_CHAR_HEADING syn5_tbone_goons[2] 50.5 
				GIVE_WEAPON_TO_CHAR syn5_tbone_goons[2] WEAPONTYPE_TEC9 30000 
			ENDIF 

			IF NOT IS_CHAR_DEAD syn5_tbone_goons[3]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[3] 
				SET_CHAR_COORDINATES syn5_tbone_goons[3] -1629.7 1416.0 6.2  
				SET_CHAR_HEADING syn5_tbone_goons[3] 222.8 
				GIVE_WEAPON_TO_CHAR syn5_tbone_goons[3] WEAPONTYPE_TEC9 30000 
			ENDIF 
			TASK_CHAT_WITH_CHAR syn5_tbone_goons[2] syn5_tbone_goons[3] TRUE TRUE 
			TASK_CHAT_WITH_CHAR syn5_tbone_goons[3] syn5_tbone_goons[2] FALSE TRUE
			
			
			//RYDER and GANG
			//west of trio
			IF NOT IS_CHAR_DEAD ryder
				CLEAR_CHAR_TASKS_IMMEDIATELY ryder 
				SET_CHAR_COORDINATES ryder -1636.5 1412.5 6.2 
				SET_CHAR_HEADING ryder 280.9 
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE ryder TRUE
			ENDIF 

			//east on bridge above trio
			IF NOT IS_CHAR_DEAD syn5_ryder_goons[0]
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[0] 
				SET_CHAR_COORDINATES syn5_ryder_goons[0] -1634.9 1408.3 6.2 
				SET_CHAR_HEADING syn5_ryder_goons[0] 293.5 
				OPEN_SEQUENCE_TASK syn5_seq
					TASK_GO_STRAIGHT_TO_COORD -1 -1629.6 1411.7 6.2 PEDMOVE_WALK -1
					TASK_PLAY_ANIM -1 idle_chat PED 1000.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_ryder_goons[0] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			ENDIF 
			
			TASK_GREET_PARTNER ryder syn5_tbone 0.5 5
				
			LOAD_SCENE -1629.2 1414.2 6.2
			
			SET_FIXED_CAMERA_POSITION -1637.6 1410.8 8.3 0.0 0.0 0.0 ///looking at the meet 
			POINT_CAMERA_AT_POINT -1626.4 1414.8 6.8 JUMP_CUT
		
			DO_FADE 2000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			GOSUB syn5_death_checks
			IF syn5_goals = 10 			  
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF
		
			// Something ain't right. Where's Toreno?
			syn5_speech_goals = 7
			syn5_speech_control_flag = 7
			syn5_random_last_label = 8 
			GOSUB syn5_dialogue_setup  

			timera = 0					  
			syn5_control_flag = 5
		ENDIF

		IF syn5_control_flag = 5
			IF timera > 3500
				IF NOT IS_CAR_DEAD syn5_toreno_chopper
					START_PLAYBACK_RECORDED_CAR syn5_toreno_chopper 28
					SKIP_IN_PLAYBACK_RECORDED_CAR syn5_toreno_chopper 10.0 
					syn5_control_flag = 6
				ENDIF
			ENDIF		
		ENDIF


		//attaching camera to the helicopter
		IF syn5_control_flag = 6
			IF timera > 6000 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks
				IF syn5_goals = 10 			  
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
			
				IF NOT IS_CHAR_DEAD ryder 
					SET_CHAR_HEADING ryder 150.0
				ENDIF
			
				IF NOT IS_CHAR_DEAD syn5_ryder_goons[0] 
					SET_CHAR_HEADING syn5_ryder_goons[0] 150.0
				ENDIF
				
				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_POS TRUE                       
				CAMERA_PERSIST_TRACK TRUE                   
				CAMERA_SET_VECTOR_MOVE -1657.1 1666.8 3.4 -1599.1 1432.0 15.1 11000 TRUE
				CAMERA_SET_VECTOR_TRACK -1676.2 1618.2 11.4 -1614.1 1428.9 15.4 11000 TRUE

				LOAD_SCENE -1657.1 1666.8 3.4 
				
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks
				IF syn5_goals = 10 			  
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
			
				// Chopper inbound! | That's got to be Toreno. 
				syn5_speech_goals = 7
				syn5_speech_control_flag = 8
				syn5_random_last_label = 10 
				GOSUB syn5_dialogue_setup  
				
				syn5_control_flag = 7
			ENDIF
		ENDIF
		
		//setting heli orientation so the chopper fucks off
		IF syn5_control_flag = 7 
			IF NOT IS_CAR_DEAD syn5_toreno_chopper 
				IF LOCATE_CAR_2D syn5_toreno_chopper -1629.1 1429.5 3.0 3.0 FALSE
					IF IS_PLAYBACK_GOING_ON_FOR_CAR syn5_toreno_chopper 
						STOP_PLAYBACK_RECORDED_CAR syn5_toreno_chopper
					ENDIF
					HELI_GOTO_COORDS syn5_toreno_chopper -1629.1 1429.5 19.3 0.0 19.3

					// Oh shit, he'll see the bodies on the rooftops! 
					syn5_speech_goals = 7
					syn5_speech_control_flag = 10
					syn5_random_last_label = 11 
					GOSUB syn5_dialogue_setup  
					
					syn5_control_flag = 8
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 8 
			IF syn5_speech_goals = 0 
				CAMERA_RESET_NEW_SCRIPTABLES
				IF NOT IS_CAR_DEAD syn5_toreno_chopper
					SET_HELI_ORIENTATION syn5_toreno_chopper 270.0
				   	HELI_GOTO_COORDS syn5_toreno_chopper -615.5 1513.9 54.8 0.0 25.5
					SET_FIXED_CAMERA_POSITION -1647.6 1409.7 8.0 0.0 0.0 0.0 ///looking at the meet 
					POINT_CAMERA_AT_POINT -1590.8 1426.7 13.8 JUMP_CUT
					timera = 0
					syn5_control_flag = 9
				ENDIF
			ENDIF
		ENDIF
			
		//having guys run off and through tear gas
		IF syn5_control_flag = 9
			IF timera > 1000
				IF NOT IS_CHAR_DEAD syn5_tbone 
					CLEAR_CHAR_TASKS syn5_tbone  
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1628.8 1421.7 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 
				
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
					CLEAR_CHAR_TASKS syn5_tbone_goons[0]  
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1626.8 1419.7 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 
				
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
					CLEAR_CHAR_TASKS syn5_tbone_goons[1]  
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1630.8 1423.7 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone_goons[1] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 
			
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[2] 
					CLEAR_CHAR_TASKS syn5_tbone_goons[2]  
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1632.8 1425.7 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 
			
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
					CLEAR_CHAR_TASKS syn5_tbone_goons[3]  
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1634.0 1423.0 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_tbone_goons[3] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 

				IF NOT IS_CHAR_DEAD ryder
					GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_TEARGAS 1  
					SET_CURRENT_CHAR_WEAPON ryder WEAPONTYPE_TEARGAS 
					TASK_SHOOT_AT_COORD ryder -1652.5 1396.0 6.2 500

				ENDIF 
		
				IF NOT IS_CHAR_DEAD syn5_ryder_goons[0] 
					GIVE_WEAPON_TO_CHAR syn5_ryder_goons[0] WEAPONTYPE_TEARGAS 1  
					SET_CURRENT_CHAR_WEAPON syn5_ryder_goons[0] WEAPONTYPE_TEARGAS 

					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_PAUSE -1 500
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 
			
				// Too late, man, he's bugging out! 
				syn5_speech_goals = 7
				syn5_speech_control_flag = 11
				syn5_random_last_label = 12 
				GOSUB syn5_dialogue_setup  
			
				timera = 0 
				syn5_control_flag = 10
			ENDIF
		ENDIF

		IF syn5_control_flag = 10 
			//throwing the second canister
			IF NOT IS_CHAR_DEAD syn5_ryder_goons[0]
				GET_SCRIPT_TASK_STATUS syn5_ryder_goons[0] PERFORM_SEQUENCE_TASK task_status	
				IF task_status = FINISHED_TASK
					TASK_SHOOT_AT_COORD syn5_ryder_goons[0] -1653.1 1400.0 7.0 500
					syn5_control_flag = 11
				ENDIF
			ENDIF	
		ENDIF		


		//creating teargas in correct position 
		IF syn5_control_flag = 11
			IF syn5_speech_goals = 0 
				IF timera > 2000
					IF NOT IS_CHAR_DEAD ryder
						GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK task_status 
						IF task_status = FINISHED_TASK
							OPEN_SEQUENCE_TASK syn5_seq	  
								TASK_PAUSE -1 500
								TASK_GO_STRAIGHT_TO_COORD -1 -1649.1 1395.0 7.0 PEDMOVE_RUN -1
							CLOSE_SEQUENCE_TASK syn5_seq
							PERFORM_SEQUENCE_TASK ryder syn5_seq
							CLEAR_SEQUENCE_TASK syn5_seq

							// Lets get those motherfuckers!  
							syn5_speech_goals = 7
							syn5_speech_control_flag = 12
							syn5_random_last_label = 14 
							GOSUB syn5_dialogue_setup  

							SET_FIXED_CAMERA_POSITION -1665.9 1386.8 11.0 0.0 0.0 0.0 ///looking at the meet 
							POINT_CAMERA_AT_POINT -1641.2 1407.9 9.9 JUMP_CUT
							timera = 0
							syn5_control_flag = 12
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF 

		IF syn5_control_flag = 12
			IF timera > 1500 
				CREATE_FX_SYSTEM teargasAD -1656.5 1398.0 6.2 TRUE syn5_teargas[0] //ryders
				PLAY_FX_SYSTEM syn5_teargas[0]

				CREATE_FX_SYSTEM teargasAD -1656.0 1393.0 7.0 TRUE syn5_teargas[1] //ryders mates
				PLAY_FX_SYSTEM syn5_teargas[1]
				syn5_control_flag = 13
			ENDIF
		ENDIF

		//finish
		IF syn5_control_flag = 13
			IF timera > 5000
				syn5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				
				//if cutscene has been skipped ////////////////////////////////////////////
				IF syn5_skip_cutscene_flag = 1
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_PRINTS
					syn5_speech_goals = 0
				ENDIF

				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks
				IF syn5_goals = 10 
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF

				//if cutscene has been skipped ////////////////////////////////////////////
				IF syn5_skip_cutscene_flag = 1
					
					CAMERA_RESET_NEW_SCRIPTABLES
					GOSUB syn5_creating_tbone
					GOSUB syn5_creating_ryder

					IF NOT IS_CAR_DEAD syn5_ryders_car 
						SET_CAR_COORDINATES syn5_ryders_car -1702.9 1335.5 5.9 
						SET_CAR_HEADING syn5_ryders_car 271.8
					ENDIF

					IF NOT IS_CAR_DEAD syn5_ryder_goon_cars[0] 
						SET_CAR_COORDINATES syn5_ryder_goon_cars[0] -1706.6 1340.2 5.9 
						SET_CAR_HEADING syn5_ryder_goon_cars[0] 271.8
					ENDIF

					IF NOT IS_CAR_DEAD syn5_ryder_goon_cars[1] 
						SET_CAR_COORDINATES syn5_ryder_goon_cars[1] -1718.4 1345.7 6.2  
						SET_CAR_HEADING syn5_ryder_goon_cars[1] 231.8
					ENDIF

					IF NOT IS_CAR_DEAD syn5_tbones_car
						OPEN_CAR_DOOR syn5_tbones_car REAR_LEFT_DOOR
						OPEN_CAR_DOOR syn5_tbones_car REAR_RIGHT_DOOR
				
						IF IS_PLAYBACK_GOING_ON_FOR_CAR syn5_tbones_car 
							STOP_PLAYBACK_RECORDED_CAR syn5_tbones_car
						ENDIF 	
						SET_CAR_COORDINATES syn5_tbones_car -1722.3 1365.6 6.2
						SET_CAR_HEADING syn5_tbones_car 325.6  
					ENDIF
					REMOVE_CAR_RECORDING 27
					
					KILL_FX_SYSTEM syn5_teargas[0] 
					KILL_FX_SYSTEM syn5_teargas[1] 

					CREATE_FX_SYSTEM teargasAD -1656.5 1398.0 6.2 TRUE syn5_teargas[0] //ryders
					PLAY_FX_SYSTEM syn5_teargas[0]

					CREATE_FX_SYSTEM teargasAD -1656.0 1393.0 7.0 TRUE syn5_teargas[1] //ryders mates
					PLAY_FX_SYSTEM syn5_teargas[1]
				ENDIF

				CLEAR_CHAR_TASKS_IMMEDIATELY wuzi
				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			

					
				//CESAR 			
				GIVE_WEAPON_TO_CHAR cesar WEAPONTYPE_TEC9 30000
				SET_CHAR_DECISION_MAKER cesar syn5_ped_decisions

				//WUZI
				TASK_SHOOT_AT_COORD wuzi -1685.2 1363.1 7.8 -1

				//ANY REMAINING MATES OF WUZI
				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0]
					TASK_SHOOT_AT_COORD syn5_wuzi_goons[0] -1682.2 1364.1 7.8 -1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1]
					TASK_SHOOT_AT_COORD syn5_wuzi_goons[1] -1694.2 1355.1 7.8 -1
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2]
					TASK_SHOOT_AT_COORD syn5_wuzi_goons[2] -1704.2 1343.1 7.8 -1
			   	ENDIF

				//RYDER  (NOW T-BONE)
				IF NOT IS_CHAR_DEAD syn5_tbone
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone 
					IF NOT IS_CHAR_IN_ANY_CAR syn5_tbone 
						SET_CHAR_COORDINATES syn5_tbone -1618.9 1401.8 6.2 
					ELSE
						WARP_CHAR_FROM_CAR_TO_COORD syn5_tbone -1618.9 1401.8 6.2
					ENDIF 
					SET_CHAR_HEADING syn5_tbone 110.5 
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE syn5_tbone FALSE
					/*
					//bush 1 
					OPEN_SEQUENCE_TASK syn5_ryder_seq1
						TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1618.3 1400.6 6.2 PEDMOVE_RUN 1.0 0.5 scplayer
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
						TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 4000
					CLOSE_SEQUENCE_TASK syn5_ryder_seq1
					*/
					//bush 2
					OPEN_SEQUENCE_TASK syn5_ryder_seq2
						TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1619.3 1412.1 6.2 PEDMOVE_RUN 1.0 0.5 scplayer
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
						TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 4000
					CLOSE_SEQUENCE_TASK syn5_ryder_seq2

					//bush 3
					OPEN_SEQUENCE_TASK syn5_ryder_seq3
						TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1635.0 1430.4 6.2 PEDMOVE_RUN 1.0 0.5 scplayer
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
						TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 4000
					CLOSE_SEQUENCE_TASK syn5_ryder_seq3
					/*
					//bush 4
					OPEN_SEQUENCE_TASK syn5_ryder_seq4
						TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1647.3 1431.5 6.2 PEDMOVE_RUN 1.0 0.5 scplayer
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
						TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 4000
					CLOSE_SEQUENCE_TASK syn5_ryder_seq4
					*/
					//playing sequences 
					OPEN_SEQUENCE_TASK syn5_ryder_seq5
						//PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq1 
						PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq2 
						PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq3 
						//PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq4 
						PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq3 
						PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq2 
						//PERFORM_SEQUENCE_TASK -1 syn5_ryder_seq1 
						SET_SEQUENCE_TO_REPEAT syn5_ryder_seq5 1
					CLOSE_SEQUENCE_TASK syn5_ryder_seq5
					PERFORM_SEQUENCE_TASK syn5_tbone syn5_ryder_seq5

					//CLEAR_SEQUENCE_TASK syn5_ryder_seq1
					CLEAR_SEQUENCE_TASK syn5_ryder_seq2
					CLEAR_SEQUENCE_TASK syn5_ryder_seq3
					//CLEAR_SEQUENCE_TASK syn5_ryder_seq4
					CLEAR_SEQUENCE_TASK syn5_ryder_seq5
					ADD_BLIP_FOR_CHAR syn5_tbone syn5_blip
				ENDIF 




				//ryder's mate
				IF NOT IS_CHAR_DEAD syn5_ryder_goons[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[0] 
					SET_CHAR_COORDINATES syn5_ryder_goons[0] -1631.2 1410.3 6.2 
					SET_CHAR_HEADING syn5_ryder_goons[0] 137.6
					//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[0] TRUE  
				
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						TASK_TOGGLE_DUCK -1 TRUE
						TASK_KILL_CHAR_ON_FOOT -1 scplayer	
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[0] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF 

				//rest of ryders goons
				CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1697.9 1345.7 6.1 syn5_ryder_goons[1]
				SET_CHAR_ACCURACY syn5_ryder_goons[1] syn5_accuracy
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[1] TRUE
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[1] 
				GIVE_WEAPON_TO_CHAR syn5_ryder_goons[1] WEAPONTYPE_TEC9 30000  
				SET_CHAR_HEADING syn5_ryder_goons[1] 146.8
				SET_CHAR_DECISION_MAKER syn5_ryder_goons[1] syn5_ped_decisions
				//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[1] FALSE
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_ryder_goons[1] TRUE
				OPEN_SEQUENCE_TASK syn5_seq
					//TASK_KILL_CHAR_ON_FOOT -1 scplayer 
					TASK_SHOOT_AT_COORD -1 -1684.9 1353.3 13.6 -2 
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_ryder_goons[1] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			
				IF NOT IS_CHAR_DEAD syn5_ryder_goons[2]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[2] 
					SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[2] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_ryder_goons[2] TRUE
					OPEN_SEQUENCE_TASK syn5_seq
						//TASK_GO_STRAIGHT_TO_COORD -1 -1702.6 1347.5 6.2 PEDMOVE_RUN -1 
						//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1694.9 1355.9 8.8 PEDMOVE_RUN 0.5 2.0 scplayer 
						//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1690.9 1360.7 8.8 PEDMOVE_RUN 0.5 2.0 scplayer 
						//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1690.4 1367.5 8.8 PEDMOVE_RUN 0.5 2.0 scplayer 
						
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1690.4 1367.5 8.8 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 230.5
						//TASK_KILL_CHAR_ON_FOOT -1 scplayer 
						TASK_SHOOT_AT_COORD -1 -1684.9 1353.3 13.6 -2 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[2] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_ryder_goons[3]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[3] 
					SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[3] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_ryder_goons[3] TRUE
					OPEN_SEQUENCE_TASK syn5_seq
						//TASK_GO_STRAIGHT_TO_COORD -1 -1702.6 1347.5 6.2 PEDMOVE_RUN -1 
						//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1694.9 1355.9 8.8 PEDMOVE_RUN 0.5 2.0 scplayer 
						//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1690.9 1363.8 8.8 PEDMOVE_RUN 0.5 2.0 scplayer 
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1690.9 1363.8 8.8 PEDMOVE_RUN -1 
						TASK_ACHIEVE_HEADING -1 230.5
						//TASK_KILL_CHAR_ON_FOOT -1 scplayer 
						TASK_SHOOT_AT_COORD -1 -1684.9 1353.3 13.6 -2 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[3] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD syn5_ryder_goons[4]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[4] 
					SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[4] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_ryder_goons[4] TRUE
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1698.8 1352.4 6.5 PEDMOVE_RUN -1 
						TASK_ACHIEVE_HEADING -1 251.5
						//TASK_KILL_CHAR_ON_FOOT -1 scplayer 
						TASK_SHOOT_AT_COORD -1 -1684.9 1353.3 13.6 -2 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[4] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD syn5_ryder_goons[5]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[5] 
					SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[5] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_ryder_goons[5] TRUE
					OPEN_SEQUENCE_TASK syn5_seq
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1682.5 1364.4 6.2 PEDMOVE_RUN -1 
						TASK_ACHIEVE_HEADING -1 182.5
						//TASK_KILL_CHAR_ON_FOOT -1 scplayer 
						TASK_SHOOT_AT_COORD -1 -1684.9 1353.3 13.6 -2 
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK syn5_ryder_goons[5] syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				ENDIF

				CREATE_FX_SYSTEM teargasAD -1657.8 1405.0 7.0 TRUE syn5_teargas[2] //left of ryder when looking at it from cutscene
				PLAY_FX_SYSTEM syn5_teargas[2]

				CREATE_FX_SYSTEM teargasAD -1643.3 1390.7 7.0 TRUE syn5_teargas[3] //right of ryder when looking at it from cutscene
				PLAY_FX_SYSTEM syn5_teargas[3]

				/*
				CREATE_FX_SYSTEM teargasAD -1671.1 1421.7 7.0 TRUE syn5_teargas[4] //left of pier 69 when looking at it from cutscene
				PLAY_FX_SYSTEM syn5_teargas[4]

				CREATE_FX_SYSTEM teargasAD -1631.1 1371.6 7.0 TRUE syn5_teargas[5] //right of pier 69 when looking at it from cutscene
				PLAY_FX_SYSTEM syn5_teargas[5]
				*/
				//next to parasols on the right as you look FROM ryder's position
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[0] 
					SET_CHAR_COORDINATES syn5_tbone_goons[0] -1647.7 1421.8 6.2 
					SET_CHAR_HEADING syn5_tbone_goons[0] 186.5
					SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[0] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_tbone_goons[0] TRUE
					GIVE_WEAPON_TO_CHAR syn5_tbone_goons[0] WEAPONTYPE_TEC9 30000 
					TASK_KILL_CHAR_ON_FOOT syn5_tbone_goons[0] scplayer
				ENDIF 

				//in the middle of the shops.. 
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[1]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[1] 
					SET_CHAR_COORDINATES syn5_tbone_goons[1] -1672.6 1373.9 8.8  
					SET_CHAR_HEADING syn5_tbone_goons[1] 136.4 
					GIVE_WEAPON_TO_CHAR syn5_tbone_goons[1] WEAPONTYPE_TEC9 30000 
					TASK_KILL_CHAR_ON_FOOT syn5_tbone_goons[1] scplayer
				ENDIF 
					 

				//next to parasols on the left as you look from ryders position
				IF NOT IS_CHAR_DEAD syn5_tbone_goons[2]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[2] 
					SET_CHAR_COORDINATES syn5_tbone_goons[2] -1631.8 1398.7 6.2  
					SET_CHAR_HEADING syn5_tbone_goons[2] 105.9 
					GIVE_WEAPON_TO_CHAR syn5_tbone_goons[2] WEAPONTYPE_TEC9 30000 
					TASK_KILL_CHAR_ON_FOOT syn5_tbone_goons[2] scplayer
				ENDIF 

				IF NOT IS_CHAR_DEAD syn5_tbone_goons[3]
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[3] 
					SET_CHAR_COORDINATES syn5_tbone_goons[3] -1658.7 1387.3 6.2  
					SET_CHAR_HEADING syn5_tbone_goons[3] 144.5 
					GIVE_WEAPON_TO_CHAR syn5_tbone_goons[3] WEAPONTYPE_TEC9 30000 
					SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[3] FALSE
					SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_tbone_goons[3] TRUE
					TASK_KILL_CHAR_ON_FOOT syn5_tbone_goons[3] scplayer
				ENDIF 
			
				//RANDOM GOONS ADDED IN MIDDLE OF PIER 69
				//on top of last boardwalk before player reaches ryder
				GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1645.8 1401.5 8.8 syn5_random_goons[0]	
				SET_CHAR_ACCURACY syn5_random_goons[0] syn5_accuracy
				SET_CHAR_HEADING syn5_random_goons[0] 129.4
				SET_CHAR_DECISION_MAKER syn5_random_goons[0] syn5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_random_goons[0] TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_random_goons[0] TRUE
				GIVE_WEAPON_TO_CHAR syn5_random_goons[0] WEAPONTYPE_TEC9 30000 
				TASK_KILL_CHAR_ON_FOOT syn5_random_goons[0] scplayer

				//behind bin on the left as you look towards sniping point
				GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1670.5 1364.8 6.2 syn5_random_goons[1]	
				SET_CHAR_ACCURACY syn5_random_goons[1] syn5_accuracy
				SET_CHAR_HEADING syn5_random_goons[1] 129.4
				SET_CHAR_DECISION_MAKER syn5_random_goons[1] syn5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_random_goons[1] TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_random_goons[1] TRUE
				GIVE_WEAPON_TO_CHAR syn5_random_goons[1] WEAPONTYPE_TEC9 30000 
				TASK_KILL_CHAR_ON_FOOT syn5_random_goons[1] scplayer

				//behind bin on the right as you look towards sniping point
				GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1687.5 1367.8 6.2 syn5_random_goons[2]	
				SET_CHAR_ACCURACY syn5_random_goons[2] syn5_accuracy
				SET_CHAR_HEADING syn5_random_goons[2] 124.5
				SET_CHAR_DECISION_MAKER syn5_random_goons[2] syn5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_random_goons[2] TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_random_goons[2] TRUE
				GIVE_WEAPON_TO_CHAR syn5_random_goons[2] WEAPONTYPE_TEC9 30000 
				TASK_KILL_CHAR_ON_FOOT syn5_random_goons[2] scplayer

				//on top of middle boardwalk
				GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1657.3 1379.8 8.8 syn5_random_goons[3]	
				SET_CHAR_ACCURACY syn5_random_goons[3] syn5_accuracy
				SET_CHAR_HEADING syn5_random_goons[3] 140.5
				SET_CHAR_DECISION_MAKER syn5_random_goons[3] syn5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE syn5_random_goons[3] TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_random_goons[3] TRUE
				GIVE_WEAPON_TO_CHAR syn5_random_goons[3] WEAPONTYPE_TEC9 30000 
				TASK_KILL_CHAR_ON_FOOT syn5_random_goons[3] scplayer

				DELETE_CAR syn5_toreno_chopper 
				DELETE_CHAR ryder 
				//DELETE_CHAR syn5_tbone 
				REMOVE_CAR_RECORDING 28
				
				UNLOAD_SPECIAL_CHARACTER 2
				UNLOAD_SPECIAL_CHARACTER 3
				//UNLOAD_SPECIAL_CHARACTER 4
				REMOVE_ANIMATION GRENADE

				MARK_CAR_AS_NO_LONGER_NEEDED syn5_toreno_chopper
				MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
				MARK_MODEL_AS_NO_LONGER_NEEDED PICADOR
				MARK_MODEL_AS_NO_LONGER_NEEDED tahoma
				MARK_MODEL_AS_NO_LONGER_NEEDED PONY
				MARK_CAR_AS_NO_LONGER_NEEDED syn5_dummy_car
				MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE

				SET_CAR_DENSITY_MULTIPLIER 1.0

				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE syn5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 0.0 100.0 0.0 0.0 FALSE TRUE 	

				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE syn5_ped_decisions EVENT_DEAD_PED TASK_COMPLEX_INVESTIGATE_DEAD_PED 100.0 0.0 0.0 0.0 FALSE TRUE 	
				
				LOAD_SCENE -1720.4 1275.5 16.9
				
				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				SET_GROUP_MEMBER Players_Group cesar
				SET_GROUP_DEFAULT_TASK_ALLOCATOR Players_Group DEFAULT_TASK_ALLOCATOR_FOLLOW_LIMITED				
				syn5_flag_cesar_in_group = 1
				
				SHUT_ALL_CHARS_UP FALSE
			
				//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
				CLEAR_PRINTS
				PRINT_NOW ( SYN5_06 ) 4000 1 //Find and kill Ryder!

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks
				IF syn5_goals = 10 			  
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
			
				syn5_control_flag = 0
				syn5_goals = 5
			ENDIF
		ENDIF		
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// WAITING FOR THE PLAYER TO KILL RYDER /////////////////////////////////////////////////////////// 

	IF syn5_goals = 5
		IF syn5_control_flag = 0 

			//debug to stop cunting chars getting created for no reason 
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1708.6 1331.1 200.0 200.0 FALSE
				SET_PED_DENSITY_MULTIPLIER 0.0
			ELSE
				SET_PED_DENSITY_MULTIPLIER 1.0
			ENDIF
			
			GOSUB syn5_cesar_group 
			
			///////////////////DEBUG//////////////////////////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
				IF NOT IS_CHAR_DEAD syn5_tbone
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone FALSE
					EXPLODE_CHAR_HEAD syn5_tbone
				ENDIF
			ENDIF
			///////////////////DEBUG//////////////////////////////////////
			
			//GOSUB syn5_teargas_effect	  ----- debug.... switch back on drunken filter
			
			IF IS_CHAR_DEAD syn5_tbone 
				GOSUB check_player_is_safe
				IF player_is_completely_safe = 1

					CLEAR_PRINTS
					REMOVE_BLIP syn5_blip 

					DO_FADE 1000 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE
					GOSUB syn5_death_checks		
					IF syn5_goals = 10 
						GOTO syn_goals10
					ENDIF
					IF syn5_deathcheck_flag = 1
						GOTO mission_syn5_failed
					ENDIF

					REQUEST_ANIMATION DANCING
					REQUEST_ANIMATION KISSING
					REQUEST_ANIMATION MISC
					REQUEST_ANIMATION RYDER
					REQUEST_MODEL DYN_WOODPILE2
					REQUEST_MODEL SPEEDER
					LOAD_SPECIAL_CHARACTER 2 ryder2

					LOAD_ALL_MODELS_NOW 
					syn5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 1  
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1	
			CLEAR_MISSION_AUDIO 2
			syn5_speech_goals = 0
			
			SET_PLAYER_DRUNKENNESS player1 0 
		
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			SHUT_ALL_CHARS_UP TRUE
			
			CLEAR_AREA -1629.438 1422.648 6.2 60.0 TRUE 
			LOAD_SCENE -1629.438 1422.648 6.2 

			//ryder (NOW TBONE)
			DELETE_CHAR syn5_tbone
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL04 -1629.438 1422.648 6.2 syn5_tbone
			SET_CHAR_HEALTH syn5_tbone 500  
			ADD_ARMOUR_TO_CHAR syn5_tbone 100
			SET_CHAR_ACCURACY syn5_tbone syn5_accuracy
			SET_CHAR_HEADING syn5_tbone 260.0  
			//SET_ANIM_GROUP_FOR_CHAR ryder drunkman
			SET_CHAR_PROOFS syn5_tbone TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_BLEEDING syn5_tbone TRUE
			SET_CHAR_DECISION_MAKER syn5_tbone syn5_empty_ped_decision_maker
			
			OPEN_SEQUENCE_TASK syn5_seq
				//TASK_PAUSE -1 150	  
				TASK_PLAY_ANIM -1 RYD_Die_PT1 RYDER 1000.0 FALSE TRUE TRUE FALSE -1
			CLOSE_SEQUENCE_TASK syn5_seq
			PERFORM_SEQUENCE_TASK syn5_tbone syn5_seq
			CLEAR_SEQUENCE_TASK syn5_seq

			//player
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer -1639.9 1410.3 6.2
			SET_CHAR_HEADING scplayer 325.8
			
			syn5_ammo_flag = 0
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_MP5 syn5_ammo_flag 
			IF syn5_ammo_flag > 0
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MP5
			ELSE
				GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_MICRO_UZI syn5_ammo_flag	
				IF syn5_ammo_flag > 0
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MICRO_UZI
				ELSE
					GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_TEC9 syn5_ammo_flag  
					IF syn5_ammo_flag = 0 
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_TEC9 300
					ENDIF
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_TEC9 
				ENDIF
			ENDIF
			
			OPEN_SEQUENCE_TASK syn5_seq	  
				TASK_GO_STRAIGHT_TO_COORD -1 -1632.2 1419.3 6.2 PEDMOVE_WALK -1 
			CLOSE_SEQUENCE_TASK syn5_seq
			PERFORM_SEQUENCE_TASK scplayer syn5_seq
			CLEAR_SEQUENCE_TASK syn5_seq

			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA 

			//cesar
			IF IS_GROUP_MEMBER cesar Players_Group
				REMOVE_CHAR_FROM_GROUP cesar 
			ENDIF
		
			CLEAR_CHAR_TASKS_IMMEDIATELY cesar
			SET_CHAR_COORDINATES cesar -1638.1 1408.8 6.2
			SET_CHAR_HEADING cesar 325.8
			SET_CHAR_DECISION_MAKER cesar syn5_empty_ped_decision_maker
			GIVE_WEAPON_TO_CHAR cesar WEAPONTYPE_TEC9 30000
			OPEN_SEQUENCE_TASK syn5_seq	  
				TASK_GO_STRAIGHT_TO_COORD -1 -1630.5 1417.9 6.2 PEDMOVE_WALK -1 
			CLOSE_SEQUENCE_TASK syn5_seq
			PERFORM_SEQUENCE_TASK cesar syn5_seq
			CLEAR_SEQUENCE_TASK syn5_seq

			//wuzi
			CLEAR_CHAR_TASKS_IMMEDIATELY wuzi
			SET_CHAR_COORDINATES wuzi -1645.7 1409.0 6.2
			SET_CHAR_HEADING wuzi 328.5
			SET_CHAR_DECISION_MAKER wuzi syn5_empty_ped_decision_maker
			GIVE_WEAPON_TO_CHAR wuzi WEAPONTYPE_TEC9 30000
			OPEN_SEQUENCE_TASK syn5_seq	  
				TASK_GO_STRAIGHT_TO_COORD -1 -1640.2 1414.0 6.2 PEDMOVE_WALK -1 
				TASK_TURN_CHAR_TO_FACE_CHAR -1 syn5_tbone 
			CLOSE_SEQUENCE_TASK syn5_seq
			PERFORM_SEQUENCE_TASK wuzi syn5_seq
			CLEAR_SEQUENCE_TASK syn5_seq
	
			//the goons left alive
			IF NOT IS_CHAR_DEAD syn5_ryder_goons[0]	
				SET_CHAR_DECISION_MAKER syn5_ryder_goons[0] syn5_empty_ped_decision_maker
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_ryder_goons[0]
				OPEN_SEQUENCE_TASK syn5_seq	  
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_HANDS_UP -1 50000
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_ryder_goons[0] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			ENDIF 		
				
			DELETE_CHAR syn5_ryder_goons[1] 
			DELETE_CHAR syn5_ryder_goons[2] 
			DELETE_CHAR syn5_ryder_goons[3] 
			DELETE_CHAR syn5_ryder_goons[4] 
			DELETE_CHAR syn5_ryder_goons[5] 
			
			IF NOT IS_CHAR_DEAD syn5_tbone_goons[0]
				SET_CHAR_DECISION_MAKER syn5_tbone_goons[0] syn5_empty_ped_decision_maker
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[0]
				OPEN_SEQUENCE_TASK syn5_seq	  
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_HANDS_UP -1 50000
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_tbone_goons[0] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			ENDIF 		
			
			DELETE_CHAR syn5_tbone_goons[1]

			IF NOT IS_CHAR_DEAD syn5_tbone_goons[2]
				SET_CHAR_DECISION_MAKER syn5_tbone_goons[2] syn5_empty_ped_decision_maker
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[2]
				OPEN_SEQUENCE_TASK syn5_seq	  
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_HANDS_UP -1 50000
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_tbone_goons[2] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			ENDIF 		

			IF NOT IS_CHAR_DEAD syn5_tbone_goons[3]
				SET_CHAR_DECISION_MAKER syn5_tbone_goons[3] syn5_empty_ped_decision_maker
				CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone_goons[3]
				OPEN_SEQUENCE_TASK syn5_seq	  
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_HANDS_UP -1 50000
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK syn5_tbone_goons[3] syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
			ENDIF 		

			DELETE_CHAR syn5_wuzi_goons[0]
			DELETE_CHAR syn5_wuzi_goons[1]
			DELETE_CHAR syn5_wuzi_goons[2]

			//t-bone (NOW RYDER)
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 -1633.1 1394.1 6.2 ryder
			SET_CHAR_ACCURACY ryder syn5_accuracy
			SET_CHAR_HEADING ryder 268.0  
			//SET_CHAR_PROOFS syn5_tbone TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_DECISION_MAKER ryder syn5_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER ryder TRUE
			SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
			ADD_ARMOUR_TO_CHAR ryder 100
			//GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_AK47 30000
			
			//party go-ers
			//dancers
			CREATE_RANDOM_CHAR -1502.8 1379.5 3.1 syn5_party_dudes[0] 
			SET_CHAR_HEADING syn5_party_dudes[0] 123.3 
			TASK_PLAY_ANIM syn5_party_dudes[0] DAN_Loop_A DANCING 4.0 TRUE FALSE FALSE FALSE -1
			
			CREATE_RANDOM_CHAR -1503.2 1377.3 3.1 syn5_party_dudes[1] 
			SET_CHAR_HEADING syn5_party_dudes[1] 37.1 
			TASK_PLAY_ANIM syn5_party_dudes[1] DAN_Loop_A DANCING 4.0 TRUE FALSE FALSE FALSE -1
			
			CREATE_RANDOM_CHAR -1506.0 1375.2 3.1 syn5_party_dudes[2] 
			SET_CHAR_HEADING syn5_party_dudes[2] 291.9
			TASK_PLAY_ANIM syn5_party_dudes[2] DAN_Loop_A DANCING 4.0 TRUE FALSE FALSE FALSE -1
					
			//snoggers
			CREATE_RANDOM_CHAR -1509.2 1379.3 2.8 syn5_party_dudes[3] 
		
			CREATE_RANDOM_CHAR -1510.2 1379.3 2.8 syn5_party_dudes[4] 
			
			TASK_TURN_CHAR_TO_FACE_CHAR syn5_party_dudes[3] syn5_party_dudes[4] 
			TASK_PLAY_ANIM syn5_party_dudes[3] Grlfrd_Kiss_03 KISSING 4.0 TRUE FALSE FALSE FALSE -1

			TASK_TURN_CHAR_TO_FACE_CHAR syn5_party_dudes[4] syn5_party_dudes[3] 
			TASK_PLAY_ANIM syn5_party_dudes[4] Playa_Kiss_03 KISSING 4.0 TRUE FALSE FALSE FALSE -1

		
			START_SCRIPT_FIRE -1505.0 1379.2 2.1 0 1 syn5_fire
			
			CREATE_OBJECT_NO_OFFSET DYN_WOODPILE2 -1505.0 1379.2 2.1 syn5_woodpile 
			SET_OBJECT_SCALE syn5_woodpile 0.5 
			SET_OBJECT_COLLISION_DAMAGE_EFFECT syn5_woodpile FALSE

			//boats
			//t-bones 
			CREATE_CAR speeder -1504.0 1388.7 0.0 syn5_boat[0]
			SET_CAR_HEADING syn5_boat[0] 282.0 
			ANCHOR_BOAT syn5_boat[0] TRUE 
			SET_CAR_STRAIGHT_LINE_DISTANCE syn5_boat[0] 255
	
			//players
			CREATE_CAR speeder -1519.9 1382.3 0.0 syn5_boat[1]
			SET_CAR_HEADING syn5_boat[1] 314.0 
			ANCHOR_BOAT syn5_boat[1] TRUE 
		
			SET_FIXED_CAMERA_POSITION -1643.8 1408.3 7.6 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1631.1 1416.7 7.9 JUMP_CUT
			
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
			GOSUB syn5_death_checks		
			IF syn5_goals = 10 
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF

			syn5_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			
			// Mendez, I see you, Rifa motherfucker!
			syn5_speech_goals = 8
			syn5_speech_control_flag = 0
			syn5_random_last_label = 1 
			GOSUB syn5_dialogue_setup  

			timera = 0

			syn5_control_flag = 2
		ENDIF

		IF syn5_control_flag = 2
			IF NOT IS_CHAR_DEAD syn5_tbone
				IF LOCATE_CHAR_ANY_MEANS_2D syn5_tbone -1626.6 1421.8 1.0 1.0 FALSE
					CLEAR_CHAR_TASKS_IMMEDIATELY syn5_tbone
					SET_CHAR_COORDINATES syn5_tbone -1624.4 1423.8 6.2
					SET_CHAR_HEADING syn5_tbone 135.0  
					TASK_PLAY_ANIM_NON_INTERRUPTABLE syn5_tbone RYD_Die_PT2 RYDER 1000.0 FALSE FALSE FALSE TRUE -1

					SET_FIXED_CAMERA_POSITION -1633.2 1416.9 7.6 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -1624.2 1424.9 6.8 JUMP_CUT
					syn5_control_flag = 3
				ENDIF
			ENDIF
		ENDIF		

		IF syn5_control_flag = 3
			IF NOT IS_CHAR_DEAD syn5_tbone 
				IF IS_CHAR_PLAYING_ANIM syn5_tbone RYD_Die_PT2 
					GET_CHAR_ANIM_CURRENT_TIME syn5_tbone RYD_Die_PT2 syn5_animtime
					IF syn5_animtime > 0.783 
						SET_CHAR_ANIM_SPEED syn5_tbone RYD_Die_PT2 0.0	
						syn5_control_flag = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 4 				
			IF syn5_speech_goals = 0
				IF NOT IS_CHAR_DEAD syn5_tbone
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_TURN_CHAR_TO_FACE_CHAR -1 syn5_tbone
						TASK_SHOOT_AT_CHAR -1 syn5_tbone 1000  
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK scplayer syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_TURN_CHAR_TO_FACE_CHAR -1 syn5_tbone
						TASK_SHOOT_AT_CHAR -1 syn5_tbone 1000  
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK cesar syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				
					IF IS_CHAR_PLAYING_ANIM syn5_tbone RYD_Die_PT2
						SET_CHAR_ANIM_SPEED syn5_tbone RYD_Die_PT2 1.0
					ENDIF
					
					syn5_control_flag = 5
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 5
			IF NOT IS_CHAR_DEAD syn5_tbone  
				IF IS_CHAR_PLAYING_ANIM syn5_tbone RYD_Die_PT2 
					GET_CHAR_ANIM_CURRENT_TIME syn5_tbone RYD_Die_PT2 syn5_animtime 
					IF syn5_animtime > 0.98 
						SET_CHAR_COORDINATES syn5_tbone -1622.1 1425.5 6.2
						//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1622.1 1425.5 6.2 SOUND_PED_HIT_WATER_SPLASH
						timera = 0 
						syn5_control_flag = 6
					ENDIF
				ENDIF 
			ENDIF
		ENDIF						

		IF syn5_control_flag = 6 
			IF timera > 2000
				IF NOT IS_CHAR_DEAD ryder 
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1621.0 1396.0 7.4 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
					POINT_CAMERA_AT_CHAR ryder FIXED JUMP_CUT 
					
					// Ryder, you sherm-head assshole
					syn5_speech_goals = 8
					syn5_speech_control_flag = 1
					syn5_random_last_label = 4 
					GOSUB syn5_dialogue_setup  
				ENDIF
				timera = 0
				syn5_control_flag = 7
			ENDIF
		ENDIF
		
		IF syn5_control_flag = 7 
			IF NOT IS_CHAR_DEAD ryder 
				GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK task_status
				IF task_status = FINISHED_TASK
				
					OPEN_SEQUENCE_TASK syn5_seq	  
						TASK_GO_STRAIGHT_TO_COORD -1 -1614.82, 1397.204, 7.277 PEDMOVE_RUN -1
						TASK_CHAR_SLIDE_TO_COORD -1 -1614.82, 1397.204, 7.277 287.0 0.9   
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 run_dive MISC 4.0 FALSE FALSE FALSE TRUE -1
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
				
				
					SET_FIXED_CAMERA_POSITION -1617.8 1398.8 7.2 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -1614.2 1396.3 8.0 JUMP_CUT
					timera = 0
					syn5_control_flag = 8
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 8
			IF syn5_speech_goals = 0	 
				// He's headed for those boats and I can't swim. | 	Don't worry, he's mine!
				syn5_speech_goals = 8
				syn5_speech_control_flag = 4
				syn5_random_last_label = 6 
				GOSUB syn5_dialogue_setup 
				syn5_control_flag = 9
			ENDIF
		ENDIF 

		IF syn5_control_flag = 9 
			IF NOT IS_CHAR_DEAD ryder  
				IF IS_CHAR_PLAYING_ANIM ryder run_dive 
					GET_CHAR_ANIM_CURRENT_TIME ryder run_dive syn5_animtime 
					IF syn5_animtime > 0.98 
						timera = 0
						syn5_control_flag = 10
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 10
			IF timera > 500
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1609.2 1401.7 5.8 SOUND_PED_HIT_WATER_SPLASH
				timera = 0
				syn5_control_flag = 11
			ENDIF
		ENDIF				 

		IF syn5_control_flag = 11
			IF timera > 1000
				syn5_control_flag = 12	
			ENDIF
		ENDIF

		IF syn5_control_flag = 12
			syn5_skip_cutscene_flag = 0
			SKIP_CUTSCENE_END
			GOSUB syn5_death_checks
			IF syn5_goals = 10 
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF																	   

			//if cutscene has been skipped ////////////////////////////////////////////
			IF syn5_skip_cutscene_flag = 1											   
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS
				syn5_speech_goals = 0
				
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
				GOSUB syn5_death_checks		
				IF syn5_goals = 10 
					GOTO syn_goals10
				ENDIF
				IF syn5_deathcheck_flag = 1
					GOTO mission_syn5_failed
				ENDIF
			
				DELETE_CHAR syn5_tbone													   
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer								   
				CLEAR_CHAR_TASKS_IMMEDIATELY cesar									   
			ENDIF																	   
		
			IF NOT IS_CHAR_DEAD ryder 
				IF NOT IS_CAR_DEAD syn5_boat[0] 
					SET_CHAR_DROWNS_IN_WATER ryder FALSE
					CLEAR_CHAR_TASKS_IMMEDIATELY ryder
					SET_CHAR_COORDINATES ryder -1543.9 1401.6 0.0 
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS syn5_boat[0] -1.0 2.0 0.0 syn5_x syn5_y syn5_z
					TASK_SWIM_TO_COORD ryder syn5_x syn5_y syn5_z 
					SET_SWIM_SPEED ryder 2.3  
					ADD_BLIP_FOR_CHAR ryder syn5_blip
				ENDIF
			ENDIF
	
			MARK_CAR_AS_NO_LONGER_NEEDED syn5_ryders_car 
			MARK_CAR_AS_NO_LONGER_NEEDED syn5_ryder_goon_cars[0] 
			MARK_CAR_AS_NO_LONGER_NEEDED syn5_ryder_goon_cars[1] 

			MARK_MODEL_AS_NO_LONGER_NEEDED PICADOR
			MARK_MODEL_AS_NO_LONGER_NEEDED tahoma
			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA

			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2

			MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
			MARK_MODEL_AS_NO_LONGER_NEEDED SFR2

			TASK_GO_STRAIGHT_TO_COORD cesar -1616.5 1402.9 6.2 PEDMOVE_WALK -2
			TASK_GO_STRAIGHT_TO_COORD wuzi -1615.5 1398.4 6.2 PEDMOVE_WALK -2

			UNLOAD_SPECIAL_CHARACTER 4
			REMOVE_ANIMATION MISC
			REMOVE_ANIMATION RYDER
			CLEAR_PRINTS
			PRINT ( SYN5_07 ) 7000 1 //Jump into the water and swim after T-Bone.
			//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			
			SHUT_ALL_CHARS_UP FALSE

			SWITCH_WIDESCREEN OFF	
			MAKE_PLAYER_GANG_REAPPEAR
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT 
			SET_PLAYER_CONTROL player1 ON
		
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
			GOSUB syn5_death_checks		
			IF syn5_goals = 10 
				GOTO syn_goals10
			ENDIF
			IF syn5_deathcheck_flag = 1
				GOTO mission_syn5_failed
			ENDIF
			syn5_speech_flag = 0
			syn5_control_flag = 0
			syn5_goals = 6
		ENDIF
	ENDIF





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// WAITING FOR THE PLAYER TO GET TO BOAT AND KILL T-BONE /////////////////////////////////////////// 

	IF syn5_goals = 6
		
		// help text
		IF syn5_speech_flag = 0
			IF IS_CHAR_IN_WATER scplayer
				CLEAR_THIS_PRINT SYN5_07
				PRINT_NOW ( SYN5_08 ) 7000 1 //Kill T-Bone.
				syn5_speech_flag = 1
			ENDIF  
		ENDIF

		// telling player to get in the boat
		IF syn5_speech_flag = 1
			IF NOT IS_CAR_DEAD syn5_boat[1]
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer syn5_boat[1] 10.0 10.0 FALSE   
					ADD_BLIP_FOR_CAR syn5_boat[1] syn5_boat_blip
		 			SET_BLIP_AS_FRIENDLY syn5_boat_blip TRUE
		 			PRINT_NOW ( SYN5_11 ) 7000 1 //Get in the boat.
					syn5_speech_flag = 2
				ENDIF
			ENDIF
		ENDIF
		
		// waiting for player to get in the boat 
		IF syn5_speech_flag = 2
			IF NOT IS_CAR_DEAD syn5_boat[1]
				IF IS_CHAR_IN_CAR scplayer syn5_boat[1]
					IF NOT IS_CHAR_DEAD syn5_party_dudes[0]
					
						syn5_speech_goals = 9
						GENERATE_RANDOM_INT_IN_RANGE 0 3 syn5_speech_control_flag
						syn5_random_last_label = syn5_speech_control_flag + 1 
						GOSUB syn5_dialogue_setup 
					ENDIF
					syn5_speech_flag = 3
				ENDIF
			ENDIF  

			IF IS_CHAR_IN_ANY_BOAT scplayer 
				REMOVE_BLIP syn5_boat_blip
				syn5_speech_flag = 3
			ENDIF	
		ENDIF

		IF syn5_speech_flag = 3
			IF syn5_speech_goals = 0
				IF syn5_speech_goals = 0 
					PRINT_NOW ( SYN5_12 ) 7000 1 //~s~Kill ~r~Ryder~s~.  You can do a drive-by on his boat.
					syn5_speech_flag = 4
				ENDIF
			ENDIF
		ENDIF
	
		IF syn5_speech_flag = 4
			IF syn5_speech_goals = 0	  
				IF NOT IS_CHAR_DEAD ryder 
					IF NOT IS_CAR_DEAD syn5_boat[0]
						IF IS_CHAR_IN_CAR ryder syn5_boat[0]
							IF NOT IS_CAR_DEAD syn5_boat[1]
								IF IS_CHAR_IN_CAR scplayer syn5_boat[1]
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer ryder 15.0 15.0 FALSE
										IF NOT IS_CHAR_DEAD ryder 
											syn5_speech_goals = 11
											syn5_speech_control_flag = 0
											GOSUB syn5_dialogue_setup  
											syn5_speech_flag = 5
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
		// main bit
		IF syn5_control_flag = 0
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CAR_DEAD syn5_boat[0] 
					IF NOT LOCATE_CHAR_ANY_MEANS_2D ryder syn5_x syn5_y 2.0 2.0 FALSE 
						GET_SCRIPT_TASK_STATUS ryder TASK_SWIM_TO_COORD task_status
						IF task_status = FINISHED_TASK
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS syn5_boat[0] -1.0 2.0 0.0 syn5_x syn5_y syn5_z
							TASK_SWIM_TO_COORD ryder syn5_x syn5_y syn5_z
							SET_SWIM_SPEED ryder 2.3
						ENDIF
					ELSE
						WARP_CHAR_INTO_CAR ryder syn5_boat[0]
						syn5_control_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 1
			IF NOT IS_CHAR_DEAD ryder 
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer ryder 20.0 20.0 FALSE
				OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR ryder scplayer 	 	
					syn5_control_flag = 2
				ENDIF
				IF NOT IS_CAR_DEAD syn5_boat[0] 
					IF HAS_CAR_BEEN_DAMAGED_BY_CHAR syn5_boat[0] scplayer
						syn5_control_flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 2
			IF syn5_controlling_tbone = 4
				IF NOT IS_CHAR_DEAD ryder 
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer ryder 250.0 250.0 FALSE	
				 		IF NOT IS_CHAR_ON_SCREEN ryder
							CLEAR_PRINTS
				 			PRINT_NOW ( SYN5_09 ) 4000 1 //T-Bone has escaped!
				 			GOTO mission_syn5_failed
				 		ENDIF
				 	ENDIF
				ENDIF
			ENDIF  
		ENDIF 


		//Boat AI
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CAR_DEAD syn5_boat[0]
				IF IS_CHAR_IN_CAR ryder syn5_boat[0]
					IF syn5_control_flag > 1  
						GOSUB syn5_telling_tbone_where_to_go
					ENDIF
				ENDIF
			ELSE
				//what happens if t-boats boat gets blown up before he gets in
				IF syn5_controlling_tbone = 0
					TASK_SWIM_TO_COORD ryder -1491.2 1387.4 0.0 
					SET_SWIM_SPEED ryder 2.3
					syn5_controlling_tbone = 1
				ENDIF
				IF syn5_controlling_tbone = 1
					GET_SCRIPT_TASK_STATUS ryder TASK_SWIM_TO_COORD task_status
					IF task_status = FINISHED_TASK
						OPEN_SEQUENCE_TASK syn5_seq	
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_GO_STRAIGHT_TO_COORD -1 -1497.2 1383.5 1.6 PEDMOVE_RUN 1 /////debug - needs to be -1
							TASK_ACHIEVE_HEADING -1 64.5
							TASK_KILL_CHAR_ON_FOOT -1 scplayer   	
						CLOSE_SEQUENCE_TASK syn5_seq
						PERFORM_SEQUENCE_TASK ryder syn5_seq
						CLEAR_SEQUENCE_TASK syn5_seq
						syn5_controlling_tbone = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//removing blip from boat
		IF IS_CAR_DEAD syn5_boat[1]
			REMOVE_BLIP syn5_boat_blip
		ENDIF 
		
		
		//passing mission
		IF IS_CHAR_DEAD ryder 
			timera = 0 
			syn5_control_flag = 0
			syn5_goals = 7	
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// END CUTSCENE /////////////////////////////////////////////////////////////////////////////////// 

	IF syn5_goals = 7
		IF timera > 2000
			IF syn5_control_flag = 0
				IF IS_CHAR_IN_ANY_BOAT scplayer
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1	
					CLEAR_MISSION_AUDIO 2
					syn5_speech_goals = 0
				 
				 	SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					
					SHUT_ALL_CHARS_UP TRUE
					 
					// Cesar congratulating player if he is in a boat
					syn5_speech_goals = 10
					syn5_speech_control_flag = 0
					GOSUB syn5_dialogue_setup  

					syn5_control_flag = 1
				ELSE
					GOTO mission_syn5_passed
				ENDIF
			ENDIF
		ENDIF

		IF syn5_control_flag = 1
			IF syn5_speech_goals = 0
				SHUT_ALL_CHARS_UP FALSE
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_PLAYER_CONTROL player1 ON
				GOTO mission_syn5_passed
			ENDIF
		ENDIF
	ENDIF


	//ingame dialogue
	GOSUB syn5_overall_dialogue

GOTO mission_syn5_loop


	
// Mission syn5 failed

mission_syn5_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission syn5 passed
mission_syn5_passed:
flag_synd_mission_counter ++
//flag_syn5_mission1_passed = 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 15000 5000 1 //"Mission Passed!" //100 being the amount of cash
ADD_SCORE player1 15000//amount of cash
AWARD_PLAYER_MISSION_RESPECT 40//amount of respect
REMOVE_BLIP synd_contact_blip
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED SYND_5
PLAYER_MADE_PROGRESS 1
REMOVE_BLIP garage_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip

//START_NEW_SCRIPT syn5_mission_loop
RETURN
		

// mission cleanup
mission_cleanup_syn5:

//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
DELETE_OBJECT syn5_sniper_anim
REMOVE_BLIP syn5_blip
//REMOVE_ANIMATION GRENADE
REMOVE_ANIMATION DANCING
REMOVE_ANIMATION KISSING
REMOVE_ANIMATION MISC
REMOVE_ANIMATION RYDER
REMOVE_BLIP syn5_sniper_blips[0]
REMOVE_BLIP syn5_sniper_blips[1]
REMOVE_BLIP syn5_sniper_blips[2]
REMOVE_BLIP syn5_sniper_blips[3]
REMOVE_BLIP syn5_sniper_blips[4]
REMOVE_BLIP syn5_sniper_blips[5]
REMOVE_BLIP syn5_blip
REMOVE_BLIP syn5_boat_blip
KILL_FX_SYSTEM syn5_teargas[0]
KILL_FX_SYSTEM syn5_teargas[1]
KILL_FX_SYSTEM syn5_teargas[2]
KILL_FX_SYSTEM syn5_teargas[3]
MARK_MODEL_AS_NO_LONGER_NEEDED PICADOR
MARK_MODEL_AS_NO_LONGER_NEEDED tahoma
MARK_MODEL_AS_NO_LONGER_NEEDED maverick
MARK_MODEL_AS_NO_LONGER_NEEDED SPEEDER
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1 
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2 
MARK_MODEL_AS_NO_LONGER_NEEDED TRIBOSS
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA 
MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
MARK_MODEL_AS_NO_LONGER_NEEDED SFR2
MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER 
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9 
MARK_MODEL_AS_NO_LONGER_NEEDED PONY 
MARK_MODEL_AS_NO_LONGER_NEEDED cellphone 
MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE
MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER_anim 
MARK_MODEL_AS_NO_LONGER_NEEDED TEARGAS
MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WOODPILE2
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
REMOVE_SCRIPT_FIRE SYN5_FIRE
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
RELEASE_WEATHER
REMOVE_CHAR_ELEGANTLY cesar
REMOVE_CHAR_ELEGANTLY ryder
REMOVE_CHAR_ELEGANTLY syn5_toreno
REMOVE_CHAR_ELEGANTLY syn5_tbone
SWITCH_PED_ROADS_BACK_TO_ORIGINAL -1755.6 1256.6 0.0 -1598.2 1660.1 50.0
UNLOAD_SPECIAL_CHARACTER 1																		   
UNLOAD_SPECIAL_CHARACTER 2																		   
UNLOAD_SPECIAL_CHARACTER 3																		   
UNLOAD_SPECIAL_CHARACTER 4		
ENABLE_AMBIENT_CRIME TRUE															   
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////                      GOSUBS                             ///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////
syn5_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF syn5_goals < 10
	IF IS_CHAR_DEAD cesar
		CLEAR_PRINTS
		PRINT_NOW ( SYN5_04 ) 7000 1 //Cesar has been killed!	
		syn5_deathcheck_flag = 1	
	ENDIF
ENDIF

IF syn5_goals = 3
	IF IS_CHAR_DEAD wuzi
		CLEAR_PRINTS
		//PRINT_NOW ( SYN5_05 ) 7000 1 //The Triad Leader has been killed!	
		syn5_control_flag = 0
		syn5_goals = 10	
	ENDIF
ENDIF

IF syn5_goals = 2
OR syn5_goals > 3
	IF NOT syn5_goals = 10
		IF IS_CHAR_DEAD wuzi 
			CLEAR_PRINTS
			PRINT_NOW ( SYN5_05 ) 7000 1 //The Triad Leader has been killed!
			syn5_deathcheck_flag = 1
		ENDIF	
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_creating_snipers:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//TBONE'S ROOFTOP GOONS
//southwest roof
IF IS_CHAR_DEAD syn5_tbone_goons[0] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1713.16 1356.57 16.2 syn5_tbone_goons[0]	
	SET_CHAR_HEADING syn5_tbone_goons[0] 44.5
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[0] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[0] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[0] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[0] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[0] TRUE
ENDIF
//west roof
IF IS_CHAR_DEAD syn5_tbone_goons[1] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1682.9 1379.8 13.8 syn5_tbone_goons[1]	
	SET_CHAR_HEADING syn5_tbone_goons[1] 36.8
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[1] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[1] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[1] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[1] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[1] TRUE
ENDIF
//northwest roof																				 
IF IS_CHAR_DEAD syn5_tbone_goons[2] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.2 1393.2 20.5  syn5_tbone_goons[2]	
	SET_CHAR_HEADING syn5_tbone_goons[2] 276.9
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[2] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[2] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[2] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[2] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[2] TRUE
ENDIF
//southeast roof 
IF IS_CHAR_DEAD syn5_tbone_goons[3] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1686.4 1345.6 16.2 syn5_tbone_goons[3]	
	SET_CHAR_HEADING syn5_tbone_goons[3] 4.0
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[3] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[3] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[3] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[3] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[3] TRUE
ENDIF
//east roof
IF IS_CHAR_DEAD syn5_tbone_goons[4] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1661.0 1362.2 17.4 syn5_tbone_goons[4]	
	SET_CHAR_HEADING syn5_tbone_goons[4] 236.8
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[4] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[4] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[4] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[4] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[4] TRUE
ENDIF
//northeast roof
IF IS_CHAR_DEAD syn5_tbone_goons[5] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1641.6 1369.9 17.4 syn5_tbone_goons[5]	
	SET_CHAR_HEADING syn5_tbone_goons[5] 211.1
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[5] WEAPONTYPE_TEC9 300000 
	TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[5] TRUE
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[5] syn5_empty_ped_decision_maker 
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[5] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[5] TRUE
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////
syn5_creating_wuzi_men://///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD wuzi
	CREATE_CHAR PEDTYPE_MISSION1 TRIBOSS -1687.4 1321.1 6.2 wuzi
	SET_CHAR_HEADING wuzi 326.8
	SET_CHAR_VISIBLE wuzi FALSE 
	GIVE_WEAPON_TO_CHAR wuzi WEAPONTYPE_TEC9 30000 
	SET_CHAR_NEVER_TARGETTED wuzi TRUE
	SET_CHAR_DECISION_MAKER wuzi syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE wuzi TRUE  
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER wuzi TRUE
	OPEN_SEQUENCE_TASK syn5_seq
		TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
	CLOSE_SEQUENCE_TASK syn5_seq
	PERFORM_SEQUENCE_TASK wuzi syn5_seq
	CLEAR_SEQUENCE_TASK syn5_seq
ENDIF

IF IS_CHAR_DEAD syn5_wuzi_goons[0]
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA -1689.5 1320.2 6.2 syn5_wuzi_goons[0]
	SET_CHAR_HEADING syn5_wuzi_goons[0] 312.1
	GIVE_WEAPON_TO_CHAR syn5_wuzi_goons[0] WEAPONTYPE_TEC9 30000 
	TASK_DUCK syn5_wuzi_goons[0] 360000000 
	SET_CHAR_NEVER_TARGETTED syn5_wuzi_goons[0] TRUE
	SET_CHAR_DECISION_MAKER syn5_wuzi_goons[0] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_wuzi_goons[0] TRUE  
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[0] TRUE
	OPEN_SEQUENCE_TASK syn5_seq
		TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
	CLOSE_SEQUENCE_TASK syn5_seq
	PERFORM_SEQUENCE_TASK syn5_wuzi_goons[0] syn5_seq
	CLEAR_SEQUENCE_TASK syn5_seq
ENDIF

IF IS_CHAR_DEAD syn5_wuzi_goons[1]
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA -1688.1 1319.3 6.2 syn5_wuzi_goons[1]
	SET_CHAR_HEADING syn5_wuzi_goons[1] 327.2
	GIVE_WEAPON_TO_CHAR syn5_wuzi_goons[1] WEAPONTYPE_TEC9 30000 
	TASK_DUCK syn5_wuzi_goons[1] 360000000 
	SET_CHAR_NEVER_TARGETTED syn5_wuzi_goons[1] TRUE
	SET_CHAR_DECISION_MAKER syn5_wuzi_goons[1] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_wuzi_goons[1] TRUE  
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[1] TRUE
	OPEN_SEQUENCE_TASK syn5_seq
		TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
	CLOSE_SEQUENCE_TASK syn5_seq
	PERFORM_SEQUENCE_TASK syn5_wuzi_goons[1] syn5_seq
	CLEAR_SEQUENCE_TASK syn5_seq
ENDIF

IF IS_CHAR_DEAD syn5_wuzi_goons[2]
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA -1686.5 1318.0 6.2 syn5_wuzi_goons[2]
	SET_CHAR_HEADING syn5_wuzi_goons[2] 346.0
	GIVE_WEAPON_TO_CHAR syn5_wuzi_goons[2] WEAPONTYPE_TEC9 30000 
	TASK_DUCK syn5_wuzi_goons[2] 360000000 
	SET_CHAR_NEVER_TARGETTED syn5_wuzi_goons[2] TRUE
	SET_CHAR_DECISION_MAKER syn5_wuzi_goons[2] syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_wuzi_goons[2] TRUE  
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_wuzi_goons[2] TRUE
	OPEN_SEQUENCE_TASK syn5_seq
		TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
	CLOSE_SEQUENCE_TASK syn5_seq
	PERFORM_SEQUENCE_TASK syn5_wuzi_goons[2] syn5_seq
	CLEAR_SEQUENCE_TASK syn5_seq
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
						
////////////////////////////////////////////////////////////////////////////
syn5_creating_tbone:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//setting t-bone and goons walking
//TBONE'S AND GROUND GOONS
IF IS_CHAR_DEAD syn5_tbone 
	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL04 -1716.0 1349.8 6.2 syn5_tbone
	SET_CHAR_ACCURACY syn5_tbone syn5_accuracy
	SET_ANIM_GROUP_FOR_CHAR syn5_tbone gang2	
	//SET_ANIM_GROUP_FOR_CHAR syn5_tbone fatman	
	SET_CHAR_HEADING syn5_tbone 229.4
	GIVE_WEAPON_TO_CHAR syn5_tbone WEAPONTYPE_AK47 30000 
	SET_CHAR_DECISION_MAKER syn5_tbone syn5_empty_ped_decision_maker
	SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone TRUE
ENDIF
IF IS_CHAR_DEAD syn5_tbone_goons[0] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1716.8 1351.6 6.2 syn5_tbone_goons[0]	
	SET_CHAR_ACCURACY syn5_tbone_goons[0] syn5_accuracy
	SET_CHAR_HEADING syn5_tbone_goons[0] 229.4
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[0] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[0] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[0] TRUE
ENDIF
IF IS_CHAR_DEAD syn5_tbone_goons[1] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1720.5 1353.7 6.2 syn5_tbone_goons[1]			////
	SET_CHAR_ACCURACY syn5_tbone_goons[1] syn5_accuracy
	SET_ANIM_GROUP_FOR_CHAR syn5_tbone_goons[1] gang2
	SET_CHAR_HEADING syn5_tbone_goons[1] 229.4
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[1] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[1] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[1] TRUE
ENDIF
IF IS_CHAR_DEAD syn5_tbone_goons[2] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1718.4 1351.3 6.2 syn5_tbone_goons[2]		////
	SET_CHAR_ACCURACY syn5_tbone_goons[2] syn5_accuracy
	SET_CHAR_HEADING syn5_tbone_goons[2] 236.0
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[2] WEAPONTYPE_TEC9 30000 
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[2] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[2] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[2] TRUE
ENDIF
IF IS_CHAR_DEAD syn5_tbone_goons[3] 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 syn5_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1718.2 1353.3 6.2 syn5_tbone_goons[3]	
	SET_CHAR_ACCURACY syn5_tbone_goons[3] syn5_accuracy
	SET_ANIM_GROUP_FOR_CHAR syn5_tbone_goons[3] gang1
	SET_CHAR_HEADING syn5_tbone_goons[3] 301.0
	GIVE_WEAPON_TO_CHAR syn5_tbone_goons[3] WEAPONTYPE_TEC9 30000 
	SET_CHAR_DECISION_MAKER syn5_tbone_goons[3] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_tbone_goons[3] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_tbone_goons[3] TRUE
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_creating_ryder:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//ryders car
IF IS_CAR_DEAD syn5_ryders_car 
	CUSTOM_PLATE_FOR_NEXT_CAR PICADOR &_SHERM__  
	CREATE_CAR PICADOR -1774.2 1376.8 5.7 syn5_ryders_car
	CHANGE_CAR_COLOUR syn5_ryders_car 84 84
	SET_CAR_HEADING syn5_ryders_car 251.8
	LOCK_CAR_DOORS syn5_ryders_car CARLOCK_LOCKOUT_PLAYER_ONLY
ENDIF
IF IS_CHAR_DEAD ryder 
	CREATE_CHAR_INSIDE_CAR syn5_ryders_car PEDTYPE_MISSION2 SPECIAL02 ryder
	SET_CHAR_ACCURACY ryder syn5_accuracy
	GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_AK47 30000
	SET_CHAR_DECISION_MAKER ryder syn5_empty_ped_decision_maker
	//SET_CHAR_STAY_IN_SAME_PLACE ryder TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER ryder TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
	ADD_ARMOUR_TO_CHAR ryder 100
ENDIF
IF IS_CHAR_DEAD syn5_ryder_goons[0]  
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_AS_PASSENGER syn5_ryders_car PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1 syn5_ryder_goons[0]    
	SET_CHAR_ACCURACY syn5_ryder_goons[0] syn5_accuracy
	GIVE_WEAPON_TO_CHAR syn5_ryder_goons[0] WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[0] syn5_ped_decisions 
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[0] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[0] TRUE
ENDIF
/*
IF IS_CHAR_DEAD syn5_ryder_goons[1]  
	//guy attached to back of car
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_AS_PASSENGER syn5_ryders_car PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] -1 syn5_ryder_goons[1]    
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[1] syn5_empty_ped_decision_maker
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[1] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[1] TRUE
ENDIF
*/
//2nd car
IF IS_CAR_DEAD syn5_ryder_goon_cars[0]  
	CREATE_CAR tahoma -1784.2 1379.8 5.7 syn5_ryder_goon_cars[0]
	SET_CAR_HEADING syn5_ryder_goon_cars[0] 251.8
	LOCK_CAR_DOORS syn5_ryder_goon_cars[0] CARLOCK_LOCKOUT_PLAYER_ONLY
ENDIF
IF IS_CHAR_DEAD syn5_ryder_goons[2]  
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_INSIDE_CAR syn5_ryder_goon_cars[0] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] syn5_ryder_goons[2]
	SET_CHAR_ACCURACY syn5_ryder_goons[2] syn5_accuracy
	GIVE_WEAPON_TO_CHAR syn5_ryder_goons[2] WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[2] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[2] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[2] TRUE
ENDIF
IF IS_CHAR_DEAD syn5_ryder_goons[3]  
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_AS_PASSENGER syn5_ryder_goon_cars[0] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] 0 syn5_ryder_goons[3]    
	SET_CHAR_ACCURACY syn5_ryder_goons[3] syn5_accuracy
	GIVE_WEAPON_TO_CHAR syn5_ryder_goons[3] WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[3] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[3] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[3] TRUE
ENDIF
	//3rd car
IF IS_CAR_DEAD syn5_ryder_goon_cars[1]  
	CREATE_CAR tahoma -1795.0 1383.6 5.7 syn5_ryder_goon_cars[1]
	SET_CAR_HEADING syn5_ryder_goon_cars[1] 251.8
	LOCK_CAR_DOORS syn5_ryder_goon_cars[1] CARLOCK_LOCKOUT_PLAYER_ONLY
ENDIF
IF IS_CHAR_DEAD syn5_ryder_goons[4]  
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_INSIDE_CAR syn5_ryder_goon_cars[1] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] syn5_ryder_goons[4]
	SET_CHAR_ACCURACY syn5_ryder_goons[4] syn5_accuracy
	GIVE_WEAPON_TO_CHAR syn5_ryder_goons[4] WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[4] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[4] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[4] TRUE
ENDIF
IF IS_CHAR_DEAD syn5_ryder_goons[5]  
	GENERATE_RANDOM_INT_IN_RANGE 3 5 syn5_char_select_flag
	CREATE_CHAR_AS_PASSENGER syn5_ryder_goon_cars[1] PEDTYPE_MISSION2 syn5_char_select[syn5_char_select_flag] 0 syn5_ryder_goons[5]    
	SET_CHAR_ACCURACY syn5_ryder_goons[5] syn5_accuracy
	GIVE_WEAPON_TO_CHAR syn5_ryder_goons[5] WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_ryder_goons[5] syn5_ped_decisions
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_ryder_goons[5] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_ryder_goons[5] TRUE
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_creating_toreno:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CAR_DEAD syn5_toreno_chopper
	CREATE_CAR maverick -1600.9 1424.6 300.8 syn5_toreno_chopper
	SET_CAR_HEADING syn5_toreno_chopper 97.2
	LOCK_CAR_DOORS syn5_toreno_chopper CARLOCK_LOCKOUT_PLAYER_ONLY
	SET_HELI_REACHED_TARGET_DISTANCE syn5_toreno_chopper 3
	SET_HELI_BLADES_FULL_SPEED syn5_toreno_chopper
	SET_CAR_FORWARD_SPEED syn5_toreno_chopper 10.0
	HELI_GOTO_COORDS syn5_toreno_chopper -1600.9 1424.6 300.0 0.0 300.0
ENDIF
IF IS_CHAR_DEAD syn5_toreno
	CREATE_CHAR_AS_PASSENGER syn5_toreno_chopper PEDTYPE_MISSION2 SPECIAL03 0 syn5_toreno
	GIVE_WEAPON_TO_CHAR syn5_toreno WEAPONTYPE_TEC9 30000
	SET_CHAR_DECISION_MAKER syn5_toreno syn5_empty_ped_decision_maker
	SET_CHAR_SUFFERS_CRITICAL_HITS syn5_toreno FALSE
	//SET_CHAR_STAY_IN_SAME_PLACE syn5_toreno TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER syn5_toreno TRUE
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_controlling_sniping_section_ai:////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//southwest roof
IF NOT IS_CHAR_DEAD syn5_tbone_goons[0] 
	IF syn5_sniper_control_flag[0] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -1705.8 1359.5 16.2 PEDMOVE_RUN -1 
			TASK_GO_STRAIGHT_TO_COORD -1 -1701.3 1356.7 16.2 PEDMOVE_RUN -1 
			TASK_ACHIEVE_HEADING -1 236.7
			TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
			IF NOT IS_CHAR_DEAD wuzi
				TASK_KILL_CHAR_ON_FOOT -1 wuzi
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[0] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[0] = 1
	ENDIF
ENDIF

//west roof
IF NOT IS_CHAR_DEAD syn5_tbone_goons[1] 
	IF syn5_sniper_control_flag[1] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			IF NOT IS_CHAR_DEAD wuzi 
				TASK_GO_STRAIGHT_TO_COORD -1 -1703.3 1361.5 16.2 PEDMOVE_RUN -1 
				TASK_GO_STRAIGHT_TO_COORD -1 -1699.8 1357.9 16.2 PEDMOVE_RUN -1 
				//TASK_ACHIEVE_HEADING -1 236.7
				TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
				TASK_KILL_CHAR_ON_FOOT -1 wuzi
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[1] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[1] = 1
	ENDIF
ENDIF

//northwest roof																				 
IF NOT IS_CHAR_DEAD syn5_tbone_goons[2] 
	IF syn5_sniper_control_flag[2] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			IF NOT IS_CHAR_DEAD wuzi 
				TASK_GO_STRAIGHT_TO_COORD -1 -1689.2 1371.8 14.0 PEDMOVE_RUN -1 
				TASK_ACHIEVE_HEADING -1 202.3
				TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
				//TASK_KILL_CHAR_ON_FOOT -1 wuzi
				TASK_SHOOT_AT_COORD -1 -1687.6 1367.1 15.0 -2
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[2] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[2] = 1
	ENDIF
ENDIF

//southeast roof 
IF NOT IS_CHAR_DEAD syn5_tbone_goons[3] 
	IF syn5_sniper_control_flag[3] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			IF NOT IS_CHAR_DEAD wuzi 
				TASK_ACHIEVE_HEADING -1 107.3
				TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
				TASK_KILL_CHAR_ON_FOOT -1 wuzi
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[3] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[3] = 1
	ENDIF
ENDIF

//east roof
IF NOT IS_CHAR_DEAD syn5_tbone_goons[4] 
	IF syn5_sniper_control_flag[4] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			IF NOT IS_CHAR_DEAD wuzi 
				TASK_GO_STRAIGHT_TO_COORD -1 -1674.2 1345.9 16.2 PEDMOVE_RUN -1     
				TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
				TASK_KILL_CHAR_ON_FOOT -1 wuzi
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[4] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[4] = 1
	ENDIF
ENDIF

//northeast roof
IF NOT IS_CHAR_DEAD syn5_tbone_goons[5] 
	IF syn5_sniper_control_flag[5] = 0
		TASK_SET_IGNORE_WEAPON_RANGE_FLAG syn5_tbone_goons[5] TRUE 
		OPEN_SEQUENCE_TASK syn5_seq
			IF NOT IS_CHAR_DEAD wuzi 
				TASK_GO_STRAIGHT_TO_COORD -1 -1643.6 1368.7 17.8 PEDMOVE_RUN -1 
				TASK_ACHIEVE_HEADING -1 136.5
				TASK_JUMP -1 TRUE 
				TASK_GO_STRAIGHT_TO_COORD -1 -1661.5 1354.3 15.1 PEDMOVE_RUN -1     
				TASK_KILL_CHAR_ON_FOOT -1 wuzi 
			ENDIF
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_tbone_goons[5] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_sniper_control_flag[5] = 1
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD wuzi
	IF syn5_wuzi_control_flag = 0
		OPEN_SEQUENCE_TASK syn5_seq
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK wuzi syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_wuzi_control_flag = 1
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD syn5_wuzi_goons[0]
	IF syn5_wuzi_goons_control_flag[0] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_wuzi_goons[0] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_wuzi_goons_control_flag[0] = 1
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD syn5_wuzi_goons[1]
	IF syn5_wuzi_goons_control_flag[1] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_wuzi_goons[1] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_wuzi_goons_control_flag[1] = 1
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD syn5_wuzi_goons[2]
	IF syn5_wuzi_goons_control_flag[2] = 0
		OPEN_SEQUENCE_TASK syn5_seq
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 syn5_ped_decisions
		CLOSE_SEQUENCE_TASK syn5_seq
		PERFORM_SEQUENCE_TASK syn5_wuzi_goons[2] syn5_seq
		CLEAR_SEQUENCE_TASK syn5_seq
		syn5_wuzi_goons_control_flag[2] = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_telling_tbone_where_to_go://///////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CHAR_DEAD ryder
	IF NOT IS_CAR_DEAD syn5_boat[0]
		IF IS_CHAR_IN_CAR ryder syn5_boat[0]  
			IF syn5_controlling_tbone = 0
				SET_CHAR_DROWNS_IN_WATER ryder TRUE
				OPEN_SEQUENCE_TASK syn5_seq	
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1500.0868 1396.9392 -0.7305 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1347.7032 1319.2152 0.0282 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1250.0698 1178.1776 -0.1243 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1158.0005 1033.0165 -0.1089 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1054.1752 920.3442 0.0405 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -1005.5988 787.5093 0.2581 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -883.1418 608.1723 -0.1191 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -735.4338 521.1882 -0.1478 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
				CLOSE_SEQUENCE_TASK syn5_seq
				PERFORM_SEQUENCE_TASK ryder syn5_seq
				CLEAR_SEQUENCE_TASK syn5_seq
				syn5_controlling_tbone = 1
				RETURN
			ENDIF

			IF syn5_controlling_tbone = 1 
				GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK task_status	
				IF task_status = FINISHED_TASK
					OPEN_SEQUENCE_TASK syn5_seq	
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -572.1049 467.9407 0.3352 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -404.1097 430.3622 0.3394 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -233.5327 424.5564 0.2022 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] -63.8903 409.6541 0.0351 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 106.3031 423.1877 -0.0997 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 274.6070 452.5373 0.0679 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 442.7346 482.1971 0.2390 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 611.5419 513.3603 0.1773 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
					syn5_controlling_tbone = 2
					RETURN
				ENDIF
			ENDIF
		
			IF syn5_controlling_tbone = 2 
				GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK task_status	
				IF task_status = FINISHED_TASK
					OPEN_SEQUENCE_TASK syn5_seq	
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 779.7440 547.2416 0.1644 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 945.0496 589.4660 0.0676 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1113.1173 620.2682 -0.0797 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1282.4956 600.0090 -0.1302 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1451.7389 568.6847 -0.1388 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1620.5931 536.4412 -0.0845 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1789.8967 510.0630 -0.3449 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 1959.2064 491.1599 -0.0624 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
					syn5_controlling_tbone = 3
					RETURN
				ENDIF
			ENDIF
		
			IF syn5_controlling_tbone = 3 
				GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK task_status	
				IF task_status = FINISHED_TASK
					OPEN_SEQUENCE_TASK syn5_seq	
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2130.1274 479.1175 0.1320 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2301.9817 467.9901 -0.1513 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2471.6638 456.2590 -0.0586 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2641.9817 442.9174 0.4767 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2812.7485 425.1894 0.0436 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_DRIVE_TO_COORD -1 syn5_boat[0] 2982.4722 401.9557 -0.7104 100.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						TASK_CAR_TEMP_ACTION -1 syn5_boat[0] TEMPACT_GOFORWARD 860000
					CLOSE_SEQUENCE_TASK syn5_seq
					PERFORM_SEQUENCE_TASK ryder syn5_seq
					CLEAR_SEQUENCE_TASK syn5_seq
					syn5_controlling_tbone = 4
				ENDIF
			ENDIF

			// speed_up_slow_down
			IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer syn5_boat[0] 50.0 50.0 FALSE 
				SET_BOAT_CRUISE_SPEED syn5_boat[0] 40.0
			ELSE
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer syn5_boat[0] 100.0 100.0 FALSE 
					SET_BOAT_CRUISE_SPEED syn5_boat[0] 30.0
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer syn5_boat[0] 180.0 180.0 FALSE 
						SET_BOAT_CRUISE_SPEED syn5_boat[0] 20.0
					ELSE
						SET_BOAT_CRUISE_SPEED syn5_boat[0] 10.0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_cesar_group:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF syn5_flag_cesar_in_group = 1
    IF NOT IS_GROUP_MEMBER cesar Players_Group
        REMOVE_BLIP syn5_blip
        ADD_BLIP_FOR_CHAR cesar syn5_blip
		SET_BLIP_AS_FRIENDLY syn5_blip TRUE
        syn5_flag_cesar_in_group = 0
    ENDIF
ENDIF

IF syn5_flag_cesar_in_group = 0 
    IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cesar 8.0 8.0 8.0 FALSE
        IF NOT IS_GROUP_MEMBER cesar Players_Group
        	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
        	SET_GROUP_MEMBER Players_Group cesar 
		ENDIF
        REMOVE_BLIP syn5_blip
		IF NOT IS_CHAR_DEAD syn5_tbone 
			ADD_BLIP_FOR_CHAR syn5_tbone syn5_blip
		ENDIF
        syn5_flag_cesar_in_group = 1
    ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_my_number_plates:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 syn5_no_plates_flag
	IF syn5_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates got_m00_
	ENDIF 
	IF syn5_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates m00tv_4u 
	ENDIF
	IF syn5_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates mathew_2 
	ENDIF 
	IF syn5_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates d4_dew0r 
	ENDIF 
	IF syn5_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates d0de_777 
	ENDIF 
	IF syn5_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates dam0_666 
	ENDIF 
	IF syn5_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates C0NEY_88 
	ENDIF 
	IF syn5_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates pre4cher 
	ENDIF 
	IF syn5_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates dbp_4ndy 
	ENDIF 
	IF syn5_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates ev1l_sly 
	ENDIF 
	IF syn5_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates n1_r4v3n 
	ENDIF 
	IF syn5_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates d1vx_z00 
	ENDIF 
	IF syn5_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates mr_b3nn 
	ENDIF 
	IF syn5_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates r3d_r4sp 
	ENDIF 
	IF syn5_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates La_B0mba 
	ENDIF 
	IF syn5_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates L3337_0g 
	ENDIF 
	IF syn5_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates budd4h_X 
	ENDIF 
	IF syn5_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates t3h_buck 
	ENDIF 
	IF syn5_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates CHUNKY_1 
	ENDIF 
	IF syn5_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates ev1l_bnz 
	ENDIF 
	IF syn5_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates S4ND_M4N 
	ENDIF 
	IF syn5_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates RKK_DBP1 
	ENDIF 
	IF syn5_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates RE1_K0KU 
	ENDIF 
	IF syn5_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates s3xy_jud 
	ENDIF 
	IF syn5_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates sunra_93 
	ENDIF 
	IF syn5_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates UG_FuX69 
	ENDIF 
	IF syn5_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates Li0n_Cum 
	ENDIF 
	IF syn5_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates rkk_pwnd 
	ENDIF 
	IF syn5_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates haze_b0b 
	ENDIF 
	IF syn5_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates t3h_fluf 
	ENDIF 
	IF syn5_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates BM_4NDY_ 
	ENDIF 
	IF syn5_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates BM_D34N_ 
	ENDIF 
	IF syn5_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates BM_L4C3Y 
	ENDIF 
	IF syn5_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates BM_D3V__ 
	ENDIF 
	IF syn5_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates NU_SK00L 
	ENDIF 
	IF syn5_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates G4L_AVET 
	ENDIF 
	IF syn5_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR syn5_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

/*
////////////////////////////////////////////////////////////////////////////
syn5_teargas_effect:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	//20 metres out
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1652.5 1396.0 6.2 30.0 30.0 20.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1653.1 1400.0 7.0 20.0 20.0 20.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1657.8 1405.0 7.0 20.0 20.0 20.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1643.3 1390.7 7.0 20.0 20.0 20.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1671.1 1421.7 7.0 20.0 20.0 20.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1631.1 1371.6 7.0 20.0 20.0 20.0 FALSE 
		SET_PLAYER_DRUNKENNESS player1 50
	ENDIF 
	
	//15 metres out
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1652.5 1396.0 6.2 15.0 15.0 15.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1653.1 1400.0 7.0 15.0 15.0 15.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1657.8 1405.0 7.0 15.0 15.0 15.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1643.3 1390.7 7.0 15.0 15.0 15.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1671.1 1421.7 7.0 15.0 15.0 15.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1631.1 1371.6 7.0 15.0 15.0 15.0 FALSE 
		SET_PLAYER_DRUNKENNESS player1 100
	ENDIF 

	//10 metres out in the smoke 
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1652.5 1396.0 6.2 10.0 10.0 10.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1653.1 1400.0 7.0 10.0 10.0 10.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1657.8 1405.0 7.0 10.0 10.0 10.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1643.3 1390.7 7.0 10.0 10.0 10.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1671.1 1421.7 7.0 10.0 10.0 10.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1631.1 1371.6 7.0 10.0 10.0 10.0 FALSE 
		SET_PLAYER_DRUNKENNESS player1 150
	ENDIF 

	//right in the smoke 
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1652.5 1396.0 6.2 5.0 5.0 5.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1653.1 1400.0 7.0 5.0 5.0 5.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1657.8 1405.0 7.0 5.0 5.0 5.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1643.3 1390.7 7.0 5.0 5.0 5.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1671.1 1421.7 7.0 5.0 5.0 5.0 FALSE 
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1631.1 1371.6 7.0 5.0 5.0 5.0 FALSE 
		SET_PLAYER_DRUNKENNESS player1 255
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
*/

////////////////////////////////////////////////////////////////////////////
syn5_dialogue_setup://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF syn5_speech_goals = 1
	$syn5_print_label[0] = &SYN5_AA // Hey, holmes, I'm  up here. Go around back!

	syn5_audio_label[0] = SOUND_SYN5_AA
	syn5_last_label = 1
ENDIF

IF syn5_speech_goals = 2
	$syn5_print_label[0] = &SYN5_BA // Hey man.
	$syn5_print_label[1] = &SYN5_BB // Nice job getting that phone message, man.

	$syn5_print_label[2] = &SYN5_BC // Jizzy?
	$syn5_print_label[3] = &SYN5_BD // Dead. So, what's the plan, man?
	
	$syn5_print_label[4] = &SYN5_BE // T-Bone's security got here real early. 
	$syn5_print_label[5] = &SYN5_BF // They've got men on the roofs watching over the pier.

	$syn5_print_label[6] = &MOBRING // Phone ringing 

	$syn5_print_label[7] = &SYN5_BG // Yo.
	$syn5_print_label[8] = &SYN5_BH // Ok, yeah, I see you.

	$syn5_print_label[9] = &SYN5_BJ // That was Woozie's boys, they're in place.
	$syn5_print_label[10] = &SYN5_BK // Look, down by the side entrance.

	$syn5_print_label[11] = &SYN5_BL // Shit, they're heading up to the roof!
	$syn5_print_label[12] = &SYN5_BM // Dammit! We'll have to take out T-Bone's men on the rooftops
	$syn5_print_label[13] = &SYN5_BN // before this whole gig blows wide open!

	syn5_audio_label[0] = SOUND_SYN5_BA
	syn5_audio_label[1] = SOUND_SYN5_BB
	syn5_audio_label[2] = SOUND_SYN5_BC
	syn5_audio_label[3] = SOUND_SYN5_BD
	syn5_audio_label[4] = SOUND_SYN5_BE
	syn5_audio_label[5] = SOUND_SYN5_BF
	syn5_audio_label[6] = SOUND_PED_MOBRING
	syn5_audio_label[7] = SOUND_SYN5_BG
	syn5_audio_label[8] = SOUND_SYN5_BH
	syn5_audio_label[9] = SOUND_SYN5_BJ
	syn5_audio_label[10] = SOUND_SYN5_BK
	syn5_audio_label[11] = SOUND_SYN5_BL
	syn5_audio_label[12] = SOUND_SYN5_BM
	syn5_audio_label[13] = SOUND_SYN5_BN
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 3
	//$syn5_print_label[0] = &SYN5_CA // Take the shot, CJ, they'll be up there any moment!
	$syn5_print_label[1] = &SYN5_CB // Shit they've walked right into it!
	$syn5_print_label[2] = &SYN5_CC // Take 'em out, CJ, they're in the thick of it!
	$syn5_print_label[3] = &SYN5_CD // One of those Triad boys is down!
	$syn5_print_label[4] = &SYN5_CE // Scratch one more Mountain Cloud Boy!
	$syn5_print_label[5] = &SYN5_CF // They're getting cut to pieces, CJ, shoot those damn Rifas!

	//syn5_audio_label[0] = SOUND_SYN5_CA
	syn5_audio_label[1] = SOUND_SYN5_CB
	syn5_audio_label[2] = SOUND_SYN5_CC
	syn5_audio_label[3] = SOUND_SYN5_CD
	syn5_audio_label[4] = SOUND_SYN5_CE
	syn5_audio_label[5] = SOUND_SYN5_CF
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 4
	$syn5_print_label[0] = &SYN5_DA // One down...
	$syn5_print_label[1] = &SYN5_DB // That's it, CJ, keep icing those Rifa!
	$syn5_print_label[2] = &SYN5_DC // Just a few more, holmes...

	$syn5_print_label[3] = &SYN5_DD // Some more to the right..
	$syn5_print_label[4] = &SYN5_DE // Still some on the left, CJ...

	syn5_audio_label[0] = SOUND_SYN5_DA
	syn5_audio_label[1] = SOUND_SYN5_DB
	syn5_audio_label[2] = SOUND_SYN5_DC
	syn5_audio_label[3] = SOUND_SYN5_DD
	syn5_audio_label[4] = SOUND_SYN5_DE
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 5
	$syn5_print_label[0] = &SYN5_EA // Oh, man, you let them get wiped out!
	$syn5_print_label[1] = &SYN5_EB // CJ, what happened? You let them get cut to pieces!
	$syn5_print_label[2] = &SYN5_EC // We can't do this without Woozie's boys, man...
	$syn5_print_label[3] = &SYN5_ED // You can break the news to Woozie, holmes.

	syn5_audio_label[0] = SOUND_SYN5_EA
	syn5_audio_label[1] = SOUND_SYN5_EB
	syn5_audio_label[2] = SOUND_SYN5_EC
	syn5_audio_label[3] = SOUND_SYN5_ED
	syn5_last_label = syn5_random_last_label
ENDIF

/*
IF syn5_speech_goals = 6
	$syn5_print_label[0] = &SYN5_FA // Nice one, CJ!
	$syn5_print_label[1] = &SYN5_FB // Let's head on over and see what we can see.

	syn5_audio_label[0] = SOUND_SYN5_FA
	syn5_audio_label[1] = SOUND_SYN5_FB
	syn5_last_label = 2
ENDIF
*/

IF syn5_speech_goals = 7
	$syn5_print_label[0] = &SYN5_GZ // Man, my shooting was fresh! 
	//$syn5_print_label[1] = &SYN5_GB // We should be able to see everything from up here.
	$syn5_print_label[2] = &SYN5_GC // Here comes T-Bone....
	$syn5_print_label[3] = &SYN5_GD // And here's that snake Ryder.
	$syn5_print_label[4] = &SYN5_GE // Look at that fool, hanging out with Ballas like they was life long pals.
	//$syn5_print_label[5] = &SYN5_GF // CJ, you head down when you're ready, 
	//$syn5_print_label[6] = &SYN5_GG // we'll give you cover from up here.
	$syn5_print_label[7] = &SYN5_GH // Something ain't right. Where's Toreno?
	$syn5_print_label[8] = &SYN5_GJ // Chopper inbound!
	$syn5_print_label[9] = &SYN5_GK // That's got to be Toreno.
	$syn5_print_label[10] = &SYN5_GL // Oh shit, he'll see the bodies on the rooftops!
	$syn5_print_label[11] = &SYN5_GM // Too late, man, he's bugging out! 
	$syn5_print_label[12] = &SYN5_GX // Smoke grenades!
	
	//$syn5_print_label[13] = &GRO2_AD // Lets get those motherfuckers!
	
	$syn5_print_label[13] = &SYN5_GW // So much for surprise, c'mon, we gotta take those fools right now!

	syn5_audio_label[0] = SOUND_SYN5_GZ
	//syn5_audio_label[1] = SOUND_SYN5_GB
	syn5_audio_label[2] = SOUND_SYN5_GC
	syn5_audio_label[3] = SOUND_SYN5_GD
	syn5_audio_label[4] = SOUND_SYN5_GE
	//syn5_audio_label[5] = SOUND_SYN5_GF
	//syn5_audio_label[6] = SOUND_SYN5_GG
	syn5_audio_label[7] = SOUND_SYN5_GH
	syn5_audio_label[8] = SOUND_SYN5_GJ
	syn5_audio_label[9] = SOUND_SYN5_GK
	syn5_audio_label[10] = SOUND_SYN5_GL
	syn5_audio_label[11] = SOUND_SYN5_GM
	syn5_audio_label[12] = SOUND_SYN5_GX
	
	//syn5_audio_label[13] = SOUND_GRO2_AD
	
	syn5_audio_label[13] = SOUND_SYN5_GW
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 8
	$syn5_print_label[0] = &SYN5_HH // Mendez, I see you, Rifa motherfucker!

	$syn5_print_label[1] = &SYN5_HA // Ryder, you sherm-head assshole, 
	$syn5_print_label[2] = &SYN5_HB // where do you think you're going?
	$syn5_print_label[3] = &SYN5_HC // Can't... can't stop me...

	$syn5_print_label[4] = &SYN5_HK // He's headed for those boats and I can't swim.
	$syn5_print_label[5] = &SYN5_HL // Don't worry, he's mine!


	//$syn5_print_label[8] = &SYN5_HJ // I'll be back for you, Johnson!
	//$syn5_print_label[9] = &SYN5_HD // What you wanna do with him, holmes?
	//$syn5_print_label[10] = &SYN5_HG // Aaiieeeghhg!

	syn5_audio_label[0] = SOUND_SYN5_HH	
	syn5_audio_label[1] = SOUND_SYN5_HA	
	syn5_audio_label[2] = SOUND_SYN5_HB	
	syn5_audio_label[3] = SOUND_SYN5_HC	
	syn5_audio_label[4] = SOUND_SYN5_HK	
	syn5_audio_label[5] = SOUND_SYN5_HL	
	//syn5_audio_label[8] = SOUND_SYN5_HJ	
	//syn5_audio_label[9] = SOUND_SYN5_HD	
	//syn5_audio_label[10] = SOUND_SYN5_HG
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 9
	$syn5_print_label[0] = &SYN5_JA // Hey, that's my boat!
	$syn5_print_label[1] = &SYN5_JB // Oh my god, my boat!
	$syn5_print_label[2] = &SYN5_JC // Stop, you thieving bastard!

	syn5_audio_label[0] = SOUND_SYN5_JA	
	syn5_audio_label[1] = SOUND_SYN5_JB	
	syn5_audio_label[2] = SOUND_SYN5_JC	
	syn5_last_label = syn5_random_last_label
ENDIF

IF syn5_speech_goals = 10
	$syn5_print_label[0] = &MOBRING // Phone ringing
	$syn5_print_label[1] = &SYN5_KA // yo
	$syn5_print_label[2] = &SYN5_KB // You ok, holmes, we been watching from the pier with binoculars!
	$syn5_print_label[3] = &SYN5_KC // Yeah, I'm good, I think.
	$syn5_print_label[4] = &SYN5_KD // Listen you guys better clear out, cops will be all over the place.
	$syn5_print_label[5] = &SYN5_KE // Sure thing, see you back at the garage!
						  
	syn5_audio_label[0] = SOUND_PED_MOBRING	
	syn5_audio_label[1] = SOUND_SYN5_KA	
	syn5_audio_label[2] = SOUND_SYN5_KB	
	syn5_audio_label[3] = SOUND_SYN5_KC	
	syn5_audio_label[4] = SOUND_SYN5_KD	
	syn5_audio_label[5] = SOUND_SYN5_KE	
	syn5_last_label = 6
ENDIF

IF syn5_speech_goals = 11 
	$syn5_print_label[0] = &SYN5_HE // You sold us out, Ryder, you fucked us all!
	$syn5_print_label[1] = &SYN5_HF // I'm a... I'm a motherfucking genius!

	syn5_audio_label[0] = SOUND_SYN5_HE	
	syn5_audio_label[1] = SOUND_SYN5_HF	
	syn5_last_label = 2
ENDIF

IF syn5_speech_goals = 14
	$syn5_print_label[0] = &CESX_BA // Wait up, CJ!
	$syn5_print_label[1] = &CESX_BB // Hang ten, CJ!
	$syn5_print_label[2] = &CESX_BC // Hold up!
	$syn5_print_label[3] = &CESX_BD // Slow down, Carl!

	syn5_audio_label[0] = SOUND_CESX_BA 
	syn5_audio_label[1] = SOUND_CESX_BB 
	syn5_audio_label[2] = SOUND_CESX_BC 
	syn5_audio_label[3] = SOUND_CESX_BD 
 	syn5_last_label = syn5_random_last_label 
ENDIF

syn5_slot_load = syn5_speech_control_flag
syn5_slot1 = 0
syn5_slot2 = 0
syn5_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_overall_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF syn5_speech_goals = 1 //cutscene dialogue where cesar is telling player he is up above him.
OR syn5_speech_goals = 2 //cutscene dialogue where cesar is telling player about tbone's security.
OR syn5_speech_goals = 5 //cutscene dialogue of triad leader dying
OR syn5_speech_goals = 7 //cutscene dialogue showing the baddies arriving at the meet
OR syn5_speech_goals = 8 //cutscene dialogue where ryder gets shot
OR syn5_speech_goals = 10 //cutscene dialogue at end of cesar telling player good job
	IF syn5_speech_control_flag < syn5_last_label
		GOSUB syn5_loading_dialogue
		GOSUB syn5_playing_dialogue
		GOSUB syn5_finishing_dialogue  
	ELSE
		syn5_speech_goals = 0
	ENDIF
ENDIF

IF syn5_speech_goals = 3 //dialogue where cesar is telling player that wuzi's guys are getting killed
OR syn5_speech_goals = 4 //dialogue where cesar is giving player sniping advice
	IF syn5_speech_control_flag < syn5_last_label
		GOSUB syn5_loading_dialogue
		GOSUB syn5_playing_dialogue
		IF NOT IS_CHAR_DEAD cesar
			GOSUB syn5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag] 
			syn5_slot1 = 0
			syn5_slot2 = 0
		ENDIF
	ELSE
		syn5_speech_goals = 0
	ENDIF
ENDIF

IF syn5_speech_goals = 9 //party dude shouting about his boat
	IF syn5_speech_control_flag < syn5_last_label
		GOSUB syn5_loading_dialogue
		GOSUB syn5_playing_dialogue
		IF NOT IS_CHAR_DEAD syn5_party_dudes[0]
			GOSUB syn5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag] 
			syn5_slot1 = 0
			syn5_slot2 = 0
		ENDIF
	ELSE
		syn5_speech_goals = 0
	ENDIF
ENDIF

IF syn5_speech_goals = 11 //ryder
	IF syn5_speech_control_flag < syn5_last_label
		GOSUB syn5_loading_dialogue
		GOSUB syn5_playing_dialogue
		IF NOT IS_CHAR_DEAD ryder
			GOSUB syn5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag] 
			syn5_slot1 = 0
			syn5_slot2 = 0
		ENDIF
	ELSE
		syn5_speech_goals = 0
	ENDIF
ENDIF

IF syn5_goals = 5
	IF syn5_control_flag = 0
		IF IS_GROUP_MEMBER cesar Players_Group 
			//no actual dialogue during this bit
		ELSE		  
			IF syn5_speech_goals < 14 
				IF syn5_speech_control_flag < syn5_last_label
					syn5_speech_control_flag ++
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag]
				CLEAR_PRINTS
				syn5_storing_speech_goals_number = syn5_speech_goals 
				syn5_storing_speech_control_number = syn5_speech_control_flag
				syn5_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 syn5_speech_control_flag
				syn5_random_last_label = syn5_speech_control_flag + 1 
				GOSUB syn5_dialogue_setup
			ENDIF
		ENDIF
		
		IF syn5_speech_goals = 14 //cesar is out of the group
			IF NOT IS_GROUP_MEMBER cesar Players_Group
				IF syn5_speech_control_flag < syn5_last_label
					GOSUB syn5_loading_dialogue
					GOSUB syn5_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB syn5_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag] 
						syn5_slot1 = 0
						syn5_slot2 = 0
					ENDIF
				ELSE
					PRINT ( SYN5_10 ) 7000 1 //You have left Cesar behind.
					syn5_speech_goals = 15
				ENDIF
			ELSE
				PRINT ( SYN5_10 ) 7000 1 //You have left Cesar behind.
				syn5_speech_goals = 15	
			ENDIF
		ENDIF

		IF syn5_speech_goals = 15 //cesar has been out of the group and has returned
			IF IS_GROUP_MEMBER cesar Players_Group 
				syn5_speech_goals = 16
				syn5_speech_control_flag = 0
				CLEAR_PRINTS
				//GOSUB syn5_dialogue_setup
			ENDIF
		ENDIF

		IF syn5_speech_goals = 16 //cesar is back in group
			IF IS_GROUP_MEMBER cesar Players_Group 	
				timerb = 0
				syn5_speech_goals = syn5_storing_speech_goals_number
				syn5_speech_control_flag = syn5_storing_speech_control_number
				GOSUB syn5_dialogue_setup
				IF syn5_storing_speech_goals_number = 0
					PRINT_NOW ( SYN5_06 ) 4000 1 //Find and kill Ryder!
					timerb = 0
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag]
				syn5_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 syn5_speech_control_flag
				syn5_random_last_label = syn5_speech_control_flag + 1 
				GOSUB syn5_dialogue_setup
			ENDIF
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_loading_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF syn5_slot_load < syn5_last_label 
	//slot 1
	IF syn5_slot1 = 0
		LOAD_MISSION_AUDIO 1 syn5_audio_label[syn5_slot_load] 
		syn5_slot_load ++ 
		syn5_slot1 = 1
	ENDIF

	//slot 2
	IF syn5_slot2 = 0
		LOAD_MISSION_AUDIO 2 syn5_audio_label[syn5_slot_load] 
		syn5_slot_load ++ 
		syn5_slot2 = 1
	ENDIF  
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1			   
IF syn5_play_which_slot = 1 
	IF syn5_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $syn5_print_label[syn5_speech_control_flag] ) 4500 1 //
  			syn5_slot1 = 2
		ENDIF
	ENDIF
ENDIF
			   
//slot 2
IF syn5_play_which_slot = 2 
	IF syn5_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $syn5_print_label[syn5_speech_control_flag] ) 4500 1 //
			syn5_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
syn5_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF syn5_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag]
		syn5_speech_control_flag ++		
		syn5_play_which_slot = 2
		syn5_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF syn5_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $syn5_print_label[syn5_speech_control_flag]
		syn5_speech_control_flag ++		
		syn5_play_which_slot = 1
		syn5_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

}
