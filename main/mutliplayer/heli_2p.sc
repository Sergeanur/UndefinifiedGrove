MISSION_START

// *****************************************************************************************
// ********************************* 2-Player Helicopter ***********************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************


SCRIPT_NAME heli_2p

// Begin...
GOSUB mission_start_2p_heli

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_2p_heli_failed
	ENDIF

GOSUB mission_2p_heli_cleanup

MISSION_END

{

// **************************************** Variables **************************************

LVAR_INT heli_ply_heli heli_frenzy_status

LVAR_INT heli_chopper[5] heli_pilot[5] 

// *************************************** Menu Stuff **************************************

LVAR_INT amount_of_menu_items_MS current_menu_position_MS menu_button_delay_MS

LVAR_INT option_number_values_MS[60] amount_of_option_values_MS[10] current_option_value_MS[10] amount_of_values_per_option_MS

LVAR_INT selection_alpha_MS   
 
// menu text
LVAR_TEXT_LABEL title option_text_MS[10] option_text_values_MS[60] 

LVAR_INT temp_int_1_MS temp_int_2_MS temp_int_3_MS temp_int_4_MS my_test_var my_test_var2 my_test_var3 my_test_var4

LVAR_FLOAT temp_float_1_MS temp_float_2_MS 

LVAR_INT player1_model_VS[3] player1_button_delay_VS player1_car_VS	player1_waiting_VS player1_chosen_car
LVAR_FLOAT player1_car_X player1_car_Y player1_car_Z player1_car_heading

// vehicle stuff
LVAR_INT vehicle_models_VS[31] last_vehicle_VS 

// temp stuff
LVAR_INT temp_int_1_VS temp_int_2_VS 

LVAR_INT heli_model_select heli_limit heli_model[10]

LVAR_INT heli_colour1 heli_colour2 h2_menu main_menu_heli_2p

LVAR_INT h2_time_ran_out

// ****************************************Mission Start************************************

mission_start_2p_heli:

HIDE_ALL_FRONTEND_BLIPS TRUE

CLEAR_PRINTS

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0 

//VIEW_INTEGER_VARIABLE heli_colour1 PPK

LOAD_MISSION_TEXT MENU2P

SET_FADING_COLOUR 0 0 0

GOSUB heli_fade_out

heli_ply_heli = 0

heli_frenzy_status = 0

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
 
WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL mp5lng
REQUEST_MODEL 497
REQUEST_MODEL 447
REQUEST_MODEL 469
REQUEST_MODEL BFYPRO

WHILE NOT HAS_MODEL_LOADED mp5lng
OR NOT HAS_MODEL_LOADED 497
OR NOT HAS_MODEL_LOADED 447
OR NOT HAS_MODEL_LOADED 469
OR NOT HAS_MODEL_LOADED BFYPRO

	WAIT 0

ENDWHILE

TIMERA = 0

SET_CAR_DENSITY_MULTIPLIER 1.0

CLEAR_AREA -252.9156 2583.7788 62.5703 40.0 TRUE

SWITCH heli_2p_level

		CASE 0

			my_test_var2 = 8 // Cars		

			my_test_var3 = 3 // Minutes

			my_test_var = 180000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 1

			my_test_var2 = 8  // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 2 

			my_test_var2 = 8  // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 3

			my_test_var2 = 10  // Cars		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 4

			my_test_var2 = 10  // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 5 

			my_test_var2 = 10  // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 6

			my_test_var2 = 12 // Cars		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 7

			my_test_var2 = 12 // Cars			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

		CASE 8 

			my_test_var2 = 12 // Cars		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 497 -252.9156 2583.7788 62.5703 heli_ply_heli

		BREAK

ENDSWITCH

IF DOES_VEHICLE_EXIST heli_ply_heli

	SET_CAR_HEADING heli_ply_heli 310.1937  
	
	LOCK_CAR_DOORS heli_ply_heli CARLOCK_LOCKED_PLAYER_INSIDE

	CREATE_PLAYER 1 -252.9156 2583.7788 62.5703 player2

	SET_PLAYER_MODEL player2 BFYPRO

	GET_PLAYER_CHAR player2 p2 

	IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_COORDINATES scplayer -252.9156 2583.7788 62.5703

	WARP_CHAR_INTO_CAR scplayer heli_ply_heli

	WARP_CHAR_INTO_CAR_AS_PASSENGER p2 heli_ply_heli 0

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER2

	GIVE_WEAPON_TO_CHAR p2 WEAPONTYPE_MP5 30000

	SET_CURRENT_CHAR_WEAPON p2 WEAPONTYPE_MP5

	SET_PLAYER_PLAYER_TARGETTING FALSE

	SET_CHAR_NEVER_TARGETTED scplayer TRUE

	SET_CHAR_NEVER_TARGETTED p2 TRUE 

	SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT

	LIMIT_TWO_PLAYER_DISTANCE 20.0

	DISABLE_CHAR_SPEECH p2 TRUE

	ENDIF

ELSE

	GOTO mission_2p_heli_failed

ENDIF

	GOSUB heli_set_camera

	MAKE_PLAYER_GANG_DISAPPEAR

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

	SET_FIXED_CAMERA_POSITION -252.9156 2575.8601 65.3400 0.0 0.0 0.0 
	POINT_CAMERA_AT_POINT -252.7241 2576.8193 65.1324 JUMP_CUT

	GOSUB heli_fade_in

	IF NOT my_test_var3 = 1

		PRINT_WITH_2_NUMBERS_NOW MENU_64 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ vehicles in ~1~ minutes

	ELSE

		PRINT_WITH_2_NUMBERS_NOW MENU_63 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ vehicles in ~1~ minute

	ENDIF

	WAIT 5000

	GOSUB heli_restore_camera

	LVAR_INT a_level_display	

	a_level_display = heli_2p_level + 1

   //	PRINT_WITH_NUMBER_BIG MENU_45 a_level_display 3000 1 // LEVEL ~1~

	START_KILL_FRENZY DUMMY WEAPONTYPE_MP5 my_test_var my_test_var2 -2 -2 -2 -2 FALSE

	// *************************************************************************************************
	// *																							   *
	// *	                                    MAIN LOOP               							   *
	// *																							   *
	// *************************************************************************************************

	TIMERB = 0

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2
	AND NOT IS_CAR_DEAD heli_ply_heli

		WAIT 0

		GOSUB heli_keys

		IF IS_CAR_IN_WATER heli_ply_heli

			FAIL_KILL_FRENZY

		ENDIF

	    READ_KILL_FRENZY_STATUS heli_frenzy_status
	    
	    IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S

			my_test_var = my_test_var - TIMERB

	    	heli_frenzy_status = KILLFRENZY_PASSED	  

		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F

	    	heli_frenzy_status = KILLFRENZY_FAILED	  

		ENDIF

	    IF heli_frenzy_status = KILLFRENZY_PASSED

			my_test_var = my_test_var - TIMERB

			GOTO mission_2p_heli_passed

		ENDIF

		IF heli_frenzy_status = KILLFRENZY_FAILED

			h2_time_ran_out = 1

			GOTO mission_2p_heli_failed

		ENDIF
	      	
	ENDWHILE

GOTO mission_2p_heli_failed

mission_2p_heli_failed:

	IF h2_time_ran_out = 0

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
   
mission_2p_heli_passed:

	LVAR_INT best_time_2p

	my_test_var = my_test_var / 1000

	GET_INT_STAT P2_HELI_RAMPAGE_BEST_TIME best_time_2p

	IF best_time_2p	< my_test_var

		SET_INT_STAT P2_HELI_RAMPAGE_BEST_TIME my_test_var

	ENDIF

	IF NOT IS_CAR_DEAD heli_ply_heli

	 	LOCK_CAR_DOORS heli_ply_heli CARLOCK_UNLOCKED
		
	ENDIF

	IF NOT heli_2p_level = 8

		heli_2p_level ++

	ENDIF

	IF heli_2p_level = 8

		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"

	ELSE

		PRINT_WITH_NUMBER_BIG ( MENU_67 ) 0 5000 1 //"Mission Passed!"

	ENDIF

	CLEAR_WANTED_LEVEL player1

RETURN		

// mission cleanup
mission_2p_heli_cleanup:

	HIDE_ALL_FRONTEND_BLIPS FALSE

	h2_time_ran_out = 0

	MAKE_PLAYER_GANG_REAPPEAR

	SET_WANTED_MULTIPLIER 1.0 

	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED 497
	MARK_MODEL_AS_NO_LONGER_NEEDED 447
	MARK_MODEL_AS_NO_LONGER_NEEDED 469
	MARK_MODEL_AS_NO_LONGER_NEEDED 487
	MARK_MODEL_AS_NO_LONGER_NEEDED 488
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO

	IF NOT IS_CAR_DEAD heli_ply_heli
	 
		LOCK_CAR_DOORS heli_ply_heli CARLOCK_UNLOCKED

	ENDIF

	flag_player_on_mission = 0

	CLEAR_WANTED_LEVEL player1

	MISSION_HAS_FINISHED

RETURN

heli_set_camera:

	SWITCH_WIDESCREEN ON

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

heli_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	
	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON
	
	ENDIF

	RESTORE_CAMERA_JUMPCUT
 
RETURN

heli_fade_out:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

heli_fade_in:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

heli_keys:

LVAR_INT heli_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
	
		GOTO mission_2p_heli_passed	

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
   


  


