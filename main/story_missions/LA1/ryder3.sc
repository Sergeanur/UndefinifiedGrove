MISSION_START

// *****************************************************************************************
// ************************** GUN MISSION 2: 'Ammo Train/Truck' **************************** 
// *****************************************************************************************
// *** Player has got a tip off about a train load of weaponry that has stopped. The Same*** 
// *** tip off has been giving to every gang in LA.  Huge Gun fight with enemy gang, 	 ***
// *** Mexicans and Players gang.  Player has to hold the train long enough so his homies***
// *** can grab enough weapons.  														 ***
// *****************************************************************************************

// Mission start stuff

SCRIPT_NAME ryder3

GOSUB mission_ryder3_start
																 
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_ryder3_failed	
ENDIF

GOSUB mission_ryder3_cleanup

MISSION_END
 
// ****************************************Mission Start************************************
{

LVAR_INT mission_blip sequence_task v

LVAR_INT r3_car_paused r3_left_car r3_get_back r3_get_onto_train r3_crates_0 r3_crates_1 r3_crates_2

LVAR_FLOAT x2 y2 z2 x3 y3 z3 h 

LVAR_INT r3_peekright r3_peekleft

LVAR_INT r3_m3_blip[8] r3_ammo_crate[5]

LVAR_INT r3_seq_tunnel_1 r3_seq_tunnel_2 f4_train_rear_blip r3_failed

LVAR_INT r3_catching_blip r3_grove_blip r3_tough 

LVAR_INT r3_played_anim	r3_caboose r3_van_seq r3_cower

LVAR_INT r3_crate_w r3_crate_x r3_crate_y r3_crate_z r3_crate_v r3_crate_cnt 

LVAR_INT r3_start_text r3_wait  

LVAR_INT r3_time_left r3_inside_zone

LVAR_INT r3_converse_good r3_converse_good_1

VAR_INT r3_rnd

LVAR_INT r3_mission_flag ryders_goon3  

LVAR_INT r3_crate_a r3_crate_b r3_crate_c r3_crate_d

LVAR_INT r3_playing

LVAR_INT r3_audio

LVAR_INT r3_upsidedown

LVAR_INT r3_sprayed

LVAR_INT r3_ryders_car_blip

LVAR_INT r3_fx

LVAR_INT r3_switch

LVAR_INT r3_spawn_train

LVAR_INT r3_car_been_sprayed

LVAR_INT r3_back_to_grove

LVAR_INT r3_empty

LVAR_TEXT_LABEL r3_print

mission_ryder3_start:	  

REGISTER_MISSION_GIVEN
 
flag_player_on_mission = 1

MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

r3_mission_flag = 0

r3_rnd = 0

r3_playing = 2

r3_spawn_train = 0

// *****************************************************************************************

LOAD_MISSION_TEXT ryder3

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH r3_tough

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY r3_empty

SET_CAR_DENSITY_MULTIPLIER 0.5

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 2

LOAD_CUTSCENE RYDER3A
 														      
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

CLEAR_PRINTS

SWITCH_WIDESCREEN OFF 

SET_AREA_VISIBLE 0 

SWITCH_CAR_GENERATOR gen_car6 0

REQUEST_MODEL picador
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL COLT45
REQUEST_MODEL CR_GUNCRATE
REQUEST_MODEL CR_AMMOBOX
REQUEST_MODEL FAM1
REQUEST_ANIMATION BOX
REQUEST_ANIMATION RYDER
REQUEST_ANIMATION SWAT
REQUEST_MODEL LSV1
REQUEST_MODEL LSV2
REQUEST_MODEL LSV3
REQUEST_MODEL FREIGHT
REQUEST_MODEL FREIFLAT
REQUEST_MODEL GREENWOO
REQUEST_MODEL FAM1
REQUEST_MODEL FAM2

LOAD_SPECIAL_CHARACTER 1 ryder2

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED picador
   OR NOT HAS_MODEL_LOADED MICRO_UZI
   OR NOT HAS_MODEL_LOADED COLT45
   OR NOT HAS_MODEL_LOADED CR_GUNCRATE
   OR NOT HAS_MODEL_LOADED CR_AMMOBOX
 	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED FAM1
   OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
   OR NOT HAS_ANIMATION_LOADED BOX
   OR NOT HAS_ANIMATION_LOADED RYDER
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED LSV1
   OR NOT HAS_MODEL_LOADED LSV2
   OR NOT HAS_MODEL_LOADED LSV3
   OR NOT HAS_MODEL_LOADED FREIGHT
   OR NOT HAS_MODEL_LOADED FREIFLAT
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED GREENWOO
   OR NOT HAS_MODEL_LOADED FAM1
   OR NOT HAS_MODEL_LOADED FAM2
   OR NOT HAS_ANIMATION_LOADED SWAT
	WAIT 0
ENDWHILE

IF NOT IS_CHAR_DEAD scplayer

GOSUB r3_set_camera

SET_PLAYER_CONTROL player1 OFF

OPEN_SEQUENCE_TASK r3_peekright

	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
	TASK_STAY_IN_SAME_PLACE -1 FALSE
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT r3_peekright 1

CLOSE_SEQUENCE_TASK r3_peekright

OPEN_SEQUENCE_TASK r3_peekleft

	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
	TASK_STAY_IN_SAME_PLACE -1 FALSE
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT r3_peekleft 1

CLOSE_SEQUENCE_TASK r3_peekleft

OPEN_SEQUENCE_TASK r3_cower
				
	TASK_PLAY_ANIM -1 cower PED 4.0 FALSE FALSE FALSE TRUE -1

	TASK_PAUSE -1 2000

CLOSE_SEQUENCE_TASK	r3_cower

OPEN_SEQUENCE_TASK r3_van_seq

	TASK_PLAY_ANIM -1 RYD_Beckon_01 RYDER 1.0 FALSE FALSE FALSE FALSE -1

	TASK_PAUSE -1 20000

	SET_SEQUENCE_TO_REPEAT r3_van_seq 1

CLOSE_SEQUENCE_TASK r3_van_seq

OPEN_SEQUENCE_TASK r3_get_onto_train

	TASK_STAY_IN_SAME_PLACE -1 FALSE

	TASK_GO_TO_COORD_ANY_MEANS -1 2290.5840 -1142.4111 25.7144 PEDMOVE_RUN -1

	TASK_GO_TO_COORD_ANY_MEANS -1 2288.5393 -1133.7534 25.7076 PEDMOVE_RUN -1

	TASK_ACHIEVE_HEADING -1 253.8300   

	TASK_STAY_IN_SAME_PLACE -1 TRUE

CLOSE_SEQUENCE_TASK r3_get_onto_train

OPEN_SEQUENCE_TASK r3_seq_tunnel_1

	TASK_GO_TO_COORD_ANY_MEANS -1 2289.0916 -1117.0493 25.9062 PEDMOVE_RUN -1

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK r3_seq_tunnel_1

OPEN_SEQUENCE_TASK r3_seq_tunnel_2

	TASK_GO_TO_COORD_ANY_MEANS -1 2285.1099 -1117.1169 25.9062 PEDMOVE_RUN -1

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK r3_seq_tunnel_2

CLEAR_AREA 2477.1350 -1683.1158 12.3782 10.0 TRUE
 
CLEAR_AREA 2469.2244 -1672.4965 12.4840 10.0 TRUE

CLEAR_AREA 2472.7832 -1685.6962 12.5153 0.5	0

LOAD_SCENE 2472.7832 -1685.6962 12.5153

IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_COORDINATES scplayer 2472.7832 -1685.6962 12.5153 
	SET_CHAR_HEADING scplayer 336.6331
	SET_CAMERA_BEHIND_PLAYER

ENDIF

LVAR_INT ryders_car
SET_CAR_MODEL_COMPONENTS picador 4 4
CUSTOM_PLATE_FOR_NEXT_CAR picador &_SHERM__
CREATE_CAR picador 2476.0354 -1678.3788 12.3457 ryders_car
SET_CAN_BURST_CAR_TYRES ryders_car FALSE
SET_CAR_HEADING ryders_car 43.9007
SET_CAR_HEALTH ryders_car 2000
ADD_BLIP_FOR_CAR ryders_car r3_ryders_car_blip
SET_BLIP_AS_FRIENDLY r3_ryders_car_blip TRUE
SET_CAR_ONLY_DAMAGED_BY_PLAYER ryders_car TRUE
CHANGE_CAR_COLOUR ryders_car 84 84
SET_CAR_CRUISE_SPEED ryders_car 5.0
CHANGE_BLIP_DISPLAY r3_ryders_car_blip NEITHER

SET_CAR_STRAIGHT_LINE_DISTANCE ryders_car 250

CREATE_CHAR_AS_PASSENGER ryders_car PEDTYPE_MISSION1 SPECIAL01 0 ryder

SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE

UNLOAD_SPECIAL_CHARACTER 1

SET_CHAR_HEADING ryder 121.9883
TASK_STAY_IN_SAME_PLACE ryder TRUE
SET_CHAR_HEALTH	ryder 800
SET_CHAR_MAX_HEALTH ryder 800
SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
SET_CHAR_NEVER_TARGETTED ryder TRUE
GIVE_WEAPON_TO_CHAR	ryder WEAPONTYPE_MICRO_UZI 9999
SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
SET_ANIM_GROUP_FOR_CHAR ryder gang1
SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
															
SET_CHAR_DECISION_MAKER ryder r3_tough

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_MISSION2

LVAR_INT gun_index new_guns[10] r3_grove_car r3_lsv_car

gun_index = 0

WHILE gun_index < 10

	new_guns[gun_index] = -1

	gun_index++

ENDWHILE

gun_index = 0

VAR_INT collected_guns
collected_guns = 0

VAR_INT r3_total_guns
r3_total_guns = 10

LVAR_INT goons_flag
goons_flag = 0

LVAR_INT stored_check_index
stored_check_index = -1

LVAR_INT power_bar_increment
power_bar_increment = 5

IF flag_player_on_mission = 0
	CREATE_OBJECT_NO_OFFSET GUNBOX x y z new_guns[gun_index]
ENDIF

r3_mission_flag = 0

SWITCH_ROADS_OFF 2284.0 -1151.0 10.0 2288.0 -1147.0 30.0

SWITCH_ROADS_OFF 2287.3213 -1132.5714 10.0000 2360.8386 -1177.1337 40.0000 

SWITCH_ROADS_OFF 2311.6763 -1375.3975 10.0000 2292.3650 -1303.1021 40.0000 
																    
SWITCH_ROADS_OFF 2268.6953 -1138.5408 10.0000 2282.3616 -1159.6042 40.0000 

SWITCH_ROADS_OFF 2307.5366 -1162.6240 10.0000 2266.3511 -1136.9897 40.0000 

//SWITCH_PED_ROADS_OFF 2311.6538 -1165.1200 10.0000 2255.6228 -1134.2437 40.0000

//SWITCH_PED_ROADS_OFF 2284.0 -1151.0 10.0 2288.0 -1147.0 40.0

//SWITCH_PED_ROADS_OFF 2287.3213 -1132.5714 10.0000 2360.8386 -1177.1337 40.0000 

SWITCH_RANDOM_TRAINS OFF

SET_CREATE_RANDOM_GANG_MEMBERS FALSE

LVAR_INT failed_mission
failed_mission = 0

LVAR_INT box_collisions
box_collisions = 0

LVAR_INT crate_model
crate_model = CR_GUNCRATE

TIMERA = 0

SET_WANTED_MULTIPLIER 0.0

ENDIF

LVAR_INT r3_crt_0

CREATE_OBJECT_NO_OFFSET tmp_bin 2295.3909 -1129.8121 26.4000 r3_crt_0

SET_OBJECT_HEADING r3_crt_0 187.2863 

mission_ryder3_loop://///////////////////////////////////////////////////////////////////////////

IF NOT IS_CHAR_DEAD scplayer

	TASK_ENTER_CAR_AS_DRIVER scplayer ryders_car 5000

ENDIF

SET_FIXED_CAMERA_POSITION 2473.6416 -1674.0046 13.6679 0.0 0.0 0.0 // High bike
POINT_CAMERA_AT_POINT 2473.8325 -1674.9689 13.4843 JUMP_CUT

GOSUB r3_fade_in

PRINT_NOW ( RYD3_Z ) 6000 1 // ~s~Drive ~b~Ryder's Truck ~s~to the ~y~ammo train~s~.

TIMERB = 0
WHILE TIMERB < 4000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
	ENDIF
ENDWHILE

r3_skip_the_cut_initial:

GOSUB r3_restore_camera

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	GOSUB ryder3_keys

	GOSUB r3_play_sample

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

		GOTO mission_ryder3_passed

	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2290.6499 -1148.7227 25.7508 275.0 275.0 200.0 FALSE

		SET_CAR_DENSITY_MULTIPLIER 0.0

	ELSE

		IF r3_mission_flag < 10

			SET_CAR_DENSITY_MULTIPLIER 1.0

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *							  		 Spawn that train  										*	
	// *																							*
	// **********************************************************************************************

	IF r3_spawn_train = 0

		IF HAS_MODEL_LOADED	FREIGHT
		AND HAS_MODEL_LOADED FREIFLAT

			LVAR_INT ammo_train
		  	CREATE_MISSION_TRAIN 10 2278.1770 -1144.8823 27.5107 1 ammo_train
		   	LOCK_CAR_DOORS ammo_train CARLOCK_LOCKED
			SET_TRAIN_SPEED ammo_train 0.0
			SET_TRAIN_CRUISE_SPEED ammo_train 0.0
			FREEZE_CAR_POSITION	ammo_train TRUE

			GET_TRAIN_CABOOSE ammo_train ammo_train_rear

			CREATE_OBJECT ammotrn_obj 2278.1770 -1144.8823 27.5107 r3_caboose 

			ATTACH_OBJECT_TO_CAR r3_caboose ammo_train_rear 0.0 0.0 0.0 0.0 0.0 0.0 

			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_a 

			ATTACH_OBJECT_TO_CAR r3_crate_a ammo_train_rear 0.5 -5.5 -0.8 0.0 0.0 145.0 

			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_b 

			ATTACH_OBJECT_TO_CAR r3_crate_b ammo_train_rear 0.5 6.0 -0.8 0.0 0.0 60.0 

			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_c 

			ATTACH_OBJECT_TO_CAR r3_crate_c ammo_train_rear -0.5 8.0 -0.8 0.0 0.0 130.0 

				CREATE_OBJECT CR_GUNCRATE 2287.7437 -1131.7500 25.7431  r3_ammo_crate[0]
										
			SET_OBJECT_HEADING r3_ammo_crate[0] 85.7739  	 

				CREATE_OBJECT CR_GUNCRATE 2287.3584 -1131.0911 25.7449 r3_ammo_crate[1]
										
			SET_OBJECT_HEADING r3_ammo_crate[1] 209.4727 

				CREATE_OBJECT CR_GUNCRATE 2287.6646 -1127.0555 25.8118 r3_ammo_crate[2]
										
			SET_OBJECT_HEADING r3_ammo_crate[2] 97.4572 

				CREATE_OBJECT CR_GUNCRATE 2287.4309 -1125.5863 25.8256 r3_ammo_crate[3]
										
			SET_OBJECT_HEADING r3_ammo_crate[3] 176.9109

			r3_spawn_train = 1
					
			// ----------------------------------------------------------------------------------------------

			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1

			LVAR_INT r3_enemy[6] r3_enemy_inv[6] r3_dead_grove r3_dead_lsv

			//Enemy crouching  
			CREATE_CHAR PEDTYPE_CIVMALE FAM2 2291.9690 -1131.6296 25.7468 r3_dead_grove
			SET_CHAR_HEADING r3_dead_grove 188.6236
			TASK_PLAY_ANIM_NON_INTERRUPTABLE r3_dead_grove KO_shot_front PED 2.0 FALSE FALSE FALSE TRUE -1
			SET_CHAR_COLLISION r3_dead_grove FALSE
			SET_CHAR_NEVER_TARGETTED r3_dead_grove TRUE
			SET_CHAR_DECISION_MAKER r3_dead_grove r3_empty

			//Enemy crouching  
			CREATE_CHAR PEDTYPE_CIVMALE LSV1 2290.6602 -1146.0228 25.7507 r3_dead_lsv
			SET_CHAR_HEADING r3_dead_lsv 180.7254
			TASK_PLAY_ANIM_NON_INTERRUPTABLE r3_dead_lsv KO_shot_front PED 2.0 FALSE FALSE FALSE TRUE -1
			SET_CHAR_COLLISION r3_dead_lsv FALSE
			SET_CHAR_NEVER_TARGETTED r3_dead_lsv TRUE
			SET_CHAR_DECISION_MAKER r3_dead_lsv r3_empty

			// ----------------------------------------------------------------------------------------------

			//Enemy crouching  
			CREATE_CHAR PEDTYPE_MISSION2 LSV1 2295.6833 -1128.6821 25.8647 r3_enemy[0]
			SET_CHAR_HEADING r3_enemy[0] 175.3117
			TASK_STAY_IN_SAME_PLACE r3_enemy[0] TRUE
			SET_CHAR_IS_TARGET_PRIORITY r3_enemy[0] TRUE

			//Enemy standing
			CREATE_CHAR PEDTYPE_MISSION2 LSV2 2289.1404 -1131.1329 25.7539 r3_enemy[1]
			SET_CHAR_HEADING r3_enemy[1] 190.0070
			TASK_STAY_IN_SAME_PLACE r3_enemy[1] TRUE
			SET_CHAR_IS_TARGET_PRIORITY r3_enemy[1] TRUE

			//Enemy standing
			CREATE_CHAR PEDTYPE_MISSION2 LSV2 2293.7415 -1130.3425 25.8004 r3_enemy[2]
			SET_CHAR_HEADING r3_enemy[2] 178.0093 
			TASK_TOGGLE_DUCK r3_enemy[2] TRUE
			TASK_STAY_IN_SAME_PLACE r3_enemy[2] TRUE
			SET_CHAR_IS_TARGET_PRIORITY r3_enemy[2] TRUE

			//Enemy on the train			    
			CREATE_CHAR PEDTYPE_MISSION2 LSV3 2285.5225 -1135.3694 28.0000 r3_enemy[3]
			SET_CHAR_HEADING r3_enemy[3] 202.6963
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER r3_enemy[3] TRUE
			TASK_STAY_IN_SAME_PLACE r3_enemy[3] TRUE
			SET_CHAR_IS_TARGET_PRIORITY r3_enemy[3] TRUE

			// ----------------------------------------------------------------------------------------------

			LVAR_INT r3_goon[4]

			//First guy Crouching
			CREATE_CHAR PEDTYPE_MISSION1 FAM1 2296.2817 -1158.1842 25.6529 r3_goon[0]
			SET_CHAR_HEADING r3_goon[0] 9.0020
			TASK_TOGGLE_DUCK r3_goon[0] TRUE
			TASK_STAY_IN_SAME_PLACE r3_goon[0] TRUE

			//Second guy standing
			CREATE_CHAR PEDTYPE_MISSION1 FAM2 2287.5237 -1150.0411 25.7574 r3_goon[1]
			SET_CHAR_HEADING r3_goon[1] 347.6220
			TASK_STAY_IN_SAME_PLACE r3_goon[1] TRUE
			TASK_TOGGLE_DUCK r3_goon[1] TRUE

			//Third guy standing
			CREATE_CHAR PEDTYPE_MISSION1 FAM1 2301.2568 -1157.3722 25.5970 r3_goon[2]
			SET_CHAR_HEADING r3_goon[2] 9.0020
			TASK_STAY_IN_SAME_PLACE r3_goon[2] TRUE

			// ----------------------------------------------------------------------------------------------
	
		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *							   PLAYER ENTERS CAR GUYS DRIVE OFF 							*	
	// *																							*
	// **********************************************************************************************

	IF NOT IS_CAR_DEAD ryders_car
	AND NOT IS_CHAR_DEAD ryder

		IF IS_CHAR_IN_CAR scplayer ryders_car
		AND r3_mission_flag = 0

			TIMERB = 0

			SET_RADIO_CHANNEL 5

			SWITCH_RANDOM_TRAINS OFF

			goons_flag = 4

			r3_mission_flag ++

			CHANGE_BLIP_DISPLAY r3_ryders_car_blip NEITHER

			ADD_BLIP_FOR_COORD 2278.1770 -1144.8823 27.5107 mission_blip
			SET_COORD_BLIP_APPEARANCE mission_blip COORD_BLIP_APPEARANCE_NORMAL

			r3_switch = 1

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *							    SPAWN TRAIN AND THREE GUYS									*
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 1
	OR r3_mission_flag = 2

		IF NOT IS_CAR_DEAD ryders_car
		AND NOT IS_CHAR_DEAD ryder

			IF IS_CHAR_IN_CAR scplayer ryders_car

				IF NOT r3_switch = 1

					CHANGE_BLIP_DISPLAY r3_ryders_car_blip NEITHER
					CHANGE_BLIP_DISPLAY mission_blip BOTH

					PRINT_NOW ( RYD3_Z ) 6000 1 // ~s~Drive ~b~Ryder's Truck ~s~to the ~y~ammo train~s~.

					r3_switch = 1

				ENDIF

			ELSE

				IF r3_switch = 1

					IF NOT IS_CHAR_DEAD ryder

						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE

					ENDIF

					IF NOT IS_CHAR_DEAD scplayer

						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

					ENDIF

					CHANGE_BLIP_DISPLAY mission_blip NEITHER
					CHANGE_BLIP_DISPLAY r3_ryders_car_blip BOTH

					GENERATE_RANDOM_INT_IN_RANGE 0 3 r3_rnd
					
					SWITCH r3_rnd

						CASE 0

							$r3_print = &RYD3_NA	// Hey, CJ, don't you run out on me!
							r3_audio = SOUND_RYD3_NA
							GOSUB r3_load_sample

						BREAK

						CASE 1

							$r3_print = &RYD3_NB	// You getting the buster itch again?
							r3_audio = SOUND_RYD3_NB
							GOSUB r3_load_sample

						BREAK

						CASE 2

							$r3_print = &RYD3_NC	// Don't you bust out on me, CJ!
							r3_audio = SOUND_RYD3_NC
							GOSUB r3_load_sample

						BREAK

					ENDSWITCH

					r3_switch = 2

				ENDIF

				IF r3_playing = 2
				AND r3_switch = 2 

					PRINT_NOW ( RYD3_Q ) 4000 1 // Get back into ~b~Ryder's truck~s~!

					r3_switch = 0

				ENDIF

				r3_converse_good = 1
			
			ENDIF

		ENDIF

	ENDIF

	IF r3_mission_flag = 1

	   	IF r3_converse_good = 0

			IF r3_playing = 2
			AND r3_time_left = 0

				IF NOT IS_CHAR_DEAD ryder

					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

				ENDIF
				   
				$r3_print = &RYD3_AD	// Cool, you drive CJ, seeing as you’s ‘Mister Driver’.
				r3_audio = SOUND_RYD3_AD
				GOSUB r3_load_sample

				r3_time_left = 1
			ENDIF

			IF r3_playing = 2
			AND r3_time_left = 1

				$r3_print = &RYD3_AE	// Not this shit again...
				r3_audio = SOUND_RYD3_AE
				GOSUB r3_load_sample

				r3_time_left = 2

			ENDIF

			IF r3_playing = 2
			AND r3_time_left = 2

				$r3_print = &RYD3_BA	// Don’t give me a hard time about my driving, I ain’t got the energy.
				r3_audio = SOUND_RYD3_BA
				GOSUB r3_load_sample

				r3_time_left = 3
				
			ENDIF
				
			IF r3_playing = 2
			AND r3_time_left = 3

				$r3_print = &RYD3_BB	// Well, don’t go rolling us over and over in flames, then.
				r3_audio = SOUND_RYD3_BB
				GOSUB r3_load_sample
	
				r3_time_left = 4

			ENDIF	
				
			IF r3_playing = 2
			AND r3_time_left = 4

				$r3_print = &RYD3_BC	// I ain’t risin’ to it, man.
				r3_audio = SOUND_RYD3_BC
				GOSUB r3_load_sample

				r3_time_left = 5

			ENDIF	

			IF r3_playing = 2
			AND r3_time_left = 5

				$r3_print = &RYD3_BD	// Good, means you’ll concentrate on the road!
				r3_audio = SOUND_RYD3_BD
				GOSUB r3_load_sample

				r3_time_left = 6

			ENDIF	

			IF r3_playing = 2
			AND r3_time_left = 6

				$r3_print = &RYD3_BE	// Shit, man, you love to give a homie a hard time.
				r3_audio = SOUND_RYD3_BE
				GOSUB r3_load_sample

				r3_time_left = 7

			ENDIF

			IF r3_playing = 2
			AND r3_time_left = 7

				$r3_print = &RYD3_BG	// Just trying to keep my soldiers alive!	
				r3_audio = SOUND_RYD3_BG
				GOSUB r3_load_sample

				r3_time_left = 8

			ENDIF

			IF r3_playing = 2
			AND r3_time_left = 8

				$r3_print = &RYD3_BH	// By nagging them to death?	
				r3_audio = SOUND_RYD3_BH
				GOSUB r3_load_sample

				r3_time_left = 9

			ENDIF

			IF r3_playing = 2
			AND r3_time_left = 9

				$r3_print = &RYD3_BJ	// The road, motherfucker, the road!	
				r3_audio = SOUND_RYD3_BJ
				GOSUB r3_load_sample

				IF NOT IS_CHAR_DEAD ryder

					SHUT_CHAR_UP ryder FALSE

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					SHUT_CHAR_UP scplayer FALSE

				ENDIF

				r3_time_left = 10

			ENDIF	

	  	ENDIF

  		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2290.6499 -1148.7227 25.7508 200.0 200.0 200.0 FALSE
  		AND r3_spawn_train = 1

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD r3_enemy[v]

					TASK_STAY_IN_SAME_PLACE r3_enemy[v] TRUE

					ADD_BLIP_FOR_CHAR r3_enemy[v] r3_enemy_inv[v]

					IF v = 3
					OR v = 0

						SET_CHAR_WEAPON_SKILL r3_enemy[v] WEAPONSKILL_PRO

						GIVE_WEAPON_TO_CHAR r3_enemy[v] WEAPONTYPE_PISTOL 9999

					ELSE

					   	GIVE_WEAPON_TO_CHAR r3_enemy[v] WEAPONTYPE_MICRO_UZI 9999

					  	SET_CURRENT_CHAR_WEAPON r3_enemy[v] WEAPONTYPE_MICRO_UZI
					
					ENDIF		

					SET_CHAR_SHOOT_RATE r3_enemy[v] 50

					SET_CHAR_ACCURACY r3_enemy[v] 50

					SET_CHAR_DECISION_MAKER r3_enemy[v] r3_tough

				ENDIF

			ENDREPEAT

			// *************************************************************************************
			// *																				   *
			// *						       Grove Gang at Train								   *
			// *																				   *
			// *************************************************************************************

			REPEAT 3 v

				IF NOT IS_CHAR_DEAD r3_goon[v]

					SET_CHAR_NEVER_TARGETTED r3_goon[v] TRUE

					TASK_STAY_IN_SAME_PLACE r3_goon[v] TRUE

					IF  v = 1 
											
						SET_CHAR_HEALTH r3_goon[v] 200
								
						SET_CHAR_MAX_HEALTH r3_goon[v] 200

					ELSE											

						SET_CHAR_HEALTH r3_goon[v] 50
								
						SET_CHAR_MAX_HEALTH r3_goon[v] 50

					ENDIF

					IF v = 1

						SET_CHAR_WEAPON_SKILL r3_goon[v] WEAPONSKILL_PRO

						GIVE_WEAPON_TO_CHAR r3_goon[v] WEAPONTYPE_PISTOL 9999

					ELSE

					   	GIVE_WEAPON_TO_CHAR r3_goon[v] WEAPONTYPE_MICRO_UZI 9999

					  	SET_CURRENT_CHAR_WEAPON r3_goon[v] WEAPONTYPE_MICRO_UZI
					
					ENDIF		

					SET_CHAR_SHOOT_RATE r3_goon[v] 70

					SET_CHAR_ACCURACY r3_goon[v] 70

					SET_CHAR_DECISION_MAKER r3_goon[v] r3_tough

				ENDIF

			ENDREPEAT

			r3_spawn_train = 2
			  
		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2290.6499 -1148.7227 25.7508 100.0 100.0 200.0 FALSE

			r3_mission_flag ++

			TIMERB = 0

			r3_time_left = 0
			  
		ENDIF

	ENDIF  

	// **********************************************************************************************
	// *																							*
	// *									DRIVING TO THE TRAIN 									*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 2

		REPEAT 4 v

			IF IS_CHAR_DEAD r3_enemy[v]
			AND NOT r3_enemy[v] = 0

				IF DOES_BLIP_EXIST r3_enemy_inv[v]

					REMOVE_BLIP r3_enemy_inv[v]

					r3_enemy_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED r3_enemy[v]

				ENDIF

			ENDIF

		ENDREPEAT

		IF NOT IS_CAR_DEAD ryders_car
		AND NOT IS_CHAR_DEAD ryder

			IF IS_CHAR_IN_CAR scplayer ryders_car

			//	r3_converse_good = 0

			ELSE

				r3_converse_good_1 = 1
			
			ENDIF

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2290.6499 -1148.7227 25.7508 100.0 100.0 100.0 FALSE
		AND r3_inside_zone = 0
				
			IF NOT IS_CHAR_DEAD r3_goon[1]
				
				OPEN_SEQUENCE_TASK r3_sequence_task
													 
					TASK_STAY_IN_SAME_PLACE -1 FALSE

					TASK_TOGGLE_DUCK -1 TRUE

					TASK_GO_STRAIGHT_TO_COORD -1 2291.7244 -1138.6476 25.6745 PEDMOVE_RUN -2

					TASK_STAY_IN_SAME_PLACE -1 TRUE

					IF NOT IS_CHAR_DEAD r3_enemy[0]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[0] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[1]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[1] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[2]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[2] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[3]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[3] 
					ENDIF

				CLOSE_SEQUENCE_TASK r3_sequence_task

				CLEAR_CHAR_TASKS_IMMEDIATELY r3_goon[1]

				PERFORM_SEQUENCE_TASK r3_goon[1] r3_sequence_task

				CLEAR_SEQUENCE_TASK r3_sequence_task

			ENDIF

			IF NOT IS_CHAR_DEAD r3_goon[0]
				
				OPEN_SEQUENCE_TASK r3_sequence_task
									 
					TASK_STAY_IN_SAME_PLACE -1 FALSE

					TASK_TOGGLE_DUCK -1 TRUE
					
					TASK_GO_STRAIGHT_TO_COORD -1 2297.5330 -1137.5785 25.8778 PEDMOVE_RUN -2
					
					TASK_STAY_IN_SAME_PLACE -1 TRUE

					IF NOT IS_CHAR_DEAD r3_enemy[0]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[0] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[1]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[1] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[2]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[2] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[3]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[3] 
					ENDIF

				CLOSE_SEQUENCE_TASK r3_sequence_task

				CLEAR_CHAR_TASKS_IMMEDIATELY r3_goon[0]

				PERFORM_SEQUENCE_TASK r3_goon[0] r3_sequence_task

				CLEAR_SEQUENCE_TASK r3_sequence_task

			ENDIF

			IF NOT IS_CHAR_DEAD r3_goon[2]
				
				OPEN_SEQUENCE_TASK r3_sequence_task
									 
					TASK_STAY_IN_SAME_PLACE -1 FALSE

					TASK_TOGGLE_DUCK -1 TRUE

					TASK_GO_STRAIGHT_TO_COORD -1 2290.1179 -1141.7802 25.7024 PEDMOVE_RUN -2

					FLUSH_ROUTE 
					
					IF NOT IS_CHAR_DEAD r3_enemy[0]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[0] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[1]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[1] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[2]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[2] 
					ENDIF
					IF NOT IS_CHAR_DEAD r3_enemy[3]
						TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[3] 
					ENDIF

				CLOSE_SEQUENCE_TASK r3_sequence_task

				CLEAR_CHAR_TASKS_IMMEDIATELY r3_goon[2]

				PERFORM_SEQUENCE_TASK r3_goon[2] r3_sequence_task

				CLEAR_SEQUENCE_TASK r3_sequence_task

			ENDIF

			r3_inside_zone = 1

			TIMERB = 0

		ENDIF

		   	IF r3_converse_good_1 = 0
			AND r3_inside_zone = 1

				IF r3_playing = 2
				AND r3_time_left = 0

					$r3_print = &RYD3_CA	// Here we are.
					r3_audio = SOUND_RYD3_CA
					GOSUB r3_load_sample

					r3_time_left = 1

				ENDIF
					
				IF r3_playing = 2
				AND r3_time_left = 1

					$r3_print = &RYD3_CB	// That’s our train alright.
					r3_audio = SOUND_RYD3_CB
					GOSUB r3_load_sample

					r3_time_left = 2

				ENDIF
					
		  	ENDIF

		IF NOT IS_CAR_DEAD ryders_car
		AND NOT IS_CHAR_DEAD scplayer

			IF NOT IS_CHAR_IN_CAR scplayer ryders_car

				PRINT_NOW ( RYD3_Q ) 5000 1 // What are you doing? Get back into the car!

				IF r3_get_back = 0

					REMOVE_BLIP mission_blip

					ADD_BLIP_FOR_CAR ryders_car mission_blip

					SET_BLIP_AS_FRIENDLY mission_blip TRUE

					r3_get_back = 1

				ENDIF

			ELSE
				
				IF r3_get_back = 1

					REMOVE_BLIP mission_blip

					ADD_BLIP_FOR_COORD 2278.1770 -1144.8823 27.5107 mission_blip

					SET_COORD_BLIP_APPEARANCE mission_blip COORD_BLIP_APPEARANCE_NORMAL

					r3_get_back = 0

				ENDIF

			ENDIF

		ENDIF

		// **********************************************************************************************
		// *																							*
		// *								PLAYER ARRIVES AT THE TRAIN		 							*	
		// *																							*
		// **********************************************************************************************

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD ryders_car
		
			IF LOCATE_STOPPED_CAR_3D ryders_car 2289.5911 -1135.6255 25.6863 40.0 40.0 40.0 FALSE

				REMOVE_BLIP mission_blip

				IF DOES_BLIP_EXIST r3_ryders_car_blip

					CHANGE_BLIP_DISPLAY r3_ryders_car_blip NEITHER

				ENDIF

				IF NOT IS_CHAR_DEAD r3_enemy[0]
				OR NOT IS_CHAR_DEAD r3_enemy[1]
				OR NOT IS_CHAR_DEAD r3_enemy[2]
				OR NOT IS_CHAR_DEAD r3_enemy[3]

					$r3_print = &RYD3_CC	// Shit, looks like some Northside Vagos got there first!
					r3_audio = SOUND_RYD3_CC
					GOSUB r3_load_sample

				ENDIF

				TIMERA = 0

				IF NOT IS_CHAR_DEAD ryder

					IF NOT IS_CAR_DEAD ryders_car
						IF IS_CHAR_IN_CAR ryder ryders_car

							LVAR_INT r3_seq_tracks

							OPEN_SEQUENCE_TASK r3_seq_tracks

								TASK_LEAVE_CAR -1 ryders_car	

								TASK_TOGGLE_DUCK -1 TRUE

								TASK_GO_TO_COORD_ANY_MEANS -1 2295.1042 -1148.0286 25.7612 PEDMOVE_RUN -1

								TASK_TOGGLE_PED_THREAT_SCANNER -1 TRUE TRUE TRUE

								IF NOT IS_CHAR_DEAD r3_enemy[0]
									TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[0] 
								ENDIF
								IF NOT IS_CHAR_DEAD r3_enemy[1]
									TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[1] 
								ENDIF
								IF NOT IS_CHAR_DEAD r3_enemy[2]
									TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[2] 
								ENDIF
								IF NOT IS_CHAR_DEAD r3_enemy[3]
									TASK_KILL_CHAR_ON_FOOT -1 r3_enemy[3] 
								ENDIF

							CLOSE_SEQUENCE_TASK r3_seq_tracks

							CLEAR_CHAR_TASKS ryder

							PERFORM_SEQUENCE_TASK ryder r3_seq_tracks
							
						ENDIF
					ENDIF

				ENDIF

				r3_mission_flag ++

			 ENDIF
		ENDIF

	ENDIF

	IF r3_mission_flag = 3
	AND TIMERA > 4000

		LVAR_INT r3_sequence_task

		IF NOT IS_CHAR_DEAD r3_enemy[1]
  		
  			TASK_STAY_IN_SAME_PLACE r3_enemy[1] FALSE

		ENDIF

		IF NOT IS_CHAR_DEAD r3_enemy[0]
		OR NOT IS_CHAR_DEAD r3_enemy[1]
		OR NOT IS_CHAR_DEAD r3_enemy[2]
		OR NOT IS_CHAR_DEAD r3_enemy[3]

			PRINT_NOW ( RYD3_M ) 5000 1	// ~s~Shoot the ~r~Vagos ~s~robbing the train!

		ENDIF
	
		r3_mission_flag ++
		
	ENDIF

	IF r3_mission_flag = 4

		REPEAT 4 v

			IF IS_CHAR_DEAD r3_enemy[v]
			AND NOT r3_enemy[v] = 0

				IF DOES_BLIP_EXIST r3_enemy_inv[v]

					REMOVE_BLIP r3_enemy_inv[v]

					r3_enemy_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED r3_enemy[v]

				ENDIF

			ENDIF

		ENDREPEAT

		IF IS_CHAR_DEAD r3_enemy[0]
		AND IS_CHAR_DEAD r3_enemy[1]
		AND IS_CHAR_DEAD r3_enemy[2]
		AND IS_CHAR_DEAD r3_enemy[3]

			IF r3_wait = 0

				TIMERA = 0

				r3_wait = 1

			ENDIF

		ENDIF

		IF r3_wait = 1
		AND TIMERA > 2000

			r3_mission_flag ++

			IF NOT IS_CHAR_DEAD ryder

				PERFORM_SEQUENCE_TASK ryder r3_get_onto_train
				
			ENDIF

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								  The Ballas Car arrives			 		   				*	
	// *																							*
	// **********************************************************************************************
		
	IF r3_mission_flag = 5


		TIMERA = 0

		r3_mission_flag ++
		
	ENDIF

	// **********************************************************************************************
	// *																							*
	// *						      	BALLAS ARRIVES AT THE TRAIN 								*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 6
		
		GOSUB r3_fade_out
		
		REQUEST_MODEL GREENWOO
		REQUEST_MODEL micro_uzi
		REQUEST_MODEL ballas1
		REQUEST_MODEL ballas3
		REQUEST_MODEL COLT45

		WHILE NOT HAS_MODEL_LOADED GREENWOO
		OR NOT HAS_MODEL_LOADED micro_uzi
		OR NOT HAS_MODEL_LOADED ballas1
		OR NOT HAS_MODEL_LOADED ballas3
		OR NOT HAS_MODEL_LOADED COLT45
			WAIT 0
		ENDWHILE

		LVAR_INT gang2_car
		CREATE_CAR GREENWOO 2340.0549 -1151.4324 25.9546 gang2_car
		SET_CAR_HEADING gang2_car 92.6857  
		CAR_GOTO_COORDINATES gang2_car 2313.4141 -1150.0930 25.7997 
		SET_CAR_DRIVING_STYLE gang2_car 3
		SET_CAR_CRUISE_SPEED gang2_car 25.0


		LVAR_INT r3_ballas[4] r3_ballas_inv[4]
		 
		CREATE_CHAR_INSIDE_CAR gang2_car PEDTYPE_MISSION2 ballas1 r3_ballas[0]
		SET_CHAR_HEADING r3_ballas[0] 227.7265 

		CREATE_CHAR_AS_PASSENGER gang2_car PEDTYPE_MISSION2 ballas3 0 r3_ballas[1]
		SET_CHAR_HEADING r3_ballas[1] 301.1901 

		CREATE_CHAR_AS_PASSENGER gang2_car PEDTYPE_MISSION2 ballas1 1 r3_ballas[2]
		SET_CHAR_HEADING r3_ballas[2] 82.8733 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER r3_ballas[2] TRUE

		CREATE_CHAR_AS_PASSENGER gang2_car PEDTYPE_MISSION2 ballas3 2 r3_ballas[3]
		SET_CHAR_HEADING r3_ballas[3] 82.8733 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER r3_ballas[3] TRUE

		REPEAT 4 v

			ADD_BLIP_FOR_CHAR r3_ballas[v] r3_ballas_inv[v]

			IF v = 3

				SET_CHAR_WEAPON_SKILL r3_ballas[v] WEAPONSKILL_PRO

				GIVE_WEAPON_TO_CHAR r3_ballas[v] WEAPONTYPE_PISTOL 9999

			ELSE

			   	GIVE_WEAPON_TO_CHAR r3_ballas[v] WEAPONTYPE_MICRO_UZI 9999

			  	SET_CURRENT_CHAR_WEAPON r3_ballas[v] WEAPONTYPE_MICRO_UZI
			
			ENDIF		
																		
			SET_CHAR_HEALTH r3_ballas[v] 200
					
			SET_CHAR_MAX_HEALTH r3_ballas[v] 200

			SET_CHAR_SHOOT_RATE r3_ballas[v] 40

			SET_CHAR_ACCURACY r3_ballas[v] 40

			SET_CHAR_DECISION_MAKER r3_ballas[v] r3_tough

			SET_CHAR_IS_TARGET_PRIORITY r3_ballas[v] TRUE

		ENDREPEAT 

		GOSUB r3_set_camera
				
		LOAD_SCENE_IN_DIRECTION 2287.2961 -1140.3798 25.6779 252.6459  

		SET_FIXED_CAMERA_POSITION 2283.1445 -1135.6521 28.6919 0.0 0.0 0.0

		IF NOT IS_CAR_DEAD gang2_car

			POINT_CAMERA_AT_CAR gang2_car FIXED JUMP_CUT

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
			IF NOT IS_CHAR_IN_ANY_CAR scplayer

				TASK_TURN_CHAR_TO_FACE_COORD scplayer 2313.4141 -1150.0930 25.7997

			ENDIF
		ENDIF
		REPEAT 3 v

			IF NOT IS_CHAR_DEAD r3_goon[v]
				IF NOT IS_CHAR_IN_ANY_CAR r3_goon[v]

					TASK_TURN_CHAR_TO_FACE_COORD r3_goon[v] 2313.4141 -1150.0930 25.7997

					TASK_STAY_IN_SAME_PLACE r3_goon[v] TRUE

				ENDIF
			ENDIF

		ENDREPEAT

		LVAR_INT r3_sequence

		IF NOT IS_CHAR_DEAD ryder

			OPEN_SEQUENCE_TASK r3_sequence

				TASK_TOGGLE_DUCK -1 TRUE

				TASK_GO_STRAIGHT_TO_COORD -1 2295.6929 -1142.4689 25.8392 PEDMOVE_RUN -2

				TASK_TURN_CHAR_TO_FACE_COORD -1 2313.4141 -1150.0930 25.7997
								
				IF NOT IS_CHAR_DEAD r3_ballas[0]
					TASK_KILL_CHAR_ON_FOOT -1 r3_ballas[0] 
				ENDIF
				IF NOT IS_CHAR_DEAD r3_ballas[1]
					TASK_KILL_CHAR_ON_FOOT -1 r3_ballas[1] 
				ENDIF
				IF NOT IS_CHAR_DEAD r3_ballas[2]
					TASK_KILL_CHAR_ON_FOOT -1 r3_ballas[2] 
				ENDIF
				IF NOT IS_CHAR_DEAD r3_ballas[3]
					TASK_KILL_CHAR_ON_FOOT -1 r3_ballas[3] 
				ENDIF

			CLOSE_SEQUENCE_TASK r3_sequence

			IF NOT IS_CHAR_IN_ANY_CAR ryder

				CLEAR_CHAR_TASKS_IMMEDIATELY ryder

				PERFORM_SEQUENCE_TASK ryder r3_sequence			

			ENDIF
			
			CLEAR_SEQUENCE_TASK r3_sequence	

		ENDIF

		GOSUB r3_fade_in 

		PRINT_NOW ( RYD3_2 ) 3000 1 // ~s~Protect the train from the ~r~flats!

		WAIT 1000

		TIMERB = 0
		WHILE TIMERB < 3000
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
				GOTO r3_skip_the_cut_0
			ENDIF
		ENDWHILE

		r3_skip_the_cut_0:

		GOSUB r3_restore_camera

		PRINT_NOW ( RYD3_DA ) 3000 1 // We got Ballas trying to crash the party!

		$r3_print = &RYD3_DA	// We got Ballas trying to crash the party!
		r3_audio = SOUND_RYD3_DA
		GOSUB r3_load_sample

		r3_time_left = 0
		
		TIMERB = 0

		r3_mission_flag ++

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								   MAKE FLATS LEAVE CAR			 							*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 7

		IF NOT IS_CAR_DEAD gang2_car		   
			IF LOCATE_STOPPED_CAR_2D gang2_car 2304.1821 -1142.6194 15.0 15.0 0
				
				TASK_EVERYONE_LEAVE_CAR	gang2_car
				
				r3_mission_flag ++

			ENDIF
		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								TURN OFF BLIPS WHEN FLAT DIES								*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 8

		REPEAT 4 v

			IF IS_CHAR_DEAD r3_ballas[v]
			AND NOT r3_ballas[v] = 0

				IF DOES_BLIP_EXIST r3_ballas_inv[v]

					REMOVE_BLIP r3_ballas_inv[v]

					r3_ballas_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED r3_ballas[v]

				ENDIF

			ENDIF

		ENDREPEAT

		IF TIMERB > 3000 
		AND r3_time_left = 0

			$r3_print = &RYD3_DB	// Looks like Tenpennny told every gang in South Central!
			r3_audio = SOUND_RYD3_DB
			GOSUB r3_load_sample
	
			IF NOT IS_CHAR_DEAD	r3_ballas[0]
				TASK_GO_TO_COORD_ANY_MEANS r3_ballas[0] 2299.2844 -1133.9290 27.3281 PEDMOVE_RUN -1
			ENDIF

			IF NOT IS_CHAR_DEAD	r3_ballas[1]
				TASK_GO_TO_COORD_ANY_MEANS r3_ballas[1] 2291.2261 -1140.1689 25.6912 PEDMOVE_RUN -1
			ENDIF

			IF NOT IS_CHAR_DEAD	r3_ballas[2]
				TASK_GO_TO_COORD_ANY_MEANS r3_ballas[2] 2298.0903 -1144.0359 25.9190 PEDMOVE_RUN -1
			ENDIF

			IF NOT IS_CHAR_DEAD	r3_ballas[3]
				TASK_GO_TO_COORD_ANY_MEANS r3_ballas[3] 2291.8420 -1129.2198 25.7756 PEDMOVE_RUN -1
			ENDIF

			r3_time_left = 1 			

		ENDIF

		IF r3_playing = 2 
		AND r3_time_left = 1

			$r3_print = &RYD3_DC	// Ice those Ballassholes!
			r3_audio = SOUND_RYD3_DC
			GOSUB r3_load_sample
			
			r3_time_left = 2 			

		ENDIF

		IF IS_CHAR_DEAD r3_ballas[0]
		AND IS_CHAR_DEAD r3_ballas[1]
		AND IS_CHAR_DEAD r3_ballas[2]
		AND IS_CHAR_DEAD r3_ballas[3]

			r3_mission_flag ++

			ADD_BLIP_FOR_COORD 2287.1472 -1121.7023 25.8903 f4_train_rear_blip
			SET_COORD_BLIP_APPEARANCE f4_train_rear_blip COORD_BLIP_APPEARANCE_NORMAL

			$r3_print = &RYD3_EA	// Go check out the train, CJ.
			r3_audio = SOUND_RYD3_EA
			GOSUB r3_load_sample

			r3_time_left = 0

		   	IF NOT IS_CHAR_DEAD	ryder

				// --------------------------------------------------------------------------------

				LVAR_INT r3_seq_train_goto_a r3_seq_goto_the_train

				OPEN_SEQUENCE_TASK r3_seq_goto_the_train

					TASK_ACHIEVE_HEADING -1 77.6187 

					TASK_PLAY_ANIM -1 IDLE_armed PED 1.0 FALSE FALSE FALSE FALSE -1
					
					TASK_PAUSE -1 2000			
	
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer

					TASK_PLAY_ANIM -1 RYD_Beckon_01 RYDER 1.0 FALSE FALSE FALSE FALSE -1

					SET_SEQUENCE_TO_REPEAT r3_seq_goto_the_train 1

				CLOSE_SEQUENCE_TASK r3_seq_goto_the_train

				// --------------------------------------------------------------------------------

				OPEN_SEQUENCE_TASK r3_seq_train_goto_a

					TASK_GO_STRAIGHT_TO_COORD -1 2294.8538 -1143.8496 25.8140 PEDMOVE_RUN -2

					TASK_GO_STRAIGHT_TO_COORD -1 2290.7161 -1122.9971 25.8774 PEDMOVE_RUN -2

					PERFORM_SEQUENCE_TASK -1 r3_seq_goto_the_train 

				CLOSE_SEQUENCE_TASK r3_seq_train_goto_a

				// --------------------------------------------------------------------------------

				CLEAR_CHAR_TASKS_IMMEDIATELY ryder

				PERFORM_SEQUENCE_TASK ryder r3_seq_train_goto_a	

				// --------------------------------------------------------------------------------
					
				CLEAR_SEQUENCE_TASK r3_seq_goto_the_train

				CLEAR_SEQUENCE_TASK r3_seq_train_goto_a

				// --------------------------------------------------------------------------------

			ENDIF

			TIMERB = 0

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								TRAIN LEAVES WITH THE PLAYER 								*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 9

		IF TIMERB > 4000
		AND r3_time_left = 0

			PRINT_NOW ( RYD3_1 ) 4000 1 // ~s~Climb up onto the ~y~Train ~s~before anyone else shows up

			r3_time_left = 1

	
		ENDIF

		IF LOCATE_CHAR_ON_FOOT_3D scplayer 2287.1472 -1121.7023 25.8903 1.2 1.2 3.0 TRUE

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

			ENDIF

			IF DOES_BLIP_EXIST f4_train_rear_blip

				REMOVE_BLIP f4_train_rear_blip

			ENDIF

			GOSUB r3_set_camera

			DELETE_CHAR r3_enemy[3]

			SET_PLAYER_DISPLAY_VITAL_STATS_BUTTON player1 FALSE

			SET_FIXED_CAMERA_POSITION 2288.8806 -1117.7576 28.0044 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2288.3003 -1118.5493 27.8136 JUMP_CUT

			IF NOT IS_CHAR_DEAD scplayer

				SET_CHAR_COORDINATES scplayer 2287.1472 -1121.7023 25.8903

				SET_CHAR_HEADING scplayer 91.8747 
				
				TASK_CLIMB scplayer TRUE
				
		   		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

				SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE

			ENDIF

			WAIT 1000

			GOSUB r3_fade_out

			IF NOT IS_CHAR_DEAD scplayer

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				IF NOT IS_CHAR_SITTING_IN_ANY_CAR scplayer

					SET_CHAR_COORDINATES scplayer 2284.7661 -1121.4998 27.3860

				ELSE

					WARP_CHAR_FROM_CAR_TO_COORD	scplayer 2284.7661 -1121.4998 27.3860

				ENDIF

				SET_CHAR_HEADING scplayer 220.0
			
			ENDIF

			REQUEST_CAR_RECORDING 486
			REQUEST_CAR_RECORDING 490

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 486
			OR NOT HAS_CAR_RECORDING_BEEN_LOADED 490
				WAIT 0
			ENDWHILE

			CLEAR_AREA 2284.7322 -1121.9163 27.0000 50.0 TRUE
						
			IF NOT IS_CAR_DEAD ryders_car

				LVAR_INT ryders_goon1

				CREATE_CHAR PEDTYPE_MISSION1 FAM1 2294.5537 -1156.3818 25.6646 ryders_goon1
				SET_CHAR_HEADING ryders_goon1 9.0020

				IF NOT IS_CHAR_DEAD ryders_goon1
					IF NOT IS_CHAR_IN_CAR ryders_goon1 ryders_car

						WARP_CHAR_INTO_CAR ryders_goon1 ryders_car
									
					ENDIF
				ENDIF

				IF IS_CHAR_DEAD ryders_goon1

					CREATE_CHAR_INSIDE_CAR ryders_car PEDTYPE_MISSION1 FAM1 ryders_goon1
						
					SET_CHAR_NEVER_TARGETTED ryders_goon1 TRUE

					GIVE_WEAPON_TO_CHAR	ryders_goon1 WEAPONTYPE_MICRO_UZI 9999

				ENDIF

				CLOSE_ALL_CAR_DOORS ryders_car

			ENDIF
			
			IF NOT IS_CAR_DEAD ryders_car
			AND NOT IS_CHAR_DEAD ryders_goon1

				ADD_BLIP_FOR_CAR ryders_car r3_catching_blip

				SET_BLIP_AS_FRIENDLY r3_catching_blip TRUE

				SET_CAR_HEALTH ryders_car 5000
		
				SET_CHAR_HEALTH ryders_goon1 500

			ENDIF

			DELETE_CHAR ryder

			IF NOT IS_CAR_DEAD ryders_car

				GET_CAR_COORDINATES	ryders_car x y z

			ENDIF

	   		CREATE_CHAR	PEDTYPE_MISSION1 SPECIAL01 x y z ryder
			
			SET_CHAR_HEALTH	ryder 500

			CLEAR_ALL_CHAR_RELATIONSHIPS ryder ACQUAINTANCE_TYPE_PED_HATE

			IF NOT IS_CAR_DEAD ryders_car

				ATTACH_CHAR_TO_CAR ryder ryders_car 0.0 -0.85 0.65 FACING_LEFT 360.0 WEAPONTYPE_MICRO_UZI

			ENDIF

			SET_CHAR_PROOFS	ryder TRUE TRUE FALSE TRUE TRUE

			TASK_TOGGLE_PED_THREAT_SCANNER ryder FALSE FALSE FALSE

			IF NOT IS_CHAR_DEAD ryders_goon1

				TASK_TOGGLE_PED_THREAT_SCANNER ryders_goon1 FALSE FALSE FALSE
				 
				SET_CHAR_DECISION_MAKER ryders_goon1 r3_empty

			ENDIF

			IF NOT IS_CAR_DEAD ryders_car

				START_PLAYBACK_RECORDED_CAR ryders_car 486
				
				PAUSE_PLAYBACK_RECORDED_CAR ryders_car
			
			ENDIF

			WAIT 800

			IF NOT IS_CAR_DEAD ammo_train

				FREEZE_CAR_POSITION ammo_train FALSE

				START_PLAYBACK_RECORDED_CAR ammo_train 490

			ENDIF

			DELETE_OBJECT r3_ammo_crate[0]
			DELETE_OBJECT r3_ammo_crate[1]
			DELETE_OBJECT r3_ammo_crate[2]
			DELETE_OBJECT r3_ammo_crate[3]

			REPEAT 3 v 

				DELETE_CHAR r3_goon[v]

			ENDREPEAT

			SET_CAR_DENSITY_MULTIPLIER 0.0

			SET_PED_DENSITY_MULTIPLIER 0.0
			
			LOAD_SCENE_IN_DIRECTION 2288.5164 -1136.4039 28.5557 149.2199  

			SET_FIXED_CAMERA_POSITION 2288.5164 -1136.4039 28.5557 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2288.2947 -1137.3680 28.4100 JUMP_CUT

			IF NOT IS_CHAR_DEAD scplayer

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				SET_CHAR_COORDINATES scplayer 2284.7661 -1121.4998 27.3860

				SET_CHAR_HEADING scplayer 0.0000
			
			ENDIF

			WAIT 200

			IF NOT IS_CAR_DEAD ryders_car
			AND IS_PLAYBACK_GOING_ON_FOR_CAR ryders_car
				
				UNPAUSE_PLAYBACK_RECORDED_CAR ryders_car
			
			ENDIF

			GOSUB r3_fade_in

			SET_PED_DENSITY_MULTIPLIER 0.0

			SET_CAR_DENSITY_MULTIPLIER 0.0

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_FA

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( RYD3_FA ) 2000 1 // Oh shit!
			
			WAIT 2000

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_FB

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( RYD3_FB ) 4000 1 // Stay put, CJ, we’re gonna be right behind you!

			WAIT 4000
	
			IF NOT IS_CAR_DEAD ammo_train

			 	SET_HEADING_FOR_ATTACHED_PLAYER player1 225.0000 225.0000 

				LVAR_INT ammo_train_rear

				GET_TRAIN_CABOOSE ammo_train ammo_train_rear

				ATTACH_CHAR_TO_CAR scplayer ammo_train_rear 0.0 -8.0 0.9 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED

				SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE

				SET_CHAR_COLLISION scplayer FALSE

			ENDIF

			GOSUB r3_restore_camera

			SET_CAR_DENSITY_MULTIPLIER 0.0

			DRAW_CROSSHAIR TRUE

			//SET_RADAR_ZOOM 50

			$r3_print = &RYD3_GA	// Throw me some boxes, CJ!
			r3_audio = SOUND_RYD3_GA
			GOSUB r3_load_sample

			TIMERB = 0

			r3_time_left = 0

			VAR_INT box_timer

			box_timer = 90000

			DISPLAY_ONSCREEN_TIMER_WITH_STRING box_timer TIMER_DOWN RYD3_3

			VAR_INT throw_power_bar

			throw_power_bar = 0

			goons_flag = 0

			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING throw_power_bar COUNTER_DISPLAY_BAR 1 RYD3_E

			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING r3_total_guns COUNTER_DISPLAY_NUMBER 2 RYD3_F

			IF NOT IS_CHAR_DEAD	ryder
				CLEAR_CHAR_TASKS ryder
			ENDIF

		   	r3_mission_flag ++

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
 	// *							     ON THE TRAIN THROWING BOXES								*	
	// *																							*
	// **********************************************************************************************

	IF r3_mission_flag = 10

		IF TIMERB > 5000
		AND r3_time_left = 0
			PRINT_HELP_FOREVER RYD3_D  
			r3_time_left = 1
		ENDIF 

		IF TIMERA > 8000

			IF NOT IS_CHAR_DEAD ryder
				
				CLEAR_CHAR_TASKS ryder

				GENERATE_RANDOM_INT_IN_RANGE 0 3 r3_rnd
					
				SWITCH r3_rnd

					CASE 0
                 	    TASK_PLAY_ANIM ryder RYD_Beckon_01 RYDER 1.0 FALSE FALSE FALSE FALSE -1
					BREAK

					CASE 1
						TASK_PLAY_ANIM ryder RYD_Beckon_02 RYDER 1.0 FALSE FALSE FALSE FALSE -1
					BREAK

					CASE 2
						TASK_PLAY_ANIM ryder RYD_Beckon_03 RYDER 1.0 FALSE FALSE FALSE FALSE -1
					BREAK
				ENDSWITCH

			ENDIF

			TIMERA = 0
		ENDIF
		

		IF NOT IS_CAR_DEAD ammo_train_rear

			IF NOT IS_CHAR_DEAD	ryder

				GET_CHAR_COORDINATES scplayer x y z
				GET_CHAR_COORDINATES ryder x2 y2 z2

				x -= x2
				y -= y2

				GET_HEADING_FROM_VECTOR_2D x y heading
				SET_CHAR_HEADING ryder heading

			ENDIF

			IF NOT collected_guns = 10

				IF IS_BUTTON_PRESSED PAD1 CIRCLE
				OR IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					throw_power_bar += power_bar_increment
					IF throw_power_bar > 100
						throw_power_bar = 100
						//power_bar_increment = -5
					ENDIF
					IF throw_power_bar < 0
						throw_power_bar = 0
						power_bar_increment = 50
					ENDIF
					LVAR_INT pad1_circle_pressed
					++ pad1_circle_pressed
					
				ELSE
				   	IF pad1_circle_pressed > 0
					
					IF throw_power_bar > 20

						LVAR_FLOAT throw_power
						throw_power =# throw_power_bar
						throw_power /= 2.0
						GET_ACTIVE_CAMERA_COORDINATES x y z
						GET_ACTIVE_CAMERA_POINT_AT x2 y2 z2

						IF DOES_OBJECT_EXIST new_guns[gun_index]
							DELETE_OBJECT new_guns[gun_index]
						ENDIF

						CREATE_OBJECT_NO_OFFSET crate_model x2 y2 z2 new_guns[gun_index]
						SET_OBJECT_RECORDS_COLLISIONS new_guns[gun_index] TRUE
						LVAR_INT new_guns_model[10]
						new_guns_model[gun_index] = crate_model
						x2 -= x
						y2 -= y
						z2 -= z
						x2 *= throw_power
						y2 *= throw_power
						z2 *= throw_power
						SET_OBJECT_DYNAMIC new_guns[gun_index] TRUE
						SET_OBJECT_COLLISION new_guns[gun_index] TRUE
						GET_CAR_SPEED_VECTOR ammo_train_rear x y z
						x3 = x + x2
						y3 = y + y2
						z3 = z + z2
						SET_OBJECT_VELOCITY	new_guns[gun_index]	x3 y3 z3
						++ gun_index
						IF gun_index = 10
							gun_index = 0
						ENDIF

					ENDIF

					pad1_circle_pressed = 0

					throw_power_bar = 0

					ENDIF
				ENDIF

			ENDIF

			LVAR_INT check_index
			check_index = 0
			WHILE check_index < 10

				IF DOES_OBJECT_EXIST new_guns[check_index]
				AND NOT IS_CHAR_DEAD ryder

					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS ryder 0.0 1.0 0.5 x y z

					IF LOCATE_OBJECT_3D new_guns[check_index] x y z 1.00 1.00 1.00 0

						IF goons_flag = 0
						AND NOT IS_CHAR_DEAD ryder
						AND NOT collected_guns = 10

							SET_OBJECT_COLLISION new_guns[check_index] FALSE
							TASK_PICK_UP_OBJECT ryder new_guns[check_index] 0.0 0.3 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL CATCH_BOX BOX 0

							TIMERA = 0
								
							GENERATE_RANDOM_INT_IN_RANGE 0 2 r3_rnd
								
							SWITCH collected_guns

								CASE 1
									
									IF r3_rnd = 0
									
										$r3_print = &RYD3_HA	// Damn! Not so hard!
										r3_audio = SOUND_RYD3_HA
										GOSUB r3_load_sample

									ELSE

										$r3_print = &RYD3_HB	// I got it!
										r3_audio = SOUND_RYD3_HB
										GOSUB r3_load_sample

									ENDIF

								BREAK

								CASE 3
									
									IF r3_rnd = 0
									
										$r3_print = &RYD3_HC	// Nice throw, fool!
										r3_audio = SOUND_RYD3_HC
										GOSUB r3_load_sample

									ELSE

										$r3_print = &RYD3_HD	// Keep ‘em coming!
										r3_audio = SOUND_RYD3_HD
										GOSUB r3_load_sample

									ENDIF

								BREAK

								CASE 5
									
									IF r3_rnd = 0
									
										$r3_print = &RYD3_HE	// Shit! I nearly dropped that one!
										r3_audio = SOUND_RYD3_HE
										GOSUB r3_load_sample

									ELSE

										$r3_print = &RYD3_HF	// Bull’s eye!
										r3_audio = SOUND_RYD3_HF
										GOSUB r3_load_sample

									ENDIF

								BREAK

								CASE 7
									
									IF r3_rnd = 0
									
										$r3_print = &RYD3_HG	// You trying to kill me?
										r3_audio = SOUND_RYD3_HG
										GOSUB r3_load_sample

									ELSE

										$r3_print = &RYD3_HH	// Oh, yeah!
										r3_audio = SOUND_RYD3_HH
										GOSUB r3_load_sample	 

									ENDIF

								BREAK

								CASE 8
									
									IF r3_rnd = 0
									
										$r3_print = &RYD3_HJ	// Can’t stop me!
										r3_audio = SOUND_RYD3_HJ
										GOSUB r3_load_sample

									ELSE

										$r3_print = &RYD3_HK	// And another one!
										r3_audio = SOUND_RYD3_HK
										GOSUB r3_load_sample

									ENDIF


								BREAK

								CASE 9

									$r3_print = &RYD3_HM	// Ok, CJ! That’s all I can carry!
									r3_audio = SOUND_RYD3_HM
									GOSUB r3_load_sample

								BREAK

							ENDSWITCH

							stored_check_index = check_index
							++ collected_guns

							IF r3_total_guns > 0
								r3_total_guns -- 
							ENDIF

							IF collected_guns = 5
								
								IF NOT IS_CHAR_DEAD ryder
									SET_CHAR_HEALTH	ryder 5000
								ENDIF

								IF NOT IS_CHAR_DEAD ryders_goon1
									SET_CHAR_HEALTH	ryders_goon1 5000
								ENDIF

								crate_model = CR_AMMOBOX

							ENDIF

							IF collected_guns = 10
								DRAW_CROSSHAIR FALSE
								SET_CAR_DENSITY_MULTIPLIER 1.0
							ENDIF

							goons_flag ++

						ENDIF
					ELSE
						IF new_guns_model[check_index] = CR_AMMOBOX
						AND NOT IS_CHAR_DEAD ryder

							IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON ryder WEAPONTYPE_EXPLOSION

								GENERATE_RANDOM_INT_IN_RANGE 0 6 r3_rnd
								
								SWITCH r3_rnd

									CASE 0

										$r3_print = &RYD3_JA	// Watch what the fuck you're doing!
										r3_audio = SOUND_RYD3_JA
										GOSUB r3_load_sample

									BREAK

									CASE 1

										$r3_print = &RYD3_JB	// CJ, I swear you're trying to kill me!
										r3_audio = SOUND_RYD3_JB
										GOSUB r3_load_sample

									BREAK

									CASE 2

										$r3_print = &RYD3_JC	// CAREFUL with these damn things!
										r3_audio = SOUND_RYD3_JC
										GOSUB r3_load_sample

									BREAK

									CASE 3

										$r3_print = &RYD3_JD  // Hey, EASY, CJ, EASY!
										r3_audio = SOUND_RYD3_JD
										GOSUB r3_load_sample

									BREAK

									CASE 4

										$r3_print = &RYD3_JE	// You throw like a girl!
										r3_audio = SOUND_RYD3_JE
										GOSUB r3_load_sample

									BREAK

									CASE 5

										$r3_print = &RYD3_JF	// Carl, do you understand the concept, here?
										r3_audio = SOUND_RYD3_JF
										GOSUB r3_load_sample

									BREAK

								ENDSWITCH

								CLEAR_CHAR_LAST_WEAPON_DAMAGE ryder

							ENDIF

							IF HAS_OBJECT_COLLIDED_WITH_ANYTHING new_guns[check_index]

								box_collisions ++

								IF box_collisions > 4

									IF NOT IS_CHAR_DEAD ryder
									AND NOT IS_CHAR_DEAD ryders_goon1

										IF NOT IS_CHAR_TOUCHING_OBJECT ryder new_guns[check_index]
											IF NOT IS_CHAR_TOUCHING_OBJECT ryders_goon1 new_guns[check_index]

												GET_OBJECT_COORDINATES new_guns[check_index] x y z
												BREAK_OBJECT new_guns[check_index] TRUE
												REPORT_MISSION_AUDIO_EVENT_AT_OBJECT new_guns[check_index] SOUND_EXPLOSION
												CREATE_FX_SYSTEM EXPLOSION_SMALL X Y Z TRUE r3_fx
												PLAY_AND_KILL_FX_SYSTEM r3_fx
												MARK_OBJECT_AS_NO_LONGER_NEEDED	new_guns[check_index]
												new_guns[check_index] = 0

											ENDIF
										ENDIF

									ENDIF

								ENDIF

							ELSE

								box_collisions = 0

							ENDIF

						ENDIF
					ENDIF

					IF DOES_OBJECT_EXIST new_guns[check_index]
						IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer	new_guns[check_index] 100.0 100.0 0
							MARK_OBJECT_AS_NO_LONGER_NEEDED	new_guns[check_index]
							new_guns[check_index] = 0
						ENDIF
					ENDIF

					IF goons_flag = 1

						IF NOT IS_CHAR_DEAD ryder
						AND NOT IS_CHAR_DEAD ryders_goon1
							GET_SCRIPT_TASK_STATUS ryder TASK_PICK_UP_OBJECT task_status
						ENDIF

						IF task_status = FINISHED_TASK
							IF stored_check_index > -1
																						
								SWITCH r3_crate_cnt

									CASE 0
										CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_y 
										ATTACH_OBJECT_TO_CAR r3_crate_y ryders_car 0.4 -2.10 -0.10 0.0 0.0 15.0
										SET_OBJECT_COLLISION r3_crate_y FALSE
									BREAK

									CASE 2
										CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_w 
										ATTACH_OBJECT_TO_CAR r3_crate_w ryders_car -0.5 -2.10 -0.10 0.0 0.0 30.0
										SET_OBJECT_COLLISION r3_crate_w FALSE
									BREAK

									CASE 4
										CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_x 
										ATTACH_OBJECT_TO_CAR r3_crate_x ryders_car 0.4 -2.10 0.30 0.0 30.0 0.0
									BREAK

									CASE 6
										CREATE_OBJECT CR_AMMOBOX 2278.1770 -1144.8823 27.5107 r3_crate_z 
										ATTACH_OBJECT_TO_CAR r3_crate_z ryders_car -0.6 -2.10 0.30 0.0 0.0 15.0
									BREAK

									CASE 8
										CREATE_OBJECT CR_AMMOBOX 2278.1770 -1144.8823 27.5107 r3_crate_v 
										ATTACH_OBJECT_TO_CAR r3_crate_v ryders_car -0.1 -2.10 0.30 0.0 0.0 15.0
									BREAK

								ENDSWITCH
								
								r3_crate_cnt ++

								IF r3_crate_cnt >= 10

									disdain:

									CLEAR_ONSCREEN_COUNTER r3_total_guns

									CLEAR_ONSCREEN_COUNTER throw_power_bar

									CLEAR_ONSCREEN_TIMER box_timer

									r3_mission_flag ++
								
								ELSE

								IF DOES_OBJECT_EXIST new_guns[stored_check_index]
									DELETE_OBJECT new_guns[stored_check_index]
								ENDIF

							ENDIF

							ENDIF

							goons_flag = 0

						ENDIF
					ENDIF
				ENDIF

				++ check_index

			ENDWHILE

		ENDIF

		IF NOT IS_CAR_DEAD ryders_car
			IF box_timer < 10

				CLEAR_ONSCREEN_COUNTER r3_total_guns

				CLEAR_ONSCREEN_COUNTER throw_power_bar

				CLEAR_ONSCREEN_TIMER box_timer

				failed_mission = 1

				r3_mission_flag ++

			ENDIF
		ENDIF

	ENDIF
	
	IF r3_mission_flag = 11
	AND r3_playing = 2
		
		GOSUB r3_fade_out

		SET_CAR_DENSITY_MULTIPLIER 1.0

		CLEAR_HELP

		IF NOT IS_CAR_DEAD ryders_car

			STOP_PLAYBACK_RECORDED_CAR ryders_car 

		ENDIF

		IF NOT IS_CAR_DEAD ammo_train_rear

			LVAR_FLOAT ryders_car_z	r3_direction_h

			GET_CAR_COORDINATES ammo_train_rear x y z

			GET_GROUND_Z_FOR_3D_COORD x y z ryders_car_z

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ammo_train_rear -2.0 -1.0 -1.0 x y z

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ammo_train_rear 0.0 -4.0 -1.0 x2 y2 z2

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ammo_train_rear 0.0 10.0 -0.5 x3 y3 z3

			LVAR_FLOAT DestX DestY DestZ

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ammo_train_rear 0.0 -50.0 0.0 DestX DestY DestZ

			GET_CAR_HEADING	ammo_train_rear heading

			GET_CAR_HEADING ammo_train_rear r3_direction_h

			heading -= 50.0

			IF NOT IS_CAR_DEAD ammo_train

				STOP_PLAYBACK_RECORDED_CAR ammo_train

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
														   
			SET_CHAR_COLLISION scplayer TRUE

			DETACH_CHAR_FROM_CAR scplayer

			REMOVE_ANIMATION BOX

			SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

		ENDIF

		DELETE_MISSION_TRAINS

		DELETE_OBJECT r3_caboose

		DELETE_OBJECT r3_crate_a

		DELETE_OBJECT r3_crate_b

		DELETE_OBJECT r3_crate_c 

		SET_PLAYER_DISPLAY_VITAL_STATS_BUTTON player1 TRUE

		IF NOT IS_CAR_DEAD ryders_car
		AND NOT IS_CHAR_DEAD scplayer
						
			FREEZE_CAR_POSITION ryders_car FALSE

			SET_CAR_COORDINATES	ryders_car x y ryders_car_z

			SET_CAR_HEADING	ryders_car heading

			SET_CHAR_COORDINATES scplayer x2 y2 ryders_car_z

			SET_CAR_STATUS ryders_car STATUS_PLAYER

			LOCK_CAR_DOORS ryders_car CARLOCK_UNLOCKED

		ENDIF 

		GOSUB r3_set_camera

		SET_FIXED_CAMERA_POSITION x3 y3 z3 0.0 0.0 0.0

		LOAD_SCENE_IN_DIRECTION x3 y3 z3 r3_direction_h 

		IF NOT IS_CAR_DEAD ryders_car
		
			POINT_CAMERA_AT_CAR ryders_car FIXED JUMP_CUT
			
			DELETE_CHAR ryders_goon1

			CREATE_CHAR_AS_PASSENGER ryders_car PEDTYPE_MISSION1 FAM1 0 ryders_goon1

			SET_CHAR_HEALTH	ryders_goon1 800
			SET_CHAR_MAX_HEALTH ryders_goon1 800
			SET_CHAR_SUFFERS_CRITICAL_HITS ryders_goon1 FALSE
			SET_CHAR_NEVER_TARGETTED ryders_goon1 TRUE

		ENDIF

		IF NOT IS_CHAR_DEAD ryder

			DETACH_CHAR_FROM_CAR ryder

			ATTACH_CHAR_TO_CAR ryder ryders_car 0.0 -1.30 0.80 FACING_BACK 15.0 WEAPONTYPE_MICRO_UZI

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD ryders_car

			TASK_ENTER_CAR_AS_DRIVER scplayer ryders_car 4000

		ENDIF

		GOSUB r3_fade_in

		IF failed_mission = 1

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_JG

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( RYD3_JG ) 2000 1 // You as bad at throwing as you is at driving!

		ELSE

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_HN

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( RYD3_HN ) 2000 1 // Hop in the front and burn rubber!
		
		ENDIF

		WAIT 4000

		GOSUB r3_restore_camera

		DRAW_CROSSHAIR FALSE

		TIMERA = 0

		r3_mission_flag = 35
	
	ENDIF
			
	IF r3_mission_flag = 12

		IF failed_mission = 1

			r3_mission_flag = 35

			r3_failed = 1

		ELSE

			r3_mission_flag = 35

		ENDIF
	
	ENDIF

	IF r3_mission_flag = 35

		IF DOES_BLIP_EXIST r3_catching_blip

			REMOVE_BLIP r3_catching_blip

		ENDIF

		IF failed_mission = 1

		  	GOSUB r3_fade_out
			
			IF NOT IS_CAR_DEAD ammo_train 

				DELETE_CAR ammo_train
			
			ENDIF

			IF NOT IS_CHAR_DEAD ryder

				DELETE_CHAR ryder

			ENDIF

			IF NOT IS_CHAR_DEAD ryders_goon1

				DELETE_CHAR ryders_goon1

			ENDIF  

			IF NOT IS_CAR_DEAD ryders_car
			AND NOT IS_CHAR_DEAD scplayer

				IF NOT IS_CHAR_IN_CAR scplayer ryders_car

					WARP_CHAR_INTO_CAR scplayer ryders_car 

				ENDIF

			ENDIF

			check_index = 0

			WHILE check_index < 10

				DELETE_OBJECT new_guns[check_index]

				++ check_index

			ENDWHILE

			GOSUB r3_fade_in

			PRINT_NOW ( RYD3_T ) 4000 1 // ~r~You did not get enough boxes in time.

			GOTO mission_ryder3_failed

		ENDIF

		IF NOT IS_CHAR_DEAD ryder
		AND NOT IS_CHAR_DEAD scplayer

			SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
			
			TASK_STAY_IN_SAME_PLACE ryder TRUE

			TASK_TOGGLE_PED_THREAT_SCANNER ryder TRUE TRUE TRUE

			SET_CHAR_DECISION_MAKER ryder r3_tough

		ENDIF

		// Player drives back to Grove

		$r3_print = &RYD3_KA	// Man, we got a berry on our tail!
		r3_audio = SOUND_RYD3_KA
		GOSUB r3_load_sample
		
		TIMERB = 0

		r3_time_left = 0
		 
		REMOVE_BLIP mission_blip
	   
		ADD_BLIP_FOR_COORD 2067.4631 -1831.6700 12.5527 mission_blip
		//ADD_BLIP_FOR_COORD 2451.3799 -1661.6095 12.3047 mission_blip
		SET_COORD_BLIP_APPEARANCE mission_blip COORD_BLIP_APPEARANCE_NORMAL

		SET_WANTED_MULTIPLIER 1.0

		ALTER_WANTED_LEVEL player1 3

		SET_CAR_DENSITY_MULTIPLIER 1.0
			
		SET_PED_DENSITY_MULTIPLIER 1.0

		r3_mission_flag ++			

		TIMERA = 0
	
	ENDIF
	
	IF r3_mission_flag = 36
	AND TIMERA > 4000

		IF NOT IS_CHAR_DEAD ryder

			TASK_TOGGLE_DUCK ryder TRUE

		ENDIF

		PRINT_NOW ( RYD3_13 ) 7000 1 // ~s~Use the nearby ~y~pay and spray~s~ to lose the cops

		r3_mission_flag ++
		
		r3_get_back = 0	
	
	ENDIF	
	
	// ********************************************************************************************************
	// *																									  *
	// *									Taking Crates to Grove											  *
	// *																									  *	
	// ********************************************************************************************************

	IF r3_mission_flag = 37

		IF NOT IS_CAR_DEAD ryders_car

			IF NOT IS_CHAR_IN_CAR scplayer ryders_car

				PRINT_NOW ( RYD3_Q ) 5000 1 // What are you doing? Get back into the car!

				IF r3_get_back = 0

					REMOVE_BLIP mission_blip

					ADD_BLIP_FOR_CAR ryders_car mission_blip

					SET_BLIP_AS_FRIENDLY mission_blip TRUE

					r3_get_back = 1

				ENDIF

			ELSE
				
				IF r3_get_back = 1

					REMOVE_BLIP mission_blip

					IF r3_car_been_sprayed = 1 

						ADD_BLIP_FOR_COORD 2446.8870 -1661.9623 12.3047  mission_blip // The Grove

					ELSE

						ADD_BLIP_FOR_COORD 2067.4631 -1831.6700 12.5527 mission_blip // The Garage
	
					ENDIF

					SET_COORD_BLIP_APPEARANCE mission_blip COORD_BLIP_APPEARANCE_NORMAL

					r3_get_back = 0

				ENDIF

			ENDIF

			IF IS_CAR_UPSIDEDOWN ryders_car
			AND r3_upsidedown = 0

				IF NOT IS_CHAR_DEAD ryder

					TASK_DIE ryder

				ENDIF

				r3_upsidedown = 1

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD ryder
			IF IS_CHAR_SHOOTING ryder
			AND TIMERB > 10000
			AND NOT IS_MESSAGE_BEING_DISPLAYED

				TIMERB = 0

				GENERATE_RANDOM_INT_IN_RANGE 0 8 r3_rnd
					
				SWITCH r3_rnd

					CASE 0

						$r3_print = &RYD3_LA	// Take this you cop bastards!
						r3_audio = SOUND_RYD3_LA
						GOSUB r3_load_sample

					BREAK

					CASE 1

						$r3_print = &RYD3_LB	// You’re dealing with a kung fu master!
						r3_audio = SOUND_RYD3_LB
						GOSUB r3_load_sample

					BREAK

					CASE 2

						$r3_print = &RYD3_LC	// Ninja style!
						r3_audio = SOUND_RYD3_LC
						GOSUB r3_load_sample

					BREAK

					CASE 3	
					   
						$r3_print = &RYD3_LD	// You ain’t never gonna catch this gangsta!
						r3_audio = SOUND_RYD3_LD
						GOSUB r3_load_sample
 
					BREAK

					CASE 4
					   
						$r3_print = &RYD3_LE	// Fuck off, cops!
						r3_audio = SOUND_RYD3_LE
						GOSUB r3_load_sample

					BREAK

					CASE 5

						$r3_print = &RYD3_LF	// I’m getting tired of this – fuck off!
						r3_audio = SOUND_RYD3_LF
						GOSUB r3_load_sample

					BREAK

					CASE 6

						$r3_print = &RYD3_LG	// You guys got nothing better to do?
						r3_audio = SOUND_RYD3_LG
						GOSUB r3_load_sample

					BREAK

					CASE 7

						$r3_print = &RYD3_LH	// I’m a cop killer!
						r3_audio = SOUND_RYD3_LH
						GOSUB r3_load_sample

					BREAK

				ENDSWITCH					
			
			ENDIF 
		ENDIF

		IF NOT IS_CAR_DEAD ryders_car
		AND NOT IS_CHAR_DEAD scplayer
		AND r3_get_back = 0

			IF NOT IS_CHAR_DEAD ryder
			AND NOT IS_CHAR_DEAD scplayer

				IF NOT IS_WANTED_LEVEL_GREATER player1 0

					IF r3_car_been_sprayed = 0
					AND NOT IS_CHAR_DEAD ryder

						CLEAR_CHAR_TASKS_IMMEDIATELY ryder 					

						CLEAR_ALL_CHAR_RELATIONSHIPS ryder ACQUAINTANCE_TYPE_PED_HATE

						SET_CHAR_DECISION_MAKER ryder r3_empty

						TASK_STAY_IN_SAME_PLACE ryder TRUE

						TASK_TOGGLE_PED_THREAT_SCANNER ryder FALSE FALSE FALSE

						TASK_TOGGLE_DUCK ryder TRUE

						IF DOES_BLIP_EXIST mission_blip

							REMOVE_BLIP mission_blip

						ENDIF

						$r3_print = &RYD3_KB	// Back to Grove Street!
						r3_audio = SOUND_RYD3_KB
						GOSUB r3_load_sample

						TIMERA = 0

						ADD_BLIP_FOR_COORD 2451.3799 -1661.6095 12.3047 mission_blip

						r3_car_been_sprayed = 1

					ENDIF

				ELSE

					IF r3_car_been_sprayed = 1
					AND NOT IS_CHAR_DEAD ryder

						IF DOES_BLIP_EXIST mission_blip

							REMOVE_BLIP mission_blip

						ENDIF

						CLEAR_CHAR_TASKS_IMMEDIATELY ryder 					

						SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
						
						TASK_STAY_IN_SAME_PLACE ryder TRUE

						TASK_TOGGLE_PED_THREAT_SCANNER ryder TRUE TRUE TRUE

						SET_CHAR_DECISION_MAKER ryder r3_tough

						TASK_TOGGLE_DUCK ryder TRUE

						PRINT_NOW ( RYD3_I ) 7000 1 // ~s~Lose the cops use the pay and spray nearby! 

						ADD_BLIP_FOR_COORD 2067.4631 -1831.6700 12.5527 mission_blip // The Garage

						r3_car_been_sprayed = 0

					ENDIF

				ENDIF

			ENDIF

			IF NOT IS_WANTED_LEVEL_GREATER player1 0
			AND TIMERA > 4000
			AND r3_back_to_grove = 0	 

				PRINT_NOW ( RYD3101 ) 7000 1 // ~s~Take the crates back to ~y~Grove street.

				r3_back_to_grove = 1

			ENDIF

			IF NOT IS_WANTED_LEVEL_GREATER player1 0

				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2446.8870 -1661.9623 12.3047 4.0 4.0 4.0 TRUE
				AND IS_CHAR_IN_CAR scplayer ryders_car

					CLEAR_WANTED_LEVEL player1

					CLEAR_AREA 2483.6179 -1666.0925 12.3594 50.0 TRUE

					GOSUB r3_fade_out

					IF NOT IS_CHAR_DEAD ryder
					AND NOT IS_CAR_DEAD ryders_car

						DETACH_CHAR_FROM_CAR ryder

						ATTACH_CHAR_TO_CAR ryder ryders_car 0.0 -1.30 0.80 FACING_FORWARD 15.0 WEAPONTYPE_MICRO_UZI

					ENDIF

					REMOVE_BLIP mission_blip

					REQUEST_CAR_RECORDING 104
					REQUEST_ANIMATION GANGS

					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 104
					OR NOT HAS_ANIMATION_LOADED GANGS
						WAIT 0
					ENDWHILE

					IF NOT IS_CHAR_DEAD scplayer
					AND NOT IS_CAR_DEAD ryders_car

						IF NOT IS_CHAR_IN_CAR scplayer ryders_car

							WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer ryders_car 0

						ENDIF

						START_PLAYBACK_RECORDED_CAR	ryders_car 104

						SKIP_IN_PLAYBACK_RECORDED_CAR ryders_car 1.0

					ENDIF

					GOSUB r3_set_camera
							
					SET_FIXED_CAMERA_POSITION 2444.7961 -1650.0048 24.6868 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2445.6292 -1650.4827 24.4081 JUMP_CUT

					DELETE_CHAR ryders_goon1

					IF NOT IS_CHAR_DEAD ryder

						TASK_TOGGLE_PED_THREAT_SCANNER ryder FALSE FALSE FALSE

					ENDIF

					GOSUB r3_fade_in

					// *****************************************************************************************

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_MA

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( RYD3_MA ) 8000 1 // Damn, homie, your stuff was tight!

					TIMERB = 0
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE

					CLEAR_PRINTS

					// *****************************************************************************************


					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_MB

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( RYD3_MB ) 8000 1 // You too, homie!

					TIMERB = 0
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE

					CLEAR_PRINTS

					// *****************************************************************************************

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_MC

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( RYD3_MC ) 8000 1 // LB's coming over to stash the shit.

					TIMERB = 0
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE

					CLEAR_PRINTS

					// *****************************************************************************************

					IF NOT IS_CAR_DEAD ryders_car

						SET_FIXED_CAMERA_POSITION 2472.5762 -1675.4618 14.0837 0.0 0.0 0.0
						POINT_CAMERA_AT_CAR	ryders_car FIXED JUMP_CUT

					ENDIF

					WAIT 5000

					IF r3_failed = 0

						IF NOT IS_CAR_DEAD ryders_car
						AND NOT IS_CHAR_DEAD ryder
							
							CLEAR_CHAR_TASKS ryder

							TASK_TOGGLE_DUCK ryder FALSE

							STOP_PLAYBACK_RECORDED_CAR ryders_car

							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS	ryders_car 0.0 10.0 0.0 x y z

							TASK_GO_STRAIGHT_TO_COORD scplayer x y z PEDMOVE_WALK -2
							
							TASK_GO_STRAIGHT_TO_COORD ryder x y z PEDMOVE_WALK -2

						ENDIF

						// *****************************************************************************************

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_MD

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE

						PLAY_MISSION_AUDIO 1

						PRINT_NOW ( RYD3_MD ) 8000 1 // Okay, later then.

						TIMERB = 0
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE

						CLEAR_PRINTS

						// *****************************************************************************************

						WAIT 1500

						IF HAS_ANIMATION_LOADED GANGS

						IF NOT IS_CHAR_DEAD scplayer

							IF IS_CHAR_IN_ANY_CAR scplayer
								WARP_CHAR_FROM_CAR_TO_COORD scplayer 2471.3433 -1684.3064 12.5159 // 2471.1055 -1688.0310 12.5198
							ELSE
								SET_CHAR_COORDINATES scplayer 2471.3433 -1684.3064 12.5159 // 2471.1055 -1688.0310 12.5198
							ENDIF

							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
							SET_CHAR_HEADING scplayer 238.6571 // 288.4078
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 -1.0 x y z

						ENDIF

						IF NOT IS_CHAR_DEAD ryder

							IF IS_CHAR_IN_ANY_CAR ryder
								
								DETACH_CHAR_FROM_CAR ryder

								WARP_CHAR_FROM_CAR_TO_COORD ryder x y z

							ELSE

								SET_CHAR_COORDINATES ryder x y z

							ENDIF

								H = 238.6571 - 180.0

							CLEAR_CHAR_TASKS_IMMEDIATELY ryder

							SET_CHAR_HEADING ryder H
							
							OPEN_SEQUENCE_TASK sequence_task

								TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 2471.3433 -1684.3064 12.5159 238.6571 0.4 PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

								TASK_LOOK_AT_CHAR -1 ryder 3000

							CLOSE_SEQUENCE_TASK sequence_task

							PERFORM_SEQUENCE_TASK scplayer sequence_task

							CLEAR_SEQUENCE_TASK sequence_task
							
							OPEN_SEQUENCE_TASK sequence_task

							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 x y z H 0.4 PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

							TASK_GO_STRAIGHT_TO_COORD -1 2465.7278 -1687.0248 12.5184 pedmove_walk -2

							CLOSE_SEQUENCE_TASK sequence_task

							PERFORM_SEQUENCE_TASK ryder sequence_task

							CLEAR_SEQUENCE_TASK sequence_task
						
						ENDIF

						SET_FIXED_CAMERA_POSITION 2472.5212 -1683.4700 14.0526 0.0 0.0 0.0//2471.5955 -1686.8521 14.1658 
						POINT_CAMERA_AT_POINT 2472.0012 -1684.3237 14.0313 JUMP_CUT//2471.6270 -1687.8485 14.0891 
							
						// *****************************************************************************************

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_RYD3_ME

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE

						PLAY_MISSION_AUDIO 1

						PRINT_NOW ( RYD3_ME ) 8000 1 // For life, CJ, for life – you heard?

						TIMERB = 0
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE

						CLEAR_PRINTS

						// *****************************************************************************************

						WAIT 1000

						GOSUB r3_fade_out
						
						ELSE

							REQUEST_ANIMATION GANGS

						ENDIF

						DELETE_CHAR ryder

						GOTO mission_ryder3_passed

					ENDIF
							
				ENDIF
			
			ENDIF

		ENDIF

	ENDIF
	// **********************************************************************************************
	// *																							*
	// *							   		DEATH CHECKS COMMENCE		 							*	
	// *																							*
	// **********************************************************************************************

	IF IS_CAR_DEAD ryders_car

		PRINT_NOW ( RYD3_14 ) 5000 1 // ~r~Ryders truck has been destroyed!

		GOTO mission_ryder3_failed

	ENDIF

	IF IS_CHAR_DEAD	ryder

		PRINT_NOW ( RYD3_B ) 5000 1 // Ryder is dead!

		GOTO mission_ryder3_failed

	ENDIF

ENDWHILE

GOTO mission_ryder3_failed

// Mission ryder3 failed

mission_ryder3_failed:														  

	IF NOT IS_CHAR_DEAD scplayer

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	SET_WANTED_MULTIPLIER 1.0

	IF NOT IS_CHAR_DEAD scplayer

		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

	ENDIF

	PRINT_BIG M_FAIL 5000 1

RETURN

// mission ryder3 passed

mission_ryder3_passed:

	IF NOT IS_CHAR_DEAD scplayer

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	GOSUB r3_restore_camera

	SET_WANTED_MULTIPLIER 1.0

	SET_CAMERA_BEHIND_PLAYER

	GOSUB r3_fade_in

	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 7 5000 1 //"Mission Passed!"

	AWARD_PLAYER_MISSION_RESPECT 7

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

	flag_ryder_mission_counter ++

	PLAYER_MADE_PROGRESS 1 

	REGISTER_MISSION_PASSED ( RYDER_3 )

RETURN

// mission cleanup

mission_ryder3_cleanup:

	IF NOT IS_CHAR_DEAD scplayer

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	IF NOT IS_CHAR_DEAD ryder

		REMOVE_CHAR_ELEGANTLY ryder

	ENDIF

	DRAW_CROSSHAIR FALSE

	SET_WANTED_MULTIPLIER 1.0

	SET_PLAYER_DISPLAY_VITAL_STATS_BUTTON player1 TRUE
			
	REMOVE_CAR_RECORDING 490
	REMOVE_CAR_RECORDING 486
	REMOVE_CAR_RECORDING 104

	IF NOT IS_CHAR_DEAD r3_dead_grove

		DELETE_CHAR r3_dead_grove

	ENDIF

	IF NOT IS_CHAR_DEAD r3_dead_lsv

		DELETE_CHAR r3_dead_lsv

	ENDIF

	REPEAT 4 v

		IF DOES_BLIP_EXIST r3_ballas_inv[v]

			REMOVE_BLIP r3_ballas_inv[v]

		ENDIF

		IF DOES_BLIP_EXIST r3_enemy_inv[v]

			REMOVE_BLIP r3_enemy_inv[v]

		ENDIF

	ENDREPEAT

	IF DOES_BLIP_EXIST r3_catching_blip

		REMOVE_BLIP r3_catching_blip

	ENDIF

	IF DOES_BLIP_EXIST mission_blip

		REMOVE_BLIP mission_blip

	ENDIF
	IF DOES_BLIP_EXIST f4_train_rear_blip

		REMOVE_BLIP f4_train_rear_blip

	ENDIF
	 
	IF DOES_BLIP_EXIST r3_ryders_car_blip

		REMOVE_BLIP r3_ryders_car_blip

	ENDIF

	DELETE_OBJECT r3_ammo_crate[0]
	DELETE_OBJECT r3_ammo_crate[1]
	DELETE_OBJECT r3_ammo_crate[2]
	DELETE_OBJECT r3_ammo_crate[3]

	DELETE_OBJECT r3_crate_a
	DELETE_OBJECT r3_crate_b
	DELETE_OBJECT r3_crate_c

	DELETE_OBJECT r3_crate_v
	DELETE_OBJECT r3_crate_w
	DELETE_OBJECT r3_crate_x
	DELETE_OBJECT r3_crate_y
	DELETE_OBJECT r3_crate_z

	SET_CREATE_RANDOM_GANG_MEMBERS TRUE

	CLEAR_ONSCREEN_COUNTER r3_total_guns
	CLEAR_ONSCREEN_COUNTER throw_power_bar
	CLEAR_ONSCREEN_TIMER box_timer

	DETACH_CHAR_FROM_CAR scplayer
	SWITCH_RANDOM_TRAINS ON

	MARK_MODEL_AS_NO_LONGER_NEEDED picador
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED CR_GUNCRATE
	MARK_MODEL_AS_NO_LONGER_NEEDED CR_AMMOBOX

	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2

	MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

	MARK_MODEL_AS_NO_LONGER_NEEDED ballas1
	MARK_MODEL_AS_NO_LONGER_NEEDED ballas3

	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi

	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

	REMOVE_ANIMATION BOX
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION RYDER
	REMOVE_ANIMATION SWAT

	SET_PED_DENSITY_MULTIPLIER 1.0

	SET_CAR_DENSITY_MULTIPLIER 1.0

	UNLOAD_SPECIAL_CHARACTER 1

	SWITCH_ROADS_BACK_TO_ORIGINAL 2284.0 -1151.0 10.0 2288.0 -1147.0 30.0

	SWITCH_ROADS_BACK_TO_ORIGINAL 2287.3213 -1132.5714 10.0000 2360.8386 -1177.1337 40.0000 

	SWITCH_ROADS_BACK_TO_ORIGINAL 2311.6763 -1375.3975 10.0000 2292.3650 -1303.1021 40.0000 
																	    
	SWITCH_ROADS_BACK_TO_ORIGINAL 2268.6953 -1138.5408 10.0000 2282.3616 -1159.6042 40.0000 

	SWITCH_ROADS_BACK_TO_ORIGINAL 2307.5366 -1162.6240 10.0000 2266.3511 -1136.9897 40.0000 

//	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2311.6538 -1165.1200 10.0000 2255.6228 -1134.2437 40.0000

//	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2284.0 -1151.0 10.0 2288.0 -1147.0 40.0

//	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2287.3213 -1132.5714 10.0000 2360.8386 -1177.1337 40.0000 

	flag_player_on_mission = 0

	GET_GAME_TIMER timer_mobile_start

	CLEAR_WANTED_LEVEL player1

	SET_WANTED_MULTIPLIER 1.0

	MISSION_HAS_FINISHED

RETURN

// ********************************** camera stuff ************************************

r3_set_camera:

	SET_PLAYER_CONTROL player1 OFF

	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

	SWITCH_WIDESCREEN ON

	SET_ALL_CARS_CAN_BE_DAMAGED FALSE

	CLEAR_PRINTS

	CLEAR_HELP

RETURN

r3_restore_camera:

	SET_PLAYER_CONTROL player1 ON

	SET_EVERYONE_IGNORE_PLAYER player1 FALSE

	SWITCH_WIDESCREEN OFF

	SET_ALL_CARS_CAN_BE_DAMAGED TRUE

	SET_CAMERA_BEHIND_PLAYER

	RESTORE_CAMERA_JUMPCUT

	CLEAR_HELP

	CLEAR_PRINTS

RETURN

r3_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

r3_fade_in:

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

ryder3_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

		REQUEST_MODEL picador

		WHILE NOT HAS_MODEL_LOADED picador
			WAIT 0
		ENDWHILE

		LVAR_INT r3_spawn_car
		CREATE_CAR picador 2298.3899 -1149.0121 25.7656 r3_spawn_car
		SET_CAR_HEADING r3_spawn_car 45.7660
		
		IF NOT IS_CHAR_DEAD scplayer
	
			WARP_CHAR_INTO_CAR scplayer r3_spawn_car
	
		ENDIF
		
		MARK_MODEL_AS_NO_LONGER_NEEDED picador 

		r3_mission_flag = 50

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		REQUEST_CAR_RECORDING 486
		REQUEST_CAR_RECORDING 490

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 486
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 490
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD ammo_train

			FREEZE_CAR_POSITION ammo_train FALSE

			START_PLAYBACK_RECORDED_CAR ammo_train 490

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		
		SET_CAR_DENSITY_MULTIPLIER 0.0 
		SET_PED_DENSITY_MULTIPLIER 0.0

		REQUEST_MODEL FREIGHT
		REQUEST_MODEL FREIFLAT
		REQUEST_MODEL GREENWOO

		REQUEST_CAR_RECORDING 106

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 106
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_MODEL_LOADED FREIGHT	
		OR NOT HAS_MODEL_LOADED FREIFLAT
		OR NOT HAS_MODEL_LOADED GREENWOO
			WAIT 0
		ENDWHILE

		LVAR_INT ammo_train3
	  	CREATE_MISSION_TRAIN 10 2278.1770 -1144.8823 27.5107 1 ammo_train3
	  	MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
		MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT
		SET_TRAIN_SPEED ammo_train3 0.0
		SET_TRAIN_CRUISE_SPEED ammo_train3 0.0

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
		
		IF NOT IS_CHAR_DEAD ryder
			
			TASK_PLAY_ANIM ryder RYD_Beckon_01 RYDER 1.0 FALSE FALSE FALSE FALSE -1

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		collected_guns = 9

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		IF NOT IS_CHAR_DEAD scplayer 

			REQUEST_ANIMATION CARRY

			WHILE NOT HAS_ANIMATION_LOADED CARRY
				WAIT 0
			ENDWHILE

			GET_CHAR_COORDINATES scplayer x y z

			CREATE_OBJECT_NO_OFFSET crate_model x y z new_guns[0]

			SET_OBJECT_COLLISION new_guns[0] FALSE
			TASK_PICK_UP_OBJECT scplayer new_guns[0] 0.0 0.3 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL CATCH_BOX BOX 0

			REMOVE_ANIMATION CARRY

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P

		REQUEST_CAR_RECORDING 486

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 486
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD ryders_car

			START_PLAYBACK_RECORDED_CAR ryders_car 486

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_I
	
		IF NOT IS_CAR_DEAD ryders_car
					
			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_y 

			ATTACH_OBJECT_TO_CAR r3_crate_y ryders_car 0.4 -2.10 -0.10 0.0 0.0 15.0

			SET_OBJECT_COLLISION r3_crate_y FALSE

			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_w 

			ATTACH_OBJECT_TO_CAR r3_crate_w ryders_car -0.5 -2.10 -0.10 0.0 0.0 30.0

			SET_OBJECT_COLLISION r3_crate_w FALSE

			CREATE_OBJECT CR_GUNCRATE 2278.1770 -1144.8823 27.5107 r3_crate_x 

			ATTACH_OBJECT_TO_CAR r3_crate_x ryders_car 0.4 -2.10 0.30 0.0 30.0 0.0

			CREATE_OBJECT CR_AMMOBOX 2278.1770 -1144.8823 27.5107 r3_crate_z 

			ATTACH_OBJECT_TO_CAR r3_crate_z ryders_car -0.6 -2.10 0.30 0.0 0.0 15.0

			CREATE_OBJECT CR_AMMOBOX 2278.1770 -1144.8823 27.5107 r3_crate_v 

			ATTACH_OBJECT_TO_CAR r3_crate_v ryders_car -0.1 -2.10 0.30 0.0 0.0 15.0

		ENDIF

	ENDIF

RETURN

r3_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 r3_audio

	r3_playing = 0

RETURN

r3_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND r3_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $r3_print ) 10000 1  

		r3_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND r3_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $r3_print

		r3_playing = 2

	ENDIF
	
RETURN

}

