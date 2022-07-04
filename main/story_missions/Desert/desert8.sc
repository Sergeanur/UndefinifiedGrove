MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***********************************    Mafia 4   ****************************************
// ****************************  Steal Jetpack from Area 51 ********************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME maf4

// Mission start stuff

GOSUB mission_start_mafia4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mafia4_failed
ENDIF

GOSUB mission_cleanup_mafia4

MISSION_END
 
// Variables for mission

// **************************************** Mission Start **********************************

mission_start_mafia4:

REGISTER_MISSION_GIVEN


WAIT 0

{

LOAD_MISSION_TEXT DSERT8

FORCE_WEATHER_NOW WEATHER_SUNNY_DESERT
LVAR_INT  fat_level
GET_INT_STAT FAT fat_level

IF fat_level > 600
    LOAD_CUTSCENE D8_ALT
     
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
    GOTO mission_cleanup_mafia4
ELSE
	CLEAR_AREA desert2X desert2Y desert2Z 100.0 FALSE
	LOAD_CUTSCENE DESERT8
	 
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

ENDIF


// *************************************** Variables ***************************************

//outside
CONST_INT NUMBEROFSEARCHLIGHTS_D8 7

LVAR_INT spotlight_d8[NUMBEROFSEARCHLIGHTS_D8]
LVAR_INT spotlight_d8bulb[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT startx[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT starty[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT startz[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT endx[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT endy[NUMBEROFSEARCHLIGHTS_D8]
LVAR_FLOAT endz[NUMBEROFSEARCHLIGHTS_D8]
LVAR_INT spotlight_d8health
LVAR_INT spotlight1_d8housing
LVAR_INT spotlight1_d8base
LVAR_INT spotlight2_d8housing
LVAR_INT spotlight2_d8base
LVAR_INT spotlight3_d8housing
LVAR_INT spotlight3_d8base
LVAR_INT spotlight4_d8housing
LVAR_INT spotlight4_d8base
LVAR_INT spotlight5_d8housing
LVAR_INT spotlight5_d8base
LVAR_INT spotlight6_d8housing
LVAR_INT spotlight6_d8base
LVAR_INT spotlight7_d8housing
LVAR_INT spotlight7_d8base

LVAR_INT truth_d8
LVAR_INT truthcar_d8
LVAR_INT bike_d8

LVAR_INT army_d8[22]
LVAR_INT cutchar_d8

LVAR_INT car_d8

LVAR_INT sci_d8
LVAR_INT hours_d8
LVAR_INT minutes_d8
//fx
LVAR_INT shootlightf_d8fx[NUMBEROFSEARCHLIGHTS_D8]

//objects
LVAR_INT jetdoor_d8
//pickups
LVAR_INT jetpack_d8
LVAR_INT keycard_d8
LVAR_INT armour1_d8
LVAR_INT health1_d8
LVAR_INT armour2_d8
LVAR_INT health2_d8
LVAR_INT armour3_d8
LVAR_INT armour4_d8


//seq
LVAR_INT stayshoot_d8seq
LVAR_INT enemy_d8

//text
LVAR_TEXT_LABEL $text_label_d8
LVAR_INT audio_label_d8
LVAR_TEXT_LABEL $input_text_d8


//dm
LVAR_INT desert8empty_DM
LVAR_INT desert8stealth_DM
LVAR_INT desert8coward_DM
LVAR_INT desert8tough_DM

//flags
LVAR_INT desert8_d8flag
LVAR_INT outsidearea51_d8flag
LVAR_INT spotlight_d8loop
LVAR_INT spotlight_d8loop2
LVAR_INT spotlightshot_d8flag[NUMBEROFSEARCHLIGHTS_D8]
LVAR_INT spotlightshot_d8counter
LVAR_INT trackingmode_d8flag
LVAR_INT skipcutscene_d8flag
LVAR_INT giveweapons_d8flag
LVAR_INT createsearchlights_d8flag
LVAR_INT openblastdoor_d8flag
LVAR_INT rightdooropen_d8flag
LVAR_INT leftdooropen_d8flag
LVAR_INT dooropen_d8flag
LVAR_INT inside_d8flag
LVAR_INT room_d8flag
LVAR_INT samsite_d8flag
LVAR_INT sci_d8flag
LVAR_INT jetdoor_d8flag
LVAR_INT helptext_d8flag 
LVAR_INT launchdoor_d8flag
LVAR_INT truthcar_d8flag
LVAR_INT searchlightclip_d8flag
LVAR_INT thermalhelp_d8flag
LVAR_INT warpedplayer_d8flag
LVAR_INT notinarea_d8flag
LVAR_INT timeofday_d8flag
LVAR_INT progressaudio_d8flag
LVAR_INT handlingudio_d8flag
LVAR_INT spotlightstage_d8flag
LVAR_INT stage1progressaudio_d8flag
LVAR_INT random1_ctr 
LVAR_INT stage2progressaudio_d8flag
LVAR_INT random2_ctr 
LVAR_INT audioinside_d8flag
LVAR_INT scishot_d8flag
//blips
LVAR_INT armyblips_d8[22]
LVAR_INT blastdoor_d8blip
LVAR_INT jetpack_d8blip
LVAR_INT keycard_d8blip

// *****************************************************************************************

//give values
startx[0] =	108.51
starty[0] =	1917.268
startz[0] =	17.63
endx[0] = 135.323
endy[0]	= 1917.776
endz[0]	= 18.71

startx[1] =	180.018
starty[1] =	1904.214
startz[1] =	17.255
endx[1] = 243.707
endy[1]	= 1903.543
endz[1]	= 18.639

startx[2] =	239.0798
starty[2] =	1904.08667
startz[2] =	16.899
endx[2] = 188.371
endy[2]	= 1905.376
endz[2]	= 16.825

startx[3] =	166.119
starty[3] =	1857.384
startz[3] =	16.764
endx[3] = 276.669
endy[3]	= 1856.3389
endz[3]	= 16.671

startx[4] =	191.28 //200.769
starty[4] =	1822.46 //1817.938
startz[4] =	16.83 //17.671
endx[4] = 252.37 //106.548
endy[4]	= 1821.89 //1817.335
endz[4]	= 17.04 //16.665

startx[5] =	138.07 //117.977
starty[5] =	1823.01 //1881.453
startz[5] =	16.88 //17.1706
endx[5] = 105.1 //115.604
endy[5]	= 1867.21 //1927.817
endz[5]	= 16.97 //18.237

startx[6] =	192.9 //162.281
starty[6] =	1809.56 //1917.669
startz[6] =	16.8 //18.606
endx[6] = 144.2//125.86 
endy[6]	= 1812.52//1814.72 
endz[6]	= 16.78 

//reset flags
desert8_d8flag = 0
outsidearea51_d8flag = 0
spotlight_d8loop = 0
spotlight_d8loop2 = 0
WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
	spotlightshot_d8flag[spotlight_d8loop] = 0
	spotlight_d8loop++
ENDWHILE
spotlightshot_d8counter	= 0
trackingmode_d8flag	= 0
desert8_d8flag = 0
outsidearea51_d8flag = 0
spotlight_d8loop = 0
spotlight_d8loop2 = 0
spotlightshot_d8counter = 0
trackingmode_d8flag = 0
skipcutscene_d8flag = 0
giveweapons_d8flag = 0
createsearchlights_d8flag = 0
openblastdoor_d8flag = 0
rightdooropen_d8flag = 0
leftdooropen_d8flag = 0
dooropen_d8flag = 0
inside_d8flag = 0
room_d8flag = 0
samsite_d8flag = 0
sci_d8flag = 0
jetdoor_d8flag = 0
helptext_d8flag = 0 
launchdoor_d8flag = 0
truthcar_d8flag = 0
searchlightclip_d8flag = 0
thermalhelp_d8flag = 0
warpedplayer_d8flag = 0
notinarea_d8flag = 0
timeofday_d8flag = 0
progressaudio_d8flag = 0
handlingudio_d8flag = 0
stage1progressaudio_d8flag = 0
random1_ctr = 0
stage2progressaudio_d8flag = 0
random2_ctr = 0
spotlightstage_d8flag = 0
audioinside_d8flag = 0
scishot_d8flag = 0

// ****************************************START OF CUTSCENE********************************

// ****************************************END OF CUTSCENE**********************************

// fades the screen in 

//SET_PED_DENSITY_MULTIPLIER 0.0
SET_WANTED_MULTIPLIER 0.0

SET_FADING_COLOUR 0 0 0

WAIT 500

// request models

REQUEST_MODEL ARMY
REQUEST_MODEL M4
REQUEST_MODEL A51_SPOTBULB
REQUEST_MODEL A51_SPOTHOUSING
REQUEST_MODEL A51_SPOTBASE
REQUEST_MODEL CAMPER

LOAD_SPECIAL_CHARACTER 1 TRUTH
REQUEST_CAR_RECORDING 340
REQUEST_CAR_RECORDING 341
REQUEST_MODEL KNIFECUR
REQUEST_MODEL SILENCED
REQUEST_MODEL SNIPER

REQUEST_MODEL IRGOGGLES

LOAD_MISSION_AUDIO 3 SOUND_BANK_BLACK_PROJECT
LOAD_MISSION_AUDIO 2 SOUND_TRUX_AS //Go, go, go!
LOAD_MISSION_AUDIO 1 SOUND_DES8_LA //Do we have a plan here?

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED ARMY
	OR NOT HAS_MODEL_LOADED M4
	OR NOT HAS_MODEL_LOADED A51_SPOTBULB
	OR NOT HAS_MODEL_LOADED A51_SPOTHOUSING
	OR NOT HAS_MODEL_LOADED A51_SPOTBASE
	OR NOT HAS_MODEL_LOADED CAMPER
	WAIT 0
ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 340
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 341
	OR NOT HAS_MODEL_LOADED KNIFECUR
	OR NOT HAS_MODEL_LOADED SILENCED
	OR NOT HAS_MODEL_LOADED SNIPER
	WAIT 0
ENDWHILE

WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	OR NOT HAS_MISSION_AUDIO_LOADED 2
	OR NOT HAS_MODEL_LOADED IRGOGGLES
	OR NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0
ENDWHILE

SET_DISABLE_MILITARY_ZONES TRUE


LOAD_SCENE_IN_DIRECTION	49.8744 1900.2894 17.7833 99.0
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL desert8stealth_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY desert8empty_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK desert8coward_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH desert8tough_DM

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION3 PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION3

CREATE_CAR CAMPER 22.4045 1881.6096 16.6484 truthcar_d8
SET_CAR_HEADING truthcar_d8 306.3276
CHANGE_CAR_COLOUR truthcar_d8 1 1 

GIVE_VEHICLE_PAINTJOB truthcar_d8 0

CREATE_CHAR_AS_PASSENGER truthcar_d8 PEDTYPE_SPECIAL SPECIAL01 0 truth_d8
SET_CHAR_DECISION_MAKER truth_d8 desert8empty_DM
WARP_CHAR_INTO_CAR scplayer truthcar_d8

SET_FIXED_CAMERA_POSITION 49.8744 1900.2894 17.7833 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 48.9571 1899.8960 17.8444 JUMP_CUT


SET_CHAR_COORDINATES scplayer 36.09 1921.04 18.93
SET_CHAR_HEADING scplayer 268.5112
LOAD_SCENE 106.9 -1923.21 18.35
CLEAR_AREA 82.775 1917.325 17.733 150.0 TRUE


//set back
SET_CAR_DENSITY_MULTIPLIER 0.1
SET_PED_DENSITY_MULTIPLIER 0.1 
SET_RADAR_ZOOM 75

FORCE_INTERIOR_LIGHTING_FOR_PLAYER PLAYER1 TRUE

SET_TIME_OF_DAY 23 30
TIMERA = 0
SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL PLAYER1 OFF
SHUT_CHAR_UP scplayer TRUE
DO_FADE 1000 FADE_IN

CREATE_PICKUP BODYARMOUR PICKUP_ONCE 108.48 1919.29 18.56 armour1_d8
CREATE_PICKUP HEALTH PICKUP_ONCE 228.91 1858.65 14.8 health1_d8
CREATE_PICKUP BODYARMOUR PICKUP_ONCE 247.016 1859.22 14.08 armour2_d8
CREATE_PICKUP HEALTH PICKUP_ONCE 273.61 1816.32 1.02 health2_d8
CREATE_PICKUP BODYARMOUR PICKUP_ONCE 255.36 1802.007 7.47 armour3_d8
CREATE_PICKUP BODYARMOUR PICKUP_ONCE 292.46 1817.89 1.015 armour4_d8

SHOW_BLIPS_ON_ALL_LEVELS TRUE

DELETE_OBJECT a51ventcover

CLEAR_AREA 22.4045 1881.6096 400.0 400.0 FALSE
CLEAR_WANTED_LEVEL PLAYER1

stealjetpack_main_mission_loop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_mafia4_passed  
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Arriving at Area51 cut scene	////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//arriving outside area51
IF outsidearea51_d8flag < 5

	IF outsidearea51_d8flag = 0
		IF NOT IS_CAR_DEAD truthcar_d8
			IF NOT IS_CHAR_DEAD truth_d8
				START_PLAYBACK_RECORDED_CAR truthcar_d8 340
				TASK_LOOK_AT_CHAR scplayer truth_d8 10000
				outsidearea51_d8flag = 1
				
			ENDIF
		ENDIF
	ENDIF
	IF outsidearea51_d8flag = 1
		IF NOT IS_CAR_DEAD truthcar_d8
			IF NOT IS_CHAR_DEAD truth_d8
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR truthcar_d8

					//////////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////////
					skipcutscene_d8flag = 0
					giveweapons_d8flag = 0
					SKIP_CUTSCENE_START

					//////////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////////

					TIMERA = 0
					outsidearea51_d8flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF outsidearea51_d8flag = 2
		IF TIMERA > 1000 //3000
			IF NOT IS_CAR_DEAD truthcar_d8
				IF NOT IS_CHAR_DEAD truth_d8
					IF IS_CHAR_SITTING_IN_CAR scplayer truthcar_d8
						PLAY_MISSION_AUDIO 1 //Do we have a plane here
						PRINT_NOW DES8_LA 3000 1 //Do we have a plan here?
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						LOAD_MISSION_AUDIO 1 SOUND_DES8_LC //HEY! Hold up, dude!

						PLAY_MISSION_AUDIO 2  
						PRINT_NOW TRUX_AS 2500 1 //Go, go, go!
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
							WAIT 0
						ENDWHILE
						IF NOT IS_CAR_DEAD truthcar_d8
							TASK_LEAVE_CAR scplayer	truthcar_d8
						ENDIF
						TIMERA = 0
						outsidearea51_d8flag = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF outsidearea51_d8flag = 3
		IF NOT IS_CAR_DEAD truthcar_d8
			IF NOT IS_CHAR_DEAD truth_d8
				IF NOT IS_CHAR_IN_CAR scplayer truthcar_d8
					IF HAS_MISSION_AUDIO_FINISHED 2
						IF HAS_MISSION_AUDIO_LOADED 1
							IF IS_CHAR_IN_CAR truth_d8 truthcar_d8
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT truth_d8 truthcar_d8
							ENDIF
							IF NOT IS_CAR_DEAD truthcar_d8
								TASK_LOOK_AT_VEHICLE scplayer truthcar_d8 5000
							ENDIF
							PLAY_MISSION_AUDIO 1
							PRINT_NOW DES8_LC 2000 1//HEY! Hold up, dude!
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							WAIT 350
							IF NOT IS_CAR_DEAD truthcar_d8
								START_PLAYBACK_RECORDED_CAR truthcar_d8 341
							ENDIF
							TIMERA = 0
							outsidearea51_d8flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF outsidearea51_d8flag = 4
		IF TIMERA > 4000

			CREATE_SEARCHLIGHT 161.513 1932.982 35.391 startx[0] starty[0] startz[0] 12.5 0.5 spotlight_d8[0]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 103.946 1901.047 36.246 spotlight_d8bulb[0]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 103.946 1901.047 36.246 spotlight1_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 103.946 1901.047 36.246 spotlight1_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[0] spotlight1_d8base spotlight1_d8housing spotlight_d8bulb[0] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[0] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[0] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[0] 0.0 1.181 0.768 TRUE shootlightf_d8fx[0]//

			CREATE_SEARCHLIGHT 233.067 1934.892 33.139 startx[1] starty[1] startz[1] 10.5 0.5 spotlight_d8[1]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 161.962 1933.043 36.246 spotlight_d8bulb[1]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 161.962 1933.043 36.246 spotlight2_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 161.962 1933.043 36.246 spotlight2_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[1] spotlight2_d8base spotlight2_d8housing spotlight_d8bulb[1] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[1] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[1] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[1] 0.0 1.181 0.768 TRUE shootlightf_d8fx[1]//

			CREATE_SEARCHLIGHT 266.713 1894.979 34.139 startx[2] starty[2] startz[2] 10.5 0.5 spotlight_d8[2]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 233.486 1934.789 36.246 spotlight_d8bulb[2]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 233.486 1934.789 36.246 spotlight3_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 233.486 1934.789 36.246 spotlight3_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[2] spotlight3_d8base spotlight3_d8housing spotlight_d8bulb[2] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[2] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[2] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[2] 0.0 1.181 0.768 TRUE shootlightf_d8fx[2]//

			CREATE_SEARCHLIGHT 261.97 1808.07 34.05 startx[3] starty[3] startz[3] 10.5 0.5 spotlight_d8[3]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 267.116 1895.241 36.246 spotlight_d8bulb[3]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 267.116 1895.241 36.246 spotlight4_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 267.116 1895.241 36.246 spotlight4_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[3] spotlight4_d8base spotlight4_d8housing spotlight_d8bulb[3] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[3] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[3] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[3] 0.0 1.181 0.768 TRUE shootlightf_d8fx[3]//

			CREATE_SEARCHLIGHT 164.228 1837.892 34.05 startx[4] starty[4] startz[4] 10.5 0.5 spotlight_d8[4]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 262.145 1807.62 36.246 spotlight_d8bulb[4]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 262.145 1807.62 36.246 spotlight5_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 262.145 1807.62 36.246 spotlight5_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[4] spotlight5_d8base spotlight5_d8housing spotlight_d8bulb[4] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[4] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[4] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[4] 0.0 1.181 0.768 TRUE shootlightf_d8fx[4]//

			CREATE_SEARCHLIGHT 103.887 1901.057 35.723 startx[5] starty[5] startz[5] 10.5 0.5 spotlight_d8[5]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 166.003 1849.937 36.246 spotlight_d8bulb[5]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 166.003 1849.937 36.246 spotlight6_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 166.003 1849.937 36.246 spotlight6_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[5] spotlight6_d8base spotlight6_d8housing spotlight_d8bulb[5] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[5] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[5] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[5] 0.0 1.181 0.768 TRUE shootlightf_d8fx[5]//

			CREATE_SEARCHLIGHT 261.97 1808.07 36.05 startx[6] starty[6] startz[6] 10.5 0.5 spotlight_d8[6]
			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 113.439 1814.405 36.246 spotlight_d8bulb[6]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 113.439 1814.405 36.246 spotlight7_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 113.439 1814.405 36.246 spotlight7_d8base
			ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[6] spotlight7_d8base spotlight7_d8housing spotlight_d8bulb[6] 0.0 1.181 0.768
			SET_OBJECT_HEALTH spotlight_d8bulb[6] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[6] TRUE
			CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[6] 0.0 1.181 0.768 TRUE shootlightf_d8fx[6]//

			GOSUB resetsearchlight_d8label
			
			createsearchlights_d8flag = 1

			//entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 112.39 1931.193 17.77 army_d8[0]
			SET_CHAR_DECISION_MAKER army_d8[0] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[0] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[0] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 114.51 1914.793 18.8 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 112.39 1931.193 18.77 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[0] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[0] armyblips_d8[0]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[0] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[0] BLIP_ONLY
			SET_INFORM_RESPECTED_FRIENDS army_d8[0] 20.0 1

			//1st two buidings right of the entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 125.135 1916.59 17.9 army_d8[1]
			SET_CHAR_DECISION_MAKER army_d8[1] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[1] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[1] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 124.716 1890.25 18.33 NONE NONE
			EXTEND_PATROL_ROUTE 159.0 1887.9 19.5 ROADCROSS PED
			EXTEND_PATROL_ROUTE 161.32 1914.42 18.6 NONE NONE
			EXTEND_PATROL_ROUTE 125.135 1916.59 18.9 ROADCROSS PED
			TASK_FOLLOW_PATROL_ROUTE army_d8[1] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[1] armyblips_d8[1]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[1] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[1] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[1] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[1] 30.0 1

			//empty courtyard bit behind the building
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 131.37 1821.139 16.6 army_d8[2]
			SET_CHAR_DECISION_MAKER army_d8[2] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[2] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[2] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 130.62 1855.297 17.73 ROADCROSS PED
			EXTEND_PATROL_ROUTE 102.09 1858.6 17.7 NONE NONE
			EXTEND_PATROL_ROUTE 102.94 1839.78 17.6 ROADCROSS PED
			EXTEND_PATROL_ROUTE 131.37 1821.139 17.6 NONE NONE
			TASK_FOLLOW_PATROL_ROUTE army_d8[2] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[2] armyblips_d8[2]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[2] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[2] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[2] 80
			SET_INFORM_RESPECTED_FRIENDS army_d8[2] 30.0 1

			//walks once then near tower
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 163.458 1824.58 17.64 army_d8[3]
			SET_CHAR_HEADING army_d8[3] 180.123
			SET_CHAR_DECISION_MAKER army_d8[3] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[3] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[3] 200.0
			ADD_BLIP_FOR_CHAR army_d8[3] armyblips_d8[3]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[3] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[3] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[3] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[3] 30.0 1

			//main entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 189.91 1916.399 16.64 army_d8[4]
			SET_CHAR_DECISION_MAKER army_d8[4] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[4] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[4] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 168.0187 1916.279 18.37 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 189.91 1916.399 17.64 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[4] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[4] armyblips_d8[4]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[4] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[4] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[4] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[4] 30.0 1
						
			//right of control tower
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 191.535 1809.588 16.641 army_d8[5]
			SET_CHAR_DECISION_MAKER army_d8[5] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[5] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[5] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 131.24 1811.194 17.6 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 191.535 1809.588 17.641 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[5] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[5] armyblips_d8[5]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[5] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[5] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[5] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[5] 30.0 1

			//just behind main entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 195.36 1872.92 16.64 army_d8[6]
			SET_CHAR_DECISION_MAKER army_d8[6] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[6] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[6] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 231.095 1872.9 17.64 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 195.36 1872.92 17.64 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[6] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[6] armyblips_d8[6]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[6] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[6] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[6] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[6] 20.0 1

			//first building on the right
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 108.673 1886.18 17.04 army_d8[7]
			SET_CHAR_DECISION_MAKER army_d8[7] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[7] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[7] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 107.065 1865.8 17.78 ROADCROSS PED
			EXTEND_PATROL_ROUTE 126.78 1864.25 17.8 NONE NONE
			EXTEND_PATROL_ROUTE 127.842 1885.04 18.027 ROADCROSS PED
			EXTEND_PATROL_ROUTE 108.673 1886.18 18.04 NONE NONE
			TASK_FOLLOW_PATROL_ROUTE army_d8[7] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[7] armyblips_d8[7]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[7] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[7] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[7] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[7] 30.0 1

			//right of blast doors
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 173.671 1886.841 19.9 army_d8[8]
			SET_CHAR_DECISION_MAKER army_d8[8] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[8] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[8] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 174.25 1866.51 20.7 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 173.671 1886.841 20.9 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[8] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[8] armyblips_d8[8]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[8] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[8] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[8] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[8] 30.0 1

			//left of blast doors
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 249.99 1874.78 19.6 army_d8[9]
			SET_CHAR_DECISION_MAKER army_d8[9] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[9] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[9] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 250.99 1906.56 20.6 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 249.99 1874.78 20.6 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[9] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[9] armyblips_d8[9]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[9] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[9] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[9] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[9] 30.0 1

			//infront of blast door
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 224.26 1916.79 16.64 army_d8[10]
			SET_CHAR_DECISION_MAKER army_d8[10] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[10] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[10] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 204.475 1915.9 17.64 ROADCROSS PED
			EXTEND_PATROL_ROUTE 204.628 1904.521 17.64 NONE NONE
			EXTEND_PATROL_ROUTE 221.645 1905.13 17.64 ROADCROSS PED
			EXTEND_PATROL_ROUTE 224.26 1916.79 17.64 NONE NONE
			TASK_FOLLOW_PATROL_ROUTE army_d8[10] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[10] armyblips_d8[10]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[10] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[10] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[10] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[10] 30.0 1

			//standing still inbetween two buildings
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 209.07 1833.63 16.64 army_d8[11]
			SET_CHAR_HEADING army_d8[11] 346.579
			SET_CHAR_DECISION_MAKER army_d8[11] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[11] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[11] 200.0
			ADD_BLIP_FOR_CHAR army_d8[11] armyblips_d8[11]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[11] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[11] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[11] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[11] 30.0 1

			//far side, closed off entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 268.027 1862.269 17.64 army_d8[12]
			SET_CHAR_DECISION_MAKER army_d8[12] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[12] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[12] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 266.9 1822.3 17.64 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 268.027 1862.269 17.64 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[12] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[12] armyblips_d8[12]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[12] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[12] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[12] 85
			SET_INFORM_RESPECTED_FRIENDS army_d8[12] 30.0 1

			//behind blast door
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 221.16 1858.28 20.63 army_d8[13]
			SET_CHAR_DECISION_MAKER army_d8[13] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[13] WEAPONTYPE_M4 9999
			SET_CHAR_ACCURACY army_d8[13] 90
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[13] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 198.711 1857.64 19.633 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 221.16 1858.28 20.63 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[13] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[13] armyblips_d8[13]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[13] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[13] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[13] 90
			SET_INFORM_RESPECTED_FRIENDS army_d8[13] 30.0 1

			//to the left of the control tower
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 218.45 1820.78 16.64 army_d8[14]
			SET_CHAR_DECISION_MAKER army_d8[14] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[14] WEAPONTYPE_M4 9999
			SET_CHAR_ACCURACY army_d8[14] 90
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[14] 200.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 250.31 1821.924 17.64 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 218.45 1820.78 17.64 NONE NONE
			TASK_FOLLOW_PATROL_ROUTE army_d8[14] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[14] armyblips_d8[14]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[14] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[14] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[14] 80
			SET_INFORM_RESPECTED_FRIENDS army_d8[14] 30.0 1

			DO_FADE 100 FADE_OUT

			WHILE GET_FADING_STATUS 
				WAIT 0
			ENDWHILE

		  	SET_FIXED_CAMERA_POSITION 312.659 1806.521 26.272 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 311.7959 1807.0223 26.2183 JUMP_CUT
			LOAD_SCENE_IN_DIRECTION	312.659 1806.521 26.272 60.2

			DO_FADE 100 FADE_IN

			PRINT_NOW DST8_1 7000 1 //~s~The Truth wants you to gain entry to the Area 69 reserach lab and bring back to him the 'Black Project' that is being developed inside.

			SET_CHAR_COORDINATES scplayer 69.91 1916.007 16.648
			SET_CHAR_HEADING scplayer 273.363

			TIMERA = 0

			WHILE TIMERA < 7000
				   			   
			WAIT 0
		
			ENDWHILE

			PRINT_NOW DST8_18 8000 1 //~s~The main way into the Area 69 interior is to go through the blast doors, to open these go to the ~y~control tower~s~.
			SET_FIXED_CAMERA_POSITION 232.5476 1911.0217 34.7955 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 231.9904 1910.2614 34.4617 JUMP_CUT

			TIMERA = 0

			WHILE TIMERA < 4000
				   			   
			WAIT 0
		
			ENDWHILE

			SET_FIXED_CAMERA_POSITION 183.9385 1801.7700 29.0751 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 184.8011 1802.2155 28.8356 JUMP_CUT //of control tower

			TIMERA = 0

			WHILE TIMERA < 4000
				   			   
			WAIT 0
		
			ENDWHILE

			SET_FIXED_CAMERA_POSITION 120.4534 1943.5220 29.8174 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 120.5729 1942.5332 29.7278 JUMP_CUT
			PRINT_NOW DST8_19 4000 1 //~s~There are searchlights patrolling the area observe their pattern and set movements and plan your route around.
			PRINT DST8_47 4000 1 //~s~If you stay in a searchlight too long or shoot too many out you will be spotted. 
			PRINT DST8_48 4000 1 //~s~The blast doors will go on lockdown, and alternative route will then have to be found.

			TIMERA = 0

			WHILE TIMERA < 12000
				   			   
			WAIT 0
		
			ENDWHILE

			//towers
			CREATE_CHAR PEDTYPE_MISSION3 ARMY 103.9055 1900.5345 24.4985 army_d8[15]
			SET_CHAR_DECISION_MAKER army_d8[15] desert8empty_DM
			SET_CHAR_HEADING army_d8[15] 6.0802
			GIVE_WEAPON_TO_CHAR army_d8[15] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[15] TRUE
			SET_CHAR_HEALTH army_d8[15] 10
			ADD_BLIP_FOR_CHAR army_d8[15] armyblips_d8[15]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[15] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[15] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[15] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 113.3839 1814.2606 24.4985 army_d8[16]
			SET_CHAR_DECISION_MAKER army_d8[16] desert8empty_DM
			SET_CHAR_HEADING army_d8[16] 5.0273
			GIVE_WEAPON_TO_CHAR army_d8[16] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[16] TRUE
			SET_CHAR_HEALTH army_d8[16] 10
			ADD_BLIP_FOR_CHAR army_d8[16] armyblips_d8[16]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[16] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[16] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[16] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 165.6023 1850.0703 24.4985 army_d8[17]
			SET_CHAR_DECISION_MAKER army_d8[17] desert8empty_DM
			SET_CHAR_HEADING army_d8[17] 5.0273
			GIVE_WEAPON_TO_CHAR army_d8[17] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[17] TRUE
			SET_CHAR_HEALTH army_d8[17] 10
			ADD_BLIP_FOR_CHAR army_d8[17] armyblips_d8[17]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[17] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[17] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[17] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 261.8728 1807.9380 24.4985 army_d8[18]
			SET_CHAR_DECISION_MAKER army_d8[18] desert8empty_DM
			SET_CHAR_HEADING army_d8[18] 5.0273 
			GIVE_WEAPON_TO_CHAR army_d8[18] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[18] TRUE
			SET_CHAR_HEALTH army_d8[18] 10
			ADD_BLIP_FOR_CHAR army_d8[18] armyblips_d8[18]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[18] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[18] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[18] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 267.1389 1895.4851 24.4985 army_d8[19]
			SET_CHAR_DECISION_MAKER army_d8[19] desert8empty_DM
			SET_CHAR_HEADING army_d8[19] 119.7683 
			GIVE_WEAPON_TO_CHAR army_d8[19] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[19] TRUE
			SET_CHAR_HEALTH army_d8[19] 10
			ADD_BLIP_FOR_CHAR army_d8[19] armyblips_d8[19]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[19] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[19] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[19] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 161.9575 1933.3417 24.4985 army_d8[20]
			SET_CHAR_DECISION_MAKER army_d8[20] desert8empty_DM
			SET_CHAR_HEADING army_d8[20] 119.7683 
			GIVE_WEAPON_TO_CHAR army_d8[20] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[20] TRUE
			SET_CHAR_HEALTH army_d8[20] 10
			ADD_BLIP_FOR_CHAR army_d8[20] armyblips_d8[20]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[20] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[20] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[20] desert8tough_DM

			CREATE_CHAR PEDTYPE_MISSION3 ARMY 233.6724 1934.6226 24.4985 army_d8[21]
			SET_CHAR_DECISION_MAKER army_d8[21] desert8empty_DM
			SET_CHAR_HEADING army_d8[21] 119.7683 
			GIVE_WEAPON_TO_CHAR army_d8[21] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[21] TRUE
			SET_CHAR_HEALTH army_d8[21] 10
			ADD_BLIP_FOR_CHAR army_d8[21] armyblips_d8[21]
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[21] TRUE
			CHANGE_BLIP_DISPLAY armyblips_d8[21] BLIP_ONLY
			SET_CHAR_DECISION_MAKER army_d8[21] desert8tough_DM
										
			SET_FIXED_CAMERA_POSITION 113.0058 1934.0894 18.2450 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 113.0995 1933.1117 18.4334 JUMP_CUT

			PRINT_NOW DST8_21 5000 1//~s~There are also several military personnel patrolling the area, use the thermal goggles The Truth gave you to help you see them in the dark.

			TIMERA = 0

			WHILE TIMERA < 5000
				   			   
			WAIT 0
				
			ENDWHILE

			SET_FIXED_CAMERA_POSITION 112.0028 1893.8461 19.5192 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 111.2033 1894.4467 19.5192 JUMP_CUT
			CAMERA_RESET_NEW_SCRIPTABLES
			CAMERA_PERSIST_POS TRUE
			CAMERA_SET_VECTOR_MOVE 112.0028 1893.8461 19.5192 112.0028 1893.8461 35.5192 6000 TRUE
			PRINT_NOW DST8_22 6500 1 //~s~You can use stealth to avoid them or climb to the top of a control tower and use your sniper rifle to take them out.

			TIMERA = 0

			WHILE TIMERA < 6500
				   			   
			WAIT 0
				
			ENDWHILE


			giveweapons_d8flag = 1
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 1
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 70
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_INFRARED 1
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SNIPERRIFLE 35

			//////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////
			skipcutscene_d8flag = 1
			SKIP_CUTSCENE_END
			//////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////
			IF skipcutscene_d8flag = 0
				
				DO_FADE 250 FADE_OUT 

				CLEAR_PRINTS

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				IF NOT IS_CAR_DEAD truthcar_d8
					IF IS_PLAYBACK_GOING_ON_FOR_CAR truthcar_d8
						STOP_PLAYBACK_RECORDED_CAR truthcar_d8
					ENDIF
				ENDIF

				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 69.91 1916.007 16.648
					SET_CHAR_HEADING scplayer 273.363
				ENDIF

				SET_CHAR_COORDINATES scplayer 69.91 1916.007 16.648
				SET_CHAR_HEADING scplayer 273.363

				DELETE_CHAR truth_d8
				DELETE_CAR truthcar_d8
				UNLOAD_SPECIAL_CHARACTER 1
				MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER

				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				CLEAR_PRINTS

				IF giveweapons_d8flag = 0
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 1
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 70
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_INFRARED 1
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SNIPERRIFLE 35
				ENDIF

				IF createsearchlights_d8flag = 0
					CREATE_SEARCHLIGHT 161.513 1932.982 35.391 startx[0] starty[0] startz[0] 12.5 0.5 spotlight_d8[0]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 103.946 1901.047 36.246 spotlight_d8bulb[0]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 103.946 1901.047 36.246 spotlight1_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 103.946 1901.047 36.246 spotlight1_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[0] spotlight1_d8base spotlight1_d8housing spotlight_d8bulb[0] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[0] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[0] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[0] 0.0 1.181 0.768 TRUE shootlightf_d8fx[0]//

					CREATE_SEARCHLIGHT 233.067 1934.892 33.139 startx[1] starty[1] startz[1] 10.5 0.5 spotlight_d8[1]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 161.962 1933.043 36.246 spotlight_d8bulb[1]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 161.962 1933.043 36.246 spotlight2_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 161.962 1933.043 36.246 spotlight2_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[1] spotlight2_d8base spotlight2_d8housing spotlight_d8bulb[1] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[1] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[1] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[1] 0.0 1.181 0.768 TRUE shootlightf_d8fx[1]//

					CREATE_SEARCHLIGHT 266.713 1894.979 34.139 startx[2] starty[2] startz[2] 10.5 0.5 spotlight_d8[2]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 233.486 1934.789 36.246 spotlight_d8bulb[2]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 233.486 1934.789 36.246 spotlight3_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 233.486 1934.789 36.246 spotlight3_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[2] spotlight3_d8base spotlight3_d8housing spotlight_d8bulb[2] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[2] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[2] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[2] 0.0 1.181 0.768 TRUE shootlightf_d8fx[2]//

					CREATE_SEARCHLIGHT 261.97 1808.07 34.05 startx[3] starty[3] startz[3] 10.5 0.5 spotlight_d8[3]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 267.116 1895.241 36.246 spotlight_d8bulb[3]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 267.116 1895.241 36.246 spotlight4_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 267.116 1895.241 36.246 spotlight4_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[3] spotlight4_d8base spotlight4_d8housing spotlight_d8bulb[3] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[3] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[3] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[3] 0.0 1.181 0.768 TRUE shootlightf_d8fx[3]//

					CREATE_SEARCHLIGHT 164.228 1837.892 34.05 startx[4] starty[4] startz[4] 10.5 0.5 spotlight_d8[4]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 262.145 1807.62 36.246 spotlight_d8bulb[4]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 262.145 1807.62 36.246 spotlight5_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 262.145 1807.62 36.246 spotlight5_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[4] spotlight5_d8base spotlight5_d8housing spotlight_d8bulb[4] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[4] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[4] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[4] 0.0 1.181 0.768 TRUE shootlightf_d8fx[4]//

					CREATE_SEARCHLIGHT 103.887 1901.057 35.723 startx[5] starty[5] startz[5] 10.5 0.5 spotlight_d8[5]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 166.003 1849.937 36.246 spotlight_d8bulb[5]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 166.003 1849.937 36.246 spotlight6_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 166.003 1849.937 36.246 spotlight6_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[5] spotlight6_d8base spotlight6_d8housing spotlight_d8bulb[5] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[5] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[5] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[5] 0.0 1.181 0.768 TRUE shootlightf_d8fx[5]//

					CREATE_SEARCHLIGHT 261.97 1808.07 36.05 startx[6] starty[6] startz[6] 10.5 0.5 spotlight_d8[6]
					CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 113.439 1814.405 36.246 spotlight_d8bulb[6]
					CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 113.439 1814.405 36.246 spotlight7_d8housing
					CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 113.439 1814.405 36.246 spotlight7_d8base
					ATTACH_SEARCHLIGHT_TO_SEARCHLIGHT_OBJECT spotlight_d8[6] spotlight7_d8base spotlight7_d8housing spotlight_d8bulb[6] 0.0 1.181 0.768
					SET_OBJECT_HEALTH spotlight_d8bulb[6] 5000
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[6] TRUE
					CREATE_FX_SYSTEM_ON_OBJECT shootlight spotlight_d8bulb[6] 0.0 1.181 0.768 TRUE shootlightf_d8fx[6]//
					
					GOSUB resetsearchlight_d8label
			  ENDIF

				spotlight_d8loop = 0
				WHILE spotlight_d8loop < 22
					DELETE_CHAR army_d8[spotlight_d8loop]
					REMOVE_BLIP armyblips_d8[spotlight_d8loop]
					spotlight_d8loop++
				ENDWHILE

				//entrance
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 112.39 1931.193 17.77 army_d8[0]
				SET_CHAR_DECISION_MAKER army_d8[0] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[0] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[0] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 114.51 1914.793 18.8 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 112.39 1931.193 18.77 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[0] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[0] armyblips_d8[0]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[0] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[0] BLIP_ONLY
				SET_INFORM_RESPECTED_FRIENDS army_d8[0] 20.0 1

				//1st two buidings right of the entrance
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 125.135 1916.59 17.9 army_d8[1]
				SET_CHAR_DECISION_MAKER army_d8[1] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[1] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[1] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 124.716 1890.25 18.33 NONE NONE
				EXTEND_PATROL_ROUTE 159.0 1887.9 19.5 ROADCROSS PED
				EXTEND_PATROL_ROUTE 161.32 1914.42 18.6 NONE NONE
				EXTEND_PATROL_ROUTE 125.135 1916.59 18.9 ROADCROSS PED
				TASK_FOLLOW_PATROL_ROUTE army_d8[1] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[1] armyblips_d8[1]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[1] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[1] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[1] 80
				SET_INFORM_RESPECTED_FRIENDS army_d8[1] 30.0 1

				//empty courtyard bit behind the building
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 131.37 1821.139 16.6 army_d8[2]
				SET_CHAR_DECISION_MAKER army_d8[2] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[2] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[2] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 130.62 1855.297 17.73 ROADCROSS PED
				EXTEND_PATROL_ROUTE 102.09 1858.6 17.7 NONE NONE
				EXTEND_PATROL_ROUTE 102.94 1839.78 17.6 ROADCROSS PED
				EXTEND_PATROL_ROUTE 131.37 1821.139 17.6 NONE NONE
				TASK_FOLLOW_PATROL_ROUTE army_d8[2] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[2] armyblips_d8[2]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[2] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[2] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[2] 80
				SET_INFORM_RESPECTED_FRIENDS army_d8[2] 30.0 1

				//walks once then near tower
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 163.458 1824.58 17.64 army_d8[3]
				SET_CHAR_HEADING army_d8[3] 180.123
				SET_CHAR_DECISION_MAKER army_d8[3] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[3] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[3] 200.0
				ADD_BLIP_FOR_CHAR army_d8[3] armyblips_d8[3]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[3] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[3] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[3] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[3] 30.0 1

				//main entrance
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 189.91 1916.399 16.64 army_d8[4]
				SET_CHAR_DECISION_MAKER army_d8[4] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[4] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[4] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 168.0187 1916.279 18.37 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 189.91 1916.399 17.64 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[4] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[4] armyblips_d8[4]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[4] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[4] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[4] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[4] 30.0 1
							
				//right of control tower
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 191.535 1809.588 16.641 army_d8[5]
				SET_CHAR_DECISION_MAKER army_d8[5] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[5] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[5] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 131.24 1811.194 17.6 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 191.535 1809.588 17.641 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[5] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[5] armyblips_d8[5]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[5] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[5] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[5] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[5] 30.0 1

				//just behind main entrance
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 195.36 1872.92 16.64 army_d8[6]
				SET_CHAR_DECISION_MAKER army_d8[6] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[6] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[6] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 231.095 1872.9 17.64 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 195.36 1872.92 17.64 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[6] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[6] armyblips_d8[6]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[6] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[6] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[6] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[6] 20.0 1

				//first building on the right
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 108.673 1886.18 17.04 army_d8[7]
				SET_CHAR_DECISION_MAKER army_d8[7] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[7] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[7] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 107.065 1865.8 17.78 ROADCROSS PED
				EXTEND_PATROL_ROUTE 126.78 1864.25 17.8 NONE NONE
				EXTEND_PATROL_ROUTE 127.842 1885.04 18.027 ROADCROSS PED
				EXTEND_PATROL_ROUTE 108.673 1886.18 18.04 NONE NONE
				TASK_FOLLOW_PATROL_ROUTE army_d8[7] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[7] armyblips_d8[7]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[7] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[7] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[7] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[7] 30.0 1

				//right of blast doors
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 173.671 1886.841 19.9 army_d8[8]
				SET_CHAR_DECISION_MAKER army_d8[8] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[8] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[8] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 174.25 1866.51 20.7 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 173.671 1886.841 20.9 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[8] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[8] armyblips_d8[8]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[8] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[8] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[8] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[8] 30.0 1

				//left of blast doors
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 249.99 1874.78 19.6 army_d8[9]
				SET_CHAR_DECISION_MAKER army_d8[9] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[9] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[9] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 250.99 1906.56 20.6 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 249.99 1874.78 20.6 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[9] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[9] armyblips_d8[9]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[9] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[9] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[9] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[9] 30.0 1

				//infront of blast door
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 224.26 1916.79 16.64 army_d8[10]
				SET_CHAR_DECISION_MAKER army_d8[10] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[10] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[10] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 204.475 1915.9 17.64 ROADCROSS PED
				EXTEND_PATROL_ROUTE 204.628 1904.521 17.64 NONE NONE
				EXTEND_PATROL_ROUTE 221.645 1905.13 17.64 ROADCROSS PED
				EXTEND_PATROL_ROUTE 224.26 1916.79 17.64 NONE NONE
				TASK_FOLLOW_PATROL_ROUTE army_d8[10] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[10] armyblips_d8[10]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[10] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[10] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[10] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[10] 30.0 1

				//standing still inbetween two buildings
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 209.07 1833.63 16.64 army_d8[11]
				SET_CHAR_HEADING army_d8[11] 346.579
				SET_CHAR_DECISION_MAKER army_d8[11] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[11] WEAPONTYPE_M4 99999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[11] 200.0
				ADD_BLIP_FOR_CHAR army_d8[11] armyblips_d8[11]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[11] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[11] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[11] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[11] 30.0 1

				//far side, closed off entrance
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 268.027 1862.269 17.64 army_d8[12]
				SET_CHAR_DECISION_MAKER army_d8[12] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[12] WEAPONTYPE_M4 9999
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[12] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 266.9 1822.3 17.64 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 268.027 1862.269 17.64 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[12] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[12] armyblips_d8[12]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[12] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[12] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[12] 85
				SET_INFORM_RESPECTED_FRIENDS army_d8[12] 30.0 1

				//behind blast door
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 221.16 1858.28 20.63 army_d8[13]
				SET_CHAR_DECISION_MAKER army_d8[13] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[13] WEAPONTYPE_M4 9999
				SET_CHAR_ACCURACY army_d8[13] 90
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[13] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 198.711 1857.64 19.633 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 221.16 1858.28 20.63 ROADCROSS PED 
				TASK_FOLLOW_PATROL_ROUTE army_d8[13] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[13] armyblips_d8[13]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[13] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[13] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[13] 90
				SET_INFORM_RESPECTED_FRIENDS army_d8[13] 30.0 1

				//to the left of the control tower
				CREATE_CHAR PEDTYPE_MISSION1 ARMY 218.45 1820.78 16.64 army_d8[14]
				SET_CHAR_DECISION_MAKER army_d8[14] desert8stealth_DM
				GIVE_WEAPON_TO_CHAR army_d8[14] WEAPONTYPE_M4 9999
				SET_CHAR_ACCURACY army_d8[14] 90
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[14] 200.0
				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 250.31 1821.924 17.64 ROADCROSS PED 
				EXTEND_PATROL_ROUTE 218.45 1820.78 17.64 NONE NONE
				TASK_FOLLOW_PATROL_ROUTE army_d8[14] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ADD_BLIP_FOR_CHAR army_d8[14] armyblips_d8[14]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[14] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[14] BLIP_ONLY
				SET_CHAR_ACCURACY army_d8[14] 80
				SET_INFORM_RESPECTED_FRIENDS army_d8[14] 30.0 1
				
				//towers
				CREATE_CHAR PEDTYPE_MISSION3 ARMY 103.9055 1900.5345 24.4985 army_d8[15]
				SET_CHAR_DECISION_MAKER army_d8[15] desert8empty_DM
				SET_CHAR_HEADING army_d8[15] 6.0802
				GIVE_WEAPON_TO_CHAR army_d8[15] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[15] TRUE
				SET_CHAR_HEALTH army_d8[15] 10
				ADD_BLIP_FOR_CHAR army_d8[15] armyblips_d8[15]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[15] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[15] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[15] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 113.3839 1814.2606 24.4985 army_d8[16]
				SET_CHAR_DECISION_MAKER army_d8[16] desert8empty_DM
				SET_CHAR_HEADING army_d8[16] 5.0273
				GIVE_WEAPON_TO_CHAR army_d8[16] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[16] TRUE
				SET_CHAR_HEALTH army_d8[16] 10
				ADD_BLIP_FOR_CHAR army_d8[16] armyblips_d8[16]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[16] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[16] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[16] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 165.6023 1850.0703 24.4985 army_d8[17]
				SET_CHAR_DECISION_MAKER army_d8[17] desert8empty_DM
				SET_CHAR_HEADING army_d8[17] 5.0273
				GIVE_WEAPON_TO_CHAR army_d8[17] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[17] TRUE
				SET_CHAR_HEALTH army_d8[17] 10
				ADD_BLIP_FOR_CHAR army_d8[17] armyblips_d8[17]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[17] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[17] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[17] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 261.8728 1807.9380 24.4985 army_d8[18]
				SET_CHAR_DECISION_MAKER army_d8[18] desert8empty_DM
				SET_CHAR_HEADING army_d8[18] 5.0273 
				GIVE_WEAPON_TO_CHAR army_d8[18] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[18] TRUE
				SET_CHAR_HEALTH army_d8[18] 10
				ADD_BLIP_FOR_CHAR army_d8[18] armyblips_d8[18]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[18] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[18] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[18] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 267.1389 1895.4851 24.4985 army_d8[19]
				SET_CHAR_DECISION_MAKER army_d8[19] desert8empty_DM
				SET_CHAR_HEADING army_d8[19] 119.7683 
				GIVE_WEAPON_TO_CHAR army_d8[19] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[19] TRUE
				SET_CHAR_HEALTH army_d8[19] 10
				ADD_BLIP_FOR_CHAR army_d8[19] armyblips_d8[19]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[19] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[19] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[19] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 161.9575 1933.3417 24.4985 army_d8[20]
				SET_CHAR_DECISION_MAKER army_d8[20] desert8empty_DM
				SET_CHAR_HEADING army_d8[20] 119.7683 
				GIVE_WEAPON_TO_CHAR army_d8[20] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[20] TRUE
				SET_CHAR_HEALTH army_d8[20] 10
				ADD_BLIP_FOR_CHAR army_d8[20] armyblips_d8[20]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[20] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[20] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[20] desert8tough_DM

				CREATE_CHAR PEDTYPE_MISSION3 ARMY 233.6724 1934.6226 24.4985 army_d8[21]
				SET_CHAR_DECISION_MAKER army_d8[21] desert8empty_DM
				SET_CHAR_HEADING army_d8[21] 119.7683 
				GIVE_WEAPON_TO_CHAR army_d8[21] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[21] TRUE
				SET_CHAR_HEALTH army_d8[21] 10
				ADD_BLIP_FOR_CHAR army_d8[21] armyblips_d8[21]
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[21] TRUE
				CHANGE_BLIP_DISPLAY armyblips_d8[21] BLIP_ONLY
				SET_CHAR_DECISION_MAKER army_d8[21] desert8tough_DM

				LOAD_SCENE_IN_DIRECTION 69.91 1916.007 16.648 273.4

				DO_FADE 250 FADE_IN

			ENDIF
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_TIME_OF_DAY 21 30

			CLEAR_PRINTS
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			CLEAR_PRINTS
			//SET_DARKNESS_EFFECT TRUE -1

			IF NOT IS_CAR_DEAD truthcar_d8
				IF IS_PLAYBACK_GOING_ON_FOR_CAR truthcar_d8
					STOP_PLAYBACK_RECORDED_CAR truthcar_d8
				ENDIF
			ENDIF

			DELETE_CHAR truth_d8
			DELETE_CAR truthcar_d8
			UNLOAD_SPECIAL_CHARACTER 1
			MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER
			ADD_BLIP_FOR_COORD 211.604 1811.091 20.8 blastdoor_d8blip
			//PRINT_HELP DST8_11 //Use stealth to gain entry and where possible avoid the searchlights and patrolling soldiers.
			PRINT_NOW DST8_38 5000 1 //~s~You must gain entry to the research lab before sunrise which is 0530 hours
			PRINT DST8_7 5000 1 //~s~Open the blast doors by activating the switch in the ~y~control tower~s~.					
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 2 SOUND_DES8_AA //Please, no more violence! Take my pass card and go!
			outsidearea51_d8flag = 5
		ENDIF
	ENDIF

ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Player outside	  //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF outsidearea51_d8flag = 5


	IF thermalhelp_d8flag = 0
		IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_INFRARED
			PRINT_HELP DST8_37 //When selected to toggle the thermal vision on and off press the ~o~ button.
			thermalhelp_d8flag = 1
		ENDIF
	ENDIF

	//player fails if it reaches 5.30am
	IF timeofday_d8flag = 0
		GET_TIME_OF_DAY Hours_d8 Minutes_d8
			IF hours_d8 = 5
				IF minutes_d8 > 29
					DO_FADE 500 FADE_OUT
					warpedplayer_d8flag = 1

					WHILE GET_FADING_STATUS 
						WAIT 0
					ENDWHILE
					
					SET_CHAR_COORDINATES scplayer 296.348 2474.277 15.477
					SET_CHAR_HEADING scplayer 359.722

					SET_CAMERA_BEHIND_PLAYER

					DO_FADE 500 FADE_IN
					
					PRINT_NOW DST8_41 5000 1 //~r~You had to break into the Area 69 research lab while undercover from night, next time be quicker!

					WHILE GET_FADING_STATUS 
						WAIT 0
					ENDWHILE
				
					GOTO mission_mafia4_failed

					timeofday_d8flag = 1
				ENDIF
			ENDIF
	ENDIF

	//player fails and get warned about getting out of the area
	IF warpedplayer_d8flag = 0
		IF notinarea_d8flag = 0
			IF NOT IS_CHAR_IN_AREA_2D scplayer 52.5 1952.11 295.92 1775.93 FALSE
				PRINT_NOW DST8_39 5000 1 //~s~You have venturing too far away from Area 69, if you go any further the mission will be failed.
				notinarea_d8flag = 1
			ENDIF
		ENDIF
		IF notinarea_d8flag = 1
			IF IS_CHAR_IN_AREA_2D scplayer 52.5 1952.11 295.92 1775.93 FALSE
				notinarea_d8flag = 0
			ELSE
				IF NOT IS_CHAR_IN_AREA_2D scplayer 36.9 1961.82 319.56 1760.29 FALSE			

					DO_FADE 500 FADE_OUT

					WHILE GET_FADING_STATUS 
						WAIT 0
					ENDWHILE
					
					SET_CHAR_COORDINATES scplayer 296.348 2474.277 15.477
					SET_CHAR_HEADING scplayer 359.722

					SET_CAMERA_BEHIND_PLAYER

					DO_FADE 500 FADE_IN
					
					PRINT_NOW DST8_40 5000 1 //~r~You were too far away from Area69!

					WHILE GET_FADING_STATUS 
						WAIT 0
					ENDWHILE
				
					GOTO mission_mafia4_failed

					notinarea_d8flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	
	///////////////////////////////////////////////////////////	control tower - opening and closing blast doors
	IF trackingmode_d8flag < 3

		IF trackingmode_d8flag < 2

			IF dooropen_d8flag = 0
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 211.604 1811.091 20.8 3.0 3.0 3.0 TRUE
					dooropen_d8flag = 1
				ENDIF
			ENDIF
				
				
			IF dooropen_d8flag = 1

				IF openblastdoor_d8flag = 0
					REMOVE_BLIP blastdoor_d8blip
					ADD_BLIP_FOR_COORD 214.0 1872.0 12.14 blastdoor_d8blip
					PRINT_NOW DST8_8 5000 1 //~s~The ~y~blast doors ~s~have been opened, you have limited amount of time to get through them before they shut again!
					TIMERB = 0
					//SET_FIXED_CAMERA_POSITION 212.8104 1890.4010 18.1940 0.0 0.0 0.0
					//POINT_CAMERA_AT_POINT 212.8483 1889.4835 17.7982 JUMP_CUT
					//SWITCH_WIDESCREEN ON
					//SET_PLAYER_CONTROL PLAYER1 OFF
					//ADD_ONE_OFF_SOUND 213.69 1881.26 12.44 SOUND_POLICE_CELL_DOOR_CLUNK
					//ADD_CONTINUOUS_SOUND 213.69 1881.26 12.44 SOUND_POLICE_CELL_DOOR_SLIDING_LOOP blastdoorsound_d8
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 213.85 1874.68 14.12 SOUND_BLAST_DOOR_SLIDE_START
					openblastdoor_d8flag = 1
				ENDIF
				
				//sliding door
				IF openblastdoor_d8flag = 1

					IF rightdooropen_d8flag = 0
						IF SLIDE_OBJECT frontdoorr_a51 219.941 1874.571 13.903 0.1 0.0 0.0 FALSE
							//ADD_ONE_OFF_SOUND 219.941 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
							rightdooropen_d8flag = 1
						ENDIF
					ENDIF

					IF leftdooropen_d8flag = 0
						IF SLIDE_OBJECT frontdoorl_a51 207.842 1874.571 13.903 0.1 0.0 0.0 FALSE
							//ADD_ONE_OFF_SOUND 207.842 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
							leftdooropen_d8flag = 1
						ENDIF
					ENDIF

				ENDIF

				//stop audio for door
				IF openblastdoor_d8flag = 1
					IF rightdooropen_d8flag = 1
						IF leftdooropen_d8flag = 1
							//REMOVE_SOUND blastdoorsound_d8
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 213.85 1874.68 14.12 SOUND_BLAST_DOOR_SLIDE_STOP
							//RESTORE_CAMERA_JUMPCUT
							//SWITCH_WIDESCREEN OFF
							//SET_PLAYER_CONTROL PLAYER1 ON
							openblastdoor_d8flag = 2 
						ENDIF	
					ENDIF
				ENDIF
				
				//close it after a timer			
				IF openblastdoor_d8flag = 2 
					IF TIMERB > 30000
						//ADD_ONE_OFF_SOUND 213.69 1881.26 12.44 SOUND_POLICE_CELL_DOOR_CLUNK
						//ADD_CONTINUOUS_SOUND 213.69 1881.26 12.44 SOUND_POLICE_CELL_DOOR_SLIDING_LOOP blastdoorsound_d8
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 213.85 1874.68 14.12 SOUND_BLAST_DOOR_SLIDE_START
						openblastdoor_d8flag = 3
					ENDIF
				ENDIF

				//close doors
				IF openblastdoor_d8flag = 3
					
					IF rightdooropen_d8flag = 1
						IF SLIDE_OBJECT frontdoorr_a51 215.941 1874.571 13.903 0.1 0.0 0.0 FALSE
							//ADD_ONE_OFF_SOUND 215.941 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
							rightdooropen_d8flag = 0
						ENDIF
					ENDIF

					IF leftdooropen_d8flag = 1
						IF SLIDE_OBJECT frontdoorl_a51 211.842 1874.571 13.903 0.1 0.0 0.0 FALSE
							//ADD_ONE_OFF_SOUND 211.842 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
							leftdooropen_d8flag = 0
						ENDIF
					ENDIF

				ENDIF

				IF openblastdoor_d8flag = 3
					IF rightdooropen_d8flag = 0
						IF leftdooropen_d8flag = 0
							//REMOVE_SOUND blastdoorsound_d8
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 213.85 1874.68 14.12 SOUND_BLAST_DOOR_SLIDE_STOP
							REMOVE_BLIP blastdoor_d8blip
							ADD_BLIP_FOR_COORD 211.604 1811.091 20.8 blastdoor_d8blip //control tower			
							PRINT_NOW DST8_9 5000 1 //~s~The blast doors have shut make your way back to the ~y~control tower ~s~to open them again.
			 				openblastdoor_d8flag = 0
							rightdooropen_d8flag = 0
							rightdooropen_d8flag = 0
							dooropen_d8flag = 0
						ENDIF
					ENDIF
				ENDIF

			ENDIF

		ENDIF

	ELSE
		//shut everything down
		IF rightdooropen_d8flag = 5
			IF SLIDE_OBJECT frontdoorr_a51 215.941 1874.571 13.903 0.1 0.0 0.0 FALSE
				//ADD_ONE_OFF_SOUND 215.941 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
				rightdooropen_d8flag = 6
			ENDIF
		ENDIF

		IF leftdooropen_d8flag = 5
			IF SLIDE_OBJECT frontdoorl_a51 211.842 1874.571 13.903 0.1 0.0 0.0 FALSE
				//ADD_ONE_OFF_SOUND 211.842 1874.571 13.903 SOUND_POLICE_CELL_DOOR_CLUNK
				leftdooropen_d8flag = 6
			ENDIF
		ENDIF
		
	ENDIF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//set the searchlighst to clip if player is in a certain area.
	IF searchlightclip_d8flag = 0
		IF IS_CHAR_IN_AREA_3D scplayer 254.37 1850.1 21.7 207.08 1873.36 10.0 FALSE
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
				IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]
					SWITCH_ON_GROUND_SEARCHLIGHT spotlight_d8[spotlight_d8loop] FALSE
				ENDIF
				spotlight_d8loop++
			ENDWHILE
			searchlightclip_d8flag = 1
		ENDIF
	ENDIF

	IF searchlightclip_d8flag = 1
		IF NOT IS_CHAR_IN_AREA_3D scplayer 254.37 1850.1 21.7 207.08 1873.36 10.0 FALSE
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
				IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]
					SWITCH_ON_GROUND_SEARCHLIGHT spotlight_d8[spotlight_d8loop] TRUE
				ENDIF
				spotlight_d8loop++
			ENDWHILE
			searchlightclip_d8flag = 0
		ENDIF
	ENDIF
	
	//test if spotlights getting shot
	spotlight_d8loop = 0
	WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
		
		IF spotlightshot_d8flag[spotlight_d8loop] = 0
			IF DOES_OBJECT_EXIST spotlight_d8bulb[spotlight_d8loop]
				GET_OBJECT_HEALTH spotlight_d8bulb[spotlight_d8loop] spotlight_d8health
				IF spotlight_d8health < 5000
					IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]

						DELETE_SEARCHLIGHT spotlight_d8[spotlight_d8loop]
						
						PLAY_AND_KILL_FX_SYSTEM shootlightf_d8fx[spotlight_d8loop]
						//get coords for spotlight_d8bulb[spotlight_d8loop]
						//play fx system spotlight_d8bulb[spotlight_d8loop]

						spotlightshot_d8counter++
						spotlightshot_d8flag[spotlight_d8loop] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		spotlight_d8loop++
	ENDWHILE

	//test whether the player is in the searchlights
	IF trackingmode_d8flag = 0
		spotlight_d8loop = 0
		WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
		AND trackingmode_d8flag = 0
			IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]
				IF IS_CHAR_IN_SEARCHLIGHT spotlight_d8[spotlight_d8loop] scplayer
					//PRINT_NOW DST8_15 5000 1 //	(PLACEHOLDER)What was that possible intruder sighted. Set searchlights to tracking mode.
					spotlightstage_d8flag = 1
					stage1progressaudio_d8flag = 0
				  	GOSUB searchlightfollowplayer_d8label

					TIMERA = 0
					trackingmode_d8flag = 1
				ENDIF
			ENDIF
			spotlight_d8loop++
		ENDWHILE
	ENDIF


	//if player is spotted track with timer
	IF trackingmode_d8flag = 1
		IF TIMERA > 10000
			spotlight_d8loop = 0
			trackingmode_d8flag = 0
			WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8	
				IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]
					IF IS_CHAR_IN_SEARCHLIGHT spotlight_d8[spotlight_d8loop] scplayer
						trackingmode_d8flag = 2
					ENDIF
				ENDIF
				spotlight_d8loop++
			ENDWHILE

			IF trackingmode_d8flag = 0
				//PRINT_NOW DST8_16 5000 1 //(PLACEHOLDER)False sighting resume normal searchlights.
				spotlightstage_d8flag = 2
				stage2progressaudio_d8flag = 0
				GOSUB resetsearchlight_d8label
			ENDIF

		ENDIF
	ENDIF


	//player has been spotted permanently
	IF spotlightshot_d8counter > 2
	AND trackingmode_d8flag < 2
		trackingmode_d8flag = 2
	ENDIF


	IF trackingmode_d8flag = 2
		//remove tower stuff
		REMOVE_BLIP blastdoor_d8blip
		spotlightstage_d8flag = 3
		progressaudio_d8flag = 0
		
		//add blip for grate
		ADD_BLIP_FOR_COORD 246.0 1862.64 19.89 blastdoor_d8blip
		
		//tell control tower guys to kill player
		spotlight_d8loop = 15
		
		WHILE spotlight_d8loop < 22
			IF NOT IS_CHAR_DEAD army_d8[spotlight_d8loop]
				enemy_d8 = army_d8[spotlight_d8loop]
				GOSUB stayshoot_d8label
			ENDIF
			spotlight_d8loop++
		ENDWHILE
		

		GOSUB searchlightfollowplayer_d8label
		rightdooropen_d8flag = 5
		leftdooropen_d8flag = 5
		trackingmode_d8flag = 3
	ENDIF

	//remove blips and mark army guys as no longer needed if they are dead
	spotlight_d8loop = 0
	WHILE spotlight_d8loop < 22
		IF IS_CHAR_DEAD army_d8[spotlight_d8loop]
			IF DOES_BLIP_EXIST armyblips_d8[spotlight_d8loop]
				REMOVE_BLIP armyblips_d8[spotlight_d8loop]
				MARK_CHAR_AS_NO_LONGER_NEEDED army_d8[spotlight_d8loop]
			ENDIF
		ENDIF
		spotlight_d8loop++
	ENDWHILE

	//tell player about the grate
	IF trackingmode_d8flag = 3
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 246.0 1862.64 5.0 5.0 FALSE
			
			PRINT_HELP DST8_10 //Damage the ~y~vent grate ~w~and drop into it to gain entry.
			REMOVE_BLIP blastdoor_d8blip
			ADD_BLIP_FOR_COORD 224.99 1859.18 12.31 blastdoor_d8blip
			trackingmode_d8flag = 4
		ENDIF
	ENDIF

//dialogue
	
	GOSUB process_audio_d8
		
	//initial spotted
	IF spotlightstage_d8flag = 1
		IF stage1progressaudio_d8flag = 0
			IF handlingudio_d8flag = 0

				IF random1_ctr > 1
					audio_label_d8 = SOUND_DES8_BD //Code Blue! Searchlights set to tracking mode!
					$input_text_d8 = DES8_BD //Code Blue! Searchlights set to tracking mode!
					GOSUB load_audio_d8
				ENDIF
				IF random1_ctr = 1
					audio_label_d8 = SOUND_DES8_BB //Code Blue! Set the Searchlight perimeter to tracking mode!
					$input_text_d8 = DES8_BB //Code Blue! Set the Searchlight perimeter to tracking mode!
					GOSUB load_audio_d8
					random1_ctr++
				ENDIF
				IF random1_ctr = 0
					audio_label_d8 = SOUND_DES8_BA //Possible intruder sighted. Condition Blue! Set searchlights to tracking mode!
					$input_text_d8 = DES8_BA //Possible intruder sighted. Condition Blue! Set searchlights to tracking mode!
					GOSUB load_audio_d8
					random1_ctr++
				ENDIF

			ENDIF
		ENDIF
	ENDIF

	//go back to normal
	IF spotlightstage_d8flag = 2
		IF stage2progressaudio_d8flag = 0
			IF handlingudio_d8flag = 0

				IF random2_ctr > 1
					audio_label_d8 = SOUND_DES8_CC //Perimeter is secure. Standing down from Code Blue!
					$input_text_d8 = DES8_CC //Perimeter is secure. Standing down from Code Blue!
					GOSUB load_audio_d8
				ENDIF
				IF random2_ctr = 1
					audio_label_d8 = SOUND_DES8_CB //No further sign of intrusion. Stand down, repeat, stand down!
					$input_text_d8 = DES8_CB //No further sign of intrusion. Stand down, repeat, stand down!
					GOSUB load_audio_d8
					random2_ctr++
				ENDIF
				IF random2_ctr = 0
					audio_label_d8 = SOUND_DES8_CA //Sighting unconfirmed. Stand down from Condition Blue!
					$input_text_d8 = DES8_CA //Sighting unconfirmed. Stand down from Condition Blue!
					GOSUB load_audio_d8
					random2_ctr++
				ENDIF
	
			ENDIF
		ENDIF
	ENDIF

	//definately spotted
	IF spotlightstage_d8flag = 3
		IF progressaudio_d8flag = 0
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_DC //This is a code red! All personnel adopt intruder protocols!
				$input_text_d8 = DES8_DC //This is a code red! All personnel adopt intruder protocols!
				GOSUB load_audio_d8
			ENDIF
		ENDIF
		IF progressaudio_d8flag = 1
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_EE //All towers are cleared to use lethal force, repeat, lethal force!
				$input_text_d8 = DES8_EE //All towers are cleared to use lethal force, repeat, lethal force!
				GOSUB load_audio_d8
			ENDIF
		ENDIF
		IF progressaudio_d8flag = 2
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_ED //Lock down protocols initiated! Blast doors and security tower isolated!
				$input_text_d8 = DES8_ED //Lock down protocols initiated! Blast doors and security tower isolated!
				GOSUB load_audio_d8
			ENDIF
		ENDIF
	
		IF progressaudio_d8flag = 3
			PRINT_NOW DST8_14 7000 1 //~s~The control tower has been disabled you must now find another way in.  There is a ~y~vent that goes into the Area 69 interior.
			progressaudio_d8flag = 4
		ENDIF

	ENDIF

	//if player is inside
	IF inside_d8flag = 0
		IF IS_CHAR_IN_AREA_3D scplayer 207.917 1874.33 10.0 2224.917 1868.78 14.6 FALSE //through door
		OR LOCATE_CHAR_ON_FOOT_3D scplayer 224.99 1859.18 12.31 8.0 8.0 2.0 FALSE //through vent 
		OR IS_CHAR_IN_AREA_3D scplayer 201.99 1874.7 12.3 225.47 1851.43 14.31 FALSE //big area
		OR LOCATE_CHAR_ON_FOOT_3D scplayer 226.38 1872.33 12.93 4.0 4.0 3.5 FALSE

			DO_FADE 250 FADE_OUT

			CLEAR_MISSION_AUDIO 1
			CLEAR_PRINTS

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			DELETE_OBJECT a51ventcover
			CREATE_OBJECT a51_ventcoverb 245.968 1862.843 19.49 a51ventcover  // Put back the vent cover.
			SET_OBJECT_ROTATION a51ventcover 0.0 0.0 -140.998
			DONT_REMOVE_OBJECT a51ventcover
			
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL PLAYER1 OFF

			MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTBULB
			MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTHOUSING
			MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTBASE
			MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER

			UNLOAD_SPECIAL_CHARACTER 1
			REMOVE_CAR_RECORDING 340
			REMOVE_CAR_RECORDING 341
			MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
			MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED

			REQUEST_MODEL TUG
			REQUEST_MODEL WMOSCI
			REQUEST_MODEL SANCHEZ

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED TUG
				OR NOT HAS_MODEL_LOADED WMOSCI
				OR NOT HAS_MODEL_LOADED SANCHEZ
				WAIT 0
			ENDWHILE

			//remove any sounds

			//remove lights
			CLEAR_PRINTS
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < 7
				DELETE_SEARCHLIGHT spotlight_d8[spotlight_d8loop]
				DELETE_OBJECT spotlight_d8bulb[spotlight_d8loop]
				spotlight_d8loop++
			ENDWHILE

			DELETE_OBJECT spotlight1_d8housing
			DELETE_OBJECT spotlight1_d8base
			DELETE_OBJECT spotlight2_d8housing
			DELETE_OBJECT spotlight2_d8base
			DELETE_OBJECT spotlight3_d8housing
			DELETE_OBJECT spotlight3_d8base
			DELETE_OBJECT spotlight4_d8housing
			DELETE_OBJECT spotlight4_d8base
			DELETE_OBJECT spotlight5_d8housing
			DELETE_OBJECT spotlight5_d8base
			DELETE_OBJECT spotlight6_d8housing
			DELETE_OBJECT spotlight6_d8base
			DELETE_OBJECT spotlight7_d8housing
			DELETE_OBJECT spotlight7_d8base

			//remove peds
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < 22
				DELETE_CHAR army_d8[spotlight_d8loop]
				REMOVE_BLIP armyblips_d8[spotlight_d8loop]
				spotlight_d8loop++
			ENDWHILE


			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 225.0 1859.0 5.0 5.0 FALSE
				SET_CHAR_COORDINATES scplayer 224.44 1859.34 12.14
				SET_CHAR_HEADING scplayer 6.82
			ELSE
				SET_CHAR_COORDINATES scplayer 214.14 1872.388 12.14
				SET_CHAR_HEADING scplayer 267.68
			ENDIF

			//reset blast doors
			DELETE_OBJECT frontdoorr_a51
			DELETE_OBJECT frontdoorl_a51
			CREATE_OBJECT_NO_OFFSET a51_blastdoorR 215.941 1874.571 13.903 frontdoorr_a51
			
			
			SET_OBJECT_HEADING frontdoorr_a51 0.0
			CREATE_OBJECT_NO_OFFSET a51_blastdoorL 211.842 1874.571 13.903 frontdoorl_a51
			SET_OBJECT_HEADING frontdoorl_a51 0.0
			DONT_REMOVE_OBJECT frontdoorr_a51
			DONT_REMOVE_OBJECT frontdoorl_a51

			REMOVE_BLIP blastdoor_d8blip


			////////////////////////	create guys in first room

			//right side between boxes looking out
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 248.128 1860.732 8.76 army_d8[1]
			SET_CHAR_HEADING army_d8[1] 266.82
			SET_CHAR_DECISION_MAKER army_d8[1] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[1] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[1] 5.0
			ADD_BLIP_FOR_CHAR army_d8[1] armyblips_d8[1]
			CHANGE_BLIP_DISPLAY armyblips_d8[1] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[1] 5.0 1
			TASK_PLAY_ANIM army_d8[1] ROADCROSS PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[1] TRUE

			//standing still by stairs
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 245.1791 1879.5707 10.4609 army_d8[2]
			SET_CHAR_HEADING army_d8[2] 266.1505
			SET_CHAR_DECISION_MAKER army_d8[2] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[2] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[2] 5.0
			SET_CHAR_ACCURACY army_d8[2] 80
			ADD_BLIP_FOR_CHAR army_d8[2] armyblips_d8[2]
			CHANGE_BLIP_DISPLAY armyblips_d8[2] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[2] 5.0 1
			TASK_PLAY_ANIM army_d8[2] ROADCROSS PED 4.0 TRUE FALSE FALSE FALSE -1			
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[2] TRUE

			//right side looking out
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 245.1999 1863.9520 7.5618 army_d8[3]
			SET_CHAR_HEADING army_d8[3] 175.5655
			SET_CHAR_DECISION_MAKER army_d8[3] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[3] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[3] 5.0
			ADD_BLIP_FOR_CHAR army_d8[3] armyblips_d8[3]
			CHANGE_BLIP_DISPLAY armyblips_d8[3] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[3] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[3] TRUE

			//walking around warhead
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 261.9116 1870.7275 7.7578 army_d8[4]
			SET_CHAR_DECISION_MAKER army_d8[4] desert8stealth_DM
			SET_CHAR_HEADING army_d8[4] 186.0656
			GIVE_WEAPON_TO_CHAR army_d8[4] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[4] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 261.6897 1860.4635 7.7650 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 256.1913 1859.6267 7.7650 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 256.0048 1870.2073 7.7900 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 261.9116 1870.7275 7.7578 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[4] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[4] armyblips_d8[4]
			CHANGE_BLIP_DISPLAY armyblips_d8[4] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[4] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[4] TRUE

			//vehicle
			CREATE_CAR TUG 241.0877 1843.9514 7.7578 car_d8
			SET_CAR_HEADING car_d8 268.7216 

			//patrol by vehicle
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 243.0288 1846.4508 7.7650 army_d8[5]
			SET_CHAR_DECISION_MAKER army_d8[5] desert8stealth_DM
			SET_CHAR_HEALTH army_d8[5] 10
			SET_CHAR_HEADING army_d8[5] 270.7867
			GIVE_WEAPON_TO_CHAR army_d8[5] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[5] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 255.3062 1846.2761 7.7734 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 243.0288 1846.4508 7.7650 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[5] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[5] armyblips_d8[5]
			CHANGE_BLIP_DISPLAY armyblips_d8[5] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[5] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[5] TRUE

			ADD_BLIP_FOR_COORD 267.015 1862.51 7.76 jetpack_d8blip
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR jetpack_d8blip TRUE
			PLAYER_TAKE_OFF_GOGGLES PLAYER1 FALSE
			LOAD_SCENE 248.83 1827.63 7.46
			LOAD_SCENE_IN_DIRECTION 282.28 1814.04 7.54 326.0

			DO_FADE 500 FADE_IN

			SET_RADAR_ZOOM 100
			//SET_DARKNESS_EFFECT FALSE -1

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 248.925 1832.315 6.55 cutchar_d8
			SET_CHAR_HEADING cutchar_d8 1.5
			GIVE_WEAPON_TO_CHAR cutchar_d8 WEAPONTYPE_M4 9999
			TASK_PLAY_ANIM cutchar_d8 ROADCROSS PED 4.0 FALSE FALSE FALSE FALSE 1000
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			SKIP_CUTSCENE_START
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			SET_FIXED_CAMERA_POSITION 278.6251 1812.8367 4.8301 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 279.2745 1813.5579 5.0711 JUMP_CUT

			PRINT_NOW DST8_42 5000 1 //~s~Make your way through Area 69 to the research lab which will give you access to the black project.

			TIMERA = 0

			WHILE TIMERA < 5000
				   			   
			WAIT 0
		
			ENDWHILE
			
			LOAD_SCENE_IN_DIRECTION 255.93 1838.05 7.73 142.0
						
			SET_FIXED_CAMERA_POSITION 252.5037 1836.2418 8.5554 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 252.0391 1835.3632 8.4453 JUMP_CUT

			PRINT_NOW DST8_43 8000 1 //~s~There are several military personnel on patrol throughout the installation.

			TIMERA = 0

			WHILE TIMERA < 4000
				   			   
			WAIT 0
		
			ENDWHILE


			LOAD_SCENE 249.6 1869.03 11.0

			SET_FIXED_CAMERA_POSITION 262.0718 1869.7272 13.1620 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 261.4835 1868.9966 12.8154 JUMP_CUT

			TIMERA = 0

			WHILE TIMERA < 4000
				   			   
			WAIT 0
		
			ENDWHILE

			LOAD_SCENE_IN_DIRECTION 223.75 1822.57 7.51 68.0

			SET_FIXED_CAMERA_POSITION 217.0121 1822.6581 6.4205 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 216.0150 1822.6718 6.4959 JUMP_CUT
			
			DELETE_CHAR cutchar_d8

			PRINT_NOW DST8_44 5000 1 //~s~The control room will allow you to deactivate some of Area 69's exterior defences.

			TIMERA = 0

			WHILE TIMERA < 5000
				   			   
			WAIT 0
		
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			SKIP_CUTSCENE_END			
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			DELETE_CHAR cutchar_d8
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			PRINT_NOW DST8_45 5000 1 //~s~Go get the ~g~black project~s~.
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_CLAXON_START
			TIMERB = 0
			audioinside_d8flag = 1
			progressaudio_d8flag = 0
			handlingudio_d8flag = 0
			//cut scenes
			outsidearea51_d8flag = 6
			inside_d8flag = 1
		ENDIF
	ENDIF

	
	

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Inside Area 51	  //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//imran
 
//player is in second room
IF inside_d8flag = 1

	IF audioinside_d8flag = 1
		IF TIMERB > 5000
			audioinside_d8flag = 2
		ENDIF
	ENDIF

	IF audioinside_d8flag = 2

		GOSUB process_audio_d8	

		IF room_d8flag < 3

			IF progressaudio_d8flag = 0
				IF handlingudio_d8flag = 0
					audio_label_d8 = SOUND_DES8_FB //Installation personnel please be aware we are at Condition Red. This is not a drill!
					$input_text_d8 = DES8_FB //Installation personnel please be aware we are at Condition Red. This is not a drill!
					GOSUB load_audio_d8
				ENDIF
			ENDIF
			IF progressaudio_d8flag = 1
				IF handlingudio_d8flag = 0
					audio_label_d8 = SOUND_DES8_FC //This facility has been breached, all security personnel head to the research labs now!
					$input_text_d8 = DES8_FC //This facility has been breached, all security personnel head to the research labs now!
					GOSUB load_audio_d8
					TIMERB = 0
				ENDIF
			ENDIF

			//text below should not come up
			IF TIMERB > 10000
				IF progressaudio_d8flag = 2
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GD //Personnel are reminded that the downloading of pornography is strictly prohibited.
						$input_text_d8 = DUMMY //Personnel are reminded that the downloading of pornography is strictly prohibited.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 7500
				IF progressaudio_d8flag = 3
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GE //The Composites Lab would like to remind Diagnostics that their fridge is off limits to Diagnostics staff!
						$input_text_d8 = DUMMY //The Composites Lab would like to remind Diagnostics that their fridge is off limits to Diagnostics staff!
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 5500
				IF progressaudio_d8flag = 4
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GJ //Diagnostics would like to point out that they know that the Composites Lab stole their coffee last weekend.
						$input_text_d8 = DUMMY //Diagnostics would like to point out that they know that the Composites Lab stole their coffee last weekend.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 8000
				IF progressaudio_d8flag = 5
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GA //All personnel wishing to go to Shezan tonight, please inform reception at your earliest convenience.
						$input_text_d8 = DUMMY //All personnel wishing to go to Shezan tonight, please inform reception at your earliest convenience.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 12000
				IF progressaudio_d8flag = 6
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GK //Could whoever stole the Composite Lab’s mugs please return them.
						$input_text_d8 = DUMMY //Could whoever stole the Composite Lab’s mugs please return them.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 12500
				IF progressaudio_d8flag = 7
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GG //The Globular Deli have arrived in the canteen.
						$input_text_d8 = DUMMY //The Globular Deli have arrived in the canteen.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 12000
				IF progressaudio_d8flag = 8
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GC //Tonight’s menu is posted in the canteen. Barf bags can be ordered from human resources.
						$input_text_d8 = DUMMY //Tonight’s menu is posted in the canteen. Barf bags can be ordered from human resources.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 25000
				IF progressaudio_d8flag = 9
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GL //Could the owner of the alien culture in the Diagnostics Lab’s fridge, kindly remove it please!
						$input_text_d8 = DUMMY //Could the owner of the alien culture in the Diagnostics Lab’s fridge, kindly remove it please!
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 15000
				IF progressaudio_d8flag = 10
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GM //Personnel found stealing alien technology will NOT be invited on the next staff night out!
						$input_text_d8 = DUMMY //Personnel found stealing alien technology will NOT be invited on the next staff night out!
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 20000
				IF progressaudio_d8flag = 11
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GF	//Can personnel please make sure that they shut all the windows before they leave tonight!
						$input_text_d8 = DUMMY	//Can personnel please make sure that they shut all the windows before they leave tonight!
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 15000
				IF progressaudio_d8flag = 12
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GN //Could all personnel remember that the quarantine breach alarm test has now been moved to Monday mornings.
						$input_text_d8 = DUMMY //Could all personnel remember that the quarantine breach alarm test has now been moved to Monday mornings.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 10000
				IF progressaudio_d8flag = 13
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GB //Personnel are reminded that running in corridors whilst holding scissors is NOT permitted.
						$input_text_d8 = DUMMY //Personnel are reminded that running in corridors whilst holding scissors is NOT permitted.
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERB > 12000
				IF progressaudio_d8flag = 14
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_GH //The Cola machine has now been re-filled!
						$input_text_d8 = DUMMY //The Cola machine has now been re-filled!
						GOSUB load_audio_d8
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF

		ENDIF

		IF jetdoor_d8flag = 2

			IF progressaudio_d8flag = 0
				IF TIMERB > 2000
					IF handlingudio_d8flag = 0
						audio_label_d8 = SOUND_DES8_HA //Code red, code red!
						$input_text_d8 = DST8_34 //Code red, code red!
						GOSUB load_audio_d8
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_d8flag = 1
				IF handlingudio_d8flag = 0
					audio_label_d8 = SOUND_DES8_HB	//Intruder has penetrated the project! All military personnel to the launch bay IMMEDIATELY!”	
					$input_text_d8 = DES8_HB	//Intruder has penetrated the project! All military personnel to the launch bay IMMEDIATELY!”	
					GOSUB load_audio_d8
				ENDIF
			ENDIF

		ENDIF


	ENDIF


	IF room_d8flag = 0
		IF IS_CHAR_IN_AREA_3D scplayer 266.53 1849.53 8.0 237.01 1837.55 10.52 FALSE
			//main walk way
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 248.5208 1806.6808 6.5619 army_d8[6]
			SET_CHAR_DECISION_MAKER army_d8[6] desert8stealth_DM
			SET_CHAR_HEADING army_d8[6] 359.2584
			GIVE_WEAPON_TO_CHAR army_d8[6] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[6] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 248.8298 1835.7720 6.5471 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 248.5208 1809.6808 6.5619 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[6] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[6] armyblips_d8[6]
			CHANGE_BLIP_DISPLAY armyblips_d8[6] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[6] 80
//			SET_INFORM_RESPECTED_FRIENDS army_d8[6] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[6] TRUE

			//standing opposite road entrance
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 254.1943 1835.3647 3.7175 army_d8[7]
			SET_CHAR_HEADING army_d8[7] 271.2598
			SET_CHAR_DECISION_MAKER army_d8[7] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[7] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[7] 5.0
			ADD_BLIP_FOR_CHAR army_d8[7] armyblips_d8[7]
			CHANGE_BLIP_DISPLAY armyblips_d8[7] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[7] 5.0 1
			TASK_PLAY_ANIM army_d8[7] ROADCROSS PED 4.0 TRUE FALSE FALSE FALSE 6000
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[7] TRUE
			SET_CHAR_ACCURACY army_d8[7] 80

			//walking down the bottom
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 249.41 1825.84 4.7 army_d8[8]
			SET_CHAR_DECISION_MAKER army_d8[8] desert8stealth_DM
			SET_CHAR_HEADING army_d8[8] 180.5658
			GIVE_WEAPON_TO_CHAR army_d8[8] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[8] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 249.63 1816.64 4.71 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 249.41 1825.84 4.7 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[8] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[8] armyblips_d8[8]
			CHANGE_BLIP_DISPLAY armyblips_d8[8] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[8] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[8] TRUE
	 
			//near hq room
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 236.5669 1813.1033 6.4213 army_d8[9]
			SET_CHAR_DECISION_MAKER army_d8[9] desert8stealth_DM
			SET_CHAR_HEADING army_d8[9] 167.7513
			GIVE_WEAPON_TO_CHAR army_d8[9] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[9] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 236.0416 1803.5186 6.4219 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 243.1650 1803.0487 6.4219 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 236.0416 1803.5186 6.4219 ROADCROSS PED
			EXTEND_PATROL_ROUTE 236.5669 1813.1033 6.4213 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[9] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[9] armyblips_d8[9]
			CHANGE_BLIP_DISPLAY armyblips_d8[9] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[9] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[9] TRUE

			//right walkway
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 284.416 1816.59 3.8 army_d8[11]
			SET_CHAR_DECISION_MAKER army_d8[11] desert8stealth_DM
			SET_CHAR_HEADING army_d8[11] 83.92  
			GIVE_WEAPON_TO_CHAR army_d8[11] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[11] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 265.829 1816.31 4.7 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 284.416 1816.59 4.72 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[11] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[11] armyblips_d8[11]
			CHANGE_BLIP_DISPLAY armyblips_d8[11] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[11] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[11] TRUE


			//scientist on walkway
			CREATE_CHAR PEDTYPE_MISSION1 WMOSCI 245.3671 1830.2896 6.5547 army_d8[12]
			SET_CHAR_HEADING army_d8[12] 178.5107
			SET_CHAR_DECISION_MAKER army_d8[12] desert8coward_DM
			SET_CHAR_HEALTH army_d8[12] 10
			TASK_PLAY_ANIM army_d8[12] ATM PED 4.0 TRUE FALSE FALSE FALSE -1

			//right in HQ
			CREATE_CHAR PEDTYPE_MISSION1 WMOSCI 213.3669 1825.5109 5.4141 army_d8[13]
			SET_CHAR_HEADING army_d8[13] 67.6565
			SET_CHAR_DECISION_MAKER army_d8[13] desert8coward_DM
			SET_CHAR_HEALTH army_d8[13] 10
			TASK_PLAY_ANIM army_d8[13] ATM PED 4.0 TRUE FALSE FALSE FALSE -1

			//left in HQ
			CREATE_CHAR PEDTYPE_MISSION1 WMOSCI 214.1768 1818.7389 5.4216 army_d8[14]
			SET_CHAR_HEADING army_d8[14] 113.5239
			SET_CHAR_DECISION_MAKER army_d8[14] desert8coward_DM
			SET_CHAR_HEALTH army_d8[14] 10
			TASK_PLAY_ANIM army_d8[14] ATM PED 4.0 TRUE FALSE FALSE FALSE -1

			//213.4856 1822.8217 5.4216 92.7748 locate to switch off sams
			//keycard
			room_d8flag = 1
		ENDIF
	ENDIF

	//corridors
	IF room_d8flag = 1
		IF IS_CHAR_IN_AREA_3D scplayer 301.6813 1821.8008 7.1396 272.5243 1826.9480 10.3607 FALSE

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 280.7216 1840.1617 6.7266 army_d8[15]
			SET_CHAR_DECISION_MAKER army_d8[15] desert8stealth_DM
			SET_CHAR_HEADING army_d8[15] 262.0249
			GIVE_WEAPON_TO_CHAR army_d8[15] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[15] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 292.8436 1839.7808 6.7266 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 280.7216 1840.1617 6.7266 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[15] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[15] armyblips_d8[15]
			CHANGE_BLIP_DISPLAY armyblips_d8[15] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[15] 80
//			SET_INFORM_RESPECTED_FRIENDS army_d8[15] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[15] TRUE

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 301.3004 1839.9022 6.7266 army_d8[16]
			SET_CHAR_DECISION_MAKER army_d8[16] desert8stealth_DM
			SET_CHAR_HEADING army_d8[16] 91.0904
			GIVE_WEAPON_TO_CHAR army_d8[16] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[16] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 328.5270 1839.6735 6.8281 ROADCROSS PED 
			EXTEND_PATROL_ROUTE 301.3004 1839.9022 6.7266 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[16] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[16] armyblips_d8[16]
			SET_CHAR_ACCURACY army_d8[16] 80
			CHANGE_BLIP_DISPLAY armyblips_d8[16] BLIP_ONLY
//			SET_INFORM_RESPECTED_FRIENDS army_d8[16] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[16] TRUE						

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 329.3857 1855.2948 6.8357 army_d8[17]
			SET_CHAR_DECISION_MAKER army_d8[17] desert8stealth_DM
			SET_CHAR_HEADING army_d8[17] 272.6715
			GIVE_WEAPON_TO_CHAR army_d8[17] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[17] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 317.4110 1855.2742 6.7341 ROADCROSS PED
			EXTEND_PATROL_ROUTE 329.3857 1855.2948 6.8357 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[17] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[17] armyblips_d8[17]
			CHANGE_BLIP_DISPLAY armyblips_d8[17] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[17] 80
//			SET_INFORM_RESPECTED_FRIENDS army_d8[17] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[17] TRUE

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 298.3582 1856.0538 6.8281 army_d8[18]
			SET_CHAR_DECISION_MAKER army_d8[18] desert8stealth_DM
			SET_CHAR_HEADING army_d8[18] 167.8933
			GIVE_WEAPON_TO_CHAR army_d8[18] WEAPONTYPE_M4 9999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[18] 5.0
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE 297.3482 1868.0016 7.7578 ROADCROSS PED
			EXTEND_PATROL_ROUTE 298.3582 1856.0538 6.8281 ROADCROSS PED 
			TASK_FOLLOW_PATROL_ROUTE army_d8[18] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ADD_BLIP_FOR_CHAR army_d8[18] armyblips_d8[18]
			CHANGE_BLIP_DISPLAY armyblips_d8[18] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[18] 80
//			SET_INFORM_RESPECTED_FRIENDS army_d8[18] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[18] TRUE

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 313.7065 1845.5508 6.7341 army_d8[19]
			SET_CHAR_HEADING army_d8[19] 2.8719 
			SET_CHAR_DECISION_MAKER army_d8[19] desert8stealth_DM
			GIVE_WEAPON_TO_CHAR army_d8[19] WEAPONTYPE_M4 99999
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE army_d8[19] 5.0
			ADD_BLIP_FOR_CHAR army_d8[19] armyblips_d8[19]
			CHANGE_BLIP_DISPLAY armyblips_d8[19] BLIP_ONLY
			SET_CHAR_ACCURACY army_d8[19] 80
//			SET_INFORM_RESPECTED_FRIENDS army_d8[19] 5.0 1
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[19] TRUE

			CREATE_OBJECT_NO_OFFSET a51_labdoor 268.66 1864.059 7.5 jetdoor_d8
			DONT_REMOVE_OBJECT jetdoor_d8
			SET_OBJECT_PROOFS jetdoor_d8 TRUE TRUE TRUE TRUE TRUE
			
			room_d8flag = 2
		ENDIF
	ENDIF

	//disable sam site
	IF samsite_d8flag = 0
		IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 212.93 1822.82 5.41 1.0 1.0 3.0 TRUE
			TIMERA = 0
			SET_PLAYER_CONTROL PLAYER1 OFF
			SET_CHAR_HEADING scplayer 92.24
			TASK_PLAY_ANIM scplayer ATM PED 8.0 FALSE FALSE FALSE FALSE 500
			samsite_d8flag = 1
		ENDIF
	ENDIF
	IF samsite_d8flag = 1
		IF TIMERA > 500
			CLEAR_CHAR_TASKS scplayer
			PRINT_NOW DES8_21 4000 1 //SAM sites disabled.
			SET_AREA51_SAM_SITE OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			samsite_d8flag = 2
		ENDIF
	ENDIF


	//remove blips and mark army guys as no longer needed if they are dead
	spotlight_d8loop = 0
	WHILE spotlight_d8loop < 22
		IF IS_CHAR_DEAD army_d8[spotlight_d8loop]
			IF DOES_BLIP_EXIST armyblips_d8[spotlight_d8loop]
				REMOVE_BLIP armyblips_d8[spotlight_d8loop]
				MARK_CHAR_AS_NO_LONGER_NEEDED army_d8[spotlight_d8loop]
			ENDIF
		ENDIF
		spotlight_d8loop++
	ENDWHILE


	IF room_d8flag = 2
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 287.0 1870.0 9.0 6.0 6.0 6.0 FALSE
			PRINT_NOW DST8_46 5000 1 //~s~The ~y~door to the launch bay where the black project is being stored is locked. Find a keycard to access it.
			CREATE_CHAR PEDTYPE_MISSION1 WMOSCI 276.57 1854.53 7.75 sci_d8
			SET_CHAR_HEADING sci_d8 175.15
			SET_CHAR_DECISION_MAKER sci_d8 desert8empty_DM 
			SET_CHAR_HEALTH sci_d8 10
			TASK_STAY_IN_SAME_PLACE sci_d8 TRUE
			TASK_PLAY_ANIM sci_d8 ATM PED 8.0 TRUE FALSE FALSE FALSE 1000
			sci_d8flag = 1
			room_d8flag = 3
		ENDIF
	ENDIF

	//scientist dropping keycard
	IF sci_d8flag = 1

		IF NOT IS_CHAR_DEAD sci_d8
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sci_d8 scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 sci_d8
			OR IS_CHAR_SHOOTING_IN_AREA scplayer 282.25 1852.36 264.28 1863.69 FALSE
			OR IS_CHAR_IN_AREA_2D scplayer 282.25 1852.36 264.28 1863.69 FALSE
				TASK_HANDS_UP sci_d8 20000
				IF HAS_MISSION_AUDIO_LOADED 2
					ATTACH_MISSION_AUDIO_TO_CHAR 2 sci_d8
					PLAY_MISSION_AUDIO 2
					PRINT_NOW DES8_AA 2000 1//Please, no more violence! Take my pass card and go!
				ENDIF
				CREATE_PICKUP KEYCARD PICKUP_ONCE 276.57 1854.53 8.75 keycard_d8
				SET_CHAR_DECISION_MAKER sci_d8 desert8coward_DM
				ADD_BLIP_FOR_PICKUP keycard_d8 keycard_d8blip
				PRINT DST8_25 5000 1 //~s~Collect the ~g~keycard ~S~and open the ~y~door ~s~to where the Black project is located.
				sci_d8flag = 2
			ENDIF
		ELSE
			CREATE_PICKUP KEYCARD PICKUP_ONCE 276.57 1854.53 8.75 keycard_d8
			ADD_BLIP_FOR_PICKUP keycard_d8 keycard_d8blip
			PRINT_NOW DST8_25 5000 1 //~s~Collect the ~g~keycard ~S~and open the ~y~door ~s~to where the Black project is located.
			sci_d8flag = 2
		ENDIF

	ENDIF

	IF sci_d8flag = 2

		IF IS_CHAR_DEAD sci_d8
			IF scishot_d8flag = 0
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT DST8_25
				scishot_d8flag = 1
			ENDIF
		ENDIF

		IF HAS_PICKUP_BEEN_COLLECTED keycard_d8
			REMOVE_BLIP keycard_d8blip
			sci_d8flag = 3
		ENDIF
	ENDIF

	//
	IF room_d8flag = 3
		IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 267.015 1862.51 7.76 1.0 1.0 5.0 TRUE
			IF sci_d8flag = 3
				SET_CHAR_HEADING scplayer 6.3
				TIMERA = 0
				SET_PLAYER_CONTROL PLAYER1 OFF
				TASK_PLAY_ANIM scplayer ATM PED 8.0 FALSE FALSE FALSE FALSE 500
				room_d8flag = 4
			ELSE
				PRINT_NOW DST8_24 5000 1 //You need to get a keycard to open this door.				
			ENDIF
		ENDIF
	ENDIF
	IF room_d8flag = 4
		IF DOES_OBJECT_EXIST jetdoor_d8
			IF TIMERA > 500
				jetdoor_d8flag = 1
				IF DOES_OBJECT_EXIST jetdoor_d8
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT jetdoor_d8 SOUND_SHUTTER_DOOR_START
				ENDIF
			
				//main walkway
				DELETE_CHAR army_d8[6]
				//near hq room
				DELETE_CHAR army_d8[9] 
				//scientist on walkway
				DELETE_CHAR army_d8[12]
				//right in HQ
				DELETE_CHAR army_d8[13]
				//left in HQ
				DELETE_CHAR army_d8[14]
				//right side between boxes looking out
				DELETE_CHAR army_d8[1]
				//standing still by stairs
				DELETE_CHAR army_d8[2]
				//right side looking out
				DELETE_CHAR army_d8[3]
				//walking around warhead
				DELETE_CHAR army_d8[4]
				//patrol by vehicle
				DELETE_CHAR army_d8[5]

				CLEAR_CHAR_TASKS scplayer  
				SET_PLAYER_CONTROL PLAYER1 ON

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 261.5664 1891.8499 7.4446 army_d8[6]
				SET_CHAR_DECISION_MAKER army_d8[6] desert8empty_DM
				SET_CHAR_HEADING army_d8[6] 212.7731 
				GIVE_WEAPON_TO_CHAR army_d8[6] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[6] TRUE
				SET_CHAR_HEALTH army_d8[6] 150
				SET_CHAR_ACCURACY army_d8[6] 80
				ADD_BLIP_FOR_CHAR army_d8[6] armyblips_d8[6]
				CHANGE_BLIP_DISPLAY armyblips_d8[6] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[6] TRUE
				enemy_d8 = army_d8[6]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 268.4905 1890.0736 4.8750 army_d8[9]
				SET_CHAR_DECISION_MAKER army_d8[9] desert8empty_DM
				SET_CHAR_HEADING army_d8[9] 176.3921 
				GIVE_WEAPON_TO_CHAR army_d8[9] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[9] TRUE
				SET_CHAR_HEALTH army_d8[9] 150
				SET_CHAR_ACCURACY army_d8[9] 30
				ADD_BLIP_FOR_CHAR army_d8[9] armyblips_d8[9]
				CHANGE_BLIP_DISPLAY armyblips_d8[9] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[9] TRUE
				enemy_d8 = army_d8[9]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 274.3991 1880.7250 -1.3518 army_d8[13]
				SET_CHAR_DECISION_MAKER army_d8[13] desert8empty_DM
				SET_CHAR_HEADING army_d8[13] 65.8577  
				GIVE_WEAPON_TO_CHAR army_d8[13] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[13] TRUE
				SET_CHAR_HEALTH army_d8[13] 150
				SET_CHAR_ACCURACY army_d8[13] 80
				ADD_BLIP_FOR_CHAR army_d8[13] armyblips_d8[13]
				CHANGE_BLIP_DISPLAY armyblips_d8[13] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[13] TRUE
				enemy_d8 = army_d8[13]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 268.9641 1878.0194 -4.5078 army_d8[14]
				SET_CHAR_DECISION_MAKER army_d8[14] desert8empty_DM
				SET_CHAR_HEADING army_d8[14] 352.3847  
				GIVE_WEAPON_TO_CHAR army_d8[14] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[14] TRUE
				SET_CHAR_HEALTH army_d8[14] 150
				SET_CHAR_ACCURACY army_d8[14] 40
				ADD_BLIP_FOR_CHAR army_d8[14] armyblips_d8[14]
				CHANGE_BLIP_DISPLAY armyblips_d8[14] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[14] TRUE
				enemy_d8 = army_d8[14]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 262.9983 1883.6240 -9.2031 army_d8[1]
				SET_CHAR_DECISION_MAKER army_d8[1] desert8empty_DM
				SET_CHAR_HEADING army_d8[1] 259.5976  
				GIVE_WEAPON_TO_CHAR army_d8[1] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[1] TRUE
				SET_CHAR_HEALTH army_d8[1] 100
				SET_CHAR_ACCURACY army_d8[1] 80
				ADD_BLIP_FOR_CHAR army_d8[1] armyblips_d8[1]
				CHANGE_BLIP_DISPLAY armyblips_d8[1] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[1] TRUE
				enemy_d8 = army_d8[1]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 268.0892 1889.7094 -13.8984 army_d8[2]
				SET_CHAR_DECISION_MAKER army_d8[2] desert8empty_DM
				SET_CHAR_HEADING army_d8[2] 197.1955  
				GIVE_WEAPON_TO_CHAR army_d8[2] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[2] TRUE
				SET_CHAR_HEALTH army_d8[2] 150
				SET_CHAR_ACCURACY army_d8[2] 25
				ADD_BLIP_FOR_CHAR army_d8[2] armyblips_d8[2]
				CHANGE_BLIP_DISPLAY armyblips_d8[2] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[2] TRUE
				enemy_d8 = army_d8[2]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 274.8945 1884.5248 -18.5859 army_d8[3]
				SET_CHAR_DECISION_MAKER army_d8[3] desert8empty_DM
				SET_CHAR_HEADING army_d8[3] 117.7696  
				GIVE_WEAPON_TO_CHAR army_d8[3] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[3] TRUE
				SET_CHAR_HEALTH army_d8[3] 120
				SET_CHAR_ACCURACY army_d8[3] 80
				ADD_BLIP_FOR_CHAR army_d8[3] armyblips_d8[3]
				CHANGE_BLIP_DISPLAY armyblips_d8[3] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[3] TRUE
				enemy_d8 = army_d8[3]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 270.4637 1878.0315 -22.7266 army_d8[4]
				SET_CHAR_DECISION_MAKER army_d8[4] desert8empty_DM
				SET_CHAR_HEADING army_d8[4] 117.7696  
				GIVE_WEAPON_TO_CHAR army_d8[4] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[4] TRUE
				SET_CHAR_HEALTH army_d8[4] 150
				SET_CHAR_ACCURACY army_d8[4] 85
				ADD_BLIP_FOR_CHAR army_d8[4] armyblips_d8[4]
				CHANGE_BLIP_DISPLAY armyblips_d8[4] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[4] TRUE
				enemy_d8 = army_d8[4]
				GOSUB stayshoot_d8label

				CREATE_CHAR PEDTYPE_MISSION1 ARMY 262.6877 1883.4840 -27.9766 army_d8[5]
				SET_CHAR_DECISION_MAKER army_d8[5] desert8empty_DM
				SET_CHAR_HEADING army_d8[5] 117.7696  
				GIVE_WEAPON_TO_CHAR army_d8[5] WEAPONTYPE_M4 9999
				TASK_STAY_IN_SAME_PLACE army_d8[5] TRUE
				SET_CHAR_HEALTH army_d8[5] 150
				SET_CHAR_ACCURACY army_d8[5] 80
				ADD_BLIP_FOR_CHAR army_d8[5] armyblips_d8[5]
				CHANGE_BLIP_DISPLAY armyblips_d8[5] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR armyblips_d8[5] TRUE
				enemy_d8 = army_d8[5]
				GOSUB stayshoot_d8label

				//vehicle
				MARK_CAR_AS_NO_LONGER_NEEDED car_d8
				MARK_MODEL_AS_NO_LONGER_NEEDED TUG

				//create guys
								
				//pickup
				CREATE_PICKUP_WITH_AMMO JETPACK PICKUP_ONCE 0 268.7 1884.1 -30.085 jetpack_d8
				REMOVE_BLIP jetpack_d8blip
				ADD_BLIP_FOR_PICKUP jetpack_d8 jetpack_d8blip
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR jetpack_d8blip TRUE
				CLEAR_PRINTS
				PRINT_NOW DST8_34 5000 1 //~s~The Black Project is located at the bottom of the launchbay.
				room_d8flag = 5		
			ENDIF
		ENDIF
	ENDIF

	IF jetdoor_d8flag = 1
		IF DOES_OBJECT_EXIST jetdoor_d8
			IF SLIDE_OBJECT jetdoor_d8 268.66 1864.059 12.2 0.0 0.0 0.08 FALSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT jetdoor_d8 SOUND_SHUTTER_DOOR_STOP
				progressaudio_d8flag = 0
				handlingudio_d8flag = 0
				TIMERB = 0
				jetdoor_d8flag = 2
			ENDIF
		ENDIF
	ENDIF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////	Launch bay Area 51	  //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	IF room_d8flag = 5
		IF HAS_PICKUP_BEEN_COLLECTED jetpack_d8
			REMOVE_BLIP jetpack_d8blip
			SET_PLAYER_CONTROL PLAYER1 OFF
			SWITCH_WIDESCREEN ON
			SET_TIME_OF_DAY 9 00
			SET_CHAR_COORDINATES scplayer 268.591 1884.469 -31.0869
			SET_CHAR_HEADING scplayer 180.99
			TASK_JETPACK scplayer
			SET_FIXED_CAMERA_POSITION 268.5177 1886.2970 -29.5611 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 268.5393 1885.3157 -29.7523 JUMP_CUT
			SET_INTERPOLATION_PARAMETERS 0.0 3000
			REQUEST_MODEL MICRO_UZI
			REQUEST_MODEL CAMPER
			LOAD_SPECIAL_CHARACTER 1 TRUTH

			PRINT_NOW DST8_26 7000 1 //~s~You now have what the Truth wanted.  You must first escape by using the Jet pack to fly out the ~y~launch bay ~s~which is directly above you.
			WHILE NOT HAS_MODEL_LOADED MICRO_UZI
				OR NOT HAS_MODEL_LOADED CAMPER
				OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
				WAIT 0
			ENDWHILE

			DELETE_CAR car_d8
			MARK_MODEL_AS_NO_LONGER_NEEDED TUG
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < 22
				DELETE_CHAR army_d8[spotlight_d8loop]
				REMOVE_BLIP armyblips_d8[spotlight_d8loop]
				spotlight_d8loop++
			ENDWHILE
			
			//create guys

			//outside
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 139.9523 1929.0795 18.2062 army_d8[3]
			SET_CHAR_DECISION_MAKER army_d8[3] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[3] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[3] TRUE
			SET_CHAR_HEALTH army_d8[3] 50
			SET_CHAR_ACCURACY army_d8[3] 30

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 127.6658 1885.1527 17.0346 army_d8[4]
			SET_CHAR_DECISION_MAKER army_d8[4] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[4] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[4] TRUE
			SET_CHAR_HEALTH army_d8[4] 50
			SET_CHAR_ACCURACY army_d8[4] 30

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 114.7912 1830.3859 16.6406 army_d8[5]
			SET_CHAR_DECISION_MAKER army_d8[5] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[5] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[5] TRUE
			SET_CHAR_HEALTH army_d8[5] 50
			SET_CHAR_ACCURACY army_d8[5] 40

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 205.6079 1860.5575 19.6406 army_d8[6]
			SET_CHAR_DECISION_MAKER army_d8[6] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[6] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[6] TRUE
			SET_CHAR_HEALTH army_d8[6] 50
			SET_CHAR_ACCURACY army_d8[6] 30

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 245.2025 1881.9976 19.1052 army_d8[7]
			SET_CHAR_DECISION_MAKER army_d8[7] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[7] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[7] TRUE
			SET_CHAR_HEALTH army_d8[7] 50
			SET_CHAR_ACCURACY army_d8[7] 40

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 262.8257 1807.6687 24.4985 army_d8[8]
			SET_CHAR_DECISION_MAKER army_d8[8] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[8] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[8] TRUE
			SET_CHAR_HEALTH army_d8[8] 50
			SET_CHAR_ACCURACY army_d8[8] 30

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 279.2588 1863.7567 16.6406 army_d8[9]
			SET_CHAR_DECISION_MAKER army_d8[9] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[9] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[9] TRUE
			SET_CHAR_HEALTH army_d8[9] 50
			SET_CHAR_ACCURACY army_d8[9] 30

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 279.2344 1901.9054 16.6406 army_d8[10]
			SET_CHAR_DECISION_MAKER army_d8[10] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[10] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[10] TRUE
			SET_CHAR_HEALTH army_d8[10] 50
			SET_CHAR_ACCURACY army_d8[10] 40

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 254.6844 1907.7139 19.4742 army_d8[11]
			SET_CHAR_DECISION_MAKER army_d8[11] desert8empty_DM
			GIVE_WEAPON_TO_CHAR army_d8[11] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[11] TRUE
			SET_CHAR_HEALTH army_d8[11] 50
			SET_CHAR_ACCURACY army_d8[11] 30


			DELETE_OBJECT jetdoor_d8
			CREATE_OBJECT_NO_OFFSET a51_labdoor 268.66 1864.059 7.5 jetdoor_d8
			TIMERA = 0
			room_d8flag = 6

		ENDIF
	ENDIF

	IF launchdoor_d8flag = 1
		IF DOES_OBJECT_EXIST launchdoor_a51
			IF SLIDE_OBJECT launchdoor_a51 276.14 1884.06 15.924 0.05 0.0 0.0 FALSE
				launchdoor_d8flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF room_d8flag = 6
		IF TIMERA > 1000
			
			POINT_CAMERA_AT_POINT 268.5247 1886.0841 -28.5841 INTERPOLATION
			launchdoor_d8flag = 1
			TIMERA = 0
			room_d8flag = 7

		ENDIF
	ENDIF


	IF room_d8flag = 7
		IF TIMERA > 4000
			FORCE_WEATHER_NOW WEATHER_SUNNY_DESERT

			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 200
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			SET_OBJECT_COORDINATES launchdoor_a51 276.14 1884.06 15.924
			SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 OFF
			CLEAR_PRINTS
			REMOVE_BLIP jetpack_d8blip
			SET_RADAR_ZOOM 0
			ADD_BLIP_FOR_COORD 268.9 1884.33 19.91 jetpack_d8blip

			//inside launch bay
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 271.62 1878.08 -2.32 army_d8[0]
			SET_CHAR_DECISION_MAKER army_d8[0] desert8empty_DM
			SET_CHAR_HEADING army_d8[0] 212.7731 
			GIVE_WEAPON_TO_CHAR army_d8[0] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[0] TRUE
			SET_CHAR_HEALTH army_d8[0] 50
			SET_CHAR_ACCURACY army_d8[0] 30
			enemy_d8 = army_d8[0]
			GOSUB stayshoot_d8label

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 266.112 1876.84 7.44 army_d8[1]
			SET_CHAR_DECISION_MAKER army_d8[1] desert8empty_DM
			SET_CHAR_HEADING army_d8[1] 328.04
			GIVE_WEAPON_TO_CHAR army_d8[1] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[1] TRUE
			SET_CHAR_HEALTH army_d8[1] 50
			SET_CHAR_ACCURACY army_d8[1] 30
			enemy_d8 = army_d8[1]
			GOSUB stayshoot_d8label

			CREATE_CHAR PEDTYPE_MISSION1 ARMY 274.432 1888.28 3.21 army_d8[2]
			SET_CHAR_DECISION_MAKER army_d8[2] desert8empty_DM
			SET_CHAR_HEADING army_d8[2] 328.0
			GIVE_WEAPON_TO_CHAR army_d8[2] WEAPONTYPE_M4 9999
			TASK_STAY_IN_SAME_PLACE army_d8[2] TRUE
			SET_CHAR_HEALTH army_d8[2] 50
			SET_CHAR_ACCURACY army_d8[2] 30
			enemy_d8 = army_d8[2]
			GOSUB stayshoot_d8label
			inside_d8flag = 2
			helptext_d8flag = 1
			room_d8flag = 8
		ENDIF
	ENDIF

ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Outside area 51	  //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

IF inside_d8flag = 2

	IF jetdoor_d8flag = 3

		GOSUB process_audio_d8	

		IF progressaudio_d8flag = 0
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_JA //Intruder is stealing the jet pack! All military personnel outside and fire at will!
				$input_text_d8 = DST8_35 //Intruder is stealing the jet pack! All military personnel outside and fire at will!
				GOSUB load_audio_d8
			ENDIF
		ENDIF
		IF progressaudio_d8flag = 1
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_JB //Take him out! That’s a $60,000,000 project!
				$input_text_d8 = DES8_JB //Take him out! That’s a $60,000,000 project!
				GOSUB load_audio_d8
			ENDIF
		ENDIF
		IF progressaudio_d8flag = 2
			IF handlingudio_d8flag = 0
				audio_label_d8 = SOUND_DES8_JC //Get the General Mills on the line!
				$input_text_d8 = DES8_JC //Get the General Mills on the line!
				GOSUB load_audio_d8
			ENDIF
		ENDIF

	ENDIF


	IF helptext_d8flag < 8

		IF helptext_d8flag = 1
		
			PRINT_HELP DST8_27  

			TIMERB = 0
			helptext_d8flag = 2
		ENDIF
		IF helptext_d8flag = 2
			IF TIMERB > 6000
				PRINT_HELP DST8_28  // To move in a direction with the jet pack use the movement controls while in the air.
				TIMERB = 0
				helptext_d8flag = 3
			ENDIF
		ENDIF
		IF helptext_d8flag = 3
			IF TIMERB > 6000
				
				PRINT_HELP DST8_29  

				TIMERB = 0
				helptext_d8flag = 4
			ENDIF
		ENDIF
		IF helptext_d8flag = 4
			IF TIMERB > 6000
				
				PRINT_HELP DST8_30  

				TIMERB = 0
				helptext_d8flag = 5
			ENDIF
		ENDIF
		IF helptext_d8flag = 5
			IF TIMERB > 6000
				
				PRINT_HELP DST8_31

				TIMERB = 0
				helptext_d8flag = 6
			ENDIF
		ENDIF
		IF helptext_d8flag = 6
			IF TIMERB > 6000
				PRINT_HELP DST8_32 //To hover in the jetpack hold down ~h~R2 and L2 simultaneously~w~.
				TIMERB = 0
				helptext_d8flag = 7
			ENDIF
		ENDIF
		IF helptext_d8flag = 7
			IF TIMERB > 6000
				PRINT_HELP DST8_33 //You can fire a single handed weapon as normal while in the jetpack.
				TIMERB = 0
				helptext_d8flag = 8
			ENDIF
		ENDIF

	ENDIF


	IF room_d8flag = 8
	    
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 268.73 1883.96 17.85 30.0 30.0 10.0 FALSE
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_CLAXON_STOP
			jetdoor_d8flag = 3
			progressaudio_d8flag = 0
			handlingudio_d8flag = 0
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 268.73 1883.96 17.85 SOUND_CLAXON_START

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 103.946 1901.047 36.246 spotlight_d8bulb[0]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 103.946 1901.047 36.246 spotlight1_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 103.946 1901.047 36.246 spotlight1_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[0] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[0] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 161.962 1933.043 36.246 spotlight_d8bulb[1]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 161.962 1933.043 36.246 spotlight2_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 161.962 1933.043 36.246 spotlight2_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[1] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[1] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 233.486 1934.789 36.246 spotlight_d8bulb[2]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 233.486 1934.789 36.246 spotlight3_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 233.486 1934.789 36.246 spotlight3_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[2] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[2] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 267.116 1895.241 36.246 spotlight_d8bulb[3]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 267.116 1895.241 36.246 spotlight4_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 267.116 1895.241 36.246 spotlight4_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[3] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[3] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 262.145 1807.62 36.246 spotlight_d8bulb[4]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 262.145 1807.62 36.246 spotlight5_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 262.145 1807.62 36.246 spotlight5_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[4] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[4] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 166.003 1849.937 36.246 spotlight_d8bulb[5]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 166.003 1849.937 36.246 spotlight6_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 166.003 1849.937 36.246 spotlight6_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[5] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[5] TRUE

			CREATE_OBJECT_NO_OFFSET A51_SPOTBULB 113.439 1814.405 36.246 spotlight_d8bulb[6]
			CREATE_OBJECT_NO_OFFSET A51_SPOTHOUSING 113.439 1814.405 36.246 spotlight7_d8housing
			CREATE_OBJECT_NO_OFFSET A51_SPOTBASE 113.439 1814.405 36.246 spotlight7_d8base
			SET_OBJECT_HEALTH spotlight_d8bulb[6] 5000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER spotlight_d8bulb[6] TRUE

			spotlight_d8loop = 0
			WHILE spotlight_d8loop < 22
				IF NOT IS_CHAR_DEAD army_d8[spotlight_d8loop] 
					TASK_KILL_CHAR_ON_FOOT army_d8[spotlight_d8loop] scplayer
				ENDIF
				spotlight_d8loop++
			ENDWHILE
			REMOVE_BLIP jetpack_d8blip
			ADD_BLIP_FOR_COORD -794.8 2425.184 156.124 jetpack_d8blip
			PRINT_NOW DST8_35 7000 1 //~s~Deliver the jetpack to a ~y~drop off point ~s~deep in the canyons.

			CREATE_CAR CAMPER -783.169 2430.03 156.23 truthcar_d8
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION truthcar_d8 TRUE
			SET_CAR_HEADING truthcar_d8 171.04
			CHANGE_CAR_COLOUR truthcar_d8 1 1 
			 
			GIVE_VEHICLE_PAINTJOB truthcar_d8 0
			
			CREATE_CHAR_INSIDE_CAR truthcar_d8 PEDTYPE_SPECIAL SPECIAL01 truth_d8
			SET_CHAR_DECISION_MAKER truth_d8 desert8empty_DM
			SET_CAR_PROOFS truthcar_d8 TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_PROOFS truth_d8 TRUE TRUE TRUE TRUE TRUE
			SET_VEHICLE_IS_CONSIDERED_BY_PLAYER truthcar_d8 FALSE
			truthcar_d8flag = 1
			room_d8flag = 9

		ENDIF
	ENDIF

	IF room_d8flag = 9
		IF NOT LOCATE_CHAR_ANY_MEANS_2D	scplayer 276.14 1884.06 100.0 100.0 FALSE
			spotlight_d8loop = 0
			WHILE spotlight_d8loop < 22
				MARK_CHAR_AS_NO_LONGER_NEEDED army_d8[spotlight_d8loop]
				MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
				MARK_MODEL_AS_NO_LONGER_NEEDED M4
				spotlight_d8loop++
			ENDWHILE
			room_d8flag = 10
		ENDIF
	ENDIF

	IF truthcar_d8flag = 1 
		IF NOT IS_CHAR_DEAD truth_d8
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D truth_d8 scplayer 200.0 200.0 FALSE
				
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 268.73 1883.96 17.85 SOUND_CLAXON_STOP
				IF NOT IS_CAR_DEAD truthcar_d8
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION truthcar_d8 FALSE
				ENDIF
				CREATE_CAR SANCHEZ -776.66 2426.41 156.11 bike_d8
				SET_CAR_HEADING bike_d8 175.776
				truthcar_d8flag = 2
			ENDIF
		ENDIF
	ENDIF
	IF truthcar_d8flag = 2
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -791.14 2425.33 156.2 3.0 3.0 3.0 TRUE
			CLEAR_AREA -810.94 2360.42 150.81 200.0 TRUE

			SET_PLAYER_CONTROL PLAYER1 OFF
			SWITCH_WIDESCREEN ON

			DO_FADE 500 FADE_OUT

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			LOAD_MISSION_AUDIO 1 SOUND_TRUX_BH //Carl, dude, man!
			LOAD_MISSION_AUDIO 2 SOUND_DES8_MB //Here you go, better stash it someplace fast!

	
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 2
				WAIT 0
			ENDWHILE

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			SET_CHAR_COORDINATES scplayer -791.178 2425.34 156.16
			SET_CHAR_HEADING scplayer 256.615
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			
			truthcar_d8flag = 3
		ENDIF
	ENDIF

	//mission passed bit
	IF truthcar_d8flag = 3

		SET_FIXED_CAMERA_POSITION -785.0004 2435.6792 157.5931 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -784.9536 2434.6821 157.5342  JUMP_CUT

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		DO_FADE 250 FADE_IN

		PLAY_MISSION_AUDIO 1
		PRINT_NOW TRUX_BH 2000 1 //Carl, dude, man!
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_PRINTS		

		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO 1 SOUND_TRUX_BI //Far out, have a nice trip, dude.

		PLAY_MISSION_AUDIO 2 //Here you go, better stash it someplace fast!
		PRINT_NOW DES8_MB 3000 1//Here you go, better stash it someplace fast!
		TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
			WAIT 0
		ENDWHILE
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		CLEAR_MISSION_AUDIO 2
		LOAD_MISSION_AUDIO 2 SOUND_DES8_MD //Hey, wait a...

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			WAIT 0
		ENDWHILE		
		PLAY_MISSION_AUDIO 1
		PRINT_NOW DES8_MC 3000 1 //Far out, have a nice trip, dude.
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO 1 SOUND_DES8_ME //Was it too much to get lift into town?

		IF NOT IS_CAR_DEAD truthcar_d8
			CAR_GOTO_COORDINATES truthcar_d8 -810.94 2360.42 150.81
			TIMERA = 0
			truthcar_d8flag = 4
		ENDIF

		IF NOT IS_CAR_DEAD truthcar_d8
			TASK_LOOK_AT_VEHICLE scplayer truthcar_d8 5000
		ENDIF

		WHILE NOT HAS_MISSION_AUDIO_LOADED 2
			WAIT 0
		ENDWHILE		
		PLAY_MISSION_AUDIO 2 //DES8_MD //Hey, wait a...
		PRINT_NOW DES8_MD 2000 1// //Hey, wait a...

		WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			WAIT 0
		ENDWHILE		
		PLAY_MISSION_AUDIO 1
		PRINT_NOW DES8_ME 2000 1 //Was it too much to get lift into town?
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE


	ENDIF

	IF truthcar_d8flag = 4
		IF TIMERA > 2000
						
			DO_FADE 500 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			DELETE_CAR truthcar_d8
			DELETE_CHAR	truth_d8

			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL PLAYER1 ON
			SWITCH_WIDESCREEN OFF

			DO_FADE 500 FADE_IN

			GOTO mission_mafia4_passed

			truthcar_d8flag = 5
		ENDIF
	ENDIF

	

ENDIF

GOTO stealjetpack_main_mission_loop


load_audio_d8:
IF handlingudio_d8flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_d8
	$text_label_d8 = $input_text_d8
	handlingudio_d8flag = 1
ENDIF
RETURN

process_audio_d8:
IF handlingudio_d8flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		
		IF NOT $text_label_d8 = DUMMY
			PRINT_NOW $text_label_d8 4000 1 //Dummy message"
		ENDIF
		PLAY_MISSION_AUDIO 1
		handlingudio_d8flag = 2
	ENDIF
ENDIF
IF handlingudio_d8flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_d8flag++
		stage1progressaudio_d8flag++
		stage2progressaudio_d8flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_d8flag = 0
	ENDIF
ENDIF
RETURN


resetsearchlight_d8label:
spotlight_d8loop = 0
WHILE spotlight_d8loop < NUMBEROFSEARCHLIGHTS_D8
	IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop]
		MOVE_SEARCHLIGHT_BETWEEN_COORDS spotlight_d8[spotlight_d8loop] startx[spotlight_d8loop] starty[spotlight_d8loop] startz[spotlight_d8loop] endx[spotlight_d8loop] endy[spotlight_d8loop] endz[spotlight_d8loop] 0.3
	ENDIF
	spotlight_d8loop++
ENDWHILE
RETURN

searchlightfollowplayer_d8label:
spotlight_d8loop2 = 0
WHILE spotlight_d8loop2 < NUMBEROFSEARCHLIGHTS_D8
	IF DOES_SEARCHLIGHT_EXIST spotlight_d8[spotlight_d8loop2]
		POINT_SEARCHLIGHT_AT_CHAR spotlight_d8[spotlight_d8loop2] scplayer 0.125
	ENDIF
	spotlight_d8loop2++
ENDWHILE
RETURN

stayshoot_d8label:
OPEN_SEQUENCE_TASK stayshoot_d8seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshoot_d8seq
PERFORM_SEQUENCE_TASK enemy_d8 stayshoot_d8seq
CLEAR_SEQUENCE_TASK stayshoot_d8seq
RETURN



// **************************************** Mission mafia4 failed ************************

mission_mafia4_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

DELETE_OBJECT a51ventcover
CREATE_OBJECT a51_ventcoverb 245.968 1862.843 19.49 a51ventcover  // Put the vent cover back.
SET_OBJECT_ROTATION a51ventcover 0.0 0.0 -140.998
DONT_REMOVE_OBJECT a51ventcover

RETURN

   

// **************************************** mission mafia4 passed ************************

mission_mafia4_passed:

flag_desert_mission_counter ++
SET_INT_STAT PASSED_DESERT8 1
CREATE_PICKUP_WITH_AMMO shotgspa PICKUP_ON_STREET_SLOW 30 297.8289 1846.6226 6.7266 pickup_weapon[210] // spaz in area 51
CREATE_PICKUP_WITH_AMMO JETPACK PICKUP_ON_STREET_SLOW 0 268.7 1884.1 -30.085 pickup_weapon[211]                           // the jet pack in area 51
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 266.2813 1816.3976 1.594 pickup_armour[46] // body armor for area 51 mission
CREATE_PICKUP_WITH_AMMO IRGOGGLES PICKUP_ONCE 1 212.8813 1811.0046 21.4187 goggle_pickups[0]   // in a control tower at area 51
CREATE_PICKUP_WITH_AMMO IRGOGGLES PICKUP_ONCE 1 1270.5201 -795.5929 1084.2537 goggle_pickups[1]    // mad dogs mansion in LA
CREATE_PICKUP_WITH_AMMO IRGOGGLES PICKUP_ONCE 1 -350.4990 1608.4366 75.6420 goggle_pickups[2]       // near big satellite  out in desert
CREATE_PICKUP_WITH_AMMO IRGOGGLES PICKUP_ONCE 1 -2224.6802 129.1278 1035.6200 goggle_pickups[3]       // inside zeros toy shop
REMOVE_BLIP desert2_contact_blip
REGISTER_MISSION_PASSED ( DESERT8 )
PLAY_MISSION_PASSED_TUNE 1
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSD ) 1 5000 1 //"Mission Passed!"
CLEAR_WANTED_LEVEL player1
REMOVE_BLIP desert2_contact_blip
RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_mafia4:

flag_player_on_mission = 0

MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
MARK_MODEL_AS_NO_LONGER_NEEDED M4
MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTBULB
MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTHOUSING
MARK_MODEL_AS_NO_LONGER_NEEDED A51_SPOTBASE
MARK_MODEL_AS_NO_LONGER_NEEDED CAMPER
MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED
MARK_MODEL_AS_NO_LONGER_NEEDED TUG
MARK_MODEL_AS_NO_LONGER_NEEDED WMOSCI
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
MARK_MODEL_AS_NO_LONGER_NEEDED IRGOGGLES

//misc
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0 
SET_RADAR_ZOOM 0
//SET_DARKNESS_EFFECT FALSE -1
RELEASE_WEATHER

FORCE_INTERIOR_LIGHTING_FOR_PLAYER PLAYER1 FALSE

SET_DISABLE_MILITARY_ZONES FALSE

IF IS_PLAYER_PLAYING PLAYER1
	SHUT_CHAR_UP scplayer FALSE
	SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 OFF
ENDIF

//truth
MARK_CHAR_AS_NO_LONGER_NEEDED truth_d8
UNLOAD_SPECIAL_CHARACTER 1

//pickups, jetpack keycard
//objects
DELETE_OBJECT jetdoor_d8
//pickups
REMOVE_PICKUP jetpack_d8
REMOVE_PICKUP keycard_d8
REMOVE_PICKUP armour1_d8
REMOVE_PICKUP health1_d8
REMOVE_PICKUP armour2_d8
REMOVE_PICKUP health2_d8
REMOVE_PICKUP armour3_d8
REMOVE_PICKUP armour4_d8

SHOW_BLIPS_ON_ALL_LEVELS FALSE

//reset all doors
IF DOES_OBJECT_EXIST launchdoor_a51
	SET_OBJECT_COORDINATES launchdoor_a51 268.664 1884.06 15.925
ENDIF
DELETE_OBJECT frontdoorr_a51
DELETE_OBJECT frontdoorl_a51
CREATE_OBJECT_NO_OFFSET a51_blastdoorR 215.941 1874.571 13.903 frontdoorr_a51

SET_OBJECT_HEADING frontdoorr_a51 0.0
CREATE_OBJECT_NO_OFFSET a51_blastdoorL 211.842 1874.571 13.903 frontdoorl_a51
SET_OBJECT_HEADING frontdoorl_a51 0.0
DONT_REMOVE_OBJECT frontdoorr_a51
DONT_REMOVE_OBJECT frontdoorl_a51

//remove blips
spotlight_d8loop = 0
WHILE spotlight_d8loop < 22
	REMOVE_BLIP armyblips_d8[spotlight_d8loop]
	spotlight_d8loop++
ENDWHILE
REMOVE_BLIP blastdoor_d8blip
REMOVE_BLIP jetpack_d8blip
REMOVE_BLIP keycard_d8blip

//remove searchlights
spotlight_d8loop = 0
WHILE spotlight_d8loop < 7
	DELETE_SEARCHLIGHT spotlight_d8[spotlight_d8loop]
	DELETE_OBJECT spotlight_d8bulb[spotlight_d8loop]
	spotlight_d8loop++
ENDWHILE

DELETE_OBJECT spotlight1_d8housing
DELETE_OBJECT spotlight1_d8base
DELETE_OBJECT spotlight2_d8housing
DELETE_OBJECT spotlight2_d8base
DELETE_OBJECT spotlight3_d8housing
DELETE_OBJECT spotlight3_d8base
DELETE_OBJECT spotlight4_d8housing
DELETE_OBJECT spotlight4_d8base
DELETE_OBJECT spotlight5_d8housing
DELETE_OBJECT spotlight5_d8base
DELETE_OBJECT spotlight6_d8housing
DELETE_OBJECT spotlight6_d8base
DELETE_OBJECT spotlight7_d8housing
DELETE_OBJECT spotlight7_d8base

RELEASE_WEATHER
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
SET_AREA51_SAM_SITE ON
SET_WANTED_MULTIPLIER 1.0
//SET_PED_DENSITY_MULTIPLIER 1.0
//MARK_MODEL_AS_NO_LONGER_NEEDED MALE01
RELEASE_WEATHER
GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN

 
}