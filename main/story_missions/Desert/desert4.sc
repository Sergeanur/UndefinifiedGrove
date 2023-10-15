MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* desert 4 ******************************************
// ********************************** Learning To Fly **************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME desert4

// Mission start stuff

GOSUB mission_start_desert4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_desert4_failed
ENDIF

GOSUB mission_cleanup_desert4

MISSION_END

{ 
// Variables for mission
 
// **************************************** Mission Start **********************************

mission_start_desert4:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

WAIT 0

LOAD_MISSION_TEXT DSERT4

// ****************************************START OF CUTSCENE********************************

// fades the screen in 

FORCE_WEATHER WEATHER_EXTRASUNNY_DESERT

CLEAR_AREA -688.1717 937.9888 12.6328 10.0 TRUE

LOAD_CUTSCENE desert4
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

RELEASE_WEATHER

CLEAR_CUTSCENE

// ****************************************END OF CUTSCENE**********************************

SET_CHAR_COORDINATES scplayer -696.0359 948.1198 11.2760    
SET_CHAR_HEADING scplayer 33.1992
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

LOAD_SCENE -696.0359 948.1198 11.2760

DO_FADE 1000 FADE_IN

WAIT 1000

PRINT_NOW ( BUY_AIR ) 10000 1 //go buy the airstrip

GOTO mission_desert4_passed


 // **************************************** Mission desert4 failed ***********************

mission_desert4_failed:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

   
// **************************************** mission desert4 passed ************************

mission_desert4_passed:

	flag_desert_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( DESERT4 ) //Used in the stats 
	//PRINT_WITH_NUMBER_BIG ( M_PASS ) 100 5000 1 //"Mission Passed!"
	//ADD_SCORE player1 300
	//CLEAR_WANTED_LEVEL player1
	REMOVE_BLIP	desert_contact_blip
	
	SWITCH_CAR_GENERATOR area51_gen[0] 101
	SWITCH_CAR_GENERATOR area51_gen[1] 101

	START_NEW_SCRIPT airstrip_buy_loop
	REMOVE_BLIP	airstrip_contact_blip										 
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[2] propertyY[2] propertyZ[2] RADAR_SPRITE_PROPERTY_GREEN airstrip_contact_blip
	CHANGE_BLIP_DISPLAY airstrip_contact_blip BLIP_ONLY

	REMOVE_BLIP	desert2_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT pilotx piloty pilotz desert2_blip_icon desert2_contact_blip
	CHANGE_BLIP_DISPLAY desert2_contact_blip BLIP_ONLY
	
	SET_INT_STAT PASSED_DESERT4 1
	SET_MAX_WANTED_LEVEL 6
	PLAYER_MADE_PROGRESS 1	
RETURN
		

// ********************************** mission cleanup ***********************************

mission_cleanup_desert4:

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
	MISSION_HAS_FINISHED
	//UNLOAD_SPECIAL_CHARACTER 1

RETURN

 
}