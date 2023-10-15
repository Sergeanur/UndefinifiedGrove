MISSION_START




{


																	 			   


//vehicles

// ****************************************Mission Start************************************

valet_script:
SCRIPT_NAME VALET


//flags
LVAR_INT valet_last_task[3]  valet_task_status valet_warnings_given player_is_threat_to_valet

//ints
LVAR_INT valet_i  

//decision makers
LVAR_INT valet_empty_dec

//timers
LVAR_INT checks_time_A checks_time_B checks_time_C

//sequences
LVAR_INT valet_seq

//vehicle class
LVAR_INT a_class 
LVAR_FLOAT a_float

//peds
VAR_INT a_ped a_driver a_thief a_char play_was_on_valet_oddjob

VAR_INT a_Car force_a_one_off_car pause_valet_script steal_this_car

LVAR_INT valet_model check_car_counter

VAR_FLOAT val_h

checks_time_A = 0

//SET_CAR_DENSITY_MULTIPLIER 1.0
//SET_PED_DENSITY_MULTIPLIER 1.0

 

// Initialise variables

//SET_INT_STAT CITIES_PASSED 4 //DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1


player_is_threat_to_valet = 1
can_script_be_cleaned = 1
valet_scene_running = 0
valet_can_be_created[0] = 1
valet_can_be_created[1] = 1
valet_can_be_created[2] = 1
valet_warnings_given = 0




IF checks_time_A = 99
	CREATE_CHAR PEDTYPE_CIVMALE wmyva valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] valet[valet_i]
	CREATE_CHAR PEDTYPE_CIVMALE wmyva valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] a_thief
	CREATE_CAR CHEETAH 0.0 0.0 0.0 valet_pickup_car
	CREATE_CAR CHEETAH 0.0 0.0 0.0 valet_car[1]
	CREATE_CAR CHEETAH 0.0 0.0 0.0 drop_off_car
	CREATE_CAR CHEETAH 0.0 0.0 0.0 	valet_mission_car
	CREATE_CAR CHEETAH 0.0 0.0 0.0 	sc3_missioncar
	CREATE_CAR CHEETAH 0.0 0.0 0.0 	mission_drop_off_car
	CREATE_CAR CHEETAH 0.0 0.0 0.0 	a_pickup_car
	ADD_BLIP_FOR_CAR valet_pickup_car pickup_car_blip
	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
	COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
//	COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
ENDIF


IF NOT IS_CHAR_DEAD valet[0]
	DELETE_CHAR valet[0]
ENDIF
valet_task[0] = NOT_CREATED
valet_can_be_created[0] = 1

IF NOT IS_CHAR_DEAD valet[1]
	DELETE_CHAR valet[1]
ENDIF
valet_task[1] = NOT_CREATED
valet_can_be_created[1] = 1

IF NOT IS_CHAR_DEAD valet[2]
	DELETE_CHAR valet[2]
ENDIF
valet_task[2] = NOT_CREATED
valet_can_be_created[2] = 1





//LA

VAR_FLOAT valet_la_x[3] valet_la_y[3] valet_la_z[3]	valet_la_h[3]
VAR_FLOAT valet_sf_x[3] valet_sf_y[3] valet_sf_z[3]	valet_sf_h[3]
VAR_FLOAT valet_vg_x[3] valet_vg_y[3] valet_vg_z[3]	valet_vg_h[3]

valet_la_x[0] = 330.4229
valet_la_x[1] = 328.9663
valet_la_x[2] = 326.4376

valet_la_y[0] = -1510.7832
valet_la_y[1] = -1512.4666
valet_la_y[2] = -1515.4058

valet_la_z[0] = 35.0247
valet_la_z[1] = 35.0247
valet_la_z[2] = 35.0313

valet_la_h[0] = 222.0
valet_la_h[1] = 222.0
valet_la_h[2] = 222.0

valet_sf_x[0] = -1761.0292
valet_sf_x[1] = -1759.9138
valet_sf_x[2] = -1749.7128

valet_sf_y[0] = 959.5504 
valet_sf_y[1] = 960.2669 
valet_sf_y[2] = 960.1956 

valet_sf_z[0] = 23.8828
valet_sf_z[1] = 23.8828
valet_sf_z[2] = 23.8906

valet_sf_h[0] = 204.3268
valet_sf_h[1] = 199.1670
valet_sf_h[2] = 142.3291

valet_vg_x[0] = 2029.7582
valet_vg_x[1] = 2027.6661
valet_vg_x[2] = 2026.3937

valet_vg_y[0] = 1905.9434
//valet_vg_y[1] = 1907.6700
//valet_vg_y[2] = 1905.8102

//valet_vg_z[0] = 11.3315
//valet_vg_z[1] = 11.3349
//valet_vg_z[2] =	11.3313

//valet_vg_h[0] = 331.7047
//valet_vg_h[1] = 297.1161
//valet_vg_h[2] = 288.6038

VAR_FLOAT valet_drop_off_area_x1[4] valet_drop_off_area_x2[4] valet_drop_off_area_y1[4] valet_drop_off_area_y2[4]
VAR_FLOAT car_park_area_x1[4] car_park_area_x2[4] car_park_area_y1[4] car_park_area_y2[4] car_park_area_z1[4] car_park_area_z2[4]
VAR_FLOAT valet_exit_x[4] valet_exit_y[4] valet_exit_z[4]




VAR_FLOAT find_drop_off_car_x1[4] find_drop_off_car_y1[4] find_drop_off_car_x2[4] find_drop_off_car_y2[4] find_drop_off_car_h1[4] 
VAR_FLOAT find_drop_off_car_x3[4] find_drop_off_car_y3[4] find_drop_off_car_x4[4] find_drop_off_car_y4[4] find_drop_off_car_h2[4] 
VAR_FLOAT drop_off_point_x[4] drop_off_point_y[4] drop_off_point_z[4]	

valet_drop_off_area_x1[1] =	336.9336    
valet_drop_off_area_x2[1] =	323.9190 
valet_drop_off_area_y1[1] =	-1531.0270 
valet_drop_off_area_y2[1] =	-1504.1737

valet_drop_off_area_x1[2] =	-1768.2949
valet_drop_off_area_x2[2] =	-1729.0944
valet_drop_off_area_y1[2] =	958.9591 
valet_drop_off_area_y2[2] =	942.1840 

valet_drop_off_area_x1[3] =	2050.8811 
valet_drop_off_area_x2[3] =	2016.9059 
valet_drop_off_area_y1[3] =	1958.5413 
valet_drop_off_area_y2[3] =	1871.0653




car_park_area_x1[1] = 336.0477  
car_park_area_y1[1] = -1452.2249
car_park_area_z1[1] = 31.9445  							
car_park_area_x2[1] = 271.6679 
car_park_area_y2[1] = -1550.2423
car_park_area_z2[1] = 23.1681  

car_park_area_x1[2] = -1760.1785 
car_park_area_y1[2] = 972.2405 
car_park_area_z1[2] = 16.1633 
car_park_area_x2[2] = -1679.6055
car_park_area_y2[2] = 1064.2927 
car_park_area_z2[2] = 24.8629

car_park_area_x1[3] = 1968.5016 
car_park_area_y1[3] = 1755.0790
car_park_area_z1[3] = 11.4898  
car_park_area_x2[3] = 1917.8112
car_park_area_y2[3] = 1788.1465
car_park_area_z2[3] = 17.3118  



find_drop_off_car_x1[1] = 363.3463 
find_drop_off_car_y1[1] = -1522.3734
find_drop_off_car_x2[1] = 393.1729 
find_drop_off_car_y2[1] = -1479.3210
find_drop_off_car_h1[1] = 125.0

find_drop_off_car_x3[1] = 324.8593 
find_drop_off_car_y3[1] = -1591.3274
find_drop_off_car_x4[1] = 324.8593 
find_drop_off_car_y4[1] = -1591.3274
find_drop_off_car_h2[1] = 338.0

find_drop_off_car_x3[2] = -1743.5244
find_drop_off_car_y3[2] = 930.9304 					   
find_drop_off_car_x4[2] = -1780.0813
find_drop_off_car_y4[2] = 908.4018 
find_drop_off_car_h2[2] = 270.0

find_drop_off_car_x1[2] = -1737.7147
find_drop_off_car_y1[2] = 943.7812  
find_drop_off_car_x2[2] = -1704.2163
find_drop_off_car_y2[2] = 911.4586  
find_drop_off_car_h1[2] = 45.0

find_drop_off_car_x1[3] = 2067.5288
find_drop_off_car_y1[3] = 1957.2887
find_drop_off_car_x2[3] = 2097.2629
find_drop_off_car_y2[3] = 1976.0001
find_drop_off_car_h1[3] = 120.0

find_drop_off_car_x3[3] = 2051.5361
find_drop_off_car_y3[3] = 1933.6851
find_drop_off_car_x4[3] = 2062.8179
find_drop_off_car_y4[3] = 1905.0847
find_drop_off_car_h2[3] = 7.0

drop_off_point_x[1] = 332.8306  
drop_off_point_y[1] = -1516.2509
drop_off_point_z[1]	= 34.8606

drop_off_point_x[2] = -1754.5139  
drop_off_point_y[2] = 953.5991
drop_off_point_z[2]	= 23.7500

drop_off_point_x[3] = 2035.7292
drop_off_point_y[3] = 1912.0900
drop_off_point_z[3]	= 11.1751 

valet_exit_x[0] = -1748.3761
valet_exit_y[0] = 985.3066
valet_exit_z[0]	= 17.9009			    

valet_exit_x[1] = 330.7534 
valet_exit_y[1] = -1474.6079
valet_exit_z[1]	= 24.9126 				    

valet_exit_x[2] = -1738.2800
valet_exit_y[2] = 985.5498
valet_exit_z[2]	= 16.6718

valet_exit_x[3] = 2061.3367
valet_exit_y[3] = 1808.9561
valet_exit_z[3]	= 9.6719 


VAR_FLOAT valet_spawn_x[4] valet_spawn_y[4] valet_spawn_z[4] valet_spawn_h[4]
VAR_FLOAT valet_spawn_x2[4] valet_spawn_y2[4] valet_spawn_z2[4] valet_spawn_h2[4]
		  
 
//spawn point for cars if none are found with random car check 	    
   
valet_spawn_x[1] = 	403.2297 
valet_spawn_y[1] = 	-1475.2976
valet_spawn_z[1] = 	30.0032 
valet_spawn_h[1] =  128.9467  		    
valet_spawn_x2[1] = 341.1339 
valet_spawn_y2[1] = -1556.1160
valet_spawn_z2[1] = 31.9903 
valet_spawn_h2[1] = 328.7578   		    
   
valet_spawn_x[2] = 	 -1717.2042
valet_spawn_y[2] = 	 962.7901 
valet_spawn_z[2] = 	 23.7474 
valet_spawn_h[2] =   179.9692 		    
valet_spawn_x2[2] =  -1791.2689
valet_spawn_y2[2] =  886.3627 
valet_spawn_z2[2] =  23.7500 
valet_spawn_h2[2] =  359.2426 		    

valet_spawn_x[3] = 	2073.5627
valet_spawn_y[3] = 	1879.7365
valet_spawn_z[3] = 	10.4288 
valet_spawn_h[3] =  45.5873  	
valet_spawn_x2[3] = 2084.1565
valet_spawn_y2[3] = 1965.5731
valet_spawn_z2[3] = 9.9974 
valet_spawn_h2[3] = 117.4401      



 



 









   // valet 1
   // valet 2
   // valet 3

//222.0 //valet heading
//
//333.6725 -1516.7054 34.8606 // car drop down position
//
//346.6964 -1512.2478 398.8214 -1476.9871 // get car in area 1
//
//> 165 < 86 //area 1 heading check
//
//354.2198 -1541.9437 323.4623 -1572.6309 // get car in area 2
//
//>296 < 356 //area 2 heading check
//
//

//SF



CONST_INT   NOT_CREATED				1
CONST_INT   WAITING_FOR_CAR			2
CONST_INT   RETURNING_TO_CARPORT	3
CONST_INT 	WARNING_PLAYER			4
CONST_INT 	ATTACK_PLAYER			5
CONST_INT 	VALET_DEAD				6
CONST_INT 	PICKING_UP_CAR			7
CONST_INT	DRIVE_TO_CAR_PARK		8
CONST_INT	CAR_STOLEN				9
CONST_INT	EXIT_CAR_PARK			10
CONST_INT	CHATTING				11


//	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

				REMOVE_DECISION_MAKER valet_empty_dec
				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec

				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DAMAGE TASK_NONE 0.0 100.0 100.0 100.0 1 1
				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 100.0 100.0 1 1
				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_STEAL_CAR 0.0 100.0 0.0 0.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 0.0 20.0 20.0 20.0 0 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_NONE 0.0 80.0 80.0 80.0 1 1

valet_scene_running = 1


/*
VIEW_INTEGER_VARIABLE a_pickup_car a_pickup_car
VIEW_INTEGER_VARIABLE valet_mission_car valet_mission_car
VIEW_INTEGER_VARIABLE valet_pickup_car valet_pickup_car
VIEW_INTEGER_VARIABLE drop_off_car drop_off_car
VIEW_INTEGER_VARIABLE create_drop_off_car_now create_drop_off_car_now
VIEW_INTEGER_VARIABLE blahb blahb
VIEW_INTEGER_VARIABLE VALET_MODEL valet_model
VIEW_INTEGER_VARIABLE check_car_counter check_car_counter
*/

valet_script_loop:

	WAIT 0 






IF IS_PLAYER_PLAYING player1







VAR_INT this_a this_b

//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
//		FREEZE_CAR_POSITION car FALSE
//		valet_cars_to_park = 1
//		valet_level = 5

//	ENDIF

//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
//		ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_NONE 0.0 80.0 80.0 80.0 1 1
//		TASK_CAR_DRIVE_TO_COORD -1 drop_off_car drop_off_point_x[val_area] drop_off_point_y[val_area] drop_off_point_z[val_area] 17.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 338.8148 -1525.0272 32.3047
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 2051.4150 1937.3990 11.1517 
//		ENDIF
//	ENDIF


//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3
//		IF NOT IS_CAR_DEAD valet_car[0]
//	    	ATTACH_CAMERA_TO_VEHICLE valet_car[0] 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_4
//		IF NOT IS_CAR_DEAD valet_car[1]
//	    	ATTACH_CAMERA_TO_VEHICLE valet_car[1] 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_5
//		IF NOT IS_CAR_DEAD valet_car[2]
//	    	ATTACH_CAMERA_TO_VEHICLE valet_car[2] 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_6
//		IF NOT IS_CAR_DEAD valet_pickup_car
//	    	ATTACH_CAMERA_TO_VEHICLE valet_pickup_car 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_7
//		IF NOT IS_CAR_DEAD drop_off_car
//	    	ATTACH_CAMERA_TO_VEHICLE drop_off_Car 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_8
//		IF NOT IS_CAR_DEAD mission_drop_off_car
//	    	ATTACH_CAMERA_TO_VEHICLE mission_drop_off_car 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_9
//		IF NOT IS_CAR_DEAD a_pickup_car
//	    	ATTACH_CAMERA_TO_VEHICLE a_pickup_car 0.0 -5.0 2.0 0.0 0.0 1.0 0.0 JUMP_CUT
//		ENDIF
//	ENDIF
//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_R
//		IF NOT IS_CHAR_DEAD scplayer
//	    	SET_CHAR_COORDINATES scplayer -1755.0868 944.8736 23.8906  
//		ENDIF
//	ENDIF
//
//
//
//	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_T
//		IF IS_PLAYER_PLAYING player1
//			GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 valet valet CLOTHES_TEX_EXTRA1          
//    	    BUILD_PLAYER_MODEL player1
//		ENDIF
//	ENDIF

	IF force_valet_cleanup = 1
		GOSUB valet_cleanup
	ENDIF

	IF NOT pause_valet_script = 1

		IF NOT IS_CHAR_DEAD scplayer

			IF player_is_threat_to_valet = 1
				IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
	//				SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
					player_is_threat_to_valet = 0
				ENDIF
			ELSE
				IF NOT IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
	//				SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
					player_is_threat_to_valet = 1
				ENDIF			
			ENDIF


			GOSUB check_player_location

			IF valet_scene_running = 1


				GOSUB valet_mission_control
				GOSUB valets
				GOSUB car_control
				GOSUB stop_thief


				//do this at end
				IF TIMERA > checks_time_A
					checks_time_A = TIMERA + 2300
				ENDIF

				IF TIMERA > checks_time_B
					checks_time_B = TIMERA + 100
				ENDIF

				IF TIMERA > checks_time_C
					checks_time_C = TIMERA + 900
				ENDIF




			ENDIF
		ENDIF
	ENDIF
ELSE
	IF valet_scene_running = 1
		IF player_on_valet_mission = 1
			player_on_valet_mission = 0
			flag_player_on_mission = 0
			PRINT_BIG M_FAIL 5000 1		
		ENDIF
		GOSUB valet_cleanup
	ENDIF
ENDIF

GOTO valet_script_loop



check_player_location:

	IF TIMERA > check_player_time

		LVAR_INT check_player_time

		check_player_time = TIMERA + 1000


		GET_CHAR_COORDINATES scplayer player_x player_y player_z

		val_area = 0

//		IF IS_CHAR_IN_AREA_2D scplayer 215.3643	-1651.7264 440.7311	-1369.3921 FALSE
//
//			val_Area = 1
//		ENDIF
				

		IF IS_CHAR_IN_AREA_2D scplayer -1893.4186 1119.2267 -1617.9149 828.850 FALSE

			val_Area = 2

		ENDIF
		
//		IF IS_CHAR_IN_AREA_2D scplayer 2205.5503 1772.4938 1830.5140 2086.0610 FALSE
//
//			val_Area = 3
//		ENDIF















//		val_Area = 0


			
			
				

		
//		IF player_x > 215.3643
//			IF player_x < 440.7311
//				IF player_y > -1651.7264
//					IF player_y < -1369.3921
//		IF val_Area = 1
//						
//			IF valet_scene_running = 0	
//				REMOVE_DECISION_MAKER valet_empty_dec
//				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
//
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DAMAGE TASK_NONE 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_STEAL_CAR 0.0 100.0 0.0 0.0 1 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 0.0 20.0 20.0 20.0 0 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_NONE 0.0 80.0 80.0 80.0 1 1
//			ENDIF
//			valet_scene_running = 1
//		ENDIF
//
////		IF player_x > -1893.4186
////			IF player_x < -1617.9149
////				IF player_y < 1119.2267
////					IF player_y > 828.850
//		IF val_Area = 2
//			
//			IF valet_scene_running = 0	
//				REMOVE_DECISION_MAKER valet_empty_dec
//				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
//
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DAMAGE TASK_NONE 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_STEAL_CAR 0.0 100.0 0.0 0.0 1 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 0.0 20.0 20.0 20.0 0 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_NONE 0.0 80.0 80.0 80.0 1 1
//			ENDIF
//			valet_scene_running = 1
//		ENDIF
//
////		IF player_x < 2205.5503
////			IF player_x > 1830.5140
////				IF player_y > 1772.4938
////					IF player_y < 2086.0610
//		IF val_Area = 3
//			IF valet_scene_running = 0	
//				REMOVE_DECISION_MAKER valet_empty_dec
//				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
//
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DAMAGE TASK_NONE 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 100.0 100.0 1 1
//				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_STEAL_CAR 0.0 100.0 0.0 0.0 1 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 0.0 20.0 20.0 20.0 0 1
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE valet_empty_dec EVENT_SHOT_FIRED TASK_NONE 0.0 80.0 80.0 80.0 1 1
//			ENDIF
//			valet_scene_running = 1
//		ENDIF

		IF val_Area = 0
			VAR_INT area_visible frozen_peds dont_clear_valets
			GET_AREA_VISIBLE area_visible
			IF area_visible = 0
				IF frozen_peds = 0
					IF dont_clear_valets = 0
						IF valet_scene_running = 1
							IF NOT force_valet_on_mission = 1
								IF player_on_valet_mission = 1
									player_on_valet_mission = 0
									flag_player_on_mission = 0
									PRINT VAL_A9 4000 1
									PRINT_BIG VAL_A6 4000 1
								ENDIF
						
								GOSUB valet_cleanup
								valet_scene_running = 0
							ENDIF					
						ENDIF
					ELSE
						frozen_peds = 1
						IF NOT IS_CHAR_DEAD valet[0]
							FREEZE_CHAR_POSITION valet[0] TRUE
							IF IS_CHAR_IN_ANY_CAR valet[0]
								GET_CAR_CHAR_IS_USING valet[0] car
								IF NOT IS_CAR_DEAD car
									FREEZE_CAR_POSITION car TRUE
								ENDIF
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD valet[1]
							FREEZE_CHAR_POSITION valet[1] TRUE
							IF IS_CHAR_IN_ANY_CAR valet[1]
								GET_CAR_CHAR_IS_USING valet[1] car
								IF NOT IS_CAR_DEAD car
									FREEZE_CAR_POSITION car TRUE
								ENDIF
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD valet[2]
							FREEZE_CHAR_POSITION valet[2] TRUE
							IF IS_CHAR_IN_ANY_CAR valet[2]
								GET_CAR_CHAR_IS_USING valet[2] car
								IF NOT IS_CAR_DEAD car
									FREEZE_CAR_POSITION car TRUE
								ENDIF
							ENDIF
						ENDIF						
					ENDIF
				ENDIF
			ELSE
				
				frozen_peds = 1
				IF NOT IS_CHAR_DEAD valet[0]
					FREEZE_CHAR_POSITION valet[0] TRUE
					IF IS_CHAR_IN_ANY_CAR valet[0]
						GET_CAR_CHAR_IS_USING valet[0] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car TRUE
						ENDIF
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD valet[1]
					FREEZE_CHAR_POSITION valet[1] TRUE
					IF IS_CHAR_IN_ANY_CAR valet[1]
						GET_CAR_CHAR_IS_USING valet[1] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car TRUE
						ENDIF
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD valet[2]
					FREEZE_CHAR_POSITION valet[2] TRUE
					IF IS_CHAR_IN_ANY_CAR valet[2]
						GET_CAR_CHAR_IS_USING valet[2] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car TRUE
						ENDIF
					ENDIF
				ENDIF
			ENDIF				
		ELSE
			IF frozen_peds = 1
				frozen_peds = 0
				IF NOT IS_CHAR_DEAD valet[0]
					FREEZE_CHAR_POSITION valet[0] FALSE
					IF IS_CHAR_IN_ANY_CAR valet[0]
						GET_CAR_CHAR_IS_USING valet[0] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car FALSE
						ENDIF
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD valet[1]
					FREEZE_CHAR_POSITION valet[1] FALSE
					IF IS_CHAR_IN_ANY_CAR valet[1]
						GET_CAR_CHAR_IS_USING valet[1] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car FALSE
						ENDIF
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD valet[2]
					FREEZE_CHAR_POSITION valet[2] FALSE
					IF IS_CHAR_IN_ANY_CAR valet[2]
						GET_CAR_CHAR_IS_USING valet[2] car
						IF NOT IS_CAR_DEAD car
							FREEZE_CAR_POSITION car FALSE
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF play_was_on_valet_oddjob = 1
		IF val_area = 1
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 328.1585 -1512.8881 35.0247 10.0 10.0 10.0 FALSE
				play_was_on_valet_oddjob = 0						
			ENDIF
		ENDIF
		IF val_area = 2
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -1753.9363 961.8587 23.8906 10.0 10.0 10.0 FALSE
				play_was_on_valet_oddjob = 0						
			ENDIF
		ENDIF
		IF val_area = 3
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2021.3368 1918.2360 11.3438 10.0 10.0 10.0 FALSE
				play_was_on_valet_oddjob = 0						
			ENDIF
		ENDIF
	ELSE
		IF NOT flag_player_on_mission = 1
			IF valet_oddjob_opened = 1
				IF player_on_valet_mission = 0
					IF NOT IS_CHAR_DEAD scplayer
//						IF val_area = 1
//							IF LOCATE_CHAR_ANY_MEANS_3D scplayer 328.1585 -1512.8881 35.0247 2.0 2.0 2.0 TRUE
//								IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
//									PRINT_NOW VAL_A7 5000 1 //Valet!
//									player_on_valet_mission = 1
//									flag_player_on_mission = 1
//								ELSE
//									PRINT_NOW VAL_90 1000 1 //Get a valet uniform to work as a valet.
//								ENDIF										
//							ENDIF
//						ENDIF
						IF val_area = 2
//							IF NOT IS_CHAR_DEAD valet[0]
//							AND NOT IS_CHAR_DEAD valet[1]
//							AND NOT IS_CHAR_DEAD valet[2]
								IF LOCATE_CHAR_ON_FOOT_3D scplayer -1753.9363 961.8587 23.8906 2.0 2.0 2.0 TRUE
									IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
									   IF NOT IS_WANTED_LEVEL_GREATER Player1 0
									   		PRINT_NOW VAL_A7 5000 1 //Valet!
											player_on_valet_mission = 1
											flag_player_on_mission = 1
										ELSE
											PRINT_NOW VAL_C1 1000 1 //You're too violent.
										ENDIF
									ELSE
										PRINT_NOW VAL_90 1000 1 //Get a valet uniform to work as a valet.
									ENDIF										
//								ENDIF
							ENDIF
						ENDIF
//						IF val_area = 3
//							IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2021.3368 1918.2360 11.3438 2.0 2.0 2.0 TRUE
//								IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
//									PRINT_NOW VAL_A7 5000 1 //Valet!
//									player_on_valet_mission = 1
//									flag_player_on_mission = 1
//								ELSE
//									PRINT_NOW VAL_90 1000 1 //Get a valet uniform to work as a valet.
//								ENDIF										
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
			      				 
RETURN


valet_mission_control:

	
//mission_drop_off_car

//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
//	GOSUB valet_mission_passed 
//ENDIF


	IF player_on_valet_mission = 1

		
//		VIEW_INTEGER_VARIABLE valet_mission_car valet_mission_car
//		VIEW_INTEGER_VARIABLE valet_mission_flag valet_mission_flag

		IF IS_WANTED_LEVEL_GREATER player1 1
			SWITCH_EMERGENCY_SERVICES ON					
		ENDIF
		
		IF NOT IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
			PRINT_BIG VAL_A6 4000 1
			PRINT VAL_B1 5000 1
			valet_mission_flag = 8
		ENDIF
		
		IF NOT valet_mission_car = 0
			IF NOT DOES_VEHICLE_EXIST valet_mission_car
				IF valet_time_bonus > 0 // this should only happen if player picked up mission car and then drove off far enough for it to be removed by code
					PRINT_BIG VAL_A6 4000 1	
					valet_mission_flag = 8
				ENDIF
				valet_mission_car = 0
			ELSE
				IF IS_CAR_DEAD valet_mission_car
					PRINT_BIG VAL_A6 4000 1
					PRINT VAL_B6 5000 1
					valet_mission_flag = 8				
				ENDIF
			ENDIF
		ENDIF	

		IF tell_player_to_kill_valet = 0
			IF NOT IS_CAR_DEAD mission_drop_off_car
				GET_CAR_BLOCKING_CAR mission_drop_off_car a_car
				steal_this_car = a_car
			ENDIF
		ENDIF
			

		IF valet_mission_flag = 0
			IF HAS_MODEL_LOADED wmyva
				IF IS_CHAR_DEAD valet[0]

					CREATE_CHAR PEDTYPE_CIVMALE wmyva -1783.8038 968.6431 23.7343 valet[0]
					SET_CHAR_DECISION_MAKER valet[0] valet_empty_dec
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER valet[0] TRUE
					SET_CHAR_SUFFERS_CRITICAL_HITS valet[0] FALSE					
					valet_task[0] = WAITING_FOR_CAR
					valet_last_task[0] = WAITING_FOR_CAR
				ENDIF 

				IF IS_CHAR_DEAD valet[1]
					CREATE_CHAR PEDTYPE_CIVMALE wmyva -1782.6503 965.0251 23.8906 valet[1]
					SET_CHAR_DECISION_MAKER valet[1] valet_empty_dec
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER valet[1] TRUE
					SET_CHAR_SUFFERS_CRITICAL_HITS valet[1] FALSE					
					valet_task[1] = WAITING_FOR_CAR
					valet_last_task[1] = WAITING_FOR_CAR
				ENDIF

				IF IS_CHAR_DEAD valet[2]
					CREATE_CHAR PEDTYPE_CIVMALE wmyva -1783.7314 965.4009 23.8961 valet[2]
					SET_CHAR_DECISION_MAKER valet[2] valet_empty_dec
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER valet[2] TRUE
					SET_CHAR_SUFFERS_CRITICAL_HITS valet[2] FALSE					
					valet_task[2] = WAITING_FOR_CAR
					valet_last_task[2] = WAITING_FOR_CAR
				ENDIF


				SET_PED_DENSITY_MULTIPLIER 0.3
				SWITCH_EMERGENCY_SERVICES OFF
				
				valet_mission_car = 0
				LOAD_MISSION_TEXT VALET1
				
				CLEAR_PRINTS
				PRINT_BIG VAL_A7 3000 1
				LVAR_INT valet_timer valet_level
				VAR_INT valet_cars_to_park
				LVAR_INT pickup_car_blip parking_space_blip 
				VAR_INT valet_mission_car valet_countdown valet_mission_flag
				LVAR_FLOAT target_x target_y target_z
				total_cars_parked = 0
				valet_timer = TIMERA + 4000
				valet_mission_flag = 1
				IF NOT IS_CHAR_DEAD valet[0]
					SET_CHAR_CANT_BE_DRAGGED_OUT valet[0] TRUE
				ENDIF
				IF NOT IS_CHAR_DEAD valet[1]
					SET_CHAR_CANT_BE_DRAGGED_OUT valet[1] TRUE
				ENDIF
				IF NOT IS_CHAR_DEAD valet[2]
					SET_CHAR_CANT_BE_DRAGGED_OUT valet[2] TRUE
				ENDIF
			ELSE
				REQUEST_MODEL wmyva
			ENDIF
		ENDIF
		IF valet_mission_flag = 1
			IF TIMERA > valet_timer
				valet_cars_to_park = 3 
				valet_countdown = 2			//changed this
				valet_level = 1
				CLEAR_PRINTS
				valet_mission_flag = 2
			ENDIF
		ENDIF

		IF valet_mission_flag = 2
			IF TIMERA > valet_timer
				CLEAR_PRINTS
				PRINT_WITH_NUMBER_BIG VAL_60 valet_level 4000 1
				PRINT_WITH_2_NUMBERS_NOW VAL_76 valet_cars_to_park valet_countdown 4000 1
				valet_countdown *= 60000
				
				CLEAR_ONSCREEN_COUNTER valet_countdown
				CLEAR_ONSCREEN_COUNTER valet_cars_to_park
				DISPLAY_ONSCREEN_TIMER_WITH_STRING valet_countdown TIMER_DOWN VAL_A8
				
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING valet_cars_to_park COUNTER_DISPLAY_NUMBER 1 VAL_A3

				valet_timer = TIMERA + 5000
				valet_mission_flag = 4
				IF valet_task[0] = VALET_DEAD
					valet_task[0] = NOT_CREATED
					valet_mission_dead[0] = 0
				ENDIF
				IF valet_task[1] = VALET_DEAD
					valet_task[1] = NOT_CREATED
					valet_mission_dead[1] = 0
				ENDIF
				IF valet_task[2] = VALET_DEAD
					valet_task[2] = NOT_CREATED
					valet_mission_dead[2] = 0
				ENDIF
				IF NOT drop_off_car = 0
					mission_drop_off_car = drop_off_car
				ENDIF
				IF NOT valet_pickup_car = 0
					mission_drop_off_car = valet_pickup_car
				ENDIF	
			ENDIF
		ENDIF

		IF valet_mission_flag = 3

			IF TIMERA > valet_timer
				valet_mission_flag = 4
			ENDIF
		ENDIF

		VAR_INT valet_time_bonus

		IF IS_CHAR_DEAD valet[0]
		AND valet_mission_dead[0] = 0
		AND valet_task[0] = VALET_DEAD
			valet_mission_dead[0] = 1
			valet_countdown -= 20000
			IF valet_countdown < 0
				valet_countdown = 0
			ENDIF
			PRINT VAL_A19 4000 1
		ENDIF

		IF IS_CHAR_DEAD valet[1]
		AND valet_mission_dead[1] = 0
		AND valet_task[1] = VALET_DEAD
			valet_mission_dead[1] = 1
			valet_countdown -= 20000
			IF valet_countdown < 0
				valet_countdown = 0
			ENDIF
			PRINT VAL_A19 4000 1
		ENDIF


		IF IS_CHAR_DEAD valet[2]
		AND valet_mission_dead[2] = 0
		AND valet_task[2] = VALET_DEAD
			valet_mission_dead[2] = 1
			valet_countdown -= 20000
			IF valet_countdown < 0
				valet_countdown = 0
			ENDIF
			PRINT VAL_A19 4000 1
		ENDIF


			
		IF valet_mission_flag > 3
			IF valet_countdown = 0
				CLEAR_PRINTS
				PRINT VAL_47 4000 1
				PRINT_BIG VAL_A6 4000 1
				valet_mission_flag = 8				
			ENDIF
		ENDIF

		IF valet_mission_flag = 4
			IF NOT valet_pickup_car = 0
				IF NOT IS_CAR_DEAD valet_pickup_car					
					IF valet_pickup_Car = mission_drop_off_car
						GET_DRIVER_OF_CAR valet_pickup_Car a_ped
						IF a_ped = -1
	//						SET_CAR_AS_MISSION_CAR valet_mission_car
							valet_mission_flag = 20	
							PRINT VAL_84 4000 1  // Pick up the ~g~valet's uniform.
							ADD_BLIP_FOR_CAR valet_pickup_car pickup_car_blip	
							SET_BLIP_AS_FRIENDLY pickup_car_blip TRUE
							valet_mission_car = valet_pickup_car
							valet_mission_flag = 5											
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF valet_mission_flag = 5
			IF NOT IS_CAR_DEAD valet_mission_car
			
				GET_DRIVER_OF_CAR valet_mission_car a_ped
				IF NOT a_ped = -1
					IF a_ped = scplayer
						CLEAR_PRINTS
						PRINT VAL_25 4000 1
						REMOVE_BLIP pickup_car_blip							
						valet_time_bonus = 60
						SET_CAR_HEALTH valet_mission_car 1000
						
						CLEAR_ONSCREEN_COUNTER valet_time_bonus
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING valet_time_bonus COUNTER_DISPLAY_NUMBER 2 VAL_A17
						
						GET_PARKING_NODE_IN_AREA car_park_area_x1[val_area] car_park_area_y1[val_area] car_park_area_z1[val_area] car_park_area_x2[val_area] car_park_area_y2[val_area] car_park_area_z2[val_area] target_x target_y target_z											

						CLEAR_AREA target_x target_y target_z 5.0 FALSE

						parking_x = target_x
						parking_y = target_y
						parking_z = target_z
						LVAR_FLOAT a_heading
//						VIEW_FLOAT_VARIABLE a_heading a_heading

						REMOVE_BLIP parking_space_blip
						ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip

						valet_mission_flag = 6

					ELSE
						IF a_ped = valet[0]
						OR a_ped = valet[1]
						OR a_ped = valet[2]
							REMOVE_BLIP pickup_car_blip
							PRINT VAL_79 4000 1
							valet_mission_flag = 4
							valet_mission_car = 0
							mission_drop_off_car = 0
						ENDIF
					ENDIF
				ENDIF
			ELSE
				// mission car destroyed
				REMOVE_BLIP pickup_car_blip
//				PRINT VAL_79 4000 1
				valet_mission_flag = 4
				valet_mission_car = 0
				mission_drop_off_car = 0
			ENDIF
		ENDIF

		IF valet_mission_flag = 6

			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CAR_DEAD valet_mission_car
					GET_CAR_HEALTH valet_mission_car valet_time_bonus
					a_int = 17 - valet_level
					a_int *= 20
					valet_time_bonus += -1000
					ABS valet_time_bonus 

					a_float =# valet_time_bonus
					a_float2 =# a_int

					a_float3 = a_float / a_float2
					IF a_float3 > 1.0
						a_float3 = 1.0
					ENDIF
						
					a_float3 -= 1.0
					ABS a_float3

					a_float3 *= 45.0
					valet_time_bonus =# a_float3


					IF valet_time_bonus > 45
					   valet_time_bonus = 45	
					ENDIF
					IF valet_time_bonus < 0
						valet_time_bonus = 0
					ENDIF

					GET_DRIVER_OF_CAR valet_mission_car a_ped
					IF NOT a_ped = scplayer
						REMOVE_BLIP parking_space_blip
						ADD_BLIP_FOR_CAR valet_mission_car pickup_car_blip
						SET_BLIP_AS_FRIENDLY pickup_car_blip TRUE
						CLEAR_ONSCREEN_COUNTER valet_time_bonus
						valet_mission_flag = 7
					ENDIF
					IF NOT a_ped = -1
					AND NOT a_ped = scplayer
					AND NOT a_ped = valet[0]
					AND NOT a_ped = valet[1]
					AND NOT a_ped = valet[2]
						valet_mission_car = 0
						REMOVE_BLIP pickup_car_blip
						CLEAR_ONSCREEN_COUNTER valet_time_bonus
						valet_mission_flag = 4
						PRINT VAL_A4 4000 1
					ENDIF

					IF IS_CHAR_IN_ANY_CAR scplayer
						IF NOT IS_CHAR_IN_CAR scplayer valet_mission_car
							PRINT_NOW VAL_A18 1000 1
						ENDIF
					ENDIF



					IF LOCATE_STOPPED_CAR_3D valet_mission_car target_x target_y target_z 4.0 4.0 4.0 TRUE
						REMOVE_BLIP parking_space_blip
						SET_CAR_ENGINE_BROKEN valet_mission_car TRUE
						MARK_CAR_AS_NO_LONGER_NEEDED valet_mission_car
						

						CLEAR_ONSCREEN_COUNTER valet_time_bonus
						valet_i	= valet_countdown




						
						GET_CLOSEST_CAR_NODE_WITH_HEADING target_x target_y target_z x y z a_heading
						GET_CAR_COORDINATES valet_mission_car x y z												 

						GET_DISTANCE_BETWEEN_COORDS_2D x y target_x target_y a_gap
						GET_CAR_HEADING valet_mission_car a_heading2

						LVAR_FLOAT a_heading2 a_gap a_float2 a_float3 angle_margin

						ABS a_heading
						IF a_heading > 180.0
							a_heading -= 180.0
						ENDIF

						ABS a_heading2
						IF a_heading2 > 180.0
							a_heading2 -= 180.0
						ENDIF
						
						a_float2 = a_heading - a_heading2
						ABS a_float2

						a_float =# valet_level
						a_float *= 2.0
						angle_margin = 45.0 - a_float
						a_float3 = a_float2 / angle_margin
						a_float = 1.0 - a_float3
						IF a_float < 0.0
							a_float = 0.0
						ENDIF

						a_float *= 10.0
						angle_bonus =# a_float
						parking_bonus =# a_float

						a_float =# valet_level
						a_float -= 20.0  //15 levels + 5
						ABS a_float
						a_float /= 6.0

						a_float2 = a_gap / a_float
						a_float3 = 1.0 - a_float2
						IF a_float3 < 0.0
							a_float3 = 0.0
						ENDIF

						a_float3 *= 5.0
						a_int =# a_float3
						distance_bonus = a_int

						VAR_INT distance_bonus angle_bonus parking_bonus a_int

						parking_bonus += a_int

						IF NOT parking_bonus = 1
						AND NOT valet_time_bonus = 1
							PRINT_WITH_2_NUMBERS_NOW VAL_A10 parking_bonus valet_time_bonus 2500 1
						ENDIF

						IF parking_bonus = 1
						AND NOT valet_time_bonus = 1
							PRINT_WITH_2_NUMBERS_NOW VAL_A12 parking_bonus valet_time_bonus 2500 1
						ENDIF

						IF NOT parking_bonus = 1
						AND valet_time_bonus = 1
							PRINT_WITH_2_NUMBERS_NOW VAL_A11 parking_bonus valet_time_bonus 2500 1
						ENDIF

						IF parking_bonus = 0
						AND valet_time_bonus = 0
							PRINT_WITH_2_NUMBERS_NOW VAL_A13 parking_bonus valet_time_bonus 2500 1
						ENDIF
	
						a_int = parking_bonus + valet_time_bonus

						IF NOT a_int = 1
							PRINT_WITH_NUMBER VAL_A14 a_int 2500 1
						ELSE
							PRINT_WITH_NUMBER VAL_A15 a_int 2500 1
						ENDIF

						PRINT VAL_A16 3000 1

						valet_time_bonus *= 1000
						parking_bonus *= 1000
						valet_countdown += valet_time_bonus
						valet_countdown += parking_bonus

						valet_time_bonus = 0
						

								 
						valet_mission_car = 0								 
								 
						valet_cars_to_park --
						total_cars_parked ++

						
						GET_INT_STAT VALET_CARS_PARKED a_int
						IF total_cars_parked > a_int
							SET_INT_STAT VALET_CARS_PARKED total_cars_parked
						ENDIF
						
						IF valet_cars_to_park = 0
							CLEAR_PRINTS
							CLEAR_ONSCREEN_TIMER valet_countdown
							CLEAR_ONSCREEN_COUNTER valet_cars_to_park

							valet_level++
							valet_mission_flag = 2
							valet_cars_to_park = valet_level + 2
							valet_countdown = 2

							IF valet_level < 6
								a_int = valet_level - 1
		
								PRINT_WITH_NUMBER_BIG VAL_62 a_int 3000 1
								a_int = valet_level * 100
								a_int -= 100
								PRINT_WITH_NUMBER VAL_65 a_int 4000 1
								ADD_SCORE player1 a_int
								valet_timer = TIMERA + 5000
							ENDIF

							IF valet_level = 6
								GOSUB valet_mission_passed
							ENDIF

						ELSE
							valet_mission_flag = 4
						ENDIF
						
						//do shit for parking car
					ENDIF
				ELSE
					PRINT_BIG VAL_A6 4000 1	
					valet_mission_flag = 8
				ENDIF
			ENDIF			
		ENDIF

		IF valet_mission_flag = 7
			IF NOT IS_CAR_DEAD valet_mission_car
			
				GET_DRIVER_OF_CAR valet_mission_car a_ped
				IF NOT a_ped = -1
					IF a_ped = scplayer
						REMOVE_BLIP	pickup_car_blip
						ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
						
						CLEAR_ONSCREEN_COUNTER valet_time_bonus
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING valet_time_bonus COUNTER_DISPLAY_NUMBER 2 VAL_A17

						valet_mission_flag = 6
					ELSE
						IF a_ped = valet[0]
						OR a_ped = valet[1]
						OR a_ped = valet[2]
							REMOVE_BLIP pickup_car_blip
							PRINT VAL_79 4000 1
							valet_mission_flag = 4
							valet_mission_car = 0
							mission_drop_off_car = 0
						ENDIF
					ENDIF
				ENDIF
			ELSE
				// mission car destroyed
				REMOVE_BLIP pickup_car_blip
//				PRINT VAL_79 4000 1
				valet_mission_flag = 4
				valet_mission_car = 0
				mission_drop_off_car = 0
			ENDIF
		ENDIF

		IF valet_mission_flag = 8
			REMOVE_BLIP pickup_car_blip
			REMOVE_BLIP parking_space_blip
			CLEAR_ONSCREEN_COUNTER valet_time_bonus
			CLEAR_ONSCREEN_TIMER valet_countdown
			CLEAR_ONSCREEN_COUNTER valet_cars_to_park
			SET_PED_DENSITY_MULTIPLIER 1.0
			SWITCH_EMERGENCY_SERVICES ON											
			
			player_on_valet_mission = 0
			flag_player_on_mission = 0					 
			valet_mission_flag = 0
			valet_mission_car = 0
			play_was_on_valet_oddjob = 1
			SET_WANTED_MULTIPLIER 1.0
			IF NOT IS_CHAR_DEAD valet[0]
				SET_CHAR_CANT_BE_DRAGGED_OUT valet[0] FALSE
			ENDIF
			IF NOT IS_CHAR_DEAD valet[1]
				SET_CHAR_CANT_BE_DRAGGED_OUT valet[1] FALSE
			ENDIF
			IF NOT IS_CHAR_DEAD valet[2]
				SET_CHAR_CANT_BE_DRAGGED_OUT valet[2] FALSE
			ENDIF
			//register oddjob mission passed
		ENDIF

	ENDIF

RETURN



valets:

	valet_i = 0

	WHILE valet_i < 3

		IF NOT IS_CHAR_DEAD valet[valet_i]
			IF IS_CHAR_STUCK_UNDER_CAR valet[valet_i]
				GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_DIE_NAMED_ANIM valet_task_status
				IF valet_task_status = FINISHED_TASK	
					CLEAR_CHAR_TASKS_IMMEDIATELY valet[valet_i]					
					TASK_DIE_NAMED_ANIM valet[valet_i] KO_skid_front PED 1000.0 0 
				ENDIF
			ENDIF
		ENDIF




//		IF NOT IS_CHAR_DEAD valet[valet_i]
//			GET_CHAR_HIGHEST_PRIORITY_EVENT valet[valet_i] valet_task_status
//			IF valet_task_status = EVENT_GOT_KNOCKED_OVER_BY_CAR
////				GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_DIE valet_task_status
////				IF valet_task_status = FINISHED_TASK					
////					TASK_DIE valet[valet_i]
//				SET_CHAR_HEALTH valet[valet_i] 0
////				ENDIF
//			ENDIF
//		ENDIF
	


		IF NOT valet_task[valet_i] = WARNING_PLAYER
		AND NOT valet_task[valet_i] = ATTACK_PLAYER
			IF NOT IS_CHAR_DEAD valet[valet_i]
			AND NOT IS_CHAR_DEAD scplayer
				IF IS_CHAR_RESPONDING_TO_EVENT valet[valet_i] EVENT_DAMAGE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer valet[valet_i] 5.0 5.0 5.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR valet[valet_i] scplayer
						valet_task[valet_i] = ATTACK_PLAYER
						this_a ++
						CLEAR_CHAR_TASKS valet[valet_i]						
						IF NOT IS_CHAR_DEAD valet[0]
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer valet[0] 15.0 15.0 15.0 FALSE	
								valet_task[0] = ATTACK_PLAYER
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD valet[1]
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer valet[1] 15.0 15.0 15.0 FALSE	
								valet_task[1] = ATTACK_PLAYER
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD valet[2]
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer valet[2] 15.0 15.0 15.0 FALSE	
								valet_task[2] = ATTACK_PLAYER
							ENDIF
						ENDIF
					ELSE
						valet_task[valet_i] = valet_last_task[valet_i]
					ENDIF
				ENDIF
				IF NOT valet_task[valet_i] = CAR_STOLEN 
					IF valet_event = EVENT_DRAGGED_OUT_CAR
//						CLEAR_CHAR_TASKS_IMMEDIATELY valet[valet_i]
						valet_task[valet_i] = CAR_STOLEN
					ENDIF
				ENDIF
//					CLEAR_CHAR_TASKS_IMMEDIATELY valet[valet_i]
//					IF valet_warnings_given > 4
//					OR NOT IS_CHAR_IN_ANY_CAR scplayer
//					OR IS_CHAR_RESPONDING_TO_EVENT valet[valet_i] EVENT_DRAGGED_OUT_CAR
//						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer valet[valet_i] 15.0 15.0 15.0 FALSE
//							valet_task[0] = ATTACK_PLAYER		
//							valet_task[1] = ATTACK_PLAYER
//							valet_task[2] = ATTACK_PLAYER
//						ENDIF
//					ELSE
//						valet_task[valet_i] = WARNING_PLAYER
//					ENDIF
								
			ENDIF
		ENDIF


		IF NOT valet_task[valet_i] = NOT_CREATED
			
			IF DOES_CHAR_EXIST valet[valet_i]
				IF IS_CHAR_DEAD valet[valet_i]
					GET_DEAD_CHAR_COORDINATES valet[valet_i] x y z
					IF NOT valet_task[valet_i] = VALET_DEAD
						IF NOT IS_CHAR_DEAD scplayer
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer x y z 15.0 15.0 15.0 FALSE
								valet_task[0] = ATTACK_PLAYER		
								valet_task[1] = ATTACK_PLAYER
								valet_task[2] = ATTACK_PLAYER			
							ENDIF
						ENDIF						

						IF player_on_valet_mission = 1

							IF x < car_park_area_x1[val_area] 
							OR y < car_park_area_y1[val_area] 
							OR z < car_park_area_z1[val_area] 
							OR x > car_park_area_x2[val_area] 
							OR y > car_park_area_y2[val_area] 
							OR z > car_park_area_z2[val_area]
								ALTER_WANTED_LEVEL_NO_DROP player1 1
							ENDIF
						ENDIF
					ENDIF
					valet_task[valet_i] = VALET_DEAD
					MARK_CHAR_AS_NO_LONGER_NEEDED valet[valet_i]
					
				ENDIF
			ENDIF			
		ENDIF
			
			

		SWITCH valet_task[valet_i]

			CASE NOT_CREATED
				IF valet_can_be_created[valet_i] = 1
					REQUEST_MODEL wmyva
					IF HAS_MODEL_LOADED wmyva
						IF val_Area = 1
							CREATE_CHAR PEDTYPE_MISSION1 wmyva valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] valet[valet_i]
							SET_CHAR_HEADING valet[valet_i] valet_la_h[valet_i]
						ENDIF
						IF val_Area = 2
							CREATE_CHAR PEDTYPE_MISSION1 wmyva valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] valet[valet_i]
							SET_CHAR_HEADING valet[valet_i] valet_sf_h[valet_i]
						ENDIF
						IF val_Area = 3
							CREATE_CHAR PEDTYPE_MISSION1 wmyva valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] valet[valet_i]
							SET_CHAR_HEADING valet[valet_i] valet_vg_h[valet_i]
						ENDIF

						SET_CHAR_DECISION_MAKER valet[valet_i] valet_empty_dec
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER valet[valet_i] TRUE
						SET_CHAR_SUFFERS_CRITICAL_HITS valet[valet_i] FALSE					
//						SET_FOLLOW_NODE_THRESHOLD_DISTANCE valet[valet_i] 35.0
						valet_task[valet_i] = WAITING_FOR_CAR
						valet_last_task[valet_i] = WAITING_FOR_CAR
					ENDIF
				ENDIF
			BREAK

			CASE WAITING_FOR_CAR
				IF TIMERA > checks_time_A
					IF NOT IS_CHAR_DEAD valet[valet_i]
						IF val_Area = 1
							IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] 2.0 2.0 2.0 FALSE
								OPEN_SEQUENCE_TASK valet_seq
									TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] PEDMOVE_RUN -2
									TASK_ACHIEVE_HEADING -1 valet_la_h[valet_i]
								CLOSE_SEQUENCE_TASK valet_seq
								PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
								CLEAR_SEQUENCE_TASK valet_seq
								valet_task[valet_i] = RETURNING_TO_CARPORT
							ENDIF
						ENDIF
						IF val_Area = 2
							IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] 2.0 2.0 2.0 FALSE
								OPEN_SEQUENCE_TASK valet_seq
									TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] PEDMOVE_RUN -2
									TASK_ACHIEVE_HEADING -1 valet_sf_h[valet_i]
								CLOSE_SEQUENCE_TASK valet_seq
								PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
								CLEAR_SEQUENCE_TASK valet_seq

								valet_task[valet_i] = RETURNING_TO_CARPORT
							ELSE
								GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_ACHIEVE_HEADING valet_Task_status
								IF valet_task_Status = FINISHED_TASK
									GET_CHAR_HEADING valet[valet_i] val_h
									val_h -= valet_sf_h[valet_i]
									ABS val_h
									IF NOT val_h < 10.0
										TASK_ACHIEVE_HEADING valet[valet_i] valet_sf_h[valet_i]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						IF val_Area = 3
							IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] 2.0 2.0 2.0 FALSE
								OPEN_SEQUENCE_TASK valet_seq
									TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] PEDMOVE_RUN -2
									TASK_ACHIEVE_HEADING -1 valet_vg_h[valet_i]
								CLOSE_SEQUENCE_TASK valet_seq
								PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
								CLEAR_SEQUENCE_TASK valet_seq

								valet_task[valet_i] = RETURNING_TO_CARPORT
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE RETURNING_TO_CARPORT
				IF TIMERA > checks_time_B
					IF NOT IS_CHAR_DEAD valet[valet_i]
						GET_SCRIPT_TASK_STATUS valet[valet_i] PERFORM_SEQUENCE_TASK valet_task_status
						IF valet_task_status = FINISHED_TASK
							IF val_Area = 1
								IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] 3.0 3.0 3.0 FALSE
									OPEN_SEQUENCE_TASK valet_seq
										TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] PEDMOVE_RUN -2
										TASK_ACHIEVE_HEADING -1 valet_la_h[valet_i]
									CLOSE_SEQUENCE_TASK valet_seq
									PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
									CLEAR_SEQUENCE_TASK valet_seq
								ELSE
//									CLEAR_CHAR_TASKS valet[valet_i]
									valet_task[valet_i] = WAITING_FOR_CAR
								ENDIF
							ENDIF
							IF val_Area = 2
								IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] 3.0 3.0 3.0 FALSE
									OPEN_SEQUENCE_TASK valet_seq
										TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] PEDMOVE_RUN -2
										TASK_ACHIEVE_HEADING -1 valet_sf_h[valet_i]
									CLOSE_SEQUENCE_TASK valet_seq
									PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
									CLEAR_SEQUENCE_TASK valet_seq
								ELSE
//									CLEAR_CHAR_TASKS valet[valet_i]
									valet_task[valet_i] = WAITING_FOR_CAR
								ENDIF
							ENDIF
							IF val_Area = 3
								IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] 3.0 3.0 3.0 FALSE
									OPEN_SEQUENCE_TASK valet_seq
										TASK_FOLLOW_PATH_NODES_TO_COORD -1 valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] PEDMOVE_RUN -2
										TASK_ACHIEVE_HEADING -1 valet_vg_h[valet_i]
									CLOSE_SEQUENCE_TASK valet_seq
									PERFORM_SEQUENCE_TASK valet[valet_i] valet_seq
									CLEAR_SEQUENCE_TASK valet_seq
								ELSE
//									CLEAR_CHAR_TASKS valet[valet_i]
									valet_task[valet_i] = WAITING_FOR_CAR
								ENDIF
							ENDIF
						ENDIF					
					ENDIF
				ENDIF
			BREAK

			CASE EXIT_CAR_PARK
				IF NOT IS_CHAR_DEAD valet[valet_i]
					GET_SCRIPT_TASK_STATUS valet[valet_i] PERFORM_SEQUENCE_TASK valet_task_status
					IF valet_task_status = FINISHED_TASK
						IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_exit_x[val_area] valet_exit_y[val_area] valet_exit_z[val_area] 3.0 3.0 3.0 FALSE
							TASK_FOLLOW_PATH_NODES_TO_COORD valet[valet_i] valet_exit_x[val_area] valet_exit_y[val_area] valet_exit_z[val_area] PEDMOVE_RUN -2
//							TASK_FOLLOW_PATH_NODES_TO_COORD valet[valet_i] -1738.2800 985.5498 16.6718 PEDMOVE_RUN -2
						ELSE
							valet_last_task[valet_i] = RETURNING_TO_CARPORT 
							valet_task[valet_i] = RETURNING_TO_CARPORT
						ENDIF
					ENDIF
				ENDIF			  
			BREAK

			CASE WARNING_PLAYER
				IF NOT IS_CHAR_DEAD valet[valet_i]					
					IF NOT IS_CHAR_RESPONDING_TO_EVENT valet[valet_i] EVENT_DAMAGE					  
						TASK_PLAY_ANIM_NON_INTERRUPTABLE valet[valet_i] fucku PED 4.0 FALSE FALSE FALSE FALSE -2
						valet_warnings_given ++
					ENDIF
					valet_task[valet_i] = valet_last_task[valet_i]
				ENDIF
			BREAK

			CASE ATTACK_PLAYER
				IF NOT IS_CHAR_DEAD valet[valet_i]
					GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_KILL_CHAR_ON_FOOT valet_task_status
					IF valet_task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT valet[valet_i] scplayer
					ENDIF
					IF TIMERA > checks_time_B 
						IF NOT IS_CHAR_DEAD scplayer
							IF val_Area = 1
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D valet[valet_i] scplayer 15.0 15.0 15.0 FALSE
								OR NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] 10.0 10.0 10.0 FALSE
									CLEAR_CHAR_TASKS valet[valet_i]
									
									valet_task[valet_i] = valet_last_task[valet_i]
								ENDIF
							ENDIF
							IF val_Area = 2
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D valet[valet_i] scplayer 15.0 15.0 15.0 FALSE
								OR NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] 10.0 10.0 10.0 FALSE
									CLEAR_CHAR_TASKS valet[valet_i]
									
									valet_task[valet_i] = valet_last_task[valet_i]
								ENDIF
							ENDIF
							IF val_Area = 3
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D valet[valet_i] scplayer 15.0 15.0 15.0 FALSE
								OR NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] 10.0 10.0 10.0 FALSE
									CLEAR_CHAR_TASKS valet[valet_i]
									
									valet_task[valet_i] = valet_last_task[valet_i]
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF				
			BREAK

			CASE PICKING_UP_CAR
				IF NOT IS_CHAR_DEAD valet[valet_i]

					IF val_Area = 1
						IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] 23.0 23.0 8.0 FALSE
							valet_task[valet_i] = WAITING_FOR_CAR
							valet_last_task[valet_i] = WAITING_FOR_CAR
							valet_car[valet_i] = 0
						ENDIF
					ENDIF
					IF val_Area = 2
						IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_sf_x[valet_i] valet_sf_y[valet_i] valet_sf_z[valet_i] 23.0 23.0 8.0 FALSE
							valet_task[valet_i] = WAITING_FOR_CAR
							valet_last_task[valet_i] = WAITING_FOR_CAR
							valet_car[valet_i] = 0
						ENDIF
					ENDIF
					IF val_Area = 3
						IF NOT LOCATE_CHAR_ANY_MEANS_3D valet[valet_i] valet_vg_x[valet_i] valet_vg_y[valet_i] valet_vg_z[valet_i] 23.0 23.0 8.0 FALSE
							valet_task[valet_i] = WAITING_FOR_CAR
							valet_last_task[valet_i] = WAITING_FOR_CAR
							valet_car[valet_i] = 0
						ENDIF
					ENDIF

					IF NOT valet_task[valet_i] = WAITING_FOR_CAR
						GET_SCRIPT_TASK_STATUS valet[valet_i] PERFORM_SEQUENCE_TASK valet_task_status
						IF valet_task_status = FINISHED_TASK
							IF NOT IS_CAR_DEAD valet_car[valet_i]
								IF IS_CHAR_IN_CAR valet[valet_i] valet_car[valet_i]

									IF valet_car[valet_i] = valet_pickup_car
										valet_pickup_car = 0
									ENDIF
									GET_PARKING_NODE_IN_AREA car_park_area_x1[val_area] car_park_area_y1[val_area] car_park_area_z1[val_area] car_park_area_x2[val_area] car_park_area_y2[val_area] car_park_area_z2[val_area] x y z
									
									VAR_FLOAT parking_x parking_y parking_z
									parking_x = x
									parking_y = y
									parking_z = z
									IF IS_VEHICLE_ATTACHED valet_car[valet_i]
										DETACH_CAR valet_car[valet_i] 0.0 0.0 0.0 FALSE
									ENDIF
									TASK_CAR_DRIVE_TO_COORD valet[valet_i] valet_car[valet_i] x y z 15.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
									IF player_on_valet_mission = 1
										SET_CHAR_CANT_BE_DRAGGED_OUT valet[valet_i] TRUE
									ENDIF
									valet_task[valet_i] = DRIVE_TO_CAR_PARK
									valet_last_task[valet_i] = PICKING_UP_CAR
								ELSE
									IF NOT IS_CAR_DEAD valet_car[valet_i]
										IF LOCATE_CHAR_ANY_MEANS_CAR_3D valet[valet_i] valet_car[valet_i] 20.0 20.0 20.0 FALSE
											OPEN_SEQUENCE_TASK valet_seq
												TASK_PAUSE -1 3000
												TASK_ENTER_CAR_AS_DRIVER -1 valet_car[valet_i] -2
											CLOSE_SEQUENCE_TASK valet_seq
											this_b ++
											PERFORM_SEQUENCE_TASK valet[valet_i] valet_Seq
											CLEAR_SEQUENCE_TASK valet_seq
										ENDIF
									ELSE
										valet_task[valet_i] = WAITING_FOR_CAR
										valet_last_task[valet_i] = WAITING_FOR_CAR	
										valet_car[valet_i] = 0
									ENDIF
								ENDIF

							ELSE
								valet_task[valet_i] = WAITING_FOR_CAR
								valet_last_task[valet_i] = WAITING_FOR_CAR
								valet_car[valet_i] = 0
							ENDIF
						ELSE
							IF NOT IS_CAR_DEAD valet_car[valet_i]
							AND NOT IS_CHAR_DEAD scplayer
								GET_CAR_CHAR_IS_USING scplayer a_car
								IF a_car = valet_car[valet_i]							
									IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet									
										IF NOT IS_CHAR_GETTING_IN_TO_A_CAR valet[valet_i]
										AND NOT IS_CHAR_IN_ANY_CAR valet[valet_i]																													
											CLEAR_CHAR_TASKS valet[valet_i]
											valet_task[valet_i] = WAITING_FOR_CAR
											valet_car[valet_i] = 0
										ENDIF
									ENDIF
								ENDIF		
								IF NOT IS_CAR_DEAD valet_car[valet_i]
									IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D valet[valet_i] valet_car[valet_i] 20.0 20.0 20.0 FALSE
											valet_task[valet_i] = WAITING_FOR_CAR
											valet_last_task[valet_i] = WAITING_FOR_CAR	
											valet_car[valet_i] = 0
									ENDIF
								ENDIF
							ELSE
							ENDIF
						ENDIF
					ENDIF
				ENDIF				
			BREAK

			CASE DRIVE_TO_CAR_PARK
				IF NOT IS_CHAR_DEAD valet[valet_i]				
					GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_CAR_DRIVE_TO_COORD valet_task_status
					IF valet_task_status = FINISHED_TASK
						IF IS_CHAR_IN_AREA_3D valet[valet_i] car_park_area_x1[val_area] car_park_area_y1[val_area] car_park_area_z1[val_area] car_park_area_x2[val_area] car_park_area_y2[val_area] car_park_area_z2[val_area] FALSE
							valet_task[valet_i] = RETURNING_TO_CARPORT
							valet_last_task[valet_i] = RETURNING_TO_CARPORT
						ELSE
							valet_task[valet_i] = RETURNING_TO_CARPORT
							valet_last_task[valet_i] = RETURNING_TO_CARPORT
						ENDIF 										
					ELSE
						IF flag_player_on_mission = 0
						OR player_on_valet_mission = 1
						OR force_valet_on_mission > 0
							IF NOT IS_CAR_DEAD valet_car[valet_i]
								GET_CAR_BLOCKING_CAR valet_car[valet_i] a_car
								IF NOT IS_CAR_DEAD a_car
									GET_DRIVER_OF_CAR a_car a_ped	
									IF a_ped = scplayer
									OR a_ped = -1
										IF NOT a_car = -1
											steal_this_car = a_car
//											IF IS_CHAR_DEAD a_thief
//												GET_CAR_COORDINATES valet_car[valet_i] x y z
//												GET_RANDOM_CHAR_IN_SPHERE x y z 20.0 TRUE FALSE FALSE a_thief
//												IF NOT IS_CHAR_DEAD a_thief
//													IF NOT IS_CHAR_IN_ANY_CAR a_thief
//														IF NOT IS_CHAR_DEAD a_thief
//														AND NOT IS_CAR_DEAD a_car
//															MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
//															MARK_CAR_AS_NO_LONGER_NEEDED a_car
//															TASK_STEAL_CAR a_thief a_car
//															a_car = 0
//														ENDIF
//													ENDIF
//												ENDIF
//											ENDIF		
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE CAR_STOLEN
				IF NOT IS_CHAR_DEAD valet[valet_i]
				AND NOT IS_CAR_DEAD valet_car[valet_i]
					IF NOT IS_CHAR_IN_CAR valet[valet_i] valet_car[valet_i]
						
						GET_DRIVER_OF_CAR valet_car[valet_i] a_char
						IF NOT a_char = -1
							GET_CAR_CHAR_IS_USING scplayer a_car
							IF a_car = valet_car[valet_i]					
								IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
//									valet_task[valet_i] = WAITING_FOR_CAR
//									valet_car[valet_i] = 0
								ELSE
									valet_task[valet_i] = WAITING_FOR_CAR
								ENDIF
							ELSE
								valet_task[valet_i] = WAITING_FOR_CAR
							ENDIF
						ENDIF
					ENDIF
				ENDIF
								




//					GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_ENTER_CAR_AS_DRIVER valet_task_status
//					IF valet_task_status = FINISHED_TASK
//						IF IS_CHAR_IN_ANY_CAR valet[valet_i]
//							valet_task[valet_i] = valet_last_task[valet_i]	
//						ELSE
//							TASK_ENTER_CAR_AS_DRIVER valet[valet_i] valet_car[valet_i] -2
//						ENDIF
//					ELSE
//						IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D valet[valet_i] valet_car[valet_i] 15.0 15.0 15.0 FALSE
//							valet_task[valet_i] = WAITING_FOR_CAR
//						ENDIF
//					ENDIF
//				ENDIF				
			BREAK

			CASE CHATTING
				IF NOT IS_CHAR_DEAD valet[valet_i]
					GET_SCRIPT_TASK_STATUS valet[valet_i] TASK_CHAT_WITH_CHAR valet_task_status
					IF valet_task_status = FINISHED_TASK						
						IF valet_i = 0
							IF NOT IS_CHAR_DEAD valet[1]
								IF NOT IS_CHAR_DEAD valet[0]
									GET_SCRIPT_TASK_STATUS valet[1] TASK_CHAT_WITH_CHAR valet_task_status	
									IF valet_task_status = FINISHED_TASK
										GET_SCRIPT_TASK_STATUS valet[0] TASK_CHAT_WITH_CHAR valet_task_status
										IF valet_task_status = FINISHED_TASK
											TASK_CHAT_WITH_CHAR valet[0] valet[1] TRUE TRUE
											TASK_CHAT_WITH_CHAR valet[1] valet[0] FALSE TRUE
											valet_last_task[0] = CHATTING
											valet_last_task[1] = CHATTING
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			BREAK

			CASE VALET_DEAD
			BREAK

//							   GET_RANDOM_CHAR_IN_SPHERE CentreX CentreY CentreZ Radius CivilianFlag GangFlag CriminalFlag NewCharID

		ENDSWITCH

		valet_i ++

	ENDWHILE


RETURN

car_control:

	
	
	IF freeze_creating_drop_off_cars = 0
	OR force_a_one_off_car > 0
		IF create_drop_off_car_now = 0		
			IF drop_off_car = 0
				IF NOT IS_AREA_OCCUPIED valet_drop_off_area_x1[val_area] valet_drop_off_area_y1[val_area] 0.0 valet_drop_off_area_x2[val_area] valet_drop_off_area_y2[val_area] 80.0 FALSE TRUE FALSE FALSE FALSE					
					force_a_one_off_car --
					create_drop_off_car_now = 1
					check_car_counter = 0
				ENDIF
				IF create_drop_off_car_now = 0
					IF valet_mission_car = 0
					AND valet_pickup_car = 0
					AND drop_off_car = 0
						force_a_one_off_car --
						create_drop_off_car_now = 1
						check_car_counter = 0
					ENDIF
				ENDIF	
			ENDIF
		ENDIF
	ENDIF


	IF NOT drop_off_car = 0
		IF IS_CAR_DEAD drop_off_car
			debug_this = 1
			DROP_OFF_CAR = 0
		ENDIF

		

		IF NOT IS_CAR_DEAD drop_off_car
			GET_DRIVER_OF_CAR drop_off_car a_ped
			IF NOT IS_CHAR_DEAD a_ped		
				IF NOT IS_CHAR_SITTING_IN_CAR a_ped drop_off_car
					MARK_CAR_AS_NO_LONGER_NEEDED drop_off_car
				ENDIF				
			ENDIF
			IF a_ped = -1
			OR a_ped = scplayer
				MARK_CAR_AS_NO_LONGER_NEEDED drop_off_car
				debug_this = 2
				drop_off_car = 0 			
			ENDIF
		ENDIF
	ENDIF

	IF NOT valet_pickup_car = 0
		IF IS_CAR_DEAD valet_pickup_car
		    valet_pickup_car = 0
		ENDIF

		IF NOT IS_CAR_DEAD valet_pickup_car
			GET_DRIVER_OF_CAR valet_pickup_car a_ped
			IF a_ped = scplayer
				IF NOT valet_pickup_car = sc3_missioncar
					MARK_CAR_AS_NO_LONGER_NEEDED valet_pickup_car 			
				ENDIF
				valet_pickup_car = 0
			ENDIF
		ENDIF
				
	ENDIF








	IF TIMERA > checks_time_B
			IF valet_task[0] = WAITING_FOR_CAR
			OR valet_task[1] = WAITING_FOR_CAR
			OR valet_task[2] = WAITING_FOR_CAR
			OR player_on_valet_mission = 1
			OR flag_player_on_mission = 1
						  // BECAUSE ONCE CAR HAS BEEN PICKED UP IT's NO LONGER RANDOM - is a mission car
				IF NOT valet_task[0] = PICKING_UP_CAR 
				AND NOT valet_task[1] = PICKING_UP_CAR
				AND NOT valet_task[2] = PICKING_UP_CAR
					GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE valet_drop_off_area_x1[val_area] valet_drop_off_area_y1[val_area] valet_drop_off_area_x2[val_area] valet_drop_off_area_y2[val_area] -1 a_pickup_car
					IF NOT IS_CAR_DEAD sc3_missioncar
						IF IS_CAR_IN_AREA_2D sc3_missioncar	valet_drop_off_area_x1[val_area] valet_drop_off_area_y1[val_area] valet_drop_off_area_x2[val_area] valet_drop_off_area_y2[val_area] FALSE
							a_pickup_car = sc3_missioncar
						ENDIF
					ENDIF
					IF a_pickup_car = -1
						IF NOT IS_CAR_DEAD drop_off_car
							IF IS_CAR_IN_AREA_2D drop_off_Car valet_drop_off_area_x1[val_area] valet_drop_off_area_y1[val_area] valet_drop_off_area_x2[val_area] valet_drop_off_area_y2[val_area] FALSE
								a_pickup_car = drop_off_Car
							ENDIF
						ENDIF
					ENDIF

					IF a_pickup_car = -1
						IF NOT IS_CAR_DEAD mission_drop_off_car
							IF IS_CAR_IN_AREA_2D mission_drop_off_car	valet_drop_off_area_x1[val_area] valet_drop_off_area_y1[val_area] valet_drop_off_area_x2[val_area] valet_drop_off_area_y2[val_area] FALSE
								a_pickup_car = mission_drop_off_car
							ENDIF
						ENDIF 
					ENDIF

					blah = 0
					IF NOT a_pickup_car = -1
						VAR_INT blahb
						blahb = a_pickup_car
						IF NOT IS_CAR_DEAD a_pickup_car
							GET_DRIVER_OF_CAR a_pickup_car a_ped					
							IF a_ped = -1
								GET_VEHICLE_CLASS a_pickup_car a_class
				
								IF NOT a_class	= NORMAL_CAR
								AND NOT a_class	= POOR_FAMILY_CAR
								AND NOT a_class	= RICH_FAMILY_CAR
								AND NOT a_class	= EXECUTIVE_CAR
								
		
									steal_this_car = a_pickup_car
									a_pickup_car = -1
								ENDIF
							ENDIF
						ENDIF						
					ENDIF

					IF NOT a_pickup_car = -1
						IF NOT IS_CAR_DEAD a_pickup_car
							IF IS_EMERGENCY_SERVICES_VEHICLE a_pickup_car
								steal_this_car = a_pickup_car
								a_pickup_car = -1
							ENDIF
						ENDIF
					ENDIF
								

					

					IF NOT a_pickup_car = -1
						IF NOT IS_CAR_DEAD a_pickup_Car
							GET_DRIVER_OF_CAR a_pickup_car a_ped					
							IF NOT a_ped = -1
								IF NOT IS_CHAR_DEAD a_ped
									IF NOT IS_CHAR_SITTING_IN_CAR a_ped	a_pickup_car					
										valet_pickup_car = a_pickup_car
										SET_CAR_STRAIGHT_LINE_DISTANCE valet_pickup_car 1
									ENDIF
									GET_SCRIPT_TASK_STATUS a_ped PERFORM_SEQUENCE_TASK valet_task_status
									IF valet_task_Status = PERFORMING_TASK
										GET_SEQUENCE_PROGRESS a_ped valet_task_status
										IF valet_task_status = 1
											valet_pickup_car = a_pickup_car
											SET_CAR_STRAIGHT_LINE_DISTANCE valet_pickup_car 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						IF a_ped = -1			
							valet_pickup_car = a_pickup_car
							SET_CAR_STRAIGHT_LINE_DISTANCE valet_pickup_car 1
						ENDIF
						GET_CAR_CHAR_IS_USING scplayer a_car
						IF a_car = valet_pickup_car
							valet_pickup_car = 0
						ENDIF

						IF valet_pickup_car = a_pickup_car
							IF NOT IS_CAR_DEAD a_pickup_car
								LVAR_INT valet_maxpassengers
								GET_MAXIMUM_NUMBER_OF_PASSENGERS a_pickup_car valet_MaxPassengers
								valet_i = 0
								WHILE valet_i < valet_MaxPassengers
  									IF NOT IS_CAR_PASSENGER_SEAT_FREE a_pickup_car valet_i
										GET_CHAR_IN_CAR_PASSENGER_SEAT a_pickup_car valet_i a_char
										IF NOT IS_CHAR_DEAD a_char
											IF IS_CHAR_SITTING_IN_CAR a_char a_pickup_car
												GET_SCRIPT_TASK_STATUS a_char TASK_WANDER_STANDARD valet_Task_status
												IF valet_task_status = FINISHED_TASK
													MARK_CHAR_AS_NO_LONGER_NEEDED a_char
													TASK_WANDER_STANDARD a_char
												ENDIF
											ENDIF
										ENDIF
									ENDIF
									valet_i ++
								ENDWHILE
							ENDIF

															
							IF valet_task[0] = WAITING_FOR_CAR
								valet_task[0] = PICKING_UP_CAR
								valet_car[0] = valet_pickup_car
							ELSE	
								IF valet_task[1] = WAITING_FOR_CAR
									valet_task[1] = PICKING_UP_CAR
									valet_car[1] = valet_pickup_car
								ELSE	
									IF valet_task[2] = WAITING_FOR_CAR
										valet_task[2] = PICKING_UP_CAR
										valet_car[2] = valet_pickup_car
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						a_pickup_car = 0
					ENDIF
				ENDIF

		ENDIF
	ENDIF




	// creating a drop_off car rules
	IF create_drop_off_car_now = 1

		IF TIMERA > checks_time_C
			debug_this = 3
			drop_off_car = 0
			GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE find_drop_off_car_x1[val_area] find_drop_off_car_y1[val_area] find_drop_off_car_x2[val_area] find_drop_off_car_y2[val_area] -1 a_car
			IF NOT a_car = -1
				IF NOT IS_CAR_DEAD a_car
					GET_CAR_HEADING a_car a_Heading
					a_float = a_heading - find_drop_off_car_h1[val_area]
					ABS a_float
					IF a_float < 45.0
						GET_VEHICLE_CLASS a_car a_class

						IF a_class	= NORMAL_CAR
						OR a_class	= POOR_FAMILY_CAR
						OR a_class	= RICH_FAMILY_CAR
						OR a_class	= EXECUTIVE_CAR

							GET_CAR_MODEL a_car a_class

							IF NOT a_class = copcarla						
							OR NOT a_class = TAXI
								GET_DRIVER_OF_CAR a_car a_driver
								IF NOT a_driver = -1
									IF NOT IS_CHAR_DEAD a_driver
										GET_SCRIPT_TASK_STATUS a_driver TASK_CAR_DRIVE_WANDER valet_task_status
										IF valet_task_status = FINISHED_TASK
											drop_off_car = a_car
											SET_CAR_CAN_GO_AGAINST_TRAFFIC drop_off_car FALSE
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF drop_off_car = 0

				GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE find_drop_off_car_x3[val_area] find_drop_off_car_y3[val_area] find_drop_off_car_x4[val_area] find_drop_off_car_y4[val_area] -1 a_car
				IF NOT a_car = -1
					IF NOT IS_CAR_DEAD a_car
						GET_CAR_HEADING a_car a_Heading
						a_float = a_heading - find_drop_off_car_h2[val_area]
						ABS a_float
						IF a_float < 45.0
							GET_VEHICLE_CLASS a_car a_class

							IF a_class	= NORMAL_CAR
							OR a_class	= POOR_FAMILY_CAR
							OR a_class	= RICH_FAMILY_CAR
							OR a_class	= EXECUTIVE_CAR

								GET_CAR_MODEL a_car a_class

								IF NOT a_class = copcarla						
								OR NOT a_class = TAXI
									GET_DRIVER_OF_CAR a_car a_driver
									IF NOT a_driver = -1
										IF NOT IS_CHAR_DEAD a_driver
											GET_SCRIPT_TASK_STATUS a_driver TASK_CAR_DRIVE_WANDER valet_task_status
											IF valet_task_status = FINISHED_TASK
												drop_off_car = a_car
												SET_CAR_CAN_GO_AGAINST_TRAFFIC drop_off_car FALSE
											ENDIF
										ENDIF
									ENDIF																
								ENDIF
							ENDIF							
						ENDIF
					ENDIF
				ENDIF				
			ENDIF



			IF player_on_valet_mission = 1
			OR force_valet_on_mission > 0
				IF drop_off_Car = 0
					check_car_counter ++
					IF check_car_counter = 6
						GENERATE_RANDOM_INT_IN_RANGE 0 3 valet_i
						IF valet_i = 0
							REQUEST_MODEL PREVION
							valet_model = PREVION
						ENDIF
						IF valet_i = 1
							REQUEST_MODEL FELTZER
							valet_model = FELTZER
						ENDIF
						IF valet_i = 2
							REQUEST_MODEL ELEGANT
							valet_model = ELEGANT
						ENDIF
					ENDIF
					IF check_car_counter >= 6
						IF NOT valet_model = 0
							IF HAS_MODEL_LOADED valet_model
								IF NOT IS_POINT_ON_SCREEN valet_spawn_x[val_area] valet_spawn_y[val_area] valet_spawn_z[val_area] 5.0
									CLEAR_AREA valet_spawn_x[val_area] valet_spawn_y[val_area] valet_spawn_z[val_area] 5.0 FALSE
									CREATE_CAR valet_model valet_spawn_x[val_area] valet_spawn_y[val_area] valet_spawn_z[val_area] drop_off_car
									SET_CAR_HEADING drop_off_car valet_spawn_h[val_area]
									CREATE_RANDOM_CHAR_AS_DRIVER drop_off_car a_driver
									SET_CAR_CAN_GO_AGAINST_TRAFFIC drop_off_car FALSE
//									MARK_CAR_AS_NO_LONGER_NEEDED drop_off_car
								ELSE
									IF NOT IS_POINT_ON_SCREEN valet_spawn_x2[val_area] valet_spawn_y2[val_area] valet_spawn_z2[val_area] 5.0
										CLEAR_AREA valet_spawn_x2[val_area] valet_spawn_y2[val_area] valet_spawn_z2[val_area] 5.0 FALSE
										CREATE_CAR valet_model valet_spawn_x2[val_area] valet_spawn_y2[val_area] valet_spawn_z2[val_area] drop_off_car
										SET_CAR_HEADING drop_off_car valet_spawn_h2[val_area]
										CREATE_RANDOM_CHAR_AS_DRIVER drop_off_car a_driver
										SET_CAR_CAN_GO_AGAINST_TRAFFIC drop_off_car FALSE
//										MARK_CAR_AS_NO_LONGER_NEEDED drop_off_car
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF NOT drop_off_car = 0
				create_drop_off_car_now = 0
				IF NOT IS_CHAR_DEAD a_driver
				AND NOT IS_CAR_DEAD drop_off_car
					IF sc3_missioncar_alive = 1
						CHANGE_CAR_COLOUR drop_off_car CARCOLOUR_CHERRYRED CARCOLOUR_CHERRYRED
					ENDIF
					MARK_CHAR_AS_NO_LONGER_NEEDED a_driver
					OPEN_SEQUENCE_TASK valet_seq
						IF flag_player_on_mission = 0
							TASK_CAR_DRIVE_TO_COORD -1 drop_off_car drop_off_point_x[val_area] drop_off_point_y[val_area] drop_off_point_z[val_area] 17.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS
						ENDIF
						IF flag_player_on_mission = 1
							TASK_CAR_DRIVE_TO_COORD -1 drop_off_car drop_off_point_x[val_area] drop_off_point_y[val_area] drop_off_point_z[val_area] 17.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS	
						ENDIF
						TASK_WANDER_STANDARD -1
					CLOSE_SEQUENCE_TASK valet_seq

					IF NOT IS_CHAR_DEAD	a_driver
						SET_CHAR_CANT_BE_DRAGGED_OUT a_driver TRUE
					ENDIF

					VAR_INT mission_drop_off_car
					IF player_on_valet_mission = 1
						mission_drop_off_car = drop_off_car
					ENDIF

					PERFORM_SEQUENCE_TASK a_driver valet_seq
					CLEAR_SEQUENCE_TASK valet_seq
				ENDIF
				IF NOT valet_model = 0
					MARK_MODEL_AS_NO_LONGER_NEEDED valet_model
					valet_model = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF	

RETURN

stop_thief:

IF player_on_valet_mission = 1
OR playing_scrash1 = 1
	IF NOT steal_this_car = -1
	AND NOT steal_this_car = sc3_missioncar
	AND NOT steal_this_car = valet_mission_car
		IF NOT last_Steal_car = steal_this_car
		  	IF NOT IS_CAR_DEAD steal_this_car
				GET_CAR_COORDINATES steal_this_car x y z

				IF x <  -1760.1785
				OR y < 972.2405  
				OR z < 16.1633 
				OR x > -1679.6055
				OR y > 1064.2927 
				OR z > 24.8629


					IF NOT IS_CHAR_IN_CAR scplayer steal_this_car
						IF NOT IS_CAR_ON_SCREEN steal_this_car
							DELETE_CAR steal_this_car
						ELSE
							GET_DRIVER_OF_CAR steal_This_car a_char
							IF a_char = -1
								IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer steal_this_car 8.0 8.0 8.0 FALSE
									CREATE_RANDOM_CHAR 0.0 0.0 0.0 a_char
									MARK_CHAR_AS_NO_LONGER_NEEDED a_char								
									MARK_CAR_AS_NO_LONGER_NEEDED steal_this_Car
									IF NOT IS_CHAR_DEAD a_char
									AND NOT IS_CAR_DEAD steal_this_car
										WARP_CHAR_INTO_CAR a_char steal_this_car
										TASK_CAR_DRIVE_WANDER a_char steal_this_car 20.0 DRIVINGMODE_AVOIDCARS
										steal_this_car = 0
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

//	  			GET_DRIVER_OF_CAR steal_this_car a_ped	
//				IF NOT a_ped = scplayer
//				AND NOT a_ped = -1
//					IF NOT IS_CHAR_DEAD a_ped
//						GET_SCRIPT_TASK_STATUS a_ped TASK_STEAL_CAR valet_task_Status
//						IF valeT_task_status = FINISHED_TASK
//							TASK_STEAL_CAR a_ped steal_this_car
//							MARK_CHAR_AS_NO_LONGER_NEEDED a_ped
//							MARK_CAR_AS_NO_LONGER_NEEDED steal_this_car
//							last_steal_car = steal_this_car
//							steal_this_car = -1
//						ENDIF
//					ENDIF
//				ENDIF
//
//				IF a_ped = -1
//					CREATE_RANDOM_CHAR 0.0 0.0 0.0 a_thief
//					WARP_CHAR_INTO_CAR a_thief steal_this_Car
//					TASK_STEAL_CAR a_thief steal_this_car
//					MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
//					MARK_CAR_AS_NO_LONGER_NEEDED steal_this_car
//					last_steal_car = steal_this_car
//					steal_this_car = -1
//				ENDIF

				VAR_INT last_steal_Car


//
//
//  			IF a_ped = scplayer
//	  		OR a_ped = -1
//				
//				GET_CAR_COORDINATES steal_this_car x y z
//				GET_CLOSEST_PICKUP_COORDS_TO_COORD x y z x y z
//
//				IF NOT IS_POINT_ON_SCREEN x y z 10.0
//					
////					CREATE_RANDOM_CHAR x y z a_thief
//////					WARP_CHAR_INTO_CAR a_thief steal_this_Car
////					IF NOT IS_CAR_DEAD steal_this_car
////					
////						TASK_STEAL_CAR a_thief steal_this_car
////						MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
////						MARK_CAR_AS_NO_LONGER_NEEDED steal_this_car
////						last_steal_car = steal_this_car
////						last_thief = a_thief
////						steal_this_car = -1
////					ENDIF
////
////					
//				ENDIF






//				GET_RANDOM_CHAR_IN_SPHERE x y z 35.0 TRUE FALSE FALSE a_thief
//				IF NOT IS_CHAR_DEAD a_thief
//					IF NOT IS_CHAR_IN_ANY_CAR a_thief
//						IF NOT a_thief = scplayer
//							MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
//						ENDIF
//
//						IF NOT steal_this_car = sc3_missioncar
//							MARK_CAR_AS_NO_LONGER_NEEDED steal_this_car
//						ENDIF
//
//												IF NOT steal_this_car = sc3_missioncar
//							
//							TASK_STEAL_CAR a_thief steal_this_car
//							last_steal_car = steal_this_car
//							last_thief = a_thief
//							steal_this_car = -1
//						ENDIF
//					ELSE
//						GET_SCRIPT_TASK_STATUS a_thief TASK_CAR_DRIVE_WANDER valet_task_Status
//						IF valet_task_status = FINISHED_TASK
//							STORE_CAR_CHAR_IS_IN_NO_SAVE a_thief temp_car
//
//    	                    MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
//
//        	                IF NOT IS_CAR_DEAD temp_car  // Check if it exists first!
//            	                IF NOT IS_CAR_ON_FIRE temp_car
//	            	                TASK_CAR_DRIVE_WANDER a_thief temp_car 20.0 DRIVINGMODE_AVOIDCARS
//                    	        ENDIF
//                        	ENDIF 
//						ENDIF

//					ENDIF
//				ENDIF
//	ELSE
//		steal_This_car = -1
//		IF NOT IS_CHAR_DEAD last_thief
//			GET_SCRIPT_TASK_STATUS last_thief TASK_STEAL_CAR valet_task_status
//			IF valet_task_status = FINISHED_TASK
//				last_thief = 0
//				last_steal_car = 0
//			ENDIF
//		ELSE
//			last_thief = 0
//			last_Steal_car = 0
//		ENDIF


RETURN

valet_mission_passed:

	
	IF valet_mission_completed = 0
//		WAIT 5000
//		IF TIMERA > valet_timer
			PLAYER_MADE_PROGRESS 1
			PLAY_MISSION_PASSED_TUNE 1
			valet_mission_completed = 1

			//show player Asset


			DO_FADE 500 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_PRINTS

			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON

				CREATE_PROTECTION_PICKUP -1757.6682 961.7819 24.3828 2000 2000 valet_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

				LOAD_SCENE -1757.6682 961.7819 24.3828
				SET_FIXED_CAMERA_POSITION -1761.8320 910.2675 30.7224 0.0 0.0 0.0 //cut scene of building
				POINT_CAMERA_AT_POINT -1761.6892 911.2571 30.7291 JUMP_CUT
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				PLAY_MISSION_PASSED_TUNE 2
				PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

				WAIT 5000
				SET_FIXED_CAMERA_POSITION -1748.3796 951.8210 27.5455 0.0 0.0 0.0 //cut scene showing pickup
				POINT_CAMERA_AT_POINT -1748.9379 952.5872 27.2272 JUMP_CUT
				PRINT_WITH_NUMBER_NOW ASS_LUV 2000 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

				WAIT 6000
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
			ENDIF

			IF IS_PLAYER_PLAYING player1

				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				DO_FADE 500 FADE_IN
//			ENDIF
		ENDIF

	ENDIF
	PRINT_BIG VAL_A33 5000 1
	valet_mission_flag = 8

RETURN

valet_cleanup:

	MARK_MODEL_AS_NO_LONGER_NEEDED wmyva	
	MARK_CHAR_AS_NO_LONGER_NEEDED valet[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED valet[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED valet[2]

	CLEAR_ONSCREEN_COUNTER valet_time_bonus
	CLEAR_ONSCREEN_TIMER valet_countdown
	CLEAR_ONSCREEN_COUNTER valet_cars_to_park

	REMOVE_DECISION_MAKER valet_empty_dec

	IF NOT IS_CHAR_DEAD valet[0]
		DELETE_CHAR valet[0]
	ENDIF

	IF NOT IS_CHAR_DEAD valet[1]
		DELETE_CHAR valet[1]
	ENDIF

	IF NOT IS_CHAR_DEAD valet[2]
		DELETE_CHAR valet[2]
	ENDIF

	IF player_on_valet_mission = 1
		SET_PED_DENSITY_MULTIPLIER 1.0
		SWITCH_EMERGENCY_SERVICES ON
		
	ENDIF

	valet_task[0] = NOT_CREATED
	valet_task[1] = NOT_CREATED
	valet_task[2] = NOT_CREATED
	valet_warnings_given = 0

	dont_clear_valets = 0

	
		IF NOT IS_CAR_DEAD valet_mission_car
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer valet_mission_car
					DELETE_CAR valet_mission_car
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD valet_car[0]
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer valet_car[0]
					DELETE_CAR valet_car[0]
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD valet_car[1]
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer valet_car[1]
					DELETE_CAR valet_car[1]
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD valet_car[2]
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer valet_car[2]
					DELETE_CAR valet_car[2]
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD valet_pickup_car
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer valet_pickup_car
					DELETE_CAR valet_pickup_car
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD drop_off_car
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_IN_CAR scplayer drop_off_car
					DELETE_CAR drop_off_car
				ENDIF
			ENDIF
		ENDIF

		valet_mission_dead[0] = 0
		valet_mission_dead[1] = 0
		valet_mission_dead[2] = 0

		valet_mission_car = 0
		valet_car[0] = 0
		valet_car[1] = 0
		valet_car[2] = 0
		valet_pickup_car = 0
		debug_this = 4
		drop_off_car = 0
		valet_mission_flag = 0
		pause_valet_script = 0
		force_valet_cleanup = 0


	valet_scene_running = 0

	REMOVE_BLIP parking_space_blip
	REMOVE_BLIP pickup_car_blip

	TERMINATE_THIS_SCRIPT

RETURN

MISSION_END
}