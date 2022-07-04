MISSION_START
{

// ****************************************Mission Start************************************

//	shooting_range_script:
	SCRIPT_NAME SHRANGE

   	// Initialise variables


	//REGISTER_MISSION_GIVEN
	
	SET_DEATHARREST_STATE FALSE
	
	flag_player_on_mission = 1
	flag_shooting_range_mission = 1


	//coords to check to trigger shooting challenge - use x,y of near, right corner for each range
	VAR_FLOAT Schallenge_X[4] Schallenge_Y[4] 

	LVAR_INT SR_quit_range

	// coords of initial booth in each range
	LVAR_FLOAT booth_x[4] booth_y[4] booth_z[4]

	// offset from initial booth to two subsequent booths
	LVAR_FLOAT booth_offset_1[4] booth_offset_2[4]

	// range of the range
	LVAR_FLOAT far_range_offset[4]

	//level being played in range
	VAR_INT SR_range_level[3]

	// range heading
	LVAR_FLOAT range_heading[4] h

	// chars weapon
	VAR_INT SR_ped_weapon[4]

	//targets
	VAR_INT SR_target_state[3] sr_destroyed_elements[3]

	// offset for each element - GLOBAL cos run out of available locals
   	VAR_FLOAT target_offset_x[8] target_offset_z[8]

	//object rotation
	VAR_FLOAT sr_target_rotation[3]

	// object names
	VAR_INT sr_object_name[8] sr_obj

	// advance time
	VAR_INT sr_target_advance_time[3]

	// flags
	VAR_INT sr_Dec SR_mission_state SR_targets_created[3] sr_area

	//cutscenes
	VAR_INT SR_flag SR_time_check

	// general
	VAR_INT sr_int1 sr_int2 sr_seq sr_direction
	VAR_FLOAT sr_float1 sr_float2 sr_float3 sr_float4 sr_yoffset

	VAR_INT SR_ped_state[3] SR_ped_can_be_created[3] sr_j sr_obj_number SR_ped[3]
	VAR_INT sr_target[24] sr_time_to_new_target[3]

	//awards
	VAR_INT SR_skill_won sr_Score[3]


	// coords to check to trigger challenge	- WARNING coords local to axis of range
	Schallenge_X[0] = 286.1649 
	Schallenge_Y[0] = -30.9591	
	Schallenge_X[1] = 301.8877 
	Schallenge_Y[1] = -77.2586 
	Schallenge_X[2] = 306.4883					   
	Schallenge_Y[2] = -141.9047
	Schallenge_X[3] = 307.0919 
	Schallenge_Y[3] = -159.2197

	// object anmes for the target elements
	sr_object_name[0] = target_frame
	sr_object_name[1] = target_lleg
	sr_object_name[2] = target_rleg
	sr_object_name[3] = target_ltorso
	sr_object_name[4] = target_rtorso
	sr_object_name[5] = target_larm
	sr_object_name[6] = target_rarm
	sr_object_name[7] = target_head

	// Offset of each target element

	target_offset_x[1] = 0.275 
	target_offset_z[1] = -3.502

	target_offset_x[2] = -0.214 
	target_offset_z[2] = -3.479
	
	target_offset_x[3] = 0.245 
	target_offset_z[3] = -2.791

	target_offset_x[4] = -0.168
	target_offset_z[4] = -2.806
	
	target_offset_x[5] = 0.079
	target_offset_z[5] = -2.123
	target_offset_x[6] = -0.589
	target_offset_z[6] = -2.317
	target_offset_x[7] = -0.357 
	target_offset_z[7] = -1.855

	// coords at which target will be nearest the booths 
	// (target for furthest left target - other targets will be created as an offset to this)
	// all coords are calculates as an offset from this point




	// position of left booth - ammun1
	booth_x[0] = 290.6264
	booth_y[0] = -24.5548
	booth_z[0] = 1000.5229

	booth_offset_1[0] = 3.0583
	booth_offset_2[0] =	6.1726

	// in x axis
	far_range_offset[0] = -13.6242
	range_heading[0] = 0.0


	// position of left booth - ammun2
	booth_x[1] = 303.0788
	booth_y[1] = -61.2269
	booth_z[1] = 1000.5234

	booth_offset_1[1] = 2.9464
	booth_offset_2[1] =	6.0799

	// in x axis
	far_range_offset[1] = -13.6242
	range_heading[1] = 270.0


	// position of left booth - ammun4
	booth_x[2] = 300.1018
	booth_y[2] = -137.0399
	booth_z[2] = 1003.0547

	booth_offset_1[2] = 3.1341
	booth_offset_2[2] =	6.1264

	// in x axis
	far_range_offset[2] = -13.6242
	range_heading[2] = 90.0



	// position of left booth - ammun5
	booth_x[3] = 299.4518 
	booth_y[3] = -166.3517
	booth_z[3] = 998.6105

	booth_offset_1[3] = 3.0055
	booth_offset_2[3] =	4.6018

	// in x axis
	far_range_offset[3] = -13.6242
	range_heading[3] = 90.0

	sr_i = 0
	WHILE sr_i < 3
		SR_ped_can_be_created[sr_i] = 1
		SR_ped_state[sr_i] = 0
		SR_ped_weapon[sr_i] = 0
		SR_range_level[sr_i] = 0
		SR_target_state[sr_i] = 0
		sr_target_rotation[sr_i] = 90.0
		sr_time_to_new_target[sr_i] = 0
		SR_targets_created[sr_i] = 0
		sr_i ++

	ENDWHILE

	SR_mission_state = 0
	SR_flag = 0
	SR_time_check = 0

	GET_INT_STAT CITIES_PASSED Return_cities_passed
	
	IF Return_cities_passed = 0
		range_weapons_open = 2
	ENDIF

	IF Return_cities_passed = 1
		IF range_weapons_open = 2
			trigger_new_range_level = 1	
		ENDIF
		range_weapons_open = 3
	ENDIF

	IF Return_cities_passed > 1
		IF range_weapons_open < 4
			trigger_new_range_level = 1
		ENDIF	
		range_weapons_open = 4
	ENDIF

//	IF flag_player_on_mission = 0
//		flag_shooting_range_mission = 1

		COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_NORM sr_dec

		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE sr_dec EVENT_SHOT_FIRED
		ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE sr_dec EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 100.0 100.0 100.0 100.0 TRUE TRUE

		LOAD_MISSION_AUDIO 4 SOUND_BANK_SHOOTING_RANGE
		
		REQUEST_MODEL target_head
		REQUEST_MODEL target_larm
		REQUEST_MODEL target_rarm
		REQUEST_MODEL target_lleg
		REQUEST_MODEL target_rleg
		REQUEST_MODEL target_rtorso
		REQUEST_MODEL target_ltorso
		REQUEST_MODEL target_frame

		REQUEST_MODEL COLT45
		REQUEST_MODEL MICRO_UZI
		REQUEST_MODEL CHROMEGUN
		REQUEST_MODEL AK47

		WHILE NOT HAS_MODEL_LOADED COLT45
		OR NOT HAS_MODEL_LOADED MICRO_UZI
		OR NOT HAS_MODEL_LOADED CHROMEGUN
		OR NOT HAS_MODEL_LOADED AK47
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_MODEL_LOADED target_head
		OR NOT HAS_MODEL_LOADED target_larm
		OR NOT HAS_MODEL_LOADED target_rarm
		OR NOT HAS_MODEL_LOADED target_lleg
		OR NOT HAS_MODEL_LOADED target_rleg
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_MODEL_LOADED target_rtorso
		OR NOT HAS_MODEL_LOADED target_ltorso
		OR NOT HAS_MODEL_LOADED target_frame
		OR NOT HAS_MISSION_AUDIO_LOADED 4
			WAIT 0
		ENDWHILE
//	ENDIF

VAR_INT SR_temp_target[3]

	IF flag_shooting_range_mission = 47
		CREATE_OBJECT_NO_OFFSET sr_object_name[sr_i] 293.7570 1.0 1000.5156 sr_target[0]
		CREATE_RANDOM_CHAR X Y Z SR_ped[0]
		CREATE_OBJECT poolcue x y z sr_obj

	ENDIF

	GET_AREA_VISIBLE sr_area

		SR_loop:

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
			sr_i = 9
			WHILE sr_i < 16
				IF DOES_OBJECT_EXIST sr_target[sr_i]
					BREAK_OBJECT sr_target[sr_i] TRUE
				ENDIF
				sr_i ++
			ENDWHILE
			sr_score[1] = 20
		ENDIF

		WAIT 0

		GET_AREA_VISIBLE sr_i
		IF NOT sr_i = sr_area
			GOTO SR_cleanup
		ENDIF

		IF IS_PLAYER_PLAYING Player1

			GOSUB SR_targets
			GOSUB SR_AI
			GOSUB SR_mission
		ENDIF

	GOTO SR_loop

TERMINATE_THIS_SCRIPT	//	should never get here

SR_mission:

	IF NOT IS_JAPANESE_VERSION
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			IF NOT SR_mission_state = 0
				CLEAR_PRINTS
				
				SR_quit_range = 1
				SR_mission_state = 20
				SR_flag = 1
				SR_time_check = 0
			ENDIF
		ENDIF
	ELSE
		IF IS_BUTTON_PRESSED PAD1 CROSS
			IF NOT SR_mission_state = 0
				CLEAR_PRINTS
				
				SR_quit_range = 1
				SR_mission_state = 20
				SR_flag = 1
				SR_time_check = 0
			ENDIF
		ENDIF
	ENDIF


	SWITCH SR_mission_state

		CASE 0 //player not on shooting range mission
//			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			IF SR_range_id = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 286.1649 -30.9591 1000.5156 2.0 2.0 2.0 TRUE
					SR_mission_state = 1
				ENDIF
			ENDIF
			IF SR_range_id = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 301.8877 -77.2586 1000.5234 2.0 2.0 2.0  TRUE
					SR_mission_state = 1
				ENDIF																							   
			ENDIF																								   
			IF SR_range_id = 2																					   
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 306.4883 -141.9047 1003.0547 2.0 2.0 2.0  TRUE				   
					SR_mission_state = 1
				ENDIF																							   
			ENDIF																								   
			IF SR_range_id = 3																					   
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 307.0919 -159.2197 998.6010 2.0 2.0 2.0  TRUE				   
					SR_mission_state = 1
				ENDIF
			ENDIF
		BREAK

		CASE 1 // range missions triggered for first time.

			IF range_cuts_watched > 0
				IF SR_flag < 7
				AND sr_flag > 1					
					SR_flag = 7
					SR_time_check = 0
					CLEAR_PRINTS
				ENDIF
			ENDIF

			// fade out
			IF SR_flag = 0				
				SR_quit_range = 0
				SET_PLAYER_CYCLE_WEAPON_BUTTON Player1 FALSE
				VAR_INT ammo[4] WeaponType[4] ModelForWeaponType[4]

				GET_CHAR_WEAPON_IN_SLOT scplayer 3 WeaponType[0] ammo[0] ModelForWeaponType[0]
				GET_CHAR_WEAPON_IN_SLOT scplayer 4 weapontype[1] ammo[1] ModelForweapontype[1]
				GET_CHAR_WEAPON_IN_SLOT scplayer 5 weapontype[2] ammo[2] ModelForweapontype[2]
				GET_CHAR_WEAPON_IN_SLOT scplayer 6 weapontype[3] ammo[3] ModelForweapontype[3]


				sr_skill_won = 0
				SET_PLAYER_CONTROL player1 OFF
				SET_FADING_COLOUR 0 0 0
				SHOW_UPDATE_STATS FALSE
				DO_FADE 1000 FADE_OUT
				
				SR_flag = 1
				SR_time_check = TIMERA + 1500
			ENDIF

			// shows cut of guns shooting down range
			IF SR_flag = 1
				IF TIMERA > SR_time_check
					// Mission level1 Pistol

					SWITCH_WIDESCREEN ON

					CLEAR_PRINTS
					PRINT ANR_1 4000 1
					PRINT ANR_2 4000 1
					PRINT ANR_3 4000 1

					SR_float3 = -0.83
					SR_float4 = 0.47
					z = 1.7

					GOSUB getWorldXYZ
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0

					SR_float3 = 0.17
					SR_float4 = 0.63
					z = 1.7

					GOSUB getWorldXYZ
					
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					DO_FADE 1000 FADE_IN
					SR_flag = 2
					SR_time_check = TIMERA + 4000
				ENDIF
			ENDIF

			// shows cut of ped leaving the range and player walks in
			IF SR_flag = 2
				IF TIMERA > SR_time_check

					SR_ped_state[1] = 3
					SR_ped_can_be_created[1] = 0

					SR_float3 = -0.63
					SR_float4 = -3.47
					z = 0.2

					GOSUB getWorldXYZ
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0

					SR_float3 = 0.4
					SR_float4 = -2.5
					z = 0.5

					GOSUB getWorldXYZ

					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					DO_FADE 1000 FADE_IN
					SR_flag = 3
					SR_time_check = TIMERA + 2000
				ENDIF
			ENDIF


			// player walks in with pistol
			IF SR_flag = 3
				IF TIMERA > SR_time_check

					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 99999

					SR_float3 = 0.0 + booth_offset_1[SR_range_id]
					SR_float4 = -1.0

					GOSUB getWorldXYZ

					TASK_GO_STRAIGHT_TO_COORD scplayer x y z PEDMOVE_WALK -2

					SR_flag = 4
					SR_time_check = TIMERA + 3000
				ENDIF
			ENDIF

			//close up of booth
			IF SR_flag = 4
				IF TIMERA > SR_time_check
					SR_ped_state[1] = 4
					SR_float1 = range_heading[SR_range_id]
					SR_float1 += 90.0

					SET_CHAR_HEADING scplayer SR_float1		 

					SR_float3 = booth_offset_1[SR_range_id] - 0.8201
					SR_float4 = -0.4944
					z = 2.1312

					GOSUB getWorldXYZ
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0


					SR_float3 = booth_offset_1[SR_range_id] - 0.1125
					SR_float4 = -0.0237
					z = 1.6042

					GOSUB getWorldXYZ
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					sr_float3 = booth_offset_1[SR_range_id]
					sr_float4 = -2.0
					z = 0.0

					GOSUB getWorldXYZ

					SET_CHAR_COORDINATES_DONT_WARP_GANG  scplayer x y z

					sr_float3 = booth_offset_1[SR_range_id]
					sr_float4 = 0.0
					z = 0.0

					GOSUB getWorldXYZ

					SET_CHAR_HEADING scplayer range_heading[SR_range_id] 
					TASK_GO_STRAIGHT_TO_COORD scplayer x y z PEDMOVE_WALK -2


					IF sr_skill_won = 0
						SR_ped_weapon[0] = 1
						SR_ped_weapon[2] = 1
					ENDIF

					SR_ped_state[0] = 1
					SR_ped_state[2] = 1
					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0
					SR_range_level[0] = 1
					SR_range_level[1] = 1
					SR_range_level[2] = 1

					SR_flag = 5
					SR_time_check = TIMERA + 3000
				ENDIF
			ENDIF

			//peds shooting down range
			IF SR_flag = 5
				IF TIMERA > SR_time_check

					CLEAR_PRINTS
					PRINT_BIG ANR_6 2000 1
					
					SR_float3 = 2.1566
					SR_float4 = 2.4369
					z = 1.4565

					GOSUB getWorldXYZ
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0

					SR_float3 = 1.9771
					SR_float4 = 3.4129
					z = 1.3326

					GOSUB getWorldXYZ
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					SR_flag = 6
					SR_time_check = TIMERA + 3000
				ENDIF
			ENDIF

			// camera showing ped shooting targets
			IF sr_flag = 6
				IF TIMERA > SR_time_check

					IF NOT IS_CHAR_DEAD SR_ped[0]
						SET_CHAR_ACCURACY SR_ped[0] 100
					ENDIF
						
					
					CLEAR_THIS_BIG_PRINT ANR_6
					PRINT ANR_7 4000 1
					PRINT ANR_10 4000 1

					SR_float3 = 0.7525
					SR_float4 = -0.3628
					z = 1.6174

					GOSUB getWorldXYZ
					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0

					SR_float3 = 0.1734
					SR_float4 = 0.446
					z = 1.5146

					GOSUB getWorldXYZ
					POINT_CAMERA_AT_POINT x y z JUMP_CUT
					SR_flag = 7
					SR_time_check = TIMERA + 8000
				ENDIF
			ENDIF

			IF SR_flag = 7
				IF TIMERA > SR_time_check

					IF NOT IS_CHAR_DEAD scplayer
					ENDIF

					IF range_cuts_watched = 0												
						range_cuts_watched = 1
					ELSE
						PRINT_BIG ANR_6 2000 1
					ENDIF

					DO_FADE 1000 FADE_OUT
					SR_flag = 8
				ENDIF
			ENDIF

			IF SR_flag = 8
				IF NOT GET_FADING_STATUS

					sr_float3 = booth_offset_1[SR_range_id]
					sr_float4 = 0.0
					z = 0.0

					GOSUB getWorldXYZ

					IF NOT DOES_OBJECT_EXIST sr_obj
						CREATE_OBJECT poolcue x y z sr_obj
						SET_OBJECT_VISIBLE sr_obj FALSE
						SET_CHAR_HEADING scplayer range_heading[SR_range_id]
					ENDIF
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_PISTOL
					
					SET_PLAYER_DUCK_BUTTON Player1 FALSE
	   
					IF SR_skill_won = 0
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 99999
					ENDIF

					SR_ped_state[1] = 4
					SR_ped_can_be_created[1] = 0

					SR_flag = 9
					SR_time_check = TIMERA + 800

					IF sr_skill_won = 0
						SR_ped_weapon[0] = 1
						SR_ped_weapon[2] = 1
					ENDIF


					SR_ped_state[0] = 1
					SR_ped_state[2] = 1

					SR_target_state[0] = 4
					SR_target_state[1] = 4
					SR_target_state[2] = 4					
				ENDIF
			ENDIF

			IF SR_flag = 9
				IF TIMERA > SR_time_check

					DO_FADE 1000 FADE_IN
					DETACH_CHAR_FROM_CAR scplayer
					ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_PISTOL
				
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON

					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0
					SR_range_level[0] = 1
					SR_range_level[1] = 1
					SR_range_level[2] = 1

					IF trigger_new_range_level = 1
						PRINT_HELP ANR_56  // A new weapon challenge is available.
						trigger_new_range_level = 0
					ENDIF

					SR_flag = 10
				ENDIF
			ENDIF


			IF SR_flag = 10
				IF TIMERA > sr_time_check

			       PRINT_BIG ( RACE2 ) 1000 4 //"3"

					SR_flag = 11
					SR_time_check = TIMERA + 1000
					SET_PLAYER_CONTROL player1 ON
				ENDIF
			ENDIF

			IF SR_FLAG = 11
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE3 ) 1000 4 //"2"
					SR_flag = 12
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 12
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE4 ) 1000 4 //"1"
					SR_flag = 13
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 13
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE5 ) 1000 4 //"GO"
					SR_flag = 14
					IF NOT IS_CHAR_DEAD SR_ped[0]
						SET_CHAR_ACCURACY SR_ped[0] 80
					ENDIF

				ENDIF
			ENDIF

			IF SR_Flag = 14
				SR_mission_state = 2
				SR_flag = 0
			ENDIF

			// alternative start 
			IF sr_flag = 16
				IF TIMERA > SR_time_check
				
					IF NOT IS_CHAR_DEAD scplayer
						IF NOT DOES_OBJECT_EXIST sr_obj
							sr_float3 = booth_offset_1[SR_range_id]
							sr_float4 = 0.0
							z = 0.0

							GOSUB getWorldXYZ

							CREATE_OBJECT poolcue x y z sr_obj
							SET_OBJECT_VISIBLE sr_obj FALSE
							SET_CHAR_HEADING scplayer range_heading[SR_range_id]
							ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_PISTOL
						
							
							RESTORE_CAMERA_JUMPCUT
							SWITCH_WIDESCREEN OFF
							
							SET_PLAYER_DUCK_BUTTON Player1 FALSE
						ENDIF
					ENDIF
				
					DO_FADE 1000 FADE_OUT
					
					SR_time_check = TIMERA + 2500

					SR_flag = 17
				ENDIF
			ENDIF

			IF sr_flag = 17
				IF TIMERA > sr_time_check

					IF SR_skill_won = 3
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 99999
						DETACH_CHAR_FROM_CAR scplayer
						ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_MICRO_UZI
						
					ENDIF

					IF SR_skill_won = 6
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 99999
						DETACH_CHAR_FROM_CAR scplayer
						ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_SHOTGUN
					
					ENDIF

					IF SR_skill_won = 9
						GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_AK47 99999
						DETACH_CHAR_FROM_CAR scplayer
						ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_AK47
						
					ENDIF
					
					DO_FADE 1000 FADE_IN

					CLEAR_PRINTS			
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON

					IF sr_skill_won = 3
						PRINT_BIG ANR_47 3000 1 
					ENDIF

					IF sr_skill_won = 6
						PRINT_BIG ANR_23 3000 1 
					ENDIF

					IF sr_skill_won = 9
						PRINT_BIG ANR_50 3000 1 
					ENDIF
					sr_time_check = TIMERA + 4000
					sr_flag = 18
				ENDIF
			ENDIF

			IF sr_flag = 18
				IF TIMERA > sr_time_check
					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0
					SR_range_level[0] = 1
					SR_range_level[1] = 1
					SR_range_level[2] = 1
					sr_flag = 10
				ENDIF
			ENDIF

		BREAK

		CASE 2 // Shoot 3 targets at progressively further distances

	
			IF SR_flag = 0		
				IF NOT IS_CHAR_DEAD SR_ped[0]
					SET_CHAR_ACCURACY SR_ped[0] 87
				ENDIF
				IF NOT IS_CHAR_DEAD SR_ped[2]
					SET_CHAR_ACCURACY SR_ped[2] 87
				ENDIF

				SR_flag = 1
			ENDIF

			IF SR_flag = 1

				// You were too slow
				IF SR_target_state[0] = 3
				AND SR_range_level[0] = 3
					SR_mission_state = 20 //fail
					SR_flag = 0
				ENDIF
				
				IF SR_target_state[2] = 3
				AND SR_range_level[2] = 3
					SR_mission_state = 20 //fail
					SR_flag = 0
				ENDIF

				IF SR_target_state[1] = 3
				AND SR_range_level[1] = 3
					SR_mission_state = 3 //pass
					SR_flag = 0
				ENDIF
				
			ENDIF

		BREAK

		CASE 3

			IF SR_flag = 0 //passed

				IF SR_skill_won = 0	
				OR SR_skill_won = 3
				OR SR_skill_won = 6
				OR SR_skill_won = 9
					PRINT_BIG ANR_41 3000 1
				ENDIF

				IF SR_skill_won = 1
				OR SR_skill_won = 4
				OR SR_skill_won = 7
				OR SR_skill_won = 10
					CLEAR_THIS_BIG_PRINT ANR_41
					PRINT_BIG ANR_42 2800 1				
				ENDIF

				IF SR_skill_won = 2
				OR SR_skill_won = 5
				OR SR_skill_won = 8
					CLEAR_THIS_BIG_PRINT ANR_42
					sr_time_check = TIMERA + 5500

					SR_target_state[0] = 5
					SR_target_state[1] = 5
					SR_target_state[2] = 5

				ENDIF

				IF SR_skill_won = 11
					
					PRINT_BIG ANR_45 3000 1
					sr_time_check = TIMERA + 5500
					SR_target_state[0] = 5
					SR_target_state[1] = 5
					SR_target_state[2] = 5
				ENDIF

				IF SR_skill_won = SR_skill_award_given
					// these only ever happen once.
					IF SR_skill_won = 0
					OR SR_skill_won = 1
						INCREMENT_FLOAT_STAT WEAPONTYPE_PISTOL_SKILL 50.0				
					ENDIF

					IF SR_skill_won = 3
					OR SR_skill_won = 4
						INCREMENT_FLOAT_STAT WEAPONTYPE_MICRO_UZI_SKILL 50.0
					ENDIF

					IF SR_skill_won = 6
					OR SR_skill_won = 7
						INCREMENT_FLOAT_STAT WEAPONTYPE_SHOTGUN_SKILL 50.0
					ENDIF

					IF SR_skill_won = 9
					OR SR_skill_won = 10
						INCREMENT_FLOAT_STAT WEAPONTYPE_AK47_SKILL 50.0
					ENDIF
				
					IF SR_skill_won = 2
						INCREMENT_FLOAT_STAT WEAPONTYPE_PISTOL_SKILL 100.0
						PLAYER_MADE_PROGRESS 1
					ENDIF

					IF SR_skill_won = 5
						INCREMENT_FLOAT_STAT WEAPONTYPE_MICRO_UZI_SKILL 100.0
						PLAYER_MADE_PROGRESS 1
					ENDIF

					IF SR_skill_won = 8
						INCREMENT_FLOAT_STAT WEAPONTYPE_SHOTGUN_SKILL 100.0
						PLAYER_MADE_PROGRESS 1
					ENDIF

					IF SR_skill_won = 11
						INCREMENT_FLOAT_STAT WEAPONTYPE_AK47_SKILL 100.0
						SET_FLOAT_STAT WEAPONTYPE_PISTOL_SKILL 1000.0
						ADD_SCORE Player1 10000 //amount of cash reward

						PLAYER_MADE_PROGRESS 1
//						PRINT_HELP ANR_54
						PLAY_MISSION_PASSED_TUNE 2
//						VIEW_INTEGER_VARIABLE player_has_Fast_reload player_has_Fast_reload
						player_has_Fast_reload = 1
//						SET_PLAYER_FAST_RELOAD Player1 TRUE
					ENDIF

					SR_skill_award_given ++					
					SET_INT_STAT SHOOTING_RANGE_SCORE SR_skill_award_given
				ENDIF
				SR_flag = 1
			ENDIF

			IF SR_flag = 1
				IF SR_skill_won = 0	
					SR_mission_state = 4
					SR_flag = 0
				ENDIF

				IF SR_skill_won = 3
				OR SR_skill_won = 6
				OR SR_skill_won = 9
					SR_mission_state = 4
					SR_flag = 10
				ENDIF

				IF SR_skill_won = 1
					SR_mission_state = 5
					SR_flag = 0
				ENDIF

				IF SR_skill_won = 4
				OR SR_skill_won = 7
				OR SR_skill_won = 10
					SR_mission_state = 5
					SR_flag = 10
				ENDIF

				IF SR_skill_won = 2
				OR SR_skill_won = 5
				OR SR_skill_won = 8
					SR_mission_state = 1
					SR_flag = 16
				ENDIF

				IF SR_skill_won = 2
					SR_ped_weapon[0] = 2
					SR_ped_weapon[2] = 2
					SR_ped_state[0] = 1

					SR_ped_state[2] = 1
					PRINT_BIG ANR_29 2000 1
				ENDIF

				IF SR_skill_won = 5
					SR_ped_weapon[0] = 3

					SR_ped_weapon[2] = 3
					SR_ped_state[0] = 1

					SR_ped_state[2] = 1
					PRINT_BIG ANR_53 2000 1
				ENDIF

				IF SR_skill_won = 8
					SR_ped_weapon[0] = 4

					SR_ped_weapon[2] = 4
					SR_ped_state[0] = 1

					SR_ped_state[2] = 1
					PRINT_BIG ANR_31 2000 1

				ENDIF

				IF SR_skill_won = 5 //SMG round passed. Can we go to next round?
					IF range_weapons_open <= 2
						SR_mission_state = 20
						sr_flag = 1
					ELSE
						PRINT ANR_44 4000 1	
					ENDIF
				ENDIF

				IF SR_skill_won = 8 //Shotgun round passed. Can we go to next round?
					IF range_weapons_open <= 3
						SR_mission_state = 20
						sr_flag = 1
					ELSE
						PRINT ANR_44 4000 1	
					ENDIF
				ENDIF

				IF SR_skill_won = 11					
					sr_mission_state = 20
					sr_flag = 1
				ENDIF


				SR_skill_won ++
			ENDIF

		BREAK

		CASE 4

			IF SR_flag = 0

					// rotate up targets and dlete

					SR_targets_created[0] = 0
					SR_targets_created[1] = 0
					SR_targets_created[2] = 0

					SR_target_state[0] = 5
					SR_target_state[1] = 5
					SR_target_state[2] = 5
					SR_range_level[0] = 4
					SR_range_level[1] = 4
					SR_range_level[2] = 4		
					SR_flag = 1
					sr_time_check = TIMERA + 6000

			ENDIF

			IF SR_flag = 1
				IF TIMERA > sr_time_check

					IF range_cuts_watched < 3
						SET_FADING_COLOUR 0 0 0
						DO_FADE 1000 FADE_OUT

					ENDIF
					SR_flag = 2
					SR_time_check = TIMERA + 1500
				ENDIF
			ENDIF

			// shows cut of guns shooting down range
			IF SR_flag = 2

				IF range_cuts_watched > 1
						CLEAR_PRINTS
						IF DOES_OBJECT_EXIST sr_target[8]	
							DELETE_OBJECT sr_target[8]
						ENDIF
						CLEAR_THIS_BIG_PRINT ANR_18
						CLEAR_THIS_BIG_PRINT ANR_19

						sr_time_check = 0
						SR_target_state[0] = 0
						SR_target_state[1] = 0
						SR_target_state[2] = 0
						SR_flag = 3
						SR_time_check = 0
				ELSE

					IF TIMERA > SR_time_check
						// Mission level1 Pistol
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON

						CLEAR_THIS_BIG_PRINT ANR_41
						PRINT_BIG ANR_18 4000 1						
						PRINT ANR_9 3000 1
						PRINT ANR_10 3000 1

						SR_float3 = -2.0835
						SR_float4 = 6.0428

						z = 0.0387

						GOSUB getWorldXYZ

						SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0
						
						SR_flag = 3
						SR_time_check = TIMERA + 1000

						SR_target_state[0] = 0
						SR_target_state[1] = 0
						SR_target_state[2] = 0
					ENDIF
				ENDIF
			ENDIF

			IF SR_flag = 3
				IF TIMERA > SR_time_check
					DO_FADE 1000 FADE_IN
					SR_flag = 4
					SR_time_check = TIMERA + 6000
				ENDIF
			ENDIF	

			IF SR_flag = 4

				IF range_cuts_watched = 1
					IF DOES_OBJECT_EXIST sr_target[8]
						GET_OBJECT_COORDINATES sr_target[8] x y z
						z -= 2.0
						POINT_CAMERA_AT_POINT x y z JUMP_CUT
					ENDIF
				ENDIF				

				IF TIMERA > SR_time_check
				OR range_cuts_watched > 1

					IF range_cuts_watched = 1
						range_cuts_watched = 2
					ELSE
						PRINT_BIG ANR_18 2000 1
					ENDIF
					IF range_cuts_watched < 3
						RESTORE_CAMERA_JUMPCUT
						SWITCH_WIDESCREEN OFF
						DETACH_CHAR_FROM_CAR scplayer
						ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_PISTOL
					ENDIF
					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0

					SR_targets_created[0] = 0
					SR_targets_created[1] = 0
					SR_targets_created[2] = 0	

					PRINT_BIG ( RACE2 ) 1000 4 //"3"
		
					SR_flag = 5
					SR_time_check = TIMERA + 1000
					SET_PLAYER_CONTROL player1 ON
				ENDIF
			ENDIF

			IF SR_FLAG = 5
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE3 ) 1000 4 //"2"
					SR_flag = 6
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 6
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE4 ) 1000 4 //"1"
					SR_flag = 7
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 7
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE5 ) 1000 4 //"GO"
					SR_flag = 8
				ENDIF
			ENDIF

			IF SR_flag = 8
				IF SR_target_state[0] = 3
				AND SR_targets_created[0] = 3
					SR_mission_state = 20 //fail
					SR_flag = 0
				ENDIF
				
				IF SR_target_state[2] = 3
				AND SR_targets_created[2] = 3
					SR_mission_state = 20 //fail
					SR_flag = 0
				ENDIF

				IF SR_target_state[1] = 3
				AND SR_targets_created[1] = 3
					SR_mission_state = 3 //pass
					SR_flag = 0
				ENDIF

			ENDIF

			// rotate existing objects away.
			IF sr_flag = 10
				SR_target_state[0] = 5
				SR_target_state[1] = 5
				SR_target_state[2] = 5
				sr_flag = 11		
			ENDIF

			IF sr_flag = 11
				IF SR_target_state[0] = 99
				AND SR_target_state[1] = 99
				AND SR_target_state[2] = 99
					CLEAR_PRINTS
					IF sr_skill_won = 4
						PRINT_BIG ANR_48 3000 1 
					ENDIF

					IF sr_skill_won = 7
						PRINT_BIG ANR_24 3000 1 
					ENDIF

					IF sr_skill_won = 10
						PRINT_BIG ANR_51 3000 1 
					ENDIF
					sr_time_check = TIMERA + 4000
					sr_flag = 12
				ENDIF
			ENDIF

			IF sr_flag = 12
				IF TIMERA > sr_time_check
					SR_range_level[0] = 4
					SR_range_level[1] = 4
					SR_range_level[2] = 4

					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0	

					SR_targets_created[0] = 0
					SR_targets_created[1] = 0
					SR_targets_created[2] = 0	

					PRINT_BIG RACE2 1000 4

					SR_flag = 4
					SR_time_check = TIMERA + 1000
					SET_PLAYER_CONTROL player1 ON
				ENDIF
			ENDIF
												
		BREAK

		CASE 5

			IF range_cuts_watched > 2
				IF SR_flag < 4
				AND sr_flag > 1
					sr_time_check = 0
					SR_flag = 4
				ENDIF
			ENDIF


			IF SR_flag = 0

				// rotate up targets and dlete

				SR_score[0] = 0
				SR_score[1] = 0
				SR_score[2] = 0
				
				SR_targets_created[0] = 0
				SR_targets_created[1] = 0
				SR_targets_created[2] = 0

				SR_target_state[0] = 5
				SR_target_state[1] = 5
				SR_target_state[2] = 5
				SR_range_level[0] = 0
				SR_range_level[1] = 5
				SR_range_level[2] = 0		
				SR_flag = 1
				sr_time_check = TIMERA + 8000
			ENDIF

			IF SR_flag = 1
				IF TIMERA > sr_time_check					
					IF range_cuts_watched < 3
						SET_FADING_COLOUR 0 0 0
						DO_FADE 1000 FADE_OUT

					ENDIF
					SR_flag = 2
					SR_time_check = TIMERA + 1500
				ENDIF
			ENDIF

			// shows cut of guns shooting down range
			IF SR_flag = 2
				IF TIMERA > SR_time_check
					// Mission level1 Pistol
					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON
					SET_PLAYER_CONTROL player1 OFF

					CLEAR_THIS_BIG_PRINT ANR_41
					PRINT_BIG ANR_19 4000 1
					PRINT ANR_33 3000 1
					PRINT ANR_34 3000 1
					PRINT ANR_35 3000 1
					PRINT ANR_46 3000 1

					SR_float3 = 3.0583
					SR_float4 = 0.6889

					z = 1.2953

					GOSUB getWorldXYZ

					SET_FIXED_CAMERA_POSITION x y z 0.0 0.0 0.0


					SR_float3 = 2.9703
					SR_float4 = 1.684
					z = 1.2513

					GOSUB getWorldXYZ
					POINT_CAMERA_AT_POINT x y z JUMP_CUT

					DO_FADE 1000 FADE_IN
					SR_flag = 3
					SR_time_check = TIMERA + 12000

					SR_target_state[0] = 5
					SR_target_state[1] = 0
					SR_target_state[2] = 5
				ENDIF
			ENDIF

			IF SR_flag = 3
				IF TIMERA > SR_time_check

					IF range_cuts_watched < 3
						DO_FADE 1000 FADE_OUT		
					ENDIF				
					SR_time_check = TIMERA + 1500
					SR_flag = 4

					sr_score[0] = 0
					sr_score[1] = 0
					sr_score[2] = 0

				ENDIF
			ENDIF

			IF SR_flag = 4
				IF TIMERA > SR_time_check
					IF NOT GET_FADING_STATUS

						DISPLAY_ONSCREEN_COUNTER_WITH_STRING sr_Score[0] COUNTER_DISPLAY_NUMBER ANR_37
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING sr_Score[1] COUNTER_DISPLAY_NUMBER 2 ANR_39
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING sr_Score[2] COUNTER_DISPLAY_NUMBER 3 ANR_38

						DO_FADE 1000 FADE_IN

						IF range_cuts_watched < 3

							DETACH_CHAR_FROM_CAR scplayer
							ATTACH_CHAR_TO_OBJECT scplayer sr_obj 0.0 0.0 1.0 FACING_FORWARD 90.0 WEAPONTYPE_PISTOL
						
							RESTORE_CAMERA_JUMPCUT

						ENDIF

						IF range_cuts_watched = 2
							range_cuts_watched = 3
						ELSE
							PRINT_BIG ANR_19 2000 1
						ENDIF

						SR_targets_created[0] = 0
						SR_targets_created[1] = 0
						SR_targets_created[2] = 0
						
						SR_target_state[0] = 5
						SR_target_state[1] = 0
						SR_target_state[2] = 5

				       PRINT_BIG ( RACE2 ) 1000 4 //"3"

						SR_flag = 5
						SR_time_check = TIMERA + 1000
						SET_PLAYER_CONTROL player1 ON
						SWITCH_WIDESCREEN OFF
					ENDIF
				ENDIF
			ENDIF

			IF SR_FLAG = 5
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE3 ) 1000 4 //"2"
					SR_flag = 6
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 6
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE4 ) 1000 4 //"1"
					SR_flag = 7
					SR_time_check = TIMERA + 1000
				ENDIF
			ENDIF

			IF SR_FLAG = 7
				IF TIMERA > SR_time_check
			       PRINT_BIG ( RACE5 ) 1000 4 //"GO"
					SR_flag = 8
					sr_score[0] = 0
					sr_score[1] = 0
					sr_score[2] = 0
				ENDIF
			ENDIF

			IF SR_Flag = 8
				IF sr_score[1] >= 20
					SR_mission_state = 3
					SR_flag = 0
					CLEAR_ONSCREEN_COUNTER sr_Score[0]
					CLEAR_ONSCREEN_COUNTER sr_Score[1]
					CLEAR_ONSCREEN_COUNTER sr_Score[2]

					// player wins - end of pistol round
					// increase pistol stats
					// change weapon to uzi
				ENDIF
				IF sr_score[0] >= 20
				OR sr_score[2] >= 20
					SR_mission_state = 20
					sr_flag = 0
					CLEAR_ONSCREEN_COUNTER sr_Score[0]
					CLEAR_ONSCREEN_COUNTER sr_Score[1]
					CLEAR_ONSCREEN_COUNTER sr_Score[2]
				ENDIF	
			ENDIF

			// rotate existing objects away.
			IF sr_flag = 10
				SR_target_state[0] = 5
				SR_target_state[1] = 5
				SR_target_state[2] = 5
				sr_time_check = TIMERA + 4000
				sr_flag = 11		
			ENDIF

			IF sr_flag = 11
				IF SR_target_state[0] = 99
				AND SR_target_state[1] = 99
				AND SR_target_state[2] = 99
				AND TIMERA > sr_time_check
					CLEAR_PRINTS
					IF sr_skill_won = 5
						PRINT_BIG ANR_49 2000 1 
					ENDIF

					IF sr_skill_won = 8
						PRINT_BIG ANR_25 2000 1 
					ENDIF

					IF sr_skill_won = 11
						PRINT_BIG ANR_52 2000 1 
					ENDIF
					sr_time_check = TIMERA + 4000
					sr_flag = 12
				ENDIF

			ENDIF

			IF sr_flag = 12
				IF TIMERA > sr_time_check
					SR_score[0] = 0
					SR_score[1] = 0
					SR_score[2] = 0
					
					SR_targets_created[1] = 0

					SR_target_state[1] = 0
					SR_range_level[0] = 0
					SR_range_level[1] = 5
					SR_range_level[2] = 0

					sr_score[0] = 0
					sr_score[1] = 0
					sr_score[2] = 0

					DISPLAY_ONSCREEN_COUNTER_WITH_STRING sr_Score[0] COUNTER_DISPLAY_NUMBER ANR_37
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING sr_Score[1] COUNTER_DISPLAY_NUMBER 2 ANR_39
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING sr_Score[2] COUNTER_DISPLAY_NUMBER 3 ANR_38


			       PRINT_BIG ( RACE2 ) 1000 4 //"3"

					SR_flag = 5
					SR_time_check = TIMERA + 1000
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
				ENDIF
			ENDIF
 

		BREAK

		CASE 20
			IF SR_flag = 0
				PRINT_BIG ANR_16 3000 1 //failed
				sr_time_check = TIMERA + 3000
				SR_flag = 1
			ENDIF
			IF SR_flag = 1
				IF TIMERA > sr_time_check
						DO_FADE 1000 FADE_OUT  
					SR_flag = 2
				ENDIF
			ENDIF
			IF SR_flag = 2
				IF NOT GET_FADING_STATUS
					sr_time_check = TIMERA + 500
					SHOW_UPDATE_STATS TRUE

					SR_flag = 3					
				ENDIF
			ENDIF
			IF SR_flag = 3
				IF TIMERA > sr_time_check
					sr_float3 = 2.0
					sr_float4 = -12.0
					z = 0.0

					CLEAR_ONSCREEN_COUNTER sr_Score[0]
					CLEAR_ONSCREEN_COUNTER sr_Score[1]
					CLEAR_ONSCREEN_COUNTER sr_Score[2]

					DETACH_CHAR_FROM_CAR scplayer

					GOSUB getWorldXYZ

					SET_PLAYER_CYCLE_WEAPON_BUTTON Player1 TRUE

					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PISTOL 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_DESERT_EAGLE 

					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MICRO_UZI 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MP5 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_TEC9 

					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_SHOTGUN 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_SAWNOFF_SHOTGUN 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_SPAS12_SHOTGUN 
					
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_AK47 
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_M4 

					IF ammo[0] > 0
 
						WHILE NOT HAS_MODEL_LOADED ModelForweapontype[0]
							REQUEST_MODEL ModelForweapontype[0]
							WAIT 0
						ENDWHILE
						
						GIVE_WEAPON_TO_CHAR scplayer weapontype[0] ammo[0]
					ENDIF

					IF ammo[1] > 0
						WHILE NOT HAS_MODEL_LOADED ModelForweapontype[1]
							REQUEST_MODEL ModelForweapontype[1]
							WAIT 0
						ENDWHILE
						
						GIVE_WEAPON_TO_CHAR scplayer weapontype[1] ammo[1]
					ENDIF

					IF ammo[2] > 0
					   	WHILE NOT HAS_MODEL_LOADED ModelForweapontype[2]
							REQUEST_MODEL ModelForweapontype[2]
							WAIT 0
						ENDWHILE
						
						GIVE_WEAPON_TO_CHAR scplayer weapontype[2] ammo[2]
					ENDIF
					
					IF ammo[3] > 0
						
						WHILE NOT HAS_MODEL_LOADED ModelForweapontype[3]
							REQUEST_MODEL ModelForweapontype[3]
							WAIT 0
						ENDWHILE
						
						GIVE_WEAPON_TO_CHAR scplayer weapontype[3] ammo[3]
					ENDIF
					
					SET_PLAYER_DUCK_BUTTON Player1 TRUE
					DELETE_OBJECT sr_obj
  
					CLEAR_PRINTS
					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer x y z 
					IF NOT IS_CHAR_DEAD scplayer
						FREEZE_CHAR_POSITION scplayer FALSE
					ENDIF
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					IF NOT IS_CHAR_DEAD scplayer
						SET_CHAR_HEADING scplayer range_heading[SR_range_id]
					ENDIF
					SR_ped_can_be_created[1] = 1

					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					DO_FADE 1000 FADE_IN
					SR_mission_state = 0
					SR_flag = 0
					SR_targets_created[0] = 0
					SR_targets_created[1] = 0
					SR_targets_created[2] = 0
					SR_ped_weapon[0] = 0
					SR_ped_weapon[1] = 0
					SR_ped_weapon[2] = 0
					SR_ped_state[0] = 1
					IF IS_CHAR_DEAD sr_ped[1]
						SR_ped_state[1] = 0
					ENDIF
					SR_ped_state[2] = 1
					SR_range_level[0] = 0
					SR_range_level[1] = 0
					SR_range_level[2] = 0
					SR_target_state[0] = 0
					SR_target_state[1] = 0
					SR_target_state[2] = 0
					
					IF SR_quit_range = 1
						CLEAR_THIS_PRINT_BIG_NOW 1
						PRINT ANR_A1 4000 1
						SR_quit_range = 0
					ENDIF

					IF NOT range_weapons_open = 4
					AND NOT SR_quit_range = 1
						PRINT ANR_55 4000 1		
					ENDIF

				ENDIF
			ENDIF
			
		BREAK

	ENDSWITCH

RETURN

SR_AI:

	SR_i = 0

	VAR_INT this_Watch[9]

	this_watch[0] = SR_target_state[0]
	this_watch[3] = SR_target_state[1]
	this_watch[6] = SR_target_state[2]

	WHILE SR_i < 3

		SWITCH SR_ped_state[sr_i]
			CASE 0 //ped no created

				IF SR_ped_can_be_created[sr_i] = 1
					SR_ped_state[sr_i] = 1	

					x = booth_x[SR_range_id]
					y = booth_y[SR_range_id]
					z = booth_z[SR_range_id]
					h = range_heading[SR_range_id]
					
					IF sr_i = 1
						IF h = 90.0
							y += booth_offset_1[SR_range_id]
						ENDIF
						IF h = 270.0
							y -= booth_offset_1[SR_range_id]
						ENDIF
						IF h = 0.0
							x += booth_offset_1[SR_range_id]
						ENDIF
					ENDIF
					IF sr_i = 2
						IF h = 90.0
							y += booth_offset_2[SR_range_id]
						ENDIF
						IF h = 270.0
							y -= booth_offset_2[SR_range_id]
						ENDIF
						IF h = 0.0
							x += booth_offset_2[SR_range_id]
						ENDIF
					ENDIF

					CREATE_RANDOM_CHAR X Y Z SR_ped[sr_i]
					IF NOT IS_CHAR_DEAD SR_ped[sr_i]
						
						SET_CHAR_PROOFS SR_ped[sr_i] TRUE TRUE TRUE TRUE TRUE
						SET_CHAR_DECISION_MAKER SR_ped[sr_i] SR_dec

						SET_CHAR_ACCURACY sr_ped[sr_i] 93

						SET_CHAR_HEADING SR_ped[sr_i] h
					ENDIF


				ENDIF
			BREAK

			CASE 1 // select weapon
				IF NOT IS_CHAR_DEAD SR_ped[sr_i]
					
					IF SR_ped_weapon[sr_i] = 0
						// select a random weapon
						GENERATE_RANDOM_INT_IN_RANGE 1 5 SR_ped_weapon[sr_i]
					ENDIF
					IF SR_ped_weapon[sr_i] = 1
						GIVE_WEAPON_TO_CHAR SR_ped[sr_i] WEAPONTYPE_PISTOL 99999
						SET_CURRENT_CHAR_WEAPON SR_ped[sr_i] WEAPONTYPE_PISTOL
					ENDIF
					IF SR_ped_weapon[sr_i] = 2
						GIVE_WEAPON_TO_CHAR SR_ped[sr_i] WEAPONTYPE_MICRO_UZI 99999
						SET_CURRENT_CHAR_WEAPON SR_ped[sr_i] WEAPONTYPE_MICRO_UZI
					ENDIF
					IF SR_ped_weapon[sr_i] = 3
						GIVE_WEAPON_TO_CHAR SR_ped[sr_i] WEAPONTYPE_SHOTGUN 99999
						SET_CURRENT_CHAR_WEAPON SR_ped[sr_i] WEAPONTYPE_SHOTGUN
					ENDIF
					IF SR_ped_weapon[sr_i] = 4
						GIVE_WEAPON_TO_CHAR SR_ped[sr_i] WEAPONTYPE_AK47 99999
						SET_CURRENT_CHAR_WEAPON SR_ped[sr_i] WEAPONTYPE_AK47
					ENDIF
					SR_ped_state[sr_i] = 2
				ENDIF
			BREAK

			CASE 2 //shoot at targets
				sr_int1 = 0
				IF SR_target_state[sr_i] = 2
					sr_int1 = 1
				ENDIF
				
				IF sr_mission_state = 5
				AND SR_target_state[1] = 2
					sr_int1 = 1
				ENDIF

				IF sr_i = 0
					this_watch[1] = sr_int1
				ENDIF	

				IF sr_i = 1
					this_watch[4] = sr_int1
				ENDIF	

				IF sr_i = 2
					this_watch[7] = sr_int1
				ENDIF	

				IF sr_int1 = 1
					IF TIMERA > sr_time_to_new_target[sr_i]
						//choose an element to aim at
						GENERATE_RANDOM_INT_IN_RANGE 1 8 sr_int1
						
						IF sr_mission_state = 5
							sr_int1 += 8
						ELSE

							IF sr_i = 2
								sr_int1 += 16				
							ENDIF
							IF sr_i = 1
								sr_int1 += 8
							ENDIF
						ENDIF

						sr_j = 0

						WHILE HAS_OBJECT_BEEN_DAMAGED sr_target[sr_int1]
						AND sr_j < 8
							sr_int1 ++
							sr_j ++
							IF sr_int1 = 8
							OR sr_int1 = 24
							OR sr_int1 = 16
								sr_int1 -= 7
							ENDIF
							
						ENDWHILE 

						IF sr_i = 0
							this_watch[2] = sr_j
						ENDIF

						IF sr_i = 1
							this_watch[5] = sr_j
						ENDIF

						IF sr_i = 2
							this_watch[8] = sr_j
						ENDIF

						IF NOT IS_CHAR_DEAD SR_ped[sr_i]
							GET_OBJECT_COORDINATES sr_target[sr_int1] x y z
							IF sr_int1 > 8 
							AND sr_int1 < 16
								sr_int1 -= 8
							ENDIF
							IF sr_int1 > 16 
							AND sr_int1 < 24
								sr_int1 -= 16
							ENDIF

							IF h = 0.0
								x += target_offset_x[sr_int1]
							ENDIF
							IF h = 90.0
								y += target_offset_x[sr_int1]
							ENDIF
							IF h = 270.0
								y -= target_offset_x[sr_int1]
							ENDIF

							z += target_offset_z[sr_int1]
			
							IF SR_skill_won >= 8
								GET_SCRIPT_TASK_STATUS SR_ped[sr_i] TASK_SHOOT_AT_COORD sr_int1
								IF sr_int1 = FINISHED_TASK
									FREEZE_CHAR_POSITION SR_ped[sr_i] TRUE
									TASK_SHOOT_AT_COORD SR_ped[sr_i] x y z 800
									SET_CHAR_ACCURACY SR_ped[sr_i] 95
								ENDIF
							ELSE
								GET_SCRIPT_TASK_STATUS SR_ped[sr_i] TASK_SHOOT_AT_COORD sr_int1
								IF sr_int1 = FINISHED_TASK
									FREEZE_CHAR_POSITION SR_ped[sr_i] TRUE
									TASK_SHOOT_AT_COORD SR_ped[sr_i] x y z 1500
								ENDIF
							ENDIF

							IF SR_skill_won >= 8
								GENERATE_RANDOM_INT_IN_RANGE 200 400 sr_int1
								sr_time_to_new_target[sr_i] += 600
							ELSE		
								GENERATE_RANDOM_INT_IN_RANGE 200 1600 sr_int1
							ENDIF
							sr_time_to_new_target[sr_i] = TIMERA + sr_int1
		
						ENDIF
					ENDIF
				ELSE
					IF NOT IS_CHAR_DEAD SR_ped[sr_i]
						GET_SCRIPT_TASK_STATUS SR_ped[sr_i] TASK_SHOOT_AT_COORD sr_int1
						IF NOT sr_int1 = FINISHED_TASK
							CLEAR_CHAR_TASKS SR_ped[sr_i]
						ENDIF
					ENDIF
				ENDIF

			BREAK

			CASE 3 //leave range

				IF NOT IS_CHAR_DEAD SR_ped[sr_i]
					GET_CHAR_COORDINATES SR_ped[sr_i] x y z			
					FREEZE_CHAR_POSITION SR_ped[sr_i] FALSE
					FLUSH_ROUTE

					OPEN_SEQUENCE_TASK SR_seq

					SR_float3 = 0.0 + booth_offset_1[SR_range_id]
					SR_float4 = -1.0

					COS range_heading[SR_range_id] SR_float1
					SIN range_heading[SR_range_id] SR_float2 

					IF range_heading[SR_range_id] = 0.0
						x = SR_float3 * SR_float1
						y = SR_float4 * SR_float1
					ELSE
						SR_float4 *= -1.0
						x = SR_float4 * SR_float2
						y = SR_float3 * SR_float2
					ENDIF

					x += booth_x[SR_range_id]
					y += booth_y[SR_range_id]
					z += booth_z[SR_range_id]
					h = range_heading[SR_range_id]
					h -= 180.0

					TASK_ACHIEVE_HEADING -1 h

					h = range_heading[SR_range_id]

					EXTEND_ROUTE x y z

					SR_float3 = -0.5 + booth_offset_1[SR_range_id]
					SR_float4 = -4.5

					IF range_heading[SR_range_id] = 0.0
						x = SR_float3 * SR_float1
						y = SR_float4 * SR_float1
					ELSE
						SR_float4 *= -1.0
						x = SR_float4 * SR_float2
						y = SR_float3 * SR_float2
					ENDIF

					x += booth_x[SR_range_id]
					y += booth_y[SR_range_id]
					z += booth_z[SR_range_id]

					EXTEND_ROUTE x y z						
					 
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE

					CLOSE_SEQUENCE_TASK SR_seq
					PERFORM_SEQUENCE_TASK SR_ped[sr_i] SR_seq
					CLEAR_SEQUENCE_TASK SR_seq
					SR_ped_state[sr_i] = 99
				ENDIF						
				
			BREAK

			CASE 4 //remove ped 
				
				IF NOT IS_CHAR_DEAD SR_ped[sr_i]
					DELETE_CHAR SR_ped[sr_i]
				ENDIF

				SR_ped_state[sr_i] = 0

			BREAK

		ENDSWITCH

		SR_i ++

	ENDWHILE

RETURN

SR_targets:

	SR_i = 0

	WHILE SR_i < 3

		SWITCH SR_target_state[sr_i]
			CASE 0 //create target

				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE SR_dec EVENT_SHOT_FIRED

				SR_target_state[sr_i] = 1

				IF SR_range_level[sr_i] = 0
					GENERATE_RANDOM_INT_IN_RANGE 1 3 SR_range_level[sr_i]
					IF SR_range_level[sr_i] = 2
						SR_range_level[sr_i] = 4
					ENDIF
				ENDIF

				x = 0.0
				y = 5.0
				z = 4.0

				h = range_heading[SR_range_id]
	
				IF SR_range_level[sr_i] < 5
					IF sr_i = 1
						x += booth_offset_1[SR_range_id]
					ENDIF

					IF sr_i = 2
						x += booth_offset_2[SR_range_id]
					ENDIF

					//Set the Distance of the target from the player, for each round
					IF SR_range_level[sr_i] = 1
						y += 0.0
					ENDIF

					IF SR_range_level[sr_i] = 2
						y += 5.0
					ENDIF

					IF SR_range_level[sr_i] = 3
					OR SR_range_level[sr_i] = 4
						y += 10.0
					ENDIF

				ENDIF

				IF SR_range_level[sr_i] = 5
					// create target at random side of room going left or right.
					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 10.0 sr_yoffset
					y += sr_yoffset
					
					GENERATE_RANDOM_INT_IN_RANGE 0 2 sr_direction
					IF sr_direction = 1
						x += booth_offset_2[SR_range_id]
					ENDIF
						
				ENDIF

				SR_float3 = x
				SR_float4 = y

				GOSUB getWorldXYZ

				sr_int1 = sr_i * 8

				IF DOES_OBJECT_EXIST sr_target[sr_int1]
					DELETE_OBJECT sr_target[sr_int1]
				ENDIF
				CREATE_OBJECT_NO_OFFSET sr_object_name[0] x y z sr_target[sr_int1]
				SET_OBJECT_ROTATION sr_target[sr_int1] 90.0 0.0 h

				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_int1] SOUND_SHOOTING_RANGE_TARGET_DROP

				sr_target_rotation[sr_i] = 90.0

				sr_j = 1
				WHILE sr_j < 8

					sr_obj_number = sr_i * 8
					sr_obj_number += sr_j

					IF DOES_OBJECT_EXIST sr_target[sr_obj_number]
						DELETE_OBJECT sr_target[sr_obj_number]
					ENDIF
					
					CREATE_OBJECT_NO_OFFSET sr_object_name[sr_j] x y z sr_target[sr_obj_number]

					ATTACH_OBJECT_TO_OBJECT sr_target[sr_obj_number] sr_target[sr_int1] 0.0 0.0 0.0 0.0 0.0 0.0	
										
					IF DOES_OBJECT_EXIST sr_target[sr_obj_number] 
						SET_OBJECT_ROTATION sr_target[sr_obj_number] 90.0 0.0 h
						SET_OBJECT_COLLISION sr_target[sr_obj_number] FALSE
					ENDIF

					sr_j ++
				ENDWHILE

				SR_targets_created[sr_i] ++
				sr_target_advance_time[sr_i] = TIMERA

			BREAK

			CASE 1 // rotate object

					sr_int1 = TIMERA - sr_target_advance_time[sr_i]

					IF sr_int1 > 3000
						sr_int1 = 3000
					ENDIF

					sr_float1 =# sr_int1
					sr_float1 /= 3000.0
					sr_float1 *= -90.0
					sr_float1 += 90.0


					sr_target_rotation[sr_i] = sr_float1
					sr_obj_number = sr_i * 8

					IF DOES_OBJECT_EXIST sr_target[sr_obj_number]
						SET_OBJECT_ROTATION sr_target[sr_obj_number] sr_target_rotation[sr_i] 0.0 h			
					ENDIF

					IF sr_target_rotation[sr_i] <= 0.0

						SR_target_state[sr_i] = 2				
						sr_target_advance_time[sr_i] = TIMERA

						IF sr_range_level[sr_i] = 4
						OR sr_range_level[sr_i] = 5
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_START
//							temp_audio_flag[sr_obj_number] = 3
						ENDIF

						sr_j = 0
						WHILE sr_j < 8
							sr_obj_number = sr_i * 8
							sr_obj_number += sr_j
							SET_OBJECT_COLLISION sr_target[sr_obj_number] TRUE
							sr_j ++
						ENDWHILE	

					ENDIF
			BREAK

			CASE 2 //move object
				IF SR_range_level[sr_i] = 4

					sr_int1 = TIMERA - sr_target_advance_time[sr_i]

					IF SR_targets_created[sr_i] = 4
						SR_targets_created[sr_i] = 1
					ENDIF

					IF SR_targets_created[sr_i] = 1
						IF sr_int1 > 20000
							sr_int1 = 20000
							sr_obj_number = sr_i * 8
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
						ENDIF
					ENDIF

					IF SR_targets_created[sr_i] = 2
						IF sr_int1 > 15000
							sr_int1 = 15000
							sr_obj_number = sr_i * 8
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
						ENDIF
					ENDIF

					IF SR_targets_created[sr_i] = 3
						IF sr_int1 > 10000
							sr_int1 = 10000
							sr_obj_number = sr_i * 8
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
						ENDIF
					ENDIF

					sr_float1 =# sr_int1

					IF SR_targets_created[sr_i] = 1
						sr_float1 /= 20000.0
					ENDIF
					IF SR_targets_created[sr_i] = 2
						sr_float1 /= 15000.0
					ENDIF
					IF SR_targets_created[sr_i] = 3
						sr_float1 /= 10000.0
					ENDIF

					sr_float1 *= 10.0

					sr_obj_number = sr_i * 8
					GET_OBJECT_COORDINATES sr_target[sr_obj_number] x y z

					IF h = 90.0						
						x = booth_x[SR_range_id]
						x -= 15.0
						x += sr_float1
					ENDIF

					IF h = 270.0
						x = booth_x[SR_range_id]
						x += 15.0
						x -= sr_float1
					ENDIF
					IF h = 0.0
						y = booth_y[SR_range_id]
						y += 15.0
						y -= sr_float1
					ENDIF

					//set the target position
					sr_obj_number = sr_i * 8
					SET_OBJECT_COORDINATES sr_target[sr_obj_number] x y z 

				ENDIF

				IF SR_range_level[sr_i] = 5

					sr_int1 = TIMERA - sr_target_advance_time[sr_i]

					IF sr_int1 > 10000
						sr_int1 = 10000
						SR_target_state[sr_i] = 3
						sr_obj_number = sr_i * 8
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION x y z SOUND_SHOOTING_RANGE_TARGET_DROP
						sr_target_advance_time[sr_i] = TIMERA
						sr_j = 0
						WHILE sr_j < 8
							sr_obj_number = sr_i * 8
							sr_obj_number += sr_j
							SET_OBJECT_COLLISION sr_target[sr_obj_number] FALSE
							sr_j ++
						ENDWHILE
					ENDIF

					sr_float1 =# sr_int1
					sr_float1 /= 10000.0
					sr_float1 *= booth_offset_2[SR_range_id]

					sr_obj_number = sr_i * 8

					IF sr_direction = 0
						sr_float3 = sr_float1
					ELSE
						sr_float3 = booth_offset_2[SR_range_id] - sr_float1
					ENDIF

					z = 4.0
					sr_float4 = sr_yoffset + 5.0

					GOSUB getWOrldXYZ

					SET_OBJECT_COORDINATES sr_target[sr_obj_number] x y z 

				ENDIF

				sr_int1 = sr_destroyed_elements[sr_i]

				sr_destroyed_elements[sr_i] = 0

				sr_j = 0
				WHILE sr_j < 8
					
					sr_obj_number = sr_i * 8
					sr_obj_number += sr_j

					IF HAS_OBJECT_BEEN_DAMAGED sr_target[sr_obj_number]
						sr_destroyed_elements[sr_i] ++
					ENDIF
					sr_j++
				ENDWHILE

				IF sr_destroyed_elements[sr_i] > sr_int1
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_SHATTER					
				ENDIF

				IF sr_i = 1
					IF sr_destroyed_elements[1] > sr_int1

						sr_int2 = sr_destroyed_elements[1] - sr_int1
						
					   GENERATE_RANDOM_INT_IN_RANGE 0 2 sr_int1

						IF sr_int1 = 0
							IF NOT IS_CHAR_DEAD sr_ped[0]
								IF IS_CHAR_SHOOTING sr_ped[0]
									sr_score[0] += sr_int2
								ELSE
									IF IS_CHAR_SHOOTING scplayer
										sr_score[1] += sr_int2
									ELSE
										IF NOT IS_CHAR_DEAD sr_ped[2]
											IF IS_CHAR_SHOOTING sr_ped[2]
												sr_score[2] += sr_int2
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ELSE
							IF NOT IS_CHAR_DEAD sr_ped[2]
								IF IS_CHAR_SHOOTING sr_ped[2]
									sr_score[2] += sr_int2
								ELSE
									IF IS_CHAR_SHOOTING scplayer
										sr_score[1] += sr_int2
									ELSE
										IF NOT IS_CHAR_DEAD sr_ped[0]
											IF IS_CHAR_SHOOTING sr_ped[0]
												sr_score[0] += sr_int2
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF sr_destroyed_elements[sr_i] = 7
					sr_target_advance_time[sr_i] = TIMERA
					SR_target_state[sr_i] = 3
					sr_obj_number = sr_i * 8
					
//					temp_audio_flag[sr_obj_number] = 4
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION x y z SOUND_SHOOTING_RANGE_TARGET_DROP

					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE SR_dec EVENT_SHOT_FIRED
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE SR_dec EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 100.0 100.0 100.0 100.0 TRUE TRUE
				ENDIF

			BREAK

			CASE 3 //rotate object up
				sr_int1 = TIMERA - sr_target_advance_time[sr_i]

				IF sr_int1 > 2000
					sr_int1 = 2000
				ENDIF

				sr_float1 =# sr_int1
				sr_float1 /= 2000.0
				sr_float1 *= 90.0

				sr_target_rotation[sr_i] = sr_float1
				sr_obj_number = sr_i * 8

				IF DOES_OBJECT_EXIST sr_target[sr_obj_number]
					SET_OBJECT_ROTATION sr_target[sr_obj_number] sr_target_rotation[sr_i] 0.0 h
				ENDIF

				IF sr_target_rotation[sr_i] >= 90.0

					SR_target_state[sr_i] = 0

					IF SR_range_level[sr_i] = 3
						SR_range_level[sr_i] = 1
					ELSE
						IF SR_range_level[sr_i] = 2
							SR_range_level[sr_i] = 3
						ELSE
							IF SR_range_level[sr_i] = 1
								SR_range_level[sr_i] = 2
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE 4 //force delete object

				sr_j = 0
				WHILE sr_j < 8
					
					sr_obj_number = sr_i * 8
					sr_obj_number += sr_j

					IF DOES_OBJECT_EXIST sr_target[sr_j]
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_j] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
						DELETE_OBJECT sr_target[sr_j]
					ENDIF
					sr_j++
				ENDWHILE

				SR_target_state[sr_i] = 99				
				
			BREAK

			CASE 5 //rotate object up and delete
				sr_target_rotation[sr_i] += 1.0
				sr_obj_number = sr_i * 8

				IF DOES_OBJECT_EXIST sr_target[sr_obj_number]
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT sr_target[sr_obj_number] SOUND_SHOOTING_RANGE_TARGET_MOVE_STOP
					SET_OBJECT_ROTATION sr_target[sr_obj_number] sr_target_rotation[sr_i] 0.0 h
				ENDIF

				IF sr_target_rotation[sr_i] >= 90.0
					SR_target_state[sr_i] = 4	
				ENDIF
			BREAK

		ENDSWITCH
		sr_i ++
	ENDWHILE			 

RETURN

getWorldXYZ:
	COS range_heading[SR_range_id] SR_float1
	SIN range_heading[SR_range_id] SR_float2 

	IF range_heading[SR_range_id] = 0.0
		x = SR_float3 * SR_float1
		y = SR_float4 * SR_float1
	ELSE
		SR_float4 *= -1.0
		x = SR_float4 * SR_float2
		y = SR_float3 * SR_float2
	ENDIF

	x += booth_x[SR_range_id]
	y += booth_y[SR_range_id]
	z += booth_z[SR_range_id]
RETURN


SR_cleanup:

	DELETE_CHAR SR_ped[0]
	DELETE_CHAR SR_ped[1]
	DELETE_CHAR SR_ped[2]

	CLEAR_ONSCREEN_COUNTER sr_Score[0]
	CLEAR_ONSCREEN_COUNTER sr_Score[1]
	CLEAR_ONSCREEN_COUNTER sr_Score[2]

	MARK_MODEL_AS_NO_LONGER_NEEDED target_head
	MARK_MODEL_AS_NO_LONGER_NEEDED target_larm
	MARK_MODEL_AS_NO_LONGER_NEEDED target_rarm
	MARK_MODEL_AS_NO_LONGER_NEEDED target_lleg
	MARK_MODEL_AS_NO_LONGER_NEEDED target_rleg
	MARK_MODEL_AS_NO_LONGER_NEEDED target_rtorso
	MARK_MODEL_AS_NO_LONGER_NEEDED target_ltorso
	MARK_MODEL_AS_NO_LONGER_NEEDED target_frame

	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED AK47

	REMOVE_DECISION_MAKER sr_dec

	CLEAR_MISSION_AUDIO 4
  
	sr_j = 0
	WHILE sr_j < 24

		IF DOES_OBJECT_EXIST sr_target[sr_j]
			DELETE_OBJECT sr_target[sr_j]
		ENDIF
		sr_j ++
	ENDWHILE

	flag_shooting_range_mission = 0
	flag_player_on_mission = 0

	IF NOT IS_CHAR_DEAD scplayer
		FREEZE_CHAR_POSITION scplayer FALSE
	ENDIF

	TERMINATE_THIS_SCRIPT

RETURN
		   
}

MISSION_END
