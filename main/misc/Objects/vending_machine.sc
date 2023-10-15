MISSION_START

{///////////////////////////////////////////////////////////////////////////////
vending_machine:////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME VENDING

LVAR_INT vending_machine
LVAR_INT flag timer
flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET vendmachfd 0.0 0.0 0.0 vending_machine
    CREATE_OBJECT_NO_OFFSET KB_BEER 0.0 0.0 0.0 drinks_can
ENDIF

//VENDMACHFD//drink
//VENDMACH  //drink
//VENDIN3	  //food
//CJ_SPRUNK1//drink
//CJ_CANDYVENDOR//food
//CJ_EXT_CANDY//food
//CJ_EXT_SPRUNK//drink 


vending_machine_loop:

	WAIT 0

	GET_GAME_TIMER game_timer

    IF DOES_OBJECT_EXIST vending_machine
		if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE vending_machine
			IF IS_PLAYER_PLAYING player1
				IF flag = 0
					//load anims & create extra objects etc (cleanup stuff created here in cleanup)
					REQUEST_ANIMATION VENDING
					++ flag
				ENDIF
				IF flag = 1
					IF HAS_ANIMATION_LOADED VENDING
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS vending_machine 0.0 -1.0 0.5 x y z
						IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
							if DOES_OBJECT_HAVE_THIS_MODEL vending_machine VENDIN3
							or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_CANDYVENDOR
							or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_EXT_CANDY
								LOAD_MISSION_AUDIO 4 SOUND_VENDING_EAT
								PRINT_HELP_FOREVER SLOT_04 //~t~ Buy Snack: $1
							else
								LOAD_MISSION_AUDIO 4 SOUND_DRINKS_CAN
								PRINT_HELP_FOREVER SLOT_05 //~t~ Buy Drink: $1
							endif
							SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE
							++ flag
						ENDIF
					else
						REQUEST_ANIMATION VENDING
					ENDIF
				ENDIF
				IF flag = 2
					IF HAS_ANIMATION_LOADED VENDING
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS vending_machine 0.0 -1.0 0.5 x y z
						if DOES_OBJECT_HAVE_THIS_MODEL vending_machine VENDIN3
						or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_CANDYVENDOR
						or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_EXT_CANDY
							LOAD_MISSION_AUDIO 4 SOUND_VENDING_EAT
						else
							LOAD_MISSION_AUDIO 4 SOUND_DRINKS_CAN
						endif
						IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
							
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								if CAN_PLAYER_START_MISSION player1
									IF IS_SCORE_GREATER player1 0
										if has_mission_audio_loaded 4
											GET_OBJECT_HEADING vending_machine heading
											GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS vending_machine 0.0 -0.2 0.6 x y z
											LVAR_INT drinks_can
											CREATE_OBJECT_NO_OFFSET	CJ_JUICE_CAN x y z drinks_can //KB_BEER
											GET_OBJECT_HEADING vending_machine	heading
											OPEN_SEQUENCE_TASK sequence_task_obj
												GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS vending_machine 0.2 -1.0 0.0 x y z
												TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 x y z heading 0.4 VEND_USE VENDING 4.0 FALSE FALSE FALSE TRUE 0
												if DOES_OBJECT_HAVE_THIS_MODEL vending_machine VENDIN3
												or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_CANDYVENDOR
												or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_EXT_CANDY
													TASK_PLAY_ANIM_SECONDARY -1 VEND_Eat_P VENDING 4.0 false false false false -1
												else
													GET_MODEL_DIMENSIONS CJ_JUICE_CAN x y z x y z
													y /= 2.0
													y += 0.027
													TASK_PICK_UP_OBJECT -1 drinks_can 0.062 y 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL VEND_USE_PT2 VENDING FALSE//-0.05 0.15 0.0
													TASK_PICK_UP_OBJECT -1 drinks_can 0.062 y 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL VEND_DRINK2_P VENDING FALSE//-0.05 0.15 0.0
												endif
											CLOSE_SEQUENCE_TASK sequence_task_obj
											PERFORM_SEQUENCE_TASK scplayer sequence_task_obj
											CLEAR_SEQUENCE_TASK sequence_task_obj
											ADD_SCORE player1 -1
											CLEAR_HELP

											if DOES_OBJECT_HAVE_THIS_MODEL vending_machine VENDIN3
											or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_CANDYVENDOR
											or DOES_OBJECT_HAVE_THIS_MODEL vending_machine CJ_EXT_CANDY
												LOAD_MISSION_AUDIO 4 SOUND_VENDING_EAT
											else
												LOAD_MISSION_AUDIO 4 SOUND_DRINKS_CAN
											endif
											if has_mission_audio_loaded 4
												ATTACH_MISSION_AUDIO_TO_CHAR 4 scplayer
												PLAY_MISSION_AUDIO 4
											endif
											
											timer = game_timer + 3000//800
											++ flag
										endif
									ELSE
										GET_CHAR_COORDINATES scplayer x y z
										ADD_ONE_OFF_SOUND x y z SOUND_AMMUNATION_BUY_WEAPON_DENIED
									ENDIF
								ENDIF
							ENDIF
						ELSE
							CLEAR_HELP
							SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
							flag = 1
						ENDIF
					ELSE
						REQUEST_ANIMATION VENDING
					ENDIF
				ENDIF
				IF flag = 3
					get_script_task_status scplayer PERFORM_SEQUENCE_TASK task_status
					if task_status = finished_task
					//IF timer < game_timer
						INCREMENT_INT_STAT CALORIES 5
						GET_CHAR_HEALTH scplayer temp_integer_1
						temp_integer_1 += 30
						SET_CHAR_HEALTH scplayer temp_integer_1
						MARK_OBJECT_AS_NO_LONGER_NEEDED	drinks_can
						CLEAR_HELP
						SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
						timer = game_timer + 1500
						++ flag
					ENDIF
				ENDIF
				IF flag = 4
					IF timer < game_timer
						clear_char_tasks scplayer
						REMOVE_ANIMATION VENDING
						flag = 0
					ENDIF
				ENDIF
			ELSE
				IF flag > 0
					IF flag > 1
						CLEAR_HELP
						SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
					ENDIF
					IF flag > 2
						MARK_OBJECT_AS_NO_LONGER_NEEDED	drinks_can
					ENDIF
					REMOVE_ANIMATION VENDING
					flag = 0
				ENDIF
			ENDIF
		ELSE
			IF flag > 0
				IF flag > 1
					CLEAR_HELP
					SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
				ENDIF
				IF flag > 2
					MARK_OBJECT_AS_NO_LONGER_NEEDED	drinks_can
				ENDIF
				REMOVE_ANIMATION VENDING
				flag = 0
			ENDIF
			TERMINATE_THIS_SCRIPT
		ENDIF
	ELSE
		IF flag > 0
			IF flag > 1
				CLEAR_HELP
				SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
			ENDIF
			IF flag > 2
				MARK_OBJECT_AS_NO_LONGER_NEEDED	drinks_can
			ENDIF
			REMOVE_ANIMATION VENDING
			flag = 0
		ENDIF
		TERMINATE_THIS_SCRIPT
	ENDIF//DOES_OBJECT_EXIST

GOTO vending_machine_loop
}

MISSION_END
