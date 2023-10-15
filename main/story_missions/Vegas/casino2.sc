MISSION_START

// *****************************************************************************************
// ******************************* Casino 2 - Kickstart Quarry *****************************
// *****************************************************************************************
// *************************************** Paul Davis **************************************
// *****************************************************************************************

SCRIPT_NAME CASINO2

// Mission start stuff

GOSUB mission_start_casino2


IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino2_failed
ENDIF

GOSUB mission_cleanup_casino2

MISSION_END

{ 

// Variables for mission

LVAR_INT t v
LVAR_INT message_once
LVAR_INT crate_smash[4]
LVAR_INT everyone_is_fleeing
LVAR_INT called_security

LVAR_INT quarry_worker[10] c2_decision c2_breakpoint

LVAR_INT seq_machine seq_patrol1 seq_patrol2 seq_patrol3 seq_pissed

LVAR_INT cas2_gate1 cas2_gate2

LVAR_INT security_guards[10] seq_security security_van_1 security_van_2 security_van_3 c2_sign1
			
LVAR_INT c2_sign c2_security_decision

LVAR_INT on_way_home in_dumper c2_dumper_is_safe

LVAR_INT quarry_dirt_bike pd_cut_car

LVAR_FLOAT dynamite_x[4] dynamite_y[4] dynamite_z[4] dynamite_h[4] dynamite_offset 
 
LVAR_INT dynamite_blip[4] dynamite_collected[4] dynamite_pick_up[4]

LVAR_INT gun_box[4] gun_box_broken[4] broken_box[4] barrier

LVAR_INT c2_box_count c2_decision_none c2_in_range_of_worker

LVAR_INT c2_worker_yell c2_rnd

LVAR_INT c2_cutscene_playing c2_blip[6] 

LVAR_FLOAT c2_blip_x[6] c2_blip_y[6] c2_blip_z[6] 

LVAR_FLOAT c2_arrow_x[6] c2_arrow_y[6] c2_arrow_z[6] 

LVAR_INT c2_check_point[6] c2_intro_quarry[5] c2_seq_entrance

LVAR_INT c2_quarry_blip c2_forklift c2_crate_dropped

LVAR_INT c2_kill_timer 

LVAR_INT c2_seq_forklift

LVAR_INT c2_boxpile	c2_bike_blip c2_store_car

LVAR_INT c2_been_damaged c2_fx c2_in_dumper c2_plunger

LVAR_INT c2_plunger_man c2_fx_cut[4] c2_dont_display

LVAR_INT c2_final_blip seq_patrol4 c2_seq_flee[5] 

LVAR_INT c2_seq_jump 

LVAR_INT c2_backup c2_crate_fx

LVAR_INT c2_seq_lookabout c2_seq_crouch

LVAR_INT c2_on_bike c2_stand_and_shoot 

LVAR_FLOAT c2_ply_X c2_ply_Y c2_ply_Z

LVAR_INT c2_attack_the_player_1 c2_attack_the_player_2 

LVAR_INT c2_end_truck c2_end_guy c2_collect_the_dynamite c2_courier_txt

LVAR_INT c2_given_guns c2_cut_dynamite_A c2_cut_dynamite_B c2_cut_dynamite_C

VAR_INT c2_time_left c2_cp

LVAR_FLOAT c2_temp_X c2_temp_Y c2_temp_Z

LVAR_INT c2_plunger_txt	  

LVAR_INT c2_jumped_fence

LVAR_INT c2_dyna_rock[10] 

LVAR_INT c2_playing c2_audio

LVAR_INT c2_last_played

LVAR_INT c2_break_loop

LVAR_INT c2_seq_crate_a c2_seq_crate_b

LVAR_INT c2_decision_norm

LVAR_INT c2_dumper_gen

LVAR_INT c2_dumper

LVAR_INT c2_get_back_on_bike

LVAR_INT c2_TIMERC 

LVAR_INT c2_inside_quarry

LVAR_INT c2_left_on_bike

LVAR_INT c2_been_damaged_2

LVAR_INT c2_second_bike

LVAR_INT c2_audio_3	c2_playing_3

LVAR_INT c2_can_see

LVAR_TEXT_LABEL c2_print

LVAR_FLOAT c2_z

// **************************************** Mission Start *********************************

mission_start_casino2:

LOAD_MISSION_TEXT CASINO2

disable_all_cranes = 1

c2_time_left = 0

c2_cp = 0

SET_FADING_COLOUR 0 0 0

c2_blip_x[0] = 542.6064
c2_blip_y[0] = 880.7817
c2_blip_z[0] = -36.4105

c2_blip_x[1] = 585.3983
c2_blip_y[1] = 870.3217
c2_blip_z[1] = -38.6206

c2_blip_x[2] = 518.1696
c2_blip_y[2] = 814.9295
c2_blip_z[2] = -9.7489
	      
c2_blip_x[3] = 484.6667
c2_blip_y[3] = 937.2839
c2_blip_z[3] = -16.6201

c2_blip_x[4] = 438.5849
c2_blip_y[4] = 870.4443
c2_blip_z[4] = -4.2640
	  
c2_blip_x[5] = 455.1732 
c2_blip_y[5] = 803.1501
c2_blip_z[5] = 5.3843
 
c2_arrow_x[0] = 561.1495  
c2_arrow_y[0] = 875.8472 
c2_arrow_z[0] = -37.2976 

c2_arrow_x[1] = 585.9339 
c2_arrow_y[1] = 851.1839 
c2_arrow_z[1] = -36.1799 

c2_arrow_x[2] = 464.2791 
c2_arrow_y[2] = 860.6772 
c2_arrow_z[2] = -17.3952 

c2_arrow_x[3] = 443.1031 
c2_arrow_y[3] = 902.1370 
c2_arrow_z[3] = -7.5636

c2_arrow_x[4] = 429.2553 
c2_arrow_y[4] = 923.7551 
c2_arrow_z[4] = 0.5245 

c2_arrow_x[5] = 476.2539 
c2_arrow_y[5] = 779.3841 
c2_arrow_z[5] = 8.0929 

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************
 
//GOSUB casino2_fade_out

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK c2_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY c2_decision_none

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_NORM c2_decision_norm

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH c2_security_decision

// *****************************************************************************************

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION2

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_MISSION1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_MISSION2

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
	
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

// *****************************************************************************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

LOAD_CUTSCENE CAS_2
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 11
 
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

SUPPRESS_CAR_MODEL dumper

// *****************************************************************************************
 
IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO

	LOAD_SCENE 2028.9316 1023.2923 9.8127 

	CLEAR_AREA 2028.9316 1023.2923 9.8127  20.0 TRUE

	SET_CHAR_COORDINATES scplayer 2028.9316 1023.2923 9.8127 

	SET_CHAR_HEADING scplayer 278.4092

	SET_CAMERA_BEHIND_PLAYER

    WAIT 500

ENDIF

// *****************************************************************************************

REQUEST_MODEL BMYCON

WHILE NOT HAS_MODEL_LOADED BMYCON
	WAIT 0
ENDWHILE 

// * Cue Cutscene ****************************************************************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_IN	
							 
SET_PLAYER_CONTROL player1 ON

// *****************************************************************************************

// *******************************************************************************************
// *																						 *
// *                                   Driving to the Quarry								 *
// *							  															 *
// *******************************************************************************************
								     
CREATE_CHAR PEDTYPE_CIVMALE BMYCON 819.7070 857.8054 11.0547 c2_intro_quarry[2]

SET_CHAR_HEADING c2_intro_quarry[2] 204.1414   

SET_CHAR_DECISION_MAKER	c2_intro_quarry[2] c2_decision

// *******************************************************************************************

CREATE_OBJECT des_quarrygate2 806.5 846.9 8.6822 cas2_gate1
  
SET_OBJECT_HEADING cas2_gate1 0.0 

CREATE_OBJECT des_quarrygate 810.3 838.6 8.6822 cas2_gate2

SET_OBJECT_HEADING cas2_gate2 45.0 

ADD_BLIP_FOR_COORD 811.1817 843.9733 8.9859 c2_quarry_blip

PRINT_NOW (PD_16 ) 8000 1 // ~s~Go to the ~y~Quarry~s~.

CREATE_OBJECT gunbox 600.8789 826.5641 -44.2392 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 131.8763   

CREATE_OBJECT gunbox 599.4742 827.0499 -44.2844 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 129.5516 

CREATE_OBJECT gunbox 600.8787 828.1313 -44.271 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 72.6773   

CREATE_OBJECT gunbox 671.1311 816.2497 -43.9456 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 280.6081   

CREATE_OBJECT gunbox 672.9714 817.7601 -43.9456 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 295.8181 

CREATE_OBJECT gunbox 673.1854 817.8634 -43.9456 c2_boxpile
  
SET_OBJECT_HEADING c2_boxpile 295.8181 

// ************************************************************************************************

SET_WANTED_MULTIPLIER 0.0

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0
	
	GOSUB casino2_keys

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S

    	GOTO mission_casino2_passed	  

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 811.1817 843.9733 8.9859 4.0 4.0 4.0 TRUE

		SET_PLAYER_CONTROL player1 OFF
					
		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		REMOVE_BLIP c2_quarry_blip
		
		IF IS_CHAR_IN_ANY_CAR scplayer

			STORE_CAR_CHAR_IS_IN scplayer pd_cut_car

			SET_CAR_STATUS pd_cut_car STATUS_PHYSICS

			SET_CAR_TEMP_ACTION pd_cut_car TEMPACT_HANDBRAKETURNRIGHT 1500

		ENDIF

		GOTO d2_start_the_mission

	ENDIF

ENDWHILE

WAIT 1000

GOTO mission_casino2_failed

d2_start_the_mission:

dynamite_x[0] = 662.1465    
dynamite_y[0] = 917.9582 
dynamite_z[0] = -41.8491
dynamite_h[0] = 318.2808    
 
dynamite_x[1] = 583.0025  
dynamite_y[1] = 934.0627 
dynamite_z[1] = -43.9531 
dynamite_h[1] = 114.8609
  
dynamite_x[2] = 552.9266 
dynamite_y[2] = 846.8138 
dynamite_z[2] = -42.8758
dynamite_h[2] = 197.0568 

dynamite_x[3] = 640.8603 
dynamite_y[3] = 810.4040
dynamite_z[3] = -43.9531
dynamite_h[3] = 250.0977   

c2_playing = 2

c2_playing_3 = 2

REQUEST_MODEL cellphone
REQUEST_MODEL wmyconb
REQUEST_MODEL wmysgrd
REQUEST_MODEL BMYCON
REQUEST_MODEL COLT45
REQUEST_MODEL shovel
REQUEST_MODEL FORKLIFT
REQUEST_MODEL DUMPER

WHILE NOT HAS_MODEL_LOADED cellphone
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED wmyconb
OR NOT HAS_MODEL_LOADED wmysgrd
OR NOT HAS_MODEL_LOADED BMYCON
OR NOT HAS_MODEL_LOADED COLT45
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED shovel
OR NOT HAS_MODEL_LOADED FORKLIFT
OR NOT HAS_MODEL_LOADED DUMPER
	WAIT 0
ENDWHILE

// The One Dumper Truck ***********************************************************************************

CREATE_CAR DUMPER 684.1580 899.4011 -41.382 c2_dumper

SET_CAR_HEADING c2_dumper 50.0

//CREATE_CAR_GENERATOR 684.1580 899.4011 -41.382 50.0 dumper -1 -1 TRUE 0 0 0 10000 c2_dumper_gen // Dumper truck 

//SWITCH_CAR_GENERATOR c2_dumper_gen 101

// Sequences **********************************************************************************************


OPEN_SEQUENCE_TASK c2_seq_crate_a
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 661.3970 917.1706 -41.4059 PEDMOVE_WALK -1
	TASK_PLAY_ANIM -1 WEAPON_crouch PED 1.0 TRUE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT c2_seq_crate_a 1

CLOSE_SEQUENCE_TASK c2_seq_crate_a

OPEN_SEQUENCE_TASK c2_seq_crate_b
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 662.6050 916.3130 -41.4059 PEDMOVE_WALK -1
	TASK_PLAY_ANIM -1 IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT c2_seq_crate_b 1

CLOSE_SEQUENCE_TASK c2_seq_crate_b

OPEN_SEQUENCE_TASK c2_seq_crouch
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 661.3970 917.1706 -41.4059 PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT c2_seq_crouch 1

CLOSE_SEQUENCE_TASK c2_seq_crouch

OPEN_SEQUENCE_TASK c2_backup
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 590.1505 875.5482 -43.4973 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 40000
	SET_SEQUENCE_TO_REPEAT c2_backup 1

CLOSE_SEQUENCE_TASK c2_backup

OPEN_SEQUENCE_TASK seq_security							  

 	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK seq_security

OPEN_SEQUENCE_TASK seq_pissed	

	TASK_GO_TO_COORD_ANY_MEANS -1 416.7344 894.5847 1.6400 PEDMOVE_RUN -1

CLOSE_SEQUENCE_TASK seq_pissed

OPEN_SEQUENCE_TASK c2_seq_entrance

	TASK_GO_TO_COORD_ANY_MEANS -1 811.1705 840.3314 8.8556 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 835.0692 854.2280 11.5339 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 831.1895 869.6328 11.7659 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 812.9305 865.5474 11.2512 PEDMOVE_WALK -1

CLOSE_SEQUENCE_TASK c2_seq_entrance

OPEN_SEQUENCE_TASK c2_stand_and_shoot

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_SHOOT_AT_CHAR -1 scplayer -1

	SET_SEQUENCE_TO_REPEAT c2_stand_and_shoot 1

CLOSE_SEQUENCE_TASK c2_stand_and_shoot

OPEN_SEQUENCE_TASK c2_seq_flee[0]

	FLUSH_ROUTE

	EXTEND_ROUTE 652.6294 920.1244 -41.6245 

	EXTEND_ROUTE 643.2743 944.6199 -36.1984  

	EXTEND_ROUTE 650.2857 948.2803 -35.9383
  
	EXTEND_ROUTE 676.3673 939.0834 -31.2018
  
	EXTEND_ROUTE 695.1881 918.9306 -31.3478

	EXTEND_ROUTE 709.0739 891.5104 -28.7325

	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE

	FLUSH_ROUTE
  
	EXTEND_ROUTE 728.9528 889.2318 -27.4689
  
	EXTEND_ROUTE 718.7523 918.5203 -19.8860 

	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE

	FLUSH_ROUTE

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer

CLOSE_SEQUENCE_TASK c2_seq_flee[0]

OPEN_SEQUENCE_TASK seq_machine

	TASK_STAND_STILL -1 4000
	TASK_SCRATCH_HEAD -1
	TASK_LOOK_ABOUT -1 4000
	SET_SEQUENCE_TO_REPEAT seq_machine 1

CLOSE_SEQUENCE_TASK seq_machine

OPEN_SEQUENCE_TASK seq_patrol1

	TASK_GO_TO_COORD_ANY_MEANS -1 625.9337 831.7066 -43.9531 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 2000
	TASK_GO_TO_COORD_ANY_MEANS -1 629.7086 863.9051 -43.9456 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 2000
	TASK_GO_TO_COORD_ANY_MEANS -1 633.3620 899.4764 -43.8397 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 2000
	SET_SEQUENCE_TO_REPEAT seq_patrol1 1

CLOSE_SEQUENCE_TASK seq_patrol1

OPEN_SEQUENCE_TASK seq_patrol2

	TASK_GO_TO_COORD_ANY_MEANS -1 531.6239 860.3109 -43.6083 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 536.4184 852.4755 -43.4897 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 548.7656 858.6099 -43.8300 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 544.7462 868.4120 -43.0847 PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT seq_patrol2 1

CLOSE_SEQUENCE_TASK seq_patrol2

OPEN_SEQUENCE_TASK seq_patrol3

	TASK_GO_TO_COORD_ANY_MEANS -1 580.5311 830.0402 -30.9456 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 2000
	TASK_GO_TO_COORD_ANY_MEANS -1 570.5531 828.2956 -26.8808  PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 561.6993 826.1243 -23.1406 PEDMOVE_WALK -1
	TASK_LOOK_ABOUT -1 2000
	SET_SEQUENCE_TO_REPEAT seq_patrol3 1

CLOSE_SEQUENCE_TASK seq_patrol3

OPEN_SEQUENCE_TASK seq_patrol4

	TASK_GO_TO_COORD_ANY_MEANS -1 576.2826 880.9235 -44.6488 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 575.4687 871.9396 -44.6139 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 577.0265 862.7321 -44.0826 PEDMOVE_WALK -1
	TASK_GO_TO_COORD_ANY_MEANS -1 584.0759 844.0593 -43.4072 PEDMOVE_WALK -1

	SET_SEQUENCE_TO_REPEAT seq_patrol4 1

CLOSE_SEQUENCE_TASK seq_patrol4

OPEN_SEQUENCE_TASK c2_seq_jump

	FLUSH_ROUTE
		
	EXTEND_ROUTE 473.5352 916.5417 -19.6808 
	EXTEND_ROUTE 467.0137 925.6787 -19.4363 
	EXTEND_ROUTE 476.3217 939.0852 -17.1781 
	EXTEND_ROUTE 493.3413 945.0632 -16.4974 
	EXTEND_ROUTE 508.5752 956.9808 -15.2792 
	EXTEND_ROUTE 493.3413 945.0632 -16.4974 
	EXTEND_ROUTE 476.3217 939.0852 -17.1781 
	EXTEND_ROUTE 467.0137 925.6787 -19.4363 

	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE

	FLUSH_ROUTE

	SET_SEQUENCE_TO_REPEAT c2_seq_jump 1

CLOSE_SEQUENCE_TASK c2_seq_jump

OPEN_SEQUENCE_TASK c2_attack_the_player_1

	TASK_GO_TO_COORD_ANY_MEANS -1 420.7673 866.2073 5.7531 PEDMOVE_RUN -1

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_SHOOT_AT_CHAR -1 scplayer -1

CLOSE_SEQUENCE_TASK c2_attack_the_player_1

OPEN_SEQUENCE_TASK c2_attack_the_player_2

	TASK_GO_TO_COORD_ANY_MEANS -1 413.9022 863.8785 7.0415 PEDMOVE_RUN -1

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_SHOOT_AT_CHAR -1 scplayer -1

CLOSE_SEQUENCE_TASK c2_attack_the_player_2

// *******************************************************************************************************************
 
CREATE_CHAR PEDTYPE_MISSION1 wmyconb 590.3660 876.7747 -43.4973 c2_plunger_man

SET_CHAR_HEADING c2_plunger_man 177.7406    

SET_CHAR_DECISION_MAKER	c2_plunger_man c2_decision_norm

GIVE_WEAPON_TO_CHAR c2_plunger_man WEAPONTYPE_PISTOL 30000

SET_CURRENT_CHAR_WEAPON c2_plunger_man WEAPONTYPE_PISTOL

SET_CHAR_HEALTH c2_plunger_man 200

SET_CHAR_MAX_HEALTH c2_plunger_man 200

TASK_STAY_IN_SAME_PLACE c2_plunger_man TRUE

TASK_USE_MOBILE_PHONE c2_plunger_man TRUE

// *******************************************************************************************************************

CREATE_OBJECT CJ_Dyn_Plunge_1 591.2704 876.7986 -43.4973 c2_plunger
  
SET_OBJECT_HEADING c2_plunger 270.0000

// *******************************************************************************************************************

CREATE_CAR FORKLIFT 640.8603 810.4040 -43.9531 c2_forklift

SET_CAR_HEADING c2_forklift 250.0977

CONTROL_MOVABLE_VEHICLE_PART c2_forklift 0.5

//SET_CAR_HEAVY c2_forklift TRUE 
    
// *******************************************************************************************************************

OPEN_SEQUENCE_TASK c2_seq_forklift

	IF NOT IS_CAR_DEAD c2_forklift

		FLUSH_ROUTE

		EXTEND_ROUTE 667.7962 819.8148 -43.9456

		EXTEND_ROUTE 655.5785 831.1092 -43.9456 

		EXTEND_ROUTE 643.8084 819.2715 -43.9456

		EXTEND_ROUTE 605.1934 820.2333 -44.0023  

		TASK_DRIVE_POINT_ROUTE_ADVANCED -1 c2_forklift 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

		FLUSH_ROUTE

		EXTEND_ROUTE 608.6918 819.8881 -43.9456  

		EXTEND_ROUTE 643.8084 819.2715 -43.9456

		EXTEND_ROUTE 655.5785 831.1092 -43.9456 

		EXTEND_ROUTE 667.7962 819.8148 -43.9456

		TASK_DRIVE_POINT_ROUTE_ADVANCED -1 c2_forklift 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

		FLUSH_ROUTE

		SET_SEQUENCE_TO_REPEAT c2_seq_forklift 1

	ENDIF
 
CLOSE_SEQUENCE_TASK c2_seq_forklift 

// ******************************************************************************************************
								   
CREATE_CHAR PEDTYPE_MISSION1 BMYCON 625.9337 831.7066 -43.9531 quarry_worker[0]

SET_CHAR_HEADING quarry_worker[0] 156.9796
SET_CHAR_DECISION_MAKER	quarry_worker[0] c2_decision
PERFORM_SEQUENCE_TASK quarry_worker[0] seq_patrol1

CREATE_CHAR PEDTYPE_MISSION2 BMYCON 531.6239 860.3109 -43.6083 quarry_worker[1]

SET_CHAR_HEADING quarry_worker[1] 156.9796 
SET_CHAR_DECISION_MAKER	quarry_worker[1] c2_security_decision

GIVE_WEAPON_TO_CHAR quarry_worker[1] WEAPONTYPE_SHOVEL 30000

SET_CURRENT_CHAR_WEAPON quarry_worker[1] WEAPONTYPE_SHOVEL

PERFORM_SEQUENCE_TASK quarry_worker[1] seq_patrol2

CREATE_CHAR PEDTYPE_MISSION1 BMYCON 637.8237 825.2736 -41.2744 quarry_worker[2]

SET_CHAR_HEADING quarry_worker[2] 309.4228

SET_CHAR_DECISION_MAKER	quarry_worker[2] c2_decision

CREATE_CHAR PEDTYPE_MISSION1 BMYCON 638.5316 826.1287 -41.2291 quarry_worker[3]

SET_CHAR_HEADING quarry_worker[3] 144.6359
      
SET_CHAR_DECISION_MAKER	quarry_worker[3] c2_decision

CREATE_CHAR PEDTYPE_MISSION1 BMYCON 583.0614 849.4081 -43.5974 quarry_worker[4]

SET_CHAR_HEADING quarry_worker[4] 26.0656    
SET_CHAR_DECISION_MAKER	quarry_worker[4] c2_decision

PERFORM_SEQUENCE_TASK quarry_worker[4] seq_patrol4
   
CREATE_CHAR PEDTYPE_MISSION2 wmysgrd 491.5784 940.8132 -17.1478 quarry_worker[6]

GIVE_WEAPON_TO_CHAR quarry_worker[6] WEAPONTYPE_PISTOL 30000

SET_CURRENT_CHAR_WEAPON quarry_worker[6] WEAPONTYPE_PISTOL

SET_CHAR_HEADING quarry_worker[6] 279.9455
 
SET_CHAR_DECISION_MAKER	quarry_worker[6] c2_security_decision

PERFORM_SEQUENCE_TASK quarry_worker[6] c2_seq_jump
								
// *****************************************************************************************************
// *																								   *
// *								      Guy protecting boss		 								   *
// *																								   *	 
// *****************************************************************************************************

CREATE_CHAR PEDTYPE_MISSION2 wmysgrd 590.1505 875.5482 -43.4973 quarry_worker[7]

SET_CHAR_HEADING quarry_worker[7] 278.2997
 
SET_CHAR_DECISION_MAKER	quarry_worker[7] c2_security_decision

GIVE_WEAPON_TO_CHAR quarry_worker[7] WEAPONTYPE_PISTOL 30000

SET_CURRENT_CHAR_WEAPON quarry_worker[7] WEAPONTYPE_PISTOL

PERFORM_SEQUENCE_TASK quarry_worker[7] c2_backup
								
// *****************************************************************************************************
// *																								   *
// *								  Guys at crate during cutscene									   *
// *																								   *	 
// *****************************************************************************************************
										   
CREATE_CHAR PEDTYPE_MISSION1 BMYCON 655.8689 904.3282 -42.0397 quarry_worker[8]

SET_CHAR_HEADING quarry_worker[8] 330.4476

SET_CHAR_DECISION_MAKER	quarry_worker[8] c2_decision

CREATE_CHAR PEDTYPE_MISSION1 BMYCON 654.8843 906.4748 -42.0822 quarry_worker[9]

SET_CHAR_HEADING quarry_worker[9] 324.1920	

GIVE_WEAPON_TO_CHAR quarry_worker[9] WEAPONTYPE_SHOVEL 30000

SET_CURRENT_CHAR_WEAPON quarry_worker[9] WEAPONTYPE_SHOVEL
 
SET_CHAR_DECISION_MAKER	quarry_worker[9] c2_decision

REPEAT 10 v

	IF NOT IS_CHAR_DEAD quarry_worker[v]

		SET_SENSE_RANGE quarry_worker[v] 15.0

	ENDIF

ENDREPEAT 

IF NOT IS_CAR_DEAD c2_forklift

	CREATE_CHAR_INSIDE_CAR c2_forklift PEDTYPE_MISSION1 BMYCON c2_intro_quarry[3]

	SET_CHAR_DECISION_MAKER	c2_intro_quarry[3] c2_decision

	PERFORM_SEQUENCE_TASK c2_intro_quarry[3] c2_seq_forklift

ENDIF

// *******************************************************************************************************
									
SET_CAMERA_BEHIND_PLAYER

REPEAT 4 v

	CREATE_OBJECT_NO_OFFSET Dynamite dynamite_x[v] dynamite_y[v] dynamite_z[v] dynamite_pick_up[v]

	SET_OBJECT_PROOFS dynamite_pick_up[v] FALSE FALSE FALSE FALSE FALSE

	CREATE_OBJECT_NO_OFFSET dyno_box_A dynamite_x[v] dynamite_y[v] dynamite_z[v] broken_box[v]

 	SET_OBJECT_COLLISION dynamite_pick_up[v] FALSE

	SET_OBJECT_HEADING broken_box[v] dynamite_h[v]

	CREATE_OBJECT_NO_OFFSET dyno_box_B dynamite_x[v] dynamite_y[v] dynamite_z[v] gun_box[v]

	SET_OBJECT_HEADING gun_box[v] dynamite_h[v]

	ADD_BLIP_FOR_OBJECT dynamite_pick_up[v] dynamite_blip[v]

	CHANGE_BLIP_DISPLAY dynamite_blip[v] BOTH
	MAKE_OBJECT_TARGETTABLE gun_box[v] FALSE
	MAKE_OBJECT_TARGETTABLE dynamite_pick_up[v] FALSE
	MAKE_OBJECT_TARGETTABLE broken_box[v] FALSE

	SET_OBJECT_HEALTH gun_box[v] 1000

ENDREPEAT

//ATTACH_OBJECT_TO_CAR dynamite_pick_up[3] c2_forklift -0.6 0.4 0.0 0.0 90.0 90.0

ATTACH_OBJECT_TO_OBJECT dynamite_pick_up[3] gun_box[3] 0.0 0.0 0.4 0.0 0.0 0.0

ATTACH_OBJECT_TO_OBJECT broken_box[3] gun_box[3] 0.0 0.0 0.0 0.0 0.0 0.0

ATTACH_OBJECT_TO_CAR gun_box[3] c2_forklift 0.0 0.8 -0.3 0.0 0.0 0.0

CREATE_OBJECT privatesign3 470.3749 965.7881 4.5134 c2_sign

SET_OBJECT_HEADING c2_sign 311.2527

CREATE_OBJECT privatesign1 546.1226 879.7054 -42.1212 c2_sign1

SET_OBJECT_HEADING c2_sign1 76.9240 

ADD_BLIP_FOR_COORD 542.6064 880.7817 -36.3928 c2_blip[0]

CHANGE_BLIP_DISPLAY c2_blip[0] NEITHER  

ADD_BLIP_FOR_COORD 585.3983 870.3217 -39.4206 c2_blip[1] 

CHANGE_BLIP_DISPLAY c2_blip[1] NEITHER  

ADD_BLIP_FOR_COORD 518.1917 814.9812 -9.7609 c2_blip[2] 

CHANGE_BLIP_DISPLAY c2_blip[2] NEITHER  

ADD_BLIP_FOR_COORD 479.1757 930.0793 -16.6475 c2_blip[3] 

CHANGE_BLIP_DISPLAY c2_blip[3] NEITHER  

ADD_BLIP_FOR_COORD 437.9441 869.2394 -4.0196 c2_blip[4] 

CHANGE_BLIP_DISPLAY c2_blip[4] NEITHER  

ADD_BLIP_FOR_COORD 455.1732 803.1501 5.3872 c2_blip[5] 

CHANGE_BLIP_DISPLAY c2_blip[5] NEITHER

ADD_BLIP_FOR_COORD 1030.1570 744.3469 9.7922 c2_final_blip

CHANGE_BLIP_DISPLAY c2_final_blip NEITHER

IF NOT IS_CHAR_DEAD scplayer

	IF IS_CHAR_IN_ANY_CAR scplayer

		STORE_CAR_CHAR_IS_IN scplayer pd_cut_car

		SET_CAR_STATUS pd_cut_car STATUS_PHYSICS

		SET_CAR_TEMP_ACTION pd_cut_car TEMPACT_HANDBRAKETURNRIGHT 1500

	ENDIF

ENDIF 

GOSUB casino2_set_camera

IF NOT IS_CHAR_DEAD scplayer

	IF IS_CHAR_IN_ANY_CAR scplayer

		SET_CAR_COORDINATES pd_cut_car 777.9584 838.6478 4.8610

		SET_CAR_HEADING pd_cut_car 0.0000 

		WARP_CHAR_FROM_CAR_TO_COORD scplayer 774.3173 835.6580 4.8819

	ELSE
	
		SET_CHAR_COORDINATES scplayer 775.5965 834.8624 4.8713				

	ENDIF

	TASK_GO_TO_COORD_ANY_MEANS scplayer 771.6103 836.6703 4.8969 PEDMOVE_WALK -1

	SET_CHAR_HEADING scplayer 87.4579 

ENDIF

LOAD_SCENE_IN_DIRECTION 773.9383 834.7843 4.8835 74.4105 

SET_FIXED_CAMERA_POSITION 776.3613 837.3477 7.3533 0.0 0.0 0.0 // Crate Cut
POINT_CAMERA_AT_POINT 775.4211 837.4819 7.0403 JUMP_CUT

WAIT 500
						   
GOSUB casino2_fade_in

PRINT_NOW ( PD_01 ) 4500 1 // Enter the Quarry and steal the dynamite.

TIMERA = 0
WHILE TIMERA < 4500
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
		GOTO c2_skip_the_cutscene
	ENDIF
ENDWHILE

LOAD_SCENE_IN_DIRECTION 610.9529 869.6495 -40.7211 77.0000 

SET_FIXED_CAMERA_POSITION 610.9529 869.6495 -40.7211 0.0 0.0 0.0 // Crate Cut
POINT_CAMERA_AT_POINT 610.0316 870.0207 -40.8371 JUMP_CUT

PRINT_NOW ( PD_20 ) 5000 1 // The dynamite is rigged to blow!

TIMERA = 0
WHILE TIMERA < 2000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
		GOTO c2_skip_the_cutscene
	ENDIF
ENDWHILE

IF NOT IS_CHAR_DEAD quarry_worker[8]
AND NOT IS_CHAR_DEAD quarry_worker[9]

	PERFORM_SEQUENCE_TASK quarry_worker[8] c2_seq_crate_b

	PERFORM_SEQUENCE_TASK quarry_worker[9] c2_seq_crate_a

ENDIF

TIMERA = 0
WHILE TIMERA < 3000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
		GOTO c2_skip_the_cutscene
	ENDIF
ENDWHILE

SET_FIXED_CAMERA_POSITION 664.9276 918.5754 -40.8955 0.0 0.0 0.0 // Crate Cut
POINT_CAMERA_AT_POINT 663.9788 918.2599 -40.9078 JUMP_CUT

PRINT_NOW ( PD_21 ) 4000 1 // Get it before the workers destroy it.

IF NOT IS_CHAR_DEAD quarry_worker[3] 

	TASK_PLAY_ANIM quarry_worker[3] IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1  

ENDIF

TIMERA = 0
WHILE TIMERA < 4000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
		GOTO c2_skip_the_cutscene
	ENDIF
ENDWHILE 

SET_FIXED_CAMERA_POSITION 661.5589 921.2776 -40.5538 0.0 0.0 0.0 // Crate Cut
POINT_CAMERA_AT_POINT 661.9819 920.3747 -40.6291 JUMP_CUT

PRINT_NOW ( PD_27 ) 6000 1 // You'll need something heavy to smash those crates!
 
TIMERA = 0
WHILE TIMERA < 6000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
		GOTO c2_skip_the_cutscene
	ENDIF
ENDWHILE

c2_skip_the_cutscene:

IF NOT IS_CHAR_DEAD quarry_worker[8]
AND NOT IS_CHAR_DEAD quarry_worker[9]

	PERFORM_SEQUENCE_TASK quarry_worker[8] c2_seq_crate_b

	PERFORM_SEQUENCE_TASK quarry_worker[9] c2_seq_crate_a

ENDIF

IF NOT IS_CHAR_DEAD quarry_worker[3]

	TASK_PLAY_ANIM quarry_worker[3] IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1	

ENDIF	

GOSUB casino2_fade_out

GOSUB casino2_restore_camera

IF NOT IS_CHAR_DEAD scplayer

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

  	SET_CHAR_COORDINATES scplayer 775.4108 834.5572 4.8721  

  	SET_CHAR_HEADING scplayer 93.2533  

ENDIF
 
GOSUB casino2_fade_in

CLEAR_PRINTS

c2_time_left = 150000

//DISPLAY_ONSCREEN_TIMER_WITH_STRING c2_time_left TIMER_DOWN BB_19  // Time

DISPLAY_ONSCREEN_TIMER_WITH_STRING c2_time_left TIMER_DOWN PD_25
 
// Construction Workers X 13 ****************************************************************************

GOTO c2_main_loop

// *****************************************************************************************
// *																					   *
// *                             All Dynamite Collected 				   				   *
// *																					   *
// *****************************************************************************************

cavalry_arrives:

	REQUEST_MODEL TRIADA
	REQUEST_MODEL manana
	REQUEST_ANIMATION CARRY

	WHILE NOT HAS_MODEL_LOADED TRIADA
	OR NOT HAS_MODEL_LOADED manana
	OR NOT HAS_ANIMATION_LOADED CARRY
		WAIT 0
	ENDWHILE
		
	CREATE_CHAR PEDTYPE_MISSION2 wmysgrd 580.5311 830.0402 -30.9456 quarry_worker[5]

	SET_CHAR_HEADING quarry_worker[5] 57.3299 
	SET_CHAR_DECISION_MAKER	quarry_worker[5] c2_security_decision

	GIVE_WEAPON_TO_CHAR quarry_worker[5] WEAPONTYPE_PISTOL 30000

	SET_CURRENT_CHAR_WEAPON quarry_worker[5] WEAPONTYPE_PISTOL

	PERFORM_SEQUENCE_TASK quarry_worker[5] seq_patrol3	

	CLEAR_AREA 1032.6781 741.7072 9.8044 10.0 TRUE

	CREATE_CAR manana 1032.6781 741.7072 9.8044 c2_end_truck

	LOCK_CAR_DOORS c2_end_truck CARLOCK_LOCKED

	FREEZE_CAR_POSITION c2_end_truck TRUE

	POP_CAR_BOOT c2_end_truck

	SET_CAR_HEADING c2_end_truck 292.6477

	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 1029.1730 740.8435 9.8018 c2_end_guy

	SET_CHAR_HEADING c2_end_guy 63.6493

	SET_CHAR_SUFFERS_CRITICAL_HITS c2_end_guy FALSE

	SET_CHAR_NEVER_TARGETTED c2_end_guy TRUE

	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	c2_end_guy TRUE

	SET_CHAR_DECISION_MAKER c2_end_guy c2_decision_none

	REQUEST_MODEL securica
	REQUEST_MODEL CHROMEGUN

	WHILE NOT HAS_MODEL_LOADED securica
	OR NOT HAS_MODEL_LOADED CHROMEGUN

											 
		WAIT 0

	ENDWHILE

	CLEAR_AREA 812.6123 845.2352 9.1419 20.0 TRUE

	CREATE_CAR securica 812.6123 845.2352 9.1419 security_van_1

	SET_CAR_HEADING security_van_1 101.1888  

	CREATE_CAR securica 812.4752 840.8808 8.9765 security_van_2 

	SET_CAR_HEADING security_van_2 101.8471  
 
	CREATE_CAR securica 422.5194 856.2830 6.1078 security_van_3 

	SET_CAR_HEADING security_van_3 33.0215 

	// The Quarry Dirt Bike *********************************************************************************

	CREATE_CHAR_INSIDE_CAR security_van_1 PEDTYPE_MISSION2 wmysgrd security_guards[0]
	CREATE_CHAR_AS_PASSENGER security_van_1 PEDTYPE_MISSION2 wmysgrd 0 security_guards[1] 
	 
	CREATE_CHAR_INSIDE_CAR security_van_2 PEDTYPE_MISSION2 wmysgrd security_guards[2]
	CREATE_CHAR_AS_PASSENGER security_van_2 PEDTYPE_MISSION2 wmysgrd 0 security_guards[3] 

	REPEAT 4 v

	  	GIVE_WEAPON_TO_CHAR security_guards[v] WEAPONTYPE_SHOTGUN 30000

	  	SET_CURRENT_CHAR_WEAPON security_guards[v] WEAPONTYPE_SHOTGUN

		SET_CHAR_DECISION_MAKER security_guards[v] c2_security_decision

	ENDREPEAT 

	CLEAR_AREA 783.6387 841.3950 4.8177 20.0 TRUE

	TASK_CAR_DRIVE_TO_COORD security_guards[0] security_van_1 783.6387 841.3950 4.8177 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
	 
	TASK_CAR_DRIVE_TO_COORD security_guards[2] security_van_2 781.6849 833.6969 4.8296 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

   	c2_cutscene_playing = 1

	CLEAR_PRINTS

	GOSUB casino2_fade_out

	REPEAT 10 v
		
		IF NOT v = 5
		AND NOT v = 6
		AND NOT v = 7

			IF NOT IS_CHAR_DEAD quarry_worker[v]

				DELETE_CHAR quarry_worker[v]

			ENDIF
			
		ENDIF

		IF v = 7	

			IF NOT IS_CHAR_DEAD quarry_worker[v]

				CLEAR_CHAR_TASKS_IMMEDIATELY quarry_worker[v] 

				TASK_KILL_CHAR_ON_FOOT quarry_worker[v] scplayer

			ENDIF

		ENDIF

	ENDREPEAT

	IF NOT IS_CHAR_DEAD c2_intro_quarry[2]

		DELETE_CHAR c2_intro_quarry[2]

	ENDIF

	IF NOT IS_CHAR_DEAD c2_intro_quarry[3]

		DELETE_CHAR c2_intro_quarry[3]

	ENDIF

	IF NOT IS_CHAR_DEAD c2_plunger_man

		DELETE_CHAR c2_plunger_man
	
	ENDIF

	REQUEST_MODEL sanchez

	WHILE NOT HAS_MODEL_LOADED sanchez
		WAIT 0
	ENDWHILE

	LVAR_INT c2_bike_gen c2_bike_gen1

	CREATE_CAR sanchez 583.0051 881.9482 -45.0648 quarry_dirt_bike

	SET_CAN_BURST_CAR_TYRES quarry_dirt_bike FALSE

	SET_CAR_HEADING quarry_dirt_bike 93.6785

	CLEAR_AREA 583.0051 881.9482 -45.0648 10.0 TRUE

	CLEAR_AREA 583.0051 881.9482 -45.0648 10.0 TRUE

	ADD_BLIP_FOR_CAR quarry_dirt_bike c2_bike_blip 
	
	SET_BLIP_AS_FRIENDLY c2_bike_blip TRUE  

	GOSUB casino2_set_camera

	FORCE_WEATHER_NOW WEATHER_SANDSTORM_DESERT

	SET_FIXED_CAMERA_POSITION 772.3265 828.7196 5.8497 0.0 0.0 0.0 // Pan infront of security vans
	POINT_CAMERA_AT_POINT 773.1074 829.3431 5.8867 JUMP_CUT
	
	GOSUB casino2_fade_in

	TIMERA = 0
	WHILE TIMERA < 2000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 772.3265 828.7196 5.8497 0.0 0.0 0.0 // Pan infront of security vans
	POINT_CAMERA_AT_POINT 773.1074 829.3431 5.8867 JUMP_CUT
	
	PRINT_NOW ( PD_10 ) 3500 1 // Security is blocking the exit.

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	IF NOT IS_CAR_DEAD security_van_2

		TASK_EVERYONE_LEAVE_CAR security_van_2

	ENDIF

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	IF NOT IS_CAR_DEAD security_van_1

		TASK_EVERYONE_LEAVE_CAR security_van_1

	ENDIF	

	TIMERA = 0
	WHILE TIMERA < 2000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 587.0494 883.9788 -44.1279 0.0 0.0 0.0 // Pan infront of security vans
	POINT_CAMERA_AT_POINT 586.0945 883.6844 -44.1663 JUMP_CUT

	PRINT_NOW ( PD_11 ) 4000 1 // ~s~Use the ~r~Dirt bike ~s~and find another way out!
 
	TIMERA = 0
	WHILE TIMERA < 4000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 560.5041 888.8810 -42.2954 0.0 0.0 0.0 // Dirt Bike Route
	POINT_CAMERA_AT_POINT 559.5857 888.5298 -42.1132 JUMP_CUT

	TIMERA = 0
	WHILE TIMERA < 100
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	SET_INTERPOLATION_PARAMETERS 0.0 5000

	SET_FIXED_CAMERA_POSITION 560.5041 888.8810 -42.2954 0.0 0.0 0.0 // Dirt Bike Route
	POINT_CAMERA_AT_POINT 561.1447 888.1351 -42.1132 INTERPOLATION

	TIMERA = 0
	WHILE TIMERA < 5000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE 

 	SET_FIXED_CAMERA_POSITION 584.2947 833.9013 -23.7385 0.0 0.0 0.0 // Dirt Bike Route
	POINT_CAMERA_AT_POINT 583.8419 833.3374 -24.4291 JUMP_CUT

	TIMERA = 0
	WHILE TIMERA < 100
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	SET_INTERPOLATION_PARAMETERS 0.0 4000

	SET_FIXED_CAMERA_POSITION 584.2947 833.9013 -23.7385 0.0 0.0 0.0 // Dirt Bike Route
	POINT_CAMERA_AT_POINT 583.3537 833.6394 -23.5241 INTERPOLATION

	TIMERA = 0
	WHILE TIMERA < 4000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO c2_skip_the_cutscene1
		ENDIF
	ENDWHILE

	c2_skip_the_cutscene1:

	GOSUB casino2_fade_out

	CLEAR_PRINTS

	GOSUB casino2_restore_camera

	GOSUB casino2_fade_in

	SET_OBJECT_HEADING cas2_gate1 115.0

	SET_OBJECT_HEADING cas2_gate2 -65.0

	IF NOT IS_CAR_DEAD security_van_1

		SET_CAR_COORDINATES security_van_1 783.6387 841.3950 4.8177	

	ENDIF

	IF NOT IS_CAR_DEAD security_van_2

		SET_CAR_COORDINATES security_van_2 781.6849 833.6969 4.8296	

	ENDIF
	
   	c2_cutscene_playing = 1
   				 
	CREATE_OBJECT_NO_OFFSET barrier_4andy 783.3699 847.4722 4.8193 barrier

	SET_OBJECT_HEADING barrier 10.6540 

	CREATE_OBJECT_NO_OFFSET barrier_4andy 778.0906 847.8346 4.8515 barrier

	SET_OBJECT_HEADING barrier 10.5331

	IF NOT IS_CHAR_DEAD security_guards[0] 
		IF NOT IS_CHAR_IN_ANY_CAR security_guards[0]

			SET_CHAR_COORDINATES security_guards[0] 781.9483 848.7029 4.8280 
			SET_CHAR_HEADING security_guards[0] 20.7251 

		ELSE

			WARP_CHAR_FROM_CAR_TO_COORD security_guards[0] 781.9483 848.7029 4.8280 
			SET_CHAR_HEADING security_guards[0] 20.7251 
		
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD security_guards[1] 
		IF NOT IS_CHAR_IN_ANY_CAR security_guards[1]

			SET_CHAR_COORDINATES security_guards[1] 777.5142 849.4856 4.8551 
			SET_CHAR_HEADING security_guards[1] 22.0536   

		ELSE

			WARP_CHAR_FROM_CAR_TO_COORD security_guards[1] 777.5142 849.4856 4.8551 
			SET_CHAR_HEADING security_guards[1] 22.0536 
		
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD security_guards[2] 
		IF NOT IS_CHAR_IN_ANY_CAR security_guards[2]

			SET_CHAR_COORDINATES security_guards[2] 773.2135 821.8822 4.3934  
			SET_CHAR_HEADING security_guards[2] 168.8133

		ELSE

			WARP_CHAR_FROM_CAR_TO_COORD security_guards[2] 773.2135 821.8822 4.3934 
			SET_CHAR_HEADING security_guards[2] 168.8133 
		
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD security_guards[3] 
		IF NOT IS_CHAR_IN_ANY_CAR security_guards[3]

			SET_CHAR_COORDINATES security_guards[3] 778.8481 819.9525 4.3237
			SET_CHAR_HEADING security_guards[3] 156.0701     

		ELSE

			WARP_CHAR_FROM_CAR_TO_COORD security_guards[3] 778.8481 819.9525 4.3237 
			SET_CHAR_HEADING security_guards[3] 156.0701 
		
		ENDIF
	ENDIF

 	REPEAT 4 v

		IF NOT IS_CHAR_DEAD security_guards[v] 
		
			PERFORM_SEQUENCE_TASK security_guards[v] seq_security

		ENDIF

	ENDREPEAT

RETURN

// **********************************************************************************
// *																				*
// *																				*
// *    		                  MAIN LOOP COMMENCETH								*
// *																				*
// *																				*
// **********************************************************************************

c2_main_loop:

TIMERB = 0

CLEAR_PRINTS
 
PRINT_NOW(PD_26) 7000 1 // ~s~Get the ~g~Dynamite ~s~before the timer runs out and it's detonated.

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0
	
	c2_TIMERC ++

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
		GOTO mission_casino2_passed	   

	ENDIF

	GOSUB casino2_keys

	GOSUB c2_play_sample

	GOSUB c2_play_sample_3

	IF NOT IS_CHAR_DEAD scplayer

		GET_CHAR_COORDINATES scplayer c2_ply_X c2_ply_Y c2_ply_Z	

	ENDIF

	GOSUB kill_guys_if_in_radius

	IF c2_time_left <= 22000
	AND c2_plunger_txt = 0

		IF c2_inside_quarry = 1
			c2_audio_3 = SOUND_DETONATION_SIREN
			GOSUB c2_load_sample_3
		ENDIF

		c2_plunger_txt = 1

	ENDIF

	IF c2_time_left <= 20000
	AND c2_plunger_txt = 1

		IF c2_inside_quarry = 1
			$c2_print = &CAS2_BA	// ALL PERSONEL CLEAR!
			c2_audio = SOUND_CAS2_BA
			GOSUB c2_load_sample
		ENDIF

		c2_plunger_txt = 2

	ENDIF

	IF c2_time_left <= 6000
	AND c2_plunger_txt = 2

		IF c2_inside_quarry = 1
			$c2_print = &CAS2_BB	// FIVE SECONDS TO DETONATION!
			c2_audio = SOUND_CAS2_BB
			GOSUB c2_load_sample
		ENDIF

		c2_plunger_txt = 3

	ENDIF
	IF c2_time_left <= 3800
 	AND c2_plunger_txt = 3

		IF c2_inside_quarry = 1
			$c2_print = &CAS2_BC	// THREE...!
			c2_audio = SOUND_CAS2_BC
			GOSUB c2_load_sample
		ENDIF

		c2_plunger_txt = 4

	ENDIF

	IF c2_time_left <= 2800
	AND c2_plunger_txt = 4

		IF c2_inside_quarry = 1
	 		$c2_print = &CAS2_BD	// TWO...!
			c2_audio = SOUND_CAS2_BD
			GOSUB c2_load_sample
		ENDIF
		
		c2_plunger_txt = 5

	ENDIF

	IF IS_CAR_DEAD c2_dumper
	AND c2_dumper_is_safe = 0
				
		PRINT_NOW ( PD_33 ) 4000 1 // ~r~The dumper truck has been destroyed the only way to break the crates

		GOTO mission_casino2_failed

	ENDIF
	
	IF NOT IS_CHAR_DEAD c2_plunger_man
	AND NOT IS_CHAR_DEAD scplayer

		IF IS_CHAR_SHOOTING c2_plunger_man 
		AND c2_been_damaged_2 = 0
		
			CLEAR_CHAR_TASKS_IMMEDIATELY c2_plunger_man

			TASK_USE_MOBILE_PHONE c2_plunger_man FALSE

			c2_been_damaged_2 = 1

		ENDIF

	ENDIF

	IF c2_time_left <= 1800
	AND NOT IS_CHAR_DEAD c2_plunger_man

		IF c2_inside_quarry = 1

		 	GOSUB casino2_set_camera
		 	
		 	CLEAR_HELP

			CLEAR_PRINTS

		 	SET_FIXED_CAMERA_POSITION 586.0814 876.6778 -41.6123 0.0 0.0 0.0 // Dirt Bike Route
			POINT_CAMERA_AT_POINT 587.0665 876.6669 -41.7839 JUMP_CUT

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_CAS2_BE // ONE...

			REQUEST_ANIMATION MISC

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			OR NOT HAS_ANIMATION_LOADED MISC
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( CAS2_BE ) 2000 1 // ONE...

			WAIT 1000

			TIMERA = 0
			WHILE TIMERA < 1000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO c2_skip_the_dynamite
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD c2_plunger_man
				
				CLEAR_CHAR_TASKS_IMMEDIATELY c2_plunger_man

				SET_CHAR_DECISION_MAKER	c2_plunger_man c2_decision

				SET_CHAR_HEADING c2_plunger_man 270.0000   

				TASK_PLAY_ANIM c2_plunger_man Plunger_01 MISC 1.0 TRUE FALSE FALSE FALSE -1

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO c2_skip_the_dynamite
				ENDIF
			ENDWHILE

			c2_skip_the_dynamite:

			REMOVE_ANIMATION MISC

			GOSUB c2_dynamite_explodes

			GOSUB casino2_restore_camera

		ENDIF

		PRINT_NOW ( PD_19 ) 4000 1 // ~r~The dynamite has been destroyed!

		GOTO mission_casino2_failed	

	ENDIF

	IF IS_CHAR_DEAD c2_plunger_man
	AND c2_kill_timer = 0

		CLEAR_ONSCREEN_TIMER c2_time_left

		c2_kill_timer = 1

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		IF IS_CHAR_IN_ANY_CAR scplayer

			STORE_CAR_CHAR_IS_IN scplayer c2_store_car

		ENDIF
	ENDIF

/*	IF IS_CAR_DEAD c2_forklift
	AND dynamite_collected[3] = 0
	AND c2_crate_dropped = 0

		IF c2_been_damaged = 0

			PRINT_NOW ( PD_09 ) 4000 1 // ~r~You blew up the dynamite.

			CREATE_FX_SYSTEM EXPLOSION_SMALL dynamite_x[3] dynamite_y[3] dynamite_z[3] TRUE c2_fx

			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gun_box[3] SOUND_EXPLOSION

			PLAY_AND_KILL_FX_SYSTEM c2_fx

			IF gun_box_broken[3] = 0
				BREAK_OBJECT gun_box[3] FALSE
			ENDIF

			DELETE_OBJECT dynamite_pick_up[3] 		

			GOTO mission_casino2_failed

		ENDIF

	ENDIF  */
	
	IF NOT IS_CAR_DEAD c2_store_car
	AND NOT IS_CAR_DEAD c2_forklift
	AND c2_in_dumper = 1

		IF HAS_CAR_BEEN_DAMAGED_BY_CAR c2_forklift c2_store_car
		AND c2_been_damaged = 0

			c2_been_damaged = 1	

			IF NOT IS_CAR_DEAD c2_forklift
				
			   	IF DOES_OBJECT_EXIST broken_box[3]
					SET_OBJECT_COLLISION broken_box[3] FALSE
				 //	DETACH_OBJECT broken_box[3] 0.0 0.0 0.0 FALSE
				ENDIF

				IF DOES_OBJECT_EXIST dynamite_pick_up[3]
					SET_OBJECT_COLLISION dynamite_pick_up[3] FALSE
					DETACH_OBJECT dynamite_pick_up[3] 0.0 0.0 0.0 FALSE
			  		PLACE_OBJECT_RELATIVE_TO_CAR dynamite_pick_up[3] c2_forklift 0.0 1.8 -0.4
				ENDIF

				IF DOES_OBJECT_EXIST gun_box[3]
					SET_OBJECT_COLLISION gun_box[3] FALSE
				 //	DETACH_OBJECT gun_box[3] 0.0 0.0 0.0 FALSE
			  	 //	PLACE_OBJECT_RELATIVE_TO_CAR gun_box[3] c2_forklift 0.0 1.8 -0.6
				ENDIF

  			ENDIF

		   	BREAK_OBJECT gun_box[3] TRUE

			DELETE_OBJECT broken_box[3]
																				 
			dynamite_offset = dynamite_z[3] + 0.3

			CHANGE_BLIP_COLOUR dynamite_blip[3] RED

			//CHANGE_BLIP_DISPLAY dynamite_blip[3] BLIP_ONLY

			CREATE_FX_SYSTEM explosion_crate dynamite_x[3] dynamite_y[3] dynamite_z[3] TRUE c2_crate_fx

			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gun_box[3] SOUND_EXPLOSION

			PLAY_AND_KILL_FX_SYSTEM c2_crate_fx

			gun_box_broken[3] = 1

			message_once = 2

			c2_box_count ++

			IF NOT IS_CHAR_DEAD c2_intro_quarry[3]
			AND NOT IS_CAR_DEAD c2_forklift

				IF IS_CHAR_IN_CAR c2_intro_quarry[3] c2_forklift
				AND NOT IS_CAR_DEAD c2_forklift

					TASK_LEAVE_CAR c2_intro_quarry[3] c2_forklift

					TASK_KILL_CHAR_ON_FOOT c2_intro_quarry[3] scplayer

				ENDIF

			ENDIF			

		ENDIF

	ENDIF
	
    IF NOT LOCATE_CHAR_ANY_MEANS_3D	scplayer 429.4690 916.0814 0.9027 20.0 20.0 20.0 FALSE
	AND c2_given_guns = 0

		CREATE_CHAR PEDTYPE_MISSION2 wmysgrd 416.5302 862.1686 6.7328 security_guards[4]

		SET_CHAR_HEADING security_guards[4] 357.0781  	

		GIVE_WEAPON_TO_CHAR security_guards[4] WEAPONTYPE_PISTOL 30000

	  	SET_CURRENT_CHAR_WEAPON security_guards[4] WEAPONTYPE_PISTOL

		PERFORM_SEQUENCE_TASK security_guards[4] c2_stand_and_shoot

		CREATE_CHAR PEDTYPE_MISSION2 wmysgrd 423.9201 861.7891 5.5413 security_guards[5]

		SET_CHAR_HEADING security_guards[5] 352.4637

	  	GIVE_WEAPON_TO_CHAR security_guards[5] WEAPONTYPE_PISTOL 30000

	  	SET_CURRENT_CHAR_WEAPON security_guards[5] WEAPONTYPE_PISTOL

		PERFORM_SEQUENCE_TASK security_guards[5] c2_stand_and_shoot

		c2_given_guns = 1		

	ENDIF
	
	IF DOES_OBJECT_EXIST dynamite_pick_up[3]
		GET_OBJECT_COORDINATES dynamite_pick_up[3] dynamite_x[3] dynamite_y[3] dynamite_z[3]
	ENDIF

	IF IS_CHAR_IN_MODEL scplayer FORKLIFT
	AND NOT c2_crate_dropped = 2
	AND NOT gun_box_broken[3] = 1

		PRINT_HELP_FOREVER ( PD_17 ) // ~s~Press the ~o~ button to drop the crate

		STORE_CAR_CHAR_IS_IN scplayer car

		IF DOES_OBJECT_EXIST broken_box[3]
		//	SET_OBJECT_COLLISION broken_box[3] FALSE
		ENDIF

		IF DOES_OBJECT_EXIST dynamite_pick_up[3]
		//	SET_OBJECT_COLLISION dynamite_pick_up[3] FALSE
		ENDIF

		IF DOES_OBJECT_EXIST gun_box[3]
		//	SET_OBJECT_COLLISION gun_box[3] FALSE
		ENDIF

	ELSE 

		IF DOES_OBJECT_EXIST broken_box[3]
		//	SET_OBJECT_COLLISION broken_box[3] TRUE
		ENDIF

		IF DOES_OBJECT_EXIST dynamite_pick_up[3]
		//	SET_OBJECT_COLLISION dynamite_pick_up[3] FALSE
		ENDIF

		IF DOES_OBJECT_EXIST gun_box[3]
		//	SET_OBJECT_COLLISION gun_box[3] TRUE
		ENDIF

		IF NOT on_way_home = 4

			CLEAR_HELP

		ENDIF

	ENDIF

	IF IS_CHAR_IN_MODEL scplayer dumper
	OR IS_CHAR_IN_MODEL scplayer dozer
	
		REPEAT 4 v

			IF DOES_OBJECT_EXIST gun_box[v]
				SET_OBJECT_COLLISION gun_box[v] FALSE
			ENDIF
			IF DOES_OBJECT_EXIST broken_box[v]
				SET_OBJECT_COLLISION broken_box[v] FALSE
			ENDIF

		ENDREPEAT
		
		c2_in_dumper = 1		

	ELSE

	//	IF NOT IS_CHAR_IN_MODEL scplayer FORKLIFT

			REPEAT 4 v

				IF DOES_OBJECT_EXIST gun_box[v]
					SET_OBJECT_COLLISION gun_box[v] TRUE
				ENDIF
				IF DOES_OBJECT_EXIST broken_box[v]
					SET_OBJECT_COLLISION broken_box[v] TRUE
				ENDIF

			ENDREPEAT

	//	ENDIF
		
		c2_in_dumper = 0
	 
	ENDIF 	

	REPEAT 10 v

		// Player is within 10 meters of worker
		IF NOT IS_CHAR_DEAD quarry_worker[v]
		
			GET_CHAR_COORDINATES quarry_worker[v] c2_temp_X c2_temp_Y c2_temp_Z 

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer c2_temp_X c2_temp_Y c2_temp_Z 15.0 15.0 3.0 FALSE

				IF c2_worker_yell = 0
				AND c2_TIMERC > 300
				AND c2_cutscene_playing = 0

					IF NOT IS_MESSAGE_BEING_DISPLAYED
						
						c2_break_loop = 0

						another_please:

						GENERATE_RANDOM_INT_IN_RANGE 0 6 c2_rnd
						
						IF c2_rnd = c2_last_played 

							c2_break_loop ++

							IF c2_break_loop < 5
								GOTO another_please
							ENDIF
	
						ENDIF

						SWITCH c2_rnd

							CASE 0

								$c2_print = &CAS2_AA	// Hey you can't come down here!
								c2_audio = SOUND_CAS2_AA
								GOSUB c2_load_sample

							BREAK

							CASE 1

								$c2_print = &CAS2_AB	// Where's your safety gear?
								c2_audio = SOUND_CAS2_AB
								GOSUB c2_load_sample

							BREAK

							CASE 2

								$c2_print = &CAS2_AC	// You don't have clearence for this area!
								c2_audio = SOUND_CAS2_AC
								GOSUB c2_load_sample

							BREAK

							CASE 3

								$c2_print = &CAS2_AJ	// Who's this asshole?
								c2_audio = SOUND_CAS2_AJ
								GOSUB c2_load_sample
												    
							BREAK

							CASE 4

								$c2_print = &CAS2_AK	// Someone call security!
								c2_audio = SOUND_CAS2_AK
								GOSUB c2_load_sample

							BREAK

							CASE 5

								$c2_print = &CAS2_AL	// What do you think you're doing?
								c2_audio = SOUND_CAS2_AL
								GOSUB c2_load_sample

							BREAK

						ENDSWITCH

					    c2_last_played = c2_rnd

						c2_TIMERC = 0			

					ENDIF

				ENDIF

				IF c2_in_range_of_worker = 0

					c2_in_range_of_worker = 1

				ENDIF

			ENDIF

		ENDIF

	ENDREPEAT

	REPEAT 4 v

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer dynamite_x[v] dynamite_y[v] dynamite_z[v] 5.0 5.0 5.0 FALSE
		AND c2_in_range_of_worker = 1
		AND message_once = 2

			IF NOT IS_MESSAGE_BEING_DISPLAYED

				GENERATE_RANDOM_INT_IN_RANGE 0 3 c2_rnd

				IF c2_rnd = 0
							
					$c2_print = &CAS2_AD	// Worker: Get away from that dynamite!
					c2_audio = SOUND_CAS2_AD
					GOSUB c2_load_sample

				ENDIF
				
				IF c2_rnd = 1 

					$c2_print = &CAS2_AE	// Hey, that's dangerous!
					c2_audio = SOUND_CAS2_AE
					GOSUB c2_load_sample
 
				ENDIF

				IF c2_rnd = 2 

					$c2_print = &CAS2_AF	// Stay away from that stuff!
					c2_audio = SOUND_CAS2_AF
					GOSUB c2_load_sample

				ENDIF

			  	c2_in_range_of_worker = 2

				TIMERB = 0

			ENDIF
			
		ENDIF

  		IF gun_box_broken[v] = 0

	 	  //	GET_OBJECT_HEALTH gun_box[v] gun_health
						
			IF DOES_OBJECT_EXIST dynamite_pick_up[3]
				GET_OBJECT_COORDINATES dynamite_pick_up[3] dynamite_x[3] dynamite_y[3] dynamite_z[3]
			ENDIF

			// Smashing the boxes with the dumper truck
		 	IF LOCATE_CHAR_IN_CAR_3D scplayer dynamite_x[v] dynamite_y[v] dynamite_z[v] 3.0 3.0 3.0 FALSE
			AND gun_box_broken[v] = 0
		
				IF IS_CHAR_IN_MODEL scplayer dumper
				OR IS_CHAR_IN_MODEL scplayer dozer

					IF v = 3
					AND NOT IS_CAR_DEAD c2_forklift
					AND NOT IS_CHAR_DEAD c2_intro_quarry[3]

						IF IS_CHAR_IN_CAR c2_intro_quarry[3] c2_forklift
						
							TASK_LEAVE_CAR c2_intro_quarry[3] c2_forklift

						ENDIF

					ENDIF

					IF v = 0

						IF NOT IS_CHAR_DEAD quarry_worker[8]
						AND NOT IS_CHAR_DEAD scplayer

							CLEAR_CHAR_TASKS_IMMEDIATELY quarry_worker[8]

							PERFORM_SEQUENCE_TASK quarry_worker[8] c2_seq_flee[0]

						ENDIF
				
						IF NOT IS_CHAR_DEAD quarry_worker[9]
						AND NOT IS_CHAR_DEAD scplayer

							CLEAR_CHAR_TASKS_IMMEDIATELY quarry_worker[9]

							PERFORM_SEQUENCE_TASK quarry_worker[9] c2_seq_flee[0]

						ENDIF
	
					ENDIF					

					IF c2_collect_the_dynamite = 0

						PRINT_NOW ( PD_29 ) 4000 1 // ~s~Pick up the ~r~Dynamite~s~! 

						c2_collect_the_dynamite = 1

					ENDIF

					SHAKE_PAD PAD1 300 200
								    
					BREAK_OBJECT gun_box[v] TRUE

					dynamite_offset = dynamite_z[v] + 0.3

					MAKE_OBJECT_TARGETTABLE gun_box[v] FALSE

					SET_OBJECT_COORDINATES dynamite_pick_up[v] dynamite_x[v] dynamite_y[v] dynamite_offset

					CHANGE_BLIP_COLOUR dynamite_blip[v] RED

					//CHANGE_BLIP_DISPLAY dynamite_blip[v] BLIP_ONLY

					CREATE_FX_SYSTEM explosion_crate dynamite_x[v] dynamite_y[v] dynamite_z[v] TRUE crate_smash[v]

					PLAY_AND_KILL_FX_SYSTEM crate_smash[v]

					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gun_box[v] SOUND_CAR_SMASH_CAR

					gun_box_broken[v] = 1

					message_once = 2

					c2_box_count ++

					GOSUB c2_kill_the_player

			  	ENDIF

 			ENDIF 

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer dynamite_x[v] dynamite_y[v] dynamite_z[v] 2.0 2.0 2.0 FALSE
			AND message_once = 0
			
				IF NOT IS_CHAR_IN_MODEL scplayer dumper
				AND NOT IS_CHAR_IN_MODEL scplayer dozer
				
					PRINT_NOW ( PD_02 ) 4000 1 // You'll need something heavy to smash this dynamite crate.

					message_once = 1
					
				ENDIF

			ENDIF
	
		ENDIF

		IF dynamite_collected[v] = 0
		AND gun_box_broken[v] = 1
		
			dynamite_offset = dynamite_z[v] + 0.3

			// Collecting the dynamite
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer dynamite_x[v] dynamite_y[v] dynamite_z[v] 1.5 1.5 1.5 FALSE
			   
			  	in_dumper = 0

				IF IS_CHAR_IN_MODEL scplayer dumper
				OR IS_CHAR_IN_MODEL scplayer dozer
				
					in_dumper = 1

				ENDIF 
 				
				IF in_dumper = 0 

					REMOVE_BLIP dynamite_blip[v]
					
					dynamite_collected[v] = 1

					SET_OBJECT_COLLISION dynamite_pick_up[v] FALSE

					DELETE_OBJECT dynamite_pick_up[v]

					SHAKE_PAD PAD1 200 100

					GOSUB display_gametext

					c2_box_count = 5
						
 			 	ENDIF
 							
			ENDIF

		ENDIF
 
	ENDREPEAT

	IF c2_box_count = 4

		IF NOT IS_CHAR_DEAD quarry_worker[7]

			CLEAR_CHAR_TASKS_IMMEDIATELY quarry_worker[7] 

			TASK_KILL_CHAR_ON_FOOT quarry_worker[7] scplayer

		ENDIF

		PRINT_NOW ( PD_12 ) 4000 1 // ~s~Now collect the ~r~Dynamite~s~!

		c2_dumper_is_safe = 1

		c2_box_count = 5		

	ENDIF

	IF on_way_home = 3
	AND c2_jumped_fence = 0

		IF NOT IS_CAR_DEAD quarry_dirt_bike 
			IF IS_CHAR_IN_CAR scplayer quarry_dirt_bike
			AND c2_dont_display = 0

				CHANGE_BLIP_DISPLAY c2_blip[0] BLIP_ONLY

				CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[0] c2_blip_y[0] c2_blip_z[0] c2_arrow_x[0] c2_arrow_y[0] c2_arrow_z[0] 4.0 c2_check_point[0]

				PRINT_NOW ( PD_23 ) 4000 1 // ~s~Follow the ~y~Arrows ~s~to escape!
							
				c2_dont_display = 1

			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD quarry_dirt_bike

			IF IS_CHAR_IN_CAR scplayer quarry_dirt_bike
			
				IF c2_get_back_on_bike = 0

					c2_get_back_on_bike = 1

				ENDIF

			ELSE

				IF c2_get_back_on_bike = 1

					PRINT_NOW ( PD_X ) 4000 1 // ~s~Get back on the ~b~bike~s~!

					c2_get_back_on_bike = 0

				ENDIF

			ENDIF

			IF IS_CHAR_IN_CAR scplayer quarry_dirt_bike

			    IF c2_on_bike = 0

					IF c2_cp < 6

						CHANGE_BLIP_DISPLAY c2_blip[c2_cp] BLIP_ONLY

						DELETE_CHECKPOINT c2_check_point[c2_cp]

						CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[c2_cp] c2_blip_y[c2_cp] c2_blip_z[c2_cp] c2_arrow_x[c2_cp] c2_arrow_y[c2_cp] c2_arrow_z[c2_cp] 4.0 c2_check_point[c2_cp]

					ENDIF

					CHANGE_BLIP_DISPLAY c2_bike_blip NEITHER

					c2_on_bike = 1

				ENDIF

			ELSE 

				IF c2_on_bike = 1

					IF c2_cp < 6

						CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER

						DELETE_CHECKPOINT c2_check_point[c2_cp]

					ENDIF

					CHANGE_BLIP_DISPLAY c2_bike_blip BOTH
					
					c2_on_bike = 0

				ENDIF

			ENDIF
		ENDIF 
		IF c2_on_bike = 1

		 	IF c2_ply_Z < -4.0
			AND c2_cp > 4

				IF c2_cp < 6

					CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER
					
					DELETE_CHECKPOINT c2_check_point[c2_cp]

				ENDIF

				CHANGE_BLIP_DISPLAY c2_blip[4] BLIP_ONLY

				CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[4] c2_blip_y[4] c2_blip_z[4] c2_arrow_x[4] c2_arrow_y[4] c2_arrow_z[4] 4.0 c2_check_point[4]

				CHANGE_BLIP_DISPLAY c2_final_blip NEITHER

				c2_cp = 4

			ENDIF

			IF c2_ply_Z < -16.0
			AND c2_cp > 3
				IF c2_cp < 6

					CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER
					
					DELETE_CHECKPOINT c2_check_point[c2_cp]

				ENDIF

				CHANGE_BLIP_DISPLAY c2_blip[3] BLIP_ONLY

				CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[3] c2_blip_y[3] c2_blip_z[3] c2_arrow_x[3] c2_arrow_y[3] c2_arrow_z[3] 4.0 c2_check_point[3]

				CHANGE_BLIP_DISPLAY c2_final_blip NEITHER

				c2_cp = 3

			ENDIF
			IF c2_ply_Z < -30.0
			AND c2_cp > 2
				IF c2_cp < 6

					CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER
					
					DELETE_CHECKPOINT c2_check_point[c2_cp]

				ENDIF

				CHANGE_BLIP_DISPLAY c2_blip[2] BLIP_ONLY

				CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[2] c2_blip_y[2] c2_blip_z[2] c2_arrow_x[2] c2_arrow_y[2] c2_arrow_z[2] 4.0 c2_check_point[2]

				CHANGE_BLIP_DISPLAY c2_final_blip NEITHER

				c2_cp = 2

			ENDIF
			IF c2_ply_Z < -40.0
			AND NOT c2_cp = 0
				IF c2_cp < 6

					CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER
					
					DELETE_CHECKPOINT c2_check_point[c2_cp]

				ENDIF

				CHANGE_BLIP_DISPLAY c2_blip[0] BLIP_ONLY

				CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[0] c2_blip_y[0] c2_blip_z[0] c2_arrow_x[0] c2_arrow_y[0] c2_arrow_z[0] 4.0 c2_check_point[0]

				CHANGE_BLIP_DISPLAY c2_final_blip NEITHER

				c2_cp = 0

			ENDIF 
			IF c2_cp < 6

				IF LOCATE_CHAR_ANY_MEANS_3D scplayer c2_blip_X[c2_cp] c2_blip_Y[c2_cp] c2_blip_z[c2_cp] 3.0 3.0 3.0 FALSE	
				AND c2_on_bike = 1

					CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER
					
					DELETE_CHECKPOINT c2_check_point[c2_cp]
					 
					c2_cp ++

					IF c2_cp < 6

						CREATE_CHECKPOINT CHECKPOINT_TUBE c2_blip_x[c2_cp] c2_blip_y[c2_cp] c2_blip_z[c2_cp] c2_arrow_x[c2_cp] c2_arrow_y[c2_cp] c2_arrow_z[c2_cp] 4.0 c2_check_point[c2_cp]

						CHANGE_BLIP_DISPLAY c2_blip[c2_cp] BLIP_ONLY

					ENDIF
					IF c2_cp = 6

						CHANGE_BLIP_DISPLAY c2_final_blip BOTH

						IF c2_courier_txt = 0

							PRINT_NOW ( PD_28 ) 7000 1 // ~s~Now deliver the dynamite to Woozie's ~y~Courier~s~!      

							c2_courier_txt = 1

						ENDIF

					ENDIF

				ENDIF

			ENDIF
		ENDIF

		IF IS_CAR_DEAD quarry_dirt_bike 
		AND c2_jumped_fence = 0

			MARK_CAR_AS_NO_LONGER_NEEDED quarry_dirt_bike
									
			IF DOES_BLIP_EXIST c2_bike_blip

				REMOVE_BLIP c2_bike_blip

			ENDIF

			IF c2_second_bike = 0

			 	CLEAR_AREA 639.3412 850.4575 -43.9534 15.0 TRUE
			 		    
				CREATE_CAR sanchez 639.3412 850.4575 -43.9534 quarry_dirt_bike

				SET_CAN_BURST_CAR_TYRES quarry_dirt_bike FALSE

				SET_CAR_HEADING quarry_dirt_bike 90.0000

				ADD_BLIP_FOR_CAR quarry_dirt_bike c2_bike_blip 
				
				SET_BLIP_AS_FRIENDLY c2_bike_blip TRUE  
				 
				c2_second_bike = 1
						
			ELSE

			 	CLEAR_AREA 583.0051 881.9482 -45.0648 15.0 TRUE
			 		    
				CREATE_CAR sanchez 583.0051 881.9482 -45.0648 quarry_dirt_bike

				SET_CAN_BURST_CAR_TYRES quarry_dirt_bike FALSE

				SET_CAR_HEADING quarry_dirt_bike 93.6785

				ADD_BLIP_FOR_CAR quarry_dirt_bike c2_bike_blip 
				
				SET_BLIP_AS_FRIENDLY c2_bike_blip TRUE 

				c2_second_bike = 0

			ENDIF

			IF c2_left_on_bike = 0

				PRINT_NOW ( PD_34 ) 4000 1 // ~s~Find another ~b~bike ~s~and escape!

			ENDIF

		ENDIF

		IF NOT IS_CAR_DEAD quarry_dirt_bike
		AND NOT IS_CHAR_DEAD scplayer

			GET_CHAR_COORDINATES scplayer x y z

			GET_CAR_COORDINATES quarry_dirt_bike x y c2_z

			c2_z = c2_z - 6.0

			IF z < c2_z

				EXPLODE_CAR quarry_dirt_bike

			ENDIF

		ENDIF
	
	ENDIF

	IF on_way_home > 0

		IF TIMERA > 3000
		AND on_way_home = 1

			PRINT_NOW ( PD_22 ) 4000 1 // ~s~Get back home with the dynamite!

			on_way_home = 2

		ENDIF

		IF TIMERA > 7000
		AND on_way_home = 2

			 // Security Guards *********************************************
			GOSUB cavalry_arrives

			on_way_home = 3

		ENDIF

		IF on_way_home = 3

			IF IS_CAR_DEAD c2_end_truck

				PRINT_NOW ( PD_30 ) 4000 1 // ~r~You idiot you blew up the couriers car!

				GOTO mission_casino2_failed

			ENDIF

			IF IS_CHAR_DEAD c2_end_guy

				PRINT_NOW ( PD_31 ) 4000 1 // ~r~You idiot you killed the courier!

				GOTO mission_casino2_failed

			ENDIF

		ENDIF

		IF NOT on_way_home = 4
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1030.1570 744.3469 9.7922 2.0 2.0 2.0 TRUE
		
			SET_PLAYER_CONTROL player1 OFF

			IF DOES_BLIP_EXIST c2_final_blip

				REMOVE_BLIP c2_final_blip

			ENDIF

			REPEAT 5 v

				IF DOES_BLIP_EXIST c2_blip[v]

					REMOVE_BLIP c2_blip[v]

				ENDIF

			ENDREPEAT

			CLEAR_PRINTS

			IF NOT IS_CHAR_DEAD scplayer
	
				GOSUB casino2_fade_out			
					
				LVAR_INT c2_ply_car

				CLEAR_AREA 1023.9374 738.5938 9.8080 30.0 TRUE

				IF NOT IS_CAR_DEAD c2_end_truck

					FREEZE_CAR_POSITION c2_end_truck FALSE

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer
					IF IS_CHAR_IN_ANY_CAR scplayer

						STORE_CAR_CHAR_IS_IN scplayer c2_ply_car
							
						SET_CAR_COORDINATES c2_ply_car 1025.2955 746.0016 9.8302  
 
						SET_CAR_HEADING c2_ply_car 300.0064

						WARP_CHAR_FROM_CAR_TO_COORD scplayer 1028.1681 741.3518 9.7989
						
					ELSE

						SET_CHAR_COORDINATES scplayer 1028.1681 741.3518 9.7989  

					ENDIF

				ENDIF

				IF NOT IS_CHAR_DEAD c2_end_guy
					
					CLEAR_CHAR_TASKS_IMMEDIATELY c2_end_guy

					SET_CHAR_COORDINATES c2_end_guy	1029.1730 740.8435 9.8018

					SET_CHAR_HEADING c2_end_guy 63.6493

				ENDIF

				SET_CHAR_HEADING scplayer 232.6414 

				CREATE_OBJECT dynamite 1028.9346 741.1799 9.8005 c2_cut_dynamite_A
				
				SET_OBJECT_HEADING c2_cut_dynamite_A 90.0000
				
				SET_OBJECT_COLLISION c2_cut_dynamite_A FALSE
					
			   	CREATE_OBJECT dynamite 1028.9346 741.0000 9.8005 c2_cut_dynamite_B

			   	SET_OBJECT_COLLISION c2_cut_dynamite_B FALSE

			   	CREATE_OBJECT dynamite 1028.9346 741.0000 9.8005 c2_cut_dynamite_C

			   	SET_OBJECT_COLLISION c2_cut_dynamite_C FALSE

			   	ATTACH_OBJECT_TO_OBJECT c2_cut_dynamite_B c2_cut_dynamite_A 0.2 0.0 -0.2 0.0 90.0 40.0

			  	ATTACH_OBJECT_TO_OBJECT c2_cut_dynamite_C c2_cut_dynamite_A 0.3 0.0 0.0 0.0 45.0 60.0

				IF NOT IS_CHAR_DEAD c2_end_guy

					TASK_COMPLEX_PICKUP_OBJECT c2_end_guy c2_cut_dynamite_A

				ENDIF

			   	GOSUB casino2_set_camera
				
				LOAD_SCENE 1028.9346 741.1799 9.8005

				SET_FIXED_CAMERA_POSITION 1026.3605 737.9733 11.3745 0.0 0.0 0.0 // Dirt Bike Route
				POINT_CAMERA_AT_POINT 1026.9908 738.7281 11.1933 JUMP_CUT

				GOSUB casino2_fade_in

				IF NOT IS_CHAR_DEAD scplayer					

					TASK_PLAY_ANIM scplayer IDLE_chat PED 1.0 FALSE FALSE FALSE FALSE -1 
	
				ENDIF

				PRINT_NOW ( CAS2_CA ) 4000 1 // Delivery for Woozie.
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_CAS2_CA // Delivery for Woozie.

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
				ENDWHILE

				PRINT_NOW ( CAS2_CB ) 4000 1 // Be careful with that...

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_CAS2_CB // Be careful with that...

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
				ENDWHILE

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
						GOTO c2_skip_the_last_cut_1
					ENDIF
				ENDWHILE

				GOSUB casino2_fade_out			
	  
				IF NOT IS_CAR_DEAD c2_end_truck

					DELETE_CAR c2_end_truck

				ENDIF	

				CREATE_CAR manana 1032.6781 741.7072 9.8044 c2_end_truck

			 	SET_FIXED_CAMERA_POSITION 1020.4991 740.7717 14.7072 0.0 0.0 0.0 // Dirt Bike Route

				IF NOT IS_CAR_DEAD c2_end_truck
					POINT_CAMERA_AT_CAR c2_end_truck FIXED JUMP_CUT
				ENDIF

				IF NOT IS_CHAR_DEAD c2_end_guy
				OR NOT IS_CAR_DEAD c2_end_truck
					
					DELETE_OBJECT c2_cut_dynamite_B
					DELETE_OBJECT c2_cut_dynamite_C
					DELETE_OBJECT c2_cut_dynamite_A

					WARP_CHAR_INTO_CAR c2_end_guy c2_end_truck

					TASK_CAR_DRIVE_TO_COORD c2_end_guy c2_end_truck 1052.5677 754.0004 9.8094 25.00 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
					
				ENDIF

				TIMERA = 0
				WHILE TIMERA < 10
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
						GOTO c2_skip_the_last_cut_1
					ENDIF
				ENDWHILE
				
				GOSUB casino2_fade_in

				TIMERA = 0
				WHILE TIMERA < 3000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
						GOTO c2_skip_the_last_cut_1
					ENDIF
				ENDWHILE
				
				c2_skip_the_last_cut_1:							   

				IF NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				ENDIF

				IF DOES_OBJECT_EXIST c2_cut_dynamite_A
										
					DELETE_OBJECT c2_cut_dynamite_A

				ENDIF

				IF DOES_OBJECT_EXIST c2_cut_dynamite_B
										
					DELETE_OBJECT c2_cut_dynamite_B

				ENDIF

				IF DOES_OBJECT_EXIST c2_cut_dynamite_C
										
					DELETE_OBJECT c2_cut_dynamite_C

				ENDIF

				IF NOT IS_CHAR_DEAD	c2_end_guy

					DELETE_CHAR	c2_end_guy			

				ENDIF

				IF NOT IS_CAR_DEAD c2_end_truck

					DELETE_CAR c2_end_truck

				ENDIF

				GOSUB casino2_restore_camera 
				
			ENDIF	

			PRINT_HELP ( PD_32 ) // Visit the quarry to access the Bulldozer and Dumper truck sub-missions
			 
			on_way_home = 4

			TIMERA = 0

		ENDIF 

		ENDIF

	ENDIF

	IF on_way_home = 4
	AND TIMERA > 4000

		GOTO mission_casino2_passed

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 454.5292 804.0931 5.3702 2.0 2.0 2.0 FALSE

		c2_left_on_bike = 1

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 447.3409 811.1259 5.4896 2.0 2.0 2.0 FALSE

		c2_left_on_bike = 0

	ENDIF

	IF NOT LOCATE_CHAR_ANY_MEANS_3D	scplayer 562.4489 871.3499 -17.0245 250.0 250.0 250.0 FALSE

		c2_inside_quarry = 0

	ELSE

		c2_inside_quarry = 1

	ENDIF

	// *****************************************************************************************************
	// *																								   *
	// *                                    Check if jumped fence										   *
	// *																								   *
	// *****************************************************************************************************
	
	IF NOT LOCATE_CHAR_ANY_MEANS_3D	scplayer 562.4489 871.3499 -17.0245 250.0 250.0 250.0 FALSE
	AND on_way_home = 3
	AND c2_cp < 6

		IF c2_jumped_fence = 0

			CHANGE_BLIP_DISPLAY c2_blip[c2_cp] NEITHER

			CHANGE_BLIP_DISPLAY c2_bike_blip[c2_cp] NEITHER
					
			DELETE_CHECKPOINT c2_check_point[c2_cp]

			c2_cp = 6

			CHANGE_BLIP_DISPLAY c2_final_blip BOTH

			IF c2_courier_txt = 0

				PRINT_NOW ( PD_28 ) 4000 1 // ~s~Now deliver the dynamite to Woozie's ~y~Courier~s~!      

				c2_courier_txt = 1

			ENDIF

			c2_jumped_fence = 1

		ENDIF

	ENDIF

ENDWHILE 

GOTO mission_casino2_failed

// CALLING SECURITY ************************************************************************

call_security:

	IF called_security = 0

		IF everyone_is_fleeing = 1
		
		 	PRINT ( CAS4_AD ) 4000 1 // Get away from that dynamite!

		ENDIF

	ENDIF

	called_security = 1

RETURN

// DISPLAY PACKAGE GAMETEXT ****************************************************************

display_gametext:

	IF t = 0 

		GOSUB call_security

		PRINT ( PD_05 ) 4000 1 // 1 stick of 4

		IF NOT IS_CHAR_DEAD scplayer

			REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_PICKUP_STANDARD

		ENDIF

	//	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE

	ENDIF

	IF t = 1

		PRINT_NOW ( PD_06 ) 4000 1 // 2 sticks of 4

		IF NOT IS_CHAR_DEAD scplayer

			REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_PICKUP_STANDARD

		ENDIF

	//	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE

	ENDIF

	IF t = 2

		PRINT_NOW ( PD_07 ) 4000 1 // 3 sticks of 4

		IF NOT IS_CHAR_DEAD scplayer

			REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_PICKUP_STANDARD

		ENDIF

	//	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE

	ENDIF

	IF t = 3

		PRINT_NOW ( PD_08 ) 4000 1 // 4 sticks of 4

		IF NOT IS_CHAR_DEAD scplayer

			REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_PICKUP_STANDARD

		ENDIF

	//	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
	
		TIMERA = 0
				
		on_way_home = 1

		CLEAR_ONSCREEN_TIMER c2_time_left

	ENDIF

	t ++

RETURN

c2_kill_the_player:

	IF c2_breakpoint = 0

		SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1 

	ENDIF

RETURN

// EVERYONE IS FLEEING THE PLAYER ***************************************************

everyone_flee:

	IF everyone_is_fleeing = 0

		everyone_is_fleeing = 1

	ENDIF

RETURN

// PLAYER HAS SHOT AND EXPLODED THE DYNAMITE **********************************************

dyna_failed:

PRINT_BIG ( M_FAIL ) 5000 1 // "Mission Failed"

PRINT_NOW ( PD_09 ) 4000 1 // ~r~You blew up the dynamite.

WAIT 4000

RETURN
																		    
// *****************************************************************************************
// *																					   *
// *                                 Mission Failed     				   				   *
// *																					   *
// *****************************************************************************************

mission_casino2_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

// *****************************************************************************************
// *																					   *
// *                                 Mission Passed     				   				   *
// *																					   *
// *****************************************************************************************

mission_casino2_passed:

	START_NEW_SCRIPT heist_mission_loop

	START_NEW_SCRIPT quarry_loop

	REMOVE_BLIP Theheist_contact_blip

	ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip

	SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
				 
	flag_casino_mission_counter ++ // Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc

	REGISTER_MISSION_PASSED ( CASEEN2 ) // Used in the stats 

	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 7000 5000 1 // "Mission Passed!" //100 being the amount of cash

	ADD_SCORE player1 7000 // amount of cash

	AWARD_PLAYER_MISSION_RESPECT 15 // amount of respect

	PLAYER_MADE_PROGRESS 1

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

	RELEASE_WEATHER

	FORCE_WEATHER WEATHER_SUNNY_LA

	REMOVE_BLIP quarry_contact_blip

	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT quarryX quarryY quarryZ RADAR_SPRITE_BULLDOZER quarry_contact_blip

RETURN

// *****************************************************************************************
// *																					   *
// *                                 Mission Clean-Up   			   					   *
// *																					   *
// *****************************************************************************************

mission_cleanup_casino2:

	IF player_fall_state > 0
		player_fall_state = 6 
	ENDIF

	disable_all_cranes = 0

	CLEAR_ONSCREEN_TIMER c2_time_left

	REPEAT 4 v

		IF DOES_BLIP_EXIST dynamite_blip[v]
			REMOVE_BLIP dynamite_blip[v]
		ENDIF

	ENDREPEAT

	REPEAT 5 v

		IF DOES_BLIP_EXIST c2_blip[v]
			REMOVE_BLIP c2_blip[v]
		ENDIF

	ENDREPEAT

	IF DOES_BLIP_EXIST c2_final_blip
		REMOVE_BLIP c2_final_blip	
	ENDIF
		
	IF DOES_BLIP_EXIST c2_bike_blip
		REMOVE_BLIP c2_bike_blip
	ENDIF

	IF DOES_BLIP_EXIST c2_quarry_blip
		REMOVE_BLIP c2_quarry_blip
	ENDIF

	IF NOT IS_CAR_DEAD security_van_1
		DELETE_CAR security_van_1
	ENDIF

	IF NOT IS_CAR_DEAD security_van_2
		DELETE_CAR security_van_2
	ENDIF

	IF NOT IS_CAR_DEAD security_van_3
		DELETE_CAR security_van_3
	ENDIF

	IF DOES_OBJECT_EXIST cas2_gate1
		SET_OBJECT_HEADING cas2_gate1 0.0 
	ENDIF

	IF DOES_OBJECT_EXIST cas2_gate2
		SET_OBJECT_HEADING cas2_gate2 15.0 
	ENDIF

	IF DOES_OBJECT_EXIST c2_sign
		DELETE_OBJECT c2_sign
	ENDIF

	IF DOES_OBJECT_EXIST c2_sign1
		DELETE_OBJECT c2_sign1
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED BMYCON
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	MARK_MODEL_AS_NO_LONGER_NEEDED wmyconb
	MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
 	MARK_MODEL_AS_NO_LONGER_NEEDED shovel
	MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED manana
 	MARK_MODEL_AS_NO_LONGER_NEEDED securica
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED sanchez
	MARK_MODEL_AS_NO_LONGER_NEEDED DUMPER

	REMOVE_ANIMATION CARRY
	REMOVE_ANIMATION MISC

	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

	CLEAR_WANTED_LEVEL player1

	DONT_SUPPRESS_CAR_MODEL dumper
	
	RELEASE_WEATHER
	MISSION_HAS_FINISHED

	flag_player_on_mission = 0

RETURN 

c2_dynamite_explodes:

	REPEAT 4 v

		IF DOES_OBJECT_EXIST dynamite_pick_up[v]	

			SWITCH v

				CASE 0 

					IF NOT IS_CHAR_DEAD quarry_worker[8]

						DELETE_CHAR quarry_worker[8]

					ENDIF
					
					IF NOT IS_CHAR_DEAD quarry_worker[9] 

						DELETE_CHAR quarry_worker[9]

					ENDIF
					
				 	SET_FIXED_CAMERA_POSITION 656.9689 914.2382 -38.7741 0.0 0.0 0.0 // Dirt Bike Route
					POINT_CAMERA_AT_POINT 657.7072 914.8211 -39.1131 JUMP_CUT		
					
				BREAK
 
				CASE 1 

				 	SET_FIXED_CAMERA_POSITION 578.0302 926.3607 -39.6861 0.0 0.0 0.0 // Dirt Bike Route
					POINT_CAMERA_AT_POINT 578.5041 927.1686 -40.0363 JUMP_CUT	

				BREAK
 
				CASE 2 
											   
				 	SET_FIXED_CAMERA_POSITION 543.1476 853.9489 -38.0164 0.0 0.0 0.0 // Dirt Bike Route
					POINT_CAMERA_AT_POINT 543.9039 853.3993 -38.3712 JUMP_CUT		

				BREAK
 
				CASE 3 

					IF NOT IS_CHAR_DEAD scplayer
						IF NOT IS_CAR_DEAD c2_forklift
						AND c2_crate_dropped = 0
						AND NOT IS_CHAR_IN_MODEL scplayer FORKLIFT
					
						   	IF DOES_OBJECT_EXIST broken_box[3]
								DETACH_OBJECT broken_box[3] 0.0 0.0 0.0 FALSE
								PLACE_OBJECT_RELATIVE_TO_CAR broken_box[3] c2_forklift 0.0 1.8 -0.6
							ENDIF

							IF DOES_OBJECT_EXIST dynamite_pick_up[3]
								DETACH_OBJECT dynamite_pick_up[3] 0.0 0.0 0.0 FALSE
						  		PLACE_OBJECT_RELATIVE_TO_CAR dynamite_pick_up[3] c2_forklift 0.0 1.8 -0.6
							ENDIF

							IF DOES_OBJECT_EXIST gun_box[3]
								DETACH_OBJECT gun_box[3] 0.0 0.0 0.0 FALSE
						  		PLACE_OBJECT_RELATIVE_TO_CAR gun_box[3] c2_forklift 0.0 1.8 -0.6
							ENDIF

							IF NOT IS_CHAR_DEAD c2_intro_quarry[3] 

								CLEAR_CHAR_TASKS_IMMEDIATELY c2_intro_quarry[3] 

								DELETE_CHAR c2_intro_quarry[3] 

							ENDIF		

							DELETE_CAR c2_forklift 

			  			ENDIF	
					ENDIF

					GET_OBJECT_COORDINATES dynamite_pick_up[3] x y z 

				 	SET_FIXED_CAMERA_POSITION 635.8651 827.8486 -34.5451 0.0 0.0 0.0 // Dirt Bike Route
					POINT_CAMERA_AT_POINT x y z JUMP_CUT		

				BREAK

			ENDSWITCH

			IF DOES_OBJECT_EXIST broken_box[v]	

				FREEZE_OBJECT_POSITION broken_box[v] TRUE

			ENDIF

			GET_OBJECT_COORDINATES dynamite_pick_up[v] x y z

			ADD_EXPLOSION_VARIABLE_SHAKE x y z EXPLOSION_SMALL 5.0

		  	CREATE_FX_SYSTEM EXPLOSION_SMALL x y z TRUE c2_fx_cut[v]

		 	PLAY_AND_KILL_FX_SYSTEM c2_fx_cut[v]

			DELETE_OBJECT dynamite_pick_up[v]

			IF gun_box_broken[v] = 0

				BREAK_OBJECT gun_box[v] TRUE

			ENDIF

			WAIT 2000

			KILL_FX_SYSTEM c2_fx_cut[v]

		 //	SET_OBJECT_RENDER_SCORCHED broken_box[v] TRUE

		ENDIF

	ENDREPEAT

RETURN

// *****************************************************************************************
// *																					   *
// *                                   Camera Control   				   				   *
// *																					   *
// *****************************************************************************************

kill_guys_if_in_radius:

	IF NOT IS_CHAR_DEAD quarry_worker[8]
		IF LOCATE_CHAR_ANY_MEANS_3D quarry_worker[8] 718.7523 918.5203 -19.8860 3.0 3.0 3.0 FALSE
		
			REMOVE_CHAR_ELEGANTLY quarry_worker[8]

		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD quarry_worker[9]
		IF LOCATE_CHAR_ANY_MEANS_3D quarry_worker[9] 718.7523 918.5203 -19.8860 3.0 3.0 3.0 FALSE
		
			REMOVE_CHAR_ELEGANTLY quarry_worker[9]

		ENDIF
	ENDIF

RETURN

casino2_set_camera:

	CLEAR_PRINTS

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

RETURN

casino2_restore_camera:

	CLEAR_PRINTS

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE

 //	CLEAR_CUTSCENE

RETURN

// *****************************************************************************************
// *																					   *
// *                                        Fades  						   				   *
// *																					   *
// *****************************************************************************************

casino2_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CLEAR_PRINTS

RETURN

casino2_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

// *****************************************************************************************
// *																					   *
// *                                   Keyboard shortcuts								   *
// *																					   *
// *****************************************************************************************

casino2_keys:

	LVAR_INT c2_dummy_car 
	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Q

		IF NOT IS_CAR_DEAD c2_forklift

			ATTACH_OBJECT_TO_CAR broken_box[3] c2_forklift -0.4 0.0 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR dynamite_pick_up[3] c2_forklift 0.0 0.0 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR gun_box[3] c2_forklift -0.4 0.0 0.0 0.0 90.0 90.0

		ENDIF
			
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		REQUEST_MODEL TRIADA
		REQUEST_MODEL manana
		REQUEST_ANIMATION CARRY

		WHILE NOT HAS_MODEL_LOADED TRIADA
		OR NOT HAS_MODEL_LOADED manana
		OR NOT HAS_ANIMATION_LOADED CARRY
			WAIT 0
		ENDWHILE

		CLEAR_AREA 1032.6781 741.7072 9.8044 10.0 TRUE

		CREATE_CAR manana 1032.6781 741.7072 9.8044 c2_end_truck

		LOCK_CAR_DOORS c2_end_truck CARLOCK_LOCKED

		FREEZE_CAR_POSITION c2_end_truck TRUE

		POP_CAR_BOOT c2_end_truck

		SET_CAR_HEADING c2_end_truck 292.6477

		CREATE_CHAR PEDTYPE_CIVMALE TRIADA 1029.1730 740.8435 9.8018 c2_end_guy

		SET_CHAR_HEADING c2_end_guy 63.6493

		SET_CHAR_SUFFERS_CRITICAL_HITS c2_end_guy FALSE

		SET_CHAR_NEVER_TARGETTED c2_end_guy TRUE

		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	c2_end_guy TRUE

		SET_CHAR_DECISION_MAKER c2_end_guy c2_decision_none

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer 1010.2213 736.1201 9.8047 
			
			SET_CHAR_HEADING scplayer 275.6392 

			on_way_home = 3

			c2_cp = 6 

			c2_jumped_fence = 1

		ENDIF
			
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W

		IF NOT IS_CAR_DEAD c2_forklift

			ATTACH_OBJECT_TO_CAR broken_box[3] c2_forklift -0.6 0.0 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR dynamite_pick_up[3] c2_forklift 0.0 0.5 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR gun_box[3] c2_forklift -0.6 0.0 0.0 0.0 90.0 90.0

		ENDIF
				
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_E

		IF NOT IS_CAR_DEAD c2_forklift

			ATTACH_OBJECT_TO_CAR broken_box[3] c2_forklift -1.0 0.0 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR dynamite_pick_up[3] c2_forklift 0.0 -1.0 0.0 0.0 90.0 90.0

			ATTACH_OBJECT_TO_CAR gun_box[3] c2_forklift -1.0 0.0 0.0 0.0 90.0 90.0

		ENDIF
		
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P

		c2_time_left = 25000

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Y

		IF NOT IS_CHAR_DEAD scplayer

			CLEAR_PRINTS

			SET_CHAR_COORDINATES scplayer 820.8088 847.8527 10.0376  
					 	
			SET_CHAR_HEADING scplayer 114.6715 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		
		IF NOT IS_CHAR_DEAD scplayer

			IF IS_CHAR_IN_ANY_CAR scplayer
				
				STORE_CAR_CHAR_IS_IN scplayer c2_dummy_car
						
			 	SET_CAR_COORDINATES c2_dummy_car 495.8263 820.5667 -9.8729 
			 	
			 	SET_CAR_HEADING c2_dummy_car 30.4329 

				SET_CAMERA_BEHIND_PLAYER

			ENDIF
			
		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		CREATE_CAR securica 428.4801 840.5771 5.5835 security_van_3 

		SET_CAR_HEADING security_van_3 28.5847 

		MARK_MODEL_AS_NO_LONGER_NEEDED securica

		CREATE_CHAR_INSIDE_CAR security_van_3 PEDTYPE_MISSION2 wmysgrd security_guards[4]
		CREATE_CHAR_AS_PASSENGER security_van_3 PEDTYPE_MISSION2 wmysgrd 0 security_guards[5]

		MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			CLEAR_PRINTS

			SET_CHAR_COORDINATES scplayer 658.1340 947.6506 -35.8939    
					 	
			SET_CHAR_HEADING scplayer 194.3468 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		REQUEST_MODEL TRIADA
		REQUEST_MODEL manana

		WHILE NOT HAS_MODEL_LOADED TRIADA
		OR NOT HAS_MODEL_LOADED manana
			WAIT 0
		ENDWHILE
		
		CLEAR_AREA 1032.6781 741.7072 9.8044 10.0 TRUE

		CREATE_CAR manana 1032.6781 741.7072 9.8044 c2_end_truck

		LOCK_CAR_DOORS c2_end_truck CARLOCK_LOCKED

		POP_CAR_BOOT c2_end_truck

		SET_CAR_HEADING c2_end_truck 292.6477

		CREATE_CHAR PEDTYPE_CIVMALE TRIADA 1029.1730 740.8435 9.8018 c2_end_guy

		SET_CHAR_HEADING c2_end_guy 63.6493

		GOSUB casino2_fade_out			

			CLEAR_AREA 1032.6781 741.7072 9.8044 10.0 TRUE

			IF IS_CHAR_IN_ANY_CAR scplayer
				
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 1028.1681 741.3518 9.7989

			ELSE

				SET_CHAR_COORDINATES scplayer 1028.1681 741.3518 9.7989  

			ENDIF

			IF NOT IS_CHAR_DEAD c2_end_guy

				SET_CHAR_COORDINATES c2_end_guy	1029.1730 740.8435 9.8018

				SET_CHAR_HEADING c2_end_guy 63.6493

			ENDIF

			SET_CHAR_HEADING scplayer 232.6414 

			//REQUEST_MODEL dynamite
			REQUEST_ANIMATION CARRY

		  //	WHILE NOT HAS_MODEL_LOADED dynamite
			WHILE NOT HAS_ANIMATION_LOADED CARRY
				WAIT 0
			ENDWHILE

			CREATE_OBJECT dynamite 1028.9346 741.1799 9.8005 c2_cut_dynamite_A
			
			SET_OBJECT_HEADING c2_cut_dynamite_A 90.0000
			
			SET_OBJECT_COLLISION c2_cut_dynamite_A FALSE
				
		   	CREATE_OBJECT dynamite 1028.9346 741.0000 9.8005 c2_cut_dynamite_B

		   	SET_OBJECT_COLLISION c2_cut_dynamite_B FALSE

		   	CREATE_OBJECT dynamite 1028.9346 741.0000 9.8005 c2_cut_dynamite_C

		   	SET_OBJECT_COLLISION c2_cut_dynamite_C FALSE

		   	ATTACH_OBJECT_TO_OBJECT c2_cut_dynamite_B c2_cut_dynamite_A 0.2 0.0 -0.2 0.0 90.0 40.0

		  	ATTACH_OBJECT_TO_OBJECT c2_cut_dynamite_C c2_cut_dynamite_A 0.3 0.0 0.0 0.0 45.0 60.0

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			IF NOT IS_CHAR_DEAD c2_end_guy

				TASK_COMPLEX_PICKUP_OBJECT c2_end_guy c2_cut_dynamite_A

			ENDIF

		   	GOSUB casino2_set_camera
			
			SET_FIXED_CAMERA_POSITION 1026.3605 737.9733 11.3745 0.0 0.0 0.0 // Dirt Bike Route
			POINT_CAMERA_AT_POINT 1026.9908 738.7281 11.1933 JUMP_CUT

			GOSUB casino2_fade_in

			IF NOT IS_CHAR_DEAD scplayer					

				TASK_PLAY_ANIM scplayer IDLE_chat PED 1.0 FALSE FALSE FALSE FALSE -1 

			ENDIF
	 
			PRINT_NOW ( PD_32 ) 4000 1 // Carl : Delivery for Woozie, don't drop it.

			WAIT 1000

			TIMERA = 0
			WHILE TIMERA < 3000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO c2_skip_the_last_cut_11
				ENDIF
			ENDWHILE

			GOSUB casino2_fade_out			
			
			IF NOT IS_CAR_DEAD c2_end_truck
	
				DELETE_CAR c2_end_truck

			ENDIF	

			CREATE_CAR manana 1032.6781 741.7072 9.8044 c2_end_truck

		 	SET_FIXED_CAMERA_POSITION 1020.4991 740.7717 14.7072 0.0 0.0 0.0 // Dirt Bike Route
			IF NOT IS_CAR_DEAD c2_end_truck
				POINT_CAMERA_AT_CAR c2_end_truck FIXED JUMP_CUT
			ENDIF

			IF NOT IS_CHAR_DEAD c2_end_guy
			OR NOT IS_CAR_DEAD c2_end_truck
				
				DELETE_OBJECT c2_cut_dynamite_A
				DELETE_OBJECT c2_cut_dynamite_B
				DELETE_OBJECT c2_cut_dynamite_C

				WARP_CHAR_INTO_CAR c2_end_guy c2_end_truck

				TASK_CAR_DRIVE_TO_COORD c2_end_guy c2_end_truck 1065.9009 775.0016 9.8227 25.00 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				
			ENDIF

			TIMERA = 0
			WHILE TIMERA < 10
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO c2_skip_the_last_cut_1
				ENDIF
			ENDWHILE			    
			
			GOSUB casino2_fade_in

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO c2_skip_the_last_cut_1
				ENDIF
			ENDWHILE
			
			c2_skip_the_last_cut_11:

			IF NOT IS_CHAR_DEAD	c2_end_guy

				DELETE_CHAR	c2_end_guy			

			ENDIF

			IF NOT IS_CAR_DEAD c2_end_truck

				DELETE_CAR c2_end_truck

			ENDIF

			GOSUB casino2_restore_camera 

	ENDIF

RETURN

c2_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 c2_audio

	c2_playing = 0

RETURN

c2_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND c2_playing = 0

		PLAY_MISSION_AUDIO 1

		IF NOT $c2_print = DUMMY 

			PRINT_NOW ( $c2_print ) 10000 1 

		ENDIF

		c2_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND c2_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $c2_print

		c2_playing = 2

	ENDIF
	
RETURN

c2_load_sample_3:

	CLEAR_MISSION_AUDIO 3

	LOAD_MISSION_AUDIO 3 c2_audio_3

	c2_playing_3 = 0

RETURN

c2_play_sample_3:

	IF HAS_MISSION_AUDIO_LOADED 3
	AND c2_playing_3 = 0

		SET_MISSION_AUDIO_POSITION 3 591.0910 877.4084 -43.4973 

		PLAY_MISSION_AUDIO 3

		c2_playing_3 = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 3
	AND c2_playing_3 = 1

		CLEAR_MISSION_AUDIO 3	

		c2_playing_3 = 2

	ENDIF
	
RETURN

}

/*

 
 

{---------------------------------------CASINO2--------------------------------------------}

[PD_01:CASINO2]
Enter the Quarry and steal the dynamite.

[PD_02:CASINO2]
You'll need something heavy to smash this dynamite crate.

[PD_05:CASINO2]
1 stick of 4

[PD_06:CASINO2]
2 sticks of 4

[PD_07:CASINO2]
3 sticks of 4

[PD_08:CASINO2]
4 sticks of 4

[PD_09:CASINO2]
~r~You blew up the dynamite.

[PD_10:CASINO2]
Security is blocking the exit.

[PD_11:CASINO2]
~s~Use the ~b~Dirt bike ~s~and find another way out!

[PD_12:CASINO2]
~s~Now collect the ~r~Dynamite~s~!

[PD_16:CASINO2]
~s~Go to the ~y~Quarry~s~.

[PD_17:CASINO2]
~s~Press the ~o~ button to drop the crate

[PD_18:CASINO2]
~r~The bike was destroyed!

[PD_19:CASINO2]
~r~The dynamite has been destroyed!

[PD_20:CASINO2]	 
The dynamite is rigged to blow!

[PD_21:CASINO2]
Get it before the workers destroy it.

[PD_22:CASINO2]
~s~Get back home with the dynamite!

[PD_23:CASINO2]
~s~Follow the ~y~Arrows ~s~to escape!

[PD_25:CASINO2]
Detonation :

[PD_26:CASINO2]
~s~Get the ~g~Dynamite ~s~before the timer runs out and it's detonated.

[PD_27:CASINO2]
You'll need something heavy to smash those crates!

[PD_28:CASINO2]
~s~Now deliver the dynamite to Woozie's ~y~Courier~s~!                                                  

[PD_29:CASINO2]
~s~Pick up the ~r~Dynamite~s~!  

[PD_30:CASINO2]
~r~You idiot you blew up the couriers car!

[PD_31:CASINO2]
~r~You idiot you killed the courier!

[PD_32:CASINO2]
Carl : Delivery for Woozie, don't drop it.



   
*/