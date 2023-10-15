MISSION_START
// ------------------------------------------------------------------------------------------------
// Badlands CRASH Mission 1: Witness Protection

{

SCRIPT_NAME bcrash1

// Begin...
GOSUB mission_start_bcrash1

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_bcrash1
	ENDIF

GOSUB mission_cleanup_bcrash1

MISSION_END

// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	VAR_INT bc1_stage bc1_in_water bc1_cut
	LVAR_INT bc1_wander bc1_incar bc1_status bc1_flee bc1_pressed bc1_perform
	LVAR_INT bc1_car_attack[5] bc1_onfoot_attack[5] bc1_cur_marshall bc1_onscreen
	LVAR_INT bc1_target_photo  bc1_create bc1_blipped bc1_help
	LVAR_INT bc1_drive_car bc1_speed_boost bc1_photo_loc bc1_camera_ammo_flag
	VAR_INT bc1_route bc1_route_nx bc1_final_dest

// ---- Check
	LVAR_FLOAT bc1_distance bc1_distance_check
	LVAR_INT bc1_closest bc1_route_check


// ---- Blips
	LVAR_INT bc1_hut_blip bc1_check_blip bc1_drop_blip

// ---- Coords
	LVAR_FLOAT bc1_player_x bc1_player_y bc1_player_z
	LVAR_FLOAT bc1_hut_x bc1_hut_y bc1_hut_z
	LVAR_FLOAT bc1_cur_marsh_x bc1_cur_marsh_y bc1_cur_marsh_z bc1_z
	LVAR_FLOAT bc1_point_x[6] bc1_point_y[6] bc1_point_z[6]
	LVAR_FLOAT bc1_locate_x bc1_locate_y bc1_locate_z 	
	LVAR_FLOAT bc1_check_x[3] bc1_check_y[3] bc1_check_z[3]
	LVAR_FLOAT bc1_route_x[40] bc1_route_y[40] bc1_route_z[40]

	LVAR_FLOAT bc1_min_x bc1_min_y bc1_min_z bc1_max_x bc1_max_y bc1_max_z
	LVAR_FLOAT bc1_offset_x bc1_offset_y bc1_offset_z

// ---- Cameras
	LVAR_FLOAT bc1_cam_x[10] bc1_cam_y[10] bc1_cam_z[10]

// ---- Timers
	LVAR_INT bc1_timer_start bc1_timer_end bc1_timer_diff

// ---- Entities
	LVAR_INT bc1_health 
	LVAR_INT bc1_camera_pickup bc1_camera_pickup_blip bc1_player_car
	LVAR_INT bc1_barrier[3] bc1_camera_ammo

// ---- Sequence Vars
	LVAR_INT bc1_patrol[5] bc1_run_to_car bc1_run_away

// ------------------------------------------------------------------------------------------------
// Create Entities 
	bc1_load_all:
		REQUEST_MODEL WMYBU
		REQUEST_MODEL FBI
		REQUEST_MODEL WASHING
		REQUEST_MODEL SANCHEZ
		REQUEST_MODEL micro_uzi
		REQUEST_MODEL CAMERA

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED WMYBU
		OR NOT HAS_MODEL_LOADED FBI
		OR NOT HAS_MODEL_LOADED micro_uzi
		OR NOT HAS_MODEL_LOADED WASHING
		OR NOT HAS_MODEL_LOADED SANCHEZ
		OR NOT HAS_MODEL_LOADED CAMERA
		WAIT 0
		ENDWHILE
	RETURN
	CREATE_PICKUP_WITH_AMMO CAMERA PICKUP_ONCE 24 -2044.10 -2523.86 31.11 bc1_camera_pickup
	ADD_BLIP_FOR_PICKUP bc1_camera_pickup bc1_camera_pickup_blip
	
// ---- Target 
	bc1_target_create:
		LVAR_INT bc1_target bc1_target_blip bc1_dec
		LVAR_FLOAT bc1_target_x bc1_target_y bc1_target_z 
		bc1_target_x = -2814.84
		bc1_target_y = -1522.00
		bc1_target_z = 139.33 
		CREATE_CHAR PEDTYPE_CIVMALE WMYBU bc1_target_x bc1_target_y bc1_target_z bc1_target
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE bc1_target FALSE
		//SET_CHAR_RELATIONSHIP bc1_target ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY bc1_dec
		SET_CHAR_DECISION_MAKER bc1_target bc1_dec
		//SET_CHAR_SUFFERS_CRITICAL_HITS bc1_target FALSE
		//SET_CHAR_PROOFS bc1_target FALSE TRUE TRUE FALSE FALSE
	RETURN
	bc1_target_delete:
		
		MARK_MODEL_AS_NO_LONGER_NEEDED WMYBU
	RETURN

// ----	US Marshalls
	bc1_marshall_create:
		LVAR_INT bc1_marshall[5] bc1_marshall_blip[5] bc1_dec2
		LVAR_FLOAT bc1_marshall_x[5] bc1_marshall_y[5] bc1_marshall_z[5] bc1_marshall_h[5] bc1_car_speed
		
		bc1_marshall_x[1] = -2800.11 
		bc1_marshall_y[1] = -1521.49 
		bc1_marshall_z[1] = 137.37 
		bc1_marshall_h[1] = 305.1252 

		bc1_marshall_x[2] = -2799.34 
		bc1_marshall_y[2] = -1524.66 
		bc1_marshall_z[2] = 137.38 
		bc1_marshall_h[2] = 356.2755 

		bc1_marshall_x[3] = -2789.0469 
		bc1_marshall_y[3] = -1533.3169 
		bc1_marshall_z[3] = 138.4778 
		bc1_marshall_h[3] = 317.5168 

		bc1_marshall_x[4] = -2801.70 
		bc1_marshall_y[4] = -1515.16 
		bc1_marshall_z[4] = 137.33 
		bc1_marshall_h[4] = 270.3571 

		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY bc1_dec2
		
		CREATE_CHAR PEDTYPE_MISSION1 FBI bc1_point_x[4] bc1_point_y[4] bc1_point_z[4] bc1_marshall[1]
		SET_CHAR_HEADING bc1_marshall[1] bc1_marshall_h[1]
		GIVE_WEAPON_TO_CHAR bc1_marshall[1] WEAPONTYPE_MICRO_UZI 30000
		SET_CHAR_RELATIONSHIP bc1_marshall[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_KEEP_TASK bc1_marshall[1] TRUE
		SET_CHAR_DECISION_MAKER bc1_marshall[1] bc1_dec2

		CREATE_CHAR PEDTYPE_MISSION1 FBI bc1_point_x[5] bc1_point_y[5] bc1_point_z[5] bc1_marshall[2]
		SET_CHAR_HEADING bc1_marshall[2] bc1_marshall_h[2]
		GIVE_WEAPON_TO_CHAR bc1_marshall[2] WEAPONTYPE_MICRO_UZI 30000
		SET_CHAR_RELATIONSHIP bc1_marshall[2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_KEEP_TASK bc1_marshall[2] TRUE	  
		SET_CHAR_DECISION_MAKER bc1_marshall[2] bc1_dec2

		CREATE_CHAR PEDTYPE_MISSION1 FBI bc1_point_x[3] bc1_point_y[3] bc1_point_z[3] bc1_marshall[3]
		SET_CHAR_HEADING bc1_marshall[3] bc1_marshall_h[3]
		GIVE_WEAPON_TO_CHAR bc1_marshall[3] WEAPONTYPE_MICRO_UZI 30000
		SET_CHAR_RELATIONSHIP bc1_marshall[3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_KEEP_TASK bc1_marshall[3] TRUE
		SET_CHAR_DECISION_MAKER bc1_marshall[3] bc1_dec2

		CREATE_CHAR PEDTYPE_MISSION1 FBI bc1_point_x[2] bc1_point_y[2] bc1_point_z[2] bc1_marshall[4]
		SET_CHAR_HEADING bc1_marshall[4] bc1_marshall_h[4]
		GIVE_WEAPON_TO_CHAR bc1_marshall[4] WEAPONTYPE_MICRO_UZI 30000
		SET_CHAR_RELATIONSHIP bc1_marshall[4] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_KEEP_TASK bc1_marshall[4] TRUE
		SET_CHAR_DECISION_MAKER bc1_marshall[4] bc1_dec2

		SET_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
		
	RETURN
	bc1_marshall_delete:
		MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
		MARK_MODEL_AS_NO_LONGER_NEEDED FBI
	RETURN

// ---- US Marshalls Car
	bc1_marshall_car_create:
		LVAR_INT bc1_marshall_car bc1_marshall_car_blip
		LVAR_FLOAT bc1_marshall_car_x bc1_marshall_car_y bc1_marshall_car_z bc1_marshall_car_h
   				bc1_marshall_car_x = -2815.16
		bc1_marshall_car_y = -1512.03
		bc1_marshall_car_z = 138.08 
		bc1_marshall_car_h = 272.57 
		CUSTOM_PLATE_FOR_NEXT_CAR WASHING &_ASSMAN_
		CREATE_CAR WASHING bc1_marshall_car_x bc1_marshall_car_y bc1_marshall_car_z bc1_marshall_car
		SET_LOAD_COLLISION_FOR_CAR_FLAG bc1_marshall_car FALSE  
		SET_CAR_HEADING bc1_marshall_car bc1_marshall_car_h
		//SET_CAR_PROOFS bc1_marshall_car TRUE TRUE TRUE TRUE TRUE
		LOCK_CAR_DOORS bc1_marshall_car CARLOCK_LOCKOUT_PLAYER_ONLY
		//FREEZE_CAR_POSITION bc1_marshall_car TRUE
		SET_CAN_BURST_CAR_TYRES bc1_marshall_car FALSE
   	RETURN
	bc1_marshall_car_delete:
		MARK_MODEL_AS_NO_LONGER_NEEDED WASHING
	RETURN

// ---- Dirtbike (Not BMX)
   	bc1_bmx_create:
//		REQUEST_MODEL SANCHEZ
//		WHILE NOT HAS_MODEL_LOADED SANCHEZ
//		WAIT 0
//		ENDWHILE
//		LVAR_INT bc1_bmx bc1_bmx_blip
//		LVAR_FLOAT bc1_bmx_x bc1_bmx_y bc1_bmx_z bc1_bmx_h
//		bc1_bmx_x =	-2047.8842
//		bc1_bmx_y =	-2516.6404
//		bc1_bmx_z =	29.7755 
//		bc1_bmx_h =	316.2936
//		CREATE_CAR SANCHEZ bc1_bmx_x bc1_bmx_y bc1_bmx_z bc1_bmx 
//		SET_LOAD_COLLISION_FOR_CAR_FLAG bc1_bmx FALSE 
//		SET_CAR_HEADING bc1_bmx bc1_bmx_h
//		SET_CAR_PROOFS bc1_bmx TRUE TRUE TRUE TRUE TRUE
   	RETURN
	bc1_bmx_new_coords:
		REQUEST_MODEL SANCHEZ
		WHILE NOT HAS_MODEL_LOADED SANCHEZ
		WAIT 0
		ENDWHILE
		LVAR_INT bc1_bmx bc1_bmx_blip
		LVAR_FLOAT bc1_bmx_x bc1_bmx_y bc1_bmx_z bc1_bmx_h
		bc1_bmx_x = -2803.1562
		bc1_bmx_y = -1519.4596
		bc1_bmx_z = 138.2818 
		bc1_bmx_h = 189.4269 
		CREATE_CAR SANCHEZ bc1_bmx_x bc1_bmx_y bc1_bmx_z bc1_bmx 
		SET_LOAD_COLLISION_FOR_CAR_FLAG bc1_bmx FALSE 
		SET_CAR_HEADING bc1_bmx bc1_bmx_h
		SET_CAR_PROOFS bc1_bmx TRUE TRUE TRUE TRUE TRUE
	RETURN
	bc1_bmx_delete:
		//DELETE_CAR bc1_bmx
		MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	RETURN

// ------------------------------------------------------------------------------------------------
// Start Mission

mission_start_bcrash1:
	REGISTER_MISSION_GIVEN

	flag_player_on_mission = 1

	WAIT 0

	LOAD_MISSION_TEXT BCRASH1

// ------------------------------------------------------------------------------------------------
// Initialise Variables

	//VIEW_INTEGER_VARIABLE bc1_route bc1_route
// ---- Coords
	bc1_route_x[0] = -2815.3821 
	bc1_route_y[0] = -1511.2039 
	bc1_route_z[0] = 139.1089 

	bc1_route_x[1] = -2766.9119 
	bc1_route_y[1] = -1599.0131 
	bc1_route_z[1] = 141.1248 

	bc1_route_x[2] = -2778.6113 
	bc1_route_y[2] = -1698.4384 
	bc1_route_z[2] = 141.3879 

	bc1_route_x[3] = -2770.7793 
	bc1_route_y[3] = -1798.4749 
	bc1_route_z[3] = 141.5863 

	bc1_route_x[4] = -2705.2419 
	bc1_route_y[4] = -1874.1293 
	bc1_route_z[4] = 137.9205 

	bc1_route_x[5] = -2651.3994 
	bc1_route_y[5] = -1958.4800 
	bc1_route_z[5] = 127.1876 

	bc1_route_x[6] = -2624.5076 
	bc1_route_y[6] = -2055.0771 
	bc1_route_z[6] = 129.1295 

	bc1_route_x[7] = -2525.1726 
	bc1_route_y[7] = -2071.4380 
	bc1_route_z[7] = 126.7572 

	bc1_route_x[8] = -2426.9585 
	bc1_route_y[8] = -2090.1919 
	bc1_route_z[8] = 122.5222 

	bc1_route_x[9] =   -2393.0
	bc1_route_y[9] =   -2096.0
	bc1_route_z[9] =  118.0

	bc1_route_x[10] = -2326.8984 
	bc1_route_y[10] = -2098.7317 
	bc1_route_z[10] = 114.4895 	
	
	bc1_route_x[11] = -2257.1584 
	bc1_route_y[11] = -2073.1099 
	bc1_route_z[11] = 119.2503
	
	bc1_route_x[12] = -2215.6384 
	bc1_route_y[12] = -2044.9622 
	bc1_route_z[12] = 118.7853

	bc1_route_x[13] = -2143.4915 
	bc1_route_y[13] = -1955.6223 
	bc1_route_z[13] = 117.0468
	
	bc1_route_x[14] = -2123.1479 
	bc1_route_y[14] = -1897.0499 
	bc1_route_z[14] = 113.4490 

	bc1_route_x[15] = -2137.8574 
	bc1_route_y[15] = -1994.8489 
	bc1_route_z[15] = 96.0566 
	
	bc1_route_x[16] = -2165.0095 
	bc1_route_y[16] = -2044.6545 
	bc1_route_z[16] = 90.2613

	bc1_route_x[17] = -2266.4578 
	bc1_route_y[17] = -2142.7351 
	bc1_route_z[17] = 55.9682 

	bc1_route_x[18] = -2345.6956 
	bc1_route_y[18] = -2199.5110 
	bc1_route_z[18] = 33.1272 

	bc1_route_x[19] = -2245.3535 
	bc1_route_y[19] = -2201.1106 
	bc1_route_z[19] = 35.0159 

	bc1_route_x[20] = -2164.6462 
	bc1_route_y[20] = -2143.8171 
	bc1_route_z[20] = 51.5793 

	bc1_route_x[21] = -2094.4729 
	bc1_route_y[21] = -2072.8215 
	bc1_route_z[21] = 63.6193 

	bc1_route_x[22] = -2063.8655 
	bc1_route_y[22] = -1977.4004 
	bc1_route_z[22] = 57.6244 

	bc1_route_x[23] = -2014.4531 
	bc1_route_y[23] = -1890.3556 
	bc1_route_z[23] = 45.5302 

	bc1_route_x[24] = -1949.7415 
	bc1_route_y[24] = -1814.2330 
	bc1_route_z[24] = 33.7562 

	bc1_route_x[25] = -1872.9307 
	bc1_route_y[25] = -1750.0071 
	bc1_route_z[25] = 29.0840 

	bc1_route_x[26] = -1783.8834 
	bc1_route_y[26] = -1701.4440 
	bc1_route_z[26] = 29.5583 

	bc1_route_x[27] = -1697.5596 
	bc1_route_y[27] = -1651.1669 
	bc1_route_z[27] = 36.0764 

	bc1_route_x[28] = -1604.9380 
	bc1_route_y[28] = -1613.3876 
	bc1_route_z[28] = 36.1148 

	bc1_route_x[29] = -1587.8641 
	bc1_route_y[29] = -1514.8492 
	bc1_route_z[29] = 37.5464 

	bc1_route_x[30] = -1685.7241 
	bc1_route_y[30] = -1494.0428 
	bc1_route_z[30] = 34.7867 

	bc1_route_x[31] = -1777.0442 
	bc1_route_y[31] = -1452.4105 
	bc1_route_z[31] = 34.6646 

	bc1_route_x[32] = -1862.8461 
	bc1_route_y[32] = -1400.2253 
	bc1_route_z[32] = 38.3508 

	bc1_route_x[33] = -1947.1061 
	bc1_route_y[33] = -1346.2959 
	bc1_route_z[33] = 40.5623 

	bc1_route_x[34] = -2005.1072 
	bc1_route_y[34] = -1264.5165 
	bc1_route_z[34] = 36.3458 

	bc1_route_x[35] = -2055.8374 
	bc1_route_y[35] = -1177.7612 
	bc1_route_z[35] = 32.1425 

	bc1_route_x[36] = -2106.0793 
	bc1_route_y[36] = -1091.0768
	bc1_route_z[36] = 29.9780 

	bc1_route_x[37] = -2168.3723 
	bc1_route_y[37] = -1012.4182
	bc1_route_z[37] = 33.7818 

	bc1_route_x[38] = -2210.5234
	bc1_route_y[38] = -974.8890 
	bc1_route_z[38] = 38.5044



// ---- Patrol Paths
   	bc1_point_x[0] = -2797.75 
	bc1_point_y[0] = -1531.63 
	bc1_point_z[0] = 138.50

	bc1_point_x[1] = -2803.09 
	bc1_point_y[1] = -1503.01 
	bc1_point_z[1] = 138.83

	bc1_point_x[2] = -2804.69 
	bc1_point_y[2] = -1506.70 
	bc1_point_z[2] = 137.83

	bc1_point_x[3] = -2818.89 
	bc1_point_y[3] = -1505.72 
	bc1_point_z[3] = 139.34

	bc1_point_x[4] = -2826.68 
	bc1_point_y[4] = -1522.23 
	bc1_point_z[4] = 138.78

	bc1_point_x[5] = -2822.90 
	bc1_point_y[5] = -1536.94 
	bc1_point_z[5] = 138.86

	bc1_hut_x =	-2814.84 
	bc1_hut_y =	-1522.00
	bc1_hut_z =	139.33 

	bc1_locate_x = -2777.05	
	bc1_locate_y = -1568.29	
	bc1_locate_z = 140.04	

	bc1_check_x[0] = -2346.70
	bc1_check_y[0] = -2240.18
	bc1_check_z[0] = 17.29
// --- Mountain Checkpoints
	bc1_check_x[1] = -2103.0
	bc1_check_y[1] = -1885.0
	bc1_check_z[1] = 109.0

	bc1_check_x[2] = -2602.0
	bc1_check_y[2] = -2084.0
	bc1_check_z[2] = 131.0

// ---- Cameras
	bc1_cam_x[0] = -2767.98
	bc1_cam_y[0] = -1597.90
	bc1_cam_z[0] = 141.46

	bc1_cam_x[1] = -2787.39
	bc1_cam_y[1] = -1568.11
	bc1_cam_z[1] = 147.27

	bc1_cam_x[2] = -2796.35
	bc1_cam_y[2] = -1537.58
	bc1_cam_z[2] = 143.82

	bc1_cam_x[3] = -2800.19
	bc1_cam_y[3] = -1505.59
	bc1_cam_z[3] = 143.89

	bc1_cam_x[4] = -2801.43
	bc1_cam_y[4] = -1541.87
	bc1_cam_z[4] = 140.29

// ---- Entity Subroutines
	bc1_car_speed = 30.0

// ---- Flags
	bc1_stage = -1
	bc1_wander = 0
	bc1_onscreen = 1
	bc1_patrol[1] = 1
	bc1_patrol[2] = 3
   	bc1_status = 0
	bc1_cut = 0
	bc1_create = 0
	bc1_blipped = 1
	bc1_drive_car = 0
	bc1_flee = 0
	bc1_speed_boost = 0
	bc1_camera_ammo_flag = 1   
	bc1_in_water = 0
	bc1_help = 0

	bc1_route = 0
	bc1_route_nx = 1
	bc1_final_dest = 38

	bc1_onfoot_attack[0] = 0
	bc1_onfoot_attack[1] = 0
	bc1_onfoot_attack[2] = 0
	bc1_onfoot_attack[3] = 0
	bc1_onfoot_attack[4] = 0
	bc1_car_attack[0] = 0
	bc1_car_attack[1] = 0
	bc1_car_attack[2] = 0
	bc1_car_attack[3] = 0
	bc1_car_attack[4] = 0

// ---- Sequences
OPEN_SEQUENCE_TASK bc1_patrol[1]
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[4] bc1_point_y[4] bc1_point_z[4] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[5] bc1_point_y[5] bc1_point_z[5] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[0] bc1_point_y[0] bc1_point_z[0] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[1] bc1_point_y[1] bc1_point_z[1] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[2] bc1_point_y[2] bc1_point_z[2] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[3] bc1_point_y[3] bc1_point_z[3] PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT bc1_patrol[1] 1
CLOSE_SEQUENCE_TASK bc1_patrol[1]

OPEN_SEQUENCE_TASK bc1_patrol[2]
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[5] bc1_point_y[5] bc1_point_z[5] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[4] bc1_point_y[4] bc1_point_z[4] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[3] bc1_point_y[3] bc1_point_z[3] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[2] bc1_point_y[2] bc1_point_z[2] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[1] bc1_point_y[1] bc1_point_z[1] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[0] bc1_point_y[0] bc1_point_z[0] PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT bc1_patrol[2] 1
CLOSE_SEQUENCE_TASK bc1_patrol[2]

OPEN_SEQUENCE_TASK bc1_patrol[3]
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[3] bc1_point_y[3] bc1_point_z[3] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[4] bc1_point_y[4] bc1_point_z[4] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[5] bc1_point_y[5] bc1_point_z[5] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[0] bc1_point_y[0] bc1_point_z[0] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[1] bc1_point_y[1] bc1_point_z[1] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[2] bc1_point_y[2] bc1_point_z[2] PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT bc1_patrol[3] 1
CLOSE_SEQUENCE_TASK bc1_patrol[3]

OPEN_SEQUENCE_TASK bc1_patrol[4]
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[2] bc1_point_y[2] bc1_point_z[2] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[1] bc1_point_y[1] bc1_point_z[1] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[0] bc1_point_y[0] bc1_point_z[0] PEDMOVE_WALK -1
   	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[5] bc1_point_y[5] bc1_point_z[5] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[4] bc1_point_y[4] bc1_point_z[4] PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 bc1_point_x[3] bc1_point_y[3] bc1_point_z[3] PEDMOVE_WALK -1
	SET_SEQUENCE_TO_REPEAT bc1_patrol[4] 1
CLOSE_SEQUENCE_TASK bc1_patrol[4]

CREATE_OBJECT_NO_OFFSET roadworkbarrier1 -2450.083 -2079.953 125.711 bc1_barrier[0] // Barrier
SET_OBJECT_ROTATION bc1_barrier[0] 9.209 7.675 -43.371
CREATE_OBJECT_NO_OFFSET roadworkbarrier1 -2449.15 -2078.21 125.917 bc1_barrier[1] // Barrier
SET_OBJECT_ROTATION bc1_barrier[1] 4.992 7.247 -15.069
CREATE_OBJECT_NO_OFFSET roadworkbarrier1 -2447.868 -2076.578 125.975 bc1_barrier[2] // Barrier
SET_OBJECT_ROTATION bc1_barrier[2] -1.869 8.724 -44.611

// ------------------------------------------------------------------------------------------------
// ---- Misc Mission Crap
	//GOSUB bc1_bmx_create
//	DO_FADE 300 FADE_OUT
//	WHILE GET_FADING_STATUS
//	WAIT 0
//	ENDWHILE
	GOSUB bc1_load_all

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 30000
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	SET_CHAR_HEADING scplayer 54.0
	SET_FIXED_CAMERA_POSITION -2041.0087 -2527.2070 33.9116 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -2041.7853 -2526.6025 34.0884 JUMP_CUT
	  
	DO_FADE 300 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE

	WAIT 3000

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	PRINT_NOW ( BCR1_03 ) 5000 1 // Kill the witness and take his photograph...
	ADD_BLIP_FOR_COORD bc1_check_x[0] bc1_check_y[0] bc1_check_z[0] bc1_check_blip
	
//	SET_CAMERA_BEHIND_PLAYER
//	VIEW_INTEGER_VARIABLE bc1_cut bc1_cut
//	VIEW_INTEGER_VARIABLE bc1_stage bc1_stage

// ------------------------------------------------------------------------------------------------
// Main mission loop 
mission_loop_bcrash1:

WAIT 0


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_bcrash1
ENDIF
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
	flag_bcr1_skip = 1
ENDIF
IF main_visible_area = 0
	IF bc1_help = 3
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			PRINT_HELP ( BCR1_23 ) // You can view your Gallery photos in your GTA San Andreas User Files directory.
			bc1_help = 4
		ENDIF
	ENDIF
	IF bc1_help = 2
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			PRINT_HELP ( FEG_HOW )  
			bc1_help = 3
		ENDIF
	ENDIF
	IF bc1_help = 1
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			PRINT_HELP ( BCR1_21 )  
			bc1_help = 2
		ENDIF
	ENDIF
	IF bc1_help = 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
			PRINT_HELP BCR1_22	
			bc1_help = 1
		ENDIF
	ENDIF
ENDIF

// In car check
IF bc1_stage < 4
	IF bc1_incar = 0
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer bc1_player_car
			bc1_incar = 1
		ENDIF
	ENDIF
	IF bc1_incar = 1
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			bc1_incar = 0
		ENDIF
	ENDIF
ENDIF

IF bc1_stage = -1
	IF NOT IS_CHAR_DEAD scplayer
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer bc1_check_x[0] bc1_check_y[0] bc1_check_z[0] 4.0 4.0 4.0 TRUE
			//GOSUB bc1_bmx_delete
			REMOVE_BLIP bc1_check_blip
			bc1_locate_x = -2774.9807
			bc1_locate_y = -1591.2599
			bc1_locate_z = 140.3880 
			ADD_BLIP_FOR_COORD bc1_locate_x bc1_locate_y bc1_locate_z bc1_hut_blip
//			SET_COORD_BLIP_APPEARANCE bc1_hut_blip COORD_BLIP_APPEARANCE_ENEMY
			PRINT_NOW ( BCR1_18 ) 5000 1 // ~g~Make your way up the mountain, find that snitch.
			IF flag_bcr1_skip = 1
				SET_UP_SKIP -2767.8828 -1634.1143 140.3939 0.0569
			ENDIF
			bc1_stage = 0
		ENDIF
	ENDIF
ENDIF


IF bc1_stage = 0
	IF NOT IS_CHAR_DEAD scplayer
		IF bc1_create = 0 
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer bc1_locate_x bc1_locate_y bc1_locate_z 150.0 150.0 150.0 FALSE
				REMOVE_BLIP bc1_hut_blip
				bc1_locate_x = -2774.9807
				bc1_locate_y = -1591.2599
				bc1_locate_z = 140.3880 
				ADD_BLIP_FOR_COORD bc1_locate_x bc1_locate_y bc1_locate_z bc1_hut_blip
				//GOSUB bc1_bmx_create
				bc1_cut = 0
			   	bc1_create = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bc1_stage = 0
	IF NOT IS_CHAR_DEAD scplayer
		IF bc1_cut > 1
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				bc1_pressed = 0
			ENDIF
			IF bc1_pressed = 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					CLEAR_PRINTS
					bc1_timer_start -= 1000
					bc1_cut = 6
				ENDIF
			ENDIF
	   	ENDIF
		IF bc1_cut = 0 
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer bc1_locate_x bc1_locate_y bc1_locate_z 100.0 100.0 100.0 FALSE
				bc1_pressed = 1
				SET_PLAYER_CONTROL player1 OFF
				DO_FADE 300 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				GOSUB bc1_target_create
				GOSUB bc1_marshall_create
				GOSUB bc1_marshall_car_create
				GOSUB bc1_bmx_new_coords
				
				GET_GAME_TIMER bc1_timer_start
				IF flag_bcr1_skip = 1
					CLEAR_SKIP
				ENDIF
				IF flag_bcr1_skip = 0
					flag_bcr1_skip = 1
				ENDIF
				bc1_cut = 1
			ENDIF
		ENDIF
		IF bc1_cut = 1
			GET_GAME_TIMER bc1_timer_end
			bc1_timer_diff = bc1_timer_end - bc1_timer_start
			IF bc1_timer_diff > 250
				LOAD_SCENE bc1_hut_x bc1_hut_y bc1_hut_z
				SET_FIXED_CAMERA_POSITION -2795.8606 -1536.9749 139.3093 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2796.5530 -1536.2822 139.5107 JUMP_CUT
				CLEAR_AREA bc1_hut_x bc1_hut_y bc1_hut_z 75.0 TRUE
				SWITCH_WIDESCREEN ON
				SET_FADING_COLOUR 0 0 0
				DO_FADE 300 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				PRINT_NOW ( BCR1_04 ) 3000 1 // He's inside. Smoke him out!
			   	REMOVE_BLIP bc1_hut_blip
				IF NOT IS_CHAR_DEAD bc1_marshall[1]
					PERFORM_SEQUENCE_TASK bc1_marshall[1] bc1_patrol[1]
				ENDIF
				IF NOT IS_CHAR_DEAD bc1_marshall[2]
					PERFORM_SEQUENCE_TASK bc1_marshall[2] bc1_patrol[2]
				ENDIF
				IF NOT IS_CHAR_DEAD bc1_marshall[3]
					PERFORM_SEQUENCE_TASK bc1_marshall[3] bc1_patrol[3]
				ENDIF
				IF NOT IS_CHAR_DEAD bc1_marshall[4]
					PERFORM_SEQUENCE_TASK bc1_marshall[4] bc1_patrol[4]
				ENDIF

				GET_GAME_TIMER bc1_timer_start
				bc1_cut = 2
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD bc1_target
			IF bc1_cut = 2
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 3000
					PRINT_NOW ( BCR1_19 ) 3000 1 // Be wary of the U.S. Marshalls. 
					GET_GAME_TIMER bc1_timer_start
					bc1_cut = 3
				ENDIF
			ENDIF
			IF bc1_cut = 3
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 3000
					PRINT_NOW ( BCR1_20 ) 4000 1 // And don't let him escape.
					SET_FIXED_CAMERA_POSITION -2793.0923 -1510.4733 139.2297 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2793.9470 -1510.9451 139.4455 JUMP_CUT
					GET_GAME_TIMER bc1_timer_start
					bc1_cut = 4
				ENDIF
			ENDIF
			IF bc1_cut = 4
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 2000
					GET_GAME_TIMER bc1_timer_start
					bc1_cut = 5
				ENDIF
			ENDIF
			IF bc1_cut = 5
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 1500
					GET_GAME_TIMER bc1_timer_start
					bc1_cut = 6
				ENDIF
			ENDIF
		   	IF bc1_cut = 6
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 500
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					IF NOT IS_CHAR_DEAD bc1_target
					AND NOT IS_CAR_DEAD bc1_marshall_car
						ADD_BLIP_FOR_CHAR bc1_target bc1_target_blip
						bc1_stage = 1
						bc1_cut = 7
						OPEN_SEQUENCE_TASK bc1_run_to_car
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2822.4700 -1518.5946 139.7656 PEDMOVE_WALK -2
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2822.9700 -1514.2443 138.2893 PEDMOVE_WALK -2
							TASK_ENTER_CAR_AS_DRIVER -1 bc1_marshall_car 999999999999
						CLOSE_SEQUENCE_TASK bc1_run_to_car
		  			ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF 
 
IF bc1_stage = 1
	IF IS_CHAR_ON_FOOT scplayer
		IF IS_CHAR_DEAD bc1_target
			IF NOT IS_CHAR_DEAD bc1_marshall[1]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[1] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[2]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[2] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[3]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[3] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[4]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[4] scplayer
			ENDIF
			IF NOT IS_CAR_DEAD bc1_marshall_car
				SET_CAR_PROOFS bc1_marshall_car FALSE FALSE FALSE FALSE FALSE
				LOCK_CAR_DOORS bc1_marshall_car CARLOCK_UNLOCKED
				FREEZE_CAR_POSITION bc1_marshall_car FALSE
			ENDIF
			//PRINT_NOW ( BCR1_10 ) 5000 1 // Take the photograph...
			//bc1_stage = 7
		ENDIF
		IF IS_CHAR_DEAD	bc1_marshall[1]
		OR IS_CHAR_DEAD	bc1_marshall[2]
		OR IS_CHAR_DEAD	bc1_marshall[3]
		OR IS_CHAR_DEAD	bc1_marshall[4]
			IF bc1_onfoot_attack[1] = 0
				IF NOT IS_CHAR_DEAD bc1_marshall[1]
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[1] scplayer
					bc1_onfoot_attack[1] = 1
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[2] = 0
				IF NOT IS_CHAR_DEAD bc1_marshall[2]
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[2] scplayer
					bc1_onfoot_attack[2] = 1
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[3] = 0
				IF NOT IS_CHAR_DEAD bc1_marshall[3]
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[3] scplayer
					bc1_onfoot_attack[3] = 1
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[4] = 0
				IF NOT IS_CHAR_DEAD bc1_marshall[4]
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[4] scplayer
					bc1_onfoot_attack[4] = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	// locate gosub
	IF NOT IS_CHAR_DEAD scplayer
	// marshall 1
		IF NOT IS_CHAR_DEAD bc1_marshall[1]
			bc1_cur_marshall = 1
			GOSUB bc1_marshall_locate
			bc1_marshall_x[1] =	bc1_cur_marsh_x 
			bc1_marshall_y[1] = bc1_cur_marsh_y 
			bc1_marshall_z[1] = bc1_cur_marsh_z
			IF bc1_car_attack[1] = 0
		   		IF LOCATE_CHAR_IN_CAR_3D scplayer bc1_marshall_x[1] bc1_marshall_y[1] bc1_marshall_z[1] 30.0 30.0 30.0 FALSE
					IF NOT IS_CAR_DEAD bc1_player_car
						TASK_DESTROY_CAR bc1_marshall[1] bc1_player_car
						bc1_car_attack[1] = 1
						bc1_onfoot_attack[1] = 0
						IF bc1_drive_car = 0
							IF NOT IS_CAR_DEAD bc1_marshall_car
								IF NOT IS_CHAR_DEAD bc1_target
									PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
									bc1_drive_car = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[1] = 0
				IF LOCATE_CHAR_ON_FOOT_3D scplayer bc1_marshall_x[1] bc1_marshall_y[1] bc1_marshall_z[1] 5.0 5.0 5.0 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[1] scplayer
					bc1_onfoot_attack[1] = 1
					bc1_car_attack[1] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF IS_CHAR_SHOOTING_IN_AREA scplayer -2774.03 -1572.78 -2837.13 -1468.24 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[1] scplayer
					bc1_onfoot_attack[1] = 1
					bc1_car_attack[1] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	// marshall 2
		IF NOT IS_CHAR_DEAD bc1_marshall[2]
			bc1_cur_marshall = 2
			GOSUB bc1_marshall_locate
			bc1_marshall_x[2] =	bc1_cur_marsh_x 
			bc1_marshall_y[2] = bc1_cur_marsh_y 
			bc1_marshall_z[2] = bc1_cur_marsh_z
			IF bc1_car_attack[2] = 0
				IF LOCATE_CHAR_IN_CAR_3D scplayer bc1_marshall_x[2] bc1_marshall_y[2] bc1_marshall_z[2] 30.0 30.0 30.0 FALSE
					IF NOT IS_CAR_DEAD bc1_player_car
						TASK_DESTROY_CAR bc1_marshall[2] bc1_player_car
						bc1_car_attack[2] = 1
						bc1_onfoot_attack[2] = 0
						IF bc1_drive_car = 0
							IF NOT IS_CAR_DEAD bc1_marshall_car
								IF NOT IS_CHAR_DEAD bc1_target
									PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
									bc1_drive_car = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[2] = 0
				IF LOCATE_CHAR_ON_FOOT_3D scplayer bc1_marshall_x[2] bc1_marshall_y[2] bc1_marshall_z[2] 5.0 5.0 5.0 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[2] scplayer
					bc1_onfoot_attack[2] = 1
					bc1_car_attack[2] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF IS_CHAR_SHOOTING_IN_AREA scplayer -2774.03 -1572.78 -2837.13 -1468.24 FALSE
	 				TASK_KILL_CHAR_ON_FOOT bc1_marshall[2] scplayer
					bc1_onfoot_attack[2] = 1
					bc1_car_attack[2] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
		   	ENDIF
		ENDIF
	// marshall 3
		IF NOT IS_CHAR_DEAD bc1_marshall[3]
			bc1_cur_marshall = 3
			GOSUB bc1_marshall_locate
			bc1_marshall_x[3] =	bc1_cur_marsh_x 
			bc1_marshall_y[3] = bc1_cur_marsh_y 
			bc1_marshall_z[3] = bc1_cur_marsh_z
			IF bc1_car_attack[3] = 0
		   		IF LOCATE_CHAR_IN_CAR_3D scplayer bc1_marshall_x[3] bc1_marshall_y[3] bc1_marshall_z[3] 30.0 30.0 30.0 FALSE
					IF NOT IS_CAR_DEAD bc1_player_car
						TASK_DESTROY_CAR bc1_marshall[3] bc1_player_car
						bc1_car_attack[3] = 1
						bc1_onfoot_attack[3] = 0
						IF bc1_drive_car = 0
							IF NOT IS_CAR_DEAD bc1_marshall_car
								IF NOT IS_CHAR_DEAD bc1_target
									PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
									bc1_drive_car = 1
								ENDIF
							ENDIF
						ENDIF					
					ENDIF
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[3] = 0
				IF LOCATE_CHAR_ON_FOOT_3D scplayer bc1_marshall_x[3] bc1_marshall_y[3] bc1_marshall_z[3] 5.0 5.0 5.0 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[3] scplayer
					bc1_onfoot_attack[3] = 1
					bc1_car_attack[3] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF			
				ENDIF
				IF IS_CHAR_SHOOTING_IN_AREA scplayer -2774.03 -1572.78 -2837.13 -1468.24 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[3] scplayer
					bc1_onfoot_attack[3] = 1
					bc1_car_attack[3] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF				
				ENDIF
		   	ENDIF
		ENDIF
	// marshall 4
		IF NOT IS_CHAR_DEAD bc1_marshall[4]
			bc1_cur_marshall = 4
			GOSUB bc1_marshall_locate
			bc1_marshall_x[4] =	bc1_cur_marsh_x 
			bc1_marshall_y[4] = bc1_cur_marsh_y 
			bc1_marshall_z[4] = bc1_cur_marsh_z
			IF bc1_car_attack[4] = 0
				IF LOCATE_CHAR_IN_CAR_3D scplayer bc1_marshall_x[4] bc1_marshall_y[4] bc1_marshall_z[4] 30.0 30.0 30.0 FALSE
					IF NOT IS_CAR_DEAD bc1_player_car
						TASK_DESTROY_CAR bc1_marshall[4] bc1_player_car
						bc1_car_attack[4] = 1
						bc1_onfoot_attack[4] = 0
						IF bc1_drive_car = 0
							IF NOT IS_CAR_DEAD bc1_marshall_car
								IF NOT IS_CHAR_DEAD bc1_target
									PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
									bc1_drive_car = 1
								ENDIF
							ENDIF
						ENDIF					
					ENDIF
				ENDIF
			ENDIF
			IF bc1_onfoot_attack[4] = 0
				IF LOCATE_CHAR_ON_FOOT_3D scplayer bc1_marshall_x[4] bc1_marshall_y[4] bc1_marshall_z[4] 5.0 5.0 5.0 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[4] scplayer
					bc1_onfoot_attack[4] = 1
					bc1_car_attack[4] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF	
						ENDIF
					ENDIF			
				ENDIF
				IF IS_CHAR_SHOOTING_IN_AREA scplayer -2774.03 -1572.78 -2837.13 -1468.24 FALSE
					TASK_KILL_CHAR_ON_FOOT bc1_marshall[4] scplayer
					bc1_onfoot_attack[4] = 1
					bc1_car_attack[4] = 0
					IF bc1_drive_car = 0
						IF NOT IS_CAR_DEAD bc1_marshall_car
							IF NOT IS_CHAR_DEAD bc1_target
								PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car
								bc1_drive_car = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
		  	ENDIF
		ENDIF
		IF bc1_drive_car = 1
			IF NOT IS_CAR_DEAD bc1_marshall_car
				IF NOT IS_CHAR_DEAD bc1_target
					IF IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
						bc1_cut = 0
						bc1_stage = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD bc1_target
			IF NOT IS_CHAR_DEAD bc1_marshall[1]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[1] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[2]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[2] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[3]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[3] scplayer
			ENDIF
			IF NOT IS_CHAR_DEAD bc1_marshall[4]
				TASK_KILL_CHAR_ON_FOOT bc1_marshall[4] scplayer
			ENDIF
			//PRINT_NOW ( BCR1_10 ) 5000 1 // Take the photograph...
			//bc1_stage = 7
			IF NOT IS_CAR_DEAD bc1_marshall_car
				SET_CAR_PROOFS bc1_marshall_car FALSE FALSE FALSE FALSE FALSE
				LOCK_CAR_DOORS bc1_marshall_car CARLOCK_UNLOCKED
				FREEZE_CAR_POSITION bc1_marshall_car FALSE
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD bc1_marshall[1]
		AND IS_CHAR_DEAD bc1_marshall[2]
		AND IS_CHAR_DEAD bc1_marshall[3]
		AND IS_CHAR_DEAD bc1_marshall[4]
			bc1_cut = 0
			bc1_stage = 2
		ENDIF
	ENDIF
ENDIF

// set target to get in car and drive away
IF bc1_stage = 2
	IF NOT IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF bc1_drive_car = 0
				PERFORM_SEQUENCE_TASK bc1_target bc1_run_to_car // if car is hidden prior to getaway, teleport instantly
				bc1_drive_car = 1
				bc1_cut = 0					   
			   	bc1_stage = 3
			ELSE
				bc1_cut = 0					   
			   	bc1_stage = 3
			ENDIF								  
		ELSE
			PRINT_NOW ( BCR1_10 ) 5000 1 // Take the photograph...
			bc1_stage = 7
		ENDIF
	ENDIF
ENDIF

// tell player to avoid blowing up the car as he needs remains that can be identified, photographed.
// set car properties / mission. tell the target to drive to the nearest police station. that's the time limit.
IF bc1_stage = 3
	IF NOT IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF IS_CHAR_IN_CAR bc1_target bc1_marshall_car
				IF bc1_cut > 2
					bc1_cut = 0
				ENDIF
				IF IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bc1_marshall_car 0.0 4.0 0.0 bc1_offset_x bc1_offset_y bc1_offset_z
					bc1_min_x = bc1_offset_x - 1.5
					bc1_min_y = bc1_offset_y - 1.5
					bc1_min_z = bc1_offset_z - 1.5
					bc1_max_x = bc1_offset_x + 1.5
					bc1_max_y = bc1_offset_y + 1.5
					bc1_max_z = bc1_offset_z + 1.5
					IF IS_AREA_OCCUPIED bc1_min_x bc1_min_y bc1_min_z bc1_max_x bc1_max_y bc1_max_z FALSE FALSE TRUE FALSE FALSE
						STOP_PLAYBACK_RECORDED_CAR bc1_marshall_car
					ENDIF
				ENDIF
				IF IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
					IF LOCATE_CHAR_IN_CAR_3D bc1_target bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] 20.0 20.0 20.0 FALSE
						bc1_route++
						bc1_route_nx++
					ENDIF
				ENDIF
				IF bc1_cut = 0
					SET_CAR_HEALTH bc1_marshall_car 1000
					SET_CAR_CRUISE_SPEED bc1_marshall_car 40.0
					SET_CAR_DRIVING_STYLE bc1_marshall_car DRIVINGMODE_PLOUGHTHROUGH
					SET_CAR_ONLY_DAMAGED_BY_PLAYER bc1_marshall_car TRUE
					SET_CAR_PROOFS bc1_marshall_car FALSE FALSE FALSE FALSE FALSE
					REQUEST_CAR_RECORDING 170
					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 170
						WAIT 0
					ENDWHILE  
					IF NOT IS_CAR_DEAD bc1_marshall_car 
					AND NOT IS_CHAR_DEAD bc1_target
						IF IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
							//SET_CHAR_DECISION_MAKER bc1_target DM_PED_RANDOM_WEAK
							FREEZE_CAR_POSITION bc1_marshall_car FALSE
							START_PLAYBACK_RECORDED_CAR bc1_marshall_car 170
							SET_PLAYBACK_SPEED bc1_marshall_car 0.65
							ADD_STUCK_CAR_CHECK_WITH_WARP bc1_marshall_car 1.0 2000 TRUE TRUE TRUE 1
//						ELSE 
//							IF NOT IS_CHAR_IN_CAR bc1_target bc1_marshall_car
//								SET_CHAR_DECISION_MAKER bc1_target DM_PED_RANDOM_WEAK
//								WARP_CHAR_INTO_CAR bc1_target bc1_marshall_car
//								FREEZE_CAR_POSITION bc1_marshall_car FALSE
//								START_PLAYBACK_RECORDED_CAR bc1_marshall_car 170
//								SET_PLAYBACK_SPEED bc1_marshall_car 0.65
//								ADD_STUCK_CAR_CHECK_WITH_WARP bc1_marshall_car 1.0 2000 TRUE TRUE TRUE 1
//							ENDIF	
						ENDIF
					ENDIF
					PRINT_NOW ( BCR1_07 ) 4000 1 // He's getting away"
					IF NOT IS_CAR_DEAD bc1_bmx
						IF NOT IS_CHAR_ON_ANY_BIKE scplayer
						AND NOT IS_CHAR_IN_ANY_CAR scplayer
							PRINT ( BCR1_14	) 4000 1
						ENDIF
					ENDIF
					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON
					SET_FIXED_CAMERA_POSITION -2795.2522 -1505.9489 138.8073 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2795.9998 -1506.5857 138.9953 JUMP_CUT
					GET_GAME_TIMER bc1_timer_start
					bc1_cut = 1

				ENDIF
				IF bc1_cut = 1
					IF IS_CHAR_ON_SCREEN scplayer
						IF IS_CHAR_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer bc1_player_car
							SET_CAR_COORDINATES bc1_player_car -2789.6721 -1491.1418 137.1726 
							SET_CAR_HEADING bc1_player_car 227.9636
						ELSE
							SET_CHAR_COORDINATES scplayer -2789.6721 -1491.1418 137.1726
							SET_CHAR_HEADING scplayer 227.9636	
						ENDIF
					ENDIF
					GET_GAME_TIMER bc1_timer_end
					bc1_timer_diff = bc1_timer_end - bc1_timer_start
					IF bc1_timer_diff > 4000
						GET_GAME_TIMER bc1_timer_start
						bc1_cut = 2
					ENDIF	 
				ENDIF
			ENDIF
			IF bc1_cut = 2
				GET_GAME_TIMER bc1_timer_end
				bc1_timer_diff = bc1_timer_end - bc1_timer_start
				IF bc1_timer_diff > 1
					//CLEAR_CUTSCENE 
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					bc1_stage = 4
					//bc1_cut = 0
				ENDIF	 
			ENDIF
		ELSE
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			PRINT_NOW ( BCR1_10 ) 5000 1 // Take the photograph...
			bc1_stage = 7
		ENDIF
	ELSE
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		bc1_stage = 4
	ENDIF			
ENDIF


// when car is burning - stop car. 
IF bc1_stage = 4
	IF IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF NOT IS_CHAR_ON_FOOT bc1_target
				DELETE_CHAR bc1_target
				PRINT_NOW ( BCR1_09 ) 5000 1	// He can't be identified!
				GOTO mission_failed_bcrash1
			ELSE
				bc1_stage = 7
			ENDIF
		ENDIF
	ENDIF
//	IF IS_CHAR_DEAD bc1_target
//		REMOVE_BLIP bc1_target_blip
//		ADD_BLIP_FOR_DEAD_CHAR bc1_target bc1_target_blip
//		bc1_stage = 7
//	ENDIF

	IF NOT IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
		AND NOT IS_CHAR_DEAD scplayer
			
			IF IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
				IF IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
					IF LOCATE_CHAR_IN_CAR_3D bc1_target bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] 15.0 15.0 15.0 FALSE
						bc1_route++
						bc1_route_nx++
					ENDIF
				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
//					IF bc1_target_z < 40.0
//						bc1_route = bc1_final_dest
//					ENDIF
					IF bc1_wander = 0
						TASK_CAR_DRIVE_TO_COORD bc1_target bc1_marshall_car bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] bc1_car_speed MODE_RACING FALSE DRIVINGMODE_AVOIDCARS
						bc1_wander = 1
					ENDIF
					IF IS_CAR_ON_FIRE bc1_marshall_car
					OR IS_CAR_UPSIDEDOWN bc1_marshall_car
						SET_CAR_CRUISE_SPEED bc1_marshall_car 0.0
						IF NOT IS_CHAR_DEAD bc1_target
							SET_CHAR_RELATIONSHIP bc1_target ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_DECISION_MAKER bc1_target DM_PED_RANDOM_WEAK
						ENDIF
						bc1_stage = 5
					ENDIF
					IF bc1_wander = 1
						IF bc1_route < bc1_final_dest
							GOSUB bc1_waypoint_check
							IF LOCATE_CHAR_IN_CAR_3D bc1_target bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] 5.0 5.0 5.0 FALSE
//								IF bc1_route = 3
//								OR bc1_route = 4
//								OR bc1_route = 6
//									bc1_car_speed = 30.0
//								ELSE
//									IF bc1_route = 8
//									OR bc1_route = 9
//										bc1_car_speed = 15.0
//									ENDIF	

									IF bc1_route > 7
									AND bc1_route < 19
										bc1_car_speed = 20.0
									ELSE
										bc1_car_speed = 30.0
									ENDIF
									
//								ENDIF	
								//SET_CAR_CRUISE_SPEED bc1_marshall_car bc1_car_speed
								bc1_route++
								bc1_route_nx++
								TASK_CAR_DRIVE_TO_COORD bc1_target bc1_marshall_car bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] bc1_car_speed MODE_RACING FALSE DRIVINGMODE_AVOIDCARS
							ENDIF
						ELSE
							IF LOCATE_CHAR_IN_CAR_3D bc1_target bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_route_z[bc1_route] 8.0 8.0 8.0 FALSE
								TASK_CAR_DRIVE_WANDER bc1_target bc1_marshall_car 50.0 DRIVINGMODE_AVOIDCARS
								SET_CAR_CRUISE_SPEED bc1_marshall_car 50.0	
								bc1_wander = 2
							ENDIF
						ENDIF
					ENDIF
					IF bc1_wander = 2
						IF NOT IS_CHAR_ON_SCREEN bc1_target
							IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bc1_target 100.0 100.0 100.0 FALSE
								PRINT_NOW ( BCR1_12 ) 6000 1	// You let him get away
								GOTO mission_failed_bcrash1
							ENDIF
						ENDIF
					ENDIF		
				ENDIF
				IF bc1_speed_boost = 0
					GET_GAME_TIMER bc1_timer_end
					bc1_timer_diff = bc1_timer_end - bc1_timer_start
					IF bc1_timer_diff > 6000
						IF IS_CHAR_IN_ANY_CAR scplayer
							IF IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
								IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer bc1_marshall_car 30.0 30.0 30.0 FALSE
									SET_PLAYBACK_SPEED bc1_marshall_car 0.8
									PRINT_NOW ( BCR1_15 ) 6000 1
									bc1_speed_boost = 1
							  	ENDIF
							ENDIF
						ENDIF
					ENDIF
			   	ENDIF
				IF IS_PLAYBACK_GOING_ON_FOR_CAR bc1_marshall_car
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bc1_marshall_car 0.0 4.0 0.0 bc1_offset_x bc1_offset_y bc1_offset_z
					bc1_min_x = bc1_offset_x - 1.5
					bc1_min_y = bc1_offset_y - 1.5
					bc1_min_z = bc1_offset_z - 1.5
					bc1_max_x = bc1_offset_x + 1.5
					bc1_max_y = bc1_offset_y + 1.5
					bc1_max_z = bc1_offset_z + 1.5
					IF IS_AREA_OCCUPIED bc1_min_x bc1_min_y bc1_min_z bc1_max_x bc1_max_y bc1_max_z FALSE TRUE FALSE FALSE FALSE
						STOP_PLAYBACK_RECORDED_CAR bc1_marshall_car
					ENDIF
					IF IS_CAR_ON_FIRE bc1_marshall_car
						STOP_PLAYBACK_RECORDED_CAR bc1_marshall_car
						SET_CAR_CRUISE_SPEED bc1_marshall_car 0.0
						bc1_stage = 5
					ENDIF
					GET_CAR_HEALTH bc1_marshall_car bc1_health
					IF bc1_health < 900
						STOP_PLAYBACK_RECORDED_CAR bc1_marshall_car
					ENDIF
				ENDIF
				IF bc1_onscreen = 1
					GET_CHAR_COORDINATES scplayer bc1_player_x bc1_player_y bc1_player_z
					GET_CHAR_COORDINATES bc1_target bc1_target_x bc1_target_y bc1_target_z
					IF NOT IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE 
						GET_GAME_TIMER bc1_timer_start
						bc1_onscreen = 0
					ENDIF
				ENDIF
				IF bc1_onscreen = 0
					GET_CHAR_COORDINATES scplayer bc1_player_x bc1_player_y bc1_player_z
					GET_CHAR_COORDINATES bc1_target bc1_target_x bc1_target_y bc1_target_z
					IF NOT IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE
						GET_GAME_TIMER bc1_timer_end
						bc1_timer_diff = bc1_timer_end - bc1_timer_start
						IF bc1_timer_diff > 1000
						AND bc1_timer_diff < 6000
							PRINT_NOW ( BCR1_07 ) 100 1
						ENDIF
						IF bc1_timer_diff > 30000
							IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bc1_target 25.0 25.0 25.0 FALSE
								PRINT_NOW ( BCR1_12 ) 5000 1 // You let him get away!
								GOTO mission_failed_bcrash1
							ENDIF
						ENDIF
					ENDIF
					IF IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE
						bc1_onscreen = 1
					ENDIF
				ENDIF
			ENDIF
			IF IS_CAR_IN_WATER bc1_marshall_car
				IF DOES_CAR_HAVE_STUCK_CAR_CHECK bc1_marshall_car
					REMOVE_STUCK_CAR_CHECK bc1_marshall_car
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// When car is burning - run away from it.
IF bc1_stage = 5
	IF IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF NOT IS_CHAR_ON_FOOT bc1_target
				DELETE_CHAR bc1_target
				PRINT_NOW ( BCR1_09 ) 5000 1	// He can't be identified!
				GOTO mission_failed_bcrash1
			ELSE
				bc1_stage = 6
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF IS_CAR_STOPPED bc1_marshall_car
				IF IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
		//			SET_CHAR_NEVER_TARGETTED bc1_target FALSE
					GET_CAR_COORDINATES bc1_marshall_car bc1_marshall_car_x bc1_marshall_car_y bc1_marshall_car_z
					TASK_LEAVE_CAR_AND_FLEE bc1_target bc1_marshall_car bc1_marshall_car_x bc1_marshall_car_y bc1_marshall_car_z // flee from burning car
					bc1_stage = 6
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD bc1_target
		AND NOT IS_CAR_DEAD bc1_marshall_car
			IF IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
				TASK_LEAVE_CAR_AND_FLEE bc1_target bc1_marshall_car bc1_marshall_car_x bc1_marshall_car_y bc1_marshall_car_z // flee from burning car
				bc1_stage = 6
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bc1_stage > 0
AND bc1_stage < 7
	IF DOES_CHAR_EXIST bc1_target
		IF IS_CHAR_HEAD_MISSING bc1_target
			IF NOT IS_CAR_DEAD bc1_marshall_car
				LOCK_CAR_DOORS bc1_marshall_car CARLOCK_UNLOCKED
			ENDIF
			PRINT_NOW ( BCR1_09 ) 5000 1 // He can't be identified!
			GOTO mission_failed_bcrash1
		ENDIF
		IF IS_CHAR_DEAD bc1_target
			IF NOT IS_CAR_DEAD bc1_marshall_car
				LOCK_CAR_DOORS bc1_marshall_car CARLOCK_UNLOCKED
			ENDIF
			bc1_stage = 7
		ENDIF	
		IF NOT IS_CHAR_DEAD bc1_target
			IF HAS_CHAR_BEEN_PHOTOGRAPHED bc1_target
			ENDIF
		ENDIF
//		IF bc1_stage < 3
//			IF NOT IS_CAR_DEAD bc1_marshall_car
//				IF NOT IS_CHAR_DEAD bc1_target
//					IF IS_CHAR_IN_CAR bc1_target bc1_marshall_car
//						bc1_stage = 3
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
		IF bc1_drive_car = 1
			IF NOT bc1_flee = 2
				IF IS_CAR_DEAD bc1_marshall_car
					IF NOT IS_CHAR_DEAD bc1_target
						OPEN_SEQUENCE_TASK bc1_run_away
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2822.4700 -1518.5946 139.7656 PEDMOVE_WALK -2
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2822.9700 -1514.2443 138.2893 PEDMOVE_RUN -2
							TASK_FLEE_CHAR -1 scplayer 1000.0 5000000
						CLOSE_SEQUENCE_TASK bc1_run_away
						PERFORM_SEQUENCE_TASK bc1_target bc1_run_away
						bc1_flee = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// remove car blip and add target blip
IF bc1_stage = 6
	IF NOT IS_CAR_DEAD bc1_marshall_car
		IF NOT IS_CHAR_DEAD bc1_target
			IF NOT IS_CHAR_SITTING_IN_CAR bc1_target bc1_marshall_car
				REMOVE_BLIP bc1_target_blip
				ADD_BLIP_FOR_CHAR bc1_target bc1_target_blip
				PRINT_NOW ( BCR1_08 ) 5000 1 // He's getting away on foot"
				
				// PRINT_NOW he's getting away on foot!
				bc1_stage = 7
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bc1_stage > 3
	IF bc1_target_photo = 0
		IF NOT DOES_CHAR_EXIST bc1_target
			PRINT_NOW ( BCR1_09 ) 5000 1 // He can't be identified!
			GOTO mission_failed_bcrash1
		ENDIF
	ENDIF
ENDIF

// check that photo has been taken of dead target
IF bc1_stage = 7
	IF DOES_CHAR_EXIST bc1_target
		IF IS_CHAR_HEAD_MISSING bc1_target
			PRINT_NOW ( BCR1_09 ) 5000 1 // He can't be identified!
			GOTO mission_failed_bcrash1
		ENDIF
	ENDIF

	IF bc1_target_photo = 0
		IF NOT IS_CHAR_DEAD bc1_target
			IF bc1_flee = 0
				GET_CHAR_COORDINATES bc1_target bc1_target_x bc1_target_y bc1_target_z
				IF NOT IS_CAR_DEAD bc1_marshall_car
					GET_SCRIPT_TASK_STATUS bc1_target TASK_LEAVE_CAR_AND_FLEE bc1_status
				ELSE
					bc1_status = FINISHED_TASK
				ENDIF	
				IF bc1_status = FINISHED_TASK
					TASK_FLEE_CHAR bc1_target scplayer 50.0 100000000
					bc1_flee = 1
					bc1_status = 5
				ENDIF
			ENDIF
			IF bc1_flee = 1
				GET_SCRIPT_TASK_STATUS bc1_target TASK_FLEE_CHAR bc1_status
				IF bc1_status = FINISHED_TASK
					TASK_FLEE_CHAR bc1_target scplayer 50.0 100000000
				ENDIF
			ENDIF 
			IF bc1_onscreen = 1
				GET_CHAR_COORDINATES scplayer bc1_player_x bc1_player_y bc1_player_z
				GET_CHAR_COORDINATES bc1_target bc1_target_x bc1_target_y bc1_target_z
				IF NOT IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE
					GET_GAME_TIMER bc1_timer_start
					bc1_onscreen = 0
				ENDIF
			ENDIF
			IF bc1_onscreen = 0
				GET_CHAR_COORDINATES scplayer bc1_player_x bc1_player_y bc1_player_z
				GET_CHAR_COORDINATES bc1_target bc1_target_x bc1_target_y bc1_target_z
				IF NOT IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE
					GET_GAME_TIMER bc1_timer_end
					bc1_timer_diff = bc1_timer_end - bc1_timer_start
					IF bc1_timer_diff > 1000
						PRINT_NOW ( BCR1_07 ) 100 1
					ENDIF
					IF bc1_timer_diff > 15000					    
						PRINT_NOW ( BCR1_12 ) 5000 1 // You let him get away!
						GOTO mission_failed_bcrash1
					ENDIF
				ENDIF
				IF IS_LINE_OF_SIGHT_CLEAR bc1_player_x bc1_player_y bc1_player_z bc1_target_x bc1_target_y bc1_target_z TRUE FALSE FALSE FALSE FALSE
					bc1_onscreen = 1
				ENDIF
			ENDIF
	   	ENDIF
		IF NOT IS_CHAR_DEAD scplayer
			IF DOES_CHAR_EXIST bc1_target
				IF HAS_CHAR_BEEN_PHOTOGRAPHED bc1_target
					IF IS_CHAR_DEAD bc1_target				
						REMOVE_BLIP	bc1_target_blip
						bc1_target_photo = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF bc1_target_photo = 1
		ADD_BLIP_FOR_COORD -2044.10 -2523.86 31.11 bc1_drop_blip
		PRINT_NOW ( BCR1_17 ) 5000 1
		bc1_stage = 8
	ENDIF
ENDIF

IF bc1_stage = 8
	IF NOT IS_CHAR_DEAD scplayer
		IF LOCATE_CHAR_ON_FOOT_3D scplayer -2044.10 -2523.86 31.11 1.2 1.2 2.0 TRUE 
			SET_CHAR_AMMO scplayer WEAPONTYPE_CAMERA 0
			GOTO mission_passed_bcrash1
		ENDIF
	ENDIF
ENDIF


IF bc1_stage = 7
	//IF bc1_flee = 1
		IF bc1_target_photo = 0
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA bc1_camera_ammo

			IF bc1_camera_ammo > 0
				IF bc1_camera_ammo_flag = 2
					ADD_BLIP_FOR_DEAD_CHAR bc1_target bc1_target_blip
					REMOVE_BLIP bc1_camera_pickup_blip
					bc1_camera_ammo_flag = 1
				ENDIF
				IF bc1_camera_ammo_flag = 1
					IF IS_CHAR_IN_WATER bc1_target
						bc1_in_water = 1
					ENDIF
					IF NOT IS_CHAR_DEAD scplayer
					AND IS_CHAR_DEAD bc1_target
						PRINT_NOW ( BCR1_10 ) 100 1 // Take the photograph...
						IF DOES_CHAR_EXIST bc1_target
							IF IS_CHAR_DEAD bc1_target				
						   		IF bc1_photo_loc = 0
									IF LOCATE_CHAR_ANY_MEANS_3D scplayer bc1_target_x bc1_target_y bc1_target_z 5.0 5.0 5.0 FALSE
										bc1_photo_loc = 1
									ENDIF
								ENDIF
								IF bc1_photo_loc = 1
									IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer bc1_target_x bc1_target_y bc1_target_z 100.0 100.0 30.0 FALSE
										IF bc1_in_water = 1
											PRINT_NOW ( BCR1_16 ) 5000 1 // ******Change to: The sharks got him!
											GOTO mission_failed_bcrash1
										ELSE
											PRINT_NOW ( BCR1_16 ) 5000 1 // The buzzards got him!
											GOTO mission_failed_bcrash1
										ENDIF
									ENDIF
								ENDIF	
						   	ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF IS_CHAR_DEAD bc1_target
					IF bc1_camera_ammo_flag = 1
						IF bc1_camera_ammo = 0
							bc1_camera_ammo_flag = 0
						ENDIF
					ENDIF
					IF bc1_camera_ammo_flag = 0
						PRINT_NOW ( BCR1_11 ) 5000 1 // You need a camera, go and pick one up.
						REMOVE_BLIP	bc1_target_blip
						CREATE_PICKUP_WITH_AMMO CAMERA PICKUP_ONCE 24 -2044.10 -2523.86 31.11 bc1_camera_pickup
						ADD_BLIP_FOR_PICKUP bc1_camera_pickup bc1_camera_pickup_blip 
						bc1_camera_ammo_flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	//ENDIF
	IF bc1_flee = 0
	AND NOT bc1_stage = 4
	AND NOT	bc1_stage = 5
		IF NOT IS_CHAR_DEAD	bc1_target
		AND NOT IS_CHAR_DEAD scplayer
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer bc1_target 10.0 10.0 FALSE
				TASK_FLEE_CHAR bc1_target scplayer 50.0 100000000
				bc1_flee = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF

// In car check
IF bc1_stage > 3
	IF bc1_blipped = 1
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD bc1_bmx
		AND NOT IS_CHAR_DEAD bc1_target
			IF IS_CHAR_IN_CAR scplayer bc1_bmx
			OR NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer bc1_bmx 50.0 50.0 FALSE
				PRINT ( BCR1_13 ) 5000 1 // Chase and kill
				bc1_blipped = 0
			ENDIF
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_CHAR_IN_CAR scplayer bc1_bmx
					PRINT ( BCR1_13 ) 5000 1 // Chase and kill
					bc1_blipped = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


GOTO mission_loop_bcrash1

WAIT 1500

RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

GOTO mission_passed_bcrash1

// **************************************** Mission bcrash1 failed ************************

mission_failed_bcrash1:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
IF IS_PLAYER_PLAYING player1
	IF NOT IS_CHAR_DEAD scplayer
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
			SET_CHAR_AMMO scplayer WEAPONTYPE_CAMERA 0
		ENDIF
	ENDIF
ENDIF
IF NOT IS_CHAR_DEAD bc1_target
	DELETE_CHAR bc1_target
ENDIF
RETURN

// **************************************** mission bcrash1 passed ************************

mission_passed_bcrash1:

REGISTER_MISSION_PASSED ( BCRASH1 )
PLAYER_MADE_PROGRESS 1
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 400 5000 1 //"Mission Passed!"
PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"
//ADD_SCORE player1 400
CLEAR_WANTED_LEVEL player1
SET_INT_STAT PASSED_BCRASH1 1
PLAY_MISSION_PASSED_TUNE 1


flag_bcrash_mission_counter ++
REMOVE_BLIP bcrash_contact_blip
call_delay = 15000

//START_NEW_SCRIPT cat_mission_loop
//REMOVE_BLIP catalina_contact_blip[5]
//ADD_SPRITE_BLIP_FOR_COORD 681.37 -477.27 15.32 RADAR_SPRITE_CAT catalina_contact_blip[5] //TRUCK STOP

RETURN

// ********************************** mission cleanup **************************************

mission_cleanup_bcrash1:

GOSUB bc1_target_delete
GOSUB bc1_marshall_delete
GOSUB bc1_marshall_car_delete
IF IS_PLAYER_PLAYING player1
	IF NOT IS_CHAR_DEAD scplayer
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
			SET_CHAR_AMMO scplayer WEAPONTYPE_CAMERA 36
		ENDIF
	ENDIF
ENDIF

IF NOT IS_CAR_DEAD bc1_bmx
	SET_CAR_PROOFS bc1_bmx FALSE FALSE FALSE FALSE FALSE
ENDIF
CLEAR_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA
MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

REMOVE_BLIP bc1_target_blip
REMOVE_BLIP bc1_hut_blip
REMOVE_BLIP bc1_check_blip
REMOVE_BLIP bc1_drop_blip
REMOVE_BLIP bc1_camera_pickup_blip

REMOVE_PICKUP bc1_camera_pickup

flag_player_on_mission = 0

GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN

// gosubs ----------------------------------------------------------------------------------


bc1_marshall_locate:

	GET_CHAR_COORDINATES bc1_marshall[bc1_cur_marshall] bc1_cur_marsh_x bc1_cur_marsh_y bc1_cur_marsh_z 

RETURN

bc1_waypoint_check:
	bc1_closest = -1 
	bc1_distance = 9999999.99
	bc1_route_check = 38
	WHILE bc1_route_check < 38
		IF NOT IS_CHAR_DEAD bc1_target
			GET_DISTANCE_BETWEEN_COORDS_2D bc1_target_x bc1_target_y bc1_route_x[bc1_route] bc1_route_y[bc1_route] bc1_distance_check
			IF bc1_distance_check < bc1_distance
				bc1_distance = bc1_distance_check
				bc1_closest = bc1_route_check
				IF bc1_route_z[bc1_closest] < bc1_route_z[bc1_route]
					bc1_distance_check = bc1_route_z[bc1_route]	- bc1_route_z[bc1_closest]
					IF bc1_distance_check > 3.0
						bc1_route = bc1_closest
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		bc1_route_check++
	ENDWHILE 
RETURN

 
}

/*
[BCR1_01:BCRASH1]
~s~You'll need the ~g~camera~s~.

[BCR1_02:BCRASH1]
~s~Make your way up the mountain, find that ~r~snitch~s~.

[BCR1_03:BCRASH1]
~s~Kill the ~y~witness~s~! ~s~Bring back proof.

[BCR1_04:BCRASH1]
~s~He's inside, smoke him out!

[BCR1_05:BCRASH1]
~s~Be wary of the U.S. Marshalls.

[BCR1_06:BCRASH1]
~s~And don't let him escape!

[BCR1_07:BCRASH1]
~s~The ~r~snitch ~s~is getting away!

[BCR1_08:BCRASH1]
~s~The ~r~snitch ~s~is getting away on foot!

[BCR1_09:BCRASH1]
~r~He can't be identified!

[BCR1_10:BCRASH1]
~s~Photograph the ~r~body~s~.

[BCR1_11:BCRASH1]
~s~You need a ~g~camera, ~s~go and pick one up.

[BCR1_12:BCRASH1]
~r~You let him get away!

[BCR1_13:BCRASH1]
~s~Chase ~r~him ~s~down and kill him!

[BCR1_14:BCRASH1]
~s~Get on the ~y~bike~s~!

[BCR1_15:BCRASH1]
~s~Take ~r~him ~s~out!

[BCR1_16:BCRASH1]
~r~The buzzards got him!

[BCR1_17:BCRASH1]
~s~Take the camera to the ~y~drop~s~.



*/