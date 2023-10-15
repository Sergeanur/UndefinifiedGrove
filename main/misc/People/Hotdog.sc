MISSION_START

{///////////////////////////////////////////////////////////////////////////////
hotdog_vendor://////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME HOTDOGV

LVAR_INT vendor
LVAR_INT hotdog_van
LVAR_INT flag timer
flag = 0
IF flag = 1
  CREATE_CHAR pedtype_civmale male01 0.0 0.0 0.0 vendor
  CREATE_CAR hotdog x y z hotdog_van
ENDIF

hotdog_vendor_loop:

	WAIT 0

	GET_GAME_TIMER game_timer
	
    IF NOT IS_CHAR_DEAD vendor
		IF IS_PLAYER_PLAYING player1
			IF IS_CHAR_ATTACHED_TO_ANY_CAR vendor
				STORE_CAR_CHAR_IS_ATTACHED_TO_NO_SAVE vendor hotdog_van
				if not is_char_in_car scplayer hotdog_van
					IF flag = 0
						IF LOCATE_CHAR_ON_FOOT_CAR_2D scplayer hotdog_van 12.0 12.0 0
							REQUEST_ANIMATION VENDING
							IF HAS_ANIMATION_LOADED VENDING
								IF IS_SCORE_GREATER player1 0
									GET_OFFSET_FROM_CAR_IN_WORLD_COORDS hotdog_van 2.0 0.0 0.0 x y z
									IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 TRUE
										GET_CHAR_HEALTH scplayer temp_integer_1
										temp_integer_1 += 50
										SET_CHAR_HEALTH scplayer temp_integer_1
										TASK_PLAY_ANIM_SECONDARY scplayer vend_eat1_P VENDING 4.0 false false false false -1
										INCREMENT_INT_STAT CALORIES 10
										timer = game_timer + 3000
										add_score player1 -1
										++ flag
									endif
								endif
							endif
						endif
					ENDIF
					IF flag = 1
						if timer < game_timer
							timer = game_timer + 27000
							++ flag
						endif
					ENDIF
					IF flag = 2
						if timer < game_timer
							GET_OFFSET_FROM_car_IN_WORLD_COORDS hotdog_van 1.0 0.0 0.0 x y z
							IF not LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 false
								flag = 0
							ENDIF
						endif
					ENDIF
				else
					MARK_CHAR_AS_NO_LONGER_NEEDED vendor
					TASK_DUCK vendor -2
					REMOVE_ANIMATION VENDING
					TERMINATE_THIS_SCRIPT
				endif
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED vendor
				REMOVE_ANIMATION VENDING
				TERMINATE_THIS_SCRIPT
			endif
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED vendor
			REMOVE_ANIMATION VENDING
			TERMINATE_THIS_SCRIPT
		endif
	ELSE
		MARK_CHAR_AS_NO_LONGER_NEEDED vendor
		REMOVE_ANIMATION VENDING
		TERMINATE_THIS_SCRIPT
	endif

GOTO hotdog_vendor_loop
}

MISSION_END
