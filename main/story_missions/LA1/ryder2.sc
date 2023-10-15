MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION RYDER2 : 
//				  AUTHOR :
//			DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this				 
// *************************************************************************************************************
SCRIPT_NAME RYDER2
GOSUB mission_start_RYDER2
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_RYDER2
ENDIF
GOSUB mission_cleanup_RYDER2
MISSION_END

mission_start_RYDER2:
//WRITE_DEBUG ryder2_started
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed
LVAR_INT frame_num
LVAR_INT this_frame_time
LVAR_INT last_frame_time
LVAR_INT time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT temp_seq 	
LVAR_INT dialogue_flag dialogue_flag2

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0
dialogue_flag = 0
dialogue_flag2 = 0

mission_loop_RYDER2:

WAIT 0
// frame count
frame_num++														
IF frame_num > 9
	frame_num = 0
ENDIF

// timer
GET_GAME_TIMER this_frame_time
time_diff = this_frame_time - last_frame_time 
last_frame_time = this_frame_time
ping_box_timer += time_diff
ryder_dialogue_timer += time_diff
gate_reminder_timer += time_diff
temp_int = 0
WHILE temp_int < 9
	m_guard_timer[temp_int] += time_diff
temp_int++
ENDWHILE
	
LVAR_INT spawn_timer
LVAR_INT jeep_timer
LVAR_INT exploding_box_timer
LVAR_INT dialogue_timer
LVAR_INT boxes_outside_timer
LVAR_INT load_box_timer
LVAR_INT ryder_dialogue_timer2
spawn_timer += time_diff
jeep_timer += time_diff
exploding_box_timer += time_diff
dialogue_timer += time_diff
boxes_outside_timer += time_diff
load_box_timer += time_diff
ryder_dialogue_timer2 += time_diff

IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	m_passed = 1
ENDIF

	//GOSUB adjust_box_position

	GOSUB ryder2_debug_tools


	ryder2_loop_start:
	SWITCH m_stage

		CASE 0
			GOSUB ryder2_m_stage_0
		BREAK
		CASE 1
			GOSUB ryder2_m_stage_1
		BREAK
		CASE 2
			GOSUB ryder2_m_stage_2
		BREAK
		CASE 3
			GOSUB ryder2_m_stage_3
		BREAK
		CASE 4
			GOSUB ryder2_m_stage_4
		BREAK
		CASE 5
			GOSUB ryder2_m_stage_5
		BREAK
		CASE 6
			GOSUB ryder2_m_stage_6
		BREAK
		CASE 7
			GOSUB ryder2_m_stage_7
		BREAK

	ENDSWITCH

	GOSUB ryder2_global_functions

	IF goto_start_of_loop = 1 
		goto_start_of_loop = 0
		GOTO ryder2_loop_start
	ENDIF

// end of main loop
IF m_failed = 0
	IF m_passed = 0																 
		GOTO mission_loop_RYDER2 
	ELSE
		GOSUB mission_passed_RYDER2
		RETURN
	ENDIF
ELSE
	GOSUB mission_failed_RYDER2
	RETURN
ENDIF


// Fake create
GOTO ry2_skip_this
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ryder
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 exploding_box[0]
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 throwing_box 
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 displayed_boxes_in_van[0]
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 hide_anim_box
	CREATE_CAR PONY 0.0 0.0 0.0 patriot1
	CREATE_CAR PONY 0.0 0.0 0.0 patriot2
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 van_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 ryder_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 switch1_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 switch2_blip
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 fake_van_collision_obj
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 spawned_dude[0]
ry2_skip_this:




// *************************************************************************************************************
//		COMMONLY USED STUFF FOR EVERY SCRIPT - DON'T CHANGE
// *************************************************************************************************************

ryder2_debug_tools:

LVAR_INT debug_on	
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_X
	IF debug_on = 0
		debug_on = 1
		WRITE_DEBUG LEVEL_DEBUG_ON
		IF NOT IS_CHAR_DEAD ryder
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER ryder TRUE // TAKE OUT !!!!!!
		ENDIF
	ELSE
		debug_on = 0
		WRITE_DEBUG LEVEL_DEBUG_OFF
	ENDIF
ENDIF

IF debug_on = 1
	// display mission stage variables for debug
	LVAR_INT display_debug
	VAR_INT ry2_view_debug[8]
	VAR_FLOAT ry2_view_debug_f[8]
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_SPACE
		display_debug++
		IF display_debug > 2 
			display_debug = 0
		ENDIF
		CLEAR_ALL_VIEW_VARIABLES
	ENDIF
	IF display_debug = 1
		ry2_view_debug[0] = m_stage
		ry2_view_debug[1] = m_goals
		ry2_view_debug[2] = ryder_stage
		ry2_view_debug[3] =	warehouse_opened
		ry2_view_debug_f[4] = gate2_target_z
		ry2_view_debug[5] =	dialogue_flag
		ry2_view_debug[6] =	dialogue_flag2
		ry2_view_debug[7] =	audio_line_is_active
		VIEW_INTEGER_VARIABLE ry2_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE ry2_view_debug[1] m_goals	
		VIEW_INTEGER_VARIABLE ry2_view_debug[2] ryder_stage
		VIEW_INTEGER_VARIABLE ry2_view_debug[3]	warehouse_opened
		VIEW_FLOAT_VARIABLE ry2_view_debug_f[4] gate2_target_z
		VIEW_INTEGER_VARIABLE ry2_view_debug[5]	dialogue_flag
		VIEW_INTEGER_VARIABLE ry2_view_debug[6]	dialogue_flag2
		VIEW_INTEGER_VARIABLE ry2_view_debug[7]	audio_line_is_active
	ENDIF					 
	IF display_debug = 2
		ry2_view_debug[0] = ryder_get_box_flag
		ry2_view_debug[1] = loaded_boxes
		ry2_view_debug[2] = spawn_this_many_guards
		ry2_view_debug[3] =	TIMERA
		ry2_view_debug[4] =	spawn_timer
	//	ry2_view_debug[5] =
	//	ry2_view_debug[6] =
	//	ry2_view_debug[7] =
		VIEW_INTEGER_VARIABLE ry2_view_debug[0] ryder_get_box_flag
		VIEW_INTEGER_VARIABLE ry2_view_debug[1] loaded_boxes
		VIEW_INTEGER_VARIABLE ry2_view_debug[2] spawn_this_many_guards
		VIEW_INTEGER_VARIABLE ry2_view_debug[3]	TIMERA
		VIEW_INTEGER_VARIABLE ry2_view_debug[4]	spawn_timer
	//	VIEW_INTEGER_VARIABLE ry2_view_debug[5]
	//	VIEW_INTEGER_VARIABLE ry2_view_debug[6]
	//	VIEW_INTEGER_VARIABLE ry2_view_debug[7]
	ENDIF
ENDIF

RETURN

// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
ryder2_m_stage_0:
		
		// Specific mission variables		

		// Initialise any variables
		IF m_goals = 0
			
			// objects
			LVAR_INT switch1  
			LVAR_INT switch2
			LVAR_INT m_box[25]
			LVAR_INT back_door[6]
			LVAR_INT fake_van_collision_obj
			LVAR_INT fake_ryder_collision

			// vehicles
			LVAR_INT van
			LVAR_INT patriot1 patriot2
			LVAR_INT m_mesa
			LVAR_INT fork
			LVAR_INT jeep1 
			LVAR_INT jeep2

			// peds
			LVAR_INT m_guard[9]
			LVAR_INT m_mesa_driver
			LVAR_INT fork_driver
			LVAR_INT driver1
			LVAR_INT driver2
			LVAR_INT end_cut_ped1
			LVAR_INT end_cut_ped2
			LVAR_INT passenger1
			LVAR_INT passenger2

			// blips
			LVAR_INT van_blip
			LVAR_INT location_blip
			LVAR_INT ryder_blip	
			LVAR_INT switch1_blip
			LVAR_INT switch2_blip	
		   
			// flags
			LVAR_INT alarm_raised
			LVAR_INT player_got_uniform
			LVAR_INT player_in_complex
			LVAR_INT loaded_boxes
			LVAR_INT guards_alerted
			LVAR_INT fork_loaded fork_box1
			LVAR_INT ryder_got_driveby
			LVAR_INT warehouse_opened
			LVAR_INT gate1_opened
			LVAR_INT ryder_stage
			LVAR_INT generate_baddies
			LVAR_INT m_wave 
			LVAR_INT jeep_wave
			LVAR_INT passenger1_got_task
			LVAR_INT passenger2_got_task
		   	VAR_INT ryder_health 
		   	LVAR_INT ryder_health_displayed
			LVAR_INT ping_box_timer
			LVAR_INT ryder_dialogue_timer
			LVAR_INT ryder_dialogue_int
			LVAR_INT gate_reminder_timer
			LVAR_INT smashed_boxes
			LVAR_INT boxes_are_outside_dialogue
			LVAR_INT m_box_value[25]
			LVAR_INT m_guard_flag[9]
			LVAR_INT m_guard_timer[9]
			LVAR_INT ryder_text_van
			LVAR_INT ryder_flag
			LVAR_INT ryder_explained_what_to_do
			LVAR_INT burst_tyres_flag
			LVAR_INT conversation_on_route_back
			LVAR_INT chasers_have_bugged_off
			LVAR_INT van_collision
			LVAR_INT enable_fake_van_collision
			LVAR_INT jeep_created
			LVAR_INT spawn_difficulty
			LVAR_INT required_boxes
			LVAR_INT beep_sound_flag
			LVAR_INT told_more_boxes_outside
			LVAR_INT goto_start_of_loop



			// coords
			LVAR_FLOAT m_guard_off_x m_guard_off_y m_guard_off_z
			LVAR_FLOAT x2 y2 z2

			// pickups
			LVAR_INT m_pickup[25]

			// decision makers
			LVAR_INT ryder_dm
			LVAR_INT tough_dm
			LVAR_INT empty_dm
			LVAR_INT driveby_dm

			// groups
			LVAR_INT guard_group

			// gates for now
			LVAR_INT gate1
			LVAR_INT gate2
			LVAR_FLOAT gate1_start_x gate1_start_y gate1_start_z
			LVAR_FLOAT gate2_start_x gate2_start_y gate2_start_z
			LVAR_FLOAT gate1_target_x gate1_target_y gate1_target_z
			LVAR_FLOAT gate2_target_x gate2_target_y gate2_target_z
			LVAR_FLOAT gate3_target_x gate3_target_y gate3_target_z

			LVAR_INT gate3 gate4 gate7

			// set flags
			ryder = 0
			van = 0
			fork = 0

			alarm_raised = 0
			player_got_uniform = 0
			player_in_complex = 0
			loaded_boxes = 0
			guards_alerted = 0
			fork_loaded = 0
			fork_box1 = 0
			ryder_got_driveby = 0
			warehouse_opened = 0
			gate1_opened	= 0
			ryder_stage = 0
			generate_baddies = 0
			m_wave = 0
			jeep_wave = 0
			passenger1_got_task	= 0
			passenger2_got_task	= 0
			ryder_health = 0
			ryder_health_displayed = 0	   
			ryder_dialogue_int = 0
			//first_box_picked_up = 0
			smashed_boxes = 0
			boxes_are_outside_dialogue = 0
			jeep_created = 0
			spawn_difficulty = 0
			goto_start_of_loop = 0

			temp_int = 0
			WHILE temp_int < 9
				m_guard_flag[temp_int] = 0
				temp_int++
			ENDWHILE
			location_blip = 0
			burst_tyres_flag = 0
			van_collision = 0
			enable_fake_van_collision = 0

			show_front_box = 0
			told_more_boxes_outside = 0

			required_boxes = 6
			
			gate1_start_x =	2720.623	
			gate1_start_y =	-2405.432	
			gate1_start_z = 13.989		

			gate2_start_x =	2774.361
			gate2_start_y =	-2417.829	
			gate2_start_z =	14.675


			spawn_x1[0] = 2721.0 	 // stairs 1
			spawn_y1[0] = -2380.0 
			spawn_z1[0] = 16.3507 
			spawn_h1[0] = 173.3713 
			spawn_x2[0] = 2721.6006 
			spawn_y2[0] = -2384.6377 
			spawn_z2[0] = 16.3403  
			spawn_x3[0] = 2732.8726 
			spawn_y3[0] = -2385.6775 
			spawn_z3[0] = 12.6250  
			spawn_x4[0] = 2763.6924 
			spawn_y4[0] = -2411.2581 
			spawn_z4[0] = 12.6276   

			spawn_x1[1] = 2729.35    // stairs 2		 2729.3586 -2451.0784
			spawn_y1[1] = -2451.07 
			spawn_z1[1] = 16.6007 
			spawn_h1[1] = 247.5382 
			spawn_x2[1] = 2734.9082 
			spawn_y2[1] = -2451.3196 
			spawn_z2[1] = 16.5938  
			spawn_x3[1] = 2735.1760 
			spawn_y3[1] = -2437.9150 
			spawn_z3[1] = 12.6362  
			spawn_x4[1] = 2763.7722 
			spawn_y4[1] = -2428.2026 
			spawn_z4[1] = 12.4725 

			spawn_x1[2] = 2748.5549    // porta 1
			spawn_y1[2] = -2453.0491 
			spawn_z1[2] = 12.8623 
			spawn_h1[2] = 347.9489 
			spawn_x2[2] = 2748.5955 
			spawn_y2[2] = -2446.5869 
			spawn_z2[2] = 12.6432 
			spawn_x3[2] = 2757.5857 
			spawn_y3[2] = -2434.7612 
			spawn_z3[2] = 12.4876  
			spawn_x4[2] = 2766.1582 
			spawn_y4[2] = -2432.0142 
			spawn_z4[2] = 12.6485  

			spawn_x1[3] = 2793.5156    // dont want	  porta 2
			spawn_y1[3] = -2394.2839 
			spawn_z1[3] = 12.8623 
			spawn_h1[3] = 178.5569 
			spawn_x2[3] = 2793.4727 
			spawn_y2[3] = -2398.6621 
			spawn_z2[3] = 12.6323  
			spawn_x3[3] = 2799.5728 
			spawn_y3[3] = -2403.2915 
			spawn_z3[3] = 12.6308  
			spawn_x4[3] = 2799.2507 
			spawn_y4[3] = -2410.9849 
			spawn_z4[3] = 12.6309  

			spawn_x1[4] = 2721.0 		// stairs 1
			spawn_y1[4] = -2380.0  
			spawn_z1[4] = 16.3507 
			spawn_h1[4] = 186.1396 
			spawn_x2[4] = 2721.5022 
			spawn_y2[4] = -2383.6094 
			spawn_z2[4] = 16.3403  
			spawn_x3[4] = 2733.7444 
			spawn_y3[4] = -2386.2271 
			spawn_z3[4] = 12.6250  
			spawn_x4[4] = 2754.8855 
			spawn_y4[4] = -2409.5017 
			spawn_z4[4] = 12.5104  

			spawn_x1[5] = 2729.35    // stairs2
			spawn_y1[5] = -2451.07 
			spawn_z1[5] = 16.6007 
			spawn_h1[5] = 246.8993 
			spawn_x2[5] = 2734.4683 
			spawn_y2[5] = -2450.8242 
			spawn_z2[5] = 16.5938  
			spawn_x3[5] = 2735.5732 
			spawn_y3[5] = -2436.6675 
			spawn_z3[5] = 12.6361  
			spawn_x4[5] = 2759.3601 
			spawn_y4[5] = -2420.3557 
			spawn_z4[5] = 12.4948 
			 
			spawn_x1[6] = 2748.5513 	// porta 1
			spawn_y1[6] = -2452.8279 
			spawn_z1[6] = 12.8623 
			spawn_h1[6] = 339.2833 
			spawn_x2[6] = 2750.6704 
			spawn_y2[6] = -2442.9441 
			spawn_z2[6] = 12.6432  
			spawn_x3[6] = 2758.1475 
			spawn_y3[6] = -2440.4915 
			spawn_z3[6] = 12.4936  
			spawn_x4[6] = 2769.0291 
			spawn_y4[6] = -2430.3367 
			spawn_z4[6] = 12.6485  

			spawn_x1[7] = 2793.6384    // porta 2 dont want
			spawn_y1[7] = -2395.2266 
			spawn_z1[7] = 12.8623 
			spawn_h1[7] = 170.3273 
			spawn_x2[7] = 2793.9031 
			spawn_y2[7] = -2399.0630 
			spawn_z2[7] = 12.6322  
			spawn_x3[7] = 2799.3840 
			spawn_y3[7] = -2403.4741 
			spawn_z3[7] = 12.6309  
			spawn_x4[7] = 2799.4099 
			spawn_y4[7] = -2414.3362 
			spawn_z4[7] = 12.6309 
			 
			spawn_x1[8] = 2799.7637    // back door 2 dont want
			spawn_y1[8] = -2433.0024 
			spawn_z1[8] = 12.6308 
			spawn_h1[8] = 358.8697 
			spawn_x2[8] = 2799.9019 
			spawn_y2[8] = -2425.6943 
			spawn_z2[8] = 12.6307  
			spawn_x3[8] = 2796.6543 
			spawn_y3[8] = -2420.4629 
			spawn_z3[8] = 12.6315 
			spawn_x4[8] = 2786.0105 
			spawn_y4[8] = -2414.4451 
			spawn_z4[8] = 12.6341  

			spawn_x1[9] = 2800.0078    // back door2 dont want
			spawn_y1[9] = -2432.2847 
			spawn_z1[9] = 12.6307 
			spawn_h1[9] = 2.0796 
			spawn_x2[9] = 2800.0232 
			spawn_y2[9] = -2426.1531 
			spawn_z2[9] = 12.6307  
			spawn_x3[9] = 2790.8010 
			spawn_y3[9] = -2421.9172
			spawn_z3[9] = 12.6330 
			spawn_x4[9] = 2786.3369 
			spawn_y4[9] = -2421.4333 
			spawn_z4[9] = 12.6340 

		m_goals++				
		ENDIF

		// Load any text / textures	etc.
		IF m_goals = 1
			LOAD_MISSION_TEXT RYDER2
//			REQUEST_ANIMATION BOX
//			REQUEST_ANIMATION CARRY
//			WHILE NOT HAS_ANIMATION_LOADED BOX
//			   OR NOT HAS_ANIMATION_LOADED CARRY
//				WAIT 0
//			ENDWHILE
		m_goals++
		ENDIF

		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2

		// load decision makers
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH ryder_dm
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY driveby_dm

		// load audio bank
		LOAD_MISSION_AUDIO 3 SOUND_BANK_UNCLE_SAM
		WHILE NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE

		// Environment Settings	
		IF m_goals = 2
			//SWITCH_PED_ROADS_OFF 2694.0 -2329.3125 0.0 2811.9556 -2567.3694 20.0
			//SET_PED_DENSITY_MULTIPLIER 1.0
			//SET_CAR_DENSITY_MULTIPLIER 0.3
			//SET_TIME_OF_DAY 23 1
			SET_WANTED_MULTIPLIER 0.2
			m_goals = 99
		ENDIF

		// End of stage
		IF m_goals = 99
			m_stage++
			m_goals = 0
		ENDIF

RETURN

// *************************************************************************************************************
//							STAGE 1 - wait for player to get in van and drive to 1st locate 
// *************************************************************************************************************
ryder2_m_stage_1:
		
		// animated cut scene
		IF m_goals = 0
			
			CLEAR_AREA 2463.4751 -1713.4281 12.5025 20.0 TRUE

			LOAD_CUTSCENE RYDER2A
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			START_CUTSCENE
			DO_FADE 1000 FADE_IN
			WHILE NOT HAS_CUTSCENE_FINISHED
				WAIT 0
			ENDWHILE
			SET_PLAYER_CONTROL player1 OFF
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE   
			CLEAR_CUTSCENE
		m_goals++
		ENDIF
		
		// create van outside house
		IF m_goals = 1

			SWITCH_CAR_GENERATOR gen_car6 0 //Ryders car
			CLEAR_AREA 2472.81 -1690.97 12.53 5.0 TRUE

			REQUEST_MODEL MULE
			LOAD_SPECIAL_CHARACTER 1 RYDER2
			REQUEST_MODEL COLT45
			WHILE NOT HAS_MODEL_LOADED MULE
			   OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
			   OR NOT HAS_MODEL_LOADED COLT45
				WAIT 0
			ENDWHILE
			
			SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE
		
			SET_CAR_MODEL_COMPONENTS MULE -1 -1	   
			CREATE_CAR MULE 2475.9084 -1679.6207 12.3447 van
			SET_CAR_HEADING van 69.0573 
			REMOVE_BLIP van_blip
			ADD_BLIP_FOR_CAR van van_blip
			SET_BLIP_AS_FRIENDLY van_blip TRUE
			SET_CAN_BURST_CAR_TYRES van FALSE
			VEHICLE_DOES_PROVIDE_COVER  van FALSE	
			SUPPRESS_CAR_MODEL MULE

			// create ryder
			CREATE_CHAR_AS_PASSENGER van PEDTYPE_MISSION2 SPECIAL01 0 ryder
			SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE 
	 		GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY ryder 70
			SET_CHAR_MAX_HEALTH ryder 500 
			SET_CHAR_HEALTH ryder 500
			TASK_TOGGLE_PED_THREAT_SCANNER ryder FALSE FALSE FALSE
			SET_CHAR_NEVER_TARGETTED ryder TRUE
			SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
			SET_ANIM_GROUP_FOR_CHAR ryder gang1 
			SET_CHAR_DECISION_MAKER ryder ryder_dm
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryder_dm EVENT_VEHICLE_ON_FIRE
			//PRINT_NOW RY2_01 8000 1 // Ryder - 'I got my hands on this funky van which should do the trick, get in.'
			
			TASK_LOOK_AT_CHAR ryder scplayer 999999

			PRINT_NOW RY2_03 7000 1 
			dialogue_flag = 0

			SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
			SET_ANIM_GROUP_FOR_CHAR ryder MAN
			
			SET_CHAR_COORDINATES scplayer 2474.8699 -1698.4652 12.5210  
			SET_CHAR_HEADING scplayer 4.8941
			SET_CAMERA_BEHIND_PLAYER 
			RESTORE_CAMERA_JUMPCUT

			DO_FADE 0 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
							


			SET_PLAYER_CONTROL player1 ON
			TIMERA = 0

			// create gates
			CREATE_OBJECT_NO_OFFSET KMB_FRONTGATE	 gate1_start_x gate1_start_y gate1_start_z gate1
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS gate1 0.0 10.0 0.0 gate1_target_x gate1_target_y gate1_target_z
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR2B gate2_start_x gate2_start_y gate2_start_z gate2
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS gate2 0.0 0.0 4.0 gate2_target_x gate2_target_y gate2_target_z

			// create other gates
			CREATE_OBJECT_NO_OFFSET KMB_FRONTGATE 	 2720.623 -2504.023 13.989 gate3
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS gate3 0.0 10.0 0.0 gate3_target_x gate3_target_y gate3_target_z
			CREATE_OBJECT_NO_OFFSET WAREHOUSE_DOOR2B 2774.361 -2493.922 14.675 gate4	 // far
 			CREATE_OBJECT_NO_OFFSET WAREHOUSE_DOOR2B 2774.361 -2455.925 14.675 gate7	 // near

			// create back doors
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2405.3943 14.58 back_door[0]
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2430.2153 14.58 back_door[1]
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2443.4866 14.58 back_door[2]
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2468.2542 14.58 back_door[3]
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2481.7112 14.58 back_door[4]
			CREATE_OBJECT_NO_OFFSET	WAREHOUSE_DOOR1	2799.75 -2506.3135 14.58 back_door[5]
			temp_int = 0
			WHILE temp_int < 6
				FREEZE_OBJECT_POSITION back_door[temp_int] TRUE
			temp_int++
			ENDWHILE

			// create switches
			CREATE_OBJECT SEC_KEYPAD  2720.4749 -2411.8562 13.8768 switch1
			SET_OBJECT_HEADING switch1 90.0
			SET_OBJECT_HEALTH switch1 10
			FREEZE_OBJECT_POSITION switch1 TRUE
			SET_OBJECT_COLLISION switch1 OFF
							 
			CREATE_OBJECT SEC_KEYPAD  2774.1479 -2423.2297 14.2684 switch2
			SET_OBJECT_HEADING switch2 270.0
			SET_OBJECT_HEALTH switch2 10
			FREEZE_OBJECT_POSITION switch2 TRUE
			SET_OBJECT_COLLISION switch2 OFF

			SET_RADIO_CHANNEL RS_MODERN_HIP_HOP

			dialogue_flag = 0
			TIMERA = 0
			m_goals++
		ENDIF	
																	 
		// wait for player to get into van							 
		IF m_goals = 2

			IF audio_line_is_active = 0
				IF NOT IS_CAR_DEAD van
					IF IS_CHAR_IN_CAR scplayer van
						REMOVE_BLIP location_Blip
						ADD_BLIP_FOR_COORD 2699.2932 -2395.0129 12.6172 location_blip
						PRINT_NOW RY2_02 7000 1 //Drive down to the compound at Ocean Docks.
						dialogue_timer = 0
						m_goals++
						dialogue_flag = 0
						dialogue_flag2 = 0

						IF NOT IS_CHAR_DEAD ryder
							CLEAR_LOOK_AT ryder 
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF

		// wait for player to arrive at docks
		IF m_goals = 3
			
			// cheat to warp player there
			IF debug_on = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					SET_CHAR_COORDINATES scplayer 2699.2932 -2395.0129 12.6172
				ENDIF
			ENDIF
						 
			IF NOT IS_CAR_DEAD van
				IF DOES_BLIP_EXIST location_blip
					IF LOCATE_CAR_3D van 2699.2932 -2395.0129 12.6172 4.0 4.0 4.0 TRUE
						IF IS_CHAR_IN_CAR scplayer van
							IF IS_VEHICLE_ON_ALL_WHEELS van
								STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
								TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
								SET_PLAYER_CONTROL player1 OFF

								REMOVE_BLIP location_blip
								m_goals = 99
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF	

			// conversation 
			IF dialogue_timer > 7500
				IF NOT IS_CHAR_DEAD ryder
					IF IS_CHAR_IN_ANY_CAR ryder
						STORE_CAR_CHAR_IS_IN ryder car
						IF NOT IS_CAR_DEAD car
							IF IS_CHAR_IN_CAR scplayer car
								IF TIMERB > 1000
									IF audio_line_is_active = 0
										SWITCH dialogue_flag2
											CASE 0
												$audio_string    = &RYD2_AA					  
												audio_sound_file = SOUND_RYD2_AA
												START_NEW_SCRIPT audio_line ryder 0 0 2 0
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 1
												LOAD_MISSION_AUDIO 1 SOUND_RYD2_CA
											BREAK
											CASE 1
												$audio_string    = &RYD2_CA					  
												audio_sound_file = SOUND_RYD2_CA
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line scplayer 0 0 1 1
												ELSE
													START_NEW_SCRIPT audio_line scplayer 0 0 1 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 2 
												LOAD_MISSION_AUDIO 2 SOUND_RYD2_CB
											BREAK
											CASE 2
												$audio_string    = &RYD2_CB				  
												audio_sound_file = SOUND_RYD2_CB
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line ryder 0 0 2 1
												ELSE
													START_NEW_SCRIPT audio_line ryder 0 0 2 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 1 
												LOAD_MISSION_AUDIO 1 SOUND_RYD2_CC
											BREAK
											CASE 3
												$audio_string    = &RYD2_CC					  
												audio_sound_file = SOUND_RYD2_CC
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line scplayer 0 0 1 1
												ELSE
													START_NEW_SCRIPT audio_line scplayer 0 0 1 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 2 
												LOAD_MISSION_AUDIO 2 SOUND_RYD2_CD
											BREAK
											CASE 4
												$audio_string    = &RYD2_CD					  
												audio_sound_file = SOUND_RYD2_CD
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line ryder 0 0 2 1
												ELSE
													START_NEW_SCRIPT audio_line ryder 0 0 2 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 1 
												LOAD_MISSION_AUDIO 1 SOUND_RYD2_CE
											BREAK
											CASE 5
												$audio_string    = &RYD2_CE					  
												audio_sound_file = SOUND_RYD2_CE
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line ryder 0 0 1 1
												ELSE
													START_NEW_SCRIPT audio_line ryder 0 0 1 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 2 
												LOAD_MISSION_AUDIO 2 SOUND_RYD2_CF
											BREAK
											CASE 6
												$audio_string    = &RYD2_CF					  
												audio_sound_file = SOUND_RYD2_CF
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line scplayer 0 0 2 1
												ELSE
													START_NEW_SCRIPT audio_line scplayer 0 0 2 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 1 
												LOAD_MISSION_AUDIO 1 SOUND_RYD2_CG
											BREAK
											CASE 7
												$audio_string    = &RYD2_CG					  
												audio_sound_file = SOUND_RYD2_CG
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line scplayer 0 0 1 1
												ELSE
													START_NEW_SCRIPT audio_line scplayer 0 0 1 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
												CLEAR_MISSION_AUDIO 2 
												LOAD_MISSION_AUDIO 2 SOUND_RYD2_CH
											BREAK
											CASE 8
												$audio_string    = &RYD2_CH					  
												audio_sound_file = SOUND_RYD2_CH
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line ryder 0 0 2 1
												ELSE
													START_NEW_SCRIPT audio_line ryder 0 0 2 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
	  											CLEAR_MISSION_AUDIO 1 
												LOAD_MISSION_AUDIO 1 SOUND_RYD2_CJ
											BREAK
											CASE 9
												$audio_string    = &RYD2_CJ				  
												audio_sound_file = SOUND_RYD2_CJ
												IF dialogue_flag = 0
													START_NEW_SCRIPT audio_line scplayer 0 0 1 1
												ELSE
													START_NEW_SCRIPT audio_line scplayer 0 0 1 0
													dialogue_flag = 0
												ENDIF
												dialogue_flag2++
												TIMERB = 0
											BREAK
										ENDSWITCH
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF	
				ENDIF
			ENDIF

		ENDIF
	
		// exit
		IF m_goals = 99
			m_goals = 0
			m_stage++
		ENDIF

		// FUNCTIONS FOR STAGE -------
		IF NOT IS_CAR_DEAD van
			IF NOT IS_CHAR_IN_CAR scplayer van
				IF NOT DOES_BLIP_EXIST van_blip
					REMOVE_BLIP van_blip
					ADD_BLIP_FOR_CAR van van_blip
					SET_BLIP_AS_FRIENDLY van_blip TRUE
					REMOVE_BLIP location_blip
					IF NOT IS_CHAR_DEAD ryder
						TASK_LOOK_AT_CHAR ryder scplayer 999999
					ENDIF
					TIMERA = 20001
				ENDIF
				IF audio_line_is_active = 0
					IF TIMERA > 60000

						PRINT_NOW RY2_03 7000 1 // get in the van with ryder
						dialogue_flag = 0
						TIMERA = 0

					ENDIF
					IF m_goals > 2
						IF TIMERA > 5000
							IF dialogue_flag = 0 
								IF NOT IS_CHAR_DEAD ryder
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryder 15.0 15.0 15.0 FALSE
										GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
										SWITCH temp_int
											CASE 0
												$audio_string    = &RYD2_BA					  
												audio_sound_file = SOUND_RYD2_BA	
											BREAK
											CASE 1
												$audio_string    = &RYD2_BB					  
												audio_sound_file = SOUND_RYD2_BB
											BREAK
											CASE 2
												$audio_string    = &RYD2_BC					  
												audio_sound_file = SOUND_RYD2_BC
											BREAK
										ENDSWITCH	
										START_NEW_SCRIPT audio_line ryder 0 1 1 0
										dialogue_flag++
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF DOES_BLIP_EXIST van_blip
					REMOVE_BLIP van_blip
					IF NOT DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_Blip
						ADD_BLIP_FOR_COORD 2699.2932 -2395.0129 12.6172 location_blip
						IF NOT IS_CHAR_DEAD ryder
							CLEAR_LOOK_AT ryder
						ENDIF
					ENDIF 
				ENDIF
			ENDIF
		ENDIF


RETURN 


// *************************************************************************************************************
//								STAGE 2 - scripted cutscene at entrance to compound  
// *************************************************************************************************************
ryder2_m_stage_2:
		
		// initialisation for stage
		IF m_goals = 0
			CLEAR_PRINTS
			SET_PLAYER_CONTROL player1 OFF
//			SET_FADING_COLOUR 0 0 0
//			DO_FADE 500 FADE_OUT
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE
//			SWITCH_WIDESCREEN ON
//			SET_NEAR_CLIP 0.01

			dialogue_flag = -1
				 
		m_goals++
		ENDIF

		// wait for car to stop
		IF m_goals = 1
			IF NOT IS_CAR_DEAD van
				IF IS_CAR_STOPPED van
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// load scene
		IF m_goals = 2

			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			// set player van position
			IF NOT IS_CAR_DEAD van 
				SET_CAR_COORDINATES van 2699.6230 -2394.7869 12.6172 
				SET_CAR_HEADING van 267.9095
			ENDIF

			// clear area around gate
			CLEAR_AREA 2720.6111 -2405.3799 12.4609	30.0 TRUE
			// clear area around warehouse door
		   	CLEAR_AREA 2768.7400 -2413.7883 12.6172	30.0 TRUE

			SWITCH_WIDESCREEN ON
			//SET_NEAR_CLIP 0.01  
			SET_FIXED_CAMERA_POSITION 2688.5696 -2394.1858 17.6992 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT     2689.4553 -2394.5994 17.4888 JUMP_CUT

			REQUEST_MODEL CJ_CARDBRD_PICKUP
			REQUEST_MODEL ARMY
			REQUEST_MODEL COLT45
			REQUEST_MODEL FORKLIFT
			LOAD_ALL_MODELS_NOW
			WHILE NOT HAS_MODEL_LOADED CJ_CARDBRD_PICKUP
			   OR NOT HAS_MODEL_LOADED ARMY
			   OR NOT HAS_MODEL_LOADED COLT45
			   OR NOT HAS_MODEL_LOADED FORKLIFT							   
				WAIT 0
			ENDWHILE									
														
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2796.8752 -2417.1418 13.23  m_box[4] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2798.0090 -2417.1072 13.23  m_box[5] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2796.8999 -2418.3098 13.23  m_box[6] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2798.0134 -2418.3247 13.23  m_box[7] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2799.8865 -2381.2029 13.23  m_box[11] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2799.9575 -2379.7205 13.23  m_box[12] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2763.5308 -2401.9348 13.23  m_box[13] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2802.7502 -2449.8354 13.23  m_box[14] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2802.9148 -2448.5754 13.23  m_box[15] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2802.9062 -2446.8267 13.23  m_box[16] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2788.9319 -2431.2712 13.23  m_box[17] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2790.3984 -2431.3357 13.23  m_box[18] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2778.6975 -2442.4333 13.23  m_box[19] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2763.5647 -2400.6414 13.23  m_box[20] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2780.8511 -2392.7373 13.23  m_box[21] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2795.0940 -2469.4895 13.23  m_box[22] 
			CREATE_OBJECT_NO_OFFSET CJ_CARDBRD_PICKUP  2803.0669 -2428.3799 13.23  m_box[23] 

			SET_OBJECT_HEADING m_box[4]   91.9411
			SET_OBJECT_HEADING m_box[5]   0.0320 
			SET_OBJECT_HEADING m_box[6]   89.8612
			SET_OBJECT_HEADING m_box[7]   1.6342 
			SET_OBJECT_HEADING m_box[11]  6.8174 	
			SET_OBJECT_HEADING m_box[12]  19.1807 	
			SET_OBJECT_HEADING m_box[13]  339.1130 
			SET_OBJECT_HEADING m_box[14]  352.3154 
			SET_OBJECT_HEADING m_box[15]  2.8386 	
			SET_OBJECT_HEADING m_box[16]  3.2099 	
			SET_OBJECT_HEADING m_box[17]  266.6248 
			SET_OBJECT_HEADING m_box[18]  4.6663 	
			SET_OBJECT_HEADING m_box[19]  175.8277 
			SET_OBJECT_HEADING m_box[20]  344.0064 
			SET_OBJECT_HEADING m_box[21]  355.9493 
			SET_OBJECT_HEADING m_box[22]  1.9283 	
			SET_OBJECT_HEADING m_box[23]  358.0803 

			temp_int = 0
			WHILE temp_int < 25
			WAIT 0
				IF DOES_OBJECT_EXIST m_box[temp_int]
					SET_OBJECT_DYNAMIC m_box[temp_int] TRUE 
					//FREEZE_OBJECT_POSITION m_box[temp_int] TRUE 
					GENERATE_RANDOM_INT_IN_RANGE 1 8 temp_int2
					SET_OBJECT_MASS	 m_box[temp_int] 1000.0
					SET_OBJECT_TURN_MASS m_box[temp_int] 1000.0
					m_box_value[temp_int]	= temp_int2
				ENDIF
			temp_int++
			ENDWHILE

			// guards around warehouse
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 2773.7222 -2423.3884 12.6311 m_guard[0]			
			SET_CHAR_HEADING m_guard[0] 68.8849 
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS m_guard[0] 0.0 1.5 0.0 m_guard_off_x m_guard_off_y m_guard_off_z
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 2721.9438 -2411.5654 12.6198 m_guard[1]
			SET_CHAR_HEADING m_guard[1] 0.0 
											  
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 2789.5242 -2416.3723 12.6333 m_guard[2]	// inside warehouse
			SET_CHAR_HEADING m_guard[2] 202.4407
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 2790.2310 -2418.8372 12.6331 m_guard[3]	// inside warehouse
			SET_CHAR_HEADING m_guard[3] 23.3411  
			CREATE_CHAR PEDTYPE_MISSION1 ARMY 2774.9111 -2404.3035 12.6311 m_guard[4]
			SET_CHAR_HEADING m_guard[4] 77.8711
			
			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL guard_group
			
			temp_int = 0					   
			WHILE temp_int < 5
				GIVE_WEAPON_TO_CHAR m_guard[temp_int] WEAPONTYPE_PISTOL 99999
				SET_CHAR_ACCURACY m_guard[temp_int] 35
				SET_CHAR_HEALTH m_guard[temp_int] 100 
				TASK_TOGGLE_PED_THREAT_SCANNER m_guard[temp_int] FALSE FALSE FALSE
				SET_CHAR_DECISION_MAKER m_guard[temp_int] tough_dm
				SET_SENSE_RANGE m_guard[temp_int] 100.0
				IF temp_int = 0
					SET_GROUP_LEADER guard_group m_guard[0]
				ELSE
					IF NOT temp_int = 2
					AND NOT temp_int = 3
						SET_GROUP_MEMBER guard_group m_guard[temp_int] 
					ENDIF
				ENDIF
				SET_CHAR_NEVER_LEAVES_GROUP m_guard[temp_int] TRUE
			temp_int++
			ENDWHILE

			IF NOT IS_CHAR_DEAD m_guard[2]
				SET_CHAR_DECISION_MAKER m_guard[2] empty_dm
			ENDIF
			IF NOT IS_CHAR_DEAD m_guard[3]
				SET_CHAR_DECISION_MAKER m_guard[3] empty_dm
			ENDIF

			// create forklift
			CREATE_CAR FORKLIFT 2791.426 -2418.577 12.66 fork
			SET_CAR_HEADING fork 90.0
			MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
			SET_CAR_PROOFS fork TRUE FALSE FALSE FALSE FALSE 
			SET_CAR_COORDINATES fork 2758.2412 -2372.3140 12.6172 
			SET_CAR_HEADING fork 180.3774 
			SET_CAN_BURST_CAR_TYRES fork FALSE
			SET_CAR_HEALTH fork 3000
			CREATE_CHAR_INSIDE_CAR fork PEDTYPE_MISSION1 ARMY fork_driver
			
			// create mesa
			REQUEST_MODEL MESA 
			WHILE NOT HAS_MODEL_LOADED MESA
				WAIT 0
			ENDWHILE
			CREATE_CAR MESA 2688.9626 -2407.6763 12.4609 m_mesa
			SET_CAR_HEADING m_mesa 270.0
			CREATE_CHAR_INSIDE_CAR m_mesa PEDTYPE_MISSION1 ARMY m_mesa_driver
			CAR_GOTO_COORDINATES_ACCURATE m_mesa 2712.8535 -2407.8250 12.4684
			MARK_MODEL_AS_NO_LONGER_NEEDED MESA
			CHANGE_CAR_COLOUR m_mesa 37 37 // army green

			
			IF NOT IS_CHAR_DEAD m_guard[4]
				FLUSH_ROUTE
				EXTEND_ROUTE 2776.5305 -2401.0420 12.6250
				EXTEND_ROUTE 2798.9333 -2400.3425 12.6310
				TASK_FOLLOW_POINT_ROUTE m_guard[4] PEDMOVE_WALK FOLLOW_ROUTE_LOOP
			ENDIF

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			//PRINT RY2_04 3000 1 // RYDER - 'This is the place.'

			TIMERA = 0

		m_goals++
		ENDIF
		
		// switch camera mode
		IF m_goals = 3
			IF audio_line_is_active = 0
				IF NOT IS_CAR_DEAD m_mesa
					IF LOCATE_CAR_3D m_mesa 2712.8535 -2407.8250 12.4684 15.0 15.0 15.0 FALSE
						// shot of gate 		  
						//SET_FIXED_CAMERA_POSITION 2700.4141 -2394.4092 14.6699 0.0 0.0 0.0
						//POINT_CAMERA_AT_POINT 2701.0142 -2395.1892 14.4928 JUMP_CUT
						
						SET_FIXED_CAMERA_POSITION 2700.6265 -2392.2102 14.9203 0.0 0.0 0.0 
						POINT_CAMERA_AT_POINT     2701.0923 -2393.0881 14.8109 JUMP_CUT

						// start off forklift
						IF NOT IS_CAR_DEAD fork
							CAR_GOTO_COORDINATES_ACCURATE fork 2768.7400 -2413.7883 12.6172	
							SET_CAR_STRAIGHT_LINE_DISTANCE fork 200
						ENDIF
						IF NOT IS_CHAR_DEAD ryder
							IF NOT IS_CAR_DEAD m_mesa
								TASK_LOOK_AT_VEHICLE ryder m_mesa 5000 
								TASK_LOOK_AT_VEHICLE scplayer m_mesa 6000
							ENDIF
						ENDIF
						
						TIMERA = 0
						dialogue_flag++
						m_goals++
					ENDIF 
				ENDIF
			ENDIF
		ENDIF

		// wait for mesa to stop
		IF m_goals = 4
			IF NOT IS_CAR_DEAD m_mesa			
				IF LOCATE_STOPPED_CAR_3D m_mesa 2712.8535 -2407.8250 12.4684 4.0 4.0 4.0 FALSE
					// open gate - should happen automatically	 
					m_goals++
					IF DOES_OBJECT_EXIST gate1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate1 SOUND_HEAVY_GATE_START
					ENDIF
					TIMERA = 0		
				ENDIF
			ENDIF
		ENDIF
		
		// open gate
		IF m_goals = 5
			IF DOES_OBJECT_EXIST gate1
				IF SLIDE_OBJECT gate1 gate1_target_x gate1_target_y gate1_target_z 0.0 0.2 0.0 FALSE	
			   		m_goals++
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate1 SOUND_HEAVY_GATE_STOP
				ENDIF
			ENDIF
		ENDIF

		// start mesa after a couple of tics
		IF m_goals = 6
			IF NOT IS_CAR_DEAD m_mesa
				SET_FIXED_CAMERA_POSITION 2723.0945 -2413.2454 14.2659 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 2722.5476 -2412.4136 14.1704 JUMP_CUT
				CAR_GOTO_COORDINATES m_mesa 2758.4919 -2454.5100 12.4766
			
				IF NOT IS_CHAR_DEAD m_guard[0]
					SET_CHAR_HEADING m_guard[0] 275.0
				ENDIF
				IF NOT IS_CHAR_DEAD m_guard[1]
					TASK_LOOK_AT_VEHICLE m_guard[1] m_mesa 999999 
				ENDIF	

				m_goals++
				TIMERA = 0
			ENDIF
		ENDIF
		
		// change camera view
		IF m_goals = 7
			IF TIMERA >	2500
				SET_FIXED_CAMERA_POSITION 2770.9795 -2426.9326 13.3413 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2771.1853 -2425.9773 13.5536 JUMP_CUT
				TIMERA = 0
				beep_sound_flag = 0
				m_goals++
			ENDIF
		ENDIF

		// wait for forklift to hit mark
		IF m_goals = 8
			IF NOT IS_CAR_DEAD fork
				IF LOCATE_STOPPED_CAR_3D fork 2768.7400 -2413.7883 12.6172 4.0 4.0 4.0 FALSE
					IF NOT IS_CHAR_DEAD m_guard[0]
						OPEN_SEQUENCE_TASK temp_seq	
							TASK_TURN_CHAR_TO_FACE_COORD -1 2774.2666 -2423.2197 14.3429
							//TASK_USE_ATM -1
							TASK_PLAY_ANIM -1 atm ped 4.0 FALSE FALSE FALSE FALSE 1000
							TASK_GO_STRAIGHT_TO_COORD -1 2773.7222 -2423.3884 12.6311 PEDMOVE_WALK 5000
							TASK_ACHIEVE_HEADING -1 90.0
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK m_guard[0] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
						TIMERA = 0
						m_goals++
					ENDIF
				ENDIF
			ENDIF
			IF TIMERA > 10000
				IF NOT IS_CAR_DEAD fork
					SET_CAR_COORDINATES fork 2768.7400 -2413.7883 12.6172
					SET_CAR_HEADING fork 225.6142
				ENDIF
			ENDIF 
		ENDIF

		// wait for ped to finish anim
		IF m_goals = 9
			IF NOT IS_CHAR_DEAD m_guard[0]
				IF IS_CHAR_PLAYING_ANIM m_guard[0] atm
					IF beep_sound_flag = 0
						TIMERB = 0
						beep_sound_flag++
					ENDIF
					IF beep_sound_flag = 1
						IF TIMERB > 500
							IF DOES_OBJECT_EXIST switch2
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT switch2 SOUND_KEYPAD_BEEP
								beep_sound_flag++
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				GET_SCRIPT_TASK_STATUS m_guard[0] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
				OR TIMERA > 4000
					// open door
					TIMERA = 0
					IF DOES_OBJECT_EXIST gate2
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_START
					ENDIF
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// wait for door to open
		IF m_goals = 10
			IF DOES_OBJECT_EXIST gate2
				IF SLIDE_OBJECT gate2 gate2_target_x gate2_target_y gate2_target_z 0.0 0.0 0.1 FALSE
					IF NOT IS_CAR_DEAD fork
						CAR_GOTO_COORDINATES fork 2785.3538 -2416.7444 12.6187
						TIMERA = 0
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_STOP
						m_goals++
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// wait a couple of secs, then close door
		IF m_goals = 11
			IF TIMERA > 4000
				// close door
				IF DOES_OBJECT_EXIST gate2
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_START
				ENDIF
				m_goals++
			ENDIF
		ENDIF

		// wait for door to close
		IF m_goals = 12
			IF DOES_OBJECT_EXIST gate2
				IF SLIDE_OBJECT gate2 gate2_start_x gate2_start_y gate2_start_z 0.0 0.0 0.2 FALSE
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_STOP
					m_goals++	
				ENDIF
			ENDIF
		ENDIF

		IF m_goals = 13
			IF TIMERA > 2000
			AND audio_line_is_active = 0
				m_goals = 99
			ENDIF
		ENDIF


		// dialogue
		IF TIMERB > 1000
			IF audio_line_is_active = 0
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &RYD2_DA					  
						audio_sound_file = SOUND_RYD2_DA
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
	  					CLEAR_MISSION_AUDIO 2 
						LOAD_MISSION_AUDIO 2 SOUND_RYD2_DB							
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 1
						// slight pause
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 2
						$audio_string    = &RYD2_DB					  
						audio_sound_file = SOUND_RYD2_DB
						START_NEW_SCRIPT audio_line scplayer 0 1 2 1
	  					CLEAR_MISSION_AUDIO 1 
						LOAD_MISSION_AUDIO 1 SOUND_RYD2_DC							
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 3
						$audio_string    = &RYD2_DC					  
						audio_sound_file = SOUND_RYD2_DC
						START_NEW_SCRIPT audio_line scplayer 0 1 1 1
	  					CLEAR_MISSION_AUDIO 2 
						LOAD_MISSION_AUDIO 2 SOUND_RYD2_DD							
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 4
						$audio_string    = &RYD2_DD					  
						audio_sound_file = SOUND_RYD2_DD
						START_NEW_SCRIPT audio_line ryder 0 1 2 1
	  					CLEAR_MISSION_AUDIO 1 
						LOAD_MISSION_AUDIO 1 SOUND_RYD2_DE							
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 5
						$audio_string    = &RYD2_DE					  
						audio_sound_file = SOUND_RYD2_DE
						START_NEW_SCRIPT audio_line ryder 0 1 1 1
	  					CLEAR_MISSION_AUDIO 2 
						LOAD_MISSION_AUDIO 2 SOUND_RYD2_DF							
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 6
						// slight pause
						dialogue_flag++
						TIMERB = 0
					BREAK
					CASE 7
						$audio_string    = &RYD2_DF					  
						audio_sound_file = SOUND_RYD2_DF
						START_NEW_SCRIPT audio_line ryder 0 1 2 1						
						dialogue_flag++
						TIMERB = 0
					BREAK
				ENDSWITCH
			ENDIF
		ENDIF


		// skip cutscene
		IF m_goals > 2
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				m_goals = 99
			ENDIF
		ENDIF
		
		// finish cutscene
		IF m_goals = 99

			DO_FADE 250 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0 
			ENDWHILE

			// stop bug about loop gate opening sounds
			IF DOES_OBJECT_EXIST gate1
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate1 SOUND_HEAVY_GATE_STOP
			ENDIF
			IF DOES_OBJECT_EXIST gate2
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_STOP
			ENDIF


			CLEAR_PRINTS

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			IF NOT IS_CAR_DEAD van
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 2700.7578 -2392.4504 12.6172 
				SET_CHAR_HEADING scplayer 236.0759
				IF NOT IS_CHAR_DEAD ryder
					CLEAR_CHAR_TASKS_IMMEDIATELY ryder
					WARP_CHAR_INTO_CAR ryder van
				ENDIF
			ENDIF
			//WAIT 100



			// set forklift back to original position
			IF NOT IS_CAR_DEAD fork				  
				SET_CAR_COORDINATES fork 2791.426 -2418.3098 13.66 
				SET_CAR_HEADING fork 270.0
			ENDIF
			IF NOT IS_CHAR_DEAD fork_driver
				DELETE_CHAR fork_driver
			ENDIF

			// remove  mesa truck 
			IF NOT IS_CAR_DEAD m_mesa
				SET_CAR_COORDINATES m_mesa 2748.0549 -2394.4268 12.6419 
				SET_CAR_HEADING m_mesa 174.3167
			ENDIF
			IF NOT IS_CHAR_DEAD m_mesa_driver
				DELETE_CHAR m_mesa_driver
			ENDIF
					
			// set gates back
			SET_OBJECT_COORDINATES gate1 gate1_start_x gate1_start_y gate1_start_z
			SET_OBJECT_COORDINATES gate2 gate2_start_x gate2_start_y gate2_start_z

						
			// unfreeze any boxes
			temp_int = 0
			WHILE temp_int < 25
			//WAIT 0
				IF DOES_OBJECT_EXIST m_box[temp_int]
					FREEZE_OBJECT_POSITION m_box[temp_int] FALSE
					SET_OBJECT_VELOCITY m_box[temp_int] 0.0 0.0 0.0
					SET_OBJECT_DYNAMIC m_box[temp_int] FALSE
					SET_OBJECT_COLLISION m_box[temp_int] TRUE 
				ENDIF
			temp_int++
			ENDWHILE 

			IF NOT IS_CAR_DEAD van
				POP_CAR_DOOR van BOOT FALSE
			ENDIF
			IF NOT IS_CHAR_DEAD m_guard[1]
				CLEAR_LOOK_AT m_guard[1]
			ENDIF

			SWITCH_EMERGENCY_SERVICES OFF


			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			DO_FADE 250 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0 
			ENDWHILE


			SET_PLAYER_CONTROL player1 ON



			m_goals = 0
			m_stage++
		ENDIF
					   
		// FUNCTIONS GLOBAL FOR STAGE -------

RETURN 

// *************************************************************************************************************
//						STAGE 3 - WAIT FOR PLAYER TO OPEN FRONT GATE  
// *************************************************************************************************************
ryder2_m_stage_3:
		
		IF m_goals = 0

			IF DOES_OBJECT_EXIST switch1
				SET_OBJECT_COLLISION switch1 TRUE
				MAKE_OBJECT_TARGETTABLE switch1 FALSE
				FREEZE_OBJECT_POSITION switch1 TRUE
			ENDIF
			IF DOES_OBJECT_EXIST switch2
				SET_OBJECT_COLLISION switch2 TRUE
				MAKE_OBJECT_TARGETTABLE switch2 FALSE
				FREEZE_OBJECT_POSITION switch2 TRUE
			ENDIF

			PRINT RY2_59 7000 1 //Open the front gate by shooting the ~r~switch~s~.

			REMOVE_BLIP switch1_blip
			ADD_BLIP_FOR_COORD 2722.0752 -2411.2715 12.6198 switch1_blip
			CHANGE_BLIP_COLOUR switch1_blip RED
			IF NOT IS_CAR_DEAD van
				FREEZE_CAR_POSITION van TRUE
				LOCK_CAR_DOORS van CARLOCK_LOCKOUT_PLAYER_ONLY
			ENDIF
			ryder_flag = 0

			IF NOT IS_CHAR_DEAD ryder
				TASK_LOOK_AT_CHAR ryder scplayer 99999
			ENDIF
			ryder_dialogue_timer = 0
			m_goals++	
			
		ENDIF		

		// wait for gate to open
		IF m_goals = 1
			IF gate1_opened = 2
				IF NOT IS_CAR_DEAD van
					REMOVE_BLIP switch1_blip
					REQUEST_CAR_RECORDING 523
					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 523
						WAIT 0
					ENDWHILE
					IF NOT IS_CAR_DEAD van
						START_PLAYBACK_RECORDED_CAR van 523
					ENDIF
					IF NOT IS_CHAR_DEAD ryder
						CLEAR_LOOK_AT ryder
					ENDIF
					$audio_string    = &RYD2_GA					  
					audio_sound_file = SOUND_RYD2_GA
					START_NEW_SCRIPT audio_line ryder 0 1 1 0
					WAIT 100
					m_goals = 99
				ENDIF
			ELSE
				// if player is near van and presses triangle to try and get in
				IF NOT IS_CAR_DEAD van
					IF LOCATE_CHAR_ON_FOOT_CAR_3D scplayer van 5.0 5.0 3.0 FALSE
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								
							IF ryder_text_van = 0					
									IF audio_line_is_active = 0
										$audio_string    = &RYD2_EA					  
										audio_sound_file = SOUND_RYD2_EA
										START_NEW_SCRIPT audio_line ryder 0 1 1 0
										ryder_dialogue_timer = 0						
									ENDIF
								ryder_text_van++
							ELSE
								IF ryder_text_van = 1
									IF audio_line_is_active = 0
										$audio_string    = &RYD2_EB					  
										audio_sound_file = SOUND_RYD2_EB
										START_NEW_SCRIPT audio_line ryder 0 1 1 0	
										ryder_dialogue_timer = 0
									ENDIF
								ryder_text_van = 0
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF
				// ryder shouts at player if he's taking too long
				IF NOT IS_CHAR_DEAD ryder
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 20.0 20.0 20.0 FALSE
						IF ryder_dialogue_timer > 40000
							IF audio_line_is_active = 0
								$audio_string    = &RYD2_FA					  
								audio_sound_file = SOUND_RYD2_FA
								START_NEW_SCRIPT audio_line ryder 0 1 1 0	
								ryder_dialogue_timer = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
		
		// if warehouse door has been opened, but gate hasn't spawn new guards if player is inside the warehouse
		IF NOT m_goals = 99
			IF frame_num = 9
				IF NOT warehouse_opened = 0
				AND gate1_opened = 0
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2785.89 -2417.98 14.36 20.0 20.0 5.0 FALSE
						//GOSUB spawn_new_guards
						IF spawn_timer > 30000
							spawn_this_many_guards = 3
							GOSUB process_spawning_guards
							spawn_timer = 0
						ENDIF
					ENDIF
				ELSE
					IF gate1_opened = 0
					AND warehouse_opened = 0
						IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2785.89 -2417.98 14.36 20.0 20.0 5.0 FALSE
							//GOSUB spawn_new_guards
							IF spawn_timer > 30000
								spawn_this_many_guards = 3
								GOSUB process_spawning_guards
								spawn_timer = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		

		IF m_goals = 99
			m_stage++
			m_goals = 0
			ryder_flag = 0
			spawn_this_many_guards = 0
		ENDIF

RETURN	

// *************************************************************************************************************
//						STAGE 4 - WAIT FOR PLAYER TO OPEN WAREHOUSE DOOR  
// *************************************************************************************************************
ryder2_m_stage_4:

		// wait for door to open
		IF m_goals = 0
			IF NOT IS_MESSAGE_BEING_DISPLAYED 
			AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				IF audio_line_is_active = 0
					IF warehouse_opened = 0 
						//REMOVE_BLIP location_blip
						ADD_BLIP_FOR_COORD 2773.1008 -2423.4185 12.6269 switch2_blip
						CHANGE_BLIP_COLOUR switch2_blip RED
						PRINT RY2_60 7000 1 //Open the warehouse door by shooting the ~r~switch~s~.
						dialogue_timer = 0
						dialogue_flag = 0
						m_goals++
					ELSE
						m_goals++
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF m_goals = 1
			IF NOT IS_CAR_DEAD van
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR van
					FREEZE_CAR_POSITION van TRUE
					dialogue_flag = 0
					dialogue_timer = 20000
					m_goals++
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF

		// wait for player to open warehouse
		IF m_goals = 2
			IF NOT warehouse_opened = 0
				IF NOT IS_CAR_DEAD van
					REMOVE_BLIP switch2_blip
					//FREEZE_CAR_POSITION van FALSE
					REQUEST_CAR_RECORDING 524
					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 524
						WAIT 0
					ENDWHILE
					IF NOT IS_CAR_DEAD van
						START_PLAYBACK_RECORDED_CAR van 524
					ENDIF
					TIMERA = 0
					dialogue_flag = 0
					m_goals++
				ENDIF
			ELSE
				// ryder is waiting for player to open gate
				IF dialogue_timer > 20000
					IF NOT IS_CHAR_DEAD ryder
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryder 15.0 15.0 15.0 FALSE
							IF audio_line_is_active = 0
								SWITCH dialogue_flag
									CASE 0 
										$audio_string    = &RYD2_GB					  
										audio_sound_file = SOUND_RYD2_GB
										START_NEW_SCRIPT audio_line ryder 0 1 1 0
										dialogue_flag++
										dialogue_timer = 0
									BREAK
									CASE 1
										$audio_string    = &RYD2_LA					  
										audio_sound_file = SOUND_RYD2_LA
										START_NEW_SCRIPT audio_line ryder 0 1 1 0
										dialogue_flag++
										dialogue_timer = 0
									BREAK
									CASE 2
										$audio_string    = &RYD2_LE					  
										audio_sound_file = SOUND_RYD2_LE
										START_NEW_SCRIPT audio_line ryder 0 1 1 0
										dialogue_flag = 0
										dialogue_timer = 0
									BREAK
								ENDSWITCH
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
			   
		// play dialogue
		IF m_goals = 3
			dialogue_flag = 0
			IF audio_line_is_active = 0
				GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0
					$audio_string    = &RYD2_HA					  
					audio_sound_file = SOUND_RYD2_HA
					START_NEW_SCRIPT audio_line scplayer 0 1 1 0
					m_goals++
				ELSE
					$audio_string    = &RYD2_HB					  
					audio_sound_file = SOUND_RYD2_HB
					START_NEW_SCRIPT audio_line scplayer 0 1 1 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF


		// wait for ryder to park van
		IF m_goals = 4
			IF NOT IS_CAR_DEAD van
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR van
					m_goals = 99
				ELSE
					IF TIMERA > 10600
						STOP_PLAYBACK_RECORDED_CAR van
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// end
		IF m_goals = 99
			IF NOT IS_CAR_DEAD van
				FREEZE_CAR_POSITION van TRUE
				SET_VEHICLE_IS_CONSIDERED_BY_PLAYER van FALSE
			ENDIF
			m_goals = 0
			m_stage++
		ENDIF

		// if ryder gets pulled out van
		// 1st time
		IF ryder_flag = 0
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CAR_DEAD van
					IF NOT IS_CHAR_IN_CAR ryder van
						IF IS_PLAYBACK_GOING_ON_FOR_CAR van 
							PAUSE_PLAYBACK_RECORDED_CAR van
							FREEZE_CAR_POSITION van TRUE
						ENDIF
						TASK_ENTER_CAR_AS_DRIVER ryder van 10000
						TIMERA = 0
						ryder_flag++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF ryder_flag = 1
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CAR_DEAD van
					IF IS_CHAR_IN_CAR ryder van
						IF IS_PLAYBACK_GOING_ON_FOR_CAR van
							UNPAUSE_PLAYBACK_RECORDED_CAR van
							FREEZE_CAR_POSITION van FALSE
						ENDIF
						ryder_flag = 0
					ENDIF
				ENDIF
			ENDIF
			IF TIMERA > 10000
				ryder_flag = 0
			ENDIF
		ENDIF

RETURN


// *************************************************************************************************************
//						STAGE 5 - LOAD VAN  
// *************************************************************************************************************
LVAR_FLOAT van_back_x van_back_y van_back_z
LVAR_FLOAT van_back_ryder_x van_back_ryder_y van_back_ryder_z
LVAR_INT ryder_explain_dialogue
ryder2_m_stage_5:

	// DEBUG
	IF debug_on = 1
		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			loaded_boxes = required_boxes	
		ENDIF
	ENDIF

	
	
	// setup loading of van
	IF m_goals = 0

		REQUEST_ANIMATION RYDER
		WHILE NOT HAS_ANIMATION_LOADED RYDER
			WAIT 0
		ENDWHILE

		ryder_explain_dialogue = 0
		loaded_boxes_neg = required_boxes					
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING loaded_boxes_neg COUNTER_DISPLAY_NUMBER 2 RY2_21 // Boxes Loaded ~1~
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING ryder_health COUNTER_DISPLAY_BAR 1 RY2_19

		IF NOT IS_CAR_DEAD van	
			IF IS_CAR_MODEL van MULE
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS van 0.0 -5.5 0.6 van_back_x van_back_y van_back_z // was 0.0 4.5 0.6
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS	van 3.0 5.5 0.6 van_back_ryder_x van_back_ryder_y van_back_ryder_z
			ENDIF
			SET_CAR_HEAVY van TRUE
			SET_CAR_STRONG van TRUE
			SET_CAR_HEALTH van 2500 
			//FREEZE_CAR_POSITION van TRUE
			LOCK_CAR_DOORS van CARLOCK_LOCKOUT_PLAYER_ONLY
		ENDIF
		IF NOT IS_CHAR_DEAD ryder
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryder_dm EVENT_POTENTIAL_GET_RUN_OVER
			SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1
			SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
			TASK_TOGGLE_PED_THREAT_SCANNER ryder TRUE TRUE TRUE 
			IF DOES_BLIP_EXIST van_blip
				REMOVE_BLIP van_blip
			ENDIF
		ENDIF
		

		IF NOT IS_CAR_DEAD fork
			IF NOT IS_CHAR_IN_CAR scplayer fork
				PRINT RY2_55 5000 1  // Get in the ~b~forklift~s~ truck
				REMOVE_BLIP van_blip
				ADD_BLIP_FOR_CAR fork van_blip
				SET_BLIP_AS_FRIENDLY van_blip TRUE
			ENDIF
		ENDIF

		enable_fake_van_collision = 1

		frozen_van = 0
		dialogue_timer = -4000
		dialogue_flag = 0
		TIMERA = 0
		WAIT 0
		m_goals++
	ENDIF
		 
	LVAR_INT frozen_van
	IF frozen_van = 0
		IF NOT IS_CHAR_DEAD ryder
			IF IS_CHAR_ON_FOOT ryder
				IF NOT IS_CAR_DEAD van
					FREEZE_CAR_POSITION van TRUE
					frozen_van = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	IF ryder_explain_dialogue = 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND audio_line_is_active = 0
			$audio_string    = &RYD2_KB					  
			audio_sound_file = SOUND_RYD2_KB
			START_NEW_SCRIPT audio_line ryder 0 1 1 0
			ryder_explain_dialogue = 1
			TIMERA = 0
		ENDIF
	ELSE
		IF ryder_explain_dialogue = 1
			IF TIMERA > 1000
				IF audio_line_is_active = 0
					ryder_explain_dialogue = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF



	// wait for player to get in forklift truck
	IF m_goals = 1
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF NOT IS_CAR_DEAD fork
				IF IS_CHAR_IN_CAR scplayer fork
				AND ryder_explain_dialogue = 2
					//PRINT_NOW RY2_70  7000 1 // use the forklift truck to load boxes onto the van.
					IF forklift_help_flag < 1
						forklift_help_flag = 1
					ENDIF
					m_goals++
					TIMERA = 0
					WAIT 0
				ELSE
					IF IS_CHAR_IN_CAR scplayer fork
						CLEAR_THIS_PRINT RY2_55
						forklift_help_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF  

	// forklift help
	LVAR_INT forklift_help_flag
	IF forklift_help_flag > 0
	AND forklift_help_flag < 5
		IF NOT IS_CAR_DEAD fork
			IF IS_CHAR_IN_CAR scplayer fork
				IF NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					SWITCH forklift_help_flag
						CASE 1 
							PRINT_HELP RY2_69 // Position the forks below the box then raise the forks to lift the box.
						BREAK
						CASE 2
							PRINT_HELP RY2_68 
						BREAK
						CASE 3
						    PRINT_HELP RY2_71  
						BREAK
					ENDSWITCH
					forklift_help_flag++
				ENDIF
			ELSE
				CLEAR_HELP
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 2
		m_goals += 3
	ENDIF

//	// wait for text to finish, put up forklift help 
//	IF m_goals = 2
//		IF NOT IS_MESSAGE_BEING_DISPLAYED
//			PRINT_HELP RY2_69 // Position the forks below the box then raise the forks to lift the box.
//			TIMERA = 0
//			m_goals++
//			WAIT 0
//		ENDIF
//	ENDIF
	
//	// 2nd bit of help text
//	IF m_goals = 3
//		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
//			
//			PRINT_HELP RY2_68
//			WAIT 0
//			m_goals++		
//		ENDIF
//	ENDIF

//	// 3rd bit of help text
//	IF m_goals = 4
//		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
//			PRINT_HELP RY2_71
//			WAIT 0
//			m_goals++		
//		ENDIF
//	ENDIF


	// play audio
	IF m_goals = 5
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND NOT IS_MESSAGE_BEING_DISPLAYED
			IF audio_line_is_active = 0
				IF dialogue_timer > 1000
					SWITCH dialogue_flag
						CASE 0
							$audio_string    = &RYD2_KA					  
							audio_sound_file = SOUND_RYD2_KA
							START_NEW_SCRIPT audio_line ryder 0 1 1 0
							dialogue_flag++
							dialogue_timer = 0
						BREAK
//						CASE 1
//							$audio_string    = &RYD2_KB					  
//							audio_sound_file = SOUND_RYD2_KB
//							START_NEW_SCRIPT audio_line ryder 0 1 1 0
//							dialogue_flag++
//							dialogue_timer = 0
//						BREAK
//						CASE 2
//							$audio_string    = &RYD2_KC					  
//							audio_sound_file = SOUND_RYD2_KC
//							START_NEW_SCRIPT audio_line ryder 0 1 1 0
//							dialogue_flag++
//							dialogue_timer = 0
//						BREAK
						CASE 1
							dialogue_flag = 0
							m_goals++
						BREAK
					ENDSWITCH
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// wait for audio to finish
	IF m_goals = 6
		IF dialogue_timer > 1000
			IF audio_line_is_active	= 0


				spawn_timer	= 0
//				next_spawn_time = 40000
//				baddies_spawned = 0
//				baddies_to_be_spawned = 2
				TIMERB = 30000
				ryder_dialogue_timer = 0



				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF
	ENDIF
		
//	// spawn guards
	LVAR_INT spawn_new_wave_of_guards
//	IF spawn_new_wave_of_guards = 1  
//		SWITCH loaded_boxes
//			CASE 0
//				spawn_this_many_guards = 1
//			BREAK
//			CASE 1
//				spawn_this_many_guards = 1
//			BREAK
//			CASE 2
//				spawn_this_many_guards = 1
//			BREAK
//			CASE 3
//				spawn_this_many_guards = 1
//			BREAK
//			CASE 4
//			   	spawn_this_many_guards = 1
//			BREAK
//			CASE 5
//				spawn_this_many_guards = 1
//			BREAK
//		ENDSWITCH
//		spawn_timer = 0
//		spawn_new_wave_of_guards = 0
//	ENDIF 

	// spawn more guard if the player is taking ages
	IF m_goals > 5
	AND spawn_timer > 30000		
		//WRITE_DEBUG spawned_guard
		spawn_this_many_guards = 1
		spawn_timer = 0
	ENDIF
														  
	// ryder loads boxes								  
	IF m_goals = 8										  
		IF NOT IS_CHAR_DEAD ryder 
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			AND NOT IS_MESSAGE_BEING_DISPLAYED
			
				ryder_explained_what_to_do = 1

				// print dialogue
				IF ryder_dialogue_timer > 30000
					
					SWITCH ryder_dialogue_int

						CASE 0
							$audio_string    = &RYD2_LA				
							audio_sound_file = SOUND_RYD2_LA
							START_NEW_SCRIPT audio_line ryder 0 0 1 0
						BREAK
						CASE 1
							$audio_string    = &RYD2_LB				
							audio_sound_file = SOUND_RYD2_LB
							START_NEW_SCRIPT audio_line ryder 0 0 1 0
						BREAK
						CASE 2
							$audio_string    = &RYD2_LC				
							audio_sound_file = SOUND_RYD2_LC
							START_NEW_SCRIPT audio_line ryder 0 0 1 0
						BREAK
						CASE 3
							$audio_string    = &RYD2_LD				
							audio_sound_file = SOUND_RYD2_LD
							START_NEW_SCRIPT audio_line ryder 0 0 1 0
						BREAK
						CASE 4
							$audio_string    = &RYD2_LE				
							audio_sound_file = SOUND_RYD2_LE
							START_NEW_SCRIPT audio_line ryder 0 0 1 0
						BREAK
					
					ENDSWITCH

					ryder_dialogue_int++
					IF ryder_dialogue_int > 4
						ryder_dialogue_int = 0
					ENDIF
					ryder_dialogue_timer = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// manage forklift / location blips
	IF DOES_BLIP_EXIST van_Blip
		IF NOT IS_CAR_DEAD fork
			IF IS_CHAR_IN_CAR scplayer fork
				CLEAR_THIS_PRINT RY2_55
				REMOVE_BLIP van_blip
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CAR_DEAD fork
			IF NOT IS_CHAR_IN_CAR scplayer fork
				REMOVE_BLIP van_blip
				ADD_BLIP_FOR_CAR fork van_blip
				SET_BLIP_AS_FRIENDLY van_Blip TRUE
				IF DOES_BLIP_EXIST location_blip
					REMOVE_BLIP location_blip
				ENDIF
				//IF NOT IS_MESSAGE_BEING_DISPLAYED
				//AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					PRINT RY2_55 5000 1  // Get in the ~b~forklift~s~ truck
				//ENDIF
			ELSE
				IF m_goals >= 1
					IF NOT DOES_BLIP_EXIST location_blip 
						REMOVE_BLIP location_blip
						IF NOT IS_CAR_DEAD van
							ADD_BLIP_FOR_CAR van location_blip
							//CHANGE_BLIP_COLOUR location_blip YELLOW
							SET_BLIP_AS_FRIENDLY location_blip TRUE
						ENDIF
						//ADD_BLIP_FOR_COORD van_back_x van_back_y van_back_z location_Blip
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	IF NOT IS_CHAR_DEAD ryder
		
		// 0. Go to back of van and wait for next box
		IF ryder_stage = 0
			GET_SCRIPT_TASK_STATUS ryder PERFORM_SEQUENCE_TASK temp_int
			IF temp_int	= FINISHED_TASK


				WHILE NOT HAS_ANIMATION_LOADED COLT45
					REQUEST_ANIMATION COLT45
					WAIT 0
				ENDWHILE
				

				OPEN_SEQUENCE_TASK temp_seq
					TASK_GO_STRAIGHT_TO_COORD -1 van_back_ryder_x van_back_ryder_y van_back_ryder_z PEDMOVE_WALK -2
//						IF IS_PLAYER_PLAYING player1
//							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
//						ENDIF
					TASK_ACHIEVE_HEADING -1 90.0
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					//TASK_LOOK_ABOUT -1 5000
					TASK_PLAY_ANIM -1 roadcross PED 4.0 FALSE TRUE TRUE FALSE -1
					TASK_PLAY_ANIM -1 roadcross PED 4.0 FALSE TRUE TRUE FALSE -1
					TASK_PLAY_ANIM -1 colt45_reload COLT45 4.0 FALSE TRUE TRUE FALSE -1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK ryder temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				//ryder_stage++
			ENDIF						
		ENDIF

		


		// check if box has been loaded onto the back of the van -----------------------------------------------------------
		LVAR_INT activate_load_box_timer	
		LVAR_INT loading_box_screen
		LVAR_INT box_to_delete
		temp_int = 0
		WHILE temp_int < 25
			IF DOES_OBJECT_EXIST m_box[temp_int]
				IF NOT IS_CAR_DEAD van
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS van 0.0000 -2.6100 1.0400	x y z
					IF LOCATE_OBJECT_3D m_box[temp_int]	x y z 0.7100 1.0000 1.1200	FALSE
						IF NOT IS_CAR_DEAD fork
							IF IS_CHAR_IN_CAR scplayer fork
								IF NOT IS_CHAR_ON_FOOT scplayer
									IF IS_CHAR_SITTING_IN_CAR scplayer fork
										IF activate_load_box_timer = 0
											activate_load_box_timer = 1
										ENDIF
										IF load_box_timer > 500
											
											box_to_delete = temp_int
											loading_box_screen++ 
											load_box_timer = 0
											
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE
		IF activate_load_box_timer = 0
			load_box_timer = 0
		ENDIF

		IF NOT loading_box_screen = 0
			
			SWITCH loading_box_screen
				CASE 1
					IF NOT IS_CAR_DEAD fork
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						WARP_CHAR_INTO_CAR scplayer fork
					ENDIF
					SWITCH_WIDESCREEN ON
					CLEAR_HELP
					SET_PLAYER_CONTROL player1 OFF	   
					SET_FIXED_CAMERA_POSITION 	2767.4814 -2410.5925 14.7201 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT  		2768.1326 -2411.3464 14.6336 JUMP_CUT	
					DELETE_OBJECT m_box[box_to_delete]
					loaded_boxes++
					loading_box_screen++
				BREAK
				CASE 2
					IF load_box_timer > 1000
//						IF IS_CHAR_IN_ANY_CAR scplayer
//							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
//							TASK_CAR_TEMP_ACTION scplayer car TEMPACT_REVERSE 2000
//							SET_CAR_FORWARD_SPEED car -5.0				
//						ENDIF	
						load_box_timer = 0 
						loading_box_screen++
					ENDIF		
				BREAK
				CASE 3
					IF load_box_timer > 1000
						loading_box_screen++	
					ELSE
						IF load_box_timer < 500
							IF NOT IS_CAR_DEAD fork
								SET_CAR_FORWARD_SPEED fork -5.0
							ENDIF
						ENDIF
					ENDIF
				BREAK
				CASE 4
					SWITCH_WIDESCREEN OFF
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					SET_PLAYER_CONTROL player1 ON
					activate_load_box_timer = 0
					loading_box_screen = 0
					load_box_timer = 0	
					spawn_new_wave_of_guards = 1
				BREAK
			ENDSWITCH

		ENDIF
		


	ENDIF
	


//	// if alarm is gone off spawn baddies who arrive in jeeps
//	IF jeep_created = 0 
//	AND loaded_boxes > 2
//		IF m_goals > 4
//			IF frame_num = 9
//			AND NOT alarm_raised = 0
//			//AND jeep_timer > 200000
//				// make a jeep come from the left.
//				IF jeep2 = 0
//					  
//					IF NOT IS_POINT_ON_SCREEN 2485.0 -2562.3 14.0 5.0
//			
//						IF NOT HAS_MODEL_LOADED MESA
//							REQUEST_MODEL MESA
//							WHILE NOT HAS_MODEL_LOADED MESA
//								WAIT 0
//							ENDWHILE
//						ENDIF
//
//				   		CREATE_CAR MESA 2485.0 -2562.3 14.0 jeep2
//						MARK_MODEL_AS_NO_LONGER_NEEDED MESA
//						SET_CAR_HEADING jeep2 0.0
//						SET_CAR_CRUISE_SPEED jeep2 50.0
//						CHANGE_CAR_COLOUR jeep2 37 37 // army green
//						CREATE_CHAR_INSIDE_CAR jeep2 PEDTYPE_MISSION1 ARMY m_guard[7]
//						new_group_member = m_guard[7]
//						GOSUB set_new_guard_member
//						CREATE_CHAR_AS_PASSENGER jeep2 PEDTYPE_MISSION1 ARMY 0 m_guard[8]
//						new_group_member = m_guard[8]
//						GOSUB set_new_guard_member
//
//						GENERATE_RANDOM_INT_IN_RANGE 3 6 temp_int
//						OPEN_SEQUENCE_TASK temp_seq
//							IF NOT IS_CAR_DEAD jeep2
//								TASK_CAR_DRIVE_TO_COORD -1 jeep2 2651.4 -2505.8 13.6 50.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//								TASK_CAR_DRIVE_TO_COORD -1 jeep2 2731.4 -2504.2 13.5 50.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//								TASK_CAR_DRIVE_TO_COORD -1 jeep2 2761.1 -2439.4 13.6 50.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//								TASK_CAR_TEMP_ACTION -1 jeep2 temp_int 1000
//								TASK_LEAVE_CAR -1 jeep2
//							ENDIF
//								TASK_GOTO_CHAR -1 scplayer 60000 30.0
//								TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
//						CLOSE_SEQUENCE_TASK temp_seq
//						PERFORM_SEQUENCE_TASK m_guard[7] temp_seq
//						CLEAR_SEQUENCE_TASK temp_seq
//													
//						passenger2_got_task = 0
//						jeep_created = 1
//						//WRITE_DEBUG LEFT_JEEP_CREATED
//						jeep_timer = 0
//						jeep_wave = 0
//					ELSE
//						jeep_wave = 0
//					ENDIF
//
//				ELSE
//					IF NOT m_guard[7] = 0
//						//MARK_CHAR_AS_NO_LONGER_NEEDED m_guard[7]
//						m_guard[7] = 0
//					ENDIF
//					IF NOT m_guard[8] = 0
//						//MARK_CHAR_AS_NO_LONGER_NEEDED m_guard[8]
//						m_guard[8] = 0
//					ENDIF
//					IF NOT jeep2 = 0
//						MARK_CAR_AS_NO_LONGER_NEEDED jeep2
//						jeep2 = 0
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF 
//	ELSE
//		// open gate
//		IF jeep_created = 1
//			IF DOES_OBJECT_EXIST gate3
//				IF SLIDE_OBJECT gate3 gate3_target_x gate3_target_y gate3_target_z 0.0 0.2 0.0 FALSE	
//			   		jeep_created++
//				ENDIF
//			ENDIF			
//		ENDIF
//	ENDIF
//	IF passenger2_got_task = 0
//		IF NOT IS_CHAR_DEAD m_guard[8]
//			IF NOT IS_CHAR_DEAD m_guard[7]
//				IF NOT IS_CAR_DEAD jeep2
//					IF NOT IS_CHAR_IN_CAR m_guard[7] jeep2
//						OPEN_SEQUENCE_TASK temp_seq
//							TASK_LEAVE_CAR -1 jeep2
//							TASK_GOTO_CHAR -1 scplayer 60000 30.0
//							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
//						CLOSE_SEQUENCE_TASK temp_seq 
//						PERFORM_SEQUENCE_TASK m_guard[8] temp_seq
//						PERFORM_SEQUENCE_TASK m_guard[7] temp_seq
//						CLEAR_SEQUENCE_TASK	temp_seq
//						passenger2_got_task = 1 
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF	

	// check if ryder gets damaged by guards
	IF frame_num = 7
		IF NOT IS_CHAR_DEAD ryder 
			IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON ryder WEAPONTYPE_PISTOL
			AND ryder_dialogue_timer2 > 40000
				LVAR_INT got_hit_dialogue
				got_hit_dialogue ++
				IF got_hit_dialogue > 4
					got_hit_dialogue = 1
				ENDIF
					
				SWITCH got_hit_dialogue
					CASE 1 
					
						$audio_string    = &RYD2_MA					  
						audio_sound_file = SOUND_RYD2_MA
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
					BREAK
					CASE 2
						$audio_string    = &RYD2_MC					
						audio_sound_file = SOUND_RYD2_MC
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
					BREAK
					CASE 3
						$audio_string    = &RYD2_MD					
						audio_sound_file = SOUND_RYD2_MD
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
					BREAK
					CASE 4
						$audio_string    = &RYD2_ME					
						audio_sound_file = SOUND_RYD2_ME
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
					BREAK
				ENDSWITCH
				
				CLEAR_CHAR_LAST_WEAPON_DAMAGE ryder 
				ryder_dialogue_timer2 = 0

			ENDIF
		ENDIF
	ENDIF

	// if all the boxes are loaded go to next stage
	IF loaded_boxes >= required_boxes
	AND loading_box_screen = 0
		CLEAR_ONSCREEN_COUNTER loaded_boxes_neg
		IF NOT next_box = -1
			IF DOES_OBJECT_EXIST m_box[next_box]
				IF NOT IS_CHAR_DEAD ryder
					IF IS_CHAR_HOLDING_OBJECT ryder m_box[next_box]
						DROP_OBJECT ryder TRUE
					ENDIF
				ENDIF
//				IF IS_OBJECT_ATTACHED m_box[next_box]
//					DETACH_OBJECT m_box[next_box]	0.0 0.0 0.01 TRUE
//				ENDIF
			ENDIF
		ENDIF
		m_goals = 99


	ENDIF

	// **************************************************
	// 		TELL PLAYER THERE ARE MORE BOXES OUTSIDE
	// **************************************************
	IF boxes_outside_timer > 30000
	AND NOT m_goals = 99
	AND told_more_boxes_outside < 1

		IF audio_line_is_active = 0 
		AND NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF frame_num = 5

				IF IS_CHAR_IN_AREA_3D scplayer 2775.7815 -2406.8206 10.6380 2801.4333 -2429.6179 15.3143 FALSE
				
					temp_int = 0
					temp_int2 = 0
					WHILE temp_int < 25
						IF DOES_OBJECT_EXIST m_box[temp_int]
							IF NOT HAS_OBJECT_BEEN_DAMAGED m_box[temp_int]
								IF IS_OBJECT_IN_AREA_3D m_box[temp_int] 2774.3079 -2405.2800 12.6803 2801.6074 -2430.0728 15.3896 FALSE 
									temp_int2++
									temp_int = 25
								ENDIF
							ENDIF
						ENDIF
						temp_int++
					ENDWHILE


					//WRITE_DEBUG_WITH_INT temp_int2 temp_int2

					IF temp_int2 = 0
						//PRINT_NOW RY2_49 5000 1 // there are more boxes outside
						GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
						IF temp_int = 0
							$audio_string    = &RYD2_LH					
							audio_sound_file = SOUND_RYD2_LH
							START_NEW_SCRIPT audio_line ryder 0 1 1 0
						ELSE
							$audio_string    = &RYD2_LJ				
							audio_sound_file = SOUND_RYD2_LJ
							START_NEW_SCRIPT audio_line ryder 0 1 1 0
						ENDIF
						boxes_outside_timer =  0
						//boxes_are_outside_dialogue++ 
						told_more_boxes_outside++
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF 

	IF m_goals = 99
		goto_start_of_loop = 1
		m_goals = 0
		m_stage++
	ENDIF
	
	GOSUB process_spawning_guards

	shown_boxes = loaded_boxes
	GOSUB update_van_filling

RETURN

// *************************************************************************************************************
//						STAGE 6 - Get back to base  
// *************************************************************************************************************
ryder2_m_stage_6:

	IF m_goals = 0
		
		LVAR_FLOAT van_roll
		LVAR_FLOAT van_this_heading 
		LVAR_FLOAT van_last_heading 
		LVAR_FLOAT van_diff_heading
		LVAR_INT ryder_anim_flag
		LVAR_INT ryder_get_box_flag
		LVAR_INT r3_is_pressed
		LVAR_INT throwing_box
		LVAR_INT exploding_box[10]
		LVAR_INT ryder_is_attached_to_van
		LVAR_INT disable_chuck_boxes
		LVAR_FLOAT vec_x vec_y

		ryder_anim_flag = 0
		ryder_get_box_flag = 0
		r3_is_pressed = 0
		throwing_box = 0
		temp_int = 0
		WHILE temp_int < 10
			exploding_box[temp_int] = 0
			temp_int++
		ENDWHILE
		ryder_is_attached_to_van = 0
		disable_chuck_boxes = 0
		throwing_boxes = 4
		
		IF NOT IS_CHAR_DEAD ryder
			SET_CHAR_DECISION_MAKER ryder empty_dm
		ENDIF

//		REQUEST_ANIMATION RYDER
//		WHILE NOT HAS_ANIMATION_LOADED RYDER
//			WAIT 0
//		ENDWHILE

//		REMOVE_ANIMATION BOX
//		REMOVE_ANIMATION CARRY
		
		dialogue_flag = 0
		ryder_is_attached_to_van = 0

		m_goals++
	ENDIF


	// small cut of ryder getting in van
	IF m_goals = 1
		
		LVAR_FLOAT ryder_point_x ryder_point_y ryder_point_z

		ryder_point_x =	2765.9990 
		ryder_point_y =	-2419.7766 
		ryder_point_z =	12.6396

		// get safe point to put ryder
		CLEAR_AREA ryder_point_x ryder_point_y ryder_point_z 1.5 TRUE
//		WHILE IS_POINT_OBSCURED_BY_A_MISSION_ENTITY ryder_point_x ryder_point_y ryder_point_z 0.5 0.5 1.0 
//			ryder_point_y += -1.0
//			WAIT 0
//		ENDWHILE

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 2761.1191 -2421.1570 14.5289 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2762.0024 -2420.6914 14.4766 JUMP_CUT

		IF NOT IS_CHAR_DEAD ryder
			SET_CHAR_PROOFS ryder TRUE TRUE TRUE TRUE TRUE
			CLEAR_CHAR_TASKS_IMMEDIATELY ryder 
			SET_CHAR_COORDINATES ryder ryder_point_x ryder_point_y ryder_point_z
			SET_CHAR_HEADING ryder 270.0
			TASK_GO_STRAIGHT_TO_COORD ryder 2771.3521 -2418.0725 12.6440 PEDMOVE_RUN 5000
		ENDIF

		IF audio_line_is_active = 0 
			$audio_string    = &RYD2_ND				
			audio_sound_file = SOUND_RYD2_ND
			START_NEW_SCRIPT audio_line ryder 0 1 1 0  // C’mon, CJ, we got enough!	
		ENDIF

		TIMERA = 0
		
		m_goals++
	ENDIF

	// wait 
	IF m_goals = 2
		IF TIMERA > 2500
		AND audio_line_is_active = 0
			m_goals++
			TIMERA = 0	
		ENDIF
	ENDIF

	// switch camera
	IF m_goals = 3
	
	
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CAR_DEAD van		

				CLEAR_CHAR_TASKS_IMMEDIATELY ryder
				ATTACH_CHAR_TO_CAR ryder van 0.0 -3.0 0.9 FACING_BACK 180.0 WEAPONTYPE_UNARMED
				CLEAR_ONSCREEN_COUNTER ryder_health
				SET_CHAR_MAX_HEALTH ryder 350
				SET_CHAR_HEALTH ryder 350
				CREATE_OBJECT NF_PED_COLL 0.0 0.0 0.0 fake_ryder_collision
				ATTACH_OBJECT_TO_CAR fake_ryder_collision van -0.5 -2.0 0.0 0.0 0.0 0.0
				SET_CHAR_PROOFS ryder TRUE TRUE TRUE TRUE TRUE
				
				SET_OBJECT_SCALE fake_ryder_collision 0.5
				SET_OBJECT_PROOFS fake_ryder_collision FALSE TRUE TRUE TRUE FALSE
 				last_fake_object_health = 1000
				ryder_is_attached_to_van = 1
				disable_chuck_boxes = 1

				OPEN_SEQUENCE_TASK temp_seq
					TASK_LOOK_AT_CHAR -1 scplayer 2000
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 idle_chat PED 4.0 FALSE FALSE FALSE TRUE 2000
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 VAN_stand RYDER 4.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK temp_Seq
				PERFORM_SEQUENCE_TASK ryder temp_seq
				CLEAR_SEQUENCE_TASK temp_seq

			ENDIF
		ENDIF

		
		

		SET_FIXED_CAMERA_POSITION 2774.3230 -2420.5894 14.8944 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2773.4412 -2420.1323 14.7784 JUMP_CUT

		$audio_string    = &RYD2_NB				
		audio_sound_file = SOUND_RYD2_NB
		START_NEW_SCRIPT audio_line ryder 0 1 1 0  // CJ, get up front an drive us outta here!

		TIMERA = 0
		m_goals++
	ENDIF

	// wait 
	IF m_goals = 4
		IF TIMERA > 2500
		AND audio_line_is_active = 0
			
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			PRINT_NOW RY2_67 5000 1 //Get in the ~b~van~s~.
			
			IF NOT IS_CAR_DEAD van
				REMOVE_BLIP van_blip
				REMOVE_BLIP van_blip
				ADD_BLIP_FOR_CAR van van_blip
				SET_BLIP_AS_FRIENDLY van_blip TRUE
				SET_CAR_PROOFS van FALSE FALSE TRUE FALSE FALSE

				FREEZE_CAR_POSITION van FALSE
				SET_VEHICLE_IS_CONSIDERED_BY_PLAYER van TRUE
				LOCK_CAR_DOORS van CARLOCK_UNLOCKED
			ENDIF

			IF NOT IS_CHAR_DEAD ryder
				SET_CHAR_PROOFS ryder FALSE FALSE FALSE FALSE FALSE
			ENDIF

			m_goals++
			TIMERA = 0	
		ENDIF
	ENDIF
	
	IF m_goals = 5
		
		temp_int2 = 0
		// check if player is in van
		IF NOT IS_CAR_DEAD van
			IF IS_CHAR_IN_CAR scplayer van
				temp_int2 = 1
				SET_PLAYER_IN_CAR_CAMERA_MODE   ZOOM_THREE

			ELSE
				IF NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					IF dialogue_flag = 0
						IF TIMERA > 30000
						OR temp_int2 = 1
							GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
							SWITCH temp_int
								CASE 0
									$audio_string    = &RYD2_NA				
									audio_sound_file = SOUND_RYD2_NA
									START_NEW_SCRIPT audio_line ryder 0 1 1 0	
								BREAK
		//						CASE 1
		//							$audio_string    = &RYD2_NB				
		//							audio_sound_file = SOUND_RYD2_NB
		//							START_NEW_SCRIPT audio_line ryder 0 1 1 0	
		//						BREAK
								CASE 1
									$audio_string    = &RYD2_NC				
									audio_sound_file = SOUND_RYD2_NC
									START_NEW_SCRIPT audio_line ryder 0 1 1 0	
								BREAK
								CASE 2
									$audio_string    = &RYD2_ND				
									audio_sound_file = SOUND_RYD2_ND
									START_NEW_SCRIPT audio_line ryder 0 1 1 0	
								BREAK
							ENDSWITCH
							dialogue_flag = 1
							TIMERA = 0
						ENDIF
					ENDIF
				ENDIF

			ENDIF
		ENDIF




		IF temp_int2 = 1
			TIMERA = 0
			dialogue_timer = 0
			m_goals++
		ENDIF

	ENDIF




	IF m_goals = 6
		// check if player has got back in van
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND audio_line_is_active = 0 
			IF NOT IS_CAR_DEAD van
				IF IS_CHAR_IN_CAR scplayer van

//					START_NEW_SCRIPT cleanup_audio_line
//					CLEAR_MISSION_AUDIO 1
//					CLEAR_MISSION_AUDIO 2

					FREEZE_CAR_POSITION van FALSE
					IF DOES_BLIP_EXIST van_blip
						REMOVE_BLIP van_blip
					ENDIF
					
					REMOVE_BLIP location_blip
					
					IF NOT DOES_BLIP_EXIST location_blip
						ADD_BLIP_FOR_COORD 2454.6257 -1972.5344 12.5617 location_blip
						PRINT_NOW RY2_66 6000 1 // head to willowfield
					ENDIF
	 				TIMERA = 0
			
					REQUEST_MODEL PATRIOT
					REQUEST_MODEL ARMY
					WHILE NOT HAS_MODEL_LOADED PATRIOT
					   OR NOT HAS_MODEL_LOADED ARMY
						WAIT 0
					ENDWHILE
									   
					CREATE_CAR PATRIOT 2780.7183 -2456.1033 12.6354 patriot1	  //2783.1235 -2456.7844 12.6281
					SET_CAR_HEADING patriot1 90.0
					SET_CAR_HEAVY patriot1 TRUE
					LOCK_CAR_DOORS patriot1 CARLOCK_LOCKOUT_PLAYER_ONLY
					SET_CAR_PROOFS patriot1 TRUE TRUE TRUE TRUE TRUE
					 
					CREATE_CAR PATRIOT 2780.5569 -2493.3464 12.6287 patriot2
					SET_CAR_HEADING patriot2 90.0
					SET_CAR_HEAVY patriot2 TRUE
					LOCK_CAR_DOORS patriot2 CARLOCK_LOCKOUT_PLAYER_ONLY
					SET_CAR_PROOFS patriot2 TRUE TRUE TRUE TRUE TRUE

					// create patriot drivers
					IF NOT IS_CAR_DEAD patriot1
						CREATE_CHAR_INSIDE_CAR patriot1 PEDTYPE_MISSION1 ARMY driver1
						TASK_TOGGLE_PED_THREAT_SCANNER driver1 FALSE FALSE FALSE
						SET_CAR_DRIVING_STYLE  patriot1 DRIVINGMODE_AVOIDCARS
						SET_CAR_CRUISE_SPEED patriot1 50.0
						SET_CAR_PROOFS patriot1 FALSE FALSE FALSE FALSE FALSE
						SET_CAR_TRACTION patriot1 2.0
						SET_CAR_HEAVY patriot1 TRUE
						SET_CAR_HEALTH patriot1 1300
						
						// create passenger
						CREATE_CHAR_AS_PASSENGER patriot1 PEDTYPE_MISSION1 ARMY 0 passenger1
						SET_CHAR_RELATIONSHIP passenger1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						SET_CHAR_DECISION_MAKER passenger1 empty_dm
						GIVE_WEAPON_TO_CHAR passenger1 WEAPONTYPE_PISTOL 999999
 					ENDIF

					IF NOT IS_CAR_DEAD patriot2
						CREATE_CHAR_INSIDE_CAR patriot2 PEDTYPE_MISSION1 ARMY driver2
						TASK_TOGGLE_PED_THREAT_SCANNER driver2 FALSE FALSE FALSE
						SET_CAR_DRIVING_STYLE  patriot2 DRIVINGMODE_AVOIDCARS
						SET_CAR_CRUISE_SPEED patriot2 50.0
						SET_CAR_PROOFS patriot2 FALSE FALSE FALSE FALSE FALSE
						SET_CAR_TRACTION patriot2 2.0
						SET_CAR_HEAVY patriot2 TRUE
						SET_CAR_HEALTH patriot2 1300
						// create passenger
						CREATE_CHAR_AS_PASSENGER patriot2 PEDTYPE_MISSION1 ARMY 0 passenger2
						SET_CHAR_RELATIONSHIP passenger2 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						SET_CHAR_DECISION_MAKER passenger2 empty_dm
						GIVE_WEAPON_TO_CHAR passenger2 WEAPONTYPE_PISTOL 999999
					ENDIF

					MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT
					MARK_MODEL_AS_NO_LONGER_NEEDED ARMY

					dialogue_timer = 0
					dialogue_flag = 0
					m_goals++
				ELSE
					IF dialogue_timer > 20000
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
 
							IF NOT IS_CHAR_DEAD ryder
								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryder 5.0 5.0 3.0 FALSE
									GENERATE_RANDOM_INT_IN_RANGE 0 4 temp_int
									SWITCH temp_int
										CASE 0
											$audio_string    = &RYD2_NA				
											audio_sound_file = SOUND_RYD2_NA
											START_NEW_SCRIPT audio_line ryder 1 0 1 0	
										BREAK
										CASE 1
											$audio_string    = &RYD2_NB				
											audio_sound_file = SOUND_RYD2_NB
											START_NEW_SCRIPT audio_line ryder 1 0 1 0	
										BREAK
										CASE 2
											$audio_string    = &RYD2_NC				
											audio_sound_file = SOUND_RYD2_NC
											START_NEW_SCRIPT audio_line ryder 1 0 1 0	
										BREAK
										CASE 3
											$audio_string    = &RYD2_ND				
											audio_sound_file = SOUND_RYD2_ND
											START_NEW_SCRIPT audio_line ryder 1 0 1 0	
										BREAK
									ENDSWITCH
									dialogue_timer = 0
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 7
		// cutscene of patriots moving out
		//IF TIMERA > 6000			

			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS gate4 0.0 0.0 4.0 gate1_target_x	gate1_target_y gate1_target_z
			GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS gate7 0.0 0.0 4.0 gate2_target_x	gate2_target_y gate2_target_z

			IF DOES_OBJECT_EXIST gate4
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate4 SOUND_SHUTTER_DOOR_START
			ENDIF

			TIMERA = 0
			m_goals++
		//ENDIF
	ENDIF

	IF m_goals = 8
		// open doors
		IF TIMERA > 1000 
			IF  SLIDE_OBJECT gate4 gate1_target_x gate1_target_y gate1_target_z 0.0 0.0 0.2 FALSE
			AND SLIDE_OBJECT gate7 gate2_target_x gate2_target_y gate2_target_z 0.0 0.0 0.15 FALSE
				IF DOES_OBJECT_EXIST gate4
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate4 SOUND_SHUTTER_DOOR_STOP
				ENDIF
				m_goals++
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 9
		// patriots drive out
		IF NOT IS_CAR_DEAD patriot1
			//CAR_GOTO_COORDINATES patriot1 2765.1235 -2456.7844 12.6281
			SET_CAR_FORWARD_SPEED patriot1 20.0
		ENDIF
		IF NOT IS_CAR_DEAD patriot2
			//CAR_GOTO_COORDINATES patriot2 2765.1235 -2493.3464 12.6287
			SET_CAR_FORWARD_SPEED patriot2 20.0
		ENDIF
		
		//PRINT_NOW RY2_16 5000 1 // RYDER - 'Shit we've got a couple of army heading for us.'
		dialogue_flag = 0
		dialogue_flag2 = 0
		dialogue_timer = 0
		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO 1 SOUND_RYD2_PA 

		IF NOT IS_CAR_DEAD patriot1
			IF NOT IS_CAR_DEAD van
				IF NOT IS_CHAR_DEAD driver1
					TASK_CAR_MISSION driver1 patriot1 van MISSION_RAMPLAYER_FARAWAY 50.0 DRIVINGMODE_AVOIDCARS
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD patriot2
			IF NOT IS_CAR_DEAD van
				IF NOT IS_CHAR_DEAD driver2
					TASK_CAR_MISSION driver2 patriot2 van MISSION_BLOCKPLAYER_FARAWAY 50.0 DRIVINGMODE_AVOIDCARS
				ENDIF
			ENDIF
		ENDIF

		// add stuck car checks
		IF NOT IS_CAR_DEAD patriot1
			IF NOT IS_CAR_DEAD patriot2
				ADD_STUCK_CAR_CHECK_WITH_WARP patriot1 1.0 2000 TRUE TRUE TRUE -1
				ADD_STUCK_CAR_CHECK_WITH_WARP patriot2 1.0 2000 TRUE TRUE TRUE -1
			ENDIF
		ENDIF

		m_goals++
		TIMERA = 0
	ENDIF

	IF m_goals = 10
		IF NOT IS_MESSAGE_BEING_DISPLAYED 
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			
			// say dialogue
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				$audio_string    = &RYD2_OA				
				audio_sound_file = SOUND_RYD2_OA
				START_NEW_SCRIPT audio_line ryder 0 1 2 0
			ELSE
				$audio_string    = &RYD2_OB								
				audio_sound_file = SOUND_RYD2_OB
				START_NEW_SCRIPT audio_line ryder 0 1 2 0
			ENDIF

			TIMERA = 0
			m_goals++
		ENDIF
	ENDIF

	// play dialogue
	IF m_goals = 11
		IF TIMERA > 1000
			IF NOT IS_CAR_DEAD van
				IF IS_CHAR_IN_CAR scplayer van
					IF NOT IS_MESSAGE_BEING_DISPLAYED 
					AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
						IF dialogue_timer > 1000
							IF audio_line_is_active= 0
								SWITCH dialogue_flag2 
									CASE 0
										$audio_string    = &RYD2_PA				
										audio_sound_file = SOUND_RYD2_PA
										START_NEW_SCRIPT audio_line ryder 0 1 1 1
										CLEAR_MISSION_AUDIO	2
										LOAD_MISSION_AUDIO 2 SOUND_RYD2_PB
										dialogue_flag2++
									BREAK
									CASE 1
										$audio_string    = &RYD2_PB				
										audio_sound_file = SOUND_RYD2_PB
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1
										CLEAR_MISSION_AUDIO	1
										LOAD_MISSION_AUDIO 1 SOUND_RYD2_PC
										dialogue_flag2++
									BREAK
									CASE 2
										$audio_string    = &RYD2_PC				
										audio_sound_file = SOUND_RYD2_PC
										START_NEW_SCRIPT audio_line ryder 0 1 1 1
										CLEAR_MISSION_AUDIO	2
										LOAD_MISSION_AUDIO 2 SOUND_RYD2_PD
										dialogue_flag2++
									BREAK
									CASE 3
										$audio_string    = &RYD2_PD				
										audio_sound_file = SOUND_RYD2_PD
										START_NEW_SCRIPT audio_line scplayer 0 1 2 1
										CLEAR_MISSION_AUDIO	1
										LOAD_MISSION_AUDIO 1 SOUND_RYD2_PE
										dialogue_flag2++
									BREAK
									CASE 4
										$audio_string    = &RYD2_PE				
										audio_sound_file = SOUND_RYD2_PE
										START_NEW_SCRIPT audio_line scplayer 0 1 1 1
										CLEAR_MISSION_AUDIO	2
										LOAD_MISSION_AUDIO 2 SOUND_RYD2_PF
										dialogue_flag2++
										ryder_get_box_flag++
									BREAK
									CASE 5
										$audio_string    = &RYD2_PF				
										audio_sound_file = SOUND_RYD2_PF
										START_NEW_SCRIPT audio_line ryder 0 1 2 1
										CLEAR_MISSION_AUDIO	1
										LOAD_MISSION_AUDIO 1 SOUND_RYD2_PG
										dialogue_flag2++
									BREAK
									CASE 6
										$audio_string    = &RYD2_PG				
										audio_sound_file = SOUND_RYD2_PG
										START_NEW_SCRIPT audio_line ryder 0 1 1 1
										dialogue_flag2++
									BREAK
									CASE 7
										m_goals++
									BREAK
								ENDSWITCH

								dialogue_timer = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 12
		m_goals++
	ENDIF

	IF m_goals = 13
		PRINT_HELP RY2_72 // press the l3 button to make ryder throw a box. 
		disable_chuck_boxes = 0
		TIMERA = 0
		burst_tyres_flag = 0
		conversation_on_route_back = 0
		chasers_have_bugged_off = 0
		dialogue_flag = 0
		m_goals++
	ENDIF

	IF m_goals = 14
		IF burst_tyres_flag = 0
			IF TIMERA > 10000
				IF NOT IS_CAR_DEAD van
					SET_CAN_BURST_CAR_TYRES van TRUE
					burst_tyres_flag = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD van
			IF IS_CHAR_IN_CAR scplayer van
		   		IF LOCATE_CAR_3D van 2454.6257 -1972.5344 12.5617 4.0 4.0 4.0 TRUE
					IF IS_VEHICLE_ON_ALL_WHEELS van	
						//TASK_CAR_TEMP_ACTION scplayer van TEMPACT_HANDBRAKESTRAIGHT 2000000
						SET_PLAYER_CONTROL player1 OFF

						REMOVE_BLIP location_blip
						m_goals = 99
					ENDIF			
				ENDIF
			ENDIF
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ENTER
			IF NOT IS_CAR_DEAD van
				SET_CAR_COORDINATES van 2454.6257 -1972.5344 12.5617
			ENDIF
		ENDIF

		// make chasers bug off if player is near to destination
		IF chasers_have_bugged_off = 0
			IF NOT IS_CAR_DEAD van
				IF LOCATE_CAR_2D van 2454.6257 -1972.5344 40.0 40.0 FALSE
					IF DOES_VEHICLE_EXIST patriot1
						MARK_CAR_AS_NO_LONGER_NEEDED patriot1
					ENDIF
					IF DOES_CHAR_EXIST driver1
						MARK_CHAR_AS_NO_LONGER_NEEDED driver1
					ENDIF
					IF DOES_VEHICLE_EXIST patriot2
						MARK_CAR_AS_NO_LONGER_NEEDED patriot2
					ENDIF
					IF DOES_CHAR_EXIST driver2
						MARK_CHAR_AS_NO_LONGER_NEEDED driver2
					ENDIF
					chasers_have_bugged_off++
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF TIMERA > 20000
				IF NOT conversation_on_route_back = 99
					IF dialogue_timer > 1000
						IF audio_line_is_active = 0
							IF NOT IS_CHAR_DEAD ryder
								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryder 15.0 15.0 5.0 FALSE
									SWITCH conversation_on_route_back
										CASE 0
											$audio_string    = &RYD2_SA				
											audio_sound_file = SOUND_RYD2_SA
											START_NEW_SCRIPT audio_line scplayer 0 1 1 0
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_RYD2_SB
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 1
											$audio_string    = &RYD2_SB				
											audio_sound_file = SOUND_RYD2_SB
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line scplayer 0 1 2 1
											ELSE
												START_NEW_SCRIPT audio_line scplayer 0 1 2 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 1
											LOAD_MISSION_AUDIO 1 SOUND_RYD2_SC
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 2
											$audio_string    = &RYD2_SC				
											audio_sound_file = SOUND_RYD2_SC
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line ryder 0 1 1 1
											ELSE
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_RYD2_SD
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 3
											$audio_string    = &RYD2_SD				
											audio_sound_file = SOUND_RYD2_SD
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line scplayer 0 1 2 1
											ELSE
												START_NEW_SCRIPT audio_line scplayer 0 1 2 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 1
											LOAD_MISSION_AUDIO 1 SOUND_RYD2_SE
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 4
											$audio_string    = &RYD2_SE				
											audio_sound_file = SOUND_RYD2_SE
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line ryder 0 1 1 1
											ELSE
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_RYD2_SF
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 5
											$audio_string    = &RYD2_SF				
											audio_sound_file = SOUND_RYD2_SF
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line ryder 0 1 2 1
											ELSE
												START_NEW_SCRIPT audio_line ryder 0 1 2 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 1
											LOAD_MISSION_AUDIO 1 SOUND_RYD2_SG
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 6
											$audio_string    = &RYD2_SG				
											audio_sound_file = SOUND_RYD2_SG
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line ryder 0 1 1 1
											ELSE
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_RYD2_SH
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 7
											$audio_string    = &RYD2_SH				
											audio_sound_file = SOUND_RYD2_SH
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line scplayer 0 1 2 1
											ELSE
												START_NEW_SCRIPT audio_line scplayer 0 1 2 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 1
											LOAD_MISSION_AUDIO 1 SOUND_RYD2_SJ
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK
										CASE 8 
											$audio_string    = &RYD2_SJ				
											audio_sound_file = SOUND_RYD2_SJ
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line scplayer 0 1 1 1
											ELSE
												START_NEW_SCRIPT audio_line scplayer 0 1 1 0
												dialogue_flag = 0
											ENDIF
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_RYD2_SK
											conversation_on_route_back++
											dialogue_timer = 0
										BREAK 
										CASE 9
											$audio_string    = &RYD2_SK				
											audio_sound_file = SOUND_RYD2_SK
											IF dialogue_flag = 0
												START_NEW_SCRIPT audio_line ryder 0 1 2 1
											ELSE
												START_NEW_SCRIPT audio_line ryder 0 1 2 0
												dialogue_flag = 0
											ENDIF
											conversation_on_route_back = 99
										BREAK
									ENDSWITCH
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			IF NOT conversation_on_route_back > 0
			AND NOT conversation_on_route_back <= 99

				IF throwing_boxes = 0 
					disable_chuck_boxes = 1
					//PRINT_NOW RY2_54 6000 1 // RYDER - 'Shit! We've ditched too many boxes, we can't afford to ditch any more!!'
					GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
					IF temp_int = 0
						$audio_string    = &RYD2_RA				
						audio_sound_file = SOUND_RYD2_RA
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
						dialogue_flag = 1
					ELSE
						$audio_string    = &RYD2_RB				
						audio_sound_file = SOUND_RYD2_RB
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
						dialogue_flag = 1
					ENDIF
					throwing_boxes--
				ENDIF

			ENDIF
		ENDIF

	ENDIF


	IF m_goals = 99
		IF NOT IS_CAR_DEAD van
			IF IS_CAR_STOPPED van
				m_goals = 0
				m_stage++
			ENDIF
		ENDIF
	ENDIF 

	GOSUB update_van_filling

	// check if fake ryder collision takes damage
	LVAR_INT last_fake_object_health
	IF DOES_OBJECT_EXIST fake_ryder_collision
		GET_OBJECT_HEALTH fake_ryder_collision temp_int
		IF temp_int < last_fake_object_health

				
			IF NOT IS_CHAR_DEAD ryder
				GET_CHAR_HEALTH ryder temp_int
				temp_int += -17
				IF temp_int < 1
					temp_int = 1
				ENDIF
				SET_CHAR_HEALTH ryder temp_int
				IF temp_int = 1
					TASK_DIE ryder
				ENDIF
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS ryder 0.5 0.2 0.4 x y z
				GENERATE_RANDOM_FLOAT_IN_RANGE -1.0 1.0 x2
				GENERATE_RANDOM_FLOAT_IN_RANGE -1.0 1.0 y2
				GENERATE_RANDOM_FLOAT_IN_RANGE -1.0 1.0 z2
				IF DOES_CHAR_EXIST ryder
					IF NOT IS_CHAR_DEAD ryder
						ADD_BLOOD x y z x2 y2 z2 20 ryder
					ENDIF
				ENDIF

			ENDIF


		ENDIF
		CLEAR_OBJECT_LAST_WEAPON_DAMAGE fake_ryder_collision
		GET_OBJECT_HEALTH fake_ryder_collision last_fake_object_health 	
	ENDIF 


	// ryder behaviour for van
	IF ryder_is_attached_to_van = 1

		IF disable_chuck_boxes = 0
			// box picking up and throwing
			IF NOT IS_CAR_DEAD van
				IF r3_is_pressed = 0
					IF IS_BUTTON_PRESSED PAD1 LEFTSHOCK 
						r3_is_pressed = 1
						IF ryder_get_box_flag = 0
						OR ryder_get_box_flag = 3
							ryder_get_box_flag++
						ENDIF
					ENDIF
				ELSE
					IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOCK
						r3_is_pressed = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// set char heading and get roll
		IF NOT IS_CAR_DEAD van
			GET_CAR_HEADING van heading
			IF NOT IS_CHAR_DEAD ryder
				heading += 180.0
				SET_CHAR_HEADING ryder heading
			ENDIF
			GET_CAR_ROLL van van_roll
		ENDIF

		IF NOT IS_CAR_DEAD van
			GET_CAR_HEADING van van_this_heading
		ENDIF
		van_diff_heading = van_this_heading - van_last_heading
		IF van_diff_heading < -180.0
			van_diff_heading += 360.0
		ENDIF
		IF van_diff_heading > 180.0
			van_diff_heading += -360.0
		ENDIF
		van_last_heading = van_this_heading



		IF NOT IS_CHAR_DEAD ryder
							
			IF ryder_get_box_flag = 0

				IF van_roll < -60.0
					IF NOT ryder_anim_flag = 1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Fall_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
						ryder_anim_flag = 1	
					ENDIF
				ELSE
					IF van_roll > 60.0
						IF NOT ryder_anim_flag = 2
							TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Fall_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
							ryder_anim_flag = 2	
						ENDIF
					ELSE
						IF van_roll < -5.0 
							IF NOT ryder_anim_flag = 3
								TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Lean_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
								ryder_anim_flag = 3 
							ENDIF
						ELSE
							IF van_roll > 5.0 
								IF NOT ryder_anim_flag = 4
									TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Lean_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
									ryder_anim_flag = 4
								ENDIF
							ELSE
								IF van_diff_heading < -2.0
									IF NOT ryder_anim_flag = 4
										TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Lean_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
										ryder_anim_flag = 4 
									ENDIF
								ELSE
									IF van_diff_heading > 2.0
										IF NOT ryder_anim_flag = 3
											TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Lean_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
											ryder_anim_flag = 3 
										ENDIF
									ELSE
										IF NOT ryder_anim_flag = 0
											TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_stand RYDER 4.0 FALSE FALSE FALSE TRUE -1
											ryder_anim_flag = 0
										ENDIF
									ENDIF
								ENDIF		
							ENDIF					
						ENDIF
					ENDIF

				ENDIF

			ELSE

				// go to pickup box
				IF ryder_get_box_flag = 1
					IF NOT ryder_anim_flag = 5
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_PickUp_S RYDER 1000.0 FALSE FALSE FALSE FALSE -1
						ryder_anim_flag = 5
					ELSE
						GET_SCRIPT_TASK_STATUS ryder TASK_PLAY_ANIM_NON_INTERRUPTABLE temp_int
						IF temp_int = FINISHED_TASK
							// attach box
							GET_CHAR_COORDINATES ryder x y z
							CREATE_OBJECT CM_BOX x y z throwing_box
							TASK_PICK_UP_OBJECT ryder throwing_box -0.096 0.246 0.142  PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE
							SET_OBJECT_COLLISION throwing_box FALSE
							shown_boxes--
							throwing_boxes--
							ryder_get_box_flag++
						ENDIF
					ENDIF
				ENDIF
			
				// wait to finish picking up box
				IF ryder_get_box_flag = 2
					IF NOT ryder_anim_flag = 6
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_PickUp_E RYDER 1000.0 FALSE FALSE FALSE FALSE -1
						ryder_anim_flag = 6
					ELSE
						GET_SCRIPT_TASK_STATUS ryder TASK_PLAY_ANIM_NON_INTERRUPTABLE temp_int
						IF temp_int = FINISHED_TASK
							ryder_get_box_flag++
						ENDIF
					ENDIF
				ENDIF

				// lean with box
				IF ryder_get_box_flag = 3
					IF van_roll < -60.0
						IF NOT ryder_anim_flag = 1

							GOSUB ry2_throw_box
									
							TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Fall_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
							ryder_anim_flag = 1	
							ryder_get_box_flag = 0
						ENDIF
					ELSE
						IF van_roll > 60.0
							IF NOT ryder_anim_flag = 2
							
								GOSUB ry2_throw_box

								TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Fall_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
								ryder_anim_flag = 2	
								ryder_get_box_flag = 0
							ENDIF
						ELSE
							IF van_roll < -5.0 
								IF NOT ryder_anim_flag = 7
									TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Crate_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
									ryder_anim_flag = 7 
								ENDIF
							ELSE
								IF van_roll > 5.0 
									IF NOT ryder_anim_flag = 8
										TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Crate_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
										ryder_anim_flag = 8
									ENDIF
								ELSE
									IF van_diff_heading < -2.0
										IF NOT ryder_anim_flag = 7
											TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Crate_R RYDER 4.0 FALSE FALSE FALSE TRUE -1
											ryder_anim_flag = 7 
										ENDIF
									ELSE
										IF van_diff_heading > 2.0
											IF NOT ryder_anim_flag = 8
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_Crate_L RYDER 4.0 FALSE FALSE FALSE TRUE -1
												ryder_anim_flag = 8 
											ENDIF
										ELSE
											IF NOT ryder_anim_flag = 9
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder Van_Stand_Crate RYDER 4.0 FALSE FALSE FALSE TRUE -1
												ryder_anim_flag = 9
											ENDIF
										ENDIF
									ENDIF		
								ENDIF					
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				// throw box
				IF ryder_get_box_flag = 4
					IF NOT ryder_anim_flag = 10
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder Van_Throw RYDER 1000.0 FALSE FALSE FALSE FALSE -1
						ryder_anim_flag = 10
					ELSE
						IF IS_CHAR_PLAYING_ANIM ryder Van_Throw 
							GET_CHAR_ANIM_CURRENT_TIME ryder Van_Throw temp_float
							IF temp_float > 0.292
								GOSUB ry2_throw_box
								ryder_get_box_flag++
							ENDIF
						ENDIF 
					ENDIF	
				ENDIF

				// wait to finish throwing box
				IF ryder_get_box_flag = 5
					GET_SCRIPT_TASK_STATUS ryder TASK_PLAY_ANIM_NON_INTERRUPTABLE temp_int
					IF temp_int = FINISHED_TASK
						IF NOT ryder_anim_flag = 0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder VAN_stand RYDER 1000.0 FALSE FALSE FALSE TRUE -1
							ryder_anim_flag = 0
						ENDIF
						IF throwing_boxes > 0
							ryder_get_box_flag = 1
						ELSE		
							ryder_get_box_flag = 0
							disable_chuck_boxes = 1
						ENDIF
					ENDIF
				ENDIF

			ENDIF


		ENDIF						  
	ENDIF	   

	// if ryders health is zero - detach and die
	IF NOT ryder_is_attached_to_van = 0
		IF NOT IS_CHAR_DEAD ryder
			GET_CHAR_HEALTH ryder temp_int
			IF temp_int < 100
				DETACH_CHAR_FROM_CAR ryder
				TASK_DIE ryder
				ryder_is_attached_to_van = 0
			ENDIF
		ENDIF
	ENDIF

	// make exploding boxes explode
	temp_int = 0
	WHILE temp_int < 5
		IF DOES_OBJECT_EXIST exploding_box[temp_int]
			IF DOES_OBJECT_EXIST exploding_box[temp_int]
				IF NOT IS_CAR_DEAD patriot1 
					IF IS_VEHICLE_TOUCHING_OBJECT patriot1 exploding_box[temp_int]
						GET_OBJECT_COORDINATES exploding_box[temp_int] x y z
						DELETE_OBJECT exploding_box[temp_int]
						ADD_EXPLOSION X Y Z EXPLOSION_SMALL	
					ENDIF 
				ENDIF
			ENDIF
			IF DOES_OBJECT_EXIST exploding_box[temp_int]
				IF NOT IS_CAR_DEAD patriot2 
					IF IS_VEHICLE_TOUCHING_OBJECT patriot2 exploding_box[temp_int]
						GET_OBJECT_COORDINATES exploding_box[temp_int] x y z
						DELETE_OBJECT exploding_box[temp_int]
						ADD_EXPLOSION X Y Z EXPLOSION_SMALL	
					ENDIF 
				ENDIF
			ENDIF
			
			IF DOES_OBJECT_EXIST exploding_box[temp_int]
				GET_OBJECT_HEALTH exploding_box[temp_int] temp_int2
				IF temp_int2 < 950
					IF NOT IS_CAR_DEAD van
						GET_OBJECT_COORDINATES exploding_box[temp_int] x y z
						DELETE_OBJECT exploding_box[temp_int]
						ADD_EXPLOSION X Y Z EXPLOSION_GRENADE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	temp_int++
	ENDWHILE

	// give van a blip if the player gets out
	IF m_goals > 4
		IF NOT IS_CAR_DEAD van
			IF IS_CHAR_IN_CAR scplayer van
				IF DOES_BLIP_EXIST van_blip
					REMOVE_BLIP van_blip
				ENDIF
				IF NOT DOES_BLIP_EXIST location_blip
					ADD_BLIP_FOR_COORD 2454.6257 -1972.5344 12.5617 location_blip
				ENDIF
			ELSE
				IF NOT DOES_BLIP_EXIST van_blip
					ADD_BLIP_FOR_CAR van van_blip
					SET_BLIP_AS_FRIENDLY van_blip TRUE
				ENDIF
				IF DOES_BLIP_EXIST location_blip
					REMOVE_BLIP location_blip
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// check if player has left the van
	IF m_goals >= 13
		IF NOT IS_CAR_DEAD van
			IF NOT IS_CHAR_IN_CAR scplayer van
				IF NOT IS_MESSAGE_BEING_DISPLAYED
				AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					IF NOT conversation_on_route_back > 0
					AND NOT conversation_on_route_back <= 99
					
						IF dialogue_timer > 15000
							IF audio_line_is_active = 0
								IF NOT IS_CHAR_DEAD ryder 
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryder 5.0 5.0 5.0 FALSE								
										GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
										SWITCH temp_int
											CASE 0
												$audio_string    = &RYD2_QA				
												audio_sound_file = SOUND_RYD2_QA
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 1	
											BREAK
											CASE 1
												$audio_string    = &RYD2_QB				
												audio_sound_file = SOUND_RYD2_QB
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 1
											BREAK
											CASE 2
												$audio_string    = &RYD2_QC				
												audio_sound_file = SOUND_RYD2_QC
												START_NEW_SCRIPT audio_line ryder 0 1 1 0
												dialogue_flag = 1	
											BREAK
										ENDSWITCH
										dialogue_timer = 0
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF
				ENDIF
 			ENDIF
		ENDIF
	ENDIF


	IF frame_num = 4
		IF DOES_VEHICLE_EXIST patriot1
			IF NOT IS_CAR_DEAD patriot1	 
				
				// driver AI
				IF NOT IS_CHAR_DEAD driver1
					IF NOT IS_CHAR_IN_CAR driver1 patriot1
						GET_SCRIPT_TASK_STATUS driver1 TASK_KILL_CHAR_ON_FOOT temp_int
						IF temp_int = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT driver1 scplayer
						ENDIF
					ENDIF
				ENDIF

			 	// passenger AI
				IF NOT IS_CHAR_DEAD passenger1
					IF IS_CHAR_IN_CAR passenger1 patriot1
						IF NOT IS_CAR_DEAD van
							IF IS_CHAR_IN_CAR scplayer van
								GET_CAR_SPEED van temp_float
								IF temp_float < 5.0
									IF NOT IS_CHAR_DEAD ryder
										IF LOCATE_CHAR_ANY_MEANS_CHAR_3D passenger1 ryder 40.0 40.0 20.0 FALSE
											GET_SCRIPT_TASK_STATUS passenger1 TASK_DRIVE_BY temp_int
											IF temp_int = FINISHED_TASK
												TASK_DRIVE_BY passenger1 -1 van 0.0 0.0 0.0 100.0 DRIVEBY_AI_ALL_DIRN TRUE 40	
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ELSE
								IF NOT IS_CHAR_IN_CAR scplayer patriot1
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D passenger1 scplayer 40.0 40.0 20.0 FALSE
										GET_SCRIPT_TASK_STATUS passenger1 TASK_DRIVE_BY temp_int
										IF temp_int = FINISHED_TASK
											TASK_DRIVE_BY passenger1 scplayer -1 0.0 0.0 0.0 100.0 DRIVEBY_AI_ALL_DIRN TRUE 40	
										ENDIF
									ENDIF
								ELSE
									GET_SCRIPT_TASK_STATUS passenger1 TASK_KILL_CHAR_ON_FOOT temp_int
									IF temp_int = FINISHED_TASK
										TASK_KILL_CHAR_ON_FOOT passenger1 scplayer
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS passenger1 TASK_KILL_CHAR_ON_FOOT temp_int
						IF temp_int = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT passenger1 scplayer
						ENDIF	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	IF frame_num = 8
		IF DOES_VEHICLE_EXIST patriot2
			IF NOT IS_CAR_DEAD patriot2	  

				// driver AI
				IF NOT IS_CHAR_DEAD driver2
					IF NOT IS_CHAR_IN_CAR driver2 patriot2
						GET_SCRIPT_TASK_STATUS driver2 TASK_KILL_CHAR_ON_FOOT temp_int
						IF temp_int = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT driver2 scplayer
						ENDIF
					ENDIF
				ENDIF

				// passenger AI
				IF NOT IS_CHAR_DEAD passenger2
					IF IS_CHAR_IN_CAR passenger2 patriot2
						IF NOT IS_CAR_DEAD van
							IF IS_CHAR_IN_CAR scplayer van
								GET_CAR_SPEED van temp_float
								IF temp_float < 5.0
									IF NOT IS_CHAR_DEAD ryder
										IF LOCATE_CHAR_ANY_MEANS_CHAR_3D passenger2 ryder 40.0 40.0 20.0 FALSE
											GET_SCRIPT_TASK_STATUS passenger2 TASK_DRIVE_BY temp_int
											IF temp_int = FINISHED_TASK
												TASK_DRIVE_BY passenger2 -1 van 0.0 0.0 0.0 100.0 DRIVEBY_AI_ALL_DIRN TRUE 60	
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ELSE
								IF NOT IS_CHAR_IN_CAR scplayer patriot2
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D passenger2 scplayer 40.0 40.0 20.0 FALSE
										GET_SCRIPT_TASK_STATUS passenger2 TASK_DRIVE_BY temp_int
										IF temp_int = FINISHED_TASK
											TASK_DRIVE_BY passenger2 scplayer -1 0.0 0.0 0.0 100.0 DRIVEBY_AI_ALL_DIRN TRUE 60	
										ENDIF
									ENDIF
								ELSE
									GET_SCRIPT_TASK_STATUS passenger2 TASK_KILL_CHAR_ON_FOOT temp_int
									IF temp_int = FINISHED_TASK
										TASK_KILL_CHAR_ON_FOOT passenger2 scplayer
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS passenger2 TASK_KILL_CHAR_ON_FOOT temp_int
						IF temp_int = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT passenger2 scplayer
						ENDIF	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// warp patriots close to van if they've fallen behind
	IF frame_num = 0
	AND m_goals > 10
		IF DOES_VEHICLE_EXIST patriot1
			IF NOT IS_CAR_DEAD patriot1
				IF NOT IS_CAR_DEAD van
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS van 0.0 -20.0 0.0 x y z
					IF NOT LOCATE_CAR_2D patriot1 x y 40.0 40.0 FALSE
						IF NOT IS_CAR_ON_SCREEN patriot1
							temp_int = 1
							temp_int2 = 0
							WHILE temp_int2 = 0
								GET_NTH_CLOSEST_CAR_NODE x y z temp_int x2 y2 z2
								IF NOT IS_POINT_ON_SCREEN x2 y2 z2 5.0
								AND NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x2 y2 z2 5.0 5.0 5.0
									GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
									IF temp_float > 30.0
										temp_int2 = -1
									ELSE
										temp_int2 = 1
									ENDIF
								ELSE
									temp_int++
									IF temp_int > 10
										temp_int2 = -1
									ENDIF
								ENDIF
							ENDWHILE
							// warp
							IF temp_int2 = 1
								GET_CAR_COORDINATES van x y z
								vec_x = x - x2
								vec_y = y - y2
								GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
								CLEAR_AREA x2 y2 z2 5.0 TRUE
								SET_CAR_COORDINATES patriot1 x2 y2 z2
								SET_CAR_HEADING patriot1 heading
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF						  

	// warp patriots close to van if they've fallen behind
	IF frame_num = 1
	AND m_goals > 10
		IF DOES_VEHICLE_EXIST patriot2
			IF NOT IS_CAR_DEAD patriot2
				IF NOT IS_CAR_DEAD van
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS van 0.0 -20.0 0.0 x y z
					IF NOT LOCATE_CAR_2D patriot2 x y 40.0 40.0 FALSE
						IF NOT IS_CAR_ON_SCREEN patriot2
							temp_int = 1
							temp_int2 = 0
							WHILE temp_int2 = 0
								GET_NTH_CLOSEST_CAR_NODE x y z temp_int x2 y2 z2
								IF NOT IS_POINT_ON_SCREEN x2 y2 z2 5.0
								AND NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x2 y2 z2 5.0 5.0 5.0 
									GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
									IF temp_float > 30.0
										temp_int2 = -1
									ELSE
										temp_int2 = 1
									ENDIF
								ELSE
									temp_int++
									IF temp_int > 10
										temp_int2 = -1
									ENDIF
								ENDIF
							ENDWHILE
							// warp
							IF temp_int2 = 1
								GET_CAR_COORDINATES van x y z
								vec_x = x - x2
								vec_y = y - y2
								GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
								CLEAR_AREA x2 y2 z2 5.0 TRUE
								SET_CAR_COORDINATES patriot2 x2 y2 z2
								SET_CAR_HEADING patriot2 heading
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	

RETURN

// **************************************************************************************************************
//										STAGE 7 - end cutscene
// **************************************************************************************************************
ryder2_m_stage_7:

	IF m_goals = 0
		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		REQUEST_MODEL FAM1
		REQUEST_MODEL FAM2
		WHILE NOT HAS_MODEL_LOADED FAM1
		   OR NOT HAS_MODEL_LOADED FAM2
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD van
			SET_CAR_COORDINATES van	2455.7078 -1972.8582 12.5469 
			SET_CAR_HEADING van 343.4514
		ENDIF

		SWITCH_WIDESCREEN ON
		SET_NEAR_CLIP 0.1

		IF NOT IS_CHAR_DEAD ryder
			CLEAR_CHAR_TASKS ryder
		ENDIF
		
		CLEAR_AREA 2447.9011 -1980.5422 12.8338 20.0 TRUE

		SET_FIXED_CAMERA_POSITION 2447.9011 -1980.5422 12.8338 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2448.4839 -1979.7354 12.9290 JUMP_CUT


		IF NOT IS_CHAR_DEAD driver1
			DELETE_CHAR driver1
		ENDIF
		IF NOT IS_CHAR_DEAD driver2
			DELETE_CHAR driver2
		ENDIF
		IF NOT IS_CAR_DEAD patriot1
			DELETE_CAR patriot1	
		ENDIF
		IF NOT IS_CAR_DEAD patriot2
			DELETE_CAR patriot2
		ENDIF	
		
		CREATE_CHAR PEDTYPE_CIVMALE FAM1 2448.4761 -1980.7510 12.5947 end_cut_ped1
		CREATE_CHAR PEDTYPE_CIVMALE FAM2 2447.5532 -1979.8827 12.5947 end_cut_ped2
		SET_CHAR_HEADING end_cut_ped1 316.4611
		SET_CHAR_HEADING end_cut_ped2 319.2614
		SET_CHAR_DECISION_MAKER end_cut_ped1 empty_dm
		SET_CHAR_DECISION_MAKER end_cut_ped2 empty_dm
		MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
		MARK_MODEL_AS_NO_LONGER_NEEDED FAM2

		IF NOT IS_CHAR_DEAD ryder 
			DETACH_CHAR_FROM_CAR ryder
			CLEAR_CHAR_TASKS_IMMEDIATELY ryder
			IF DOES_OBJECT_EXIST throwing_box
				DELETE_OBJECT throwing_box
			ENDIF
			SET_CHAR_COORDINATES ryder 2453.0017 -1976.5757 12.5469 
			SET_CHAR_HEADING ryder 5.1526 
			SET_ANIM_GROUP_FOR_CHAR ryder gang1
			OPEN_SEQUENCE_TASK temp_seq
				TASK_GO_STRAIGHT_TO_COORD -1 2452.7024 -1972.9393 12.5469 PEDMOVE_WALK 10000
				TASK_LOOK_AT_CHAR -1 scplayer 3000				
			CLOSE_SEQUENCE_TASK temp_seq
			PERFORM_SEQUENCE_TASK ryder temp_seq
			CLEAR_SEQUENCE_TASK temp_seq
		ENDIF
		
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer 2452.2900 -1971.6825 12.5469
		SET_CHAR_HEADING scplayer 153.4741


		IF NOT IS_CHAR_DEAD end_cut_ped1
			TASK_LOOK_AT_CHAR scplayer end_cut_ped1 4000
		ENDIF

//		IF NOT IS_CHAR_DEAD ryder
//			OPEN_SEQUENCE_TASK temp_seq
//				TASK_LOOK_AT_CHAR -1 end_cut_ped1 5000
//				TASK_TURN_CHAR_TO_FACE_CHAR -1 end_cut_ped1 
//			CLOSE_SEQUENCE_TASK temp_seq
//			PERFORM_SEQUENCE_TASK ryder temp_seq
//			CLEAR_SEQUENCE_TASK temp_seq
//		ENDIF
		
//		TASK_GO_STRAIGHT_TO_COORD end_cut_ped1 2451.9153 -1977.5061 12.5947	PEDMOVE_WALK 10000
//		TASK_GO_STRAIGHT_TO_COORD end_cut_ped2 2450.5459 -1976.3612 12.5947	PEDMOVE_WALK 10000
		
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		audio_line_is_active = 0

		CLEAR_PRINTS
		dialogue_flag = 0
		dialogue_timer = 1000
		TIMERA = 0

		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		m_goals++
	ENDIF


	IF m_goals > 0
		IF audio_line_is_active = 0
			IF dialogue_timer  > 1000
				SWITCH dialogue_flag
					CASE 0
						$audio_string    = &RYD2_TA				
						audio_sound_file = SOUND_RYD2_TA
						START_NEW_SCRIPT audio_line ryder 0 1 1 0
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 2 SOUND_RYD2_TB	
						dialogue_flag++
						dialogue_timer = 0

						IF NOT IS_CHAR_DEAD end_cut_ped1
							TASK_GO_STRAIGHT_TO_COORD end_cut_ped1 2451.9153 -1977.5061 12.5947	PEDMOVE_WALK 10000
						ENDIF
						IF NOT IS_CHAR_DEAD end_cut_ped2 
							TASK_GO_STRAIGHT_TO_COORD end_cut_ped2 2450.5459 -1976.3612 12.5947	PEDMOVE_WALK 10000
						ENDIF

					BREAK
					CASE 1
						$audio_string    = &RYD2_TB				
						audio_sound_file = SOUND_RYD2_TB
						START_NEW_SCRIPT audio_line scplayer 0 1 2 1
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_RYD2_TC
						dialogue_flag++
						dialogue_timer = 0
				
					BREAK
					CASE 2
						
						SET_FIXED_CAMERA_POSITION 2450.0459 -1966.4122 15.1296 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2450.5613 -1967.2509 14.9539 JUMP_CUT

						$audio_string    = &RYD2_TC				
						audio_sound_file = SOUND_RYD2_TC
						START_NEW_SCRIPT audio_line ryder 0 1 1 1
						//CLEAR_MISSION_AUDIO 2
						//LOAD_MISSION_AUDIO 2 SOUND_RYD2_TD
						dialogue_flag++
						dialogue_timer = 0

						IF NOT IS_CHAR_DEAD ryder
							OPEN_SEQUENCE_TASK temp_seq
//								TASK_PAUSE -1 1000
//								TASK_PLAY_ANIM -1 fucku PED 4.0 FALSE TRUE TRUE FALSE -1
								TASK_PLAY_ANIM -1 idle_chat ped 4.0 FALSE TRUE TRUE FALSE 2000
								TASK_GO_STRAIGHT_TO_COORD -1	2452.2388 -1980.2244 12.5469 PEDMOVE_WALK 10000
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK ryder temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
						ENDIF

					BREAK
					CASE 3
						//$audio_string    = &RYD2_TD				
						//audio_sound_file = SOUND_RYD2_TD
						//START_NEW_SCRIPT audio_line ryder 0 1 2 1

						
						IF NOT IS_CHAR_DEAD end_cut_ped1
							OPEN_SEQUENCE_TASK temp_seq
								TASK_PAUSE -1 2000
								//TASK_PLAY_ANIM -1 TURN_180 PED FALSE TRUE TRUE FALSE -1
								TASK_GO_STRAIGHT_TO_COORD -1 2452.9514 -1982.9205 12.5469	PEDMOVE_WALK 10000
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK end_cut_ped1 temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
						ENDIF
						IF NOT IS_CHAR_DEAD end_cut_ped2 
							OPEN_SEQUENCE_TASK temp_seq
								TASK_PAUSE -1 1500
								//TASK_PLAY_ANIM -1 TURN_180 PED FALSE TRUE TRUE FALSE -1
								TASK_GO_STRAIGHT_TO_COORD -1 2452.0056 -1983.1857 12.5469	PEDMOVE_WALK 10000
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK end_cut_ped2 temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
						ENDIF


						dialogue_flag++
						dialogue_timer = 0
					BREAK
				ENDSWITCH
			ENDIF
		ENDIF
	ENDIF

	IF m_goals = 1
		IF dialogue_flag > 3 
		AND dialogue_timer > 5000
		AND audio_line_is_active = 0
			IF NOT IS_MESSAGE_BEING_DISPLAYED
			AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				m_goals++
			ENDIF
		ELSE
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				m_goals++
			ENDIF		 
		ENDIF
	ENDIF

	IF m_goals = 2
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		DELETE_CHAR end_cut_ped1
		DELETE_CHAR end_cut_ped2
		DELETE_CHAR ryder
//		IF NOT IS_CAR_DEAD van
//			IF IS_CHAR_IN_CAR scplayer van
//				WARP_CHAR_FROM_CAR_TO_COORD scplayer 2457.4285 -1973.7041 12.5984
//				SET_CHAR_HEADING scplayer 0.0
//				DELETE_CAR van
//			ENDIF
//		ENDIF
		
		temp_int = 0
		WHILE temp_int < 5
			IF DOES_OBJECT_EXIST displayed_boxes_in_van[temp_int] 
				DELETE_OBJECT displayed_boxes_in_van[temp_int]
			ENDIF
		temp_int++
		ENDWHILE

		IF DOES_OBJECT_EXIST hide_anim_box
			DELETE_OBJECT hide_anim_box
		ENDIF

		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		
		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER 
		RESTORE_CAMERA_JUMPCUT
		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SET_PLAYER_CONTROL player1 ON
		m_passed = 1
	ENDIF

RETURN


// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
ryder2_global_functions:

	//  fail conditions
	IF DOES_CHAR_EXIST ryder
		IF IS_CHAR_DEAD ryder
			m_failed = 1
			PRINT_NOW RY2_22 5000 1 		// ryder is history
		ENDIF
	ENDIF
	IF DOES_VEHICLE_EXIST van
		IF IS_CAR_DEAD van		
			IF ryder_is_attached_to_van = 1
				IF NOT IS_CHAR_DEAD ryder 
					TASK_DIE ryder
				ENDIF
			ENDIF
			m_failed = 1
			PRINT_NOW RY2_25 5000 1 		// van is history
		ENDIF
	ENDIF

	IF m_stage < 6
		IF DOES_VEHICLE_EXIST fork
			IF IS_CAR_DEAD fork
				m_failed = 1
				PRINT_NOW RY2_40 5000 1 		// forklift truck is history
			ELSE
				IF IS_CAR_IN_WATER fork
					 m_failed = 1
					 PRINT_NOW RY2_40 5000 1 	// forklift truck is history
				ENDIF
			ENDIF
		ENDIF
	ENDIF



	// count available boxes
	IF m_stage > 2
	AND m_stage < 6
		IF frame_num = 6
			temp_int = 0
			temp_int2 = 0
			WHILE temp_int < 25
				IF DOES_OBJECT_EXIST m_box[temp_int]
					IF NOT IS_OBJECT_IN_WATER m_box[temp_int]
						GET_OBJECT_COORDINATES m_box[temp_int] x y z
						IF z > 0.0
							temp_int2++
						ENDIF
						//WRITE_DEBUG_WITH_INT BOX_NOT_IN_WATER temp_int	
					ELSE
						//WRITE_DEBUG_WITH_INT BOX_IN_WATER temp_int
					ENDIF
				ENDIF  
				temp_int++
			ENDWHILE

			IF temp_int2 < loaded_boxes_neg
				GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
				IF temp_int = 0
					$audio_string    = &RYD2_LF					  
					audio_sound_file = SOUND_RYD2_LF
					START_NEW_SCRIPT audio_line ryder 0 1 1 0
				ELSE
					$audio_string    = &RYD2_LE					  
					audio_sound_file = SOUND_RYD2_LE
					START_NEW_SCRIPT audio_line ryder 0 1 1 0
				ENDIF
				WAIT 4000	
				PRINT_NOW RY2_58 5000 1 // too many boxes were destroyed!
				m_failed = 1
			ENDIF
		ENDIF
	ENDIF



	// switches
	IF m_stage >= 3

		IF gate1_opened = 0
			IF DOES_OBJECT_EXIST switch1
				IF HAS_OBJECT_BEEN_DAMAGED switch1
					gate1_opened++
					CLEAR_PRINTS
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT switch1 SOUND_SHOOT_CONTROLS
					IF DOES_OBJECT_EXIST gate1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate1 SOUND_HEAVY_GATE_START
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF gate1_opened = 1
				IF DOES_OBJECT_EXIST gate1
					IF SLIDE_OBJECT gate1 gate1_target_x gate1_target_y gate1_target_z 0.0 0.2 0.0 FALSE
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate1 SOUND_HEAVY_GATE_STOP
						gate1_opened++
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		 
		IF warehouse_opened = 0
			IF DOES_OBJECT_EXIST switch2
				IF HAS_OBJECT_BEEN_DAMAGED switch2

					IF DOES_OBJECT_EXIST back_door[0]
						DELETE_OBJECT back_door[0]
					ENDIF
					IF DOES_OBJECT_EXIST back_door[1]
						DELETE_OBJECT back_door[1]
					ENDIF

					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT switch2 SOUND_SHOOT_CONTROLS

					IF DOES_OBJECT_EXIST gate2
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_START
					ENDIF

					CLEAR_PRINTS
					
					warehouse_opened++
				ENDIF
			ENDIF	
		ELSE
			IF warehouse_opened = 1
				IF DOES_OBJECT_EXIST gate2
					IF SLIDE_OBJECT gate2 gate2_target_x gate2_target_y gate2_target_z 0.1 0.1 0.1 FALSE
						
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT gate2 SOUND_SHUTTER_DOOR_STOP
						
						IF NOT gate1_opened = 0
							REMOVE_BLIP location_blip
						ENDIF					
						warehouse_opened++
						gate_reminder_timer = 30000
					ENDIF
				ENDIF
		 	ENDIF
		ENDIF
	ENDIF


	// Check if player is in complex ----------------------------------------------------------------------
	IF frame_num = 0
		IF IS_CHAR_IN_AREA_2D scplayer 2721.0 -2329.8518 2810.5479 -2565.9336 FALSE
			IF player_in_complex = 0
				player_in_complex = 1
			ENDIF 
		ELSE
			player_in_complex = 0	
		ENDIF
	ENDIF


	// loaded boxes --------------------------------------------------------------------
	VAR_INT loaded_boxes_neg
	loaded_boxes_neg = required_boxes - loaded_boxes
	IF loaded_boxes_neg	< 0
		loaded_boxes_neg = 0
	ENDIF

	// make ryder shoot from van if alarm has gone off ---------------------------------
	IF alarm_raised = 1
		IF ryder_got_driveby = 0
			IF NOT IS_CHAR_DEAD ryder
				SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
				TASK_TOGGLE_PED_THREAT_SCANNER ryder 0 1 0
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ryder_dm EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
				ryder_got_driveby = 1
			ENDIF
		ENDIF
	ENDIF

	// display ryders health once he's taken damage
	IF NOT IS_CHAR_DEAD ryder
		GET_CHAR_HEALTH ryder ryder_health
		temp_float =# ryder_health
		temp_float /= 500.0
		temp_float *= 100.0	
		IF temp_float > 100.0
			temp_float = 100.0
		ENDIF
		ryder_health =# temp_float	
	ENDIF



////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
//																									  //
//										AI															  //
//																									  //
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////


	// before alarm is raised guards will question player ------------------------------
	IF alarm_raised = 0
		IF frame_num = 1

			temp_int = 0
			WHILE temp_int < 5
				IF NOT IS_CHAR_DEAD m_guard[temp_int]
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR m_guard[temp_int] scplayer
					OR IS_PLAYER_TARGETTING_CHAR player1 m_guard[temp_int]
						alarm_raised = 1
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE

			// if any of the guards see the player or buddy and they don't have uniform - set off alarm
			IF alarm_raised = 0
				temp_int = 0
				WHILE temp_int < 5

					IF NOT IS_CHAR_DEAD m_guard[temp_int]
						
						IF m_guard_flag[temp_int] = 0
							IF player_in_complex = 1 
								IF HAS_CHAR_SPOTTED_CHAR m_guard[temp_int] scplayer
									TASK_AIM_GUN_AT_CHAR m_guard[temp_int] scplayer 10000

									LVAR_INT warning_flag
									IF warning_flag = 0
										$audio_string    = &RYD2_VA
										audio_sound_file = SOUND_RYD2_VA
										START_NEW_SCRIPT audio_line m_guard[temp_int] 0 1 1 0
										warning_flag ++
									ENDIF

									m_guard_timer[temp_int] = 0
									m_guard_flag[temp_int]++
								ELSE
									IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer m_guard[temp_int] 5.0 5.0 2.0 FALSE
										GET_SCRIPT_TASK_STATUS m_guard[temp_int] TASK_TURN_CHAR_TO_FACE_CHAR temp_int2
										IF temp_int2 = FINISHED_TASK
											TASK_TURN_CHAR_TO_FACE_CHAR m_guard[temp_int] scplayer	
										ENDIF
									ENDIF	
								ENDIF 
							ENDIF
						ENDIF

						IF m_guard_flag[temp_int] = 1
							IF m_guard_timer[temp_int] > 8000
								IF player_in_complex = 1
									//PRINT_NOW RY2_G2 4000 1 // sound the alarm!
									
									GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int2
									IF temp_int = 0
										$audio_string    = &RYD2_VB
										audio_sound_file = SOUND_RYD2_VB
										START_NEW_SCRIPT audio_line m_guard[temp_int] 0 1 1 0
									ELSE
										$audio_string    = &RYD2_VG
										audio_sound_file = SOUND_RYD2_VG
										START_NEW_SCRIPT audio_line m_guard[temp_int] 0 1 1 0
									ENDIF

									alarm_raised = 1
									temp_int = 5
	  							ENDIF
							ELSE
								IF player_in_complex = 0
									m_guard_flag[temp_int] = 0
									warning_flag = 0
								ENDIF 	
							ENDIF	
						ENDIF

					ENDIF


				temp_int++
				ENDWHILE
			ENDIF

			// if alarm is raised reset flags
			IF alarm_raised = 1
				temp_int = 0
				WHILE temp_int < 9
					m_guard_flag[temp_int] = 0	
				temp_int++
				ENDWHILE
			ENDIF

		ENDIF
	ELSE 
	
		// ai if alarm has gone off - guard behaviour
		IF frame_num < 9
			IF NOT IS_CHAR_DEAD m_guard[frame_num]	 
				
				SWITCH frame_num 

					CASE 1
						IF m_guard_flag[frame_num] = 0
							OPEN_SEQUENCE_TASK temp_seq
								TASK_STAY_IN_SAME_PLACE -1 TRUE
								TASK_KILL_CHAR_ON_FOOT -1 scplayer
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK m_guard[frame_num] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							m_guard_flag[frame_num]++
						ENDIF	
					BREAK
					CASE 2
						IF warehouse_opened = 1
							IF m_guard_flag[2] = 0
								SET_CHAR_RELATIONSHIP m_guard[2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
								SET_CHAR_RELATIONSHIP m_guard[2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 
								TASK_TOGGLE_PED_THREAT_SCANNER m_guard[2] FALSE FALSE FALSE
								OPEN_SEQUENCE_TASK temp_seq
									TASK_GO_STRAIGHT_TO_COORD -1 2785.7522 -2413.0188 12.6316 PEDMOVE_RUN -2
									TASK_SET_CHAR_DECISION_MAKER -1 tough_dm
									TASK_TOGGLE_PED_THREAT_SCANNER -1 TRUE TRUE FALSE
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									TASK_KILL_CHAR_ON_FOOT -1 scplayer
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK m_guard[2] temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
								m_guard_flag[2]++
							ENDIF
							IF m_guard_flag[2] = 1
								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D m_guard[2] scplayer 5.0 5.0 5.0 FALSE
									TASK_KILL_CHAR_ON_FOOT m_guard[2] scplayer
									m_guard_flag[2]++
								ENDIF
							ENDIF
						ENDIF
					BREAK

					CASE 3
						IF warehouse_opened = 1
							IF m_guard_flag[3] = 0
								SET_CHAR_RELATIONSHIP m_guard[3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
								SET_CHAR_RELATIONSHIP m_guard[3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 
								TASK_TOGGLE_PED_THREAT_SCANNER m_guard[3] FALSE FALSE FALSE
								OPEN_SEQUENCE_TASK temp_seq
									TASK_GO_STRAIGHT_TO_COORD -1 2786.6631 -2422.2354 12.6340  PEDMOVE_RUN -2
									TASK_SET_CHAR_DECISION_MAKER -1 tough_dm
									TASK_TOGGLE_PED_THREAT_SCANNER -1 TRUE TRUE FALSE
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
									TASK_KILL_CHAR_ON_FOOT -1 scplayer
									TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								CLOSE_SEQUENCE_TASK temp_seq
								PERFORM_SEQUENCE_TASK m_guard[3] temp_seq
								CLEAR_SEQUENCE_TASK temp_seq
							ENDIF
							IF m_guard_flag[3] = 1
								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D m_guard[3] scplayer 5.0 5.0 5.0 FALSE
									TASK_KILL_CHAR_ON_FOOT m_guard[3] scplayer
									m_guard_flag[3]++
								ENDIF
							ENDIF
						ENDIF
					BREAK

					CASE 4
						IF m_guard_flag[4] = 0
							OPEN_SEQUENCE_TASK temp_seq
								TASK_GO_STRAIGHT_TO_COORD -1 2764.9285 -2402.1235 12.6250 PEDMOVE_RUN -2
								TASK_STAY_IN_SAME_PLACE -1 TRUE
								TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_WHEN_PLAYER_SHOOTING 3000 90
								TASK_STAY_IN_SAME_PLACE -1 TRUE 
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK m_guard[4] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							m_guard_flag[4]++				
						ENDIF		
						IF m_guard_flag[4] = 1
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D m_guard[4] scplayer 5.0 5.0 5.0 FALSE
								TASK_KILL_CHAR_ON_FOOT m_guard[4] scplayer
								m_guard_flag[4]++
							ENDIF
						ENDIF
					BREAK
					DEFAULT
						IF m_guard_flag[frame_num] = 0
							OPEN_SEQUENCE_TASK temp_seq
								//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
								TASK_KILL_CHAR_ON_FOOT -1 scplayer
								//TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK m_guard[frame_num] temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							m_guard_flag[frame_num]++
						ENDIF	
					BREAK
				ENDSWITCH

			ENDIF
		ENDIF
	ENDIF

//	// alert guards if alarm has gone off ------------------------------
//	IF alarm_raised = 1
//	AND guards_alerted = 0
//		IF NOT IS_CHAR_DEAD ryder 
//			SET_CHAR_RELATIONSHIP ryder ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 
//			TASK_TOGGLE_PED_THREAT_SCANNER ryder TRUE TRUE FALSE
//		ENDIF
//		temp_int = 0
//		WHILE temp_int < 5
//			IF NOT temp_int = 2
//			AND NOT temp_int = 3
//				IF NOT IS_CHAR_DEAD m_guard[temp_int]
//					SET_CHAR_RELATIONSHIP m_guard[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
//					SET_CHAR_RELATIONSHIP m_guard[temp_int] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 
//					TASK_TOGGLE_PED_THREAT_SCANNER m_guard[temp_int] TRUE TRUE TRUE
//					TASK_KILL_CHAR_ON_FOOT m_guard[temp_int] scplayer
//				ENDIF
//			ENDIF
//		temp_int++
//		ENDWHILE
//
//		IF NOT IS_CHAR_DEAD m_guard[4]
//		
//
//		ENDIF
//		guards_alerted = 1
//	ENDIF


	// create pickups if boxes get destroyed -------------------------------------------------------------------------
	IF frame_num = 2
		temp_int = 0 
		WHILE temp_int < 25
			IF DOES_OBJECT_EXIST m_box[temp_int] 		
				//IF NOT IS_OBJECT_ATTACHED m_box[temp_int]
					IF HAS_OBJECT_BEEN_DAMAGED m_box[temp_int]
						GET_OBJECT_COORDINATES m_box[temp_int] x y z
						
						// PICKUPS
						// ---------------
						// 1. pistol
						// 2. armour
						// 3. grenades
						// 4. health
						// 5. explosion
						// 6. uzi
						// 7. shotgun

						IF IS_OBJECT_ATTACHED m_box[temp_int]
							// do fuck all
						ELSE

							IF m_box_value[temp_int] = 1
								CREATE_PICKUP_WITH_AMMO colt45 PICKUP_ONCE 30 x y z m_pickup[temp_int]	
							ELSE
								IF m_box_value[temp_int] = 2
									CREATE_PICKUP bodyarmour PICKUP_ONCE x y z m_pickup[temp_int]
								ELSE
									IF m_box_value[temp_int] = 3
										CREATE_PICKUP_WITH_AMMO grenade PICKUP_ONCE 6 x y z m_pickup[temp_int]
									ELSE
										IF m_box_value[temp_int] = 4
											CREATE_PICKUP health PICKUP_ONCE x y z m_pickup[temp_int]
										ELSE
											IF m_box_value[temp_int] = 5
												//ADD_EXPLOSION x y z EXPLOSION_GRENADE 
											ELSE
												IF m_box_value[temp_int] = 6
													CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ONCE 30 x y z m_pickup[temp_int]
												ELSE
													IF m_box_value[temp_int] = 7													 
														CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ONCE 30 x y z m_pickup[temp_int]	
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						smashed_boxes++
						DELETE_OBJECT m_box[temp_int]
					ENDIF
				ENDIF
			//ENDIF
		temp_int++
		ENDWHILE
	ENDIF

	// switch van collision if player is in forklift truck ------------------------------------------
	IF enable_fake_van_collision = 1
		IF van_collision = 0
			IF NOT IS_CAR_DEAD fork
				IF IS_CHAR_IN_CAR scplayer fork
					IF NOT IS_CAR_DEAD van
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR van
							IF NOT IS_CHAR_DEAD	ryder
								IF NOT IS_CHAR_IN_CAR ryder van
									IF IS_CAR_STOPPED van

										van_collision = 1
										
										FREEZE_CAR_POSITION van TRUE
										SET_CAR_COLLISION van FALSE

										IF NOT DOES_OBJECT_EXIST fake_van_collision_obj
											GET_CAR_COORDINATES van x y z 
											GET_CAR_HEADING van heading
											CREATE_OBJECT_NO_OFFSET FAKE_MULE_COL x y z fake_van_collision_obj
											SET_OBJECT_HEADING fake_van_collision_obj heading
										ENDIF

									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF loading_box_screen = 0
				IF NOT IS_CAR_DEAD fork
					IF NOT IS_CHAR_IN_CAR scplayer fork
						van_collision = 0
					ENDIF
				ELSE
					van_collision = 0
				ENDIF
				IF van_collision = 0
					IF DOES_OBJECT_EXIST fake_van_collision_obj
						GET_OBJECT_COORDINATES fake_van_collision_obj x y z
						GET_OBJECT_HEADING fake_van_collision_obj heading
						DELETE_OBJECT fake_van_collision_obj 
					ENDIF
					IF NOT IS_CAR_DEAD van
						SET_CAR_COLLISION van TRUE
						FREEZE_CAR_POSITION van FALSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
				   
																					 

	// *******************************************************************************
	//				HELP TEXT
	// *******************************************************************************
	IF frame_num = 3
	AND m_stage >= 3
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND audio_line_is_active = 0

			// switch1 help
			LVAR_INT switch1_help
			IF DOES_OBJECT_EXIST switch1
				IF NOT HAS_OBJECT_BEEN_DAMAGED switch1
					IF LOCATE_CHAR_ON_FOOT_3D scplayer 2722.1143 -2412.0549 12.6198 2.0 2.0 2.0 FALSE
						IF switch1_help = 0
							//PRINT_NOW RY2_42 5000 1 // shoot switch
							PRINT_NOW RY2_61 5000 1 
							switch1_help++
						ENDIF
					ELSE
						IF NOT switch1_help = 0
							switch1_help = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// switch2 help
			LVAR_INT switch2_help
			IF DOES_OBJECT_EXIST switch2			   
				IF NOT HAS_OBJECT_BEEN_DAMAGED switch2 
					IF LOCATE_CHAR_ON_FOOT_3D scplayer 2773.1008 -2423.4185 12.6269 2.0 2.0 2.0 FALSE
						IF switch2_help = 0
							PRINT_NOW RY2_62 5000 1 // shoot switch
							switch2_help++
						ENDIF
					ELSE
						IF NOT switch2_help = 0
							switch2_help = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF

	ENDIF


	// **************************************************
	// 				RYDER DRIVING VAN DIALOGUE
	// **************************************************
	LVAR_INT in_van_way
	IF frame_num = 4
		IF  audio_line_is_active = 0

			IF NOT IS_CAR_DEAD van
				IF IS_PLAYBACK_GOING_ON_FOR_CAR van
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS van 0.0 10.0 0.0 x y z
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer x y z 4.0 4.0 4.0 FALSE
						IF in_van_way = 0
							GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
							SWITCH temp_int
								CASE 0
									$audio_string    = &RYD2_JA	
									audio_sound_file = SOUND_RYD2_JA
									START_NEW_SCRIPT audio_line ryder 0 0 1 0
								BREAK
								CASE 1
									$audio_string    = &RYD2_JB	
									audio_sound_file = SOUND_RYD2_JB
									START_NEW_SCRIPT audio_line ryder 0 0 1 0
								BREAK
								CASE 2
									$audio_string    = &RYD2_JC	
									audio_sound_file = SOUND_RYD2_JC
									START_NEW_SCRIPT audio_line ryder 0 0 1 0
								BREAK
							ENDSWITCH						
							in_van_way++
						ENDIF
					ELSE
						IF NOT in_van_way = 0
							in_van_way = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// **************************************************
	// 				SMASHED BOXES DIALOGUE
	// **************************************************
	IF smashed_boxes = 3
		IF NOT IS_MESSAGE_BEING_DISPLAYED
		AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND audio_line_is_active = 0

			$audio_string    = &RYD2_LG
			audio_sound_file = SOUND_RYD2_LG
			START_NEW_SCRIPT audio_line ryder 0 1 1 0
			smashed_boxes++
		ENDIF
	ENDIF

RETURN

// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************

LVAR_INT throwing_boxes
LVAR_INT throwing_status
LVAR_INT shown_boxes
LVAR_INT show_front_box
LVAR_INT displayed_boxes_in_van[5]
LVAR_INT hide_anim_box
LVAR_INT uvf_temp_int
update_van_filling:

	IF shown_boxes = 6
		show_front_box = 1
		shown_boxes = 5
	ENDIF

	IF show_front_box = 1
		IF NOT IS_CAR_DEAD van
			IF NOT DOES_OBJECT_EXIST hide_anim_box
				CREATE_OBJECT CM_BOX 0.0 0.0 0.0 hide_anim_box
				SET_OBJECT_COLLISION hide_anim_box FALSE
				ATTACH_OBJECT_TO_CAR hide_anim_box van 0.6223 -2.3101 0.2002 0.0 0.0 317.0214	
			ENDIF
		ENDIF
	ELSE
		IF DOES_OBJECT_EXIST hide_anim_box
			DELETE_OBJECT hide_anim_box
		ENDIF	
	ENDIF

	SWITCH shown_boxes

		CASE 0
			uvf_temp_int = 0
			WHILE uvf_temp_int < 5
				IF DOES_OBJECT_EXIST displayed_boxes_in_van[uvf_temp_int]
					DELETE_OBJECT displayed_boxes_in_van[uvf_temp_int]
				ENDIF 
			uvf_temp_int++
			ENDWHILE
		BREAK

		CASE 1
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[0]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[0]
					SET_OBJECT_COLLISION displayed_boxes_in_van[0] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[0] van -0.4928 -0.1684 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			uvf_temp_int = 1
			WHILE uvf_temp_int < 5
				IF DOES_OBJECT_EXIST displayed_boxes_in_van[uvf_temp_int]
					DELETE_OBJECT displayed_boxes_in_van[uvf_temp_int]
				ENDIF 
			uvf_temp_int++
			ENDWHILE
		BREAK

		CASE 2 
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[1]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[1]
					SET_OBJECT_COLLISION displayed_boxes_in_van[1] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[1] van 0.4835 -0.2148 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[0]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[0]
					SET_OBJECT_COLLISION displayed_boxes_in_van[0] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[0] van -0.4928 -0.1684 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			uvf_temp_int = 2
			WHILE uvf_temp_int < 5
				IF DOES_OBJECT_EXIST displayed_boxes_in_van[uvf_temp_int]
					DELETE_OBJECT displayed_boxes_in_van[uvf_temp_int]
				ENDIF 
			uvf_temp_int++
			ENDWHILE
		BREAK

		CASE 3
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[2]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[2]
					SET_OBJECT_COLLISION displayed_boxes_in_van[2] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[2] van -0.2585 -0.3195 1.1567 0.0 0.0 23.4700 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[1]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[1]
					SET_OBJECT_COLLISION displayed_boxes_in_van[1] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[1] van 0.4835 -0.2148 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[0]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[0]
					SET_OBJECT_COLLISION displayed_boxes_in_van[0] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[0] van -0.4928 -0.1684 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			uvf_temp_int = 3
			WHILE uvf_temp_int < 5
				IF DOES_OBJECT_EXIST displayed_boxes_in_van[uvf_temp_int]
					DELETE_OBJECT displayed_boxes_in_van[uvf_temp_int]
				ENDIF 
			uvf_temp_int++
			ENDWHILE
		BREAK
		CASE 4
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[3]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[3]
					SET_OBJECT_COLLISION displayed_boxes_in_van[3] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[3] van -0.4009 -1.7762 0.2292 0.0 0.0 342.0102 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[2]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[2]
					SET_OBJECT_COLLISION displayed_boxes_in_van[2] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[2] van -0.2585 -0.3195 1.1567 0.0 0.0 23.4700 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[1]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[1]
					SET_OBJECT_COLLISION displayed_boxes_in_van[1] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[1] van 0.4835 -0.2148 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[0]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[0]
					SET_OBJECT_COLLISION displayed_boxes_in_van[0] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[0] van -0.4928 -0.1684 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			uvf_temp_int = 4
			WHILE uvf_temp_int < 5
				IF DOES_OBJECT_EXIST displayed_boxes_in_van[uvf_temp_int]
					DELETE_OBJECT displayed_boxes_in_van[uvf_temp_int]
				ENDIF 
			uvf_temp_int++
			ENDWHILE
		BREAK

		CASE 5
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[4]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[4]
					SET_OBJECT_COLLISION displayed_boxes_in_van[4] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[4] van -0.4374 -1.7451 0.9021 0.0 0.0 210.1588 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[3]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[3]
					SET_OBJECT_COLLISION displayed_boxes_in_van[3] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[3] van -0.4009 -1.7762 0.2292 0.0 0.0 342.0102 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[2]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 displayed_boxes_in_van[2]
					SET_OBJECT_COLLISION displayed_boxes_in_van[2] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[2] van -0.2585 -0.3195 1.1567 0.0 0.0 23.4700 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[1]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[1]
					SET_OBJECT_COLLISION displayed_boxes_in_van[1] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[1] van 0.4835 -0.2148 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
			IF NOT DOES_OBJECT_EXIST displayed_boxes_in_van[0]
				IF NOT IS_CAR_DEAD van
					CREATE_OBJECT CJ_CARDBRD_PICKUP 0.0 0.0 0.0 displayed_boxes_in_van[0]
					SET_OBJECT_COLLISION displayed_boxes_in_van[0] FALSE
					ATTACH_OBJECT_TO_CAR displayed_boxes_in_van[0] van -0.4928 -0.1684 0.3614 0.0 0.0 0.0 
				ENDIF
			ENDIF
		BREAK 
	
	ENDSWITCH

RETURN




LVAR_INT exp_temp_int exp_temp_int2
add_to_exploding_boxes:

	exp_temp_int2 = -1

	exp_temp_int = 0
	WHILE exp_temp_int < 10
		IF NOT DOES_OBJECT_EXIST exploding_box[exp_temp_int]
			exp_temp_int2 = exp_temp_int
			exp_temp_int = 10
		ENDIF
	exp_temp_int++
	ENDWHILE

	IF NOT exp_temp_int2 = -1
		exploding_box[exp_temp_int2] = throwing_box
		exploding_box_timer = 0
		SET_OBJECT_RECORDS_COLLISIONS exploding_box[exp_temp_int2] TRUE
		loaded_boxes--
	ENDIF

RETURN


LVAR_INT new_group_member
LVAR_INT ngm_temp_int ngm_temp_int2
set_new_guard_member:

	// fake create
	IF ngm_temp_int = -999
		CREATE_CHAR PEDTYPE_MISSION1 MALE01 0.0 0.0 0.0 	new_group_member
	ENDIF

	IF NOT IS_CHAR_DEAD new_group_member
		GIVE_WEAPON_TO_CHAR new_group_member WEAPONTYPE_PISTOL 99999
		SET_CHAR_ACCURACY new_group_member 32
		//SET_CHAR_SHOOT_RATE new_group_member 50
		SET_CHAR_HEALTH new_group_member 100 
		SET_CHAR_DECISION_MAKER new_group_member tough_dm
		SET_CHAR_RELATIONSHIP new_group_member ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
		IF NOT gate1_opened = 0
			SET_CHAR_RELATIONSHIP new_group_member ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
		ENDIF
		TASK_TOGGLE_PED_THREAT_SCANNER new_group_member TRUE TRUE TRUE
//		SET_SEEING_RANGE new_group_member 100.0
//		SET_HEARING_RANGE new_group_member 100.0
		SET_SENSE_RANGE new_group_member 100.0
		IF NOT DOES_GROUP_EXIST guard_group
			REMOVE_GROUP guard_group
			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL guard_group 
		ENDIF
		GET_GROUP_SIZE guard_group ngm_temp_int ngm_temp_int2
		IF ngm_temp_int = 0
			SET_GROUP_LEADER guard_group new_group_member
		ELSE
			SET_GROUP_MEMBER guard_group new_group_member
		ENDIF
		SET_CHAR_NEVER_LEAVES_GROUP new_group_member TRUE
	ENDIF
RETURN











// ************************************************************
//		get next box coords
// ************************************************************
LVAR_FLOAT closest_dist
LVAR_INT closest_box
LVAR_FLOAT closest_x	closest_y closest_z
LVAR_FLOAT next_box_x next_box_y next_box_z
LVAR_INT next_box
get_box_coords:
	closest_dist 	= 999999.0
	closest_box 	= -1
	closest_x 		= 0.0
	closest_y 		= 0.0
	closest_z 		= 0.0
	temp_int = 0	
	WHILE temp_int < 25
  		IF DOES_OBJECT_EXIST m_box[temp_int]
			IF NOT IS_CHAR_HOLDING_OBJECT scplayer m_box[temp_int]
				IF NOT IS_OBJECT_ATTACHED m_box[temp_int]
					IF NOT HAS_OBJECT_BEEN_DAMAGED m_box[temp_int]	 		
				 		IF NOT IS_CHAR_DEAD ryder
							GET_CHAR_COORDINATES ryder x y z
						ENDIF
						GET_OBJECT_COORDINATES m_box[temp_int] next_box_x next_box_y next_box_z
						GET_DISTANCE_BETWEEN_COORDS_2D next_box_x next_box_y x y temp_float
						IF temp_float < 6.0
							IF temp_float < closest_dist
								closest_dist = temp_float
								closest_box = temp_int
								closest_x = next_box_x 
								closest_y = next_box_y 
								closest_z = next_box_z
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF			
		ENDIF
	temp_int++
	ENDWHILE
	// store resulting values
	next_box 	= 	closest_box
	next_box_x 	=	closest_x
	next_box_y 	=	closest_y
	next_box_z 	=	closest_z
RETURN


LVAR_FLOAT throw_box_x throw_box_y throw_box_z
LVAR_FLOAT throw_box_q1	throw_box_q2 throw_box_q3 throw_box_q4
ry2_throw_box:

	// detach box
	GET_OBJECT_COORDINATES throwing_box throw_box_x throw_box_y throw_box_z
	GET_OBJECT_QUATERNION throwing_box throw_box_q1	throw_box_q2 throw_box_q3 throw_box_q4
	DROP_OBJECT ryder TRUE

	IF NOT IS_CAR_DEAD van
		GET_CAR_FORWARD_X van vec_x
		GET_CAR_FORWARD_Y van vec_y
	ENDIF
	vec_x *= -10.0
	vec_y *= -10.0	 
	SET_OBJECT_COORDINATES throwing_box throw_box_x throw_box_y throw_box_z
	SET_OBJECT_QUATERNION throwing_box throw_box_q1	throw_box_q2 throw_box_q3 throw_box_q4
	SET_OBJECT_DYNAMIC throwing_box TRUE
	SET_OBJECT_COLLISION throwing_box TRUE
	ADD_TO_OBJECT_VELOCITY throwing_box vec_x vec_y 0.0
	IF NOT IS_CAR_DEAD van
		SORT_OUT_OBJECT_COLLISION_WITH_CAR throwing_box van 
	ENDIF
	GOSUB add_to_exploding_boxes
	throwing_box = 0

RETURN

// *********************************************************************************************
// 									process spawned guards
// *********************************************************************************************
// parameters
LVAR_INT spawn_this_many_guards
// workings
LVAR_INT last_spawned_guard_position
LVAR_INT this_spawned_guard_position
LVAR_INT spawn_flag
LVAR_INT spawned_dude[5]
LVAR_INT this_spawned_dude
LVAR_INT spawn_int
// settings
LVAR_FLOAT spawn_x1[10] spawn_y1[10] spawn_z1[10] spawn_h1[10]
LVAR_FLOAT spawn_x2[10] spawn_y2[10] spawn_z2[10] 
LVAR_FLOAT spawn_x3[10] spawn_y3[10] spawn_z3[10] 
LVAR_FLOAT spawn_x4[10] spawn_y4[10] spawn_z4[10] 
process_spawning_guards:

	IF spawn_this_many_guards > 0
		SWITCH spawn_flag

			// check model is loaded
			CASE 0
				IF NOT HAS_MODEL_LOADED ARMY
					REQUEST_MODEL ARMY
				ELSE
					spawn_flag++
				ENDIF
			BREAK

			// check there is space
			CASE 1
				// check there is space to create a new spawned dude
				this_spawned_dude = -1
				spawn_int = 0
				WHILE spawn_int < 5
					IF DOES_CHAR_EXIST spawned_dude[spawn_int]
						IF IS_CHAR_DEAD spawned_dude[spawn_int]
							MARK_CHAR_AS_NO_LONGER_NEEDED spawned_dude[spawn_int]
							spawned_dude[spawn_int] = 0
							this_spawned_dude = spawn_int
							spawn_int = 5
						ENDIF	
					ELSE
						this_spawned_dude = spawn_int
						spawn_int = 5
					ENDIF
				spawn_int++
				ENDWHILE
				IF NOT this_spawned_dude = -1
					spawn_flag++
				ENDIF
			BREAK

			// check for suitable spawn position
			CASE 2
				ry2_spawn_bit:
				IF this_spawned_guard_position = 3
				OR this_spawned_guard_position = 7
				OR this_spawned_guard_position = 8
				OR this_spawned_guard_position = 9
					this_spawned_guard_position++ 
					GOTO ry2_spawn_bit
				ENDIF
				IF this_spawned_guard_position > 9
					this_spawned_guard_position = 0
				ENDIF

				IF NOT this_spawned_guard_position = last_spawned_guard_position
					IF NOT IS_POINT_ON_SCREEN spawn_x1[this_spawned_guard_position] spawn_y1[this_spawned_guard_position] spawn_z1[this_spawned_guard_position] 2.0 
						IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY spawn_x1[this_spawned_guard_position] spawn_y1[this_spawned_guard_position] spawn_z1[this_spawned_guard_position] 1.5 1.5 2.0  
							spawn_flag++
						ELSE
							this_spawned_guard_position++
						ENDIF
					ELSE
						this_spawned_guard_position++
					ENDIF
				ELSE
					this_spawned_guard_position++
				ENDIF
				IF this_spawned_guard_position > 9
					this_spawned_guard_position = 0
				ENDIF
			BREAK

			// create dude
			CASE 3
	 			CREATE_CHAR PEDTYPE_MISSION1 ARMY spawn_x1[this_spawned_guard_position] spawn_y1[this_spawned_guard_position] spawn_z1[this_spawned_guard_position] spawned_dude[this_spawned_dude] 
				new_group_member = spawned_dude[this_spawned_dude]
				GOSUB set_new_guard_member
				OPEN_SEQUENCE_TASK temp_seq	
					TASK_TOGGLE_PED_THREAT_SCANNER -1 TRUE TRUE TRUE
					TASK_GO_STRAIGHT_TO_COORD -1 spawn_x2[this_spawned_guard_position] spawn_y2[this_spawned_guard_position] spawn_z2[this_spawned_guard_position] PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 spawn_x3[this_spawned_guard_position] spawn_y3[this_spawned_guard_position] spawn_z3[this_spawned_guard_position] PEDMOVE_RUN -2
					IF NOT IS_CHAR_DEAD ryder
						TASK_GOTO_CHAR -1 ryder 20000 25.0
						//TASK_KILL_CHAR_ON_FOOT -1 ryder
					ENDIF
				CLOSE_SEQUENCE_TASK	temp_seq
				PERFORM_SEQUENCE_TASK spawned_dude[this_spawned_dude] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				last_spawned_guard_position = this_spawned_guard_position
				spawn_this_many_guards += -1
				spawn_flag = 0
				//WRITE_DEBUG_WITH_INT GUARD_CREATED this_spawned_guard_position
			BREAK

		ENDSWITCH
	ELSE
		spawn_flag = 0	
	ENDIF

RETURN


LVAR_INT adjust_box_int		
adjust_box_position:
	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B
		adjust_box_int++
		IF adjust_box_int > 24
			adjust_box_int = 0
		ENDIF
		WRITE_DEBUG_WITH_INT ADJUST_BOX_NUMBER adjust_box_int
	ENDIF

	IF DOES_OBJECT_EXIST m_box[adjust_box_int]
		GET_OBJECT_COORDINATES m_box[adjust_box_int] x y z
		GET_OBJECT_HEADING m_box[adjust_box_int] heading
		z += 1.0
		DRAW_CORONA x y z 0.2 CORONATYPE_MOON FLARETYPE_NONE 255 0 0
		z += -1.0
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		y += 0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		y += -0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_RIGHT
		x += 0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_LEFT
		x += -0.1
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
		z += 0.01
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
		z += -0.01
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
		heading += 1.0
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
		heading += -1.0
	ENDIF


	IF DOES_OBJECT_EXIST m_box[adjust_box_int]
		SET_OBJECT_COORDINATES m_box[adjust_box_int] x y z
		SET_OBJECT_HEADING m_box[adjust_box_int] heading
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ENTER
		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "Box coords = "
		SAVE_INT_TO_DEBUG_FILE adjust_box_int
		SAVE_FLOAT_TO_DEBUG_FILE x
		SAVE_FLOAT_TO_DEBUG_FILE y
		SAVE_FLOAT_TO_DEBUG_FILE z
		SAVE_FLOAT_TO_DEBUG_FILE heading

	ENDIF

RETURN


// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_RYDER2:

//	WRITE_DEBUG ryder2_failed
	PRINT_BIG M_FAIL 5000 1

RETURN

// PASS
mission_passed_RYDER2:
	CLEAR_WANTED_LEVEL player1
	REGISTER_MISSION_PASSED RYDER_2

	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 5 //amount of respect
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1

	PLAYER_MADE_PROGRESS 1

	IF flag_returned_tags = 0
		SET_GANG_WEAPONS GANG_GROVE WEAPONTYPE_PISTOL WEAPONTYPE_TEC9 WEAPONTYPE_UNARMED
	ENDIF

	REMOVE_BLIP ryder_contact_blip

	SET_INT_STAT PASSED_RYDER2 1

	flag_ryder_mission_counter++ 
RETURN

// CLEANUP
mission_cleanup_RYDER2:

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 FALSE
	ENDIF

	IF NOT IS_CAR_DEAD van
		FREEZE_CAR_POSITION van FALSE
		SET_VEHICLE_IS_CONSIDERED_BY_PLAYER van TRUE
	ENDIF

	SET_PLAYER_IN_CAR_CAMERA_MODE   ZOOM_TWO

	IF NOT IS_CAR_DEAD m_mesa
		MARK_CAR_AS_NO_LONGER_NEEDED m_mesa
	ENDIF

	// tell any remaining peds to attack player
	IF alarm_raised = 1

		temp_int = 0
		WHILE temp_int < 9
			IF DOES_CHAR_EXIST m_guard[temp_int]
				IF NOT IS_CHAR_DEAD m_guard[temp_int]
					TASK_KILL_CHAR_ON_FOOT m_guard[temp_int] scplayer
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		temp_int = 0
		WHILE temp_int < 5
			IF DOES_CHAR_EXIST spawned_dude[temp_int]
				IF NOT IS_CHAR_DEAD spawned_dude[temp_int]
					TASK_KILL_CHAR_ON_FOOT spawned_dude[temp_int] scplayer
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

	ENDIF

	IF NOT IS_CHAR_DEAD ryder
		REMOVE_CHAR_ELEGANTLY ryder 
	ENDIF

	// === MARK ENTITIES AS NO LONGER NEEDED === (cars,peds,objects,blips,attractors)
	MARK_OBJECT_AS_NO_LONGER_NEEDED	gate1
	MARK_OBJECT_AS_NO_LONGER_NEEDED	gate2
	MARK_OBJECT_AS_NO_LONGER_NEEDED	gate3
	MARK_OBJECT_AS_NO_LONGER_NEEDED	gate4
	MARK_OBJECT_AS_NO_LONGER_NEEDED	gate7

	temp_int = 0
	WHILE temp_int < 25
		MARK_OBJECT_AS_NO_LONGER_NEEDED m_box[temp_int]
		IF NOT m_pickup[temp_int] = 0
			REMOVE_PICKUP m_pickup[temp_int]
			m_pickup[temp_int] = 0
		ENDIF
	temp_int++
	ENDWHILE 									   
	temp_int = 0
	WHILE temp_int < 5
		//MARK_CHAR_AS_NO_LONGER_NEEDED m_guard[temp_int]
	temp_int++
	ENDWHILE

	REMOVE_BLIP van_blip
	REMOVE_BLIP location_blip
	REMOVE_BLIP ryder_blip
	REMOVE_BLIP switch1_blip
	REMOVE_BLIP switch2_blip

	temp_int = 0
	WHILE temp_int < 5
		IF DOES_OBJECT_EXIST displayed_boxes_in_van[temp_int] 
			DELETE_OBJECT displayed_boxes_in_van[temp_int]
		ENDIF
	temp_int++
	ENDWHILE

	IF DOES_OBJECT_EXIST hide_anim_box
		DELETE_OBJECT hide_anim_box
	ENDIF

	IF DOES_OBJECT_EXIST fake_van_collision_obj
		DELETE_OBJECT fake_van_collision_obj
	ENDIF

	IF DOES_VEHICLE_EXIST van
		IF NOT IS_CAR_DEAD van
			FREEZE_CAR_POSITION van FALSE
			SET_CAR_COLLISION van TRUE
		ENDIF
	ENDIF

	SWITCH_EMERGENCY_SERVICES ON

	REMOVE_DECISION_MAKER empty_dm
	REMOVE_DECISION_MAKER tough_dm
	REMOVE_DECISION_MAKER ryder_dm
	REMOVE_DECISION_MAKER driveby_dm
	
	DONT_SUPPRESS_CAR_MODEL MULE

	// === REMOVE ANY OTHER LOADED STUFF === (models,animations,textures)
	CLEAR_ONSCREEN_COUNTER loaded_boxes_neg 
	CLEAR_ONSCREEN_COUNTER ryder_health 

	MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
	MARK_MODEL_AS_NO_LONGER_NEEDED MULE
	MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT
	MARK_MODEL_AS_NO_LONGER_NEEDED MESA
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_CARDBRD_PICKUP
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
//	REMOVE_ANIMATION box
//	REMOVE_ANIMATION carry
	REMOVE_ANIMATION ryder
	REMOVE_ANIMATION colt45

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0

	GET_GAME_TIMER timer_mobile_start

	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3


//	WRITE_DEBUG ryder2_cleaned_up
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN
}
