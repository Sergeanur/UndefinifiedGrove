MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 01/10/2003 	Time: 14:19:33	   Name:  Steve Taylor 						 ***
// ***																					 ***
// *** Mission: Syndicate 6 - Toreno's Heli 											 ***
// ***																					 ***
// *** Brief: Player and Cesar must get to airstrip in time to see Toreno's DC3 taking 	 ***
// *** off.  Once there Cesar drives whilst player must shoot out the plane from the 	 ***
// *** sunroof of the car DC3.  Car takes route with DC3 appearing/from behind forest, 	 ***
// *** crossing overhead. Car goes under bridges etc. Plane doesn't explode; rather it 	 ***
// *** produces smoke and banks into a dive. It crashes in the distance.                 ***                                                            ***
// ***																					 ***
// *****************************************************************************************


//chopper coords
//-1437.0984 -1527.9949 100.7504 93.8061 

SCRIPT_NAME syn6


GOSUB mission_syn6_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_syn6_failed
ENDIF

GOSUB mission_syn6_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_syn6_start:



flag_player_on_mission = 1

tlf_underway = 1

REGISTER_MISSION_GIVEN
SET_PLAYER_CONTROL player1 OFF






LOAD_SCENE -2034.2550 176.5000 27.8359


DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
      WAIT 0
ENDWHILE 







SET_AREA_VISIBLE 0



LOAD_MISSION_TEXT SYN6


LVAR_INT mission_blip mission_timer  players_car temp_int temp_int2 c r
//LVAR_FLOAT x2 y2 z2 x3 y3 z3 speed temp_float
//LOAD_MISSION_TEXT syn6

//DO_FADE 1500 FADE_IN


REQUEST_MODEL MAVERICK
REQUEST_MODEL FCR900
REQUEST_MODEL AK47
REQUEST_MODEL colt45
REQUEST_MODEL WBDYG1
REQUEST_MODEL WBDYG2
REQUEST_MODEL ROCKETLA
REQUEST_MODEL PONY
REQUEST_MODEL CELLPHONE
REQUEST_MODEL desert_eagle

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED MAVERICK
OR NOT HAS_MODEL_LOADED FCR900
OR NOT HAS_MODEL_LOADED AK47
OR NOT HAS_MODEL_LOADED colt45
OR NOT HAS_MODEL_LOADED WBDYG1
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED ROCKETLA
OR NOT HAS_MODEL_LOADED WBDYG2
OR NOT HAS_MODEL_LOADED PONY
OR NOT HAS_MODEL_LOADED CELLPHONE
OR NOT HAS_MODEL_LOADED desert_eagle
	WAIT 0
ENDWHILE


//CLEAR_AREA -2623.2590 1404.0876 6.1109 0.5 0
//LOAD_SCENE -2623.2590 1404.0876 6.1109

//SET_CHAR_COORDINATES scplayer -2034.1271 175.6148 27.8359
//SET_CHAR_HEADING scplayer 331.5565

//LOAD_SCENE -2034.2550 176.5000 27.8359


//DO_FADE 1000 FADE_IN 
//WHILE GET_FADING_STATUS
  //	WAIT 0
//ENDWHILE

//SET_CAMERA_BEHIND_PLAYER
//RESTORE_CAMERA_JUMPCUT
//SET_PLAYER_CONTROL player1 ON



WHILE NOT IS_PLAYER_PLAYING player1
	WAIT 0
ENDWHILE

//PRINT_NOW MWUZ01A 4000 1
//PRINT_NOW SYN6_01 10000 1//Go and kill Toreno.
//ADD_BLIP_FOR_COORD -1680.3770 705.4620 30.2209 mission_blip

LVAR_INT enemie[6]

LVAR_INT padgoon[6]
CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1691.7203 703.6754 29.6065 padgoon[0]
CREATE_CHAR PEDTYPE_CIVMALE WBDYG2 -1701.0607 712.7670 23.8976 padgoon[1]
CREATE_CHAR PEDTYPE_CIVMALE WBDYG2 -1688.3717 687.4631 20.6150 padgoon[2]
//CREATE_CHAR PEDTYPE_CIVMALE WBDYG2 -1689.1284 695.7116 29.6065 padgoon[3]
CREATE_CHAR PEDTYPE_CIVMALE WBDYG2 -1686.1497 701.9777 29.6065 padgoon[3]


CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1681.6963 680.8264 18.8009 padgoon[4]
CREATE_CHAR PEDTYPE_CIVMALE WBDYG2 -1690.1498 716.6064 29.6065 padgoon[5]

SET_CHAR_HEADING padgoon[0] 82.1070 
SET_CHAR_HEADING padgoon[1] 224.7688
SET_CHAR_HEADING padgoon[2] 118.1096
TASK_KINDA_STAY_IN_SAME_PLACE padgoon[2] TRUE
SET_CHAR_HEADING padgoon[3] 239.6//279.9582 
TASK_STAY_IN_SAME_PLACE padgoon[3] TRUE
TASK_TOGGLE_DUCK padgoon[3] TRUE

SET_CHAR_HEADING padgoon[4] 86.9582 
TASK_KINDA_STAY_IN_SAME_PLACE padgoon[4] TRUE

SET_CHAR_HEADING padgoon[5] 222.9582 

a = 0
WHILE a < 5
	GIVE_WEAPON_TO_CHAR	padgoon[a] WEAPONTYPE_PISTOL 9999
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER padgoon[a] TRUE
	SET_CHAR_RELATIONSHIP padgoon[a] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
	SET_CHAR_IS_TARGET_PRIORITY padgoon[a] TRUE
	++ a
ENDWHILE

	GIVE_WEAPON_TO_CHAR	padgoon[5] WEAPONTYPE_ROCKETLAUNCHER 9999
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER padgoon[5] TRUE
	SET_CHAR_RELATIONSHIP padgoon[5] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
	SET_CHAR_IS_TARGET_PRIORITY padgoon[5] TRUE
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD padgoon[5] FALSE
	TASK_STAY_IN_SAME_PLACE	padgoon[5] TRUE
	SET_CHAR_ACCURACY padgoon[5] 100
	SET_CHAR_SHOOT_RATE padgoon[5] 50
	SET_CHAR_WEAPON_SKILL padgoon[5] WEAPONSKILL_POOR

CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 60 -1688.8604 695.3077 30.3452 rocket_pickup //rocketX rocketY rocketZ rocket_pickup
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ONCE 120 -1683.4968 716.2739 30.6452 ak_pickup


LVAR_INT syn6_dummychopper syn6_helicrate syn6_crate1 syn6_crate2

CREATE_CAR MAVERICK -1680.5024 705.6951 29.6065 syn6_dummychopper
SET_HELI_BLADES_FULL_SPEED syn6_dummychopper
SET_CAR_HEADING syn6_dummychopper 90.0
OPEN_CAR_DOOR syn6_dummychopper REAR_RIGHT_DOOR
OPEN_CAR_DOOR syn6_dummychopper REAR_LEFT_DOOR // Testing!
SET_CAR_PROOFS syn6_dummychopper TRUE TRUE TRUE TRUE TRUE
CHANGE_CAR_COLOUR syn6_dummychopper 10 125


 
//SET_OBJECT_DYNAMIC syn6_crate2 FALSE


//	SET_OBJECT_ROTATION he1_hintbox2 0.0 0.0 229.0
//
//	CREATE_OBJECT cardboardbox  379.4728 164.5040 1019.6091 he1_hintbox3
//	SET_OBJECT_ROTATION he1_hintbox3 0.0 0.0 123.762






LVAR_INT enemie_bike
CREATE_CAR FCR900 -1705.1984 682.3814 23.8906 enemie_bike
MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
SET_CAR_HEADING enemie_bike 181.5462
SET_CAN_BURST_CAR_TYRES enemie_bike FALSE

LVAR_INT toreno_van	toreno_vandriver
CREATE_CAR PONY -1665.8002 682.1571 13.8891 toreno_van
SET_CAR_HEADING toreno_van 214.5462
CHANGE_CAR_COLOUR toreno_van 7 1

OPEN_CAR_DOOR toreno_van REAR_LEFT_DOOR

//CREATE_CHAR_INSIDE_CAR toreno_van PEDTYPE_CIVMALE WBDYG1 toreno_vandriver
//LOCK_CAR_DOORS toreno_van CARLOCK_LOCKED
MARK_MODEL_AS_NO_LONGER_NEEDED PONY
//TASK_DRIVE_BY sweet sw7_kane -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 90

												    


LVAR_INT mission_flag
mission_flag = -1

LVAR_INT created_goons_at_farm rocket_blip rocket_pickup ak_pickup spawn_rocketflag
LVAR_FLOAT rocketx rockety rocketz
LVAR_FLOAT playerx playery playerz


created_goons_at_farm = 0
spawn_rocketflag = 0


LVAR_INT kill_now_flag rocket_fired
kill_now_flag = 0
rocket_fired = 0

LVAR_INT v m e a
v = 0
m = 0
e = 0
a = 0

LVAR_INT syn6_ctskip_needed
syn6_ctskip_needed = 0

LVAR_INT speed_selected
speed_selected = 0

LVAR_INT pauseswap
pauseswap = 0

LVAR_INT warningswap
warningswap = 0

LVAR_INT fix_pb
fix_pb = 0

LVAR_INT syn6_timer
syn6_timer = 0

LVAR_INT syn6_phonecut
syn6_phonecut = 0

LVAR_INT syn6_phoneskip
syn6_phoneskip = 0

LVAR_INT syn6_phonecutskip
syn6_phonecutskip = 0


LVAR_INT task0_status task1_status


LVAR_INT syn6_camerashake
syn6_camerashake = 0

LVAR_INT syn6_anycar syn6_isincar syn6_weird

syn6_weird = 0
syn6_isincar = 0


LVAR_INT shooterdead[3]

shooterdead[0] = 0
shooterdead[1] = 0

LVAR_FLOAT syn6_explosionx syn6_explosiony syn6_explosionz 





//Dialogue and audio variables

LVAR_INT syn6_dialogue syn6_audio_char
LVAR_INT syn6_text_timer_diff syn6_text_timer_end syn6_text_timer_start
LVAR_TEXT_LABEL syn6_text[12]
LVAR_INT syn6_audio[12] syn6_audio_slot syn6_alt_slot syn6_counter syn6_ahead_counter syn6_audio_playing syn6_audio_underway
LVAR_INT syn6_convo_underway syn6_convo_counter syn6_random //syn6_leftcar_counter syn6_backcar_counter syn6_ganghire_counter 
//LVAR_INT syn6_driveby_counter syn6_cut_counter


// ---- Dialogue Flags
	syn6_audio_slot = 1
	syn6_alt_slot = 2
	syn6_counter = 0
	syn6_ahead_counter = 1
	syn6_audio_playing = 0
	syn6_audio_underway = 0



//Dialogue text



 $syn6_text[1] = MWUZ01A//Carl
 $syn6_text[2] = MWUZ01B//Hey man. What's going on?
 $syn6_text[3] = MWUZ01C//My man found that van you were looking for, by the helipad downtown.
 $syn6_text[4] = MWUZ01D//And Toreno?
 $syn6_text[5] = MWUZ01E//Yeah, he's there, 
 $syn6_text[6] = MWUZ01F//looks like he's about to take some merchandise and dip out by helicopter.
 $syn6_text[7] = MWUZ01G// They've already started loading boxes.
 $syn6_text[8] = MWUZ01H//Something about Toreno doesn't add up. 
 $syn6_text[9] = MWUZ01J//I'm going to go and check out this helipad. 
 $syn6_text[10] = MWUZ01K//Call me if you hear anything.



//Dialogue audio



 $syn6_audio[1] = SOUND_MWUZ01A//Carl
 $syn6_audio[2] = SOUND_MWUZ01B//Hey man. What's going on?
 $syn6_audio[3] = SOUND_MWUZ01C//My man found that van you were looking for, by the helipad downtown.
 $syn6_audio[4] = SOUND_MWUZ01D//And Toreno?
 $syn6_audio[5] = SOUND_MWUZ01E//Yeah, he's there, 
 $syn6_audio[6] = SOUND_MWUZ01F//looks like he's about to take some merchandise and dip out by helicopter.
 $syn6_audio[7] = SOUND_MWUZ01G// They've already started loading boxes.
 $syn6_audio[8] = SOUND_MWUZ01H//Something about Toreno doesn't add up. 
 $syn6_audio[9] = SOUND_MWUZ01J//I'm going to go and check out this helipad. 
 $syn6_audio[10] = SOUND_MWUZ01K//Call me if you hear anything.













LOAD_MISSION_AUDIO 3 SOUND_PED_MOBRING








timera = 0


SWITCH_WIDESCREEN ON

SET_CHAR_COORDINATES scplayer -2030.2500 148.8279 27.8359 
SET_CHAR_HEADING scplayer 350.0

SET_FIXED_CAMERA_POSITION -2024.2136 147.8178 28.9808 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -2025.1592 148.1366 29.0460 JUMP_CUT
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE


LOAD_SCENE_IN_DIRECTION -2024.1370 149.9411 27.8359 348.3137 
WAIT 0




DO_FADE 2500 FADE_IN
LOAD_SCENE_IN_DIRECTION -2024.1370 149.9411 27.8359 348.3137
WAIT 0


TASK_GO_STRAIGHT_TO_COORD scplayer -2026.6746 148.8807 27.8359 PEDMOVE_WALK -2 

WHILE GET_FADING_STATUS
      WAIT 0
ENDWHILE 
 


IF HAS_MISSION_AUDIO_LOADED 3

	//write_debug pish
	PLAY_MISSION_AUDIO 3

ENDIF


//LOAD_SCENE_IN_DIRECTION -2024.1370 149.9411 27.8359 348.3137
WAIT 0

//TASK_USE_MOBILE_PHONE scplayer TRUE

timera = 0


//PRINT_NOW MWUZ01A 3000 1

syn6_phonecut = 4
syn6_phoneskip = 0
syn6_phonecutskip = 1



// ************************************* MISSION LOOP **************************************
mission_syn6_loop:

WAIT 0





// ---- Load & Play Dialogue...
	IF NOT syn6_counter = 0
		IF syn6_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED syn6_alt_slot
				CLEAR_MISSION_AUDIO syn6_alt_slot
			ENDIF
			syn6_audio_playing = 1
			syn6_audio_underway = 1
		ENDIF

		IF syn6_audio_playing = 1
			LOAD_MISSION_AUDIO syn6_audio_slot syn6_audio[syn6_counter]
			//GOSUB syn6_dialogue_pos
			//ATTACH_MISSION_AUDIO_TO_PED syn6_audio_slot syn6_audio_char
			syn6_audio_playing = 2
		ENDIF

		IF syn6_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED syn6_audio_slot
				PLAY_MISSION_AUDIO syn6_audio_slot
				PRINT_NOW $syn6_text[syn6_counter] 10000 1
				syn6_audio_playing = 3
			ENDIF
		ENDIF

		IF syn6_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED syn6_audio_slot
				CLEAR_THIS_PRINT $syn6_text[syn6_counter]
				IF syn6_audio_slot = 1
					syn6_audio_slot = 2
					syn6_alt_slot = 1
				ELSE
					syn6_audio_slot = 1
					syn6_alt_slot = 2
				ENDIF
				syn6_counter = 0
				syn6_audio_playing = 0
			
				syn6_audio_underway = 0 // okay to cue up next piece of convo text

			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED syn6_alt_slot
					IF syn6_counter < 10
						syn6_ahead_counter = syn6_counter + 1
						LOAD_MISSION_AUDIO syn6_alt_slot syn6_audio[syn6_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// End of dialogue loader / player



IF syn6_camerashake = 1

	SHAKE_CAM 20
   //	SHAKE_PAD PAD1 1000 256
	
ENDIF


IF NOT syn6_phonecut = -13

	
	IF syn6_phonecut > 4
		IF syn6_phoneskip = 1
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			WHILE IS_BUTTON_PRESSED PAD1 TRIANGLE
				WAIT 0
			ENDWHILE
			
				syn6_phonecut = 100

			ENDIF
		ENDIF
		IF syn6_phonecutskip = 1
			IF IS_BUTTON_PRESSED PAD1 CROSS
			WHILE IS_BUTTON_PRESSED PAD1 CROSS
				WAIT 0	
			ENDWHILE		

				syn6_phonecut = 10

			ENDIF
		ENDIF
	ENDIF

	IF syn6_phonecut = 4

		IF timera > 1000
			CLEAR_CHAR_TASKS scplayer
			TASK_USE_MOBILE_PHONE scplayer TRUE
			syn6_phonecut = 5
 		ENDIF

		

	ENDIF


	//IF syn6_audio_underway = 0
	IF syn6_phonecut = 5
	   //	CLEAR_MISSION_AUDIO 3

		IF timera > 5000 
			//PRINT_NOW MWUZ01A 2500 1 

		   //	LOAD_SCENE_IN_DIRECTION -2024.1370 149.9411 27.8359 348.3137 
			
			syn6_counter = 1
			timera = 0
			syn6_phonecut = 7
		ENDIF
		
	ENDIF


	IF syn6_phonecut = 7
		IF timera > 2500
	   
			syn6_phonecut = 10
			timera = 0
		ENDIF
	ENDIF

	IF syn6_phonecut = 10

		//IF syn6_audio_underway = 0
		   //	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

			syn6_phonecutskip = 0
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			//syn6_counter = 2

			syn6_phonecut = 15

			
			//CLEAR_PRINTS
			//PRINT_NOW MWUZ01B 2000 1
			syn6_phoneskip = 1

			timera = 0
		//ENDIF
	ENDIF


	IF syn6_phonecut  > 10
		IF syn6_audio_underway = 0

			SWITCH syn6_phonecut

				CASE 15
					syn6_counter = 2
					syn6_phonecut = 20
					BREAK
				CASE 20
					syn6_counter = 3
					syn6_phonecut =  30
					BREAK
				CASE 30
					syn6_counter = 4
					syn6_phonecut = 40
					BREAK
				CASE 40
					syn6_counter = 5
					syn6_phonecut = 50
					BREAK
				CASE 50
					syn6_counter = 6
					syn6_phonecut = 60
					BREAK
				CASE 60
					syn6_counter = 7
					syn6_phonecut = 70
					BREAK
				CASE 70
					syn6_counter = 8
					//	syn6_phonecut = 80
					syn6_phonecut = 90
					BREAK
				CASE 80
					syn6_counter = 9
					syn6_phonecut = 90
					BREAK
				CASE 90
					syn6_counter = 10
					syn6_phonecut = 100
					BREAK

				CASE 100
					syn6_counter = 0
				   //	CLEAR_CHAR_TASKS scplayer

					TASK_USE_MOBILE_PHONE scplayer FALSE
					MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE


					PRINT_NOW SYN6_01 6000 1
					syn6_phonecut = 110 // finish convo
					
					BREAK


			ENDSWITCH
		   //	syn6_audio_underway = 1
		ENDIF
	ENDIF





//	IF syn6_phonecut = 20
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01C 2700 1
//			syn6_phonecut = 30
//			timera = 0
//		ENDIF
//	ENDIF
//
//
//	IF syn6_phonecut = 30
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01D 2700 1
//			syn6_phonecut = 40
//			timera = 0
//		ENDIF
//	ENDIF
//
//
//	IF syn6_phonecut = 40
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01E 2700 1
//			syn6_phonecut = 50
//			timera = 0
//		ENDIF
//	ENDIF
//
//	IF syn6_phonecut = 50
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01F 3000 1
//			syn6_phonecut = 60
//			timera = 0
//		ENDIF
//	ENDIF
//
//	IF syn6_phonecut = 60
//		IF timera > 3700
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01G 2700 1
//			syn6_phonecut = 70
//			timera = 0
//		ENDIF
//	ENDIF
//
//
//	IF syn6_phonecut = 70
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01H 2700 1
//			syn6_phonecut = 80
//			timera = 0
//		ENDIF
//	ENDIF
//
//
//	IF syn6_phonecut = 80
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01J 2700 1
//			syn6_phonecut = 90
//			timera = 0
//		ENDIF
//	ENDIF
//
//
//	IF syn6_phonecut = 90
//		IF timera > 3500
//			CLEAR_PRINTS 
//			PRINT_NOW MWUZ01K 2700 1
//			syn6_phonecut = 100
//			timera = 0
//		ENDIF
//	ENDIF
//
//	IF syn6_phonecut = 100
//		IF timera > 3000
//			syn6_phonecut = 110
//		ENDIF
//		
//	ENDIF
//


ENDIF // syn6_phonecut = -13


//
//
//
//IF syn6_phonecut = 1
//   SWITCH_WIDESCREEN ON
//   SET_PLAYER_CONTROL player1 OFF
//ENDIF



IF syn6_phonecut = 110

	//CLEAR_PRINTS
	
	CLEAR_CHAR_TASKS scplayer
  

	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE


  
   //	PRINT_NOW SYN6_01 10000 1//Go and kill Toreno.
	ADD_BLIP_FOR_COORD -1680.3770 705.4620 30.2209 mission_blip
	syn6_phonecut = -13
	syn6_phoneskip = 0
	

ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
            GOTO mission_syn6_passed  
ENDIF
IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_p
	   ALTER_WANTED_LEVEL Player1 2
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_o
	    ALTER_WANTED_LEVEL Player1 0
ENDIF


GET_GAME_TIMER game_timer

IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_syn6_failed
ENDIF



IF syn6_ctskip_needed = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
		mission_flag = 6
		syn6_ctskip_needed = 0 // Don't want to read PS2 joypad anymore
	ENDIF
ENDIF

IF mission_flag = -99
	CREATE_CAR MAVERICK -1680.5024 705.6951 29.6065 toreno_plane

ENDIF



IF mission_flag = -1
	IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
		CLEAR_AREA -1704.3240 637.5129 23.8959 0.5 0
		LOAD_SCENE -1704.3240 637.5129 23.8959
		SET_CHAR_COORDINATES scplayer -1704.3240 637.5129 23.8959 
		SET_CHAR_HEADING scplayer 0.0
		SET_CAMERA_BEHIND_PLAYER
	ENDIF
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1699.9908 686.4141 20.0 60.0 60.0 80.0 0
		syn6_phonecut = -13
		CLEAR_CHAR_TASKS scplayer
	   //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		
			REMOVE_BLIP mission_blip
			//ADD_BLIP_FOR_CHAR padgoon[5] rocket_blip
			//CHANGE_BLIP_COLOUR rocket_blip GREEN 
			//CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 60 -1689.6283 691.6729 30.3452 rocket_pickup //rocketX rocketY rocketZ rocket_pickup
		   	ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
		   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			kill_now_flag = 1

 		++ mission_flag
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST padgoon[0]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[0] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		   //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			 kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST padgoon[1]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[1] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		   //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			 kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST padgoon[2]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[2] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		   //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST	padgoon[3]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[3] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		   //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			 kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST	padgoon[4]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[4] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		  //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF

	IF mission_flag = -1
	IF DOES_CHAR_EXIST padgoon[5]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR padgoon[5] scplayer
			syn6_phonecut = -13
			CLEAR_CHAR_TASKS scplayer
		  //	PRINT_NOW SYN6_02 10000 1//That is Toreno's men up ahead, kill them.
		  		REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
			   	CHANGE_BLIP_COLOUR rocket_blip GREEN

			kill_now_flag = 1

	 		++ mission_flag
		ENDIF
	ENDIF
	ENDIF



ENDIF

/*
IF mission_flag < 1
	m = 0
	WHILE m < 5
		IF NOT IS_CHAR_DEAD	padgoon[m]
			IF HAS_CHAR_SPOTTED_CHAR padgoon[m] scplayer
			OR LOCATE_CHAR_ANY_MEANS_3D scplayer -1699.9908 686.4141 20.0 40.0 40.0 40.0 0
			OR IS_PLAYER_TARGETTING_CHAR player1 padgoon[m]
			OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON padgoon[m] WEAPONTYPE_ANYWEAPON
			OR kill_now_flag = 1
				GET_SCRIPT_TASK_STATUS padgoon[m] TASK_KILL_CHAR_ON_FOOT task_status
				IF task_status = FINISHED_TASK
					TASK_KILL_CHAR_ON_FOOT padgoon[m] scplayer
				ENDIF
				kill_now_flag = 1
			ENDIF
		ELSE
			kill_now_flag = 1
			MARK_CHAR_AS_NO_LONGER_NEEDED padgoon[m]
			//REMOVE_BLIP enemie_blip[m]
		ENDIF
		++ m

		

	ENDWHILE
ENDIF */




 

IF kill_now_flag = 1
	IF rocket_fired = 0
		IF NOT IS_CHAR_DEAD padgoon[5]
			CLEAR_CHAR_TASKS padgoon[5]

			GET_CHAR_COORDINATES scplayer playerx playery playerz
			playerx = playerx + 5.0
			playery = playery + 5.0
			playerz = playerz + 15.0
			CLEAR_CHAR_TASKS padgoon[5]
		   	TASK_SHOOT_AT_COORD padgoon[5] playerx playery playerz -1
			rocket_fired = 1
			timerb = 0
		   //	IF NOT IS_CHAR_DEAD toreno_vandriver
			 //	IF NOT IS_CAR_DEAD toreno_van
			   //		TASK_CAR_DRIVE_WANDER toreno_vandriver toreno_van 30.0	DRIVINGMODE_AVOIDCARS
			   //	ENDIF
			//ENDIF
		   //	WRITE_DEBUG rocketsegment
		   //   WRITE_DEBUG_WITH_INT rf rocket_fired
	  	ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD padgoon[0]
		CLEAR_CHAR_TASKS padgoon[0]
		TASK_KILL_CHAR_ON_FOOT padgoon[0] scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD padgoon[1]
		CLEAR_CHAR_TASKS padgoon[1]
		TASK_KILL_CHAR_ON_FOOT padgoon[1] scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD padgoon[2]
		CLEAR_CHAR_TASKS padgoon[2]
		TASK_KILL_CHAR_ON_FOOT padgoon[2] scplayer
	ENDIF

//	IF NOT IS_CHAR_DEAD padgoon[3]
//		CLEAR_CHAR_TASKS padgoon[3]
//		TASK_KILL_CHAR_ON_FOOT padgoon[3] scplayer
//	ENDIF

	IF NOT IS_CHAR_DEAD padgoon[4]
		CLEAR_CHAR_TASKS padgoon[4]
		TASK_KILL_CHAR_ON_FOOT padgoon[4] scplayer
	ENDIF

	kill_now_flag = 2

ENDIF


IF rocket_fired = 1
	IF timerb > 4000
		IF NOT IS_CHAR_DEAD padgoon[5]
			CLEAR_CHAR_TASKS padgoon[5]
			//rocket_fired = 2
			rocket_fired = 99
			timerb = 0
			GIVE_WEAPON_TO_CHAR	padgoon[5] WEAPONTYPE_PISTOL 9999
			
		ENDIF
	ENDIF
ENDIF

IF rocket_fired = 2
	IF timerb > 6000
		rocket_fired = 0
	ENDIF
ENDIF


IF spawn_rocketflag = 0
		// Keep in, in case it has to change back.
			
		//IF IS_CHAR_DEAD padgoon[3]
			//REMOVE_BLIP rocket_blip
			//GET_DEAD_CHAR_PICKUP_COORDS padgoon[3] rocketX rocketY rocketZ
			
			//CREATE_PICKUP_WITH_AMMO ROCKETLA PICKUP_ONCE 60 -1689.6283 691.6729 30.3452 rocket_pickup //rocketX rocketY rocketZ rocket_pickup
			//IF IS_ANY_PICKUP_AT_COORDS rocketX rocketY rocketZ
			   //	ADD_BLIP_FOR_PICKUP rocket_pickup rocket_blip
		   	   //	CHANGE_BLIP_COLOUR rocket_blip GREEN
			//ENDIF
			spawn_rocketflag = 1
	   //ENDIF
ENDIF


IF spawn_rocketflag < 2
	IF mission_flag > 4
		IF pauseswap = 0
			IF NOT IS_CAR_DEAD toreno_plane
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 250.0 250.0 100.0 0
				   PAUSE_PLAYBACK_RECORDED_CAR toreno_plane
				   pauseswap = 1
		 		ENDIF
			ENDIF
		ENDIF
		IF pauseswap = 1
			IF NOT IS_CAR_DEAD toreno_plane
				IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 250.0 250.0 100.0 0
				   UNPAUSE_PLAYBACK_RECORDED_CAR toreno_plane
				   pauseswap = 0
		 		ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


	


IF spawn_rocketflag = 1
	IF HAS_PICKUP_BEEN_COLLECTED rocket_pickup
		//IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_ROCKETLAUNCHER
			//WRITE_DEBUG bliptest
			CLEAR_PRINTS
			//PRINT_NOW SYN6_11 6000 1

			PRINT_NOW SYN6_20 6000 1
			REMOVE_BLIP rocket_blip	
			IF NOT IS_CAR_DEAD toreno_plane
			ADD_BLIP_FOR_CAR toreno_plane mission_blip
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING health_counter COUNTER_DISPLAY_BAR SYN6_05
			UNPAUSE_PLAYBACK_RECORDED_CAR toreno_plane
			ENDIF
			spawn_rocketflag = 2
			//PRINT SYN6_11 6000 1

		//ENDIF
	ENDIF


	//IF syn6_weird = 1
	//	IF syn6_isincar = 1	
	// May have check here!


ENDIF


IF spawn_rocketflag = 2

ENDIF

IF mission_flag = 0
//	IF mission_flag = 0
//	IF IS_CHAR_DEAD	padgoon[0]
//		IF IS_CHAR_DEAD	padgoon[1]
//			IF IS_CHAR_DEAD	padgoon[2]
//				IF IS_CHAR_DEAD	padgoon[3]
//					SET_FADING_COLOUR 1 0 0
//					//DO_FADE 500 FADE_OUT
//					++ mission_flag
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF
//	ENDIF
	IF mission_flag = 0
	IF IS_CHAR_IN_ANGLED_AREA_3D scplayer -1695.6678 693.7705 29.5938 -1665.8649 693.7705 59.5943 28.0 FALSE
		 IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer syn6_anycar
			syn6_isincar = 1

	  	 //SET_PLAYER_CONTROL player1 OFF
	 	 //SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		 //SET_FADING_COLOUR 1 0 0
		// DO_FADE 500 FADE_OUT
		 ENDIF
		 syn6_weird = 1
		 ++ mission_flag
		 
	ENDIF
	ENDIF
	




	IF mission_flag = 0
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1683.8203 686.5125 23.9687 2.0 2.0 2.0 FALSE
	  	 //SET_PLAYER_CONTROL player1 OFF
	 	 //SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		 //SET_FADING_COLOUR 1 0 0
		// DO_FADE 500 FADE_OUT
		 ++ mission_flag
	ENDIF
	ENDIF
	

	// new bug fix
 	IF mission_flag = 0
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1683.7972 692.3915 27.5860 5.0 5.0 2.0 FALSE
	  	 //SET_PLAYER_CONTROL player1 OFF
	 	 //SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		 //SET_FADING_COLOUR 1 0 0
		// DO_FADE 500 FADE_OUT
		 ++ mission_flag
	ENDIF
	ENDIF


ENDIF

IF mission_flag > 1
	IF created_goons_at_farm = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1448.0062 -1533.0359 200.0 200.00	0
			//MARK_CAR_AS_NO_LONGER_NEEDED enemie_bike
			//MARK_CHAR_AS_NO_LONGER_NEEDED enemie[0]
			//MARK_CHAR_AS_NO_LONGER_NEEDED enemie[1]
			//MARK_CHAR_AS_NO_LONGER_NEEDED enemie[2]
			//MARK_CHAR_AS_NO_LONGER_NEEDED enemie[3]
			
		
			CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1450.2561 -1543.0123 100.7579 enemie[0]
			CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1422.8545 -1542.1029 100.7514 enemie[1]
			CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1431.0964 -1506.9623 100.7262 enemie[2]
			CREATE_CHAR PEDTYPE_CIVMALE WBDYG1 -1448.4773 -1511.9182 100.7579 enemie[3]
			//CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 -1444.2607 -1499.3761 100.7501 enemie[4]
			//CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 -1443.4293 -1501.7506 100.7501 enemie[5]

			SET_CHAR_HEADING enemie[0] 269.8948
			SET_CHAR_HEADING enemie[1] 26.8258 
			SET_CHAR_HEADING enemie[2] 223.7033
			SET_CHAR_HEADING enemie[3] 266.9233
		   //	SET_CHAR_HEADING enemie[4] 272.0418
		   //	SET_CHAR_HEADING enemie[5] 285.0045

			
			a = 0
			WHILE a < 4
				GIVE_WEAPON_TO_CHAR	enemie[a] WEAPONTYPE_PISTOL 9999
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER enemie[a] TRUE
				SET_CHAR_RELATIONSHIP enemie[a] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				SET_CHAR_IS_TARGET_PRIORITY enemie[a] TRUE
				++ a
			ENDWHILE
			

			//LVAR_INT vehicle
			//CREATE_CAR colt45 -1439.0107 -1498.6058 100.7445 vehicle
			//SET_CAR_HEADING vehicle 63.3532
		   //	WRITE_DEBUG created
			++ created_goons_at_farm
		ENDIF



		//Collision shit in here!
	ENDIF

ENDIF

IF mission_flag = 1
	IF HAS_CAR_RECORDING_BEEN_LOADED 110
		IF NOT GET_FADING_STATUS
			CLEAR_THIS_PRINT SYN6_02
			DELETE_CAR syn6_dummychopper
			LVAR_INT toreno_plane
			CREATE_CAR MAVERICK -1680.5024 705.6951 29.6065 toreno_plane
			SET_HELI_BLADES_FULL_SPEED toreno_plane
			CHANGE_CAR_COLOUR toreno_plane 10 125


			CREATE_OBJECT cardboardbox2 -1680.9305 705.7776 30.6065 syn6_crate2
			SET_OBJECT_COLLISION syn6_crate2 FALSE
			FREEZE_OBJECT_POSITION syn6_crate2 TRUE 
			SET_OBJECT_COLLISION syn6_crate2 FALSE




			SET_CAR_HEADING toreno_plane 90.0
			LOCK_CAR_DOORS toreno_plane CARLOCK_LOCKOUT_PLAYER_ONLY
			SET_VEHICLE_CAN_BE_TARGETTED toreno_plane TRUE
			REMOVE_BLIP mission_blip
			//ADD_BLIP_FOR_CAR toreno_plane mission_blip
			SET_CAR_ONLY_DAMAGED_BY_PLAYER toreno_plane TRUE
			SET_CAR_HEALTH toreno_plane 1500
			LVAR_INT maverick_pilot
			CREATE_CHAR_INSIDE_CAR toreno_plane PEDTYPE_CIVMALE WBDYG1 maverick_pilot
			SET_CHAR_IS_TARGET_PRIORITY maverick_pilot TRUE

			LVAR_INT shooter[2]
			
		   			
		
			CREATE_CHAR_AS_PASSENGER toreno_plane PEDTYPE_CIVMALE WBDYG1 1 shooter[0]

			
			
			CREATE_CHAR_AS_PASSENGER toreno_plane PEDTYPE_CIVMALE WBDYG1 2 shooter[1]

		   	OPEN_CAR_DOOR toreno_plane REAR_RIGHT_DOOR

		   	OPEN_CAR_DOOR toreno_plane REAR_LEFT_DOOR // Testing!

			//OPEN_CAR_DOOR toreno_plane FRONT_LEFT_DOOR

			//syn6_ctskip_needed = 1 // so we can skip cutscene




			c = 0 // was 0! Testing driveby
			WHILE c < 2
				SET_CHAR_STAY_IN_SAME_PLACE	shooter[c] TRUE
				GIVE_WEAPON_TO_CHAR	shooter[1] WEAPONTYPE_AK47 9999
				GIVE_WEAPON_TO_CHAR shooter[0] WEAPONTYPE_desert_eagle 9999
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER	shooter[c] TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS shooter[c] FALSE
				SET_CHAR_HEALTH shooter[c] 300
				SET_CHAR_IS_TARGET_PRIORITY shooter[c] TRUE
				set_char_health	shooter[c] 500

			 ++ c
			ENDWHILE	



				// Moved segment
				IF IS_CHAR_IN_CAR shooter[0] toreno_plane
					GET_CAR_COORDINATES toreno_plane x y z
					WARP_CHAR_FROM_CAR_TO_COORD shooter[0] x y z
				   	DETACH_CHAR_FROM_CAR shooter[0]
					SET_CHAR_ALLOWED_TO_DUCK shooter[0] TRUE
				 
						
						ATTACH_CHAR_TO_CAR shooter[0] toreno_plane -1.0 -0.3 0.0 FACING_LEFT 180.0 WEAPONTYPE_AK47
						//TASK_TOGGLE_DUCK shooter[0] TRUE
						SET_CHAR_HEADING shooter[0] 200.0
					   //	TASK_TURN_CHAR_TO_FACE_CHAR shooter[0] scplayer
				ENDIF
				// Moved 

			
				IF IS_CHAR_IN_CAR shooter[1] toreno_plane
					GET_CAR_COORDINATES toreno_plane x y z
					WARP_CHAR_FROM_CAR_TO_COORD shooter[1] x y z
				   	DETACH_CHAR_FROM_CAR shooter[1]
					SET_CHAR_ALLOWED_TO_DUCK shooter[1] TRUE
				 
						
						ATTACH_CHAR_TO_CAR shooter[1] toreno_plane 1.2 -0.3 0.0 FACING_RIGHT 180.0 WEAPONTYPE_AK47
						TASK_TURN_CHAR_TO_FACE_CHAR shooter[1] scplayer
				ENDIF


			 			





			SET_PLAYER_CONTROL player1 OFF

//			DO_FADE 500 FADE_OUT
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE

		  //	MAKE_PLAYER_SAFE_FOR_CUTSCENE player1
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
		  //	SET_FIXED_CAMERA_POSITION -1667.2103 689.8309 30.5514 0.0 0.0 0.0	// Old one. Les approved.
		  //	POINT_CAMERA_AT_POINT -1668.0150 690.4207 30.6191 JUMP_CUT

			// New shit!
			SET_FIXED_CAMERA_POSITION -1675.1948 699.6955 36.8409 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1675.7728 700.2547 36.2466  JUMP_CUT
			syn6_camerashake = 1
			IF syn6_weird = 0
			SET_CHAR_COORDINATES scplayer -1683.8489 692.0139 27.5860
			SET_CHAR_HEADING scplayer 304.0
			ELSE 
				
				IF syn6_isincar = 1
					IF NOT IS_CAR_DEAD syn6_anycar
						SET_CAR_COORDINATES syn6_anycar	-1667.7699 728.9113 49.8973 
						SET_CAR_HEADING syn6_anycar 135.4521 

						FREEZE_CAR_POSITION syn6_anycar	TRUE
					ENDIF
				ELSE
					FREEZE_CHAR_POSITION scplayer TRUE
				ENDIF
				
			
			
			ENDIF

			

//			DO_FADE 500 FADE_IN
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE


			SET_FADING_COLOUR 1 0 0
			//DO_FADE 500 FADE_IN
			
		   	START_PLAYBACK_RECORDED_CAR	toreno_plane 110
			TASK_TOGGLE_DUCK shooter[0] TRUE	// testing!
		   	TASK_TOGGLE_DUCK shooter[1] TRUE
			
			IF NOT IS_CHAR_DEAD padgoon[3]
			   //	CLEAR_CHAR_TASKS padgoon[3]
				//TASK_COWER padgoon[3] 
				//TASK_DUCK padgoon[3] 10000
				//WRITE_DEBUG test
			   //	TASK_PLAY_ANIM padgoon[3] cower PED 8.0 TRUE FALSE FALSE TRUE -1
			ENDIF
			
				
			//START_PLAYBACK_RECORDED_CAR	toreno_plane 110

			SET_PLAYBACK_SPEED toreno_plane 3.0	// was 4.5

		 //	TASK_DRIVE_BY shooter[0] scplayer -1 0.0 0.0 0.0 80.0 DRIVEBY_FIXED_LHS FALSE 90


			++ mission_flag
			//syn6_ctskip_needed = 1 // so we can skip cutscene

		ENDIF
	ELSE
		REQUEST_CAR_RECORDING 110
	ENDIF
ENDIF

IF NOT IS_CAR_DEAD toreno_plane
	IF mission_flag = 2
		IF NOT GET_FADING_STATUS
			//PRINT_NOW SYN6_03 5000 1//They are getting away in a helicopter!
			++ mission_flag
			timerb = 0
			//syn6_ctskip_needed = 1
		ENDIF
	ENDIF

	IF mission_flag = 3
		++ mission_flag
	ENDIF

	IF mission_flag = 4
		IF timerb  > 1100


			// Taken out deliberately as it fucks up car recording!
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//
			//
			//syn6_ctskip_needed = 1
			//
			//
		   	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			IF NOT IS_CAR_DEAD toreno_plane 
				SET_PLAYBACK_SPEED toreno_plane 0.85
			ENDIF

			++ mission_flag

	   
		
		ENDIF
	ENDIF

	IF mission_flag = 5
		// new section
		IF timerb > 3000
			DELETE_OBJECT syn6_crate2

			syn6_camerashake = 0
			SET_FIXED_CAMERA_POSITION -1681.2646 685.2078 30.6465 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1681.2633 686.2019 30.5389 JUMP_CUT
			
		
			
			
		ENDIF
		// end of new section

		IF timerb > 6500 // was 3000
			++ mission_flag
		ENDIF

	ENDIF


	IF mission_flag = 6
		IF NOT GET_FADING_STATUS
			IF IS_CAR_DEAD enemie_bike
				MARK_CAR_AS_NO_LONGER_NEEDED enemie_bike
				CREATE_CAR FCR900 -1705.1984 682.3814 23.8906 enemie_bike
				SET_CAR_HEADING enemie_bike 181.5462
			ELSE
				IF NOT LOCATE_CAR_2D enemie_bike -1703.9486 690.2634 20.0 20.0 0
					MARK_CAR_AS_NO_LONGER_NEEDED enemie_bike
					CREATE_CAR FCR900 -1705.1984 682.3814 23.8906 enemie_bike
					SET_CAR_HEADING enemie_bike 181.5462
				ENDIF
			ENDIF

			syn6_ctskip_needed = 0 // so we can skip cutscene

			CLEAR_THIS_PRINT SYN6_03
			SET_FADING_COLOUR 1 0 0
	  		SET_EVERYONE_IGNORE_PLAYER player1 FALSE

			SWITCH_WIDESCREEN OFF

			PRINT_NOW SYN6_03 8000 1

			SET_ALL_CARS_CAN_BE_DAMAGED TRUE

			IF NOT IS_CAR_DEAD toreno_plane
				IF NOT IS_CHAR_DEAD shooter[0]
				ENDIF
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR toreno_plane
					START_PLAYBACK_RECORDED_CAR	toreno_plane 110
				ENDIF
			ENDIF

			//CLEAR_CUTSCENE
			DELETE_OBJECT syn6_crate2
			syn6_camerashake = 0
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON

			IF syn6_weird = 1
				IF syn6_isincar = 1
					IF NOT IS_CAR_DEAD syn6_anycar
						FREEZE_CAR_POSITION syn6_anycar FALSE
					ENDIF
				ELSE
					FREEZE_CHAR_POSITION scplayer FALSE
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD padgoon[5]
				CLEAR_CHAR_TASKS padgoon[5]
				TASK_KINDA_STAY_IN_SAME_PLACE	padgoon[5] TRUE
				TASK_KILL_CHAR_ON_FOOT padgoon[5] scplayer
			ENDIF	 

			IF NOT IS_CHAR_DEAD padgoon[3]
				CLEAR_CHAR_TASKS padgoon[3]
				TASK_TOGGLE_DUCK padgoon[3] FALSE

				TASK_KINDA_STAY_IN_SAME_PLACE padgoon[3] TRUE
				TASK_KILL_CHAR_ON_FOOT padgoon[3] scplayer
			ENDIF
			
			SET_PLAYBACK_SPEED toreno_plane 1.0//0.75
			
			IF syn6_weird = 1
				IF syn6_isincar = 1
					SET_PLAYBACK_SPEED toreno_plane 1.5
					IF NOT IS_CHAR_DEAD shooter[0]
						GIVE_WEAPON_TO_CHAR	shooter[0] WEAPONTYPE_ROCKETLAUNCHER 9999
					ENDIF
					SET_CAR_HEALTH toreno_plane 3000  //Dodgy!

				ENDIF
			ENDIF
			


			VAR_INT health_counter
			health_counter = 100
			//DISPLAY_ONSCREEN_COUNTER_WITH_STRING health_counter COUNTER_DISPLAY_BAR SYN6_05
			
			mission_timer = game_timer + 5000

			mission_flag = 8  // skip bike bit. It's dangerous!
		ENDIF
	ENDIF

	IF mission_flag = 9



			// need to put in check for helicopter here and detach guys.

			IF LOCATE_CAR_3D toreno_plane -1437.0984 -1527.9949 100.7504 10.0 10.0 4.0 FALSE
				STOP_PLAYBACK_RECORDED_CAR toreno_plane
			  	++ mission_flag
			 ENDIF

	ENDIF


	IF mission_flag = 10

		IF NOT IS_CAR_DEAD toreno_plane 
			IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 40.0 40.0 40.0 FALSE
		  		 PRINT_NOW SYN6_07 5000 1
				 ++ mission_flag
				 IF NOT IS_CHAR_DEAD shooter[0]
					TASK_TOGGLE_DUCK shooter[0] FALSE
				 	DETACH_CHAR_FROM_CAR shooter[0]
				 	CLEAR_CHAR_TASKS shooter[0]
				 	TASK_KILL_CHAR_ON_FOOT shooter[0] scplayer
				 ENDIF
				 IF NOT IS_CHAR_DEAD shooter[1]
					TASK_TOGGLE_DUCK shooter[1] FALSE

				 	CLEAR_CHAR_TASKS shooter[1]
				 	DETACH_CHAR_FROM_CAR shooter[1]
				 	TASK_KILL_CHAR_ON_FOOT shooter[1] scplayer
				 ENDIF

				 SET_CAR_CRUISE_SPEED toreno_plane 70.0 	
				 HELI_GOTO_COORDS toreno_plane -23.2666 1210.6686 100.3528 70.0 70.0
		  	ENDIF
		ENDIF

	ENDIF

	IF mission_flag = 10

		IF NOT IS_CAR_DEAD toreno_plane 
			IF HAS_CAR_BEEN_DAMAGED_BY_CHAR toreno_plane scplayer 
				PRINT_NOW SYN6_07 5000 1
				 ++ mission_flag
				 IF NOT IS_CHAR_DEAD shooter[0]
					TASK_TOGGLE_DUCK shooter[0] FALSE

				 	DETACH_CHAR_FROM_CAR shooter[0]
				 	CLEAR_CHAR_TASKS shooter[0]
				 	TASK_KILL_CHAR_ON_FOOT shooter[0] scplayer
				 ENDIF
				 IF NOT IS_CHAR_DEAD shooter[1]
					TASK_TOGGLE_DUCK shooter[1] FALSE

				 	CLEAR_CHAR_TASKS shooter[1]
				 	DETACH_CHAR_FROM_CAR shooter[1]
				 	TASK_KILL_CHAR_ON_FOOT shooter[1] scplayer
				 ENDIF

				 SET_CAR_CRUISE_SPEED toreno_plane 70.0 	
				 HELI_GOTO_COORDS toreno_plane -23.2666 1210.6686 100.3528 70.0 70.0
		  	ENDIF
		ENDIF

	ENDIF

	IF mission_flag = 11

		IF NOT IS_CHAR_DEAD enemie[0]
			CLEAR_CHAR_TASKS enemie[0]
			SET_CHAR_KEEP_TASK enemie[0] TRUE
			TASK_KILL_CHAR_ON_FOOT enemie[0] scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD enemie[1]
			CLEAR_CHAR_TASKS enemie[1]	 
			SET_CHAR_KEEP_TASK enemie[1] TRUE
			TASK_KILL_CHAR_ON_FOOT enemie[1] scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD enemie[2]
			CLEAR_CHAR_TASKS enemie[2]
			SET_CHAR_KEEP_TASK enemie[2] TRUE
			TASK_KILL_CHAR_ON_FOOT enemie[2] scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD enemie[3]
			CLEAR_CHAR_TASKS enemie[3]
			SET_CHAR_KEEP_TASK enemie[3] TRUE
			TASK_KILL_CHAR_ON_FOOT enemie[3] scplayer
		ENDIF

	
		// GOTO mission_syn6_failed
		++ mission_flag
	ENDIF







	//IF mission_flag = 7
	IF mission_flag = 8
//		MARK_CAR_AS_NO_LONGER_NEEDED enemie_bike
//		REMOVE_BLIP	bike_blip
		IF HAS_CAR_RECORDING_BEEN_LOADED 111
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	toreno_plane
//			IF NOT IS_RECORDING_GOING_ON_FOR_CAR toreno_plane
				IF mission_flag = 8
					START_PLAYBACK_RECORDED_CAR	toreno_plane 111
					//WRITE_DEBUG_WITH_INT missionflag mission_flag 
					++ mission_flag
					//WRITE_DEBUG test
				ELSE
					PRINT_NOW SYN6_06 5000 1//You failed to destroy the helicopter!
					GOTO mission_syn6_failed
				ENDIF
//				PRINT_NOW WASTED 5000 1
//				START_RECORDING_CAR	toreno_plane 111
			ENDIF
		ELSE
			REQUEST_CAR_RECORDING 111
		ENDIF
	ENDIF
ENDIF


IF mission_flag > 4
	IF NOT IS_CAR_DEAD toreno_plane
		speed_selected = 0	  //keep checking speed
		GET_CAR_HEALTH toreno_plane health_counter
		health_counter -= 250
		health_counter /= 10
		IF health_counter > 100
			health_counter = 100
		ENDIF
		IF health_counter < 1
			IF syn6_timer = 0
			IF NOT mission_flag = 99
				STOP_PLAYBACK_RECORDED_CAR toreno_plane
				//GET_CAR_COORDINATES toreno_plane syn6_explosionX syn6_explosionY syn6_explosionZ
				//ADD_EXPLOSION_VARIABLE_SHAKE syn6_explosionX syn6_explosionY syn6_explosionZ EXPLOSION_HELI	1.0

				MAKE_HELI_COME_CRASHING_DOWN toreno_plane
				mission_flag = 99
				syn6_timer = 1
				timera = 0
//				IF NOT IS_CHAR_DEAD shooter[0]
//					DETACH_CHAR_FROM_CAR shooter[0]
//				ENDIF
//				IF NOT IS_CHAR_DEAD shooter[1]
//					DETACH_CHAR_FROM_CAR shooter[1]
//				ENDIF
//				syn6_timer = 1
//				CLEAR_PRINTS
//				PRINT_NOW SYN6_09 6000 1
//
//				GOTO mission_syn6_passed



			ENDIF
			ENDIF
			health_counter = 0
		ENDIF

		IF mission_flag > 7
		IF NOT syn6_weird = 1
		IF NOT syn6_isincar = 1
			IF speed_selected = 0
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer toreno_plane 100.0 100.0 0
					SET_PLAYBACK_SPEED toreno_plane 0.75//0.80//1.0//0.75
				  //	WRITE_DEBUG full
					speed_selected = 1	 
				ENDIF
			ENDIF

			IF speed_selected = 0		 // if player is within 100 metres we don't want to check again as he would be within 175 too
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer toreno_plane 200.0 200.0 0
					SET_PLAYBACK_SPEED toreno_plane 0.5//0.6//0.75
				   //	write_debug twohthirds
					speed_selected = 1
				ELSE
					SET_PLAYBACK_SPEED toreno_plane 0.3//0.50
				  //	write_debug half
					speed_selected = 1	 // player not within 100 ergot not 200 metres
				ENDIF
			ENDIF
		ENDIF
		ENDIF
		ENDIF

				
		IF mission_flag < 10

			IF warningswap = 0
			  	IF NOT IS_CAR_DEAD toreno_plane
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 560.0 560.0 500.0 0
					   CLEAR_PRINTS
					   PRINT_NOW SYN6_12 6000 1					   
					   warningswap = 1
			 		ENDIF
				ENDIF
			ENDIF
			IF warningswap = 1
				IF NOT IS_CAR_DEAD toreno_plane
					IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 400.0 400.0 400.0 0
					   CLEAR_PRINTS
					   PRINT_NOW SYN6_13 6000 1
					   warningswap = 0
			 		ENDIF
				ENDIF
			ENDIF


			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer toreno_plane 700.0 700.0 0
			  	CLEAR_PRINTS
				PRINT_NOW SYN6_08 6000 1
				GOTO mission_syn6_failed
			ENDIF
		ENDIF

		IF mission_flag > 10
			IF NOT mission_flag = 99
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer toreno_plane 100.0 100.0 50.0 0

					CLEAR_PRINTS
					PRINT_NOW SYN6_10 6000 1
					GOTO mission_syn6_failed
				ENDIF
			ENDIF
		ENDIF
				
		 
		

	  



	ELSE

// Copter dead!	This bit keeps getting rechecked!
//	    IF NOT IS_CHAR_DEAD shooter[0]
//			DETACH_CHAR_FROM_CAR shooter[0]
//	 	ENDIF
//		IF NOT IS_CHAR_DEAD shooter[1]
//			DETACH_CHAR_FROM_CAR shooter[1]
//		ENDIF
		CLEAR_PRINTS
		//Check tomorrow for heli crashing down!
		

		//GOTO mission_syn6_passed


		IF syn6_timer = 0
			mission_flag = 99

			STOP_PLAYBACK_RECORDED_CAR toreno_plane
			
			//GET_CAR_COORDINATES toreno_plane syn6_explosionX syn6_explosionY syn6_explosionZ
			//ADD_EXPLOSION_VARIABLE_SHAKE syn6_explosionX syn6_explosionY syn6_explosionZ EXPLOSION_HELI	1.0


			MAKE_HELI_COME_CRASHING_DOWN toreno_plane
			syn6_timer = 1
			TIMERA = 0
		ENDIF




	ENDIF


	IF mission_flag < 10
	c = 0
	WHILE c < 2
		IF NOT IS_CHAR_DEAD	shooter[c]
			GET_CHAR_HEALTH	shooter[c] temp_int2
			
			// New section
			IF shooterdead[c] = 0
				IF temp_int2 < 50
			
			   		TASK_TOGGLE_DUCK shooter[c] FALSE
					shooterdead[c] = 1
					DETACH_CHAR_FROM_CAR shooter[c]
	
				ENDIF
			ENDIF

			//Reinstate section below if fix turns out to be problematic
			/*
			IF temp_int2 < 300
				temp_int = 300 - temp_int2
				GET_CAR_HEALTH toreno_plane r
				r -= temp_int
				IF r > 0
				   	SET_CAR_HEALTH toreno_plane r
				   	SET_CHAR_HEALTH shooter[c] 300
				ENDIF
			ENDIF
			*/ 
			


			IF mission_flag > 6
				IF NOT IS_CHAR_DEAD shooter[1]
				GET_SCRIPT_TASK_STATUS shooter[1] TASK_KILL_CHAR_ON_FOOT_TIMED task1_status
				
				//GET_SCRIPT_TASK_STATUS shooter[c] TASK_KILL_CHAR_ON_FOOT_TIMED task_status

			 
				IF task1_status = FINISHED_TASK
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D shooter[1] scplayer 60.0 60.0 0
						SET_CURRENT_CHAR_WEAPON shooter[1] WEAPONTYPE_AK47
						//TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING shooter[c] scplayer 5 300000 100
						   
					   	TASK_KILL_CHAR_ON_FOOT_TIMED shooter[1] scplayer 4000
					ENDIF
				ENDIF
				ENDIF
				

				IF NOT IS_CHAR_DEAD shooter[0]
			   //	GET_SCRIPT_TASK_STATUS shooter[0] TASK_DRIVE_BY task0_status
				
				GET_SCRIPT_TASK_STATUS shooter[0] TASK_KILL_CHAR_ON_FOOT_TIMED task0_status

			 
				IF task0_status = FINISHED_TASK


					IF syn6_weird = 1

						IF LOCATE_CHAR_ANY_MEANS_CHAR_2D shooter[0] scplayer 160.0 160.0 0
			
						   //	WRITE_DEBUG weir
							IF NOT syn6_weird = 1
								SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_desert_eagle
							ENDIF

							IF syn6_weird = 1
								SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_AK47
								SET_CHAR_ACCURACY shooter[0] 100
							ENDIF
							//SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_AK47
							//TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING shooter[c] scplayer 5 300000 100
							   
						   //	TASK_DRIVE_BY shooter[0] scplayer -1 0.0 0.0 0.0 80.0 DRIVEBY_FIXED_LHS FALSE 90
							TASK_KILL_CHAR_ON_FOOT_TIMED shooter[0] scplayer 4000

						ENDIF

					ELSE

						 IF LOCATE_CHAR_ANY_MEANS_CHAR_2D shooter[0] scplayer 60.0 60.0 0 
			
						   //	WRITE_DEBUG norm
							IF NOT syn6_weird = 1
								SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_desert_eagle
							ENDIF

							IF syn6_weird = 1
								SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_AK47
							ENDIF
							//SET_CURRENT_CHAR_WEAPON shooter[0] WEAPONTYPE_AK47
							//TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING shooter[c] scplayer 5 300000 100
							   
						   //	TASK_DRIVE_BY shooter[0] scplayer -1 0.0 0.0 0.0 80.0 DRIVEBY_FIXED_LHS FALSE 90
							TASK_KILL_CHAR_ON_FOOT_TIMED shooter[0] scplayer 4000

						ENDIF

					
					ENDIF
					
				ENDIF
				ENDIF

			ENDIF
		ELSE
			//TASK_TOGGLE_DUCK shooter[c] FALSE

			DETACH_CHAR_FROM_CAR shooter[c]
			
		ENDIF
		++ c
	ENDWHILE
	ENDIF
ENDIF


IF syn6_timer = 1
   //	IF NOT IS_CAR_DEAD toreno_plane
   //	ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE toreno_plane 0.0 0.0 5.0 bike_truck 0.0 JUMP_CUT
   //	ENDIF

	IF timera > 5000
	    IF NOT IS_CHAR_DEAD shooter[0]
			TASK_TOGGLE_DUCK shooter[0] FALSE

			DETACH_CHAR_FROM_CAR shooter[0]
		ENDIF
		IF NOT IS_CHAR_DEAD shooter[1]
			TASK_TOGGLE_DUCK shooter[1] FALSE

			DETACH_CHAR_FROM_CAR shooter[1]
		ENDIF
	
		CLEAR_PRINTS
		PRINT_NOW SYN6_09 6000 1
		

		GOTO mission_syn6_passed

	
	ENDIF	


ENDIF



GOTO mission_syn6_loop


											 
// ************************************ MISSION FAILED *************************************
mission_syn6_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// ************************************ MISSION PASSED *************************************
mission_syn6_passed:
REMOVE_BLIP garage_contact_blip

++ flag_synd_mission_counter
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 18000 5000 1
ADD_SCORE player1 18000

AWARD_PLAYER_MISSION_RESPECT 50

CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED ( SYND_6 )
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT syn6_mission_loop
RETURN
		


// *********************************** MISSION CLEANUP *************************************
mission_syn6_cleanup:

tlf_underway = 2

CLEAR_MISSION_AUDIO 3


REMOVE_BLIP mission_blip
REMOVE_BLIP	rocket_blip
								
IF NOT IS_CHAR_DEAD scplayer
	REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_ROCKETLAUNCHER
ENDIF

CLEAR_ONSCREEN_COUNTER health_counter

MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED colt45
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG1
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG2
MARK_MODEL_AS_NO_LONGER_NEEDED PONY
MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
MARK_MODEL_AS_NO_LONGER_NEEDED desert_eagle



//MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA

GET_GAME_TIMER timer_mobile_start

 


flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN

}

					   
/*
[SYN6_01:SYN6]
~s~Toreno's boarding a helicopter at a ~y~helipad~s~ nearby. Get there and stop him.

[SYN6_02:SYN6]
~s~Toreno's men have ~g~heavy duty weaponry~s~ at their disposal.  Claim it for yourself!

[SYN6_03:SYN6]
~s~The helicopter's airborne! Get that ~g~rocket launcher~s~ to bring it down!

[SYN6_04:SYN6]
~s~ Follow that ~r~helicopter~s~ and bring it down! Use any means necessary!

[SYN6_05:SYN6]
Heli Health:

[SYN6_06:SYN6]
~r~You failed to destroy the helicopter!

[SYN6_07:SYN6]
~s~It's an ambush! Stop that ~r~helicopter~s~ from getting away! 

[SYN6_08:SYN6]
~r~Too late! Toreno's helicopter is well out of range now! 

[SYN6_09:SYN6]
~s~Excellent. Toreno can't have survived that fireball.

[SYN6_10:SYN6]
~r~Toreno's tricked you! He's left and you've got trouble!

[SYN6_11:SYN6]
~s~Chase the ~r~helicopter~s~ and use the rocket launcher to destroy it!

[SYN6_12:SYN6]
~s~Toreno's ~r~helicopter~s~ is getting out of range! Keep up!

[SYN6_13:SYN6]
~s~Good. Get close to the ~r~helicopter~s~ and shoot it down.

 */