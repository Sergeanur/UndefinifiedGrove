MISSION_START

// **************************************************************************************************
//
// Farlie Mission 1: Airport Pickup
//
// **************************************************************************************************

SCRIPT_NAME FARLIE4

// Begin...
GOSUB mission_start_farlie4										   

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_farlie4
	ENDIF

GOSUB mission_cleanup_farlie4

MISSION_END
 
{

// **************************************************************************************************
// 
//
//
// **************************************************************************************************

// Integers

VAR_INT f4_rnd f4_car_damage

LVAR_INT f4_objective_car f4_objective_blip v 

LVAR_INT f4_home_blip f4_sequence_task 
  
LVAR_INT f4_cp f4_inside_car f4_outside

LVAR_INT f4_garage_open

LVAR_INT f4_heal 

LVAR_INT f4_roll_out_1

LVAR_INT f4_roll_out_2

LVAR_INT f4_decision

LVAR_INT f4_ply_ride

LVAR_INT ReturnStatus

// Arrays

LVAR_INT f4_mule[10] 

LVAR_INT f4_van_man[10] 

LVAR_INT f4_entrance[10]

LVAR_INT f4_trigger[20]

LVAR_INT f4_road_block[10]

LVAR_INT f4_motorbike[10]

LVAR_INT f4_biker[10]

LVAR_INT f4_bike_blip[10] 

LVAR_INT f4_audio

LVAR_INT f4_playing

LVAR_INT f4_car_has_been_created

LVAR_INT f4_mule_seq[4]

LVAR_INT f4_decision_empty

LVAR_INT f4_driver[10]

LVAR_INT f4_trigger_car[5]

LVAR_INT f4_car[5] f4_car_blip[5]

LVAR_INT f4_ply_in_car[5]

LVAR_TEXT_LABEL f4_print
// Floats

LVAR_FLOAT f4_temp_X f4_temp_Y f4_temp_Z

// **************************************************************************************************
// *																								*
// *                                    Start Mission												*
// *																								*
// **************************************************************************************************

mission_start_farlie4:

// Cutscene

LOAD_MISSION_TEXT FARLIE4

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 1

LOAD_CUTSCENE FARL_4A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

SET_AREA_VISIBLE 0

// *****************************************************************************************

IF NOT IS_CHAR_DEAD scplayer

	LOAD_SCENE -2154.9983 645.5436 51.3516

	SET_CHAR_COORDINATES scplayer -2154.9983 645.5436 51.3516 
	
	SET_CHAR_HEADING scplayer 271.1130 

	SET_CAMERA_BEHIND_PLAYER

ENDIF

// *****************************************************************************************

SWITCH_WIDESCREEN OFF
	  
SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_IN

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

SWITCH_ROADS_OFF -1478.2944 -65.4014 0.0000 -1395.4229 2.7054 10.0000 

// *****************************************************************************************
																			 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION1

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH f4_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY f4_decision_empty

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE f4_decision_empty EVENT_PED_ENTERED_MY_VEHICLE TASK_COMPLEX_LEAVE_CAR 0.0 0.0 100.0 0.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE f4_decision EVENT_PED_ENTERED_MY_VEHICLE TASK_COMPLEX_LEAVE_CAR 0.0 0.0 100.0 0.0 TRUE TRUE

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

ADD_BLIP_FOR_COORD -1742.2732 1422.6310 6.1842 f4_home_blip

CHANGE_BLIP_DISPLAY f4_home_blip NEITHER

ADD_BLIP_FOR_COORD -1409.9000 -303.4000 4.5000 f4_outside

CHANGE_GARAGE_TYPE fdorsfe GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE

REQUEST_MODEL MICRO_UZI

WHILE NOT HAS_MODEL_LOADED MICRO_UZI
	WAIT 0
ENDWHILE

IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_MICRO_UZI
AND NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_MP5

	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 150

	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MICRO_UZI

ENDIF

// ****************************************************************************************************
// 
//                                         MAIN LOOP
//
// ****************************************************************************************************

TIMERA = 0

f4_playing = 2

PRINT_NOW ( F4_T0 ) 9000 1 // ~s~Pick up the car from the airport ~y~car park~s~!

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
													  
		GOTO mission_passed_farlie4 

	ENDIF

	GOSUB f4_keys

	GOSUB f4_play_sample

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1409.9000 -303.4000 4.5000 4.0 4.0 4.0 TRUE

		REQUEST_MODEL sabre
		REQUEST_MODEL MANANA

		WHILE NOT HAS_MODEL_LOADED sabre
		OR NOT HAS_MODEL_LOADED MANANA
			WAIT 0
		ENDWHILE

		f4_car_has_been_created = 1

		REMOVE_BLIP f4_outside

		CREATE_CAR MANANA -1425.5125 -21.2813 5.0078 f4_objective_car

		SET_CAR_HEADING f4_objective_car 88.7848   
		 
		FREEZE_CAR_POSITION f4_objective_car TRUE

		ADD_BLIP_FOR_CAR f4_objective_car f4_objective_blip
		
		SET_BLIP_AS_FRIENDLY f4_objective_blip TRUE

		SET_CAN_BURST_CAR_TYRES f4_objective_car FALSE

		SET_CAR_HEALTH f4_objective_car 3000

		CHANGE_CAR_COLOUR f4_objective_car CARCOLOUR_CHERRYRED CARCOLOUR_CHERRYRED 

		PRINT_NOW ( F4_T7 ) 4000 1 // ~s~Get the ~b~car~s~!
		
		GOTO f4_main_loop1a 

	ENDIF
			
ENDWHILE

GOTO mission_failed_farlie4

// ****************************************************************************************************
// 
//                                             MAIN LOOP
//
// ****************************************************************************************************

f4_main_loop1a:

WHILE NOT IS_CHAR_DEAD scplayer
AND NOT IS_CAR_DEAD f4_objective_car

	WAIT 0

	GOSUB f4_keys

	GOSUB f4_play_sample

	IF IS_CHAR_IN_ANY_CAR scplayer 
	AND NOT IS_CAR_DEAD f4_objective_car

		IF NOT IS_CHAR_IN_CAR scplayer f4_objective_car

			STORE_CAR_CHAR_IS_IN scplayer car

		ENDIF

	ENDIF

	IF NOT IS_CAR_DEAD f4_objective_car

		IF IS_CHAR_IN_CAR scplayer f4_objective_car				 

			CHANGE_BLIP_DISPLAY f4_objective_blip NEITHER

			CHANGE_BLIP_DISPLAY f4_home_blip BOTH

		  	FREEZE_CAR_POSITION f4_objective_car FALSE

			GOTO f4_main_loop2

		ENDIF

	ENDIF
	
ENDWHILE

GOTO mission_failed_farlie4

// ****************************************************************************************************
//
//                                          MAIN LOOP 2
//
// ****************************************************************************************************

f4_main_loop2:

SET_PLAYER_CONTROL player1 FALSE

REQUEST_MODEL dnb1
REQUEST_MODEL dnb2
REQUEST_MODEL FCR900
REQUEST_MODEL micro_uzi
REQUEST_MODEL mule
REQUEST_MODEL cellphone
REQUEST_MODEL sabre
REQUEST_MODEL SADLER

WHILE NOT HAS_MODEL_LOADED dnb1
OR NOT HAS_MODEL_LOADED dnb2
OR NOT HAS_MODEL_LOADED FCR900
OR NOT HAS_MODEL_LOADED micro_uzi
OR NOT HAS_MODEL_LOADED mule
OR NOT HAS_MODEL_LOADED cellphone

	WAIT 0

ENDWHILE

CLEAR_MISSION_AUDIO 3

LOAD_MISSION_AUDIO 3 SOUND_REVERB_CAR_SCREECH

WHILE NOT HAS_MISSION_AUDIO_LOADED 3
OR NOT HAS_MODEL_LOADED sabre
OR NOT HAS_MODEL_LOADED SADLER

	WAIT 0

ENDWHILE

SET_CAR_DENSITY_MULTIPLIER 0.0

// **************************************************************************************************

quick_test:

GOSUB f4_fade_out

IF NOT IS_CAR_DEAD car

	IF LOCATE_CAR_3D car -1429.6185 -21.2788 5.0078 10.0 10.0 10.0 FALSE

		SET_CAR_AS_MISSION_CAR car
		
		SET_CAR_COORDINATES car -1425.6151 -16.4956 5.0078 
		
		SET_CAR_HEADING car 271.5388 	

	ENDIF

	WAIT 500

ENDIF

CLEAR_AREA -1412.5598 -138.0878 5.0000 200.0 TRUE

GOSUB f4_set_camera

SET_FIXED_CAMERA_POSITION -1430.3612 -18.6971 6.1910 0.0 0.0 0.0 // Bike from front
POINT_CAMERA_AT_POINT -1429.6552 -19.4032 6.1376 JUMP_CUT

// Cutscene Van

CREATE_CAR mule -1414.1731 -50.4918 5.0000 f4_mule[0]

SET_CAR_HEADING f4_mule[0] 0.0000  

CREATE_CHAR_INSIDE_CAR f4_mule[0] PEDTYPE_MISSION1 dnb1 f4_van_man[0]

GIVE_WEAPON_TO_CHAR f4_van_man[0] WEAPONTYPE_MICRO_UZI 30000

SET_CURRENT_CHAR_WEAPON f4_van_man[0] WEAPONTYPE_MICRO_UZI

// Drive out for cutscene
IF NOT IS_CAR_DEAD f4_mule[0]
AND NOT IS_CHAR_DEAD f4_van_man[0]
AND NOT IS_CHAR_DEAD scplayer

	OPEN_SEQUENCE_TASK f4_mule_seq[0]

		TASK_CAR_DRIVE_TO_COORD -1 f4_mule[0] -1414.4281 -32.5711 5.0069 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

		TASK_LEAVE_CAR -1 f4_mule[0]

		TASK_KILL_CHAR_ON_FOOT -1 scplayer

	CLOSE_SEQUENCE_TASK f4_mule_seq[0]

	PERFORM_SEQUENCE_TASK f4_van_man[0] f4_mule_seq[0]										 

ENDIF

IF NOT IS_CAR_DEAD f4_objective_car

	GET_CAR_HEALTH f4_objective_car f4_heal

ENDIF

f4_car_damage = f4_heal / 30

DISPLAY_ONSCREEN_COUNTER_WITH_STRING f4_car_damage COUNTER_DISPLAY_BAR F4_D

GOSUB f4_fade_in

PRINT_NOW ( F4_T5 ) 5000 1 // It's an ambush, the Vietnamese gang are covering the exits!
 
TIMERB = 0
WHILE TIMERB < 2000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		GOTO f4_skip_the_cut
	ENDIF
ENDWHILE  
																							
IF HAS_MISSION_AUDIO_LOADED 3

	PLAY_MISSION_AUDIO 3

ENDIF

TIMERB = 0
WHILE TIMERB < 2000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		GOTO f4_skip_the_cut
	ENDIF
ENDWHILE  

f4_skip_the_cut:

GOSUB f4_restore_camera
 
PRINT_NOW ( F4_T8 ) 4000 1 // ~s~Get the car back in one piece!

IF NOT IS_CAR_DEAD f4_objective_car
	FREEZE_CAR_POSITION f4_objective_car FALSE
ENDIF

WHILE NOT IS_CHAR_DEAD scplayer
AND NOT IS_CAR_DEAD f4_objective_car

	WAIT 0

	GOSUB f4_keys	 

	GOSUB f4_play_sample

	// **************************************************************************************************
	// *																								*
	// *                                         Respray Check            								*
	// *																								*
	// **************************************************************************************************

	IF NOT IS_CAR_DEAD f4_objective_car		

	  	IF HAS_CAR_BEEN_RESPRAYED f4_objective_car 

			SET_CAR_HEALTH f4_objective_car 3000						

		ENDIF

  	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                   Blip for getting out of car 									*
	// *																								*
	// **************************************************************************************************

	IF NOT IS_CAR_DEAD f4_objective_car

		IF IS_CHAR_IN_CAR scplayer f4_objective_car

			IF f4_inside_car = 0

				CHANGE_BLIP_DISPLAY f4_objective_blip NEITHER

				CHANGE_BLIP_DISPLAY f4_home_blip BOTH

				f4_inside_car = 1

			ENDIF

		ELSE

			IF f4_inside_car = 1

				CHANGE_BLIP_DISPLAY f4_objective_blip BOTH

				CHANGE_BLIP_DISPLAY f4_home_blip NEITHER

				PRINT_NOW ( F4_T4 ) 4000 1 // ~s~Get back in the ~b~car!

				f4_inside_car = 0

			ENDIF

		ENDIF
				
		GET_CAR_HEALTH f4_objective_car f4_heal

		f4_car_damage = f4_heal / 30
				
	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Vans move to block player									*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1467.0321 -133.7941 5.0078 60.0 60.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1347.1105 -49.7249 5.0078 20.0 20.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1421.3441 -238.9247 5.0000 20.0 20.0 10.0 FALSE 

		IF f4_trigger[0] = 0
			
			CLEAR_AREA -1474.4658 -162.8309 5.0234 10.0 TRUE
					
			CREATE_CAR mule -1474.4658 -162.8309 5.0234 f4_mule[2]

			SET_CAR_HEADING f4_mule[2] 272.5542  

			CREATE_CHAR_INSIDE_CAR f4_mule[2] PEDTYPE_MISSION1 dnb1 f4_van_man[2]

			GIVE_WEAPON_TO_CHAR f4_van_man[2] WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON f4_van_man[2] WEAPONTYPE_MICRO_UZI

			IF NOT IS_CAR_DEAD f4_mule[2]
			AND NOT IS_CHAR_DEAD f4_van_man[2]
			AND NOT IS_CHAR_DEAD scplayer

				OPEN_SEQUENCE_TASK f4_mule_seq[2]

					TASK_CAR_DRIVE_TO_COORD -1 f4_mule[2] -1464.8624 -162.4026 5.0226 25.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

					TASK_LEAVE_CAR -1 f4_mule[2]

					TASK_KILL_CHAR_ON_FOOT -1 scplayer

				CLOSE_SEQUENCE_TASK f4_mule_seq[2]

				PERFORM_SEQUENCE_TASK f4_van_man[2] f4_mule_seq[2]										 

			ENDIF

			f4_trigger[0] = 1

		ENDIF

	ENDIF
	
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1423.8495 -135.7411 5.0078 20.0 20.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1347.1105 -49.7249 5.0078 20.0 20.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1421.3441 -238.9247 5.0000 20.0 20.0 10.0 FALSE
	 
		IF f4_trigger[1] = 0

			CLEAR_AREA -1387.2682 -126.9234 5.0078 10.0 TRUE

			CREATE_CAR mule -1387.2682 -126.9234 5.0078 f4_mule[1]

			SET_CAR_HEADING f4_mule[1] 180.2388  

			CREATE_CHAR_INSIDE_CAR f4_mule[1] PEDTYPE_MISSION1 dnb1 f4_van_man[1]

			GIVE_WEAPON_TO_CHAR f4_van_man[1] WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON f4_van_man[1] WEAPONTYPE_MICRO_UZI

			IF NOT IS_CAR_DEAD f4_mule[1]
			AND NOT IS_CHAR_DEAD f4_van_man[1]
			AND NOT IS_CHAR_DEAD scplayer

				OPEN_SEQUENCE_TASK f4_mule_seq[1]

					TASK_CAR_DRIVE_TO_COORD -1 f4_mule[1] -1387.2255 -135.6199 5.0078 25.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

					TASK_LEAVE_CAR -1 f4_mule[1]

					TASK_KILL_CHAR_ON_FOOT -1 scplayer

				CLOSE_SEQUENCE_TASK f4_mule_seq[1]

				PERFORM_SEQUENCE_TASK f4_van_man[1] f4_mule_seq[1]	

			ENDIF

			f4_trigger[1] = 1

		ENDIF

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1427.2694 -238.5722 5.0234 20.0 20.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1347.1105 -49.7249 5.0078 20.0 20.0 10.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1421.3441 -238.9247 5.0000 20.0 20.0 10.0 FALSE 

		IF f4_trigger[10] = 0

			CLEAR_AREA -1387.7921 -230.1776 5.0078 10.0 TRUE

			CREATE_CAR mule -1387.7921 -230.1776 5.0078 f4_mule[3]

			SET_CAR_HEADING f4_mule[3] 179.5878   

			CREATE_CHAR_INSIDE_CAR f4_mule[3] PEDTYPE_MISSION1 dnb1 f4_van_man[3]

			GIVE_WEAPON_TO_CHAR f4_van_man[3] WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON f4_van_man[3] WEAPONTYPE_MICRO_UZI

			IF NOT IS_CAR_DEAD f4_mule[3]
			AND NOT IS_CHAR_DEAD scplayer

				OPEN_SEQUENCE_TASK f4_mule_seq[3]

					TASK_CAR_DRIVE_TO_COORD -1 f4_mule[3] -1387.8624 -240.3811 5.0078 25.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

					TASK_LEAVE_CAR -1 f4_mule[3]

					TASK_KILL_CHAR_ON_FOOT -1 scplayer

				CLOSE_SEQUENCE_TASK f4_mule_seq[3]

				PERFORM_SEQUENCE_TASK f4_van_man[3] f4_mule_seq[3]	

			ENDIF

			f4_trigger[10] = 1

		ENDIF

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Scripted guys who roll out									*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1399.6862 -184.7570 5.3047 5.0 5.0 10.0 FALSE
	AND f4_trigger[2] = 0

		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1404.0490 -234.7071 5.3047 f4_roll_out_1

		SET_CHAR_HEADING f4_roll_out_1 353.8535

		GIVE_WEAPON_TO_CHAR f4_roll_out_1 WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_roll_out_1 WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_roll_out_1 80

		SET_CHAR_DECISION_MAKER f4_roll_out_1 f4_decision

		TASK_WEAPON_ROLL f4_roll_out_1 TRUE
		 
		f4_trigger[2] = 1 

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1400.1842 -205.7361 5.3047 5.0 5.0 10.0 FALSE
	AND f4_trigger[3] = 0

		CREATE_CHAR PEDTYPE_MISSION1 dnb1 -1394.2347 -234.7350 5.3047 f4_roll_out_2

		SET_CHAR_HEADING f4_roll_out_2 1.5813

		GIVE_WEAPON_TO_CHAR f4_roll_out_2 WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_roll_out_2 WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_roll_out_2 80

		SET_CHAR_DECISION_MAKER f4_roll_out_2 f4_decision

		TASK_WEAPON_ROLL f4_roll_out_2 FALSE
		 
		f4_trigger[3] = 1 

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                Scripted guys talking outside									*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1407.1873 -239.4782 5.3047 20.0 20.0 10.0 FALSE
	AND f4_trigger[4] = 0

		// Guys at Car park entrance
		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1407.0764 -358.3559 8.3099 f4_entrance[0]

		SET_CHAR_HEADING f4_entrance[0] 9.5994  

		CREATE_CHAR PEDTYPE_MISSION1 dnb1 -1476.8867 -291.5300 8.7087 f4_entrance[1]

		SET_CHAR_HEADING f4_entrance[1] 258.9856

		REPEAT 2 v

			GIVE_WEAPON_TO_CHAR f4_entrance[v] WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON f4_entrance[v] WEAPONTYPE_MICRO_UZI

			SET_CHAR_ACCURACY f4_entrance[v] 80

			SET_CHAR_DECISION_MAKER f4_entrance[v] f4_decision
		
			TASK_STAY_IN_SAME_PLACE f4_entrance[v] TRUE

			TASK_KILL_CHAR_ON_FOOT f4_entrance[v] scplayer

			TASK_TOGGLE_DUCK f4_entrance[v] TRUE

		ENDREPEAT

		// Small roadblock
		CREATE_CAR mule -1478.8958 -470.3535 10.9926 f4_mule[3]

		SET_CAR_HEADING f4_mule[3] 11.1921  
 
		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1478.1881 -476.1086 11.1411 f4_road_block[0]

		SET_CHAR_HEADING f4_road_block[0] 277.8792 

		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1479.5347 -466.4356 11.0000 f4_road_block[1]

		SET_CHAR_HEADING f4_road_block[1] 263.3237

		// Small roadblock
		CREATE_CAR mule -1586.0226 -359.7679 10.9926 f4_mule[4]

		SET_CAR_HEADING f4_mule[4] 248.7342  
		 
		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1582.2249 -362.0264 11.0000 f4_road_block[2]

		SET_CHAR_HEADING f4_road_block[2] 356.2877 

		CREATE_CHAR PEDTYPE_MISSION1 dnb2 -1590.6940 -357.4667 11.1411 f4_road_block[3]

		SET_CHAR_HEADING f4_road_block[3] 323.3828

		REPEAT 4 v

			GIVE_WEAPON_TO_CHAR f4_road_block[v] WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON f4_road_block[v] WEAPONTYPE_MICRO_UZI

			SET_CHAR_ACCURACY f4_road_block[v] 80

			SET_CHAR_DECISION_MAKER f4_road_block[v] f4_decision
			
			TASK_STAY_IN_SAME_PLACE f4_road_block[v] TRUE

			TASK_KILL_CHAR_ON_FOOT f4_road_block[v] scplayer

		ENDREPEAT
			    
		f4_trigger[4] = 1 

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                     Turn back on random cars  									*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1570.8882 -455.6748 5.0000 25.0 25.0 10.0 FALSE
	AND f4_trigger[5] = 0

		SET_CAR_DENSITY_MULTIPLIER 1.0

		DELETE_CHAR f4_roll_out_1

		DELETE_CHAR f4_roll_out_2

		REPEAT 3 v

			DELETE_CHAR f4_van_man[v]

			IF NOT IS_CAR_DEAD f4_mule[v]
				IF NOT IS_CHAR_IN_CAR scplayer f4_mule[v]

					DELETE_CAR f4_mule[v]	

				ENDIF
			ENDIF
					
			DELETE_CHAR f4_entrance[v]

		ENDREPEAT

		f4_trigger[5] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                      Guys on Motorcycles    									*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2258.5188 124.3485 34.3125 150.0 150.0 10.0 FALSE
	AND f4_trigger[6] = 0
	
		CLEAR_AREA -2258.5188 124.3485 34.3125 10.0 TRUE

		CREATE_CAR FCR900 -2258.5188 124.3485 34.3125 f4_motorbike[0]
		
		ADD_BLIP_FOR_CAR f4_motorbike[0] f4_bike_blip[0]
		 
		SET_CAR_DRIVING_STYLE f4_motorbike[0] DRIVINGMODE_AVOIDCARS

		SET_CAR_HEADING f4_motorbike[0] 179.5562  
		
		CREATE_CHAR_INSIDE_CAR f4_motorbike[0] PEDTYPE_MISSION1 dnb1 f4_biker[0]

		CREATE_CHAR_AS_PASSENGER f4_motorbike[0] PEDTYPE_MISSION1 dnb2 0 f4_biker[1]

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[0] KNOCKOFFBIKE_DEFAULT

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[1] KNOCKOFFBIKE_DEFAULT

		GIVE_WEAPON_TO_CHAR f4_biker[0] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[0] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[0] 30

		GIVE_WEAPON_TO_CHAR f4_biker[1] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[1] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[1] 30

		SET_CHAR_DECISION_MAKER f4_biker[0] f4_decision
		 
	   	SET_CHAR_DECISION_MAKER f4_biker[1] f4_decision

		TASK_DRIVE_BY f4_biker[1] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

		IF NOT IS_CAR_DEAD f4_objective_car

			TASK_CAR_MISSION f4_biker[0] f4_motorbike[0] f4_objective_car MISSION_ESCORT_LEFT 100.0 DRIVINGMODE_AVOIDCARS

		ENDIF

		f4_trigger[6] = 1

	ENDIF
	
	// **************************************************************************************************
	// *																								*
	// *                                      Guys on Motorcycles    									*
	// *																								*
	// **************************************************************************************************
   
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1817.8049 -552.1889 15.0039 150.0 150.0 10.0 FALSE
	AND f4_trigger[7] = 0

		CLEAR_AREA -1817.8049 -552.1889 15.0039 10.0 TRUE

		CREATE_CAR FCR900 -1817.8049 -552.1889 15.0039 f4_motorbike[1]

		ADD_BLIP_FOR_CAR f4_motorbike[1] f4_bike_blip[1]

		SET_CAR_HEADING f4_motorbike[1] 174.0251 
		 
		SET_CAR_DRIVING_STYLE f4_motorbike[1] DRIVINGMODE_AVOIDCARS

		CREATE_CHAR_INSIDE_CAR f4_motorbike[1] PEDTYPE_MISSION1 dnb1 f4_biker[2]

		CREATE_CHAR_AS_PASSENGER f4_motorbike[1] PEDTYPE_MISSION1 dnb2 0 f4_biker[3]

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[2] KNOCKOFFBIKE_DEFAULT

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[3] KNOCKOFFBIKE_DEFAULT

		GIVE_WEAPON_TO_CHAR f4_biker[2] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[2] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[2] 30

		GIVE_WEAPON_TO_CHAR f4_biker[3] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[3] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[3] 30

		TASK_DRIVE_BY f4_biker[3] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50
		 
		SET_CHAR_DECISION_MAKER f4_biker[3] f4_decision 

	   	SET_CHAR_DECISION_MAKER f4_biker[2] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			OPEN_SEQUENCE_TASK f4_sequence_task
				
				TASK_PAUSE -1 5000

				TASK_CAR_MISSION -1 f4_motorbike[1] f4_objective_car MISSION_ESCORT_RIGHT 100.0 DRIVINGMODE_AVOIDCARS

			CLOSE_SEQUENCE_TASK f4_sequence_task

			PERFORM_SEQUENCE_TASK f4_biker[2] f4_sequence_task

			CLEAR_SEQUENCE_TASK f4_sequence_task

		ENDIF

		f4_trigger[7] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                      Guys on Motorcycles    									*
	// *																								*
	// **************************************************************************************************
   
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1818.3853 -621.8912 15.3120 150.0 150.0 10.0 FALSE
	AND f4_trigger[8] = 0

		CLEAR_AREA -1818.3853 -621.8912 15.3120 10.0 TRUE

		CREATE_CAR FCR900 -1818.3853 -621.8912 15.3120 f4_motorbike[2]

		ADD_BLIP_FOR_CAR f4_motorbike[2] f4_bike_blip[2]

		SET_CAR_HEADING f4_motorbike[2] 1.7010   
				 
		SET_CAR_DRIVING_STYLE f4_motorbike[2] DRIVINGMODE_AVOIDCARS

		CREATE_CHAR_INSIDE_CAR f4_motorbike[2] PEDTYPE_MISSION1 dnb1 f4_biker[4]

		CREATE_CHAR_AS_PASSENGER f4_motorbike[2] PEDTYPE_MISSION1 dnb2 0 f4_biker[5]

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[4] KNOCKOFFBIKE_DEFAULT

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_biker[5] KNOCKOFFBIKE_DEFAULT

		GIVE_WEAPON_TO_CHAR f4_biker[4] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[4] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[4] 30

		GIVE_WEAPON_TO_CHAR f4_biker[5] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_biker[5] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_biker[5] 30

		TASK_DRIVE_BY f4_biker[5] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

	  	SET_CHAR_DECISION_MAKER f4_biker[5] f4_decision
		 
		SET_CHAR_DECISION_MAKER f4_biker[4] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			OPEN_SEQUENCE_TASK f4_sequence_task
				
				TASK_PAUSE -1 5000

				TASK_CAR_MISSION -1 f4_motorbike[2] f4_objective_car MISSION_ESCORT_LEFT 100.0 DRIVINGMODE_AVOIDCARS

			CLOSE_SEQUENCE_TASK f4_sequence_task

			PERFORM_SEQUENCE_TASK f4_biker[4] f4_sequence_task

			CLEAR_SEQUENCE_TASK f4_sequence_task

		ENDIF

		f4_trigger[8] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Car to ambush player   										*
	// *																								*
	// **************************************************************************************************
   				   
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2211.6204 580.9911 34.1691 300.0 300.0 10.0 FALSE
	AND f4_trigger_car[0] = 0

		CLEAR_AREA -2211.6204 580.9911 34.1691 10.0 TRUE

		CREATE_CAR MANANA -2211.6204 580.9911 34.1691 f4_car[0]

		ADD_BLIP_FOR_CAR f4_car[0] f4_car_blip[0]

		SET_CAR_HEADING f4_car[0] 176.7102   
				 
		SET_CAR_DRIVING_STYLE f4_car[0] DRIVINGMODE_AVOIDCARS

		CREATE_CHAR_INSIDE_CAR f4_car[0] PEDTYPE_MISSION1 dnb1 f4_driver[0]

		CREATE_CHAR_AS_PASSENGER f4_car[0] PEDTYPE_MISSION1 dnb2 0 f4_driver[1]

		SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE f4_driver[1] KNOCKOFFBIKE_ALWAYSNORMAL

		GIVE_WEAPON_TO_CHAR f4_driver[0] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[0] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[0] 30

		GIVE_WEAPON_TO_CHAR f4_driver[1] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[1] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[1] 30

		TASK_DRIVE_BY f4_driver[1] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

	  	SET_CHAR_DECISION_MAKER f4_driver[1] f4_decision
		 
		SET_CHAR_DECISION_MAKER f4_driver[0] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			TASK_CAR_MISSION f4_driver[0] f4_car[0] f4_objective_car MISSION_ESCORT_RIGHT 100.0 DRIVINGMODE_AVOIDCARS

		ENDIF

		f4_trigger_car[0] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Car to ambush player   										*
	// *																								*
	// **************************************************************************************************
	   
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1850.1274 1096.3236 44.2969 300.0 300.0 10.0 FALSE
	AND f4_trigger_car[1] = 0

		CLEAR_AREA -1850.1274 1096.3236 44.2969 10.0 TRUE

		CREATE_CAR sabre -1850.1274 1096.3236 44.2969 f4_car[1]

		ADD_BLIP_FOR_CAR f4_car[1] f4_car_blip[1]

		SET_CAR_HEADING f4_car[1] 81.5282   
				 
		SET_CAR_DRIVING_STYLE f4_car[1] DRIVINGMODE_AVOIDCARS

		CREATE_CHAR_INSIDE_CAR f4_car[1] PEDTYPE_MISSION1 dnb1 f4_driver[2]

		CREATE_CHAR_AS_PASSENGER f4_car[1] PEDTYPE_MISSION1 dnb2 0 f4_driver[3]

		GIVE_WEAPON_TO_CHAR f4_driver[2] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[2] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[2] 30

		GIVE_WEAPON_TO_CHAR f4_driver[3] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[3] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[3] 30

		TASK_DRIVE_BY f4_driver[3] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

	  	SET_CHAR_DECISION_MAKER f4_driver[3] f4_decision
		 
		SET_CHAR_DECISION_MAKER f4_driver[2] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			TASK_CAR_MISSION f4_driver[2] f4_car[1] f4_objective_car MISSION_ESCORT_LEFT 100.0 DRIVINGMODE_AVOIDCARS

		ENDIF

		f4_trigger_car[1] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Car to ambush player   										*
	// *																								*
	// **************************************************************************************************
    
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1740.3693 1404.4607 6.1875 300.0 300.0 10.0 FALSE
	AND f4_trigger_car[2] = 0

		CLEAR_AREA -1740.3693 1404.4607 6.1875 10.0 TRUE

		CREATE_CAR SADLER -1740.3693 1404.4607 6.1875 f4_car[2]

		ADD_BLIP_FOR_CAR f4_car[2] f4_car_blip[2]

		SET_CAR_HEADING f4_car[2] 172.1585   
				 
		SET_CAR_DRIVING_STYLE f4_car[2] DRIVINGMODE_AVOIDCARS

		CREATE_CHAR_INSIDE_CAR f4_car[2] PEDTYPE_MISSION1 dnb1 f4_driver[4]

		CREATE_CHAR_AS_PASSENGER f4_car[2] PEDTYPE_MISSION1 dnb2 0 f4_driver[5]

		GIVE_WEAPON_TO_CHAR f4_driver[4] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[4] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[4] 30

		GIVE_WEAPON_TO_CHAR f4_driver[5] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[5] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[5] 30

		TASK_DRIVE_BY f4_driver[5] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

	  	SET_CHAR_DECISION_MAKER f4_driver[5] f4_decision
		 
		SET_CHAR_DECISION_MAKER f4_driver[4] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			TASK_CAR_MISSION f4_driver[4] f4_car[2] f4_objective_car MISSION_ESCORT_RIGHT 100.0 DRIVINGMODE_AVOIDCARS

		ENDIF

		f4_trigger_car[2] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                    Car to ambush player   										*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1892.6516 745.0367 44.2969 300.0 300.0 10.0 FALSE
	AND f4_trigger_car[3] = 0

		CLEAR_AREA -1892.6516 745.0367 44.2969 10.0 TRUE

		CREATE_CAR sabre -1892.6516 745.0367 44.2969 f4_car[3]

		ADD_BLIP_FOR_CAR f4_car[3] f4_car_blip[3]

		SET_CAR_HEADING f4_car[3] 167.9494   
				 
		SET_CAR_DRIVING_STYLE f4_car[3] DRIVINGMODE_AVOIDCARS

		SET_CAR_MISSION f4_car[3] MISSION_BLOCKPLAYER_CLOSE

		CREATE_CHAR_INSIDE_CAR f4_car[3] PEDTYPE_MISSION1 dnb1 f4_driver[6]

		CREATE_CHAR_AS_PASSENGER f4_car[3] PEDTYPE_MISSION1 dnb2 0 f4_driver[7]

		GIVE_WEAPON_TO_CHAR f4_driver[6] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[6] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[6] 30

		GIVE_WEAPON_TO_CHAR f4_driver[7] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_driver[7] WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_driver[7] 30

		TASK_DRIVE_BY f4_driver[6] scplayer -1 0.0 0.0 0.0 999.0 DRIVEBY_AI_ALL_DIRN FALSE 50 

	  	SET_CHAR_DECISION_MAKER f4_driver[7] f4_decision
		 
		SET_CHAR_DECISION_MAKER f4_driver[6] f4_decision 

		IF NOT IS_CAR_DEAD f4_objective_car

			TASK_CAR_MISSION f4_driver[7] f4_car[3] f4_objective_car MISSION_ESCORT_LEFT 100.0 DRIVINGMODE_AVOIDCARS

		ENDIF

		f4_trigger_car[3] = 1

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                     Guy waiting at end    										*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2154.2065 576.4759 34.1719 150.0 150.0 10.0 FALSE
	AND f4_trigger[9] = 0
			
		LVAR_INT f4_roll_out_bike

		LVAR_INT f4_roll_out_3

		CLEAR_AREA -1818.3853 -621.8912 15.3120 10.0 TRUE

		CREATE_CHAR PEDTYPE_MISSION1 dnb1 -2154.2065 576.4759 34.1719 f4_roll_out_3

		CREATE_CAR FCR900 -2154.3816 577.5017 34.1719 f4_roll_out_bike

		SET_CAR_HEADING f4_roll_out_bike 114.1029   

		SET_CHAR_HEADING f4_roll_out_3 211.0992 

		GIVE_WEAPON_TO_CHAR f4_roll_out_3 WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_roll_out_3 WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_roll_out_3 90

		SET_CHAR_DECISION_MAKER f4_roll_out_3 f4_decision

		TASK_STAY_IN_SAME_PLACE f4_roll_out_3 TRUE
		 
		f4_trigger[9] = 1 

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                     Guy waiting at end    										*
	// *																								*
	// **************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2245.6750 303.3215 34.3203 150.0 150.0 10.0 FALSE
	AND f4_trigger[9] = 0
			
		LVAR_INT f4_roll_out_bike_a

		LVAR_INT f4_roll_out_4

		CLEAR_AREA -2245.6750 303.3215 34.3203 10.0 TRUE

		CREATE_CHAR PEDTYPE_MISSION1 dnb1 -2245.6750 303.3215 34.3203 f4_roll_out_4

		CREATE_CAR FCR900 -2244.7817 304.8253 34.3203 f4_roll_out_bike_a

		SET_CAR_HEADING f4_roll_out_bike_a 191.9683   

		SET_CHAR_HEADING f4_roll_out_4 165.1429  

		GIVE_WEAPON_TO_CHAR f4_roll_out_4 WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON f4_roll_out_4 WEAPONTYPE_MICRO_UZI

		SET_CHAR_ACCURACY f4_roll_out_4 90

		SET_CHAR_DECISION_MAKER f4_roll_out_4 f4_decision

		TASK_STAY_IN_SAME_PLACE f4_roll_out_4 TRUE
		 
		f4_trigger[9] = 1 

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                  Remove blips if guys are dead									*
	// *																								*
	// **************************************************************************************************

	IF NOT IS_CHAR_DEAD f4_biker[0]

		IF NOT IS_CHAR_IN_ANY_CAR f4_biker[0]
				
			TASK_DIE f4_biker[0]

			IF DOES_BLIP_EXIST f4_bike_blip[0]
				REMOVE_BLIP f4_bike_blip[0]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_bike_blip[0]
			REMOVE_BLIP f4_bike_blip[0]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_biker[2]

		IF NOT IS_CHAR_IN_ANY_CAR f4_biker[2]

			IF DOES_BLIP_EXIST f4_bike_blip[1]
				REMOVE_BLIP f4_bike_blip[1]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_bike_blip[1]
			REMOVE_BLIP f4_bike_blip[1]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_biker[4]

		IF NOT IS_CHAR_IN_ANY_CAR f4_biker[4]

			IF DOES_BLIP_EXIST f4_bike_blip[2]
				REMOVE_BLIP f4_bike_blip[2]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_bike_blip[2]
			REMOVE_BLIP f4_bike_blip[2]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_driver[0]

		IF NOT IS_CHAR_IN_ANY_CAR f4_driver[0]

			IF DOES_BLIP_EXIST f4_car_blip[0]
				REMOVE_BLIP f4_car_blip[0]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_car_blip[0]
			REMOVE_BLIP f4_car_blip[0]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_driver[2]

		IF NOT IS_CHAR_IN_ANY_CAR f4_driver[2]

			IF DOES_BLIP_EXIST f4_car_blip[1]
				REMOVE_BLIP f4_car_blip[1]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_car_blip[1]
			REMOVE_BLIP f4_car_blip[1]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_driver[4]

		IF NOT IS_CHAR_IN_ANY_CAR f4_driver[4]

			IF DOES_BLIP_EXIST f4_car_blip[2]
				REMOVE_BLIP f4_car_blip[2]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_car_blip[2]
			REMOVE_BLIP f4_car_blip[2]
		ENDIF

	ENDIF

	// -------------------------------------------------------------------------------

	IF NOT IS_CHAR_DEAD f4_driver[6]

		IF NOT IS_CHAR_IN_ANY_CAR f4_driver[6]

			IF DOES_BLIP_EXIST f4_car_blip[3]
				REMOVE_BLIP f4_car_blip[3]
			ENDIF

		ENDIF

	ELSE

		IF DOES_BLIP_EXIST f4_car_blip[3]
			REMOVE_BLIP f4_car_blip[3]
		ENDIF

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                        Enemy speech 	     									*
	// *																								*
	// **************************************************************************************************

	IF NOT IS_CHAR_DEAD scplayer

		REPEAT 4 v

			IF NOT IS_CHAR_DEAD f4_biker[v]
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer f4_biker[v] 10.0 10.0 FALSE

					GOSUB f4_shout	
					
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD f4_road_block[v]
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer f4_road_block[v] 10.0 10.0 FALSE

					GOSUB f4_shout						
					
				ENDIF
			ENDIF																			 

		ENDREPEAT

		REPEAT 2 v

			IF NOT IS_CHAR_DEAD f4_entrance[v]
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer f4_entrance[v] 10.0 10.0 FALSE

					GOSUB f4_shout						
					
				ENDIF
			ENDIF

		ENDREPEAT
		
		IF NOT IS_CHAR_DEAD f4_roll_out_1
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer f4_roll_out_1 10.0 10.0 FALSE

				GOSUB f4_shout						
				
			ENDIF
		ENDIF
				 
		IF NOT IS_CHAR_DEAD f4_roll_out_2
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer f4_roll_out_2 10.0 10.0 FALSE

				GOSUB f4_shout						
				
			ENDIF
		ENDIF

	ENDIF

	// **************************************************************************************************
	// *																								*
	// *                                       Enters garage  											*
	// *																								*
	// **************************************************************************************************
	
	IF NOT IS_CAR_DEAD f4_objective_car

		IF IS_CHAR_IN_CAR scplayer f4_objective_car

		  	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1742.2732 1422.6310 6.1842 4.0 4.0 4.0 TRUE

				SET_PLAYER_CONTROL player1 FALSE				

				GOSUB f4_fade_out

				IF NOT IS_CAR_DEAD f4_motorbike[0]
				
					DELETE_CAR f4_motorbike[0]	

				ENDIF

				IF NOT IS_CAR_DEAD f4_motorbike[1]
				
					DELETE_CAR f4_motorbike[1]	

				ENDIF

				IF NOT IS_CAR_DEAD f4_motorbike[2]
				
					DELETE_CAR f4_motorbike[2]	

				ENDIF 

				IF NOT IS_CAR_DEAD f4_car[0]
				
					DELETE_CAR f4_car[0]	

				ENDIF 
				IF NOT IS_CAR_DEAD f4_car[1]
				
					DELETE_CAR f4_car[1]	

				ENDIF 
				IF NOT IS_CAR_DEAD f4_car[2]
				
					DELETE_CAR f4_car[2]	

				ENDIF 
				IF NOT IS_CAR_DEAD f4_car[3]
				
					DELETE_CAR f4_car[3]	

				ENDIF 

				IF NOT IS_CHAR_DEAD scplayer
 
				  	WARP_CHAR_FROM_CAR_TO_COORD scplayer -1741.7063 1425.3844 6.1842

				  	SET_CHAR_HEADING scplayer 179.2136 

				ENDIF 

				CLEAR_ONSCREEN_COUNTER f4_car_damage

				DELETE_CAR f4_objective_car
								
				SET_PLAYER_CONTROL player1 TRUE
				
				SET_CAMERA_BEHIND_PLAYER
					
				RESTORE_CAMERA_JUMPCUT

				WAIT 2000

				GOSUB f4_fade_in

				GOTO mission_passed_farlie4	

			ENDIF

		ENDIF

	ENDIF

ENDWHILE

GOTO mission_failed_farlie4

// **************************************************************************************************
// *																								*
// *                                       Mission Failed											*
// *																								*
// **************************************************************************************************

mission_failed_farlie4:

	IF DOES_BLIP_EXIST f4_home_blip
		REMOVE_BLIP f4_home_blip
	ENDIF

	IF DOES_BLIP_EXIST f4_objective_blip
		REMOVE_BLIP f4_objective_blip
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[0]
		REMOVE_BLIP f4_bike_blip[0]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[1]
		REMOVE_BLIP f4_bike_blip[1]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[2]
		REMOVE_BLIP f4_bike_blip[2]
	ENDIF

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

	IF IS_CAR_DEAD f4_objective_car
	AND f4_car_has_been_created = 1

		PRINT_NOW ( F4_F ) 4000 1 // ~r~The car has been destroyed!

	ENDIF
   
RETURN

// **************************************************************************************************
//
//                                         Mission Passed
//
// **************************************************************************************************

mission_passed_farlie4:

flag_wuzi_mission_counter ++

	IF DOES_BLIP_EXIST f4_home_blip
		REMOVE_BLIP f4_home_blip
	ENDIF
	IF DOES_BLIP_EXIST f4_objective_blip
		REMOVE_BLIP f4_objective_blip
	ENDIF

	REGISTER_MISSION_PASSED ( FAR_4 )
	PLAYER_MADE_PROGRESS 1

	PLAY_MISSION_PASSED_TUNE 1

	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 6000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 6000//amount of cash

	AWARD_PLAYER_MISSION_RESPECT 15 //amount of respect

	CLEAR_WANTED_LEVEL player1
	
	IF DOES_BLIP_EXIST f4_bike_blip[0]
		REMOVE_BLIP f4_bike_blip[0]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[1]
		REMOVE_BLIP f4_bike_blip[1]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[2]
		REMOVE_BLIP f4_bike_blip[2]
	ENDIF

	IF NOT IS_CAR_DEAD f4_motorbike[0]
		DELETE_CAR f4_motorbike[0]
	ENDIF

	IF NOT IS_CAR_DEAD f4_motorbike[1]
		DELETE_CAR f4_motorbike[1]
	ENDIF

	IF NOT IS_CAR_DEAD f4_motorbike[2]
		DELETE_CAR f4_motorbike[2]
	ENDIF

	GOSUB f4_fade_in

RETURN

// **************************************************************************************************

f4_set_camera:

	CLEAR_PRINTS

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

RETURN
// **************************************************************************************************

f4_restore_camera:

	CLEAR_PRINTS

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
 
RETURN
// **************************************************************************************************

f4_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN
// **************************************************************************************************

f4_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

// **************************************************************************************************
//
//                                          Mission Cleanup
//
// **************************************************************************************************

mission_cleanup_farlie4:

	flag_player_on_mission = 0

	CLEAR_ONSCREEN_COUNTER f4_car_damage

	SWITCH_ROADS_BACK_TO_ORIGINAL -1478.2944 -65.4014 0.0000 -1395.4229 2.7054 10.0000 

	MARK_MODEL_AS_NO_LONGER_NEEDED dnb1
	MARK_MODEL_AS_NO_LONGER_NEEDED dnb2
	MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED MULE
	MARK_MODEL_AS_NO_LONGER_NEEDED sabre
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	MARK_MODEL_AS_NO_LONGER_NEEDED MANANA
	MARK_MODEL_AS_NO_LONGER_NEEDED SADLER

	GET_GAME_TIMER timer_mobile_start

	IF DOES_BLIP_EXIST f4_home_blip
		REMOVE_BLIP f4_home_blip
	ENDIF

	IF DOES_BLIP_EXIST f4_objective_blip
		REMOVE_BLIP f4_objective_blip
	ENDIF

	IF DOES_BLIP_EXIST f4_outside
		REMOVE_BLIP f4_outside
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[0]
		REMOVE_BLIP f4_bike_blip[0]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[1]
		REMOVE_BLIP f4_bike_blip[1]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_bike_blip[2]
		REMOVE_BLIP f4_bike_blip[2]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_car_blip[0]
		REMOVE_BLIP f4_car_blip[0]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_car_blip[1]
		REMOVE_BLIP f4_car_blip[1]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_car_blip[2]
		REMOVE_BLIP f4_car_blip[2]
	ENDIF
	
	IF DOES_BLIP_EXIST f4_car_blip[3]
		REMOVE_BLIP f4_car_blip[3]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		IF IS_GARAGE_OPEN fdorsfe
		AND NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -2161.6282 654.6520 51.3706 10.0 10.0 10.0 FALSE

		  	CLOSE_GARAGE fdorsfe 

		ENDIF
	ENDIF
			
	MISSION_HAS_FINISHED

RETURN
 
// **************************************************************************************************
//
//                                         Keyboard Shortcuts
//
// **************************************************************************************************
 
f4_keys:

	LVAR_INT doc2_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer -1403.2834 -395.7443 11.0000    

			SET_CHAR_HEADING scplayer 343.9661 

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

		IF IS_CHAR_IN_ANY_CAR scplayer
			
			STORE_CAR_CHAR_IS_IN scplayer doc2_dummy_car
					
			POP_CAR_BOOT doc2_dummy_car

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		GOTO f4_main_loop2

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
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z

		GOTO quick_test

	ENDIF

RETURN
	
f4_shout:

IF TIMERB > 15000

	IF NOT IS_MESSAGE_BEING_DISPLAYED

		SWITCH f4_rnd
		
			CASE 0

				$f4_print = &FAR4_AA	// You won't get far!
				f4_audio = SOUND_FAR4_AA
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK
			CASE 1

				$f4_print = &FAR4_AB	// Stop that car!
				f4_audio = SOUND_FAR4_AB
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK
			CASE 2

				$f4_print = &FAR4_AC	// He's getting away!
				f4_audio = SOUND_FAR4_AC
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK
			CASE 3

				$f4_print = &FAR4_AD	// Stop that car at all costs!
				f4_audio = SOUND_FAR4_AD
				GOSUB f4_load_sample

		   		TIMERB = 0
		
			BREAK
			CASE 4

				$f4_print = &FAR4_AE	// Get after him!
				f4_audio = SOUND_FAR4_AE
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK
			CASE 5

				$f4_print = &FAR4_AG	// Give up the car and we'll spare you!
				f4_audio = SOUND_FAR4_AG
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK
			CASE 6

				$f4_print = &FAR4_AH	// That's our property!
				f4_audio = SOUND_FAR4_AH
				GOSUB f4_load_sample
 
				TIMERB = 0

			BREAK
			CASE 7

				$f4_print = &FAR4_AJ	// After him, no quarter!
				f4_audio = SOUND_FAR4_AJ
				GOSUB f4_load_sample

				TIMERB = 0

			BREAK

		ENDSWITCH

		f4_rnd ++
		
		IF f4_rnd = 8

			f4_rnd = 0

		ENDIF

	ENDIF

ENDIF


RETURN

f4_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 f4_audio

	f4_playing = 0

RETURN

f4_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND f4_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $f4_print ) 10000 1 

		f4_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND f4_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $f4_print

		f4_playing = 2

	ENDIF
	
RETURN

// **************************************************************************************************

}

/* 
   
{-------------------------- FARLIE 4 ---------------------------}

[F4_T0:FARLIE4]
~s~Pick up the car from the airport ~y~car park~s~.

[F4_T2:FARLIE4]
~s~Get the car back in one piece!

[F4_D:FARLIE4]
Damage:

[F4_F:FARLIE4]
~r~The car has been destroyed!

[F4_T4:FARLIE4]
~s~Get back in the ~b~car!

[F4_T5:FARLIE4]
It's an ambush, the Vietnamese gang are covering the exits!

[F4_T7:FARLIE4]
~s~Get the ~b~car~s~!

[F4_T8:FARLIE4]
~s~Take the car back to the ~y~garage~s~.

[F4_T9:FARLIE4]
~s~Dodge the enemy ~r~bikes~s~!



*/