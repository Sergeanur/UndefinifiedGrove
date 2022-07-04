MISSION_START

//{///////////////////////////////////////////////////////////////////////////////
//porta_cabin:///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//SCRIPT_NAME CABIN
//
//LVAR_INT full_model
//LVAR_INT section[3]
//LVAR_INT damage_state
//LVAR_INT ped[4]
//LVAR_INT flag
//LVAR_INT particle_fx
//LVAR_INT door
//LVAR_INT door_attractor
//LVAR_INT door_sequence
//LVAR_INT sequence
//
//flag = 0
//IF flag = 1
//	CREATE_OBJECT_NO_OFFSET PORTAKABIN 0.0 0.0 0.0 full_model
//    CREATE_OBJECT_NO_OFFSET PORTAKABIN 0.0 0.0 0.0 section[0]
//	CREATE_OBJECT_NO_OFFSET PORTAKABIN 0.0 0.0 0.0 section[1]
//	CREATE_OBJECT_NO_OFFSET PORTAKABIN 0.0 0.0 0.0 section[2]
//	CREATE_OBJECT_NO_OFFSET PORTAKABIN 0.0 0.0 0.0 door
//	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ped[0]
//	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ped[1]
//	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ped[2]
//	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ped[3]
//	OPEN_SEQUENCE_TASK door_sequence
//	CLOSE_SEQUENCE_TASK door_sequence
//	ADD_ATTRACTOR 0.0 0.0 0.0 0.0 0.0 door_sequence door_attractor
//ENDIF
//
/////////PROBLEM COMMANDS/////
////PRINT_HELP_FOREVER	  //
////USE_TEXT_COMMANDS		  //
////CREATE_OBJECT_NO_OFFSET //
//////////////////////////////
//
//porta_cabin_loop:
//
//	WAIT 0
//
//	// figure out damage state							   // use binary to store damage state
//	temp_integer_1 = 0									   //    C    B    A	  total(non-binary)	
//	IF DOES_OBJECT_EXIST section[0]						   //    0    0    0        0    NONE
//		IF HAS_OBJECT_BEEN_DAMAGED section[0]			   //    0    0    1        1	 A
//			temp_integer_1 += 1							   //    0    1    0        2	 B
//		ENDIF											   //    0    1    1        3	 AB
//	ENDIF												   //    1    0    0        4	 C
//	IF DOES_OBJECT_EXIST section[1]						   //    1    0    1        5	 AC
//		IF HAS_OBJECT_BEEN_DAMAGED section[1]			   //    1    1    0        6	 BC
//			temp_integer_1 += 2							   //    1    1    1        7	 ABC
//		ENDIF				
//	ENDIF
//	IF DOES_OBJECT_EXIST section[2]
//		IF HAS_OBJECT_BEEN_DAMAGED section[2]
//			temp_integer_1 += 4
//		ENDIF				
//	ENDIF
//	IF NOT damage_state = temp_integer_1
//		flag = 0
//	ENDIF
//	damage_state = temp_integer_1
//
//	// if sections don't exist reset flags
//	IF NOT DOES_OBJECT_EXIST section[0]
//	AND NOT DOES_OBJECT_EXIST section[1]
//	AND NOT DOES_OBJECT_EXIST section[2]
//		temp_integer_1 = 0
//		WHILE temp_integer_1 < 4
//			IF DOES_CHAR_EXIST ped[temp_integer_1]
//				DELETE_CHAR ped[temp_integer_1]
//			ENDIF
//		temp_integer_1++
//		ENDWHILE
//		damage_state = 0
//		flag = 0
//	ENDIF
//
//	// load peds inside undamaged portacabin if player gets near
//	IF IS_PLAYER_PLAYING player1
//		
//		// NO DAMAGE ++++++++++
//		IF damage_state = 0
//			IF flag = 0
//				temp_integer_1 = 0
//				WHILE temp_integer_1 < 3
//					IF DOES_OBJECT_EXIST section[temp_integer_1]
//						IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer section[temp_integer_1] 40.0 40.0 FALSE
//							flag = 1
//						ENDIF
//					ENDIF
//				temp_integer_1++
//				ENDWHILE
//				IF DOES_OBJECT_EXIST full_model
//					IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer full_model 40.0 40.0 FALSE
//						flag = 1
//					ENDIF
//				ENDIF
//			ENDIF
//			IF flag = 1
//				
//				REQUEST_MODEL P_KABINA // sectionA
//				REQUEST_MODEL P_KABINB // sectionB
//				REQUEST_MODEL P_KABINC // sectionC
//				REQUEST_MODEL CONST1 
//				WHILE NOT HAS_MODEL_LOADED P_KABINA  // sectionA
//				   OR NOT HAS_MODEL_LOADED P_KABINB  // sectionB
//				   OR NOT HAS_MODEL_LOADED P_KABINC  // sectionC 
//				   OR NOT HAS_MODEL_LOADED CONST1
//					WAIT 0
//				ENDWHILE
//				
//				// create new models
//				IF DOES_OBJECT_EXIST full_model
//					GET_OBJECT_COORDINATES full_model x y z
//					GET_GROUND_Z_FOR_3D_COORD X Y Z z
//					GET_OBJECT_HEADING full_model temp_float_1
//				ENDIF
//				
//				CREATE_OBJECT_NO_OFFSET P_KABINB x y z section[1]
//				SET_OBJECT_HEADING section[1] temp_float_1
//
//				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS full_model -3.5 0.0 0.0 x y temp_float_2
//				CREATE_OBJECT_NO_OFFSET P_KABINA x y z section[0]
//				SET_OBJECT_HEADING section[0] temp_float_1
//
//				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS full_model 3.5 0.0 0.0 x y temp_float_2
//				CREATE_OBJECT_NO_OFFSET P_KABINC x y z section[2]
//				SET_OBJECT_HEADING section[2] temp_float_1
//
//				SET_OBJECT_VISIBLE full_model FALSE
//				SET_OBJECT_COLLISION full_model FALSE
//				 
//				IF DOES_OBJECT_EXIST section[0]
//					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS section[0] 1.0 0.0 0.0 x y z
//					CREATE_CHAR PEDTYPE_CIVMALE CONST1 x y z ped[0]
//					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 360.0 temp_float_1
//					SET_CHAR_HEADING ped[0] temp_float_1  
//				ENDIF
//				IF DOES_OBJECT_EXIST section[1]
//					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS section[1] 1.0 0.0 0.0 x y z
//					CREATE_CHAR PEDTYPE_CIVMALE CONST1 x y z ped[1]
//					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 360.0 temp_float_1
//					SET_CHAR_HEADING ped[1] temp_float_1  
//
//					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS section[1] 1.0 1.0 0.0 x y z
//					CREATE_CHAR PEDTYPE_CIVMALE CONST1 x y z ped[2]
//					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 360.0 temp_float_1
//					SET_CHAR_HEADING ped[2] temp_float_1  
//				ENDIF
//				IF DOES_OBJECT_EXIST section[2]
//					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS section[2] 1.0 0.0 0.0 x y z
//					CREATE_CHAR PEDTYPE_CIVMALE CONST1 x y z ped[3]
//					GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 360.0 temp_float_1
//					SET_CHAR_HEADING ped[3] temp_float_1  
//				ENDIF
//			
//				// mark models as no longer needed
//				MARK_MODEL_AS_NO_LONGER_NEEDED P_KABINA 
//				MARK_MODEL_AS_NO_LONGER_NEEDED P_KABINB 
//				MARK_MODEL_AS_NO_LONGER_NEEDED P_KABINC 
//				MARK_MODEL_AS_NO_LONGER_NEEDED CONST1 
//
//				flag++
//			ENDIF	
//			IF flag = 2
//				temp_integer_1 = 0
//				temp_integer_2 = 0
//				IF IS_PLAYER_PLAYING player1
//					WHILE temp_integer_1 < 3
//						IF DOES_OBJECT_EXIST section[temp_integer_1]
//							IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer section[temp_integer_1] 20.0 20.0 FALSE
//								temp_integer_2++
//							ENDIF
//						ENDIF
//					temp_integer_1++
//					ENDWHILE
//				ENDIF
//				IF temp_integer_2 = 3
//					SET_OBJECT_VISIBLE full_model TRUE
//					SET_OBJECT_COLLISION full_model TRUE					
//					DELETE_OBJECT section[0] 
//					DELETE_OBJECT section[1]
//					DELETE_OBJECT section[2]
//					DELETE_CHAR ped[0]
//					DELETE_CHAR ped[1]
//					DELETE_CHAR ped[2]
//					DELETE_CHAR ped[3]
//					flag = 0
//				ENDIF
//			ENDIF
//		ENDIF
//
//		// IF ANY OF THE SECTIONS ARE FUCKED MAKE PEDS RUN OUT OF REMAINING SECTIONS
//		IF NOT damage_state = 0
//			
//			// section A is fucked
//			// or AC is fucked - unlikely
//			IF damage_state = 1
//			OR damage_state = 5
//				IF flag = 0
//					OPEN_SEQUENCE_TASK sequence
//						//TASK_GO_TO_OBJECT -1 section[0] 5000 3.0
//						TASK_FLEE_CHAR -1 scplayer 20.0 20000
//						TASK_KILL_CHAR_ON_FOOT -1 scplayer 
//					CLOSE_SEQUENCE_TASK sequence
//					temp_integer_1 = 0
//					WHILE temp_integer_1 < 4
//						IF NOT IS_CHAR_DEAD ped[temp_integer_1]
//							PERFORM_SEQUENCE_TASK ped[temp_integer_1] sequence
//							MARK_CHAR_AS_NO_LONGER_NEEDED ped[temp_integer_1]
//						ENDIF
//					temp_integer_1++
//					ENDWHILE
//					CLEAR_SEQUENCE_TASK sequence
//					flag++
//				ENDIF
//			ENDIF 
//			// section B is fucked
//			// OR AB,CB is fucked
//			IF damage_state = 2
//			OR damage_state = 3
//			OR damage_state = 6
//				IF flag = 0
//					OPEN_SEQUENCE_TASK sequence
//						//TASK_GO_TO_OBJECT -1 section[1] 5000 3.0
//						TASK_FLEE_CHAR -1 scplayer 20.0 20000
//						TASK_KILL_CHAR_ON_FOOT -1 scplayer 
//					CLOSE_SEQUENCE_TASK sequence
//					temp_integer_1 = 0
//					WHILE temp_integer_1 < 4
//						IF NOT IS_CHAR_DEAD ped[temp_integer_1]
//							PERFORM_SEQUENCE_TASK ped[temp_integer_1] sequence
//							MARK_CHAR_AS_NO_LONGER_NEEDED ped[temp_integer_1]
//						ENDIF
//					temp_integer_1++
//					ENDWHILE
//					CLEAR_SEQUENCE_TASK sequence
//					flag++
//				ENDIF
//			ENDIF		
//			// section C is fucked
//			IF damage_state = 4
//				IF flag = 0
//					OPEN_SEQUENCE_TASK sequence
//						//TASK_GO_TO_OBJECT -1 section[2] 5000 3.0
//						TASK_FLEE_CHAR -1 scplayer 20.0 20000
//						TASK_KILL_CHAR_ON_FOOT -1 scplayer 
//					CLOSE_SEQUENCE_TASK sequence
//					temp_integer_1 = 0
//					WHILE temp_integer_1 < 4
//						IF NOT IS_CHAR_DEAD ped[temp_integer_1]
//							PERFORM_SEQUENCE_TASK ped[temp_integer_1] sequence
//							MARK_CHAR_AS_NO_LONGER_NEEDED ped[temp_integer_1]
//						ENDIF
//					temp_integer_1++
//					ENDWHILE
//					CLEAR_SEQUENCE_TASK sequence
//					flag++
//				ENDIF
//			ENDIF
//			// all sections fucked
//			IF damage_state	= 7
//				IF flag = 0
//					OPEN_SEQUENCE_TASK sequence
//						TASK_FLEE_CHAR -1 scplayer 20.0 20000
//						TASK_KILL_CHAR_ON_FOOT -1 scplayer 
//					CLOSE_SEQUENCE_TASK sequence
//					temp_integer_1 = 0
//					WHILE temp_integer_1 < 4
//						IF NOT IS_CHAR_DEAD ped[temp_integer_1]
//							PERFORM_SEQUENCE_TASK ped[temp_integer_1] sequence
//							MARK_CHAR_AS_NO_LONGER_NEEDED ped[temp_integer_1]
//						ENDIF
//					temp_integer_1++
//					ENDWHILE
//					CLEAR_SEQUENCE_TASK sequence
//					flag++
//				ENDIF
//			ENDIF
//		ENDIF
//
//	ENDIF
//
//
//GOTO porta_cabin_loop
//}

MISSION_END
