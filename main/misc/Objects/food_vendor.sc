MISSION_START

{///////////////////////////////////////////////////////////////////////////////
food_vendor:////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME FODVEND

LVAR_INT food_stand
LVAR_INT flag vendor timer
flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET icescart_prop   0.0 0.0 0.0 food_stand
  //CREATE_OBJECT_NO_OFFSET chillidogcart	0.0 0.0 0.0 food_stand
  //CREATE_OBJECT_NO_OFFSET noodlecart_prop 0.0 0.0 0.0 food_stand
  //CREATE_OBJECT_NO_OFFSET	CJ_JUICE_CAN x y z drinks_can
ENDIF


food_vendor_loop:

	WAIT 0

	GET_GAME_TIMER game_timer

    IF DOES_OBJECT_EXIST food_stand
		if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE food_stand
			IF IS_PLAYER_PLAYING player1
				if not HAS_OBJECT_BEEN_UPROOTED food_stand
					IF flag = 0
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS food_stand -1.0 0.0 -1.0 x y z
						x_temp = x - 0.5
						y_temp = y - 0.5
						z_temp = z
						temp_float_1 = x + 0.5
						temp_float_2 = y + 0.5
						temp_float_3 = z + 2.0
						if not IS_AREA_OCCUPIED x_temp y_temp z_temp temp_float_1 temp_float_2 temp_float_3 false false true false false
							if not IS_point_on_screen x y z 1.0
								request_model BMOCHIL
								if has_model_loaded BMOCHIL
									create_char pedtype_civmale BMOCHIL	x y z vendor
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS food_stand 1.0 0.0 0.0 x_temp y_temp z_temp
									x_temp -= x
									y_temp -= y
									GET_HEADING_FROM_VECTOR_2D x_temp y_temp heading
									set_char_heading vendor heading
									++ flag
								endif
							endif
						else
							mark_model_as_no_longer_needed BMOCHIL
							TERMINATE_THIS_SCRIPT
						endif
					ENDIF
					IF flag = 1
						IF LOCATE_CHAR_ON_FOOT_OBJECT_2D scplayer food_stand 8.0 8.0 0
							REQUEST_ANIMATION VENDING
							IF HAS_ANIMATION_LOADED VENDING
								IF IS_SCORE_GREATER player1 0
									GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS food_stand 1.0 0.0 0.0 x y z
									//GET_LEVEL_DESIGN_COORDS_FOR_OBJECT food_stand 1 x y z
									IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 TRUE
										GET_CHAR_HEALTH scplayer temp_integer_1
										temp_integer_1 += 50
										SET_CHAR_HEALTH scplayer temp_integer_1
										
//										LVAR_INT drinks_can
//										CREATE_OBJECT_NO_OFFSET	CJ_BURG_1 x y z drinks_can //KB_BEER
//										GET_MODEL_DIMENSIONS CJ_BURG_1 x y z x y z
//										y /= 2.0
//										y += 0.027
//										TASK_PICK_UP_OBJECT scplayer drinks_can 0.062 y 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL vend_eat1_P VENDING FALSE//VEND_DRINK2_P
										//set_object_visible drinks_can false
										//delete_object drinks_can
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
					IF flag = 2
						if timer < game_timer
//							delete_object drinks_can
							timer = game_timer + 27000
							++ flag
						endif
					ENDIF
					IF flag = 3
						if timer < game_timer
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS food_stand 1.0 0.0 0.0 x y z
							IF not LOCATE_CHAR_ON_FOOT_3D scplayer x y z 0.6 0.6 1.0 false
								flag = 1
							ENDIF
						endif
					ENDIF
				else
					IF flag > 0
						mark_char_as_no_longer_needed vendor
						IF IS_PLAYER_PLAYING player1
							if not is_char_dead	vendor
								task_kill_char_on_foot_timed vendor scplayer 10000
							endif
						endif
//						mark_object_as_no_longer_needed	drinks_can
						remove_animation VENDING
						flag = 0
					endif
					mark_model_as_no_longer_needed BMOCHIL
					TERMINATE_THIS_SCRIPT
				endif
			ELSE
				IF flag > 0
					mark_char_as_no_longer_needed vendor
//					mark_object_as_no_longer_needed	drinks_can
					remove_animation VENDING
					flag = 0
				ENDIF
				mark_model_as_no_longer_needed BMOCHIL
			ENDIF
		ELSE
			IF flag > 0
				mark_char_as_no_longer_needed vendor
//				mark_object_as_no_longer_needed	drinks_can
				remove_animation VENDING
				flag = 0
			ENDIF
			mark_model_as_no_longer_needed BMOCHIL
			TERMINATE_THIS_SCRIPT
		ENDIF
	ELSE
		IF flag > 0
			mark_char_as_no_longer_needed vendor
//			mark_object_as_no_longer_needed	drinks_can
			remove_animation VENDING
			flag = 0
		ENDIF
		mark_model_as_no_longer_needed BMOCHIL
		TERMINATE_THIS_SCRIPT
	ENDIF//DOES_OBJECT_EXIST

GOTO food_vendor_loop
}

MISSION_END
