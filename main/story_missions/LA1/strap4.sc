MISSION_START
// *****************************************************************************************
// ********************************* music5 ************************************************ 
// *****************************************************************************************
// *****************************************************************************************
// ****************************** HOUSE PARTY **********************************************
// *****************************************************************************************
SCRIPT_NAME music5
// Mission start stuff
GOSUB mission_start_music5
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_music5_failed
ENDIF
GOSUB mission_cleanup_music5
MISSION_END
{
// Variables for mission
//people
LVAR_INT blocking_car1 blocking_car2 good_gang_member[7] attcar[4] bad_gang_member[14]
LVAR_INT alleyway_car
LVAR_INT m5_UZI_pickup m5_car_mover
LVAR_INT tw7_cutscene_car1 tw7_cutscene_car2 

//blips
LVAR_INT bad_gang_member_blip[14]

//flags
LVAR_INT music5_goals m5_control_flag m5_skip_cutscene_flag m5_deathcheck_flag 
LVAR_INT sweet_recording m5_car_mover_recording triggering_original_enemy_cars   
LVAR_INT m5_gang1_recieved_orders 
LVAR_INT m5_blip_flag 												  
LVAR_INT m5_char_select[6] m5_char_select_flag 
LVAR_INT m5_controlling_last_wave 
LVAR_INT m5_cutscene_crap
LVAR_INT m5_no_plates_flag m5_no_plates  
LVAR_INT m5_hours m5_minutes
LVAR_INT m5_model m5_class
LVAR_INT m5_enemy_accuracy

//speech								 
LVAR_INT m5_speech_goals m5_speech_flag m5_speech_control_flag m5_random_last_label 
LVAR_TEXT_LABEL m5_print_label[9] 
LVAR_INT m5_audio_label[9] 
LVAR_INT m5_last_label 
LVAR_INT m5_slot1 m5_slot2 m5_slot_load m5_play_which_slot


//coords
LVAR_FLOAT m5_x m5_y m5_z

//sequences/decision makers/threat lists/attractors/groups
LVAR_INT m5_seq m5_ped_decisions m5_empty_ped_decision_maker m5_good_group m5_good_group2 m5_bad_group m5_bad_group2 m5_group_dec


//debug

// ****************************************Mission Start************************************
mission_start_music5:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT STRAP4
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
LA_hub_activity = 1 //ambient hub stuff turned off
music5_goals = 0
m5_control_flag = 0 
m5_skip_cutscene_flag = 0 
m5_deathcheck_flag = 0 
m5_speech_flag = 0

sweet_recording = 0
m5_car_mover_recording = 0
triggering_original_enemy_cars = 0
m5_gang1_recieved_orders = 0 
m5_blip_flag = 0

m5_char_select[0] = FAM2 
m5_char_select[1] = FAM3 
m5_char_select[2] = FAM3 
m5_char_select[3] = BALLAS2 
m5_char_select[4] = BALLAS2 
m5_char_select[5] = BALLAS3 
m5_char_select_flag = 0

m5_controlling_last_wave = 0

m5_speech_goals = 0
m5_speech_control_flag = 0 
m5_random_last_label = 0 
m5_last_label = 0 
m5_slot1 = 0 
m5_slot2 = 0 
m5_slot_load = 0 
m5_play_which_slot = 0

m5_x = 0.0
m5_y = 0.0 
m5_z = 0.0

m5_cutscene_crap = 0

m5_no_plates_flag = 0 
m5_no_plates = 0

m5_hours = 0
m5_minutes = 0

m5_enemy_accuracy = 75

//switching off car gens
SWITCH_CAR_GENERATOR gen_car6 0
SWITCH_CAR_GENERATOR gen_car7 0
SWITCH_CAR_GENERATOR gen_car10 0


// ****************************************START OF CUTSCENE********************************
CLEAR_AREA 791.7 -1627.0 12.3 10.0 TRUE
SET_CAR_DENSITY_MULTIPLIER 0.0 
SET_PED_DENSITY_MULTIPLIER 0.0 
MAKE_PLAYER_GANG_DISAPPEAR
SET_FADING_COLOUR 0 0 0																		
IF strap4_mission_passed_once_flag = 0
	CLEAR_AREA 782.5 -1619.3 12.0 5.0 0
	GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE m5_model m5_class
	IF m5_model >= 0
		CREATE_CAR m5_model 782.5 -1619.3 12.0 tw7_cutscene_car1
		SET_CAR_HEADING tw7_cutscene_car1 270.0  
	ENDIF 
	
	CLEAR_AREA 782.5 -1604.8 12.0 5.0 0
	GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE m5_model m5_class
	IF m5_model >= 0
		CREATE_CAR m5_model 782.5 -1604.8 12.0 tw7_cutscene_car2
		SET_CAR_HEADING tw7_cutscene_car2 270.0 
	ENDIF  

	LOAD_CUTSCENE STRAP4A
ELSE
	IF m5_cutscene_crap = 0
		CLEAR_AREA 2484.9 -1649.2 12.5 160.0 0
		SET_AREA_VISIBLE 3
		LOAD_CUTSCENE Strp4b1
		m5_cutscene_crap = 1
	ENDIF
ENDIF
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
MARK_CAR_AS_NO_LONGER_NEEDED tw7_cutscene_car1
MARK_CAR_AS_NO_LONGER_NEEDED tw7_cutscene_car2
CLEAR_CUTSCENE
SET_PLAYER_CONTROL player1 OFF
// ****************************************END OF CUTSCENE**********************************
// ****************************************START OF CUTSCENE********************************
IF m5_cutscene_crap = 1
	IF NOT WAS_CUTSCENE_SKIPPED
		CLEAR_AREA 2484.9 -1649.2 12.5 160.0 0
		SET_FADING_COLOUR 0 0 0																		

		REQUEST_MODEL GREENWOO
		LOAD_ALL_MODELS_NOW
		CLEAR_AREA 2503.0 -1658.8 12.3 5.0 TRUE 
		CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO GROVE4L_
		CREATE_CAR GREENWOO 2503.0 -1658.8 13.0 blocking_car1
		CHANGE_CAR_COLOUR blocking_car1 59 34 
		SET_CAR_HEADING blocking_car1 234.0
		   		
		SET_AREA_VISIBLE 0
		LOAD_CUTSCENE Strp4b2
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

		DELETE_CAR blocking_car1

		SET_PLAYER_CONTROL player1 OFF
		m5_cutscene_crap = 0
	ENDIF
ENDIF
// ****************************************END OF CUTSCENE**********************************
MAKE_PLAYER_GANG_REAPPEAR
SET_FADING_COLOUR 0 0 0
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0 
WAIT 0
//------------------REQUEST_MODELS ------------------------------
IF strap4_mission_passed_once_flag = 0
	DO_FADE 0 FADE_IN
	GOTO mission_music5_passed
ENDIF  

REQUEST_MODEL FAM2
REQUEST_MODEL FAM3
REQUEST_MODEL BALLAS2
REQUEST_MODEL BALLAS3
REQUEST_MODEL GREENWOO
REQUEST_MODEL TAHOMA
REQUEST_MODEL VOODOO
REQUEST_MODEL MOLOTOV
REQUEST_MODEL TEC9
LOAD_SPECIAL_CHARACTER 1 sweet
LOAD_SPECIAL_CHARACTER 2 ryder2

LOAD_MISSION_AUDIO 3 SOUND_HOUSE_PARTY_BASS_LOOP
WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
ENDWHILE
SET_MISSION_AUDIO_POSITION 3 2482.0 -1646.3 12.8 
PLAY_MISSION_AUDIO 3
LOAD_ALL_MODELS_NOW

REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE



/////////////////////////////// DECISION MAKER SHIT ////////////////////////////////////////////// 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2	   ////1 is BAD GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1	   ////2 is players group
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_MISSION1	   ////3 is smoke's group

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION3	   
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION3 PEDTYPE_MISSION2

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_PLAYER1	   ////2 is players group
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION3 PEDTYPE_PLAYER1	   ////3 is smoke's group
	   
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION2
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION3 PEDTYPE_MISSION3


GET_PLAYER_GROUP Player1 m5_good_group
SET_GROUP_SEPARATION_RANGE m5_good_group 1000.0

LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM m5_group_dec
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE m5_group_dec EVENT_LEADER_EXITED_CAR_AS_DRIVER
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE m5_group_dec EVENT_LEADER_ENTERED_CAR_AS_DRIVER
ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE m5_group_dec EVENT_SHOT_FIRED TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE


SET_GROUP_DECISION_MAKER m5_good_group m5_group_dec
SET_GROUP_DEFAULT_TASK_ALLOCATOR m5_good_group DEFAULT_TASK_ALLOCATOR_STAND_STILL

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH m5_ped_decisions  
COPY_CHAR_DECISION_MAKER DM_PED_EMPTY m5_empty_ped_decision_maker

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_empty_ped_decision_maker EVENT_POTENTIAL_WALK_INTO_PED
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED
						 
//sweet
CREATE_CHAR PEDTYPE_MISSION3 SPECIAL01 2485.5 -1647.7 13.3 sweet
SET_CHAR_NEVER_TARGETTED sweet TRUE  
SET_CHAR_HEADING sweet 196.6 	
SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE 
SET_CHAR_STAY_IN_SAME_PLACE sweet TRUE
GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_TEC9 3000
ADD_ARMOUR_TO_CHAR sweet 100
SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE 
SET_CHAR_DECISION_MAKER sweet m5_empty_ped_decision_maker

//car sweet moves 
CLEAR_AREA 2503.0 -1658.8 12.3 5.0 TRUE 
//m5_no_plates = GREENWOO
//GOSUB m5_my_number_plates
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO GROVE4L_
CREATE_CAR GREENWOO 2503.0 -1658.8 13.0 blocking_car1
CHANGE_CAR_COLOUR blocking_car1 59 34 
SET_CAR_HEADING blocking_car1 234.0
LOCK_CAR_DOORS blocking_car1 CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_HEAVY blocking_car1 TRUE
SET_CAR_PROOFS blocking_car1 TRUE TRUE FALSE FALSE FALSE  

//car moving guy with sweet
GENERATE_RANDOM_INT_IN_RANGE 0 3 m5_char_select_flag
CREATE_CHAR PEDTYPE_MISSION3 m5_char_select[m5_char_select_flag] 2487.1 -1647.7 13.3 m5_car_mover
SET_CHAR_NEVER_TARGETTED m5_car_mover TRUE  
SET_CHAR_HEADING m5_car_mover 158.9 	
SET_CHAR_ONLY_DAMAGED_BY_PLAYER m5_car_mover TRUE 
SET_CHAR_STAY_IN_SAME_PLACE m5_car_mover TRUE
GIVE_WEAPON_TO_CHAR m5_car_mover WEAPONTYPE_TEC9 3000 
SET_CHAR_DECISION_MAKER m5_car_mover m5_empty_ped_decision_maker

//car m5_car_mover moves
CLEAR_AREA 2496.7 -1683.4 13.0 5.0 TRUE 
m5_no_plates = VOODOO
GOSUB m5_my_number_plates
CREATE_CAR VOODOO 2496.7 -1683.4 13.0 blocking_car2
SET_CAR_HEADING blocking_car2 272.5 
LOCK_CAR_DOORS blocking_car2 CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_HEAVY blocking_car2 TRUE 
SET_CAR_PROOFS blocking_car2 TRUE TRUE FALSE FALSE FALSE  

//ryder																								
CREATE_CHAR PEDTYPE_MISSION3 SPECIAL02 2484.1 -1641.2 13.5 ryder
SET_CHAR_STAY_IN_SAME_PLACE ryder TRUE
SET_CHAR_NEVER_TARGETTED ryder TRUE  
SET_CHAR_HEADING ryder 180.0 	
GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_TEC9 3000
SET_CHAR_DECISION_MAKER ryder m5_empty_ped_decision_maker

//good gang member0
GENERATE_RANDOM_INT_IN_RANGE 0 3 m5_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 m5_char_select[m5_char_select_flag] 2486.2 -1642.2 13.5 good_gang_member[0]
SET_CHAR_STAY_IN_SAME_PLACE good_gang_member[0] TRUE
SET_CHAR_NEVER_TARGETTED good_gang_member[0] TRUE  
SET_CHAR_HEADING good_gang_member[0] 180.0 																		
GIVE_WEAPON_TO_CHAR good_gang_member[0] WEAPONTYPE_TEC9 3000
//SET_CHAR_SUFFERS_CRITICAL_HITS good_gang_member[0] FALSE		    
//ADD_ARMOUR_TO_CHAR good_gang_member[0] 100
SET_CHAR_DECISION_MAKER good_gang_member[0] m5_empty_ped_decision_maker

//good gang member1
GENERATE_RANDOM_INT_IN_RANGE 0 3 m5_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 m5_char_select[m5_char_select_flag] 2486.2 -1641.2 13.5 good_gang_member[1]
SET_CHAR_STAY_IN_SAME_PLACE good_gang_member[1] TRUE
SET_CHAR_NEVER_TARGETTED good_gang_member[1] TRUE  
SET_CHAR_HEADING good_gang_member[1] 180.0 	
GIVE_WEAPON_TO_CHAR good_gang_member[1] WEAPONTYPE_TEC9 3000
SET_CHAR_DECISION_MAKER good_gang_member[1] m5_empty_ped_decision_maker

//good gang member3
GENERATE_RANDOM_INT_IN_RANGE 0 3 m5_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 m5_char_select[m5_char_select_flag] 2486.2 -1639.2 13.5 good_gang_member[3]
SET_CHAR_STAY_IN_SAME_PLACE good_gang_member[3] TRUE
SET_CHAR_NEVER_TARGETTED good_gang_member[3] TRUE  
SET_CHAR_HEADING good_gang_member[3] 180.0 	
GIVE_WEAPON_TO_CHAR good_gang_member[3] WEAPONTYPE_TEC9 3000
SET_CHAR_DECISION_MAKER good_gang_member[3] m5_empty_ped_decision_maker

//FIRST CAR GOES TO RIGHT HAND SIDE OF THE ROAD
m5_no_plates = TAHOMA
GOSUB m5_my_number_plates
CREATE_CAR TAHOMA 2227.6 -1667.8 13.7 attcar[0]
SET_CAR_HEADING attcar[0] 342.3
SET_CAR_PROOFS attcar[0] TRUE TRUE FALSE FALSE FALSE  
LOCK_CAR_DOORS attcar[0] CARLOCK_LOCKOUT_PLAYER_ONLY
				   
GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_INSIDE_CAR attcar[0] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] bad_gang_member[0]
SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[0] TRUE
GIVE_WEAPON_TO_CHAR bad_gang_member[0] WEAPONTYPE_TEC9 3000
SET_CHAR_DECISION_MAKER bad_gang_member[0] m5_empty_ped_decision_maker
CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_bad_group 
SET_GROUP_SEPARATION_RANGE m5_bad_group 300.0
SET_GROUP_DECISION_MAKER m5_bad_group m5_group_dec
SET_GROUP_LEADER m5_bad_group bad_gang_member[0] 
SET_CHAR_ACCURACY bad_gang_member[0] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[0] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 0 bad_gang_member[1]  					   
GIVE_WEAPON_TO_CHAR bad_gang_member[1] WEAPONTYPE_TEC9 3000 
SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[1] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[1] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[1] 
SET_CHAR_ACCURACY bad_gang_member[1] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[0] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 1 bad_gang_member[2]  
SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[2] TRUE 
SET_CHAR_SUFFERS_CRITICAL_HITS bad_gang_member[2] FALSE		    
ADD_ARMOUR_TO_CHAR bad_gang_member[2] 100
GIVE_WEAPON_TO_CHAR bad_gang_member[2] WEAPONTYPE_TEC9 3000 
//GIVE_WEAPON_TO_CHAR bad_gang_member[2] WEAPONTYPE_MOLOTOV 1 
//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[2] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[2] m5_empty_ped_decision_maker
SET_CHAR_ACCURACY bad_gang_member[2] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[0] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2 bad_gang_member[3]  
GIVE_WEAPON_TO_CHAR bad_gang_member[3] WEAPONTYPE_TEC9 3000 
//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[3] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[3] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[3] 
SET_CHAR_ACCURACY bad_gang_member[3] m5_enemy_accuracy

//SECOND CAR GOES TO LEFT HAND SIDE OF THE ROAD
m5_no_plates = TAHOMA
GOSUB m5_my_number_plates
CREATE_CAR TAHOMA 2224.6 -1677.9 13.2 attcar[1]
SET_CAR_HEADING attcar[1] 342.3
SET_CAR_PROOFS attcar[1] TRUE TRUE FALSE FALSE FALSE  
LOCK_CAR_DOORS attcar[1] CARLOCK_LOCKOUT_PLAYER_ONLY

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_INSIDE_CAR attcar[1] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] bad_gang_member[4]
GIVE_WEAPON_TO_CHAR bad_gang_member[4] WEAPONTYPE_TEC9 3000 
SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[4] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[4] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[4] 
SET_CHAR_ACCURACY bad_gang_member[4] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[1] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 0 bad_gang_member[5]  
GIVE_WEAPON_TO_CHAR bad_gang_member[5] WEAPONTYPE_TEC9 3000 
//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[5] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[5] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[5] 
SET_CHAR_ACCURACY bad_gang_member[5] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[1] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 1 bad_gang_member[6]  
GIVE_WEAPON_TO_CHAR bad_gang_member[6] WEAPONTYPE_TEC9 3000 
SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[6] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[6] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[6] 
SET_CHAR_ACCURACY bad_gang_member[6] m5_enemy_accuracy

GENERATE_RANDOM_INT_IN_RANGE 3 6 m5_char_select_flag
CREATE_CHAR_AS_PASSENGER attcar[1] PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2 bad_gang_member[7]  
GIVE_WEAPON_TO_CHAR bad_gang_member[7] WEAPONTYPE_TEC9 3000 
SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[7] TRUE
SET_CHAR_DECISION_MAKER bad_gang_member[7] m5_empty_ped_decision_maker
SET_GROUP_MEMBER m5_bad_group bad_gang_member[7] 
SET_CHAR_ACCURACY bad_gang_member[7] m5_enemy_accuracy

SET_AREA_VISIBLE 0
LOAD_SCENE 2484.9 -1649.2 12.5 
CLEAR_AREA 2484.9 -1649.2 12.5 160.0 0

SET_PLAYER_CONTROL player1 OFF		
SWITCH_WIDESCREEN ON
MAKE_PLAYER_GANG_DISAPPEAR 
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

SET_MAX_FIRE_GENERATIONS 1

REQUEST_CAR_RECORDING 22
REQUEST_CAR_RECORDING 23

CLEAR_AREA 2492.4 -1632.0 12.4 1.0 TRUE
SET_CHAR_COORDINATES scplayer 2492.4 -1632.0 12.4

SET_FIXED_CAMERA_POSITION 2483.0 -1649.8 12.5 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2494.3 -1650.3 16.6 JUMP_CUT

SHUT_ALL_CHARS_UP TRUE

ENABLE_AMBIENT_CRIME FALSE

mission_music5_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_music5_passed  
	ENDIF

	ALTER_WANTED_LEVEL player1 0

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB m5_death_checks
	IF m5_deathcheck_flag = 1
		GOTO mission_music5_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Playing the BIG intro cutscene//////////////////////////////////////////////////////////////////
	IF music5_goals = 0
		IF m5_control_flag = 0

			IF NOT IS_CAR_DEAD blocking_car1
				OPEN_SEQUENCE_TASK m5_seq
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2498.5 -1652.3 12.5 PEDMOVE_RUN -1 
					TASK_ENTER_CAR_AS_DRIVER -1 blocking_car1 -1
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK sweet m5_seq 	
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF

			IF NOT IS_CHAR_DEAD m5_car_mover
				IF NOT IS_CAR_DEAD blocking_car2
					OPEN_SEQUENCE_TASK m5_seq
						//TASK_PAUSE -1 100
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 2487.4 -1657.9 12.3 PEDMOVE_RUN -1 
						TASK_ENTER_CAR_AS_DRIVER -1 blocking_car2 -1
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK m5_car_mover m5_seq 
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF
			ENDIF

			//ryder fucking off 
			IF NOT IS_CHAR_DEAD ryder									
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 500
					TASK_GO_STRAIGHT_TO_COORD -1 2486.1 -1646.7 12.9 PEDMOVE_WALK 1
					TASK_GO_STRAIGHT_TO_COORD -1 2485.1 -1652.7 12.3 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2478.4 -1649.1 12.5 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2478.2 -1631.4 12.5 PEDMOVE_RUN -1
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK ryder m5_seq 
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF

			//good_gang_member[0] - runs to left hand car
			IF NOT IS_CHAR_DEAD good_gang_member[0]
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 1000
					TASK_GO_STRAIGHT_TO_COORD -1 2487.1 -1646.7 12.9 PEDMOVE_WALK 1
					TASK_GO_STRAIGHT_TO_COORD -1 2487.1 -1654.7 12.3 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2472.3 -1652.8 12.5 PEDMOVE_RUN -1 
					TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					TASK_PAUSE -1 9000
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2466.3 -1660.7 12.2 PEDMOVE_RUN -1 
					TASK_ACHIEVE_HEADING -1 88.6  
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[0] m5_seq 
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF
				
			//good_gang_member[1] - runs to the house at the right of the cars next to smashable fence
			IF NOT IS_CHAR_DEAD good_gang_member[1]
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 1500
					TASK_GO_STRAIGHT_TO_COORD -1 2486.1 -1646.7 12.9 PEDMOVE_WALK 1
					TASK_GO_STRAIGHT_TO_COORD -1 2486.1 -1654.7 12.3 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2467.6 -1649.2 12.5 PEDMOVE_RUN -1
					TASK_ACHIEVE_HEADING -1 110.0  
					TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					TASK_TOGGLE_DUCK -1 TRUE
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[1] m5_seq 	
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF

			//good_gang_member[3] - runs to left hand car
			IF NOT IS_CHAR_DEAD good_gang_member[3]
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 3000
					TASK_GO_STRAIGHT_TO_COORD -1 2486.1 -1646.7 12.9 PEDMOVE_WALK 1
					TASK_GO_STRAIGHT_TO_COORD -1 2486.1 -1654.7 12.3 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2473.4 -1652.7 12.5 PEDMOVE_RUN -1 
					TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					TASK_PAUSE -1 9000
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2463.8 -1663.6 12.2 PEDMOVE_RUN -1 
					TASK_ACHIEVE_HEADING -1 116.3  
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[3] m5_seq 
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF

			DO_FADE 1000 FADE_IN
			
			//ryder dialogue
			m5_speech_goals = 1
			GENERATE_RANDOM_INT_IN_RANGE 0 3 m5_speech_control_flag
			m5_random_last_label = m5_speech_control_flag + 1 
			GOSUB m5_dialogue_setup 		

			m5_skip_cutscene_flag = 1		    
			SKIP_CUTSCENE_START
			timera = 0
			m5_control_flag = 1
		ENDIF

		IF m5_control_flag = 1			    
		   IF NOT IS_CHAR_DEAD sweet 
				IF timera > 3000
					//sweet dialogue
					m5_speech_goals = 2
					m5_speech_control_flag = 0
					m5_random_last_label = 2 
					GOSUB m5_dialogue_setup 		

					SET_FIXED_CAMERA_POSITION 2501.5 -1650.5 13.3 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2498.9 -1682.1 16.3 JUMP_CUT
					timera = 0 
					m5_control_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		//waiting until big smoke and m5_car_mover are in the cars
		IF m5_control_flag = 2 
			//controlling the baddies coming in		
		 	IF triggering_original_enemy_cars = 0
				IF timera > 6000 
				 	//sorting out first car of first wave - goes right of road
				 	IF NOT IS_CAR_DEAD attcar[0] 
						IF NOT IS_CHAR_DEAD bad_gang_member[0]
							OPEN_SEQUENCE_TASK m5_seq
								TASK_CAR_DRIVE_TO_COORD -1 attcar[0] 2435.3 -1661.1 12.2 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
								TASK_CAR_TEMP_ACTION -1 attcar[0] TEMPACT_TURNRIGHT 200 
								TASK_CAR_TEMP_ACTION -1 attcar[0] TEMPACT_HANDBRAKESTRAIGHT 400 
							CLOSE_SEQUENCE_TASK m5_seq										
							PERFORM_SEQUENCE_TASK bad_gang_member[0] m5_seq
							CLEAR_SEQUENCE_TASK m5_seq
						ENDIF
					ENDIF
						
					//sorting out second car of first wave - goes left of road
					IF NOT IS_CAR_DEAD attcar[1] 
						IF NOT IS_CHAR_DEAD bad_gang_member[4]
							OPEN_SEQUENCE_TASK m5_seq
								TASK_CAR_DRIVE_TO_COORD -1 attcar[1] 2440.8 -1656.9 12.2 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
								TASK_CAR_TEMP_ACTION -1 attcar[1] TEMPACT_TURNLEFT 200 
								TASK_CAR_TEMP_ACTION -1 attcar[1] TEMPACT_HANDBRAKESTRAIGHT 400 
							CLOSE_SEQUENCE_TASK m5_seq
							PERFORM_SEQUENCE_TASK bad_gang_member[4] m5_seq
							CLEAR_SEQUENCE_TASK m5_seq
						ENDIF
					ENDIF
					triggering_original_enemy_cars = 1
				ENDIF
			ENDIF
		
			//controlling the car recordings
			IF sweet_recording < 2 
			OR m5_car_mover_recording < 2
				IF NOT IS_CAR_DEAD blocking_car1 
					IF sweet_recording = 0
						IF IS_CHAR_IN_CAR sweet blocking_car1 
							DO_FADE 500 FADE_OUT	
							WHILE GET_FADING_STATUS	
							   WAIT 0
							ENDWHILE 
							GOSUB m5_death_checks
							IF m5_deathcheck_flag = 1
								GOTO mission_music5_failed
							ENDIF
							
							LOAD_SCENE 2459.2747 -1653.2631 13.0938 
							
							SET_FIXED_CAMERA_POSITION 2459.2747 -1653.2631 13.0938 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2460.0891 -1653.8428 13.1158 JUMP_CUT
							
							IF NOT IS_CAR_DEAD blocking_car1
								START_PLAYBACK_RECORDED_CAR blocking_car1 22
							ENDIF
						
							DO_FADE 500 FADE_IN	
							WHILE GET_FADING_STATUS	
							   WAIT 0
							ENDWHILE 
							GOSUB m5_death_checks
							IF m5_deathcheck_flag = 1
								GOTO mission_music5_failed
							ENDIF
							
							sweet_recording = 1
						ENDIF
					ELSE
						IF NOT IS_CAR_DEAD blocking_car1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR blocking_car1
								IF sweet_recording = 1
									OPEN_SEQUENCE_TASK m5_seq
										TASK_PAUSE -1 2000 
										TASK_LEAVE_ANY_CAR -1  
										TASK_GO_STRAIGHT_TO_COORD -1 2458.9 -1665.6 12.2 PEDMOVE_RUN -2
										TASK_GO_STRAIGHT_TO_COORD -1 2465.0 -1666.4 12.2 PEDMOVE_RUN -2
										TASK_FOLLOW_PATH_NODES_TO_COORD -1 2465.3 -1654.6 12.2 PEDMOVE_RUN -2
										TASK_ACHIEVE_HEADING -1 107.0  
										TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
										TASK_TOGGLE_DUCK -1 TRUE
									CLOSE_SEQUENCE_TASK m5_seq
									PERFORM_SEQUENCE_TASK sweet m5_seq		
									CLEAR_SEQUENCE_TASK m5_seq
									sweet_recording = 2
								ENDIF
							ENDIF 
						ENDIF
					ENDIF
				ENDIF
			
				IF NOT IS_CHAR_DEAD m5_car_mover
					IF NOT IS_CAR_DEAD blocking_car2 
						IF m5_car_mover_recording = 0
							IF IS_CHAR_IN_CAR m5_car_mover blocking_car2 
								START_PLAYBACK_RECORDED_CAR blocking_car2 23
								m5_car_mover_recording = 1
							ENDIF
						ELSE
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR blocking_car2
								IF m5_car_mover_recording = 1 
									OPEN_SEQUENCE_TASK m5_seq
										TASK_PAUSE -1 2000 
										TASK_LEAVE_ANY_CAR -1  
										TASK_GO_STRAIGHT_TO_COORD -1 2458.2 -1655.0 12.2 PEDMOVE_RUN -2
										TASK_GO_STRAIGHT_TO_COORD -1 2461.1 -1650.8 12.2 PEDMOVE_RUN -2
										TASK_FOLLOW_PATH_NODES_TO_COORD -1 2467.4 -1657.7 12.2 PEDMOVE_RUN -2
										TASK_ACHIEVE_HEADING -1 110.0  
									CLOSE_SEQUENCE_TASK m5_seq
									PERFORM_SEQUENCE_TASK m5_car_mover m5_seq		
									CLEAR_SEQUENCE_TASK m5_seq
									m5_car_mover_recording = 2
								ENDIF
							ENDIF 
						ENDIF
					ENDIF
				ENDIF
			ELSE
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS	
				   WAIT 0
				ENDWHILE 
				GOSUB m5_death_checks
				IF m5_deathcheck_flag = 1
					GOTO mission_music5_failed
				ENDIF
				m5_control_flag = 3	
			ENDIF
		ENDIF
		
		IF m5_control_flag = 3 
			
			LOAD_SCENE 2404.5 -1672.3 15.8 
			
			//creating car at end of the alleyway
			m5_no_plates = TAHOMA
			GOSUB m5_my_number_plates
			CREATE_CAR TAHOMA 2485.0 -1682.5 15.0 alleyway_car
			LOCK_CAR_DOORS alleyway_car CARLOCK_LOCKOUT_PLAYER_ONLY
			SET_CAR_HEAVY alleyway_car TRUE
			SET_CAR_PROOFS alleyway_car TRUE TRUE FALSE FALSE FALSE  
			SET_CAR_HEADING alleyway_car 90.0
			
			CAMERA_RESET_NEW_SCRIPTABLES
			CAMERA_PERSIST_POS TRUE                       
			CAMERA_PERSIST_TRACK TRUE                   
			CAMERA_SET_VECTOR_MOVE 2404.5 -1672.3 15.8 2408.6 -1672.7 15.9 5000 TRUE
			CAMERA_SET_VECTOR_TRACK 2384.8 -1665.4 13.4 2416.5 -1665.3 13.9 5000 TRUE
			
			DO_FADE 500 FADE_IN	
			WHILE GET_FADING_STATUS	
			   WAIT 0
			ENDWHILE 
			GOSUB m5_death_checks
			IF m5_deathcheck_flag = 1
				GOTO mission_music5_failed
			ENDIF
			timera = 0
			m5_control_flag = 4			
		ENDIF
		
		IF m5_control_flag = 4
			IF timera > 5800  
				m5_control_flag = 5
			ENDIF
		ENDIF	
	 
		IF m5_control_flag = 5 
			m5_skip_cutscene_flag = 0
			SKIP_CUTSCENE_END
			GOSUB m5_death_checks
			IF m5_deathcheck_flag = 1
				GOTO mission_music5_failed
			ENDIF
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			m5_speech_goals = 0
			DO_FADE 500 FADE_OUT	
			WHILE GET_FADING_STATUS	
			   WAIT 0	
			ENDWHILE 
			GOSUB m5_death_checks
			IF m5_deathcheck_flag = 1
				GOTO mission_music5_failed
			ENDIF
			
			CAMERA_RESET_NEW_SCRIPTABLES

			//If cutscene has been skipped
			IF m5_skip_cutscene_flag = 1
				
				
				IF NOT IS_CAR_DEAD attcar[0] 
					SET_CAR_COORDINATES attcar[0] 2453.1 -1663.0 12.3
					SET_CAR_HEADING attcar[0] 217.2
				ENDIF

				IF NOT IS_CAR_DEAD attcar[1] 
					SET_CAR_COORDINATES attcar[1] 2452.2 -1651.9 12.3
					SET_CAR_HEADING attcar[1] 314.2
				ENDIF

				CLEAR_PRINTS
			ENDIF

			IF IS_CAR_DEAD alleyway_car 
				//creating car at end of the alleyway
				m5_no_plates = TAHOMA
				GOSUB m5_my_number_plates
				CREATE_CAR TAHOMA 2485.0 -1682.5 15.0 alleyway_car
				LOCK_CAR_DOORS alleyway_car CARLOCK_LOCKOUT_PLAYER_ONLY
				SET_CAR_HEAVY alleyway_car TRUE
				SET_CAR_PROOFS alleyway_car TRUE TRUE FALSE FALSE FALSE  
				SET_CAR_HEADING alleyway_car 90.0
			ENDIF

			IF NOT IS_CAR_DEAD blocking_car1 
				IF IS_PLAYBACK_GOING_ON_FOR_CAR blocking_car1 
					STOP_PLAYBACK_RECORDED_CAR blocking_car1
				ENDIF 
				SET_CAR_COORDINATES blocking_car1 2462.3 -1662.4 12.1
				SET_CAR_HEADING blocking_car1 325.8
			ENDIF
			IF NOT IS_CAR_DEAD blocking_car2 
				IF IS_PLAYBACK_GOING_ON_FOR_CAR blocking_car2 
					STOP_PLAYBACK_RECORDED_CAR blocking_car2
				ENDIF 
				SET_CAR_COORDINATES blocking_car2 2463.4 -1656.1 12.0
				SET_CAR_HEADING blocking_car2 50.5
			ENDIF

			CLEAR_CHAR_TASKS_IMMEDIATELY sweet
			CLEAR_AREA 2465.3 -1654.6 12.2 1.0 TRUE
			SET_CHAR_COORDINATES sweet 2465.3 -1654.6 12.2
			SET_CHAR_HEADING sweet 107.0
			SET_CHAR_DECISION_MAKER sweet m5_ped_decisions
			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_good_group2 
			SET_GROUP_DECISION_MAKER m5_good_group2 m5_group_dec
			SET_GROUP_LEADER m5_good_group2 sweet 
			OPEN_SEQUENCE_TASK m5_seq
				TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
				TASK_TOGGLE_DUCK -1 TRUE
			CLOSE_SEQUENCE_TASK m5_seq
			PERFORM_SEQUENCE_TASK sweet m5_seq		
			CLEAR_SEQUENCE_TASK m5_seq

			IF NOT IS_CHAR_DEAD m5_car_mover
				CLEAR_CHAR_TASKS_IMMEDIATELY m5_car_mover
				CLEAR_AREA 2467.4 -1657.7 12.2 1.0 TRUE
				SET_CHAR_COORDINATES m5_car_mover 2467.4 -1657.7 12.2
				SET_CHAR_HEADING m5_car_mover 107.0
				SET_CHAR_DECISION_MAKER m5_car_mover m5_ped_decisions
				SET_GROUP_MEMBER m5_good_group2 m5_car_mover 
			ENDIF

			IF NOT IS_CHAR_DEAD good_gang_member[0] 
				CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[0] 
				CLEAR_AREA 2466.3 -1660.7 12.2 1.0 TRUE
				SET_CHAR_COORDINATES good_gang_member[0] 2466.3 -1660.7 12.2	 
				SET_CHAR_HEADING good_gang_member[0] 88.6
				SET_CHAR_DECISION_MAKER good_gang_member[0] m5_ped_decisions
				SET_GROUP_MEMBER m5_good_group good_gang_member[0] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[0] m5_seq 	
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF
			IF NOT IS_CHAR_DEAD good_gang_member[1] 
				CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[1] 
				CLEAR_AREA 2467.6 -1649.2 12.5 1.0 TRUE
				SET_CHAR_COORDINATES good_gang_member[1] 2467.6 -1649.2 12.5	 
				SET_CHAR_HEADING good_gang_member[1] 110.0
				SET_CHAR_DECISION_MAKER good_gang_member[1] m5_ped_decisions
				SET_GROUP_MEMBER m5_good_group good_gang_member[1] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[1] m5_seq 	
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF
			IF NOT IS_CHAR_DEAD good_gang_member[3] 
				CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[3] 
				CLEAR_AREA 2462.3 -1665.7 12.2 1.0 TRUE
				SET_CHAR_COORDINATES good_gang_member[3] 2462.3 -1665.7 12.2	 
				SET_CHAR_HEADING good_gang_member[3] 76.9
				SET_CHAR_DECISION_MAKER good_gang_member[3] m5_ped_decisions
				SET_GROUP_MEMBER m5_good_group good_gang_member[3] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_PLAY_ANIM -1 weapon_crouch PED 1000.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK good_gang_member[3] m5_seq 	
				CLEAR_SEQUENCE_TASK m5_seq
			ENDIF

			LOAD_SCENE 2487.3 -1650.7 12.5
			CLEAR_AREA 2469.7 -1657.0 12.3 1.0 TRUE
			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2469.7 -1657.0 12.3
			SET_CHAR_HEADING scplayer 90.0 

			CREATE_PICKUP_WITH_AMMO TEC9 PICKUP_ON_STREET 120 2466.0 -1656.1 13.3 m5_UZI_pickup 

			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 0.0 100.0 0.0 0.0 FALSE TRUE 	
		
			DELETE_CHAR ryder
			UNLOAD_SPECIAL_CHARACTER 2
			
			//sweet dialogue
			m5_speech_goals = 2
			m5_speech_control_flag = 2
			m5_random_last_label = 4 
			GOSUB m5_dialogue_setup 		

			SHUT_ALL_CHARS_UP FALSE
				
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			MAKE_PLAYER_GANG_REAPPEAR
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
		
			DO_FADE 1500 FADE_IN	
			WHILE GET_FADING_STATUS	
			   WAIT 0	
			ENDWHILE 
			GOSUB m5_death_checks
			IF m5_deathcheck_flag = 1
				GOTO mission_music5_failed
			ENDIF
		
			timera = 0
			m5_control_flag = 0
			music5_goals = 1

		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////WAVE 1 - Getting the first set of guys into place and waiting for them all to die///////////////
	IF music5_goals = 1	
		IF m5_speech_flag = 0
			IF m5_speech_goals = 0 
				PRINT ( STP4_02 ) 11000 1 //Defend your hood from the attackers.
				m5_speech_flag = 1
			ENDIF
		ENDIF
		
		//giving gang orders
		IF m5_control_flag = 0

			//sorting out first car driver of first wave - goes right of road
			IF NOT IS_CHAR_DEAD bad_gang_member[0]
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 1200 
					TASK_LEAVE_ANY_CAR -1
					TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
				CLOSE_SEQUENCE_TASK m5_seq                                                                                                                  
				PERFORM_SEQUENCE_TASK bad_gang_member[0] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[0] bad_gang_member_blip[0]
				CHANGE_BLIP_SCALE bad_gang_member_blip[0] 1
			ENDIF
		
			IF NOT IS_CHAR_DEAD bad_gang_member[1] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 500 
					//TASK_FOLLOW_PATH_NODES_TO_COORD -1 2442.9 -1672.9 12.6 PEDMOVE_RUN -1 
					TASK_LEAVE_ANY_CAR -1
					TASK_ACHIEVE_HEADING -1 295.1  
					TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[1] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[1] bad_gang_member_blip[1] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[1] 1
			ENDIF
			IF NOT IS_CHAR_DEAD bad_gang_member[2] //going to throw molotov 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 2200 
					TASK_LEAVE_ANY_CAR -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[2] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[2] bad_gang_member_blip[2] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[2] 1
			ENDIF
			IF NOT IS_CHAR_DEAD bad_gang_member[3]  
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 2000 
					TASK_LEAVE_ANY_CAR -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[3] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[3] bad_gang_member_blip[3] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[3] 1
			ENDIF

			//sorting out second car driver of first wave - goes left of road
			IF NOT IS_CHAR_DEAD bad_gang_member[4]
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 1800 
					TASK_LEAVE_ANY_CAR -1
					TASK_ACHIEVE_HEADING -1 245.3     
					TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
				CLOSE_SEQUENCE_TASK m5_seq
				PERFORM_SEQUENCE_TASK bad_gang_member[4] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[4] bad_gang_member_blip[4]
				CHANGE_BLIP_SCALE bad_gang_member_blip[4] 1
			ENDIF

			IF NOT IS_CHAR_DEAD bad_gang_member[5] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 2300 
					//TASK_FOLLOW_PATH_NODES_TO_COORD -1 2436.3 -1647.3 12.6 PEDMOVE_RUN -1 
					TASK_LEAVE_ANY_CAR -1
					TASK_ACHIEVE_HEADING -1 268.6  
					//TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[5] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[5] bad_gang_member_blip[5] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[5] 1
			ENDIF
			IF NOT IS_CHAR_DEAD bad_gang_member[6] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 2100 
					//TASK_FOLLOW_PATH_NODES_TO_COORD -1 2455.2 -1647.8 12.6 PEDMOVE_RUN -1 
					TASK_LEAVE_ANY_CAR -1
					TASK_ACHIEVE_HEADING -1 225.8  
					TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[6] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[6] bad_gang_member_blip[6] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[6] 1
			ENDIF
			IF NOT IS_CHAR_DEAD bad_gang_member[7] 
				OPEN_SEQUENCE_TASK m5_seq
					TASK_PAUSE -1 1200 
					//TASK_FOLLOW_PATH_NODES_TO_COORD -1 2445.1 -1645.0 12.6 PEDMOVE_RUN -1 
					TASK_LEAVE_ANY_CAR -1
					TASK_ACHIEVE_HEADING -1 236.0
					TASK_KILL_CHAR_ON_FOOT -1 scplayer 
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[7] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
				ADD_BLIP_FOR_CHAR bad_gang_member[7] bad_gang_member_blip[7] 
				CHANGE_BLIP_SCALE bad_gang_member_blip[7] 1
			ENDIF


			m5_control_flag = 1
		ENDIF
			
		
		//waiting for all of them to die
		IF m5_control_flag = 1
			/*
			//getting molotov guy to throw molotov
			IF NOT IS_CHAR_DEAD bad_gang_member[2] 
				IF m5_gang1_recieved_orders = 0				
					GET_SCRIPT_TASK_STATUS bad_gang_member[2] PERFORM_SEQUENCE_TASK task_status
					IF task_status = FINISHED_TASK
						OPEN_SEQUENCE_TASK m5_seq
							IF NOT IS_CHAR_DEAD good_gang_member[0]
								TASK_KILL_CHAR_ON_FOOT -1 good_gang_member[0]
							ENDIF
							TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
						CLOSE_SEQUENCE_TASK m5_seq 
						PERFORM_SEQUENCE_TASK bad_gang_member[2] m5_seq
						CLEAR_SEQUENCE_TASK m5_seq
						m5_gang1_recieved_orders = 1
					ENDIF
				ENDIF

				IF m5_gang1_recieved_orders = 1 
					GET_SCRIPT_TASK_STATUS bad_gang_member[2] PERFORM_SEQUENCE_TASK task_status
					IF task_status = FINISHED_TASK
						IF DOES_GROUP_EXIST m5_bad_group 
							SET_GROUP_MEMBER m5_bad_group bad_gang_member[2] 
						ELSE
							CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_bad_group 
							SET_GROUP_DECISION_MAKER m5_bad_group m5_group_dec
							SET_GROUP_LEADER m5_bad_group bad_gang_member[2] 
						ENDIF
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[2] FALSE 
						m5_gang1_recieved_orders = 2
					ENDIF
				ENDIF
			ENDIF
			*/
			IF IS_CHAR_DEAD bad_gang_member[0]
				IF IS_CHAR_DEAD bad_gang_member[1]
					IF IS_CHAR_DEAD bad_gang_member[2]
						IF IS_CHAR_DEAD bad_gang_member[3]
							IF IS_CHAR_DEAD bad_gang_member[4]
								IF IS_CHAR_DEAD bad_gang_member[5]
									IF IS_CHAR_DEAD bad_gang_member[6]
										IF IS_CHAR_DEAD bad_gang_member[7]
											IF IS_CHAR_DEAD bad_gang_member[8]
												IF IS_CHAR_DEAD bad_gang_member[9]
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[0] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[1] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[2] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[3] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[4] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[5] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[6] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[7] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[8] 
													MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[9] 
													REMOVE_BLIP bad_gang_member_blip[0]
													REMOVE_BLIP bad_gang_member_blip[1]
													REMOVE_BLIP bad_gang_member_blip[2]
													REMOVE_BLIP bad_gang_member_blip[3]
													REMOVE_BLIP bad_gang_member_blip[4]
													REMOVE_BLIP bad_gang_member_blip[5]
													REMOVE_BLIP bad_gang_member_blip[6]
													REMOVE_BLIP bad_gang_member_blip[7]
													REMOVE_BLIP bad_gang_member_blip[8]
													REMOVE_BLIP bad_gang_member_blip[9]
													CLEAR_PRINTS
													timera = 0
													m5_control_flag = 0
													music5_goals = 2
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
		//removing blips as each char dies
		IF m5_blip_flag < 10
			IF IS_CHAR_DEAD bad_gang_member[m5_blip_flag]
				REMOVE_BLIP bad_gang_member_blip[m5_blip_flag]
			ENDIF 

			m5_blip_flag ++
		ELSE
			m5_blip_flag = 0
		ENDIF
		
	ENDIF

	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////WAVE 2 - Guys on top of the bridge//////////////////////////////////////////////////////////////
	IF music5_goals = 2
	
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF m5_control_flag = 0
				IF timera > 2000 
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m5_speech_goals = 0

					SET_PLAYER_CONTROL player1 OFF			
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
					
					SHUT_ALL_CHARS_UP TRUE

					//CAR ON TOP OF THE BRIDGE
					CLEAR_AREA 2425.8 -1636.6 26.2 5.0 TRUE 
					m5_no_plates = TAHOMA
					GOSUB m5_my_number_plates
					CREATE_CAR TAHOMA 2425.8 -1636.6 26.2 attcar[2]
					SET_CAR_HEADING attcar[2] 166.8
											
					CLEAR_AREA 2441.6 -1658.6 25.2 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2441.6 -1658.6 25.2 bad_gang_member[0]
					SET_CHAR_ACCURACY bad_gang_member[0] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[0] 262.7
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[0] TRUE   
					GIVE_WEAPON_TO_CHAR bad_gang_member[0] WEAPONTYPE_TEC9 3000 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[0] TRUE
					CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_bad_group2 
					SET_GROUP_SEPARATION_RANGE m5_bad_group2 300.0
					SET_CHAR_HEALTH bad_gang_member[0] 10 
					SET_CHAR_DECISION_MAKER bad_gang_member[0] m5_empty_ped_decision_maker
					SET_GROUP_DECISION_MAKER m5_bad_group2 m5_group_dec
					SET_GROUP_LEADER m5_bad_group2 bad_gang_member[0] 
					ADD_BLIP_FOR_CHAR bad_gang_member[0] bad_gang_member_blip[0]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[0] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
						TASK_PAUSE -1 500 
						TASK_CLIMB -1 FALSE
						//TASK_GO_STRAIGHT_TO_COORD -1 2442.5 -1658.4 25.3 PEDMOVE_RUN -1
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[0] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq

					CLEAR_AREA 2441.6 -1660.2 25.1 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2441.6 -1660.2 25.1 bad_gang_member[1]  
					SET_CHAR_ACCURACY bad_gang_member[1] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[1] 265.9
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[1] TRUE   
					GIVE_WEAPON_TO_CHAR bad_gang_member[1] WEAPONTYPE_TEC9 3000 
					SET_CHAR_HEALTH bad_gang_member[1] 10 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[1] TRUE
					SET_CHAR_DECISION_MAKER bad_gang_member[1] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[1] 
					ADD_BLIP_FOR_CHAR bad_gang_member[1] bad_gang_member_blip[1]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[1] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
						TASK_PAUSE -1 600 
						TASK_CLIMB -1 FALSE
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[1] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq

					CLEAR_AREA 2441.6 -1665.2 24.6 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2441.6 -1665.2 24.6 bad_gang_member[2]  
					SET_CHAR_ACCURACY bad_gang_member[2] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[2] 273.6
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[2] TRUE   
					SET_CHAR_HEALTH bad_gang_member[2] 10 
					GIVE_WEAPON_TO_CHAR bad_gang_member[2] WEAPONTYPE_TEC9 3000 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[2] TRUE
					SET_CHAR_DECISION_MAKER bad_gang_member[2] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[2] 
					ADD_BLIP_FOR_CHAR bad_gang_member[2] bad_gang_member_blip[2]
					CHANGE_BLIP_SCALE bad_gang_member_blip[2] 1		
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
						TASK_PAUSE -1 1700 
						TASK_CLIMB -1 FALSE
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[2] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq

					CLEAR_AREA 2441.6 -1655.4 25.6 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2441.6 -1655.4 25.6 bad_gang_member[3]
					SET_CHAR_ACCURACY bad_gang_member[3] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[3] 280.0
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER bad_gang_member[3] TRUE   
					GIVE_WEAPON_TO_CHAR bad_gang_member[3] WEAPONTYPE_TEC9 3000 
					SET_CHAR_HEALTH bad_gang_member[3] 10 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[3] TRUE
					SET_CHAR_DECISION_MAKER bad_gang_member[3] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[3] 
					ADD_BLIP_FOR_CHAR bad_gang_member[3] bad_gang_member_blip[3]
					CHANGE_BLIP_SCALE bad_gang_member_blip[3] 1		
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
						TASK_PAUSE -1 2000 
						TASK_CLIMB -1 FALSE
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[3] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq

					//LOAD_SCENE 2441.6 -1655.4 25.6  
					
					TASK_TURN_CHAR_TO_FACE_CHAR scplayer bad_gang_member[3] 
					
					SET_FIXED_CAMERA_POSITION 2474.3 -1660.9 14.1 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2449.8 -1657.2 19.1 JUMP_CUT
				
					//sweet dialogue
					m5_speech_goals = 2
					m5_speech_control_flag = 5
					m5_random_last_label = 6 
					GOSUB m5_dialogue_setup 		

					SKIP_CUTSCENE_START 
					timera = 0
					timerb = 0
					m5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF
		
		IF m5_control_flag = 1
			IF timera > 3000
				SKIP_CUTSCENE_END
				GOSUB m5_death_checks
				IF m5_deathcheck_flag = 1
					GOTO mission_music5_failed
				ENDIF

				//If cutscene has been skipped
				IF m5_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m5_speech_goals = 0
				ENDIF

				
			
				IF NOT IS_CHAR_DEAD good_gang_member[1]
					TASK_SHOOT_AT_COORD good_gang_member[1] 2443.9 -1659.9 24.6 -2
				ENDIF  

				IF NOT IS_CHAR_DEAD good_gang_member[3]
					EXPLODE_CHAR_HEAD good_gang_member[3] 
				ENDIF 
		
				//GET_CHAR_COORDINATES scplayer m5_x m5_y m5_z
				//LOAD_SCENE m5_x m5_y m5_z  

				SHUT_ALL_CHARS_UP FALSE

				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON

				m5_speech_flag = 0
				m5_control_flag = 2
			ENDIF 
		ENDIF

		IF m5_control_flag = 2 
			IF m5_speech_flag = 0
				IF m5_speech_goals = 0 
					PRINT ( STP4_03 ) 11000 1 //There are enemies on the bridge above.
					m5_speech_flag = 1
				ENDIF
			ENDIF
		
			IF IS_CHAR_DEAD bad_gang_member[0]
				IF IS_CHAR_DEAD bad_gang_member[1]
					IF IS_CHAR_DEAD bad_gang_member[2]
						IF IS_CHAR_DEAD bad_gang_member[3]
							MARK_CAR_AS_NO_LONGER_NEEDED attcar[2] 
							MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[0] 
							MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[1] 
							MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[2] 
							MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[3] 
							REMOVE_BLIP bad_gang_member_blip[0]
							REMOVE_BLIP bad_gang_member_blip[1]
							REMOVE_BLIP bad_gang_member_blip[2]
							REMOVE_BLIP bad_gang_member_blip[3]
							CLEAR_PRINTS
							timera = 0
							m5_control_flag = 0
							music5_goals = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//removing blips as each char dies
		IF m5_blip_flag < 4
			IF IS_CHAR_DEAD bad_gang_member[m5_blip_flag]
				REMOVE_BLIP bad_gang_member_blip[m5_blip_flag]
			ENDIF 

			m5_blip_flag ++
		ELSE
			m5_blip_flag = 0
		ENDIF

	ENDIF	
		

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////WAVE 3 - Guys coming from the alleyway//////////////////////////////////////////////////////////
	IF music5_goals = 3
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF m5_control_flag = 0
				IF timera > 2000 
					CLEAR_PRINTS
				
					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS	
					   WAIT 0
					ENDWHILE 

					GOSUB m5_death_checks
					IF m5_deathcheck_flag = 1
						GOTO mission_music5_failed
					ENDIF
				
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m5_speech_goals = 0
					
					SET_PLAYER_CONTROL player1 OFF			
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

					SHUT_ALL_CHARS_UP TRUE

					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_empty_ped_decision_maker EVENT_POTENTIAL_WALK_INTO_PED
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED
					
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2473.5 -1655.2 12.1 5.0 5.0 5.0 FALSE
						CLEAR_AREA 2475.8 -1663.6 12.3 1.0 TRUE
						SET_CHAR_COORDINATES scplayer 2475.8 -1663.6 12.3
					ENDIF   
					
					IF NOT IS_CAR_DEAD blocking_car2
						CLEAR_AREA 2473.5 -1655.2 12.1 5.0 TRUE
						SET_CAR_COORDINATES blocking_car2 2473.5 -1655.2 12.1
						SET_CAR_HEADING blocking_car2 267.2
					ELSE
						CLEAR_AREA 2473.5 -1655.2 12.1 5.0 TRUE 
						m5_no_plates = VOODOO
						GOSUB m5_my_number_plates
						CREATE_CAR VOODOO 2473.5 -1655.2 12.1 blocking_car2
						SET_CAR_HEADING blocking_car2 267.2 
						LOCK_CAR_DOORS blocking_car2 CARLOCK_LOCKOUT_PLAYER_ONLY
						SET_CAR_HEAVY blocking_car2 TRUE 
						SET_CAR_PROOFS blocking_car2 TRUE TRUE FALSE FALSE FALSE  
					ENDIF
				
					//final guys coming in from alleyway
					CLEAR_AREA 2480.7 -1729.2 12.2 5.0 TRUE
					m5_no_plates = TAHOMA
					GOSUB m5_my_number_plates
					CREATE_CAR TAHOMA 2480.7 -1729.2 12.2 attcar[3]
					SET_CAR_HEADING attcar[3] 105.1
					LOCK_CAR_DOORS attcar[3] CARLOCK_LOCKOUT_PLAYER_ONLY
											
					//goes to left of alleyway and sits there
					CLEAR_AREA 2481.2 -1718.2 12.5 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2481.2 -1718.2 12.5 bad_gang_member[0]
					SET_CHAR_ACCURACY bad_gang_member[0] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[0] 353.4
					GIVE_WEAPON_TO_CHAR bad_gang_member[0] WEAPONTYPE_TEC9 3000 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[0] TRUE
					SET_CHAR_DECISION_MAKER bad_gang_member[0] m5_empty_ped_decision_maker
					CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_bad_group2 
					SET_GROUP_SEPARATION_RANGE m5_bad_group2 300.0
					SET_GROUP_DECISION_MAKER m5_bad_group2 m5_group_dec
					SET_GROUP_LEADER m5_bad_group2 bad_gang_member[0] 
					ADD_BLIP_FOR_CHAR bad_gang_member[0] bad_gang_member_blip[0]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[0] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2481.2 -1688.1 12.5 PEDMOVE_RUN -1
						TASK_TOGGLE_DUCK -1 TRUE
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						TASK_SHOOT_AT_COORD -1 2480.2 -1680.1 12.5 10000 	
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[0] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
					
					//goes to left in cutscene, standing behind ducking guy then goes to kill sweet
					CLEAR_AREA 2482.1 -1720.2 12.5 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2482.1 -1720.2 12.5 bad_gang_member[1]
					SET_CHAR_ACCURACY bad_gang_member[1] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[1] 352.8
					GIVE_WEAPON_TO_CHAR bad_gang_member[1] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER bad_gang_member[1] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[1] 
					ADD_BLIP_FOR_CHAR bad_gang_member[1] bad_gang_member_blip[1]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[1] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2482.1 -1689.8 12.5 PEDMOVE_RUN -1
						TASK_SHOOT_AT_COORD -1 2480.2 -1680.1 13.5 4000 
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[1] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq

					//goes to right ducking down in same place
					CLEAR_AREA 2481.0 -1718.2 12.5 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2481.0 -1718.2 12.5 bad_gang_member[2]
					SET_CHAR_ACCURACY bad_gang_member[2] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[2] 352.8
					GIVE_WEAPON_TO_CHAR bad_gang_member[2] WEAPONTYPE_TEC9 3000 
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[2] TRUE
					SET_CHAR_DECISION_MAKER bad_gang_member[2] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[2] 
					ADD_BLIP_FOR_CHAR bad_gang_member[2] bad_gang_member_blip[2]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[2] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2481.0 -1687.8 12.5 PEDMOVE_RUN -1
						TASK_TOGGLE_DUCK -1 TRUE
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						TASK_SHOOT_AT_COORD -1 2480.2 -1680.1 13.5 10000 
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[2] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
									
					//goes to right in cutscene, standing behind ducking guy then goes to kill player
					CLEAR_AREA 2480.3 -1719.2 12.5 5.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2480.3 -1719.2 12.5 bad_gang_member[3]
					SET_CHAR_ACCURACY bad_gang_member[3] m5_enemy_accuracy
					SET_CHAR_HEADING bad_gang_member[3] 352.8
					GIVE_WEAPON_TO_CHAR bad_gang_member[3] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER bad_gang_member[3] m5_empty_ped_decision_maker
					SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[3] 
					ADD_BLIP_FOR_CHAR bad_gang_member[3] bad_gang_member_blip[3]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[3] 1
					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2480.3 -1689.7 12.5 PEDMOVE_RUN -1
						TASK_TOGGLE_DUCK -1 TRUE
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						TASK_SHOOT_AT_COORD -1 2480.2 -1680.1 13.5 4000 
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK bad_gang_member[3] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
									   
					LOAD_SCENE_IN_DIRECTION 2478.8 -1708.1 12.9 190.0  
					
					SET_FIXED_CAMERA_POSITION 2481.0 -1682.5 12.9 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2487.4 -1700.0 16.7 JUMP_CUT
					
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS	
					   WAIT 0
					ENDWHILE 
					GOSUB m5_death_checks
					IF m5_deathcheck_flag = 1
						GOTO mission_music5_failed
					ENDIF
				
					//sweet dialogue
					m5_speech_goals = 2
					m5_speech_control_flag = 6
					m5_random_last_label = 7 
					GOSUB m5_dialogue_setup 	
					
					m5_skip_cutscene_flag = 1
					SKIP_CUTSCENE_START 
					timera = 0
					m5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF m5_control_flag = 1
			IF timera > 3000
				m5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB m5_death_checks
				IF m5_deathcheck_flag = 1
					GOTO mission_music5_failed
				ENDIF
				
				//If cutscene has been skipped
				IF m5_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m5_speech_goals = 0
				ENDIF
			
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS	
				   WAIT 0
				ENDWHILE 
				GOSUB m5_death_checks
				IF m5_deathcheck_flag = 1
					GOTO mission_music5_failed
				ENDIF
				

				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				//SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2474.3 -1674.2 12.5 
				SET_CHAR_HEADING scplayer 215.5 

				IF NOT IS_CHAR_DEAD sweet 
					CLEAR_CHAR_TASKS_IMMEDIATELY sweet
					CLEAR_AREA 2472.5 -1666.2 12.3 1.0 TRUE
					SET_CHAR_COORDINATES sweet 2472.5 -1666.2 12.3
					SET_CHAR_HEADING sweet 234.4
					SET_CHAR_STAY_IN_SAME_PLACE sweet FALSE 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_empty_ped_decision_maker
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 2476.1 -1673.4 12.5 PEDMOVE_WALK -1 
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK sweet m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD m5_car_mover 
					CLEAR_CHAR_TASKS_IMMEDIATELY m5_car_mover
					SET_CHAR_STAY_IN_SAME_PLACE m5_car_mover FALSE 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_empty_ped_decision_maker
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 2478.2 -1673.7 12.5 PEDMOVE_RUN -1 
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK m5_car_mover m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD good_gang_member[0] 
					CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[0]
					SET_CHAR_STAY_IN_SAME_PLACE good_gang_member[0] FALSE 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_empty_ped_decision_maker
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 2481.2 -1682.9 12.5 PEDMOVE_RUN -1 
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK good_gang_member[0] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD good_gang_member[1] 
					CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[1]
					SET_CHAR_STAY_IN_SAME_PLACE good_gang_member[1] FALSE 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_empty_ped_decision_maker
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_GO_STRAIGHT_TO_COORD -1 2482.2 -1682.9 12.5 PEDMOVE_RUN -1 
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK good_gang_member[1] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF

				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE m5_ped_decisions EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 0.0 100.0 0.0 0.0 FALSE TRUE 	
		
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE m5_group_dec EVENT_SHOT_FIRED TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE 	
				
				IF NOT IS_CHAR_DEAD bad_gang_member[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY bad_gang_member[0] 
					//SET_CHAR_COORDINATES bad_gang_member[0] 2481.2 -1688.1 12.5 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK bad_gang_member[0] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF 
				IF NOT IS_CHAR_DEAD bad_gang_member[1]
					CLEAR_CHAR_TASKS_IMMEDIATELY bad_gang_member[1] 
					//SET_CHAR_COORDINATES bad_gang_member[1] 2482.1 -1689.8 12.5 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK bad_gang_member[1] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF 
				IF NOT IS_CHAR_DEAD bad_gang_member[2]
					CLEAR_CHAR_TASKS_IMMEDIATELY bad_gang_member[2] 
					//SET_CHAR_COORDINATES bad_gang_member[2] 2481.0 -1687.8 12.5 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK bad_gang_member[2] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF 
				IF NOT IS_CHAR_DEAD bad_gang_member[3]
					CLEAR_CHAR_TASKS_IMMEDIATELY bad_gang_member[3] 
					CLEAR_AREA 2489.6 -1683.3 12.5 1.0 TRUE
					SET_CHAR_COORDINATES bad_gang_member[3] 2489.6 -1683.3 12.5
					SET_CHAR_HEADING bad_gang_member[3] 45.5 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_SET_CHAR_DECISION_MAKER -1 m5_ped_decisions
					CLOSE_SEQUENCE_TASK m5_seq										
					PERFORM_SEQUENCE_TASK bad_gang_member[3] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF 
				
				//behind ryders house at front  
				CLEAR_AREA 2469.4 -1708.9 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2469.4 -1708.9 12.5 bad_gang_member[4]
				SET_CHAR_ACCURACY bad_gang_member[4] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[4] 352.8
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[4] TRUE
				GIVE_WEAPON_TO_CHAR bad_gang_member[4] WEAPONTYPE_TEC9 3000 
				SET_CHAR_DECISION_MAKER bad_gang_member[4] m5_ped_decisions
				SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[4] 
				ADD_BLIP_FOR_CHAR bad_gang_member[4] bad_gang_member_blip[4]		
				CHANGE_BLIP_SCALE bad_gang_member_blip[4] 1
				OPEN_SEQUENCE_TASK m5_seq
					//TASK_TOGGLE_DUCK -1 TRUE
					//TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					//TASK_PAUSE -1 2000 
					//TASK_GO_STRAIGHT_TO_COORD -1 2472.7 -1704.5 12.5 PEDMOVE_RUN -1
					//TASK_GO_STRAIGHT_TO_COORD -1 2476.5 -1688.0 12.5 PEDMOVE_RUN -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[4] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq
			
				//behind ryders house at front  
				CLEAR_AREA 2467.1 -1710.8 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2467.1 -1710.8 12.5 bad_gang_member[5]
				SET_CHAR_ACCURACY bad_gang_member[5] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[5] 352.8
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[5] TRUE
				GIVE_WEAPON_TO_CHAR bad_gang_member[5] WEAPONTYPE_TEC9 3000 
				SET_CHAR_DECISION_MAKER bad_gang_member[5] m5_ped_decisions
				SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[5] 
				ADD_BLIP_FOR_CHAR bad_gang_member[5] bad_gang_member_blip[5]		
				CHANGE_BLIP_SCALE bad_gang_member_blip[5] 1
				OPEN_SEQUENCE_TASK m5_seq
					//TASK_TOGGLE_DUCK -1 TRUE
					//TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					//TASK_PAUSE -1 2000 
					//TASK_GO_STRAIGHT_TO_COORD -1 2472.9 -1706.1 12.5 PEDMOVE_RUN -1
					//TASK_GO_STRAIGHT_TO_COORD -1 2473.7 -1686.7 12.5 PEDMOVE_RUN -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[5] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq

				//up beside sweets at front  
				CLEAR_AREA 2529.3 -1691.6 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2529.3 -1691.6 12.5 bad_gang_member[6]
				SET_CHAR_ACCURACY bad_gang_member[6] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[6] 352.8
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[6] TRUE
				GIVE_WEAPON_TO_CHAR bad_gang_member[6] WEAPONTYPE_TEC9 3000 
				SET_CHAR_DECISION_MAKER bad_gang_member[6] m5_ped_decisions
				SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[6] 
				ADD_BLIP_FOR_CHAR bad_gang_member[6] bad_gang_member_blip[6]		
				CHANGE_BLIP_SCALE bad_gang_member_blip[6] 1
				OPEN_SEQUENCE_TASK m5_seq
					//TASK_GO_STRAIGHT_TO_COORD -1 2512.7 -1679.9 12.5 PEDMOVE_RUN -1
					//TASK_GO_STRAIGHT_TO_COORD -1 2490.3 -1678.7 12.5 PEDMOVE_RUN -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
					//IF NOT IS_CHAR_DEAD sweet 
						//TASK_KILL_CHAR_ON_FOOT -1 sweet
					//ENDIF
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[6] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq

				//up beside sweets at back  
				CLEAR_AREA 2529.7 -1690.1 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2529.7 -1690.1 12.5 bad_gang_member[7]
				SET_CHAR_ACCURACY bad_gang_member[7] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[7] 352.8
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE bad_gang_member[7] TRUE
				GIVE_WEAPON_TO_CHAR bad_gang_member[7] WEAPONTYPE_TEC9 3000 
				SET_CHAR_DECISION_MAKER bad_gang_member[7] m5_ped_decisions
				SET_GROUP_MEMBER m5_bad_group2 bad_gang_member[7] 
				ADD_BLIP_FOR_CHAR bad_gang_member[7] bad_gang_member_blip[7]		
				CHANGE_BLIP_SCALE bad_gang_member_blip[7] 1
				OPEN_SEQUENCE_TASK m5_seq
					//TASK_GO_STRAIGHT_TO_COORD -1 2511.3 -1680.3 12.5 PEDMOVE_RUN -1
					//TASK_GO_STRAIGHT_TO_COORD -1 2491.3 -1678.7 12.5 PEDMOVE_RUN -1
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
				CLOSE_SEQUENCE_TASK m5_seq 
				PERFORM_SEQUENCE_TASK bad_gang_member[7] m5_seq
				CLEAR_SEQUENCE_TASK m5_seq

				//Guys coming from behind strap's house
				CLEAR_AREA 2478.7 -1645.7 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2478.7 -1645.7 12.5 bad_gang_member[8]
				SET_CHAR_ACCURACY bad_gang_member[8] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[8] 180.0
				GIVE_WEAPON_TO_CHAR bad_gang_member[8] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[8] TRUE
				SET_CHAR_DECISION_MAKER bad_gang_member[8] m5_empty_ped_decision_maker

				CLEAR_AREA 2477.7 -1645.7 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2477.7 -1645.7 12.5 bad_gang_member[9]
				SET_CHAR_ACCURACY bad_gang_member[9] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[9] 180.0
				GIVE_WEAPON_TO_CHAR bad_gang_member[9] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[9] TRUE
				SET_CHAR_DECISION_MAKER bad_gang_member[9] m5_empty_ped_decision_maker

				CLEAR_AREA 2478.7 -1644.7 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2478.7 -1644.7 12.5 bad_gang_member[10]
				SET_CHAR_ACCURACY bad_gang_member[10] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[10] 180.0
				GIVE_WEAPON_TO_CHAR bad_gang_member[10] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[10] TRUE
				SET_CHAR_DECISION_MAKER bad_gang_member[10] m5_empty_ped_decision_maker

				CLEAR_AREA 2477.7 -1644.7 12.5 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION1 m5_char_select[m5_char_select_flag] 2477.7 -1644.7 12.5 bad_gang_member[11]
				SET_CHAR_ACCURACY bad_gang_member[11] m5_enemy_accuracy
				SET_CHAR_HEADING bad_gang_member[11] 180.0
				GIVE_WEAPON_TO_CHAR bad_gang_member[11] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[11] TRUE
				SET_CHAR_DECISION_MAKER bad_gang_member[11] m5_empty_ped_decision_maker

				SHUT_ALL_CHARS_UP FALSE
				
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				PRINT ( STP4_04 ) 11000 1 //Help Sweet defend against the last of the enemies. 
			
				DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS	
				   WAIT 0
				ENDWHILE 
				GOSUB m5_death_checks
				IF m5_deathcheck_flag = 1
					GOTO mission_music5_failed
				ENDIF
			
				timera = 0 
				m5_control_flag = 2
			ENDIF 
		ENDIF

		IF m5_control_flag = 2 
			//controlling the guys coming from behind straps house
			IF m5_controlling_last_wave = 0 
				IF IS_CHAR_DEAD bad_gang_member[0]
					IF IS_CHAR_DEAD bad_gang_member[1]
						IF IS_CHAR_DEAD bad_gang_member[2]
							IF IS_CHAR_DEAD bad_gang_member[7]
								IF IS_CHAR_DEAD bad_gang_member[8]
									m5_controlling_last_wave = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				
				IF timera > 10000
					m5_controlling_last_wave = 1
				ENDIF 
			
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2478.5 -1646.2 7.0 7.0 FALSE
					m5_controlling_last_wave = 1
				ENDIF  

				IF NOT IS_CHAR_DEAD bad_gang_member[8]  
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bad_gang_member[8] scplayer 
						m5_controlling_last_wave = 1
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD bad_gang_member[9]  
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bad_gang_member[9] scplayer 
						m5_controlling_last_wave = 1
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD bad_gang_member[10]  
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bad_gang_member[10] scplayer 
						m5_controlling_last_wave = 1
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD bad_gang_member[11]  
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bad_gang_member[11] scplayer 
						m5_controlling_last_wave = 1
					ENDIF
				ENDIF
			ENDIF

			IF m5_controlling_last_wave = 1 
				IF NOT IS_CHAR_DEAD bad_gang_member[8] 
					CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL m5_bad_group 
					SET_GROUP_SEPARATION_RANGE m5_bad_group 300.0
					SET_CHAR_DECISION_MAKER bad_gang_member[8] m5_ped_decisions
					SET_GROUP_DECISION_MAKER m5_bad_group m5_group_dec
					SET_GROUP_LEADER m5_bad_group bad_gang_member[8] 
					ADD_BLIP_FOR_CHAR bad_gang_member[8] bad_gang_member_blip[8]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[8] 1
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[8] FALSE
					TASK_KILL_CHAR_ON_FOOT bad_gang_member[8] scplayer 
				ENDIF
			
				IF NOT IS_CHAR_DEAD bad_gang_member[9] 
					SET_CHAR_DECISION_MAKER bad_gang_member[9] m5_ped_decisions
					SET_GROUP_MEMBER m5_bad_group bad_gang_member[9] 
					ADD_BLIP_FOR_CHAR bad_gang_member[9] bad_gang_member_blip[9]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[9] 1
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[9] FALSE
					TASK_KILL_CHAR_ON_FOOT bad_gang_member[9] scplayer 
				ENDIF
			
				IF NOT IS_CHAR_DEAD bad_gang_member[10] 
					SET_CHAR_DECISION_MAKER bad_gang_member[10] m5_ped_decisions
					SET_GROUP_MEMBER m5_bad_group bad_gang_member[10] 
					ADD_BLIP_FOR_CHAR bad_gang_member[10] bad_gang_member_blip[10]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[10] 1
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[10] FALSE

					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2477.3 -1651.4 12.5 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 2464.0 -1653.4 12.5 PEDMOVE_RUN -1
						TASK_KILL_CHAR_ON_FOOT -1 scplayer 
					CLOSE_SEQUENCE_TASK m5_seq                                                                                                                  
					PERFORM_SEQUENCE_TASK bad_gang_member[10] m5_seq
					CLEAR_SEQUENCE_TASK m5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD bad_gang_member[11] 
					SET_CHAR_DECISION_MAKER bad_gang_member[11] m5_ped_decisions
					SET_GROUP_MEMBER m5_bad_group bad_gang_member[11] 
					ADD_BLIP_FOR_CHAR bad_gang_member[11] bad_gang_member_blip[11]		
					CHANGE_BLIP_SCALE bad_gang_member_blip[11] 1
					SET_CHAR_STAY_IN_SAME_PLACE bad_gang_member[11] FALSE
					TASK_KILL_CHAR_ON_FOOT bad_gang_member[11] sweet 
				ENDIF
			
				//sweet dialogue
				CLEAR_PRINTS
				m5_speech_goals = 2
				m5_speech_control_flag = 7
				m5_random_last_label = 8 
				GOSUB m5_dialogue_setup 		

				m5_controlling_last_wave = 2
			ENDIF

			//waiting for everyone to die
			IF IS_CHAR_DEAD bad_gang_member[0]
				IF IS_CHAR_DEAD bad_gang_member[1]
					IF IS_CHAR_DEAD bad_gang_member[2]
						IF IS_CHAR_DEAD bad_gang_member[3]
							IF IS_CHAR_DEAD bad_gang_member[4]
								IF IS_CHAR_DEAD bad_gang_member[5]
									IF IS_CHAR_DEAD bad_gang_member[6]
										IF IS_CHAR_DEAD bad_gang_member[7]
											IF IS_CHAR_DEAD bad_gang_member[8]
												IF IS_CHAR_DEAD bad_gang_member[9]
													IF IS_CHAR_DEAD bad_gang_member[10]
														IF IS_CHAR_DEAD bad_gang_member[11]
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[0] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[1] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[2] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[3] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[4] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[5] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[6] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[7] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[8] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[9] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[10] 
															MARK_CHAR_AS_NO_LONGER_NEEDED bad_gang_member[11] 
															REMOVE_BLIP bad_gang_member_blip[0]
															REMOVE_BLIP bad_gang_member_blip[1]
															REMOVE_BLIP bad_gang_member_blip[2]
															REMOVE_BLIP bad_gang_member_blip[3]
															REMOVE_BLIP bad_gang_member_blip[4]
															REMOVE_BLIP bad_gang_member_blip[5]
															REMOVE_BLIP bad_gang_member_blip[6]
															REMOVE_BLIP bad_gang_member_blip[7]
															REMOVE_BLIP bad_gang_member_blip[8]
															REMOVE_BLIP bad_gang_member_blip[9]
															REMOVE_BLIP bad_gang_member_blip[10]
															REMOVE_BLIP bad_gang_member_blip[11]
															CLEAR_PRINTS
															timera = 0 
															m5_control_flag = 0
															music5_goals = 4 
														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//removing blips as each char dies
		IF m5_blip_flag < 12
			IF IS_CHAR_DEAD bad_gang_member[m5_blip_flag]
				REMOVE_BLIP bad_gang_member_blip[m5_blip_flag]
			ENDIF 

			m5_blip_flag ++
		ELSE
			m5_blip_flag = 0
		ENDIF
	ENDIF

	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Final Cutscene ////////////////////////////////////////////////////////////////////////////////
	
	IF music5_goals = 4
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF m5_control_flag = 0
				IF timera > 2000 
					CLEAR_PRINTS
					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS	
					   WAIT 0
					ENDWHILE 
					GOSUB m5_death_checks
					IF m5_deathcheck_flag = 1
						GOTO mission_music5_failed
					ENDIF
				
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m5_speech_goals = 0

					SET_PLAYER_CONTROL player1 OFF			
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				
					IF NOT main_visible_area = 0
						SET_AREA_VISIBLE 0
						SET_CHAR_AREA_VISIBLE scplayer 0 
					ENDIF
							
					
					SHUT_ALL_CHARS_UP TRUE
							
					CLEAR_AREA 2484.9 -1649.2 12.5 160.0 TRUE 
					
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
					SET_CHAR_COORDINATES scplayer 2484.2 -1666.2 12.3 
					SET_CHAR_HEADING scplayer 336.7 
					OPEN_SEQUENCE_TASK m5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 2486.7 -1650.1 12.3 PEDMOVE_WALK -1
						TASK_PAUSE -1 1000
						TASK_GO_STRAIGHT_TO_COORD -1 2486.7 -1646.0 12.5 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK m5_seq 
					PERFORM_SEQUENCE_TASK scplayer m5_seq 	
					CLEAR_SEQUENCE_TASK m5_seq

					IF NOT IS_CHAR_DEAD sweet 
						CLEAR_CHAR_TASKS_IMMEDIATELY sweet
						SET_CHAR_COORDINATES sweet 2487.2 -1666.1 12.3
						SET_CHAR_HEADING sweet 4.3
						OPEN_SEQUENCE_TASK m5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 2486.7 -1645.1 12.3 PEDMOVE_WALK -1
							TASK_ACHIEVE_HEADING -1 178.8 
						CLOSE_SEQUENCE_TASK m5_seq 
						PERFORM_SEQUENCE_TASK sweet m5_seq 	
						CLEAR_SEQUENCE_TASK m5_seq
					ENDIF

					IF NOT IS_CHAR_DEAD m5_car_mover 
						CLEAR_CHAR_TASKS_IMMEDIATELY m5_car_mover
						SET_CHAR_COORDINATES m5_car_mover 2488.9 -1649.0 12.5
						SET_CHAR_HEADING m5_car_mover 177.2
					ENDIF

					IF NOT IS_CHAR_DEAD good_gang_member[0] 
						CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[0]
						SET_CHAR_COORDINATES good_gang_member[0] 2484.2 -1669.1 12.3
						SET_CHAR_HEADING good_gang_member[0] 177.2
						OPEN_SEQUENCE_TASK m5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 2488.4 -1650.8 12.3 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK m5_seq 
						PERFORM_SEQUENCE_TASK good_gang_member[0] m5_seq 	
						CLEAR_SEQUENCE_TASK m5_seq
					ENDIF

					IF NOT IS_CHAR_DEAD good_gang_member[1] 
						CLEAR_CHAR_TASKS_IMMEDIATELY good_gang_member[1]
						SET_CHAR_COORDINATES good_gang_member[1] 2485.2 -1669.1 12.3
						SET_CHAR_HEADING good_gang_member[1] 177.2
						OPEN_SEQUENCE_TASK m5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 2489.4 -1650.7 12.3 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK m5_seq 
						PERFORM_SEQUENCE_TASK good_gang_member[1] m5_seq 	
						CLEAR_SEQUENCE_TASK m5_seq
					ENDIF

					//sweet dialogue
					m5_speech_goals = 3
					m5_speech_control_flag = 0
					GOSUB m5_dialogue_setup 		
				
					SET_FIXED_CAMERA_POSITION 2480.7 -1660.5 13.1 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2498.7 -1656.6 17.9 JUMP_CUT
				
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS	
					   WAIT 0
					ENDWHILE 
					GOSUB m5_death_checks
					IF m5_deathcheck_flag = 1
						GOTO mission_music5_failed
					ENDIF
					m5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF
		
		IF m5_control_flag = 1
			IF m5_speech_control_flag = 4
				
				SET_FIXED_CAMERA_POSITION 2471.5542 -1656.1665 18.3790 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2472.4253 -1655.6761 18.4020 JUMP_CUT
				
				//SET_FIXED_CAMERA_POSITION 2480.7 -1660.5 13.1 0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT 2486.5 -1646.7 16.3 JUMP_CUT
				m5_control_flag = 2
			ENDIF
		ENDIF
		
		IF m5_control_flag = 2
			IF m5_speech_goals = 0 
				timera = 0
				m5_control_flag = 3
			ENDIF
		ENDIF

		IF m5_control_flag = 3
			IF timera > 1000 
				DO_FADE 2500 FADE_OUT		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
				

				GET_TIME_OF_DAY m5_hours m5_minutes
				m5_hours += 6
				SET_TIME_OF_DAY m5_hours m5_minutes 
				DELETE_CHAR sweet
				UNLOAD_SPECIAL_CHARACTER 1
			
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 2486.6 -1649.9 12.5
				SET_CHAR_HEADING scplayer 177.8
				
				SHUT_ALL_CHARS_UP FALSE
				
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				
				WAIT 2000
			
				DO_FADE 2500 FADE_IN		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
				GOTO mission_music5_passed
			ENDIF
		ENDIF
	ENDIF

	
			
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////MISC STUFF//////////////////////////////////////////////////////////////////////////////////////
	IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2484.9 -1663.5 300.0 300.0	FALSE 
		CLEAR_PRINTS
		PRINT_NOW ( STP4_06 ) 7000 1 //Too many of your gang have been killed.
		GOTO mission_music5_failed
	ENDIF  

	//ingame dialogue
	GOSUB m5_overall_dialogue



GOTO mission_music5_loop


	
// Mission music5 failed
mission_music5_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission music5 passed
mission_music5_passed:
IF strap4_mission_passed_once_flag = 0
	REMOVE_BLIP strap_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT strap2X strap2Y strap2Z strap_blip_icon strap_contact_blip
	DO_FADE 500 FADE_IN
	PRINT_NOW ( STP4_07 ) 11000 1 //The house party will start at 8pm and finish at 5am at MC Strap's house.  Get some new threads and a haircut. 
	flag_strap_mission_counter ++	
	strap4_mission_passed_once_flag = 1
ELSE
	SET_INT_STAT PASSED_STRAP4 1
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 10//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED STRAP_4
	PLAYER_MADE_PROGRESS 1
	flag_strap_mission_counter ++
	REMOVE_BLIP strap_contact_blip
ENDIF
RETURN
		

// mission cleanup
mission_cleanup_music5:
//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
IF NOT IS_CAR_DEAD blocking_car1
	LOCK_CAR_DOORS blocking_car1 CARLOCK_UNLOCKED
	SET_CAR_PROOFS blocking_car1 FALSE FALSE FALSE FALSE FALSE
	SET_CAR_HEAVY blocking_car1 FALSE
ENDIF
IF NOT IS_CAR_DEAD blocking_car2
	LOCK_CAR_DOORS blocking_car2 CARLOCK_UNLOCKED
	SET_CAR_PROOFS blocking_car2 FALSE FALSE FALSE FALSE FALSE
	SET_CAR_HEAVY blocking_car2 FALSE
ENDIF
IF NOT IS_CAR_DEAD alleyway_car
	LOCK_CAR_DOORS alleyway_car CARLOCK_UNLOCKED
	SET_CAR_PROOFS alleyway_car FALSE FALSE FALSE FALSE FALSE
	SET_CAR_HEAVY alleyway_car FALSE
ENDIF
	
		
REMOVE_BLIP bad_gang_member_blip[0]
REMOVE_BLIP bad_gang_member_blip[1]
REMOVE_BLIP bad_gang_member_blip[2]
REMOVE_BLIP bad_gang_member_blip[3]
REMOVE_BLIP bad_gang_member_blip[4]
REMOVE_BLIP bad_gang_member_blip[5]
REMOVE_BLIP bad_gang_member_blip[6]
REMOVE_BLIP bad_gang_member_blip[7]
REMOVE_BLIP bad_gang_member_blip[8]
REMOVE_BLIP bad_gang_member_blip[9]
REMOVE_BLIP bad_gang_member_blip[10]
REMOVE_BLIP bad_gang_member_blip[11]
REMOVE_BLIP bad_gang_member_blip[12]
REMOVE_BLIP bad_gang_member_blip[13]
LA_hub_activity = 0 //ambient shit turned off
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
REMOVE_CHAR_ELEGANTLY sweet 
REMOVE_CHAR_ELEGANTLY ryder
MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED TAHOMA
MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
//switching on car gens
UNLOAD_SPECIAL_CHARACTER 1																		   
UNLOAD_SPECIAL_CHARACTER 2
SWITCH_CAR_GENERATOR gen_car6 101
SWITCH_CAR_GENERATOR gen_car7 101
SWITCH_CAR_GENERATOR gen_car10 101
REMOVE_PICKUP m5_UZI_pickup
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
m5_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD sweet
	CLEAR_PRINTS
	PRINT_NOW ( STP4_05 ) 7000 1 //You killed Sweet!	
	m5_deathcheck_flag = 1	
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
m5_my_number_plates:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 m5_no_plates_flag
	IF m5_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates got_m00_
	ENDIF 
	IF m5_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates m00tv_4u 
	ENDIF
	IF m5_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates mathew_2 
	ENDIF 
	IF m5_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates d4_dew0r 
	ENDIF 
	IF m5_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates d0de_777 
	ENDIF 
	IF m5_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates dam0_666 
	ENDIF 
	IF m5_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates C0NEY_88 
	ENDIF 
	IF m5_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates pre4cher 
	ENDIF 
	IF m5_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates dbp_4ndy 
	ENDIF 
	IF m5_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates ev1l_sly 
	ENDIF 
	IF m5_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates n1_r4v3n 
	ENDIF 
	IF m5_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates d1vx_z00 
	ENDIF 
	IF m5_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates mr_b3nn 
	ENDIF 
	IF m5_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates r3d_r4sp 
	ENDIF 
	IF m5_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates La_B0mba 
	ENDIF 
	IF m5_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates L3337_0g 
	ENDIF 
	IF m5_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates budd4h_X 
	ENDIF 
	IF m5_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates t3h_buck 
	ENDIF 
	IF m5_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates CHUNKY_1 
	ENDIF 
	IF m5_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates ev1l_bnz 
	ENDIF 
	IF m5_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates S4ND_M4N 
	ENDIF 
	IF m5_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates RKK_DBP1 
	ENDIF 
	IF m5_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates RE1_K0KU 
	ENDIF 
	IF m5_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates s3xy_jud 
	ENDIF 
	IF m5_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates sunra_93 
	ENDIF 
	IF m5_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates UG_FuX69 
	ENDIF 
	IF m5_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates Li0n_Cum 
	ENDIF 
	IF m5_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates rkk_pwnd 
	ENDIF 
	IF m5_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates haze_b0b 
	ENDIF 
	IF m5_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates t3h_fluf 
	ENDIF 
	IF m5_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates BM_4NDY_ 
	ENDIF 
	IF m5_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates BM_D34N_ 
	ENDIF 
	IF m5_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates BM_L4C3Y 
	ENDIF 
	IF m5_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates BM_D3V__ 
	ENDIF 
	IF m5_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates NU_SK00L 
	ENDIF 
	IF m5_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates G4L_AVET 
	ENDIF 
	IF m5_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR m5_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////
m5_dialogue_setup://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF m5_speech_goals = 1
	$m5_print_label[0] = &LOC4_CA // Shit, I'll go rustle up some homies!
	$m5_print_label[1] = &LOC4_CB // Oh man, I'll go get us some more back up!
	$m5_print_label[2] = &LOC4_CC // I'll go get Smoke and some Grove boys!

	m5_audio_label[0] = SOUND_LOC4_CA
	m5_audio_label[1] = SOUND_LOC4_CB
	m5_audio_label[2] = SOUND_LOC4_CC
	m5_last_label = m5_random_last_label
ENDIF

IF m5_speech_goals = 2
	$m5_print_label[0] = &LOC4_AA // Yo, get some cars and block the road!
	$m5_print_label[1] = &LOC4_AB // The rest of you get strapped!
	$m5_print_label[2] = &LOC4_AC // Pick your positions - Carl get some cover!
	$m5_print_label[3] = &LOC4_AE // Let 'em have it!
	//$m5_print_label[4] = &LOC4_AD // Steady, here they come!
	$m5_print_label[5] = &LOC4_AF // Look out, more of them above us on the bridge!
	$m5_print_label[6] = &LOC4_AG // Carl! Cover the alleyway!
	$m5_print_label[7] = &LOC4_AH // Behind us, CJ, behind us!

	m5_audio_label[0] = SOUND_LOC4_AA 
	m5_audio_label[1] = SOUND_LOC4_AB 
	m5_audio_label[2] = SOUND_LOC4_AC 
	m5_audio_label[3] = SOUND_LOC4_AE 
	//m5_audio_label[4] = SOUND_LOC4_AD 
	m5_audio_label[5] = SOUND_LOC4_AF 
	m5_audio_label[6] = SOUND_LOC4_AG 
	m5_audio_label[7] = SOUND_LOC4_AH 
	m5_last_label = m5_random_last_label
ENDIF

IF m5_speech_goals = 3
	$m5_print_label[0] = &LOC4_BA // GROVE IS KING!!
	$m5_print_label[1] = &LOC4_BB // Man, I ain't seen Ballas roll in that strength before!
	$m5_print_label[2] = &LOC4_BC // They heard Carl Johnson was running with his brother again!
	$m5_print_label[3] = &LOC4_BD // Word.
	$m5_print_label[4] = &LOC4_BE // C'mon, let's get back to the party.
	//$m5_print_label[5] = &LOC4_BF // GROVE IS KING!
	$m5_print_label[5] = &LOC4_BG // Shit, man, some party after all!

	m5_audio_label[0] = SOUND_LOC4_BA 
	m5_audio_label[1] = SOUND_LOC4_BB 
	m5_audio_label[2] = SOUND_LOC4_BC 
	m5_audio_label[3] = SOUND_LOC4_BD 
	m5_audio_label[4] = SOUND_LOC4_BE 
	//m5_audio_label[5] = SOUND_LOC4_BF 
	m5_audio_label[5] = SOUND_LOC4_BG 
	m5_last_label = 6
ENDIF

m5_slot_load = m5_speech_control_flag
m5_slot1 = 0
m5_slot2 = 0
m5_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
m5_overall_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF m5_speech_goals = 2 //sweet giving orders
	IF m5_speech_control_flag < m5_last_label
		GOSUB m5_loading_dialogue
		GOSUB m5_playing_dialogue
		IF NOT IS_CHAR_DEAD sweet
			GOSUB m5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $m5_print_label[m5_speech_control_flag] 
			m5_slot1 = 0
			m5_slot2 = 0
		ENDIF
	ELSE
		m5_speech_goals = 0
	ENDIF
ENDIF	

IF m5_speech_goals = 1 //ryder cutscene dialogue
OR m5_speech_goals = 3 //final cutscene dialogue
	IF m5_speech_control_flag < m5_last_label
		GOSUB m5_loading_dialogue
		GOSUB m5_playing_dialogue
		GOSUB m5_finishing_dialogue  
	ELSE
		m5_speech_goals = 0
	ENDIF
ENDIF	
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
m5_loading_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF m5_slot_load < m5_last_label 
	//slot 1
	IF m5_slot1 = 0
		LOAD_MISSION_AUDIO 1 m5_audio_label[m5_slot_load] 
		m5_slot_load ++ 
		m5_slot1 = 1
	ENDIF

	//slot 2
	IF m5_slot2 = 0
		LOAD_MISSION_AUDIO 2 m5_audio_label[m5_slot_load] 
		m5_slot_load ++ 
		m5_slot2 = 1
	ENDIF  
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
m5_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF m5_play_which_slot = 1 
	IF m5_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $m5_print_label[m5_speech_control_flag] ) 4500 1 //
			m5_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF m5_play_which_slot = 2 
	IF m5_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $m5_print_label[m5_speech_control_flag] ) 4500 1 //
			m5_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
m5_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF m5_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $m5_print_label[m5_speech_control_flag]
		m5_speech_control_flag ++		
		m5_play_which_slot = 2
		m5_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF m5_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $m5_print_label[m5_speech_control_flag]
		m5_speech_control_flag ++		
		m5_play_which_slot = 1
		m5_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}
		

											 













































