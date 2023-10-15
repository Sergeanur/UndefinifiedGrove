MISSION_START
// *****************************************************************************************
// ***************************** steal2 **************************************************** 
// *****************************************************************************************
// *****************************************************************************************
// ******************** Steal cars from the car showroom ***********************************
// *****************************************************************************************
SCRIPT_NAME steal2
// Mission start stuff
GOSUB mission_start_steal2
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_steal2_failed
ENDIF
GOSUB mission_cleanup_steal2
MISSION_END																			    			 
{																					  
// Variables for mission															  								  
																					  
//people																			  
LVAR_INT st2_cesars_car st2_players_car 
LVAR_INT st2_cars[22] st2_peds[22] st2_object[15] st2_tram 
LVAR_INT st2_salesman st2_random_char  

		 																			  
//blips																				  
LVAR_INT st2_cesar_blip 							  
																					  
//flags	
LVAR_INT steal2_goals st2_control_flag st2_skip_cutscene_flag st2_deathcheck_flag  
LVAR_INT st2_car_nodes																			   
LVAR_INT st2_flag_cesar_in_group st2_car_check_flag st2_salesman_flag 
LVAR_INT st2_seq_progress
LVAR_INT st2_model st2_class
LVAR_INT st2_no_plates st2_no_plates_flag


//speech
LVAR_INT st2_speech_goals st2_speech_control_flag st2_speech_flag 
LVAR_TEXT_LABEL st2_print_label[13] 
LVAR_INT st2_audio_label[13] 
LVAR_INT st2_last_label //st2_played_random_speech[2]   
LVAR_INT st2_slot1 st2_slot2 st2_slot_load st2_play_which_slot
LVAR_INT st2_random_last_label
LVAR_INT st2_storing_speech_goals_number st2_storing_speech_control_number
		
//coords
LVAR_FLOAT st2_player_x st2_player_y st2_cesar_x st2_cesar_y st2_temp_x st2_temp_y st2_playback_speed

//sequences/decision makers/threat lists/attractors/groups
LVAR_INT st2_ped_decisions st2_empty_ped_decision_maker st2_sequence 

//debug
// ****************************************Mission Start************************************
mission_start_steal2:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT STEAL2
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
steal2_goals = 0  ////debug should be 0																		  
st2_control_flag = 0
st2_skip_cutscene_flag = 0 
st2_deathcheck_flag = 0 
st2_car_nodes = 0
st2_flag_cesar_in_group = 0
st2_car_check_flag = 0
st2_salesman_flag = 0
st2_seq_progress = 0
st2_player_x = 0.0 
st2_player_y = 0.0 
st2_cesar_x = 0.0 
st2_cesar_y = 0.0 
st2_temp_x = 0.0
st2_temp_y = 0.0
st2_playback_speed = 0.0

st2_speech_goals = 0 
st2_speech_control_flag = 0 
st2_speech_flag = 0
st2_last_label = 0
st2_slot1 = 0 
st2_slot2 = 0 
st2_slot_load = 0 
st2_play_which_slot = 0	  
st2_storing_speech_goals_number  = 0 
st2_storing_speech_control_number  = 0 

st2_model = 0 
st2_class = 0



// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 1
SET_CHAR_AREA_VISIBLE scplayer 1
LOAD_SCENE -2031.0 149.0 29.0
LOAD_CUTSCENE STEAL_2
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
SET_CHAR_AREA_VISIBLE scplayer 0
CLEAR_CUTSCENE
SET_PLAYER_CONTROL player1 OFF
MAKE_PLAYER_GANG_REAPPEAR
// ****************************************END OF CUTSCENE**********************************

SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------
//WAIT 1000

REQUEST_MODEL SULTAN 
REQUEST_MODEL ELEGY
REQUEST_MODEL WMYBU
REQUEST_VEHICLE_MOD nto_b_s  

LOAD_SPECIAL_CHARACTER 1 cesar

LOAD_ALL_MODELS_NOW

REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH st2_ped_decisions  

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY st2_empty_ped_decision_maker 

LOAD_SCENE -2032.4 171.8 27.8 
CLEAR_AREA -2032.4 171.8 27.8 1.0 TRUE
SET_CHAR_COORDINATES scplayer -2032.4 171.8 27.8
SET_CHAR_HEADING scplayer 308.7 

//Creating cesar
CLEAR_AREA -2032.3 161.9 28.0 1.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 -2032.3 161.9 28.0 cesar
SET_CHAR_NEVER_TARGETTED cesar TRUE  
SET_CHAR_DECISION_MAKER cesar st2_empty_ped_decision_maker

MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
SET_GROUP_MEMBER Players_Group cesar
SET_GROUP_DEFAULT_TASK_ALLOCATOR Players_Group DEFAULT_TASK_ALLOCATOR_FOLLOW_LIMITED
SET_CHAR_DECISION_MAKER cesar st2_ped_decisions
st2_flag_cesar_in_group = 1

CLEAR_AREA -1656.2 1210.2 12.3 5.0 TRUE 
st2_no_plates = ELEGY
GOSUB st2_my_number_plates
CREATE_CAR ELEGY -1656.2 1210.2 12.3 st2_cesars_car
SET_CAR_STRONG st2_cesars_car TRUE 
SET_CAR_HEADING st2_cesars_car 316.1 
LOCK_CAR_DOORS st2_cesars_car CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_DRIVING_STYLE st2_cesars_car 2
SET_CAR_HEALTH st2_cesars_car 2000 
SET_CAN_BURST_CAR_TYRES st2_cesars_car FALSE 
FREEZE_CAR_POSITION st2_cesars_car TRUE 
ADD_VEHICLE_MOD st2_cesars_car nto_b_s car

CLEAR_AREA -1662.3 1211.0 12.3 5.0 TRUE
st2_no_plates = SULTAN
GOSUB st2_my_number_plates
CREATE_CAR SULTAN -1662.3 1211.0 12.3 st2_players_car
SET_CAR_HEADING st2_players_car 316.1 
LOCK_CAR_DOORS st2_players_car CARLOCK_LOCKOUT_PLAYER_ONLY
CHANGE_CAR_COLOUR st2_players_car 59 12
SET_CAR_HEALTH st2_players_car 2000 
FREEZE_CAR_POSITION st2_players_car TRUE 
ADD_VEHICLE_MOD st2_players_car nto_b_s	car

SWITCH_RANDOM_TRAINS OFF

ADD_BLIP_FOR_COORD -1632.9 1207.4 6.1 st2_cesar_blip

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SET_PLAYER_CONTROL player1 ON 
DO_FADE 500 FADE_IN

SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE

/*
//// debug //////
steal2_goals = 14
FREEZE_CAR_POSITION st2_players_car FALSE
REMOVE_CHAR_FROM_GROUP cesar
/// debug ///////
*/



mission_steal2_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_steal2_passed  
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////

	GOSUB st2_death_checks
	IF st2_deathcheck_flag = 1
		GOTO mission_steal2_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// THE DRIVE THERE AND THE CAR SHOWROOM BIT //////////////////////////////////////////////////////

	IF steal2_goals = 0 
		
		//main bit for this section
		IF st2_control_flag = 0
			GOSUB st2_cesar_group

			//speech for this section 
			IF st2_speech_flag = 0 
				st2_speech_goals = 1
				st2_speech_control_flag = 0
				GOSUB st2_dialogue_setup 
				st2_speech_flag = 1
				
			ENDIF

			IF st2_speech_flag = 1
				IF st2_speech_goals = 0
					PRINT ( STE2_01 ) 7000 1 //Go and get the cars from the car showroom.
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
					timerb = 0
					st2_speech_flag = 2
				ENDIF
			ENDIF

			IF st2_speech_flag = 2
				IF timerb > 7000
					IF st2_speech_goals = 0 
						st2_speech_goals = 2
						st2_speech_control_flag = 0
						GOSUB st2_dialogue_setup 
						timerb = 0
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
						st2_speech_flag = 3	
					ENDIF
				ENDIF  
			ENDIF
			
			IF st2_speech_flag = 3
				IF timerb > 7000
					IF st2_speech_goals = 0 
						st2_speech_goals = 3
						st2_speech_control_flag = 0
						GOSUB st2_dialogue_setup 
						st2_speech_flag = 4
					ENDIF
				ENDIF
			ENDIF 

			IF st2_speech_flag = 4 
				IF st2_speech_goals = 0
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE	
					st2_speech_flag = 5
				ENDIF
			ENDIF

			IF IS_GROUP_MEMBER cesar Players_Group 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1632.9 1207.4 6.1 4.0 4.0 4.0 TRUE
					IF LOCATE_CHAR_ANY_MEANS_3D cesar -1632.9 1207.4 6.1 4.0 4.0 4.0 TRUE
						SET_PLAYER_CONTROL player1 OFF
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE	
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_PRINTS
						st2_speech_goals = 0
						st2_control_flag = 1
					ENDIF
				ENDIF
			ENDIF	
		ENDIF
		
		IF st2_control_flag = 1			
			SET_PLAYER_CONTROL player1 OFF							
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			SHUT_ALL_CHARS_UP TRUE

			SET_PED_DENSITY_MULTIPLIER 0.0
			CLEAR_AREA -1662.3 1211.0 13.4 15.0 TRUE

			LOCK_CAR_DOORS st2_players_car CARLOCK_UNLOCKED
			REMOVE_BLIP st2_cesar_blip
		
			//debug - needs to be a salesman char
			CLEAR_AREA -1656.7 1213.8 12.7 1.0 TRUE
			CREATE_CHAR PEDTYPE_MISSION1 WMYBU -1656.7 1213.8 12.7 st2_salesman
			SET_CHAR_HEADING st2_salesman 132.3
		
			IF IS_GROUP_MEMBER cesar Players_Group
				REMOVE_CHAR_FROM_GROUP cesar 
				CLEAR_CHAR_TASKS cesar 
			ENDIF
		
			OPEN_SEQUENCE_TASK st2_sequence
				TASK_LEAVE_ANY_CAR -1 
				TASK_GO_STRAIGHT_TO_COORD -1 -1642.3 1204.1 6.2 PEDMOVE_WALK -1
				TASK_GO_STRAIGHT_TO_COORD -1 -1651.2 1204.0 6.2 PEDMOVE_WALK -1
				TASK_GO_STRAIGHT_TO_COORD -1 -1663.5 1204.2 6.2 PEDMOVE_WALK -1
			CLOSE_SEQUENCE_TASK st2_sequence
			PERFORM_SEQUENCE_TASK scplayer st2_sequence	
			CLEAR_SEQUENCE_TASK st2_sequence

			OPEN_SEQUENCE_TASK st2_sequence
				TASK_LEAVE_ANY_CAR -1 
				TASK_GO_STRAIGHT_TO_COORD -1 -1640.3 1204.4 6.2 PEDMOVE_WALK -1
				TASK_GO_STRAIGHT_TO_COORD -1 -1651.7 1205.0 6.2 PEDMOVE_WALK -1
				TASK_GO_STRAIGHT_TO_COORD -1 -1662.3 1204.8 6.2 PEDMOVE_WALK -1
			CLOSE_SEQUENCE_TASK st2_sequence
			PERFORM_SEQUENCE_TASK cesar st2_sequence	
			CLEAR_SEQUENCE_TASK st2_sequence
			
			st2_speech_goals = 4
			GENERATE_RANDOM_INT_IN_RANGE 0 3 st2_speech_control_flag
			st2_random_last_label = st2_speech_control_flag + 1 
			GOSUB st2_dialogue_setup 
		
			SET_FIXED_CAMERA_POSITION -1624.5 1201.5 7.8 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1664.6 1207.1 10.9 JUMP_CUT
																								
			st2_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			
			timera = 0 
			st2_speech_flag = 0
			st2_control_flag = 2
		ENDIF

		IF st2_control_flag = 2 
			//speech for this section
			IF st2_speech_flag = 0
				IF st2_speech_goals = 0 
					st2_speech_goals = 4
					GENERATE_RANDOM_INT_IN_RANGE 3 6 st2_speech_control_flag
					st2_random_last_label = st2_speech_control_flag + 1 
					GOSUB st2_dialogue_setup 
					st2_speech_flag = 1
				ENDIF
			ENDIF		
			IF st2_speech_flag = 1
				IF st2_speech_goals = 0 
					st2_speech_goals = 4
					GENERATE_RANDOM_INT_IN_RANGE 6 9 st2_speech_control_flag
					st2_random_last_label = st2_speech_control_flag + 1 
					GOSUB st2_dialogue_setup 
					st2_speech_flag = 2
				ENDIF
			ENDIF
			
			//main bit
			IF timera > 8000 
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				CLEAR_AREA -1659.6 1203.3 11.4 400.0 TRUE
				SET_CHAR_COORDINATES scplayer -1659.6 1203.3 11.4
				SET_CHAR_HEADING scplayer 96.8 

				CLEAR_CHAR_TASKS_IMMEDIATELY cesar 
				CLEAR_AREA -1659.8 1205.0 11.4 1.0 TRUE
				SET_CHAR_COORDINATES cesar -1659.8 1205.0 11.4
				SET_CHAR_HEADING cesar 91.3 

				OPEN_SEQUENCE_TASK st2_sequence
					TASK_LEAVE_ANY_CAR -1 
					TASK_GO_STRAIGHT_TO_COORD -1 -1662.6 1202.6 12.7 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 -1666.0 1208.8 12.7 PEDMOVE_WALK -1
					TASK_ENTER_CAR_AS_DRIVER -1 st2_players_car -1 
				CLOSE_SEQUENCE_TASK st2_sequence
				PERFORM_SEQUENCE_TASK scplayer st2_sequence	
				CLEAR_SEQUENCE_TASK st2_sequence

				OPEN_SEQUENCE_TASK st2_sequence
					TASK_LEAVE_ANY_CAR -1 
					TASK_GO_STRAIGHT_TO_COORD -1 -1662.7 1204.8 12.7 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 -1662.9 1206.5 12.7 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 -1660.3 1208.8 12.7 PEDMOVE_WALK -1
					TASK_PAUSE -1 1000
					TASK_ENTER_CAR_AS_DRIVER -1 st2_cesars_car -1 
				CLOSE_SEQUENCE_TASK st2_sequence
				PERFORM_SEQUENCE_TASK cesar st2_sequence	
				CLEAR_SEQUENCE_TASK st2_sequence
				
				SET_FIXED_CAMERA_POSITION -1661.1 1219.7 15.15 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1658.9 1204.6 13.85 JUMP_CUT
				timera = 0 
				st2_control_flag = 3
			ENDIF	
		ENDIF
			
		IF st2_control_flag = 3
			GET_SCRIPT_TASK_STATUS cesar PERFORM_SEQUENCE_TASK task_status
			IF NOT task_status = FINISHED_TASK 
				GET_SEQUENCE_PROGRESS cesar st2_seq_progress
				IF st2_seq_progress = 2
					st2_speech_goals = 5
					st2_speech_control_flag = 0
					GOSUB st2_dialogue_setup 
					st2_control_flag = 4
				ENDIF
			ENDIF
		ENDIF
	
		IF st2_control_flag = 4
			IF st2_speech_control_flag = 2
				IF NOT IS_CHAR_DEAD st2_salesman 
					OPEN_SEQUENCE_TASK st2_sequence
						TASK_GO_STRAIGHT_TO_COORD -1 -1660.6 1210.1 12.7 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 -1664.0 1205.3 12.7 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 -1651.3 1203.2 6.2 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK st2_sequence
					PERFORM_SEQUENCE_TASK st2_salesman st2_sequence	
					CLEAR_SEQUENCE_TASK st2_sequence
				ENDIF
				st2_control_flag = 5
			ENDIF
		ENDIF
		
		IF st2_control_flag = 5 
			IF st2_speech_goals = 0 
				st2_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB st2_death_checks
				IF st2_deathcheck_flag = 1
					GOTO mission_steal2_failed
				ENDIF
				
				//skipping cutscene
				IF st2_skip_cutscene_flag = 1
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_PRINTS
					st2_speech_goals = 0

					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB st2_death_checks
					IF st2_deathcheck_flag = 1
						GOTO mission_steal2_failed
					ENDIF
				
					IF NOT IS_CHAR_DEAD st2_salesman
						CLEAR_AREA -1651.3 1203.2 6.2 1.0 TRUE
						SET_CHAR_COORDINATES st2_salesman -1651.3 1203.2 6.2
					ENDIF
					  
					CLEAR_CHAR_TASKS_IMMEDIATELY cesar
					WARP_CHAR_INTO_CAR cesar st2_cesars_car 
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					WARP_CHAR_INTO_CAR scplayer st2_players_car
				ENDIF 
				
				IF IS_CHAR_IN_CAR cesar st2_cesars_car
					IF IS_CHAR_IN_CAR scplayer st2_players_car  		
						FREEZE_CAR_POSITION st2_cesars_car FALSE 
						FREEZE_CAR_POSITION st2_players_car FALSE 
					
						//creating the cars needed for recording 1 
						MARK_CHAR_AS_NO_LONGER_NEEDED st2_salesman
						MARK_MODEL_AS_NO_LONGER_NEEDED WMYBU
							
						REQUEST_MODEL MULE
						REQUEST_MODEL CARDBOARDBOX
						REQUEST_MODEL TRAM
						LOAD_ALL_MODELS_NOW
						
						//truck and cardboard boxes
						SET_CAR_MODEL_COMPONENTS MULE 1 1 
						CLEAR_AREA -1580.1 938.2 6.8 5.0 TRUE
						st2_no_plates = MULE 
						GOSUB st2_my_number_plates
						CREATE_CAR MULE -1580.1 938.2 6.8 st2_cars[0]
						SET_CAR_HEADING st2_cars[0] 90.0 
						SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
						 
						CLEAR_AREA -1576.0 938.9 6.5 5.0 TRUE
						CREATE_RANDOM_CHAR -1576.0 938.9 6.5 st2_peds[0]
						SET_CHAR_HEADING st2_peds[0] 84.9
						SET_CHAR_DECISION_MAKER st2_peds[0] st2_empty_ped_decision_maker 
														    
						CLEAR_AREA -1575.1 940.7 6.4 10.0 TRUE
						CREATE_OBJECT cardboardbox -1575.1 940.7 6.4 st2_object[0]
						SET_OBJECT_COLLISION st2_object[0] TRUE
						SET_OBJECT_DYNAMIC st2_object[0] TRUE
						CREATE_OBJECT cardboardbox -1574.9 941.7 6.4 st2_object[1]
						SET_OBJECT_COLLISION st2_object[1] TRUE
						SET_OBJECT_DYNAMIC st2_object[1] TRUE
						CREATE_OBJECT cardboardbox -1573.1 942.7 6.4 st2_object[2]
						SET_OBJECT_COLLISION st2_object[2] TRUE
						SET_OBJECT_DYNAMIC st2_object[2] TRUE
						CREATE_OBJECT cardboardbox -1575.1 940.7 7.0 st2_object[3]
						SET_OBJECT_COLLISION st2_object[3] TRUE
						SET_OBJECT_DYNAMIC st2_object[3] TRUE
						CREATE_OBJECT cardboardbox -1573.1 942.7 7.0 st2_object[4]
						SET_OBJECT_COLLISION st2_object[4] TRUE
						SET_OBJECT_DYNAMIC st2_object[4] TRUE

						/*
						//car 1 - stopped
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1608.2 1214.3 5.9 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1608.2 1214.3 5.9 st2_cars[1]	    
							SET_CAR_HEADING st2_cars[1] 3.3 
							LOCK_CAR_DOORS st2_cars[1] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[1] st2_peds[1]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[1] FALSE  
						ENDIF
						*/
						//car 2
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1626.9 1202.5 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1626.9 1202.5 5.7 st2_cars[2]	    
							SET_CAR_HEADING st2_cars[2] 222.7 
							LOCK_CAR_DOORS st2_cars[2] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[2] st2_peds[2]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[2] FALSE  
						ENDIF

						//car 3
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1609.2 1160.4 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1609.2 1160.4 5.7 st2_cars[3]	    
							SET_CAR_HEADING st2_cars[3] 184.3 
							LOCK_CAR_DOORS st2_cars[3] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[3] st2_peds[3]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[3] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[3] FALSE  
						ENDIF

						//car 4										
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1600.9 1111.7 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1600.9 1111.7 5.7 st2_cars[4]	    
							SET_CAR_HEADING st2_cars[4] 183.3 
							LOCK_CAR_DOORS st2_cars[4] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[4] st2_peds[4]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[4] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[4] FALSE  
						ENDIF

						//car 5
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1608.2 1094.0 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1608.2 1094.0 5.7 st2_cars[5]	    
							SET_CAR_HEADING st2_cars[5] 180.0 
							LOCK_CAR_DOORS st2_cars[5] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[5] st2_peds[5]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[5] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[5] FALSE  
						ENDIF

						//car 6
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1608.2 1046.5 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1608.2 1046.5 5.7 st2_cars[6]	    
							SET_CAR_HEADING st2_cars[6] 183.5 
							LOCK_CAR_DOORS st2_cars[6] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[6] st2_peds[6]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[6] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[6] FALSE  
						ENDIF

						//car 7
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1595.7 1012.0 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1595.7 1012.0 5.7 st2_cars[7]	    
							SET_CAR_HEADING st2_cars[7] 207.3 
							LOCK_CAR_DOORS st2_cars[7] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[7] st2_peds[7]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[7] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[7] FALSE  
						ENDIF

						//car 8
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1540.5 959.8 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1540.5 959.8 5.7 st2_cars[8]	    
							SET_CAR_HEADING st2_cars[8] 24.3 
							LOCK_CAR_DOORS st2_cars[8] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[8] st2_peds[8]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[8] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[8] FALSE  
						ENDIF

						//car 9
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1556.0 995.6 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1556.0 995.6 5.7 st2_cars[9]	    
							SET_CAR_HEADING st2_cars[9] 41.0 
							LOCK_CAR_DOORS st2_cars[9] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[9] st2_peds[9]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[9] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[9] FALSE  
						ENDIF

						//car 10
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1587.6 1036.7 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1587.6 1036.7 5.7 st2_cars[10]	    
							SET_CAR_HEADING st2_cars[10] 3.3 
							LOCK_CAR_DOORS st2_cars[10] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[10] st2_peds[10]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[10] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[10] FALSE  
						ENDIF
						/*
						//car 11
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1580.3 1096.9 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1580.3 1096.9 5.7 st2_cars[11]	    
							SET_CAR_HEADING st2_cars[11] 3.3 
							LOCK_CAR_DOORS st2_cars[11] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[11] st2_peds[11]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[11] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[11] FALSE  
						ENDIF
						*/
						//car 12
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1579.6 1140.1 5.7 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1579.6 1140.1 5.7 st2_cars[12]	    
							SET_CAR_HEADING st2_cars[12] 2.0 
							LOCK_CAR_DOORS st2_cars[12] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[12] st2_peds[12]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[12] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[12] FALSE  
						ENDIF

						//tram
						CLEAR_AREA -1542.9 974.0 5.3 5.0 TRUE
						CREATE_MISSION_TRAIN 9 -1542.9 974.0 5.3 0 st2_tram
						SET_TRAIN_SPEED st2_tram 0.0
						SET_TRAIN_CRUISE_SPEED st2_tram 0.0
						LOCK_CAR_DOORS st2_tram CARLOCK_LOCKED 
							
						//creating the stuff needed for recording 2
						//car 13 
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1592.8 934.7 6.2 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1592.8 934.7 6.2 st2_cars[13]	    
							SET_CAR_HEADING st2_cars[13] 91.3 
							LOCK_CAR_DOORS st2_cars[13] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[13] st2_peds[13]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[13] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[13] FALSE  
						ENDIF

						//car 14 
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1608.8 934.3 6.2 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1608.8 934.3 6.2 st2_cars[14]	    
							SET_CAR_HEADING st2_cars[14] 91.3 
							LOCK_CAR_DOORS st2_cars[14] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[14] st2_peds[14]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[14] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[14] FALSE  
						ENDIF

						//car 15 
						GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
						IF st2_model >= 0 
							CLEAR_AREA -1704.4 916.0 23.4 5.0 TRUE
							st2_no_plates = st2_model
							GOSUB st2_my_number_plates
							CREATE_CAR st2_model -1704.4 916.0 23.4 st2_cars[15]	    
							SET_CAR_HEADING st2_cars[15] 269.1 
							LOCK_CAR_DOORS st2_cars[15] CARLOCK_LOCKED
							CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[15] st2_peds[15]
							SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[15] TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[15] FALSE  
						ENDIF

						CLEAR_AREA -1642.2 1210.5 6.2 15.0 TRUE
						CREATE_RANDOM_CHAR -1642.2 1210.5 6.2 st2_random_char
						SET_CHAR_DECISION_MAKER st2_random_char st2_empty_ped_decision_maker
						OPEN_SEQUENCE_TASK st2_sequence
							TASK_GO_STRAIGHT_TO_COORD -1 -1645.5 1214.0 6.2 PEDMOVE_WALK -1
							TASK_PLAY_ANIM -1 COWER PED 4.0 TRUE FALSE FALSE FALSE 30000
						CLOSE_SEQUENCE_TASK st2_sequence 
						PERFORM_SEQUENCE_TASK st2_random_char st2_sequence 	
						CLEAR_SEQUENCE_TASK st2_sequence
						
						SET_CAR_DENSITY_MULTIPLIER 0.0
						
						SET_FIXED_CAMERA_POSITION -1640.4 1212.9 6.6 0.0 0.0 0.0	 
						POINT_CAMERA_AT_POINT -1651.3 1215.3 10.6 JUMP_CUT
					
						//skipping cutscene
						IF st2_skip_cutscene_flag = 1
							DO_FADE 500 FADE_IN
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 
							GOSUB st2_death_checks
							IF st2_deathcheck_flag = 1
								GOTO mission_steal2_failed
							ENDIF
						ENDIF					
					
						timera = 0
						st2_control_flag = 6
					ENDIF
				ENDIF
			ENDIF
		ENDIF
				
		IF st2_control_flag = 6 
			IF NOT IS_CHAR_DEAD st2_random_char
				IF LOCATE_CHAR_ANY_MEANS_2D st2_random_char -1646.0 1214.4 2.0 2.0 FALSE 
					//playing the car recordings for recording 1 
					START_PLAYBACK_RECORDED_CAR st2_cesars_car 70
					IF NOT IS_CAR_DEAD st2_cars[2]  
						START_PLAYBACK_RECORDED_CAR st2_cars[2] 71
					ENDIF
					IF NOT IS_CAR_DEAD st2_cars[3]  
						START_PLAYBACK_RECORDED_CAR st2_cars[3] 72
					ENDIF
					IF NOT IS_CAR_DEAD st2_cars[4]  
						START_PLAYBACK_RECORDED_CAR st2_cars[4] 73
					ENDIF
					IF NOT IS_CAR_DEAD st2_cars[5]  
						START_PLAYBACK_RECORDED_CAR st2_cars[5] 74
					ENDIF								 
					IF NOT IS_CAR_DEAD st2_cars[6]  
						START_PLAYBACK_RECORDED_CAR st2_cars[6] 75
					ENDIF
					IF NOT IS_CAR_DEAD st2_cars[7]  
						START_PLAYBACK_RECORDED_CAR st2_cars[7] 76
					ENDIF
					IF NOT IS_CAR_DEAD st2_tram 
						START_PLAYBACK_RECORDED_CAR st2_tram 77
					ENDIF
					timera = 0 
					st2_control_flag = 7
				ENDIF
			ENDIF
		ENDIF		
			
		IF st2_control_flag = 7
			IF timera > 500
				st2_speech_goals = 6
				st2_speech_control_flag = 0
				GOSUB st2_dialogue_setup 
				st2_control_flag = 8
			ENDIF
		ENDIF
		
		IF st2_control_flag = 8 
			IF timera > 2500 
				IF NOT IS_CHAR_DEAD st2_random_char 
					TASK_SMART_FLEE_CHAR st2_random_char scplayer 200.0 600000 
				ENDIF
			
				PAUSE_PLAYBACK_RECORDED_CAR st2_cesars_car 
				IF NOT IS_CAR_DEAD st2_cars[2]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[2] 
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[3]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[3] 
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[4]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[4] 
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[5]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[5] 
				ENDIF								 
				IF NOT IS_CAR_DEAD st2_cars[6]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[6] 
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[7]  
					PAUSE_PLAYBACK_RECORDED_CAR st2_cars[7] 
				ENDIF
				IF NOT IS_CAR_DEAD st2_tram 
					PAUSE_PLAYBACK_RECORDED_CAR st2_tram 
				ENDIF

				SET_PED_DENSITY_MULTIPLIER 0.5
				
				SHUT_ALL_CHARS_UP FALSE
				
				ADD_BLIP_FOR_CAR st2_cesars_car st2_cesar_blip
				SET_BLIP_AS_FRIENDLY st2_cesar_blip TRUE
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_random_char 
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
				PRINT ( STE2_02 ) 7000 1 //Stay close to Cesar as you return to the hub.
				timera = 0
				timerb = 0
				st2_speech_flag = 0
				st2_car_check_flag = 1
				st2_control_flag = 0
				steal2_goals = 1
			ENDIF
		ENDIF
	

	//////////////////////////////////DEBUG////////////////////////
		IF st2_control_flag = 0
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
				LOAD_SCENE -1638.7 1199.4 6.2
				SET_CHAR_COORDINATES scplayer -1638.7 1199.4 6.2
				SET_CHAR_COORDINATES cesar -1639.7 1199.4 6.2
			ENDIF
		ENDIF
	//////////////////////////////////DEBUG////////////////////////
	ENDIF 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 1: BLASTING OUT OF THE CAR SHOWROOM  ////////////////////////////////////////////////

	IF steal2_goals = 1
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				IF timerb > 7000 
					st2_speech_goals = 7
					st2_speech_control_flag = 0
					GOSUB st2_dialogue_setup 
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					st2_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 1
			IF st2_speech_goals = 0  
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 2
			ENDIF
		ENDIF

			
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		//waiting for player to leave car showroom
		IF st2_control_flag = 0 
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -1656.2 1210.2 12.3 7.0 7.0 7.0 FALSE 
				st2_control_flag = 1
			ENDIF
		ENDIF

		//player has left car showroom
		IF st2_control_flag = 1 
			UNPAUSE_PLAYBACK_RECORDED_CAR st2_cesars_car
			IF NOT IS_CAR_DEAD st2_cars[2]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[2] 
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[3]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[3] 
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[4]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[4] 
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[5]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[5] 
			ENDIF								 
			IF NOT IS_CAR_DEAD st2_cars[6]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[6] 
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[7]  
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_cars[7] 
			ENDIF
			IF NOT IS_CAR_DEAD st2_tram 
				UNPAUSE_PLAYBACK_RECORDED_CAR st2_tram 
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[8]  
				SET_CAR_CRUISE_SPEED st2_cars[8] 15.0
				CAR_WANDER_RANDOMLY st2_cars[8]
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[9]  
				SET_CAR_CRUISE_SPEED st2_cars[9] 15.0
				CAR_WANDER_RANDOMLY st2_cars[9]
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[10]  
				SET_CAR_CRUISE_SPEED st2_cars[10] 15.0
				CAR_WANDER_RANDOMLY st2_cars[10]
			ENDIF
			/*
			IF NOT IS_CAR_DEAD st2_cars[11]  
				SET_CAR_CRUISE_SPEED st2_cars[11] 15.0
				CAR_WANDER_RANDOMLY st2_cars[11]
			ENDIF
			*/
			IF NOT IS_CAR_DEAD st2_cars[12]  
				SET_CAR_CRUISE_SPEED st2_cars[12] 15.0
				CAR_WANDER_RANDOMLY st2_cars[12]
			ENDIF
			REQUEST_MODEL COPCARSF
			REQUEST_MODEL SFPD1
			st2_control_flag = 2
		ENDIF
		
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 2
			
			GOSUB st2_sorting_speed
			
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 79
				IF NOT IS_CAR_DEAD st2_tram 
					START_PLAYBACK_RECORDED_CAR st2_tram 78
				ENDIF
				
				//playing the car recordings for recording 2 
				IF NOT IS_CAR_DEAD st2_cars[13]
					SET_CAR_FORWARD_SPEED st2_cars[13] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[13] 15.0	 
					CAR_GOTO_COORDINATES st2_cars[13] -1881.4 1038.7 44.8
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[14]
					SET_CAR_FORWARD_SPEED st2_cars[14] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[14] 15.0	 
					CAR_GOTO_COORDINATES st2_cars[14] -1880.1 1029.8 44.8
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[15]
					SET_CAR_FORWARD_SPEED st2_cars[15] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[15] 15.0	 
					CAR_WANDER_RANDOMLY st2_cars[15]
				ENDIF

				//deleting the stuff from recording 1
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[10] 
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[12] 
				
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[10] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[11] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[12] 
			
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[0] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[1] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[2] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[3] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[4] 
				MARK_MODEL_AS_NO_LONGER_NEEDED MULE
				MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX

				timerb = 0
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 2
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 2: RACING THE TRAM //////////////////////////////////////////////////////////////////

	IF steal2_goals = 2
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
		
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				st2_speech_goals = 8
				st2_speech_control_flag = 0
				st2_random_last_label = 1
				GOSUB st2_dialogue_setup
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF
		ENDIF 
	
		IF st2_speech_flag = 1
			IF LOCATE_CAR_2D st2_cesars_car -1702.4 921.4 3.0 3.0 FALSE 
				IF st2_speech_goals = 0 
					st2_speech_goals = 8
					st2_speech_control_flag = 1
					st2_random_last_label = 2
					GOSUB st2_dialogue_setup
					st2_speech_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 3
			ENDIF
		ENDIF

		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		IF st2_control_flag = 0
			REQUEST_MODEL COPCARSF
			REQUEST_MODEL SFPD1
			st2_control_flag = 1
		ENDIF 
		
		IF st2_control_flag = 1 
		////// streaming in stuff for next recording ///////
			
			/*
			//creating the stuff needed for recording 3
			//car 0 - recorded 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1732.5 853.1 23.4 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1732.5 853.1 23.4 st2_cars[0]	    
				SET_CAR_HEADING st2_cars[0] 89.4 
				LOCK_CAR_DOORS st2_cars[0] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[0] st2_peds[0]
				SET_CAR_DRIVING_STYLE st2_cars[0] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS 
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[0] FALSE  
			ENDIF
			*/
			//car 1 - told where to go
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1802.9 853.6 23.4 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1802.9 853.6 23.4 st2_cars[1]	    
				SET_CAR_HEADING st2_cars[1] 89.3 
				LOCK_CAR_DOORS st2_cars[1] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[1] st2_peds[1]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[1] FALSE  
			ENDIF
			/*
			//car 2
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1804.5 836.8 23.4 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1804.5 836.8 23.4 st2_cars[2]	    
				SET_CAR_HEADING st2_cars[2] 268.6 
				LOCK_CAR_DOORS st2_cars[2] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[2] st2_peds[2]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[2] FALSE  
		   	ENDIF
			*/										
			//car 3
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1852.2 836.7 33.8 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1852.2 836.7 33.8 st2_cars[3]	    
				SET_CAR_HEADING st2_cars[3] 270.9 
				LOCK_CAR_DOORS st2_cars[3] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[3] st2_peds[3]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[3] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[3] FALSE  
			ENDIF

			//car 4
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1876.7 836.3 33.8 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1876.7 836.3 33.8 st2_cars[4]	    
				SET_CAR_HEADING st2_cars[4] 270.9 
				LOCK_CAR_DOORS st2_cars[4] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[4] st2_peds[4]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[4] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[4] FALSE  
			ENDIF
			/*
			//car 5
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1895.8 824.1 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1895.8 824.1 33.7 st2_cars[5]	    
				SET_CAR_HEADING st2_cars[5] 358.8 
				LOCK_CAR_DOORS st2_cars[5] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[5] st2_peds[5]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[5] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[5] FALSE  
			ENDIF
			*/
			//car 6
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1903.1 826.2 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1903.1 826.2 33.7 st2_cars[6]	    
				SET_CAR_HEADING st2_cars[6] 178.0 
				LOCK_CAR_DOORS st2_cars[6] CARLOCK_LOCKED 
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[6] st2_peds[6]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[6] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[6] FALSE  
			ENDIF

			//car 7
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1915.7 836.6 34.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1915.7 836.6 34.2 st2_cars[7]	    
				SET_CAR_HEADING st2_cars[7] 272.0 
				LOCK_CAR_DOORS st2_cars[7] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[7] st2_peds[7]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[7] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[7] FALSE  
			ENDIF

			//car 8
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1902.8 866.4 34.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1902.8 866.4 34.2 st2_cars[8]	    
				SET_CAR_HEADING st2_cars[8] 179.4 
				LOCK_CAR_DOORS st2_cars[8] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[8] st2_peds[8]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[8] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[8] FALSE  
			ENDIF

			//car 9
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1902.7 882.1 34.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1902.7 882.1 34.2 st2_cars[9]	    
				SET_CAR_HEADING st2_cars[9] 179.4 
				LOCK_CAR_DOORS st2_cars[9] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[9] st2_peds[9]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[9] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[9] FALSE  
			ENDIF

			//ped 10
			CLEAR_AREA -1805.5 900.3 23.9 5.0 TRUE
			CREATE_RANDOM_CHAR -1805.5 900.3 23.9 st2_peds[10]
			SET_CHAR_HEADING st2_peds[10] 352.4

			//ped 11
			CLEAR_AREA -1804.5 901.3 23.9 5.0 TRUE
			CREATE_RANDOM_CHAR -1804.5 901.3 23.9 st2_peds[11]
			SET_CHAR_HEADING st2_peds[11] 169.0

			TASK_CHAT_WITH_CHAR st2_peds[10] st2_peds[11] TRUE TRUE
			TASK_CHAT_WITH_CHAR st2_peds[11] st2_peds[10] FALSE TRUE
		
			//car 13
			//carried forwards from recording 2 

			//car 14
			//carried forwards from recording 2 


			//creating the stuff needed for recording 4
			//car 30 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -1988.1 933.5 43.9 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -1988.1 933.5 43.9 st2_cars[21]	    
				SET_CAR_HEADING st2_cars[21] 85.7 
				LOCK_CAR_DOORS st2_cars[21] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[21] st2_peds[21]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[21] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[21] FALSE  
			ENDIF

			//car 16 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2007.7 1039.2 55.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2007.7 1039.2 55.2 st2_cars[16]	    
				SET_CAR_HEADING st2_cars[16] 178.1 
				LOCK_CAR_DOORS st2_cars[16] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[16] st2_peds[16]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[16] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[16] FALSE  
			ENDIF

			//car 17 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2009.2 962.6 44.1 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates	   
				CREATE_CAR st2_model -2009.2 962.6 44.1 st2_cars[17]	    
				SET_CAR_HEADING st2_cars[17] 179.8 
				LOCK_CAR_DOORS st2_cars[17] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[17] st2_peds[17]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[17] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[17] FALSE  
			ENDIF

			//car 18 - cop  
			IF HAS_MODEL_LOADED COPCARSF 
				CLEAR_AREA -1998.2 825.3 44.1 5.0 TRUE
				CREATE_CAR COPCARSF -1998.2 825.3 44.1 st2_cars[18] 
				SET_CAR_HEADING st2_cars[18] 6.4
				CREATE_CHAR_INSIDE_CAR st2_cars[18] PEDTYPE_MISSION1 SFPD1 st2_peds[18]
				SET_CAR_DRIVING_STYLE st2_cars[18] DRIVINGMODE_AVOIDCARS
				LOCK_CAR_DOORS st2_cars[18] CARLOCK_LOCKED
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[18] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[18] FALSE  
			ENDIF

			//car 19 - cop 
			IF HAS_MODEL_LOADED COPCARSF 
				CLEAR_AREA -1997.5 815.6 44.1 5.0 TRUE
				CREATE_CAR COPCARSF -1997.5 815.6 44.1 st2_cars[19] 
				SET_CAR_HEADING st2_cars[19] 1.0
				CREATE_CHAR_INSIDE_CAR st2_cars[19] PEDTYPE_MISSION1 SFPD1 st2_peds[19]
				SET_CAR_DRIVING_STYLE st2_cars[19] DRIVINGMODE_AVOIDCARS
				LOCK_CAR_DOORS st2_cars[19] CARLOCK_LOCKED
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[19] TRUE	
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[19] FALSE  
			ENDIF
			st2_control_flag = 2
		ENDIF
		
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 2
			
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting the stuff from recording 2
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[15]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[15]

				//playing the car recordings for recording 3 
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 81
				IF NOT IS_CAR_DEAD st2_tram 
					START_PLAYBACK_RECORDED_CAR st2_tram 80
				ENDIF
				/*
				IF NOT IS_CAR_DEAD st2_cars[0] 
					START_PLAYBACK_RECORDED_CAR st2_cars[0] 82
				ENDIF
				*/
				IF NOT IS_CAR_DEAD st2_cars[1] 
					SET_CAR_FORWARD_SPEED st2_cars[1] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[1] 15.0	 
					CAR_GOTO_COORDINATES st2_cars[1] -1881.4 853.7 35.7
				ENDIF
				/*
				IF NOT IS_CAR_DEAD st2_cars[2] 
					SET_CAR_FORWARD_SPEED st2_cars[2] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[2] 15.0	 
					CAR_GOTO_COORDINATES st2_cars[2] -1717.8 746.3 24.8
				ENDIF
				*/
				IF NOT IS_CAR_DEAD st2_cars[3] 
					SET_CAR_FORWARD_SPEED st2_cars[3] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[3] 15.0	 
					CAR_GOTO_COORDINATES st2_cars[3] -1717.8 755.3 24.8
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[4] 
					SET_CAR_FORWARD_SPEED st2_cars[4] 15.0 
					SET_CAR_CRUISE_SPEED st2_cars[4] 15.0	 
				ENDIF

				timerb = 0 
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 3
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 3: ZIG ZAG THEN AVOID THE BLOCKED JUNCTION  /////////////////////////////////////////

	IF steal2_goals = 3
		//sorting cesar's speed 
		GOSUB st2_sorting_speed

		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF LOCATE_CAR_2D st2_cesars_car -1855.2 916.9 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 2
					st2_random_last_label = 3
					GOSUB st2_dialogue_setup
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					st2_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 1  
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 2
			ENDIF
		ENDIF
		
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
		////// streaming in stuff for next recording ///////
			st2_control_flag = 1	
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting the stuff from recording 3
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[10]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11]
				
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
		
				//deleting the boxes from recording 1
				DELETE_OBJECT st2_object[0] 
				DELETE_OBJECT st2_object[1] 
				DELETE_OBJECT st2_object[2] 
				DELETE_OBJECT st2_object[3] 
				DELETE_OBJECT st2_object[4] 
			
				//playing the car recordings for recording 4 
				IF NOT IS_CAR_DEAD st2_tram 
					START_PLAYBACK_RECORDED_CAR st2_tram 83
				ENDIF
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 84
				
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 4
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 4: RUNNING FROM THE POLICE  /////////////////////////////////////////////////////////

	IF steal2_goals = 4
		//sorting cesar's speed 
		GOSUB st2_sorting_speed

		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				st2_speech_goals = 8
				st2_speech_control_flag = 3
				st2_random_last_label = 4
				GOSUB st2_dialogue_setup
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF
		ENDIF 

		IF st2_speech_flag = 1
			IF st2_speech_goals = 0 
				ALTER_WANTED_LEVEL_NO_DROP player1 2
				st2_speech_flag = 2
			ENDIF
		ENDIF

		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 3
			ENDIF
		ENDIF
		


		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			//creating the stuff needed for recording 5
			//car 0 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2350.7 1091.2 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2350.7 1091.2 54.2 st2_cars[0]	    
				SET_CAR_HEADING st2_cars[0] 72.3 
				LOCK_CAR_DOORS st2_cars[0] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[0] st2_peds[0]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[0] FALSE  
			ENDIF

			//car 1 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2347.8 1081.2 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2347.8 1081.2 54.2 st2_cars[1]	    
				SET_CAR_HEADING st2_cars[1] 73.7 
				LOCK_CAR_DOORS st2_cars[1] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[1] st2_peds[1]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[1] FALSE  
			ENDIF

			//car 2
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2124.1 1080.8 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2124.1 1080.8 54.2 st2_cars[2]	    
				SET_CAR_HEADING st2_cars[2] 90.7 
				LOCK_CAR_DOORS st2_cars[2] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[2] st2_peds[2]
				SET_CAR_STAY_IN_SLOW_LANE st2_cars[2] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[2] FALSE  
			ENDIF

			//car 3
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2132.0 1059.0 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2132.0 1059.0 54.2 st2_cars[3]	    
				SET_CAR_HEADING st2_cars[3] 269.2 
				LOCK_CAR_DOORS st2_cars[3] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[3] st2_peds[3]
				SET_CAR_STAY_IN_FAST_LANE st2_cars[3] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[3] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[3] FALSE  
			ENDIF

			//car 4
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2166.7 1050.8 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2166.7 1050.8 54.2 st2_cars[4]	    
				SET_CAR_HEADING st2_cars[4] 270.2 
				LOCK_CAR_DOORS st2_cars[4] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[4] st2_peds[4]
				SET_CAR_STAY_IN_SLOW_LANE st2_cars[4] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[4] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[4] FALSE  
			ENDIF

			//car 5
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2231.0 1059.8 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2231.0 1059.8 54.2 st2_cars[5]	    
				SET_CAR_HEADING st2_cars[5] 268.2 
				LOCK_CAR_DOORS st2_cars[5] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[5] st2_peds[5]
				SET_CAR_STAY_IN_FAST_LANE st2_cars[5] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[5] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[5] FALSE  
			ENDIF

			//car 6
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2299.5 1052.1 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2299.5 1052.1 54.2 st2_cars[6]	    
				SET_CAR_HEADING st2_cars[6] 272.2 
				LOCK_CAR_DOORS st2_cars[6] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[6] st2_peds[6]
				SET_CAR_STAY_IN_SLOW_LANE st2_cars[6] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[6] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[6] FALSE  
			ENDIF

			//car 7
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2338.1 1066.2 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2338.1 1066.2 54.2 st2_cars[7]	    
				SET_CAR_HEADING st2_cars[7] 255.2 
				LOCK_CAR_DOORS st2_cars[7] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[7] st2_peds[7]
				SET_CAR_STAY_IN_FAST_LANE st2_cars[7] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[7] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[7] FALSE  
			ENDIF

			//car 8
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2344.3 1058.8 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2344.3 1058.8 54.2 st2_cars[8]	    
				SET_CAR_HEADING st2_cars[8] 257.1 
				LOCK_CAR_DOORS st2_cars[8] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[8] st2_peds[8]
				SET_CAR_STAY_IN_SLOW_LANE st2_cars[8] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[8] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[8] FALSE  
			ENDIF

			//car 9
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2446.5 1097.0 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2446.5 1097.0 54.2 st2_cars[9]	    
				SET_CAR_HEADING st2_cars[9] 266.7 
				LOCK_CAR_DOORS st2_cars[9] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[9] st2_peds[9]
				SET_CAR_STAY_IN_FAST_LANE st2_cars[9] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[9] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[9] FALSE  
			ENDIF

			//car 10
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2427.5 1087.2 54.2 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2427.5 1087.2 54.2 st2_cars[10]	    
				SET_CAR_HEADING st2_cars[10] 261.0 
				LOCK_CAR_DOORS st2_cars[10]	CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[10] st2_peds[10]
				SET_CAR_STAY_IN_SLOW_LANE st2_cars[10] TRUE
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[10] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[10] FALSE  
			ENDIF
			st2_control_flag = 1
		ENDIF
	
		IF st2_control_flag = 1
			//triggering the police
			IF LOCATE_CAR_2D st2_cesars_car -1990.0 838.0 3.0 3.0 FALSE
				IF NOT IS_CAR_DEAD st2_cars[15]
					START_PLAYBACK_RECORDED_CAR st2_cars[15] 85
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[16]
					SET_CAR_FORWARD_SPEED st2_cars[16] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[16] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[16] -2008.2 581.2 35.3
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[17]
					SET_CAR_FORWARD_SPEED st2_cars[17] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[17] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[17] -2008.2 571.2 35.3
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[18]
					SET_CAR_FORWARD_SPEED st2_cars[18] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[18] 40.0	 		
					IF NOT IS_CAR_DEAD st2_cesars_car 
						SET_CAR_FOLLOW_CAR st2_cars[18] st2_cesars_car 10.0
						SWITCH_CAR_SIREN st2_cars[18] ON 
					ENDIF
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[19]
					SET_CAR_FORWARD_SPEED st2_cars[19] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[19] 40.0	 		
					IF NOT IS_CAR_DEAD st2_cesars_car 
						SET_CAR_FOLLOW_CAR st2_cars[19] st2_cesars_car 20.0
						SWITCH_CAR_SIREN st2_cars[19] ON 
					ENDIF
		 		ENDIF
				st2_control_flag = 2
			ENDIF
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 2
			REQUEST_MODEL COACH
			REQUEST_MODEL PCJ600
			
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting the cars from recording 2
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11]
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[12] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[11]
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[12]
		
				//deleting the cars from recording 4
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[21] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[16] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[17] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[18] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[19] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[21]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[16]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[17]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[18]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[19]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_tram
				MARK_MODEL_AS_NO_LONGER_NEEDED TRAM

				//playing the car recordings for recording 5 
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 86
				IF NOT IS_CAR_DEAD st2_cars[2]
					START_PLAYBACK_RECORDED_CAR st2_cars[2] 87
		 		ENDIF

				IF NOT IS_CAR_DEAD st2_cars[15]
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[15]  
						STOP_PLAYBACK_RECORDED_CAR st2_cars[15] 
					ENDIF
				ENDIF
				REMOVE_CAR_RECORDING 85

				IF NOT IS_CAR_DEAD st2_cars[3]
					SET_CAR_FORWARD_SPEED st2_cars[3] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[3] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[3] -1896.9 1060.1 44.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[4]
					SET_CAR_FORWARD_SPEED st2_cars[4] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[4] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[4] -1896.9 1050.1 44.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[5]
					SET_CAR_FORWARD_SPEED st2_cars[5] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[5] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[5] -1906.9 1060.1 48.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[6]
					SET_CAR_FORWARD_SPEED st2_cars[6] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[6] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[6] -1906.9 1050.1 48.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[7]
					SET_CAR_FORWARD_SPEED st2_cars[7] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[7] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[7] -1916.9 1060.1 52.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[8]
					SET_CAR_FORWARD_SPEED st2_cars[8] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[8] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[8] -1916.9 1050.1 52.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[9]
					SET_CAR_FORWARD_SPEED st2_cars[9] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[9] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[9] -1926.9 1060.1 56.2
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[10]
					SET_CAR_FORWARD_SPEED st2_cars[10] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[10] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[10] -1926.9 1050.1 56.2
		 		ENDIF

				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 5
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 5: NITRO AND DOWN THE HILL  /////////////////////////////////////////////////////////

	IF steal2_goals = 5
		//sorting cesar's speed 
		GOSUB st2_sorting_speed

		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				st2_speech_goals = 8
				st2_speech_control_flag = 4
				st2_random_last_label = 5
				GOSUB st2_dialogue_setup
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF
		ENDIF 
		IF st2_speech_flag = 1
			PRINT_HELP STE2_03 
			GIVE_NON_PLAYER_CAR_NITRO st2_cesars_car	
			st2_speech_flag = 2
		ENDIF 
		IF st2_speech_flag = 2
			IF LOCATE_CAR_2D st2_cesars_car -2319.8 1080.2 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 5
					st2_random_last_label = 6
					GOSUB st2_dialogue_setup
					st2_speech_flag = 3
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 3
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 4
			ENDIF
		ENDIF
	
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			//creating the stuff needed for recording 6
			//car 11 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2503.3 1237.0 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2503.3 1237.0 33.7 st2_cars[11]	    
				SET_CAR_HEADING st2_cars[11] 249.3 
				LOCK_CAR_DOORS st2_cars[11] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[11] st2_peds[11]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[11] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[11] FALSE  
			ENDIF

			//car 14 
			CLEAR_AREA -2741.5 1058.2 48.1 5.0 TRUE
			CREATE_CAR PCJ600 -2741.5 1058.2 48.1 st2_cars[14] 
			SET_CAR_HEADING st2_cars[14] 337.2
			CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[14] st2_peds[14]
			SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[14] TRUE
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[14] FALSE  
	
			//car 16 
			CLEAR_AREA -2513.7 1246.2 34.1 5.0 TRUE
			CREATE_CAR COACH -2513.7 1246.2 34.1 st2_cars[16] 
			SET_CAR_HEADING st2_cars[16] 82.5
			CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[16] st2_peds[16]
			SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[16] TRUE
			LOCK_CAR_DOORS st2_cars[16] CARLOCK_LOCKED
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[16] FALSE  

			//car 17 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2471.6 1219.7 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2471.6 1219.7 33.7 st2_cars[17]	    
				SET_CAR_HEADING st2_cars[17] 50.8 
				LOCK_CAR_DOORS st2_cars[17] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[17] st2_peds[17]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[17] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[17] FALSE  
			ENDIF

			//car 18 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2459.1 1224.3 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2459.1 1224.3 33.7 st2_cars[18]	    
				SET_CAR_HEADING st2_cars[18] 163.8 
				LOCK_CAR_DOORS st2_cars[18] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[18] st2_peds[18]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[18] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[18] FALSE  
			ENDIF

			//car 19 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2458.1 1234.3 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2458.1 1234.3 33.7 st2_cars[19]	    
				SET_CAR_HEADING st2_cars[19] 178.2 
				LOCK_CAR_DOORS st2_cars[19] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[19] st2_peds[19]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[19] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[19] FALSE  
			ENDIF
				
			//car 20 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2474.5 1196.9 34.4 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2474.5 1196.9 34.4 st2_cars[20]	    
				SET_CAR_HEADING st2_cars[20] 310.3 
				LOCK_CAR_DOORS st2_cars[20] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[20] st2_peds[20]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[20] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[20] FALSE  
			ENDIF

			st2_control_flag = 1
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting the stuff from recording 5
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[10] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[10]
				
				//playing the car recordings for recording 6 
				IF NOT IS_CAR_DEAD st2_cars[11]
					SET_CAR_FORWARD_SPEED st2_cars[11] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[11] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[11] -2293.5 1172.3 54.8
		 		ENDIF

				IF NOT IS_CAR_DEAD st2_cars[14]
					START_PLAYBACK_RECORDED_CAR st2_cars[14] 91
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[16]
					START_PLAYBACK_RECORDED_CAR st2_cars[16] 90
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[17]
					START_PLAYBACK_RECORDED_CAR st2_cars[17] 89
		 		ENDIF
				IF NOT IS_CAR_DEAD st2_cars[20]
					SET_CAR_FORWARD_SPEED st2_cars[20] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[20] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[20] -2283.5 1172.3 54.8
		 		ENDIF
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 88

				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 6
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 6: AROUND THE BUS AND AVOIDING THE SKIDDING BIKE  ///////////////////////////////////

	IF steal2_goals = 6
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
	
	
		
		////// Speech for this section /////
		/*
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				st2_speech_goals = 8
				st2_speech_control_flag = 6
				st2_random_last_label = 7
				GOSUB st2_dialogue_setup
				st2_speech_flag = 1
			ENDIF
		ENDIF
		*/ 

		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			//creating more stuff needed for recording 6
			//car 12 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2533.8 1242.8 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2533.8 1242.8 33.7 st2_cars[12]	    
				SET_CAR_HEADING st2_cars[12] 288.3 
				LOCK_CAR_DOORS st2_cars[12] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[12] st2_peds[12]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[12] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[12] FALSE  
			ENDIF
		
	   		//car 13 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2594.0 1213.9 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2594.0 1213.9 33.7 st2_cars[13]	    
				SET_CAR_HEADING st2_cars[13] 317.3 
				LOCK_CAR_DOORS st2_cars[13] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[13] st2_peds[13]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[13] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[13] FALSE  
			ENDIF
		
			//car 15 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2732.9 1098.0 45.0 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2732.9 1098.0 45.0 st2_cars[15]	    
				SET_CAR_HEADING st2_cars[15] 218.5 
				LOCK_CAR_DOORS st2_cars[15] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[15] st2_peds[15]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[15] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[15] FALSE  
			ENDIF
			IF NOT IS_CAR_DEAD st2_cars[12]
				SET_CAR_FORWARD_SPEED st2_cars[12] 15.0 	
				SET_CAR_CRUISE_SPEED st2_cars[12] 15.0	 		
				CAR_GOTO_COORDINATES st2_cars[12] -2303.5 1172.3 54.8
	 		ENDIF
			IF NOT IS_CAR_DEAD st2_cars[13]
				SET_CAR_FORWARD_SPEED st2_cars[13] 15.0 	
				SET_CAR_CRUISE_SPEED st2_cars[13] 15.0	 		
				CAR_GOTO_COORDINATES st2_cars[13] -2313.5 1060.1 44.2
	 		ENDIF
			
		
			st2_control_flag = 1
		ENDIF
		  	

		//stopping bike from sliding along the road
		IF st2_control_flag = 1
			IF NOT IS_CAR_DEAD st2_cars[14]
				IF LOCATE_CAR_2D st2_cesars_car -2599.3 1206.5 3.0 3.0 FALSE
					IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[14]
						STOP_PLAYBACK_RECORDED_CAR st2_cars[14]  
						MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[14]
						st2_control_flag = 2
					ELSE
						st2_control_flag = 2
					ENDIF
				ENDIF
			ELSE
				st2_control_flag = 2
			ENDIF
		ENDIF	 	

		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 2
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				
				//deleting the stuff from recording 6
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[12] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[13] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[14] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[15] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[16] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[17] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[18] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[19] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[20] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[11] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[12] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[13] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[14] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[15] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[16] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[17] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[18] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[19] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[20]

				//removing bike recording 
				REMOVE_CAR_RECORDING 91
			
				//playing the car recordings for recording 7 
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 92
				
				MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
				
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 7
			ENDIF
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 7: DOWN THE HILL PAST THE SKIDDING CAR  /////////////////////////////////////////////

	IF steal2_goals = 7
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
	
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				st2_speech_goals = 9
				st2_speech_control_flag = 0
				GOSUB st2_dialogue_setup
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF
		ENDIF 
		IF st2_speech_flag = 1
			IF LOCATE_CAR_2D st2_cesars_car -2622.2 999.7 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 7
					st2_random_last_label = 8
					GOSUB st2_dialogue_setup
					st2_speech_flag = 2
				ENDIF
			ENDIF
		ENDIF 
		IF st2_speech_flag = 2
			IF LOCATE_CAR_2D st2_cesars_car -2608.9 913.3 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 8
					st2_random_last_label = 9
					GOSUB st2_dialogue_setup
					st2_speech_flag = 3
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 3
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 4
			ENDIF
		ENDIF
	
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			//creating the stuff needed for recording 7
			//car 0  
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2595.6 1006.4 76.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2595.6 1006.4 76.7 st2_cars[0]	    
				SET_CAR_HEADING st2_cars[0] 89.4 
				LOCK_CAR_DOORS st2_cars[0] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[0] st2_peds[0]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[0] FALSE  
			ENDIF

			//car 1  
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2595.6 1000.9 76.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2595.6 1000.9 76.7 st2_cars[1]	    
				SET_CAR_HEADING st2_cars[1] 274.4 
				LOCK_CAR_DOORS st2_cars[1] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[1] st2_peds[1]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[1] FALSE  
			ENDIF

			//car 2  
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2604.0 963.9 76.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2604.0 963.9 76.7 st2_cars[2]	    
				SET_CAR_HEADING st2_cars[2] 360.0 
				LOCK_CAR_DOORS st2_cars[2] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[2] st2_peds[2]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[2] FALSE  
			ENDIF

			//car 3  
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2616.4 905.6 64.5 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2616.4 905.6 64.5 st2_cars[3]	    
				SET_CAR_HEADING st2_cars[3] 269.5 
				LOCK_CAR_DOORS st2_cars[3] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[3] st2_peds[3]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[3] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[3] FALSE  
			ENDIF

			//car 4  
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2536.9 911.1 63.5 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2536.9 911.1 63.5 st2_cars[4]	    
				SET_CAR_HEADING st2_cars[4] 89.8 
				LOCK_CAR_DOORS st2_cars[4] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[4] st2_peds[4]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[4] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[4] FALSE  
			ENDIF

			//car 5
			IF HAS_MODEL_LOADED COPCARSF 
				CLEAR_AREA -2609.3 1012.8 76.8 5.0 TRUE
				CREATE_CAR COPCARSF -2609.3 1012.8 76.8 st2_cars[5] 
				SET_CAR_HEADING st2_cars[5] 178.8
				LOCK_CAR_DOORS st2_cars[5] CARLOCK_LOCKED
				CREATE_CHAR_INSIDE_CAR st2_cars[5] PEDTYPE_MISSION1 SFPD1 st2_peds[5]
				SET_CAR_DRIVING_STYLE st2_cars[5] DRIVINGMODE_AVOIDCARS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[5] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[5] FALSE  
			ENDIF

			//car 6  
			IF HAS_MODEL_LOADED COPCARSF 
				CLEAR_AREA -2603.7 1011.2 76.8 5.0 TRUE
				CREATE_CAR COPCARSF -2603.7 1011.2 76.8 st2_cars[6] 
				SET_CAR_HEADING st2_cars[6] 179.1
				LOCK_CAR_DOORS st2_cars[6] CARLOCK_LOCKED
				CREATE_CHAR_INSIDE_CAR st2_cars[6] PEDTYPE_MISSION1 SFPD1 st2_peds[6]
				SET_CAR_DRIVING_STYLE st2_cars[6] DRIVINGMODE_AVOIDCARS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[6] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[6] FALSE  
			ENDIF
			
			//creating the stuff needed for recording 8
			//car 7 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2590.0 705.9 26.6 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2590.0 705.9 26.6 st2_cars[7]	    
				SET_CAR_HEADING st2_cars[7] 268.3 
				LOCK_CAR_DOORS st2_cars[7] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[7] st2_peds[7]
				SET_CAR_DRIVING_STYLE st2_cars[7] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[7] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[7] FALSE  
			ENDIF

			//car 8 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2463.0 710.6 33.9 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2463.0 710.6 33.9 st2_cars[8]	    
				SET_CAR_HEADING st2_cars[8] 90.3 
				LOCK_CAR_DOORS st2_cars[8] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[8] st2_peds[8]
				SET_CAR_DRIVING_STYLE st2_cars[8] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[8] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[8] FALSE  
			ENDIF
		
			//creating the stuff needed for recording 9
			//car 9 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2501.6 562.7 13.1 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2501.6 562.7 13.1 st2_cars[9]	    
				SET_CAR_HEADING st2_cars[9] 271.8 
				LOCK_CAR_DOORS st2_cars[9] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[9] st2_peds[9]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[9] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[9] FALSE  
			ENDIF

			//car 10 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2390.9 581.7 23.9 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2390.9 581.7 23.9 st2_cars[10]	    
				SET_CAR_HEADING st2_cars[10] 184.1 
				LOCK_CAR_DOORS st2_cars[10] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[10] st2_peds[10]
				SET_CAR_DRIVING_STYLE st2_cars[10] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[10] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[10] FALSE  
			ENDIF										

			//car 11 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2361.7 510.9 27.9 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2361.7 510.9 27.9 st2_cars[11]	    
				SET_CAR_HEADING st2_cars[11] 100.0 
				LOCK_CAR_DOORS st2_cars[11] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[11] st2_peds[11]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[11] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[11] FALSE  
			ENDIF

			//car 12 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2281.3 569.6 33.7 5.0 TRUE
				st2_no_plates = st2_model
			  	GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2281.3 569.6 33.7 st2_cars[12]	    
				SET_CAR_HEADING st2_cars[12] 87.6 
				LOCK_CAR_DOORS st2_cars[12] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[12] st2_peds[12]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[12] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[12] FALSE  
			ENDIF

			//car 13 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2358.9 569.9 23.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2358.9 569.9 23.7 st2_cars[13]	    
				SET_CAR_HEADING st2_cars[13] 90.7 
				LOCK_CAR_DOORS st2_cars[13] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[13] st2_peds[13]
				SET_CAR_DRIVING_STYLE st2_cars[13] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[13] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[13] FALSE  
			ENDIF

			//car 14 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2373.1 569.6 23.4 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2373.1 569.6 23.4 st2_cars[14]	    
				SET_CAR_HEADING st2_cars[14] 91.3 
				LOCK_CAR_DOORS st2_cars[14] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[14] st2_peds[14]
				SET_CAR_DRIVING_STYLE st2_cars[14] DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[14] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[14] FALSE  
			ENDIF
		
			st2_control_flag = 1
		ENDIF
		 
		IF st2_control_flag = 1
			IF LOCATE_CAR_2D st2_cesars_car -2610.7 992.2 3.0 3.0 FALSE 
				IF NOT IS_CAR_DEAD st2_cars[2]
					SET_CAR_FORWARD_SPEED st2_cars[2] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[2] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[2] -2736.4 1006.9 53.0
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[5]
					SET_CAR_FORWARD_SPEED st2_cars[5] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[5] 40.0	 		
					IF NOT IS_CAR_DEAD st2_cesars_car 
						SET_CAR_FOLLOW_CAR st2_cars[5] st2_cesars_car 10.0
						SWITCH_CAR_SIREN st2_cars[5] ON 
					ENDIF
				ENDIF
		 		
				IF NOT IS_CAR_DEAD st2_cars[6]
					SET_CAR_FORWARD_SPEED st2_cars[6] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[6] 40.0	 		
					IF NOT IS_CAR_DEAD st2_cesars_car 
						SET_CAR_FOLLOW_CAR st2_cars[6] st2_cesars_car 20.0
						SWITCH_CAR_SIREN st2_cars[6] ON 
					ENDIF
		 		ENDIF
				st2_control_flag = 2
			ENDIF
		ENDIF
		 
		IF st2_control_flag = 2
			IF LOCATE_CAR_2D st2_cesars_car -2610.4 922.8 3.0 3.0 FALSE 
				IF NOT IS_CAR_DEAD st2_cars[3]
					START_PLAYBACK_RECORDED_CAR st2_cars[3] 93
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[4]
					SET_CAR_FORWARD_SPEED st2_cars[4] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[4] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[4] -2604.0 963.9 76.7
		 		ENDIF
				st2_control_flag = 3
			ENDIF
		ENDIF
		 
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 3
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting the cars from recording 7
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
			
				//playing the car recordings for recording 8 
				IF NOT IS_CAR_DEAD st2_cars[7]
		 			START_PLAYBACK_RECORDED_CAR st2_cars[7] 95
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[8]
		 			START_PLAYBACK_RECORDED_CAR st2_cars[8] 96
		 		ENDIF

				START_PLAYBACK_RECORDED_CAR st2_cesars_car 94
			
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 8
			ENDIF 	
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 8: DOWN THE HILL AND DOING THE 360  /////////////////////////////////////////////////

	IF steal2_goals = 8
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
	
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF LOCATE_CAR_2D st2_cesars_car -2528.9 737.7 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 9
					st2_random_last_label = 10
					GOSUB st2_dialogue_setup
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					st2_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 1
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 2
			ENDIF
		ENDIF
	
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			
			
			
			st2_control_flag = 1
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting cop cars from recording 7
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
			
				//deleting the cars from recording 8
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
			
				//playing the car recordings for recording 9 
				IF NOT IS_CAR_DEAD st2_cars[9]
					SET_CAR_FORWARD_SPEED st2_cars[9] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[9] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[9] -2400.7 562.2 23.4
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[10]
					SET_CAR_FORWARD_SPEED st2_cars[10] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[10] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[10] -2319.5 424.7 33.6
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[11]
					SET_CAR_FORWARD_SPEED st2_cars[11] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[11] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[11] -2383.1 551.4 23.6
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[12]
					SET_CAR_FORWARD_SPEED st2_cars[12] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[12] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[12] -2372.9 569.6 23.4
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[13]
					SET_CAR_FORWARD_SPEED st2_cars[13] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[13] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[13] -2516.5 569.9 13.1
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[14]
					SET_CAR_FORWARD_SPEED st2_cars[14] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[14] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[14] -2383.9 655.8 33.6
		 		ENDIF
				
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 97

				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 9
			ENDIF 	
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 9: ALONG THE ROAD TOWARDS THE TRAM STATION  /////////////////////////////////////////

	IF steal2_goals = 9
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
	
	
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF LOCATE_CAR_2D st2_cesars_car -2528.6 656.4 3.0 3.0 FALSE 
				//IF st2_speech_goals = 0
					st2_speech_goals = 10
					st2_speech_control_flag = 0
					GOSUB st2_dialogue_setup
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					st2_speech_flag = 1
				//ENDIF
			ENDIF
		ENDIF 
		IF st2_speech_flag = 1
			IF LOCATE_CAR_2D st2_cesars_car -2375.0 566.2 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 10
					st2_random_last_label = 11
					GOSUB st2_dialogue_setup
					st2_speech_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				CLEAR_WANTED_LEVEL player1
				st2_speech_flag = 3
			ENDIF
		ENDIF   	

		IF st2_speech_flag = 3
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 4
			ENDIF
		ENDIF

		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			//creating the stuff needed for recording 10
			//car 0 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				
				CLEAR_AREA -2269.1 5748.0 33.7 5.0 TRUE  // See comment below...
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2269.1 5748.0 33.7 st2_cars[0]
				SET_CAR_HEADING st2_cars[0] 182.3 
				LOCK_CAR_DOORS st2_cars[0] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[0] st2_peds[0]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[0] FALSE  
			    
			ENDIF

			//car 1 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2251.1 561.9 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2251.1 561.9 33.7 st2_cars[1]	    
				SET_CAR_HEADING st2_cars[1] 271.1 
				LOCK_CAR_DOORS st2_cars[1] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[1] st2_peds[1]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[1] FALSE  
			ENDIF

			//car 2 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2212.5 569.9 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2212.5 569.9 33.7 st2_cars[2]	    
				SET_CAR_HEADING st2_cars[2] 91.3 
				LOCK_CAR_DOORS st2_cars[2] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[2] st2_peds[2]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[2] FALSE  
			ENDIF

			//car 3 - coach 
			CLEAR_AREA -2229.4 532.7 34.0 5.0 TRUE
			CREATE_CAR COACH -2229.4 532.7 34.0 st2_cars[3] 
			SET_CAR_HEADING st2_cars[3] 180.3
			CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[3] st2_peds[3]
			SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[3] TRUE

			//car 4 - tram 
			//CREATE_CAR TRAM -2270.6 541.3 34.8 st2_cars[4] 
			//SET_CAR_HEADING st2_cars[4] 269.1
			//CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[4] st2_peds[4]
			//SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[4] TRUE

			//car 5 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2342.8 505.3 28.3 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2342.8 505.3 28.3 st2_cars[5]	    
				SET_CAR_HEADING st2_cars[5] 269.2 
				LOCK_CAR_DOORS st2_cars[5] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[5] st2_peds[5]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[5] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[5] FALSE  
			ENDIF

			//car 6 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2293.5 505.4 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2293.5 505.4 33.7 st2_cars[6]	    
				SET_CAR_HEADING st2_cars[6] 270.0 
				LOCK_CAR_DOORS st2_cars[6] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[6] st2_peds[6]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[6] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[6] FALSE  
			ENDIF

			//car 7 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2238.9 511.2 34.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2238.9 511.2 34.7 st2_cars[7]	    
				SET_CAR_HEADING st2_cars[7] 90.6 
				LOCK_CAR_DOORS st2_cars[7] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[7] st2_peds[7]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[7] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[7] FALSE  
			ENDIF

			//car 8 
			GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
			IF st2_model >= 0 
				CLEAR_AREA -2224.0 496.6 33.7 5.0 TRUE
				st2_no_plates = st2_model
				GOSUB st2_my_number_plates
				CREATE_CAR st2_model -2224.0 496.6 33.7 st2_cars[8]	    
				SET_CAR_HEADING st2_cars[8] 0.0 
				LOCK_CAR_DOORS st2_cars[8] CARLOCK_LOCKED
				CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[8] st2_peds[8]
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[8] TRUE
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[8] FALSE  
			ENDIF

			//ped 19
			CLEAR_AREA -2232.2 529.5 34.2 5.0 TRUE
			CREATE_RANDOM_CHAR -2232.2 529.5 34.2 st2_peds[19]
			SET_CHAR_HEADING st2_peds[19] 174.2
			//IF IS_CAR_ON_SCREEN st2_cars[3] 
			//	TASK_ENTER_CAR_AS_PASSENGER st2_peds[9] st2_cars[3] -1 1  
			//ENDIF

			//ped 20
			CLEAR_AREA -2232.5 531.0 34.2 5.0 TRUE
			CREATE_RANDOM_CHAR -2232.5 531.0 34.2 st2_peds[20]
			SET_CHAR_HEADING st2_peds[20] 174.2
			//IF IS_CAR_ON_SCREEN st2_cars[3] 
			//	TASK_ENTER_CAR_AS_PASSENGER st2_peds[10] st2_cars[3] -1 2  
			//ENDIF
			st2_control_flag = 1
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				//deleting stuff from recording 9
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[10] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[12] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[13] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[14] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[10] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[11] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[12] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[13] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[14] 
			
				//playing the car recordings for recording 10 
				IF NOT IS_CAR_DEAD st2_cars[0]
					SET_CAR_FORWARD_SPEED st2_cars[0] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[0] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[0] -2174.9 561.8 33.6
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[1]
					SET_CAR_FORWARD_SPEED st2_cars[1] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[1] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[1] -2160.2 561.9 33.6
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[5]
					SET_CAR_FORWARD_SPEED st2_cars[5] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[5] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[5] -2252.6 505.7 33.7
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[6]
					SET_CAR_FORWARD_SPEED st2_cars[6] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[6] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[6] -2239.4 505.7 33.7
		 		ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[7]
					SET_CAR_FORWARD_SPEED st2_cars[7] 15.0 	
					SET_CAR_CRUISE_SPEED st2_cars[7] 15.0	 		
					CAR_GOTO_COORDINATES st2_cars[7] -2361.2 511.5 27.9
		 		ENDIF
				
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 98

				
				MARK_MODEL_AS_NO_LONGER_NEEDED COACH
				MARK_MODEL_AS_NO_LONGER_NEEDED TRAM

				REQUEST_MODEL DYN_ROADBARRIER_3
				REQUEST_MODEL DYN_ROADBARRIER_4
				REQUEST_MODEL KMB_SKIP
				REQUEST_MODEL DYN_DUMPSTER
				REQUEST_MODEL DOZER
				REQUEST_MODEL CEMENT
				REQUEST_MODEL DYN_RAMP
		
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 10
			ENDIF 	
		ENDIF 	
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 10: THROUGH THE TRAM STATION TOWARDS THE POLICE ALLEYWAY ////////////////////////////

	IF steal2_goals = 10
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
	
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF LOCATE_CAR_2D st2_cesars_car -2264.5 546.1 3.0 3.0 FALSE 
				IF st2_speech_goals = 0
					st2_speech_goals = 8
					st2_speech_control_flag = 11
					st2_random_last_label = 12
					GOSUB st2_dialogue_setup
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					st2_speech_flag = 1
				ENDIF
			ENDIF
		ENDIF 
		IF st2_speech_flag = 1
			IF LOCATE_CAR_2D st2_cesars_car -2224.1 454.8 3.0 3.0 FALSE 
				PRINT_NOW ( STE2_04 ) 4000 1 //Follow Cesar into the alleyway. 
				st2_speech_flag = 2
			ENDIF
		ENDIF 

		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 3
			ENDIF
		ENDIF
	
		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				st2_control_flag = 1
			ENDIF
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF LOCATE_CHAR_IN_CAR_2D scplayer -2213.9 453.0 5.0 5.0 FALSE 
				
				//deleting the stuff from recording 10
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				//MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[19] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[20] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				//MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
		
				//creating the stuff needed for recording 11
				//car 0  
				GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE st2_model st2_class
				IF st2_model >= 0 
					CLEAR_AREA -2228.8 521.1 33.8 5.0 TRUE
					st2_no_plates = st2_model
					GOSUB st2_my_number_plates
					CREATE_CAR st2_model -2228.8 521.1 33.8 st2_cars[10]	    
					SET_CAR_HEADING st2_cars[10] 179.4 
					LOCK_CAR_DOORS st2_cars[10] CARLOCK_LOCKED
					CREATE_RANDOM_CHAR_AS_DRIVER st2_cars[10] st2_peds[10]
					SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[10] TRUE
					SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[10] FALSE  
				ENDIF

				//car 11
				IF HAS_MODEL_LOADED COPCARSF 
					CLEAR_AREA -2149.4 462.0 33.8 5.0 TRUE
					CREATE_CAR COPCARSF -2149.4 462.0 33.8 st2_cars[11] 
					SET_CAR_HEADING st2_cars[11] 174.8
					LOCK_CAR_DOORS st2_cars[11] CARLOCK_LOCKED
					CREATE_CHAR_INSIDE_CAR st2_cars[11] PEDTYPE_MISSION1 SFPD1 st2_peds[11]
					SET_CAR_DRIVING_STYLE st2_cars[11] DRIVINGMODE_AVOIDCARS
					SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[11] TRUE
					SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[11] FALSE  
				ENDIF
			
				//car 12  
				IF HAS_MODEL_LOADED COPCARSF 
					CLEAR_AREA -2148.8 444.0 33.8 5.0 TRUE
					CREATE_CAR COPCARSF -2148.8 444.0 33.8 st2_cars[12] 
					SET_CAR_HEADING st2_cars[12] 360.0
					LOCK_CAR_DOORS st2_cars[12] CARLOCK_LOCKED
					CREATE_CHAR_INSIDE_CAR st2_cars[12] PEDTYPE_MISSION1 SFPD1 st2_peds[12]
					SET_CAR_DRIVING_STYLE st2_cars[12] DRIVINGMODE_AVOIDCARS
					SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[12] TRUE
					SET_CHAR_CAN_BE_SHOT_IN_VEHICLE st2_peds[12] FALSE  
				ENDIF
				//playing the car recordings for recording 11 
				IF NOT IS_CAR_DEAD st2_cesars_car
					START_PLAYBACK_RECORDED_CAR st2_cesars_car 65
				ENDIF
				
				IF NOT IS_CAR_DEAD st2_cars[11]
					START_PLAYBACK_RECORDED_CAR st2_cars[11] 66
					SWITCH_CAR_SIREN st2_cars[11] ON 
				ENDIF
				IF NOT IS_CAR_DEAD st2_cars[12] 
					START_PLAYBACK_RECORDED_CAR st2_cars[12] 67
					SWITCH_CAR_SIREN st2_cars[12] ON 
				ENDIF	
				CLEAR_THIS_PRINT STE2_04
				timerb = 0 
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 11
			ENDIF 	
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 11: REVERSING OUT OF THE ALLEYWAY WITH THE POLICE  //////////////////////////////////

	IF steal2_goals = 11
		//sorting cesar's speed 
		//GOSUB st2_sorting_speed
		
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF timerb > 1000 
				st2_speech_goals = 11
				st2_speech_control_flag = 0
				GOSUB st2_dialogue_setup
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF 
		ENDIF

		IF st2_speech_flag = 1
			IF st2_speech_goals = 0 
				ALTER_WANTED_LEVEL_NO_DROP player1 2
				st2_speech_flag = 2
			ENDIF
		ENDIF

		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 3
			ENDIF
		ENDIF


		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
			CLEAR_AREA -2128.1 496.6 34.0 100.0 TRUE
		
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[13] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[14] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[15] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[16] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[17] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[18] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[19] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[20] 
			MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[21] 

			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0]
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[13] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[14] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[15] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[16] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[17] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[18] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[19] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[20] 
			MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[21] 
			
			//creating the stuff needed for recording 12
			//CREATE_OBJECT CJ_ROADBARRIER_L0 -2128.1 496.6 34.0 st2_object[0] 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2128.1 496.6 34.0 st2_object[0] 
			SET_OBJECT_HEADING st2_object[0] 270.0 
			CREATE_OBJECT DYN_ROADBARRIER_4 -2128.1 501.6 34.0 st2_object[1] 
			SET_OBJECT_HEADING st2_object[1] 270.0 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2128.1 503.6 34.0 st2_object[2] 
			SET_OBJECT_HEADING st2_object[2] 270.0 
			CREATE_OBJECT DYN_ROADBARRIER_4 -2128.1 506.8 34.0 st2_object[3] 
			SET_OBJECT_HEADING st2_object[3] 270.0 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2128.1 511.6 34.0 st2_object[4] 
			SET_OBJECT_HEADING st2_object[4] 270.0 
			//CREATE_OBJECT CJ_ROADBARRIER_L0 -2128.1 511.6 34.0 st2_object[4] 

			//CREATE_OBJECT CJ_ROADBARRIER_L0 -2016.1 496.6 34.0 st2_object[5] 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2016.1 496.6 34.0 st2_object[5] 
			SET_OBJECT_HEADING st2_object[5] 90.0 
			CREATE_OBJECT DYN_ROADBARRIER_4 -2016.1 501.6 34.0 st2_object[6] 
			SET_OBJECT_HEADING st2_object[6] 90.0 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2016.1 503.6 34.0 st2_object[7] 
			SET_OBJECT_HEADING st2_object[7] 90.0 
			CREATE_OBJECT DYN_ROADBARRIER_4 -2016.1 506.8 34.0 st2_object[8] 
			SET_OBJECT_HEADING st2_object[8] 90.0 
			CREATE_OBJECT DYN_ROADBARRIER_3 -2016.1 511.6 34.0 st2_object[9] 
			SET_OBJECT_HEADING st2_object[9] 90.0 
			//CREATE_OBJECT CJ_ROADBARRIER_L0 -2016.1 511.6 34.0 st2_object[9] 

		
			CREATE_OBJECT KMB_SKIP -2096.1 501.8 34.0 st2_object[10]
			SET_OBJECT_HEADING st2_object[10] 90.0 

			CREATE_OBJECT DYN_DUMPSTER -2096.1 504.8 34.0 st2_object[11]
			SET_OBJECT_HEADING st2_object[11] 270.0 

			CREATE_OBJECT DYN_RAMP -2056.9 503.8 34.0 st2_object[12]
			SET_OBJECT_HEADING st2_object[12] 270.0 

			//car 0 - flatbed
			IF HAS_MODEL_LOADED DOZER 
				CREATE_CAR DOZER -2084.6 501.2 34.4 st2_cars[0] 
				SET_CAR_HEADING st2_cars[0] 269.7
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[0] TRUE
			ENDIF
			 
			//car 1 - dozer
			IF HAS_MODEL_LOADED DOZER 
				CREATE_CAR DOZER -2045.1 500.9 34.3 st2_cars[1] 
				SET_CAR_HEADING st2_cars[1] 156.1
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[1] TRUE
			ENDIF

			//car 2 - cement
			IF HAS_MODEL_LOADED CEMENT 
				CREATE_CAR CEMENT -2048.3 508.4 35.0 st2_cars[2] 
				SET_CAR_HEADING st2_cars[2] 88.4
				SET_LOAD_COLLISION_FOR_CAR_FLAG st2_cars[2] TRUE
			ENDIF
			st2_control_flag = 1
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				START_PLAYBACK_RECORDED_CAR st2_cesars_car 99
				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 12
			ENDIF 	
		ENDIF 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// RECORDING 12: 2 WHEELS PAST THE ROADWORKS /////////////////////////////////////////////////////

	IF steal2_goals = 12
		//sorting cesar's speed 
		GOSUB st2_sorting_speed
		
		////// Speech for this section /////
		IF st2_speech_flag = 0
		ENDIF 

		////// car nodes jiggery pokery ///////
		IF st2_car_nodes = 0 
		ENDIF

		
		IF st2_control_flag = 0 
		////// streaming in stuff for next recording ///////
			st2_control_flag = 1
		ENDIF
		  	
		////// waiting for cesar to finish recording /////
		IF st2_control_flag = 1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car	
				SET_CAR_DENSITY_MULTIPLIER 1.0
			
				OPEN_SEQUENCE_TASK st2_sequence
					TASK_CAR_DRIVE_TO_COORD -1 st2_cesars_car -2009.7 192.2 26.0 45.0 MODE_ACCURATE TRUE DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 st2_cesars_car -2031.7 180.1 27.4 10.0 MODE_ACCURATE TRUE DRIVINGMODE_AVOIDCARS
				CLOSE_SEQUENCE_TASK st2_sequence
				PERFORM_SEQUENCE_TASK cesar st2_sequence	
				CLEAR_SEQUENCE_TASK st2_sequence
				
				IF IS_CHAR_IN_CAR scplayer st2_players_car
					REMOVE_BLIP st2_cesar_blip 
					ADD_BLIP_FOR_COORD -2024.7 179.4 27.4 st2_cesar_blip
				ENDIF

				st2_speech_flag = 0
				st2_car_nodes = 0
				st2_control_flag = 0
				steal2_goals = 13
			ENDIF 	
		ENDIF 	
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// INTO THE HUB AND MISSION COMPLETE  ////////////////////////////////////////////////////////////

	IF steal2_goals = 13
		////// Speech for this section /////
		IF st2_speech_flag = 0
			IF st2_speech_goals = 0
				// Ok, we're good. | See you back at the hub, CJ!
				st2_speech_goals = 12
				st2_speech_control_flag = 0
				GOSUB st2_dialogue_setup

				IF NOT IS_CAR_DEAD st2_cars[11]
					IF NOT IS_CAR_DEAD st2_cars[12]
						IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer st2_cars[11] 30.0 30.0 FALSE 
							IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer st2_cars[12] 30.0 30.0 FALSE
								CLEAR_WANTED_LEVEL player1 
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
				st2_speech_flag = 1
			ENDIF
		ENDIF 
	
		IF st2_speech_flag = 1
			IF st2_speech_goals = 0
				PRINT ( STE2_05 ) 4000 1 //Drive back to the hub.
				st2_speech_flag = 2
			ENDIF
		ENDIF 
		
		IF st2_speech_flag = 2
			IF st2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE		 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
				st2_speech_flag = 3
			ENDIF
		ENDIF
	
		////// deleting everything once Cesar has returned to the hub ///////
		IF st2_control_flag = 0 
			GET_SCRIPT_TASK_STATUS cesar PERFORM_SEQUENCE_TASK task_status
			IF task_status = FINISHED_TASK
				SET_PED_DENSITY_MULTIPLIER 1.0
				
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[4] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[6] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[7] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[8] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[9] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[10] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[11] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[12] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[13] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[14] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[15] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[16] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[17] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[18] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[19] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[20] 
				MARK_CHAR_AS_NO_LONGER_NEEDED st2_peds[21] 
					
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[0]
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[1] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[2] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[3] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[4] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[5] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[6] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[7] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[8] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[9] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[10] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[11] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[12] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[13] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[14] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[15] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[16] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[17] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[18] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[19] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[20] 
				MARK_CAR_AS_NO_LONGER_NEEDED st2_cars[21]
				
				MARK_CAR_AS_NO_LONGER_NEEDED st2_tram 

				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[0] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[1] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[2] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[3] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[4] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[5] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[6] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[7] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[8] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[9] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[10] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[11] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[12] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[13] 
				MARK_OBJECT_AS_NO_LONGER_NEEDED st2_object[14] 
				
				st2_control_flag = 1
			ENDIF
		ENDIF

		IF IS_CHAR_IN_CAR scplayer st2_players_car 
			IF LOCATE_CHAR_IN_CAR_3D scplayer -2024.7 179.4 27.4 4.0 4.0 4.0 TRUE
				CLEAR_WANTED_LEVEL player1
				SET_PLAYER_CONTROL player1 OFF
				st2_control_flag = 0
				steal2_goals = 14
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////// FINAL CUTSCENE //////////////////////////////////////////////////////////////////////////

	IF steal2_goals = 14
		IF st2_control_flag = 0
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			OPEN_GARAGE hbgdSFS
			WHILE NOT IS_GARAGE_OPEN hbgdSFS
				WAIT 0 	
			ENDWHILE
			GOSUB st2_death_checks
			IF st2_deathcheck_flag = 1
				GOTO mission_steal2_failed
			ENDIF
			
			SET_AREA_VISIBLE 1 	

			SHUT_ALL_CHARS_UP TRUE
			
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS
			st2_speech_goals = 0
		
			REQUEST_ANIMATION PLAYIDLES 
			
			LOAD_ALL_MODELS_NOW 
			
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			SET_CHAR_AREA_VISIBLE scplayer 1

			CLEAR_AREA -2043.7 171.4 27.6 100.0 TRUE
			SET_VEHICLE_AREA_VISIBLE st2_cesars_car 1
			SET_CAR_COORDINATES st2_cesars_car -2043.7 171.4 27.6
			SET_CAR_HEADING st2_cesars_car 91.6

			LOAD_SCENE_IN_DIRECTION -2045.4 173.1 27.8 309.5 
			
			SET_CHAR_AREA_VISIBLE cesar 1
			CLEAR_CHAR_TASKS_IMMEDIATELY cesar 
			SET_CHAR_COORDINATES cesar -2045.4 173.1 27.8
			SET_CHAR_HEADING cesar 325.5
	
			SET_VEHICLE_AREA_VISIBLE st2_players_car 1
			SET_CAR_COORDINATES st2_players_car -2033.8 178.6 27.6
			SET_CAR_HEADING st2_players_car 90.0
		
			IF NOT IS_CHAR_IN_CAR scplayer st2_players_car
				WARP_CHAR_INTO_CAR scplayer st2_players_car
			ENDIF 
			
			//SET_CAR_FORWARD_SPEED st2_players_car 
			OPEN_SEQUENCE_TASK st2_sequence
				TASK_CAR_DRIVE_TO_COORD -1 st2_players_car -2044.4 177.6 27.6 5.0 MODE_ACCURATE TRUE DRIVINGMODE_AVOIDCARS
				TASK_LEAVE_ANY_CAR -1 
				TASK_TURN_CHAR_TO_FACE_CHAR -1 cesar
			CLOSE_SEQUENCE_TASK st2_sequence
			PERFORM_SEQUENCE_TASK scplayer st2_sequence	
			CLEAR_SEQUENCE_TASK st2_sequence
	
			SET_FIXED_CAMERA_POSITION -2049.3 172.8 28.78 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2034.7 177.4 29.8 JUMP_CUT
			
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			GOSUB st2_death_checks
			IF st2_deathcheck_flag = 1
				GOTO mission_steal2_failed
			ENDIF
			st2_control_flag = 1
		ENDIF
		
		IF st2_control_flag = 1
			IF IS_CAR_IN_AREA_2D st2_players_car -2039.3 175.1-2041.9 181.6 FALSE
				TASK_PLAY_ANIM cesar stretch PLAYIDLES 1000.0 FALSE FALSE FALSE FALSE -1
				st2_control_flag = 2
			ENDIF
		ENDIF
	
		IF st2_control_flag = 2
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status	
			IF task_status = FINISHED_TASK
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				CLOSE_GARAGE hbgdSFS
				WHILE NOT IS_GARAGE_CLOSED hbgdSFS
					WAIT 0 	
				ENDWHILE
				GOSUB st2_death_checks
				IF st2_deathcheck_flag = 1
					GOTO mission_steal2_failed
				ENDIF

				SHUT_ALL_CHARS_UP FALSE
				
				SET_AREA_VISIBLE 0
				SET_CHAR_AREA_VISIBLE scplayer 0 
				LOAD_SCENE -2045.4 173.1 27.8
				LOAD_SCENE_IN_DIRECTION -2045.4 173.1 27.8 309.5
				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				SET_CHAR_COORDINATES scplayer -2027.2 179.5 27.8
				SET_CHAR_HEADING scplayer 277.5 
				DO_FADE 500 FADE_IN
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOTO mission_steal2_passed
			ENDIF
		ENDIF 
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Streaming in the car_recordings/////////////////////////////////////////////////////////////////
	IF steal2_goals = 0
		//recording 1
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 70 
			REQUEST_CAR_RECORDING 70
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 71 
			REQUEST_CAR_RECORDING 71
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 72 
			REQUEST_CAR_RECORDING 72
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 73 
			REQUEST_CAR_RECORDING 73
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 74 
			REQUEST_CAR_RECORDING 74
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 75 
			REQUEST_CAR_RECORDING 75
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 76 
			REQUEST_CAR_RECORDING 76
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 77 
			REQUEST_CAR_RECORDING 77
		ENDIF	
		//recording 2
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 78 
			REQUEST_CAR_RECORDING 78
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 79 
			REQUEST_CAR_RECORDING 79
		ENDIF	
	ENDIF
	IF steal2_goals = 2
		//recording 3
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 80 
			REQUEST_CAR_RECORDING 80
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 81 
			REQUEST_CAR_RECORDING 81
		ENDIF	
		/*
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 82 
			REQUEST_CAR_RECORDING 82
		ENDIF	
		*/
		//recording 4
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 83 
			REQUEST_CAR_RECORDING 83
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 84 
			REQUEST_CAR_RECORDING 84
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 85 
			REQUEST_CAR_RECORDING 85
		ENDIF	
	ENDIF
	IF steal2_goals = 4
		//recording 5
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 86 
			REQUEST_CAR_RECORDING 86
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 87 
			REQUEST_CAR_RECORDING 87
		ENDIF
	ENDIF	
	IF steal2_goals = 5
		//recording 6
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 88 
			REQUEST_CAR_RECORDING 88
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 89 
			REQUEST_CAR_RECORDING 89
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 90 
			REQUEST_CAR_RECORDING 90
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 91 
			REQUEST_CAR_RECORDING 91
		ENDIF
	ENDIF	
	IF steal2_goals = 6
		//recording 7
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 92 
			REQUEST_CAR_RECORDING 92
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 93 
			REQUEST_CAR_RECORDING 93
		ENDIF	
	ENDIF
	IF steal2_goals = 7
		//recording 8
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 94 
			REQUEST_CAR_RECORDING 94
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 95 
			REQUEST_CAR_RECORDING 95
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 96 
			REQUEST_CAR_RECORDING 96
		ENDIF	
		//recording 9
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 97 
			REQUEST_CAR_RECORDING 97
		ENDIF	
	ENDIF
	IF steal2_goals = 8
		//recording 10
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 98 
			REQUEST_CAR_RECORDING 98
		ENDIF	
		//recording 11
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 65 
			REQUEST_CAR_RECORDING 65
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 66 
			REQUEST_CAR_RECORDING 66
		ENDIF	
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 67 
			REQUEST_CAR_RECORDING 67
		ENDIF	
	ENDIF

	IF steal2_goals = 11
		//recording 12
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 99 
			REQUEST_CAR_RECORDING 99
		ENDIF	
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////failing mission/////////////////////////////////////////////////////////////////////////////////
	IF steal2_goals > 0 
		IF steal2_goals < 13
			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer st2_cesars_car 150.0 150.0 FALSE		
				IF NOT IS_CAR_ON_SCREEN st2_cesars_car 
					CLEAR_PRINTS
					PRINT_NOW ( STE2_06 ) 4000 1 //Cesar got too far ahead.
					GOTO mission_steal2_failed
				ENDIF
			ENDIF
			
			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer st2_players_car 120.0 120.0 FALSE		
				IF NOT IS_CAR_ON_SCREEN st2_players_car 
					CLEAR_PRINTS
					PRINT_NOW ( STE2_09 ) 4000 1 //The police recovered one of the cars!
					GOTO mission_steal2_failed
				ENDIF
			ENDIF
			
			IF NOT IS_CHAR_IN_CAR scplayer st2_players_car 
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer st2_players_car 160.0 160.0 FALSE   
					IF NOT IS_CAR_ON_SCREEN st2_players_car 
						CLEAR_PRINTS
						PRINT_NOW ( STE2_08 ) 4000 1 //The target car was destroyed.
						GOTO mission_steal2_failed
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF	  

	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////failing mission/////////////////////////////////////////////////////////////////////////////////
	IF steal2_goals > 0 
		IF steal2_goals < 14
			GOSUB st2_blippage
		ENDIF
	ENDIF
	
	
	///ingame dialogue///
	GOSUB st2_overall_dialogue


GOTO mission_steal2_loop 




// Mission steal2 failed
mission_steal2_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission steal2 passed
mission_steal2_passed:
flag_steal_mission_counter ++
/*
IF flag_steal_mission_counter = 2
    REMOVE_BLIP trace_contact_blip
    ADD_SPRITE_BLIP_FOR_CONTACT_POINT traceX traceY traceZ trace_blip_icon trace_contact_blip          
ENDIF
IF flag_steal_mission_counter = 3
    START_NEW_SCRIPT zero_mission_loop
    REMOVE_BLIP zero_contact_blip
    ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip 
ENDIF
*/
SWITCH_CAR_GENERATOR steal_car_cargen2 101
SWITCH_CAR_GENERATOR steal_car_cargen3 101
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!" //100 being the amount of cash
ADD_SCORE player1 5000//amount of cash
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect


PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED STEAL_2
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT steal2_mission_loop
RETURN
		

// mission cleanup
mission_cleanup_steal2:

//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
REMOVE_CHAR_ELEGANTLY cesar
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
MARK_MODEL_AS_NO_LONGER_NEEDED SULTAN 
MARK_MODEL_AS_NO_LONGER_NEEDED ELEGY
MARK_MODEL_AS_NO_LONGER_NEEDED WMYBU
MARK_MODEL_AS_NO_LONGER_NEEDED MULE
MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX
MARK_MODEL_AS_NO_LONGER_NEEDED TRAM
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARSF
MARK_MODEL_AS_NO_LONGER_NEEDED SFPD1
MARK_MODEL_AS_NO_LONGER_NEEDED COACH
MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
MARK_VEHICLE_MOD_AS_NO_LONGER_NEEDED nto_b_s 
MARK_MODEL_AS_NO_LONGER_NEEDED DOZER
MARK_MODEL_AS_NO_LONGER_NEEDED CEMENT
//MARK_MODEL_AS_NO_LONGER_NEEDED UTILITY
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_ROADBARRIER_3
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_ROADBARRIER_4
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_SKIP
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_DUMPSTER
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_RAMP
DONT_SUPPRESS_ANY_CAR_MODELS
REMOVE_ANIMATION PLAYIDLES
REMOVE_BLIP st2_cesar_blip
UNLOAD_SPECIAL_CHARACTER 1
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
st2_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD cesar 
	CLEAR_PRINTS
	PRINT_NOW ( STE2_07 ) 4000 1 //Cesar was killed.
	st2_deathcheck_flag = 1
ENDIF

IF IS_CAR_DEAD st2_players_car
	CLEAR_PRINTS
	PRINT_NOW ( STE2_08 ) 4000 1 //The target car was destroyed. 
	st2_deathcheck_flag = 1
ENDIF

IF IS_CAR_DEAD st2_cesars_car 
	CLEAR_PRINTS
	PRINT_NOW ( STE2_08 ) 4000 1 //The target car was destroyed.
	st2_deathcheck_flag = 1
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_cesar_group:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF st2_flag_cesar_in_group = 1
    IF NOT IS_GROUP_MEMBER cesar Players_Group
        REMOVE_BLIP st2_cesar_blip
        ADD_BLIP_FOR_CHAR cesar st2_cesar_blip
		SET_BLIP_AS_FRIENDLY st2_cesar_blip TRUE
        st2_flag_cesar_in_group = 0
    ENDIF
ENDIF

IF st2_flag_cesar_in_group = 0 
    IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cesar 8.0 8.0 8.0 FALSE
        IF NOT IS_GROUP_MEMBER cesar Players_Group
        	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
        	SET_GROUP_MEMBER Players_Group cesar 
		ENDIF
        REMOVE_BLIP st2_cesar_blip
		ADD_BLIP_FOR_COORD -1632.9 1207.4 6.1 st2_cesar_blip
        st2_flag_cesar_in_group = 1
    ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_blippage:///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF st2_car_check_flag = 0
	IF IS_CHAR_IN_CAR scplayer st2_players_car
		CLEAR_THIS_PRINT IN_VEH 
		REMOVE_BLIP st2_cesar_blip
		IF steal2_goals = 13
			ADD_BLIP_FOR_COORD -2024.7 179.4 27.4 st2_cesar_blip
			PRINT ( STE2_05 ) 4000 1 //Drive back to the hub.
		ELSE
			ADD_BLIP_FOR_CAR st2_cesars_car st2_cesar_blip
			SET_BLIP_AS_FRIENDLY st2_cesar_blip TRUE
			PRINT ( STE2_02 ) 7000 1 //Stay close to Cesar as you return to the hub.
		ENDIF
		st2_car_check_flag = 1
	ENDIF
ENDIF

IF st2_car_check_flag = 1 
	IF NOT IS_CHAR_IN_CAR scplayer st2_players_car
		CLEAR_THIS_PRINT STE2_05 
		CLEAR_THIS_PRINT STE2_02
		PRINT ( STE2_11 ) 7000 1 //Get back in the car.
		REMOVE_BLIP st2_cesar_blip
		ADD_BLIP_FOR_CAR st2_players_car st2_cesar_blip
		SET_BLIP_AS_FRIENDLY st2_cesar_blip TRUE
		st2_car_check_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_sorting_speed://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
GET_CHAR_COORDINATES scplayer st2_player_x st2_player_y z

GET_CAR_COORDINATES st2_cesars_car st2_cesar_x st2_cesar_y z

GET_DISTANCE_BETWEEN_COORDS_2D st2_player_x st2_player_y st2_cesar_x st2_cesar_y st2_temp_x

////////////////////////////////////////////////////////////////////////////////////////////
/////  playback ranges from 0.0 - 2.0 													////
/////  sqrt of tempx is from 0.0 (close) to 100.0 (far)									////
/////  when tempx < 21, playback speed should be 1.0 - 2.0 (i.e. speed car up a bit)    ////   
/////  when tempx > 20, playback speed should be 0.0 - 1.0 (i.e. slow car down a bit)   ////
/////  speed should never be above 2.0 or below 0.0										////
/////  formula for < 21 = 2.0 - (actual / 20.0) 										////
/////  formula for > 21 = 1.0 - ((actual - 20.0) / 80.0) 								////
////////////////////////////////////////////////////////////////////////////////////////////

IF st2_temp_x < 21.0 
	st2_temp_x = st2_temp_x / 20.0
	st2_temp_y = st2_temp_x
	st2_temp_x = 2.0 - st2_temp_y
ELSE
	st2_temp_x = st2_temp_x - 20.0
	//st2_tempx = st2_tempx / 80.0
	st2_temp_x = st2_temp_x / 90.0

	st2_temp_y = st2_temp_x
	st2_temp_x = 1.0 - st2_temp_y
ENDIF
 	 
st2_temp_x = st2_temp_x + 0.3	 ////DEBUG - this is tweaking... add to speed up, subtract to slow down.

st2_playback_speed = st2_temp_x

IF st2_playback_speed > 2.0
	st2_playback_speed = 2.0
ENDIF
IF st2_playback_speed < 0.5
	st2_playback_speed = 0.5
ENDIF


IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cesars_car
	SET_PLAYBACK_SPEED st2_cesars_car st2_playback_speed 
ENDIF

IF NOT IS_CAR_DEAD st2_tram 
	IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_tram 
		SET_PLAYBACK_SPEED st2_tram st2_playback_speed
	ENDIF 
ENDIF


//setting the speed of the other cars
IF steal2_goals = 1
	IF NOT IS_CAR_DEAD st2_cars[2]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[2] 
			SET_PLAYBACK_SPEED st2_cars[2] st2_playback_speed
		ENDIF 
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[3]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[3] 
			SET_PLAYBACK_SPEED st2_cars[3] st2_playback_speed
		ENDIF 
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[4]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[4] 
			SET_PLAYBACK_SPEED st2_cars[4] st2_playback_speed
		ENDIF 
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[5]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[5] 
			SET_PLAYBACK_SPEED st2_cars[5] st2_playback_speed
		ENDIF 
	ENDIF								 
	IF NOT IS_CAR_DEAD st2_cars[6]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[6] 
			SET_PLAYBACK_SPEED st2_cars[6] st2_playback_speed
		ENDIF 
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[7]  
		IF IS_PLAYBACK_GOING_ON_FOR_CAR st2_cars[7] 
			SET_PLAYBACK_SPEED st2_cars[7] st2_playback_speed
		ENDIF 
	ENDIF
ENDIF

/*
IF steal2_goals = 3
	IF NOT IS_CAR_DEAD st2_cars[0] 
		SET_PLAYBACK_SPEED st2_cars[0] st2_playback_speed
	ENDIF
ENDIF
*/
IF steal2_goals = 4
	IF NOT IS_CAR_DEAD st2_cars[15] 
		SET_PLAYBACK_SPEED st2_cars[15] st2_playback_speed
	ENDIF
ENDIF

IF steal2_goals = 5
	IF NOT IS_CAR_DEAD st2_cars[2] 
		SET_PLAYBACK_SPEED st2_cars[2] st2_playback_speed
	ENDIF
ENDIF

IF steal2_goals = 6
	IF NOT IS_CAR_DEAD st2_cars[14] 
		SET_PLAYBACK_SPEED st2_cars[14] st2_playback_speed
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[16] 
		SET_PLAYBACK_SPEED st2_cars[16] st2_playback_speed
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[17] 
		SET_PLAYBACK_SPEED st2_cars[17] st2_playback_speed
	ENDIF
ENDIF

IF steal2_goals = 7
	IF NOT IS_CAR_DEAD st2_cars[3] 
		SET_PLAYBACK_SPEED st2_cars[3] st2_playback_speed
	ENDIF
ENDIF

IF steal2_goals = 8
	IF NOT IS_CAR_DEAD st2_cars[7] 
		SET_PLAYBACK_SPEED st2_cars[7] st2_playback_speed
	ENDIF
	IF NOT IS_CAR_DEAD st2_cars[8] 
		SET_PLAYBACK_SPEED st2_cars[8] st2_playback_speed
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_my_number_plates:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 st2_no_plates_flag
	IF st2_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates got_m00_
	ENDIF 
	IF st2_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates m00tv_4u 
	ENDIF
	IF st2_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates mathew_2 
	ENDIF 
	IF st2_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates d4_dew0r 
	ENDIF 
	IF st2_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates d0de_777 
	ENDIF 
	IF st2_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates dam0_666 
	ENDIF 
	IF st2_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates C0NEY_88 
	ENDIF 
	IF st2_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates pre4cher 
	ENDIF 
	IF st2_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates dbp_4ndy 
	ENDIF 
	IF st2_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates ev1l_sly 
	ENDIF 
	IF st2_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates n1_r4v3n 
	ENDIF 
	IF st2_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates d1vx_z00 
	ENDIF 
	IF st2_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates mr_b3nn 
	ENDIF 
	IF st2_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates r3d_r4sp 
	ENDIF 
	IF st2_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates La_B0mba 
	ENDIF 
	IF st2_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates L3337_0g 
	ENDIF 
	IF st2_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates budd4h_X 
	ENDIF 
	IF st2_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates t3h_buck 
	ENDIF 
	IF st2_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates CHUNKY_1 
	ENDIF 
	IF st2_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates ev1l_bnz 
	ENDIF 
	IF st2_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates S4ND_M4N 
	ENDIF 
	IF st2_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates RKK_DBP1 
	ENDIF 
	IF st2_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates RE1_K0KU 
	ENDIF 
	IF st2_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates s3xy_jud 
	ENDIF 
	IF st2_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates sunra_93 
	ENDIF 
	IF st2_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates UG_FuX69 
	ENDIF 
	IF st2_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates Li0n_Cum 
	ENDIF 
	IF st2_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates rkk_pwnd 
	ENDIF 
	IF st2_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates haze_b0b 
	ENDIF 
	IF st2_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates t3h_fluf 
	ENDIF 
	IF st2_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates BM_4NDY_ 
	ENDIF 
	IF st2_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates BM_D34N_ 
	ENDIF 
	IF st2_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates BM_L4C3Y 
	ENDIF 
	IF st2_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates BM_D3V__ 
	ENDIF 
	IF st2_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates NU_SK00L 
	ENDIF 
	IF st2_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates G4L_AVET 
	ENDIF 
	IF st2_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR st2_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_overall_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF st2_speech_goals = 1 //initial dialogue with cesar
	IF st2_speech_control_flag < st2_last_label
		GOSUB st2_loading_dialogue
		GOSUB st2_playing_dialogue
		IF NOT IS_CHAR_DEAD cesar
			GOSUB st2_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
			st2_slot1 = 0
			st2_slot2 = 0
		ENDIF
	ELSE
		st2_speech_goals = 0
	ENDIF
ENDIF

IF st2_speech_goals = 4 //cutscene before going upstairs at showroom
OR st2_speech_goals = 5 //cutscene upstairs at showroom
OR st2_speech_goals = 6 //cutscene jumping out of the showroom
	IF st2_speech_control_flag < st2_last_label
		GOSUB st2_loading_dialogue
		GOSUB st2_playing_dialogue
		GOSUB st2_finishing_dialogue  
	ELSE
		st2_speech_goals = 0
	ENDIF
ENDIF

IF st2_speech_goals = 7 //walkie talkie working?
OR st2_speech_goals = 8 //all the main dialogue
OR st2_speech_goals = 9 //get back to the garage
OR st2_speech_goals = 10 //did you see that?
OR st2_speech_goals = 11 //back up!
OR st2_speech_goals = 12 //see you back at the hub
	IF st2_speech_control_flag < st2_last_label
		GOSUB st2_loading_dialogue
		GOSUB st2_playing_dialogue
		IF NOT IS_CHAR_DEAD cesar
			GOSUB st2_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
			st2_slot1 = 0
			st2_slot2 = 0
		ENDIF
	ELSE
		st2_speech_goals = 0
	ENDIF
ENDIF

IF steal2_goals = 0
	IF st2_control_flag = 0
		IF IS_GROUP_MEMBER cesar Players_Group 
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D cesar scplayer 10.0 10.0 8.0 FALSE
	            IF IS_CHAR_ON_FOOT scplayer
    	        AND IS_CHAR_ON_FOOT cesar
					IF st2_speech_goals = 2	//talking on the way to the car showroom
					OR st2_speech_goals = 3 //talking on the way to the car showroom
						IF st2_speech_control_flag < st2_last_label
							GOSUB st2_loading_dialogue
							GOSUB st2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB st2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
								st2_slot1 = 0
								st2_slot2 = 0
							ENDIF
						ELSE
							st2_speech_goals = 0
						ENDIF
					ENDIF
	            ENDIF   
                IF IS_CHAR_IN_ANY_CAR scplayer
                AND IS_CHAR_IN_ANY_CAR cesar
					IF st2_speech_goals = 2	//talking on the way to the car showroom
					OR st2_speech_goals = 3 //talking on the way to the car showroom
						IF st2_speech_control_flag < st2_last_label
							GOSUB st2_loading_dialogue
							GOSUB st2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB st2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
								st2_slot1 = 0
								st2_slot2 = 0
							ENDIF
						ELSE
							st2_speech_goals = 0
						ENDIF
					ENDIF
                ENDIF
			ENDIF

			/*
			IF st2_speech_goals = 2	//talking on the way to the car showroom
			OR st2_speech_goals = 3 //talking on the way to the car showroom
				IF st2_speech_control_flag < st2_last_label
					GOSUB st2_loading_dialogue
					GOSUB st2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB st2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
						st2_slot1 = 0
						st2_slot2 = 0
					ENDIF
				ELSE
					st2_speech_goals = 0
				ENDIF
			ENDIF
			*/
		ELSE		  
			IF st2_speech_goals < 14 
				IF st2_speech_control_flag < st2_last_label
					st2_speech_control_flag ++
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag]
				CLEAR_PRINTS
				st2_storing_speech_goals_number = st2_speech_goals 
				st2_storing_speech_control_number = st2_speech_control_flag
				st2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 st2_speech_control_flag
				st2_random_last_label = st2_speech_control_flag + 1 
				GOSUB st2_dialogue_setup
			ENDIF
		ENDIF
		
		IF st2_speech_goals = 14 //cesar is out of the group
			IF NOT IS_GROUP_MEMBER cesar Players_Group
				IF st2_speech_control_flag < st2_last_label
					GOSUB st2_loading_dialogue
					GOSUB st2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB st2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag] 
						st2_slot1 = 0
						st2_slot2 = 0
					ENDIF
				ELSE
					PRINT ( STE2_10 ) 7000 1 //You have left Cesar behind.
					st2_speech_goals = 15
				ENDIF
			ELSE
				PRINT ( STE2_10 ) 7000 1 //You have left Cesar behind.
				st2_speech_goals = 15	
			ENDIF
		ENDIF

		IF st2_speech_goals = 15 //cesar has been out of the group and has returned
			IF IS_GROUP_MEMBER cesar Players_Group 
				st2_speech_goals = 16
				st2_speech_control_flag = 0
				CLEAR_PRINTS
				//GOSUB st2_dialogue_setup
			ENDIF
		ENDIF

		IF st2_speech_goals = 16 //cesar is back in group
			IF IS_GROUP_MEMBER cesar Players_Group 	
				timerb = 0
				st2_speech_goals = st2_storing_speech_goals_number
				st2_speech_control_flag = st2_storing_speech_control_number
				GOSUB st2_dialogue_setup
				IF st2_storing_speech_goals_number = 0
					PRINT ( STE2_01 ) 7000 1 //Go and get the cars from the car showroom.
					timerb = 0
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag]
				st2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 st2_speech_control_flag
				st2_random_last_label = st2_speech_control_flag + 1 
				GOSUB st2_dialogue_setup
			ENDIF
		ENDIF
	ENDIF
ENDIF	

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_dialogue_setup://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF st2_speech_goals = 1
	$st2_print_label[0] = &STL2_AA // Ok, let's go and get those wheels!		
	st2_audio_label[0] = SOUND_STL2_AA 
	st2_last_label = 1
ENDIF

IF st2_speech_goals = 2
	$st2_print_label[0] = &STL2_BA // I like this place, you know.		
	$st2_print_label[1] = &STL2_BB // What? Where?		
	$st2_print_label[2] = &STL2_BC // San Fierro, man.		
	$st2_print_label[3] = &STL2_BD // My home will always be the Varrios and El Corona,		
	$st2_print_label[4] = &STL2_BE // but this city, it has something gentle about it.		
	$st2_print_label[5] = &STL2_BF // Yeah, I know what you mean.		
	$st2_print_label[6] = &STL2_BG // Kendl seems to like it too, you know?		
	$st2_print_label[7] = &STL2_BH // Oh yeah, she's really getting her head into this business thing.		
	$st2_print_label[8] = &STL2_BJ // That's good, she's always been the brains in the family. 		
	$st2_print_label[9] = &STL2_BK // She should get out of the ghetto and make something of herself.		
	$st2_print_label[10] = &STL2_BL // I think she's aiming to make something out of ALL of us, eh!	
	$st2_print_label[11] = &STL2_BM // Yeah, she's the moms of the family now...	

	st2_audio_label[0] = SOUND_STL2_BA 
	st2_audio_label[1] = SOUND_STL2_BB 
	st2_audio_label[2] = SOUND_STL2_BC 
	st2_audio_label[3] = SOUND_STL2_BD 
	st2_audio_label[4] = SOUND_STL2_BE 
	st2_audio_label[5] = SOUND_STL2_BF 
	st2_audio_label[6] = SOUND_STL2_BG 
	st2_audio_label[7] = SOUND_STL2_BH 
	st2_audio_label[8] = SOUND_STL2_BJ 
	st2_audio_label[9] = SOUND_STL2_BK 
	st2_audio_label[10] = SOUND_STL2_BL 
	st2_audio_label[11] = SOUND_STL2_BM 
	st2_last_label = 12
ENDIF

IF st2_speech_goals = 3
	$st2_print_label[0] = &STL2_CA // Who's this 'Truth' guy, holmes? Is he all there?
	$st2_print_label[1] = &STL2_CB // He just sees everything from a very different perspective, is all.
	$st2_print_label[2] = &STL2_CC // At first I thought he was just another acid casualty fruit cake.
	$st2_print_label[3] = &STL2_CD // But some of the things he says...		
	$st2_print_label[4] = &STL2_CE // I don't know, man, it ain't all bullshit....	
	$st2_print_label[5] = &STL2_CF // You gonna become an alien hunter, holmes?
	$st2_print_label[6] = &STL2_CG // I'll take a rain check on that one.
	
	st2_audio_label[0] = SOUND_STL2_CA 
	st2_audio_label[1] = SOUND_STL2_CB 
	st2_audio_label[2] = SOUND_STL2_CC 
	st2_audio_label[3] = SOUND_STL2_CD 
	st2_audio_label[4] = SOUND_STL2_CE 
	st2_audio_label[5] = SOUND_STL2_CF 
	st2_audio_label[6] = SOUND_STL2_CG 
	st2_last_label = 7
ENDIF

IF st2_speech_goals = 4
	$st2_print_label[0] = &STL2_DA // This is the place!
	$st2_print_label[1] = &STL2_DB // Here we go, holmes.
	$st2_print_label[2] = &STL2_DC // This is it, CJ!
	
	$st2_print_label[3] = &STL2_DD // The cars are upstairs!
	$st2_print_label[4] = &STL2_DE // The cars are on the first floor
	$st2_print_label[5] = &STL2_DF // They're in the first floor showroom

	$st2_print_label[6] = &STL2_DH // What, how are we going to get 'em down?
	$st2_print_label[7] = &STL2_DJ // First floor? Shit...		
	$st2_print_label[8] = &STL2_DK // Great, nothing's ever easy is it!

	st2_audio_label[0] = SOUND_STL2_DA 
	st2_audio_label[1] = SOUND_STL2_DB 
	st2_audio_label[2] = SOUND_STL2_DC 
	st2_audio_label[3] = SOUND_STL2_DD 
	st2_audio_label[4] = SOUND_STL2_DE 
	st2_audio_label[5] = SOUND_STL2_DF 
	st2_audio_label[6] = SOUND_STL2_DH 
	st2_audio_label[7] = SOUND_STL2_DJ 
	st2_audio_label[8] = SOUND_STL2_DK 
	st2_last_label = st2_random_last_label
ENDIF

IF st2_speech_goals = 5
	$st2_print_label[0] = &STL2_EA // Can I help you two, eerr, gentlemen?
	$st2_print_label[1] = &STL2_EB // Yeah, you can help us by going and helping some other motherfucker.
	$st2_print_label[2] = &STL2_EC // Y- yeah, that sounds like a good idea!
	$st2_print_label[3] = &STL2_ED // Well, sometimes you gotta just do it, I guess.		
	$st2_print_label[4] = &STL2_EE // It's follow the leader, CJ, you better keep up!	

	st2_audio_label[0] = SOUND_STL2_EA 
	st2_audio_label[1] = SOUND_STL2_EB 
	st2_audio_label[2] = SOUND_STL2_EC 
	st2_audio_label[3] = SOUND_STL2_ED 
	st2_audio_label[4] = SOUND_STL2_EE 
	st2_last_label = 5
ENDIF
 
IF st2_speech_goals = 6
	$st2_print_label[0] = &STL2_EF // Holy fuck!

	st2_audio_label[0] = SOUND_STL2_EF 
	st2_last_label = 1
ENDIF

IF st2_speech_goals = 7
	$st2_print_label[0] = &STL2_FA // Hey, CJ, is this walkie talkie working?
	$st2_print_label[1] = &STL2_FB // Yeah, reading you loud and clear!
	$st2_print_label[2] = &STL2_FC // C'mon, CJ, see if you can keep up with Cesar Vialpando!

	st2_audio_label[0] = SOUND_STL2_FA 
	st2_audio_label[1] = SOUND_STL2_FB 
	st2_audio_label[2] = SOUND_STL2_FC 
	st2_last_label = 3
ENDIF

IF st2_speech_goals = 8
	$st2_print_label[0] = &STL2_FD // Beat the tram up the hill!
	$st2_print_label[1] = &STL2_FE // Piece of chocolate cake!
	$st2_print_label[2] = &STL2_FF // This tram driver must be shitting himself!
	$st2_print_label[3] = &STL2_FG // Hello cops, what took you, eh?
	$st2_print_label[4] = &STL2_FH // Hey, CJ, watch this ... NITRO!		
	$st2_print_label[5] = &STL2_FJ // Follow me down the hill, holmes!
	//$st2_print_label[6] = &STL2_FK // Bike in the road ... watch out!	
	$st2_print_label[7] = &STL2_FO // Hello. Cops, back again you idiotas?
	$st2_print_label[8] = &STL2_FP // Get out of the fucking road!
	$st2_print_label[9] = &STL2_FQ // Holy SHIIIIIIIT!
	$st2_print_label[10] = &STL2_HB // I think we lost 'em.   
	$st2_print_label[11] = &STL2_HC // There's a shortcut this way!

	st2_audio_label[0] = SOUND_STL2_FD 
	st2_audio_label[1] = SOUND_STL2_FE 
	st2_audio_label[2] = SOUND_STL2_FF 
	st2_audio_label[3] = SOUND_STL2_FG 
	st2_audio_label[4] = SOUND_STL2_FH 
	st2_audio_label[5] = SOUND_STL2_FJ 
	//st2_audio_label[6] = SOUND_STL2_FK 
	st2_audio_label[7] = SOUND_STL2_FO 
	st2_audio_label[8] = SOUND_STL2_FP 
	st2_audio_label[9] = SOUND_STL2_FQ 
	st2_audio_label[10] = SOUND_STL2_HB 
	st2_audio_label[11] = SOUND_STL2_HC 
	st2_last_label = st2_random_last_label
ENDIF

IF st2_speech_goals = 9
	$st2_print_label[0] = &STL2_FL // Ok, Cesar, that's enough fun.	
	$st2_print_label[1] = &STL2_FM // Let's get these cars back to the garage!
	$st2_print_label[2] = &STL2_FN // Ok, CJ, I know a quick route!

	st2_audio_label[0] = SOUND_STL2_FL 
	st2_audio_label[1] = SOUND_STL2_FM 
	st2_audio_label[2] = SOUND_STL2_FN 
	st2_last_label = 3
ENDIF

IF st2_speech_goals = 10
	$st2_print_label[0] = &STL2_FR // Fuck, holmes, did you see that?
	$st2_print_label[1] = &STL2_GA // Yeah, can we think about getting back 
	$st2_print_label[2] = &STL2_GB // before I end up in a carwreck barbeque?
	$st2_print_label[3] = &STL2_HA // Sure thing, holmes.						    
	
	st2_audio_label[0] = SOUND_STL2_FR 
	st2_audio_label[1] = SOUND_STL2_GA 
	st2_audio_label[2] = SOUND_STL2_GB 
	st2_audio_label[3] = SOUND_STL2_HA 
	st2_last_label = 4																		   
ENDIF

IF st2_speech_goals = 11
	$st2_print_label[0] = &STL2_HD // Uh-oh, more cops!
	$st2_print_label[1] = &STL2_HE // BACK UP, HOLMES, BACK UP!

	st2_audio_label[0] = SOUND_STL2_HD 
	st2_audio_label[1] = SOUND_STL2_HE 
	st2_last_label = 2
ENDIF

IF st2_speech_goals = 12
	$st2_print_label[0] = &STL2_HF // Ok, we're good.
	$st2_print_label[1] = &STL2_HG // See you back at the hub, CJ!

	st2_audio_label[0] = SOUND_STL2_HF 
	st2_audio_label[1] = SOUND_STL2_HG 
	st2_last_label = 2
ENDIF

IF st2_speech_goals = 14
	$st2_print_label[0] = &CESX_BA // Wait up, CJ!
	$st2_print_label[1] = &CESX_BB // Hang ten, CJ!
	$st2_print_label[2] = &CESX_BC // Hold up!
	$st2_print_label[3] = &CESX_BD // Slow down, Carl!

	st2_audio_label[0] = SOUND_CESX_BA 
	st2_audio_label[1] = SOUND_CESX_BB 
	st2_audio_label[2] = SOUND_CESX_BC 
	st2_audio_label[3] = SOUND_CESX_BD 
 	st2_last_label = st2_random_last_label 
ENDIF

st2_slot_load = st2_speech_control_flag
st2_slot1 = 0
st2_slot2 = 0
st2_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_loading_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF st2_slot_load < st2_last_label 
	//slot 1
	IF st2_slot1 = 0
		LOAD_MISSION_AUDIO 1 st2_audio_label[st2_slot_load] 
		st2_slot_load ++ 
		st2_slot1 = 1
	ENDIF

	//slot 2		    
	IF st2_slot2 = 0
		LOAD_MISSION_AUDIO 2 st2_audio_label[st2_slot_load] 
		st2_slot_load ++ 
		st2_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_playing_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF st2_play_which_slot = 1 
	IF st2_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $st2_print_label[st2_speech_control_flag] ) 4500 1 
			st2_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF st2_play_which_slot = 2 
	IF st2_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $st2_print_label[st2_speech_control_flag] ) 4500 1 
			st2_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
st2_finishing_dialogue://///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF st2_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag]
		st2_speech_control_flag ++		
		st2_play_which_slot = 2
		st2_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF st2_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $st2_print_label[st2_speech_control_flag]
		st2_speech_control_flag ++		
		st2_play_which_slot = 1
		st2_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}



