MISSION_START

// *****************************************************************************************
// ********************************** 2-Player Rampage *************************************
// *****************************************************************************************


SCRIPT_NAME ram_2p

// Begin...
GOSUB mission_start_2p_ram

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_2p_ram_failed
	ENDIF

GOSUB mission_2p_ram_cleanup

MISSION_END

{

LVAR_INT amount_of_menu_items_MS current_menu_position_MS menu_button_delay_MS

LVAR_INT option_number_values_MS[60] amount_of_option_values_MS[10] current_option_value_MS[10] amount_of_values_per_option_MS

LVAR_INT selection_alpha_MS main_menu_ram_2p   
 
// menu text
LVAR_TEXT_LABEL title option_text_MS[10] option_text_values_MS[60] 

LVAR_INT temp_int_1_MS temp_int_2_MS temp_int_3_MS temp_int_4_MS my_test_var my_test_var2 my_test_var3 my_test_var4

LVAR_FLOAT temp_float_1_MS temp_float_2_MS 

LVAR_INT player1_model_VS[3] player1_button_delay_VS player1_car_VS	player1_waiting_VS player1_chosen_car
LVAR_FLOAT player1_car_X player1_car_Y player1_car_Z player1_car_heading


// **************************************** Variables **************************************

LVAR_INT peds_frenzy_status ram_ply1_kills ram_ply2_kills 

LVAR_INT ram_ply_ram ram_ply_ram2 v

LVAR_INT black_car[5] ram_some_guy

// **************************************** Variables **************************************
LVAR_INT vehicle_models_VS[31] last_vehicle_VS 

// temp stuff
LVAR_INT temp_int_1_VS temp_int_2_VS r2_number sub_menu_ram_2p

LVAR_INT heli_model_select heli_model_select2 heli_limit heli_model[20]

LVAR_INT heli_colour1 heli_colour2 r2_menu r2_overall_menu r2_my_gun

LVAR_INT r2_time_ran_out

// ****************************************Mission Start************************************

mission_start_2p_ram:

HIDE_ALL_FRONTEND_BLIPS TRUE

CLEAR_PRINTS

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0 

SET_FADING_COLOUR 0 0 0

peds_frenzy_status = 0
ram_ply_ram        = 0
ram_ply_ram2       = 0
ram_some_guy       = 0

REPEAT 5 v

	black_car[v] = 0    

ENDREPEAT

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT MENU2P

GOTO ram_skip_it

ram_skip_it:

GOSUB ram2_fade_out

WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL micro_uzi
REQUEST_MODEL mp5lng
REQUEST_MODEL colt45
REQUEST_MODEL BFYPRO

WHILE NOT HAS_MODEL_LOADED micro_uzi
OR NOT HAS_MODEL_LOADED mp5lng
OR NOT HAS_MODEL_LOADED colt45
OR NOT HAS_MODEL_LOADED BFYPRO

	WAIT 0

ENDWHILE

TIMERA = 0

SET_PED_DENSITY_MULTIPLIER 2.0

//SET_CAR_DENSITY_MULTIPLIER 1.0

CLEAR_AREA 1478.8678 -1655.8528 13.0469 40.0 TRUE

CREATE_PLAYER 1 1478.8678 -1655.8528 13.0469 player2

GET_PLAYER_CHAR player2 p2

SET_PLAYER_MODEL player2 BFYPRO

SET_CHAR_HEADING p2 237.1571 

IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_COORDINATES scplayer 1478.9728 -1657.8544 13.0469 

	SET_CHAR_HEADING scplayer 237.1571   

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER2

	SET_PLAYER_PLAYER_TARGETTING FALSE

	SET_CHAR_NEVER_TARGETTED scplayer TRUE

	SET_CHAR_NEVER_TARGETTED p2 TRUE 

	SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT

	SET_PLAYERS_CAN_BE_IN_SEPARATE_CARS FALSE
	  
	LIMIT_TWO_PLAYER_DISTANCE 20.0

	DISABLE_CHAR_SPEECH p2 TRUE

ENDIF

	SWITCH ram_2p_level

		CASE 0

			my_test_var2 = 15 // Peds	

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			r2_my_gun = 0

		BREAK

		CASE 1

			my_test_var2 = 15 // Peds	

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			r2_my_gun = 0

		BREAK

		CASE 2 

			my_test_var2 = 15 // Peds	

			my_test_var3 = 1  // Minute

			my_test_var =  90000

			r2_my_gun = 0

		BREAK

		CASE 3

			my_test_var2 = 18 // Peds	

			my_test_var3 = 3 // Minutes

			my_test_var = 180000

			r2_my_gun = 1

		BREAK

		CASE 4

			my_test_var2 = 18 // Peds	

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			r2_my_gun = 1

		BREAK

		CASE 5 

			my_test_var2 = 18 // Peds	

			my_test_var3 = 1  // Minute

			my_test_var =  90000

			r2_my_gun = 1

		BREAK

		CASE 6

			my_test_var2 = 20 // Peds	

			my_test_var3 = 3 // Minutes

			my_test_var = 180000

			r2_my_gun = 2

		BREAK

		CASE 7

			my_test_var2 = 20 // Peds	

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			r2_my_gun = 2

		BREAK
																								    
		CASE 8 

			my_test_var2 = 20 // Peds	

			my_test_var3 = 1  // Minute

			my_test_var =  90000

			r2_my_gun = 2

		BREAK

	ENDSWITCH
	
	GOSUB ram2_set_camera

	MAKE_PLAYER_GANG_DISAPPEAR

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

	SET_FIXED_CAMERA_POSITION 1484.0725 -1659.5990 15.6832 0.0 0.0 0.0 // Bike from front
   	POINT_CAMERA_AT_POINT 1483.2703 -1659.0812 15.3862 JUMP_CUT

	GOSUB ram2_fade_in

	IF my_test_var3 = 1

		PRINT_WITH_3_NUMBERS MENU_65 my_test_var2 my_test_var3 30 5000 1 // Kill ~1~ peds in ~1~ minute and ~1~ seconds.

	ELSE

	  	PRINT_WITH_2_NUMBERS_NOW MENU_36 my_test_var2 my_test_var3 5000 1 // Kill ~1~ peds in ~1~ minutes

	ENDIF

	WAIT 5000

	GOSUB ram2_restore_camera

	LVAR_INT ram2_level_display	

	ram2_level_display = ram_2p_level + 1

	CLEAR_PRINTS

	SWITCH r2_my_gun

		CASE 0

			START_KILL_FRENZY DUMMY WEAPONTYPE_MP5 my_test_var my_test_var2 -1 -1 -1 -1 FALSE

		BREAK

		CASE 1

			START_KILL_FRENZY DUMMY WEAPONTYPE_MICRO_UZI my_test_var my_test_var2 -1 -1 -1 -1 FALSE

		BREAK

		CASE 2

			START_KILL_FRENZY DUMMY WEAPONTYPE_PISTOL my_test_var my_test_var2 -1 -1 -1 -1 FALSE

		BREAK

	ENDSWITCH

	TIMERB = 0

	// *************************************************************************************************
	// *																							   *
	// *	                                    MAIN LOOP               							   *
	// *																							   *
	// *************************************************************************************************

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		WAIT 0

		GOSUB ram2_keys

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

			IF HAS_CHAR_BEEN_ARRESTED scplayer
			OR HAS_CHAR_BEEN_ARRESTED p2

				FAIL_KILL_FRENZY

			ENDIF

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

			GOTO mission_2p_ram_passed

		ENDIF

		IF peds_frenzy_status = KILLFRENZY_FAILED

			r2_time_ran_out = 1

			GOTO mission_2p_ram_failed

		ENDIF
	      	
	ENDWHILE

GOTO mission_2p_ram_failed

mission_2p_ram_failed:

	IF r2_time_ran_out = 0

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
   
mission_2p_ram_passed:

	LVAR_INT best_time_2p

	my_test_var = my_test_var / 1000

	GET_INT_STAT P2_RAMPAGE_BEST_TIME best_time_2p

	IF best_time_2p	< my_test_var

		SET_INT_STAT P2_RAMPAGE_BEST_TIME my_test_var

	ENDIF

	IF ram_2p_level = 8

		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"

	ELSE

		PRINT_WITH_NUMBER_BIG ( MENU_67 ) 0 5000 1 //"Mission Passed!"

	ENDIF	

	CLEAR_WANTED_LEVEL player1

	IF NOT ram_2p_level = 8

		ram_2p_level ++

	ENDIF

RETURN		

// mission cleanup
mission_2p_ram_cleanup:

	HIDE_ALL_FRONTEND_BLIPS FALSE

	r2_time_ran_out = 0

	MAKE_PLAYER_GANG_REAPPEAR

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0 

	SET_CAR_DENSITY_MULTIPLIER 1.0

	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED colt45
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO

	flag_player_on_mission = 0

	CLEAR_WANTED_LEVEL player1

	MISSION_HAS_FINISHED

RETURN

ram2_set_camera:

	SWITCH_WIDESCREEN ON

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

ram2_restore_camera:

 //	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
		
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON

	ENDIF

	RESTORE_CAMERA_JUMPCUT 

  	SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT
 
RETURN

ram2_fade_out:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

ram2_fade_in:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

ram2_keys:

LVAR_INT ram_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

		IF NOT IS_CHAR_DEAD p2

			TASK_DIE p2

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z

		IF NOT IS_CHAR_DEAD scplayer

			TASK_DIE scplayer

		ENDIF
		
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

		VIEW_INTEGER_VARIABLE ram_2p_level ram_2p_level

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

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

[menu_09:MENU2P]
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

*/  
