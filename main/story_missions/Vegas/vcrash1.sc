MISSION_START
// ****************************************************************************************
// ***																					***
// *** Date: 12/08/2003 	Time: 15:50:31	   Name:  Chris Rothwell 					***
// ***																					***
// *** Mission: Vegas Crash 1 - Misappropriation (Uber Chase)				 			***
// ***																					***
// *** Brief: Player has to go to a ghost town in the desert to perform an assassination***
// *** DA and FBI arrive in separate helicopters, the reporter in his own car.  There	***
// *** is a big shoot out around some derelict buildings. The target escapes in a heli.	***
// *** The player must follow him in the other heli; the target flies to a multi-storey	***
// *** car park in Vegas gets out grabs a car and drives all the way down, the player 	***
// *** must chase him down and take him out.											***
// ***																					***
// ****************************************************************************************

GOSUB mission_vcr1_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_vcr1_failed
ENDIF

GOSUB mission_vcr1_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_vcr1_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

SCRIPT_NAME VCRASH1

WAIT 0

LVAR_INT mission_blip mission_blip2 mission_flag mission_timer sequence_task players_car car_health	temp_int
LVAR_INT r c e v m s b a
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 speed temp_float
LOAD_MISSION_TEXT VCR1

MAKE_PLAYER_GANG_DISAPPEAR

LOAD_CUTSCENE CRASHV1
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_VEGAS
 
START_CUTSCENE

CLEAR_AREA 1653.3469 2668.3733 10.8203 10.0 0

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

WHILE NOT IS_PLAYER_PLAYING	player1
	WAIT 0
ENDWHILE

CLEAR_AREA 1599.0898 2667.7246 9.8203 0.5 0
LOAD_SCENE 1599.0898 2667.7246 9.8203
SET_CHAR_COORDINATES scplayer 1599.0898 2667.7246 9.8203 
SET_CHAR_HEADING scplayer 90.0
SET_CAMERA_BEHIND_PLAYER

RELEASE_WEATHER

MAKE_PLAYER_GANG_REAPPEAR

DO_FADE 1000 FADE_IN

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1599.0898 2667.7246 9.8203

PRINT_NOW VCR1__1 15000 1 //Kill the target to get the stuff.
ADD_BLIP_FOR_COORD -1306.1910 2532.4802 86.7332 mission_blip
change_blip_colour mission_blip RED

LVAR_INT kill_player_flag
kill_player_flag = 0

LVAR_INT enemy_in_car created_pickup_flag
enemy_in_car = 0
created_pickup_flag = 0

LVAR_INT enemy_flag[10]
a = 0
WHILE a < 10
	enemy_flag[a] = 0
	++ a
ENDWHILE

mission_flag = 0

LVAR_INT printed_this_yet
printed_this_yet = 0

lvar_int player_in_area
player_in_area = 0

IF mission_flag = 1
	CREATE_PICKUP BRIEFCASE PICKUP_ONCE the_stuff_x the_stuff_y the_stuff_z the_stuff
ENDIF

SET_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE pedtype_mission1

if played_vcrash1_before = 1
	SET_UP_SKIP -1346.6138 2304.4824 93.5390 4.8156
endif

// ************************************* MISSION LOOP **************************************
mission_vcr1_loop:

WAIT 0
IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_vcr1_loop
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_vcr1_passed  
ENDIF

GET_GAME_TIMER game_timer

IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2511.3638 2388.9138 6.2421 38.6 26.4 3.4 0//UNDERGROUND CARPARK FULL LOCATE
or LOCATE_CHAR_ANY_MEANS_2D scplayer -1306.1910 2532.4802 200.0 200.0 0
	alter_wanted_level player1 0
endif

IF mission_flag = 0
	//IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
	//	SET_CHAR_COORDINATES scplayer -1249.7703 2550.8384 122.4406
	//ENDIF
	IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1306.1910 2532.4802 250.0 250.0 0
		e = 0
		IF NOT HAS_MODEL_LOADED	MAVERICK
			REQUEST_MODEL MAVERICK
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	MESA
			REQUEST_MODEL MESA
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	FBIRANCH
			REQUEST_MODEL FBIRANCH
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	SANCHEZ
			REQUEST_MODEL SANCHEZ
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	BUFFALO
			REQUEST_MODEL BUFFALO
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	AK47
			REQUEST_MODEL AK47
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	FBI
			REQUEST_MODEL FBI
		ELSE
			++ e
		ENDIF
		IF NOT HAS_MODEL_LOADED	WMYBU
			REQUEST_MODEL WMYBU
		ELSE
			++ e
		ENDIF
		
		IF NOT HAS_MODEL_LOADED	MICRO_UZI
			REQUEST_MODEL MICRO_UZI
		ELSE
			++ e
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 114
			REQUEST_CAR_RECORDING 114
		ELSE
			++ e
		ENDIF
		IF NOT HAS_CAR_RECORDING_BEEN_LOADED 109
			REQUEST_CAR_RECORDING 109
		ELSE
			++ e
		ENDIF
		
		IF e = 11
			played_vcrash1_before = 1
			LVAR_INT heli1 heli2
			CREATE_CAR MAVERICK -1325.5135 2559.8704 87.6329 heli1
			SET_CAR_HEADING heli1 90.0
			LOCK_CAR_DOORS heli1 CARLOCK_LOCKOUT_PLAYER_ONLY
			//FREEZE_CAR_POSITION heli1 TRUE
			SET_CAR_HEALTH heli1 50000
			SET_CAR_PROOFS heli1 TRUE TRUE TRUE TRUE TRUE
			START_PLAYBACK_RECORDED_CAR heli1 114
			SET_PLAYBACK_SPEED heli1 0.0
			SET_HELI_BLADES_FULL_SPEED heli1

			CREATE_CAR MAVERICK -1343.2197 2538.3367 87.0107 heli2
			SET_CAR_HEADING heli2 10.3040
			SET_CAR_PROOFS heli2 TRUE TRUE TRUE TRUE TRUE
			MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK

			LVAR_INT jeep1 jeep2
			CREATE_CAR MESA -1304.4161 2498.8213 87.0571 jeep1
			MARK_MODEL_AS_NO_LONGER_NEEDED MESA
			SET_CAR_HEADING jeep1 240.0877
			CREATE_CAR FBIRANCH -1291.9478 2497.7817 87.1105 jeep2
			MARK_MODEL_AS_NO_LONGER_NEEDED FBIRANCH
			SET_CAR_HEADING jeep2 107.4866

			LVAR_INT dirtbike1 dirtbike2
			CREATE_CAR SANCHEZ -1311.1300 2512.1687 86.7085 dirtbike1
			SET_CAR_HEADING dirtbike1 177.3181
			CREATE_CAR SANCHEZ -1308.1073 2542.5774 87.4072 dirtbike2
			MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
			SET_CAR_HEADING dirtbike2 179.8761

			LVAR_INT enemy[10]
			CREATE_CHAR PEDTYPE_MISSION1 WMYBU -1312.0085 2549.5996 86.7332 enemy[0]//-1306.1910 2532.4802 86.7332 enemy[0]//STANDING BY FIRE
			SET_CHAR_HEADING enemy[0] 259.3965
			GIVE_WEAPON_TO_CHAR enemy[0] WEAPONTYPE_MICRO_UZI 9999 
			//TASK_TOGGLE_PED_THREAT_SCANNER enemy[0] FALSE FALSE TRUE
			//TASK_TOGGLE_PED_THREAT_SCANNER CharID Everywhere InCar DuringScriptTask
			//SET_SENSE_RANGE enemy[0] 50.0
			////SET_HEARING_RANGE enemy[0] 50.0
			SET_INFORM_RESPECTED_FRIENDS enemy[0] 30.0 5
			REMOVE_BLIP	mission_blip
			ADD_BLIP_FOR_CHAR enemy[0] mission_blip
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER	enemy[0] TRUE
			SET_CHAR_HEALTH enemy[0] 200
			LVAR_INT geez_decisions
			COPY_CHAR_DECISION_MAKER -1 geez_decisions
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE geez_decisions EVENT_GUN_AIMED_AT
			SET_CHAR_DECISION_MAKER enemy[0] geez_decisions
			LVAR_INT geez
			geez = 0

			CREATE_CHAR PEDTYPE_MISSION1 FBI -1302.7150 2534.3738 86.7332 enemy[1]//STANDING BY FIRE  
			SET_CHAR_HEADING enemy[1] 183.0002 
			GIVE_WEAPON_TO_CHAR enemy[1] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[1] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[1] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[1] 70.0
			SET_CHAR_KEEP_TASK enemy[1] true
			//SET_HEARING_RANGE enemy[1] 50.0

			CREATE_CHAR PEDTYPE_MISSION1 FBI -1325.8683 2525.3525 89.2044 enemy[2]//STANDING ON ROOF
			SET_CHAR_HEADING enemy[2] 226.1060
			GIVE_WEAPON_TO_CHAR enemy[2] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[2] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[2] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[2] 70.0
			SET_CHAR_KEEP_TASK enemy[2] true
			//SET_HEARING_RANGE enemy[2] 50.0

			CREATE_CHAR PEDTYPE_MISSION1 FBI -1303.2039 2530.1968 86.6889 enemy[3]//STANDING BY FIRE
			SET_CHAR_HEADING enemy[3] 4.8042
			GIVE_WEAPON_TO_CHAR enemy[3] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[3] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[3] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[3] 70.0
			SET_CHAR_KEEP_TASK enemy[3] true
			//SET_HEARING_RANGE enemy[3] 50.0

			CREATE_CHAR PEDTYPE_MISSION1 FBI -1315.7826 2502.1667 88.8673 enemy[4]//ON ROOF WITH ASSAULT RIFLE
			SET_CHAR_HEADING enemy[4]	242.7848
			GIVE_WEAPON_TO_CHAR enemy[4] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[4] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[4] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[4] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[4] 70.0
			SET_CHAR_KEEP_TASK enemy[4] true
			//SET_HEARING_RANGE enemy[4] 50.0

			//PATROL ROUTE 1
			CREATE_CHAR	PEDTYPE_MISSION1 FBI -1299.5397 2555.9194 86.5877 enemy[5]//WALKING A LOOPING ROUTE
			GIVE_WEAPON_TO_CHAR enemy[5] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[5] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[5] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[5] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[5] 70.0
			SET_CHAR_KEEP_TASK enemy[5] true
			//SET_HEARING_RANGE enemy[5] 50.0
			FLUSH_ROUTE
			EXTEND_ROUTE -1299.5397 2555.9194 86.5877
			EXTEND_ROUTE -1310.9596 2556.2319 86.7332 
			EXTEND_ROUTE -1311.9681 2538.9626 86.7332 
			EXTEND_ROUTE -1321.3568 2531.0759 86.6104
			EXTEND_ROUTE -1321.0430 2522.4998 86.2850 
			EXTEND_ROUTE -1314.5389 2513.3638 86.0349 
			EXTEND_ROUTE -1309.2075 2508.2400 86.0349 
			EXTEND_ROUTE -1296.6858 2524.0125 86.4456 
//			EXTEND_ROUTE -1295.6384 2546.6731 86.3820 
			TASK_FOLLOW_POINT_ROUTE enemy[5] PEDMOVE_WALK FOLLOW_ROUTE_LOOP

			//PATROL ROUTE 2
			CREATE_CHAR	PEDTYPE_MISSION1 FBI -1328.9739 2533.5286 86.4090 enemy[6]//WALKING A SMALL LOOPING ROUTE
			GIVE_WEAPON_TO_CHAR enemy[6] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[6] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[6] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[6] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[6] 70.0
			SET_CHAR_KEEP_TASK enemy[6] true
			//SET_HEARING_RANGE enemy[6] 50.0
			FLUSH_ROUTE
			EXTEND_ROUTE -1328.9739 2533.5286 86.4090 
			EXTEND_ROUTE -1329.2932 2521.8799 86.0349 
			EXTEND_ROUTE -1309.1946 2521.4177 86.3435
			EXTEND_ROUTE -1308.7552 2535.4458 86.7332 
			TASK_FOLLOW_POINT_ROUTE enemy[6] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			 
			//PATROL ROUTE 3 - BACKFORTH
			CREATE_CHAR	PEDTYPE_MISSION1 FBI -1288.0240 2516.4307 86.1473 enemy[7]//WALKING A BACKFORWARD ROUTE
			GIVE_WEAPON_TO_CHAR enemy[7] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[7] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[7] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[7] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[7] 70.0
			SET_CHAR_KEEP_TASK enemy[7] true
			//SET_HEARING_RANGE enemy[7] 50.0
			FLUSH_ROUTE
			EXTEND_ROUTE -1288.0240 2516.4307 86.1473
			EXTEND_ROUTE -1287.4299 2508.7686 86.0176 
			EXTEND_ROUTE -1295.2076 2506.5483 85.9726 
			EXTEND_ROUTE -1310.5842 2517.5430 86.1910
			EXTEND_ROUTE -1311.3357 2524.4321 86.4621
			EXTEND_ROUTE -1305.5496 2521.8127 86.3590 
			EXTEND_ROUTE -1300.1976 2521.1826 86.3342 
			EXTEND_ROUTE -1296.4155 2525.1853 86.4917 
//			EXTEND_ROUTE -1296.2900 2533.6702 86.7332
//			EXTEND_ROUTE -1307.6914 2535.3662 86.7332
//			EXTEND_ROUTE -1307.3020 2523.4485 86.4234 
//			EXTEND_ROUTE -1307.7834 2508.2190 86.0349 
//			EXTEND_ROUTE -1313.5420 2499.3018 86.0377 
//			EXTEND_ROUTE -1324.2356 2496.5830 86.0377
//			EXTEND_ROUTE -1326.3759 2507.1326 86.0377 
			TASK_FOLLOW_POINT_ROUTE enemy[7] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
			 
			//2 DUDES CHATTING BY RANCHER
			CREATE_CHAR PEDTYPE_MISSION1 FBI -1294.9702 2499.6191 85.9347 enemy[8]
			SET_CHAR_HEADING enemy[8] 102.9169 
			GIVE_WEAPON_TO_CHAR enemy[8] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[8] FALSE FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[8] 30.0 8
			SET_CHAR_RELATIONSHIP enemy[8] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[8] 70.0
			SET_CHAR_KEEP_TASK enemy[8] true
			//SET_HEARING_RANGE enemy[8] 50.0

			CREATE_CHAR PEDTYPE_MISSION1 FBI -1296.5479 2499.4358 85.9413 enemy[9]
			SET_CHAR_HEADING enemy[9] 281.0570
			GIVE_WEAPON_TO_CHAR enemy[9] WEAPONTYPE_AK47 9999 
			TASK_TOGGLE_PED_THREAT_SCANNER enemy[9] FALSE FALSE TRUE
			TASK_CHAT_WITH_CHAR enemy[8] enemy[9] TRUE TRUE
			TASK_CHAT_WITH_CHAR enemy[9] enemy[8] FALSE TRUE
			SET_INFORM_RESPECTED_FRIENDS enemy[9] 30.0 12
			SET_CHAR_RELATIONSHIP enemy[9] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_SENSE_RANGE enemy[9] 70.0
			SET_CHAR_KEEP_TASK enemy[9] true
			//SET_HEARING_RANGE enemy[9] 50.0

			LVAR_INT parked_car
			CREATE_CAR BUFFALO 2077.7607 2420.9014 48.5234 parked_car
			SET_CAR_HEADING parked_car 90.0
			START_PLAYBACK_RECORDED_CAR parked_car 109
			SET_PLAYBACK_SPEED parked_car 0.0
			SET_CAR_PROOFS parked_car TRUE TRUE TRUE TRUE TRUE
			SET_CAR_HEALTH parked_car 50000
			//FREEZE_CAR_POSITION parked_car TRUE
			//FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION parked_car TRUE
			LOCK_CAR_DOORS parked_car CARLOCK_LOCKOUT_PLAYER_ONLY

			LVAR_INT parked_car2
			CREATE_CAR BUFFALO 2077.7607 2416.9014 48.5234 parked_car2
			SET_CAR_HEADING parked_car2 90.0
			SET_CAR_HEALTH parked_car2 1750
			//FREEZE_CAR_POSITION parked_car2 TRUE
			//FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION parked_car2 TRUE
			LOCK_CAR_DOORS parked_car2 CARLOCK_LOCKOUT_PLAYER_ONLY

			LVAR_INT run_to_heli_from_campfire
			OPEN_SEQUENCE_TASK run_to_heli_from_campfire
			//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1308.6035 2535.9551 86.7332 PEDMOVE_SPRINT 1.0 0.5 scplayer
			//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1311.4175 2540.1951 86.7332 PEDMOVE_SPRINT 1.0 0.5 scplayer
			//TASK_GO_TO_COORD_WHILE_SHOOTING -1 -1312.0085 2549.5996 86.7332 PEDMOVE_SPRINT 1.0 0.5 scplayer
			TASK_GO_STRAIGHT_TO_COORD -1 -1324.9021 2554.6538 87.8548 PEDMOVE_SPRINT -2
			//TASK_FOLLOW_PATH_NODES_TO_COORD_SHOOTING -1 -1323.1914 2556.3059 88.332 PEDMOVE_SPRINT -2 scplayer
			TASK_ENTER_CAR_AS_DRIVER -1 heli1 20000
			CLOSE_SEQUENCE_TASK run_to_heli_from_campfire

			LVAR_INT run_to_car_from_helipad
			OPEN_SEQUENCE_TASK run_to_car_from_helipad
			TASK_LEAVE_ANY_CAR -1
			//2072.9229 2438.2524 48.5157 - routefind to carpark (replace gotos with...TASK_FOLLOW_PATH_NODES_TO_COORD_WHILE_SHOOTING)
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2112.6072 2422.8733 59.8223 PEDMOVE_SPRINT 1.0 0.5 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2097.2878 2436.6177 59.8223 PEDMOVE_SPRINT 1.0 0.5 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2096.7571 2440.3943 59.8151 PEDMOVE_SPRINT 1.0 0.5 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2074.0361 2440.0984 48.5176 PEDMOVE_SPRINT 1.0 0.5 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2077.9998 2427.0044 48.5244 PEDMOVE_SPRINT 1.0 0.5 scplayer
			//TASK_FOLLOW_PATH_NODES_TO_COORD_SHOOTING -1 2077.9998 2427.0044 48.5244 PEDMOVE_SPRINT -2 scplayer
			TASK_ENTER_CAR_AS_DRIVER -1 parked_car 20000
			CLOSE_SEQUENCE_TASK run_to_car_from_helipad

			++ mission_flag
		ENDIF
	ENDIF
ENDIF

IF printed_this_yet = 0
	if enemy_flag[geez] = 2
		PRINT_NOW VCR1__7 8000 1//The target is escaping in a helicopter.
		SWITCH_CAR_GENERATOR gen_car_flying[7] 0
		mission_timer = game_timer + 8000
		++ printed_this_yet
	ENDIF
ENDIF
IF printed_this_yet = 1
	if created_pickup_flag < 1
		if mission_timer < game_timer
			IF NOT IS_CAR_DEAD heli2
				IF NOT IS_CHAR_IN_ANY_HELI scplayer
					PRINT_NOW VCR1__9 6000 1//Follow him using the other helicopter.
					ADD_BLIP_FOR_CAR heli2 mission_blip2
					SET_BLIP_AS_FRIENDLY mission_blip2 TRUE
					CHANGE_BLIP_DISPLAY	mission_blip MARKER_ONLY
				ENDIF
			ENDIF
			mission_timer = game_timer + 6000
			++ printed_this_yet
		ENDIF
	endif
ENDIF
IF printed_this_yet = 2
	if mission_timer < game_timer
		IF IS_CHAR_IN_ANY_HELI scplayer
			PRINT_NOW VCR1_10 5000 1//Don't let him get away!
			if not is_car_dead heli1
				SET_CAR_PROOFS heli1 false false false false false
			endif
			if not is_car_dead heli2
				SET_CAR_PROOFS heli2 false false false false false
			endif
			CHANGE_BLIP_DISPLAY	mission_blip BOTH
			REMOVE_BLIP mission_blip2
			++ printed_this_yet
		ENDIF
		if is_char_dead	enemy[geez]
			remove_blip	mission_blip2
			++ printed_this_yet
		endif
	ENDIF
ENDIF
						
IF mission_flag > 0
	a = 0
	WHILE a < 10
		IF a = geez
			IF NOT IS_CHAR_DEAD	enemy[a]
				IF enemy_flag[geez] = 0
					IF kill_player_flag > 0
						PERFORM_SEQUENCE_TASK enemy[geez] run_to_heli_from_campfire
						SET_AREA51_SAM_SITE off
						++ enemy_flag[geez]
					ENDIF
				ENDIF
				IF NOT IS_CAR_DEAD heli1
					IF enemy_flag[geez] = 1
						IF IS_CHAR_IN_CAR enemy[geez] heli1
							//FREEZE_CAR_POSITION heli1 FALSE
							SET_CAR_HEALTH heli1 1750
							SET_CAR_PROOFS heli1 FALSE FALSE FALSE FALSE FALSE
							//START_PLAYBACK_RECORDED_CAR heli1 114
							++ enemy_flag[geez]
						ENDIF
					ENDIF
					IF enemy_flag[geez] = 2
						
						//MAKE HELI GO FAST IF THE PLAYER IS CLOSE
						GET_CAR_COORDINATES heli1 x y z
						GET_CHAR_COORDINATES scplayer x2 y2 z2
						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 distance
						LVAR_FLOAT heli_speed
						heli_speed = 7000.0 / distance//INCREASE NUMBER TO MAKE CAR GO FASTER
						heli_speed /= 100.0
						IF heli_speed > 2.0
							heli_speed = 2.0
						ENDIF
						IF heli_speed < 0.30
							heli_speed = 0.30
						ENDIF
						SET_PLAYBACK_SPEED heli1 heli_speed
										
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR heli1
							//FREEZE_CAR_POSITION parked_car2 FALSE
							IF NOT IS_CAR_DEAD parked_car2
								LOCK_CAR_DOORS parked_car2 CARLOCK_UNLOCKED
							ENDIF
							PERFORM_SEQUENCE_TASK enemy[geez] run_to_car_from_helipad
							PRINT_NOW VCR1__8 5000 1//He's abandoned the helicopter.
							remove_blip mission_blip2
							CHANGE_BLIP_DISPLAY	mission_blip BOTH
							SWITCH_ROADS_OFF 2475.4282 2345.0068 2.7255 2517.4163 2398.8738 12.369
							++ enemy_flag[geez]
						ENDIF
					ENDIF
				ENDIF
				IF NOT IS_CAR_DEAD parked_car
					IF enemy_flag[geez] = 3
						IF IS_CHAR_IN_CAR enemy[geez] parked_car
							SET_CAR_HEALTH parked_car 1750
							SET_CAR_PROOFS parked_car FALSE FALSE FALSE FALSE FALSE
							//FREEZE_CAR_POSITION parked_car FALSE
							MARK_CAR_AS_NO_LONGER_NEEDED heli1
							MARK_CAR_AS_NO_LONGER_NEEDED heli2
							MARK_CAR_AS_NO_LONGER_NEEDED jeep1
							MARK_CAR_AS_NO_LONGER_NEEDED jeep2
							MARK_CAR_AS_NO_LONGER_NEEDED dirtbike1
							MARK_CAR_AS_NO_LONGER_NEEDED dirtbike2
							r = 0
							WHILE r < 10
								IF NOT r = geez
									MARK_CHAR_AS_NO_LONGER_NEEDED enemy[r]
								ENDIF
								++ r
							ENDWHILE
							//START_PLAYBACK_RECORDED_CAR parked_car 109
							++ enemy_flag[geez]
						ENDIF
					ENDIF
					IF enemy_flag[geez] = 4
						IF IS_PLAYBACK_GOING_ON_FOR_CAR parked_car
							//MAKE CAR GO FAST IF THE PLAYER IS CLOSE
							GET_CAR_COORDINATES parked_car x y z
							GET_CHAR_COORDINATES scplayer x2 y2 z2
							GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 distance
							heli_speed = 5000.0 / distance//INCREASE NUMBER TO MAKE CAR GO FASTER
							heli_speed /= 100.0
							IF heli_speed > 1.1
								heli_speed = 1.1
							ENDIF
							IF heli_speed < 0.60
								heli_speed = 0.60
							ENDIF
							SET_PLAYBACK_SPEED parked_car heli_speed
						ELSE
							TASK_CAR_DRIVE_TO_COORD enemy[geez] -1 2538.0671 2389.8188 3.2200 100.0 MODE_NORMAL TRUE DRIVINGMODE_AVOIDCARS
							SET_CAR_STRAIGHT_LINE_DISTANCE parked_car 1
							++ enemy_flag[geez]
						ENDIF
					ENDIF
				ENDIF
				IF enemy_flag[geez] = 5
					IF IS_CHAR_IN_ANY_CAR enemy[geez]
						LVAR_INT current_car
						STORE_CAR_CHAR_IS_IN_NO_SAVE enemy[geez] current_car
						SET_CAR_DRIVING_STYLE current_car 2
						SET_CAR_STRAIGHT_LINE_DISTANCE current_car 1
						
						//SLOW CAR IF FAR FROM PLAYER
						GET_CAR_COORDINATES current_car x y z
						GET_CHAR_COORDINATES scplayer x2 y2 z2
						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 distance
						//speed = distance + 3.0 //< THIS WILL SLOW THE CAR DOWN IF IT GETS CLOSER (FOR CONVOYS OR CAR GOING TOWARDS)
						speed = 3000.0 / distance //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
						if locate_char_in_car_3d enemy[geez] 2506.6499 2355.8572 10.8203 29.9399 15.5700 2.0 0
							speed = 20.0
						endif
						IF speed > 100.0
							speed = 100.0
						ENDIF
						IF speed < 15.0
							speed = 15.0
						ENDIF
						SET_CAR_CRUISE_SPEED current_car speed

						//ON FIRE CHECKS
						GET_CAR_HEALTH current_car car_health
						IF car_health < 251
							SET_CAR_TEMP_ACTION current_car TEMPACT_HANDBRAKETURNLEFT 5000
							GET_CHAR_COORDINATES enemy[geez] x y z
							TASK_FLEE_POINT enemy[geez] x y z 15.0 1500
							++ enemy_flag[geez]
						ENDIF
						//UPSIDEDOWN CHECKS
						IF IS_CAR_UPSIDEDOWN current_car
						AND IS_CAR_STOPPED current_car
							IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer current_car 90.0 90.0 0
								SET_UPSIDEDOWN_CAR_NOT_DAMAGED current_car FALSE
								GET_CHAR_COORDINATES enemy[geez] x y z
								TASK_FLEE_POINT enemy[geez] x y z 15.0 1500
								++ enemy_flag[geez]
							ELSE
								IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x y z 4.0 4.0 4.0
									IF NOT IS_CAR_ON_SCREEN current_car
										GET_CAR_COORDINATES current_car x y z
										GET_CLOSEST_CAR_NODE_WITH_HEADING x y z x y z heading
										IF NOT IS_POINT_ON_SCREEN x y z 4.0
											SET_CAR_COORDINATES current_car x y z
											SET_CAR_HEADING	current_car heading
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						//STUCK CHECKS
						LVAR_FLOAT stucky_x stucky_y stucky_z
						LVAR_INT stucky_timer
						IF LOCATE_CAR_3D current_car stucky_x stucky_y stucky_z 4.0 4.0 4.0 0
							IF stucky_timer < game_timer
								IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer current_car 90.0 90.0 0
									SET_UPSIDEDOWN_CAR_NOT_DAMAGED current_car FALSE
									GET_CHAR_COORDINATES enemy[geez] x y z
									TASK_FLEE_POINT enemy[geez] x y z 15.0 500
									++ enemy_flag[geez]
								ELSE
									IF NOT IS_CAR_ON_SCREEN current_car
										GET_CAR_COORDINATES current_car x y z
										GET_CLOSEST_CAR_NODE_WITH_HEADING x y z x y z heading
										IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x y z 4.0 4.0 4.0
											IF NOT IS_POINT_ON_SCREEN x y z 4.0
												SET_CAR_COORDINATES current_car x y z
												SET_CAR_HEADING current_car heading
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ELSE
							GET_CAR_COORDINATES current_car stucky_x stucky_y stucky_z
							stucky_timer = game_timer + 5000
						ENDIF

						IF LOCATE_CHAR_IN_CAR_3D enemy[geez] 2538.0671 2389.8188 3.2200 5.0 5.0 2.0 0
							enemy_flag[geez] = 7
						ENDIF
					ELSE
						++ enemy_flag[geez]
					ENDIF
				ENDIF
				IF enemy_flag[geez] = 6
					GET_SCRIPT_TASK_STATUS enemy[geez] TASK_FLEE_POINT task_status
					LVAR_INT task_status2
					GET_SCRIPT_TASK_STATUS enemy[geez] TASK_CAR_DRIVE_TO_COORD task_status2
					IF task_status = FINISHED_TASK
					OR IS_CHAR_IN_ANY_CAR enemy[geez]
						IF task_status2 = FINISHED_TASK
							TASK_CAR_DRIVE_TO_COORD enemy[geez] -1 2538.0671 2389.8188 3.2200 100.0 MODE_NORMAL TRUE DRIVINGMODE_AVOIDCARS
							-- enemy_flag[geez]
						ENDIF
					ENDIF
				ENDIF

				IF IS_CHAR_IN_ANY_CAR enemy[geez]
					IF enemy_in_car = 0
						enemy_in_car = 1
					ENDIF
				ELSE
					IF enemy_in_car = 1
						enemy_in_car = 0
					ENDIF
				ENDIF
				if enemy_flag[geez] = 7
					get_script_task_status enemy[geez] task_kill_char_on_foot task_status
					if task_status = finished_task
						task_kill_char_on_foot enemy[geez] scplayer
					endif
				endif
			ELSE
				IF enemy_flag[geez] = 2
					IF NOT IS_CAR_DEAD heli1
						IF IS_PLAYBACK_GOING_ON_FOR_CAR	heli1
							STOP_PLAYBACK_RECORDED_CAR heli1
						ENDIF
						MAKE_HELI_COME_CRASHING_DOWN heli1
						enemy_flag[geez] = 90
					ENDIF
				ENDIF

				IF DOES_CHAR_EXIST enemy[geez]
					IF NOT IS_CHAR_IN_WATER	enemy[geez]
						IF created_pickup_flag = 0
							REMOVE_BLIP mission_blip
							LVAR_FLOAT the_stuff_x the_stuff_y the_stuff_z
							GET_DEAD_CHAR_PICKUP_COORDS enemy[geez] the_stuff_x the_stuff_y the_stuff_z
							LVAR_INT the_stuff
							CREATE_PICKUP BRIEFCASE PICKUP_ONCE the_stuff_x the_stuff_y the_stuff_z the_stuff
							ADD_BLIP_FOR_PICKUP the_stuff mission_blip
							PRINT_NOW VCR1__3 5000 1//He is dead and has dropped the stuff, get to it before anyone else.
							enemy_in_car = 2
							created_pickup_flag = 1
						ENDIF
					ELSE
						PRINT_NOW VCR1__4 5000 1//"~r~The stuff is at the bottom of the sea!"
						GOTO mission_vcr1_failed
					ENDIF
				ELSE
					PRINT_NOW VCR1__5 5000 1//"You destroyed the stuff in the explosion!"
					GOTO mission_vcr1_failed
				ENDIF
				
				IF kill_player_flag = 0
					kill_player_flag = 1
				ENDIF
			ENDIF
		ELSE
			//THIS IS THE BIT FOR ALL OTHER PEDS
			IF NOT IS_CHAR_DEAD	enemy[a]
				IF enemy_flag[geez] < 3
					IF enemy_flag[a] = 0
						IF player_in_area = 1
							GET_SCRIPT_TASK_STATUS enemy[a]	TASK_KILL_CHAR_ON_FOOT task_status
							IF task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT enemy[a] scplayer
							ENDIF
						ENDIF
						IF enemy_flag[a] < 2
							IF created_pickup_flag = 1
								IF LOCATE_CHAR_ON_FOOT_2D enemy[a] the_stuff_x the_stuff_y 65.0	65.0 0
									GIVE_WEAPON_TO_CHAR enemy[a] WEAPONTYPE_MICRO_UZI 9999
									SET_CURRENT_CHAR_WEAPON enemy[a] WEAPONTYPE_MICRO_UZI
									TASK_GO_TO_COORD_WHILE_SHOOTING enemy[a] the_stuff_x the_stuff_y the_stuff_z PEDMOVE_RUN 1.0 0.5 scplayer
									++ enemy_flag[a]
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF enemy_flag[a] = 1
						IF created_pickup_flag = 1
							GET_SCRIPT_TASK_STATUS enemy[a]	TASK_GO_TO_COORD_WHILE_SHOOTING task_status
							IF task_status = FINISHED_TASK
								IF LOCATE_CHAR_ON_FOOT_2D enemy[a] the_stuff_x the_stuff_y 2.0 2.0 0
									MARK_CHAR_AS_NO_LONGER_NEEDED enemy[geez]
									enemy[geez] = -1
									geez = a
									REMOVE_BLIP mission_blip
									REMOVE_PICKUP the_stuff
									ADD_BLIP_FOR_CHAR enemy[geez] mission_blip
									SET_CHAR_ONLY_DAMAGED_BY_PLAYER	enemy[geez] TRUE
									IF NOT IS_PLAYER_TARGETTING_CHAR player1 enemy[geez]
										SET_CHAR_HEALTH enemy[geez] 200
									ENDIF
									enemy_flag[geez] = 0
									PRINT_NOW VCR1__2 5000 1//You were too slow. One of the guards has grabbed the stuff.
									created_pickup_flag = 0
								ENDIF
							ENDIF
						ELSE
							-- enemy_flag[a]
						ENDIF
					ENDIF
				ENDIF
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED enemy[a]
				IF kill_player_flag = 0
					//SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
					CLEAR_SKIP
					kill_player_flag = 1
				ENDIF
			ENDIF
		ENDIF
		IF kill_player_flag = 0
			IF IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_ACQUAINTANCE_PED_HATE
			or IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_POTENTIAL_GET_RUN_OVER
			or IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_SHOT_FIRED
			or IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_DEAD_PED
			or IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_SHOT_FIRED_WHIZZED_BY
			or IS_CHAR_RESPONDING_TO_EVENT enemy[a] EVENT_VEHICLE_THREAT
			//IF HAS_CHAR_SPOTTED_CHAR enemy[a] scplayer
				CLEAR_SKIP
				kill_player_flag = 1
			ENDIF
		ENDIF
		++ a
	ENDWHILE
ENDIF

if player_in_area = 0
	if locate_char_any_means_3d scplayer -1308.9282 2527.4656 92.8160 26.8500 38.6399 8.9000 0
		CLEAR_SKIP
		++ player_in_area
	endif
endif

IF kill_player_flag = 1
	PRINT_NOW VCR1_11 5000 1//They have spotted you! The target will try to escape, don't let him get away.
	CLEAR_SKIP
	++ kill_player_flag
ENDIF

IF mission_flag = 1
	IF enemy_flag[geez] > 2
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2511.3638 2388.9138 6.2421 38.6 26.4 3.4 0//UNDERGROUND CARPARK FULL LOCATE
			LVAR_INT finalcar1 finalcar2 finalcar3
			CREATE_CAR BUFFALO 2522.2114 2383.6292 4.0162 finalcar1
			SET_CAR_HEADING finalcar1 339.2576 
			CREATE_CAR BUFFALO 2518.5107 2393.8206 4.0104 finalcar2
			SET_CAR_HEADING finalcar2 168.2849 
			CREATE_CAR BUFFALO 2510.3362 2380.5833 4.0139 finalcar3
			SET_CAR_HEADING finalcar3 110.7153 
			
			LVAR_FLOAT checkpoints_x[10] checkpoints_y[10]
			checkpoints_x[0] = 	2524.2214
			checkpoints_y[0] = 	2385.0073

			checkpoints_x[1] = 	2520.0532
			checkpoints_y[1] = 	2392.2817

			checkpoints_x[2] = 	2513.9375
			checkpoints_y[2] = 	2381.3342

			checkpoints_x[3] = 	2513.9097
			checkpoints_y[3] = 	2385.4709

			checkpoints_x[4] = 	2511.9897
			checkpoints_y[4] = 	2392.3469

			checkpoints_x[5] = 	2519.3079
			checkpoints_y[5] = 	2399.5352

			checkpoints_x[6] = 	2530.1687
			checkpoints_y[6] = 	2388.5193

			checkpoints_x[7] = 	2504.1436
			checkpoints_y[7] = 	2382.4683

			checkpoints_x[8] = 	2504.8323
			checkpoints_y[8] = 	2397.6157

			checkpoints_x[9] = 	2506.8323
			checkpoints_y[9] = 	2397.6157

			c = 0
			WHILE c < 10
				IF NOT c = geez
					CREATE_CHAR PEDTYPE_MISSION1 FBI checkpoints_x[c] checkpoints_y[c] 3.2262 enemy[c]
					SET_CHAR_HEADING enemy[c] 91.4967
					GIVE_WEAPON_TO_CHAR enemy[c] WEAPONTYPE_micro_uzi 9999
					//SET_CHAR_STAY_IN_SAME_PLACE	enemy[c] TRUE
					//TASK_KILL_CHAR_ON_FOOT enemy[c] scplayer
					SET_CHAR_KEEP_TASK enemy[c] true
					//SET_INFORM_RESPECTED_FRIENDS enemy[c] 50.0 8
					//SET_CHAR_RELATIONSHIP enemy[c] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				ENDIF
				++ c
			ENDWHILE

			mission_timer = game_timer + 3000
			++ mission_flag
		ENDIF
	ENDIF
ENDIF

if mission_flag = 2
	if mission_timer < game_timer
		c = 0
		WHILE c < 10
			if not is_char_dead	enemy[c]
				SET_INFORM_RESPECTED_FRIENDS enemy[c] 50.0 8
				SET_CHAR_RELATIONSHIP enemy[c] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			ENDIF
			++ c
		ENDWHILE
		mission_timer = game_timer + 3000
		++ mission_flag
	endif
endif

if mission_flag = 3
	if mission_timer < game_timer
		c = 0
		WHILE c < 10
			if not is_char_dead	enemy[c]
				TASK_KILL_CHAR_ON_FOOT enemy[c] scplayer
			ENDIF
			++ c
		ENDWHILE
		++ mission_flag
	endif
endif

IF created_pickup_flag = 1
	IF HAS_PICKUP_BEEN_COLLECTED the_stuff
		REMOVE_BLIP	mission_blip
		//ADD_BLIP_FOR_COORD 2036.4814 2729.7861 9.8281 mission_blip
		PRINT_NOW VCR1__6 5000 1//Congratulations, you have the stuff.
		GOTO mission_vcr1_passed
	ENDIF
ENDIF
//IF created_pickup_flag = 2
//	IF LOCATE_CHAR_ON_FOOT_3D scplayer 2036.4814 2729.7861 9.8281 2.0 2.0 2.0 1
//		GOTO mission_vcr1_passed
//	ENDIF
//ENDIF

GOTO mission_vcr1_loop


	
// ************************************ MISSION FAILED *************************************
mission_vcr1_failed:
PRINT_BIG M_FAIL 5000 1
IF NOT IS_CHAR_DEAD	enemy[geez]
	IF NOT IS_CAR_DEAD heli1
		IF IS_CHAR_IN_CAR enemy[geez] heli1
			HELI_GOTO_COORDS heli1 0.0 0.0 0.0 20.0 40.0
		ENDIF
	ENDIF
ENDIF
RETURN

   

// ************************************ MISSION PASSED *************************************
mission_vcr1_passed:

flag_vcrash_mission_counter ++
PRINT_BIG M_PASSD 5000 1 //"Mission Passed!"
ADD_SCORE player1 0
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED VCRASH1
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT vcr1_mission_loop
REMOVE_BLIP vcrash_contact_blip
RETURN
		


// *********************************** MISSION CLEANUP *************************************
mission_vcr1_cleanup:

REMOVE_BLIP mission_blip
REMOVE_BLIP mission_blip2

SET_AREA51_SAM_SITE on

remove_pickup the_stuff

if not is_car_dead heli2
	SET_CAR_PROOFS heli2 FALSE FALSE FALSE FALSE FALSE
endif

SWITCH_ROADS_ON 2475.4282 2345.0068 2.7255 2517.4163 2398.8738 12.369

IF d5_pilot_licence_obtained = 1
	SWITCH_CAR_GENERATOR gen_car_flying[7] 101
ENDIF

MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
MARK_MODEL_AS_NO_LONGER_NEEDED MESA
MARK_MODEL_AS_NO_LONGER_NEEDED FBIRANCH
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED BUFFALO
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED FBI
MARK_MODEL_AS_NO_LONGER_NEEDED WMYBU
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI

if is_player_playing player1
	CLEAR_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
endif

GET_GAME_TIMER timer_mobile_start

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN

}