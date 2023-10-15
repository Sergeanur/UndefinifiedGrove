MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *********************************** trains ********************************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
{
SCRIPT_NAME trains

VAR_INT train_control_flag train_car 
VAR_FLOAT train_speed

train_speed = 0.0

// **************************************** START OF SETUP ********************************
IF NOT IS_PLAYER_PLAYING player1 
	GOTO mission_cleanup_trains
ENDIF
SET_DEATHARREST_STATE OFF 


mission_trains_loop:

WAIT 0

	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 
			IF IS_CHAR_IN_MODEL scplayer streakc

				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer train_car

				IF train_control_flag = 0
					GET_CAR_SPEED train_car train_speed 
					IF train_speed > 0.0
						PRINT_NOW ( TRAINS ) 20000 1  
						train_control_flag = 1
					ENDIF
				ENDIF
				
				IF train_control_flag = 1 
					IF IS_NEXT_STATION_ALLOWED train_car
						IF IS_BUTTON_PRESSED PAD1 CROSS 
							DO_FADE 1000 FADE_OUT
							WHILE GET_FADING_STATUS
							    WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_trains
								ENDIF
							ENDWHILE 
							IF IS_PLAYER_PLAYING player1
								IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 
									IF IS_CHAR_IN_MODEL scplayer streakc
										STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer train_car
										SKIP_TO_NEXT_ALLOWED_STATION train_car
										CLEAR_THIS_PRINT TRAINS  
									ENDIF
								ENDIF
							ENDIF
							DO_FADE 1000 FADE_IN
							WHILE GET_FADING_STATUS
							    WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_trains
								ENDIF
							ENDWHILE 
							train_control_flag = 2
						ENDIF
					ELSE
						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
						    WAIT 0
							IF NOT IS_PLAYER_PLAYING player1 
								GOTO mission_cleanup_trains
							ENDIF
						ENDWHILE 
						IF IS_PLAYER_PLAYING player1
							IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 
								IF IS_CHAR_IN_MODEL scplayer streakc
									STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer train_car
									SKIP_TO_NEXT_ALLOWED_STATION train_car
									CLEAR_THIS_PRINT TRAINS  
								ENDIF
							ENDIF
						ENDIF
						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
						    WAIT 0
							IF NOT IS_PLAYER_PLAYING player1 
								GOTO mission_cleanup_trains
							ENDIF
						ENDWHILE 
						train_control_flag = 2
					ENDIF	 			 	
				ENDIF

				IF train_control_flag = 2
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 
							IF IS_CHAR_IN_MODEL scplayer streakc
								STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer train_car
								IF IS_CAR_STOPPED train_car
									train_control_flag = 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE
				GOTO mission_cleanup_trains
			ENDIF
		ELSE
			GOTO mission_cleanup_trains
		ENDIF
	ELSE
		GOTO mission_cleanup_trains
	ENDIF

GOTO mission_trains_loop


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// mission cleanup
mission_cleanup_trains:
train_control_flag = 0
CLEAR_THIS_PRINT TRAINS  
TERMINATE_THIS_SCRIPT
MISSION_END
}
		


