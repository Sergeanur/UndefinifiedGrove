MISSION_START

// Goes in main.sc
// LAUNCH_MISSION collect.sc
//
////CREATE_COLLECTABLE1 479.6 -1718.5 15.6
//VAR_INT collectable1_van
//VAR_FLOAT collectable1_van_x collectable1_van_y collectable1_van_z collectable1_van_h 
//
//// Zone Locate needed for each van/radio station
//collectable1_van_x = 2520.56
//collectable1_van_y = -1461.98
//collectable1_van_z = 23.79
//collectable1_van_h = 270.37
//
//CREATE_CAR_GENERATOR collectable1_van_x collectable1_van_y collectable1_van_z collectable1_van_h NEWSVAN -1 -1 TRUE 0 0 0 10000 gen_car41 // Package Van 
//SWITCH_CAR_GENERATOR gen_car41 101

//// LA Packages
//CREATE_COLLECTABLE1 1803.46 -1579.89 13.58 
//CREATE_COLLECTABLE1 1838.63 -1485.81 13.69 
//CREATE_COLLECTABLE1	2410.2507 -1558.6847 30.4922
//CREATE_COLLECTABLE1	2629.9314 -1485.3994 15.3902
//CREATE_COLLECTABLE1	2633.1877 -1479.8478 15.3543
//CREATE_COLLECTABLE1	2516.1604 -1945.1355 15.7336
//CREATE_COLLECTABLE1	2517.0613 -2049.5420 5.5971 
//CREATE_COLLECTABLE1	2649.8315 -2116.7195 15.9609
//CREATE_COLLECTABLE1	2689.0583 -2516.2615 16.3672
//CREATE_COLLECTABLE1	1168.6608 -891.5475 42.0859


SCRIPT_NAME	collect

 

VAR_FLOAT collectable1_radius collectable1_distance
VAR_INT collectable1_collected collectable1_van_grabbed collectable1_van_blip 
VAR_INT collect_car_model collect_pulse
VAR_INT c1_radar_timer_start c1_radar_timer_end c1_radar_timer_diff c1_radar_timer_distance_diff c1_radar_flag

collectable1_radius = 500.0
c1_radar_flag = 0
collect_pulse = 0
collectable1_van_grabbed = 0

//VIEW_INTEGER_VARIABLE collectable1_van_grabbed collectable1_van_grabbed
//VIEW_INTEGER_VARIABLE c1_radar_timer_distance_diff c1_radar_timer_distance_diff
//VIEW_FLOAT_VARIABLE collectable1_distance collectable1_distance
{
START_NEW_SCRIPT collect_script
MISSION_END
}


{
collect_script:

SCRIPT_NAME	collB

collect_loop:
WAIT 0
	IF collectable1_van_grabbed = 0
		IF NOT IS_CHAR_DEAD scplayer
			IF LOCATE_CHAR_IN_CAR_3D scplayer collectable1_van_x collectable1_van_y collectable1_van_z 3.0 3.0 3.0 FALSE
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer collectable1_van
					GET_CAR_MODEL collectable1_van collect_car_model
					IF collect_car_model = NEWSVAN
					 	collectable1_van_grabbed = 1
						SWITCH_CAR_GENERATOR gen_car41 0
					ELSE
						SWITCH_CAR_GENERATOR gen_car41 101
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF collectable1_van_grabbed = 1
		IF NOT IS_CAR_DEAD collectable1_van
		AND NOT IS_CHAR_DEAD scplayer
			IF IS_CHAR_IN_CAR scplayer collectable1_van
				IF collect_pulse = 0
					GET_COLLECTABLE1S_COLLECTED collectable1_collected
					GET_CHAR_COORDINATES scplayer collectable1_van_x collectable1_van_y collectable1_van_z
					GET_COORDS_OF_CLOSEST_COLLECTABLE1 scplayer collectable1_radius collectable1_x collectable1_y collectable1_z
					GET_DISTANCE_BETWEEN_COORDS_2D collectable1_van_x collectable1_van_y collectable1_x collectable1_y collectable1_distance
					collect_pulse = 1
				ENDIF
				IF collect_pulse = 1 
					IF collectable1_distance < collectable1_radius
						collectable1_distance *= 10.0
						c1_radar_timer_distance_diff =# collectable1_distance   
						IF c1_radar_flag = 0
							GET_GAME_TIMER c1_radar_timer_start
							c1_radar_flag = 1
						ENDIF
						IF c1_radar_flag = 1
							GET_GAME_TIMER c1_radar_timer_end
							c1_radar_timer_diff = c1_radar_timer_end - c1_radar_timer_start
							IF c1_radar_timer_diff > c1_radar_timer_distance_diff
								ADD_ONE_OFF_SOUND collectable1_van_x collectable1_van_y collectable1_van_z SOUND_PART_MISSION_COMPLETE
								collect_pulse = 0
								c1_radar_flag = 0
							ENDIF	
						ENDIF
					ELSE
						collect_pulse = 0
					ENDIF
				ENDIF
			ELSE
				collectable1_van_grabbed = 0
				//TERMINATE_THIS_SCRIPT
			ENDIF
		ENDIF
	ENDIF

GOTO collect_loop

}


