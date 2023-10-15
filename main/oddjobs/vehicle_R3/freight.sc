MISSION_START

// *****************************************************************************************
// *********************************** Freight Train ***************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

SCRIPT_NAME freight

// Begin...
GOSUB mission_start_freight

	IF HAS_DEATHARREST_BEEN_EXECUTED

		GOSUB mission_freight_failed

	ENDIF

GOSUB mission_freight_cleanup

	IF flag_freight_passed_1stime = 1
	START_NEW_SCRIPT text_after_freight
		flag_freight_passed_1stime = 2
	ENDIF

MISSION_END

{

VAR_INT freight_remaining flag_freight_passed_1stime flag_freight_passed_0stime 

LVAR_INT freight_time_remaining[5] freight_time_remaining_reverse[5]

LVAR_INT freight_train_engine 

LVAR_FLOAT ft_station_X[20] ft_station_Y[20] ft_station_Z[20] // The stations

LVAR_INT freight_station_blip[20] freight_start	ft_counter_displayed

LVAR_INT ft_addition ft_display_addition v ft_stations_done ft_test_train

LVAR_INT ft_potential ft_direction 

LVAR_FLOAT ft_distance_to_station ft_distance_temp

LVAR_FLOAT ft_distance_float ft_train_speed

VAR_INT ft_distance ft_speed ft_help_been_displayed	freight_player_location

// ****************************************Mission Start************************************

mission_start_freight:

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0

ft_distance_temp = 999999.00

flag_player_on_mission = 1
flag_player_on_freight_mission = 1

ft_stations_done = 0
ft_counter_displayed = 0
freight_start = 0

IF ft_train_level = 0

	ft_train_level = 1 

ENDIF

// L.A station
ft_station_X[0] = 1698.8257 
ft_station_Y[0] = -1953.7498
ft_station_Z[0] = 12.5469
freight_time_remaining[0] = 150000
freight_time_remaining_reverse[0] = 60000

// Richman station
ft_station_X[1] = 789.4952 
ft_station_Y[1] = -1343.7961
ft_station_Z[1] = -2.5794
freight_time_remaining[1] = 60000
freight_time_remaining_reverse[1] = 140000

 // Near SF airport station
ft_station_X[2] = -1943.2415 
ft_station_Y[2] = 159.5850
ft_station_Z[2] = 24.7186
freight_time_remaining[2] = 140000
freight_time_remaining_reverse[2] = 150000

// Vegas station
ft_station_X[3] = 1463.3593 
ft_station_Y[3] = 2632.2495
ft_station_Z[3] = 9.8203
freight_time_remaining[3] = 150000
freight_time_remaining_reverse[3] = 105000 

// Outside Vegas L.A-side station
ft_station_X[4] = 2864.6228 
ft_station_Y[4] = 1248.2665
ft_station_Z[4] = 9.8203 
freight_time_remaining[4] = 105000
freight_time_remaining_reverse[4] = 150000

IF ft_train_level = 1

	ft_addition = 15000

ENDIF
IF ft_train_level = 2

	ft_addition = 10000

ENDIF
IF ft_train_level = 3

	ft_addition = 0

ENDIF

REPEAT 5 v

	freight_time_remaining[v] = freight_time_remaining[v] + ft_addition

	freight_time_remaining_reverse[v] = freight_time_remaining_reverse[v] + ft_addition

ENDREPEAT

SET_FADING_COLOUR 0 0 0

flag_player_on_mission = 1

IF flag_freight_passed_1stime = 0
	REGISTER_MISSION_GIVEN
ENDIF

LOAD_MISSION_TEXT FTRAIN

IF NOT IS_CHAR_DEAD scplayer
	IF IS_CHAR_IN_ANY_CAR scplayer

		STORE_CAR_CHAR_IS_IN scplayer car

  //	CLOSE_ALL_CAR_DOORS car	

	ENDIF
ENDIF

 // ***************************************************************************************************************

IF flag_freight_passed_1stime = 0

	PRINT_WITH_NUMBER_BIG ( FREI_04 ) ft_train_level 5000 4 // Freight Mission Level ~1~

ELSE

	PRINT_BIG ( FREI_X ) 5000 4 // Freight Mission

ENDIF

WAIT 0


// *******************************************************************************************
//
//                                  Find where the player starts
//
// *******************************************************************************************

IF NOT IS_CHAR_DEAD scplayer 
	GET_CHAR_COORDINATES scplayer x y z
ENDIF

// L.A station
GET_DISTANCE_BETWEEN_COORDS_2D x y 1698.8257 -1953.7498 ft_distance_to_station // (LA EAST)
IF ft_distance_to_station < ft_distance_temp
	ft_distance_temp = ft_distance_to_station
	freight_start = 0
ENDIF

// Richman station
GET_DISTANCE_BETWEEN_COORDS_2D x y 789.4952 -1343.7961 ft_distance_to_station // (LA EAST)
IF ft_distance_to_station < ft_distance_temp
	ft_distance_temp = ft_distance_to_station
	freight_start = 1
ENDIF		    		    

// Near SF airport station
GET_DISTANCE_BETWEEN_COORDS_2D x y -1943.2415 159.5850 ft_distance_to_station // (LA EAST)
IF ft_distance_to_station < ft_distance_temp
	ft_distance_temp = ft_distance_to_station
	freight_start = 2
ENDIF

// Vegas station
GET_DISTANCE_BETWEEN_COORDS_2D x y 1463.3593 2632.2495 ft_distance_to_station // (LA EAST)
IF ft_distance_to_station < ft_distance_temp
	ft_distance_temp = ft_distance_to_station
	freight_start = 3
ENDIF

// Outside Vegas L.A-side station
GET_DISTANCE_BETWEEN_COORDS_2D x y 2864.6228 1248.2665 ft_distance_to_station // (LA EAST)
IF ft_distance_to_station < ft_distance_temp
	ft_distance_temp = ft_distance_to_station
	freight_start = 4
ENDIF

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL 590 // Big Carriage
REQUEST_MODEL 570 // Silver Carriage
REQUEST_MODEL 569 // Flatbed
REQUEST_MODEL 538 // Train Engine
REQUEST_MODEL 537 // Train Engine

WHILE NOT HAS_MODEL_LOADED 590
OR NOT HAS_MODEL_LOADED 570
OR NOT HAS_MODEL_LOADED 569
OR NOT HAS_MODEL_LOADED 538
OR NOT HAS_MODEL_LOADED 537

	WAIT 0

ENDWHILE

TIMERA = 0

TIMERB = 0

DELETE_ALL_TRAINS

SWITCH_RANDOM_TRAINS OFF

IF NOT IS_CHAR_DEAD scplayer
	IF IS_CHAR_IN_ANY_CAR scplayer

		STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car 

	ENDIF
ENDIF

IF NOT IS_CAR_DEAD car
	IF FIND_TRAIN_DIRECTION car

		freight_start ++

		ft_direction = 0

	ELSE

		freight_start --

		ft_direction = 1

	ENDIF
ENDIF

IF freight_start = 5

	freight_start = 0

ENDIF

IF freight_start = -1

	freight_start = 4

ENDIF


GOSUB freight_fade_in   

ADD_BLIP_FOR_COORD ft_station_X[freight_start] ft_station_Y[freight_start] ft_station_Z[freight_start] freight_station_blip[freight_start]

IF ft_direction = 0

	freight_remaining = freight_time_remaining[freight_start] 

ELSE

	freight_remaining = freight_time_remaining_reverse[freight_start] 

ENDIF

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
		GOTO ft_skip_a_station
	ENDIF

	IF IS_CHAR_IN_MODEL scplayer 538
	OR IS_CHAR_IN_MODEL scplayer 537

		STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car 

		IF NOT IS_CAR_UPRIGHT car

			CLEAR_PRINTS

			CLEAR_HELP

			GOTO mission_freight_cancel
			
		ENDIF

	ELSE

		CLEAR_PRINTS

		CLEAR_HELP

	  	GOTO mission_freight_cancel

	ENDIF

	GET_CAR_SPEED car ft_train_speed

    
	ft_speed =# ft_train_speed

	IF TIMERB > 3000    
	AND ft_counter_displayed = 0
	
		ft_counter_displayed = 1

		CLEAR_PRINTS

	   	PRINT_HELP ( FREI_03 ) // ~s~Deliver the cargo to the stations within the time limit!

	ENDIF

	IF TIMERB > 8000
	AND ft_counter_displayed = 1	

		ft_counter_displayed = 2

 		CLEAR_PRINTS

		CLEAR_HELP

		IF ft_help_been_displayed = 0

			PRINT_HELP ( FREI_12 ) // ~s~If you go too fast you will derail

		ELSE
			
			TIMERB = 18000

			ft_counter_displayed = 3

		ENDIF

	ENDIF

	IF TIMERB > 12000
	AND ft_counter_displayed = 2	

		ft_counter_displayed = 3

 		CLEAR_PRINTS

		CLEAR_HELP

		IF ft_help_been_displayed = 0

			PRINT_HELP ( FREI_13 ) // ~s~Brake early or you will overshoot the station
		
		ELSE
			
			TIMERB = 18000

			ft_counter_displayed = 3

		ENDIF

	ENDIF

	IF TIMERB > 16000
	AND ft_counter_displayed = 3
		
		// ******************************** Calculate the first station time ********************************************

	 	IF NOT IS_CHAR_DEAD scplayer

			GET_CHAR_COORDINATES scplayer x y z

		ENDIF

		ft_distance_float = 0.0

		freight_remaining = 0

		GET_DISTANCE_BETWEEN_COORDS_2D x y ft_station_X[freight_start] ft_station_Y[freight_start] ft_distance_float
				
		ft_distance =# ft_distance_float

		IF ft_distance_float > 3000.0000

			ft_distance_float = ft_distance_float / 0.021

		ELSE

			ft_distance_float = ft_distance_float / 0.019

		ENDIF

		IF ft_distance_float < 10000.0 
			ft_distance_float = 10000.0
		ENDIF

		freight_remaining =# ft_distance_float
	
		IF ft_train_level = 1 

			ft_addition = 15000

		ENDIF

		IF ft_train_level = 2

		//	ft_addition = 10000
			ft_addition = 0

		ENDIF

		IF ft_train_level = 3

			ft_addition = 0

		ENDIF

		freight_remaining = freight_remaining + ft_addition

		// **************************************************************************************************************

		ft_counter_displayed = 4

		ft_help_been_displayed = 1

		DISPLAY_ONSCREEN_TIMER_WITH_STRING freight_remaining TIMER_DOWN	FREI_14	// Time

		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING ft_distance COUNTER_DISPLAY_NUMBER 1 FREI_10 // power :

		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING ft_speed COUNTER_DISPLAY_NUMBER 2 FREI_11 // level :

 		CLEAR_PRINTS

		CLEAR_HELP

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer

		GET_CHAR_COORDINATES scplayer x y z

	ENDIF
	
	GET_DISTANCE_BETWEEN_COORDS_2D x y ft_station_X[freight_start] ft_station_Y[freight_start] ft_distance_float

	ft_distance =# ft_distance_float  

	IF HAS_TRAIN_DERAILED car    

		GOTO mission_freight_failed

	ENDIF
	
	IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
		CLEAR_PRINTS
		CLEAR_HELP
		GOTO mission_freight_cancel
	ENDIF

    IF freight_remaining <= 0
		GOTO mission_freight_failed
	ENDIF 

	IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer ft_station_X[freight_start] ft_station_Y[freight_start] ft_station_Z[freight_start] 10.0 10.0 10.0 TRUE
	
		IF IS_CHAR_IN_MODEL scplayer 538
		OR IS_CHAR_IN_MODEL scplayer 537

			ft_skip_a_station:

			IF DOES_BLIP_EXIST freight_station_blip[freight_start]

				REMOVE_BLIP freight_station_blip[freight_start]
			
			ENDIF	

			ft_stations_done ++

			ft_addition = freight_remaining

			CLEAR_ONSCREEN_TIMER freight_remaining

			CLEAR_ONSCREEN_COUNTER ft_distance

			CLEAR_ONSCREEN_COUNTER ft_speed

			IF ft_stations_done = 5

				GOTO mission_freight_passed

			ENDIF

			IF ft_direction = 0

				freight_start ++

			ELSE

				freight_start --

			ENDIF

			IF freight_start = 5

				freight_start = 0

			ENDIF

			IF freight_start = -1

				freight_start = 4

			ENDIF

			IF ft_addition > 15000

				ft_addition = 15000

			ENDIF

			ft_display_addition = ft_addition / 100
			
			ft_display_addition = ft_display_addition * ft_train_level		

		  	PRINT_WITH_NUMBER_BIG FREI_07 ft_display_addition 3000 1  // CARGO DELIVERED! $~1~
			
			ADD_SCORE player1 ft_display_addition

			TIMERA = 0

			ft_potential = 1

		ENDIF

	ENDIF

	IF ft_potential = 1
	AND TIMERA > 6000

		ft_display_addition = ft_addition / 1000

		PRINT_WITH_NUMBER_BIG FREI_05 ft_display_addition 4000 6  // +~1~ Seconds

		freight_remaining = 0

		IF ft_direction = 0

			freight_remaining = freight_time_remaining[freight_start] + ft_addition

		ELSE

			freight_remaining = freight_time_remaining_reverse[freight_start] + ft_addition

		ENDIF

		CLEAR_ONSCREEN_TIMER freight_remaining

		CLEAR_ONSCREEN_COUNTER ft_distance

		CLEAR_ONSCREEN_COUNTER ft_speed

		DISPLAY_ONSCREEN_TIMER_WITH_STRING freight_remaining TIMER_DOWN	FREI_14	// Time

		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING ft_distance COUNTER_DISPLAY_NUMBER 1 FREI_10 // power :

		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING ft_speed COUNTER_DISPLAY_NUMBER 2 FREI_11 // level :

		ADD_BLIP_FOR_COORD ft_station_X[freight_start] ft_station_Y[freight_start] ft_station_Z[freight_start] freight_station_blip[freight_start]

		ft_potential = 0

	ENDIF
		
ENDWHILE

GOTO mission_freight_failed

mission_freight_cancel:

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	CLEAR_PRINTS
	CLEAR_HELP

	PRINT_BIG ( FREI_02 ) 5000 1 // ~r~Freight mission cancelled!
	   
	CLEAR_ONSCREEN_TIMER freight_remaining
	CLEAR_ONSCREEN_COUNTER ft_distance
	CLEAR_ONSCREEN_COUNTER ft_speed

	WAIT 2000

RETURN

mission_freight_failed:

	IF DOES_BLIP_EXIST freight_station_blip[freight_start]

		REMOVE_BLIP freight_station_blip[freight_start]
	
	ENDIF

	CLEAR_ONSCREEN_TIMER freight_remaining
	CLEAR_ONSCREEN_COUNTER ft_distance
	CLEAR_ONSCREEN_COUNTER ft_speed
	
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	CLEAR_HELP

	CLEAR_PRINTS

	PRINT_BIG ( M_FAIL ) 5000 1

	PRINT_NOW ( FREI_06 ) 4000 1 //	~r~You missed your delivery!

RETURN
   
mission_freight_passed:

	IF ft_train_level = 2

		IF flag_freight_passed_1stime = 0

			PRINT_WITH_NUMBER_BIG ( M_PASS ) 50000 5000 1 

			ADD_SCORE player1 50000

			PLAY_MISSION_PASSED_TUNE 2

		ENDIF

		IF flag_freight_passed_1stime = 2

			PRINT_WITH_NUMBER_BIG ( M_PASS ) 10000 5000 1 

			ADD_SCORE player1 10000

			PLAY_MISSION_PASSED_TUNE 2

		ENDIF

	ELSE

		PRINT_WITH_NUMBER_BIG ( FREI_15 ) ft_train_level 5000 1	// Level ~1~ Complete

	ENDIF

	IF flag_freight_passed_0stime = 0
	AND ft_train_level = 1

	    REGISTER_ODDJOB_MISSION_PASSED

        flag_freight_passed_0stime = 1

	ENDIF

	IF flag_freight_passed_1stime = 0
	AND ft_train_level = 2

	    REGISTER_ODDJOB_MISSION_PASSED

        PLAYER_MADE_PROGRESS 1

        flag_freight_passed_1stime = 1

	ENDIF

	CLEAR_ONSCREEN_TIMER freight_remaining
	CLEAR_ONSCREEN_COUNTER ft_distance
	CLEAR_ONSCREEN_COUNTER ft_speed

	CLEAR_WANTED_LEVEL player1

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	PLAY_MISSION_PASSED_TUNE 1

	IF NOT ft_train_level = 2

		ft_train_level ++

	ENDIF

RETURN		

// mission cleanup
mission_freight_cleanup:

	IF DOES_BLIP_EXIST freight_station_blip[freight_start]

		REMOVE_BLIP freight_station_blip[freight_start]
	
	ENDIF	

	MARK_MODEL_AS_NO_LONGER_NEEDED 590
	MARK_MODEL_AS_NO_LONGER_NEEDED 570
	MARK_MODEL_AS_NO_LONGER_NEEDED 569
	MARK_MODEL_AS_NO_LONGER_NEEDED 538
	MARK_MODEL_AS_NO_LONGER_NEEDED 537

	flag_player_on_mission = 0
	flag_player_on_freight_mission = 0

	CLEAR_WANTED_LEVEL player1

	CLEAR_ONSCREEN_COUNTER ft_distance

	CLEAR_ONSCREEN_COUNTER ft_speed

	CLEAR_ONSCREEN_TIMER freight_remaining

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0

	MISSION_HAS_FINISHED

RETURN

freight_set_camera:

	SWITCH_WIDESCREEN ON

	SET_PLAYER_CONTROL player1 OFF

RETURN

freight_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF

	SET_PLAYER_CONTROL player1 ON

	RESTORE_CAMERA_JUMPCUT
 
RETURN

freight_fade_out:
	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

freight_fade_in:
	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

freight_keys:

LVAR_INT freight_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
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

