MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// ************************************* final part A **************************************
// ************************************* Carter Block **************************************
// *********** Inspired by Zelda, Metroid, MGS, Perfect Dark, Time Crisis, GTA *************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_finaleA

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_finaleA_failed
ENDIF

GOSUB mission_cleanup_finaleA

MISSION_END

{

// ********************************** Variables for shootout *********************************

LVAR_FLOAT speed_fa
LVAR_FLOAT camposX_fa camposY_fa camposZ_fa camlookX_fa camlookY_fa camlookZ_fa 
LVAR_FLOAT camposincX_fa camposincY_fa camposincZ_fa camlookincX_fa camlookincY_fa camlookincZ_fa
LVAR_INT carter_DM
LVAR_INT cartercoward_DM
LVAR_INT cartertough_DM
LVAR_INT swatgroup_GMD
LVAR_INT ganggroup_GMD
LVAR_INT gang2group_GMD
LVAR_INT carter_interior
LVAR_INT copcar1_fa
LVAR_INT copcar2_fa
LVAR_INT copcar3_fa
LVAR_INT swatvan_fa
LVAR_INT car1_fa
LVAR_INT car2_fa
LVAR_INT car3_fa
LVAR_INT car4_fa
LVAR_INT car5_fa
LVAR_INT bmx_fa
LVAR_INT swat1_fa
LVAR_INT swat2_fa
LVAR_INT swat3_fa
LVAR_INT swat4_fa
LVAR_INT swat5_fa
LVAR_INT swat6_fa
LVAR_INT swat7_fa
LVAR_INT gang1_fa
LVAR_INT gang2_fa
LVAR_INT gang3_fa
LVAR_INT gang4_fa
LVAR_INT gang5_fa
LVAR_INT gang6_fa
LVAR_INT gang7_fa
LVAR_INT gang8_fa
LVAR_INT gang9_fa
LVAR_INT gang10_fa
LVAR_INT gang11_fa
LVAR_INT gang12_fa
LVAR_INT gang13_fa
LVAR_INT gang14_fa
LVAR_INT gang15_fa
LVAR_INT gang16_fa
LVAR_INT gang17_fa
LVAR_INT gang18_fa
LVAR_INT gang19_fa
LVAR_INT gang20_fa
LVAR_INT hoochie1_fa
LVAR_INT hoochie3_fa
LVAR_INT hoochie4_fa
LVAR_INT healthforsmoke_fa 
LVAR_INT wallhealth_fa
//objects
LVAR_INT barrel1_fa
LVAR_INT barrel2_fa
LVAR_INT barrel3_fa
LVAR_INT barrel4_fa
LVAR_INT door_fa
LVAR_INT wall_fa

//fx
LVAR_INT wallsmashfx_fa

//counters
VAR_INT smokehealth_fa 

//sequences
LVAR_INT runnostay_faseq
LVAR_INT stayshoot_faseq
LVAR_INT stayshootnoduck_faseq
LVAR_INT rolloutr_faseq
LVAR_INT rolloutl_faseq
LVAR_INT peekright_faseq
LVAR_INT enemy_fa
LVAR_INT peekleft_faseq
LVAR_FLOAT enemyx_fa 
LVAR_FLOAT enemyy_fa 
LVAR_FLOAT enemyz_fa
LVAR_INT enemytarget_fa
LVAR_INT enemytarget2_fa
LVAR_INT run2shoot_faseq
LVAR_INT shootimer_s4
LVAR_FLOAT enemyx2_fa 
LVAR_FLOAT enemyy2_fa 
LVAR_FLOAT enemyz2_fa
LVAR_FLOAT heading_fa
LVAR_INT flee_faseq


//text
LVAR_TEXT_LABEL $text_label_fa
LVAR_INT audio_label_fa
LVAR_TEXT_LABEL $input_text_fa

//blips
LVAR_INT sweet_fablip
LVAR_INT swatvan_fablip
LVAR_INT carter_fablip
LVAR_INT smoke_fablip
LVAR_INT irgoggles_fablip

//pickups
LVAR_INT armour1_fa
LVAR_INT armour2_fa
LVAR_INT armour3_fa
LVAR_INT armour4_fa
LVAR_INT health1_fa
LVAR_INT health2_fa
LVAR_INT health3_fa
LVAR_INT m4pickup_fa
LVAR_INT shotgunpickup_fa 
LVAR_INT irgoggles_fa
LVAR_INT shotgunpickup2_fa

//added to stop ped already has this command bug
LVAR_INT smoke_progress_fa smoke_can_kill_player_fa
//

//flags
LVAR_INT ram_faflag
LVAR_INT carter_faflag
LVAR_INT lockcar_faflag 
LVAR_INT playerincar_faflag
LVAR_INT coordshoot_faflag
LVAR_INT skipcutscene_faflag
LVAR_INT swatvan_faflag
LVAR_INT swat1_faflag
LVAR_INT swat5_faflag
LVAR_INT floorg_faflag
LVAR_INT floor1_faflag
LVAR_INT floor2_faflag
LVAR_INT floor3_faflag
LVAR_INT gang1_faflag
LVAR_INT gang2_faflag
LVAR_INT gang3_faflag
LVAR_INT gang4_faflag
LVAR_INT gang5_faflag
LVAR_INT gang6_faflag
LVAR_INT gang7_faflag
LVAR_INT gang8_faflag
LVAR_INT gang9_faflag
LVAR_INT gang10_faflag
LVAR_INT gang11_faflag
LVAR_INT gang12_faflag
LVAR_INT gang13_faflag
LVAR_INT gang14_faflag
LVAR_INT gang15_faflag
LVAR_INT gang16_faflag
LVAR_INT gang17_faflag
LVAR_INT gang18_faflag
LVAR_INT gang19_faflag
LVAR_INT gang20_faflag
LVAR_INT swat2_faflag
LVAR_INT firstfloorcounter_fa
LVAR_INT slidedoor_faflag
LVAR_INT createguysfirst_faflag
LVAR_INT secondfloorcounter_fa
LVAR_INT druglabguys1_faflag
LVAR_INT druglabguys2_faflag
LVAR_INT rightroom_faflag
LVAR_INT leftroom_faflag
LVAR_INT bossbattle_faflag
LVAR_INT smokerun_faflag
LVAR_INT tellsmoke_faflag 
LVAR_INT switch_faflag 
LVAR_INT irgoggles_faflag
LVAR_INT progressaudio_faflag
LVAR_INT handlingudio_faflag
LVAR_INT bossbattleaudio_faflag
LVAR_INT lightsout_faflag
LVAR_INT ped1_faflag
LVAR_INT ped2_faflag
LVAR_INT ped3_faflag
LVAR_INT ped4_faflag
LVAR_INT finaleaplayerpass_flag

// **************************************** Mission Start ************************************

mission_start_finaleA:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME finaleA

WAIT 0

LOAD_MISSION_TEXT RIOT4


//intro cut scene
SET_AREA_VISIBLE 1

LOAD_CUTSCENE RIOT_4a
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 0




REQUEST_MODEL SWATVAN
REQUEST_MODEL COPCARLA
REQUEST_MODEL SWAT
REQUEST_MODEL BALLAS1

REQUEST_MODEL GREENWOO
LOAD_SPECIAL_CHARACTER 1 SWEET

REMOVE_IPL carter 
REQUEST_IPL	carter

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED GREENWOO
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED SWATVAN
	OR NOT HAS_MODEL_LOADED COPCARLA
	OR NOT HAS_MODEL_LOADED SWAT
	OR NOT HAS_MODEL_LOADED BALLAS1
	WAIT 0
ENDWHILE


SET_CHAR_COORDINATES scplayer 2514.745 -1673.838 12.8
SET_CHAR_HEADING scplayer 60.891

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY carter_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH cartertough_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK cartercoward_DM

SET_MAX_WANTED_LEVEL 0


SWITCH_EMERGENCY_SERVICES OFF

DELETE_OBJECT carterwall
CREATE_OBJECT_NO_OFFSET imy_shash_wall 2522.008 -1272.93 35.609 carterwall
SET_OBJECT_HEALTH carterwall 100
SET_OBJECT_COLLISION_DAMAGE_EFFECT carterwall FALSE
FREEZE_OBJECT_POSITION carterwall TRUE
SET_OBJECT_COLLISION_DAMAGE_EFFECT carterwall TRUE

//create car
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_
CREATE_CAR GREENWOO 2507.823 -1671.88 12.1678 sweet_car
SET_CAR_HEADING sweet_car 347.15
SET_CAR_ONLY_DAMAGED_BY_PLAYER sweet_car TRUE
ADD_BLIP_FOR_CAR sweet_car sweet_fablip
SET_BLIP_AS_FRIENDLY sweet_fablip TRUE
CHANGE_CAR_COLOUR sweet_car 59 34
SET_CAR_HEALTH sweet_car 2000
SET_CAN_BURST_CAR_TYRES sweet_car FALSE
SET_CAR_CAN_BE_VISIBLY_DAMAGED sweet_car FALSE

//create buddies
CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2511.169 -1672.747 12.493 sweet
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE
SET_CHAR_HEADING sweet 68.365
SET_CHAR_NEVER_TARGETTED sweet TRUE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE
TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 5000 0
SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
SET_CHAR_DECISION_MAKER sweet carter_DM
SET_CHAR_NEVER_TARGETTED sweet TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
CREATE_FX_SYSTEM wallbust 2521.86 -1272.66 35.04 TRUE wallsmashfx_fa

SET_TIME_OF_DAY 21 30
SET_MAX_FIRE_GENERATIONS 0
SWITCH_WIDESCREEN OFF
LOAD_SCENE 2511.75 -1672.15 14.0
DO_FADE 1000 FADE_IN
SET_PLAYER_CONTROL PLAYER1 ON
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER
PRINT_NOW RM4_1 7000 1 //~s~Get in the car with ~b~Sweet~s~.

SET_PLAYER_GROUP_RECRUITMENT PLAYER1 FALSE

// **************************************** Declare Variables ************************************

//initial cut scene
camposincX_fa = 2505.2148 - 2508.1704  
camposincY_fa = -1261.3512 - -1267.8781 
camposincZ_fa = 43.8341 - 48.0744 
camlookincX_fa = 2506.0029 - 2508.3508
camlookincY_fa = -1261.8015 - -1268.8573 
camlookincZ_fa = 43.4146 - 47.9824

camposX_fa = 2508.1704
camposY_fa = -1267.8781
camposZ_fa = 48.0744
camlookX_fa = 2508.3508
camlookY_fa	= -1268.8573
camlookZ_fa	= 47.9824

speed_fa = 160.0
camposincX_fa /= speed_fa 
camposincY_fa /= speed_fa
camposincZ_fa /= speed_fa
camlookincX_fa /= speed_fa
camlookincY_fa /= speed_fa
camlookincZ_fa /= speed_fa


//flags
ram_faflag = 0
carter_faflag = 0
lockcar_faflag = 0 
playerincar_faflag = 0
coordshoot_faflag = 0
skipcutscene_faflag = 0
swatvan_faflag = 0
swat1_faflag = 0
swat5_faflag = 0
floorg_faflag = 0
floor1_faflag = 0
floor2_faflag = 0
floor3_faflag = 0
gang1_faflag = 0
gang2_faflag = 0
gang3_faflag = 0
gang4_faflag = 0
gang5_faflag = 0
gang6_faflag = 0
gang7_faflag = 0
gang8_faflag = 0
gang9_faflag = 0
gang10_faflag = 0
gang11_faflag = 0
gang12_faflag = 0
gang13_faflag = 0
gang14_faflag = 0
gang15_faflag = 0
gang16_faflag = 0
gang17_faflag = 0
gang18_faflag = 0
gang19_faflag = 0
gang20_faflag = 0
swat2_faflag = 0
firstfloorcounter_fa = 0
slidedoor_faflag = 0
createguysfirst_faflag = 0
secondfloorcounter_fa = 0
druglabguys1_faflag = 0
druglabguys2_faflag = 0
rightroom_faflag = 0
leftroom_faflag = 0
bossbattle_faflag = 0
smokerun_faflag = 0
tellsmoke_faflag = 0 
switch_faflag = 0 
irgoggles_faflag = 0
progressaudio_faflag = 0
handlingudio_faflag = 0
bossbattleaudio_faflag = 0
lightsout_faflag = 0
ped1_faflag	= 0
ped2_faflag	= 0
ped3_faflag	= 0
ped4_faflag	= 0
finaleaplayerpass_flag = 0

// **********************************************************************************************





//omway
CREATE_SCRIPT_ROADBLOCK 2336.63 -1548.68 23.99 2347.01 -1548.37 23.68 TRUE
CREATE_SCRIPT_ROADBLOCK 2506.76 -1432.29 28.13 2516.63 -1430.51 27.82 TRUE
CREATE_SCRIPT_ROADBLOCK 2462.0 -1449.0 24.0 2463.0 -1439.0 24.0 TRUE

mission_finaleA_loop:

WAIT 0


//VIEW_INTEGER_VARIABLE carter_faflag carter_faflag 
//VIEW_INTEGER_VARIABLE ram_faflag ram_faflag
//VIEW_INTEGER_VARIABLE floor1_faflag floor1_faflag
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////		Getting into the Carter Block	////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_finalea_passed
ENDIF


IF carter_faflag = 0

	IF ram_faflag = 0
		IF NOT IS_CAR_DEAD sweet_car
			IF NOT IS_CHAR_DEAD sweet
				IF IS_CHAR_IN_CAR scplayer sweet_car
					REMOVE_BLIP sweet_fablip
					ADD_BLIP_FOR_COORD 2507.8201 -1309.2263 33.6726 sweet_fablip
					PRINT_NOW RM4_2 5000 1 //~s~Drive to ~y~Big Smoke's Crack Fortress~s~.
					TIMERB = 0
					ram_faflag = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//text
	IF ram_faflag = 1

		GOSUB process_audio_fa

		//play mission audio
		IF progressaudio_faflag = 0
			IF handlingudio_faflag = 0
				IF TIMERB > 5500
					audio_label_fa = SOUND_ROT4_AA	//We sure he’s in Eat Los Santos?
					$input_text_fa = ROT4_AA	//We sure he’s in Eat Los Santos?
					GOSUB load_audio_fa
				ENDIF
			ENDIF
		ENDIF
		IF progressaudio_faflag = 1
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_AB	//Yeah, right on the edge of Los Flores – some old apartments and a warehouse.
				$input_text_fa = ROT4_AB	//Yeah, right on the edge of Los Flores – some old apartments and a warehouse.
				GOSUB load_audio_fa
			ENDIF
		ENDIF
		IF progressaudio_faflag = 2
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_AC //He’s as good as dead!
				$input_text_fa = ROT4_AC //He’s as good as dead!
				GOSUB load_audio_fa
				TIMERB = 0
			ENDIF
		ENDIF
		IF progressaudio_faflag = 3
			IF handlingudio_faflag = 0
				IF TIMERB > 3000
					audio_label_fa = SOUND_ROT4_BA //I can’t believe you bought the same crap car!
					$input_text_fa = ROT4_BA //I can’t believe you bought the same crap car!
					GOSUB load_audio_fa
				ENDIF
			ENDIF
		ENDIF
		IF progressaudio_faflag = 4
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BB	//Hey, if it ain’t broke, don’t fix it.
				$input_text_fa = ROT4_BB	//Hey, if it ain’t broke, don’t fix it.
				GOSUB load_audio_fa
				TIMERA = 0
			ENDIF
		ENDIF
		IF progressaudio_faflag = 5
			IF handlingudio_faflag = 0
				IF TIMERB > 12000
					audio_label_fa = SOUND_ROT4_BC //So this is it, huh?
					$input_text_fa = ROT4_BC //So this is it, huh?
					GOSUB load_audio_fa
				ENDIF
			ENDIF
		ENDIF
		IF progressaudio_faflag = 6
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BE //Johnson boys sorting shit out.
				$input_text_fa = ROT4_BE //Johnson boys sorting shit out.
				GOSUB load_audio_fa
			ENDIF
		ENDIF
		IF progressaudio_faflag = 7
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BF //Yeah.
				$input_text_fa = ROT4_BF //Yeah.
				GOSUB load_audio_fa
			ENDIF
		ENDIF
		IF progressaudio_faflag = 8
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BG //Nervous?
				$input_text_fa = ROT4_BG //Nervous?
				GOSUB load_audio_fa
			ENDIF
		ENDIF
		IF progressaudio_faflag = 9
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BH //Yeah.
				$input_text_fa = ROT4_BH //Yeah.
				GOSUB load_audio_fa
			ENDIF
		ENDIF
		IF progressaudio_faflag = 10
			IF handlingudio_faflag = 0
				audio_label_fa = SOUND_ROT4_BJ //Me too, me too.
				$input_text_fa = ROT4_BJ //Me too, me too.
				GOSUB load_audio_fa
			ENDIF
		ENDIF


	ENDIF

	IF ram_faflag = 1
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CAR_DEAD sweet_car
				IF LOCATE_CAR_3D sweet_car 2507.8201 -1309.2263 33.85 4.0 4.0 4.0 TRUE
					IF IS_CHAR_SITTING_IN_CAR sweet sweet_car
						IF IS_CHAR_SITTING_IN_CAR scplayer sweet_car
							SET_PLAYER_CONTROL player1 OFF
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
							REMOVE_BLIP sweet_fablip
							CLEAR_ALL_SCRIPT_ROADBLOCKS
							//outside Carter Block in car with Sweet
							SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE
							SET_CAR_PROOFS sweet_car TRUE TRUE TRUE TRUE TRUE

							LOAD_MISSION_AUDIO 3 SOUND_SWAT_WALL_BREAK
							LOAD_MISSION_AUDIO 2 SOUND_ROT4_CA	//CJ, you lost your nerve or what?

							LOAD_ALL_MODELS_NOW

							SET_FADING_COLOUR 0 0 0
							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1 
							DO_FADE 500 FADE_OUT

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE

							WHILE NOT HAS_MISSION_AUDIO_LOADED 3
								OR NOT HAS_MISSION_AUDIO_LOADED 2
								WAIT 0
							ENDWHILE 

							IF NOT IS_CAR_DEAD sweet_car
								SET_CAR_COORDINATES sweet_car 2536.709 -1278.253 52.57
								SET_CAR_HEADING sweet_car 359.954
								LOCK_CAR_DOORS sweet_car CARLOCK_LOCKED
							ENDIF
							
							SET_CAR_DENSITY_MULTIPLIER 0.0
							SET_PED_DENSITY_MULTIPLIER 0.0

							CLEAR_AREA 2536.709 -1278.253 100.0 100.0 TRUE

							LOAD_CUTSCENE RIOT_4b
							 
							WHILE NOT HAS_CUTSCENE_LOADED
							            WAIT 0
							ENDWHILE
							 
							CLEAR_AREA 2536.709 -1278.253 100.0 100.0 TRUE

							START_CUTSCENE
							
							DO_FADE 1000 FADE_IN
							  
							WHILE NOT HAS_CUTSCENE_FINISHED
							            WAIT 0
							ENDWHILE

							CLEAR_CUTSCENE

							DO_FADE 0 FADE_OUT

							WHILE GET_FADING_STATUS
							            WAIT 0
							ENDWHILE

							//
							SET_PLAYER_CONTROL player1 OFF

							CLEAR_AREA 2372.0105 -1306.6090 300.0 300.0 FALSE

							IF NOT IS_CAR_DEAD sweet_car
								SET_CAR_COORDINATES sweet_car 2507.8201 -1309.2263 33.2726
								SET_CAR_HEADING sweet_car 1.7082
								LOCK_CAR_DOORS sweet_car CARLOCK_LOCKED
							ENDIF

							IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 
								WARP_CHAR_FROM_CAR_TO_COORD scplayer 2509.781 -1308.965 34.7125
							ELSE
								SET_CHAR_COORDINATES scplayer 2509.781 -1308.965 34.7125
							ENDIF

							SET_CHAR_HEADING scplayer 352.414
							CLEAR_AREA 2372.0105 -1306.6090 300.0 300.0 FALSE
							
							WAIT 2000

							SET_FIXED_CAMERA_POSITION camposX_fa camposY_fa camposZ_fa 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT camlookX_fa camlookY_fa camlookZ_fa JUMP_CUT
						
							REQUEST_MODEL BMX
							REQUEST_MODEL MP5LNG
							REQUEST_MODEL AK47
							REQUEST_MODEL BFYPRO
							REQUEST_ANIMATION CRACK

							SWITCH_ROADS_OFF 2356.4 -1233.16 30.6 2382.19 -1329.65 21.49
							SWITCH_PED_ROADS_OFF 2356.4 -1233.16 30.6 2382.19 -1329.65 21.49
							SWITCH_ROADS_OFF 2522.74 -1260.01 30.28 2499.0 -1322.01 31.76
							SWITCH_PED_ROADS_OFF 2522.74 -1260.01 30.28 2499.0 -1322.01 31.76
							SWITCH_ROADS_OFF 2431.9 -1268.06 30.6 2474.9 -1240.38 20.84
							SWITCH_PED_ROADS_OFF 2431.9 -1268.06 30.6 2474.9 -1240.38 20.84
							CLEAR_AREA 2356.4 -1233.16 300.0 300.0 TRUE

							LOAD_ALL_MODELS_NOW
							
							WHILE NOT HAS_MODEL_LOADED BMX
								OR NOT HAS_MODEL_LOADED MP5LNG
								OR NOT HAS_MODEL_LOADED AK47
								OR NOT HAS_ANIMATION_LOADED CRACK
								OR NOT HAS_MODEL_LOADED BFYPRO
								WAIT 0
							ENDWHILE

							CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL swatgroup_GMD
							CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL ganggroup_GMD
							CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL gang2group_GMD

							//main road block section
							CREATE_CAR COPCARLA 2373.8030 -1314.1724 22.8398 copcar1_fa
							SET_CAR_HEADING copcar1_fa 292.1921
							SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar1_fa TRUE
							SET_CAR_HEALTH copcar1_fa 500

							CREATE_CAR COPCARLA 2368.0403 -1315.7059 22.8459 copcar2_fa
							SET_CAR_HEADING copcar2_fa 96.2549
							SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar2_fa TRUE
							LOCK_CAR_DOORS copcar2_fa CARLOCK_UNLOCKED

							CREATE_CAR SWATVAN 2374.3599 -1323.7917 22.8389 swatvan_fa
							SET_CAR_HEADING swatvan_fa 3.7561
							SET_CAR_HEALTH swatvan_fa 3000
							SET_CAR_HEAVY swatvan_fa TRUE
							SET_CAR_PROOFS swatvan_fa TRUE TRUE FALSE FALSE FALSE
							SET_CAR_ONLY_DAMAGED_BY_PLAYER swatvan_fa TRUE
							LOCK_CAR_DOORS swatvan_fa CARLOCK_UNLOCKED
							SET_CAN_BURST_CAR_TYRES swatvan_fa FALSE

							CREATE_CAR GREENWOO 2365.6116 -1294.8286 22.8433 car1_fa
							SET_CAR_HEADING car1_fa 97.3729
							SET_CAR_HEALTH car1_fa 500
							SET_CAR_ONLY_DAMAGED_BY_PLAYER car1_fa TRUE

							CREATE_CAR GREENWOO 2372.4312 -1295.2722 22.8418 car2_fa
							SET_CAR_HEADING car2_fa 75.2267
							SET_CAR_ONLY_DAMAGED_BY_PLAYER car2_fa TRUE

							CREATE_CAR BMX 2507.2007 -1300.8408 33.6726 bmx_fa
							SET_CAR_HEADING bmx_fa 1.6501

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2366.3423 -1317.3771 22.8438 swat1_fa
							SET_CHAR_HEADING swat1_fa 3.9226
							SET_CHAR_DECISION_MAKER	swat1_fa cartertough_DM
							SET_CHAR_RELATIONSHIP swat1_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_RELATIONSHIP swat1_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat1_fa TRUE
							GIVE_WEAPON_TO_CHAR swat1_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE swat1_fa TRUE
							SET_GROUP_LEADER swatgroup_GMD swat1_fa

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2375.8479 -1315.3252 22.8368 swat2_fa
							SET_CHAR_HEADING swat2_fa 4.8178
							SET_CHAR_DECISION_MAKER	swat2_fa cartertough_DM
							SET_CHAR_RELATIONSHIP swat2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_RELATIONSHIP swat2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat2_fa TRUE
							GIVE_WEAPON_TO_CHAR swat2_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE swat2_fa TRUE
							SET_GROUP_MEMBER swatgroup_GMD swat2_fa

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2371.6758 -1316.8058 22.8429 swat3_fa
							SET_CHAR_HEADING swat3_fa 0.5529
							SET_CHAR_DECISION_MAKER	swat3_fa cartertough_DM
							SET_CHAR_RELATIONSHIP swat3_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_RELATIONSHIP swat3_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat3_fa TRUE
							GIVE_WEAPON_TO_CHAR swat3_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE swat3_fa TRUE
							SET_GROUP_MEMBER swatgroup_GMD swat3_fa

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 22370.3606 -1319.4922 22.8515 swat4_fa
							SET_CHAR_HEADING swat4_fa 177.5538
							SET_CHAR_DECISION_MAKER	swat4_fa cartertough_DM
							SET_CHAR_RELATIONSHIP swat4_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_RELATIONSHIP swat4_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat4_fa TRUE
							GIVE_WEAPON_TO_CHAR swat4_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE swat4_fa TRUE
							SET_GROUP_MEMBER swatgroup_GMD swat4_fa // in the previous script, this call asserts swat4_fa ped "out of the map" during AssertEntityPointerValid

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2376.4399 -1321.6250 22.9844 swat5_fa
							SET_CHAR_HEADING swat5_fa 177.5538
							GIVE_WEAPON_TO_CHAR swat5_fa WEAPONTYPE_MP5 99999
							SET_CHAR_DECISION_MAKER	swat5_fa cartertough_DM
							SET_CHAR_RELATIONSHIP swat5_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_RELATIONSHIP swat5_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat5_fa TRUE
							GIVE_WEAPON_TO_CHAR swat5_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE swat5_fa TRUE
							SET_GROUP_MEMBER swatgroup_GMD swat5_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2364.4321 -1294.0126 22.9882 gang1_fa
							SET_CHAR_HEADING gang1_fa 177.5538 
							SET_CHAR_DECISION_MAKER	gang1_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang1_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_RELATIONSHIP gang1_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang1_fa TRUE
							GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_AK47 99999
							TASK_STAY_IN_SAME_PLACE gang1_fa TRUE
							SET_GROUP_LEADER ganggroup_GMD gang1_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2369.5271 -1292.2279 22.8515 gang2_fa
							SET_CHAR_HEADING gang2_fa 177.5538
							SET_CHAR_DECISION_MAKER	gang2_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
							SET_CHAR_RELATIONSHIP gang2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang2_fa TRUE
							GIVE_WEAPON_TO_CHAR gang2_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE gang2_fa TRUE
							SET_GROUP_MEMBER ganggroup_GMD gang2_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2371.1680 -1293.3701 22.8436 gang3_fa
							SET_CHAR_HEADING gang3_fa 177.5538 
							SET_CHAR_DECISION_MAKER	gang3_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang3_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_RELATIONSHIP gang3_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang3_fa TRUE
							GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_AK47 99999
							TASK_STAY_IN_SAME_PLACE gang3_fa TRUE
							SET_GROUP_MEMBER ganggroup_GMD gang3_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2374.9871 -1294.0789 22.8380 gang4_fa
							SET_CHAR_HEADING gang4_fa 177.5538
							SET_CHAR_DECISION_MAKER	gang4_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang4_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
							SET_CHAR_RELATIONSHIP gang4_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang4_fa TRUE
							GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE gang4_fa TRUE
							SET_GROUP_MEMBER ganggroup_GMD gang4_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2521.5095 -1270.2585 33.9206 gang5_fa
							SET_CHAR_HEADING gang5_fa 100.2518
							SET_CHAR_DECISION_MAKER	gang5_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang5_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang5_fa TRUE
							GIVE_WEAPON_TO_CHAR gang5_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE gang5_fa TRUE
							SET_GROUP_LEADER gang2group_GMD gang5_fa

							CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2521.7202 -1274.9811 33.8748 gang6_fa
							SET_CHAR_HEADING gang6_fa 98.0309
							SET_CHAR_DECISION_MAKER	gang6_fa cartertough_DM
							SET_CHAR_RELATIONSHIP gang6_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang6_fa TRUE
							GIVE_WEAPON_TO_CHAR gang6_fa WEAPONTYPE_MP5 99999
							TASK_STAY_IN_SAME_PLACE gang6_fa TRUE
							SET_GROUP_MEMBER gang2group_GMD gang6_fa

   							ram_faflag = 2	

							SWITCH_WIDESCREEN ON
							
							DO_FADE 1000 FADE_IN

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
							
							PRINT_NOW RM4_4 5000 1 //~s~Security is tight in the Crack Fortress there is only one way in but the door is locked down because of the riots.
							PRINT RM4_14 5000 1//~s~The only way passed that reinforced entrance is to knock it down.
							TIMERA = 0

							//////////////////////////////////////////////////////////////////////////////////////
							//////////////////////////////////////////////////////////////////////////////////////
							skipcutscene_faflag = 0
							SKIP_CUTSCENE_START
							//////////////////////////////////////////////////////////////////////////////////////
							//////////////////////////////////////////////////////////////////////////////////////
							
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// cut scene
	IF ram_faflag = 2
		camposX_fa += camposincX_fa
		camposY_fa += camposincY_fa
		camposZ_fa += camposincZ_fa
		SET_FIXED_CAMERA_POSITION camposX_fa camposY_fa camposZ_fa 0.0 0.0 0.0

		camlookX_fa += camlookincX_fa
		camlookY_fa	+= camlookincY_fa
		camlookZ_fa	+= camlookincZ_fa
		POINT_CAMERA_AT_POINT camlookX_fa camlookY_fa camlookZ_fa JUMP_CUT
		IF camposX_fa <= 2505.2148
		OR TIMERA > 8999
			ram_faflag = 3
		ENDIF
	ENDIF

	IF ram_faflag = 3
		IF TIMERA > 8999
			ram_faflag = 4
		ENDIF
	ENDIF

	IF ram_faflag = 4
		SET_FIXED_CAMERA_POSITION 2370.9019 -1328.8146 23.0473 0.0 0.0 0.0 
		POINT_CAMERA_AT_POINT 2371.4600 -1328.0050 23.2292 JUMP_CUT
		TIMERA = 0
		CLEAR_PRINTS
		PRINT_NOW RM4_5 8000 1 //~s~You will need to find a way to knock it down, a heavy vehicle like the SWAT Tank would be able to smash it down.
		ram_faflag = 5
	ENDIF

	IF ram_faflag = 5
		IF TIMERA > 3999
			SET_FIXED_CAMERA_POSITION 2372.9490 -1326.9136 24.7902 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2372.8738 -1325.9204 24.7010 JUMP_CUT
			TIMERA = 0
			ram_faflag = 6
		ENDIF
	ENDIF

	IF ram_faflag = 6
		IF TIMERA > 3999

			//////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////
			skipcutscene_faflag = 1
			SKIP_CUTSCENE_END
			//////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////

			//PLAYER HAS SKIPPED CUTSCENE
			IF skipcutscene_faflag = 0

				DO_FADE 500 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				CLEAR_PRINTS
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
	
				SWITCH_WIDESCREEN OFF

				DO_FADE 500 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
		
			ENDIF

			IF NOT IS_CAR_DEAD swatvan_fa
				ADD_BLIP_FOR_CAR swatvan_fa swatvan_fablip
				SET_BLIP_AS_FRIENDLY swatvan_fablip TRUE
				PRINT_NOW RM4_6 7000 1//~s~Go get the ~b~SWAT Tank~s~.
				swatvan_faflag = 1
			ENDIF
			IF NOT IS_CAR_DEAD sweet_car
				FREEZE_CAR_POSITION sweet_car TRUE
				//SET_VEHICLE_IS_CONSIDERED_BY_PLAYER sweet_car FALSE
			ENDIF

			SET_CAR_DENSITY_MULTIPLIER 1.0
			SET_PED_DENSITY_MULTIPLIER 1.0

			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			CREATE_SCRIPT_ROADBLOCK 2456.468 -1262.2 23.87 2455.625 -1250.55 23.89 TRUE
			CREATE_SCRIPT_ROADBLOCK 2375.778 -1358.43 23.878 2365.675 -1358.34 24.036 TRUE
			SET_PED_DENSITY_MULTIPLIER 0.5
			ram_faflag = 7
		ENDIF
	ENDIF

	// Getting SWAT Tank
	IF ram_faflag = 7

		IF swat5_faflag = 0
			IF NOT IS_CHAR_DEAD swat5_fa
				IF HAS_CHAR_SPOTTED_CHAR swat5_fa scplayer
					enemy_fa = swat5_fa
					enemytarget_fa = scplayer
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat5_fa FALSE
					GOSUB stayshootnoduck_falabel
					swat5_faflag = 1
				ENDIF
			ENDIF
		ENDIF
		
		IF swat1_faflag = 0
			IF NOT IS_CAR_DEAD swatvan_fa
				IF IS_CHAR_IN_CAR scplayer swatvan_fa

					IF NOT IS_CHAR_DEAD swat1_fa
						enemy_fa = swat1_fa
						enemytarget_fa = scplayer
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat1_fa FALSE
						GOSUB stayshootnoduck_falabel
					ENDIF

					IF NOT IS_CHAR_DEAD swat2_fa
						enemy_fa = swat2_fa
						enemytarget_fa = scplayer
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat2_fa FALSE
						GOSUB stayshootnoduck_falabel
					ENDIF

					IF NOT IS_CHAR_DEAD swat3_fa
						enemy_fa = swat3_fa
						enemytarget_fa = scplayer
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat3_fa FALSE
						GOSUB stayshootnoduck_falabel
					ENDIF

					IF NOT IS_CHAR_DEAD swat4_fa
						enemy_fa = swat4_fa
						enemytarget_fa = scplayer
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat4_fa FALSE
						GOSUB stayshootnoduck_falabel
					ENDIF

					IF NOT IS_CHAR_DEAD swat5_fa
						enemy_fa = swat5_fa
						enemytarget_fa = scplayer
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat5_fa FALSE
						GOSUB stayshootnoduck_falabel
					ENDIF

					IF NOT IS_CHAR_DEAD gang1_fa
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang1_fa FALSE
					ENDIF
					IF NOT IS_CHAR_DEAD gang2_fa
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang2_fa FALSE
					ENDIF
					IF NOT IS_CHAR_DEAD gang3_fa
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang3_fa FALSE
					ENDIF
					IF NOT IS_CHAR_DEAD gang4_fa
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang4_fa FALSE
					ENDIF

					MARK_MODEL_AS_NO_LONGER_NEEDED BMX
					MARK_CAR_AS_NO_LONGER_NEEDED bmx_fa
					swat1_faflag = 1
				ENDIF
			ENDIF
		ENDIF

		IF swat1_faflag = 1
			IF NOT IS_CAR_DEAD swatvan_fa
				IF LOCATE_CHAR_IN_CAR_2D scplayer 2521.91 -1273.05 30.0 30.0 FALSE
					IF IS_CHAR_IN_CAR scplayer swatvan_Fa
						PRINT_NOW RM4_15 5000 1 //~s~Smash through the ~y~wall ~s~to gain entry!
						MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
						MARK_MODEL_AS_NO_LONGER_NEEDED SWAT
						MARK_CHAR_AS_NO_LONGER_NEEDED swat1_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED swat2_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED swat3_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED swat4_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED swat5_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
						MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
						MARK_CAR_AS_NO_LONGER_NEEDED copcar1_fa
						MARK_CAR_AS_NO_LONGER_NEEDED copcar2_fa
						MARK_CAR_AS_NO_LONGER_NEEDED car1_fa
						MARK_CAR_AS_NO_LONGER_NEEDED car2_fa
						swat1_faflag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF HAS_OBJECT_OF_TYPE_BEEN_SMASHED 2522.008 -1272.93 35.609 30.0 imy_shash_wall
			PLAY_AND_KILL_FX_SYSTEM wallsmashfx_fa
			PLAY_MISSION_AUDIO 3
			DELETE_CHAR swat1_fa
			DELETE_CHAR swat2_fa
			DELETE_CHAR swat3_fa
			DELETE_CHAR swat4_fa
			DELETE_CHAR swat5_fa
			DELETE_CHAR gang1_fa
			DELETE_CHAR gang2_fa
			DELETE_CHAR gang3_fa
			DELETE_CHAR gang4_fa
			DELETE_CAR copcar1_fa
			DELETE_CAR car1_fa
			DELETE_CAR car2_fa
			REMOVE_BLIP swatvan_fablip
			CLEAR_PRINTS
			carter_faflag = 1
		ENDIF

	ENDIF

	IF swatvan_faflag = 1
		IF NOT IS_CAR_DEAD swatvan_fa
			IF IS_CHAR_SITTING_IN_CAR scplayer swatvan_fa
				REMOVE_BLIP swatvan_fablip
				PRINT_NOW RM4_8 5000 1 //~s~Use the SWAT Tank to ram right through the entrance of ~y~Big Smoke's Crack Fortess~s~.
				ADD_BLIP_FOR_COORD 2522.79 -1272.85 34.19 carter_fablip
				swatvan_faflag = 2
			ENDIF
		ENDIF
	ENDIF


	IF swatvan_faflag = 2
		IF NOT IS_CAR_DEAD swatvan_fa
			IF NOT IS_CHAR_SITTING_IN_CAR scplayer swatvan_fa
				REMOVE_BLIP carter_fablip
				ADD_BLIP_FOR_CAR swatvan_fa swatvan_fablip
				SET_BLIP_AS_FRIENDLY swatvan_fablip TRUE
				PRINT_NOW RM4_9 5000 1 //You need to ram that wall right through, get back in the ~b~SWAT Tank~s~!				
				swatvan_faflag = 1
			ENDIF
		ENDIF
	ENDIF

	IF swatvan_faflag = 1
	OR swatvan_faflag = 2
		IF IS_CAR_DEAD swatvan_fa
			PRINT_NOW RM4_10 7000 1 //~r~The SWAT Tank has been destroyed how are you going to get into ~y~Big Smoke's Crack Fortess~r~ now?
			GOTO mission_finaleA_failed
		ENDIF
	ENDIF

	///////////////////////////////////////////////////////// sweet tells player to go and finish off Smoke
	IF lockcar_faflag = 0
		IF NOT IS_CAR_DEAD sweet_car
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2507.8201 -1309.2263 3.5 3.5 FALSE
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				//OR IS_CHAR_TOUCHING_VEHICLE scplayer sweet_car
					//Hey man find a way inside
					PLAY_MISSION_AUDIO 2 //ROT4_CA	CJ, you lost your nerve or what?
					PRINT_NOW ROT4_CA 2000 1 //CJ, you lost your nerve or what?
					lockcar_faflag = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF lockcar_faflag = 1
		IF HAS_MISSION_AUDIO_FINISHED 2
			IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2507.8201 -1309.2263 3.5 3.5 FALSE
			OR NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
				lockcar_faflag = 0
			ENDIF
		ENDIF
	ENDIF
	
	///////////////////////////////////////////////////////// Getting in and out of car stuff

	IF ram_faflag = 1
	OR ram_faflag = 100

		//tell player to get back in the car if they leave it
		IF NOT IS_CAR_DEAD sweet_car

			IF playerincar_faflag = 1
				IF NOT IS_CHAR_IN_CAR scplayer sweet_car
					REMOVE_BLIP sweet_fablip
					ADD_BLIP_FOR_CAR sweet_car sweet_fablip	
					SET_BLIP_AS_FRIENDLY sweet_fablip TRUE
					PRINT_NOW RM4_7 5000 1 //~s~Get back in the ~b~car~s~!
					ram_faflag = 100
					playerincar_faflag = 0
				ENDIF
			ENDIF

			IF playerincar_faflag = 0
				IF IS_CHAR_IN_CAR scplayer sweet_car
					REMOVE_BLIP sweet_fablip 
					ADD_BLIP_FOR_COORD 2507.8201 -1309.2263 33.6726 sweet_fablip
					PRINT_NOW RM4_2 5000 1 //~s~Drive to ~y~Big Smoke's Crack Fortress~s~.
					ram_faflag = 1
					playerincar_faflag = 1
				ENDIF
			ENDIF

		ENDIF
	ENDIF

	//sweet dies
//	IF ram_faflag = 0
//	OR ram_faflag = 1
//	OR ram_faflag = 100
	//any of the crew get wasted
	IF IS_CHAR_DEAD sweet
		PRINT_NOW RM4_11 5000 1 //~r~Sweet died!
		GOTO mission_finaleA_failed
	ENDIF

	//car gets wasted
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW RM4_12 5000 1 //~r~You wrecked the car!
		GOTO mission_finaleA_failed
	ENDIF

	////////////////////////////////////////////////////////////////////////////////////////

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////		Carter Wall has been smashed	////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF carter_faflag = 1
	
	IF floorg_faflag = 0
		REMOVE_BLIP carter_fablip
		REMOVE_GROUP swatgroup_GMD
		REMOVE_GROUP ganggroup_GMD

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2528.2524 -1273.7610 33.9609 gang1_fa
		SET_CHAR_HEADING gang1_fa 91.4211 
		SET_CHAR_DECISION_MAKER	gang1_fa cartertough_DM
		TASK_STAY_IN_SAME_PLACE gang1_fa TRUE
		GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_AK47 99999
		SET_CHAR_RELATIONSHIP gang1_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang1_fa 20
		SET_SENSE_RANGE gang1_fa 50.0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2530.7715 -1271.3906 33.9602 gang2_fa
		SET_CHAR_HEADING gang2_fa 91.5182 
		SET_CHAR_DECISION_MAKER	gang2_fa cartertough_DM
		GIVE_WEAPON_TO_CHAR gang2_fa WEAPONTYPE_AK47 99999
		TASK_STAY_IN_SAME_PLACE gang2_fa TRUE
		SET_CHAR_RELATIONSHIP gang2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang2_fa 20
		SET_SENSE_RANGE gang2_fa 50.0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2532.0796 -1272.2800 33.9602 gang3_fa //2541.4548 -1277.1276 33.9609 233.7332 
		SET_CHAR_HEADING gang3_fa 177.5538 
		SET_CHAR_DECISION_MAKER	gang3_fa cartertough_DM
		GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_AK47 99999
		TASK_STAY_IN_SAME_PLACE gang3_fa TRUE
		SET_CHAR_RELATIONSHIP gang3_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang3_fa 20
		SET_SENSE_RANGE gang3_fa 50.0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2539.9688 -1279.5626 33.9609 gang4_fa
		SET_CHAR_HEADING gang4_fa 25.3324
		SET_CHAR_DECISION_MAKER	gang4_fa cartertough_DM
		GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_MP5 99999
		TASK_STAY_IN_SAME_PLACE gang4_fa TRUE
		SET_CHAR_RELATIONSHIP gang4_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang4_fa 20
		SET_SENSE_RANGE gang4_fa 50.0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2533.2791 -1295.5552 33.9609 gang7_fa
		SET_CHAR_HEADING gang7_fa 327.5814
		SET_CHAR_DECISION_MAKER	gang7_fa cartertough_DM
		GIVE_WEAPON_TO_CHAR gang7_fa WEAPONTYPE_MP5 99999
		TASK_STAY_IN_SAME_PLACE gang7_fa TRUE
		SET_CHAR_RELATIONSHIP gang7_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang7_fa 20
		SET_SENSE_RANGE gang7_fa 50.0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2529.1777 -1304.4725 33.9609 gang8_fa
		SET_CHAR_HEADING gang8_fa 351.5292
		SET_CHAR_DECISION_MAKER	gang8_fa cartertough_DM
		GIVE_WEAPON_TO_CHAR gang8_fa WEAPONTYPE_MP5 99999
		TASK_STAY_IN_SAME_PLACE gang8_fa TRUE
		SET_CHAR_RELATIONSHIP gang8_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_HEALTH gang8_fa 20

		CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 2538.0 -1307.0 34.0 gang9_fa
		SET_CHAR_HEADING gang9_fa 35.0
		SET_CHAR_HEALTH gang9_fa 3
//		SET_CHAR_DECISION_MAKER	gang9_fa cartercoward_DM
		TASK_PLAY_ANIM gang9_fa CRCKIDLE4 CRACK 1000.0 TRUE FALSE FALSE TRUE -1

		CREATE_CHAR PEDTYPE_MISSION1 MALE01 2540.04 -1307.37 33.9609 gang10_fa
		SET_CHAR_HEADING gang10_fa 92.5436 
		SET_CHAR_HEALTH gang10_fa 3
//		SET_CHAR_DECISION_MAKER	gang10_fa cartercoward_DM
		TASK_PLAY_ANIM gang10_fa CRCKIDLE3 CRACK 1000.0 TRUE FALSE FALSE TRUE -1

		CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 2533.5525 -1308.2837 33.9609 gang11_fa
		SET_CHAR_HEADING gang11_fa 67.9508
		SET_CHAR_HEALTH gang11_fa 3
//		SET_CHAR_DECISION_MAKER	gang11_fa cartercoward_DM
		TASK_PLAY_ANIM gang11_fa CRCKIDLE2 CRACK 1000.0 TRUE FALSE FALSE TRUE -1

		CREATE_CHAR PEDTYPE_MISSION1 MALE01 2534.682 -1287.199 33.9609 gang12_fa
		SET_CHAR_HEADING gang12_fa 104.5
		SET_CHAR_HEALTH gang12_fa 3
//		SET_CHAR_DECISION_MAKER	gang12_fa cartercoward_DM
		TASK_PLAY_ANIM gang12_fa CRCKIDLE4 CRACK 1000.0 TRUE FALSE FALSE TRUE -1

		PRINT_NOW RM4_13 5000 1 //~s~There is an entrance into the ~y~upper floor towards the back of this area.
		ADD_BLIP_FOR_COORD 2542.1 -1303.99 34.03 carter_fablip
		//SET_BLIP_ENTRY_EXIT carter_fablip 2540.83 -1304.05 1.0

		floorg_faflag = 1
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW RM4_11 5000 1 //~r~Sweet died!
		GOTO mission_finaleA_failed
	ENDIF

	IF floorg_faflag = 1
		GET_AREA_VISIBLE carter_interior

			IF ped1_faflag	= 0
				IF NOT IS_CHAR_DEAD gang9_fa
					IF IS_CHAR_TOUCHING_char gang9_fa scplayer
						TASK_FLEE_CHAR gang9_fa scplayer 100.0 30000
						ped1_faflag	= 1
					ENDIF
				ENDIF
			ENDIF
			IF ped2_faflag	= 0
				IF NOT IS_CHAR_DEAD gang10_fa
					IF IS_CHAR_TOUCHING_char gang10_fa scplayer
						TASK_FLEE_CHAR gang10_fa scplayer 100.0 30000
						ped2_faflag	= 1
					ENDIF
				ENDIF
			ENDIF
			IF ped3_faflag	= 0
				IF NOT IS_CHAR_DEAD gang11_fa
					IF IS_CHAR_TOUCHING_char gang11_fa scplayer
						TASK_FLEE_CHAR gang11_fa scplayer 100.0 30000
						ped3_faflag	= 1
					ENDIF
				ENDIF
			ENDIF
			IF ped4_faflag	= 0
				IF NOT IS_CHAR_DEAD gang12_fa
					IF IS_CHAR_TOUCHING_char gang12_fa scplayer
						TASK_FLEE_CHAR gang12_fa scplayer 100.0 30000
						ped4_faflag	= 1
					ENDIF
				ENDIF
			ENDIF

			IF carter_interior = 2
				DO_FADE 1000 FADE_OUT
				
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				carter_faflag = 2
				floor1_faflag = 1

			ENDIF
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		1st Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF carter_faflag = 2

	IF floor1_faflag = 1
		SET_PLAYER_CONTROL PLAYER1 OFF
		SWITCH_WIDESCREEN ON
		DO_FADE 1000 FADE_OUT
		CLEAR_ALL_SCRIPT_ROADBLOCKS
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_PLAYER_MOOD PLAYER1 MOOD_PR 360000

		CLEAR_MISSION_AUDIO 2
		REMOVE_BLIP carter_fablip
		DELETE_CHAR gang1_fa
		DELETE_CHAR gang2_fa
		DELETE_CHAR gang3_fa
		DELETE_CHAR gang4_fa
		DELETE_CHAR gang7_fa
		DELETE_CHAR gang8_fa
		DELETE_CHAR gang9_fa
		DELETE_CHAR gang10_fa
		DELETE_CHAR gang11_fa
		DELETE_CHAR gang12_fa
		DELETE_CAR swatvan_fa
		DELETE_CHAR sweet

		REMOVE_ANIMATION CRACK
		UNLOAD_SPECIAL_CHARACTER 1
		MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

		SET_CHAR_COORDINATES scplayer 2543.2600 -1303.8445 1024.0742
		SET_CHAR_HEADING scplayer 175.5166 

		SET_FIXED_CAMERA_POSITION 2544.6160 -1302.8993 1024.4072 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2544.1931 -1303.7017 1024.8282 JUMP_CUT

		SET_CHAR_AREA_VISIBLE scplayer 2 //2543.199 -1304.003 20.0
		ADD_BLIP_FOR_COORD 2579.85 -1305.76 1037.07 carter_fablip
		SET_BLIP_ENTRY_EXIT carter_fablip 2540.83 -1304.05 1.0
		CLEAR_AREA 2578.0 -1306.0 200.0 200.0 TRUE
		
		DO_FADE 1000 FADE_IN
		PRINT_BIG RM4_17 1000 2 //Floor 1: Security Area
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
 

		//pickups
		CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2527.7830 -1288.8158 1031.4297 armour1_fa
		CREATE_PICKUP HEALTH PICKUP_ONCE 2575.0945 -1286.0415 1037.7812 health1_fa	
		CREATE_PICKUP_WITH_AMMO shotgspa PICKUP_ONCE 20 2568.5022 -1282.3447 1037.7812 shotgunpickup2_fa
		
		REQUEST_MODEL BALLAS2
		REQUEST_MODEL LSV1
		REQUEST_MODEL SWAT
		REQUEST_MODEL SWATVAN
		REQUEST_ANIMATION SWAT
		REQUEST_MODEL imcompmovedr1_las
		REQUEST_CAR_RECORDING 287

		LOAD_ALL_MODELS_NOW
		LOAD_SCENE 2541.81 -1311.32 1028.22
		//top of the stairs @ 2543.38 -1310.38 1028.26 5.0 -> 2527.5789 -1318.7618 1030.4297 91.9142 
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2543.2439 -1318.5378 1030.4274 gang15_fa
		SET_CHAR_HEADING gang15_fa 3.2815 
		SET_CHAR_DECISION_MAKER	gang15_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang15_fa WEAPONTYPE_MP5 99999
		TASK_PLAY_ANIM gang15_fa ROADCROSS PED 4.0 FALSE FALSE FALSE TRUE -1
		SET_CHAR_AREA_VISIBLE gang15_fa 2
		SET_CHAR_HEALTH gang15_fa 200
		SET_CHAR_MAX_HEALTH gang15_fa 200
		gang15_faflag = 0

		//roll left 2d @ 2540.95 -1314.42 1030.79/ 2529.24 -1323.21 1031.04
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2536.0710 -1321.4709 1030.4297 gang16_fa
		SET_CHAR_HEADING gang16_fa 263.8364
		SET_CHAR_DECISION_MAKER	gang16_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang16_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang16_fa 2
		SET_CHAR_HEALTH gang16_fa 200
		SET_CHAR_MAX_HEALTH gang16_fa 200
		gang16_faflag = 0

		//roll left 2d 2d @ 2532.14 -1315.59 1030.8 / 2518.25 -1307.07 1041.71
		CREATE_CHAR PEDTYPE_MISSION1 LSV1 2524.7366 -1298.3278 1030.4274 gang17_fa
		SET_CHAR_HEADING gang17_fa 175.4097 
		SET_CHAR_DECISION_MAKER	gang17_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang17_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang17_fa 2
		SET_CHAR_ACCURACY gang17_fa 80
		SET_CHAR_HEALTH gang17_fa 200
		SET_CHAR_MAX_HEALTH gang17_fa 200
		gang17_faflag = 0
		
		//security stay still no duck @ 2532.14 -1315.59 1030.8 / 2518.25 -1307.07 1041.71
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2527.8145 -1289.8164 1030.4274 gang18_fa
		SET_CHAR_HEADING gang18_fa 178.5987
		SET_CHAR_DECISION_MAKER	gang18_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang18_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang18_fa 2
		SET_CHAR_ACCURACY gang17_fa 80
		SET_CHAR_SHOOT_RATE gang17_fa 70
		SET_CHAR_HEALTH gang18_fa 200
		SET_CHAR_MAX_HEALTH gang18_fa 200
		gang18_faflag = 0

		WAIT 3000

		//->2560.0554 -1304.9164 1030.4297 275.5342 duck and shoot
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2531.0645 -1304.3324 1030.4297 gang1_fa
		SET_CHAR_HEADING gang1_fa 264.7670
		SET_CHAR_DECISION_MAKER	gang1_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang1_fa 2
		SET_CHAR_ACCURACY gang1_fa 80
		SET_CHAR_HEALTH gang1_fa 200
		SET_CHAR_MAX_HEALTH gang1_fa 200
		gang1_faflag = 0

		//two at the top room looking through window.
		//right, stay in same place no duck
		CREATE_CHAR PEDTYPE_MISSION1 LSV1 2566.6731 -1297.7087 1036.7805 gang3_fa
		SET_CHAR_HEADING gang3_fa 85.3469
		SET_CHAR_DECISION_MAKER	gang3_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang3_fa 2
		SET_CHAR_ACCURACY gang3_fa 80
		SET_CHAR_SHOOT_RATE gang3_fa 60
		gang3_faflag = 0

		//left stay in same place no duck
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2566.7266 -1287.2239 1036.7812 gang4_fa
		SET_CHAR_HEADING gang4_fa 96.3687
		SET_CHAR_DECISION_MAKER	gang4_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang4_fa 2
		SET_CHAR_HEALTH gang4_fa 200
		SET_CHAR_MAX_HEALTH gang4_fa 200
		SET_CHAR_ACCURACY gang4_fa 80
		gang4_faflag = 0

		//first left pillar shooting inside
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2540.859 -1287.161 1030.419 gang5_fa
		SET_CHAR_HEADING gang5_fa 90.0
		SET_CHAR_DECISION_MAKER	gang5_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang5_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang5_fa 2
		SET_CHAR_HEALTH gang5_fa 200
		SET_CHAR_MAX_HEALTH gang5_fa 200
		SET_CHAR_ACCURACY gang5_fa 80
		SET_CHAR_SHOOT_RATE gang5_fa 60
		gang5_faflag = 0

		//third left pillar shooting inside
		CREATE_CHAR PEDTYPE_MISSION1 LSV1 2556.854 -1287.161 1030.419 gang6_fa
		SET_CHAR_HEADING gang6_fa 90.0
		SET_CHAR_DECISION_MAKER	gang6_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang6_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang6_fa 2
		SET_CHAR_HEALTH gang6_fa 200
		SET_CHAR_MAX_HEALTH gang6_fa 200
		gang6_faflag = 0

		//duck and shoot stay in same place behind boxes in on right 
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2556.854 -1300.628 1030.419 gang7_fa
		SET_CHAR_HEADING gang7_fa 90.0
		SET_CHAR_DECISION_MAKER	gang7_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang7_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang7_fa 2
		SET_CHAR_HEALTH gang7_fa 200
		SET_CHAR_MAX_HEALTH gang7_fa 200
		gang7_faflag = 0

		//Duck and shoot stay in same place behind boxes in on right 
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2539.8049 -1298.6069 1030.4297 gang8_fa
		SET_CHAR_HEADING gang8_fa 64.7154 
		SET_CHAR_DECISION_MAKER	gang8_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang8_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang8_fa 2
		SET_CHAR_HEALTH gang8_fa 200
		SET_CHAR_SHOOT_RATE gang8_fa 70
		SET_CHAR_MAX_HEALTH gang8_fa 200
		gang8_faflag = 0

		//third right pillar just infront shoot normally
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2552.7976 -1300.9926 1030.4297 gang10_fa
		SET_CHAR_HEADING gang10_fa 87.5663 
		SET_CHAR_DECISION_MAKER	gang10_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang10_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang10_fa 2
		SET_CHAR_HEALTH gang10_fa 200
		SET_CHAR_MAX_HEALTH gang10_fa 200
		SET_CHAR_ACCURACY gang10_fa 80
		SET_CHAR_SHOOT_RATE gang10_fa 60
		gang10_faflag = 0

		//back centre just infront of shutters shoot normally
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2563.2339 -1294.0665 1030.4297 gang11_fa
		SET_CHAR_HEADING gang11_fa 93.2190
		SET_CHAR_DECISION_MAKER	gang11_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang11_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang11_fa 2
		SET_CHAR_HEALTH gang11_fa 200
		SET_CHAR_MAX_HEALTH gang11_fa 200
		gang11_faflag = 0
				
		//middle centre shoot normally
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2550.9922 -1290.2742 1030.4297 gang12_fa
		SET_CHAR_HEADING gang12_fa 103.6499
		SET_CHAR_DECISION_MAKER	gang12_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang12_fa WEAPONTYPE_AK47 99999
		SET_CHAR_AREA_VISIBLE gang12_fa 2
		SET_CHAR_HEALTH gang12_fa 200
		SET_CHAR_SHOOT_RATE gang12_fa 60
		SET_CHAR_MAX_HEALTH gang12_fa 200
		gang12_faflag = 0

		//left railings stay in same place 
		CREATE_CHAR PEDTYPE_MISSION1 LSV1 2556.1907 -1281.4457 1036.7812 gang13_fa
		SET_CHAR_HEADING gang13_fa 183.4208
		SET_CHAR_DECISION_MAKER	gang13_fa carter_DM
		GIVE_WEAPON_TO_CHAR gang13_fa WEAPONTYPE_MP5 99999
		SET_CHAR_AREA_VISIBLE gang13_fa 2
		gang13_faflag = 0

		CREATE_CAR SWATVAN 2561.818 -1275.167 1031.0189 swatvan_fa
		FREEZE_CAR_POSITION swatvan_fa TRUE
		SET_CAR_HEADING swatvan_fa 178.9
		SET_VEHICLE_AREA_VISIBLE swatvan_fa 2
		CREATE_CHAR_INSIDE_CAR swatvan_fa PEDTYPE_MISSION2 SWAT swat1_fa
		SET_CHAR_DECISION_MAKER	swat1_fa carter_DM
		GIVE_WEAPON_TO_CHAR swat1_fa WEAPONTYPE_MP5 9999
		SET_CHAR_AREA_VISIBLE swat1_fa 2
		SET_CHAR_SHOOT_RATE swat1_fa 80
		SET_CHAR_HEALTH swat1_fa 200
		SET_CHAR_MAX_HEALTH swat1_fa 200
		SET_CHAR_ACCURACY swat1_fa 80
		CREATE_CHAR_AS_PASSENGER swatvan_fa PEDTYPE_MISSION2 SWAT 0 swat2_fa
		SET_CHAR_DECISION_MAKER	swat2_fa carter_DM
		SET_CHAR_HEALTH swat2_fa 200
		SET_CHAR_MAX_HEALTH swat2_fa 200
		GIVE_WEAPON_TO_CHAR swat2_fa WEAPONTYPE_MP5 9999
		SET_CHAR_RELATIONSHIP swat2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
		SET_CHAR_RELATIONSHIP swat2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		SET_CHAR_AREA_VISIBLE swat2_fa 2
		swat1_faflag = 0
		swat2_faflag = 0
		swatvan_faflag = 0

		CREATE_OBJECT_NO_OFFSET imcompmovedr1_las 2565.814 -1303.566 1033.173 door_fa
		SET_OBJECT_HEADING door_fa 90.0

		CREATE_OBJECT_NO_OFFSET CJ_WIN_POP2 2561.811 -1279.639 1032.128 wall_fa
		FREEZE_OBJECT_POSITION wall_fa TRUE
		CREATE_FX_SYSTEM wallbust 2562.0 -1282.0 1031.0 TRUE wallsmashfx_fa

		DISABLE_ALL_ENTRY_EXITS TRUE

		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		SET_PLAYER_CONTROL PLAYER1 ON
		SWITCH_WIDESCREEN OFF
		CLEAR_PRINTS
		PRINT_NOW RM4_16 7000 1 //~s~Smoke is on the Fourth floor in his ~y~Penthouse Suite~s~.  You will have to work your way through three floors to get there.
		floor1_faflag = 2


	ENDIF

	//first guy running away
	IF floor1_faflag = 2
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2543.38 -1310.38 1028.26 5.0 5.0 5.0 FALSE
		OR IS_CHAR_SHOOTING scplayer

			IF gang15_faflag = 0
				IF NOT IS_CHAR_DEAD gang15_fa
					enemy_fa = gang15_fa
					enemyx_fa = 2527.5789
					enemyy_fa = -1318.7618
					enemyz_fa = 1030.4297
					GOSUB runnostay_falabel
					gang15_faflag = 1
				ENDIF
			ENDIF

			IF gang18_faflag = 0
				IF NOT IS_CHAR_DEAD gang18_fa
					enemy_fa = gang18_fa
					GOSUB stayshootnoduck_falabel
					gang18_faflag = 1
				ENDIF
			ENDIF
												
			floor1_faflag = 3
		ENDIF
	ENDIF

	IF floor1_faflag = 3
		
		IF IS_CHAR_IN_AREA_3D scplayer 2540.95 -1314.42 1028.79 2529.24 -1333.21 1035.04 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2534.44 -1318.92 1031.17 5.5 5.5 4.0 FALSE

			IF gang16_faflag = 0
				IF NOT IS_CHAR_DEAD gang16_fa
					enemy_fa = gang16_fa
					GOSUB rolloutl_falabel
					gang16_faflag = 1
				ENDIF
			ENDIF
			floor1_faflag = 4

		ENDIF

		IF gang16_faflag = 0
			IF NOT IS_CHAR_DEAD gang16_fa
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang16_fa scplayer
					enemy_fa = gang16_fa
					GOSUB rolloutl_falabel
					gang16_faflag = 1
					floor1_faflag = 4
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF floor1_faflag = 4
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2527.18 -1304.24 1027.0 6.0 6.0 5.0 FALSE
		OR IS_CHAR_DEAD gang18_fa 

			IF gang17_faflag = 0
				IF NOT IS_CHAR_DEAD gang17_fa
					enemy_fa = gang17_fa
					GOSUB rolloutl_falabel
					gang17_faflag = 1
				ENDIF
			ENDIF
			floor1_faflag = 5
		ENDIF
	ENDIF

	IF floor1_faflag = 5
		IF IS_CHAR_IN_AREA_3D scplayer 2529.33 -1308.3 1027.55 2537.78 -1272.14 1035.76 FALSE
		OR IS_CHAR_SHOOTING_IN_AREA scplayer 2522.87 -1299.61 2529.85 -1287.66 FALSE


			IF gang5_faflag = 0
				IF NOT IS_CHAR_DEAD gang5_fa
					enemy_fa = gang5_fa
					enemyx_fa = 2540.859
					enemyy_fa = -1287.161
					enemyz_fa = 1030.419
					shootimer_s4 = 1500
					heading_fa = 90.0
					GOSUB peekright_falabel
					gang5_faflag = 1
				ENDIF
			ENDIF

			IF gang6_faflag = 0
				IF NOT IS_CHAR_DEAD gang6_fa
					enemy_fa = gang6_fa
					enemyx_fa = 2556.854
					enemyy_fa = -1287.161
					enemyz_fa = 1030.419
					shootimer_s4 = 2000
					heading_fa = 90.0
					GOSUB peekright_falabel
					gang6_faflag = 1
				ENDIF
			ENDIF

			IF gang7_faflag = 0
				IF NOT IS_CHAR_DEAD gang7_fa
					enemy_fa = gang7_fa
					enemyx_fa = 2556.854
					enemyy_fa = -1300.628
					enemyz_fa = 1030.419
					shootimer_s4 = 1200
					heading_fa = 90.0
					GOSUB peekleft_falabel
					gang7_faflag = 1
				ENDIF
			ENDIF

			IF gang3_faflag = 0
				IF NOT IS_CHAR_DEAD gang3_fa
					enemy_fa = gang3_fa
					GOSUB stayshootnoduck_falabel
					gang3_faflag = 1
				ENDIF
			ENDIF

			IF gang4_faflag = 0
				IF NOT IS_CHAR_DEAD gang4_fa
					enemy_fa = gang4_fa
					GOSUB stayshootnoduck_falabel
					gang4_faflag = 1
				ENDIF
			ENDIF
		
			IF gang13_faflag = 0
				IF NOT IS_CHAR_DEAD gang13_fa
					enemy_fa = gang13_fa
					GOSUB stayshootnoduck_falabel
					gang13_faflag = 1
				ENDIF
			ENDIF
		 
			//Duck and shoot stay in same place behind boxes in on right 
			IF gang8_faflag = 0
				IF NOT IS_CHAR_DEAD gang8_fa
					enemy_fa = gang8_fa
					GOSUB stayshoot_falabel
					gang8_faflag = 1
				ENDIF
			ENDIF

			//third right pillar just infront shoot normally
			IF gang10_faflag = 0
				IF NOT IS_CHAR_DEAD gang10_fa
					TASK_KILL_CHAR_ON_FOOT gang10_fa scplayer
					gang10_faflag = 1
				ENDIF
			ENDIF

			//back centre just infront of shutters shoot normally
			IF gang11_faflag = 0
				IF NOT IS_CHAR_DEAD gang11_fa
					TASK_KILL_CHAR_ON_FOOT gang11_fa scplayer
					gang11_faflag = 1
				ENDIF
			ENDIF
					
			//middle centre shoot normally
			IF gang12_faflag = 0
				IF NOT IS_CHAR_DEAD gang12_fa
					TASK_KILL_CHAR_ON_FOOT gang12_fa scplayer
					gang12_faflag = 1
				ENDIF
			ENDIF

			floor1_faflag = 6
		ENDIF
	ENDIF

	IF floor1_faflag = 6
		IF IS_CHAR_IN_AREA_3D scplayer 2529.88 -1307.89 1027.0 2538.32 -1277.63 1032.54 FALSE
			MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fa 
			MARK_CHAR_AS_NO_LONGER_NEEDED gang16_fa 
			MARK_CHAR_AS_NO_LONGER_NEEDED gang17_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang18_fa
			//2560.0554 -1304.9164 1030.4297 duck and shoot
			IF gang1_faflag = 0
				IF NOT IS_CHAR_DEAD gang1_fa
					enemy_fa = gang1_fa
					enemyx_fa = 2560.0554
					enemyy_fa = -1304.9164
					enemyz_fa = 1030.4297
					GOSUB runnostay_falabel
					gang1_faflag = 1
				ENDIF
			ENDIF

			floor1_faflag = 7
		ENDIF
	ENDIF

	//player is near door
	IF floor1_faflag = 7
		IF IS_CHAR_IN_AREA_3D scplayer 2546.43 -1277.49 1027.0 2568.06 -1310.79 1032.54 FALSE
			floor1_faflag = 8
		ENDIF
	ENDIF
	
	//start the swat truck coming out
	IF floor1_faflag = 8
	OR firstfloorcounter_fa > 6
		IF swatvan_faflag = 0
			IF NOT IS_CAR_DEAD swatvan_fa
				FREEZE_CAR_POSITION swatvan_fa FALSE
				START_PLAYBACK_RECORDED_CAR swatvan_fa 287
				TIMERB = 0
				swatvan_faflag = 1
			ENDIF
		ENDIF
	ENDIF

	//smash wall
	IF swatvan_faflag = 1
		IF IS_CLOSEST_OBJECT_OF_TYPE_SMASHED_OR_DAMAGED 2561.811 -1279.639 1032.128 10.0 CJ_WIN_POP2 TRUE TRUE
		OR TIMERB > 250
			PLAY_AND_KILL_FX_SYSTEM wallsmashfx_fa
			SET_MISSION_AUDIO_POSITION 3 2557.88 -1288.65 1031.91
			PLAY_MISSION_AUDIO 3
			BREAK_OBJECT wall_fa TRUE
			swatvan_faflag = 2
		ENDIF
	ENDIF

	IF swatvan_faflag = 2
		IF NOT IS_CAR_DEAD swatvan_fa
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	swatvan_fa
				IF NOT IS_CHAR_DEAD swat1_fa
					TASK_KILL_CHAR_ON_FOOT swat1_fa scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD swat2_fa
					TASK_KILL_CHAR_ON_FOOT swat2_fa scplayer
				ENDIF
				swatvan_faflag = 3
			ENDIF
		ENDIF
	ENDIF

	IF gang5_faflag = 1
		IF NOT IS_CHAR_DEAD gang5_fa
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang5_fa scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang5_fa scplayer 4.0 4.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang5_fa
					TASK_KILL_CHAR_ON_FOOT gang5_fa scplayer
					gang5_faflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
					gang5_faflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF gang6_faflag = 1
		IF NOT IS_CHAR_DEAD gang6_fa
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang6_fa scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang6_fa scplayer 4.0 4.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang6_fa
					TASK_KILL_CHAR_ON_FOOT gang6_fa scplayer
					gang6_faflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fa
					gang6_faflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF gang7_faflag = 1
		IF NOT IS_CHAR_DEAD gang7_fa
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang7_fa scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang7_fa scplayer 5.0 5.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang7_fa
					TASK_KILL_CHAR_ON_FOOT gang7_fa scplayer
					gang7_faflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fa
					gang7_faflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//mark as no longer needed
	IF gang1_faflag = 1
		IF IS_CHAR_DEAD gang1_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fa
			firstfloorcounter_fa++
			gang1_faflag = 2
		ENDIF
	ENDIF
	IF gang3_faflag = 1
		IF IS_CHAR_DEAD gang3_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
			firstfloorcounter_fa++
			gang3_faflag = 2
		ENDIF
	ENDIF
	IF gang4_faflag = 1
		IF IS_CHAR_DEAD gang4_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
			firstfloorcounter_fa++
			gang4_faflag = 2
		ENDIF
	ENDIF
	IF gang5_faflag = 1
	OR gang5_faflag = 2
		IF IS_CHAR_DEAD gang5_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
			firstfloorcounter_fa++
			gang5_faflag = 3
		ENDIF
	ENDIF
	IF gang6_faflag = 1
	OR gang6_faflag = 2
		IF IS_CHAR_DEAD gang6_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fa
			firstfloorcounter_fa++
			gang6_faflag = 3
		ENDIF
	ENDIF
	IF gang7_faflag = 1
	OR gang7_faflag = 2
		IF IS_CHAR_DEAD gang7_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fa
			firstfloorcounter_fa++
			gang7_faflag = 3
		ENDIF
	ENDIF
	IF gang8_faflag = 1
		IF IS_CHAR_DEAD gang8_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fa
			firstfloorcounter_fa++
			gang8_faflag = 2
		ENDIF
	ENDIF
	IF gang10_faflag = 1
		IF IS_CHAR_DEAD gang10_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fa
			firstfloorcounter_fa++
			gang10_faflag = 2
		ENDIF
	ENDIF
	IF gang11_faflag = 1
		IF IS_CHAR_DEAD gang11_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fa
			firstfloorcounter_fa++
			gang11_faflag = 2
		ENDIF
	ENDIF
	IF gang12_faflag = 1
		IF IS_CHAR_DEAD gang12_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fa
			firstfloorcounter_fa++
			gang12_faflag = 2
		ENDIF
	ENDIF
	IF gang13_faflag = 1
		IF IS_CHAR_DEAD gang13_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fa
			firstfloorcounter_fa++
			gang13_faflag = 2
		ENDIF
	ENDIF
	IF swat1_faflag = 0
		IF IS_CHAR_DEAD swat1_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED swat1_fa
			firstfloorcounter_fa++
			swat1_faflag = 1
		ENDIF
	ENDIF
	IF swat2_faflag = 0
		IF IS_CHAR_DEAD swat2_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED swat2_fa
			firstfloorcounter_fa++
			swat2_faflag = 1
		ENDIF
	ENDIF

	//checking if guys are dead, once atleast 12 are taken out open the door providing the player has been in that area.
	IF firstfloorcounter_fa > 8
		IF floor1_faflag = 8
			IF slidedoor_faflag = 0
				IF DOES_OBJECT_EXIST door_fa

					IF createguysfirst_faflag = 0
						//runs through door
						//-> 2564.1860 -1301.6758 1030.4297 86.0738 
						CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2571.6353 -1302.1888 1030.4274 gang15_fa
						SET_CHAR_HEADING gang15_fa 84.9134 
						SET_CHAR_DECISION_MAKER	gang15_fa cartertough_DM
						GIVE_WEAPON_TO_CHAR gang15_fa WEAPONTYPE_MP5 99999
						TASK_PLAY_ANIM gang15_fa ROADCROSS PED 4.0 FALSE FALSE FALSE TRUE -1
						SET_CHAR_AREA_VISIBLE gang15_fa 2
						SET_CHAR_ACCURACY gang15_fa 80
						SET_CHAR_HEALTH gang15_fa 150
						SET_CHAR_MAX_HEALTH gang15_fa 150
						gang15_faflag = 0

						//duck and shoot
						CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2570.3979 -1299.2329 1030.4274 gang16_fa
						SET_CHAR_HEADING gang16_fa 119.2958
						SET_CHAR_DECISION_MAKER	gang16_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang16_fa WEAPONTYPE_MP5 99999
						SET_CHAR_AREA_VISIBLE gang16_fa 2
						SET_CHAR_HEALTH gang16_fa 200
						SET_CHAR_MAX_HEALTH gang16_fa 200
						gang16_faflag = 0

						//duck and shoot
						CREATE_CHAR PEDTYPE_MISSION1 LSV1 2566.8992 -1290.1445 1030.4297 gang17_fa
						SET_CHAR_HEADING gang17_fa 180.0082 
						SET_CHAR_DECISION_MAKER	gang17_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang17_fa WEAPONTYPE_MP5 99999
						SET_CHAR_AREA_VISIBLE gang17_fa 2
						SET_CHAR_HEALTH gang17_fa 200
						SET_CHAR_MAX_HEALTH gang17_fa 200
						gang17_faflag = 0
						
						//duck and shoot
						CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2570.6372 -1286.8409 1030.4297 gang18_fa
						SET_CHAR_HEADING gang18_fa 182.7895 
						SET_CHAR_DECISION_MAKER	gang18_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang18_fa WEAPONTYPE_MP5 99999
						SET_CHAR_AREA_VISIBLE gang18_fa 2
						SET_CHAR_HEALTH gang18_fa 200
						SET_CHAR_MAX_HEALTH gang18_fa 200
						gang18_faflag = 0

						//-> 2575.5647 -1284.9652 1030.4274 264.3392 -> 2575.5127 -1303.2421 1036.7812 179.2655 
						CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 2569.7278 -1284.5280 1030.4297 gang14_fa
						SET_CHAR_HEADING gang14_fa 168.4302
						SET_CHAR_DECISION_MAKER	gang14_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang14_fa WEAPONTYPE_MP5 99999
						SET_CHAR_AREA_VISIBLE gang14_fa 2
						SET_CHAR_HEALTH gang14_fa 200
						SET_CHAR_MAX_HEALTH gang14_fa 200
						gang14_faflag = 0
						createguysfirst_faflag = 1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT door_fa SOUND_SHUTTER_DOOR_START
					ENDIF

					IF SLIDE_OBJECT door_fa 2565.814 -1303.566 1035.362 0.0 0.0 0.15 FALSE
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT door_fa SOUND_SHUTTER_DOOR_STOP
						floor1_faflag = 9

						IF gang15_faflag = 0
							IF NOT IS_CHAR_DEAD gang15_fa
								enemy_fa = gang15_fa
								enemyx_fa = 2564.1860 
								enemyy_fa = -1301.6758 
								enemyz_fa = 1030.4297
								GOSUB runnostay_falabel
								gang15_faflag = 1
							ENDIF
						ENDIF
						IF gang16_faflag = 0
							IF NOT IS_CHAR_DEAD gang16_fa
								enemy_fa = gang16_fa
								GOSUB stayshoot_falabel
								gang16_faflag = 1
							ENDIF
						ENDIF
						IF gang14_faflag = 0
							IF NOT IS_CHAR_DEAD gang14_fa
								enemy_fa = gang14_fa
								GOSUB stayshoot_falabel
								gang14_faflag = 1
							ENDIF
						ENDIF
						IF gang17_faflag = 0
							IF NOT IS_CHAR_DEAD gang17_fa
								enemy_fa = gang17_fa
								GOSUB stayshoot_falabel
								gang17_faflag = 1
							ENDIF
						ENDIF
						IF gang18_faflag = 0
							IF NOT IS_CHAR_DEAD gang18_fa
								enemy_fa = gang18_fa
								GOSUB stayshoot_falabel
								gang18_faflag = 1
							ENDIF
						ENDIF
						slidedoor_faflag = 1
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF floor1_faflag = 9
		IF IS_CHAR_IN_AREA_3D scplayer 2566.14 -1307.43 1027.0 2580.06 -1279.36 1034.16 FALSE	

			IF gang14_faflag = 1
				IF NOT IS_CHAR_DEAD gang14_fa
					enemy_fa = gang14_fa
					enemyx_fa = 2575.5647
					enemyy_fa = -1284.9652
					enemyz_fa = 1030.4274
					enemyx2_fa = 2575.5127
					enemyy2_fa = -1303.2421
					enemyz2_fa = 1036.7812
					GOSUB run2shoot_falabel
					gang14_faflag = 2
				ENDIF
			ENDIF

			floor1_faflag = 10
		ENDIF
	ENDIF

	//player goes to the next floor
	IF floor1_faflag = 10
		IF IS_CHAR_IN_AREA_3D scplayer 2577.98 -1307.44 1034.5 2583.49 -1298.94 1039.3 FALSE	
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL PLAYER1 OFF
			REMOVE_BLIP carter_fablip
			CLEAR_MISSION_AUDIO 3

			SET_CHAR_COORDINATES scplayer 2575.0364 -1305.7413 1036.7812 
			SET_CHAR_HEADING scplayer 273.5172

			DELETE_CHAR gang7_fa
			DELETE_CHAR gang8_fa
			DELETE_CHAR gang10_fa
			DELETE_CHAR gang11_fa
			DELETE_CHAR gang12_fa
			DELETE_CHAR gang13_fa
			DELETE_CHAR gang1_fa
			DELETE_CHAR gang3_fa
			DELETE_CHAR gang4_fa
			DELETE_CHAR gang5_fa
			DELETE_CHAR gang6_fa
			DELETE_CHAR gang7_fa
			DELETE_CHAR gang8_fa
			DELETE_CHAR gang10_fa
			DELETE_CHAR gang11_fa
			DELETE_CHAR gang12_fa
			DELETE_CHAR gang13_fa
			DELETE_CHAR swat1_fa
			DELETE_CHAR swat2_fa
			DELETE_CAR swatvan_fa
			DELETE_CHAR gang14_fa
			DELETE_CHAR gang15_fa
			DELETE_CHAR gang16_fa
			DELETE_CHAR gang17_fa
			DELETE_CHAR gang18_fa
			DELETE_OBJECT door_fa
			MARK_MODEL_AS_NO_LONGER_NEEDED SWAT			
			MARK_MODEL_AS_NO_LONGER_NEEDED SWATVAN
			MARK_MODEL_AS_NO_LONGER_NEEDED AK47
			MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
			MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
			MARK_MODEL_AS_NO_LONGER_NEEDED imcompmovedr1_las

			SET_FIXED_CAMERA_POSITION 2579.6863 -1310.0020 1037.2235 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2579.7361 -1309.0225 1037.4183 JUMP_CUT

			PRINT_BIG RM4_18 1000 2 //Floor 2: Drug Lab

			WAIT 1000

			TASK_GO_STRAIGHT_TO_COORD scplayer 2580.0911 -1305.6494 1036.7734 PEDMOVE_WALK 4000

			floor1_faflag = 11
		ENDIF
	ENDIF


	IF floor1_faflag = 11
		IF LOCATE_STOPPED_CHAR_ON_FOOT_2D scplayer 2580.0911 -1305.6494 3.0 3.0 FALSE
			ADD_BLIP_FOR_COORD 2521.52 -1301.89 1047.37 carter_fablip
			SET_BLIP_ENTRY_EXIT carter_fablip 2540.83 -1304.05 1.0
			REQUEST_MODEL BMYCG
			REQUEST_MODEL HMYCM
			REQUEST_MODEL SFR1
			REQUEST_MODEL M4
			REQUEST_MODEL COLT45
			REQUEST_MODEL BARREL4

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED BMYCG
				OR NOT HAS_MODEL_LOADED HMYCM
				OR NOT HAS_MODEL_LOADED SFR1
				OR NOT HAS_MODEL_LOADED M4
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED COLT45
				OR NOT HAS_MODEL_LOADED BARREL4
				WAIT 0
			ENDWHILE

			CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2580.953 -1285.117 1044.173 armour2_fa
			CREATE_PICKUP HEALTH PICKUP_ONCE 2528.456 -1282.385 1048.337 health2_fa	

			CREATE_OBJECT BARREL4 2560.3086 -1290.7020 1043.1328 barrel1_fa
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel1_fa TRUE
			MAKE_OBJECT_TARGETTABLE barrel1_fa TRUE

			CREATE_OBJECT BARREL4 2540.4673 -1306.6808 1047.2969 barrel2_fa
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel2_fa TRUE
			MAKE_OBJECT_TARGETTABLE barrel2_fa TRUE

			CREATE_OBJECT BARREL4 2545.4915 -1296.2844 1043.1305 barrel3_fa
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel3_fa TRUE
			MAKE_OBJECT_TARGETTABLE barrel3_fa TRUE

			CREATE_OBJECT BARREL4 2538.1714 -1289.8064 1043.1328 barrel4_fa
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel4_fa TRUE
			MAKE_OBJECT_TARGETTABLE barrel4_fa TRUE

			//1 in security room
			CREATE_CHAR PEDTYPE_MISSION1 SFR1 2580.8091 -1285.6500 1043.1328 gang1_fa
			SET_CHAR_HEADING gang1_fa 178.4956 
			SET_CHAR_DECISION_MAKER	gang1_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang1_fa 2
			SET_CHAR_HEALTH gang1_fa 150
			SET_CHAR_MAX_HEALTH gang1_fa 150
			gang1_faflag = 0

			//2 tough guy back to player
			CREATE_CHAR PEDTYPE_MISSION1 SFR1 2574.7358 -1295.8608 1043.1328 gang2_fa
			SET_CHAR_HEADING gang2_fa 183.0452
			SET_CHAR_DECISION_MAKER	gang2_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang2_fa WEAPONTYPE_PISTOL 99999
			SET_CHAR_WEAPON_SKILL gang2_fa WEAPONSKILL_PRO
			SET_CHAR_RELATIONSHIP gang2_fa ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_AREA_VISIBLE gang2_fa 2
			SET_SENSE_RANGE gang2_fa 10.0
			SET_CHAR_ACCURACY gang2_fa 60
			gang2_faflag = 0
			
			//shoot in
			CREATE_CHAR PEDTYPE_MISSION1 HMYCM 2561.648 -1300.648 1043.17 gang3_fa
			SET_CHAR_HEADING gang3_fa 270.0
			SET_CHAR_DECISION_MAKER	gang3_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang3_fa 2
			SET_CHAR_PROOFS gang3_fa FALSE FALSE TRUE FALSE FALSE
			SET_CHAR_HEALTH gang3_fa 200
			SET_CHAR_MAX_HEALTH gang3_fa 200

			gang3_faflag = 0

			//shoot in 
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2545.648 -1300.648 1043.17 gang4_fa
			SET_CHAR_HEADING gang4_fa 270.0
			SET_CHAR_DECISION_MAKER	gang4_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang4_fa 2
			SET_CHAR_PROOFS gang4_fa FALSE FALSE TRUE FALSE FALSE
			SET_CHAR_HEALTH gang4_fa 200
			SET_CHAR_MAX_HEALTH gang4_fa 200
			SET_CHAR_SHOOT_RATE gang4_fa 70
			gang4_faflag = 0
			
			//shoot in 
			CREATE_CHAR PEDTYPE_MISSION1 HMYCM 2538.348 -1287.148 1043.1328 gang5_fa
			SET_CHAR_HEADING gang5_fa 270.0
			SET_CHAR_DECISION_MAKER	gang5_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang5_fa WEAPONTYPE_M4 99999
			SET_CHAR_PROOFS gang5_fa FALSE FALSE TRUE FALSE FALSE
			SET_CHAR_AREA_VISIBLE gang5_fa 2
			SET_CHAR_HEALTH gang5_fa 200
			SET_CHAR_MAX_HEALTH gang5_fa 200
			gang5_faflag = 0

			//shoot in
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2553.648 -1287.148 1043.1328  gang6_fa
			SET_CHAR_HEADING gang6_fa 270.0
			SET_CHAR_DECISION_MAKER	gang6_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang6_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang6_fa 2
			SET_CHAR_HEALTH gang6_fa 200
			SET_CHAR_MAX_HEALTH gang6_fa 200
			SET_CHAR_SHOOT_RATE gang6_fa 60
			gang6_faflag = 0

			WAIT 250 

			//7 near tables kill normally
			CREATE_CHAR PEDTYPE_MISSION1 HMYCM 2552.7480 -1304.3599 1043.1328 gang7_fa
			SET_CHAR_HEADING gang7_fa 87.8604 
			SET_CHAR_DECISION_MAKER	gang7_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang7_fa WEAPONTYPE_PISTOL 99999
			SET_CHAR_WEAPON_SKILL gang7_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang7_fa 2
			SET_CHAR_HEALTH gang7_fa 200
			SET_CHAR_MAX_HEALTH gang7_fa 200
			SET_CHAR_SHOOT_RATE gang7_fa 80
			gang7_faflag = 0

			//9 breakable table first one on left kill normally
			CREATE_CHAR PEDTYPE_MISSION1 HMYCM 2555.8508 -1296.1154 1043.1305 gang9_fa
			SET_CHAR_HEADING gang9_fa 348.0356  
			SET_CHAR_DECISION_MAKER	gang9_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang9_fa WEAPONTYPE_PISTOL 99999
			SET_CHAR_WEAPON_SKILL gang9_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang9_fa 2
			SET_CHAR_PROOFS gang9_fa FALSE FALSE TRUE FALSE FALSE
			SET_CHAR_HEALTH gang9_fa 200
			SET_CHAR_MAX_HEALTH gang9_fa 200
			SET_CHAR_ACCURACY gang9_fa 80
			gang9_faflag = 0

			//10 breakable table far left stay in the same place
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2542.6062 -1296.0956 1043.1328 gang10_fa
			SET_CHAR_HEADING gang10_fa 353.9691
			SET_CHAR_DECISION_MAKER	gang10_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang10_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang10_fa 2
			SET_CHAR_HEALTH gang10_fa 200
			SET_CHAR_MAX_HEALTH gang10_fa 200
			gang10_faflag = 0

			//11 breakable stay in same place first right
			CREATE_CHAR PEDTYPE_MISSION1 HMYCM 2552.9243 -1290.8783 1043.1328 gang11_fa
			SET_CHAR_HEADING gang11_fa 187.0611
			SET_CHAR_DECISION_MAKER	gang11_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang11_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang11_fa 2
			SET_CHAR_HEALTH gang11_fa 200
			SET_CHAR_MAX_HEALTH gang11_fa 200
			SET_CHAR_ACCURACY gang11_fa 70
			gang11_faflag = 0

			//13 stay in same place back right solid tables
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2550.0588 -1281.2484 1043.1328 gang13_fa
			SET_CHAR_HEADING gang13_fa 230.8361 
			SET_CHAR_DECISION_MAKER	gang13_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang13_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang13_fa 2
			SET_CHAR_HEALTH gang13_fa 200
			SET_CHAR_MAX_HEALTH gang13_fa 200
			SET_CHAR_SHOOT_RATE gang13_fa 80
			gang13_faflag = 0

			WAIT 250

			//14 top of stairs stay in same place
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2531.9060 -1283.0620 1047.2969 gang14_fa
			SET_CHAR_HEADING gang14_fa 233.2448 
			SET_CHAR_DECISION_MAKER	gang14_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang14_fa WEAPONTYPE_M4 99999
			SET_CHAR_AREA_VISIBLE gang14_fa 2
			SET_CHAR_HEALTH gang14_fa 200
			SET_CHAR_MAX_HEALTH gang14_fa 200
			SET_CHAR_ACCURACY gang14_fa 50
			gang14_faflag = 0

			//15 same side 
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2548.9773 -1282.4260 1047.2969 gang15_fa
			SET_CHAR_HEADING gang15_fa 234.0824  
			SET_CHAR_DECISION_MAKER	gang15_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang15_fa WEAPONTYPE_PISTOL 99999
			SET_CHAR_WEAPON_SKILL gang15_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang15_fa 2
			SET_CHAR_HEALTH gang15_fa 200
			SET_CHAR_MAX_HEALTH gang15_fa 200
			gang15_faflag = 0

			//16 at the back
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG 2530.0352 -1293.182 1047.36 gang16_fa
			SET_CHAR_HEADING gang16_fa 268.475
			SET_CHAR_DECISION_MAKER	gang16_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang16_fa WEAPONTYPE_M4 99999
			SET_CHAR_HEALTH gang16_fa 200
			SET_CHAR_MAX_HEALTH gang16_fa 200
			SET_CHAR_AREA_VISIBLE gang16_fa 2
			SET_CHAR_ACCURACY gang16_fa 80
			gang16_faflag = 0


			SET_PLAYER_CONTROL PLAYER1 ON
			SWITCH_WIDESCREEN OFF
			SET_CHAR_HEADING scplayer 0.0
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			CLEAR_PRINTS
			PRINT_NOW RM4_23 7000 1 //~s~Make your way to ~y~Floor 3~s~.

			carter_faflag = 3
			floor2_faflag = 1
			floor1_faflag = 12
		ENDIF
	ENDIF 

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		2nd Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF carter_faflag = 3

	IF floor2_faflag = 1
		IF IS_CHAR_IN_AREA_3D scplayer 2576.3 -1299.12 1039.71 2584.46 -1281.22 1046.6 FALSE

			IF gang1_faflag = 0
				IF NOT IS_CHAR_DEAD gang1_fa
					enemy_fa = gang1_fa
					GOSUB stayshootnoduck_falabel
					gang1_faflag = 1
				ENDIF
			ENDIF

			floor2_faflag = 2
		ENDIF
	ENDIF

	IF gang2_faflag = 0
		IF NOT IS_CHAR_DEAD gang2_fa
			IF HAS_CHAR_SPOTTED_CHAR gang2_fa scplayer
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang2_fa scplayer
				TASK_KILL_CHAR_ON_FOOT gang2_fa scplayer
				gang2_faflag = 1
			ENDIF
		ENDIF
	ENDIF


	IF floor2_faflag = 2
		IF IS_CHAR_IN_AREA_3D scplayer 2571.5020 -1311.9929 1041.8575 2560.8064 -1276.3655 1046.7268 FALSE

			IF gang1_faflag = 0
				MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fa
				gang1_faflag = 1
			ENDIF

			IF gang3_faflag = 0
				IF NOT IS_CHAR_DEAD gang3_fa
					enemy_fa = gang3_fa
					enemyx_fa = 2561.648
					enemyy_fa = -1300.648
					enemyz_fa = 1043.17
					shootimer_s4 = 3000
					heading_fa = 270.0
					GOSUB peekright_falabel
					gang3_faflag = 1
				ENDIF
			ENDIF

			IF gang4_faflag = 0
				IF NOT IS_CHAR_DEAD gang4_fa
					enemy_fa = gang4_fa
					enemyx_fa = 2545.648
					enemyy_fa = -1300.648
					enemyz_fa = 1043.17
					shootimer_s4 = 1200
					heading_fa = 270.0
					GOSUB peekright_falabel
					gang4_faflag = 1
				ENDIF
			ENDIF

			IF gang5_faflag = 0
				IF NOT IS_CHAR_DEAD gang5_fa
					enemy_fa = gang5_fa
					enemyx_fa = 2538.348			
					enemyy_fa = -1287.148
					enemyz_fa = 1043.1328
					shootimer_s4 = 1500
					heading_fa = 270.0
					GOSUB peekleft_falabel
					gang5_faflag = 1
				ENDIF
			ENDIF

			IF gang6_faflag = 0
				IF NOT IS_CHAR_DEAD gang6_fa
					enemy_fa = gang6_fa
					enemyx_fa = 2553.648
					enemyy_fa = -1287.148
					enemyz_fa = 1043.1328
					shootimer_s4 = 2000
					heading_fa = 270.0
					GOSUB peekleft_falabel 
					gang6_faflag = 1
				ENDIF
			ENDIF

			//7 near tables kill normally
			IF gang7_faflag = 0
				IF NOT IS_CHAR_DEAD	gang7_fa
					TASK_KILL_CHAR_ON_FOOT gang7_fa scplayer
					gang7_faflag = 1
				ENDIF
			ENDIF

			//9 breakable table first one on left kill normally
			IF gang9_faflag = 0
				IF NOT IS_CHAR_DEAD	gang9_fa
					TASK_KILL_CHAR_ON_FOOT gang9_fa scplayer
					gang9_faflag = 1
				ENDIF
			ENDIF

			//10 breakable table far left stay in the same place
			IF gang10_faflag = 0
				IF NOT IS_CHAR_DEAD gang10_fa
					enemy_fa = gang10_fa
					GOSUB stayshoot_falabel
					gang10_faflag = 1
				ENDIF
			ENDIF

			//11 breakable stay in same place first right
			IF gang11_faflag = 0
				IF NOT IS_CHAR_DEAD gang11_fa
					enemy_fa = gang11_fa
					GOSUB stayshoot_falabel
					gang10_faflag = 1
				ENDIF
			ENDIF

			//13 stay in same place back right solid tables
			IF gang13_faflag = 0
				IF NOT IS_CHAR_DEAD gang13_fa
					TASK_KILL_CHAR_ON_FOOT gang13_fa scplayer
					gang13_faflag = 1
				ENDIF
			ENDIF

			//14 top of stairs stay in same place
			IF gang14_faflag = 0
				IF NOT IS_CHAR_DEAD gang14_fa
					enemy_fa = gang14_fa
					GOSUB stayshoot_falabel
					gang14_faflag = 1
				ENDIF
			ENDIF

			//15 same side 
			IF gang15_faflag = 0
				IF NOT IS_CHAR_DEAD gang15_fa
					enemy_fa = gang15_fa
					GOSUB stayshootnoduck_falabel
					gang15_faflag = 1
				ENDIF
			ENDIF
			
			//16 at the back
			IF gang16_faflag = 0
				IF NOT IS_CHAR_DEAD gang16_fa
					enemy_fa = gang16_fa
					GOSUB stayshootnoduck_falabel
					gang16_faflag = 1
				ENDIF
			ENDIF
		
			floor2_faflag = 3
		ENDIF
	ENDIF


	IF floor2_faflag = 3

		//mark as no longer needed
		IF gang3_faflag = 1
			IF NOT IS_CHAR_DEAD gang3_fa
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang3_fa scplayer
					IF NOT IS_CHAR_DEAD gang3_fa
					OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang3_fa scplayer 4.0 4.0 3.0 FALSE
						TASK_KILL_CHAR_ON_FOOT gang3_fa scplayer
						gang3_faflag = 2
					ELSE
						MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
						gang3_faflag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF gang4_faflag = 1
			IF NOT IS_CHAR_DEAD gang4_fa
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang4_fa scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang4_fa scplayer 4.0 4.0 3.0 FALSE
					IF NOT IS_CHAR_DEAD gang4_fa
						TASK_KILL_CHAR_ON_FOOT gang4_fa scplayer
						gang4_faflag = 2
					ELSE
						MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
						gang4_faflag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF gang5_faflag = 1
			IF NOT IS_CHAR_DEAD gang5_fa
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang5_fa scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang5_fa scplayer 5.0 5.0 3.0 FALSE
					IF NOT IS_CHAR_DEAD gang5_fa
						TASK_KILL_CHAR_ON_FOOT gang5_fa scplayer
						gang5_faflag = 2
					ELSE
						MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
						gang5_faflag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF gang6_faflag = 1
			IF NOT IS_CHAR_DEAD gang6_fa
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang6_fa scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang6_fa scplayer 5.0 5.0 3.0 FALSE
					IF NOT IS_CHAR_DEAD gang6_fa
						TASK_KILL_CHAR_ON_FOOT gang6_fa scplayer
						gang6_faflag = 2
					ELSE
						gang6_faflag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		IF gang3_faflag = 1
		OR gang3_faflag = 2
			IF IS_CHAR_DEAD gang3_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
				secondfloorcounter_fa++
				gang3_faflag = 3
			ENDIF
		ENDIF
		IF gang4_faflag = 1
		OR gang4_faflag = 2
			IF IS_CHAR_DEAD gang4_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
				secondfloorcounter_fa++
				gang4_faflag = 3
			ENDIF
		ENDIF
		IF gang5_faflag = 1
		OR gang5_faflag = 2
			IF IS_CHAR_DEAD gang5_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
				secondfloorcounter_fa++
				gang5_faflag = 3
			ENDIF
		ENDIF
		IF gang6_faflag = 1
		OR gang6_faflag = 2
			IF IS_CHAR_DEAD gang6_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fa
				secondfloorcounter_fa++
				gang6_faflag = 3
			ENDIF
		ENDIF
		IF gang7_faflag = 1
			IF IS_CHAR_DEAD gang7_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fa
				secondfloorcounter_fa++
				gang7_faflag = 2
			ENDIF
		ENDIF
		IF gang10_faflag = 1
			IF IS_CHAR_DEAD gang10_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fa
				secondfloorcounter_fa++
				gang10_faflag = 2
			ENDIF
		ENDIF
		IF gang11_faflag = 1
			IF IS_CHAR_DEAD gang11_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fa
				secondfloorcounter_fa++
				gang11_faflag = 2
			ENDIF
		ENDIF
		IF gang13_faflag = 1
			IF IS_CHAR_DEAD gang13_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fa
				secondfloorcounter_fa++
				gang13_faflag = 2
			ENDIF
		ENDIF
		IF gang14_faflag = 1
			IF IS_CHAR_DEAD gang14_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fa
				secondfloorcounter_fa++
				gang14_faflag = 2
			ENDIF
		ENDIF
		IF gang15_faflag = 1
			IF IS_CHAR_DEAD gang15_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fa
				secondfloorcounter_fa++
				gang15_faflag = 2
			ENDIF
		ENDIF
		IF gang16_faflag = 1
			IF IS_CHAR_DEAD gang16_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang16_fa
				secondfloorcounter_fa++
				gang16_faflag = 2
			ENDIF
		ENDIF

		//two guys come in from the bottom
		IF druglabguys1_faflag = 0
			IF secondfloorcounter_fa > 5
				IF IS_CHAR_IN_AREA_3D scplayer 2551.12 -1276.34 1046.55 2543.07 -1287.63 1050.46 FALSE

					//-> 2566.2883 -1301.6373 1043.1328 86.8088 ->2565.7402 -1297.0906 1043.1328 71.4430 stay
					CREATE_CHAR PEDTYPE_MISSION1 SFR1 2573.4482 -1301.9808 1043.1328 gang17_fa
					SET_CHAR_HEADING gang17_fa 87.1597
					SET_CHAR_DECISION_MAKER	gang17_fa carter_DM
					GIVE_WEAPON_TO_CHAR gang17_fa WEAPONTYPE_M4 99999
					SET_CHAR_AREA_VISIBLE gang17_fa 2
					SET_CHAR_HEALTH gang17_fa 200
					SET_CHAR_MAX_HEALTH gang17_fa 200
					enemy_fa = gang17_fa
					enemyx_fa = 2566.2883
					enemyy_fa = -1301.6373
					enemyz_fa = 1043.1328
					enemyx2_fa = 2565.7402
					enemyy2_fa = -1297.0906
					enemyz2_fa = 1043.1328
					GOSUB run2shoot_falabel
					gang17_faflag = 1

					//-> 2566.9844 -1302.0898 1043.1328 91.2501 kill normally
					CREATE_CHAR PEDTYPE_MISSION1 SFR1 2575.3083 -1301.9081 1043.1328 gang18_fa
					SET_CHAR_HEADING gang18_fa 91.2377 
					SET_CHAR_DECISION_MAKER	gang18_fa carter_DM
					GIVE_WEAPON_TO_CHAR gang18_fa WEAPONTYPE_M4 99999
					SET_CHAR_AREA_VISIBLE gang18_fa 2
					SET_CHAR_HEALTH gang18_fa 200
					SET_CHAR_MAX_HEALTH gang18_fa 200
					enemy_fa = gang18_fa
					enemyx_fa = 2566.9844
					enemyy_fa = -1302.0898
					enemyz_fa = 1043.1328
					GOSUB runnostay_falabel
					gang18_faflag = 1

					druglabguys1_faflag = 1
				ENDIF
			ENDIF
		ENDIF

		//two guys come in from the side
		IF druglabguys2_faflag = 0
			IF secondfloorcounter_fa > 8
				IF IS_CHAR_IN_AREA_3D scplayer 2566.09 -1301.26 1046.91 2554.56 -1309.06 1050.0	FALSE

					//-> 2542.3171 -1305.6744 1047.2969
					CREATE_CHAR PEDTYPE_MISSION1 SFR1 2527.2715 -1305.6477 1047.2969 gang19_fa
					SET_CHAR_HEADING gang19_fa 269.5592
					SET_CHAR_DECISION_MAKER	gang19_fa carter_DM
					GIVE_WEAPON_TO_CHAR gang19_fa WEAPONTYPE_PISTOL 99999
					SET_CHAR_WEAPON_SKILL gang19_fa WEAPONSKILL_PRO
					SET_CHAR_AREA_VISIBLE gang19_fa 2
					enemy_fa = gang19_fa
					enemyx_fa = 2542.3171
					enemyy_fa = -1305.6744
					enemyz_fa = 1047.2969
					GOSUB runnostay_falabel
					gang19_faflag = 1
					
					//-> 2534.5671 -1305.5472 1047.2969
					CREATE_CHAR PEDTYPE_MISSION1 SFR1 2523.7000 -1305.8778 1047.2969 gang20_fa
					SET_CHAR_HEADING gang20_fa 273.1955
					SET_CHAR_DECISION_MAKER	gang20_fa carter_DM
					GIVE_WEAPON_TO_CHAR gang20_fa WEAPONTYPE_PISTOL 99999
					SET_CHAR_WEAPON_SKILL gang20_fa WEAPONSKILL_PRO
					SET_CHAR_AREA_VISIBLE gang20_fa 2
					enemy_fa = gang20_fa
					enemyx_fa = 2534.5671 
					enemyy_fa = -1305.5472
					enemyz_fa = 1047.2969
					GOSUB runnostay_falabel
					gang20_faflag = 1
					druglabguys2_faflag = 1
				ENDIF
			ENDIF
		ENDIF

		IF gang17_faflag = 1
			IF IS_CHAR_DEAD gang17_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang17_fa
				secondfloorcounter_fa++
				gang17_faflag = 2
			ENDIF
		ENDIF
		IF gang18_faflag = 1
			IF IS_CHAR_DEAD gang18_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang18_fa
				secondfloorcounter_fa++
				gang18_faflag = 2
			ENDIF
		ENDIF
		IF gang19_faflag = 1
			IF IS_CHAR_DEAD gang19_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang19_fa
				secondfloorcounter_fa++
				gang19_faflag = 2
			ENDIF
		ENDIF
		IF gang20_faflag = 1
			IF IS_CHAR_DEAD gang20_fa
				MARK_CHAR_AS_NO_LONGER_NEEDED gang20_fa
				secondfloorcounter_fa++
				gang20_faflag = 2
			ENDIF
		ENDIF

		IF IS_CHAR_IN_AREA_3D scplayer 2522.35 -1309.65 1045.6 2513.5 -1295.97 1050.32 FALSE
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL PLAYER1 OFF

			SET_CHAR_COORDINATES scplayer 2525.0786 -1301.8910 1047.2969
			SET_CHAR_HEADING scplayer 89.7295 

			DELETE_CHAR gang1_fa
			DELETE_CHAR gang2_fa
			DELETE_CHAR gang3_fa
			DELETE_CHAR gang4_fa
			DELETE_CHAR gang5_fa
			DELETE_CHAR gang6_fa
			DELETE_CHAR gang7_fa
			DELETE_CHAR gang10_fa
			DELETE_CHAR gang11_fa
			DELETE_CHAR gang13_fa
			DELETE_CHAR gang14_fa
			DELETE_CHAR gang15_fa
			DELETE_CHAR gang16_fa
			DELETE_CHAR gang17_fa
			DELETE_CHAR gang18_fa
			DELETE_CHAR gang19_fa
			DELETE_CHAR gang20_fa
			DELETE_OBJECT barrel1_fa
			DELETE_OBJECT barrel2_fa
			DELETE_OBJECT barrel3_fa
			DELETE_OBJECT barrel4_fa
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYCG
			MARK_MODEL_AS_NO_LONGER_NEEDED WFYCRK
			MARK_MODEL_AS_NO_LONGER_NEEDED HMYCM
			MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
			MARK_MODEL_AS_NO_LONGER_NEEDED M4
			MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
			MARK_MODEL_AS_NO_LONGER_NEEDED BARREL4

			SET_FIXED_CAMERA_POSITION 2520.2822 -1306.3627 1047.5994 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2520.2996 -1305.3882 1047.8229 JUMP_CUT

			PRINT_BIG RM4_19 1000 2 //Floor 3: Baller's Lounge

			WAIT 1000

			TASK_GO_STRAIGHT_TO_COORD scplayer 2520.0254 -1301.8455 1047.2891 PEDMOVE_WALK 5000
			
			floor2_faflag = 4
		ENDIF

	ENDIF

	IF floor2_faflag = 4
		IF LOCATE_STOPPED_CHAR_ON_FOOT_2D scplayer 2520.0254 -1301.8455 3.0 3.0 FALSE

			REQUEST_MODEL BALLAS3
			REQUEST_MODEL MAFFA
			REQUEST_MODEL LSV2
			REQUEST_MODEL MICRO_UZI
			REQUEST_MODEL CHROMEGUN

			REQUEST_MODEL BFYRI
			REQUEST_ANIMATION STRIP
			REQUEST_MODEL SBFYSTR

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED BALLAS3
				OR NOT HAS_MODEL_LOADED MAFFA
				OR NOT HAS_MODEL_LOADED LSV2
				OR NOT HAS_MODEL_LOADED MICRO_UZI
				OR NOT HAS_MODEL_LOADED CHROMEGUN
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED BFYRI
				OR NOT HAS_ANIMATION_LOADED STRIP
				OR NOT HAS_MODEL_LOADED SBFYSTR
				WAIT 0
			ENDWHILE

			CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2520.4 -1281.287 1054.681 armour3_fa

			WAIT 250

			//19 out front security gate
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2520.891 -1281.763 1053.681 gang19_fa
			SET_CHAR_HEADING gang19_fa 180.628 
			SET_CHAR_DECISION_MAKER	gang19_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang19_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang19_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang19_fa 2
			gang19_faflag = 0

			//1 chatting
			CREATE_CHAR PEDTYPE_MISSION1 LSV2 2529.8130 -1285.0626 1053.6406 gang1_fa
			SET_CHAR_HEADING gang1_fa 182.0171 
			SET_CHAR_DECISION_MAKER	gang1_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_AREA_VISIBLE gang1_fa 2
			SET_CHAR_ACCURACY gang1_fa 50
			gang1_faflag = 0

			//2 chatting
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2529.9204 -1286.8298 1053.6406 gang2_fa
			SET_CHAR_HEADING gang2_fa 358.5364
			SET_CHAR_DECISION_MAKER	gang2_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang2_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang2_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang2_fa 2
			SET_CHAR_ACCURACY gang2_fa 50
			gang2_faflag = 0
	        TASK_CHAT_WITH_CHAR gang2_fa gang1_fa TRUE TRUE
	        TASK_CHAT_WITH_CHAR gang1_fa gang2_fa FALSE TRUE
			
			//hoochie -> cower 2537.7356 -1306.4485 1053.6406 183.9341 
			CREATE_CHAR PEDTYPE_MISSION1 BFYRI 2537.1504 -1288.1250 1053.6406 hoochie1_fa
			SET_CHAR_HEADING hoochie1_fa 280.1631
			SET_CHAR_DECISION_MAKER	hoochie1_fa carter_DM
			SET_CHAR_HEALTH hoochie1_fa 1

			//3 -> 2540.3699 -1280.7904 1053.6470 358.1396 //TASK_TOGGLE_DUCK gang3_fa -1
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2540.3643 -1287.5535 1053.6470 gang3_fa
			SET_CHAR_HEADING gang3_fa 89.7930
			SET_CHAR_DECISION_MAKER	gang3_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang3_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang3_fa 2
			SET_CHAR_ACCURACY gang3_fa 30
			SET_CHAR_HEALTH gang3_fa 200
			SET_CHAR_MAX_HEALTH gang3_fa 200
			gang3_faflag = 0

			CREATE_CHAR PEDTYPE_MISSION1 LSV2 2537.074 -1287.274 1053.681 gang4_fa
			SET_CHAR_HEADING gang4_fa 265.803
			SET_CHAR_DECISION_MAKER	gang4_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_SHOTGUN 99999
			SET_CHAR_AREA_VISIBLE gang4_fa 2
			SET_CHAR_PROOFS gang4_fa FALSE FALSE TRUE FALSE FALSE
			SET_CHAR_HEALTH gang4_fa 150
			SET_CHAR_MAX_HEALTH gang4_fa 150
			gang4_faflag = 0
			
			//5 sitting down
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2542.2263 -1294.3811 1053.6406 gang5_fa
			SET_CHAR_HEADING gang5_fa 89.8814
			SET_CHAR_DECISION_MAKER	gang5_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang5_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang5_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang5_fa 2
			TASK_SIT_DOWN gang5_fa 400000
			SET_CHAR_ACCURACY gang5_fa 30
			SET_CHAR_HEALTH gang5_fa 200
			SET_CHAR_MAX_HEALTH gang5_fa 200
			gang5_faflag = 0

			WAIT 250
			
			//7 stay in same place ducking
			CREATE_CHAR PEDTYPE_MISSION1 LSV2 2539.4766 -1303.4313 1053.6406 gang7_fa
			SET_CHAR_HEADING gang7_fa 10.5492
			SET_CHAR_DECISION_MAKER	gang7_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang7_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang7_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang7_fa 2
			SET_CHAR_HEALTH gang7_fa 150
			SET_CHAR_MAX_HEALTH gang7_fa 150
			gang7_faflag = 0

			//8 stay and shoot ducking
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2533.9622 -1306.9164 1053.6406 gang8_fa
			SET_CHAR_HEADING gang8_fa 338.7091
			SET_CHAR_DECISION_MAKER	gang8_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang8_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang8_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang8_fa 2
			SET_CHAR_HEALTH gang8_fa 200
			SET_CHAR_MAX_HEALTH gang8_fa 200
			gang8_faflag = 0
			REMOVE_BLIP carter_fablip
			ADD_BLIP_FOR_COORD 2579.0 -1284.0 1054.686 carter_fablip
			SET_BLIP_ENTRY_EXIT carter_fablip 2540.83 -1304.05 1.0
			SET_PLAYER_CONTROL PLAYER1 ON
			SWITCH_WIDESCREEN OFF
			SET_CHAR_HEADING scplayer 0.0
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			CLEAR_PRINTS
			PRINT_NOW RM4_21 5000 1 //~s~Make your way to ~y~Floor 4~s~.

			carter_faflag = 4
			floor3_faflag = 1
			floor2_faflag = 5

		ENDIF
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		3rd Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF carter_faflag = 4
	
	//first area where there are two guards + guard in security bit
	IF floor3_faflag = 1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2520.23 -1293.09 1051.49 6.0 6.0 5.0 FALSE
			
			IF gang19_faflag = 0
				IF NOT IS_CHAR_DEAD gang19_fa
					enemy_fa = gang19_fa
					GOSUB stayshootnoduck_falabel
					gang19_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 2

		ENDIF
	ENDIF

	IF floor3_faflag = 2	
		IF IS_CHAR_IN_AREA_3D scplayer 2522.32 -1293.64 1052.5 2532.41 -1273.44 1055.89 FALSE
			TIMERA = 0
			
			IF gang2_faflag = 0
				IF NOT IS_CHAR_DEAD gang2_fa
					TASK_KILL_CHAR_ON_FOOT gang2_fa scplayer
					gang2_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 3
		ELSE
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang1_fa scplayer
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang2_fa scplayer

				IF gang2_faflag = 0
					IF NOT IS_CHAR_DEAD gang2_fa
						TASK_KILL_CHAR_ON_FOOT gang2_fa scplayer
						gang2_faflag = 1
					ENDIF
				ENDIF

				floor3_faflag = 3				
			ENDIF
		ENDIF
	ENDIF

	IF floor3_faflag = 3
		IF TIMERA > 250
			
			IF gang1_faflag = 0
				IF NOT IS_CHAR_DEAD gang1_fa
					TASK_KILL_CHAR_ON_FOOT gang1_fa scplayer
					gang1_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 4
		ENDIF
	ENDIF

	//the bar area
	IF floor3_faflag = 4
		IF IS_CHAR_IN_AREA_3D scplayer 2530.68 -1306.62 1052.16 2542.05 -1275.19 1057.08 FALSE

			IF NOT IS_CHAR_DEAD hoochie1_fa 
				enemy_fa = hoochie1_fa
				enemyx_fa = 2537.7356
				enemyy_fa = -1306.4485
				enemyz_fa = 1053.6406
				GOSUB flee_falable
			ENDIF


			IF gang3_faflag = 0
				IF NOT IS_CHAR_DEAD gang3_fa
					TASK_TOGGLE_DUCK gang3_fa -1
					enemy_fa = gang3_fa
					enemyx_fa = 2540.3699
					enemyy_fa = -1280.7904
					enemyz_fa = 1053.6470 
					GOSUB runnostay_falabel
					gang3_faflag = 1
				ENDIF
			ENDIF

			IF gang4_faflag = 0
				IF NOT IS_CHAR_DEAD gang4_fa
					TASK_KILL_CHAR_ON_FOOT gang4_fa scplayer
					gang4_faflag = 1
				ENDIF
			ENDIF

			IF gang5_faflag = 0
				IF NOT IS_CHAR_DEAD gang5_fa
					TASK_KILL_CHAR_ON_FOOT gang5_fa scplayer
					gang5_faflag = 1
				ENDIF
			ENDIF

			IF gang7_faflag = 0
				IF NOT IS_CHAR_DEAD gang7_fa
					enemy_fa = gang7_fa
					GOSUB stayshoot_falabel
					gang7_faflag = 1
				ENDIF
			ENDIF

			IF gang8_faflag = 0
				IF NOT IS_CHAR_DEAD gang8_fa
					enemy_fa = gang8_fa
					GOSUB stayshootnoduck_falabel
					gang8_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 5
		ENDIF
	ENDIF



	IF floor3_faflag = 5
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2537.78 -1298.62 1055.1 5.0 5.0 5.0 FALSE
			
			//6 run to then kill normally but ducking -> 2545.2378 -1291.7911 1053.6406 359.7367 shotgun
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2545.1926 -1302.5448 1053.6470  gang6_fa
			SET_CHAR_HEADING gang6_fa 359.6221
			SET_CHAR_DECISION_MAKER	gang6_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang6_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang6_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang6_fa 2
			SET_CHAR_HEALTH gang6_fa 150
			SET_CHAR_MAX_HEALTH gang6_fa 150
			TASK_TOGGLE_DUCK gang6_fa -1
			gang6_faflag = 0
			
			//9 stay in same place uzi double
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2548.8081 -1288.9451 1053.6406 gang9_fa
			SET_CHAR_HEADING gang9_fa 168.1978  
			SET_CHAR_DECISION_MAKER	gang9_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang9_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang9_fa WEAPONSKILL_PRO
			SET_CHAR_ACCURACY gang9_fa 50
			SET_CHAR_AREA_VISIBLE gang9_fa 2
			SET_CHAR_HEALTH gang9_fa 150
			SET_CHAR_MAX_HEALTH gang9_fa 150
			gang9_faflag = 0

			//11 and 12 chat 11 runs to coord then kill normally 12 runs to coords then stay still
			//11 run and kill -> 2553.8767 -1292.1445 1053.6470
			CREATE_CHAR PEDTYPE_MISSION1 LSV2 2548.2112 -1293.4908 1053.6406 gang11_fa
			SET_CHAR_HEADING gang11_fa 177.2480 
			SET_CHAR_DECISION_MAKER	gang11_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang11_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang11_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang11_fa 2
			SET_CHAR_HEALTH gang11_fa 150
			SET_CHAR_MAX_HEALTH gang11_fa 150
			SET_CHAR_ACCURACY gang11_fa 30
			gang11_faflag = 0

			//12 run2stay
			//-> 2553.2173 -1296.9026 1053.6470 274.2116 
			//-> 2557.1714 -1296.6177 1053.6406 274.1124 
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2548.4727 -1295.8341 1053.6470 gang12_fa
			SET_CHAR_HEADING gang12_fa 6.5626
			SET_CHAR_DECISION_MAKER	gang12_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang12_fa WEAPONTYPE_SHOTGUN 99999
			SET_CHAR_ACCURACY gang12_fa 30
			SET_CHAR_AREA_VISIBLE gang12_fa 2
			SET_CHAR_HEALTH gang12_fa 150
			SET_CHAR_MAX_HEALTH gang12_fa 150
			gang12_faflag = 0


			//kill normally
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2568.735 -1294.134 1053.681 gang18_fa
			SET_CHAR_HEADING gang18_fa 270.3791
			SET_CHAR_DECISION_MAKER	gang18_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang18_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang18_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang18_fa 2
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang18_fa TRUE 
			SET_CHAR_HEALTH gang18_fa 150
			SET_CHAR_MAX_HEALTH gang18_fa 150
			gang18_faflag = 0

			//wall huggers
			//10
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2571.7 -1294.298 1053.680 gang10_fa
			SET_CHAR_HEADING gang10_fa 90.0 
			SET_CHAR_DECISION_MAKER	gang10_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang10_fa WEAPONTYPE_SHOTGUN 99999
			SET_CHAR_AREA_VISIBLE gang10_fa 2
			gang10_faflag = 0

			//13
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2571.8015 -1289.5913 1053.6406 gang13_fa
			SET_CHAR_HEADING gang13_fa 90.3346 
			SET_CHAR_DECISION_MAKER	gang13_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang13_fa WEAPONTYPE_SHOTGUN 99999
			SET_CHAR_ACCURACY gang13_fa 50
			SET_CHAR_AREA_VISIBLE gang13_fa 2
			SET_CHAR_HEALTH gang13_fa 150
			SET_CHAR_MAX_HEALTH gang13_fa 150
			gang13_faflag = 0

			floor3_faflag = 6

		ENDIF
	ENDIF

	//hallway
	IF floor3_faflag = 6
		IF IS_CHAR_IN_AREA_3D scplayer 2543.87 -1308.98 1050.78 2553.03 -1276.55 1059.5 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2543.88 -1304.9 1053.81 3.0 3.0 3.0 FALSE
			DELETE_CHAR gang19_fa
			DELETE_CHAR gang1_fa
			DELETE_CHAR gang2_fa

			IF gang6_faflag = 0
				IF NOT IS_CHAR_DEAD gang6_fa
					TASK_TOGGLE_DUCK gang6_fa -1
					enemy_fa = gang6_fa
					enemyx_fa = 2545.2378
					enemyy_fa = -1291.7911
					enemyz_fa = 1053.6406
					GOSUB runnostay_falabel
					gang6_faflag = 1
				ENDIF
			ENDIF
			
			//9 stay in same place uzi double
			IF gang9_faflag = 0
				IF NOT IS_CHAR_DEAD gang9_fa
					enemy_fa = gang9_fa
					GOSUB stayshootnoduck_falabel
					gang9_faflag = 1
				ENDIF
			ENDIF

			IF gang11_faflag = 0
				IF NOT IS_CHAR_DEAD gang11_fa
					enemy_fa = gang11_fa
					enemyx_fa = 2553.8767
					enemyy_fa = -1292.1445
					enemyz_fa = 1053.6470
					GOSUB runnostay_falabel
					gang11_faflag = 1
				ENDIF
			ENDIF

			IF gang12_faflag = 0			
				IF NOT IS_CHAR_DEAD gang12_fa
					enemy_fa = gang12_fa
					enemyx_fa = 2553.2173
					enemyy_fa = -1296.9026
					enemyz_fa = 1053.6470
					enemyx2_fa = 2557.1714
					enemyy2_fa = -1296.6177
					enemyz2_fa = 1053.6406
					GOSUB run2shoot_falabel
					gang12_faflag = 1
				ENDIF
			ENDIF

			IF gang18_faflag = 0
				IF NOT IS_CHAR_DEAD gang18_fa
					TASK_KILL_CHAR_ON_FOOT gang18_fa scplayer
					gang18_faflag = 1
				ENDIF
			ENDIF

			IF gang10_faflag = 0
				IF NOT IS_CHAR_DEAD gang10_fa
					enemy_fa = gang10_fa
					enemyx_fa = 2571.7
					enemyy_fa = -1294.298
					enemyz_fa = 1053.680
					shootimer_s4 = 1200
					heading_fa = 90.0
					GOSUB peekright_falabel
					gang10_faflag = 1
				ENDIF
			ENDIF

			IF gang13_faflag = 0
				IF NOT IS_CHAR_DEAD gang13_fa
					enemy_fa = gang13_fa
					enemyx_fa = 2571.8015
					enemyy_fa = -1289.5913
					enemyz_fa = 1053.6406
					shootimer_s4 = 2000
					heading_fa = 90.0
					GOSUB peekright_falabel
					gang13_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 7
		ENDIF
	ENDIF

	//guys coming out of door 
	IF floor3_faflag = 7
		IF IS_CHAR_IN_AREA_3D scplayer 2544.0 -1298.31 1051.84 2577.11 -1278.61 1057.06 FALSE

			DELETE_CHAR hoochie1_fa
			DELETE_CHAR gang4_fa
			DELETE_CHAR	gang5_fa
			DELETE_CHAR gang7_fa
			DELETE_CHAR	gang8_fa

			//14 -> 2561.0762 -1295.2528 1053.6470 kill normally dual uzi
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2561.1443 -1302.8541 1053.6406 gang14_fa
			SET_CHAR_HEADING gang14_fa 357.9032 
			SET_CHAR_DECISION_MAKER	gang14_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang14_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang14_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang14_fa 2
			SET_CHAR_HEALTH gang14_fa 200
			SET_CHAR_MAX_HEALTH gang14_fa 200
			SET_CHAR_ACCURACY gang14_fa 40
			gang14_faflag = 0
			IF gang14_faflag = 0
				IF NOT IS_CHAR_DEAD gang14_fa
					TASK_TOGGLE_DUCK gang14_fa -1
					enemy_fa = gang14_fa
					enemyx_fa = 2561.0762
					enemyy_fa = -1295.2528
					enemyz_fa = 1053.6470
					GOSUB runnostay_falabel
					gang14_faflag = 1
				ENDIF
			ENDIF

			//15 ->2561.2412 -1292.8021 1053.6470 180.8039 kill normally dual uzi
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2561.1284 -1284.6572 1053.6406 gang15_fa
			SET_CHAR_HEADING gang15_fa 180.8092
			SET_CHAR_DECISION_MAKER	gang15_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang15_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang15_fa WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang15_fa 2
			SET_CHAR_ACCURACY gang15_fa 40
			SET_CHAR_HEALTH gang15_fa 150
			SET_CHAR_MAX_HEALTH gang15_fa 150
			gang15_faflag = 0
			IF gang15_faflag = 0
				IF NOT IS_CHAR_DEAD gang15_fa
					enemy_fa = gang15_fa
					enemyx_fa = 2561.2412
					enemyy_fa = -1292.8021
					enemyz_fa = 1053.6470
					GOSUB runnostay_falabel
					gang15_faflag = 1
				ENDIF
			ENDIF

			floor3_faflag = 8
		ENDIF
	ENDIF

	IF leftroom_faflag = 0
		IF IS_CHAR_IN_AREA_3D scplayer 2556.98 -1298.58 1052.0 2569.69 -1035.12 1056.21 FALSE

			CREATE_PICKUP HEALTH PICKUP_ONCE 2552.298 -1306.06 1054.681 health3_fa

			//17 stay in same place ducking and shoot
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2566.716 -1305.75 1053.681 gang17_fa
			SET_CHAR_HEADING gang17_fa 4.514
			SET_CHAR_DECISION_MAKER	gang17_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang17_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_ACCURACY gang17_fa 70
			SET_CHAR_AREA_VISIBLE gang17_fa 2
			gang17_faflag = 1
			TASK_KILL_CHAR_ON_FOOT gang17_fa scplayer

			CREATE_CHAR PEDTYPE_MISSION1 SBFYSTR 2557.0718 -1302.7988 1053.8751 hoochie3_fa
			SET_CHAR_HEADING hoochie3_fa 53.1978
			SET_CHAR_DECISION_MAKER	hoochie3_fa carter_DM			
			SET_CHAR_HEALTH hoochie3_fa 5
			TASK_PLAY_ANIM hoochie3_fa STRIP_E STRIP 8.0 TRUE TRUE TRUE FALSE -1

			leftroom_faflag = 1
		ENDIF
	ENDIF			 


	IF rightroom_faflag = 0
		IF IS_CHAR_IN_AREA_3D scplayer 2558.94 -1290.24 1052.74 2568.72 -1283.56 1057.43 FALSE
			//16 stay in same place ducking and shoot
			CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2552.475 -1280.687 1054.686 armour4_fa

			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2567.987 -1281.41 1053.687 gang16_fa
			SET_CHAR_HEADING gang16_fa 178.685
			SET_CHAR_DECISION_MAKER	gang16_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang16_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_ACCURACY gang16_fa 30
			SET_CHAR_AREA_VISIBLE gang16_fa 2
			gang16_faflag = 1
			TASK_KILL_CHAR_ON_FOOT gang16_fa scplayer

			CREATE_CHAR PEDTYPE_MISSION1 SBFYSTR 2557.8174 -1285.2181 1053.8762 hoochie4_fa
			SET_CHAR_HEADING hoochie4_fa 288.0473
			SET_CHAR_DECISION_MAKER	hoochie4_fa carter_DM	
			SET_CHAR_HEALTH hoochie4_fa 5
			TASK_PLAY_ANIM hoochie4_fa STRIP_A STRIP 8.0 TRUE TRUE TRUE FALSE -1
			rightroom_faflag = 1
		ENDIF
	ENDIF

	//mark as no longer needed
	IF gang10_faflag = 1
		IF NOT IS_CHAR_DEAD gang10_fa
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang10_fa scplayer
				IF NOT IS_CHAR_DEAD gang10_fa
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang10_fa scplayer 5.0 5.0 3.0 FALSE
					TASK_KILL_CHAR_ON_FOOT gang10_fa scplayer
					gang10_faflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fa
					gang10_faflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF gang13_faflag = 1
		IF NOT IS_CHAR_DEAD gang13_fa
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang13_fa scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang13_fa scplayer 5.0 5.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang13_fa
					TASK_KILL_CHAR_ON_FOOT gang13_fa scplayer
					gang13_faflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fa
					gang13_faflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF gang1_faflag = 1
		IF IS_CHAR_DEAD gang1_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fa
			firstfloorcounter_fa++
			gang1_faflag = 2
		ENDIF
	ENDIF
	IF gang3_faflag = 1
		IF IS_CHAR_DEAD gang3_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
			firstfloorcounter_fa++
			gang3_faflag = 2
		ENDIF
	ENDIF
	IF gang4_faflag = 1
		IF IS_CHAR_DEAD gang4_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
			firstfloorcounter_fa++
			gang4_faflag = 2
		ENDIF
	ENDIF
	IF gang5_faflag = 1
		IF IS_CHAR_DEAD gang5_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
			firstfloorcounter_fa++
			gang5_faflag = 2
		ENDIF
	ENDIF
	IF gang6_faflag = 1
		IF IS_CHAR_DEAD gang6_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fa
			firstfloorcounter_fa++
			gang6_faflag = 2
		ENDIF
	ENDIF
	IF gang7_faflag = 1
		IF IS_CHAR_DEAD gang7_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fa
			firstfloorcounter_fa++
			gang7_faflag = 3
		ENDIF
	ENDIF
	IF gang8_faflag = 1
		IF IS_CHAR_DEAD gang8_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fa
			firstfloorcounter_fa++
			gang8_faflag = 2
		ENDIF
	ENDIF
	IF gang10_faflag = 1
	OR gang10_faflag = 2
		IF IS_CHAR_DEAD gang10_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fa
			firstfloorcounter_fa++
			gang10_faflag = 3
		ENDIF
	ENDIF
	IF gang11_faflag = 1
		IF IS_CHAR_DEAD gang11_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fa
			firstfloorcounter_fa++
			gang11_faflag = 2
		ENDIF
	ENDIF
	IF gang12_faflag = 1
		IF IS_CHAR_DEAD gang12_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fa
			firstfloorcounter_fa++
			gang12_faflag = 2
		ENDIF
	ENDIF
	IF gang13_faflag = 1
	OR gang13_faflag = 2
		IF IS_CHAR_DEAD gang13_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fa
			firstfloorcounter_fa++
			gang13_faflag = 3
		ENDIF
	ENDIF
	IF gang14_faflag = 1
		IF IS_CHAR_DEAD gang14_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fa
			secondfloorcounter_fa++
			gang14_faflag = 2
		ENDIF
	ENDIF
	IF gang15_faflag = 1
		IF IS_CHAR_DEAD gang15_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fa
			secondfloorcounter_fa++
			gang15_faflag = 2
		ENDIF
	ENDIF
	IF gang16_faflag = 1
		IF IS_CHAR_DEAD gang16_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang16_fa
			secondfloorcounter_fa++
			gang16_faflag = 2
		ENDIF
	ENDIF
	IF gang17_faflag = 1
		IF IS_CHAR_DEAD gang17_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang17_fa
			secondfloorcounter_fa++
			gang17_faflag = 2
		ENDIF
	ENDIF
	IF gang18_faflag = 1
		IF IS_CHAR_DEAD gang18_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang18_fa
			secondfloorcounter_fa++
			gang18_faflag = 2
		ENDIF
	ENDIF
	IF gang19_faflag = 1
		IF IS_CHAR_DEAD gang19_fa
			MARK_CHAR_AS_NO_LONGER_NEEDED gang19_fa
			secondfloorcounter_fa++
			gang19_faflag = 2
		ENDIF
	ENDIF


	IF floor3_faflag = 8
		IF IS_CHAR_IN_AREA_3D scplayer 2577.43 -1281.21 1050.83 2584.7 -1291.47 1055.35 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2579.82 -1284.91 1054.12 3.0 3.0 3.0 FALSE
			PRINT_NOW RM4_24 5000 1//~s~Go get ~r~Big Smoke~s~.
			REMOVE_BLIP carter_fablip
			ADD_BLIP_FOR_COORD 2550.74 -1285.02 1060.62 carter_fablip
			SET_BLIP_ENTRY_EXIT carter_fablip 2540.83 -1304.05 1.0
			floor3_faflag = 9
		ENDIF
	ENDIF

	IF floor3_faflag = 9
		IF IS_CHAR_IN_AREA_3D scplayer 2577.46 -1306.59 1058.27 2571.97 -1275.1 1063.08 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2575.4 -1300.35 1061.016 3.0 3.0 3.0 FALSE
			REMOVE_BLIP carter_fablip
			DELETE_CHAR hoochie3_fa
			DELETE_CHAR hoochie4_fa
			DELETE_CHAR gang1_fa
			DELETE_CHAR gang2_fa
			DELETE_CHAR gang3_fa
			DELETE_CHAR gang4_fa
			DELETE_CHAR gang5_fa
			DELETE_CHAR gang6_fa
			DELETE_CHAR gang7_fa
			DELETE_CHAR gang8_fa
			DELETE_CHAR gang9_fa
			DELETE_CHAR gang10_fa
			DELETE_CHAR gang11_fa
			DELETE_CHAR gang12_fa
			DELETE_CHAR gang13_fa
			DELETE_CHAR gang14_fa
			DELETE_CHAR gang15_fa
			DELETE_CHAR gang16_fa
			DELETE_CHAR gang17_fa
			DELETE_CHAR gang18_fa
			DELETE_CHAR gang19_fa
			DELETE_CHAR gang20_fa

			SET_PLAYER_CONTROL PLAYER1 OFF
			//Smokes room in Carter block
			SET_FADING_COLOUR 0 0 0

			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SET_AREA_VISIBLE 2

			LOAD_CUTSCENE RIOT_4c
			 
			WHILE NOT HAS_CUTSCENE_LOADED
			    WAIT 0
			ENDWHILE
			 
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
			    WAIT 0
			ENDWHILE

			CLEAR_CUTSCENE

			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 0 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE


			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
			MARK_MODEL_AS_NO_LONGER_NEEDED MAFFA
			MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
			MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI
			REMOVE_ANIMATION STRIP
			REMOVE_ANIMATION SWAT
			MARK_MODEL_AS_NO_LONGER_NEEDED SBFYSTR 

			REQUEST_MODEL LSV2
			REQUEST_MODEL BALLAS1
			REQUEST_MODEL SFR1
			REQUEST_MODEL AK47
			REQUEST_MODEL IRGOGGLES
			LOAD_SPECIAL_CHARACTER 2 SMOKEV

			LOAD_ALL_MODELS_NOW
			
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 2
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED LSV2
				OR NOT HAS_MODEL_LOADED BALLAS1
				OR NOT HAS_MODEL_LOADED SFR1
				OR NOT HAS_MODEL_LOADED AK47
				OR NOT HAS_MODEL_LOADED IRGOGGLES
				WAIT 0
			ENDWHILE

			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 2549.918 -1284.457 1060.0165 big_smoke
			SET_CHAR_HEADING big_smoke 250.566
			SET_CHAR_DECISION_MAKER big_smoke carter_DM
			GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_AK47 999999
			SET_CHAR_ACCURACY big_smoke 70
			SET_CHAR_BULLETPROOF_VEST big_smoke TRUE
			SET_CHAR_HEALTH big_smoke 10000
			SET_CHAR_MAX_HEALTH big_smoke 2000//1000
			SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER big_smoke TRUE
			ADD_BLIP_FOR_CHAR big_smoke smoke_fablip
			SET_CHAR_HAS_USED_ENTRY_EXIT big_smoke 2540.83 -1304.05 1.0
	
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE big_smoke 2.0 
			SET_CHAR_AREA_VISIBLE big_smoke 2
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING smokehealth_fa COUNTER_DISPLAY_BAR RM4_39
			SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY big_smoke TRUE

			SET_CHAR_COORDINATES scplayer 2564.447 -1292.822 1060.016 //2565.355 -1292.374 1061.016 
			SET_CHAR_HEADING scplayer 58.5

			REMOVE_PICKUP armour1_fa
			REMOVE_PICKUP armour2_fa
			REMOVE_PICKUP armour3_fa
			REMOVE_PICKUP armour4_fa
			REMOVE_PICKUP health1_fa
			REMOVE_PICKUP health2_fa
			REMOVE_PICKUP health3_fa

			CREATE_PICKUP HEALTH PICKUP_ONCE 2582.848 -1282.741 1065.391 health1_fa
			CREATE_PICKUP HEALTH PICKUP_ONCE 2578.784 -1282.572 1065.40 health2_fa
			CREATE_PICKUP_WITH_AMMO IRGOGGLES PICKUP_ONCE 1 2575.03 -1281.598 1061.02 irgoggles_fa
			CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2549.429 -1291.612 1061.024 armour1_fa 
			CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET 60  2549.632 -1294.612 1061.024 m4pickup_fa
			CREATE_PICKUP_WITH_AMMO CHROMEGUN PICKUP_ON_STREET 60 2552.09 -1294.106 1061.024 shotgunpickup_fa

			LOAD_SCENE 2552.31 -1286.25 1061.04

			SET_TIME_OF_DAY 22 00

			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_PLAYER_CONTROL PLAYER1 ON
			progressaudio_faflag = 0
			handlingudio_faflag = 0
			bossbattleaudio_faflag = 1
			PRINT_HELP RM4_28 //Smoke is wearing a bullet proof vest to maximise the damage you inflict on him aim at other parts of his body.
			floor3_faflag = 10
			carter_faflag = 5
		ENDIF
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////		 Boss Battle with Smoke		 ///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF carter_faflag = 5

	IF NOT IS_CHAR_DEAD big_smoke

		IF bossbattle_faflag = 0
			TASK_KILL_CHAR_ON_FOOT big_smoke scplayer
			smoke_can_kill_player_fa = FALSE

			CREATE_CHAR	PEDTYPE_MISSION1 SFR1 2554.729 -1281.952 1060.024 gang5_fa
			SET_CHAR_HEADING gang5_fa 180.771
			SET_CHAR_DECISION_MAKER gang5_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang5_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang5_fa WEAPONSKILL_PRO
			TASK_TOGGLE_DUCK gang5_fa -1
			SET_CHAR_HEALTH gang5_fa 200
			SET_CHAR_MAX_HEALTH gang5_fa 200
			enemy_fa = gang5_fa
			enemyx_fa = 2554.46
			enemyy_fa = -1284.412
			enemyz_fa = 1061.024
			GOSUB runnostay_falabel
			gang5_faflag = 1

			CREATE_CHAR	PEDTYPE_MISSION1 BALLAS1 2551.236 -1292.18 1060.02 gang4_fa
			SET_CHAR_HEADING gang4_fa 6.0
			SET_CHAR_DECISION_MAKER gang4_fa carter_DM
			GIVE_WEAPON_TO_CHAR gang4_fa WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang4_fa WEAPONSKILL_PRO
			SET_CHAR_HEALTH gang4_fa 200
			SET_CHAR_MAX_HEALTH gang4_fa 200
			enemy_fa = gang4_fa
			enemyx_fa = 2551.259
			enemyy_fa = -1288.081
			enemyz_fa = 1061.02
			GOSUB runnostay_falabel
			gang4_faflag = 1

			TIMERA = 0
			TIMERB = 0
			bossbattle_faflag = 1
		ENDIF

		//spawning guys
		IF bossbattle_faflag = 1
			IF TIMERB > 6000
				
				//armoury
				IF gang1_faflag = 0
					IF NOT IS_POINT_ON_SCREEN 2551.2 -1291.727 1060.02 5.0
						CREATE_CHAR	PEDTYPE_MISSION1 BALLAS1 2551.2 -1291.727 1060.02 gang1_fa
						SET_CHAR_HEADING gang1_fa 0.0
						SET_CHAR_DECISION_MAKER gang1_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang1_fa WEAPONTYPE_MICRO_UZI 99999
						SET_CHAR_WEAPON_SKILL gang1_fa WEAPONSKILL_PRO
						TASK_KILL_CHAR_ON_FOOT gang1_fa scplayer
						SET_CHAR_ACCURACY gang1_fa 30
						SET_CHAR_HEALTH gang1_fa 200
						SET_CHAR_MAX_HEALTH gang1_fa 200
						gang1_faflag = 1
					ENDIF
				ENDIF

				//bathroom
				IF gang2_faflag = 0
					IF NOT IS_POINT_ON_SCREEN 2579.04 -1284.275 1064.4 5.0
						CREATE_CHAR	PEDTYPE_MISSION1 LSV2 2579.04 -1284.275 1064.4 gang2_fa
						SET_CHAR_HEADING gang2_fa 0.0
						SET_CHAR_DECISION_MAKER gang2_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang2_fa WEAPONTYPE_MICRO_UZI 99999
						SET_CHAR_WEAPON_SKILL gang2_fa WEAPONSKILL_PRO
						TASK_KILL_CHAR_ON_FOOT gang2_fa scplayer
						SET_CHAR_HEALTH gang2_fa 150
						SET_CHAR_MAX_HEALTH gang2_fa 150
						gang2_faflag = 1
					ENDIF
				ENDIF

				//door
				IF gang3_faflag = 0
					IF NOT IS_POINT_ON_SCREEN 2576.94 -1300.388 1060.016 1.0
						CREATE_CHAR PEDTYPE_MISSION1 SFR1 2576.94 -1300.388 1060.016 gang3_fa
						SET_CHAR_HEADING gang3_fa 0.0
						SET_CHAR_DECISION_MAKER gang3_fa carter_DM
						GIVE_WEAPON_TO_CHAR gang3_fa WEAPONTYPE_MICRO_UZI 99999
						SET_CHAR_WEAPON_SKILL gang3_fa WEAPONSKILL_PRO
						TASK_KILL_CHAR_ON_FOOT gang3_fa scplayer
						SET_CHAR_ACCURACY gang3_fa 30
						SET_CHAR_HEALTH gang3_fa 200
						SET_CHAR_MAX_HEALTH gang3_fa 200
						gang3_faflag = 1
					ENDIF
				ENDIF

				IF gang1_faflag = 1
				OR gang2_faflag = 1
				OR gang3_faflag = 1
					TIMERB = 0
					bossbattle_faflag = 2
				ENDIF
				
			ENDIF
		ENDIF

		IF bossbattle_faflag = 2
			//armoury
			IF gang1_faflag = 1
				IF IS_CHAR_DEAD gang1_fa
					MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fa	
					bossbattle_faflag = 1
					gang1_faflag = 0
				ENDIF
			ENDIF

			//bathroom
			IF gang2_faflag = 1
				IF IS_CHAR_DEAD gang2_fa
					MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fa
					bossbattle_faflag = 1
					gang2_faflag = 0
				ENDIF
			ENDIF

			//door
			IF gang3_faflag = 1
				IF IS_CHAR_DEAD gang3_fa
					MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fa
					bossbattle_faflag = 1
					gang3_faflag = 0
				ENDIF
			ENDIF

			IF gang4_faflag = 1
				IF IS_CHAR_DEAD gang4_fa
					MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fa
					//bossbattle_faflag = 1
					gang4_faflag = 0
				ENDIF
			ENDIF

			IF gang5_faflag = 1
				IF IS_CHAR_DEAD gang5_fa
					MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fa
					//bossbattle_faflag = 1
					gang5_faflag = 0
				ENDIF
			ENDIF


		ENDIF

		
		///////////////////////////////////////////////	check to stop ped already has this script command                
		IF smoke_can_kill_player_fa = FALSE
            GET_SCRIPT_TASK_STATUS big_smoke TASK_KILL_CHAR_ON_FOOT smoke_progress_fa
            IF smoke_progress_fa = FINISHED_TASK
            	smoke_can_kill_player_fa = TRUE
            ENDIF
		ENDIF
		///////////////////////////////////////////////////


		//start smoke
		IF smokerun_faflag = 0
			IF tellsmoke_faflag = 0
				IF TIMERA > 1000
					TIMERA = 10000
					tellsmoke_faflag = 1
					smokerun_faflag = 1
				ENDIF
			ENDIF
		ENDIF

		//up stairs
		IF smokerun_faflag = 1
			IF tellsmoke_faflag = 1
				IF TIMERA > 5999
					IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2564.242 -1282.221 1064.41 3.5 3.5 3.0 FALSE
						TASK_FOLLOW_PATH_NODES_TO_COORD big_smoke 2564.242 -1282.221 1064.41 PEDMOVE_RUN 20000
						tellsmoke_faflag = 2
					ELSE
						smokerun_faflag = 3	//at beginning runs to the kitchen
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D big_smoke scplayer 3.0 3.0 1.5 FALSE
						smokerun_faflag = 2	//at beginning runs to the kitchen
						TIMERA = 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF smokerun_faflag = 1
			IF LOCATE_CHAR_ANY_MEANS_3D big_smoke 2564.242 -1282.221 1064.41 3.5 3.5 3.0 FALSE
				IF smoke_can_kill_player_fa = TRUE
		            TASK_KILL_CHAR_ON_FOOT big_smoke scplayer
		            smoke_can_kill_player_fa = FALSE
				ENDIF
				TIMERA = 0
				tellsmoke_faflag = 1
				smokerun_faflag = 2
			ENDIF
		ENDIF

		//bottom of stairs
		IF smokerun_faflag = 2
			IF tellsmoke_faflag = 1
				IF TIMERA > 4999
					IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2575.35 -1295.873 1061.85 5.5 5.5 3.5 FALSE
						TASK_FOLLOW_PATH_NODES_TO_COORD big_smoke 2575.35 -1295.873 1061.85 PEDMOVE_RUN 20000
						tellsmoke_faflag = 2
					ELSE
						smokerun_faflag = 4 //at stairs runs back down
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D big_smoke scplayer 3.0 3.0 1.5 FALSE
						smokerun_faflag = 4 //at stairs runs back down
						TIMERA = 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF smokerun_faflag = 2
			IF LOCATE_CHAR_ANY_MEANS_3D big_smoke 2575.35 -1295.873 1061.85 3.5 3.5 3.0 FALSE	
				IF smoke_can_kill_player_fa = TRUE
		            TASK_KILL_CHAR_ON_FOOT big_smoke scplayer
		            smoke_can_kill_player_fa = FALSE
				ENDIF
				TIMERA = 0
				tellsmoke_faflag = 1
				smokerun_faflag = 3
			ENDIF
		ENDIF

		//kitchen
		IF smokerun_faflag = 3
			IF tellsmoke_faflag = 1
				IF TIMERA > 4999
					IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2558.181 -1303.748 1061.024 6.0 6.0 3.5 FALSE
						TASK_FOLLOW_PATH_NODES_TO_COORD big_smoke 2558.181 -1303.748 1061.024 PEDMOVE_RUN 200000
						tellsmoke_faflag = 2
					ELSE
						smokerun_faflag = 1 //at bottom stairs runs back up
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D big_smoke scplayer 3.0 3.0 1.5 FALSE
						smokerun_faflag = 1 //at stairs runs back down
						TIMERA = 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF smokerun_faflag = 3
			IF LOCATE_CHAR_ANY_MEANS_3D big_smoke 2558.181 -1303.748 1061.024 3.5 3.5 3.0 FALSE
				IF smoke_can_kill_player_fa = TRUE
		            TASK_KILL_CHAR_ON_FOOT big_smoke scplayer
		            smoke_can_kill_player_fa = FALSE
				ENDIF
				TIMERA = 0
				tellsmoke_faflag = 1
				smokerun_faflag = 4
			ENDIF
		ENDIF

		//near beginning
		IF smokerun_faflag = 4
			IF tellsmoke_faflag = 1
				IF TIMERA > 5999
					IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2553.233 -1282.609 1061.024 4.0 4.0 3.5 FALSE
						TASK_FOLLOW_PATH_NODES_TO_COORD big_smoke 2553.233 -1282.609 1061.024 PEDMOVE_RUN 20000
						tellsmoke_faflag = 2
					ELSE
						smokerun_faflag = 2 //at kitchen run to bottom of stairs
					ENDIF
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D big_smoke scplayer 3.0 3.0 1.5 FALSE
						smokerun_faflag = 2 //at stairs runs back down
						TIMERA = 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF smokerun_faflag = 4
			IF LOCATE_CHAR_ANY_MEANS_3D big_smoke 2553.233 -1282.609 1061.024 4.0 4.0 3.0 FALSE	
				IF smoke_can_kill_player_fa = TRUE
		            TASK_KILL_CHAR_ON_FOOT big_smoke scplayer
		            smoke_can_kill_player_fa = FALSE
				ENDIF
				TIMERA = 0
				tellsmoke_faflag = 1
				smokerun_faflag = 1
			ENDIF
		ENDIF

		

		IF NOT IS_CHAR_DEAD big_smoke
			GET_CHAR_HEALTH big_smoke smokehealth_fa
			smokehealth_fa = smokehealth_fa - 8000 //8000
			smokehealth_fa = smokehealth_fa / 20
			IF smokehealth_fa <= 0
				smokehealth_fa = 0
			ENDIF
			IF switch_faflag = 0
				IF smokehealth_fa < 80	
					lightsout_faflag = 1
					SET_DARKNESS_EFFECT TRUE 200
					switch_faflag = 1
				ENDIF
			ENDIF
		
		ENDIF

		   
		IF NOT IS_CHAR_DEAD big_smoke
			GET_CHAR_HEALTH big_smoke healthforsmoke_fa
			IF healthforsmoke_fa <= 8000//9100 //8000
				smokehealth_fa = 0 
				TASK_DIE big_smoke
				SET_DARKNESS_EFFECT FALSE -1
				SET_INFRARED_VISION FALSE
				CLEAR_ONSCREEN_COUNTER smokehealth_fa
				// CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS
				SET_PLAYER_CONTROL PLAYER1 OFF
				GET_CHAR_COORDINATES scplayer player_x player_y player_z
				CLEAR_AREA player_x player_y 100.0 100.0 TRUE
				IF NOT IS_CHAR_DEAD gang1_fa
					TASK_DIE gang1_fa
				ENDIF
				IF NOT IS_CHAR_DEAD gang2_fa
					TASK_DIE gang2_fa
				ENDIF
				IF NOT IS_CHAR_DEAD gang3_fa
					TASK_DIE gang3_fa
				ENDIF
				IF NOT IS_CHAR_DEAD	gang4_fa
					TASK_DIE gang4_fa
				ENDIF
				IF NOT IS_CHAR_DEAD	gang5_fa
					TASK_DIE gang5_fa
				ENDIF
				TIMERA = 0
				TIMERB = 0
				CLEAR_MISSION_AUDIO 1
				CLEAR_PRINTS
				bossbattleaudio_faflag = 2
				DO_FADE 2500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOTO mission_finaleA_passed	
				// CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS
				TIMERA = 0
			ENDIF
		ENDIF

		IF switch_faflag = 1

			IF irgoggles_faflag = 0
				IF HAS_PICKUP_BEEN_COLLECTED irgoggles_fa
					PRINT_HELP RM4_36 //~s~Use the thermal vision to spot ~r~Smoke ~s~and his goons easier.
					irgoggles_faflag = 3
				ELSE
					ADD_BLIP_FOR_PICKUP irgoggles_fa irgoggles_fablip
					SET_BLIP_ENTRY_EXIT irgoggles_fablip 2540.83 -1304.05 1.0
					PRINT_NOW RM4_31 7000 1 //~s~Smoke has switched the lights off go get the ~y~thermal goggles ~s~by the tv.
					irgoggles_faflag = 1
				ENDIF
			ENDIF

			IF irgoggles_faflag = 1
				IF HAS_PICKUP_BEEN_COLLECTED irgoggles_fa
					REMOVE_BLIP irgoggles_fablip
					PRINT_HELP RM4_36 //~s~Use the thermal vision to spot ~r~Smoke ~s~and his goons easier.
					irgoggles_faflag = 2
				ENDIF
			ENDIF

		ENDIF

		//audio
		IF bossbattleaudio_faflag = 1

			GOSUB process_audio_fa

			//play mission audio
			IF progressaudio_faflag = 0
				IF handlingudio_faflag = 0
					audio_label_fa = SOUND_ROT4_UA //You wearing body armour, Smoke?
					$input_text_fa = ROT4_UA //You wearing body armour, Smoke?
					GOSUB load_audio_fa
				ENDIF
			ENDIF
			IF progressaudio_faflag = 1
				IF handlingudio_faflag = 0
					audio_label_fa = SOUND_ROT4_UB //Man, I’m disappointed in you – thought you were gangsta!
					$input_text_fa = ROT4_UB //Man, I’m disappointed in you – thought you were gangsta!
					GOSUB load_audio_fa
				ENDIF
			ENDIF
			IF progressaudio_faflag = 2
				IF handlingudio_faflag = 0
					audio_label_fa = SOUND_ROT4_UC //Hey, I’m a motherfucking celebrity.
					$input_text_fa = ROT4_UC //Hey, I’m a motherfucking celebrity.
					GOSUB load_audio_fa
				ENDIF
			ENDIF
			IF progressaudio_faflag = 3
				IF handlingudio_faflag = 0
					audio_label_fa = SOUND_ROT4_UD //All kinds of crazy cats out there want a piece of me!
					$input_text_fa = ROT4_UD //All kinds of crazy cats out there want a piece of me!
					GOSUB load_audio_fa
				ENDIF
			ENDIF
			IF progressaudio_faflag = 4
				IF handlingudio_faflag = 0
					audio_label_fa = SOUND_ROT4_ZB //Somebody save the Smoke!
					$input_text_fa = ROT4_ZB //Somebody save the Smoke!
					GOSUB load_audio_fa
				ENDIF
			ENDIF
			IF lightsout_faflag = 1
				IF progressaudio_faflag = 5
					IF handlingudio_faflag = 0
						audio_label_fa = SOUND_ROT4_ZA	//Shoot 'em!  Help me!
						$input_text_fa = RM4_31	//Shoot 'em!  Help me!
						GOSUB load_audio_fa
					ENDIF
				ENDIF
			ENDIF

		 ENDIF

	ELSE
		smokehealth_fa = 0
		SET_DARKNESS_EFFECT FALSE -1
		SET_INFRARED_VISION FALSE
		CLEAR_ONSCREEN_COUNTER smokehealth_fa
		// CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THISCHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS CHANGE THIS
		SET_PLAYER_CONTROL PLAYER1 OFF
		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		CLEAR_AREA player_x player_y 100.0 100.0 TRUE
		IF NOT IS_CHAR_DEAD gang1_fa
			TASK_DIE gang1_fa
		ENDIF
		IF NOT IS_CHAR_DEAD gang2_fa
			TASK_DIE gang2_fa
		ENDIF
		IF NOT IS_CHAR_DEAD gang3_fa
			TASK_DIE gang3_fa
		ENDIF
		IF NOT IS_CHAR_DEAD	gang4_fa
			TASK_DIE gang4_fa
		ENDIF
		IF NOT IS_CHAR_DEAD	gang5_fa
			TASK_DIE gang5_fa
		ENDIF
		TIMERA = 0
		TIMERB = 0
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		bossbattleaudio_faflag = 2
		DO_FADE 2500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		GOTO mission_finaleA_passed				
	ENDIF


ENDIF



GOTO mission_finaleA_loop


load_audio_fa:
IF handlingudio_faflag = 0
	LOAD_MISSION_AUDIO 1 audio_label_fa
	$text_label_fa = $input_text_fa
	handlingudio_faflag = 1
ENDIF
RETURN

process_audio_fa:
IF handlingudio_faflag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_fa 4000 1 //Dummy message"
		PLAY_MISSION_AUDIO 1
		handlingudio_faflag = 2
	ENDIF
ENDIF
IF handlingudio_faflag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_faflag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_faflag = 0
	ENDIF
ENDIF
RETURN



runnostay_falabel:
OPEN_SEQUENCE_TASK runnostay_faseq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_fa enemyy_fa enemyz_fa PEDMOVE_RUN -1
//TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runnostay_faseq
PERFORM_SEQUENCE_TASK enemy_fa runnostay_faseq
CLEAR_SEQUENCE_TASK runnostay_faseq
RETURN																					   

stayshoot_falabel:
OPEN_SEQUENCE_TASK stayshoot_faseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshoot_faseq
PERFORM_SEQUENCE_TASK enemy_fa stayshoot_faseq
CLEAR_SEQUENCE_TASK stayshoot_faseq
RETURN

stayshootnoduck_falabel:
OPEN_SEQUENCE_TASK stayshootnoduck_faseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshootnoduck_faseq
PERFORM_SEQUENCE_TASK enemy_fa stayshootnoduck_faseq
CLEAR_SEQUENCE_TASK stayshootnoduck_faseq
RETURN

run2shoot_falabel:
OPEN_SEQUENCE_TASK run2shoot_faseq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_fa enemyy_fa enemyz_fa PEDMOVE_RUN -1
TASK_GO_STRAIGHT_TO_COORD -1 enemyx2_fa enemyy2_fa enemyz2_fa PEDMOVE_RUN -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK run2shoot_faseq
PERFORM_SEQUENCE_TASK enemy_fa run2shoot_faseq
CLEAR_SEQUENCE_TASK run2shoot_faseq
RETURN

rolloutr_falabel:
OPEN_SEQUENCE_TASK rolloutr_faseq
TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutr_faseq
PERFORM_SEQUENCE_TASK enemy_fa rolloutr_faseq
CLEAR_SEQUENCE_TASK rolloutr_faseq
RETURN

rolloutl_falabel:
OPEN_SEQUENCE_TASK rolloutl_faseq
TASK_PLAY_ANIM -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutl_faseq
PERFORM_SEQUENCE_TASK enemy_fa rolloutl_faseq
CLEAR_SEQUENCE_TASK rolloutl_faseq
RETURN

peekright_falabel:
OPEN_SEQUENCE_TASK peekright_faseq
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer shootimer_s4
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fa enemyy_fa enemyz_fa heading_fa -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekright_faseq 1
CLOSE_SEQUENCE_TASK peekright_faseq
PERFORM_SEQUENCE_TASK enemy_fa peekright_faseq
CLEAR_SEQUENCE_TASK peekright_faseq
RETURN

peekleft_falabel:
OPEN_SEQUENCE_TASK peekleft_faseq
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer shootimer_s4
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fa enemyy_fa enemyz_fa heading_fa -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekleft_faseq 1
CLOSE_SEQUENCE_TASK peekleft_faseq
PERFORM_SEQUENCE_TASK enemy_fa peekleft_faseq
CLEAR_SEQUENCE_TASK peekleft_faseq
RETURN

flee_falable:
OPEN_SEQUENCE_TASK flee_faseq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_fa enemyy_fa enemyz_fa PEDMOVE_SPRINT -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_DUCK -1 -1
CLOSE_SEQUENCE_TASK flee_faseq
PERFORM_SEQUENCE_TASK enemy_fa flee_faseq
CLEAR_SEQUENCE_TASK flee_faseq
RETURN	



// Mission finaleA failed
mission_finaleA_failed:
PRINT_BIG M_FAIL 5000 1
REMOVE_IPL_DISCREETLY carter
DELETE_OBJECT carterwall
CREATE_OBJECT_NO_OFFSET imy_shash_wall 2522.008 -1272.93 35.609 carterwall
DONT_REMOVE_OBJECT carterwall
SET_OBJECT_HEALTH carterwall 100
SET_OBJECT_COLLISION_DAMAGE_EFFECT carterwall FALSE
FREEZE_OBJECT_POSITION carterwall TRUE
//remember to warp player out if he is in that area
RETURN
   

// mission finaleA passed
mission_finaleA_passed:
finaleaplayerpass_flag = 1
flag_riot_mission_counter++
CLEAR_WANTED_LEVEL player1
REQUEST_IPL	carter
RETURN
		


// mission cleanup

mission_cleanup_finaleA:
CLEAR_ALL_SCRIPT_ROADBLOCKS

DISABLE_ALL_ENTRY_EXITS FALSE

IF NOT IS_CAR_DEAD sweet_car
	SET_VEHICLE_IS_CONSIDERED_BY_PLAYER sweet_car TRUE
ENDIF

//blips
REMOVE_BLIP sweet_fablip
REMOVE_BLIP swatvan_fablip
REMOVE_BLIP carter_fablip
REMOVE_BLIP smoke_fablip
REMOVE_BLIP irgoggles_fablip
//pickups
REMOVE_PICKUP armour1_fa
REMOVE_PICKUP armour2_fa
REMOVE_PICKUP armour3_fa
REMOVE_PICKUP armour4_fa
REMOVE_PICKUP health1_fa
REMOVE_PICKUP health2_fa
REMOVE_PICKUP health3_fa
REMOVE_PICKUP m4pickup_fa
REMOVE_PICKUP shotgunpickup_fa 
REMOVE_PICKUP irgoggles_fa
REMOVE_PICKUP shotgunpickup2_fa
//counters
CLEAR_ONSCREEN_COUNTER smokehealth_fa 
//fx
KILL_FX_SYSTEM wallsmashfx_fa
//objects
DELETE_OBJECT barrel1_fa
DELETE_OBJECT barrel2_fa
DELETE_OBJECT barrel3_fa
DELETE_OBJECT barrel4_fa
DELETE_OBJECT door_fa
DELETE_OBJECT wall_fa
//models and anims
MARK_MODEL_AS_NO_LONGER_NEEDED SWATVAN
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
UNLOAD_SPECIAL_CHARACTER 1
MARK_MODEL_AS_NO_LONGER_NEEDED BMX
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
REMOVE_ANIMATION CRACK
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
MARK_MODEL_AS_NO_LONGER_NEEDED SWAT
REMOVE_ANIMATION SWAT
MARK_MODEL_AS_NO_LONGER_NEEDED imcompmovedr1_las
REMOVE_CAR_RECORDING 287
MARK_MODEL_AS_NO_LONGER_NEEDED BMYCG
MARK_MODEL_AS_NO_LONGER_NEEDED HMYCM
MARK_MODEL_AS_NO_LONGER_NEEDED SFR1
MARK_MODEL_AS_NO_LONGER_NEEDED M4
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED BARREL4
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED MAFFA
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI
REMOVE_ANIMATION STRIP
MARK_MODEL_AS_NO_LONGER_NEEDED SBFYSTR 
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED IRGOGGLES
UNLOAD_SPECIAL_CHARACTER 2
//misc
SWITCH_EMERGENCY_SERVICES ON
SET_WANTED_MULTIPLIER 1.0
SET_MAX_WANTED_LEVEL 6
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
//nodes
SWITCH_ROADS_ON 2356.4 -1233.16 30.6 2382.19 -1329.65 21.49
SWITCH_PED_ROADS_ON 2356.4 -1233.16 30.6 2382.19 -1329.65 21.49
SWITCH_ROADS_ON 2522.74 -1260.01 30.28 2499.0 -1322.01 31.76
SWITCH_PED_ROADS_ON 2522.74 -1260.01 30.28 2499.0 -1322.01 31.76
SWITCH_ROADS_ON 2431.9 -1268.06 30.6 2474.9 -1240.38 20.84
SWITCH_PED_ROADS_ON 2431.9 -1268.06 30.6 2474.9 -1240.38 20.84
REMOVE_ALL_SCRIPT_FIRES
GET_GAME_TIMER timer_mobile_start

IF IS_PLAYER_PLAYING PLAYER1
	SET_PLAYER_GROUP_RECRUITMENT PLAYER1 TRUE
ENDIF

IF NOT IS_CAR_DEAD swatvan_fa
	SET_CAR_ONLY_DAMAGED_BY_PLAYER swatvan_fa FALSE
	SET_CAR_PROOFS swatvan_fa FALSE FALSE FALSE FALSE FALSE
ENDIF
IF NOT IS_CAR_DEAD sweet_car
	SET_CAR_PROOFS sweet_car FALSE FALSE FALSE FALSE FALSE
ENDIF
flag_player_on_mission = 0
MISSION_HAS_FINISHED
IF finaleaplayerpass_flag = 1
	DO_FADE 0 FADE_OUT
ENDIF
RETURN
		

}
