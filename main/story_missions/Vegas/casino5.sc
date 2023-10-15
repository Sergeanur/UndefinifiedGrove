MISSION_START

// ########################
// ##
// ##	Casino5.sc 
// ##
// ##	Intensive Care
// ##
// ## 	Simon Lashley
// ##
// ######################## 

SCRIPT_NAME CASINO5

GOSUB mission_casino5_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino5_FAILED
ENDIF
											
GOSUB mission_casino5_CLEANUP

MISSION_END

{

// variables

// mafia
LVAR_INT mafia_timers_C5[3]

// timers
LVAR_INT this_frame_time_C5 last_frame_time_C5 time_diff_C5

// general
LVAR_INT pointer_C5 pointer2_C5 sequence_C5 sequence_progress_C5 goto_blip_C5 random_C5 cutscene_flag_C5 tough_dm_C5 empty_dm_C5 normal_dm_C5 player_vehicle_C5

// fail text
LVAR_INT fail_text_flag_C5
LVAR_TEXT_LABEL fail_text_C5
 
mission_casino5_START:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	// text
	LOAD_MISSION_TEXT CASINO5

	SET_CHAR_COORDINATES scplayer 2175.4116 1681.5483 9.8203
	SET_CHAR_HEADING scplayer 90.0 
	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 9.8203

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

	iTerminateAllAmbience = 1

// **************************************** START OF CUTSCENE

	SET_AREA_VISIBLE 2

	LOAD_CUTSCENE CAS_5a
	 
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
	SET_AREA_VISIBLE 0

// **************************************** END OF CUTSCENE

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

	// audio
	START_NEW_SCRIPT start_audio_controller

	LOAD_SCENE 2175.4116 1681.5483 9.8203

	// models
	REQUEST_MODEL AMBULAN
	REQUEST_MODEL PATRIOT

	REQUEST_MODEL LVEMT1
	REQUEST_MODEL VMAFF1
	REQUEST_MODEL VMAFF2

	LOAD_SPECIAL_CHARACTER 1 SINDACO 

	REQUEST_MODEL MP5LNG
	
	// anims
	REQUEST_ANIMATION GANGS
	 
	WHILE NOT HAS_MODEL_LOADED AMBULAN 
	OR NOT HAS_MODEL_LOADED PATRIOT
	OR NOT HAS_MODEL_LOADED LVEMT1
	OR NOT HAS_MODEL_LOADED VMAFF1
	OR NOT HAS_MODEL_LOADED VMAFF2
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_MODEL_LOADED MP5LNG
	OR NOT HAS_ANIMATION_LOADED GANGS 
		WAIT 0
	ENDWHILE

	// decision makers
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm_C5
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_NORM normal_dm_C5
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_C5

	// relationships
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
 
LVAR_INT never_run_this_C5
	never_run_this_C5 = 0
	IF never_run_this_C5 = 1
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 0.0 0.0 0.0 johnny_C5
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 0.0 0.0 0.0 backup_driver_C5[pointer_C5]
		CREATE_CAR AMBULAN 0.0 0.0 0.0 backup_car_C5[pointer_C5]
	ENDIF

	SET_FADING_COLOUR 0 0 0 
				
	SET_PLAYER_CONTROL player1 ON
	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	SWITCH_WIDESCREEN OFF
	
	DO_FADE 0 FADE_IN

	PRINT_NOW CAS5_00 8000 1
	ADD_BLIP_FOR_COORD 1604.1581 1838.7648 9.8280 goto_blip_C5

	// audio 
	$audio_text_label_C5[0] = &CAS5_AA
	audio_sound_label_C5[0]= SOUND_CAS5_AA
	$audio_text_label_C5[1] = &CAS5_AB
	audio_sound_label_C5[1]= SOUND_CAS5_AB

	$audio_text_label_C5[2] = &CAS5_BA
	audio_sound_label_C5[2]= SOUND_CAS5_BA
	$audio_text_label_C5[3] = &CAS5_BB
	audio_sound_label_C5[3]= SOUND_CAS5_BB
	$audio_text_label_C5[4] = &CAS5_BC
	audio_sound_label_C5[4]= SOUND_CAS5_BC
	$audio_text_label_C5[5] = &CAS5_BD
	audio_sound_label_C5[5]= SOUND_CAS5_BD
	$audio_text_label_C5[6] = &CAS5_BE
	audio_sound_label_C5[6]= SOUND_CAS5_BE

	$audio_text_label_C5[7] = &CAS5_CA
	audio_sound_label_C5[7]= SOUND_CAS5_CA
	$audio_text_label_C5[8] = &CAS5_CB
	audio_sound_label_C5[8]= SOUND_CAS5_CB
	$audio_text_label_C5[9] = &CAS5_CC
	audio_sound_label_C5[9]= SOUND_CAS5_CC
	$audio_text_label_C5[10] = &CAS5_CD
	audio_sound_label_C5[10]= SOUND_CAS5_CD
	$audio_text_label_C5[11] = &CAS5_CE
	audio_sound_label_C5[11]= SOUND_CAS5_CE
	$audio_text_label_C5[12] = &CAS5_CF
	audio_sound_label_C5[12]= SOUND_CAS5_CF
	$audio_text_label_C5[13] = &CAS5_CG
	audio_sound_label_C5[13]= SOUND_CAS5_CG

	$audio_text_label_C5[14] = &CAS5_DA
	audio_sound_label_C5[14]= SOUND_CAS5_DA
	$audio_text_label_C5[15] = &CAS5_DB
	audio_sound_label_C5[15]= SOUND_CAS5_DB

	audio_pointer_C5 = 0
	audio_status_C5 = 0
	TIMERB = 0

	patriot_status_C5 = 0

// ########################################################################################################################################
// ##
// ##	Get to the hospital
// ##
// ######################################################################################################################################## 

LVAR_INT patriot_C5 patriot_status_C5

mission_casino5_MAIN_get_to_hospital:

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_casino5_PASSED  
	ENDIF

	// patriot for player to use if he wants
	IF patriot_status_C5 = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1604.1581 1838.7648 9.8280 120.0 120.0 120.0 FALSE

			CLEAR_AREA 1604.1581 1838.7648 9.8280 30.0 TRUE
			
			SUPPRESS_CAR_MODEL PATRIOT
			CUSTOM_PLATE_FOR_NEXT_CAR PATRIOT H_CARMAN

		   	CLEAR_AREA 1624.5983 1850.6071 9.8280 8.0 TRUE
		   	CREATE_CAR PATRIOT 1624.5983 1850.6071 9.8280 patriot_C5
			SET_CAR_HEADING patriot_C5 180.0 	

			SET_CAR_STRONG patriot_C5 TRUE

			patriot_status_C5 ++

		ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1604.1581 1838.7648 9.8280 4.0 4.0 4.0 TRUE 
	
		IF IS_CHAR_IN_ANY_CAR scplayer

			CLEAR_PRINTS

			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 400 FADE_OUT
			
			WHILE GET_FADING_STATUS
				WAIT 0  
			ENDWHILE	

			REMOVE_BLIP goto_blip_C5
			
			GOTO mission_casino5_CUT_in_hospital

		ELSE
			PRINT_NOW CAS5_01 4000 1 
		ENDIF
	ENDIF

	GOSUB mission_casino5_SUB_dialogue
	
GOTO mission_casino5_MAIN_get_to_hospital



LVAR_INT ambulance_C5[3] ambulance_blip_C5[3] ambulance_status_C5[3] driver_C5[3] passenger_C5[3] johnny_C5  


mission_casino5_CUT_in_hospital:

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
							    
	SET_FIXED_CAMERA_POSITION 1595.1992 1838.3158 12.4095 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1596.1803 1838.2522 12.2269 JUMP_CUT

	IF NOT SLOT_status[0] < 0
		SLOT_status[0] = -2
	ENDIF
	IF NOT SLOT_status[0] < 0
		SLOT_status[1] = -2
	ENDIF
	
	STORE_CAR_CHAR_IS_IN scplayer player_vehicle_C5
	SET_CAR_HEALTH player_vehicle_C5 2000
	
	CLEAR_AREA 1604.1581 1838.7648 9.8280 15.0 TRUE

	//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS player_vehicle_C5 3.0 0.0 0.0 x y z

	DO_FADE 0 FADE_IN

	OPEN_SEQUENCE_TASK sequence_C5
		TASK_LEAVE_ANY_CAR -1
		TASK_GO_STRAIGHT_TO_COORD -1 1606.1058 1825.0652 9.8280 PEDMOVE_RUN -1
		TASK_STAND_STILL -1 100
	CLOSE_SEQUENCE_TASK sequence_C5	
	PERFORM_SEQUENCE_TASK scplayer sequence_C5
	CLEAR_SEQUENCE_TASK sequence_C5

	sequence_progress_C5 = 0

	cutscene_flag_C5 = 0

	WHILE sequence_progress_C5 < 2
		WAIT 0
		GET_SEQUENCE_PROGRESS scplayer sequence_progress_C5
	ENDWHILE

	SKIP_CUTSCENE_START

	audio_status_C5 = 4

	WHILE audio_pointer_C5 < 7
		WAIT 0
		GOSUB mission_casino5_SUB_dialogue
	ENDWHILE
	 
	OPEN_SEQUENCE_TASK sequence_C5
		TASK_STAND_STILL -1 3800
	  	IF NOT IS_CAR_DEAD player_vehicle_C5 
	  		TASK_ENTER_CAR_AS_DRIVER -1 player_vehicle_C5 -1
		ENDIF
	CLOSE_SEQUENCE_TASK sequence_C5	
	PERFORM_SEQUENCE_TASK scplayer sequence_C5
	CLEAR_SEQUENCE_TASK sequence_C5

	pointer_C5 = 0
	WHILE pointer_C5 = 0 
		WAIT 0
		IF NOT IS_CAR_DEAD player_vehicle_C5
			IF IS_CHAR_IN_CAR scplayer player_vehicle_C5
		 		pointer_C5 = 1
			ENDIF
		ENDIF
	ENDWHILE
	
	cutscene_flag_C5 = 1

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE
		  										  
	SKIP_CUTSCENE_END

	CLEAR_PRINTS
	IF NOT SLOT_status[0] < 0
		SLOT_status[0] = -2
	ENDIF
	IF NOT SLOT_status[0] < 0
		SLOT_status[1] = -2
	ENDIF

	IF NOT IS_CHAR_GETTING_IN_TO_A_CAR scplayer
	OR NOT IS_CHAR_IN_ANY_CAR scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		IF NOT IS_CAR_DEAD player_vehicle_C5 
			WARP_CHAR_INTO_CAR scplayer player_vehicle_C5
		ENDIF
	ENDIF 	

	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	
	PRINT_NOW CAS5_03 8000 1

	// disable certain nodes for wander
	// casino strip near triad casino
	MARK_ROAD_NODE_AS_DONT_WANDER 2066.2500 956.0000 9.1250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2048.6248 953.8749 9.1250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2032.5000 973.2500 9.5000 
	
	// up west side of casino strip - all way to the top (north)
	MARK_ROAD_NODE_AS_DONT_WANDER 2027.2499 1093.1249 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2030.1249 1273.0000 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2028.9999 1453.2500 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2033.7499 1713.1250 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2112.1248 1863.1250 9.7500 
	MARK_ROAD_NODE_AS_DONT_WANDER 2103.9998 1969.1250 9.7500 
	MARK_ROAD_NODE_AS_DONT_WANDER 2108.2498 2021.9999 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2107.6248 2113.1248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2091.2498 2283.1248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2005.6249 2393.2498 9.6250
	
	// town at north of vegas
	MARK_ROAD_NODE_AS_DONT_WANDER 1605.3750 2589.7500 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 1477.3750 2563.1248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 1385.6250 2575.0000 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 1293.8750 2574.6248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 1528.1250 2773.1248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2127.3748 2792.8748 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2397.3748 2713.1248 9.6250  
	
	// north,east,south edge and freeway exits
	MARK_ROAD_NODE_AS_DONT_WANDER 2404.6248 2513.1248 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2628.6248 2301.7500 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2787.2500 2303.4998 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2860.1248 2293.0000 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2796.1248 2135.3748 9.7500 
	MARK_ROAD_NODE_AS_DONT_WANDER 2796.1248 2090.8748 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2638.3748 2135.3748 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2638.3748 2090.8748 9.7500 
	MARK_ROAD_NODE_AS_DONT_WANDER 2621.6248 1473.1250 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2645.3748 1313.1250 9.7500 
	MARK_ROAD_NODE_AS_DONT_WANDER 2547.2500 923.1250 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2287.2500 957.8749 9.6250 
	MARK_ROAD_NODE_AS_DONT_WANDER 2147.2500 963.2499 9.6250 

	// ambulances
	CLEAR_AREA 2152.0291 1912.4558 9.6797 10.0 TRUE
	
	CREATE_CAR AMBULAN 2152.0291 1912.4558 9.6797 ambulance_C5[0]
	SET_CAR_HEADING ambulance_C5[0] 0.0
	ADD_BLIP_FOR_CAR ambulance_C5[0] ambulance_blip_C5[0] 
	LOCK_CAR_DOORS ambulance_C5[0] CARLOCK_LOCKOUT_PLAYER_ONLY
	SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[0] FALSE

	CREATE_CHAR_INSIDE_CAR ambulance_C5[0] PEDTYPE_MISSION1 LVEMT1 driver_C5[0]
	SET_CHAR_CANT_BE_DRAGGED_OUT driver_C5[0] TRUE
	SET_CHAR_DECISION_MAKER driver_C5[0] empty_dm_C5

	CREATE_CHAR_AS_PASSENGER ambulance_C5[0] PEDTYPE_MISSION1 LVEMT1 0 passenger_C5[0]
	SET_CHAR_CANT_BE_DRAGGED_OUT passenger_C5[0] TRUE
	SET_CHAR_DECISION_MAKER passenger_C5[0] empty_dm_C5

	TASK_CAR_DRIVE_WANDER driver_C5[0] ambulance_C5[0] 15.0 DRIVINGMODE_STOPFORCARS 
	ambulance_status_C5[0] = 0
	ambulance_collision_status_C5[0] = 0
	
	CLEAR_AREA 2050.2913 1358.5164 9.6719 10.0 TRUE
	
	CREATE_CAR AMBULAN 2050.2913 1358.5164 9.6719 ambulance_C5[1]
	SET_CAR_HEADING ambulance_C5[1] 180.0
	ADD_BLIP_FOR_CAR ambulance_C5[1] ambulance_blip_C5[1] 
	LOCK_CAR_DOORS ambulance_C5[1] CARLOCK_LOCKOUT_PLAYER_ONLY
	SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[1] FALSE

	CREATE_CHAR_INSIDE_CAR ambulance_C5[1] PEDTYPE_MISSION1 LVEMT1 driver_C5[1]
	SET_CHAR_CANT_BE_DRAGGED_OUT driver_C5[1] TRUE
	SET_CHAR_DECISION_MAKER driver_C5[1] empty_dm_C5

	CREATE_CHAR_AS_PASSENGER ambulance_C5[1] PEDTYPE_MISSION1 LVEMT1 0 passenger_C5[1]
	SET_CHAR_CANT_BE_DRAGGED_OUT passenger_C5[1] TRUE
	SET_CHAR_DECISION_MAKER passenger_C5[1] empty_dm_C5

	TASK_CAR_DRIVE_WANDER driver_C5[1] ambulance_C5[1] 15.0 DRIVINGMODE_STOPFORCARS 
	ambulance_status_C5[1] = 0
	ambulance_collision_status_C5[1] = 0
	
	CLEAR_AREA 2544.9802 1395.7972 9.7800 10.0 TRUE
	
	CREATE_CAR AMBULAN 2544.9802 1395.7972 9.7800 ambulance_C5[2]
	SET_CAR_HEADING ambulance_C5[2] 180.0
	ADD_BLIP_FOR_CAR ambulance_C5[2] ambulance_blip_C5[2] 
	LOCK_CAR_DOORS ambulance_C5[2] CARLOCK_LOCKOUT_PLAYER_ONLY
	SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[2] FALSE

	CREATE_CHAR_INSIDE_CAR ambulance_C5[2] PEDTYPE_MISSION1 LVEMT1 driver_C5[2]
	SET_CHAR_CANT_BE_DRAGGED_OUT driver_C5[2] TRUE
	SET_CHAR_DECISION_MAKER driver_C5[2] empty_dm_C5

	CREATE_CHAR_AS_PASSENGER ambulance_C5[2] PEDTYPE_MISSION1 LVEMT1 0 passenger_C5[2]
	SET_CHAR_CANT_BE_DRAGGED_OUT passenger_C5[2] TRUE
	SET_CHAR_DECISION_MAKER passenger_C5[2] empty_dm_C5

	TASK_CAR_DRIVE_WANDER driver_C5[2] ambulance_C5[2] 15.0 DRIVINGMODE_STOPFORCARS 
	ambulance_status_C5[2] = 0
	ambulance_collision_status_C5[2] = 0
	
	mafia_exist_C5 = 0
	mafia_ID_C5 = -1
	
	ambulances_rammed_C5 = 0
  	mafia_status_C5 = 0


// ########################################################################################################################################
// ##
// ##	Find the ambulance
// ##
// ########################################################################################################################################


LVAR_INT mafia_exist_C5 mafia_ID_C5 other_ID_C5 ambulances_rammed_C5 text_status_C5 ram_text_C5 alert_status_C5 mafia_status_C5 exit_status_C5[3]
LVAR_FLOAT motel_X_C5 motel_Y_C5 motel_Z_C5

mission_casino5_MAIN_find_ambulance:

	WAIT 0


	pointer_C5 = 0
	
	WHILE pointer_C5 < 3
	
		IF NOT IS_CAR_DEAD ambulance_C5[pointer_C5]
		AND ambulance_status_C5[pointer_C5] < 6

			SWITCH ambulance_status_C5[pointer_C5]
			
				CASE 0	// no-one has been hit

					IF IS_CHAR_DEAD driver_C5[pointer_C5]
					OR IS_CHAR_DEAD passenger_C5[pointer_C5]
						text_status_C5 = 0 // someone has been killed
						IF ambulances_rammed_C5 = 0
							GOSUB mission_casino5_SUB_setup_mafia_ambulance
						ENDIF
					ELSE
						IF HAS_CAR_BEEN_DAMAGED_BY_CHAR ambulance_C5[pointer_C5] scplayer
							text_status_C5 = 1 // ambulance has been damaged by player
							IF ambulances_rammed_C5 = 0
								GOSUB mission_casino5_SUB_setup_mafia_ambulance
							ENDIF
						ELSE
							IF ram_text_C5 = 0
								IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer ambulance_C5[pointer_C5] 40.0 40.0 FALSE
								AND IS_CAR_ON_SCREEN ambulance_C5[pointer_C5]
									PRINT_NOW CAS5_04 8000 1
									ram_text_C5 = 1
								ENDIF
							ENDIF
							
							BREAK
						ENDIF 
					ENDIF

					
					// text
					IF text_status_C5 = 0
						IF ambulances_rammed_C5 = 0  
							PRINT_NOW CAS5_07 8000 1 // killed medic must be one of the other
						ELSE
							PRINT_NOW CAS5_08 8000 1 // killed medic must be in the other ambulance
						ENDIF
					ELSE
						IF ambulances_rammed_C5 = 0
							PRINT_NOW CAS5_05 8000 1 // Johnny not in this ambulance must be one of the others
						ELSE
							PRINT_NOW CAS5_06 8000 1 // not this one must be other one
						ENDIF 
					ENDIF

					REMOVE_BLIP ambulance_blip_C5[pointer_C5]
					ambulances_rammed_C5 ++

					exit_status_C5[pointer_C5] = 0

					// stop ambulance and make one of paramedics get out and do anim then get back in
					SET_CAR_TEMP_ACTION ambulance_C5[pointer_C5] TEMPACT_HANDBRAKESTRAIGHT 5000

					IF NOT IS_CHAR_DEAD driver_C5[pointer_C5] // clears the car doing a wander action after the temp action
						CLEAR_CHAR_TASKS driver_C5[pointer_C5]

						OPEN_SEQUENCE_TASK sequence_C5
							TASK_LEAVE_ANY_CAR -1
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							TASK_LOOK_AT_CHAR -1 scplayer 1500
							TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 1000.0 FALSE FALSE FALSE FALSE 2000
							TASK_PLAY_ANIM -1 prtial_gngtlkG GANGS 1000.0 FALSE FALSE FALSE FALSE 2000
							TASK_STAND_STILL -1 5000										
						CLOSE_SEQUENCE_TASK sequence_C5
						PERFORM_SEQUENCE_TASK driver_C5[pointer_C5] sequence_C5
						CLEAR_SEQUENCE_TASK sequence_C5
						exit_status_C5[pointer_C5] += 1
					ENDIF

					IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5] // clears the car doing a wander action after the temp action
						CLEAR_CHAR_TASKS passenger_C5[pointer_C5]

						OPEN_SEQUENCE_TASK sequence_C5
							TASK_LEAVE_ANY_CAR -1
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							TASK_LOOK_AT_CHAR -1 scplayer 1500
							TASK_PLAY_ANIM -1 prtial_gngtlkB GANGS 1000.0 FALSE FALSE FALSE FALSE 2000
							TASK_PLAY_ANIM -1 prtial_gngtlkC GANGS 1000.0 FALSE FALSE FALSE FALSE 2000
							TASK_STAND_STILL -1 5000										
						CLOSE_SEQUENCE_TASK sequence_C5
						PERFORM_SEQUENCE_TASK passenger_C5[pointer_C5] sequence_C5
						CLEAR_SEQUENCE_TASK sequence_C5
						exit_status_C5[pointer_C5] += 2
					ENDIF
					
					IF exit_status_C5[pointer_C5] = 0							
						ambulance_status_C5[pointer_C5] = 3
						BREAK
					ENDIF
										
					ambulance_status_C5[pointer_C5] ++


				// ##########################################
				// ##
				// ##	STUFF FOR REAL AMBULANCES BEING HIT
				// ##
				// ##########################################

				
				CASE 1	// check for paramedic getting out of the ambulance and tell them to get back in
					
					// driver is getting out
					IF exit_status_C5[pointer_C5] = 1
					OR exit_status_C5[pointer_C5] = 3
					
						IF NOT IS_CHAR_DEAD driver_C5[pointer_C5]
					
							IF NOT IS_CHAR_IN_ANY_CAR driver_C5[pointer_C5]
								GET_SEQUENCE_PROGRESS driver_C5[pointer_C5] sequence_progress_C5
								IF sequence_progress_C5 = 5
									TASK_ENTER_CAR_AS_DRIVER driver_C5[pointer_C5] ambulance_C5[pointer_C5] -2 
	 								exit_status_C5[pointer_C5] -= 1
	 							ENDIF
								
							ENDIF

						ELSE
							IF exit_status_C5[pointer_C5] = 1
							OR exit_status_C5[pointer_C5] = 3
								exit_status_C5[pointer_C5] -= 1
							ENDIF
						ENDIF						

					ENDIF
			
					// passenger is getting out
					IF exit_status_C5[pointer_C5] = 2
					OR exit_status_C5[pointer_C5] = 3
						IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
					
							IF NOT IS_CHAR_IN_ANY_CAR passenger_C5[pointer_C5]
								GET_SEQUENCE_PROGRESS passenger_C5[pointer_C5] sequence_progress_C5
								IF sequence_progress_C5 = 5
									IF IS_CHAR_DEAD driver_C5[pointer_C5]
										TASK_ENTER_CAR_AS_DRIVER passenger_C5[pointer_C5] ambulance_C5[pointer_C5] -2
									ELSE
										TASK_ENTER_CAR_AS_PASSENGER passenger_C5[pointer_C5] ambulance_C5[pointer_C5] -2 0
									ENDIF
									exit_status_C5[pointer_C5] -= 2 
	 							ENDIF
							ENDIF						
						
						ELSE
							IF exit_status_C5[pointer_C5] = 2
							OR exit_status_C5[pointer_C5] = 3
								exit_status_C5[pointer_C5] -= 2
							ENDIF
						ENDIF
					ENDIF					

					IF exit_status_C5[pointer_C5] = 0
		
						IF NOT IS_CHAR_DEAD driver_C5[pointer_C5] 
							exit_status_C5[pointer_C5] += 1
						ENDIF
						IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5] 
							exit_status_C5[pointer_C5] += 2
						ENDIF

						IF exit_status_C5[pointer_C5] = 0
							ambulance_status_C5[pointer_C5] = 3
							BREAK
						ELSE
							ambulance_status_C5[pointer_C5] ++
						ENDIF
					ELSE
						BREAK	
					ENDIF
					 
				CASE 2 // wait for paramedic/s to get back in then drive off

					// driver is getting in
					IF exit_status_C5[pointer_C5] = 1
					OR exit_status_C5[pointer_C5] = 3
					
						IF NOT IS_CHAR_DEAD driver_C5[pointer_C5]
							IF IS_CHAR_IN_CAR driver_C5[pointer_C5] ambulance_C5[pointer_C5]
		 						exit_status_C5[pointer_C5] -= 1
							ENDIF
						ELSE
							IF exit_status_C5[pointer_C5] = 1
							OR exit_status_C5[pointer_C5] = 3
								exit_status_C5[pointer_C5] -= 1
							ENDIF
						ENDIF						

					ENDIF
			
					// passenger is getting in
					IF exit_status_C5[pointer_C5] = 2
					OR exit_status_C5[pointer_C5] = 3
						IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
							IF IS_CHAR_IN_CAR passenger_C5[pointer_C5] ambulance_C5[pointer_C5]
								exit_status_C5[pointer_C5] -= 2 
							ENDIF						
						ELSE
							IF exit_status_C5[pointer_C5] = 2
							OR exit_status_C5[pointer_C5] = 3
								exit_status_C5[pointer_C5] -= 2
							ENDIF
						ENDIF
					ENDIF
					
					LVAR_INT temp_driver_C5
					IF exit_status_C5[pointer_C5] = 0
						IF NOT IS_CHAR_DEAD driver_C5[pointer_C5] 
							TASK_CAR_DRIVE_WANDER driver_C5[pointer_C5] ambulance_C5[pointer_C5] 15.0 DRIVINGMODE_STOPFORCARS
						ELSE
							IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5] 
								GET_DRIVER_OF_CAR ambulance_C5[pointer_C5] temp_driver_C5	
								
								OPEN_SEQUENCE_TASK sequence_C5
									IF NOT temp_driver_C5 = passenger_C5[pointer_C5] 
										TASK_SHUFFLE_TO_NEXT_CAR_SEAT -1 ambulance_C5[pointer_C5]
								  	ENDIF
								  	TASK_CAR_DRIVE_WANDER -1 ambulance_C5[pointer_C5] 15.0 DRIVINGMODE_STOPFORCARS
								CLOSE_SEQUENCE_TASK sequence_C5	
								PERFORM_SEQUENCE_TASK passenger_C5[pointer_C5] sequence_C5
								CLEAR_SEQUENCE_TASK sequence_C5
							ENDIF
						ENDIF
						ambulance_status_C5[pointer_C5] ++
					ELSE
						BREAK	
					ENDIF										
				
				CASE 3 // remove stuff
					IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer ambulance_C5[pointer_C5] 50.0 50.0 FALSE
					OR IS_CAR_ON_SCREEN ambulance_C5[pointer_C5]
						BREAK
					ENDIF
					
					MARK_CAR_AS_NO_LONGER_NEEDED ambulance_C5[pointer_C5]
					MARK_CHAR_AS_NO_LONGER_NEEDED driver_C5[pointer_C5]
					MARK_CHAR_AS_NO_LONGER_NEEDED passenger_C5[pointer_C5]

					ambulance_status_C5[pointer_C5]  = 10000
				BREAK

				
				// ###########################################
				// ##
				// ##	STUFF FOR MAFIA AMBULANCE 
				// ##
				// ###########################################
				
				LVAR_INT passenger_driving_C5 player_hijacked_ambulance_C5
				LVAR_INT current_health_C5 damage_difference_C5 player_damage_C5

				CASE 4 // check for amulance getting to motel				
					
					IF NOT IS_CHAR_DEAD johnny_C5
						
						IF IS_CHAR_DEAD driver_C5[pointer_C5]
						OR IS_CHAR_DEAD passenger_C5[pointer_C5]
							PRINT_NOW CAS5_10 8000 1
							ambulance_status_C5[pointer_C5] = 5 
						ELSE
							IF HAS_CAR_BEEN_DAMAGED_BY_CHAR ambulance_C5[pointer_C5] scplayer
								PRINT_NOW CAS5_09 8000 1
								ambulance_status_C5[pointer_C5] = 5
							ELSE						
						
								IF TIMERA > 120000
								
									IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer ambulance_C5[pointer_C5] 60.0 60.0 FALSE
									OR IS_CAR_ON_SCREEN ambulance_C5[pointer_C5]

										IF IS_CHAR_IN_CAR scplayer ambulance_C5[pointer_C5]
										
											player_hijacked_ambulance_C5 = 1	

										ENDIF 
										
										BREAK

									ELSE
										// remove ambulance and peds instantly so player cant find it after mission failed 
										REMOVE_BLIP ambulance_blip_C5[pointer_C5]
										
										IF DOES_CHAR_EXIST johnny_C5
											DELETE_CHAR johnny_C5
										ENDIF
										IF DOES_CHAR_EXIST driver_C5[pointer_C5]
											DELETE_CHAR driver_C5[pointer_C5]
										ENDIF
										IF DOES_CHAR_EXIST passenger_C5[pointer_C5]
											DELETE_CHAR passenger_C5[pointer_C5]
										ENDIF

										IF DOES_VEHICLE_EXIST ambulance_C5[pointer_C5]
											DELETE_CAR ambulance_C5[pointer_C5] 
										ENDIF

										fail_text_flag_C5 = 1
										$fail_text_C5 = CAS5_32
										GOTO mission_casino5_FAILED		
									ENDIF
								ELSE
									
									IF alert_status_C5 < 2
										IF alert_status_C5 = 0
											IF NOT IS_MESSAGE_BEING_DISPLAYED
												PRINT_NOW CAS5_30 8000 1
												alert_status_C5 ++
											ENDIF
										ELSE
											IF NOT IS_MESSAGE_BEING_DISPLAYED
												PRINT_NOW CAS5_31 8000 1
												alert_status_C5 ++
											ENDIF
										ENDIF
									ENDIF

									BREAK

								ENDIF
							ENDIF
						ENDIF

					ELSE
						fail_text_flag_C5 = 1
						$fail_text_C5 = CAS5_17
						GOTO mission_casino5_FAILED		
					ENDIF

					

				CASE 5 // check for ambulance getting hit

					IF NOT IS_CHAR_DEAD johnny_C5

						IF mafia_status_C5 = 0
													
							pointer2_C5 = 0
							WHILE pointer2_C5 < 3
								IF NOT pointer_C5 = pointer2_C5
									REMOVE_BLIP ambulance_blip_C5[pointer2_C5]
									IF ambulance_status_C5[pointer2_C5] < 3
										ambulance_status_C5[pointer2_C5] = 3
									ENDIF 
								ENDIF
								pointer2_C5 ++
							ENDWHILE
							MARK_MODEL_AS_NO_LONGER_NEEDED LVEMT1 
							REMOVE_ANIMATION GANGS

							REQUEST_MODEL SENTINEL

							IF player_hijacked_ambulance_C5 = 0
								IF NOT IS_CHAR_DEAD driver_C5[pointer_C5] 
									CLEAR_CHAR_TASKS driver_C5[pointer_C5]
	 								TASK_CAR_DRIVE_WANDER driver_C5[pointer_C5] ambulance_C5[pointer_C5] 25.0 DRIVINGMODE_AVOIDCARS	
			 						
			 						IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
										TASK_DRIVE_BY passenger_C5[pointer_C5] scplayer -1 0.0 0.0 0.0 300.0 DRIVEBY_FIXED_RHS TRUE 35
									ENDIF
			 						passenger_driving_C5 = 0
								ELSE
									IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
										
										CLEAR_CHAR_TASKS passenger_C5[pointer_C5] 
										OPEN_SEQUENCE_TASK sequence_C5
											TASK_SHUFFLE_TO_NEXT_CAR_SEAT -1 ambulance_C5[pointer_C5]
			   								TASK_CAR_DRIVE_WANDER -1 ambulance_C5[pointer_C5] 25.0 DRIVINGMODE_AVOIDCARS	
										CLOSE_SEQUENCE_TASK sequence_C5	
										PERFORM_SEQUENCE_TASK passenger_C5[pointer_C5] sequence_C5
										CLEAR_SEQUENCE_TASK sequence_C5									
										passenger_driving_C5 = 1
									ENDIF
								ENDIF
							ENDIF

							SWITCH_CAR_SIREN ambulance_C5[pointer_C5] ON
							SET_CAR_HEALTH ambulance_C5[pointer_C5] ambulance_health_C5

							mafia_status_C5 = 1
							player_damage_C5 = 0
							under_attack_text_C5 = 0

						ELSE
							
							GET_CAR_HEALTH ambulance_C5[pointer_C5] current_health_C5  

							IF HAS_CAR_BEEN_DAMAGED_BY_CHAR ambulance_C5[pointer_C5] scplayer
								damage_difference_C5 = ambulance_health_c5 - current_health_C5
								IF HAS_CAR_BEEN_DAMAGED_BY_WEAPON ambulance_C5[pointer_C5] WEAPONTYPE_ANYWEAPON
									damage_difference_C5 /= 20
									// just in case damage is 0, make it 1
									IF damage_difference_C5 = 0
										damage_difference_C5 = 1
									ENDIF	 
								ELSE
									damage_difference_C5 *= 2
								ENDIF								   
								player_damage_C5 += damage_difference_C5
							ENDIF
							
							SET_CAR_HEALTH ambulance_C5[pointer_C5] ambulance_health_C5
							
							CLEAR_CAR_LAST_DAMAGE_ENTITY ambulance_C5[pointer_C5]			
				
							// check if player has damaged the ambulance enough to make it stop
							IF player_damage_C5 < 200
								IF NOT IS_CHAR_IN_CAR scplayer ambulance_C5[pointer_C5]
									// check if one or both mafia guys have been killed
									IF IS_CHAR_DEAD driver_C5[pointer_C5] 
										IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
											IF passenger_driving_C5 = 0
												CLEAR_CHAR_TASKS passenger_C5[pointer_C5] 
												OPEN_SEQUENCE_TASK sequence_C5
													TASK_SHUFFLE_TO_NEXT_CAR_SEAT -1 ambulance_C5[pointer_C5]
							   						TASK_CAR_DRIVE_WANDER -1 ambulance_C5[pointer_C5] 25.0 DRIVINGMODE_AVOIDCARS	
												CLOSE_SEQUENCE_TASK sequence_C5	
												PERFORM_SEQUENCE_TASK passenger_C5[pointer_C5] sequence_C5
												CLEAR_SEQUENCE_TASK sequence_C5
												passenger_driving_C5 = 1
											ENDIF
											BREAK									
		 								ENDIF
									ELSE
										BREAK
									ENDIF
								ELSE
									player_hijacked_ambulance_C5 = 1
								ENDIF
	 						ENDIF

							SET_CAR_HEALTH ambulance_C5[pointer_C5] 3000
							IF player_hijacked_ambulance_C5 = 0
								SET_CAR_TEMP_ACTION ambulance_C5[pointer_C5] TEMPACT_HANDBRAKESTRAIGHT 5000
							ENDIF

							IF NOT IS_CHAR_DEAD	driver_C5[pointer_C5]
								CLEAR_CHAR_TASKS driver_C5[pointer_C5] 
								OPEN_SEQUENCE_TASK sequence_C5
									TASK_LEAVE_CAR -1 ambulance_C5[pointer_C5]
								  	TASK_STAY_IN_SAME_PLACE -1 TRUE 
								  	TASK_KILL_CHAR_ON_FOOT -1 scplayer
								CLOSE_SEQUENCE_TASK sequence_C5	
								PERFORM_SEQUENCE_TASK driver_C5[pointer_C5] sequence_C5
								CLEAR_SEQUENCE_TASK sequence_C5
								//ADD_BLIP_FOR_CHAR driver_C5[pointer_C5] mafia_blip_C5[0]
								//mafia_status_C5[0] = 0
							ELSE
								//mafia_status_C5[0] = -1
							ENDIF

							IF NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
								CLEAR_CHAR_TASKS passenger_C5[pointer_C5]
								OPEN_SEQUENCE_TASK sequence_C5
									TASK_LEAVE_CAR -1 ambulance_C5[pointer_C5]
								  	TASK_STAY_IN_SAME_PLACE -1 TRUE 
								  	TASK_KILL_CHAR_ON_FOOT -1 scplayer
								CLOSE_SEQUENCE_TASK sequence_C5	
								PERFORM_SEQUENCE_TASK passenger_C5[pointer_C5] sequence_C5
								CLEAR_SEQUENCE_TASK sequence_C5
								//ADD_BLIP_FOR_CHAR driver_C5[pointer_C5] mafia_blip_C5[1]
								//mafia_status_C5[1] = 0
							ELSE
								//mafia_status_C5[1] = -1
							ENDIF

							IF player_hijacked_ambulance_C5 = 0 
								REMOVE_BLIP ambulance_blip_C5[pointer_C5]
								ADD_BLIP_FOR_CAR ambulance_C5[pointer_C5] ambulance_blip_C5[pointer_C5]  
								SET_BLIP_AS_FRIENDLY ambulance_blip_C5[pointer_C5] TRUE
								LOCK_CAR_DOORS ambulance_C5[pointer_C5] CARLOCK_UNLOCKED
							 
								IF NOT IS_CHAR_DEAD driver_C5[pointer_C5]
								OR NOT IS_CHAR_DEAD passenger_C5[pointer_C5]
									PRINT_NOW CAS5_11 8000 1
								ELSE
									PRINT_NOW CAS5_12 8000 1
								ENDIF
							ELSE
								REMOVE_BLIP ambulance_blip_C5[pointer_C5] 
							ENDIF

							SET_CAR_DENSITY_MULTIPLIER 0.6	
							SET_PED_DENSITY_MULTIPLIER 0.6
							
							UNMARK_ALL_ROAD_NODES_AS_DONT_WANDER
						
							backup_radius_C5 = 100.0						
							backup_ram_status_C5 = 0 
							backup_status_C5[0] = -1
							backup_status_C5[1] = -1
							backup_Y_offset_C5[0] = -10.0
							backup_Y_offset_C5[1] = -10.0

							text_status_C5 = 0

							SET_CAR_HEALTH ambulance_C5[pointer_C5] 1250 
							
							GOTO mission_casino5_MAIN_take_johnny_home
						
						ENDIF
						
					ELSE
						fail_text_flag_C5 = 1
						$fail_text_C5 = CAS5_17
						GOTO mission_casino5_FAILED						
					ENDIF	

				BREAK

			ENDSWITCH

		ENDIF

		pointer_C5 ++

	ENDWHILE

	GOSUB mission_casino5_SUB_ambulance_world_collision

GOTO mission_casino5_MAIN_find_ambulance



LVAR_INT ambulance_collision_status_C5[3]
mission_casino5_SUB_ambulance_world_collision:

	IF TIMERB > 500
		
		GET_CHAR_COORDINATES scplayer player_x player_y player_z

		pointer_C5 = 0

		WHILE pointer_C5 < 3
			IF NOT IS_CAR_DEAD ambulance_C5[pointer_C5]
				IF ambulance_status_C5[pointer_C5] = 0
					GET_CAR_COORDINATES ambulance_C5[pointer_C5] x y z
					GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance
					SWITCH ambulance_collision_status_C5[pointer_C5]
						CASE 0
							IF distance < 120.0
								SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[pointer_C5] TRUE
								ambulance_collision_status_C5[pointer_C5] ++
							ENDIF	
						BREAK
						CASE 1
							IF distance > 140.0
								SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[pointer_C5] FALSE
								ambulance_collision_status_C5[pointer_C5] --
							ENDIF	
						BREAK
					ENDSWITCH 
				ELSE
					IF ambulance_collision_status_C5[pointer_C5] = 0
						SET_LOAD_COLLISION_FOR_CAR_FLAG ambulance_C5[pointer_C5] TRUE
						ambulance_collision_status_C5[pointer_C5] = 1
					ENDIF 
				ENDIF
			ENDIF

			pointer_C5 ++

		ENDWHILE

		TIMERB = 0
	ENDIF

RETURN

 
// put mafia in an ambulance and make it goto one of the two motels
// make other ambulance goto the hospital

LVAR_INT ambulance_health_C5
mission_casino5_SUB_setup_mafia_ambulance:

	// chose a random ambulance ot hold the mafia
	GENERATE_RANDOM_INT_IN_RANGE 0 2 random_C5		
	SWITCH pointer_C5
		CASE 0
			IF random_C5 = 0
				mafia_ID_C5 = 1
			ELSE
				mafia_ID_C5 = 2
			ENDIF	
		BREAK
		CASE 1
			IF random_C5 = 0
				mafia_ID_C5 = 0
			ELSE
				mafia_ID_C5 = 2
			ENDIF			
		BREAK
		CASE 2
			IF random_C5 = 0
				mafia_ID_C5 = 0
			ELSE
				mafia_ID_C5 = 1
			ENDIF	
		BREAK
	ENDSWITCH
	
			
	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
	 
		// mafia ambulance
		ambulance_health_C5 = 5000
		SET_CAR_HEALTH ambulance_C5[mafia_ID_C5] ambulance_health_C5
		SET_CAN_BURST_CAR_TYRES ambulance_C5[mafia_ID_C5] FALSE

		// mafia driver
		IF NOT IS_CHAR_DEAD driver_C5[mafia_ID_C5]
			CLEAR_CHAR_TASKS driver_C5[mafia_ID_C5]
			DELETE_CHAR driver_C5[mafia_ID_C5]
		ENDIF
		CREATE_CHAR_INSIDE_CAR ambulance_C5[mafia_ID_C5] PEDTYPE_MISSION1 VMAFF1 driver_C5[mafia_ID_C5]
		SET_CHAR_CANT_BE_DRAGGED_OUT driver_C5[mafia_ID_C5] TRUE
		GIVE_WEAPON_TO_CHAR driver_C5[mafia_ID_C5] WEAPONTYPE_MP5 30000
		SET_CURRENT_CHAR_WEAPON driver_C5[mafia_ID_C5] WEAPONTYPE_MP5
		SET_CHAR_DECISION_MAKER driver_C5[mafia_ID_C5] tough_dm_C5

		// mafia passenger
		IF NOT IS_CHAR_DEAD passenger_C5[mafia_ID_C5]
			CLEAR_CHAR_TASKS passenger_C5[mafia_ID_C5]
			DELETE_CHAR passenger_C5[mafia_ID_C5]
		ENDIF
		CREATE_CHAR_AS_PASSENGER ambulance_C5[mafia_ID_C5] PEDTYPE_MISSION1 VMAFF2 0 passenger_C5[mafia_ID_C5]
		SET_CHAR_CANT_BE_DRAGGED_OUT passenger_C5[mafia_ID_C5] TRUE
		GIVE_WEAPON_TO_CHAR passenger_C5[mafia_ID_C5] WEAPONTYPE_MP5 30000
		SET_CURRENT_CHAR_WEAPON passenger_C5[mafia_ID_C5] WEAPONTYPE_MP5
		SET_CHAR_DECISION_MAKER passenger_C5[mafia_ID_C5] tough_dm_C5

		// johnny sindacco
		CREATE_CHAR_AS_PASSENGER ambulance_C5[mafia_ID_C5] PEDTYPE_SPECIAL SPECIAL01 1 johnny_C5
		SET_CHAR_STAY_IN_CAR_WHEN_JACKED johnny_C5 TRUE
		SET_CHAR_FORCE_DIE_IN_CAR johnny_C5 TRUE
		SET_CHAR_DECISION_MAKER johnny_C5 empty_dm_C5
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR johnny_C5 FALSE
		
		TIMERA = 0
		alert_status_C5 = 0

		ambulance_status_C5[mafia_ID_C5] = 4

	ENDIF

RETURN
	

			
LVAR_INT backup_car_C5[2] backup_driver_C5[2] backup_status_C5[2] backup_timer_C5[2] backup_pointer_C5 backup_ram_status_C5
LVAR_FLOAT backup_Y_offset_C5[2] backup_radius_C5 

mission_casino5_SUB_backup_controller:

	backup_pointer_C5 = 0
	
	WHILE backup_pointer_C5 < 2

		IF NOT IS_CHAR_DEAD backup_driver_C5[backup_pointer_C5]
		AND NOT IS_CAR_DEAD backup_car_C5[backup_pointer_C5]

			SWITCH backup_status_C5[backup_pointer_C5]
			
				CASE 1
					IF backup_ram_status_C5 = 0

						GOSUB mission_casino5_SUB_check_backup_distance						 

					ELSE

						GET_CAR_COORDINATES backup_car_C5[backup_pointer_C5] x y z
						GET_DISTANCE_BETWEEN_COORDS_2D 1074.4608 2392.2271 x y z  
						GET_DISTANCE_BETWEEN_COORDS_2D 1010.6731 1487.1841 x y distance
						
						CLEAR_CHAR_TASKS backup_driver_C5[backup_pointer_C5]

						IF z < distance
							TASK_CAR_DRIVE_TO_COORD backup_driver_C5[backup_pointer_C5] backup_car_C5[backup_pointer_C5] 1074.4608 2392.2271 9.6796 25.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS 
						ELSE
							TASK_CAR_DRIVE_TO_COORD backup_driver_C5[backup_pointer_C5] backup_car_C5[backup_pointer_C5] 1010.6731 1487.1841 9.6797 25.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
						ENDIF
						backup_status_C5[backup_pointer_C5] = 2
					ENDIF
				BREAK

				CASE 2
					IF backup_ram_status_C5 = 0 
						backup_status_C5[backup_pointer_C5] = 1
						CLEAR_CHAR_TASKS backup_driver_C5[backup_pointer_C5]
						TASK_CAR_MISSION backup_driver_C5[backup_pointer_C5] backup_car_C5[backup_pointer_C5] ambulance_C5[mafia_ID_C5] MISSION_RAMPLAYER_CLOSE 32.0 DRIVINGMODE_AVOIDCARS
					ENDIF
				BREAK

			ENDSWITCH
			 
		ELSE

			SWITCH backup_status_C5[backup_pointer_C5]
			
				DEFAULT
					MARK_CHAR_AS_NO_LONGER_NEEDED backup_driver_C5[backup_pointer_C5]
					MARK_CAR_AS_NO_LONGER_NEEDED backup_car_C5[backup_pointer_C5] 
					backup_status_C5[backup_pointer_C5] = -1
				BREAK 
			   
				CASE -1 // cars are dead/not created dont do anything

				BREAK

				CASE 0 // respawn mode
					IF backup_timer_C5[backup_pointer_C5] > 9000
						
						IF HAS_MODEL_LOADED SENTINEL

							GOSUB mission_casino5_SUB_find_hidden_node
							IF found_hidden_node_C5 = 1

							  	CREATE_CAR SENTINEL node_X_C5 node_Y_C5 node_Z_C5 backup_car_C5[backup_pointer_C5]
								SET_CAR_HEADING backup_car_C5[backup_pointer_C5] node_heading_C5 
								LOCK_CAR_DOORS backup_car_C5[backup_pointer_C5] CARLOCK_LOCKOUT_PLAYER_ONLY
								SET_CAR_CAN_BE_VISIBLY_DAMAGED backup_car_C5[backup_pointer_C5] FALSE
								SET_CAR_HEALTH backup_car_C5[backup_pointer_C5] 5000 

								CREATE_CHAR_INSIDE_CAR backup_car_C5[backup_pointer_C5] PEDTYPE_MISSION1 VMAFF2 backup_driver_C5[backup_pointer_C5]  

								GIVE_WEAPON_TO_CHAR backup_driver_C5[backup_pointer_C5] WEAPONTYPE_MP5 30000
								SET_CURRENT_CHAR_WEAPON backup_driver_C5[backup_pointer_C5] WEAPONTYPE_MP5
								SET_CHAR_DECISION_MAKER backup_driver_C5[backup_pointer_C5] empty_dm_C5
		
								SET_CHAR_CANT_BE_DRAGGED_OUT backup_driver_C5[backup_pointer_C5] TRUE

								TASK_CAR_MISSION backup_driver_C5[backup_pointer_C5] backup_car_C5[backup_pointer_C5] ambulance_C5[mafia_ID_C5] MISSION_RAMPLAYER_CLOSE 32.0 DRIVINGMODE_AVOIDCARS

								backup_status_C5[backup_pointer_C5] = 1
							ENDIF
						ENDIF
					ENDIF
			 		
			 	BREAK

			ENDSWITCH

		ENDIF

		backup_pointer_C5 ++
		
	ENDWHILE 	

RETURN


				
// ########################################################################################################################################
// ##
// ##	Drive back to meat factory
// ##
// ########################################################################################################################################

LVAR_INT player_in_ambulance_C5 mafia_audio_C5 under_attack_text_C5			
mission_casino5_MAIN_take_johnny_home:

	WAIT 0

	IF NOT IS_CHAR_DEAD johnny_C5

		IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5] // no need to fail mission if this is destroyed as johnny is inside

			IF IS_CHAR_IN_CAR scplayer ambulance_C5[mafia_ID_C5]

				IF player_in_ambulance_C5 = 0
					REMOVE_BLIP ambulance_blip_C5[mafia_ID_C5]
					ADD_BLIP_FOR_COORD 981.4241 2132.4692 9.8203 goto_blip_C5

					IF text_status_C5 = 0
						PRINT_NOW CAS5_13 8000 1
						
						backup_status_C5[0] = 0
						backup_status_C5[1] = 0 
						backup_timer_C5[0] = 0
						backup_timer_C5[1] = 0
						text_status_C5 = 1

						MARK_CAR_AS_NO_LONGER_NEEDED patriot_C5
						MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT
					ELSE
						PRINT_NOW CAS5_14 8000 1	
					ENDIF
					player_in_ambulance_C5 = 1
				ENDIF

				IF LOCATE_CHAR_IN_CAR_2D scplayer 981.4241 2132.4692 backup_radius_C5 backup_radius_C5 FALSE
					
					IF LOCATE_CHAR_IN_CAR_3D scplayer 981.4241 2132.4692 9.8203	4.0 4.0 4.0 TRUE
						GOTO mission_casino5_CUT_at_meat_factory
					ENDIF

					IF backup_ram_status_C5 = 0
						backup_radius_C5 = 120.0
						backup_ram_status_C5 = 1 // backoff
					ENDIF
				ELSE
					IF backup_ram_status_C5 = 1
						backup_radius_C5 = 100.0
						backup_ram_status_C5 = 0 // attack
					ENDIF
				ENDIF

				// johnny audio
				IF under_attack_text_C5 = 1
					GOSUB mission_casino5_SUB_dialogue
				ENDIF

			ELSE
				IF player_in_ambulance_C5 = 1
					REMOVE_BLIP goto_blip_C5
					ADD_BLIP_FOR_CAR ambulance_C5[mafia_ID_C5] ambulance_blip_C5[mafia_ID_C5] 
					SET_BLIP_AS_FRIENDLY ambulance_blip_C5[mafia_ID_C5] TRUE 
					PRINT_NOW CAS5_15 8000 1
					player_in_ambulance_C5 = 0
				ENDIF

				// mafia getting out of ambulance audi
				IF mafia_audio_C5 < 2
					IF mafia_audio_C5 = 0
						IF NOT IS_CHAR_DEAD driver_C5[mafia_ID_C5]
							IF NOT IS_CHAR_IN_ANY_CAR driver_C5[mafia_ID_C5]
								
								load_sample = SOUND_CAS5_EA
								$load_text = &CAS5_EA
								START_NEW_SCRIPT audio_load_and_play 2 100 driver_C5[mafia_ID_C5] //0.0 0.0 0.0

								mafia_audio_C5 ++
							ENDIF
						ELSE
							mafia_audio_C5 ++
						ENDIF 
					ELSE
						IF NOT IS_CHAR_DEAD passenger_C5[mafia_ID_C5]
							IF NOT IS_CHAR_IN_ANY_CAR passenger_C5[mafia_ID_C5]
								IF SLOT_status[preload_slot] = -3
									SLOT_status[preload_slot] = -4
									
									load_sample = SOUND_CAS5_EB
									$load_text = &CAS5_EB
									START_NEW_SCRIPT audio_load_and_play 2 100 passenger_C5[mafia_ID_C5] //0.0 0.0 0.0

									mafia_audio_C5 ++
								ENDIF
							ENDIF
						ELSE
							mafia_audio_C5 ++
						ENDIF 
					ENDIF
				ENDIF

			ENDIF

			// backup text
			IF under_attack_text_C5 = 0
				IF NOT IS_CAR_DEAD backup_car_C5[0]
					IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer backup_car_C5[0] 10.0 10.0 FALSE
						PRINT_NOW CAS5_16 8000 1
						under_attack_text_C5 = 1
					ENDIF
				ENDIF
				IF under_attack_text_C5 = 0
					IF NOT IS_CAR_DEAD backup_car_C5[1]
						IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer backup_car_C5[1] 10.0 10.0 FALSE
							PRINT_NOW CAS5_16 8000 1
							under_attack_text_C5 = 1
						ENDIF
					ENDIF					
				ENDIF
				IF under_attack_text_C5 = 1
					TIMERB = 0
					audio_status_C5 = 7
				ENDIF 
			ENDIF


			// timers
			GET_GAME_TIMER this_frame_time_C5
			time_diff_C5 = this_frame_time_C5 - last_frame_time_C5 
			last_frame_time_C5 = this_frame_time_C5
			
			backup_timer_C5[0] += time_diff_C5
			backup_timer_C5[1] += time_diff_C5	
			GOSUB mission_casino5_SUB_backup_controller

		ENDIF

	ELSE
		fail_text_flag_C5 = 1
		$fail_text_C5 = CAS5_17
		GOTO mission_casino5_FAILED
	ENDIF 



GOTO mission_casino5_MAIN_take_johnny_home 			
			

mission_casino5_SUB_check_backup_distance:
						
	IF backup_timer_C5[backup_pointer_C5] > 500
		IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer backup_car_C5[backup_pointer_C5] 75.0 75.0 FALSE
		AND NOT IS_CAR_ON_SCREEN backup_car_C5[backup_pointer_C5]
			GOSUB mission_casino5_SUB_find_hidden_node
			IF found_hidden_node_C5 = 1
				SET_CAR_COORDINATES backup_car_C5[backup_pointer_C5] node_X_C5 node_Y_C5 node_Z_C5 
				SET_CAR_HEADING backup_car_C5[backup_pointer_C5] node_heading_C5
				SET_CAR_FORWARD_SPEED backup_car_C5[backup_pointer_C5] 32.0  
			ENDIF
		ENDIF
		backup_timer_C5[backup_pointer_C5] = 0
	ENDIF

RETURN
						 



LVAR_INT node_number_C5 found_hidden_node_C5
LVAR_FLOAT p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5
LVAR_FLOAT node_X_C5 node_Y_C5 node_Z_C5 node_heading_C5

mission_casino5_SUB_find_hidden_node:

	node_number_C5 = 1
	found_hidden_node_C5 = 0	
	
	WHILE node_number_C5 < 10
	AND found_hidden_node_C5 = 0
		
		// node behind player
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 backup_Y_offset_C5[backup_pointer_C5] 0.0 p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 
		GET_NTH_CLOSEST_CAR_NODE_WITH_HEADING p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 node_number_C5 node_X_C5 node_Y_C5 node_Z_C5 node_heading_C5
		IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY node_X_C5 node_Y_C5 node_Z_C5 5.0 5.0 5.0
			IF NOT IS_POINT_ON_SCREEN node_X_C5 node_Y_C5 node_Z_C5 5.0
				CLEAR_AREA node_X_C5 node_Y_C5 node_Z_C5 5.0 TRUE
				found_hidden_node_C5 = 1		
			ENDIF
		ENDIF

//		IF found_hidden_node_C5 = 0
//			// node left player
//			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -20.0 -5.0 0.0 p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 
//			GET_NTH_CLOSEST_CAR_NODE_WITH_HEADING p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 node_number_C5 node_X_C5 node_Y_C5 node_Z_C5 node_heading_C5
//			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY node_X_C5 node_Y_C5 node_Z_C5 5.0 5.0 5.0
//				IF NOT IS_POINT_ON_SCREEN node_X_C5 node_Y_C5 node_Z_C5 5.0
//					CLEAR_AREA node_X_C5 node_Y_C5 node_Z_C5 5.0 TRUE
//					found_hidden_node_C5 = 1		
//				ENDIF
//			ENDIF
//		ENDIF		
//		
//		IF found_hidden_node_C5 = 0
//			// node right player
//			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 20.0 -5.0 0.0 p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 
//			GET_NTH_CLOSEST_CAR_NODE_WITH_HEADING p_offset_X_C5 p_offset_Y_C5 p_offset_Z_C5 node_number_C5 node_X_C5 node_Y_C5 node_Z_C5 node_heading_C5
//			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY node_X_C5 node_Y_C5 node_Z_C5 5.0 5.0 5.0
//				IF NOT IS_POINT_ON_SCREEN node_X_C5 node_Y_C5 node_Z_C5 5.0
//					CLEAR_AREA node_X_C5 node_Y_C5 node_Z_C5 5.0 TRUE
//					found_hidden_node_C5 = 1		
//				ENDIF
//			ENDIF
//		ENDIF
				
		node_number_C5 ++

	ENDWHILE

RETURN



// ##############################
// ## AUDIO

LVAR_INT audio_pointer_C5 audio_status_C5 audio_sound_label_C5[16]
LVAR_TEXT_LABEL $audio_text_label_C5[16]
 
mission_casino5_SUB_dialogue:

	SWITCH audio_status_C5

		CASE 0
			IF TIMERB > 8500
				audio_status_C5 ++
			ENDIF	
		BREAK

		// voice over at start
		CASE 1
			IF audio_pointer_C5 < 2
				load_sample = audio_sound_label_C5[audio_pointer_C5]
				$load_text = $audio_text_label_C5[audio_pointer_C5]
				START_NEW_SCRIPT audio_load_and_play 0 100
				audio_pointer_C5 ++
				audio_status_C5 ++
			ELSE
				audio_status_C5 = 3
				BREAK
			ENDIF
		CASE 2
			IF SLOT_status[preload_slot] = -3
				audio_status_C5 = 1
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK		

		// hospital reception
		CASE 4
			IF audio_pointer_C5 < 7
				load_sample = audio_sound_label_C5[audio_pointer_C5]
				$load_text = $audio_text_label_C5[audio_pointer_C5]
				START_NEW_SCRIPT audio_load_and_play 0 100
				audio_pointer_C5 ++
				audio_status_C5 ++
			ELSE
				audio_status_C5 = 6
				BREAK
			ENDIF
		CASE 5
			IF SLOT_status[preload_slot] = -3
				audio_status_C5 = 4
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		// johnny moaning
		CASE 7
			IF TIMERB > 8500
				GENERATE_RANDOM_INT_IN_RANGE 7 14 audio_pointer_C5 
				audio_status_C5 ++
			ENDIF
		BREAK

		CASE 8
			IF NOT IS_CAR_DEAD backup_car_C5[0]  
				IF HAS_CAR_BEEN_DAMAGED_BY_CAR ambulance_C5[mafia_ID_C5] backup_car_C5[0]
					audio_status_C5 = 9	
				ENDIF
				CLEAR_CAR_LAST_DAMAGE_ENTITY backup_car_C5[0]
			ENDIF
			IF NOT IS_CAR_DEAD backup_car_C5[1]  
				IF NOT audio_status_C5 = 9
					IF HAS_CAR_BEEN_DAMAGED_BY_CAR ambulance_C5[mafia_ID_C5] backup_car_C5[1]
						audio_status_C5 = 9	
					ENDIF
				ENDIF
				CLEAR_CAR_LAST_DAMAGE_ENTITY backup_car_C5[1]
			ENDIF
			CLEAR_CAR_LAST_DAMAGE_ENTITY ambulance_C5[mafia_ID_C5]  
		BREAK

		CASE 9
			load_sample = audio_sound_label_C5[audio_pointer_C5]
			$load_text = $audio_text_label_C5[audio_pointer_C5]
			START_NEW_SCRIPT audio_load_and_play 2 100 johnny_C5
			audio_status_C5 = 10

			audio_pointer_C5 ++
			IF audio_pointer_C5 = 14
				audio_pointer_C5 = 7
			ENDIF
			TIMERB = 0

		CASE 10
			IF TIMERB > 10000
				audio_status_C5 = 8
			ENDIF
		BREAK

	ENDSWITCH

RETURN



LVAR_INT meat_mafia_C5[2]

mission_casino5_CUT_at_meat_factory:

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CLEAR_PRINTS
	CLEAR_AREA 979.7058 2137.2466 9.8203 200.0 TRUE

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
		SET_CAR_HEALTH ambulance_C5[mafia_ID_C5] 3000
	ENDIF
	 
	REQUEST_MODEL VMAFF3
	REQUEST_MODEL VMAFF4
	REQUEST_MODEL CJ_WHEELCHAIR1

	WHILE NOT HAS_MODEL_LOADED VMAFF3
	OR NOT HAS_MODEL_LOADED VMAFF4
	OR NOT HAS_MODEL_LOADED CJ_WHEELCHAIR1
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
		SET_CAR_COORDINATES ambulance_C5[mafia_ID_C5] 979.7058 2137.2466 9.8203
		SET_CAR_HEADING ambulance_C5[mafia_ID_C5] 237.8897
		
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 998.5278 2118.5889 9.8203		
		//WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer ambulance_C5[mafia_ID_C5] 0
		CREATE_CHAR_INSIDE_CAR ambulance_C5[mafia_ID_C5] PEDTYPE_MISSION1 VMAFF3 meat_mafia_C5[0]
		SET_CHAR_DECISION_MAKER meat_mafia_C5[0] empty_dm_C5
	ENDIF 

	SET_FIXED_CAMERA_POSITION 972.8329 2141.3342 11.0496 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 973.6994 2140.8389 11.1103 JUMP_CUT

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
	AND NOT IS_CHAR_DEAD meat_mafia_C5[0]	
		OPEN_SEQUENCE_TASK sequence_C5
			TASK_CAR_TEMP_ACTION -1 ambulance_C5[mafia_ID_C5] TEMPACT_REVERSE_STRAIGHT 500	
			TASK_CAR_TEMP_ACTION -1 ambulance_C5[mafia_ID_C5] TEMPACT_HANDBRAKESTRAIGHT 300
			TASK_STAND_STILL -1 8000
		CLOSE_SEQUENCE_TASK sequence_C5	
		PERFORM_SEQUENCE_TASK meat_mafia_C5[0] sequence_C5
		CLEAR_SEQUENCE_TASK sequence_C5
	ENDIF

	cutscene_flag_C5 = 0
	WHILE cutscene_flag_C5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD meat_mafia_C5[0]
			GET_SEQUENCE_PROGRESS meat_mafia_C5[0] sequence_progress_C5
			IF sequence_progress_C5 > 0
				cutscene_flag_C5 = 1
			ENDIF
		ENDIF	 
	ENDWHILE

	IF NOT IS_CHAR_DEAD meat_mafia_C5[0]
	AND NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
		WARP_CHAR_FROM_CAR_TO_COORD meat_mafia_C5[0] 971.7789 2137.2883 9.8203
		SET_CHAR_HEADING meat_mafia_C5[0] 316.6316 
		CLEAR_CHAR_TASKS_IMMEDIATELY meat_mafia_C5[0] 
		
		OPEN_SEQUENCE_TASK sequence_C5
			TASK_GO_STRAIGHT_TO_COORD -1 974.2065 2140.1077 9.8203 PEDMOVE_WALK -2	
			TASK_LOOK_AT_VEHICLE -1 ambulance_C5[mafia_ID_C5] 8000
			TASK_STAND_STILL -1 8000
		CLOSE_SEQUENCE_TASK sequence_C5	
		PERFORM_SEQUENCE_TASK meat_mafia_C5[0] sequence_C5
		CLEAR_SEQUENCE_TASK sequence_C5

		//WARP_CHAR_FROM_CAR_TO_COORD scplayer 0.0 0.0 0.0		
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_INTO_CAR scplayer ambulance_C5[mafia_ID_C5]
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 975.8311 2144.9590 9.8203 meat_mafia_C5[1]
		SET_CHAR_HEADING meat_mafia_C5[1] 162.2693
		SET_CHAR_DECISION_MAKER meat_mafia_C5[1] empty_dm_C5
		  		
		OPEN_SEQUENCE_TASK sequence_C5
			TASK_GO_STRAIGHT_TO_COORD -1 975.0208 2141.3657 9.8203 PEDMOVE_WALK -2	
			TASK_LOOK_AT_CHAR -1 meat_mafia_C5[1] 8000	
			TASK_STAND_STILL -1 8000
		CLOSE_SEQUENCE_TASK sequence_C5	
		PERFORM_SEQUENCE_TASK meat_mafia_C5[1] sequence_C5
		CLEAR_SEQUENCE_TASK sequence_C5
	ENDIF
	
	  
	cutscene_flag_C5 = 0
	WHILE cutscene_flag_C5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD meat_mafia_C5[0]
			GET_SEQUENCE_PROGRESS meat_mafia_C5[0] sequence_progress_C5
			IF sequence_progress_C5 > 0
				cutscene_flag_C5 = 1
			ENDIF
		ENDIF	 
	ENDWHILE

	load_sample = SOUND_CAS5_DA
	$load_text = &CAS5_DA
	START_NEW_SCRIPT audio_load_and_play 2 200 meat_mafia_C5[0] 

	cutscene_flag_C5 = 0
	WHILE cutscene_flag_C5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD meat_mafia_C5[1]
			GET_SEQUENCE_PROGRESS meat_mafia_C5[1] sequence_progress_C5
			IF sequence_progress_C5 > 0
				cutscene_flag_C5 = 1
			ENDIF
		ENDIF	 
	ENDWHILE

	WHILE NOT SLOT_status[preload_slot] = -3
		WAIT 0	
	ENDWHILE

	load_sample = SOUND_CAS5_DB
	$load_text = &CAS5_DB
	START_NEW_SCRIPT audio_load_and_play 2 200 meat_mafia_C5[1]

	TIMERA = 0
	WHILE TIMERA < 4500
		WAIT 0
	ENDWHILE

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	LVAR_INT wheelchair_C5
	CREATE_OBJECT CJ_WHEELCHAIR1 971.5 2140.5 9.8203 wheelchair_C5
	SET_OBJECT_HEADING wheelchair_C5 180.0
	IF NOT IS_CHAR_DEAD johnny_C5
		WARP_CHAR_FROM_CAR_TO_COORD johnny_C5 0.0 0.0 0.0
		ATTACH_CHAR_TO_OBJECT johnny_C5 wheelchair_C5 0.0 -0.575 0.525 FACING_BACK 0.0 WEAPONTYPE_UNARMED
		TASK_PLAY_ANIM johnny_C5 SEAT_down PED 9.0 FALSE FALSE FALSE TRUE -1
	ENDIF

	IF NOT IS_CHAR_DEAD meat_mafia_C5[0]
		SET_CHAR_COORDINATES meat_mafia_C5[0] 972.5 2140.5 9.8203 
		SET_CHAR_HEADING meat_mafia_C5[0] 0.0
	ENDIF 

	IF NOT IS_CHAR_DEAD meat_mafia_C5[1]
		SET_CHAR_COORDINATES meat_mafia_C5[1] 971.5 2139.5 9.8203 
		SET_CHAR_HEADING meat_mafia_C5[1] 0.0
	ENDIF

	cutscene_flag_C5 = 0
	WHILE cutscene_flag_C5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD johnny_C5
			IF IS_CHAR_PLAYING_ANIM johnny_C5 SEAT_down
				SET_CHAR_ANIM_CURRENT_TIME johnny_C5 SEAT_down 1.0 
				cutscene_flag_C5 = 1
			ENDIF 
		ENDIF	 
	ENDWHILE
							   
	SET_FIXED_CAMERA_POSITION 971.4178 2146.1355 14.0976 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 971.5663 2145.2749 13.6106 JUMP_CUT

	TIMERA = 0
	WHILE TIMERA < 200
		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD meat_mafia_C5[0]
		CLEAR_CHAR_TASKS meat_mafia_C5[0]
	ENDIF

	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	IF NOT IS_CHAR_DEAD meat_mafia_C5[0] 
		TASK_GO_STRAIGHT_TO_COORD meat_mafia_C5[0] 972.8889 2148.0393 9.8203 PEDMOVE_WALK -2
	ENDIF

	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5] 
		TASK_CAR_DRIVE_TO_COORD scplayer ambulance_C5[mafia_ID_C5] 991.3741 2136.3118 9.8203 5.0 MODE_NORMAL 0 DRIVINGMODE_STOPFORCARS
	ENDIF

	TIMERA = 0
	WHILE TIMERA < 1700
		WAIT 0
	ENDWHILE
	
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD ambulance_C5[mafia_ID_C5]
		SET_CAR_FORWARD_SPEED ambulance_C5[mafia_ID_C5] 0.0
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 0.0 0.0 0.0
		WARP_CHAR_INTO_CAR scplayer ambulance_C5[mafia_ID_C5] 
	ENDIF

	DETACH_CHAR_FROM_CAR johnny_C5
	DELETE_CHAR johnny_C5
	DELETE_CHAR meat_mafia_C5[0]
	DELETE_CHAR meat_mafia_C5[1]
	DELETE_OBJECT wheelchair_C5 

	CLEAR_CHAR_TASKS scplayer
	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
		
	SET_PLAYER_CONTROL player1 ON  
	SWITCH_WIDESCREEN ON

	TIMERA = 0
	WHILE TIMERA < 200
		WAIT 0
	ENDWHILE
	
	DO_FADE 0 FADE_IN

// ###########################
// ##
// ## END OF MISSION SCRIPTS
// ##
// ###########################


mission_casino5_PASSED:
	
	flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( CASINO5 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 5000//amount of cash
	AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

	
	// stuff for craig
	REMOVE_BLIP vcrash_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT vcrashX vcrashY vcrashZ crash_blip_icon vcrash_contact_blip 
	START_NEW_SCRIPT vcrash_mission_loop

RETURN



mission_casino5_FAILED:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	IF fail_text_flag_C5 = 1
		PRINT_NOW $fail_text_C5 5000 1
	ENDIF

RETURN

mission_casino5_CLEANUP:
	
	START_NEW_SCRIPT terminate_audio_controller

	UNMARK_ALL_ROAD_NODES_AS_DONT_WANDER

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	ENDIF

	SET_CAR_DENSITY_MULTIPLIER 1.0	
	SET_PED_DENSITY_MULTIPLIER 1.0

	REMOVE_BLIP goto_blip_C5
	REMOVE_BLIP ambulance_blip_C5[0]
	REMOVE_BLIP ambulance_blip_C5[1]
	REMOVE_BLIP ambulance_blip_C5[2]


	MARK_CHAR_AS_NO_LONGER_NEEDED driver_C5[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED driver_C5[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED driver_C5[2]

	MARK_CHAR_AS_NO_LONGER_NEEDED passenger_C5[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED passenger_C5[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED passenger_C5[2]

	MARK_CHAR_AS_NO_LONGER_NEEDED johnny_C5

	MARK_CHAR_AS_NO_LONGER_NEEDED backup_driver_C5[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED backup_driver_C5[1]
//
//	MARK_CHAR_AS_NO_LONGER_NEEDED meat_mafia_C5[0]
//	MARK_CHAR_AS_NO_LONGER_NEEDED meat_mafia_C5[1]


	MARK_CAR_AS_NO_LONGER_NEEDED patriot_C5

	MARK_CAR_AS_NO_LONGER_NEEDED ambulance_C5[0]
	MARK_CAR_AS_NO_LONGER_NEEDED ambulance_C5[1]
	MARK_CAR_AS_NO_LONGER_NEEDED ambulance_C5[2]

	MARK_CAR_AS_NO_LONGER_NEEDED backup_car_C5[0]		
	MARK_CAR_AS_NO_LONGER_NEEDED backup_car_C5[1]
			

	DONT_SUPPRESS_CAR_MODEL PATRIOT
	
	UNLOAD_SPECIAL_CHARACTER 1

	MARK_MODEL_AS_NO_LONGER_NEEDED LVEMT1

	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4

	MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT
	MARK_MODEL_AS_NO_LONGER_NEEDED AMBULAN 
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL

	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG

	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_WHEELCHAIR1
	REMOVE_ANIMATION GANGS

	flag_player_on_mission = 0
	
	GET_GAME_TIMER timer_mobile_start

	MISSION_HAS_FINISHED
RETURN

}
