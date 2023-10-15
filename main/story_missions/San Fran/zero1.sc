MISSION_START
// *****************************************************************************************
// ************************************* RC TURF WAR ************************************** 
// ************************************* Scramble! *****************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_zero1

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_zero1_failed
	ENDIF

GOSUB mission_cleanup_zero1

MISSION_END

{
// Variables for mission

//RC VEHICLES
LVAR_INT object_turret_zero1

LVAR_INT rc_enemy_helis_zero1[10]

LVAR_INT object_transmitter[10]
//LVAR_INT rc_enemy_kamikaze_zero1 





// CHARS						
LVAR_INT char_zero_zero1  
		


LVAR_INT zeros_task_status_zero1

// COORDS


LVAR_FLOAT coord_rc_van_x coord_rc_van_y coord_rc_van_z

LVAR_FLOAT coord_rc_enemy_helis_zero1_x[8] coord_rc_enemy_helis_zero1_y[8]

LVAR_FLOAT coord_baron_z_offset_zero1
LVAR_FLOAT coord_transmitter_x[4] coord_transmitter_y[4] coord_transmitter_z[4] 
LVAR_FLOAT coord_explosion_x_zero1 coord_explosion_y_zero1 coord_explosion_z_zero1
LVAR_FLOAT baron_offset_x baron_offset_y baron_offset_z
LVAR_FLOAT coord_enemy_circling1_x[8] coord_enemy_circling1_y[8] coord_enemy_circling1_z[8]
LVAR_FLOAT coord_enemy_circling2_x[8] coord_enemy_circling2_y[8] coord_enemy_circling2_z[8]

LVAR_FLOAT heading_destroyed_transmittors[4]



 

// FLAGS
LVAR_INT flag_zero1_create_rc_baddies
LVAR_INT flag_zero1_kill_8_helis
LVAR_INT flag_zero1_mission_zero1_passed
LVAR_INT flag_zero1_mission_zero1_failed 
LVAR_INT flag_zero1_created_rc_enemies 
LVAR_INT flag_zero1_rc_enemy_helis_zero1_dead[8]
LVAR_INT flag_transmitter1_destroyed  flag_transmitter2_destroyed  flag_transmitter3_destroyed flag_transmitter4_destroyed 
LVAR_INT flag_create_kamikaze_baron_zero1
LVAR_INT flag_is_zero_panicing 
LVAR_INT flag_kamakazie_comment
VAR_INT fleet_destroyed_counter



LVAR_INT flag_enemy_objective[10]


LVAR_INT flag_cutscene_zero1 

// LVAR_INT index

// BLIP
LVAR_INT blip_zero1_rc_enemy_helis_zero1[10]
//LVAR_INT blip_rc_enemy_kamikaze_zero1  





// BOMB STUFF
LVAR_INT primed_bomb_1_zero1[10]
LVAR_INT falling_bomb_1_zero1[10] 
LVAR_INT R1_button_pressed_zero1[10]
LVAR_INT timer_zero1[10]

LVAR_INT next_bomb_timer_zero1[10]

LVAR_FLOAT distance_to_ground_zero1[10]

LVAR_INT primed_bomb_flag_zero1[10]

LVAR_FLOAT primed_bomb_z_zero1[10]
LVAR_FLOAT grenade_zero1_x[10] grenade_zero1_y[10] grenade_zero1_z[10] 

LVAR_INT circle_button_timer_zero1[10]
									
LVAR_INT detonation_timer_zero1[10] 
LVAR_FLOAT floor_z_zero1[10]

LVAR_INT index_zero1

LVAR_INT index_target

LVAR_INT index_random_coord



LVAR_INT transmitter1_health_zero1 transmitter2_health_zero1 transmitter3_health_zero1 transmitter4_health_zero1


VAR_INT transmitter_overallhealth
VAR_INT mission_countdown_zero1 


//DECISION MAKER
LVAR_INT dm_buddy_zero1


// Timers
LVAR_INT timer_heli_creation_time[10]
LVAR_INT timer_heli_time_to_leave[10]
LVAR_INT timer_current_time_zero1
LVAR_INT mission_start_time_zero1 time_since_mission_started_zero1

LVAR_INT day_hours_zero1 day_mins_zero1

								   


// zeros comments
LVAR_INT flag_squad_comment_zero1
LVAR_INT flag_i_thought_u_could_shoot_zero1
LVAR_INT flag_cocky_comment_zero1
LVAR_INT flag_shooting_zero_zero1


// FIRES

//LVAR_INT fire1_zero1
LVAR_INT fire_transmittor[4]


// sequences

LVAR_INT seq_zero_success_cut_zero1









// VAR_FLOAT ground_z
 

// ****************************************Mission Start************************************

mission_start_zero1:

	flag_player_on_mission = 1
	DISABLE_ALL_ENTRY_EXITS TRUE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
	REGISTER_MISSION_GIVEN
	SCRIPT_NAME ZERO1

	SET_PLAYER_CONTROL player1 OFF





	LOAD_MISSION_TEXT ZERO1

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	SET_AREA_VISIBLE 6	//zeros toy shop

	SET_CHAR_COORDINATES scplayer -2214.4363 123.9208 56.4219

	GET_TIME_OF_DAY day_hours_zero1 day_mins_zero1
	
	/////////////////////////////////
	// TO FIX PC BUG 221 
	IF day_hours_zero1 > 21
	OR day_hours_zero1 < 4
		day_hours_zero1 = 5
   		 SET_TIME_OF_DAY day_hours_zero1 day_mins_zero1
	ENDIF
	/////////////////////////////////
	





	LOAD_CUTSCENE ZERO_1


	// LOAD MODELS
	 
   //	REQUEST_MODEL minigun_base
	//REQUEST_MODEL rcraider
	REQUEST_MODEL fire_ex

  //	REQUEST_MODEL wongs_erection

	//REQUEST_MODEL skimmer
							   




	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE

	 
	START_CUTSCENE
	WAIT 200

	DO_FADE 1000 FADE_IN
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE
	 

	SET_PLAYER_CONTROL player1 OFF

	SET_FADING_COLOUR 0 0 0
	DO_FADE 0 FADE_OUT

	WHILE GET_FADING_STATUS
		 WAIT 0
	ENDWHILE
	CLEAR_CUTSCENE 

	SET_AREA_VISIBLE 0





	FORCE_WEATHER_NOW WEATHER_SUNNY_SF	  // can be wither sunny, foggy or extra sunny




	// INITIALISING VARIABLES

	flag_squad_comment_zero1 = 0
	flag_cocky_comment_zero1 = 0
	flag_i_thought_u_could_shoot_zero1 = 0
	flag_shooting_zero_zero1 = 0
	flag_kamakazie_comment = 0


	flag_zero1_create_rc_baddies = 1
	flag_zero1_kill_8_helis = 0

	flag_zero1_mission_zero1_passed = 0
	flag_zero1_mission_zero1_failed = 0

	flag_is_zero_panicing = 0

	flag_cutscene_zero1 = 0

	fleet_destroyed_counter = 0
	   
	flag_transmitter1_destroyed = 0
	flag_transmitter2_destroyed = 0
	flag_transmitter3_destroyed = 0
	flag_transmitter4_destroyed = 0

	flag_create_kamikaze_baron_zero1 = 0



	mission_countdown_zero1 = 210000




	coord_rc_van_x = -2239.4529 
	coord_rc_van_y = 120.4904 
	coord_rc_van_z = 56.6585 

	heading_destroyed_transmittors[0] =	97.5
	heading_destroyed_transmittors[1] =	160.0
	heading_destroyed_transmittors[2] =	14.0
	heading_destroyed_transmittors[3] =	280.0

    coord_rc_enemy_helis_zero1_x[0] = -2321.4958
	coord_rc_enemy_helis_zero1_y[0] = 211.3490 
						  	    
	coord_rc_enemy_helis_zero1_x[1] = -2252.5811 
	coord_rc_enemy_helis_zero1_y[1] = 235.0999 
						 	   
	coord_rc_enemy_helis_zero1_x[2] = -2122.6934 
	coord_rc_enemy_helis_zero1_y[2] = 65.5520 
						 
	coord_rc_enemy_helis_zero1_x[3] = -2233.5403 
	coord_rc_enemy_helis_zero1_y[3] = 16.5174 

	coord_rc_enemy_helis_zero1_x[4]= -2508.4565 
	coord_rc_enemy_helis_zero1_y[4]=	181.6713 

	coord_rc_enemy_helis_zero1_x[5]=	-2502.9084 
	coord_rc_enemy_helis_zero1_y[5]=	184.0278 

	coord_rc_enemy_helis_zero1_x[6] =	-2508.3684 
	coord_rc_enemy_helis_zero1_y[6] =	183.8913 

	coord_rc_enemy_helis_zero1_x[7]=	-2504.0205 
	coord_rc_enemy_helis_zero1_y[7]=	187.1988 

	index_random_coord = 0

						
	// transmitters health

	transmitter1_health_zero1 = 25
	transmitter2_health_zero1 =	25
	transmitter3_health_zero1 =	25
	transmitter4_health_zero1 =	25

	coord_transmitter_x[0] = -2237.24
	coord_transmitter_y[0] = 143.33
	coord_transmitter_z[0] = 57.0 

	coord_transmitter_x[1] = -2219.5 
	coord_transmitter_y[1] = 128.68 
	coord_transmitter_z[1] = 56.72 

	coord_transmitter_x[2] = -2224.5
	coord_transmitter_y[2] = 149.05
	coord_transmitter_z[2] = 59.01

	coord_transmitter_x[3] = -2226.7
	coord_transmitter_y[3] = 94.8
	coord_transmitter_z[3] = 50.87



	transmitter_overallhealth = transmitter1_health_zero1
	transmitter_overallhealth += transmitter2_health_zero1
	transmitter_overallhealth += transmitter3_health_zero1
	transmitter_overallhealth += transmitter4_health_zero1 


	GET_GAME_TIMER timer_heli_creation_time[4]	 // for the pace of RC fleet of planes 

	transmitter_overallhealth = 100
	flag_zero1_created_rc_enemies = 0
	flag_zero1_rc_enemy_helis_zero1_dead[0] = 1
	flag_zero1_rc_enemy_helis_zero1_dead[1] = 1
	flag_zero1_rc_enemy_helis_zero1_dead[2] = 1
	flag_zero1_rc_enemy_helis_zero1_dead[3] = 1


	// the fleet
	flag_zero1_rc_enemy_helis_zero1_dead[4] = 1 
	flag_zero1_rc_enemy_helis_zero1_dead[5] = 1 
	flag_zero1_rc_enemy_helis_zero1_dead[6] = 1 
	flag_zero1_rc_enemy_helis_zero1_dead[7] = 1 

	index_target = 0



	TIMERA = 0

	index_zero1 = 0

	//SWITCH_ROADS_OFF -2471.72 -89.28 26.25 -2037.99 386.94 98.0	


	SET_CAR_DENSITY_MULTIPLIER 0.0

   	CREATE_OBJECT minigun_base coord_rc_van_x coord_rc_van_y coord_rc_van_z object_turret_zero1
	SET_OBJECT_COLLISION object_turret_zero1 FALSE
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED


   //	SET_CHAR_COORDINATES scplayer -2239.7029 119.9904 56.8985
	SET_PLAYER_CONTROL player1 OFF
	SET_CHAR_COORDINATES scplayer -2214.4363 123.9208 56.4219

	SET_OBJECT_HEADING object_turret_zero1 90.0 
   	
	
  //	52.6436 

 //  	SET_CHAR_HEADING scplayer 90.0


	DISPLAY_ONSCREEN_TIMER_WITH_STRING mission_countdown_zero1 TIMER_DOWN ZER1_26
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING transmitter_overallhealth COUNTER_DISPLAY_BAR ZER1_20 // transmitters signal strength
	GET_GAME_TIMER mission_start_time_zero1


 /////////////////////////////////////////////////////////
	// CREATING 4 transmitters at the corner of buildings
	/////////////////////////////////////////////////////////
	index_zero1 = 0
	WHILE index_zero1 < 4
		CREATE_OBJECT wongs_erection coord_transmitter_x[index_zero1] coord_transmitter_y[index_zero1] coord_transmitter_z[index_zero1] object_transmitter[index_zero1] 
	   //	SET_OBJECT_PROOFS object_transmitter[index_zero1] TRUE TRUE TRUE TRUE TRUE
		index_zero1++
	ENDWHILE   
	// ////////////////////////////////////////////////////



	WHILE NOT HAS_MODEL_LOADED fire_ex 
	   //	OR NOT HAS_MODEL_LOADED minigun_base
	   //	OR NOT HAS_MODEL_LOADED wongs_erection
		WAIT 0
	ENDWHILE


	LOAD_SPECIAL_CHARACTER 1 zero
	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		WAIT 0
	ENDWHILE   


















	 



//////////////////////////
// Initialise
//////////////////////////
		SWITCH_WIDESCREEN ON






       //	LOAD_SCENE -2261.8384 118.3496 55.8344 


		//SET_FIXED_CAMERA_POSITION -2261.8384 118.3496 55.8344  0.0 0.0 0.0
	   //	POINT_CAMERA_AT_POINT -2261.1230 118.8753 55.3739 JUMP_CUT

	   	CAMERA_RESET_NEW_SCRIPTABLES
	    CAMERA_PERSIST_TRACK TRUE                   
	    CAMERA_PERSIST_POS TRUE                       
	    CAMERA_SET_VECTOR_MOVE  -2261.8384 118.3496 55.8344 -2248.4685 121.3517 60.4838    6800 TRUE    // two sets of coord for cam start point and end point + time for pan
	    CAMERA_SET_VECTOR_TRACK -2261.1230 118.8753 55.3739 -2247.6099 121.8521 60.3731     6800 TRUE    // two sets of coords for cam aim at start and end point + time for pan
			

		REQUEST_ANIMATION ON_LOOKERS
		



   		LOAD_SCENE_IN_DIRECTION	-2259.8384 122.3496 40.8344	290.0

		WHILE NOT HAS_ANIMATION_LOADED ON_LOOKERS
			WAIT 0
		ENDWHILE
		WAIT 1000




		/////////////////////////////
		// Wee cutscene here									.
		/////////////////////////////

		DO_FADE 500 FADE_IN
		SET_FADING_COLOUR 0 0 0

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE







 









	

	// CREATING ONE transmitter IN THE MIDDLE OF BUILDING

	/*	CREATE_CAR wongs_erection coord_transmitter_x[0] coord_transmitter_y[0] coord_transmitter_z[0] object_transmitter[0] 
		SET_CAR_PROOFS object_transmitter[0] TRUE TRUE TRUE TRUE TRUE
		index_zero1++
											   */


	// Create zero with fire extinguisher	



  //	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -2231.3232 129.7854 56.5985 char_zero_zero1
	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -2214.29 125.49 56.5985 char_zero_zero1
	SET_CHAR_HEALTH char_zero_zero1 1000
   	SET_CHAR_PROOFS char_zero_zero1 FALSE TRUE TRUE FALSE FALSE
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_buddy_zero1

	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_POTENTIAL_WALK_INTO_FIRE 
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_FIRE_NEARBY 
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_FIRE_NEARBY TASK_COMPLEX_EXTINGUISH_FIRES 100.0 100.0 100.0 100.0 1 1


	SET_CHAR_DECISION_MAKER char_zero_zero1 dm_buddy_zero1	
   //	TASK_PLAY_ANIM char_zero_zero1 prtial_gngtlkB PED 4.0 TRUE FALSE FALSE TRUE 0
   //	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_FIRE_NEARBY TASK_COMPLEX_EXTINGUISH_FIRE_ON_FOOT 0.0 100.0 0.0 0.0 TRUE FALSE

	





	IF NOT IS_CHAR_DEAD char_zero_zero1

		OPEN_SEQUENCE_TASK seq_zero_success_cut_zero1
			TASK_GO_STRAIGHT_TO_COORD -1 -2231.3232 129.7854 56.5985 PEDMOVE_RUN -1
			TASK_ACHIEVE_HEADING -1 0.0
			TASK_PLAY_ANIM -1 lkup_loop ON_LOOKERS 4.0 TRUE FALSE FALSE TRUE 0

			   //	SET_SEQUENCE_TO_REPEAT seq_zero_success_cut_zero1 1
		CLOSE_SEQUENCE_TASK seq_zero_success_cut_zero1
		PERFORM_SEQUENCE_TASK char_zero_zero1 seq_zero_success_cut_zero1
		CLEAR_SEQUENCE_TASK seq_zero_success_cut_zero1




	ENDIF 
	OPEN_SEQUENCE_TASK seq_zero_success_cut_zero1

		TASK_GO_STRAIGHT_TO_COORD -1 -2239.7029 119.9904 56.5985 PEDMOVE_RUN -1
		TASK_ACHIEVE_HEADING -1 0.0

	   //	SET_SEQUENCE_TO_REPEAT seq_zero_success_cut_zero1 1
	CLOSE_SEQUENCE_TASK seq_zero_success_cut_zero1
	PERFORM_SEQUENCE_TASK scplayer seq_zero_success_cut_zero1
	CLEAR_SEQUENCE_TASK seq_zero_success_cut_zero1

 	
 	
 	LOAD_MISSION_AUDIO 1 SOUND_ZER1_AA
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE  
  	PRINT_NOW ( ZER1_AA ) 10000 1		//Berkley’s launched a full-scale attack!
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT ZER1_AA 


	////////////////////////////////////////////


		REQUEST_MODEL wongs_erection2
		REQUEST_MODEL rcbaron
		REQUEST_MODEL minigun
		REQUEST_MODEL rcbomb





		WHILE NOT HAS_MODEL_LOADED wongs_erection2
			OR NOT HAS_MODEL_LOADED rcbaron
			OR NOT HAS_MODEL_LOADED rcbomb
				OR NOT HAS_MODEL_LOADED minigun
			WAIT 0
		ENDWHILE	

		index_zero1 = 0
		WHILE index_zero1 < 8
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blip_zero1_rc_enemy_helis_zero1[index_zero1]
			REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]
		index_zero1++
		ENDWHILE	

	




// ******************************************** START MAIN LOOP **********************************
		
	 main_zero1_loop:
		WAIT 0 
		// Player must fly heli near the danger zone before engaging in combat
			IF flag_zero1_create_rc_baddies = 1
				GOSUB create_rc_baddies
			ENDIF

		// The RC helis start to attack the baron
			IF flag_zero1_kill_8_helis = 1
				GOSUB kill_8_helis
			ENDIF



			IF flag_zero1_mission_zero1_passed = 1
				GOTO mission_zero1_passed
			ENDIF

			IF flag_zero1_mission_zero1_failed = 1
				GOTO mission_zero1_failed
			ENDIF

		GOTO main_zero1_loop

// ************************************     Sub Functions *****************************************


//////////////////////////////////////////
// ////////// CREATING ENEMY HELIS
//////////////////////////////////////////

		create_enemy_helis_zero1:

			CLEAR_AREA -2208.1 175.86 40.46 25.0 TRUE 
			
			IF index_zero1 < 4 
			 		CREATE_CAR rcbaron coord_rc_enemy_helis_zero1_x[index_random_coord] coord_rc_enemy_helis_zero1_y[index_random_coord] 70.0 rc_enemy_helis_zero1[index_zero1]
					TURN_CAR_TO_FACE_COORD rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target]	
				 	SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 260 
					ADD_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1] 3.0 3000
					SET_CAR_PROOFS rc_enemy_helis_zero1[index_zero1] FALSE FALSE TRUE FALSE FALSE
					PLANE_STARTS_IN_AIR rc_enemy_helis_zero1[index_zero1]
					SET_CAR_FORWARD_SPEED rc_enemy_helis_zero1[index_zero1] 14.0
					
					IF NOT DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[index_zero1]
						ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1[index_zero1]
						CHANGE_BLIP_DISPLAY blip_zero1_rc_enemy_helis_zero1[index_zero1] BLIP_ONLY 	

					ELSE
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]

				 		ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1[index_zero1]

						CHANGE_BLIP_DISPLAY blip_zero1_rc_enemy_helis_zero1[index_zero1] BLIP_ONLY 	

					ENDIF	   


					SET_CAR_STATUS rc_enemy_helis_zero1[index_zero1] STATUS_PHYSICS 


				// *************************************
				// ARMING PLANE WITH STINK BOMBS
				// *************************************
					CREATE_OBJECT rcbomb coord_rc_enemy_helis_zero1_x[index_zero1] coord_rc_enemy_helis_zero1_y[index_zero1] 130.0 primed_bomb_1_zero1[index_zero1]
					ATTACH_OBJECT_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 -0.3 0.0 0.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 primed_bomb_z_zero1[index_zero1]
					SET_OBJECT_COLLISION primed_bomb_1_zero1[index_zero1] FALSE
					SET_OBJECT_DYNAMIC primed_bomb_1_zero1[index_zero1] FALSE

					///////////////////////////
					// HELI OBJECTIVES
					////////////////////////////
					  //	HELI_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_zero1] coord_transmitter_y[index_zero1] coord_transmitter_z[index_zero1] -1.0 85.0 
					  //	HELI_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0 coord_transmitter_z[index_target] 
					coord_baron_z_offset_zero1 = coord_transmitter_z[index_target] + 10.5
			  
					PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 13.5 
					SET_PLANE_THROTTLE rc_enemy_helis_zero1[index_zero1] -0.8

				   //	ACTIVATE_HELI_SPEED_CHEAT rc_enemy_helis_zero1[index_zero1] 60
					flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0 //reseting flag
					flag_enemy_objective[index_zero1] = 0
					flag_zero1_created_rc_enemies = 1
					GET_GAME_TIMER timer_heli_creation_time[index_zero1]
				  //	timer_heli_time_to_leave[index_zero1] = timer_heli_creation_time[index_zero1] + 30 
				   
				   
				   index_random_coord++
				   IF index_random_coord > 3
				   		index_random_coord = 0
				   ENDIF



					SET_POLICE_IGNORE_PLAYER player1 ON
					SET_WANTED_MULTIPLIER 0.001		
		


				
			ENDIF

		RETURN
			


/////////////////////////////////////////////
////   CREATING RC BARONS FLEET
/////////////////////////////////////////////
		create_enemy_planes_zero1:	

			IF index_zero1 > 4 
			OR index_zero1 = 4 			
		  		CREATE_CAR rcbaron coord_rc_enemy_helis_zero1_x[index_zero1] coord_rc_enemy_helis_zero1_y[index_zero1] 85.0 rc_enemy_helis_zero1[index_zero1]
				TURN_CAR_TO_FACE_COORD rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target]
				SET_CAR_PROOFS rc_enemy_helis_zero1[index_zero1] FALSE TRUE TRUE FALSE FALSE
			   	SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 260 
				ADD_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1] 3.0 10000

				IF NOT DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[index_zero1]
			 		ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1[index_zero1]
				 //	CHANGE_BLIP_COLOUR blip_zero1_rc_enemy_helis_zero1[index_zero1] RED
					CHANGE_BLIP_DISPLAY blip_zero1_rc_enemy_helis_zero1[index_zero1] BLIP_ONLY 	
				ELSE
					REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]

			 		ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1[index_zero1]
				  //	CHANGE_BLIP_COLOUR blip_zero1_rc_enemy_helis_zero1[index_zero1] RED
					CHANGE_BLIP_DISPLAY blip_zero1_rc_enemy_helis_zero1[index_zero1] BLIP_ONLY 	
						
				ENDIF		 
				//GOSUB add_baron_blip_zero1
			
				SET_CAR_STATUS rc_enemy_helis_zero1[index_zero1] STATUS_PHYSICS
				SET_CAR_HEADING rc_enemy_helis_zero1[index_zero1] 258.0
	 
				PLANE_STARTS_IN_AIR rc_enemy_helis_zero1[index_zero1]
				SET_CAR_FORWARD_SPEED rc_enemy_helis_zero1[index_zero1] 14.0

			 //  	ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1s_zero1[index_zero1]

				flag_enemy_objective[index_zero1] = 0
				flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0

			// *************************************
			// ARMING PLANE WITH STINK BOMBS
			// *************************************

				CREATE_OBJECT rcbomb coord_rc_enemy_helis_zero1_x[index_zero1] coord_rc_enemy_helis_zero1_y[index_zero1] 120.0 primed_bomb_1_zero1[index_zero1]
				ATTACH_OBJECT_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 -0.3 0.0 0.0 0.0
				PLACE_OBJECT_RELATIVE_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 primed_bomb_z_zero1[index_zero1]
				SET_OBJECT_COLLISION primed_bomb_1_zero1[index_zero1] FALSE
				SET_OBJECT_DYNAMIC primed_bomb_1_zero1[index_zero1] FALSE
				GET_GAME_TIMER timer_heli_creation_time[index_zero1]


			  //	IF index_zero1 = 4 
			  //	PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[3] coord_transmitter_y[3] 75.0 -1.0 -1.0 
				coord_baron_z_offset_zero1 = coord_transmitter_z[index_target] + 10.5
		   	  	PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 69.5 
				SET_PLANE_THROTTLE rc_enemy_helis_zero1[index_zero1] -0.8

				 //	[ZER1_BF]	Squadron at 3 o’clock, Carl!

					IF NOT IS_CHAR_DEAD char_zero_zero1
					   	IF flag_squad_comment_zero1 = 0
						   	LOAD_MISSION_AUDIO 1 SOUND_ZER1_BD
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( ZER1_BD ) 10000 1		//BOGIES 12 o clock
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT ZER1_BD 
							
							flag_squad_comment_zero1 = 1
						ENDIF
					ENDIF	



	
			ENDIF
		RETURN



/////////////////////////////////////////////

create_rc_baddies:
	
	TIMERA = 0
	
	///////////////////////////////////////////////
	// 1st Fleet
	///////////////////////////////////////////////

	IF fleet_destroyed_counter = 0

		flag_cutscene_zero1 = 0

		TIMERB = 0
		WHILE NOT flag_cutscene_zero1 = 10
			WAIT 0
			IF flag_cutscene_zero1 = 0
	  	
 			  		LOAD_MISSION_AUDIO 1 SOUND_ZER1_AB
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  
					PRINT_NOW ( ZER1_AB ) 10000 1		//This is insane!
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE
					CLEAR_THIS_PRINT ZER1_AC 

 			  		LOAD_MISSION_AUDIO 1 SOUND_ZER1_AC
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  
					PRINT_NOW ( ZER1_AC ) 10000 1		//All batteries commence fire!
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0																						

					ENDWHILE
					CLEAR_THIS_PRINT ZER1_AC 

				flag_cutscene_zero1 = 1
			ENDIF

			IF TIMERB > 4500
				IF flag_cutscene_zero1 = 1
					
					flag_cutscene_zero1 = 2
				    //PRINT_NOW ZER1_14 5000 1	// the transmitters need protecting

					CAMERA_RESET_NEW_SCRIPTABLES
					SET_FIXED_CAMERA_POSITION -2240.4846 119.5912 58.4997 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2239.8755 120.3760 58.6143 JUMP_CUT
					CLEAR_AREA coord_rc_van_x coord_rc_van_y coord_rc_van_z 1000.0 TRUE

 			  		LOAD_MISSION_AUDIO 1 SOUND_ZER1_BA
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE  
					PRINT_NOW ( ZER1_BA ) 10000 1		//He’s going for my transmitters!
					PLAY_MISSION_AUDIO 1
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE
					CLEAR_THIS_PRINT ZER1_BA 

				ENDIF
			ENDIF

			IF TIMERB > 7500

				IF flag_cutscene_zero1 = 2
						flag_cutscene_zero1 = 3
						SET_FIXED_CAMERA_POSITION -2231.6758 127.7127 57.9036 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -2231.9646 128.6361 58.1563 JUMP_CUT


						IF NOT IS_CHAR_DEAD char_zero_zero1
						//	SET_CHAR_COORDINATES scplayer -2231.3232 129.7854 56.5985
							TASK_PLAY_ANIM char_zero_zero1 Pointup_in ON_LOOKERS 4.0 FALSE FALSE FALSE TRUE 0
						ENDIF 

						LOAD_MISSION_AUDIO 1 SOUND_ZER1_BB

						IF flag_zero1_created_rc_enemies = 0
							index_zero1 = 0
							WHILE index_zero1 < 2
								WAIT 0
								GOSUB create_enemy_helis_zero1
							  //	index_random_coord++
								index_zero1++
							ENDWHILE
						ENDIF

		 			  	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE  
						PRINT_NOW ( ZER1_BB ) 10000 1		//If if takes them out, I’ll never be able to launch a counter attack!
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT ZER1_BB 
					ENDIF
				ENDIF
				
			IF TIMERB > 12000
				IF flag_cutscene_zero1 = 3
					IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[1]
						//	ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE rc_enemy_helis_zero1[0] 0.5 -5.0 1.0  rc_enemy_helis_zero1[0] 2.0 JUMP_CUT 
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[1] 2.0 1.5 -1.0 baron_offset_x baron_offset_y baron_offset_z
						SET_FIXED_CAMERA_POSITION baron_offset_x baron_offset_y baron_offset_z 0.0 0.0 0.0
						IF NOT IS_CHAR_DEAD char_zero_zero1
							POINT_CAMERA_AT_CHAR char_zero_zero1 FIXED JUMP_CUT
						ENDIF 
							
						  //	POINT_CAMERA_AT_CAR rc_enemy_helis_zero1[1] FIXED JUMP_CUT
						  //	PRINT_NOW ZER1_15 5000 1	// destroy the enemy aircrafts

		 				LOAD_MISSION_AUDIO 1 SOUND_zer1_ad
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE  
						PRINT_NOW ( ZER1_AD ) 10000 1		//No problem, but they’re only toys!
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT ZER1_AD 

		 				LOAD_MISSION_AUDIO 1 SOUND_ZER1_AE
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE  
						PRINT_NOW ( ZER1_AE ) 10000 1		//They’re not toys! They’re just smaller!
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT ZER1_AE 

						flag_cutscene_zero1 = 4
					ENDIF
				ENDIF
			ENDIF

			   			
			IF TIMERB > 17000


				IF flag_cutscene_zero1 = 4
					flag_cutscene_zero1 = 5

					SET_CHAR_COORDINATES scplayer -2239.7029 119.9904 56.5985
					SET_CHAR_HEADING scplayer 0.0
			   //	 	TASK_PLAY_ANIM SCPLAYER lkaround_loop ON_LOOKERS 4.0 FALSE FALSE FALSE TRUE 0


					IF DOES_OBJECT_EXIST object_turret_zero1
						TASK_LOOK_AT_OBJECT scplayer object_turret_zero1 3000
					ENDIF


					SET_FIXED_CAMERA_POSITION -2239.6882 122.1658 57.6384 0.0 0.0 0.0  // pointing camera at player
					POINT_CAMERA_AT_POINT -2239.2371 121.2862 57.7886 JUMP_CUT

					PRINT_NOW ZER1_22 5000 1	// Use the minigun to protect those bastard transmitters
					
				ENDIF

			ENDIF

			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
				flag_cutscene_zero1 = 10
				CLEAR_MISSION_AUDIO 1

				IF flag_zero1_created_rc_enemies = 0
					index_zero1 = 0
					WHILE index_zero1 < 2
						WAIT 0
						GOSUB create_enemy_helis_zero1
						index_zero1++
					ENDWHILE
				ENDIF

			ENDIF
			

			// Safety Timer
			IF TIMERB > 20000
				flag_cutscene_zero1 = 10
			ENDIF

		ENDWHILE

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[0]
			SET_CAR_COORDINATES rc_enemy_helis_zero1[0] coord_rc_enemy_helis_zero1_x[0] coord_rc_enemy_helis_zero1_y[0] 95.0
			TURN_CAR_TO_FACE_COORD rc_enemy_helis_zero1[0] coord_transmitter_x[index_target] coord_transmitter_y[index_target]	
			SET_CAR_FORWARD_SPEED rc_enemy_helis_zero1[0] 10.0
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[1]
			SET_CAR_COORDINATES rc_enemy_helis_zero1[1] coord_rc_enemy_helis_zero1_x[1] coord_rc_enemy_helis_zero1_y[1] 95.0
			TURN_CAR_TO_FACE_COORD rc_enemy_helis_zero1[1] coord_transmitter_x[index_target] coord_transmitter_y[index_target]	
			SET_CAR_FORWARD_SPEED rc_enemy_helis_zero1[1] 10.0
		ENDIF


		ATTACH_CHAR_TO_OBJECT scplayer object_turret_zero1 -0.25 -0.5 1.25 FACING_BACK 360.0 WEAPONTYPE_minigun
	  	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_minigun
		ADD_AMMO_TO_CHAR scplayer WEAPONTYPE_minigun 200000
		SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 270.0 300.0

		CLEAR_CHAR_TASKS scplayer
		
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 ON

		ALTER_WANTED_LEVEL player1 0
		CAMERA_RESET_NEW_SCRIPTABLES

		RESTORE_CAMERA
		IF DOES_OBJECT_EXIST object_turret_zero1
			SET_OBJECT_VISIBLE object_turret_zero1 FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD char_zero_zero1
			GIVE_WEAPON_TO_CHAR char_zero_zero1 WEAPONTYPE_EXTINGUISHER 1000
		ENDIF
		SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
		
		
		/////////////END OF CUTSCENE///////////////
	  //	START_SCRIPT_FIRE -2233.3123 134.8945 57.5298 5 1 fire1_zero1	
		//PRINT_NOW ( ZER1_13 ) 5000 1 //ZERO: ENEMY VISIBLE AT 9 AND 12 O'CLOCK!!
 		PRINT_HELP ZER1_24 
		
		
		IF NOT IS_CHAR_DEAD char_zero_zero1
	 		LOAD_MISSION_AUDIO 1 SOUND_ZER1_BE
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( ZER1_BE ) 10000 1		//BOGIES 12 o clock
			PLAY_MISSION_AUDIO 1
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT ZER1_BE 

		ENDIF

		SET_POLICE_IGNORE_PLAYER player1 ON
		SET_WANTED_MULTIPLIER 0.001
		
		flag_zero1_create_rc_baddies = 0 
	  	flag_zero1_kill_8_helis = 1
		RETURN
	ENDIF



	///////////////////////////////////////////////
	// 2nd Fleet
	///////////////////////////////////////////////

	IF fleet_destroyed_counter = 1
		IF flag_zero1_created_rc_enemies = 0
			index_zero1 = 0
			WHILE index_zero1 < 4
			  	WAIT 0

					IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1]
						SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 20

						IF NOT IS_CAR_ON_SCREEN rc_enemy_helis_zero1[index_zero1]
							DELETE_CAR rc_enemy_helis_zero1[index_zero1]
						ENDIF

					ENDIF

					MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]

					IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[index_zero1]
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]
					ENDIF


					REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1]

	 			GOSUB create_enemy_helis_zero1
			   //	index_random_coord++
				index_zero1++
			ENDWHILE
			flag_zero1_created_rc_enemies = 1
		ENDIF

		
		flag_zero1_create_rc_baddies = 0 
	  	flag_zero1_kill_8_helis = 1
		TIMERA = 0
		RETURN

		

	ENDIF	 



	flag_zero1_create_rc_baddies = 0 
  	flag_zero1_kill_8_helis = 1
	TIMERA = 0



RETURN

// ////////////////////////////////
// The Dogfighting takes place here
// ////////////////////////////////

kill_8_helis:
	WAIT 0

//VIEW_INTEGER_VARIABLE mission_countdown_zero1 mission_countdown_zero1

 //	VIEW_INTEGER_VARIABLE fleet_destroyed_counter fleet_destroyed_counter 

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		flag_zero1_create_rc_baddies = 0
		flag_zero1_kill_8_helis = 0
		flag_zero1_mission_zero1_passed = 1
		flag_zero1_mission_zero1_failed = 0
		RETURN
	ENDIF

	GOSUB transmitters_signal_status_zero1

	IF flag_zero1_mission_zero1_failed = 1

		flag_zero1_create_rc_baddies = 0
		flag_zero1_kill_8_helis = 0
		flag_zero1_mission_zero1_passed = 0
		flag_zero1_mission_zero1_failed = 1

		RETURN
	ENDIF

	IF IS_CHAR_DEAD char_zero_zero1
		flag_zero1_kill_8_helis = 0
		flag_zero1_mission_zero1_failed = 1

		CLEAR_MISSION_AUDIO 1
		PRINT_NOW ZER1_25 5000 1 // zeros deed
		RETURN


	   //	WAIT 5000
	  //	PRINT_NOW ZER1_25 5000 1 // zeros deed
	ENDIF

	index_zero1 = 0

	WHILE index_zero1 < 8
		WAIT 0

	 	
			IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1]   

			/////////////////////////////////////	
			// make zero point and shit
			/////////////////////////////////////
				IF NOT IS_CHAR_DEAD char_zero_zero1
				
					IF flag_is_zero_panicing = 0
						IF NOT IS_CHAR_RESPONDING_TO_EVENT char_zero_zero1 EVENT_FIRE_NEARBY
							OPEN_SEQUENCE_TASK seq_zero_success_cut_zero1
								TASK_PLAY_ANIM -1 Shout_01 ON_LOOKERS 4.0 FALSE FALSE FALSE TRUE 0	
								TASK_TURN_CHAR_TO_FACE_COORD -1 grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1]
								TASK_PLAY_ANIM -1 lkup_point ON_LOOKERS 4.0 FALSE FALSE FALSE TRUE 0	
								TASK_SCRATCH_HEAD -1

							   //	SET_SEQUENCE_TO_REPEAT seq_zero_success_cut_zero1 1
							CLOSE_SEQUENCE_TASK seq_zero_success_cut_zero1
							PERFORM_SEQUENCE_TASK char_zero_zero1 seq_zero_success_cut_zero1
							CLEAR_SEQUENCE_TASK seq_zero_success_cut_zero1
							flag_is_zero_panicing = 1
						ENDIF
					ENDIF
					
					IF flag_is_zero_panicing = 1
						GET_SCRIPT_TASK_STATUS char_zero_zero1 PERFORM_SEQUENCE_TASK zeros_task_status_zero1
						IF zeros_task_status_zero1 = FINISHED_TASK 
							flag_is_zero_panicing = 0
						ENDIF
					ENDIF

					IF flag_shooting_zero_zero1 = 1
							IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON char_zero_zero1 WEAPONTYPE_MINIGUN
								CLEAR_CHAR_LAST_WEAPON_DAMAGE char_zero_zero1
								CLEAR_MISSION_AUDIO 1
							   	LOAD_MISSION_AUDIO 1 SOUND_ZER1_DB
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE  

								PRINT_NOW ( ZER1_DB ) 5000 1		//Will you watch it, you idiot!
								PLAY_MISSION_AUDIO 1
								
								flag_shooting_zero_zero1 = 0

							ENDIF
					   //	ENDIF
					ENDIF


					IF flag_shooting_zero_zero1 = 0
						//IF NOT flag_zero1_mission_zero1_failed = 1
							IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON char_zero_zero1 WEAPONTYPE_MINIGUN
								CLEAR_CHAR_LAST_WEAPON_DAMAGE char_zero_zero1
								CLEAR_MISSION_AUDIO 1
							   	LOAD_MISSION_AUDIO 1 SOUND_ZER1_DA
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0

									IF IS_CHAR_DEAD char_zero_zero1
										flag_zero1_kill_8_helis = 0
										flag_zero1_mission_zero1_failed = 1

										CLEAR_MISSION_AUDIO 1
										PRINT_NOW ZER1_25 5000 1 // zeros deed
										RETURN
									ENDIF

								ENDWHILE  

								PRINT_NOW ( ZER1_DA ) 5000 1		//Hey, hey, hey!  FRIENDLY FIRE!  FRIENDLY FIRE!
								PLAY_MISSION_AUDIO 1
							 /* 	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							   //	OR NOT flag_zero1_mission_zero1_failed = 1
									WAIT 0
									IF IS_CHAR_DEAD char_zero_zero1
										flag_zero1_kill_8_helis = 0
										flag_zero1_mission_zero1_failed = 1

										CLEAR_MISSION_AUDIO 1
										PRINT_NOW ZER1_25 5000 1 // zeros deed
										RETURN
									ENDIF

								ENDWHILE
								CLEAR_THIS_PRINT ZER1_DA   */
								
								flag_shooting_zero_zero1 = 1

							ENDIF
						//ENDIF
					ENDIF


				ELSE
					flag_zero1_kill_8_helis = 0
					flag_zero1_mission_zero1_failed = 1
					CLEAR_MISSION_AUDIO 1
					PRINT_NOW ZER1_25 5000 1 // zeros deed
					RETURN
				ENDIF
			/////////////////////////////////////	
			// end make zero point and shit
			/////////////////////////////////////

				
			// GEneric shit for both planes and helis


				IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1]	
					IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0
					// Safety Timer
						GET_GAME_TIMER timer_current_time_zero1
						timer_heli_time_to_leave[index_zero1] =	timer_current_time_zero1 - timer_heli_creation_time[index_zero1]
					   	IF timer_heli_time_to_leave[index_zero1] > 45000
							EXPLODE_CAR rc_enemy_helis_zero1[index_zero1]
						   //	fleet_destroyed_counter++
								
							flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
							flag_enemy_objective[index_zero1] = 0

							IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
								DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
							ENDIF 

						ENDIF
					ENDIF


			/// 	CHECKING IF PLANE IS STUCK
					IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0
						IF DOES_CAR_HAVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1]
							IF IS_CAR_STUCK rc_enemy_helis_zero1[index_zero1]
								
								
								EXPLODE_CAR rc_enemy_helis_zero1[index_zero1]

								IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
									DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
								ENDIF 


									
							  //  fleet_destroyed_counter++
								flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
								flag_enemy_objective[index_zero1] = 0

								

							ENDIF
						ENDIF
					ENDIF

					IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0
						IF NOT IS_CAR_IN_AIR_PROPER rc_enemy_helis_zero1[index_zero1]

							EXPLODE_CAR rc_enemy_helis_zero1[index_zero1]

							IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
								DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
							ENDIF 

									
						 //	fleet_destroyed_counter++
							flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
							flag_enemy_objective[index_zero1] = 0
						ENDIF
					ENDIF


					IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0
						IF HAS_CAR_BEEN_DAMAGED_BY_WEAPON rc_enemy_helis_zero1[index_zero1] WEAPONTYPE_minigun 

							IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
								DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
							ENDIF 

						  //	fleet_destroyed_counter++
							SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 20

						 //	fleet_destroyed_counter++
							flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
							flag_enemy_objective[index_zero1] = 0
						ENDIF
					ENDIF

				



					// dropping bombs
					IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 0
						IF flag_enemy_objective[index_zero1] = 0
							IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
								IF LOCATE_CAR_3D rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 18.0 18.0 40.0 FALSE
									flag_enemy_objective[index_zero1] = 1
								ENDIF
							ENDIF

						 /*	IF LOCATE_CAR_3D rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 17.0 17.0 40.0 FALSE
								flag_enemy_objective[index_zero1] = 1
							ENDIF	*/

						ELSE

						   /*	IF flag_enemy_objective[index_zero1] = 1
								IF NOT LOCATE_CAR_3D rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 40.0 40.0 60.0 FALSE
									flag_enemy_objective[index_zero1] = 2
								ENDIF
							ENDIF	*/
						ENDIF
					ENDIF

					// Make helis bomb transmitters

					IF flag_enemy_objective[index_zero1] = 1
						GOSUB enemy_bombs_zero1

						IF flag_zero1_mission_zero1_failed = 1

							flag_zero1_create_rc_baddies = 0
							flag_zero1_kill_8_helis = 0
							flag_zero1_mission_zero1_passed = 0
							flag_zero1_mission_zero1_failed = 1

							RETURN
						ENDIF

					ENDIF
					/////////////////////////////

					IF flag_zero1_mission_zero1_failed = 1

						flag_zero1_create_rc_baddies = 0
						flag_zero1_kill_8_helis = 0
						flag_zero1_mission_zero1_passed = 0
						flag_zero1_mission_zero1_failed = 1

						RETURN
					ENDIF





				
					IF flag_enemy_objective[index_zero1] > 2
				
				
					// If  baddies fly too far away away
						IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
							IF LOCATE_CAR_2D rc_enemy_helis_zero1[index_zero1] coord_rc_enemy_helis_zero1_x[index_zero1] coord_rc_enemy_helis_zero1_y[index_zero1] 30.0 30.0  FALSE
								DELETE_CAR rc_enemy_helis_zero1[index_zero1]

								IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
									DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
								ENDIF 


								flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
								flag_enemy_objective[index_zero1] = 0
								RETURN
							ENDIF
						ENDIF
					ENDIF

					// plane achieves runup offset
					IF flag_enemy_objective[index_zero1] = 2
						IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
							IF LOCATE_CAR_2D rc_enemy_helis_zero1[index_zero1] coord_enemy_circling1_x[index_zero1] coord_enemy_circling1_y[index_zero1] 7.0 7.0  FALSE
								PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_enemy_circling2_x[index_zero1] coord_enemy_circling2_y[index_zero1] coord_enemy_circling2_z[index_zero1] 5.0 0.5  
							   	SET_PLANE_THROTTLE rc_enemy_helis_zero1[index_zero1] -1.1
								//SET_CAR_FORWARD_SPEED rc_enemy_helis_zero1[index_zero1] 3.0

								flag_enemy_objective[index_zero1] = 3


							ENDIF
						ENDIF
					ENDIF

					// plane achieves next offset and is ready to do kamikazie
					IF flag_enemy_objective[index_zero1] = 3
						IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
							IF LOCATE_CAR_2D rc_enemy_helis_zero1[index_zero1] coord_enemy_circling2_x[index_zero1] coord_enemy_circling2_y[index_zero1] 7.0 7.0  FALSE

								PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 3.0 0.0  
							 	SET_PLANE_THROTTLE rc_enemy_helis_zero1[index_zero1] -0.9

								flag_enemy_objective[index_zero1] = 4

							ENDIF
						ENDIF
					ENDIF



										// making planes do the kamikazie



	  		 
					// Planes should blow up when crashing into transmittor
					IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
						IF LOCATE_CAR_3D rc_enemy_helis_zero1[index_zero1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 4.0 4.0 11.0 FALSE
							GET_CAR_COORDINATES rc_enemy_helis_zero1[index_zero1] coord_explosion_x_zero1 coord_explosion_y_zero1 coord_explosion_z_zero1
							EXPLODE_CAR rc_enemy_helis_zero1[index_zero1] 
							DELETE_CAR rc_enemy_helis_zero1[index_zero1]
							ADD_EXPLOSION coord_explosion_x_zero1 coord_explosion_y_zero1 coord_explosion_z_zero1 EXPLOSION_GRENADE	
 							IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
								DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
							ENDIF 

							// [ZER1_BN]	I thought you said you could shoot, Carl!
							IF NOT IS_CHAR_DEAD char_zero_zero1
								IF flag_i_thought_u_could_shoot_zero1 = 0
									LOAD_MISSION_AUDIO 1 SOUND_ZER1_BN
									WHILE NOT HAS_MISSION_AUDIO_LOADED 1
										WAIT 0
									ENDWHILE  

									PRINT_NOW ( ZER1_BN ) 10000 1	   
									PLAY_MISSION_AUDIO 1
									WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
										WAIT 0
									ENDWHILE
									CLEAR_THIS_PRINT ZER1_BN 
									flag_i_thought_u_could_shoot_zero1 = 1
								ENDIF
							ENDIF
				
				
							flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
							flag_enemy_objective[index_zero1] = 0





							IF index_target = 3
								IF transmitter4_health_zero1 > 5	
									transmitter4_health_zero1 -= 5					
								ELSE					
									flag_transmitter4_destroyed = 1					
									
								  	DELETE_OBJECT object_transmitter[index_target]
									ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] EXPLOSION_GRENADE
									//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[1] 2.0 1.5 -1.0 baron_offset_x baron_offset_y baron_offset_z

									ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] 61.5 EXPLOSION_OBJECT


									CREATE_OBJECT wongs_erection2 coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] object_transmitter[index_target]
									SET_OBJECT_HEADING object_transmitter[index_target] heading_destroyed_transmittors[index_target]
									START_SCRIPT_FIRE coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 5 3 fire_transmittor[index_target]

									flag_zero1_create_rc_baddies = 0
									flag_zero1_kill_8_helis = 0
									flag_zero1_mission_zero1_failed = 1

									IF NOT IS_CHAR_DEAD char_zero_zero1
								 		LOAD_MISSION_AUDIO 1 SOUND_ZER1_EA		// curse you
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( ZER1_EA ) 10000 1	   
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT ZER1_EA 
									ENDIF



									CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
									CLEAR_ONSCREEN_TIMER mission_countdown_zero1

									PRINT_NOW ( ZER1_19 ) 5000 1 //4th transmitter gone	
									RETURN
								ENDIF	
							ENDIF	

							IF index_target = 2
								IF transmitter3_health_zero1 > 5	
									transmitter3_health_zero1 -= 5					
								ELSE					
									flag_transmitter3_destroyed = 1
								
									IF NOT IS_CHAR_DEAD char_zero_zero1					
									   //	PRINT_NOW ( ZER1_18 ) 5000 1 //3rd transmitter gone		
										GOSUB assign_next_target_for_planes_zero1

										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF


										IF NOT IS_CHAR_DEAD char_zero_zero1
								 			LOAD_MISSION_AUDIO 1 SOUND_ZER1_BK
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_BK ) 10000 1		//cant lose another one
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BK   

								 			LOAD_MISSION_AUDIO 1 SOUND_ZER1_BL
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  
											PRINT_NOW ( ZER1_BL ) 10000 1		//[ZER1_BL]	Berkley’s going for the last transmitter, 
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BL
											   

								 			LOAD_MISSION_AUDIO 1 SOUND_ZER1_BM
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_BM ) 10000 1		//[ZER1_BM]	you’ve got to stop those planes, Carl!
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BM  
  										ENDIF

 
										
									ENDIF	

									

									
									
								ENDIF	
							ENDIF	

							IF index_target = 1
								IF transmitter2_health_zero1 > 5	
									transmitter2_health_zero1 -= 5					
								ELSE					
									flag_transmitter2_destroyed = 1
									IF NOT IS_CHAR_DEAD char_zero_zero1	
										GOSUB assign_next_target_for_planes_zero1

										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF

														
										LOAD_MISSION_AUDIO 1 SOUND_ZER1_BJ
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( ZER1_BJ ) 10000 1		//cant lose another one
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT ZER1_BJ   

									ENDIF	
									
								ENDIF	
							ENDIF	

							IF index_target = 0
								IF transmitter1_health_zero1 > 5	
									transmitter1_health_zero1 -= 5					
								ELSE					
									flag_transmitter1_destroyed = 1		
									IF NOT IS_CHAR_DEAD char_zero_zero1
										GOSUB assign_next_target_for_planes_zero1

										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF


										LOAD_MISSION_AUDIO 1 SOUND_ZER1_BG
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( ZER1_BG ) 10000 1		//WE've LOST ONE
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT ZER1_BG   

									ENDIF
									
								ENDIF	
							ENDIF
								
						ENDIF

					
					ELSE	 // if plane is dead

						flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
					ENDIF




				ELSE	 // if plane is dead

					flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
				ENDIF



			ELSE	 // if plane is dead

				flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1
			ENDIF
		

 
		index_zero1++
	ENDWHILE



	// Get ready for next fleet
	IF fleet_destroyed_counter < 1	// only happens for first  fleet


		index_zero1 = 0
		WHILE index_zero1 < 4
			IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1 

				IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[index_zero1]
					REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]
				ENDIF

				IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
					DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
				ENDIF 

				REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1]

				IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
					IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[index_zero1]
						MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]
					ELSE
						DELETE_CAR rc_enemy_helis_zero1[index_zero1]
					ENDIF
				ENDIF

				MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]
				fleet_destroyed_counter++
			ENDIF
			index_zero1++
		ENDWHILE



		IF flag_zero1_rc_enemy_helis_zero1_dead[0] = 1
			IF flag_zero1_rc_enemy_helis_zero1_dead[1] = 1
				IF flag_zero1_rc_enemy_helis_zero1_dead[2] = 1
					IF flag_zero1_rc_enemy_helis_zero1_dead[3] = 1
					   //	flag_zero1_mission_zero1_passed = 1

						fleet_destroyed_counter++
						flag_zero1_kill_8_helis = 0
					 //	PRINT_NOW ( ZER1_6 ) 5000 1 // MELVIN: Oh the humanity! You have defeated my fleet!
						flag_zero1_create_rc_baddies = 1
						flag_zero1_created_rc_enemies = 0
					   	fleet_destroyed_counter++
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[0]
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[1]
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[2]
						REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[3]
						index_zero1 = 0
						WHILE index_zero1 < 4
							IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
								SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 20
							ENDIF
							
							IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
								IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[index_zero1]
									MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]
								ELSE
									DELETE_CAR rc_enemy_helis_zero1[index_zero1]
								ENDIF
							ENDIF

							MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]

							REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1]
							index_zero1++
						ENDWHILE 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
	ELSE
		// RESPAWNING HELICOPTERS
		index_zero1 = 0
		WHILE index_zero1 < 4
			IF flag_zero1_rc_enemy_helis_zero1_dead[index_zero1] = 1 

				IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[index_zero1]
					REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[index_zero1]
				ENDIF

				IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
					DELETE_OBJECT primed_bomb_1_zero1[index_zero1]
				ENDIF 

				REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[index_zero1]
				
				IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
					IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[index_zero1]
						MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]
					ELSE
						DELETE_CAR rc_enemy_helis_zero1[index_zero1]
					ENDIF
				ENDIF

				MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1]

				




			//////////////////////////////////////////   
			// pace the creation of rc helis
			///////////////////////////////////////////
		  		GET_GAME_TIMER timer_current_time_zero1
				time_since_mission_started_zero1 =	timer_current_time_zero1 - mission_start_time_zero1


					IF mission_countdown_zero1 < 210000
					AND mission_countdown_zero1 > 190000 
						IF TIMERB > 12000

					   		GOSUB create_enemy_helis_zero1
							TIMERB = 0
						ENDIF
					ENDIF


					IF mission_countdown_zero1 < 190000
					AND mission_countdown_zero1 > 120000 
						IF TIMERB > 6000

					   		GOSUB create_enemy_helis_zero1
							TIMERB = 0
						ENDIF
					ENDIF


					IF mission_countdown_zero1 < 120000
					AND mission_countdown_zero1 > 0
						IF TIMERB > 3500
					   		GOSUB create_enemy_helis_zero1
							TIMERB = 0
						ENDIF
					ENDIF	


				IF flag_zero1_rc_enemy_helis_zero1_dead[0] = 1 
					IF flag_zero1_rc_enemy_helis_zero1_dead[1] = 1 
						IF flag_zero1_rc_enemy_helis_zero1_dead[2] = 1 
							IF flag_zero1_rc_enemy_helis_zero1_dead[3] = 1 
								IF flag_zero1_rc_enemy_helis_zero1_dead[4] = 1 
									IF flag_zero1_rc_enemy_helis_zero1_dead[5] = 1 
										IF flag_zero1_rc_enemy_helis_zero1_dead[6] = 1 
											IF flag_zero1_rc_enemy_helis_zero1_dead[7] = 1 
											   	GOSUB create_enemy_helis_zero1

													IF flag_cocky_comment_zero1 = 0
														IF NOT IS_CHAR_DEAD char_zero_zero1
															LOAD_MISSION_AUDIO 1 SOUND_ZER1_CA
															WHILE NOT HAS_MISSION_AUDIO_LOADED 1
																WAIT 0
															ENDWHILE  

															PRINT_NOW ( ZER1_CA ) 10000 1		//Great kid, don’t get cocky!
															PLAY_MISSION_AUDIO 1
															WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
																WAIT 0
															ENDWHILE
															CLEAR_THIS_PRINT ZER1_CA  
															flag_cocky_comment_zero1 = 1
														ENDIF
													ENDIF

												TIMERB = 0
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

			index_zero1++
		ENDWHILE  

  
// creating fleet of planes
	 //	IF fleet_destroyed_counter > 2	// only happens for first 4 fleets
			IF mission_countdown_zero1 < 210000
				
				IF flag_zero1_rc_enemy_helis_zero1_dead[4] = 1 
					IF flag_zero1_rc_enemy_helis_zero1_dead[5] = 1 
						IF flag_zero1_rc_enemy_helis_zero1_dead[6] = 1 
							IF flag_zero1_rc_enemy_helis_zero1_dead[7] = 1 


							   	
				   			   //	fleet_destroyed_counter++

									IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[4]
										REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[4]
									ENDIF
								  //	fleet_destroyed_counter++
									IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[5]
									   	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[5]
									ENDIF
								  //	fleet_destroyed_counter++
									IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[6]
									   	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[6]
									ENDIF
								 //	fleet_destroyed_counter++
									IF DOES_BLIP_EXIST blip_zero1_rc_enemy_helis_zero1[7]
									   	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[7]
									ENDIF

									IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[4] 
										SET_CAR_HEALTH rc_enemy_helis_zero1[4] 10
									ENDIF

									IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[5] 
										SET_CAR_HEALTH rc_enemy_helis_zero1[5] 10
									ENDIF

									IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[6] 
										SET_CAR_HEALTH rc_enemy_helis_zero1[6] 10
									ENDIF

									IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[7] 
										SET_CAR_HEALTH rc_enemy_helis_zero1[7] 10
									ENDIF

									IF DOES_OBJECT_EXIST primed_bomb_1_zero1[4]
										DELETE_OBJECT primed_bomb_1_zero1[4]
									ENDIF

									IF DOES_OBJECT_EXIST primed_bomb_1_zero1[5]
										DELETE_OBJECT primed_bomb_1_zero1[5]
									ENDIF

									IF DOES_OBJECT_EXIST primed_bomb_1_zero1[6]
										DELETE_OBJECT primed_bomb_1_zero1[6]
									ENDIF

									IF DOES_OBJECT_EXIST primed_bomb_1_zero1[7]
										DELETE_OBJECT primed_bomb_1_zero1[7]
									ENDIF


						 			IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[4] 
										IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[4]
											MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[4]
										ELSE
											DELETE_CAR rc_enemy_helis_zero1[4]
										ENDIF
									ENDIF

									MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[4]

						 			IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[5] 
										IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[5]
											MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[5]
										ELSE
											DELETE_CAR rc_enemy_helis_zero1[5]
										ENDIF
									ENDIF

									MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[5]

						 			IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[6] 
										IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[6]
											MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[6]
										ELSE
											DELETE_CAR rc_enemy_helis_zero1[6]
										ENDIF
									ENDIF

									MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[6]

						 			IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[7] 
										IF IS_CAR_ON_SCREEN rc_enemy_helis_zero1[7]
											MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[7]
										ELSE
											DELETE_CAR rc_enemy_helis_zero1[7]
										ENDIF
									ENDIF

									MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[7]

						 		  




									REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[4]
									REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[5]
									REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[6]
									REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[7]

									GET_GAME_TIMER timer_current_time_zero1
									timer_heli_time_to_leave[4] =	timer_current_time_zero1 - timer_heli_creation_time[4]
								   	IF timer_heli_time_to_leave[4] > 45000

										index_zero1 = 4
										WHILE index_zero1 < 8
											GOSUB create_enemy_planes_zero1
										 //	ENDIF											   
											index_zero1++
										ENDWHILE
									ENDIF
							   	
								  
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
	 //	ENDIF



		


		/////////////////////////////////////////////////////
		// If all  fleets are destroyed, mission passed	 
		/////////////////////////////////////////////////////




	  //	IF fleet_destroyed_counter >= 27
		IF mission_countdown_zero1 <= 1

			index_zero1 = 0
			WHILE index_zero1 < 8
				IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 
					//SET_CAR_HEALTH rc_enemy_helis_zero1[index_zero1] 5 
					EXPLODE_CAR rc_enemy_helis_zero1[index_zero1]
					MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_helis_zero1[index_zero1] 

				ENDIF
			   
				index_zero1++
			ENDWHILE

				flag_zero1_create_rc_baddies = 0
				flag_zero1_kill_8_helis = 0
				flag_zero1_mission_zero1_passed = 1
				flag_zero1_mission_zero1_failed = 0

			RETURN
 		ENDIF

 RETURN






// 		SUB FUNCTIONS  ////////////////////////////


	enemy_bombs_zero1:

	 	primed_bomb_flag_zero1[index_zero1] = 0

		// *************************************
		// BOMB SHIT
		// *************************************
		

			GET_CAR_COORDINATES rc_enemy_helis_zero1[index_zero1] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1]
			
		  	GET_GROUND_Z_FOR_3D_COORD grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] floor_z_zero1[index_zero1]				
		//   	DRAW_CORONA grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] floor_z_zero1[index_zero1] 2.0 CORONATYPE_HEADLIGHTLINE FLARETYPE_HEADLIGHTS 6 6 6					   
		
	  /*		IF NOT DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
			   //	IF timer_zero1[index_zero1] > next_bomb_timer_zero1[index_zero1]
					CREATE_OBJECT rcbomb grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] primed_bomb_1_zero1[index_zero1]
					SET_OBJECT_COLLISION primed_bomb_1_zero1[index_zero1] FALSE
					SET_OBJECT_DYNAMIC primed_bomb_1_zero1[index_zero1] FALSE  
	   
					ATTACH_OBJECT_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 -0.3 0.0 0.0 0.0

					primed_bomb_z_zero1[index_zero1] = 0.0
			   //	ENDIF
			ELSE   */
				// GET_GROUND_Z_FOR_3D_COORD grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] floor_z_zero1[index_zero1]
		   	IF DOES_OBJECT_EXIST primed_bomb_1_zero1[index_zero1]
				distance_to_ground_zero1[index_zero1] = grenade_zero1_z[index_zero1] - floor_z_zero1[index_zero1]

				IF distance_to_ground_zero1[index_zero1] > 0.5
					SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 10.0 rcbomb TRUE
					primed_bomb_z_zero1[index_zero1] -=@ 0.05
					IF primed_bomb_z_zero1[index_zero1] < -0.5
						primed_bomb_z_zero1[index_zero1] = -0.5
						primed_bomb_flag_zero1[index_zero1] = 1	// Activate bomb if above certain height
					ENDIF
				ELSE
					primed_bomb_z_zero1[index_zero1] = distance_to_ground_zero1[index_zero1] - 1.0
					primed_bomb_z_zero1[index_zero1] = primed_bomb_z_zero1[index_zero1] * -1.0

					IF primed_bomb_z_zero1[index_zero1] > 0.0
						primed_bomb_z_zero1[index_zero1] = 0.0
						SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 10.0 rcbomb FALSE
					ENDIF
				ENDIF
			   //	PLACE_OBJECT_RELATIVE_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 primed_bomb_z_zero1[index_zero1]
			ENDIF

			// Bomb dropping conditions
	  //	  	IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer rc_enemy_helis_zero1[index_zero1] 20.0 20.0 60.0 FALSE
	  		IF flag_enemy_objective[index_zero1] = 1
				GET_GAME_TIMER timer_zero1[index_zero1]
				IF timer_zero1[index_zero1] > next_bomb_timer_zero1[index_zero1]
					IF R1_button_pressed_zero1[index_zero1] = 0
						IF primed_bomb_flag_zero1[index_zero1] = 1
							primed_bomb_z_zero1[index_zero1] = primed_bomb_z_zero1[index_zero1] - 0.5
							//PLACE_OBJECT_RELATIVE_TO_CAR primed_bomb_1_zero1[index_zero1] rc_enemy_helis_zero1[index_zero1] 0.0 0.0 primed_bomb_z_zero1[index_zero1]
							
							DETACH_OBJECT primed_bomb_1_zero1[index_zero1] 0.0 0.0 0.0 FALSE
							
							SET_OBJECT_DYNAMIC primed_bomb_1_zero1[index_zero1] TRUE
							SET_OBJECT_COLLISION primed_bomb_1_zero1[index_zero1] TRUE
							ADD_TO_OBJECT_VELOCITY primed_bomb_1_zero1[index_zero1] 0.0 0.0 0.0
							next_bomb_timer_zero1[index_zero1] = timer_zero1[index_zero1] + 2500 // Delay of next bomb dropped 
							falling_bomb_1_zero1[index_zero1] = primed_bomb_1_zero1[index_zero1]
							primed_bomb_1_zero1[index_zero1] = -1
							SET_OBJECT_RECORDS_COLLISIONS falling_bomb_1_zero1[index_zero1] TRUE
							circle_button_timer_zero1[index_zero1] = timer_zero1[index_zero1] + 250
							detonation_timer_zero1[index_zero1] = timer_zero1[index_zero1] + 3000
							R1_button_pressed_zero1[index_zero1] = 1
						ENDIF
						
					ELSE

						IF R1_button_pressed_zero1[index_zero1] = 2
							IF timer_zero1[index_zero1] > circle_button_timer_zero1[index_zero1]
								R1_button_pressed_zero1[index_zero1] = 0
							ENDIF
						ENDIF	

					ENDIF
				ENDIF
			ENDIF // locate player near RC heli



			// Falling bombs
			IF R1_button_pressed_zero1[index_zero1] = 1
				IF DOES_OBJECT_EXIST falling_bomb_1_zero1[index_zero1]
					GET_OBJECT_COORDINATES falling_bomb_1_zero1[index_zero1] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1]
					GET_GROUND_Z_FOR_3D_COORD grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] floor_z_zero1[index_zero1]
					
					floor_z_zero1[index_zero1] += 0.5

					IF floor_z_zero1[index_zero1] < 6.0
						floor_z_zero1[index_zero1] = 6.0
					ENDIF

										
					IF HAS_OBJECT_COLLIDED_WITH_ANYTHING falling_bomb_1_zero1[index_zero1]
					OR timer_zero1[index_zero1] > detonation_timer_zero1[index_zero1]
					OR IS_OBJECT_IN_WATER falling_bomb_1_zero1[index_zero1]
						SET_OBJECT_RECORDS_COLLISIONS falling_bomb_1_zero1[index_zero1] FALSE
					
						ADD_EXPLOSION grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] EXPLOSION_GRENADE
						MARK_OBJECT_AS_NO_LONGER_NEEDED	falling_bomb_1_zero1[index_zero1]
						DELETE_OBJECT falling_bomb_1_zero1[index_zero1] 
						falling_bomb_1_zero1[index_zero1] = -1
						R1_button_pressed_zero1[index_zero1] = 2

						flag_enemy_objective[index_zero1] = 2

						IF NOT IS_CHAR_DEAD char_zero_zero1
							IF flag_kamakazie_comment = 0
						 		LOAD_MISSION_AUDIO 1 SOUND_ZER1_BC
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE  
						  		PRINT_NOW ( ZER1_BC ) 10000 1		//[ZER1_BC]	Kamikazes at 6 o’clock!

								PLAY_MISSION_AUDIO 1
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT ZER1_BC 
								flag_kamakazie_comment = 1
							ENDIF
						ENDIF


						// MAKING PLANES get ready for a KAMIKAZIE
						IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[index_zero1] 


						 //	HELI_GOTO_COORDS rc_enemy_helis_zero1[0] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
							coord_baron_z_offset_zero1 = coord_transmitter_z[index_target] + 4.0


							IF LOCATE_CAR_2D rc_enemy_helis_zero1[index_zero1] -2235.66 182.43 50.0 50.0  FALSE

								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[index_zero1] -25.0 0.0 5.0 coord_enemy_circling2_x[index_zero1] coord_enemy_circling2_y[index_zero1] coord_enemy_circling2_z[index_zero1]
							ELSE
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[index_zero1] 25.0 0.0 5.0 coord_enemy_circling2_x[index_zero1] coord_enemy_circling2_y[index_zero1] coord_enemy_circling2_z[index_zero1]
								
							ENDIF


							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[index_zero1] 0.0 20.0 4.0 coord_enemy_circling1_x[index_zero1] coord_enemy_circling1_y[index_zero1] coord_enemy_circling1_z[index_zero1]
							PLANE_GOTO_COORDS rc_enemy_helis_zero1[index_zero1] coord_enemy_circling1_x[index_zero1] coord_enemy_circling1_y[index_zero1] coord_enemy_circling1_z[index_zero1] -1.0 0.5  
							SET_PLANE_THROTTLE rc_enemy_helis_zero1[index_zero1] -0.9
						ENDIF



						// checking if bomb has colided with transmitter
						IF flag_transmitter1_destroyed = 0
							IF DOES_OBJECT_EXIST object_transmitter[0]
								IF LOCATE_OBJECT_3D object_transmitter[0] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 15.0 15.0 10.0 FALSE
									IF transmitter1_health_zero1 > 	5
										transmitter1_health_zero1 -= 5 
										
									ELSE
										flag_transmitter1_destroyed = 1
										GOSUB assign_next_target_for_planes_zero1

										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF

										IF NOT IS_CHAR_DEAD char_zero_zero1 
											LOAD_MISSION_AUDIO 1 SOUND_ZER1_BG
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_BG ) 10000 1		//WE've LOST ONE
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BG  
											
											transmitter1_health_zero1 = 0		
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF					

						IF flag_transmitter2_destroyed = 0
							IF DOES_OBJECT_EXIST object_transmitter[1]					
								IF LOCATE_OBJECT_3D object_transmitter[1] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 15.0 15.0 10.0 FALSE					
									IF transmitter2_health_zero1 > 5	
										transmitter2_health_zero1 -= 5					
									ELSE					
										flag_transmitter2_destroyed = 1	
										GOSUB assign_next_target_for_planes_zero1
										
										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF
	
										IF NOT IS_CHAR_DEAD char_zero_zero1			
										  	LOAD_MISSION_AUDIO 1 SOUND_ZER1_BJ
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_BJ ) 10000 1		//WE've LOST ONE
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BJ 
											transmitter2_health_zero1 = 0
										ENDIF
									ENDIF					
								ENDIF					
							ENDIF					
						ENDIF					
											
						IF flag_transmitter3_destroyed = 0					
							IF DOES_OBJECT_EXIST object_transmitter[2]					
								IF LOCATE_OBJECT_3D object_transmitter[2] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 15.0 15.0 10.0 FALSE					
									IF transmitter3_health_zero1 > 5	
										transmitter3_health_zero1 -= 5					
									ELSE					
										flag_transmitter3_destroyed = 1	
										GOSUB assign_next_target_for_planes_zero1

										IF flag_zero1_mission_zero1_failed = 1

											flag_zero1_create_rc_baddies = 0
											flag_zero1_kill_8_helis = 0
											flag_zero1_mission_zero1_passed = 0
											flag_zero1_mission_zero1_failed = 1

											RETURN
										ENDIF

										IF NOT IS_CHAR_DEAD char_zero_zero1				
											LOAD_MISSION_AUDIO 1 SOUND_ZER1_BK
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_BK ) 10000 1		//WE've LOST ONE
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_BK 
											transmitter3_health_zero1 = 0
										ENDIF
										
									ENDIF					
								ENDIF					
							ENDIF					
						ENDIF					
											
						IF flag_transmitter4_destroyed = 0					
							IF DOES_OBJECT_EXIST object_transmitter[3]					
								IF LOCATE_OBJECT_3D object_transmitter[3] grenade_zero1_x[index_zero1] grenade_zero1_y[index_zero1] grenade_zero1_z[index_zero1] 15.0 15.0 10.0 FALSE					
									IF transmitter4_health_zero1 > 5	
										transmitter4_health_zero1 -= 5					
									ELSE					
										flag_transmitter4_destroyed = 1		
										
										DELETE_OBJECT object_transmitter[index_target]
										ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] EXPLOSION_GRENADE
										CREATE_OBJECT wongs_erection2 coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] object_transmitter[index_target]
										SET_OBJECT_HEADING object_transmitter[index_target] heading_destroyed_transmittors[index_target]	
										START_SCRIPT_FIRE coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 5 3 fire_transmittor[index_target]

										transmitter4_health_zero1 = 0


										flag_zero1_create_rc_baddies = 0
										flag_zero1_kill_8_helis = 0
										flag_zero1_mission_zero1_failed = 1

									// [ZER1_EA]	Curse you, Berkley, CURSE YOOUUU!
										IF NOT IS_CHAR_DEAD char_zero_zero1
									 		LOAD_MISSION_AUDIO 1 SOUND_ZER1_EA
											WHILE NOT HAS_MISSION_AUDIO_LOADED 1
												WAIT 0
											ENDWHILE  

											PRINT_NOW ( ZER1_EA ) 10000 1	   
											PLAY_MISSION_AUDIO 1
											WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
												WAIT 0
											ENDWHILE
											CLEAR_THIS_PRINT ZER1_EA 
										ENDIF

										CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
										CLEAR_ONSCREEN_TIMER mission_countdown_zero1

										PRINT_NOW ( ZER1_19 ) 5000 1 //all transmitter gone
										RETURN
		
									ENDIF					
								ENDIF					
							ENDIF					
						ENDIF						
					ENDIF					
											
					
					
/////////////////////////////////////////////////////////////////////					

				ENDIF
			ENDIF

	RETURN


////////////////////////////
//	transmitter health
////////////////////////////

	transmitters_signal_status_zero1:


// if an explosion is near transmitter, deduct 10 off health
// if transmitter is dead, increment index_target, make remaining helis attack that transmitter
	 //	transmitter_overallhealth = 0



		IF index_target > 3
		OR transmitter_overallhealth <= 5 
			flag_zero1_create_rc_baddies = 0
			flag_zero1_kill_8_helis = 0
			flag_zero1_mission_zero1_failed = 1

			IF DOES_OBJECT_EXIST object_transmitter[index_target]

				DELETE_OBJECT object_transmitter[index_target]
				ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] EXPLOSION_GRENADE
				//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS rc_enemy_helis_zero1[1] 2.0 1.5 -1.0 baron_offset_x baron_offset_y baron_offset_z

				ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] 61.5 EXPLOSION_OBJECT


				CREATE_OBJECT wongs_erection2 coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] object_transmitter[index_target]
				SET_OBJECT_HEADING object_transmitter[index_target] heading_destroyed_transmittors[index_target]
				START_SCRIPT_FIRE coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 5 3 fire_transmittor[index_target]
			ENDIF


			// [ZER1_EA]	Curse you, Berkley, CURSE YOOUUU!
			IF NOT IS_CHAR_DEAD char_zero_zero1
				LOAD_MISSION_AUDIO 1 SOUND_ZER1_EA
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

				PRINT_NOW ( ZER1_EA ) 10000 1	   
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				CLEAR_THIS_PRINT ZER1_EA 
			ENDIF

			CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
			CLEAR_ONSCREEN_TIMER mission_countdown_zero1

			PRINT_NOW ( ZER1_19 ) 5000 1 //ALL transmitter gone
			RETURN
		ELSE

	 		transmitter_overallhealth = transmitter1_health_zero1
	 		transmitter_overallhealth += transmitter2_health_zero1
	 		transmitter_overallhealth += transmitter3_health_zero1
	 		transmitter_overallhealth += transmitter4_health_zero1 
		ENDIF


	RETURN



///////////////////////////////////////
// IF transmittor is destroyed, 
// make planes move on to next target
///////////////////////////////////////



assign_next_target_for_planes_zero1:
	DELETE_OBJECT object_transmitter[index_target]
	ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] 61.5 EXPLOSION_OBJECT
	ADD_EXPLOSION coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] EXPLOSION_GRENADE
	CREATE_OBJECT wongs_erection2 coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] object_transmitter[index_target]
	SET_OBJECT_HEADING object_transmitter[index_target] heading_destroyed_transmittors[index_target]	
	START_SCRIPT_FIRE coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 5 3 fire_transmittor[index_target]
	IF index_target < 4
	AND index_target >= 0


		index_target++

	
	



	
	// new spawn positions after transmittor 1 is destroyed
		IF index_target = 1
			coord_rc_enemy_helis_zero1_x[0] = -2308.4958
			coord_rc_enemy_helis_zero1_y[0] = 131.3490 
								  	    
			coord_rc_enemy_helis_zero1_x[1] = -2080.5811 
			coord_rc_enemy_helis_zero1_y[1] = 122.0999 
								 	   
			coord_rc_enemy_helis_zero1_x[2] = -2308.6934 
			coord_rc_enemy_helis_zero1_y[2] = 190.5520 
								 
			coord_rc_enemy_helis_zero1_x[3] = -2124.5403 
			coord_rc_enemy_helis_zero1_y[3] = 63.5174 

			coord_rc_enemy_helis_zero1_x[4]=  -2112.4565 
			coord_rc_enemy_helis_zero1_y[4]=	143.6713 

			coord_rc_enemy_helis_zero1_x[5]=	-2111.9084 
			coord_rc_enemy_helis_zero1_y[5]=	149.0278 

			coord_rc_enemy_helis_zero1_x[6] =	-2110.3684 
			coord_rc_enemy_helis_zero1_y[6] =	126.8913 

			coord_rc_enemy_helis_zero1_x[7]=	-2110.0205 
			coord_rc_enemy_helis_zero1_y[7]=	120.1988 
		ENDIF

		// new spawn positions after transmittor 2 is destroyed
		IF index_target = 2
			coord_rc_enemy_helis_zero1_x[0] = -2308.4958
			coord_rc_enemy_helis_zero1_y[0] = 131.3490 
								  	    
			coord_rc_enemy_helis_zero1_x[1] = -2080.5811 
			coord_rc_enemy_helis_zero1_y[1] = 122.0999 
								 	   
			coord_rc_enemy_helis_zero1_x[2] = -2308.6934 
			coord_rc_enemy_helis_zero1_y[2] = 190.5520 
								 
			coord_rc_enemy_helis_zero1_x[3] = -2124.5403 
			coord_rc_enemy_helis_zero1_y[3] = 63.5174 

			coord_rc_enemy_helis_zero1_x[4]=  -2112.4565 
			coord_rc_enemy_helis_zero1_y[4]=  143.6713 

			coord_rc_enemy_helis_zero1_x[5]=  -2111.9084 
			coord_rc_enemy_helis_zero1_y[5]=  149.0278 

			coord_rc_enemy_helis_zero1_x[6] = -2110.3684 
			coord_rc_enemy_helis_zero1_y[6] = 126.8913 

			coord_rc_enemy_helis_zero1_x[7]=  -2110.0205 
			coord_rc_enemy_helis_zero1_y[7]=  120.1988 
		ENDIF

		// new spawn positions after transmittor 3 is destroyed
		IF index_target = 3

			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_FIRE_NEARBY
			coord_rc_enemy_helis_zero1_x[0] = -2119.4958
			coord_rc_enemy_helis_zero1_y[0] = 91.3490 
								  	    
			coord_rc_enemy_helis_zero1_x[1] = -2258.5811 
			coord_rc_enemy_helis_zero1_y[1] = 169.0999 
								 	   
			coord_rc_enemy_helis_zero1_x[2] = -2278.6934 
			coord_rc_enemy_helis_zero1_y[2] = 66.5520 
								 
			coord_rc_enemy_helis_zero1_x[3] = -2248.5403 
			coord_rc_enemy_helis_zero1_y[3] = 181.5174 

			coord_rc_enemy_helis_zero1_x[4]= -2180.4565 
			coord_rc_enemy_helis_zero1_y[4]=	22.6713 

			coord_rc_enemy_helis_zero1_x[5]=	-2181.9084 
			coord_rc_enemy_helis_zero1_y[5]=	25.0278 

			coord_rc_enemy_helis_zero1_x[6] =	-2181.3684 
			coord_rc_enemy_helis_zero1_y[6] =	18.8913 

			coord_rc_enemy_helis_zero1_x[7]=	-2180.0205 
			coord_rc_enemy_helis_zero1_y[7]=	18.1988 
		ENDIF






		coord_baron_z_offset_zero1 = coord_transmitter_z[index_target] + 10.0

		// make remaining planes do kamakazies




		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[0] 
		 //	HELI_GOTO_COORDS rc_enemy_helis_zero1[0] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[0] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
			GET_GAME_TIMER timer_heli_creation_time[0]
		  //	flag_enemy_objective[0] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[1]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[1] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[1]
		 //	flag_enemy_objective[1] = 4
		ENDIF


		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[2]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[2] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[2] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[2]
		//	flag_enemy_objective[2] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[3]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[3]
		 //	flag_enemy_objective[3] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[4]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[4] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[4]
		 //	flag_enemy_objective[4] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[5]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[5] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[5]
		 //	flag_enemy_objective[5] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[6]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[6] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[6]
		 //	flag_enemy_objective[6] = 4
		ENDIF

		IF NOT IS_CAR_DEAD rc_enemy_helis_zero1[7]
			PLANE_GOTO_COORDS rc_enemy_helis_zero1[7] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_baron_z_offset_zero1 -1.0 0.5  
		   //	HELI_GOTO_COORDS rc_enemy_helis_zero1[3] coord_transmitter_x[index_target] coord_transmitter_y[index_target] coord_transmitter_z[index_target] 10.0  coord_transmitter_z[index_target] 
			GET_GAME_TIMER timer_heli_creation_time[7]
		 //	flag_enemy_objective[7] = 4
		ENDIF
	ENDIF



RETURN

////////////////////////////////////////////	 


add_baron_blip_zero1:
	ADD_BLIP_FOR_CAR rc_enemy_helis_zero1[index_zero1] blip_zero1_rc_enemy_helis_zero1[index_zero1]
  //	CHANGE_BLIP_COLOUR blip_zero1_rc_enemy_helis_zero1[index_zero1] RED
	CHANGE_BLIP_DISPLAY blip_zero1_rc_enemy_helis_zero1[index_zero1] BLIP_ONLY 		   
RETURN











////////////////////////////////////////////////////////////////////////////////
///////////////////////// END OF MISSION CUTSCENE //////////////////////////////
////////////////////////////////////////////////////////////////////////////////

successful_cutscene_zero1:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT	
	CLEAR_WANTED_LEVEL player1
	
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE 

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

	SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 270.0 270.0



	REQUEST_ANIMATION casino

	WHILE NOT HAS_ANIMATION_LOADED casino
		WAIT 0
	ENDWHILE

	REMOVE_ANIMATION ON_LOOKERS

 /*	IF DOES_OBJECT_EXIST object_turret_zero1
		SET_OBJECT_VISIBLE object_turret_zero1 TRUE
	ENDIF  */




	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddy_zero1 EVENT_FIRE_NEARBY
	
	// CUTSCENE OF ZERO CONGRATULATING PLAYER

	TASK_SHOOT_AT_COORD scplayer -2237.22 170.55 58.45 3000

	IF IS_CHAR_DEAD char_zero_zero1 
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -2235.0 123.0 57.1 char_zero_zero1
		SET_CHAR_HEALTH char_zero_zero1 1000
		SET_CHAR_HEADING char_zero_zero1 160.0 

	ENDIF


	IF NOT IS_CHAR_DEAD char_zero_zero1
		CLEAR_CHAR_TASKS char_zero_zero1
		SET_CHAR_COORDINATES char_zero_zero1 -2235.0 123.0 57.1
		SET_CHAR_HEADING char_zero_zero1 160.0 

		

		OPEN_SEQUENCE_TASK seq_zero_success_cut_zero1
			TASK_GO_STRAIGHT_TO_COORD -1 -2237.93 121.4 57.0 PEDMOVE_RUN -1
			TASK_PLAY_ANIM -1 manwind CASINO 4.0 TRUE FALSE FALSE FALSE 2000
			TASK_ACHIEVE_HEADING -1 100.0
			TASK_SCRATCH_HEAD -1
			TASK_CHAT_WITH_CHAR -1 SCPLAYER TRUE TRUE

		   //	SET_SEQUENCE_TO_REPEAT seq_zero_success_cut_zero1 1
		CLOSE_SEQUENCE_TASK seq_zero_success_cut_zero1
		PERFORM_SEQUENCE_TASK char_zero_zero1 seq_zero_success_cut_zero1
		CLEAR_SEQUENCE_TASK seq_zero_success_cut_zero1







		SET_FIXED_CAMERA_POSITION -2242.8374 130.3954 60.7181 0.0 0.0 0.0

		POINT_CAMERA_AT_POINT -2242.3242 129.5807 60.4482 JUMP_CUT
	
	ENDIF

	

   	DO_FADE 1000 FADE_IN
	SET_FADING_COLOUR 0 0 0

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE 
   
  //	PRINT ZER1_27 3000 1 //Berkley you fucken cunt, I'll get you back ya rat bastard!

 //	PRINT ZER1_28 2000 1  //Thanks Carl, you saved my transmitters

 		LOAD_MISSION_AUDIO 1 SOUND_ZER1_FA 	//	Ha, Berkley!
		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			WAIT 0
		ENDWHILE  
		PRINT_NOW ( ZER1_FA ) 10000 1		
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0																						
		ENDWHILE
		CLEAR_THIS_PRINT ZER1_FA
		
		LOAD_MISSION_AUDIO 1 SOUND_ZER1_FB 	//	As long as we have opposable thumbs, WE WILL FIGHT YOU!
		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			WAIT 0
		ENDWHILE  
		PRINT_NOW ( ZER1_FB  ) 10000 1		
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0																						
		ENDWHILE
		CLEAR_THIS_PRINT ZER1_FB  
 


		/*

		ZER1_FA 	//	Ha, Berkley!
		ZER1_FB 	//	As long as we have opposable thumbs, WE WILL FIGHT YOU!
		ZER1_FC	//	...Well done, Carl.
		ZER1_FD	//	Now leave, I must prepare for the battles to come!
		ZER1_FE	//	Never have so few, owed so many, no, wait...
		ZER1_FF	//	We will fight him on the beaches, well, the rooftops...


		   */





	flag_cutscene_zero1 = 0

	TIMERB = 0
	TIMERA = 0
	WHILE NOT flag_cutscene_zero1 = 10
		WAIT 0
		IF flag_cutscene_zero1 = 0
			IF TIMERA > 2000
				flag_cutscene_zero1 = 1

 
				

				SET_FIXED_CAMERA_POSITION -2238.8530 124.0934 57.5797 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2238.4761 123.2044 57.8399  JUMP_CUT
				TIMERA = 0
			/*	IF NOT IS_CHAR_DEAD char_zero_zero1
					POINT_CAMERA_AT_CHAR char_zero_zero1 FIXED JUMP_CUT
				ENDIF 	   */
				

				IF NOT IS_CHAR_DEAD char_zero_zero1
					TASK_LOOK_AT_CHAR scplayer char_zero_zero1 3500
				ENDIF 

				LOAD_MISSION_AUDIO 1 SOUND_ZER1_FC 	//	WELL DONE CARL
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

				IF NOT IS_CHAR_DEAD char_zero_zero1
					START_CHAR_FACIAL_TALK char_zero_zero1 10000
				ENDIF

				PRINT_NOW ( ZER1_FC  ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0																						
				ENDWHILE
				CLEAR_THIS_PRINT ZER1_FC

				IF NOT IS_CHAR_DEAD char_zero_zero1
					STOP_CHAR_FACIAL_TALK char_zero_zero1
				ENDIF

				LOAD_MISSION_AUDIO 1 SOUND_ZER1_FD 	//	Now leave, I must prepare for the battles ahead!
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

				IF NOT IS_CHAR_DEAD char_zero_zero1
					START_CHAR_FACIAL_TALK char_zero_zero1 10000
				ENDIF
				PRINT_NOW ( ZER1_FD  ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0																						
				ENDWHILE
				CLEAR_THIS_PRINT ZER1_FD

				IF NOT IS_CHAR_DEAD char_zero_zero1
					STOP_CHAR_FACIAL_TALK char_zero_zero1
				ENDIF




			ENDIF
		ENDIF

		IF flag_cutscene_zero1 = 1
			IF TIMERA > 100
				flag_cutscene_zero1 = 2
				TIMERA = 0
				DETACH_CHAR_FROM_CAR scplayer
				SET_FIXED_CAMERA_POSITION -2237.9753 122.7383 58.3408  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2238.1929 121.7735 58.4879 JUMP_CUT
			ENDIF
		ENDIF

		IF flag_cutscene_zero1 = 2
			IF TIMERA > 200
				flag_cutscene_zero1 = 3
				TIMERA = 0
				
				OPEN_SEQUENCE_TASK seq_zero_success_cut_zero1
					TASK_ACHIEVE_HEADING -1 270.0
					IF NOT IS_CHAR_DEAD char_zero_zero1
						TASK_CHAT_WITH_CHAR -1 char_zero_zero1 FALSE TRUE
					ENDIF
				CLOSE_SEQUENCE_TASK seq_zero_success_cut_zero1
				PERFORM_SEQUENCE_TASK scplayer seq_zero_success_cut_zero1
				CLEAR_SEQUENCE_TASK seq_zero_success_cut_zero1



				LOAD_MISSION_AUDIO 1 SOUND_ZER1_FE 	//	[ZER1_FE] Never have so few owed so many
 				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  
				PRINT_NOW ( ZER1_FE  ) 10000 1	
				
				IF NOT IS_CHAR_DEAD char_zero_zero1
					START_CHAR_FACIAL_TALK char_zero_zero1 10000
				ENDIF	
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0																						
				ENDWHILE
				CLEAR_THIS_PRINT ZER1_FE

				IF NOT IS_CHAR_DEAD char_zero_zero1
					STOP_CHAR_FACIAL_TALK char_zero_zero1
				ENDIF



				LOAD_MISSION_AUDIO 1 SOUND_ZER1_FG  	//	ZER1_FG too little, three…no that’s not it, what is it…
 
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  
				PRINT_NOW ( ZER1_FG  ) 10000 1		

				IF NOT IS_CHAR_DEAD char_zero_zero1
					START_CHAR_FACIAL_TALK char_zero_zero1 10000
				ENDIF

				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0																						

				ENDWHILE
				CLEAR_THIS_PRINT ZER1_FG
				IF NOT IS_CHAR_DEAD char_zero_zero1
					STOP_CHAR_FACIAL_TALK char_zero_zero1
				ENDIF

				IF NOT IS_CHAR_DEAD char_zero_zero1
					TASK_SCRATCH_HEAD char_zero_zero1
				ENDIF


				LOAD_MISSION_AUDIO 1 SOUND_ZER1_FF 	//	[ZER1_FF] We will fight him on the beaches, well, the rooftops...
 				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

				IF NOT IS_CHAR_DEAD char_zero_zero1
					START_CHAR_FACIAL_TALK char_zero_zero1 10000
				ENDIF

				PRINT_NOW ( ZER1_FF  ) 10000 1		
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0	
				ENDWHILE
				CLEAR_THIS_PRINT ZER1_FF

				IF NOT IS_CHAR_DEAD char_zero_zero1
					STOP_CHAR_FACIAL_TALK char_zero_zero1 
				ENDIF





				flag_cutscene_zero1 = 10

			ENDIF
		ENDIF

		// Safety Timer
		IF TIMERB > 14000
			flag_cutscene_zero1 = 10
		ENDIF

	ENDWHILE

	
	//SET_PLAYER_CONTROL player1 ON
  //	RESTORE_CAMERA_JUMPCUT

	SET_FADING_COLOUR 0 0 0

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE 

	SWITCH_WIDESCREEN OFF

	CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
	CLEAR_ONSCREEN_TIMER mission_countdown_zero1


	DETACH_CHAR_FROM_CAR scplayer

	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

		SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203
		TASK_ACHIEVE_HEADING scplayer 90.0
	ENDIF

	WAIT 500
	RESTORE_CAMERA_JUMPCUT
	CLEAR_AREA -2246.0264 134.8950 34.3047 1000.0 TRUE
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


RETURN












// *****************************
// MISSION PASSED               
// *****************************

mission_zero1_passed:
	//flag_zero1_pp1_mission1_passed = 1
	GOSUB successful_cutscene_zero1

   //	WAIT 500

  //	SET_HEADING_FOR_ATTACHED_PLAYER Player1 90.0 90.0



	SET_PLAYER_CONTROL player1 ON






	PRINT_WITH_NUMBER_BIG M_PASS 3000 100 1
	ADD_SCORE player1 3000
	
	PLAY_MISSION_PASSED_TUNE 1
	PLAYER_MADE_PROGRESS 1
	REGISTER_MISSION_PASSED ( zero_1 )
	//START_NEW_SCRIPT pp1_mission_loop


	
	flag_zero_mission_counter++

RETURN
		
// *****************************
// mission failed
// *****************************

mission_zero1_failed:


   

	PRINT_BIG M_FAIL 5000 1
   	WAIT 5000

	DO_FADE 500 FADE_OUT
	SET_FADING_COLOUR 0 0 0
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
	CLEAR_ONSCREEN_TIMER mission_countdown_zero1




//	SET_HEADING_FOR_ATTACHED_PLAYER Player1 90.0 90.0

/*	IF IS_CHAR_DEAD char_zero_zero1

		PRINT_NOW ZER1_25 5000 1 // zeros deed
	ENDIF	 */



	DETACH_CHAR_FROM_CAR scplayer



		  
	SWITCH_WIDESCREEN OFF

	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

		SET_CHAR_COORDINATES scplayer -2244.4438 136.8633 34.3203
		TASK_ACHIEVE_HEADING scplayer 90.0
	ENDIF

	//WAIT 500

	LOAD_SCENE -2246.0264 134.8950 34.3047
	RESTORE_CAMERA_JUMPCUT
	CLEAR_AREA -2246.0264 134.8950 34.3047 1000.0 TRUE
	DO_FADE 1000 FADE_IN

   

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD scplayer
		SET_PLAYER_CONTROL player1 ON
	ENDIF



	


	


	   //	SET_CHAR_COORDINATES scplayer -2246.0264 134.8950 34.3047
	
RETURN

// *****************************
// mission cleanup
// *****************************

mission_cleanup_zero1:


  	DETACH_CHAR_FROM_CAR scplayer
	RELEASE_WEATHER

	SET_FADING_COLOUR 0 0 0





	
	
	REMOVE_ALL_SCRIPT_FIRES

	 
	


	
	flag_player_on_mission = 0
	SET_POLICE_IGNORE_PLAYER player1 OFF
	SET_WANTED_MULTIPLIER 1.0
	REMOVE_RC_BUGGY

   //	SWITCH_ROADS_ON -2471.72 -89.28 26.25 -2037.99 386.94 98.0	


	SET_CAR_DENSITY_MULTIPLIER 1.0
	//SET_PED_DENSITY_MULTIPLIER 1.0


	
	
	MARK_MODEL_AS_NO_LONGER_NEEDED rcbaron 
	MARK_MODEL_AS_NO_LONGER_NEEDED rcraider
	MARK_MODEL_AS_NO_LONGER_NEEDED minigun
	MARK_MODEL_AS_NO_LONGER_NEEDED wongs_erection
	MARK_MODEL_AS_NO_LONGER_NEEDED rcbomb
	MARK_MODEL_AS_NO_LONGER_NEEDED wongs_erection2



	MARK_MODEL_AS_NO_LONGER_NEEDED minigun_base
	MARK_MODEL_AS_NO_LONGER_NEEDED fire_ex
  //	MARK_MODEL_AS_NO_LONGER_NEEDED skimmer
   	
	UNLOAD_SPECIAL_CHARACTER 1



	CLEAR_ONSCREEN_COUNTER transmitter_overallhealth 
	CLEAR_ONSCREEN_TIMER mission_countdown_zero1

   //	MARK_CAR_AS_NO_LONGER_NEEDED rc_enemy_kamikaze_zero1

   //	DELETE_CAR rc_enemy_kamikaze_zero1 
   //	REMOVE_BLIP blip_rc_enemy_kamikaze_zero1

	DELETE_CAR rc_enemy_helis_zero1[0]
	DELETE_CAR rc_enemy_helis_zero1[1]
	DELETE_CAR rc_enemy_helis_zero1[2]
	DELETE_CAR rc_enemy_helis_zero1[3]
	DELETE_CAR rc_enemy_helis_zero1[4]
	DELETE_CAR rc_enemy_helis_zero1[5]
	DELETE_CAR rc_enemy_helis_zero1[6]
	DELETE_CAR rc_enemy_helis_zero1[7]
	DELETE_CAR rc_enemy_helis_zero1[8]
	DELETE_CAR rc_enemy_helis_zero1[9]

	CLEAR_SMALL_PRINTS






	CAMERA_RESET_NEW_SCRIPTABLES
	DISABLE_ALL_ENTRY_EXITS FALSE


	DELETE_OBJECT object_transmitter[0]
	DELETE_OBJECT object_transmitter[1]
	DELETE_OBJECT object_transmitter[2]
	DELETE_OBJECT object_transmitter[3]
	DELETE_OBJECT object_transmitter[4]
	DELETE_OBJECT object_transmitter[5]
	DELETE_OBJECT object_transmitter[6]
	DELETE_OBJECT object_transmitter[7]
	DELETE_OBJECT object_transmitter[8]
	DELETE_OBJECT object_transmitter[9] 

	DELETE_OBJECT primed_bomb_1_zero1[0]
	DELETE_OBJECT primed_bomb_1_zero1[1]
	DELETE_OBJECT primed_bomb_1_zero1[2]
	DELETE_OBJECT primed_bomb_1_zero1[3]
	DELETE_OBJECT primed_bomb_1_zero1[4]
	DELETE_OBJECT primed_bomb_1_zero1[5]
	DELETE_OBJECT primed_bomb_1_zero1[6]
	DELETE_OBJECT primed_bomb_1_zero1[7]
	DELETE_OBJECT primed_bomb_1_zero1[8]
	DELETE_OBJECT primed_bomb_1_zero1[9]

	DELETE_OBJECT falling_bomb_1_zero1[0]
	DELETE_OBJECT falling_bomb_1_zero1[1]
	DELETE_OBJECT falling_bomb_1_zero1[2]
	DELETE_OBJECT falling_bomb_1_zero1[3]
	DELETE_OBJECT falling_bomb_1_zero1[4]
	DELETE_OBJECT falling_bomb_1_zero1[5]
	DELETE_OBJECT falling_bomb_1_zero1[6]
	DELETE_OBJECT falling_bomb_1_zero1[7]
	DELETE_OBJECT falling_bomb_1_zero1[8]
	DELETE_OBJECT falling_bomb_1_zero1[9]





	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[0]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[1]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[2]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[3]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[4]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[5]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[6]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[7]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[8]
	REMOVE_BLIP blip_zero1_rc_enemy_helis_zero1[9]

	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[0]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[1]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[2]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[3]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[4]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[5]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[6]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[7]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[8]
	REMOVE_STUCK_CAR_CHECK rc_enemy_helis_zero1[9]


	GET_GAME_TIMER timer_mobile_start


	REMOVE_CHAR_ELEGANTLY char_zero_zero1  
	MARK_OBJECT_AS_NO_LONGER_NEEDED object_turret_zero1

	REMOVE_ANIMATION casino
	REMOVE_ANIMATION ON_LOOKERS



	MISSION_HAS_FINISHED

RETURN


}