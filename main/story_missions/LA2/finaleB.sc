MISSION_START
// *****************************************************************************************
// ************************************* 	finaleB ****************************************
// ************************************* Carter Block **************************************
// *********** Inspired by Zelda, Metroid, MGS, Perfect Dark, Time Crisis, GTA *************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_finaleB

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_finaleB_failed
ENDIF

GOSUB mission_cleanup_finaleB

MISSION_END

{

// ********************************** Variables for shootout *********************************

LVAR_INT gang1_fb
LVAR_INT gang2_fb
LVAR_INT gang3_fb
LVAR_INT gang4_fb
LVAR_INT gang5_fb
LVAR_INT gang6_fb
LVAR_INT gang7_fb
LVAR_INT gang8_fb
LVAR_INT gang9_fb
LVAR_INT gang10_fb
LVAR_INT gang11_fb
LVAR_INT gang12_fb
LVAR_INT gang13_fb
LVAR_INT gang14_fb
LVAR_INT gang15_fb
LVAR_INT tenpenny_fb 
LVAR_INT playervisible_fb
VAR_FLOAT camx_fb 
VAR_FLOAT camy_fb 
VAR_FLOAT camz_fb 
VAR_FLOAT lookcamx_fb 
VAR_FLOAT lookcamy_fb 
VAR_FLOAT lookcamz_fb

//timer
VAR_INT escapetimer_fb

//particles & fire
LVAR_INT fxPlayerSmoke_fb[9]
LVAR_INT nLoop_fb
LVAR_INT fire1_fb
LVAR_INT fire2_fb
LVAR_INT fire3_fb
LVAR_INT fire4_fb
LVAR_INT fire5_fb
LVAR_INT fire6_fb
LVAR_INT fire7_fb
LVAR_INT fire8_fb
LVAR_INT fire9_fb
LVAR_INT fire10_fb
LVAR_INT fire11_fb
LVAR_INT fire12_fb
LVAR_INT fire13_fb
LVAR_INT fire14_fb
LVAR_INT fire15_fb
LVAR_INT fire16_fb
LVAR_INT fire17_fb
LVAR_INT fire18_fb
LVAR_INT fire19_fb
LVAR_INT fire20_fb
LVAR_INT fire21_fb
LVAR_INT fire22_fb
LVAR_INT fire23_fb
LVAR_INT fire24_fb
LVAR_INT fire25_fb
LVAR_INT fire26_fb
LVAR_INT fire27_fb
LVAR_INT fire28_fb
LVAR_INT fire29_fb
LVAR_INT fire30_fb
LVAR_INT fire31_fb
LVAR_INT fire32_fb
LVAR_INT fire33_fb
LVAR_INT fire34_fb
LVAR_INT fire35_fb
LVAR_INT fire36_fb

//sequences
LVAR_INT carterb_DM
LVAR_INT cartertoughb_DM
LVAR_INT runnostay_fbseq
LVAR_INT stayshoot_fbseq
LVAR_INT stayshootnoduck_fbseq
LVAR_INT rolloutr_fbseq
LVAR_INT peekright_fbseq
LVAR_INT enemy_fb
LVAR_INT peekleft_fbseq
LVAR_FLOAT enemyx_fb 
LVAR_FLOAT enemyy_fb 
LVAR_FLOAT enemyz_fb
LVAR_INT enemytarget_fb
LVAR_INT enemytarget2_fb
LVAR_INT run2shoot_fbseq
LVAR_INT shootimer_s4
LVAR_FLOAT enemyx2_fb 
LVAR_FLOAT enemyy2_fb 
LVAR_FLOAT enemyz2_fb
LVAR_FLOAT heading_fb
//blips
LVAR_INT sweet_fbblip
LVAR_INT carter_fbblip
LVAR_INT extinguisher1_fbblip

//pickups
LVAR_INT armour1_fb
LVAR_INT armour2_fb
LVAR_INT armour3_fb
LVAR_INT armour4_fb
LVAR_INT armour5_fb
LVAR_INT health1_fb
LVAR_INT health2_fb
LVAR_INT health3_fb
LVAR_INT nightvision_fb
LVAR_INT extinguisher1_fb
LVAR_INT extinguisher2_fb
LVAR_INT ak_fb

//flags
LVAR_INT finaleb_fbflag
LVAR_INT floorg_fbflag
LVAR_INT floor1_fbflag
LVAR_INT floor2_fbflag
LVAR_INT floor3_fbflag
LVAR_INT gang1_fbflag
LVAR_INT gang2_fbflag
LVAR_INT gang3_fbflag
LVAR_INT gang4_fbflag
LVAR_INT gang5_fbflag
LVAR_INT gang6_fbflag
LVAR_INT gang7_fbflag
LVAR_INT gang8_fbflag
LVAR_INT gang9_fbflag
LVAR_INT gang10_fbflag
LVAR_INT gang11_fbflag
LVAR_INT gang12_fbflag
LVAR_INT gang13_fbflag
LVAR_INT gang14_fbflag
LVAR_INT gang15_fbflag
LVAR_INT tenpennycut_fbflag 
LVAR_INT explosion_fbflag
LVAR_INT timerout_fbflag
LVAR_INT helptext_fbflag
LVAR_INT finalebplayerpass_flag
// ********************************** Variables for chase *********************************

//new variables
LVAR_INT firetruck_fb
LVAR_INT benz_fb
LVAR_INT cop1_fb
LVAR_INT cop2_fb
LVAR_INT cop3_fb 
LVAR_INT train_fb
LVAR_INT copladder_fb
LVAR_INT coptruck_fb
LVAR_INT gangcar1_fb
LVAR_INT gangcar2_fb
LVAR_INT gangcar3_fb
LVAR_INT gangcar4_fb
LVAR_INT gangcar5_fb


//sequences
LVAR_INT chasekill_fbseq

//flags
LVAR_INT sweethang_fbflag
LVAR_INT finalebchase_fbflag
LVAR_INT sweetlegsup_fbflag
LVAR_INT locatestage_fbflag 
LVAR_INT speedup_fbflag
LVAR_INT createtrain_fbflag
LVAR_INT copladder_fbflag
LVAR_INT coptruck_fbflag
LVAR_INT displaygripbar_fbflag
LVAR_INT cardensity_fbflag
LVAR_INT notincar_fbflag
LVAR_INT cardead_fbflag
LVAR_INT lostsweet_fbflag



// **************************************** Mission Start ************************************

mission_start_finaleB:

flag_player_on_mission = 1

SCRIPT_NAME finaleB

WAIT 0

LOAD_MISSION_TEXT RIOT4


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY carterb_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH cartertoughb_DM


/////////////////////////////////////////////////////// 
SET_MAX_WANTED_LEVEL 0
//REQUEST_IPL	carter
//DELETE_OBJECT carterwall
///////////////////////////////////////////////////////

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL PLAYER1 ON

//Smokes dying in Carter block 
SET_FADING_COLOUR 0 0 0
SET_AREA_VISIBLE 2
//DO_FADE 1000 FADE_OUT
//
//WHILE GET_FADING_STATUS
//            WAIT 0
//ENDWHILE

LOAD_CUTSCENE RIOT_4d
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE
 
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


REQUEST_MODEL BALLAS1
REQUEST_MODEL BALLAS3
REQUEST_MODEL LSV2
REQUEST_MODEL NVGOGGLES

REQUEST_MODEL CHROMEGUN
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL M4 
REQUEST_ANIMATION SWAT
REQUEST_MODEL FIRE_EX

LOAD_SPECIAL_CHARACTER 1 SMOKEV
LOAD_SPECIAL_CHARACTER 2 TENPEN

LOAD_MISSION_AUDIO 1 SOUND_ROT4_ZC

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
	OR NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED BALLAS1
	OR NOT HAS_MODEL_LOADED BALLAS3
	OR NOT HAS_MODEL_LOADED LSV2
	OR NOT HAS_MODEL_LOADED NVGOGGLES
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED CHROMEGUN
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	OR NOT HAS_MODEL_LOADED M4
	OR NOT HAS_ANIMATION_LOADED SWAT
	OR NOT HAS_MODEL_LOADED FIRE_EX
	WAIT 0
ENDWHILE

// **************************************** Declare Variables ************************************

//shootout 
finaleb_fbflag = 0
floorg_fbflag = 0
floor1_fbflag = 0
floor2_fbflag = 0
floor3_fbflag = 0
gang1_fbflag = 0
gang2_fbflag = 0
gang3_fbflag = 0
gang4_fbflag = 0
gang5_fbflag = 0
gang6_fbflag = 0
gang7_fbflag = 0
gang8_fbflag = 0
gang9_fbflag = 0
gang10_fbflag = 0
gang11_fbflag = 0
gang12_fbflag = 0
gang13_fbflag = 0
gang14_fbflag = 0
gang15_fbflag = 0
tenpennycut_fbflag =  0
explosion_fbflag = 0
timerout_fbflag = 0
helptext_fbflag = 0
escapetimer_fb = 420000
finalebplayerpass_flag = 0

//rails
sweethang_fbflag = 0
finalebchase_fbflag = 0
sweetlegsup_fbflag = 0
locatestage_fbflag = 0 
speedup_fbflag = 0
createtrain_fbflag = 0
copladder_fbflag = 0
coptruck_fbflag = 0
displaygripbar_fbflag = 0
cardensity_fbflag = 0
notincar_fbflag = 0
cardead_fbflag = 0
lostsweet_fbflag = 0



// **********************************************************************************************




SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0
SET_MAX_FIRE_GENERATIONS 0
SET_WANTED_MULTIPLIER 0.0
SWITCH_EMERGENCY_SERVICES OFF



mission_finaleB_loop:

WAIT 0


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_finaleb_passed
ENDIF

//player fails - if he runs out of time
IF finaleb_fbflag < 9
	IF timerout_fbflag = 0
		IF escapetimer_fb <= 0
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			ADD_EXPLOSION player_x player_y player_z EXPLOSION_GRENADE
			player_x = player_x + 0.0
			player_y = player_y	- 0.5
			player_z = player_z + 0.7
			ADD_EXPLOSION player_x player_y player_z EXPLOSION_ROCKET
			player_x = player_x - 1.0
			player_y = player_y	+ 2.0
			ADD_EXPLOSION player_x player_y player_z EXPLOSION_CAR
			player_x = player_x + 1.5
			player_y = player_y	+ 2.0
			ADD_EXPLOSION player_x player_y player_z EXPLOSION_GRENADE
			TIMERB = 0
			timerout_fbflag = 1
		ENDIF
	ENDIF
	IF timerout_fbflag = 1
		IF TIMERB > 3000
			TASK_DIE scplayer
			timerout_fbflag = 2
		ENDIF
	ENDIF
ENDIF

//play cut scene's
IF finaleb_fbflag = 0
	IF tenpennycut_fbflag = 0

		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2553.2 -1284.857 1060.0242 big_smoke
		SET_CHAR_HEADING big_smoke 90.0
		SET_CHAR_AREA_VISIBLE big_smoke 2
		TASK_DIE big_smoke
		CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2552.31 -1286.274 1061.024 armour5_fb
		CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ONCE 60 2552.36 -1283.15 1061.02 ak_fb

		SET_CHAR_COORDINATES scplayer 2553.734 -1285.256 1060.024
		SET_CHAR_HEADING scplayer 79.954
		SET_CHAR_AREA_VISIBLE scplayer 2

		SET_PLAYER_CONTROL PLAYER1 OFF
		SWITCH_WIDESCREEN ON

		SET_FIXED_CAMERA_POSITION 2550.6038 -1294.5857 1044.8022 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2551.6008 -1294.6489 1044.7625 JUMP_CUT
	
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 2556.9880 -1297.4994 1043.1305 tenpenny_fb
		SET_CHAR_HEADING tenpenny_fb 27.2743
	 	SET_CHAR_DECISION_MAKER tenpenny_fb carterb_DM
		GIVE_WEAPON_TO_CHAR tenpenny_fb WEAPONTYPE_SHOTGUN 99999
		LOAD_SCENE 2552.29 -1294.24 1044.84
		CLEAR_AREA 2552.38 -1294.06 1044.84 100.0 FALSE
		LOAD_SCENE 2552.29 -1294.24 1044.84
		SET_TIME_OF_DAY 00 00
		DO_FADE 500 FADE_IN

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		tenpennycut_fbflag = 1
	ENDIF
ENDIF

IF tenpennycut_fbflag = 1
	IF NOT IS_CHAR_DEAD tenpenny_fb
		TASK_SHOOT_AT_COORD tenpenny_fb 2552.29 -1294.24 1044.84 1000
		IF HAS_MISSION_AUDIO_LOADED 1
			PLAY_MISSION_AUDIO 1 
			PRINT_NOW ROT4_ZC 3500 1
		ENDIF
		WAIT 500
		START_SCRIPT_FIRE 2552.38 -1294.06 1044.84 0 1 fire1_fb //machine
		//////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////
		SKIP_CUTSCENE_START
		//////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////
		WAIT 200
		START_SCRIPT_FIRE 2553.33 -1293.89 1044.87 0 2 fire2_fb //machine
		WAIT 300
		IF NOT IS_CHAR_DEAD tenpenny_fb
			TASK_GO_STRAIGHT_TO_COORD tenpenny_fb 25566.84 -1297.272 1043.17 PEDMOVE_WALK -1
		ENDIF
		WAIT 2500
		SET_FIXED_CAMERA_POSITION 2565.8159 -1296.7323 1044.4026 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2564.8240 -1296.6128 1044.4437 JUMP_CUT
		PRINT_NOW RM4_38 10000 1 //~s~Tenpenny has caused an explosion, the lighting in the building is off and the whole place is beginning to burn down.  You need to get out of here quick!
		WAIT 3250
		ADD_EXPLOSION_VARIABLE_SHAKE 2553.19 -1293.58 1044.29 EXPLOSION_CAR	0.4
		WAIT 200
		ADD_EXPLOSION_VARIABLE_SHAKE 2558.53 -1293.88 1044.58 EXPLOSION_GRENADE 0.3
		WAIT 500
		ADD_EXPLOSION_VARIABLE_SHAKE 2552.78 -1293.24 1045.32 EXPLOSION_GRENADE 0.4
		SET_DARKNESS_EFFECT FALSE 175
		WAIT 100
		ADD_EXPLOSION_VARIABLE_SHAKE 2557.6 -1294.57 1043.49 EXPLOSION_ROCKET 0.5
		WAIT 500
		ADD_EXPLOSION_VARIABLE_SHAKE 2555.86 -1293.27 1043.91 EXPLOSION_ROCKET 0.6
		WAIT 50
		ADD_EXPLOSION_VARIABLE_SHAKE 2558.73 -1293.4 1044.36 EXPLOSION_CAR 0.6
		WAIT 50
		SET_TIME_OF_DAY 00 00
		SET_HEATHAZE_EFFECT TRUE
		SET_DARKNESS_EFFECT TRUE 100
		START_SCRIPT_FIRE 2567.77 -1294.03 1045.5 0 3 fire17_fb
		START_SCRIPT_FIRE 2541.687 -1289.68 1043.0 1 1 fire8_fb
		START_SCRIPT_FIRE 2559.45 -1303.65 1044.0 0 2 fire23_fb
		START_SCRIPT_FIRE 2564.72 -1296.06 1042.91 0 2 fire25_fb
		START_SCRIPT_FIRE 2570.115 -1301.434 1043.8 0 2 fire15_fb
		WAIT 250
		SET_DARKNESS_EFFECT FALSE 120
		START_SCRIPT_FIRE 2559.37 -1284.76 1044.1 0 2 fire21_fb
		WAIT 500
		SET_DARKNESS_EFFECT TRUE 140
		START_SCRIPT_FIRE 2538.751 -1291.7 1044.17 0 2 fire10_fb
		START_SCRIPT_FIRE 2541.56 -1292.35 1044.1 0 3 fire13_fb
		START_SCRIPT_FIRE 2551.47 -1297.2 1044.17 0 2 fire28_fb
		START_SCRIPT_FIRE 2551.425 -1298.4 1044.1 0 2 fire29_fb
		WAIT 400
		SET_DARKNESS_EFFECT FALSE 150
		WAIT 500
		START_SCRIPT_FIRE 2551.33 -1295.311 1043.8 0 3 fire34_fb
		START_SCRIPT_FIRE 2541.36 -1300.36 1044.1 0 2 fire31_fb
		SET_DARKNESS_EFFECT TRUE 180
		WAIT 3000
		tenpennycut_fbflag = 2
	ENDIF
ENDIF


IF tenpennycut_fbflag = 2

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	SKIP_CUTSCENE_END
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	CLEAR_THIS_PRINT ROT4_ZC
	CLEAR_MISSION_AUDIO 1

	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL PLAYER1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	DELETE_CHAR tenpenny_fb
	SET_HEATHAZE_EFFECT FALSE
	SET_DARKNESS_EFFECT TRUE 200
	SET_TIME_OF_DAY 00 00

	SET_PLAYER_MOOD PLAYER1 MOOD_PR 360000

	REMOVE_ALL_SCRIPT_FIRES
	CREATE_PICKUP HEALTH PICKUP_ONCE 2524.0 -1283.0 1048.337 health1_fb
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2527.7830 -1288.8158 1031.4297 armour1_fb
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2580.953 -1285.117 1044.173 armour2_fb
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2520.4 -1281.287 1054.681 armour3_fb
	CREATE_PICKUP HEALTH PICKUP_ONCE 2552.298 -1306.06 1054.681 health2_fb
	CREATE_PICKUP HEALTH PICKUP_ONCE 2570.839 -1285.818 1037.821 health3_fb
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2552.475 -1280.687 1054.686 armour4_fb
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_NIGHTVISION 1 
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_NIGHTVISION
	MARK_MODEL_AS_NO_LONGER_NEEDED NVGOGGLES
	PRINT_NOW RM4_33 10000 1 //~s~Use the night vision goggles you have just picked up from Big Smoke to help you see in the dark and ~y~escape ~s~the building as quick as possible.
	DISPLAY_ONSCREEN_TIMER_WITH_STRING escapetimer_fb TIMER_DOWN RM4_34
	SET_TIMER_BEEP_COUNTDOWN_TIME escapetimer_fb 32
	ADD_BLIP_FOR_COORD 2577.82 -1284.6 1053.99 carter_fbblip
	SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0
	TIMERA = 0
	finaleb_fbflag = 1

	tenpennycut_fbflag = 3
ENDIF

IF finaleb_fbflag < 3
	IF helptext_fbflag = 0
		IF TIMERA > 7000
			CLEAR_PRINTS
			PRINT_HELP RM4_32 
			helptext_fbflag = 1
		ENDIF
	ENDIF
ENDIF
IF finaleb_fbflag < 4
	IF helptext_fbflag = 2
		IF TIMERA > 8000
			CLEAR_PRINTS
			PRINT_HELP RM4_40 //Nightvision can brighten up fires
			helptext_fbflag = 3
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		3rd Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF finaleb_fbflag = 1
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2578.21 -1284.87 1053.95 5.0 5.0 5.0 FALSE
		REMOVE_BLIP carter_fbblip
		ADD_BLIP_FOR_COORD 2521.89 -1301.98 1047.54 carter_fbblip //2528.213 -1297.409 1048.3367
		SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0
		CLEAR_PRINTS
		//kill normally
		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2574.5 -1296.029 1054.687 gang1_fb
		SET_CHAR_HEADING gang1_fb 4.7018 
		SET_CHAR_DECISION_MAKER	gang1_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang1_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang1_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang1_fb 2
		SET_CHAR_ACCURACY gang1_fb 20
		SET_CHAR_HEALTH gang1_fb 150
		SET_CHAR_MAX_HEALTH gang1_fb 150
		gang1_fbflag = 1
		TASK_KILL_CHAR_ON_FOOT gang1_fb scplayer

		//2 peek shotgun
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2553.7642 -1294.5363 1053.6470 gang2_fb
		SET_CHAR_HEADING gang2_fb 266.9614
		SET_CHAR_DECISION_MAKER	gang2_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang2_fb WEAPONTYPE_SHOTGUN 99999
		SET_CHAR_AREA_VISIBLE gang2_fb 2
		gang2_fbflag = 0
		IF gang2_fbflag = 0
			IF NOT IS_CHAR_DEAD gang2_fb
				enemy_fb = gang2_fb
				enemyx_fb = 2553.7642
				enemyy_fb = -1294.5363
				enemyz_fb = 1053.6470
				shootimer_s4 = 2700
				heading_fb = 270.0
				GOSUB peekright_fblabel
				gang2_fbflag = 1
			ENDIF
		ENDIF

		//kill normally
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2575.266 -1296.4 1054.687 gang3_fb
		SET_CHAR_HEADING gang3_fb 89.298
		SET_CHAR_DECISION_MAKER	gang3_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang3_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang3_fb WEAPONSKILL_PRO
		SET_CHAR_HEALTH gang3_fb 150
		SET_CHAR_MAX_HEALTH gang3_fb 150
		SET_CHAR_AREA_VISIBLE gang3_fb 2
		IF gang3_fbflag = 0
			IF NOT IS_CHAR_DEAD gang3_fb
				enemy_fb = gang3_fb
				enemyx_fb = 2556.3096
				enemyy_fb = -1297.2192
				enemyz_fb = 1053.6406
				GOSUB runnostay_fblabel
				gang3_fbflag = 1
			ENDIF
		ENDIF
 		
		//stay in same place
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2547.426 -1306.462 1054.681 gang4_fb
		SET_CHAR_HEADING gang4_fb 359.0
		SET_CHAR_DECISION_MAKER	gang4_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang4_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang4_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang4_fb 2
		gang4_fbflag = 0
		IF gang4_fbflag = 0
			IF NOT IS_CHAR_DEAD gang4_fb
				enemy_fb = gang4_fb
				GOSUB stayshoot_fblabel
				gang4_fbflag = 1
			ENDIF
		ENDIF

		//stay in same place
		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2534.0447 -1304.8566 1053.6406 gang5_fb
		SET_CHAR_HEADING gang5_fb 273.9959 
		SET_CHAR_DECISION_MAKER	gang5_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang5_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang5_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang5_fb 2
		SET_CHAR_HEALTH gang5_fb 150
		SET_CHAR_MAX_HEALTH gang5_fb 150
		gang5_fbflag = 0
		IF gang5_fbflag = 0
			IF NOT IS_CHAR_DEAD gang5_fb
				enemy_fb = gang5_fb
				GOSUB stayshoot_fblabel
				gang5_fbflag = 1
			ENDIF
		ENDIF

		//stay in same place
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2540.1919 -1287.6666 1053.6470 gang6_fb
		SET_CHAR_HEADING gang6_fb 165.9748
		SET_CHAR_DECISION_MAKER	gang6_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang6_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang6_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang6_fb 2
		SET_CHAR_ALLOWED_TO_DUCK gang6_fb FALSE
		gang6_fbflag = 0
		IF gang6_fbflag = 0
			IF NOT IS_CHAR_DEAD gang6_fb
				enemy_fb = gang6_fb
				GOSUB stayshootnoduck_fblabel
				gang6_fbflag = 1
			ENDIF
		ENDIF
		//body armour 
		//2521.159 -1281.722 1054.68

		finaleb_fbflag = 2
	ENDIF
ENDIF

IF finaleb_fbflag = 2

	//mark as no longer needed
	IF gang1_fbflag = 1
		IF IS_CHAR_DEAD gang1_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fb
			gang1_fbflag = 2
		ENDIF
	ENDIF

	IF gang2_fbflag = 1
		IF NOT IS_CHAR_DEAD gang2_fb
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang2_fb scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang2_fb scplayer 5.0 5.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang2_fb
					TASK_KILL_CHAR_ON_FOOT gang2_fb scplayer
					gang2_fbflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
					gang2_fbflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF gang2_fbflag = 1
	OR gang2_fbflag = 2
		IF IS_CHAR_DEAD gang2_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
			gang2_fbflag = 3
		ENDIF
	ENDIF

	IF gang3_fbflag = 1
		IF IS_CHAR_DEAD gang3_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fb
			gang3_fbflag = 2
		ENDIF
	ENDIF
	IF gang4_fbflag = 1
		IF IS_CHAR_DEAD gang4_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fb
			gang4_fbflag = 2
		ENDIF
	ENDIF
	IF gang5_fbflag = 1
		IF IS_CHAR_DEAD gang5_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fb
			gang5_fbflag = 2
		ENDIF
	ENDIF
	IF gang6_fbflag = 1
		IF IS_CHAR_DEAD gang6_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fb
			gang6_fbflag = 2
		ENDIF
	ENDIF

	IF gang7_fbflag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2543.46 -1304.9 1054.68 5.0 5.0 5.0 FALSE
			//kill normally
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2532.4 -1286.7 1053.68 gang7_fb
			SET_CHAR_HEADING gang7_fb 230.835
			SET_CHAR_DECISION_MAKER	gang7_fb carterb_DM
			GIVE_WEAPON_TO_CHAR gang7_fb WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_WEAPON_SKILL gang7_fb WEAPONSKILL_PRO
			SET_CHAR_AREA_VISIBLE gang7_fb 2
			TASK_KILL_CHAR_ON_FOOT gang7_fb scplayer
			gang7_fbflag = 1
		ENDIF
	ENDIF
	IF gang7_fbflag = 1
		IF IS_CHAR_DEAD gang7_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fb
			gang7_fbflag = 2
		ENDIF
	ENDIF

	IF gang8_fbflag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2519.93 -1285.547 1054.673 5.0 5.0 5.0 FALSE
			//kill normally
			CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2519.93 -1285.54 1054.673 gang8_fb
			SET_CHAR_HEADING gang8_fb 200.537
			SET_CHAR_DECISION_MAKER	gang8_fb carterb_DM
			GIVE_WEAPON_TO_CHAR gang8_fb WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_AREA_VISIBLE gang8_fb 2
			SET_CHAR_HEALTH gang8_fb 150
			SET_CHAR_MAX_HEALTH gang8_fb 150
			enemy_fb = gang8_fb
			enemyx_fb = 2520.05
			enemyy_fb = -1301.518
			enemyz_fb = 1048.33
			GOSUB runnostay_fblabel
			gang8_fbflag = 1
		ENDIF
	ENDIF
	IF gang8_fbflag = 1
		IF IS_CHAR_DEAD gang8_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fb
			gang8_fbflag = 2
		ENDIF
	ENDIF

ENDIF
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		2nd Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF finaleb_fbflag = 2
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2521.9 -1301.86 1047.5 6.0 6.0 3.0 FALSE
		PRINT_NOW RM4_35 7000 1 //~s~This floor is on fire use a ~g~fire extinguisher ~s~to help you get out!
		TIMERA = 0
		helptext_fbflag = 2

		DELETE_CHAR gang1_fb
		DELETE_CHAR gang2_fb
		DELETE_CHAR gang3_fb
		DELETE_CHAR gang4_fb
		DELETE_CHAR gang5_fb
		DELETE_CHAR gang6_fb

		REMOVE_BLIP carter_fbblip
		ADD_BLIP_FOR_COORD 2572.08 -1301.88 1043.32 carter_fbblip
		SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0
		SET_HEATHAZE_EFFECT TRUE

		DELETE_CHAR big_smoke
		UNLOAD_SPECIAL_CHARACTER 2

		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 0.0 2.2 TRUE fxPlayerSmoke_fb[0] //directly above	  //all z had 0.2 added to them apart from behind
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 2.0 2.0 TRUE fxPlayerSmoke_fb[1] //in front
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 -2.0 2.0 TRUE fxPlayerSmoke_fb[2] //behind
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 1.0 5.0 1.7 TRUE fxPlayerSmoke_fb[3] //5m in front
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -1.0 8.0 2.0 TRUE fxPlayerSmoke_fb[4] //8m in front
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 2.0 0.0 1.7 TRUE fxPlayerSmoke_fb[5] //side
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -2.0 0.0 1.7 TRUE fxPlayerSmoke_fb[6] //side
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -2.0 2.0 1.7 TRUE fxPlayerSmoke_fb[7] //front diag
		CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 2.0 2.0 1.7 TRUE fxPlayerSmoke_fb[8] //front diag

		nLoop_fb = 0
		WHILE nLoop_fb < 9
			PLAY_FX_SYSTEM fxPlayerSmoke_fb[nLoop_fb]
			nLoop_fb ++	
		ENDWHILE
					
		//small room
		START_SCRIPT_FIRE 2529.62 -1306.62 1048.0 0 2 fire1_fb //infront of door
		START_SCRIPT_FIRE 2529.497 -1304.791 1048.0 0 2 fire2_fb //infront of door
		START_SCRIPT_FIRE 2530.12 -1296.96 1047.8 0 3 fire3_fb //left of building
//		START_SCRIPT_FIRE 2529.89 -1299.77 1048.0 0 2 fire19_fb
		START_SCRIPT_FIRE 2529.49 -1287.93 1048.0 0 3 fire4_fb //guy putting out
		//ped @ 2528.6 -1290.47 1047.337 h344.715

	
		//walk way
		START_SCRIPT_FIRE 2543.98 -1305.709 1047.445 0 3 fire4_fb //1st one that player comes across
		START_SCRIPT_FIRE 2544.54 -1304.47 1047.55 0 3 fire5_fb
		START_SCRIPT_FIRE 2543.94 -1306.83 1047.62 0 3 fire30_fb
		START_SCRIPT_FIRE 2545.139 -1281.497 1047.5 0 2 fire6_fb //3rd that player comes across
		
		START_SCRIPT_FIRE 2567.77 -1294.03 1047.5 0 3 fire17_fb
		START_SCRIPT_FIRE 2570.2 -1294.4 1047.5 0 3 fire18_fb
		START_SCRIPT_FIRE 2568.72 -1294.32 1047.5 0 2 fire15_fb
		//left when you come down
		START_SCRIPT_FIRE 2540.247 -1288.566 1044.0 0 3 fire7_fb
		START_SCRIPT_FIRE 2541.687 -1289.68 1044.0 1 1 fire8_fb
		START_SCRIPT_FIRE 2540.214 -1290.218 1044.0 0 2 fire9_fb
		START_SCRIPT_FIRE 2538.751 -1291.7 1044.17 0 2 fire10_fb
		START_SCRIPT_FIRE 2537.339 -1286.56 1044.1 1 3 fire11_fb

		START_SCRIPT_FIRE 2541.56 -1292.35 1044.1 0 3 fire13_fb

		//table ends
		//desks
		START_SCRIPT_FIRE 2570.77 -1303.14 1043.35 0 2 fire20_fb
		START_SCRIPT_FIRE 2559.37 -1284.76 1044.1 0 2 fire21_fb
		START_SCRIPT_FIRE 2551.4 -1301.3 1044.0 0 2 fire22_fb
		START_SCRIPT_FIRE 2570.52 -1301.42 1043.47 0 2 fire23_fb
		START_SCRIPT_FIRE 2550.94 -1301.81 1044.0 0 3 fire24_fb
		
		//right
		START_SCRIPT_FIRE 2537.69 -1301.34 1044.0 0 2 fire25_fb
		START_SCRIPT_FIRE 2536.42 -1302.01 1044.0 0 2 fire26_fb
		START_SCRIPT_FIRE 2536.82 -1290.5 1044.0 0 2 fire27_fb
		START_SCRIPT_FIRE 2551.47 -1297.2 1044.17 0 2 fire28_fb
		START_SCRIPT_FIRE 2570.54 -1301.22 1043.36 0 3 fire29_fb
		
		START_SCRIPT_FIRE 2551.33 -1295.311 1043.8 0 3 fire34_fb
		//pillars
		START_SCRIPT_FIRE 2541.36 -1300.36 1044.1 0 2 fire31_fb
		START_SCRIPT_FIRE 2545.328 -1300.739 1044.1 0 2 fire32_fb
		START_SCRIPT_FIRE 2548.77 -1300.375 1044.04 0 3 fire33_fb
		START_SCRIPT_FIRE 2544.16 -1306.56 1043.8 0 2 fire16_fb

		START_SCRIPT_FIRE 2570.43 -1302.43 1043.4 0 1 fire12_fb
		START_SCRIPT_FIRE 2549.05 -1287.54 1043.8 0 3 fire14_fb

		START_SCRIPT_FIRE 2570.482 -1302.438 1043.8 0 2 fire35_fb

		START_SCRIPT_FIRE 2565.705 -1298.03 1043.7 0 3 fire36_fb

		START_SCRIPT_FIRE 2565.528 -1289.17 1043.7 0 3 fire19_fb



		//fire extinguisher
		CREATE_PICKUP_WITH_AMMO FIRE_EX PICKUP_ONCE 99999 2529.829 -1303.027 1048.337 extinguisher1_fb
		ADD_BLIP_FOR_PICKUP extinguisher1_fb extinguisher1_fbblip
		SET_BLIP_ENTRY_EXIT extinguisher1_fbblip 2540.83 -1304.05 1.0

		CREATE_PICKUP_WITH_AMMO FIRE_EX PICKUP_ONCE 99999 2567.948 -1292.879 1044.173 extinguisher2_fb

		//guy putting out fire
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2528.539 -1289.935 1048.336 gang9_fb
		SET_CHAR_HEADING gang9_fb 359.0
		SET_CHAR_HEALTH gang9_fb 10
		SET_CHAR_DECISION_MAKER	gang9_fb cartertoughb_DM
		GIVE_WEAPON_TO_CHAR gang9_fb WEAPONTYPE_EXTINGUISHER 99999
		SET_CHAR_SHOOT_RATE gang9_fb 20
		SET_CHAR_AREA_VISIBLE gang9_fb 2
		SET_CHAR_HEALTH gang9_fb 5
		SET_CHAR_MAX_HEALTH gang9_fb 5
		SET_CHAR_ACCURACY gang9_fb 20
		gang9_fbflag = 1
		TASK_SHOOT_AT_COORD gang9_fb 2529.49 -1287.93 1048.0 650000

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2538.2744 -1293.3219 1043.1328 gang10_fb
		SET_CHAR_HEADING gang10_fb 351.1779
		SET_CHAR_HEALTH gang10_fb 5
		SET_CHAR_DECISION_MAKER	gang10_fb cartertoughb_DM
		GIVE_WEAPON_TO_CHAR gang10_fb WEAPONTYPE_EXTINGUISHER 99999
		SET_CHAR_SHOOT_RATE gang10_fb 20
		SET_CHAR_AREA_VISIBLE gang10_fb 2
		SET_CHAR_ACCURACY gang10_fb 20
		gang10_fbflag = 1
		TASK_SHOOT_AT_COORD gang10_fb 2538.5688 -1291.4803 1043.6042 650000

		//stay in same place
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2539.5339 -1297.7778 1043.1305 gang11_fb
		SET_CHAR_HEADING gang11_fb 43.6277 
		SET_CHAR_DECISION_MAKER	gang11_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang11_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang11_fb 2
		SET_CHAR_ACCURACY gang11_fb 20
		gang11_fbflag = 0
		IF gang11_fbflag = 0
			IF NOT IS_CHAR_DEAD gang11_fb
				enemy_fb = gang11_fb
				GOSUB stayshootnoduck_fblabel
				gang11_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2548.2446 -1294.1420 1043.1305 gang12_fb
		SET_CHAR_HEADING gang12_fb 105.3104
		SET_CHAR_DECISION_MAKER	gang12_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang12_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang12_fb 2
		SET_CHAR_HEALTH gang12_fb 150
		SET_CHAR_MAX_HEALTH gang12_fb 150
		gang12_fbflag = 0
		IF gang12_fbflag = 0
			IF NOT IS_CHAR_DEAD gang12_fb
				enemy_fb = gang12_fb
				GOSUB stayshoot_fblabel
				gang12_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2558.4517 -1289.5217 1043.1328 gang13_fb
		SET_CHAR_HEADING gang13_fb 91.8235
		SET_CHAR_DECISION_MAKER	gang13_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang13_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang13_fb 2
		SET_CHAR_ACCURACY gang13_fb 20
		gang13_fbflag = 0
		IF gang13_fbflag = 0
			IF NOT IS_CHAR_DEAD gang13_fb
				enemy_fb = gang13_fb
				GOSUB stayshootnoduck_fblabel
				gang13_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2565.7219 -1293.1332 1044.0703 gang14_fb
		SET_CHAR_HEADING gang14_fb 85.0916
		SET_CHAR_DECISION_MAKER	gang14_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang14_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang14_fb 2
		SET_CHAR_HEALTH gang14_fb 150
		SET_CHAR_MAX_HEALTH gang14_fb 150
		gang14_fbflag = 0
		IF gang14_fbflag = 0
			IF NOT IS_CHAR_DEAD gang14_fb
				enemy_fb = gang14_fb
				GOSUB stayshoot_fblabel
				gang14_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2557.1443 -1297.8053 1043.1305 gang15_fb
		SET_CHAR_HEADING gang15_fb 65.8333
		SET_CHAR_DECISION_MAKER	gang15_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang15_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang15_fb 2
		gang15_fbflag = 0
		IF gang15_fbflag = 0
			IF NOT IS_CHAR_DEAD gang15_fb
				enemy_fb = gang15_fb
				GOSUB stayshootnoduck_fblabel
				gang15_fbflag = 1
			ENDIF
		ENDIF

		//mark as no longer needed
		IF gang9_fbflag = 1
			IF IS_CHAR_DEAD gang9_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang9_fb
				gang9_fbflag = 2
			ENDIF
		ENDIF
		IF gang10_fbflag = 1
			IF IS_CHAR_DEAD gang10_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fb
				gang10_fbflag = 2
			ENDIF
		ENDIF
		IF gang11_fbflag = 1
			IF IS_CHAR_DEAD gang11_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fb
				gang11_fbflag = 2
			ENDIF
		ENDIF
		IF gang12_fbflag = 1
			IF IS_CHAR_DEAD gang12_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fb
				gang12_fbflag = 2
			ENDIF
		ENDIF
		IF gang13_fbflag = 1
			IF IS_CHAR_DEAD gang13_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fb
				gang13_fbflag = 2
			ENDIF
		ENDIF
		IF gang14_fbflag = 1
			IF IS_CHAR_DEAD gang14_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fb
				gang14_fbflag = 2
			ENDIF
		ENDIF
		IF gang15_fbflag = 1
			IF IS_CHAR_DEAD gang15_fb
				MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fb
				gang15_fbflag = 2
			ENDIF
		ENDIF

		finaleb_fbflag = 3
	ENDIF
ENDIF





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		1st Floor	////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////		Corridor	////////////////////////////////////////////////////////


IF finaleb_fbflag = 3
	IF IS_CHAR_IN_AREA_3D scplayer 2571.39 -1308.59 1041.4 2584.08 -1272.85 1047.02 FALSE
		MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
		REMOVE_BLIP extinguisher1_fbblip
		REMOVE_BLIP carter_fbblip
		ADD_BLIP_FOR_COORD 2565.56 -1301.7 1030.97 carter_fbblip 
		SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0
		REMOVE_BLIP extinguisher1_fbblip
		REMOVE_ALL_SCRIPT_FIRES
		DELETE_CHAR gang7_fb
		DELETE_CHAR gang8_fb
		MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX
		START_SCRIPT_FIRE 2575.7742 -1283.0548 1042.1328 0 3 fire1_fb
		START_SCRIPT_FIRE 2581.7437 -1297.5173 1039.0199 0 1 fire2_fb 
		START_SCRIPT_FIRE 2570.4136 -1300.0470 1036.7812 0 3 fire3_fb
		START_SCRIPT_FIRE 2568.7798 -1299.9869 1036.7812 0 3 fire4_fb
		START_SCRIPT_FIRE 2577.2471 -1294.8145 1034.0480 0 1 fire5_fb
		START_SCRIPT_FIRE 2566.8335 -1282.4888 1030.4297 0 3 fire6_fb
		START_SCRIPT_FIRE 2567.9844 -1284.9739 1030.4297 0 2 fire7_fb
		START_SCRIPT_FIRE 2572.4019 -1304.5807 1030.4297 0 2 fire8_fb
		START_SCRIPT_FIRE 2570.7896 -1305.2994 1030.4297 0 1 fire9_fb

		//main room
		START_SCRIPT_FIRE 2556.55 -1301.88 1029.82 0 3 fire10_fb
		START_SCRIPT_FIRE 2556.83 -1303.91 1029.42 0 2 fire11_fb
		START_SCRIPT_FIRE 2556.01 -1305.31 1029.82 0 3 fire12_fb
		START_SCRIPT_FIRE 2553.16 -1297.94 1029.42 0 2 fire13_fb
		START_SCRIPT_FIRE 2552.43 -1297.31 1029.82 0 2 fire14_fb
		START_SCRIPT_FIRE 2552.15 -1294.97 1029.42 0 3 fire15_fb
		START_SCRIPT_FIRE 2547.77 -1285.41 1029.82 0 3 fire16_fb
		START_SCRIPT_FIRE 2547.40 -1284.33 1029.42 0 1 fire17_fb
		START_SCRIPT_FIRE 2547.77 -1282.32 1029.82 0 3 fire18_fb
		START_SCRIPT_FIRE 2545.93 -1292.47 1029.42 0 3 fire19_fb
		START_SCRIPT_FIRE 2545.48 -1290.81 1029.82 0 1 fire20_fb
		START_SCRIPT_FIRE 2544.11 -1292.71 1029.42 0 2 fire21_fb
		START_SCRIPT_FIRE 2539.75 -1299.17 1029.82 0 1 fire22_fb
		START_SCRIPT_FIRE 2539.25 -1297.76 1029.42 0 2 fire23_fb
		START_SCRIPT_FIRE 2540.58 -1280.24 1036.78 0 2 fire24_fb
		START_SCRIPT_FIRE 2540.40 -1281.22 1036.78 0 1 fire25_fb
		START_SCRIPT_FIRE 2529.85 -1294.27 1036.8 0 2 fire26_fb
		START_SCRIPT_FIRE 2545.85 -1307.04 1036.78 0 2 fire27_fb
		START_SCRIPT_FIRE 2547.47 -1306.59 1036.78 0 1 fire28_fb

		//gang1
		//gang1
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2579.9053 -1291.1630 1043.1250 gang1_fb
		SET_CHAR_HEADING gang1_fb 177.191
		SET_CHAR_DECISION_MAKER	gang1_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang1_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang1_fb 2
		gang1_fbflag = 0

		//
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2567.4128 -1305.3245 1036.7812 gang2_fb
		SET_CHAR_HEADING gang2_fb 268.0134 
		SET_CHAR_DECISION_MAKER	gang2_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang2_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang2_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang2_fb 2
		SET_CHAR_HEALTH gang2_fb 150
		SET_CHAR_MAX_HEALTH gang2_fb 150
		gang2_fbflag = 0

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2572.1150 -1301.5042 1036.7812 gang3_fb
		SET_CHAR_HEADING gang3_fb 61.2775
		SET_CHAR_DECISION_MAKER	gang3_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang3_fb WEAPONTYPE_EXTINGUISHER 99999
		SET_CHAR_SHOOT_RATE gang3_fb 20
		SET_CHAR_AREA_VISIBLE gang3_fb 2
		SET_CHAR_HEALTH gang3_fb 5
		TASK_SHOOT_AT_COORD gang3_fb 2570.2073 -1300.2378 1037.059 650000
		gang3_fbflag = 0

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2575.0068 -1284.8375 1030.4274 gang4_fb
		SET_CHAR_HEADING gang4_fb 170.5391
		SET_CHAR_DECISION_MAKER	gang4_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang4_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang4_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang4_fb 2
		gang4_fbflag = 0
		IF gang4_fbflag = 0
			IF NOT IS_CHAR_DEAD gang4_fb
				enemy_fb = gang4_fb
				GOSUB stayshootnoduck_fblabel
				gang4_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2568.0752 -1293.6281 1030.4297 gang5_fb
		SET_CHAR_HEADING gang5_fb 356.7112
		SET_CHAR_DECISION_MAKER	gang5_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang5_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang5_fb 2
		SET_CHAR_HEALTH gang5_fb 150
		SET_CHAR_MAX_HEALTH gang5_fb 150
		gang5_fbflag = 0
		IF gang5_fbflag = 0
			IF NOT IS_CHAR_DEAD gang5_fb
				enemy_fb = gang5_fb
				GOSUB stayshoot_fblabel
				gang5_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2570.6257 -1297.4996 1030.4274 gang6_fb
		SET_CHAR_HEADING gang6_fb 3.5755
		SET_CHAR_DECISION_MAKER	gang6_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang6_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang6_fb 2
		gang6_fbflag = 0
		IF gang6_fbflag = 0
			IF NOT IS_CHAR_DEAD gang6_fb
				enemy_fb = gang6_fb
				GOSUB stayshoot_fblabel
				gang6_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2570.9470 -1301.7192 1030.4297 gang7_fb
		SET_CHAR_HEADING gang7_fb 359.8018
		SET_CHAR_DECISION_MAKER	gang7_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang7_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang7_fb 2
		gang7_fbflag = 0
		IF gang7_fbflag = 0
			IF NOT IS_CHAR_DEAD gang7_fb
				enemy_fb = gang7_fb
				GOSUB stayshoot_fblabel
				gang7_fbflag = 1
			ENDIF
		ENDIF

		finaleb_fbflag = 4
	ENDIF
ENDIF

IF finaleb_fbflag = 4

	IF gang1_fbflag = 0
		IF NOT IS_CHAR_DEAD gang1_fb
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2577.6 -1289.91 1043.77 5.0 5.0 3.0 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang1_fb scplayer
				enemy_fb = gang1_fb
				enemyx_fb = 2579.9053 
				enemyy_fb = -1304.9850
				enemyz_fb = 1036.7734
				GOSUB runnostay_fblabel
				gang1_fbflag = 1
			ENDIF
		ENDIF
	ENDIF

	IF gang2_fbflag = 0
		IF NOT IS_CHAR_DEAD gang2_fb
			IF IS_CHAR_IN_AREA_3D scplayer 2577.88 -1309.87 1036.2 2563.87 -1289.87 1042.68 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang2_fb scplayer
				TASK_KILL_CHAR_ON_FOOT gang2_fb scplayer
				gang2_fbflag = 1
			ENDIF
		ENDIF
	ENDIF

	IF gang7_fbflag = 1
		IF NOT IS_CHAR_DEAD gang7_fb
			IF IS_CHAR_IN_AREA_3D scplayer 2573.3 -1281.03 1028.8 2563.7 -1300.61 1034.0  FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang7_fb scplayer
				enemy_fb = gang7_fb
				enemyx_fb = 2570.9470
				enemyy_fb = -1301.7192
				enemyz_fb = 1030.4297
				enemyx2_fb = 2558.7222
				enemyy2_fb = -1301.8295
				enemyz2_fb = 1030.4297
				GOSUB run2shoot_fblabel
				gang7_fbflag = 2
			ENDIF
		ENDIF
	ENDIF

	//mark as no longer needed
	IF gang1_fbflag = 1
		IF IS_CHAR_DEAD gang1_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fb
			gang1_fbflag = 2
		ENDIF
	ENDIF
	IF gang2_fbflag = 1
		IF IS_CHAR_DEAD gang2_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
			gang2_fbflag = 2
		ENDIF
	ENDIF
	IF gang3_fbflag = 1
		IF IS_CHAR_DEAD gang3_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fb
			gang3_fbflag = 2
		ENDIF
	ENDIF
	IF gang4_fbflag = 1
		IF IS_CHAR_DEAD gang4_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fb
			gang4_fbflag = 2
		ENDIF
	ENDIF
	IF gang5_fbflag = 1
		IF IS_CHAR_DEAD gang5_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fb
			gang5_fbflag = 2
		ENDIF
	ENDIF
	IF gang6_fbflag = 1
		IF IS_CHAR_DEAD gang6_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fb
			gang6_fbflag = 2
		ENDIF
	ENDIF
	IF gang7_fbflag = 1
	OR gang7_fbflag = 1
		IF IS_CHAR_DEAD gang7_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fb
			gang7_fbflag = 2
		ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2565.94 -1301.96 1030.77 8.0 8.0 3.0 FALSE

		REMOVE_BLIP carter_fbblip
		ADD_BLIP_FOR_COORD 2528.78 -1293.94 1030.86 carter_fbblip
		SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0

		DELETE_CHAR gang9_fb
		DELETE_CHAR gang10_fb
		DELETE_CHAR gang11_fb
		DELETE_CHAR gang12_fb
		DELETE_CHAR gang13_fb
		DELETE_CHAR gang14_fb
		DELETE_CHAR gang15_fb

		//locate 2553.85 -1308.35 1028.6 2540.27 -1275.04 1033.2
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2549.162 -1291.369 1030.467  gang10_fb
		SET_CHAR_HEADING gang10_fb 275.0
		SET_CHAR_DECISION_MAKER	gang10_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang10_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang10_fb 2
		SET_CHAR_HEALTH gang10_fb 150
		SET_CHAR_MAX_HEALTH gang10_fb 150
		gang10_fbflag = 0
		IF gang10_fbflag = 0
			IF NOT IS_CHAR_DEAD gang10_fb
				enemy_fb = gang10_fb
				GOSUB stayshoot_fblabel
				gang10_fbflag = 1
			ENDIF
		ENDIF

		//locate 2542.24 -1308.67 1028.09 2527.86 -1276.9 1033.19
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2534.88 -1292.009 1031.469 gang11_fb
		SET_CHAR_HEADING gang11_fb 275.0
		SET_CHAR_DECISION_MAKER	gang11_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang11_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang11_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang11_fb 2
		gang11_fbflag = 0
		IF gang11_fbflag = 0
			IF NOT IS_CHAR_DEAD gang11_fb
				enemy_fb = gang11_fb
				GOSUB stayshootnoduck_fblabel
				gang11_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2554.0334 -1287.0798 1030.4297 gang12_fb
		SET_CHAR_HEADING gang12_fb 270.0
		SET_CHAR_DECISION_MAKER	gang12_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang12_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang12_fb 2
		gang12_fbflag = 0
		IF gang12_fbflag = 0
			IF NOT IS_CHAR_DEAD gang12_fb
				enemy_fb = gang12_fb
				enemyx_fb = 2554.0334
				enemyy_fb = -1287.0798
				enemyz_fb = 1030.4297
				shootimer_s4 = 1200
				heading_fb = 270.0
				GOSUB peekleft_fblabel
				gang12_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2546.0071 -1287.1501 1030.4274 gang13_fb
		SET_CHAR_HEADING gang13_fb 270.0
		SET_CHAR_DECISION_MAKER	gang13_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang13_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang13_fb 2
		gang13_fbflag = 0
		IF gang13_fbflag = 0
			IF NOT IS_CHAR_DEAD gang13_fb
				enemy_fb = gang13_fb
				enemyx_fb = 2546.0071
				enemyy_fb = -1287.1501
				enemyz_fb = 1030.4274
				shootimer_s4 = 2000
				heading_fb = 270.0
				GOSUB peekleft_fblabel
				gang13_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2530.3970 -1291.3810 1036.7812 gang14_fb
		SET_CHAR_HEADING gang14_fb 278.2231
		SET_CHAR_DECISION_MAKER	gang14_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang14_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang14_fb 2
		gang14_fbflag = 0
		IF gang14_fbflag = 0
			IF NOT IS_CHAR_DEAD gang14_fb
				enemy_fb = gang14_fb
				GOSUB stayshootnoduck_fblabel
				gang14_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2540.4434 -1306.6213 1036.7812 gang15_fb
		SET_CHAR_HEADING gang15_fb 328.6817
		SET_CHAR_DECISION_MAKER	gang15_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang15_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang15_fb 2
		gang15_fbflag = 0
		IF gang15_fbflag = 0
			IF NOT IS_CHAR_DEAD gang15_fb
				enemy_fb = gang15_fb
				GOSUB stayshootnoduck_fblabel
				gang15_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2535.1111 -1304.9923 1030.4297 gang9_fb
		SET_CHAR_HEADING gang9_fb 328.6817
		SET_CHAR_DECISION_MAKER	gang9_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang9_fb WEAPONTYPE_M4 99999
		SET_CHAR_AREA_VISIBLE gang9_fb 2
		TASK_KILL_CHAR_ON_FOOT gang9_fb scplayer
		gang9_fbflag = 0

		finaleb_fbflag = 5
	ENDIF

ENDIF
		
//////////////////////////////////////////////////		Main room	////////////////////////////////////////////////////////

IF finaleb_fbflag = 5

	IF gang10_fbflag = 1
		IF NOT IS_CHAR_DEAD gang10_fb
			IF IS_CHAR_IN_AREA_3D scplayer 2553.85 -1308.35 1028.6 2540.27 -1275.04 1033.2 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang10_fb scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 gang10_fb
				TASK_KILL_CHAR_ON_FOOT gang10_fb scplayer
				gang10_fbflag = 2
			ENDIF
		ENDIF
	ENDIF

	IF gang11_fbflag = 1
		IF NOT IS_CHAR_DEAD gang11_fb
			IF IS_CHAR_IN_AREA_3D scplayer 2542.24 -1308.67 1028.09 2527.86 -1276.9 1033.19 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang11_fb scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 gang11_fb
				TASK_KILL_CHAR_ON_FOOT gang11_fb scplayer
				gang11_fbflag = 2
			ENDIF
		ENDIF
	ENDIF

	//mark as no longer needed
	IF gang10_fbflag = 1
	OR gang9_fbflag = 2
		IF IS_CHAR_DEAD gang10_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fb
			gang10_fbflag = 3
		ENDIF
	ENDIF
	IF gang11_fbflag = 1
	OR gang9_fbflag = 2
		IF IS_CHAR_DEAD gang11_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fb
			gang11_fbflag = 3
		ENDIF
	ENDIF

	IF gang12_fbflag = 1
		IF NOT IS_CHAR_DEAD gang12_fb
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang12_fb scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang12_fb scplayer 5.0 5.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang12_fb
					TASK_KILL_CHAR_ON_FOOT gang12_fb scplayer
					gang12_fbflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fb
					gang12_fbflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF	
	IF gang12_fbflag = 1
	OR gang12_fbflag = 2
		IF IS_CHAR_DEAD gang12_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fb
			gang12_fbflag = 3
		ENDIF
	ENDIF

	IF gang13_fbflag = 1
		IF NOT IS_CHAR_DEAD gang13_fb
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang13_fb scplayer
			OR LOCATE_CHAR_ANY_MEANS_CHAR_3D gang13_fb scplayer 5.0 5.0 3.0 FALSE
				IF NOT IS_CHAR_DEAD gang13_fb
					TASK_KILL_CHAR_ON_FOOT gang13_fb scplayer
					gang13_fbflag = 2
				ELSE
					MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fb
					gang13_fbflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF gang13_fbflag = 1
	OR gang13_fbflag = 2
		IF IS_CHAR_DEAD gang13_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fb
			gang13_fbflag = 3
		ENDIF
	ENDIF

	IF gang14_fbflag = 1
		IF IS_CHAR_DEAD gang14_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fb
			gang14_fbflag = 2
		ENDIF
	ENDIF
	IF gang15_fbflag = 1
		IF IS_CHAR_DEAD gang15_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fb
			gang15_fbflag = 2
		ENDIF
	ENDIF
	IF gang9_fbflag = 1
		IF IS_CHAR_DEAD gang9_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang9_fb
			gang9_fbflag = 2
		ENDIF
	ENDIF
	
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2528.53 -1293.96 1030.86 8.0 8.0 4.0 FALSE

		DELETE_CHAR gang1_fb
		DELETE_CHAR gang2_fb
		DELETE_CHAR gang3_fb
		DELETE_CHAR gang4_fb
		DELETE_CHAR gang5_fb
		DELETE_CHAR gang6_fb
		DELETE_CHAR gang7_fb

		REMOVE_SCRIPT_FIRE fire1_fb
		REMOVE_SCRIPT_FIRE fire2_fb 
		REMOVE_SCRIPT_FIRE fire3_fb
		REMOVE_SCRIPT_FIRE fire4_fb
		REMOVE_SCRIPT_FIRE fire5_fb
		REMOVE_SCRIPT_FIRE fire6_fb
		REMOVE_SCRIPT_FIRE fire7_fb
		REMOVE_SCRIPT_FIRE fire8_fb
		REMOVE_SCRIPT_FIRE fire9_fb
		REMOVE_SCRIPT_FIRE fire10_fb
		REMOVE_SCRIPT_FIRE fire11_fb
		REMOVE_SCRIPT_FIRE fire12_fb
		REMOVE_SCRIPT_FIRE fire13_fb
		REMOVE_SCRIPT_FIRE fire14_fb
		REMOVE_SCRIPT_FIRE fire16_fb
		REMOVE_SCRIPT_FIRE fire18_fb
		REMOVE_SCRIPT_FIRE fire22_fb
		REMOVE_SCRIPT_FIRE fire24_fb
		REMOVE_SCRIPT_FIRE fire25_fb
		REMOVE_SCRIPT_FIRE fire26_fb

		START_SCRIPT_FIRE 2523.92 -1299.21 1030.78 0 3 fire1_fb 
		START_SCRIPT_FIRE 2523.66 -1297.4 1030.8 0 1 fire2_fb
		START_SCRIPT_FIRE 2528.97 -1304.32 1030.77 0 2 fire3_fb
		START_SCRIPT_FIRE 2525.5 -1321.59 1030.75 0 2 fire4_fb
		START_SCRIPT_FIRE 2541.0 -1311.02 1028.09 0 2 fire6_fb

		REMOVE_PICKUP extinguisher2_fb
		CREATE_PICKUP_WITH_AMMO FIRE_EX PICKUP_ONCE 99999 2534.036 -1321.212 1031.469 extinguisher2_fb
		//corridor
		START_SCRIPT_FIRE 2540.69 -1320.01 1030.68 0 3 fire7_fb
		START_SCRIPT_FIRE 2540.2 -1316.76 1030.64 0 3 fire8_fb
		START_SCRIPT_FIRE 2540.61 -1317.43 1030.62 0 3 fire9_fb
		START_SCRIPT_FIRE 2540.35 -1318.39 1030.59 0 3 fire10_fb
		START_SCRIPT_FIRE 2540.5 -1319.58 1030.61 0 3 fire11_fb
		
		REMOVE_BLIP carter_fbblip
		ADD_BLIP_FOR_COORD 2540.92 -1304.14 1024.39 carter_fbblip
		SET_BLIP_ENTRY_EXIT carter_fbblip 2540.83 -1304.05 1.0

		//gang1
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2527.3677 -1319.5874 1030.4297 gang1_fb
		SET_CHAR_HEADING gang1_fb 1.4222
		SET_CHAR_DECISION_MAKER	gang1_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang1_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang1_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang1_fb 2
		SET_CHAR_ACCURACY gang1_fb 30
		gang1_fbflag = 0

		//
		CREATE_CHAR PEDTYPE_MISSION1 BALLAS3 2528.2844 -1314.8649 1030.4297 gang2_fb
		SET_CHAR_HEADING gang2_fb 3.4628
		SET_CHAR_DECISION_MAKER	gang2_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang2_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_WEAPON_SKILL gang2_fb WEAPONSKILL_PRO
		SET_CHAR_AREA_VISIBLE gang2_fb 2
		gang2_fbflag = 0
		IF gang2_fbflag = 0
			IF NOT IS_CHAR_DEAD gang2_fb
				enemy_fb = gang2_fb
				GOSUB stayshoot_fblabel
				gang2_fbflag = 1
			ENDIF
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 LSV2 2524.0547 -1309.5328 1030.4297 gang3_fb
		SET_CHAR_HEADING gang3_fb 7.0010
		SET_CHAR_DECISION_MAKER	gang3_fb carterb_DM
		GIVE_WEAPON_TO_CHAR gang3_fb WEAPONTYPE_MICRO_UZI 99999
		SET_CHAR_AREA_VISIBLE gang3_fb 2
		SET_CHAR_ACCURACY gang3_fb 30
		gang3_fbflag = 0

	
		finaleb_fbflag = 6
	ENDIF

ENDIF



IF finaleb_fbflag = 6

	IF gang1_fbflag = 0
		IF NOT IS_CHAR_DEAD gang1_fb
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2527.07 -1293.72 1029.5 3.0 3.0 3.5 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang1_fb scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 gang1_fb
				TASK_KILL_CHAR_ON_FOOT gang1_fb scplayer
				gang1_fbflag = 1
			ENDIF
		ENDIF
	ENDIF

	IF gang3_fbflag = 0
		IF NOT IS_CHAR_DEAD gang3_fb
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer gang3_fb 7.0 7.0 3.5 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR gang3_fb scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 gang3_fb
				enemy_fb = gang3_fb
				GOSUB rolloutr_fblabel
				gang3_fbflag = 1
			ENDIF
		ENDIF
	ENDIF
			
	IF gang1_fbflag = 1
		IF IS_CHAR_DEAD gang1_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fb
			gang1_fbflag = 2
		ENDIF
	ENDIF
	IF gang2_fbflag = 1
		IF IS_CHAR_DEAD gang2_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
			gang2_fbflag = 2
		ENDIF
	ENDIF
	IF gang3_fbflag = 1
		IF IS_CHAR_DEAD gang3_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fb
			gang3_fbflag = 2
		ENDIF
	ENDIF

	GET_CHAR_AREA_VISIBLE scplayer playervisible_fb
	
	IF playervisible_fb = 0
		finaleb_fbflag = 7
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////		Ground Floor 	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF finaleb_fbflag = 7

	SET_PED_DENSITY_MULTIPLIER 0.0
	SET_CAR_DENSITY_MULTIPLIER 0.0
	CLEAR_AREA 2506.2175 -1293.0929 200.0 200.0 FALSE 

	REQUEST_ANIMATION DAM_JUMP
	REQUEST_ANIMATION FINALE

	LOAD_ALL_MODELS_NOW
	
	WHILE NOT HAS_ANIMATION_LOADED DAM_JUMP
		OR NOT HAS_ANIMATION_LOADED FINALE
		WAIT 0 
	ENDWHILE

	REMOVE_BLIP carter_fbblip
	ADD_BLIP_FOR_COORD 2522.82 -1272.52 34.151 carter_fbblip
	SET_CHAR_COORDINATES scplayer 2538.657 -1304.358 34.0
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	REMOVE_ANIMATION SWAT
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED M4 
	MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX

	DELETE_CHAR gang4_fb
	DELETE_CHAR	gang5_fb
	DELETE_CHAR	gang6_fb
	DELETE_CHAR	gang7_fb
	DELETE_CHAR	gang8_fb
	DELETE_CHAR	gang9_fb
	DELETE_CHAR	gang10_fb
	DELETE_CHAR	gang11_fb
	DELETE_CHAR	gang12_fb
	DELETE_CHAR	gang13_fb
	DELETE_CHAR	gang14_fb
	DELETE_CHAR	gang15_fb
	REMOVE_PICKUP extinguisher1_fb
	REMOVE_PICKUP extinguisher2_fb
	REMOVE_PICKUP health1_fb
	REMOVE_PICKUP armour1_fb
	REMOVE_PICKUP armour2_fb
	REMOVE_PICKUP armour3_fb
	REMOVE_PICKUP health2_fb
	REMOVE_PICKUP armour4_fb
	MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fb
	MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
	MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fb

	CREATE_CHAR PEDTYPE_MISSION1 LSV2 2543.2000 -1277.6451 33.9609 gang1_fb
	SET_CHAR_HEADING gang1_fb 7.0010
	SET_CHAR_DECISION_MAKER	gang1_fb carterb_DM
	GIVE_WEAPON_TO_CHAR gang1_fb WEAPONTYPE_MICRO_UZI 99999
	SET_CHAR_WEAPON_SKILL gang1_fb WEAPONSKILL_PRO
	TASK_KILL_CHAR_ON_FOOT gang1_fb scplayer
	SET_CHAR_ACCURACY gang1_fb 10
	gang1_fbflag = 1

	CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2536.9165 -1283.4459 33.9609 gang2_fb
	SET_CHAR_HEADING gang2_fb 7.0010
	SET_CHAR_DECISION_MAKER	gang2_fb carterb_DM
	GIVE_WEAPON_TO_CHAR gang2_fb WEAPONTYPE_M4 99999
	SET_CHAR_ACCURACY gang2_fb 10
	gang2_fbflag = 0
	IF gang2_fbflag = 0
		IF NOT IS_CHAR_DEAD gang2_fb
			enemy_fb = gang2_fb
			GOSUB stayshootnoduck_fblabel
			gang2_fbflag = 1
		ENDIF
	ENDIF

	CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2531.6565 -1287.2700 33.9602 gang3_fb
	SET_CHAR_HEADING gang3_fb 7.0010
	SET_CHAR_DECISION_MAKER	gang3_fb carterb_DM
	GIVE_WEAPON_TO_CHAR gang3_fb WEAPONTYPE_MICRO_UZI 99999
	SET_CHAR_WEAPON_SKILL gang3_fb WEAPONSKILL_PRO
	SET_CHAR_ACCURACY gang3_fb 10
	gang3_fbflag = 0
	IF gang3_fbflag = 0
		IF NOT IS_CHAR_DEAD gang3_fb
			enemy_fb = gang3_fb
			GOSUB stayshoot_fblabel
			gang3_fbflag = 1
		ENDIF
	ENDIF

	CREATE_CHAR PEDTYPE_MISSION1 LSV2 2539.5686 -1268.4714 33.9609 gang4_fb
	SET_CHAR_HEADING gang4_fb 2.21
	SET_CHAR_DECISION_MAKER	gang4_fb carterb_DM
	GIVE_WEAPON_TO_CHAR gang4_fb WEAPONTYPE_M4 99999
	TASK_KILL_CHAR_ON_FOOT gang4_fb scplayer
	SET_CHAR_ACCURACY gang4_fb 20
	gang4_fbflag = 1

	REMOVE_ALL_SCRIPT_FIRES

	START_SCRIPT_FIRE 2538.45 -1281.4 34.34 0 3 fire19_fb
	START_SCRIPT_FIRE 2527.64 -1312.0 33.9 0 2 fire1_fb
	START_SCRIPT_FIRE 2527.58 -1303.3 34.4 0 1 fire2_fb 
	START_SCRIPT_FIRE 2532.52 -1306.39 33.9 0 2 fire3_fb
	START_SCRIPT_FIRE 2528.08 -1284.9 34.3 0 2 fire4_fb
	START_SCRIPT_FIRE 2544.03 -1269.3 34.2 0 1 fire5_fb
//	START_SCRIPT_FIRE 2535.05 -1276.7 34.1 0 2 fire5_fb
	START_SCRIPT_FIRE 2536.26 -1302.49 34.0 0 2 fire6_fb
	START_SCRIPT_FIRE 2532.58 -1291.07 34.0 0 1 fire7_fb
//	START_SCRIPT_FIRE 2544.09 -1285.29 33.88 0 3 fire8_fb
	START_SCRIPT_FIRE 2528.64 -1269.64 34.0 0 1 fire11_fb
	START_SCRIPT_FIRE 2534.57 -1284.5 34.04 0 1 fire12_fb
//	START_SCRIPT_FIRE 2544.21 -1295.652 34.0 0 3 fire13_fb

	START_SCRIPT_FIRE 2531.99 -1268.71 34.0 0 2 fire10_fb
	START_SCRIPT_FIRE 2530.987 -1269.858 33.8 0 3 fire9_fb
	START_SCRIPT_FIRE 2532.39 -1270.4 34.0 0 3 fire14_fb
	START_SCRIPT_FIRE 2531.165 -1271.961 34.0 0 2 fire15_fb
	START_SCRIPT_FIRE 2532.529 -1272.169 34.0 0 1 fire16_fb
	START_SCRIPT_FIRE 2532.395 -1273.24 34.0 0 2 fire17_fb
	START_SCRIPT_FIRE 2531.94 -1274.46 33.8 0 3 fire18_fb



	finaleb_fbflag = 8
ENDIF

IF finaleb_fbflag = 8

	//mark as no longer needed
	IF gang1_fbflag = 1
		IF IS_CHAR_DEAD gang1_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fb
			gang1_fbflag = 2
		ENDIF
	ENDIF
	IF gang2_fbflag = 1
		IF IS_CHAR_DEAD gang2_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fb
			gang2_fbflag = 2
		ENDIF
	ENDIF
	IF gang3_fbflag = 1
		IF IS_CHAR_DEAD gang3_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fb
			gang3_fbflag = 2
		ENDIF
	ENDIF
	IF gang4_fbflag = 1
		IF IS_CHAR_DEAD gang4_fb
			MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fb
			gang4_fbflag = 2
		ENDIF
	ENDIF

	IF IS_CHAR_IN_AREA_3D scplayer 2545.59 -1278.26 31.36 2516.65 -1259.84 37.0 FALSE
		REMOVE_ALL_SCRIPT_FIRES
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		nLoop_fb = 0
		WHILE nLoop_fb < 9
			KILL_FX_SYSTEM fxPlayerSmoke_fb[nLoop_fb]
			nLoop_fb ++	
		ENDWHILE
		finaleb_fbflag = 9
	ENDIF
	
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	  Exit Cutscene    /////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF finaleb_fbflag = 9
	REMOVE_BLIP carter_fbblip
	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL PLAYER1 OFF

	SET_DARKNESS_EFFECT FALSE -1
	SET_NIGHT_VISION FALSE
	PLAYER_TAKE_OFF_GOGGLES PLAYER1 FALSE

	START_SCRIPT_FIRE 2532.62 -1272.65 34.31 0 1 fire1_fb
	START_SCRIPT_FIRE 2532.88 -1272.75 34.12 0 2 fire2_fb 
	START_SCRIPT_FIRE 2527.9 -1272.5 34.8 0 2 fire4_fb
	START_SCRIPT_FIRE 2533.743 -1272.434 34.0 0 3 fire5_fb
	START_SCRIPT_FIRE 2533.743 -1272.434 34.0 0 2 fire6_fb
	START_SCRIPT_FIRE 2531.99 -1268.71 34.0 0 2 fire10_fb
	START_SCRIPT_FIRE 2530.987 -1269.858 33.8 0 3 fire9_fb
	START_SCRIPT_FIRE 2532.39 -1270.4 34.0 0 1 fire14_fb
	START_SCRIPT_FIRE 2531.165 -1271.961 34.0 0 2 fire15_fb
	START_SCRIPT_FIRE 2532.529 -1272.169 34.0 0 1 fire16_fb
	START_SCRIPT_FIRE 2532.395 -1273.24 34.0 0 3 fire17_fb
	START_SCRIPT_FIRE 2531.94 -1274.46 33.8 0 1 fire13_fb
	START_SCRIPT_FIRE 2532.433 -1275.658 34.0 0 3 fire3_fb
	SET_MAX_FIRE_GENERATIONS 3
	camx_fb = 2526.9885
	camy_fb = -1272.5771
	camz_fb = 34.8419
	lookcamx_fb = 2527.9875
	lookcamy_fb = -1272.5565
	lookcamz_fb	= 34.8799
	
	DELETE_CHAR gang1_fb
	DELETE_CHAR gang2_fb
	DELETE_CHAR gang3_fb
	DELETE_CHAR gang4_fb
	DELETE_CHAR gang5_fb

	SET_CHAR_COORDINATES scplayer 2538.45 -1281.4 34.34
	SET_CHAR_HEADING scplayer 270.0 //76.23

	SET_FIXED_CAMERA_POSITION camx_fb camy_fb camz_fb 0.0 0.0 0.0 	//2517.78 1272.76
	POINT_CAMERA_AT_POINT lookcamx_fb lookcamy_fb lookcamz_fb JUMP_CUT
	WAIT 2000

	SET_CHAR_COORDINATES scplayer 2533.743 -1272.434 34.0
	SET_CHAR_HEADING scplayer 90.0 //76.23

	WAIT 500

	//TASK_GO_STRAIGHT_TO_COORD scplayer 2508.62 -1272.47 34.938 PEDMOVE_SPRINT -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Jump_Roll DAM_JUMP 8.0 FALSE FALSE FALSE TRUE -1

	//VAR_INT camtime

	SET_TIME_OF_DAY 17 00
	TIMERA = 0

	SET_TIME_SCALE 0.3
	finaleb_fbflag = 10
ENDIF

//IF finaleb_fbflag > 9
//	camtime = TIMERA
//	VIEW_INTEGER_VARIABLE camtime camtime
//ENDIF


IF finaleb_fbflag = 10

	SET_FIXED_CAMERA_POSITION camx_fb camy_fb camz_fb 0.0 0.0 0.0 	//2517.78 1272.76
	POINT_CAMERA_AT_POINT lookcamx_fb lookcamy_fb lookcamz_fb JUMP_CUT


	IF camx_fb <= 2512.28 //LOCATE_CHAR_ANY_MEANS_2D scplayer 2515.62 -1272.47 2.0 2.0 FALSE
		TIMERA = 0
		explosion_fbflag = 12
		finaleb_fbflag = 11
	ENDIF


	IF TIMERA > 500
//		camx_fb = camx_fb - 0.27 //31
		camx_fb -=@ 0.18 //15 //11
	ENDIF
	IF TIMERA > 0	
		IF explosion_fbflag = 0
			ADD_EXPLOSION_VARIABLE_SHAKE 2538.4 -1278.4 34.04 EXPLOSION_CAR 0.2 //0
			explosion_fbflag = 1
		ENDIF
	ENDIF
	IF TIMERA > 100
		IF explosion_fbflag = 1
			//ADD_EXPLOSION_VARIABLE_SHAKE 2540.36 -1275.04 34.34 EXPLOSION_GRENADE //100
			explosion_fbflag = 2
		ENDIF
	ENDIF
	IF TIMERA > 250
		IF explosion_fbflag = 2
			ADD_EXPLOSION_VARIABLE_SHAKE 2540.24 -1270.59 34.07 EXPLOSION_GRENADE 0.2 //250
			REMOVE_SCRIPT_FIRE fire4_fb
			REMOVE_SCRIPT_FIRE fire1_fb
			explosion_fbflag = 3
		ENDIF
	ENDIF
	IF TIMERA > 500
		IF explosion_fbflag = 3
			ADD_EXPLOSION_VARIABLE_SHAKE 2535.66 -1276.3 34.34 EXPLOSION_ROCKET 0.2 //500
			explosion_fbflag = 4
		ENDIF
	ENDIF
	IF TIMERA > 850
		IF explosion_fbflag = 4
			ADD_EXPLOSION_VARIABLE_SHAKE 2531.74 -1270.35 34.39 EXPLOSION_MOLOTOV 0.2 //500
			REMOVE_SCRIPT_FIRE fire4_fb
			explosion_fbflag = 5
		ENDIF
	ENDIF
	IF TIMERA > 1100
		IF explosion_fbflag = 5
			SET_TIME_SCALE 0.35
			//ADD_EXPLOSION_VARIABLE_SHAKE 2534.36 -1272.31 34.36 EXPLOSION_CAR //1000
			explosion_fbflag = 6
		ENDIF
	ENDIF
	IF TIMERA > 1298
		IF explosion_fbflag = 6
			SET_TIME_SCALE 0.39
			ADD_EXPLOSION_VARIABLE_SHAKE 2527.72 -1272.82 34.26 EXPLOSION_GRENADE 0.2 //1298	
			REMOVE_SCRIPT_FIRE fire5_fb
			explosion_fbflag = 7
		ENDIF
	ENDIF
	IF TIMERA > 1400
		IF explosion_fbflag = 7
			SET_TIME_SCALE 0.42
			ADD_EXPLOSION_VARIABLE_SHAKE 2529.54 -1270.12 34.31 EXPLOSION_ROCKET  0.22 //1298	
			explosion_fbflag = 8
		ENDIF
	ENDIF
	IF TIMERA > 1500
		IF explosion_fbflag = 8
			SET_TIME_SCALE 0.44
			ADD_EXPLOSION_VARIABLE_SHAKE 2524.15 -1272.31 34.42 EXPLOSION_CAR 0.25 //1298	
			REMOVE_SCRIPT_FIRE fire10_fb
			explosion_fbflag = 9
		ENDIF
	ENDIF

	IF TIMERA > 1600
		IF explosion_fbflag = 9
			SET_TIME_SCALE 0.5
			ADD_EXPLOSION_VARIABLE_SHAKE 2522.33 -1270.19 34.19 EXPLOSION_MOLOTOV 0.3 //1592
			REMOVE_SCRIPT_FIRE fire1_fb
			REMOVE_SCRIPT_FIRE fire2_fb 
			REMOVE_SCRIPT_FIRE fire5_fb
			REMOVE_SCRIPT_FIRE fire10_fb
			REMOVE_SCRIPT_FIRE fire9_fb
			REMOVE_SCRIPT_FIRE fire15_fb
			REMOVE_SCRIPT_FIRE fire16_fb
			REMOVE_SCRIPT_FIRE fire13_fb
			REMOVE_SCRIPT_FIRE fire3_fb
			explosion_fbflag = 10
		ENDIF
	ENDIF
	IF TIMERA > 1700
		IF explosion_fbflag = 10
			ADD_EXPLOSION_VARIABLE_SHAKE 2524.14 -1270.04 34.19 EXPLOSION_ROCKET 0.25 //2076

			START_SCRIPT_FIRE 2523.17 -1269.18 34.04 0 3 fire1_fb
			START_SCRIPT_FIRE 2522.47 -1271.46 34.15 0 2 fire2_fb
			START_SCRIPT_FIRE 2523.68 -1272.35 34.19 0 3 fire5_fb
			START_SCRIPT_FIRE 2523.18 -1273.32 34.06 0 2 fire10_fb
			START_SCRIPT_FIRE 2522.8 -1274.45 34.05 0 3 fire9_fb
			START_SCRIPT_FIRE 2522.42 -1276.4 34.05 0 2 fire15_fb
			START_SCRIPT_FIRE 2520.32 -1273.41 34.0 0 1 fire16_fb
			START_SCRIPT_FIRE 2521.32 -1273.41 34.0 0 1 fire13_fb
			START_SCRIPT_FIRE 2520.93 -1269.28 34.0 0 2 fire3_fb
			explosion_fbflag = 11
		ENDIF
	ENDIF
	IF TIMERA > 1916
		IF explosion_fbflag = 11
			ADD_EXPLOSION_VARIABLE_SHAKE 2522.01 -1274.06 34.2 EXPLOSION_CAR 0.2 //2516

			explosion_fbflag = 12
		ENDIF
	ENDIF
//	IF TIMERA > 1916
//		IF explosion_fbflag = 9
//			ADD_EXPLOSION_VARIABLE_SHAKE 2519.3 -1272.06 34.05 EXPLOSION_ROCKET //2936
//			explosion_fbflag = 10
//		ENDIF
//	ENDIF

ENDIF


IF finaleb_fbflag = 11

	//camtime = TIMERA
	//VIEW_INTEGER_VARIABLE camtime camtime

	
	IF camz_fb < 61.0
		camz_fb +=@ 0.1 //05
		camx_fb	-=@ 0.02
		lookcamz_fb +=@ 0.008
	ENDIF

	SET_FIXED_CAMERA_POSITION camx_fb camy_fb camz_fb 0.0 0.0 0.0 	//2517.78 1272.76
	POINT_CAMERA_AT_POINT lookcamx_fb lookcamy_fb lookcamz_fb JUMP_CUT
	
	//1st floor
	IF explosion_fbflag = 12
		IF TIMERA > 1400
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.46 -1268.02 39.8 EXPLOSION_GRENADE 0.2 //100
			explosion_fbflag = 13
		ENDIF
	ENDIF

	IF explosion_fbflag = 13
		IF TIMERA > 1670
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.48 -1270.67 40.32 EXPLOSION_CAR 0.25 //100
			explosion_fbflag = 14
		ENDIF
	ENDIF

	IF explosion_fbflag = 14
		IF TIMERA > 1800
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.32 -1275.86 40.47 EXPLOSION_ROCKET 0.2 //100

			REMOVE_SCRIPT_FIRE fire4_fb
			REMOVE_SCRIPT_FIRE fire6_fb
			REMOVE_SCRIPT_FIRE fire14_fb
			REMOVE_SCRIPT_FIRE fire9_fb

			START_SCRIPT_FIRE 2521.46 -1268.02 39.8 1 2 fire4_fb
			START_SCRIPT_FIRE 22521.48 -1270.67 40.32 1 2 fire6_fb
			START_SCRIPT_FIRE 2521.32 -1275.86 40.47 1 1 fire14_fb

			explosion_fbflag = 15
		ENDIF
	ENDIF
											 
	IF explosion_fbflag = 15
		IF TIMERA > 1950
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.42 -1273.18 39.83 EXPLOSION_ROCKET 0.2 //100 2525.43 -1280.65 40.28
			ADD_EXPLOSION_VARIABLE_SHAKE 2525.43 -1280.65 40.28 EXPLOSION_CAR 0.2 //side
			explosion_fbflag = 16
		ENDIF
	ENDIF
	
	//2nd floor
	IF explosion_fbflag = 16
		IF TIMERA > 2200
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.27 -1270.64 43.25 EXPLOSION_ROCKET 0.2 //100
			REMOVE_SCRIPT_FIRE fire17_fb
			REMOVE_SCRIPT_FIRE fire3_fb
			REMOVE_SCRIPT_FIRE fire19_fb
			REMOVE_SCRIPT_FIRE fire18_fb
			REMOVE_SCRIPT_FIRE fire10_fb
			START_SCRIPT_FIRE 2525.43 -1280.65 40.28 1 2 fire9_fb
			explosion_fbflag = 17
		ENDIF
	ENDIF

	IF explosion_fbflag = 17
		IF TIMERA > 2568
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.15 -1267.91 43.37 EXPLOSION_ROCKET 0.2 //100
			ADD_EXPLOSION_VARIABLE_SHAKE 2525.89 -1281.0 43.79 EXPLOSION_CAR 0.2 //side 
			START_SCRIPT_FIRE 2525.89 -1281.0 43.79 3 3 fire10_fb
			explosion_fbflag = 18
		ENDIF
	ENDIF

	IF explosion_fbflag = 18
		IF TIMERA > 3241
			ADD_EXPLOSION_VARIABLE_SHAKE 2520.86 -1273.39 43.2 EXPLOSION_GRENADE 0.2 //100
			START_SCRIPT_FIRE 2521.27 -1270.64 43.25 1 3 fire17_fb
			START_SCRIPT_FIRE 2521.15 -1267.91 43.37 2 2 fire3_fb
			START_SCRIPT_FIRE 2521.34 -1275.7 43.26 3 1 fire19_fb
			explosion_fbflag = 19
		ENDIF
	ENDIF

	IF explosion_fbflag = 19
		IF TIMERA > 3485
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.34 -1275.7 43.26 EXPLOSION_CAR 0.2 //
			explosion_fbflag = 20
		ENDIF
	ENDIF

	//3rd floor
	IF explosion_fbflag = 20
		IF TIMERA > 3708
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.15 -1275.61 47.83 EXPLOSION_ROCKET 0.2 //100
			explosion_fbflag = 21
		ENDIF
	ENDIF

	IF explosion_fbflag = 21
		IF TIMERA > 4246
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.04 -1272.97 47.91 EXPLOSION_GRENADE 0.2 //100
			REMOVE_SCRIPT_FIRE fire15_fb
			START_SCRIPT_FIRE 2525.88 -1279.99 48.43 3 2 fire2_fb
			explosion_fbflag = 22
		ENDIF
	ENDIF

	IF explosion_fbflag = 22
		IF TIMERA > 4708
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.24 -1267.9 48.15 EXPLOSION_CAR 0.2 //100
			REMOVE_SCRIPT_FIRE fire1_fb
			REMOVE_SCRIPT_FIRE fire2_fb
			ADD_EXPLOSION_VARIABLE_SHAKE 2525.88 -1279.99 48.43 EXPLOSION_ROCKET 0.2 //side
			explosion_fbflag = 23
		ENDIF
	ENDIF

	IF explosion_fbflag = 23
		IF TIMERA > 5000
			ADD_EXPLOSION_VARIABLE_SHAKE 2521.37 -1270.86 48.35 EXPLOSION_GRENADE 0.2 //100 
			START_SCRIPT_FIRE 2521.15 -1275.61 47.83 3 1 fire18_fb
			START_SCRIPT_FIRE 2521.04 -1272.97 47.91 2 2 fire1_fb
			START_SCRIPT_FIRE 2521.24 -1267.9 48.15 3 2 fire2_fb

			explosion_fbflag = 24
		ENDIF
	ENDIF

	IF explosion_fbflag = 24
		IF TIMERA > 3000
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_ONSCREEN_TIMER escapetimer_fb

			REMOVE_ANIMATION DAM_JUMP
			MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
			MARK_MODEL_AS_NO_LONGER_NEEDED M4 
			MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX

			SET_HEATHAZE_EFFECT FALSE

			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			GOTO mission_finaleB_passed

			finaleb_fbflag = 11
			explosion_fbflag = 25
		ENDIF
	ENDIF
	
ENDIF


GOTO mission_finaleB_loop


runnostay_fblabel:
OPEN_SEQUENCE_TASK runnostay_fbseq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_fb enemyy_fb enemyz_fb PEDMOVE_RUN -1
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runnostay_fbseq
PERFORM_SEQUENCE_TASK enemy_fb runnostay_fbseq
CLEAR_SEQUENCE_TASK runnostay_fbseq
RETURN																					   

stayshoot_fblabel:
OPEN_SEQUENCE_TASK stayshoot_fbseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshoot_fbseq
PERFORM_SEQUENCE_TASK enemy_fb stayshoot_fbseq
CLEAR_SEQUENCE_TASK stayshoot_fbseq
RETURN

stayshootnoduck_fblabel:
OPEN_SEQUENCE_TASK stayshootnoduck_fbseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshootnoduck_fbseq
PERFORM_SEQUENCE_TASK enemy_fb stayshootnoduck_fbseq
CLEAR_SEQUENCE_TASK stayshootnoduck_fbseq
RETURN

run2shoot_fblabel:
OPEN_SEQUENCE_TASK run2shoot_fbseq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_fb enemyy_fb enemyz_fb PEDMOVE_RUN -1
TASK_GO_STRAIGHT_TO_COORD -1 enemyx2_fb enemyy2_fb enemyz2_fb PEDMOVE_RUN -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK run2shoot_fbseq
PERFORM_SEQUENCE_TASK enemy_fb run2shoot_fbseq
CLEAR_SEQUENCE_TASK run2shoot_fbseq
RETURN

rolloutr_fblabel:
OPEN_SEQUENCE_TASK rolloutr_fbseq
TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutr_fbseq
PERFORM_SEQUENCE_TASK enemy_fb rolloutr_fbseq
CLEAR_SEQUENCE_TASK rolloutr_fbseq
RETURN

peekright_fblabel:
OPEN_SEQUENCE_TASK peekright_fbseq
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer shootimer_s4
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fb enemyy_fb enemyz_fb heading_fb -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekright_fbseq 1
CLOSE_SEQUENCE_TASK peekright_fbseq
PERFORM_SEQUENCE_TASK enemy_fb peekright_fbseq
CLEAR_SEQUENCE_TASK peekright_fbseq
RETURN

peekleft_fblabel:
OPEN_SEQUENCE_TASK peekleft_fbseq
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer shootimer_s4
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fb enemyy_fb enemyz_fb heading_fb -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekleft_fbseq 1
CLOSE_SEQUENCE_TASK peekleft_fbseq
PERFORM_SEQUENCE_TASK enemy_fb peekleft_fbseq
CLEAR_SEQUENCE_TASK peekleft_fbseq
RETURN

// Mission finaleB failed
mission_finaleB_failed:
PRINT_BIG M_FAIL 5000 1

IF cardead_fbflag = 1
	PRINT_NOW RM4_46 5000 1 //~r~You wrecked your car!
ENDIF
IF displaygripbar_fbflag = 7
	PRINT_NOW RM4_45 7000 1 //~r~Sweet died! You are supposed to catch him, next time get your car underneath Sweet when he begins to lose grip!
ENDIF
IF lostsweet_fbflag = 1
	PRINT_NOW RM4_50 5000 1 //~r~You lost Sweet! Next time stay closer!
ENDIF

RETURN
   

// mission finaleB passed
mission_finaleB_passed:
finalebplayerpass_flag = 1
CLEAR_WANTED_LEVEL player1
flag_riot_mission_counter++
RETURN
		


// mission cleanup

mission_cleanup_finaleB:
SET_HEATHAZE_EFFECT FALSE
SET_DARKNESS_EFFECT FALSE -1
REMOVE_ALL_SCRIPT_FIRES

SET_WANTED_MULTIPLIER 1.0
SET_MAX_WANTED_LEVEL 6
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0

CLEAR_ONSCREEN_TIMER escapetimer_fb

MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED NVGOGGLES
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED M4 
MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX
REMOVE_CHAR_ELEGANTLY big_smoke
REMOVE_CHAR_ELEGANTLY tenpenny_fb
UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2
REMOVE_ANIMATION SWAT
REMOVE_ANIMATION DAM_JUMP

MARK_MODEL_AS_NO_LONGER_NEEDED FIRELA
MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED FELTZER
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2

MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar1
MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar2

REMOVE_ANIMATION FINALE
REMOVE_ANIMATION FINALE2

//REMOVE_BLIP sweet_fbblip
REMOVE_BLIP carter_fbblip
REMOVE_BLIP extinguisher1_fbblip

//pickups
REMOVE_PICKUP armour1_fb
REMOVE_PICKUP armour2_fb
REMOVE_PICKUP armour3_fb
REMOVE_PICKUP armour4_fb
REMOVE_PICKUP armour5_fb
REMOVE_PICKUP health1_fb
REMOVE_PICKUP health2_fb
REMOVE_PICKUP health3_fb
REMOVE_PICKUP extinguisher1_fb
REMOVE_PICKUP extinguisher2_fb
REMOVE_PICKUP ak_fb

SWITCH_EMERGENCY_SERVICES ON
SET_WANTED_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
nLoop_fb = 0
WHILE nLoop_fb < 9
	KILL_FX_SYSTEM fxPlayerSmoke_fb[nLoop_fb]
	nLoop_fb ++	
ENDWHILE
//delete doors

flag_player_on_mission = 0
MISSION_HAS_FINISHED
IF finalebplayerpass_flag = 1
	DO_FADE 0 FADE_OUT
ENDIF
RETURN
		

}


