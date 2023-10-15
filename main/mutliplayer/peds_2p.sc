MISSION_START


// *****************************************************************************************
// *********************************** 2-Player Peds ***************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************


SCRIPT_NAME peds_2p

// Begin...
GOSUB mission_start_2p_peds

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_2p_peds_failed
	ENDIF

GOSUB mission_2p_peds_cleanup

MISSION_END

{

LVAR_INT amount_of_menu_items_MS current_menu_position_MS menu_button_delay_MS

LVAR_INT option_number_values_MS[60] amount_of_option_values_MS[10] current_option_value_MS[10] amount_of_values_per_option_MS

LVAR_INT selection_alpha_MS   
 
// menu text
LVAR_TEXT_LABEL title option_text_MS[10] option_text_values_MS[60] 

LVAR_INT temp_int_1_MS temp_int_2_MS temp_int_3_MS temp_int_4_MS my_test_var my_test_var2 my_test_var3 my_test_var4

LVAR_FLOAT temp_float_1_MS temp_float_2_MS 

LVAR_INT player1_model_VS[3] player1_button_delay_VS player1_ped_VS	player1_waiting_VS player1_chosen_ped

LVAR_FLOAT player1_ped_X player1_ped_Y player1_ped_Z player1_ped_heading

// **************************************** Variables **************************************

LVAR_INT peds_frenzy_status 

LVAR_INT peds_ply_ped peds_ply_ped2 v

LVAR_INT black_ped[5] peds_some_guy

// **************************************** Variables **************************************

LVAR_INT vehicle_models_VS[31] last_vehicle_VS 

// temp stuff
LVAR_INT temp_int_1_VS temp_int_2_VS 

LVAR_INT heli_model_select heli_limit heli_model[20]

LVAR_INT heli_colour1 heli_colour2

LVAR_INT p2_menu main_menu_peds_2p

LVAR_INT p2_time_ran_out

// ****************************************Mission Start************************************

mission_start_2p_peds:

HIDE_ALL_FRONTEND_BLIPS TRUE

CLEAR_PRINTS

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0 

SET_FADING_COLOUR 0 0 0

CLEAR_PRINTS

peds_frenzy_status = 0
peds_ply_ped       = 0
peds_ply_ped2      = 0
peds_some_guy      = 0

REPEAT 5 v

	black_ped[v] = 0

ENDREPEAT

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT MENU2P

GOSUB peds2_fade_out

WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL mp5lng
REQUEST_MODEL BFYPRO

WHILE NOT HAS_MODEL_LOADED mp5lng
OR NOT HAS_MODEL_LOADED BFYPRO

	WAIT 0

ENDWHILE

TIMERA = 0

SET_CAR_DENSITY_MULTIPLIER 0.5
  
CLEAR_AREA 2510.6331 1207.9175 9.8281 40.0 TRUE

	SWITCH peds_2p_level


		CASE 0

			REQUEST_MODEL 402

			WHILE NOT HAS_MODEL_LOADED 402
				WAIT 0
			ENDWHILE

			my_test_var2 = 15 // Peds		

			my_test_var3 = 3 // Minutes

			my_test_var = 180000

			CREATE_CAR 402 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 1

			REQUEST_MODEL 402

			WHILE NOT HAS_MODEL_LOADED 402
				WAIT 0
			ENDWHILE

			my_test_var2 = 15  // Peds			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 402 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 2 

			REQUEST_MODEL 402

			WHILE NOT HAS_MODEL_LOADED 402
				WAIT 0
			ENDWHILE

			my_test_var2 = 15  // Peds		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 402 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 3

			REQUEST_MODEL 411

			WHILE NOT HAS_MODEL_LOADED 411
				WAIT 0
			ENDWHILE

			my_test_var2 = 20  // Peds		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 411 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 4

			REQUEST_MODEL 411

			WHILE NOT HAS_MODEL_LOADED 411
				WAIT 0
			ENDWHILE

			my_test_var2 = 20 // Peds			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 411 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 5 

			REQUEST_MODEL 411

			WHILE NOT HAS_MODEL_LOADED 411
				WAIT 0
			ENDWHILE

			my_test_var2 = 20 // Peds		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 411 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 6

			REQUEST_MODEL 477

			WHILE NOT HAS_MODEL_LOADED 477
				WAIT 0
			ENDWHILE

			my_test_var2 = 25 // Peds		

			my_test_var3 = 3  // Minutes

			my_test_var = 180000

			CREATE_CAR 477 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 7

			REQUEST_MODEL 477

			WHILE NOT HAS_MODEL_LOADED 477
				WAIT 0
			ENDWHILE

			my_test_var2 = 25 // Peds			

			my_test_var3 = 2  // Minutes

			my_test_var = 120000

			CREATE_CAR 477 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

		CASE 8 

			REQUEST_MODEL 477

			WHILE NOT HAS_MODEL_LOADED 477
				WAIT 0
			ENDWHILE

			my_test_var2 = 25 // Peds		

			my_test_var3 = 1  // Minutes

			my_test_var = 60000

			CREATE_CAR 477 2510.6331 1207.9175 9.8281 peds_ply_ped

		BREAK

	ENDSWITCH

SET_CAR_HEADING peds_ply_ped 271.2962   
 
LOCK_CAR_DOORS peds_ply_ped CARLOCK_LOCKED_PLAYER_INSIDE

CREATE_PLAYER 1 2510.6331 1207.9175 9.8281  player2

SET_PLAYER_MODEL player2 BFYPRO

GET_PLAYER_CHAR player2 p2

IF NOT IS_CHAR_DEAD scplayer

SET_CHAR_COORDINATES scplayer 2510.6331 1207.9175 9.8281  

WARP_CHAR_INTO_CAR scplayer peds_ply_ped

WARP_CHAR_INTO_CAR_AS_PASSENGER p2 peds_ply_ped 0

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

 	GOSUB peds2_set_camera 

	MAKE_PLAYER_GANG_DISAPPEAR

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

	SET_FIXED_CAMERA_POSITION 2508.0933 1205.5894 11.1785 0.0 0.0 0.0 // Bike from front
   	POINT_CAMERA_AT_POINT 2509.0173 1205.9573 11.0761 JUMP_CUT

	GOSUB peds2_fade_in

	IF NOT my_test_var3 = 1

		PRINT_WITH_2_NUMBERS_NOW MENU_46 my_test_var2 my_test_var3 5000 1 // Shoot ~1~ peds in ~1~ minutes

	ELSE

		PRINT_WITH_2_NUMBERS_NOW MENU_62 my_test_var2 my_test_var3 5000 1 // Shoot ~1~ peds in ~1~ minute

	ENDIF

	WAIT 5000

	GOSUB peds2_restore_camera

	LVAR_INT a_level_display	

	a_level_display = peds_2p_level + 1

	//PRINT_WITH_NUMBER_BIG MENU_45 a_level_display 3000 1 // LEVEL ~1~

   	START_KILL_FRENZY DUMMY WEAPONTYPE_MP5 my_test_var my_test_var2 -1 -1 -1 -1 FALSE

	SET_PED_DENSITY_MULTIPLIER 2.0

	// *************************************************************************************************
	// *																							   *
	// *	                                    MAIN LOOP               							   *
	// *																							   *
	// *************************************************************************************************

	TIMERB = 0

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2
	AND NOT IS_CAR_DEAD peds_ply_ped

		WAIT 0

		GOSUB peds2_keys

		IF IS_CAR_IN_WATER peds_ply_ped

			FAIL_KILL_FRENZY

		ENDIF

	    READ_KILL_FRENZY_STATUS peds_frenzy_status

	    IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S

	    	peds_frenzy_status = KILLFRENZY_PASSED	  

		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F		   

	    	peds_frenzy_status = KILLFRENZY_FAILED	  

		ENDIF

	    IF peds_frenzy_status = KILLFRENZY_PASSED

			my_test_var = my_test_var - TIMERB

			GOTO mission_2p_peds_passed

		ENDIF
		IF peds_frenzy_status = KILLFRENZY_FAILED

			p2_time_ran_out = 1

			GOTO mission_2p_peds_failed

		ENDIF
	      	
	ENDWHILE

GOTO mission_2p_peds_failed

mission_2p_peds_failed:

	IF p2_time_ran_out = 0

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
   
mission_2p_peds_passed:

	LVAR_INT best_time_2p

	my_test_var = my_test_var / 1000

	GET_INT_STAT P2_PEDS_RAMPAGE_BEST_TIME best_time_2p

	IF best_time_2p	< my_test_var

		SET_INT_STAT P2_PEDS_RAMPAGE_BEST_TIME my_test_var

	ENDIF

	IF NOT peds_2p_level = 8

		peds_2p_level ++

	ENDIF

	IF peds_2p_level = 8

		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"

	ELSE

		PRINT_WITH_NUMBER_BIG ( MENU_67 ) 0 5000 1 //"Mission Passed!"

	ENDIF

	CLEAR_WANTED_LEVEL player1

	IF NOT IS_CAR_DEAD peds_ply_ped

	 	LOCK_CAR_DOORS peds_ply_ped CARLOCK_UNLOCKED
		
	ENDIF

RETURN		

// mission cleanup
mission_2p_peds_cleanup:

	HIDE_ALL_FRONTEND_BLIPS FALSE

	p2_time_ran_out = 0

	MAKE_PLAYER_GANG_REAPPEAR

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0 

	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED 402
	MARK_MODEL_AS_NO_LONGER_NEEDED 411
	MARK_MODEL_AS_NO_LONGER_NEEDED 477
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO

	IF NOT IS_CAR_DEAD peds_ply_ped

	 	LOCK_CAR_DOORS peds_ply_ped CARLOCK_UNLOCKED
		
	ENDIF

	flag_player_on_mission = 0

	MISSION_HAS_FINISHED

RETURN

peds2_set_camera:

	SWITCH_WIDESCREEN ON

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

peds2_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	
	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON
	
	ENDIF

	RESTORE_CAMERA_JUMPCUT
 
RETURN

peds2_fade_out:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

peds2_fade_in:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

peds2_keys:

LVAR_INT peds_dummy_ped

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
	
		GOTO mission_2p_peds_passed	

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

[MENU_45:MENU2P]
LEVEL ~1~

[MENU_46:MENU2P]
Shoot ~1~ peds in ~1~ minutes


*/
  
