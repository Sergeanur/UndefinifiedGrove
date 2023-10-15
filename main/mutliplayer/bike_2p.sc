MISSION_START

// *****************************************************************************************
// *********************************** 2-Player Bikes **************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

SCRIPT_NAME bike_2p

// Begin...
GOSUB mission_start_2p_bike

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_2p_bike_failed
	ENDIF

GOSUB mission_2p_bike_cleanup

MISSION_END

{

LVAR_INT amount_of_menu_items_MS current_menu_position_MS menu_button_delay_MS

LVAR_INT option_number_values_MS[60] amount_of_option_values_MS[10] current_option_value_MS[10] amount_of_values_per_option_MS

LVAR_INT selection_alpha_MS   
 
// menu text
LVAR_TEXT_LABEL title option_text_MS[10] option_text_values_MS[60] 

LVAR_INT temp_int_1_MS temp_int_2_MS temp_int_3_MS temp_int_4_MS my_test_var my_test_var2 my_test_var3 my_test_var4

LVAR_FLOAT temp_float_1_MS temp_float_2_MS 

LVAR_INT player1_model_VS[3] player1_button_delay_VS player1_car_VS	player1_waiting_VS player1_chosen_car

LVAR_FLOAT player1_car_X player1_car_Y player1_car_Z player1_car_heading b2_my_gun

// **************************************** Variables **************************************

LVAR_INT peds_frenzy_status 

LVAR_INT bike_ply_bike bike_ply_bike2 v

LVAR_INT black_car[5] bike_some_guy

// **************************************** Variables **************************************

LVAR_INT vehicle_models_VS[31] last_vehicle_VS 

// temp stuff
LVAR_INT temp_int_1_VS temp_int_2_VS b2_time_ran_out

LVAR_INT heli_model_select heli_limit heli_model[20]

LVAR_INT heli_colour1 heli_colour2 b2_menu main_menu_bike_2p

// ****************************************Mission Start************************************

mission_start_2p_bike:

HIDE_ALL_FRONTEND_BLIPS TRUE

CLEAR_PRINTS

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0 

SET_FADING_COLOUR 0 0 0

peds_frenzy_status = 0
bike_ply_bike      = 0
bike_ply_bike2     = 0
bike_some_guy      = 0

REPEAT 5 v

	black_car[v] = 0

ENDREPEAT

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT MENU2P

//DISPLAY_HUD FALSE

GOSUB bike2_fade_out

WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL mp5lng
REQUEST_MODEL BFYPRO
REQUEST_MODEL fcr900
REQUEST_MODEL nrg500
REQUEST_MODEL pcj600

WHILE NOT HAS_MODEL_LOADED mp5lng
OR NOT HAS_MODEL_LOADED BFYPRO
OR NOT HAS_MODEL_LOADED fcr900
OR NOT HAS_MODEL_LOADED nrg500
OR NOT HAS_MODEL_LOADED pcj600

	WAIT 0

ENDWHILE

TIMERA = 0

SUPPRESS_CAR_MODEL pizzaboy
SUPPRESS_CAR_MODEL faggio
SUPPRESS_CAR_MODEL freeway
SUPPRESS_CAR_MODEL bike
SUPPRESS_CAR_MODEL mtbike
SUPPRESS_CAR_MODEL copbike
SUPPRESS_CAR_MODEL bf400
SUPPRESS_CAR_MODEL wayfarer
SUPPRESS_CAR_MODEL copbike
SUPPRESS_CAR_MODEL bf400
SUPPRESS_CAR_MODEL wayfarer

SET_FORCE_RANDOM_CAR_MODEL fcr900

//SET_PED_DENSITY_MULTIPLIER 2.0

DISPLAY_RADAR FALSE	

SET_PED_DENSITY_MULTIPLIER 0.0

CLEAR_AREA 1196.4912 249.3210 18.5618 60.0 TRUE

SWITCH bike_2p_level

	CASE 0

		my_test_var2 = 5 // Bikes		

		my_test_var3 = 3 // Minutes

		my_test_var = 180000

		CREATE_CAR fcr900 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 1

		my_test_var2 = 5 // Bikes			

		my_test_var3 = 2  // Minutes

		my_test_var = 120000

		CREATE_CAR fcr900 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 2 

		my_test_var2 = 5 // Bikes		

		my_test_var3 = 1  // Minutes

		my_test_var = 60000

		CREATE_CAR fcr900 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 3

		my_test_var2 = 8  // Bikes		

		my_test_var3 = 3 // Minutes

		my_test_var = 180000

		CREATE_CAR nrg500 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 4

		my_test_var2 = 8  // Bikes			

		my_test_var3 = 2  // Minutes

		my_test_var = 120000

		CREATE_CAR nrg500 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 5 

		my_test_var2 = 8  // Bikes		

		my_test_var3 = 1  // Minutes

		my_test_var = 60000

		CREATE_CAR nrg500 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 6

		my_test_var2 = 10 // Bikes		

		my_test_var3 = 3 // Minutes

		my_test_var = 180000

		CREATE_CAR pcj600 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 7

		my_test_var2 = 10 // Bikes			

		my_test_var3 = 2  // Minutes

		my_test_var = 120000

		CREATE_CAR pcj600 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

	CASE 8 

		my_test_var2 = 10 // Bikes		

		my_test_var3 = 1  // Minutes

		my_test_var = 60000

		CREATE_CAR pcj600 1196.4912 249.3210 18.5618 bike_ply_bike

	BREAK

ENDSWITCH

SET_CAR_HEADING bike_ply_bike 337.2467  
 
LOCK_CAR_DOORS bike_ply_bike CARLOCK_LOCKED_PLAYER_INSIDE

CREATE_PLAYER 1 1196.4912 249.3210 18.5618 player2

SET_PLAYER_MODEL player2 BFYPRO	

GET_PLAYER_CHAR player2 p2

IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_COORDINATES scplayer 1196.4912 249.3210 18.5618  

	WARP_CHAR_INTO_CAR scplayer bike_ply_bike

	WARP_CHAR_INTO_CAR_AS_PASSENGER p2 bike_ply_bike 0

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER2

	SET_PLAYER_PLAYER_TARGETTING FALSE

	SET_CHAR_NEVER_TARGETTED scplayer TRUE

	SET_CHAR_NEVER_TARGETTED p2 TRUE 

	SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT

	DISABLE_CHAR_SPEECH p2 TRUE

	LIMIT_TWO_PLAYER_DISTANCE 20.0

	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_NEVER

	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE p2 KNOCKOFFBIKE_NEVER

ENDIF

	GOSUB bike2_set_camera 
	
	MAKE_PLAYER_GANG_DISAPPEAR

	SET_FIXED_CAMERA_POSITION 1192.1661 245.3466 20.4391 0.0 0.0 0.0 // Bike from front
   	POINT_CAMERA_AT_POINT 1192.8378 246.0682 20.2713 JUMP_CUT

	GOSUB bike2_fade_in

	IF NOT my_test_var3 = 1

		PRINT_WITH_2_NUMBERS_NOW MENU_38 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ bikes in ~1~ minutes

	ELSE

		PRINT_WITH_2_NUMBERS_NOW MENU_61 my_test_var2 my_test_var3 5000 1 // Destroy ~1~ bikes in ~1~ minute

	ENDIF

	WAIT 5000

	GOSUB bike2_restore_camera
	 
	LVAR_INT a_level_display	

	a_level_display = bike_2p_level + 1

	//PRINT_WITH_NUMBER_BIG MENU_45 a_level_display 3000 1 // LEVEL ~1~

	START_KILL_FRENZY DUMMY WEAPONTYPE_MP5 my_test_var my_test_var2 fcr900 nrg500 pcj600 sanchez FALSE

	SET_CAR_DENSITY_MULTIPLIER 2.0

	// *************************************************************************************************
	// *																							   *
	// *	                                    MAIN LOOP               							   *
	// *																							   *
	// *************************************************************************************************

	TIMERB = 0

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		WAIT 0

		GOSUB bike2_keys

		IF IS_CAR_IN_WATER bike_ply_bike

			FAIL_KILL_FRENZY

		ENDIF

	    IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

			GOTO mission_2p_bike_passed
					
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

			GOTO mission_2p_bike_passed

		ENDIF

		IF peds_frenzy_status = KILLFRENZY_FAILED

			b2_time_ran_out = 1

			GOTO mission_2p_bike_failed

		ENDIF
	      	
	ENDWHILE

GOTO mission_2p_bike_failed

mission_2p_bike_failed:

	IF b2_time_ran_out = 0
		
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
   
mission_2p_bike_passed:

	LVAR_INT best_time_2p

	my_test_var = my_test_var / 1000

	GET_INT_STAT P2_BIKE_RAMPAGE_BEST_TIME best_time_2p

	IF best_time_2p	< my_test_var

		SET_INT_STAT P2_BIKE_RAMPAGE_BEST_TIME my_test_var

	ENDIF

	IF bike_2p_level = 8

		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"

	ELSE

		PRINT_WITH_NUMBER_BIG ( MENU_67 ) 0 5000 1 //"Mission Passed!"

	ENDIF

	IF NOT bike_2p_level = 8

		bike_2p_level ++ 

	ENDIF

	CLEAR_WANTED_LEVEL player1

	IF NOT IS_CAR_DEAD bike_ply_bike

	 	LOCK_CAR_DOORS bike_ply_bike CARLOCK_UNLOCKED
		
	ENDIF

RETURN		

// mission cleanup
mission_2p_bike_cleanup:

	HIDE_ALL_FRONTEND_BLIPS FALSE

	b2_time_ran_out = 0

	MAKE_PLAYER_GANG_REAPPEAR

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0 

	IF NOT IS_CAR_DEAD bike_ply_bike

	 	LOCK_CAR_DOORS bike_ply_bike CARLOCK_UNLOCKED
		
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED nrg500
	MARK_MODEL_AS_NO_LONGER_NEEDED pcj600
	MARK_MODEL_AS_NO_LONGER_NEEDED fcr900
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO


	DONT_SUPPRESS_CAR_MODEL pizzaboy
	DONT_SUPPRESS_CAR_MODEL faggio
	DONT_SUPPRESS_CAR_MODEL freeway
	DONT_SUPPRESS_CAR_MODEL bike
	DONT_SUPPRESS_CAR_MODEL mtbike
	DONT_SUPPRESS_CAR_MODEL copbike
	DONT_SUPPRESS_CAR_MODEL bf400
	DONT_SUPPRESS_CAR_MODEL wayfarer
	DONT_SUPPRESS_CAR_MODEL copbike
	DONT_SUPPRESS_CAR_MODEL bf400
	DONT_SUPPRESS_CAR_MODEL wayfarer

	flag_player_on_mission = 0

	MISSION_HAS_FINISHED

RETURN

bike2_set_camera:

	SWITCH_WIDESCREEN ON

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

bike2_restore_camera:

	SET_CAMERA_BEHIND_PLAYER

	SWITCH_WIDESCREEN OFF
	
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON
	
	ENDIF

	RESTORE_CAMERA_JUMPCUT
 
RETURN

bike2_fade_out:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

bike2_fade_in:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

bike2_keys:

LVAR_INT bike_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Y

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

		GOTO mission_2p_bike_passed
				
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		IF NOT IS_CHAR_DEAD p2

			TASK_DIE p2

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

   		FAIL_KILL_FRENZY

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

/*

{---------------------------------------MENU2P---------------------------------------}

[MENU_00:MENU2P]
SUMO

[MENU_01:MENU2P]
SURVIVE

[MENU_02:MENU2P]
BOXING

[MENU_03:MENU2P]
RAMPAGE

[MENU_04:MENU2P]
TAG

[MENU_05:MENU2P]
FIXED SHOOTING

[MENU_06:MENU2P]
KILL

[MENU_07:MENU2P]
BIKE

[MENU_08:MENU2P]
CARS

[MENU_09:MENU2P]
HELICOPTERS

[MENU_10:MENU2P]
Peds : 

[MENU_11:MENU2P]
~1~

[MENU_12:MENU2P]
Start game

[MENU_13:MENU2P]
Number of players

[MENU_14:MENU2P]
CPU opponents

[MENU_15:MENU2P]
Knockouts to win

[MENU_16:MENU2P]
Random pickups

[MENU_17:MENU2P]
Continues

[MENU_18:MENU2P]
Yes

[MENU_19:MENU2P]
No

[MENU_20:MENU2P]
Number of rounds

[MENU_21:MENU2P]
Location

[MENU_22:MENU2P]
Los Angeles

[MENU_23:MENU2P]
Las Vegas

[MENU_24:MENU2P]
Mountains

[MENU_25:MENU2P]
Desert

[MENU_26:MENU2P]
Ped limit

[MENU_27:MENU2P]
Bike limit

[MENU_28:MENU2P]
Car limit

[MENU_29:MENU2P]
Time limit

[MENU_30:MENU2P]
~1~ mins

[MENU_31:MENU2P]
~1~ min

[MENU_33:MENU2P]
Peds : ~1~

[MENU_34:MENU2P]
~1~

[MENU_35:MENU2P]
Push the ~H~left analog stick~w~ left or right to select a weapon.~N~~X~ = Select.~N~~T~ = Quit.

[MENU_36:MENU2P]
Kill ~1~ peds in ~1~ minutes

[MENU_37:MENU2P]
Push the ~H~left analog stick~w~ left or right to select a vehicle.~N~~X~ = Select.~N~~T~ = Quit.

[MENU_38:MENU2P]
Destroy ~1~ bikes in ~1~ minutes

[MENU_39:MENU2P]
Destroy ~1~ cars in ~1~ minutes

[MENU_40:MENU2P]
Destroy ~1~ cars in ~1~ minutes

[MENU_41:MENU2P]
Cars : ~1~

[MENU_42:MENU2P]
Bikes : ~1~

[MENU_43:MENU2P]
Push the ~H~left analog stick~w~ left or right to select a helicopter.~N~~X~ = Select.~N~~T~ = Quit.

[MENU_44:MENU2P]
DRIVEBY

*/
  
