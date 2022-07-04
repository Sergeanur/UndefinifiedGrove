MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ****************************************** MUSIC 3 **************************************
// ********************************* TAKE OUT DOC G'S MANAGER ******************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME music3
// Mission start stuff
GOSUB mission_start_music3
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_music3_failed
ENDIF
GOSUB mission_cleanup_music3
MISSION_END
{
// Variables for mission
//people
LVAR_INT crowd[21] m3_cop[9] wc1 mc1 m3_limo[3] m3_limo_driver[3] m3_dgcar1[3] m3_dgcar1_driver[3]
LVAR_INT m3_east_barrier1 m3_east_barrier2 m3_south_east_barrier1 m3_south_east_barrier2 m3_south_west_barrier1 m3_south_west_barrier2
LVAR_INT m3_west_barrier1 m3_west_barrier2 m3_north_west_barrier1 m3_north_west_barrier2 m3_north_east_barrier1 m3_north_east_barrier2
LVAR_INT right_barrier1 right_barrier2 right_barrier3 left_barrier1 left_barrier2 left_barrier3
LVAR_INT front_left_barrier1 front_left_barrier2 front_left_barrier3 front_right_barrier1 front_right_barrier2 front_right_barrier3
LVAR_INT docg_manager docg_hoochie 
LVAR_INT m3_goons[7]
LVAR_INT m3_interviewer 
//LVAR_INT m3_checkpoint
LVAR_INT m3_dummy_target

//blips
LVAR_INT m3_docg_manager_blip m3_goon_blips[8] 
 

//flags
LVAR_INT m3_goals m3_control_flag m3_skip_cutscene_flag m3_deathcheck_flag m3_cutscene_goals_flag      
LVAR_INT m3_flash_flag1 m3_flash_flag2 
LVAR_INT m3_shit_hit_fan  
LVAR_INT has_crowd_been_created crowd_flag_death_check creating_crowd_flag m3_crowd_flag m3_crowd_check 
LVAR_INT m3_char_select[5] m3_cs
LVAR_INT cop_death_check 
LVAR_INT m3_car_check_flag 
LVAR_INT m3_gametimer_difference m3_gametimer_ended m3_gametimer_started m3_time_to_fail m3_stray_flag m3_number_to_print
LVAR_INT m3_hours 
LVAR_INT m3_how_many_cars_flag
LVAR_INT m3_context

LVAR_INT m3_random_number
LVAR_INT m3_baddy_control_flag[2]
LVAR_INT m3_baddy_in_range_check
LVAR_INT m3_seq_progress
									   
LVAR_FLOAT m3_car_heading
LVAR_FLOAT m3_car_speed 
LVAR_INT m3_paynspray_text_duration
LVAR_INT m3_paynspray_text_flag
LVAR_INT m3_initial_direction_text_flag
LVAR_INT m3_warning_text
LVAR_INT m3_help_flag 
LVAR_INT m3_removing_crowd
LVAR_INT m3_removing_cop_flag
LVAR_INT m3_removing_crowd_flag
LVAR_INT m3_phone_cutscene

LVAR_INT m3_no_plates_flag m3_no_plates

LVAR_INT m3_random_flash_flag1
LVAR_INT m3_random_flash_flag2
LVAR_INT m3_camflash1
LVAR_INT m3_camflash2

//speech
LVAR_INT m3_speech_goals m3_speech_flag m3_speech_control_flag m3_random_last_label 
LVAR_TEXT_LABEL m3_print_label[14] 
LVAR_INT m3_audio_label[14] 
LVAR_INT m3_last_label 
LVAR_INT m3_slot1 m3_slot2 m3_slot_load m3_play_which_slot

//coords
LVAR_FLOAT m3_x m3_y m3_z
LVAR_FLOAT m3_x2 m3_y2 m3_z2  


//sequences/decision makers/threat lists/attractors/groups
LVAR_INT m3_limo_seq m3_seq m3_empty_ped_decision_maker m3_ped_decisions  

//debug

// **************************************** Mission Start **********************************
mission_start_music3:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT STRAP3

IF flag_player_on_mission = 0 
	//COPY_CHAR_DECISION_MAKER m3_star m3_celeb_decisions
	CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1123.2 22.9 right_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1127.2 22.9 right_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1131.2 22.9 right_barrier3
	CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1123.2 22.9 left_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1127.2 22.9 left_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1131.2 22.9 left_barrier3
	CREATE_OBJECT DYN_ROADBARRIER_2 1016.5 -1133.2 22.9 front_left_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1012.5 -1133.2 22.9 front_left_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1008.5 -1133.2 22.9 front_left_barrier3
	CREATE_OBJECT DYN_ROADBARRIER_2 1028.5 -1133.2 22.9 front_right_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1032.5 -1133.2 22.9 front_right_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 front_right_barrier3
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_east_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_east_barrier2

	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_south_east_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_south_east_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_south_west_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_south_west_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_west_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_west_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_north_west_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_north_west_barrier2
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_north_east_barrier1
	CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 m3_north_east_barrier2

	ADD_BLIP_FOR_COORD 1118.3 -1140.9 22.2 m3_docg_manager_blip
	CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1023.4 -1121.0 22.9 m3_cop[8]
	CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1023.4 -1121.0 22.9 m3_limo_driver[1]
	CREATE_CAR ELEGANT 1174.1 -926.8 43.6 m3_limo[1]
	CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1023.4 -1121.0 22.9 mc1
	CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1023.4 -1121.0 22.9 wc1
	CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1023.4 -1121.0 22.9 m3_interviewer

ENDIF

CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
m3_goals = 0
m3_control_flag = 0
m3_skip_cutscene_flag = 0 
m3_deathcheck_flag = 0 
m3_speech_flag = 0 
m3_cutscene_goals_flag = 0
x = 0.0
y = 0.0
z = 0.0
m3_cs = 0
m3_flash_flag1 = 0
m3_flash_flag2 = 0
m3_shit_hit_fan = 0
has_crowd_been_created = 0 
crowd_flag_death_check = 0 
creating_crowd_flag = 0 
m3_crowd_flag = 0 
m3_crowd_check = 0
m3_char_select[1] = WBDYG1 
//m3_char_select[3] = WMYPAP1
//m3_char_select[4] = WMYPAP2
cop_death_check = 0 
m3_car_check_flag = 0
m3_x = 0.0 
m3_y = 0.0 
m3_z = 0.0
m3_x2 = 0.0 
m3_y2 = 0.0 
m3_z2 = 0.0
m3_gametimer_difference = 0
m3_gametimer_ended = 0 
m3_gametimer_started = 0
m3_time_to_fail = 0	
m3_stray_flag = 0	
m3_number_to_print = 0
m3_hours = 0 
minutes = 0
m3_how_many_cars_flag = 0
m3_car_heading = 0.0
m3_random_number = 0
m3_baddy_control_flag[0] = 0
m3_baddy_control_flag[1] = 0
m3_baddy_in_range_check = 0
m3_paynspray_text_duration = 0
m3_paynspray_text_flag = 0
m3_initial_direction_text_flag = 0
m3_warning_text = 0

m3_speech_goals = 0 
m3_speech_flag = 0 
m3_speech_control_flag = 0 
m3_random_last_label = 0 
m3_last_label = 0 
m3_slot1 = 0 
m3_slot2 = 0 
m3_slot_load = 0
m3_play_which_slot = 0
m3_help_flag = 0
m3_removing_crowd = 0
m3_removing_cop_flag = 0
m3_removing_crowd_flag = 0
m3_phone_cutscene = 0

m3_no_plates_flag = 0 
m3_no_plates = 0

m3_seq_progress = 0
m3_random_flash_flag1 = 0
m3_random_flash_flag2 = 0
		
// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 10
SET_FADING_COLOUR 0 0 0
LOAD_CUTSCENE STRAP3A
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
SET_AREA_VISIBLE 0
CLEAR_CUTSCENE
MAKE_PLAYER_GANG_REAPPEAR
SET_PLAYER_CONTROL player1 OFF
// ****************************************END OF CUTSCENE**********************************
SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS------------------------------
REQUEST_MODEL WBDYG1
REQUEST_MODEL ELEGANT
REQUEST_MODEL COLT45
REQUEST_MODEL CELLPHONE
LOAD_MISSION_AUDIO 3 SOUND_LOCK_CAR_DOORS

LOAD_ALL_MODELS_NOW

///setting up char and group decision makers
REMOVE_GROUP Players_Group 
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY m3_empty_ped_decision_maker
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH m3_ped_decisions 

CLEAR_AREA 1214.8 -921.7 41.7 5.0 TRUE 
m3_no_plates = ELEGANT
GOSUB m3_my_number_plates
CREATE_CAR ELEGANT 1214.8 -921.7 41.7 m3_dgcar1[1]
SET_CAR_HEADING m3_dgcar1[1] 169.3
CHANGE_CAR_COLOUR m3_dgcar1[1] 0 0
SET_CAN_RESPRAY_CAR m3_dgcar1[1] FALSE 
SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[1] TRUE
LOCK_CAR_DOORS m3_dgcar1[1] CARLOCK_UNLOCKED
SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[1] TRUE
SET_CAN_BURST_CAR_TYRES m3_dgcar1[1] FALSE
ADD_BLIP_FOR_CAR m3_dgcar1[1] m3_docg_manager_blip

CREATE_CHAR_INSIDE_CAR m3_dgcar1[1] PEDTYPE_MISSION2 WBDYG1 m3_dgcar1_driver[1]
GIVE_WEAPON_TO_CHAR m3_dgcar1_driver[1] WEAPONTYPE_PISTOL 3000
SET_CHAR_DECISION_MAKER m3_dgcar1_driver[1] m3_ped_decisions

CLEAR_AREA 1297.6 -1137.2 22.5 5.0 TRUE 
m3_no_plates = ELEGANT
GOSUB m3_my_number_plates
CREATE_CAR ELEGANT 1297.6 -1137.2 22.5 m3_dgcar1[0]																   
SET_CAR_HEADING	m3_dgcar1[0] 90.0																			   
SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[0] TRUE																   
CHANGE_CAR_COLOUR m3_dgcar1[0] 0 0
MARK_CAR_AS_CONVOY_CAR m3_dgcar1[0] TRUE
LOCK_CAR_DOORS m3_dgcar1[0] CARLOCK_LOCKOUT_PLAYER_ONLY
FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[0] TRUE 
SET_CAN_BURST_CAR_TYRES m3_dgcar1[0] FALSE

CLEAR_AREA 1316.5 -1137.2 22.5 5.0 TRUE 
m3_no_plates = ELEGANT
GOSUB m3_my_number_plates
CREATE_CAR ELEGANT 1316.5 -1137.2 22.5 m3_dgcar1[2]																   
SET_CAR_HEADING	m3_dgcar1[2] 90.0																			   
SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[2] TRUE																   
CHANGE_CAR_COLOUR m3_dgcar1[2] 0 0
MARK_CAR_AS_CONVOY_CAR m3_dgcar1[2] TRUE
LOCK_CAR_DOORS m3_dgcar1[2] CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_DRIVING_STYLE m3_dgcar1[2] DRIVINGMODE_SLOWDOWNFORCARS
FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[2] TRUE  
SET_CAN_BURST_CAR_TYRES m3_dgcar1[2] FALSE
SET_CAR_STRAIGHT_LINE_DISTANCE m3_dgcar1[2] 50 
SET_CAR_CAN_BE_VISIBLY_DAMAGED m3_dgcar1[2] FALSE 

//nodes stopping wander car from fucking off into countryside
//node 1 
MARK_ROAD_NODE_AS_DONT_WANDER 76.6 -1527.6 4.1
//node 2 
MARK_ROAD_NODE_AS_DONT_WANDER 162.2 -1394.6 46.8
//node 3 
MARK_ROAD_NODE_AS_DONT_WANDER 273.2 -980.9 47.4
//node 4 
MARK_ROAD_NODE_AS_DONT_WANDER 362.8 -1147.8 77.0
//node 5 
MARK_ROAD_NODE_AS_DONT_WANDER 378.1 -1147.4 77.0
//node 6 
MARK_ROAD_NODE_AS_DONT_WANDER 1536.4 -994.8 41.5
//node 7 
MARK_ROAD_NODE_AS_DONT_WANDER 1638.7 -1177.5 54.1
//node 8 
MARK_ROAD_NODE_AS_DONT_WANDER 1873.9 -1009.4 35.0
//node 9 
MARK_ROAD_NODE_AS_DONT_WANDER 2887.8 -1121.4 9.8
//node 10 
MARK_ROAD_NODE_AS_DONT_WANDER 165.8 -1185.3 51.8
//node 11 
MARK_ROAD_NODE_AS_DONT_WANDER 695.7 -995.6 51.0
//node 12 
MARK_ROAD_NODE_AS_DONT_WANDER 794.8 -1033.6 23.7
//node 13 
MARK_ROAD_NODE_AS_DONT_WANDER 1311.1 -701.7 91.7
//node 14 
MARK_ROAD_NODE_AS_DONT_WANDER 2838.7 -1024.2 21.5

PRINT_NOW ( STP3_01 ) 7000 1 //One of Doc G's Managers drivers is eating at the Burger Shot across town. 
PRINT ( STP3_02 ) 7000 1 //Go and steal his car and meet the other drivers before 10pm. 
CLEAR_AREA 794.6 -1597.1 12.5 1.0 TRUE
LOAD_SCENE 794.6 -1597.1 12.5 
SET_CHAR_COORDINATES scplayer 794.6 -1597.1 12.5												   
SET_CHAR_HEADING scplayer 45.0

SET_TIME_OF_DAY 16 00

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

ENABLE_AMBIENT_CRIME FALSE

/*
VAR_INT m3_flash_flag1 
VAR_INT m3_flash_flag2 
VIEW_INTEGER_VARIABLE m3_flash_flag1 m3_flash_flag1
VIEW_INTEGER_VARIABLE m3_flash_flag2 m3_flash_flag2
*/
timerb = 0
DO_FADE 500 FADE_IN

mission_music3_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_music3_passed  
	ENDIF
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Deathchecks ///////////////////////////////////////////////////////////////////////////////////
	GOSUB m3_death_checks
	IF m3_deathcheck_flag = 1
		GOTO mission_music3_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// setting up a game timer ///////////////////////////////////////////////////////////////////////
	GET_GAME_TIMER m3_gametimer_ended 
	m3_gametimer_difference = m3_gametimer_ended - m3_gametimer_started
	m3_gametimer_started = m3_gametimer_ended

	m3_time_to_fail -= m3_gametimer_difference 
	m3_paynspray_text_duration += m3_gametimer_difference

	//getting time of day
	GET_TIME_OF_DAY m3_hours minutes


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// waiting for player to steal the humvee  ///////////////////////////////////////////////////////
	IF m3_goals = 0
		////debug////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			SET_CHAR_COORDINATES scplayer 1179.5 -920.3 42.2
			SET_CHAR_HEADING scplayer 99.7 
		ENDIF
		/////debug///
		
		////////////SPEECH FOR THIS SECTION////////////////////
		//dialogue once player is near the car to tell the player to damage it
		IF m3_speech_flag = 0		    
			IF m3_control_flag = 1
			OR m3_control_flag = 2 
				IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer m3_dgcar1[1] 25.0 25.0 25.0 FALSE 
					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
						IF IS_CHAR_IN_CAR m3_dgcar1_driver[1] m3_dgcar1[1]
							PRINT ( STP3_19 ) 7000 1 //Damage the car to force the driver out.
						ENDIF
					ELSE
						m3_speech_flag = 2
					ENDIF
					m3_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF m3_speech_flag = 1
			//clearing that fucking damage print
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
				IF NOT IS_CHAR_SITTING_IN_CAR m3_dgcar1_driver[1] m3_dgcar1[1]
					CLEAR_THIS_PRINT STP3_19 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer m3_dgcar1_driver[1] 15.0 15.0 15.0 FALSE
						IF m3_speech_goals = 0 
							//driver swearing at player
							m3_speech_goals = 1
							m3_speech_control_flag = 0
							GOSUB m3_dialogue_setup 
						ENDIF
					ENDIF
					m3_speech_flag = 2	
				ENDIF
			ELSE
				m3_speech_flag = 2
			ENDIF
		ENDIF
		
		IF m3_speech_flag = 2 
			IF m3_speech_goals = 0 
				IF NOT IS_CAR_DEAD m3_dgcar1[1] 
					IF NOT IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
						CLEAR_THIS_PRINT STP3_19
						PRINT ( STP3_11 ) 7000 1 //Get in the car.
						m3_speech_flag = 3				   
					ELSE
						m3_speech_flag = 3	
					ENDIF 
				ENDIF
			ENDIF
		ENDIF
		
		/// WAITING FOR PLAYER TO TRIGGER MC STRAP PHONE CALL
		IF m3_control_flag = 0
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer m3_dgcar1_driver[1] 250.0 250.0 FALSE
				OR m3_hours > 17
					GOSUB check_player_is_safe
					IF player_is_completely_safe = 1
						//phone cutscene
						IF NOT IS_CHAR_IN_ANY_CAR scplayer  
							//cutscene showing player answering phone
							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1	 
							CLEAR_MISSION_AUDIO 2
							m3_speech_goals = 0
						
							SET_PLAYER_CONTROL player1 OFF 	
							SWITCH_WIDESCREEN ON
						
							//phone ringing
							m3_speech_goals = 7
							m3_speech_control_flag = 0
							m3_random_last_label = 1
							GOSUB m3_dialogue_setup 
									
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
						
							m3_skip_cutscene_flag = 1
							SKIP_CUTSCENE_START
							
							TASK_USE_MOBILE_PHONE scplayer TRUE	
							timera = 0
							m3_phone_cutscene = 1
							m3_control_flag = 1
						ELSE
							IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
								IF NOT IS_CAR_DEAD m3_dgcar1[1] 
									OPEN_SEQUENCE_TASK m3_seq	  
										TASK_ENTER_CAR_AS_DRIVER -1 m3_dgcar1[1] -1 
										TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[1] 1211.0 -932.3 41.9 15.0 MODE_NORMAL 1 DRIVINGMODE_STOPFORCARS
										TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[1] 1160.8 -1027.5 31.4 15.0 MODE_NORMAL 1 DRIVINGMODE_STOPFORCARS
										TASK_CAR_DRIVE_WANDER -1 m3_dgcar1[1] 15.0 DRIVINGMODE_STOPFORCARS
									CLOSE_SEQUENCE_TASK m3_seq
									PERFORM_SEQUENCE_TASK m3_dgcar1_driver[1] m3_seq
									CLEAR_SEQUENCE_TASK m3_seq
									
									ADD_STUCK_CAR_CHECK_WITH_WARP m3_dgcar1[1] 0.5 4000 TRUE TRUE TRUE 7
								ENDIF
							ENDIF
						
							//mc strap phoning player
							m3_speech_goals = 7
							m3_speech_control_flag = 0
							m3_random_last_label = 5
							GOSUB m3_dialogue_setup 		
						
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
							
							m3_phone_cutscene = 3
							m3_control_flag = 1
						ENDIF
					ELSE
						m3_control_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF m3_phone_cutscene = 1 
			IF timera > 2000
				IF m3_speech_goals = 0 
					//mc strap phoning player
					m3_speech_goals = 7
					m3_speech_control_flag = 1
					m3_random_last_label = 2
					GOSUB m3_dialogue_setup 		
					m3_phone_cutscene = 2
				ENDIF
			ENDIF
		ENDIF					
	
		IF m3_phone_cutscene = 2
			IF m3_speech_goals = 0 
				//mc strap phoning player
				m3_speech_goals = 7
				m3_speech_control_flag = 2
				m3_random_last_label = 5
				GOSUB m3_dialogue_setup 		
			
				m3_skip_cutscene_flag = 0				
				SKIP_CUTSCENE_END
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF

				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
					IF NOT IS_CAR_DEAD m3_dgcar1[1] 
						OPEN_SEQUENCE_TASK m3_seq	  
							TASK_ENTER_CAR_AS_DRIVER -1 m3_dgcar1[1] -1 
							TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[1] 1211.0 -932.3 41.9 15.0 MODE_NORMAL 1 DRIVINGMODE_STOPFORCARS
							TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[1] 1160.8 -1027.5 31.4 15.0 MODE_NORMAL 1 DRIVINGMODE_STOPFORCARS
							TASK_CAR_DRIVE_WANDER -1 m3_dgcar1[1] 15.0 DRIVINGMODE_STOPFORCARS
						CLOSE_SEQUENCE_TASK m3_seq
						PERFORM_SEQUENCE_TASK m3_dgcar1_driver[1] m3_seq
						CLEAR_SEQUENCE_TASK m3_seq
						
						ADD_STUCK_CAR_CHECK_WITH_WARP m3_dgcar1[1] 0.5 4000 TRUE TRUE TRUE 7
					ENDIF
				ENDIF

				SET_PLAYER_CONTROL player1 ON 	
				SWITCH_WIDESCREEN OFF
				m3_phone_cutscene = 3
			ENDIF
		ENDIF

		IF m3_phone_cutscene = 3
			IF m3_speech_goals = 0
				GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE task_status
				IF task_status = PERFORMING_TASK
					TASK_USE_MOBILE_PHONE scplayer FALSE	 
				ENDIF
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				m3_phone_cutscene = 4
			ENDIF
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				CLEAR_PRINTS 
				CLEAR_MISSION_AUDIO 1	
				CLEAR_MISSION_AUDIO 2
				m3_speech_goals = 0
				GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE task_status
				IF task_status = PERFORMING_TASK
					TASK_USE_MOBILE_PHONE scplayer FALSE
				ENDIF
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				m3_phone_cutscene = 4
			ENDIF
		ENDIF

		//car is on it's way to meet the other cars 
		IF m3_control_flag = 1
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
				IF IS_CHAR_IN_CAR m3_dgcar1_driver[1] m3_dgcar1[1]
					IF m3_hours > 20 //i.e. 9 pm
						TASK_CAR_DRIVE_TO_COORD m3_dgcar1_driver[1] m3_dgcar1[1] 1316.4 -1136.5 22.9 15.0 MODE_NORMAL 1 DRIVINGMODE_AVOIDCARS
						m3_control_flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//waiting for car to reach other cars and then fail the mission
		IF m3_control_flag = 2 
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
				IF IS_CHAR_IN_CAR m3_dgcar1_driver[1] m3_dgcar1[1]
					IF LOCATE_CAR_2D m3_dgcar1[1] 1316.4 -1136.5 5.0 5.0 FALSE
						PRINT_NOW ( STP3_03 ) 4000 1 //You were too slow!
						m3_how_many_cars_flag = 1
						m3_goals = 9	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
		//checking if enemy should attack player
		IF m3_control_flag > 0
			IF m3_control_flag < 3 
				//car has been damaged
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR m3_dgcar1[1] scplayer 
					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1] 
						OPEN_SEQUENCE_TASK m3_seq	  
							TASK_LEAVE_ANY_CAR -1
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK m3_seq
						PERFORM_SEQUENCE_TASK m3_dgcar1_driver[1] m3_seq
						CLEAR_SEQUENCE_TASK m3_seq
					ENDIF
					REMOVE_STUCK_CAR_CHECK m3_dgcar1[1]
					m3_control_flag = 3
				ENDIF
		
				//player has pulled char out 
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
					IF NOT IS_CHAR_IN_CAR m3_dgcar1_driver[1] m3_dgcar1[1] 
						IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1] 
							OPEN_SEQUENCE_TASK m3_seq	  
								TASK_LEAVE_ANY_CAR -1
								TASK_KILL_CHAR_ON_FOOT -1 scplayer
							CLOSE_SEQUENCE_TASK m3_seq
							PERFORM_SEQUENCE_TASK m3_dgcar1_driver[1] m3_seq
							CLEAR_SEQUENCE_TASK m3_seq
						ENDIF
						REMOVE_STUCK_CAR_CHECK m3_dgcar1[1] 
						m3_control_flag = 3
					ENDIF
				ELSE
					REMOVE_STUCK_CAR_CHECK m3_dgcar1[1]
					m3_control_flag = 3
				ENDIF
			ENDIF
		ENDIF

	
	
		//////////////MISC STUFF FOR THIS BIT////////////////////
		//failing the mission due to running out of time
		IF m3_hours > 21 //i.e. 10pm
			m3_how_many_cars_flag = 0
			m3_goals = 9
		ENDIF

		//changing car to be able to be damaged by other people while on_screen
		IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[1] 60.0 60.0 FALSE 
			SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[1] FALSE
		ELSE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER m3_dgcar1[1] TRUE
		ENDIF

		
		
		////////////WAITING FOR PLAYER TO GET INTO CAR///////////////
		IF m3_control_flag = 3 
			IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1]
				REMOVE_BLIP m3_docg_manager_blip
				ADD_BLIP_FOR_COORD 1306.4 -1137.5 22.5 m3_docg_manager_blip 
				CLEAR_THIS_PRINT STP3_11
				PRINT ( STP3_33 ) 7000 1 //Go and meet up with the other drivers.  Don't damage your car! 
				MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
				m3_speech_flag = 0
				m3_control_flag = 0
				m3_goals = 1
			ENDIF
		ENDIF
	ENDIF


			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// waiting for player to arrive next to other drivers ////////////////////////////////////////////
	IF m3_goals = 1 
		////debug////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			SET_CHAR_COORDINATES scplayer 1306.4 -1137.5 22.5
			SET_CAR_HEADING m3_dgcar1[1] 90.0  
		ENDIF
		/////debug///
		
		
		//failing the mission due to running out of time
		IF m3_hours > 21 //i.e. 10pm
			m3_how_many_cars_flag = 0
			m3_goals = 9
		ENDIF
		
		//getting the car repaired if it is damaged
		IF m3_control_flag = 0
			IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1]
				IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[1] 700
				OR IS_CAR_VISIBLY_DAMAGED m3_dgcar1[1]
					CLEAR_THIS_PRINT STP3_33 
					IF m3_speech_flag = 0
						REMOVE_BLIP spray_shop2 
						ADD_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
						PRINT ( STP3_22 ) 11000 1 //The car is damaged, go and get it repaired.
						m3_speech_flag = 1
					ENDIF
				ENDIF
					
				IF IS_CAR_HEALTH_GREATER m3_dgcar1[1] 700
					IF NOT IS_CAR_VISIBLY_DAMAGED m3_dgcar1[1]  
						CLEAR_THIS_PRINT STP3_22 
						REMOVE_BLIP spray_shop2 
						ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
						REMOVE_BLIP m3_docg_manager_blip
						ADD_BLIP_FOR_COORD 1306.4 -1137.5 22.5 m3_docg_manager_blip
						m3_paynspray_text_flag = 0
						m3_paynspray_text_duration = 0
						m3_speech_flag = 0
						m3_control_flag = 1	
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF m3_paynspray_text_flag = 0
			IF m3_paynspray_text_duration > 4000
				PRINT ( STP3_33 ) 7000 1 //Go and meet up with the other drivers.  Don't damage your car!
				m3_paynspray_text_flag = 1
			ENDIF
		ENDIF
		
		//waiting for player to get to the other cars and get in correct position
		IF m3_control_flag = 1
			IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
			
				//waiting for player to get to the other cars
				IF LOCATE_CAR_3D m3_dgcar1[1] 1306.4 -1137.5 22.5 4.0 4.0 4.0 TRUE
					GET_CAR_HEADING m3_dgcar1[1] m3_car_heading
					
					IF m3_car_heading > 95.0
						IF m3_car_heading <= 360.0 
							PRINT_NOW ( STP3_25 ) 7000 1 //Park the car facing the same way as the other cars.
						ENDIF
					ENDIF
					
					
					IF m3_car_heading < 85.0    
						IF m3_car_heading >= 0.0
							PRINT_NOW ( STP3_25 ) 7000 1 //Park the car facing the same way as the other cars.
						ENDIF
					ENDIF
					
					IF m3_car_heading >= 85.0
						IF m3_car_heading <= 95.0 
							m3_control_flag = 2	
						ENDIF
					ENDIF  
				ENDIF

				//printing help first time player comes close to cars
				IF m3_initial_direction_text_flag = 0
					IF LOCATE_CAR_3D m3_dgcar1[1] 1306.4 -1137.5 22.5 15.0 30.0 30.0 FALSE
						IF IS_CAR_ON_SCREEN m3_dgcar1[0] 
						OR IS_CAR_ON_SCREEN m3_dgcar1[2]
							PRINT_NOW ( STP3_25 ) 7000 1 //Park the car facing the same way as the other cars.
							m3_initial_direction_text_flag = 1
						ENDIF
					ENDIF
				ENDIF
			
				//checking if car is damaged
				IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[1] 700
				OR IS_CAR_VISIBLY_DAMAGED m3_dgcar1[1]
					REMOVE_BLIP m3_docg_manager_blip
					REMOVE_BLIP spray_shop2 
					ADD_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
					CLEAR_PRINTS
					PRINT ( STP3_22 ) 11000 1 //The car is damaged, go and get it repaired.
					m3_control_flag = 0	
				ENDIF
			ENDIF
		ENDIF

		//player has acheived the correct position
		IF IS_CAR_HEALTH_GREATER m3_dgcar1[1] 700
			IF NOT IS_CAR_VISIBLY_DAMAGED m3_dgcar1[1]
				IF m3_control_flag = 2
					SET_PLAYER_CONTROL player1 OFF
					MARK_CHAR_AS_NO_LONGER_NEEDED m3_dgcar1_driver[1] 
					REMOVE_BLIP m3_docg_manager_blip
					REMOVE_BLIP spray_shop2 
					ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
					m3_goals = 2
				ENDIF
			ENDIF
		ENDIF

		GOSUB m3_blippage
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// FAIL CUTSCENE /////////////////////////////////////////////////////////////////////////////////
	IF m3_goals = 9 ///this is used to stop control_flag from resetting other stuff within m3_goals = 0 and m3_goals = 1 
		m3_control_flag = 0
		m3_goals = 10
	ENDIF
	IF m3_goals = 10 
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			//m3_how_many_cars_flag = 0 //show two cars pulling away 
			//m3_how_many_cars_flag = 1 //show three cars pulling away 
			IF m3_control_flag = 0
				CLEAR_PRINTS
			
				DO_FADE 500 FADE_OUT		
				WHILE GET_FADING_STATUS	
				    WAIT 0
				ENDWHILE 
			
				CLEAR_MISSION_AUDIO 1	 
				CLEAR_MISSION_AUDIO 2
				m3_speech_goals = 0
			
				SET_PLAYER_CONTROL player1 OFF 	
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				SHUT_ALL_CHARS_UP TRUE 
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1306.4 -1137.5 20.0 20.0 FALSE 	
					CLEAR_AREA 1247.1 -1156.0 22.7 1.0 TRUE
					SET_CHAR_COORDINATES scplayer 1247.1 -1156.0 22.7
				ENDIF  
			
				LOAD_SCENE 1305.5 -1131.7 22.6

				DELETE_CAR m3_dgcar1[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED m3_dgcar1[1] //player could be in this car 
				DELETE_CHAR m3_dgcar1_driver[1] 
				DELETE_CAR m3_dgcar1[2]
				SET_RADIO_CHANNEL RS_OFF
				
				CLEAR_AREA 1303.5 -1131.5 22.6 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1303.5 -1131.5 22.6 m3_dgcar1_driver[0]
				SET_CHAR_HEADING m3_dgcar1_driver[0] 168.1
				SET_CHAR_DECISION_MAKER m3_dgcar1_driver[0] m3_empty_ped_decision_maker
					
				CLEAR_AREA 1297.6 -1137.2 22.5 5.0 TRUE
				m3_no_plates = ELEGANT
				GOSUB m3_my_number_plates
				CREATE_CAR ELEGANT 1297.6 -1137.2 22.5 m3_dgcar1[0]																   
				SET_CAR_HEADING	m3_dgcar1[0] 90.0																			   
				CHANGE_CAR_COLOUR m3_dgcar1[0] 0 0
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[0] TRUE
				TASK_ENTER_CAR_AS_DRIVER m3_dgcar1_driver[0] m3_dgcar1[0] -1

				CLEAR_AREA 1305.5 -1131.7 22.6 5.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1305.5 -1131.7 22.6 m3_dgcar1_driver[2]
				SET_CHAR_HEADING m3_dgcar1_driver[2] 168.1
				SET_CHAR_DECISION_MAKER m3_dgcar1_driver[2] m3_empty_ped_decision_maker
			
				CLEAR_AREA 1316.5 -1137.2 22.5 5.0 TRUE
				m3_no_plates = ELEGANT
				GOSUB m3_my_number_plates
				CREATE_CAR ELEGANT 1316.5 -1137.2 22.5 m3_dgcar1[2]																   
				SET_CAR_HEADING	m3_dgcar1[2] 90.0																			   
				CHANGE_CAR_COLOUR m3_dgcar1[2] 0 0
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[2] TRUE
				SET_CAR_STRAIGHT_LINE_DISTANCE m3_dgcar1[2] 50 

				IF m3_how_many_cars_flag = 1 
					CLEAR_AREA 1306.4 -1137.5 22.5 5.0 TRUE
					m3_no_plates = ELEGANT
					GOSUB m3_my_number_plates
					CREATE_CAR ELEGANT 1306.4 -1137.5 22.5 m3_dgcar1[1]																   
					SET_CAR_HEADING	m3_dgcar1[1] 90.0																			   
					CHANGE_CAR_COLOUR m3_dgcar1[1] 0 0
					MARK_CAR_AS_CONVOY_CAR m3_dgcar1[2] TRUE
					SET_CAR_STRAIGHT_LINE_DISTANCE m3_dgcar1[1] 50 

					GENERATE_RANDOM_INT_IN_RANGE 0 3 m3_cs
					CREATE_CHAR_INSIDE_CAR m3_dgcar1[1] PEDTYPE_MISSION2 m3_char_select[m3_cs] m3_dgcar1_driver[1]
				ELSE
					IF NOT IS_CAR_DEAD m3_dgcar1[1] 
						IF LOCATE_CAR_2D m3_dgcar1[1] 1306.4 -1137.5 20.0 20.0 FALSE 	
							SET_CAR_COORDINATES m3_dgcar1[1] 1247.5 -1151.8 22.6
						ENDIF
					ENDIF  
				ENDIF
				
				SET_FIXED_CAMERA_POSITION 1292.8 -1143.5 24.3 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1302.3 -1136.2 24.0 JUMP_CUT
		
				CLEAR_PRINTS
				IF m3_how_many_cars_flag = 0
					PRINT_NOW ( STP3_20 ) 8000 1 //You were too slow! Doc G's other cars have left without you.
				ELSE
					PRINT_NOW ( STP3_21 ) 8000 1 //You were too slow! Doc G's cars have left!
				ENDIF
				
				DO_FADE 500 FADE_IN		
				WHILE GET_FADING_STATUS	
				    WAIT 0
				ENDWHILE 
				timera = 0
				m3_control_flag = 1
			ENDIF
		ENDIF

		IF m3_control_flag = 1
			IF timera > 1000 
				IF NOT IS_CAR_DEAD m3_dgcar1[2] 
					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2] 
						TASK_ENTER_CAR_AS_DRIVER m3_dgcar1_driver[2] m3_dgcar1[2] -1
					ENDIF
				ENDIF
				m3_control_flag = 2
			ENDIF
		ENDIF

		IF m3_control_flag = 2
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
				GET_SCRIPT_TASK_STATUS m3_dgcar1_driver[2] TASK_ENTER_CAR_AS_DRIVER task_status 
				IF task_status = FINISHED_TASK
					IF NOT IS_CAR_DEAD m3_dgcar1[0]  
						IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
							OPEN_SEQUENCE_TASK m3_limo_seq
								TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[0] 1103.3 -1140.9 22.7 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS //front car
							CLOSE_SEQUENCE_TASK m3_limo_seq
							PERFORM_SEQUENCE_TASK m3_dgcar1_driver[0] m3_limo_seq 		
							CLEAR_SEQUENCE_TASK m3_limo_seq
						ENDIF
					ENDIF
					
					IF NOT IS_CAR_DEAD m3_dgcar1[2]  
						IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
							OPEN_SEQUENCE_TASK m3_limo_seq
								TASK_PAUSE -1 500
								TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[2] 1103.3 -1140.9 22.7 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS //front car
							CLOSE_SEQUENCE_TASK m3_limo_seq
							PERFORM_SEQUENCE_TASK m3_dgcar1_driver[2] m3_limo_seq 		
							CLEAR_SEQUENCE_TASK m3_limo_seq
						ENDIF
					ENDIF
					
					IF m3_how_many_cars_flag = 1
						IF NOT IS_CAR_DEAD m3_dgcar1[1]  
							IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
								OPEN_SEQUENCE_TASK m3_limo_seq
									TASK_PAUSE -1 1000
									TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[1] 1103.3 -1140.9 22.7 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS //front car
								CLOSE_SEQUENCE_TASK m3_limo_seq
								PERFORM_SEQUENCE_TASK m3_dgcar1_driver[1] m3_limo_seq 		
								CLEAR_SEQUENCE_TASK m3_limo_seq
							ENDIF
						ENDIF
					ENDIF
					m3_control_flag = 3
				ENDIF
			ENDIF
		ENDIF

		IF m3_control_flag = 3
			IF NOT IS_CAR_DEAD m3_dgcar1[0] 
				IF NOT IS_CAR_ON_SCREEN m3_dgcar1[0]
					DO_FADE 500 FADE_OUT		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 

					
					SHUT_ALL_CHARS_UP FALSE
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					SET_PLAYER_CONTROL player1 ON 
					SET_CAMERA_BEHIND_PLAYER 	
					RESTORE_CAMERA_JUMPCUT
					GET_CHAR_COORDINATES scplayer m3_x m3_y m3_z 
					LOAD_SCENE m3_x m3_y m3_z
				
					CLEAR_PRINTS
					PRINT_BIG M_FAIL 5000 1
					IF m3_how_many_cars_flag = 0
						PRINT_NOW ( STP3_20 ) 5500 1 //You were too slow! Doc G's other cars have left without you.
					ELSE
						PRINT_NOW ( STP3_21 ) 5500 1 //You were too slow! Doc G's cars have left!
					ENDIF

					DO_FADE 500 FADE_IN		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOTO mission_music3_failed
				ENDIF
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// playing the MOOTV awards cutscene /////////////////////////////////////////////////////////////
	IF m3_goals = 2
		IF m3_cutscene_goals_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1	 
			CLEAR_MISSION_AUDIO 2
			m3_speech_goals = 0
			
			SWITCH_AUDIO_ZONE AWARDS TRUE
			
			SHUT_ALL_CHARS_UP TRUE
			
			CLEAR_WANTED_LEVEL player1

			CLEAR_AREA 1303.5 -1131.5 22.6 5.0 TRUE 
			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1303.5 -1131.5 22.6 m3_dgcar1_driver[0]
			SET_CHAR_HEADING m3_dgcar1_driver[0] 168.1
			GIVE_WEAPON_TO_CHAR m3_dgcar1_driver[0] WEAPONTYPE_PISTOL 3000
			SET_CHAR_DECISION_MAKER m3_dgcar1_driver[0] m3_empty_ped_decision_maker

			CLEAR_AREA 1305.5 -1131.7 22.6 5.0 TRUE 
			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1305.5 -1131.7 22.6 m3_dgcar1_driver[2]
			SET_CHAR_HEADING m3_dgcar1_driver[2] 168.1
			GIVE_WEAPON_TO_CHAR m3_dgcar1_driver[2] WEAPONTYPE_PISTOL 3000
			SET_CHAR_DECISION_MAKER m3_dgcar1_driver[2] m3_empty_ped_decision_maker

			CLEAR_AREA 1306.4 -1137.5 22.5 5.0 TRUE
			SET_CAR_COORDINATES m3_dgcar1[1] 1306.4 -1137.5 22.5
			SET_CAR_HEADING m3_dgcar1[1] 90.0

			SET_PLAYER_CONTROL player1 OFF 	
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			SET_FIXED_CAMERA_POSITION 1288.6 -1146.2 28.9 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1301.0 -1137.2 23.3 JUMP_CUT

			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[0] FALSE
			TASK_ENTER_CAR_AS_DRIVER m3_dgcar1_driver[0] m3_dgcar1[0] -1
		
			timera = 0
			m3_cutscene_goals_flag = 1
		ENDIF
			
		IF m3_cutscene_goals_flag = 1 
			IF timera > 1000 
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2] 
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[2] FALSE
					TASK_ENTER_CAR_AS_DRIVER m3_dgcar1_driver[2] m3_dgcar1[2] -1
				ENDIF

				m3_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				m3_cutscene_goals_flag = 2
			ENDIF
		ENDIF
			
		IF m3_cutscene_goals_flag = 2
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
				GET_SCRIPT_TASK_STATUS m3_dgcar1_driver[2] TASK_ENTER_CAR_AS_DRIVER task_status 
				IF task_status = FINISHED_TASK
					//Hey, what kept you?
					m3_speech_goals = 8
					m3_speech_control_flag = 0
					m3_random_last_label = 2
					GOSUB m3_dialogue_setup
					m3_cutscene_goals_flag = 3
				ENDIF
			ENDIF
		ENDIF 		
				
		IF m3_cutscene_goals_flag = 3
			IF m3_speech_goals = 0 		
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF

				REQUEST_MODEL BMYRI
				REQUEST_MODEL WFYRI
				//REQUEST_MODEL WMYPAP1
				//REQUEST_MODEL WMYPAP2
				REQUEST_MODEL CAMERA
				REQUEST_MODEL STRETCH
				REQUEST_MODEL LAPD1
				REQUEST_MODEL DYN_ROADBARRIER_2
				
				LOAD_ALL_MODELS_NOW
			
				SET_RADIO_CHANNEL RS_OFF
				
				WHILE has_crowd_been_created = 0
					WAIT 0
					GOSUB m3_creating_crowd
				ENDWHILE 
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF

				SET_TIME_OF_DAY 22 00
				CLEAR_AREA 1022.2 -1133.2 24.0 80.0 TRUE 
				LOAD_SCENE 1022.2 -1133.2 24.0
				SET_FIXED_CAMERA_POSITION 996.6 -1144.4 29.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1018.9 -1133.7 23.5 JUMP_CUT	

				//PRINT_NOW ( STP3_05 ) 4000 1 //THE M00TV AWARDS CEREMONY 

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF
				m3_cutscene_goals_flag = 4
				timera = 0
			ENDIF 
		ENDIF

		IF m3_cutscene_goals_flag = 4 
			IF NOT IS_CAR_DEAD m3_limo[0]
				IF NOT IS_CHAR_DEAD m3_limo_driver[0]
					GET_SCRIPT_TASK_STATUS m3_limo_driver[0] PERFORM_SEQUENCE_TASK task_status
					IF task_status = FINISHED_TASK  
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS	m3_limo[0] 1.7 1.5 0.0 m3_x m3_y z
						OPEN_SEQUENCE_TASK m3_limo_seq
							TASK_LEAVE_ANY_CAR -1  
							TASK_GO_STRAIGHT_TO_COORD -1 m3_x m3_y m3_z PEDMOVE_WALK -1
							TASK_ACHIEVE_HEADING -1 0.0  
						CLOSE_SEQUENCE_TASK m3_limo_seq
						PERFORM_SEQUENCE_TASK m3_limo_driver[0] m3_limo_seq 		
						CLEAR_SEQUENCE_TASK m3_limo_seq
					ENDIF
				ENDIF
			ENDIF
			m3_cutscene_goals_flag = 5
		ENDIF
		
		IF m3_cutscene_goals_flag = 5 
			GOSUB m3_cameras

			IF timera > 4000
				SET_FIXED_CAMERA_POSITION 1026.3 -1123.6 24.3 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1023.6 -1123.8 24.3 JUMP_CUT	
				
				// Congratulations on your award, you must be thrilled!		 
				m3_speech_goals = 11
				m3_speech_control_flag = 0
				GOSUB m3_dialogue_setup
				m3_cutscene_goals_flag = 6	
			ENDIF
		ENDIF

		IF m3_cutscene_goals_flag = 6
			IF m3_speech_goals = 0  
				// Yeah, yeah, yeah, yeah, I'd like to thank my fans, my momma and my dealer.		 
				m3_speech_goals = 12
				m3_speech_control_flag = 0
				GOSUB m3_dialogue_setup
				
				IF NOT IS_CHAR_DEAD mc1 
					TASK_PLAY_ANIM mc1 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				ENDIF 
				m3_cutscene_goals_flag = 7
			ENDIF
		ENDIF

		IF m3_cutscene_goals_flag = 7
			GOSUB m3_cameras
			IF m3_speech_goals = 0 
				IF m3_speech_goals = 0 
					DO_FADE 500 FADE_OUT		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF

					CLEAR_AREA 1305.5 -1131.7 22.6 60.0 TRUE 
					LOAD_SCENE 1305.5 -1131.7 22.6

					
					SET_FIXED_CAMERA_POSITION 1288.6 -1146.2 28.9 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1301.0 -1137.2 23.3 JUMP_CUT

					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF
						
					//Hold position in the middle of the motorcade until we get to the Awards Show.		 
					m3_speech_goals = 8
					m3_speech_control_flag = 2
					m3_random_last_label = 4
					GOSUB m3_dialogue_setup

					m3_cutscene_goals_flag = 8
				ENDIF
			ENDIF
		ENDIF

		IF m3_cutscene_goals_flag = 8
			IF m3_speech_goals = 0 
			//LOAD_SCENE 1317.7 -1145.3 22.9
				
				m3_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF
				
				//if cutscene has been skipped
				IF m3_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					m3_speech_goals = 0
					
					DO_FADE 500 FADE_OUT		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF
				
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[0] FALSE
					IF IS_CHAR_DEAD m3_dgcar1_driver[0]
						CLEAR_AREA 1303.5 -1131.5 22.6 5.0 TRUE 
						CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1303.5 -1131.5 22.6 m3_dgcar1_driver[0]
						SET_CHAR_HEADING m3_dgcar1_driver[0] 168.1
						GIVE_WEAPON_TO_CHAR m3_dgcar1_driver[0] WEAPONTYPE_PISTOL 3000
					ENDIF

					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0] 
						IF NOT IS_CHAR_IN_CAR m3_dgcar1_driver[0] m3_dgcar1[0]
							WARP_CHAR_INTO_CAR m3_dgcar1_driver[0] m3_dgcar1[0] 
						ENDIF
					ENDIF 

					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION m3_dgcar1[2] FALSE
					IF IS_CHAR_DEAD m3_dgcar1_driver[2]
						GENERATE_RANDOM_INT_IN_RANGE 0 3 m3_cs
						CLEAR_AREA 1305.5 -1131.7 22.6 5.0 TRUE
						CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1305.5 -1131.7 22.6 m3_dgcar1_driver[2]
						SET_CHAR_HEADING m3_dgcar1_driver[2] 168.1
						GIVE_WEAPON_TO_CHAR m3_dgcar1_driver[2] WEAPONTYPE_PISTOL 3000
						IF NOT IS_CHAR_IN_CAR m3_dgcar1_driver[2] m3_dgcar1[2]
							WARP_CHAR_INTO_CAR m3_dgcar1_driver[2] m3_dgcar1[2] 
						ENDIF  
					ENDIF
				
					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2] 
						IF NOT IS_CHAR_IN_CAR m3_dgcar1_driver[2] m3_dgcar1[2]
							WARP_CHAR_INTO_CAR m3_dgcar1_driver[2] m3_dgcar1[2] 
						ENDIF
					ENDIF 
				
					CLEAR_AREA 1306.4 -1137.5 22.5 5.0 TRUE
					SET_CAR_COORDINATES m3_dgcar1[1] 1306.4 -1137.5 22.5
					SET_CAR_HEADING m3_dgcar1[1] 90.0

					REQUEST_MODEL BMYRI
					REQUEST_MODEL WFYRI
					REQUEST_MODEL CAMERA
					REQUEST_MODEL STRETCH
					REQUEST_MODEL LAPD1
					
					LOAD_ALL_MODELS_NOW
					
					WHILE has_crowd_been_created = 0
						WAIT 0
						GOSUB m3_creating_crowd
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF

					SET_TIME_OF_DAY 22 00
				ENDIF
				

				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[0] TRUE
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[1] TRUE
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[2] TRUE

				DELETE_CHAR mc1
				DELETE_CHAR wc1
				DELETE_CHAR m3_interviewer 
				DELETE_CHAR m3_limo_driver[0]
				DELETE_CHAR m3_limo_driver[1]
				DELETE_CAR m3_limo[0]
				DELETE_CAR m3_limo[1]

				MARK_MODEL_AS_NO_LONGER_NEEDED BMYRI
				MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
				MARK_MODEL_AS_NO_LONGER_NEEDED STRETCH

				LOCK_CAR_DOORS m3_dgcar1[1] CARLOCK_LOCKED
			
				SHUT_ALL_CHARS_UP FALSE
					
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				//CREATE_CHECKPOINT CHECKPOINT_TORUS_UPDOWN 1306.4 -1137.5 22.5 1306.4 -1137.5 27.5 5.0 m3_checkpoint 
				
				//PRINT_NOW ( STP3_44 ) 3000 1 //OTHER LIMO DRIVER: Right, stick close and let's roll
				PRINT ( STP3_06 ) 7000 1 //Follow the other cars in a convoy until you reach the awards ceremony. 

				//if cutscene has been skipped
				IF m3_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
					TASK_CAR_DRIVE_TO_COORD m3_dgcar1_driver[0] m3_dgcar1[0] 1015.1 -1139.2 22.7 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS //front car
				ENDIF
				
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2] 
					SET_CAR_FOLLOW_CAR m3_dgcar1[2] m3_dgcar1[0] 27.0
					SET_CAR_CRUISE_SPEED m3_dgcar1[2] 15.0 
					SET_CAR_DRIVING_STYLE m3_dgcar1[2] DRIVINGMODE_SLOWDOWNFORCARS 
				ENDIF

				m3_speech_flag = 0
				m3_control_flag = 0
				m3_goals = 3
			ENDIF
		ENDIF
	ENDIF		
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Waiting for the cars to arrive at the awards////////////////////////////////////////////////////

	//waiting for cars to arrive at awards 
	IF m3_goals = 3 

		

		IF m3_speech_flag = 0
			IF LOCATE_CAR_2D m3_dgcar1[0] 1094.8 -1142.6 3.0 3.0 FALSE
				//Keep frosty, guys.			
				m3_speech_goals = 8
				m3_speech_control_flag = 4
				m3_random_last_label = 5
				GOSUB m3_dialogue_setup

				m3_speech_flag = 1
			ENDIF
		ENDIF
		
		IF m3_control_flag = 0
			IF LOCATE_CAR_2D m3_dgcar1[0] 1015.1 -1139.2 3.0 3.0 FALSE
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS m3_dgcar1[0] 0.0 -12.0 0.0 m3_x m3_y z					
				REMOVE_BLIP m3_docg_manager_blip 
				ADD_BLIP_FOR_COORD m3_x m3_y m3_z m3_docg_manager_blip
				m3_control_flag = 1
			ENDIF
		ENDIF
		IF m3_control_flag = 1 
			IF LOCATE_CAR_2D m3_dgcar1[1] m3_x m3_y 9.0 9.0 FALSE 
				PRINT_NOW ( STP3_32 ) 7000 1 //Stop the car here.
			ELSE
				CLEAR_PRINTS
			ENDIF	
			
			IF LOCATE_STOPPED_CAR_2D m3_dgcar1[1] m3_x m3_y 10.0 10.0 FALSE
			AND IS_CHAR_SITTING_IN_CAR scplayer m3_dgcar1[1] 
				REMOVE_BLIP m3_docg_manager_blip 
				//PRINT_NOW ( STP3_12 ) 7000 1 //Wait for Doc G to get into your vehicle.
				CLEAR_PRINTS
				m3_control_flag = 0
				IF m3_shit_hit_fan = 0
					m3_speech_flag = 0
					m3_goals = 4
				ENDIF
			ENDIF
		ENDIF
												   
		//creating the checkpoint you have to stay in
		//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS m3_dgcar1[0] 0.0 -12.0 0.0 m3_x m3_y z
		//SET_CHECKPOINT_COORDS m3_checkpoint m3_x m3_y z
		
		//failing
		GOSUB m3_stray_timer

		IF NOT IS_CAR_DEAD m3_dgcar1[1] 
			IF NOT IS_CHAR_IN_CAR scplayer m3_dgcar1[1]   
				CLEAR_PRINTS
				PRINT_NOW ( STP3_12 ) 4000 1 //You left the car and alerted the other drivers!
				GOTO mission_music3_failed
			ENDIF
		ENDIF

		GOSUB m3_damaging_other_cars


		///// MISC - CHECKING NO-ONE HAS DIED AT CEREMONY
		IF has_crowd_been_created = 1
			GOSUB m3_crowd_death_checks
			IF m3_shit_hit_fan = 4
				CLEAR_PRINTS
				PRINT_NOW ( STP3_24 ) 4000 1 //Doc G's Manager has been spooked and refuses to come out!
				GOTO mission_music3_failed
			ENDIF
		ENDIF
	ENDIF
		
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////GOAL 1 - Waiting for Doc G to enter the car and telling the cars to start heading to the mansion///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF m3_goals = 4
		IF m3_control_flag = 0
			CLEAR_PRINTS
			
			DO_FADE 500 FADE_OUT		
			WHILE GET_FADING_STATUS	
			    WAIT 0
			ENDWHILE 
			GOSUB m3_death_checks
			IF m3_deathcheck_flag = 1
				GOTO mission_music3_failed
			ENDIF
			
			CLEAR_MISSION_AUDIO 1	 
			CLEAR_MISSION_AUDIO 2
			m3_speech_goals = 0
		
			SHUT_ALL_CHARS_UP TRUE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
			
			SET_RADIO_CHANNEL RS_OFF
				
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		
			REQUEST_MODEL BMYRI
			REQUEST_MODEL HFYRI

			LOAD_ALL_MODELS_NOW		
		
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE m3_empty_ped_decision_maker EVENT_POTENTIAL_WALK_INTO_PED

			CLEAR_AREA 1021.0 -1122.0 22.9 50.0 TRUE
			
			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1021.0 -1122.0 22.9 m3_goons[0]
			SET_CHAR_HEADING m3_goons[0] 180.0  
			SET_ANIM_GROUP_FOR_CHAR m3_goons[0] gang2
			SET_CHAR_DECISION_MAKER m3_goons[0] m3_empty_ped_decision_maker 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[0] TRUE
			GIVE_WEAPON_TO_CHAR m3_goons[0] WEAPONTYPE_PISTOL 3000
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 120
				TASK_GO_STRAIGHT_TO_COORD -1 1021.0 -1133.7 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[0] -1 0
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[0] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq

			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1022.2 -1122.0 22.9 m3_goons[1]
			SET_CHAR_HEADING m3_goons[1] 180.0  
			SET_ANIM_GROUP_FOR_CHAR m3_goons[0] gang1
			SET_CHAR_DECISION_MAKER m3_goons[1] m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[1] TRUE 
			GIVE_WEAPON_TO_CHAR m3_goons[1] WEAPONTYPE_PISTOL 3000
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 100
				TASK_GO_STRAIGHT_TO_COORD -1 1022.2 -1133.7 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[0] -1 1
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[1] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq

			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1023.4 -1122.0 22.9 m3_goons[2]
			GIVE_WEAPON_TO_CHAR m3_goons[2] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_goons[2] 180.0  
			SET_CHAR_DECISION_MAKER m3_goons[2] m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[2] TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 100
				TASK_GO_STRAIGHT_TO_COORD -1 1023.4 -1133.7 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[0] -1 2
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[2] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq
			
			//docg
			CREATE_CHAR PEDTYPE_GANG9 BMYRI 1021.6 -1123.2 22.9 docg_manager	
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH docg_manager TRUE 
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR docg_manager FALSE
			SET_ANIM_GROUP_FOR_CHAR m3_goons[0] gang2
			SET_CHAR_HEADING docg_manager 187.9							
			SET_CHAR_DECISION_MAKER docg_manager m3_empty_ped_decision_maker
			REMOVE_BLIP m3_docg_manager_blip
			//ADD_BLIP_FOR_CHAR docg_manager m3_docg_manager_blip
			SET_CHAR_FORCE_DIE_IN_CAR docg_manager TRUE 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER docg_manager TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 100
				TASK_GO_STRAIGHT_TO_COORD -1 1021.6 -1134.9 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[1] -1 1
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK docg_manager m3_seq
			CLEAR_SEQUENCE_TASK m3_seq
					
			//hoochie
			CREATE_CHAR PEDTYPE_GANG9 HFYRI 1022.8 -1123.2 22.9 docg_hoochie
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR docg_hoochie FALSE
			SET_CHAR_HEADING docg_hoochie 187.9
			SET_CHAR_DECISION_MAKER docg_hoochie m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER docg_hoochie TRUE 
			SET_CHAR_FORCE_DIE_IN_CAR docg_hoochie TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 100
				TASK_GO_STRAIGHT_TO_COORD -1 1022.8 -1134.9 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[1] -1 2
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK docg_hoochie m3_seq
			CLEAR_SEQUENCE_TASK m3_seq
			TASK_LOOK_AT_CHAR scplayer docg_hoochie -2 
		
			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1022.2 -1124.4 22.9 m3_goons[4]
			GIVE_WEAPON_TO_CHAR m3_goons[4] WEAPONTYPE_PISTOL 3000
			SET_ANIM_GROUP_FOR_CHAR m3_goons[0] gang1
			SET_CHAR_HEADING m3_goons[4] 180.0  
			SET_CHAR_DECISION_MAKER m3_goons[4] m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[4] TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 50
				TASK_GO_STRAIGHT_TO_COORD -1 1022.2 -1136.1 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[2] -1 0
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[4] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq

			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1023.4 -1124.4 22.9 m3_goons[5]
			GIVE_WEAPON_TO_CHAR m3_goons[5] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_goons[5] 180.0  
			SET_CHAR_DECISION_MAKER m3_goons[5] m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[5] TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_PAUSE -1 50
				TASK_GO_STRAIGHT_TO_COORD -1 1023.4 -1136.1 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[2] -1 1
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[5] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq
			
			CREATE_CHAR PEDTYPE_MISSION2 WBDYG1 1021.4 -1125.6 22.9 m3_goons[6]
			GIVE_WEAPON_TO_CHAR m3_goons[6] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_goons[6] 180.0
			SET_CHAR_DECISION_MAKER m3_goons[6] m3_empty_ped_decision_maker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER m3_goons[6] TRUE 
			OPEN_SEQUENCE_TASK m3_seq	  
				TASK_GO_STRAIGHT_TO_COORD -1 1021.4 -1137.3 22.9 PEDMOVE_WALK -1 
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_PASSENGER -1 m3_dgcar1[2] -1 2
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK m3_goons[6] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq

			IF NOT IS_CAR_DEAD m3_dgcar1[0] 
				CLEAR_AREA 1015.1 -1139.2 22.9 50.0 TRUE
				SET_CAR_COORDINATES m3_dgcar1[0] 1015.1 -1139.2 22.9
				SET_CAR_HEADING m3_dgcar1[0] 90.0
			ENDIF
			  
			IF NOT IS_CAR_DEAD m3_dgcar1[1] 
				CLEAR_AREA 1025.1 -1139.2 22.9 50.0 TRUE
				SET_CAR_COORDINATES m3_dgcar1[1] 1025.1 -1139.2 22.9
				SET_CAR_HEADING m3_dgcar1[1] 90.0
			ENDIF

			IF NOT IS_CAR_DEAD m3_dgcar1[2] 
				CLEAR_AREA 1035.1 -1139.2 22.9 50.0 TRUE
				SET_CAR_COORDINATES m3_dgcar1[2] 1035.1 -1139.2 22.9
				SET_CAR_HEADING m3_dgcar1[2] 90.0
			ENDIF

			//DELETE_CHECKPOINT m3_checkpoint
			LOCK_CAR_DOORS m3_dgcar1[1] CARLOCK_UNLOCKED

			SET_FIXED_CAMERA_POSITION 1021.4 -1135.3 23.5 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1021.7 -1124.1 25.2 JUMP_CUT 
		
			DO_FADE 500 FADE_IN		
			WHILE GET_FADING_STATUS	
			    WAIT 0
			ENDWHILE 
			GOSUB m3_death_checks
			IF m3_deathcheck_flag = 1
				GOTO mission_music3_failed
			ENDIF

			m3_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			
			m3_control_flag = 1
		ENDIF

		IF m3_control_flag = 1 
			IF NOT IS_CHAR_DEAD docg_manager 	
				IF IS_CHAR_IN_AREA_2D docg_manager 1019.7 -1130.2 1025.7 -1135.0 FALSE 
					SET_FIXED_CAMERA_POSITION 1024.7 -1141.7 24.0 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1024.6 -1138.7 23.9 JUMP_CUT
					m3_control_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		IF m3_control_flag = 2
			//giving orders and weapons to goons
			IF NOT IS_CAR_DEAD m3_dgcar1[1] 
				IF NOT IS_CHAR_DEAD docg_manager 
					IF IS_CHAR_IN_CAR docg_manager m3_dgcar1[1] 
						
						SET_RADIO_CHANNEL RS_NEW_JACK_SWING
						
						//doc g manager talking to player at awards ceremony
						m3_speech_goals = 2
						m3_speech_control_flag = 0
						GOSUB m3_dialogue_setup 	
						
						timera = 0 
						m3_control_flag = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
			
		IF m3_control_flag = 3 
			IF m3_speech_goals = 0
				PLAY_MISSION_AUDIO 3
				m3_control_flag = 4
			ENDIF
		ENDIF
		
		IF m3_control_flag = 4 
			IF HAS_MISSION_AUDIO_FINISHED 3	 
				m3_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB m3_death_checks
				IF m3_deathcheck_flag = 1
					GOTO mission_music3_failed
				ENDIF
			
				//if cutscene has been skipped
				IF m3_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1	 
					CLEAR_MISSION_AUDIO 2
					m3_speech_goals = 0
				
					DO_FADE 500 FADE_OUT		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF
				  
					//CLEAR_CHAR_TASKS scplayer 
					IF NOT IS_CAR_DEAD m3_dgcar1[0]
						IF NOT IS_CHAR_DEAD m3_goons[0]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[0]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[0] m3_dgcar1[0] 0
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD m3_goons[1]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[1]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[1] m3_dgcar1[0] 1
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD m3_goons[2]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[2]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[2] m3_dgcar1[0] 2
							ENDIF
						ENDIF
					ENDIF   
					IF NOT IS_CAR_DEAD m3_dgcar1[1]
						/*
						IF NOT IS_CHAR_DEAD m3_goons[3]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[3]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[3] m3_dgcar1[1] 0
							ENDIF
						ENDIF
						*/
						IF NOT IS_CHAR_DEAD docg_manager
							IF NOT IS_CHAR_IN_ANY_CAR docg_manager
								WARP_CHAR_INTO_CAR_AS_PASSENGER docg_manager m3_dgcar1[1] 1
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD docg_hoochie
							IF NOT IS_CHAR_IN_ANY_CAR docg_hoochie
								WARP_CHAR_INTO_CAR_AS_PASSENGER docg_hoochie m3_dgcar1[1] 2
							ENDIF
						ENDIF
					ENDIF   
					IF NOT IS_CAR_DEAD m3_dgcar1[2]
						IF NOT IS_CHAR_DEAD m3_goons[4]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[4]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[4] m3_dgcar1[2] 0
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD m3_goons[5]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[5]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[5] m3_dgcar1[2] 1
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD m3_goons[6]
							IF NOT IS_CHAR_IN_ANY_CAR m3_goons[6]
								WARP_CHAR_INTO_CAR_AS_PASSENGER m3_goons[6] m3_dgcar1[2] 2
							ENDIF
						ENDIF
					ENDIF 
				ENDIF  

				IF NOT IS_CAR_DEAD m3_dgcar1[0]
					IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]  
						OPEN_SEQUENCE_TASK m3_seq	  
							TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[0] 964.6 -1055.1 27.6 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS   
							TASK_CAR_DRIVE_TO_COORD -1 m3_dgcar1[0] 965.0 -1036.4 29.0 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS   
						CLOSE_SEQUENCE_TASK m3_seq
						PERFORM_SEQUENCE_TASK m3_dgcar1_driver[0] m3_seq
						CLEAR_SEQUENCE_TASK m3_seq
					ENDIF
				ENDIF

				CLEAR_LOOK_AT scplayer 
				CLEAR_PRINTS 
				PRINT ( STP3_08 ) 7000 1 //There is a pier to the South that you can dump the car off.
				PRINT ( STP3_09 ) 7000 1 //Dump the car in the water but make sure no-one sees you!
				REMOVE_BLIP m3_docg_manager_blip 
				ADD_BLIP_FOR_COORD 836.9 -2041.5 11.8 m3_docg_manager_blip 

				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE m3_empty_ped_decision_maker EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 0.0 100.0 0.0 0.0 FALSE TRUE 	
			
				LOCK_CAR_DOORS m3_dgcar1[1] CARLOCK_LOCKED

				SHUT_ALL_CHARS_UP FALSE
					
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON 	
			
				IF m3_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN		
					WHILE GET_FADING_STATUS	
					    WAIT 0
					ENDWHILE 
					GOSUB m3_death_checks
					IF m3_deathcheck_flag = 1
						GOTO mission_music3_failed
					ENDIF
				ENDIF
				timera = 0
				m3_control_flag = 0
				m3_goals = 5
			ENDIF
		ENDIF									 
	
		GOSUB m3_cameras

	ENDIF										 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////GOAL 2 - Drowning Doc G's Manager//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF m3_goals = 5
		
		/*
		IF NOT IS_CAR_DEAD m3_dgcar1[1]
			IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 836.0 -2036.0 11.6 5.0 5.0 5.0 TRUE
				ENDIF
			ENDIF
		ENDIF
		*/
		////////////////////////////////////////////////////////////
		/// controlling the body guards ////////////////////////////
		////////////////////////////////////////////////////////////
		GOSUB m3_controlling_enemy_cars
			
		
		IF m3_control_flag = 0
			//speech for this section
			IF m3_speech_flag = 0
				IF timera > 14000
					//radio message informing guards that the manager has been swiped
					m3_speech_goals = 3
					m3_speech_control_flag = 0
					GOSUB m3_dialogue_setup 
					m3_speech_flag = 1
				ENDIF
			ENDIF
			
			IF m3_speech_flag = 1 
				IF m3_speech_goals = 0 
					//Doc G's Manager pleading for his life part 1
					m3_speech_goals = 4
					m3_speech_control_flag = 0
					GOSUB m3_dialogue_setup 
					m3_speech_flag = 2
				ENDIF
			ENDIF
			
			IF m3_speech_flag = 2 
				IF m3_speech_goals = 0 
					//Doc G's Manager pleading for his life part 2
					m3_speech_goals = 5
					m3_speech_control_flag = 0
					GOSUB m3_dialogue_setup 
					m3_speech_flag = 3
				ENDIF
			ENDIF

			IF m3_speech_flag = 3 
				IF IS_CHAR_IN_AREA_3D scplayer 789.4 -1798.8 10.0 896.9 -1763.6 20.0 FALSE 
					IF m3_speech_goals = 5 
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						m3_speech_goals = 0
						CLEAR_PRINTS
						
						//Doc G's Manager pleading for his life part 2
						m3_speech_goals = 10
						m3_speech_control_flag = 0
						GOSUB m3_dialogue_setup 
						m3_speech_flag = 4

						IF NOT IS_CHAR_DEAD docg_manager 
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH docg_manager FALSE
						ENDIF
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					ENDIF
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD docg_hoochie  
				IF NOT IS_CHAR_TALKING docg_hoochie
					IF m3_speech_flag = 0
						IF timera < 10000
							SET_CHAR_SAY_CONTEXT docg_hoochie CONTEXT_GLOBAL_PAIN_ON_FIRE m3_context
						ENDIF
					ENDIF
					
					IF m3_speech_flag = 3
					OR m3_speech_flag = 4
						SET_CHAR_SAY_CONTEXT docg_hoochie CONTEXT_GLOBAL_PAIN_ON_FIRE m3_context
					ENDIF
				ENDIF
			ENDIF

		ENDIF
		
		
		////////////////////////////////////////////////////////////
		///waiting for player to dump the enemy car in the water!///
		////////////////////////////////////////////////////////////
		IF m3_control_flag = 0
			IF NOT IS_CAR_DEAD m3_dgcar1[1]
				IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 

					IF m3_help_flag = 0 
						IF IS_CHAR_IN_AREA_3D scplayer 853.7 -1810.1 10.0 818.5 -1874.0 20.0 FALSE
							PRINT_NOW STP3_35 7000 1
							m3_help_flag = 1
						ENDIF
					ENDIF
					IF m3_help_flag = 1 
						GET_CAR_SPEED m3_dgcar1[1] m3_car_speed 
						IF m3_car_speed > 10.0
							IF IS_CHAR_IN_AREA_3D scplayer 820.0 -2004.0 10.0 852.4 -2068.0 20.0 FALSE
								PRINT_NOW STP3_34 7000 1
								m3_help_flag = 2
							ENDIF		   	
						ENDIF
					ENDIF

				ENDIF
			ENDIF

		

			//checking to see if the car has ended off the end of the pier
			IF NOT IS_CAR_DEAD m3_dgcar1[1]
				IF NOT IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
					//going off the end of the pier
					IF IS_CAR_IN_AREA_3D m3_dgcar1[1] 855.6 -2067.9 5.0 812.6 -2085.9 20.0 FALSE  
						SET_TIME_SCALE 0.3
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						m3_speech_goals = 0
						CLEAR_PRINTS
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON
						MAKE_PLAYER_GANG_DISAPPEAR
						SET_FIXED_CAMERA_POSITION 813.5 -2128.9 2.2 0.0 0.0 0.0	
					 	POINT_CAMERA_AT_POINT 847.6 -2068.1 11.7 JUMP_CUT
						GOSUB m3_baddy_close_check
						m3_speech_flag = 0
						timera = 0
						m3_control_flag = 1
					ENDIF
				
				ENDIF
		   ENDIF

			//removing blip if car is in water
			IF IS_CAR_IN_WATER m3_dgcar1[1]
				REMOVE_BLIP m3_docg_manager_blip 
				GOSUB m3_baddy_close_check
				m3_speech_goals = 0
				m3_control_flag = 2
			ENDIF
		ENDIF

		IF m3_control_flag = 1
			IF m3_speech_flag = 0
				//doc g screaming holy fuck
				m3_speech_goals = 6
				m3_speech_control_flag = 0
				GOSUB m3_dialogue_setup 
				m3_speech_flag = 1	
			ENDIF

			IF IS_CAR_IN_WATER m3_dgcar1[1] 	
			OR timera > 10000
				SET_TIME_SCALE 1.0
				timera = 0 
				m3_control_flag = 2
			ENDIF
		ENDIF

		IF m3_control_flag = 2
			IF timera > 500 
				IF m3_speech_goals = 0
					IF m3_baddy_in_range_check = 0
						timera = 0
						m3_goals = 7
					ELSE
						SET_PLAYER_CONTROL player1 ON
						MAKE_PLAYER_GANG_REAPPEAR
						SWITCH_WIDESCREEN OFF
						SET_CAMERA_BEHIND_PLAYER
						RESTORE_CAMERA_JUMPCUT
						m3_control_flag = 0
						timerb = 0 
						m3_goals = 6
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		////////////////////////////////////////////////////////////
		///////////////////MISC SHIT////////////////////////////////
		////////////////////////////////////////////////////////////
		//clearing help 
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE 
		OR timera > 11000
			CLEAR_HELP
		ENDIF
	
		//crowd checks
		IF m3_shit_hit_fan < 4
			GOSUB m3_crowd_death_checks
		ENDIF
		
		//removing ceremony crowd 
		IF m3_removing_crowd < 3
			GOSUB m3_removing_crowd_after_ceremony
		ENDIF
				
		//failin
		IF m3_control_flag = 0
			IF NOT IS_CAR_IN_WATER m3_dgcar1[1]
				IF IS_CHAR_DEAD docg_manager 
					CLEAR_PRINTS
					PRINT_NOW ( STP3_31 ) 4000 1 //You didn't dump the car in the water!
					GOTO mission_music3_failed
				ENDIF

				IF NOT IS_CAR_DEAD m3_dgcar1[1] 
					IF NOT IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
						IF IS_CAR_STOPPED m3_dgcar1[1]
							TASK_LEAVE_CAR_AND_FLEE docg_manager m3_dgcar1[1] 2316.4 -1519.2 24.3
							CLEAR_PRINTS
							PRINT_NOW ( STP3_18 ) 4000 1 //You let Doc G's Manager escape!
							GOTO mission_music3_failed
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF 
		
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////// Manager is dead, waiting to kill bodyguards //////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF m3_goals = 6

		GOSUB m3_controlling_enemy_cars

		IF m3_control_flag = 0
			IF m3_baddy_in_range_check = 0
				timera = 0
				m3_goals = 7
			ELSE
				REMOVE_BLIP m3_docg_manager_blip
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
					ADD_BLIP_FOR_CHAR m3_dgcar1_driver[0] m3_goon_blips[0]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
					ADD_BLIP_FOR_CHAR m3_dgcar1_driver[2] m3_goon_blips[1]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[0]
					ADD_BLIP_FOR_CHAR m3_goons[0] m3_goon_blips[2]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[1]
					ADD_BLIP_FOR_CHAR m3_goons[1] m3_goon_blips[3]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[2]
					ADD_BLIP_FOR_CHAR m3_goons[2] m3_goon_blips[4]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[4]
					ADD_BLIP_FOR_CHAR m3_goons[4] m3_goon_blips[5]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[5]
					ADD_BLIP_FOR_CHAR m3_goons[5] m3_goon_blips[6]  
				ENDIF 			
				IF NOT IS_CHAR_DEAD m3_goons[6]
					ADD_BLIP_FOR_CHAR m3_goons[6] m3_goon_blips[7]  
				ENDIF 
				PRINT_NOW ( STP3_26 ) 11000 1 //One of the other drivers saw you dispose of Doc G's Manager.  Kill them!
				m3_control_flag = 1
			ENDIF			
		ENDIF 
		IF m3_control_flag = 1
			IF IS_CHAR_DEAD m3_dgcar1_driver[0]
				REMOVE_BLIP m3_goon_blips[0]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_dgcar1_driver[2]
				REMOVE_BLIP m3_goon_blips[1]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[0]
				REMOVE_BLIP m3_goon_blips[2]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[1]
				REMOVE_BLIP m3_goon_blips[3]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[2]
				REMOVE_BLIP m3_goon_blips[4]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[4]
				REMOVE_BLIP m3_goon_blips[5]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[5]
				REMOVE_BLIP m3_goon_blips[6]  
			ENDIF 			
			IF IS_CHAR_DEAD m3_goons[6]
				REMOVE_BLIP m3_goon_blips[7]  
			ENDIF 
		 
			IF IS_CHAR_DEAD m3_dgcar1_driver[0]
				IF IS_CHAR_DEAD m3_dgcar1_driver[2]
					IF IS_CHAR_DEAD m3_goons[0]
						IF IS_CHAR_DEAD m3_goons[1]
							IF IS_CHAR_DEAD m3_goons[2]
								IF IS_CHAR_DEAD m3_goons[4]
									IF IS_CHAR_DEAD m3_goons[5]
										IF IS_CHAR_DEAD m3_goons[6]
											timera = 2000
											m3_goals = 7
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
	
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////GOAL 3 - Waiting for doc g to drown////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF m3_goals = 7
		IF timera > 3000	
			SET_PLAYER_CONTROL player1 ON
			MAKE_PLAYER_GANG_REAPPEAR
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			GOTO mission_music3_passed
		ENDIF
	ENDIF 

	




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////// MISC /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//prompting player that he doesn't have long left
	IF m3_warning_text = 0
		IF m3_goals = 0
		OR m3_goals = 1
			IF m3_hours = 21
				IF minutes > 30
					PRINT ( STP3_28 ) 7000 1 //Hurry up, you have to meet the other drivers before 10pm!
					m3_warning_text = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	//ingame dialogue
	GOSUB m3_overall_dialogue


GOTO mission_music3_loop



mission_music3_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

mission_music3_passed:
flag_strap_mission_counter ++
CLEAR_PRINTS
SET_INT_STAT PASSED_STRAP3 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
REGISTER_MISSION_PASSED STRAP_3
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REMOVE_BLIP strap_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
PLAYER_MADE_PROGRESS 1
RETURN

mission_cleanup_music3:

SWITCH_AUDIO_ZONE AWARDS FALSE
SET_TIME_SCALE 1.0
//SET_CAMERA_BEHIND_PLAYER
//SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
REMOVE_BLIP m3_docg_manager_blip 
//DELETE_CHECKPOINT m3_checkpoint
REMOVE_BLIP spray_shop2 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
REMOVE_CHAR_ELEGANTLY docg_manager 
REMOVE_CHAR_ELEGANTLY docg_hoochie
IF NOT IS_CAR_DEAD m3_dgcar1[0] 
	IMPROVE_CAR_BY_CHEATING m3_dgcar1[0] FALSE
ENDIF
IF NOT IS_CAR_DEAD m3_dgcar1[2] 
	IMPROVE_CAR_BY_CHEATING m3_dgcar1[2] FALSE
ENDIF
IF NOT IS_CAR_DEAD m3_dgcar1[1] 
	LOCK_CAR_DOORS m3_dgcar1[1] CARLOCK_UNLOCKED
ENDIF
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG1
MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
MARK_MODEL_AS_NO_LONGER_NEEDED BMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
MARK_MODEL_AS_NO_LONGER_NEEDED BMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED HFYRI
MARK_MODEL_AS_NO_LONGER_NEEDED STRETCH
MARK_MODEL_AS_NO_LONGER_NEEDED ELEGANT
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_ROADBARRIER_2
MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
UNMARK_ALL_ROAD_NODES_AS_DONT_WANDER
SWITCH_ROADS_BACK_TO_ORIGINAL 802.1 -1150.2 10.0 1156.9 -1131.2 50.0
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 802.1 -1150.2 10.0 1156.9 -1131.2 50.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1053.2 -1146.1 10.0 1072.5 -1215.5 50.0 
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1053.2 -1146.1 10.0 1072.5 -1215.5 50.0
SWITCH_ROADS_BACK_TO_ORIGINAL 932.9 -1154.4 10.0 951.8 -1215.4 50.0 
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 932.9 -1154.4 10.0 951.8 -1215.4 50.0
SWITCH_ROADS_BACK_TO_ORIGINAL 972.1 -1135.4 10.0 952.9 -1044.3 50.0 
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 972.1 -1135.4 10.0 952.9 -1044.3 50.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1072.8 -1135.0 10.0 1092.4 -1045.5 50.0 
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1072.8 -1135.0 10.0 1092.4 -1045.5 50.0
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

///////////////////////////////////////////////////////////////////////////////////////////
m3_death_checks:///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_goals < 5
	IF IS_CAR_DEAD m3_dgcar1[1]
		CLEAR_PRINTS
		PRINT_NOW ( STP3_10 ) 4000 1 //The convoy vehicle has been destroyed!	
		m3_deathcheck_flag = 1	
	ENDIF
ENDIF


IF m3_goals < 5
	IF IS_CAR_DEAD m3_dgcar1[0]
		CLEAR_PRINTS
		PRINT_NOW ( STP3_10 ) 4000 1 //The convoy vehicle has been destroyed!	
		m3_deathcheck_flag = 1	
	ENDIF

	IF IS_CAR_DEAD m3_dgcar1[2]
		CLEAR_PRINTS
		PRINT_NOW ( STP3_10 ) 4000 1 //The convoy vehicle has been destroyed!	
		m3_deathcheck_flag = 1	
	ENDIF
ENDIF

IF m3_goals = 3
	IF m3_control_flag < 4
		IF IS_CHAR_DEAD m3_cop[8]
			CLEAR_PRINTS
			PRINT_NOW ( STP3_16 ) 4000 1 //You killed a cop and alerted the other drivers!	
			m3_deathcheck_flag = 1	
		ENDIF
	ENDIF
ENDIF

IF m3_goals > 2
	IF m3_goals < 5
		IF IS_CHAR_DEAD m3_dgcar1_driver[0] 
			CLEAR_PRINTS
			PRINT_NOW ( STP3_17 ) 4000 1 //You killed one of the other drivers!	
			m3_deathcheck_flag = 1	
		ENDIF

		IF IS_CHAR_DEAD m3_dgcar1_driver[2] 
			CLEAR_PRINTS
			PRINT_NOW ( STP3_17 ) 4000 1 //You killed one of the other drivers!	
			m3_deathcheck_flag = 1	
		ENDIF
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_creating_crowd://///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
	IF creating_crowd_flag < 20
		IF creating_crowd_flag = 0
			//stars
			CREATE_CHAR PEDTYPE_CIVMALE BMYRI 1023.4 -1123.6 22.9 mc1
			SET_CHAR_HEADING mc1 267.1 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG mc1 TRUE
			SET_CHAR_DECISION_MAKER mc1 m3_empty_ped_decision_maker

			CREATE_CHAR PEDTYPE_CIVFEMALE WFYRI 1023.1 -1124.6 22.9 wc1
			SET_CHAR_HEADING wc1 296.3 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG wc1 TRUE
			SET_CHAR_DECISION_MAKER wc1 m3_empty_ped_decision_maker
			
			//interviewers
			CREATE_CHAR PEDTYPE_CIVFEMALE WFYRI 1025.0 -1124.6 22.9 m3_interviewer
			SET_CHAR_HEADING m3_interviewer 90.0 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_interviewer TRUE
			SET_CHAR_DECISION_MAKER m3_interviewer m3_empty_ped_decision_maker
			//creating barriers around the roads to stop player entering and switching off nodes
	
			//EAST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 1092.7 -1144.2 22.7 m3_east_barrier1
			SET_OBJECT_HEADING m3_east_barrier1 0.0 
			SET_OBJECT_COLLISION m3_east_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 1092.7 -1148.8 22.7 m3_east_barrier2
			SET_OBJECT_HEADING m3_east_barrier2 90.0 
			SET_OBJECT_COLLISION m3_east_barrier2 TRUE

			SWITCH_ROADS_OFF 802.1 -1150.2 10.0 1156.9 -1131.2 50.0
			SWITCH_PED_ROADS_OFF 802.1 -1150.2 10.0 1156.9 -1131.2 50.0

			//SOUTH EAST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 1062.5 -1159.4 22.6 m3_south_east_barrier1
			SET_OBJECT_HEADING m3_south_east_barrier1 180.0 
			SET_OBJECT_COLLISION m3_south_east_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 1054.4 -1159.4 22.6 m3_south_east_barrier2
			SET_OBJECT_HEADING m3_south_east_barrier2 180.0 
			SET_OBJECT_COLLISION m3_south_east_barrier2 TRUE

			SWITCH_ROADS_OFF 1053.2 -1146.1 10.0 1072.5 -1215.5 50.0 
			SWITCH_PED_ROADS_OFF 1053.2 -1146.1 10.0 1072.5 -1215.5 50.0

			//SOUTH WEST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 944.5 -1159.4 22.6 m3_south_west_barrier1
			SET_OBJECT_HEADING m3_south_west_barrier1 180.0 
			SET_OBJECT_COLLISION m3_south_west_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 940.0 -1159.4 22.6 m3_south_west_barrier2
			SET_OBJECT_HEADING m3_south_west_barrier2 180.0 
			SET_OBJECT_COLLISION m3_south_west_barrier2 TRUE

			SWITCH_ROADS_OFF 932.9 -1154.4 10.0 951.8 -1215.4 50.0 
			SWITCH_PED_ROADS_OFF 932.9 -1154.4 10.0 951.8 -1215.4 50.0
						 
			//WEST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 931.3 -1148.8 22.7 m3_west_barrier1
			SET_OBJECT_HEADING m3_west_barrier1 90.0 
			SET_OBJECT_COLLISION m3_west_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 931.3 -1139.9 22.7 m3_west_barrier2
			SET_OBJECT_HEADING m3_west_barrier2 90.0 
			SET_OBJECT_COLLISION m3_west_barrier2 TRUE

			//NORTH WEST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 960.2 -1130.4 22.7 m3_north_west_barrier1
			SET_OBJECT_HEADING m3_north_west_barrier1 180.0 
			SET_OBJECT_COLLISION m3_north_west_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 967.4 -1130.4 22.7 m3_north_west_barrier2
			SET_OBJECT_HEADING m3_north_west_barrier2 90.0 
			SET_OBJECT_COLLISION m3_north_west_barrier2 TRUE

			SWITCH_ROADS_OFF 972.1 -1135.4 10.0 952.9 -1044.3 50.0 
			SWITCH_PED_ROADS_OFF 972.1 -1135.4 10.0 952.9 -1044.3 50.0

			//NORTH EAST barriers
			CREATE_OBJECT DYN_ROADBARRIER_2 1080.6 -1130.7 22.7 m3_north_east_barrier1
			SET_OBJECT_HEADING m3_north_east_barrier1 180.0 
			SET_OBJECT_COLLISION m3_north_east_barrier1 TRUE

			CREATE_OBJECT DYN_ROADBARRIER_2 1085.5 -1130.6 22.7 m3_north_east_barrier2
			SET_OBJECT_HEADING m3_north_east_barrier2 180.0 
			SET_OBJECT_COLLISION m3_north_east_barrier2 TRUE

			SWITCH_ROADS_OFF 1072.8 -1135.0 10.0 1092.4 -1045.5 50.0 
			SWITCH_PED_ROADS_OFF 1072.8 -1135.0 10.0 1092.4 -1045.5 50.0

			//creating awards ceremony scene
			CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1123.2 22.9 right_barrier1
			SET_OBJECT_HEADING right_barrier1 90.0 
			SET_OBJECT_COLLISION right_barrier1 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1127.2 22.9 right_barrier2
			SET_OBJECT_HEADING right_barrier2 90.0 
			SET_OBJECT_COLLISION right_barrier2 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1024.5 -1131.2 22.9 right_barrier3
			SET_OBJECT_HEADING right_barrier3 90.0 
			SET_OBJECT_COLLISION right_barrier3 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1123.2 22.9 left_barrier1
			SET_OBJECT_HEADING left_barrier1 90.0 
			SET_OBJECT_COLLISION left_barrier1 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1127.2 22.9 left_barrier2
			SET_OBJECT_HEADING left_barrier2 90.0 
			SET_OBJECT_COLLISION left_barrier2 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1020.5 -1131.2 22.9 left_barrier3
			SET_OBJECT_HEADING left_barrier3 90.0 
			SET_OBJECT_COLLISION left_barrier3 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1016.5 -1133.2 22.9 front_left_barrier1
			SET_OBJECT_HEADING front_left_barrier1 180.0 
			SET_OBJECT_COLLISION front_left_barrier1 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1012.5 -1133.2 22.9 front_left_barrier2
			SET_OBJECT_HEADING front_left_barrier2 180.0 
			SET_OBJECT_COLLISION front_left_barrier2 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1008.5 -1133.2 22.9 front_left_barrier3
			SET_OBJECT_HEADING front_left_barrier3 180.0 
			SET_OBJECT_COLLISION front_left_barrier3 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1028.5 -1133.2 22.9 front_right_barrier1
			SET_OBJECT_HEADING front_right_barrier1 180.0 
			SET_OBJECT_COLLISION front_right_barrier1 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1032.5 -1133.2 22.9 front_right_barrier2
			SET_OBJECT_HEADING front_right_barrier2 180.0 
			SET_OBJECT_COLLISION front_right_barrier2 FALSE

			CREATE_OBJECT DYN_ROADBARRIER_2 1036.5 -1133.2 22.9 front_right_barrier3
			SET_OBJECT_HEADING front_right_barrier3 180.0 
			SET_OBJECT_COLLISION front_right_barrier3 FALSE

			//right barrier first gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1025.3 -1124.9 22.9 m3_cop[0]
			GIVE_WEAPON_TO_CHAR m3_cop[0] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[0] 276.6 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[0] TRUE 

			//right barrier second gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1024.7 -1129.1 22.9 m3_cop[1]
			GIVE_WEAPON_TO_CHAR m3_cop[1] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[1] 276.6 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[1] TRUE 

			//right barrier third gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1024.7 -1133.8 22.9 m3_cop[2]
			GIVE_WEAPON_TO_CHAR m3_cop[2] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[2] 276.6 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[2] TRUE 

			//left barrier first gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1020.4 -1125.2 22.9 m3_cop[3]
			GIVE_WEAPON_TO_CHAR m3_cop[3] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[3] 92.2 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[3] TRUE 

			//left barrier second gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1020.4 -1129.1 22.9 m3_cop[4]
			GIVE_WEAPON_TO_CHAR m3_cop[4] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[4] 92.2 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[4] TRUE 

			//left barrier third gap
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1020.4 -1133.5 22.9 m3_cop[5]
			GIVE_WEAPON_TO_CHAR m3_cop[5] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[5] 92.2 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[5] TRUE 

			//right emergency exit
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1032.6 -1115.5 22.9 m3_cop[6]
			GIVE_WEAPON_TO_CHAR m3_cop[6] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[6] 173.9 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[6] TRUE 

			//left emergency exit
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1012.0 -1115.8 22.9 m3_cop[7]
			GIVE_WEAPON_TO_CHAR m3_cop[7] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[7] 187.9 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[7] TRUE 

			//creating limos
			CREATE_CAR STRETCH 1031.4 -1138.6 22.7 m3_limo[0]
			CREATE_CHAR_INSIDE_CAR m3_limo[0] PEDTYPE_MISSION2 WBDYG1 m3_limo_driver[0]
			SET_CAR_HEADING m3_limo[0] 90.0  
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_limo_driver[0] TRUE

			OPEN_SEQUENCE_TASK m3_limo_seq
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_CAR_DRIVE_TO_COORD -1 m3_limo[0] 1023.3 -1138.6 22.7 15.0 MODE_ACCURATE 1 DRIVINGMODE_AVOIDCARS
			CLOSE_SEQUENCE_TASK m3_limo_seq
			PERFORM_SEQUENCE_TASK m3_limo_driver[0] m3_limo_seq	
			CLEAR_SEQUENCE_TASK m3_limo_seq

			CREATE_CAR STRETCH 1042.8 -1138.6 22.7 m3_limo[1]
			CREATE_CHAR_INSIDE_CAR m3_limo[1] PEDTYPE_MISSION2 WBDYG1 m3_limo_driver[1]
			SET_CAR_HEADING m3_limo[1] 90.0 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_limo_driver[1] TRUE

			//create LAPD1 to open barrier 
			CREATE_CHAR PEDTYPE_MISSION2 LAPD1 1093.7 -1145.2 22.7 m3_cop[8]
			GIVE_WEAPON_TO_CHAR m3_cop[8] WEAPONTYPE_PISTOL 3000
			SET_CHAR_HEADING m3_cop[8] 274.6 
			SET_LOAD_COLLISION_FOR_CHAR_FLAG m3_cop[8] TRUE
			SET_CHAR_DECISION_MAKER m3_cop[8] m3_empty_ped_decision_maker

			//creating a dummy target for the photographers to shoot
			CREATE_RANDOM_CHAR 1023.6 -1122.3 22.9 m3_dummy_target
			SET_CHAR_VISIBLE m3_dummy_target FALSE
			SET_CHAR_COLLISION m3_dummy_target FALSE 
			SET_CHAR_DECISION_MAKER m3_dummy_target m3_empty_ped_decision_maker 
			SET_CHAR_STAY_IN_SAME_PLACE m3_dummy_target TRUE  
		ENDIF
		
		GOSUB m3_rand_coords
		//GENERATE_RANDOM_INT_IN_RANGE 3 5 m3_cs
		
		CREATE_RANDOM_CHAR m3_x m3_y 22.9 crowd[creating_crowd_flag]
		//CREATE_CHAR PEDTYPE_MISSION2 m3_char_select[m3_cs] m3_x m3_y 22.9 crowd[creating_crowd_flag]
		SET_CHAR_HEADING crowd[creating_crowd_flag] heading
		SET_SENSE_RANGE crowd[creating_crowd_flag] 0.0
		SET_CHAR_STAY_IN_SAME_PLACE crowd[creating_crowd_flag] TRUE 
		GIVE_WEAPON_TO_CHAR crowd[creating_crowd_flag] WEAPONTYPE_CAMERA 30000
		SET_CHAR_DECISION_MAKER crowd[creating_crowd_flag] m3_empty_ped_decision_maker
		SET_LOAD_COLLISION_FOR_CHAR_FLAG crowd[creating_crowd_flag] TRUE

		IF creating_crowd_flag < 5 //first five get told to do something 
			OPEN_SEQUENCE_TASK m3_seq
				GENERATE_RANDOM_INT_IN_RANGE 500 2000 m3_random_number
				IF NOT IS_CHAR_DEAD m3_dummy_target
					TASK_KILL_CHAR_ON_FOOT_TIMED -1 m3_dummy_target m3_random_number 
				ENDIF
				GENERATE_RANDOM_INT_IN_RANGE 1500 4000 m3_random_number
				TASK_PAUSE -1 m3_random_number 
				SET_SEQUENCE_TO_REPEAT m3_seq 1
			CLOSE_SEQUENCE_TASK m3_seq
			PERFORM_SEQUENCE_TASK crowd[creating_crowd_flag] m3_seq
			CLEAR_SEQUENCE_TASK m3_seq
		ENDIF
			
		//SET_CHAR_COLLISION crowd[creating_crowd_flag] FALSE
		SET_LOAD_COLLISION_FOR_CHAR_FLAG crowd[creating_crowd_flag] TRUE 
		creating_crowd_flag ++
	ELSE
		has_crowd_been_created = 1
	ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_crowd_death_checks://///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_shit_hit_fan = 0
	//checking crowd or cops 
	IF crowd_flag_death_check < 20
		IF IS_CHAR_DEAD crowd[crowd_flag_death_check] 
			m3_shit_hit_fan = 1
		ENDIF
		IF NOT IS_CHAR_DEAD crowd[crowd_flag_death_check]
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR crowd[crowd_flag_death_check] scplayer
				m3_shit_hit_fan = 1
			ENDIF
		ENDIF 	 
		crowd_flag_death_check ++
	ELSE
		crowd_flag_death_check = 0
	ENDIF

	IF cop_death_check < 8 
		IF IS_CHAR_DEAD m3_cop[cop_death_check]
			m3_shit_hit_fan = 1
		ENDIF
		IF NOT IS_CHAR_DEAD m3_cop[cop_death_check]
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR m3_cop[cop_death_check] scplayer
				m3_shit_hit_fan = 1
			ENDIF
		ENDIF 	 
		cop_death_check ++
	ELSE
		cop_death_check = 0
	ENDIF
ENDIF

IF m3_shit_hit_fan = 1
	crowd_flag_death_check = 0
	cop_death_check = 0
	m3_shit_hit_fan = 2
ENDIF	
	 
IF m3_shit_hit_fan = 2 	
	IF crowd_flag_death_check < 20
		IF NOT IS_CHAR_DEAD crowd[crowd_flag_death_check] 
			GET_SCRIPT_TASK_STATUS crowd[crowd_flag_death_check] TASK_FLEE_CHAR task_status
			IF task_status = FINISHED_TASK	
				SET_CHAR_KEEP_TASK crowd[crowd_flag_death_check] TRUE 
				TASK_FLEE_CHAR crowd[crowd_flag_death_check] scplayer 80.0 10000000
			ENDIF

			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D crowd[crowd_flag_death_check] scplayer 80.0 80.0 FALSE
				IF NOT IS_CHAR_ON_SCREEN crowd[crowd_flag_death_check]
					MARK_CHAR_AS_NO_LONGER_NEEDED crowd[crowd_flag_death_check] 
				ENDIF
			ENDIF
		ENDIF
		crowd_flag_death_check ++
	ELSE
		m3_shit_hit_fan = 3
	ENDIF
ENDIF

IF m3_shit_hit_fan = 3 
	IF cop_death_check < 8
		IF NOT IS_CHAR_DEAD m3_cop[cop_death_check]
			GET_SCRIPT_TASK_STATUS m3_cop[cop_death_check] TASK_CHAR_ARREST_CHAR task_status
			IF task_status = FINISHED_TASK	
				SET_CHAR_KEEP_TASK m3_cop[cop_death_check] TRUE 
				TASK_CHAR_ARREST_CHAR m3_cop[cop_death_check] scplayer 
			ENDIF
		ENDIF
		cop_death_check ++
	ELSE
		m3_shit_hit_fan = 4
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_removing_crowd_after_ceremony://////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_removing_crowd = 0 
	IF m3_removing_cop_flag < 8
		IF NOT IS_CHAR_DEAD m3_cop[m3_removing_cop_flag]
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D m3_cop[m3_removing_cop_flag] scplayer 80.0 80.0 FALSE
				IF NOT IS_CHAR_ON_SCREEN m3_cop[m3_removing_cop_flag]
					MARK_CHAR_AS_NO_LONGER_NEEDED m3_cop[m3_removing_cop_flag] 
				ENDIF
			ENDIF
		ENDIF
		m3_removing_cop_flag ++
	ELSE
		m3_removing_crowd = 1
	ENDIF
ENDIF

IF m3_removing_crowd = 1 
	IF m3_removing_crowd_flag < 8
		IF NOT IS_CHAR_DEAD m3_cop[m3_removing_crowd_flag]
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D m3_cop[m3_removing_crowd_flag] scplayer 80.0 80.0 FALSE
				IF NOT IS_CHAR_ON_SCREEN m3_cop[m3_removing_crowd_flag]
					MARK_CHAR_AS_NO_LONGER_NEEDED m3_cop[m3_removing_crowd_flag] 
				ENDIF
			ENDIF
		ENDIF
		m3_removing_crowd_flag ++
	ELSE
		m3_removing_crowd = 2
	ENDIF
ENDIF

IF m3_removing_crowd = 2 
	IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1015.1 -1139.2 80.0 80.0 FALSE
		MARK_OBJECT_AS_NO_LONGER_NEEDED right_barrier1 
		MARK_OBJECT_AS_NO_LONGER_NEEDED right_barrier2 
		MARK_OBJECT_AS_NO_LONGER_NEEDED right_barrier3 
		MARK_OBJECT_AS_NO_LONGER_NEEDED left_barrier1 
		MARK_OBJECT_AS_NO_LONGER_NEEDED left_barrier2 
		MARK_OBJECT_AS_NO_LONGER_NEEDED left_barrier3 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_left_barrier1 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_left_barrier2 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_left_barrier3 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_right_barrier1 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_right_barrier2 
		MARK_OBJECT_AS_NO_LONGER_NEEDED front_right_barrier3 
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_east_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_east_barrier2
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_south_east_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_south_east_barrier2
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_south_west_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_south_west_barrier2
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_west_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_west_barrier2
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_north_west_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_north_west_barrier2
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_north_east_barrier1
		MARK_OBJECT_AS_NO_LONGER_NEEDED m3_north_east_barrier2
		MARK_MODEL_AS_NO_LONGER_NEEDED DYN_ROADBARRIER_2
		SWITCH_AUDIO_ZONE AWARDS FALSE
		m3_removing_crowd = 3
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_rand_coords:////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GENERATE_RANDOM_FLOAT_IN_RANGE 1016.2 1030.8 m3_x
GENERATE_RANDOM_FLOAT_IN_RANGE -1122.3 -1133.7 m3_y
IF m3_x < 1020.0
	heading = 280.1
ELSE
	heading = 96.5
ENDIF

IF m3_x < 1027.4 
AND m3_x > 1019.8 
	GOTO m3_rand_coords
ENDIF

WHILE m3_crowd_check < m3_crowd_flag 
WAIT 0 
	IF NOT IS_CHAR_DEAD crowd[m3_crowd_check]
		IF LOCATE_CHAR_ANY_MEANS_2D crowd[m3_crowd_check] m3_x m3_y 1.0 1.0 0
			GOTO m3_rand_coords
		ENDIF
	ENDIF
	m3_crowd_check ++
ENDWHILE
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_cameras:////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_flash_flag1 = 0
	GOSUB taking_pictures
ELSE
	IF m3_flash_flag1 < m3_random_flash_flag1
		m3_flash_flag1 ++
	ELSE
		DRAW_LIGHT_WITH_RANGE m3_x m3_y m3_z 255 255 255 200.0  		   
		//DRAW_WEAPONSHOP_CORONA m3_x m3_y m3_z 0.5 CORONATYPE_SHINYSTAR FLARETYPE_NONE 255 255 255
		
		CREATE_FX_SYSTEM CAMFLASH m3_x m3_y m3_z TRUE m3_camflash1
		PLAY_AND_KILL_FX_SYSTEM m3_camflash1
		m3_flash_flag1 = 0
	ENDIF
ENDIF
IF m3_flash_flag2 = 0
	GOSUB taking_pictures
ELSE
	IF m3_flash_flag2 < m3_random_flash_flag2
		m3_flash_flag2 ++
	ELSE
		DRAW_LIGHT_WITH_RANGE m3_x2 m3_y2 m3_z2 255 255 255 200.0 		   
		//DRAW_WEAPONSHOP_CORONA m3_x2 m3_y2 m3_z2 0.5 CORONATYPE_SHINYSTAR FLARETYPE_NONE 255 255 255 
	
		CREATE_FX_SYSTEM CAMFLASH m3_x2 m3_y2 m3_z2 TRUE m3_camflash2
		PLAY_AND_KILL_FX_SYSTEM m3_camflash2
		m3_flash_flag2 = 0
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
taking_pictures:///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_flash_flag1 = 0 
	GENERATE_RANDOM_INT_IN_RANGE 1 10 m3_random_flash_flag1
	GENERATE_RANDOM_INT_IN_RANGE 0 20 m3_crowd_flag
	IF NOT IS_CHAR_DEAD crowd[m3_crowd_flag]
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS crowd[m3_crowd_flag] 0.0 0.3 0.5 m3_x m3_y z
		m3_flash_flag1 ++
	ENDIF
ENDIF
IF m3_flash_flag2 = 0
	GENERATE_RANDOM_INT_IN_RANGE 1 10 m3_random_flash_flag2
	GENERATE_RANDOM_INT_IN_RANGE 0 20 m3_crowd_flag
	IF NOT IS_CHAR_DEAD crowd[m3_crowd_flag]
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS crowd[m3_crowd_flag] 0.0 0.3 0.5 m3_x2 m3_y2 m3_z2
		m3_flash_flag2 ++
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_blippage:///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

//sorting the blippage
IF m3_car_check_flag = 0
	IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
		REMOVE_BLIP m3_docg_manager_blip
		CLEAR_THIS_PRINT STP3_13  
		IF m3_control_flag = 0
			m3_speech_flag = 0
		ENDIF	
		IF m3_control_flag = 1
			ADD_BLIP_FOR_COORD 1306.4 -1137.5 22.5 m3_docg_manager_blip
			PRINT ( STP3_33 ) 7000 1 //Go and meet up with the other drivers.  Don't damage your car!
		ENDIF
		m3_car_check_flag = 1
	ENDIF
ENDIF
IF m3_car_check_flag = 1 
	IF NOT IS_CHAR_IN_CAR scplayer m3_dgcar1[1]
		REMOVE_BLIP m3_docg_manager_blip
		ADD_BLIP_FOR_CAR m3_dgcar1[1] m3_docg_manager_blip
		SET_BLIP_AS_FRIENDLY m3_docg_manager_blip TRUE 
		IF timerb > 7000
			CLEAR_THIS_PRINT STP3_33
			PRINT ( STP3_13 ) 7000 1 //Get back in the car and get on with the mission.
		ENDIF		
		m3_car_check_flag = 0
	ENDIF
ENDIF

///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_stray_timer:////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_stray_flag = 0
	IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[0] 35.0 35.0 FALSE
		m3_time_to_fail = 5000
	ENDIF		
	m3_stray_flag = 1
ENDIF
IF m3_stray_flag = 1	
	IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[0] 35.0 35.0 FALSE
		IF m3_time_to_fail > 4000
			m3_number_to_print = 5
			PRINT_WITH_NUMBER_NOW ( STP3_14 ) m3_number_to_print 4000 1 //You have ~1~ seconds to rejoin the convoy.
		ENDIF
		IF m3_time_to_fail < 4000
			IF m3_time_to_fail > 3000
				m3_number_to_print = 4
				PRINT_WITH_NUMBER_NOW ( STP3_14 ) m3_number_to_print 4000 1 //You have ~1~ seconds to rejoin the convoy.
			ENDIF
		ENDIF
		IF m3_time_to_fail < 3000
			IF m3_time_to_fail > 2000
				m3_number_to_print = 3
				PRINT_WITH_NUMBER_NOW ( STP3_14 ) m3_number_to_print 4000 1 //You have ~1~ seconds to rejoin the convoy.
			ENDIF
		ENDIF
		IF m3_time_to_fail < 2000
			IF m3_time_to_fail > 1000
				m3_number_to_print = 2
				PRINT_WITH_NUMBER_NOW ( STP3_14 ) m3_number_to_print 4000 1 //You have ~1~ seconds to rejoin the convoy.
			ENDIF
		ENDIF
		IF m3_time_to_fail < 1000
			m3_number_to_print = 1
			PRINT_WITH_NUMBER_NOW ( STP3_15 ) m3_number_to_print 4000 1 //You have ~1~ second to rejoin the convoy.
		ENDIF
		
		IF m3_time_to_fail < 0
			PRINT_NOW ( STP3_07 ) 4000 1 //You strayed too far and alerted the other drivers!
			m3_deathcheck_flag = 1
		ENDIF
	ELSE
		CLEAR_THIS_PRINT STP3_14
		CLEAR_THIS_PRINT STP3_15
		m3_stray_flag = 0
	ENDIF	
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_damaging_other_cars:////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_speech_flag = 0
	IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[0] 950
		PRINT_NOW ( STP3_30 ) 4000 1 // Don't damage the car!
		m3_speech_flag = 1
	ENDIF
	
	IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[2] 950
		PRINT_NOW ( STP3_30 ) 4000 1 // Don't damage the car!
		m3_speech_flag = 1
	ENDIF
ENDIF
IF m3_speech_flag = 1	
	IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[0] 850
		CLEAR_PRINTS
		PRINT ( STP3_23 ) 4000 1 //You alerted the other drivers with your bad driving. 
		m3_speech_flag = 2
	ENDIF

	IF NOT IS_CAR_HEALTH_GREATER m3_dgcar1[2] 850
		CLEAR_PRINTS
		PRINT ( STP3_23 ) 4000 1 //You alerted the other drivers with your bad driving.   
		m3_speech_flag = 2
	ENDIF
ENDIF 	
IF m3_speech_flag = 2
	IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
		TASK_KILL_CHAR_ON_FOOT m3_dgcar1_driver[0] scplayer
	ENDIF  
	IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
		TASK_KILL_CHAR_ON_FOOT m3_dgcar1_driver[2] scplayer
	ENDIF  
	timera = 0 
	m3_speech_flag = 3
ENDIF 

IF m3_speech_flag = 3
	IF timera > 3000 
		m3_deathcheck_flag = 1
	ENDIF 
ENDIF 
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_car0_goons://///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF NOT IS_CHAR_DEAD m3_goons[0]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_PAUSE -1 150
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[0] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
IF NOT IS_CHAR_DEAD m3_goons[1]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[1] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
IF NOT IS_CHAR_DEAD m3_goons[2]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_PAUSE -1 100
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[2] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_car1_goons://///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF NOT IS_CHAR_DEAD m3_goons[4]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_PAUSE -1 150
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[4] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
IF NOT IS_CHAR_DEAD m3_goons[5]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[5] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
IF NOT IS_CHAR_DEAD m3_goons[6]
	OPEN_SEQUENCE_TASK m3_seq
		TASK_PAUSE -1 100
		TASK_LEAVE_ANY_CAR -1
		TASK_KILL_CHAR_ON_FOOT -1 scplayer		
	CLOSE_SEQUENCE_TASK m3_seq
	PERFORM_SEQUENCE_TASK m3_goons[6] m3_seq
	CLEAR_SEQUENCE_TASK m3_seq
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_baddy_close_check://////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD m3_dgcar1[0]
	IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[0] 160.0 160.0 FALSE
		m3_baddy_in_range_check ++ 
	ENDIF
ENDIF
IF NOT IS_CAR_DEAD m3_dgcar1[2]
	IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[2] 160.0 160.0 FALSE
		m3_baddy_in_range_check ++ 
	ENDIF
ENDIF

IF NOT IS_CAR_DEAD m3_dgcar1[0] 
	IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
		TASK_CAR_TEMP_ACTION m3_dgcar1_driver[0] m3_dgcar1[0] TEMPACT_HANDBRAKESTRAIGHT 3000
	ENDIF
ENDIF
IF NOT IS_CAR_DEAD m3_dgcar1[2] 
	IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
		TASK_CAR_TEMP_ACTION m3_dgcar1_driver[2] m3_dgcar1[2] TEMPACT_HANDBRAKESTRAIGHT 3000
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_controlling_enemy_cars://///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//controlling the first car	and occupants
IF m3_baddy_control_flag[0] = 0
	IF NOT IS_CAR_DEAD m3_dgcar1[0]
		IF NOT LOCATE_CHAR_IN_CAR_CAR_2D scplayer m3_dgcar1[0] 15.0 15.0 FALSE
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
				IMPROVE_CAR_BY_CHEATING m3_dgcar1[0] TRUE
				SET_CAR_CRUISE_SPEED m3_dgcar1[0] 100.0  
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[0] FALSE
				ADD_STUCK_CAR_CHECK_WITH_WARP m3_dgcar1[0] 0.5 4000 TRUE TRUE TRUE 7
				TASK_CAR_MISSION m3_dgcar1_driver[0] m3_dgcar1[0] -1 MISSION_BLOCKPLAYER_FARAWAY 35.0 DRIVINGMODE_AVOIDCARS
				m3_baddy_control_flag[0] = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF m3_goals = 6	 
	IF timerb > 1000
		IF m3_baddy_control_flag[0] = 1
			IF NOT IS_CAR_DEAD m3_dgcar1[0] 
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[0] 20.0 20.0 FALSE
						TASK_CAR_MISSION m3_dgcar1_driver[0] m3_dgcar1[0] -1 MISSION_BLOCKPLAYER_FARAWAY 35.0 DRIVINGMODE_AVOIDCARS
						m3_baddy_control_flag[0] = 2
					ELSE
						m3_baddy_control_flag[0] = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF m3_baddy_control_flag[0] = 2
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_CAR_DEAD m3_dgcar1[0] 
		 			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
		 				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[0] 20.0 20.0 FALSE
		 					CLEAR_CHAR_TASKS m3_dgcar1_driver[0] 
		 					OPEN_SEQUENCE_TASK m3_seq	  
						  		TASK_CAR_TEMP_ACTION -1 m3_dgcar1[0] TEMPACT_HANDBRAKESTRAIGHT 3000
						  		TASK_LEAVE_ANY_CAR -1
								TASK_KILL_CHAR_ON_FOOT -1 scplayer	
							CLOSE_SEQUENCE_TASK m3_seq
							PERFORM_SEQUENCE_TASK m3_dgcar1_driver[0] m3_seq
							CLEAR_SEQUENCE_TASK m3_seq
			 				GOSUB m3_car0_goons	
							LOCK_CAR_DOORS m3_dgcar1[0] CARLOCK_UNLOCKED
							m3_baddy_control_flag[0] = 3
						ENDIF
		 			ELSE
		 				GOSUB m3_car0_goons	
						LOCK_CAR_DOORS m3_dgcar1[0] CARLOCK_UNLOCKED
						m3_baddy_control_flag[0] = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

//controlling the second car and occupants
IF m3_baddy_control_flag[1] = 0
	IF NOT IS_CAR_DEAD m3_dgcar1[2]
		IF NOT LOCATE_CHAR_IN_CAR_CAR_2D scplayer m3_dgcar1[2] 15.0 15.0 FALSE
			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
				IMPROVE_CAR_BY_CHEATING m3_dgcar1[2] TRUE
				SET_CAR_CRUISE_SPEED m3_dgcar1[2] 100.0  
				MARK_CAR_AS_CONVOY_CAR m3_dgcar1[2] FALSE
				ADD_STUCK_CAR_CHECK_WITH_WARP m3_dgcar1[2] 0.5 4000 TRUE TRUE TRUE 7
				TASK_CAR_MISSION m3_dgcar1_driver[2] m3_dgcar1[2] -1 MISSION_BLOCKPLAYER_FARAWAY 35.0 DRIVINGMODE_AVOIDCARS
				m3_baddy_control_flag[1] = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF m3_goals = 6
	IF timerb > 1000
		IF m3_baddy_control_flag[1] = 1
			IF NOT IS_CAR_DEAD m3_dgcar1[2] 
				IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[2] 20.0 20.0 FALSE
						TASK_CAR_MISSION m3_dgcar1_driver[2] m3_dgcar1[2] -1 MISSION_BLOCKPLAYER_FARAWAY 35.0 DRIVINGMODE_AVOIDCARS
						m3_baddy_control_flag[1] = 2
					ELSE
						m3_baddy_control_flag[1] = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF m3_baddy_control_flag[1] = 2
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_CAR_DEAD m3_dgcar1[2] 
		 			IF NOT IS_CHAR_DEAD m3_dgcar1_driver[2]
		 				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer m3_dgcar1[2] 15.0 15.0 FALSE
		 					CLEAR_CHAR_TASKS m3_dgcar1_driver[2]
		 					OPEN_SEQUENCE_TASK m3_seq	  						   
						  		TASK_CAR_TEMP_ACTION -1 m3_dgcar1[2] TEMPACT_HANDBRAKESTRAIGHT 3000
						  		TASK_LEAVE_ANY_CAR -1
								TASK_KILL_CHAR_ON_FOOT -1 scplayer	
							CLOSE_SEQUENCE_TASK m3_seq
							PERFORM_SEQUENCE_TASK m3_dgcar1_driver[2] m3_seq
							CLEAR_SEQUENCE_TASK m3_seq
			 				GOSUB m3_car1_goons	
							LOCK_CAR_DOORS m3_dgcar1[2] CARLOCK_UNLOCKED
							m3_baddy_control_flag[1] = 3						   
						ENDIF
		 			ELSE
		 				GOSUB m3_car1_goons	
						LOCK_CAR_DOORS m3_dgcar1[2] CARLOCK_UNLOCKED
						m3_baddy_control_flag[1] = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_my_number_plates:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 m3_no_plates_flag
	IF m3_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates got_m00_
	ENDIF 
	IF m3_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates m00tv_4u 
	ENDIF
	IF m3_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates mathew_2 
	ENDIF 
	IF m3_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates d4_dew0r 
	ENDIF 
	IF m3_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates d0de_777 
	ENDIF 
	IF m3_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates dam0_666 
	ENDIF 
	IF m3_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates C0NEY_88 
	ENDIF 
	IF m3_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates pre4cher 
	ENDIF 
	IF m3_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates dbp_4ndy 
	ENDIF 
	IF m3_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates ev1l_sly 
	ENDIF 
	IF m3_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates n1_r4v3n 
	ENDIF 
	IF m3_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates d1vx_z00 
	ENDIF 
	IF m3_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates mr_b3nn 
	ENDIF 
	IF m3_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates r3d_r4sp 
	ENDIF 
	IF m3_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates La_B0mba 
	ENDIF 
	IF m3_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates L3337_0g 
	ENDIF 
	IF m3_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates budd4h_X 
	ENDIF 
	IF m3_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates t3h_buck 
	ENDIF 
	IF m3_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates CHUNKY_1 
	ENDIF 
	IF m3_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates ev1l_bnz 
	ENDIF 
	IF m3_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates S4ND_M4N 
	ENDIF 
	IF m3_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates RKK_DBP1 
	ENDIF 
	IF m3_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates RE1_K0KU 
	ENDIF 
	IF m3_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates s3xy_jud 
	ENDIF 
	IF m3_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates sunra_93 
	ENDIF 
	IF m3_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates UG_FuX69 
	ENDIF 
	IF m3_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates Li0n_Cum 
	ENDIF 
	IF m3_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates rkk_pwnd 
	ENDIF 
	IF m3_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates haze_b0b 
	ENDIF 
	IF m3_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates t3h_fluf 
	ENDIF 
	IF m3_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates BM_4NDY_ 
	ENDIF 
	IF m3_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates BM_D34N_ 
	ENDIF 
	IF m3_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates BM_L4C3Y 
	ENDIF 
	IF m3_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates BM_D3V__ 
	ENDIF 
	IF m3_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates NU_SK00L 
	ENDIF 
	IF m3_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates G4L_AVET 
	ENDIF 
	IF m3_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR m3_no_plates M0j0_j0j 
	ENDIF 
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_dialogue_setup://///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_speech_goals = 1
	$m3_print_label[0] = &LOC3_AA // Hey, what the fuck are you playing at?

	m3_audio_label[0] = SOUND_LOC3_AA
	m3_last_label = 1
ENDIF

IF m3_speech_goals = 2
	$m3_print_label[0] = &LOC3_BA // Hey, man.
	$m3_print_label[1] = &LOC3_BB // Take me back to Dogg's mansion.
	$m3_print_label[2] = &LOC3_BC // Not today, asshole.
	$m3_print_label[3] = &LOC3_BD // Today we're taking the scenic route,
	$m3_print_label[4] = &LOC3_BE // via the bottom of the ocean!

	m3_audio_label[0] = SOUND_LOC3_BA
	m3_audio_label[1] = SOUND_LOC3_BB
	m3_audio_label[2] = SOUND_LOC3_BC
	m3_audio_label[3] = SOUND_LOC3_BD
	m3_audio_label[4] = SOUND_LOC3_BE
	m3_last_label = 5
ENDIF

IF m3_speech_goals = 3
	$m3_print_label[0] = &LOC3_CA // Security team, the Principle is being kidnapped!
	$m3_print_label[1] = &LOC3_CB // Rescue him at all costs!

	m3_audio_label[0] = SOUND_LOC3_CA
	m3_audio_label[1] = SOUND_LOC3_CB
	m3_last_label = 2
ENDIF

IF m3_speech_goals = 4
	$m3_print_label[0] = &LOC3_DA // Who the fuck are you?
	$m3_print_label[1] = &LOC3_DB // Where's my usual driver?
	$m3_print_label[2] = &LOC3_DC // Unlock this fucking door!
	$m3_print_label[3] = &LOC3_DD // I can't fucking swim, you fucking psycho!
	$m3_print_label[4] = &LOC3_EA // So I've heard!

	m3_audio_label[0] = SOUND_LOC3_DA
	m3_audio_label[1] = SOUND_LOC3_DB
	m3_audio_label[2] = SOUND_LOC3_DC
	m3_audio_label[3] = SOUND_LOC3_DD
	m3_audio_label[4] = SOUND_LOC3_EA
	m3_last_label = 5
ENDIF					

IF m3_speech_goals = 5
	$m3_print_label[0] = &LOC3_EB // What you want, fool, money?
	$m3_print_label[1] = &LOC3_EC // I got bitches, loads o'fine -bitches take 'em.
	$m3_print_label[2] = &LOC3_ED // They'll do anything you want!
	$m3_print_label[3] = &LOC3_EE // You want a record contract?
	$m3_print_label[4] = &LOC3_EF // Man I can make any fool a superstar!
	$m3_print_label[5] = &LOC3_EG // I know people in this town, powerful people!
	$m3_print_label[6] = &LOC3_EH // Dangerous motherfuckers!
	$m3_print_label[7] = &LOC3_EJ // You Grove Street Families?
	$m3_print_label[8] = &LOC3_EK // I know Ballas OG's - we're like brothers!
	$m3_print_label[9] = &LOC3_EL // They'll fuck you up so bad!
	//$m3_print_label[10] = &LOC3_EM // And yo'moms. And yo dad!
	//$m3_print_label[11] = &LOC3_EN // Fuck, NOOOOOOOOO!

	m3_audio_label[0] = SOUND_LOC3_EB
	m3_audio_label[1] = SOUND_LOC3_EC
	m3_audio_label[2] = SOUND_LOC3_ED
	m3_audio_label[3] = SOUND_LOC3_EE
	m3_audio_label[4] = SOUND_LOC3_EF
	m3_audio_label[5] = SOUND_LOC3_EG
	m3_audio_label[6] = SOUND_LOC3_EH
	m3_audio_label[7] = SOUND_LOC3_EJ
	m3_audio_label[8] = SOUND_LOC3_EK
	m3_audio_label[9] = SOUND_LOC3_EL
	//m3_audio_label[10] = SOUND_LOC3_EM
	//m3_audio_label[11] = SOUND_LOC3_EN
	m3_last_label = 10
ENDIF

IF m3_speech_goals = 6
	$m3_print_label[0] = &LOC3_EO // Holy FUUUUUUUUUUCK!

	m3_audio_label[0] = SOUND_LOC3_EO
	m3_last_label = 1
ENDIF

IF m3_speech_goals = 7
	$m3_print_label[0] = &MOBRING // Phone Ringing
	$m3_print_label[1] = &MLOC04A // Yo, Loc? Whatttup now?
	$m3_print_label[2] = &MLOC04B // One of Madd Dogg's chaufeurs just left the Burger Shot across town.
	$m3_print_label[3] = &MLOC04C // Said he was headed over to the Music Awards!
	$m3_print_label[4] = &MLOC04D // Good lookin'.  I'm a see if I can catch up with them.

	m3_audio_label[0] = SOUND_MOBRING
	m3_audio_label[1] = SOUND_MLOC04A
	m3_audio_label[2] = SOUND_MLOC04B
	m3_audio_label[3] = SOUND_MLOC04C
	m3_audio_label[4] = SOUND_MLOC04D
	m3_last_label = m3_random_last_label
ENDIF

IF m3_speech_goals = 8
	$m3_print_label[0] = &LOC3_FA // Hey, what kept you?
	$m3_print_label[1] = &LOC3_FB // Come on we need to go and pick up the boss!
	$m3_print_label[2] = &LOC3_FC // Hold position in the middle of the motorcade until we get to the Awards Show.
	//$m3_print_label[3] = &LOC3_FD // Hold up here for a second until the cops let us through.
	$m3_print_label[3] = &LOC3_FE // Stick close and let's roll!
	$m3_print_label[4] = &LOC3_FF // Keep frosty, guys.

	m3_audio_label[0] = SOUND_LOC3_FA
	m3_audio_label[1] = SOUND_LOC3_FB
	m3_audio_label[2] = SOUND_LOC3_FC
	//m3_audio_label[3] = SOUND_LOC3_FD
	m3_audio_label[3] = SOUND_LOC3_FE
	m3_audio_label[4] = SOUND_LOC3_FF
	m3_last_label = m3_random_last_label
ENDIF

IF m3_speech_goals = 9
	$m3_print_label[0] = &LOC3_GA // Hey, watch what you're doing!
	$m3_print_label[1] = &LOC3_GB // Watch the damn car!
	$m3_print_label[2] = &LOC3_GC // You unprofessional asshole!
	$m3_print_label[3] = &LOC3_GD // Keep your mind on the job!

	m3_audio_label[0] = SOUND_LOC3_GA
	m3_audio_label[1] = SOUND_LOC3_GB
	m3_audio_label[2] = SOUND_LOC3_GC
	m3_audio_label[3] = SOUND_LOC3_GD
	m3_last_label = m3_random_last_label
ENDIF

IF m3_speech_goals = 10
	$m3_print_label[0] = &RYD1_BE // Shut up!

	m3_audio_label[0] = SOUND_RYD1_BE
	m3_last_label = 1
ENDIF

IF m3_speech_goals = 11
	$m3_print_label[0] = &LOC3_JA // Congratulations on your award, you must be thrilled! 

	m3_audio_label[0] = SOUND_LOC3_JA
	m3_last_label = 1
ENDIF

IF m3_speech_goals = 12
	$m3_print_label[0] = &LOC3_JB // Yeah, yeah, yeah, yeah, I'd like to thank my fans, my momma and my dealer.
	
	m3_audio_label[0] = SOUND_LOC3_JB
	m3_last_label = 1
ENDIF
		  
m3_slot_load = m3_speech_control_flag
m3_slot1 = 0
m3_slot2 = 0
m3_play_which_slot = 1
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_overall_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_speech_goals = 1 //driver swearing at player
	IF m3_speech_control_flag < m3_last_label
		GOSUB m3_loading_dialogue
		GOSUB m3_playing_dialogue
		IF NOT IS_CHAR_DEAD m3_dgcar1_driver[1]
			GOSUB m3_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
			m3_slot1 = 0
			m3_slot2 = 0
		ENDIF
	ELSE
		m3_speech_goals = 0
	ENDIF
ENDIF	

IF m3_speech_goals = 7 //mc strap phoning player
OR m3_speech_goals = 2 //doc g manager talking to player at awards ceremony
OR m3_speech_goals = 6 //doc g screaming holy fuck
OR m3_speech_goals = 11 //Awards ceremony
OR m3_speech_goals = 12 //Awards ceremony
	IF m3_speech_control_flag < m3_last_label
		GOSUB m3_loading_dialogue
		GOSUB m3_playing_dialogue
		GOSUB m3_finishing_dialogue  
	ELSE
		m3_speech_goals = 0
	ENDIF
ENDIF	

IF m3_goals = 5
	IF m3_control_flag = 0
		IF m3_speech_goals = 3 //radio message informing guards that the manager has been swiped
		OR m3_speech_goals = 4 //Doc G's Manager pleading for his life part 1
		OR m3_speech_goals = 5 //Doc G's Manager pleading for his life part 2
		OR m3_speech_goals = 10	//player telling manager to stfu
			IF m3_speech_control_flag < m3_last_label
				GOSUB m3_loading_dialogue
				GOSUB m3_playing_dialogue
				IF NOT IS_CHAR_DEAD docg_manager
					IF NOT IS_CAR_DEAD m3_dgcar1[1] 
						IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1] 
							GOSUB m3_finishing_dialogue 
						ELSE
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
							CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
							m3_slot1 = 0
							m3_slot2 = 0
						ENDIF
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
						m3_slot1 = 0
						m3_slot2 = 0
					ENDIF 
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
					m3_slot1 = 0
					m3_slot2 = 0
				ENDIF
			ELSE
				m3_speech_goals = 0
			ENDIF
		ENDIF
	ENDIF
ENDIF	

IF m3_speech_goals = 8 //driver swearing at player
OR m3_speech_goals = 9
	IF m3_speech_control_flag < m3_last_label
		GOSUB m3_loading_dialogue
		GOSUB m3_playing_dialogue
		IF NOT IS_CHAR_DEAD m3_dgcar1_driver[0]
			IF NOT IS_CAR_DEAD m3_dgcar1[1] 
				IF IS_CHAR_IN_CAR scplayer m3_dgcar1[1]
					GOSUB m3_finishing_dialogue  
				ELSE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
					m3_slot1 = 0
					m3_slot2 = 0
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
				m3_slot1 = 0
				m3_slot2 = 0
			ENDIF
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag] 
			m3_slot1 = 0
			m3_slot2 = 0
		ENDIF
	ELSE
		m3_speech_goals = 0
	ENDIF
ENDIF	

///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_loading_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF m3_slot_load < m3_last_label 
	//slot 1
	IF m3_slot1 = 0
		LOAD_MISSION_AUDIO 1 m3_audio_label[m3_slot_load] 
		m3_slot_load ++ 
		m3_slot1 = 1
	ENDIF

	//slot 2
	IF m3_slot2 = 0
		LOAD_MISSION_AUDIO 2 m3_audio_label[m3_slot_load] 
		m3_slot_load ++ 
		m3_slot2 = 1
	ENDIF  
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_playing_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//slot 1
IF m3_play_which_slot = 1 
	IF m3_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $m3_print_label[m3_speech_control_flag] ) 4500 1 //
			m3_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF m3_play_which_slot = 2 
	IF m3_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $m3_print_label[m3_speech_control_flag] ) 4500 1 //
			m3_slot2 = 2
		ENDIF
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
m3_finishing_dialogue://///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//slot 1
IF m3_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag]
		m3_speech_control_flag ++		
		m3_play_which_slot = 2
		m3_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF m3_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $m3_print_label[m3_speech_control_flag]
		m3_speech_control_flag ++		
		m3_play_which_slot = 1
		m3_slot2 = 0
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

}									

