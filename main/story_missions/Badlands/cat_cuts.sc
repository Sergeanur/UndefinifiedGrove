MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ********************************** Catalina Cut Scenes **********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME CATCUT

// Mission start stuff

GOSUB mission_start_catcut

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_catcut_failed
ENDIF

GOSUB mission_cleanup_catcut

MISSION_END
 
// Variables for mission
{
LVAR_INT catcut_chat_switch	catcut_index catcut_audio_is_playing catcut_cutscene_flag catcut_cell_index_end	catcut_audio_chat[8]
VAR_TEXT_LABEL $catcut_chat[8]
										   

catcut_chat_switch:

SWITCH catcut_chat_switch		   

	CONST_INT CATCUT_CHAT1 0
	
	CASE CATCUT_CHAT1

		$catcut_chat[0] = &MCAT01A	// Hello?
		$catcut_chat[1] = &MCAT01B	// Where you been, you asshole?
		$catcut_chat[2] = &MCAT01C	// Why don't you call, eh?
		$catcut_chat[3] = &MCAT01D	// Well I was just abou-
		$catcut_chat[4] = &MCAT01E	// LIAR! You've been hanging out with stinking putas!
		$catcut_chat[5] = &MCAT01F	// No! No, if you'd just let me-
		$catcut_chat[6] = &MCAT01G	// Silence! Get up here, we've got places to rob!
		$catcut_chat[7] = &MCAT01H	// Look, I'm in the middle of-

		//AUDIO LABELS
		catcut_audio_chat[0] = SOUND_MCAT01A	// Hello?
		catcut_audio_chat[1] = SOUND_MCAT01B	// Where you been, you asshole?
		catcut_audio_chat[2] = SOUND_MCAT01C	// Why don't you call, eh?
		catcut_audio_chat[3] = SOUND_MCAT01D	// Well I was just abou-
		catcut_audio_chat[4] = SOUND_MCAT01E	// LIAR! You've been hanging out with stinking putas!
		catcut_audio_chat[5] = SOUND_MCAT01F	// No! No, if you'd just let me-
		catcut_audio_chat[6] = SOUND_MCAT01G	// Silence! Get up here, we've got places to rob!
		catcut_audio_chat[7] = SOUND_MCAT01H	// Look, I'm in the middle of-
	
		catcut_cell_index_end = 7

	BREAK

ENDSWITCH

RETURN


// **************************************** Mission Start **********************************

mission_start_catcut:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

LOAD_MISSION_TEXT BCESAR2

WAIT 0



// ****************************************CESAR IN TRAILOR********************************

	SET_AREA_VISIBLE 2

	LOAD_CUTSCENE BCESAR2

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
	
// *****************************************END OF CUTSCENE*********************************

REQUEST_MODEL cellphone

WHILE NOT HAS_MODEL_LOADED cellphone
	WAIT 0
ENDWHILE

LOAD_MISSION_AUDIO 3 SOUND_MOBRING


catcut_index = 0
catcut_audio_is_playing = 0
catcut_cutscene_flag = 0
catcut_chat_switch = CATCUT_CHAT1
GOSUB catcut_chat_switch


SET_CHAR_COORDINATES scplayer -2042.1294 -2528.7905 29.6250   
SET_CHAR_HEADING scplayer 284.27
LOAD_SCENE -2042.1294 -2528.7905 29.6250

RESTORE_CAMERA_JUMPCUT

DO_FADE 1000 FADE_IN

WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0

ENDWHILE

PLAY_MISSION_AUDIO 3

TIMERA = 0

WHILE NOT HAS_MISSION_AUDIO_FINISHED 3
	WAIT 0

	IF TIMERA < 1500
		GOTO task_use_the_phone
	ENDIf

ENDWHILE

task_use_the_phone:

TASK_USE_MOBILE_PHONE scplayer TRUE

timera = 0
WHILE timera < 1500
	WAIT 0

ENDWHILE

SET_PLAYER_CONTROL player1 ON

WHILE NOT catcut_index = 8
	WAIT 0

	GOSUB load_and_play_audio_catcut

	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
		GOTO skip_catcut_mobile_call
	ENDIF

ENDWHILE

skip_catcut_mobile_call:

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2
CLEAR_PRINTS
TASK_USE_MOBILE_PHONE scplayer FALSE

GOTO mission_catcut_passed

 // **************************************** Mission catcut failed ************************

mission_catcut_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

   

// **************************************** mission catcut passed *************************

mission_catcut_passed:

	REMOVE_BLIP bcrash_contact_blip
	CLEAR_WANTED_LEVEL player1
	flag_trailor_cutscene = 1
	
	REMOVE_BLIP cat_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT catX[0] catY[0] catZ[0] cat_blip_icon cat_contact_blip //LODGE
	REMOVE_BLIP save_house_blip[14]
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[14] save_pickupY[14] save_pickupZ[14] RADAR_SPRITE_SAVEHOUSE save_house_blip[14]
	CHANGE_BLIP_DISPLAY save_house_blip[14] BLIP_ONLY
	REMOVE_PICKUP grove_save_pickup[14]
	CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[14] save_pickupY[14] save_pickupZ[14] grove_save_pickup[14] //CATS LODGE//remove
	number_of_save_icons = 15

RETURN
		

// ********************************** mission cleanup ************************************

mission_cleanup_catcut:

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

RETURN



load_and_play_audio_catcut:

	IF catcut_audio_is_playing = 0
	OR catcut_audio_is_playing = 1
		IF catcut_index <= catcut_cell_index_end
			//IF TIMERA > 1000
				GOSUB play_catcut_audio
			//ENDIF
		ENDIF
	ENDIF

	IF catcut_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			catcut_audio_is_playing = 0
			catcut_index ++
			catcut_cutscene_flag = 0
			CLEAR_PRINTS
			//TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_catcut_audio:

	IF catcut_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 catcut_audio_chat[catcut_index]
		catcut_audio_is_playing = 1
	ENDIF
	IF catcut_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $catcut_chat[catcut_index] ) 4000 1 //Let's head over to B-Dup's crib.
			PLAY_MISSION_AUDIO 1
			catcut_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN

}


