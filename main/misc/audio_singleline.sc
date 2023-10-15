MISSION_START
MISSION_END

// GLOBALS
VAR_INT audio_line_is_active

VAR_TEXT_LABEL 	audio_string
VAR_INT 		audio_sound_file

VAR_INT audio_line_terminate_all

{
audio_line:
SCRIPT_NAME AUDIOL

// input parameters
LVAR_INT audio_ped
LVAR_INT audio_attached
LVAR_INT audio_forced
LVAR_INT audio_slot
LVAR_INT audio_is_preloaded

LVAR_TEXT_LABEL audio_text
LVAR_INT audio_sound


// workings
LVAR_INT flag
flag = 0	

// fake create
IF flag = -1	
	CREATE_CHAR PEDTYPE_CIVMALE MALE01  0.0 0.0 0.0 audio_ped
ENDIF

// check if there is another audio line currently active
IF NOT audio_line_is_active = 0
	// force this script to play
	IF audio_forced = 1
		CLEAR_MISSION_AUDIO audio_slot
		WHILE NOT audio_line_is_active = 0
			IF audio_line_terminate_all = 1
				GOTO terminate_audio_line
			ENDIF
			WAIT 0
		ENDWHILE
	ELSE
		GOTO terminate_audio_line
	ENDIF
ENDIF

$audio_text	= $audio_string
audio_sound	= audio_sound_file

audio_line_is_active = 1
audio_loop:


	IF audio_line_terminate_all = 1
		GOTO cleanup_audio_line
	ENDIF

	SWITCH flag

		CASE 0
			IF audio_is_preloaded = 0
				CLEAR_MISSION_AUDIO audio_slot
				LOAD_MISSION_AUDIO audio_slot audio_sound
			ENDIF
			IF NOT IS_CHAR_DEAD audio_ped
				DISABLE_CHAR_SPEECH audio_ped TRUE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH audio_ped TRUE
			ENDIF
			TIMERA = 0
			flag++		
		BREAK
		CASE 1
			IF HAS_MISSION_AUDIO_LOADED audio_slot
				IF NOT IS_CHAR_DEAD audio_ped
					IF NOT IS_CHAR_TALKING  audio_ped
						IF audio_attached = 1
							ATTACH_MISSION_AUDIO_TO_CHAR audio_slot audio_ped
						ENDIF
					ENDIF
					START_CHAR_FACIAL_TALK audio_ped 10000
				ENDIF
				PLAY_MISSION_AUDIO audio_slot
				IF NOT $audio_text = DUMMY
					PRINT_NOW $audio_text 10000 1
				ENDIF
				flag++
			ELSE
				IF TIMERA > 5000 // if it takes too long to load quit out
					flag++
				ENDIF		
			ENDIF
		BREAK
		CASE 2
			IF HAS_MISSION_AUDIO_LOADED	audio_slot
				IF HAS_MISSION_AUDIO_FINISHED audio_slot
					flag++
				ELSE
					IF DOES_CHAR_EXIST audio_ped
						IF IS_CHAR_DEAD audio_ped
							flag++
						ENDIF
					ENDIF
				ENDIF
			ELSE
				flag++
			ENDIF
		BREAK
		CASE 3
			GOTO cleanup_audio_line
		BREAK

	ENDSWITCH

WAIT 0
GOTO audio_loop


cleanup_audio_line:
IF NOT IS_CHAR_DEAD audio_ped
	ENABLE_CHAR_SPEECH audio_ped
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH audio_ped FALSE
	STOP_CHAR_FACIAL_TALK audio_ped
ENDIF
CLEAR_MISSION_AUDIO audio_slot
IF NOT $audio_text = DUMMY
	CLEAR_THIS_PRINT $audio_text
ENDIF
audio_line_is_active = 0


terminate_audio_line:
TERMINATE_THIS_SCRIPT

}


{
cleanup_audio_lines:
SCRIPT_NAME CLEANAU

	TIMERA = 0
	audio_line_terminate_all = 1

	WHILE NOT audio_line_is_active = 0
		WAIT 0
		IF TIMERA > 2000
			audio_line_is_active = 0
		ENDIF
	ENDWHILE

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME AUDIOL
	//CLEAR_MISSION_AUDIO 1
	//CLEAR_MISSION_AUDIO 2

	audio_line_terminate_all = 0

TERMINATE_THIS_SCRIPT
}