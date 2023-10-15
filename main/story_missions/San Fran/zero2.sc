MISSION_START
// *****************************************************************************************
// ************************************* RC TURF WAR 2 *************************************
// ************************************* rc baron ******* **********************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_zero2

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_zero2_failed
	ENDIF

GOSUB mission_cleanup_zero2

MISSION_END


// Variables for mission

//RC VEHICLES
{

LVAR_INT rc_playerbaron_zero2 

LVAR_INT rc_van_zero2 

LVAR_INT char_drivers_zero2[5]

LVAR_INT char_dude_with_gun[5]
LVAR_INT car_enemy_vans_zero2[5]

LVAR_INT char_3_buddy_with_MICRO_UZI[5]


LVAR_INT dm_baddies_zero2


LVAR_INT char_zero_zero2  

LVAR_INT return_city_zero2

LVAR_FLOAT timer_time_limit timer_time_limit_initial timer_time_limit_converted_float
VAR_INT  timer_time_limit_converted_int

LVAR_FLOAT speed_rc_playerbaron_zero2







LVAR_FLOAT coord_enemyvan_x[5] coord_enemyvan_y[5] coord_enemyvan_z[5] heading_enemy_van[5]
LVAR_FLOAT coord_redbaron_x	coord_redbaron_y coord_redbaron_z heading_redbaron
LVAR_FLOAT coord_players_van_x coord_players_van_y coord_players_van_z heading_players_van


LVAR_FLOAT stuck_x_zero2 stuck_y_zero2 stuck_z_zero2

LVAR_INT stuck_timer_zero2 game_timer_zero2

//VAR_FLOAT rotation_velocity_zero2_x rotation_velocity_zero2_y rotation_velocity_zero2_z




//LVAR_INT rc_cutscene_heli_zero2

// OBJECT




// FLAGS

LVAR_INT flag_main_sub_function_zero2 
LVAR_INT flag_fly_next_to_transmittor_zero2
LVAR_INT flag_heli_landing_cutscene_zero2 
LVAR_INT flag_fly_back_to_player_zero2
LVAR_INT flag_mission_zero2_passed
LVAR_INT flag_mission_zero2_failed 
LVAR_INT flag_created_rc_enemies_zero2 
LVAR_INT flag_enemy_van_alarmed[5]
LVAR_INT flag_help_text
LVAR_INT flag_help_text2
LVAR_INT flag_transmittor_in_place
LVAR_INT flag_is_enemy_van_dead[5]
LVAR_INT flag_land_plane_zero2
LVAR_INT flag_self_destruct_zero2


LVAR_INT seq_zero_cutscene

LVAR_INT help_flags_zero1

LVAR_INT cutscene_flag_zero2

LVAR_INT van_death_counter

LVAR_INT flag_zeros_fuel_comments	


// BLIP
LVAR_INT blip_rctiger_zero2[5] 
LVAR_INT blip_landing_zero2


LVAR_INT index_zero2  


// ****************************************Mission Start************************************

mission_start_zero2:

help_flags_zero1 = 0
flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME ZERO2

SET_PLAYER_CONTROL player1 OFF

LOAD_MISSION_TEXT ZERO2

DISPLAY_CAR_NAMES FALSE



//////////////////////////////
SET_FADING_COLOUR 0 0 0
SET_POLICE_IGNORE_PLAYER player1 ON
/*
DO_FADE 1000 FADE_OUT	 
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE*/
SET_AREA_VISIBLE 6
SET_PLAYER_CONTROL player1 OFF


FORCE_WEATHER_NOW WEATHER_SUNNY_SF
LOAD_CUTSCENE ZERO_2
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
START_CUTSCENE
DO_FADE 1000 FADE_IN
SET_PLAYER_CONTROL player1 OFF


WHILE NOT HAS_CUTSCENE_FINISHED
   WAIT 0
ENDWHILE

CLEAR_CUTSCENE 

SET_PLAYER_CONTROL player1 OFF
SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT
///////////////////////////////////



WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
CLEAR_CUTSCENE 

SET_AREA_VISIBLE 0

SET_PLAYER_CONTROL player1 OFF

 



// INITIALISING VARIABLES 
flag_main_sub_function_zero2 = 1
flag_fly_next_to_transmittor_zero2 = 0
flag_heli_landing_cutscene_zero2 = 0
flag_fly_back_to_player_zero2 = 0			
flag_transmittor_in_place = 0
flag_help_text = 0
flag_help_text2 = 0
flag_land_plane_zero2 = 0

flag_self_destruct_zero2 = 0


flag_mission_zero2_passed = 0
flag_mission_zero2_failed = 0


					  

flag_is_enemy_van_dead[0] = 0
flag_is_enemy_van_dead[1] =	0
flag_is_enemy_van_dead[2] = 0


van_death_counter = 0


		

timer_time_limit = 390000.0 //6.5 minutes
timer_time_limit_initial = timer_time_limit 
//timer_time_limit = 10000 //5 minutes



	  
coord_enemyvan_x[0] = -2006.77 
coord_enemyvan_y[0] = 365.69 
coord_enemyvan_z[0] = 34.35
heading_enemy_van[0] = 0.0  


  	 


coord_enemyvan_x[1] = -2250.5725
coord_enemyvan_y[1] = 22.8303 
coord_enemyvan_z[1] = 34.4634
heading_enemy_van[1] = 180.0  
									/*
coord_enemyvan_x[2] =  -1728.08 
coord_enemyvan_y[2] =  850.89  
coord_enemyvan_z[2] =  23.73	 
heading_enemy_van[2] =  270.0	  */

coord_enemyvan_x[2] =  -2266.27 
coord_enemyvan_y[2] =  53.1194  
coord_enemyvan_z[2] =  34.38 	 
heading_enemy_van[2] =  90.0  


coord_enemyvan_x[3] =  -2441.4241 
coord_enemyvan_y[3] = 	41.9630 
coord_enemyvan_z[3] = 33.004
heading_enemy_van[3] =90.0  

coord_enemyvan_x[4] = -1728.08 
coord_enemyvan_y[4] = 850.89  
coord_enemyvan_z[4] = 23.73	
heading_enemy_van[4] = 270.0	

flag_enemy_van_alarmed[0] = 0
flag_enemy_van_alarmed[1] = 0
flag_enemy_van_alarmed[2] = 0
flag_enemy_van_alarmed[3] = 0
flag_enemy_van_alarmed[4] = 0


flag_zeros_fuel_comments = 0




coord_redbaron_x = -2205.44	
coord_redbaron_y = 128.99 
coord_redbaron_z = 57.33
heading_redbaron = 90.0

coord_players_van_x =  -2227.0   
coord_players_van_y = 113.0
coord_players_van_z = 35.5  

timer_time_limit_converted_float = 100.0
timer_time_limit_converted_int = 100

TIMERA = 0

index_zero2 = 0


DISABLE_ALL_ENTRY_EXITS TRUE


// LOAD MODELS
 
REQUEST_MODEL rcbaron
REQUEST_MODEL topfun
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL mtbike
REQUEST_MODEL sanchez
LOAD_SPECIAL_CHARACTER 1 zero

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED rcbaron
	OR NOT HAS_MODEL_LOADED topfun
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	OR NOT HAS_MODEL_LOADED mtbike
	OR NOT HAS_MODEL_LOADED	sanchez
	WAIT 0
ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	WAIT 0
ENDWHILE   
	 



/*
REQUEST_MODEL news1

WHILE NOT HAS_MODEL_LOADED news1
	WAIT 0
ENDWHILE
  */










//LOAD_SCENE -2083.7158 -990.5264 31.1692 


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_baddies_zero2





// ******************************************** START MAIN LOOP **********************************
		
	main_zero2_loop:
		WAIT 0 

		// WAIT FOR HELI TO REACH WINDOW
			IF flag_main_sub_function_zero2 = 1
				GOSUB main_sub_function_zero2
			ENDIF

			IF flag_land_plane_zero2 = 1
				GOSUB land_plane_zero2 
			ENDIF

			
			IF flag_mission_zero2_passed = 1
				GOTO mission_zero2_passed
			ENDIF

			IF flag_mission_zero2_failed = 1


				GOTO mission_zero2_failed
			ENDIF

			

	GOTO main_zero2_loop



// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////
// ************************************     Sub Functions *****************************************
// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////







main_sub_function_zero2:
//   SET_CHAR_COORDINATES scplayer -1851.7444 651.0315 79.4154 
	CLEAR_AREA coord_players_van_x coord_players_van_y coord_players_van_z 1000.0 TRUE 
   	CREATE_CAR TOPFUN -2250.9 124.6 28.48  rc_van_zero2
   	SET_LOAD_COLLISION_FOR_CAR_FLAG rc_van_zero2 FALSE
	FREEZE_CAR_POSITION rc_van_zero2 TRUE
   	  //	coord_players_van_x coord_players_van_y coord_players_van_z
	OPEN_CAR_DOOR rc_van_zero2 REAR_LEFT_DOOR
	OPEN_CAR_DOOR rc_van_zero2 REAR_RIGHT_DOOR 

	WARP_CHAR_INTO_CAR scplayer rc_van_zero2
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER 
	IF NOT IS_CAR_DEAD rc_van_zero2
		CREATE_CHAR_AS_PASSENGER rc_van_zero2 PEDTYPE_CIVMALE SPECIAL01 0 char_zero_zero2
		SET_CAR_HEALTH rc_van_zero2 1000
		SET_CAR_PROOFS rc_van_zero2 TRUE TRUE TRUE TRUE TRUE
	ENDIF

	SET_POLICE_IGNORE_PLAYER player1 ON
   	
  // 	CREATE_CAR rcbaron coord_redbaron_x coord_redbaron_y coord_redbaron_z rc_cutscene_heli_zero2

   	GIVE_REMOTE_CONTROLLED_MODEL_TO_PLAYER player1 coord_redbaron_x coord_redbaron_y coord_redbaron_z 40.090 rcbaron
   	GET_REMOTE_CONTROLLED_CAR player1 rc_playerbaron_zero2
	SET_CAR_HEADING rc_playerbaron_zero2	90.0


	SET_ENABLE_RC_DETONATE_ON_CONTACT FALSE
	SET_ENABLE_RC_DETONATE FALSE
	SET_CAR_HEALTH rc_playerbaron_zero2 1100	
	SET_CAR_PROOFS rc_playerbaron_zero2 FALSE TRUE TRUE FALSE FALSE
   //	SET_CAR_PROOFS CarID Bulletproof Flameproof Explosionproof Collisionproof MeleeWeaponproof
 //	SET_CAR_PROOFS CarID Bulletproof Flameproof Explosionproof Collisionproof MeleeWeaponproof


	
	
	index_zero2 = 0
 	WHILE index_zero2 < 5 //creating 3 RC bandits to capture the enemy flag
 		WAIT 0
	//	CREATE_CAR rctiger coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]

		CLEAR_AREA coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] 1000.0 TRUE
	
		// car 4 is the parked car
		// car 3 is the one with a guy shooting
		// car 2 is the one where the guy legs it
		// 1 A mt bike
		// 0 motorbike

	
		IF index_zero2 = 0 
			CREATE_CAR SANCHEZ coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]
			SET_CAR_HEALTH car_enemy_vans_zero2[index_zero2] 1000 
		ENDIF

		IF index_zero2 = 1 
			CREATE_CAR mtbike coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]
			SET_CAR_HEALTH car_enemy_vans_zero2[index_zero2] 1500 
		ENDIF

		IF index_zero2 = 2 
			CREATE_CAR  TOPFUN coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]
			SET_CAR_HEALTH car_enemy_vans_zero2[index_zero2] 1500 
		ENDIF

		IF index_zero2 = 3 
			CREATE_CAR TOPFUN coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]
			SET_CAR_HEALTH car_enemy_vans_zero2[index_zero2] 1500 
		ENDIF

		IF index_zero2 = 4 
			CREATE_CAR TOPFUN coord_enemyvan_x[index_zero2] coord_enemyvan_y[index_zero2] coord_enemyvan_z[index_zero2] car_enemy_vans_zero2[index_zero2]
			SET_CAR_HEALTH car_enemy_vans_zero2[index_zero2] 1500 
			LOCK_CAR_DOORS car_enemy_vans_zero2[index_zero2] CARLOCK_LOCKED_BUT_CAN_BE_DAMAGED
		ENDIF
		
		SET_CAR_HEADING car_enemy_vans_zero2[index_zero2] heading_enemy_van[index_zero2] 
		SET_CAR_PROOFS car_enemy_vans_zero2[index_zero2] FALSE TRUE TRUE FALSE FALSE
		
	    CREATE_RANDOM_CHAR_AS_DRIVER car_enemy_vans_zero2[index_zero2] char_drivers_zero2[index_zero2]
		GIVE_WEAPON_TO_CHAR char_drivers_zero2[index_zero2] WEAPONTYPE_MICRO_UZI 500
		SET_CHAR_ACCURACY char_drivers_zero2[index_zero2] 100
	   
		SET_LOAD_COLLISION_FOR_CAR_FLAG car_enemy_vans_zero2[index_zero2] FALSE


		IF index_zero2 = 3 // this car has buddy  
		OR index_zero2 = 0
		   //	CREATE_CHAR_AS_PASSENGER car_enemy_vans_zero2[index_zero2] PEDTYPE_MISSION1 news1 0 char_3_buddy_with_MICRO_UZI[index_zero2]
			
			
			CREATE_RANDOM_CHAR_AS_PASSENGER car_enemy_vans_zero2[index_zero2] 0 char_3_buddy_with_MICRO_UZI[index_zero2]
			GIVE_WEAPON_TO_CHAR char_3_buddy_with_MICRO_UZI[index_zero2] WEAPONTYPE_MICRO_UZI 500 
			SET_CHAR_ACCURACY char_3_buddy_with_MICRO_UZI[index_zero2] 100

			SET_CHAR_DECISION_MAKER char_3_buddy_with_MICRO_UZI[index_zero2] dm_baddies_zero2
			SET_CHAR_DECISION_MAKER char_drivers_zero2[index_zero2] dm_baddies_zero2
		  //	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_baddies_zero2 EVENT_VEHICLE_DAMAGE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
		ENDIF 	
		
		//IF NOT index_zero2 = 4 // car 4 is parked
			TASK_CAR_DRIVE_WANDER char_drivers_zero2[index_zero2] car_enemy_vans_zero2[index_zero2] 3.0 DRIVINGMODE_STOPFORCARS
		//ENDIF

		flag_enemy_van_alarmed[index_zero2]= 0
		index_zero2++
 	ENDWHILE

	TIMERB = 0
	

	// CUTSCENE HERE. 

 
  	SET_FIXED_CAMERA_POSITION -2273.8411 45.5794 37.0987 0.0 0.0 0.0
 	POINT_CAMERA_AT_POINT -2273.2969 46.4174 37.1385 JUMP_CUT
 
  //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
 //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 



	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	CLEAR_AREA -2285.54 41.31 35.01 1000.0 TRUE



   	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE                       
    CAMERA_SET_VECTOR_MOVE  -2273.8411 45.5794 37.0987 -2280.6526 50.3058 35.7357    5000 TRUE    // two sets of coord for cam start point and end point + time for pan
    CAMERA_SET_VECTOR_TRACK -2273.2969 46.4174 37.1385 -2280.0540 51.1040 35.6677     5000 TRUE    // two sets of coords for cam aim at start and end point + time for pan
				


	SWITCH_WIDESCREEN ON
	TIMERA = 0
	TIMERB = 0

   //	LOAD_SCENE -2285.54 41.31 35.01
	LOAD_SCENE_IN_DIRECTION	-2273.8411 45.5794 37.0987 310.0



	SET_FADING_COLOUR 0 0 0	
	WAIT 1000
	DO_FADE 1000 FADE_IN


	cutscene_flag_zero2 = 0
	WHILE NOT cutscene_flag_zero2 = 10 
		WAIT 0
		


		IF cutscene_flag_zero2 = 0


			cutscene_flag_zero2 = 1
			SKIP_CUTSCENE_START
			LOAD_MISSION_AUDIO 1 SOUND_ZER2_AB  //	Berkley has his sychophantic lackies do all his deliveries.
		   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   		WAIT 0
		   	ENDWHILE  

		   	PRINT_NOW ( ZER2_AB ) 10000 1		
		   	PLAY_MISSION_AUDIO 1
		   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		   		WAIT 0
		   	ENDWHILE
		   	CLEAR_THIS_PRINT ZER2_AB

		ENDIF

		IF cutscene_flag_zero2 = 1
			cutscene_flag_zero2 = 2
			LOAD_MISSION_AUDIO 1 SOUND_ZER2_AC  //	We shall hit him where it hurts the most!
		   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   		WAIT 0
		   	ENDWHILE  

		   	PRINT_NOW ( ZER2_AC ) 10000 1		
		   	PLAY_MISSION_AUDIO 1
		   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		   		WAIT 0
		   	ENDWHILE
		   	CLEAR_THIS_PRINT ZER2_AC
		ENDIF

		IF cutscene_flag_zero2 = 2
			cutscene_flag_zero2 = 3
			LOAD_MISSION_AUDIO 1 SOUND_ZER2_AD  //	Bring his mail order model business to its knees!
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   		WAIT 0
		   	ENDWHILE  

		   	PRINT_NOW ( ZER2_AD ) 10000 1		
		   	PLAY_MISSION_AUDIO 1
		   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		   		WAIT 0
		   	ENDWHILE
		   	CLEAR_THIS_PRINT ZER2_AD

		ENDIF
  
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			CLEAR_MISSION_AUDIO 1
			cutscene_flag_zero2 = 10		
		ENDIF


		IF TIMERA > 5000
			cutscene_flag_zero2 = 10
		ENDIF

	ENDWHILE
	

	SKIP_CUTSCENE_END
	SET_FADING_COLOUR 0 0 0

	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

	CAMERA_RESET_NEW_SCRIPTABLES

	IF NOT IS_CAR_DEAD rc_van_zero2 
		SET_CAR_COORDINATES  rc_van_zero2 -2233.9 122.6 700.48
		SET_CAR_HEADING rc_van_zero2 90.0
		FREEZE_CAR_POSITION rc_van_zero2 TRUE
	ENDIF
	SET_RADIO_CHANNEL RS_MODERN_ROCK

   //	WAIT 1000

	// END CUTSCENE

	IF NOT IS_CAR_DEAD rc_van_zero2

		CLOSE_ALL_CAR_DOORS rc_van_zero2

											   
	ENDIF	
	 

   //	DELETE_CAR rc_cutscene_heli_zero2
	SWITCH_WIDESCREEN OFF

	//SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
   //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993



	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0



	RESTORE_CAMERA_JUMPCUT
	IF NOT IS_CAR_DEAD rc_playerbaron_zero2 
	   	POINT_CAMERA_AT_CAR rc_playerbaron_zero2 CAM_ON_A_STRING JUMP_CUT 
		FREEZE_CAR_POSITION rc_playerbaron_zero2 FALSE

		WAIT 0
		SET_CAMERA_ZOOM CAM_ZOOM_TWO

	ENDIF	


	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

   	

	SET_PLAYER_CONTROL player1 ON

	index_zero2 = 0
	WHILE index_zero2 < 5 //creating 3 RC bandits to capture the enemy flag
		IF NOT IS_CHAR_DEAD char_drivers_zero2[index_zero2]
			ADD_BLIP_FOR_CHAR char_drivers_zero2[index_zero2] blip_rctiger_zero2[index_zero2]
		ENDIF
		index_zero2++
	ENDWHILE
   //	DISPLAY_ONSCREEN_TIMER_WITH_STRING timer_time_limit TIMER_DOWN ZER2_28
 //	DISPLAY_ONSCREEN_TIMER_WITH_STRING timer_time_limit TIMER_DOWN ZER2_28

	DISPLAY_ONSCREEN_COUNTER_WITH_STRING  timer_time_limit_converted_int COUNTER_DISPLAY_BAR ZER2_28
	CLEAR_MISSION_AUDIO 1
	LOAD_MISSION_AUDIO 1 SOUND_ZER2_AA  //	[ZER2_AA]	Launch the Red Baron!
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE  

	PRINT_NOW ( ZER2_AA ) 10000 1		
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT ZER2_AA


	
	// WAiting for rc heli to pickup transmittor
	IF NOT IS_CAR_DEAD rc_playerbaron_zero2

		WHILE NOT flag_transmittor_in_place = 1 
			WAIT 0
//			VIEW_FLOAT_VARIABLE rotation_velocity_zero2_x rotation_velocity_zero2_x
  ///			VIEW_FLOAT_VARIABLE rotation_velocity_zero2_y rotation_velocity_zero2_y
	   //		VIEW_INTEGER_VARIABLE timer_time_limit_converted_int timer_time_limit_converted_int
			

			IF NOT IS_CAR_DEAD rc_playerbaron_zero2
				IF IS_BUTTON_PRESSED PAD1 CROSS 
					timer_time_limit-=45.0
					timer_time_limit_converted_float = timer_time_limit / timer_time_limit_initial
					timer_time_limit_converted_float*=100.0
					timer_time_limit_converted_int =# timer_time_limit_converted_float
				ENDIF

				GET_CAR_COORDINATES rc_playerbaron_zero2 coord_redbaron_x coord_redbaron_y coord_redbaron_z  
				GET_CITY_FROM_COORDS coord_redbaron_x coord_redbaron_y coord_redbaron_z return_city_zero2	
			ENDIF	
			
			IF return_city_zero2 = LEVEL_LOSANGELES
			OR return_city_zero2 = LEVEL_LASVEGAS
				EXPLODE_CAR rc_playerbaron_zero2
				EXPLODE_CAR rc_playerbaron_zero2
			ENDIF	

			IF help_flags_zero1 = 0
				PRINT_HELP ZER2_1 	 // press circle to accelerate
			 	help_flags_zero1 = 1
				TIMERA = 0
			ENDIF

			IF help_flags_zero1 = 1
				IF TIMERA > 8000

					CLEAR_HELP
					PRINT_HELP ZER2_2  
					
					help_flags_zero1 = 2
					TIMERA = 0
				ENDIF
			ENDIF


			IF help_flags_zero1 = 2
			   //	IF IS_BUTTON_PRESSED PAD1 SQUARE

				IF TIMERA > 8000
					CLEAR_HELP
					PRINT_HELP (ZER2_30)  
					help_flags_zero1 = 3
					TIMERA = 0
			 	ENDIF
			ENDIF

			IF help_flags_zero1 = 3
			   //	IF IS_BUTTON_PRESSED PAD1 SQUARE

				IF TIMERA > 8000
					CLEAR_HELP
					PRINT_NOW ZER2_14 10000 1 //Destroy the vans 
					help_flags_zero1 = 4
			 	ENDIF
			ENDIF

		 	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
				 WAIT 0
				 WAIT 0
				 WAIT 0
				 WAIT 0
			   //	 flag_main_sub_function_zero2 = 0

				 flag_main_sub_function_zero2 = 0
				 flag_fly_next_to_transmittor_zero2 = 0
				 flag_heli_landing_cutscene_zero2 = 0
			  //	 flag_fly_back_to_player_zero2 = 0
				 flag_mission_zero2_passed = 1

				 flag_mission_zero2_failed = 0
				RETURN 	
			ENDIF 


			IF flag_zeros_fuel_comments	= 0
			  //	IF timer_time_limit <= 180000
				IF timer_time_limit_converted_int <= 50

					flag_zeros_fuel_comments = 1

					LOAD_MISSION_AUDIO 1 SOUND_ZER2_DA  //	[ZER2_DA]	Watch your fuel, Carl, only half a tank left!
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  

					PRINT_NOW ( ZER2_DA ) 10000 1		
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE
					CLEAR_THIS_PRINT ZER2_DA
				ENDIF
			ENDIF

			IF flag_zeros_fuel_comments	= 1
			 //	IF timer_time_limit <= 50000
				IF timer_time_limit_converted_int <= 10
					flag_zeros_fuel_comments = 2

					LOAD_MISSION_AUDIO 1 SOUND_ZER2_DB  //	[ZER2_DA]	Watch your fuel, Carl, only half a tank left!
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  

					PRINT_NOW ( ZER2_DB ) 10000 1		
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE
					CLEAR_THIS_PRINT ZER2_DB
				ENDIF
			ENDIF





			IF timer_time_limit_converted_int  <=0
				 //SET_PLAYER_CONTROL Player1 OFF
				IF NOT IS_CAR_DEAD rc_playerbaron_zero2
				  //	 EXPLODE_CAR rc_playerbaron_zero2 
				//  SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
				  SET_CAR_ENGINE_ON rc_playerbaron_zero2 FALSE	  
				  SET_CAR_HEAVY rc_playerbaron_zero2 TRUE
				ENDIF
				CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int
				TIMERA = 0

				 flag_main_sub_function_zero2 = 0
				 flag_fly_next_to_transmittor_zero2 = 0
				 flag_heli_landing_cutscene_zero2 = 0
				 flag_fly_back_to_player_zero2 = 0
				 flag_mission_zero2_passed = 0
				 flag_mission_zero2_failed = 1
				 SET_PLAYER_CONTROL player1 OFF
				 PRINT_NOW ZER2_42 5000 1 // ran out of battery


				 PRINT_BIG M_FAIL 5000 1


				LOAD_MISSION_AUDIO 1 SOUND_ZER1_EC  //  Noooooooooo!
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

			   //	PRINT_NOW ( ZER2_DB ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0

					IF NOT IS_CAR_DEAD rc_playerbaron_zero2
						//	GET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 rotation_velocity_zero2_x rotation_velocity_zero2_y rotation_velocity_zero2_z
						
						  //	IF NOT rotation_velocity_zero2_x = 0.0 
						  //	SET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 0.0 0.0 0.0
						SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
					ENDIF

				ENDWHILE


				REMOVE_RC_BUGGY
				
				IF NOT IS_CHAR_DEAD scplayer
					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

					ELSE
						SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

					ENDIF
					SET_CHAR_HEADING scplayer 90.0
				ENDIF


			   //	WAIT 1000
				SET_FADING_COLOUR 0 0 0
				DO_FADE 1000 FADE_OUT
			
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
	

			  	
				LOAD_SCENE -2245.0264 126.8950 34.3047 
				WAIT 1000
				
				DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE 


				



				


				
				RETURN 	
			ENDIF
			
				
			 
			IF IS_CAR_DEAD rc_playerbaron_zero2

			   
				CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int
				flag_mission_zero2_failed = 1
				SET_PLAYER_CONTROL player1 OFF
				flag_main_sub_function_zero2 = 0
				PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!

 				PRINT_BIG M_FAIL 5000 1


				LOAD_MISSION_AUDIO 1 SOUND_ZER1_EB  //  Noooooooooo!
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

			   //	PRINT_NOW ( ZER2_DB ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0

					IF NOT IS_CAR_DEAD rc_playerbaron_zero2
						SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
					ENDIF

				ENDWHILE
				REMOVE_RC_BUGGY
				IF NOT IS_CHAR_DEAD scplayer
					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

					ELSE
						SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

					ENDIF
					SET_CHAR_HEADING scplayer 90.0
				ENDIF

			//	WAIT 1000
				SET_FADING_COLOUR 0 0 0
				DO_FADE 1000 FADE_OUT
			
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
	

			  	
				LOAD_SCENE -2245.0264 126.8950 34.3047 
				WAIT 1000
				
				DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE 

				 

				RETURN

			ENDIF



			index_zero2 = 0
			WHILE index_zero2 < 5 
				IF NOT IS_CAR_DEAD rc_playerbaron_zero2
					GET_CAR_COORDINATES rc_playerbaron_zero2 coord_redbaron_x coord_redbaron_y coord_redbaron_z
					// checking the guy who flees

					IF flag_is_enemy_van_dead[index_zero2] = 0
						IF NOT IS_CHAR_DEAD char_drivers_zero2[index_zero2]
							IF flag_enemy_van_alarmed[index_zero2]= 1 

							ENDIF
						ELSE
							flag_is_enemy_van_dead[index_zero2] = 1
							REMOVE_BLIP blip_rctiger_zero2[index_zero2]
							MARK_CAR_AS_NO_LONGER_NEEDED car_enemy_vans_zero2[index_zero2]


							IF index_zero2 = 3 // this car has buddy  
							OR index_zero2 = 0

								MARK_CHAR_AS_NO_LONGER_NEEDED char_3_buddy_with_MICRO_UZI[index_zero2]
							ENDIF

							van_death_counter++	   
								flag_is_enemy_van_dead[index_zero2] = 1
								IF van_death_counter = 1
								  //	PRINT_NOW ZER2_23 5000 1 //one van destroyed

									LOAD_MISSION_AUDIO 1 SOUND_ZER2_CA  //	One down, four to go!
								   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								   		WAIT 0
								   	ENDWHILE  

								   	PRINT_NOW ( ZER2_CA ) 10000 1		
								   	PLAY_MISSION_AUDIO 1
								   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								   		WAIT 0
								   	ENDWHILE
								   	CLEAR_THIS_PRINT ZER2_CA
								ENDIF

								IF van_death_counter = 2
									LOAD_MISSION_AUDIO 1 SOUND_ZER2_CC  //	3 left
									WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								   		WAIT 0
								   	ENDWHILE  

								   	PRINT_NOW ( ZER2_CC ) 10000 1		
								   	PLAY_MISSION_AUDIO 1
								   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								   		WAIT 0
								   	ENDWHILE
								   	CLEAR_THIS_PRINT ZER2_CC
								ENDIF

								IF van_death_counter = 3
									LOAD_MISSION_AUDIO 1 SOUND_ZER2_CD  //	Only two left now, Carl – HUNT THEM DOWN!
									WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								   		WAIT 0
								   	ENDWHILE  

								   	PRINT_NOW ( ZER2_CD ) 10000 1		
								   	PLAY_MISSION_AUDIO 1
								   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								   		WAIT 0
								   	ENDWHILE
								   	CLEAR_THIS_PRINT ZER2_CD
								ENDIF

								IF van_death_counter = 4
									LOAD_MISSION_AUDIO 1 SOUND_ZER2_CE  //   [ZER2_CE]	Just one more and Berkley’s mail order department is finished!
									WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								   		WAIT 0
								   	ENDWHILE  

								   	PRINT_NOW ( ZER2_CE ) 10000 1		
								   	PLAY_MISSION_AUDIO 1
								   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								   		WAIT 0
								   	ENDWHILE
								   	CLEAR_THIS_PRINT ZER2_CE
								ENDIF
								//flag_heli_landing_cutscene_zero2 = 0


						ENDIF
					ENDIF
				ENDIF
				
 


				IF NOT IS_CAR_DEAD rc_playerbaron_zero2
					 GET_GAME_TIMER game_timer_zero2

				     IF NOT LOCATE_CAR_3D rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE
				            GET_CAR_COORDINATES rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2
				            stuck_timer_zero2 = game_timer_zero2 + 4000
							flag_self_destruct_zero2 = 0
				     ENDIF

					IF LOCATE_CAR_3D rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE

						IF stuck_timer_zero2 < game_timer_zero2
							IF help_flags_zero1 > 3

								IF flag_self_destruct_zero2 = 0
									PRINT_HELP ZER2_4
									flag_self_destruct_zero2 = 1 
								ENDIF

								IF IS_BUTTON_PRESSED PAD1 CIRCLE
						 			  flag_main_sub_function_zero2 = 0
									  flag_fly_next_to_transmittor_zero2 = 0
									  flag_heli_landing_cutscene_zero2 = 0
									  flag_fly_back_to_player_zero2 = 0
									  flag_mission_zero2_passed = 0
									  flag_mission_zero2_failed = 1
									  SET_PLAYER_CONTROL player1 OFF

									  PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!
									  PRINT_BIG M_FAIL 5000 1
									  SET_CAR_HEALTH rc_playerbaron_zero2 1
									  CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int

  									  IF NOT IS_CHAR_DEAD scplayer
									  	IF IS_CHAR_IN_ANY_CAR scplayer
									  		WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

									  	ELSE
									  		SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

									  	ENDIF
									  	SET_CHAR_HEADING scplayer 90.0
									  ENDIF



									  ADD_EXPLOSION  stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 EXPLOSION_GRENADE

									  LOAD_MISSION_AUDIO 1 SOUND_ZER1_EC  //  Noooooooooo!
									  WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									  	WAIT 0
									  ENDWHILE  

									  PLAY_MISSION_AUDIO 1
									  WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									  		WAIT 0
 									  ENDWHILE

									 REMOVE_RC_BUGGY
								  //	WAIT 1000
									SET_FADING_COLOUR 0 0 0
									DO_FADE 1000 FADE_OUT
								
									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE
						

								  	
									LOAD_SCENE -2245.0264 126.8950 34.3047 
									WAIT 1000
									
									DO_FADE 1000 FADE_IN

									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE 

									  
   			   						RETURN 
									  			
									 

								ENDIF
							ENDIF
						ENDIF
					ENDIF


				

					


					IF NOT IS_CAR_DEAD car_enemy_vans_zero2[index_zero2]

						IF flag_enemy_van_alarmed[index_zero2]= 0
								IF HAS_CAR_BEEN_DAMAGED_BY_CAR car_enemy_vans_zero2[index_zero2] rc_playerbaron_zero2 
								OR HAS_CAR_BEEN_DAMAGED_BY_WEAPON car_enemy_vans_zero2[index_zero2] WEAPONTYPE_M4
									flag_enemy_van_alarmed[index_zero2]= 1
									IF index_zero2 = 2 	   // making sure its not the van where teh guy flees
							   		OR index_zero2 = 1

										IF NOT IS_CHAR_DEAD char_drivers_zero2[index_zero2]
											TASK_LEAVE_CAR_AND_FLEE  char_drivers_zero2[index_zero2] car_enemy_vans_zero2[index_zero2] coord_redbaron_x coord_redbaron_y coord_redbaron_z
										   //	PRINT_NOW ZER2_39 5000 1 // watch out for that guy with the gun!
											TASK_DESTROY_CAR char_drivers_zero2[index_zero2] rc_playerbaron_zero2
											

											IF index_zero2 = 1
												LOAD_MISSION_AUDIO 1 SOUND_ZER2_BB  
											   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											   		WAIT 0
											   	ENDWHILE  

											   	PRINT_NOW ( ZER2_BB ) 10000 1		
											   	PLAY_MISSION_AUDIO 1
											   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											   		WAIT 0
											   	ENDWHILE
											   	CLEAR_THIS_PRINT ZER2_BB
											ENDIF

											IF index_zero2 = 2
												LOAD_MISSION_AUDIO 1 SOUND_ZER2_BA  
											   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											   		WAIT 0
											   	ENDWHILE  

											   	PRINT_NOW ( ZER2_BA ) 10000 1		
											   	PLAY_MISSION_AUDIO 1
											   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											   		WAIT 0
											   	ENDWHILE
											   	CLEAR_THIS_PRINT ZER2_BA
											ENDIF
										ENDIF
									ELSE

										IF NOT IS_CAR_DEAD car_enemy_vans_zero2[index_zero2]
											IF NOT IS_CHAR_DEAD char_drivers_zero2[index_zero2]
												IF NOT index_zero2 = 4 // if not the parked car 4
													TASK_CAR_DRIVE_WANDER char_drivers_zero2[index_zero2] car_enemy_vans_zero2[index_zero2] 12.0 DRIVINGMODE_PLOUGHTHROUGH
												ENDIF
											ENDIF
										ENDIF
									ENDIF

									IF index_zero2 = 3
									OR index_zero2 = 0
										IF NOT IS_CAR_DEAD rc_playerbaron_zero2
											IF NOT IS_CHAR_DEAD char_3_buddy_with_MICRO_UZI[index_zero2]
												TASK_DRIVE_BY char_3_buddy_with_MICRO_UZI[index_zero2] -1 rc_playerbaron_zero2 0.0 0.0 0.0 1000.0 DRIVEBY_AI_ALL_DIRN FALSE 100
											//	PRINT_NOW ZER2_39 5000 1 // watch out for that guy with the gun!
											ENDIF
										ENDIF
									ENDIF 

									IF index_zero2 = 0
										LOAD_MISSION_AUDIO 1 SOUND_ZER1_FH 	///ZER1_FH Fight or die Carl, it's the only choice we have.
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  
									  	PRINT_NOW ( ZER1_FH  ) 10000 1		
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0																						
										ENDWHILE
									  	CLEAR_THIS_PRINT ZER1_FH
									ENDIF




								ENDIF
							
						ENDIF


						IF help_flags_zero1 = 4
							IF NOT IS_CAR_DEAD car_enemy_vans_zero2[index_zero2]
								IF LOCATE_CAR_3D car_enemy_vans_zero2[index_zero2] coord_redbaron_x coord_redbaron_y coord_redbaron_z 50.0 50.0 50.0 FALSE
									IF IS_CAR_ON_SCREEN car_enemy_vans_zero2[index_zero2]  
										IF flag_heli_landing_cutscene_zero2 = 0
											PRINT (ZER2_7) 5000 1 // Line up behind the van and use machine gun
											flag_heli_landing_cutscene_zero2 = 1
										ENDIF
									ENDIF

								ENDIF
							
								IF LOCATE_CAR_3D car_enemy_vans_zero2[index_zero2] coord_redbaron_x coord_redbaron_y coord_redbaron_z 30.0 30.0 30.0 FALSE
									IF IS_CAR_ON_SCREEN car_enemy_vans_zero2[index_zero2]  
										IF flag_heli_landing_cutscene_zero2 = 1
											PRINT_HELP (ZER2_30)  
											flag_heli_landing_cutscene_zero2 = 2
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF	

					ENDIF

				ELSE   

					flag_mission_zero2_failed = 1
					SET_PLAYER_CONTROL player1 OFF
					flag_main_sub_function_zero2 = 0
					PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!

					
					CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int
					IF NOT IS_CHAR_DEAD scplayer
						IF IS_CHAR_IN_ANY_CAR scplayer
							WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

						ELSE
							SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

						ENDIF
						SET_CHAR_HEADING scplayer 90.0
					ENDIF


 					PRINT_BIG M_FAIL 5000 1
					LOAD_MISSION_AUDIO 1 SOUND_ZER1_EB  //  Noooooooooo!
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  

				   //	PRINT_NOW ( ZER2_DB ) 10000 1		
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0

						IF NOT IS_CAR_DEAD rc_playerbaron_zero2
							//	GET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 rotation_velocity_zero2_x rotation_velocity_zero2_y rotation_velocity_zero2_z
							
							  //	IF NOT rotation_velocity_zero2_x = 0.0 
							  //	SET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 0.0 0.0 0.0
							SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
						ENDIF

					ENDWHILE
					REMOVE_RC_BUGGY

				  //	WAIT 1000
					SET_FADING_COLOUR 0 0 0
					DO_FADE 1000 FADE_OUT
				
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
		

				  	
					LOAD_SCENE -2245.0264 126.8950 34.3047 
					WAIT 1000
					
					DO_FADE 1000 FADE_IN

					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE 

				
			  /*	DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE   */


					RETURN

				ENDIF



				index_zero2++
			ENDWHILE

			

	  

			IF flag_is_enemy_van_dead[0] = 1
				IF flag_is_enemy_van_dead[1] = 1
					IF flag_is_enemy_van_dead[2] = 1
						IF flag_is_enemy_van_dead[3] = 1
							IF flag_is_enemy_van_dead[4] = 1
								flag_transmittor_in_place = 1	
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
							



		ENDWHILE

	ENDIF
	
 //	CLEAR_ONSCREEN_TIMER timer_time_limit
	flag_main_sub_function_zero2 = 0
	flag_mission_zero2_passed = 0
	flag_land_plane_zero2 = 1
	flag_help_text = 0
	ADD_BLIP_FOR_COORD -2237.31 140.08 57.23 blip_landing_zero2

  //	[ZER2_EB]  
	
	LOAD_MISSION_AUDIO 1 SOUND_ZER2_EB  //	Now get her back home in one piece, before she runs out of fuel!

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE  

	PRINT_NOW ( ZER2_EB ) 10000 1		
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT ZER2_EB




	PRINT_NOW ZER2_41 5000 1// get back to zeros roof


RETURN	 



land_plane_zero2:

	IF NOT IS_CAR_DEAD rc_playerbaron_zero2
		GET_CAR_SPEED rc_playerbaron_zero2 speed_rc_playerbaron_zero2
				IF IS_BUTTON_PRESSED PAD1 CROSS
					timer_time_limit-=45.0
					timer_time_limit_converted_float = timer_time_limit / timer_time_limit_initial
					timer_time_limit_converted_float*=100.0
					timer_time_limit_converted_int =# timer_time_limit_converted_float
				ENDIF


		IF return_city_zero2 = LEVEL_LOSANGELES
		OR return_city_zero2 = LEVEL_LASVEGAS
			EXPLODE_CAR rc_playerbaron_zero2
			EXPLODE_CAR rc_playerbaron_zero2
		ENDIF	

		GET_GAME_TIMER game_timer_zero2

     	IF NOT LOCATE_CAR_3D rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE
            GET_CAR_COORDINATES rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2
            stuck_timer_zero2 = game_timer_zero2 + 4000
			flag_self_destruct_zero2 = 0
    	 ENDIF

		IF LOCATE_CAR_3D rc_playerbaron_zero2 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE

			IF stuck_timer_zero2 < game_timer_zero2
				IF help_flags_zero1 > 3

					IF flag_self_destruct_zero2 = 0
						PRINT_HELP ZER2_4
						flag_self_destruct_zero2 = 1 
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 CIRCLE
			 			  flag_main_sub_function_zero2 = 0
						  flag_fly_next_to_transmittor_zero2 = 0
						  flag_heli_landing_cutscene_zero2 = 0
						  flag_fly_back_to_player_zero2 = 0
						  flag_mission_zero2_passed = 0
						  flag_mission_zero2_failed = 1
						  SET_PLAYER_CONTROL player1 OFF
						  PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!


						  PRINT_BIG M_FAIL 5000 1
						  SET_CAR_HEALTH rc_playerbaron_zero2 1


							
							CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int

						  IF NOT IS_CHAR_DEAD scplayer
						  	IF IS_CHAR_IN_ANY_CAR scplayer
						  		WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

						  	ELSE
						  		SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

						  	ENDIF
						  	SET_CHAR_HEADING scplayer 90.0
						  ENDIF



						  ADD_EXPLOSION  stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 EXPLOSION_GRENADE

							EXPLODE_CAR rc_playerbaron_zero2
							EXPLODE_CAR rc_playerbaron_zero2

							EXPLODE_CAR rc_playerbaron_zero2
							EXPLODE_CAR rc_playerbaron_zero2

						  LOAD_MISSION_AUDIO 1 SOUND_ZER1_EC  //  Noooooooooo!
						  WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						  	WAIT 0
						  ENDWHILE  

						  PLAY_MISSION_AUDIO 1
						  WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						  		WAIT 0
						  ENDWHILE

							
						   REMOVE_RC_BUGGY

						  //	WAIT 1000
							SET_FADING_COLOUR 0 0 0
							DO_FADE 1000 FADE_OUT
						
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
				

						  	
							LOAD_SCENE -2245.0264 126.8950 34.3047 
							WAIT 1000
							
							DO_FADE 1000 FADE_IN

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE 

						   
  						RETURN 
						  			
						 

					ENDIF
				ENDIF
			ENDIF
		ENDIF



		
		IF LOCATE_CAR_3D rc_playerbaron_zero2 -2237.31 140.08 57.23 4.0 4.0 4.0 TRUE
			SET_PLAYER_CONTROL player1 OFF
			flag_main_sub_function_zero2 = 0
			flag_mission_zero2_passed = 1
			flag_land_plane_zero2 = 0
			flag_help_text = 0

		  SET_CAR_ENGINE_ON rc_playerbaron_zero2 FALSE	  
		  SET_CAR_HEAVY rc_playerbaron_zero2 TRUE




			PRINT_WITH_NUMBER_BIG M_PASS 5000 5000 1
			ADD_SCORE player1 5000
			PLAY_MISSION_PASSED_TUNE 1



			LOAD_MISSION_AUDIO 1 SOUND_ZER2_FB  //	The smell, you know that ozone smell, smells like... victory!

		   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   		WAIT 0
		   	ENDWHILE  

		   	PRINT_NOW ( ZER2_FB ) 10000 1		
		   	PLAY_MISSION_AUDIO 1
		   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		   		WAIT 0
		   	ENDWHILE
		   	CLEAR_THIS_PRINT ZER2_FB


			LOAD_MISSION_AUDIO 1 SOUND_ZER2_FC  //ZER2_FC smells like... victory!

		   	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		   		WAIT 0
		   	ENDWHILE  

		   	PRINT_NOW ( ZER2_FC ) 10000 1		
		   	PLAY_MISSION_AUDIO 1
		   	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		   		WAIT 0
		   	ENDWHILE
		   	CLEAR_THIS_PRINT ZER2_FC




 

			REMOVE_RC_BUGGY
			CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int
			LOAD_SCENE -2245.0264 126.8950 34.3047
			IF NOT IS_CHAR_DEAD scplayer
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203
				ELSE
					SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

				ENDIF
				SET_CHAR_HEADING scplayer 90.0
			ENDIF

			WAIT 1200
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE



			 




			RETURN

			
		ENDIF

	ELSE   

		flag_mission_zero2_failed = 1
		SET_PLAYER_CONTROL player1 OFF
		flag_main_sub_function_zero2 = 0

 			PRINT_BIG M_FAIL 5000 1
			TIMERA = 0

			  //	LOAD_SCENE -2245.0264 126.8950 34.3047 
				

				IF NOT IS_CHAR_DEAD scplayer
					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

					ELSE
						SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

					ENDIF
					SET_CHAR_HEADING scplayer 90.0
				ENDIF

				LOAD_MISSION_AUDIO 1 SOUND_ZER1_EB  //  Noooooooooo!
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

			   //	PRINT_NOW ( ZER2_DB ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0

					IF NOT IS_CAR_DEAD rc_playerbaron_zero2
						//	GET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 rotation_velocity_zero2_x rotation_velocity_zero2_y rotation_velocity_zero2_z
						
						  //	IF NOT rotation_velocity_zero2_x = 0.0 
						  //	SET_CAR_ROTATION_VELOCITY rc_playerbaron_zero2 0.0 0.0 0.0
						SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
					ENDIF

				ENDWHILE

				REMOVE_RC_BUGGY
			  //	WAIT 1000
				SET_FADING_COLOUR 0 0 0
				DO_FADE 1000 FADE_OUT
			
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
	

			  	
				LOAD_SCENE -2245.0264 126.8950 34.3047 
				WAIT 1000
 


				
				DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE 
				 


		PRINT_NOW ( ZER2_8 ) 10000 1 // You crashed your plane!
		RETURN



	ENDIF 

	IF flag_zeros_fuel_comments	= 0
  //		IF timer_time_limit <= 180000
		IF timer_time_limit_converted_int <= 50
			flag_zeros_fuel_comments = 1

			LOAD_MISSION_AUDIO 1 SOUND_ZER2_DA  //	[ZER2_DA]	Watch your fuel, Carl, only half a tank left!
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( ZER2_DA ) 10000 1		
			PLAY_MISSION_AUDIO 1
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT ZER2_DA
		ENDIF
	ENDIF

	IF flag_zeros_fuel_comments	= 1
	  //	IF timer_time_limit <= 50000
	  IF timer_time_limit_converted_int <= 20

			flag_zeros_fuel_comments = 2

			LOAD_MISSION_AUDIO 1 SOUND_ZER2_DB  //	[ZER2_DA]	Watch your fuel, Carl, only half a tank left!
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( ZER2_DB ) 10000 1		
			PLAY_MISSION_AUDIO 1
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT ZER2_DB
		ENDIF
	ENDIF


  //	IF timer_time_limit <=0
	IF timer_time_limit_converted_int <= 0

		IF NOT IS_CAR_DEAD rc_playerbaron_zero2
			SET_CAR_ENGINE_ON rc_playerbaron_zero2 FALSE
			SET_CAR_HEAVY rc_playerbaron_zero2 TRUE
		ENDIF
		TIMERA = 0
		 flag_main_sub_function_zero2 = 0
		 flag_fly_next_to_transmittor_zero2 = 0
		 flag_heli_landing_cutscene_zero2 = 0
		 flag_fly_back_to_player_zero2 = 0
		 flag_mission_zero2_passed = 0
		 flag_mission_zero2_failed = 1
		 SET_PLAYER_CONTROL player1 OFF
		 PRINT_NOW ZER2_42 5000 1 // ran out of time

 		PRINT_BIG M_FAIL 5000 1
				TIMERA = 0

				
				CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int
				IF NOT IS_CHAR_DEAD scplayer
					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203

					ELSE
						SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203

					ENDIF
					SET_CHAR_HEADING scplayer 90.0
				ENDIF

				LOAD_MISSION_AUDIO 1 SOUND_ZER1_EC  //  Noooooooooo!
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

			   //	PRINT_NOW ( ZER2_DB ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0

					IF NOT IS_CAR_DEAD rc_playerbaron_zero2
						SET_CAR_FORWARD_SPEED rc_playerbaron_zero2 0.0
					ENDIF

				ENDWHILE
			  //	CLEAR_THIS_PRINT ZER2_DB

			   /*	DO_FADE 1000 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE */
 		  //		WAIT 1000
				REMOVE_RC_BUGGY
				SET_FADING_COLOUR 0 0 0
				DO_FADE 1000 FADE_OUT
			
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
	

			  	
				LOAD_SCENE -2245.0264 126.8950 34.3047 
				WAIT 1000
 

				
				DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE 



				

			 //	WAIT 2500
				
			 /*	DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE   */


		RETURN 	
	ENDIF
			
RETURN




// *****************************
// MISSION PASSED               
// *****************************

mission_zero2_passed:
	//flag_pp1_mission1_passed = 1

   //	SET_PLAYER_CONTROL player1 ON
	CLEAR_WANTED_LEVEL player1
	REGISTER_MISSION_PASSED ( zero_2 )
	PLAYER_MADE_PROGRESS 1
	flag_zero_mission_counter++



	//START_NEW_SCRIPT pp1_mission_loop
RETURN
		
// *****************************
// mission failed
// *****************************

mission_zero2_failed:



  //	PRINT_BIG M_FAIL 5000 1


RETURN

// *****************************
// mission cleanup
// *****************************

mission_cleanup_zero2:
	DISABLE_ALL_ENTRY_EXITS FALSE
	CAMERA_RESET_NEW_SCRIPTABLES

	DISPLAY_CAR_NAMES TRUE


	IF IS_PLAYER_PLAYING Player1
		SET_PLAYER_CONTROL player1 ON
		IF IS_CHAR_IN_ANY_CAR scplayer

			WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203
			SET_CHAR_HEADING scplayer 90.0

		ENDIF

	ENDIF

	flag_player_on_mission = 0
	SET_POLICE_IGNORE_PLAYER player1 OFF

	IF NOT IS_CAR_DEAD rc_van_zero2 
		SET_CAR_HEALTH rc_van_zero2 300
		SET_LOAD_COLLISION_FOR_CAR_FLAG rc_van_zero2 TRUE

		SET_CAR_PROOFS rc_van_zero2 FALSE FALSE FALSE FALSE FALSE
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED rc_van_zero2



	RELEASE_WEATHER

	REMOVE_BLIP blip_landing_zero2

	DELETE_CAR car_enemy_vans_zero2[0]
	DELETE_CAR car_enemy_vans_zero2[1]
	DELETE_CAR car_enemy_vans_zero2[2]
	DELETE_CAR car_enemy_vans_zero2[3]
	DELETE_CAR car_enemy_vans_zero2[4]
	DELETE_CHAR char_3_buddy_with_MICRO_UZI[0]
	DELETE_CHAR char_3_buddy_with_MICRO_UZI[3]



	REMOVE_BLIP blip_rctiger_zero2[0] 
	REMOVE_BLIP blip_rctiger_zero2[1] 
	REMOVE_BLIP blip_rctiger_zero2[2] 
	REMOVE_BLIP blip_rctiger_zero2[3] 
	REMOVE_BLIP blip_rctiger_zero2[4] 
	







  //	DELETE_CAR rc_cutscene_heli_zero2


	REMOVE_RC_BUGGY

	MARK_MODEL_AS_NO_LONGER_NEEDED topfun
	MARK_MODEL_AS_NO_LONGER_NEEDED rcbaron
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI

	MARK_MODEL_AS_NO_LONGER_NEEDED mtbike
	MARK_MODEL_AS_NO_LONGER_NEEDED sanchez



	UNLOAD_SPECIAL_CHARACTER 1

	REMOVE_CHAR_ELEGANTLY char_zero_zero2

	CLEAR_ONSCREEN_COUNTER timer_time_limit_converted_int

	 
	GET_GAME_TIMER timer_mobile_start
	CLEAR_PRINTS

	MISSION_HAS_FINISHED
RETURN

}
