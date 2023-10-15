MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***********************************    Casino 7   ***************************************
// ********************************* Clear & Present Danger ********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME CASINO7

// Mission start stuff

GOSUB mission_start_mafia1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mafia1_failed
ENDIF

GOSUB mission_cleanup_mafia1

MISSION_END

{ 
// Variables for mission



// **************************************** Mission Start **********************************

mission_start_mafia1:

LOAD_MISSION_TEXT CASINO7

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

WAIT 0

SET_AREA_VISIBLE 11

LOAD_CUTSCENE CAS_7b
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
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

SET_AREA_VISIBLE 0



// ****************************************START OF CUTSCENE********************************


// ****************************************END OF CUTSCENE**********************************

// fades the screen in 


SET_FADING_COLOUR 0 0 0

SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
REQUEST_COLLISION 2025.80 1007.87 
LOAD_SCENE_IN_DIRECTION 2025.80 1007.87 9.81 279.83

SET_CHAR_COORDINATES scplayer 2025.80 1007.87 9.81   
SET_CHAR_HEADING scplayer 279.83
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON

DO_FADE 2000 FADE_IN


GOTO mission_mafia1_passed

mission_mafia1_failed:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission mafia1 passed ************************

mission_mafia1_passed:

	REMOVE_BLIP casino_contact_blip
	flag_casino_mission_counter ++
	REGISTER_MISSION_PASSED ( CASINO7 )
	//PRINT_WITH_NUMBER_BIG ( M_PASS ) 200 5000 1 //"Mission Passed!"
	//ADD_SCORE player1 200
	SET_INT_STAT PASSED_CASINO7 1
	CLEAR_WANTED_LEVEL player1
	PLAYER_MADE_PROGRESS 1
	
RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_mafia1:

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start

MISSION_HAS_FINISHED
RETURN

 
}

