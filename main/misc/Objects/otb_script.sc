MISSION_START

{///////////////////////////////////////////////////////////////////////////////
otb_script: 	////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME OTB_MSC

LVAR_INT otb_object
LVAR_INT otb_help

otb_help = 0
IF otb_help = -1
	CREATE_OBJECT OTB_machine 0.0 0.0 0.0 otb_object
ENDIF

otb_script_loop:
	
WAIT 0

IF DOES_OBJECT_EXIST otb_object
	if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE otb_object
		IF IS_PLAYER_PLAYING player1
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS otb_object -1.0 0.0 0.0 x y z
			IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 FALSE
				
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					IF otb_help = 1
						CLEAR_HELP
						otb_help = 0
						IF flag_player_on_mission = 0
							IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
								IF CAN_PLAYER_START_MISSION player1
									SET_PLAYER_CONTROL player1 OFF
									SET_EVERYONE_IGNORE_PLAYER player1 TRUE
									DO_FADE 500 FADE_OUT
									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE
									load_and_launch_mission_if_poss = OTB_SCRIPT
									WAIT 0
									WAIT 0
									WAIT 0
									if is_player_playing player1
										IF flag_player_on_mission = 0
											SET_PLAYER_CONTROL player1 ON
										ENDIF
										GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS otb_object -2.0 0.0 -1.0 x y z
										SET_CHAR_COORDINATES scplayer x y z
									endif
								endif
							ELSE
								PRINT_NOW BUSY 3000 1
							ENDIF
						ELSE
							PRINT_NOW BUSY 3000 1
						ENDIF
					ENDIF
				ELSE
					IF otb_help = 0
						PRINT_HELP_FOREVER OTB_A  
						otb_help = 1
					ENDIF
				ENDIF
			ELSE
				IF otb_help = 1
					CLEAR_HELP
					otb_help = 0
				ENDIF
			ENDIF
		ELSE
			IF otb_help = 1
				CLEAR_HELP
				otb_help = 0
			ENDIF
		ENDIF
	ELSE
		IF otb_help = 1
			CLEAR_HELP
			otb_help = 0
		ENDIF
		terminate_this_script
	ENDIF
ELSE
	IF otb_help = 1
		CLEAR_HELP
		otb_help = 0
	ENDIF
	terminate_this_script
ENDIF	

GOTO otb_script_loop
}

MISSION_END
