MISSION_START
// *****************************************************************************************
// ************************************** truth2 ******************************************* 
// *****************************************************************************************
// ********************************Full Head of Green***************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME truth2
// Mission start stuff
GOSUB mission_start_truth2
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_truth2_failed
ENDIF
GOSUB mission_cleanup_truth2
MISSION_END
{
// Variables for mission

//people
LVAR_INT t2_mothership t2_chopper t2_pilot t2_cop t2_helilight
LVAR_INT t2_sadler t2_flame_pickup t2_rocket_pickup


//blips
LVAR_INT t2_truths_farm_blip t2_weed_blips[44]

//flags
LVAR_INT t2_goals t2_control_flag t2_skip_cutscene_flag t2_deathcheck_flag     
LVAR_INT t2_weed_burnt[44] 
LVAR_INT t2_one_cop_alive_in_area
LVAR_INT t2_car_check_flag
LVAR_INT t2_city
LVAR_FLOAT t2_x t2_y t2_z t2_x2 t2_y2 t2_z2
LVAR_INT t2_faded_flag t2_drunkeness t2_beer_goggles t2_weed_check


VAR_INT t2_total_weed_burnt t2_cop_timer 

LVAR_INT t2_flamethrower_control_flag t2_flame_ammo t2_rocketlauncher_control_flag


//speech
LVAR_INT t2_speech_goals t2_speech_control_flag t2_speech_flag 
LVAR_TEXT_LABEL t2_print_label[16] 
LVAR_INT t2_audio_label[16] 
LVAR_INT t2_played_random_speech[5] t2_last_label 
LVAR_INT t2_slot1 t2_slot2 t2_slot_load t2_play_which_slot
LVAR_INT t2_random_last_label
LVAR_INT t2_storing_speech_control_number t2_storing_speech_goals_number 
//coords

//sequences/decision makers/threat lists/attractors/groups
LVAR_INT t2_seq t2_empty_ped_decision_maker   

//debug




// ****************************************Mission Start************************************
mission_start_truth2:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT TRU2
IF flag_player_on_mission = 0 
	ADD_BLIP_FOR_COORD -1111.7 -1622.7 76.4 t2_truths_farm_blip
	CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 10 -1100.3 -1640.4 76.4 t2_rocket_pickup
	CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 10 -1100.3 -1640.4 76.4 t2_flame_pickup
ENDIF
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
t2_cop_timer = 331000 //5 and a half minutes
t2_goals = 0
t2_control_flag = 0
t2_skip_cutscene_flag = 0 
t2_deathcheck_flag = 0 

t2_weed_burnt[0] = 0 
t2_weed_burnt[1] = 0 
t2_weed_burnt[2] = 0 
t2_weed_burnt[3] = 0 
t2_weed_burnt[4] = 0 
t2_weed_burnt[5] = 0 
t2_weed_burnt[6] = 0 
t2_weed_burnt[7] = 0 
t2_weed_burnt[8] = 0 
t2_weed_burnt[9] = 0 
t2_weed_burnt[10] = 0 
t2_weed_burnt[11] = 0 
t2_weed_burnt[12] = 0 
t2_weed_burnt[13] = 0 
t2_weed_burnt[14] = 0 
t2_weed_burnt[15] = 0 
t2_weed_burnt[16] = 0 
t2_weed_burnt[17] = 0 
t2_weed_burnt[18] = 0 
t2_weed_burnt[19] = 0 
t2_weed_burnt[20] = 0 
t2_weed_burnt[21] = 0 
t2_weed_burnt[22] = 0 
t2_weed_burnt[23] = 0 
t2_weed_burnt[24] = 0 
t2_weed_burnt[25] = 0 
t2_weed_burnt[26] = 0 
t2_weed_burnt[27] = 0 
t2_weed_burnt[28] = 0 
t2_weed_burnt[29] = 0 
t2_weed_burnt[30] = 0 
t2_weed_burnt[31] = 0 
t2_weed_burnt[32] = 0 
t2_weed_burnt[33] = 0 
t2_weed_burnt[34] = 0 
t2_weed_burnt[35] = 0 
t2_weed_burnt[36] = 0 
t2_weed_burnt[37] = 0 
t2_weed_burnt[38] = 0 
t2_weed_burnt[39] = 0 
t2_weed_burnt[40] = 0 
t2_weed_burnt[41] = 0 
t2_weed_burnt[42] = 0 
t2_weed_burnt[43] = 0 
t2_total_weed_burnt = 44

t2_one_cop_alive_in_area = 0
t2_car_check_flag = 0

t2_flamethrower_control_flag = 0
t2_flame_ammo = 0

t2_city = 0

t2_x = 0.0 
t2_y = 0.0 
t2_z = 0.0 
t2_x2 = 0.0 
t2_y2 = 0.0 
t2_z2 = 0.0

t2_faded_flag = 0
t2_drunkeness = 0 
t2_beer_goggles = 0
t2_weed_check = 44

t2_speech_goals = 0
t2_speech_control_flag = 0
t2_speech_flag = 0
t2_played_random_speech[0] = 0
t2_played_random_speech[1] = 0
t2_played_random_speech[2] = 0
t2_played_random_speech[3] = 0
t2_played_random_speech[4] = 0
t2_last_label = 0
t2_slot1 = 0 
t2_slot2 = 0 
t2_slot_load = 0
t2_play_which_slot = 0


t2_rocketlauncher_control_flag = 0

t2_storing_speech_control_number = 0
t2_storing_speech_goals_number = 0

t2_random_last_label = 0





// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
CLEAR_AREA -1110.2 -1638.5 76.0 20.0 TRUE
LOAD_CUTSCENE TRUTH_2
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
SET_PLAYER_CONTROL player1 OFF
MAKE_PLAYER_GANG_REAPPEAR
// ****************************************END OF CUTSCENE**********************************

SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------
REQUEST_MODEL CAMPER
REQUEST_MODEL SADLER

REQUEST_MODEL POLMAV
REQUEST_MODEL CSHER 

REQUEST_MODEL FLAME
REQUEST_MODEL COLT45

LOAD_SPECIAL_CHARACTER 1 truth

REMOVE_IPL truthsfarm 
REQUEST_IPL	truthsfarm

LOAD_ALL_MODELS_NOW

SET_MAX_FIRE_GENERATIONS 2

CLEAR_AREA -913.3 -1720.5 76.6 300.0 TRUE  /////debug  

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY t2_empty_ped_decision_maker 

CLEAR_AREA -913.3 -1720.5 76.6 1.0 TRUE
CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
SET_CHAR_COORDINATES scplayer -913.3 -1720.5 76.6
GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_FLAMETHROWER 3000

CLEAR_AREA -1079.4043 -1640.6936 75.3750 20.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 -1079.4043 -1640.6936 75.3750 truth
SET_CHAR_DROPS_WEAPONS_WHEN_DEAD truth FALSE 
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR truth FALSE
SET_CHAR_DECISION_MAKER truth t2_empty_ped_decision_maker
SET_CHAR_HEADING truth 299.7411
GIVE_WEAPON_TO_CHAR truth WEAPONTYPE_FLAMETHROWER 3000
SET_CHAR_NEVER_TARGETTED truth TRUE 
SET_CHAR_CANT_BE_DRAGGED_OUT truth TRUE  
OPEN_SEQUENCE_TASK t2_seq	
	TASK_GO_STRAIGHT_TO_COORD -1 -1070.1 -1636.5 75.4 PEDMOVE_RUN -1
	TASK_SHOOT_AT_COORD -1 -1062.2 -1631.1 75.3 -1 
CLOSE_SEQUENCE_TASK t2_seq
PERFORM_SEQUENCE_TASK truth t2_seq
CLEAR_SEQUENCE_TASK t2_seq

CLEAR_AREA -1077.6 -1650.2 75.3 20.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR CAMPER OUTTHERE
CREATE_CAR CAMPER -1077.6 -1650.2 75.3 t2_mothership
CHANGE_CAR_COLOUR t2_mothership 1 1 
GIVE_VEHICLE_PAINTJOB t2_mothership 0
SET_CAN_RESPRAY_CAR t2_mothership FALSE

LOCK_CAR_DOORS t2_mothership CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_TRACTION t2_mothership 2.0
SET_CAR_HEADING t2_mothership 256.4 
FREEZE_CAR_POSITION t2_mothership TRUE
SET_CAR_PROOFS t2_mothership FALSE FALSE TRUE FALSE FALSE 

CLEAR_AREA -946.6 -1735.0 76.9 5.0 TRUE
CREATE_CAR SADLER -946.6 -1735.0 76.9 t2_sadler
SET_CAR_HEADING t2_sadler 1.4 
SET_CAR_PROOFS t2_sadler FALSE FALSE TRUE FALSE FALSE

CLEAR_AREA -918.5 -1751.1 97.3 5.0 TRUE
CREATE_CAR POLMAV -918.5 -1751.1 97.3 t2_chopper
SET_HELI_BLADES_FULL_SPEED t2_chopper 
CREATE_CHAR_INSIDE_CAR t2_chopper PEDTYPE_MISSION1 CSHER t2_pilot 
CREATE_SEARCHLIGHT_ON_VEHICLE t2_chopper 0.0 0.0 -1.0 -936.6 -1723.2 76.8 6.0 1.0 t2_helilight
POINT_SEARCHLIGHT_AT_CHAR t2_helilight truth 10.0
HELI_FOLLOW_ENTITY t2_chopper truth -1 20.0
//ACTIVATE_HELI_SPEED_CHEAT t2_chopper 10
LOCK_CAR_DOORS t2_chopper CARLOCK_LOCKED
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE t2_pilot FALSE
SET_CHAR_DECISION_MAKER t2_pilot t2_empty_ped_decision_maker

ADD_BLIP_FOR_COORD -1062.1 -1631.7 75.3 t2_weed_blips[0]    
CHANGE_BLIP_SCALE t2_weed_blips[0] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[0] COORD_BLIP_APPEARANCE_ENEMY
  
ADD_BLIP_FOR_COORD -1052.3 -1631.7 75.3 t2_weed_blips[1]    
CHANGE_BLIP_SCALE t2_weed_blips[1] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[1] COORD_BLIP_APPEARANCE_ENEMY 
 
ADD_BLIP_FOR_COORD -1042.4 -1631.7 75.3 t2_weed_blips[2]    
CHANGE_BLIP_SCALE t2_weed_blips[2] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[2] COORD_BLIP_APPEARANCE_ENEMY 
 
ADD_BLIP_FOR_COORD -1032.6 -1631.7 75.3 t2_weed_blips[3]    									   
CHANGE_BLIP_SCALE t2_weed_blips[3] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[3] COORD_BLIP_APPEARANCE_ENEMY
 							   
ADD_BLIP_FOR_COORD -1023.1 -1631.7 75.3 t2_weed_blips[4]    									   
CHANGE_BLIP_SCALE t2_weed_blips[4] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[4] COORD_BLIP_APPEARANCE_ENEMY
 							   
ADD_BLIP_FOR_COORD -1062.1 -1622.2 75.3 t2_weed_blips[5]   										   
CHANGE_BLIP_SCALE t2_weed_blips[5] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[5] COORD_BLIP_APPEARANCE_ENEMY 
							   
ADD_BLIP_FOR_COORD -1052.3 -1622.2 75.3 t2_weed_blips[6]   										   
CHANGE_BLIP_SCALE t2_weed_blips[6] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[6] COORD_BLIP_APPEARANCE_ENEMY 
							   
ADD_BLIP_FOR_COORD -1042.4 -1622.2 75.3 t2_weed_blips[7]   										   
CHANGE_BLIP_SCALE t2_weed_blips[7] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[7] COORD_BLIP_APPEARANCE_ENEMY 
							   
ADD_BLIP_FOR_COORD -1032.6 -1622.2 75.3 t2_weed_blips[8]   										   
CHANGE_BLIP_SCALE t2_weed_blips[8] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[8] COORD_BLIP_APPEARANCE_ENEMY 
							   
ADD_BLIP_FOR_COORD -1023.1 -1622.2 75.3 t2_weed_blips[9]  										   
CHANGE_BLIP_SCALE t2_weed_blips[9] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[9] COORD_BLIP_APPEARANCE_ENEMY  
						   
ADD_BLIP_FOR_COORD -991.92 -1703.1 75.3 t2_weed_blips[10]  										   
CHANGE_BLIP_SCALE t2_weed_blips[10] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[10] COORD_BLIP_APPEARANCE_ENEMY	
						   
ADD_BLIP_FOR_COORD -982.38 -1703.1 75.3 t2_weed_blips[11]  										   
CHANGE_BLIP_SCALE t2_weed_blips[11] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[11] COORD_BLIP_APPEARANCE_ENEMY 
						   
ADD_BLIP_FOR_COORD -991.92 -1693.6 75.3 t2_weed_blips[12] 										   
CHANGE_BLIP_SCALE t2_weed_blips[12] 1 															   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[12] COORD_BLIP_APPEARANCE_ENEMY	
						   
ADD_BLIP_FOR_COORD -982.38 -1693.6 75.3 t2_weed_blips[13]  										   
CHANGE_BLIP_SCALE t2_weed_blips[13] 1 															   	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[13] COORD_BLIP_APPEARANCE_ENEMY	
						   	 
ADD_BLIP_FOR_COORD -991.92 -1684.1 75.3 t2_weed_blips[14]   										 
CHANGE_BLIP_SCALE t2_weed_blips[14] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[14] COORD_BLIP_APPEARANCE_ENEMY	
							 
ADD_BLIP_FOR_COORD -982.38 -1684.1 75.3 t2_weed_blips[15]   										 
CHANGE_BLIP_SCALE t2_weed_blips[15] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[15] COORD_BLIP_APPEARANCE_ENEMY 
							 
ADD_BLIP_FOR_COORD -1011.8 -1667.6 75.3 t2_weed_blips[16]   											 
CHANGE_BLIP_SCALE t2_weed_blips[16] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[16] COORD_BLIP_APPEARANCE_ENEMY 
							 
ADD_BLIP_FOR_COORD -1011.8 -1658.1 75.3 t2_weed_blips[17]   											 
CHANGE_BLIP_SCALE t2_weed_blips[17] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[17] COORD_BLIP_APPEARANCE_ENEMY
 							 
ADD_BLIP_FOR_COORD -1011.8 -1648.7 75.3 t2_weed_blips[18]  											 
CHANGE_BLIP_SCALE t2_weed_blips[18] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[18] COORD_BLIP_APPEARANCE_ENEMY
 							 
ADD_BLIP_FOR_COORD -1000.7 -1667.6 75.3 t2_weed_blips[19]   											 
CHANGE_BLIP_SCALE t2_weed_blips[19] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[19] COORD_BLIP_APPEARANCE_ENEMY
								 
ADD_BLIP_FOR_COORD -1000.7 -1658.1 75.3 t2_weed_blips[20]  											 
CHANGE_BLIP_SCALE t2_weed_blips[20] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[20] COORD_BLIP_APPEARANCE_ENEMY	
							 
ADD_BLIP_FOR_COORD -1000.7 -1648.7 75.3 t2_weed_blips[21]  											 
CHANGE_BLIP_SCALE t2_weed_blips[21] 1 																 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[21] COORD_BLIP_APPEARANCE_ENEMY 
							 
ADD_BLIP_FOR_COORD -1011.8 -1672.3 75.3 t2_weed_blips[22]  
CHANGE_BLIP_SCALE t2_weed_blips[22] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[22] COORD_BLIP_APPEARANCE_ENEMY 

ADD_BLIP_FOR_COORD -991.92 -1688.8 75.3 t2_weed_blips[23]  												 
CHANGE_BLIP_SCALE t2_weed_blips[23] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[23] COORD_BLIP_APPEARANCE_ENEMY
  								 
ADD_BLIP_FOR_COORD -982.38 -1688.8 75.3 t2_weed_blips[24]  												 
CHANGE_BLIP_SCALE t2_weed_blips[24] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[24] COORD_BLIP_APPEARANCE_ENEMY
 								 
ADD_BLIP_FOR_COORD -982.38 -1698.2 75.3 t2_weed_blips[25]  												 
CHANGE_BLIP_SCALE t2_weed_blips[25] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[25] COORD_BLIP_APPEARANCE_ENEMY 
								 
ADD_BLIP_FOR_COORD -991.92 -1698.2 75.3 t2_weed_blips[26]  												 
CHANGE_BLIP_SCALE t2_weed_blips[26] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[26] COORD_BLIP_APPEARANCE_ENEMY 
								 
ADD_BLIP_FOR_COORD -982.38 -1707.7 75.3 t2_weed_blips[27]  												 
CHANGE_BLIP_SCALE t2_weed_blips[27] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[27] COORD_BLIP_APPEARANCE_ENEMY 
								 
ADD_BLIP_FOR_COORD -1011.8 -1662.8 75.3 t2_weed_blips[28]  												 
CHANGE_BLIP_SCALE t2_weed_blips[28] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[28] COORD_BLIP_APPEARANCE_ENEMY	
								 
ADD_BLIP_FOR_COORD -1000.7 -1672.3 75.3 t2_weed_blips[29]  												 
CHANGE_BLIP_SCALE t2_weed_blips[29] 1 																	 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[29] COORD_BLIP_APPEARANCE_ENEMY 
								 
ADD_BLIP_FOR_COORD -1011.8 -1653.4 75.3 t2_weed_blips[30]  								   
CHANGE_BLIP_SCALE t2_weed_blips[30] 1 													   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[30] COORD_BLIP_APPEARANCE_ENEMY 
 				   
ADD_BLIP_FOR_COORD -1032.6 -1636.3 75.3 t2_weed_blips[31]  								   
CHANGE_BLIP_SCALE t2_weed_blips[31] 1 													   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[31] COORD_BLIP_APPEARANCE_ENEMY 
				   
ADD_BLIP_FOR_COORD -1052.3 -1636.3 75.3 t2_weed_blips[32]  								   
CHANGE_BLIP_SCALE t2_weed_blips[32] 1 													   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[32] COORD_BLIP_APPEARANCE_ENEMY	
				   
ADD_BLIP_FOR_COORD -1042.4 -1636.3 75.3 t2_weed_blips[33]  								   
CHANGE_BLIP_SCALE t2_weed_blips[33] 1 													   
SET_COORD_BLIP_APPEARANCE t2_weed_blips[33] COORD_BLIP_APPEARANCE_ENEMY 
 				   
ADD_BLIP_FOR_COORD -1062.1 -1626.8 75.3 t2_weed_blips[34]  								   
CHANGE_BLIP_SCALE t2_weed_blips[34] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[34] COORD_BLIP_APPEARANCE_ENEMY
 
ADD_BLIP_FOR_COORD -991.92 -1707.7 75.3 t2_weed_blips[35]  
CHANGE_BLIP_SCALE t2_weed_blips[35] 1 															  
SET_COORD_BLIP_APPEARANCE t2_weed_blips[35] COORD_BLIP_APPEARANCE_ENEMY 
						  
ADD_BLIP_FOR_COORD -1023.1 -1626.8 75.3 t2_weed_blips[36]  										  
CHANGE_BLIP_SCALE t2_weed_blips[36] 1 															  
SET_COORD_BLIP_APPEARANCE t2_weed_blips[36] COORD_BLIP_APPEARANCE_ENEMY
 						  
ADD_BLIP_FOR_COORD -1000.7 -1662.8 75.3 t2_weed_blips[37]  										  
CHANGE_BLIP_SCALE t2_weed_blips[37] 1 															  
SET_COORD_BLIP_APPEARANCE t2_weed_blips[37] COORD_BLIP_APPEARANCE_ENEMY 
						  
ADD_BLIP_FOR_COORD -1000.7 -1653.4 75.3 t2_weed_blips[38]  
CHANGE_BLIP_SCALE t2_weed_blips[38] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[38] COORD_BLIP_APPEARANCE_ENEMY 

ADD_BLIP_FOR_COORD -1032.6 -1626.8 75.3 t2_weed_blips[39]  
CHANGE_BLIP_SCALE t2_weed_blips[39] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[39] COORD_BLIP_APPEARANCE_ENEMY 

ADD_BLIP_FOR_COORD -1042.4 -1626.8 75.3 t2_weed_blips[40]  
CHANGE_BLIP_SCALE t2_weed_blips[40] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[40] COORD_BLIP_APPEARANCE_ENEMY  

ADD_BLIP_FOR_COORD -1052.3 -1626.8 75.3 t2_weed_blips[41]  
CHANGE_BLIP_SCALE t2_weed_blips[41] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[41] COORD_BLIP_APPEARANCE_ENEMY 

ADD_BLIP_FOR_COORD -1062.1 -1636.3 75.3 t2_weed_blips[42]  
CHANGE_BLIP_SCALE t2_weed_blips[42] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[42] COORD_BLIP_APPEARANCE_ENEMY
 
ADD_BLIP_FOR_COORD -1023.1 -1636.3 75.3 t2_weed_blips[43]  
CHANGE_BLIP_SCALE t2_weed_blips[43] 1 
SET_COORD_BLIP_APPEARANCE t2_weed_blips[43] COORD_BLIP_APPEARANCE_ENEMY  

//DISPLAY_ONSCREEN_COUNTER_WITH_STRING t2_total_weed_burnt COUNTER_DISPLAY_NUMBER TRU2_05 
DISPLAY_ONSCREEN_TIMER_WITH_STRING t2_cop_timer TIMER_DOWN TRU2_06  // Cops arrival

SET_CHAR_COORDINATES scplayer -1089.9 -1644.1 75.4 
SET_CHAR_HEADING scplayer 283.3 
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT

SET_PLAYER_CONTROL player1 ON
DO_FADE 500 FADE_IN


PRINT ( TRU2_01 ) 7000 1 //Go and help The Truth destroy the weed.
PRINT ( TRU2_02 ) 7000 1 //To burn a weed field, cover it in flames.
/*
VIEW_INTEGER_VARIABLE t2_speech_goals t2_speech_goals 
VIEW_INTEGER_VARIABLE t2_speech_control_flag t2_speech_control_flag 
VIEW_INTEGER_VARIABLE t2_storing_speech_goals_number t2_storing_speech_goals_number 
VIEW_INTEGER_VARIABLE t2_storing_speech_control_number t2_storing_speech_control_number 
VIEW_INTEGER_VARIABLE t2_last_label t2_last_label
VIEW_INTEGER_VARIABLE t2_slot1 t2_slot1
VIEW_INTEGER_VARIABLE t2_slot2 t2_slot2
*/

timera = 0
timerb = 0

mission_truth2_loop:
WAIT 0

	GOSUB t2_drunken_filter
	
	//clear the wanted level on our way to san fierro, slow ride....
	CLEAR_WANTED_LEVEL player1

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_truth2_passed  
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB t2_death_checks
	IF t2_deathcheck_flag = 1
		GOTO mission_truth2_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// WAITING FOR PLAYER TO BURN WEED ////////////////////////////////////////
	IF t2_goals = 0  
		//////////////////DEBUG////////////////////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			t2_speech_goals = 0
			IF t2_control_flag = 0
				CLEAR_PRINTS
				t2_control_flag = 1
			ENDIF
			SET_CHAR_COORDINATES scplayer -1104.7 -1648.0 75.4
			t2_total_weed_burnt = 0
		ENDIF	
		
		IF t2_control_flag = 0	
			IF t2_total_weed_burnt < 20
				CLEAR_PRINTS 
				t2_speech_goals = 1
				t2_speech_control_flag = 0
				GOSUB t2_dialogue_setup 
				t2_control_flag = 1				
				timera = 0 
			ENDIF
		ENDIF

		IF t2_control_flag = 1
			IF t2_speech_goals = 0  
				TASK_ENTER_CAR_AS_DRIVER truth t2_mothership -1
				t2_control_flag = 2
			ENDIF
		ENDIF

		////////checking if player has used up all his flamethrower////////////////
		GOSUB t2_flamethrower
		
		IF t2_weed_burnt[0] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1062.1 -1631.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[0] 
				t2_total_weed_burnt --
				t2_weed_burnt[0] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[1] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1052.3 -1631.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[1] 
				t2_total_weed_burnt --
				t2_weed_burnt[1] = 1
			ENDIF
		ENDIF
		
		IF t2_weed_burnt[2] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1042.4 -1631.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[2] 
				t2_total_weed_burnt --
				t2_weed_burnt[2] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[3] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1032.6 -1631.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[3] 
				t2_total_weed_burnt --
				t2_weed_burnt[3] = 1
			ENDIF
		ENDIF

		IF t2_weed_burnt[4] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1023.1 -1631.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[4] 
				t2_total_weed_burnt --
				t2_weed_burnt[4] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[5] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1062.1 -1622.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[5] 
				t2_total_weed_burnt --
				t2_weed_burnt[5] = 1
			ENDIF
		ENDIF

		IF t2_weed_burnt[6] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1052.3 -1622.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[6] 
				t2_total_weed_burnt --
				t2_weed_burnt[6] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[7] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1042.4 -1622.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[7] 
				t2_total_weed_burnt --
				t2_weed_burnt[7] = 1
			ENDIF
		ENDIF
		
		IF t2_weed_burnt[8] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1032.6 -1622.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[8] 
				t2_total_weed_burnt --
				t2_weed_burnt[8] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[9] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1023.1 -1622.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[9] 
				t2_total_weed_burnt --
				t2_weed_burnt[9] = 1
			ENDIF
		ENDIF

		IF t2_weed_burnt[10] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1703.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[10] 
				t2_total_weed_burnt --
				t2_weed_burnt[10] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[11] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1703.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[11] 
				t2_total_weed_burnt --
				t2_weed_burnt[11] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[12] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1693.6 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[12] 
				t2_total_weed_burnt --
				t2_weed_burnt[12] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[13] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1693.6 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[13] 
				t2_total_weed_burnt --
				t2_weed_burnt[13] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[14] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1684.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[14] 
				t2_total_weed_burnt --
				t2_weed_burnt[14] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[15] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1684.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[15] 
				t2_total_weed_burnt --
				t2_weed_burnt[15] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[16] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1667.6 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[16] 
				t2_total_weed_burnt --
				t2_weed_burnt[16] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[17] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1658.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[17] 
				t2_total_weed_burnt --
				t2_weed_burnt[17] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[18] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1648.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[18] 
				t2_total_weed_burnt --
				t2_weed_burnt[18] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[19] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1667.6 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[19] 
				t2_total_weed_burnt --
				t2_weed_burnt[19] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[20] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1658.1 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[20] 
				t2_total_weed_burnt --
				t2_weed_burnt[20] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[21] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1648.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[21] 
				t2_total_weed_burnt --
				t2_weed_burnt[21] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[22] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1672.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[22] 
				t2_total_weed_burnt --
				t2_weed_burnt[22] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[23] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1688.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[23] 
				t2_total_weed_burnt --
				t2_weed_burnt[23] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[24] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1688.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[24] 
				t2_total_weed_burnt --
				t2_weed_burnt[24] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[25] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1698.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[25] 
				t2_total_weed_burnt --
				t2_weed_burnt[25] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[26] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1698.2 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[26] 
				t2_total_weed_burnt --
				t2_weed_burnt[26] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[27] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -982.38 -1707.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[27] 
				t2_total_weed_burnt --
				t2_weed_burnt[27] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[28] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1662.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[28] 
				t2_total_weed_burnt --
				t2_weed_burnt[28] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[29] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1672.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[29] 
				t2_total_weed_burnt --
				t2_weed_burnt[29] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[30] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1011.8 -1653.4 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[30] 
				t2_total_weed_burnt --
				t2_weed_burnt[30] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[31] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1032.6 -1636.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[31] 
				t2_total_weed_burnt --
				t2_weed_burnt[31] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[32] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1052.3 -1636.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[32] 
				t2_total_weed_burnt --
				t2_weed_burnt[32] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[33] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1042.4 -1636.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[33] 
				t2_total_weed_burnt --
				t2_weed_burnt[33] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[34] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1062.1 -1626.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[34] 
				t2_total_weed_burnt --
				t2_weed_burnt[34] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[35] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -991.92 -1707.7 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[35] 
				t2_total_weed_burnt --
				t2_weed_burnt[35] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[36] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1023.1 -1626.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[36] 
				t2_total_weed_burnt --
				t2_weed_burnt[36] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[37] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1662.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[37] 
				t2_total_weed_burnt --
				t2_weed_burnt[37] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[38] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1000.7 -1653.4 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[38] 
				t2_total_weed_burnt --
				t2_weed_burnt[38] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[39] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1032.6 -1626.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[39] 
				t2_total_weed_burnt --
				t2_weed_burnt[39] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[40] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1042.4 -1626.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[40] 
				t2_total_weed_burnt --
				t2_weed_burnt[40] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[41] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1052.3 -1626.8 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[41] 
				t2_total_weed_burnt --
				t2_weed_burnt[41] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[42] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1062.1 -1636.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[42] 
				t2_total_weed_burnt --
				t2_weed_burnt[42] = 1
			ENDIF
		ENDIF
		IF t2_weed_burnt[43] = 0
			IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED -1023.1 -1636.3 75.3 1.0 GRASSPLANT FALSE TRUE 
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP t2_weed_blips[43] 
				t2_total_weed_burnt --
				t2_weed_burnt[43] = 1
			ENDIF
		ENDIF

		IF t2_total_weed_burnt = 0
			MARK_MODEL_AS_NO_LONGER_NEEDED FLAME
			//CLEAR_ONSCREEN_COUNTER t2_total_weed_burnt
			REMOVE_BLIP t2_truths_farm_blip 
			ADD_BLIP_FOR_COORD -1075.5 -1648.5 75.1 t2_truths_farm_blip
			SET_COORD_BLIP_APPEARANCE t2_truths_farm_blip COORD_BLIP_APPEARANCE_FRIEND
			PRINT ( TRU2_14 ) 11000 1 // Go and speak to the Truth.
			t2_faded_flag = 0
			t2_control_flag = 0
			t2_goals = 1
		ENDIF	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// Blowing up the cop helicopter //////////////////////////////////////////
	IF t2_goals = 1 
		//waiting for the player to get the rocket launcher from the mothership in cutscene
		IF t2_control_flag = 0
			IF NOT IS_CAR_DEAD t2_chopper 
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -1075.5 -1648.0 75.1 1.2 1.2 2.0 TRUE     
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					t2_speech_goals = 0
				
					SET_PLAYER_CONTROL player1 OFF							 
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

					SHUT_ALL_CHARS_UP TRUE

					SET_PLAYER_DRUNKENNESS player1 0

					REMOVE_PICKUP t2_flame_pickup 
				
					FREEZE_ONSCREEN_TIMER TRUE
					
					REQUEST_MODEL ROCKETLA
					LOAD_ALL_MODELS_NOW
					
					CLEAR_AREA -1075.5 -1648.0 75.1 5.0 TRUE
					SET_CHAR_COORDINATES scplayer -1075.5 -1648.0 75.1 
					SET_CHAR_HEADING scplayer 157.1
					TASK_PLAY_ANIM scplayer idle_chat PED 6.0 TRUE FALSE FALSE FALSE 4000
					
					SET_FIXED_CAMERA_POSITION -1071.3 -1647.8 75.4 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -1078.8 -1648.8 76.3 JUMP_CUT
					CLEAR_PRINTS
					
					t2_skip_cutscene_flag = 1
					SKIP_CUTSCENE_START
					
					t2_speech_goals = 8
					t2_speech_control_flag = 0
					GOSUB t2_dialogue_setup
					t2_control_flag = 1
				ENDIF
			ELSE
				FREEZE_CAR_POSITION t2_mothership FALSE
				TASK_CAR_DRIVE_TO_COORD truth t2_mothership -939.5 -1718.8 76.7 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS //at gate
				t2_control_flag = 5
			ENDIF
		ENDIF

		IF t2_control_flag = 1
			IF t2_speech_goals =  0  
				CLEAR_CHAR_TASKS scplayer
				TASK_GO_STRAIGHT_TO_COORD scplayer -1081.4 -1650.3 75.4 PEDMOVE_WALK -1
				t2_control_flag = 2
			ENDIF
		ENDIF
		
		IF t2_control_flag = 2
			GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD task_status		
			IF task_status = FINISHED_TASK
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_ROCKETLAUNCHER 10  
				t2_speech_goals = 9
				t2_speech_control_flag = 0
				GOSUB t2_dialogue_setup
				t2_control_flag = 3
			ENDIF
		ENDIF
		
		IF t2_control_flag = 3
			IF t2_speech_control_flag = 1  
				TASK_GO_STRAIGHT_TO_COORD scplayer -1075.5 -1648.0 75.1 PEDMOVE_WALK -1
				t2_control_flag = 4
			ENDIF
		ENDIF

		IF t2_control_flag = 4
			IF t2_speech_goals =  0  
				t2_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB t2_death_checks
				IF t2_deathcheck_flag = 1
					GOTO mission_truth2_failed
				ENDIF
				
				//skipping cutscene
				IF t2_skip_cutscene_flag = 1
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					t2_speech_goals = 0

					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB t2_death_checks
					IF t2_deathcheck_flag = 1
						GOTO mission_truth2_failed
					ENDIF
				
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_ROCKETLAUNCHER 10
					CLEAR_AREA -1075.5 -1648.0 75.1 1.0 TRUE
					SET_CHAR_COORDINATES scplayer -1075.5 -1648.0 75.1
					SET_CHAR_HEADING scplayer 268.0
				ENDIF 
				
				SHUT_ALL_CHARS_UP FALSE
				
				FREEZE_ONSCREEN_TIMER FALSE
				FREEZE_CAR_POSITION t2_mothership FALSE
				SET_DRUNK_INPUT_DELAY player1 t2_drunkeness 
				SET_PLAYER_DRUNKENNESS player1 t2_beer_goggles
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER	
				RESTORE_CAMERA_JUMPCUT
				TASK_CAR_DRIVE_TO_COORD truth t2_mothership -939.5 -1718.8 76.7 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS //at gate
				REMOVE_BLIP t2_truths_farm_blip 
				ADD_BLIP_FOR_CAR t2_chopper t2_truths_farm_blip
				IF NOT IS_CAR_DEAD t2_chopper
					PRINT_NOW ( TRU2_15 ) 7000 1 // Take out the chopper!
				ENDIF

				//skipping cutscene
				IF t2_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB t2_death_checks
					IF t2_deathcheck_flag = 1
						GOTO mission_truth2_failed
					ENDIF
				ENDIF
				timerb = 0
				t2_control_flag = 5
			ENDIF
		ENDIF

		//Waiting for player to destroy chopper
		IF t2_control_flag = 5
			//////////////////DEBUG////////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
				IF NOT IS_CAR_DEAD t2_chopper
					EXPLODE_CAR t2_chopper
				ENDIF
				SET_CHAR_COORDINATES scplayer -946.8 -1733.5 76.8
			ENDIF   
			
			IF t2_speech_flag = 2 
				IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SYN3_23
					PRINT_HELP ( SYN3_24 ) // Use the camera movement controls to move the target position.
					t2_speech_flag = 3
				ENDIF
			ENDIF
			IF t2_speech_flag = 1 
				IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED SYN3_22  
					PRINT_HELP ( SYN3_23 ) 
					t2_speech_flag = 2
				ENDIF
			ENDIF
			IF t2_speech_flag = 0
				IF timerb > 7000
					PRINT_HELP SYN3_22  
					t2_speech_flag = 1
				ENDIF
			ENDIF

			IF IS_CAR_DEAD t2_chopper 
				CLEAR_ONSCREEN_TIMER t2_cop_timer
				CLEAR_PRINTS
			
				SET_RADIO_CHANNEL RS_CLASSIC_ROCK
				PRINT ( TRU2_17 ) 11000 1 // Get inside the mothership.
				 
				SET_CAR_STATUS t2_mothership STATUS_PHYSICS  
				SET_CAR_CRUISE_SPEED t2_mothership 0.0 
				SET_CAR_TEMP_ACTION t2_mothership TEMPACT_HANDBRAKESTRAIGHT 1000  
				IF IS_CHAR_IN_ANY_CAR truth 
					WARP_CHAR_FROM_CAR_TO_COORD truth -1516.4 -1596.9 41.0
				ENDIF
				WARP_CHAR_INTO_CAR_AS_PASSENGER truth t2_mothership -1 
				LOCK_CAR_DOORS t2_mothership CARLOCK_UNLOCKED
				REMOVE_BLIP t2_truths_farm_blip
				ADD_BLIP_FOR_CAR t2_mothership t2_truths_farm_blip
				SET_BLIP_AS_FRIENDLY t2_truths_farm_blip TRUE
				
				t2_control_flag = 0
				t2_goals = 2
			ELSE
				GOSUB t2_rocket_ammo 
			ENDIF	 	 
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// WAITING FOR PLAYER TO GET INSIDE COPCAR /////////////////////////////////////
	IF t2_goals = 2 
		////WAITING for player to get into mothership
		IF t2_control_flag = 0 
			IF IS_CHAR_IN_CAR scplayer t2_mothership
				SET_CAR_STATUS t2_mothership STATUS_PLAYER
				CLEAR_THIS_PRINT TRU2_17 				
				PRINT_NOW ( TRU2_12 ) 7000 1 // Drive the Mothership to San Fran.
				
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH truth TRUE

				REMOVE_BLIP t2_truths_farm_blip 
				ADD_BLIP_FOR_COORD -2031.3 178.5 27.9 t2_truths_farm_blip 
				SET_BLIP_AS_FRIENDLY t2_truths_farm_blip FALSE
				//ALTER_WANTED_LEVEL_NO_DROP player1 2
				timerb = 0 
				t2_car_check_flag = 1
				t2_control_flag = 0
				t2_goals = 3
			ENDIF
		ENDIF

		//failing mission if player gets too far away
	   	IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer t2_mothership 200.0 200.0 FALSE
			t2_control_flag = 0
			t2_goals = 4
		ENDIF
	ENDIF
		
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// PLAYER DRIVING MOTHERSHIP //////////////////////////////////////////////

	IF t2_goals = 3 
		//cancelling phone call
		IF t2_speech_goals = 3
			IF t2_speech_control_flag = 6
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE  
					CLEAR_PRINTS		
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					t2_speech_goals = 0
				ENDIF
			ENDIF
		ENDIF

		IF t2_control_flag = 0
			IF timerb > 7000
				IF t2_speech_goals = 0 
					t2_speech_goals = 3
					t2_speech_control_flag = 0
					GOSUB t2_dialogue_setup				
					
					timerb = 0
					t2_control_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF t2_control_flag = 1
			//playing random speech
			IF t2_speech_goals = 0
				IF timerb > 7000
					GENERATE_RANDOM_INT_IN_RANGE 4 8 t2_speech_goals
					IF t2_speech_goals = 4
						IF t2_played_random_speech[0] = 1
							t2_speech_goals = 0
						ENDIF
						t2_played_random_speech[0] = 1	
					ENDIF	 	
					
					IF t2_speech_goals = 5
						IF t2_played_random_speech[1] = 1
							t2_speech_goals = 0
						ENDIF
						t2_played_random_speech[1] = 1
					ENDIF	 	
				
					IF t2_speech_goals = 6
						IF t2_played_random_speech[2] = 1
							t2_speech_goals = 0
						ENDIF
						t2_played_random_speech[2] = 1
					ENDIF	 	
				
					IF t2_speech_goals = 7
						IF t2_played_random_speech[3] = 1
							t2_speech_goals = 0
						ENDIF
						t2_played_random_speech[3] = 1
					ENDIF	 	
				
					IF t2_played_random_speech[4] = 0
						GET_CITY_PLAYER_IS_IN player1 t2_city   
						IF t2_city = LEVEL_SANFRANCISCO 
							t2_played_random_speech[4] = 1
							t2_speech_goals = 10
						ENDIF
					ENDIF
					t2_speech_control_flag = 0
					GOSUB t2_dialogue_setup
				ENDIF
			ENDIF	 	
		ENDIF
		
		IF t2_control_flag < 2
			//waiting for player to reach the hub
			IF IS_CHAR_IN_CAR scplayer t2_mothership 
				//////////////////DEBUG////////////////////
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
					SET_CAR_COORDINATES t2_mothership -2009.3 145.2 26.7
					SET_CAR_HEADING t2_mothership 359.8
				ENDIF
				
				IF LOCATE_CHAR_IN_CAR_3D scplayer -2031.3 178.5 27.9 4.0 4.0 4.0 TRUE
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					t2_speech_goals = 0
				
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH truth FALSE
				
					SET_PLAYER_CONTROL player1 OFF
					MARK_MODEL_AS_NO_LONGER_NEEDED CSHER

					/*
					OPEN_SEQUENCE_TASK t2_seq	
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2028.9 172.3 27.8 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK t2_seq
					PERFORM_SEQUENCE_TASK scplayer t2_seq
					CLEAR_SEQUENCE_TASK t2_seq
					
					OPEN_SEQUENCE_TASK t2_seq	
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2030.9 172.3 27.8 PEDMOVE_WALK -1 
					CLOSE_SEQUENCE_TASK t2_seq
					PERFORM_SEQUENCE_TASK truth t2_seq
					CLEAR_SEQUENCE_TASK t2_seq
					*/
					t2_control_flag = 0
					t2_goals = 5
				ENDIF
			ENDIF
		ENDIF

		GOSUB t2_blippage
	ENDIF

													
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// Cutscene showing truth getting arrested ////////////////////////////////
	IF t2_goals = 4 
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF t2_control_flag = 0	
				CLEAR_PRINTS
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB t2_death_checks
				IF t2_deathcheck_flag = 1
					GOTO mission_truth2_failed
				ENDIF

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				t2_speech_goals = 0

				SET_PLAYER_DRUNKENNESS player1 0
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER truth TRUE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS truth 0.0 -10.0 5.0 t2_x t2_y t2_z   
				
				REQUEST_MODEL NITESTICK 
						  
				LOAD_SCENE t2_x t2_y t2_z 
				CLEAR_AREA t2_x t2_y t2_z 30.0 TRUE

				SET_FIXED_CAMERA_POSITION t2_x t2_y t2_z 0.0 0.0 0.0
				POINT_CAMERA_AT_CHAR truth FIXED JUMP_CUT

				CLEAR_CHAR_TASKS truth
				
				IF IS_CHAR_IN_CAR truth t2_mothership 
					TASK_CAR_TEMP_ACTION truth t2_mothership TEMPACT_HANDBRAKESTRAIGHT 2000000
				ENDIF
			
				GET_NTH_CLOSEST_CAR_NODE t2_x t2_y t2_z 1 t2_x2 t2_y2 t2_z2   	
				CREATE_CHAR PEDTYPE_MISSION1 CSHER t2_x2 t2_y2 t2_z2 t2_cop
				IF IS_CHAR_IN_ANY_CAR truth 
					GIVE_WEAPON_TO_CHAR t2_cop WEAPONTYPE_NIGHTSTICK 1
				ENDIF
				TASK_CHAR_ARREST_CHAR t2_cop truth  
				PRINT_NOW ( TRU2_10 ) 7000 1 //The Truth has been arrested!

				DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB t2_death_checks
				IF t2_deathcheck_flag = 1
					GOTO mission_truth2_failed
				ENDIF

				timera = 0
				t2_control_flag = 1
			ENDIF
		ENDIF
	
		IF t2_control_flag = 1
			IF timera > 7000
				DO_FADE 500 FADE_OUT		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
				
				IF DOES_SEARCHLIGHT_EXIST t2_helilight
					DELETE_SEARCHLIGHT t2_helilight
				ENDIF
				DELETE_CHAR truth
				UNLOAD_SPECIAL_CHARACTER 1
				DELETE_CAR t2_mothership
				DELETE_CHAR t2_cop 
			
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				//CLEAR_PRINTS
				
				PRINT_BIG M_FAIL 5000 1
				PRINT_NOW ( TRU2_10 ) 5500 1 //The Truth has been arrested!
				
				DO_FADE 500 FADE_IN		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 

				GOTO mission_truth2_failed
			ENDIF 
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// Completion cutscene ////////////////////////////////////////////////////


	IF t2_goals = 5 
		IF t2_control_flag = 0
			IF timera > 1500
				t2_control_flag = 1	
			ENDIF
		ENDIF 
		
		IF t2_control_flag = 1						
			DO_FADE 500 FADE_OUT		
			WHILE GET_FADING_STATUS
			   WAIT 0
			ENDWHILE 
			GOSUB t2_death_checks
			IF t2_deathcheck_flag = 1
				GOTO mission_truth2_failed
			ENDIF
			
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_PLAYER_DRUNKENNESS player1 0

			SET_FIXED_CAMERA_POSITION -2025.0 172.3 28.0 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2032.9 163.0 30.88 JUMP_CUT
					
			SHUT_ALL_CHARS_UP TRUE

			CLEAR_AREA -2028.9 172.3 27.8 30.0 TRUE
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 	
			SET_CHAR_COORDINATES scplayer -2028.9 172.3 27.8 					
			SET_CHAR_HEADING scplayer 167.6
		
			OPEN_SEQUENCE_TASK t2_seq	
				TASK_GO_STRAIGHT_TO_COORD -1 -2029.9 167.7 27.8 PEDMOVE_WALK -1
				TASK_PAUSE -1 2000 
				//TASK_PLAY_ANIM -1 kick_floor PED 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM -1 idle_tired PED 4.0 FALSE FALSE FALSE FALSE 4000
				TASK_GO_STRAIGHT_TO_COORD -1 -2033.0 161.4 28.0 PEDMOVE_WALK -1
			CLOSE_SEQUENCE_TASK t2_seq
			PERFORM_SEQUENCE_TASK scplayer t2_seq
			CLEAR_SEQUENCE_TASK t2_seq

			SET_PLAYER_DRUNKENNESS player1 0
			CLEAR_AREA -2030.9 172.3 27.8 30.0 TRUE
			CLEAR_CHAR_TASKS_IMMEDIATELY truth 	
			SET_CHAR_COORDINATES truth -2030.9 172.3 27.8 					
			SET_CHAR_HEADING truth 167.6

			OPEN_SEQUENCE_TASK t2_seq	
				TASK_GO_STRAIGHT_TO_COORD -1 -2030.9 167.7 27.8 PEDMOVE_WALK -1
				TASK_PAUSE -1 1000 
				TASK_GO_STRAIGHT_TO_COORD -1 -2033.0 161.4 28.0 PEDMOVE_WALK -1
				TASK_ACHIEVE_HEADING -1 115.2
			CLOSE_SEQUENCE_TASK t2_seq
			PERFORM_SEQUENCE_TASK truth t2_seq
			CLEAR_SEQUENCE_TASK t2_seq

			DO_FADE 500 FADE_IN		
			WHILE GET_FADING_STATUS
			   WAIT 0
			ENDWHILE 
			GOSUB t2_death_checks
			IF t2_deathcheck_flag = 1
				GOTO mission_truth2_failed
			ENDIF
			
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			t2_speech_goals = 11
			t2_speech_control_flag = 0
			GOSUB t2_dialogue_setup
			t2_control_flag = 2
		ENDIF

	 	IF t2_control_flag = 2 
	 		IF t2_speech_goals = 0 
				t2_control_flag = 3
				timera = 0 
			ENDIF
		ENDIF
		
		IF t2_control_flag = 3
			IF timera > 1000 
				DO_FADE 500 FADE_OUT		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
				
				IF DOES_SEARCHLIGHT_EXIST t2_helilight
					DELETE_SEARCHLIGHT t2_helilight
				ENDIF
				DELETE_CHAR truth
				UNLOAD_SPECIAL_CHARACTER 1
				DELETE_CAR t2_mothership
				DELETE_CHAR t2_cop 
			
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				//SET_CHAR_HEADING scplayer 276.1 
				
				SHUT_ALL_CHARS_UP FALSE

				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				
				DO_FADE 500 FADE_IN		
				WHILE GET_FADING_STATUS
				   WAIT 0
				ENDWHILE 
					
				GOTO mission_truth2_passed
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// MISC ///////////////////////////////////////////////////////////////////
	//checking time has run out 
	IF t2_goals < 2
		IF t2_cop_timer = 0
			t2_goals = 4 
			t2_control_flag = 0
			SET_CHAR_HEALTH truth 10
			//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS truth 0.0 -12.0 0.0 t2_x t2_y t2_z
			//CREATE_CHAR PEDTYPE_MISSION1 CSHER t2_x t2_y t2_z t2_cop
		ENDIF
	ENDIF

	///ingame dialogue///
	GOSUB t2_overall_dialogue


GOTO mission_truth2_loop


	
// Mission truth2 failed

mission_truth2_failed:
REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_FLAMETHROWER  
REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_ROCKETLAUNCHER  
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission truth2 passed
mission_truth2_passed:
//flag_truth2_mission1_passed = 1
SET_INT_STAT PASSED_TRUTH2 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 15 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 15//amount of respect
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED TRUTH_2
PLAYER_MADE_PROGRESS 1
flag_truth_mission_counter ++
REMOVE_BLIP truth_contact_blip


TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_SF
START_NEW_SCRIPT cell_phone_sanfran

REMOVE_BLIP save_house_blip[15] 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[15] save_pickupY[15] save_pickupZ[15] RADAR_SPRITE_SAVEHOUSE save_house_blip[15]
CHANGE_BLIP_DISPLAY save_house_blip[15] BLIP_ONLY

START_NEW_SCRIPT garage_mission_loop
REMOVE_BLIP garage_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ RADAR_SPRITE_CJ garage_contact_blip

REMOVE_PICKUP grove_save_pickup[15]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[15] save_pickupY[15] save_pickupZ[15] grove_save_pickup[15] //SAN FRAN GARAGE
number_of_save_icons = 16

ACTIVATE_GARAGE mdsSFSe
REMOVE_BLIP mod_garage_blips[1]
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1941.0, 251.7, 33.4 RADAR_SPRITE_MOD_GARAGE mod_garage_blips[1]

RETURN
		

// mission cleanup
mission_cleanup_truth2:
//SET_CAMERA_BEHIND_PLAYER 
IF IS_PLAYER_PLAYING player1
	SET_DRUNK_INPUT_DELAY player1 0
	SET_PLAYER_DRUNKENNESS player1 0
ENDIF
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
REMOVE_IPL_DISCREETLY truthsfarm
REMOVE_BLIP t2_truths_farm_blip
IF NOT IS_CAR_DEAD t2_chopper 
	HELI_GOTO_COORDS t2_chopper 0.0 0.0 200.0 0.0 200.0 
ENDIF 
IF NOT IS_CHAR_DEAD truth
	DETACH_CHAR_FROM_CAR truth
ENDIF 
IF NOT IS_CAR_DEAD t2_sadler 
	SET_CAR_PROOFS t2_sadler FALSE FALSE FALSE FALSE FALSE
ENDIF
IF NOT IS_CAR_DEAD t2_mothership 
	LOCK_CAR_DOORS t2_mothership CARLOCK_UNLOCKED
ENDIF
REMOVE_PICKUP t2_flame_pickup
REMOVE_PICKUP t2_rocket_pickup
MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER 
MARK_MODEL_AS_NO_LONGER_NEEDED SADLER
MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
MARK_MODEL_AS_NO_LONGER_NEEDED CSHER
MARK_MODEL_AS_NO_LONGER_NEEDED FLAME
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED NITESTICK 
REMOVE_CHAR_ELEGANTLY truth
UNLOAD_SPECIAL_CHARACTER 1
//CLEAR_ONSCREEN_COUNTER t2_total_weed_burnt
CLEAR_ONSCREEN_TIMER t2_cop_timer
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
REMOVE_BLIP t2_weed_blips[0]
REMOVE_BLIP t2_weed_blips[1]
REMOVE_BLIP t2_weed_blips[2]
REMOVE_BLIP t2_weed_blips[3]
REMOVE_BLIP t2_weed_blips[4]
REMOVE_BLIP t2_weed_blips[5]
REMOVE_BLIP t2_weed_blips[6]
REMOVE_BLIP t2_weed_blips[7]
REMOVE_BLIP t2_weed_blips[8]
REMOVE_BLIP t2_weed_blips[9]
REMOVE_BLIP t2_weed_blips[10]
REMOVE_BLIP t2_weed_blips[11]
REMOVE_BLIP t2_weed_blips[12]
REMOVE_BLIP t2_weed_blips[13]
REMOVE_BLIP t2_weed_blips[14]
REMOVE_BLIP t2_weed_blips[15]
REMOVE_BLIP t2_weed_blips[16]
REMOVE_BLIP t2_weed_blips[17]
REMOVE_BLIP t2_weed_blips[18]
REMOVE_BLIP t2_weed_blips[19]
REMOVE_BLIP t2_weed_blips[20]
REMOVE_BLIP t2_weed_blips[21]
REMOVE_BLIP t2_weed_blips[22]
REMOVE_BLIP t2_weed_blips[23]
REMOVE_BLIP t2_weed_blips[24]
REMOVE_BLIP t2_weed_blips[25]
REMOVE_BLIP t2_weed_blips[26]
REMOVE_BLIP t2_weed_blips[27]
REMOVE_BLIP t2_weed_blips[28]
REMOVE_BLIP t2_weed_blips[29]
REMOVE_BLIP t2_weed_blips[30]
REMOVE_BLIP t2_weed_blips[31]
REMOVE_BLIP t2_weed_blips[32]
REMOVE_BLIP t2_weed_blips[33]
REMOVE_BLIP t2_weed_blips[34]
REMOVE_BLIP t2_weed_blips[35]
REMOVE_BLIP t2_weed_blips[36]
REMOVE_BLIP t2_weed_blips[37]
REMOVE_BLIP t2_weed_blips[38]
REMOVE_BLIP t2_weed_blips[39]
REMOVE_BLIP t2_weed_blips[40]
REMOVE_BLIP t2_weed_blips[41]
REMOVE_BLIP t2_weed_blips[42]
REMOVE_BLIP t2_weed_blips[43]
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
t2_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD truth
	CLEAR_PRINTS
	PRINT_NOW ( TRU2_08 ) 7000 1 //The Truth has been killed!	
	t2_deathcheck_flag = 1	
ENDIF
IF IS_CAR_DEAD t2_mothership
	CLEAR_PRINTS
	PRINT_NOW ( TRU2_09 ) 7000 1 //The Mothership has been destroyed!	
	t2_deathcheck_flag = 1	
ENDIF
IF IS_CAR_DEAD t2_chopper 
	IF DOES_SEARCHLIGHT_EXIST t2_helilight
		DELETE_SEARCHLIGHT t2_helilight
	ENDIF
ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_flamethrower:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_flamethrower_control_flag = 0
	GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_FLAMETHROWER t2_flame_ammo
	IF t2_flame_ammo = 0 
		REMOVE_BLIP t2_weed_blips[0]
		REMOVE_BLIP t2_weed_blips[1]
		REMOVE_BLIP t2_weed_blips[2]
		REMOVE_BLIP t2_weed_blips[3]
		REMOVE_BLIP t2_weed_blips[4]
		REMOVE_BLIP t2_weed_blips[5]
		REMOVE_BLIP t2_weed_blips[6]
		REMOVE_BLIP t2_weed_blips[7]
		REMOVE_BLIP t2_weed_blips[8]
		REMOVE_BLIP t2_weed_blips[9]
		REMOVE_BLIP t2_weed_blips[10]
		REMOVE_BLIP t2_weed_blips[11]
		REMOVE_BLIP t2_weed_blips[12]
		REMOVE_BLIP t2_weed_blips[13]
		REMOVE_BLIP t2_weed_blips[14]
		REMOVE_BLIP t2_weed_blips[15]
		REMOVE_BLIP t2_weed_blips[16]
		REMOVE_BLIP t2_weed_blips[17]
		REMOVE_BLIP t2_weed_blips[18]
		REMOVE_BLIP t2_weed_blips[19]
		REMOVE_BLIP t2_weed_blips[20]
		REMOVE_BLIP t2_weed_blips[21]
		REMOVE_BLIP t2_truths_farm_blip
		CREATE_PICKUP_WITH_AMMO flame PICKUP_ONCE 3000 -1100.3 -1640.4 76.4 t2_flame_pickup
		ADD_BLIP_FOR_PICKUP t2_flame_pickup t2_truths_farm_blip 
		SET_BLIP_AS_FRIENDLY t2_truths_farm_blip FALSE
		PRINT_NOW ( TRU2_13 ) 7000 1 //There is more ammo near the shed.
		t2_flamethrower_control_flag = 1
	ENDIF 	
ENDIF

IF t2_flamethrower_control_flag = 1
	IF HAS_PICKUP_BEEN_COLLECTED t2_flame_pickup 
		REMOVE_BLIP t2_truths_farm_blip 
		IF t2_weed_burnt[0] = 0
			ADD_BLIP_FOR_COORD -1061.9 -1630.7 75.3 t2_weed_blips[0]   //1st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[0] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[0] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[1] = 0
			ADD_BLIP_FOR_COORD -1062.3 -1621.4 75.3 t2_weed_blips[1]   //1st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[1] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[1] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[2] = 0
			ADD_BLIP_FOR_COORD -1052.8 -1630.8 75.3 t2_weed_blips[2]   //2st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[2] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[2] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[3] = 0
			ADD_BLIP_FOR_COORD -1052.8 -1621.4 75.3 t2_weed_blips[3]   //2st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[3] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[3] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[4] = 0
			ADD_BLIP_FOR_COORD -1042.8 -1630.8 75.3 t2_weed_blips[4]   //3st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[4] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[4] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[5] = 0
			ADD_BLIP_FOR_COORD -1042.8 -1621.4 75.3 t2_weed_blips[5]   //3st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[5] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[5] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[6] = 0
			ADD_BLIP_FOR_COORD -1032.8 -1630.8 75.3 t2_weed_blips[6]   //4st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[6] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[6] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[7] = 0
			ADD_BLIP_FOR_COORD -1032.8 -1621.4 75.3 t2_weed_blips[7]   //4st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[7] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[7] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[8] = 0
			ADD_BLIP_FOR_COORD -1022.8 -1630.8 75.3 t2_weed_blips[8]   //5st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[8] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[8] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[9] = 0
			ADD_BLIP_FOR_COORD -1022.8 -1621.4 75.3 t2_weed_blips[9]   //5st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[9] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[9] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[10] = 0
			ADD_BLIP_FOR_COORD -1012.1 -1666.8 75.3 t2_weed_blips[10]   //6st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[10] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[10] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
	  	IF t2_weed_burnt[11] = 0
			ADD_BLIP_FOR_COORD -1012.8 -1657.4 75.3 t2_weed_blips[11]   //6st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[11] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[11] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[12] = 0
			ADD_BLIP_FOR_COORD -1012.8 -1648.4 75.3 t2_weed_blips[12]   //6st row, 3st part 
			CHANGE_BLIP_SCALE t2_weed_blips[12] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[12] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[13] = 0
			ADD_BLIP_FOR_COORD -1000.1 -1666.8 75.3 t2_weed_blips[13]   //7st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[13] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[13] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[14] = 0
			ADD_BLIP_FOR_COORD -1000.8 -1657.4 75.3 t2_weed_blips[14]   //7st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[14] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[14] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[15] = 0
			ADD_BLIP_FOR_COORD -1000.8 -1648.4 75.3 t2_weed_blips[15]   //7st row, 3st part 
			CHANGE_BLIP_SCALE t2_weed_blips[15] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[15] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[16] = 0
			ADD_BLIP_FOR_COORD -992.1 -1702.8 75.3 t2_weed_blips[16]   //8st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[16] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[16] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[17] = 0
			ADD_BLIP_FOR_COORD -992.8 -1693.4 75.3 t2_weed_blips[17]   //8st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[17] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[17] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[18] = 0
			ADD_BLIP_FOR_COORD -992.8 -1684.4 75.3 t2_weed_blips[18]   //8st row, 3st part 
			CHANGE_BLIP_SCALE t2_weed_blips[18] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[18] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[19] = 0
			ADD_BLIP_FOR_COORD -982.1 -1702.8 75.3 t2_weed_blips[19]   //9st row, 1st part 
			CHANGE_BLIP_SCALE t2_weed_blips[19] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[19] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[20] = 0
			ADD_BLIP_FOR_COORD -982.8 -1693.4 75.3 t2_weed_blips[20]   //9st row, 2st part 
			CHANGE_BLIP_SCALE t2_weed_blips[20] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[20] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		IF t2_weed_burnt[21] = 0
		   	ADD_BLIP_FOR_COORD -982.8 -1684.4 75.3 t2_weed_blips[21]   //9st row, 3st part 
			CHANGE_BLIP_SCALE t2_weed_blips[21] 1 
			SET_COORD_BLIP_APPEARANCE t2_weed_blips[21] COORD_BLIP_APPEARANCE_ENEMY  
		ENDIF
		PRINT_NOW ( TRU2_01 ) 11000 1 //Go and help The Truth destroy the weed.
		t2_flamethrower_control_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_rocket_ammo:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_rocketlauncher_control_flag = 0
	GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_ROCKETLAUNCHER t2_flame_ammo
	IF t2_flame_ammo = 0 
		REMOVE_BLIP t2_truths_farm_blip
		CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 10 -1100.3 -1640.4 76.4 t2_rocket_pickup
		ADD_BLIP_FOR_PICKUP t2_rocket_pickup t2_truths_farm_blip 
		SET_BLIP_AS_FRIENDLY t2_truths_farm_blip FALSE
		PRINT_NOW ( TRU2_13 ) 7000 1 //There is more ammo near the shed.
		t2_rocketlauncher_control_flag = 1
	ENDIF 	
ENDIF

IF t2_rocketlauncher_control_flag = 1
	IF HAS_PICKUP_BEEN_COLLECTED t2_rocket_pickup 
		REMOVE_BLIP t2_truths_farm_blip 
		IF NOT IS_CAR_DEAD t2_chopper 
			ADD_BLIP_FOR_CAR t2_chopper t2_truths_farm_blip
			PRINT_NOW ( TRU2_15 ) 11000 1 // Take out the chopper!
		ENDIF
		t2_rocketlauncher_control_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_blippage:////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	//sorting the blippage
	IF t2_car_check_flag = 0
		IF IS_CHAR_IN_CAR scplayer t2_mothership
			REMOVE_BLIP t2_truths_farm_blip
			ADD_BLIP_FOR_COORD -2031.3 178.5 27.9 t2_truths_farm_blip
			SET_BLIP_AS_FRIENDLY t2_truths_farm_blip FALSE
			t2_car_check_flag = 1
		ENDIF
	ENDIF
	IF t2_car_check_flag = 1 
		IF NOT IS_CHAR_IN_CAR scplayer t2_mothership
			REMOVE_BLIP t2_truths_farm_blip
			ADD_BLIP_FOR_CAR t2_mothership t2_truths_farm_blip
			SET_BLIP_AS_FRIENDLY t2_truths_farm_blip TRUE
			t2_car_check_flag = 0
		ENDIF
	ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_drunken_filter://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_goals = 0
	//increasing the motion blur
	IF t2_total_weed_burnt > 0 
		IF t2_total_weed_burnt < t2_weed_check
			IF t2_beer_goggles < 200 
				t2_beer_goggles ++
				t2_beer_goggles ++
			ENDIF
			t2_weed_check --
		ENDIF
	ENDIF

	//increasing the drunkenness factor
	IF t2_faded_flag = 0
		IF t2_total_weed_burnt < 40   
			t2_drunkeness ++
			t2_faded_flag = 1
		ENDIF
	ENDIF

	IF t2_faded_flag = 1
		IF t2_total_weed_burnt < 30   
			t2_drunkeness ++
			t2_faded_flag = 2
		ENDIF
	ENDIF

	IF t2_faded_flag = 2
		IF t2_total_weed_burnt < 20   
			t2_drunkeness ++
			t2_faded_flag = 3
		ENDIF
	ENDIF

	IF t2_faded_flag = 3
		IF t2_total_weed_burnt < 10   
			t2_drunkeness ++
			t2_faded_flag = 4
		ENDIF
	ENDIF

	SET_DRUNK_INPUT_DELAY player1 t2_drunkeness 
	SET_PLAYER_DRUNKENNESS player1 t2_beer_goggles
ENDIF

IF t2_goals = 2
OR t2_goals = 3
	IF timera > 660
		IF t2_beer_goggles > 1 
			t2_beer_goggles -= 1   
		ENDIF	
		SET_DRUNK_INPUT_DELAY player1 t2_drunkeness 
		SET_PLAYER_DRUNKENNESS player1 t2_beer_goggles
		timera = 0
	ENDIF

ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_dialogue_setup://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_speech_goals = 1
	$t2_print_label[0] = &TRU2_AA // Assholes! Republican assholes!		
	$t2_print_label[1] = &TRU2_AB // I don't feel too good..
	$t2_print_label[2] = &TRU2_AC // It's a crying shame, ain't it.
	$t2_print_label[3] = &TRU2_AD // No I mean I think I'm gonna black out!
	$t2_print_label[4] = &TRU2_AE // Fight the ocean and you will drown, brother!
	$t2_print_label[5] = &TRU2_BA // Carl, we'll take the mothership and get our shit out of here!
	$t2_print_label[6] = &TRU2_BB // Go get her fired up!
	$t2_print_label[7] = &TRU2_BC // I'll finish burning and follow you!
	
	t2_audio_label[0] = SOUND_TRU2_AA 
	t2_audio_label[1] = SOUND_TRU2_AB 
	t2_audio_label[2] = SOUND_TRU2_AC 
	t2_audio_label[3] = SOUND_TRU2_AD 
	t2_audio_label[4] = SOUND_TRU2_AE 
	t2_audio_label[5] = SOUND_TRU2_BA 
	t2_audio_label[6] = SOUND_TRU2_BB 
	t2_audio_label[7] = SOUND_TRU2_BC 
	t2_last_label = 8
ENDIF

IF t2_speech_goals = 2
	$t2_print_label[0] =  &TRU2_CA // Hey, I'm kinda busy, lemme get back at you. 
	$t2_print_label[1] =  &TRU2_CB // Carl, it's me! Get those cops off my damned tail!
	
	t2_audio_label[0] = SOUND_TRU2_CA 
	t2_audio_label[1] = SOUND_TRU2_CB 
	t2_last_label = 2 
ENDIF

IF t2_speech_goals = 3
	$t2_print_label[0] = &TRU2_DA // What you pulled over for?   	
	$t2_print_label[1] = &TRU2_DB // You better drive. I haven't driven in 15 years. 
	$t2_print_label[2] = &TRU2_DC // You were doing alright!   
	$t2_print_label[3] = &TRU2_DD // Yeah, then the fear hit me. 
	$t2_print_label[4] = &TRU2_DE // Now I'm rolling a number to calm the waves! 
	$t2_print_label[5] = &TRU2_OA // Shit, I better call Cesar!
	$t2_print_label[6] = &TRU2_OB // (Dialing number)
	$t2_print_label[7] = &TRU2_OC // Yo, Cesar, no time to talk, dude!
	$t2_print_label[8] = &TRU2_OD // I'm on my way to San Fierro, ok,
	$t2_print_label[9] = &TRU2_OE // I'll meet you and Kendl at that garage I won in that race!
	$t2_print_label[10] = &TRU2_OF // Ok, gotta go, bye!

	t2_audio_label[0] = SOUND_TRU2_DA 
	t2_audio_label[1] = SOUND_TRU2_DB 
	t2_audio_label[2] = SOUND_TRU2_DC 
	t2_audio_label[3] = SOUND_TRU2_DD 
	t2_audio_label[4] = SOUND_TRU2_DE 
	t2_audio_label[5] = SOUND_TRU2_OA 
	t2_audio_label[6] = SOUND_MOBILE_DIALING 
	t2_audio_label[7] = SOUND_TRU2_OC 
	t2_audio_label[8] = SOUND_TRU2_OD 
	t2_audio_label[9] = SOUND_TRU2_OE 
	t2_audio_label[10] = SOUND_TRU2_OF 
	t2_last_label = 11 
ENDIF

IF t2_speech_goals = 4
	$t2_print_label[0] = &TRU2_EA // Jesus, we're screwed, when did you get this?
	$t2_print_label[1] = &TRU2_EB // 1967 
	$t2_print_label[2] = &TRU2_EC // How do you get around if you don't drive?
	$t2_print_label[3] = &TRU2_ED // I have an astral goat called 'Herbie'.
	$t2_print_label[4] = &TRU2_EF // Faster than most, but she's getting old..
	$t2_print_label[5] = &TRU2_EG // Yeah whatever man, you're talking shit.
	
	t2_audio_label[0] = SOUND_TRU2_EA 
	t2_audio_label[1] = SOUND_TRU2_EB 
	t2_audio_label[2] = SOUND_TRU2_EC 
	t2_audio_label[3] = SOUND_TRU2_ED 
	t2_audio_label[4] = SOUND_TRU2_EF 												  
	t2_audio_label[5] = SOUND_TRU2_EG 
	t2_last_label = 6 
ENDIF

IF t2_speech_goals = 5
	$t2_print_label[0] = &TRU2_FA // Hey, you want a hit on this? A little Temple Charis  
	$t2_print_label[1] = &TRU2_FB // in a cocktail with some Nepalese munga munga.
	$t2_print_label[2] = &TRU2_FC // Put that thing out, I can't see. 
	$t2_print_label[3] = &TRU2_FD // Mellow out, brother. Its good shit. 
	$t2_print_label[4] = &TRU2_FE // Put it out, motherfucker. I'm warning you. 
	$t2_print_label[5] = &TRU2_FF // Wooah, chill the fuck out. 
	$t2_print_label[6] = &TRU2_FG // Firstly, you are a real buzz kill, amigo.  
	$t2_print_label[7] = &TRU2_FH // Secondly, I never made love to my mother. She wouldn't.
	$t2_print_label[8] = &TRU2_FJ // And thirdly, we're in this together, so be cool. 
	$t2_print_label[9] = &TRU2_FK // Sorry. I just don't like driving when I'm faded.

	t2_audio_label[0] = SOUND_TRU2_FA 
	t2_audio_label[1] = SOUND_TRU2_FB 
	t2_audio_label[2] = SOUND_TRU2_FC 
	t2_audio_label[3] = SOUND_TRU2_FD 
	t2_audio_label[4] = SOUND_TRU2_FE 
	t2_audio_label[5] = SOUND_TRU2_FF 
	t2_audio_label[6] = SOUND_TRU2_FG 
	t2_audio_label[7] = SOUND_TRU2_FH 
	t2_audio_label[8] = SOUND_TRU2_FJ 
	t2_audio_label[9] = SOUND_TRU2_FK 
	t2_last_label = 10 
ENDIF

IF t2_speech_goals = 6
	$t2_print_label[0] = &TRU2_GA // Hey, does this thing go any faster?
	$t2_print_label[1] = &TRU2_GB // Man, we got 3 tonnes of grass on board,
	$t2_print_label[2] = &TRU2_GC // the engine block is held together with a macrame hammock and it's running on 15-year-old cooking oil. 
	$t2_print_label[3] = &TRU2_GE // Shit. Can you shoot?
	$t2_print_label[4] = &TRU2_GF // Shoot? I'm a hippy!
	$t2_print_label[5] = &TRU2_GG // The only thing I've shot is acid. 
	$t2_print_label[6] = &TRU2_GH // I heard about a guy snorting it once. 
	$t2_print_label[7] = &TRU2_GJ // Thought his nose was a kangaroo and the moon was a dog!
	
	t2_audio_label[0] = SOUND_TRU2_GA 
	t2_audio_label[1] = SOUND_TRU2_GB 
	t2_audio_label[2] = SOUND_TRU2_GC 
	t2_audio_label[3] = SOUND_TRU2_GE 
	t2_audio_label[4] = SOUND_TRU2_GF 
	t2_audio_label[5] = SOUND_TRU2_GG 
	t2_audio_label[6] = SOUND_TRU2_GH 
	t2_audio_label[7] = SOUND_TRU2_GJ 
	t2_last_label = 8 
ENDIF
				
IF t2_speech_goals = 7
	$t2_print_label[0] = &TRU2_HA // What's with all the aluminium foil, man?
	$t2_print_label[1] = &TRU2_HB // Protection from mind control, dude.
	$t2_print_label[2] = &TRU2_HC // Mind control?
	$t2_print_label[3] = &TRU2_HD // Induction of images, sound or emotion using microwave radiation.
	$t2_print_label[4] = &TRU2_HE // D'you know how many government satellites 
	$t2_print_label[5] = &TRU2_HF // are watching any citizen at any one moment?
	$t2_print_label[6] = &TRU2_HG // No.
	$t2_print_label[7] = &TRU2_HH // Twenty-three.
	$t2_print_label[8] = &TRU2_HJ // Do you know how many religious relics are kept at the Pentagon?
	$t2_print_label[9] = &TRU2_HK // No I don't.
	$t2_print_label[10] = &TRU2_HL // Twenty-three.
	$t2_print_label[11] = &TRU2_HM // You see a pattern emerging here? 
	$t2_print_label[12] = &TRU2_HN // Dude, I'm seeing patterns all over the place!
	$t2_print_label[13] = &TRU2_HO // Get that smoke outta my face!

	t2_audio_label[0] = SOUND_TRU2_HA 
	t2_audio_label[1] = SOUND_TRU2_HB 
	t2_audio_label[2] = SOUND_TRU2_HC 
	t2_audio_label[3] = SOUND_TRU2_HD 
	t2_audio_label[4] = SOUND_TRU2_HE 
	t2_audio_label[5] = SOUND_TRU2_HF 
	t2_audio_label[6] = SOUND_TRU2_HG 
	t2_audio_label[7] = SOUND_TRU2_HH 
	t2_audio_label[8] = SOUND_TRU2_HJ 
	t2_audio_label[9] = SOUND_TRU2_HK 
	t2_audio_label[10] = SOUND_TRU2_HL 
	t2_audio_label[11] = SOUND_TRU2_HM 
	t2_audio_label[12] = SOUND_TRU2_HN 
	t2_audio_label[13] = SOUND_TRU2_HO 
	t2_last_label = 14 
ENDIF

IF t2_speech_goals = 8
	$t2_print_label[0] = &TRU2_JA // We got a chopper on our tail, we'll never shake 'em now.
	$t2_print_label[1] = &TRU2_JB // Hold on, I got a little something back here I was saving for a rainy day..

	t2_audio_label[0] = SOUND_TRU2_JA 
	t2_audio_label[1] = SOUND_TRU2_JB 
	t2_last_label = 2 
ENDIF

IF t2_speech_goals = 9
	$t2_print_label[0] = &TRU2_KA // Holy motherfucker!
	$t2_print_label[1] = &TRU2_KB // Where'd you get this?
	$t2_print_label[2] = &TRU2_KC // Found it in a bail of Thai Sticks.
	$t2_print_label[3] = &TRU2_KD // Shame really, I was going to make it into a lamp.

	t2_audio_label[0] = SOUND_TRU2_KA 
	t2_audio_label[1] = SOUND_TRU2_KB 
	t2_audio_label[2] = SOUND_TRU2_KC 
	t2_audio_label[3] = SOUND_TRU2_KD 
	t2_last_label = 4 
ENDIF

IF t2_speech_goals = 10
	$t2_print_label[0] = &TRU2_LA // There she is, brother; San Fierro: the City of Psychadelic Wonders!
	$t2_print_label[1] = &TRU2_LB // I'm amazed I've never been before.
	$t2_print_label[2] = &TRU2_LC // There ain't a better place to escape the man, man.
	$t2_print_label[3] = &TRU2_MA // Ok, Mr. San Fierro, where's the spot at?
	$t2_print_label[4] = &TRU2_MB // It's in Doherty on the East side of Fierro,
	$t2_print_label[5] = &TRU2_MC // between Garcia and Easter Basin.

	t2_audio_label[0] = SOUND_TRU2_LA 
	t2_audio_label[1] = SOUND_TRU2_LB 
	t2_audio_label[2] = SOUND_TRU2_LC 
	t2_audio_label[3] = SOUND_TRU2_MA 
	t2_audio_label[4] = SOUND_TRU2_MB 
	t2_audio_label[5] = SOUND_TRU2_MC 
	t2_last_label = 6 
ENDIF

IF t2_speech_goals = 11
	$t2_print_label[0] = &TRU2_NA // This is the place. 	
	$t2_print_label[1] = &TRU2_NB // Jesus, dude, you've been fed a bummer! 

	t2_audio_label[0] = SOUND_TRU2_NA 
	t2_audio_label[1] = SOUND_TRU2_NB 
	t2_last_label = 2 
ENDIF

IF t2_speech_goals = 12
	$t2_print_label[0] = &TRUX_AA // Get in the car, quick!
	$t2_print_label[1] = &TRUX_AB // Get in the car, it's a Faraday cage!
	$t2_print_label[2] = &TRUX_AC // C'mon, Carl, get in!
	
	t2_audio_label[0] = SOUND_TRUX_AA 
	t2_audio_label[1] = SOUND_TRUX_AB 
	t2_audio_label[2] = SOUND_TRUX_AC 
 	t2_last_label = t2_random_last_label 
ENDIF

t2_slot_load = t2_speech_control_flag
t2_slot1 = 0
t2_slot2 = 0
t2_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_overall_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_speech_goals = 1 //While the truth and the player are burning the weed
OR t2_speech_goals = 2 //telling player that he needs to get the cops off his tail
OR t2_speech_goals = 8 //telling player a chopper is following
OR t2_speech_goals = 9 //telling player where truth got the rocketlauncher from
OR t2_speech_goals = 11 //final piece of text
	IF t2_speech_control_flag < t2_last_label
		GOSUB t2_loading_dialogue
		GOSUB t2_playing_dialogue
		IF NOT IS_CHAR_DEAD truth
			GOSUB t2_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag] 
			t2_slot1 = 0
			t2_slot2 = 0
		ENDIF
	ELSE
		t2_speech_goals = 0
	ENDIF
ENDIF	

IF t2_goals = 3
OR t2_goals = 4
	IF IS_CHAR_SITTING_IN_CAR scplayer t2_mothership
		IF t2_speech_goals = 3 //telling player why truth has stopped driving
			IF t2_speech_control_flag < t2_last_label
				GOSUB t2_loading_dialogue
				GOSUB t2_playing_dialogue
				IF NOT IS_CHAR_DEAD truth
					GOSUB t2_finishing_dialogue  
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag] 
					t2_slot1 = 0
					t2_slot2 = 0
				ENDIF
			ELSE
				timerb = 0
				t2_speech_goals = 0
			ENDIF
		ENDIF
	
		IF t2_speech_goals = 4 //random banter 1
		OR t2_speech_goals = 5 //random banter 2
		OR t2_speech_goals = 6 //random banter 3
		OR t2_speech_goals = 7 //random banter 4
		OR t2_speech_goals = 10	//telling player about san fran
			IF t2_speech_control_flag < t2_last_label
				GOSUB t2_loading_dialogue
				GOSUB t2_playing_dialogue
				IF NOT IS_CHAR_DEAD truth
					GOSUB t2_finishing_dialogue  
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag] 
					t2_slot1 = 0
					t2_slot2 = 0
				ENDIF
			ELSE
				IF t2_drunkeness > 0 
					t2_drunkeness -= 1
				ENDIF
				timerb = 0 
				t2_speech_goals = 0
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_IN_CAR scplayer t2_mothership
		IF t2_speech_goals < 12 
			IF t2_speech_control_flag < t2_last_label  
				t2_speech_control_flag ++
			ENDIF
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag]
			CLEAR_PRINTS 
			t2_storing_speech_goals_number = t2_speech_goals 
			t2_storing_speech_control_number = t2_speech_control_flag 
			t2_speech_goals = 12
			GENERATE_RANDOM_INT_IN_RANGE 0 3 t2_speech_control_flag
			t2_random_last_label = t2_speech_control_flag + 1 
			GOSUB t2_dialogue_setup
		ENDIF
	ENDIF
ENDIF

IF t2_goals < 4
	IF t2_speech_goals = 12 //carl is out of car
		IF NOT IS_CHAR_IN_CAR scplayer t2_mothership
			IF t2_speech_control_flag < t2_last_label
				GOSUB t2_loading_dialogue
				GOSUB t2_playing_dialogue
				IF NOT IS_CHAR_DEAD truth
					GOSUB t2_finishing_dialogue  
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag] 
					t2_slot1 = 0
					t2_slot2 = 0
				ENDIF
			ELSE
				PRINT ( TRU2_17 ) 11000 1 // Get inside the mothership.
				t2_speech_goals = 13
			ENDIF
		ENDIF
		IF IS_CHAR_SITTING_IN_CAR scplayer t2_mothership
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag]
			t2_speech_goals = 14
			t2_speech_control_flag = 0
			CLEAR_PRINTS 
			//GOSUB t2_dialogue_setup
		ENDIF
	ENDIF

	IF t2_speech_goals = 13 //carl has been out of car and has returned
		IF IS_CHAR_SITTING_IN_CAR scplayer t2_mothership 
			t2_speech_goals = 14
			t2_speech_control_flag = 0
			CLEAR_PRINTS 
			//GOSUB t2_dialogue_setup
		ENDIF
	ENDIF

	IF t2_speech_goals = 14 //where player has returned to the car
		IF IS_CHAR_SITTING_IN_CAR scplayer t2_mothership 	
			timerb = 0
			t2_speech_goals = t2_storing_speech_goals_number
			t2_speech_control_flag = t2_storing_speech_control_number
			GOSUB t2_dialogue_setup
			IF t2_storing_speech_goals_number = 0 
				PRINT ( TRU2_12 ) 7000 1 // Drive the Mothership to San Fran.
			ENDIF
		ENDIF
		IF NOT IS_CHAR_IN_CAR scplayer t2_mothership
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag]
			t2_speech_goals = 12
			GENERATE_RANDOM_INT_IN_RANGE 0 3 t2_speech_control_flag
			t2_random_last_label = t2_speech_control_flag + 1 
			GOSUB t2_dialogue_setup
		ENDIF
	ENDIF
ENDIF	
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_loading_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF t2_slot_load < t2_last_label 
	//slot 1
	IF t2_slot1 = 0
		LOAD_MISSION_AUDIO 1 t2_audio_label[t2_slot_load] 
		t2_slot_load ++ 
		t2_slot1 = 1
	ENDIF

	//slot 2
	IF t2_slot2 = 0
		LOAD_MISSION_AUDIO 2 t2_audio_label[t2_slot_load] 
		t2_slot_load ++ 
		t2_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF t2_play_which_slot = 1 
	IF t2_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $t2_print_label[t2_speech_control_flag] ) 4500 1 //
			t2_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF t2_play_which_slot = 2 
	IF t2_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $t2_print_label[t2_speech_control_flag] ) 4500 1 //
			t2_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
t2_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF t2_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag]
		t2_speech_control_flag ++		
		t2_play_which_slot = 2
		t2_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF t2_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $t2_print_label[t2_speech_control_flag]
		t2_speech_control_flag ++		
		t2_play_which_slot = 1
		t2_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

}

