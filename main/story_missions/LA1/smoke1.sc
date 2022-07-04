MISSION_START
// *****************************************************************************************
// *********************************** TWAR mission 7  ************************************* 
// *************************************** Payback *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// Mission start stuff
SCRIPT_NAME twar7
GOSUB mission_start_twar7
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_twar7_failed
ENDIF
GOSUB mission_cleanup_twar7
MISSION_END
{
// Variables for mission
//people
LVAR_INT tw7_ass_bandit tw7_ass_bandit_bike tw7_players_bike tw7_smokes_car 
LVAR_INT tw7_bj_male tw7_bj_female tw7_lying_down_ped tw7_friend_of_lying_down_ped
LVAR_INT tw7_car[10] tw7_char[10] tw7_goon[3]
LVAR_INT tw7_cutscene_object

//blips
LVAR_INT tw7_control_blip  

//flags
LVAR_INT tw7_goals tw7_control_flag tw7_skip_cutscene_flag tw7_deathcheck_flag   
LVAR_INT tw7_car_check_flag 
LVAR_INT tw7_flag_mc_strap_in_group
LVAR_INT tw7_car_nodes 
LVAR_INT tw7_model tw7_class
LVAR_INT tw7_which_gun
LVAR_INT tw7_no_plates_flag tw7_no_plates
LVAR_INT tw7_car_playback_flag
LVAR_INT tw7_car_health_flag  
LVAR_INT tw7_special_trip_skip
LVAR_FLOAT tw7_anim_time

//speech
LVAR_INT tw7_speech_goals tw7_speech_control_flag tw7_speech_flag 
LVAR_TEXT_LABEL tw7_print_label[13] 
LVAR_INT tw7_audio_label[13] 
LVAR_INT tw7_last_label    
LVAR_INT tw7_slot1 tw7_slot2 tw7_slot_load tw7_play_which_slot
LVAR_INT tw7_random_last_label
LVAR_INT tw7_storing_speech_control_number tw7_storing_speech_goals_number 

//coords
LVAR_FLOAT tw7_playerx tw7_playery tw7_ass_banditx tw7_ass_bandity tw7_tempx tw7_tempy tw7_tempz tw7_playback_speed 
LVAR_FLOAT tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz tw7_plyr_cut_heading


//sequences/decision makers/threat lists/attractors/groups
LVAR_INT tw7_seq tw7_group_decision_maker tw7_group_seq tw7_char_decision_maker tw7_empty_decision_maker  
//debug
// 



// ****************************************Mission Start************************************
mission_start_twar7:

flag_player_on_mission = 1
REGISTER_MISSION_GIVEN

// the pathing is now just turned off during the chase
//DISPLAY_RADAR 5 // RADAR IGNORE SWITCHED OFF NODES

LOAD_MISSION_TEXT SMOKE1
IF flag_player_on_mission = 0 
	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL03 1543.2 -1687.0 12.5 mc_strap
	ADD_BLIP_FOR_COORD 2454.4 -1284.5 22.7 tw7_control_blip
ENDIF
CLEAR_PRINTS
WAIT 0
// *************************************Set Flags/variables*********************************
tw7_goals = 0
tw7_control_flag = 0
tw7_skip_cutscene_flag = 0
tw7_deathcheck_flag = 0
tw7_speech_flag = 0 
tw7_car_check_flag = 0
tw7_flag_mc_strap_in_group = 0
tw7_car_nodes = 0
tw7_car_playback_flag = 0
tw7_car_health_flag = 0

tw7_playerx = 0.0 
tw7_playery = 0.0 
tw7_ass_banditx = 0.0 
tw7_ass_bandity = 0.0 
tw7_tempx = 0.0 
tw7_tempy = 0.0
tw7_tempz = 0.0
tw7_playback_speed = 0.0

tw7_speech_goals = 0
tw7_speech_control_flag = 0 
tw7_speech_flag = 0 
tw7_last_label = 0    
tw7_slot1 = 0 
tw7_slot2 = 0 
tw7_slot_load = 0 
tw7_play_which_slot = 0
tw7_random_last_label = 0
tw7_storing_speech_control_number = 0
tw7_storing_speech_goals_number = 0

tw7_plyr_cutx = 0.0 
tw7_plyr_cuty = 0.0 
tw7_plyr_cutz = 0.0
tw7_plyr_cut_heading = 0.0

tw7_model = 0
tw7_class = 0

tw7_which_gun = 0

tw7_no_plates_flag = 0
tw7_no_plates = 0

tw7_special_trip_skip = 0
tw7_anim_time = 0.0

// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
CLEAR_AREA 2072.3 -1695.2 12.5 80.0 TRUE 
CREATE_OBJECT Lae_smokecutscene 2055.0 -1695.0 12.5 tw7_cutscene_object 
LOAD_CUTSCENE SMOKE1A
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
DELETE_OBJECT tw7_cutscene_object
MAKE_PLAYER_GANG_REAPPEAR
SET_PLAYER_CONTROL player1 OFF
// ****************************************END OF CUTSCENE**********************************

SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------
REQUEST_MODEL GLENDALE

LOAD_SPECIAL_CHARACTER 1 smoke
LOAD_SPECIAL_CHARACTER 2 sweet

LOAD_ALL_MODELS_NOW 

/*
////////////debug/////////////////////////////////////
SWITCH_WIDESCREEN OFF
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0
REQUEST_MODEL PCJ600
LOAD_ALL_MODELS_NOW

LOAD_SCENE 2408.7 -1260.7 22.5 
CREATE_CAR PCJ600 2408.7 -1260.7 22.5 tw7_players_bike
SET_CAR_HEADING tw7_players_bike 96.2
WARP_CHAR_INTO_CAR scplayer tw7_players_bike 

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SET_PLAYER_CONTROL player1 ON
DO_FADE 0 FADE_IN 

blergh:
WAIT 0
GOTO blergh
*/
											  
/*
//////////////DEBUG/////////////////
LOAD_SPECIAL_CHARACTER 3 ogloc
REQUEST_MODEL LSV2
REQUEST_MODEL LSV3
REQUEST_MODEL PCJ600
LOAD_ALL_MODELS_NOW 
SET_CHAR_COORDINATES scplayer 2286.8 -1485.0 21.8
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL03 2285.6 -1483.1 21.7 mc_strap
SET_PLAYER_CONTROL player1 ON
tw7_goals = 15
GOTO twar7mainloop
//////////////DEBUG/////////////////
*/

/*
//////////////DEBUG/////////////////
LOAD_SPECIAL_CHARACTER 3 ogloc
REQUEST_MODEL LSV2
REQUEST_MODEL LSV3
REQUEST_MODEL PCJ600
LOAD_ALL_MODELS_NOW 
SET_CHAR_COORDINATES scplayer 2466.9 -1278.4 28.9
CREATE_CAR GLENDALE 2075.0 -1688.5 12.29 tw7_smokes_car
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2072.3 -1697.2 12.5 big_smoke
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 2072.3 -1696.2 12.5 sweet	
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL03 2467.8 -1275.6 28.8 mc_strap
SET_PLAYER_CONTROL player1 ON
tw7_goals = 3
DO_FADE 0 FADE_IN
GOTO twar7mainloop
//////////////DEBUG/////////////////
*/
/*
VAR_INT tw7_slot1 tw7_slot2 tw7_slot_load tw7_play_which_slot
VAR_INT tw7_storing_speech_control_number tw7_storing_speech_goals_number 
VAR_INT tw7_speech_goals tw7_speech_control_flag tw7_speech_flag
VIEW_INTEGER_VARIABLE tw7_speech_control_flag tw7_speech_control_flag 
VIEW_INTEGER_VARIABLE tw7_storing_speech_control_number tw7_storing_speech_control_number
VIEW_INTEGER_VARIABLE tw7_speech_goals tw7_speech_goals
VIEW_INTEGER_VARIABLE tw7_slot_load tw7_slot_load
*/

///setting up char and group decision makers
REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE


//starting intro scripted cut of player, smoke and sweet getting into the car
SWITCH_WIDESCREEN ON 
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
MAKE_PLAYER_GANG_DISAPPEAR

//SET_FIXED_CAMERA_POSITION 2074.1 -1686.0 13.9 0.0 0.0 0.0	
//POINT_CAMERA_AT_POINT 2074.7 -1707.0 13.2 JUMP_CUT 	

SET_FIXED_CAMERA_POSITION 2073.8699 -1682.7007 14.1963 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2073.6985 -1683.6857 14.1768 JUMP_CUT


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tw7_char_decision_maker
COPY_CHAR_DECISION_MAKER DM_PED_EMPTY tw7_empty_decision_maker  

LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM tw7_group_decision_maker
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_LEADER_ENTERED_CAR_AS_DRIVER
ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_LEADER_ENTERED_CAR_AS_DRIVER TASK_GROUP_ENTER_CAR_AND_PERFORM_SEQUENCE 100.0 100.0 100.0 100.0 1 1

CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_ACQUAINTANCE_PED_HATE
ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_USE_MEMBER_DECISION 0.0 100.0 0.0 0.0 1 0

CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_PLAYER_COMMAND_TO_GROUP
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE tw7_group_decision_maker EVENT_DAMAGE

SET_GROUP_DECISION_MAKER Players_Group tw7_group_decision_maker

//smokes car
CLEAR_AREA 2075.0 -1688.5 12.29 10.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
CREATE_CAR GLENDALE 2075.0 -1688.5 12.29 tw7_smokes_car
SET_CAR_HEADING tw7_smokes_car 359.74 
CHANGE_CAR_COLOUR tw7_smokes_car 98 14
SET_CAN_RESPRAY_CAR tw7_smokes_car FALSE  
SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL  

//smoke
CLEAR_AREA 2077.0 -1691.1 12.5 1.0 TRUE
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2077.0 -1691.1 12.5 big_smoke
//CLEAR_AREA 2072.3 -1697.2 12.5 1.0 TRUE
//CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2072.3 -1697.2 12.5 big_smoke
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE
SET_CHAR_HEADING big_smoke 255.7
SET_CHAR_NEVER_TARGETTED big_smoke TRUE  
SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
TASK_ENTER_CAR_AS_PASSENGER big_smoke tw7_smokes_car -2 0  
SET_CHAR_DECISION_MAKER big_smoke tw7_empty_decision_maker




//sweet
CLEAR_AREA 2075.3 -1693.4 12.5 1.0 TRUE
CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 2075.3 -1693.4 12.5 sweet	
//CLEAR_AREA 2072.3 -1696.2 12.5 1.0 TRUE
//CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 2072.3 -1696.2 12.5 sweet	
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE
SET_CHAR_NEVER_TARGETTED sweet TRUE  
SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE 
SET_ANIM_GROUP_FOR_CHAR sweet gang2
SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
TASK_ENTER_CAR_AS_PASSENGER sweet tw7_smokes_car -2 2  
SET_CHAR_DECISION_MAKER sweet tw7_empty_decision_maker

//scplayer
CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
CLEAR_AREA 2072.9 -1690.6 12.5 1.0 TRUE
SET_CHAR_COORDINATES scplayer 2072.9 -1690.6 12.5
//CLEAR_AREA 2072.3 -1695.2 12.5 1.0 TRUE
//SET_CHAR_COORDINATES scplayer 2072.3 -1695.2 12.5
SET_CHAR_HEADING scplayer 33.0 
SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
TASK_ENTER_CAR_AS_DRIVER scplayer tw7_smokes_car -2   

SWITCH_RANDOM_TRAINS OFF
DELETE_ALL_TRAINS

ENABLE_AMBIENT_CRIME FALSE

//switching burglary door off
SWITCH_ENTRY_EXIT LAHS1A FALSE

DO_FADE 500 FADE_IN
tw7_skip_cutscene_flag = 1
SKIP_CUTSCENE_START 

twar7mainloop:
WAIT 0 


	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_twar7_passed  
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB tw7_death_checks
	IF tw7_deathcheck_flag = 1
		GOTO mission_twar7_failed
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// FIXING SPECIAL SKIP ///////////////////////////////////////////////////////////////////////////


	IF tw7_goals = 0
	OR tw7_goals = 1
		IF tw7_special_trip_skip = 1
			IF IS_SKIP_WAITING_FOR_SCRIPT_TO_FADE_IN
				SET_PLAYER_CONTROL player1 OFF
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				tw7_speech_goals = 0

				//mc_strap
				IF IS_CHAR_DEAD mc_strap
					REQUEST_ANIMATION CAR_CHAT
					LOAD_SPECIAL_CHARACTER 3 ogloc
					LOAD_ALL_MODELS_NOW 
					 
					CREATE_CHAR PEDTYPE_MISSION2 SPECIAL03 1543.2 -1687.0 12.5 mc_strap
					SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR mc_strap FALSE 	
					SET_CHAR_HEADING mc_strap 97.2
					//TASK_PLAY_ANIM mc_strap SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
					SET_CHAR_NEVER_TARGETTED mc_strap TRUE  
					SET_CHAR_CANT_BE_DRAGGED_OUT mc_strap TRUE 
					SET_CHAR_SUFFERS_CRITICAL_HITS mc_strap FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER mc_strap TRUE  
					WARP_CHAR_INTO_CAR_AS_PASSENGER mc_strap tw7_smokes_car -1   
					SET_SENSE_RANGE mc_strap 80.0
				ENDIF 
				REMOVE_BLIP tw7_control_blip							
				ADD_BLIP_FOR_COORD 2454.4 -1284.5 22.7 tw7_control_blip
				PRINT_NOW ( SMK1_10 ) 7000 1 //Drive to the Ass bandits' house.
				SET_PLAYER_CONTROL player1 ON 
				DO_FADE 1000 FADE_IN
				tw7_speech_flag = 2
				tw7_control_flag = 2
				tw7_goals = 1
			ENDIF
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////entering Smokes car////////////////////////////////////////////////////////////////////////////
	IF tw7_goals = 0
		IF IS_CHAR_IN_CAR scplayer tw7_smokes_car 
			IF IS_CHAR_IN_CAR big_smoke tw7_smokes_car 
				IF IS_CHAR_IN_CAR sweet tw7_smokes_car 
					
					tw7_skip_cutscene_flag = 0
					SKIP_CUTSCENE_END
					GOSUB tw7_death_checks
					IF tw7_deathcheck_flag = 1
						GOTO mission_twar7_failed
					ENDIF
					
					IF tw7_skip_cutscene_flag = 1
						DO_FADE 500 FADE_OUT	
						WHILE GET_FADING_STATUS	
						   WAIT 0
						ENDWHILE 
						GOSUB tw7_death_checks
						IF tw7_deathcheck_flag = 1
							GOTO mission_twar7_failed
						ENDIF
					
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
						WARP_CHAR_INTO_CAR scplayer tw7_smokes_car 

						CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke 
						WARP_CHAR_INTO_CAR_AS_PASSENGER big_smoke tw7_smokes_car 0 
						
						CLEAR_CHAR_TASKS_IMMEDIATELY sweet 
						WARP_CHAR_INTO_CAR_AS_PASSENGER sweet tw7_smokes_car 2
					ENDIF 

					IF tw7_trip_skip_flag[0] = 1 
						//SET_UP_SKIP 1526.9 -1654.5 12.1 180.0
						SET_UP_SKIP_FOR_SPECIFIC_VEHICLE 1526.9 -1654.5 12.1 180.0 tw7_smokes_car  
						IF tw7_trip_skip_flag[1] = 0
							PRINT_HELP SKIP_1  
							tw7_trip_skip_flag[1] = 1
						ENDIF
						PRINT_NOW ( SMK1_02 ) 7000 1 //Go and pick up OG Loc from the Police Station.
					ENDIF
				
					IF tw7_trip_skip_flag[0] = 2 
						//SET_UP_SKIP 2453.8 -1305.0 22.5 0.0
						SET_UP_SKIP_FOR_VEHICLE_FINISHED_BY_SCRIPT 2453.8 -1305.0 22.5 0.0 tw7_smokes_car
						IF tw7_trip_skip_flag[1] = 0
							PRINT_HELP SKIP_1  
							tw7_trip_skip_flag[1] = 1
						ENDIF
						tw7_special_trip_skip = 1
					ENDIF
				
					ADD_BLIP_FOR_COORD 1532.8 -1672.9 12.4 tw7_control_blip

					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					MAKE_PLAYER_GANG_REAPPEAR
					SWITCH_WIDESCREEN OFF
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					SET_PLAYER_CONTROL player1 ON 	
				
					IF tw7_skip_cutscene_flag = 1
						DO_FADE 500 FADE_IN	
						WHILE GET_FADING_STATUS	
						   WAIT 0
						ENDWHILE 
						GOSUB tw7_death_checks
						IF tw7_deathcheck_flag = 1
							GOTO mission_twar7_failed
						ENDIF
					ENDIF 
					timerb = 0
					tw7_control_flag = 0
					tw7_car_check_flag = 1
					tw7_goals = 1
					
				ENDIF
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////going to pick up strap//////////////////////////////////////////////////////////////////////////
	//waiting for player to reach strap and getting him in the car
	IF tw7_goals = 1
		IF tw7_control_flag = 0 

			//initial bit of dialogue
			IF tw7_speech_goals = 0 
				IF tw7_speech_flag = 0
					IF timerb > 7000	
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
						tw7_speech_goals = 1
						tw7_speech_control_flag = 0
						GOSUB tw7_dialogue_setup 
						tw7_speech_flag = 1
					ENDIF
				ENDIF
			ENDIF
			IF tw7_speech_flag = 1 
				IF tw7_speech_goals = 0	 
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
					tw7_speech_flag = 2
				ENDIF
			ENDIF

			
			////////////////debug//////////////////////////////////////////////////
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
					SET_CHAR_COORDINATES scplayer 1531.4 -1693.7 12.4
				ENDIF
			ENDIF  
			////////////////debug//////////////////////////////////////////////////
		 
			IF IS_CHAR_IN_CAR scplayer tw7_smokes_car 
				IF IS_CHAR_IN_CAR big_smoke tw7_smokes_car 
					IF IS_CHAR_IN_CAR sweet tw7_smokes_car 
						IF LOCATE_CHAR_IN_CAR_3D scplayer 1532.8 -1672.9 12.4 4.0 4.0 4.0 TRUE 
							CLEAR_HELP
							SET_PLAYER_CONTROL player1 OFF
							tw7_control_flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF tw7_control_flag = 1 
			SET_PLAYER_CONTROL player1 OFF

			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			tw7_speech_goals = 0

			IF tw7_trip_skip_flag[0] = 0
				tw7_trip_skip_flag[0] = 1
			ENDIF	 	
			
			CLEAR_SKIP

			SET_FADING_COLOUR 0 0 0
			DO_FADE 2000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 

			MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
			SWITCH_STREAMING OFF							

			CLEAR_AREA 1532.8 -1672.9 12.4 300.0 TRUE
			SET_CAR_DENSITY_MULTIPLIER 0.0 
			SET_PED_DENSITY_MULTIPLIER 0.0 
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			SET_CHAR_COORDINATES scplayer 1496.5 -1672.6 13.2 
			
			DELETE_CHAR big_smoke
			DELETE_CHAR sweet
			DELETE_CAR tw7_smokes_car   
			UNLOAD_SPECIAL_CHARACTER 1 
			UNLOAD_SPECIAL_CHARACTER 2				
			MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
			
			LOAD_TEXTURE_DICTIONARY LD_bum
			LOAD_SPRITE 1 bum2
			LOAD_SPRITE 2 blkdot
			LOAD_ALL_MODELS_NOW
			
			USE_TEXT_COMMANDS TRUE
			
			LOAD_CUTSCENE SMOKE1B
			WHILE NOT HAS_CUTSCENE_LOADED
			    WAIT 0
			ENDWHILE
			START_CUTSCENE
			DO_FADE 1000 FADE_IN
			
			timera = 0 
			WHILE NOT HAS_CUTSCENE_FINISHED
			    WAIT 0
				
				/*
				IF timera > 28700
					IF timera < 29000
					    DRAW_SPRITE 2 320.0 225.0 800.0 600.0 0 0 0 255
						DRAW_SPRITE	1 322.0 228.0 453.0 317.0 160 160 160 255
					ENDIF
				ENDIF
				*/
			ENDWHILE
			CLEAR_CUTSCENE
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE

			USE_TEXT_COMMANDS FALSE
			REMOVE_TEXTURE_DICTIONARY  
			
			REQUEST_MODEL GLENDALE
			REQUEST_ANIMATION CAR_CHAT 

			LOAD_SPECIAL_CHARACTER 1 smoke
			LOAD_SPECIAL_CHARACTER 2 sweet
			LOAD_SPECIAL_CHARACTER 3 ogloc

			LOAD_ALL_MODELS_NOW 

			//smokes car
			CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
			CREATE_CAR GLENDALE 1532.8 -1672.9 12.4 tw7_smokes_car
			SET_CAR_HEADING tw7_smokes_car 184.0 
			CHANGE_CAR_COLOUR tw7_smokes_car 98 14
			SET_CAN_RESPRAY_CAR tw7_smokes_car FALSE  
			WARP_CHAR_INTO_CAR scplayer tw7_smokes_car 
			SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

			//smoke
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2072.3 -1697.2 12.5 big_smoke
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE
			SET_CHAR_HEADING big_smoke 255.7
			SET_CHAR_NEVER_TARGETTED big_smoke TRUE  
			SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
			SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
			TASK_ENTER_CAR_AS_PASSENGER big_smoke tw7_smokes_car -2 0  
			SET_CHAR_DECISION_MAKER big_smoke tw7_empty_decision_maker
			WARP_CHAR_INTO_CAR_AS_PASSENGER big_smoke tw7_smokes_car -1
			
			//ryder
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 2072.3 -1696.2 12.5 sweet	
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE
			SET_CHAR_NEVER_TARGETTED sweet TRUE  
			SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE 
			SET_ANIM_GROUP_FOR_CHAR sweet gang2
			TASK_ENTER_CAR_AS_PASSENGER sweet tw7_smokes_car -2 2  
			SET_CHAR_DECISION_MAKER sweet tw7_empty_decision_maker
			WARP_CHAR_INTO_CAR_AS_PASSENGER sweet tw7_smokes_car -1
			
			//mc_strap
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL03 1543.2 -1687.0 12.5 mc_strap
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR mc_strap FALSE 	
			SET_CHAR_HEADING mc_strap 97.2
			//TASK_PLAY_ANIM mc_strap SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_NEVER_TARGETTED mc_strap TRUE  
			SET_CHAR_CANT_BE_DRAGGED_OUT mc_strap TRUE 
			SET_CHAR_SUFFERS_CRITICAL_HITS mc_strap FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER mc_strap TRUE  
			WARP_CHAR_INTO_CAR_AS_PASSENGER mc_strap tw7_smokes_car -1   
			SET_SENSE_RANGE mc_strap 80.0 
			
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE tw7_char_decision_maker EVENT_VEHICLE_ON_FIRE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE tw7_char_decision_maker EVENT_ACQUAINTANCE_PED_HATE
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE tw7_char_decision_maker EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 1 1
			SET_CHAR_DECISION_MAKER mc_strap tw7_char_decision_maker

			REMOVE_BLIP tw7_control_blip							
			ADD_BLIP_FOR_COORD 2454.4 -1284.5 22.7 tw7_control_blip

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON
		
			SET_CAR_DENSITY_MULTIPLIER 1.0 
			SET_PED_DENSITY_MULTIPLIER 1.0 
		
			GOSUB tw7_death_checks
			IF tw7_deathcheck_flag = 1
				GOTO mission_twar7_failed
			ENDIF

			DO_FADE 1000 FADE_IN 

			PRINT_NOW ( SMK1_10 ) 7000 1 //Drive to the Ass bandits' house.
			timerb = 0
			tw7_speech_flag = 0
			tw7_control_flag = 2
		ENDIF
	
		//waiting for player to reach ass bandit house
		IF tw7_control_flag = 2 
			//initial bit of dialogue
			IF tw7_speech_goals = 0 
				IF tw7_speech_flag = 0
					IF timerb > 7000	
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH mc_strap TRUE
						tw7_speech_goals = 2
						tw7_speech_control_flag = 0
						GOSUB tw7_dialogue_setup 
						tw7_speech_flag = 1
					ENDIF
				ENDIF
			ENDIF
			IF tw7_speech_flag = 1 
				IF tw7_speech_goals = 0	 
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH mc_strap FALSE
					tw7_speech_flag = 2
				ENDIF
			ENDIF
		
			////////////////debug////////////////////////////////debug////////////////
			IF IS_CHAR_IN_ANY_CAR mc_strap 									
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF IS_CHAR_IN_ANY_CAR big_smoke
						IF IS_CHAR_IN_ANY_CAR sweet
							IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
								SET_CHAR_COORDINATES scplayer 2454.7 -1294.5 22.7
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			////////////////debug////////////////////////////////debug////////////////
		
			IF IS_CHAR_IN_CAR scplayer tw7_smokes_car 
				IF IS_CHAR_IN_CAR big_smoke tw7_smokes_car 
			 		IF IS_CHAR_IN_CAR sweet tw7_smokes_car 
						IF LOCATE_CHAR_IN_CAR_3D scplayer 2454.4 -1284.5 22.7 4.0 4.0 4.0 TRUE	
							IF tw7_trip_skip_flag[0] = 1
								tw7_trip_skip_flag[0] = 2
							ENDIF	 	
						
							tw7_speech_flag = 0
							tw7_control_flag = 0
							tw7_goals = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
						
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// at Mexican's house ////////////////////////////////////////////////////////////////////////////
	IF tw7_goals = 2
		IF tw7_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			tw7_speech_goals = 0
			
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON 
			SHUT_ALL_CHARS_UP TRUE 
			MAKE_PLAYER_GANG_DISAPPEAR 
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_FIXED_CAMERA_POSITION 2451.2 -1295.1 24.8 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2459.7 -1284.6 26.5 JUMP_CUT
		
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH mc_strap FALSE
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			tw7_speech_goals = 0
		
			tw7_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			
			TASK_PLAY_ANIM mc_strap CAR_Sc1_BR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
		
			tw7_speech_goals = 3
			tw7_speech_control_flag = 0
			GOSUB tw7_dialogue_setup 
				
			timera = 0 
			tw7_control_flag = 1

		ENDIF

		IF tw7_control_flag = 1
			IF tw7_speech_control_flag = 1
				TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 6.0 FALSE FALSE FALSE TRUE -1
				TASK_PLAY_ANIM sweet CAR_Sc1_BL CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
				tw7_control_flag = 2
			ENDIF
		ENDIF
			
		IF tw7_control_flag = 2
			IF tw7_speech_control_flag = 4
				TASK_PLAY_ANIM big_smoke CAR_Sc1_FR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
				tw7_control_flag = 3
			ENDIF
		ENDIF

		IF tw7_control_flag = 3 
			DRAW_SPHERE 2468.8 -1278.2 29.1 1.2

			IF tw7_speech_goals = 0 
				IF IS_CAR_STOPPED tw7_smokes_car 
					OPEN_SEQUENCE_TASK tw7_seq
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 2457.4 -1286.1 23.0 PEDMOVE_RUN 60000
					CLOSE_SEQUENCE_TASK tw7_seq
					PERFORM_SEQUENCE_TASK scplayer tw7_seq
					CLEAR_SEQUENCE_TASK tw7_seq
				
					OPEN_SEQUENCE_TASK tw7_seq
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 2458.4 -1287.8 23.0 PEDMOVE_RUN 60000
					CLOSE_SEQUENCE_TASK tw7_seq
					PERFORM_SEQUENCE_TASK mc_strap tw7_seq
					CLEAR_SEQUENCE_TASK tw7_seq
				
					tw7_control_flag = 4
				ENDIF
			ENDIF
		ENDIF
		IF tw7_control_flag = 4 
			DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status	
			IF task_status = FINISHED_TASK	
				
				IF IS_CHAR_IN_ANY_CAR big_smoke 
					WARP_CHAR_FROM_CAR_TO_COORD big_smoke 2472.8 -1277.9 28.9
				ENDIF
				WARP_CHAR_INTO_CAR big_smoke tw7_smokes_car
				LOCK_CAR_DOORS tw7_smokes_car CARLOCK_LOCKED
				TASK_CAR_DRIVE_WANDER big_smoke tw7_smokes_car 15.0 DRIVINGMODE_AVOIDCARS
				
				timera = 0
				tw7_control_flag = 5
			ENDIF
		ENDIF
		IF tw7_control_flag = 5
			DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
			IF timera > 0   //////DEBUG - THIS CAN BE CHANGED BACK TO 3000 IF YOU WANT TO SEE SMOKE PULLING AWAY
										   
				tw7_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB tw7_death_checks
				IF tw7_deathcheck_flag = 1
					GOTO mission_twar7_failed
				ENDIF

				//if cutscene has been skipped 
				IF tw7_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					tw7_speech_goals = 0
					
					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS	
						DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
					   	WAIT 0
						DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
					ENDWHILE 
					GOSUB tw7_death_checks
					IF tw7_deathcheck_flag = 1
						GOTO mission_twar7_failed
					ENDIF
				
					CLEAR_AREA 2457.4 -1286.1 23.0 1.0 TRUE
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				   	SET_CHAR_COORDINATES scplayer 2457.4 -1286.1 23.0 
				   	SET_CHAR_HEADING scplayer 230.2 

					CLEAR_AREA 2458.4 -1287.8 23.0 1.0 TRUE
					CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap
					SET_CHAR_COORDINATES mc_strap 2458.4 -1287.8 23.0 
					SET_CHAR_HEADING mc_strap 230.2 

					CLEAR_AREA 2452.4 -1267.6 23.8 5.0 TRUE
					SET_CAR_COORDINATES tw7_smokes_car 2452.4 -1267.6 23.8
					SET_CAR_HEADING tw7_smokes_car 0.0
					IF IS_CHAR_IN_ANY_CAR big_smoke 
						WARP_CHAR_FROM_CAR_TO_COORD big_smoke 2472.8 -1277.9 28.9
					ENDIF
					WARP_CHAR_INTO_CAR big_smoke tw7_smokes_car
					LOCK_CAR_DOORS tw7_smokes_car CARLOCK_LOCKED
					TASK_CAR_DRIVE_WANDER big_smoke tw7_smokes_car 15.0 DRIVINGMODE_AVOIDCARS
				ENDIF
			 
				OPEN_SEQUENCE_TASK tw7_seq
					TASK_GO_STRAIGHT_TO_COORD -1 2463.5 -1286.9 25.2 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 2463.9 -1278.4 29.0 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 2467.8 -1277.1 28.9 PEDMOVE_RUN -2
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK mc_strap tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq
			 
			 	REMOVE_ANIMATION CAR_CHAT
			 	REMOVE_BLIP tw7_control_blip
				ADD_BLIP_FOR_COORD 2468.8 -1278.4 28.9 tw7_control_blip
				SET_BLIP_AS_FRIENDLY tw7_control_blip FALSE 
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE	
				MAKE_PLAYER_GANG_REAPPEAR
				SWITCH_WIDESCREEN OFF
				SHUT_ALL_CHARS_UP FALSE	
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON

				CLEAR_PRINTS 
				PRINT_NOW ( SMK1_03 ) 7000 1 //Go and ring the doorbell.
				IF tw7_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS	
						DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
					   	WAIT 0
						DRAW_SPHERE 2468.8 -1278.2 29.1 1.2
					ENDWHILE 
					GOSUB tw7_death_checks
					IF tw7_deathcheck_flag = 1
						GOTO mission_twar7_failed
					ENDIF
				ENDIF 
				tw7_control_flag = 0
				tw7_goals = 3
			ENDIF  
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// At the Mexican's house ////////////////////////////////////////////////////////////////////////
	
	IF tw7_goals = 3
		IF tw7_control_flag = 0
			IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 2468.8 -1278.2 29.1 1.2 1.2 3.0 TRUE
				tw7_control_flag = 1
			ENDIF
		ENDIF
	
		IF tw7_control_flag = 1 	
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_MISSION_AUDIO 3
			tw7_speech_goals = 0

			DO_FADE 500 FADE_OUT	
			WHILE GET_FADING_STATUS	
			   WAIT 0
			ENDWHILE 
			GOSUB tw7_death_checks
			IF tw7_deathcheck_flag = 1
				GOTO mission_twar7_failed
			ENDIF

			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON  
			SHUT_ALL_CHARS_UP TRUE
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			REQUEST_MODEL PCJ600
			REQUEST_MODEL TEC9
			REQUEST_MODEL LSV2
			REQUEST_MODEL MICRO_UZI
			
			REQUEST_ANIMATION CRIB
			REQUEST_ANIMATION MISC
			REQUEST_ANIMATION GANGS
			REQUEST_ANIMATION RAPPING

			LOAD_TEXTURE_DICTIONARY LD_bum		
			LOAD_SPRITE 1 bum1
			LOAD_SPRITE 2 blkdot
			LOAD_ALL_MODELS_NOW
			
			USE_TEXT_COMMANDS TRUE

			LOAD_MISSION_AUDIO 3 SOUND_BANK_OGLOC
			WHILE NOT HAS_MISSION_AUDIO_LOADED 3	
			   WAIT 0
			ENDWHILE 
			GOSUB tw7_death_checks
			IF tw7_deathcheck_flag = 1
				GOTO mission_twar7_failed
			ENDIF

			LOAD_ALL_MODELS_NOW
		
			//creating fake car for player
			CLEAR_AREA 2471.0 -1285.1 28.3 30.0 TRUE
			CREATE_CAR PCJ600 2471.0 -1285.1 28.3 tw7_players_bike
			SET_CAR_HEADING tw7_players_bike 272.5
			SET_CAN_BURST_CAR_TYRES tw7_players_bike FALSE 

			CLEAR_AREA 2488.2 -1279.9 30.0 30.0 TRUE
			CREATE_CAR PCJ600 2488.2 -1279.9 30.0 tw7_ass_bandit_bike
			SET_CAR_ONLY_DAMAGED_BY_PLAYER tw7_ass_bandit_bike TRUE 
			SET_CAR_HEADING tw7_ass_bandit_bike 286.4
			SET_CAR_ALWAYS_CREATE_SKIDS tw7_ass_bandit_bike TRUE 
			SET_CAN_BURST_CAR_TYRES tw7_ass_bandit_bike FALSE 
			SET_CAR_PROOFS tw7_ass_bandit_bike TRUE TRUE TRUE TRUE TRUE 
			   
		
			CLEAR_AREA 2483.9 -1280.2 29.4 30.0 TRUE
			CREATE_CHAR PEDTYPE_MISSION1 LSV2 2483.9 -1280.2 29.4 tw7_ass_bandit
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH tw7_ass_bandit TRUE
			SET_CHAR_MONEY tw7_ass_bandit 500 
			SET_CHAR_HEADING tw7_ass_bandit 261.6
			GIVE_WEAPON_TO_CHAR tw7_ass_bandit WEAPONTYPE_TEC9 30000
			SET_CHAR_SUFFERS_CRITICAL_HITS tw7_ass_bandit FALSE  
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER tw7_ass_bandit TRUE
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE tw7_ass_bandit KNOCKOFFBIKE_NEVER
			SET_CHAR_STAY_IN_SAME_PLACE tw7_ass_bandit TRUE 
			SET_CHAR_PROOFS tw7_ass_bandit TRUE TRUE FALSE TRUE TRUE   
			SET_CHAR_SHOOT_RATE tw7_ass_bandit 20
			SET_CHAR_DECISION_MAKER tw7_ass_bandit tw7_empty_decision_maker

			OPEN_SEQUENCE_TASK tw7_group_seq	  
				TASK_DRIVE_BY -1 tw7_ass_bandit -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN FALSE 100   
			CLOSE_SEQUENCE_TASK tw7_group_seq
			SET_GROUP_SEQUENCE Players_Group tw7_group_seq

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap
		
			CLEAR_AREA 2468.0 -1278.5 29.1 1.0 TRUE
			SET_CHAR_COORDINATES scplayer 2468.0 -1278.5 29.1
			SET_CHAR_HEADING scplayer 269.4   
		
			CLEAR_AREA 2467.4 -1275.8 28.8 1.0 TRUE
			SET_CHAR_COORDINATES mc_strap 2467.4 -1275.8 28.8
			SET_CHAR_HEADING mc_strap 282.8   
		
			OPEN_SEQUENCE_TASK tw7_seq
				TASK_GO_STRAIGHT_TO_COORD -1 2469.2 -1278.7 29.3 PEDMOVE_WALK 60000
				TASK_ACHIEVE_HEADING -1 262.0
				TASK_PLAY_ANIM -1 CRIB_Use_Switch CRIB 4.0 FALSE FALSE FALSE FALSE 800
				TASK_LOOK_AT_CHAR -1 mc_strap 7000
			CLOSE_SEQUENCE_TASK tw7_seq
			PERFORM_SEQUENCE_TASK scplayer tw7_seq
			CLEAR_SEQUENCE_TASK tw7_seq

			tw7_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
			
			CLEAR_PRINTS

			SET_FIXED_CAMERA_POSITION 2461.7 -1273.7 30.0 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2470.1 -1277.1 30.7 JUMP_CUT

			DO_FADE 500 FADE_IN	
			WHILE GET_FADING_STATUS	
			   WAIT 0
			ENDWHILE 
			GOSUB tw7_death_checks
			IF tw7_deathcheck_flag = 1
				GOTO mission_twar7_failed
			ENDIF

			tw7_control_flag = 2
		ENDIF

		IF tw7_control_flag = 2
			IF IS_CHAR_PLAYING_ANIM scplayer CRIB_Use_Switch 
				GET_CHAR_ANIM_CURRENT_TIME scplayer CRIB_Use_Switch tw7_anim_time
				IF tw7_anim_time > 0.65 
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2469.2 -1278.7 29.3 SOUND_OGLOC_DOORBELL	
					tw7_control_flag = 3
				ENDIF
			ENDIF
		ENDIF	 


		IF tw7_control_flag = 3 
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status
			IF task_status = FINISHED_TASK
				OPEN_SEQUENCE_TASK tw7_seq
					TASK_PLAY_ANIM -1 prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_GO_STRAIGHT_TO_COORD -1 2467.8 -1274.4 28.8 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 2468.6 -1273.3 28.8 PEDMOVE_WALK -2
					TASK_ACHIEVE_HEADING -1 278.7
					//TASK_PLAY_ANIM -1 bng_wndw_02 MISC 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK mc_strap tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq	

				// Freddy! I've come for you, motherfucker!
				tw7_speech_goals = 4
				tw7_speech_control_flag = 0
				tw7_random_last_label = 5 
				GOSUB tw7_dialogue_setup 
				tw7_control_flag = 4
			ENDIF
		ENDIF

		IF tw7_control_flag = 4
			IF tw7_speech_control_flag > 2
				OPEN_SEQUENCE_TASK tw7_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 mc_strap
					TASK_PLAY_ANIM -1 plyr_shkhead MISC 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK scplayer tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq	
				tw7_control_flag = 5
			ENDIF
		ENDIF

		IF tw7_control_flag = 5
			//loading up the window banging sound
			IF tw7_speech_goals = 0
				OPEN_SEQUENCE_TASK tw7_seq
					TASK_PLAY_ANIM -1 bng_wndw MISC 4.0 FALSE FALSE FALSE FALSE -1
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
					//TASK_PLAY_ANIM -1 prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE FALSE -1
					//TASK_ACHIEVE_HEADING -1 278.7
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK mc_strap tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq

				OPEN_SEQUENCE_TASK tw7_seq
					TASK_PLAY_ANIM -1 Laugh_01 RAPPING 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK scplayer tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq	
				
				// GIMME BACK MY RHYMES!
				tw7_speech_goals = 4
				tw7_speech_control_flag = 5
				tw7_random_last_label = 9 
				GOSUB tw7_dialogue_setup 

				timera = 0 
				tw7_control_flag = 6
			ENDIF
		ENDIF

		IF tw7_control_flag = 6 
			IF IS_CHAR_PLAYING_ANIM mc_strap bng_wndw 
				GET_CHAR_ANIM_CURRENT_TIME mc_strap bng_wndw tw7_anim_time
				IF tw7_anim_time > 0.4 
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2468.6 -1273.3 28.8 SOUND_OGLOC_WINDOW_RATTLE_BANG	
					tw7_control_flag = 7
				ENDIF
			ENDIF
		ENDIF	 

		IF NOT IS_CHAR_DEAD tw7_ass_bandit 
			IF NOT IS_CAR_DEAD tw7_ass_bandit_bike 
				IF tw7_control_flag = 7 
					
					/*
					IF timera > 8700
						IF timera < 9000
						    DRAW_SPRITE 2 320.0 225.0 800.0 600.0 0 0 0 255
							DRAW_SPRITE	1 322.0 228.0 453.0 317.0 160 160 160 255
						ENDIF
					ENDIF
					*/

					IF tw7_speech_goals = 0 
						
						OPEN_SEQUENCE_TASK tw7_seq
							TASK_ENTER_CAR_AS_DRIVER -1 tw7_ass_bandit_bike -1 
						CLOSE_SEQUENCE_TASK tw7_seq
						PERFORM_SEQUENCE_TASK tw7_ass_bandit tw7_seq
						CLEAR_SEQUENCE_TASK tw7_seq
					
						// Motherfucker's making a break for it!
						tw7_speech_goals = 4
						tw7_speech_control_flag = 9
						tw7_random_last_label = 10 
						GOSUB tw7_dialogue_setup 
					
						SET_FIXED_CAMERA_POSITION 2495.4 -1282.3 32.6 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2479.2 -1272.5 30.5 JUMP_CUT
						tw7_control_flag = 8
					ENDIF
				ENDIF

				IF tw7_control_flag = 8
					IF IS_CHAR_IN_CAR tw7_ass_bandit tw7_ass_bandit_bike	   
						START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 30 //leaving the house and stopping just before the alleyways
						timera = 0
						tw7_control_flag = 9
					ENDIF
				ENDIF
				
				IF tw7_control_flag = 9
					IF timera > 2000
						IF NOT IS_CAR_DEAD tw7_players_bike 
							CLEAR_AREA 2468.4 -1282.4 28.8 1.0 TRUE
							SET_CHAR_COORDINATES mc_strap 2468.4 -1282.4 28.8  
							SET_CHAR_HEADING mc_strap 171.7 
							SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN 
							
							OPEN_SEQUENCE_TASK tw7_seq
								TASK_ENTER_CAR_AS_PASSENGER -1 tw7_players_bike -2 -1 
							CLOSE_SEQUENCE_TASK tw7_seq
							PERFORM_SEQUENCE_TASK mc_strap tw7_seq
							CLEAR_SEQUENCE_TASK tw7_seq						  

							CLEAR_AREA 2467.4 -1281.2 28.8 1.0 TRUE
							SET_CHAR_COORDINATES scplayer 2467.4 -1281.2 28.8 
							SET_CHAR_HEADING scplayer 171.7 
							SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN 
							OPEN_SEQUENCE_TASK tw7_seq
								TASK_ENTER_CAR_AS_DRIVER -1 tw7_players_bike -2 
							CLOSE_SEQUENCE_TASK tw7_seq
							PERFORM_SEQUENCE_TASK scplayer tw7_seq
							CLEAR_SEQUENCE_TASK tw7_seq

							SET_FIXED_CAMERA_POSITION 2460.9 -1283.7 30.6 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2472.6 -1284.4 31.1 JUMP_CUT

							tw7_speech_goals = 5 
							tw7_speech_control_flag = 0
							GOSUB tw7_dialogue_setup
						
							tw7_control_flag = 10

						ENDIF	
					ENDIF
				ENDIF

				IF tw7_control_flag = 10
					IF tw7_speech_goals = 0 
						IF NOT IS_CHAR_DEAD mc_strap
							IF NOT IS_CAR_DEAD tw7_players_bike  
								IF IS_CHAR_IN_CAR scplayer tw7_players_bike
								AND IS_CHAR_IN_CAR mc_strap tw7_players_bike 
									tw7_skip_cutscene_flag = 0
									SKIP_CUTSCENE_END
									GOSUB tw7_death_checks
									IF tw7_deathcheck_flag = 1
										GOTO mission_twar7_failed
									ENDIF
									
									IF tw7_skip_cutscene_flag = 1
										CLEAR_PRINTS
										CLEAR_MISSION_AUDIO 1
										CLEAR_MISSION_AUDIO 2
										CLEAR_MISSION_AUDIO 3
										tw7_speech_goals = 0
									
										DO_FADE 500 FADE_OUT	
										WHILE GET_FADING_STATUS	
										   WAIT 0
										ENDWHILE 
										GOSUB tw7_death_checks
										IF tw7_deathcheck_flag = 1
											GOTO mission_twar7_failed
										ENDIF

										IF NOT IS_CHAR_DEAD tw7_ass_bandit
											IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
												CLEAR_CHAR_TASKS tw7_ass_bandit
												IF NOT IS_CHAR_IN_CAR tw7_ass_bandit tw7_ass_bandit_bike
													WARP_CHAR_INTO_CAR tw7_ass_bandit tw7_ass_bandit_bike
												ENDIF
											ENDIF
										ENDIF

										IF NOT IS_CAR_DEAD tw7_players_bike
											CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
											CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap
											IF NOT IS_CHAR_IN_CAR scplayer tw7_players_bike 
												WARP_CHAR_INTO_CAR scplayer tw7_players_bike
											ENDIF
											IF NOT IS_CHAR_IN_CAR mc_strap tw7_players_bike 
												TASK_WARP_CHAR_INTO_CAR_AS_PASSENGER mc_strap tw7_players_bike -1
											ENDIF
										ENDIF
									ENDIF
							
									ENSURE_PLAYER_HAS_DRIVE_BY_WEAPON player1 78
									GIVE_WEAPON_TO_CHAR mc_strap WEAPONTYPE_TEC9 30000
									SET_CURRENT_CHAR_WEAPON mc_strap WEAPONTYPE_TEC9 
									
									MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
									SET_GROUP_MEMBER Players_Group mc_strap
									tw7_flag_mc_strap_in_group = 1
									tw7_car_check_flag = 1
								
									SET_CHAR_RELATIONSHIP mc_strap ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1  

									IF NOT IS_CHAR_DEAD tw7_ass_bandit
										IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
											IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
												STOP_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike
											ENDIF  
											SET_CAR_COORDINATES tw7_ass_bandit_bike 2521.2 -1359.0 27.3  
											SET_CAR_HEADING tw7_ass_bandit_bike 295.2
										ENDIF
									ENDIF
								
									SET_CAMERA_BEHIND_PLAYER
									RESTORE_CAMERA_JUMPCUT

									REMOVE_ANIMATION CRIB
									REMOVE_ANIMATION MISC
									REMOVE_ANIMATION GANGS
									REMOVE_ANIMATION RAPPING 
									CLEAR_HELP

									PRINT_NOW ( SMK1_04 ) 7000 1 //Catch and kill the Ass Bandit!
									
									REMOVE_BLIP tw7_control_blip
									IF NOT IS_CHAR_DEAD tw7_ass_bandit 
										ADD_BLIP_FOR_CHAR tw7_ass_bandit tw7_control_blip
										SET_BLIP_AS_FRIENDLY tw7_control_blip FALSE
									ENDIF
									HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE	
									SHUT_ALL_CHARS_UP FALSE
									SWITCH_WIDESCREEN OFF	
									MAKE_PLAYER_GANG_REAPPEAR
									RESTORE_CAMERA_JUMPCUT
									SET_PLAYER_CONTROL player1 ON

									IF tw7_skip_cutscene_flag = 1
										DO_FADE 500 FADE_IN	
										WHILE GET_FADING_STATUS	
										   WAIT 0
										ENDWHILE 
										GOSUB tw7_death_checks
										IF tw7_deathcheck_flag = 1
											GOTO mission_twar7_failed
										ENDIF
									ENDIF 
									
									USE_TEXT_COMMANDS FALSE
									REMOVE_TEXTURE_DICTIONARY
									DELETE_CHAR big_smoke
									DELETE_CHAR sweet
									DELETE_CAR tw7_smokes_car   
									UNLOAD_SPECIAL_CHARACTER 1 
									UNLOAD_SPECIAL_CHARACTER 2					  
									MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
									CLEAR_MISSION_AUDIO 3
								
									IF IS_CAR_DEAD tw7_ass_bandit_bike
									OR IS_CAR_DEAD tw7_players_bike 
										GOTO mission_twar7_failed
									ENDIF
									tw7_speech_flag = 0
									tw7_control_flag = 0
									tw7_goals = 4
								ENDIF
							ENDIF									 
						ENDIF 
					ENDIF
				ENDIF
			ENDIF	
		ENDIF
	ENDIF										 


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Triggering the Mexican car recordings /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// 31 - through the alleyways ////////////////////////////////////////////////////////////////////
	IF tw7_goals > 3
		IF tw7_goals < 15 
			IF NOT IS_CHAR_DEAD tw7_ass_bandit
				IF NOT IS_CAR_DEAD tw7_ass_bandit_bike  
			
					IF tw7_goals = 4
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF tw7_speech_goals = 0 
								IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
								OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
								//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
								//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
									IF NOT IS_CHAR_DEAD tw7_ass_bandit  
										tw7_speech_goals = 6 
										tw7_speech_control_flag = 0
										tw7_random_last_label = 1 
										GOSUB tw7_dialogue_setup
									ENDIF	
									tw7_speech_flag = 1
								ENDIF
							ENDIF
						ENDIF

					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//road at end of first alleyway 
							SWITCH_ROADS_OFF 2563.7 -1435.0 10.0 2580.3 -1266.4 100.6

							//road at end of second alleyway
							SWITCH_ROADS_OFF 2635.0 -1434.4 10.0 2652.0 -1263.6 100.0
							
							//road at end of third alleyway
							SWITCH_ROADS_OFF 2714.9 -1501.6 10.0 2746.1 -1266.4 100.0

							//create_random_ped to look dead
							CREATE_RANDOM_CHAR 2579.8 -1348.3 35.5 tw7_lying_down_ped
							TASK_DIE tw7_lying_down_ped
							
							CREATE_RANDOM_CHAR 2578.8 -1348.3 35.5 tw7_friend_of_lying_down_ped
							SET_CHAR_HEADING tw7_friend_of_lying_down_ped 260.0  
							TASK_PLAY_ANIM tw7_friend_of_lying_down_ped weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1	 
							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road at end of first alleyway 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2582.6 -1351.8 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2563.7 -1435.0 10.0 2580.3 -1266.4 100.6
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on road at end of second alleyway 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2653.0 -1329.8 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2635.0 -1434.4 10.0 2652.0 -1263.6 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF

						IF tw7_car_nodes = 3
							//switching on road at end of second alleyway 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2748.3 -1320.3 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2714.9 -1501.6 10.0 2746.1 -1266.4 100.0
								tw7_car_nodes = 4	
							ENDIF
						ENDIF

						///// main bit ////////
						IF tw7_control_flag = 0									 
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer  
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 31 
								REMOVE_CAR_RECORDING 30
								tw7_control_flag = 1
							ENDIF
						ENDIF
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2563.7 -1435.0 10.0 2580.3 -1266.4 100.6
								SWITCH_ROADS_ON 2635.0 -1434.4 10.0 2652.0 -1263.6 100.0
								SWITCH_ROADS_ON 2714.9 -1501.6 10.0 2746.1 -1266.4 100.0
								timera = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_car_nodes = 0
								tw7_goals = 5
							ENDIF
						ENDIF
						
						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
						
					ENDIF



				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 32 - up the steep alleyway ////////////////////////////////////////////////////////////////////

					IF tw7_goals = 5
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 1
									tw7_random_last_label = 2 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF


						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//first left road 
							SWITCH_ROADS_OFF 2713.9 -1499.3 10.0 2746.6 -1266.3 100.0

							//first right road
							SWITCH_ROADS_OFF 2714.6 -1249.9 10.0 2744.7 -1332.9 100.0
							
							//second left road 
							SWITCH_ROADS_OFF 2634.6 -1396.6 10.0 2651.0 -1264.2 100.0

							//second right road
							SWITCH_ROADS_OFF 2635.1 -1248.0 10.0 2651.0 -1055.3 100.0

							//second straight on road
							SWITCH_ROADS_OFF 2651.5 -1263.8 10.0 2579.5 -1248.7 100.0
							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road at end of first road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2712.7 -1265.7 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2713.9 -1499.3 10.0 2746.6 -1266.3 100.0
								SWITCH_ROADS_ON 2714.6 -1249.9 10.0 2744.7 -1332.9 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on road at end of second road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2630.5 -1248.3 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2634.6 -1396.6 10.0 2651.0 -1264.2 100.0
								SWITCH_ROADS_ON 2635.1 -1248.0 10.0 2651.0 -1055.3 100.0
								SWITCH_ROADS_ON 2651.5 -1263.8 10.0 2579.5 -1248.7 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								IF NOT IS_CHAR_DEAD tw7_ass_bandit 
									SET_CHAR_PROOFS tw7_ass_bandit FALSE FALSE FALSE FALSE FALSE
								ENDIF   
								IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
									SET_CAR_PROOFS tw7_ass_bandit_bike FALSE FALSE FALSE FALSE FALSE   
								ENDIF									 
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 32 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_friend_of_lying_down_ped
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_lying_down_ped	
								SWITCH_ROADS_ON 2713.9 -1499.3 10.0 2746.6 -1266.3 100.0
								SWITCH_ROADS_ON 2714.6 -1249.9 10.0 2744.7 -1332.9 100.0
								SWITCH_ROADS_ON 2634.6 -1396.6 10.0 2651.0 -1264.2 100.0
								SWITCH_ROADS_ON 2635.1 -1248.0 10.0 2651.0 -1055.3 100.0
								SWITCH_ROADS_ON 2651.5 -1263.8 10.0 2579.5 -1248.7 100.0
								timera = 0
								tw7_speech_flag = 0
								tw7_car_nodes = 0
								tw7_control_flag = 0
								tw7_goals = 6
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					ENDIF

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 33 - fast road section and stops just before the wee hill                                  ///

					IF tw7_goals = 6
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							OR LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 35.0 35.0 FALSE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 2
									tw7_random_last_label = 3 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
						
						
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//first left road 
							SWITCH_ROADS_OFF 2563.7 -1248.6 10.0 2578.9 -1192.2 100.0

							//second left road
							SWITCH_ROADS_OFF 2443.7 -1249.2 10.0 2459.1 -1191.9 100.0

							//third left road
							SWITCH_ROADS_OFF 2363.5 -1253.4 10.0 2378.7 -1145.5 100.0

							//long road
							SWITCH_ROADS_OFF 2311.2 -1144.7 10.0 2379.1 -1162.1 100.0

							///////nodes for NEXT playback section//////////
							SWITCH_ROADS_OFF 2239.9 -1130.0 10.0 2017.6 -928.3 100.0


							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on first left road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2563.2 -1191.8 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2563.7 -1248.6 10.0 2578.9 -1192.2 100.6
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on second left road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2442.4 -1191.4 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2443.7 -1249.2 10.0 2459.1 -1191.9 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF

						IF tw7_car_nodes = 3
							//switching on third left road
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2360.1 -1164.8 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2363.5 -1253.4 10.0 2378.7 -1145.5 100.0
								tw7_car_nodes = 4	
							ENDIF
						ENDIF

						IF tw7_car_nodes = 4
							//switching on road next to railway 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2315.0 -1145.9 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2311.2 -1144.7 10.0 2379.1 -1162.1 100.0
								tw7_car_nodes = 5	
							ENDIF
						ENDIF

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 33 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2563.7 -1248.6 10.0 2578.9 -1192.2 100.6
								SWITCH_ROADS_ON 2443.7 -1249.2 10.0 2459.1 -1191.9 100.0
								SWITCH_ROADS_ON 2363.5 -1253.4 10.0 2378.7 -1145.5 100.0
								SWITCH_ROADS_ON 2311.2 -1144.7 10.0 2379.1 -1162.1 100.0
								timera = 0
								tw7_speech_flag = 0
								tw7_car_nodes = 0
								tw7_control_flag = 0
								tw7_goals = 7
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					ENDIF

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 34 - the wee hill just before the BIG road bit (bike start @ 2224.0 -1125.8 24.2, 347.0) /////

					IF tw7_goals = 7
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 3
									tw7_random_last_label = 4 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							///////nodes for NEXT playback section//////////
							//road straight ahead 
							SWITCH_ROADS_OFF 1930.6 -1031.9 10.0 1574.4 -879.9 100.0
							tw7_car_nodes = 1							 
						ENDIF
						
						IF tw7_car_nodes = 1
							//switching on first left road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2149.8 -996.1 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2239.9 -1130.0 10.0 2017.6 -928.3 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
					

						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 34 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2239.9 -1130.0 10.0 2017.6 -928.3 100.0
								timera = 0
								tw7_speech_flag = 0
								tw7_car_nodes = 0
								tw7_control_flag = 0
								tw7_goals = 8
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					ENDIF


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 35 - THE big road bit (bike start @ 1942.5 -1016.2 31.8, 87.4) ///////////////////////////

					IF tw7_goals = 8
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 4
									tw7_random_last_label = 5 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//whole of freeway
							SWITCH_ROADS_OFF 1879.6 -1024.3 10.0 1600.0 -1521.7 100.0

							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road straight ahead 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 1819.4 -1034.0 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 1930.6 -1031.9 10.0 1574.4 -879.9 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on whole of freeway 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 1755.3 -1489.1 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 1879.6 -1024.3 10.0 1552.0 -1521.7 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF


						///// main bit ////////
						IF tw7_control_flag = 0
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								tw7_no_plates = MANANA
								GOSUB tw7_my_number_plates
								CREATE_CAR MANANA 1688.5 -1111.7 58.5 tw7_car[0]	   //front car 	- car rec 50
								CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[0] tw7_char[0]
								SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[0] TRUE
								SET_CAR_HEADING tw7_car[0] 322.3 
								START_PLAYBACK_RECORDED_CAR tw7_car[0] 50								   

								tw7_no_plates = MANANA
								GOSUB tw7_my_number_plates
								CREATE_CAR MANANA 1679.7 -1131.6 59.7 tw7_car[1]	   //second car - car rec 51 
								CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[1] tw7_char[1] 
								SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[1] TRUE
								SET_CAR_HEADING tw7_car[1] 321.2 
								START_PLAYBACK_RECORDED_CAR tw7_car[1] 51

								CREATE_CAR PCJ600 1644.8 -1177.5 54.3 tw7_car[2]	   //third car - first bike - car rec 52 
								CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[2] tw7_char[2] 
								SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[2] TRUE
								SET_CAR_HEADING tw7_car[2] 320.8 								   
								START_PLAYBACK_RECORDED_CAR tw7_car[2] 52

								tw7_no_plates = MANANA
								GOSUB tw7_my_number_plates
								CREATE_CAR MANANA 1637.0 -1203.8 51.5 tw7_car[3]	   //fourth car - car rec 53 
								CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[3] tw7_char[3] 
								SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[3] TRUE
								SET_CAR_HEADING tw7_car[3] 350.3 								   
								START_PLAYBACK_RECORDED_CAR tw7_car[3] 53

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1618.2 -1306.4 35.6 tw7_car[4]	   //first car left on dual carriageway - car rec 54 
									SET_CAR_HEADING tw7_car[4] 343.7 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[4] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[4] tw7_char[4] 
									START_PLAYBACK_RECORDED_CAR tw7_car[4] 54
								ENDIF

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1621.8 -1317.9 33.0 tw7_car[5]	   //second car left on dual carriageway - car rec 55 
									SET_CAR_HEADING tw7_car[5] 348.7 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[5] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[5] tw7_char[5] 
									START_PLAYBACK_RECORDED_CAR tw7_car[5] 55
								ENDIF

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1615.6 -1327.7 31.5 tw7_car[6]	   //second car right on dual carriageway - car rec 56 
									SET_CAR_HEADING tw7_car[6] 352.1 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[6] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[6] tw7_char[6] 
									START_PLAYBACK_RECORDED_CAR tw7_car[6] 56
								ENDIF

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1612.3 -1394.0 27.6 tw7_car[7]	   //third car right on dual carriageway - car rec 57 
									SET_CAR_HEADING tw7_car[7] 353.6 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[7] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[7] tw7_char[7] 
									START_PLAYBACK_RECORDED_CAR tw7_car[7] 57
								ENDIF

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1612.3 -1462.2 27.1 tw7_car[8]	   //fourth car right on dual carriageway - car rec 58 
									SET_CAR_HEADING tw7_car[8] 357.2 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[8] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[8] tw7_char[8] 
									START_PLAYBACK_RECORDED_CAR tw7_car[8] 58
								ENDIF

								GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE tw7_model tw7_class
								IF tw7_model >= 0 
									tw7_no_plates = tw7_model
									GOSUB tw7_my_number_plates
									CREATE_CAR tw7_model 1663.4 -1457.0 24.3 tw7_car[9]	   //car that crashes - car rec 60 
									SET_CAR_HEADING tw7_car[9] 51.9 
									SET_LOAD_COLLISION_FOR_CAR_FLAG tw7_car[9] TRUE
									CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[9] tw7_char[9] 																  
									START_PLAYBACK_RECORDED_CAR tw7_car[9] 60
								ENDIF

								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 35
								tw7_control_flag = 1
							ENDIF
						ENDIF
									  
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								tw7_control_flag = 2
							ENDIF
							
							IF NOT IS_CAR_DEAD tw7_car[9] 
								IF LOCATE_CAR_2D tw7_car[9] 1601.4 -1389.5 5.0 5.0 FALSE  
									STOP_PLAYBACK_RECORDED_CAR tw7_car[9]
									//EXPLODE_CAR tw7_car[9]
									//ADD_EXPLOSION 1601.4 -1389.5 30.4 EXPLOSION_GRENADE	
									//ADD_EXPLOSION 1601.4 -1389.5 30.4 EXPLOSION_OBJECT
									//ADD_EXPLOSION 1601.4 -1389.5 30.4 EXPLOSION_MOLOTOV
									tw7_control_flag = 2
								ENDIF
							ENDIF
						ENDIF

						IF tw7_control_flag = 2
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 1930.6 -1031.9 10.0 1574.4 -879.9 100.0
								SWITCH_ROADS_ON 1879.6 -1024.3 10.0 1552.0 -1521.7 100.0
								timera = 0
								tw7_car_nodes = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_goals = 9
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					ENDIF

				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////// 36 - Through Little School bit with snogging peeps (bike start @ 1762.6 -1474.1 12.0, 0.0) ///

					IF tw7_goals = 9
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 5
									tw7_random_last_label = 6 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
						
						
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//only road to cross
							SWITCH_ROADS_OFF 1725.8 -1433.3 10.0 1838.0 -1459.4 100.0

							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road straight ahead 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 1781.3 -1437.9 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 1725.8 -1433.3 10.0 1838.0 -1459.4 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[0]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[1]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[2]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[3]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[4]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[5]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[6]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[7]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[8]
								MARK_CAR_AS_NO_LONGER_NEEDED tw7_car[9] 

								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[0]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[1]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[2]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[3]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[4]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[5]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[6]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[7]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[8]
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_char[9] 
												
								CREATE_RANDOM_CHAR 1778.4 -1343.1 14.7 tw7_bj_male
								SET_CHAR_HEADING tw7_bj_male 180.0 
								CREATE_RANDOM_CHAR 1778.4 -1344.1 14.7 tw7_bj_female
								SET_CHAR_HEADING tw7_bj_female 0.0 

								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 36 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 1725.8 -1433.3 10.0 1838.0 -1459.4 100.0
								timera = 0
								tw7_car_nodes = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_goals = 10
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					
					ENDIF


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 37 - Through Skate Park (bike start @ 1828.8 -1342.5 13.0, 276.3) ////////////////////////

					IF tw7_goals = 10
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 6
									tw7_random_last_label = 7 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//road straight ahead 
							SWITCH_ROADS_OFF 1838.4 -1453.6 10.0 1853.7 -1303.4 100.0

							//road that you jump over
							SWITCH_ROADS_OFF 1979.3 -1468.7 10.0 1853.5 -1452.5 100.0

							////// SWITCHING OFF ROAD FOR NEXT PLAYBACK /////////////
							SWITCH_ROADS_OFF 2334.0 -1556.1 10.0 2350.5 -1489.7 100.0


							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road straight ahead 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 1855.7 -1379.0 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 1838.4 -1453.6 10.0 1853.7 -1303.4 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on road that you jump over 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 1971.5 -1474.6 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 1979.3 -1468.7 10.0 1853.5 -1452.5 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer
							OR timera > 4000
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 37 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_bj_male
								MARK_CHAR_AS_NO_LONGER_NEEDED tw7_bj_female
								SWITCH_ROADS_ON 1838.4 -1453.6 10.0 1853.7 -1303.4 100.0
								SWITCH_ROADS_ON 1979.3 -1468.7 10.0 1853.5 -1452.5 100.0
								timera = 0
								tw7_car_nodes = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_goals = 11
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					
					ENDIF			  


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 38 - Through Taco Bell (bike start @ 2333.3 -1551.2 22.6, 344.6) /////////////////////////

					IF tw7_goals = 11
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 7
									tw7_random_last_label = 8 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
						
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//road next to taco bell 
							SWITCH_ROADS_OFF 2350.1 -1516.0 10.0 2423.9 -1532.5 100.0

							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road next to taco bell
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2394.8 -1508.7 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2350.1 -1516.0 10.0 2423.9 -1532.5 100.0
								SWITCH_ROADS_ON 2334.0 -1556.1 10.0 2350.5 -1489.7 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 38 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2350.1 -1516.0 10.0 2423.9 -1532.5 100.0
								SWITCH_ROADS_ON 2334.0 -1556.1 10.0 2350.5 -1489.7 100.0
								timera = 0
								tw7_car_nodes = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_goals = 12
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					
					ENDIF


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// 39 - Past Chris Police Bit (bike start @ 2424.5 -1483.3 22.6, 354.8) /////////////////////

					IF tw7_goals = 12
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 8
									tw7_random_last_label = 9 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//all roads  
							SWITCH_ROADS_OFF 2379.8 -1373.3 10.0 2423.7 -1452.6 100.0

							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on all roads
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2398.6 -1432.4 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2379.8 -1373.3 10.0 2423.7 -1452.6 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							

						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 39 
								tw7_control_flag = 1
							ENDIF
						ENDIF
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2379.8 -1373.3 10.0 2423.7 -1452.6 100.0
								timera = 0
								tw7_car_nodes = 0
								tw7_speech_flag = 0
								tw7_control_flag = 0
								tw7_goals = 13
							ENDIF
						ENDIF

						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
					
					ENDIF


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////// 40 - Last bit, stop at basketball courts (phew) (bike start @ 2408.7 -1260.7 22.5, 96.2) /////

					IF tw7_goals = 13
						GOSUB tw7_sorting_speed
						GOSUB tw7_mc_strap_group
					
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								timerb = 0
								IF NOT IS_CHAR_DEAD tw7_ass_bandit  
									tw7_speech_goals = 6 
									tw7_speech_control_flag = 9
									tw7_random_last_label = 10 
									GOSUB tw7_dialogue_setup
								ENDIF	
								tw7_speech_flag = 1
							ENDIF
							IF tw7_control_flag = 1
								tw7_speech_flag = 1
							ENDIF
						ENDIF
					
					
						////// car nodes jiggery pokery ///////
						IF tw7_car_nodes = 0
							//road just before the jump through the persons backyard 
							SWITCH_ROADS_OFF 2363.5 -1291.7 10.0 2379.5 -1181.2 100.0

							//road just after the jump through the persons backyard 
							SWITCH_ROADS_OFF 2295.9 -1291.5 10.0 2311.5 -1162.3 100.0

							//road just after the jump over the railway thing
							SWITCH_ROADS_OFF 2333.9 -1392.1 10.0 2220.2 -1377.1 100.0
							 
							//last road
							SWITCH_ROADS_OFF 2334.6 -1493.6 10.0 2219.8 -1475.2 100.0
							  

							tw7_car_nodes = 1							 
						ENDIF

						IF tw7_car_nodes = 1
							//switching on road just before the jump through the persons backyard 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2362.8 -1259.3 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2363.5 -1291.7 10.0 2379.5 -1181.2 100.0
								tw7_car_nodes = 2	
							ENDIF
						ENDIF
							
						IF tw7_car_nodes = 2
							//switching on road just after the jump through the persons backyard 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2294.5 -1263.5 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2295.9 -1291.5 10.0 2311.5 -1162.3 100.0
								tw7_car_nodes = 3	
							ENDIF
						ENDIF

						IF tw7_car_nodes = 3
							//switching on road just after the jump over the railway thing 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2284.2 -1393.8 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2333.9 -1392.1 10.0 2220.2 -1377.1 100.0
								tw7_car_nodes = 4	
							ENDIF
						ENDIF

						IF tw7_car_nodes = 4
							//switching on last road 
							IF LOCATE_CAR_2D tw7_ass_bandit_bike 2293.8 -1491.7 5.0 5.0 FALSE 
								SWITCH_ROADS_ON 2334.6 -1493.6 10.0 2219.8 -1475.2 100.0
								tw7_car_nodes = 5	
							ENDIF
						ENDIF
											   
						///// main bit ////////
						IF tw7_control_flag = 0	
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
							OR timera > 4000
								//creating guys at the basketball court for the ass bandit to run too
								IF HAS_MODEL_LOADED LSV3
									CREATE_CHAR PEDTYPE_MISSION2 LSV3 2301.9 -1504.1 24.3 tw7_goon[0]  //hoop shooter
									SET_CHAR_HEADING tw7_goon[0] 358.5  
									GIVE_WEAPON_TO_CHAR tw7_goon[0] WEAPONTYPE_TEC9 30000 
									SET_CHAR_KINDA_STAY_IN_SAME_PLACE tw7_goon[0] TRUE
									SET_CHAR_DECISION_MAKER tw7_goon[0] tw7_empty_decision_maker
								ENDIF
										 
								IF HAS_MODEL_LOADED LSV3
									CREATE_CHAR PEDTYPE_MISSION2 LSV3 2302.5 -1502.3 24.3 tw7_goon[1]  //left guy watching 
									SET_CHAR_HEADING tw7_goon[1] 18.5
									GIVE_WEAPON_TO_CHAR tw7_goon[1] WEAPONTYPE_TEC9 30000 
									SET_CHAR_KINDA_STAY_IN_SAME_PLACE tw7_goon[1] TRUE
									SET_CHAR_DECISION_MAKER tw7_goon[1] tw7_empty_decision_maker
								ENDIF
								
								IF HAS_MODEL_LOADED LSV3
									CREATE_CHAR PEDTYPE_MISSION2 LSV3 2300.6 -1503.9 24.3 tw7_goon[2]  //right guy watching
									SET_CHAR_HEADING tw7_goon[2] 5.9
									GIVE_WEAPON_TO_CHAR tw7_goon[2] WEAPONTYPE_TEC9 30000 
									SET_CHAR_KINDA_STAY_IN_SAME_PLACE tw7_goon[2] TRUE
									SET_CHAR_DECISION_MAKER tw7_goon[2] tw7_empty_decision_maker
								ENDIF
								
								START_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike 40 
								tw7_control_flag = 1
							ENDIF
						ENDIF			   
					
						IF tw7_control_flag = 1
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
								SWITCH_ROADS_ON 2363.5 -1291.7 10.0 2379.5 -1181.2 100.0
								SWITCH_ROADS_ON 2295.9 -1291.5 10.0 2311.5 -1162.3 100.0
								SWITCH_ROADS_ON 2333.9 -1392.1 10.0 2220.2 -1377.1 100.0
								SWITCH_ROADS_ON 2334.6 -1493.6 10.0 2219.8 -1475.2 100.0
								OPEN_SEQUENCE_TASK tw7_seq
									TASK_LEAVE_ANY_CAR -1
									TASK_GO_STRAIGHT_TO_COORD -1 2300.3 -1502.8 24.3 PEDMOVE_RUN -1
									TASK_ACHIEVE_HEADING -1 205.1
								CLOSE_SEQUENCE_TASK tw7_seq
								PERFORM_SEQUENCE_TASK tw7_ass_bandit tw7_seq
								CLEAR_SEQUENCE_TASK tw7_seq

								IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
									SET_CAR_ALWAYS_CREATE_SKIDS tw7_ass_bandit_bike FALSE
								ENDIF
								tw7_control_flag = 2
							ENDIF
						ENDIF

						IF tw7_control_flag = 2 
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 30.0 30.0 FALSE 
							OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer 
							OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer 
								IF IS_GROUP_MEMBER mc_strap Players_Group
									REMOVE_CHAR_FROM_GROUP mc_strap
								ENDIF 
																
								IGNORE_HEIGHT_DIFFERENCE_FOLLOWING_NODES mc_strap TRUE 
								SET_FOLLOW_NODE_THRESHOLD_DISTANCE mc_strap 50.0

								OPEN_SEQUENCE_TASK tw7_seq
									TASK_LEAVE_ANY_CAR -1
									TASK_KILL_CHAR_ON_FOOT -1 tw7_ass_bandit
								CLOSE_SEQUENCE_TASK tw7_seq
								PERFORM_SEQUENCE_TASK mc_strap tw7_seq
								CLEAR_SEQUENCE_TASK tw7_seq

								tw7_speech_flag = 0
								tw7_car_nodes = 0
								tw7_control_flag = 0
								tw7_goals = 14
							ENDIF
						ENDIF
					ENDIF


				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////// guys at the basketball court /////////////////////////////////////////////////////////////
					IF tw7_goals = 14
						/////////////////// SPEECH FOR THIS SECTION //////////////////////
						IF tw7_speech_flag = 0	
							IF HAS_CHAR_SPOTTED_CHAR mc_strap tw7_ass_bandit
							OR HAS_CHAR_SPOTTED_CHAR tw7_ass_bandit mc_strap
							//IF IS_CHAR_RESPONDING_TO_EVENT mc_strap EVENT_ACQUAINTANCE_PED_HATE
							//OR IS_CHAR_RESPONDING_TO_EVENT tw7_ass_bandit EVENT_ACQUAINTANCE_PED_HATE
								tw7_speech_goals = 7
								tw7_speech_control_flag = 0
								GOSUB tw7_dialogue_setup 
								timerb = 0
								tw7_speech_flag = 1
							ENDIF
						ENDIF

						IF tw7_speech_flag = 1
							IF tw7_speech_goals = 0  
								// I'm gonna kill that cheeky motherfucker!
								tw7_speech_goals = 17
								tw7_speech_control_flag = 0
								GOSUB tw7_dialogue_setup 
								tw7_speech_flag = 2
							ENDIF
						ENDIF
						
						IF tw7_control_flag = 0
							REMOVE_BLIP tw7_control_blip
							ADD_BLIP_FOR_CHAR tw7_ass_bandit tw7_control_blip
							
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER tw7_ass_bandit FALSE
							OPEN_SEQUENCE_TASK tw7_seq
								TASK_LEAVE_ANY_CAR -1
								TASK_TURN_CHAR_TO_FACE_CHAR -1 mc_strap 
								TASK_KILL_CHAR_ON_FOOT -1 mc_strap
							CLOSE_SEQUENCE_TASK tw7_seq
							PERFORM_SEQUENCE_TASK tw7_ass_bandit tw7_seq
							CLEAR_SEQUENCE_TASK tw7_seq

							IF NOT IS_CHAR_DEAD tw7_goon[0] 
								GET_SCRIPT_TASK_STATUS tw7_goon[0] TASK_KILL_CHAR_ON_FOOT task_status
								IF task_status = FINISHED_TASK
						 			TASK_KILL_CHAR_ON_FOOT tw7_goon[0] scplayer	
								ENDIF
						 	ENDIF	
							IF NOT IS_CHAR_DEAD tw7_goon[1] 
								GET_SCRIPT_TASK_STATUS tw7_goon[1] TASK_KILL_CHAR_ON_FOOT task_status
								IF task_status = FINISHED_TASK
						 			TASK_KILL_CHAR_ON_FOOT tw7_goon[1] scplayer	
								ENDIF
						 	ENDIF	
							IF NOT IS_CHAR_DEAD tw7_goon[2] 
								GET_SCRIPT_TASK_STATUS tw7_goon[2] TASK_KILL_CHAR_ON_FOOT task_status
								IF task_status = FINISHED_TASK
						 			TASK_KILL_CHAR_ON_FOOT tw7_goon[2] scplayer	
								ENDIF
						 	ENDIF
							tw7_control_flag = 1
						ENDIF	
					ENDIF			   
				ELSE
					REMOVE_BLIP tw7_control_blip
					IF NOT IS_CHAR_DEAD tw7_goon[0]
						TASK_FLEE_CHAR tw7_goon[0] scplayer 20.0 -2
					ENDIF 	
					IF NOT IS_CHAR_DEAD tw7_goon[1]
						TASK_FLEE_CHAR tw7_goon[1] scplayer 20.0 -2
					ENDIF 	
					IF NOT IS_CHAR_DEAD tw7_goon[2]
						TASK_FLEE_CHAR tw7_goon[2] scplayer 20.0 -2
					ENDIF 	
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					timera = 0
					tw7_control_flag = 0
					tw7_speech_flag = 0
					tw7_goals = 15
				ENDIF
			ELSE
				REMOVE_BLIP tw7_control_blip
				IF NOT IS_CHAR_DEAD tw7_goon[0]
					TASK_FLEE_CHAR tw7_goon[0] scplayer 20.0 -2
				ENDIF 	
				IF NOT IS_CHAR_DEAD tw7_goon[1]
					TASK_FLEE_CHAR tw7_goon[1] scplayer 20.0 -2
				ENDIF 	
				IF NOT IS_CHAR_DEAD tw7_goon[2]
					TASK_FLEE_CHAR tw7_goon[2] scplayer 20.0 -2
				ENDIF 	
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				timera = 0
				tw7_control_flag = 0
				tw7_speech_flag = 0
				tw7_goals = 15
			ENDIF
		ENDIF
	ENDIF
				

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////ASS BANDIT DEAD CUTSCENE ///////////////////////////////////////////////////////////////////////
	IF tw7_goals = 15
		IF tw7_control_flag = 0
			IF timera > 2000
	            GOSUB check_player_is_safe
	            IF player_is_completely_safe = 1
					CLEAR_PRINTS
				
					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB tw7_death_checks
					IF tw7_deathcheck_flag = 1	
						GOTO mission_twar7_failed
					ENDIF
					
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					tw7_speech_goals = 0
					
					DELETE_CHAR tw7_goon[0]
					DELETE_CHAR tw7_goon[1]
					DELETE_CHAR tw7_goon[2]
					
					SWITCH_ROADS_BACK_TO_ORIGINAL 2563.7 -1435.0 10.0 2580.3 -1266.4 100.6
					SWITCH_ROADS_BACK_TO_ORIGINAL 2635.0 -1434.4 10.0 2652.0 -1263.6 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2714.9 -1501.6 10.0 2746.1 -1266.4 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2713.9 -1499.3 10.0 2746.6 -1266.3 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2714.6 -1249.9 10.0 2744.7 -1332.9 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2634.6 -1396.6 10.0 2651.0 -1264.2 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2635.1 -1248.0 10.0 2651.0 -1055.3 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2651.5 -1263.8 10.0 2579.5 -1248.7 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2563.7 -1248.6 10.0 2578.9 -1192.2 100.6
					SWITCH_ROADS_BACK_TO_ORIGINAL 2443.7 -1249.2 10.0 2459.1 -1191.9 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2363.5 -1253.4 10.0 2378.7 -1145.5 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2311.2 -1144.7 10.0 2379.1 -1162.1 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2239.9 -1130.0 10.0 2017.6 -928.3 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 1930.6 -1031.9 10.0 1574.4 -879.9 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 1879.6 -1024.3 10.0 1552.0 -1521.7 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 1725.8 -1433.3 10.0 1838.0 -1459.4 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 1838.4 -1453.6 10.0 1853.7 -1303.4 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 1979.3 -1468.7 10.0 1853.5 -1452.5 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2334.0 -1556.1 10.0 2350.5 -1489.7 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2350.1 -1516.0 10.0 2423.9 -1532.5 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2379.8 -1373.3 10.0 2423.7 -1452.6 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2363.5 -1291.7 10.0 2379.5 -1181.2 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2295.9 -1291.5 10.0 2311.5 -1162.3 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2333.9 -1392.1 10.0 2220.2 -1377.1 100.0
					SWITCH_ROADS_BACK_TO_ORIGINAL 2334.6 -1493.6 10.0 2219.8 -1475.2 100.0

					MARK_CAR_AS_NO_LONGER_NEEDED tw7_players_bike
					MARK_CAR_AS_NO_LONGER_NEEDED tw7_ass_bandit_bike
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
					MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
					
					REQUEST_ANIMATION MISC
					LOAD_ALL_MODELS_NOW

					REQUEST_COLLISION 2293.4 -1490.2 
					LOAD_SCENE_IN_DIRECTION 2293.4 -1490.2 22.2  90.0 
					CLEAR_AREA 2293.4 -1490.2 22.2 20.0 TRUE

					SET_PED_DENSITY_MULTIPLIER 0.0
					SET_PLAYER_CONTROL player1 OFF		
					SWITCH_WIDESCREEN ON
					SHUT_ALL_CHARS_UP TRUE
					MAKE_PLAYER_GANG_DISAPPEAR    
					
					IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
						SET_CAR_ALWAYS_CREATE_SKIDS tw7_ass_bandit_bike FALSE
					ENDIF
				
					//getting current player position
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
					GET_CHAR_COORDINATES scplayer tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz
					GET_CHAR_HEADING scplayer tw7_plyr_cut_heading
					  
					REMOVE_CHAR_FROM_GROUP mc_strap
					CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap 
				
					//setting player in position
					CLEAR_AREA 2290.4 -1490.2 22.3 1.0 TRUE
					SET_CHAR_COORDINATES scplayer 2290.4 -1490.2 22.3
					SET_CHAR_HEADING scplayer 265.8 
					TASK_GO_STRAIGHT_TO_COORD scplayer 2295.6 -1490.5 21.3 PEDMOVE_WALK -1  
					GET_CURRENT_CHAR_WEAPON scplayer tw7_which_gun 
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED  

					//setting mc-strap in position
					CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap 
					CLEAR_AREA 2295.3 -1491.3 22.3 1.0 TRUE
					SET_CHAR_COORDINATES mc_strap 2295.3 -1491.3 22.3
					SET_CHAR_HEADING mc_strap 268.4
					TASK_AIM_GUN_AT_COORD mc_strap 2299.3 -1490.6 22.8 10000  
				
					SET_FIXED_CAMERA_POSITION 2299.3 -1490.6 22.8 0.0 0.0 0.0			
					POINT_CAMERA_AT_POINT 2255.6 -1493.3 38.3 JUMP_CUT
				
					tw7_speech_goals = 8
					tw7_speech_control_flag = 0
					GOSUB tw7_dialogue_setup 
					
					tw7_control_flag = 1
					DO_FADE 1500 FADE_IN	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB tw7_death_checks
					IF tw7_deathcheck_flag = 1
						GOTO mission_twar7_failed
					ENDIF

					tw7_skip_cutscene_flag = 1
					SKIP_CUTSCENE_START 
				ENDIF
			ENDIF
		ENDIF

		IF tw7_control_flag = 1 
			GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD task_status	
			IF task_status = FINISHED_TASK
				TASK_TURN_CHAR_TO_FACE_CHAR mc_strap scplayer  
				OPEN_SEQUENCE_TASK tw7_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 mc_strap
					TASK_PLAY_ANIM -1 IDLE_CHAT_02 MISC 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK tw7_seq
				PERFORM_SEQUENCE_TASK scplayer tw7_seq
				CLEAR_SEQUENCE_TASK tw7_seq
				tw7_control_flag = 2
			ENDIF
		ENDIF 
		
		IF tw7_control_flag = 2 
			IF tw7_speech_control_flag > 2
				CLEAR_CHAR_TASKS scplayer
				TASK_PLAY_ANIM mc_strap IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1  
				tw7_control_flag = 3
			ENDIF
		ENDIF

		IF tw7_control_flag = 3
			IF tw7_speech_control_flag > 3
				CLEAR_CHAR_TASKS mc_strap
				TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1  
				tw7_control_flag = 4
			ENDIF
		ENDIF

		IF tw7_control_flag = 4 
			IF tw7_speech_control_flag > 4
				CLEAR_CHAR_TASKS scplayer
				TASK_PLAY_ANIM mc_strap IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1  
				tw7_control_flag = 5
			ENDIF
		ENDIF

		IF tw7_control_flag = 5
			IF tw7_speech_control_flag > 5
				CLEAR_CHAR_TASKS mc_strap
				TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1  
				tw7_control_flag = 6
			ENDIF
		ENDIF

		IF tw7_control_flag = 6 
			IF tw7_speech_control_flag > 6
				CLEAR_CHAR_TASKS scplayer
				TASK_PLAY_ANIM mc_strap IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				tw7_control_flag = 7
			ENDIF
		ENDIF

		IF tw7_control_flag = 7
			IF tw7_speech_goals = 0 
				tw7_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB tw7_death_checks
				IF tw7_deathcheck_flag = 1
					GOTO mission_twar7_failed
				ENDIF
				
				CLEAR_CHAR_TASKS mc_strap

				IF tw7_skip_cutscene_flag = 1				
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2	
					tw7_speech_goals = 0
				ENDIF
				
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB tw7_death_checks
				IF tw7_deathcheck_flag = 1
					GOTO mission_twar7_failed
				ENDIF
				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				tw7_plyr_cutz -= 1.0
				CLEAR_AREA tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz 1.0 TRUE 
				REQUEST_COLLISION tw7_plyr_cutx tw7_plyr_cuty   
				LOAD_ALL_MODELS_NOW
				SET_CHAR_COORDINATES scplayer tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz
				SET_CHAR_HEADING scplayer tw7_plyr_cut_heading 
				SET_CURRENT_CHAR_WEAPON scplayer tw7_which_gun  

				CLEAR_CHAR_TASKS_IMMEDIATELY mc_strap	 
				GET_CLOSEST_CHAR_NODE tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz tw7_tempx tw7_tempy tw7_tempz
				REQUEST_COLLISION tw7_tempx tw7_tempy   
				LOAD_ALL_MODELS_NOW
				tw7_tempz += 2.0
				GET_GROUND_Z_FOR_3D_COORD tw7_tempx tw7_tempy tw7_tempz tw7_plyr_cutz
				CLEAR_AREA tw7_tempx tw7_tempy tw7_plyr_cutz 1.0 TRUE 
				SET_CHAR_COORDINATES mc_strap tw7_tempx tw7_tempy tw7_plyr_cutz
				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				SET_GROUP_MEMBER Players_Group mc_strap
				tw7_flag_mc_strap_in_group = 1
			
				LOAD_SCENE tw7_plyr_cutx tw7_plyr_cuty tw7_plyr_cutz 
			
				REMOVE_ANIMATION MISC

				SET_PED_DENSITY_MULTIPLIER 1.0
				REMOVE_BLIP tw7_control_blip
				ADD_BLIP_FOR_COORD 783.2 -1630.3 12.2 tw7_control_blip
				SET_BLIP_AS_FRIENDLY tw7_control_blip TRUE	
				SWITCH_WIDESCREEN OFF
				SHUT_ALL_CHARS_UP FALSE
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER	
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				
				DO_FADE 1500 FADE_IN	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB tw7_death_checks
				IF tw7_deathcheck_flag = 1
					GOTO mission_twar7_failed
				ENDIF
				tw7_control_flag = 0
				tw7_speech_flag = 0
				tw7_goals = 16
				timerb = 0	
			ENDIF	
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Driving OG Loc back to the burger shot /////////////////////////////////////////////////////////
	IF tw7_goals = 16
		IF tw7_speech_goals = 0 
			IF tw7_speech_flag = 0
				tw7_speech_goals = 9
				tw7_speech_control_flag = 0
				GOSUB tw7_dialogue_setup 
				tw7_speech_flag = 1
			ENDIF
		ENDIF
		
		IF tw7_speech_goals = 0 
			IF tw7_speech_flag = 1
				PRINT_NOW ( SMK1_13 ) 8500 1 //Take OG Loc to the Burger Shot.
				tw7_speech_flag = 2
			ENDIF
		ENDIF

		IF tw7_control_flag = 0
			IF IS_GROUP_MEMBER mc_strap Players_Group
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 783.2 -1630.3 12.2 4.0 4.0 4.0 TRUE 
					IF LOCATE_CHAR_ANY_MEANS_3D mc_strap 783.2 -1630.3 12.2 4.0 4.0 4.0 FALSE
						//checking if player is in a vehicle
						SET_PLAYER_CONTROL player1 OFF
						tw7_control_flag = 0
						tw7_goals = 17
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		GOSUB tw7_mc_strap_group 	
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// final cutscene at the burger shot /////////////////////////////////////////////////////////////
	IF tw7_goals = 17
		IF tw7_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			tw7_speech_goals = 0
				
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON 
			SHUT_ALL_CHARS_UP TRUE 
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			REMOVE_CHAR_FROM_GROUP mc_strap

			OPEN_SEQUENCE_TASK tw7_seq
				TASK_PAUSE -1 1500
				TASK_LEAVE_ANY_CAR -1
				TASK_TURN_CHAR_TO_FACE_CHAR -1 mc_strap 
			CLOSE_SEQUENCE_TASK tw7_seq
			PERFORM_SEQUENCE_TASK scplayer tw7_seq
			CLEAR_SEQUENCE_TASK tw7_seq
		
			OPEN_SEQUENCE_TASK tw7_seq
				TASK_LEAVE_ANY_CAR -1
				//TASK_PAUSE -1 2000  
				TASK_GO_STRAIGHT_TO_COORD -1 792.1 -1626.0 12.4 PEDMOVE_WALK -2
				TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
			CLOSE_SEQUENCE_TASK tw7_seq
			PERFORM_SEQUENCE_TASK mc_strap tw7_seq
			CLEAR_SEQUENCE_TASK tw7_seq
			
			tw7_speech_goals = 10
			tw7_speech_control_flag = 0
			GOSUB tw7_dialogue_setup 
			
			SET_FIXED_CAMERA_POSITION 776.7145 -1637.2482 14.0840 0.0 0.0 0.0			
			POINT_CAMERA_AT_POINT 777.5906 -1636.7693 14.1398 JUMP_CUT
			tw7_control_flag = 1
		ENDIF

		IF tw7_control_flag = 1
			IF tw7_speech_goals = 0
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				DELETE_CHAR mc_strap
			 
				REMOVE_BLIP tw7_control_blip
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
				GOTO mission_twar7_passed
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////setting up the drive-by shooting////////////////////////////////////////////////////////////////
	IF tw7_goals > 3
		IF tw7_goals < 14
			IF NOT IS_CHAR_DEAD tw7_ass_bandit 	
				GET_SCRIPT_TASK_STATUS tw7_ass_bandit TASK_DRIVE_BY task_status
				IF task_status = FINISHED_TASK
					TASK_DRIVE_BY tw7_ass_bandit scplayer -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN FALSE 100
				ENDIF
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////failing mission/////////////////////////////////////////////////////////////////////////////////
	IF tw7_goals > 3 
		IF tw7_goals < 15  
			IF NOT IS_CHAR_DEAD tw7_ass_bandit
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tw7_ass_bandit 250.0 250.0 FALSE
					IF NOT IS_CHAR_ON_SCREEN tw7_ass_bandit 
						tw7_control_flag = 0	  
						tw7_goals = 20	  
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF	  

	IF tw7_goals = 20
		IF tw7_control_flag = 0
			IF NOT IS_CHAR_DEAD mc_strap
				IF NOT IS_CAR_DEAD tw7_players_bike  
					IF IS_CHAR_IN_CAR mc_strap tw7_players_bike  
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						tw7_speech_goals = 0
					
						SET_PLAYER_CONTROL player1 OFF 
						SWITCH_WIDESCREEN ON
						MAKE_PLAYER_GANG_DISAPPEAR  
						//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
						
						PRINT_NOW ( SMK1_12 ) 4000 1 //Freddy got away!
						
						IF IS_CHAR_IN_ANY_CAR scplayer
							APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
						ENDIF

						REMOVE_CHAR_FROM_GROUP mc_strap
						OPEN_SEQUENCE_TASK tw7_seq
							TASK_LEAVE_ANY_CAR -1
							TASK_WANDER_STANDARD -1  
						CLOSE_SEQUENCE_TASK tw7_seq
						PERFORM_SEQUENCE_TASK mc_strap tw7_seq
						CLEAR_SEQUENCE_TASK tw7_seq
						timera = 0 
						tw7_control_flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF IS_CAR_DEAD tw7_players_bike
				tw7_control_flag = 2
			ENDIF 
			IF IS_CHAR_DEAD mc_strap
				tw7_control_flag = 2
			ENDIF
			IF NOT IS_CHAR_IN_CAR mc_strap tw7_players_bike  
				tw7_control_flag = 2
			ENDIF	
		ENDIF
		IF tw7_control_flag = 1
			IF timera > 3000  
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				DELETE_CHAR mc_strap
			 
				IF IS_CHAR_IN_ANY_CAR scplayer
					APPLY_BRAKES_TO_PLAYERS_CAR player1 OFF
				ENDIF
			
				REMOVE_BLIP tw7_control_blip
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER	
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				
				DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				tw7_control_flag = 2
			ENDIF	
		ENDIF

		IF tw7_control_flag = 2
			CLEAR_PRINTS
			PRINT_NOW ( SMK1_12 ) 4000 1 //Freddy got away!
			GOTO mission_twar7_failed	
		ENDIF
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Streaming in the car_recordings/////////////////////////////////////////////////////////////////
	IF tw7_goals = 3
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 30
			REQUEST_CAR_RECORDING 30
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 31
			REQUEST_CAR_RECORDING 31
		ENDIF
	ENDIF 
	IF tw7_goals = 4
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 32
			REQUEST_CAR_RECORDING 32
		ENDIF
	ENDIF 
	IF tw7_goals = 5
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 33
			REQUEST_CAR_RECORDING 33
		ENDIF
	ENDIF 
	IF tw7_goals = 6
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 34
			REQUEST_CAR_RECORDING 34
		ENDIF
	ENDIF 
	IF tw7_goals = 7
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 35
			REQUEST_CAR_RECORDING 35
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 50
			REQUEST_CAR_RECORDING 50
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 51
			REQUEST_CAR_RECORDING 51
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 52
			REQUEST_CAR_RECORDING 52
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 53
			REQUEST_CAR_RECORDING 53
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 54
			REQUEST_CAR_RECORDING 54
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 55
			REQUEST_CAR_RECORDING 55
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 56
			REQUEST_CAR_RECORDING 56
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 57
			REQUEST_CAR_RECORDING 57
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 58
			REQUEST_CAR_RECORDING 58
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 35
			REQUEST_CAR_RECORDING 60
		ENDIF
		
		REQUEST_MODEL MANANA
	ENDIF 
	IF tw7_goals = 8
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 36
			REQUEST_CAR_RECORDING 36
		ENDIF
	ENDIF 
	IF tw7_goals = 9
		MARK_MODEL_AS_NO_LONGER_NEEDED MANANA 
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 37
			REQUEST_CAR_RECORDING 37
		ENDIF
	ENDIF 
	IF tw7_goals = 10
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 38
			REQUEST_CAR_RECORDING 38
		ENDIF
	ENDIF 
	IF tw7_goals = 11
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 39
			REQUEST_CAR_RECORDING 39
		ENDIF
	ENDIF 
	IF tw7_goals = 12
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 40
			REQUEST_CAR_RECORDING 40
		ENDIF

		REQUEST_MODEL LSV3
	ENDIF 


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////blips///////////////////////////////////////////////////////////////////////////////////////////
	IF tw7_goals > 0 
		IF tw7_goals < 3 
			GOSUB tw7_blippage 
		ENDIF
	ENDIF
	IF tw7_goals > 3
		IF tw7_goals < 14
			GOSUB tw7_blippage_part2
		ENDIF
	ENDIF


	///ingame dialogue///
	GOSUB tw7_overall_dialogue

GOTO twar7mainloop



	
// Mission twar7 failed
mission_twar7_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission twar7 passed
mission_twar7_passed:

CLEAR_PRINTS
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED SMOKE_1
PLAYER_MADE_PROGRESS 1
flag_smoke_mission_counter ++
START_NEW_SCRIPT strap_mission_loop
REMOVE_BLIP strap_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
RETURN
		


// mission cleanup
mission_cleanup_twar7:

//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
REMOVE_BLIP tw7_control_blip
CLEAR_MISSION_AUDIO 3
MARK_MODEL_AS_NO_LONGER_NEEDED MANANA
MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
REMOVE_ANIMATION CAR_CHAT
REMOVE_ANIMATION CRIB
REMOVE_ANIMATION MISC
REMOVE_ANIMATION RAPPING
SWITCH_ROADS_BACK_TO_ORIGINAL 2563.7 -1435.0 10.0 2580.3 -1266.4 100.6
SWITCH_ROADS_BACK_TO_ORIGINAL 2635.0 -1434.4 10.0 2652.0 -1263.6 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2714.9 -1501.6 10.0 2746.1 -1266.4 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2713.9 -1499.3 10.0 2746.6 -1266.3 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2714.6 -1249.9 10.0 2744.7 -1332.9 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2634.6 -1396.6 10.0 2651.0 -1264.2 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2635.1 -1248.0 10.0 2651.0 -1055.3 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2651.5 -1263.8 10.0 2579.5 -1248.7 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2563.7 -1248.6 10.0 2578.9 -1192.2 100.6
SWITCH_ROADS_BACK_TO_ORIGINAL 2443.7 -1249.2 10.0 2459.1 -1191.9 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2363.5 -1253.4 10.0 2378.7 -1145.5 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2311.2 -1144.7 10.0 2379.1 -1162.1 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2239.9 -1130.0 10.0 2017.6 -928.3 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1930.6 -1031.9 10.0 1574.4 -879.9 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1879.6 -1024.3 10.0 1552.0 -1521.7 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1725.8 -1433.3 10.0 1838.0 -1459.4 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1838.4 -1453.6 10.0 1853.7 -1303.4 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 1979.3 -1468.7 10.0 1853.5 -1452.5 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2334.0 -1556.1 10.0 2350.5 -1489.7 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2350.1 -1516.0 10.0 2423.9 -1532.5 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2379.8 -1373.3 10.0 2423.7 -1452.6 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2363.5 -1291.7 10.0 2379.5 -1181.2 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2295.9 -1291.5 10.0 2311.5 -1162.3 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2333.9 -1392.1 10.0 2220.2 -1377.1 100.0
SWITCH_ROADS_BACK_TO_ORIGINAL 2334.6 -1493.6 10.0 2219.8 -1475.2 100.0
SWITCH_ENTRY_EXIT LAHS1A TRUE

SWITCH_RANDOM_TRAINS ON
REMOVE_CHAR_ELEGANTLY big_smoke 
REMOVE_CHAR_ELEGANTLY sweet
REMOVE_CHAR_ELEGANTLY mc_strap
UNLOAD_SPECIAL_CHARACTER 1																		   
UNLOAD_SPECIAL_CHARACTER 2																		   
UNLOAD_SPECIAL_CHARACTER 3
ENABLE_AMBIENT_CRIME TRUE
CLEAR_SEQUENCE_TASK tw7_group_seq
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
tw7_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_goals < 4
	IF IS_CHAR_DEAD big_smoke 	
		CLEAR_PRINTS
		PRINT_NOW ( SMK1_06 ) 7000 1 //Big Smoke has been killed.	
		tw7_deathcheck_flag = 1	
	ENDIF

	IF IS_CHAR_DEAD sweet 	
		CLEAR_PRINTS
		PRINT_NOW ( SMK1_07 ) 7000 1 //sweet has been killed.	
		tw7_deathcheck_flag = 1		
	ENDIF

	IF IS_CAR_DEAD tw7_smokes_car
		CLEAR_PRINTS
		PRINT_NOW ( SMK1_08 ) 7000 1 //Smoke's car has been destroyed.	
		tw7_deathcheck_flag = 1		
	ENDIF
ENDIF

//ignoring chars not being there during animated cutscene
IF tw7_goals = 1
	IF tw7_control_flag = 1
		IF tw7_deathcheck_flag = 1
			tw7_deathcheck_flag = 0
		ENDIF
	ENDIF
ENDIF	

IF tw7_goals = 1
	IF tw7_control_flag = 2
		IF IS_CHAR_DEAD mc_strap
			CLEAR_PRINTS
			PRINT_NOW ( SMK1_05 ) 7000 1 //MC Strap has been killed!	
			tw7_deathcheck_flag = 1	
		ENDIF
	ENDIF
ENDIF
IF tw7_goals > 1
	IF IS_CHAR_DEAD mc_strap
		CLEAR_PRINTS
		PRINT_NOW ( SMK1_05 ) 7000 1 //MC Strap has been killed!	
		tw7_deathcheck_flag = 1	
	ENDIF
ENDIF

IF tw7_goals > 3
	IF tw7_goals < 15
		IF IS_CAR_DEAD tw7_players_bike
			CLEAR_PRINTS
			PRINT_NOW ( SMK1_14 ) 7000 1 //The bike you needed was destroyed!
			tw7_deathcheck_flag = 1	
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_blippage:///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//sorting the blippage
IF tw7_car_check_flag = 0
	IF IS_CHAR_IN_CAR scplayer tw7_smokes_car
		CLEAR_THIS_PRINT SMK1_01 
		REMOVE_BLIP tw7_control_blip
		IF tw7_goals = 1
			IF tw7_control_flag = 0 
				ADD_BLIP_FOR_COORD 1532.8 -1672.9 12.4 tw7_control_blip	// picking up mc_strap
			ELSE
				ADD_BLIP_FOR_COORD 2454.4 -1284.5 22.7 tw7_control_blip	// ass bandit house
			ENDIF
		ENDIF	
		tw7_car_check_flag = 1
	ENDIF
ENDIF

IF tw7_car_check_flag = 1 
	IF NOT IS_CHAR_IN_CAR scplayer tw7_smokes_car
		REMOVE_BLIP tw7_control_blip
		ADD_BLIP_FOR_CAR tw7_smokes_car tw7_control_blip
		SET_BLIP_AS_FRIENDLY tw7_control_blip TRUE
		tw7_car_check_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_blippage_part2://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_flag_mc_strap_in_group = 1
	IF tw7_goals > 3
		IF tw7_goals < 14
			IF tw7_car_check_flag = 0
				IF NOT IS_CAR_DEAD tw7_players_bike 
					IF IS_CHAR_IN_CAR scplayer tw7_players_bike
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
						CLEAR_THIS_PRINT SMK1_11 
						CLEAR_THIS_PRINT SMK1_04 
						PRINT_NOW ( SMK1_04 ) 7000 1 //Catch and kill the Ass Bandit!
						REMOVE_BLIP tw7_control_blip
						ADD_BLIP_FOR_CHAR tw7_ass_bandit tw7_control_blip
						SET_BLIP_AS_FRIENDLY tw7_control_blip FALSE
						tw7_car_check_flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF tw7_car_check_flag = 1 
				IF NOT IS_CAR_DEAD tw7_players_bike 
					IF NOT IS_CHAR_IN_CAR scplayer tw7_players_bike
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
						CLEAR_THIS_PRINT SMK1_11 
						CLEAR_THIS_PRINT SMK1_04 
						PRINT_NOW ( SMK1_11 ) 7000 1 //Get back on the bike and continue with the mission.
						REMOVE_BLIP tw7_control_blip
						ADD_BLIP_FOR_CAR tw7_players_bike tw7_control_blip
						SET_BLIP_AS_FRIENDLY tw7_control_blip TRUE
						tw7_car_check_flag = 0
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
tw7_mc_strap_group://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_flag_mc_strap_in_group = 1
    IF NOT IS_GROUP_MEMBER mc_strap Players_Group
        REMOVE_BLIP tw7_control_blip
        ADD_BLIP_FOR_CHAR mc_strap tw7_control_blip
		SET_BLIP_AS_FRIENDLY tw7_control_blip TRUE
        tw7_flag_mc_strap_in_group = 0
    ENDIF
ENDIF

IF tw7_flag_mc_strap_in_group = 0 
    IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer mc_strap 8.0 8.0 8.0 FALSE
	    IF NOT IS_GROUP_MEMBER mc_strap Players_Group  
        	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
        	SET_GROUP_MEMBER Players_Group mc_strap 
		ENDIF
        IF tw7_goals < 16  
	        REMOVE_BLIP tw7_control_blip
			IF IS_CHAR_IN_CAR scplayer tw7_players_bike 
				ADD_BLIP_FOR_CHAR tw7_ass_bandit tw7_control_blip
			ELSE
				ADD_BLIP_FOR_CAR tw7_players_bike tw7_control_blip
				SET_BLIP_AS_FRIENDLY tw7_control_blip TRUE
			ENDIF
		ELSE
	        REMOVE_BLIP tw7_control_blip
			ADD_BLIP_FOR_COORD 783.2 -1630.3 12.2 tw7_control_blip
		ENDIF	
        tw7_flag_mc_strap_in_group = 1
    ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_sorting_speed://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CHAR_DEAD tw7_ass_bandit
	IF NOT IS_CAR_DEAD tw7_ass_bandit_bike
		IF IS_CHAR_IN_CAR tw7_ass_bandit tw7_ass_bandit_bike  
			//sorting out car health
			IF tw7_car_health_flag = 0
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit scplayer
				OR HAS_CAR_BEEN_DAMAGED_BY_CHAR tw7_ass_bandit_bike scplayer  
					SET_CAR_HEALTH tw7_ass_bandit_bike 350
					tw7_car_health_flag = 1
				ENDIF
			ENDIF
			
			GET_CHAR_COORDINATES scplayer tw7_playerx tw7_playery tw7_tempx

			GET_CAR_COORDINATES tw7_ass_bandit_bike tw7_ass_banditx tw7_ass_bandity tw7_tempx

			GET_DISTANCE_BETWEEN_COORDS_2D tw7_playerx tw7_playery tw7_ass_banditx tw7_ass_bandity tw7_tempx

			////////////////////////////////////////////////////////////////////////////////////////////
			/////  playback ranges from 0.0 - 2.0 													////
			/////  sqrt of tempx is from 0.0 (close) to 100.0 (far)									////
			/////  when tempx < 21, playback speed should be 1.0 - 2.0 (i.e. speed car up a bit)    ////   
			/////  when tempx > 20, playback speed should be 0.0 - 1.0 (i.e. slow car down a bit)   ////
			/////  speed should never be above 2.0 or below 0.0										////
			/////  formula for < 21 = 2.0 - (actual / 20.0) 										////
			/////  formula for > 21 = 1.0 - ((actual - 20.0) / 80.0) 								////
			////////////////////////////////////////////////////////////////////////////////////////////

			IF tw7_tempx < 21.0 
				tw7_tempx = tw7_tempx / 20.0
				tw7_tempy = tw7_tempx
				tw7_tempx = 2.0 - tw7_tempy
			ELSE
				tw7_tempx = tw7_tempx - 20.0


				//tw7_tempx = tw7_tempx / 80.0
				tw7_tempx = tw7_tempx / 90.0

				tw7_tempy = tw7_tempx
				tw7_tempx = 1.0 - tw7_tempy
			ENDIF
			 	 
			tw7_playback_speed = tw7_tempx

			IF tw7_playback_speed > 2.0
				tw7_playback_speed = 2.0
			ENDIF
			IF tw7_playback_speed < 0.5
				tw7_playback_speed = 0.5
			ENDIF
			/*
			IF tw7_playback_speed < 0.0
				tw7_playback_speed = 0.0
			ENDIF
			*/
			IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
				SET_PLAYBACK_SPEED tw7_ass_bandit_bike tw7_playback_speed 
			ENDIF

			//setting the speed of the other cars
			IF tw7_goals = 8
				IF tw7_car_playback_flag < 10 
					IF NOT IS_CAR_DEAD tw7_car[tw7_car_playback_flag]
						IF NOT IS_CHAR_DEAD tw7_char[tw7_car_playback_flag]
							IF IS_CHAR_IN_CAR tw7_char[tw7_car_playback_flag] tw7_car[tw7_car_playback_flag]   
								IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_car[tw7_car_playback_flag]
									SET_PLAYBACK_SPEED tw7_car[tw7_car_playback_flag] tw7_playback_speed 
								ENDIF
							ELSE
								IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_car[tw7_car_playback_flag]
									STOP_PLAYBACK_RECORDED_CAR tw7_car[tw7_car_playback_flag]
								ENDIF
							ENDIF
						ELSE
							IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_car[tw7_car_playback_flag]
								STOP_PLAYBACK_RECORDED_CAR tw7_car[tw7_car_playback_flag]
							ENDIF
						ENDIF
					ENDIF
					tw7_car_playback_flag ++
				ELSE
					tw7_car_playback_flag = 0
				ENDIF
			ENDIF

			/////DEBUG/////////////////////////////////////////
			IF NOT IS_CHAR_IN_CAR tw7_ass_bandit tw7_ass_bandit_bike
				WARP_CHAR_INTO_CAR tw7_ass_bandit tw7_ass_bandit_bike
			ENDIF 
			/////DEBUG/////////////////////////////////////////
		ELSE
			IF NOT IS_CAR_DEAD tw7_ass_bandit_bike 
				IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
					STOP_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ELSE
	IF NOT IS_CAR_DEAD tw7_ass_bandit_bike 
		IF IS_PLAYBACK_GOING_ON_FOR_CAR tw7_ass_bandit_bike
			STOP_PLAYBACK_RECORDED_CAR tw7_ass_bandit_bike
		ENDIF
	ENDIF
ENDIF		
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_my_number_plates:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 tw7_no_plates_flag
	IF tw7_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates got_m00_
	ENDIF 
	IF tw7_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates m00tv_4u 
	ENDIF
	IF tw7_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates mathew_2 
	ENDIF 
	IF tw7_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates d4_dew0r 
	ENDIF 
	IF tw7_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates d0de_777 
	ENDIF 
	IF tw7_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates dam0_666 
	ENDIF 
	IF tw7_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates C0NEY_88 
	ENDIF 
	IF tw7_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates pre4cher 
	ENDIF 
	IF tw7_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates dbp_4ndy 
	ENDIF 
	IF tw7_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates ev1l_sly 
	ENDIF 
	IF tw7_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates n1_r4v3n 
	ENDIF 
	IF tw7_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates d1vx_z00 
	ENDIF 
	IF tw7_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates mr_b3nn 
	ENDIF 
	IF tw7_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates r3d_r4sp 
	ENDIF 
	IF tw7_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates La_B0mba 
	ENDIF 
	IF tw7_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates L3337_0g 
	ENDIF 
	IF tw7_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates budd4h_X 
	ENDIF 
	IF tw7_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates t3h_buck 
	ENDIF 
	IF tw7_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates CHUNKY_1 
	ENDIF 
	IF tw7_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates ev1l_bnz 
	ENDIF 
	IF tw7_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates S4ND_M4N 
	ENDIF 
	IF tw7_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates RKK_DBP1 
	ENDIF 
	IF tw7_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates RE1_K0KU 
	ENDIF 
	IF tw7_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates s3xy_jud 
	ENDIF 
	IF tw7_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates sunra_93 
	ENDIF 
	IF tw7_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates UG_FuX69 
	ENDIF 
	IF tw7_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates Li0n_Cum 
	ENDIF 
	IF tw7_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates rkk_pwnd 
	ENDIF 
	IF tw7_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates haze_b0b 
	ENDIF 
	IF tw7_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates t3h_fluf 
	ENDIF 
	IF tw7_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates BM_4NDY_ 
	ENDIF 
	IF tw7_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates BM_D34N_ 
	ENDIF 
	IF tw7_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates BM_L4C3Y 
	ENDIF 
	IF tw7_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates BM_D3V__ 
	ENDIF 
	IF tw7_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates NU_SK00L 
	ENDIF 
	IF tw7_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates G4L_AVET 
	ENDIF 
	IF tw7_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR tw7_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_overall_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_goals = 1
	IF IS_CHAR_SITTING_IN_CAR scplayer tw7_smokes_car
		IF tw7_speech_goals = 1 //initial driving dialogue
		OR tw7_speech_goals = 2 //dialogue on the way to the ass bandits house
			IF tw7_speech_control_flag < tw7_last_label
				GOSUB tw7_loading_dialogue
				GOSUB tw7_playing_dialogue
				IF tw7_control_flag = 0 
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ENDIF
				IF tw7_control_flag = 1
				OR tw7_control_flag = 2
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
					OR NOT IS_CHAR_DEAD mc_strap
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ENDIF
					
			ELSE
				tw7_speech_goals = 0
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_IN_CAR scplayer tw7_smokes_car 
		IF tw7_speech_goals < 11 
			IF tw7_speech_control_flag < tw7_last_label 
				tw7_speech_control_flag ++ 
			ENDIF
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS 
			CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
			tw7_storing_speech_goals_number = tw7_speech_goals
			tw7_storing_speech_control_number = tw7_speech_control_flag
			tw7_speech_goals = 11
			GENERATE_RANDOM_INT_IN_RANGE 0 5 tw7_speech_control_flag
			tw7_random_last_label = tw7_speech_control_flag + 1 
			GOSUB tw7_dialogue_setup
		ENDIF
	ENDIF	

	IF tw7_speech_goals = 11 //carl is out of car
		IF NOT IS_CHAR_IN_CAR scplayer tw7_smokes_car
			IF tw7_speech_control_flag < tw7_last_label
				GOSUB tw7_loading_dialogue
				GOSUB tw7_playing_dialogue
				IF tw7_control_flag = 0 
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ENDIF
				IF tw7_control_flag = 1
					IF NOT IS_CHAR_DEAD big_smoke
					OR NOT IS_CHAR_DEAD sweet
					OR NOT IS_CHAR_DEAD mc_strap
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ENDIF
			ELSE
				PRINT_NOW ( SMK1_01 ) 11000 1 //Get back in Smoke's car.
				tw7_speech_goals = 12
			ENDIF
		ENDIF
		IF IS_CHAR_SITTING_IN_CAR scplayer tw7_smokes_car
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
			tw7_speech_goals = 13
			tw7_speech_control_flag = 0
			CLEAR_PRINTS 
			//GOSUB tw7_dialogue_setup
		ENDIF
	ENDIF

	IF tw7_speech_goals = 12 //carl has been out of car and has returned
		IF IS_CHAR_SITTING_IN_CAR scplayer tw7_smokes_car 
			tw7_speech_goals = 13
			tw7_speech_control_flag = 0
			CLEAR_PRINTS 
			//GOSUB tw7_dialogue_setup
		ENDIF
	ENDIF

	IF tw7_speech_goals = 13 //where player has returned to the car
		IF IS_CHAR_SITTING_IN_CAR scplayer tw7_smokes_car 	
			timerb = 0 
			tw7_speech_goals = tw7_storing_speech_goals_number
			tw7_speech_control_flag = tw7_storing_speech_control_number
			GOSUB tw7_dialogue_setup
			IF tw7_storing_speech_goals_number = 0
				IF tw7_control_flag = 0 
					PRINT_NOW ( SMK1_02 ) 7000 1 //Go and pick up OG Loc from the Police Station.
				ELSE
					PRINT_NOW ( SMK1_10 ) 7000 1 //Drive to the Ass bandits' house. 
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_IN_CAR scplayer tw7_smokes_car
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
			tw7_speech_goals = 11
			GENERATE_RANDOM_INT_IN_RANGE 0 5 tw7_speech_control_flag			
			tw7_random_last_label = tw7_speech_control_flag + 1 
			GOSUB tw7_dialogue_setup
		ENDIF
	ENDIF

ENDIF

IF tw7_speech_goals = 3 //cutscene in car about to get out and ring doorbell 
OR tw7_speech_goals = 4 //cutscene at the house 
OR tw7_speech_goals = 5 //first convo as player and mcstrap get on the bike
OR tw7_speech_goals = 8 //cutscene after killing ass bandit 
OR tw7_speech_goals = 10 //cutscene at burger shot 
	IF tw7_speech_control_flag < tw7_last_label
		GOSUB tw7_loading_dialogue
		GOSUB tw7_playing_dialogue
		GOSUB tw7_finishing_dialogue  
	ELSE
		tw7_speech_goals = 0
	ENDIF
ENDIF

IF tw7_goals > 3
	IF tw7_goals < 14
	OR tw7_goals = 16 
		IF IS_GROUP_MEMBER mc_strap Players_Group 
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D mc_strap scplayer 10.0 10.0 8.0 FALSE
	            IF IS_CHAR_ON_FOOT scplayer
    	        AND IS_CHAR_ON_FOOT mc_strap
					IF tw7_speech_goals = 9 //strap talking to player about where to go
						IF tw7_speech_control_flag < tw7_last_label
							GOSUB tw7_loading_dialogue
							GOSUB tw7_playing_dialogue
							IF NOT IS_CHAR_DEAD mc_strap
								GOSUB tw7_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
								tw7_slot1 = 0
								tw7_slot2 = 0
							ENDIF
						ELSE
							tw7_speech_goals = 0
						ENDIF
					ENDIF
	            ENDIF   
                IF IS_CHAR_IN_ANY_CAR scplayer
                AND IS_CHAR_IN_ANY_CAR mc_strap
					IF tw7_speech_goals = 9 //strap talking to player about where to go
						IF tw7_speech_control_flag < tw7_last_label
							GOSUB tw7_loading_dialogue
							GOSUB tw7_playing_dialogue
							IF NOT IS_CHAR_DEAD mc_strap
								GOSUB tw7_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
								tw7_slot1 = 0
								tw7_slot2 = 0
							ENDIF
						ELSE
							tw7_speech_goals = 0
						ENDIF
					ENDIF
                ENDIF
			ENDIF
		
			/*
			IF tw7_speech_goals = 9 //strap talking to player about where to go
				IF tw7_speech_control_flag < tw7_last_label
					GOSUB tw7_loading_dialogue
					GOSUB tw7_playing_dialogue
					IF NOT IS_CHAR_DEAD mc_strap
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ELSE
					tw7_speech_goals = 0
				ENDIF
			ENDIF
			*/
		ELSE		  
			IF tw7_speech_goals < 14 
				IF tw7_speech_control_flag < tw7_last_label 
					tw7_speech_control_flag ++
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
				CLEAR_PRINTS
				tw7_storing_speech_goals_number = tw7_speech_goals 
				tw7_storing_speech_control_number = tw7_speech_control_flag
				tw7_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 3 tw7_speech_control_flag
				tw7_random_last_label = tw7_speech_control_flag + 1 
				GOSUB tw7_dialogue_setup
			ENDIF
		ENDIF


		IF tw7_speech_goals = 14 //mc_strap is out of the group
			IF NOT IS_GROUP_MEMBER mc_strap Players_Group
				IF tw7_speech_control_flag < tw7_last_label
					GOSUB tw7_loading_dialogue
					GOSUB tw7_playing_dialogue
					IF NOT IS_CHAR_DEAD mc_strap
						GOSUB tw7_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
						tw7_slot1 = 0
						tw7_slot2 = 0
					ENDIF
				ELSE
					PRINT_NOW ( SMK1_09 ) 7000 1 //You have left OG Loc behind.
					tw7_speech_goals = 15
				ENDIF
			ELSE
				PRINT_NOW ( SMK1_09 ) 7000 1 //You have left OG Loc behind.
				tw7_speech_goals = 15
			ENDIF
		ENDIF

		IF tw7_speech_goals = 15 //mc_strap has been out of the group and has returned
			IF IS_GROUP_MEMBER mc_strap Players_Group 
				tw7_speech_goals = 16
				tw7_speech_control_flag = 0
				CLEAR_PRINTS
				//GOSUB tw7_dialogue_setup
			ENDIF
		ENDIF

		IF tw7_speech_goals = 16 //mc_strap is back in group
			IF IS_GROUP_MEMBER mc_strap Players_Group 	
				timerb = 0
				tw7_speech_goals = tw7_storing_speech_goals_number
				tw7_speech_control_flag = tw7_storing_speech_control_number
				GOSUB tw7_dialogue_setup
				IF tw7_storing_speech_goals_number = 0
					IF tw7_goals < 14
						IF IS_CHAR_IN_CAR scplayer tw7_players_bike 
							PRINT_NOW ( SMK1_04 ) 7000 1 //Catch and kill the Ass Bandit!
						ELSE
							PRINT_NOW ( SMK1_11 ) 7000 1 //Get back on the bike and continue with the mission.
						ENDIF
					ELSE
						PRINT_NOW ( SMK1_13 ) 8500 1 //Take OG Loc to the Burger Shot.	
					ENDIF
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
				tw7_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 3 tw7_speech_control_flag
				tw7_random_last_label = tw7_speech_control_flag + 1 
				GOSUB tw7_dialogue_setup
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF tw7_speech_goals = 6 //freddy giving mc_strap hassle  
OR tw7_speech_goals = 7 //freddy giving player hassle before freddy is killed 
	IF tw7_speech_control_flag < tw7_last_label
		GOSUB tw7_loading_dialogue
		GOSUB tw7_playing_dialogue
		IF NOT IS_CHAR_DEAD tw7_ass_bandit
			GOSUB tw7_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
			tw7_slot1 = 0
			tw7_slot2 = 0
		ENDIF
	ELSE
		tw7_speech_goals = 0
	ENDIF
ENDIF

IF tw7_speech_goals = 17 //I'm gonna kill that cheeky motherfucker!  
	IF tw7_speech_control_flag < tw7_last_label
		GOSUB tw7_loading_dialogue
		GOSUB tw7_playing_dialogue
		IF NOT IS_CHAR_DEAD tw7_ass_bandit
		AND NOT IS_CHAR_DEAD mc_strap 
			GOSUB tw7_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag] 
			tw7_slot1 = 0
			tw7_slot2 = 0
		ENDIF
	ELSE
		tw7_speech_goals = 0
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_dialogue_setup://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_speech_goals = 1
	$tw7_print_label[0] = &SMO1_AA // Good to hang with you, brother. I'm sorry I can get a little tense.
	$tw7_print_label[1] = &SMO1_AC // Don't worry about it.
	$tw7_print_label[2] = &SMO1_AD // And it ain't Jeffery no more. It's OG Loc
	$tw7_print_label[3] = &SMO1_AE // OG Loc?
	$tw7_print_label[4] = &SMO1_AF // Yeah. Real gangster.
	$tw7_print_label[5] = &SMO1_AG // I see. And what he do?
	$tw7_print_label[6] = &SMO1_AH // Anything he could to get thrown in jail. For his career.
	$tw7_print_label[7] = &SMO1_AJ // Joyriding or parking fines or some bullshit.
	
	tw7_audio_label[0] = SOUND_SMO1_AA 
	tw7_audio_label[1] = SOUND_SMO1_AC 
	tw7_audio_label[2] = SOUND_SMO1_AD 
	tw7_audio_label[3] = SOUND_SMO1_AE 
	tw7_audio_label[4] = SOUND_SMO1_AF 
	tw7_audio_label[5] = SOUND_SMO1_AG 
	tw7_audio_label[6] = SOUND_SMO1_AH 
	tw7_audio_label[7] = SOUND_SMO1_AJ 
	tw7_last_label = 8 
ENDIF
IF tw7_speech_goals = 2
	$tw7_print_label[0] = &SMO1_BA // What are your plans, homie, now you're a free man.
	$tw7_print_label[1] = &SMO1_BB // I ain't free, my parole officer lined me up with a job!
	$tw7_print_label[2] = &SMO1_BC // Motherfucker! Always keeping a guy down.
	$tw7_print_label[3] = &SMO1_BD // You got that right!
	$tw7_print_label[4] = &SMO1_BE // Still, ain't so bad, I'm gonna be a 'hygiene technician'.
	$tw7_print_label[5] = &SMO1_BF // Going up in the world.
	$tw7_print_label[6] = &SMO1_BG // Just a stepping-stone to greatness.

	tw7_audio_label[0] = SOUND_SMO1_BA 
	tw7_audio_label[1] = SOUND_SMO1_BB 
	tw7_audio_label[2] = SOUND_SMO1_BC 
	tw7_audio_label[3] = SOUND_SMO1_BD 
	tw7_audio_label[4] = SOUND_SMO1_BE 
	tw7_audio_label[5] = SOUND_SMO1_BF 
	tw7_audio_label[6] = SOUND_SMO1_BG 
	tw7_last_label = 7
ENDIF
IF tw7_speech_goals = 3
	$tw7_print_label[0] = &SMO1_CA // This is the place
	$tw7_print_label[1] = &SMO1_CB // Ain't this a Vagos' 'hood?
	$tw7_print_label[2] = &SMO1_CC // Don't give a shit.
	$tw7_print_label[3] = &SMO1_CD // C'mon, let's leave Loc to deal with Casanova.
	$tw7_print_label[4] = &SMO1_CE // I'll stick with Jeff- Loc!
	$tw7_print_label[5] = &SMO1_CF // Ok. Watch yourselves.
	$tw7_print_label[6] = &SMO1_CG // See you back at the 'hood.

	tw7_audio_label[0] = SOUND_SMO1_CA
	tw7_audio_label[1] = SOUND_SMO1_CB
	tw7_audio_label[2] = SOUND_SMO1_CC
	tw7_audio_label[3] = SOUND_SMO1_CD
	tw7_audio_label[4] = SOUND_SMO1_CE
	tw7_audio_label[5] = SOUND_SMO1_CF
	tw7_audio_label[6] = SOUND_SMO1_CG
	tw7_last_label = 7
ENDIF

IF tw7_speech_goals = 4
	$tw7_print_label[0] = &SMO1_CH // Freddy! I've come for you, motherfucker!
	$tw7_print_label[1] = &SMO1_DB // Hey, Loc, wait a second!
	$tw7_print_label[2] = &SMO1_CJ // Jeffery, you got the wrong idea, man - that was just a prison thing!
	$tw7_print_label[3] = &SMO1_CK // I got me plenty of muchachas on the outside, 
	$tw7_print_label[4] = &SMO1_CL // don't need your scrawny ass!

	$tw7_print_label[5] = &SMO1_CM // Ignore him, CJ, I don't know what he's talking about!
	$tw7_print_label[6] = &SMO1_CN // GIMME BACK MY RHYMES!
	$tw7_print_label[7] = &SMO1_CO // You dropped the soap, sugar, 
	$tw7_print_label[8] = &SMO1_CP // I don't know nothing about any rhymes!

	$tw7_print_label[9] = &SMO1_DA // Motherfucker's making a break for it!
	//$tw7_print_label[10] = &SMO1_DC // Ah shit, he'll get caught violating his parole!
						  
	tw7_audio_label[0] = SOUND_SMO1_CH
	tw7_audio_label[1] = SOUND_SMO1_DB
	tw7_audio_label[2] = SOUND_SMO1_CJ
	tw7_audio_label[3] = SOUND_SMO1_CK
	tw7_audio_label[4] = SOUND_SMO1_CL
	tw7_audio_label[5] = SOUND_SMO1_CM
	tw7_audio_label[6] = SOUND_SMO1_CN
	tw7_audio_label[7] = SOUND_SMO1_CO
	tw7_audio_label[8] = SOUND_SMO1_CP
	tw7_audio_label[9] = SOUND_SMO1_DA
	//tw7_audio_label[10] = SOUND_SMO1_DC
	tw7_last_label = tw7_random_last_label
ENDIF
IF tw7_speech_goals = 5
	$tw7_print_label[0] = &SMO1_EA // Hey Loc, you gone crazy?!							   
	$tw7_print_label[1] = &SMO1_EB // Back off, CJ, I gotta protect my rep!	
	
	tw7_audio_label[0] = SOUND_SMO1_EA
	tw7_audio_label[1] = SOUND_SMO1_EB
	tw7_last_label = 2
ENDIF
IF tw7_speech_goals = 6
	$tw7_print_label[0] = &SMO1_FA // Oooo! Chase me! Chase me!
	$tw7_print_label[1] = &SMO1_FB // C'mon, honey, I'm losing my patience!
	$tw7_print_label[2] = &SMO1_FC // I like a fast ass, not a slow ass!
	$tw7_print_label[3] = &SMO1_FD // I thought you were keen, cute buns!
	$tw7_print_label[4] = &SMO1_FE // Cath me if you caaAAAaan!
	$tw7_print_label[5] = &SMO1_FF // Yoohoo! Over here!
	$tw7_print_label[6] = &SMO1_FG // Cooweee, Jeffery!
	$tw7_print_label[7] = &SMO1_FH // I do love the thrill of the chase!
	$tw7_print_label[8] = &SMO1_FJ // Move those sweaty cheeks!
	$tw7_print_label[9] = &SMO1_FK // Try to show a little more enthusiasm, darling.
	//$tw7_print_label[10] = &SMO1_FL // Honey buns, I'm over here!						   ///debug - dialogue not in yet
	//$tw7_print_label[11] = &SMO1_FM // The anticipation is unbearable!					   ///debug - dialogue not in yet

	tw7_audio_label[0] = SOUND_SMO1_FA
	tw7_audio_label[1] = SOUND_SMO1_FB
	tw7_audio_label[2] = SOUND_SMO1_FC
	tw7_audio_label[3] = SOUND_SMO1_FD
	tw7_audio_label[4] = SOUND_SMO1_FE
	tw7_audio_label[5] = SOUND_SMO1_FF
	tw7_audio_label[6] = SOUND_SMO1_FG
	tw7_audio_label[7] = SOUND_SMO1_FH
	tw7_audio_label[8] = SOUND_SMO1_FJ
	tw7_audio_label[9] = SOUND_SMO1_FK
	//tw7_audio_label[10] = SOUND_SMO1_FL
	//tw7_audio_label[11] = SOUND_SMO1_FM
	tw7_last_label = tw7_random_last_label
ENDIF
IF tw7_speech_goals = 7
	$tw7_print_label[0] = &SMO1_GA // He's broken my heart!
	$tw7_print_label[1] = &SMO1_GB // Get him, boys!

	tw7_audio_label[0] = SOUND_SMO1_GA
	tw7_audio_label[1] = SOUND_SMO1_GB
	tw7_last_label = 2
ENDIF
IF tw7_speech_goals = 8
	$tw7_print_label[0] = &SMO1_HA // Don't say a damn thing.
	$tw7_print_label[1] = &SMO1_HB // Was you lonely, Loc?
	$tw7_print_label[2] = &SMO1_HC // Hey, I like a nice moustache myself!

	$tw7_print_label[3] = &SMO1_HD // I kept it real, unlike you fake ass motherfucker.
	
	$tw7_print_label[4] = &SMO1_HE // C'mon let's get back to the Grove.
	
	$tw7_print_label[5] = &SMO1_HF // Nah, I gotta go and sign in for this damn job.
	
	$tw7_print_label[6] = &SMO1_HG // Whatever, you want a ride anyway?
	
	$tw7_print_label[7] = &SMO1_HH // Sure thing. Let's roll.
						  
	tw7_audio_label[0] = SOUND_SMO1_HA
	tw7_audio_label[1] = SOUND_SMO1_HB
	tw7_audio_label[2] = SOUND_SMO1_HC
	tw7_audio_label[3] = SOUND_SMO1_HD
	tw7_audio_label[4] = SOUND_SMO1_HE
	tw7_audio_label[5] = SOUND_SMO1_HF
	tw7_audio_label[6] = SOUND_SMO1_HG
	tw7_audio_label[7] = SOUND_SMO1_HH
	tw7_last_label = 8
ENDIF
IF tw7_speech_goals = 9
	$tw7_print_label[0] = &SMO1_JA // It's the Burger Shot, Verona Beach. 
	$tw7_print_label[1] = &SMO1_JB // You're the boss
	$tw7_print_label[2] = &SMO1_JC // Coz I'm keen With the hygiene,
	$tw7_print_label[3] = &SMO1_JD // On a mission, Like a super technician.
	$tw7_print_label[4] = &SMO1_JG // Get on with that shit man. 
	$tw7_print_label[5] = &SMO1_JM // Look at you, all muscley and shit.
	$tw7_print_label[6] = &SMO1_JH // 17s, on the hang
	$tw7_print_label[7] = &SMO1_JJ // You coulda did that out here.
	$tw7_print_label[8] = &SMO1_JK // Because I did it in the joint, its real now.
	$tw7_print_label[9] = &SMO1_JL // Yeah, I guess it is, homie.

	tw7_audio_label[0] = SOUND_SMO1_JA
	tw7_audio_label[1] = SOUND_SMO1_JB
	tw7_audio_label[2] = SOUND_SMO1_JC
	tw7_audio_label[3] = SOUND_SMO1_JD
	tw7_audio_label[4] = SOUND_SMO1_JG
	tw7_audio_label[5] = SOUND_SMO1_JM
	tw7_audio_label[6] = SOUND_SMO1_JH
	tw7_audio_label[7] = SOUND_SMO1_JJ
	tw7_audio_label[8] = SOUND_SMO1_JK
	tw7_audio_label[9] = SOUND_SMO1_JL
	tw7_last_label = 10
ENDIF
IF tw7_speech_goals = 10
	$tw7_print_label[0] = &SMO1_KA // Thanks for the ride, CJ.
	$tw7_print_label[1] = &SMO1_KB // Don't be a stranger.
	$tw7_print_label[2] = &SMO1_KC // Sure thing, I'll see you around
	$tw7_print_label[3] = &SMO1_KD // Like a quarter pound!
	$tw7_print_label[4] = &SMO1_KE // Later.
	
	tw7_audio_label[0] = SOUND_SMO1_KA
	tw7_audio_label[1] = SOUND_SMO1_KB
	tw7_audio_label[2] = SOUND_SMO1_KC
	tw7_audio_label[3] = SOUND_SMO1_KD
	tw7_audio_label[4] = SOUND_SMO1_KE
	tw7_last_label = 5
ENDIF

IF tw7_speech_goals = 11
	$tw7_print_label[0] = &SMOX_AA // Get in
	$tw7_print_label[1] = &SMOX_AB // In the ride!
	$tw7_print_label[2] = &SMOX_AC // Get in the car!
	$tw7_print_label[3] = &SMOX_AD // Come on, dude, get in!
	$tw7_print_label[4] = &SMOX_AE // Come on, wise man, get in the car!
	$tw7_print_label[5] = &SMOX_AF // Get in the mother fucking car!

	tw7_audio_label[0] = SOUND_SMOX_AA
	tw7_audio_label[1] = SOUND_SMOX_AB
	tw7_audio_label[2] = SOUND_SMOX_AC
	tw7_audio_label[3] = SOUND_SMOX_AD
	tw7_audio_label[4] = SOUND_SMOX_AE
	tw7_audio_label[5] = SOUND_SMOX_AF
 	tw7_last_label = tw7_random_last_label 
ENDIF

IF tw7_speech_goals = 14
	$tw7_print_label[0] = &LOCX_AA // Hold up, CJ!
	$tw7_print_label[1] = &LOCX_AB // Hey, wait up, dog!
	$tw7_print_label[2] = &LOCX_AC // Hold it, CJ!

	tw7_audio_label[0] = SOUND_LOCX_AA
	tw7_audio_label[1] = SOUND_LOCX_AB
	tw7_audio_label[2] = SOUND_LOCX_AC
 	tw7_last_label = tw7_random_last_label 
ENDIF

IF tw7_speech_goals = 17   
	$tw7_print_label[0] = SMO1_GC // I'm gonna kill that cheeky motherfucker!
	$tw7_print_label[1] = SMO1_GD // Your ass is mine!
	$tw7_print_label[2] = SMO1_GE // No, I didn't mean it like that!
	$tw7_print_label[3] = SMO1_GF // CJ, help me out here!
	
	tw7_audio_label[0] = SOUND_SMO1_GC
	tw7_audio_label[1] = SOUND_SMO1_GD
	tw7_audio_label[2] = SOUND_SMO1_GE
	tw7_audio_label[3] = SOUND_SMO1_GF
	tw7_last_label = 4 
ENDIF

tw7_slot_load = tw7_speech_control_flag
tw7_slot1 = 0
tw7_slot2 = 0
tw7_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_loading_dialogue:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF tw7_slot_load < tw7_last_label 
	//slot 1
	IF tw7_slot1 = 0
		LOAD_MISSION_AUDIO 1 tw7_audio_label[tw7_slot_load]  
		tw7_slot_load ++ 
		tw7_slot1 = 1
	ENDIF

	//slot 2		    
	IF tw7_slot2 = 0
		LOAD_MISSION_AUDIO 2 tw7_audio_label[tw7_slot_load]  
		tw7_slot_load ++ 
		tw7_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF tw7_play_which_slot = 1 
	IF tw7_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $tw7_print_label[tw7_speech_control_flag] ) 4500 1 
			tw7_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF tw7_play_which_slot = 2 
	IF tw7_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $tw7_print_label[tw7_speech_control_flag] ) 4500 1 
			tw7_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
tw7_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF tw7_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
		tw7_speech_control_flag ++		
		tw7_play_which_slot = 2
		tw7_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF tw7_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $tw7_print_label[tw7_speech_control_flag]
		tw7_speech_control_flag ++		
		tw7_play_which_slot = 1
		tw7_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}












/*
REQUEST_MODEL SENTINEL
REQUEST_MODEL PCJ600
REQUEST_CAR_RECORDING 60
LOAD_ALL_MODELS_NOW 
WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 60
	WAIT 0
ENDWHILE 

LOAD_SCENE 1942.5 -1016.2 31.8 
CREATE_CAR PCJ600 1942.5 -1016.2 31.8 tw7_ass_bandit_bike
//SET_CAR_ONLY_DAMAGED_BY_PLAYER tw7_ass_bandit_bike TRUE 
//SET_CAR_DRIVING_STYLE tw7_ass_bandit_bike DRIVINGMODE_AVOIDCARS
SET_CAR_HEADING tw7_ass_bandit_bike 87.4
WARP_CHAR_INTO_CAR scplayer tw7_ass_bandit_bike 
DO_FADE 0 FADE_IN


CREATE_CAR SENTINEL 1688.5 -1111.7 58.5 tw7_car[0]	   //front car 	- car rec 50
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[0] tw7_char[0]
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[0] TRUE
SET_CAR_HEADING tw7_car[0] 322.3 								   

CREATE_CAR SENTINEL 1679.7 -1131.6 59.7 tw7_car[1]	   //second car - car rec 51 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[1] tw7_char[1] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[1] TRUE
SET_CAR_HEADING tw7_car[1] 321.2 								   

CREATE_CAR PCJ600 1644.8 -1177.5 54.3 tw7_car[2]	   //third car - first bike - car rec 52 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[2] tw7_char[2] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[2] TRUE
SET_CAR_HEADING tw7_car[2] 320.8 								   

CREATE_CAR SENTINEL 1637.0 -1203.8 51.5 tw7_car[3]	   //fourth car - car rec 53 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[3] tw7_char[3] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[3] TRUE
SET_CAR_HEADING tw7_car[3] 350.3 								   

CREATE_CAR SENTINEL 1618.2 -1306.4 35.6 tw7_car[4]	   //first car left on dual carriageway - car rec 54 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[4] tw7_char[4] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[4] TRUE
SET_CAR_HEADING tw7_car[4] 343.7 								   

CREATE_CAR SENTINEL 1621.8 -1317.9 33.0 tw7_car[5]	   //second car left on dual carriageway - car rec 55 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[5] tw7_char[5] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[5] TRUE
SET_CAR_HEADING tw7_car[5] 348.7 								   

CREATE_CAR SENTINEL 1615.6 -1327.7 31.5 tw7_car[6]	   //second car right on dual carriageway - car rec 56 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[6] tw7_char[6] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[6] TRUE
SET_CAR_HEADING tw7_car[6] 352.1 								   

CREATE_CAR SENTINEL 1612.3 -1394.0 27.6 tw7_car[7]	   //third car right on dual carriageway - car rec 57  
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[7] tw7_char[7] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[7] TRUE
SET_CAR_HEADING tw7_car[7] 353.6 								   

CREATE_CAR SENTINEL 1612.3 -1462.2 27.1 tw7_car[8]	   //fourth car right on dual carriageway - car rec 58 
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[8] tw7_char[8] 
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[8] TRUE
SET_CAR_HEADING tw7_car[8] 357.2 								   


CREATE_CAR SENTINEL 1663.4 -1457.0 24.3 tw7_car[9]	   //car that crashes into tanker - car rec 60 					  
CREATE_RANDOM_CHAR_AS_DRIVER tw7_car[9] tw7_char[9] 																  
SET_LOAD_COLLISION_FOR_CHAR_FLAG tw7_char[9] TRUE																	  
SET_CAR_HEADING tw7_car[9] 51.9 								   



blergh:
WAIT 0

	IF tw7_control_flag = 0
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL 	
			WHILE IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
				WAIT 0
			ENDWHILE 
		
			IF NOT IS_CHAR_DEAD tw7_char[0] 
				IF NOT IS_CAR_DEAD tw7_car[0] 
					SET_CAR_STAY_IN_SLOW_LANE tw7_car[0] TRUE 
					TASK_CAR_DRIVE_TO_COORD tw7_char[0] tw7_car[0] 1852.2 -1028.5 34.8 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[0] 50 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[1] 
				IF NOT IS_CAR_DEAD tw7_car[1] 
					TASK_CAR_DRIVE_TO_COORD tw7_char[1] tw7_car[1] 1852.3 -1022.9 34.7 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					SET_CAR_STAY_IN_SLOW_LANE tw7_car[1] TRUE 
					START_RECORDING_CAR tw7_car[1] 51 
				ENDIF
			ENDIF


			IF NOT IS_CHAR_DEAD tw7_char[2] 
				IF NOT IS_CAR_DEAD tw7_car[2] 
					TASK_CAR_DRIVE_TO_COORD tw7_char[2] tw7_car[2] 1833.8 -1031.3 35.3 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[2] 52 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[3] 
				IF NOT IS_CAR_DEAD tw7_car[3] 
					TASK_CAR_DRIVE_TO_COORD tw7_char[3] tw7_car[3] 1837.4 -1025.9 35.0 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[3] 53 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[4] 
				IF NOT IS_CAR_DEAD tw7_car[4] 
					SET_CAR_FORWARD_SPEED tw7_car[4] 15.0 
					TASK_CAR_DRIVE_TO_COORD tw7_char[4] tw7_car[4] 1826.7 -1032.7 35.7 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[4] 54 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[5] 
				IF NOT IS_CAR_DEAD tw7_car[5] 
					//SET_CAR_FORWARD_SPEED tw7_car[5] 15.0 
					TASK_CAR_DRIVE_TO_COORD tw7_char[5] tw7_car[5] 1642.1 -1131.3 57.3 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[5] 55 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[6] 
				IF NOT IS_CAR_DEAD tw7_car[6] 
					//SET_CAR_FORWARD_SPEED tw7_car[6] 15.0 
					TASK_CAR_DRIVE_TO_COORD tw7_char[6] tw7_car[6] 1806.4 -1025.9 35.0 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[6] 56 
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD tw7_char[7] 
				IF NOT IS_CAR_DEAD tw7_car[7] 
					TASK_CAR_DRIVE_TO_COORD tw7_char[7] tw7_car[7] 1642.2 -1160.7 54.9 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[7] 57 
				ENDIF
			ENDIF


			IF NOT IS_CHAR_DEAD tw7_char[8] 
				IF NOT IS_CAR_DEAD tw7_car[8] 
					TASK_CAR_DRIVE_TO_COORD tw7_char[8] tw7_car[8] 1647.2 -1160.7 54.9 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS  
					START_RECORDING_CAR tw7_car[8] 58 
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD tw7_car[9] 
				START_PLAYBACK_RECORDED_CAR tw7_car[9] 60
			ENDIF

			
			tw7_control_flag = 1
		ENDIF
	ENDIF

	IF tw7_control_flag = 1
		IF NOT IS_CAR_DEAD tw7_car[0]
			IF IS_RECORDING_GOING_ON_FOR_CAR tw7_car[0] 
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL 	
					WHILE IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
						WAIT 0
					ENDWHILE 
					tw7_control_flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO blergh


*/