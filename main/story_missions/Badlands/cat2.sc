MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 19/08/2003 	Time: 11:06:45	   Name:  Chris Rothwell 					 ***
// ***																					 ***
// *** Mission: cat2					 												 ***
// ***																					 ***
// *** Brief: Pishy bank job                                                             ***
// ***																					 ***
// *****************************************************************************************

GOSUB mission_cat2_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_cat2_failed																   

ENDIF

GOSUB mission_cat2_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{

LVAR_INT ct2_door_destroyed print_god_text cat_comment cat_decisions

mission_cat2_start:

force_audio = 1


LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM cat_decisions
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_DAMAGE
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_WEAPON
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_GUN_AIMED_AT
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_COLLISION
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_SEEN_COP
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE
ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

LVAR_INT cat_own_dec

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY cat_own_dec //newline
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cat_own_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 TRUE TRUE //newline
//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cat_own_dec EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 TRUE TRUE //newline



cat_comment = 32

flag_player_on_mission = 1

ALTER_WANTED_LEVEL Player1 0

IF flag_player_on_mission = 69
	ADD_CONTINUOUS_SOUND 2320.0427 -8.5501 27.2047 SOUND_BANK_ALARM_LOOP bank_alarm
	CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 682.8292 -477.8332 15.3359 c2_alley_cop[0]
ENDIF

//REGISTER_MISSION_GIVEN
SCRIPT_NAME cat2

DO_FADE 800 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE


SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0


IF DOES_CHAR_EXIST catalina
	DELETE_CHAR catalina
ENDIF

LOAD_SPECIAL_CHARACTER 5 cat 
while NOT HAS_SPECIAL_CHARACTER_LOADED 5
	wait 0
	LOAD_SPECIAL_CHARACTER 5 cat 
endwhile

CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 682.8292 -477.8332 15.3359 catalina








UNLOAD_SPECIAL_CHARACTER 5

SET_ANIM_GROUP_FOR_CHAR catalina WOMAN
//DONT_REMOVE_CHAR catalina
SET_CHAR_ONLY_DAMAGED_BY_PLAYER catalina TRUE
SET_CHAR_NEVER_TARGETTED catalina TRUE
SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1

SET_CHAR_HEALTH catalina 100
SET_CHAR_SUFFERS_CRITICAL_HITS catalina FALSE


CLEAR_AREA 2297.7864 -16.8313 26.2964 20.0 TRUE

IF NOT IS_CHAR_DEAD scplayer
	IF IS_CHAR_IN_ANY_CAR catalina
		LVAR_INT temp_car
		STORE_CAR_CHAR_IS_IN_NO_SAVE catalina temp_car
		IF IS_CHAR_IN_CAR scplayer temp_car
			SET_CAR_COORDINATES temp_car 2297.9861 -14.0735 25.3495
			SET_CAR_HEADING temp_car 180.0
		ELSE
			IF IS_CHAR_IN_ANY_CAR scplayer
				SET_CHAR_COORDINATES scplayer 2297.7864 -15.5166 26.2964	
				SET_CHAR_HEADING scplayer 180.0
			ENDIF
		ENDIF
	ELSE
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer temp_car
			IF IS_CHAR_IN_CAR scplayer temp_car
				SET_CAR_COORDINATES temp_car 2297.9861 -14.0735 25.3495
				SET_CAR_HEADING temp_car 180.0
			ENDIF

			SET_CHAR_COORDINATES scplayer 2297.7864 -15.5166 26.2964
			SET_CHAR_HEADING scplayer 180.0
		ENDIF
	ENDIF
ENDIF

IF IS_CHAR_IN_ANY_CAR scplayer
	WARP_CHAR_FROM_CAR_TO_COORD	scplayer 2297.7864 -15.5166 26.2964
ELSE
	SET_CHAR_COORDINATES scplayer 2297.7864 -15.5166 26.2964
ENDIF
SET_CHAR_HEADING scplayer 162.2290



REQUEST_MODEL CSHER
REQUEST_MODEL COLT45
REQUEST_MODEL CHROMEGUN
REQUEST_CAR_RECORDING 123
REQUEST_CAR_RECORDING 124
REQUEST_MODEL KMB_ATM1
REQUEST_MODEL KMB_ATM2
REQUEST_MODEL KMB_ATM3
REQUEST_MODEL MTSAFE
REQUEST_MODEL CR_DOOR_01
REQUEST_MODEL CR_DOOR_03



WHILE NOT HAS_MODEL_LOADED CSHER
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED CHROMEGUN
OR NOT HAS_MODEL_LOADED KMB_ATM1
OR NOT HAS_MODEL_LOADED KMB_ATM2
OR NOT HAS_MODEL_LOADED KMB_ATM3
	WAIT 0
ENDWHILE 

WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 124
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 123
OR NOT HAS_MODEL_LOADED MTSAFE
OR NOT HAS_MODEL_LOADED CR_DOOR_01
OR NOT HAS_MODEL_LOADED CR_DOOR_03
	WAIT 0
ENDWHILE
 


LVAR_INT mission_blip mission_flag mission_timer sequence_task temp_int	a
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 speed temp_float

LVAR_INT ct2_objects_created
//VIEW_INTEGER_VARIABLE debug_var debug_var
LOAD_MISSION_TEXT cat


//REQUEST_MODEL PCJ600
REQUEST_MODEL WMYSGRD
REQUEST_MODEL SWMYRI
REQUEST_MODEL WFYSTEW
REQUEST_ANIMATION ROB_BANK
LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED WMYSGRD
OR NOT HAS_MODEL_LOADED SWMYRI
OR NOT HAS_MODEL_LOADED WFYSTEW
	WAIT 0
ENDWHILE

WHILE NOT HAS_ANIMATION_LOADED ROB_BANK
	WAIT 0
ENDWHILE

WHILE NOT IS_PLAYER_PLAYING	player1
	WAIT 0
ENDWHILE

LOAD_MISSION_AUDIO 3 SOUND_BANK_CAT2_BANK



LVAR_INT	catalina_flag
catalina_flag = 0

LVAR_FLOAT security1_scared worker1_scared worker2_scared worker3_scared
security1_scared = 85.0
worker1_scared = 100.0
worker2_scared = 90.0
worker3_scared = 95.0


//
//LVAR_INT goto_bankroof_from_pizzashop
//OPEN_SEQUENCE_TASK goto_bankroof_from_pizzashop
//	TASK_GO_STRAIGHT_TO_COORD -1 2324.6196 -3.2737 30.1564 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 184.3031
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2322.5469 -9.3704 28.9834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 94.6195
//	TASK_CLIMB -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2315.1860 -9.9396 31.5313 PEDMOVE_WALK -2
//	TASK_GO_STRAIGHT_TO_COORD -1 2315.5491 -2.1481 31.5313 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_bankroof_from_pizzashop
//
//LVAR_INT goto_lazershop_from_bankroof
//OPEN_SEQUENCE_TASK goto_lazershop_from_bankroof
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.3611 0.2392 31.5313 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 0.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.4788 3.4769 32.1951 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 0.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.7012 11.3982 28.9834 PEDMOVE_WALK -2
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.7012 14.2735 28.9834 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_lazershop_from_bankroof
//
//LVAR_INT goto_drycleaner_from_lazershop
//OPEN_SEQUENCE_TASK goto_drycleaner_from_lazershop
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.5288 17.3576 28.9834 PEDMOVE_WALK -2
//	TASK_GO_STRAIGHT_TO_COORD -1 2312.1909 19.8714 28.5834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 9.0 
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.8774 27.2978 28.4834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 0.0
//	TASK_CLIMB -1 TRUE 
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.8621 30.3306 29.8072 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_drycleaner_from_lazershop
//
//LVAR_INT goto_furnitureshop_from_drycleaner
//OPEN_SEQUENCE_TASK goto_furnitureshop_from_drycleaner
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.7368 31.1751 29.8072 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 0.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.5356 41.0045 30.3072 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 10.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.7646 49.7877 29.4834 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_furnitureshop_from_drycleaner
//
//LVAR_INT goto_recordshop_from_furnitureshop
//OPEN_SEQUENCE_TASK goto_recordshop_from_furnitureshop
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.1519 68.0 29.4834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 0.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2313.4077 75.1751 28.6413 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_recordshop_from_furnitureshop
//
//LVAR_INT goto_furnitureshop_from_recordshop
//OPEN_SEQUENCE_TASK goto_furnitureshop_from_recordshop
//	TASK_SCRATCH_HEAD -1
//	TASK_GO_STRAIGHT_TO_COORD -1 2315.8765 69.0288 25.4762 PEDMOVE_WALK -2//DROP OFF THE ROOF, CATALINA CANT MAKE THE JUMP
//CLOSE_SEQUENCE_TASK goto_furnitureshop_from_recordshop
//
//LVAR_INT goto_drycleaner_from_furnitureshop
//OPEN_SEQUENCE_TASK goto_drycleaner_from_furnitureshop
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.8958 44.6904 29.4834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 174.6394
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.9290 30.3306 29.8072 PEDMOVE_WALK -2
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.8621 30.3306 29.8072 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_drycleaner_from_furnitureshop
//
//LVAR_INT goto_lasershop_from_drycleaner
//OPEN_SEQUENCE_TASK goto_lasershop_from_drycleaner
//	TASK_GO_STRAIGHT_TO_COORD -1 2311.2166 18.7016 28.5834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 90.0
//	TASK_JUMP -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.7012 14.2735 28.9834 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_lasershop_from_drycleaner
//
//LVAR_INT goto_bankroof_from_lasershop
//OPEN_SEQUENCE_TASK goto_bankroof_from_lasershop
//	TASK_GO_STRAIGHT_TO_COORD -1 2309.4966 10.8862 28.9834 PEDMOVE_WALK -2
//	TASK_GO_STRAIGHT_TO_COORD -1 2313.0020 8.2174 28.5834 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 180.0000
//	TASK_CLIMB -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2313.0051 2.4411 30.2345 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 180.0000
//	TASK_CLIMB -1 TRUE
//	TASK_GO_STRAIGHT_TO_COORD -1 2315.5012 -2.4998 31.5313 PEDMOVE_WALK -2
//CLOSE_SEQUENCE_TASK goto_bankroof_from_lasershop
//
//LVAR_INT goto_pizzashop_from_bankroof
//OPEN_SEQUENCE_TASK goto_pizzashop_from_bankroof
//	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2321.6851 -2.2579 32.2969 PEDMOVE_WALK -2
//	TASK_ACHIEVE_HEADING -1 270.0
//	TASK_CLIMB -1 TRUE
//CLOSE_SEQUENCE_TASK goto_pizzashop_from_bankroof
//
//LVAR_INT routefind_to_bankroof
//OPEN_SEQUENCE_TASK routefind_to_bankroof
//	TASK_SCRATCH_HEAD -1
//	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2315.5491 -2.1481 31.5313 PEDMOVE_WALK -2
//	//TASK_GO_STRAIGHT_TO_COORD -1 2315.5491 -2.1481 31.5313 PEDMOVE_WALK 1000
//CLOSE_SEQUENCE_TASK routefind_to_bankroof

LVAR_INT shoot_at_cops
OPEN_SEQUENCE_TASK shoot_at_cops
	TASK_SCRATCH_HEAD -1
	TASK_SCRATCH_HEAD -1
CLOSE_SEQUENCE_TASK shoot_at_cops

//IF mission_flag = 28
//	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2317.4285 2.9314 26.7164 3.0 2.0 1.2 0//OUTSIDE BANKS BACK DOOR LOCATE
//		IF NOT IS_CHAR_DEAD	alley_cop1
//		IF NOT IS_CHAR_DEAD	alley_cop2
//  
//IF mission_flag = 29
//	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2318.7502 15.8125 31.3712 15.0 11.2 5.8 0//MIDDLE AREA 2 & 3
//		IF NOT IS_CHAR_DEAD	alley_cop3
//		IF NOT IS_CHAR_DEAD	alley_cop4
//		IF NOT IS_CHAR_DEAD	alley_cop5
//		IF NOT IS_CHAR_DEAD	alley_cop6
//
//IF mission_flag = 30
//	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2318.7502 54.3324 31.3712 15.0 11.2 5.8 0//BIKE END OF ALLEY AREA 4
//		IF NOT IS_CHAR_DEAD	alley_cop7
//		IF NOT IS_CHAR_DEAD	alley_cop8

LVAR_INT bank_peds_decision_maker
COPY_CHAR_DECISION_MAKER -1 bank_peds_decision_maker
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE bank_peds_decision_maker EVENT_GUN_AIMED_AT

LVAR_INT worker1 worker2 worker3 security1 granny
CREATE_CHAR PEDTYPE_CIVFEMALE WFYSTEW 2318.4919 -15.5635 25.6913 worker1//BEHIND COUNTER TO RIGHT
SET_CHAR_HEADING worker1 95.2069
SET_CHAR_DECISION_MAKER worker1 bank_peds_decision_maker

CREATE_CHAR PEDTYPE_CIVFEMALE WFYSTEW 2318.4727 -7.4565 25.6913 worker2//BEHIND COUNTER TO LEFT
MARK_MODEL_AS_NO_LONGER_NEEDED WFYSTEW
SET_CHAR_HEADING worker2 95.2069
SET_CHAR_DECISION_MAKER worker2 bank_peds_decision_maker

CREATE_CHAR PEDTYPE_CIVMALE SWMYRI 2311.5093 -11.1779 25.6987 worker3//BEHIND RECEPTION DESK
SET_CHAR_HEADING worker3 176.6813
SET_CHAR_DECISION_MAKER worker3 bank_peds_decision_maker

CREATE_CHAR PEDTYPE_CIVMALE WMYSGRD 2316.618 -12.83 25.75 security1//SECURITY GUARD BACK WALL
MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD
SET_CHAR_HEADING security1 90.0
SET_CHAR_DECISION_MAKER security1 bank_peds_decision_maker
GIVE_WEAPON_TO_CHAR	security1 WEAPONTYPE_PISTOL 9999
//CREATE_CHAR PEDTYPE_CIVFEMALE OFOST 2316.7742 -7.4783 25.6913 granny//GRANNY BEING SERVED AT LEFT HAND COUNTER
//SET_CHAR_HEADING granny 272.6767



SET_PLAYER_CONTROL player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 TRUE
SWITCH_WIDESCREEN ON
//SET_ALL_CARS_CAN_BE_DAMAGED FALSE
SET_FIXED_CAMERA_POSITION 2299.8948 -7.9983 26.6055 0.0 0.0 0.0//OUTSIDE BANK
POINT_CAMERA_AT_POINT 2299.9976 -8.9930 26.6023 JUMP_CUT
mission_timer = game_timer + 3000

CREATE_OBJECT_NO_OFFSET KMB_ATM2 2310.3669 -17.0 20.414 broken_atm[0] //BROKEN ATM MODEL HERE
CREATE_OBJECT_NO_OFFSET KMB_ATM2 2313.6890 -17.0 20.414 broken_atm[1] //BROKEN ATM MODEL HERE
CREATE_OBJECT_NO_OFFSET KMB_ATM2 2313.3184 -0.1206 20.414 broken_atm[2] //BROKEN ATM MODEL HERE
//
//SET_OBJECT_VISIBLE broken_atm[0] FALSE
//SET_OBJECT_VISIBLE broken_atm[1] FALSE
//SET_OBJECT_VISIBLE broken_atm[2] FALSE

IF NOT IS_CHAR_DEAD catalina
	REMOVE_CHAR_FROM_GROUP catalina
	IF IS_CHAR_IN_ANY_CAR catalina
		WARP_CHAR_FROM_CAR_TO_COORD	catalina 2300.7429 -15.5274 25.4844
	ELSE
		SET_CHAR_COORDINATES catalina 2301.7429 -17.1274 25.4844
	ENDIF

			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN cat2_ReturnedAmmo
			IF cat2_ReturnedAmmo = 0
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 48
			ELSE
				IF cat2_ReturnedAmmo < 48
					SET_CHAR_AMMO scplayer WEAPONTYPE_SHOTGUN 48
				ENDIF
			ENDIF

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN
			
			GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_SHOTGUN 9999




	SET_CHAR_HEADING catalina 329.4303
	
	TASK_GO_STRAIGHT_TO_COORD scplayer 2307.8022 -17.2210 25.7574 PEDMOVE_WALK 10000
	TASK_GO_STRAIGHT_TO_COORD catalina 2312.4260 -15.4762 25.7500 PEDMOVE_WALK 10000

ENDIF

DO_FADE 500 FADE_IN

DELETE_OBJECT pc_door[2]
DELETE_OBJECT pc_door[3]

//PRINT_NOW CAT2_01 3500 1 //When we're inside I'll get the cash, your on crowd control. Don't kill anyone.
play_audio = 1
//DO_FADE 500 FADE_IN
GET_GAME_TIMER game_timer
mission_timer = game_timer + 3500
mission_flag = 1

SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0

//GET_GAME_TIMER game_timer
//timer = game_timer + 5000
//WHILE timer > game_timer
//	WAIT 0
//	GET_GAME_TIMER game_timer
//ENDWHILE

LVAR_INT skip_buttons_pressed
skip_buttons_pressed = -1

//VIEW_INTEGER_VARIABLE mission_flag mission_flag
//VIEW_INTEGER_VARIABLE catalina_flag catalina_flag

SET_PED_DENSITY_MULTIPLIER 0.0
SET_CAR_DENSITY_MULTIPLIER 0.0

// ************************************* MISSION LOOP **************************************
mission_cat2_loop:

//VAR_INT checky checkx check1 check2

//checky = play_audio
//checkx = audio_flag
//check1 = mission_flag
//check2 = catalina_flag
//
//VIEW_INTEGER_VARIABLE checky play_audio
//VIEW_INTEGER_VARIABLE checkx audio_flag
//VIEW_INTEGER_VARIABLE check1 mission_flag
//VIEW_INTEGER_VARIABLE check2 catalina_flag


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_cat2_passed  
ENDIF

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W
	cat_counter = 2
	catalina_flag = 9
	ct2_door_destroyed = 1
	mission_flag = 462
	SET_CHAR_COORDINATES scplayer 857.4263 -29.5418 62.1858
	IF NOT IS_CHAR_DEAD catalina
		SET_CHAR_COORDINATES catalina 858.4263 -29.5418 62.1858
	ENDIF

	REQUEST_MODEL BUFFALO
	WHILE NOT HAS_MODEL_LOADED BUFFALO
		WAIT 0
	ENDWHILE

	LVAR_INT debugy_Car
	CREATE_CAR BUFFALO 857.4263 -29.5418 62.1858 debugy_Car

	WARP_CHAR_INTO_CAR scplayer debugy_Car
	IF NOT IS_CHAR_DEAD catalina
		WARP_CHAR_INTO_CAR_AS_PASSENGER catalina debugy_Car 0
	ENDIF
	
ENDIF


WAIT 0

IF IS_CHAR_DEAD catalina
	CLEAR_PRINTS
	PRINT CAT2_A1 5000 1
	GOTO mission_cat2_failed
ENDIF

GOSUB players_group

GOSUB cat2_audio

GET_GAME_TIMER game_timer

IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_cat2_failed
ENDIF

IF IS_CHAR_DEAD	catalina
	GOTO mission_cat2_failed
ENDIF

IF mission_flag < 9
AND	mission_flag > -1
	
	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		IF skip_buttons_pressed = 0
			//SKIP CUTSCENE HERE
			DO_FADE 500 FADE_OUT
			mission_flag = 0
			++ skip_buttons_pressed
		ENDIF
	ELSE
		IF skip_buttons_pressed = -1
			++ skip_buttons_pressed
		ENDIF
	ENDIF
	IF skip_buttons_pressed = 1
		IF NOT GET_FADING_STATUS
			

			IF mission_flag < 2
			AND ct2_objects_created = 0
				CREATE_OBJECT_NO_OFFSET KMB_ATM1 2310.3669 -17.0 26.414 atm[0]
				SET_OBJECT_HEADING atm[0] 180.0
				set_object_collision_damage_effect atm[0] FALSE
				CREATE_OBJECT_NO_OFFSET KMB_ATM1 2313.6890 -17.0 26.414 atm[1]
				SET_OBJECT_HEADING atm[1] 180.0
				set_object_collision_damage_effect atm[1] FALSE
				CREATE_OBJECT_NO_OFFSET KMB_ATM1 2313.3184 -0.1206 26.414 atm[2]
				set_object_collision_damage_effect atm[2] FALSE
				CREATE_OBJECT_NO_OFFSET MTSafe 2305.6655 -4.7944 25.65 atm[3]
				SET_OBJECT_HEADING atm[3] 90.0
				set_object_collision_damage_effect atm[3] FALSE

				CREATE_OBJECT_NO_OFFSET KMB_ATM3 2310.3669 -17.0 26.414 atm_static[0]
				set_object_collision_damage_effect atm_static[0] FALSE
				SET_OBJECT_HEADING atm[0] 180.0
				CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.6890 -17.0 26.414 atm_static[1]
				set_object_collision_damage_effect atm_static[1] FALSE
				SET_OBJECT_HEADING atm[1] 180.0
				CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.3184 -0.1206 26.414 atm_static[2]
				set_object_collision_damage_effect atm_static[2] FALSE
//				CREATE_OBJECT_NO_OFFSET man_safenew 2306.1655 -4.7944 26.414 atm_static[3]
				LVAR_INT safe_col
				CREATE_OBJECT_NO_OFFSET cat2_safe_col 2305.6655 -4.7944 25.65 safe_col
				SET_OBJECT_HEADING safe_col 90.0

				SET_OBJECT_HEADING atm[3] 90.0
   				SET_OBJECT_HEADING atm_static[0] 180.0
				SET_OBJECT_HEADING atm_static[1] 180.0
//				SET_OBJECT_HEADING atm_static[3] 90.0

				SET_OBJECT_COLLISION atm[0] FALSE
				SET_OBJECT_COLLISION atm[1] FALSE
				SET_OBJECT_COLLISION atm[2] FALSE
//				SET_OBJECT_COLLISION atm[3] FALSE

//				SET_OBJECT_VISIBLE atm_static[3] TRUE
//				DELETE_OBJECT atm[3]


				ct2_objects_created = 1
			ENDIF
			LVAR_INT cat2_ReturnedAmmo
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN cat2_ReturnedAmmo
			IF cat2_ReturnedAmmo = 0
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 48
			ELSE
				IF cat2_ReturnedAmmo < 48
					SET_CHAR_AMMO scplayer WEAPONTYPE_SHOTGUN 48
				ENDIF
			ENDIF

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN
			
			GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_SHOTGUN 9999
			CLEAR_CHAR_TASKS scplayer
			SET_CHAR_COORDINATES scplayer 2312.5779 -7.4347 25.7500
			SET_CHAR_HEADING scplayer 126.7479
			SET_CHAR_COORDINATES catalina 2306.6655 -4.7944 25.75  
			SET_CHAR_HEADING catalina 90.0
			CLEAR_CHAR_TASKS catalina
			CLEAR_CHAR_TASKS_IMMEDIATELY catalina
			TASK_PLAY_ANIM catalina CAT_SAFE_ROB ROB_BANK 1000.0 TRUE FALSE FALSE FALSE -2
//			PLAY_OBJECT_ANIM atm[3] CAT_Safe_Open_O ROB_BANK 1000.0 FALSE TRUE
			PLAY_OBJECT_ANIM atm[3] CAT_SAFE_OPEN_O ROB_BANK 1000.0 FALSE TRUE
//			SET_OBJECT_ANIM_CURRENT_TIME atm[3] CAT_Safe_Open_O 1.0

//			SET_CHAR_COORDINATES catalina 2307.1655 -4.7944 26.414
//			SET_CHAR_HEADING catalina 90.0
//			IF NOT IS_CHAR_DEAD catalina
////				TASK_TOGGLE_DUCK catalina TRUE
//				TASK_PLAY_ANIM catalina CAT_SAFE_OPEN ROB_BANK 1000.0 FALSE FALSE FALSE FALSE -2
//				PLAY_OBJECT_ANIM atm[3] CAT_SAFE_OPEN_O ROB_BANK 1000.0 FALSE TRUE  
//			ENDIF


			IF NOT IS_CHAR_DEAD	security1											  
				CLEAR_CHAR_TASKS_IMMEDIATELY security1											  
				SET_CHAR_COORDINATES security1 2310.5461 -7.7439 25.7422			  
				SET_CHAR_HEADING security1 281.4267 								  
				TASK_TURN_CHAR_TO_FACE_CHAR security1 scplayer
				FREEZE_CHAR_POSITION security1 TRUE
			ENDIF
			IF NOT IS_CHAR_DEAD	worker1
				CLEAR_CHAR_TASKS_IMMEDIATELY worker1
				SET_CHAR_COORDINATES worker1 2311.5972 -9.1847 25.7422
				SET_CHAR_HEADING worker1 314.5010 								 
				TASK_TURN_CHAR_TO_FACE_CHAR worker1 scplayer					 
				FREEZE_CHAR_POSITION worker1 TRUE								 
			ENDIF																 
			IF NOT IS_CHAR_DEAD	worker2
				CLEAR_CHAR_TASKS_IMMEDIATELY worker2
				SET_CHAR_COORDINATES worker2 2310.5906 -9.3749 25.7422
				SET_CHAR_HEADING worker2 323.1424 
				TASK_TURN_CHAR_TO_FACE_CHAR worker2 scplayer
				FREEZE_CHAR_POSITION worker2 TRUE
			ENDIF
			IF NOT IS_CHAR_DEAD	worker3
				CLEAR_CHAR_TASKS_IMMEDIATELY worker3
				SET_CHAR_COORDINATES worker3 2309.6101 -8.5556 25.7422
				SET_CHAR_HEADING worker3 309.2547  //BY THE ALARM
				TASK_TURN_CHAR_TO_FACE_CHAR worker3 scplayer
				FREEZE_CHAR_POSITION worker3 TRUE
			ENDIF


 
			DO_FADE 500 FADE_IN
			mission_timer = game_timer - 555
			mission_flag = 9
			audio_flag = 5
			++ skip_buttons_pressed
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 1
	IF mission_timer < game_timer
	AND ct2_objects_created = 0
	AND play_audio = 0
		//1ST SHOT INSIDE BANK
		play_audio = 2
		CLEAR_SMALL_PRINTS
		CLEAR_AREA 2307.8022 -17.2210 25.7574 100.0	TRUE
		SET_FIXED_CAMERA_POSITION 2308.3958 -15.8240 27.6660 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2309.2732 -15.3600 27.5448 JUMP_CUT

		

		LVAR_INT atm[4] atm_static[4]
		CREATE_OBJECT_NO_OFFSET KMB_ATM1 2310.3669 -17.0 26.414 atm[0]
		SET_OBJECT_HEADING atm[0] 180.0
		set_object_collision_damage_effect atm[0] FALSE
		CREATE_OBJECT_NO_OFFSET KMB_ATM1 2313.6890 -17.0 26.414 atm[1]
		SET_OBJECT_HEADING atm[1] 180.0
		set_object_collision_damage_effect atm[1] FALSE
		CREATE_OBJECT_NO_OFFSET KMB_ATM1 2313.3184 -0.1206 26.414 atm[2]
		set_object_collision_damage_effect atm[2] FALSE
		CREATE_OBJECT_NO_OFFSET MTSafe 2305.7655 -4.7944 25.75 atm[3]
		SET_OBJECT_HEADING atm[3] 90.0
		CREATE_OBJECT_NO_OFFSET cat2_safe_col 2305.7655 -4.7944 25.75 safe_col
		SET_OBJECT_HEADING safe_col 90.0
		set_object_collision_damage_effect atm[3] FALSE

		IF NOT DOES_OBJECT_EXIST pc_door[0]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2322.845 8.304 25.483 pc_door[0]
			DONT_REMOVE_OBJECT pc_door[0]
			set_object_collision_damage_effect pc_door[0] FALSE
			FREEZE_OBJECT_POSITION pc_door[0] TRUE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[1]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2316.233 0.712 25.742 pc_door[1]
			DONT_REMOVE_OBJECT pc_door[1]
			SET_OBJECT_HEADING pc_door[1] 270.0
			FREEZE_OBJECT_POSITION pc_door[1] TRUE 
			set_object_collision_damage_effect pc_door[1] FALSE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[2]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -17.744 25.742 pc_door[2]
			DONT_REMOVE_OBJECT pc_door[2]
			FREEZE_OBJECT_POSITION pc_door[2] TRUE 
			set_object_collision_damage_effect pc_door[2] FALSE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[3]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -14.583 25.742 pc_door[3]
			DONT_REMOVE_OBJECT pc_door[3]
			FREEZE_OBJECT_POSITION pc_door[3] TRUE 
			SET_OBJECT_HEADING pc_door[3] 180.0
			set_object_collision_damage_effect pc_door[3] FALSE
		ENDIF


   
		CREATE_OBJECT_NO_OFFSET KMB_ATM3 2310.3669 -17.0 26.414 atm_static[0]
		set_object_collision_damage_effect atm_static[0] FALSE
		SET_OBJECT_HEADING atm[0] 180.0
		CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.6890 -17.0 26.414 atm_static[1]
		set_object_collision_damage_effect atm_static[1] FALSE
		SET_OBJECT_HEADING atm[1] 180.0
		CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.3184 -0.1206 26.414 atm_static[2]
		set_object_collision_damage_effect atm_static[2] FALSE
//		CREATE_OBJECT_NO_OFFSET man_safenew 2306.1655 -4.7944 26.414 atm_static[3]
		SET_OBJECT_HEADING atm[3] 90.0
		SET_OBJECT_HEADING atm_static[0] 180.0
		SET_OBJECT_HEADING atm_static[1] 180.0
//		SET_OBJECT_HEADING atm_static[3] 90.0

//		SET_OBJECT_VISIBLE atm_static[3] TRUE
//		DELETE_OBJECT ATM[3]

		SET_OBJECT_COLLISION atm[0] FALSE
		SET_OBJECT_COLLISION atm[1] FALSE
		SET_OBJECT_COLLISION atm[2] FALSE



		//cr_door_01 (breakable single door) occurs at:
		//2322.845 8.304 25.483 at 0 heading
		//2316.233 0.712 25.742 at 90 heading
		//
		////cr_door_02 (makes up double doors) at:
		////2333.781 7.194 25.488 at 0 heading
		////2333.781 5.243 25.488 at 180 heading
		//
		//cr_door_03 (makes up double doors) at:
		//2304.257 -17.744 25.742 at 0 heading
		//2304.257 -14.583 25.742 at 180 heading

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer 2309.2888 -14.3987 25.7500
		SET_CHAR_HEADING scplayer 287.3674
		CLEAR_CHAR_TASKS_IMMEDIATELY catalina
		SET_CHAR_COORDINATES catalina 2310.2058 -15.8887 25.7574
		SET_CHAR_HEADING catalina 287.3674

		TASK_GO_STRAIGHT_TO_COORD scplayer 2315.4119 -13.1812 25.7500 PEDMOVE_WALK 10000
		TASK_GO_STRAIGHT_TO_COORD catalina 2316.6191 -15.1198 25.7500 PEDMOVE_WALK 10000
		mission_timer = game_timer + 3000
		ct2_objects_created = 1
		SET_WANTED_MULTIPLIER 0.2
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 2
	IF mission_timer < game_timer
	AND play_audio = 0
		//TARGETTING SECURITTY GUARD - player
		CLEAR_SMALL_PRINTS
		SET_FIXED_CAMERA_POSITION 2315.3115 -13.8451 27.6696 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2315.7708 -12.9906 27.4270 JUMP_CUT

		CLEAR_CHAR_TASKS scplayer
		SET_CHAR_COORDINATES scplayer 2315.4119 -12.1812 25.7500
		SET_CHAR_HEADING scplayer 236.5262
		FREEZE_CHAR_POSITION scplayer TRUE//DEBUG!!!
		
		CLEAR_CHAR_TASKS catalina
		SET_CHAR_COORDINATES catalina 2316.6191 -15.1198 25.7500
		SET_CHAR_HEADING catalina 242.0258
		FREEZE_CHAR_POSITION catalina TRUE//DEBUG!!!

		IF NOT IS_CHAR_DEAD	security1
			GET_CHAR_COORDINATES security1 x y z
			//put the coord back a bit
			x += 2.7
		
			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SHOTGUN cat2_ReturnedAmmo
			IF cat2_ReturnedAmmo = 0
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 48
			ELSE
				IF cat2_ReturnedAmmo < 48
					SET_CHAR_AMMO scplayer WEAPONTYPE_SHOTGUN 48
				ENDIF
			ENDIF

			TASK_AIM_GUN_AT_COORD scplayer x y z 10000
			TASK_HANDS_UP security1 8000
			FREEZE_CHAR_POSITION security1 TRUE
		ENDIF

		play_audio = 3
		mission_timer = game_timer + 2000
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 3
//	IF mission_timer < game_timer
	IF play_audio = 0
		//TARGETTING CASHIER - catalina
		IF NOT IS_CHAR_DEAD	worker1
			GET_CHAR_COORDINATES worker1 x y z
			//put the coord back a bit
			x += 2.7
			z += 0.5
			GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_SHOTGUN 9999
			TASK_AIM_GUN_AT_COORD catalina x y z 10000
			FREEZE_CHAR_POSITION worker1 TRUE
			TASK_HANDS_UP worker1 8000
		ENDIF
		CLEAR_SMALL_PRINTS
		SET_FIXED_CAMERA_POSITION 2317.0112 -17.3945 27.4246 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2317.2483 -16.4244 27.3736 JUMP_CUT
//		PRINT_NOW CAT2_03 2500 1 //I'd like to make a withdrawal bitch....NOW!
		play_audio = 4
		mission_timer = game_timer + 2500
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 4
	IF skip_buttons_pressed < 1
		IF mission_timer < game_timer
			DO_FADE 500 FADE_OUT
			++ mission_flag
		ENDIF
	ENDIF
ENDIF
IF mission_flag = 5
	IF skip_buttons_pressed < 1
		IF NOT GET_FADING_STATUS
			//STAFF STAND AND BE SCARED
			CLEAR_SMALL_PRINTS
			SET_FIXED_CAMERA_POSITION 2316.3770 -11.0151 28.6019 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2315.6130 -10.6059 28.1029 JUMP_CUT
			 
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer 2312.5779 -7.4347 25.7500
			SET_CHAR_HEADING scplayer 126.7479
			CLEAR_CHAR_TASKS_IMMEDIATELY catalina
			SET_CHAR_COORDINATES catalina 2308.5022 -3.9120 25.7422
			SET_CHAR_HEADING catalina 81.0
			IF NOT IS_CHAR_DEAD catalina
				FREEZE_CHAR_POSITION catalina FALSE
//				TASK_TOGGLE_DUCK catalina TRUE
				TASK_GO_STRAIGHT_TO_COORD catalina 2306.7678 -4.5085 25.7422 PEDMOVE_WALK -2
			ENDIF

//			SET_OBJECT_VISIBLE atm_static[3] TRUE
//			DELETE_OBJECT atm[3]

			IF NOT IS_CHAR_DEAD	security1											  
				CLEAR_CHAR_TASKS security1											  
				SET_CHAR_COORDINATES security1 2310.5461 -7.7439 25.7422			  
				SET_CHAR_HEADING security1 281.4267 								  
				TASK_TURN_CHAR_TO_FACE_CHAR security1 scplayer
				FREEZE_CHAR_POSITION security1 TRUE
			ENDIF
			IF NOT IS_CHAR_DEAD	worker1
				CLEAR_CHAR_TASKS worker1
				SET_CHAR_COORDINATES worker1 2311.5972 -9.1847 25.7422
				SET_CHAR_HEADING worker1 314.5010 								 
				TASK_TURN_CHAR_TO_FACE_CHAR worker1 scplayer					 
				FREEZE_CHAR_POSITION worker1 TRUE								 
			ENDIF																 
			IF NOT IS_CHAR_DEAD	worker2
				CLEAR_CHAR_TASKS worker2
				SET_CHAR_COORDINATES worker2 2310.5906 -9.3749 25.7422
				SET_CHAR_HEADING worker2 323.1424 
				TASK_TURN_CHAR_TO_FACE_CHAR worker2 scplayer
				FREEZE_CHAR_POSITION worker2 TRUE
			ENDIF
			IF NOT IS_CHAR_DEAD	worker3
				CLEAR_CHAR_TASKS worker3
				SET_CHAR_COORDINATES worker3 2309.6101 -8.5556 25.7422
				SET_CHAR_HEADING worker3 309.2547  //BY THE ALARM
				TASK_TURN_CHAR_TO_FACE_CHAR worker3 scplayer
				FREEZE_CHAR_POSITION worker3 TRUE
			ENDIF

			REQUEST_ANIMATION OTB

			DO_FADE 500 FADE_IN
			++ mission_flag
		ENDIF
	ENDIF
ENDIF






IF mission_flag = 6
	IF NOT GET_FADING_STATUS

	    SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE 2312.5779 -7.4347 25.7500 4.5 man_safenew FALSE
    	SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE 2312.5779 -7.4347 25.7500 4.5 kev_safe FALSE


		play_audio = 5
		play_audio_for = 3
//		PRINT CAT2_04 4000 1//I'm going to empty the safe.

		mission_timer = game_timer + 800

		++ mission_flag

	ENDIF
ENDIF

IF mission_flag = 7
	IF mission_timer < game_timer
		IF NOT IS_CHAR_DEAD	worker1
			GET_CHAR_COORDINATES worker1 x y z
			z += 0.7
//			TASK_AIM_GUN_AT_COORD scplayer x y z 1500
		ENDIF
		mission_timer = game_timer + 800
		++ mission_flag
		new_flag = 0
	ENDIF
ENDIF

LVAR_INT new_flag
IF mission_flag = 8
	IF new_flag = 0
		IF mission_timer < game_timer
			SET_FIXED_CAMERA_POSITION 2314.6023 -4.9551 28.3779 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2313.7793 -5.3630 27.9827 JUMP_CUT
			IF NOT IS_CHAR_DEAD catalina
				SET_CHAR_COORDINATES catalina 2306.6655 -4.7944 25.75
				SET_CHAR_HEADING catalina 90.0
				SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_UNARMED
				OPEN_SEQUENCE_TASK cat2_seq
					TASK_PLAY_ANIM -1 CAT_SAFE_OPEN ROB_BANK 1000.0 FALSE FALSE FALSE FALSE -2
					TASK_PLAY_ANIM -1 CAT_SAFE_ROB ROB_BANK 1000.0 FALSE FALSE FALSE FALSE -2
				CLOSE_SEQUENCE_TASK cat2_seq
				PERFORM_SEQUENCE_TASK catalina cat2_seq
				CLEAR_SEQUENCE_TASK cat2_Seq
				PLAY_OBJECT_ANIM atm[3] CAT_SAFE_OPEN_O ROB_BANK 1000.0 FALSE TRUE
			ENDIF  
			new_flag = 1
			FREEZE_CHAR_POSITION scplayer FALSE
			IF NOT IS_CHAR_DEAD	worker1
				TASK_AIM_GUN_AT_CHAR scplayer worker1 5500
			ENDIF
			mission_timer = game_timer + 800
		ENDIF
	ENDIF
	IF new_flag = 1
		IF mission_timer < game_timer
//			SET_FIXED_CAMERA_POSITION 2315.5745 -7.7408 27.9122 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT 2314.6243 -7.5849 27.6423 JUMP_CUT
//			IF NOT IS_CHAR_DEAD catalina

//			ENDIF

			
			

			IF NOT IS_CHAR_DEAD	security1											  
				TASK_HANDS_UP security1 8000
			ENDIF
			IF NOT IS_CHAR_DEAD	worker1		 
				OPEN_SEQUENCE_TASK cat2_seq
					TASK_PAUSE -1 250
					TASK_HANDS_UP -1 8000
				CLOSE_SEQUENCE_TASK cat2_seq
				PERFORM_SEQUENCE_TASK worker1 cat2_seq
				CLEAR_SEQUENCE_TASK cat2_seq
			ENDIF																 
			IF NOT IS_CHAR_DEAD	worker2
				OPEN_SEQUENCE_TASK cat2_seq
					TASK_PAUSE -1 350
					TASK_HANDS_UP -1 8000
				CLOSE_SEQUENCE_TASK cat2_seq
				PERFORM_SEQUENCE_TASK worker2 cat2_seq
				CLEAR_SEQUENCE_TASK cat2_seq
			ENDIF
			IF NOT IS_CHAR_DEAD	worker3
				OPEN_SEQUENCE_TASK cat2_seq
					TASK_PAUSE -1 850
					TASK_HANDS_UP -1 8000
				CLOSE_SEQUENCE_TASK cat2_seq
				PERFORM_SEQUENCE_TASK worker3 cat2_seq
				CLEAR_SEQUENCE_TASK cat2_seq

			ENDIF
			mission_timer = game_timer + 2000
			++ mission_flag
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 9
	IF mission_timer < game_timer
	AND play_audio = 0
	AND audio_flag = 1

		IF NOT DOES_OBJECT_EXIST pc_door[0]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2322.845 8.304 25.483 pc_door[0]
			DONT_REMOVE_OBJECT pc_door[0]
			set_object_collision_damage_effect pc_door[0] FALSE
			FREEZE_OBJECT_POSITION pc_door[0] TRUE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[1]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2316.233 0.712 25.742 pc_door[1]
			DONT_REMOVE_OBJECT pc_door[1]
			SET_OBJECT_HEADING pc_door[1] 270.0
			FREEZE_OBJECT_POSITION pc_door[1] TRUE 
			set_object_collision_damage_effect pc_door[1] FALSE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[2]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -17.744 25.742 pc_door[2]
			DONT_REMOVE_OBJECT pc_door[2]
			FREEZE_OBJECT_POSITION pc_door[2] TRUE 
			set_object_collision_damage_effect pc_door[2] FALSE
		ENDIF

		IF NOT DOES_OBJECT_EXIST pc_door[3]
			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -14.583 25.742 pc_door[3]
			DONT_REMOVE_OBJECT pc_door[3]
			FREEZE_OBJECT_POSITION pc_door[3] TRUE 
			SET_OBJECT_HEADING pc_door[3] 180.0
			set_object_collision_damage_effect pc_door[3] FALSE
		ENDIF

		FREEZE_CHAR_POSITION scplayer FALSE
		FREEZE_CHAR_POSITION catalina FALSE
		SET_PLAYER_CONTROL player1 ON
		CLEAR_PRINTS
		PRINT CAT2_05 4000 1//Point your gun at the staff to keep them scared and under control.
		PRINT CAT2_06 8000 1//Don't let them put their hands down or they may set off an alarm.
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		CLEAR_CHAR_TASKS_IMMEDIATELY catalina
		SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_UNARMED
		TASK_PLAY_ANIM catalina CAT_SAFE_ROB ROB_BANK 1000.0 TRUE FALSE FALSE FALSE -2
		mission_timer = game_timer + 2000
		++ mission_flag
		LVAR_FLOAT hand_drop_speed
		hand_drop_speed = 0.0015
	    SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE 2312.5779 -7.4347 25.7500 4.5 man_safenew TRUE
    	SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE 2312.5779 -7.4347 25.7500 4.5 kev_safe TRUE
	ENDIF
ENDIF

IF mission_flag > 9
	hand_drop_speed += 0.000025
	IF NOT IS_CHAR_DEAD	security1
		IF mission_flag > 10
			GET_SCRIPT_TASK_STATUS security1 TASK_KILL_CHAR_ON_FOOT task_status
			IF task_status = FINISHED_TASK
				FREEZE_CHAR_POSITION security1 FALSE
				TASK_KILL_CHAR_ON_FOOT security1 scplayer
			ENDIF
		ELSE
			IF IS_PLAYER_TARGETTING_CHAR player1 security1
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				security1_scared +=@ 0.02
			ELSE
				security1_scared -=@ hand_drop_speed
			ENDIF
			IF security1_scared > 1.0
				security1_scared = 1.0
			ENDIF
			IF security1_scared < 0.0
				security1_scared = 0.0
				IF mission_flag = 10
					LVAR_INT bank_alarm
					GOSUB alarm_off
   					mission_flag = 11
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS security1 TASK_PLAY_ANIM task_status
			IF task_status = FINISHED_TASK
				//security1_scared += 10.0
				//TASK_HANDS_UP security1 100000
				TASK_PLAY_ANIM security1 SHP_HANDSUP_SCR ROB_BANK 4.0 FALSE FALSE FALSE FALSE 0
			ELSE
				IF IS_CHAR_PLAYING_ANIM security1 SHP_HANDSUP_SCR
					SET_CHAR_ANIM_PLAYING_FLAG security1 SHP_HANDSUP_SCR FALSE
					SET_CHAR_ANIM_CURRENT_TIME security1 SHP_HANDSUP_SCR security1_scared
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF mission_flag = 10
			
			GOSUB alarm_off

			mission_flag = 11
		ENDIF
		MARK_CHAR_AS_NO_LONGER_NEEDED security1
	ENDIF
	
	IF NOT IS_CHAR_DEAD	worker1
		IF mission_flag > 10
			GET_SCRIPT_TASK_STATUS worker1 TASK_DUCK task_status
			IF task_status = FINISHED_TASK
				FREEZE_CHAR_POSITION worker1 FALSE
				TASK_DUCK worker1 9999999999
			ENDIF
		ELSE
			IF IS_PLAYER_TARGETTING_CHAR player1 worker1
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				worker1_scared +=@ 0.023
			ELSE
				worker1_scared -=@ hand_drop_speed 
			ENDIF
			IF worker1_scared > 1.0
				worker1_scared = 1.0
			ENDIF
			IF worker1_scared < 0.0
				worker1_scared = 0.0
				IF mission_flag = 10
				 					GOSUB alarm_off
					mission_flag = 11
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS worker1 TASK_PLAY_ANIM task_status
			IF task_status = FINISHED_TASK
				//worker1_scared += 10.0
				//TASK_HANDS_UP worker1 100000
				TASK_PLAY_ANIM worker1 SHP_HANDSUP_SCR ROB_BANK 4.0 FALSE FALSE FALSE FALSE 0
			ELSE
				IF IS_CHAR_PLAYING_ANIM worker1 SHP_HANDSUP_SCR
					SET_CHAR_ANIM_PLAYING_FLAG worker1 SHP_HANDSUP_SCR FALSE
					SET_CHAR_ANIM_CURRENT_TIME worker1 SHP_HANDSUP_SCR worker1_scared
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF mission_flag = 10
			GOSUB alarm_off
			mission_flag = 11
		ENDIF
		MARK_CHAR_AS_NO_LONGER_NEEDED worker1
	ENDIF
	IF NOT IS_CHAR_DEAD	worker2
		IF mission_flag > 10
			GET_SCRIPT_TASK_STATUS worker2 TASK_DUCK task_status
			IF task_status = FINISHED_TASK
				FREEZE_CHAR_POSITION worker2 FALSE
				TASK_DUCK worker2 9999999999
			ENDIF
		ELSE
			IF IS_PLAYER_TARGETTING_CHAR player1 worker2
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				worker2_scared +=@ 0.026
			ELSE
				worker2_scared -=@ hand_drop_speed
			ENDIF
			IF worker2_scared > 1.0
				worker2_scared = 1.0
			ENDIF
			IF worker2_scared < 0.0
				worker2_scared = 0.0
				IF mission_flag = 10

					GOSUB alarm_off
					mission_flag = 11
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS worker2 TASK_PLAY_ANIM task_status
			IF task_status = FINISHED_TASK
				//worker2_scared += 10.0
				//TASK_HANDS_UP worker2 100000
				TASK_PLAY_ANIM worker2 SHP_HANDSUP_SCR ROB_BANK 4.0 FALSE FALSE FALSE FALSE 0
			ELSE
				IF IS_CHAR_PLAYING_ANIM worker2 SHP_HANDSUP_SCR
					SET_CHAR_ANIM_PLAYING_FLAG worker2 SHP_HANDSUP_SCR FALSE
					SET_CHAR_ANIM_CURRENT_TIME worker2 SHP_HANDSUP_SCR worker2_scared
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF mission_flag = 10
			GOSUB alarm_off
			mission_flag = 11
		ENDIF
		MARK_CHAR_AS_NO_LONGER_NEEDED worker2
	ENDIF
	IF NOT IS_CHAR_DEAD	worker3
		IF mission_flag > 10
			GET_SCRIPT_TASK_STATUS worker3 TASK_DUCK task_status
			IF task_status = FINISHED_TASK
				FREEZE_CHAR_POSITION worker3 FALSE
				TASK_DUCK worker3 9999999999
			ENDIF
		ELSE
			IF IS_PLAYER_TARGETTING_CHAR player1 worker3
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				worker3_scared +=@ 0.025
			ELSE
				worker3_scared -=@ hand_drop_speed
			ENDIF
			IF worker3_scared > 1.0
				worker3_scared = 1.0
			ENDIF
			IF worker3_scared < 0.0
				worker3_scared = 0.0
				IF mission_flag = 10
					GOSUB alarm_off
					mission_flag = 11
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS worker3 TASK_PLAY_ANIM task_status
			IF task_status = FINISHED_TASK
				//worker3_scared += 10.0
				//TASK_HANDS_UP worker3 100000
				TASK_PLAY_ANIM worker3 SHP_HANDSUP_SCR ROB_BANK 4.0 FALSE FALSE FALSE FALSE 0
			ELSE
				IF IS_CHAR_PLAYING_ANIM worker3 SHP_HANDSUP_SCR
					SET_CHAR_ANIM_PLAYING_FLAG worker3 SHP_HANDSUP_SCR FALSE
					SET_CHAR_ANIM_CURRENT_TIME worker3 SHP_HANDSUP_SCR worker3_scared
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF mission_flag = 10

			GOSUB alarm_off
			LVAR_INT cat_anim_time
			cat_anim_time = TIMERA + 500
			mission_flag = 11
		ENDIF
		MARK_CHAR_AS_NO_LONGER_NEEDED worker3
	ENDIF
ENDIF

IF play_audio = 9
	IF NOT IS_CHAR_DEAD catalina
		IF HAS_ANIMATION_LOADED OTB

			LVAR_INT cat2_seq
			GET_SCRIPT_TASK_STATUS catalina PERFORM_SEQUENCE_TASK task_status

			IF task_Status = FINISHED_TASK
				CLEAR_CHAR_TASKS catalina
				OPEN_SEQUENCE_TASK cat2_seq
					TASK_TOGGLE_DUCK -1 FALSE
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
					TASK_PLAY_ANIM -1 wtchrace_cmon OTB 4.0 FALSE FALSE FALSE FALSE 0
					TASK_PLAY_ANIM -1 wtchrace_lose OTB 4.0 FALSE FALSE FALSE FALSE 0
				CLOSE_SEQUENCE_TASK cat2_seq
				PERFORM_SEQUENCE_TASK catalina cat2_seq
				CLEAR_SEQUENCE_TASK cat2_seq
			ENDIF
		ELSE
			REQUEST_ANIMATION OTB
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 11
	REMOVE_ANIMATION ROB_BANK
	
	IF catalina_flag = 4
		IF IS_CHAR_DEAD	security1
			mission_timer = game_timer + 2000
			++ mission_flag
		ENDIF
	ENDIF
ENDIF
IF mission_flag = 12
AND play_audio = 0
	IF mission_timer < game_timer
		DO_FADE 500 FADE_OUT
		REQUEST_MODEL COPCARRU
		REQUEST_MODEL COPBIKE
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 13
	IF  HAS_CAR_RECORDING_BEEN_LOADED 116
	AND HAS_CAR_RECORDING_BEEN_LOADED 117
	AND HAS_CAR_RECORDING_BEEN_LOADED 118
	AND HAS_CAR_RECORDING_BEEN_LOADED 119
	AND HAS_ANIMATION_LOADED MISC
		IF NOT GET_FADING_STATUS
		AND HAS_MODEL_LOADED COPCARRU
		AND HAS_MODEL_LOADED COPBIKE

			REMOVE_ANIMATION OTB
			REMOVE_ANIMATION SHOP

			CLEAR_SMALL_PRINTS
			SET_PLAYER_CONTROL player1 OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
			SET_NEAR_CLIP 0.1
			REMOVE_SOUND bank_alarm
		
			LVAR_INT copcar1 //cop1 cop2
			CREATE_CAR COPCARRU 2302.1809 6.6 25.4766 copcar1
			SET_CAR_HEADING copcar1 90.0
			LOCK_CAR_DOORS copcar1 CARLOCK_LOCKOUT_PLAYER_ONLY
			START_PLAYBACK_RECORDED_CAR	copcar1	116
			SET_PLAYBACK_SPEED copcar1 0.0
			//LVAR_INT blip1 blip2 blip3 blip4
			//ADD_BLIP_FOR_CAR copcar1 blip1

			LVAR_INT copcar2 copcar2_cop1
			CREATE_CAR COPBIKE 2348.6582 5.9060 25.3359 copcar2
			SET_CAR_HEADING copcar2 359.5453
			START_PLAYBACK_RECORDED_CAR	copcar2	117
			SET_PLAYBACK_SPEED copcar2 0.0
			CREATE_CHAR_INSIDE_CAR copcar2 PEDTYPE_CIVMALE CSHER copcar2_cop1
			SET_CHAR_DECISION_MAKER copcar2_cop1 bank_peds_decision_maker
			//SWITCH_CAR_SIREN copcar2 ON
			//ADD_BLIP_FOR_CAR copcar2 blip2
			//WARP_CHAR_INTO_CAR scplayer copcar2
			//START_RECORDING_CAR copcar2 117

			LVAR_INT copcar3 copcar3_cop1
			CREATE_CAR COPBIKE 2264.9182 37.5190 25.3359 copcar3
			SET_CAR_HEADING copcar3 268.8078
			START_PLAYBACK_RECORDED_CAR	copcar3	118
			SET_PLAYBACK_SPEED copcar3 0.0
			CREATE_CHAR_INSIDE_CAR copcar3 PEDTYPE_CIVMALE CSHER copcar3_cop1
			SET_CHAR_DECISION_MAKER copcar3_cop1 bank_peds_decision_maker
			//SWITCH_CAR_SIREN copcar3 ON
			//ADD_BLIP_FOR_CAR copcar3 blip3
			//WARP_CHAR_INTO_CAR scplayer copcar3
			//START_RECORDING_CAR copcar3 118

			LVAR_INT copcar4 copcar4_cop1
			CREATE_CAR COPCARRU 2278.4771 89.2844 25.3378 copcar4
			SET_CAR_HEADING copcar4 269.8063
			START_PLAYBACK_RECORDED_CAR	copcar4	119
			SET_PLAYBACK_SPEED copcar4 0.0
			CREATE_CHAR_INSIDE_CAR copcar4 PEDTYPE_CIVMALE CSHER copcar4_cop1
			SET_CHAR_DECISION_MAKER copcar4_cop1 bank_peds_decision_maker
			SWITCH_CAR_SIREN copcar4 ON
//			LOCK_CAR_DOORS copcar4 CARLOCK_UNLOCKED
			//ADD_BLIP_FOR_CAR copcar4 blip4
			//WARP_CHAR_INTO_CAR scplayer copcar4
			//START_RECORDING_CAR copcar4 119
			
			LVAR_INT copcar1_cop1 copcar1_cop2
			CREATE_CHAR PEDTYPE_CIVMALE CSHER 2328.5645 1.8673 25.5693 copcar1_cop1//ON RIGHT
			SET_CHAR_HEADING copcar1_cop1 0.0

			OPEN_SEQUENCE_TASK sequence_task
			TASK_PLAY_ANIM -1 SEAT_IDLE PED 1000.0 TRUE FALSE FALSE TRUE 5000//3000
			TASK_PLAY_ANIM -1 Seat_talk_01 MISC 4.0 FALSE FALSE FALSE FALSE 0
			TASK_PLAY_ANIM -1 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE 10000
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK copcar1_cop1 sequence_task
			CLEAR_SEQUENCE_TASK sequence_task

			// fleeing ped
			LVAR_INT c2_fleeing_ped
			CREATE_CHAR PEDTYPE_CIVMALE SWMYRI 2336.6794 -8.2464 25.4766 c2_fleeing_ped
			MARK_MODEL_AS_NO_LONGER_NEEDED SWMYRI
			TASK_SMART_FLEE_POINT c2_fleeing_ped 2336.3994 -18.3093 25.4766 30.0 8000



			CREATE_CHAR PEDTYPE_CIVMALE CSHER 2328.4763 2.3077 25.5693 copcar1_cop2
			SET_CHAR_DECISION_MAKER copcar1_cop2 bank_peds_decision_maker
			SET_CHAR_HEADING copcar1_cop2 180.0 
			//TASK_PLAY_ANIM copcar1_cop2 SEAT_WATCH MISC 4.0 TRUE FALSE FALSE TRUE 60000

			OPEN_SEQUENCE_TASK sequence_task
			TASK_PLAY_ANIM -1 SEAT_IDLE PED 4.0 TRUE FALSE FALSE TRUE 9200
			TASK_PLAY_ANIM -1 Seat_talk_02 MISC 1000.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM -1 SEAT_WATCH MISC 4.0 FALSE FALSE FALSE FALSE 0
			TASK_PLAY_ANIM -1 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE 10000
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK copcar1_cop2 sequence_task
			CLEAR_SEQUENCE_TASK sequence_task

			mission_timer = TIMERA + 1000
			++ mission_flag
		ELSE
	   		REQUEST_MODEL COPCARRU
			REQUEST_MODEL COPBIKE
		ENDIF
	ELSE
		REQUEST_CAR_RECORDING 116
		REQUEST_CAR_RECORDING 117
		REQUEST_CAR_RECORDING 118
		REQUEST_CAR_RECORDING 119
		REQUEST_ANIMATION MISC
	ENDIF
ELSE
	IF mission_flag = 15
		IF TIMERA > mission_timer 
			SET_FIXED_CAMERA_POSITION 2327.5833 1.7960 26.5414 0.0 0.0 0.0 //cut of cops in pizza shop
			POINT_CAMERA_AT_POINT 2328.5378 2.0688 26.6612 JUMP_CUT
			LOAD_SCENE_IN_DIRECTION 2328.5378 2.0688 26.6612 270.0
		 
			DO_FADE 500 FADE_IN
			skip_buttons_pressed = -1
			++ mission_flag
		ENDIF
	ENDIF
	IF mission_flag = 14  //LET THE INITIAL SITTING ANIMATION HAVE A COUPLE OF FRAMES TO GET INTO POSITION
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag < 25
	IF mission_flag > 15
	OR mission_flag = -1
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF skip_buttons_pressed = 0
				DO_FADE 500 FADE_OUT
				mission_flag = -1

				++ skip_buttons_pressed
			ENDIF
		ELSE
			IF skip_buttons_pressed = -1
				++ skip_buttons_pressed
			ENDIF
		ENDIF
		IF skip_buttons_pressed = 1
			IF NOT GET_FADING_STATUS
				audio_flag = 5 // clear audio for cut skip
				//SET COPS POSITIONS
				IF NOT IS_CHAR_DEAD	copcar1_cop1
					CLEAR_CHAR_TASKS copcar1_cop1
					GIVE_WEAPON_TO_CHAR copcar1_cop1 WEAPONTYPE_SHOTGUN 9999
					IF IS_CHAR_IN_ANY_CAR copcar1_cop1
						WARP_CHAR_FROM_CAR_TO_COORD	copcar1_cop1 2292.5305 -19.1915 25.3437
					ELSE
						SET_CHAR_COORDINATES copcar1_cop1 2292.5305 -19.1915 25.3437
					ENDIF
					TASK_AIM_GUN_AT_COORD copcar1_cop1 2304.1372 -16.5910 27.2760 15000
				ENDIF

				IF NOT IS_CHAR_DEAD	copcar1_cop2
					CLEAR_CHAR_TASKS copcar1_cop2
					GIVE_WEAPON_TO_CHAR copcar1_cop2 WEAPONTYPE_SHOTGUN 9999
					IF IS_CHAR_IN_ANY_CAR copcar1_cop2
						WARP_CHAR_FROM_CAR_TO_COORD	copcar1_cop2 2297.0220 -19.6483 25.3359
					ELSE
						SET_CHAR_COORDINATES copcar1_cop2 2297.0220 -19.6483 25.3359 //PASSENGER IN COPCAR1 GOTO AND AIMS
					ENDIF
					TASK_AIM_GUN_AT_COORD copcar1_cop2 2304.1372 -16.5910 27.2760 15000
				ENDIF

				IF NOT IS_CHAR_DEAD	copcar2_cop1
					CLEAR_CHAR_TASKS copcar2_cop1
					GIVE_WEAPON_TO_CHAR copcar2_cop1 WEAPONTYPE_SHOTGUN 9999
					IF IS_CHAR_IN_ANY_CAR copcar2_cop1
						WARP_CHAR_FROM_CAR_TO_COORD	copcar2_cop1 2303.1191 -13.7964 25.4766
					ELSE
						SET_CHAR_COORDINATES copcar2_cop1 2303.1191 -13.7964 25.4766 //GUY ON BIKE BY BANK COVERS THE DOOR
					ENDIF
					SET_CHAR_HEADING copcar2_cop1	119.4845
				ENDIF

				IF NOT IS_CHAR_DEAD	copcar3_cop1
					CLEAR_CHAR_TASKS copcar3_cop1
					GIVE_WEAPON_TO_CHAR copcar3_cop1 WEAPONTYPE_SHOTGUN 9999
					IF IS_CHAR_IN_ANY_CAR copcar3_cop1
						WARP_CHAR_FROM_CAR_TO_COORD	copcar3_cop1 2297.2437 -16.8148 25.3328
					ELSE
						SET_CHAR_COORDINATES copcar3_cop1 2297.2437 -16.8148 25.3328 //GUY ON BIKE BY CAR GOTO AND DUCK AND AIM
					ENDIF
					TASK_AIM_GUN_AT_COORD copcar3_cop1 2304.1372 -16.5910 27.2760 15000
				ENDIF

				IF NOT IS_CAR_DEAD copcar1
					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR copcar1
//					SET_PLAYBACK_SPEED copcar1 1.0
//					STOP_PLAYBACK_RECORDED_CAR copcar1
//					FREEZE_CAR_POSITION copcar1 FALSE
//					DELETE_CAR copcar1
				ENDIF
				IF NOT IS_CAR_DEAD copcar2
					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR copcar2
//					SET_PLAYBACK_SPEED copcar2 1.0 
//					STOP_PLAYBACK_RECORDED_CAR copcar2
//					FREEZE_CAR_POSITION copcar2 FALSE
// 					DELETE_CAR copcar2
				ENDIF
				IF NOT IS_CAR_DEAD copcar3
					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR copcar3
//					SET_PLAYBACK_SPEED copcar3 1.0
//					STOP_PLAYBACK_RECORDED_CAR copcar3
//					FREEZE_CAR_POSITION copcar3 FALSE
				ENDIF
				IF NOT IS_CAR_DEAD copcar4
// ############################## Something odd happening to this car causing it to spin violently when the cutscene has skipped.
// ############################## Following measures prevent it.

					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR copcar4
//					SET_PLAYBACK_SPEED copcar4 1.0
//					STOP_PLAYBACK_RECORDED_CAR copcar4
//					FREEZE_CAR_POSITION copcar4 FALSE


					SET_CAR_COORDINATES copcar4 2288.7573 -11.9521 25.4766 
					SET_CAR_HEADING copcar4 142.4622 
					SET_CAR_FORWARD_SPEED copcar4 0.0
					SET_CAR_ROTATION_VELOCITY copcar4 0.0 0.0 0.0
				ENDIF

				++ skip_buttons_pressed
				mission_flag = 25
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 16
	IF mission_timer < game_timer
	AND play_audio = 0
//		PRINT CAT2_07 5000 1//POLICE RADIO: Attention all cars. Bank robbery in progress on Soandso street.
		play_audio = 39
		mission_timer = game_timer + 4000
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 17
	IF mission_timer < game_timer
//		PRINT_NOW CAT2_08 5000 1//Shit, I just bought another donut!  Don't criminals have any consideration?
		IF play_audio = 0
			audio_actor[3] = copcar1_cop1
			audio_actor[4] = copcar1_cop2
			play_audio_global = 1
			play_audio = 11

			mission_timer = game_timer + 2800
			++ mission_flag
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 18
AND play_audio = 0
	IF mission_timer < game_timer
//		PRINT_NOW CAT2_09 5000 1//We have a few hours before we need to collect that bribe, we may as well take a look.
		play_audio = 12
		mission_timer = game_timer + 3500
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 19
AND play_audio = 0
	IF mission_timer < game_timer
		IF NOT IS_CAR_DEAD copcar1
			IF NOT IS_CHAR_DEAD	copcar1_cop1
				CLEAR_CHAR_TASKS copcar1_cop1
				SET_CHAR_COORDINATES copcar1_cop1 2345.9895 5.6379 25.3359
				SET_CHAR_HEADING copcar1_cop1 276.5795
				TASK_ENTER_CAR_AS_DRIVER copcar1_cop1 copcar1 5000
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD copcar1
			IF NOT IS_CHAR_DEAD	copcar1_cop2
				CLEAR_CHAR_TASKS copcar1_cop2
				SET_CHAR_COORDINATES copcar1_cop2 2349.0730 9.3357 25.4766
				SET_CHAR_HEADING copcar1_cop2 282.9661
				TASK_ENTER_CAR_AS_PASSENGER copcar1_cop2 copcar1 5000 0
			ENDIF
		ENDIF
		CLEAR_SMALL_PRINTS
//		SWITCH_STREAMING OFF

 

		SET_FIXED_CAMERA_POSITION 2353.4939 10.2912 29.0217 0.0 0.0 0.0 //CUT OF COPS GETTING INTO THEIR VAN
		POINT_CAMERA_AT_POINT 2352.7229 9.7997 28.6169 JUMP_CUT


//		SET_FIXED_CAMERA_POSITION 2353.0544 12.1400 28.0091 0.0 0.0 0.0 //CUT OF COPS GETTING INTO THEIR VAN
//		POINT_CAMERA_AT_POINT 2352.2913 11.5119 27.8575 JUMP_CUT
		DELETE_CHAR c2_fleeing_ped
		mission_timer = game_timer + 1000
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 20
	IF mission_timer < game_timer
		IF NOT IS_CAR_DEAD copcar2
			SET_PLAYBACK_SPEED copcar2 1.0
		ENDIF
		IF NOT IS_CAR_DEAD copcar3
			SET_PLAYBACK_SPEED copcar3 1.0
		ENDIF

		mission_timer = game_timer + 2500
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 21
	IF NOT IS_CAR_DEAD copcar1
		IF mission_timer < game_timer
			SET_PLAYBACK_SPEED copcar1 1.0
			SET_INTERPOLATION_PARAMETERS 0.0 5500
			SET_FIXED_CAMERA_POSITION 2353.0544 12.1400 28.0091 0.0 0.0 0.0 //CUT OF COPS GETTING INTO THEIR VAN
			POINT_CAMERA_AT_CAR copcar1 FIXED INTERPOLATION
			SWITCH_CAR_SIREN copcar1 ON
			mission_timer = game_timer + 5500
			mission_flag = 23
		ENDIF
	ELSE
		mission_timer = game_timer + 5500
		mission_flag = 23
	ENDIF
ENDIF

IF mission_flag = 22
	IF mission_timer < game_timer
		SET_FIXED_CAMERA_POSITION 2297.5618 11.3784 29.2857 0.0 0.0 0.0 //CUT OF COP BIKES
		IF NOT IS_CAR_DEAD copcar4
			POINT_CAMERA_AT_CAR copcar4 FIXED JUMP_CUT
		ELSE
			POINT_CAMERA_AT_POINT 2297.3772 12.3375 29.0710 JUMP_CUT
		ENDIF
		mission_timer = game_timer + 6500
		++ mission_flag
	ENDIF
ENDIF
IF mission_flag = 23
	IF mission_timer < game_timer
		play_audio = 13
		play_audio_for = 3

		IF NOT IS_CAR_DEAD copcar1
			IF IS_PLAYBACK_GOING_ON_FOR_CAR copcar1
				STOP_PLAYBACK_RECORDED_CAR copcar1
				SET_CAR_COORDINATES copcar1 2297.2664 -27.4791 25.3359
				SET_CAR_HEADING copcar1 23.0
				SET_CAR_FORWARD_SPEED copcar1 17.0
				IF NOT IS_CHAR_DEAD copcar1_cop1
					GIVE_WEAPON_TO_CHAR	copcar1_cop1 WEAPONTYPE_PISTOL 9999
					OPEN_SEQUENCE_TASK sequence_task
						TASK_CAR_DRIVE_TO_COORD -1 copcar1 2295.6117 -23.6996 25.3437  17.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_CAR_TEMP_ACTION -1 copcar1 TEMPACT_HANDBRAKESTRAIGHT 1000
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 2292.5305 -19.1915 25.3437	PEDMOVE_RUN 10000 //COPCAR1 DRIVER GOTO AND AIM
						TASK_AIM_GUN_AT_COORD -1 2304.1372 -16.5910 27.2760	5000
					CLOSE_SEQUENCE_TASK sequence_task

					PERFORM_SEQUENCE_TASK copcar1_cop1 sequence_task
					CLEAR_SEQUENCE_TASK sequence_task
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD copcar1_cop2
			GIVE_WEAPON_TO_CHAR	copcar1_cop2 WEAPONTYPE_PISTOL 9999
			OPEN_SEQUENCE_TASK sequence_task
				TASK_PAUSE -1 1000
				TASK_LEAVE_ANY_CAR -1
				TASK_GO_STRAIGHT_TO_COORD -1 2296.7065 -15.3321 25.3279	PEDMOVE_RUN 10000 //COPCAR1 DRIVER GOTO AND AIM
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_AIM_GUN_AT_COORD -1 2304.1372 -16.5910 27.2760	5000
			CLOSE_SEQUENCE_TASK sequence_task

			PERFORM_SEQUENCE_TASK copcar1_cop2 sequence_task
			CLEAR_SEQUENCE_TASK sequence_task
		ENDIF

		IF NOT IS_CAR_DEAD copcar2
			IF IS_PLAYBACK_GOING_ON_FOR_CAR copcar2
				STOP_PLAYBACK_RECORDED_CAR copcar2
				SET_CAR_COORDINATES copcar2 2305.8347 -30.7209 25.3382
				SET_CAR_HEADING copcar2 33.0
				SET_CAR_FORWARD_SPEED copcar2 17.0

				IF NOT IS_CHAR_DEAD copcar2_cop1
				GIVE_WEAPON_TO_CHAR	copcar2_cop1 WEAPONTYPE_PISTOL 9999
					OPEN_SEQUENCE_TASK sequence_task
						TASK_CAR_DRIVE_TO_COORD -1 copcar2 2302.1902 -21.2635 25.4844  17.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_CAR_TEMP_ACTION -1 copcar2 TEMPACT_HANDBRAKESTRAIGHT 1000
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1 2297.2437 -16.8148 25.3328 PEDMOVE_RUN 10000 //GUY ON BIKE BY BANK COVERS THE DOOR
						TASK_ACHIEVE_HEADING -1	291.4845
					CLOSE_SEQUENCE_TASK sequence_task

					PERFORM_SEQUENCE_TASK copcar2_cop1 sequence_task
					CLEAR_SEQUENCE_TASK sequence_task
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD copcar3
			IF IS_PLAYBACK_GOING_ON_FOR_CAR copcar3
				STOP_PLAYBACK_RECORDED_CAR copcar3
				SET_CAR_COORDINATES copcar3 2294.4912 -2.6373 25.3359
				SET_CAR_HEADING copcar3 186.0
				SET_CAR_FORWARD_SPEED copcar3 17.0

				IF NOT IS_CHAR_DEAD copcar3_cop1
					GIVE_WEAPON_TO_CHAR	copcar3_cop1 WEAPONTYPE_PISTOL 9999
					OPEN_SEQUENCE_TASK sequence_task
						TASK_CAR_DRIVE_TO_COORD -1 copcar3 2298.0938 -10.0818 25.3285  17.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH
						TASK_CAR_TEMP_ACTION -1 copcar3 TEMPACT_HANDBRAKESTRAIGHT 1000
						TASK_LEAVE_ANY_CAR -1
						TASK_GO_STRAIGHT_TO_COORD -1	2303.1191 -13.7964 25.4766 PEDMOVE_RUN 10000 //GUY ON BIKE BY CAR GOTO AND DUCK AND AIM
						TASK_AIM_GUN_AT_COORD -1 2304.1372 -16.5910 27.2760	5000
					CLOSE_SEQUENCE_TASK sequence_task

					PERFORM_SEQUENCE_TASK copcar3_cop1 sequence_task
					CLEAR_SEQUENCE_TASK sequence_task
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD copcar4
			SET_PLAYBACK_SPEED copcar4 1.0
		ENDIF
					

		SET_FIXED_CAMERA_POSITION 2288.5771 -15.1784 27.8832 0.0 0.0 0.0 //CUT OF COPS GETTING OUT OF THIR VEHICLES
		POINT_CAMERA_AT_POINT 2289.5530 -15.2742 27.6869 JUMP_CUT


		mission_timer = game_timer + 6000
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 24
AND play_audio = 0
	IF mission_timer < game_timer
		DO_FADE 500 FADE_OUT
		REQUEST_MODEL ENFORCER
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 25
	IF NOT GET_FADING_STATUS
	AND HAS_MODEL_LOADED ENFORCER
		SWITCH_ROADS_OFF 2344.5178 36.1468 36.0359 2269.7183 -44.3760 23.0569
		REMOVE_ANIMATION MISC
		REQUEST_ANIMATION GANGS
		LVAR_INT alley_copcar1
		CREATE_CAR ENFORCER 2302.1809 6.6 25.4766 alley_copcar1
		SET_CAR_HEADING alley_copcar1 90.0
		FREEZE_CAR_POSITION	alley_copcar1 TRUE

		LVAR_INT alley_copcar2
		CREATE_CAR ENFORCER 2337.9 23.4 25.4766 alley_copcar2
		SET_CAR_HEADING alley_copcar2 270.0
		FREEZE_CAR_POSITION	alley_copcar2 TRUE

		LVAR_INT atm_flag[4]
		LVAR_INT c
		c = 0
		WHILE c < 3
			atm_flag[c] = 0
			LVAR_INT atm_blip[4]
			IF DOES_OBJECT_EXIST atm[c]
				ADD_BLIP_FOR_OBJECT	atm[c] atm_blip[c]
				MAKE_OBJECT_TARGETTABLE atm_static[c] TRUE
				SET_OBJECT_HEALTH atm_static[c] 1000
			ENDIF
			++ c
		ENDWHILE

		IF NOT IS_CHAR_DEAD c2_fleeing_ped
			MARK_CHAR_AS_NO_LONGER_NEEDED c2_fleeing_ped
		ENDIF

//		SWITCH_STREAMING ON



		SET_PLAYER_CONTROL player1 ON
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
//		ADD_CONTINUOUS_SOUND 2320.0427 -8.5501 27.2047 SOUND_BANK_ALARM_LOOP bank_alarm
		DO_FADE 500 FADE_IN
		IF NOT IS_CHAR_DEAD copcar1_cop1
//			SET_CHAR_RELATIONSHIP copcar1_cop1 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
			DELETE_CHAR copcar1_cop1
		ENDIF
		IF NOT IS_CHAR_DEAD copcar1_cop2
//			SET_CHAR_RELATIONSHIP copcar1_cop2 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
			DELETE_CHAR copcar1_cop2
		ENDIF
		IF NOT IS_CHAR_DEAD copcar2_cop1
//			SET_CHAR_RELATIONSHIP copcar2_cop1 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
			DELETE_CHAR copcar2_cop1
		ENDIF
		IF NOT IS_CHAR_DEAD copcar3_cop1
//			SET_CHAR_RELATIONSHIP copcar3_cop1 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
			DELETE_CHAR copcar3_cop1
		ENDIF

		DELETE_CAR copcar1
		DELETE_CAR copcar2
		DELETE_CAR copcar3
		DELETE_CAR copcar4

		SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN

		SET_CHAR_COORDINATES catalina 2312.2083 -1.9564 25.7422
		SET_CHAR_HEADING catalina 199.0
		
		++ mission_flag
	ELSE
		REQUEST_MODEL ENFORCER
	ENDIF
ENDIF

IF mission_flag = 26
	IF NOT GET_FADING_STATUS
	AND audio_flag = 1
	AND HAS_ANIMATION_LOADED GANGS
//		PRINT_NOW CAT2_10 5000 1 //I need you to help me smash the ATMs to get the last of the cash.
		play_audio = 16
		play_audio_for = 3
		IF NOT IS_CHAR_DEAD catalina
			TASK_PLAY_ANIM catalina prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE FALSE 0
		ENDIF
		//MAKE CATALINA COLLECT CASH TOO
//		set_object_collision_damage_effect atm[0] true
//		set_object_collision_damage_effect atm[1] true
//		set_object_collision_damage_effect atm[2] true
		set_object_collision_damage_effect atm_static[0] FALSE
		set_object_collision_damage_effect atm_static[1] TRUE
		set_object_collision_damage_effect atm_static[2] TRUE
//		set_object_collision_damage_effect atm[3] true
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 27

	LVAR_INT cat_task_progress

	GET_SCRIPT_TASK_STATUS catalina TASK_PLAY_ANIM cat_task_progress
	IF cat_task_progress = FINISHED_TASK
		IF HAS_ANIMATION_LOADED gangs
			REMOVE_ANIMATION gangs
		ENDIF
	ENDIF

	c = 0


	WHILE c < 3
		IF DOES_OBJECT_EXIST atm[c]
			IF atm_flag[c] = 0
				GET_OBJECT_HEALTH atm_static[c] temp_int
//				IF c = 0
//					VAR_INT thisone
//					VIEW_INTEGER_VARIABLE thisone thisone
//					thisone = temp_int
//				ENDIF
				GET_OBJECT_COORDINATES atm[c] x y z

				LVAR_FLOAT bx1 by1 bz1 bx2 by2 bz2

				bx1 = x - 1.0
				bx2 = x + 1.0
				by1 = y - 1.0
				by2 = y + 1.0
				bz1 = z - 1.0
				bz2 = z + 1.0
	
				IF IS_EXPLOSION_IN_AREA -1 bx1 by1 bz1 bx2 by2 bz2
					SET_OBJECT_HEALTH broken_atm[c] 0
					temp_int = 0					
				ENDIF


				IF temp_int < 500					
					
					GET_OBJECT_HEADING atm[c] h
					BREAK_OBJECT atm[c] TRUE
					LVAR_INT broken_atm[4]					

					SET_OBJECT_COORDINATES broken_atm[c] x y z

					SET_OBJECT_VISIBLE broken_atm[c] TRUE
					SET_OBJECT_HEADING broken_atm[c] h
					SET_OBJECT_COLLISION broken_atm[c] FALSE
					SET_OBJECT_HEALTH broken_atm[c] 150
					++ atm_flag[c]
				ENDIF
			ENDIF
		ENDIF

		IF DOES_OBJECT_EXIST broken_atm[c]
			IF atm_flag[c] = 1
				GET_OBJECT_HEALTH atm_static[c] temp_int
				IF temp_int < 1
					REMOVE_BLIP	atm_blip[c]
					DELETE_OBJECT atm_static[c]
					
					IF c = 0
						CREATE_OBJECT_NO_OFFSET KMB_ATM3 2310.3669 -17.0 26.414 atm_static[0]
						set_object_collision_damage_effect atm_static[0] FALSE
						SET_OBJECT_HEADING atm_static[0] 180.0
					ENDIF

					IF c = 1
						CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.6890 -17.0 26.414 atm_static[1]
						set_object_collision_damage_effect atm_static[1] FALSE
						SET_OBJECT_HEADING atm_static[1] 180.0
					ENDIF

					IF c = 2
						CREATE_OBJECT_NO_OFFSET KMB_ATM3 2313.3184 -0.1206 26.414 atm_static[2]
						set_object_collision_damage_effect atm_static[2] FALSE
					ENDIF




					MARK_OBJECT_AS_NO_LONGER_NEEDED	atm[c]
					
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS broken_atm[c] 0.0 -1.0 0.0 x y z
					MAKE_OBJECT_TARGETTABLE atm_static[c] FALSE
					SET_OBJECT_VISIBLE atm_static[c] TRUE
					BREAK_OBJECT broken_atm[c] FALSE
					
					LVAR_INT atm_cash[4]
					//CREATE_PICKUP MONEY PICKUP_ONCE x y z atm_cash[c]
					CREATE_MONEY_PICKUP X Y Z 0 TRUE atm_cash[c]
					//CREATE_MONEY_PICKUP x y z 10000 atm_cash[c]
					ADD_BLIP_FOR_PICKUP	atm_cash[c]	atm_blip[c]
					++ atm_flag[c]
				ENDIF
			ENDIF
		ENDIF

		IF atm_flag[c] = 2
			IF HAS_PICKUP_BEEN_COLLECTED atm_cash[c]
				MARK_OBJECT_AS_NO_LONGER_NEEDED	broken_atm[c]
//				ADD_SCORE player1 10000
				REMOVE_BLIP	atm_blip[c]
				++ atm_flag[c]
			ENDIF
		ENDIF
		++ c
	ENDWHILE

	IF atm_flag[0] = 3
	AND	atm_flag[1] = 3
	AND	atm_flag[2] = 3
		

//		set_object_collision_damage_effect door[0] true

		set_object_collision_damage_effect pc_door[1] true

//		set_object_collision_damage_effect door[2] true
//		set_object_collision_damage_effect door[3] true
		++ mission_flag
		IF NOT IS_CHAR_DEAD copcar1_cop1
			SET_CHAR_RELATIONSHIP copcar1_cop1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		ENDIF
		IF NOT IS_CHAR_DEAD copcar1_cop2
			SET_CHAR_RELATIONSHIP copcar1_cop2 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		ENDIF
		IF NOT IS_CHAR_DEAD copcar2_cop1
			SET_CHAR_RELATIONSHIP copcar2_cop1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		ENDIF
		IF NOT IS_CHAR_DEAD copcar3_cop1
			SET_CHAR_RELATIONSHIP copcar3_cop1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		ENDIF
//		DELETE_CHAR alley_cop1
//		DELETE_CHAR alley_cop2
		SWITCH_ROADS_ON 2344.5178 36.1468 36.0359 2269.7183 -44.3760 23.0569
		SET_CHAR_DECISION_MAKER catalina cat_own_dec //newline
		
	ENDIF
ENDIF

IF mission_flag = 28
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM1
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM2
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM3
MARK_MODEL_AS_NO_LONGER_NEEDED MTSAFE
MARK_MODEL_AS_NO_LONGER_NEEDED CR_DOOR_01
MARK_MODEL_AS_NO_LONGER_NEEDED CR_DOOR_03
mission_flag = 29
ENDIF

IF mission_flag = 31
	IF  HAS_CAR_RECORDING_BEEN_LOADED 123
	AND HAS_CAR_RECORDING_BEEN_LOADED 124
		IF catalina_advance_flag = 10
			IF NOT IS_CHAR_DEAD scplayer
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2315.5576 70.7181 31.4998 11.4 8.4 6.0 0//LOCATE FOR PLAYER AND CATALINA TO TRIGGER COPS ARRIVING ON BIKES AT END OF ALLEY
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cat_own_dec EVENT_ACQUAINTANCE_PED_HATE
					//TRIGGER CUT SCENE OF BIKES ARRIVING
					SET_PLAYER_CONTROL player1 OFF
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
					SET_EVERYONE_IGNORE_PLAYER player1 TRUE
					SWITCH_WIDESCREEN ON
					SET_ALL_CARS_CAN_BE_DAMAGED FALSE
					
					CLEAR_AREA 2321.2180 69.1035 26.7314 25.0 TRUE
					
					SET_FIXED_CAMERA_POSITION 2324.8989 66.4572 25.8911 0.0 0.0 0.0	//CAMERA CUT OF THE COPBIKES ARRIVING AT THE END OF THE ALLEY
					POINT_CAMERA_AT_POINT 2324.1917 67.1570 25.9911 JUMP_CUT

					DELETE_CAR copcar2
					DELETE_CAR copcar3
					DELETE_CHAR copcar2_cop1
					DELETE_CHAR copcar3_cop1
					
					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD	scplayer 2319.7930 61.6571 25.4769
					ELSE
						SET_CHAR_COORDINATES scplayer 2319.7930 61.6571 25.4769
					ENDIF
					SET_CHAR_HEADING scplayer 5.0

					REMOVE_CHAR_FROM_GROUP catalina
					IF IS_CHAR_IN_ANY_CAR catalina
						WARP_CHAR_FROM_CAR_TO_COORD	catalina 2318.2424 61.3145 25.4759
					ELSE
						SET_CHAR_COORDINATES catalina 2318.2424 61.3145 25.4759
					ENDIF
					SET_CHAR_HEADING catalina 350.0

					CREATE_CAR COPBIKE 2287.1562 61.9821 25.4766 copcar2
					SET_CAR_HEADING copcar2 322.7361
					CREATE_CHAR_INSIDE_CAR copcar2 PEDTYPE_CIVMALE CSHER copcar2_cop1
					SET_CHAR_DECISION_MAKER copcar2_cop1 bank_peds_decision_maker
					START_PLAYBACK_RECORDED_CAR	copcar2	123
					SET_CAR_PROOFS copcar2 TRUE TRUE TRUE TRUE TRUE
					CREATE_CAR COPBIKE 2289.6289 58.0551 25.3359 copcar3
					SET_CAR_HEADING copcar3 330.5475
					SET_CAR_PROOFS copcar3 TRUE TRUE TRUE TRUE TRUE
					CREATE_CHAR_INSIDE_CAR copcar3 PEDTYPE_CIVMALE CSHER copcar3_cop1
					SET_CHAR_DECISION_MAKER copcar3_cop1 bank_peds_decision_maker
					START_PLAYBACK_RECORDED_CAR	copcar3	124
					++ mission_flag
				ENDIF
			ENDIF
		ENDIF
	ELSE
		REQUEST_CAR_RECORDING 123
		REQUEST_CAR_RECORDING 124
	ENDIF
ENDIF

IF mission_flag = 32
	IF NOT IS_CAR_DEAD copcar2
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	copcar2
			IF NOT IS_CHAR_DEAD	copcar2_cop1
				SET_CAR_ENGINE_ON copcar2 FALSE
				TASK_LEAVE_ANY_CAR copcar2_cop1
				FREEZE_CAR_POSITION copcar2 TRUE
				++ mission_flag
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 33
AND play_audio = 0
	IF NOT IS_CAR_DEAD copcar3
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	copcar3
			IF NOT IS_CHAR_DEAD	copcar3_cop1
				TASK_LEAVE_ANY_CAR	copcar3_cop1
				SET_CAR_ENGINE_ON copcar3 FALSE
				FREEZE_CAR_POSITION copcar3 TRUE
				DO_FADE 500 FADE_OUT
				++ mission_flag
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF mission_flag = 34
	IF NOT GET_FADING_STATUS
		play_audio = 29
		SET_PLAYER_CONTROL player1 ON
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		DO_FADE 500 FADE_IN
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 35
	IF NOT GET_FADING_STATUS
		IF NOT IS_CHAR_DEAD	copcar2_cop1
			GIVE_WEAPON_TO_CHAR	copcar2_cop1 WEAPONTYPE_PISTOL 9999
			TASK_KILL_CHAR_ON_FOOT copcar2_cop1 scplayer
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER copcar2_cop1 TRUE
		ENDIF
		IF NOT IS_CHAR_DEAD	copcar3_cop1
			GIVE_WEAPON_TO_CHAR	copcar3_cop1 WEAPONTYPE_SHOTGUN 9999
			TASK_KILL_CHAR_ON_FOOT copcar3_cop1	scplayer
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER copcar3_cop1 TRUE
		ENDIF
		IF NOT IS_CHAR_DEAD catalina
			TASK_SHOOT_AT_COORD catalina 2322.1826 71.2342 26.5490 -2
		ENDIF
//		PRINT_NOW CAT2_13 5000 1// Kill the cops on the bikes or somesuch
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 36
AND play_audio = 0
	IF NOT GET_FADING_STATUS
		IF IS_CHAR_DEAD	copcar2_cop1
		AND	IS_CHAR_DEAD copcar3_cop1
			
//			IF NOT IS_CAR_DEAD copcar2
//				GET_CAR_COORDINATES	copcar2	x y z
//				GET_CAR_HEADING	copcar2 h
//			ENDIF
//			SAVE_NEWLINE_TO_DEBUG_FILE
//			SAVE_FLOAT_TO_DEBUG_FILE x
//			SAVE_FLOAT_TO_DEBUG_FILE y
//			SAVE_FLOAT_TO_DEBUG_FILE z
//			SAVE_FLOAT_TO_DEBUG_FILE h
//			SAVE_NEWLINE_TO_DEBUG_FILE
//			IF NOT IS_CAR_DEAD copcar3
//				GET_CAR_COORDINATES	copcar3	x y z
//				GET_CAR_HEADING	copcar3 h
//			ENDIF
//			SAVE_NEWLINE_TO_DEBUG_FILE
//			SAVE_FLOAT_TO_DEBUG_FILE x
//			SAVE_FLOAT_TO_DEBUG_FILE y
//			SAVE_FLOAT_TO_DEBUG_FILE z
//			SAVE_FLOAT_TO_DEBUG_FILE h
//			SAVE_NEWLINE_TO_DEBUG_FILE
			
//			PRINT_NOW CAT2_15 5000 1//Lets take their bikes.

			mission_timer = game_timer + 750
			++ mission_flag
		ENDIF
	ENDIF
ENDIF

IF mission_flag = 37
	IF mission_timer < game_timer
//	ELSE
		DO_FADE 500 FADE_OUT
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag < 38
//	IF NOT LOCATE_CHAR_ANY_MEANS_2D SCPLAYER 2318.7539 33.2110 22.0 57.0 0
//		IF go_get_catalina = 0
//			go_get_catalina = 1
//			PRINT CAT2_14 5000 1//You have left catalina behind, go back and get her.
//		ENDIF
//	ELSE
//		IF go_get_catalina = 1 
//			
//		ENDIF
//	ENDIF
ENDIF

somewhere_else:

IF mission_flag = 38
	IF NOT GET_FADING_STATUS

		SET_PLAYER_CONTROL player1 OFF
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SWITCH_WIDESCREEN ON
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE
		
		CLEAR_AREA 2321.2180 69.1035 26.7314 25.0 TRUE
		
		SET_FIXED_CAMERA_POSITION 2325.2913 70.4759 26.8236 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2324.3533 70.7834 26.6641 JUMP_CUT

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_CHAR_TASKS_IMMEDIATELY catalina
		
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD	scplayer 2322.0752 69.6739 25.4784
		ELSE
			SET_CHAR_COORDINATES scplayer 2322.0752 69.6739 25.4784
		ENDIF
		SET_CHAR_HEADING scplayer 325.1885

		REMOVE_CHAR_FROM_GROUP catalina
		IF IS_CHAR_IN_ANY_CAR catalina
			WARP_CHAR_FROM_CAR_TO_COORD	catalina 2321.6304 71.6478 25.4724
		ELSE
			SET_CHAR_COORDINATES catalina 2321.6304 71.6478 25.4724
		ENDIF
		SET_CHAR_HEADING catalina 295.8968

		DELETE_CAR copcar2
		DELETE_CAR copcar3
		DELETE_CHAR copcar2_cop1
		DELETE_CHAR copcar3_cop1

		IF NOT IS_CAR_DEAD alley_copcar1
			MARK_CAR_AS_NO_LONGER_NEEDED alley_copcar1
		ENDIF
		IF NOT IS_CAR_DEAD alley_copcar2
			MARK_CAR_AS_NO_LONGER_NEEDED alley_copcar2
		ENDIF
		IF NOT IS_CAR_DEAD copcar1
			MARK_CAR_AS_NO_LONGER_NEEDED copcar1
		ENDIF
		IF NOT IS_CAR_DEAD copcar2
			MARK_CAR_AS_NO_LONGER_NEEDED copcar2
		ENDIF



		CLEAR_AREA 2320.6343 79.9329 27.9897 100.0 TRUE


		CREATE_CAR COPBIKE 2322.8628 73.0477 26.0378 copcar2
		SET_CAR_HEADING copcar2 34.6853
		CREATE_CAR COPBIKE 2323.1655 70.1374 26.0405 copcar3
		SET_CAR_HEADING copcar3 345.7562
		
		OPEN_SEQUENCE_TASK sequence_task
			TASK_STAND_STILL -1 750
			TASK_ENTER_CAR_AS_DRIVER -1 copcar2 -2
		CLOSE_SEQUENCE_TASK sequence_task
		PERFORM_SEQUENCE_TASK catalina sequence_task
		CLEAR_SEQUENCE_TASK sequence_task

		OPEN_SEQUENCE_TASK sequence_task
			TASK_STAND_STILL -1 500
			TASK_ENTER_CAR_AS_DRIVER -1 copcar3 -2
		CLOSE_SEQUENCE_TASK sequence_task
		PERFORM_SEQUENCE_TASK scplayer sequence_task
		CLEAR_SEQUENCE_TASK sequence_task

		MARK_OBJECT_AS_NO_LONGER_NEEDED broken_atm[0]
		MARK_OBJECT_AS_NO_LONGER_NEEDED broken_atm[1]
		MARK_OBJECT_AS_NO_LONGER_NEEDED broken_atm[2]

		MARK_OBJECT_AS_NO_LONGER_NEEDED atm[0]
		MARK_OBJECT_AS_NO_LONGER_NEEDED atm[1]
		MARK_OBJECT_AS_NO_LONGER_NEEDED atm[2]
		MARK_OBJECT_AS_NO_LONGER_NEEDED atm[3]

		MARK_OBJECT_AS_NO_LONGER_NEEDED atm_static[0]
		MARK_OBJECT_AS_NO_LONGER_NEEDED atm_static[1]
		MARK_OBJECT_AS_NO_LONGER_NEEDED atm_static[2]

		MARK_OBJECT_AS_NO_LONGER_NEEDED safe_col

		MARK_CHAR_AS_NO_LONGER_NEEDED worker1
		MARK_CHAR_AS_NO_LONGER_NEEDED worker2
		MARK_CHAR_AS_NO_LONGER_NEEDED worker3
		MARK_CHAR_AS_NO_LONGER_NEEDED security1

		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[0]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[1]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[2]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[3]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[4]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[5]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[6]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[7]
		MARK_CHAR_AS_NO_LONGER_NEEDED c2_alley_cop[8]

		DO_FADE 500 FADE_IN
		mission_timer = game_timer + 2500
		++ mission_flag
		play_audio = 30
		play_audio_for = 2
		play_delay = TIMERA + 400
	ENDIF
ENDIF

IF mission_flag = 39
	IF NOT IS_CAR_DEAD copcar2
		IF IS_CHAR_SITTING_IN_CAR catalina copcar2
			IF mission_timer < game_timer
			AND play_audio = 0
				DO_FADE 500 FADE_OUT
				++ mission_flag
			ENDIF
		ENDIF
	ELSE
		DO_FADE 500 FADE_OUT
		++ mission_flag
	ENDIF
ENDIF

IF mission_flag = 40
	IF 	HAS_CAR_RECORDING_BEEN_LOADED 156
	AND	HAS_CAR_RECORDING_BEEN_LOADED 157
	AND	HAS_CAR_RECORDING_BEEN_LOADED 158
	AND	HAS_CAR_RECORDING_BEEN_LOADED 159
		IF	HAS_CAR_RECORDING_BEEN_LOADED 160
		AND HAS_CAR_RECORDING_BEEN_LOADED 161

 			IF NOT GET_FADING_STATUS
 //				IF NOT IS_CAR_DEAD copcar2
//					START_PLAYBACK_RECORDED_CAR copcar2 157
//					SET_PLAYBACK_SPEED copcar2 0.0
//				ENDIF

				SET_PLAYER_CONTROL player1 ON
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_ALL_CARS_CAN_BE_DAMAGED TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT




				LVAR_INT trigger_car_recc ambush_cop_car ambush_cop_car2 ambush_cop_car3 recording_going_on playback_cat_drive
				LVAR_INT cat_fall_off_time cat_ambush_seq ambush_cop[8] cop_i leave_time1 leave_time2 leave_time3 do_cop_task catalina_pickup_blip
				LVAR_INT players_car cops_shoot_time cat_entering_players_car chase_cop_bike1 chase_cop_bike2
				LVAR_FLOAT ambush_point_x[6] ambush_point_y[6] ambush_point_z[6]


				//SET_CHAR_COORDINATES scplayer 2319.5288 61.1502 25.4769
				CREATE_CAR COPCARRU 1307.0953 280.2934 18.4062 ambush_cop_car 
				CREATE_CAR COPCARRU 1307.0953 290.2934 18.4062 ambush_cop_car2
				CREATE_CAR COPCARRU 1307.0953 300.2934 18.4062 ambush_cop_car3

				FREEZE_CAR_POSITION ambush_cop_car TRUE
				FREEZE_CAR_POSITION ambush_cop_car2 TRUE
				FREEZE_CAR_POSITION ambush_cop_car3 TRUE

				CREATE_CAR COPBIKE 2346.5232 62.9803 25.3359 chase_cop_bike1
				CREATE_CAR COPBIKE 2346.6821 45.9405 25.3359 chase_cop_bike2

				CREATE_CHAR_INSIDE_CAR ambush_cop_car PEDTYPE_MISSION1 CSHER ambush_cop[0]
				CREATE_CHAR_AS_PASSENGER ambush_cop_car PEDTYPE_MISSION1 CSHER 0 ambush_cop[1]
				CREATE_CHAR_INSIDE_CAR ambush_cop_car2 PEDTYPE_MISSION1 CSHER ambush_cop[2]
				CREATE_CHAR_AS_PASSENGER ambush_cop_car2 PEDTYPE_MISSION1 CSHER 0 ambush_cop[3]
				CREATE_CHAR_INSIDE_CAR ambush_cop_car3 PEDTYPE_MISSION1 CSHER ambush_cop[4]
				CREATE_CHAR_AS_PASSENGER ambush_cop_car3 PEDTYPE_MISSION1 CSHER 0 ambush_cop[5]

				CREATE_CHAR_INSIDE_CAR chase_cop_bike1 PEDTYPE_MISSION2 CSHER ambush_cop[6]
				CREATE_CHAR_INSIDE_CAR chase_cop_bike2 PEDTYPE_MISSION2 CSHER ambush_cop[7]

				SET_CHAR_RELATIONSHIP ambush_cop[6] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
				SET_CHAR_RELATIONSHIP ambush_cop[7] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1

				LVAR_INT ambush_dec
				COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH ambush_dec
				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY empty_dec

				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_SHOT_FIRED
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_SHOT_FIRED_WHIZZED_BY
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_DAMAGE
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_ACQUAINTANCE_PED_HATE
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_GUN_AIMED_AT
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_DEAD_PED
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_VEHICLE_DAMAGE_WEAPON


				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_SHOT_FIRED TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 100.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 75.0 0.0 75.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_DAMAGE TASK_SIMPLE_DUCK 0.0 25.0 0.0 25.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_GUN_AIMED_AT TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 1 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ambush_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 1 1


				SET_CHAR_DECISION_MAKER ambush_cop[0] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[1] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[2] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[3] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[4] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[5] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[6] empty_dec
				SET_CHAR_DECISION_MAKER ambush_cop[7] empty_dec

				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE ambush_cop[6] KNOCKOFFBIKE_ALWAYSNORMAL
				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE ambush_cop[7] KNOCKOFFBIKE_ALWAYSNORMAL

				GIVE_WEAPON_TO_CHAR ambush_cop[0] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[1] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[2] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[3] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[4] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[5] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[6] WEAPONTYPE_PISTOL 9999
				GIVE_WEAPON_TO_CHAR ambush_cop[7] WEAPONTYPE_PISTOL 9999


				IF NOT IS_CHAR_DEAD catalina
					GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_SHOTGUN 9999
				ENDIF



				ambush_point_x[0] =	1290.0247
				ambush_point_x[1] =	1294.2424
				ambush_point_x[2] =	1282.6812
				ambush_point_x[3] =	1285.5846
				ambush_point_x[4] =	1289.3892
				ambush_point_x[5] =	1285.5931

				ambush_point_y[0] =	252.8313
				ambush_point_y[1] =	249.3508
				ambush_point_y[2] =	250.9628
				ambush_point_y[3] =	253.8959
				ambush_point_y[4] =	241.5564
				ambush_point_y[5] =	240.6378

				ambush_point_z[0] =	18.4062
				ambush_point_z[1] =	18.4062
				ambush_point_z[2] =	18.4062
				ambush_point_z[3] =	18.4062
				ambush_point_z[4] =	18.4062
				ambush_point_z[5] =	18.4062



				trigger_car_recc = 0
				recording_going_on = 0
				playback_cat_drive = 0


				DO_FADE 500 FADE_IN
				++ mission_flag


			ENDIF
		ELSE
			REQUEST_CAR_RECORDING 160
			REQUEST_CAR_RECORDING 161
		ENDIF
	ELSE
		REQUEST_CAR_RECORDING 156
		REQUEST_CAR_RECORDING 157
		REQUEST_CAR_RECORDING 158
		REQUEST_CAR_RECORDING 159
	ENDIF
ENDIF

IF mission_flag = 41
	IF NOT GET_FADING_STATUS
		catalina_flag = 8
		SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
		++ mission_flag
	ENDIF
ENDIF

//IF catalina_flag = 6
//	IF NOT IS_CAR_DEAD catalina_pcj600 //WARNING!!! (will break if player gets on bike or it gets destroyed)
//		IF IS_CHAR_SITTING_IN_CAR catalina catalina_pcj600
//			START_PLAYBACK_RECORDED_CAR catalina_pcj600 115
//			++ catalina_flag
//		ENDIF
//	ENDIF
//ENDIF




//IF NOT IS_CAR_DEAD copcar2
//	IF NOT IS_CAR_DEAD copcar3
//		IF mission_flag = 31
//			mission_flag = 28
//		ENDIF
//		IF mission_flag = 30
//			SET_CAR_COORDINATES copcar2 2287.1562 61.9821 25.4766
//			SET_CAR_HEADING copcar2 322.7361
//			SET_CAR_COORDINATES copcar3 2289.6289 58.0551 25.3359
//			SET_CAR_HEADING copcar3 330.5475
//			//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
//			//WARP_CHAR_INTO_CAR scplayer	copcar3//copcar2
//			++ mission_flag
//		ENDIF
//		IF mission_flag = 29
//			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar2
//				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar3
//					//STOP_RECORDING_CAR copcar2
//					STOP_PLAYBACK_RECORDED_CAR copcar2
//					//STOP_RECORDING_CAR copcar3
//					STOP_PLAYBACK_RECORDED_CAR copcar3
//					++ mission_flag
//				ENDIF
//			ENDIF
//		ENDIF
//		IF mission_flag = 28
//			//START_RECORDING_CAR copcar2 123
//			START_PLAYBACK_RECORDED_CAR	copcar2	123
//			//START_RECORDING_CAR	copcar3 124
//			START_PLAYBACK_RECORDED_CAR	copcar3	124
//			++ mission_flag
//		ENDIF
//	ENDIF
//ENDIF


//OUTSIDE BANKS BACK DOOR LOCATE
//2317.4285 2.9314 26.7164 3.0 2.0 1.2

//BANK END OF BLOCK AREA 1
//2318.7502 -6.5675 31.3712 15.0 11.2 5.8

//MIDDLE AREA 2 & 3
//2318.7502 15.8125 31.3712 15.0 11.2 5.8

//BIKE END OF ALLEY AREA 4
//2318.7502 54.3324 31.3712 15.0 11.2 5.8


//ROOF OF PIZZA SHOP
//2328.3840 2.0130 35.4040 5.6 7.6 5.4

//ROOF OF BANK
//2313.1731 -6.9870 35.4040 9.2 12.0 5.0

//LAZER SHOP ROOF
//2310.1777 14.5224 31.3712 6.6 7.2 2.8

//DRY CLEANER ROOF
//2309.4468 32.0223 31.3712 5.2 10.0 2.8

//FURNITURE SHOP
//2308.6724 56.5422 31.9712 5.6 12.8 3.0

//BACK ROOF OF RECORD SHOP
//2311.6362 74.2422 30.7711 6.0 2.4 2.2

//LOCATE FOR PLAYER AND CATALINA TO TRIGGER COPS ARRIVING ON BIKES AT END OF ALLEY
//2315.5576 70.7181 31.4998 11.4 8.4 6.0

//END OF PED NODES ON BANK ROOF
//2317.0110 -2.2289 31.5313

//PLAYERS Z VALUE > IF HES ON THE ROOFS.
//28.65


// CATALINA STUFF ///////////////////////////
IF mission_flag > 9
	IF catalina_flag = 0
		IF mission_timer < game_timer
//			TASK_GO_STRAIGHT_TO_COORD catalina 2314.8865 -16.2812 25.7574 PEDMOVE_WALK 10000//CATALINA GOTO TO STEAL MONEY
			++ catalina_flag
		ENDIF
	ENDIF
ENDIF

IF catalina_flag = 1
	GET_SCRIPT_TASK_STATUS catalina	TASK_GO_STRAIGHT_TO_COORD task_status
	IF task_status = FINISHED_TASK
//		TASK_ACHIEVE_HEADING catalina 173.6441
		++ catalina_flag
	ENDIF
ENDIF

IF catalina_flag = 2
	GET_SCRIPT_TASK_STATUS catalina	TASK_ACHIEVE_HEADING task_status
	IF task_status = FINISHED_TASK
//		TASK_PLAY_ANIM catalina FUCKU PED 4.0 TRUE FALSE FALSE FALSE 120000
		LVAR_INT catalina_timer
		catalina_timer = game_timer + 20000
		++ catalina_flag
	ENDIF
ENDIF

IF catalina_flag = 3
	LVAR_INT cat_money_collected
	cat_money_collected += 15
	IF catalina_timer < game_timer
	OR mission_flag > 10
//		OPEN_SEQUENCE_TASK sequence_task
//			TASK_STAND_STILL -1 2000
//			TASK_STAND_STILL -1 2000
//			TASK_GOTO_CHAR -1 scplayer 5000 3.0
//			TASK_LOOK_AT_CHAR -1 scplayer 2500
//		CLOSE_SEQUENCE_TASK sequence_task
//		PERFORM_SEQUENCE_TASK catalina sequence_task
//		CLEAR_SEQUENCE_TASK sequence_task
		IF mission_flag = 10
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2312.0940 -9.6433 28.6790 SOUND_CAT2_SECURITY_ALARM
			GOSUB alarm_off
			mission_flag = 11
		ENDIF
		++ catalina_flag
	ENDIF
ENDIF

IF catalina_flag = 4
	IF mission_flag > 27

		LVAR_INT cat_shooting_door_flag
		IF NOT IS_CHAR_DEAD catalina
			IF cat_shooting_door_flag = 0
			AND play_audio = 0
				// CATALINA SHOOTS THE BACK DOOR
		//		PRINT_NOW CAT2_11 4000 1 //Follow me.
				play_audio = 19
				play_audio_for = 2
				ADD_BLIP_FOR_CHAR catalina catalina_blip
				SET_BLIP_AS_FRIENDLY catalina_blip TRUE
				TASK_GO_STRAIGHT_TO_COORD catalina 2315.3909 -1.6768 25.7500 PEDMOVE_RUN -2//INSIDE BACK DOOR
				cat_shooting_door_flag = 1
			ENDIF

			IF cat_shooting_door_flag = 1
				GET_SCRIPT_TASK_STATUS catalina -1 task_status
				IF task_status = FINISHED_TASK
					IF NOT HAS_OBJECT_BEEN_DAMAGED pc_door[1]
					   cat_shooting_door_flag = 2	
						TASK_SHOOT_AT_COORD catalina 2315.4749 1.0941 26.8796 1000//SHOOT THROUGH BACK DOOR
//						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2315.4749 1.0941 26.8796 SOUND_CAT2_SECURITY_ALARM
												LVAR_INT shoot_count 
						shoot_count ++
					ELSE
						cat_shooting_door_flag = 2
					ENDIF	
				ENDIF 
			ENDIF

			IF cat_shooting_door_flag = 2
				GET_SCRIPT_TASK_STATUS catalina TASK_SHOOT_AT_COORD task_status
				IF task_status = FINISHED_TASK
					IF HAS_OBJECT_BEEN_DAMAGED pc_door[1]
					OR shoot_count > 2
						TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2315.5095 3.1256 25.4844 PEDMOVE_RUN -2//OUTSIDE THE BACK OF THE BANK
						cat_shooting_door_flag = 3
						++ catalina_flag
					ELSE
						cat_shooting_door_flag = 1
					ENDIF
				ENDIF
			ENDIF		
		ENDIF

	ENDIF
ENDIF

IF ct2_door_destroyed = 0
	IF catalina_flag > 4
		IF print_god_text = 0
		AND play_audio = 0
			PRINT CAT2_19 4000 1
			print_god_text = 1
		ENDIF
		IF HAS_OBJECT_BEEN_DAMAGED pc_door[1]
//			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2315.4749 1.0941 26.8796 SOUND_CAT2_WOODEN_DOOR_BREACH	
			ct2_door_destroyed = 1
			play_audio = 21
			play_audio_delay = TIMERA + 2500
			play_audio_for = 3
		ENDIF
	ENDIF
ENDIF

IF catalina_flag = 5


	// set up the escape through alley section of the mission.

	//The alley leading away from the bank to the point where Cat and CJ get on the cop bikes will be split in to three sections.
	// Each section will host three cops placed randomly from a possible six locations. So each play of the mission can be different.


	// For cop_initial_task.....

	//each location will have a specific task assigned to it, as such:
	// 0 = duck and shoot
	// 1 = toggle duck/stand and shoot
	// 2 = advance and shoot
	// 3 = roll out and shoot - uses a default locate range of 6 meters - if already shot then ped will do "toggle duck/shoot"

	// Need to ensure Cat can get line of sight for all of the cops. 

	LVAR_FLOAT cop_create_x[18] cop_create_y[18] cop_create_z[18] cop_create_h[18] c2_float c2_x c2_y c2_z c2_x_offset c2_y_offset 
	LVAR_FLOAT c2_sin c2_cos
	LVAR_INT cop_initial_task[18] c2_duck_time[9]

	// guy behind crates
	cop_create_x[0] = 2328.6377   
	cop_create_y[0] = 9.9046
	cop_create_z[0] = 25.4766
	cop_create_h[0] = 82.0
	cop_initial_task[0] = 1

	// guy behind crates (higher)
	cop_create_x[1] = 2328.6304 
	cop_create_y[1] = 12.0793 
	cop_create_z[1] = 26.4366 
	cop_create_h[1] = 102.1560 
	cop_initial_task[1] = 1

	// cop on stairs - now moved on to roof
	cop_create_x[2] = 2323.5391 
	cop_create_y[2] = 8.1573 
	cop_create_z[2] = 30.1564 
	cop_create_h[2] = 119.8251 
	cop_initial_task[2] = 0

	// cop behind wall
	cop_create_x[3] = 2313.9192  
	cop_create_y[3] = 5.5612 
	cop_create_z[3] = 25.4766 
	cop_create_h[3] = 179.0108 
	cop_initial_task[3] = 3

	// cop behind crates immediately in front of player
	cop_create_x[4] = 2319.2803   
	cop_create_y[4] = 10.1457
	cop_create_z[4] = 25.4766 
	cop_create_h[4] = 173.7026 
	cop_initial_task[4] = 1

	// cop on tall roof
	cop_create_x[5] = 2322.4219   
	cop_create_y[5] = 14.3559 
	cop_create_z[5] = 33.4834 
	cop_create_h[5] = 131.5315 
	cop_initial_task[5] = 0

	// cop behind wall
	cop_create_x[6] = 2322.5669    
	cop_create_y[6] = 22.3196 
	cop_create_z[6] = 25.4766 
	cop_create_h[6] = 191.9916 
	cop_initial_task[6] = 5

	// cop behind bins
	cop_create_x[7] = 2317.4221     
	cop_create_y[7] = 31.4408 
	cop_create_z[7] = 25.4688 
	cop_create_h[7] = 170.5408 
	cop_initial_task[7] = 1

	// cop comes down stairs
	cop_create_x[8] = 2323.3474     		   
	cop_create_y[8] = 32.5086 
	cop_create_z[8] = 30.3438 
	cop_create_h[8] = 167.5312 
	cop_initial_task[8] = 2

	// crouched on roof
	cop_create_x[9] = 2314.0598     
	cop_create_y[9] = 27.4771 
	cop_create_z[9] = 28.4834 
	cop_create_h[9] = 211.9221 
	cop_initial_task[9] = 0

	// crouched on roof
	cop_create_x[10] = 2314.1458      
	cop_create_y[10] = 32.6897 
	cop_create_z[10] = 30.3072 
	cop_create_h[10] = 218.8303 
	cop_initial_task[10] = 0

	// crouched on roof
	cop_create_x[11] = 2324.3235      
	cop_create_y[11] = 25.9529 
	cop_create_z[11] = 30.4834 
	cop_create_h[11] = 146.7435 
	cop_initial_task[11] = 0

	// crouched behind crates
	cop_create_x[12] = 2315.2354      
	cop_create_y[12] = 44.7149 
	cop_create_z[12] = 27.6529 
	cop_create_h[12] = 177.4772 
	cop_initial_task[12] = 1

	// behind wall
	cop_create_x[13] = 2322.1423       
	cop_create_y[13] = 38.0272 
	cop_create_z[13] = 25.4698 
	cop_create_h[13] = 165.0420 
	cop_initial_task[13] = 5

	// behind rubble to roll
	cop_create_x[14] = 2316.2810       
	cop_create_y[14] = 53.2450 
	cop_create_z[14] = 25.4745 
	cop_create_h[14] = 211.6924 
	cop_initial_task[14] = 3

	// duck behind rubble
	cop_create_x[15] = 2316.9771        
	cop_create_y[15] = 60.9693 
	cop_create_z[15] = 26.9906 
	cop_create_h[15] = 217.9312 
	cop_initial_task[15] = 0

	// on roof
	cop_create_x[16] = 2322.6902         
	cop_create_y[16] = 57.5449 
	cop_create_z[16] = 31.9884 
	cop_create_h[16] = 174.0596 
	cop_initial_task[16] = 0

	// on roof
	cop_create_x[17] = 2313.3901          
	cop_create_y[17] = 51.5349 
	cop_create_z[17] = 29.4834 
	cop_create_h[17] = 196.6579 
	cop_initial_task[17] = 0


	LVAR_INT c2_i c2_int c2_location_in_use[18] c2_alley_cop[9] 
	LVAR_INT cop_type[9] cop_location_occupier[18] cop_location[9] 

	c2_i = 0
	WHILE c2_i < 9
		IF c2_i < 3
			GENERATE_RANDOM_INT_IN_RANGE 0 6 c2_int
		
			WHILE c2_location_in_use[c2_int] = 1
				c2_int ++
				IF c2_int = 6
					c2_int = 0
				ENDIF
			ENDWHILE
		ENDIF

		IF c2_i > 2
		AND c2_i < 6
			GENERATE_RANDOM_INT_IN_RANGE 6 12 c2_int
		
			WHILE c2_location_in_use[c2_int] = 1
				c2_int ++
				IF c2_int = 12
					c2_int = 6
				ENDIF
			ENDWHILE
		ENDIF

		IF c2_i > 5
		AND c2_i < 9
			GENERATE_RANDOM_INT_IN_RANGE 12 18 c2_int
		
			WHILE c2_location_in_use[c2_int] = 1
				c2_int ++
				IF c2_int = 18
					c2_int = 12
				ENDIF
			ENDWHILE
		ENDIF





		c2_location_in_use[c2_int] = 1
		cop_location_occupier[c2_int] = c2_i
		CREATE_CHAR PEDTYPE_CIVMALE CSHER cop_create_x[c2_int] cop_create_y[c2_int] cop_create_z[c2_int] c2_alley_cop[c2_i]
		SET_CHAR_SHOOT_RATE c2_alley_cop[c2_i] 40
		SET_CHAR_ACCURACY c2_alley_cop[c2_i] 60
		cop_type[c2_i] = cop_initial_task[c2_int]
		cop_location[c2_i] = c2_int
	//		SET_CHAR_DECISION_MAKER alley_cop1 bank_peds_decision_maker
		SET_CHAR_HEADING c2_alley_cop[c2_i] cop_create_h[c2_int]
		GENERATE_RANDOM_INT_IN_RANGE 0 3 c2_int
		LVAR_INT shotguns_given
		IF c2_int < 3
		OR shotguns_given > 0			
			GIVE_WEAPON_TO_CHAR	c2_alley_cop[c2_i] WEAPONTYPE_PISTOL 9999
		ELSE
			IF cop_location[c2_i] < 6
				GIVE_WEAPON_TO_CHAR	c2_alley_cop[c2_i] WEAPONTYPE_PISTOL 9999	
			ELSE
				GIVE_WEAPON_TO_CHAR	c2_alley_cop[c2_i] WEAPONTYPE_SHOTGUN 9999
				SET_CHAR_SHOOT_RATE c2_alley_cop[c2_i] 10
				shotguns_given = 1
			ENDIF
		ENDIF
		SET_CHAR_RELATIONSHIP c2_alley_cop[c2_i] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_RELATIONSHIP c2_alley_cop[c2_i] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL

		IF cop_initial_task[c2_int] = 0
			OPEN_SEQUENCE_TASK sequence_task
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE	-1 TRUE
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK c2_alley_cop[c2_i] sequence_task
			CLEAR_SEQUENCE_TASK sequence_task
			SET_CHAR_STAY_IN_SAME_PLACE c2_alley_cop[c2_i] TRUE
//			FREEZE_CHAR_POSITION c2_alley_cop[c2_i] TRUE
		ENDIF

		IF cop_initial_task[c2_int] = 1
			OPEN_SEQUENCE_TASK sequence_task
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE	-1 TRUE
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK c2_alley_cop[c2_i] sequence_task
			CLEAR_SEQUENCE_TASK sequence_task
//			FREEZE_CHAR_POSITION c2_alley_cop[c2_i] TRUE							
			SET_CHAR_STAY_IN_SAME_PLACE c2_alley_cop[c2_i] TRUE
		ENDIF

		IF cop_initial_task[c2_int] = 2
			OPEN_SEQUENCE_TASK sequence_task
				TASK_STAY_IN_SAME_PLACE	-1 TRUE
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK c2_alley_cop[c2_i] sequence_task
			CLEAR_SEQUENCE_TASK sequence_task				
		ENDIF


		IF cop_initial_task[c2_int] = 3
			OPEN_SEQUENCE_TASK sequence_task
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE	-1 TRUE
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK c2_alley_cop[c2_i] sequence_task
			CLEAR_SEQUENCE_TASK sequence_task				
		ENDIF

		IF cop_initial_task[c2_int] = 5
			OPEN_SEQUENCE_TASK sequence_task
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE	-1 TRUE
			CLOSE_SEQUENCE_TASK sequence_task
			PERFORM_SEQUENCE_TASK c2_alley_cop[c2_i] sequence_task
			CLEAR_SEQUENCE_TASK sequence_task				
		ENDIF



		c2_i ++
	ENDWHILE


	c2_catalina_target = 99
	force_cat_advance_time = TIMERA + 30000
	catalina_flag = 6



//	VIEW_INTEGER_VARIABLE cop_type[0] cop_type[0] 
//	VIEW_INTEGER_VARIABLE cop_type[1] cop_type[1]
//	VIEW_INTEGER_VARIABLE cop_type[2] cop_type[2]
//	VIEW_INTEGER_VARIABLE cop_type[3] cop_type[3]
//	VIEW_INTEGER_VARIABLE cop_type[4] cop_type[4]
//	VIEW_INTEGER_VARIABLE cop_type[5] cop_type[5]
//	VIEW_INTEGER_VARIABLE cop_type[6] cop_type[6]
//	VIEW_INTEGER_VARIABLE cop_type[7] cop_type[7]
//	VIEW_INTEGER_VARIABLE cop_type[8] cop_type[8]




ENDIF



IF catalina_flag = 6

LVAR_INT catalina_advance_flag c2_task_status c2_catalina_target
//VIEW_INTEGER_VARIABLE catalina_Advance_flag	catalina_Advance_flag
//VIEW_INTEGER_VARIABLE c2_catalina_target c2_catalina_target


	IF NOT IS_CHAR_DEAD catalina

//		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_UP
//			bad_flag ++
//			IF bad_flag > 17
//				bad_flag = 0
//			ENDIF
//			SET_CHAR_COORDINATES scplayer cop_create_x[bad_flag] cop_create_y[bad_flag] cop_create_z[bad_flag]
//			SET_CHAR_HEADING scplayer cop_create_h[bad_flag]
//		ENDIF
//
//		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DOWN
//			bad_flag --
//			IF bad_flag < 0
//				bad_flag = 17
//			ENDIF
//			SET_CHAR_COORDINATES scplayer cop_create_x[bad_flag] cop_create_y[bad_flag] cop_create_z[bad_flag]
//			SET_CHAR_HEADING scplayer cop_create_h[bad_flag]
//		ENDIF


		IF NOT c2_catalina_target = 99
			IF IS_CHAR_DEAD c2_alley_cop[c2_catalina_target]
				c2_i = cop_location[c2_catalina_target] 
				c2_location_in_use[c2_i] = 0
				c2_catalina_target = 99				
			ENDIF
			IF TIMERA > force_cat_advance_time 
				IF NOT c2_catalina_target = 99				
					force_cat_advance_time = TIMERA + 30000
					make_catalina_advance = 1	
					c2_i = cop_location[c2_catalina_target] 
					c2_location_in_use[c2_i] = 0
					c2_catalina_target = 99
				ENDIF
			ENDIF
		ENDIF


		IF c2_catalina_target = 99
			//This bit of script will prevent Catalina getting stuck if she can not see any enemies to target.
			IF catalina_advance_flag > 0
			AND catalina_advance_flag <	10
				LVAR_INT make_catalina_advance catalina_advance_timer
				GET_SCRIPT_TASK_STATUS catalina -1 cat_task_status
				IF cat_task_status = PERFORMING_TASK
					catalina_advance_timer = TIMERA + 8000
				ENDIF
				IF cat_task_status = FINISHED_TASK
					IF TIMERA > catalina_advance_timer
						make_catalina_advance = 1
					ENDIF	
				ENDIF	
			ENDIF

			IF catalina_advance_flag = 0
				LVAR_INT cat_task_status
				GET_SCRIPT_TASK_STATUS catalina -1 cat_task_status
				IF cat_task_status = FINISHED_TASK
//				AND play_audio = 0
//					CLEAR_PRINTS
//					PRINT CAT2_20 4000 1
					catalina_advance_flag = 1
				ENDIF
			ENDIF
			IF catalina_advance_flag = 1
				IF c2_location_in_use[3] = 1
					c2_catalina_target = cop_location_occupier[3]	 
				ELSE 
					IF c2_location_in_use[2] = 1
						c2_catalina_target = cop_location_occupier[2]	
					ELSE
						IF c2_location_in_use[4] = 1
							c2_catalina_target = cop_location_occupier[4]	
						ELSE
							IF c2_location_in_use[5] = 1
								c2_catalina_target = cop_location_occupier[5]	
							ELSE
								catalina_advance_flag = 2
								play_audio = 24
								TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2321.7400 10.5486 25.4688 PEDMOVE_RUN -2 
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF make_catalina_advance = 1
					make_catalina_advance = 0
					catalina_advance_flag = 2
					play_audio = 24
					TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2321.7400 10.5486 25.4688 PEDMOVE_RUN -2
				ENDIF
			ENDIF
			IF catalina_advance_flag = 2
				IF NOT IS_CHAR_DEAD catalina
					GET_SCRIPT_TASK_STATUS catalina TASK_FOLLOW_PATH_NODES_TO_COORD cat_task_status
					IF cat_task_status = FINISHED_TASK
						IF LOCATE_CHAR_ANY_MEANS_3D catalina 2321.7400 10.5486 25.4688 3.0 3.0 3.0 FALSE
							catalina_advance_flag = 3
						ELSE
							TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2321.7400 10.5486 25.4688 PEDMOVE_RUN -2
						ENDIF	
					ENDIF				
				ENDIF	
			ENDIF
			IF catalina_advance_flag = 3
			AND play_audio = 0
				IF c2_location_in_use[0] = 1
					c2_catalina_target = cop_location_occupier[0]	 
				ELSE 
					IF c2_location_in_use[1] = 1
						c2_catalina_target = cop_location_occupier[1]	
					ELSE
						catalina_advance_flag = 4
						play_audio = 25
						TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2318.2805 18.5403 25.4766 PEDMOVE_RUN -2
					ENDIF
				ENDIF
				IF make_catalina_advance = 1
					make_catalina_advance = 0
					catalina_advance_flag = 4
					play_audio = 25
					TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2318.2805 18.5403 25.4766 PEDMOVE_RUN -2
				ENDIF

			ENDIF
			IF catalina_advance_flag = 4
				IF NOT IS_CHAR_DEAD catalina
					GET_SCRIPT_TASK_STATUS catalina TASK_FOLLOW_PATH_NODES_TO_COORD cat_task_status
					IF cat_task_status = FINISHED_TASK
						IF LOCATE_CHAR_ANY_MEANS_3D catalina 2318.2805 18.5403 25.4766 3.0 3.0 3.0 FALSE
							catalina_advance_flag = 5
						ELSE
							TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2318.2805 18.5403 25.4766 PEDMOVE_RUN -2
						ENDIF	
					ENDIF
				ENDIF	
			ENDIF
			IF catalina_advance_flag = 5
				IF c2_location_in_use[6] = 1
					c2_catalina_target = cop_location_occupier[6]	 
				ELSE 
					IF c2_location_in_use[7] = 1
						c2_catalina_target = cop_location_occupier[7]	
					ELSE
						IF c2_location_in_use[8] = 1
							c2_catalina_target = cop_location_occupier[8]	
						ELSE
							IF c2_location_in_use[9] = 1
								c2_catalina_target = cop_location_occupier[9]	
							ELSE
								IF c2_location_in_use[10] = 1
									c2_catalina_target = cop_location_occupier[10]	
								ELSE
									IF c2_location_in_use[11] = 1
										c2_catalina_target = cop_location_occupier[11]	
									ELSE								
										catalina_advance_flag = 6
										play_audio = 26
										TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2278 34.5650 25.468 PEDMOVE_RUN -2
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF make_catalina_advance = 1
					make_catalina_advance = 0
					catalina_advance_flag = 6
					play_audio = 26
					TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2278 34.5650 25.468 PEDMOVE_RUN -2
				ENDIF
			ENDIF
			IF catalina_advance_flag = 6
				IF NOT IS_CHAR_DEAD catalina
					GET_SCRIPT_TASK_STATUS catalina TASK_FOLLOW_PATH_NODES_TO_COORD cat_task_status
					IF cat_task_status = FINISHED_TASK
						IF LOCATE_CHAR_ANY_MEANS_3D catalina 2319.2278 34.5650 25.468 3.0 3.0 3.0 FALSE
							catalina_advance_flag = 7
						ELSE
							TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2278 34.5650 25.468 PEDMOVE_RUN -2
						ENDIF	
					ENDIF
				ENDIF	
			ENDIF
			IF catalina_advance_flag = 7
			AND play_audio = 0
				IF c2_location_in_use[12] = 1
					c2_catalina_target = cop_location_occupier[12]	 
				ELSE 
					IF c2_location_in_use[13] = 1
						c2_catalina_target = cop_location_occupier[13]	
					ELSE
						catalina_advance_flag = 8						
						TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2318.6216 45.0292 25.4762 PEDMOVE_RUN -2
					ENDIF
				ENDIF
				IF make_catalina_advance = 1
					make_catalina_advance = 0
					catalina_advance_flag = 8						
					TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2318.6216 45.0292 25.4762 PEDMOVE_RUN -2
				ENDIF
			ENDIF
			IF catalina_advance_flag = 8
				IF NOT IS_CHAR_DEAD catalina
					GET_SCRIPT_TASK_STATUS catalina -1 cat_task_status
					IF cat_task_status = FINISHED_TASK
						catalina_advance_flag = 9
					ENDIF
				ENDIF	
			ENDIF
			IF catalina_advance_flag = 9
			AND play_audio = 0
				IF c2_location_in_use[14] = 1
					c2_catalina_target = cop_location_occupier[14]	 
				ELSE 
					IF c2_location_in_use[15] = 1
						c2_catalina_target = cop_location_occupier[15]	
					ELSE
						IF c2_location_in_use[16] = 1
							c2_catalina_target = cop_location_occupier[16]	
						ELSE
							IF c2_location_in_use[17] = 1
								c2_catalina_target = cop_location_occupier[17]	
							ELSE							
								catalina_advance_flag = 10
								play_audio = 28
								TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2373 64.4156 25.4766 PEDMOVE_RUN -2
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF make_catalina_advance = 1
					make_catalina_advance = 0
					catalina_advance_flag = 10
					play_audio = 28
					TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2373 64.4156 25.4766 PEDMOVE_RUN -2
				ENDIF
			ENDIF
			IF catalina_advance_flag = 10
				IF NOT IS_CHAR_DEAD catalina
					GET_SCRIPT_TASK_STATUS catalina -1 cat_task_status
					IF cat_task_status = FINISHED_TASK
						IF LOCATE_CHAR_ANY_MEANS_3D catalina 2319.2373 64.4156 25.4766 4.0 4.0 4.0 FALSE
							catalina_flag = 7
							mission_flag = 31
						ELSE
							TASK_FOLLOW_PATH_NODES_TO_COORD catalina 2319.2373 64.4156 25.4766 PEDMOVE_RUN -2
						ENDIF

					ENDIF
				ENDIF	
			ENDIF







			IF NOT c2_catalina_target = 99
				IF NOT IS_CHAR_DEAD catalina
					IF NOT IS_CHAR_DEAD c2_alley_cop[c2_catalina_target]
						TASK_KILL_CHAR_ON_FOOT catalina c2_alley_cop[c2_catalina_target]
						LVAR_INT force_cat_advance_time
						force_cat_advance_time = TIMERA + 15000
					ENDIF
				ENDIF
			ENDIF
		ENDIF

			



		c2_i = 0
		WHILE c2_i < 9
			IF NOT IS_CHAR_DEAD c2_alley_cop[c2_i]
				IF cop_type[c2_i] = 1



				// make cop duck and shoot
					IF TIMERA > c2_duck_time[c2_i]
						GET_SCRIPT_TASK_STATUS c2_alley_cop[c2_i] TASK_TOGGLE_DUCK c2_task_status
						IF c2_task_status = FINISHED_TASK 
							GENERATE_RANDOM_INT_IN_RANGE 4000 8000 c2_int
							c2_duck_time[c2_i] = TIMERA + c2_int
							IF IS_CHAR_DUCKING c2_alley_cop[c2_i]
								TASK_TOGGLE_DUCK c2_alley_cop[c2_i] FALSE
							ELSE
								TASK_TOGGLE_DUCK c2_alley_cop[c2_i] TRUE
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF cop_type[c2_i] = 2
				// make cop kill char on foot
					IF NOT IS_CHAR_RESPONDING_TO_EVENT c2_alley_cop[c2_i] EVENT_ACQUAINTANCE_PED_HATE
						TASK_STAY_IN_SAME_PLACE	c2_alley_cop[c2_i] FALSE
						TASK_KILL_CHAR_ON_FOOT c2_alley_cop[c2_i] scplayer 
						cop_type[c2_i] = 4
					ENDIF
				ENDIF

				IF cop_type[c2_i] = 3
				// make cop duck and shoot
	//				IF NOT IS_CHAR_RESPONDING_TO_EVENT c2_alley_cop[c2_i] EVENT_ACQUAINTANCE_PED_HATE
	//				IF NOT HAS_CHAR_SPOTTED_CHAR c2_alley_cop[c2_i] scplayer
	//				AND NOT HAS_CHAR_SPOTTED_CHAR c2_alley_cop[c2_i] catalina
						GET_CHAR_HEADING c2_alley_cop[c2_i] c2_float
						GET_CHAR_COORDINATES c2_alley_cop[c2_i] c2_x c2_y c2_z
						SIN c2_float c2_sin
						COS c2_float c2_cos
						c2_x_offset = c2_sin * -2.5
						c2_x_offset += c2_x
						c2_y_offset = c2_cos * 2.5
						c2_y_offset += c2_y

						IF NOT IS_CHAR_DEAD catalina
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer c2_x_offset c2_y_offset 25.7 5.0 5.0 3.0 FALSE
							OR LOCATE_CHAR_ANY_MEANS_3D catalina c2_x_offset c2_y_offset 25.7 5.0 5.0 3.0 FALSE
								TASK_STAY_IN_SAME_PLACE	c2_alley_cop[c2_i] FALSE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE c2_alley_cop[c2_i] Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
								cop_type[c2_i] = 0
							ENDIF
						ENDIF

	//				ENDIF
				ENDIF

				IF cop_type[c2_i] = 5
				// make cop duck and shoot
	//				IF NOT IS_CHAR_RESPONDING_TO_EVENT c2_alley_cop[c2_i] EVENT_ACQUAINTANCE_PED_HATE
	//				IF NOT HAS_CHAR_SPOTTED_CHAR c2_alley_cop[c2_i] scplayer
	//				AND NOT HAS_CHAR_SPOTTED_CHAR c2_alley_cop[c2_i] catalina
						GET_CHAR_HEADING c2_alley_cop[c2_i] c2_float
						GET_CHAR_COORDINATES c2_alley_cop[c2_i] c2_x c2_y c2_z
						SIN c2_float c2_sin
						COS c2_float c2_cos
						c2_x_offset = c2_sin * -2.5
						c2_x_offset += c2_x
						c2_y_offset = c2_cos * 2.5
						c2_y_offset += c2_y

						IF NOT IS_CHAR_DEAD catalina
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer c2_x_offset c2_y_offset 25.7 5.0 5.0 3.0 FALSE
							OR LOCATE_CHAR_ANY_MEANS_3D catalina c2_x_offset c2_y_offset 25.7 5.0 5.0 3.0 FALSE
								IF play_audio = 0
									play_audio = 27
								ENDIF
								TASK_STAY_IN_SAME_PLACE	c2_alley_cop[c2_i] FALSE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE c2_alley_cop[c2_i] Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
								cop_type[c2_i] = 0
							ENDIF
						ENDIF

	//				ENDIF
				ENDIF																						 

			ENDIF
			c2_i ++

		ENDWHILE
	ENDIF
ENDIF
		 	



// Cat gets on bike and drives off
IF catalina_flag = 8
	IF mission_flag > 40


		LVAR_INT beach_ped_Created beach_ped
		IF beach_ped_Created = 0			 
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2130.6890 149.1074 0.3268 30.0 30.0 30.0 FALSE
				REQUEST_MODEL COPBIKE
				REQUEST_MODEL CSHER
				REQUEST_MODEL COLT45
				IF HAS_MODEL_LOADED COPBIKE
				AND HAS_MODEL_LOADED CSHER
				AND HAS_MODEL_LOADED COLT45
					beach_ped_Created = 1
					LVAR_INT beach_cop_car beach_cop beach_seq
					CREATE_CAR COPBIKE 2048.1565 195.1577 15.8884 beach_cop_car 
					SWITCH_CAR_SIREN beach_cop_car ON
					SET_CAR_HEADING beach_cop_car 270.0
					CREATE_CHAR_INSIDE_CAR beach_cop_car PEDTYPE_MISSION1 CSHER beach_cop
					GIVE_WEAPON_TO_CHAR beach_cop WEAPONTYPE_PISTOL 9999
					OPEN_SEQUENCE_TASK beach_seq
						TASK_CAR_DRIVE_TO_COORD -1 beach_cop_car 2105.2363 179.1932 0.5812 15.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
						TASK_CAR_TEMP_ACTION -1 beach_cop_car TEMPACT_HANDBRAKETURNRIGHT 1000
						TASK_LEAVE_ANY_CAR -1
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK beach_seq
					PERFORM_SEQUENCE_TASK beach_cop	beach_seq
					CLEAR_SEQUENCE_TASK beach_seq
				ENDIF
			ENDIF
		ENDIF



		IF playback_cat_drive = 0
			IF NOT IS_CAR_DEAD copcar3
			AND NOT IS_CAR_DEAD copcar2
			AND NOT IS_CHAR_DEAD catalina
			AND NOT IS_CAR_DEAD chase_cop_bike1
			AND NOT IS_CAR_DEAD chase_cop_bike2
				IF IS_CHAR_IN_CAR catalina copcar2
					IF NOT IS_CHAR_DEAD catalina
						SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE catalina KNOCKOFFBIKE_ALWAYSNORMAL
					ENDIF

						playback_cat_drive = 1
		//				recording_going_on = 1
						START_PLAYBACK_RECORDED_CAR copcar2 157	
						START_PLAYBACK_RECORDED_CAR chase_cop_bike1 160
						START_PLAYBACK_RECORDED_CAR chase_cop_bike2 161
						SWITCH_CAR_SIREN chase_cop_bike1 ON
						SWITCH_CAR_SIREN chase_cop_bike2 ON
						GET_CAR_CHAR_IS_USING scplayer players_car
		//				START_RECORDING_CAR players_car 161
//						ADD_BLIP_FOR_CHAR catalina catalina_pickup_blip
//					SET_BLIP_AS_FRIENDLY catalina_pickup_blip TRUE
				ENDIF
			ENDIF
		ENDIF

		IF playback_cat_drive > 0
			LVAR_INT c2_driver bike1_empty bike2_empty
			IF NOT IS_CAR_DEAD chase_cop_bike1
				IF IS_PLAYBACK_GOING_ON_FOR_CAR chase_cop_bike1
					GET_DRIVER_OF_CAR chase_cop_bike1 c2_driver
					IF c2_driver = -1
						STOP_PLAYBACK_RECORDED_CAR chase_cop_bike1
					ENDIF
//				ELSE
//					IF bike1_empty = 0					
//						GET_DRIVER_OF_CAR chase_cop_bike1 c2_driver
//						IF NOT c2_driver = -1
//							TASK_EVERYONE_LEAVE_CAR chase_cop_bike1
//							bike1_empty = 1
//						ENDIF					
//					ENDIF
				ENDIF					
			ENDIF
			IF NOT IS_CAR_DEAD chase_cop_bike2
				IF IS_PLAYBACK_GOING_ON_FOR_CAR chase_cop_bike2
					GET_DRIVER_OF_CAR chase_cop_bike2 c2_driver
					IF c2_driver = -1
						STOP_PLAYBACK_RECORDED_CAR chase_cop_bike2
					ELSE
						IF bike2_empty = 0
							GET_CAR_COORDINATES chase_cop_bike2 x y z
							IF x < 1570.0
								IF IS_CAR_IN_AIR_PROPER chase_cop_bike2
									IF NOT IS_CHAR_DEAD c2_driver
										TASK_LEAVE_CAR_IMMEDIATELY c2_driver chase_cop_bike2
										bike2_empty = 1 
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF 
				ENDIF		
			ENDIF
		ENDIF
				

		IF playback_cat_drive = 1
			IF NOT IS_CHAR_DEAD catalina 
			AND NOT IS_CAR_DEAD copcar2
			

				// prevent recording speed changing if cat is entering the ambush area.
				IF trigger_car_recc = 0

					GET_CHAR_COORDINATES catalina x y z
					GET_CHAR_COORDINATES scplayer x2 y2 z2
//					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 distance
					distance = x2 - x
					IF distance < 0.0
						distance = 0.0
					ENDIF
					speed = 2000.0 / distance//INCREASE NUMBER TO MAKE CAR GO FASTER
					speed /= 100.0
					IF speed > 1.5
						speed = 1.5
					ENDIF
					IF speed < 0.3
						speed = 0.3
					ENDIF

					LVAR_INT check_cat_comment_time

					IF TIMERA > check_cat_comment_time
					AND cat_comment < 37
						IF distance > 10.0
						AND distance < 50.0
							IF play_audio = 0
								play_audio = cat_comment
								play_audio_global = 1
								check_cat_comment_time = TIMERA + 8000
								cat_comment ++															
							ENDIF
						ENDIF
					ENDIF

					IF NOT IS_CAR_DEAD chase_cop_bike1
						IF IS_CAR_IN_AIR_PROPER chase_cop_bike1
							speed= 1.0
						ENDIF
					ENDIF
					IF NOT IS_CAR_DEAD chase_cop_bike2
						IF IS_CAR_IN_AIR_PROPER chase_cop_bike2
							speed= 1.0
						ENDIF
						IF NOT IS_CHAR_DEAD ambush_cop[7]
							IF NOT IS_CHAR_IN_ANY_CAR ambush_cop[7]
								IF give_cop_Task = 0
									LVAR_INT give_cop_task
									GIVE_WEAPON_TO_CHAR ambush_cop[7] WEAPONTYPE_PISTOL 9999
									TASK_KILL_CHAR_ON_FOOT ambush_cop[7] scplayer
									SET_CHAR_RELATIONSHIP ambush_cop[7] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1								
									SET_CHAR_DECISION_MAKER ambush_cop[7] ambush_dec
									give_cop_task = 1
								ENDIF

							ENDIF
						ENDIF
					ENDIF
					IF NOT IS_CAR_DEAD copcar2
						IF IS_CAR_IN_AIR_PROPER copcar2
							speed= 1.0
						ENDIF
					ENDIF

					SET_PLAYBACK_SPEED copcar2 speed
					IF NOT IS_CAR_DEAD chase_cop_bike1
						SET_PLAYBACK_SPEED chase_cop_bike1 speed
					ENDIF
					IF NOT IS_CAR_DEAD chase_cop_bike2
						SET_PLAYBACK_SPEED chase_cop_bike2 speed
					ENDIF
									
//					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar2
//						//MAKE CATALINA DO SOMETHING
//						GOTO mission_cat2_passed
//					ENDIF
				ELSE
					SET_PLAYBACK_SPEED copcar2 1.0
					IF NOT IS_CAR_DEAD chase_cop_bike1
						SET_PLAYBACK_SPEED chase_cop_bike1 1.0
					ENDIF
					IF NOT IS_CAR_DEAD chase_cop_bike2
						SET_PLAYBACK_SPEED chase_cop_bike2 1.0
					ENDIF

				ENDIF
				IF IS_CHAR_IN_AREA_2D catalina 1304.9573 252.9172 1275.8398 241.8114 FALSE	
//					STOP_PLAYBACK_RECORDED_CAR copcar2
//					SET_CAR_FORWARD_SPEED copcar2 25.0
					playback_cat_drive = 2
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cat_own_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 TRUE TRUE //newline
					
					cat_fall_off_time = TIMERA + 900
				ENDIF
			ENDIF	
		ENDIF

		IF playback_cat_drive = 2
			IF TIMERA > cat_fall_off_time
				IF NOT IS_CAR_DEAD copcar2
				AND NOT IS_CHAR_DEAD catalina
					LVAR_INT print_time_for_catalina
					print_time_for_catalina = TIMERA + 1000
					
		// 				
					OPEN_SEQUENCE_TASK cat_ambush_seq 
						TASK_LEAVE_CAR_IMMEDIATELY -1 copcar2
						TASK_GO_STRAIGHT_TO_COORD -1 1286.9689 247.1966 18.4062 PEDMOVE_RUN -2
						TASK_HANDS_UP -1 40000
					CLOSE_SEQUENCE_TASK cat_ambush_seq
					PERFORM_SEQUENCE_TASK catalina cat_ambush_seq
					CLEAR_SEQUENCE_TASK cat_ambush_seq
					playback_cat_drive = 3



					
				ENDIF
			ENDIF		
		ENDIF

		
		IF print_time_for_catalina > 0
			IF TIMERA > print_time_for_catalina
				CLEAR_PRINTS
				PRINT CAT2_16 6000 1	
				
//				REMOVE_BLIP mission_blip
				ADD_BLIP_FOR_COORD 867.6723 -30.3543 62.1893 mission_blip
				print_time_for_catalina = 0
				playback_cat_drive = 4
			ENDIF
		ENDIF

		IF playback_cat_drive = 4
			IF NOT IS_CHAR_DEAD catalina
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer catalina 15.0 15.0 15.0 FALSE
				OR IS_CHAR_SHOOTING_IN_AREA scplayer 1389.0215 67.2039 1232.6281 407.0970 FALSE

					MARK_CHAR_AS_NO_LONGER_NEEDED ambush_cop[6]
					MARK_CHAR_AS_NO_LONGER_NEEDED ambush_cop[7]

					MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car 
					MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car2
					MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car3

					IF DOES_CHAR_EXIST beach_cop
						MARK_CHAR_AS_NO_LONGER_NEEDED beach_cop
					ENDIF

					IF NOT IS_CAR_DEAD beach_cop_car
						IF NOT IS_CHAR_IN_CAR scplayer beach_cop_car
							MARK_CAR_AS_NO_LONGER_NEEDED beach_cop_car
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED chase_cop_bike2
					ENDIF

					IF NOT IS_CAR_DEAD chase_cop_bike1 
						IF NOT IS_CHAR_IN_CAR scplayer chase_cop_bike1
							MARK_CAR_AS_NO_LONGER_NEEDED chase_cop_bike1
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED chase_cop_bike1
					ENDIF
					 
					IF NOT IS_CAR_DEAD chase_cop_bike2
						IF NOT IS_CHAR_IN_CAR scplayer chase_cop_bike2
							MARK_CAR_AS_NO_LONGER_NEEDED chase_cop_bike2
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED chase_cop_bike2
					ENDIF

					IF NOT IS_CAR_DEAD copcar3
						IF NOT IS_CHAR_IN_CAR scplayer copcar3
							MARK_CAR_AS_NO_LONGER_NEEDED copcar3
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED copcar3
					ENDIF

					IF NOT IS_CAR_DEAD copcar2
						IF NOT IS_CHAR_IN_CAR scplayer copcar2
							MARK_CAR_AS_NO_LONGER_NEEDED copcar2
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED copcar2
					ENDIF




					LVAR_INT cat2_i	empty_dec

					cat2_i = 0
					WHILE cat2_i < 8

						IF NOT IS_CHAR_DEAD ambush_cop[cat2_i]
							CLEAR_CHAR_TASKS ambush_cop[cat2_i]
							MARK_CHAR_AS_NO_LONGER_NEEDED ambush_cop[cat2_i]
							SET_CHAR_RELATIONSHIP ambush_cop[cat2_i] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_RELATIONSHIP ambush_cop[cat2_i] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL						
						ENDIF

						cat2_i ++
					ENDWHILE
				 
//					SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
//					SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_SPECIAL
//					SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL PEDTYPE_MISSION1


					print_god_text = 0
//				   	PRINT CAT2_18 7000 1
//					REMOVE_BLIP catalina_pickup_blip
//					REMOVE_BLIP mission_blip


					IF NOT IS_GROUP_MEMBER catalina players_group  
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group catalina
						SET_GROUP_DECISION_MAKER players_group cat_decisions
//						ADD_BLIP_FOR_CHAR catalina catalina_blip
						
					ENDIF

					LVAR_INT cat_in_players_group
					cat_in_players_group = 1
					REMOVE_BLIP catalina_blip

	//				TASK_ENTER_CAR_AS_PASSENGER catalina players_car -2 0
					playback_cat_drive = 5

//					cops_shoot_time = TIMERA + 4000

				ENDIF
			ENDIF
		ENDIF 


		IF playback_cat_drive = 5
			LVAR_INT cops_shoot
			IF cops_shoot = 0
				IF TIMERA > cops_shoot_time

					cat2_i = 0
					WHILE cat2_i < 8

						IF NOT IS_CHAR_DEAD ambush_cop[cat2_i]
							TASK_SET_CHAR_DECISION_MAKER ambush_cop[cat2_i] ambush_dec
						ENDIF

						cat2_i ++
					ENDWHILE

					cops_shoot = 1
				ENDIF
			ENDIF





			LVAR_INT cat2_play_getaway_dialogue
			IF NOT IS_CHAR_DEAD catalina
				IF IS_CHAR_IN_ANY_CAR catalina
					IF cat2_play_getaway_dialogue = 0
						cat2_play_getaway_dialogue = 1
					  	only_play_audio_if_player_in_car = 1
						play_audio_global = 1
						
						IF cat_getaway_dialogue = 0

							IF cat_counter = 0 

								// getaway dialogue 1














								audio_for_char[40] = 2 //CARL
								audio_for_char[41] = 1 //CATALINA
								audio_for_char[42] = 1
								audio_for_char[43] = 1
								audio_for_char[44] = 1
								audio_for_char[45] = 1
								audio_for_char[46] = 2 //CARL
								audio_for_char[47] = 1 //CATALINA
								audio_for_char[48] = 1
								audio_for_char[49] = 2 //CARL
								audio_for_char[50] = 1 //CATALINA
					
								$audio_text[40] = &CAT_DF 
								$audio_text[41] = &CAT_DG 
								$audio_text[42] = &CAT_DH 
								$audio_text[43] = &CAT_DI 
								$audio_text[44] = &CAT_DJ 
								$audio_text[45] = &CAT_DK 
								$audio_text[46] = &CAT_DL 
								$audio_text[47] = &CAT_DM 
								$audio_text[48] = &CAT_DN 
								$audio_text[49] = &CAT_DO 
								$audio_text[50] = &CAT_DP
								
								audio_sound[40] = SOUND_CAT_DF 
								audio_sound[41] = SOUND_CAT_DG 
								audio_sound[42] = SOUND_CAT_DH 
								audio_sound[43] = SOUND_CAT_DI 
								audio_sound[44] = SOUND_CAT_DJ 
								audio_sound[45] = SOUND_CAT_DK 
								audio_sound[46] = SOUND_CAT_DL 
								audio_sound[47] = SOUND_CAT_DM 
								audio_sound[48] = SOUND_CAT_DN 
								audio_sound[49] = SOUND_CAT_DO 
								audio_sound[50] = SOUND_CAT_DP

								play_audio = 40
								play_audio_for = 11


							ELSE
								// getaway dialogue 1

								audio_for_char[40] = 1  
								audio_for_char[41] = 2 //CARL
								audio_for_char[42] = 1 //CATALINA
								audio_for_char[43] = 1
								audio_for_char[44] = 1
								audio_for_char[45] = 1
								audio_for_char[46] = 1 
								audio_for_char[47] = 2 //CARL
								audio_for_char[48] = 1 //CATALINA
								audio_for_char[49] = 1 
								audio_for_char[50] = 2 //CARL
								audio_for_char[51] = 1 //CATALINA

								$audio_text[40] = &CAT_DA
								$audio_text[41] = &CAT_DF 
								$audio_text[42] = &CAT_DG 
								$audio_text[43] = &CAT_DH 
								$audio_text[44] = &CAT_DI 
								$audio_text[45] = &CAT_DJ 
								$audio_text[46] = &CAT_DK 
								$audio_text[47] = &CAT_DL 
								$audio_text[48] = &CAT_DM 
								$audio_text[49] = &CAT_DN 
								$audio_text[50] = &CAT_DO 
								$audio_text[51] = &CAT_DP

								audio_sound[40] = SOUND_CAT_DA
								audio_sound[41] = SOUND_CAT_DF 
								audio_sound[42] = SOUND_CAT_DG 
								audio_sound[43] = SOUND_CAT_DH 
								audio_sound[44] = SOUND_CAT_DI 
								audio_sound[45] = SOUND_CAT_DJ 
								audio_sound[46] = SOUND_CAT_DK 
								audio_sound[47] = SOUND_CAT_DL 
								audio_sound[48] = SOUND_CAT_DM 
								audio_sound[49] = SOUND_CAT_DN 
								audio_sound[50] = SOUND_CAT_DO 
								audio_sound[51] = SOUND_CAT_DP
								
								play_audio = 40
								play_audio_for = 12
																			
							ENDIF
						ENDIF
						
						IF cat_getaway_dialogue = 1 

							audio_for_char[40] = 1 //CATALINA
							audio_for_char[41] = 2 //CARL
							audio_for_char[42] = 1 //CATALINA
							audio_for_char[43] = 2 //CARL
							audio_for_char[44] = 2
							audio_for_char[45] = 1 //CATALINA
							audio_for_char[46] = 2 //CARL
							audio_for_char[47] = 1 //CATALINA
							audio_for_char[48] = 2 //CARL
							audio_for_char[49] = 1 //CATALINA
							audio_for_char[50] = 1 
							audio_for_char[51] = 1 
							audio_for_char[52] = 1 
							audio_for_char[53] = 2 //CARL

  
							// getaway dialogue 2
							$audio_text[40] = &CAT_GA 
							$audio_text[41] = &CAT_GB 
							$audio_text[42] = &CAT_GC 
							$audio_text[43] = &CAT_GD 
							$audio_text[44] = &CAT_GE 
							$audio_text[45] = &CAT_GF 
							$audio_text[46] = &CAT_GG 
							$audio_text[47] = &CAT_GH 
							$audio_text[48] = &CAT_GI 
							$audio_text[49] = &CAT_GJ 
							$audio_text[50] = &CAT_GK 
							$audio_text[51] = &CAT_GL 
							$audio_text[52] = &CAT_GM 
							$audio_text[53] = &CAT_GN
							
							audio_sound[40] = SOUND_CAT_GA 
							audio_sound[41] = SOUND_CAT_GB 
							audio_sound[42] = SOUND_CAT_GC 
							audio_sound[43] = SOUND_CAT_GD 
							audio_sound[44] = SOUND_CAT_GE 
							audio_sound[45] = SOUND_CAT_GF 
							audio_sound[46] = SOUND_CAT_GG 
							audio_sound[47] = SOUND_CAT_GH 
							audio_sound[48] = SOUND_CAT_GI 
							audio_sound[49] = SOUND_CAT_GJ 
							audio_sound[50] = SOUND_CAT_GK 
							audio_sound[51] = SOUND_CAT_GL 
							audio_sound[52] = SOUND_CAT_GM 
							audio_sound[53] = SOUND_CAT_GN 

							play_audio = 40
							play_audio_for = 14
						ENDIF














						IF cat_getaway_dialogue = 2

							audio_for_char[40] = 1 //CATALINA
							audio_for_char[41] = 2 //CARL
							audio_for_char[42] = 1 //CATALINA
							audio_for_char[43] = 2 //CARL
							audio_for_char[44] = 1 //CATALINA
							audio_for_char[45] = 2 //CARL
							audio_for_char[46] = 1 //CATALINA
							audio_for_char[47] = 1 //
				
							// getaway dialogue 3
							$audio_text[40] = &CAT_OA 
							$audio_text[41] = &CAT_OB 
							$audio_text[42] = &CAT_OC 
							$audio_text[43] = &CAT_OD 
							$audio_text[44] = &CAT_OE 
							$audio_text[45] = &CAT_OF 
							$audio_text[46] = &CAT_OG 
							$audio_text[47] = &CAT_OH 
							
							audio_sound[40] = SOUND_CAT_OA 
							audio_sound[41] = SOUND_CAT_OB 
							audio_sound[42] = SOUND_CAT_OC 
							audio_sound[43] = SOUND_CAT_OD 
							audio_sound[44] = SOUND_CAT_OE 
							audio_sound[45] = SOUND_CAT_OF 
							audio_sound[46] = SOUND_CAT_OG 
							audio_sound[47] = SOUND_CAT_OH

							play_audio = 40
							play_audio_for = 8

						ENDIF
					ENDIF
				ENDIF
	//				IF NOT cat_loves_you_time = -1
	//					IF IS_CHAR_IN_ANY_CAR catalina
	//					AND IS_CHAR_IN_ANY_CAR scplayer
	//						IF play_audio = 0
	//							IF cat_loves_you_time = 0
	//								cat_loves_you_time = TIMERA + 8000
	//							ENDIF
	//							IF NOT cat_loves_you_time = 0
	//								IF TIMERA > cat_loves_you_time
	//									play_audio = 38
	//									cat_loves_you_time = -1
	//								ENDIF
	//							ENDIF
	//						ENDIF
	//					ENDIF
	//				ENDIF
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 863.6693 -30.8520 62.1615 4.0 4.0 4.0 TRUE
				AND LOCATE_CHAR_ANY_MEANS_3D catalina 863.6693 -30.8520 62.1615 4.0 4.0 4.0 FALSE					
					REMOVE_BLIP mission_blip
					SET_PLAYER_CONTROL player1 OFF
					catalina_flag = 9
				ENDIF
				IF print_god_text = 0
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						IF play_audio = 0
							play_audio = 37
							print_god_text = 1						
						ENDIF
					ENDIF
				ENDIF
							
			ENDIF
		ENDIF


		// WONT DO THIS BIT NOW
		IF cat_entering_players_car = 2
			IF NOT IS_CHAR_DEAD catalina
				IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina 1287.5 249.2 19.22 60.0 60.0 60.0 FALSE
					cat_entering_players_car = 3
					IF NOT IS_CAR_DEAD ambush_cop_car
						MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car 
					ENDIF
					IF NOT IS_CAR_DEAD ambush_cop_car2
						MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car2
					ENDIF
					IF NOT IS_CAR_DEAD ambush_cop_car3
						MARK_CAR_AS_NO_LONGER_NEEDED ambush_cop_car3
					ENDIF
					cop_i = 0
					WHILE cop_i < 6
						MARK_CHAR_AS_NO_LONGER_NEEDED ambush_cop[cop_i]
						cop_i ++
					ENDWHILE					
				ENDIF
										
			ENDIF 
		ENDIF



		 


		IF trigger_car_recc = 0
			IF NOT IS_CHAR_DEAD catalina
				IF IS_CHAR_IN_AREA_2D catalina 1393.2465 233.1637 1325.0726 190.1405 FALSE
					trigger_car_recc = 1
					IF NOT IS_CAR_DEAD ambush_cop_car
					AND NOT IS_CAR_DEAD ambush_cop_car2
					AND NOT IS_CAR_DEAD ambush_cop_car3

						START_PLAYBACK_RECORDED_CAR ambush_cop_car 156
						START_PLAYBACK_RECORDED_CAR ambush_cop_car2 158
						START_PLAYBACK_RECORDED_CAR ambush_cop_car3 159

						FREEZE_CAR_POSITION ambush_cop_car FALSE
						FREEZE_CAR_POSITION ambush_cop_car2 FALSE
						FREEZE_CAR_POSITION ambush_cop_car3 FALSE

						SWITCH_CAR_SIREN ambush_cop_car ON  
						SWITCH_CAR_SIREN ambush_cop_car2 ON
						SWITCH_CAR_SIREN ambush_cop_car3 ON
						leave_time1 = TIMERA + 4000
						leave_time2 = TIMERA + 4000
						leave_time3 = TIMERA + 4000
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF trigger_car_recc = 1
			IF NOT IS_CAR_DEAD ambush_cop_car
			AND NOT IS_CAR_DEAD ambush_cop_car2
			AND NOT IS_CAR_DEAD ambush_cop_car3
			AND NOT IS_CHAR_DEAD catalina
				cop_i = 0
				WHILE cop_i < 6
					do_cop_task = 0
					IF NOT IS_CHAR_DEAD ambush_cop[cop_i]
						IF IS_CHAR_SITTING_IN_ANY_CAR ambush_cop[cop_i]
							IF cop_i < 2
								IF TIMERA > leave_time1
									do_cop_task = 1
								ENDIF
							ENDIF
							IF cop_i < 4
							AND cop_i > 1
								IF TIMERA > leave_time2
									do_cop_task = 1
								ENDIF
							ENDIF
							IF cop_i > 3
								IF TIMERA > leave_time3
									do_cop_task = 1
								ENDIF
							ENDIF

							IF do_cop_task = 1
								OPEN_SEQUENCE_TASK cat_ambush_seq
									IF cop_i = 0
									OR cop_i = 3
									OR cop_i = 4
										TASK_TOGGLE_DUCK -1 TRUE
									ENDIF
									TASK_LEAVE_ANY_CAR -1
									TASK_GO_STRAIGHT_TO_COORD -1 ambush_point_x[cop_i] ambush_point_y[cop_i] ambush_point_z[cop_i] PEDMOVE_RUN -2
									TASK_AIM_GUN_AT_CHAR -1 catalina -2
								CLOSE_SEQUENCE_TASK cat_ambush_seq
								PERFORM_SEQUENCE_TASK ambush_cop[cop_i] cat_ambush_seq
								CLEAR_SEQUENCE_TASK cat_ambush_Seq
							ENDIF
						ENDIF
					ENDIF
					cop_i ++
				ENDWHILE
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF catalina_flag = 9

	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		cat_mini_flag = 3
		audio_flag = 5
	ENDIF

    // small cut of catalina walking into cabin
	IF cat_mini_flag = 0

		ALTER_WANTED_LEVEL player1 0

		audio_flag = 5
		audio_slot[1] = 0
		audio_slot[2] = 0
		play_audio_global = 1
		cat_mini_flag = 1
	ENDIF

	IF cat_mini_flag = 1
		IF play_audio = 0
			IF audio_flag = 1

				
				
				
				
				
				
				
				
				
				
				
				

				only_play_audio_if_player_in_car = 0

				IF cat_counter = 0


					audio_for_char[1] = 1
					audio_for_char[2] = 1
					audio_for_char[3] = 1
					audio_for_char[4] = 2
					audio_for_char[5] = 2
					audio_for_char[6] = 1
					audio_for_char[7] = 1
					audio_for_char[8] = 1
					audio_for_char[9] = 2
					audio_for_char[10] = 2
					audio_for_char[11] = 2
					audio_for_char[12] = 2





					audio_sound[1] = SOUND_CAT_EA
					audio_sound[2] = SOUND_CAT_EB			
					audio_sound[3] = SOUND_CAT_EC
					audio_sound[4] = SOUND_CAT_ED
					audio_sound[5] = SOUND_CAT_EE
					audio_sound[6] = SOUND_CAT_EF
					audio_sound[7] = SOUND_CAT_EG
					audio_sound[8] = SOUND_CAT_EH
					audio_sound[9] = SOUND_CAT_EI
					audio_sound[10] = SOUND_CAT_EJ
					audio_sound[11] = SOUND_CAT_EK
					audio_sound[12] = SOUND_CAT_EL


					$audio_text[1] = &CAT_EA
					$audio_text[2] = &CAT_EB
					$audio_text[3] = &CAT_EC
					$audio_text[4] = &CAT_ED
					$audio_text[5] = &CAT_EE
					$audio_text[6] = &CAT_EF
					$audio_text[7] = &CAT_EG
					$audio_text[8] = &CAT_EH
					$audio_text[9] = &CAT_EI
					$audio_text[10] = &CAT_EJ
					$audio_text[11] = &CAT_EK
					$audio_text[12] = &CAT_EL
 

					play_audio = 1
					play_audio_for = 12
			
				ENDIF

				IF cat_counter = 1

					audio_for_char[1] = 1
					audio_for_char[2] = 2
					audio_for_char[3] = 2
					audio_for_char[4] = 2
					audio_for_char[5] = 1
					audio_for_char[6] = 1

					audio_sound[1] = SOUND_CAT_HA
					audio_sound[2] = SOUND_CAT_HB
					audio_sound[3] = SOUND_CAT_HC
					audio_sound[4] = SOUND_CAT_HD
					audio_sound[5] = SOUND_CAT_HE
					audio_sound[6] = SOUND_CAT_HF

					$audio_text[1] = &CAT_HA
					$audio_text[2] = &CAT_HB
					$audio_text[3] = &CAT_HC
					$audio_text[4] = &CAT_HD
					$audio_text[5] = &CAT_HE
					$audio_text[6] = &CAT_HF

					play_audio = 1
					play_audio_for = 6
				ENDIF

				IF cat_counter = 2

					audio_for_char[1] = 1
					audio_for_char[2] = 1
					audio_for_char[3] = 1

					audio_sound[1] = SOUND_CAT_MA
					audio_sound[2] = SOUND_CAT_MB
					audio_sound[3] = SOUND_CAT_MC

					$audio_text[1] = &CAT_MA
					$audio_text[2] = &CAT_MB
					$audio_text[3] = &CAT_MC

					play_audio = 1
					play_audio_for = 3
				ENDIF

				IF cat_counter = 3

					audio_for_char[1] = 1
					audio_for_char[2] = 2
					audio_for_char[3] = 1
					audio_for_char[4] = 1
					audio_for_char[5] = 1
					audio_for_char[6] = 1

					audio_sound[1] = SOUND_CAT_PA
					audio_sound[2] = SOUND_CAT_PB
					audio_sound[3] = SOUND_CAT_PC
					audio_sound[4] = SOUND_CAT_PD
					audio_sound[5] = SOUND_CAT_PE
					audio_sound[6] = SOUND_CAT_PF

					$audio_text[1] = &CAT_PA
					$audio_text[2] = &CAT_PB
					$audio_text[3] = &CAT_PC
					$audio_text[4] = &CAT_PD
					$audio_text[5] = &CAT_PE
					$audio_text[6] = &CAT_PF
					play_audio = 1
					play_audio_for = 6
				ENDIF

				LVAR_INT cat_mini_flag temp_seq	cat_seq cat_time

				IF NOT IS_CHAR_DEAD catalina
					GET_CHAR_COORDINATES catalina x y z
				ENDIF

				CLEAR_AREA x y z 20.0 TRUE

				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL PLAYER1 OFF

				SET_FIXED_CAMERA_POSITION 854.5403 -22.1425 64.3802 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 855.2749 -22.7951 64.1942 JUMP_CUT

				IF IS_CHAR_IN_ANY_CAR scplayer
					IF NOT IS_CHAR_DEAD catalina
						IF NOT IS_CHAR_IN_ANY_CAR catalina
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car	
							WARP_CHAR_INTO_CAR catalina car
						ENDIF
					ENDIF
				ENDIF
					

				IF NOT IS_CHAR_DEAD catalina
					IF NOT IS_CHAR_IN_ANY_CAR catalina
//						IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina x y z 5.0 5.0 5.0 FALSE

							
							SET_CHAR_COORDINATES scplayer 857.4263 -29.5418 62.1858
							SET_CHAR_COORDINATES catalina 858.4263 -29.5418 62.1858
							SET_CHAR_HEADING catalina 161.4460
							SET_CHAR_HEADING catalina 340.4460
							cat_in_players_group = 0
							REMOVE_CHAR_FROM_GROUP catalina
							
								
//						ENDIF
					ENDIF
					OPEN_SEQUENCE_TASK temp_seq
						IF IS_CHAR_IN_ANY_CAR catalina
							STORE_CAR_CHAR_IS_IN_NO_SAVE catalina car
							TASK_LEAVE_CAR -1 car
						ENDIF  
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
						TASK_LOOK_AT_CHAR -1 scplayer 99999
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK catalina temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF

				cat_mini_flag = 2
				
			ENDIF
		ENDIF
	ENDIF

	IF cat_mini_flag = 2
		IF play_audio = 0
			IF audio_flag = 1
				


		        IF NOT IS_CHAR_DEAD catalina
		              OPEN_SEQUENCE_TASK cat_seq
		                    IF IS_CHAR_IN_ANY_CAR catalina
		                          STORE_CAR_CHAR_IS_IN_NO_SAVE catalina car
		                          TASK_LEAVE_CAR -1 car
		                    ENDIF
		                    TASK_GO_STRAIGHT_TO_COORD -1 869.0271 -28.2591 62.1893 PEDMOVE_WALK      20000
		                    TASK_GO_STRAIGHT_TO_COORD -1 870.2891 -25.0424 62.9858 PEDMOVE_WALK      5000
		              CLOSE_SEQUENCE_TASK cat_seq
		              PERFORM_SEQUENCE_TASK catalina cat_seq
		              CLEAR_SEQUENCE_TASK cat_seq
		        ENDIF
		        

				cat_mini_flag = 3
				cat_time = TIMERA + 5000
			ENDIF
 		ENDIF

    ENDIF

      // wait to finish
      IF cat_mini_flag = 3
            
            IF NOT IS_CHAR_DEAD catalina
                  GET_SCRIPT_TASK_STATUS catalina PERFORM_SEQUENCE_TASK task_status
                  IF task_status = FINISHED_TASK
				  OR TIMERA > cat_time
	                  SET_CAMERA_BEHIND_PLAYER
	                  RESTORE_CAMERA_JUMPCUT
	                  IF DOES_CHAR_EXIST catalina
	                        DELETE_CHAR catalina
	                  ENDIF
	                  SET_PLAYER_CONTROL player1 TRUE
	                  SWITCH_WIDESCREEN OFF
					  CLEAR_PRINTS
					  GOTO mission_cat2_passed
                  ENDIF
            ENDIF
      ENDIF



 


ENDIF

//IF catalina_flag = 9
//	IF NOT IS_CAR_DEAD copcar2//WARNING!!! (if bike is too far away or destroyed - make her get on players bike or steal a vehicle or try and create another)
//		IF IS_CHAR_SITTING_IN_CAR catalina copcar2
//			START_PLAYBACK_RECORDED_CAR_USING_AI copcar2 115
//			-- catalina_flag
//		ENDIF
//	ENDIF
//ENDIF

		





//OUTSIDE BANKS BACK DOOR LOCATE
//2317.4285 2.9314 26.7164 3.0 2.0 1.2

//BANK END OF BLOCK AREA 1
//2318.7502 -6.5675 31.3712 15.0 11.2 5.8

//MIDDLE AREA 2 & 3
//2318.7502 15.8125 31.3712 15.0 11.2 5.8

//BIKE END OF ALLEY AREA 4
//2318.7502 54.3324 31.3712 15.0 11.2 5.8


//ROOF OF PIZZA SHOP
//2328.3840 2.0130 35.4040 5.6 7.6 5.4

//ROOF OF BANK
//2313.1731 -6.9870 35.4040 9.2 12.0 5.0

//LAZER SHOP ROOF
//2310.1777 14.5224 31.3712 6.6 7.2 2.8

//DRY CLEANER ROOF
//2309.4468 32.0223 31.3712 5.2 10.0 2.8

//FURNITURE SHOP
//2308.6724 56.5422 31.9712 5.6 12.8 3.0

//BACK ROOF OF RECORD SHOP
//2311.6362 74.2422 30.7711 6.0 2.4 2.2

//LOCATE FOR PLAYER AND CATALINA TO TRIGGER COPS ARRIVING ON BIKES AT END OF ALLEY
//2315.5576 70.7181 31.4998 11.4 8.4 6.0

//END OF PED NODES ON BANK ROOF
//2317.0110 -2.2289 31.5313

//PLAYERS Z VALUE > IF HES ON THE ROOFS.
//28.65




GOTO mission_cat2_loop


alarm_off:

			ALTER_WANTED_LEVEL_NO_DROP player1 3

			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2312.0940 -9.6433 28.6790 SOUND_CAT2_SECURITY_ALARM

			play_audio = 8
			play_audio_for = 3

			IF NOT IS_CHAR_DEAD catalina
				TASK_TURN_CHAR_TO_FACE_CHAR catalina scplayer
			ENDIF


//			CLEAR_PRINTS
//			PRINT_NOW CAT2_12 5000 1 //Shit, the alarm has been set off.

RETURN

	
// ************************************ MISSION FAILED *************************************
mission_cat2_failed:



PRINT_BIG M_FAIL 5000 1

WAIT 2000

IF NOT IS_CHAR_DEAD scplayer
//	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2312.5610 -9.0629 27.1588 40.0 40.0 20.0 FALSE
	IF IS_CHAR_IN_AREA_3D scplayer 2303.9031 -18.2879 25.5416 2321.1470 0.7716 30.8122 FALSE
		IF NOT IS_CHAR_DEAD scplayer
		
			DO_FADE 800 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			WAIT 700					   

			IF IS_CHAR_IN_AREA_3D scplayer 2303.9031 -18.2879 25.5416 2321.1470 0.7716 30.8122 FALSE

				SET_CHAR_COORDINATES scplayer 2300.1116 -16.5618 25.4844
				SET_CHAR_HEADING scplayer 90.0
			ENDIF

			
			
			DO_FADE 600 FADE_IN
		ENDIF
	ENDIF
ENDIF


RETURN

   

// ************************************ MISSION PASSED *************************************
mission_cat2_passed:

cat_counter ++
++ cat_getaway_dialogue

SET_INT_STAT PASSED_CAT2 1

cat_money_collected += 10000

PRINT_WITH_NUMBER_BIG ( M_PASS ) 10000 5000 1 //"Mission Passed!"
ADD_SCORE player1 10000 //amount of cash reward



CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
//REGISTER_MISSION_PASSED cat2
PLAYER_MADE_PROGRESS 1
REGISTER_MISSION_PASSED ( CAT_2 ) //Used in the stats 
flag_cat_mission2_passed = 1
//
IF cat_otb_banter = 1
	cat_otb_banter = 2
ENDIF
IF cat_liquor_banter = 1
	cat_liquor_banter = 2
ENDIF
//



RETURN
		


// *********************************** MISSION CLEANUP *************************************
mission_cat2_cleanup:


		CLEAR_MISSION_AUDIO 3

		IF DOES_OBJECT_EXIST pc_door[0]
			DELETE_OBJECT pc_door[0]
		ENDIF
		CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2322.845 8.304 25.483 pc_door[0]
		DONT_REMOVE_OBJECT pc_door[0]
		set_object_collision_damage_effect pc_door[0] FALSE
		FREEZE_OBJECT_POSITION pc_door[0] TRUE
		

		IF DOES_OBJECT_EXIST pc_door[1]
			DELETE_OBJECT pc_door[1]
		ENDIF
			CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2316.233 0.712 25.742 pc_door[1]
			DONT_REMOVE_OBJECT pc_door[1]
			SET_OBJECT_HEADING pc_door[1] 270.0
			FREEZE_OBJECT_POSITION pc_door[1] TRUE 
			set_object_collision_damage_effect pc_door[1] FALSE
		

		IF DOES_OBJECT_EXIST pc_door[2]
			DELETE_OBJECT pc_door[2]
		ENDIF

			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -17.744 25.742 pc_door[2]
			DONT_REMOVE_OBJECT pc_door[2]
			FREEZE_OBJECT_POSITION pc_door[2] TRUE 
			set_object_collision_damage_effect pc_door[2] FALSE
		

		IF DOES_OBJECT_EXIST pc_door[3]
			DELETE_OBJECT pc_door[3]
		ENDIF
			CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -14.583 25.742 pc_door[3]
			DONT_REMOVE_OBJECT pc_door[3]
			FREEZE_OBJECT_POSITION pc_door[3] TRUE 
			SET_OBJECT_HEADING pc_door[3] 180.0
			set_object_collision_damage_effect pc_door[3] FALSE
		



SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0



MARK_MODEL_AS_NO_LONGER_NEEDED COPCARRU
MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
MARK_MODEL_AS_NO_LONGER_NEEDED CSHER
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
//MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
MARK_MODEL_AS_NO_LONGER_NEEDED CSHER
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARRU
MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD
MARK_MODEL_AS_NO_LONGER_NEEDED SWMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED WFYSTEW
MARK_MODEL_AS_NO_LONGER_NEEDED ENFORCER
MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM1
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM2
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_ATM3
MARK_MODEL_AS_NO_LONGER_NEEDED MTSAFE
MARK_MODEL_AS_NO_LONGER_NEEDED CR_DOOR_01
MARK_MODEL_AS_NO_LONGER_NEEDED CR_DOOR_03


REMOVE_CAR_RECORDING 156
REMOVE_CAR_RECORDING 157
REMOVE_CAR_RECORDING 123
REMOVE_CAR_RECORDING 124
REMOVE_CAR_RECORDING 158
REMOVE_CAR_RECORDING 159
REMOVE_CAR_RECORDING 160
REMOVE_CAR_RECORDING 161
REMOVE_CAR_RECORDING 116
REMOVE_CAR_RECORDING 117
REMOVE_CAR_RECORDING 118
REMOVE_CAR_RECORDING 119
REMOVE_CAR_RECORDING 123
REMOVE_CAR_RECORDING 124

REMOVE_CAR_RECORDING 160
REMOVE_CAR_RECORDING 161
REMOVE_CAR_RECORDING 156
REMOVE_CAR_RECORDING 157
REMOVE_CAR_RECORDING 158
REMOVE_CAR_RECORDING 159


REMOVE_ANIMATION SHOP
REMOVE_ANIMATION MISC
REMOVE_ANIMATION OTB
REMOVE_ANIMATION GANGS
REMOVE_ANIMATION ROB_BANK





SWITCH_ROADS_ON 2344.5178 36.1468 36.0359 2269.7183 -44.3760 23.0569


REMOVE_BLIP catalina_blip
REMOVE_BLIP mission_blip
REMOVE_BLIP atm_blip[0]
REMOVE_BLIP atm_blip[1]
REMOVE_BLIP atm_blip[2]
REMOVE_BLIP atm_blip[3]

MARK_OBJECT_AS_NO_LONGER_NEEDED	atm_static[0]
MARK_OBJECT_AS_NO_LONGER_NEEDED	atm_static[1]
MARK_OBJECT_AS_NO_LONGER_NEEDED	atm_static[2]
//MARK_OBJECT_AS_NO_LONGER_NEEDED	atm_static[3]

//MARK_OBJECT_AS_NO_LONGER_NEEDED	door[0]
//MARK_OBJECT_AS_NO_LONGER_NEEDED	door[1]
//MARK_OBJECT_AS_NO_LONGER_NEEDED	door[2]
//MARK_OBJECT_AS_NO_LONGER_NEEDED	door[3]

//IF DOES_OBJECT_EXIST pc_door[0]
//	set_object_collision_damage_effect pc_door[0] true
//ENDIF
//IF DOES_OBJECT_EXIST pc_door[1]
//	set_object_collision_damage_effect pc_door[1] true
//ENDIF
//IF DOES_OBJECT_EXIST pc_door[2]
//	set_object_collision_damage_effect pc_door[2] true
//ENDIF
//IF DOES_OBJECT_EXIST pc_door[3]
//	set_object_collision_damage_effect pc_door[3] true
//ENDIF

IF NOT IS_CAR_DEAD copcar1
	MARK_CAR_AS_NO_LONGER_NEEDED copcar1
ENDIF

IF NOT IS_CAR_DEAD copcar2
	MARK_CAR_AS_NO_LONGER_NEEDED copcar2
ENDIF

IF NOT IS_CAR_DEAD copcar3
	MARK_CAR_AS_NO_LONGER_NEEDED copcar3
ENDIF

IF NOT IS_CAR_DEAD copcar4
	MARK_CAR_AS_NO_LONGER_NEEDED copcar4
ENDIF

//IF NOT IS_CHAR_DEAD alley_cop1
//	FREEZE_CHAR_POSITION alley_cop1 FALSE
//	MARK_CHAR_AS_NO_LONGER_NEEDED alley_cop1
//
//ENDIF
//
//IF NOT IS_CHAR_DEAD alley_cop2
//	FREEZE_CHAR_POSITION alley_cop2 FALSE
//	MARK_CHAR_AS_NO_LONGER_NEEDED alley_cop2
//ENDIF



IF NOT IS_CHAR_DEAD copcar1_cop1
	FREEZE_CHAR_POSITION copcar1_cop1 FALSE 
	MARK_CHAR_AS_NO_LONGER_NEEDED copcar1_cop1

ENDIF

IF NOT IS_CHAR_DEAD copcar1_cop2
	FREEZE_CHAR_POSITION copcar1_cop2 FALSE 
	MARK_CHAR_AS_NO_LONGER_NEEDED copcar1_cop2
ENDIF


IF NOT IS_CHAR_DEAD copcar2_cop1
	FREEZE_CHAR_POSITION copcar2_cop1 FALSE 
	MARK_CHAR_AS_NO_LONGER_NEEDED copcar2_cop1 

ENDIF

IF NOT IS_CHAR_DEAD copcar3_cop1
	FREEZE_CHAR_POSITION copcar3_cop1 FALSE 
	MARK_CHAR_AS_NO_LONGER_NEEDED copcar3_cop1 

ENDIF

IF NOT IS_CHAR_DEAD copcar4_cop1
	FREEZE_CHAR_POSITION copcar4_cop1 FALSE
	MARK_CHAR_AS_NO_LONGER_NEEDED copcar4_cop1 
 
ENDIF

IF NOT IS_CHAR_DEAD c2_fleeing_ped
	MARK_CHAR_AS_NO_LONGER_NEEDED c2_fleeing_ped
ENDIF


REMOVE_SOUND bank_alarm

flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start

//ALTER_WANTED_LEVEL Player1 0
SET_WANTED_MULTIPLIER 1.0

MISSION_HAS_FINISHED
RETURN



players_group:

LVAR_INT cat_in_group catalina_blip cat_hates

IF cat_in_players_group = 1

		IF cat_in_group = 1
			IF NOT IS_CHAR_DEAD catalina
				IF NOT IS_GROUP_MEMBER catalina players_group 
					cat_in_group = 0
					ADD_BLIP_FOR_CHAR catalina catalina_blip
					SET_BLIP_AS_FRIENDLY catalina_blip TRUE
					CHANGE_BLIP_DISPLAY mission_blip NEITHER
					CLEAR_PRINTS
					PRINT CAT2_14 4000 1
				ENDIF
			ENDIF
		ENDIF
		IF cat_in_group = 0
			IF NOT IS_CHAR_DEAD catalina
				IF IS_GROUP_MEMBER catalina players_group
					cat_in_group = 1
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer catalina 10.0 10.0 FALSE
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group catalina
						REMOVE_BLIP catalina_blip
						CHANGE_BLIP_DISPLAY mission_blip BOTH
						cat_in_group = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			IF cat_hates = 0
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_DAMAGE
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_WEAPON
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_GUN_AIMED_AT
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_COLLISION
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_SEEN_COP
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
//				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_WEAPON TASK_GROUP_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
				SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
				cat_hates = 1
			ENDIF
		ELSE
			IF cat_hates = 1
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_DAMAGE
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_WEAPON
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_GUN_AIMED_AT
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_VEHICLE_DAMAGE_COLLISION
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_SEEN_COP
				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_DAMAGE TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_GUN_AIMED_AT TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cat_decisions EVENT_SEEN_COP TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE
				SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
				cat_hates = 0
			ENDIF
		ENDIF


//
//		IF NOT IS_CHAR_DEAD catalina
//			IF IS_CHAR_IN_ANY_CAR scplayer
//			AND IS_CHAR_IN_ANY_CAR catalina
//				IF cat_hates = 1
//					CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
//					SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1
//					cat_hates = 0
//				ENDIF					
//			ELSE
//				IF cat_hates = 0
//					CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1
//					SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
//					cat_hates = 1
//				ENDIF
//			ENDIF
//		ENDIF

ENDIF



RETURN


cat2_audio:


	SWITCH audio_flag

		CASE 0 //first time game starts

			LVAR_TEXT_LABEL audio_text[60]
			LVAR_INT audio_sound[60] audio_slot[3] play_slot  
			LVAR_INT next_audio  
			LVAR_INT audio_flag play_audio play_audio_for only_play_audio_if_player_in_car 
//			VAR_INT play_audio_global // make sounds not attached to char

			LVAR_INT audio_for_char[60] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay audio_i audio_char audio_count force_audio play_audio_delay


			

//			VIEW_INTEGER_VARIABLE play_audio play_audio
//			VIEW_INTEGER_VARIABLE audio_slot[1] slot1
//			VIEW_INTEGER_VARIABLE audio_slot[2] slot2

			$audio_text[0] = &CAT4_BI

			$audio_text[1] = &CAT4_CA //You're on crowd control so don't take any shit!
			audio_sound[1] = SOUND_CAT4_CA

			$audio_text[2] = &CAT4_CB //Yes’m, Miss Catalina, Miss.
			audio_sound[2] = SOUND_CAT4_CB

			$audio_text[3] = &CAT4_CD //Don’t even think about doin’ anything, motherfucker!
			audio_sound[3] = SOUND_CAT4_CD

			$audio_text[4] = &CAT4_CE //Hand over every last dollar – NOW, BITCH!
			audio_sound[4] = SOUND_CAT4_CE

			$audio_text[5] = &CAT4_CF //I’m going to empty the safe.
			audio_sound[5] = SOUND_CAT4_CF

			$audio_text[6] = &CAT4_CG //Keep these idiots covered!
			audio_sound[6] = SOUND_CAT4_CG

			$audio_text[7] = &CAT4_CH //Aigh’t, you heard the lady, no heroic shit!
			audio_sound[7] = SOUND_CAT4_CH

			$audio_text[8] = &CAT4_EA //Oh shit!
			audio_sound[8] = SOUND_CAT4_EA

			$audio_text[9] = &CAT4_EC //Shit! I give you one simple job!
			audio_sound[9] = SOUND_CAT4_EC

			$audio_text[10] = &CAT4_ED //Idiota!
			audio_sound[10] = SOUND_CAT4_ED

			$audio_text[11] = &CAT4_BA //Shit, I just bought another donut! Don't criminals have any consideration?
			audio_sound[11] = SOUND_CAT4_BA

			$audio_text[12] = &CAT4_AB //We can collect that bribe later. Might as well go and take a look.
			audio_sound[12] = SOUND_CAT4_AB

			$audio_text[13] = &CAT4_AC //We know you’re in there!
			audio_sound[13] = SOUND_CAT4_AC

			$audio_text[14] = &CAT4_AD //The game’s up! Come on out!
			audio_sound[14] = SOUND_CAT4_AD

			$audio_text[15] = &CAT4_AE //Come out real peacable, like!
			audio_sound[15] = SOUND_CAT4_AE

			$audio_text[16] = &CAT4_FB //Smash the ATM’s and get as much cash as possible!
			audio_sound[16] = SOUND_CAT4_FB

			$audio_text[17] = &CAT4_AF //Give up, you’re surrounded!
			audio_sound[17] = SOUND_CAT4_AF

			$audio_text[18] = &CAT4_FD //Quick, we haven’t much time!
			audio_sound[18] = SOUND_CAT4_FD

			$audio_text[19] = &CAT4_GB //Better take the back door!
			audio_sound[19] = SOUND_CAT4_GB

			$audio_text[20] = &CAT4_HH //Follow me!
			audio_sound[20] = SOUND_CAT4_HH

			$audio_text[21] = &CAT4_HF //Cops, take cover!
			audio_sound[21] = SOUND_CAT4_HF
						
			$audio_text[22] = &CAT4_AG //Thought you might try the back way!
			audio_sound[22] = SOUND_CAT4_AG

			$audio_text[23] = &CAT4_AH //We were waiting for yer!
			audio_sound[23] = SOUND_CAT4_AH

			$audio_text[24] = &CAT4_HI //Cover me!
			audio_sound[24] = SOUND_CAT4_HI

			$audio_text[25] = &CAT4_BF //Give up, you're surrounded
			audio_sound[25] = SOUND_CAT4_BF

			$audio_text[26] = &CAT4_KF //pick it up a little you retard
			audio_sound[26] = SOUND_CAT4_KF

			$audio_text[27] = &CAT4_HB  //Look out carl
			audio_sound[27] = SOUND_CAT4_HB

			$audio_text[28] = &CAT4_HG //keep going!
			audio_sound[28] = SOUND_CAT4_HG

			$audio_text[29] = &CAT4_AJ //You two; FREEZE!
			audio_sound[29] = SOUND_CAT4_AJ

			$audio_text[30] = &CAT4_JA //Grab a bike and follow me!
			audio_sound[30] = SOUND_CAT4_JA

			$audio_text[31] = &CAT4_JB //You think you can keep up with a real woman?
			audio_sound[31] = SOUND_CAT4_JB 

			$audio_text[32] = &CAT4_KA //You think you can keep up with a real woman?
			audio_sound[32] = SOUND_CAT4_KA

			$audio_text[33] = &CAT4_KB //You think you can keep up with a real woman?
			audio_sound[33] = SOUND_CAT4_KB

			$audio_text[34] = &CAT4_KC //You think you can keep up with a real woman?
			audio_sound[34] = SOUND_CAT4_KC

			$audio_text[35] = &CAT4_KD //You think you can keep up with a real woman?
			audio_sound[35] = SOUND_CAT4_KD

			$audio_text[36] = &CAT4_KE //You think you can keep up with a real woman?
			audio_sound[36] = SOUND_CAT4_KE

			$audio_text[37] = &CAT3_EC //C’mon, Carl, move it!
			audio_sound[37] = SOUND_CAT3_EC

			$audio_text[38] = &CAT_GJ //C’mon, Carl, move it!
			audio_sound[38] = SOUND_CAT_GJ

			$audio_text[39] = &CAT4_BK //Attention all units
			audio_sound[39] = SOUND_CAT4_BK










			audio_for_char[1] = 1
			audio_for_char[2] = 2
			audio_for_char[3] = 2
			audio_for_char[4] = 1
			audio_for_char[5] = 1
			audio_for_char[6] = 1
			audio_for_char[7] = 2
			audio_for_char[8] = 2
			audio_for_char[9] = 1
			audio_for_char[10] = 1
			audio_for_char[11] = 3
			audio_for_char[12] = 4
			audio_for_char[13] = 3
			audio_for_char[14] = 4
			audio_for_char[15] = 3
			audio_for_char[16] = 1
			audio_for_char[17] = 0
			audio_for_char[18] = 1
			audio_for_char[19] = 1
			audio_for_char[20] = 1
			audio_for_char[21] = 5
			audio_for_char[22] = 5
			audio_for_char[23] = 1
			audio_for_char[24] = 1
			audio_for_char[25] = 6
			audio_for_char[26] = 1
			audio_for_char[27] = 1
			audio_for_char[28] = 1
			audio_for_char[29] = 6
			audio_for_char[30] = 1
			audio_for_char[31] = 1
			audio_for_char[32] = 0
			audio_for_char[33] = 0
			audio_for_char[34] = 0
			audio_for_char[35] = 0
			audio_for_char[36] = 0
			audio_for_char[37] = 1
			audio_for_char[38] = 1
			audio_for_char[39] = 0


			audio_actor[1] = catalina
			audio_actor[2] = scplayer

			audio_actor[5] = scplayer //alley cop1
			audio_actor[6] = scplayer //alley cop2


			
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
				IF TIMERA > play_audio_delay
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
										IF play_audio_global = 0   
											ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor								 
										ENDIF
										IF NOT IS_CHAR_TALKING this_actor
											play_audio_now = 1
											START_CHAR_FACIAL_TALK this_actor 15000
										ELSE
											DISABLE_CHAR_SPEECH this_actor FALSE
										ENDIF

										IF NOT this_actor = 0
											IF NOT IS_CHAR_DEAD this_Actor
												SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor TRUE
											ENDIF
										ENDIF
									ENDIF
								ENDIF

								IF audio_for_char[play_audio] = 0
									play_audio_now = 1
								ENDIF

								IF only_play_audio_if_player_in_car = 1
									IF NOT IS_CHAR_IN_ANY_CAR scplayer
										play_audio_now = 0
									ENDIF
								ENDIF
										

								IF play_audio_now = 1							
									PLAY_MISSION_AUDIO play_slot
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
							ELSE
								audio_flag = 6
							ENDIF
						ELSE
							LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		BREAK


	   
		CASE 2 // check if audio has/should finish
//			fake_flag = 13
			IF HAS_MISSION_AUDIO_FINISHED play_slot
				IF NOT IS_CHAR_DEAD this_actor
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
				ENDIF
				audio_flag++
			ELSE
				IF DOES_CHAR_EXIST this_actor
					IF IS_CHAR_DEAD this_actor
						audio_flag++
					ENDIF
				ENDIF
			ENDIF
		BREAK

		CASE 3 //clear audio
				IF NOT IS_CHAR_DEAD this_actor
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
				ENDIF
//			fake_flag = 14
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF
			audio_flag++
		BREAK

		CASE 4 //request next audio
//			fake_flag = 15
				IF NOT IS_CHAR_DEAD this_actor
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
				ENDIF
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
				IF NOT IS_CHAR_DEAD this_actor
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
				ENDIF
			audio_flag = 1
			play_audio = 0
			play_audio_for = 0
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK
	ENDSWITCH


RETURN

/*
CASE 1 //waiting to play audio
			
			IF NOT play_audio = 0
				fake_flag = 1
				IF TIMERA > play_delay
					fake_flag = 2
					IF HAS_MISSION_AUDIO_FINISHED 1
					AND HAS_MISSION_AUDIO_FINISHED 2
						fake_flag = 3

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
								fake_flag = 4
							ENDIF
						ENDIF			

						IF HAS_MISSION_AUDIO_LOADED play_slot
							fake_flag = 5
							IF NOT audio_for_char[play_audio] = 0
								fake_flag = 6
								actor_int = audio_for_char[play_audio]
								this_actor = audio_actor[actor_int]
								IF NOT IS_CHAR_DEAD this_actor
									fake_flag = 7
									IF NOT IS_CHAR_TALKING this_actor
										fake_flag = 8
										ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor
										PLAY_MISSION_AUDIO play_slot
										CLEAR_PRINTS
										PRINT $audio_text[play_audio] 10000 1
										audio_flag ++

										play_audio ++
										next_audio = play_audio

										// if the other slot doesn't already have the next audio loaded, then load it.
										IF NOT audio_sound[play_audio] = 0
											fake_flag = 9
											IF play_slot = 1									
												fake_flag = 10
												IF NOT audio_slot[2] = play_audio
													fake_flag = 11
													LOAD_MISSION_AUDIO 2 audio_sound[play_audio]	
													audio_slot[2] = play_audio
												ENDIF
											ELSE
												IF NOT audio_slot[1] = play_audio
													fake_flag = 12
													LOAD_MISSION_AUDIO 1 audio_sound[play_audio]	
													audio_slot[1] = play_audio
												ENDIF
											ENDIF
										ENDIF
									ELSE
										DISABLE_CHAR_SPEECH this_actor FALSE
									ENDIF
								ENDIF
							ENDIF
						ELSE
							LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		BREAK
*/



}