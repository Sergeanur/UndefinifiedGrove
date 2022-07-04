MISSION_START

{///////////////////////////////////////////////////////////////////////////////
arcade_script: 	////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME ARCADE

LVAR_INT arcade_cabinet
LVAR_INT arcade_help

arcade_help = 0
IF arcade_help = -1
	CREATE_OBJECT CJ_COIN_OP_2 0.0 0.0 0.0 arcade_cabinet

//	 dual.sc CJ_COIN_OP_2   // DUALITY
//	 none.sc CJ_COIN_OP_3      
//	 none.sc SWANK_CONSOLE  // URANUS
//	 grav.sc CJ_COIN_OP_1   // BEE GAME
//	 grav.sc SNESISH
//	 shtr.sc CJ_COIN_OP     // SPACE MONKEY
//	 shtr.sc LOW_CONSOLE
ENDIF

arcade_script_loop:

WAIT 0

IF DOES_OBJECT_EXIST arcade_cabinet
	if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE arcade_cabinet
		IF IS_PLAYER_PLAYING player1
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS arcade_cabinet 0.0 -1.0 1.0 x y z
			IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 FALSE
				
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE

					IF arcade_help = 1
						CLEAR_HELP
						arcade_help = 0
						IF flag_player_on_mission = 0
							IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
								IF CAN_PLAYER_START_MISSION player1
									SET_PLAYER_CONTROL player1 OFF
									SET_EVERYONE_IGNORE_PLAYER player1 TRUE
									DO_FADE 500 FADE_OUT
									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE
									if DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet CJ_COIN_OP_2
										load_and_launch_mission_if_poss = DUAL_SC
									else
										if DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet CJ_COIN_OP_3
										or DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet SWANK_CONSOLE
											load_and_launch_mission_if_poss = NONE_SC
										else
											if DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet CJ_COIN_OP_1
											or DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet SNESISH
												load_and_launch_mission_if_poss = GRAV_SC
											else
												if DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet CJ_COIN_OP
												or DOES_OBJECT_HAVE_THIS_MODEL arcade_cabinet LOW_CONSOLE
													load_and_launch_mission_if_poss = SHTR_SC
												endif
											endif
										endif
									endif
									WAIT 0
									WAIT 0
									WAIT 0
									if is_player_playing player1
										IF flag_player_on_mission = 0
											SET_PLAYER_CONTROL player1 ON
										ENDIF
									endif
								endif
							ELSE
								if is_player_control_on player1
									PRINT_NOW BUSY 3000 1
								endif
							ENDIF
						ELSE
							if is_player_control_on player1
								PRINT_NOW BUSY 3000 1
							endif
						ENDIF
					ENDIF
				ELSE
					IF arcade_help = 0
						PRINT_HELP_FOREVER DUAL_A  
						arcade_help = 1
					ENDIF
				ENDIF
			ELSE
				IF arcade_help = 1
					CLEAR_HELP
					arcade_help = 0
				ENDIF
			ENDIF
		else
			IF arcade_help = 1
				CLEAR_HELP
				arcade_help = 0
			ENDIF
		ENDIF
	ELSE
		IF arcade_help = 1
			CLEAR_HELP
			arcade_help = 0
		ENDIF
		TERMINATE_THIS_SCRIPT
	ENDIF
ELSE
	IF arcade_help = 1
		CLEAR_HELP
		arcade_help = 0
	ENDIF
	TERMINATE_THIS_SCRIPT
ENDIF	

GOTO arcade_script_loop
}

MISSION_END
