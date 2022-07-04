MISSION_START
// *****************************************************************************************
// ************************************* RC TURF WAR 5 *************************************
// ************************************* Bonus Level ****************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_zero5

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_zero5_failed
ENDIF

GOSUB mission_cleanup_zero5

MISSION_END


// Variables for mission

//RC VEHICLES
{

LVAR_INT rc_playerheli_zero5 

LVAR_INT rc_van_zero5 

LVAR_INT random_car_zero5





//VAR_INT timer_time_limit__converted_int_zero5

VAR_INT timer_time_limit_zero5
VAR_INT van_death_counter_zero5	

//LVAR_FLOAT timer_time_limit timer_time_limit_initial timer_time_limit_converted_float
//VAR_INT  timer_time_limit_converted_int





LVAR_FLOAT coord_temp_redbaron_x	coord_temp_redbaron_y coord_temp_redbaron_z
LVAR_FLOAT coord_redbaron_x	coord_redbaron_y coord_redbaron_z heading_redbaron
LVAR_FLOAT coord_players_van_x coord_players_van_y coord_players_van_z heading_players_van

//VAR_FLOAT rotation_velocity_zero5_x rotation_velocity_zero5_y rotation_velocity_zero5_z





// OBJECT




// FLAGS

LVAR_INT flag_main_sub_function_zero5 
LVAR_INT flag_fly_next_to_transmittor_zero5
LVAR_INT flag_heli_landing_cutscene_zero5 
LVAR_INT flag_fly_back_to_player_zero5
LVAR_INT flag_mission_zero5_passed
LVAR_INT flag_mission_zero5_failed 
LVAR_INT flag_created_rc_enemies_zero5 
LVAR_INT flag_help_text
LVAR_INT flag_help_text2
LVAR_INT flag_transmittor_in_place
LVAR_INT flag_is_enemy_van_dead[5]
LVAR_INT flag_land_plane_zero5
LVAR_INT flag_self_destruct_zero2

LVAR_INT seq_zero_cutscene

LVAR_INT help_flags_zero1

LVAR_INT cutscene_flag_zero5




// BLIP

LVAR_INT blip_landing_zero5


LVAR_INT index_zero5 

LVAR_INT game_timer_zero2 stuck_timer_zero2

LVAR_FLOAT stuck_x_zero2 stuck_y_zero2 stuck_z_zero2

 


// ****************************************Mission Start************************************

mission_start_zero5:

help_flags_zero1 = 0
flag_player_on_mission = 1
CLEAR_THIS_PRINT_BIG_NOW 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME zero5
SET_PLAYER_CONTROL player1 OFF

WAIT 0


//timer_time_limit__converted_int_zero5 = 100
 



// INITIALISING VARIABLES 
flag_main_sub_function_zero5 = 1
flag_fly_next_to_transmittor_zero5 = 0
flag_heli_landing_cutscene_zero5 = 0
flag_fly_back_to_player_zero5 = 0			
flag_transmittor_in_place = 0
flag_help_text = 0
flag_help_text2 = 0
flag_land_plane_zero5 = 0

flag_self_destruct_zero2 = 0


flag_mission_zero5_passed = 0
flag_mission_zero5_failed = 0


					  


van_death_counter_zero5 = 0


		

timer_time_limit_zero5 = 180000 //5 minutes

//timer_time_limit_initial
//timer_time_limit_zero5 = 10000 //5 minutes







coord_redbaron_x = -2205.44	
coord_redbaron_y = 128.99 
coord_redbaron_z = 57.33
heading_redbaron = 90.0

coord_players_van_x =  -2227.0   
coord_players_van_y = 113.0
coord_players_van_z = 35.5  

RESET_NUM_OF_MODELS_KILLED_BY_PLAYER Player1


TIMERA = 0

index_zero5 = 0


// LOAD MODELS
 
REQUEST_MODEL rcbaron
REQUEST_MODEL topfun

WHILE NOT HAS_MODEL_LOADED rcbaron
	OR NOT HAS_MODEL_LOADED topfun
	WAIT 0
ENDWHILE

DISPLAY_CAR_NAMES FALSE



/*
REQUEST_MODEL news1

WHILE NOT HAS_MODEL_LOADED news1
	WAIT 0
ENDWHILE
  */


	 










LOAD_MISSION_TEXT ZERO2
DISABLE_ALL_ENTRY_EXITS TRUE

		 /*
DO_FADE 1000 FADE_OUT	 
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
		   */



// ******************************************** START MAIN LOOP **********************************
		
	main_zero5_loop:
		WAIT 0 

		// WAIT FOR HELI TO REACH WINDOW
		IF flag_main_sub_function_zero5 = 1
			GOSUB main_sub_function_zero5
		ENDIF

			
		IF flag_mission_zero5_passed = 1
			GOTO mission_zero5_passed
		ENDIF

		IF flag_mission_zero5_failed = 1
			GOTO mission_zero5_failed
		ENDIF

	GOTO main_zero5_loop



// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////
// ************************************     Sub Functions *****************************************
// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////







main_sub_function_zero5:


	

	
	TIMERB = 0
	    
//   SET_CHAR_COORDINATES scplayer -1851.7444 651.0315 79.4154 
	CLEAR_AREA coord_players_van_x coord_players_van_y coord_players_van_z 1000.0 TRUE 
   	CREATE_CAR TOPFUN -2250.9 124.6 28.48  rc_van_zero5
   	SET_LOAD_COLLISION_FOR_CAR_FLAG rc_van_zero5 FALSE
	

   	  //	coord_players_van_x coord_players_van_y coord_players_van_z
	OPEN_CAR_DOOR rc_van_zero5 REAR_LEFT_DOOR
	OPEN_CAR_DOOR rc_van_zero5 REAR_RIGHT_DOOR 

	WARP_CHAR_INTO_CAR scplayer rc_van_zero5
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER 

	SET_POLICE_IGNORE_PLAYER player1 ON
   	
	


  
	SET_FADING_COLOUR 0 0 0


	IF NOT IS_CAR_DEAD rc_van_zero5 
		SET_CAR_COORDINATES  rc_van_zero5 -2233.9 122.6 746.48

		SET_CAR_HEADING rc_van_zero5 90.0
		FREEZE_CAR_POSITION rc_van_zero5 TRUE
	ENDIF

   	WAIT 1000

	// END CUTSCENE

	IF NOT IS_CAR_DEAD rc_van_zero5

		CLOSE_ALL_CAR_DOORS rc_van_zero5


	ENDIF	

	SET_AREA_VISIBLE 0
	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2240.8535 129.3346 1.5 // inside rc shop

	 

   //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
	//SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993




	

	LOAD_SCENE coord_redbaron_x coord_redbaron_y coord_redbaron_z

   	GIVE_REMOTE_CONTROLLED_MODEL_TO_PLAYER player1 coord_redbaron_x coord_redbaron_y coord_redbaron_z 40.090 rcbaron
   	GET_REMOTE_CONTROLLED_CAR player1 rc_playerheli_zero5
	SET_CAR_HEADING rc_playerheli_zero5	90.0


	SET_ENABLE_RC_DETONATE_ON_CONTACT FALSE
	SET_ENABLE_RC_DETONATE FALSE
	SET_CAR_HEALTH rc_playerheli_zero5 1000	
	SET_CAR_PROOFS rc_playerheli_zero5 FALSE TRUE TRUE FALSE FALSE
 //	SET_CAR_PROOFS CarID Bulletproof Flameproof Explosionproof Collisionproof MeleeWeaponproof

   	
	  


 //	WAIT 2000
   //	RESTORE_CAMERA_JUMPCUT


	SWITCH_WIDESCREEN OFF


	IF NOT IS_CAR_DEAD rc_playerheli_zero5 
	   	POINT_CAMERA_AT_CAR rc_playerheli_zero5 CAM_ON_A_STRING JUMP_CUT 
		FREEZE_CAR_POSITION rc_playerheli_zero5 FALSE
		WAIT 0
		SET_CAMERA_ZOOM CAM_ZOOM_TWO
	ENDIF	


	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS 
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 ON


  	DISPLAY_ONSCREEN_TIMER_WITH_STRING timer_time_limit_zero5 TIMER_DOWN ZER2_5
	
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING van_death_counter_zero5 COUNTER_DISPLAY_NUMBER ZER2_43
	PRINT_NOW ZER2_45 5000 1 // destroy berkleys vans
	SET_FORCE_RANDOM_CAR_MODEL TOPFUN
	



	// WAiting for rc heli to pickup transmittor

		WHILE NOT flag_transmittor_in_place = 1 
			WAIT 0




			


			

			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
				 flag_main_sub_function_zero5 = 0
				 flag_fly_next_to_transmittor_zero5 = 0
				 flag_heli_landing_cutscene_zero5 = 0
				 flag_fly_back_to_player_zero5 = 0
				 flag_mission_zero5_passed = 1
				 flag_mission_zero5_failed = 0
				RETURN 	
			ENDIF

			IF timer_time_limit_zero5 <=0
				 //SET_PLAYER_CONTROL Player1 OFF
				IF NOT IS_CAR_DEAD rc_playerheli_zero5
				  SET_CAR_ENGINE_ON rc_playerheli_zero5 FALSE	  
				  SET_CAR_HEAVY rc_playerheli_zero5 TRUE
				ENDIF
				REMOVE_RC_BUGGY 

				TIMERA = 0

				 flag_main_sub_function_zero5 = 0
				 flag_fly_next_to_transmittor_zero5 = 0
				 flag_heli_landing_cutscene_zero5 = 0
				 flag_fly_back_to_player_zero5 = 0
				 flag_mission_zero5_passed = 1
				 flag_mission_zero5_failed = 0

		   /*		IF IS_PLAYER_PLAYING Player1
					SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2240.8535 129.3346 1.5 // inside rc shop
				

					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer -2220.1455 135.9560 1034.6406 
					ELSE
						SET_CHAR_COORDINATES scplayer -2220.1455 135.9560 1034.6406 
					ENDIF


					SET_CHAR_HEADING scplayer 183.6951 
				ENDIF	  */
				PRINT_WITH_NUMBER_BIG ZER2_43 van_death_counter_zero5 5000 1
				PLAY_MISSION_PASSED_TUNE 1
				WAIT 2000
 				SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2241.8535 128.3346 1.5 // inside rc shop
				LOAD_SCENE -2241.8535 128.3346 1034.6406
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2220.1455 135.9560 1034.6406 
				ELSE
					SET_CHAR_COORDINATES scplayer -2220.1455 135.9560 1034.6406 
				ENDIF

				SET_CHAR_HEADING scplayer 183.6951 
				SET_AREA_VISIBLE 6	//zeros toy shop

		  //		 PRINT_NOW ZER2_38 5000 1 // ran out of time
				RETURN 	
			ENDIF
			
			 
			IF NOT IS_CAR_DEAD rc_playerheli_zero5
				IF IS_PLAYER_IN_REMOTE_MODE player1
					 GET_GAME_TIMER game_timer_zero2

				     IF NOT LOCATE_CAR_3D rc_playerheli_zero5 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE
				            GET_CAR_COORDINATES rc_playerheli_zero5 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2
				            stuck_timer_zero2 = game_timer_zero2 + 4000
							flag_self_destruct_zero2 = 0
				     ENDIF

					IF LOCATE_CAR_3D rc_playerheli_zero5 stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 1.5 1.5 1.5 FALSE

						IF stuck_timer_zero2 < game_timer_zero2

							IF flag_self_destruct_zero2 = 0
								PRINT_HELP ZER2_4
								flag_self_destruct_zero2 = 1 
							ENDIF

							IF IS_XBOX_VERSION
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									flag_mission_zero5_failed = 1
									flag_main_sub_function_zero5 = 0
									SET_PLAYER_CONTROL player1 OFF
									PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!


									PRINT_BIG M_FAIL 5000 1


									SET_CAR_HEALTH rc_playerheli_zero5 1
									ADD_EXPLOSION  stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 EXPLOSION_GRENADE
									REMOVE_RC_BUGGY
									flag_mission_zero5_failed = 1
									flag_main_sub_function_zero5 = 0
									PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!

					

									WAIT 2000
									SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2241.8535 128.3346 1.5 // inside rc shop
									LOAD_SCENE -2241.8535 128.3346 1034.6406


									IF IS_CHAR_IN_ANY_CAR scplayer
										WARP_CHAR_FROM_CAR_TO_COORD scplayer -2220.1455 135.9560 1034.6406 
									ELSE
										SET_CHAR_COORDINATES scplayer -2220.1455 135.9560 1034.6406 
									ENDIF

									SET_CHAR_HEADING scplayer 183.6951 
									SET_AREA_VISIBLE 6	//zeros toy shop

									RETURN	 
								
								ENDIF
							ELSE
								IF IS_BUTTON_PRESSED PAD1 CIRCLE
									flag_mission_zero5_failed = 1
									flag_main_sub_function_zero5 = 0
									SET_PLAYER_CONTROL player1 OFF
									PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!


									PRINT_BIG M_FAIL 5000 1


									SET_CAR_HEALTH rc_playerheli_zero5 1
									ADD_EXPLOSION  stuck_x_zero2 stuck_y_zero2 stuck_z_zero2 EXPLOSION_GRENADE
									REMOVE_RC_BUGGY
									flag_mission_zero5_failed = 1
									flag_main_sub_function_zero5 = 0
									PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!

					

									WAIT 2000
									SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2241.8535 128.3346 1.5 // inside rc shop
									LOAD_SCENE -2241.8535 128.3346 1034.6406


									IF IS_CHAR_IN_ANY_CAR scplayer
										WARP_CHAR_FROM_CAR_TO_COORD scplayer -2220.1455 135.9560 1034.6406 
									ELSE
										SET_CHAR_COORDINATES scplayer -2220.1455 135.9560 1034.6406 
									ENDIF

									SET_CHAR_HEADING scplayer 183.6951 
									SET_AREA_VISIBLE 6	//zeros toy shop

									RETURN	 
								
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					
					GET_CAR_COORDINATES rc_playerheli_zero5 coord_temp_redbaron_x coord_temp_redbaron_y coord_temp_redbaron_z	
					GET_RANDOM_CAR_IN_SPHERE_NO_SAVE coord_temp_redbaron_x coord_temp_redbaron_y coord_temp_redbaron_z 30.0 TOPFUN random_car_zero5
					IF NOT IS_CAR_DEAD random_car_zero5
					   //	SET_CAR_HEALTH random_car_zero5 500
						SET_CAR_CRUISE_SPEED random_car_zero5 10.0
					ENDIF


				   /*	IF NOT LOCATE_CAR_3D rc_playerheli_zero5 coord_redbaron_x coord_redbaron_y coord_redbaron_z 20.0 20.0 20.0 FALSE 
						IF IS_BUTTON_PRESSED PAD1 CIRCLE
							SET_CAR_HEALTH rc_playerheli_zero5 1
							 
							ADD_EXPLOSION coord_temp_redbaron_x coord_temp_redbaron_y coord_temp_redbaron_z EXPLOSION_ROCKET  
							 

							REMOVE_RC_BUGGY
							EXPLODE_CAR rc_playerheli_zero5
							DO_FADE 900	FADE_OUT
							WAIT 900

							SET_FORCE_RANDOM_CAR_MODEL TOPFUN
							GIVE_REMOTE_CONTROLLED_MODEL_TO_PLAYER player1 coord_redbaron_x coord_redbaron_y coord_redbaron_z 40.090 rcbaron
							DELETE_CAR rc_playerheli_zero5
							GET_REMOTE_CONTROLLED_CAR player1 rc_playerheli_zero5
							DO_FADE 900	FADE_IN

							
						ENDIF
					ENDIF	*/
				ENDIF

		 	ELSE   
				flag_mission_zero5_failed = 1
				flag_main_sub_function_zero5 = 0
				PRINT_NOW ( ZER2_8 ) 5000 1 // You crashed your plane!
				SET_PLAYER_CONTROL player1 OFF




				WAIT 2000
				 SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -2241.8535 128.3346 1.5 // inside rc shop
				LOAD_SCENE -2241.8535 128.3346 1034.6406


				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2220.1455 135.9560 1034.6406 
				ELSE
					SET_CHAR_COORDINATES scplayer -2220.1455 135.9560 1034.6406 
				ENDIF

				SET_CHAR_HEADING scplayer 183.6951 
				SET_AREA_VISIBLE 6	//zeros toy shop

				RETURN	 
			ENDIF


			
		/*	IF NOT IS_PLAYER_IN_REMOTE_MODE player1
				SET_FORCE_RANDOM_CAR_MODEL TOPFUN
				GIVE_REMOTE_CONTROLLED_MODEL_TO_PLAYER player1 coord_redbaron_x coord_redbaron_y coord_redbaron_z 40.090 rcbaron
				DELETE_CAR rc_playerheli_zero5
				GET_REMOTE_CONTROLLED_CAR player1 rc_playerheli_zero5
				DO_FADE 900	FADE_IN
			ENDIF	 */



			GET_NUM_OF_MODELS_KILLED_BY_PLAYER player1 TOPFUN van_death_counter_zero5


		



		ENDWHILE

	

RETURN








// *****************************
// MISSION PASSED               
// *****************************

mission_zero5_passed:
	//flag_pp1_mission1_passed = 1

	SET_PLAYER_CONTROL player1 ON
	CLEAR_WANTED_LEVEL player1
  //	REGISTER_MISSION_PASSED ( zero_2 )
  //	flag_zero_mission_counter++

	IF NOT IS_CHAR_DEAD scplayer 
		SET_PLAYER_CONTROL player1 ON

	ENDIF

	 


	//START_NEW_SCRIPT pp1_mission_loop
RETURN
		
// *****************************
// mission failed
// *****************************

mission_zero5_failed:

	IF NOT IS_CHAR_DEAD scplayer 
		SET_PLAYER_CONTROL player1 ON

	ENDIF


	PRINT_BIG M_FAIL 5000 1

	WHILE TIMERA < 3000
		WAIT 0
		IF NOT IS_CAR_DEAD rc_playerheli_zero5
		//	GET_CAR_ROTATION_VELOCITY rc_playerheli_zero5 rotation_velocity_zero5_x rotation_velocity_zero5_y rotation_velocity_zero5_z
		
		  //	IF NOT rotation_velocity_zero5_x = 0.0 
		  //	SET_CAR_ROTATION_VELOCITY rc_playerheli_zero5 0.0 0.0 0.0
			SET_CAR_FORWARD_SPEED rc_playerheli_zero5 0.0
		ENDIF
	ENDWHILE

RETURN

// *****************************
// mission cleanup
// *****************************

mission_cleanup_zero5:

	CAMERA_RESET_NEW_SCRIPTABLES

 //	SET_AREA_VISIBLE 0





	flag_player_on_mission = 0
	SET_POLICE_IGNORE_PLAYER player1 OFF

	MARK_CAR_AS_NO_LONGER_NEEDED rc_van_zero5
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_ANY_CAR scplayer 

			WARP_CHAR_FROM_CAR_TO_COORD scplayer -2244.4438 136.8633 34.3203
			SET_CHAR_HEADING scplayer 90.0
		ENDIF
	ENDIF


	RELEASE_WEATHER
										  




	SET_PLAYER_CONTROL player1 ON


	DISABLE_ALL_ENTRY_EXITS FALSE







	REMOVE_RC_BUGGY

	MARK_MODEL_AS_NO_LONGER_NEEDED topfun
	MARK_MODEL_AS_NO_LONGER_NEEDED rcbaron

	CLEAR_ONSCREEN_TIMER timer_time_limit_zero5
	CLEAR_ONSCREEN_COUNTER van_death_counter_zero5
	 
	GET_GAME_TIMER timer_mobile_start

	DISPLAY_CAR_NAMES TRUE
	MISSION_HAS_FINISHED
RETURN

}
