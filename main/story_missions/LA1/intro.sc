MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** INTRO *******************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME INTRO

// Mission start stuff

GOSUB mission_start_intro 

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_intro_failed
ENDIF

GOSUB mission_cleanup_intro

MISSION_END

{ 
// Variables for mission

LVAR_INT play_bmx_geton_help


mission_start_intro:
// **************************************** Mission Start **********************************
flag_player_on_mission = 1
DISPLAY_ZONE_NAMES FALSE
// fool compiler
IF flag_player_on_mission = 0
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 random_char_CUT
	CREATE_CAR COPCARLA 0.0 0.0 0.0 random_car_CUT
	WRITE_DEBUG aa
ENDIF 

// audio controller
START_NEW_SCRIPT start_audio_controller

LOAD_MISSION_TEXT INTRO1

// text display stuff
USE_TEXT_COMMANDS TRUE
WAIT 0 // must wait0 before using text or sprite commands

SET_TEXT_DRAW_BEFORE_FADE FALSE
SET_TEXT_FONT FONT_STANDARD

text_alpha_SS = 0
text_status_SS = 0
$text_label_SS = LOAD_01

SWITCH_WIDESCREEN ON

// fade in
WHILE text_alpha_SS < 200
	WAIT 0
	GOSUB mission_intro1_SUB_text_handler
ENDWHILE

text_status_SS = 2
	
TIMERA = 0
WHILE TIMERA < 1000
	WAIT 0
	GOSUB mission_intro1_SUB_text_handler
ENDWHILE

// ****************************************START OF CUTSCENE********************************

// fades the screen in 

SET_AREA_VISIBLE 14

SWITCH_WIDESCREEN OFF

LOAD_CUTSCENE PROLOG1
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
	GOSUB mission_intro1_SUB_text_handler 
ENDWHILE
text_status_SS = 1

// fade out
WHILE text_alpha_SS > 0
	WAIT 0
	GOSUB mission_intro1_SUB_text_handler
ENDWHILE
// new text
text_status_SS = 0
text_display = 1
$text_label_SS = LOAD_02
text_flag_SS = 0

START_CUTSCENE

DO_FADE 0 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

DO_FADE 1000 FADE_IN
TIMERA = 0
  
WHILE NOT HAS_CUTSCENE_FINISHED
	
	WAIT 0
	
	IF TIMERA > 6800
		SWITCH text_flag_SS
			CASE 0
				IF text_alpha_SS < 200
					GOSUB mission_intro1_SUB_text_handler
					BREAK
				ELSE
					
					text_status_SS = 2
					text_flag_SS ++
				ENDIF	

			CASE 1
				IF TIMERA < 10000
					GOSUB mission_intro1_SUB_text_handler
					BREAK
				ELSE
					text_status_SS = 1
					text_flag_SS ++
				ENDIF
					
			CASE 2
				IF text_alpha_SS > 0
					GOSUB mission_intro1_SUB_text_handler
					BREAK
				ELSE
					text_flag_SS ++
				ENDIF			
		ENDSWITCH
	ENDIF

ENDWHILE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

IF WAS_CUTSCENE_SKIPPED
	SET_AREA_VISIBLE 0
	cutscene_skip_CUT = 1
	GOTO mission_intro_end_of_cutscene
ENDIF

// ****************************************START OF CUTSCENE********************************
SET_TIME_OF_DAY 06 30
FORCE_WEATHER_NOW WEATHER_SUNNY_COUNTRYSIDE

SET_AREA_VISIBLE 0
LOAD_CUTSCENE PROLOG3
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

REQUEST_MODEL gwforum1_LAe
WHILE NOT HAS_MODEL_LOADED gwforum1_LAe
	WAIT 0
ENDWHILE 
LOAD_SCENE_IN_DIRECTION 2493.3623 -1734.2950 12.3828 258.7583 

CLEAR_AREA 2482.5815 -1729.6418 12.3906 500.0 TRUE

START_CUTSCENE

DO_FADE 1000 FADE_IN

WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
	SET_TIME_OF_DAY 06 30
ENDWHILE
 
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

MARK_MODEL_AS_NO_LONGER_NEEDED gwforum1_LAe

IF WAS_CUTSCENE_SKIPPED
	cutscene_skip_CUT = 1
	GOTO mission_intro_end_of_cutscene
ENDIF

// ****************************************START OF (SCRIPTED) CUTSCENE********************************


mission_intro_CUT:

LVAR_INT copcar_CUT train_CUT cut_status_CUT beat_status_CUT camera_status_CUT 
LVAR_INT tenpenny_CUT pulaski_CUT hernandez_CUT


// models
REQUEST_MODEL COPCARLA
REQUEST_MODEL freight
REQUEST_MODEL freiflat
REQUEST_MODEL BMX

// building
REQUEST_MODEL gangshops4_LAe2 
REQUEST_MODEL CE_HillsEast06
REQUEST_MODEL cuntEground23
REQUEST_MODEL HillsEast05_LAe


LOAD_SPECIAL_CHARACTER 1 TENPEN
LOAD_SPECIAL_CHARACTER 2 PULASKI
LOAD_SPECIAL_CHARACTER 3 HERN

//REQUEST_MODEL LAPD1

REQUEST_CAR_RECORDING 706
REQUEST_CAR_RECORDING 707

WHILE NOT HAS_MODEL_LOADED COPCARLA
OR NOT HAS_MODEL_LOADED freight
OR NOT HAS_MODEL_LOADED freiflat
OR NOT HAS_MODEL_LOADED BMX
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 706
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 707
	WAIT 0
ENDWHILE


WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED gangshops4_LAe2
OR NOT HAS_MODEL_LOADED CE_HillsEast06
OR NOT HAS_MODEL_LOADED cuntEground23
OR NOT HAS_MODEL_LOADED HillsEast05_LAe
	WAIT 0
ENDWHILE


REQUEST_COLLISION 2431.6086 -1254.0608
LOAD_SCENE 2358.6562 -1246.3481 28.7884

// audio stuff
PRELOAD_BEAT_TRACK 10

beat_status_CUT = 0
WHILE NOT beat_status_CUT = 2
	WAIT 0
	GET_BEAT_TRACK_STATUS beat_status_CUT
ENDWHILE

/*
SUBTITLE STUFF
*/

LVAR_INT subtitle_lenght_CUT[25] subtitle_start_CUT[25] subtitle_pointer_CUT subtitle_status_CUT
LVAR_TEXT_LABEL subtitle_label_CUT[25]


IF ARE_SUBTITLES_SWITCHED_ON
	subtitle_pointer_CUT = 0

	$subtitle_label_CUT[0] = &PRO@24	// How you been, Carl? How's you wonderful family
	subtitle_lenght_CUT[0] = 3050
	subtitle_start_CUT[0] = 0

	$subtitle_label_CUT[1] = &PRO@25	// I'm here to bury my moms. You know that.
	subtitle_lenght_CUT[1] = 2530
	subtitle_start_CUT[1] = 3150

	$subtitle_label_CUT[2] = &PRO@26	// Yeah, I guess I do.
	subtitle_lenght_CUT[2] = 1600
	subtitle_start_CUT[2] = 6000

	$subtitle_label_CUT[3] = &PRO@49	// So what else you got shakin Carl.
	subtitle_lenght_CUT[3] = 1530
	subtitle_start_CUT[3] = 9190

	$subtitle_label_CUT[4] = &PRO@27	// Nothing, I live in Liberty City now. I'm clean. Legit.
	subtitle_lenght_CUT[4] = 5510
	subtitle_start_CUT[4] = 10810

	$subtitle_label_CUT[5] = &PRO@28	// No, you aint never been clean Carl.
	subtitle_lenght_CUT[5] = 2560
	subtitle_start_CUT[5] = 16600

	$subtitle_label_CUT[6] = &PRO@29	// Well, what've we got here
	subtitle_lenght_CUT[6] = 1350
	subtitle_start_CUT[6] = 21000

	$subtitle_label_CUT[7] = &PRO@30	// This is a weapon, officer pulaski that was used to gun down
	subtitle_lenght_CUT[7] = 3760
	subtitle_start_CUT[7] = 23500

	$subtitle_label_CUT[8] = &PRO@30b	// a police officer not ten minutes ago
	subtitle_lenght_CUT[8] = 2450
	subtitle_start_CUT[8] = subtitle_start_CUT[7] +	subtitle_lenght_CUT[7]

	$subtitle_label_CUT[9] = &PRO@31	// Officer Pendelberry, a fine man, I might add.
	subtitle_lenght_CUT[9] = 3620
	subtitle_start_CUT[9] = 30700

	$subtitle_label_CUT[10] = &PRO@33	// You work fast nigger.
	subtitle_lenght_CUT[10] = 1440
	subtitle_start_CUT[10] = 35000

	$subtitle_label_CUT[11] = &PRO@32	// You know I just got off the plane!
	subtitle_lenght_CUT[11] = 1739
	subtitle_start_CUT[11] = 36750

	$subtitle_label_CUT[12] = &PRO@34	// It's a good thing we found you and retrieved the murder weapon
	subtitle_lenght_CUT[12] = 2450
	subtitle_start_CUT[12] = subtitle_start_CUT[11] + subtitle_lenght_CUT[11]

	$subtitle_label_CUT[13] = &PRO@35	// That aint my gun
	subtitle_lenght_CUT[13] = 971
	subtitle_start_CUT[13] = subtitle_start_CUT[12] + subtitle_lenght_CUT[12]

	$subtitle_label_CUT[14] = &PRO@36	// Dont bullshit me carl
	subtitle_lenght_CUT[14] = 1320
	subtitle_start_CUT[14] = subtitle_start_CUT[13] + subtitle_lenght_CUT[13]

	$subtitle_label_CUT[15] = &PRO@37	// Yeah, dont bullshit him carl
	subtitle_lenght_CUT[15] = 1342
	subtitle_start_CUT[15] = subtitle_start_CUT[14] + subtitle_lenght_CUT[14]

	$subtitle_label_CUT[16] = &PRO@38	// What the fuck you want from me this time 
	subtitle_lenght_CUT[16] = 1747
	subtitle_start_CUT[16] = subtitle_start_CUT[15] + subtitle_lenght_CUT[15]

	$subtitle_label_CUT[17] = &PRO@40	// When we want you, we'll find you. 
	subtitle_lenght_CUT[17] = 2355
	subtitle_start_CUT[17] = 46700 

	$subtitle_label_CUT[18] = &PRO@41	// In the mean time, try not to gun down any officers of the law
	subtitle_lenght_CUT[18] = 4400
	subtitle_start_CUT[18] = 49500

	$subtitle_label_CUT[19] = &PRO@43	// Y'all cant leave me here, this is ballas country
	subtitle_lenght_CUT[19] = 3111
	subtitle_start_CUT[19] = 55900

	$subtitle_label_CUT[20] = &PRO@44	// I though you said you was innocent carl? That you dont bang.
	subtitle_lenght_CUT[20] = 3400
	subtitle_start_CUT[20] = 59500

	$subtitle_label_CUT[21] = &PRO@45	// This is car 58... WHAT?!
	subtitle_lenght_CUT[21] = 1180
	subtitle_start_CUT[21] = subtitle_start_CUT[20] + subtitle_lenght_CUT[20]

	$subtitle_label_CUT[22] = &PRO@47	// See you around like a donut carl
	subtitle_lenght_CUT[22] = 1700
	subtitle_start_CUT[22] = subtitle_start_CUT[21] + subtitle_lenght_CUT[21]

	$subtitle_label_CUT[23] = &PRO@46	// Officer Pendelberry's down? We'll be right over
	subtitle_lenght_CUT[23] = 2500
	subtitle_start_CUT[23] = subtitle_start_CUT[22] + subtitle_lenght_CUT[22]

//	$subtitle_label_CUT[24] = &PRO@48	// See, Hernandez, that is how an experienced officer deals with a known threat
//	subtitle_lenght_CUT[24] = 5994
//	subtitle_start_CUT[24] = subtitle_start_CUT[23] + subtitle_lenght_CUT[23]
ELSE
	subtitle_pointer_CUT = 25
ENDIF

/*
END OF SUBTITLE STUFF
*/


SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 1.0
SWITCH_RANDOM_TRAINS OFF

cut_status_CUT = 0
 
// copcar
CREATE_CAR COPCARLA 2431.6086 -1254.0608 22.8303 copcar_CUT 
SET_CAR_HEADING copcar_CUT 89.5453 
FORCE_CAR_LIGHTS copcar_CUT FORCE_CAR_LIGHTS_ON

WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer copcar_CUT 2
  
CREATE_CHAR_INSIDE_CAR copcar_CUT PEDTYPE_SPECIAL SPECIAL02 pulaski_CUT
CREATE_CHAR_AS_PASSENGER copcar_CUT PEDTYPE_SPECIAL SPECIAL01 0 tenpenny_CUT
CREATE_CHAR_AS_PASSENGER copcar_CUT PEDTYPE_SPECIAL SPECIAL03 1 hernandez_CUT

SET_FIXED_CAMERA_POSITION 2358.6562 -1246.3481 28.7884 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2359.5212 -1246.8428 28.7047 JUMP_CUT
	   	   
SET_TIME_OF_DAY 06 30

FORCE_WEATHER_NOW WEATHER_SUNNY_COUNTRYSIDE

// stop peds crossing roads as intro plays
SWITCH_PED_ROADS_OFF 2380.6816 -1274.5280 22.0 2375.9453 -1239.3557 26.0
SWITCH_PED_ROADS_OFF 2358.3831 -1372.6659 22.0 2382.9690 -1379.4574 25.0
SWITCH_PED_ROADS_OFF 2151.6025 -1380.3746 22.0 2184.7036 -1374.7100 26.0 
SWITCH_PED_ROADS_OFF 2180.7307 -1286.3281 22.0 2153.5266 -1315.7975 26.0 



TIMERA = 0
WHILE TIMERA < 500
	WAIT 0
ENDWHILE

DO_FADE 500 FADE_IN

SWITCH_WIDESCREEN ON

IF NOT IS_CAR_DEAD copcar_CUT
	IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar_CUT
		START_PLAYBACK_RECORDED_CAR copcar_CUT 706
		PLAY_BEAT_TRACK
	ENDIF
ENDIF

IF IS_PLAYER_PLAYING player1
	SET_PLAYER_CONTROL player1 OFF
ENDIF

CREATE_MISSION_TRAIN 13 2285.1523 -1257.4998 23.0000 TRUE train_CUT
SET_TRAIN_SPEED train_CUT 0.0
SET_TRAIN_CRUISE_SPEED train_CUT 0.0


x = 2373.1050
y = -1281.9690
z = 22.8330
heading = 0.0
gotoX_CUT = 2478.5591  
gotoY_CUT = -1258.5406
gotoZ_CUT = 27.5958
GOSUB mission_intro_SUB_create_intro_car


TIMERA = 0
TIMERB = 0

cutscene_skip_CUT = 2

LVAR_INT bike_exists_CUT
mission_intro_scripted_cut:

	WAIT 0

	SET_TIME_OF_DAY 06 30

	// player and car movement
	IF NOT IS_CAR_DEAD copcar_CUT

		SWITCH cut_status_CUT
			CASE 0
				IF GET_FADING_STATUS
					BREAK
				ENDIF

				SKIP_CUTSCENE_START
				cut_status_CUT ++
			
			CASE 1
				IF TIMERA < 10000
					BREAK
				ENDIF

				IF NOT IS_CAR_DEAD train_CUT 
					SET_TRAIN_SPEED train_CUT 8.5
					SET_TRAIN_CRUISE_SPEED train_CUT 8.5
				ENDIF
				cut_status_CUT ++
								 
			CASE 2		
				IF IS_PLAYBACK_GOING_ON_FOR_CAR copcar_CUT
					BREAK
				ENDIF
				
				TIMERA = 0
				cut_status_CUT ++

			CASE 3
				IF TIMERA < 13000
					BREAK
				ENDIF

				TIMERA = 0
				START_PLAYBACK_RECORDED_CAR copcar_CUT 707
  				SET_CAR_COLLISION copcar_CUT FALSE
				
				CLEAR_AREA 2246.5076 -1263.0870 22.9531 500.0 TRUE
				CREATE_CAR BMX 2246.5076 -1263.0870 22.9531 intro_bmx
				SET_CAR_HEADING intro_bmx 285.0
				bike_exists_CUT = 1
  				cut_status_CUT ++

			CASE 4
				IF TIMERA < 26800
					BREAK
				ENDIF
				
				TASK_LEAVE_CAR_IMMEDIATELY scplayer copcar_CUT
				cut_status_CUT ++
			BREAK

			CASE 5
				
				IF IS_PLAYBACK_GOING_ON_FOR_CAR copcar_CUT
					BREAK
				ENDIF

				TASK_GO_STRAIGHT_TO_COORD scplayer 2239.3303 -1261.9406 22.9375 PEDMOVE_WALK -2

				cutscene_skip_CUT = 0

				SKIP_CUTSCENE_END

				IF cutscene_skip_CUT = 2
					GOTO mission_intro_end_of_cutscene
				ENDIF

				STOP_BEAT_TRACK

				DELETE_MISSION_TRAINS
				SWITCH_RANDOM_TRAINS ON

				MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
				MARK_MODEL_AS_NO_LONGER_NEEDED freight
				MARK_MODEL_AS_NO_LONGER_NEEDED freiflat

				UNLOAD_SPECIAL_CHARACTER 1 // tenpenny
				UNLOAD_SPECIAL_CHARACTER 2 // pulaski
				UNLOAD_SPECIAL_CHARACTER 3 // hernandez
				
				DELETE_CHAR tenpenny_CUT
				DELETE_CHAR pulaski_CUT 
				DELETE_CHAR hernandez_CUT

				DELETE_CAR copcar_CUT

				cutscene_skip_CUT = 3
										   
				GOTO mission_intro_carl_voice_over_setup
							
		ENDSWITCH
		
		// CAMERA MOVEMENT
		SWITCH camera_status_CUT

			// start pan delay before moving
			CASE 0 
				IF TIMERB < 1500
					BREAK
				ENDIF
									
				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2358.6562 -1246.3481 28.7884 2358.6562 -1246.3481 28.7884 7000 TRUE
				CAMERA_SET_VECTOR_TRACK 2359.5212 -1246.8428 28.7047 2359.2756 -1247.1285 28.7047 7000 TRUE
							
				camera_status_CUT ++

			// new street shot
			CASE 1
								
				IF TIMERB < 5500
					BREAK
				ENDIF

				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2362.0295 -1288.5000 23.8624 2362.0295 -1288.5000 23.8624 5500 TRUE
				CAMERA_SET_VECTOR_TRACK 2362.5920 -1287.6763 23.9326 2362.0474 -1289.4973 23.932 5500 TRUE

				camera_status_CUT ++																										  
			
			// street shot - chasing car
			CASE 2
				IF TIMERB < 9500
					BREAK
				ENDIF

				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2377.1628 -1308.2468 23.6193 2380.6733 -1390.4246 24.0771 6800 FALSE
				CAMERA_SET_VECTOR_TRACK 2376.4695 -1307.5341 23.7249 2379.8110 -1389.9220 24.1388 6800 FALSE

				camera_status_CUT ++

			// new low shot approaching train 
			CASE 3				
				IF TIMERB < 16200
					BREAK
				ENDIF
				MARK_MODEL_AS_NO_LONGER_NEEDED gangshops4_LAe2
				MARK_MODEL_AS_NO_LONGER_NEEDED CE_HillsEast06
				MARK_MODEL_AS_NO_LONGER_NEEDED cuntEground23
				MARK_MODEL_AS_NO_LONGER_NEEDED HillsEast05_LAe

				SET_FIXED_CAMERA_POSITION 2316.9878 -1378.1227 23.3054 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2317.9082 -1378.4891 23.4404 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES

				camera_status_CUT ++

			// high shot of train
			CASE 4
				IF TIMERB < 20500
					BREAK
				ENDIF

				SET_FIXED_CAMERA_POSITION 2271.7495 -1396.4435 36.7292 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2272.4749 -1395.8940 36.3149 JUMP_CUT			
				CAMERA_RESET_NEW_SCRIPTABLES
				camera_status_CUT ++

			// side of the car
			CASE 5
				IF TIMERB < 32300
					BREAK
				ENDIF
				 							  
				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2305.5364 -1384.7535 23.2002 2303.5210 -1384.3871 23.0641 5700 FALSE
				CAMERA_SET_VECTOR_TRACK 2304.7085 -1384.1930 23.1803 2302.7280 -1383.8613 23.3722 5700 FALSE

				camera_status_CUT ++

			// camera pan
			CASE 6
				IF TIMERB < 37589
					BREAK
				ENDIF					

				SET_FIXED_CAMERA_POSITION 2265.2544 -1388.5906 23.4001 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2266.1641 -1388.2019 23.4001 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES
				camera_status_CUT ++

			CASE 7
				IF TIMERB < 38500
					BREAK
				ENDIF
					
				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2265.2544 -1388.5906 23.4001 2246.9104 -1388.5906 23.4001 7500 TRUE
				CAMERA_SET_VECTOR_TRACK 2266.1641 -1388.2019 23.4001 2245.9353 -1388.4237 23.4001 7500 TRUE
				camera_status_CUT ++

			CASE 8
				IF TIMERB < 46000
					BREAK
				ENDIF

				SET_FIXED_CAMERA_POSITION 2163.0845 -1387.7574 23.8438 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2163.9458 -1387.2679 23.9785 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES
								
				camera_status_CUT ++

			CASE 9 
				IF TIMERB < 48000
					BREAK
				ENDIF

				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2163.0845 -1387.7574 23.8438 2163.0845 -1387.7574 23.8438 5000 TRUE
				CAMERA_SET_VECTOR_TRACK 2163.9458 -1387.2679 23.9785 2163.4229 -1386.8262 23.9785 5000 TRUE
				camera_status_CUT ++

			CASE 10
				IF TIMERB < 53000
					BREAK
				ENDIF

				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2156.5513 -1292.0818 39.2814 2156.9744 -1288.5637 36.9331 4000 TRUE
				CAMERA_SET_VECTOR_TRACK 2157.0251 -1292.6703 38.6264 2157.6265 -1289.0844 36.3820 4000 TRUE

				camera_status_CUT ++
							
			CASE 11
				IF TIMERB < 57000
					BREAK
				ENDIF					

				SET_FIXED_CAMERA_POSITION 2176.3728 -1252.7585 23.4951 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2176.1179 -1253.7253 23.5115 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES
				camera_status_CUT ++

			CASE 12
				IF TIMERB < 59200
					BREAK
				ENDIF
				CAMERA_RESET_NEW_SCRIPTABLES
				CAMERA_PERSIST_TRACK TRUE 
				CAMERA_PERSIST_POS TRUE
				CAMERA_SET_VECTOR_MOVE 2176.3728 -1252.7585 23.4951 2176.3728 -1252.7585 23.4951 2500 TRUE
				CAMERA_SET_VECTOR_TRACK 2176.1179 -1253.7253 23.5115 2177.0786 -1253.4666 23.5115 2500 TRUE

				camera_status_CUT ++

			CASE 13
				IF TIMERB < 61400
					BREAK
				ENDIF

				SET_FIXED_CAMERA_POSITION 2212.5820 -1262.7559 25.8688 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2211.6807 -1262.3927 25.6330 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES

				camera_status_CUT ++

			// end shot CJ walking away and car driving off
			CASE 14 
				IF TIMERB < 63000
					BREAK
				ENDIF

				SET_FIXED_CAMERA_POSITION 2210.4185 -1263.1212 23.1607 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2211.3989 -1262.9810 23.2985 JUMP_CUT
				CAMERA_RESET_NEW_SCRIPTABLES

				camera_status_CUT ++

			CASE 15
				IF TIMERB < 64000 // 67500
					BREAK
				ENDIF
							     		    	  
				CAMERA_RESET_NEW_SCRIPTABLES		   
				CAMERA_PERSIST_TRACK TRUE 							  
				CAMERA_PERSIST_POS TRUE															 //7500
				CAMERA_SET_VECTOR_MOVE 2210.4185 -1263.1212 23.1607 2217.6152 -1262.7610 24.4500 6500 TRUE
				CAMERA_SET_VECTOR_TRACK 2211.3989 -1262.9810 23.2985 2218.6064 -1262.8859 24.4112 6500 TRUE

				camera_status_CUT ++

		ENDSWITCH

		// subtitle text (using TIMERB as this is not reset by the camera switch)
		
		LVAR_INT temp_int_CUT
		IF subtitle_pointer_CUT < 24
			
			SWITCH subtitle_status_CUT
				
				CASE 1
					IF TIMERB < temp_int_CUT
						BREAK
					ENDIF
					
					CLEAR_PRINTS
					subtitle_status_CUT --

				CASE 0
					IF TIMERB < subtitle_start_CUT[subtitle_pointer_CUT] 
						BREAK
					ENDIF
					
					PRINT_NOW $subtitle_label_CUT[subtitle_pointer_CUT] 12000 1
				 	temp_int_CUT = TIMERB + subtitle_lenght_CUT[subtitle_pointer_CUT]
				 	
				 	subtitle_pointer_CUT ++

				 	subtitle_status_CUT ++

			ENDSWITCH
 	
		ENDIF
		
		// cars for intro - not using density to avoid cars clipping camera and hitting police car

		LVAR_INT car_timer_CUT
		SWITCH car_timer_CUT
			CASE 0
				IF TIMERB < 8500
					BREAK
				ENDIF

				x = 2345.4519 
				y = -1303.9263
				z = 22.9955
				heading = 270.0
				gotoX_CUT = 2368.4365
				gotoY_CUT = -1202.7523
				gotoZ_CUT = 26.4297 
				GOSUB mission_intro_SUB_create_intro_car
				car_timer_CUT ++

			CASE 1
				IF TIMERB < 15000
					BREAK
				ENDIF

				x = 2346.3833 
				y = -1420.5553
				z = 22.8281
				heading = 0.0
				gotoX_CUT = 2387.4878
				gotoY_CUT = -1409.5367
				gotoZ_CUT = 22.8359 
				GOSUB mission_intro_SUB_create_intro_car
				car_timer_CUT ++

			CASE 2
				IF TIMERB < 40000
					BREAK
				ENDIF
				 	  
				x = 2215.0879
				y = -1417.0861
				z = 22.8281
				heading = 0.0
				gotoX_CUT = 2255.4443
				gotoY_CUT = -1302.7999
				gotoZ_CUT = 22.8402 
				GOSUB mission_intro_SUB_create_intro_car
				car_timer_CUT ++			

			CASE 3
				IF TIMERB < 48000
					BREAK
				ENDIF
				 	  
				x = 2165.0415
				y = -1353.0554
				z = 22.8281
				heading = 180.0
				gotoX_CUT = 2130.5789
				gotoY_CUT = -1381.4778
				gotoZ_CUT = 22.8281 
				GOSUB mission_intro_SUB_create_intro_car
				car_timer_CUT ++	

			CASE 4
				IF TIMERB < 52000
					BREAK
				ENDIF

				x = 2201.3516
				y = -1298.1646
				z = 22.8303
				heading = 90.0
				gotoX_CUT = 2121.0894
				gotoY_CUT = -1298.5696
				gotoZ_CUT = 22.8170 
				GOSUB mission_intro_SUB_create_intro_car
				car_timer_CUT ++	
							
		ENDSWITCH

	ENDIF 

GOTO mission_intro_scripted_cut 

LVAR_INT random_car_CUT random_char_CUT random_model_CUT random_class_CUT
LVAR_FLOAT gotoX_CUT gotoY_CUT gotoZ_CUT 
mission_intro_SUB_create_intro_car:

	GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE random_model_CUT random_class_CUT
	IF NOT random_model_CUT = -1 

		MARK_CHAR_AS_NO_LONGER_NEEDED random_char_CUT 
		MARK_CAR_AS_NO_LONGER_NEEDED random_car_CUT

		CREATE_CAR random_model_CUT x y z random_car_CUT 
		SET_CAR_HEADING random_car_CUT heading
		CREATE_RANDOM_CHAR_AS_DRIVER random_car_CUT random_char_CUT
		
		//TASK_CAR_DRIVE random_char_CUT random_car_CUT
		//TASK_CAR_DRIVE_WANDER random_char_CUT random_car_CUT 15.0 DRIVINGMODE_STOPFORCARS 

		//SET_CAR_FORWARD_SPEED random_car_CUT 15.0
		TASK_CAR_DRIVE_TO_COORD random_char_CUT random_car_CUT gotoX_CUT gotoY_CUT gotoZ_CUT 15.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS 
	ENDIF

RETURN


// ****************************************END OF CUTSCENE**********************************

LVAR_INT audio_status_CUT audio_pointer_CUT audio_sound_label_CUT[7]
LVAR_TEXT_LABEL audio_text_label_CUT[7]

mission_intro_carl_voice_over_setup:
	
	audio_status_CUT = 0
	audio_pointer_CUT = 0

	$audio_text_label_CUT[0] = &VO_AA
	audio_sound_label_CUT[0] = SOUND_VO_AA
	$audio_text_label_CUT[1] = &VO_AB
	audio_sound_label_CUT[1] = SOUND_VO_AB
	$audio_text_label_CUT[2] = &VO_AC
	audio_sound_label_CUT[2] = SOUND_VO_AC
	$audio_text_label_CUT[3] = &VO_AD
	audio_sound_label_CUT[3] = SOUND_VO_AD
	$audio_text_label_CUT[4] = &VO_AE
	audio_sound_label_CUT[4] = SOUND_VO_AE
	$audio_text_label_CUT[5] = &VO_AF
	audio_sound_label_CUT[5] = SOUND_VO_AF
	$audio_text_label_CUT[6] = &VO_AG
	audio_sound_label_CUT[6] = SOUND_VO_AG
	
	RESTORE_CAMERA_JUMPCUT						 
	CAMERA_RESET_NEW_SCRIPTABLES

	CAMERA_RESET_NEW_SCRIPTABLES						    
	CAMERA_PERSIST_TRACK TRUE 
	CAMERA_PERSIST_POS TRUE
	CAMERA_SET_VECTOR_MOVE 2217.6152 -1262.7610 24.4500 2238.6409 -1261.3286 24.6289 13500 FALSE
	CAMERA_SET_VECTOR_TRACK 2218.6064 -1262.8859 24.4112 2239.6021 -1261.5890 24.5400 13500 FALSE
	CAMERA_SET_SHAKE_SIMULATION_SIMPLE 1 20000.0 3.0

	camera_status_CUT = 0

	TIMERA = 0

SKIP_CUTSCENE_START

VAR_INT cutscene_skip_CUT
mission_intro_carl_voice_over:

	WAIT 0

	// audio
	//IF NOT GET_FADING_STATUS
		SWITCH audio_status_CUT
			CASE 0
				IF audio_pointer_CUT < 5
					load_sample = audio_sound_label_CUT[audio_pointer_CUT]
					$load_text = $audio_text_label_CUT[audio_pointer_CUT]
					START_NEW_SCRIPT audio_load_and_play 1 100 // speaker 0.0 0.0 0.0
					audio_pointer_CUT ++
					audio_status_CUT ++
				ELSE
					audio_status_CUT = -1
					BREAK
				ENDIF
			CASE 1
				IF SLOT_status[preload_slot] = -3
					audio_status_CUT --
					SLOT_status[preload_slot] = -4
				ENDIF
		ENDSWITCH
		

		SWITCH camera_status_CUT
			CASE 0
				IF TIMERA > 13500
					SET_FIXED_CAMERA_POSITION 2238.6409 -1261.3286 24.6289 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2239.6021 -1261.5890 24.5400 JUMP_CUT
					CAMERA_RESET_NEW_SCRIPTABLES	
					CAMERA_SET_SHAKE_SIMULATION_SIMPLE 1 2500.0 3.0

					camera_status_CUT ++
				ENDIF
			BREAK
					
			CASE 1
				IF TIMERA > 16000
					cutscene_skip_CUT = 0

					SKIP_CUTSCENE_END
			
					IF cutscene_skip_CUT = 0
						CAMERA_RESET_NEW_SCRIPTABLES
						RESTORE_CAMERA

						SWITCH_WIDESCREEN OFF
						SET_PLAYER_CONTROL player1 ON
					ENDIF

					GOTO mission_intro_end_of_cutscene
				ENDIF
			
	//		IF cutscene_skip_CUT = 0 
	//			DO_FADE 0 FADE_OUT
	//			SET_CHAR_COORDINATES scplayer 2212.4353 -1260.8224 22.8792
	//			SET_CHAR_HEADING scplayer 40.0
	//			TIMERA = 0
	//			WHILE TIMERA < 1000
	//				WAIT 0
	//			ENDWHILE
	//			DO_FADE 500 FADE_IN
	//		ENDIF
	//

			//WAIT 9999999999999
		ENDSWITCH	
	//ENDIF

GOTO mission_intro_carl_voice_over








mission_intro_end_of_cutscene:

	SWITCH cutscene_skip_CUT
		CASE 0 // no skip
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
		BREAK			

		CASE 1
		CASE 2
		CASE 3
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			SET_TIME_OF_DAY 06 30
			FORCE_WEATHER_NOW WEATHER_SUNNY_COUNTRYSIDE

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 

			LOAD_SCENE_IN_DIRECTION 2239.3674 -1261.9388 22.9375 272.5814
			IF IS_CHAR_IN_ANY_CAR scplayer 
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 2239.3674 -1261.9388 22.9375
			ELSE
				SET_CHAR_COORDINATES scplayer  2239.3674 -1261.9388 22.9375  
			ENDIF
			SET_CHAR_HEADING scplayer 272.5814
			IF cutscene_skip_CUT = 2 			
				STOP_BEAT_TRACK

				DELETE_MISSION_TRAINS
				SWITCH_RANDOM_TRAINS ON

				MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
				MARK_MODEL_AS_NO_LONGER_NEEDED freight
				MARK_MODEL_AS_NO_LONGER_NEEDED freiflat

				UNLOAD_SPECIAL_CHARACTER 1 // tenpenny
				UNLOAD_SPECIAL_CHARACTER 2 // pulaski
				UNLOAD_SPECIAL_CHARACTER 3 // hernandez
				
				DELETE_CHAR tenpenny_CUT
				DELETE_CHAR pulaski_CUT 
				DELETE_CHAR hernandez_CUT

				DELETE_CAR copcar_CUT

				MARK_MODEL_AS_NO_LONGER_NEEDED gangshops4_LAe2
				MARK_MODEL_AS_NO_LONGER_NEEDED CE_HillsEast06
				MARK_MODEL_AS_NO_LONGER_NEEDED cuntEground23
				MARK_MODEL_AS_NO_LONGER_NEEDED HillsEast05_LAe

				CLEAR_CHAR_TASKS scplayer
			ENDIF

			IF bike_exists_CUT = 0
				REQUEST_MODEL BMX
				WHILE NOT HAS_MODEL_LOADED BMX
					WAIT 0
				ENDWHILE				
				CLEAR_AREA 2246.5076 -1263.0870 22.9531 500.0 TRUE
				CREATE_CAR BMX 2246.5076 -1263.0870 22.9531 intro_bmx
				SET_CAR_HEADING intro_bmx 285.0
				bike_exists_CUT = 1
			ENDIF


			RESTORE_CAMERA_JUMPCUT
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_CAMERA_BEHIND_PLAYER
			
			SWITCH_WIDESCREEN OFF

			TIMERA = 0
			WHILE TIMERA < 500
				WAIT 0
			ENDWHILE

			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			DO_FADE 1500 FADE_IN 

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			//PRINT_HELP HELP_41

		BREAK

	ENDSWITCH

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON

	START_NEW_SCRIPT terminate_audio_controller
	
	DISPLAY_ZONE_NAMES TRUE

	// stop peds crossing roads as intro plays
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2380.6816 -1274.5280 22.0 2375.9453 -1239.3557 26.0
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2358.3831 -1372.6659 22.0 2382.9690 -1379.4574 25.0
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2151.6025 -1380.3746 22.0 2184.7036 -1374.7100 26.0 
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2180.7307 -1286.3281 22.0 2153.5266 -1315.7975 26.0 

	RELEASE_WEATHER
	
	USE_TEXT_COMMANDS FALSE

	SET_INT_STAT CITIES_PASSED 0

	SET_PLAYER_CONTROL player1 ON

	SET_NEAR_CLIP 0.1
	SET_AREA_VISIBLE 0
	
	SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 10			
	SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 10
	
	REQUEST_IPL Barriers1 //BROKEN BRIDGES
	REQUEST_IPL Barriers2 //BROKEN BRIDGES
	
	//Gant Bridge  ( Golden Gate )
	SWITCH_ROADS_OFF -2696.4641 1239.8665 40.7599 -2665.3591 2190.9604 70.8125    // Main Section.
	SWITCH_ROADS_OFF -2740.6941 2233.6179 40.8431 -2720.9102 2338.2244 80.4822	  // Country Section.
	SWITCH_ROADS_OFF -2695.5842 1237.9807 40.7328 -2664.4170 1454.7675 60.8126
	SWITCH_ROADS_OFF -2670.1731 1203.3314 50.4297 -2662.6604 1237.7072 60.5781 //Last minute fix for Golden gate traffic madness, may cause more chaos. Switches four nodes off.

	// The Panopticon ( Red metal bridge in D6  )
	SWITCH_ROADS_OFF -995.0013 -416.2032 30.4207 -940.5399 -251.8564 40.6762

	//Red County ( E5 Red curved bridge )
	SWITCH_ROADS_OFF -205.8387 250.7443 7.2472 -131.0039 481.8496 15.9152

	//Flint County ( E7 small road bridge )
	SWITCH_ROADS_OFF -100.7515 -927.8298 18.0 -68.3752 -891.9871 14.0

	//Hampton Barns            ( F5 Hexagonal Style framing )
	SWITCH_ROADS_OFF 609.7595 327.3437 15.8783 429.8884 616.0168 20.2890
	SWITCH_ROADS_OFF 317.1688 707.7672 7.0 437.5726 709.0657 20.5578
	SWITCH_ROADS_OFF 391.1194 640.0150 7.0 402.2627 664.7980 18.5098

	SWITCH_ROADS_OFF 289.2904 636.3991 7.8675 409.4943 702.3849 20.0345
	SWITCH_ROADS_OFF 300.3153 718.7909 7.7846 316.7906 781.0926 14.0795 
	SWITCH_ROADS_OFF 254.9982 837.0290 10.1731 270.7453 929.2404 30.2553
	SWITCH_ROADS_OFF 210.7811 939.2068 10.9062 249.4799 959.1111 30.2141

	SWITCH_ROADS_OFF 230.4545 946.0961 20.6674 255.9772 969.2755 30.4776
	SWITCH_ROADS_OFF 249.4279 899.7975 10.5871 268.6826 933.5995 30.3975
	SWITCH_ROADS_OFF 312.1081 694.1089 6.0 324.0811 733.0005 10.0
	SWITCH_ROADS_OFF 324.4526 804.9198 9.6186 332.8747 814.3560 14.3925

	//Montgomery Intersection ( I5 potential hotspot! )
	SWITCH_ROADS_OFF 1690.8192 376.5103 28.1103 1730.2230 445.2955 30.8414
	SWITCH_ROADS_OFF 1643.5355 227.3723 27.4457 1673.0623 295.5788 30.0815 
	SWITCH_ROADS_OFF 1673.7654 388.1013 40.2331	1815.8619 804.9291 10.0 // Bridge section.
	SWITCH_ROADS_OFF 1705.1558 308.3448 20.0  1710.9475 316.4094 23.5612 // Slip Road.

	//Wee metal side bridge in E8 near Flint Intersection
	SWITCH_ROADS_OFF -12.7067 -1522.4554 1.0 80.8463 -1517.1113 5.0
	SWITCH_ROADS_OFF -16.3392 -1532.8817 0.0394 69.3401 -1523.7710 5.9220 

	//Complicated tunnel bit from F8 to G8
	SWITCH_ROADS_OFF 618.7253 -1189.6063 18.0 623.5441 -1161.9812 22.0 // Main Section blocker
	SWITCH_ROADS_OFF -33.4208 -1341.8403 9.0 35.3764 -1303.9479 13.0 // Close Southbound traffic
	SWITCH_ROADS_OFF -41.2393 -1385.8701 8.0 -3.5883 -1368.8558 10.5 // Fiddly section to stop northbound traffic but keep ring round open

	//Garver Bridge	( Forth Road ) 
	SWITCH_ROADS_OFF -1690.7048 539.6102 30.3278 -1100.5674 1140.5695 50.7350
	SWITCH_ROADS_OFF -1799.5405 379.7155 16.0 -1780.1991 392.2779 18.0
	SWITCH_ROADS_OFF -1092.4293 1286.5054 30.0 -1077.0385 1319.4948 35.0
	SWITCH_ROADS_OFF -1860.1334 314.7891 38.0 -1638.5630 557.4354 40.0
	SWITCH_ROADS_OFF -1737.3331 455.9431 30.3573 -1710.3633 500.6261 40.4891
	SWITCH_ROADS_OFF -1689.2291 513.0995 30.2597 -1679.1241 524.8383 40.2500
	SWITCH_ROADS_OFF -1742.9060 500.7302 30.4679 -1650.3119 551.8201 40.7455 
	
	CLEAR_PRINTS
	//REMOVE_BLIP intro_contact_blip
	ADD_SPRITE_BLIP_FOR_COORD introX introY introZ intro_blip_icon intro_contact_blip

	//energy
	//SET_FLOAT_STAT ENERGY 800.0
	//fatness
	//SET_FLOAT_STAT FAT 200.0 
		
	//BUILD_PLAYER_MODEL player1
	
	SWITCH_ENTRY_EXIT CARLS	FALSE

	SWITCH_ENTRY_EXIT ammun1 FALSE
	SWITCH_ENTRY_EXIT ammun2 FALSE
	SWITCH_ENTRY_EXIT ammun3 FALSE
	SWITCH_ENTRY_EXIT ammun4 FALSE
	SWITCH_ENTRY_EXIT ammun5 FALSE

	SWITCH_ENTRY_EXIT barbers FALSE 
	SWITCH_ENTRY_EXIT barber2 FALSE 
	SWITCH_ENTRY_EXIT barber3 FALSE 
	SWITCH_ENTRY_EXIT FDpiza FALSE
	SWITCH_ENTRY_EXIT fdchick FALSE
	SWITCH_ENTRY_EXIT fdburg FALSE
	SWITCH_ENTRY_EXIT tattoo FALSE

	SWITCH_ENTRY_EXIT cschp	FALSE
	SWITCH_ENTRY_EXIT cssprt FALSE
	SWITCH_ENTRY_EXIT lacs1	FALSE
	SWITCH_ENTRY_EXIT clothgp FALSE
	SWITCH_ENTRY_EXIT csdesgn FALSE
	SWITCH_ENTRY_EXIT csexl	FALSE
	
	SWITCH_ENTRY_EXIT gym1 FALSE
	SWITCH_ENTRY_EXIT gym2 FALSE
	SWITCH_ENTRY_EXIT gym3 FALSE
	
	SWITCH_ENTRY_EXIT PDOMES FALSE
	SWITCH_ENTRY_EXIT PDOMES2 FALSE
	
	SWITCH_ENTRY_EXIT MADDOGS FALSE
	SWITCH_ENTRY_EXIT MDDOGS FALSE

	SWITCH_ENTRY_EXIT GANG	FALSE	// Burning Desire house
	
	SWITCH_ENTRY_EXIT RCPLAY FALSE //ZEROS

	SWITCH_ENTRY_EXIT paper FALSE 	// Switch off Vegas planning department
	SWITCH_ENTRY_EXIT abatoir FALSE	// Switch off Vegas abattoir
	
	SWITCH_ENTRY_EXIT lacrak FALSE
		  
	DEACTIVATE_GARAGE bodLAwN 		  
    DEACTIVATE_GARAGE modlast 		  
    DEACTIVATE_GARAGE mdsSFSe 		  
    DEACTIVATE_GARAGE mds1SFS //PP CAR GARAGE 
    DEACTIVATE_GARAGE vEcmod 
 	
	//DEACTIVATE_GARAGE vgshngr //Hanger
	DEACTIVATE_GARAGE dhangar	//Hanger
		   
	CLEAR_PRINTS

	SET_CHAR_HEALTH scplayer 100
	ADD_SCORE Player1 350

	SET_PLAYER_NEVER_GETS_TIRED Player1 FALSE
	REMOVE_ALL_CHAR_WEAPONS scplayer
	
	flag_intro_mission_counter = 0
	flag_sweet_mission_counter = 0
	flag_crash_mission_counter = 0
	flag_smoke_mission_counter = 0
	flag_strap_mission_counter = 0
	flag_ryder_mission_counter = 0
	flag_cesar_mission_counter = 0
	flag_la1fin1_mission_counter = 0

	drive_by_help = 0
	bike_help = 0 
	wanted_star_help = 0
	car_help_played = 0
	print_first_help = 0

	
	//RESTORE_CAMERA_JUMPCUT
	//SET_CAMERA_BEHIND_PLAYER
//	SWITCH_WIDESCREEN OFF
//	SET_PLAYER_CONTROL player1 ON
	
	
	// old bmx coords
	/*
	CLEAR_AREA 2137.04 -1262.09 23.00 500.0 TRUE
	CREATE_CAR BMX 2137.04 -1262.09 23.00 intro_bmx
	SET_CAR_HEADING intro_bmx 273.69
	//FREEZE_CAR_POSITION intro_bmx TRUE
	*/

	TIMERA = 0
	WHILE NOT TIMERA > 1000
		WAIT 0

		GOSUB bmx_help_sub

		IF NOT IS_CAR_DEAD intro_bmx
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.5076 -1263.0870 22.9531 30.0 30.0 3.0 FALSE
				GOTO mission_passed_intro
			ENDIF
		ENDIF
		
		IF IS_CHAR_IN_ANY_CAR scplayer
			CLEAR_THIS_PRINT INTRO2E
			CLEAR_THIS_PRINT HELP21  
			CLEAR_THIS_PRINT HELP26
			GOTO mission_passed_intro
		ENDIF

	ENDWHILE

	DO_FADE 1500 FADE_IN
	
	WHILE GET_FADING_STATUS
		WAIT 0
		CLEAR_PRINTS
		CLEAR_HELP
		GOSUB bmx_help_sub

		IF NOT IS_CAR_DEAD intro_bmx
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.5076 -1263.0870 22.9531 30.0 30.0 3.0 FALSE
				GOTO mission_passed_intro
			ENDIF
		ENDIF
		
		IF IS_CHAR_IN_ANY_CAR scplayer
			CLEAR_THIS_PRINT INTRO2E
			CLEAR_THIS_PRINT HELP21  
			CLEAR_THIS_PRINT HELP26
			GOTO mission_passed_intro
		ENDIF

	ENDWHILE

	PRINT_HELP HELPMO  

	IF NOT IS_CAR_DEAD intro_bmx
		ADD_BLIP_FOR_CAR intro_bmx intro_bmx_blip 
		SET_BLIP_AS_FRIENDLY intro_bmx_blip TRUE
		CHANGE_BLIP_DISPLAY intro_bmx_blip MARKER_ONLY
	ENDIF

    TIMERA = 0
	WHILE NOT TIMERA > 5000
		WAIT 0

		GOSUB bmx_help_sub

		IF NOT IS_CAR_DEAD intro_bmx
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.5076 -1263.0870 22.9531 30.0 30.0 3.0 FALSE
				GOTO mission_passed_intro
			ENDIF
		ENDIF
		
		IF IS_CHAR_IN_ANY_CAR scplayer
			REMOVE_BLIP intro_bmx_blip
			CLEAR_THIS_PRINT INTRO2E
			CLEAR_THIS_PRINT HELP21  
			CLEAR_THIS_PRINT HELP26
			GOTO mission_passed_intro
		ENDIF

	ENDWHILE

	PRINT_NOW ( INTROB ) 4000 1  //Get on the Bike!

	TIMERA = 0
	WHILE NOT TIMERA > 5000
		WAIT 0
		GOSUB bmx_help_sub
	ENDWHILE

	WHILE NOT IS_CHAR_IN_CAR scplayer intro_bmx
		WAIT 0
		
		GOSUB bmx_help_sub

		IF NOT IS_CAR_DEAD intro_bmx
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.5076 -1263.0870 22.9531 30.0 30.0 3.0 FALSE
				GOTO mission_passed_intro
			ENDIF
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			REMOVE_BLIP intro_bmx_blip
			CLEAR_THIS_PRINT INTRO2E
			CLEAR_THIS_PRINT HELP21  
			CLEAR_THIS_PRINT HELP26
			GOTO mission_passed_intro
		ENDIF

	ENDWHILE



mission_passed_intro:
RETURN


mission_intro_failed:

RETURN

mission_cleanup_intro:

	START_NEW_SCRIPT terminate_audio_controller
	//SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2442.0 -1734.0 10.0 2530.0 -1618.0 20.0
	MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
	MARK_MODEL_AS_NO_LONGER_NEEDED freight
	MARK_MODEL_AS_NO_LONGER_NEEDED freiflat

	START_NEW_SCRIPT intro_mission_loop

	START_NEW_SCRIPT cell_phone_LA1

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
	START_NEW_SCRIPT cell_phone_random

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME INTROST
	START_NEW_SCRIPT intro_stuff_loop

	REMOVE_BLIP intro_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT introX introY introZ intro_blip_icon intro_contact_blip

	MARK_MODEL_AS_NO_LONGER_NEEDED BMX
	IF NOT IS_CAR_DEAD intro_bmx
		MARK_CAR_AS_NO_LONGER_NEEDED intro_bmx
		REMOVE_BLIP	intro_bmx_blip
	ENDIF
	flag_player_on_mission = 0
	game_starts_from_scratch = 1

	MISSION_HAS_FINISHED

RETURN

bmx_help_sub:

IF NOT IS_CAR_DEAD intro_bmx
	IF play_bmx_geton_help = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.5076 -1263.0870 22.9531 2.0 2.0 2.0 FALSE
			IF controlmode = 2
				PRINT_HELP HELP26
			ELSE
				PRINT_HELP HELP21  
			ENDIF
			play_bmx_geton_help = 1
		ENDIF
	ENDIF
ENDIF

RETURN


/*
TEXT GOSUBS FOR FAKE SPLASH SCREENS
*/

LVAR_INT text_status_SS text_alpha_SS text_flag_SS text_display
LVAR_TEXT_LABEL text_label_SS

mission_intro1_SUB_text_handler:

	SWITCH text_status_SS  

		CASE 0 // fade in
			
			text_alpha_SS += 10
			
			// ARGH SWITCH STATEMENT DROPS THROUGH HERE!

		CASE 1 // fade out
			
			if text_status_SS = 1 

				text_alpha_SS -= 10	
			ENDIF

			// ARGH SWITCH STATEMENT DROPS THROUGH HERE!

		CASE 2 // display text
			IF text_display = 0
				SET_TEXT_SCALE 0.7 1.7 
				SET_TEXT_COLOUR 255 255 255 text_alpha_SS 
				SET_TEXT_CENTRE ON
				SET_TEXT_WRAPX 600.0
				SET_TEXT_DROPSHADOW 0 0 0 0 255
				DISPLAY_TEXT 320.0 180.0 LOAD_01

				SET_TEXT_SCALE 0.7 1.7 
				SET_TEXT_COLOUR 255 255 255 text_alpha_SS 
				SET_TEXT_CENTRE ON
				SET_TEXT_WRAPX 600.0
				SET_TEXT_DROPSHADOW 0 0 0 0 255
 				DISPLAY_TEXT 320.0 200.0 LOAD_03

				SET_TEXT_SCALE 0.7 1.7 
				SET_TEXT_COLOUR 255 255 255 text_alpha_SS 
				SET_TEXT_CENTRE ON
				SET_TEXT_WRAPX 600.0
				SET_TEXT_DROPSHADOW 0 0 0 0 255
				DISPLAY_TEXT 320.0 220.0 LOAD_04

			ELSE			
				SET_TEXT_SCALE 0.6 1.6 
				SET_TEXT_COLOUR 255 255 255 text_alpha_SS 
				SET_TEXT_CENTRE ON
				SET_TEXT_WRAPX 600.0
				SET_TEXT_DROPSHADOW 1 0 0 0 text_alpha_SS
				DISPLAY_TEXT 320.0 200.0 $text_label_SS 
			
			ENDIF
	ENDSWITCH	
			
RETURN
 
 
}
