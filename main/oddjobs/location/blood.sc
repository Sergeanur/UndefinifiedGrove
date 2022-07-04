MISSION_START
// *****************************************************************************************
// ***************************** blood ************************************ 
// *****************************************************************************************
// *****************************************************************************************
// ***                                                                                   ***
// *****************************************************************************************
SCRIPT_NAME blood
// Mission start stuff
GOSUB mission_start_blood
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_blood_failed
ENDIF
GOSUB mission_cleanup_blood
MISSION_END
{
// Variables for mission

//people
LVAR_INT blood_player_car blood_car[16] blood_driver[16] blood_passenger[16] blood_player2 
LVAR_INT blood_rnd_car[3] 
LVAR_INT blood_upgrade blood_this_car 
LVAR_INT blood_checkpoint

//blips
LVAR_INT blood_blip  
																					
//flags
LVAR_INT blood_goals blood_speech_flag
LVAR_INT blood_car_flag blood_car_flag2 
LVAR_INT blood_closest_car_index
//LVAR_INT blood_players_current_time 	 
//LVAR_INT blood_time_to_beat 
LVAR_INT blood_players_car_health 
LVAR_INT blood_top_left blood_top_right blood_bottom_left blood_bottom_right
LVAR_INT blood_no_of_seats
//LVAR_INT blood_car_dead_flag 
LVAR_INT blood_random_garage_flag
LVAR_INT blood_bullets
LVAR_INT blood_model
LVAR_INT blood_class
LVAR_FLOAT blood_spin_upgrade
LVAR_INT blood_no_plates_flag blood_no_plates


LVAR_INT blood_longest_time 
LVAR_INT blood_window
LVAR_INT blood_cars_killed
LVAR_INT blood_player_damaged_car[16]
LVAR_INT blood_rnd_time blood_audio_flag blood_sound_flag blood_sound[23] 


//speech

									  
//coords
LVAR_FLOAT blood_x blood_y blood_z blood_heading 
LVAR_FLOAT blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 

LVAR_FLOAT blood_this_car_x blood_this_car_y 
LVAR_FLOAT blood_other_car_x blood_other_car_y 
LVAR_FLOAT blood_closest_distance blood_current_distance

LVAR_FLOAT blood_reposition_car_x blood_reposition_car_y blood_reposition_car_z 
LVAR_FLOAT blood_upgrade_x blood_upgrade_y blood_upgrade_z

LVAR_FLOAT blood_rnd_1x blood_rnd_1y blood_rnd_1z 
LVAR_FLOAT blood_rnd_2x blood_rnd_2y blood_rnd_2z 
LVAR_FLOAT blood_rnd_3x blood_rnd_3y blood_rnd_3z 
LVAR_FLOAT blood_rnd_4x blood_rnd_4y blood_rnd_4z 


//sequences/decision makers/threat lists/attractors/groups
LVAR_INT blood_empty_ped_decision_maker

//debug

// ****************************************Mission Start************************************
mission_start_blood:

flag_player_on_mission = 1
IF blood_passed_once = 0 
	REGISTER_MISSION_GIVEN
ENDIF
LOAD_MISSION_TEXT blood

IF flag_player_on_mission = 0 
	ADD_BLIP_FOR_COORD blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z blood_blip
	CREATE_OBJECT bb_pickup blood_upgrade_x blood_upgrade_y blood_upgrade_z blood_upgrade
	CREATE_CAR BLOODRA blood_x blood_y blood_z blood_rnd_car[0]
	CREATE_CAR BLOODRA blood_x blood_y blood_z blood_rnd_car[1]
	CREATE_CAR BLOODRA blood_x blood_y blood_z blood_rnd_car[2]
	CREATE_CAR BLOODRA blood_x blood_y blood_z blood_closest_car_index
ENDIF
SET_FADING_COLOUR 0 0 0
CLEAR_PRINTS
CLEAR_THIS_PRINT_BIG_NOW 1
WAIT 0
// *************************************Set Flags/variables*********************************
blood_goals = 0 
blood_speech_flag = 0


IF blood_passed_once = 0
	blood_best_time = 60
	blood_time_to_beat = 60000 
ENDIF

blood_car_flag = 0
blood_car_flag2 = 0	   

blood_closest_car_index = 0
blood_players_current_time = 30000

blood_players_car_health = 0

blood_top_left = 0 
blood_top_right = 0 
blood_bottom_left = 0 
blood_bottom_right = 0

blood_no_of_seats = 0

//blood_car_dead_flag = -1     ////// -1 means no cars are dead
blood_random_garage_flag = -1 ///// -1 means no flag was returned


blood_x = -1280.9  
blood_y = 994.8 
blood_z = 1037.0
blood_heading = 90.0   
blood_chckpnt_x = 0.0  
blood_chckpnt_y = 0.0  
blood_chckpnt_z = 0.0 

blood_this_car_x = 0.0 
blood_this_car_y = 0.0 
blood_other_car_x = 0.0 
blood_other_car_y = 0.0 
blood_closest_distance = 0.0 
blood_current_distance = 0.0

blood_reposition_car_x = 0.0 
blood_reposition_car_y = 0.0 
blood_reposition_car_z = 0.0 
blood_upgrade_x = 0.0 
blood_upgrade_y = 0.0 
blood_upgrade_z = 0.0

blood_rnd_1x = 0.0 
blood_rnd_1y = 0.0 
blood_rnd_1z = 0.0
blood_rnd_2x = 0.0 
blood_rnd_2y = 0.0 
blood_rnd_2z = 0.0
blood_rnd_3x = 0.0 
blood_rnd_3y = 0.0 
blood_rnd_3z = 0.0
blood_rnd_4x = 0.0 
blood_rnd_4y = 0.0 
blood_rnd_4z = 0.0

blood_bullets = 0

blood_cars_killed = 0

blood_model = 0
blood_class = 0

blood_player_damaged_car[0] = 0
blood_player_damaged_car[1] = 0
blood_player_damaged_car[2] = 0
blood_player_damaged_car[3] = 0
blood_player_damaged_car[4] = 0
blood_player_damaged_car[5] = 0
blood_player_damaged_car[6] = 0
blood_player_damaged_car[7] = 0
blood_player_damaged_car[8] = 0
blood_player_damaged_car[9] = 0
blood_player_damaged_car[10] = 0
blood_player_damaged_car[11] = 0
blood_player_damaged_car[12] = 0
blood_player_damaged_car[13] = 0
blood_player_damaged_car[14] = 0
blood_player_damaged_car[15] = 0

blood_spin_upgrade = 0.0

blood_sound[0] = SOUND_CROWD_AWWS 
blood_sound[1] = SOUND_CROWD_CHEERS 
blood_sound[2] = SOUND_CROWD_CHEERS_BIG 
blood_sound[3] = SOUND_CROWD_AWWS 
blood_sound[4] = SOUND_CROWD_CHEERS 
blood_sound[5] = SOUND_CROWD_CHEERS_BIG 
blood_sound[6] = SOUND_CROWD_AWWS 
blood_sound[7] = SOUND_CROWD_CHEERS 
blood_sound[8] = SOUND_CROWD_CHEERS_BIG 
blood_sound[9] = SOUND_CROWD_AWWS 
blood_sound[10] = SOUND_CROWD_CHEERS 
blood_sound[11] = SOUND_CROWD_CHEERS_BIG 
blood_sound[12] = SOUND_CROWD_AWWS 
blood_sound[13] = SOUND_CROWD_CHEERS 
blood_sound[14] = SOUND_CROWD_CHEERS_BIG 
blood_sound[15] = SOUND_CROWD_AWWS 
blood_sound[16] = SOUND_CROWD_CHEERS 
blood_sound[17] = SOUND_CROWD_CHEERS_BIG 
blood_sound[18] = SOUND_CROWD_AWWS 
blood_sound[19] = SOUND_CROWD_CHEERS 
blood_sound[20] = SOUND_CROWD_CHEERS_BIG 
blood_sound[21] = SOUND_CROWD_AWWS 
blood_sound[22] = SOUND_CROWD_CHEERS 

blood_no_plates_flag = 0
blood_no_plates = 0



// ****************************************START OF CUTSCENE********************************
// ******************************************END OF CUTSCENE********************************
WAIT 0
//------------------REQUEST_MODELS ------------------------------


VAR_INT blood_players_current_time blood_time_to_beat blood_best_time

/*
/////////////DEBUG/////////////////
VIEW_INTEGER_VARIABLE blood_best_time blood_best_time  
VIEW_INTEGER_VARIABLE blood_players_current_time blood_players_current_time
VIEW_INTEGER_VARIABLE blood_time_to_beat blood_time_to_beat 
VIEW_INTEGER_VARIABLE blood_attempt blood_attempt
/////////////DEBUG/////////////////
*/

DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 

REQUEST_MODEL BLOODRA
REQUEST_MODEL STALLION
REQUEST_MODEL mp5lng
REQUEST_MODEL bb_pickup

LOAD_MISSION_AUDIO 4 SOUND_BANK_AIR_HORN
WHILE NOT HAS_MISSION_AUDIO_LOADED 4
	WAIT 0 
ENDWHILE 

LOAD_ALL_MODELS_NOW

OVERRIDE_NEXT_RESTART -2126.3 -440.6 34.5 82.7 

FORCE_ALL_VEHICLE_LIGHTS_OFF TRUE

//suppress all big vehicles
SUPPRESS_CAR_MODEL linerun
SUPPRESS_CAR_MODEL trash
SUPPRESS_CAR_MODEL stretch
SUPPRESS_CAR_MODEL pony
SUPPRESS_CAR_MODEL mule
SUPPRESS_CAR_MODEL moonbeam

SUPPRESS_CAR_MODEL mrwhoop
SUPPRESS_CAR_MODEL securica
SUPPRESS_CAR_MODEL bus
SUPPRESS_CAR_MODEL barracks
SUPPRESS_CAR_MODEL coach
SUPPRESS_CAR_MODEL rumpo
SUPPRESS_CAR_MODEL romero
SUPPRESS_CAR_MODEL packer
SUPPRESS_CAR_MODEL monster
SUPPRESS_CAR_MODEL flatbed
SUPPRESS_CAR_MODEL yankee
SUPPRESS_CAR_MODEL solair
SUPPRESS_CAR_MODEL patriot
SUPPRESS_CAR_MODEL burrito
SUPPRESS_CAR_MODEL camper
SUPPRESS_CAR_MODEL dozer
SUPPRESS_CAR_MODEL rancher
SUPPRESS_CAR_MODEL boxville
SUPPRESS_CAR_MODEL benson
SUPPRESS_CAR_MODEL journey
SUPPRESS_CAR_MODEL petro
SUPPRESS_CAR_MODEL rdtrain
SUPPRESS_CAR_MODEL cement
SUPPRESS_CAR_MODEL towtruck
SUPPRESS_CAR_MODEL dft30
SUPPRESS_CAR_MODEL newsvan
SUPPRESS_CAR_MODEL hotdog

SET_PLAYER_CONTROL player1 OFF
COPY_CHAR_DECISION_MAKER DM_PED_EMPTY blood_empty_ped_decision_maker 

HIDE_ALL_FRONTEND_BLIPS TRUE
SET_FREE_HEALTH_CARE player1 TRUE
SWITCH_RUBBISH OFF
SET_CAR_DENSITY_MULTIPLIER 0.0 
SET_PED_DENSITY_MULTIPLIER 0.0
SHUT_PLAYER_UP player1 TRUE

SET_CHAR_COORDINATES scplayer -1398.461 995.641 1024.462
SET_AREA_VISIBLE 15
SET_CHAR_AREA_VISIBLE scplayer 15
SET_EXTRA_COLOURS 2 FALSE   

REQUEST_COLLISION blood_x blood_y 
LOAD_SCENE blood_x blood_y blood_z 

CLEAR_AREA -1280.9 994.8 1037.0 300.0 TRUE  

LOAD_SCENE_IN_DIRECTION -1280.8 994.7 1036.0 90.0

GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_MP5 blood_bullets
GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MP5 30000

///setting up the cars
blood_no_plates = BLOODRA
GOSUB blood_my_number_plates
CREATE_CAR BLOODRA blood_x blood_y blood_z blood_player_car
SET_LOAD_COLLISION_FOR_CAR_FLAG blood_player_car TRUE 
SET_VEHICLE_AREA_VISIBLE blood_player_car 15
SET_CAR_HEADING blood_player_car blood_heading  						
LOCK_CAR_DOORS blood_player_car CARLOCK_LOCKED_PLAYER_INSIDE
WARP_CHAR_INTO_CAR scplayer blood_player_car 
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MP5
SET_CAR_STRONG blood_player_car TRUE
SET_UPSIDEDOWN_CAR_NOT_DAMAGED blood_player_car TRUE
SET_CAN_BURST_CAR_TYRES blood_player_car FALSE
SET_CAR_HEALTH blood_player_car 1249  

CREATE_RANDOM_CHAR_AS_PASSENGER blood_player_car 0 blood_player2
SET_CHAR_AREA_VISIBLE blood_player2 15 
SET_CHAR_DECISION_MAKER blood_player2 blood_empty_ped_decision_maker 
GIVE_WEAPON_TO_CHAR blood_player2 WEAPONTYPE_MP5 30000
SET_CHAR_PROOFS blood_player2 TRUE FALSE FALSE FALSE FALSE 
//SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH blood_player2 TRUE 

WHILE blood_car_flag < 16
	WAIT 0   

	GOSUB blood_setting_up_cars
	GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE blood_model blood_class
	IF blood_model >= 0 
		blood_no_plates = blood_model
		GOSUB blood_my_number_plates
		CREATE_CAR blood_model blood_x blood_y blood_z blood_car[blood_car_flag]
	ENDIF
	IF blood_model = -1
		blood_no_plates = STALLION
		GOSUB blood_my_number_plates
		CREATE_CAR STALLION blood_x blood_y blood_z blood_car[blood_car_flag]
	ENDIF
	IF NOT IS_CAR_DEAD blood_car[blood_car_flag] 
		SET_CAR_HEADING blood_car[blood_car_flag] blood_heading   
		SET_LOAD_COLLISION_FOR_CAR_FLAG blood_car[blood_car_flag] TRUE
	
		SET_CAR_STRAIGHT_LINE_DISTANCE blood_car[blood_car_flag] 255
		SET_CAN_BURST_CAR_TYRES blood_car[blood_car_flag] FALSE
	
		CREATE_RANDOM_CHAR_AS_DRIVER blood_car[blood_car_flag] blood_driver[blood_car_flag]
		SET_CHAR_AREA_VISIBLE blood_driver[blood_car_flag] 15 
		SET_CHAR_DECISION_MAKER blood_driver[blood_car_flag] blood_empty_ped_decision_maker 
		SET_CHAR_PROOFS blood_driver[blood_car_flag] TRUE FALSE FALSE FALSE FALSE 
		
		//SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH blood_driver[blood_car_flag] TRUE 
		GET_MAXIMUM_NUMBER_OF_PASSENGERS blood_car[blood_car_flag] blood_no_of_seats 
		IF blood_no_of_seats > 0 
			CREATE_RANDOM_CHAR_AS_PASSENGER blood_car[blood_car_flag] 0 blood_passenger[blood_car_flag] 
			SET_CHAR_DECISION_MAKER blood_passenger[blood_car_flag] blood_empty_ped_decision_maker 
			SET_CHAR_PROOFS blood_passenger[blood_car_flag] TRUE FALSE FALSE FALSE FALSE 
			//SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH blood_passenger[blood_car_flag] TRUE 
			GIVE_WEAPON_TO_CHAR blood_passenger[blood_car_flag] WEAPONTYPE_MP5 30000
		ENDIF
		IF IS_CHAR_ON_ANY_BIKE blood_driver[blood_car_flag]
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE blood_driver[blood_car_flag] KNOCKOFFBIKE_NEVER
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE blood_passenger[blood_car_flag] KNOCKOFFBIKE_NEVER
		ENDIF
		ADD_STUCK_CAR_CHECK blood_car[blood_car_flag] 1.0 3000
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED blood_car[blood_car_flag] TRUE
		SET_VEHICLE_AREA_VISIBLE blood_car[blood_car_flag] 15 
	ENDIF
	blood_car_flag ++
ENDWHILE

  

// ******************************************START OF Demolition Derby*******************************************
SWITCH_WIDESCREEN ON

//SET_FIXED_CAMERA_POSITION -1266.5 1042.8 1054.3 0.0 0.0 0.0 
//POINT_CAMERA_AT_POINT -1339.9 1009.8 1028.0 JUMP_CUT

SET_FIXED_CAMERA_POSITION -1273.2 992.4 1038.5 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1307.9 995.3 1034.2 JUMP_CUT

CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE -1432.6 1016.1 1024.1 0.0 0.0 0.0 6.0 blood_checkpoint

SET_FADING_COLOUR 0 0 0
WAIT 500
DO_FADE 1500 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SKIP_CUTSCENE_START
PRINT_NOW ( BLOD_10 ) 5000 1 //The overall time starts at 30 seconds and counts down.
timera = 0
WHILE timera < 5000
	WAIT 0 	
ENDWHILE

SET_FIXED_CAMERA_POSITION -1441.9 1054.6 1040.5 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1432.0 1012.6 1034.7 JUMP_CUT
PRINT_NOW ( BLOD_01 ) 5000 1 //Drive through the checkpoints to increase your overall time.
timera = 0
WHILE timera < 5000
	WAIT 0 	
ENDWHILE


SET_FIXED_CAMERA_POSITION -1464.6 943.6 1036.1 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1436.1 923.4 1040.2 JUMP_CUT
IF blood_passed_once = 0 
	PRINT_NOW ( BLOD_11 ) 5000 1 //The current Target Time to win the bloodbowl is 1 min.
ELSE
	PRINT_WITH_NUMBER_NOW ( BLOD_12 ) blood_attempt 5000 1 //The current Target Time to win the bloodbowl is ~1~ min.
ENDIF
timera = 0
WHILE timera < 5000
	WAIT 0 	
ENDWHILE

SET_FIXED_CAMERA_POSITION -1492.7 1029.5 1038.4 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1539.1 1032.3 1038.9 JUMP_CUT
PRINT_NOW ( BLOD_03 ) 5000 1 //Get your overall time above the Target Time to win!
timera = 0
WHILE timera < 5000
	WAIT 0 	
ENDWHILE

SET_FIXED_CAMERA_POSITION -1314.1 1040.6 1038.7 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1373.1 1047.2 1034.5 JUMP_CUT
PRINT_NOW ( BLOD_02 ) 5000 1 //You will lose if your overall time reaches zero.
timera = 0
WHILE timera < 5000
	WAIT 0 	
ENDWHILE

CREATE_OBJECT bb_pickup -1345.2 978.7 1024.9 blood_upgrade
SET_OBJECT_AREA_VISIBLE blood_upgrade 15
 
SET_FIXED_CAMERA_POSITION -1342.1 975.2 1026.6 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -1345.2 979.7 1026.4 JUMP_CUT
PRINT_NOW ( SPANNER ) 5000 1 //Drive over pickups to repair your car!

timera = 0
WHILE timera < 5000
	WAIT 0 	
	IF DOES_OBJECT_EXIST blood_upgrade
		IF blood_spin_upgrade < 358.0		
			blood_spin_upgrade += 3.0	
			SET_OBJECT_HEADING blood_upgrade blood_spin_upgrade
		ELSE
			blood_spin_upgrade = 0.0
		ENDIF	
	ENDIF
ENDWHILE

SKIP_CUTSCENE_END

DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
	IF DOES_OBJECT_EXIST blood_upgrade
		IF blood_spin_upgrade < 358.0		
			blood_spin_upgrade += 3.0	
			SET_OBJECT_HEADING blood_upgrade blood_spin_upgrade
		ELSE
			blood_spin_upgrade = 0.0
		ENDIF	
	ENDIF
ENDWHILE


DELETE_CHECKPOINT blood_checkpoint
blood_chckpnt_x = -1432.6 
blood_chckpnt_y = 1016.1 
blood_chckpnt_z = 1024.1
CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 0.0 0.0 0.0 6.0 blood_checkpoint
ADD_BLIP_FOR_COORD blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z blood_blip

DELETE_OBJECT blood_upgrade
GOSUB blood_upgrade_location
CLEAR_PRINTS
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
DO_FADE 500 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
// ****************************************** END OF Demolition Derby *******************************************

PRINT_BIG ( RACE2 ) 1100 4 //"THREE"
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
WAIT 1000
PRINT_BIG ( RACE3 ) 1100 4 //"TWO"
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
WAIT 1000
PRINT_BIG ( RACE4 ) 1100 4 //"ONE"
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_321
WAIT 1000
PRINT_BIG ( RACE5 ) 800 4  //"GO!"
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_AIR_HORN
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_RACE_GO


WAIT 800



CLEAR_PRINTS
SET_PLAYER_CONTROL player1 ON

DISPLAY_ONSCREEN_TIMER blood_players_current_time TIMER_DOWN



blood_car_flag = 0
WHILE blood_car_flag < 16
	WAIT 0   

	IF NOT IS_CAR_DEAD blood_car[blood_car_flag]  
		IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag]
			SET_CAR_FORWARD_SPEED blood_car[blood_car_flag] 10.0 
			SET_CAR_CRUISE_SPEED blood_car[blood_car_flag] 100.0
			TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_GOFORWARD 5000
		ENDIF
	ENDIF

	blood_car_flag ++
ENDWHILE




mission_blood_loop:

WAIT 0

	CLEAR_WANTED_LEVEL player1
		
	// debug to get to menu at end of race.
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_blood_passed  
	ENDIF


	IF blood_goals = 0 

		IF NOT IS_CAR_DEAD blood_player_car
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// WHERE IS PLAYER ////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//if blood_top_left = 0 then player is OUTside area
			//if blood_top_left = 1 then player is INtside area

			
			//top left of bloodring
			IF IS_CHAR_IN_AREA_2D scplayer -1530.0 995.0 -1384.0 923.0 FALSE
				IF blood_top_left = 0
					IF NOT IS_CHAR_DEAD blood_driver[1] 
						CLEAR_CHAR_TASKS blood_driver[1]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[2]
						CLEAR_CHAR_TASKS blood_driver[2]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[0]
						CLEAR_CHAR_TASKS blood_passenger[0]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[1]
						CLEAR_CHAR_TASKS blood_passenger[1]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[2]
						CLEAR_CHAR_TASKS blood_passenger[2]
					ENDIF
					blood_top_left = 1
				ENDIF
			ELSE
				IF blood_top_left = 1
					IF NOT IS_CHAR_DEAD blood_driver[1] 
						CLEAR_CHAR_TASKS blood_driver[1]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[2]
						CLEAR_CHAR_TASKS blood_driver[2]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[0]
						CLEAR_CHAR_TASKS blood_passenger[0]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[1]
						CLEAR_CHAR_TASKS blood_passenger[1]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[2]
						CLEAR_CHAR_TASKS blood_passenger[2]
					ENDIF
					blood_top_left = 0
				ENDIF
			ENDIF

			//top right of bloodring
			IF IS_CHAR_IN_AREA_2D scplayer -1530.0 995.0 -1384.0 1076.0 FALSE
				IF blood_top_right = 0
					IF NOT IS_CHAR_DEAD blood_driver[4] 
						CLEAR_CHAR_TASKS blood_driver[4]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[5]
						CLEAR_CHAR_TASKS blood_driver[5]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[3]
						CLEAR_CHAR_TASKS blood_passenger[3]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[4]
						CLEAR_CHAR_TASKS blood_passenger[4]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[5]
						CLEAR_CHAR_TASKS blood_passenger[5]
					ENDIF
					blood_top_right = 1
				ENDIF
			ELSE
				IF blood_top_right = 1
					IF NOT IS_CHAR_DEAD blood_driver[4] 
						CLEAR_CHAR_TASKS blood_driver[4]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[5]
						CLEAR_CHAR_TASKS blood_driver[5]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[3]
						CLEAR_CHAR_TASKS blood_passenger[3]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[4]
						CLEAR_CHAR_TASKS blood_passenger[4]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[5]
						CLEAR_CHAR_TASKS blood_passenger[5]
					ENDIF
					blood_top_right = 0
				ENDIF
			ENDIF

			//Bottom Left of bloodring
			IF IS_CHAR_IN_AREA_2D scplayer -1265.0 995.0 -1384.0 923.0 FALSE 
				IF blood_bottom_left = 0
					IF NOT IS_CHAR_DEAD blood_driver[7] 
						CLEAR_CHAR_TASKS blood_driver[7]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[8]
						CLEAR_CHAR_TASKS blood_driver[8]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[6]
						CLEAR_CHAR_TASKS blood_passenger[6]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[7]
						CLEAR_CHAR_TASKS blood_passenger[7]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[8]
						CLEAR_CHAR_TASKS blood_passenger[8]
					ENDIF
					blood_bottom_left = 1
				ENDIF
			ELSE
				IF blood_bottom_left = 1
					IF NOT IS_CHAR_DEAD blood_driver[7] 
						CLEAR_CHAR_TASKS blood_driver[7]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[8]
						CLEAR_CHAR_TASKS blood_driver[8]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[6]
						CLEAR_CHAR_TASKS blood_passenger[6]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[7]
						CLEAR_CHAR_TASKS blood_passenger[7]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[8]
						CLEAR_CHAR_TASKS blood_passenger[8]
					ENDIF
					blood_bottom_left = 0
				ENDIF
			ENDIF

			//Bottom Right of bloodring
			IF IS_CHAR_IN_AREA_2D scplayer -1265.0 995.0 -1384.0 1076.0 FALSE 
				IF blood_bottom_right = 0
					IF NOT IS_CHAR_DEAD blood_driver[10] 
						CLEAR_CHAR_TASKS blood_driver[10]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[11]
						CLEAR_CHAR_TASKS blood_driver[11]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[9]
						CLEAR_CHAR_TASKS blood_passenger[9]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[10]
						CLEAR_CHAR_TASKS blood_passenger[10]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[11]
						CLEAR_CHAR_TASKS blood_passenger[11]
					ENDIF
					blood_bottom_right = 1
				ENDIF
			ELSE
				IF blood_bottom_right = 1
					IF NOT IS_CHAR_DEAD blood_driver[10] 
						CLEAR_CHAR_TASKS blood_driver[10]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_driver[11]
						CLEAR_CHAR_TASKS blood_driver[11]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[9]
						CLEAR_CHAR_TASKS blood_passenger[9]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[10]
						CLEAR_CHAR_TASKS blood_passenger[10]
					ENDIF
					IF NOT IS_CHAR_DEAD blood_passenger[11]
						CLEAR_CHAR_TASKS blood_passenger[11]
					ENDIF
					blood_bottom_right = 0
				ENDIF
			ENDIF


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// DEALING WITH THE OTHER COMPETITORS /////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//// CAR 0 - Top Left Leader
			//// CAR 1 - Top Left 1
			//// CAR 2 - Top Left 2

			//// CAR 3 - Top Right Leader
			//// CAR 4 - Top Right 1
			//// CAR 5 - Top Right 2
			
			//// CAR 6 - Bottom Left Leader
			//// CAR 7 - Bottom Left 1
			//// CAR 8 - Bottom Left 2
			
			//// CAR 9 - Bottom Right Leader
			//// CAR 10	- Bottom Right 1
			//// CAR 11	- Bottom Right 2
			
			//// CAR 12	- Block Player
			//// CAR 13	- Block Player
			
			//// CAR 14	- Chase Checkpoints
			//// CAR 15	- Chase Checkpoints


			IF blood_car_flag < 16 

				// TOP LEFT 
				//car 0
				IF blood_car_flag = 0
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver 
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag] 
							
							
								IF blood_top_left = 0  //player is OUTside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_DRIVE_TO_COORD task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											GENERATE_RANDOM_FLOAT_IN_RANGE -1530.0 -1384.0 blood_rnd_1x
											GENERATE_RANDOM_FLOAT_IN_RANGE 995.0 923.0 blood_rnd_1y
											GET_GROUND_Z_FOR_3D_COORD blood_rnd_1x blood_rnd_1y 1078.7 blood_rnd_1z
											IF blood_rnd_1z < 1034.1
												IF blood_rnd_1z > 1022.8
													TASK_CAR_DRIVE_TO_COORD blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_rnd_1x blood_rnd_1y blood_rnd_1z 100.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								IF blood_top_left = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF 
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								

						ENDIF 

						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag] 
							IF blood_top_left = 0  //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[1] 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[1] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ELSE
										IF NOT IS_CAR_DEAD blood_car[2]
											TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[2] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							IF blood_top_left = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF 
		
				//car 1 & 2
				IF blood_car_flag = 1
				OR blood_car_flag = 2
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag] 
								IF blood_top_left = 0 //player is OUTside area
									IF NOT IS_CAR_DEAD blood_car[0]
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
										IF task_status = FINISHED_TASK
											GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
											IF task_status = FINISHED_TASK
												TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_car[0] MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								
								IF blood_top_left = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							IF blood_top_left = 0 //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[0]
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[0] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
									ENDIF
								ENDIF
							ENDIF

							IF blood_top_left = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF


				// TOP RIGHT
				//car 3
				IF blood_car_flag = 3
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver 
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
							 	IF blood_top_right = 0  //player is OUTside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_DRIVE_TO_COORD task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											GENERATE_RANDOM_FLOAT_IN_RANGE -1530.0 -1384.0 blood_rnd_2x
											GENERATE_RANDOM_FLOAT_IN_RANGE 995.0 1056.0 blood_rnd_2y
											GET_GROUND_Z_FOR_3D_COORD blood_rnd_2x blood_rnd_2y 1078.7 blood_rnd_2z
											IF blood_rnd_2z < 1034.1
												IF blood_rnd_2z > 1022.8
													TASK_CAR_DRIVE_TO_COORD blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_rnd_2x blood_rnd_2y blood_rnd_2z 100.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								IF blood_top_right = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF 
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF 

						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag] 
							IF blood_top_right = 0  //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[4] 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[4] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ELSE
										IF NOT IS_CAR_DEAD blood_car[5]
											TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[5] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							IF blood_top_right = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF 
		
				//car 4 & 5
				IF blood_car_flag = 4
				OR blood_car_flag = 5
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								IF blood_top_right = 0 //player is OUTside area
									IF NOT IS_CAR_DEAD blood_car[3]
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
										IF task_status = FINISHED_TASK
											GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
											IF task_status = FINISHED_TASK
												TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_car[3] MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								
								IF blood_top_right = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							IF blood_top_right = 0 //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[3]
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[3] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
									ENDIF
								ENDIF
							ENDIF

							IF blood_top_right = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF


				// BOTTOM LEFT
				//car 6
				IF blood_car_flag = 6
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver 
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								IF blood_bottom_left = 0  //player is OUTside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_DRIVE_TO_COORD task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											GENERATE_RANDOM_FLOAT_IN_RANGE -1265.0 -1384.0 blood_rnd_3x
											GENERATE_RANDOM_FLOAT_IN_RANGE 995.0 923.0 blood_rnd_3y
											GET_GROUND_Z_FOR_3D_COORD blood_rnd_3x blood_rnd_3y 1078.7 blood_rnd_3z
											IF blood_rnd_3z < 1034.1
												IF blood_rnd_3z > 1022.8
													TASK_CAR_DRIVE_TO_COORD blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_rnd_3x blood_rnd_3y blood_rnd_3z 100.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								IF blood_bottom_left = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF 
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF 

						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag] 
							IF blood_bottom_left = 0  //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[7] 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[7] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ELSE
										IF NOT IS_CAR_DEAD blood_car[8]
											TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[8] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							IF blood_bottom_left = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF 
		
				//car 7 & 8
				IF blood_car_flag = 7
				OR blood_car_flag = 8
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								IF blood_bottom_left = 0 //player is OUTside area
									IF NOT IS_CAR_DEAD blood_car[6]
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
										IF task_status = FINISHED_TASK
											GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
											IF task_status = FINISHED_TASK
												TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_car[6] MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								
								IF blood_bottom_left = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							IF blood_bottom_left = 0 //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[6]
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[6] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
									ENDIF
								ENDIF
							ENDIF

							IF blood_bottom_left = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF



				// BOTTOM RIGHT
				//car 9
				IF blood_car_flag = 9
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver 
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								IF blood_bottom_right = 0  //player is OUTside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_DRIVE_TO_COORD task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											GENERATE_RANDOM_FLOAT_IN_RANGE -1265.0 -1384.0 blood_rnd_4x
											GENERATE_RANDOM_FLOAT_IN_RANGE 995.0 1056.0 blood_rnd_4y
											GET_GROUND_Z_FOR_3D_COORD blood_rnd_4x blood_rnd_4y 1078.7 blood_rnd_4z
											IF blood_rnd_4z < 1034.1
												IF blood_rnd_4z > 1022.8
													TASK_CAR_DRIVE_TO_COORD blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_rnd_4x blood_rnd_4y blood_rnd_4z 100.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								IF blood_bottom_right = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF 
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF 

						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag] 
							IF blood_bottom_right = 0  //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[10] 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[10] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ELSE
										IF NOT IS_CAR_DEAD blood_car[11]
											TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[11] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 50
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							IF blood_bottom_right = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF 
		
				//car 10 & 11
				IF blood_car_flag = 10
				OR blood_car_flag = 11
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								IF blood_bottom_right = 0 //player is OUTside area
									IF NOT IS_CAR_DEAD blood_car[9]
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
										IF task_status = FINISHED_TASK
											GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
											IF task_status = FINISHED_TASK
												TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_car[9] MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								
								IF blood_bottom_right = 1  //player is INside area
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
									IF task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
										IF task_status = FINISHED_TASK
											TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
										ENDIF
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							IF blood_bottom_right = 0 //player is OUTside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									IF NOT IS_CAR_DEAD blood_car[9]
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_car[9] 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
									ENDIF
								ENDIF
							ENDIF

							IF blood_bottom_right = 1  //player is INside area
								GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
								IF task_status = FINISHED_TASK
									TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 25
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//CARS BLOCKING PLAYERS CAR
				//car 12 & 13
				IF blood_car_flag = 12
				OR blood_car_flag = 13
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_MISSION task_status
								IF task_status = FINISHED_TASK
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
									IF task_status = FINISHED_TASK
										TASK_CAR_MISSION blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_player_car MISSION_BLOCKCAR_FARAWAY 100.0 DRIVINGMODE_PLOUGHTHROUGH
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						
						//car passenger
						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
							IF task_status = FINISHED_TASK
								TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_player_car 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 40
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//CARS CHASING CHECKPOINTS
				//car 14 & 15
				IF blood_car_flag = 14
				OR blood_car_flag = 15
					IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
						//car driver
						IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag] 
							IF NOT IS_CAR_ON_FIRE blood_car[blood_car_flag]
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_DRIVE_TO_COORD task_status
								IF task_status = FINISHED_TASK
									GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
									IF task_status = FINISHED_TASK
										TASK_CAR_DRIVE_TO_COORD blood_driver[blood_car_flag] blood_car[blood_car_flag] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 100.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
									ENDIF
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
								IF task_status = FINISHED_TASK
									TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_HANDBRAKESTRAIGHT 2000000
								ENDIF
							ENDIF								
						ENDIF
						

						IF NOT IS_CHAR_DEAD blood_passenger[blood_car_flag]
							GET_SCRIPT_TASK_STATUS blood_passenger[blood_car_flag] TASK_DRIVE_BY task_status					
							IF task_status = FINISHED_TASK
								
								blood_this_car = blood_car[blood_car_flag] 
								IF blood_car_flag = 14 								  
									GOSUB blood_get_closest_car 
									blood_rnd_car[0] = blood_closest_car_index 
									IF NOT blood_rnd_car[0] = -1 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_rnd_car[0] 0.0 0.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ENDIF
								ENDIF
								IF blood_car_flag = 15 
									GOSUB blood_get_closest_car 
									blood_rnd_car[1] = blood_closest_car_index
									IF NOT blood_rnd_car[1] = -1 
										TASK_DRIVE_BY blood_passenger[blood_car_flag] -1 blood_rnd_car[1] 0.0 0.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN FALSE 50
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF
				ENDIF
			

				//checking if any car has passed through the checkpoint
				/*
				IF NOT IS_CAR_DEAD blood_car[0]
					IF LOCATE_CAR_3D blood_car[0] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
						GOSUB blood_checkpoint_location
					ENDIF
				ENDIF
			
				IF NOT IS_CAR_DEAD blood_car[3]
					IF LOCATE_CAR_3D blood_car[3] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
						GOSUB blood_checkpoint_location
					ENDIF
				ENDIF
			
				IF NOT IS_CAR_DEAD blood_car[6]
					IF LOCATE_CAR_3D blood_car[6] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
						GOSUB blood_checkpoint_location
					ENDIF
				ENDIF
			
				IF NOT IS_CAR_DEAD blood_car[9]
					IF LOCATE_CAR_3D blood_car[9] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
						GOSUB blood_checkpoint_location
					ENDIF
				ENDIF
				*/
			
				//checking if player killed a car
				IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
					IF NOT IS_CHAR_DEAD blood_player2 
						IF HAS_CAR_BEEN_DAMAGED_BY_CHAR blood_car[blood_car_flag] blood_player2
						OR HAS_CAR_BEEN_DAMAGED_BY_CHAR blood_car[blood_car_flag] scplayer  
							blood_player_damaged_car[blood_car_flag] = 1
						ELSE
							blood_player_damaged_car[blood_car_flag] = 0
						ENDIF
						CLEAR_CAR_LAST_DAMAGE_ENTITY blood_car[blood_car_flag]  	
					ENDIF
				ENDIF
	
		
				///checking if cars are stuck or not
				IF NOT IS_CAR_DEAD blood_car[blood_car_flag]
					IF NOT IS_CHAR_DEAD blood_driver[blood_car_flag]
						IF IS_CAR_UPSIDEDOWN blood_car[blood_car_flag] 
							IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer blood_car[blood_car_flag] 7.0 7.0 FALSE
								GET_CAR_COORDINATES blood_car[blood_car_flag] blood_reposition_car_x blood_reposition_car_y blood_reposition_car_z
								SET_CAR_COORDINATES blood_car[blood_car_flag] blood_reposition_car_x blood_reposition_car_y blood_reposition_car_z
							ENDIF
						ENDIF
						IF IS_CAR_STUCK	blood_car[blood_car_flag]
							GET_SCRIPT_TASK_STATUS blood_driver[blood_car_flag] TASK_CAR_TEMP_ACTION task_status
							IF task_status = FINISHED_TASK
								TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_REVERSE 3000
							ENDIF
						ENDIF
					ENDIF
				ELSE
					//checking if player killed a car
					IF blood_player_damaged_car[blood_car_flag] = 1
						blood_cars_killed ++
					ENDIF
					blood_player_damaged_car[blood_car_flag] = 0	
					//creating new car
					//blood_car_dead_flag = blood_car_flag
					GOSUB blood_new_cars 
				ENDIF
				blood_car_flag ++
			ELSE
				blood_car_flag = 0
			ENDIF


			//checking if one of the cars chasing you has gone through a checkpoint
			IF NOT IS_CAR_DEAD blood_car[12]
				IF LOCATE_CAR_3D blood_car[12] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
					GOSUB blood_checkpoint_location
				ENDIF
			ENDIF
		
			IF NOT IS_CAR_DEAD blood_car[13]
				IF LOCATE_CAR_3D blood_car[13] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
					GOSUB blood_checkpoint_location
				ENDIF
			ENDIF
		
			IF NOT IS_CAR_DEAD blood_car[14]
				IF LOCATE_CAR_3D blood_car[14] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
					GOSUB blood_checkpoint_location
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD blood_car[15]
				IF LOCATE_CAR_3D blood_car[15] blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
					GOSUB blood_checkpoint_location
				ENDIF
			ENDIF



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// DEALING WITH THE UPGRADES //////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
			IF DOES_OBJECT_EXIST blood_upgrade 
				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer blood_upgrade 5.0 5.0 FALSE  
					DELETE_OBJECT blood_upgrade
					GET_CAR_HEALTH blood_player_car blood_players_car_health
					IF blood_players_car_health < 999 
						blood_players_car_health += 500
						SET_CAR_HEALTH blood_player_car blood_players_car_health
					ELSE
						blood_players_car_health = 1500
						SET_CAR_HEALTH blood_player_car blood_players_car_health
					ENDIF
				ELSE
					IF blood_spin_upgrade < 358.0
						blood_spin_upgrade += 3.0	
						SET_OBJECT_HEADING blood_upgrade blood_spin_upgrade
					ELSE
						blood_spin_upgrade = 0.0
					ENDIF	
				ENDIF
			ELSE
				GOSUB blood_upgrade_location
			ENDIF
			

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// DEALING WITH THE BLIPS, CORONAS AND COMPLETION /////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//drawing corona for checkpoint
			//DRAW_CORONA blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 6.0 CORONATYPE_SHINYSTAR FLARETYPE_NONE 0 0 255
			
			//checking player has reached corona
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 7.0 7.0 7.0 FALSE
				IF blood_players_current_time > 0 
					GET_CAR_HEALTH blood_player_car blood_players_car_health
					IF blood_players_car_health < 1199 
						blood_players_car_health += 300
						SET_CAR_HEALTH blood_player_car blood_players_car_health
					ELSE
						blood_players_car_health = 1500
						SET_CAR_HEALTH blood_player_car blood_players_car_health
					ENDIF
					blood_players_current_time += 15000 //15 secs extra
					ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
					GOSUB blood_checkpoint_location
				ENDIF
			ENDIF  
		
			//checking if players car is upside down
			IF IS_CAR_UPSIDEDOWN blood_player_car
				IF IS_CAR_STOPPED blood_player_car							

					GET_CAR_COORDINATES blood_player_car blood_reposition_car_x blood_reposition_car_y blood_reposition_car_z
					GET_CAR_HEADING blood_player_car blood_heading
					SET_CAR_COORDINATES blood_player_car blood_reposition_car_x blood_reposition_car_y blood_reposition_car_z
					SET_CAR_HEADING blood_player_car blood_heading 
				ENDIF
			ENDIF  


			//sorting out players passenger 
			IF NOT IS_CHAR_DEAD blood_player2
				GET_SCRIPT_TASK_STATUS blood_player2 TASK_DRIVE_BY task_status					
				IF task_status = FINISHED_TASK
					blood_this_car = blood_player_car 
					GOSUB blood_get_closest_car 
					blood_rnd_car[2] = blood_closest_car_index
					IF NOT blood_rnd_car[2] = -1 
						TASK_DRIVE_BY blood_player2 -1 blood_rnd_car[2] 0.0 0.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN FALSE 100
					ENDIF
				ENDIF
			ENDIF
																					   
			//playing ooh's n aaahs
			GOSUB blood_audio
			
			//passing the mission													   
			IF blood_players_current_time > blood_time_to_beat						   
				blood_goals = 1
			ENDIF  
			
			
			//failing the mission
			IF blood_players_current_time <= 0
				blood_goals = 2
			ENDIF
		ENDIF
	ENDIF


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// PASSING THE MISSION ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	IF blood_goals = 1
		
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		PLAY_MISSION_PASSED_TUNE 1

		blood_car_flag = 0
		
		WHILE blood_car_flag < 16
		
			WAIT 0
			  
			IF NOT IS_CAR_DEAD blood_car[blood_car_flag]  
				FREEZE_CAR_POSITION blood_car[blood_car_flag] TRUE
			ENDIF
			blood_car_flag ++
		ENDWHILE
		
		GOSUB blood_menu

		blood_longest_time = blood_attempt * 60 
		INCREMENT_INT_STAT BLOODRING_TIME blood_longest_time 
		INCREMENT_INT_STAT BLOODRING_KILLS blood_cars_killed

		blood_attempt ++
		blood_time_to_beat += 60000

		timera = 0

		WHILE timera < 5000
			WAIT 0
		ENDWHILE
		
		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SWITCH_WIDESCREEN OFF
		DELETE_MENU blood_window
		SET_AREA_VISIBLE 0
		SET_CHAR_AREA_VISIBLE scplayer 0 
		REQUEST_COLLISION -2126.3 -440.6
		LOAD_SCENE_IN_DIRECTION -2119.0 -440.7 34.5 82.6 
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer -2126.3 -440.6 34.5 
		SET_CHAR_HEADING scplayer 82.7
		CLEAR_ONSCREEN_TIMER blood_players_current_time
		REMOVE_BLIP blood_blip
		DELETE_CHECKPOINT blood_checkpoint
		HIDE_ALL_FRONTEND_BLIPS FALSE

		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_FADING_COLOUR 0 0 0 
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_PLAYER_CONTROL player1 ON
		GOTO mission_blood_passed
	ENDIF




				
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// FAILING THE MISSION ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF blood_goals = 2
		//REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_AIR_HORN
		SET_PLAYER_CONTROL player1 OFF
		//SWITCH_WIDESCREEN ON
		blood_car_flag = 0
		WHILE blood_car_flag < 16
			WAIT 0   
			IF NOT IS_CAR_DEAD blood_car[blood_car_flag]  
				FREEZE_CAR_POSITION blood_car[blood_car_flag] TRUE
			ENDIF
			blood_car_flag ++
		ENDWHILE
		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SWITCH_WIDESCREEN OFF
		CLEAR_PRINTS
		PRINT_BIG M_FAIL 5000 1
		PRINT_NOW ( BLOD_05 ) 5000 1 //You ran out of time.
	
		SET_AREA_VISIBLE 0
		SET_CHAR_AREA_VISIBLE scplayer 0 
		REQUEST_COLLISION -2126.3 -440.6 
		LOAD_SCENE_IN_DIRECTION -2119.0 -440.7 34.5 82.6 
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer -2126.3 -440.6 34.5 
		SET_CHAR_HEADING scplayer 82.7
		CLEAR_ONSCREEN_TIMER blood_players_current_time
		REMOVE_BLIP blood_blip
		DELETE_CHECKPOINT blood_checkpoint
		HIDE_ALL_FRONTEND_BLIPS FALSE
		CANCEL_OVERRIDE_RESTART

		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_FADING_COLOUR 0 0 0 
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_PLAYER_CONTROL player1 ON
		GOTO mission_blood_failed
	ENDIF



GOTO mission_blood_loop


	
// ************************************ Mission blood failed ************************************
mission_blood_failed:
PRINT_BIG M_FAIL 5000 1
RETURN
		 
   
// -********************************** mission blood passed ************************************
mission_blood_passed:
//flag_blood_mission1_passed = 1
//PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1
//PLAY_MISSION_PASSED_TUNE 1
//CLEAR_WANTED_LEVEL PLAYER1

CANCEL_OVERRIDE_RESTART
SET_FREE_HEALTH_CARE player1 FALSE
CLEAR_PRINTS
CLEAR_WANTED_LEVEL player1

IF blood_passed_once = 0 
	REGISTER_ODDJOB_MISSION_PASSED
	PLAYER_MADE_PROGRESS 1
	SWITCH_CAR_GENERATOR f1_blood_car_gen 101
	PLAY_MISSION_PASSED_TUNE 2
	PRINT_WITH_NUMBER_BIG ( M_PASS ) 10000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 10000//amount of cash
	blood_passed_once = 1
ENDIF



RETURN
		

// ********************************** mission cleanup ************************************
mission_cleanup_blood:

//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
CLEAR_EXTRA_COLOURS FALSE
REMOVE_BLIP blood_blip
//CLEAR_ONSCREEN_COUNTER
CLEAR_ONSCREEN_TIMER blood_players_current_time
//MARK_MODEL_AS_NO_LONGER_NEEDED 
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE

USE_TEXT_COMMANDS FALSE 
//LOAD_SCENE -2119.0 -440.7 34.5
MARK_MODEL_AS_NO_LONGER_NEEDED BLOODRA
MARK_MODEL_AS_NO_LONGER_NEEDED STALLION
MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
MARK_MODEL_AS_NO_LONGER_NEEDED bb_pickup

SET_CHAR_AMMO scplayer WEAPONTYPE_MP5 blood_bullets 

DELETE_CHECKPOINT blood_checkpoint 

DONT_SUPPRESS_ANY_CAR_MODELS
FORCE_ALL_VEHICLE_LIGHTS_OFF FALSE
//SHUT_PLAYER_UP player1 FALSE
HIDE_ALL_FRONTEND_BLIPS FALSE
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
blood_setting_up_cars://///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF blood_car_flag = 0  
	blood_x = -1285.77  
	blood_y = 1014.54
	blood_z = 1036.5
	blood_heading = 104.4
ENDIF
IF blood_car_flag = 1  
	blood_x = -1299.50  
	blood_y = 1030.74
	blood_z = 1036.8
	blood_heading = 132.6
ENDIF
IF blood_car_flag = 2  
	blood_x = -1319.30  
	blood_y = 1042.82
	blood_z = 1037.1
	blood_heading = 135.4
ENDIF
IF blood_car_flag = 3  
	blood_x = -1349.33  
	blood_y = 1054.47
	blood_z = 1037.3
	blood_heading = 155.4
ENDIF
IF blood_car_flag = 4  
	blood_x = -1384.33  
	blood_y = 1058.55
	blood_z = 1037.5
	blood_heading = 173.2
ENDIF
IF blood_car_flag = 5  
	blood_x = -1296.7  
	blood_y = 960.2
	blood_z = 1035.6
	blood_heading = 60.5
ENDIF
IF blood_car_flag = 6  
	blood_x = -1418.77  
	blood_y = 1057.54
	blood_z = 1037.5
	blood_heading = 180.4
ENDIF
IF blood_car_flag = 7  
	blood_x = -1460.3  
	blood_y = 1050.3
	blood_z = 1037.5
	blood_heading = 203.9
ENDIF
IF blood_car_flag = 8  
	blood_x = -1499.77  
	blood_y = 1029.54	
	blood_z = 1037.2
	blood_heading = 242.4
ENDIF
IF blood_car_flag = 9  
	blood_x = -1513.77  
	blood_y = 986.54
	blood_z = 1036.5
	blood_heading = 271.4
ENDIF
IF blood_car_flag = 10  
	blood_x = -1496.77  
	blood_y = 959.54
	blood_z = 1036.0
	blood_heading = 293.4
ENDIF
IF blood_car_flag = 11  
	blood_x = -1461.84  
	blood_y = 938.32
	blood_z = 1035.3
	blood_heading = 345.6
ENDIF
IF blood_car_flag = 12  
	blood_x = -1424.36  
	blood_y = 931.29
	blood_z = 1035.2
	blood_heading = 0.0
ENDIF
IF blood_car_flag = 13  
	blood_x = -1363.77  
	blood_y = 932.44
	blood_z = 1035.3
	blood_heading = 17.85
ENDIF
IF blood_car_flag = 14  
	blood_x = -1332.77  
	blood_y = 940.30
	blood_z = 1037.0
	blood_heading = 39.98
ENDIF
IF blood_car_flag = 15  
	blood_x = -1443.29  
	blood_y = 933.86
	blood_z = 1035.3
	blood_heading = 351.4
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_checkpoint_location://///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GENERATE_RANDOM_FLOAT_IN_RANGE -1290.0 -1508.0 blood_chckpnt_x
GENERATE_RANDOM_FLOAT_IN_RANGE 939.0 1052.0 blood_chckpnt_y
GET_GROUND_Z_FOR_3D_COORD blood_chckpnt_x blood_chckpnt_y 1056.7 blood_chckpnt_z
IF blood_chckpnt_z > 1034.1
	GOTO blood_checkpoint_location
ENDIF
IF blood_chckpnt_z < 1022.8
	GOTO blood_checkpoint_location
ENDIF
REMOVE_BLIP blood_blip 
ADD_BLIP_FOR_COORD blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z blood_blip
IF NOT IS_CHAR_DEAD blood_driver[14]
	CLEAR_CHAR_TASKS blood_driver[14]
ENDIF
IF NOT IS_CHAR_DEAD blood_driver[15]
	CLEAR_CHAR_TASKS blood_driver[15]
ENDIF
SET_CHECKPOINT_COORDS blood_checkpoint blood_chckpnt_x blood_chckpnt_y blood_chckpnt_z 
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_upgrade_location://///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GENERATE_RANDOM_FLOAT_IN_RANGE -1290.0 -1508.0 blood_upgrade_x
GENERATE_RANDOM_FLOAT_IN_RANGE 939.0 1052.0 blood_upgrade_y
GET_GROUND_Z_FOR_3D_COORD blood_upgrade_x blood_upgrade_y 1056.7 blood_upgrade_z
IF blood_upgrade_z > 1034.1
	GOTO blood_upgrade_location
ENDIF
IF blood_upgrade_z < 1022.8
	GOTO blood_upgrade_location
ENDIF
blood_upgrade_z += 0.5
CREATE_OBJECT bb_pickup blood_upgrade_x blood_upgrade_y blood_upgrade_z blood_upgrade
SET_OBJECT_AREA_VISIBLE blood_upgrade 15
SET_OBJECT_COLLISION blood_upgrade FALSE
SET_OBJECT_DYNAMIC blood_upgrade FALSE
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_new_cars:////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GOSUB blood_random_garages

IF NOT blood_random_garage_flag = -1 
	REMOVE_STUCK_CAR_CHECK blood_car[blood_car_flag] 
	MARK_CAR_AS_NO_LONGER_NEEDED blood_car[blood_car_flag] 
	MARK_CHAR_AS_NO_LONGER_NEEDED blood_driver[blood_car_flag]
	MARK_CHAR_AS_NO_LONGER_NEEDED blood_passenger[blood_car_flag]
	
	GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE blood_model blood_class
	IF blood_model >= 0 
		blood_no_plates = blood_model
		GOSUB blood_my_number_plates
		CREATE_CAR blood_model blood_x blood_y blood_z blood_car[blood_car_flag]
		SET_CAR_HEADING blood_car[blood_car_flag] blood_heading   
	ENDIF
	IF NOT IS_CAR_DEAD blood_car[blood_car_flag] 
		
		SET_LOAD_COLLISION_FOR_CAR_FLAG blood_car[blood_car_flag] TRUE

		SET_CAR_STRAIGHT_LINE_DISTANCE blood_car[blood_car_flag] 255
		SET_CAN_BURST_CAR_TYRES blood_car[blood_car_flag] FALSE

		CREATE_RANDOM_CHAR_AS_DRIVER blood_car[blood_car_flag] blood_driver[blood_car_flag]
		SET_CHAR_AREA_VISIBLE blood_driver[blood_car_flag] 15 
		SET_CHAR_DECISION_MAKER blood_driver[blood_car_flag] blood_empty_ped_decision_maker
		SET_CHAR_PROOFS blood_driver[blood_car_flag] TRUE FALSE FALSE FALSE FALSE 
		
		//SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH blood_driver[blood_car_flag] TRUE 
		SET_LOAD_COLLISION_FOR_CHAR_FLAG blood_driver[blood_car_flag] TRUE 
		GET_MAXIMUM_NUMBER_OF_PASSENGERS blood_car[blood_car_flag] blood_no_of_seats 
		IF blood_no_of_seats > 0 
			CREATE_RANDOM_CHAR_AS_PASSENGER blood_car[blood_car_flag] 0 blood_passenger[blood_car_flag] 
			SET_CHAR_DECISION_MAKER blood_passenger[blood_car_flag] blood_empty_ped_decision_maker
			SET_CHAR_PROOFS blood_passenger[blood_car_flag] TRUE FALSE FALSE FALSE FALSE 
			GIVE_WEAPON_TO_CHAR blood_passenger[blood_car_flag] WEAPONTYPE_MP5 30000
			//SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH blood_passenger[blood_car_flag] TRUE 
		ENDIF
		IF IS_CHAR_ON_ANY_BIKE blood_driver[blood_car_flag]
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE blood_driver[blood_car_flag] KNOCKOFFBIKE_NEVER
			SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE blood_passenger[blood_car_flag] KNOCKOFFBIKE_NEVER
		ENDIF
		ADD_STUCK_CAR_CHECK blood_car[blood_car_flag] 1.0 3000
		SET_UPSIDEDOWN_CAR_NOT_DAMAGED blood_car[blood_car_flag] TRUE
		SET_VEHICLE_AREA_VISIBLE blood_car[blood_car_flag] 15
		SET_CAR_FORWARD_SPEED blood_car[blood_car_flag] 25.0
		TASK_CAR_TEMP_ACTION blood_driver[blood_car_flag] blood_car[blood_car_flag] TEMPACT_GOFORWARD 3000
	ENDIF
ENDIF
//blood_car_dead_flag = -1
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_random_garages://////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GENERATE_RANDOM_INT_IN_RANGE 1 4 blood_random_garage_flag

IF blood_random_garage_flag = 1
	IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -1461.84 938.32 1035.00 5.0 5.0 5.0  //garage 1
		blood_x = -1461.84
		blood_y = 938.32
		blood_z = 1035.00
		blood_heading = 345.6 
	ELSE
		blood_random_garage_flag = 2
	ENDIF
ENDIF
IF blood_random_garage_flag = 2
	IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -1424.36 933.86 1035.00 5.0 5.0 5.0  //garage 2
		blood_x = -1424.36
		blood_y = 933.86
		blood_z = 1035.00
		blood_heading = 0.0 
	ELSE
		blood_random_garage_flag = 3
	ENDIF
ENDIF
IF blood_random_garage_flag = 3
	IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -1443.29 931.29 1035.00 5.0 5.0 5.0  //grage 3
		blood_x = -1443.29
		blood_y = 931.29
		blood_z = 1035.00
		blood_heading = 351.4 
	ELSE
		blood_random_garage_flag = 1
	ENDIF
ENDIF
IF blood_random_garage_flag = 1
	IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -1461.84 938.32 1035.00 5.0 5.0 5.0  //garage 1
		blood_x = -1461.84
		blood_y = 938.32
		blood_z = 1035.00
		blood_heading = 345.6 
	ELSE
		blood_random_garage_flag = -1
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_my_number_plates:////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
	GENERATE_RANDOM_INT_IN_RANGE 1 37 blood_no_plates_flag
	IF blood_no_plates_flag = 1 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates got_m00_
	ENDIF 
	IF blood_no_plates_flag = 2 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates m00tv_4u 
	ENDIF
	IF blood_no_plates_flag = 3 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates mathew_2 
	ENDIF 
	IF blood_no_plates_flag = 4 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates d4_dew0r 
	ENDIF 
	IF blood_no_plates_flag = 5 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates d0de_777 
	ENDIF 
	IF blood_no_plates_flag = 6 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates dam0_666 
	ENDIF 
	IF blood_no_plates_flag = 7 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates C0NEY_88 
	ENDIF 
	IF blood_no_plates_flag = 8 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates pre4cher 
	ENDIF 
	IF blood_no_plates_flag = 9 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates dbp_4ndy 
	ENDIF 
	IF blood_no_plates_flag = 10 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates ev1l_sly 
	ENDIF 
	IF blood_no_plates_flag = 11 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates n1_r4v3n 
	ENDIF 
	IF blood_no_plates_flag = 12 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates d1vx_z00 
	ENDIF 
	IF blood_no_plates_flag = 13 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates mr_b3nn 
	ENDIF 
	IF blood_no_plates_flag = 14 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates r3d_r4sp 
	ENDIF 
	IF blood_no_plates_flag = 15 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates La_B0mba 
	ENDIF 
	IF blood_no_plates_flag = 16 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates L3337_0g 
	ENDIF 
	IF blood_no_plates_flag = 17 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates budd4h_X 
	ENDIF 
	IF blood_no_plates_flag = 18 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates t3h_buck 
	ENDIF 
	IF blood_no_plates_flag = 19 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates CHUNKY_1 
	ENDIF 
	IF blood_no_plates_flag = 20 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates ev1l_bnz 
	ENDIF 
	IF blood_no_plates_flag = 21 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates S4ND_M4N 
	ENDIF 
	IF blood_no_plates_flag = 22 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates RKK_DBP1 
	ENDIF 
	IF blood_no_plates_flag = 23 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates RE1_K0KU 
	ENDIF 
	IF blood_no_plates_flag = 24 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates s3xy_jud 
	ENDIF 
	IF blood_no_plates_flag = 25 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates sunra_93 
	ENDIF 
	IF blood_no_plates_flag = 26 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates UG_FuX69 
	ENDIF 
	IF blood_no_plates_flag = 27 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates Li0n_Cum 
	ENDIF 
	IF blood_no_plates_flag = 28 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates rkk_pwnd 
	ENDIF 
	IF blood_no_plates_flag = 29 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates haze_b0b 
	ENDIF 
	IF blood_no_plates_flag = 30 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates t3h_fluf 
	ENDIF 
	IF blood_no_plates_flag = 31 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates BM_4NDY_ 
	ENDIF 
	IF blood_no_plates_flag = 32 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates BM_D34N_ 
	ENDIF 
	IF blood_no_plates_flag = 33 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates BM_L4C3Y 
	ENDIF 
	IF blood_no_plates_flag = 34 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates BM_D3V__ 
	ENDIF 
	IF blood_no_plates_flag = 35 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates NU_SK00L 
	ENDIF 
	IF blood_no_plates_flag = 36 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates G4L_AVET 
	ENDIF 
	IF blood_no_plates_flag = 37 
		CUSTOM_PLATE_FOR_NEXT_CAR blood_no_plates M0j0_j0j 
	ENDIF 
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_get_closest_car://////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
GET_CAR_COORDINATES blood_this_car blood_this_car_x blood_this_car_y z

blood_car_flag2 = 0

blood_closest_distance = 99999.0

blood_closest_car_index = -1

WHILE blood_car_flag2 < 16
	IF NOT blood_this_car = blood_car[blood_car_flag2]
		IF NOT IS_CAR_DEAD blood_car[blood_car_flag2]
			GET_CAR_COORDINATES blood_car[blood_car_flag2] blood_other_car_x blood_other_car_y z
			GET_DISTANCE_BETWEEN_COORDS_2D blood_this_car_x blood_this_car_y blood_other_car_x blood_other_car_y blood_current_distance
			IF blood_current_distance < blood_closest_distance
	            blood_closest_distance = blood_current_distance
	            blood_closest_car_index = blood_car[blood_car_flag2]
			ENDIF
		ENDIF
	ENDIF
    blood_car_flag2++
ENDWHILE

IF NOT blood_this_car = blood_player_car 
	GET_CAR_COORDINATES blood_player_car blood_other_car_x blood_other_car_y z
	GET_DISTANCE_BETWEEN_COORDS_2D blood_this_car_x blood_this_car_y blood_other_car_x blood_other_car_y blood_current_distance
	IF blood_current_distance < blood_closest_distance
	    blood_closest_distance = blood_current_distance
	    blood_closest_car_index = blood_player_car
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
blood_menu:////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

IF NOT current_Language = LANGUAGE_SPANISH
	CREATE_MENU BLOD_06 190.0 141.0 120.0 2 FALSE TRUE FO_LEFT blood_window
ELSE
	CREATE_MENU BLOD_06 190.0 141.0 150.0 2 FALSE TRUE FO_LEFT blood_window
ENDIF

SET_MENU_COLUMN blood_window 0 DUMMY BLOD_07 BLOD_08 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
SET_MENU_COLUMN blood_window 1 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY

SET_MENU_ITEM_WITH_NUMBER blood_window 1 0 BLOD_09 blood_attempt 
SET_MENU_ITEM_WITH_NUMBER blood_window 1 1 BLOD_09 blood_cars_killed

IF NOT current_Language = LANGUAGE_SPANISH
	SET_MENU_COLUMN_WIDTH blood_window 0 200
	SET_MENU_COLUMN_WIDTH blood_window 1 40
ELSE
	SET_MENU_COLUMN_WIDTH blood_window 0 260
	SET_MENU_COLUMN_WIDTH blood_window 1 40
ENDIF

RETURN/////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////
blood_audio:////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF blood_audio_flag = 0
	GENERATE_RANDOM_INT_IN_RANGE 8000 16000 blood_rnd_time
	GENERATE_RANDOM_INT_IN_RANGE 1 22 blood_sound_flag
	LOAD_MISSION_AUDIO 4 blood_sound[blood_sound_flag]
	timerb = 0
	blood_audio_flag = 1
ENDIF

IF blood_audio_flag = 1
	IF HAS_MISSION_AUDIO_LOADED 4
		blood_audio_flag = 2
	ENDIF
ENDIF

IF blood_audio_flag = 2
	IF timerb > blood_rnd_time 
		PLAY_MISSION_AUDIO 4
		blood_audio_flag = 3
	ENDIF
ENDIF
	
IF blood_audio_flag = 3
	IF HAS_MISSION_AUDIO_FINISHED 4
		blood_audio_flag = 0
	ENDIF
ENDIF	  
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
}
		


