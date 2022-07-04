MISSION_START

{///////////////////////////////////////////////////////////////////////////////
gates_script://////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME GATEOS

LVAR_INT gate
LVAR_INT flag
LVAR_FLOAT gate_targetX gate_targetY gate_targetZ
LVAR_FLOAT gate_oldX gate_oldY gate_oldZ
LVAR_INT gate_slide_sound

flag = 0
IF flag = 1
    CREATE_OBJECT_NO_OFFSET gate_autoL 0.0 0.0 0.0 gate
    CREATE_OBJECT_NO_OFFSET gate_autoR 0.0 0.0 0.0 gate
ENDIF

gate_loop:

	WAIT 0

	GET_GAME_TIMER game_timer

    IF DOES_OBJECT_EXIST gate
		if IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE gate
			IF flag = 0
				IF IS_PLAYER_PLAYING player1
					lvar_int gate_model
					GET_OBJECT_MODEL gate gate_model
					if HAS_MODEL_LOADED gate_model
						IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer gate 12.0 12.0 FALSE
							IF IS_CHAR_IN_MODEL scplayer BARRACKS
							OR IS_CHAR_IN_MODEL scplayer PATRIOT
							OR IS_CHAR_IN_MODEL scplayer RHINO
							//OR IS_CHAR_IN_MODEL scplayer MESA
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 5 temp_float_1 temp_float_2 temp_float_3
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 6 x_temp y_temp z_temp
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 7 x y z
								GET_DISTANCE_BETWEEN_COORDS_2D x_temp y_temp x y z
								z *= -1.0
								IF IS_CHAR_IN_ANGLED_AREA_3D scplayer temp_float_1 temp_float_2 temp_float_3 x_temp y_temp z_temp z FALSE
									flag = 1
								ENDIF
							ENDIF
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 1 temp_float_1 temp_float_2 temp_float_3
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 2 x_temp y_temp z_temp
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 3 x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x_temp y_temp x y z
							IF IS_CHAR_IN_ANGLED_AREA_3D scplayer temp_float_1 temp_float_2 temp_float_3 x_temp y_temp z_temp z FALSE
								flag = 1
							ENDIF
						ENDIF
						IF open_gate_now_flag = 1
							flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF flag = 1
				GET_OBJECT_MODEL gate gate_model
				if HAS_MODEL_LOADED gate_model
					GET_LEVEL_DESIGN_COORDS_FOR_OBJECT gate 0 gate_targetX gate_targetY gate_targetZ
					GET_OBJECT_COORDINATES gate gate_oldX gate_oldY gate_oldZ

//					IF gate_sound_in_use = 0
						//GET_OBJECT_COORDINATES gate x y z
						//ADD_CONTINUOUS_SOUND x y z SOUND_GARAGE_DOOR_SLIDING_LOOP gate_slide_sound
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate SOUND_MESH_GATE_OPEN_START
//						gate_sound_in_use = 1
//					ENDIF
					++ flag
				ENDIF
			ENDIF
			
			IF flag = 2
//				IF gate_sound_in_use = 1
//					REMOVE_SOUND gate_slide_sound
//					gate_sound_in_use = 0
//				ENDIF
				IF SLIDE_OBJECT gate gate_targetX gate_targetY gate_targetZ 0.1 0.1 0.1 TRUE
					IF LOCATE_OBJECT_3D gate gate_targetX gate_targetY gate_targetZ 0.1 0.1 0.1 FALSE
						SET_OBJECT_COORDINATES gate gate_targetX gate_targetY gate_targetZ 
						//ADD_ONE_OFF_SOUND gate_targetX gate_targetY gate_targetZ SOUND_GARAGE_DOOR_CLUNK
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate SOUND_MESH_GATE_OPEN_STOP
						++ open_gate_now_flag
						++ flag
					ENDIF
				ENDIF
			ENDIF

			IF flag = 3
				if gate_stay_open = 0
					++ flag
				endif 
			ENDIF
			
			IF flag = 4 
				IF IS_PLAYER_PLAYING player1
					IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer gate 20.0 20.0 FALSE
//						IF gate_sound_in_use = 0
							//GET_OBJECT_COORDINATES gate x y z
							//ADD_CONTINUOUS_SOUND x y z SOUND_GARAGE_DOOR_SLIDING_LOOP gate_slide_sound
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate SOUND_MESH_GATE_OPEN_START
//							gate_sound_in_use = 1
//						ENDIF
						++ flag
					ENDIF
				ENDIF
			ENDIF
				
			IF flag = 5
//				IF gate_sound_in_use = 1
//					REMOVE_SOUND gate_slide_sound
//					gate_sound_in_use = 0
//				ENDIF	
				IF SLIDE_OBJECT gate gate_oldX gate_oldY gate_oldZ  0.1 0.1 0.1 TRUE
					IF LOCATE_OBJECT_3D gate gate_oldX gate_oldY gate_oldZ 0.1 0.1 0.1 FALSE
						SET_OBJECT_COORDINATES gate	gate_oldX gate_oldY gate_oldZ
						//ADD_ONE_OFF_SOUND gate_oldX gate_oldY gate_oldZ SOUND_GARAGE_DOOR_CLUNK
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate SOUND_MESH_GATE_OPEN_STOP
						flag = 0
					ENDIF
				ENDIF		
			ENDIF
		ELSE
			TERMINATE_THIS_SCRIPT
		ENDIF			
	ELSE
		TERMINATE_THIS_SCRIPT
	ENDIF			
		 
GOTO gate_loop
}

MISSION_END
