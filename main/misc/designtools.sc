MISSION_START

{
VAR_INT design_mode
VAR_INT dt_temp_int
VAR_FLOAT dt_corona_x	dt_corona_y	dt_corona_z

VAR_INT dt_temp_int1
VAR_FLOAT dt_temp_float



SCRIPT_NAME DTOOLS

SET_DEATHARREST_STATE OFF

design_tools_loop:
// #################################################################################################################################################
// 														SELECT DESIGN MODE 
// #################################################################################################################################################

// enter object placer mode
IF NOT design_mode = 1
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F1
		IF NOT IS_DEBUG_CAMERA_ON 																	
			PRINT_HELP OP1																			
		ELSE																						
			CLEAR_HELP																				
			PRINT_HELP_FOREVER OP2																	
			op_modelID = 0																			
			
			// MODELS - CHANGE HERE IF YOU WANT TO USE DIFFERENT MODELS																														
//			VAR_TEXT_LABEL $op_string[10]
//			$op_string[0] = TEMP_CRATE1															
//			$op_string[1] = DYNO_BOX_B																
//			$op_string[2] = SMASHBOXPILE															
//			$op_string[3] = CARDBOARDBOX4															
//			$op_string[4] = BLOCKPALLET																	
//			$op_string[5] = IMY_BBOX		
//			$op_string[6] = DYNO_BOX_A	
//			$op_string[7] = CASKET_LAW	
//			$op_string[8] = CARDBOARDBOX2
//			$op_string[9] = WOODENBOX	

	
	





			op_models[0] = K_POOLTABLESM											
			op_models[1] = PRIVATESIGN2												
			op_models[2] = PRIVATESIGN3										
			op_models[3] = PRIVATESIGN4															
			op_models[4] = BARREL3												
			op_models[5] = trashcan				
			op_models[6] = KMB_CONTAINER_OPEN	
			op_models[7] = KMB_CONTAINER_RED	
			op_models[8] = MAGNOCRANE_01		
			op_models[9] = KMB_HOLDALL			
			op_last_modelID = op_modelID
			op_current_object = 0
			op_last_object = -1
			design_mode = 1
			op_distance = 10.0	
			op_help = 0
			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
		ENDIF
	ENDIF
ENDIF
	
// enter car placer mode
IF NOT design_mode = 2
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F2
		IF NOT IS_DEBUG_CAMERA_ON 																	
			PRINT_HELP OP1																			
		ELSE																						
			CLEAR_HELP																				
			PRINT_HELP_FOREVER OP2																	
			cp_modelID = 0																			
			
			// MODELS - CHANGE HERE IF YOU WANT TO USE DIFFERENT MODELS																														
			cp_models[0] = QUAD															
			cp_models[1] = SANCHEZ																
			cp_models[2] = SAVANNA															
			cp_models[3] = VOODOO															
			cp_models[4] = ESPERANT 																	
			cp_models[5] = STRETCH		
			cp_models[6] = RCBANDIT	
			cp_models[7] = RCGOBLIN
			cp_models[8] = BANDITO
			cp_models[9] = MAVERICK	
			
			cp_last_modelID = cp_modelID
			cp_current_car = 0
			cp_last_car = -1
			design_mode = 2
			cp_distance = 10.0	
			cp_help = 0

			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// enter ped placer mode
IF NOT design_mode = 3
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F3
		IF NOT IS_DEBUG_CAMERA_ON 																	
			PRINT_HELP OP1																			
		ELSE																						
			CLEAR_HELP																				
			PRINT_HELP_FOREVER OP2																	
			pp_modelID = 9																			
			
//			// MODELS - CHANGE HERE IF YOU WANT TO USE DIFFERENT MODELS																														
//			pp_models[0] = 		MALE01 //WBACKUP 
//			pp_models[1] = 		BFORI 
//			pp_models[2] = 		BFOST 
//			pp_models[3] = 		BFYCRP
//			pp_models[4] = 		BFYRI 
//			pp_models[5] = 		BFYST 
//			pp_models[6] = 		BMORI 
//			pp_models[7] = 		BMOST 
//			pp_models[8] = 		BMYAP 
//			pp_models[9] = 		BMYBB 
//
//
//			pp_modelID = 0
//													
			pp_last_modelID = pp_modelID
			
			WHILE NOT IS_MODEL_IN_CDIMAGE pp_modelID
				pp_modelID++
				IF pp_modelID > 399
					pp_modelID = 9
				ENDIF
			ENDWHILE
			
			pp_current_ped = 0
			pp_last_ped = -1
			design_mode = 3
			pp_distance = 10.0	
			pp_help = 0


			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// enter locate placer mode
IF NOT design_mode = 4
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F3
		IF NOT IS_DEBUG_CAMERA_ON 																	
			//PRINT_HELP OP1																			
		ELSE																						
			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// enter screen edit mode
IF NOT design_mode = 5
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F5																					
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 OFF
		ENDIF
		design_mode = 5
	ENDIF
ENDIF

// enter matrix cam setup
IF NOT design_mode = 6
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F6																					
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 OFF
		ENDIF
		design_mode = 6
	ENDIF
ENDIF

// enter chris's race-coord finder mode
IF NOT design_mode = 9
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F9
		// get race coords part1/////////////////
		VAR_INT coords_DT
		coords_DT = 0
		VIEW_INTEGER_VARIABLE coords_DT coords
		VIEW_FLOAT_VARIABLE separator_distance separator_distance
		separator_distance = 300.0
		design_mode = 9
	ENDIF
ENDIF

// #################################################################################################################################################
// 															OBJECT PLACER TOOL 
// #################################################################################################################################################
VAR_INT op_object[30] 
VAR_FLOAT op_x1 op_y1 op_z1
VAR_FLOAT op_x2 op_y2 op_z2
VAR_FLOAT op_x3 op_y3 op_z3
VAR_FLOAT op_distance
VAR_FLOAT op_object_x op_object_y op_object_z op_object_h
VAR_FLOAT op_object_rx op_object_ry op_object_rz
VAR_INT op_object_released[30]
VAR_INT op_current_object op_last_object
VAR_INT op_modelID op_last_modelID
VAR_INT op_temp_int
VAR_INT op_help
VAR_INT op_models[10]
VAR_INT op_found_object_after_deletion
GOTO op_skip
	dt_temp_int1 = 0
	WHILE dt_temp_int1 < 30
		CREATE_OBJECT bar_barrier10 0.0 0.0 0.0  op_object[dt_temp_int1]
	dt_temp_int1++
	ENDWHILE
op_skip:

// running object placer mode
IF design_mode = 1

	// === UPDATE CURRENT GRABBED OBJECT ===
	// Get camera point at coordinates
	IF op_object_released[op_current_object] = 0
		GET_DEBUG_CAMERA_POINT_AT op_x1 op_y1 op_z1
		GET_DEBUG_CAMERA_COORDINATES op_x2 op_y2 op_z2
		// get vector AB
		op_x3 = op_x1 - op_x2
		op_y3 = op_y1 - op_y2
		op_z3 = op_z1 - op_z2
		// multiply by distance
		op_x3 *= op_distance
		op_y3 *= op_distance
		op_z3 *= op_distance
		// add to camera position
		op_object_x = op_x3 + op_x2 
		op_object_y = op_y3 + op_y2
		op_object_z = op_z3 + op_z2

		IF DOES_OBJECT_EXIST op_object[op_current_object]
			SET_OBJECT_COORDINATES op_object[op_current_object] op_object_x op_object_y op_object_z
			SET_OBJECT_HEADING op_object[op_current_object] op_object_h	
		ENDIF
	ENDIF

	// === SWITCH CURRENTLY SELECTED OBJECT ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PERIOD
		op_current_object++
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_COMMA
		op_current_object--
		WAIT 100
	ENDIF
	IF op_current_object > 29
		op_current_object = 0
	ENDIF
	IF op_current_object < 0
		op_current_object = 29
	ENDIF
	IF NOT op_current_object = op_last_object
		
		op_object_rx = 0.0
		op_object_ry = 0.0
		op_object_rz = 0.0
		
		// set last object to released
		IF op_last_object > -1
			IF DOES_OBJECT_EXIST op_object[op_last_object]
				SET_OBJECT_COLLISION op_object[op_last_object] TRUE
				op_object_released[op_last_object] = 1		   	
			ENDIF
		ENDIF

		// switch objects	
		IF NOT DOES_OBJECT_EXIST op_object[op_current_object]
			REQUEST_MODEL op_models[op_modelID]
			WHILE NOT HAS_MODEL_LOADED op_models[op_modelID]
				WAIT 0
			ENDWHILE
			VAR_INT op_model_model[30]
			op_model_model[op_current_object] = op_models[op_modelID]
			CREATE_OBJECT op_models[op_modelID] op_object_x op_object_y op_object_z op_object[op_current_object]
			SET_OBJECT_HEADING op_object[op_current_object] op_object_h
			SET_OBJECT_DYNAMIC op_object[op_current_object] FALSE
			SET_OBJECT_COLLISION op_object[op_current_object] FALSE
			GET_AREA_VISIBLE dt_temp_int
			SET_OBJECT_AREA_VISIBLE op_object[op_current_object] dt_temp_int
			op_object_released[op_current_object] = 0 
		ENDIF
		op_last_object = op_current_object
	ENDIF
	 
	// === DELETE CURRENTLY SELECTED OBJECT ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DEL
		// delete current object
		IF DOES_OBJECT_EXIST op_object[op_current_object]
			DELETE_OBJECT op_object[op_current_object]
		ENDIF
		// move to forward to next object if it exists
		op_found_object_after_deletion = 0
		dt_temp_int = op_current_object
		dt_temp_int1 = op_current_object - 1
		IF dt_temp_int1 < 0
			dt_temp_int1 = 29
		ENDIF
		WHILE NOT dt_temp_int1 = dt_temp_int
			IF DOES_OBJECT_EXIST op_object[dt_temp_int1]
				dt_temp_int = dt_temp_int1
				op_found_object_after_deletion = 1
			ENDIF
			IF op_found_object_after_deletion = 0
				dt_temp_int1--
				IF dt_temp_int1 < 0
					dt_temp_int1 = 29
				ENDIF 
			ENDIF
		ENDWHILE
		IF op_found_object_after_deletion = 1
			op_current_object = dt_temp_int
		ELSE
			op_last_object = -1	// if no other objects are found a new one will be created
		ENDIF
		WAIT 500
	ENDIF

	// === SWITCH CURRENTLY SELECTED OBJECT'S MODEL ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGUP
		op_modelID++
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGDN
		op_modelID--
		WAIT 100
	ENDIF
	IF op_modelID > 9
		op_modelID = 0
	ENDIF
	IF op_modelID < 0
		op_modelID = 9
	ENDIF  
	IF NOT op_modelID = op_last_modelID
		IF DOES_OBJECT_EXIST op_object[op_current_object] 
			GET_OBJECT_COORDINATES op_object[op_current_object]	op_object_x op_object_y op_object_z
			GET_OBJECT_HEADING op_object[op_current_object] op_object_h	 
			DELETE_OBJECT op_object[op_current_object]
		ENDIF  
		MARK_MODEL_AS_NO_LONGER_NEEDED op_models[op_last_modelID]
		REQUEST_MODEL op_models[op_modelID]
		WHILE NOT HAS_MODEL_LOADED op_models[op_modelID]
			WAIT 0
		ENDWHILE
		op_model_model[op_current_object] = op_models[op_modelID]
		CREATE_OBJECT op_models[op_modelID] op_object_x op_object_y op_object_z op_object[op_current_object]
		SET_OBJECT_HEADING op_object[op_current_object] op_object_h
		IF op_object_released[op_current_object] = 0
			SET_OBJECT_DYNAMIC op_object[op_current_object] FALSE
			SET_OBJECT_COLLISION op_object[op_current_object] FALSE
		ENDIF 
		GET_AREA_VISIBLE dt_temp_int
		SET_OBJECT_AREA_VISIBLE op_object[op_current_object] dt_temp_int

		op_last_modelID = op_modelID
	ENDIF

	// === UNGRAB CURRENT OBJECT ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		IF op_object_released[op_current_object] = 0
			IF DOES_OBJECT_EXIST op_object[op_current_object]
				SET_OBJECT_COLLISION op_object[op_current_object] TRUE
				op_object_released[op_current_object] = 1
			ENDIF
		ELSE
			IF DOES_OBJECT_EXIST op_object[op_current_object]
				SET_OBJECT_COLLISION op_object[op_current_object] FALSE
				SET_OBJECT_DYNAMIC op_object[op_current_object] FALSE
				op_object_released[op_current_object] = 0
			ENDIF
		ENDIF
		WAIT 100				
	ENDIF

	// === APPLY GRAVITY TO OBJECT ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
		IF op_object_released[op_current_object] = 1 
			IF DOES_OBJECT_EXIST op_object[op_current_object]
				SET_OBJECT_DYNAMIC op_object[op_current_object] TRUE
				SET_OBJECT_VELOCITY op_object[op_current_object] 0.0 0.0 -1.0
			ENDIF 
		ELSE
			IF DOES_OBJECT_EXIST op_object[op_current_object]
				SET_OBJECT_DYNAMIC op_object[op_current_object] TRUE
				SET_OBJECT_COLLISION op_object[op_current_object] TRUE
				SET_OBJECT_VELOCITY op_object[op_current_object] 0.0 0.0 -1.0 
				op_object_released[op_current_object] = 1
			ENDIF
		ENDIF
	ENDIF

	// === MOVE OBJECT ===
	IF op_object_released[op_current_object] = 1
		IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
		OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKX
			IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				IF DOES_OBJECT_EXIST op_object[op_current_object]
					GET_OBJECT_COORDINATES op_object[op_current_object] op_object_x op_object_y op_object_z
					GET_OBJECT_HEADING op_object[op_current_object] op_object_h
			 		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			 		RStickX_F =# RStickX 
			 		RStickY_F =# RStickY
			 		RStickX_F /= 1000.0
			 		RStickY_F /= 1000.0
					dt_temp_float =# LStickY
					dt_temp_float /= 5000.0

					GOSUB dt_update_camera_vector
					dt_cam_nvec_x *= RStickY_F
					dt_cam_nvec_y *= RStickY_F
					dt_cam_nvec_x *= -1.0
					dt_cam_nvec_y *= -1.0
			 		op_object_x += dt_cam_nvec_x
			 		op_object_y += dt_cam_nvec_y

					dt_cam_rvec_x *= RStickX_F
					dt_cam_rvec_y *= RStickX_F
					op_object_x += dt_cam_rvec_x
			 		op_object_y += dt_cam_rvec_y
					
					op_object_z += dt_temp_float
			 		SET_OBJECT_COORDINATES op_object[op_current_object] op_object_x op_object_y op_object_z 
					dt_temp_float =# LStickX
					dt_temp_float /= 100.0
				   	op_object_h += dt_temp_float
					SET_OBJECT_HEADING op_object[op_current_object]	op_object_h	   
				ENDIF
			ELSE
		 		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
		 		RStickX_F =# RStickX 
		 		RStickY_F =# RStickY
		 		RStickX_F /= 100.0
		 		RStickY_F /= 100.0
				dt_temp_float =# LStickY
				dt_temp_float /= 100.0
				op_object_rx += RStickX_F
				op_object_ry += RStickY_F
				op_object_rz += dt_temp_float
		 		SET_OBJECT_ROTATION op_object[op_current_object] op_object_rx op_object_ry op_object_rz 	
			ENDIF
	 	ENDIF	
	ENDIF

	// === DISPLAY CORONA ===
	IF DOES_OBJECT_EXIST op_object[op_current_object]
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS op_object[op_current_object] 0.0 0.0 1.0 dt_corona_x dt_corona_y dt_corona_z
		IF op_object_released[op_current_object] = 0
			DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.2 CORONATYPE_MOON FLARETYPE_NONE 255 0 0
		ELSE
			DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.2 CORONATYPE_MOON FLARETYPE_NONE 0 255 0
		ENDIF	
	ENDIF

	// === DISPLAY HELP ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
		IF op_help = 0
			PRINT_HELP OP3
			op_help = 1
		ENDIF
	ELSE
		IF op_help = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER OP2	
			op_help = 0
		ENDIF	
	ENDIF


ENDIF

// #################################################################################################################################################
// 															CAR PLACER TOOL 
// #################################################################################################################################################
VAR_INT cp_car[10] 
VAR_FLOAT cp_x1 cp_y1 cp_z1
VAR_FLOAT cp_x2 cp_y2 cp_z2
VAR_FLOAT cp_x3 cp_y3 cp_z3
VAR_FLOAT cp_distance
VAR_FLOAT cp_car_x cp_car_y cp_car_z cp_car_h
VAR_FLOAT cp_car_rx cp_car_ry cp_car_rz
VAR_INT cp_car_released[10]
VAR_INT cp_current_car cp_last_car
VAR_INT cp_modelID cp_last_modelID
VAR_INT cp_temp_int
VAR_INT cp_help
VAR_INT cp_models[10]
VAR_INT cp_found_car_after_deletion
VAR_FLOAT cp_car_last_x cp_car_last_y cp_car_last_z
GOTO cp_skip
	dt_temp_int1 = 0
	WHILE dt_temp_int1 < 10
		CREATE_CAR MULE 0.0 0.0 0.0  cp_car[dt_temp_int1]
	dt_temp_int1++
	ENDWHILE
cp_skip:

// running car placer mode
IF design_mode = 2


//	IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
//		IF NOT IS_CAR_DEAD cp_car[cp_current_car]
//			GET_CAR_COORDINATES cp_car[cp_current_car] x y z
//			SET_CAR_COORDINATES cp_car[cp_current_car] x y z
//		ENDIF
//	ENDIF 

	// === UPDATE CURRENT GRABBED CAR ===
	// Get camera point at coordinates
	IF cp_car_released[cp_current_car] = 0
		GET_DEBUG_CAMERA_POINT_AT cp_x1 cp_y1 cp_z1
		GET_DEBUG_CAMERA_COORDINATES cp_x2 cp_y2 cp_z2
		// get vector AB
		cp_x3 = cp_x1 - cp_x2
		cp_y3 = cp_y1 - cp_y2
		cp_z3 = cp_z1 - cp_z2
		// multiply by distance
		cp_x3 *= cp_distance
		cp_y3 *= cp_distance
		cp_z3 *= cp_distance
		// add to camera position
		cp_car_x = cp_x3 + cp_x2 
		cp_car_y = cp_y3 + cp_y2
		cp_car_z = cp_z3 + cp_z2

		IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
			IF NOT IS_CAR_DEAD cp_car[cp_current_car]
				SET_CAR_COORDINATES cp_car[cp_current_car] cp_car_x cp_car_y cp_car_z
				SET_CAR_HEADING cp_car[cp_current_car] cp_car_h	
				cp_car_last_x =	cp_car_x 
				cp_car_last_y =	cp_car_y
				cp_car_last_z =	cp_car_z
			ENDIF			  
		ENDIF
	ENDIF

	// === SWITCH CURRENTLY SELECTED CAR ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PERIOD
		cp_current_car++
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_COMMA
		cp_current_car--
		WAIT 100
	ENDIF
	IF cp_current_car > 9
		cp_current_car = 0
	ENDIF
	IF cp_current_car < 0
		cp_current_car = 9
	ENDIF
	IF NOT cp_current_car = cp_last_car
		
		cp_car_rx = 0.0
		cp_car_ry = 0.0
		cp_car_rz = 0.0
		
		// set last car to released
		IF cp_last_car > -1
			IF DOES_VEHICLE_EXIST cp_car[cp_last_car]
				IF NOT IS_CAR_DEAD cp_car[cp_last_car]
					FREEZE_CAR_POSITION cp_car[cp_last_car] TRUE
					cp_car_released[cp_last_car] = 1	
				ENDIF	   	
			ENDIF
		ENDIF

		// switch cars	
		IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
			IF NOT IS_CAR_DEAD cp_car[cp_current_car]
				GET_CAR_COORDINATES cp_car[cp_current_car] cp_car_last_x cp_car_last_y cp_car_last_z
			ENDIF
		ELSE
			REQUEST_MODEL cp_models[cp_modelID]
			WHILE NOT HAS_MODEL_LOADED cp_models[cp_modelID]
				WAIT 0
			ENDWHILE
			CREATE_CAR cp_models[cp_modelID] cp_car_x cp_car_y cp_car_z cp_car[cp_current_car]
			SET_CAR_CAN_BE_DAMAGED cp_car[cp_current_car] FALSE
			SET_CAR_HEADING cp_car[cp_current_car] cp_car_h
			FREEZE_CAR_POSITION cp_car[cp_current_car] TRUE
			GET_AREA_VISIBLE dt_temp_int
			SET_VEHICLE_AREA_VISIBLE cp_car[cp_current_car] dt_temp_int

			cp_car_released[cp_current_car] = 0 
		ENDIF
		cp_last_car = cp_current_car
	ENDIF
	 
	// === DELETE CURRENTLY SELECTED CAR ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DEL
		// delete current car
		IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
			DELETE_CAR cp_car[cp_current_car]
		ENDIF
		// move to forward to next car if it exists
		cp_found_car_after_deletion = 0
		dt_temp_int = cp_current_car
		dt_temp_int1 = cp_current_car - 1
		IF dt_temp_int1 < 0
			dt_temp_int1 = 9
		ENDIF
		WHILE NOT dt_temp_int1 = dt_temp_int
			IF DOES_VEHICLE_EXIST cp_car[dt_temp_int1]
				dt_temp_int = dt_temp_int1
				cp_found_car_after_deletion = 1
			ENDIF
			IF cp_found_car_after_deletion = 0
				dt_temp_int1--
				IF dt_temp_int1 < 0
					dt_temp_int1 = 9
				ENDIF 
			ENDIF
		ENDWHILE
		IF cp_found_car_after_deletion = 1
			cp_current_car = dt_temp_int
		ELSE
			cp_last_car = -1	// if no other cars are found a new one will be created
		ENDIF
		WAIT 500
	ENDIF

	// === SWITCH CURRENTLY SELECTED CAR'S MODEL ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGUP
		cp_modelID++
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGDN
		cp_modelID--
		WAIT 100
	ENDIF
	IF cp_modelID > 9
		cp_modelID = 0
	ENDIF
	IF cp_modelID < 0
		cp_modelID = 9
	ENDIF  
	IF NOT cp_modelID = cp_last_modelID
		IF NOT IS_CAR_DEAD cp_car[cp_current_car]
			GET_CAR_COORDINATES cp_car[cp_current_car]	cp_car_x cp_car_y cp_car_z
			GET_CAR_HEADING cp_car[cp_current_car] cp_car_h
		ENDIF
		MARK_MODEL_AS_NO_LONGER_NEEDED cp_models[cp_last_modelID]
		DELETE_CAR cp_car[cp_current_car]
		REQUEST_MODEL cp_models[cp_modelID]
		WHILE NOT HAS_MODEL_LOADED cp_models[cp_modelID]
			WAIT 0
		ENDWHILE
		CREATE_CAR cp_models[cp_modelID] cp_car_x cp_car_y cp_car_z cp_car[cp_current_car]
		SET_CAR_CAN_BE_DAMAGED cp_car[cp_current_car] FALSE
		SET_CAR_HEADING cp_car[cp_current_car] cp_car_h
		GET_AREA_VISIBLE dt_temp_int
		SET_VEHICLE_AREA_VISIBLE cp_car[cp_current_car] dt_temp_int

		cp_last_modelID = cp_modelID
	ENDIF

	// === UNGRAB CURRENT CAR ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		IF cp_car_released[cp_current_car] = 0
			IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					FREEZE_CAR_POSITION cp_car[cp_current_car] TRUE 
					cp_car_released[cp_current_car] = 1
				ENDIF 
			ENDIF
		ELSE
			IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					FREEZE_CAR_POSITION cp_car[cp_current_car] TRUE
					cp_car_released[cp_current_car] = 0
				ENDIF
			ENDIF
		ENDIF
		WAIT 100				
	ENDIF

	// === APPLY GRAVITY TO CAR ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
		IF cp_car_released[cp_current_car] = 1 
			IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					FREEZE_CAR_POSITION cp_car[cp_current_car] FALSE
				ENDIF
			ENDIF 
		ELSE
			IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					FREEZE_CAR_POSITION cp_car[cp_current_car] FALSE
					cp_car_released[cp_current_car] = 1
				ENDIF 
			ENDIF
		ENDIF
	ENDIF

	// === MOVE CAR ===
	IF cp_car_released[cp_current_car] = 1
		IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
		OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKX
			IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					GET_CAR_COORDINATES cp_car[cp_current_car] cp_car_x cp_car_y cp_car_z
					GET_CAR_HEADING cp_car[cp_current_car] cp_car_h
				ENDIF
		 		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
		 		RStickX_F =# RStickX 
		 		RStickY_F =# RStickY
		 		RStickX_F /= 1000.0
		 		RStickY_F /= 1000.0
				dt_temp_float =# LStickY
				dt_temp_float /= 2000.0

//				IF RStickX_F = 0.0
//					cp_car_x = cp_car_last_x
//				ELSE
//					cp_car_x += RStickX_F
//					cp_car_last_x = cp_car_x 
//				ENDIF
//				IF RStickY_F = 0.0
//					cp_car_y = cp_car_last_y
//				ELSE
//					cp_car_y += RStickY_F
//					cp_car_last_y = cp_car_y 
//				ENDIF

				GOSUB dt_update_camera_vector
				dt_cam_nvec_x *= RStickY_F
				dt_cam_nvec_y *= RStickY_F
				dt_cam_nvec_x *= -1.0
				dt_cam_nvec_y *= -1.0
		 		cp_car_x += dt_cam_nvec_x
		 		cp_car_y += dt_cam_nvec_y
				dt_cam_rvec_x *= RStickX_F
				dt_cam_rvec_y *= RStickX_F
				cp_car_x += dt_cam_rvec_x
		 		cp_car_y += dt_cam_rvec_y

				cp_car_z += dt_temp_float

				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
		 			SET_CAR_COORDINATES cp_car[cp_current_car] cp_car_x cp_car_y cp_car_z 
				ENDIF
				dt_temp_float =# LStickX
				dt_temp_float /= 50.0
			   	cp_car_h += dt_temp_float
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					SET_CAR_HEADING cp_car[cp_current_car]	cp_car_h
				ENDIF
			ENDIF
	 	ENDIF	
	ENDIF

	// === DISPLAY CORONA ===
	IF DOES_VEHICLE_EXIST cp_car[cp_current_car] 
		IF NOT IS_CAR_DEAD cp_car[cp_current_car]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS cp_car[cp_current_car] 0.0 0.0 1.5 dt_corona_x	dt_corona_y	dt_corona_z
			IF cp_car_released[cp_current_car] = 0
				DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.25 CORONATYPE_MOON FLARETYPE_NONE 255 0 0
			ELSE
				DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.25 CORONATYPE_MOON FLARETYPE_NONE 0 255 0
			ENDIF
		ENDIF	
	ENDIF

	// === DISPLAY HELP ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
		IF cp_help = 0
			PRINT_HELP OP3
			cp_help = 1
		ENDIF
	ELSE
		IF cp_help = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER OP2	
			cp_help = 0
		ENDIF	
	ENDIF


ENDIF

// #################################################################################################################################################
// 															PED PLACER TOOL 
// #################################################################################################################################################
VAR_INT pp_ped[15] 
VAR_FLOAT pp_x1 pp_y1 pp_z1
VAR_FLOAT pp_x2 pp_y2 pp_z2
VAR_FLOAT pp_x3 pp_y3 pp_z3
VAR_FLOAT pp_distance
VAR_FLOAT pp_ped_x pp_ped_y pp_ped_z pp_ped_h
VAR_FLOAT pp_ped_rx pp_ped_ry pp_ped_rz
VAR_INT pp_ped_released[25]
VAR_INT pp_current_ped pp_last_ped
VAR_INT pp_modelID pp_last_modelID
VAR_INT pp_temp_int
VAR_INT pp_help
VAR_INT pp_models[180]
VAR_INT pp_found_ped_after_deletion
GOTO pp_skip
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0  pp_ped[0]
pp_skip:

// running ped placer mode
IF design_mode = 3

	// === UPDATE CURRENT GRABBED PED ===
	// Get camera point at coordinates
	IF pp_ped_released[pp_current_ped] = 0
		GET_DEBUG_CAMERA_POINT_AT pp_x1 pp_y1 pp_z1
		GET_DEBUG_CAMERA_COORDINATES pp_x2 pp_y2 pp_z2
		// get vector AB
		pp_x3 = pp_x1 - pp_x2
		pp_y3 = pp_y1 - pp_y2
		pp_z3 = pp_z1 - pp_z2
		// multiply by distance
		pp_x3 *= pp_distance
		pp_y3 *= pp_distance
		pp_z3 *= pp_distance
		// add to camera position
		pp_ped_x = pp_x3 + pp_x2 
		pp_ped_y = pp_y3 + pp_y2
		pp_ped_z = pp_z3 + pp_z2

		IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
			IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
				SET_CHAR_COORDINATES pp_ped[pp_current_ped] pp_ped_x pp_ped_y pp_ped_z
				SET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
			ENDIF	
		ENDIF
	ENDIF

	// === SWITCH CURRENTLY SELECTED PED ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PERIOD
		pp_current_ped++
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_COMMA
		pp_current_ped--
		WAIT 100
	ENDIF
	IF pp_current_ped > 14
		pp_current_ped = 0
	ENDIF
	IF pp_current_ped < 0
		pp_current_ped = 14
	ENDIF
	IF NOT pp_current_ped = pp_last_ped
		
		pp_ped_rx = 0.0
		pp_ped_ry = 0.0
		pp_ped_rz = 0.0
		
		// set last ped to released
		IF pp_last_ped > -1
			IF DOES_CHAR_EXIST pp_ped[pp_last_ped]
				IF NOT IS_CHAR_DEAD pp_ped[pp_last_ped]
					SET_CHAR_COLLISION pp_ped[pp_last_ped] TRUE
					FREEZE_CHAR_POSITION pp_ped[pp_last_ped] TRUE
					pp_ped_released[pp_last_ped] = 1
				ENDIF		   	
			ENDIF
		ENDIF

		// switch peds	
		IF NOT DOES_CHAR_EXIST pp_ped[pp_current_ped]
			REQUEST_MODEL pp_modelID
			WHILE NOT HAS_MODEL_LOADED pp_modelID
				WAIT 0
			ENDWHILE
			CREATE_CHAR PEDTYPE_CIVMALE pp_modelID pp_ped_x pp_ped_y pp_ped_z pp_ped[pp_current_ped]
			SET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
			SET_CHAR_COLLISION pp_ped[pp_current_ped] FALSE
			FREEZE_CHAR_POSITION pp_ped[pp_current_ped] TRUE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER pp_ped[pp_current_ped] TRUE
			GET_AREA_VISIBLE dt_temp_int
			SET_CHAR_AREA_VISIBLE pp_ped[pp_current_ped] dt_temp_int

			pp_ped_released[pp_current_ped] = 0 
		ENDIF
		pp_last_ped = pp_current_ped
	ENDIF
	 
	// === DELETE CURRENTLY SELECTED PED ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DEL
		// delete current car
		IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
			DELETE_CHAR pp_ped[pp_current_ped]
		ENDIF
		// move to forward to next car if it exists
		pp_found_ped_after_deletion = 0
		dt_temp_int = pp_current_ped
		dt_temp_int1 = pp_current_ped - 1
		IF dt_temp_int1 < 0
			dt_temp_int1 = 14
		ENDIF
		WHILE NOT dt_temp_int1 = dt_temp_int
			IF DOES_CHAR_EXIST pp_ped[dt_temp_int1]
				dt_temp_int = dt_temp_int1
				pp_found_ped_after_deletion = 1
			ENDIF
			IF pp_found_ped_after_deletion = 0
				dt_temp_int1--
				IF dt_temp_int1 < 0
					dt_temp_int1 = 14
				ENDIF 
			ENDIF
		ENDWHILE
		IF pp_found_ped_after_deletion = 1
			pp_current_ped = dt_temp_int
		ELSE
			pp_last_ped = -1	// if no other cars are found a new one will be created
		ENDIF
		WAIT 500
	ENDIF

	// === SWITCH CURRENTLY SELECTED PED'S MODEL ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGUP
		pp_modelID++
		IF pp_modelID > 399
			pp_modelID = 9
		ENDIF
		WHILE NOT IS_MODEL_IN_CDIMAGE pp_modelID
			pp_modelID++
			IF pp_modelID > 399
				pp_modelID = 9
			ENDIF
		ENDWHILE
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_PGDN
		pp_modelID--
		IF pp_modelID < 9
			pp_modelID = 399
		ENDIF
		WHILE NOT IS_MODEL_IN_CDIMAGE pp_modelID
			pp_modelID--
			IF pp_modelID < 9
				pp_modelID = 399
			ENDIF
		ENDWHILE
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_HOME
		pp_modelID += 10
		IF pp_modelID > 399
			pp_modelID = 9
		ENDIF
		WHILE NOT IS_MODEL_IN_CDIMAGE pp_modelID
			pp_modelID++
			IF pp_modelID > 399
				pp_modelID = 9
			ENDIF
		ENDWHILE
		WAIT 100
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_END
		pp_modelID += -10
		IF pp_modelID < 9
			pp_modelID = 399
		ENDIF
		WHILE NOT IS_MODEL_IN_CDIMAGE pp_modelID
			pp_modelID--
			IF pp_modelID < 9
				pp_modelID = 399
			ENDIF
		ENDWHILE
		WAIT 100
	ENDIF

	IF NOT pp_modelID = pp_last_modelID
		VIEW_INTEGER_VARIABLE pp_modelID pp_modelID
		IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
			GET_CHAR_COORDINATES pp_ped[pp_current_ped]	pp_ped_x pp_ped_y pp_ped_z
			pp_ped_z += -1.0
			GET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
		ENDIF
		MARK_MODEL_AS_NO_LONGER_NEEDED pp_last_modelID
		DELETE_CHAR pp_ped[pp_current_ped]
		REQUEST_MODEL pp_modelID
		WHILE NOT HAS_MODEL_LOADED pp_modelID
			WAIT 0
		ENDWHILE
		CREATE_CHAR PEDTYPE_CIVMALE pp_modelID pp_ped_x pp_ped_y pp_ped_z pp_ped[pp_current_ped]
		SET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER pp_ped[pp_current_ped] TRUE
		FREEZE_CHAR_POSITION pp_ped[pp_current_ped] TRUE
		SET_CHAR_COLLISION pp_ped[pp_current_ped] FALSE
		GET_AREA_VISIBLE dt_temp_int
		SET_CHAR_AREA_VISIBLE pp_ped[pp_current_ped] dt_temp_int
		pp_last_modelID = pp_modelID
	ENDIF

	// === UNGRAB CURRENT PED ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		IF pp_ped_released[pp_current_ped] = 0
			IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					SET_CHAR_COLLISION pp_ped[pp_current_ped] TRUE
					FREEZE_CHAR_POSITION pp_ped[pp_current_ped] TRUE
				ENDIF
				pp_ped_released[pp_current_ped] = 1
			ENDIF
		ELSE
			IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					SET_CHAR_COLLISION pp_ped[pp_current_ped] FALSE
					FREEZE_CHAR_POSITION pp_ped[pp_current_ped] TRUE
				ENDIF
				pp_ped_released[pp_current_ped] = 0
			ENDIF
		ENDIF
		WAIT 100				
	ENDIF

	// === APPLY GRAVITY TO PED ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
		IF pp_ped_released[pp_current_ped] = 1 
			IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					SET_CHAR_COLLISION pp_ped[pp_current_ped] TRUE
					FREEZE_CHAR_POSITION pp_ped[pp_current_ped] FALSE
				ENDIF
			ENDIF 
		ELSE
			pp_ped_released[pp_current_ped] = 1
		ENDIF
	ENDIF

	// === MOVE PED ===
	IF pp_ped_released[pp_current_ped] = 1
		IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
		OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
		OR IS_BUTTON_PRESSED PAD1 LEFTSTICKX
			IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					GET_CHAR_COORDINATES pp_ped[pp_current_ped] pp_ped_x pp_ped_y pp_ped_z
					pp_ped_z += -1.0
					GET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
				ENDIF
		 		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
		 		RStickX_F =# RStickX 
		 		RStickY_F =# RStickY
		 		RStickX_F /= 1000.0
		 		RStickY_F /= 1000.0
				dt_temp_float =# LStickY
				dt_temp_float /= 5000.0
				GOSUB dt_update_camera_vector
				dt_cam_nvec_x *= RStickY_F
				dt_cam_nvec_y *= RStickY_F
				dt_cam_nvec_x *= -1.0
				dt_cam_nvec_y *= -1.0
		 		pp_ped_x += dt_cam_nvec_x
		 		pp_ped_y += dt_cam_nvec_y
				dt_cam_rvec_x *= RStickX_F
				dt_cam_rvec_y *= RStickX_F
				pp_ped_x += dt_cam_rvec_x
		 		pp_ped_y += dt_cam_rvec_y
				pp_ped_z += dt_temp_float
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
		 			SET_CHAR_COORDINATES pp_ped[pp_current_ped] pp_ped_x pp_ped_y pp_ped_z
		 		ENDIF 
				dt_temp_float =# LStickX
				dt_temp_float /= 50.0
			   	pp_ped_h += dt_temp_float
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					SET_CHAR_HEADING pp_ped[pp_current_ped]	pp_ped_h
				ENDIF
			ENDIF
	 	ENDIF	
	ENDIF

	// === DISPLAY CORONA ===
	IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
		IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS pp_ped[pp_current_ped] 0.0 0.0 1.0 dt_corona_x	dt_corona_y	dt_corona_z
			IF pp_ped_released[pp_current_ped] = 0
				DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.25 CORONATYPE_MOON FLARETYPE_NONE 255 0 0
			ELSE
				DRAW_CORONA dt_corona_x	dt_corona_y	dt_corona_z 0.25 CORONATYPE_MOON FLARETYPE_NONE 0 255 0
			ENDIF
		ENDIF	
	ENDIF


	// === DISPLAY HELP ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_H
		IF pp_help = 0
			PRINT_HELP OP3
			pp_help = 1
		ENDIF
	ELSE
		IF pp_help = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER OP2	
			pp_help = 0
		ENDIF	
	ENDIF


ENDIF

// #################################################################################################################################################
// 												SCREEN EDIT TOOL 
// #################################################################################################################################################
VAR_INT rect_limit_ST text_limit_ST

VAR_INT screen_mode_ST rect_mode_ST text_mode_ST current_rect_ST current_text_ST

VAR_INT rect_R_ST[20] rect_G_ST[20] rect_B_ST[20] rect_alpha_ST[20] rect_exists_ST[21]
VAR_FLOAT rect_centre_X_ST[20] rect_centre_Y_ST[20] rect_width_ST[20] rect_height_ST[20] 

VAR_INT text_R_ST[40] text_G_ST[40] text_B_ST[40] text_alpha_ST[40] text_justify_ST[40] text_exists_ST[41] 
VAR_FLOAT text_X_ST[40] text_Y_ST[40] text_scale_X_ST[40] text_scale_Y_ST[40]

VAR_INT move_horizontal_ST move_vertical_ST 
VAR_INT rect_size_horizontal_ST rect_size_vertical_ST
VAR_FLOAT text_scale_horizontal_ST text_scale_vertical_ST

VAR_INT rect_new_ST text_new_ST 

// temp vars 
VAR_INT temp_int_1_ST temp_int_2_ST 
VAR_INT temp_red_ST temp_green_ST temp_blue_ST temp_alpha_ST
VAR_FLOAT temp_float_1_ST temp_float_2_ST

screen_mode_ST = 0

screen_tools:

IF design_mode = 5

	WAIT 0

	IF screen_mode_ST = 0

		//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F5
		 																																	
		rect_limit_ST = 20
		text_limit_ST = 40

		// CLEAR VARIABLES
		temp_int_1_ST = 0

		WHILE temp_int_1_ST < rect_limit_ST
			rect_exists_ST[temp_int_1_ST] = 0
			rect_R_ST[current_rect_ST] = 255
			rect_G_ST[current_rect_ST] = 0
			rect_B_ST[current_rect_ST] = 0
			rect_alpha_ST[current_rect_ST] = 150

			rect_centre_X_ST[current_rect_ST] = 0.0
			rect_centre_Y_ST[current_rect_ST] = 0.0
			rect_width_ST[current_rect_ST] = 0.0
			rect_height_ST[current_rect_ST] = 0.0

			temp_int_1_ST ++

		ENDWHILE

		temp_int_1_ST = 0

		WHILE temp_int_1_ST < text_limit_ST
			text_exists_ST[temp_int_1_ST] = 0
			temp_int_1_ST ++
		ENDWHILE

		rect_exists_ST[20] = 0 // DO NOT CHANGE
		text_exists_ST[40] = 0 // DO NOT CHANGE

		screen_mode_ST = 1 // RECT MODE

		current_rect_ST = 0
		current_text_ST = 0

		rect_new_ST = 1
		text_new_ST = 1

		// RECT ON SCREEN
		rect_exists_ST[current_rect_ST] = 1

		rect_R_ST[current_rect_ST] = 255
		rect_G_ST[current_rect_ST] = 0
		rect_B_ST[current_rect_ST] = 0
		rect_alpha_ST[current_rect_ST] = 150

		rect_centre_X_ST[current_rect_ST] = 105.0
		rect_centre_Y_ST[current_rect_ST] = 34.0
		rect_width_ST[current_rect_ST] = 184.0
		rect_height_ST[current_rect_ST] = 46.0

		// TEXT ON SCREEN
		text_exists_ST[current_text_ST] = 1

		text_R_ST[current_text_ST] = 0
		text_G_ST[current_text_ST] = 255
		text_B_ST[current_text_ST] = 0
		text_alpha_ST[current_text_ST] = 150

		text_justify_ST[current_text_ST] = 0

		text_X_ST[current_text_ST] = 17.0
		text_Y_ST[current_text_ST] = 23.0
		text_scale_X_ST[current_text_ST] = 1.5562
		text_scale_Y_ST[current_text_ST] = 2.2312

		USE_TEXT_COMMANDS TRUE

		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 OFF
		ENDIF
		//ENDIF

	ELSE
 
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RETURN // PRINT SCREEN INFO TO DEBUG FILE 
			
			IF screen_mode_ST = 1
				SAVE_NEWLINE_TO_DEBUG_FILE

		  		SAVE_STRING_TO_DEBUG_FILE "DRAW_RECT "
				SAVE_FLOAT_TO_DEBUG_FILE rect_centre_X_ST[current_rect_ST]
				SAVE_FLOAT_TO_DEBUG_FILE rect_centre_Y_ST[current_rect_ST]
				SAVE_FLOAT_TO_DEBUG_FILE rect_width_ST[current_rect_ST]
				SAVE_FLOAT_TO_DEBUG_FILE rect_height_ST[current_rect_ST]
				SAVE_INT_TO_DEBUG_FILE rect_R_ST[current_rect_ST]
				SAVE_INT_TO_DEBUG_FILE rect_G_ST[current_rect_ST]
				SAVE_INT_TO_DEBUG_FILE rect_B_ST[current_rect_ST]
				SAVE_INT_TO_DEBUG_FILE rect_alpha_ST[current_rect_ST]

				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_NEWLINE_TO_DEBUG_FILE

			ELSE
 
				// SCALE
				SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_SCALE "
				SAVE_FLOAT_TO_DEBUG_FILE text_scale_X_ST[current_text_ST]
				SAVE_FLOAT_TO_DEBUG_FILE text_scale_Y_ST[current_text_ST]

				SAVE_NEWLINE_TO_DEBUG_FILE

				// COLOUR
				SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_COLOUR "
				SAVE_INT_TO_DEBUG_FILE text_R_ST[current_text_ST]
				SAVE_INT_TO_DEBUG_FILE text_G_ST[current_text_ST]
				SAVE_INT_TO_DEBUG_FILE text_B_ST[current_text_ST]
				SAVE_INT_TO_DEBUG_FILE text_alpha_ST[current_text_ST]

				SAVE_NEWLINE_TO_DEBUG_FILE

				// JUSTIFY
				IF text_justify_ST[current_text_ST] = 0
					SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_JUSTIFY ON"
				ELSE
					IF text_justify_ST[current_text_ST] = 1
						SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_CENTRE ON"
					ELSE
						SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_RIGHT_JUSTIFY ON"
					ENDIF
				ENDIF

				SAVE_NEWLINE_TO_DEBUG_FILE
							
				// DISPLAY
				SAVE_STRING_TO_DEBUG_FILE "DISPLAY_TEXT "
				SAVE_FLOAT_TO_DEBUG_FILE text_X_ST[current_text_ST]
				SAVE_FLOAT_TO_DEBUG_FILE text_Y_ST[current_text_ST]
				SAVE_STRING_TO_DEBUG_FILE "TEXT_"
				SAVE_INT_TO_DEBUG_FILE current_text_ST

				SAVE_NEWLINE_TO_DEBUG_FILE
			ENDIF
	
			WAIT 600
		ELSE

			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ENTER // PRINT SELECTION INFO TO DEBUG FILE
				
				SAVE_NEWLINE_TO_DEBUG_FILE

				temp_int_1_ST = 0
				WHILE rect_exists_ST[temp_int_1_ST] = 1   
					
					SAVE_STRING_TO_DEBUG_FILE "DRAW_RECT "
					SAVE_FLOAT_TO_DEBUG_FILE rect_centre_X_ST[temp_int_1_ST]
					SAVE_FLOAT_TO_DEBUG_FILE rect_centre_Y_ST[temp_int_1_ST]
					SAVE_FLOAT_TO_DEBUG_FILE rect_width_ST[temp_int_1_ST]
					SAVE_FLOAT_TO_DEBUG_FILE rect_height_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE rect_R_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE rect_G_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE rect_B_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE rect_alpha_ST[temp_int_1_ST]

					SAVE_NEWLINE_TO_DEBUG_FILE
					SAVE_NEWLINE_TO_DEBUG_FILE
					temp_int_1_ST ++
				ENDWHILE

				SAVE_NEWLINE_TO_DEBUG_FILE

				temp_int_1_ST = 0
				WHILE text_exists_ST[temp_int_1_ST] = 1   
					
					// SCALE
					SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_SCALE "
					SAVE_FLOAT_TO_DEBUG_FILE text_scale_X_ST[temp_int_1_ST]
					SAVE_FLOAT_TO_DEBUG_FILE text_scale_Y_ST[temp_int_1_ST]

					SAVE_NEWLINE_TO_DEBUG_FILE

					// COLOUR
					SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_COLOUR "
					SAVE_INT_TO_DEBUG_FILE text_R_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE text_G_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE text_B_ST[temp_int_1_ST]
					SAVE_INT_TO_DEBUG_FILE text_alpha_ST[temp_int_1_ST]

					SAVE_NEWLINE_TO_DEBUG_FILE

					// JUSTIFY
					IF text_justify_ST[temp_int_1_ST] = 0
						SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_JUSTIFY ON"
					ELSE
						IF text_justify_ST[temp_int_1_ST] = 1
							SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_CENTRE ON"
						ELSE
							SAVE_STRING_TO_DEBUG_FILE "SET_TEXT_RIGHT_JUSTIFY ON"
						ENDIF
					ENDIF

					SAVE_NEWLINE_TO_DEBUG_FILE
								
					// DISPLAY
					SAVE_STRING_TO_DEBUG_FILE "DISPLAY_TEXT "
					SAVE_FLOAT_TO_DEBUG_FILE text_X_ST[temp_int_1_ST]
					SAVE_FLOAT_TO_DEBUG_FILE text_Y_ST[temp_int_1_ST]
					SAVE_STRING_TO_DEBUG_FILE "TEXT_"
					SAVE_INT_TO_DEBUG_FILE temp_int_1_ST

					SAVE_NEWLINE_TO_DEBUG_FILE
					SAVE_NEWLINE_TO_DEBUG_FILE
					temp_int_1_ST ++
				ENDWHILE
		
				WAIT 600
			ELSE
			
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1 // ADD NEW TEXT / RECT

					IF screen_mode_ST = 1
						IF NOT rect_new_ST = rect_limit_ST
							
							rect_exists_ST[rect_new_ST] = 1

							rect_R_ST[rect_new_ST] = rect_R_ST[current_rect_ST] 
							rect_G_ST[rect_new_ST] = rect_G_ST[current_rect_ST] 
							rect_B_ST[rect_new_ST] = rect_B_ST[current_rect_ST] 
							rect_alpha_ST[rect_new_ST] = rect_alpha_ST[current_rect_ST] 

							rect_centre_X_ST[rect_new_ST] = rect_centre_X_ST[current_rect_ST]
							rect_centre_Y_ST[rect_new_ST] = rect_centre_Y_ST[current_rect_ST]
							rect_width_ST[rect_new_ST] = rect_width_ST[current_rect_ST]
							rect_height_ST[rect_new_ST] = rect_height_ST[current_rect_ST]
							
							current_rect_ST = rect_new_ST
							rect_new_ST ++
							TIMERB = 0

							WAIT 0
							
							WHILE TIMERB < 350
								
								WAIT 0					
								DRAW_RECT rect_centre_X_ST[current_rect_ST] rect_centre_Y_ST[current_rect_ST] rect_width_ST[current_rect_ST] rect_height_ST[current_rect_ST] 0 0 255 255

							ENDWHILE
	 
						ENDIF
					ELSE
						IF NOT text_new_ST = text_limit_ST
							
							text_exists_ST[text_new_ST] = 1

							text_R_ST[text_new_ST] = text_R_ST[current_text_ST] 
							text_G_ST[text_new_ST] = text_G_ST[current_text_ST] 
							text_B_ST[text_new_ST] = text_B_ST[current_text_ST] 
							text_alpha_ST[text_new_ST] = text_alpha_ST[current_text_ST] 

							text_justify_ST[text_new_ST] = text_justify_ST[current_text_ST]

							text_X_ST[text_new_ST] = text_X_ST[current_text_ST] 
							text_Y_ST[text_new_ST] = text_Y_ST[current_text_ST] 
							text_scale_X_ST[text_new_ST] = text_scale_X_ST[current_text_ST] 
							text_scale_Y_ST[text_new_ST] = text_scale_Y_ST[current_text_ST]
							
							current_text_ST = text_new_ST
							text_new_ST ++
							TIMERB = 0

							WAIT 0
							
							WHILE TIMERB < 350
								
								WAIT 0					
								
								SET_TEXT_SCALE text_scale_X_ST[current_text_ST] text_scale_Y_ST[current_text_ST]
								SET_TEXT_COLOUR 0 0 255 255

								DISPLAY_TEXT_WITH_NUMBER text_X_ST[current_text_ST] text_Y_ST[current_text_ST] SCREEN current_text_ST
							ENDWHILE
	 
						ENDIF

					ENDIF	
				
				ELSE

					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1 // CHANGE MODE

						IF screen_mode_ST = 1 // CHANGE TO TEXT
							screen_mode_ST = 2

							TIMERB = 0
							WHILE TIMERB < 350
								
								WAIT 0					
								
								SET_TEXT_SCALE text_scale_X_ST[current_text_ST] text_scale_Y_ST[current_text_ST]
								SET_TEXT_COLOUR 255 0 0 255
								IF text_justify_ST[current_text_ST] = 0
									SET_TEXT_JUSTIFY ON
								ELSE
									IF text_justify_ST[current_text_ST] = 1
										SET_TEXT_CENTRE ON
									ELSE
										SET_TEXT_RIGHT_JUSTIFY ON
									ENDIF
								ENDIF

								DISPLAY_TEXT_WITH_NUMBER text_X_ST[current_text_ST] text_Y_ST[current_text_ST] SCREEN current_text_ST
							ENDWHILE
							 	
						ELSE
							screen_mode_ST = 1 // CHANGE TO RECT

							TIMERB = 0
							WHILE TIMERB < 350
								
								WAIT 0					
								
								DRAW_RECT rect_centre_X_ST[current_rect_ST] rect_centre_Y_ST[current_rect_ST] rect_width_ST[current_rect_ST] rect_height_ST[current_rect_ST] 255 0 0 255
							ENDWHILE

						ENDIF

					ELSE

						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2 // GOTO NEXT SELECTION

							IF screen_mode_ST = 1
								
								IF NOT rect_new_ST = 1
									current_rect_ST ++
									IF current_rect_ST = rect_new_ST
										current_rect_ST = 0
									ENDIF
								
									TIMERB = 0
									WHILE TIMERB < 350
										
										WAIT 0					
										
										DRAW_RECT rect_centre_X_ST[current_rect_ST] rect_centre_Y_ST[current_rect_ST] rect_width_ST[current_rect_ST] rect_height_ST[current_rect_ST] 0 255 0 255
									ENDWHILE
								ENDIF

							ELSE
								IF NOT text_new_ST = 1
									current_text_ST ++
									IF current_text_ST = text_new_ST
										current_text_ST = 0
									ENDIF
								
									TIMERB = 0
									WHILE TIMERB < 350
										
										WAIT 0					
										
										SET_TEXT_SCALE text_scale_X_ST[current_text_ST] text_scale_Y_ST[current_text_ST]
										SET_TEXT_COLOUR 0 255 0 255
										
										IF text_justify_ST[current_text_ST] = 0
											SET_TEXT_JUSTIFY ON
										ELSE
											IF text_justify_ST[current_text_ST] = 1
												SET_TEXT_CENTRE ON
											ELSE
												SET_TEXT_RIGHT_JUSTIFY ON
											ENDIF
										ENDIF

										DISPLAY_TEXT_WITH_NUMBER text_X_ST[current_text_ST] text_Y_ST[current_text_ST] SCREEN current_text_ST

									ENDWHILE
								ENDIF
							ENDIF

						ELSE

							IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2 // GOTO PREVIOUS SELECTION

								IF screen_mode_ST = 1
									
									IF NOT rect_new_ST = 1
										current_rect_ST --
										IF current_rect_ST = -1
											current_rect_ST = rect_new_ST - 1
										ENDIF
									
										TIMERB = 0
										WHILE TIMERB < 350
											
											WAIT 0					
											
											DRAW_RECT rect_centre_X_ST[current_rect_ST] rect_centre_Y_ST[current_rect_ST] rect_width_ST[current_rect_ST] rect_height_ST[current_rect_ST] 0 255 0 255
										ENDWHILE
									ENDIF

								ELSE
									IF NOT text_new_ST = 1
										current_text_ST --
										IF current_text_ST = -1
											current_text_ST = text_new_ST - 1
										ENDIF
									
										TIMERB = 0									
										WHILE TIMERB < 350
											
											WAIT 0					
											
											SET_TEXT_SCALE text_scale_X_ST[current_text_ST] text_scale_Y_ST[current_text_ST]
											SET_TEXT_COLOUR 0 255 0 255
											IF text_justify_ST[current_text_ST] = 0
												SET_TEXT_JUSTIFY ON
											ELSE
												IF text_justify_ST[current_text_ST] = 1
													SET_TEXT_CENTRE ON
												ELSE
													SET_TEXT_RIGHT_JUSTIFY ON
												ENDIF
											ENDIF


											DISPLAY_TEXT_WITH_NUMBER text_X_ST[current_text_ST] text_Y_ST[current_text_ST] SCREEN current_text_ST

										ENDWHILE
									ENDIF
								ENDIF

							ELSE

								IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DEL // DELETE CURRENT RECT / TEXT

									IF screen_mode_ST = 1
									
										IF NOT rect_new_ST = 1

											temp_int_1_ST = current_rect_ST
											temp_int_2_ST = current_rect_ST + 1

											WHILE temp_int_2_ST < rect_new_ST
											
												rect_exists_ST[temp_int_1_ST] = rect_exists_ST[temp_int_2_ST]

												rect_R_ST[temp_int_1_ST] = rect_R_ST[temp_int_2_ST]
												rect_G_ST[temp_int_1_ST] = rect_G_ST[temp_int_2_ST]
												rect_B_ST[temp_int_1_ST] = rect_B_ST[temp_int_2_ST]
												rect_alpha_ST[temp_int_1_ST] = rect_alpha_ST[temp_int_2_ST]  

												rect_centre_X_ST[temp_int_1_ST] = rect_centre_X_ST[temp_int_2_ST] 
												rect_centre_Y_ST[temp_int_1_ST] = rect_centre_Y_ST[temp_int_2_ST]
												rect_width_ST[temp_int_1_ST] = rect_width_ST[temp_int_2_ST]
												rect_height_ST[temp_int_1_ST] = rect_height_ST[temp_int_2_ST]
												 

												temp_int_1_ST ++
												temp_int_2_ST ++
											ENDWHILE
											

											rect_exists_ST[temp_int_1_ST] = 0												
											rect_new_ST = temp_int_1_ST

											IF NOT current_rect_ST = 0
												current_rect_ST --
											ENDIF

											WAIT 150
										ENDIF

									ELSE
										
										IF NOT text_new_ST = 1

											temp_int_1_ST = current_text_ST
											temp_int_2_ST = current_text_ST + 1

											WHILE temp_int_2_ST < text_new_ST
											
												text_exists_ST[temp_int_1_ST] = text_exists_ST[temp_int_2_ST]

												text_R_ST[temp_int_1_ST] = text_R_ST[temp_int_2_ST]
												text_G_ST[temp_int_1_ST] = text_G_ST[temp_int_2_ST]
												text_B_ST[temp_int_1_ST] = text_B_ST[temp_int_2_ST]
												text_alpha_ST[temp_int_1_ST] = text_alpha_ST[temp_int_2_ST]  

												text_justify_ST[temp_int_1_ST] = text_justify_ST[temp_int_2_ST]

												text_X_ST[temp_int_1_ST] = text_X_ST[temp_int_2_ST] 
												text_Y_ST[temp_int_1_ST] = text_Y_ST[temp_int_2_ST] 
												text_scale_X_ST[temp_int_1_ST] = text_scale_X_ST[temp_int_2_ST] 
												text_scale_Y_ST[temp_int_1_ST] = text_scale_Y_ST[temp_int_2_ST]

												temp_int_1_ST ++
												temp_int_2_ST ++
											ENDWHILE
											
											text_exists_ST[temp_int_1_ST] = 0												
											text_new_ST = temp_int_1_ST
											
											IF NOT current_text_ST = 0
												current_text_ST --
											ENDIF
											
											WAIT 150
										ENDIF							
									ENDIF 

								ELSE

									IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_R  // RESET SCREEN

										WHILE temp_int_1_ST < rect_limit_ST
											rect_exists_ST[temp_int_1_ST] = 0

											rect_R_ST[current_rect_ST] = 255
											rect_G_ST[current_rect_ST] = 0
											rect_B_ST[current_rect_ST] = 0
											rect_alpha_ST[current_rect_ST] = 150

											rect_centre_X_ST[current_rect_ST] = 0.0
											rect_centre_Y_ST[current_rect_ST] = 0.0
											rect_width_ST[current_rect_ST] = 0.0
											rect_height_ST[current_rect_ST] = 0.0

											temp_int_1_ST ++

										ENDWHILE

										temp_int_1_ST = 0

										WHILE temp_int_1_ST < text_limit_ST
											text_exists_ST[temp_int_1_ST] = 0
											temp_int_1_ST ++
										ENDWHILE


										screen_mode_ST = 1 // RECT MODE

										current_rect_ST = 0
										current_text_ST = 0

										rect_new_ST = 1
										text_new_ST = 1

										// RECT ON SCREEN
										rect_exists_ST[current_rect_ST] = 1

										rect_R_ST[current_rect_ST] = 255
										rect_G_ST[current_rect_ST] = 0
										rect_B_ST[current_rect_ST] = 0
										rect_alpha_ST[current_rect_ST] = 150

										rect_centre_X_ST[current_rect_ST] = 105.0
										rect_centre_Y_ST[current_rect_ST] = 34.0
										rect_width_ST[current_rect_ST] = 184.0
										rect_height_ST[current_rect_ST] = 46.0

										// TEXT ON SCREEN
										text_exists_ST[current_text_ST] = 1

										text_R_ST[current_text_ST] = 0
										text_G_ST[current_text_ST] = 255
										text_B_ST[current_text_ST] = 0
										text_alpha_ST[current_text_ST] = 150

										text_justify_ST[current_text_ST] = 0

										text_X_ST[current_text_ST] = 17.0
										text_Y_ST[current_text_ST] = 23.0
										text_scale_X_ST[current_text_ST] = 1.5562
										text_scale_Y_ST[current_text_ST] = 2.2312
										
										WAIT 150	
										
									ELSE
										
										IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ESC  // ESCAPE

											WHILE temp_int_1_ST < rect_limit_ST
												rect_exists_ST[temp_int_1_ST] = 0

												rect_R_ST[current_rect_ST] = 255
												rect_G_ST[current_rect_ST] = 0
												rect_B_ST[current_rect_ST] = 0
												rect_alpha_ST[current_rect_ST] = 150

												rect_centre_X_ST[current_rect_ST] = 0.0
												rect_centre_Y_ST[current_rect_ST] = 0.0
												rect_width_ST[current_rect_ST] = 0.0
												rect_height_ST[current_rect_ST] = 0.0

												temp_int_1_ST ++

											ENDWHILE

											temp_int_1_ST = 0

											WHILE temp_int_1_ST < text_limit_ST
												text_exists_ST[temp_int_1_ST] = 0
												temp_int_1_ST ++
											ENDWHILE
											
											IF IS_PLAYER_PLAYING player1
												SET_PLAYER_CONTROL player1 ON
											ENDIF

											USE_TEXT_COMMANDS FALSE
											design_mode = 0
											
										ELSE

											IF IS_BUTTON_PRESSED PAD1 DPADLEFT // JUSTIFY LEFT
												IF screen_mode_ST = 2
													IF NOT text_justify_ST[current_text_ST] = 0
														text_justify_ST[current_text_ST] = 0
													ENDIF	
												ENDIF
											ELSE
											
												IF IS_BUTTON_PRESSED PAD1 DPADDOWN // JUSTIFY CENTRE
													IF screen_mode_ST = 2
														IF NOT text_justify_ST[current_text_ST] = 1
															text_justify_ST[current_text_ST] = 1
														ENDIF
													ENDIF

												ELSE
												
													IF IS_BUTTON_PRESSED PAD1 DPADRIGHT // JUSTIFY RIGHT
														IF screen_mode_ST = 2
															IF NOT text_justify_ST[current_text_ST] = 2
																text_justify_ST[current_text_ST] = 2
															ENDIF
														ENDIF

													ELSE 

														IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
														OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
														OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
														OR IS_BUTTON_PRESSED PAD1 LEFTSTICKX
														
															GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

															move_horizontal_ST = LStickX / 25 
															move_vertical_ST = LStickY / 25

															IF IS_BUTTON_PRESSED PAD1 CIRCLE
															OR IS_BUTTON_PRESSED PAD1 TRIANGLE
															OR IS_BUTTON_PRESSED PAD1 CROSS
															OR IS_BUTTON_PRESSED PAD1 SQUARE
												 
																IF screen_mode_ST = 1
																	temp_red_ST = rect_R_ST[current_rect_ST]
																	temp_green_ST = rect_G_ST[current_rect_ST]
																	temp_blue_ST = rect_B_ST[current_rect_ST]
																	temp_alpha_ST = rect_alpha_ST[current_rect_ST] 
																ELSE
																	temp_red_ST = text_R_ST[current_text_ST]
																	temp_green_ST = text_G_ST[current_text_ST]
																	temp_blue_ST = text_B_ST[current_text_ST]
																	temp_alpha_ST = text_alpha_ST[current_text_ST] 
																ENDIF

																// RED
																IF IS_BUTTON_PRESSED PAD1 CIRCLE 
																	temp_red_ST = temp_red_ST + move_horizontal_ST
																	
																	IF temp_red_ST > 255
																		temp_red_ST = 255
																	ELSE
																		IF temp_red_ST < 0
																			temp_red_ST = 0
																		ENDIF
																	ENDIF
																ENDIF

																// GREEN
																IF IS_BUTTON_PRESSED PAD1 TRIANGLE 
																	temp_green_ST = temp_green_ST + move_horizontal_ST
																	
																	IF temp_green_ST > 255
																		temp_green_ST = 255
																	ELSE
																		IF temp_green_ST < 0
																			temp_green_ST = 0
																		ENDIF
																	ENDIF
																ENDIF
																		
																// BLUE
																IF IS_BUTTON_PRESSED PAD1 CROSS 
																	temp_blue_ST = temp_blue_ST + move_horizontal_ST
																	
																	IF temp_blue_ST > 255
																		temp_blue_ST = 255
																	ELSE
																		IF temp_blue_ST < 0
																			temp_blue_ST = 0
																		ENDIF
																	ENDIF
																ENDIF
																		
																// ALPHA
																IF IS_BUTTON_PRESSED PAD1 SQUARE 
																	temp_alpha_ST = temp_alpha_ST + move_horizontal_ST
																	
																	IF temp_alpha_ST > 255
																		temp_alpha_ST = 255
																	ELSE
																		IF temp_alpha_ST < 0
																			temp_alpha_ST = 0
																		ENDIF
																	ENDIF
																ENDIF
																		
																IF screen_mode_ST = 1
																	rect_R_ST[current_rect_ST] = temp_red_ST 
																	rect_G_ST[current_rect_ST] = temp_green_ST 
																	rect_B_ST[current_rect_ST] = temp_blue_ST 
																	rect_alpha_ST[current_rect_ST] = temp_alpha_ST  
																ELSE
																	text_R_ST[current_text_ST] = temp_red_ST  
																	text_G_ST[current_text_ST] = temp_green_ST  
																    text_B_ST[current_text_ST] = temp_blue_ST 
																	text_alpha_ST[current_text_ST] = temp_alpha_ST 
																ENDIF				
																		
																		
																		
															ELSE			
																		 	
															temp_float_1_ST =# move_horizontal_ST
															temp_float_2_ST =# move_vertical_ST
															
																
																IF screen_mode_ST = 1 // RECT MODE
																					  

																	rect_centre_X_ST[current_rect_ST] = rect_centre_X_ST[current_rect_ST] + temp_float_1_ST
																	rect_centre_Y_ST[current_rect_ST] = rect_centre_Y_ST[current_rect_ST] + temp_float_2_ST

																	rect_size_horizontal_ST = RStickX / 25
																	temp_float_1_ST =# rect_size_horizontal_ST 
																	rect_width_ST[current_rect_ST] = rect_width_ST[current_rect_ST] + temp_float_1_ST
																	temp_float_2_ST = temp_float_1_ST / 2.0
																	rect_centre_X_ST[current_rect_ST] = rect_centre_X_ST[current_rect_ST] + temp_float_2_ST

																	rect_size_vertical_ST = RStickY / 25
																	temp_float_1_ST =# rect_size_vertical_ST 
																	rect_height_ST[current_rect_ST] = rect_height_ST[current_rect_ST] + temp_float_1_ST
																	temp_float_2_ST = temp_float_1_ST / 2.0
																	rect_centre_Y_ST[current_rect_ST] = rect_centre_Y_ST[current_rect_ST] + temp_float_2_ST
																
																ELSE // TEXT MODE

																	text_X_ST[current_text_ST] = text_X_ST[current_text_ST] + temp_float_1_ST 
																	text_Y_ST[current_text_ST] = text_Y_ST[current_text_ST] + temp_float_2_ST

																	text_scale_horizontal_ST =# RStickX
																	text_scale_horizontal_ST = text_scale_horizontal_ST / 800.0
																	text_scale_vertical_ST =# RStickY 
																	text_scale_vertical_ST = text_scale_vertical_ST / 800.0

																	text_scale_X_ST[current_text_ST] = text_scale_X_ST[current_text_ST] + text_scale_horizontal_ST
																	text_scale_Y_ST[current_text_ST] = text_scale_Y_ST[current_text_ST] + text_scale_vertical_ST

																ENDIF
															ENDIF
														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
	 		ENDIF
		ENDIF


		//#######################
		//## UPDATE RECTANGLES ##
		//#######################
		temp_int_1_ST = 0
		WHILE rect_exists_ST[temp_int_1_ST] = 1   
			
			DRAW_RECT rect_centre_X_ST[temp_int_1_ST] rect_centre_Y_ST[temp_int_1_ST] rect_width_ST[temp_int_1_ST] rect_height_ST[temp_int_1_ST] rect_R_ST[temp_int_1_ST] rect_G_ST[temp_int_1_ST] rect_B_ST[temp_int_1_ST] rect_alpha_ST[temp_int_1_ST]
			temp_int_1_ST ++
		ENDWHILE

		//#################
		//## UPDATE TEXT ##
		//#################
		temp_int_1_ST = 0

		
		WHILE text_exists_ST[temp_int_1_ST] = 1   
			
			SET_TEXT_SCALE text_scale_X_ST[temp_int_1_ST] text_scale_Y_ST[temp_int_1_ST]
			SET_TEXT_COLOUR text_R_ST[temp_int_1_ST] text_G_ST[temp_int_1_ST] text_B_ST[temp_int_1_ST] text_alpha_ST[temp_int_1_ST]
			IF text_justify_ST[temp_int_1_ST] = 0
				SET_TEXT_JUSTIFY ON
			ELSE
				IF text_justify_ST[temp_int_1_ST] = 1
					SET_TEXT_CENTRE ON
				ELSE
					SET_TEXT_RIGHT_JUSTIFY ON
				ENDIF
			ENDIF
			DISPLAY_TEXT_WITH_NUMBER text_X_ST[temp_int_1_ST] text_Y_ST[temp_int_1_ST] SCREEN temp_int_1_ST 

			temp_int_1_ST ++
		ENDWHILE

	ENDIF

GOTO screen_tools

ENDIF

// #################################################################################################################################################
// 											MATRIX CAMERA SETUP 
// #################################################################################################################################################
VAR_INT view_mode_MCS spin_box_MCS
VAR_FLOAT spin_box_pos_X_MCS  spin_box_pos_Y_MCS spin_box_pos_Z_MCS
VAR_FLOAT spin_box_offset_X_MCS spin_box_offset_Y_MCS spin_box_offset_Z_MCS
VAR_FLOAT spin_box_rotate_X_MCS spin_box_rotate_Y_MCS spin_box_rotate_Z_MCS
VAR_INT spin_box_frame_counter_MCS spin_box_time_MCS
VAR_INT spin_box_timer_active_MCS
VAR_FLOAT temp_float_1_MCS temp_float_2_MCS temp_float_3_MCS

view_mode_MCS = 0

matrix_camera_setup:

IF design_mode = 6

	WAIT 0

 	IF NOT IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ESC  // ESCAPE

		IF view_mode_MCS = 0
			
			VIEW_INTEGER_VARIABLE view_mode_MCS view_mode			
			REQUEST_MODEL cardboardbox

			WHILE NOT HAS_MODEL_LOADED cardboardbox
				WAIT 0
			ENDWHILE

			spin_box_pos_X_MCS = 0.0
			spin_box_pos_Y_MCS = 0.0
			spin_box_pos_Z_MCS = 0.0

			spin_box_offset_X_MCS = 0.0
			spin_box_offset_Y_MCS = 0.0
			spin_box_offset_Z_MCS = 0.0

			spin_box_rotate_X_MCS = 0.0
			spin_box_rotate_Y_MCS = 0.0
			spin_box_rotate_Z_MCS = 0.0

			spin_box_timer_active_MCS = 0

			view_mode_MCS = 1
			
			IF NOT IS_CHAR_DEAD scplayer
				GET_CHAR_COORDINATES scplayer spin_box_pos_X_MCS spin_box_pos_Y_MCS spin_box_pos_Z_MCS
			ENDIF
			spin_box_pos_Z_MCS = spin_box_pos_Z_MCS + 2.0

			VIEW_FLOAT_VARIABLE spin_box_pos_X_MCS X_pos 
			VIEW_FLOAT_VARIABLE	spin_box_pos_Y_MCS Y_pos
			VIEW_FLOAT_VARIABLE spin_box_pos_Z_MCS Z_pos
			
			CREATE_OBJECT_NO_OFFSET cardboardbox spin_box_pos_X_MCS spin_box_pos_Y_MCS spin_box_pos_Z_MCS spin_box_MCS
			SET_OBJECT_COLLISION spin_box_MCS FALSE
			SET_OBJECT_DYNAMIC spin_box_MCS FALSE
			ATTACH_CAMERA_TO_OBJECT_LOOK_AT_OBJECT spin_box_MCS 4.0 4.0 0.0 spin_box_MCS 0.0 JUMP_CUT 
		
		ELSE
			
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
				
			temp_float_1_MCS =# RStickX
			temp_float_1_MCS = temp_float_1_MCS / 40.0
			
			temp_float_2_MCS =# RStickY
			temp_float_2_MCS = temp_float_2_MCS / 40.0
				
			temp_float_3_MCS =# LStickY
			temp_float_3_MCS = temp_float_3_MCS / 40.0
				
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
				
				WAIT 200

				view_mode_MCS ++
				IF view_mode_MCS = 4
					view_mode_MCS = 1
					VIEW_FLOAT_VARIABLE spin_box_pos_X_MCS X_pos 
					VIEW_FLOAT_VARIABLE	spin_box_pos_Y_MCS Y_pos
					VIEW_FLOAT_VARIABLE spin_box_pos_Z_MCS Z_pos
				ELSE
					IF view_mode_MCS = 3
						VIEW_FLOAT_VARIABLE spin_box_rotate_X_MCS X_rot 
						VIEW_FLOAT_VARIABLE	spin_box_rotate_Y_MCS Y_rot
						VIEW_FLOAT_VARIABLE spin_box_rotate_Z_MCS Z_rot
					ELSE
						VIEW_FLOAT_VARIABLE spin_box_offset_X_MCS X_off 
						VIEW_FLOAT_VARIABLE	spin_box_offset_Y_MCS Y_off
						VIEW_FLOAT_VARIABLE spin_box_offset_Z_MCS Z_off
					ENDIF
				ENDIF
			ENDIF

				
			IF spin_box_timer_active_MCS = 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					spin_box_timer_active_MCS = 1
					VIEW_INTEGER_VARIABLE spin_box_frame_counter_MCS frame_counter 
					VIEW_INTEGER_VARIABLE spin_box_time_MCS timer
				ENDIF
			ELSE
				IF spin_box_timer_active_MCS = 1
					IF IS_BUTTON_PRESSED PAD1 LEFTSTICKX
					OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
					OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKX
					OR IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
						TIMERA = 0
						spin_box_timer_active_MCS = 2
						spin_box_frame_counter_MCS = 0
						spin_box_time_MCS = TIMERA	
					ENDIF 
				ELSE
					IF IS_BUTTON_PRESSED PAD1 SQUARE
						spin_box_timer_active_MCS = 0
					ELSE
						spin_box_frame_counter_MCS ++
						spin_box_time_MCS = TIMERA
					ENDIF
				ENDIF	
			ENDIF
			
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RETURN
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA SPIN X = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_pos_X_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA SPIN Y = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_pos_Y_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA SPIN Z = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_pos_Z_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA OFFSET X = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_offset_X_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA OFFSET Y = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_offset_Y_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA OFFSET Z = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_offset_Z_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA ROTATE X = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_rotate_X_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA ROTATE Y = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_rotate_Y_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA ROTATE Z = "
				SAVE_FLOAT_TO_DEBUG_FILE spin_box_rotate_Z_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_NEWLINE_TO_DEBUG_FILE				
				SAVE_STRING_TO_DEBUG_FILE "CAMERA TIME = "
				SAVE_INT_TO_DEBUG_FILE spin_box_time_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "CAMERA FRAMES = "
				SAVE_INT_TO_DEBUG_FILE spin_box_frame_counter_MCS
				SAVE_NEWLINE_TO_DEBUG_FILE			   
			ENDIF



				
			IF view_mode_MCS = 1 // spin box position
				spin_box_pos_X_MCS = spin_box_pos_X_MCS + temp_float_1_MCS
				spin_box_pos_Y_MCS = spin_box_pos_Y_MCS + temp_float_2_MCS
				spin_box_pos_Z_MCS = spin_box_pos_Z_MCS + temp_float_3_MCS
				SET_OBJECT_COORDINATES spin_box_MCS spin_box_pos_X_MCS spin_box_pos_Y_MCS spin_box_pos_Z_MCS 
			ELSE
				IF view_mode_MCS = 2 // spin box offset
					spin_box_offset_X_MCS = spin_box_offset_X_MCS + temp_float_1_MCS
					spin_box_offset_Y_MCS = spin_box_offset_Y_MCS + temp_float_2_MCS
					spin_box_offset_Z_MCS = spin_box_offset_Z_MCS + temp_float_3_MCS
					ATTACH_CAMERA_TO_OBJECT_LOOK_AT_OBJECT spin_box_MCS spin_box_offset_X_MCS spin_box_offset_Y_MCS spin_box_offset_Z_MCS spin_box_MCS 0.0 JUMP_CUT
				ELSE
					IF view_mode_MCS = 3 // spin box rotation
						spin_box_rotate_X_MCS = spin_box_rotate_X_MCS + temp_float_1_MCS
						spin_box_rotate_Y_MCS = spin_box_rotate_Y_MCS + temp_float_2_MCS
						spin_box_rotate_Z_MCS = spin_box_rotate_Z_MCS + temp_float_3_MCS
						SET_OBJECT_ROTATION spin_box_MCS spin_box_rotate_X_MCS spin_box_rotate_Y_MCS spin_box_rotate_Z_MCS  
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ELSE

		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 ON
		ENDIF
		view_mode_MCS = 0

		IF DOES_OBJECT_EXIST spin_box_MCS
			SET_OBJECT_VISIBLE spin_box_MCS FALSE
			MARK_OBJECT_AS_NO_LONGER_NEEDED spin_box_MCS
		ENDIF
				
		MARK_MODEL_AS_NO_LONGER_NEEDED cardboardbox

		design_mode = 0
											
	ENDIF

	GOTO matrix_camera_setup

ENDIF














// #################################################################################################################################################
// 											CHRIS'S RACE COORD FINDER 
// #################################################################################################################################################
IF design_mode = 9

	VAR_FLOAT separator_distance

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_NUMPAD_PLUS
		separator_distance += 1.0
	ENDIF 
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_NUMPAD_MINUS
		separator_distance -= 1.0
	ENDIF
	IF separator_distance < 1.0
		separator_distance = 1.0
	ENDIF	

	/////////////////////////////////////////////////////////////////////
	// GET RACE COORDS part2 //////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	IF IS_PLAYER_PLAYING player1
		IF coords_DT = 0
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
				LVAR_INT pad1_circle_pressed
				IF pad1_circle_pressed = 1
					++ coords_DT
					PRINT_NOW CHEATON 800 1
					//pad1_circle_pressed = 0
				ENDIF
			ELSE
				pad1_circle_pressed = 1
			ENDIF
		ENDIF

		IF coords_DT > 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CHAR_COORDINATES scplayer x y z
				LVAR_FLOAT last_x last_y last_z
				GET_DISTANCE_BETWEEN_COORDS_3D x y z last_x last_y last_z distance
				LVAR_INT distance_int
				distance_int =# distance
				//GOSUB setup_text_cprace
				SET_TEXT_DROPSHADOW 2 0 0 0 255
				SET_TEXT_SCALE 0.8 2.0
				PRINT_WITH_2_NUMBERS_NOW TIME distance_int coords_DT 1000 1
				IF distance > separator_distance
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					IF pad1_circle_pressed = 1
						PRINT_NOW TEXXYZ4 800 1 // Writing coordinates to file...
						-- coords_DT
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_x["
						SAVE_INT_TO_DEBUG_FILE coords_DT
						SAVE_STRING_TO_DEBUG_FILE "] = "
						SAVE_FLOAT_TO_DEBUG_FILE x
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_y["
						SAVE_INT_TO_DEBUG_FILE coords_DT
						SAVE_STRING_TO_DEBUG_FILE "] = "
						SAVE_FLOAT_TO_DEBUG_FILE y
						SAVE_NEWLINE_TO_DEBUG_FILE
						SAVE_STRING_TO_DEBUG_FILE "checkpoints_z["
						SAVE_INT_TO_DEBUG_FILE coords_DT
						SAVE_STRING_TO_DEBUG_FILE "] = "
						SAVE_FLOAT_TO_DEBUG_FILE z
						SAVE_NEWLINE_TO_DEBUG_FILE
						++ coords_DT
						++ coords_DT
						last_x = x
						last_y = y
						last_z = z

						pad1_circle_pressed = 0
					ENDIF
				ELSE
					pad1_circle_pressed = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

ENDIF


// #################################################################################################################################################
// 												FUNCTIONS WHICH APPLY TO ALL MODES 
// #################################################################################################################################################
IF NOT design_mode = 0

	// === OUTPUT DATA ===
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RETURN	// outputs current entity only
		IF design_mode = 1
			IF DOES_OBJECT_EXIST op_object[op_current_object]
				GET_OBJECT_COORDINATES op_object[op_current_object] op_object_x op_object_y op_object_z
				GET_OBJECT_HEADING op_object[op_current_object] op_object_h
				SAVE_STRING_TO_DEBUG_FILE "                  x       y       z       h       rx      ry      rz"
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "SINGLE OBJECT "
				SAVE_INT_TO_DEBUG_FILE op_current_object
				SAVE_FLOAT_TO_DEBUG_FILE op_object_x
				SAVE_FLOAT_TO_DEBUG_FILE op_object_y
				SAVE_FLOAT_TO_DEBUG_FILE op_object_z
				SAVE_FLOAT_TO_DEBUG_FILE op_object_h
				SAVE_FLOAT_TO_DEBUG_FILE op_object_rx
				SAVE_FLOAT_TO_DEBUG_FILE op_object_ry
				SAVE_FLOAT_TO_DEBUG_FILE op_object_rz
			ENDIF
		ENDIF
		IF design_mode = 2
			IF DOES_VEHICLE_EXIST cp_car[cp_current_car]
				IF NOT IS_CAR_DEAD cp_car[cp_current_car]
					GET_CAR_COORDINATES cp_car[cp_current_car] cp_car_x cp_car_y cp_car_z
					GET_CAR_HEADING cp_car[cp_current_car] cp_car_h
				ENDIF
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "SINGLE VEHICLE = "
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_x
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_y
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_z
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_h
			ENDIF
		ENDIF
		IF design_mode = 3
			IF DOES_CHAR_EXIST pp_ped[pp_current_ped]
				IF NOT IS_CHAR_DEAD pp_ped[pp_current_ped]
					GET_CHAR_COORDINATES pp_ped[pp_current_ped] pp_ped_x pp_ped_y pp_ped_z
					pp_ped_z += -1.0
					GET_CHAR_HEADING pp_ped[pp_current_ped] pp_ped_h
				ENDIF
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "SINGLE PED = "
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_x
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_y
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_z
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_h
			ENDIF
		ENDIF
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ENTER	// outputs all entities
		dt_temp_int1 = 1
		op_temp_int = 0
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "       	 model      x       y       z       h       rx      ry      rz"
		WHILE op_temp_int < 30
			IF DOES_OBJECT_EXIST op_object[op_temp_int]
				GET_OBJECT_COORDINATES op_object[op_temp_int] op_object_x op_object_y op_object_z
				GET_OBJECT_HEADING op_object[op_temp_int] op_object_h
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "OBJECT"
				SAVE_INT_TO_DEBUG_FILE dt_temp_int1
				//SAVE_TEXT_LABEL_TO_DEBUG_FILE $op_string[0]
				SAVE_INT_TO_DEBUG_FILE op_model_model[op_temp_int]
				SAVE_FLOAT_TO_DEBUG_FILE op_object_x
				SAVE_FLOAT_TO_DEBUG_FILE op_object_y
				SAVE_FLOAT_TO_DEBUG_FILE op_object_z
				SAVE_FLOAT_TO_DEBUG_FILE op_object_h
				SAVE_FLOAT_TO_DEBUG_FILE op_object_rx
				SAVE_FLOAT_TO_DEBUG_FILE op_object_ry
				SAVE_FLOAT_TO_DEBUG_FILE op_object_rz
				dt_temp_int1++
			ENDIF
		op_temp_int++
		ENDWHILE 

		dt_temp_int1 = 1
		cp_temp_int = 0
		WHILE cp_temp_int < 10
			IF DOES_VEHICLE_EXIST cp_car[cp_temp_int]
				IF NOT IS_CAR_DEAD cp_car[cp_temp_int]
					GET_CAR_COORDINATES cp_car[cp_temp_int] cp_car_x cp_car_y cp_car_z
					GET_CAR_HEADING cp_car[cp_temp_int] cp_car_h
				ENDIF
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "VEHICLE NUMBER "
				SAVE_INT_TO_DEBUG_FILE dt_temp_int1
				SAVE_STRING_TO_DEBUG_FILE " = "
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_x
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_y
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_z
				SAVE_FLOAT_TO_DEBUG_FILE cp_car_h
				dt_temp_int1++
			ENDIF
		cp_temp_int++
		ENDWHILE

		dt_temp_int1 = 1
		pp_temp_int = 0
		WHILE pp_temp_int < 15
			IF DOES_CHAR_EXIST pp_ped[pp_temp_int]
				IF NOT IS_CHAR_DEAD pp_ped[pp_temp_int]
					GET_CHAR_COORDINATES pp_ped[pp_temp_int] pp_ped_x pp_ped_y pp_ped_z
					GET_CHAR_HEADING pp_ped[pp_temp_int] pp_ped_h
				ENDIF
				SAVE_NEWLINE_TO_DEBUG_FILE
				SAVE_STRING_TO_DEBUG_FILE "PED NUMBER "
				SAVE_INT_TO_DEBUG_FILE dt_temp_int1
				SAVE_STRING_TO_DEBUG_FILE " = "
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_x
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_y
				pp_ped_z += -1.0
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_z
				SAVE_FLOAT_TO_DEBUG_FILE pp_ped_h
				dt_temp_int1++
			ENDIF
		pp_temp_int++
		ENDWHILE


	ENDIF


	// === QUIT - CLEANUP ====	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ESC
			
		// objects
		op_temp_int = 0
		WHILE op_temp_int < 25
			IF DOES_OBJECT_EXIST op_object[op_temp_int]
				DELETE_OBJECT op_object[op_temp_int]
			ENDIF
		op_temp_int++
		ENDWHILE

		// cars
		cp_temp_int = 0
		WHILE cp_temp_int < 10
			IF DOES_VEHICLE_EXIST cp_car[cp_temp_int]
				DELETE_CAR cp_car[cp_temp_int]
			ENDIF
		cp_temp_int++
		ENDWHILE

		// peds
		pp_temp_int = 0
		WHILE pp_temp_int < 10
			IF DOES_CHAR_EXIST pp_ped[pp_temp_int]
				DELETE_CHAR pp_ped[pp_temp_int]
			ENDIF
		pp_temp_int++
		ENDWHILE

		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 ON
		ENDIF
		
		IF design_mode = 9
			design_mode = 0
		ENDIF

		CLEAR_HELP

	ENDIF

ENDIF


WAIT 0

GOTO design_tools_loop

// ****************************************************************************************************************************
//														GOSUBS
// ****************************************************************************************************************************
VAR_FLOAT dt_cam_x1 dt_cam_y1 dt_cam_z1
VAR_FLOAT dt_cam_x2 dt_cam_y2 dt_cam_z2
VAR_FLOAT dt_cam_nvec_x dt_cam_rvec_x
VAR_FLOAT dt_cam_nvec_y dt_cam_rvec_y
VAR_FLOAT dt_cam_dist
dt_update_camera_vector:

	GET_DEBUG_CAMERA_POINT_AT 		dt_cam_x1 dt_cam_y1 dt_cam_z1
	GET_DEBUG_CAMERA_COORDINATES 	dt_cam_x2 dt_cam_y2 dt_cam_z2	
	dt_cam_nvec_x = dt_cam_x1 - dt_cam_x2
	dt_cam_nvec_y = dt_cam_y1 - dt_cam_y2
	GET_DISTANCE_BETWEEN_COORDS_2D dt_cam_x1 dt_cam_y1 dt_cam_x2 dt_cam_y2 dt_cam_dist
	dt_cam_nvec_x /= dt_cam_dist 
	dt_cam_nvec_y /= dt_cam_dist
	
	dt_cam_rvec_x =	dt_cam_nvec_y
	dt_cam_rvec_y =	dt_cam_nvec_x
	dt_cam_rvec_y *= -1.0

RETURN

MISSION_END 
}