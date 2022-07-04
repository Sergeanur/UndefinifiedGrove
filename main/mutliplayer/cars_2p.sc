MISSION_START

// *****************************************************************************************
// *********************************** 2-Player Cars ***************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

SCRIPT_NAME cars_2p

// Begin...
GOSUB mission_start_2p_cars

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_2p_cars_failed
	ENDIF

GOTO mission_2p_cars_cleanup

//GOSUB mission_2p_cars_cleanup

//MISSION_END

{

LVAR_INT amount_of_menu_items_MS current_menu_position_MS menu_button_delay_MS

LVAR_INT option_number_values_MS[60] amount_of_option_values_MS[10] current_option_value_MS[10] amount_of_values_per_option_MS

LVAR_INT selection_alpha_MS   
 
// menu text
LVAR_TEXT_LABEL title option_text_MS[10] option_text_values_MS[60] 

LVAR_INT temp_int_1_MS temp_int_2_MS temp_int_3_MS temp_int_4_MS my_test_var my_test_var2 my_test_var3 my_test_var4

LVAR_FLOAT temp_float_1_MS temp_float_2_MS 

LVAR_INT player1_model_VS[3] player1_button_delay_VS player1_car_VS	player1_waiting_VS player1_chosen_car
LVAR_FLOAT player1_car_X player1_car_Y player1_car_Z player1_car_heading


// **************************************** Variables **************************************

LVAR_INT peds_frenzy_status 

LVAR_INT cars_ply_car cars_ply_car2 v

LVAR_INT black_car[5] cars_some_guy

// **************************************** Variables **************************************

LVAR_INT vehicle_models_VS[31] last_vehicle_VS 

// temp stuff
LVAR_INT temp_int_1_VS temp_int_2_VS 

LVAR_INT heli_model_select heli_limit heli_model[20]

LVAR_INT heli_colour1 heli_colour2

LVAR_INT c2_menu main_menu_cars_2p

LVAR_INT c2_time_ran_out 


// ****************************************Mission Start************************************

mission_start_2p_cars:

HIDE_ALL_FRONTEND_BLIPS TRUE

CLEAR_PRINTS

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0 

SET_FADING_COLOUR 0 0 0

peds_frenzy_status = 0
cars_ply_car       = 0
cars_ply_car2      = 0
cars_some_guy      = 0

REPEAT 5 v

	black_car[v] = 0

ENDREPEAT

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT MENU2P

//GOSUB cars2_fade_out

WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL mp5lng
REQUEST_MODEL BFYPRO

WHILE NOT HAS_MODEL_LOADED BFYPRO
	WAIT 0
ENDWHILE

TIMERA = 0

	SET_CAR_DENSITY_MULTIPLIER 2.0

	SET_PED_DENSITY_MULTIPLIER 0.0

	CLEAR_AREA -2102.8484 653.8868 51.3671 40.0 TRUE

	SWITCH cars_2p_level

		CASE 0

			REQUEST_MODEL 405

			WHILE NOT HAS_MODEL_LOADED 405
				WAIT 0
			ENDWHILE

			my_test_var2 = 5 // Cars		

			my_test_var3 = 3 // Minutes

			my_test_var = 180000

			CREATE_CAR 405 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 1

			REQUEST_MODEL 405

			WHILE NOT HAS_MODEL_LOADED 405
				WAIT 0
			ENDWHILE

			my_test_var2 = 5  // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 405 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 2 

			REQUEST_MODEL 405

			WHILE NOT HAS_MODEL_LOADED 405
				WAIT 0
			ENDWHILE

			my_test_var2 = 5  // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 405 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 3

			REQUEST_MODEL 420

			WHILE NOT HAS_MODEL_LOADED 420
				WAIT 0
			ENDWHILE

			my_test_var2 = 8  // Cars		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 420 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 4

			REQUEST_MODEL 420

			WHILE NOT HAS_MODEL_LOADED 420
				WAIT 0
			ENDWHILE

			my_test_var2 = 8  // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 420 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 5 

			REQUEST_MODEL 420

			WHILE NOT HAS_MODEL_LOADED 420
				WAIT 0
			ENDWHILE

			my_test_var2 = 8  // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 420 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 6

			REQUEST_MODEL 502

			WHILE NOT HAS_MODEL_LOADED 502
				WAIT 0
			ENDWHILE

			my_test_var2 = 10 // Cars		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 502 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 7
										  
			REQUEST_MODEL 502

			WHILE NOT HAS_MODEL_LOADED 502
				WAIT 0
			ENDWHILE

			my_test_var2 = 10 // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 502 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

		CASE 8 

			REQUEST_MODEL 502

			WHILE NOT HAS_MODEL_LOADED 502
				WAIT 0
			ENDWHILE

			my_test_var2 = 10 // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 502 -2103.0000 654.4047 51.3703 cars_ply_car

		BREAK

	ENDSWITCH

	SET_CAR_HEADING cars_ply_car 269.5643  
	 
	LOCK_CAR_DOORS cars_ply_car CARLOCK_LOCKED_PLAYER_INSIDE

	CREATE_PLAYER 1 -2104.5562 654.4047 51.3703 player2

	SET_PLAYER_MODEL player2 BFYPRO

	GET_PLAYER_CHAR player2 p2

	IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_COORDINATES scplayer -2104.5562 654.4047 51.3703

	WARP_CHAR_INTO_CAR scplayer cars_ply_car

	WARP_CHAR_INTO_CAR_AS_PASSENGER p2 cars_ply_car 0

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER2

	SET_PLAYER_PLAYER_TARGETTING FALSE

	SET_CHAR_NEVER_TARGETTED scplayer TRUE

	SET_CHAR_NEVER_TARGETTED p2 TRUE 

	SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT

	LIMIT_TWO_PLAYER_DISTANCE 20.0

	DISABLE_CHAR_SPEECH p2 TRUE

	ENDIF

 	GOSUB cars2_set_camera 
	
	MAKE_PLAYER_GANG_DISAPPEAR

	IF IS_PLAYER_PLAYING player1								 
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

	SET_FIXED_CAMERA_POSITION -2105.8430 651.8388 52.53596 0.0 0.0 0.0 // Bike from front
   	POINT_CAMERA_AT_POINT -2105.1243 652.5283 52.4463 JUMP_CUT

	GOSUB cars2_fade_in

	IF NOT my_test_var3 = 1

		PRINT_WITH_2_NUMBERS_NOW MENU_64 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ vehicles in ~1~ minutes

	ELSE

		PRINT_WITH_2_NUMBERS_NOW MENU_63 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ vehicles in ~1~ minute

	ENDIF

	WAIT 5000

	GOSUB cars2_restore_camera

	LVAR_INT cars2_level_display	

	cars2_level_display = cars_2p_level + 1

	//PRINT_WITH_NUMBER_BIG MENU_45 cars2_level_display 3000 1 // LEVEL ~1~

 	START_KILL_FRENZY DUMMY WEAPONTYPE_MP5 my_test_var my_test_var2 -2 -2 -2 -2 FALSE

	SET_CAR_DENSITY_MULTIPLIER 2.0

	// *************************************************************************************************
	// *																							   *
	// *	                                    MAIN LOOP               							   *
	// *																							   *
	// *************************************************************************************************

	TIMERB = 0

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2
	AND NOT IS_CAR_DEAD cars_ply_car

		WAIT 0

		GOSUB cars2_keys

		IF IS_CAR_IN_WATER cars_ply_car

			FAIL_KILL_FRENZY

		ENDIF


	    READ_KILL_FRENZY_STATUS peds_frenzy_status
	    	    
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S

			my_test_var = my_test_var - TIMERB

	    	peds_frenzy_status = KILLFRENZY_PASSED	  

		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F

	    	peds_frenzy_status = KILLFRENZY_FAILED	  

		ENDIF

	    IF peds_frenzy_status = KILLFRENZY_PASSED

			my_test_var = my_test_var - TIMERB

			GOTO mission_2p_cars_passed

		ENDIF

		IF peds_frenzy_status = KILLFRENZY_FAILED

			c2_time_ran_out = 1

			GOTO mission_2p_cars_failed

		ENDIF
	      	
	ENDWHILE

GOTO mission_2p_cars_failed

mission_2p_cars_failed:

	IF c2_time_ran_out = 0

		IF IS_CHAR_DEAD scplayer

			PRINT_NOW ( MENU_47 ) 4000 1 //	~r~Player one has been killed

		ENDIF

		IF IS_CHAR_DEAD p2

			PRINT_NOW ( MENU_48 ) 4000 1 //	~r~Player two has been killed

		ENDIF

		IF IS_CHAR_DEAD p2
		AND IS_CHAR_DEAD scplayer

			PRINT_NOW ( MENU_49 ) 4000 1 //	~r~Both players were killed

		ENDIF

	ELSE

		PRINT_NOW ( MENU_50 ) 4000 1 //	~r~You didn't get enough kills in time!

	ENDIF

	PRINT_BIG ( M_FAIL ) 5000 1

RETURN
   
mission_2p_cars_passed:

	LVAR_INT best_time_2p

	my_test_var = my_test_var / 1000

	GET_INT_STAT P2_CARS_RAMPAGE_BEST_TIME best_time_2p

	IF best_time_2p	< my_test_var

		SET_INT_STAT P2_CARS_RAMPAGE_BEST_TIME my_test_var

	ENDIF

	IF cars_2p_level = 8

		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"

	ELSE

		PRINT_WITH_NUMBER_BIG ( MENU_67 ) 0 5000 1 //"Mission Passed!"

	ENDIF

	CLEAR_WANTED_LEVEL player1

	IF NOT IS_CAR_DEAD cars_ply_car

	 	LOCK_CAR_DOORS cars_ply_car CARLOCK_UNLOCKED

	ENDIF

	IF NOT cars_2p_level = 8

		cars_2p_level ++ 

	ENDIF

RETURN	

// mission cleanup
mission_2p_cars_cleanup:

	HIDE_ALL_FRONTEND_BLIPS FALSE

	c2_time_ran_out = 0

	MAKE_PLAYER_GANG_REAPPEAR

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0 

	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED 405
	MARK_MODEL_AS_NO_LONGER_NEEDED 420
	MARK_MODEL_AS_NO_LONGER_NEEDED 502
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO

	flag_player_on_mission = 0

	CLEAR_WANTED_LEVEL player1

	MISSION_HAS_FINISHED

	IF NOT IS_CAR_DEAD cars_ply_car

	 	LOCK_CAR_DOORS cars_ply_car CARLOCK_UNLOCKED

	ENDIF

MISSION_END

cars2_set_camera:

	SWITCH_WIDESCREEN ON

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

cars2_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	
	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON
	
	ENDIF

	RESTORE_CAMERA_JUMPCUT
 
RETURN

cars2_fade_out:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

cars2_fade_in:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

cars2_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

		IF NOT IS_CAR_DEAD cars_ply_car

		 	LOCK_CAR_DOORS cars_ply_car CARLOCK_UNLOCKED
			
		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
	
		GOTO mission_2p_cars_passed	

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		IF NOT IS_CHAR_DEAD p2

			TASK_DIE p2

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			TASK_DIE scplayer

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		IF NOT IS_CHAR_DEAD scplayer

			ADD_ARMOUR_TO_CHAR scplayer 100
			INCREASE_PLAYER_MAX_HEALTH player1 100

		ENDIF

	ENDIF

RETURN

}


  
