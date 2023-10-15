MISSION_START
//SCRIPT_NAME IMPEXPS
//{
//	START_NEW_SCRIPT import_export_script
//}
MISSION_END


VAR_INT current_wanted_list
VAR_INT current_wanted_car_list[10]	   // stores model ids of cars we want
VAR_INT current_wanted_car_status[10]  // 0 - need, 1 - got
//VAR_INT current_wanted_car_payout[10]  // stores the value of each car
VAR_INT current_import_car_list[6]
VAR_INT unlocked_import_cars[42]	   // total of 42

VAR_INT imported_car
VAR_INT current_vehicle_cost

VAR_INT cross_out[10]
VAR_INT export_name[10]
VAR_INT import_name[6]
VAR_INT restore_mobile_for_filshie

VAR_INT wanted_list_object

VAR_INT impexp_selected_day
VAR_INT impexp_selected_car

VAR_FLOAT impexp_door_rotation

// globals that couldn't fit in the script
VAR_TEXT_LABEL impexp_text_label

VAR_INT impexp_menu
VAR_INT impexp_panel

VAR_TEXT_LABEL impexp_menu_text_label[6]
VAR_TEXT_LABEL impexp_day[7]

VAR_INT impexp_has_been_initialised

VAR_INT impexp_new_cars
VAR_INT explain_import_help

VAR_INT impexp_is_complete

VAR_INT steal4_flag

VAR_FLOAT import_price_multiplier
VAR_FLOAT export_price_multiplier
VAR_FLOAT export_damage_multiplier

VAR_INT imported_cars[5] // stores imported cars in memory

// ***************************************************
// 			IMPORT / EXPORT SCRIPT 
// ***************************************************
{ 
import_export_script:
SCRIPT_NAME IMPEXPM

	//WRITE_DEBUG IMPEXPM_STARTED

	LVAR_INT tri_is_pressed
	LVAR_INT cross_is_pressed
	LVAR_INT up_is_pressed
	LVAR_INT down_is_pressed


	LVAR_INT temp_int
	LVAR_INT temp_int2
	LVAR_INT temp_model
	LVAR_FLOAT x2 y2 z2
	LVAR_FLOAT x3 y3 
	LVAR_FLOAT temp_float temp_float2 temp_float3 

	LVAR_INT impexp_mode // 0 - exporting, 1 - importing
	LVAR_INT m_goals

	LVAR_INT crane_car
	LVAR_INT crane_ped
	LVAR_INT crane_obj
	LVAR_INT last_crane_car
	LVAR_INT stored_model
	LVAR_INT export_car

	LVAR_INT help_flag
	LVAR_INT selector_obj  
	LVAR_INT current_selection
	LVAR_INT delete_export_car_flag

	// board stuff
	LVAR_INT board
		
	LVAR_INT imp_exp_help	

	LVAR_INT payout
	LVAR_INT temp_seq

	LVAR_INT player_was_in_car


// ----- INITIALISATION ---- ONLY RUN ONCE!!! --------

	IF NOT impexp_has_been_initialised = 99

		// fake create
		IF impexp_has_been_initialised = -1000
			CREATE_OBJECT_NO_OFFSET NF_BLACKBOARD 0.0 0.0 0.0 board	
			CREATE_CAR_GENERATOR_WITH_PLATE 0.0 0.0 0.0	180.0 EUROS -1 -1 FALSE 50 0 0 10000 IMPEXP__ impexp_car_gen[0]
		ENDIF

		// create blackboard
		IF NOT DOES_OBJECT_EXIST board
			CREATE_OBJECT_NO_OFFSET NF_BLACKBOARD -1573.8812 135.3845 2.5350 board
			SET_OBJECT_HEADING board 180.0
			FREEZE_OBJECT_POSITION board TRUE
			SET_OBJECT_DYNAMIC board FALSE
			DONT_REMOVE_OBJECT board
		ENDIF
								
		// set stuff intially, that will only ever be set ONCE
		current_wanted_list = 0

		// clear list of unlockable cars, except for the initial unlocked ones.
		unlocked_import_cars[0] = CLUB 
		unlocked_import_cars[1] = PEREN 
		unlocked_import_cars[2] = JESTER 
		unlocked_import_cars[3] = CLUB 
		unlocked_import_cars[4] = PEREN 
		unlocked_import_cars[5] = JESTER 
		temp_int = 6
		WHILE temp_int < 42
			unlocked_import_cars[temp_int] = -1
		temp_int++
		ENDWHILE	


		$impexp_day[0] = &IE16
		$impexp_day[1] = &IE10
		$impexp_day[2] = &IE11
		$impexp_day[3] = &IE12
		$impexp_day[4] = &IE13
		$impexp_day[5] = &IE14
		$impexp_day[6] = &IE15

		explain_import_help = 0
		impexp_new_cars = 0
		impexp_is_complete = 0

		import_price_multiplier = 0.8
		export_price_multiplier	= 1.0
		
		//VIEW_INTEGER_VARIABLE impexp_new_cars impexp_new_cars

		imported_cars[0] = -1
		imported_cars[1] = -1
		imported_cars[2] = -1
		imported_cars[3] = -1
		imported_cars[4] = -1
		

		START_NEW_SCRIPT impexp_car_check

		impexp_has_been_initialised = 99

	ENDIF


import_export_script_start:


// wait for player to get near -------------------------------------------------
impexp_script_manager_loop:
WAIT 0
	IF IS_PLAYER_PLAYING player1
//		IF IS_CHAR_IN_ZONE scplayer EASB	 // Easter Basin zone.
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1574.8812 135.3845 150.0 150.0 FALSE
		AND import_export_is_active = 1
			impexp_mode = 0
			GOTO impexp_main 
		ENDIF
	ENDIF
GOTO impexp_script_manager_loop


delete_export_car_flag = 0



// load up stuff we need for import export --------------------------------------
impexp_main:

IF import_export_is_active = 1
	IF activate_mobile_phone = 1 
		activate_mobile_phone = 0
		restore_mobile_for_filshie = 1
	ENDIF
ENDIF

GOSUB impexp_update_car_gens

//WRITE_DEBUG impexp_main_started

// fake creates
IF m_goals = -1
	CREATE_CAR PONY 0.0 0.0 0.0	crane_car
	CREATE_CAR PONY 0.0 0.0 0.0 last_crane_car
	CREATE_CAR PONY 0.0 0.0 0.0 export_car
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 board
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 cross_out[0]
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 export_name[0]
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 import_name[0]
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 selector_obj
//	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 door1
//	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 door2
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 magno_arm
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 wanted_list_object
ENDIF

m_goals = 0
last_crane_car = -1
help_flag = 0
impexp_door_rotation = 0.0
imp_exp_help = 0

GOSUB load_wanted_car_list
GOSUB load_import_car_list

// export names on board
IF import_export_is_active = 1
	IF DOES_OBJECT_EXIST wanted_list_object
		DELETE_OBJECT wanted_list_object
	ENDIF
	IF current_wanted_list = 0
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 0.0 0.0 x y z
		CREATE_OBJECT_NO_OFFSET NF_LIST_1 x y z wanted_list_object
		SET_OBJECT_HEADING wanted_list_object 180.0	
		//WRITE_DEBUG create_board1	
	ENDIF
	IF current_wanted_list = 1
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 0.0 0.0 x y z	
		CREATE_OBJECT_NO_OFFSET NF_LIST_2 x y z wanted_list_object
		SET_OBJECT_HEADING wanted_list_object 180.0	
		//WRITE_DEBUG create_board2
	ENDIF
	IF current_wanted_list = 2
		GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 0.0 0.0 x y z	
		CREATE_OBJECT_NO_OFFSET NF_LIST_3 x y z wanted_list_object
		SET_OBJECT_HEADING wanted_list_object 180.0	
		//WRITE_DEBUG create_board3
	ENDIF 
ENDIF

GOSUB impexp_update_car_gens

// create score offs
IF import_export_is_active = 1

	temp_int = 0
	WHILE temp_int < 10
		IF DOES_OBJECT_EXIST cross_out[temp_int]
			DELETE_OBJECT cross_out[temp_int]
		ENDIF 
		cross_out[temp_int] = -1	
		IF flag_player_on_mission = 0
		OR steal4_flag = 1
			IF current_wanted_car_status[temp_int] = 1
				IF NOT DOES_OBJECT_EXIST cross_out[temp_int]
					// figure out x y z
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 0.0 0.0 x y z
					temp_float =# temp_int
					IF temp_float > 4.0
						temp_float += -5.0
					ENDIF

					// add on verticle offset
					temp_float2 = temp_float
					temp_float2 *= -0.05
					temp_float *= -0.2355
					temp_float += temp_float2
					z += temp_float

					// add on horizontal offset
					IF temp_int < 5
						x += 1.741				   		
					ENDIF

					temp_float *= -0.1
					z += temp_float 
					CREATE_OBJECT_NO_OFFSET WANTED_CROSS_OFF x y z cross_out[temp_int]
					FREEZE_OBJECT_POSITION cross_out[temp_int] TRUE
					SET_OBJECT_HEADING cross_out[temp_int] 180.0	
				ENDIF
			ENDIF
		ENDIF
	temp_int++
	ENDWHILE
ENDIF

// import export loop -----------------------------------------------------------
impexp_main_loop:
WAIT 0
	
	IF import_export_is_active = 0
		GOTO impexp_main_loop
	ENDIF


	IF delete_export_car_flag = 1
		IF DOES_VEHICLE_EXIST export_car
			IF NOT IS_CAR_DEAD export_car
				FREEZE_CAR_POSITION export_car TRUE
				IF NOT IS_CAR_ON_SCREEN export_car
					//WRITE_DEBUG delete_export1
					DELETE_CAR export_car
					delete_export_car_flag = 0
				ENDIF
			ELSE
				//WRITE_DEBUG delete_export2
				DELETE_CAR export_car
				delete_export_car_flag = 0
			ENDIF
			// check it not being lifted by crane
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm crane_car crane_ped crane_obj
			IF crane_car = export_car
				RELEASE_ENTITY_FROM_ROPE_FOR_OBJECT magno_arm
			ENDIF
		ENDIF
	ENDIF

	IF NOT impexp_new_cars = 0
	AND TIMERB > 5000
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF impexp_new_cars = 1
				PRINT_HELP IE25
			ENDIF
			IF impexp_new_cars = 2
				PRINT_HELP IE26
			ENDIF 
			impexp_new_cars = 0
			//WRITE_DEBUG printed_help
		ENDIF
	ENDIF

	IF explain_import_help = 0
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1574.8812 135.3845 40.0 40.0 FALSE
				IF NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					PRINT_HELP IE27
					explain_import_help++
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF IS_PLAYER_PLAYING player1
		//IF IS_CHAR_IN_ZONE scplayer EASB	 // Easter Basin zone.
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1574.8812 135.3845 150.0 150.0 FALSE

			
			// PLAYER IS IN EXPORTING MODE (DEFAULT)	+++++++++
			IF impexp_mode = 0
				IF impexp_is_complete = 0	
					// WAIT FOR CORRECT CAT TO BE PLACED IN LOCATE
					IF m_goals = 0
						
						GOSUB store_last_crane_car	
					
						// display corona
						IF IS_PLAYER_PLAYING player1
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1577.9417 52.6333 16.3281 4.0 4.0 6.0 TRUE
								// fuck all
							ENDIF
						ENDIF

						GET_RANDOM_CAR_IN_SPHERE_NO_SAVE -1577.9417 52.6333 16.3281	4.0 -1 car
						
						IF DOES_VEHICLE_EXIST car
							IF NOT IS_CAR_DEAD car
								IF IS_CAR_STOPPED car
									IF NOT IS_CAR_IN_AIR_PROPER car
										IF NOT car = crane_car
											// check to see that car is wanted 
											
											GET_CAR_MODEL car temp_model
											// check if car is on wanted list
											temp_int = 0
											temp_int2 = 0
											WHILE temp_int < 10
												IF current_wanted_car_list[temp_int] = temp_model
													IF current_wanted_car_status[temp_int] = 0
														temp_int2 = 1
														temp_int = 10 
													ENDIF
												ENDIF
											temp_int++
											ENDWHILE
											IF temp_int2 = 1
												export_car = car
																									
												
												IF IS_CAR_PASSENGER_SEAT_FREE car 0
													CREATE_RANDOM_CHAR -1576.88 55.26 8.57 crane_ped
													WARP_CHAR_INTO_CAR_AS_PASSENGER crane_ped car 0
													SET_CHAR_VISIBLE crane_ped FALSE
													//WRITE_DEBUG ped_is_in_car 
												ELSE
													GET_CHAR_IN_CAR_PASSENGER_SEAT car 0 crane_ped
													DELETE_CHAR crane_ped
													CREATE_RANDOM_CHAR -1576.88 55.26 8.57 crane_ped
													WARP_CHAR_INTO_CAR_AS_PASSENGER crane_ped car 0
													SET_CHAR_VISIBLE crane_ped FALSE
													//WRITE_DEBUG ped_is_deleted
												ENDIF

												m_goals++
												TIMERA = 0
											ELSE
												IF NOT IS_MESSAGE_BEING_DISPLAYED 
													PRINT IE23 3000 1
												ENDIF
											ENDIF

										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						IF IS_PLAYER_PLAYING player1
							IF IS_CHAR_IN_ANY_CAR scplayer
								IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer -1577.9417 52.6333 16.3281 4.0 4.0 6.0 FALSE
									STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
									GET_CAR_MODEL car temp_model
									// check if car is on wanted list
									temp_int = 0
									temp_int2 = 0
									WHILE temp_int < 10
										IF current_wanted_car_list[temp_int] = temp_model
											IF current_wanted_car_status[temp_int] = 0
												temp_int2 = 1
												temp_int = 10 
											ENDIF
										ENDIF
									temp_int++
									ENDWHILE
									IF temp_int2 = 1
										export_car = car
										IF NOT IS_CAR_DEAD export_car
											//SET_CAR_STATUS export_car STATUS_PLAYER
										ENDIF
										m_goals++
										TIMERA = 0
									ELSE
										IF NOT IS_MESSAGE_BEING_DISPLAYED 
											PRINT IE23 3000 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF

					IF m_goals = 1

						IF DOES_OBJECT_EXIST magno_arm
							SET_ROPE_HEIGHT_FOR_OBJECT magno_arm 0.7
						ENDIF
						disable_crane = 1
						

						IF IS_PLAYER_PLAYING player1
							SET_PLAYER_CONTROL player1 OFF
						ENDIF

						DO_FADE 250 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						IF DOES_VEHICLE_EXIST export_car
							IF NOT IS_CAR_DEAD export_car
								IF IS_PLAYER_PLAYING player1
									GET_CHAR_COORDINATES scplayer x y z
								ENDIF
								SET_CAR_COORDINATES export_car -1577.9417 52.6333 40.0
								FREEZE_CAR_POSITION export_car TRUE
								GET_CAR_HEALTH export_car temp_int
								export_damage_multiplier =# temp_int
								export_damage_multiplier /= 1000.0
							ENDIF
						ENDIF

						SWITCH_WIDESCREEN ON	  
						SET_FIXED_CAMERA_POSITION -1573.4189 53.4591 17.9655 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -1574.3435 53.0835 17.9027 JUMP_CUT

						CLEAR_AREA -1577.9417 52.6333 16.3281 9.0 TRUE

						WAIT 0

						// align car position
						IF NOT IS_CAR_DEAD export_car
							GET_CAR_MODEL export_car temp_int
							GET_MODEL_DIMENSIONS temp_int x y z x2 y2 z2
							temp_float = y2 - y // car length
							temp_float /= 2.0
							temp_float *= -1.0
														
							SIN 45.0 temp_float3
							COS 45.0 temp_float2

							temp_float2 *= temp_float
							temp_float3 *= temp_float

							x = -1577.9417 + temp_float3
							y = 52.6333 + temp_float2
							z = 16.3281

							FREEZE_CAR_POSITION export_car FALSE
							SET_CAR_COORDINATES export_car x y z 
							SET_CAR_HEADING export_car 315.0
						ENDIF

						DO_FADE 250 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						// set players position (if not in crane)
						IF IS_PLAYER_PLAYING player1
							IF NOT IS_CAR_DEAD export_car
								IF IS_CHAR_IN_CAR scplayer export_car
									player_was_in_car = 1
									OPEN_SEQUENCE_TASK temp_seq
										TASK_LEAVE_CAR -1 export_car
										TASK_GO_STRAIGHT_TO_COORD -1 -1578.1637 63.7954 16.3281 PEDMOVE_WALK 10000
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK scplayer temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ELSE
									player_was_in_car = 0	
								ENDIF
							ENDIF
						ENDIF

						PRINT_NOW IE24 5000 1  // ~s~You have successfully delivered this vehicle for export.
												
						IF IS_BUTTON_PRESSED PAD1 CROSS
							cross_is_pressed = 1
						ENDIF

						TIMERA = 0
						m_goals++

					ENDIF

					// wait for a couple of secs
					IF m_goals = 2
						
						IF TIMERA > 5000
						OR IS_BUTTON_PRESSED PAD1 CROSS
							IF cross_is_pressed = 0
								m_goals++
							ENDIF
						ENDIF
						
						IF cross_is_pressed = 1
							
							IF NOT IS_BUTTON_PRESSED PAD1 CROSS
								cross_is_pressed = 0
							ENDIF
						ENDIF
								 
					ENDIF

					// cleanup 
					IF m_Goals = 3
						DO_FADE 500 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						// set char coordinates (if not in crane)
						IF NOT player_was_in_car = 0
							IF IS_PLAYER_PLAYING player1
								CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
								SET_CHAR_COORDINATES scplayer -1578.1637 63.7954 16.3281
								SET_CHAR_HEADING scplayer 0.8
							ENDIF
						ENDIF

						GOSUB impexp_update_car_lists

						//WRITE_DEBUG_WITH_INT impexp_new_cars impexp_new_cars

						DELETE_CAR export_car

						IF DOES_CHAR_EXIST crane_ped
							DELETE_CHAR crane_ped
						ENDIF

						SET_CAMERA_BEHIND_PLAYER
						RESTORE_CAMERA_JUMPCUT 
						SWITCH_WIDESCREEN OFF
						
						disable_crane = 0
						IF player_is_in_crane = 2
							reset_crane_camera = 1
						ENDIF
						 
						DO_FADE 500 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						
						IF IS_PLAYER_PLAYING player1
							SET_PLAYER_CONTROL player1 ON
						ENDIF
						 
						IF impexp_is_complete = 1
							PRINT_BIG IE29 5000	1 // all cars collected!!
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							WAIT 5000
						ENDIF
						
						IF IS_PLAYER_PLAYING player1
							ADD_SCORE player1 payout
						ENDIF
						PRINT_WITH_NUMBER_BIG IE30 payout 5000 1
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
						
						TIMERB = 0 

						GOTO impexp_main

					ENDIF
				ENDIF	


				// check if player is looking at export board
				IF DOES_OBJECT_EXIST board
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 1.5 0.0 x y z
					IF IS_PLAYER_PLAYING player1
						IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 2.0 1.0 2.0 FALSE
							IF NOT help_flag = 1
								PRINT_HELP IE18
								help_flag = 1
							ENDIF
							SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE
							IF tri_is_pressed = 0
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									tri_is_pressed = 1
									impexp_mode = 1
									GOTO impexp_main
								ENDIF
							ELSE
								IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE	
									tri_is_pressed = 0
								ENDIF
							ENDIF
						ELSE
							IF help_flag = 1
								CLEAR_HELP
								help_flag = 0
							ENDIF
							SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE	 
						ENDIF
					ENDIF 
				ENDIF
				
			ENDIF
			
			// PLAYER IS VIEWING IMPORT BOARD MODE --------------------------
			IF impexp_mode = 1
		
				// set player position and camera position

				import_mode_start:

				// --------------- SET FIRST MENU UP ----------------------------
				IF m_goals = 0

					// set camera

					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 OFF	
					ENDIF

					PRINT_HELP_FOREVER IE20  
					
					DISPLAY_RADAR FALSE
					
					// Create the main menu.
					CREATE_MENU IE09 29.0 170.0 180.0 1 TRUE TRUE 0 impexp_menu
					SET_MENU_COLUMN impexp_menu 0 DUMMY IE16 IE10 IE11 IE12 IE13 IE14 IE15 DUMMY DUMMY DUMMY DUMMY DUMMY

					IF temp_int = -69
						SET_ACTIVE_MENU_ITEM impexp_menu impexp_selected_day
					ELSE
						GET_CURRENT_DAY_OF_WEEK temp_int
						temp_int += -1 // match up with menu system
						SET_ACTIVE_MENU_ITEM impexp_menu temp_int
					ENDIF
					
					m_goals++
				ENDIF

				
				// --------  wait for user to select a day	------------------------
				IF m_goals = 1

					IF cross_is_pressed = 0
						
						IF IS_BUTTON_PRESSED PAD1 CROSS
							GET_MENU_ITEM_SELECTED impexp_menu impexp_selected_day
							m_goals++
							cross_is_pressed = 1
							CLEAR_THIS_PRINT IE28
						ENDIF	
					ELSE
						IF NOT IS_BUTTON_PRESSED PAD1 CROSS
							cross_is_pressed = 0
						ENDIF
					ENDIF 

					IF tri_is_pressed = 0
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							GOSUB impexp_delete_menus
							IF IS_PLAYER_PLAYING player1
								SET_PLAYER_CONTROL player1 ON
							ENDIF
							DISPLAY_RADAR TRUE
							CLEAR_HELP
							impexp_mode = 0
							m_goals = 0
							tri_is_pressed = 1
						ENDIF
					ELSE
						IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE	
							tri_is_pressed = 0
						ENDIF
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 DPADUP
					OR IS_BUTTON_PRESSED PAD1 DPADDOWN
						CLEAR_THIS_PRINT IE28
					ENDIF
				ENDIF
				

				// -----------  setup cars available for that day	---------------
				IF m_goals = 2

					// load cars available
					import_car_list_input = impexp_selected_day
					GOSUB load_import_car_list
					
					// check cars are available
					temp_int = 0
					temp_int2 = 0
					WHILE temp_int < 6
						IF NOT current_import_car_list[temp_int] = -1
							temp_int2 = 1
							temp_int = 6
						ENDIF		
					temp_int++
					ENDWHILE
					
					// no cars are available for that day

					IF temp_int2 = 0
						PRINT_NOW IE28 5000 1
						m_goals = 1 
						GOTO import_mode_start
					
					ELSE 
						PRINT_HELP_FOREVER IE21  
						DELETE_MENU impexp_menu

						// create menu. fill menu - first colum - cars
						CREATE_MENU IE09 29.0 170.0 180.0 2 TRUE TRUE 0 impexp_panel
						
						temp_int = 0
						WHILE temp_int < 6
							temp_model = current_import_car_list[temp_int]
							IF NOT temp_model = -1
								GOSUB get_vehicle_text_label
								$impexp_menu_text_label[temp_int] = $impexp_text_label
							ELSE
								$impexp_menu_text_label[temp_int] = DUMMY
							ENDIF
						temp_int++
						ENDWHILE

						temp_int = impexp_selected_day
						
						SET_MENU_COLUMN impexp_panel 0 $impexp_day[temp_int] $impexp_menu_text_label[0] $impexp_menu_text_label[1] $impexp_menu_text_label[2] $impexp_menu_text_label[3] $impexp_menu_text_label[4] $impexp_menu_text_label[5] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
						SET_MENU_COLUMN impexp_panel 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY

						temp_int = 0
						WHILE temp_int < 6
							temp_model = current_import_car_list[temp_int]
							IF NOT temp_model = -1
								GET_CAR_MODEL_VALUE current_import_car_list[temp_int] current_vehicle_cost
								temp_float =# current_vehicle_cost 
								temp_float *= import_price_multiplier
								current_vehicle_cost =# temp_float
								SET_MENU_ITEM_WITH_NUMBER impexp_panel 1 temp_int DOLLAR current_vehicle_cost
							ENDIF
						temp_int++
						ENDWHILE

						m_goals ++
					
					ENDIF

				ENDIF		
				
				// --------- wait for user to select -------------
				IF m_goals = 3

					IF cross_is_pressed = 0
						
						IF IS_BUTTON_PRESSED PAD1 CROSS

							//CLEAR_HELP  This is not allowed here; breaks existing saves :(
							GET_MENU_ITEM_SELECTED impexp_panel impexp_selected_car
							m_goals++
							cross_is_pressed = 1
						ENDIF	
					ELSE
						IF NOT IS_BUTTON_PRESSED PAD1 CROSS
							cross_is_pressed = 0
						ENDIF
					ENDIF
					IF tri_is_pressed = 0
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							GOSUB impexp_delete_menus
							m_goals = 0
							tri_is_pressed = 1
							temp_int = -69
							GOTO import_mode_start
						ENDIF
					ELSE
						IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
							tri_is_pressed = 0
						ENDIF
					ENDIF

			
				ENDIF	
				
				
				// --------  check if player can buy car --------
				IF m_goals = 4
					
					// check it's correct day
					GET_CURRENT_DAY_OF_WEEK temp_int
					temp_int += -1 // match up with menu system
					IF NOT temp_int =  impexp_selected_day
						PRINT_NOW IE19 5000 1 // wrong day!
						GOSUB impexp_delete_menus
						m_goals = 0
					ENDIF
					
					// check player has enough cash
					GET_CAR_MODEL_VALUE current_import_car_list[current_selection] current_vehicle_cost
					temp_float =# current_vehicle_cost 
					temp_float *= import_price_multiplier
					current_vehicle_cost =# temp_float	
					STORE_SCORE player1 temp_int
					IF current_vehicle_cost > temp_int
						PRINT_NOW IE07 5000 1 // not enough cash!
						GOSUB impexp_delete_menus
						m_goals = 0
					ENDIF 

					// check that a vehicle has been selected
					IF NOT m_goals = 0
						IF current_import_car_list[impexp_selected_car]	= -1
							GOSUB impexp_delete_menus
							m_goals = 0
						ENDIF
					ENDIF

					// we must be ok then - proceed with importing the car
					IF NOT m_goals = 0
//						current_selection = impexp_selected_car
//						GOSUB get_model_cost
						GET_CAR_MODEL_VALUE current_import_car_list[current_selection] current_vehicle_cost
						temp_float =# current_vehicle_cost 
						temp_float *= import_price_multiplier
						current_vehicle_cost =# temp_float
						temp_int = current_vehicle_cost	* -1
						ADD_SCORE player1 temp_int
						m_goals++
					ENDIF
					
				ENDIF


				// ------- create car ----------------------
				IF m_Goals = 5

					// load the model
					IF NOT HAS_MODEL_LOADED current_import_car_list[impexp_selected_car]
						REQUEST_MODEL current_import_car_list[impexp_selected_car]
						WHILE NOT HAS_MODEL_LOADED current_import_car_list[impexp_selected_car]
							WAIT 0
						ENDWHILE
					ENDIF	

					CLEAR_HELP					
					GOSUB impexp_delete_menus
					CLEAR_AREA -1572.1682 63.2853 16.3281 20.0 TRUE

					// create car
					GOSUB ie_create_custom_plates

					CREATE_CAR current_import_car_list[impexp_selected_car] -1572.1682 63.2853 16.3281 imported_car
					SET_VEHICLE_DIRT_LEVEL imported_car 0.0
					SET_CAR_HEADING imported_car 315.0
					MARK_MODEL_AS_NO_LONGER_NEEDED current_import_car_list[impexp_selected_car]

					// if it's a golf
					IF current_import_car_list[impexp_selected_car] = CLUB
						CHANGE_CAR_COLOUR imported_car 11 0
					ENDIF 

					GET_CAR_MODEL imported_car temp_int
					GET_MODEL_DIMENSIONS temp_int x y z x2 y2 z2
					temp_float = y2 - y // car length
					temp_float /= 2.0
					temp_float *= -1.0

					COS 45.0 temp_float2
					SIN 45.0 temp_float3

					temp_float2 *= temp_float
					temp_float3 *= temp_float

					x = -1572.1682 + temp_float3
					y = 63.2853 + temp_float2
					z = 16.3281

					SET_CAR_COORDINATES imported_car x y z 
					SET_CAR_HEADING imported_car 315.0

					CLEAR_PRINTS
					SWITCH_WIDESCREEN ON	  		   
					SET_FIXED_CAMERA_POSITION -1567.5967 63.2567 17.7586 0.0 0.0 0.0		
					POINT_CAMERA_AT_POINT 	  -1568.5756 63.0585 17.7094 JUMP_CUT 			
					PRINT_NOW IE08 5000 1 // Your new vehicle is on the container ship.

					// increment stat
					INCREMENT_INT_STAT VEHICLES_IMPORTED 1

					TIMERA = 0
					m_goals++

				ENDIF
				
				// ---------- admire car -----------
				IF m_goals = 6
					IF TIMERA > 5000
						m_goals++
					ELSE
						IF cross_is_pressed = 0
							
							IF IS_BUTTON_PRESSED PAD1 CROSS
								m_goals++
							ENDIF 
						ELSE 
							IF NOT IS_BUTTON_PRESSED PAD1 CROSS
								cross_is_pressed = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
				
				// -------- restore player, wait for him to pickup car -------------
				IF m_goals = 7		
					CLEAR_PRINTS
					SET_CAMERA_BEHIND_PLAYER 
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					IF IS_PLAYER_PLAYING player1 
						SET_PLAYER_CONTROL player1 ON
					ENDIF	   
					SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
					DISPLAY_RADAR TRUE
					CLEAR_HELP
					m_goals++
				ENDIF		
				

				// wait for player to remove car
				IF m_goals = 8
					IF NOT IS_CAR_DEAD imported_car		  
						IF NOT LOCATE_CAR_3D imported_car -1572.1682 63.2853 16.3281 30.0 30.0 5.0 FALSE
						AND NOT IS_POINT_ON_SCREEN -1572.1682 63.2853 16.3281 5.0
							MARK_CAR_AS_NO_LONGER_NEEDED imported_car
							impexp_mode = 0
							m_goals = 0	
							GOTO impexp_main
						ENDIF
					ELSE
						MARK_CAR_AS_NO_LONGER_NEEDED imported_car
						impexp_mode = 0
						m_goals = 0	
						GOTO impexp_main	
					ENDIF
					// check if player is looking at export board
					IF DOES_OBJECT_EXIST board
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS board 0.0 1.5 0.0 x y z
						IF IS_PLAYER_PLAYING player1
							IF LOCATE_CHAR_ON_FOOT_3D scplayer x y z 2.0 1.0 2.0 FALSE
								IF tri_is_pressed = 0
									IF IS_BUTTON_PRESSED PAD1 TRIANGLE
										tri_is_pressed = 1
										PRINT_NOW IE08 5000 1 // Your new vehicle is on the container ship.
									ENDIF
								ELSE
									IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
										tri_is_pressed = 0
									ENDIF
								ENDIF 
							ENDIF
						ENDIF 
					ENDIF
				ENDIF
			ENDIF

		ELSE
			GOTO impexp_cleanup
		ENDIF
	ELSE
		GOTO impexp_cleanup
	ENDIF

	

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U

		// find next wanted model
		temp_int = 0
		WHILE temp_int < 10
			IF current_wanted_car_status[temp_int] = 0
				temp_model = current_wanted_car_list[temp_int]
				temp_int = 10
			ENDIF
		temp_int++
		ENDWHILE

		GOSUB impexp_update_car_lists

		GOTO impexp_main

	ENDIF


	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_T

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "current_wanted_list = "
		SAVE_INT_TO_DEBUG_FILE current_wanted_list
		SAVE_NEWLINE_TO_DEBUG_FILE 
		SAVE_STRING_TO_DEBUG_FILE "modID, status, payout, exp name,  cross out"
		SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int = 0
		WHILE temp_int < 10
			SAVE_INT_TO_DEBUG_FILE current_wanted_car_list[temp_int]
			SAVE_STRING_TO_DEBUG_FILE "        "
			SAVE_INT_TO_DEBUG_FILE current_wanted_car_status[temp_int]
			SAVE_STRING_TO_DEBUG_FILE "        "
			//SAVE_INT_TO_DEBUG_FILE current_wanted_car_payout[temp_int]
			SAVE_STRING_TO_DEBUG_FILE "        "
			SAVE_INT_TO_DEBUG_FILE export_name[temp_int]
			SAVE_STRING_TO_DEBUG_FILE "        "
			SAVE_INT_TO_DEBUG_FILE cross_out[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE
		temp_int++
		ENDWHILE
	
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "import details"
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "modID, imp name"
		SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int = 0
		WHILE temp_int < 6
			SAVE_INT_TO_DEBUG_FILE current_import_car_list[temp_int]
			SAVE_STRING_TO_DEBUG_FILE "         "
			SAVE_INT_TO_DEBUG_FILE import_name[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE

		temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "unlocked_import_cars array"
		SAVE_NEWLINE_TO_DEBUG_FILE
		
		temp_int = 0
		WHILE temp_int < 42
			SAVE_STRING_TO_DEBUG_FILE "unlocked_import_cars["
			SAVE_INT_TO_DEBUG_FILE temp_int
			SAVE_STRING_TO_DEBUG_FILE "] = "
			SAVE_INT_TO_DEBUG_FILE unlocked_import_cars[temp_int]
			SAVE_NEWLINE_TO_DEBUG_FILE 
		temp_int++
		ENDWHILE
	ENDIF


GOTO impexp_main_loop

impexp_delete_menus:
DELETE_MENU impexp_menu
DELETE_MENU impexp_panel
RETURN


// cleanup then go back to the start of script ------------------------------------
impexp_cleanup:

	//WRITE_DEBUG impexp_cleaned_up

	IF DOES_VEHICLE_EXIST export_car
		IF DOES_OBJECT_EXIST magno_arm
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm crane_car crane_ped crane_obj
			IF NOT crane_car = export_car
				MARK_CAR_AS_NO_LONGER_NEEDED export_car
			ENDIF
		ENDIF
	ENDIF
	IF DOES_VEHICLE_EXIST imported_car
		IF DOES_OBJECT_EXIST magno_arm
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm crane_car crane_ped crane_obj
			IF NOT crane_car = imported_car
				MARK_CAR_AS_NO_LONGER_NEEDED imported_car
			ENDIF
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST crane_ped
		DELETE_CHAR crane_ped
	ENDIF


	IF DOES_OBJECT_EXIST selector_obj
		DELETE_OBJECT selector_obj
	ENDIF
	
	temp_int = 0
	WHILE temp_int < 10
		IF DOES_OBJECT_EXIST cross_out[temp_int]
			DELETE_OBJECT cross_out[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

	IF DOES_OBJECT_EXIST wanted_list_object
		DELETE_OBJECT wanted_list_object
	ENDIF

//	IF DOES_OBJECT_EXIST board
//		DELETE_OBJECT board
//	ENDIF	

	IF restore_mobile_for_filshie = 1
		activate_mobile_phone = 1	
	ENDIF
									    
GOTO import_export_script_start




// GOSUBS ***********************************************


impexp_board_cleanup:

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 ON
	   	SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
	ENDIF
	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	CLEAR_PRINTS
	tri_is_pressed = 1
	
RETURN



impexp_draw_buying_hud:

RETURN

impexp_update_car_lists:

	// update stat
	INCREMENT_INT_STAT VEHICLES_EXPORTED 1

	// mark car off list
	temp_int = 0
	WHILE temp_int < 10
		IF current_wanted_car_list[temp_int] = temp_model
			IF current_wanted_car_status[temp_int] = 0
				current_wanted_car_status[temp_int] = 1
				// give player cash
				//payout = current_wanted_car_payout[temp_int]
				GET_CAR_MODEL_VALUE temp_model payout
				temp_float =# payout
				temp_float *= export_price_multiplier
				temp_float *= export_damage_multiplier
				payout =# temp_float

				GET_INT_STAT VEHICLES_EXPORTED temp_int
				SWITCH temp_int
					CASE 10
						payout += 50000
					BREAK
					CASE 20
						payout += 100000
					BREAK
					CASE 30
						payout += 200000

						impexp_is_complete = 1
					BREAK
				ENDSWITCH

				temp_int = 10 
			ENDIF
		ENDIF
	temp_int++
	ENDWHILE

	// update car gens
	GOSUB impexp_update_car_gens

	// add car to unlocked list
	temp_int = 0
	WHILE temp_int < 42
		IF unlocked_import_cars[temp_int] = -1
			unlocked_import_cars[temp_int] = temp_model
			temp_int2 = temp_int
			temp_int = 42
		ENDIF	
	temp_int++
	ENDWHILE

	impexp_new_cars = 1
	//WRITE_DEBUG_WITH_INT impexp_new_cars impexp_new_cars

	// unlock special cars - one gets unlocked every 3ish cars
	SWITCH temp_int2
		CASE 10
			temp_int2++
			unlocked_import_cars[temp_int2] = MONSTER
			impexp_new_cars++
		BREAK
//		CASE 9
//			temp_int2++
//			unlocked_import_cars[temp_int2] = NRG500
//			impexp_new_cars++
//		BREAK
		CASE 16
			temp_int2++
			unlocked_import_cars[temp_int2] = WINDSOR
			PLAYER_MADE_PROGRESS 1
			PLAY_MISSION_PASSED_TUNE 2
			impexp_new_cars++
		BREAK
//		CASE 18
//			temp_int2++
//			unlocked_import_cars[temp_int2] = HOTKNIFE
//			impexp_new_cars++
//		BREAK
		CASE 22
			temp_int2++
			unlocked_import_cars[temp_int2] = BANDITO
			impexp_new_cars++
		BREAK
		CASE 28
			temp_int2++
			unlocked_import_cars[temp_int2] = TURISMO
			PLAYER_MADE_PROGRESS 1
			PLAY_MISSION_PASSED_TUNE 2
			impexp_new_cars++
		BREAK
//		CASE 31
//			temp_int2++
//			unlocked_import_cars[temp_int2] = DUNERIDE
//			impexp_new_cars++
//		BREAK
		CASE 34
			temp_int2++
			unlocked_import_cars[temp_int2] = VORTEX
			impexp_new_cars++
		BREAK
		CASE 40
			temp_int2++
			unlocked_import_cars[temp_int2] = BULLET
			PLAYER_MADE_PROGRESS 1
			PLAY_MISSION_PASSED_TUNE 2
			impexp_new_cars++
		BREAK
	ENDSWITCH

RETURN


load_wanted_car_list:

	IF flag_player_on_mission = 1
	AND NOT steal4_flag = 1
		//WRITE_DEBUG FLAG_PLAYER_ON_MISSION 
		current_wanted_car_list[0] = -1
		current_wanted_car_list[1] = -1
		current_wanted_car_list[2] = -1
		current_wanted_car_list[3] = -1
		current_wanted_car_list[4] = -1
		current_wanted_car_list[5] = -1
		current_wanted_car_list[6] = -1
		current_wanted_car_list[7] = -1
		current_wanted_car_list[8] = -1
		current_wanted_car_list[9] = -1
	ELSE

		// check if all the wanted cars have been got
		temp_int = 0
		temp_int2 = 0
		WHILE temp_int < 10
			IF current_wanted_car_status[temp_int] = 1
				temp_int2++
			ENDIF
		temp_int++
		ENDWHILE
		// if got them all 
		IF temp_int2 = 10
			// increment wanted list
			IF current_wanted_list < 2
				current_wanted_list++
				// clear need / got status
				temp_int = 0
				WHILE temp_int < 10
					current_wanted_car_status[temp_int] = 0	
				temp_int++
				ENDWHILE
			ELSE
				impexp_is_complete = 1
			ENDIF
		ENDIF

		IF current_wanted_list = 0
			current_wanted_car_list[0] = BUFFALO
			current_wanted_car_list[1] = SENTINEL
			current_wanted_car_list[2] = INFERNUS
			current_wanted_car_list[3] = CAMPER
			current_wanted_car_list[4] = ADMIRAL
			current_wanted_car_list[5] = PATRIOT
			current_wanted_car_list[6] = SANCHEZ
			current_wanted_car_list[7] = STRETCH
			current_wanted_car_list[8] = FELTZER
			current_wanted_car_list[9] = REMINGTN
		ENDIF

		IF current_wanted_list = 1
			current_wanted_car_list[0] = CHEETAH
			current_wanted_car_list[1] = RANCHER
			current_wanted_car_list[2] = STALLION
			current_wanted_car_list[3] = PETRO
			current_wanted_car_list[4] = COMET
			current_wanted_car_list[5] = SLAMVAN
			current_wanted_car_list[6] = BLISTAC
			current_wanted_car_list[7] = STAFFORD
			current_wanted_car_list[8] = SABRE
			current_wanted_car_list[9] = FCR900
		ENDIF

		IF current_wanted_list = 2
			current_wanted_car_list[0] = BANSHEE
			current_wanted_car_list[1] = SUPERGT
			current_wanted_car_list[2] = JOURNEY
			current_wanted_car_list[3] = HUNTLEY
			current_wanted_car_list[4] = BFINJECT
			current_wanted_car_list[5] = BLADE
			current_wanted_car_list[6] = FREEWAY
			current_wanted_car_list[7] = MESA
			current_wanted_car_list[8] = ZR350
			current_wanted_car_list[9] = EUROS
		ENDIF
	ENDIF

RETURN

store_last_crane_car:
	IF DOES_OBJECT_EXIST magno_arm
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT magno_arm crane_car crane_ped crane_obj
		IF NOT crane_car = -1
			IF NOT last_crane_car = crane_car
				last_crane_car = crane_car
			ENDIF
		ENDIF
	ENDIF
RETURN


VAR_INT import_car_list_input
load_import_car_list:

	temp_int = 0
	WHILE temp_int < 6
		current_import_car_list[temp_int] = -1
	temp_int++
	ENDWHILE
	
	//GET_CURRENT_DAY_OF_WEEK temp_int

	// sunday
	SWITCH import_car_list_input
		CASE 0
			current_import_car_list[0] = unlocked_import_cars[0]
			current_import_car_list[1] = unlocked_import_cars[7]
			current_import_car_list[2] = unlocked_import_cars[14]
			current_import_car_list[3] = unlocked_import_cars[21]
			current_import_car_list[4] = unlocked_import_cars[28]
			current_import_car_list[5] = unlocked_import_cars[35]
		BREAK

		// monday
		CASE 1
			current_import_car_list[0] = unlocked_import_cars[1]
			current_import_car_list[1] = unlocked_import_cars[8]
			current_import_car_list[2] = unlocked_import_cars[15]
			current_import_car_list[3] = unlocked_import_cars[22]
			current_import_car_list[4] = unlocked_import_cars[29]
			current_import_car_list[5] = unlocked_import_cars[36]
		BREAK

		// tuesday
		CASE 2
			current_import_car_list[0] = unlocked_import_cars[2]
			current_import_car_list[1] = unlocked_import_cars[9]
			current_import_car_list[2] = unlocked_import_cars[16]
			current_import_car_list[3] = unlocked_import_cars[23]
			current_import_car_list[4] = unlocked_import_cars[30]
			current_import_car_list[5] = unlocked_import_cars[37]
		BREAK

		// wednesday
		CASE 3
			current_import_car_list[0] = unlocked_import_cars[3]
			current_import_car_list[1] = unlocked_import_cars[10]
			current_import_car_list[2] = unlocked_import_cars[17]
			current_import_car_list[3] = unlocked_import_cars[24]
			current_import_car_list[4] = unlocked_import_cars[31]
			current_import_car_list[5] = unlocked_import_cars[38]	   	
		BREAK

		// thursday
		CASE 4
			current_import_car_list[0] = unlocked_import_cars[4]
			current_import_car_list[1] = unlocked_import_cars[11]
			current_import_car_list[2] = unlocked_import_cars[18]
			current_import_car_list[3] = unlocked_import_cars[25]
			current_import_car_list[4] = unlocked_import_cars[32]
			current_import_car_list[5] = unlocked_import_cars[39]
		BREAK

		// friday
		CASE 5
			current_import_car_list[0] = unlocked_import_cars[5]
			current_import_car_list[1] = unlocked_import_cars[12]
			current_import_car_list[2] = unlocked_import_cars[19]
			current_import_car_list[3] = unlocked_import_cars[26]
			current_import_car_list[4] = unlocked_import_cars[33]
			current_import_car_list[5] = unlocked_import_cars[40]
		BREAK

		// saturday
		CASE 6
			current_import_car_list[0] = unlocked_import_cars[6]
			current_import_car_list[1] = unlocked_import_cars[13]
			current_import_car_list[2] = unlocked_import_cars[20]
			current_import_car_list[3] = unlocked_import_cars[27]
			current_import_car_list[4] = unlocked_import_cars[34]
			current_import_car_list[5] = unlocked_import_cars[41]
		BREAK
	ENDSWITCH

RETURN

//get_model_cost:
//
//	temp_model = current_import_car_list[current_selection]
//
//	current_vehicle_cost = 0
//	
//	SWITCH temp_model
//		CASE CLUB
//			current_vehicle_cost = 100
//		BREAK
//		CASE JESTER
//			current_vehicle_cost = 100
//		BREAK
//		CASE BUFFALO
//			current_vehicle_cost = 100
//		BREAK
//		CASE SENTINEL
//			current_vehicle_cost = 100
//		BREAK
//		CASE INFERNUS
//			current_vehicle_cost = 100
//		BREAK
//		CASE CAMPER
//			current_vehicle_cost = 100
//		BREAK
//		CASE ADMIRAL
//			current_vehicle_cost = 100
//		BREAK
//		CASE PATRIOT
//			current_vehicle_cost = 100
//		BREAK
//		CASE SANCHEZ
//			current_vehicle_cost = 100
//		BREAK
//		CASE STRETCH
//			current_vehicle_cost = 100
//		BREAK
//		CASE FELTZER
//			current_vehicle_cost = 100
//		BREAK
//		CASE REMINGTN
//			current_vehicle_cost = 100
//		BREAK
//		CASE MONSTER
//			current_vehicle_cost = 100
//		BREAK
//		CASE NRG500
//			current_vehicle_cost = 100
//		BREAK
//		CASE WINDSOR
//			current_vehicle_cost = 100
//		BREAK
//		CASE CHEETAH
//			current_vehicle_cost = 100
//		BREAK
//		CASE RANCHER
//			current_vehicle_cost = 100
//		BREAK
//		CASE STALLION
//			current_vehicle_cost = 100
//		BREAK
//		CASE PETRO
//			current_vehicle_cost = 100
//		BREAK
//		CASE COMET
//			current_vehicle_cost = 100
//		BREAK
//		CASE SLAMVAN
//			current_vehicle_cost = 100
//		BREAK
//		CASE BLISTAC
//			current_vehicle_cost = 100
//		BREAK
//		CASE STAFFORD
//			current_vehicle_cost = 100
//		BREAK
//		CASE SABRE
//			current_vehicle_cost = 100
//		BREAK
//		CASE FCR900
//			current_vehicle_cost = 100
//		BREAK
//		CASE EUROS
//			current_vehicle_cost = 100
//		BREAK
//		CASE BANDITO
//			current_vehicle_cost = 100
//		BREAK
//		CASE SUPERGT
//			current_vehicle_cost = 100
//		BREAK
//		CASE BANSHEE
//			current_vehicle_cost = 100
//		BREAK
//		CASE TURISMO
//			current_vehicle_cost = 100
//		BREAK
//		CASE JOURNEY
//			current_vehicle_cost = 100
//		BREAK
//		CASE HUNTLEY
//			current_vehicle_cost = 100
//		BREAK
//		CASE BFINJECT
//			current_vehicle_cost = 100
//		BREAK
//		CASE BLADE
//			current_vehicle_cost = 100
//		BREAK
//		CASE FREEWAY
//			current_vehicle_cost = 100
//		BREAK
//		CASE MESA
//			current_vehicle_cost = 100
//		BREAK
//		CASE ZR350
//			current_vehicle_cost = 100
//		BREAK
//		CASE HOTKNIFE
//			current_vehicle_cost = 100
//		BREAK
//		CASE DUNERIDE
//			current_vehicle_cost = 100
//		BREAK
//		CASE VORTEX
//			current_vehicle_cost = 100
//		BREAK
//		CASE BULLET
//			current_vehicle_cost = 100
//		BREAK
//		CASE PEREN
//			current_vehicle_cost = 5
//		BREAK
//	ENDSWITCH
//								  
//				
//	//PRINT_WITH_NUMBER_NOW IE06 current_vehicle_cost 9999999 1 // vehicle price: $
//								
//RETURN


add_car_to_players_imported_cars:

	add_car_to_players_imported_cars_start:

	temp_int = 0
	WHILE temp_int < 5
		IF imported_cars[temp_int] = -1
			imported_cars[temp_int] = imported_car
			temp_int = 10
		ELSE
			IF DOES_VEHICLE_EXIST imported_cars[temp_int] 
				IF IS_CAR_DEAD imported_cars[temp_int]
					MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[temp_int]
					imported_cars[temp_int] = -1
					GOTO add_car_to_players_imported_cars_start
				ELSE
					temp_int++	
				ENDIF
			ELSE
				MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[temp_int]
				imported_cars[temp_int] = -1	
				GOTO add_car_to_players_imported_cars_start
			ENDIF								
		ENDIF
	ENDWHILE

	// if 5 are full - replace last one
	IF temp_int = 5
		MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[4]
		imported_cars[4] = -1
		GOTO add_car_to_players_imported_cars_start
	ENDIF

RETURN

trim_players_imported_cars:

	temp_int = 0
	WHILE temp_int < 5

		IF NOT imported_cars[temp_int] = -1
			IF DOES_VEHICLE_EXIST imported_cars[temp_int]
				IF NOT IS_CAR_DEAD imported_cars[temp_int]
					IF IS_PLAYER_PLAYING player1
						IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer imported_cars[temp_int] 500.0 500.0 FALSE
							MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[temp_int]
							imported_cars[temp_int] = -1	
						ENDIF
					ENDIF	
				ELSE
					MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[temp_int]
					imported_cars[temp_int] = -1	
				ENDIF
			ELSE
				MARK_CAR_AS_NO_LONGER_NEEDED imported_cars[temp_int]
				imported_cars[temp_int] = -1
			ENDIF
		ENDIF

	temp_int++
	ENDWHILE

RETURN


impexp_update_car_gens:

	// switch on / off car gens
	temp_int = 0
	WHILE temp_int < 10
		IF current_wanted_list = 0
			temp_int2 = temp_int + 0
		ENDIF
		IF current_wanted_list = 1
			temp_int2 = temp_int + 10
		ENDIF 
		IF current_wanted_list = 2
			temp_int2 = temp_int + 20
		ENDIF
		IF current_wanted_car_status[temp_int] = 0
			SWITCH_CAR_GENERATOR impexp_car_gen[temp_int2] 101
		ELSE
			SWITCH_CAR_GENERATOR impexp_car_gen[temp_int2] 0				
		ENDIF	
	temp_int++
	ENDWHILE

RETURN
		
get_vehicle_text_label:

	SWITCH temp_model
		CASE CLUB
			$impexp_text_label = &CLUB
		BREAK					 
		CASE JESTER
			$impexp_text_label = &JESTER
		BREAK
		CASE BUFFALO
			$impexp_text_label = &BUFFALO
		BREAK
		CASE SENTINEL			 
			$impexp_text_label = &SENTINL
		BREAK
		CASE INFERNUS			 
			$impexp_text_label = &INFERNU
		BREAK
		CASE CAMPER				 
			$impexp_text_label = &CAMPER
		BREAK
		CASE ADMIRAL			 
			$impexp_text_label = &ADMIRAL
		BREAK
		CASE PATRIOT			 
			$impexp_text_label = &PATRIOT
		BREAK
		CASE SANCHEZ			 
			$impexp_text_label = &SANCHEZ
		BREAK
		CASE STRETCH			 
			$impexp_text_label = &STRETCH
		BREAK
		CASE FELTZER			 
			$impexp_text_label = &FELTZER
		BREAK
		CASE REMINGTN			 
			$impexp_text_label = &REMING
		BREAK
		CASE MONSTER			 
			$impexp_text_label = &MONSTER
		BREAK
		CASE NRG500				 
			$impexp_text_label = &NRG500
		BREAK
		CASE WINDSOR			 
			$impexp_text_label = &WINDSOR
		BREAK
		CASE CHEETAH			 
			$impexp_text_label = &CHEETAH
		BREAK
		CASE RANCHER			 
			$impexp_text_label = &RANCHER
		BREAK
		CASE STALLION			 
			$impexp_text_label = &STALION
		BREAK
		CASE PETRO				 
			$impexp_text_label = &PETROL
		BREAK
		CASE COMET				 
			$impexp_text_label = &COMET
		BREAK
		CASE SLAMVAN			 
			$impexp_text_label = &SLAMVAN
		BREAK
		CASE BLISTAC			 
			$impexp_text_label = &BLISTAC
		BREAK
		CASE STAFFORD			 
			$impexp_text_label = &STAFFRD
		BREAK
		CASE SABRE				 
			$impexp_text_label = &SABRE
		BREAK
		CASE FCR900				 
			$impexp_text_label = &FCR900
		BREAK
		CASE EUROS				 
			$impexp_text_label = &EUROS
		BREAK
		CASE BANDITO			 
			$impexp_text_label = &BANDITO
		BREAK
		CASE SUPERGT			 
			$impexp_text_label = &SUPERGT
		BREAK
		CASE BANSHEE			 
			$impexp_text_label = &BANSHEE
		BREAK
		CASE TURISMO			 
			$impexp_text_label = &TURISMO
		BREAK
		CASE JOURNEY			 
			$impexp_text_label = &JOURNEY
		BREAK
		CASE HUNTLEY			 
			$impexp_text_label = &HUNTLEY
		BREAK
		CASE BFINJECT			 
			$impexp_text_label = &BFINJC
		BREAK
		CASE BLADE				 
			$impexp_text_label = &BLADE
		BREAK
		CASE FREEWAY			 
			$impexp_text_label = &FREEWAY
		BREAK
		CASE MESA				 
			$impexp_text_label = &MESAA
		BREAK
		CASE ZR350				 
			$impexp_text_label = &ZR350
		BREAK
		CASE HOTKNIFE			 
			$impexp_text_label = &HOTKNIF
		BREAK
		CASE DUNERIDE			 
			$impexp_text_label = &DUNE
		BREAK
		CASE VORTEX				 
			$impexp_text_label = &VORTEX
		BREAK
		CASE BULLET				 
			$impexp_text_label = &BULLET
		BREAK
		CASE PEREN				 
			$impexp_text_label = &PEREN
		BREAK
	ENDSWITCH
								 
RETURN
										
ie_create_custom_plates:

	SWITCH current_import_car_list[impexp_selected_car]
		CASE CLUB
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &N13_LLF_
		BREAK
		CASE EUROS
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_DS3MP__
		BREAK
		CASE SUPERGT
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_CMACD1_
		BREAK
		CASE WINDSOR
			GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &__C0S___
			ENDIF
		BREAK
		CASE JESTER
			GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
			IF temp_int = 1
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_X2_GAV_
			ENDIF
			IF temp_int = 2
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &__G3PO__
			ENDIF
		BREAK
		CASE SENTINEL
			GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &D0N_D0N_
			ENDIF
			IF temp_int = 1
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_D0_NNY_
			ENDIF
			IF temp_int = 2
		   		CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &TH3_D0N_
			ENDIF
		BREAK
		CASE CAMPER
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &SJM1985
		BREAK
		CASE FELTZER
			GENERATE_RANDOM_INT_IN_RANGE 0 5 temp_int
			IF temp_int = 1
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &433_ADF_	
			ENDIF
			IF temp_int = 2
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &DR_F_MBE	
			ENDIF	
			IF temp_int = 3
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &ANN_F3RG	
			ENDIF
		BREAK
		CASE SABRE
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &__FR4Z__
		BREAK
		CASE CHEETAH
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_IMY_AK_
		BREAK
		CASE COMET
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_L0LLY__
		BREAK
		CASE INFERNUS
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_J_L33S_
		BREAK
		CASE RANCHER
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &S4_LIJON
		BREAK
		CASE BLISTAC
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &DI5CO5TU
			ELSE
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &SM53_NUV
			ENDIF
		BREAK
		CASE BANSHEE
			GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &J3NYTAL5
			ENDIF
			IF temp_int = 1
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_J3_NCF_
			ENDIF
			IF temp_int = 2
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &DD0_N4LD
			ENDIF
		BREAK
		CASE BFINJECT
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &LA5H_L3Y
		BREAK
		CASE PATRIOT
			GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &AL3X_RES
			ENDIF
			IF temp_int = 1
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &R_F3RG1E
			ENDIF
			IF temp_int = 2
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &H4_NNAHF
			ENDIF
			IF temp_int = 3
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &CL41_RES 
			ENDIF
		BREAK 
		CASE BLADE
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &R055_MCL 
		BREAK
		CASE BULLET
			GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
			IF temp_int = 0
				CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &T00_FAST 
			ENDIF
		BREAK
		DEFAULT
			GOSUB ie_random_custom_plate
		BREAK
	ENDSWITCH

RETURN

ie_random_custom_plate:

	GENERATE_RANDOM_INT_IN_RANGE 0 100 temp_int

	SWITCH temp_int
		CASE 0
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &R4N_G3RS
		BREAK
		CASE 1
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &GL4S_G0W
		BREAK
		CASE 2
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_ARRAN__
		BREAK
		CASE 3
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &AM0_RUS0
		BREAK
		CASE 4
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_AMAT0__
		BREAK
		CASE 5
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &_GA_ZZA_
		BREAK
		CASE 6
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &ZID_ANE_
		BREAK
		CASE 7
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &MC_C01ST
		BREAK
		CASE 8
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &BAW_BAG_
		BREAK
		CASE 9
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &BR0_D1E_
		BREAK
		CASE 10
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &MR_J0BBY
		BREAK
		CASE 11
			CUSTOM_PLATE_FOR_NEXT_CAR current_import_car_list[impexp_selected_car] &BR0_DICK
		BREAK
	ENDSWITCH	 

RETURN



}			


{
impexp_car_check:
SCRIPT_NAME IMPEXPC

LVAR_INT impexp_help
LVAR_INT temp_model
LVAR_INT temp_int
LVAR_INT temp_int2

impexp_car_check_loop:

	WAIT 5000

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF import_export_is_active = 1
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				AND NOT IS_MESSAGE_BEING_DISPLAYED
					IF IS_CHAR_IN_ANY_CAR scplayer
						IF impexp_help = 0
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
							GET_CAR_MODEL car temp_model
							// check if car is on wanted list
							temp_int = 0
							temp_int2 = 0
							WHILE temp_int < 10
								IF current_wanted_car_list[temp_int] = temp_model
									IF current_wanted_car_status[temp_int] = 0
										temp_int2 = 1
										temp_int = 10 
									ENDIF
								ENDIF
							temp_int++
							ENDWHILE
							IF temp_int2 = 1
								PRINT_HELP IE22
								impexp_help = 1
							ENDIF
						ENDIF
					ELSE
						IF impexp_help = 1
							impexp_help = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO impexp_car_check_loop

}							

										


