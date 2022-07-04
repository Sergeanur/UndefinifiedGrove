MISSION_START

{///////////////////////////////////////////////////////////////////////////////
petrol_pumps:///////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME petrolp

LVAR_INT petrol_pump
LVAR_INT flag
flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET PETROLPUMP 0.0 0.0 0.0 petrol_pump
	//PETROLPUMPNEW
	//WASHGASPUMP
ENDIF

petrol_pump_script_loop:

	WAIT 0

	GET_GAME_TIMER game_timer
    IF DOES_OBJECT_EXIST petrol_pump
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer petrol_pump 5.0 5.0 0
				IF flag = 0
					GET_OBJECT_COORDINATES petrol_pump x y z
					LVAR_INT dummy_model_loader
					CREATE_OBJECT_NO_OFFSET	MOLOTOV x y z dummy_model_loader
					++ flag
				ENDIF
				IF flag = 1
					IF HAS_MODEL_LOADED	MOLOTOV
						//GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS petrol_pump 0.0 -1.0 0.0 x y z
						//IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
						IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer petrol_pump 1.2 1.2 0
							PRINT_HELP_FOREVER LABEL //PRESS <BUTTON> TO buy molotovs for $50
							SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE
							++ flag
						ENDIF
					ENDIF
				ENDIF
				IF flag = 2
					//GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS petrol_pump 0.0 -1.0 0.0 x y z
					//IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 0
					IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer petrol_pump 1.2 1.2 0
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							IF triangle_pressed = 0
								IF IS_SCORE_GREATER player1 49
									GET_CHAR_COORDINATES scplayer x y z
									GET_OBJECT_HEADING petrol_pump	heading
									OPEN_SEQUENCE_TASK sequence_task_obj
										TASK_ACHIEVE_HEADING -1	heading
										TASK_CHAR_SLIDE_TO_COORD -1 x y z heading 0.3
										TASK_PLAY_ANIM -1 FUCKU PED 4.0 FALSE FALSE FALSE FALSE 0
									CLOSE_SEQUENCE_TASK sequence_task_obj
									PERFORM_SEQUENCE_TASK scplayer sequence_task_obj
									CLEAR_SEQUENCE_TASK sequence_task_obj
									GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MOLOTOV 12
									ADD_SCORE player1 -50
									CLEAR_HELP
									SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
									flag = 1
								ELSE
									//PLAY NO CHANCE SOUND
								ENDIF
								triangle_pressed = 1
							ENDIF
						ELSE
							triangle_pressed = 0
						ENDIF
					ELSE
						GOSUB petrol_pump_script_cleanup
					ENDIF
				ENDIF
			ELSE
				//FIRST level cleanup (PLAYER HAS LEFT THE AREA OF OBJECT)
				GOSUB petrol_pump_script_cleanup
			ENDIF
		ELSE
			//SECOND level cleanup (PLAYER HAS DIED)
			GOSUB petrol_pump_script_cleanup
		ENDIF
	ELSE
		//FINAL level cleanup (THE OBJECT DOESNT EXIST)
		GOSUB petrol_pump_script_cleanup
		TERMINATE_THIS_SCRIPT
	ENDIF

GOTO petrol_pump_script_loop

petrol_pump_script_cleanup:
	IF flag > 0
		DELETE_OBJECT dummy_model_loader
		IF flag > 1
			SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
			CLEAR_HELP
		ENDIF
		flag = 0
	ENDIF
RETURN
}

MISSION_END

