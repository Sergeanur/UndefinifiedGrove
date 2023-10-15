MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************** DRIVER 2 *****************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME driv2

// Mission start stuff

GOSUB mission_start_driv2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_driv2_failed
ENDIF

GOSUB mission_cleanup_driv2

MISSION_END

{
 
// Variables for mission

LVAR_INT d2_ambushed_van d2_player_bike d2_last_player_car_before_meet
LVAR_INT d2_num_of_packages d2_packages[4] //d2_num_packages_collected // global (temp)
LVAR_FLOAT d2_package_x[4] d2_package_y[4] d2_package_z[4]
LVAR_INT d2_gang_bikers[4]
LVAR_INT d2_gang_bikes[4]
LVAR_FLOAT d2_bike_speed[4]
LVAR_INT d2_bike_health[4] d2_last_bike_health[4] d2_damage_done

LVAR_FLOAT d2_city_centre_x d2_city_centre_y d2_city_centre_z
LVAR_FLOAT d2_bike_first_pt_x[4] d2_bike_first_pt_y[4] d2_bike_first_pt_z[4]
LVAR_FLOAT d2_bike_dest_x d2_bike_dest_y d2_bike_dest_z

LVAR_FLOAT d2_player_dest_x d2_player_dest_y d2_player_dest_z

LVAR_FLOAT d2_player_x d2_player_y d2_player_z d2_dist_from_player_to_bike
LVAR_FLOAT d2_bike_x d2_bike_y d2_bike_z
LVAR_FLOAT d2_player_heading

LVAR_FLOAT d2_player_offset_x1 d2_player_offset_y1 d2_player_offset_x2 d2_player_offset_y2 d2_dummy_z

LVAR_INT d2_task_status d2_event_type

LVAR_INT d2_biker_task_status[4] d2_biker_relationship_set[4]

LVAR_INT d2_index d2_package_at_dest_index

LVAR_INT d2_current_area_visible

// flags

LVAR_INT d2_gang_created
LVAR_INT d2_package_collected[4]
//LVAR_INT d2_player_bike_blip_removed
LVAR_INT /*d2_player_picked_up_bike*/ d2_player_reached_deal_location
LVAR_INT d2_player_sniped_at_biker d2_projectile_reached_area
LVAR_INT d2_cur_bike_state[4] d2_not_started_chase d2_wandering_randomly d2_going_to_city_centre d2_going_to_first_point d2_going_to_destination
LVAR_INT d2_getting_back_on_bike[4] d2_biker_picking_up_dropped_package[4]
LVAR_INT d2_biker_attacking_player[4] d2_biker_shooting_at_player[4]
LVAR_INT d2_bikes_going_to_destination d2_any_package_at_destination
LVAR_INT d2_any_packages_in_water d2_any_packages_been_destroyed
LVAR_INT d2_package_attach_state[4] d2_not_attached d2_attached_to_bike d2_attached_to_char
LVAR_INT d2_all_packages_collected

LVAR_INT d2_leftshoulder1_pressed_last_frame d2_in_range_last_printed

LVAR_INT d2_cutscene_skipped d2_player_given_mission_brief
LVAR_INT d2_first_help_text_cleared d2_second_help_text_cleared d2_player_got_within_10m_of_snatchable_package

LVAR_INT d2_player_entered_any_car

LVAR_INT d2_package_immovable[4]

LVAR_INT d2_fake_creates

// blips

LVAR_INT d2_package_blips[4] /*d2_player_bike_blip*/ d2_ambushed_van_blip d2_player_dest_blip

// **************************************** Mission Start **********************************

mission_start_driv2:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT FARLIE2

flag_player_on_mission = 1

WAIT 0

// ************************************* Initialise variables *****************************

d2_num_of_packages = 4
d2_num_packages_collected = 0

d2_index = 0
WHILE d2_index < d2_num_of_packages
	d2_package_collected[d2_index] = 0
	d2_package_immovable[d2_index] = 0
	d2_cur_bike_state[d2_index] = 0
	d2_getting_back_on_bike[d2_index] = 0
	d2_biker_picking_up_dropped_package[d2_index] = 0
	d2_biker_attacking_player[d2_index] = 0
	d2_biker_shooting_at_player[d2_index] = 0
	d2_bike_health[d2_index] = 1000
	d2_last_bike_health[d2_index] = 1000
	d2_package_attach_state[d2_index] = 0
	d2_bike_speed[d2_index] = 20.0
	d2_biker_relationship_set[d2_index] = 0
	d2_index++
ENDWHILE

d2_bike_dest_x = -2730.14
d2_bike_dest_y = 84.24
d2_bike_dest_z = 3.04

d2_bike_first_pt_x[0] = -2600.53
d2_bike_first_pt_y[0] = 1337.91
d2_bike_first_pt_z[0] = 5.60
d2_bike_first_pt_x[1] = -2855.52
d2_bike_first_pt_y[1] = 719.95
d2_bike_first_pt_z[1] = 26.84
d2_bike_first_pt_x[2] = -1897.94
d2_bike_first_pt_y[2] = -576.43
d2_bike_first_pt_z[2] = 22.99
d2_bike_first_pt_x[3] = -2811.49
d2_bike_first_pt_y[3] = -326.14
d2_bike_first_pt_z[3] = 5.72

//d2_player_dest_x = -2622.34
//d2_player_dest_y = 1404.97
//d2_player_dest_z = 6.15
d2_player_dest_x = -2622.49
d2_player_dest_y = 1406.6
d2_player_dest_z = 6.15

d2_gang_created = 0

//d2_player_bike_blip_removed = 0
//d2_player_picked_up_bike = 0
d2_player_reached_deal_location = 0
d2_player_sniped_at_biker = 0
d2_projectile_reached_area = 0

// enum bike states
d2_not_started_chase = 0
d2_wandering_randomly = 1
d2_going_to_city_centre = 2
d2_going_to_first_point = 3
d2_going_to_destination = 4

// enum package states
d2_not_attached = 0
d2_attached_to_bike = 1
d2_attached_to_char = 2

d2_bikes_going_to_destination = 0
d2_any_package_at_destination = 0
d2_any_packages_in_water = 0
d2_any_packages_been_destroyed = 0

d2_all_packages_collected = 0

d2_city_centre_x = -2143.27
d2_city_centre_y = 918.38
d2_city_centre_z = 79.42

d2_leftshoulder1_pressed_last_frame = 0
d2_in_range_last_printed = 0

d2_first_help_text_cleared = 0
d2_second_help_text_cleared = 0
d2_player_got_within_10m_of_snatchable_package = 0

d2_player_entered_any_car = 0

d2_fake_creates = 0

// ***************************************START OF CUTSCENE********************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 3

LOAD_CUTSCENE FARL_2a
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

SET_PLAYER_CONTROL player1 OFF

SET_AREA_VISIBLE 0

// ****************************************END OF CUTSCENE*********************************

REQUEST_MODEL FCR900
REQUEST_MODEL BOXVILLE
REQUEST_MODEL kmb_packet
REQUEST_MODEL WMYCR
REQUEST_MODEL HMYCR
REQUEST_MODEL MICRO_UZI
WHILE NOT HAS_MODEL_LOADED FCR900
   OR NOT HAS_MODEL_LOADED BOXVILLE
   OR NOT HAS_MODEL_LOADED kmb_packet
   OR NOT HAS_MODEL_LOADED WMYCR
   OR NOT HAS_MODEL_LOADED HMYCR
   OR NOT HAS_MODEL_LOADED MICRO_UZI
	WAIT 0
ENDWHILE
REQUEST_ANIMATION BIKES
REQUEST_ANIMATION MISC
WHILE NOT HAS_ANIMATION_LOADED BIKES
   OR NOT HAS_ANIMATION_LOADED MISC
	WAIT 0
ENDWHILE
// create bike for player
//CREATE_CAR FCR900 -2619.81 1405.47 5.68 d2_player_bike
//SET_CAR_HEADING d2_player_bike 205.07
//ADD_BLIP_FOR_CAR d2_player_bike d2_player_bike_blip
//SET_BLIP_AS_FRIENDLY d2_player_bike_blip TRUE

LOAD_SCENE -2625.26 1407.32 5.62

SET_CHAR_COORDINATES scplayer -2625.26 1407.32 5.62
SET_CHAR_HEADING scplayer 224.0

CHANGE_GARAGE_TYPE Tbon GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

// fades the screen in 

SET_FADING_COLOUR 0 0 0

WAIT 500

DO_FADE 1500 FADE_IN

// CREATE statements to keep the compiler happy

IF d2_fake_creates = 1
	CREATE_CAR BOXVILLE 0.0 0.0 -100.0 d2_ambushed_van
	ADD_BLIP_FOR_CAR d2_ambushed_van d2_ambushed_van_blip
	d2_index = 0
	WHILE d2_index < d2_num_of_packages
		CREATE_OBJECT kmb_packet 0.0 0.0 -100.0 d2_packages[d2_index]
		ADD_BLIP_FOR_OBJECT d2_packages[d2_index] d2_package_blips[d2_index]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCR 0.0 0.0 -100.0 d2_gang_bikers[d2_index]
		CREATE_CAR FCR900 0.0 0.0 -100.0 d2_gang_bikes[d2_index]
		d2_index++
	ENDWHILE
	//CREATE_CAR FCR900 0.0 0.0 -100.0 d2_player_bike
	//ADD_BLIP_FOR_CAR d2_player_bike d2_player_bike_blip
ENDIF

//// stop bikes getting stuck on winding road
//SWITCH_ROADS_OFF -2130.87 903.39 45.48 -2013.14 959.18 80.03
// stop traffic jams at location of ambush
//SWITCH_ROADS_OFF -1859.24 349.43 13.18 -1792.44 418.63 21.34
SWITCH_ROADS_OFF -2014.82 181.4 -13.0 -1614.82 581.4 47.0

ADD_BLIP_FOR_COORD -1814.82 381.40 17.09 d2_ambushed_van_blip
SET_COORD_BLIP_APPEARANCE d2_ambushed_van_blip COORD_BLIP_APPEARANCE_FRIEND

PRINT_NOW ( DRV3_11 ) 10000 0

// Mission loop
driv2_loop:

WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_driv2_passed  
	ENDIF

	//IF d2_player_picked_up_bike = 1
	IF NOT d2_player_reached_deal_location = 1

		// get handle for last car player is in before reaching meet
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer d2_last_player_car_before_meet
			IF NOT d2_player_entered_any_car = 1
				d2_player_entered_any_car = 1
			ENDIF
		ENDIF

		IF NOT d2_gang_created = 1
		AND LOCATE_CHAR_ANY_MEANS_2D scplayer -1814.82 381.40 200.0 200.0 FALSE
			REMOVE_BLIP d2_ambushed_van_blip
			GOSUB d2_create_ambushed_van_and_gang
			d2_gang_created = 1
		ENDIF

		IF d2_gang_created = 1

			GET_AREA_VISIBLE d2_current_area_visible
			IF d2_current_area_visible = 0
				d2_index = 0
				WHILE d2_index < d2_num_of_packages
				AND NOT d2_player_sniped_at_biker = 1
					IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
						GET_CHAR_HIGHEST_PRIORITY_EVENT d2_gang_bikers[d2_index] d2_event_type
						IF d2_event_type = EVENT_SHOT_FIRED_WHIZZED_BY
							d2_player_sniped_at_biker = 1
						ENDIF
					ENDIF
					d2_index++
				ENDWHILE
				IF NOT d2_player_sniped_at_biker = 1
					IF NOT d2_projectile_reached_area = 1
					AND IS_PROJECTILE_IN_AREA -1824.82 371.40 12.09 -1804.82 391.40 22.09
						d2_projectile_reached_area = 1
						TIMERA = 0
					ENDIF
					IF d2_projectile_reached_area = 1
						d2_index = 0
						WHILE d2_index < d2_num_of_packages
							IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
								LVAR_FLOAT d2_xmin d2_xmax d2_ymin d2_ymax d2_zmin d2_zmax
								GET_CHAR_COORDINATES d2_gang_bikers[d2_index] player_x player_y player_z
								d2_xmin = player_x - 3.0
								d2_xmax = player_x + 3.0
								d2_ymin = player_y - 3.0
								d2_ymax = player_y + 3.0
								d2_zmin = player_z - 3.0
								d2_zmax = player_z + 3.0
								IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
								OR IS_EXPLOSION_IN_AREA EXPLOSION_MOLOTOV d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
								OR IS_EXPLOSION_IN_AREA EXPLOSION_ROCKET d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
								OR IS_EXPLOSION_IN_AREA EXPLOSION_ROCKET_WEAK d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
								OR IS_EXPLOSION_IN_AREA EXPLOSION_SMALL d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
								OR IS_EXPLOSION_IN_AREA EXPLOSION_TINY d2_xmin d2_ymin d2_zmin d2_xmax d2_ymax d2_zmax
									IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
										TASK_DIE d2_gang_bikers[0]
									ENDIF
									IF NOT IS_CHAR_DEAD d2_gang_bikers[1]
										TASK_DIE d2_gang_bikers[1]
									ENDIF
									IF NOT IS_CHAR_DEAD d2_gang_bikers[2]
										TASK_DIE d2_gang_bikers[2]
									ENDIF
									IF NOT IS_CHAR_DEAD d2_gang_bikers[3]
										TASK_DIE d2_gang_bikers[3]
									ENDIF
									IF NOT IS_CAR_DEAD d2_gang_bikes[0]
										EXPLODE_CAR d2_gang_bikes[0]
									ENDIF
									IF NOT IS_CAR_DEAD d2_gang_bikes[1]
										EXPLODE_CAR d2_gang_bikes[1]
									ENDIF
									IF NOT IS_CAR_DEAD d2_gang_bikes[2]
										EXPLODE_CAR d2_gang_bikes[2]
									ENDIF
									IF NOT IS_CAR_DEAD d2_gang_bikes[3]
										EXPLODE_CAR d2_gang_bikes[3]
									ENDIF
									DELETE_OBJECT d2_packages[0]
									DELETE_OBJECT d2_packages[1]
									DELETE_OBJECT d2_packages[2]
									DELETE_OBJECT d2_packages[3]
									PRINT_NOW ( DRV3_9 ) 5000 0
									GOTO mission_driv2_failed
								ENDIF
							ENDIF
							d2_index++
						ENDWHILE
						IF TIMERA > 2000
							d2_player_sniped_at_biker = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1814.82 381.40 50.0 50.0 FALSE
			OR d2_player_sniped_at_biker = 1

				REMOVE_BLIP d2_ambushed_van_blip
				GOSUB d2_play_cutscene_for_deal

				d2_player_reached_deal_location = 1

				TIMERA = 0
				PRINT_HELP_FOREVER DRV3_H  
				TIMERB = 0

			ENDIF

		ENDIF

	ENDIF

	IF d2_player_reached_deal_location = 1

		IF NOT d2_first_help_text_cleared = 1
			IF TIMERB > 12000
				CLEAR_HELP
				d2_first_help_text_cleared = 1
			ENDIF
		ENDIF
		// first time player gets near to package that can be snatched,
		// flash up help text again as a reminder
		IF NOT d2_player_got_within_10m_of_snatchable_package = 1
			IF IS_CHAR_ON_ANY_BIKE scplayer
			OR IS_CHAR_ON_FOOT scplayer 
				d2_index = 0
				WHILE d2_index < d2_num_of_packages
				AND NOT d2_player_got_within_10m_of_snatchable_package = 1
					IF DOES_OBJECT_EXIST d2_packages[d2_index]
					AND NOT d2_package_collected[d2_index] = 1
						IF IS_OBJECT_ATTACHED d2_packages[d2_index]
						OR IS_CHAR_HOLDING_OBJECT -1 d2_packages[d2_index]

							IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer d2_packages[d2_index] 10.0 10.0 FALSE
								PRINT_HELP_FOREVER DRV3_H  
								d2_player_got_within_10m_of_snatchable_package = 1
								IF NOT d2_first_help_text_cleared = 1
									d2_first_help_text_cleared = 1
								ENDIF
								TIMERB = 0
							ENDIF

						ENDIF
					ENDIF
					d2_index++
				ENDWHILE
			ENDIF
		ELSE
			IF NOT d2_second_help_text_cleared = 1
				IF TIMERB > 12000
					CLEAR_HELP
					d2_second_help_text_cleared = 1
				ENDIF
			ENDIF
		ENDIF

		GOSUB d2_package_collect_check
		GOSUB d2_package_dropped_check

		GOSUB d2_keep_packages_on_ground

		// if the bikes have reached their first destination, they wander randomly.
		// if they're doing this and get too far away from the city centre, send them back into town
		// and then set to wander randomly again
		GOSUB d2_keep_wandering_bikes_in_city_centre
		GOSUB d2_set_centre_bikes_wandering
		// bikes head toward second destination after five minutes
		IF NOT d2_bikes_going_to_destination = 1
		AND NOT d2_all_packages_collected = 1
			IF TIMERA > 280000
				GOSUB d2_send_bikes_to_destination
				d2_bikes_going_to_destination = 1
				IF d2_cur_bike_state[0] = d2_going_to_destination
				OR d2_cur_bike_state[1] = d2_going_to_destination
				OR d2_cur_bike_state[2] = d2_going_to_destination
				OR d2_cur_bike_state[3] = d2_going_to_destination
					LVAR_INT d2_packages_remaining
					d2_packages_remaining = d2_num_of_packages - d2_num_packages_collected
					IF d2_packages_remaining > 1
						PRINT_NOW ( DRV3_5 ) 10000 0
					ELSE
						PRINT_NOW ( DRV3_6 ) 10000 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		GOSUB d2_set_bikes_wandering_from_first_dest_check

		GOSUB d2_keep_bikers_on_bikes

		// speed up / slow down code for bikes
		GOSUB d2_new_bike_speed_check

		// bikers shoot player if near, chase player if far away
		GOSUB d2_update_biker_attack_state

		// if the bikers aren't doing anything (eg. they can't get to their bike),
		// they attack the player if close
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				GET_SCRIPT_TASK_STATUS d2_gang_bikers[d2_index] -1 d2_biker_task_status[d2_index]
				IF d2_biker_relationship_set[d2_index] = 0
				AND d2_biker_task_status[d2_index] = FINISHED_TASK
					SET_CHAR_RELATIONSHIP d2_gang_bikers[d2_index] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
					d2_biker_relationship_set[d2_index] = 1
				ENDIF
				IF d2_biker_relationship_set[d2_index] = 1
				AND NOT d2_biker_task_status[d2_index] = FINISHED_TASK
					CLEAR_ALL_CHAR_RELATIONSHIPS d2_gang_bikers[d2_index] ACQUAINTANCE_TYPE_PED_HATE
					CLEAR_ALL_CHAR_RELATIONSHIPS d2_gang_bikers[d2_index] ACQUAINTANCE_TYPE_PED_DISLIKE
					d2_biker_relationship_set[d2_index] = 0
				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

		IF d2_num_packages_collected = d2_num_of_packages
		AND NOT d2_all_packages_collected = 1
			ADD_BLIP_FOR_COORD d2_player_dest_x d2_player_dest_y d2_player_dest_z d2_player_dest_blip
			PRINT_NOW ( DRV3_10 ) 7000 0
			d2_all_packages_collected = 1
		ENDIF

		// mission pass/fail conditions
		IF d2_all_packages_collected = 1
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer d2_player_dest_x d2_player_dest_y d2_player_dest_z 4.0 4.0 2.0 TRUE
				GOSUB d2_play_mission_passed_cutscene
				GOTO mission_driv2_passed
			ENDIF
		ENDIF
		IF d2_bikes_going_to_destination = 1
			GOSUB d2_have_any_packages_reached_destination
			IF d2_any_package_at_destination = 1
				GOSUB check_player_is_safe
				IF player_is_completely_safe = 1
					GOSUB d2_play_mission_failed_cutscene
				ENDIF
				PRINT_NOW ( DRV3_7 ) 5000 0
				GOTO mission_driv2_failed
			ENDIF
		ENDIF
		GOSUB d2_are_any_packages_in_water
		IF d2_any_packages_in_water = 1
			PRINT_NOW ( DRV3_8 ) 5000 0
			GOTO mission_driv2_failed
		ENDIF
		GOSUB d2_have_any_packages_been_destroyed
		IF d2_any_packages_been_destroyed = 1
			PRINT_NOW ( DRV3_9 ) 5000 0
			GOTO mission_driv2_failed
		ENDIF
	ENDIF	

GOTO driv2_loop


// ********************************** Mission GOSUBS ************************************

// ************************************************************
// 						 Create gang setup
// ************************************************************

	d2_create_ambushed_van_and_gang:

		CLEAR_AREA -1814.82 381.40 17.09 100.0 TRUE
		
		SET_CAR_MODEL_COMPONENTS BOXVILLE 0 0
		CREATE_CAR BOXVILLE -1814.82 381.40 9.96 d2_ambushed_van
		SET_CAR_HEADING d2_ambushed_van 33.27
		POP_CAR_DOOR d2_ambushed_van REAR_LEFT_DOOR FALSE
		OPEN_CAR_DOOR d2_ambushed_van REAR_RIGHT_DOOR
		// make sure can't destroy ambushed van before the cutscene's played
		SET_CAR_PROOFS d2_ambushed_van TRUE TRUE TRUE TRUE TRUE
		ADD_BLIP_FOR_CAR d2_ambushed_van d2_ambushed_van_blip
		SET_BLIP_AS_FRIENDLY d2_ambushed_van_blip TRUE

		CREATE_CAR FCR900 -1814.86 376.0 15.68 d2_gang_bikes[0]
		SET_CAR_HEADING d2_gang_bikes[0] 26.65
		CREATE_CAR FCR900 -1817.44 380.33 15.68 d2_gang_bikes[1]
		SET_CAR_HEADING d2_gang_bikes[1] 23.76
		CREATE_CAR FCR900 -1818.09 377.08 15.68 d2_gang_bikes[2]
		SET_CAR_HEADING d2_gang_bikes[2] 32.17
		CREATE_CAR FCR900 -1816.66 374.73 15.68 d2_gang_bikes[3]
		SET_CAR_HEADING d2_gang_bikes[3] 30.21
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			SET_CAR_ONLY_DAMAGED_BY_PLAYER d2_gang_bikes[d2_index] TRUE
			SET_CAN_BURST_CAR_TYRES d2_gang_bikes[d2_index] FALSE
			FREEZE_CAR_POSITION d2_gang_bikes[d2_index] TRUE
			d2_index++
		ENDWHILE
		CREATE_CAR FCR900 -1819.01 386.28 16.58 d2_player_bike
		SET_CAR_HEADING d2_player_bike 49.56
		SET_CAR_ONLY_DAMAGED_BY_PLAYER d2_player_bike TRUE
		SET_CAN_BURST_CAR_TYRES d2_player_bike FALSE

		CREATE_CHAR PEDTYPE_MISSION1 WMYCR -1811.85 375.88 15.68 d2_gang_bikers[0]
		SET_CHAR_HEADING d2_gang_bikers[0] 27.32
		CREATE_CHAR_INSIDE_CAR d2_gang_bikes[1] PEDTYPE_MISSION1 WMYCR d2_gang_bikers[1]
		CREATE_CHAR_INSIDE_CAR d2_gang_bikes[2] PEDTYPE_MISSION1 HMYCR d2_gang_bikers[2]
		CREATE_CHAR_INSIDE_CAR d2_gang_bikes[3] PEDTYPE_MISSION1 HMYCR d2_gang_bikers[3]

 		LVAR_INT d2_gang_biker_decisions
		COPY_CHAR_DECISION_MAKER -1 d2_gang_biker_decisions
		ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE d2_gang_biker_decisions EVENT_DAMAGE TASK_SIMPLE_BE_DAMAGED 100.0 100.0 100.0 100.0 TRUE FALSE
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				SET_CHAR_DECISION_MAKER d2_gang_bikers[d2_index] d2_gang_biker_decisions
				GIVE_WEAPON_TO_CHAR d2_gang_bikers[d2_index] WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE d2_gang_bikers[d2_index] KNOCKOFFBIKE_ALWAYSNORMAL
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER d2_gang_bikers[d2_index] TRUE
				CLEAR_ALL_CHAR_RELATIONSHIPS d2_gang_bikers[d2_index] ACQUAINTANCE_TYPE_PED_HATE
				CLEAR_ALL_CHAR_RELATIONSHIPS d2_gang_bikers[d2_index] ACQUAINTANCE_TYPE_PED_DISLIKE
			ENDIF
			d2_index++
		ENDWHILE

		// make sure can't destroy bikes and can only snipe biker guys before the cutscene's played
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				IF d2_index = 0
					SET_CHAR_PROOFS d2_gang_bikers[d2_index] TRUE TRUE TRUE TRUE TRUE
					SET_CHAR_VISIBLE d2_gang_bikers[d2_index] FALSE
				ELSE
					SET_CHAR_PROOFS d2_gang_bikers[d2_index] FALSE TRUE TRUE TRUE TRUE
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
				SET_CAR_PROOFS d2_gang_bikes[d2_index] TRUE TRUE TRUE TRUE TRUE
			ENDIF
			d2_index++
		ENDWHILE
		SET_CAR_PROOFS d2_player_bike TRUE TRUE TRUE TRUE TRUE

		CREATE_OBJECT kmb_packet -1812.30 377.57 9.34 d2_packages[0]
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT d2_index = 0
				CREATE_OBJECT kmb_packet 0.0 0.0 -100.0 d2_packages[d2_index]
				ATTACH_OBJECT_TO_CAR d2_packages[d2_index] d2_gang_bikes[d2_index] 0.0 -0.9 0.5 0.0 0.0 0.0
				d2_package_attach_state[d2_index] = d2_attached_to_bike
			ELSE
				IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
					TASK_PICK_UP_OBJECT d2_gang_bikers[0] d2_packages[0] 0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE 
					d2_package_attach_state[0] = d2_attached_to_char
					SET_OBJECT_VISIBLE d2_packages[0] FALSE
				ENDIF
			ENDIF
			
			SET_OBJECT_COLLISION d2_packages[d2_index] TRUE
			SET_OBJECT_DYNAMIC d2_packages[d2_index] TRUE
			SET_OBJECT_PROOFS d2_packages[d2_index] TRUE TRUE TRUE TRUE TRUE 
			SET_OBJECT_COLLISION_DAMAGE_EFFECT d2_packages[d2_index] FALSE
			d2_index++
		ENDWHILE

	RETURN

// ************************************************************
// 						 Start bike chase
// ************************************************************

	d2_start_bike_chase:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND d2_cur_bike_state[d2_index] = d2_not_started_chase

					TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_first_pt_x[d2_index] d2_bike_first_pt_y[d2_index] d2_bike_first_pt_z[d2_index] d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					//TASK_CAR_DRIVE_WANDER d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_speed[d2_index] DRIVINGMODE_AVOIDCARS
					d2_cur_bike_state[d2_index] = d2_going_to_first_point

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE
		
	RETURN

// ************************************************************
// 			 Check whether player's collected a package
// ************************************************************

	d2_package_collect_check:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF DOES_OBJECT_EXIST d2_packages[d2_index]
			AND NOT d2_package_collected[d2_index] = 1

				IF IS_OBJECT_ATTACHED d2_packages[d2_index]
				OR IS_CHAR_HOLDING_OBJECT -1 d2_packages[d2_index]
					//IF LOCATE_CHAR_ANY_MEANS_2D scplayer d2_package_x[d2_index] d2_package_y[d2_index] 1.0 1.0 FALSE
					IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer d2_packages[d2_index] 2.0 2.0 2.0 FALSE

						IF IS_CHAR_ON_ANY_BIKE scplayer 
						OR IS_CHAR_ON_FOOT scplayer
							IF IS_XBOX_VERSION
								IF IS_BUTTON_PRESSED PAD1 CIRCLE
								AND NOT d2_leftshoulder1_pressed_last_frame = 1

									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -3.0 0.0 d2_player_offset_x1 d2_player_offset_y1 d2_dummy_z
									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 3.0 0.0 d2_player_offset_x2 d2_player_offset_y2 d2_dummy_z
									// different anims depending on which side of the player the package is on
									// and whether the player's on a bike or on foot
									// If the player's on foot, play different anims depending on the relative height of the package								
									IF IS_OBJECT_IN_ANGLED_AREA_2D d2_packages[d2_index] d2_player_offset_x1 d2_player_offset_y1 d2_player_offset_x2 d2_player_offset_y2 3.0 FALSE
										IF IS_CHAR_ON_ANY_BIKE scplayer
											TASK_PLAY_ANIM scplayer BIKEs_Snatch_L BIKES 4.0 FALSE FALSE FALSE FALSE 220
										ELSE
											GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
											GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
											d2_package_z[d2_index] += 0.25
											IF d2_package_z[d2_index] <= d2_player_z
												TASK_PLAY_ANIM scplayer pickup_box MISC 4.0 FALSE FALSE FALSE FALSE 180
											ELSE
												TASK_PLAY_ANIM scplayer GRAB_L MISC 4.0 FALSE FALSE FALSE FALSE 180
											ENDIF
										ENDIF
									ELSE
										IF IS_CHAR_ON_ANY_BIKE scplayer
											TASK_PLAY_ANIM scplayer BIKEs_Snatch_R BIKES 4.0 FALSE FALSE FALSE FALSE 220
										ELSE
											GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
											GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
											d2_package_z[d2_index] += 0.25
											IF d2_package_z[d2_index] <= d2_player_z
												TASK_PLAY_ANIM scplayer pickup_box MISC 4.0 FALSE FALSE FALSE FALSE 180
											ELSE
												TASK_PLAY_ANIM scplayer GRAB_R MISC 4.0 FALSE FALSE FALSE FALSE 180
											ENDIF
										ENDIF
									ENDIF

									GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
									DELETE_OBJECT d2_packages[d2_index]
									REMOVE_BLIP d2_package_blips[d2_index]
									ADD_ONE_OFF_SOUND d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index] SOUND_RACE_START_GO
									PRINT_NOW ( DRV3_4 ) 5000 0
									d2_package_collected[d2_index] = 1
									d2_num_packages_collected++

								ELSE
									IF NOT IS_MESSAGE_BEING_DISPLAYED
										PRINT_NOW DRV3_3 1 0 
										d2_in_range_last_printed = 1
									ELSE
										IF d2_in_range_last_printed = 1
											PRINT_NOW DRV3_3 1 0  
											d2_in_range_last_printed = 1
										ENDIF
									ENDIF
								ENDIF
							ELSE
								IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
								AND NOT d2_leftshoulder1_pressed_last_frame = 1

									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -3.0 0.0 d2_player_offset_x1 d2_player_offset_y1 d2_dummy_z
									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 3.0 0.0 d2_player_offset_x2 d2_player_offset_y2 d2_dummy_z
									// different anims depending on which side of the player the package is on
									// and whether the player's on a bike or on foot
									// If the player's on foot, play different anims depending on the relative height of the package								
									IF IS_OBJECT_IN_ANGLED_AREA_2D d2_packages[d2_index] d2_player_offset_x1 d2_player_offset_y1 d2_player_offset_x2 d2_player_offset_y2 3.0 FALSE
										IF IS_CHAR_ON_ANY_BIKE scplayer
											TASK_PLAY_ANIM scplayer BIKEs_Snatch_L BIKES 4.0 FALSE FALSE FALSE FALSE 220
										ELSE
											GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
											GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
											d2_package_z[d2_index] += 0.25
											IF d2_package_z[d2_index] <= d2_player_z
												TASK_PLAY_ANIM scplayer pickup_box MISC 4.0 FALSE FALSE FALSE FALSE 180
											ELSE
												TASK_PLAY_ANIM scplayer GRAB_L MISC 4.0 FALSE FALSE FALSE FALSE 180
											ENDIF
										ENDIF
									ELSE
										IF IS_CHAR_ON_ANY_BIKE scplayer
											TASK_PLAY_ANIM scplayer BIKEs_Snatch_R BIKES 4.0 FALSE FALSE FALSE FALSE 220
										ELSE
											GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
											GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
											d2_package_z[d2_index] += 0.25
											IF d2_package_z[d2_index] <= d2_player_z
												TASK_PLAY_ANIM scplayer pickup_box MISC 4.0 FALSE FALSE FALSE FALSE 180
											ELSE
												TASK_PLAY_ANIM scplayer GRAB_R MISC 4.0 FALSE FALSE FALSE FALSE 180
											ENDIF
										ENDIF
									ENDIF

									GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
									DELETE_OBJECT d2_packages[d2_index]
									REMOVE_BLIP d2_package_blips[d2_index]
									ADD_ONE_OFF_SOUND d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index] SOUND_RACE_START_GO
									PRINT_NOW ( DRV3_4 ) 5000 0
									d2_package_collected[d2_index] = 1
									d2_num_packages_collected++

								ELSE
									IF NOT IS_MESSAGE_BEING_DISPLAYED
										PRINT_NOW DRV3_3 1 0 
										d2_in_range_last_printed = 1
									ELSE
										IF d2_in_range_last_printed = 1
											PRINT_NOW DRV3_3 1 0  
											d2_in_range_last_printed = 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF

						ENDIF
 
					ENDIF

					IF NOT d2_package_collected[d2_index] = 1
						IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
							IF IS_CHAR_IN_CAR scplayer d2_gang_bikes[d2_index]
							AND IS_OBJECT_ATTACHED d2_packages[d2_index]
							//AND d2_package_attach_state[d2_index] = d2_attached_to_bike

								GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
								DELETE_OBJECT d2_packages[d2_index]
								REMOVE_BLIP d2_package_blips[d2_index]
								ADD_ONE_OFF_SOUND d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index] SOUND_RACE_START_GO
								PRINT_NOW ( DRV3_4 ) 5000 0
								d2_package_collected[d2_index] = 1
								d2_num_packages_collected++

							ENDIF
						ENDIF
					ENDIF
				ELSE
					//IF LOCATE_CHAR_ANY_MEANS_2D scplayer d2_package_x[d2_index] d2_package_y[d2_index] 1.0 1.0 FALSE
					IF IS_CHAR_TOUCHING_OBJECT scplayer d2_packages[d2_index]
						IF IS_CHAR_ON_ANY_BIKE scplayer 
						OR IS_CHAR_ON_FOOT scplayer
						
							GET_OBJECT_COORDINATES d2_packages[d2_index] d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index]
							DELETE_OBJECT d2_packages[d2_index]
							REMOVE_BLIP d2_package_blips[d2_index]
							ADD_ONE_OFF_SOUND d2_package_x[d2_index] d2_package_y[d2_index] d2_package_z[d2_index] SOUND_RACE_START_GO
							PRINT_NOW ( DRV3_4 ) 5000 0
							d2_package_collected[d2_index] = 1
							d2_num_packages_collected++

						ENDIF
					ENDIF
				ENDIF

				IF d2_package_collected[d2_index] = 1
					IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
						GIVE_WEAPON_TO_CHAR d2_gang_bikers[d2_index] WEAPONTYPE_MICRO_UZI 99999
						TASK_KILL_CHAR_ON_FOOT d2_gang_bikers[d2_index] scplayer
						d2_biker_attacking_player[d2_index] = 1
					ENDIF
				ENDIF

			ENDIF
			d2_index++
		ENDWHILE

		IF IS_XBOX_VERSION
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
				d2_leftshoulder1_pressed_last_frame = 1
			ELSE
				d2_leftshoulder1_pressed_last_frame = 0
			ENDIF
		ELSE
			IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				d2_leftshoulder1_pressed_last_frame = 1
			ELSE
				d2_leftshoulder1_pressed_last_frame = 0
			ENDIF
		ENDIF

	RETURN

// ***************************************************************
// 			  If player rams bikes, bikers drop packages
//	Biker tries to pick up the dropped package and drive off again
// ***************************************************************

	d2_package_dropped_check:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT d2_cur_bike_state[d2_index] = d2_not_started_chase
			AND NOT d2_package_collected[d2_index] = 1

				GET_CAR_HEALTH d2_gang_bikes[d2_index] d2_bike_health[d2_index]

				IF IS_OBJECT_ATTACHED d2_packages[d2_index]
					 IF LOCATE_CHAR_IN_CAR_CAR_2D scplayer d2_gang_bikes[d2_index] 2.0 2.0 FALSE

						d2_damage_done = d2_last_bike_health[d2_index] - d2_bike_health[d2_index]
						IF d2_damage_done >= 2
							DETACH_OBJECT d2_packages[d2_index] 0.0 0.0 0.0 FALSE
							d2_package_attach_state[d2_index] = d2_not_attached
						ENDIF

					ENDIF
				ELSE
					IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
						IF NOT IS_CHAR_HOLDING_OBJECT d2_gang_bikers[d2_index] -1
						
							IF NOT d2_biker_picking_up_dropped_package[d2_index] = 1
								
								IF DOES_OBJECT_EXIST d2_packages[d2_index]
									TASK_GO_TO_OBJECT d2_gang_bikers[d2_index] d2_packages[d2_index] -1 1.0
									d2_biker_picking_up_dropped_package[d2_index] = 1
								ENDIF
							
							ELSE
								
								GET_SCRIPT_TASK_STATUS d2_gang_bikers[d2_index] TASK_GO_TO_OBJECT d2_task_status
								IF d2_task_status = FINISHED_TASK
									
									IF DOES_OBJECT_EXIST d2_packages[d2_index]
										TASK_PICK_UP_OBJECT d2_gang_bikers[d2_index] d2_packages[d2_index] 0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_PEDHEADING NULL NULL FALSE
										d2_package_attach_state[d2_index] = d2_attached_to_char
									ENDIF
									
									d2_biker_picking_up_dropped_package[d2_index] = 0
									// set to zero so that keep_bikers_on_bikes now puts the driver back on the bike
									d2_getting_back_on_bike[d2_index] = 0
								
								ELSE
								
									IF NOT DOES_OBJECT_EXIST d2_packages[d2_index]
										d2_biker_picking_up_dropped_package[d2_index] = 0
										// set to zero so that keep_bikers_on_bikes now puts the driver back on the bike
										d2_getting_back_on_bike[d2_index] = 0
									ENDIF
									
								ENDIF		

							ENDIF

						ENDIF
					ENDIF
				ENDIF

				d2_last_bike_health[d2_index] = d2_bike_health[d2_index]
			
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// ****************************************************************
// Keep packages still on ground when not attached to bike or biker 
// ****************************************************************

	d2_keep_packages_on_ground:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF DOES_OBJECT_EXIST d2_packages[d2_index]
				IF NOT IS_OBJECT_ATTACHED d2_packages[d2_index]
				AND NOT IS_CHAR_HOLDING_OBJECT -1 d2_packages[d2_index]
				AND IS_OBJECT_STATIC d2_packages[d2_index]
					IF NOT d2_package_immovable[d2_index] = 1
						FREEZE_OBJECT_POSITION d2_packages[d2_index] TRUE
						d2_package_immovable[d2_index] = 1
					ENDIF
				ELSE
					IF d2_package_immovable[d2_index] = 1
						FREEZE_OBJECT_POSITION d2_packages[d2_index] FALSE
						d2_package_immovable[d2_index] = 0
					ENDIF
				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *************************************************************
//   Stop bikes from getting too far away from the city centre
// *************************************************************

	d2_keep_wandering_bikes_in_city_centre:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
			AND NOT d2_biker_attacking_player[d2_index] = 1
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND d2_cur_bike_state[d2_index] = d2_wandering_randomly
				AND NOT LOCATE_CHAR_IN_CAR_2D d2_gang_bikers[d2_index] d2_city_centre_x d2_city_centre_y 400.0 400.0 FALSE

					TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_city_centre_x d2_city_centre_y d2_city_centre_z d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

					d2_cur_bike_state[d2_index] = d2_going_to_city_centre

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

	d2_set_centre_bikes_wandering:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
			AND NOT d2_biker_attacking_player[d2_index] = 1
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND d2_cur_bike_state[d2_index] = d2_going_to_city_centre
				AND LOCATE_CHAR_IN_CAR_2D d2_gang_bikers[d2_index] d2_city_centre_x d2_city_centre_y 200.0 200.0 FALSE
					
					TASK_CAR_DRIVE_WANDER d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_speed[d2_index] DRIVINGMODE_AVOIDCARS

					d2_cur_bike_state[d2_index] = d2_wandering_randomly

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *************************************************************
// 			Put bikers back on bikes if they've fallen off
// *************************************************************

	d2_keep_bikers_on_bikes:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
			AND NOT d2_biker_picking_up_dropped_package[d2_index] = 1
			AND NOT d2_biker_attacking_player[d2_index] = 1
				IF NOT IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
					IF d2_getting_back_on_bike[d2_index] = 0

						TASK_ENTER_CAR_AS_DRIVER d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] -1
						d2_getting_back_on_bike[d2_index] = 1

					ENDIF
				ELSE
					IF d2_getting_back_on_bike[d2_index] = 1
						IF d2_cur_bike_state[d2_index] = d2_wandering_randomly
							TASK_CAR_DRIVE_WANDER d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_speed[d2_index] DRIVINGMODE_AVOIDCARS
						ENDIF
						IF d2_cur_bike_state[d2_index] = d2_going_to_city_centre
							TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_city_centre_x d2_city_centre_y d2_city_centre_z d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
						IF d2_cur_bike_state[d2_index] = d2_going_to_first_point
							TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_first_pt_x[d2_index] d2_bike_first_pt_y[d2_index] d2_bike_first_pt_z[d2_index] d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
						IF d2_cur_bike_state[d2_index] = d2_going_to_destination
							TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_dest_x d2_bike_dest_y d2_bike_dest_z d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
						IF DOES_OBJECT_EXIST d2_packages[d2_index]
							IF IS_CHAR_HOLDING_OBJECT d2_gang_bikers[d2_index] d2_packages[d2_index]
								DROP_OBJECT d2_gang_bikers[d2_index] FALSE
								ATTACH_OBJECT_TO_CAR d2_packages[d2_index] d2_gang_bikes[d2_index] 0.0 -0.9 0.5 0.0 0.0 0.0
								d2_package_attach_state[d2_index] = d2_attached_to_bike
							ENDIF
						ENDIF
						d2_getting_back_on_bike[d2_index] = 0
					ENDIF
				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *********************************************************************
// If bikes attacking player, shoot if player near and chase if far away
// *********************************************************************

	d2_update_biker_attack_state:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
			AND d2_biker_attacking_player[d2_index] = 1

				IF d2_biker_shooting_at_player[d2_index] = 1
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D d2_gang_bikers[d2_index] scplayer 40.0 40.0 FALSE
						TASK_CAR_MISSION d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] -1 MISSION_RAMPLAYER_FARAWAY d2_bike_speed[d2_index] DRIVINGMODE_AVOIDCARS
						d2_biker_shooting_at_player[d2_index] = 0
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D d2_gang_bikers[d2_index] scplayer 10.0 10.0 FALSE
//					AND IS_CHAR_STOPPED d2_gang_bikers[d2_index]
						TASK_KILL_CHAR_ON_FOOT d2_gang_bikers[d2_index] scplayer
						d2_biker_shooting_at_player[d2_index] = 1
					ENDIF
				ENDIF

			ENDIF
			
			d2_index++
		
		ENDWHILE

	RETURN

// *************************************************************
// 	   Send bikes to their own territory after five minutes
// *************************************************************

	d2_send_bikes_to_destination:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND NOT d2_cur_bike_state[d2_index] = d2_going_to_destination
				AND NOT d2_biker_attacking_player[d2_index] = 1
					
					TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_dest_x d2_bike_dest_y d2_bike_dest_z d2_bike_speed[d2_index] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

					d2_cur_bike_state[d2_index] = d2_going_to_destination

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *********************************************************************************
// If bikes have reached first point, set to wander for the rest of the mission time
// *********************************************************************************

	d2_set_bikes_wandering_from_first_dest_check:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND d2_cur_bike_state[d2_index] = d2_going_to_first_point
				AND NOT d2_biker_attacking_player[d2_index] = 1
				AND LOCATE_CHAR_IN_CAR_3D d2_gang_bikers[d2_index] d2_bike_first_pt_x[d2_index] d2_bike_first_pt_y[d2_index] d2_bike_first_pt_z[d2_index] 3.0 3.0 3.0 FALSE

					TASK_CAR_DRIVE_WANDER d2_gang_bikers[d2_index] d2_gang_bikes[d2_index] d2_bike_speed[d2_index] DRIVINGMODE_AVOIDCARS
					d2_cur_bike_state[d2_index] = d2_wandering_randomly

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *****************************************************************
// Find whether any bikers have reached destination with the package
// *****************************************************************

	d2_have_any_packages_reached_destination:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF DOES_OBJECT_EXIST d2_packages[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
			AND NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
				IF IS_OBJECT_ATTACHED d2_packages[d2_index]
				AND IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
					IF LOCATE_OBJECT_2D d2_packages[d2_index] d2_bike_dest_x d2_bike_dest_y 20.0 7.0 FALSE

						d2_any_package_at_destination = 1
						d2_package_at_dest_index = d2_index

					ENDIF
				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *****************************************************************
// 	   Bikers speed up and slow down if player is near/far away
// *****************************************************************

	d2_new_bike_speed_check:

		GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				IF IS_CHAR_IN_CAR d2_gang_bikers[d2_index] d2_gang_bikes[d2_index]
				AND NOT d2_biker_attacking_player[d2_index] = 1

					IF d2_cur_bike_state[d2_index] = d2_going_to_destination
					AND LOCATE_CAR_2D d2_gang_bikes[d2_index] d2_bike_dest_x d2_bike_dest_y 25.0 25.0 FALSE

						// slow bikes down for final corner before cutscene
						d2_bike_speed[d2_index] = 15.0

					ELSE
					
						GET_CAR_COORDINATES d2_gang_bikes[d2_index] d2_bike_x d2_bike_y d2_bike_z
						GET_DISTANCE_BETWEEN_COORDS_3D d2_player_x d2_player_y d2_player_z d2_bike_x d2_bike_y d2_bike_z d2_dist_from_player_to_bike

						IF d2_dist_from_player_to_bike < 0.0
							d2_dist_from_player_to_bike = 0.0
						ENDIF
						IF d2_dist_from_player_to_bike <= 50.0
							d2_bike_speed[d2_index] = d2_dist_from_player_to_bike * 0.4
							d2_bike_speed[d2_index] *= -1.0
							d2_bike_speed[d2_index] += 40.0
						ELSE
							d2_bike_speed[d2_index] = 20.0
						ENDIF

					ENDIF

					SET_CAR_CRUISE_SPEED d2_gang_bikes[d2_index] d2_bike_speed[d2_index]

				ENDIF						
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *****************************************************************
// 		Find out whether any packages have gone into the water
// *****************************************************************

	d2_are_any_packages_in_water:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF DOES_OBJECT_EXIST d2_packages[d2_index]
				IF IS_OBJECT_IN_WATER d2_packages[d2_index]

					d2_any_packages_in_water = 1

				ENDIF
			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *****************************************************************
// 		   Find out whether any packages have been destroyed
// *****************************************************************

	d2_have_any_packages_been_destroyed:

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF IS_CAR_DEAD d2_gang_bikes[d2_index]
			AND DOES_OBJECT_EXIST d2_packages[d2_index]
			AND d2_package_attach_state[d2_index] = d2_attached_to_bike

				DELETE_OBJECT d2_packages[d2_index]
				d2_any_packages_been_destroyed = 1

			ENDIF
			d2_index++
		ENDWHILE

	RETURN

// *****************************************************************
// 							 Deal cutscene
// *****************************************************************

	d2_play_cutscene_for_deal:

		CLEAR_AREA -1814.82 381.40 17.09 100.0 FALSE

//		IF d2_player_sniped_at_biker = 1
//			REQUEST_COLLISION -1814.82 381.40
//			LOAD_SCENE -1814.82 381.40 17.09
//		ENDIF

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			SET_CHAR_COORDINATES scplayer -1771.94 336.35 8.94
			SET_CHAR_HEADING scplayer 49.66
		ENDIF
		IF d2_player_entered_any_car = 1
			IF NOT IS_CAR_DEAD d2_last_player_car_before_meet
				SET_CAR_COORDINATES d2_last_player_car_before_meet -1766.6 330.71 8.23
				SET_CAR_HEADING d2_last_player_car_before_meet 45.1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
			SET_CHAR_VISIBLE d2_gang_bikers[0] TRUE
			IF DOES_OBJECT_EXIST d2_packages[0]
				SET_OBJECT_VISIBLE d2_packages[0] TRUE
			ENDIF
		ENDIF
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
				FREEZE_CAR_POSITION d2_gang_bikes[d2_index] FALSE
			ENDIF
			d2_index++
		ENDWHILE

		d2_cutscene_skipped = 1
		d2_player_given_mission_brief = 0
		
		SKIP_CUTSCENE_START

		SET_FIXED_CAMERA_POSITION -1808.39 374.18 17.98 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1813.41 376.31 17.32 JUMP_CUT

		IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
		AND NOT IS_CAR_DEAD d2_gang_bikes[0]
			TASK_ENTER_CAR_AS_DRIVER d2_gang_bikers[0] d2_gang_bikes[0] -1
		ENDIF

		LVAR_INT d2_all_bikers_on_bikes
		d2_all_bikers_on_bikes = 0
		WHILE NOT d2_all_bikers_on_bikes = 1
		AND NOT IS_CHAR_DEAD d2_gang_bikers[0]
			WAIT 0
			IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
			AND NOT IS_CAR_DEAD d2_gang_bikes[0]
				IF IS_CHAR_IN_CAR d2_gang_bikers[0] d2_gang_bikes[0]
					d2_all_bikers_on_bikes = 1
				ENDIF
			ENDIF
		ENDWHILE

		WAIT 1000

		SWITCH_ROADS_BACK_TO_ORIGINAL -2014.82 181.4 -13.0 -1614.82 581.4 47.0
		
		SET_FIXED_CAMERA_POSITION -1832.36 380.48 18.71 0.0 0.0 0.0
		IF NOT IS_CAR_DEAD d2_gang_bikes[0]
			POINT_CAMERA_AT_CAR d2_gang_bikes[0] FIXED JUMP_CUT
			IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
			AND DOES_OBJECT_EXIST d2_packages[0]
				IF IS_CHAR_HOLDING_OBJECT d2_gang_bikers[0] d2_packages[0]	
					DROP_OBJECT d2_gang_bikers[0] FALSE
				ENDIF
				ATTACH_OBJECT_TO_CAR d2_packages[0] d2_gang_bikes[0] 0.0 -0.9 0.5 0.0 0.0 0.0
				d2_package_attach_state[0] = d2_attached_to_bike
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD d2_gang_bikes[1]
		AND NOT IS_CHAR_DEAD d2_gang_bikers[1]
			TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[1] d2_gang_bikes[1] -1854.74 533.22 33.12 d2_bike_speed[1] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
		ENDIF
		WAIT 150
		IF NOT IS_CAR_DEAD d2_gang_bikes[2]
		AND NOT IS_CHAR_DEAD d2_gang_bikers[2]
			TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[2] d2_gang_bikes[2] -1854.74 533.22 33.12 d2_bike_speed[2] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
		ENDIF
		WAIT 150
		IF NOT IS_CAR_DEAD d2_gang_bikes[0]
		AND NOT IS_CHAR_DEAD d2_gang_bikers[0]
			TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[0] d2_gang_bikes[0] -1854.74 533.22 33.12 d2_bike_speed[0] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
		ENDIF
		WAIT 100
		IF NOT IS_CAR_DEAD d2_gang_bikes[3]
		AND NOT IS_CHAR_DEAD d2_gang_bikers[3]
			TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[3] d2_gang_bikes[3] -1854.74 533.22 33.12 d2_bike_speed[3] MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
		ENDIF

		WAIT 7000

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer -1820.72 382.73 16.04
		ELSE
			SET_CHAR_COORDINATES scplayer -1820.72 382.73 16.04
		ENDIF
		SET_CHAR_HEADING scplayer 326.45
		IF NOT IS_CAR_DEAD d2_player_bike
			TASK_ENTER_CAR_AS_DRIVER scplayer d2_player_bike -1
		ENDIF
		// show player running to bike
//		SET_FIXED_CAMERA_POSITION -1818.15 380.66 17.06 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT -1818.74 383.39 16.9 JUMP_CUT
		SET_FIXED_CAMERA_POSITION -1818.1 380.01 16.8 0.0 0.0 0.0
//		POINT_CAMERA_AT_POINT -1818.72 382.74 16.9 JUMP_CUT
		POINT_CAMERA_AT_CAR d2_player_bike FIXED JUMP_CUT

		TIMERB = 0
		WHILE TIMERB < 4000

			WAIT 0

			IF TIMERB > 0
			AND NOT d2_player_given_mission_brief = 1
				PRINT_NOW ( DRV3_2 ) 10000 0
				d2_player_given_mission_brief = 1
			ENDIF				

		ENDWHILE

		GOSUB d2_start_bike_chase

		d2_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF d2_cutscene_skipped = 1

			// if cutscene skipped, set conditions to those at the end of the cutscene
			SWITCH_ROADS_BACK_TO_ORIGINAL -2014.82 181.4 -13.0 -1614.82 581.4 47.0

			IF NOT IS_CHAR_DEAD d2_gang_bikers[0]
			AND NOT IS_CAR_DEAD d2_gang_bikes[0]
				IF NOT IS_CHAR_IN_CAR d2_gang_bikers[0] d2_gang_bikes[0]
					WARP_CHAR_INTO_CAR d2_gang_bikers[0] d2_gang_bikes[0]
				ENDIF
				IF DOES_OBJECT_EXIST d2_packages[0]
					IF NOT IS_OBJECT_ATTACHED d2_packages[0]
						IF IS_CHAR_HOLDING_OBJECT d2_gang_bikers[0] d2_packages[0]
							DROP_OBJECT d2_gang_bikers[0] FALSE
						ENDIF
						ATTACH_OBJECT_TO_CAR d2_packages[0] d2_gang_bikes[0] 0.0 -0.9 0.5 0.0 0.0 0.0
						d2_package_attach_state[0] = d2_attached_to_bike
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD d2_gang_bikes[0]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[0]
				SET_CAR_COORDINATES d2_gang_bikes[0] -1894.59 582.43 34.56
			ENDIF
			IF NOT IS_CAR_DEAD d2_gang_bikes[1]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[1]
				SET_CAR_COORDINATES d2_gang_bikes[1] -1893.56 574.34 34.55
			ENDIF
			IF NOT IS_CAR_DEAD d2_gang_bikes[2]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[2]
				SET_CAR_COORDINATES d2_gang_bikes[2] -1899.96 581.25 34.56
			ENDIF
			IF NOT IS_CAR_DEAD d2_gang_bikes[3]
			AND NOT IS_CHAR_DEAD d2_gang_bikers[3]
				SET_CAR_COORDINATES d2_gang_bikes[3] -1898.95 574.08 34.56
			ENDIF

			IF NOT IS_CAR_DEAD d2_player_bike
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -1819.01 386.28 6.58
				ENDIF
				WARP_CHAR_INTO_CAR scplayer d2_player_bike
			ENDIF

			GOSUB d2_start_bike_chase

			IF NOT d2_player_given_mission_brief = 1
				PRINT_NOW ( DRV3_2 ) 10000 0
			ENDIF

		ENDIF

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF DOES_OBJECT_EXIST d2_packages[d2_index]
				ADD_BLIP_FOR_OBJECT d2_packages[d2_index] d2_package_blips[d2_index]
				CHANGE_BLIP_COLOUR d2_package_blips[d2_index] RED
			ENDIF
			d2_index++
		ENDWHILE

		// reset ambushed van proofs
		IF NOT IS_CAR_DEAD d2_ambushed_van
			SET_CAR_PROOFS d2_ambushed_van FALSE FALSE FALSE FALSE FALSE
		ENDIF
		// reset biker and bike proofs
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_index]
				SET_CHAR_PROOFS d2_gang_bikers[d2_index] FALSE FALSE FALSE FALSE FALSE
			ENDIF
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
				SET_CAR_PROOFS d2_gang_bikes[d2_index] FALSE FALSE FALSE FALSE FALSE
			ENDIF
			d2_index++
		ENDWHILE
		IF NOT IS_CAR_DEAD d2_player_bike
			SET_CAR_PROOFS d2_player_bike FALSE FALSE FALSE FALSE FALSE
		ENDIF

		// bikes are allowed to wander away from the player
		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
				SET_LOAD_COLLISION_FOR_CAR_FLAG d2_gang_bikes[d2_index] FALSE
			ENDIF
			d2_index++
		ENDWHILE
		IF NOT IS_CAR_DEAD d2_player_bike
			SET_LOAD_COLLISION_FOR_CAR_FLAG d2_player_bike FALSE
		ENDIF
		IF NOT IS_CAR_DEAD d2_ambushed_van
			SET_LOAD_COLLISION_FOR_CAR_FLAG d2_ambushed_van FALSE
		ENDIF

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

	RETURN

// *****************************************************************
// 		  Cutscene showing player arriving back with packages
// *****************************************************************

	d2_play_mission_passed_cutscene:

		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		CLEAR_AREA d2_player_dest_x d2_player_dest_y d2_player_dest_z 50.0 FALSE
		CLEAR_PRINTS
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		SWITCH_WIDESCREEN ON

		LVAR_INT d2_final_cutscene_seq
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer d2_player_bike
			SET_CAR_COORDINATES d2_player_bike d2_player_dest_x d2_player_dest_y d2_player_dest_z
			SET_CAR_HEADING d2_player_bike 340.37
			OPEN_SEQUENCE_TASK d2_final_cutscene_seq
				TASK_LEAVE_CAR -1 d2_player_bike
				TASK_GO_STRAIGHT_TO_COORD -1 -2625.96 1417.82 6.15 PEDMOVE_WALK -1
			CLOSE_SEQUENCE_TASK d2_final_cutscene_seq
			PERFORM_SEQUENCE_TASK scplayer d2_final_cutscene_seq
			CLEAR_SEQUENCE_TASK d2_final_cutscene_seq
		ELSE
			DELETE_CAR d2_player_bike
			SET_CHAR_COORDINATES scplayer d2_player_dest_x d2_player_dest_y d2_player_dest_z
			SET_CHAR_HEADING scplayer 10.0
			TASK_GO_STRAIGHT_TO_COORD scplayer -2625.96 1417.82 6.15 PEDMOVE_WALK -1
		ENDIF

		SET_FIXED_CAMERA_POSITION -2626.02 1403.8 6.95 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2624.56 1406.19 7.03 JUMP_CUT

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		d2_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		WAIT 2000

		d2_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		IF d2_cutscene_skipped = 1
		ENDIF

		IF NOT d2_cutscene_skipped = 1
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer -2624.28 1410.93 6.15
		SET_CHAR_HEADING scplayer 194.52

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		IF NOT d2_cutscene_skipped = 1
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF

		SET_PLAYER_CONTROL player1 ON

	RETURN

// *****************************************************************
// 			Cutscene showing biker escaping into garage
// *****************************************************************

	d2_play_mission_failed_cutscene:
		
		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

		MARK_CAR_AS_NO_LONGER_NEEDED d2_player_bike
		CLEAR_AREA d2_bike_dest_x d2_bike_dest_y d2_bike_dest_z 100.0 FALSE

		d2_index = 0
		WHILE d2_index < d2_num_of_packages
			IF NOT d2_index = d2_package_at_dest_index
				DELETE_CHAR d2_gang_bikers[d2_index]
				IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
					IF NOT IS_CHAR_IN_CAR scplayer d2_gang_bikes[d2_index]
						DELETE_CAR d2_gang_bikes[d2_index]
					ENDIF
				ELSE
					DELETE_CAR d2_gang_bikes[d2_index]
				ENDIF
				DELETE_OBJECT d2_packages[d2_index]
			ENDIF
			d2_index++
		ENDWHILE

		GET_AREA_VISIBLE d2_current_area_visible

		LVAR_FLOAT d2_ground_z d2_water_height
		GET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_player_z
		GET_CHAR_HEADING scplayer d2_player_heading
		GET_WATER_HEIGHT_AT_COORDS d2_player_x d2_player_y FALSE d2_water_height
		GET_GROUND_Z_FOR_3D_COORD d2_player_x d2_player_y d2_player_z d2_ground_z
		IF d2_water_height > d2_ground_z
			d2_ground_z = d2_water_height
		ENDIF
		SET_CHAR_COORDINATES scplayer -2762.82 98.13 -100.0
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer d2_player_bike
			SET_CAR_HEADING d2_player_bike 354.04
		ENDIF

		SET_AREA_VISIBLE 0

		REQUEST_COLLISION d2_bike_dest_x d2_bike_dest_y
		LOAD_SCENE d2_bike_dest_x d2_bike_dest_y d2_bike_dest_z

		CLEAR_HELP

		SWITCH_WIDESCREEN ON

		SET_FIXED_CAMERA_POSITION -2736.43 87.02 4.27 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2735.57 86.18 4.27 JUMP_CUT

		OPEN_GARAGE Tbon

		IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_package_at_dest_index]
		AND NOT IS_CAR_DEAD d2_gang_bikes[d2_package_at_dest_index]
			SET_CAR_COORDINATES d2_gang_bikes[d2_package_at_dest_index] -2720.92 83.65 3.69
			SET_CAR_HEADING d2_gang_bikes[d2_package_at_dest_index] 85.84
			IF NOT IS_CHAR_IN_CAR d2_gang_bikers[d2_package_at_dest_index] d2_gang_bikes[d2_package_at_dest_index]
				IF IS_CHAR_IN_ANY_CAR d2_gang_bikers[d2_package_at_dest_index]
					WARP_CHAR_FROM_CAR_TO_COORD d2_gang_bikers[d2_package_at_dest_index] -2720.92 83.65 -6.31
				ENDIF
				WARP_CHAR_INTO_CAR d2_gang_bikers[d2_package_at_dest_index] d2_gang_bikes[d2_package_at_dest_index]
			ENDIF
			// make sure bike follows nodes and doesn't drive into wall to get to point
			SET_CAR_STRAIGHT_LINE_DISTANCE d2_gang_bikes[d2_package_at_dest_index] 2
			TASK_CAR_DRIVE_TO_COORD d2_gang_bikers[d2_package_at_dest_index] d2_gang_bikes[d2_package_at_dest_index] -2730.11 65.22 3.91 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
			SET_CAR_FORWARD_SPEED d2_gang_bikes[d2_package_at_dest_index] 7.0
		ENDIF

		DELETE_CAR d2_ambushed_van

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

		d2_cutscene_skipped = 1
		
		SKIP_CUTSCENE_START

		IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_package_at_dest_index]
			GET_CHAR_COORDINATES d2_gang_bikers[d2_package_at_dest_index] d2_bike_x d2_bike_y d2_bike_z
		ENDIF
		WHILE d2_bike_y > 71.36
			WAIT 0
			IF NOT IS_CHAR_DEAD d2_gang_bikers[d2_package_at_dest_index]
				GET_CHAR_COORDINATES d2_gang_bikers[d2_package_at_dest_index] d2_bike_x d2_bike_y d2_bike_z
			ELSE
				d2_bike_y = 71.36
			ENDIF
		ENDWHILE

		CLOSE_GARAGE Tbon
		WHILE NOT IS_GARAGE_CLOSED Tbon
			WAIT 0
		ENDWHILE

		WAIT 1000

		d2_cutscene_skipped = 0

		SKIP_CUTSCENE_END

		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

		DELETE_CHAR d2_gang_bikers[d2_package_at_dest_index]
		DELETE_CAR d2_gang_bikes[d2_package_at_dest_index]

		IF d2_cutscene_skipped = 1

			IF NOT IS_GARAGE_CLOSED Tbon
				CLOSE_GARAGE Tbon
				WHILE NOT IS_GARAGE_CLOSED Tbon
					WAIT 0
				ENDWHILE
			ENDIF
				
		ENDIF

		SET_AREA_VISIBLE d2_current_area_visible

		REQUEST_COLLISION d2_player_x d2_player_y
		LOAD_SCENE d2_player_x d2_player_y d2_ground_z

		SET_CHAR_COORDINATES scplayer d2_player_x d2_player_y d2_ground_z
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer d2_player_bike
			SET_CAR_HEADING d2_player_bike d2_player_heading
		ELSE
			SET_CHAR_HEADING scplayer d2_player_heading
		ENDIF

		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON

		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

	RETURN

// ******************************** Mission driv2 failed **********************************

mission_driv2_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN


// ******************************** Mission driv2 passed **********************************

mission_driv2_passed:

flag_synd_mission_counter++
REGISTER_MISSION_PASSED ( FAR_2 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!"
ADD_SCORE player1 5000
AWARD_PLAYER_MISSION_RESPECT 20
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
RETURN
		

// *********************************** Mission cleanup *************************************

mission_cleanup_driv2:

MARK_MODEL_AS_NO_LONGER_NEEDED BOXVILLE
MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
MARK_MODEL_AS_NO_LONGER_NEEDED kmb_packet
MARK_MODEL_AS_NO_LONGER_NEEDED WMYCR
MARK_MODEL_AS_NO_LONGER_NEEDED HMYCR
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
REMOVE_ANIMATION BIKES
REMOVE_ANIMATION MISC
d2_index = 0
WHILE d2_index < d2_num_of_packages
	REMOVE_BLIP d2_package_blips[d2_index]
	IF DOES_OBJECT_EXIST d2_packages[d2_index]
		IF NOT IS_OBJECT_ON_SCREEN d2_packages[d2_index]
			DELETE_OBJECT d2_packages[d2_index]
		ENDIF
	ENDIF
	d2_index++
ENDWHILE
//REMOVE_BLIP d2_player_bike_blip
REMOVE_BLIP d2_ambushed_van_blip
REMOVE_BLIP d2_player_dest_blip
// reset car proofs
IF NOT IS_CAR_DEAD d2_ambushed_van
	SET_CAR_PROOFS d2_ambushed_van FALSE FALSE FALSE FALSE FALSE
ENDIF
d2_index = 0
WHILE d2_index < d2_num_of_packages
	IF NOT IS_CAR_DEAD d2_gang_bikes[d2_index]
		SET_CAR_PROOFS d2_gang_bikes[d2_index] FALSE FALSE FALSE FALSE FALSE
		FREEZE_CAR_POSITION d2_gang_bikes[d2_index] FALSE
	ENDIF
	d2_index++
ENDWHILE
IF NOT IS_CAR_DEAD d2_player_bike
	SET_CAR_PROOFS d2_player_bike FALSE FALSE FALSE FALSE FALSE
ENDIF
REMOVE_DECISION_MAKER d2_gang_biker_decisions
CLEAR_SEQUENCE_TASK d2_final_cutscene_seq
CLEAR_HELP
//SWITCH_ROADS_BACK_TO_ORIGINAL -2130.87 903.39 45.48 -2013.14 959.18 80.03
SWITCH_ROADS_BACK_TO_ORIGINAL -2014.82 181.4 -13.0 -1614.82 581.4 47.0
IF IS_PLAYER_PLAYING player1
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
ENDIF
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


}