MISSION_START
// *****************************************************************************************
// ***************************** Toreno 2 ************************************************** 
// *****************************************************************************************
// *****************************************************************************************
// **************************FAST AND THE FURIOUS*******************************************
// *****************************************************************************************
SCRIPT_NAME toreno2
// Mission start stuff
GOSUB mission_start_toreno2
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_toreno2_failed
ENDIF
GOSUB mission_cleanup_toreno2
MISSION_END
{
// Variables for mission

//people
LVAR_INT des2_transporter des2_transporter_driver des2_car des2_tanker 
//LVAR_INT des2_checkpoint
LVAR_INT des2_random_car des2_cesars_car

//blips
LVAR_INT des2_transporter_blip

//flags
LVAR_INT des2_goals des2_control_flag des2_skip_cutscene_flag des2_deathcheck_flag  
LVAR_INT des2_car_check_flag des2_flag_cesar_in_group 
LVAR_INT pos1_flag pos2_flag pos3_flag pos4_flag des2_anim_been_played  
LVAR_FLOAT des2_anim_time 
LVAR_FLOAT des2_truck_heading
LVAR_INT des2_truck_control_flag
LVAR_INT des2_cab_attached
LVAR_INT des2_opening_door_flag
LVAR_INT des2_health

LVAR_INT game_timer_diff game_timer_end game_timer_start game_timer_flag


LVAR_FLOAT fForceX fForceY fForceZ
LVAR_FLOAT fOffsetX fOffsetY fOffsetZ
LVAR_FLOAT fDotProd fTransSpeed
LVAR_FLOAT temp1 temp2 temp3 



//speech
LVAR_INT des2_speech_goals des2_speech_control_flag des2_speech_flag 
LVAR_TEXT_LABEL des2_print_label[8] 
LVAR_INT des2_audio_label[8] 
LVAR_INT des2_last_label    
LVAR_INT des2_slot1 des2_slot2 des2_slot_load des2_play_which_slot
LVAR_INT des2_speech_location_flag[3] 
LVAR_INT des2_random_last_label
LVAR_INT des2_storing_speech_goals_number des2_storing_speech_control_number 


//coords

LVAR_FLOAT in_pos1x in_pos1y in_pos1z in_pos2x in_pos2y in_pos2z

LVAR_FLOAT des2_x des2_y des2_z 
LVAR_FLOAT infront1x infront1y infront2x infront2y 

LVAR_FLOAT des2_open_door_float


//sequences/decision makers/threat lists/attractors/groups
LVAR_INT des2_seq des2_empty_decision_maker

// ****************************************Mission Start************************************
mission_start_toreno2:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT TORENO2
CLEAR_PRINTS 
WAIT 0
// *************************************Set Flags/variables*********************************
des2_goals = 0
des2_control_flag = 0
des2_skip_cutscene_flag = 0 
des2_deathcheck_flag = 0 
des2_speech_flag = 0
game_timer_diff = 0 
game_timer_end = 0 
game_timer_start = 0
game_timer_flag = 0
in_pos1x = 0.0 
in_pos1y = 0.0 
in_pos1z = 0.0
in_pos2x = 0.0 
in_pos2y = 0.0	
in_pos2z = 0.0
des2_car_check_flag = 0
des2_flag_cesar_in_group = 0
pos1_flag = 0 
pos2_flag = 0 
pos3_flag = 0 
pos4_flag = 0 
des2_anim_been_played = 0
des2_anim_time = 0.0
des2_truck_heading = 0.0
des2_truck_control_flag = 0
des2_x = 0.0 
des2_y = 0.0
des2_z = 0.0
infront1x = 0.0 
infront1y = 0.0 
infront2x = 0.0 
infront2y = 0.0 
des2_cab_attached = 0
des2_opening_door_flag = 0
des2_open_door_float = 0.0

des2_speech_goals = 0 
des2_speech_control_flag = 0 
des2_speech_flag = 0 
des2_last_label = 0    
des2_slot1 = 0 
des2_slot2 = 0 
des2_slot_load = 0 
des2_play_which_slot = 0
des2_speech_location_flag[0] = 0
des2_speech_location_flag[1] = 0
des2_speech_location_flag[2] = 0
des2_random_last_label = 0
des2_storing_speech_goals_number = 0
des2_storing_speech_control_number = 0

des2_health = 0

fForceX = 0.0 
fForceY = 0.0 
fForceZ = 0.0
fOffsetX = 0.0 
fOffsetY = 0.0 
fOffsetZ = 0.0
fDotProd = 0.0 
fTransSpeed = 0.0
temp1 = 0.0 
temp2 = 0.0
temp3 = 0.0

// ****************************************START OF CUTSCENE********************************
CLEAR_AREA -685.1 922.0 10.9 180.0 TRUE
CLEAR_AREA -692.2 948.0 11.2 100.0 TRUE
FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_VEGAS
MAKE_PLAYER_GANG_DISAPPEAR
LOAD_CUTSCENE DESERT2
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
RELEASE_WEATHER
// ****************************************END OF CUTSCENE**********************************
SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------

REQUEST_MODEL PETRO
REQUEST_MODEL pcj600 
REQUEST_MODEL petrotr
REQUEST_MODEL SAVANNA

REQUEST_ANIMATION BIKELEAP
LOAD_SPECIAL_CHARACTER 1 cesar
LOAD_PATH_NODES_IN_AREA -1695.1 587.6 -1771.3 -1310.0

REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

LOAD_ALL_MODELS_NOW

//starting intro scripted cut of player, smoke and sweet getting into the car
SWITCH_WIDESCREEN ON 
MAKE_PLAYER_GANG_DISAPPEAR
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

SET_FIXED_CAMERA_POSITION -701.09 917.26 12.08 0.0 0.0 0.0	
POINT_CAMERA_AT_POINT -694.0 938.7 16.69 JUMP_CUT 	

//suppress all big vehicles
SUPPRESS_CAR_MODEL linerun
//SUPPRESS_CAR_MODEL trash
SUPPRESS_CAR_MODEL stretch
SUPPRESS_CAR_MODEL pony
SUPPRESS_CAR_MODEL mule
SUPPRESS_CAR_MODEL moonbeam
SUPPRESS_CAR_MODEL mrwhoop
SUPPRESS_CAR_MODEL securica
SUPPRESS_CAR_MODEL bus
//SUPPRESS_CAR_MODEL barracks
SUPPRESS_CAR_MODEL coach
SUPPRESS_CAR_MODEL rumpo
//SUPPRESS_CAR_MODEL romero
//SUPPRESS_CAR_MODEL packer
//SUPPRESS_CAR_MODEL monster
SUPPRESS_CAR_MODEL flatbed
SUPPRESS_CAR_MODEL yankee
SUPPRESS_CAR_MODEL solair
//SUPPRESS_CAR_MODEL patriot
SUPPRESS_CAR_MODEL burrito
SUPPRESS_CAR_MODEL camper
//SUPPRESS_CAR_MODEL dozer
SUPPRESS_CAR_MODEL rancher
SUPPRESS_CAR_MODEL boxville
SUPPRESS_CAR_MODEL benson
SUPPRESS_CAR_MODEL journey
//SUPPRESS_CAR_MODEL petro
//SUPPRESS_CAR_MODEL rdtrain
SUPPRESS_CAR_MODEL cement
SUPPRESS_CAR_MODEL towtruck
SUPPRESS_CAR_MODEL cadrona
SUPPRESS_CAR_MODEL utility
//SUPPRESS_CAR_MODEL dft30
SUPPRESS_CAR_MODEL newsvan
//SUPPRESS_CAR_MODEL float1
SUPPRESS_CAR_MODEL hotdog

//suppressing bikes for the moment
SUPPRESS_CAR_MODEL pcj600
SUPPRESS_CAR_MODEL faggio
SUPPRESS_CAR_MODEL freeway
SUPPRESS_CAR_MODEL sanchez
SUPPRESS_CAR_MODEL bike
SUPPRESS_CAR_MODEL mtbike
SUPPRESS_CAR_MODEL fcr900
SUPPRESS_CAR_MODEL nrg500
//SUPPRESS_CAR_MODEL copbike
SUPPRESS_CAR_MODEL bf400
SUPPRESS_CAR_MODEL wayfarer

CLEAR_AREA -693.6 924.5 11.3 1.0 TRUE
SET_CHAR_COORDINATES scplayer -693.6 924.5 11.3
SET_CHAR_HEADING scplayer 71.2

CLEAR_AREA -685.1 922.0 10.9 180.0 TRUE 

CLEAR_AREA -692.2 948.0 11.2 100.0 TRUE 

//creating bike to take to the car transporter
CUSTOM_PLATE_FOR_NEXT_CAR pcj600 preacher
CREATE_CAR pcj600 -712.5 943.5 10.8 des2_car
SET_CAR_HEADING des2_car 32.7 

//creating cesar's car
CLEAR_AREA -709.0 952.0 11.2 5.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA &_LVA4L__
CREATE_CAR SAVANNA -709.0 952.0 11.2 des2_cesars_car
SET_CAR_HEADING des2_cesars_car 240.7 
CHANGE_CAR_COLOUR des2_cesars_car 3 3 

//Creating cesar
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 -703.7 932.4 11.4 cesar
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR cesar FALSE
SET_CHAR_HEADING cesar 214.6
SET_CHAR_NEVER_TARGETTED cesar TRUE 
SET_CHAR_ONLY_DAMAGED_BY_PLAYER cesar TRUE
SET_CHAR_CANT_BE_DRAGGED_OUT cesar TRUE
COPY_CHAR_DECISION_MAKER DM_PED_EMPTY des2_empty_decision_maker
SET_CHAR_DECISION_MAKER cesar des2_empty_decision_maker


SET_NEXT_DESIRED_MOVE_STATE	PEDMOVE_WALK
OPEN_SEQUENCE_TASK des2_seq
	TASK_GOTO_CHAR_OFFSET -1 scplayer 10000 3.0 0.0	
	TASK_PAUSE -1 5300
	TASK_GO_STRAIGHT_TO_COORD -1 -710.0 938.4 11.4 PEDMOVE_WALK -1
CLOSE_SEQUENCE_TASK des2_seq
PERFORM_SEQUENCE_TASK cesar des2_seq
CLEAR_SEQUENCE_TASK des2_seq

SET_NEXT_DESIRED_MOVE_STATE	PEDMOVE_WALK
OPEN_SEQUENCE_TASK des2_seq
	TASK_GOTO_CHAR_OFFSET -1 cesar 10000 5.0 0.0	
	TASK_PAUSE -1 5000
	TASK_GO_STRAIGHT_TO_COORD -1 -708.0 937.9 11.4 PEDMOVE_WALK -1
CLOSE_SEQUENCE_TASK des2_seq
PERFORM_SEQUENCE_TASK scplayer des2_seq
CLEAR_SEQUENCE_TASK des2_seq

SHUT_ALL_CHARS_UP TRUE

des2_skip_cutscene_flag = 1
SKIP_CUTSCENE_START

/*
/// debug to take player to last cutscene /////////////////////////////
SKIP_CUTSCENE_END
CREATE_CAR PETRO -1687.8 537.7 36.8 des2_transporter
SET_CAR_HEADING des2_transporter 137.0  
CREATE_CAR petrotr -1679.5 545.9 36.9 des2_tanker
SET_CAR_HEADING des2_tanker 137.0  
ATTACH_TRAILER_TO_CAB des2_tanker des2_transporter
des2_goals = 5
/// debug /////////////////////////////////////////////////////////////
*/

LOAD_MISSION_AUDIO 3 SOUND_TRUCK_SMASH_VEHICLE
WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
ENDWHILE

timerb = 0
DO_FADE 500 FADE_IN



mission_toreno2_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_toreno2_passed  
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB des2_death_checks
	IF des2_deathcheck_flag = 1
		GOTO mission_toreno2_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Initial Cutscene //////////////////////////////////////////////////////////////////////////////


	IF des2_goals = 0    
		IF des2_control_flag = 0
			IF timerb > 1000 
				des2_speech_goals = 1
				des2_speech_control_flag = 0
				GOSUB des2_dialogue_setup 
				des2_control_flag = 1
			ENDIF
		ENDIF
	
		IF des2_control_flag = 1
			IF des2_speech_goals = 0 
				
				des2_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB des2_death_checks
				IF des2_deathcheck_flag = 1
					GOTO mission_toreno2_failed
				ENDIF
					
				//skipping cutscene 
				IF des2_skip_cutscene_flag = 1
					CLEAR_PRINTS 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					des2_speech_goals = 0

					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS	
					   	WAIT 0
					ENDWHILE 
					GOSUB des2_death_checks
					IF des2_deathcheck_flag = 1
						GOTO mission_toreno2_failed
					ENDIF
				ENDIF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer  
				SET_CHAR_COORDINATES scplayer -710.0 938.4 11.4 
				SET_CHAR_HEADING scplayer 47.9
				
				CLEAR_CHAR_TASKS_IMMEDIATELY cesar 
				SET_CHAR_COORDINATES cesar -708.0 937.9 11.4 
				SET_CHAR_HEADING cesar 47.9
				
				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				SET_GROUP_MEMBER Players_Group cesar
				des2_flag_cesar_in_group = 1
				SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
				SET_GROUP_DEFAULT_TASK_ALLOCATOR Players_Group DEFAULT_TASK_ALLOCATOR_FOLLOW_LIMITED
				
				ADD_BLIP_FOR_CAR des2_car des2_transporter_blip
				SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
				PRINT_NOW ( TOR1_01 ) 7000 1 //You need to use that bike in order to get Cesar close enough to the truck.  
				
				SHUT_ALL_CHARS_UP FALSE
				
				SWITCH_WIDESCREEN OFF	
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT 
				SET_PLAYER_CONTROL player1 ON
				
				//skipping cutscene 
				IF des2_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS	
					   	WAIT 0
					ENDWHILE 
					GOSUB des2_death_checks
					IF des2_deathcheck_flag = 1
						GOTO mission_toreno2_failed
					ENDIF
				ENDIF
				
				des2_control_flag = 0
				des2_goals = 1 
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////waiting for player and cesar to get on bike ////////////////////////////////////////////////////


	IF des2_goals = 1
		//waiting for player to get on bike initially
		IF des2_control_flag = 0
			IF des2_flag_cesar_in_group = 1	
				IF IS_CHAR_IN_CAR scplayer des2_car
					CREATE_CAR PETRO -1687.8 537.7 36.8 des2_transporter
					SET_CAR_HEADING des2_transporter 137.0  
					CREATE_CAR petrotr -1679.5 545.9 36.9 des2_tanker
					SET_CAR_HEADING des2_tanker 137.0  
					ATTACH_TRAILER_TO_CAB des2_tanker des2_transporter
					SET_CAN_BURST_CAR_TYRES des2_transporter FALSE
					LOCK_CAR_DOORS des2_transporter CARLOCK_LOCKOUT_PLAYER_ONLY  
					CREATE_RANDOM_CHAR_AS_DRIVER des2_transporter des2_transporter_driver 
					//SET_CAR_HEAVY des2_transporter TRUE 
					SET_CAR_ONLY_DAMAGED_BY_PLAYER des2_transporter TRUE 

					//CREATE_CHECKPOINT CHECKPOINT_TORUS_UPDOWN -1687.8 537.7 38.0 -1687.8 537.7 40.8 1.0 des2_checkpoint	
							
					SET_CHAR_CAN_BE_SHOT_IN_VEHICLE des2_transporter_driver FALSE
					 
					SET_CAR_CRUISE_SPEED des2_transporter 0.0
					SET_CAR_DRIVING_STYLE des2_transporter 2 
					SET_CAR_STAY_IN_SLOW_LANE des2_transporter TRUE   

					MARK_CAR_AS_NO_LONGER_NEEDED des2_cesars_car 
					MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA 
				
					CLEAR_PRINTS
					
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE 
					
					des2_speech_goals = 2
					des2_speech_control_flag = 0
					GOSUB des2_dialogue_setup
					
					REMOVE_BLIP des2_transporter_blip
					IF NOT IS_CAR_DEAD des2_transporter 
						ADD_BLIP_FOR_CAR des2_transporter des2_transporter_blip
					ENDIF

					des2_speech_flag = 0
					des2_goals = 2
				ENDIF
			ENDIF
		ENDIF 

		GOSUB des2_cesar_group
		GOSUB des2_bike_blippage
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////waiting for player to pull alongside the truck//////////////////////////////////////////////////
/////// des2_anim_been_played = 0 - no animation
///////	des2_anim_been_played = 1 - balance anim			
///////	des2_anim_been_played = 2 - ready anim			

	IF des2_goals = 2
		
	//////////////////////////////////DEBUG////////////////////////
		IF IS_CHAR_IN_ANY_CAR scplayer 
			IF IS_CHAR_IN_ANY_CAR cesar
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
					LOAD_SCENE -1641.0 586.5 38.5 
					SET_CHAR_COORDINATES scplayer -1641.0 586.5 38.5
					SET_CAR_HEADING des2_car 136.9
				ENDIF
			ENDIF
		ENDIF
	//////////////////////////////////DEBUG////////////////////////
		
	
		//speech for this section
		IF des2_speech_flag = 0
			IF des2_speech_goals = 0 
				PRINT_NOW ( TOR1_04 ) 7000 1 //The truck is coming from Las Vegas and going to Silicon Valley.
				des2_speech_flag = 1 
			ENDIF
		ENDIF
	   
		//speech stuff to make sure player isn't going the wrong way
		IF des2_speech_flag = 1
			IF IS_CHAR_IN_CAR scplayer des2_car 
				IF IS_CHAR_IN_CAR cesar des2_car 
				
					//telling player to pull onto the freeway
					IF IS_CHAR_IN_ANGLED_AREA_3D scplayer -1042.5 1016.4 15.3 -810.5 1300.7 50.0 250.0 FALSE
						IF des2_speech_location_flag[0] = 0
							IF des2_speech_goals = 0 
								
								//We can get on the freeway here, holmes.
								des2_speech_goals = 3
								des2_speech_control_flag = 0
								GOSUB des2_dialogue_setup 
								des2_speech_location_flag[0] = 1
							ENDIF
						ENDIF
					ELSE
						des2_speech_location_flag[0] = 0	
					ENDIF

					//telling player to get off the rail bridge
					IF IS_CHAR_IN_ANGLED_AREA_3D scplayer -881.0 1029.0 50.9 -970.2 955.7 33.0 -20.0 FALSE  
						IF des2_speech_location_flag[1] = 0
							IF des2_speech_goals = 0 

								//Man, you crazy, this is the rail bridge!
								des2_speech_goals = 4
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 

								des2_speech_location_flag[1] = 1
							ENDIF
						ENDIF
					ELSE
						des2_speech_location_flag[1] = 0	
					ENDIF
	

					//telling player to go back because he went to far
					IF IS_CHAR_IN_ANGLED_AREA_3D scplayer -774.9 1445.6 10.0 -1187.0 1275.6 50.0 -250.0 FALSE  
						IF des2_speech_location_flag[2] = 0
							IF des2_speech_goals = 0 

								//You missed the turnpike, holmes!
								des2_speech_goals = 5
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 

								des2_speech_location_flag[2] = 1
							ENDIF
						ENDIF
					ELSE
						des2_speech_location_flag[2] = 0	
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//speech of cesar asking player what the plan is
		IF des2_speech_flag < 2
			IF IS_CHAR_IN_ANGLED_AREA_3D scplayer -1143.8 1041.0 50.9 -1393.7 797.3 33.0 -60.0 FALSE
				IF des2_speech_goals = 0

					//What's the plan?
					des2_speech_goals = 6
					des2_speech_control_flag = 1
					des2_random_last_label = 6
					GOSUB des2_dialogue_setup 

					des2_speech_flag = 3
				ENDIF
			ENDIF
		ENDIF

		//speech of cesar telling player truck is up ahead
		IF des2_speech_flag = 3
			IF des2_speech_goals = 0
				IF LOCATE_CHAR_IN_CAR_CAR_2D scplayer des2_transporter 180.0 180.0 FALSE
					IF IS_CHAR_IN_CAR scplayer des2_car 
						IF IS_CHAR_IN_CAR cesar des2_car
							
							//There's the rig up ahead!
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
							des2_speech_goals = 6
							des2_speech_control_flag = 0
							des2_random_last_label = 1
							GOSUB des2_dialogue_setup 
							des2_speech_flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF des2_speech_flag = 4 
			IF des2_speech_goals = 0
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				des2_speech_flag = 5
			ENDIF
		ENDIF


		///MAIN BIT FOR THIS SECTION 

	   	//waiting for the player and cesar to get close to the truck
	   	IF des2_control_flag = 0
			IF LOCATE_CHAR_IN_CAR_CAR_2D scplayer des2_transporter 180.0 180.0 FALSE 
				SET_CAR_FORWARD_SPEED des2_transporter 35.0	 
				IF NOT IS_CHAR_DEAD des2_transporter_driver 
					OPEN_SEQUENCE_TASK des2_seq
						TASK_CAR_DRIVE_TO_COORD -1 des2_transporter -1914.6 -1187.7 30.0 15.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH 	
					CLOSE_SEQUENCE_TASK des2_seq
					PERFORM_SEQUENCE_TASK des2_transporter_driver des2_seq
					CLEAR_SEQUENCE_TASK des2_seq
				ENDIF
				des2_control_flag = 1
			ELSE
				SET_CAR_HEADING des2_transporter 137.0
			ENDIF
		ENDIF
	
		IF des2_control_flag = 1
			IF LOCATE_CHAR_IN_CAR_CAR_3D scplayer des2_transporter 20.0 20.0 20.0 FALSE 
				IF IS_CHAR_IN_CAR cesar des2_car 
					PRINT_NOW ( TOR1_10 ) 11000 1 //Pull up to the left hand side of the truck and hold the bike in position until Cesar is ready to jump.				
					SET_CAR_CRUISE_SPEED des2_transporter 37.0   
					des2_control_flag = 2
				ENDIF
			ENDIF
		ENDIF
		
		IF des2_control_flag = 2
			IF IS_CHAR_IN_CAR scplayer des2_car 
				IF IS_CHAR_IN_CAR cesar des2_car 
					//correct position - flag 1 
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 4.0 10.0 in_pos1x in_pos1y in_pos1z     
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 0.0 -10.0 in_pos2x in_pos2y in_pos2z    
					
					IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer in_pos1x in_pos1y in_pos1z in_pos2x in_pos2y in_pos2z 3.0 FALSE
						IF pos1_flag = 0 
							TASK_PLAY_ANIM cesar BK_rdy_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
							des2_anim_been_played = 2

							PRINT_NOW ( HOLD_1 ) 7000 1 //Hold this position!
							
							/*
							//HOLD HER STEADY SPEECH 
							IF des2_speech_goals = 0							
								des2_speech_goals = 8
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 
							ENDIF
							*/
							pos1_flag = 1
						ENDIF

						IF pos1_flag = 1
							IF game_timer_flag = 0
								GET_GAME_TIMER game_timer_start 			
								game_timer_flag = 1
							ELSE
								GET_GAME_TIMER game_timer_end 	
								game_timer_diff = game_timer_end - game_timer_start
								IF game_timer_diff > 3500
									SET_PLAYER_CONTROL player1 OFF
									ATTACH_CAR_TO_CAR des2_car des2_transporter -2.692 2.484 -1.0 0.0 0.0 0.0 
									//DELETE_CHECKPOINT des2_checkpoint
									des2_control_flag = 0
									des2_truck_control_flag = 0
									des2_goals = 3
								ENDIF
							
								//starting truck switching lanes
								IF des2_truck_control_flag = 0  
									IF game_timer_diff > 1000
										des2_truck_control_flag = 1
									ENDIF
								ENDIF	 
							ENDIF
						ENDIF
					ELSE
						CLEAR_THIS_PRINT HOLD_1
						game_timer_flag = 0
						pos1_flag = 0	
					ENDIF
				
				
					//in front of correct position - flag 2
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 12.0 10.0 in_pos1x in_pos1y in_pos1z    
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 4.0 -10.0 in_pos2x in_pos2y in_pos2z    
					IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer in_pos1x in_pos1y in_pos1z in_pos2x in_pos2y in_pos2z 3.0 FALSE
						IF pos2_flag = 0
							IF des2_anim_been_played = 0 
								IF IS_CHAR_PLAYING_ANIM cesar BK_blnce_in
									GET_CHAR_ANIM_CURRENT_TIME cesar BK_blnce_in des2_anim_time
									IF des2_anim_time = 1.0
										TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
										des2_anim_been_played = 1	
									ENDIF
								ELSE
									TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
									des2_anim_been_played = 1	
								ENDIF
							ENDIF
							IF des2_anim_been_played = 2
								TASK_PLAY_ANIM cesar BK_rdy_out BIKELEAP 4.0 FALSE FALSE FALSE FALSE -1
								des2_anim_been_played = 1	
							ENDIF 
						
							//SLOW DOWN SPEECH  
							IF des2_speech_goals = 0							
								des2_speech_goals = 10
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 
							ENDIF
						
							pos2_flag = 1
						ENDIF
					ELSE
						pos2_flag = 0
					ENDIF						    
				
					//to the right of the correct position - flag 3
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -6.0 8.0 10.0 in_pos1x in_pos1y in_pos1z    
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -6.0 -8.0 -10.0 in_pos2x in_pos2y in_pos2z    
					IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer in_pos1x in_pos1y in_pos1z in_pos2x in_pos2y in_pos2z 3.0 FALSE
						IF pos3_flag = 0
							IF des2_anim_been_played = 0 
								IF IS_CHAR_PLAYING_ANIM cesar BK_blnce_in
									GET_CHAR_ANIM_CURRENT_TIME cesar BK_blnce_in des2_anim_time
									IF des2_anim_time = 1.0
										TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
										des2_anim_been_played = 1	
									ENDIF
								ELSE
									TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
									des2_anim_been_played = 1	
								ENDIF
							ENDIF
							IF des2_anim_been_played = 2
								TASK_PLAY_ANIM cesar BK_rdy_out BIKELEAP 4.0 FALSE FALSE FALSE FALSE -1
								des2_anim_been_played = 1	
							ENDIF 
														    
							//GET CLOSER SPEECH  
							IF des2_speech_goals = 0							
								des2_speech_goals = 7
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 
							ENDIF

							pos3_flag = 1
						ENDIF
					ELSE
						pos3_flag = 0		  
					ENDIF
				
					//behind the correct position
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 0.0 10.0 in_pos1x in_pos1y in_pos1z    
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 -8.0 -10.0 in_pos2x in_pos2y in_pos2z    
					IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer in_pos1x in_pos1y in_pos1z in_pos2x in_pos2y in_pos2z 3.0 FALSE
						IF pos4_flag = 0
							IF des2_anim_been_played = 0 
								IF IS_CHAR_PLAYING_ANIM cesar BK_blnce_in
									GET_CHAR_ANIM_CURRENT_TIME cesar BK_blnce_in des2_anim_time
									IF des2_anim_time = 1.0
										TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
										des2_anim_been_played = 1	
									ENDIF
								ELSE
									TASK_PLAY_ANIM cesar BK_blnce_in BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
									des2_anim_been_played = 1	
								ENDIF
							ENDIF
							IF des2_anim_been_played = 2
								TASK_PLAY_ANIM cesar BK_rdy_out BIKELEAP 4.0 FALSE FALSE FALSE FALSE -1
								des2_anim_been_played = 1	
							ENDIF 
						
							//SPEED UP SPEECH  
							IF des2_speech_goals = 0							
								des2_speech_goals = 9
								GENERATE_RANDOM_INT_IN_RANGE 0 3 des2_speech_control_flag
								des2_random_last_label = des2_speech_control_flag + 1 
								GOSUB des2_dialogue_setup 
							ENDIF

							pos4_flag = 1
						ENDIF
					ELSE
						pos4_flag = 0
					ENDIF
				
					//getting cesar to sit back down 
					IF pos1_flag = 0 
					AND pos2_flag = 0
					AND pos3_flag = 0
					AND pos4_flag = 0
						IF des2_anim_been_played > 0
							TASK_PLAY_ANIM cesar BK_blnce_out BIKELEAP 4.0 FALSE FALSE FALSE FALSE -1
							des2_anim_been_played = 0	
						ENDIF	 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		GOSUB des2_attaching_cab
		GOSUB des2_bike_blippage
		GOSUB des2_cesar_group
		//GOSUB des2_moving_checkpoint
		GOSUB des2_slowdown_code
		GOSUB des2_throwing_cars_out_of_way
	ENDIF

		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////making the jump/////////////////////////////////////////////////////////////////////////////////
		
	//cesar jumping
	IF des2_goals = 3
		IF des2_control_flag = 0
			REMOVE_CHAR_FROM_GROUP cesar
			TASK_PLAY_ANIM cesar Bk_jmp BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
			des2_control_flag = 1
		ENDIF

		IF des2_control_flag = 1 
			IF IS_CHAR_PLAYING_ANIM cesar Bk_jmp
				GET_CHAR_ANIM_CURRENT_TIME cesar Bk_jmp des2_anim_time
				IF des2_anim_time = 1.0
					des2_control_flag = 2
				ENDIF
			ENDIF
		ENDIF
		
		IF des2_control_flag = 2
			IF NOT IS_CAR_DEAD des2_car 
				REMOVE_CHAR_FROM_CAR_MAINTAIN_POSITION cesar des2_car
			ENDIF
			//WARP_CHAR_FROM_CAR_TO_COORD cesar 0.0 0.0 0.0
			ATTACH_CHAR_TO_CAR cesar des2_transporter -1.0 0.0 0.0 FACING_RIGHT 360.0 WEAPONTYPE_UNARMED
			TASK_PLAY_ANIM cesar truck_getin BIKELEAP 1000.0 FALSE FALSE FALSE TRUE -1				 
			IF NOT IS_CAR_DEAD des2_car 
				DETACH_CAR des2_car 0.0 0.0 0.0 0
			ENDIF
			SET_PLAYER_CONTROL player1 ON
			des2_control_flag = 3
		ENDIF
		
		IF des2_control_flag > 3
			GET_CAR_HEADING des2_transporter des2_truck_heading
			SET_CHAR_HEADING cesar des2_truck_heading   	
		ENDIF
		
		IF des2_control_flag = 3 			
			IF IS_CHAR_PLAYING_ANIM cesar truck_getin
				GET_CHAR_ANIM_CURRENT_TIME cesar truck_getin des2_anim_time
				IF des2_anim_time > 0.41 
				//IF des2_anim_time > 0.47 ///////DEBUG - THIS NEEDS TO BE SET TO 0.41 ONCE OPEN_CAR_DOOR works smoothly
					des2_opening_door_flag = 1 
					des2_control_flag = 4
				ENDIF
			ENDIF
		ENDIF

		IF des2_control_flag = 4
			IF IS_CHAR_PLAYING_ANIM cesar truck_getin
				GET_CHAR_ANIM_CURRENT_TIME cesar truck_getin des2_anim_time
				IF des2_anim_time > 0.66
					IF NOT IS_CHAR_DEAD des2_transporter_driver 
						TASK_PLAY_ANIM des2_transporter_driver truck_driver BIKELEAP 4.0 FALSE FALSE FALSE TRUE -1
						des2_truck_control_flag = 1
						des2_control_flag = 5
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF des2_control_flag = 5
			IF IS_CHAR_PLAYING_ANIM cesar truck_getin
				GET_CHAR_ANIM_CURRENT_TIME cesar truck_getin des2_anim_time
				IF des2_anim_time = 1.0
					IF NOT IS_CHAR_DEAD des2_transporter_driver 
						WARP_CHAR_FROM_CAR_TO_COORD des2_transporter_driver 0.0 0.0 0.0
						WARP_CHAR_INTO_CAR_AS_PASSENGER des2_transporter_driver des2_transporter -1
						TASK_PLAY_ANIM des2_transporter_driver struggle_driver BIKELEAP 1000.0 TRUE FALSE FALSE TRUE -1
					ENDIF
					TASK_PLAY_ANIM cesar struggle_cesar BIKELEAP 1000.0 TRUE FALSE FALSE TRUE -1
					des2_control_flag = 6
				ENDIF
			ENDIF
		ENDIF
 			
			
		IF des2_control_flag = 6 	
			IF des2_truck_control_flag = 16
				IF NOT IS_CHAR_DEAD des2_transporter_driver
					CLEAR_CHAR_TASKS cesar
					CLEAR_CHAR_TASKS des2_transporter_driver
					//WARP_CHAR_FROM_CAR_TO_COORD des2_transporter_driver 0.0 0.0 0.0
					//ATTACH_CHAR_TO_CAR des2_transporter_driver des2_transporter 0.0 0.0 0.0 FACING_RIGHT 360.0 WEAPONTYPE_UNARMED
					OPEN_SEQUENCE_TASK des2_seq	
						TASK_LEAVE_CAR_IMMEDIATELY -1 des2_transporter
						//TASK_PLAY_ANIM -1 truck_jumpout BIKELEAP 1000.0 FALSE FALSE FALSE TRUE -1 	
					CLOSE_SEQUENCE_TASK des2_seq
					PERFORM_SEQUENCE_TASK des2_transporter_driver des2_seq
					CLEAR_SEQUENCE_TASK des2_seq
					des2_control_flag = 8
				ENDIF
			ENDIF
		ENDIF
			
		/*
		IF des2_control_flag = 7
			IF NOT IS_CHAR_DEAD des2_transporter_driver 
				IF IS_CHAR_PLAYING_ANIM des2_transporter_driver truck_jumpout
					GET_CHAR_ANIM_CURRENT_TIME des2_transporter_driver truck_jumpout des2_anim_time
					IF des2_anim_time > 0.10 //////////DEBUG this needs to be 0.06 once OPEN_CAR_DOOR is fixed 
						OPEN_CAR_DOOR des2_transporter FRONT_RIGHT_DOOR
						des2_control_flag = 8
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		*/
		IF des2_control_flag = 8	
			IF NOT IS_CHAR_DEAD des2_transporter_driver 
				IF NOT IS_CHAR_IN_ANY_CAR des2_transporter_driver 
					SET_CAR_TEMP_ACTION des2_transporter TEMPACT_NONE -1 
					SET_CAR_ONLY_DAMAGED_BY_PLAYER des2_transporter FALSE
					//DETACH_CHAR_FROM_CAR des2_transporter_driver 
					TASK_DEAD des2_transporter_driver
					DETACH_CHAR_FROM_CAR cesar
					WARP_CHAR_INTO_CAR_AS_PASSENGER cesar des2_transporter -1
					des2_car_check_flag = 1
					LOCK_CAR_DOORS des2_transporter CARLOCK_UNLOCKED 
					MARK_MODEL_AS_NO_LONGER_NEEDED pcj600
					REMOVE_ANIMATION BIKELEAP
					RELEASE_PATH_NODES
					CLEAR_PRINTS

					REMOVE_BLIP des2_transporter_blip
					ADD_BLIP_FOR_CAR des2_transporter des2_transporter_blip
					SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
					
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE

					PRINT_NOW ( TOR1_11 ) 4000 1 // Get into the truck and drive it back to the garage.
					des2_control_flag = 0
					des2_speech_goals = 0
					des2_speech_flag = 0
					des2_goals = 4
				ENDIF
			ENDIF	
		ENDIF

		
		//controlling the truck	sliding about
 		IF des2_truck_control_flag = 1
			SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
			SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNLEFT 200 
			SET_CAR_CRUISE_SPEED des2_transporter 35.0 
			timera = 0 
			des2_truck_control_flag = 2
		ENDIF
		
		IF des2_truck_control_flag = 2
			IF timera > 200
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 300	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 3
			ENDIF
		ENDIF
		
		IF des2_truck_control_flag = 3
			IF timera > 300
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNRIGHT 200 
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0 
				des2_truck_control_flag = 4
			ENDIF
		ENDIF
		
		IF des2_truck_control_flag = 4
			IF timera > 200
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 5
			ENDIF
		ENDIF
		
		IF des2_truck_control_flag = 5
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNLEFT 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 6
			ENDIF
		ENDIF
	
		IF des2_truck_control_flag = 6
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 7
			ENDIF
		ENDIF

		IF des2_truck_control_flag = 7
			IF timera > 1000
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNRIGHT 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 8
			ENDIF
		ENDIF

		IF des2_truck_control_flag = 8
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 9
			ENDIF
		ENDIF

		IF des2_truck_control_flag = 9
			IF timera > 1000
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNLEFT 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 10
			ENDIF
		ENDIF

		IF des2_truck_control_flag = 10
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 11
			ENDIF
		ENDIF

		IF des2_truck_control_flag = 11
			IF timera > 1000
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNRIGHT 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 12
			ENDIF
		ENDIF
	
		IF des2_truck_control_flag = 12
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 13
			ENDIF
		ENDIF
	
		IF des2_truck_control_flag = 13
			IF timera > 1000
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_TURNLEFT 500	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 14
			ENDIF
		ENDIF
	
		IF des2_truck_control_flag = 14
			IF timera > 500
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_GOFORWARD 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 15
			ENDIF
		ENDIF
	
		IF des2_truck_control_flag = 15
			IF timera > 1000
				SET_CAR_STATUS des2_transporter STATUS_PHYSICS  
				SET_CAR_TEMP_ACTION des2_transporter TEMPACT_HANDBRAKESTRAIGHT 1000	
				SET_CAR_CRUISE_SPEED des2_transporter 35.0 
				timera = 0
				des2_truck_control_flag = 16
			ENDIF
		ENDIF
	

		//opening car door
		IF des2_opening_door_flag = 1
			IF NOT IS_CAR_DOOR_FULLY_OPEN des2_transporter FRONT_LEFT_DOOR
				des2_open_door_float += 0.1	
				OPEN_CAR_DOOR_A_BIT des2_transporter FRONT_LEFT_DOOR des2_open_door_float
			ELSE
				des2_opening_door_flag = 2
			ENDIF
		ENDIF	


		GOSUB des2_throwing_cars_out_of_way
		GOSUB des2_attaching_cab
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////driving the truck back to the hub///////////////////////////////////////////////////////////////

	IF des2_goals = 4
	//////////////////////////////////DEBUG////////////////////////
		IF IS_CHAR_IN_ANY_CAR scplayer 
			IF IS_CHAR_IN_ANY_CAR cesar
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
					des2_control_flag = 0
					des2_goals = 5
				ENDIF
			ENDIF
		ENDIF
	//////////////////////////////////DEBUG////////////////////////
		
		IF des2_control_flag = 0
			IF IS_CHAR_IN_CAR scplayer des2_transporter 
				IF IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter
					REMOVE_BLIP des2_transporter_blip
					ADD_BLIP_FOR_COORD -2016.5 157.7 26.7 des2_transporter_blip
					PRINT ( TOR1_06 ) 7000 1 //Drive the truck back to the ~y~garage~s~.
					des2_cab_attached = 1
				ELSE
					REMOVE_BLIP des2_transporter_blip
					ADD_BLIP_FOR_CAR des2_tanker des2_transporter_blip
					SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
					PRINT ( TOR1_21 ) 7000 1 //Reconnect to the ~b~cab~s~.
					des2_cab_attached = 0
				ENDIF		 
				SET_RADIO_CHANNEL RS_CLASSIC_HIP_HOP
				des2_car_check_flag = 1
				des2_control_flag = 1
				timerb = 0
			ENDIF
		ENDIF
		
		IF des2_control_flag = 1
			//speech for this section
			IF des2_speech_flag = 0
				IF timerb > 7000
					IF des2_cab_attached = 1
						IF des2_car_check_flag = 1  
							des2_speech_goals = 11			
							des2_speech_control_flag = 0
							GOSUB des2_dialogue_setup 
							des2_speech_flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
	
			//waiting for player to arrive at the hub
			IF IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter	
				IF IS_CHAR_IN_CAR scplayer des2_transporter 
					IF LOCATE_CAR_3D des2_transporter -2016.5 157.7 26.7 4.0 4.0 4.0 TRUE
						SET_PLAYER_CONTROL player1 OFF
						des2_control_flag = 0
						des2_goals = 5
					ENDIF
				ENDIF
			ENDIF
			GOSUB des2_car_blippage
			GOSUB des2_cab_attached
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////cutscene at hub/////////////////////////////////////////////////////////////////////////////////

	IF des2_goals = 5
		IF des2_control_flag = 0
			SET_PLAYER_CONTROL player1 OFF 
			APPLY_BRAKES_TO_PLAYERS_CAR player1 ON  
			CLEAR_PRINTS
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			des2_control_flag = 1
		ENDIF
		IF des2_control_flag = 1
			REQUEST_ANIMATION POLICE
			WHILE NOT HAS_ANIMATION_LOADED POLICE
				WAIT 0
			ENDWHILE

			OPEN_GARAGE hbgdSFS
			WHILE NOT IS_GARAGE_OPEN hbgdSFS
				WAIT 0 	
			ENDWHILE
			
			GOSUB des2_death_checks
			IF des2_deathcheck_flag = 1
				GOTO mission_toreno2_failed
			ENDIF
			
			REQUEST_COLLISION -1998.7 178.4 
			LOAD_SCENE_IN_DIRECTION -1998.7 178.4 26.3 270.0
			//LOAD_SCENE -2035.3 180.5 27.8
			CLEAR_AREA -1998.7 178.4 26.3 200.0 FALSE
			SET_PED_DENSITY_MULTIPLIER 0.0 
			SET_CAR_DENSITY_MULTIPLIER 0.0 
			
			IF NOT IS_CAR_DEAD des2_tanker 
				IF IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter
					DETACH_TRAILER_FROM_CAB des2_tanker des2_transporter
					SET_CAR_COORDINATES des2_tanker -1998.7 178.4 26.3 
					SET_CAR_HEADING des2_tanker 88.7
				ENDIF
			ENDIF
			//DELETE_CAR des2_tanker 
			SET_CAR_COORDINATES des2_transporter -2006.7 178.4 26.3 
			SET_CAR_HEADING des2_transporter 88.7
			
			WAIT 0 
			GOSUB des2_death_checks
			IF des2_deathcheck_flag = 1
				GOTO mission_toreno2_failed
			ENDIF
			
			IF NOT IS_CAR_DEAD des2_tanker 			
				ATTACH_TRAILER_TO_CAB des2_tanker des2_transporter
			ENDIF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			SET_CHAR_COORDINATES scplayer -2035.3 180.5 27.8
			SET_CHAR_HEADING scplayer 277.5 
		
			OPEN_SEQUENCE_TASK des2_seq
				TASK_PLAY_ANIM -1 CopTraf_Come POLICE 4.0 FALSE FALSE FALSE FALSE -1 
			CLOSE_SEQUENCE_TASK des2_seq
			PERFORM_SEQUENCE_TASK scplayer des2_seq
			CLEAR_SEQUENCE_TASK des2_seq
				
			CLEAR_CHAR_TASKS_IMMEDIATELY cesar 
			WARP_CHAR_INTO_CAR cesar des2_transporter 	 

			TASK_CAR_DRIVE_TO_COORD cesar des2_transporter -2053.3 178.8 27.8 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			
			//SET_CAR_STRAIGHT_LINE_DISTANCE des2_transporter 255
			//CAR_GOTO_COORDINATES des2_transporter -2053.3 178.8 27.8
			//SET_CAR_CRUISE_SPEED des2_transporter 10.0
			
			CLEAR_PRINTS 
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			des2_speech_goals = 0
		
			SHUT_ALL_CHARS_UP TRUE
			
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				
			SET_FIXED_CAMERA_POSITION -2038.0 182.8 29.2 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2001.0 166.2 31.2 JUMP_CUT

			DO_FADE 2000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			GOSUB des2_death_checks
			IF des2_deathcheck_flag = 1
				GOTO mission_toreno2_failed
			ENDIF
			des2_control_flag = 2
		ENDIF

		IF des2_control_flag = 2
			GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM task_status
			IF task_status = FINISHED_TASK
				OPEN_SEQUENCE_TASK des2_seq
					TASK_PLAY_ANIM -1 CopTraf_Come POLICE 4.0 FALSE FALSE FALSE FALSE -1 
					TASK_PLAY_ANIM -1 CopTraf_left POLICE 4.0 FALSE FALSE FALSE FALSE -1 
				CLOSE_SEQUENCE_TASK des2_seq
				PERFORM_SEQUENCE_TASK scplayer des2_seq
				CLEAR_SEQUENCE_TASK des2_seq
				timera = 0
				des2_control_flag = 3
			ENDIF			
		ENDIF

		IF des2_control_flag = 3
			IF timera > 5000 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				UNLOAD_SPECIAL_CHARACTER 1
				DELETE_CAR des2_transporter
				DELETE_CAR des2_tanker  
				CLOSE_GARAGE hbgdSFS
				WHILE NOT IS_GARAGE_CLOSED hbgdSFS
					WAIT 0 
				ENDWHILE

				SHUT_ALL_CHARS_UP FALSE
				
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
				GOTO mission_toreno2_passed 
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Failing mission if transporter makes it back to silicon valley ////////////////////////////////


	IF des2_goals < 3
		IF NOT IS_CHAR_DEAD des2_transporter_driver
			IF LOCATE_CAR_2D des2_transporter -1914.6 -1187.7 5.0 5.0 FALSE 
				des2_control_flag = 0
				des2_goals = 6
			ENDIF
		ENDIF 
	ENDIF
	
	IF des2_goals = 6
		GOSUB check_player_is_safe
		IF player_is_completely_safe = 1
			IF des2_control_flag = 0
				CLEAR_PRINTS
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
			
				GOSUB des2_death_checks
				IF des2_deathcheck_flag = 1
					GOTO mission_toreno2_failed
				ENDIF
				
				CLEAR_PRINTS 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				des2_speech_goals = 0
			
				SET_PLAYER_CONTROL player1 OFF			
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				SET_FIXED_CAMERA_POSITION -1932.8 -991.8 31.3 0.0 0.0 0.0	
				POINT_CAMERA_AT_POINT -1959.7 -1019.9 43.1 JUMP_CUT

				IF IS_CHAR_IN_AREA_2D scplayer -1915.0 -977.8 -2013.4 -1074.8 FALSE
					SET_CHAR_COORDINATES scplayer -1984.5 -1072.9 31.5
				ENDIF
				 
				IF IS_CHAR_IN_AREA_2D cesar -1915.0 -977.8 -2013.4 -1074.8 FALSE
					SET_CHAR_COORDINATES cesar -1984.5 -1074.9 31.5
				ENDIF

				REQUEST_COLLISION -1984.3 -1045.7
				LOAD_SCENE -1984.3 -1045.7 31.6 
				CLEAR_AREA -1984.3 -1045.7 31.6 25.0 TRUE 
			 	SET_CAR_COORDINATES des2_transporter -1984.3 -1045.7 31.6 
				SET_CAR_HEADING des2_transporter 0.0
				
				IF NOT IS_CAR_DEAD des2_tanker
				 	SET_CAR_COORDINATES des2_tanker -1984.3 -1055.7 31.6 
					SET_CAR_HEADING des2_tanker 0.0
					ATTACH_TRAILER_TO_CAB des2_tanker des2_transporter
				ENDIF 
				
				IF NOT IS_CHAR_DEAD des2_transporter_driver
					OPEN_SEQUENCE_TASK des2_seq
						TASK_CAR_DRIVE_TO_COORD -1 des2_transporter -1984.8 -1022.1 31.6 15.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH 
						TASK_CAR_DRIVE_TO_COORD -1 des2_transporter -1954.4 -1007.4 31.6 15.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH 
						TASK_CAR_DRIVE_TO_COORD -1 des2_transporter -1943.6 -1040.1 31.6 15.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
						TASK_CAR_DRIVE_TO_COORD -1 des2_transporter -1946.6 -1018.0 31.6 15.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH 
					CLOSE_SEQUENCE_TASK des2_seq
					PERFORM_SEQUENCE_TASK des2_transporter_driver des2_seq
					CLEAR_SEQUENCE_TASK des2_seq
				ENDIF

				PRINT_NOW ( TOR1_12 ) 11000 1 //You failed to steal the truck!
				
				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
					GOSUB des2_attaching_cab
				ENDWHILE 
			
				GOSUB des2_death_checks
				IF des2_deathcheck_flag = 1
					GOTO mission_toreno2_failed
				ENDIF
				SKIP_CUTSCENE_START
					
				des2_control_flag = 1
			ENDIF
		ENDIF

		IF des2_control_flag = 1
			GOSUB des2_attaching_cab
			IF LOCATE_CAR_2D des2_transporter -1943.6 -1040.1 5.0 5.0 FALSE	
				SKIP_CUTSCENE_END
				GOSUB des2_death_checks
				IF des2_deathcheck_flag = 1
					GOTO mission_toreno2_failed
				ENDIF
				
				CLEAR_PRINTS 
				PRINT_NOW ( TOR1_12 ) 4000 1 //You failed to steal the truck!			
				GOTO mission_toreno2_failed 
			ENDIF
		ENDIF
	ENDIF

	//ingame dialogue 
	GOSUB des2_overall_dialogue

GOTO mission_toreno2_loop


	
// Mission toreno2 failed

mission_toreno2_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission toreno2 passed
mission_toreno2_passed:
flag_desert_mission_counter ++
//flag_toreno2_mission1_passed = 1
PRINT_WITH_NUMBER_BIG ( M_PASS ) 7000 5000 1 //"Mission Passed!" //100 being the amount of cash
ADD_SCORE player1 7000//amount of cash
SET_INT_STAT PASSED_DESERT12 1
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED DESERT2
REMOVE_BLIP desert_contact_blip
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT toreno2_mission_loop
RETURN
		

// mission cleanup
mission_cleanup_toreno2:
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
RELEASE_PATH_NODES
SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0
MARK_MODEL_AS_NO_LONGER_NEEDED PETRO 
MARK_MODEL_AS_NO_LONGER_NEEDED pcj600
MARK_MODEL_AS_NO_LONGER_NEEDED petrotr
MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
REMOVE_CHAR_ELEGANTLY cesar
UNLOAD_SPECIAL_CHARACTER 1
REMOVE_ANIMATION BIKELEAP
REMOVE_ANIMATION POLICE
REMOVE_BLIP des2_transporter_blip
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
des2_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF des2_goals < 3
	IF IS_CAR_DEAD des2_car
		CLEAR_PRINTS
		PRINT_NOW ( TOR1_02 ) 4000 1 //You destroyed the bike.
		des2_deathcheck_flag = 1 
	ENDIF
ENDIF

IF des2_goals > 1
	IF IS_CAR_DEAD des2_transporter
		CLEAR_PRINTS
		PRINT_NOW ( TOR1_14 ) 4000 1 //The transport has been destroyed.
		des2_deathcheck_flag = 1 
	ENDIF

	IF IS_CAR_DEAD des2_tanker
		CLEAR_PRINTS
		PRINT_NOW ( TOR1_14 ) 4000 1 //The transport has been destroyed.
		des2_deathcheck_flag = 1 
	ENDIF
ENDIF

IF IS_CHAR_DEAD cesar
	CLEAR_PRINTS
	PRINT_NOW ( TOR1_03 ) 4000 1 //Cesar has been killed.
	des2_deathcheck_flag = 1 
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_car_blippage://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//sorting the blippage
IF des2_car_check_flag = 0
	IF IS_CHAR_IN_CAR scplayer des2_transporter 
		IF NOT IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter 
			REMOVE_BLIP des2_transporter_blip
			ADD_BLIP_FOR_CAR des2_tanker des2_transporter_blip
			SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
		ELSE
			REMOVE_BLIP des2_transporter_blip
			ADD_BLIP_FOR_COORD -2016.5 157.7 26.7 des2_transporter_blip
		ENDIF
		des2_car_check_flag = 1
	ENDIF
ENDIF
IF des2_car_check_flag = 1 
	IF NOT IS_CHAR_IN_CAR scplayer des2_transporter
		REMOVE_BLIP des2_transporter_blip
		ADD_BLIP_FOR_CAR des2_transporter des2_transporter_blip
		SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
		des2_car_check_flag = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_cab_attached://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF des2_car_check_flag = 1
	IF NOT IS_CAR_DEAD des2_tanker 
		SET_CAR_HEALTH des2_tanker 1000
		IF des2_cab_attached = 1
			IF NOT IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter 
		        REMOVE_BLIP des2_transporter_blip
				ADD_BLIP_FOR_CAR des2_tanker des2_transporter_blip
				SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
				PRINT_NOW ( TOR1_21 ) 4000 1 //Reconnect to the ~b~cab~s~.
				des2_cab_attached = 0
			ENDIF
		ENDIF

		IF des2_cab_attached = 0
			IF IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter	
		        REMOVE_BLIP des2_transporter_blip
				ADD_BLIP_FOR_COORD -2016.5 157.7 26.7 des2_transporter_blip
				PRINT ( TOR1_06 ) 4000 1 //Drive the truck back to the ~y~garage~s~.
				des2_cab_attached = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_bike_blippage://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//sorting the blippage
IF des2_flag_cesar_in_group = 1
	IF des2_car_check_flag = 0
		IF IS_CHAR_IN_CAR scplayer des2_car 
			CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
			CLEAR_THIS_PRINT TOR1_20 
		
			REMOVE_BLIP des2_transporter_blip
			IF NOT IS_CAR_DEAD des2_transporter 
				ADD_BLIP_FOR_CAR des2_transporter des2_transporter_blip
			ENDIF

			IF timerb > 8000
				IF IS_CHAR_IN_CAR cesar des2_car  
					PRINT_NOW ( TOR1_05 ) 4000 1 // Catch up to the truck.
				ELSE
					PRINT_NOW ( TOR1_16 ) 4000 1 // Pick up Cesar then catch up to the truck.
				ENDIF
			ENDIF

			des2_car_check_flag = 1
		ENDIF
	ENDIF
	IF des2_car_check_flag = 1 
		IF NOT IS_CHAR_IN_CAR scplayer des2_car
			CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
			CLEAR_THIS_PRINT TOR1_10 
			CLEAR_THIS_PRINT TOR1_16 
		
			REMOVE_BLIP des2_transporter_blip
			ADD_BLIP_FOR_CAR des2_car des2_transporter_blip
			SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE

			IF timerb > 8000
				PRINT_NOW ( TOR1_20 ) 4000 1 //Get back on the bike.	
			ENDIF
			des2_car_check_flag = 0
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_cesar_group://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF des2_flag_cesar_in_group = 1
    IF NOT IS_GROUP_MEMBER cesar Players_Group
        REMOVE_BLIP des2_transporter_blip
        ADD_BLIP_FOR_CHAR cesar des2_transporter_blip
		SET_BLIP_AS_FRIENDLY des2_transporter_blip TRUE
        des2_flag_cesar_in_group = 0
    ENDIF
ENDIF

IF des2_flag_cesar_in_group = 0 
    IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cesar 8.0 8.0 8.0 FALSE
		IF NOT IS_GROUP_MEMBER cesar Players_Group
	        MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
	        SET_GROUP_MEMBER Players_Group cesar 
		ENDIF

        REMOVE_BLIP des2_transporter_blip
		IF NOT IS_CHAR_IN_CAR scplayer des2_car
			des2_car_check_flag = 1
		ELSE
			des2_car_check_flag = 0
		ENDIF	 
        des2_flag_cesar_in_group = 1
    ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
/*
////////////////////////////////////////////////////////////////////////////
des2_moving_checkpoint://///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -2.692 2.484 -1.0 des2_x des2_y z //exactly positioned where jump takes place
//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -2.5 2.0 -1.0 des2_x des2_y z //middle of area
GET_GROUND_Z_FOR_3D_COORD des2_x des2_y 45.0 des2_z 
des2_z += 1.0 
SET_CHECKPOINT_COORDS des2_checkpoint des2_x des2_y des2_z  
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
*/
////////////////////////////////////////////////////////////////////////////
des2_attaching_cab://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD des2_transporter
	IF NOT IS_CAR_DEAD des2_tanker
		SET_CAR_HEALTH des2_tanker 1000
		IF NOT IS_TRAILER_ATTACHED_TO_CAB des2_tanker des2_transporter 
			ATTACH_TRAILER_TO_CAB des2_tanker des2_transporter
		ENDIF
	ENDIF
ENDIF	  
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_slowdown_code://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD des2_transporter
	IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer des2_transporter 20.0 20.0 FALSE 
		SET_CAR_CRUISE_SPEED des2_transporter 37.0
	ELSE
		IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer des2_transporter 40.0 40.0 FALSE 
			SET_CAR_CRUISE_SPEED des2_transporter 20.0
		ELSE
			SET_CAR_CRUISE_SPEED des2_transporter 15.0
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_throwing_cars_out_of_way://////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF NOT IS_CAR_DEAD des2_transporter
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter -3.0 2.0 0.0 infront1x infront1y z	
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS des2_transporter 3.0 11.0 0.0 infront2x infront2y z	
	GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE infront1x infront1y infront2x infront2y -1 des2_random_car 
	
	IF NOT IS_CAR_DEAD des2_random_car 
		IF NOT des2_random_car = des2_car  
			
			GET_CAR_HEALTH des2_random_car des2_health 
			
			IF des2_health > 500
			 
				GET_CAR_SPEED des2_transporter fTransSpeed
				// this is used as a force mult
				//fTransSpeed *= 0.005
				fTransSpeed *= 0.02

				GET_CAR_FORWARD_X des2_transporter infront1x
				GET_CAR_FORWARD_Y des2_transporter infront1y
				
				infront2x = infront1y
				infront2y = -1.0 * infront1x
				
				// work out which side of the transporter the car is on
				GET_CAR_COORDINATES des2_random_car fOffsetX fOffsetY fOffsetZ
				GET_CAR_COORDINATES des2_transporter fForceX fForceY fForceZ
				
				fOffsetX -= fForceX
				fOffsetY -= fForceY

				// use to decide which direction to shove the offending car
				temp1 = infront2x * fOffsetX
				temp2 = infront2y * fOffsetY
				fDotProd = temp1 + temp2

				
				IF fDotProd < 2.5
					IF fDotProd > -2.5 

						IF fDotProd > 0.0
							fDotProd = 1.0
						ELSE
							fDotProd = -1.0
						ENDIF

						// calculate direction of force to apply
						temp1 = infront1x * fTransSpeed
						temp2 = fDotProd * infront2x
						temp2 *= 0.5
						temp3 = fTransSpeed * temp2				
						fForceX = temp1 + temp3 

						temp1 = infront1y * fTransSpeed
						temp2 = fDotProd * infront2y
						temp2 *= 0.5
						temp3 = fTransSpeed * temp2				
						fForceY = temp1 + temp3

						fForceZ = fTransSpeed + 0.008
						fForceZ *= 0.5

						// calculate an offset position so the car spins the correct way
						fOffsetX = -1.0
						fOffsetX *= fDotProd
						fOffsetX *= infront2x
						fOffsetX *= fTransSpeed 

						fOffsetY = -1.0
						fOffsetY *= fDotProd
						fOffsetY *= infront2Y
						fOffsetY *= fTransSpeed 
						
						fOffsetZ = 0.0
			
						//WRITE_DEBUG workedfine 

						PLAY_MISSION_AUDIO 3
						SET_CAR_STATUS des2_random_car STATUS_PHYSICS
						APPLY_FORCE_TO_CAR des2_random_car fForceX fForceY fForceZ fOffsetX fOffsetY fOffsetZ
						SET_CAR_HEALTH des2_random_car 499 
					ENDIF
				ENDIF

				CLEAR_CAR_LAST_DAMAGE_ENTITY des2_random_car 
				MARK_CAR_AS_NO_LONGER_NEEDED des2_random_car  
			ENDIF
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
																			   
////////////////////////////////////////////////////////////////////////////   
des2_overall_dialogue:///////////////////////////////////////////////////////  
////////////////////////////////////////////////////////////////////////////
IF des2_speech_goals = 1 //initial cutscene  
	IF des2_speech_control_flag < des2_last_label
		GOSUB des2_loading_dialogue
		GOSUB des2_playing_dialogue
		GOSUB des2_finishing_dialogue  
	ELSE
		des2_speech_goals = 0
	ENDIF
ENDIF

IF des2_goals = 1
OR des2_goals = 2
	IF des2_goals < 14
	OR des2_goals = 16 
		IF IS_GROUP_MEMBER cesar Players_Group 

			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D cesar scplayer 10.0 10.0 8.0 FALSE
	            IF IS_CHAR_ON_FOOT scplayer
    	        AND IS_CHAR_ON_FOOT cesar
					IF des2_speech_goals = 2 //first convo as player and cesar get on the bike
					OR des2_speech_goals = 3 //getting on the freeway
					OR des2_speech_goals = 4 //on wrong bridge 
					OR des2_speech_goals = 5 //missed the freeway
					OR des2_speech_goals = 6 //coming up to the rig
						IF des2_speech_control_flag < des2_last_label
							GOSUB des2_loading_dialogue
							GOSUB des2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB des2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
								des2_slot1 = 0
								des2_slot2 = 0
							ENDIF
						ELSE
							des2_speech_goals = 0
						ENDIF
					ENDIF
	            ENDIF   
                IF IS_CHAR_IN_ANY_CAR scplayer
                AND IS_CHAR_IN_ANY_CAR cesar
					IF des2_speech_goals = 2 //first convo as player and cesar get on the bike
					OR des2_speech_goals = 3 //getting on the freeway
					OR des2_speech_goals = 4 //on wrong bridge 
					OR des2_speech_goals = 5 //missed the freeway
					OR des2_speech_goals = 6 //coming up to the rig
						IF des2_speech_control_flag < des2_last_label
							GOSUB des2_loading_dialogue
							GOSUB des2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB des2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
								des2_slot1 = 0
								des2_slot2 = 0
							ENDIF
						ELSE
							des2_speech_goals = 0
						ENDIF
					ENDIF
                ENDIF
		
		
	            IF IS_CHAR_ON_FOOT scplayer
    	        AND IS_CHAR_ON_FOOT cesar
					IF des2_speech_goals = 7 //HOLD HER STEADY SPEECH
					OR des2_speech_goals = 9 //SPEED UP SPEECH 
					OR des2_speech_goals = 10 //SLOW DOWN SPEECH
						IF des2_speech_control_flag < des2_last_label
							GOSUB des2_loading_dialogue
							GOSUB des2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB des2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
								des2_slot1 = 0
								des2_slot2 = 0
							ENDIF
						ELSE
							des2_speech_goals = 0
						ENDIF
					ENDIF
	            ENDIF   
                IF IS_CHAR_IN_ANY_CAR scplayer
                AND IS_CHAR_IN_ANY_CAR cesar
					IF des2_speech_goals = 7 //HOLD HER STEADY SPEECH
					OR des2_speech_goals = 9 //SPEED UP SPEECH 
					OR des2_speech_goals = 10 //SLOW DOWN SPEECH
						IF des2_speech_control_flag < des2_last_label
							GOSUB des2_loading_dialogue
							GOSUB des2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB des2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
								des2_slot1 = 0
								des2_slot2 = 0
							ENDIF
						ELSE
							des2_speech_goals = 0
						ENDIF
					ENDIF
                ENDIF
			ENDIF

		
			/*
			IF des2_speech_goals = 2 //first convo as player and cesar get on the bike
			OR des2_speech_goals = 3 //getting on the freeway
			OR des2_speech_goals = 4 //on wrong bridge 
			OR des2_speech_goals = 5 //missed the freeway
			OR des2_speech_goals = 6 //coming up to the rig
				IF des2_speech_control_flag < des2_last_label
					GOSUB des2_loading_dialogue
					GOSUB des2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB des2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
						des2_slot1 = 0
						des2_slot2 = 0
					ENDIF
				ELSE
					des2_speech_goals = 0
				ENDIF
			ENDIF

			//OR des2_speech_goals = 8 //getting on the freeway
			IF des2_speech_goals = 7 //HOLD HER STEADY SPEECH
			OR des2_speech_goals = 9 //SPEED UP SPEECH 
			OR des2_speech_goals = 10 //SLOW DOWN SPEECH
				IF des2_speech_control_flag < des2_last_label
					GOSUB des2_loading_dialogue
					GOSUB des2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB des2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
						des2_slot1 = 0
						des2_slot2 = 0
					ENDIF
				ELSE
					des2_speech_goals = 0
				ENDIF
			ENDIF
			*/
		ELSE		  
			IF des2_speech_goals < 14
				IF des2_speech_control_flag < des2_last_label
					des2_speech_control_flag ++ 
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
				CLEAR_PRINTS
				des2_storing_speech_goals_number = des2_speech_goals 
				des2_storing_speech_control_number = des2_speech_control_flag
				des2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 des2_speech_control_flag
				des2_random_last_label = des2_speech_control_flag + 1 
				GOSUB des2_dialogue_setup
			ENDIF
		ENDIF

		IF des2_speech_goals = 14 //cesar is out of the group
			IF NOT IS_GROUP_MEMBER cesar Players_Group
				IF des2_speech_control_flag < des2_last_label
					GOSUB des2_loading_dialogue
					GOSUB des2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB des2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
						des2_slot1 = 0
						des2_slot2 = 0
					ENDIF
				ELSE
					PRINT ( TOR1_13 ) 4000 1 //You have left Cesar behind.
					des2_speech_goals = 15
				ENDIF
			ELSE
				PRINT ( TOR1_13 ) 4000 1 //You have left Cesar behind.
				des2_speech_goals = 15
			ENDIF
		ENDIF

		IF des2_speech_goals = 15 //cesar has been out of the group and has returned
			IF IS_GROUP_MEMBER cesar Players_Group 
				des2_speech_goals = 16
				des2_speech_control_flag = 0
				CLEAR_PRINTS
				//GOSUB des2_dialogue_setup
			ENDIF
		ENDIF

		IF des2_speech_goals = 16 //cesar is back in group
			IF IS_GROUP_MEMBER cesar Players_Group 	
				timerb = 0
				des2_speech_goals = des2_storing_speech_goals_number
				des2_speech_control_flag = des2_storing_speech_control_number
				GOSUB des2_dialogue_setup
				IF des2_storing_speech_goals_number = 0
					IF IS_CHAR_IN_CAR scplayer des2_car 
						PRINT ( TOR1_05 ) 7000 1 //~s~Catch up to the ~r~truck~s~.
					ELSE
						PRINT ( TOR1_20 ) 4000 1 //Get back on the bike.
					ENDIF
					timerb = 0
					des2_speech_control_flag = 0
					des2_speech_goals = 0
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
				des2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 des2_speech_control_flag
				des2_random_last_label = des2_speech_control_flag + 1 
				GOSUB des2_dialogue_setup
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF des2_goals = 4
	IF des2_control_flag = 1
		IF IS_CHAR_SITTING_IN_CAR scplayer des2_transporter
			IF des2_speech_goals = 11 //telling player to go to the hub
				IF des2_speech_control_flag < des2_last_label
					GOSUB des2_loading_dialogue
					GOSUB des2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB des2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
						des2_slot1 = 0
						des2_slot2 = 0
					ENDIF
				ELSE
					des2_speech_goals = 0
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_IN_CAR scplayer des2_transporter 
			IF des2_speech_goals < 17 
				IF des2_speech_control_flag < des2_last_label
					des2_speech_control_flag ++
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS 
				CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
				des2_storing_speech_goals_number = des2_speech_goals
				des2_storing_speech_control_number = des2_speech_control_flag
				des2_speech_goals = 17
				GENERATE_RANDOM_INT_IN_RANGE 0 6 des2_speech_control_flag
				des2_random_last_label = des2_speech_control_flag + 1 
				GOSUB des2_dialogue_setup
			ENDIF
		ENDIF	

		IF des2_speech_goals = 17 //carl is out of car
			IF NOT IS_CHAR_IN_CAR scplayer des2_transporter
				IF des2_speech_control_flag < des2_last_label
					GOSUB des2_loading_dialogue
					GOSUB des2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB des2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag] 
						des2_slot1 = 0
						des2_slot2 = 0
					ENDIF
				ELSE
					PRINT ( TOR1_11 ) 4000 1 // Get into the truck and drive it back to the garage.
					des2_speech_goals = 18
				ENDIF
			ENDIF
			IF IS_CHAR_SITTING_IN_CAR scplayer des2_transporter
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
				des2_speech_goals = 19
				des2_speech_control_flag = 0
				CLEAR_PRINTS 
				//GOSUB des2_dialogue_setup
			ENDIF
		ENDIF

		IF des2_speech_goals = 18 //carl has been out of car and has returned
			IF IS_CHAR_SITTING_IN_CAR scplayer des2_transporter 
				des2_speech_goals = 19
				des2_speech_control_flag = 0
				CLEAR_PRINTS 
				//GOSUB des2_dialogue_setup
			ENDIF
		ENDIF

		IF des2_speech_goals = 19 //where player has returned to the car
			IF IS_CHAR_SITTING_IN_CAR scplayer des2_transporter 	
				timerb = 0 
				des2_speech_goals = des2_storing_speech_goals_number
				des2_speech_control_flag = des2_storing_speech_control_number
				GOSUB des2_dialogue_setup
				IF des2_storing_speech_goals_number = 0
					IF des2_cab_attached = 1
						PRINT ( TOR1_06 ) 4000 1 //Drive the truck back to the ~y~garage~s~.
					ELSE
						PRINT ( TOR1_21 ) 4000 1 //Reconnect to the ~b~cab~s~.
					ENDIF		 
				ENDIF
			ENDIF
			IF NOT IS_CHAR_IN_CAR scplayer des2_transporter
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
				des2_speech_goals = 17
				GENERATE_RANDOM_INT_IN_RANGE 0 6 des2_speech_control_flag
				des2_random_last_label = des2_speech_control_flag + 1 	
				GOSUB des2_dialogue_setup
			ENDIF
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_dialogue_setup://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF des2_speech_goals = 1
	$des2_print_label[0] = &DES2_AA // I got here as fast as I could, CJ.
	$des2_print_label[1] = &DES2_AB // You sure did, how did you know I needed help?
	$des2_print_label[2] = &DES2_AC // Man, you losing it, man.
	$des2_print_label[3] = &DES2_AD // You phoned me not half an hour ago, man!
	$des2_print_label[4] = &DES2_AE // I did? I mean, oh yeah, I did.
	$des2_print_label[5] = &DES2_AF // Sorry, dude, a lot on my mind.
	
	des2_audio_label[0] = SOUND_DES2_AA 
	des2_audio_label[1] = SOUND_DES2_AB 
	des2_audio_label[2] = SOUND_DES2_AC 
	des2_audio_label[3] = SOUND_DES2_AD 
	des2_audio_label[4] = SOUND_DES2_AE 
	des2_audio_label[5] = SOUND_DES2_AF 
	des2_last_label = 6
ENDIF

IF des2_speech_goals = 2
	$des2_print_label[0] = &DES2_BA // We need to 'jack a truck on the freeway. 
	$des2_print_label[1] = &DES2_BB // It's headed to San Fierro.

	des2_audio_label[0] = SOUND_DES2_BA 
	des2_audio_label[1] = SOUND_DES2_BB 
	des2_last_label = 2
ENDIF

IF des2_speech_goals = 3
	$des2_print_label[0] = &DES2_BC // We can get on the freeway here, holmes.
	des2_audio_label[0] = SOUND_DES2_BC 
	des2_last_label = 1
ENDIF

IF des2_speech_goals = 4
	$des2_print_label[0] = &DES2_CA // Man, you crazy, this is the rail bridge! 
	$des2_print_label[1] = &DES2_CB // The road bridge is over there!
	$des2_print_label[2] = &DES2_CC // We jacking a train, holmes?

	des2_audio_label[0] = SOUND_DES2_CA 
	des2_audio_label[1] = SOUND_DES2_CB 
	des2_audio_label[2] = SOUND_DES2_CC 
	des2_last_label = des2_random_last_label
ENDIF

IF des2_speech_goals = 5
	$des2_print_label[0] = &DES2_DA // You missed the turnpike, holmes! 
	$des2_print_label[1] = &DES2_DB // The freeway's back there!
	$des2_print_label[2] = &DES2_DC // Dude, you missed the turn!

	des2_audio_label[0] = SOUND_DES2_DA 
	des2_audio_label[1] = SOUND_DES2_DB 
	des2_audio_label[2] = SOUND_DES2_DC 
	des2_last_label = des2_random_last_label
ENDIF

IF des2_speech_goals = 6
	$des2_print_label[0] = &DES2_EA // There's the rig up ahead!	
	$des2_print_label[1] = &DES2_EB // What's the plan?
	$des2_print_label[2] = &DES2_EC // I'm gonna pull alongside and you're gonna hop on board!
	$des2_print_label[3] = &DES2_ED // Oh shit, you did not mention this on the phone.
	$des2_print_label[4] = &DES2_EE // It'll be a walk in the park!
	$des2_print_label[5] = &DES2_EF // Tell Kendl I love her!

	des2_audio_label[0] = SOUND_DES2_EA 
	des2_audio_label[1] = SOUND_DES2_EB 
	des2_audio_label[2] = SOUND_DES2_EC 
	des2_audio_label[3] = SOUND_DES2_ED 
	des2_audio_label[4] = SOUND_DES2_EE 
	des2_audio_label[5] = SOUND_DES2_EF 
	des2_last_label = des2_random_last_label	   
ENDIF

IF des2_speech_goals = 7
	$des2_print_label[0] = &DES2_FA // Closer, CJ, closer! 
	$des2_print_label[1] = &DES2_FB // Just a little closer!
	$des2_print_label[2] = &DES2_FC // I am not a kangaroo, holmes, get closer!

	des2_audio_label[0] = SOUND_DES2_FA 
	des2_audio_label[1] = SOUND_DES2_FB 
	des2_audio_label[2] = SOUND_DES2_FC 
	des2_last_label = des2_random_last_label
ENDIF

/*
IF des2_speech_goals = 8
	$des2_print_label[0] = &DES2_GA // Hold her steady, CJ! 
	$des2_print_label[1] = &DES2_GB // Steady... steady!
	$des2_print_label[2] = &DES2_GC // Just let me get my balance!

	des2_audio_label[0] = SOUND_DES2_GA 
	des2_audio_label[1] = SOUND_DES2_GB 
	des2_audio_label[2] = SOUND_DES2_GC 
	des2_last_label = des2_random_last_label
ENDIF
*/
IF des2_speech_goals = 9
	$des2_print_label[0] = &DES2_HA // Speed up, CJ! 
	$des2_print_label[1] = &DES2_HB // Match the truck's speed!
	$des2_print_label[2] = &DES2_HC // Keep level, CJ, keep it level!

	des2_audio_label[0] = SOUND_DES2_HA 
	des2_audio_label[1] = SOUND_DES2_HB 
	des2_audio_label[2] = SOUND_DES2_HC 
	des2_last_label = des2_random_last_label
ENDIF

IF des2_speech_goals = 10
	$des2_print_label[0] = &DES2_JA // Slow down, CJ! 
	$des2_print_label[1] = &DES2_JB // Just a bit slower!
	$des2_print_label[2] = &DES2_JC // Calm it down, CJ, you're too fast!

	des2_audio_label[0] = SOUND_DES2_JA 
	des2_audio_label[1] = SOUND_DES2_JB 
	des2_audio_label[2] = SOUND_DES2_JC 
	des2_last_label = des2_random_last_label
ENDIF

IF des2_speech_goals = 11
	$des2_print_label[0] = &DES2_KA // Ok, CJ, let's get this rig back to the garage!
	//$des2_print_label[1] = &DES2_KB // Where we going to take this thing?
	//$des2_print_label[2] = &DES2_KC // There's a haulage firm over in Whetstone county.
	//$des2_print_label[3] = &DES2_KD // We can take it there and get some cash for it.
	//$des2_print_label[4] = &DES2_KE // Ok, Drop me off and I'll make my own way back to the garage.

	des2_audio_label[0] = SOUND_DES2_KA 
	//des2_audio_label[1] = SOUND_DES2_KB 
	//des2_audio_label[2] = SOUND_DES2_KC 
	//des2_audio_label[3] = SOUND_DES2_KC 
	//des2_audio_label[4] = SOUND_DES2_KC 
	des2_last_label = 1
ENDIF

IF des2_speech_goals = 12
	$des2_print_label[0] = &DES2_LA // See you later, holmes!
	des2_audio_label[0] = SOUND_DES2_LA 
	des2_last_label = 1
ENDIF

IF des2_speech_goals = 14
	$des2_print_label[0] = &CESX_BA // Wait up, CJ!
	$des2_print_label[1] = &CESX_BB // Hang ten, CJ!
	$des2_print_label[2] = &CESX_BC // Hold up!
	$des2_print_label[3] = &CESX_BD // Slow down, Carl!

	des2_audio_label[0] = SOUND_CESX_BA 
	des2_audio_label[1] = SOUND_CESX_BB 
	des2_audio_label[2] = SOUND_CESX_BC 
	des2_audio_label[3] = SOUND_CESX_BD 
 	des2_last_label = des2_random_last_label 
ENDIF

IF des2_speech_goals = 17
	$des2_print_label[0] = &CESX_AA // Get in, CJ, get in!
	$des2_print_label[1] = &CESX_AB // Hop in, holmes!
	$des2_print_label[2] = &CESX_AC // All aboard - heh heh!
	$des2_print_label[3] = &CESX_AD // You got shotgun, CJ!
	$des2_print_label[4] = &CESX_AE // Get in the car, CJ!
	$des2_print_label[5] = &CESX_AF // Jump in, CJ, quick!

	des2_audio_label[0] = SOUND_CESX_AA 
	des2_audio_label[1] = SOUND_CESX_AB 
	des2_audio_label[2] = SOUND_CESX_AC 
	des2_audio_label[3] = SOUND_CESX_AD 
	des2_audio_label[4] = SOUND_CESX_AE 
	des2_audio_label[5] = SOUND_CESX_AF 
 	des2_last_label = des2_random_last_label 
ENDIF

des2_slot_load = des2_speech_control_flag
des2_slot1 = 0
des2_slot2 = 0
des2_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_loading_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF des2_slot_load < des2_last_label 
	//slot 1
	IF des2_slot1 = 0
		LOAD_MISSION_AUDIO 1 des2_audio_label[des2_slot_load]  
		des2_slot_load ++ 
		des2_slot1 = 1
	ENDIF

	//slot 2		    
	IF des2_slot2 = 0
		LOAD_MISSION_AUDIO 2 des2_audio_label[des2_slot_load]  
		des2_slot_load ++ 
		des2_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_playing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF des2_play_which_slot = 1 
	IF des2_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $des2_print_label[des2_speech_control_flag] ) 4500 1 
			des2_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF des2_play_which_slot = 2 
	IF des2_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $des2_print_label[des2_speech_control_flag] ) 4500 1 
			des2_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
des2_finishing_dialogue:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF des2_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
		des2_speech_control_flag ++		
		des2_play_which_slot = 2
		des2_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF des2_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $des2_print_label[des2_speech_control_flag]
		des2_speech_control_flag ++		
		des2_play_which_slot = 1
		des2_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

}
  