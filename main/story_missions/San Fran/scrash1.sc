MISSION_START
// *****************************************************************************************
// **************************************** scrash1 ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
																				  
SCRIPT_NAME scrash3

GOSUB mission_start_scrash3
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_scrash3_failed
ENDIF
GOSUB mission_cleanup_scrash3
MISSION_END
{ 
// Variables for mission
//people

LVAR_INT sc3_valet sc3_valet1 sc3_valet2 sc3_missionchar
LVAR_FLOAT sc3_valetX sc3_valetY sc3_valetZ	sc3_x sc3_y sc3_z
LVAR_FLOAT sc3_player_X sc3_player_Y sc3_player_Z sc3_carX sc3_carY sc3_carZ sc3_h


// vehicles

LVAR_INT sc3_bike sc3_car1  sc3_playerscar sc3_copcar1 sc3_copcar2
LVAR_INT sc3_here_comes_the_prosecutor


//blips

LVAR_INT sc3_valet_blip sc3_hotel_blip sc3_drug_plant_blip sc3_missioncar_blip
LVAR_INT sc3_blip[3] val_uniform_blip

//flags

LVAR_INT sc3_flag sc3_vehicle_warning timer_running sc3_missionchar_alive 
LVAR_INT sc3_player_straying sc3_mini_flag

LVAR_INT sc3_cut_time 
LVAR_INT sc3_cut_flag sc3_uniform_given

// objects


//clothes pickup
LVAR_INT val_uniform_pickup										   

//groups


//colours
LVAR_INT sc3_col1 sc3_col2

//coords



// route nodes array

// Other

LVAR_INT sc3_var1 sc3_var2 sc3_var3

LVAR_INT sc3_alive sc3_driving  sc3_warning
LVAR_INT sc3_carhealth sc3_valet_cycle_fast sc3_player_in_car

VAR_INT sc3_countdown

// temp

// hiding areas

// sequences

LVAR_INT sc3_seq1 sc3_missioncar_seq

//decisions

LVAR_INT sc3_dec

//debug


// ****************************************Mission Start************************************
mission_start_scrash3:
CLEAR_THIS_PRINT M_FAIL
flag_player_on_mission = 1
playing_scrash1 = 1
REGISTER_MISSION_GIVEN
//LOAD_MISSION_TEXT wuzi1
//DO_FADE 0 FADE_IN
//WAIT 0

// *************************************Set Flags/variables*********************************
sc3_alive = 1
sc3_driving = 0
sc3_warning = 0
sc3_flag = 0
sc3_vehicle_warning = 0
sc3_valet_cycle_fast = 0 
sc3_player_in_car = 0


SET_SCRIPT_LIMIT_TO_GANG_SIZE 0






LOAD_MISSION_TEXT VALET1

SET_FADING_COLOUR 0 0 0


TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME valet
IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
	GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
    BUILD_PLAYER_MODEL player1
ENDIF


//WAIT 0


//PRINT wz1_1 3000 1


			SET_AREA_VISIBLE 1
//------------------REQUEST_MODELS ------------------------------

            LOAD_CUTSCENE SCRASH1


            WHILE NOT HAS_CUTSCENE_LOADED
                        WAIT 0
            ENDWHILE

            START_CUTSCENE

            SET_FADING_COLOUR 0 0 0
            DO_FADE 1000 FADE_IN

            WHILE NOT HAS_CUTSCENE_FINISHED
                        WAIT 0
            ENDWHILE

            

            DO_FADE 0 FADE_OUT
            WHILE GET_FADING_STATUS
            	WAIT 0
            ENDWHILE
			CLEAR_CUTSCENE

			SET_AREA_VISIBLE 0


	SET_CHAR_HEADING scplayer 270.0
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT




REQUEST_MODEL CHEETAH
REQUEST_MODEL SWMYRI

WHILE NOT HAS_MODEL_LOADED CHEETAH
OR NOT HAS_MODEL_LOADED SWMYRI
	WAIT 0
ENDWHILE





  
// ****************************************START OF CUTSCENE********************************


// ******************************************END OF CUTSCENE********************************

SET_CAR_DENSITY_MULTIPLIER 1.0
	
sc3_flag = 0
//GOTO blah

//VIEW_INTEGER_VARIABLE sc3_missioncar sc3_missioncar

IF sc3_flag = 20
	ADD_BLIP_FOR_COORD -1748.4017 909.7514 23.8906 sc3_valet_blip
	ADD_BLIP_FOR_COORD -1748.4017 909.7514 23.8906 sc3_missioncar_blip
	ADD_BLIP_FOR_COORD -1748.4017 909.7514 23.8906 sc3_drug_plant_blip
	CREATE_CHAR PEDTYPE_MISSION1 wmyva 0.0 0.0 0.0 sc3_missionchar


//IF checks_time_A = 99
	CREATE_CHAR PEDTYPE_CIVMALE wmyva 0.0 0.0 0.0 valet[0]
//	CREATE_CHAR PEDTYPE_CIVMALE wmyva valet_la_x[valet_i] valet_la_y[valet_i] valet_la_z[valet_i] a_thief
	CREATE_CAR CHEETAH 0.0 0.0 0.0 valet_pickup_car
//	CREATE_CAR CHEETAH 0.0 0.0 0.0 valet_car[1]
	CREATE_CAR CHEETAH 0.0 0.0 0.0 drop_off_car
//	CREATE_CAR CHEETAH 0.0 0.0 0.0 	valet_mission_car
	CREATE_CAR CHEETAH 0.0 0.0 0.0 	sc3_missioncar
//	CREATE_CAR CHEETAH 0.0 0.0 0.0 	mission_drop_off_car
//	CREATE_CAR CHEETAH 0.0 0.0 0.0 	a_pickup_car
//	ADD_BLIP_FOR_CAR valet_pickup_car pickup_car_blip
//	ADD_BLIP_FOR_COORD target_x target_y target_z parking_space_blip
//	COPY_CHAR_DECISION_MAKER DM_PED_EMPTY valet_empty_dec
//	ADD_BLIP_FOR_COORD target_x target_y target_z drop_off_car_blip
//ENDIF













ENDIF

 
ADD_BLIP_FOR_COORD -1748.4017 909.7514 23.8906 sc3_valet_blip


	


WAIT 500
DO_FADE 1000 FADE_IN

play_audio = 0
PRINT VAL_5 10000 1  // ~s~The D.A. is on his way to the ~y~Vank Hoff Hotel~s~ in downtown San Fierro. Get over there now.

//VIEW_INTEGER_VARIABLE sc3_here_comes_the_prosecutor sc3_here_comes_the_prosecutor

mission_scrash3_loop:

WAIT 0


IF IS_WANTED_LEVEL_GREATER Player1 0
	player_has_wanted_level = 1
ELSE
	player_has_wanted_level = 0
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOSUB cleanup_uniform
    GOTO mission_scrash3_passed  
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_D

	SET_CHAR_COORDINATES scplayer -1755.0876 910.0911 23.8906
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
	SET_CHAR_COORDINATES scplayer -1712.6528 985.0446 16.5859
	IF NOT IS_CHAR_DEAD valet[2]
		SET_CHAR_COORDINATES valet[2] -1700.7703 984.3545 16.5936
		SET_CHAR_SUFFERS_CRITICAL_HITS valet[2] FALSE
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
	SET_CHAR_COORDINATES scplayer -1753.8409 960.2274 23.8828
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
	IF IS_CHAR_DEAD sc3_missionchar
		CREATE_CHAR PEDTYPE_CIVMALE SWMYRI -1611.4540 714.3873 12.4715 sc3_missionchar
	ENDIF
//	REQUEST_MODEL MERIT
	WHILE NOT HAS_MODEL_LOADED MERIT										  
		WAIT 0
	ENDWHILE
	IF IS_CAR_DEAD sc3_missioncar
		//CREATE_CAR MERIT -1753.9613 951.9568 23.7422 sc3_missioncar
	ENDIF
	IF NOT IS_CAR_DEAD sc3_missioncar
		SET_CAR_COORDINATES sc3_missioncar -1753.9613 951.9568 23.7422
		SET_CAR_HEADING sc3_missioncar 90.0
	ENDIF
	IF NOT IS_CHAR_DEAD sc3_missionchar
//		TASK_LEAVE_ANY_CAR sc3_missionchar
		da_left_car = 1
	ENDIF
ENDIF 

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_J
	IF NOT IS_CAR_DEAD sc3_missioncar
		SET_CAR_COORDINATES sc3_missioncar -2031.8279 179.5876 27.8359
		//SET_CAR_HEADING sc3_missioncar 90.0
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_K
	IF NOT IS_CAR_DEAD sc3_missioncar
		SET_CAR_COORDINATES sc3_missioncar -1779.7593 971.5197 23.7344
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_R
	sc3_flag = 10
	sc3_cut_flag = 90
ENDIF









IF sc3_uniform_given = 1
	IF NOT IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
		
//		GOSUB cleanup_uniform
		PRINT VAL_B1 4000 1
		GOTO mission_scrash3_failed
	ENDIF
ENDIF


GOSUB scrash1_audio

LVAR_INT scr_i
scr_i = 0




IF sc3_flag > 1
	WHILE scr_i <  2
		IF valet_task[scr_I] = VALET_DEAD
			IF DOES_CHAR_EXIST valet[scr_i]
				GET_DEAD_CHAR_PICKUP_COORDS valet[scr_i] x y z
				IF x >  -1760.1785
				AND y > 972.2405  
				AND z > 16.1633 
				AND x < -1679.6055
				AND y < 1064.2927 
				AND z < 24.8629	
				ELSE
					
					GOSUB cleanup_uniform
					PRINT VAL_9 3000 1
					GOTO mission_scrash3_failed		
				ENDIF
			ENDIF

		ENDIF
		scr_i ++
	ENDWHILE
ENDIF


IF sc3_missioncar_alive = 1
	IF sc3_missionchar_alive = 1
		IF NOT IS_CAR_DEAD sc3_missioncar
			GET_CAR_BLOCKING_CAR sc3_missioncar a_car
			steal_this_car = a_car
//				IF IS_CHAR_DEAD a_thief
//					GET_CAR_COORDINATES sc3_missioncar x y z
//					GET_RANDOM_CHAR_IN_SPHERE x y z 20.0 TRUE FALSE FALSE a_thief
//					IF NOT IS_CHAR_DEAD a_thief
//						IF NOT IS_CHAR_IN_ANY_CAR a_thief
//							IF NOT IS_CHAR_DEAD a_thief
//							AND NOT IS_CAR_DEAD a_car
//								MARK_CHAR_AS_NO_LONGER_NEEDED a_thief
//								MARK_CAR_AS_NO_LONGER_NEEDED a_car
//								TASK_STEAL_CAR a_thief a_car
//								a_car = 0
//							ENDIF
//						ENDIF
//					ENDIF
			LVAR_INT sc3_colour1 sc3_colour2
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD sc3_missioncar
		GET_CAR_COLOURS sc3_missioncar sc3_colour1 sc3_colour2
		IF NOT sc3_colour1 = CARCOLOUR_MIDNIGHTBLUE
		OR NOT sc3_colour2 = CARCOLOUR_MIDNIGHTBLUE
			CLEAR_PRINTS
		   
			GOSUB cleanup_uniform
			PRINT VAL_A35 4000 1
			GOTO mission_scrash3_failed			
		ENDIF
	ENDIF
ENDIF
IF sc3_flag = 0
	sc3_flag = 1
	sc3_mini_flag = 0



	// when the player gets within range of the valets, they will remain idle until this variable is set to 2.
	// when set to 2, A car will be created specifically for the mission cut-scene.
//	valet_force_car_scene = 1
	force_valet_on_mission = 1
	SUPPRESS_CAR_MODEL MERIT
ENDIF

IF sc3_flag = 1
	IF sc3_mini_flag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1748.4017 909.7514 23.8906 3.0 3.0 3.0 TRUE
			SET_PLAYER_CONTROL Player1 OFF
			sc3_mini_flag = 1
		ENDIF
	ENDIF

	IF sc3_mini_flag = 1

		DO_FADE 1000 FADE_OUT
		REQUEST_MODEL ZR350
		sc3_mini_flag = 2
	ENDIF

	IF sc3_mini_flag = 2

		IF NOT GET_FADING_STATUS
		AND HAS_MODEL_LOADED ZR350
			DELETE_CHAR valet[0]
			DELETE_CHAR valet[1]
			DELETE_CHAR valet[2]

			valet_unlocked = 1

			
			STREAM_SCRIPT valet.sc
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT valet.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script > 0
				force_valet_cleanup = 1
				WHILE number_of_instances_of_streamed_script > 0
					WAIT 0
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT valet.sc number_of_instances_of_streamed_script
				ENDWHILE
				force_valet_cleanup = 0
			ENDIF

			WHILE NOT HAS_STREAMED_SCRIPT_LOADED valet.sc
				WAIT 0
			ENDWHILE
			START_NEW_STREAMED_SCRIPT valet.sc

			force_valet_on_mission = 2

		   
			freeze_creating_drop_off_cars = 1

			SWITCH_PED_ROADS_OFF -1729.5487 942.8860 40.3585 -1742.7734 937.7814 10.1928
			SWITCH_PED_ROADS_OFF -1768.5243 938.8870 40.7496 -1780.7653 943.4574 10.0929


			LVAR_INT car_for_valet char_for_valet
			CLEAR_AREA -1722.6754 935.2790 23.7500 55.0 FALSE
			CREATE_CAR ZR350 -1736.9607 946.0503 23.7487 car_for_Valet
			SET_CAR_CAN_GO_AGAINST_TRAFFIC car_for_Valet FALSE
			SET_CAR_HEADING car_for_Valet 19.0
			CREATE_RANDOM_CHAR_AS_DRIVER car_for_valet char_for_valet
			MARK_CHAR_AS_NO_LONGER_NEEDED char_for_valet
			MARK_CAR_AS_NO_LONGER_NEEDED car_for_valet
			MARK_MODEL_AS_NO_LONGER_NEEDED ZR350
			IF NOT IS_CAR_DEAD car_for_valet
				OPEN_SEQUENCE_TASK sc3_seq1
					TASK_CAR_DRIVE_TO_COORD -1 car_for_valet -1753.9678 952.9821 23.7500 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH 
					TASK_LEAVE_CAR -1 car_for_valet
					TASK_SAY -1 CONTEXT_GLOBAL_VALET_PARK_CAR
					TASK_WANDER_STANDARD -1
				CLOSE_SEQUENCE_TASK sc3_seq1

			PERFORM_SEQUENCE_TASK char_for_Valet sc3_seq1
			CLEAR_SEQUENCE_TASK sc3_seq1
			ENDIF
			REMOVE_BLIP sc3_valet_blip

			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -1763.4816 959.9526 25.8866 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1762.6008 959.5414 25.6518 JUMP_CUT
			sc3_mini_flag = 3
			sc3_cut_time = TIMERA + 3000


		ENDIF
	ENDIF


	IF sc3_mini_flag = 3
		IF valet_task[0] = WAITING_FOR_CAR
		AND valet_task[1] = WAITING_FOR_CAR 
		AND  valet_task[2] = WAITING_FOR_CAR
			valet_task[0] = CHATTING
			valet_task[1] = CHATTING
			sc3_cut_time = TIMERA + 700
			sc3_mini_flag = 4
		ENDIF
		IF TIMERA > sc3_cut_time
			valet_task[0] = CHATTING
			valet_task[1] = CHATTING
			sc3_cut_time = TIMERA + 700
			sc3_mini_flag = 4
		ENDIF			
	ENDIF

	IF sc3_mini_flag = 4
		IF TIMERA > sc3_cut_time
			DO_FADE 700 FADE_IN
			sc3_mini_flag = 5
		ENDIF
	ENDIF

	IF sc3_mini_flag = 5
		IF NOT GET_FADING_STATUS
			PRINT_NOW VAL_6 6000 1
			sc3_cut_time = TIMERA + 6000
			sc3_mini_flag = 6
		ENDIF
	ENDIF

	IF sc3_mini_flag = 6
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			sc3_mini_flag = 7
		ENDIF			

		IF TIMERA > sc3_cut_time
			sc3_mini_flag = 7
		ENDIF
	ENDIF

	IF sc3_mini_flag = 7
		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			SET_CHAR_COORDINATES scplayer -1747.1979 909.9226 23.8906 
			SET_CHAR_HEADING scplayer 5.6727
		ELSE
			GET_CAR_CHAR_IS_USING scplayer a_car
			IF NOT IS_CAR_DEAD a_car
				SET_CAR_HEADING a_car 0.0
			ENDIF
		ENDIF


		SET_CAMERA_BEHIND_PLAYER														  
		RESTORE_CAMERA_JUMPCUT															   
		SWITCH_WIDESCREEN OFF															   
		SET_PLAYER_CONTROL Player1 ON													  
		PRINT_NOW VAL_A25 6000 1														   
		sc3_flag = 2																	  
		dont_clear_valets = 1
//			valet_uniform_dropped = 0
		IF NOT IS_CHAR_DEAD valet[2]
			ADD_BLIP_FOR_CHAR valet[2] sc3_blip[2]
			IF NOT IS_CAR_DEAD car_for_Valet
				IF NOT IS_CHAR_IN_CAR valet[2] car_for_valet
					IF NOT IS_CHAR_DEAD char_for_valet
						DELETE_CHAR char_for_valet
						WARP_CHAR_INTO_CAR valet[2] car_for_valet
						GET_PARKING_NODE_IN_AREA -1760.1785 972.2405 16.1633 -1679.6055 1064.2927 24.8629 x y z
						parking_x = x
						parking_y = y
						parking_z = z
						TASK_CAR_DRIVE_TO_COORD valet[2] car_for_Valet x y z 15.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS

						valet_task[2] = DRIVE_TO_CAR_PARK
//						valet_last_task[2] = PICKING_UP_CAR
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF sc3_flag = 2
	
	IF last_player_wanted_level = 0
	AND player_has_wanted_level = 1
		PRINT VAL_B3 5000 1 // the cops are on to you. Clear the heat before killing the valet
	ENDIF
	
	IF player_has_wanted_level = 1
		GET_CHAR_COORDINATES scplayer x y z		
		IF x >  -1760.1785
		AND y > 972.2405  
		AND z > 16.1633 
		AND x < -1679.6055
		AND y < 1064.2927 
		AND z < 24.8629
			CLEAR_PRINTS
			PRINT VAL_B4 5000 1 // The cops saw you entering the car park.	
			GOTO mission_scrash3_failed
		ENDIF
	ENDIF	

	last_player_wanted_level = player_has_wanted_level

	IF DOES_CHAR_EXIST valet[2]		
		LVAR_INT player_has_wanted_level last_player_wanted_level
		IF NOT IS_CHAR_DEAD valet[2]
			GET_CHAR_COORDINATES valet[2] x y z
			IF valet_task[2] = WAITING_FOR_CAR				
				IF drop_off_car = 0
					force_a_one_off_car = 1
				ENDIF
			ENDIF
			IF tell_player_to_kill_valet = 0
				IF player_has_wanted_level = 0
					IF x >  -1760.1785
					AND y > 972.2405  
					AND z > 16.1633 
					AND x < -1679.6055
					AND y < 1064.2927 
					AND z < 24.8629
						PRINT VAL_A22 5000 1 // take out the valet now while no one is watching.
						tell_player_to_kill_valet = 1
						SET_WANTED_MULTIPLIER -1.0
					ENDIF
				ENDIF
			ELSE
				IF x <  -1760.1785
				OR y < 972.2405  
				OR z < 16.1633 
				OR x > -1679.6055
				OR y > 1064.2927 
				OR z > 24.8629
					PRINT VAL_A23 4000 1 // the valet's leaving the car park.
					PRINT VAL_A24 5000 1 // Kill him when he gets back down with another car.
					tell_player_to_kill_valet = 0					
					SET_WANTED_MULTIPLIER 1.0
				ENDIF				
			ENDIF
		ENDIF

LVAR_FLOAT pu_x pu_y pu_z 

		IF IS_CHAR_DEAD valet[2]
			REMOVE_BLIP sc3_blip[2]
			GET_DEAD_CHAR_PICKUP_COORDS valet[2] pu_x pu_y pu_z
			SET_WANTED_MULTIPLIER 1.0
				
			IF pu_x >  -1760.1785
			AND pu_y > 972.2405  
			AND pu_z > 16.1633 
			AND pu_x < -1679.6055
			AND pu_y < 1064.2927 
			AND pu_z < 24.8629
//				CREATE_CLOTHES_PICKUP pu_x pu_y pu_z Valet val_uniform_pickup
				CREATE_PICKUP clothesp PICKUP_ONCE pu_x pu_y pu_z val_uniform_pickup
				ADD_BLIP_FOR_PICKUP val_uniform_pickup val_uniform_blip
				PRINT_NOW VAL_8 5000 1	// Pick up the ~g~valet's uniform.
				sc3_flag = 3			
			ELSE
				REMOVE_BLIP sc3_blip[0] 
				REMOVE_BLIP sc3_blip[1]
				REMOVE_BLIP sc3_blip[2]
				CLEAR_PRINTS
				 
				GOSUB cleanup_uniform
				PRINT_NOW VAL_9 5000 1
				GOTO mission_scrash3_failed		
			ENDIF
		ENDIF
	ENDIF
ENDIF

	
IF sc3_flag = 3
	IF HAS_PICKUP_BEEN_COLLECTED val_uniform_pickup
//	IF valet_uniform_dropped = 3
		head_missing = 1
		SET_MUSIC_DOES_FADE FALSE
		DO_FADE 1000 FADE_OUT
		REMOVE_PICKUP val_uniform_pickup
		REMOVE_BLIP val_uniform_blip
		REQUEST_MODEL WMYVA2
		sc3_flag = 41
		sc3_cut_time = TIMERA + 2000
		LVAR_INT play_dressing_sound
		play_dressing_sound = 0
	ENDIF
ENDIF

IF sc3_flag = 41
	IF NOT GET_FADING_STATUS 
		IF play_dressing_sound = 0
			effect_to_play[0] = SOUND_DRESSING
			 effect_time_to_play[0] = TIMERA + 500
			play_dressing_sound = 1
		ENDIF

	

		IF HAS_MODEL_LOADED WMYVA2
			IF DOES_CHAR_EXIST valet[2]
				GET_DEAD_CHAR_COORDINATES valet[2] pu_x pu_y pu_z		
				GET_DEAD_CHAR_PICKUP_COORDS valet[2] pu_x pu_y pu_z
				IF IS_CHAR_HEAD_MISSING valet[2]
					LVAR_INT head_missing
					head_missing = 1
				ENDIF
				DELETE_CHAR valet[2]
			ENDIF
				
			LVAR_INT valet_dead_name
			CREATE_CHAR PEDTYPE_CIVMALE WMYVA2 pu_x pu_y pu_z valet_dead_name
//			SET_CHAR_COLLISION valet_dead_name FALSE
			SET_CHAR_COORDINATES valet_dead_name pu_x pu_y pu_z
//			FREEZE_CHAR_POSITION valet_dead_name TRUE
			MARK_CHAR_AS_NO_LONGER_NEEDED valet_dead_name
			SHUT_CHAR_UP valet_dead_name TRUE
			IF head_missing = 1
				EXPLODE_CHAR_HEAD valet_dead_name
			ENDIF
			TASK_DIE valet_dead_name
			
			SET_PLAYER_CONTROL player1 OFF
			GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 valet valet CLOTHES_TEX_EXTRA1          
			sc3_uniform_given = 1
        	BUILD_PLAYER_MODEL player1
			SET_PLAYER_CONTROL player1 ON
			sc3_cut_time = TIMERA + 700
		
			sc3_flag = 40
		ENDIF
	ENDIF
ENDIF

IF sc3_uniform_given = 1
	IF player_has_wanted_level = 1
		CLEAR_PRINTS 
		GOSUB cleanup_uniform
		PRINT_NOW VAL_B5 5000 1
		GOTO mission_scrash3_failed			
	ENDIF
ENDIF

IF sc3_flag = 40
	IF TIMERA > sc3_cut_time
		IF effect_to_play[0] = 0
//		IF NOT IS_CHAR_DEAD valet[2]
//			SET_CHAR_SUFFERS_CRITICAL_HITS valet[2] FALSE
//		ENDIF

		
			DO_FADE 600 FADE_IN
			sc3_flag = 42
		ENDIF
	ENDIF
ENDIF

IF sc3_flag = 42
	IF NOT GET_FADING_STATUS
		SET_MUSIC_DOES_FADE TRUE
//		valet_scene_created = 0		
		CLEAR_PRINTS
		PRINT_NOW VAL_A32 5000 1	
		REMOVE_BLIP  sc3_valet_blip
		ADD_BLIP_FOR_COORD -1759.0265 960.4122 23.8905 sc3_valet_blip
		sc3_flag = 4

	ENDIF
ENDIF

IF sc3_flag = 4
	LVAR_INT its_the_new_guy
	IF its_the_new_guy = 0
		IF LOCATE_CHAR_ON_FOOT_3D scplayer -1759.0265 960.4122 23.8905 10.0 10.0 10.0 FALSE
			its_the_new_guy = 1	
			play_audio = 13
//			PRINT VAL_A26 4000 1
		ENDIF
	ENDIF
	IF LOCATE_CHAR_ON_FOOT_3D scplayer -1757.0537 960.1191 23.8906 2.0 2.0 2.0 TRUE		
		sc3_flag = 5
		sc3_cut_flag = 0
	ENDIF
ENDIF


// cutscene showing prosecutor and his car
IF sc3_flag = 5
	IF sc3_cut_flag = 0
		
		DO_FADE 1000 FADE_OUT
 		REQUEST_MODEL MERIT
		REQUEST_MODEL BUFFALO
 		REQUEST_CAR_RECORDING 155

		sc3_cut_flag = 1
	ENDIF

	IF sc3_cut_flag = 1
		IF NOT GET_FADING_STATUS
			SET_PLAYER_CONTROL Player1 OFF
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -1584.3715 727.5854 28.9622 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1585.1138 727.2239 28.3981 JUMP_CUT
			REQUEST_COLLISION -1585.1138 727.2239 
			LOAD_SCENE_IN_DIRECTION -1584.3715 727.5854 28.9622 270.0
			sc3_cut_flag = 2
			sc3_cut_time = TIMERA + 1000
		ENDIF
	ENDIF

	IF sc3_cut_flag = 2
		IF HAS_MODEL_LOADED MERIT
		AND HAS_CAR_RECORDING_BEEN_LOADED 155
			REMOVE_BLIP sc3_valet_blip
			sc3_cut_flag = 3
		ENDIF
	ENDIF
   
	IF sc3_cut_flag = 3
		IF TIMERA > sc3_cut_time
		AND HAS_MODEL_LOADED BUFFALO
		AND HAS_MODEL_LOADED MERIT
			
//			SWITCH_ROADS_OFF 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
//			CLEAR_AREA_OF_CARS 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752

			SET_FIXED_CAMERA_POSITION -1621.4814 738.7783 14.2930 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1620.6378 738.2501 14.3895 JUMP_CUT

			CREATE_CAR MERIT -1611.3792 725.2400 11.8774 sc3_missioncar
			CHANGE_CAR_COLOUR sc3_missioncar CARCOLOUR_MIDNIGHTBLUE CARCOLOUR_MIDNIGHTBLUE
			START_PLAYBACK_RECORDED_CAR sc3_missioncar 155

//			CHANGE_GARAGE_TYPE hbgdSFS GARAGE_MISSION
			
			SET_CAR_CAN_GO_AGAINST_TRAFFIC sc3_missioncar FALSE

			LVAR_INT sc3_randomchar

			CREATE_CHAR PEDTYPE_CIVMALE SWMYRI -1611.4540 714.3873 12.4715 sc3_missionchar
			SET_CHAR_HEALTH sc3_missionchar 200
			SET_CHAR_MAX_HEALTH sc3_missionchar 200

			SET_CHAR_HEADING sc3_missionchar 0.0

			OPEN_SEQUENCE_TASK sc3_seq1
				IF NOT IS_CAR_DEAD sc3_missioncar
					TASK_GO_STRAIGHT_TO_COORD -1 -1611.5964 723.0802 12.2361 PEDMOVE_WALK -2
					TASK_ENTER_CAR_AS_DRIVER -1 sc3_missioncar -2
				ENDIF
			CLOSE_SEQUENCE_TASK sc3_seq1
			PERFORM_SEQUENCE_TASK sc3_missionchar sc3_seq1
			CLEAR_SEQUENCE_TASK sc3_seq1


		

			CLEAR_AREA -1779.8044 915.7557 23.7487 25.0 FALSE
			CREATE_CAR BUFFALO -1779.8044 915.7557 23.7487 car_for_Valet
			SET_CAR_CAN_GO_AGAINST_TRAFFIC car_for_Valet FALSE
			SET_CAR_HEADING car_for_Valet 270.0
			CREATE_RANDOM_CHAR_AS_DRIVER car_for_valet char_for_valet
			MARK_CHAR_AS_NO_LONGER_NEEDED char_for_valet
			MARK_CAR_AS_NO_LONGER_NEEDED car_for_valet
			MARK_MODEL_AS_NO_LONGER_NEEDED BUFFALO
			IF NOT IS_CAR_DEAD car_for_Valet
				OPEN_SEQUENCE_TASK sc3_seq1
					TASK_CAR_DRIVE_TO_COORD -1 car_for_valet -1753.9678 952.9821 23.7500 15.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH 
					TASK_LEAVE_CAR -1 car_for_valet
					TASK_SAY -1 CONTEXT_GLOBAL_VALET_PARK_CAR
					TASK_WANDER_STANDARD -1
				CLOSE_SEQUENCE_TASK sc3_seq1

			PERFORM_SEQUENCE_TASK char_for_Valet sc3_seq1
			CLEAR_SEQUENCE_TASK sc3_seq1
			ENDIF



			DO_FADE 1000 FADE_IN
			sc3_cut_flag = 4
		ELSE
			REQUEST_MODEL BUFFALO
			REQUEST_MODEL MERIT
		ENDIF
	ENDIF

	IF sc3_cut_flag = 4
		IF NOT GET_FADING_STATUS
			SKIP_CUTSCENE_START
			PRINT_NOW VAL_12 4000 1
			PRINT VAL_13 4000 1
			LVAR_INT sc3_change_cut_time
			sc3_cut_time = TIMERA + 3500
			sc3_cut_flag = 5
		ENDIF
	ENDIF
									   
	IF sc3_cut_flag = 5
		IF TIMERA > sc3_cut_time
//			SET_FIXED_CAMERA_POSITION -1621.4814 738.7783 14.2930 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -1620.6378 738.2501 14.3895 JUMP_CUT

//			SET_FIXED_CAMERA_POSITION -1607.1012 722.6023 12.4166 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -1607.9513 723.1281 12.3872 JUMP_CUT
			sc3_cut_flag = 6
		ENDIF
	ENDIF

	IF sc3_cut_flag = 6
			IF NOT IS_CHAR_DEAD sc3_missionchar
				IF IS_CHAR_IN_ANY_CAR sc3_missionchar

					STOP_PLAYBACK_RECORDED_CAR sc3_missioncar
					IF NOT IS_CHAR_DEAD sc3_missionchar
					AND NOT IS_CAR_DEAD sc3_missioncar
						OPEN_SEQUENCE_TASK sc3_seq1
							TASK_PAUSE -1 800
							TASK_CAR_DRIVE_TO_COORD -1 sc3_missioncar -1571.4248 725.7955 6.0391 7.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
						CLOSE_SEQUENCE_TASK sc3_seq1
						PERFORM_SEQUENCE_TASK sc3_missionchar sc3_seq1
						CLEAR_SEQUENCE_TASK sc3_seq1
					ENDIF
					sc3_cut_flag = 7
					sc3_cut_time = TIMERA + 2000
				ENDIF
		ENDIF
	ENDIF

	IF sc3_cut_flag = 7
		IF TIMERA > sc3_cut_time
			SET_FIXED_CAMERA_POSITION -1587.9736 725.1835 7.7383 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1588.8909 725.1658 8.1361 JUMP_CUT
			sc3_cut_flag = 8
			sc3_cut_time = TIMERA + 3000
		ENDIF
	ENDIF

	IF sc3_cut_flag = 8
		IF TIMERA > sc3_cut_time
			SKIP_CUTSCENE_END
			DO_FADE 900 FADE_OUT
			sc3_cut_flag = 9
		ENDIF
	ENDIF

	IF sc3_cut_flag = 9
		IF NOT GET_FADING_STATUS
			REMOVE_CAR_RECORDING 155

			SWITCH_ROADS_ON 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752
			IF NOT IS_CAR_DEAD sc3_missioncar
			AND NOT IS_CHAR_DEAD sc3_missionchar
				IF IS_PLAYBACK_GOING_ON_FOR_CAR sc3_missioncar
					STOP_PLAYBACK_RECORDED_CAR sc3_missioncar
					DELETE_CAR sc3_missioncar
					DELETE_CHAR sc3_missionchar
				ENDIF
			ENDIF

			SET_CHAR_COORDINATES scplayer -1759.6311 960.4594 23.8905 
			SET_CHAR_HEADING scplayer 194.1832


			


   			REQUEST_COLLISION -1748.4017 909.7514 
			LOAD_SCENE -1748.4017 909.7514 23.8906
			sc3_cut_time = TIMERA + 1000
			sc3_cut_flag = 10
//			valet_scene_created = 0
		ENDIF
	ENDIF

	IF sc3_cut_flag = 10
		IF TIMERA > sc3_cut_time
			DO_FADE 900 FADE_IN
			SET_PLAYER_CONTROL Player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			CLEAR_PRINTS
			PRINT VAL_A27 7000 1
			valet_task[0] = WAITING_FOR_CAR
			valet_task[1] = WAITING_FOR_CAR
			GENERATE_RANDOM_INT_IN_RANGE 1 3 force_a_one_off_Car
			sc3_here_comes_the_prosecutor = 1
//			force_a_one_off_car = 1
			sc3_flag = 6
		ENDIF
	ENDIF
		
		
ENDIF


	IF sc3_missioncar_alive = 1
		IF IS_CAR_DEAD sc3_missioncar
			CLEAR_PRINTS
			
			GOSUB cleanup_uniform
			PRINT VAL_40 4000 1
			GOTO mission_scrash3_failed
		ENDIF
	ENDIF
	
	LVAR_INT sc3_task_Status da_left_car create_the_prosecutor

//	VIEW_INTEGER_VARIABLE drop_off_Car drop_off_car
//	VIEW_INTEGER_VARIABLE force_a_one_off_car force_a_one_off_car
//	VIEW_INTEGER_VARIABLE create_drop_off_car_now create_drop_off_car_now
//	VIEW_INTEGER_VARIABLE sc3_here_comes_the_prosecutor sc3_here_comes_the_prosecutor
//	VIEW_INTEGER_VARIABLE create_the_prosecutor create_the_prosecutor

	IF sc3_here_comes_the_prosecutor = 1

		IF NOT create_the_prosecutor = 2
			IF NOT valet_task[0] = WAITING_FOR_CAR
			AND NOT valet_task[1] = WAITING_FOR_CAR
				force_a_one_off_car = 0
			ENDIF
				
			IF sc3_here_comes_the_prosecutor = 1
				IF force_a_one_off_car = 0
					IF create_drop_off_car_now = 0
						IF drop_off_Car = 0
							IF create_the_prosecutor = 0
								IF NOT IS_POINT_ON_SCREEN -1709.8862 899.3372 23.7422 5.0
									IF NOT IS_CHAR_IN_AREA_3D scplayer -1709.8862 899.3372 23.7422 20.0 20.0 20.0 FALSE
										CLEAR_AREA -1709.8862 899.3372 23.7422 5.0 FALSE
										CREATE_CAR MERIT -1611.3792 725.2400 11.8774 sc3_missioncar
										SET_CAR_HEADING sc3_missioncar 0.0
										create_the_prosecutor = 1
									ENDIF
								ENDIF
							ENDIF

							IF create_the_prosecutor = 0
								IF NOT IS_POINT_ON_SCREEN -1674.7253 933.5232 23.7365 5.0
									IF NOT IS_CHAR_IN_AREA_3D scplayer -1674.7253 933.5232 23.7365 20.0 20.0 20.0 FALSE
										CLEAR_AREA -1674.7253 933.5232 23.7365 5.0 FALSE
										CREATE_CAR MERIT -1674.7253 933.5232 23.7365 sc3_missioncar
										SET_CAR_HEADING sc3_missioncar 90.0
										create_the_prosecutor = 1
									ENDIF
								ENDIF
							ENDIF

							IF create_the_prosecutor = 0
								IF NOT IS_POINT_ON_SCREEN -1791.1580 877.2700 23.7500 5.0
									IF NOT IS_CHAR_IN_AREA_3D scplayer -1791.1580 877.2700 23.7500 20.0 20.0 20.0 FALSE
										CLEAR_AREA -1791.1580 877.2700 23.7500 5.0 FALSE
										CREATE_CAR MERIT -1791.1580 877.2700 23.7500 sc3_missioncar
										SET_CAR_HEADING sc3_missioncar 0.0
										create_the_prosecutor = 1
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF create_the_prosecutor = 1
					IF NOT IS_CAR_DEAD sc3_missioncar
				
						CHANGE_CAR_COLOUR sc3_missioncar CARCOLOUR_MIDNIGHTBLUE CARCOLOUR_MIDNIGHTBLUE
//						CHANGE_GARAGE_TYPE hbgdSFS GARAGE_MISSION
						
						SET_CAR_CAN_GO_AGAINST_TRAFFIC sc3_missioncar FALSE


						CREATE_CHAR PEDTYPE_CIVMALE SWMYRI -1611.4540 714.3873 12.4715 sc3_missionchar
						SET_CHAR_HEALTH sc3_missionchar 200
						SET_CHAR_MAX_HEALTH sc3_missionchar 200

						IF NOT IS_CHAR_IN_ANY_CAR sc3_missionchar
							CLEAR_CHAR_TASKS_IMMEDIATELY sc3_missionchar
							WARP_CHAR_INTO_CAR sc3_missionchar sc3_missioncar
						ENDIF			

						IF NOT IS_CAR_DEAD sc3_missioncar
							OPEN_SEQUENCE_TASK sc3_seq1
								TASK_PAUSE -1 10000
								TASK_CAR_DRIVE_TO_COORD -1 sc3_missioncar -1758.8767 952.7440 23.7487 15.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS
								TASK_LEAVE_CAR -1 sc3_missioncar
								TASK_SAY -1 CONTEXT_GLOBAL_VALET_PARK_CAR
								TASK_WANDER_STANDARD -1
							CLOSE_SEQUENCE_TASK sc3_seq1
							PERFORM_SEQUENCE_TASK sc3_missionchar sc3_seq1
							CLEAR_SEQUENCE_TASK sc3_seq1
						ENDIF

						sc3_missionchar_alive = 1
						sc3_missioncar_alive = 1
						create_the_prosecutor = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF sc3_missionchar_alive = 1
		IF NOT IS_CHAR_DEAD sc3_missionchar
			IF IS_CHAR_IN_CAR sc3_missionchar sc3_missioncar				
				GET_SCRIPT_TASK_STATUS sc3_missionchar PERFORM_SEQUENCE_TASK sc3_task_status
				IF sc3_task_Status = PERFORMING_TASK
					GET_SEQUENCE_PROGRESS sc3_missionchar sc3_task_status
					IF sc3_task_Status = 2
						da_left_car = 1
					ENDIF
				ENDIF						
			ELSE
				IF NOT da_left_car = 1
//					CLEAR_PRINTS
					
					GOSUB cleanup_uniform
					PRINT VAL_A1 4000 1
					GOTO mission_scrash3_failed					
				ENDIF
			ENDIF
		ENDIF
				
		IF IS_CHAR_DEAD sc3_missionchar
			CLEAR_PRINTS
			
			GOSUB cleanup_uniform
			PRINT VAL_34 4000 1
			GOTO mission_scrash3_failed
		ENDIF
	ENDIF


IF sc3_flag > 6 	
	IF NOT IS_CHAR_DEAD sc3_missionchar
		IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sc3_missionchar 80.0 80.0 FALSE
			DELETE_CHAR sc3_missionchar
			sc3_missionchar_alive = 0
		ENDIF
	ENDIF
ENDIF

IF sc3_flag > 5
	IF valet_picked_up_prosecutors_car = 0
	AND valet_taking_car_to_da = 0
		IF NOT IS_CAR_DEAD sc3_missioncar
			GET_DRIVER_OF_CAR sc3_missioncar sc3_var3
			IF NOT sc3_var3 = sc3_missionchar
			AND NOT sc3_var3 = scplayer
			AND NOT sc3_var3 = -1			
				PRINT VAL_A28 4000 1
				REMOVE_BLIP sc3_missioncar_blip
				ADD_BLIP_FOR_CAR sc3_missioncar sc3_missioncar_blip
				REMOVE_BLIP sc3_valet_blip
				sc3_here_comes_the_prosecutor = 0
				valet_picked_up_prosecutors_car = 1
				valet_parking_x = parking_x
				valet_parking_y = parking_y
				valet_parking_z	= parking_z
				
//				VIEW_INTEGER_VARIABLE valet_picked_up_prosecutors_car valet_picked_up_prosecutors_car
LVAR_INT valet_picked_up_prosecutors_car
LVAR_FLOAT valet_parking_x valet_parking_y valet_parking_z
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF valet_picked_up_prosecutors_car = 1
	IF NOT IS_CAR_DEAD sc3_missioncar
		IF LOCATE_CAR_3D sc3_missioncar valet_parking_x valet_parking_y valet_parking_z 4.0 4.0 4.0 FALSE
			
			
			
			GOSUB cleanup_uniform
			PRINT VAL_A29 4000 1
			GOTO mission_scrash3_failed				
		ENDIF				
		IF IS_CHAR_IN_CAR scplayer sc3_missioncar	
			valet_picked_up_prosecutors_car = 2
			REMOVE_BLIP sc3_missioncar_blip
		ENDIF
	ENDIF
ENDIF



		
// player is waiting for Prosecutor's car
IF sc3_flag = 6

	// If player strays too far from the valet area...
	IF sc3_here_comes_the_prosecutor = 1
		IF sc3_player_straying = 0
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -1759.0265 960.4122 23.8905 15.0 15.0 15.0 FALSE
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD valet[1]
				OR NOT IS_CHAR_DEAD valet[0]
					PRINT_NOW VAL_49 5000 1
				ENDIF
				REMOVE_BLIP sc3_valet_blip
				ADD_BLIP_FOR_COORD -1759.0265 960.4122 23.8905 sc3_valet_blip
				sc3_player_straying = 1
			ENDIF
		ENDIF

		IF sc3_player_straying = 1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer -1759.0265 960.4122 23.8905 2.0 2.0 2.0 TRUE			
				REMOVE_BLIP sc3_valet_blip
				sc3_player_straying = 0
			ENDIF
		ENDIF
	ENDIF

	IF sc3_here_comes_the_prosecutor = 1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1759.0265 960.4122 23.8905 15.0 15.0 15.0 FALSE
			IF NOT IS_CAR_DEAD sc3_missioncar
				IF LOCATE_CAR_3D sc3_missioncar -1757.9069 952.8868 25.9104 20.0 20.0 15.0 FALSE
					IF NOT IS_CHAR_DEAD sc3_missionchar
						IF IS_CHAR_IN_CAR sc3_missionchar sc3_missioncar
							IF NOT IS_CHAR_DEAD valet[1]
								IF LOCATE_CHAR_ANY_MEANS_3D valet[1] -1759.0265 960.4122 23.8905 10.0 10.0 10.0 FALSE				
									sc3_here_comes_the_prosecutor = 2
								ENDIF
							ENDIF
							IF NOT IS_CHAR_DEAD valet[0]
								IF LOCATE_CHAR_ANY_MEANS_3D valet[0] -1759.0265 960.4122 23.8905 10.0 10.0 10.0 FALSE				
									sc3_here_comes_the_prosecutor = 2
								ENDIF
							ENDIF

							REMOVE_BLIP sc3_valet_blip
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

		
	IF sc3_here_comes_the_prosecutor = 2							
		sc3_here_comes_the_prosecutor = 3
		CLEAR_PRINTS
		play_audio = 14
	ENDIF



	IF da_left_car = 1  
		GET_CAR_CHAR_IS_USING scplayer sc3_var1
		IF NOT IS_CHAR_DEAD sc3_missionchar
			GET_CAR_CHAR_IS_USING sc3_missionchar sc3_var2
			IF NOT sc3_var1 = sc3_var2
				IF NOT IS_CAR_DEAD sc3_missioncar
					IF IS_CHAR_IN_CAR scplayer sc3_missioncar
						CLEAR_PRINTS
						PRINT VAL_15 7000 1 
						sc3_flag = 7
						REMOVE_BLIP sc3_valet_blip
						REMOVE_BLIP sc3_drug_plant_blip
						ADD_BLIP_FOR_COORD -2045.2523 178.7994 27.8359 sc3_drug_plant_blip
						SET_CAR_HEALTH sc3_missioncar 1000
						sc3_countdown = 150000 //was 3 minutes
						DISPLAY_ONSCREEN_TIMER_WITH_STRING sc3_countdown TIMER_DOWN VAL_21
						timer_running = 1	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

    
IF sc3_flag > 6
	IF sc3_flag < 10
	OR sc3_flag = 13
	OR sc3_flag = 20
	LVAR_INT valet_taking_car_to_da
		IF NOT valet_taking_car_to_da = 1
			IF NOT IS_CAR_DEAD sc3_missioncar
				IF NOT IS_CHAR_IN_CAR scplayer sc3_missioncar
					IF sc3_player_in_car = 1
						sc3_player_in_car = 0
						REMOVE_BLIP sc3_missioncar_blip
						ADD_BLIP_FOR_CAR sc3_missioncar sc3_missioncar_blip
						SET_BLIP_AS_FRIENDLY sc3_missioncar_blip TRUE
						CHANGE_BLIP_DISPLAY sc3_drug_plant_blip NEITHER
						CLEAR_PRINTS
						PRINT VAL_29 5000 1
					ENDIF
				ENDIF
				IF IS_CHAR_IN_CAR scplayer sc3_missioncar
					IF sc3_player_in_car = 0
						sc3_player_in_car = 1
						REMOVE_BLIP sc3_missioncar_blip
						CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// player arrives at hub
IF sc3_flag = 7
//	IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer -2179.1973 715.6656 52.9098 3.0 3.0 3.0 TRUE


	IF LOCATE_CHAR_IN_CAR_3D scplayer -2033.6256 178.8560 27.8359 3.0 3.0 3.0 TRUE
		IF NOT IS_CAR_DEAD sc3_missioncar
			IF IS_CHAR_IN_CAR scplayer sc3_missioncar
 				
				REMOVE_BLIP sc3_drug_plant_blip
				ADD_BLIP_FOR_COORD -2045.9474 178.5964 28.1397 sc3_drug_plant_blip 
				CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
				CLEAR_PRINTS
				OPEN_GARAGE hbgdSFS
				sc3_flag = 80
			ENDIF
		ENDIF

	ENDIF
ENDIF

IF sc3_flag = 80
	IF NOT IS_CAR_DEAD sc3_missioncar
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION -2038.0552 186.7164 31.0085 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2037.7037 185.8301 30.7068 JUMP_CUT
		SET_CAR_COORDINATES sc3_missioncar -2033.6256 178.8560 27.8359
		SET_CAR_HEADING sc3_missioncar 90.0
		OPEN_SEQUENCE_TASK sc3_sequence
			TASK_PAUSE -1 1000
			TASK_CAR_DRIVE_TO_COORD -1 sc3_missioncar -2047.5168 178.5924	27.8359 7.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			TASK_LEAVE_ANY_CAR -1
		CLOSE_SEQUENCE_TASK sc3_sequence
		PERFORM_SEQUENCE_TASK scplayer sc3_sequence
		CLEAR_SEQUENCE_TASK sc3_sequence
		sc3_flag = 81
	ENDIF
ENDIF

IF sc3_flag = 81
	GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK sc3_task_status
	IF sc3_Task_Status = FINISHED_TASK
		DO_FADE 700 FADE_OUT
		sc3_flag = 82
	ENDIF
ENDIF

IF sc3_flag = 82
	IF NOT GET_FADING_STATUS
		CLOSE_GARAGE hbgdSFS
		sc3_cut_time = TIMERA + 1000 
		sc3_flag = 83
	ENDIF
ENDIF

IF sc3_flag = 83
	IF TIMERA > sc3_cut_time
		IF IS_GARAGE_CLOSED hbgdSFS
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			IF NOT IS_CAR_DEAD sc3_missioncar
				GET_CAR_COORDINATES sc3_missioncar sc3_x sc3_y sc3_z
				GET_CAR_HEADING sc3_missioncar sc3_h
				GET_CAR_COLOURS sc3_missioncar sc3_col1 sc3_col2
				DELETE_CAR sc3_missioncar
			ENDIF

			REQUEST_MODEL MERIT
			WHILE NOT HAS_MODEL_LOADED MERIT
				WAIT 0
			ENDWHILE

			CREATE_CAR MERIT -2031.5023 178.6435 27.8516 sc3_missioncar
			CHANGE_CAR_COLOUR sc3_missioncar sc3_col1 sc3_col2
			SET_CAR_HEADING sc3_missioncar 270.0

			IF NOT IS_CAR_DEAD sc3_missioncar
				WARP_CHAR_INTO_CAR scplayer sc3_missioncar
			ENDIF

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			DO_FADE 500 FADE_IN

			REMOVE_BLIP sc3_drug_plant_blip 
			ADD_BLIP_FOR_COORD -1780.0695 970.9553 23.7500 sc3_drug_plant_blip 
			CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
			sc3_flag = 8
			CLEAR_PRINTS
			PRINT VAL_20 5000 1  // ~s~The drugs are planted. Take the car to the valet's ~y~car park~s~.
			PRINT VAL_A31 5000 1
			SET_PLAYER_CONTROL player1 ON
		ENDIF
	ENDIF
ENDIF


IF sc3_flag = 20
	IF NOT LOCATE_CHAR_ANY_MEANS_3D	scplayer -2037.2389 179.5294 28.316 10.0 10.0 10.0 FALSE
		sc3_flag = 7
		REMOVE_BLIP sc3_drug_plant_blip
		ADD_BLIP_FOR_COORD -2033.6256 178.8560 27.8359 sc3_drug_plant_blip
		CLEAR_PRINTS
		PRINT VAL_51 5000 1	
	ENDIF
		
	IF LOCATE_CHAR_IN_CAR_3D scplayer -2045.9474 178.5964 28.1397 4.0 4.0 4.0 TRUE
		IF NOT IS_CAR_DEAD sc3_missioncar
			IF IS_CHAR_IN_CAR scplayer sc3_missioncar
				SET_PLAYER_CONTROL player1 OFF
				TASK_CAR_TEMP_ACTION scplayer sc3_missioncar TEMPACT_HANDBRAKESTRAIGHT 2000000

				IF NOT IS_CAR_DEAD sc3_missioncar
					IF IS_CHAR_IN_CAR scplayer sc3_missioncar
						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						IF NOT IS_CAR_DEAD sc3_missioncar
							GET_CAR_COORDINATES sc3_missioncar sc3_x sc3_y sc3_z
							GET_CAR_HEADING sc3_missioncar sc3_h
							GET_CAR_COLOURS sc3_missioncar sc3_col1 sc3_col2

							DELETE_CAR sc3_missioncar
						ENDIF
						REQUEST_MODEL MERIT
						WHILE NOT HAS_MODEL_LOADED MERIT
							WAIT 0
						ENDWHILE
						CREATE_CAR MERIT -2031.5023 178.6435 27.8516 sc3_missioncar
						CHANGE_CAR_COLOUR sc3_missioncar sc3_col1 sc3_col2
						SET_CAR_HEADING sc3_missioncar 270.0

						SET_TARGET_CAR_FOR_MISSION_GARAGE hbgdSFS sc3_missioncar

						IF NOT IS_CAR_DEAD sc3_missioncar
							WARP_CHAR_INTO_CAR scplayer sc3_missioncar
						ENDIF

						SET_CAMERA_BEHIND_PLAYER
						RESTORE_CAMERA_JUMPCUT

						WAIT 1000
						
						DO_FADE 500 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE				

						REMOVE_BLIP sc3_drug_plant_blip 
						ADD_BLIP_FOR_COORD -1780.0695 970.9553 23.7500 sc3_drug_plant_blip 
						CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
						sc3_flag = 8
						CLEAR_PRINTS
						PRINT VAL_20 5000 1  // ~s~The drugs are planted. Take the car to the valet's ~y~car park~s~.
						PRINT VAL_A31 5000 1
						SET_PLAYER_CONTROL player1 ON
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF  
ENDIF

// player arrives at car park entrance

IF sc3_flag = 8
	IF NOT IS_CAR_DEAD sc3_missioncar
		IF LOCATE_CAR_3D sc3_missioncar -1780.0695 970.9553 23.7500 3.0 3.0 3.0 TRUE
			PRINT VAL_32 5000 1	
			REMOVE_BLIP sc3_drug_plant_blip
			ADD_BLIP_FOR_COORD -1688.2136 987.1218 16.5855 sc3_drug_plant_blip
			sc3_flag = 9
		ENDIF
	ENDIF
ENDIF


// Do check to see if timer has run out.

IF timer_running = 1
	IF sc3_countdown = 0
		CLEAR_PRINTS
		GOSUB cleanup_uniform
		PRINT VAL_47 5000 1
		GOTO mission_scrash3_failed
	ENDIF
ENDIF


// player arrives at parking space.		
IF sc3_flag = 9
	IF sc3_player_in_car = 1
		IF NOT IS_CAR_DEAD sc3_missioncar
			IF LOCATE_STOPPED_CAR_3D sc3_missioncar -1688.2136 987.1218 16.5855 3.0 3.0 3.0 TRUE
				SET_PLAYER_CONTROL player1 OFF
				TASK_LEAVE_ANY_CAR scplayer
				
				LOCK_CAR_DOORS sc3_missioncar CARLOCK_LOCKOUT_PLAYER_ONLY
				REMOVE_BLIP sc3_drug_plant_blip
				REMOVE_BLIP sc3_missioncar_blip 
				ADD_BLIP_FOR_COORD -1742.3003 940.8106 23.8972 sc3_drug_plant_blip 
				CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
				CLEAR_ONSCREEN_TIMER sc3_countdown
				timer_running = 0
				
				//Print get outside to call the cops.
				CLEAR_PRINTS 
				PRINT VAL_A30 6000 1
				sc3_flag = 30

				LVAR_INT script_valet sc3_sequence
				CREATE_CHAR PEDTYPE_MISSION1 wmyva -1772.8086 977.8892 22.6614 script_valet
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER script_Valet TRUE
				OPEN_SEQUENCE_TASK sc3_sequence
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1710.6670 985.5689 16.5936 PEDMOVE_RUN -2
					TASK_ENTER_CAR_AS_DRIVER -1 sc3_missioncar -2
					TASK_CAR_TEMP_ACTION -1 sc3_missioncar TEMPACT_REVERSE_RIGHT 1400
					TASK_CAR_DRIVE_TO_COORD -1 sc3_missioncar -1764.0815 982.5382 21.1042 15.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS
					TASK_CAR_DRIVE_TO_COORD -1 sc3_missioncar -1754.8885 952.3113 23.7422 15.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS
					TASK_LEAVE_ANY_CAR -1
					TASK_GO_STRAIGHT_TO_COORD -1 -1754.1168 959.0662 23.8828 PEDMOVE_WALK -2
					TASK_ACHIEVE_HEADING -1 147.0
				CLOSE_SEQUENCE_TASK sc3_sequence

				SET_CAR_CAN_GO_AGAINST_TRAFFIC sc3_missioncar FALSE

				CREATE_CHAR PEDTYPE_MISSION1 SWMYRI -1755.3920 946.9804 23.8906 sc3_missionchar
				SET_CHAR_HEADING sc3_missionchar 0.0	

				PERFORM_SEQUENCE_TASK script_valet sc3_sequence
				CLEAR_SEQUENCE_TASK sc3_sequence
			ENDIF										
		ENDIF
	ENDIF 
ENDIF


IF sc3_flag = 30

	// put player's clothes back on at some point.
	IF NOT IS_CAR_DEAD sc3_missioncar
		IF NOT IS_CHAR_IN_CAR scplayer sc3_missioncar
			IF valet_taking_car_to_da = 0
				SET_PLAYER_CONTROL player1 ON
				valet_taking_car_to_da = 1
				SET_CAR_PROOFS sc3_missioncar TRUE TRUE TRUE TRUE TRUE

			ENDIF
		ENDIF

		
		IF IS_CAR_IN_AREA_3D sc3_missioncar -1764.1991 946.5950 28.3546 -1744.0857 957.8790 22.6221 FALSE
			IF NOT IS_CHAR_DEAD sc3_missionchar
				GET_SCRIPT_TASK_STATUS sc3_missionchar PERFORM_SEQUENCE_TASK sc3_task_status
				IF sc3_task_Status = FINISHED_TASK
					OPEN_SEQUENCE_TASK sc3_sequence
						TASK_ENTER_CAR_AS_DRIVER -1 sc3_missioncar -2
						TASK_CAR_DRIVE_WANDER -1 sc3_missioncar 15.0 DRIVINGMODE_STOPFORCARS
					CLOSE_SEQUENCE_TASK sc3_sequence
					PERFORM_SEQUENCE_TASK sc3_missionchar sc3_sequence
					CLEAR_SEQUENCE_TASK sc3_sequence
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD sc3_missionchar
			IF IS_CHAR_IN_CAR sc3_missionchar sc3_missioncar
				CLEAR_PRINTS
				GOSUB cleanup_uniform
				PRINT VAL_A34 5000 1
				GOTO mission_scrash3_failed				
			ENDIF
		ENDIF

		IF LOCATE_CHAR_ON_FOOT_3D scplayer -1742.3003 940.8106 23.8972 2.0 2.0 3.0 TRUE
			timer_running = 0
			SET_PLAYER_CONTROL player1 OFF
			REMOVE_BLIP sc3_drug_plant_blip
			sc3_flag = 10
			sc3_cut_flag = 90
		ENDIF

		IF IS_CAR_VISIBLY_DAMAGED sc3_missioncar
			IF NOT IS_CHAR_DEAD script_valet
				IF NOT IS_CHAR_IN_CAR script_valet sc3_missioncar
					
					GOSUB cleanup_uniform
					PRINT VAL_B2 4000 1 //The car's visibly damaged. It's too late to get it fixed.
					GOTO mission_scrash3_failed
				ENDIF
			ENDIF
		ENDIF

	ENDIF
ENDIF



IF sc3_flag > 7
	IF sc3_flag < 10
		IF NOT IS_CAR_DEAD sc3_missioncar
			IF IS_CAR_VISIBLY_DAMAGED sc3_missioncar
				sc3_flag = 7
				PRINT VAL_31 5000 1 
				REMOVE_BLIP sc3_drug_plant_blip
				ADD_BLIP_FOR_COORD -2045.2523 178.7994 27.8359 sc3_drug_plant_blip
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF sc3_flag = 10
    SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -1741.7917 939.7310 23.8906 2.5 lamppost3 FALSE

	IF sc3_cut_flag = 90
		REQUEST_MODEL CELLPHONE
		force_audio = 1
		sc3_cut_flag = 91
	ENDIF

	IF sc3_cut_flag = 91
		IF HAS_MODEL_LOADED CELLPHONE
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -1738.0826 938.6749 25.0378 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1738.9598 939.1542 25.0127 JUMP_CUT
			CLEAR_AREA -1741.7917 939.7310 23.8906 20.0 FALSE
			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer -1741.7917 939.7310 23.8906
			ELSE
				SET_CHAR_COORDINATES scplayer -1741.7917 939.7310 23.8906
			ENDIF

			SET_CHAR_HEADING scplayer 270.0
			TASK_USE_MOBILE_PHONE scplayer TRUE
			cut_time_change = TIMERA + 2500
			sc3_cut_flag = 89
		ENDIF
	ENDIF

	IF sc3_cut_flag = 89
		IF TIMERA > cut_time_change
			play_audio = 1
			play_audio_for = 2
			sc3_cut_flag = 88
		ENDIF
	ENDIF

	IF sc3_cut_flag = 88
		IF play_audio = 0
		AND play_audio_for = 0
			TASK_USE_MOBILE_PHONE scplayer FALSE
			DO_FADE 1000 FADE_OUT
			sc3_missioncar_alive = 0
			sc3_cut_flag = 87
		ENDIF
	ENDIF

	IF sc3_cut_flag = 87
		IF NOT GET_FADING_STATUS 
			IF NOT IS_CHAR_DEAD sc3_missionchar
				DELETE_CHAR sc3_missionchar
			ENDIF

			sc3_cut_flag = 99
			freeze_creating_drop_off_cars = 1
			IF NOT IS_CAR_DEAD drop_off_car
				DELETE_CAR drop_off_car
			ENDIF
			IF NOT IS_CAR_DEAD valet_pickup_car	
				DELETE_CAR valet_pickup_car
			ENDIF
			CLEAR_AREA -1741.7917 939.7310 23.0 80.0 FALSE
		ENDIF
	ENDIF


	IF sc3_cut_flag = 99
		SET_PED_DENSITY_MULTIPLIER 0.0
		SET_CAR_DENSITY_MULTIPLIER 0.0

		pause_valet_script = 1

		REQUEST_COLLISION -1759.4489 954.4333 
		LOAD_SCENE -1759.4489 954.4333 23.7200
//		valet_scene_created = 2
//		sc3_flag = 11
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer -1741.7917 939.7310 23.8906
		ELSE
			SET_CHAR_COORDINATES scplayer -1741.7917 939.7310 23.8906
		ENDIF

		SET_CHAR_HEADING scplayer 40.0
		SET_PLAYER_CONTROL Player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION -1738.0826 938.6749 25.0378 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1738.9598 939.1542 25.0127 JUMP_CUT
		IF NOT IS_CAR_DEAD sc3_missioncar
			DELETE_CAR sc3_missioncar
		ENDIF


		sc3_cut_flag = 98		
	ENDIF

	IF sc3_cut_flag = 98
		IF HAS_MODEL_LOADED MERIT
		AND HAS_MODEL_LOADED COLT45 			
		AND HAS_MODEL_LOADED SWMYRI
		AND HAS_ANIMATION_LOADED POLICE
		AND HAS_ANIMATION_LOADED MISC
			IF HAS_MODEL_LOADED SFPD1
			AND HAS_CAR_RECORDING_BEEN_LOADED 165
			AND HAS_CAR_RECORDING_BEEN_LOADED 166
			AND HAS_MODEL_LOADED COPCARSF
				sc3_cut_flag = 0
				MARK_MODEL_AS_NO_LONGER_NEEDED CHEETAH
				MARK_MODEL_AS_NO_LONGER_NEEDED WMYVA2
			ELSE
				REQUEST_MODEL COPCARSF
				REQUEST_CAR_RECORDING 166
				REQUEST_CAR_RECORDING 165
				REQUEST_MODEL SFPD1
			ENDIF
		ELSE
			REQUEST_ANIMATION MISC
			REQUEST_ANIMATION POLICE
			REQUEST_MODEL SWMYRI	
			REQUEST_MODEL COLT45				
			REQUEST_MODEL MERIT
		ENDIF
	ENDIF

	IF sc3_cut_flag = 0

		DO_FADE 800 FADE_IN

		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

		LVAR_INT sc3_end_copcar1 sc3_end_copcar2

		CLEAR_AREA -1759.4489 954.4333 23.7200 50.0 FALSE
		SET_CAR_DENSITY_MULTIPLIER 0.0
	
		CREATE_CAR MERIT -1759.4489 954.4333 23.7200 sc3_missioncar
		
		CHANGE_CAR_COLOUR sc3_missioncar CARCOLOUR_MIDNIGHTBLUE CARCOLOUR_MIDNIGHTBLUE
		CREATE_CAR COPCARSF -1711.3990 903.0281 24.1296 sc3_end_copcar1
		CREATE_CAR COPCARSF -1711.0159 893.7642 24.0975 sc3_end_copcar2

		SWITCH_CAR_SIREN sc3_end_copcar1 TRUE
		SWITCH_CAR_SIREN sc3_end_copcar2 TRUE


		CREATE_CHAR_INSIDE_CAR sc3_end_copcar1 PEDTYPE_CIVMALE SFPD1 sc3_cop1 
		CREATE_CHAR_INSIDE_CAR sc3_end_copcar2 PEDTYPE_CIVMALE SFPD1 sc3_cop2

		GIVE_WEAPON_TO_CHAR sc3_cop1 WEAPONTYPE_PISTOL 99999
		GIVE_WEAPON_TO_CHAR sc3_cop2 WEAPONTYPE_PISTOL 99999

		CREATE_CHAR PEDTYPE_CIVMALE SWMYRI -1763.2758 946.1676 23.8905 sc3_missionchar

		IF NOT IS_CHAR_DEAD sc3_missionchar
			WARP_CHAR_INTO_CAR sc3_missionchar sc3_missioncar
		ENDIF

		OPEN_SEQUENCE_TASK sc3_seq
			TASK_PAUSE -1 6500
			TASK_LEAVE_ANY_CAR -1
			TASK_HANDS_UP -1 3000
		CLOSE_SEQUENCE_TASK sc3_seq
		PERFORM_SEQUENCE_TASK sc3_missionchar sc3_seq
		CLEAR_SEQUENCE_TASK sc3_seq


		SET_CAR_HEADING sc3_missioncar 90.0

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer plyrlean_loop MISC 1.0 TRUE 0 0 0 -2

		VAR_FLOAT cunt_x cunt_y
//		VAR_INT task_statt
		LVAR_INT sc3_seq
//		VIEW_FLOAT_VARIABLE cunt_x cunt_x
//		VIEW_FLOAT_VARIABLE cunt_y cunt_y
//		VIEW_INTEGER_VARIABLE task_statt task_Statt

		GET_CHAR_COORDINATES scplayer cunt_x cunt_y player_z

	//	CREATE_CAR COPCARSF 
	                             
	                                                                                                 

		SET_FIXED_CAMERA_POSITION -1740.3453 939.6078 25.3651 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1741.2230 940.0797 25.2814 JUMP_CUT

		audio_actor[2] = sc3_missionchar
		audio_actor[3] = sc3_cop1
		audio_actor[4] = sc3_cop2

		cut_time_change = TIMERA + 1000
		sc3_cut_flag = 1
	ENDIF

	IF sc3_cut_flag = 1
		IF TIMERA > cut_time_change
			sc3_cut_flag = 2
		ENDIF
	ENDIF

	IF sc3_cut_flag = 2
		// Play cop cars
		IF NOT IS_CAR_DEAD sc3_end_copcar1 
		AND NOT IS_CAR_DEAD sc3_end_copcar2
			IF HAS_CAR_RECORDING_BEEN_LOADED 165
			AND HAS_CAR_RECORDING_BEEN_LOADED 166 
				START_PLAYBACK_RECORDED_CAR sc3_end_copcar1 165
				START_PLAYBACK_RECORDED_CAR sc3_end_copcar2 166
				SET_PLAYBACK_SPEED sc3_end_copcar1 0.8
				SET_PLAYBACK_SPEED sc3_end_copcar2 0.8
			ELSE
				REQUEST_CAR_RECORDING 165
				REQUEST_CAR_RECORDING 166
			ENDIF
		ENDIF

		cut_time_change = TIMERA + 3000
		sc3_cut_flag = 3
	ENDIF

	IF sc3_cut_flag = 3
		IF TIMERA > cut_time_change
			sc3_cut_flag = 4
		ENDIF
	ENDIF

	IF sc3_cut_flag = 4
		// Cops leave cop cars
		CLEAR_PRINTS
		play_audio = 3
		play_audio_for = 2
		IF NOT IS_CHAR_DEAD sc3_missionchar
			IF NOT IS_CHAR_DEAD	sc3_cop1
				OPEN_SEQUENCE_TASK sc3_seq
					TASK_LEAVE_ANY_CAR -1
					TASK_AIM_GUN_AT_CHAR -1 sc3_missionchar 3000
				CLOSE_SEQUENCE_TASK sc3_seq
				PERFORM_SEQUENCE_TASK sc3_cop1 sc3_seq
				CLEAR_SEQUENCE_TASK sc3_seq
			ENDIF
			IF NOT IS_CHAR_DEAD	sc3_cop2
				OPEN_SEQUENCE_TASK sc3_seq
					TASK_LEAVE_ANY_CAR -1
					TASK_GO_TO_COORD_WHILE_AIMING -1 -1762.3694 951.1278 23.7487 PEDMOVE_WALK 1.0 1.0 sc3_missionchar 0.0 0.0 0.0
				CLOSE_SEQUENCE_TASK sc3_seq
				PERFORM_SEQUENCE_TASK sc3_cop2 sc3_seq
				CLEAR_SEQUENCE_TASK sc3_seq
			ENDIF

		ENDIF

		cut_time_change = TIMERA + 6200
		sc3_cut_flag = 5																   
	ENDIF																				   
																						   
	IF sc3_cut_flag = 5
		IF TIMERA > cut_time_change
			IF play_audio = 0
				sc3_cut_flag = 6
				LVAR_INT hit_bonnet_time
				
			ENDIF
		ENDIF
	ENDIF

	IF sc3_cut_flag = 6
		play_audio = 5
		play_audio_for = 3
		LVAR_INT play_audio_dent_time play_audio_hit_time
		play_audio_hit_time = TIMERA + 17333
		play_audio_dent_time = TIMERA + 17633
		hit_bonnet_time = TIMERA + 4313

		// Prosecutor gets busted
		IF NOT IS_CHAR_DEAD sc3_cop1
		AND NOT IS_CHAR_DEAD sc3_cop2

			SET_CURRENT_CHAR_WEAPON sc3_cop1 WEAPONTYPE_UNARMED
			SET_CURRENT_CHAR_WEAPON sc3_cop2 WEAPONTYPE_UNARMED
			SET_CHAR_HEADING sc3_cop1 0.0
		ENDIF

//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE TIMERA
//		sucky = 2
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE sucky

	
	//  Cut at front of car
		SET_FIXED_CAMERA_POSITION -1768.0479 955.8058 25.1327 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1767.2375 955.2321 25.0132 JUMP_CUT

		
	//
		IF NOT IS_CAR_DEAD sc3_missioncar
			POP_CAR_BOOT sc3_missioncar
		ENDIF
	//	IF NOT IS_CHAR_DEAD sc3_missionchar
	//		DELETE_CHAR sc3_missionchar
	//	ENDIF

		IF NOT IS_CHAR_DEAD sc3_missionchar
			CLEAR_CHAR_TASKS_IMMEDIATELY sc3_missionchar
			SET_CHAR_COLLISION sc3_missionchar FALSE
			SET_CHAR_COORDINATES sc3_missionchar -1761.71 952.9333 23.7900
			SET_CHAR_HEADING sc3_missionchar 0.0
			FREEZE_CHAR_POSITION sc3_missionchar TRUE
			TASK_PLAY_ANIM_NON_INTERRUPTABLE sc3_missionchar Crm_drgbst_01 POLICE 1.0 FALSE 0 0 TRUE -1
		ENDIF
	//	

		LVAR_INT sc3_cop1 sc3_cop2


		SET_CHAR_COORDINATES sc3_cop1 -1761.7236 952.4515 23.7200 
		CLEAR_CHAR_TASKS_IMMEDIATELY sc3_cop2
		SET_CHAR_COORDINATES sc3_cop2 -1755.7499 953.9333 23.7200

	//	SET_CHAR_HEADING sc3_cop2 90.0

	//	CREATE_CAR COPCARSF -1761.4489 952.4333 23.7500 sc3_copcar1
	//	SET_CAR_HEADING sc3_copcar1 209.0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE sc3_cop1 Plc_drgbst_01 POLICE 1.0 TRUE 0 0 0 25000
		


		LVAR_INT cut_time_change

//		effect_to_play = SOUND_CAR_BOOT_OPEN
//		CLEAR_MISSION_AUDIO 3

		cut_time_change = TIMERA + 7606
		sc3_cut_flag = 7
	ENDIF

	IF sc3_cut_flag = 7

		IF NOT hit_bonnet_time = 0
			IF TIMERA > hit_bonnet_time
				REPORT_MISSION_AUDIO_EVENT_AT_CHAR sc3_missionchar SOUND_BONNET_DENT
				hit_bonnet_time = 0
			ENDIF
		ENDIF


		IF TIMERA > cut_time_change
			IF play_audio = 0
				sc3_cut_flag = 8
			ENDIF
		ENDIF
	ENDIF

	IF sc3_cut_flag = 8
		// cut at reat of car
		play_audio = 8
		play_audio_for = 2
		SET_FIXED_CAMERA_POSITION -1757.7467 952.7751 24.8666 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1756.9093 953.3010 25.0154 JUMP_CUT

//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE TIMERA
//		sucky = 3
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE sucky


		IF NOT IS_CHAR_DEAD sc3_cop2
			SET_CHAR_HEADING sc3_cop2 90.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE sc3_cop2 Plc_drgbst_02 POLICE 1.0 FALSE 0 0 0 -1
		ENDIF

		cut_time_change = TIMERA + 3267
		sc3_cut_flag = 9
	ENDIF

	IF sc3_cut_flag = 9
		IF TIMERA > cut_time_change	
			IF play_audio = 0
				sc3_cut_flag = 10
			ENDIF
		ENDIF
	ENDIF

	IF sc3_cut_flag = 10
		// End cut back at front of car
		play_audio = 10
		play_audio_for = 2
		SET_FIXED_CAMERA_POSITION -1768.0479 955.8058 25.1327 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -1767.2375 955.2321 25.0132 JUMP_CUT

//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE TIMERA
//		sucky = 4
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_INT_TO_DEBUG_FILE sucky


		cut_time_change = TIMERA + 750
		sc3_cut_flag = 80
//		effect_to_play[0] = SOUND_PUNCH_PED
		
//		effect_time_to_play[0] = TIMERA + 500
//		effect_time_to_play[1] = TIMERA + 1000
	ENDIF



	IF sc3_cut_flag = 80
		IF TIMERA > play_audio_hit_time
			REPORT_MISSION_AUDIO_EVENT_AT_CHAR sc3_missionchar SOUND_PUNCH_PED
			cut_time_change = TIMERA + 500
			sc3_cut_flag = 81
		ENDIF
	ENDIF
	
	IF sc3_cut_flag = 81
		IF TIMERA > play_audio_dent_time
			REPORT_MISSION_AUDIO_EVENT_AT_CHAR sc3_missionchar SOUND_BONNET_DENT
			cut_time_change = TIMERA + 1900
			sc3_cut_flag = 11
		ENDIF
	ENDIF	

	IF sc3_cut_flag = 11
	AND play_audio = 0
		IF TIMERA > cut_time_change
			SET_FIXED_CAMERA_POSITION -1738.0826 938.6749 25.0378 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1738.9598 939.1542 25.0127 JUMP_CUT

//			SAVE_NEWLINE_TO_DEBUG_FILE
//			SAVE_INT_TO_DEBUG_FILE TIMERA
//			sucky = 5
//			SAVE_NEWLINE_TO_DEBUG_FILE
//			SAVE_INT_TO_DEBUG_FILE sucky


			IF NOT IS_CHAR_DEAD sc3_cop1
			AND NOT IS_CHAR_DEAD sc3_cop2
			AND NOT IS_CAR_DEAD sc3_end_copcar1
			AND NOT IS_CAR_DEAD sc3_end_copcar2
			AND NOT IS_CHAR_DEAD sc3_missionchar
				WARP_CHAR_INTO_CAR sc3_cop1 sc3_end_copcar1
				WARP_CHAR_INTO_CAR sc3_cop2 sc3_end_copcar2
				FREEZE_CHAR_POSITION sc3_missionchar FALSE
				WARP_CHAR_INTO_CAR_AS_PASSENGER sc3_missionchar sc3_end_copcar2 1
				TASK_CAR_DRIVE_TO_COORD sc3_cop2 sc3_end_copcar2 -1790.4049 905.0311 25.0203 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH				 
				OPEN_SEQUENCE_TASK sc3_seq
					TASK_CAR_TEMP_ACTION -1 sc3_end_copcar1 TEMPACT_REVERSE_RIGHT 800
					TASK_CAR_DRIVE_TO_COORD -1 sc3_end_copcar1 -1790.4049 905.0311 25.0203 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH	
				CLOSE_SEQUENCE_TASK sc3_seq
				PERFORM_SEQUENCE_TASK sc3_cop1 sc3_seq
				CLEAR_SEQUENCE_TASK sc3_seq
			ENDIF

			cut_time_change = TIMERA + 4000
			sc3_cut_flag = 12



//			GOTO mission_scrash3_passed 
		ENDIF
	ENDIF

	IF sc3_cut_flag = 12
		IF TIMERA > cut_time_change
			
			sc3_cut_flag = 13
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF DOES_CHAR_EXIST script_valet
				DELETE_CHAR script_valet
			ENDIF

			IF NOT IS_CAR_DEAD sc3_missioncar
				MARK_CAR_AS_NO_LONGER_NEEDED sc3_missioncar
			ENDIF
			IF NOT IS_CHAR_DEAD sc3_cop1
			AND NOT IS_CHAR_DEAD sc3_cop2
			AND NOT IS_CAR_DEAD sc3_end_copcar1
			AND NOT IS_CAR_DEAD sc3_end_copcar2
			AND NOT IS_CHAR_DEAD sc3_missionchar

				DELETE_CHAR sc3_cop1
				DELETE_CHAR sc3_cop2
				DELETE_CAR sc3_end_copcar1
				DELETE_CAR sc3_end_copcar2
				DELETE_CHAR sc3_missionchar
			ENDIF

			IF NOT IS_CAR_DEAD sc3_missioncar
				DELETE_CAR sc3_missioncar
			ENDIF

			IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
			    BUILD_PLAYER_MODEL player1
			ENDIF

			WAIT 1000

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_PLAYER_CONTROL player1 ON

			DO_FADE 1000 FADE_IN

			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

			GOTO mission_scrash3_passed
		ENDIF
	ENDIF








//
//
//	fuckit:
//
//	WAIT 0
//
//
//
//		
//	
//		
//
//	GOTO fuckit
	
	

	



//	CREATE_CAR COPCARSF -1729.5880 1048.5339 44.3116 sc3_copcar2
//	SET_CAR_HEADING sc3_copcar2 98.0
//	MARK_MODEL_AS_NO_LONGER_NEEDED MERIT
//	MARK_MODEL_AS_NO_LONGER_NEEDED COPCARSF

//-1728.8507 1061.3982 44.3496

//	IF NOT IS_CHAR_DEAD sc3_cop1
//		GET_CHAR_COORDINATES sc3_cop1 cunt_x cunt_y player_z
////    	SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE cunt_x cunt_y player_z 2.5 lamppost3 FALSE
//
//		player_z = 23.898
//
//		IF IS_BUTTON_PRESSED PAD1 SQUARE
//			cunt_x -= 0.01
//			SET_CHAR_COORDINATES sc3_cop1 cunt_x cunt_y player_z
//		ENDIF
//		IF IS_BUTTON_PRESSED PAD1 CIRCLE
//			cunt_x += 0.01
//			SET_CHAR_COORDINATES sc3_cop1 cunt_x cunt_y player_z
//		ENDIF
//		IF IS_BUTTON_PRESSED PAD1 CROSS
//			cunt_y -= 0.01
//			SET_CHAR_COORDINATES sc3_cop1 cunt_x cunt_y player_z
//		ENDIF
//		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
//			cunt_y += 0.01
//			SET_CHAR_COORDINATES sc3_cop1 cunt_x cunt_y player_z
//		ENDIF
//
//		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
//			GET_SCRIPT_TASK_STATUS sc3_cop1 -1 task_Statt
//			IF task_statt = FINISHED_TASK
//			OR task_statt = WAITING_TO_START_TASK
//				CLEAR_CHAR_TASKS_IMMEDIATELY sc3_cop1
//				TASK_PLAY_ANIM_NON_INTERRUPTABLE sc3_cop1 Plc_drgbst_01 POLICE 1.0 TRUE 0 0 0 25000
//			ENDIF
//		ENDIF
//
//	ENDIF
//
//

	
ENDIF

IF sc3_flag = 13
//	IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer -2179.1973 715.6656 52.9098 3.0 3.0 3.0 TRUE
	IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer -2045.2523 178.7994 27.8359 3.0 3.0 3.0 TRUE
	ENDIF
	IF IS_CHAR_IN_AREA_IN_CAR_3D scplayer -2042.9429 167.5841 26.7590 -2055.2017 180.1328 32.3919 FALSE
		IF NOT IS_CAR_DEAD sc3_missioncar
			IF IS_CHAR_IN_CAR scplayer sc3_missioncar
				SET_PLAYER_CONTROL player1 OFF
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE


				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer


//				IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 valet
//					GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
//				    BUILD_PLAYER_MODEL player1
//				ENDIF

				IF NOT IS_CAR_DEAD sc3_missioncar
					GET_CAR_COORDINATES sc3_missioncar sc3_x sc3_y sc3_z
					GET_CAR_HEADING sc3_missioncar sc3_h
					GET_CAR_COLOURS sc3_missioncar sc3_col1 sc3_col2

					DELETE_CAR sc3_missioncar
				ENDIF
				REQUEST_MODEL MERIT
				WHILE NOT HAS_MODEL_LOADED MERIT
					WAIT 0
				ENDWHILE
				CREATE_CAR MERIT -2031.5023 178.6435 27.8516 sc3_missioncar
				CHANGE_CAR_COLOUR sc3_missioncar sc3_col1 sc3_col2
				SET_CAR_HEADING sc3_missioncar 270.0
				SET_TARGET_CAR_FOR_MISSION_GARAGE hbgdSFS sc3_missioncar

				IF NOT IS_CAR_DEAD sc3_missioncar
					WARP_CHAR_INTO_CAR scplayer sc3_missioncar
				ENDIF

				WAIT 1000
				
				
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE				

				REMOVE_BLIP sc3_drug_plant_blip 
				ADD_BLIP_FOR_COORD -1780.0695 970.9553 23.7500 sc3_drug_plant_blip 
				CHANGE_BLIP_DISPLAY sc3_drug_plant_blip BLIP_ONLY
				sc3_flag = 8
				CLEAR_PRINTS
				PRINT VAL_33 5000 1
			ENDIF
		ENDIF
	ENDIF  
ENDIF


IF sc3_flag = 11
	
ENDIF
	
//GOTO mission_scrash3_passed




GOTO mission_scrash3_loop



scrash1_audio:


	LVAR_INT play_timed_audio play_timed_audio_flag audio_time_start audio_timer_flag audio_time[10] audio_to_play[10] play_timed_audio_for
	LVAR_INT audio_fading
 
	// SOUND EFFECTS WORK HERE

	IF NOT effect_to_play[0] = 0

		IF effect_flag = 0
			CLEAR_MISSION_AUDIO 3
			LOAD_MISSION_AUDIO 3 effect_to_play[0]						 			
			effect_Flag = 1
		ENDIF

		IF effect_flag = 1
			IF HAS_MISSION_AUDIO_LOADED 3
				IF TIMERA > effect_time_to_play[0]
					PLAY_MISSION_AUDIO 3
					effect_flag = 2					
				ENDIF
			ENDIF
		ENDIF

		IF effect_flag = 2
			IF HAS_MISSION_AUDIO_FINISHED 3
				CLEAR_MISSION_AUDIO 3
				effect_to_play[0] = effect_to_play[1]
				effect_to_play[1] = 0
				effect_time_to_play[0] = effect_time_to_play[1]
				effect_time_to_play[1] = 0
				effect_flag = 0
			ENDIF
		ENDIF	
	ENDIF

	LVAR_INT effect_flag effect_to_play[2] effect_time_to_play[2]



	// DIALOGUE AUDIO WORKS HERE

	// play timed audio
	IF NOT play_timed_audio = 0
		IF play_timed_audio_flag = 0
			play_timed_audio_flag = 1
			audio_time_start = TIMERA
			audio_timer_flag = 0
			play_delay = audio_time_start + audio_time[audio_timer_flag]
		ENDIF	

		IF play_timed_audio_flag = 1
			IF TIMERA > play_delay
				play_audio = audio_to_play[audio_timer_flag]
				audio_timer_flag ++
				play_timed_audio_for --
				IF play_timed_audio_for = 0
					play_timed_audio = 0
					play_timed_audio_flag = 0
				ELSE
					play_delay = audio_time_start + audio_time[audio_timer_flag]
				ENDIF
			ENDIF
		ENDIF
	ENDIF

//	IF GET_FADING_STATUS
//		play_audio = 0
//		play_audio_for = 0
//		audio_fading = 1
//	ENDIF

	IF audio_fading = 1
		IF NOT GET_FADING_STATUS
			IF NOT HAS_MISSION_AUDIO_FINISHED 1
			OR NOT HAS_MISSION_AUDIO_FINISHED 2
				audio_fading = 0
				audio_flag = 5
			ENDIF
		ENDIF
	ENDIF
		
		


	SWITCH audio_flag


	

		CASE 0 //first time game starts
			
			LVAR_TEXT_LABEL audio_text[40]
			LVAR_INT audio_sound[40] audio_slot[3] play_slot  
			LVAR_INT next_audio  
			LVAR_INT audio_flag play_audio play_audio_for
			LVAR_INT audio_for_char[40] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay audio_i audio_char audio_count force_audio


			IF audio_flag = 1
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 this_actor
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 audio_char
			ENDIF

			$audio_text[1] = &SCR1_AA
			$audio_text[2] = &SCR1_AB			
			$audio_text[3] = &SCR1_AC
			$audio_text[4] = &SCR1_AD
			$audio_text[5] = &SCR1_AE
			$audio_text[6] = &SCR1_AF
			$audio_text[7] = &SCR1_AG
			$audio_text[8] = &SCR1_AH
			$audio_text[9] = &SCR1_AJ
			$audio_text[10] = &SCR1_AK
			$audio_text[11] = &SCR1_AL
			$audio_text[12] = &SCR1_AM
			$audio_text[13] = &SCR1_BB
			$audio_text[14] = &SCR1_BA

			
			 
			audio_sound[1] = SOUND_SCR1_AA
			audio_sound[2] = SOUND_SCR1_AB
			audio_sound[3] = SOUND_SCR1_AC
			audio_sound[4] = SOUND_SCR1_AD
			audio_sound[5] = SOUND_SCR1_AE
			audio_sound[6] = SOUND_SCR1_AF
			audio_sound[7] = SOUND_SCR1_AG
			audio_sound[8] = SOUND_SCR1_AH
			audio_sound[9] = SOUND_SCR1_AJ
			audio_sound[10] = SOUND_SCR1_AK
			audio_sound[11] = SOUND_SCR1_AL
			audio_sound[12] = SOUND_SCR1_AM
			audio_sound[13] = SOUND_SCR1_BB
			audio_sound[14] = SOUND_SCR1_BA

		  
			audio_for_char[1] = 1
			audio_for_char[2] = 1
			audio_for_char[3] = 3
			audio_for_char[4] = 3
			audio_for_char[5] = 2
			audio_for_char[6] = 2
			audio_for_char[7] = 3
			audio_for_char[8] = 4
			audio_for_char[9] = 4
			audio_for_char[10] = 2
			audio_for_char[11] = 3
			audio_for_char[12] = 3
			audio_for_char[13] = 0
			audio_for_char[14] = 0

			audio_actor[1] = scplayer

			
			//1 = catalina
			//2 = player

			audio_flag = 1
//			play_audio = 0

			LOAD_MISSION_AUDIO 1 audio_sound[1]
			LOAD_MISSION_AUDIO 2 audio_sound[2]

			audio_slot[1] = 1
			audio_slot[2] = 2


		BREAK

		CASE 1 //waiting to play audio
			
			IF NOT play_audio = 0

				IF HAS_MISSION_AUDIO_FINISHED 1
				AND HAS_MISSION_AUDIO_FINISHED 2
					IF audio_slot[1] = play_audio
						play_slot = 1
					ELSE
						IF audio_slot[2] = play_audio
							play_slot = 2
						ELSE
							play_slot = 1
							audio_slot[1] = play_audio
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 audio_sound[play_audio]
							//audio hasn't been requested yet
						ENDIF
					ENDIF			

					IF HAS_MISSION_AUDIO_LOADED play_slot
						actor_int = audio_for_char[play_audio]
						this_actor = audio_actor[actor_int]
						IF NOT force_audio = 1 //otherwise audio will not play if no mission peds are nearby
							audio_i = 1
							audio_count = 0
							WHILE audio_i < 7
								audio_char = audio_actor[audio_i]
								IF NOT audio_char = 0
									IF NOT audio_char = this_actor
										IF NOT IS_CHAR_DEAD this_actor
											IF NOT IS_CHAR_DEAD audio_char
												IF LOCATE_CHAR_ANY_MEANS_CHAR_3D this_actor audio_char 10.0 10.0 10.0 FALSE
													audio_count ++
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								audio_i++
							ENDWHILE
						ENDIF

						IF force_audio = 1
						OR audio_count > 0
						OR audio_for_char[play_audio] = 0
							IF NOT audio_for_char[play_audio] = 0
								IF NOT IS_CHAR_DEAD this_actor   
									ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor								 
									IF NOT IS_CHAR_TALKING this_actor
										play_audio_now = 1
										START_CHAR_FACIAL_TALK this_actor 15000
									ELSE
										DISABLE_CHAR_SPEECH this_actor FALSE
									ENDIF
								ENDIF
							ENDIF

							IF audio_for_char[play_audio] = 0
								play_audio_now = 1
							ENDIF

							IF play_audio_now = 1							
								PLAY_MISSION_AUDIO play_slot
//								SAVE_NEWLINE_TO_DEBUG_FILE
//								SAVE_INT_TO_DEBUG_FILE TIMERA
//								LVAR_INT sucky
//								sucky = 1
//								SAVE_NEWLINE_TO_DEBUG_FILE
//								SAVE_INT_TO_DEBUG_FILE sucky

								CLEAR_PRINTS
								PRINT $audio_text[play_audio] 10000 1
								audio_flag ++
								play_audio_now = 0

								play_audio ++
								next_audio = play_audio

								// if the other slot doesn't already have the next audio loaded, then load it.
								IF NOT audio_sound[play_audio] = 0
									IF play_slot = 1									
										IF NOT audio_slot[2] = play_audio
											LOAD_MISSION_AUDIO 2 audio_sound[play_audio]	
											audio_slot[2] = play_audio
										ENDIF
									ELSE
										IF NOT audio_slot[1] = play_audio
											LOAD_MISSION_AUDIO 1 audio_sound[play_audio]	
											audio_slot[1] = play_audio
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE
						LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
					ENDIF
				ENDIF
			ENDIF
		BREAK


		CASE 2 // check if audio has/should finish
			IF HAS_MISSION_AUDIO_FINISHED play_slot
				audio_flag++
			ELSE
				IF DOES_CHAR_EXIST this_actor
					IF IS_CHAR_DEAD this_actor
						audio_flag++
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD this_actor
				ENDIF
			ENDIF
		BREAK

		CASE 3 //clear audio
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD this_actor
				STOP_CHAR_FACIAL_TALK this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF
			audio_flag++
		BREAK

		CASE 4 //request next audio

			play_audio ++
			IF NOT audio_sound[play_audio] = 0 
				LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
				audio_slot[play_slot] = play_audio
			ENDIF
			
			play_audio_for -= 1
			IF NOT play_audio_for > 0
				play_audio = 0
			ELSE
				play_audio = next_audio
			ENDIF
			audio_flag = 1
		BREAK

		CASE 5 // clear all for cut scene skip

			audio_flag = 1
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK
	ENDSWITCH
RETURN



cleanup_uniform:

	CLEAR_PRINTS

	IF NOT IS_MINIGAME_IN_PROGRESS
		SET_PLAYER_CONTROL player1 OFF
	ENDIF

	IF NOT IS_GARAGE_CLOSED HBGDSFS  
		CLOSE_GARAGE HBGDSFS
	ENDIF

	IF IS_CHAR_IN_AREA_3D scplayer -2039.1527 182.7238 20.8154 -2057.0874 150.6935 34.1252 FALSE

		
		IF IS_PLAYER_WEARING player1  CLOTHES_TEX_EXTRA1 valet
		    DO_FADE 1000 FADE_OUT 
	    	WHILE GET_FADING_STATUS
	                WAIT 0
		    ENDWHILE
				 
			car = 0		 
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer car
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 0.0 0.0 0.0
			ENDIF

			

			CLEAR_AREA_OF_CARS -2039.0558 182.7439 32.4018 -2057.3413 150.5405 27.7805
			CLEAR_AREA_OF_CHARS -2039.0558 182.7439 32.4018 -2057.3413 150.5405 27.7805


			SET_CHAR_COORDINATES_NO_OFFSET scplayer -2037.8862 178.9478 27.8359
			SET_CHAR_HEADING scplayer 270.0
		




			IF NOT IS_CHAR_DEAD sc3_missionchar
				IF NOT IS_CHAR_IN_ANY_CAR sc3_missionchar
					DELETE_CHAR sc3_missionchar
				ENDIF
			ENDIF


			SET_PLAYER_CONTROL player1 OFF
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
			PRINT_BIG M_FAIL 5000 1
			valet_mission_terminate = 1


 			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
   			SET_PLAYER_CONTROL PLAYER1 ON

		    GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
	    	BUILD_PLAYER_MODEL player1
		    DO_FADE 1000 FADE_IN 
		    WHILE GET_FADING_STATUS
	    	            WAIT 0
		    ENDWHILE
		ENDIF
	ELSE
		IF IS_PLAYER_WEARING player1  CLOTHES_TEX_EXTRA1 valet
		    DO_FADE 1000 FADE_OUT 
	    	WHILE GET_FADING_STATUS
	                WAIT 0
		    ENDWHILE

			

			car = 0		 
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer car
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 0.0 0.0 0.0
			ENDIF

			

			CLEAR_AREA_OF_CARS -2039.0558 182.7439 32.4018 -2057.3413 150.5405 27.7805
			CLEAR_AREA_OF_CHARS -2039.0558 182.7439 32.4018 -2057.3413 150.5405 27.7805

			IF NOT IS_CAR_DEAD car
				WARP_CHAR_INTO_CAR scplayer car							
			ELSE
				IF NOT car = 0
					SET_CHAR_COORDINATES_NO_OFFSET scplayer -2037.8862 178.9478 27.8359
					SET_CHAR_HEADING scplayer 270.0
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD sc3_missionchar
				IF NOT IS_CHAR_IN_ANY_CAR sc3_missionchar
					DELETE_CHAR sc3_missionchar
				ENDIF
			ENDIF

		    GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
	    	BUILD_PLAYER_MODEL player1
		    DO_FADE 1000 FADE_IN 
		    WHILE GET_FADING_STATUS
	    	            WAIT 0
		    ENDWHILE
		ENDIF
	ENDIF
                                    
RETURN






	
// Mission wuzi1 failed
mission_scrash3_failed:

		PRINT_BIG M_FAIL 5000 1
		valet_mission_terminate = 1
		valet_unlocked = 0

RETURN

   

// mission wuzi1 passed
mission_scrash3_passed:

	//flag_wuzi1_mission1_passed = 1

	SET_INT_STAT PASSED_ZERO1 1
	valet_unlocked = 1
	valet_oddjob_opened = 1
	PRINT_WITH_NUMBER_BIG ( M_PASSD ) 0 5000 1 //"Mission Passed!"
	SET_INT_STAT PASSED_SCRASH1 1


	//ADD_SCORE player1 30000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	PLAYER_MADE_PROGRESS 1
	flag_scrash_mission_counter ++
	flag_player_got_valet_uniform = 1

	REGISTER_MISSION_PASSED ( SCRA_1 ) //Used in the stats 


	REMOVE_BLIP garage_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ RADAR_SPRITE_CJ garage_contact_blip
	REMOVE_BLIP scrash_contact_blip

	//REGISTER_MISSION_PASSED wuzi1
	//START_NEW_SCRIPT wuzi1_mission_loop
RETURN
		


// mission cleanup
mission_cleanup_scrash3:


SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
playing_scrash1 = 0

freeze_creating_drop_off_cars = 0
force_valet_on_mission = 0
SET_WANTED_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0

SWITCH_ROADS_ON 2793.8140 -1987.9346 15.3045 2754.3240 -1900.3694 9.8752

CLEAR_ONSCREEN_TIMER sc3_countdown

REMOVE_BLIP sc3_drug_plant_blip
REMOVE_BLIP sc3_missioncar_blip
REMOVE_BLIP sc3_valet_blip
REMOVE_BLIP sc3_blip[0]
REMOVE_BLIP sc3_blip[1]
REMOVE_BLIP sc3_blip[2]
REMOVE_BLIP val_uniform_blip

//CHANGE_GARAGE_TYPE hbgdSFS GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE
SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -1741.7917 939.7310 23.8906 2.5 lamppost3 TRUE

DONT_SUPPRESS_CAR_MODEL MERIT

SWITCH_PED_ROADS_BACK_TO_ORIGINAL -1729.5487 942.8860 40.3585 -1742.7734 937.7814 10.1928
SWITCH_PED_ROADS_BACK_TO_ORIGINAL -1768.5243 938.8870 40.7496 -1780.7653 943.4574 10.0929

MARK_MODEL_AS_NO_LONGER_NEEDED MTBIKE
//MARK_MODEL_AS_NO_LONGER_NEEDED wmyplt
MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE
MARK_MODEL_AS_NO_LONGER_NEEDED CHEETAH
MARK_MODEL_AS_NO_LONGER_NEEDED MERIT
MARK_MODEL_AS_NO_LONGER_NEEDED SWMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED WMYVA2
MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE

REMOVE_CAR_RECORDING 155

		MARK_MODEL_AS_NO_LONGER_NEEDED COPCARSF
		MARK_MODEL_AS_NO_LONGER_NEEDED SWMYRI
		MARK_MODEL_AS_NO_LONGER_NEEDED SFPD1
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
		REMOVE_ANIMATION POLICE
		REMOVE_ANIMATION MISC
		REMOVE_CAR_RECORDING 165
		REMOVE_CAR_RECORDING 166

IF NOT IS_CAR_DEAD sc3_missioncar
	SET_CAR_PROOFS sc3_missioncar FALSE FALSE FALSE FALSE FALSE
ENDIF

GET_GAME_TIMER timer_mobile_start

//REMOVE_BLIP wz1_meet1_blip
pause_valet_script = 0
flag_player_on_mission = 0
freeze_creating_drop_off_cars = 0
sc3_missioncar_alive = 0
dont_clear_valets = 0
tell_player_to_kill_valet = 0
MISSION_HAS_FINISHED
RETURN



}









