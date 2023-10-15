MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 19/11/2003 	Time: 15:41:04	   Name:  Chris Rothwell 					 ***
// ***																					 ***
// *** Mission: Desert 10 - Train Heist	 												 ***
// ***																					 ***
// *** Brief: Player must use jet pack to catch up to and pull alongside the train. 	 *** 
// *** Guards along the train must be killed before the Player can land on the roof and  ***
// *** blow open the roof's loading doors. Player must retrieve <item> and return it to  ***
// *** The Truth.																		 ***
// ***																					 ***
// *****************************************************************************************

GOSUB mission_des10_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_des10_failed
ENDIF

GOSUB mission_des10_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_des10_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME des10

WAIT 0

LVAR_INT mission_blip mission_flag mission_timer sequence_task players_car temp_int
LVAR_INT r c e v m s b a
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 speed temp_float
LOAD_MISSION_TEXT DSERT10

FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT

MAKE_PLAYER_GANG_DISAPPEAR

lvar_int fat_level
GET_INT_STAT FAT fat_level
if fat_level > 600
	LOAD_CUTSCENE D10_ALT
	 
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 
	clear_area 405.2136 2525.9004 16.4918 7.5 0
	START_CUTSCENE

	DO_FADE 1000 FADE_IN
	  
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE
	 
	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 0 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	 
	CLEAR_CUTSCENE
	
	CLEAR_AREA 419.4488 2529.9143 15.6219 0.5 0
	LOAD_SCENE 419.4488 2529.9143 15.6219
	SET_CHAR_COORDINATES scplayer 419.4488 2529.9143 15.6219 
	SET_CHAR_HEADING scplayer 180.0
	SET_CAMERA_BEHIND_PLAYER
	DO_FADE 1500 FADE_IN
	SET_PLAYER_CONTROL player1 ON
	return
else
	LOAD_CUTSCENE DES_10A
	 
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 
	clear_area 419.6919 2529.9143 16.6612 6.8600 0
	START_CUTSCENE

	DO_FADE 1000 FADE_IN
	  
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 0 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	 
	CLEAR_CUTSCENE
endif

RELEASE_WEATHER

REQUEST_MODEL MICRO_UZI
REQUEST_MODEL FREIGHT
REQUEST_MODEL FREIFLAT
REQUEST_MODEL MP5LNG
REQUEST_MODEL rhino
REQUEST_MODEL army
request_model jetpack
LOAD_ALL_MODELS_NOW
WHILE NOT HAS_MODEL_LOADED MICRO_UZI
OR NOT HAS_MODEL_LOADED FREIGHT
OR NOT HAS_MODEL_LOADED FREIFLAT
OR NOT HAS_MODEL_LOADED MP5LNG
OR NOT HAS_MODEL_LOADED rhino
OR NOT HAS_MODEL_LOADED army
	WAIT 0
ENDWHILE

WHILE NOT IS_PLAYER_PLAYING player1
	WAIT 0
ENDWHILE

//VAR_INT car1
//CREATE_CAR FREIFLAT 0.0 0.0 50.0 car1
//LVAR_INT blippy
//ADD_BLIP_FOR_CAR car1 blippy
//SET_CAR_COORDINATES car1 0.0 0.0 50.0
//FREEZE_CAR_POSITION car1 TRUE
//WHILE NOT IS_CAR_DEAD car1
//	WAIT 0
//	DRAW_LIGHT 3.1294 -5.2529 57.0223 255 255 255
//	DRAW_LIGHT 3.7165 -0.8260 56.4677 255 255 255
//	DRAW_LIGHT 3.9602 1.9442 56.1411 255 255 255
//	DRAW_LIGHT 2.6261 8.9473 55.3012 255 255 255
//	DRAW_LIGHT -3.1294 -5.2529 57.0223 255 255 255
//	DRAW_LIGHT -3.7165 -0.8260 56.4677 255 255 255
//	DRAW_LIGHT -3.9602 1.9442 56.1411 255 255 255
//	DRAW_LIGHT -2.6261 8.9473 55.3012 255 255 255
//	IF NOT IS_CAR_DEAD car1
//		SET_CAR_COORDINATES car1 0.0 0.0 50.0
//	ENDIF
//ENDWHILE

SWITCH_RANDOM_TRAINS OFF

CLEAR_AREA 419.4488 2529.9143 15.6219 0.5 0
LOAD_SCENE 419.4488 2529.9143 15.6219
SET_CHAR_COORDINATES scplayer 419.4488 2529.9143 15.6219 
SET_CHAR_HEADING scplayer 180.0
SET_CAMERA_BEHIND_PLAYER
SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 OFF
TASK_JETPACK scplayer
GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 2000

while not has_model_loaded jetpack
	wait 0
endwhile

MAKE_PLAYER_GANG_REAPPEAR

DO_FADE 1500 FADE_IN
SET_PLAYER_CONTROL player1 ON

LVAR_INT heist_train[8]
CREATE_MISSION_TRAIN 13 2120.2573 2694.0579 9.8359 0 heist_train[0]
LOCK_CAR_DOORS heist_train[0] CARLOCK_LOCKED
SET_TRAIN_SPEED heist_train[0] 5.0
SET_TRAIN_CRUISE_SPEED heist_train[0] 5.0

PRINT_NOW DE10_01 5000 1//Fly jetpack to the train and steal aliens.

GET_CAR_COORDINATES	heist_train[0] x y z
z += 10.0
m = 0

WHILE m < 12
	LVAR_INT traindude[12]
	CREATE_CHAR	PEDTYPE_CIVMALE army x y z traindude[m]
	TASK_STAY_IN_SAME_PLACE traindude[m] TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	traindude[m] TRUE
	GIVE_WEAPON_TO_CHAR	traindude[m] WEAPONTYPE_MP5 9999
	//SET_CHAR_ACCURACY traindude[m] 99
	TASK_KILL_CHAR_ON_FOOT traindude[m] scplayer
	SET_CHAR_RELATIONSHIP traindude[m] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
	SET_SENSE_RANGE traindude[m] 150.0
	SET_LOAD_COLLISION_FOR_CHAR_FLAG traindude[m] FALSE
	//LVAR_INT traindude_blip[12]
	//ADD_BLIP_FOR_CHAR traindude[m] traindude_blip[m]
	++ m
ENDWHILE

//ENGINE
ATTACH_CHAR_TO_CAR traindude[0] heist_train[0] -0.0032 -8.3584 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5//REAR OF ENGINE
ATTACH_CHAR_TO_CAR traindude[1] heist_train[0] 0.1129 6.5210 1.9285 FACING_LEFT 360.0 WEAPONTYPE_MP5//FRONT OF ENGINE

//CARRIAGE 1
GET_TRAIN_CARRIAGE heist_train[0] 1 heist_train[1]
ATTACH_CHAR_TO_CAR traindude[2] heist_train[1] -0.0140 -7.1790 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
ATTACH_CHAR_TO_CAR traindude[3] heist_train[1] -1.0891 0.6968 -0.055 FACING_LEFT 360.0 WEAPONTYPE_MP5

LVAR_INT alien_crate[3]	alien_crate_blip[3]
CREATE_OBJECT_NO_OFFSET KMILITARY_CRATE x y z alien_crate[0]
ATTACH_OBJECT_TO_CAR alien_crate[0] heist_train[1] -0.0485 5.5780 -1.0661 0.0 0.0 90.1967 
MAKE_OBJECT_TARGETTABLE alien_crate[0] TRUE
SET_OBJECT_HEALTH alien_crate[0] 180
ADD_BLIP_FOR_OBJECT alien_crate[0] alien_crate_blip[0]

LVAR_INT crate_object[19]
CREATE_OBJECT_NO_OFFSET kmilitary_base x y z crate_object[0]
ATTACH_OBJECT_TO_CAR crate_object[0] heist_train[1] -0.0485 5.5780 -1.0661 0.0 0.0 90.1967

CREATE_OBJECT_NO_OFFSET blockpallet x y z crate_object[1]
ATTACH_OBJECT_TO_CAR crate_object[1] heist_train[1] 0.2634 1.2508 -0.3321 0.0 0.0 90.1967 

CREATE_OBJECT_NO_OFFSET temp_crate1 x y z crate_object[2]
ATTACH_OBJECT_TO_CAR crate_object[2] heist_train[1] 1.1025 -7.3122 -1.0766 0.0 0.0 90.1967

CREATE_OBJECT_NO_OFFSET temp_crate1 x y z crate_object[3]
ATTACH_OBJECT_TO_CAR crate_object[3] heist_train[1] 0.8835 -6.5365 -1.1047 0.0 0.0 93.7464

CREATE_OBJECT_NO_OFFSET temp_crate1 x y z crate_object[4]
ATTACH_OBJECT_TO_CAR crate_object[4] heist_train[1] 1.1395 -5.6969 -1.0827 0.0 0.0 83.6459

CREATE_OBJECT_NO_OFFSET k_smashboxes x y z crate_object[5]
ATTACH_OBJECT_TO_CAR crate_object[5] heist_train[1] 0.4817 -1.5356 -1.0651 0.0 0.0 93.1856

//CARRIAGE 2
GET_TRAIN_CARRIAGE heist_train[0] 2 heist_train[2]
ATTACH_CHAR_TO_CAR traindude[4] heist_train[2] -0.0140 -7.1790 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
ATTACH_CHAR_TO_CAR traindude[5] heist_train[2] -1.0891 0.6968 -0.055 FACING_LEFT 360.0 WEAPONTYPE_MP5

//CARRIAGE 3
GET_TRAIN_CARRIAGE heist_train[0] 3 heist_train[3]
ATTACH_CHAR_TO_CAR traindude[6] heist_train[3] 0.3372 -1.3877 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
ATTACH_CHAR_TO_CAR traindude[7] heist_train[3] -0.0956 2.1291 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5

CREATE_OBJECT_NO_OFFSET KMILITARY_CRATE x y z alien_crate[1]
ATTACH_OBJECT_TO_CAR alien_crate[1] heist_train[3] -0.0403 4.0293 -1.0661 0.0 0.0 90.1967 
MAKE_OBJECT_TARGETTABLE alien_crate[1] TRUE
SET_OBJECT_HEALTH alien_crate[1] 180
ADD_BLIP_FOR_OBJECT alien_crate[1] alien_crate_blip[1]

CREATE_OBJECT_NO_OFFSET kmilitary_base x y z crate_object[6]
ATTACH_OBJECT_TO_CAR crate_object[6] heist_train[3] -0.0403 4.0293 -1.0661 0.0 0.0 2.7605 

CREATE_OBJECT_NO_OFFSET k_cargo1 x y z crate_object[7] 
ATTACH_OBJECT_TO_CAR crate_object[7] heist_train[3] -0.0177 0.6971 -1.0662 0.0 0.0 91.0 

CREATE_OBJECT_NO_OFFSET k_smashboxes x y z crate_object[8] 
ATTACH_OBJECT_TO_CAR crate_object[8] heist_train[3] -0.0228 -2.9213 -1.0651 0.0 0.0 87.9095 

CREATE_OBJECT_NO_OFFSET k_smashboxes x y z crate_object[9] 
ATTACH_OBJECT_TO_CAR crate_object[9] heist_train[3] 0.1054 6.4364 -1.0651 0.0 0.0 89.7187 

//CREATE_OBJECT_NO_OFFSET k_cargo4 x y z crate_object[10] 
//ATTACH_OBJECT_TO_CAR crate_object[10] heist_train[3] 0.7382 -4.6029 -1.0628 0.0 0.0 358.6504 
//
//CREATE_OBJECT_NO_OFFSET k_cargo4 x y z crate_object[11] 
//ATTACH_OBJECT_TO_CAR crate_object[11] heist_train[3] -0.7168 -4.6539 -1.0628 0.0 0.0 2.7605 

CREATE_OBJECT_NO_OFFSET k_cargo4 x y z crate_object[12] 
ATTACH_OBJECT_TO_CAR crate_object[12] heist_train[3] 0.4192 -6.0624 -1.0628 0.0 0.0 2.7605 

//CARRIAGE 4
GET_TRAIN_CARRIAGE heist_train[0] 4 heist_train[4]
ATTACH_CHAR_TO_CAR traindude[8] heist_train[4] -0.5160 7.4724 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
LVAR_INT train_rhino
CREATE_CAR rhino x y z train_rhino
LOCK_CAR_DOORS train_rhino CARLOCK_LOCKED
ATTACH_CAR_TO_CAR train_rhino heist_train[4] 0.0 0.0 0.0 0.0 0.0 0.0 //Z=0.5588

//CARRIAGE 5
GET_TRAIN_CARRIAGE heist_train[0] 5 heist_train[5]
ATTACH_CHAR_TO_CAR traindude[9] heist_train[5] 0.2861 -5.3712 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
ATTACH_CHAR_TO_CAR traindude[10] heist_train[5] -0.2277 7.3808 -0.055 FACING_RIGHT 360.0 WEAPONTYPE_MP5
ATTACH_CHAR_TO_CAR traindude[11] heist_train[5] 0.6604 3.8015 -0.055  FACING_RIGHT 360.0 WEAPONTYPE_MP5

CREATE_OBJECT_NO_OFFSET kmilitary_crate x y z alien_crate[2]
ATTACH_OBJECT_TO_CAR alien_crate[2] heist_train[5] 0.0853 5.9501 -1.0583 0.0 0.0 90.1967 
MAKE_OBJECT_TARGETTABLE alien_crate[2] TRUE
SET_OBJECT_HEALTH alien_crate[2] 180
ADD_BLIP_FOR_OBJECT alien_crate[2] alien_crate_blip[2]

CREATE_OBJECT_NO_OFFSET kmilitary_base x y z crate_object[13] 
ATTACH_OBJECT_TO_CAR crate_object[13] heist_train[5] 0.0853 5.9501 -1.0583 0.0 0.0 92.2390 

CREATE_OBJECT_NO_OFFSET k_cargo4 x y z crate_object[14] 
ATTACH_OBJECT_TO_CAR crate_object[14] heist_train[5] -0.8464 -6.5879 -1.0628 0.0 0.0 0.1200 

//CREATE_OBJECT_NO_OFFSET k_cargo4 x y z crate_object[15] 
//ATTACH_OBJECT_TO_CAR crate_object[15] heist_train[5] 0.7975 -3.6648 -1.0518 0.0 0.0 0.1200 

CREATE_OBJECT_NO_OFFSET k_cargo1 x y z crate_object[16] 
ATTACH_OBJECT_TO_CAR crate_object[16] heist_train[5] 0.8877 0.1707 -1.0564 0.0 0.0 177.5167 

CREATE_OBJECT_NO_OFFSET k_cargo1 x y z crate_object[17] 
ATTACH_OBJECT_TO_CAR crate_object[17] heist_train[5] -0.6907 0.2050 -1.0662 0.0 0.0 179.6863 

CREATE_OBJECT_NO_OFFSET k_cargo1 x y z crate_object[18] 
ATTACH_OBJECT_TO_CAR crate_object[18] heist_train[5] 0.0036 2.2326 -1.0644 0.0 0.0 267.6086 

LVAR_INT alien_crate_flag[3]
alien_crate_flag[0] = 0
alien_crate_flag[1] = 0
alien_crate_flag[2] = 0

LVAR_INT created_alien_crates destroyed_alien_crates
created_alien_crates = 0
destroyed_alien_crates = 0

get_game_timer game_timer
mission_timer = game_timer + 600000

mission_flag = 0

lvar_int done_instruction_text
done_instruction_text = 0

LOAD_MISSION_AUDIO 3 SOUND_GREEN_GOO_HUM


// ************************************* MISSION LOOP **************************************
mission_des10_loop:

WAIT 0

get_game_timer game_timer

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_des10_passed
ENDIF

IF NOT IS_CAR_DEAD heist_train[0]
	SET_TRAIN_CRUISE_SPEED heist_train[0] 10.0
ENDIF

if done_instruction_text = 0
	if locate_char_any_means_car_2d scplayer heist_train[0] 150.0 150.0 0
		print_now DE10_06 5000 1//Shoot the crates on the train to find the Jar of Green Goo.
		++ done_instruction_text
	endif
endif

m = 0
WHILE m < 3
	IF alien_crate_flag[m] = 0
		IF DOES_OBJECT_EXIST alien_crate[m]
			++ created_alien_crates
			++ alien_crate_flag[m]
		ENDIF
	ENDIF
	IF alien_crate_flag[m] = 1
		IF HAS_OBJECT_BEEN_DAMAGED alien_crate[m]
			REMOVE_BLIP	alien_crate_blip[m]
			++ destroyed_alien_crates
			++ alien_crate_flag[m]
		ENDIF
	ENDIF
	IF alien_crate_flag[m] = 2
		IF destroyed_alien_crates = created_alien_crates
			GET_OBJECT_COORDINATES alien_crate[m] x y z
			z += 1.0
			DELETE_OBJECT alien_crate[m]
			CREATE_OBJECT_NO_OFFSET GREEN_GLOOP x y z alien_crate[m]
			IF m = 0
				IF NOT IS_CAR_DEAD heist_train[1]
					ATTACH_OBJECT_TO_CAR alien_crate[m] heist_train[1] -0.0485 5.5780 -0.0661 0.0 0.0 90.1967
				ENDIF
			ELSE
				IF m = 1
					IF NOT IS_CAR_DEAD heist_train[3]
						ATTACH_OBJECT_TO_CAR alien_crate[m] heist_train[3] -0.0485 5.5780 -0.0661 0.0 0.0 90.1967
					ENDIF
				ELSE
					IF NOT IS_CAR_DEAD heist_train[5]
						ATTACH_OBJECT_TO_CAR alien_crate[m] heist_train[5] -0.0485 5.5780 -0.0661 0.0 0.0 90.1967
					ENDIF
				ENDIF
			ENDIF
			SET_OBJECT_COLLISION alien_crate[m] FALSE
			ADD_BLIP_FOR_OBJECT alien_crate[m] alien_crate_blip[m]
			PRINT_NOW DE10_02 5000 1//That's the right crate! Grab the top secret artifact.
			LOAD_MISSION_AUDIO 3 SOUND_GREEN_GOO_HUM
			if HAS_MISSION_AUDIO_LOADED 3
				//REPORT_MISSION_AUDIO_EVENT_AT_OBJECT alien_crate[m] SOUND_GREEN_GOO_HUM
				ATTACH_MISSION_AUDIO_TO_OBJECT 3 alien_crate[m]
				PLAY_MISSION_AUDIO 3
			endif
			alien_crate_flag[m] = 4
		ELSE
			MARK_OBJECT_AS_NO_LONGER_NEEDED	alien_crate[m]
			PRINT_NOW DE10_03 5000 1//That crate is empty, try another.
			++ alien_crate_flag[m]
		ENDIF
	ENDIF
	IF alien_crate_flag[m] = 4
		GET_OBJECT_COORDINATES alien_crate[m] x y z
		LVAR_FLOAT alien_corona_size size_change
		alien_corona_size += size_change
		IF alien_corona_size > 0.2
			//alien_corona_size = 0.6
			size_change = -0.01
		ENDIF
		IF alien_corona_size < 0.0
			alien_corona_size = 0.0
			size_change = 0.01
		ENDIF
		DRAW_WEAPONSHOP_CORONA x y z alien_corona_size CORONATYPE_MOON FLARETYPE_HEADLIGHTS 0 255 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer x y z 1.5 1.5 1.5 0
			DELETE_OBJECT alien_crate[m]
			clear_mission_audio 3
			PRINT_NOW DE10_04 5000 1//You have the alien artifact. Take it to The Truth.
			REMOVE_BLIP	alien_crate_blip[m]
			ADD_BLIP_FOR_COORD 408.7383 2515.8379 15.4918 alien_crate_blip[m]
			++ alien_crate_flag[m]
			++ mission_flag
		ENDIF
	ENDIF
	IF alien_crate_flag[m] = 5
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 408.7383 2515.8379 15.4918 1.5 1.5 1.5	TRUE
			
			DO_FADE 1000 FADE_out
			
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			clear_char_tasks_immediately scplayer
			LOAD_CUTSCENE DES_10b
			 
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			 
			clear_area 398.7038 2529.7346 16.5630 150.0 0
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
				WAIT 0
			ENDWHILE
			 
			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 0 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_CUTSCENE

			CLEAR_AREA 402.8737 2531.5820 15.5558 0.5 0
			LOAD_SCENE 402.8737 2531.5820 15.5558
			SET_CHAR_COORDINATES scplayer 402.8737 2531.5820 15.5558
			SET_CHAR_HEADING scplayer 224.0
			SET_CAMERA_BEHIND_PLAYER
			//DO_FADE 1500 FADE_IN
			SET_PLAYER_CONTROL player1 ON
			GOTO mission_des10_passed
		ENDIF
	ENDIF
	++ m
ENDWHILE

if mission_flag = 0
	if not is_car_dead heist_train[0]
		if mission_timer < game_timer
			if not locate_char_any_means_car_2d scplayer heist_train[0] 300.0 300.0 0
				print_now DE10_05 5000 1//You failed to get the jar of green goo in time.
				goto mission_des10_failed
			endif
		endif
	endif
endif

GOTO mission_des10_loop


	
// ************************************ MISSION FAILED *************************************
mission_des10_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// ************************************ MISSION PASSED *************************************
mission_des10_passed:

VAR_INT pilot_revenue pilot_cash_pickup // put this in initial
pilot_revenue = 10000 // put this in initial

lvar_int jetpack_pickup
CREATE_PICKUP jetpack pickup_on_street_slow 431.0450 2537.7798 16.2463 jetpack_pickup

CREATE_PROTECTION_PICKUP 420.5204 2532.6865 16.0869 pilot_revenue pilot_revenue pilot_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

SET_PLAYER_CONTROL player1 OFF
wait 0
SWITCH_WIDESCREEN ON

//DO_FADE 500 FADE_OUT
//
//WHILE GET_FADING_STATUS
//	WAIT 0
//ENDWHILE

LOAD_SCENE 407.8369 2515.6504 18.9138
SET_FIXED_CAMERA_POSITION 407.8369 2515.6504 18.9138 0.0 0.0 0.0 //cut scene of building
POINT_CAMERA_AT_POINT 408.3296 2516.5200 18.9449 JUMP_CUT
DO_FADE 500 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

PLAY_MISSION_PASSED_TUNE 1
PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

WAIT 5000
SET_FIXED_CAMERA_POSITION 419.7284 2526.5459 16.8000 0.0 0.0 0.0 //cut scene showing pickup
POINT_CAMERA_AT_POINT 419.8970 2527.5308 16.8356 JUMP_CUT
PRINT_WITH_NUMBER_NOW ASS_LUV pilot_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

WAIT 6000
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

DO_FADE 500 FADE_IN

SET_INT_STAT PASSED_DESERT10 1

restore_camera_jumpcut

flag_desert_mission_counter ++
PRINT_WITH_NUMBER_BIG M_PASS 20000 5000 1
ADD_SCORE player1 20000
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED DESER10
PLAYER_MADE_PROGRESS 1
REMOVE_BLIP desert2_contact_blip
RETURN
		


// *********************************** MISSION CLEANUP *************************************
mission_des10_cleanup:

IF destroyed_alien_crates = created_alien_crates
	clear_mission_audio 3
endif

REMOVE_BLIP alien_crate_blip[0]
REMOVE_BLIP alien_crate_blip[1]
REMOVE_BLIP alien_crate_blip[2]

MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED rhino
MARK_MODEL_AS_NO_LONGER_NEEDED army
MARK_MODEL_AS_NO_LONGER_NEEDED jetpack

SWITCH_RANDOM_TRAINS ON

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
		
}

